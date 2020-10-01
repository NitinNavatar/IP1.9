/**
 * 
 */
package com.navatar.scripts;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.navatar.generic.BaseLib;
import com.navatar.generic.EmailLib;
import com.navatar.generic.CommonLib.EditViewMode;
import com.navatar.generic.CommonLib.EnableDisable;
import com.navatar.generic.CommonLib.FolderType;
import com.navatar.generic.CommonLib.PageName;
import com.navatar.generic.CommonLib.TabName;
import com.navatar.generic.CommonLib.WorkSpaceAction;
import com.navatar.generic.CommonLib.Workspace;
import com.navatar.generic.CommonLib.accessType;
import com.navatar.generic.CommonLib.action;
import com.navatar.generic.CommonLib.excelLabel;
import com.navatar.generic.CommonLib.fileDistributor;
import com.navatar.generic.CommonLib.sideMenu;
import com.navatar.generic.CommonLib.userType;
import com.navatar.generic.ExcelUtils;
import com.navatar.generic.SoftAssert;
import com.navatar.pageObjects.AllFirmsPageBusinesslayer;
import com.navatar.pageObjects.BasePageBusinessLayer;
import com.navatar.pageObjects.CommitmentPageBusinessLayer;
import com.navatar.pageObjects.ContactPageBusinessLayer;
import com.navatar.pageObjects.FundRaisingPageBusinessLayer;
import com.navatar.pageObjects.FundsPageBusinessLayer;
import com.navatar.pageObjects.FundsPageErrorMessage;
import com.navatar.pageObjects.InstitutionPageBusinessLayer;
import com.navatar.pageObjects.InvestorFirmPageBusinesslayer;
import com.navatar.pageObjects.LoginPageBusinessLayer;
import com.navatar.pageObjects.NIMPageBusinessLayer;
import com.navatar.pageObjects.PartnershipPageBusinessLayer;

import static com.navatar.generic.AppListeners.appLog;
import static com.navatar.generic.BaseLib.sa;
import static com.navatar.generic.CommonLib.*;
import static com.navatar.generic.CommonVariables.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * @author Akul Bhutani
 *
 */
public class Module16 extends BaseLib{
	
	@Test
	public void M16tc001_Precondition() {
	LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
	BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
	FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
	FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
	InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
	ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
	PartnershipPageBusinessLayer pp = new PartnershipPageBusinessLayer(driver);
	CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
	NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
	if(lp.preCondition(superAdminUserName, CRMUser1FirstName+" "+CRMUser1LastName+"<break>"+CRMUser2FirstName+" "+CRMUser2LastName+"<break>"+CRMUser3FirstName+" "+CRMUser3LastName, CRMUser1EmailID+"<break>"+CRMUser2EmailID+"<break>"+CRMUser3EmailID, EnableDisable.Disable, EnableDisable.Disable, accessType.InternalUserAccess)){
		appLog.info("Provided internal user access to crm user "+CRMUser1FirstName+" "+CRMUser1LastName+".");
	} else {
		appLog.error("Not able to provide internal user access to crm user "+CRMUser1FirstName+" "+CRMUser1LastName+".");
		sa.assertTrue(false,"Not able to provide internal user access to crm user "+CRMUser1FirstName+" "+CRMUser1LastName+".");
	}
	/*if (bp.clickOnTab(TabName.NIMTab)) {
		if (np.giveAccessToUserInNIMTabFromAdmin(CRMUser1FirstName+" "+CRMUser1LastName, accessType.InternalUserAccess)) {
			appLog.info("successfully given access to "+CRMUser1FirstName+" "+CRMUser1LastName);
		}
		else {
			appLog.error("could not give access to "+CRMUser1FirstName+" "+CRMUser1LastName);
			sa.assertTrue(false, "could not give access to "+CRMUser1FirstName+" "+CRMUser1LastName);
		}
		if (np.giveAccessToUserInNIMTabFromAdmin(CRMUser2FirstName+" "+CRMUser2LastName, accessType.InternalUserAccess)) {
			appLog.info("successfully given access to "+CRMUser2FirstName+" "+CRMUser2LastName);
		}
		else {
			appLog.error("could not give access to "+CRMUser2FirstName+" "+CRMUser2LastName);
			sa.assertTrue(false, "could not give access to "+CRMUser2FirstName+" "+CRMUser2LastName);
		}
		if (np.giveAccessToUserInNIMTabFromAdmin(CRMUser3FirstName+" "+CRMUser3LastName, accessType.InternalUserAccess)) {
			appLog.info("successfully given access to "+CRMUser3FirstName+" "+CRMUser3LastName);
		}
		else {
			appLog.error("could not give access to "+CRMUser3FirstName+" "+CRMUser3LastName);
			sa.assertTrue(false, "could not give access to "+CRMUser3FirstName+" "+CRMUser3LastName);
		}

	}*/
	

	driver.close();
	config(ExcelUtils.readDataFromPropertyFile("Browser"));
	lp = new LoginPageBusinessLayer(driver);
	bp = new BasePageBusinessLayer(driver);
	np = new NIMPageBusinessLayer(driver);
	
	
	lp.CRMLogin(superAdminUserName, adminPassword);
	if (bp.clickOnTab(TabName.NIMTab)) {
		if (np.giveAccessToUserInNIMTabFromAdmin(CRMUser2FirstName+" "+CRMUser2LastName, accessType.AdminUserAccess)) {
			appLog.info("successfully given admin access to "+CRMUser2FirstName+" "+CRMUser2LastName);
		}
		else {
			appLog.error("could not give admin access to "+CRMUser2FirstName+" "+CRMUser2LastName);
			sa.assertTrue(false, "could not give admin access to "+CRMUser2FirstName+" "+CRMUser2LastName);
		}
	}
	else {
		appLog.error("nim tab is not clickable");
		sa.assertTrue(false, "nim tab is not clickable");
	}
	lp.CRMlogout();
	sa.assertAll();
}

	@Test
	public void M16tc002_CreatePreconditionData() {
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
			if (ip.createInstitution(M16Institution1)) {
				appLog.info("Institution Created Successfully : " + M16Institution1);
			} else {
				appLog.error("Not Able to Create Institution : " + M16Institution1);
				sa.assertTrue(false, "Not Able to Create Institution : " + M16Institution1);
			}
		}
		
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.createInstitution(M16Institution2)) {
				appLog.info("Institution Created Successfully : " + M16Institution2);
			} else {
				appLog.error("Not Able to Create Institution : " + M16Institution2);
				sa.assertTrue(false, "Not Able to Create Institution : " + M16Institution2);
			}
		}
		
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.createInstitution(M16Institution3)) {
				appLog.info("Institution Created Successfully : " + M16Institution3);
			} else {
				appLog.error("Not Able to Create Institution : " + M16Institution3);
				sa.assertTrue(false, "Not Able to Create Institution : " + M16Institution3);
			}
		}
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.createFund(M16FundName1, M16Fund1Type, M16Fund1InvestmentCategory)) {
				appLog.info("New fund " + M16FundName1 + " was successfully created");
			} else {
				appLog.error("New fund " + M16FundName1 + " could not be created");
				sa.assertTrue(false, "New fund " + M16FundName1 + " could not be created");
			}
		} else {
			appLog.error("Not Able to Click Funds tab ");
			sa.assertTrue(false, "Not Able to Click Funds tab ");
		}
		String M16Contact1EmailID = cp.generateRandomEmailId();

		// Contact
		if (bp.clickOnTab(TabName.ContactTab)) {
			if (cp.createContact(M16Contact1FirstName, M16Contact1LastName, M16Institution1, M16Contact1EmailID)) {
				ExcelUtils.writeData(M16Contact1EmailID, "Contacts", excelLabel.Variable_Name, "M16Contact1",
						excelLabel.Contact_EmailId);
				appLog.info("Contact " + M16Contact1FirstName + " " + M16Contact1LastName + " was successfully created");
			} else {
				appLog.error("Contact " + M16Contact1FirstName + " " + M16Contact1LastName + " could not be created");
				sa.assertTrue(false,
						"Contact " + M16Contact1FirstName + " " + M16Contact1LastName + " could not be created");
			}
		} else {
			appLog.error("Not Able to Click contact Tab");
			sa.assertTrue(false, "Not Able to Click contact Tab");
		}
		// Fund Raising
		if (bp.clickOnTab(TabName.FundraisingsTab)) {
			if (frp.createFundRaising(M16FundRaisingName1, M16FundName1, M16Institution1)) {
				appLog.info("Fundraising " + M16FundRaisingName1 + " was successfully created");
			} else {
				appLog.error("Fundraising " + M16FundRaisingName1 + " could not be created");
				sa.assertTrue(false, "Fundraising " + M16FundRaisingName1 + " could not be created");
			}
		} else {
			appLog.error("Not Able to Click Fundraising tab");
			sa.assertTrue(false, "Not Able to Click Fundraising tab");
		}
		
		if (bp.clickOnTab(TabName.FundraisingsTab)) {
			if (frp.createFundRaising(M16FundRaisingName2, M16FundName1, M16Institution2)) {
				appLog.info("Fundraising " + M16FundRaisingName1 + " was successfully created");
			} else {
				appLog.error("Fundraising " + M16FundRaisingName1 + " could not be created");
				sa.assertTrue(false, "Fundraising " + M16FundRaisingName1 + " could not be created");
			}
		} else {
			appLog.error("Not Able to Click Fundraising tab");
			sa.assertTrue(false, "Not Able to Click Fundraising tab");
		}
		
		if (bp.clickOnTab(TabName.FundraisingsTab)) {
			if (frp.createFundRaising(M16FundRaisingName3, M16FundName1, M16Institution3)) {
				appLog.info("Fundraising " + M16FundRaisingName1 + " was successfully created");
			} else {
				appLog.error("Fundraising " + M16FundRaisingName1 + " could not be created");
				sa.assertTrue(false, "Fundraising " + M16FundRaisingName1 + " could not be created");
			}
		} else {
			appLog.error("Not Able to Click Fundraising tab");
			sa.assertTrue(false, "Not Able to Click Fundraising tab");
		}
		// Limited Partner
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.createLimitedPartner(M16LimitedPartner1, M16Institution1)) {
				appLog.info(M16LimitedPartner1 + " limited partner was successfully created");
			} else {
				appLog.error(M16LimitedPartner1 + " LP could not be created");
				sa.assertTrue(false, M16LimitedPartner1 + " LP could not be created");
			}
		} else {
			appLog.error("Not Able to Click Institution tab");
			sa.assertTrue(false, "Not Able to Click Institution tab");
		}

		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.createLimitedPartner(M16LimitedPartner2, M16Institution2)) {
				appLog.info(M16LimitedPartner2 + " limited partner was successfully created");
			} else {
				appLog.error(M16LimitedPartner2 + " LP could not be created");
				sa.assertTrue(false, M16LimitedPartner2 + " LP could not be created");
			}
		} else {
			appLog.error("Not Able to Click Institution tab");
			sa.assertTrue(false, "Not Able to Click Institution tab");
		}

		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.createLimitedPartner(M16LimitedPartner3, M16Institution3)) {
				appLog.info(M16LimitedPartner3 + " limited partner was successfully created");
			} else {
				appLog.error(M16LimitedPartner3 + " LP could not be created");
				sa.assertTrue(false, M16LimitedPartner3 + " LP could not be created");
			}
		} else {
			appLog.error("Not Able to Click Institution tab");
			sa.assertTrue(false, "Not Able to Click Institution tab");
		}
		// PartnerShip
		if (bp.clickOnTab(TabName.PartnershipsTab)) {
			if (pp.createPartnership(M16Partnership1, M16FundName1)) {
				appLog.info(M16Partnership1 + " was successfully created");
			} else {
				appLog.error(M16Partnership1 + " could not be created");
				sa.assertTrue(false, M16Partnership1 + " could not be created");
			}
		} else {
			appLog.error("Not Able to Click Partnership tab");
			sa.assertTrue(false, "Not Able to Click Partnership tab");
		}

		// Commitment
		if (bp.clickOnTab(TabName.CommitmentsTab)) {
			if (cmp.createCommitment(M16LimitedPartner1, M16Partnership1, M16Commitment1, null)) {
				appLog.info(M16Commitment1 + " was successfully created");
			} else {
				appLog.error(M16Commitment1 + " could not be created");
				sa.assertTrue(false, M16Commitment1 + " could not be created");
			}
		} else {
			appLog.error("Not Able to Click Commitments tab");
			sa.assertTrue(false, "Not Able to Click Commitments tab");
		}

		// Commitment
		if (bp.clickOnTab(TabName.CommitmentsTab)) {
			if (cmp.createCommitment(M16LimitedPartner2, M16Partnership1, M16Commitment2, null)) {
				appLog.info(M16Commitment1 + " was successfully created");
			} else {
				appLog.error(M16Commitment1 + " could not be created");
				sa.assertTrue(false, M16Commitment1 + " could not be created");
			}
		} else {
			appLog.error("Not Able to Click Commitments tab");
			sa.assertTrue(false, "Not Able to Click Commitments tab");
		}

		// Commitment
		if (bp.clickOnTab(TabName.CommitmentsTab)) {
			if (cmp.createCommitment(M16LimitedPartner3, M16Partnership1, M16Commitment3, null)) {
				appLog.info(M16Commitment1 + " was successfully created");
			} else {
				appLog.error(M16Commitment1 + " could not be created");
				sa.assertTrue(false, M16Commitment1 + " could not be created");
			}
		} else {
			appLog.error("Not Able to Click Commitments tab");
			sa.assertTrue(false, "Not Able to Click Commitments tab");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M16tc003_ActiveWatermarkingAndManageApprovals() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName,adminPassword);
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
			switchToDefaultContent(driver);
			switchToFrame(driver, 30, nim.getFrame( PageName.NavatarInvestorManager, 10));
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
	public void M16tc004_VerifyUIOfBulkUpload() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		PartnershipPageBusinessLayer pp = new PartnershipPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, fp.getFrame( PageName.NavatarInvestorManager, 30));
			if (np.clickOnSideMenusTab(sideMenu.FileDistributorSettings)) {
				if (np.verifyFileDistributerUI(fileDistributor.BulkUpload)) {
					appLog.info("successfully verified ui of bulk upload");
				}
				else {
					appLog.error("ui of bulk upload is not verified");
					sa.assertTrue(false, "ui if not verified");
				}
				
				if (click(driver, np.getReverseNamingRadioButton(30), "ReverseNamingRadioButton", action.BOOLEAN)) {
					if (click(driver, np.getNimRegisterPopupCancelButton(30), "CancelButton", action.SCROLLANDBOOLEAN)) {
						if (isSelected(driver,np.getDefaultNamingRadioButton(30) , "DefaultNamingRadioButton")) {
							appLog.info("default naming option is selected after clicking cancel button");
						}
						else {
							appLog.error("default naming option is not selected after clicking cancel button");
							sa.assertTrue(false, "default naming option is not selected after clicking cancel button");
						}
					}
					else {
						appLog.error("cancel button is not clickable on nim page");
						sa.assertTrue(false, "cancel button is not clickable on nim page");
					}
				}
				else {
					appLog.error("reverse naming convention option is not clickable");
					sa.assertTrue(false, "reverse naming convention option is not clickable");
				}
			}
			switchToDefaultContent(driver);
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M16tc005_VerifyUIOfFileSplitter() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, fp.getFrame( PageName.NavatarInvestorManager, 30));
			if (np.clickOnSideMenusTab(sideMenu.FileDistributorSettings)) {
				if (np.clickOnSideMenusTab(sideMenu.FileSplitterOptions)) {
					if (np.verifyFileDistributerUI(fileDistributor.FileSplitter)) {
						appLog.info("successfully verified file splitter UI");
					}
					else {
						appLog.error("ui of file splitter is not verified");
						sa.assertTrue(false, "ui of file splitter is not verified");
					}
					if (click(driver, np.getReverseNamingRadioButton(30), "ReverseNamingRadioButton", action.BOOLEAN)) {
						if (click(driver, np.getNimRegisterPopupCancelButton(30), "CancelButton", action.SCROLLANDBOOLEAN)) {
							if (isSelected(driver,np.getDefaultNamingRadioButton(30) , "DefaultNamingRadioButton")) {
								appLog.info("default naming option is selected after clicking cancel button");
							}
							else {
								appLog.error("default naming option is not selected after clicking cancel button");
								sa.assertTrue(false, "default naming option is not selected after clicking cancel button");
							}
						}
						else {
							appLog.error("cancel button is not clickable on nim page");
							sa.assertTrue(false, "cancel button is not clickable on nim page");
						}
					}
					else {
						appLog.error("reverse naming convention option is not clickable");
						sa.assertTrue(false, "reverse naming convention option is not clickable");
					}
				}
				else {
					appLog.error("file splitter option is not clickable");
					sa.assertTrue(false, "file splitter option is not clickable");
				}
			}
			else {
				appLog.error("file distributor menu item is not clickable");
				sa.assertTrue(false, "file distributor menu item is not clickable");
			}
			switchToDefaultContent(driver);
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M16tc006_BuildWorkspaceAndInvite() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		String Size = ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M16Fund1", excelLabel.Fund_Size);
		String vintageyear = ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M16Fund1",excelLabel.Fund_VintageYear);
		String contact = ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M16Fund1",excelLabel.Fund_Contact);
		String phone = ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M16Fund1", excelLabel.Fund_Phone);
		String email = ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M16Fund1", excelLabel.Fund_Email);
		String description = ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M16Fund1",excelLabel.Fund_Description);
		String[] data = { Size, vintageyear, contact, phone, email, description };

		
		if (np.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M16FundName1)) {
				ThreadSleep(3000);
				if (fp.buildWorkspace(data, WorkSpaceAction.IMPORTFOLDERTEMPLATE, folderTemplateName, null, M16Institution1+"<break>"+M16Institution2+"<break>"+M16Institution3, Workspace.FundraisingWorkspace, 30)){
					appLog.info("fundraising worskpace has been created succesfully");
				}
				else {
					appLog.error("could not build fundraising workspace");
					sa.assertTrue(false, "could not build fundraising workspace");
				}
				if (fp.inviteContact(environment, mode,M16Institution1, M16Contact1EmailId, null, FolderType.Standard, "Upload", "Yes", "No", M16Institution1, Workspace.FundraisingWorkspace, null)) {
					appLog.info("invited contact "+M16Contact1FirstName+" "+M16Contact1LastName+" for "+M16Institution1);
				}
				else {
					appLog.error("could not invite contact "+M16Contact1EmailId + " for "+M16Institution1);
					sa.assertTrue(false, "could not invite contact "+M16Contact1EmailId + " for "+M16Institution1);
				}
				if (fp.inviteContact(environment, mode,M16Institution2, M16Contact1EmailId, null, FolderType.Standard, "Upload", "Yes", "No", M16Institution2, Workspace.FundraisingWorkspace, M16Contact1EmailId)) {
					appLog.info("invited contact "+M16Contact1FirstName+" "+M16Contact1LastName+" for "+M16Institution2);
				}
				else {
					appLog.error("could not invite contact "+M16Contact1EmailId + " for "+M16Institution2);
					sa.assertTrue(false, "could not invite contact "+M16Contact1EmailId + " for "+M16Institution2);
				}
				if (fp.inviteContact(environment, mode,M16Institution3, M16Contact1EmailId, null, FolderType.Standard, "Upload", "Yes", "Yes", M16Institution3, Workspace.FundraisingWorkspace, M16Contact1EmailId)) {
					appLog.info("invited contact "+M16Contact1FirstName+" "+M16Contact1LastName+" for "+M16Institution3);
				}
				else {
					appLog.error("could not invite contact "+M16Contact1EmailId + " for "+M16Institution3);
					sa.assertTrue(false, "could not invite contact "+M16Contact1EmailId + " for "+M16Institution3);
				}
			}

			else {
				appLog.error("cannot click on fund "+M16FundName1);
				sa.assertTrue(false, "cannot click on fund "+M16FundName1);
			}
		}
		else {
			appLog.error("cannot click on funds tab");
			sa.assertTrue(false, "cannot click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test	
	public void M16tc007_RegisterContact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		
		
		String reg_link = null;
		try {
			reg_link = new EmailLib().getInvestorRegLink("InvitationMail", gmailUserName, gmailPassword, CRMUser1EmailID, M16Contact1EmailId);
		} catch (Exception e) {
			
			e.printStackTrace();
			appLog.error("investor registration url was not found on mail");
			sa.assertTrue(false, "investor registration url was not found on mail");
		}
		if (reg_link == null) {
			driver.get(InvestorRegistrationURL);
		}
		else {
			driver.get(reg_link);
		}
		if (lp.targetRegistration(M16Contact1FirstName, M16Contact1LastName, M16Contact1EmailId, Org1FirmName, adminPassword)) {
			appLog.info("contact successfully registered");
		}
		else {
			appLog.error("could not register contact");
			sa.assertTrue(false, "could not register contact");
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Test
	public void M16tc008_VerifyFileDistributor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		PartnershipPageBusinessLayer pp = new PartnershipPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String parentID = null;
		String dropImage="BulkUpload.jpg";
		String folderpath1 = "UploadFiles\\Module16\\InvalidFileDistributor";
		String folderpath2 = "UploadFiles\\Module16\\Testing";
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M16FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, M16Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					if (click(driver, fp.getUploadIcon(Workspace.FundraisingWorkspace, 30), "upload icon fundraising workspace", action.SCROLLANDBOOLEAN)) {
						parentID = switchOnWindow(driver);
						if (parentID!=null) {
							if (!isSelected(driver, fp.getBulkUploaderOrFileSplitterRadioButton(30), "radio button for bulk upload")) {
								appLog.info("on upload window, radio button for bulk upload is not selected by default");
							}
							else {
								appLog.error("radio button for bulk upload is selected by default but it should not be");
								sa.assertTrue(false, "radio button for bulk upload is selected by default but it should not be");
							}
							if (click(driver, fp.getBulkUploaderOrFileSplitterRadioButton(30), "radio button for bulk upload", action.BOOLEAN)) {
								if (fp.getFileDistributorHead(30)!=null) {
									appLog.info("file distributor head is successfully displayed on upload window");
								}
								else {
									appLog.error("file distributor head is not displayed on upload window");
									sa.assertTrue(false, "file distributor head is not displayed on upload window");
								}
								if (fp.getFileDistributorCancelBtn(30)!=null) {
									appLog.info("cancel button on upload window is successfully displayed");
								}
								else {
									appLog.error("cancel button is not displayed on file distributor window");
									sa.assertTrue(false, "cancel button is not displayed on file distributor window");
								}
							}
							if (fp.dragDropFiles(folderpath1, dropImage)) {
								ThreadSleep(3000);
								if (click(driver, FindElement(driver, "//li[text()='All Folders']/ul/li[text()='"+stdPath+"']/input", "parent folder radio button", action.BOOLEAN, 10), "parent folder radio button", action.BOOLEAN)) {
									if (fp.clickUsingCssSelectorPath("div#BulkDocument_step_1of2 a[title=Next]", "next button")) {
									//if (click(driver, fp.getFileDistNextBtn(30), "file dist next button", action.BOOLEAN)) {
										ThreadSleep(5000);
										if (isAlertPresent(driver)) {
											String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
											switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
											if (msg.contains(FundsPageErrorMessage.invalidDelimeterError1)) {
												appLog.info("invalid delimiter error message is successfully verified");
											}
											else {
												appLog.error("invalid delimiter error message is wrong");
												sa.assertTrue(false, "invalid delimiter error message is wrong");
											}
											if (msg.contains(FundsPageErrorMessage.invalidDelimeterError2)) {
												appLog.info("invalid delimiter error message is successfully verified");
											}
											else {
												appLog.error("invalid delimiter error message is wrong");
												sa.assertTrue(false, "invalid delimiter error message is wrong");
											}
										}
										else {
											appLog.error("no alert message is present");
											sa.assertTrue(false, "no alert message is present");
										}
										//now standard radio button will be unchecked
										if (!isSelected(driver, FindElement(driver, "//li[text()='All Folders']/ul/li[text()='"+stdPath+"']/input", "parent folder radio button",action.BOOLEAN,30), "parent folder radio button")) {
											appLog.info("now parent folder standard radio button is deselected");
										}else {
											appLog.error("parent folder standard radio button is selected but it should not be");
											sa.assertTrue(false, "parent folder standard radio button is selected but it should not be");
										}
										if (click(driver, fp.getFileDistBackBtn(30), "file dist back button", action.BOOLEAN)) {
										}	
									}
									else {
										appLog.error("next button is not clickable");
										sa.assertTrue(false, "next button is not clickable");
									}
								}
								else {
									appLog.error("radio button for standard parent folder is not clickable");
									sa.assertTrue(false, "radio button for standard parent folder is not clickable");
								}
							}
							else {
								appLog.error("could not drag and drop file");
								sa.assertTrue(false, "could not drag and drop file");
							}
							
							if (fp.dragDropFiles(folderpath2, dropImage)) {
								ThreadSleep(3000);
								if (fp.getFileDistributorDocInfo(30).getText().trim().contains(FundsPageErrorMessage.fileDistributorDocInfo)) {
									appLog.info("document info text is successfully verified");
								}
								else {
									appLog.error("document info text is incorrect");
									sa.assertTrue(false, "document info text is incorrect");
								}
								if (fp.clickUsingCssSelectorPath("div#BulkDocument_step_1of2 a[title=Next]", "next button")) {
									
								//if (click(driver, fp.getFileDistNextBtn(30), "file distributor next button", action.SCROLLANDBOOLEAN)) {
									ThreadSleep(5000);
									if (isAlertPresent(driver)) {
										String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
										switchToAlertAndAcceptOrDecline(driver, 10, action.ACCEPT);
										if (msg.contains(FundsPageErrorMessage.pleaseSelectFolder)) {
											appLog.info("please Select Folder error message is successfully verified");
										}
										else {
											appLog.error("please Select Folder error alert message is wrong");
											sa.assertTrue(false, "please Select Folder error alert message is wrong");
										}
									}
									if (click(driver, FindElement(driver, "//li[text()='All Folders']/ul/li[text()='"+stdPath+"']/input", "parent folder radio button", action.BOOLEAN, 10), "parent folder radio button", action.BOOLEAN)) {
										if (click(driver, fp.getFileDistNextBtn(30), "file dist next button", action.BOOLEAN)) {
											//verify UI of file distributor window
											if (fp.getFileDist2Of2(30)!=null) {
												appLog.info("heading file distributor 2 of 2 is successfully displayed");
											}
											else {
												appLog.error("heading file distributor 2 of 2 is not visible");
												sa.assertTrue(false, "heading file distributor 2 of 2 is not visible");
											}
											if (isDisplayed(driver, FindElement(driver, "//div[@id='BulkDocument_step_2of2']//div[@class='yellow_txt_box']","file distributor yellow text", action.BOOLEAN, 30), "visbility", 10,"file distributor yellow text" ).getText().trim().contains(FundsPageErrorMessage.fileDistributionUploadprocesswillallowyoutosplit)) {
												appLog.info("correct msg info is being displayed");
											}
											else {
												appLog.error("msg info is not correct on upload window");
												sa.assertTrue(false, "msg info is not correct on upload window");
											}
											if (fp.getFileDist2Of2Info(30).getText().trim().contains(FundsPageErrorMessage.bulkUploadProcessCompleted1+"1"+FundsPageErrorMessage.bulkUploadProcessCompleted2)) {
												appLog.info("correct msg info is being displayed");
											}
											else {
												appLog.error("msg info is not visible on upload window");
												sa.assertTrue(false, "msg info is not visible on upload window");
											}
											if (fp.getSuccessfullyAppliedOnFileDistributor(30).getText().trim().contains(FundsPageErrorMessage.successfullyAppliedFileDistributor)) {
												appLog.info("successfully applied column text is successfully verified");
											}
											else {
												appLog.error("successfully applied column text is incorrect on webpage");
												sa.assertTrue(false, "successfully applied column text is incorrect on webpage");
											}
											List<WebElement> issueUI = fp.getIssueUiOnFileDistributor(30, true);
											if (!issueUI.isEmpty()) {
											if (issueUI.get(0).getText().trim().contains(3+FundsPageErrorMessage.issueUiFileDistributor)) {
												appLog.info("no of investors is correct on ui of file distributor");
											}
											else {
												appLog.error("no of investor is wrong on ui of file distributors");
												sa.assertTrue(false, "no of investor is wrong on ui of file distributors");
											}
											
											if ((issueUI.get(0).getText().trim().contains(M16Institution1)) &&(issueUI.get(0).getText().trim().contains(M16Institution2))&& (issueUI.get(0).getText().trim().contains(M16Institution3))) {
												appLog.info("all institutions name are correct on issue ui of file distributor");
											}
											else {
												appLog.error("all institutions name are not present on issue ui of file distributor");
												sa.assertTrue(false, "all institutions name are not present on issue ui of file distributor");
											}}
											if (fp.getFileDist2Of2BackBtn(30)!=null) {
												appLog.info("file dist 2 of 2 back button is successfully found");
											}
											else {
												appLog.error("file dist 2 of 2 back button is not visible");
												sa.assertTrue(false, "file dist 2 of 2 back button is not visible");
											}
											if (fp.getFileDist2Of2CancelBtn(30)!=null) {
												appLog.info("cancel button is successfully displayed");
											}
											else {
												appLog.error("cancel button is not visible on file dist window");
												sa.assertTrue(false, "cancel button is not visible on file dist window");
											}
											if (fp.getFileDist2Of2ApplyBtn(30)!=null) {
												appLog.info("apply button is successfully displayed on file dist 2 of 2 window");
											}
											else {
												appLog.error("apply button is not visible on file dist 2 of 2 window");
												sa.assertTrue(false, "apply button is not visible on file dist 2 of 2 window");
											}
											if (fp.clickUsingCssSelectorPath("#lnlbuttonApply", "apply")) {
											//if (click(driver, fp.getFileDist2Of2ApplyBtn(30), "apply btn file distribution", action.BOOLEAN)) {
												ThreadSleep(3000);
												if (isAlertPresent(driver)) {
													String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
													switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
													if (msg.trim().contains(FundsPageErrorMessage.noInvestorReceivedFiles)) {
														appLog.info("no investor received files is successfully displayed");
													}
													else {
														appLog.error("error alert for no investor received files is not displayed");
														sa.assertTrue(false, "error alert for no investor received files is not displayed");
													}
												}
												else {
													appLog.error("no alert is present");
													sa.assertTrue(false, "no alert is present");
												}
											}
											else {
												appLog.error("apply button is not clickable");
												sa.assertTrue(false, "apply button is not clickable");
												driver.close();
											}
										}
										else {
											appLog.error("next button is not clickable");
											sa.assertTrue(false, "next button is not clickable");
											driver.close();
										}
									}
									else {
										appLog.error("cannot click on std path radio button");
										sa.assertTrue(false, "cannot click on std path radio button");
										driver.close();
									}
								}
								else {
									appLog.error("next button of file dist is not clickable");
									sa.assertTrue(false, "next button of file dist is not clickable");
								}
							}
							else {
								appLog.error("could not drag and drop files");
								sa.assertTrue(false, "could not drag and drop files");
							}
							driver.switchTo().window(parentID);
							switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 10));
						}
						else {
							appLog.error("child window not found");
							sa.assertTrue(false, "child window not found");
						}
					}
					else {
						appLog.error("uplaod icon is not clickable");
						sa.assertTrue(false, "uplaod icon is not clickable");
					}
					//verify no file is present in any inst std folder
					if (click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30), "refresh icon", action.BOOLEAN)) {
						if (FindElement(driver, "//span[@id='myGridfundr-cell-0-0']//span", "no data to display", action.BOOLEAN, 30).getText().trim().contains(FundsPageErrorMessage.SelectFolderTemplateNoDataMessage)) {
							appLog.info("no file is present in workspace of isnt 1 folder");
						}
						else {
							appLog.error("no data to display is not found on workspace");
							sa.assertTrue(false, "no data to display is not found on workspace");
						}
					}
					else {
						appLog.error("content grid refresh button is not clickable");
						sa.assertTrue(false, "content grid refresh button is not clickable");
					}
				}
				else {
					appLog.error("standard path is not found in folder structure");
					sa.assertTrue(false, "standard path is not found in folder structure");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("fund name not found on funds page");
				sa.assertTrue(false, "fund name not found on funds page");
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
	public void M16tc009_VerifyResolveIssuePopup() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String fileName = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
		String pathToTraverse = null;
		String firstWindow = null,secondWindow = null,thirdWindow = null;
		Set<String> windows = new HashSet<String>();
		String dropImage="BulkUpload.jpg";
		String folderpath1 = "UploadFiles\\Module16\\Testing";
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M16FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, M16Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					if (click(driver, fp.getUploadIcon(Workspace.FundraisingWorkspace, 30), "upload icon fundraising workspace", action.SCROLLANDBOOLEAN)) {
						firstWindow = switchOnWindow(driver);
						if (firstWindow!=null) {
							if (click(driver, fp.getBulkUploaderOrFileSplitterRadioButton(30), "radio button for bulk upload", action.BOOLEAN)) {
								if (fp.dragDropFiles(folderpath1, dropImage)) {
									ThreadSleep(5000);
									secondWindow = driver.getWindowHandle();
									if (click(driver, FindElement(driver, "//li[text()='All Folders']/ul/li[text()='"+stdPath+"']/input", "parent folder radio button", action.BOOLEAN, 10), "parent folder radio button", action.BOOLEAN)) {
										if (click(driver, fp.getFileDistNextBtn(30), "next button file dist", action.SCROLLANDBOOLEAN)) {
											if (click(driver, fp.getResolveIssuesFileDist2Of2(30), "resolve issues button on file dist 2 of 2", action.BOOLEAN)) {
												if (isDisplayed(driver, FindElement(driver, "//div[@id='BulkDocument_step_2bof2b']//div[@class='yellow_txt_box']", "to resolve the issues select folder(yellow text box)", action.BOOLEAN, 30),"visibility", 30, "to resolve the issues select folder(yellow text box)").getText().trim().contains(FundsPageErrorMessage.resolveIssueFileDistri2bOf2)) {
													appLog.info("yellow text box text is successfully verified");
												}
												else {
													appLog.error("yellow text box text is not verified for file distributor 2b of 2");
													sa.assertTrue(false, "yellow text box text is not verified for file distributor 2b of 2");
												}
												if (FindElements(driver, "//div[@id='BulkDocument_step_2bof2b']//div[@class='head_popup_inner']", "list of columns on resolve issue window").get(0).getText().trim().equals("Unassigned Files")) {
													appLog.info("unassigned files are successfully displayed on resolve issue window");
												}
												else {
													appLog.error("unassigned files could not be displayed on resolve issue window");
													sa.assertTrue(false, "unassigned files could not be displayed on resolve issue window");
												}
												if (FindElements(driver, "//div[@id='BulkDocument_step_2bof2b']//div[@class='head_popup_inner']", "list of columns on resolve issue window").get(1).getText().trim().equals("Investor Folders")) {
													appLog.info("investor folders column is successfully displayed");
												}
												else {
													appLog.error("investor folders column is not displayed");
													sa.assertTrue(false, "investor folders column is not displayed");
												}
												if (fp.getUnassignedFileNamesFileDist2bOf2(30).getText().trim().contains(fileName)) {
													appLog.info(fileName+" was successfully found on unassigned files column");
												}
												else {
													appLog.error(fileName+" could not be found on unassigned files column");
													sa.assertTrue(false, fileName+" could not be found on unassigned files column");
												}
												
												if (click(driver, fp.getViewLinkForUnassignedFileName(fileName), "view link", action.BOOLEAN)) {
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
													
													if(thirdWindow!=null){
														if (fp.getViewFileOnfileDistDownload(60) != null) {
															appLog.info("Download Button is displaying");
														} else {
															appLog.error("Document Download Button is not displaying");
															sa.assertTrue(false, "Document Download Button is not displaying");
														}
														if (fp.getViewFileOnFileDistClose(60) != null) {
															appLog.info("Close Button is displaying");
														} else {
															appLog.error("Close Button is not displaying");
															sa.assertTrue(false, "Close Button is not displaying");
														}
														driver.close();
														driver.switchTo().window(secondWindow);
													}
													
													if (click(driver, fp.getSelectFolderLinkForUnassignedFileName(fileName), "select folder link", action.BOOLEAN)) {
														if (fp.clickonFolderStructureFileDist(M16Institution1, stdPath)){
															appLog.info("successfully clicked on "+stdPath+" folder of "+M16Institution1);
														}
														else {
															appLog.error("could not find path of std folder in "+M16Institution1);
															sa.assertTrue(false, "could not find path of std folder in "+M16Institution1);
														}
														if (fp.clickonFolderStructureFileDist(M16Institution2, stdPath)){
															appLog.info("successfully clicked on "+stdPath+" folder of "+M16Institution2);
														}
														else {
															appLog.error("could not find path of std folder in "+M16Institution2);
															sa.assertTrue(false, "could not find path of std folder in "+M16Institution2);
														}
														if (fp.clickonFolderStructureFileDist(M16Institution3, stdPath)){
															appLog.info("successfully clicked on "+stdPath+" folder of "+M16Institution3);
														}
														else {
															appLog.error("could not find path of std folder in "+M16Institution3);
															sa.assertTrue(false, "could not find path of std folder in "+M16Institution3);
														}
														if (click(driver, fp.getBackButtonFileDist2bOf2(30), "back button", action.BOOLEAN)) {
															if (fp.getFileDist2Of2(30)!=null) {
																appLog.info("file dist 2 of 2 is successfully displayed");
															}
															else {
																appLog.error("file dist 2 of 2 window is not present");
																sa.assertTrue(false, "file dist 2 of 2 window is not present");
															}
															if (click(driver, fp.getResolveIssuesFileDist2Of2(30), "resolve issues button", action.BOOLEAN)) {
																if (isDisplayed(driver, FindElement(driver, "//div[@id='BulkDocument_step_2bof2b']//div[@class='yellow_txt_box']", "to resolve the issues select folder(yellow text box)", action.BOOLEAN, 30),"visibility", 30, "to resolve the issues select folder(yellow text box)").getText().trim().contains(FundsPageErrorMessage.resolveIssueFileDistri2bOf2)) {
																	appLog.info("yellow text box text is successfully verified");
																}
																else {
																	appLog.error("yellow text box text is not verified for file distributor 2b of 2");
																	sa.assertTrue(false, "yellow text box text is not verified for file distributor 2b of 2");
																}
																click(driver, fp.getFileDist2bOf2CancelBtn(30), "cancel button file dist 2b of 2", action.SCROLLANDBOOLEAN);
																ThreadSleep(5000);
															}
															else {
																appLog.error("resolve issues button is not clickable");
																sa.assertTrue(false, "resolve issues button is not clickable");
																driver.close();
															}
														}
														else {
															appLog.error("back button 2bof 2 is not clickable");
															sa.assertTrue(false, "back button 2bof 2 is not clickable");
														}
													}
													else {
														appLog.error("cannot click on select folder link");
														sa.assertTrue(false, "cannot click on select folder link");
													}
												}
												else {
													appLog.error("cannot click on view link");
													sa.assertTrue(false, "cannot click on view link");
												}
											}
											else {
												appLog.error("resolve issues button is not clickable");
												sa.assertTrue(false, "resolve issues button is not clickable");
											}
										}
										else {
											appLog.error("next button is not clickable");
											sa.assertTrue(false, "next button is not clickable");
										}
									}
									else {
										appLog.error("std folder radio button is not clickable");
										sa.assertTrue(false, "std folder radio button is not clickable");
									}
								}
								else {
									appLog.error("could not drag and drop file");
									sa.assertTrue(false, "could not drag and drop file");
								}
							}
							driver.switchTo().window(firstWindow);
							switchToFrame(driver, 30, fp.getFrame(environment,mode, PageName.FundsPage, 30));
						}
						else {
							appLog.error("could not switch to window");
							sa.assertTrue(false, "could not switch to window");
						}
					}
					else {
						appLog.error("upload icon is not clickable");
						sa.assertTrue(false, "upload icon is not clickable");
					}
				}
				else {
					appLog.error("std path is not found in foldr structure");
					sa.assertTrue(false, "std path is not found in foldr structure");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M16FundName1+" is not found on funds page");
				sa.assertTrue(false, M16FundName1+" is not found on funds page");
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
	public void M16tc010_VerifyBulkUploadSuggestedNaming() {
		
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		String dropImage="BulkUpload.jpg";
		String folderpath1 = "UploadFiles\\Module16\\Bulk_File_Upload_1";
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String files[] = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.KeyWord_For_Search).split("<break>");
		String firstWindow = null,secondWindow = null;
		String date = getSystemDate("MM/dd/yyyy")+previousOrForwardDate(-1, "MM/dd/yyyy");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M16FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, M16Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					if (click(driver, fp.getUploadIcon(Workspace.FundraisingWorkspace, 30), "upload icon fundraising workspace", action.SCROLLANDBOOLEAN)) {
						firstWindow = switchOnWindow(driver);
						if (firstWindow!=null) {
							if (click(driver, fp.getBulkUploaderOrFileSplitterRadioButton(30), "radio button for bulk upload", action.BOOLEAN)) {
								if (fp.dragDropFiles(folderpath1, dropImage)) {
									ThreadSleep(5000);
									secondWindow = driver.getWindowHandle();
									if (click(driver, FindElement(driver, "//li[text()='All Folders']/ul/li[text()='"+stdPath+"']/input", "parent folder radio button", action.BOOLEAN, 10), "parent folder radio button", action.BOOLEAN)) {
										if (click(driver, fp.getFileDistNextBtn(30), "next button file dist", action.SCROLLANDBOOLEAN)) {
										if (fp.getAllFilesInSuccessFullyAppliedFileDist2Of2().size() == 4) {
											appLog.info("4 files are verified successfully");
										}
										else {
											appLog.error("no of files is wrong");
										}
										if (fp.getFileDist2Of2(30)!=null) {
											appLog.info("file dist 2 of 2 head is successfully displayed");
										}
										else {
											appLog.error("file dist 2 of 2 head is not displayed");
											sa.assertTrue(false, "file dist 2 of 2 head is not displayed");
										}
										for (int i = 0;i<2;i++)
										{
											saa = fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16Institution1, files[i], false, false, firstWindow, secondWindow, null);
											saa.combineAssertions(sa);
										}
										for (int i = 0;i<2;i++)
										{
											saa = fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16Institution2, files[i], false, false, firstWindow, secondWindow, null);
											saa.combineAssertions(sa);
										}
										//verifying issue ui
										
											List<WebElement> issueUI= fp.getIssueUiOnFileDistributor(30, true);
										if (!issueUI.isEmpty()) {
											if (getText(driver, issueUI.get(0), "issue ui", action.BOOLEAN).trim().contains("1"+FundsPageErrorMessage.issueUiFileDistributor)) {
												appLog.info("successfully verified issue UI of file distributor");
											}
											else {
												appLog.error("could not verify issue UI of file dist");
												sa.assertTrue(false, "could not verify issue UI of file dist");
											}
											System.err.println("content of "+files[0]);
											if (fp.findFileOnIssueUIFileDistributor(files[0],M16Institution3 ,true)!=null) {
											appLog.info("successfully found 1st file for issues");
											}
											else {
												appLog.error("could not find 1st file in issue window");
												sa.assertTrue(false, "could not find 1st file in issue window");
											}
											if (fp.findFileOnIssueUIFileDistributor(files[1], M16Institution3,true)!=null)
											appLog.info("successfully found 2nd file for issues");
											}
											else {
												appLog.error("could not find 2nd file in issue window");
												sa.assertTrue(false, "could not find 2nd file in issue window");
											}
										}
										
										//click on view link
										saa = fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16Institution2, files[1], true, false, firstWindow, secondWindow, null);
										saa.combineAssertions(sa);
										if (click(driver,fp.findFileOnIssueUIFileDistributor(files[1], M16Institution3,true) , "file of "+M16Institution3+" "+files[1], action.BOOLEAN)) {
											appLog.info("lolol");
											ThreadSleep(4000);
											Set<String> windows = driver.getWindowHandles();
											if (windows!=null) {
												for (String temp_window:windows) {
													if (!temp_window.equalsIgnoreCase(firstWindow)) {
														if (!temp_window.equalsIgnoreCase(secondWindow)) {
															driver.switchTo().window(temp_window);
															break;
														}
													}
												}
											}
											if (fp.verifyDownloadFunctionalityFileDistributorWithoutClick()) {
												appLog.info("successfully verified download and close button of file");
											}
											else {
												appLog.error("could not verify download and close button of file");
												sa.assertTrue(false, "could not verify download and close button of file");
											}
											driver.close();
											driver.switchTo().window(secondWindow);
										}
										appLog.info("clicking on resolve issue button");
										if (click(driver, fp.getResolveIssuesFileDist2Of2(30), "resolve issues link", action.BOOLEAN)) {
											if (click(driver, fp.getSelectFolderLinkForUnassignedFileName(fp.reverseFileNameForFileDistributor(files[0], M16Institution3)), "Select Folder Link", action.BOOLEAN)) {
												if (fp.clickonFolderStructureFileDist(M16Institution3, stdPath)) {
													appLog.info("successfully select std folder for "+M16Institution3);
												}
												else {
													appLog.error("could not select std folder of "+M16Institution3);
													sa.assertTrue(false, "could not select std folder of "+M16Institution3);
												}
												
											}
											else {
												appLog.error("select folder link is not clickable");
												sa.assertTrue(false, "select folder link is not clickable");
											}
											if (click(driver, fp.getSelectFolderLinkForUnassignedFileName(fp.reverseFileNameForFileDistributor(files[1], M16Institution3)), "Select Folder Link", action.BOOLEAN)) {
												appLog.info("successfully clicked on select folder link of "+files[1]);
												if (fp.clickonFolderStructureFileDist(M16Institution3, stdPath)) {
													appLog.info("successfully clicked on std folder of "+M16Institution3);
												}
												else {
													appLog.error("could not click on std folder of "+M16Institution3);
													sa.assertTrue(false, "could not click on std folder of "+M16Institution3);
												}
												
											}
											else {
												appLog.error("could not click select folder link of "+files[1]);
												sa.assertTrue(false, "could not click select folder link of "+files[1]);
											}
											ThreadSleep(7000);	
											if (click(driver, fp.getApplyButtonFileDist2bOf2(30), "apply button", action.BOOLEAN)) {
													//check issue column
												ThreadSleep(7000);	
												List<WebElement> issueUI = fp.getIssueUiOnFileDistributor(30, false);
												if (!issueUI.isEmpty()) {
													if (getText(driver,issueUI.get(0) , "issue ui no of isues", action.BOOLEAN).trim().contains(FundsPageErrorMessage.noIssueFile)) {
														appLog.info("no issues found is successfully verified");
													}
													else {
														appLog.error("no issue found is not present on file dist 2b of 2");
														sa.assertTrue(false, "no issue found is not present on file dist 2b of 2");
													}
												}
													for (int i = 0;i<2;i++)
													{
														saa = fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16Institution1, files[i], false, false, null, null, null);
														saa.combineAssertions(sa);
													}
													for (int i = 0;i<2;i++)
													{
														saa = fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16Institution2, files[i], false, false, null, null, null);
														saa.combineAssertions(sa);
													}
													saa = fp.verifyFileDist2Of2SuccessfullyAppliedUIReverse(M16Institution3, files[1], false, false, null, null);
													sa.combineAssertions(saa);
													
													//clicking on remove link and reject
													saa = fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16Institution1, files[0], false, true, null, null, action.DECLINE);
													sa.combineAssertions(saa);
													//clicking on remove link and accept
													saa = fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16Institution1, files[0], false, true, null, null, action.ACCEPT);
													sa.combineAssertions(saa);
													ThreadSleep(4000);
													if (fp.clickUsingCssSelectorPath("#lnlbuttonApply", "apply")) {
													//if (click(driver, fp.getFileDist2Of2ApplyBtn(30), "apply button", action.BOOLEAN)) {
														if (isAlertPresent(driver)) {
															String msg = switchToAlertAndGetMessage(driver, 10, action.GETTEXT);
															switchToAlertAndAcceptOrDecline(driver, 10, action.ACCEPT);
															if (msg.trim().equals(FundsPageErrorMessage.DocumentUploadSuccessMsg)) {
																appLog.info("successfully verified document upload success message");
															}
															else {
																appLog.error("document upload message could not be verified");
																sa.assertTrue(false, "document upload message could not be verified");
															}
														}
														else {
															appLog.error("could not find alert after clicking apply button");
															sa.assertTrue(false, "could not find alert after clicking apply button");
														}
										
													}
													else {
														appLog.error("apply button is not clickable");
														sa.assertTrue(false, "apply button is not clickable");
													}
												}
												else {
													appLog.error("apply button is not clickable");
													sa.assertTrue(false, "apply button is not clickable");
												}
										
										}
										else {
											appLog.error("resovle issues link is not clickable");
											sa.assertTrue(false, "resovle issues link is not clickable");
										}
									}
									else {
										appLog.error("std path folder radio button is not clickable");
										sa.assertTrue(false, "std path folder radio button is not clickable");
									}
								}
								else {
									appLog.error("could not drag and drop files for upload");
									sa.assertTrue(false, "could not drag and drop files for upload");
								}
							}
							else {
								appLog.error("could not find bulk upload radio button on upload window");
								sa.assertTrue(false, "could not find bulk upload radio button on upload window");
							}
							driver.switchTo().window(firstWindow);
							switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
						}
						if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN)) {
							if (selectVisibleTextFromDropDown(driver, fp.getManageApprovalsDropdown(ManageApprovalTabs.PendingDocuments, 10), "All Pending Documents", "All Pending Documents")) {
								appLog.info("selected all pending docs from manage approvals dropdown");
							}
							else {
								appLog.error("manage approvals dropdown does not have all pending documents");
								sa.assertTrue(false, "manage approvals dropdown does not have all pending documents");
							}
							for (int i = 0;i<2;i++) {
								if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments, M16Institution2+"_"+files[i], M16FundName1+" > "+M16Institution2+" > "+stdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
									appLog.info("verified file for "+files[i]+" for "+M16Institution2);
								}
								else {
									appLog.error("could not verify file "+files[i]+" for "+M16Institution2);
									sa.assertTrue(false, "could not verify file "+files[i]+" for "+M16Institution2);
								}
								if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments, fp.reverseFileNameForFileDistributor(files[i], M16Institution3), M16FundName1+" > "+M16Institution3+" > "+stdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
									appLog.info("verified file for "+files[i]+" for "+M16Institution3);
								}
								else {
									appLog.error("could not verify "+files[i]+" for "+M16Institution3);
									sa.assertTrue(false, "could not verify "+files[i]+" for "+M16Institution3);
								}
							}
							if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments, M16Institution1+"_"+files[1], M16FundName1+" > "+M16Institution1+" > "+stdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
								appLog.info("verified file for "+files[1]+" for "+M16Institution1);
							}
							else {
								appLog.error("could not verify file "+files[1]+" for "+M16Institution1);
								sa.assertTrue(false, "could not verify file "+files[1]+" for "+M16Institution1);
							}
							
						}
						else {
							appLog.error("manage approval icon is not clickable");
							sa.assertTrue(false, "manage approval icon is not clickable");
						}
					}
					else {
						appLog.error("upload icon is not clickable");
						sa.assertTrue(false, "upload icon is not clickable");
					}
				}
				else {
					appLog.error("std path is not found in folder structure");
					sa.assertTrue(false, "std path is not found in folder structure");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("could not find fund of "+M16FundName1);
				sa.assertTrue(false, "could not find fund of "+M16FundName1);
			}
		}
		lp.CRMlogout();
		sa.assertAll();
									
	}

	@Test
	public void M16tc011_ApproveDocument_Action() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String files[] = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M16tc010_VerifyBulkUploadSuggestedNaming", excelLabel.KeyWord_For_Search).split("<break>");
		
		String date = getSystemDate("MM/dd/yyyy")+previousOrForwardDate(-1, "MM/dd/yyyy");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M16FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30),"manage approval icon", action.SCROLLANDBOOLEAN)) {
					if (selectVisibleTextFromDropDown(driver, fp.getManageApprovalsDropdown(ManageApprovalTabs.PendingDocuments, 30), "All Pending Documents", "All Pending Documents")) {
						if (click(driver, fp.getManageApprovalsAllDocumentSelectCheckBox(30),"all documents checkbox", action.BOOLEAN)) {
							if (click(driver, fp.getApproveBtnManageApprovals(30), "approve button manage approvals", action.SCROLLANDBOOLEAN)) {
								if (click(driver, fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30), "approve yes button", action.BOOLEAN))
									if (click(driver, fp.getManageApprovalsApproveConfirmationCrossIcon(30), "cross icon", action.BOOLEAN)) {
										if (click(driver, fp.getCrossIconManageApp(30), "cross button manage approvals", action.SCROLLANDBOOLEAN)) {

										}
										else {
											appLog.error("approve cross icon is not clickable");
											sa.assertTrue(false, "approve cross icon is not clickable");
										}
									}
									else {
										appLog.error("manage aproval confirmation cross icon is not clickable");
										sa.assertTrue(false, "manage aproval confirmation cross icon is not clickable");
									}
							}
							else {
								appLog.error("approve button is not clickable");
								sa.assertTrue(false, "approve button is not clickable");
							}
						}
						else {
							appLog.error("all documents checkbox is not clickable");
							sa.assertTrue(false, "all documents checkbox is not clickable");
						}
					}
					else {
						appLog.error("all pending documents is not present");
						sa.assertTrue(false, "all pending documents is not present");
					}
				}
				else {
					appLog.error("manage approval icon is not clickable");
					sa.assertTrue(false, "manage approval icon is not clickable");
				}
				if (fp.verifyFolderPathdummy(stdPath, M16Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.FundraisingWorkspace, M16Institution1+"_"+files[1])) {
					appLog.info("successfully verified file on "+files[1]+" on "+M16Institution1);	
					}
					else {
						appLog.error("could not verify "+files[1]+" on "+M16Institution1);
						sa.assertTrue(false, "could not verify "+files[1]+" on "+M16Institution1);
					}
					if (fp.verifyDownloadFunctionality(PageName.FundsPage, Workspace.FundraisingWorkspace,M16Institution1+"_"+files[1] , true,true,false)) {
						
					}
					else {
						appLog.error("could not verify download functionality");
						sa.assertTrue(false, "could not verify download functionality");
					}
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				}
				else {
					appLog.error("std path is not found on "+M16Institution1);
					sa.assertTrue(false, "std path is not found on "+M16Institution1);
				}
				if (fp.verifyFolderPathdummy(stdPath, M16Institution2, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.FundraisingWorkspace, M16Institution2+"_"+files[0])) {
						
					}
					else {
						appLog.error("could not verify "+files[0]+" on "+M16Institution2);
						sa.assertTrue(false, "could not verify "+files[0]+" on "+M16Institution2);
					}
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.FundraisingWorkspace, M16Institution2+"_"+files[1])) {
						
					}
					else {
						appLog.error("could not find "+files[1]+" on "+M16Institution2);
						sa.assertTrue(false, "could not find "+files[1]+" on "+M16Institution2);
					}
				}	
				if (fp.verifyFolderPathdummy(stdPath, M16Institution3, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.FundraisingWorkspace, fp.reverseFileNameForFileDistributor(files[0], M16Institution3))) {
						appLog.info("successfully verified "+files[0]+" on "+M16Institution3);
						
					}
					else {
						appLog.error("could not verify "+files[0]+" on "+M16Institution3);
						sa.assertTrue(false, "could not verify "+files[0]+" on "+M16Institution3);
					}
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.FundraisingWorkspace, fp.reverseFileNameForFileDistributor(files[1], M16Institution3))) {
						
					}
					else {
						appLog.error("could not verify "+files[1]+" on "+M16Institution3);
						sa.assertTrue(false, "could not verify "+files[1]+" on "+M16Institution3);
					}
				}
				else {
					appLog.error("std folder is not present in folder structure");
					sa.assertTrue(false, "std folder is not present in folder structure");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M16FundName1+" is not found on funds page");
				sa.assertTrue(false, M16FundName1+" is not found on funds page");
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
	public void M16tc011_ApproveDocument_Impact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		AllFirmsPageBusinesslayer afp = new AllFirmsPageBusinesslayer(driver);
		SoftAssert saa = new SoftAssert();
		List<String> fail_list = new ArrayList<String>();
		
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String files[] = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M16tc010_VerifyBulkUploadSuggestedNaming", excelLabel.KeyWord_For_Search).split("<break>");
		lp.investorLogin(M16Contact1EmailId, adminPassword);
		String date = getSystemDate("MM/dd/yyyy")+previousOrForwardDate(-1, "MM/dd/yyyy");
		if (click(driver, ifp.getRecentActivitiesTab(30), "recent activities tab", action.BOOLEAN)) {
			click(driver, ifp.getActivitiesCreatedOnLabel(30), "activity date", action.BOOLEAN);
			click(driver, ifp.getActivitiesCreatedOnLabel(30), "activity date", action.BOOLEAN);
			fail_list=ifp.verifyAlertOnRecentAllDocumentGrid(M16Institution1+"_"+files[1]+"<break>"+M16Institution2+"_"+files[0]+"<break>"+M16Institution2+"_"+files[1]+"<break>"+ fp.reverseFileNameForFileDistributor(files[0], M16Institution3)+"<break>"+ fp.reverseFileNameForFileDistributor(files[1], M16Institution3), M16FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities, activityType.DocumentUpload, PageName.AllFirmsPage);
			if (fail_list.isEmpty()) {
				appLog.info("successfully verified all allerts on recent activities");
			}
			else {
				for (int i = 0;i<fail_list.size();i++) {
					appLog.error(fail_list.get(i)+" is not present on recent docs grid");
				}
				sa.assertTrue(false, "could not verify docs on recent activities grid");
			}
		}
		else {
			appLog.error("RecentActivities tab is not clickable");
			sa.assertTrue(false, "RecentActivities tab is not clickable");
		}
		
		
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			if (fp.verifyFolderPathdummy(stdPath, M16Institution1, null, null, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)) {
				saa=ifp.verifyContentGridInvestorSide(driver, PageName.FundsPage, M16Institution1+"_"+files[1], CRMUser1FirstName+" "+CRMUser1LastName,date);
				sa.combineAssertions(saa);
				
			}
			else {
				appLog.error("cannot find "+stdPath+" on folder structure");
				sa.assertTrue(false, "cannot find "+stdPath+" on folder structure");
			}
				
			if (fp.verifyFolderPathdummy(stdPath, M16Institution2, null, null, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)) {
				saa=ifp.verifyContentGridInvestorSide(driver, PageName.FundsPage, M16Institution2+"_"+files[0], CRMUser1FirstName+" "+CRMUser1LastName, date);
				sa.combineAssertions(saa);
				saa=ifp.verifyContentGridInvestorSide(driver, PageName.FundsPage, M16Institution2+"_"+files[1], CRMUser1FirstName+" "+CRMUser1LastName, date);
				sa.combineAssertions(saa);
				
			}
			else {
				appLog.error("cannot find "+stdPath+" on folder structure");
				sa.assertTrue(false, "cannot find "+stdPath+" on folder structure");
			}
			if (fp.verifyFolderPathdummy(stdPath, M16Institution3, null, null, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)) {
				
			saa=ifp.verifyContentGridInvestorSide(driver, PageName.FundsPage, fp.reverseFileNameForFileDistributor(files[0], M16Institution3), CRMUser1FirstName+" "+CRMUser1LastName, date);
			sa.combineAssertions(saa);
			saa=ifp.verifyContentGridInvestorSide(driver, PageName.FundsPage, fp.reverseFileNameForFileDistributor(files[1], M16Institution3), CRMUser1FirstName+" "+CRMUser1LastName, date);	
			sa.combineAssertions(saa);
			}
			else {
				appLog.error("std folder is not present in folder structure");
				sa.assertTrue(false, "std folder is not present in folder structure");
			}
			if (selectVisibleTextFromDropDown(driver, ifp.getFirmNameDropdownWRTPage(PageName.InvestorFirmPage), "All Firms", "All Firms")) {
				ThreadSleep(5000);
				fail_list=afp.verifyAlertsOnAllFirmsPage(M16Institution2+"_"+files[0]+"<break>"+M16Institution2+"_"+files[1]+"<break>"+fp.reverseFileNameForFileDistributor(files[0], M16Institution3)+"<break>"+fp.reverseFileNameForFileDistributor(files[1], M16Institution3)+"<break>"+M16Institution1+"_"+files[1], Org1FirmName, M16FundName1, investorSideWorkSpace.PotentialInvestment, activityType.DocumentUpload, PageName.AllFirmsPage); 				
				if (fail_list.isEmpty()) {
					appLog.info("successfully verified alerts on all firms page");
				}
				else {
					for (int i = 0;i<fail_list.size();i++) {
						appLog.error("content of not verified alerts is "+fail_list.get(i));
					}
					sa.assertTrue(false, "could not verify alerts on all firms page");
				}
				if (ifp.verifyDownloadFunctionality(PageName.AllFirmsPage, Workspace.FundraisingWorkspace, M16Institution1+"_"+files[1], true, false, false)) { 
					appLog.info("succcessfully verified download functionality of file from all firms page");
				}
				else {
					appLog.error("download functionality could not be verified");
					sa.assertTrue(false, "download functionality could not be verified");
				}
			}
			else {
				appLog.error("could not find all firms option in dropdown");
				sa.assertTrue(false, "could not find all firms option in dropdown");
			}
		}
		else {
			appLog.error("could not click on potential investment tab");
			sa.assertTrue(false, "could not click on potential investment tab");
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Test
	public void M16tc012_DeactivateManageApprovals() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		PartnershipPageBusinessLayer pp = new PartnershipPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
			if (np.deactivateManageApprovalsSetting()) {
				
			}
			else {
				appLog.error("could not deactivate manage approvals");
				sa.assertTrue(false, "could not deactivate manage approvals");
			}
			switchToDefaultContent(driver);
			
		}
		lp.CRMlogout();
		sa.assertAll();
				
	}

	@Test
	public void M16tc013_VerifyDuplicateDocumentsPopup_Action() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		PartnershipPageBusinessLayer pp = new PartnershipPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);

		String dropImage="BulkUpload.jpg";
		String folderpath1 = "UploadFiles\\Module16\\Bulk_File_Upload_1";
		String folderpath2 = "UploadFiles\\Module16\\Testing";
		String parentID = null;
		List<String> duplicateDocs;
		List<String> folderLoc;
		String files[] = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M16tc010_VerifyBulkUploadSuggestedNaming", excelLabel.KeyWord_For_Search).split("<break>");

		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String date = getSystemDate("MM/dd/yyyy")+previousOrForwardDate(-1, "MM/dd/yyyy");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M16FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, M16Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					if (click(driver, fp.getUploadIcon(Workspace.FundraisingWorkspace, 30), "upload icon", action.BOOLEAN)) {
						parentID = switchOnWindow(driver);
						if (parentID!=null) {
							if (click(driver, fp.getBulkUploaderOrFileSplitterRadioButton(30), "bulk upload or file splitter radio button", action.BOOLEAN)) {
								if (fp.dragDropFiles(folderpath1, dropImage)) {
									ThreadSleep(5000);
									if (click(driver, FindElement(driver, "//li[text()='All Folders']/ul/li[text()='"+stdPath+"']/input", "parent folder radio button", action.BOOLEAN, 10), "parent folder radio button", action.BOOLEAN)) {
										if (click(driver, fp.getFileDistNextBtn(30), "file dist next button", action.BOOLEAN)) {
											ThreadSleep(5000);
											if (click(driver, fp.getResolveIssuesFileDist2Of2(30), "resolve issues button", action.BOOLEAN)) {
												if (click(driver, fp.getSelectFolderLinkForUnassignedFileName(fp.reverseFileNameForFileDistributor(files[0], M16Institution3)), "select folder link", action.BOOLEAN)) {
												}
												fp.clickonFolderStructureFileDist(M16Institution3, stdPath);
												if (click(driver, fp.getApplyButtonFileDist2bOf2(30), "apply button", action.BOOLEAN)) {
													if (click(driver, fp.getFileDist2Of2ApplyBtn(30), "apply button", action.BOOLEAN)) {
														List<String> a = fp.verifyDuplicateDocWindowWithoutUpload(Workspace.FundraisingWorkspace, 
																M16Institution1+"_"+files[1]+"<break>"+M16Institution2+"_"+files[0]+"<break>"+
																		M16Institution2+"_"+files[1]+"<break>"+fp.reverseFileNameForFileDistributor(files[0], M16Institution3), 
																		M16FundName1+" > "+M16Institution1+" > "+stdPath+"<break>"+
																				M16FundName1+" > "+M16Institution2+" > "+stdPath+"<break>"+
																				M16FundName1+" > "+M16Institution2+" > "+stdPath+"<break>"+
																				M16FundName1+" > "+M16Institution3+" > "+stdPath);
														if (a.isEmpty()) {
															appLog.info("successfully verified documents on duplicate doc window");
														}
														else {
															for (int i =0;i<a.size();i++) {
																appLog.error(a.get(i)+" is not matching with duplicate doc window");
															}
															sa.assertTrue(false, "could not verify duplicate doc window");
														}
													}
													click(driver, fp.getIgnoreAllButton(30), "ignore all button", action.BOOLEAN);

												}
												else {
													appLog.error("apply button for 2b of 2 is not clickable");
													sa.assertTrue(false, "apply button for 2b of 2 is not clickable");
												}
											}
											else {
												appLog.error("resolve issues button is not clickable");
												sa.assertTrue(false, "resolve issues button is not clickable");
											}
										}
										else {
											appLog.error("next button is not clickable");
											sa.assertTrue(false, "next button is not clickable");
										}
									}
									else {
										appLog.error("std radio button is not clickable");
										sa.assertTrue(false, "std radio button is not clickable");
									}
								}
								else {
									appLog.error("drag and drop of file is not possible");
									sa.assertTrue(false, "drag and drop of file is not possible");
								}
							}
							else {
								appLog.error("bulk upload button is not clickable");
								sa.assertTrue(false, "bulk upload button is not clickable");
							}
							driver.switchTo().window(parentID);
							switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));


						}
						else {
							appLog.error("could not find child window to switch");
							sa.assertTrue(false, "could not find child window to switch");
						}
					}
					else {
						appLog.error("upload icon is not clickable");
						sa.assertTrue(false, "upload icon is not clickable");
					}
					if (click(driver, fp.getUploadIcon(Workspace.FundraisingWorkspace, 30), "upload icon", action.BOOLEAN)) {
						parentID = switchOnWindow(driver);
						if (parentID!=null) {

							if (click(driver, fp.getBulkUploaderOrFileSplitterRadioButton(30), "bulk upload or file splitter radio button", action.BOOLEAN)) {
								if (fp.dragDropFiles(folderpath1, dropImage)) {
									ThreadSleep(5000);
									if (click(driver, FindElement(driver, "//li[text()='All Folders']/ul/li[text()='"+stdPath+"']/input", "parent folder radio button", action.BOOLEAN, 10), "parent folder radio button", action.BOOLEAN)) {
										if (click(driver, fp.getFileDistNextBtn(30), "file dist next button", action.BOOLEAN)) {
											ThreadSleep(5000);
											if (click(driver, fp.getResolveIssuesFileDist2Of2(30), "resolve issues button", action.BOOLEAN)) {
												if (click(driver, fp.getSelectFolderLinkForUnassignedFileName(fp.reverseFileNameForFileDistributor(files[0], M16Institution3)), "select folder link", action.BOOLEAN)) {
												}
												fp.clickonFolderStructureFileDist(M16Institution3, stdPath);
												if (click(driver, fp.getApplyButtonFileDist2bOf2(30), "apply button", action.BOOLEAN)) {
													if (click(driver, fp.getFileDist2Of2ApplyBtn(30), "apply button", action.BOOLEAN)) {
													}
													else {
														appLog.error("apply button 2 of 2 is not clickable");
														sa.assertTrue(false, "apply button 2 of 2 is not clickable");
													}
													fp.clickUsingCssSelectorPath("#lnkReplaceAll", "update all");
													//click(driver, fp.getUpdateAllButton(30), "update all button", action.BOOLEAN);
													if (isAlertPresent(driver)) {
														String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
														switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
														if (msg.contains(FundsPageErrorMessage.DocumentUploadSuccessMsg)) {
															appLog.info("document uploaded successfully");
														}
														else {
															appLog.error("could not find document upload success message");
															sa.assertTrue(false, "could not find document upload success message");
														}
													}
													else {
														appLog.error("no alert is present after clicking on update all button");
														sa.assertTrue(false, "no alert is present after clicking on update all button");
													}
												}
												else {
													appLog.error("apply button for 2b of 2 is not clickable");
													sa.assertTrue(false, "apply button for 2b of 2 is not clickable");
												}
											}
											else {
												appLog.error("resolve issues button is not clickable");
												sa.assertTrue(false, "resolve issues button is not clickable");
											}
										}
										else {
											appLog.error("next button is not clickable");
											sa.assertTrue(false, "next button is not clickable");
										}
									}
									else {
										appLog.error("std radio button is not clickable");
										sa.assertTrue(false, "std radio button is not clickable");
									}
								}
								else {
									appLog.error("drag and drop of file is not possible");
									sa.assertTrue(false, "drag and drop of file is not possible");
								}
							}
							else {
								appLog.error("bulk upload button is not clickable");
								sa.assertTrue(false, "bulk upload button is not clickable");
							}
							driver.switchTo().window(parentID);
							switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
						}
						else {
							appLog.error("cannot find child window to switch");
							sa.assertTrue(false, "cannot find child window to switch");
						}
					}
					else {
						appLog.error("upload icon is not clickable");
						sa.assertTrue(false, "upload icon is not clickable");
					}
					if (fp.verifyFolderPathdummy(stdPath, M16Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
						if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.FundraisingWorkspace,M16Institution1+"_"+files[0])) {
							appLog.info("successfully verified "+files[0]+" in institution1");
						}
						else {
							appLog.error("could not verify "+files[0]+" in institution1");
							sa.assertTrue(false, "could not verify "+files[0]+" in institution1");
						}
						if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.FundraisingWorkspace,M16Institution1+"_"+files[1])) {
							appLog.info("successfully verified "+files[0]+" in institution2");
						}
						else {
							appLog.error("could not verify "+files[0]+" in institution2");
							sa.assertTrue(false, "could not verify "+files[0]+" in institution2");
						}
					}
					else {
						appLog.error(stdPath+" is not found in folder structure");
						sa.assertTrue(false, stdPath+" is not found in folder structure");
					}
				}
				else {
					appLog.error("could not find child window to switch");
					sa.assertTrue(false, "could not find child window to switch");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("upload window is not clickable");
				sa.assertTrue(false, "upload window is not clickable");
			}
			lp.CRMlogout();
			sa.assertAll();
		}
	}

	@Test
	public void M16tc013_VerifyDuplicateDocumentsPopup_Impact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		AllFirmsPageBusinesslayer afp = new AllFirmsPageBusinesslayer(driver);
		List<String> fail_list = new ArrayList<String>();
		
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M16tc013_VerifyDuplicateDocumentsPopup_Action", excelLabel.StandardPath);
		String files[] = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M16tc010_VerifyBulkUploadSuggestedNaming", excelLabel.KeyWord_For_Search).split("<break>");
		
		lp.investorLogin(M16Contact1EmailId, adminPassword);
		click(driver, ifp.getRecentActivitiesTab(30), "recent activities tab", action.BOOLEAN);
		fail_list=ifp.verifyAlertOnRecentAllDocumentGrid(M16Institution1+"_"+files[0], M16FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities, activityType.DocumentUpload, PageName.AllFirmsPage);
		if (fail_list.isEmpty()) {
			appLog.info("successfully verified all allerts on recent activities");
		}
		else {
			for (int i = 0;i<fail_list.size();i++) {
				appLog.error(fail_list.get(i)+" is not present on recent docs grid");
			}
			sa.assertTrue(false, "could not verify docs on recent activities grid");
		}
		fail_list=ifp.verifyAlertOnRecentAllDocumentGrid(M16Institution1+"_"+files[1]+"<break>"+M16Institution2+"_"+files[0]+"<break>"+M16Institution2+"_"+files[1]+"<break>"+fp.reverseFileNameForFileDistributor(files[0], M16Institution3), M16FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities, activityType.DocumentUpdate, PageName.AllFirmsPage);
		if (fail_list.isEmpty()) {
			appLog.info("successfully verified all allerts on recent activities");
		}
		else {
			for (int i = 0;i<fail_list.size();i++) {
				appLog.error(fail_list.get(i)+" is not present on recent docs grid");
			}
			sa.assertTrue(false, "could not verify docs on recent activities grid");
		}
		
		lp.investorLogout();
		sa.assertAll();
	}

	@Test
	public void M16tc014_ChangeBulkUploadSettings_Action() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		String parentID = null;
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String files[] = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M16tc010_VerifyBulkUploadSuggestedNaming", excelLabel.KeyWord_For_Search).split("<break>");
		SoftAssert saa = new SoftAssert();
		String folderpath1 = "UploadFiles\\Module16\\Bulk_File_Upload_1";
		String dropImage="BulkUpload.jpg";
		List<String> fail_list = null;

		lp.CRMLogin(superAdminUserName, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, np.getFrame(environment,mode, PageName.NavatarInvestorManager, 30));
			if (np.clickOnSideMenusTab(sideMenu.FileDistributorSettings)) {
				if (click(driver, np.getReverseNamingRadioButton(30), "reverse naming radio button", action.BOOLEAN)) {
					if (click(driver,np.getFileDistributorSaveButton(fileDistributor.BulkUpload), "save button", action.SCROLLANDBOOLEAN)) {
						if (isSelected(driver,np.getReverseNamingRadioButton(30),"reverse naming radio button") ) {
							appLog.info("successfully saved reverse naaming radio button");
						}
						else {
							appLog.error("could not save reverse naming radio button");
							sa.assertTrue(false, "could not save reverse naming radio button");
						}
						if (!isSelected(driver,np.getUseSuggestedNaming(30), "suggested naming radio button")) {
							appLog.info("successfully deselected suggested naming radio button");
						}
						else {
							appLog.error("suggested naming radio button is still selected");
							sa.assertTrue(false, "suggested naming radio button is still selected");
						}
					}
					else {
						appLog.error("save button on file distributor is not clickable");
						sa.assertTrue(false, "save button on file distributor is not clickable");
					}
				}
				else {
					appLog.error("reverse naming radio button is not clickable");
					sa.assertTrue(false, "reverse naming radio button is not clickable");
				}
			}
			switchToDefaultContent(driver);
		}
		lp.CRMlogout();
		ThreadSleep(10000);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M16FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, M16Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					if (click(driver,fp.getUploadIcon(Workspace.FundraisingWorkspace, 30), "upload icon fund/ws", action.BOOLEAN)) {
								parentID = switchOnWindow(driver);
								if (parentID!=null) {
									if (click(driver, fp.getBulkUploaderOrFileSplitterRadioButton(30), "bulk upload or file splitter radio button", action.BOOLEAN)) {
										if (fp.dragDropFiles(folderpath1, dropImage)) {
											ThreadSleep(5000);
											if (click(driver, FindElement(driver, "//li[text()='All Folders']/ul/li[text()='"+stdPath+"']/input", "parent folder radio button", action.BOOLEAN, 10), "parent folder radio button", action.BOOLEAN)) {
												if (click(driver, fp.getFileDistNextBtn(30), "file dist next button", action.BOOLEAN)) {
													if (fp.findFileOnIssueUIFileDistributor(files[0], M16Institution1, false)!=null) {
														appLog.info("successfully found "+files[0]+" of "+M16Institution1);
													}
													else {
														appLog.error("could not find "+files[0]+" of "+M16Institution1);
														sa.assertTrue(false, "could not find "+files[0]+" of "+M16Institution1);
													}
													if (fp.findFileOnIssueUIFileDistributor(files[1], M16Institution1, false)!=null) {
														appLog.info("successfully found "+files[1]+" of "+M16Institution1);
													}
													else {
														appLog.error("could not find "+files[1]+" of "+M16Institution1);
														sa.assertTrue(false, "could not find "+files[1]+" of "+M16Institution1);
													}
													if (fp.findFileOnIssueUIFileDistributor(files[0], M16Institution2, false)!=null) {
														appLog.info("successfully found "+files[0]+" of "+M16Institution2);
													}
													else {
														appLog.error("could not find "+files[0]+" of "+M16Institution2);
														sa.assertTrue(false, "could not find "+files[0]+" of "+M16Institution2);
													}
													if (fp.findFileOnIssueUIFileDistributor(files[1], M16Institution2, false)!=null) {
														appLog.info("successfully found "+files[1]+" of "+M16Institution2);
													}
													else {
														appLog.error("could not find "+files[1]+" of "+M16Institution2);
														sa.assertTrue(false, "could not find "+files[1]+" of "+M16Institution2);
													}
													for (int i = 0;i<2;i++) {
													saa=fp.verifyFileDist2Of2SuccessfullyAppliedUIReverse(M16Institution3, files[i], false, false, null, null);
													sa.combineAssertions(saa);
													
													}
													
													if (click(driver, fp.getResolveIssuesFileDist2Of2(30), "resolve issues link", action.BOOLEAN)) {
														if (click(driver, fp.getSelectFolderLinkForUnassignedFileName(M16Institution2+"_"+files[0]), "select folder link", action.BOOLEAN)) {
															if (fp.clickonFolderStructureFileDist(M16Institution2, stdPath)) {
																appLog.info("successfully clicked on radio button of "+stdPath);
															}
															else {
																appLog.error("could not find radio button of "+stdPath+" and inst "+M16Institution2);
																sa.assertTrue(false, "could not find radio button of "+stdPath+" and inst "+M16Institution2);
															}
															ThreadSleep(2000);
														}
														if (click(driver, fp.getSelectFolderLinkForUnassignedFileName(M16Institution1+"_"+files[1]), "select folder link", action.BOOLEAN)) {
															if (fp.clickonFolderStructureFileDist(M16Institution1, stdPath)) {
																appLog.info("successfully clicked on radio button of "+stdPath);
															}
															else {
																appLog.error("could not find radio button of "+stdPath+" and inst "+M16Institution1);
																sa.assertTrue(false, "could not find radio button of "+stdPath+" and inst "+M16Institution1);
															}
															ThreadSleep(2000);
														}
														if (click(driver, fp.getApplyButtonFileDist2bOf2(30), "file dist apply button", action.BOOLEAN)) {
															ThreadSleep(4000);
															saa=fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16Institution2, files[0], false, false, null, null,null);
															sa.combineAssertions(saa);
															saa=fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16Institution1, files[1], false, false, null, null,null);
															sa.combineAssertions(saa);

															for (int i = 0;i<2;i++) {
																saa=fp.verifyFileDist2Of2SuccessfullyAppliedUIReverse(M16Institution3, files[i], false, false, null, null);
																sa.combineAssertions(saa);

															}
															//issue ui 2 files
															if(fp.findFileOnIssueUIFileDistributor(files[0], M16Institution1, false)!=null) {
																appLog.info("successfully verified "+files[0] + " on "+M16Institution1);
															}
															else {
																appLog.error("could not verify "+files[0]+" on "+M16Institution1);
																sa.assertTrue(false, "could not verify "+files[0]+" on "+M16Institution1);
															}
															if(fp.findFileOnIssueUIFileDistributor(files[1], M16Institution2, false)!=null) {
																appLog.info("successfully verified "+files[1] + " on "+M16Institution2);
															}
															else {
																appLog.error("could not verify "+files[1]+" on "+M16Institution2);
																sa.assertTrue(false, "could not verify "+files[1]+" on "+M16Institution2);
															}
															if(click(driver, fp.getFileDist2Of2ApplyBtn(30),"apply button", action.BOOLEAN)) {
																fail_list=fp.verifyDuplicateDocWindowWithoutUpload(Workspace.FundraisingWorkspace, M16Institution1+"_"+files[1]+"<break>"+M16Institution2+"_"+files[0], M16FundName1+" > "+M16Institution1+" > "+stdPath+"<break>"+M16FundName1+" > "+M16Institution2+" > "+stdPath);
																if (fail_list.isEmpty()) {
																	appLog.info("successfully verified duplicate docs window with 2 files");
																}
																else {
																	for (int i = 0;i<fail_list.size();i++) {
																		appLog.error(fail_list.get(0)+" was not found in duplicate doc window");
																	}
																	sa.assertTrue(false, "could not find files on duplicate doc window");
																}
																if (fp.clickUsingCssSelectorPath("#lnkReplaceAll", "update all")) {
																//if (click(driver, fp.getUpdateAllButton(30), "update all button", action.BOOLEAN)) {
																	if (isAlertPresent(driver)) {
																		String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
																		switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
																		if (msg.trim().contains(FundsPageErrorMessage.DocumentUploadSuccessMsg)) {
																			appLog.info("document upload alert is successfully verified");
																		}
																		else {
																			appLog.error("could not verify document upload alert");
																			sa.assertTrue(false, "could not verify document upload alert");
																		}
																	}
																	else {
																		appLog.error("could not find alert after updating all docs");
																		sa.assertTrue(false, "could not find alert after updating all docs");
																	}
																}
																else {
																	appLog.error("update all button is not clickable");
																	sa.assertTrue(false, "update all button is not clickable");
																}
															}
															else {
																appLog.error("apply 2b of 2 button is not clickable");
																sa.assertTrue(false, "apply 2b of 2 button is not clickable");
															}
														}
														else {
															appLog.error("apply 2b of 2 button is not clickable");
															sa.assertTrue(false, "apply 2b of 2 button is not clickable");
														}

													}
													else {
														appLog.error("resolve issue button is not clickable");
														sa.assertTrue(false, "resolve issue button is not clickable");
													}
														
												}
												else {
													appLog.error("next button is not clickable on file dist 2of 2");
													sa.assertTrue(false, "next button is not clickable on file dist 2of 2");
												}
											}
											else {
												appLog.error("stdpath radio button is not found on file dist window");
												sa.assertTrue(false, "stdpath radio button is not found on file dist window");
											}
										}
										else {
											appLog.error("drag and drop of files is not successful");
											sa.assertTrue(false, "drag and drop of files is not successful");
										}
									}
									else {
										appLog.error("bulk upload or file splitter radio button on upload window is not clickable");
										sa.assertTrue(false, "bulk upload or file splitter radio button on upload window is not clickable");
									}
									driver.switchTo().window(parentID);
									switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
								}
								else {
									appLog.error("could not find new window to switch");
									sa.assertTrue(false, "could not find new window to switch");
								}
						
						}
						else {
							appLog.error(stdPath+" is not found in folder structure");
							sa.assertTrue(false, stdPath+" is not found in folder structure");
						}
						appLog.info("verifying updated files on folders");
						//verifying 6
						if (fp.verifyFolderPathdummy(stdPath, M16Institution3, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
							if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.FundraisingWorkspace, fp.reverseFileNameForFileDistributor(files[0], M16Institution3))) {
								appLog.info("successfully verified "+files[0]+" on "+M16Institution3);
							}
							else {
								appLog.error("could not verify "+files[0]+" on "+M16Institution3);
								sa.assertTrue(false, "could not verify "+files[0]+" on "+M16Institution3);
							}
							if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.FundraisingWorkspace, fp.reverseFileNameForFileDistributor(files[1], M16Institution3))) {
								appLog.info("successfully verified "+files[1]+" on "+M16Institution3);
							}
							else {
								appLog.error("could not verify "+files[1]+" on "+M16Institution3);
								sa.assertTrue(false, "could not verify "+files[1]+" on "+M16Institution3);
							}
						}
						if (fp.verifyFolderPathdummy(stdPath, M16Institution2, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
							if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.FundraisingWorkspace, M16Institution2+"_"+files[0])) {
								appLog.info("successfully verified "+files[0]+" on "+M16Institution2);
							}
							else {
								appLog.error("could not verify "+files[0]+" on "+M16Institution2);
								sa.assertTrue(false, "could not verify "+files[0]+" on "+M16Institution2);
							}
							if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.FundraisingWorkspace, M16Institution2+"_"+files[1])) {
								appLog.info("successfully verified "+files[1]+" on "+M16Institution2);
							}
							else {
								appLog.error("could not verify "+files[1]+" on "+M16Institution2);
								sa.assertTrue(false, "could not verify "+files[1]+" on "+M16Institution2);
							}
						}
						if (fp.verifyFolderPathdummy(stdPath, M16Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
							if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.FundraisingWorkspace, M16Institution1+"_"+files[0])) {
								appLog.info("successfully verified "+files[0]+" on "+M16Institution1);
							}
							else {
								appLog.error("could not verify "+files[0]+" on "+M16Institution1);
								sa.assertTrue(false, "could not verify "+files[0]+" on "+M16Institution1);
							}
							if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.FundraisingWorkspace, M16Institution1+"_"+files[1])) {
								appLog.info("successfully verified "+files[1]+" on "+M16Institution1);
							}
							else {
								appLog.error("could not verify "+files[1]+" on "+M16Institution1);
								sa.assertTrue(false, "could not verify "+files[1]+" on "+M16Institution1);
							}
						}
				}else {
					appLog.error(stdPath+" is not found in folder structure");
					sa.assertTrue(false, stdPath+" is not found in folder structure");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("could not find fund on funds tab");
				sa.assertTrue(false, "could not find fund on funds tab");
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
	public void M16tc014_ChangeBulkUploadSettings_ImpactCRM() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String files[] = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M16tc010_VerifyBulkUploadSuggestedNaming", excelLabel.KeyWord_For_Search).split("<break>");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M16Institution1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.InstitutionsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, null, null, M16FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, M16Institution1+"_"+files[0])) {
						appLog.info("successfully verified "+files[0]+" on "+M16Institution1);
					}
					else {
						appLog.error("could not verify "+files[0]+" on "+M16Institution1);
						sa.assertTrue(false, "could not verify "+files[0]+" on "+M16Institution1);
					}
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, M16Institution1+"_"+files[1])) {
						appLog.info("successfully verified "+files[1]+" on "+M16Institution1);
					}
					else {
						appLog.error("could not verify "+files[1]+" on "+M16Institution1);
						sa.assertTrue(false, "could not verify "+files[1]+" on "+M16Institution1);
					}
				}
				switchToDefaultContent(driver);
			}
				else {
					appLog.error("could not find institution on inst page");
					sa.assertTrue(false, "could not find institution on inst page");
				}
			}
			else {
				appLog.error("inst tab is not clickable");
				sa.assertTrue(false, "inst tab is not clickable");
			}
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M16Institution2)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.InstitutionsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, null, null, M16FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, M16Institution2+"_"+files[0])) {
						appLog.info("successfully verified "+files[0]+" on "+M16Institution2);
					}
					else {
						appLog.error("could not verify "+files[0]+" on "+M16Institution2);
						sa.assertTrue(false, "could not verify "+files[0]+" on "+M16Institution2);
					}
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, M16Institution2+"_"+files[1])) {
						appLog.info("successfully verified "+files[1]+" on "+M16Institution2);
					}
					else {
						appLog.error("could not verify "+files[1]+" on "+M16Institution2);
						sa.assertTrue(false, "could not verify "+files[1]+" on "+M16Institution2);
					}
				}
				switchToDefaultContent(driver);
			}
				else {
					appLog.error("could not find institution on inst page");
					sa.assertTrue(false, "could not find institution on inst page");
				}
			}
			else {
				appLog.error("inst tab is not clickable");
				sa.assertTrue(false, "inst tab is not clickable");
			}
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M16Institution3)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.InstitutionsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, null, null, M16FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, fp.reverseFileNameForFileDistributor(files[0], M16Institution3))) {
						appLog.info("successfully verified "+files[0]+" on "+M16Institution3);
					}
					else {
						appLog.error("could not verify "+files[0]+" on "+M16Institution3);
						sa.assertTrue(false, "could not verify "+files[0]+" on "+M16Institution3);
					}
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, fp.reverseFileNameForFileDistributor(files[1], M16Institution3))) {
						appLog.info("successfully verified "+files[1]+" on "+M16Institution3);
					}
					else {
						appLog.error("could not verify "+files[1]+" on "+M16Institution3);
						sa.assertTrue(false, "could not verify "+files[1]+" on "+M16Institution3);
					}
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("could not find institution on inst page");
				sa.assertTrue(false, "could not find institution on inst page");
			}
		}
		else {
			appLog.error("inst tab is not clickable");
			sa.assertTrue(false, "inst tab is not clickable");
		}
		if (bp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M16Contact1FirstName, M16Contact1LastName, null)) {
				switchToFrame(driver, 30, cp.getFrame(environment,mode, PageName.ContactsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, M16Institution1, null, M16FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.ContactsPage, Workspace.FundraisingWorkspace, M16Institution1+"_"+files[0])) {
						appLog.info("successfully verified "+files[0]+" on "+M16Institution1);
					}
					else {
						appLog.error("could not verify "+files[0]+" on "+M16Institution1);
						sa.assertTrue(false, "could not verify "+files[0]+" on "+M16Institution1);
					}
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.ContactsPage, Workspace.FundraisingWorkspace, M16Institution1+"_"+files[1])) {
						appLog.info("successfully verified "+files[1]+" on "+M16Institution1);
					}
					else {
						appLog.error("could not verify "+files[1]+" on "+M16Institution1);
						sa.assertTrue(false, "could not verify "+files[1]+" on "+M16Institution1);
					}
				}
				if (fp.verifyFolderPathdummy(stdPath, M16Institution2, null, M16FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.ContactsPage, Workspace.FundraisingWorkspace, M16Institution2+"_"+files[0])) {
						appLog.info("successfully verified "+files[0]+" on "+M16Institution2);
					}
					else {
						appLog.error("could not verify "+files[0]+" on "+M16Institution2);
						sa.assertTrue(false, "could not verify "+files[0]+" on "+M16Institution2);
					}
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.ContactsPage, Workspace.FundraisingWorkspace, M16Institution2+"_"+files[1])) {
						appLog.info("successfully verified "+files[1]+" on "+M16Institution2);
					}
					else {
						appLog.error("could not verify "+files[1]+" on "+M16Institution2);
						sa.assertTrue(false, "could not verify "+files[1]+" on "+M16Institution2);
					}
				}
				if (fp.verifyFolderPathdummy(stdPath, M16Institution3, null, M16FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.ContactsPage, Workspace.FundraisingWorkspace, fp.reverseFileNameForFileDistributor(files[0], M16Institution3))) {
						appLog.info("successfully verified "+files[0]+" on "+M16Institution3);
					}
					else {
						appLog.error("could not verify "+files[0]+" on "+M16Institution3);
						sa.assertTrue(false, "could not verify "+files[0]+" on "+M16Institution3);
					}
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.ContactsPage, Workspace.FundraisingWorkspace, fp.reverseFileNameForFileDistributor(files[1], M16Institution3))) {
						appLog.info("successfully verified "+files[1]+" on "+M16Institution3);
					}
					else {
						appLog.error("could not verify "+files[1]+" on "+M16Institution3);
						sa.assertTrue(false, "could not verify "+files[1]+" on "+M16Institution3);
					}
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("could not find contact "+M16Contact1FirstName+" "+M16Contact1LastName);
				sa.assertTrue(false, "could not find contact "+M16Contact1FirstName+" "+M16Contact1LastName);
			}
		}
		else {
			appLog.error("could not click on contact tab");
			sa.assertTrue(false, "could not click on contact tab");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M16tc014_ChangeBulkUploadSettings_ImpactInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String files[] = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M16tc010_VerifyBulkUploadSuggestedNaming", excelLabel.KeyWord_For_Search).split("<break>");
		SoftAssert saa = new SoftAssert();
		List<String> fail_list = null;
		String date = getSystemDate("MM/dd/yyyy")+previousOrForwardDate(-1, "MM/dd/yyyy");
		lp.investorLogin(M16Contact1EmailId, adminPassword);
		
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			if (fp.verifyFolderPathdummy(stdPath, M16Institution1, null, M16FundName1, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)) {
				saa=ifp.verifyContentGridInvestorSide(driver, PageName.PotentialInvestmentPage, M16Institution1+"_"+files[0]+"<break>"+ M16Institution1+"_"+files[1], CRMUser1FirstName+" "+CRMUser1LastName, date);
				sa.combineAssertions(saa);
			}
			else {
				appLog.error("could not find path "+stdPath+ " of "+M16Institution1);
				sa.assertTrue(false, "could not find path "+stdPath+ " of "+M16Institution1);
			}
			if (fp.verifyFolderPathdummy(stdPath, M16Institution2, null, M16FundName1, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)) {
				saa=ifp.verifyContentGridInvestorSide(driver, PageName.PotentialInvestmentPage, M16Institution2+"_"+files[0]+"<break>"+ M16Institution2+"_"+files[1], CRMUser1FirstName+" "+CRMUser1LastName, date);
				sa.combineAssertions(saa);
			}
			else {
				appLog.error("could not find path "+stdPath+ " of "+M16Institution2);
				sa.assertTrue(false, "could not find path "+stdPath+ " of "+M16Institution2);
			}
			if (fp.verifyFolderPathdummy(stdPath, M16Institution3, null, M16FundName1, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)) {
				saa=ifp.verifyContentGridInvestorSide(driver, PageName.PotentialInvestmentPage, fp.reverseFileNameForFileDistributor(files[0], M16Institution3)+"<break>"+fp.reverseFileNameForFileDistributor(files[1], M16Institution3), CRMUser1FirstName+" "+CRMUser1LastName, date);
				sa.combineAssertions(saa);
			}
			else {
				appLog.error("could not find path "+stdPath+ " of "+M16Institution3);
				sa.assertTrue(false, "could not find path "+stdPath+ " of "+M16Institution3);
			}
				
		}
		else {
			appLog.error("could not click on potential investments tab");
			sa.assertTrue(false, "could not click on potential investments tab");
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Test
	public void M16tc015_VerifyUploadValidAndInvalidDelimeter_Action() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		String parentID = null;
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String files = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.KeyWord_For_Search);
		SoftAssert saa = new SoftAssert();
		String folderpath1 = "UploadFiles\\Module16\\Bulk_File_Upload_2";
		String dropImage="BulkUpload.jpg";
		List<String> fail_list = null;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M16FundName1)) {
		switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, M16Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					if (click(driver,fp.getUploadIcon(Workspace.FundraisingWorkspace, 30), "upload icon fund/ws", action.BOOLEAN)) {
								parentID = switchOnWindow(driver);
								if (parentID!=null) {
									if (click(driver, fp.getBulkUploaderOrFileSplitterRadioButton(30), "bulk upload or file splitter radio button", action.BOOLEAN)) {
										if (fp.dragDropFiles(folderpath1, dropImage)) {
											ThreadSleep(5000);
											if (click(driver, FindElement(driver, "//li[text()='All Folders']/ul/li[text()='"+stdPath+"']/input", "parent folder radio button", action.BOOLEAN, 10), "parent folder radio button", action.BOOLEAN)) {
												if (click(driver, fp.getFileDistNextBtn(30), "file dist next button", action.BOOLEAN)) {
													if (fp.getFileDist2Of2Info(30).getText().trim().contains(FundsPageErrorMessage.bulkUploadProcessCompleted1+"4"+FundsPageErrorMessage.bulkUploadProcessCompleted2)) {
														appLog.info("correct msg info is being displayed");
													}
													else {
														appLog.error("could not verify info section of file dist window");
														sa.assertTrue(false, "could not verify info section of file dist window");
													}
													saa=fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16Institution1, files, false, false, null, null, null);
													sa.combineAssertions(saa);
													
													saa=fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16Institution2, files, false, false, null, null, null);
													sa.combineAssertions(saa);
													
													saa=fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16Institution3, files, false, false, null, null, null);
													sa.combineAssertions(saa);
													
													if (fp.findFileOnIssueUIFileDistributor(files, M16Institution4, false)!=null) {
														appLog.info("successfully found "+files+" of "+M16Institution4+" on issue ui");
													}
													else {
														appLog.error("could not find "+files+" of "+M16Institution4+" on issue ui");
														sa.assertTrue(false, "could not find "+files+" of "+M16Institution4+" on issue ui");
													}
													saa = fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16Institution3, files, false, true,null,null,action.ACCEPT);
													sa.combineAssertions(saa);
													//now inst 3 file will not be present in successfully applied ui
													
													if (click(driver, fp.getResolveIssuesFileDist2Of2(30), "resolve issues button", action.BOOLEAN)) {
														if (click(driver, fp.getSelectFolderLinkForUnassignedFileName(M16Institution4+"_"+files), "inst 4 file", action.BOOLEAN)) {
															if (fp.clickonFolderStructureFileDist(M16Institution3, stdPath)) {
																if (click(driver, fp.getApplyButtonFileDist2bOf2(30), "apply button 2b of 2", action.BOOLEAN)) {
																	
																	ThreadSleep(4000);
																	saa=fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16Institution1, files, false, false, null, null, null);
																	sa.combineAssertions(saa);
																	
																	saa=fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16Institution2, files, false, false, null, null, null);
																	sa.combineAssertions(saa);
																	
																	saa=fp.verifyFileDist2Of2SuccessfullyAppliedUIDiffInst(M16Institution3, M16Institution4, files, false, false, null, null, null);
																	sa.combineAssertions(saa);
																	//no issues will be present in issue ui
																	List<WebElement> issueUI = fp.getIssueUiOnFileDistributor(30, false);
																	if (!issueUI.isEmpty()) {
																		if (issueUI.get(0).getText().trim().equals("No issue File")) {
																			appLog.info("no issue file is successfully found on issue ui");
																		}
																		else {
																			appLog.error("no issue file is not visible on issue ui");
																			sa.assertTrue(false, "no issue file is not visible on issue ui");
																		}
																	}
																	if (fp.clickUsingCssSelectorPath("#lnlbuttonApply", "apply")) {
																		
																	//if (click(driver, fp.getFileDist2Of2ApplyBtn(30), "apply button 2 of 2", action.BOOLEAN)) {
																		if (isAlertPresent(driver)) {
																			String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
																			switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
																			if (msg.trim().contains(FundsPageErrorMessage.DocumentUploadSuccessMsg)) {
																				appLog.info("successfully verified document upload message");
																			}
																			else {
																				appLog.error("could not verify document upload message");
																				sa.assertTrue(false, "could not verify document upload message");
																			}
																		}
																		else {
																			appLog.error("no alert is found when clicked on apply button");
																			sa.assertTrue(false, "no alert is found when clicked on apply button");
																		}
																	}
																	else {
																		appLog.error("2 of 2 apply button is not clickable");
																		sa.assertTrue(false, "2 of 2 apply button is not clickable");
																	}
																}
																else {
																	appLog.error("apply button 2b of 2 is not clickable");
																	sa.assertTrue(false, "apply button 2b of 2 is not clickable");
																}
															}
															else {
																appLog.error("could not find institution and folder in folder structure");
																sa.assertTrue(false, "could not find institution and folder in folder structure");
															}
														}
														else {
															appLog.error("select folder link is not clickable");
															sa.assertTrue(false, "select folder link is not clickable");
														}
													}
													else {
														appLog.error("resolve issues link is not clickable");
														sa.assertTrue(false, "resolve issues link is not clickable");
													}
												}
												else {
													appLog.error("next button is not clickable");
													sa.assertTrue(false, "next button is not clickable");
												}
												}
											else {
												appLog.error("std folder radio button is not clickable");
												sa.assertTrue(false, "std folder radio button is not clickable");
											}
										}
										else {
											appLog.error("could not drag and drop files to bulk upload window");
											sa.assertTrue(false, "could not drag and drop files to bulk upload window");
										}
									}
									else {
										appLog.error("bulk upload radio button is not clickable");
										sa.assertTrue(false, "bulk upload radio button is not clickable");
									}
									driver.switchTo().window(parentID);
									switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
								}
								else {
									appLog.error("could not find child window to switch");
									sa.assertTrue(false, "could not find child window to switch");
								}
								if (fp.verifyFolderPathdummy(stdPath, M16Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
									if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.FundraisingWorkspace, M16Institution1+"_"+files)) {
										appLog.info("successfully verified "+files+" on "+M16Institution1);
									}
									else {
										appLog.error("could not verify "+files+" on "+M16Institution1);
										sa.assertTrue(false, "could not verify "+files+" on "+M16Institution1);
									}
									if (fp.clickOnDocument(M16Institution1+"_"+files, 30)) {
										parentID = switchOnWindow(driver);
										ThreadSleep(5000);
										if (parentID!=null) {
											
											if (fp.verifyDataOnFileDistDocument(M16Institution1, "File 1")) {
												appLog.info("successfully verified data on file splitter doc for inst 1");
											}
											else {
												appLog.error("could not verify file splitter doc for inst 1");
												sa.assertTrue(false, "could not verify file splitter doc for inst 1");
											}
											driver.close();
											driver.switchTo().window(parentID);
											switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
										}
									}
									
								}
								else {
									appLog.error(stdPath+" is not found in folder structure");
									sa.assertTrue(false, stdPath+" is not found in folder structure");
								}
								if (fp.verifyFolderPathdummy(stdPath, M16Institution2, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
									if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.FundraisingWorkspace, M16Institution2+"_"+files)) {
										appLog.info("successfully verified files on "+M16Institution2);
									}
									else {
										appLog.error("could not verify file on "+M16Institution2);
										sa.assertTrue(false, "could not verify file on "+M16Institution2);
									}
									if (fp.clickOnDocument(M16Institution2+"_"+files, 30)) {
										parentID = switchOnWindow(driver);
										ThreadSleep(5000);
										if (parentID!=null) {
											
											if (fp.verifyDataOnFileDistDocument(M16Institution2, "File 2")) {
												appLog.info("successfully verified data on file splitter doc for inst 2");
											}
											else {
												appLog.error("could not verify file splitter doc for inst 2");
												sa.assertTrue(false, "could not verify file splitter doc for inst 2");
											}
											driver.close();
											driver.switchTo().window(parentID);
											switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
										}
									}
								}
								else {
									appLog.error(stdPath+" is not found in folder structure");
									sa.assertTrue(false, stdPath+" is not found in folder structure");
								}
								if (fp.verifyFolderPathdummy(stdPath, M16Institution3, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
									if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.FundraisingWorkspace, M16Institution3+"_"+M16Institution4+"_"+files)) {
										appLog.info("successfully verified files on inst 3");
									}
									else {
										appLog.error("could not verify file on inst 3");
										sa.assertTrue(false, "could not verify file on inst 3");
									}
									if (fp.clickOnDocument(M16Institution3+"_"+M16Institution4+"_"+files, 30)) {
										parentID = switchOnWindow(driver);
										ThreadSleep(5000);
										if (parentID!=null) {
											
											if (fp.verifyDataOnFileDistDocument(M16Institution4, "File 4")) {
												appLog.info("successfully verified data on file splitter doc for inst 3");
											}
											else {
												appLog.error("could not verify file splitter doc for inst 3");
												sa.assertTrue(false, "could not verify file splitter doc for inst 3");
											}
											driver.close();
											driver.switchTo().window(parentID);
											switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
										}
									}
								}
								else {
									appLog.error(stdPath+" is not found in folder structure");
									sa.assertTrue(false, stdPath+" is not found in folder structure");
								}
									
					}
					else {
						appLog.error("upload icon is not clickable");
						sa.assertTrue(false, "upload icon is not clickable");
					}
				}
				else {
					appLog.error(stdPath+" is not found in folder structure");
					sa.assertTrue(false, stdPath+" is not found in folder structure");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M16FundName1+" is not found on funds tab");
				sa.assertTrue(false, M16FundName1+" is not found on funds tab");
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
	public void M16tc015_VerifyUploadValidAndInvalidDelimeter_ImpactCRM() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		String parentID = null;
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String files = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M16tc015_VerifyUploadValidAndInvalidDelimeter_Action", excelLabel.KeyWord_For_Search);
		SoftAssert saa = new SoftAssert();
		String folderpath1 = "UploadFiles\\Module16\\Bulk_File_Upload_2";
		String dropImage="BulkUpload.jpg";
		List<String> fail_list = null;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M16Institution1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.InstitutionsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, null, null, M16FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, M16Institution1+"_"+files)) {
						appLog.info("successfully verified "+files+" on "+M16Institution1);
					}
					else {
						appLog.error("could not verify "+files+" on "+M16Institution1);
						sa.assertTrue(false, "could not verify "+files+" on "+M16Institution1);
					}
					}
				else {
					appLog.error("could not find "+stdPath+" in folder structure");
					sa.assertTrue(false, "could not find "+stdPath+" in folder structure");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("could not find institution "+M16Institution1);
				sa.assertTrue(false, "could not find institution "+M16Institution1);
			}
		}
		else {
			appLog.error("inst tab is not clickable");
			sa.assertTrue(false, "inst tab is not clickable");
		}
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M16Institution2)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.InstitutionsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, null, null, M16FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, M16Institution2+"_"+files)) {
						appLog.info("successfully verified "+files+" on "+M16Institution2);
					}
					else {
						appLog.error("could not verify "+files+" on "+M16Institution2);
						sa.assertTrue(false, "could not verify "+files+" on "+M16Institution2);
					}
					}
				else {
					appLog.error("could not find "+stdPath+" in folder structure");
					sa.assertTrue(false, "could not find "+stdPath+" in folder structure");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("could not find institution "+M16Institution2);
				sa.assertTrue(false, "could not find institution "+M16Institution2);
			}
		}
		else {
			appLog.error("inst tab is not clickable");
			sa.assertTrue(false, "inst tab is not clickable");
		}
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M16Institution3)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.InstitutionsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, null, null, M16FundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, M16Institution3+"_"+M16Institution4+"_"+files)) {
						appLog.info("successfully verified "+files+" on "+M16Institution3);
					}
					else {
						appLog.error("could not verify "+files+" on "+M16Institution3);
						sa.assertTrue(false, "could not verify "+files+" on "+M16Institution3);
					}
					}
				else {
					appLog.error("could not find "+stdPath+" in folder structure");
					sa.assertTrue(false, "could not find "+stdPath+" in folder structure");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("could not find institution "+M16Institution3);
				sa.assertTrue(false, "could not find institution "+M16Institution3);
			}
		}
		else {
			appLog.error("inst tab is not clickable");
			sa.assertTrue(false, "inst tab is not clickable");
		}
		if (bp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M16Contact1FirstName, M16Contact1LastName, null)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.ContactsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, M16Institution1, null, M16FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.ContactsPage, Workspace.FundraisingWorkspace, M16Institution1+"_"+files)) {
						appLog.info("successfully verified "+files+" on "+M16Institution1);
					}
					else {
						appLog.error("could not verify "+files+" on "+M16Institution1);
						sa.assertTrue(false, "could not verify "+files+" on "+M16Institution1);
					}
					}
				else {
					appLog.error("could not find "+stdPath+" in folder structure");
					sa.assertTrue(false, "could not find "+stdPath+" in folder structure");
				}
				if (fp.verifyFolderPathdummy(stdPath, M16Institution2, null, M16FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.ContactsPage, Workspace.FundraisingWorkspace, M16Institution2+"_"+files)) {
						appLog.info("successfully verified "+files+" on "+M16Institution2);
					}
					else {
						appLog.error("could not verify "+files+" on "+M16Institution2);
						sa.assertTrue(false, "could not verify "+files+" on "+M16Institution2);
					}
					}
				else {
					appLog.error("could not find "+stdPath+" in folder structure");
					sa.assertTrue(false, "could not find "+stdPath+" in folder structure");
				}
				
				if (fp.verifyFolderPathdummy(stdPath, M16Institution3, null, M16FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.ContactsPage, Workspace.FundraisingWorkspace, M16Institution3+"_"+M16Institution4+"_"+files)) {
						appLog.info("successfully verified "+files+" on "+M16Institution3);
					}
					else {
						appLog.error("could not verify "+files+" on "+M16Institution3);
						sa.assertTrue(false, "could not verify "+files+" on "+M16Institution3);
					}
					}
				else {
					appLog.error("could not find "+stdPath+" in folder structure");
					sa.assertTrue(false, "could not find "+stdPath+" in folder structure");
				}
				switchToDefaultContent(driver);
			}
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M16tc015_VerifyUploadValidAndInvalidDelimeter_ImpactInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		String parentID = null;
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String files = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M16tc015_VerifyUploadValidAndInvalidDelimeter_Action", excelLabel.KeyWord_For_Search);
		SoftAssert saa = new SoftAssert();
		List<String> fail_list = null;
		String date = getSystemDate("MM/dd/yyyy")+previousOrForwardDate(-1, "MM/dd/yyyy");
		lp.investorLogin(M16Contact1EmailId, adminPassword);
		if (click(driver, ifp.getAllDocumentsTab(30), "all documents tab", action.BOOLEAN)) {
			fail_list=ifp.verifyAlertOnRecentAllDocumentGrid(M16Institution1+"_"+files+"<break>"+M16Institution2+"_"+files+"<break>"+M16Institution3+"_"+M16Institution4+"_"+files, M16FundName1, investorSideWorkSpace.PotentialInvestment, TabName.AllDocuments, activityType.DocumentUpload, PageName.AllFirmsPage);
			if (fail_list.isEmpty()) {
				appLog.info("successfully verified all allerts on AllDocument");
			}
			else {
				for (int i = 0;i<fail_list.size();i++) {
					appLog.error(fail_list.get(i)+" is not present on AllDocument grid");
				}
				sa.assertTrue(false, "could not verify docs on AllDocument grid");
			}
		}
		else {
			appLog.error("all docs tab is not clickable");
			sa.assertTrue(false, "all docs tab is not clickable");
		}
		if (click(driver, ifp.getRecentActivitiesTab(30), "recent activities tab", action.BOOLEAN)) {
			fail_list=ifp.verifyAlertOnRecentAllDocumentGrid(M16Institution1+"_"+files+"<break>"+M16Institution2+"_"+files+"<break>"+M16Institution3+"_"+M16Institution4+"_"+files, M16FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities, activityType.DocumentUpload, PageName.AllFirmsPage);
			if (fail_list.isEmpty()) {
				appLog.info("successfully verified all allerts on recent activities");
			}
			else {
				for (int i = 0;i<fail_list.size();i++) {
					appLog.error(fail_list.get(i)+" is not present on recent docs grid");
				}
				sa.assertTrue(false, "could not verify docs on recent activities grid");
			}
		}
		else {
			appLog.error("RecentActivities tab is not clickable");
			sa.assertTrue(false, "RecentActivities tab is not clickable");
		}
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			if (fp.verifyFolderPathdummy(stdPath, M16Institution1, null, M16FundName1, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)) {
				saa=ifp.verifyContentGridInvestorSide(driver, PageName.PotentialInvestmentPage, M16Institution1+"_"+files, CRMUser1FirstName+" "+CRMUser1LastName, date);
				sa.combineAssertions(saa);
				if (fp.clickOnDocument(M16Institution1+"_"+files, 30)) {
					parentID = switchOnWindow(driver);
					ThreadSleep(5000);
					if (parentID!=null) {
						
						if (fp.verifyDataOnFileDistDocument(M16Institution1, "File 1")) {
							appLog.info("successfully verified data on file splitter doc for inst 1");
						}
						else {
							appLog.error("could not verify file splitter doc for inst 1");
							sa.assertTrue(false, "could not verify file splitter doc for inst 1");
						}
						driver.close();
						driver.switchTo().window(parentID);
						switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					}
				}
				else {
					appLog.error("document of "+M16Institution1+" is not found");
					sa.assertTrue(false, "document of "+M16Institution1+" is not found");
				}
			}
			else {
				appLog.error("could not find path "+stdPath+" of "+M16Institution1);
				sa.assertTrue(false, "could not find path "+stdPath+" of "+M16Institution1);
			}
			if (fp.verifyFolderPathdummy(stdPath, M16Institution2, null, M16FundName1, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)) {
				saa=ifp.verifyContentGridInvestorSide(driver, PageName.PotentialInvestmentPage, M16Institution2+"_"+files, CRMUser1FirstName+" "+CRMUser1LastName, date);
				sa.combineAssertions(saa);
				if (fp.clickOnDocument(M16Institution2+"_"+files, 30)) {
					parentID = switchOnWindow(driver);
					ThreadSleep(5000);
					if (parentID!=null) {
						
						if (fp.verifyDataOnFileDistDocument(M16Institution2, "File 2")) {
							appLog.info("successfully verified data on file splitter doc for inst 2");
						}
						else {
							appLog.error("could not verify file splitter doc for inst 2");
							sa.assertTrue(false, "could not verify file splitter doc for inst 2");
						}
						driver.close();
						driver.switchTo().window(parentID);
						switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					}
				}
				else {
					appLog.error("document of "+M16Institution2+" is not found");
					sa.assertTrue(false, "document of "+M16Institution2+" is not found");
				}
			}
			else {
				appLog.error("could not find path "+stdPath+" of "+M16Institution2);
				sa.assertTrue(false, "could not find path "+stdPath+" of "+M16Institution2);
			}
			if (fp.verifyFolderPathdummy(stdPath, M16Institution3, null, M16FundName1, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)) {
				saa=ifp.verifyContentGridInvestorSide(driver, PageName.PotentialInvestmentPage, M16Institution3+"_"+M16Institution4+"_"+files, CRMUser1FirstName+" "+CRMUser1LastName, date);
				sa.combineAssertions(saa);
				if (fp.clickOnDocument(M16Institution3+"_"+M16Institution4+"_"+files, 30)) {
					parentID = switchOnWindow(driver);
					ThreadSleep(5000);
					if (parentID!=null) {
						
						if (fp.verifyDataOnFileDistDocument(M16Institution4, "File 4")) {
							appLog.info("successfully verified data on file splitter doc for inst 3");
						}
						else {
							appLog.error("could not verify file splitter doc for inst 3");
							sa.assertTrue(false, "could not verify file splitter doc for inst 3");
						}
						driver.close();
						driver.switchTo().window(parentID);
						switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					}
				}
				else {
					appLog.error("document of "+M16Institution4+" is not found");
					sa.assertTrue(false, "document of "+M16Institution4+" is not found");
				}
			}
			else {
				appLog.error("could not find path "+stdPath+" of "+M16Institution3);
				sa.assertTrue(false, "could not find path "+stdPath+" of "+M16Institution3);
			}
		}
		else {
			appLog.error("could not click on potential investments tab");
			sa.assertTrue(false, "could not click on potential investments tab");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M16tc016_ChangeFileSplitterOptionsAndVerify_Action() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		String parentID = null;
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String files = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.KeyWord_For_Search);
		SoftAssert saa = new SoftAssert();
		String folderpath1 = "UploadFiles\\Module16\\Bulk_File_Upload_3";
		String dropImage="BulkUpload.jpg";
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, np.getFrame(environment,mode, PageName.NavatarInvestorManager, 30));
			if (np.clickOnSideMenusTab(sideMenu.FileDistributorSettings)) {
				if (np.clickOnSideMenusTab(sideMenu.FileSplitterOptions)) {
					if (click(driver, np.getReverseNamingRadioButton(30), "reverse naming radio button", action.BOOLEAN)) {
						if (click(driver,np.getFileDistributorSaveButton(fileDistributor.FileSplitter), "save button", action.SCROLLANDBOOLEAN)) {
							if (isSelected(driver,np.getReverseNamingRadioButton(30),"reverse naming radio button") ) {
								appLog.info("successfully saved reverse naaming radio button");
							}
							else {
								appLog.error("could not save reverse naming radio button");
								sa.assertTrue(false, "could not save reverse naming radio button");
							}
							if (!isSelected(driver,np.getUseSuggestedNaming(30), "suggested naming radio button")) {
								appLog.info("successfully deselected suggested naming radio button");
							}
							else {
								appLog.error("suggested naming radio button is still selected");
								sa.assertTrue(false, "suggested naming radio button is still selected");
							}
						}
						else {
							appLog.error("save button on file distributor is not clickable");
							sa.assertTrue(false, "save button on file distributor is not clickable");
						}
					}
					else {
						appLog.error("reverse naming radio button is not clickable");
						sa.assertTrue(false, "reverse naming radio button is not clickable");
					}
				}
				else {
					appLog.error("file splitter link is not clickable");
					sa.assertTrue(false, "file splitter link is not clickable");
				}
			}
			else {
				appLog.error("file distributor link is not clickable");
				sa.assertTrue(false, "file distributor link is not clickable");
			}
			switchToDefaultContent(driver);
		}
		else {
			appLog.error("nim tab is not clickable");
			sa.assertTrue(false, "nim tab is not clickable");
		}
		lp.CRMlogout();
		ThreadSleep(6000);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M16FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, M16Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					if (click(driver,fp.getUploadIcon(Workspace.FundraisingWorkspace, 30), "upload icon fund/ws", action.BOOLEAN)) {
						parentID = switchOnWindow(driver);
						if (parentID!=null) {
							if (click(driver, fp.getBulkUploaderOrFileSplitterRadioButton(30), "bulk upload or file splitter radio button", action.BOOLEAN)) {
								if (fp.dragDropFiles(folderpath1, dropImage)) {
									ThreadSleep(5000);
									if (click(driver, FindElement(driver, "//li[text()='All Folders']/ul/li[text()='"+stdPath+"']/input", "parent folder radio button", action.BOOLEAN, 10), "parent folder radio button", action.BOOLEAN)) {
										if (click(driver, fp.getFileDistNextBtn(30), "file dist next button", action.BOOLEAN)) {
											ThreadSleep(4000);
											saa=fp.verifyFileDist2Of2SuccessfullyAppliedUIReverse(M16Institution1, files, false, false, null, null);
											sa.combineAssertions(saa);
											saa=fp.verifyFileDist2Of2SuccessfullyAppliedUIReverse(M16Institution2, files, false, false, null, null);
											sa.combineAssertions(saa);
											saa=fp.verifyFileDist2Of2SuccessfullyAppliedUIReverse(M16Institution3, files, false, false, null, null);
											sa.combineAssertions(saa);
											//issue ui
											if (fp.findFileOnIssueUIFileDistributor(files, M16Institution4, true)!=null) {
												appLog.info("successfully verified "+files+" of "+M16Institution4+" on issue ui");
											}
											else {
												appLog.error("could not verify "+files+" of "+M16Institution4+" on issue ui");
												sa.assertTrue(false, "could not verify "+files+" of "+M16Institution4+" on issue ui");
											}
											if (fp.clickUsingCssSelectorPath("#lnlbuttonApply", "apply")) {
												
											//if (click(driver, fp.getFileDist2Of2ApplyBtn(30), "apply btn 2 of 2", action.BOOLEAN)) {
												if (isAlertPresent(driver)) {
													String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
													switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
													if (msg.trim().contains(FundsPageErrorMessage.DocumentUploadSuccessMsg)) {
														appLog.info("document upload success message is successfully verified");
													}
													else {
														appLog.error("could not verify document upload message");
														sa.assertTrue(false, "could not verify document upload message");
													}
												}
												else {
													appLog.error("alert is not present for clicking on apply button");
													sa.assertTrue(false, "alert is not present for clicking on apply button");
												}
											}
											else {
												appLog.error("apply button 2 of 2 is not clickable");
												sa.assertTrue(false, "apply button 2 of 2 is not clickable");
											}
										}
										else {
											appLog.error("next button is not clickable");
											sa.assertTrue(false, "next button is not clickable");
										}
									}
									else {
										appLog.error("std radio button is not clickable");
										sa.assertTrue(false, "std radio button is not clickable");
									}
								}
								else {
									appLog.error("could not drag and drop file");
									sa.assertTrue(false, "could not drag and drop file");
								}
							}
							else {
								appLog.error("bulk upload radio button is not clickable");
								sa.assertTrue(false, "bulk upload radio button is not clickable");
							}
							driver.switchTo().window(parentID);
							switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
						}
						else {
							appLog.error("could not find child window to switch");
							sa.assertTrue(false, "could not find child window to switch");
						}
						if (fp.verifyFolderPathdummy(stdPath, M16Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
							if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.FundraisingWorkspace, fp.reverseFileNameForFileDistributor(files, M16Institution1))) {
								appLog.info("successfully verified document "+files+" on "+M16Institution1);
							}
							else {
								appLog.error("could not verify "+files+" on "+M16Institution1);
								sa.assertTrue(false, "could not verify "+files+" on "+M16Institution1);
							}
							if (fp.clickOnDocument(fp.reverseFileNameForFileDistributor(files, M16Institution1), 30)) {
								parentID = switchOnWindow(driver);
								ThreadSleep(5000);
								if (parentID!=null) {
									ThreadSleep(5000);
									if (fp.verifyDataOnFileDistDocument(M16Institution1, "File 1")) {
										appLog.info("successfully verified data on file splitter doc for inst 1");
									}
									else {
										appLog.error("could not verify file splitter doc for inst 1");
										sa.assertTrue(false, "could not verify file splitter doc for inst 1");
									}
									driver.close();
									driver.switchTo().window(parentID);
									switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
								}
							}
							else {
								appLog.error("could not find document to click");
								sa.assertTrue(false, "could not find document to click");
							}
						}
						if (fp.verifyFolderPathdummy(stdPath, M16Institution2, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
							if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.FundraisingWorkspace, fp.reverseFileNameForFileDistributor(files, M16Institution2))) {
								appLog.info("successfully verified document "+files+" on "+M16Institution2);
							}
							else {
								appLog.error("could not verify "+files+" on "+M16Institution2);
								sa.assertTrue(false, "could not verify "+files+" on "+M16Institution2);
							}
							if (fp.clickOnDocument(fp.reverseFileNameForFileDistributor(files, M16Institution2), 30)) {
								parentID = switchOnWindow(driver);
								ThreadSleep(5000);
								if (parentID!=null) {
									if (fp.verifyDataOnFileDistDocument(M16Institution2, "File 2")) {
										appLog.info("successfully verified data on file splitter doc for inst 2");
									}
									else {
										appLog.error("could not verify file splitter doc for inst 2");
										sa.assertTrue(false, "could not verify file splitter doc for inst 2");
									}
									driver.close();
									driver.switchTo().window(parentID);
									switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
								}
							}
							else {
								appLog.error("could not find document to click");
								sa.assertTrue(false, "could not find document to click");
							}
						}
						else {
							appLog.error("could not find "+stdPath+" in folder structure");
							sa.assertTrue(false, "could not find "+stdPath+" in folder structure");
						}
						if (fp.verifyFolderPathdummy(stdPath, M16Institution3, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
							if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.FundraisingWorkspace, fp.reverseFileNameForFileDistributor(files, M16Institution3))) {
								appLog.info("successfully verified document "+files+" on "+M16Institution3);
							}
							else {
								appLog.error("could not verify "+files+" on "+M16Institution3);
								sa.assertTrue(false, "could not verify "+files+" on "+M16Institution3);
							}
							if (fp.clickOnDocument(fp.reverseFileNameForFileDistributor(files, M16Institution3), 30)) {
								parentID = switchOnWindow(driver);
								ThreadSleep(5000);
								if (parentID!=null) {
									ThreadSleep(5000);
									if (fp.verifyDataOnFileDistDocument(M16Institution3, "File 3")) {
										appLog.info("successfully verified data on file splitter doc for inst 3");
									}
									else {
										appLog.error("could not verify file splitter doc for inst 3");
										sa.assertTrue(false, "could not verify file splitter doc for inst 3");
									}
									driver.close();
									driver.switchTo().window(parentID);
									switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
								}
							}
							else {
								appLog.error("could not find document to click");
								sa.assertTrue(false, "could not find document to click");
							}
						}
						else {
							appLog.error("could not find "+stdPath+" in folder structure");
							sa.assertTrue(false, "could not find "+stdPath+" in folder structure");
						}
					}
					else {
						appLog.error("upload icon is not clickable");
						sa.assertTrue(false, "upload icon is not clickable");
					}
				}
				else {
					appLog.error("could not find "+stdPath+" in folder structure");
					sa.assertTrue(false, "could not find "+stdPath+" in folder structure");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M16FundName1+" is not found on funds page");
				sa.assertTrue(false, M16FundName1+" is not found on funds page");
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
	public void M16tc016_ChangeFileSplitterOptionsAndVerify_Impact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M16tc016_ChangeFileSplitterOptionsAndVerify_Action", excelLabel.StandardPath);
		String files = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M16tc016_ChangeFileSplitterOptionsAndVerify_Action", excelLabel.KeyWord_For_Search);
		SoftAssert saa = new SoftAssert();
		List<String> fail_list = null;
		String parentID = null;
		String date = getSystemDate("MM/dd/yyyy")+previousOrForwardDate(-1, "MM/dd/yyyy");
		lp.investorLogin(M16Contact1EmailId, adminPassword);
		if (click(driver, ifp.getAllDocumentsTab(30), "all documents tab", action.BOOLEAN)) {
			fail_list=ifp.verifyAlertOnRecentAllDocumentGrid(fp.reverseFileNameForFileDistributor(files, M16Institution1)+"<break>"+fp.reverseFileNameForFileDistributor(files, M16Institution2)+"<break>"+fp.reverseFileNameForFileDistributor(files, M16Institution3), M16FundName1, investorSideWorkSpace.PotentialInvestment, TabName.AllDocuments, activityType.DocumentUpload, PageName.AllFirmsPage);
			if (fail_list.isEmpty()) {
				appLog.info("successfully verified all allerts on AllDocument");
			}
			else {
				for (int i = 0;i<fail_list.size();i++) {
					appLog.error(fail_list.get(i)+" is not present on AllDocument grid");
				}
				sa.assertTrue(false, "could not verify docs on AllDocument grid");
			}
		}
		else {
			appLog.error("all docs tab is not clickable");
			sa.assertTrue(false, "all docs tab is not clickable");
		}
		if (click(driver, ifp.getRecentActivitiesTab(30), "recent activities tab", action.BOOLEAN)) {
			fail_list=ifp.verifyAlertOnRecentAllDocumentGrid(fp.reverseFileNameForFileDistributor(files, M16Institution1)+"<break>"+fp.reverseFileNameForFileDistributor(files, M16Institution2)+"<break>"+fp.reverseFileNameForFileDistributor(files, M16Institution3), M16FundName1, investorSideWorkSpace.PotentialInvestment, TabName.RecentActivities, activityType.DocumentUpload, PageName.AllFirmsPage);
			if (fail_list.isEmpty()) {
				appLog.info("successfully verified all allerts on recent activities");
			}
			else {
				for (int i = 0;i<fail_list.size();i++) {
					appLog.error(fail_list.get(i)+" is not present on recent docs grid");
				}
				sa.assertTrue(false, "could not verify docs on recent activities grid");
			}
		}
		else {
			appLog.error("RecentActivities tab is not clickable");
			sa.assertTrue(false, "RecentActivities tab is not clickable");
		}
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			if (fp.verifyFolderPathdummy(stdPath, M16Institution1, null, M16FundName1, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)) {
				saa=ifp.verifyContentGridInvestorSide(driver, PageName.PotentialInvestmentPage, fp.reverseFileNameForFileDistributor(files, M16Institution1), CRMUser1FirstName+" "+CRMUser1LastName, date);
				sa.combineAssertions(saa);
					if (fp.clickOnDocument(fp.reverseFileNameForFileDistributor(files, M16Institution1), 30)) {
					parentID = switchOnWindow(driver);
					ThreadSleep(5000);
					if (parentID!=null) {
						ThreadSleep(5000);
						if (fp.verifyDataOnFileDistDocument(M16Institution1, "File 1")) {
							appLog.info("successfully verified data on file splitter doc for inst 1");
						}
						else {
							appLog.error("could not verify file splitter doc for inst 1");
							sa.assertTrue(false, "could not verify file splitter doc for inst 1");
						}
						driver.close();
						driver.switchTo().window(parentID);
					}
				}
				else {
					appLog.error("could not find document to click");
					sa.assertTrue(false, "could not find document to click");
				}
			}
			if (fp.verifyFolderPathdummy(stdPath, M16Institution2, null, M16FundName1, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)) {
				saa=ifp.verifyContentGridInvestorSide(driver, PageName.PotentialInvestmentPage, fp.reverseFileNameForFileDistributor(files, M16Institution2), CRMUser1FirstName+" "+CRMUser1LastName, date);
				sa.combineAssertions(saa);
					if (fp.clickOnDocument(fp.reverseFileNameForFileDistributor(files, M16Institution2), 30)) {
					parentID = switchOnWindow(driver);
					ThreadSleep(5000);
					if (parentID!=null) {
						ThreadSleep(5000);
						if (fp.verifyDataOnFileDistDocument(M16Institution2, "File 2")) {
							appLog.info("successfully verified data on file splitter doc for lp 2");
						}
						else {
							appLog.error("could not verify file splitter doc for lp 2");
							sa.assertTrue(false, "could not verify file splitter doc for lp 2");
						}
						driver.close();
						driver.switchTo().window(parentID);
					}
				}
				else {
					appLog.error("could not find document to click");
					sa.assertTrue(false, "could not find document to click");
				}
			}
			if (fp.verifyFolderPathdummy(stdPath, M16Institution3, null, M16FundName1, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)) {
				saa=ifp.verifyContentGridInvestorSide(driver, PageName.PotentialInvestmentPage, fp.reverseFileNameForFileDistributor(files, M16Institution3), CRMUser1FirstName+" "+CRMUser1LastName, date);
				sa.combineAssertions(saa);
					if (fp.clickOnDocument(fp.reverseFileNameForFileDistributor(files, M16Institution3), 30)) {
					parentID = switchOnWindow(driver);
					ThreadSleep(5000);
					if (parentID!=null) {
						ThreadSleep(5000);
						if (fp.verifyDataOnFileDistDocument(M16Institution3, "File 3")) {
							appLog.info("successfully verified data on file splitter doc for lp 3");
						}
						else {
							appLog.error("could not verify file splitter doc for lp 3");
							sa.assertTrue(false, "could not verify file splitter doc for lp 3");
						}
						driver.close();
						driver.switchTo().window(parentID);
					}
				}
				else {
					appLog.error("could not find document to click");
					sa.assertTrue(false, "could not find document to click");
				}
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Test
	public void M16tc017_ActivateManageApprovalAndFileDistr() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		PartnershipPageBusinessLayer pp = new PartnershipPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		String userName1 = ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.User_First_Name)+" "+ExcelUtils.readData("Users",excelLabel.Variable_Name, "User1", excelLabel.User_Last_Name);
		
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
			if (np.clickOnSideMenusTab(sideMenu.ManageApprovals)) {
				if (np.clickOnEditIcon()) {
					if (click(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.Edit), "activate manage approval checkbox", action.SCROLLANDBOOLEAN)) {
						/*if (click(driver, np.findCheckboxForUserInManageApproval(CRMUser1FirstName+" "+CRMUser1LastName), "checkbox in front of "+userName1, action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on checkbox of "+userName1);
						}
						else {
							appLog.error("could not find checkbox for "+userName1);
							sa.assertTrue(false, "could not find checkbox for "+userName1);
						}
						*/if (click(driver, np.getManageApprovalSaveButton(60), "save button on manage approvals page", action.SCROLLANDBOOLEAN)) {
							if (click(driver, np.getManageApprovalActivateYesButton(30), "yes button manage approvals", action.BOOLEAN)) {
								
							}
							else {
								appLog.error("activate yes button is not clickable");
								sa.assertTrue(false, "activate yes button is not clickable");
							}
						}
						else {
							appLog.error("manage approval save button is not clickable");
							sa.assertTrue(false, "manage approval save button is not clickable");
						}
					}
					else {
						appLog.error("manage approvals activate checkbox is not clickable");
						sa.assertTrue(false, "manage approvals activate checkbox is not clickable");
					}
				}
				else {
					appLog.error("edit icon is not clickable");
					sa.assertTrue(false, "edit icon is not clickable");
				}
			}
			else {
				appLog.error("manage approvals side menu is not clickable");
				sa.assertTrue(false, "manage approvals side menu is not clickable");
			}
			if (np.clickOnSideMenusTab(sideMenu.FileDistributorSettings)) {
				if (click(driver, np.getDefaultNamingRadioButton(30), "suggested naming radio button", action.BOOLEAN)) {
					if (click(driver, np.getFileDistributorSaveButton(fileDistributor.BulkUpload), "save button bulk upload", action.BOOLEAN)) {
						
					}
					else {
						appLog.error("save button of file dist is not clickable");
						sa.assertTrue(false, "save button of file dist is not clickable");
					}
				}
				else {
					appLog.error("default naming radio button is not clickable");
					sa.assertTrue(false, "default naming radio button is not clickable");
				}
			}
			else {
				appLog.error("file distributor settings side menu is not clickable");
				sa.assertTrue(false, "file distributor settings side menu is not clickable");
			}
			if (np.clickOnSideMenusTab(sideMenu.FileDistributorSettings)) {
				if (np.clickOnSideMenusTab(sideMenu.FileSplitterOptions)) {
					if (click(driver,  np.getDefaultNamingRadioButton(30), "suggested naming radio button",action.BOOLEAN)) {
						if (click(driver, np.getFileDistributorSaveButton(fileDistributor.FileSplitter), "save button FileSplitter", action.BOOLEAN)) {
							
						}
						else {
							appLog.error("file dist save button is not clickable");
							sa.assertTrue(false, "file dist save button is not clickable");
						}
					}
					else {
						appLog.error("default naming radio button is not clickable");
						sa.assertTrue(false, "default naming radio button is not clickable");
					}
				}
				else {
					appLog.error("file splitter options is not clickable");
					sa.assertTrue(false, "file splitter options is not clickable");
				}
			}
			else {
				appLog.error("file dist settings is not clickable");
				sa.assertTrue(false, "file dist settings is not clickable");
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
	public void M16tc018_BuildInvWorkspaceAndInviteContact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		String Size = ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M16Fund1", excelLabel.Fund_Size);
		String vintageyear = ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M16Fund1",excelLabel.Fund_VintageYear);
		String contact = ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M16Fund1",excelLabel.Fund_Contact);
		String phone = ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M16Fund1", excelLabel.Fund_Phone);
		String email = ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M16Fund1", excelLabel.Fund_Email);
		String description = ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M16Fund1",excelLabel.Fund_Description);
		String[] data = { Size, vintageyear, contact, phone, email, description };

		
		if (np.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M16FundName1)) {
				ThreadSleep(3000);
				if (fp.buildWorkspace(data, WorkSpaceAction.IMPORTFOLDERTEMPLATE, folderTemplateName, null, M16Institution1+"/"+M16LimitedPartner1+"<break>"+M16Institution2+"/"+M16LimitedPartner2+"<break>"+M16Institution3+"/"+M16LimitedPartner3, Workspace.InvestorWorkspace, 30)){
					appLog.info("fundraising worskpace has been created succesfully");
				}
				else {
					appLog.error("could not build InvestorWorkspace");
					sa.assertTrue(false, "could not build InvestorWorkspace");
				}
				if (fp.inviteContact(environment, mode,M16Institution1+"/"+M16LimitedPartner1, M16Contact1EmailId, null, FolderType.Standard, "Upload", "Yes", "No", "All Folders", Workspace.InvestorWorkspace, null)) {
					appLog.info("invited contact "+M16Contact1FirstName+" "+M16Contact1LastName+" for "+M16Institution1);
				}
				else {
					appLog.error("could not invite contact "+M16Contact1EmailId + " for "+M16Institution1);
					sa.assertTrue(false, "could not invite contact "+M16Contact1EmailId + " for "+M16Institution1);
				}
				if (fp.inviteContact(environment, mode,M16Institution2+"/"+M16LimitedPartner2, M16Contact1EmailId, null, FolderType.Standard, "Upload", "Yes", "No", "All Folders", Workspace.InvestorWorkspace, M16Contact1EmailId)) {
					appLog.info("invited contact "+M16Contact1FirstName+" "+M16Contact1LastName+" for "+M16Institution2);
				}
				else {
					appLog.error("could not invite contact "+M16Contact1EmailId + " for "+M16Institution2);
					sa.assertTrue(false, "could not invite contact "+M16Contact1EmailId + " for "+M16Institution2);
				}
				if (fp.inviteContact(environment, mode,M16Institution3+"/"+M16LimitedPartner3, M16Contact1EmailId, null, FolderType.Standard, "Upload", "Yes", "Yes", "All Folders", Workspace.InvestorWorkspace, M16Contact1EmailId)) {
					appLog.info("invited contact "+M16Contact1FirstName+" "+M16Contact1LastName+" for "+M16Institution3);
				}
				else {
					appLog.error("could not invite contact "+M16Contact1EmailId + " for "+M16Institution3);
					sa.assertTrue(false, "could not invite contact "+M16Contact1EmailId + " for "+M16Institution3);
				}
			}

			else {
				appLog.error("cannot click on fund "+M16FundName1);
				sa.assertTrue(false, "cannot click on fund "+M16FundName1);
			}
		}
		else {
			appLog.error("cannot click on funds tab");
			sa.assertTrue(false, "cannot click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M16tc019_VerifyFileDistributorInvestorWs() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		PartnershipPageBusinessLayer pp = new PartnershipPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String parentID = null;
		String dropImage="BulkUpload.jpg";
		String folderpath1 = "UploadFiles\\Module16\\InvalidFileDistributor";
		String folderpath2 = "UploadFiles\\Module16\\Testing";
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M16FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, M16Institution1, M16LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					if (click(driver, fp.getUploadIcon(Workspace.InvestorWorkspace, 30), "upload icon InvestorWorkspace", action.SCROLLANDBOOLEAN)) {
						parentID = switchOnWindow(driver);
						if (parentID!=null) {
							if (!isSelected(driver, fp.getBulkUploaderOrFileSplitterRadioButton(30), "radio button for bulk upload")) {
								appLog.info("on upload window, radio button for bulk upload is not selected by default");
							}
							else {
								appLog.error("radio button for bulk upload is selected by default but it should not be");
								sa.assertTrue(false, "radio button for bulk upload is selected by default but it should not be");
							}
							if (click(driver, fp.getBulkUploaderOrFileSplitterRadioButton(30), "radio button for bulk upload", action.BOOLEAN)) {
								if (fp.getFileDistributorHead(30)!=null) {
									appLog.info("file distributor head is successfully displayed on upload window");
								}
								else {
									appLog.error("file distributor head is not displayed on upload window");
									sa.assertTrue(false, "file distributor head is not displayed on upload window");
								}
								if (fp.getFileDistributorCancelBtn(30)!=null) {
									appLog.info("cancel button on upload window is successfully displayed");
								}
								else {
									appLog.error("cancel button is not displayed on file distributor window");
									sa.assertTrue(false, "cancel button is not displayed on file distributor window");
								}
							}
							if (fp.dragDropFiles(folderpath1, dropImage)) {
								ThreadSleep(3000);
								if (click(driver, FindElement(driver, "//li[text()='All Folders']/ul/li[text()='"+stdPath+"']/input", "parent folder radio button", action.BOOLEAN, 10), "parent folder radio button", action.BOOLEAN)) {
									if (fp.clickUsingCssSelectorPath("div#BulkDocument_step_1of2 a[title=Next]", "next button")) {
										//if (click(driver, fp.getFileDistNextBtn(30), "file dist next button", action.BOOLEAN)) {
										ThreadSleep(5000);
										if (isAlertPresent(driver)) {
											String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
											switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
											if (msg.contains(FundsPageErrorMessage.invalidDelimeterError1)) {
												appLog.info("invalid delimiter error message is successfully verified");
											}
											else {
												appLog.error("invalid delimiter error message is wrong");
												sa.assertTrue(false, "invalid delimiter error message is wrong");
											}
											if (msg.contains(FundsPageErrorMessage.invalidDelimeterError2)) {
												appLog.info("invalid delimiter error message is successfully verified");
											}
											else {
												appLog.error("invalid delimiter error message is wrong");
												sa.assertTrue(false, "invalid delimiter error message is wrong");
											}
										}
										else {
											appLog.error("no alert message is present");
											sa.assertTrue(false, "no alert message is present");
										}
										//now standard radio button will be unchecked
										if (!isSelected(driver, FindElement(driver, "//li[text()='All Folders']/ul/li[text()='"+stdPath+"']/input", "parent folder radio button",action.BOOLEAN,30), "parent folder radio button")) {
											appLog.info("now parent folder standard radio button is deselected");
										}else {
											appLog.error("parent folder standard radio button is selected but it should not be");
											sa.assertTrue(false, "parent folder standard radio button is selected but it should not be");
										}
										if (click(driver, fp.getFileDistBackBtn(30), "file dist back button", action.BOOLEAN)) {
										}	
									}
									else {
										appLog.error("next button is not clickable");
										sa.assertTrue(false, "next button is not clickable");
									}
								}
								else {
									appLog.error("radio button for standard parent folder is not clickable");
									sa.assertTrue(false, "radio button for standard parent folder is not clickable");
								}
							}
							else {
								appLog.error("could not drag and drop file");
								sa.assertTrue(false, "could not drag and drop file");
							}
							
							if (fp.dragDropFiles(folderpath2, dropImage)) {
								ThreadSleep(3000);
								if (fp.getFileDistributorDocInfo(30).getText().trim().contains(FundsPageErrorMessage.fileDistributorDocInfo)) {
									appLog.info("document info text is successfully verified");
								}
								else {
									appLog.error("document info text is incorrect");
									sa.assertTrue(false, "document info text is incorrect");
								}
								if (fp.clickUsingCssSelectorPath("div#BulkDocument_step_1of2 a[title=Next]", "next button")) {
									//if (click(driver, fp.getFileDistNextBtn(30), "file distributor next button", action.SCROLLANDBOOLEAN)) {
									ThreadSleep(5000);
									if (isAlertPresent(driver)) {
										String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
										switchToAlertAndAcceptOrDecline(driver, 10, action.ACCEPT);
										if (msg.contains(FundsPageErrorMessage.pleaseSelectFolder)) {
											appLog.info("please Select Folder error message is successfully verified");
										}
										else {
											appLog.error("please Select Folder error alert message is wrong");
											sa.assertTrue(false, "please Select Folder error alert message is wrong");
										}
									}
									if (click(driver, FindElement(driver, "//li[text()='All Folders']/ul/li[text()='"+stdPath+"']/input", "parent folder radio button", action.BOOLEAN, 10), "parent folder radio button", action.BOOLEAN)) {
										if (click(driver, fp.getFileDistNextBtn(30), "file dist next button", action.BOOLEAN)) {
											//verify UI of file distributor window
											if (fp.getFileDist2Of2(30)!=null) {
												appLog.info("heading file distributor 2 of 2 is successfully displayed");
											}
											else {
												appLog.error("heading file distributor 2 of 2 is not visible");
												sa.assertTrue(false, "heading file distributor 2 of 2 is not visible");
											}
											if (isDisplayed(driver, FindElement(driver, "//div[@id='BulkDocument_step_2of2']//div[@class='yellow_txt_box']","file distributor yellow text", action.BOOLEAN, 30), "visbility", 10,"file distributor yellow text" ).getText().trim().contains(FundsPageErrorMessage.fileDistributionUploadprocesswillallowyoutosplit)) {
												appLog.info("correct msg info is being displayed");
											}
											else {
												appLog.error("msg info is not correct on upload window");
												sa.assertTrue(false, "msg info is not correct on upload window");
											}
											if (fp.getFileDist2Of2Info(30).getText().trim().contains(FundsPageErrorMessage.bulkUploadProcessCompleted1+"1"+FundsPageErrorMessage.bulkUploadProcessCompleted2)) {
												appLog.info("correct msg info is being displayed");
											}
											else {
												appLog.error("msg info is not visible on upload window");
												sa.assertTrue(false, "msg info is not visible on upload window");
											}
											if (fp.getSuccessfullyAppliedOnFileDistributor(30).getText().trim().contains(FundsPageErrorMessage.successfullyAppliedFileDistributor)) {
												appLog.info("successfully applied column text is successfully verified");
											}
											else {
												appLog.error("successfully applied column text is incorrect on webpage");
												sa.assertTrue(false, "successfully applied column text is incorrect on webpage");
											}
											List<WebElement> issueUI = fp.getIssueUiOnFileDistributor(30, true);
											if (!issueUI.isEmpty()) {
											if (issueUI.get(0).getText().trim().contains(3+FundsPageErrorMessage.issueUiFileDistributor)) {
												appLog.info("no of investors is correct on ui of file distributor");
											}
											else {
												appLog.error("no of investor is wrong on ui of file distributors");
												sa.assertTrue(false, "no of investor is wrong on ui of file distributors");
											}
											
											if ((issueUI.get(0).getText().trim().contains(M16LimitedPartner1)) &&(issueUI.get(0).getText().trim().contains(M16LimitedPartner2))&& (issueUI.get(0).getText().trim().contains(M16LimitedPartner3))) {
												appLog.info("all institutions name are correct on issue ui of file distributor");
											}
											else {
												appLog.error("all institutions name are not present on issue ui of file distributor");
												sa.assertTrue(false, "all institutions name are not present on issue ui of file distributor");
											}}
											if (fp.getFileDist2Of2BackBtn(30)!=null) {
												appLog.info("file dist 2 of 2 back button is successfully found");
											}
											else {
												appLog.error("file dist 2 of 2 back button is not visible");
												sa.assertTrue(false, "file dist 2 of 2 back button is not visible");
											}
											if (fp.getFileDist2Of2CancelBtn(30)!=null) {
												appLog.info("cancel button is successfully displayed");
											}
											else {
												appLog.error("cancel button is not visible on file dist window");
												sa.assertTrue(false, "cancel button is not visible on file dist window");
											}
											if (fp.getFileDist2Of2ApplyBtn(30)!=null) {
												appLog.info("apply button is successfully displayed on file dist 2 of 2 window");
											}
											else {
												appLog.error("apply button is not visible on file dist 2 of 2 window");
												sa.assertTrue(false, "apply button is not visible on file dist 2 of 2 window");
											}
											if (fp.clickUsingCssSelectorPath("#lnlbuttonApply", "apply")) {
												//if (click(driver, fp.getFileDist2Of2ApplyBtn(30), "apply btn file distribution", action.BOOLEAN)) {
												ThreadSleep(3000);
												if (isAlertPresent(driver)) {
													String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
													switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
													if (msg.trim().contains(FundsPageErrorMessage.noInvestorReceivedFiles)) {
														appLog.info("no investor received files is successfully displayed");
													}
													else {
														appLog.error("error alert for no investor received files is not displayed");
														sa.assertTrue(false, "error alert for no investor received files is not displayed");
													}
												}
												else {
													appLog.error("no alert is present");
													sa.assertTrue(false, "no alert is present");
												}
											}
											else {
												appLog.error("apply button is not clickable");
												sa.assertTrue(false, "apply button is not clickable");
												driver.close();
											}
										}
										else {
											appLog.error("next button is not clickable");
											sa.assertTrue(false, "next button is not clickable");
											driver.close();
										}
									}
									else {
										appLog.error("cannot click on std path radio button");
										sa.assertTrue(false, "cannot click on std path radio button");
										driver.close();
									}
								}
								else {
									appLog.error("next button of file dist is not clickable");
									sa.assertTrue(false, "next button of file dist is not clickable");
								}
							}
							else {
								appLog.error("could not drag and drop files");
								sa.assertTrue(false, "could not drag and drop files");
							}
							driver.switchTo().window(parentID);
							switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 10));
						}
						else {
							appLog.error("child window not found");
							sa.assertTrue(false, "child window not found");
						}
					}
					else {
						appLog.error("uplaod icon is not clickable");
						sa.assertTrue(false, "uplaod icon is not clickable");
					}
					//verify no file is present in any inst std folder
					if (click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30), "refresh icon", action.BOOLEAN)) {
						if (FindElement(driver, "//span[@id='myGridfundr-cell-0-0']//span", "no data to display", action.BOOLEAN, 30).getText().trim().contains(FundsPageErrorMessage.SelectFolderTemplateNoDataMessage)) {
							appLog.info("no file is present in workspace of isnt 1 folder");
						}
						else {
							appLog.error("no data to display is not found on workspace");
							sa.assertTrue(false, "no data to display is not found on workspace");
						}
					}
					else {
						appLog.error("content grid refresh button is not clickable");
						sa.assertTrue(false, "content grid refresh button is not clickable");
					}
				}
				else {
					appLog.error("standard path is not found in folder structure");
					sa.assertTrue(false, "standard path is not found in folder structure");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("fund name not found on funds page");
				sa.assertTrue(false, "fund name not found on funds page");
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
	public void M16tc020_VerifyResolveIssuePopup_Investor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String fileName = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
		String pathToTraverse = null;
		String firstWindow = null,secondWindow = null,thirdWindow = null;
		Set<String> windows = new HashSet<String>();
		String dropImage="BulkUpload.jpg";
		String folderpath1 = "UploadFiles\\Module16\\Testing";
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M16FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, M16Institution1, M16LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					if (click(driver, fp.getUploadIcon(Workspace.InvestorWorkspace, 30), "upload icon fundraising workspace", action.SCROLLANDBOOLEAN)) {
						firstWindow = switchOnWindow(driver);
						if (firstWindow!=null) {
							if (click(driver, fp.getBulkUploaderOrFileSplitterRadioButton(30), "radio button for bulk upload", action.BOOLEAN)) {
								if (fp.dragDropFiles(folderpath1, dropImage)) {
									ThreadSleep(5000);
									secondWindow = driver.getWindowHandle();
									if (click(driver, FindElement(driver, "//li[text()='All Folders']/ul/li[text()='"+stdPath+"']/input", "parent folder radio button", action.BOOLEAN, 10), "parent folder radio button", action.BOOLEAN)) {
										if (click(driver, fp.getFileDistNextBtn(30), "next button file dist", action.SCROLLANDBOOLEAN)) {
											if (click(driver, fp.getResolveIssuesFileDist2Of2(30), "resolve issues button on file dist 2 of 2", action.BOOLEAN)) {
												if (isDisplayed(driver, FindElement(driver, "//div[@id='BulkDocument_step_2bof2b']//div[@class='yellow_txt_box']", "to resolve the issues select folder(yellow text box)", action.BOOLEAN, 30),"visibility", 30, "to resolve the issues select folder(yellow text box)").getText().trim().contains(FundsPageErrorMessage.resolveIssueFileDistri2bOf2)) {
													appLog.info("yellow text box text is successfully verified");
												}
												else {
													appLog.error("yellow text box text is not verified for file distributor 2b of 2");
													sa.assertTrue(false, "yellow text box text is not verified for file distributor 2b of 2");
												}
												if (FindElements(driver, "//div[@id='BulkDocument_step_2bof2b']//div[@class='head_popup_inner']", "list of columns on resolve issue window").get(0).getText().trim().equals("Unassigned Files")) {
													appLog.info("unassigned files are successfully displayed on resolve issue window");
												}
												else {
													appLog.error("unassigned files could not be displayed on resolve issue window");
													sa.assertTrue(false, "unassigned files could not be displayed on resolve issue window");
												}
												if (FindElements(driver, "//div[@id='BulkDocument_step_2bof2b']//div[@class='head_popup_inner']", "list of columns on resolve issue window").get(1).getText().trim().equals("Investor Folders")) {
													appLog.info("investor folders column is successfully displayed");
												}
												else {
													appLog.error("investor folders column is not displayed");
													sa.assertTrue(false, "investor folders column is not displayed");
												}
												if (fp.getUnassignedFileNamesFileDist2bOf2(30).getText().trim().contains(fileName)) {
													appLog.info(fileName+" was successfully found on unassigned files column");
												}
												else {
													appLog.error(fileName+" could not be found on unassigned files column");
													sa.assertTrue(false, fileName+" could not be found on unassigned files column");
												}
												
												if (click(driver, fp.getViewLinkForUnassignedFileName(fileName), "view link", action.BOOLEAN)) {
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
													
													if(thirdWindow!=null){
														if (fp.getViewFileOnfileDistDownload(60) != null) {
															appLog.info("Download Button is displaying");
														} else {
															appLog.error("Document Download Button is not displaying");
															sa.assertTrue(false, "Document Download Button is not displaying");
														}
														if (fp.getViewFileOnFileDistClose(60) != null) {
															appLog.info("Close Button is displaying");
														} else {
															appLog.error("Close Button is not displaying");
															sa.assertTrue(false, "Close Button is not displaying");
														}
														driver.close();
														driver.switchTo().window(secondWindow);
													}
													
													if (click(driver, fp.getSelectFolderLinkForUnassignedFileName(fileName), "select folder link", action.BOOLEAN)) {
														if (fp.clickonFolderStructureFileDistInvestor(M16Institution1+"/"+M16LimitedPartner1, stdPath)){
															appLog.info("successfully clicked on "+stdPath+" folder of "+M16Institution1);
														}
														else {
															appLog.error("could not find path of std folder in "+M16Institution1);
															sa.assertTrue(false, "could not find path of std folder in "+M16Institution1);
														}
														if (fp.clickonFolderStructureFileDistInvestor(M16Institution2+"/"+M16LimitedPartner2, stdPath)){
															appLog.info("successfully clicked on "+stdPath+" folder of "+M16Institution2);
														}
														else {
															appLog.error("could not find path of std folder in "+M16Institution2);
															sa.assertTrue(false, "could not find path of std folder in "+M16Institution2);
														}
														if (fp.clickonFolderStructureFileDistInvestor(M16Institution3+"/"+M16LimitedPartner3, stdPath)){
															appLog.info("successfully clicked on "+stdPath+" folder of "+M16Institution3);
														}
														else {
															appLog.error("could not find path of std folder in "+M16Institution3);
															sa.assertTrue(false, "could not find path of std folder in "+M16Institution3);
														}
														if (click(driver, fp.getBackButtonFileDist2bOf2(30), "back button", action.BOOLEAN)) {
															if (fp.getFileDist2Of2(30)!=null) {
																appLog.info("file dist 2 of 2 is successfully displayed");
															}
															else {
																appLog.error("file dist 2 of 2 window is not present");
																sa.assertTrue(false, "file dist 2 of 2 window is not present");
															}
															if (click(driver, fp.getResolveIssuesFileDist2Of2(30), "resolve issues button", action.BOOLEAN)) {
																if (isDisplayed(driver, FindElement(driver, "//div[@id='BulkDocument_step_2bof2b']//div[@class='yellow_txt_box']", "to resolve the issues select folder(yellow text box)", action.BOOLEAN, 30),"visibility", 30, "to resolve the issues select folder(yellow text box)").getText().trim().contains(FundsPageErrorMessage.resolveIssueFileDistri2bOf2)) {
																	appLog.info("yellow text box text is successfully verified");
																}
																else {
																	appLog.error("yellow text box text is not verified for file distributor 2b of 2");
																	sa.assertTrue(false, "yellow text box text is not verified for file distributor 2b of 2");
																}
																click(driver, fp.getFileDist2bOf2CancelBtn(30), "cancel button file dist 2b of 2", action.SCROLLANDBOOLEAN);
																ThreadSleep(5000);
															}
															else {
																appLog.error("resolve issues button is not clickable");
																sa.assertTrue(false, "resolve issues button is not clickable");
																driver.close();
															}
														}
														else {
															appLog.error("back button 2bof 2 is not clickable");
															sa.assertTrue(false, "back button 2bof 2 is not clickable");
														}
													}
													else {
														appLog.error("cannot click on select folder link");
														sa.assertTrue(false, "cannot click on select folder link");
													}
												}
												else {
													appLog.error("cannot click on view link");
													sa.assertTrue(false, "cannot click on view link");
												}
											}
											else {
												appLog.error("resolve issues button is not clickable");
												sa.assertTrue(false, "resolve issues button is not clickable");
											}
										}
										else {
											appLog.error("next button is not clickable");
											sa.assertTrue(false, "next button is not clickable");
										}
									}
									else {
										appLog.error("std folder radio button is not clickable");
										sa.assertTrue(false, "std folder radio button is not clickable");
									}
								}
								else {
									appLog.error("could not drag and drop file");
									sa.assertTrue(false, "could not drag and drop file");
								}
							}
							driver.switchTo().window(firstWindow);
							switchToFrame(driver, 30, fp.getFrame(environment,mode, PageName.FundsPage, 30));
						}
						else {
							appLog.error("could not switch to window");
							sa.assertTrue(false, "could not switch to window");
						}
					}
					else {
						appLog.error("upload icon is not clickable");
						sa.assertTrue(false, "upload icon is not clickable");
					}
				}
				else {
					appLog.error("std path is not found in foldr structure");
					sa.assertTrue(false, "std path is not found in foldr structure");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M16FundName1+" is not found on funds page");
				sa.assertTrue(false, M16FundName1+" is not found on funds page");
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
	public void M16tc021_VerifyBulkUploadSuggestedNamingInvestor() {
		
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		String dropImage="BulkUpload.jpg";
		String folderpath1 = "UploadFiles\\Module16\\Bulk_File_Upload_4";
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String files[] = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.KeyWord_For_Search).split("<break>");
		String firstWindow = null,secondWindow = null;
		String date = getSystemDate("MM/dd/yyyy")+previousOrForwardDate(-1, "MM/dd/yyyy");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M16FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, M16Institution1, M16LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					if (click(driver, fp.getUploadIcon(Workspace.InvestorWorkspace, 30), "upload icon fundraising workspace", action.SCROLLANDBOOLEAN)) {
						firstWindow = switchOnWindow(driver);
						if (firstWindow!=null) {
							if (click(driver, fp.getBulkUploaderOrFileSplitterRadioButton(30), "radio button for bulk upload", action.BOOLEAN)) {
								if (fp.dragDropFiles(folderpath1, dropImage)) {
									ThreadSleep(5000);
									secondWindow = driver.getWindowHandle();
									if (click(driver, FindElement(driver, "//li[text()='All Folders']/ul/li[text()='"+stdPath+"']/input", "parent folder radio button", action.BOOLEAN, 10), "parent folder radio button", action.BOOLEAN)) {
										if (click(driver, fp.getFileDistNextBtn(30), "next button file dist", action.SCROLLANDBOOLEAN)) {
										if (fp.getAllFilesInSuccessFullyAppliedFileDist2Of2().size() == 4) {
											appLog.info("4 files are verified successfully");
										}
										else {
											appLog.error("no of files is wrong");
										}
										if (fp.getFileDist2Of2(30)!=null) {
											appLog.info("file dist 2 of 2 head is successfully displayed");
										}
										else {
											appLog.error("file dist 2 of 2 head is not displayed");
											sa.assertTrue(false, "file dist 2 of 2 head is not displayed");
										}
										for (int i = 0;i<2;i++)
										{
											saa = fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16LimitedPartner1, files[i], false, false, firstWindow, secondWindow, null);
											saa.combineAssertions(sa);
										}
										for (int i = 0;i<2;i++)
										{
											saa = fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16LimitedPartner2, files[i], false, false, firstWindow, secondWindow, null);
											saa.combineAssertions(sa);
										}
										//verifying issue ui
										List<WebElement> issueUI = fp.getIssueUiOnFileDistributor(30, true);
										if (!issueUI.isEmpty()) {
											if (getText(driver, issueUI.get(0), "issue ui", action.BOOLEAN).trim().contains("1"+FundsPageErrorMessage.issueUiFileDistributor)) {
												appLog.info("successfully verified issue UI of file distributor");
											}
											else {
												appLog.error("could not verify issue UI of file dist");
												sa.assertTrue(false, "could not verify issue UI of file dist");
											}
											System.err.println("content of "+files[0]);
											if (fp.findFileOnIssueUIFileDistributor(files[0],M16LimitedPartner3 ,true)!=null) {
											appLog.info("successfully found 1st file for issues");
											}
											else {
												appLog.error("could not find 1st file in issue window");
												sa.assertTrue(false, "could not find 1st file in issue window");
											}
											if (fp.findFileOnIssueUIFileDistributor(files[1], M16LimitedPartner3,true)!=null)
											appLog.info("successfully found 2nd file for issues");
											}
											else {
												appLog.error("could not find 2nd file in issue window");
												sa.assertTrue(false, "could not find 2nd file in issue window");
											}
										}
										
										//click on view link
										saa = fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16LimitedPartner2, files[1], true, false, firstWindow, secondWindow, null);
										saa.combineAssertions(sa);
										if (click(driver,fp.findFileOnIssueUIFileDistributor(files[1], M16LimitedPartner3,true) , "file of "+M16LimitedPartner3+" "+files[1], action.BOOLEAN)) {
											appLog.info("lolol");
											ThreadSleep(4000);
											Set<String> windows = driver.getWindowHandles();
											if (windows!=null) {
												for (String temp_window:windows) {
													if (!temp_window.equalsIgnoreCase(firstWindow)) {
														if (!temp_window.equalsIgnoreCase(secondWindow)) {
															driver.switchTo().window(temp_window);
															break;
														}
													}
												}
											}
											if (fp.verifyDownloadFunctionalityFileDistributorWithoutClick()) {
												appLog.info("successfully verified download and close button of file");
											}
											else {
												appLog.error("could not verify download and close button of file");
												sa.assertTrue(false, "could not verify download and close button of file");
											}
											driver.close();
											driver.switchTo().window(secondWindow);
										}
										appLog.info("clicking on resolve issue button");
										if (click(driver, fp.getResolveIssuesFileDist2Of2(30), "resolve issues link", action.BOOLEAN)) {
											if (click(driver, fp.getSelectFolderLinkForUnassignedFileName(fp.reverseFileNameForFileDistributor(files[0], M16LimitedPartner3)), "Select Folder Link", action.BOOLEAN)) {
												if (fp.clickonFolderStructureFileDistInvestor(M16Institution3+"/"+M16LimitedPartner3, stdPath)) {
													appLog.info("successfully select std folder for "+M16Institution3);
												}
												else {
													appLog.error("could not select std folder of "+M16Institution3);
													sa.assertTrue(false, "could not select std folder of "+M16Institution3);
												}
												
											}
											else {
												appLog.error("select folder link is not clickable");
												sa.assertTrue(false, "select folder link is not clickable");
											}
											if (click(driver, fp.getSelectFolderLinkForUnassignedFileName(fp.reverseFileNameForFileDistributor(files[1], M16LimitedPartner3)), "Select Folder Link", action.BOOLEAN)) {
												appLog.info("successfully clicked on select folder link of "+files[1]);
												if (fp.clickonFolderStructureFileDistInvestor(M16Institution3+"/"+M16LimitedPartner3, stdPath)) {
													appLog.info("successfully clicked on std folder of "+M16LimitedPartner3);
												}
												else {
													appLog.error("could not click on std folder of "+M16LimitedPartner3);
													sa.assertTrue(false, "could not click on std folder of "+M16LimitedPartner3);
												}
												
											}
											else {
												appLog.error("could not click select folder link of "+files[1]);
												sa.assertTrue(false, "could not click select folder link of "+files[1]);
											}
											ThreadSleep(7000);	
											if (click(driver, fp.getApplyButtonFileDist2bOf2(30), "apply button", action.BOOLEAN)) {
												ThreadSleep(5000);	
													//check issue column
												List<WebElement> issueUI = fp.getIssueUiOnFileDistributor(30, false);
												if (!issueUI.isEmpty()) {
													if (getText(driver, issueUI.get(0), "issue ui no of isues", action.BOOLEAN).trim().contains(FundsPageErrorMessage.noIssueFile)) {
														appLog.info("no issues found is successfully verified");
													}
													else {
														appLog.error("no issue found is not present on file dist 2b of 2");
														sa.assertTrue(false, "no issue found is not present on file dist 2b of 2");
													}
												}
													for (int i = 0;i<2;i++)
													{
														saa = fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16LimitedPartner1, files[i], false, false, null, null, null);
														saa.combineAssertions(sa);
													}
													for (int i = 0;i<2;i++)
													{
														saa = fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16LimitedPartner2, files[i], false, false, null, null, null);
														saa.combineAssertions(sa);
													}
													saa = fp.verifyFileDist2Of2SuccessfullyAppliedUIReverse(M16LimitedPartner3, files[1], false, false, null, null);
													sa.combineAssertions(saa);
													
													//clicking on remove link and reject
													saa = fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16LimitedPartner1, files[0], false, true, null, null, action.DECLINE);
													sa.combineAssertions(saa);
													//clicking on remove link and accept
													saa = fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16LimitedPartner1, files[0], false, true, null, null, action.ACCEPT);
													sa.combineAssertions(saa);
													if (fp.clickUsingCssSelectorPath("#lnlbuttonApply", "apply")) {
														//if (click(driver, fp.getFileDist2Of2ApplyBtn(30), "apply button", action.BOOLEAN)) {
														if (isAlertPresent(driver)) {
															String msg = switchToAlertAndGetMessage(driver, 10, action.GETTEXT);
															switchToAlertAndAcceptOrDecline(driver, 10, action.ACCEPT);
															if (msg.trim().equals(FundsPageErrorMessage.DocumentUploadSuccessMsg)) {
																appLog.info("successfully verified document upload success message");
															}
															else {
																appLog.error("document upload message could not be verified");
																sa.assertTrue(false, "document upload message could not be verified");
															}
														}
														else {
															appLog.error("could not find alert after clicking apply button");
															sa.assertTrue(false, "could not find alert after clicking apply button");
														}
										
													}
													else {
														appLog.error("apply button is not clickable");
														sa.assertTrue(false, "apply button is not clickable");
													}
												}
												else {
													appLog.error("apply button is not clickable");
													sa.assertTrue(false, "apply button is not clickable");
												}
										
										}
										else {
											appLog.error("resovle issues link is not clickable");
											sa.assertTrue(false, "resovle issues link is not clickable");
										}
									}
									else {
										appLog.error("std path folder radio button is not clickable");
										sa.assertTrue(false, "std path folder radio button is not clickable");
									}
								}
								else {
									appLog.error("could not drag and drop files for upload");
									sa.assertTrue(false, "could not drag and drop files for upload");
								}
							}
							else {
								appLog.error("could not find bulk upload radio button on upload window");
								sa.assertTrue(false, "could not find bulk upload radio button on upload window");
							}
							driver.switchTo().window(firstWindow);
							switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
						}
						if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approval icon", action.SCROLLANDBOOLEAN)) {
							if (selectVisibleTextFromDropDown(driver, fp.getManageApprovalsDropdown(ManageApprovalTabs.PendingDocuments, 10), "All Pending Documents", "All Pending Documents")) {
								appLog.info("selected all pending docs from manage approvals dropdown");
							}
							else {
								appLog.error("manage approvals dropdown does not have all pending documents");
								sa.assertTrue(false, "manage approvals dropdown does not have all pending documents");
							}
							for (int i = 0;i<2;i++) {
								if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments, M16LimitedPartner2+"_"+files[i], M16FundName1+" > "+M16Institution2+" > "+M16LimitedPartner2+" > "+stdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
									appLog.info("verified file for "+files[i]+" for "+M16Institution2);
								}
								else {
									appLog.error("could not verify file "+files[i]+" for "+M16Institution2);
									sa.assertTrue(false, "could not verify file "+files[i]+" for "+M16Institution2);
								}
								if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments, fp.reverseFileNameForFileDistributor(files[i], M16LimitedPartner3), M16FundName1+" > "+M16Institution3+" > "+M16LimitedPartner3+" > "+stdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
									appLog.info("verified file for "+files[i]+" for "+M16Institution3);
								}
								else {
									appLog.error("could not verify "+files[i]+" for "+M16Institution3);
									sa.assertTrue(false, "could not verify "+files[i]+" for "+M16Institution3);
								}
							}
							if (fp.verifyFilesPresentInManageApprovals(Workspace.InvestorWorkspace, ManageApprovalTabs.PendingDocuments, M16LimitedPartner1+"_"+files[1], M16FundName1+" > "+M16Institution1+" > "+M16LimitedPartner1+" > "+stdPath, "pending", CRMUser1FirstName+" "+CRMUser1LastName, Org1FirmName, date)) {
								appLog.info("verified file for "+files[1]+" for "+M16Institution1);
							}
							else {
								appLog.error("could not verify file "+files[1]+" for "+M16Institution1);
								sa.assertTrue(false, "could not verify file "+files[1]+" for "+M16Institution1);
							}
							
						}
						else {
							appLog.error("manage approval icon is not clickable");
							sa.assertTrue(false, "manage approval icon is not clickable");
						}
					}
					else {
						appLog.error("upload icon is not clickable");
						sa.assertTrue(false, "upload icon is not clickable");
					}
				}
				else {
					appLog.error("std path is not found in folder structure");
					sa.assertTrue(false, "std path is not found in folder structure");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("could not find fund of "+M16FundName1);
				sa.assertTrue(false, "could not find fund of "+M16FundName1);
			}
		}
		lp.CRMlogout();
		sa.assertAll();
									
	}

	@Test
	public void M16tc022_ApproveDocumentInvestors_Action() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String files[] = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M16tc021_VerifyBulkUploadSuggestedNamingInvestor", excelLabel.KeyWord_For_Search).split("<break>");
		
		String date = getSystemDate("MM/dd/yyyy")+previousOrForwardDate(-1, "MM/dd/yyyy");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M16FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30),"manage approval icon", action.SCROLLANDBOOLEAN)) {
					if (selectVisibleTextFromDropDown(driver, fp.getManageApprovalsDropdown(ManageApprovalTabs.PendingDocuments, 30), "All Pending Documents", "All Pending Documents")) {
						if (click(driver, fp.getManageApprovalsAllDocumentSelectCheckBox(30),"all documents checkbox", action.BOOLEAN)) {
							if (click(driver, fp.getApproveBtnManageApprovals(30), "approve button manage approvals", action.SCROLLANDBOOLEAN)) {
								if (click(driver, fp.getManageApprovalApproveYesOrNo(YesNo.Yes, 30), "approve yes button", action.BOOLEAN))
									if (click(driver, fp.getManageApprovalsApproveConfirmationCrossIcon(30), "cross icon", action.BOOLEAN)) {
										if (click(driver, fp.getCrossIconManageApp(30), "cross button manage approvals", action.SCROLLANDBOOLEAN)) {

										}
										else {
											appLog.error("approve cross icon is not clickable");
											sa.assertTrue(false, "approve cross icon is not clickable");
										}
									}
									else {
										appLog.error("manage aproval confirmation cross icon is not clickable");
										sa.assertTrue(false, "manage aproval confirmation cross icon is not clickable");
									}
							}
							else {
								appLog.error("approve button is not clickable");
								sa.assertTrue(false, "approve button is not clickable");
							}
						}
						else {
							appLog.error("all documents checkbox is not clickable");
							sa.assertTrue(false, "all documents checkbox is not clickable");
						}
					}
					else {
						appLog.error("all pending documents is not present");
						sa.assertTrue(false, "all pending documents is not present");
					}
				}
				else {
					appLog.error("manage approval icon is not clickable");
					sa.assertTrue(false, "manage approval icon is not clickable");
				}
				if (fp.verifyFolderPathdummy(stdPath, M16Institution1, M16LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.InvestorWorkspace, M16LimitedPartner1+"_"+files[1])) {
					appLog.info("successfully verified file on "+files[1]+" on "+M16Institution1);	
					}
					else {
						appLog.error("could not verify "+files[1]+" on "+M16Institution1);
						sa.assertTrue(false, "could not verify "+files[1]+" on "+M16Institution1);
					}
					if (fp.verifyDownloadFunctionality(PageName.FundsPage, Workspace.InvestorWorkspace,M16LimitedPartner1+"_"+files[1] , true,true,false)) {
						appLog.info("successfully verified download functionalit for "+M16LimitedPartner1+"_"+files[1]);
					}
					else {
						appLog.error("could not verify download functionality for "+M16LimitedPartner1+"_"+files[1]);
						sa.assertTrue(false, "could not verify download functionality for "+M16LimitedPartner1+"_"+files[1]);
					}
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				}
				else {
					appLog.error("std path is not found on "+M16Institution1);
					sa.assertTrue(false, "std path is not found on "+M16Institution1);
				}
				if (fp.verifyFolderPathdummy(stdPath, M16Institution2, M16LimitedPartner2, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.InvestorWorkspace, M16LimitedPartner2+"_"+files[0])) {
						appLog.info("successfully verified "+files[0]+" on "+M16Institution2);
					}
					else {
						appLog.error("could not verify "+files[0]+" on "+M16Institution2);
						sa.assertTrue(false, "could not verify "+files[0]+" on "+M16Institution2);
					}
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.InvestorWorkspace, M16LimitedPartner2+"_"+files[1])) {
						appLog.info("successfully verified "+files[1]+" on "+M16Institution2);
					}
					else {
						appLog.error("could not find "+files[1]+" on "+M16Institution2);
						sa.assertTrue(false, "could not find "+files[1]+" on "+M16Institution2);
					}
				}	
				if (fp.verifyFolderPathdummy(stdPath, M16Institution3, M16LimitedPartner3, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.InvestorWorkspace, fp.reverseFileNameForFileDistributor(files[0], M16LimitedPartner3))) {
						appLog.info("successfully verified "+files[0]+" on "+M16Institution3);
					}
					else {
						appLog.error("could not verify "+files[0]+" on "+M16Institution3);
						sa.assertTrue(false, "could not verify "+files[0]+" on "+M16Institution3);
					}
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.InvestorWorkspace, fp.reverseFileNameForFileDistributor(files[1], M16LimitedPartner3))) {
						appLog.info("successfully verified "+files[1]+" on "+M16Institution3);
					}
					else {
						appLog.error("could not verify "+files[1]+" on "+M16Institution3);
						sa.assertTrue(false, "could not verify "+files[1]+" on "+M16Institution3);
					}
				}
				else {
					appLog.error("std folder is not present in folder structure");
					sa.assertTrue(false, "std folder is not present in folder structure");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M16FundName1+" is not found on funds page");
				sa.assertTrue(false, M16FundName1+" is not found on funds page");
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
	public void M16tc022_ApproveDocumentInvestors_Impact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		AllFirmsPageBusinesslayer afp = new AllFirmsPageBusinesslayer(driver);
		SoftAssert saa = new SoftAssert();
		List<String> fail_list = new ArrayList<String>();
		
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String files[] = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M16tc021_VerifyBulkUploadSuggestedNamingInvestor", excelLabel.KeyWord_For_Search).split("<break>");
		
		String date = getSystemDate("MM/dd/yyyy")+previousOrForwardDate(-1, "MM/dd/yyyy");
		lp.investorLogin(M16Contact1EmailId, adminPassword);
		
		click(driver, ifp.getRecentActivitiesTab(30), "recent activities tab", action.BOOLEAN);
		fail_list=ifp.verifyAlertOnRecentAllDocumentGrid(M16LimitedPartner2+"_"+files[0]+"<break>"+M16LimitedPartner2+"_"+files[1]+"<break>"+fp.reverseFileNameForFileDistributor(files[0], M16LimitedPartner3)+"<break>"+fp.reverseFileNameForFileDistributor(files[1], M16LimitedPartner3)+"<break>"+M16LimitedPartner1+"_"+files[1], M16FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities, activityType.DocumentUpload, PageName.AllFirmsPage);
		if (fail_list.isEmpty()) {
			appLog.info("successfully verified all allerts on recent activities");
		}
		else {
			for (int i = 0;i<fail_list.size();i++) {
				appLog.error(fail_list.get(i)+" is not present on recent docs grid");
			}
			sa.assertTrue(false, "could not verify docs on recent activities grid");
		}
		
		click(driver, ifp.getAllDocumentsTab(30), "recent activities tab", action.BOOLEAN);
		fail_list=ifp.verifyAlertOnRecentAllDocumentGrid(M16LimitedPartner2+"_"+files[0]+"<break>"+M16LimitedPartner2+"_"+files[1]+"<break>"+fp.reverseFileNameForFileDistributor(files[0], M16LimitedPartner3)+"<break>"+fp.reverseFileNameForFileDistributor(files[1], M16LimitedPartner3)+"<break>"+M16LimitedPartner1+"_"+files[1], M16FundName1, investorSideWorkSpace.CurrentInvestment, TabName.AllDocuments, activityType.DocumentUpload, PageName.AllFirmsPage);
		if (fail_list.isEmpty()) {
			appLog.info("successfully verified all allerts on recent activities");
		}
		else {
			for (int i = 0;i<fail_list.size();i++) {
				appLog.error(fail_list.get(i)+" is not present on recent docs grid");
			}
			sa.assertTrue(false, "could not verify docs on recent activities grid");
		}
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			if (fp.verifyFolderPathdummy(stdPath, null, M16LimitedPartner1, null, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 30)) {
				saa=ifp.verifyContentGridInvestorSide(driver, PageName.FundsPage, M16LimitedPartner1+"_"+files[1], CRMUser1FirstName+" "+CRMUser1LastName,date);
				sa.combineAssertions(saa);
				
			}
			else {
				appLog.error("cannot find "+stdPath+" on folder structure");
				sa.assertTrue(false, "cannot find "+stdPath+" on folder structure");
			}
				
			if (fp.verifyFolderPathdummy(stdPath, null, M16LimitedPartner2, null, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 30)) {
				saa=ifp.verifyContentGridInvestorSide(driver, PageName.FundsPage, M16LimitedPartner2+"_"+files[0], CRMUser1FirstName+" "+CRMUser1LastName, date);
				sa.combineAssertions(saa);
				saa=ifp.verifyContentGridInvestorSide(driver, PageName.FundsPage, M16LimitedPartner2+"_"+files[1], CRMUser1FirstName+" "+CRMUser1LastName, date);
				sa.combineAssertions(saa);
				
			}
			else {
				appLog.error("cannot find "+stdPath+" on folder structure");
				sa.assertTrue(false, "cannot find "+stdPath+" on folder structure");
			}
			if (fp.verifyFolderPathdummy(stdPath, null, M16LimitedPartner3, null, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 30)) {
				
			saa=ifp.verifyContentGridInvestorSide(driver, PageName.FundsPage, fp.reverseFileNameForFileDistributor(files[0], M16LimitedPartner3), CRMUser1FirstName+" "+CRMUser1LastName, date);
			sa.combineAssertions(saa);
			saa=ifp.verifyContentGridInvestorSide(driver, PageName.FundsPage, fp.reverseFileNameForFileDistributor(files[1], M16LimitedPartner3), CRMUser1FirstName+" "+CRMUser1LastName, date);	
			sa.combineAssertions(saa);
			}
			else {
				appLog.error("std folder is not present in folder structure");
				sa.assertTrue(false, "std folder is not present in folder structure");
			}
			if (selectVisibleTextFromDropDown(driver, ifp.getFirmNameDropdownWRTPage(PageName.InvestorFirmPage), "All Firms", "All Firms")) {
				ThreadSleep(5000);
				fail_list=afp.verifyAlertsOnAllFirmsPage(M16LimitedPartner2+"_"+files[0]+"<break>"+M16LimitedPartner2+"_"+files[1]+"<break>"+fp.reverseFileNameForFileDistributor(files[0], M16LimitedPartner3)+"<break>"+fp.reverseFileNameForFileDistributor(files[1], M16LimitedPartner3)+"<break>"+M16LimitedPartner1+"_"+files[1], Org1FirmName, M16FundName1, investorSideWorkSpace.CurrentInvestment, activityType.DocumentUpload, PageName.AllFirmsPage); 				
				if (fail_list.isEmpty()) {
					appLog.info("successfully verified alerts on all firms page");
				}
				else {
					for (int i = 0;i<fail_list.size();i++) {
						appLog.error("content of not verified alerts is "+fail_list.get(i));
					}
					sa.assertTrue(false, "could not verify alerts on all firms page");
				}
				if (ifp.verifyDownloadFunctionality(PageName.AllFirmsPage, Workspace.InvestorWorkspace, M16LimitedPartner1+"_"+files[1], true, true, false)) { 
					appLog.info("succcessfully verified download functionality of file from all firms page");
				}
				else {
					appLog.error("download functionality could not be verified");
					sa.assertTrue(false, "download functionality could not be verified");
				}
			}
			else {
				appLog.error("could not find all firms option in dropdown");
				sa.assertTrue(false, "could not find all firms option in dropdown");
			}
		}
		else {
			appLog.error("could not click on potential investment tab");
			sa.assertTrue(false, "could not click on potential investment tab");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M16tc023_DeactivateManageApprovals() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		PartnershipPageBusinessLayer pp = new PartnershipPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
			if (np.deactivateManageApprovalsSetting()) {
				appLog.info("successfully deactivated manage approvals");
			}
			else {
				appLog.error("could not deactivate manage approvals");
				sa.assertTrue(false, "could not deactivate manage approvals");
			}
			switchToDefaultContent(driver);
			
		}
		lp.CRMlogout();
		sa.assertAll();
				
	}
	
	@Test
	public void M16tc024_VerifyDuplicateDocumentsPopup_Investors_Action() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		PartnershipPageBusinessLayer pp = new PartnershipPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);

		String dropImage="BulkUpload.jpg";
		String folderpath1 = "UploadFiles\\Module16\\Bulk_File_Upload_4";
		String folderpath2 = "UploadFiles\\Module16\\Testing";
		String parentID = null;
		List<String> duplicateDocs;
		List<String> folderLoc;
		String files[] = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M16tc021_VerifyBulkUploadSuggestedNamingInvestor", excelLabel.KeyWord_For_Search).split("<break>");

		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String date = getSystemDate("MM/dd/yyyy")+previousOrForwardDate(-1, "MM/dd/yyyy");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M16FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, M16Institution1, M16LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					if (click(driver, fp.getUploadIcon(Workspace.InvestorWorkspace, 30), "upload icon", action.BOOLEAN)) {
						parentID = switchOnWindow(driver);
						if (parentID!=null) {
							if (click(driver, fp.getBulkUploaderOrFileSplitterRadioButton(30), "bulk upload or file splitter radio button", action.BOOLEAN)) {
								if (fp.dragDropFiles(folderpath1, dropImage)) {
									ThreadSleep(5000);
									if (click(driver, FindElement(driver, "//li[text()='All Folders']/ul/li[text()='"+stdPath+"']/input", "parent folder radio button", action.BOOLEAN, 10), "parent folder radio button", action.BOOLEAN)) {
										if (click(driver, fp.getFileDistNextBtn(30), "file dist next button", action.BOOLEAN)) {
											ThreadSleep(5000);
											if (click(driver, fp.getResolveIssuesFileDist2Of2(30), "resolve issues button", action.BOOLEAN)) {
												if (click(driver, fp.getSelectFolderLinkForUnassignedFileName(fp.reverseFileNameForFileDistributor(files[0], M16LimitedPartner3)), "select folder link", action.BOOLEAN)) {
												}
												fp.clickonFolderStructureFileDistInvestor(M16Institution3+"/"+M16LimitedPartner3, stdPath);
												if (click(driver, fp.getApplyButtonFileDist2bOf2(30), "apply button", action.BOOLEAN)) {
													if (click(driver, fp.getFileDist2Of2ApplyBtn(30), "apply button", action.BOOLEAN)) {
														List<String> a = fp.verifyDuplicateDocWindowWithoutUpload(Workspace.InvestorWorkspace, 
																M16LimitedPartner1+"_"+files[1]+"<break>"+M16LimitedPartner2+"_"+files[0]+"<break>"+
																		M16LimitedPartner2+"_"+files[1]+"<break>"+fp.reverseFileNameForFileDistributor(files[0], M16LimitedPartner3), 
																		M16FundName1+" > "+M16Institution1+" > "+M16LimitedPartner1+" > "+stdPath+"<break>"+
																				M16FundName1+" > "+M16Institution2+" > "+M16LimitedPartner2+" > "+stdPath+"<break>"+
																				M16FundName1+" > "+M16Institution2+" > "+M16LimitedPartner2+" > "+stdPath+"<break>"+
																				M16FundName1+" > "+M16Institution3+" > "+M16LimitedPartner3+" > "+stdPath);
														if (a.isEmpty()) {
															appLog.info("successfully verified documents on duplicate doc window");
														}
														else {
															for (int i =0;i<a.size();i++) {
																appLog.error(a.get(i)+" is not matching with duplicate doc window");
															}
															sa.assertTrue(false, "could not verify duplicate doc window");
														}
													}
													click(driver, fp.getIgnoreAllButton(30), "ignore all button", action.BOOLEAN);

												}
												else {
													appLog.error("apply button for 2b of 2 is not clickable");
													sa.assertTrue(false, "apply button for 2b of 2 is not clickable");
												}
											}
											else {
												appLog.error("resolve issues button is not clickable");
												sa.assertTrue(false, "resolve issues button is not clickable");
											}
										}
										else {
											appLog.error("next button is not clickable");
											sa.assertTrue(false, "next button is not clickable");
										}
									}
									else {
										appLog.error("std radio button is not clickable");
										sa.assertTrue(false, "std radio button is not clickable");
									}
								}
								else {
									appLog.error("drag and drop of file is not possible");
									sa.assertTrue(false, "drag and drop of file is not possible");
								}
							}
							else {
								appLog.error("bulk upload button is not clickable");
								sa.assertTrue(false, "bulk upload button is not clickable");
							}
							driver.switchTo().window(parentID);
							switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));


						}
						else {
							appLog.error("could not find child window to switch");
							sa.assertTrue(false, "could not find child window to switch");
						}
					}
					else {
						appLog.error("upload icon is not clickable");
						sa.assertTrue(false, "upload icon is not clickable");
					}
					if (click(driver, fp.getUploadIcon(Workspace.InvestorWorkspace, 30), "upload icon", action.BOOLEAN)) {
						parentID = switchOnWindow(driver);
						if (parentID!=null) {

							if (click(driver, fp.getBulkUploaderOrFileSplitterRadioButton(30), "bulk upload or file splitter radio button", action.BOOLEAN)) {
								if (fp.dragDropFiles(folderpath1, dropImage)) {
									ThreadSleep(5000);
									if (click(driver, FindElement(driver, "//li[text()='All Folders']/ul/li[text()='"+stdPath+"']/input", "parent folder radio button", action.BOOLEAN, 10), "parent folder radio button", action.BOOLEAN)) {
										if (click(driver, fp.getFileDistNextBtn(30), "file dist next button", action.BOOLEAN)) {
											ThreadSleep(5000);
											if (click(driver, fp.getResolveIssuesFileDist2Of2(30), "resolve issues button", action.BOOLEAN)) {
												if (click(driver, fp.getSelectFolderLinkForUnassignedFileName(fp.reverseFileNameForFileDistributor(files[0], M16LimitedPartner3)), "select folder link", action.BOOLEAN)) {
												}
												fp.clickonFolderStructureFileDistInvestor(M16Institution3+"/"+M16LimitedPartner3, stdPath);
												if (click(driver, fp.getApplyButtonFileDist2bOf2(30), "apply button", action.BOOLEAN)) {
													if (click(driver, fp.getFileDist2Of2ApplyBtn(30), "apply button", action.BOOLEAN)) {
													}
													else {
														appLog.error("apply button 2 of 2 is not clickable");
														sa.assertTrue(false, "apply button 2 of 2 is not clickable");
													}
													fp.clickUsingCssSelectorPath("#lnkReplaceAll", "update all");
													//click(driver, fp.getUpdateAllButton(30), "update all button", action.BOOLEAN);
													if (isAlertPresent(driver)) {
														String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
														switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
														if (msg.contains(FundsPageErrorMessage.DocumentUploadSuccessMsg)) {
															appLog.info("document uploaded successfully");
														}
														else {
															appLog.error("could not find document upload success message");
															sa.assertTrue(false, "could not find document upload success message");
														}
													}
													else {
														appLog.error("no alert is present after clicking on update all button");
														sa.assertTrue(false, "no alert is present after clicking on update all button");
													}
												}
												else {
													appLog.error("apply button for 2b of 2 is not clickable");
													sa.assertTrue(false, "apply button for 2b of 2 is not clickable");
												}
											}
											else {
												appLog.error("resolve issues button is not clickable");
												sa.assertTrue(false, "resolve issues button is not clickable");
											}
										}
										else {
											appLog.error("next button is not clickable");
											sa.assertTrue(false, "next button is not clickable");
										}
									}
									else {
										appLog.error("std radio button is not clickable");
										sa.assertTrue(false, "std radio button is not clickable");
									}
								}
								else {
									appLog.error("drag and drop of file is not possible");
									sa.assertTrue(false, "drag and drop of file is not possible");
								}
							}
							else {
								appLog.error("bulk upload button is not clickable");
								sa.assertTrue(false, "bulk upload button is not clickable");
							}
							driver.switchTo().window(parentID);
							switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
						}
						else {
							appLog.error("cannot find child window to switch");
							sa.assertTrue(false, "cannot find child window to switch");
						}
					}
					else {
						appLog.error("upload icon is not clickable");
						sa.assertTrue(false, "upload icon is not clickable");
					}
					if (fp.verifyFolderPathdummy(stdPath, M16Institution1, M16LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
						if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.InvestorWorkspace,M16LimitedPartner1+"_"+files[0])) {
							appLog.info("successfully verified "+files[0]+" in institution1");
						}
						else {
							appLog.error("could not verify "+files[0]+" in institution1");
							sa.assertTrue(false, "could not verify "+files[0]+" in institution1");
						}
						if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.InvestorWorkspace,M16LimitedPartner1+"_"+files[1])) {
							appLog.info("successfully verified "+files[0]+" in institution2");
						}
						else {
							appLog.error("could not verify "+files[0]+" in institution2");
							sa.assertTrue(false, "could not verify "+files[0]+" in institution2");
						}
					}
					else {
						appLog.error(stdPath+" is not found in folder structure");
						sa.assertTrue(false, stdPath+" is not found in folder structure");
					}
				}
				else {
					appLog.error("could not find child window to switch");
					sa.assertTrue(false, "could not find child window to switch");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("upload window is not clickable");
				sa.assertTrue(false, "upload window is not clickable");
			}
			lp.CRMlogout();
			sa.assertAll();
		}
	}

	@Test
	public void M16tc024_VerifyDuplicateDocumentsPopup_Investors_Impact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		AllFirmsPageBusinesslayer afp = new AllFirmsPageBusinesslayer(driver);
		List<String> fail_list = new ArrayList<String>();
		
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M16tc021_VerifyBulkUploadSuggestedNamingInvestor", excelLabel.StandardPath);
		String files[] = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M16tc021_VerifyBulkUploadSuggestedNamingInvestor", excelLabel.KeyWord_For_Search).split("<break>");
		
		lp.investorLogin(M16Contact1EmailId, adminPassword);
		click(driver, ifp.getRecentActivitiesTab(30), "recent activities tab", action.BOOLEAN);
		fail_list=ifp.verifyAlertOnRecentAllDocumentGrid(M16LimitedPartner1+"_"+files[0], M16FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities, activityType.DocumentUpload, PageName.AllFirmsPage);
		if (fail_list.isEmpty()) {
			appLog.info("successfully verified all allerts on recent activities");
		}
		else {
			for (int i = 0;i<fail_list.size();i++) {
				appLog.error(fail_list.get(i)+" is not present on recent docs grid");
			}
			sa.assertTrue(false, "could not verify docs on recent activities grid");
		}
		fail_list=ifp.verifyAlertOnRecentAllDocumentGrid(M16LimitedPartner1+"_"+files[1]+"<break>"+M16LimitedPartner2+"_"+files[0]+"<break>"+M16LimitedPartner2+"_"+files[1]+"<break>"+fp.reverseFileNameForFileDistributor(files[0], M16LimitedPartner3), M16FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities, activityType.DocumentUpdate, PageName.AllFirmsPage);
		if (fail_list.isEmpty()) {
			appLog.info("successfully verified all allerts on recent activities");
		}
		else {
			for (int i = 0;i<fail_list.size();i++) {
				appLog.error(fail_list.get(i)+" is not present on recent docs grid");
			}
			sa.assertTrue(false, "could not verify docs on recent activities grid");
		}
		
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M16tc025_ChangeBulkUploadSettingsInvestor_Action() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		String parentID = null;
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String files[] = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M16tc021_VerifyBulkUploadSuggestedNamingInvestor", excelLabel.KeyWord_For_Search).split("<break>");
		SoftAssert saa = new SoftAssert();
		String folderpath1 = "UploadFiles\\Module16\\Bulk_File_Upload_4";
		String dropImage="BulkUpload.jpg";
		List<String> fail_list = null;

		lp.CRMLogin(superAdminUserName, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, np.getFrame(environment,mode, PageName.NavatarInvestorManager, 30));
			if (np.clickOnSideMenusTab(sideMenu.FileDistributorSettings)) {
				if (click(driver, np.getReverseNamingRadioButton(30), "reverse naming radio button", action.BOOLEAN)) {
					if (click(driver,np.getFileDistributorSaveButton(fileDistributor.BulkUpload), "save button", action.SCROLLANDBOOLEAN)) {
						if (isSelected(driver,np.getReverseNamingRadioButton(30),"reverse naming radio button") ) {
							appLog.info("successfully saved reverse naaming radio button");
						}
						else {
							appLog.error("could not save reverse naming radio button");
							sa.assertTrue(false, "could not save reverse naming radio button");
						}
						if (!isSelected(driver,np.getUseSuggestedNaming(30), "suggested naming radio button")) {
							appLog.info("successfully deselected suggested naming radio button");
						}
						else {
							appLog.error("suggested naming radio button is still selected");
							sa.assertTrue(false, "suggested naming radio button is still selected");
						}
					}
					else {
						appLog.error("save button on file distributor is not clickable");
						sa.assertTrue(false, "save button on file distributor is not clickable");
					}
				}
				else {
					appLog.error("reverse naming radio button is not clickable");
					sa.assertTrue(false, "reverse naming radio button is not clickable");
				}
			}
			switchToDefaultContent(driver);
		}
		lp.CRMlogout();
		ThreadSleep(10000);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M16FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, M16Institution1, M16LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					if (click(driver,fp.getUploadIcon(Workspace.InvestorWorkspace, 30), "upload icon fund/ws", action.BOOLEAN)) {
								parentID = switchOnWindow(driver);
								if (parentID!=null) {
									if (click(driver, fp.getBulkUploaderOrFileSplitterRadioButton(30), "bulk upload or file splitter radio button", action.BOOLEAN)) {
										if (fp.dragDropFiles(folderpath1, dropImage)) {
											ThreadSleep(5000);
											if (click(driver, FindElement(driver, "//li[text()='All Folders']/ul/li[text()='"+stdPath+"']/input", "parent folder radio button", action.BOOLEAN, 10), "parent folder radio button", action.BOOLEAN)) {
												if (click(driver, fp.getFileDistNextBtn(30), "file dist next button", action.BOOLEAN)) {
													if (fp.findFileOnIssueUIFileDistributor(files[0], M16LimitedPartner1, false)!=null) {
														appLog.info("successfully found "+files[0]+" of "+M16LimitedPartner1);
													}
													else {
														appLog.error("could not find "+files[0]+" of "+M16LimitedPartner1);
														sa.assertTrue(false, "could not find "+files[0]+" of "+M16LimitedPartner1);
													}
													if (fp.findFileOnIssueUIFileDistributor(files[1], M16LimitedPartner1, false)!=null) {
														appLog.info("successfully found "+files[1]+" of "+M16LimitedPartner1);
													}
													else {
														appLog.error("could not find "+files[1]+" of "+M16LimitedPartner1);
														sa.assertTrue(false, "could not find "+files[1]+" of "+M16LimitedPartner1);
													}
													if (fp.findFileOnIssueUIFileDistributor(files[0], M16LimitedPartner2, false)!=null) {
														appLog.info("successfully found "+files[0]+" of "+M16LimitedPartner2);
													}
													else {
														appLog.error("could not find "+files[0]+" of "+M16LimitedPartner2);
														sa.assertTrue(false, "could not find "+files[0]+" of "+M16LimitedPartner2);
													}
													if (fp.findFileOnIssueUIFileDistributor(files[1], M16LimitedPartner2, false)!=null) {
														appLog.info("successfully found "+files[1]+" of "+M16LimitedPartner2);
													}
													else {
														appLog.error("could not find "+files[1]+" of "+M16LimitedPartner2);
														sa.assertTrue(false, "could not find "+files[1]+" of "+M16LimitedPartner2);
													}
													for (int i = 0;i<2;i++) {
													saa=fp.verifyFileDist2Of2SuccessfullyAppliedUIReverse(M16LimitedPartner3, files[i], false, false, null, null);
													sa.combineAssertions(saa);
													
													}
													
													if (click(driver, fp.getResolveIssuesFileDist2Of2(30), "resolve issues link", action.BOOLEAN)) {
														if (click(driver, fp.getSelectFolderLinkForUnassignedFileName(M16LimitedPartner2+"_"+files[0]), "select folder link", action.BOOLEAN)) {
															if (fp.clickonFolderStructureFileDistInvestor(M16Institution2+"/"+M16LimitedPartner2, stdPath)) {
																appLog.info("successfully clicked on radio button of "+stdPath);
															}
															else {
																appLog.error("could not find radio button of "+stdPath+" and inst "+M16LimitedPartner2);
																sa.assertTrue(false, "could not find radio button of "+stdPath+" and inst "+M16LimitedPartner2);
															}
															ThreadSleep(2000);
														}
														if (click(driver, fp.getSelectFolderLinkForUnassignedFileName(M16LimitedPartner1+"_"+files[1]), "select folder link", action.BOOLEAN)) {
															if (fp.clickonFolderStructureFileDistInvestor(M16Institution1+"/"+M16LimitedPartner1, stdPath)) {
																appLog.info("successfully clicked on radio button of "+stdPath);
															}
															else {
																appLog.error("could not find radio button of "+stdPath+" and inst "+M16LimitedPartner1);
																sa.assertTrue(false, "could not find radio button of "+stdPath+" and inst "+M16LimitedPartner1);
															}
															ThreadSleep(2000);
														}
														if (click(driver, fp.getApplyButtonFileDist2bOf2(30), "file dist apply button", action.BOOLEAN)) {
															ThreadSleep(4000);
															saa=fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16LimitedPartner2, files[0], false, false, null, null,null);
															sa.combineAssertions(saa);
															saa=fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16LimitedPartner1, files[1], false, false, null, null,null);
															sa.combineAssertions(saa);

															for (int i = 0;i<2;i++) {
																saa=fp.verifyFileDist2Of2SuccessfullyAppliedUIReverse(M16LimitedPartner3, files[i], false, false, null, null);
																sa.combineAssertions(saa);

															}
															//issue ui 2 files
															if(fp.findFileOnIssueUIFileDistributor(files[0], M16LimitedPartner1, false)!=null) {
																appLog.info("successfully verified "+files[0] + " on "+M16LimitedPartner1);
															}
															else {
																appLog.error("could not verify "+files[0]+" on "+M16LimitedPartner1);
																sa.assertTrue(false, "could not verify "+files[0]+" on "+M16LimitedPartner1);
															}
															if(fp.findFileOnIssueUIFileDistributor(files[1], M16LimitedPartner2, false)!=null) {
																appLog.info("successfully verified "+files[1] + " on "+M16LimitedPartner2);
															}
															else {
																appLog.error("could not verify "+files[1]+" on "+M16LimitedPartner2);
																sa.assertTrue(false, "could not verify "+files[1]+" on "+M16LimitedPartner2);
															}
															if(click(driver, fp.getFileDist2Of2ApplyBtn(30),"apply button", action.BOOLEAN)) {
																fail_list=fp.verifyDuplicateDocWindowWithoutUpload(Workspace.InvestorWorkspace, M16LimitedPartner1+"_"+files[1]+"<break>"+M16LimitedPartner2+"_"+files[0], M16FundName1+" > "+M16Institution1+" > "+M16LimitedPartner1+" > "+stdPath+"<break>"+M16FundName1+" > "+M16Institution2+" > "+M16LimitedPartner2+" > "+stdPath);
																if (fail_list.isEmpty()) {
																	appLog.info("successfully verified duplicate docs window with 2 files");
																}
																else {
																	for (int i = 0;i<fail_list.size();i++) {
																		appLog.error(fail_list.get(0)+" was not found in duplicate doc window");
																	}
																	sa.assertTrue(false, "could not find files on duplicate doc window");
																}
																if (fp.clickUsingCssSelectorPath("#lnkReplaceAll", "update all")) {
																	//if (click(driver, fp.getUpdateAllButton(30), "update all button", action.BOOLEAN)) {
																	if (isAlertPresent(driver)) {
																		String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
																		switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
																		if (msg.trim().contains(FundsPageErrorMessage.DocumentUploadSuccessMsg)) {
																			appLog.info("document upload alert is successfully verified");
																		}
																		else {
																			appLog.error("could not verify document upload alert");
																			sa.assertTrue(false, "could not verify document upload alert");
																		}
																	}
																	else {
																		appLog.error("could not find alert after updating all docs");
																		sa.assertTrue(false, "could not find alert after updating all docs");
																	}
																}
																else {
																	appLog.error("update all button is not clickable");
																	sa.assertTrue(false, "update all button is not clickable");
																}
															}
															else {
																appLog.error("apply 2b of 2 button is not clickable");
																sa.assertTrue(false, "apply 2b of 2 button is not clickable");
															}
														}
														else {
															appLog.error("apply 2b of 2 button is not clickable");
															sa.assertTrue(false, "apply 2b of 2 button is not clickable");
														}

													}
													else {
														appLog.error("resolve issue button is not clickable");
														sa.assertTrue(false, "resolve issue button is not clickable");
													}
														
												}
												else {
													appLog.error("next button is not clickable on file dist 2of 2");
													sa.assertTrue(false, "next button is not clickable on file dist 2of 2");
												}
											}
											else {
												appLog.error("stdpath radio button is not found on file dist window");
												sa.assertTrue(false, "stdpath radio button is not found on file dist window");
											}
										}
										else {
											appLog.error("drag and drop of files is not successful");
											sa.assertTrue(false, "drag and drop of files is not successful");
										}
									}
									else {
										appLog.error("bulk upload or file splitter radio button on upload window is not clickable");
										sa.assertTrue(false, "bulk upload or file splitter radio button on upload window is not clickable");
									}
									driver.switchTo().window(parentID);
									switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
								}
								else {
									appLog.error("could not find new window to switch");
									sa.assertTrue(false, "could not find new window to switch");
								}
						
						}
						else {
							appLog.error(stdPath+" is not found in folder structure");
							sa.assertTrue(false, stdPath+" is not found in folder structure");
						}
						appLog.info("verifying updated files on folders");
						//verifying 6
						if (fp.verifyFolderPathdummy(stdPath, M16Institution3, M16LimitedPartner3, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
							if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.InvestorWorkspace, fp.reverseFileNameForFileDistributor(files[0], M16LimitedPartner3))) {
								appLog.info("successfully verified "+files[0]+" on "+M16LimitedPartner3);
							}
							else {
								appLog.error("could not verify "+files[0]+" on "+M16LimitedPartner3);
								sa.assertTrue(false, "could not verify "+files[0]+" on "+M16LimitedPartner3);
							}
							if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.InvestorWorkspace, fp.reverseFileNameForFileDistributor(files[1], M16LimitedPartner3))) {
								appLog.info("successfully verified "+files[1]+" on "+M16LimitedPartner3);
							}
							else {
								appLog.error("could not verify "+files[1]+" on "+M16LimitedPartner3);
								sa.assertTrue(false, "could not verify "+files[1]+" on "+M16LimitedPartner3);
							}
						}
						if (fp.verifyFolderPathdummy(stdPath, M16Institution2, M16LimitedPartner2, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
							if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.InvestorWorkspace, M16LimitedPartner2+"_"+files[0])) {
								appLog.info("successfully verified "+files[0]+" on "+M16LimitedPartner2);
							}
							else {
								appLog.error("could not verify "+files[0]+" on "+M16LimitedPartner2);
								sa.assertTrue(false, "could not verify "+files[0]+" on "+M16LimitedPartner2);
							}
							if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.InvestorWorkspace, M16LimitedPartner2+"_"+files[1])) {
								appLog.info("successfully verified "+files[1]+" on "+M16Institution2);
							}
							else {
								appLog.error("could not verify "+files[1]+" on "+M16Institution2);
								sa.assertTrue(false, "could not verify "+files[1]+" on "+M16Institution2);
							}
						}
						if (fp.verifyFolderPathdummy(stdPath, M16Institution1, M16LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
							if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.InvestorWorkspace, M16LimitedPartner1+"_"+files[0])) {
								appLog.info("successfully verified "+files[0]+" on "+M16LimitedPartner1);
							}
							else {
								appLog.error("could not verify "+files[0]+" on "+M16LimitedPartner1);
								sa.assertTrue(false, "could not verify "+files[0]+" on "+M16LimitedPartner1);
							}
							if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.InvestorWorkspace, M16LimitedPartner1+"_"+files[1])) {
								appLog.info("successfully verified "+files[1]+" on "+M16LimitedPartner1);
							}
							else {
								appLog.error("could not verify "+files[1]+" on "+M16LimitedPartner1);
								sa.assertTrue(false, "could not verify "+files[1]+" on "+M16LimitedPartner1);
							}
						}
				}else {
					appLog.error(stdPath+" is not found in folder structure");
					sa.assertTrue(false, stdPath+" is not found in folder structure");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("could not find fund on funds tab");
				sa.assertTrue(false, "could not find fund on funds tab");
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
	public void M16tc025_ChangeBulkUploadSettingsInvestor_ImpactCRM() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String files[] = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M16tc021_VerifyBulkUploadSuggestedNamingInvestor", excelLabel.KeyWord_For_Search).split("<break>");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		
		if (bp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M16Contact1FirstName,M16Contact1LastName, null)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.ContactsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, M16LimitedPartner1, null, M16FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.ContactsPage, Workspace.InvestorWorkspace, M16LimitedPartner1+"_"+files[0])) {
						appLog.info("successfully verified "+files[0]+" on "+M16LimitedPartner1);
					}
					else {
						appLog.error("could not verify "+files[0]+" on "+M16LimitedPartner1);
						sa.assertTrue(false, "could not verify "+files[0]+" on "+M16LimitedPartner1);
					}
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.ContactsPage, Workspace.InvestorWorkspace, M16LimitedPartner1+"_"+files[1])) {
						appLog.info("successfully verified "+files[1]+" on "+M16LimitedPartner1);
					}
					else {
						appLog.error("could not verify "+files[1]+" on "+M16LimitedPartner1);
						sa.assertTrue(false, "could not verify "+files[1]+" on "+M16LimitedPartner1);
					}
				}
				if (fp.verifyFolderPathdummy(stdPath, M16LimitedPartner2, null, M16FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.ContactsPage, Workspace.InvestorWorkspace, M16LimitedPartner2+"_"+files[0])) {
						appLog.info("successfully verified "+files[0]+" on "+M16LimitedPartner2);
					}
					else {
						appLog.error("could not verify "+files[0]+" on "+M16LimitedPartner2);
						sa.assertTrue(false, "could not verify "+files[0]+" on "+M16LimitedPartner2);
					}
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.ContactsPage, Workspace.InvestorWorkspace, M16LimitedPartner2+"_"+files[1])) {
						appLog.info("successfully verified "+files[1]+" on "+M16LimitedPartner2);
					}
					else {
						appLog.error("could not verify "+files[1]+" on "+M16LimitedPartner2);
						sa.assertTrue(false, "could not verify "+files[1]+" on "+M16LimitedPartner2);
					}
				}
				if (fp.verifyFolderPathdummy(stdPath, M16LimitedPartner3, null, M16FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.ContactsPage, Workspace.InvestorWorkspace, fp.reverseFileNameForFileDistributor(files[0], M16LimitedPartner3))) {
						appLog.info("successfully verified "+files[0]+" on "+M16LimitedPartner3);
					}
					else {
						appLog.error("could not verify "+files[0]+" on "+M16LimitedPartner3);
						sa.assertTrue(false, "could not verify "+files[0]+" on "+M16LimitedPartner3);
					}
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.ContactsPage, Workspace.InvestorWorkspace, fp.reverseFileNameForFileDistributor(files[1], M16LimitedPartner3))) {
						appLog.info("successfully verified "+files[1]+" on "+M16LimitedPartner3);
					}
					else {
						appLog.error("could not verify "+files[1]+" on "+M16LimitedPartner3);
						sa.assertTrue(false, "could not verify "+files[1]+" on "+M16LimitedPartner3);
					}
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("could not find contact on contact page");
				sa.assertTrue(false, "could not find contact on contact page");
			}
		}
		else {
			appLog.error("contact tab is not clickable");
			sa.assertTrue(false, "contact tab is not clickable");
		}
		//inst
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M16Institution1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.InstitutionsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, null, M16LimitedPartner1, M16FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.InstitutionsPage, Workspace.InvestorWorkspace, M16LimitedPartner1+"_"+files[0])) {
						appLog.info("successfully verified "+files[0]+" on "+M16LimitedPartner1);
					}
					else {
						appLog.error("could not verify "+files[0]+" on "+M16LimitedPartner1);
						sa.assertTrue(false, "could not verify "+files[0]+" on "+M16LimitedPartner1);
					}
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.InstitutionsPage, Workspace.InvestorWorkspace, M16LimitedPartner1+"_"+files[1])) {
						appLog.info("successfully verified "+files[1]+" on "+M16LimitedPartner1);
					}
					else {
						appLog.error("could not verify "+files[1]+" on "+M16LimitedPartner1);
						sa.assertTrue(false, "could not verify "+files[1]+" on "+M16LimitedPartner1);
					}
				}
				switchToDefaultContent(driver);
			}
				else {
					appLog.error("could not find institution on inst page");
					sa.assertTrue(false, "could not find institution on inst page");
				}
			}
			else {
				appLog.error("inst tab is not clickable");
				sa.assertTrue(false, "inst tab is not clickable");
			}
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M16Institution2)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.InstitutionsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, null, M16LimitedPartner2, M16FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.InstitutionsPage, Workspace.InvestorWorkspace, M16LimitedPartner2+"_"+files[0])) {
						appLog.info("successfully verified "+files[0]+" on "+M16LimitedPartner2);
					}
					else {
						appLog.error("could not verify "+files[0]+" on "+M16LimitedPartner2);
						sa.assertTrue(false, "could not verify "+files[0]+" on "+M16LimitedPartner2);
					}
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.InstitutionsPage, Workspace.InvestorWorkspace, M16LimitedPartner2+"_"+files[1])) {
						appLog.info("successfully verified "+files[1]+" on "+M16LimitedPartner2);
					}
					else {
						appLog.error("could not verify "+files[1]+" on "+M16LimitedPartner2);
						sa.assertTrue(false, "could not verify "+files[1]+" on "+M16LimitedPartner2);
					}
				}
				switchToDefaultContent(driver);
			}
				else {
					appLog.error("could not find institution on inst page");
					sa.assertTrue(false, "could not find institution on inst page");
				}
			}
			else {
				appLog.error("inst tab is not clickable");
				sa.assertTrue(false, "inst tab is not clickable");
			}
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M16Institution3)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.InstitutionsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, null, M16LimitedPartner3, M16FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.InstitutionsPage, Workspace.InvestorWorkspace, fp.reverseFileNameForFileDistributor(files[0], M16LimitedPartner3))) {
						appLog.info("successfully verified "+files[0]+" on "+M16Institution3);
					}
					else {
						appLog.error("could not verify "+files[0]+" on "+M16Institution3);
						sa.assertTrue(false, "could not verify "+files[0]+" on "+M16Institution3);
					}
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.InstitutionsPage, Workspace.InvestorWorkspace, fp.reverseFileNameForFileDistributor(files[1], M16LimitedPartner3))) {
						appLog.info("successfully verified "+files[1]+" on "+M16Institution3);
					}
					else {
						appLog.error("could not verify "+files[1]+" on "+M16Institution3);
						sa.assertTrue(false, "could not verify "+files[1]+" on "+M16Institution3);
					}
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("could not find institution on inst page");
				sa.assertTrue(false, "could not find institution on inst page");
			}
		}
		else {
			appLog.error("inst tab is not clickable");
			sa.assertTrue(false, "inst tab is not clickable");
		}
		
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedLP(M16LimitedPartner1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.InstitutionsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, null, null, M16FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.InstitutionsPage, Workspace.InvestorWorkspace, M16LimitedPartner1+"_"+files[0])) {
						appLog.info("successfully verified "+files[0]+" on "+M16LimitedPartner1);
					}
					else {
						appLog.error("could not verify "+files[0]+" on "+M16LimitedPartner1);
						sa.assertTrue(false, "could not verify "+files[0]+" on "+M16LimitedPartner1);
					}
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.InstitutionsPage, Workspace.InvestorWorkspace, M16LimitedPartner1+"_"+files[1])) {
						appLog.info("successfully verified "+files[1]+" on "+M16LimitedPartner1);
					}
					else {
						appLog.error("could not verify "+files[1]+" on "+M16LimitedPartner1);
						sa.assertTrue(false, "could not verify "+files[1]+" on "+M16LimitedPartner1);
					}
				}
				switchToDefaultContent(driver);
			}
				else {
					appLog.error("could not find institution on inst page");
					sa.assertTrue(false, "could not find institution on inst page");
				}
			}
			else {
				appLog.error("inst tab is not clickable");
				sa.assertTrue(false, "inst tab is not clickable");
			}
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedLP(M16LimitedPartner2)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.InstitutionsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, null, null, M16FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.InstitutionsPage, Workspace.InvestorWorkspace, M16LimitedPartner2+"_"+files[0])) {
						appLog.info("successfully verified "+files[0]+" on "+M16LimitedPartner2);
					}
					else {
						appLog.error("could not verify "+files[0]+" on "+M16LimitedPartner2);
						sa.assertTrue(false, "could not verify "+files[0]+" on "+M16LimitedPartner2);
					}
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.InstitutionsPage, Workspace.InvestorWorkspace, M16LimitedPartner2+"_"+files[1])) {
						appLog.info("successfully verified "+files[1]+" on "+M16LimitedPartner2);
					}
					else {
						appLog.error("could not verify "+files[1]+" on "+M16LimitedPartner2);
						sa.assertTrue(false, "could not verify "+files[1]+" on "+M16LimitedPartner2);
					}
				}
				switchToDefaultContent(driver);
			}
				else {
					appLog.error("could not find institution on inst page");
					sa.assertTrue(false, "could not find institution on inst page");
				}
			}
			else {
				appLog.error("inst tab is not clickable");
				sa.assertTrue(false, "inst tab is not clickable");
			}
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedLP(M16LimitedPartner3)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.InstitutionsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, null, null, M16FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.InstitutionsPage, Workspace.InvestorWorkspace, fp.reverseFileNameForFileDistributor(files[0], M16LimitedPartner3))) {
						appLog.info("successfully verified "+files[0]+" on "+M16Institution3);
					}
					else {
						appLog.error("could not verify "+files[0]+" on "+M16Institution3);
						sa.assertTrue(false, "could not verify "+files[0]+" on "+M16Institution3);
					}
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.InstitutionsPage, Workspace.InvestorWorkspace, fp.reverseFileNameForFileDistributor(files[1], M16LimitedPartner3))) {
						appLog.info("successfully verified "+files[1]+" on "+M16Institution3);
					}
					else {
						appLog.error("could not verify "+files[1]+" on "+M16Institution3);
						sa.assertTrue(false, "could not verify "+files[1]+" on "+M16Institution3);
					}
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("could not find institution on inst page");
				sa.assertTrue(false, "could not find institution on inst page");
			}
		}
		else {
			appLog.error("inst tab is not clickable");
			sa.assertTrue(false, "inst tab is not clickable");
		}
		
		
		//commitment
		if (ip.clickOnTab(TabName.CommitmentsTab)) {
			if (cmp.clickOnCreatedCommitmentId(M16CommitmentId1)) {
				switchToFrame(driver, 30, cmp.getFrame( PageName.CommitmentsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, null, M16LimitedPartner1, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.CommitmentsPage, Workspace.InvestorWorkspace, M16LimitedPartner1+"_"+files[0])) {
						appLog.info("successfully verified "+files[0]+" on "+M16Institution1);
					}
					else {
						appLog.error("could not verify "+files[0]+" on "+M16Institution1);
						sa.assertTrue(false, "could not verify "+files[0]+" on "+M16Institution1);
					}
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.CommitmentsPage, Workspace.InvestorWorkspace, M16LimitedPartner1+"_"+files[1])) {
						appLog.info("successfully verified "+files[1]+" on "+M16Institution1);
					}
					else {
						appLog.error("could not verify "+files[1]+" on "+M16Institution1);
						sa.assertTrue(false, "could not verify "+files[1]+" on "+M16Institution1);
					}
				}
				switchToDefaultContent(driver);
			}
		}
		if (ip.clickOnTab(TabName.CommitmentsTab)) {
			if (cmp.clickOnCreatedCommitmentId(M16CommitmentId2)) {
				switchToFrame(driver, 30, cmp.getFrame( PageName.CommitmentsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, null, M16LimitedPartner2, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.CommitmentsPage, Workspace.InvestorWorkspace, M16LimitedPartner2+"_"+files[0])) {
						appLog.info("successfully verified "+files[0]+" on "+M16LimitedPartner2);
					}
					else {
						appLog.error("could not verify "+files[0]+" on "+M16LimitedPartner2);
						sa.assertTrue(false, "could not verify "+files[0]+" on "+M16LimitedPartner2);
					}
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.CommitmentsPage, Workspace.InvestorWorkspace, M16LimitedPartner2+"_"+files[1])) {
						appLog.info("successfully verified "+files[1]+" on "+M16LimitedPartner2);
					}
					else {
						appLog.error("could not verify "+files[1]+" on "+M16LimitedPartner2);
						sa.assertTrue(false, "could not verify "+files[1]+" on "+M16LimitedPartner2);
					}
				}
				switchToDefaultContent(driver);
			}
		}
		if (ip.clickOnTab(TabName.CommitmentsTab)) {
			if (cmp.clickOnCreatedCommitmentId(M16CommitmentId3)) {
				switchToFrame(driver, 30, cmp.getFrame( PageName.CommitmentsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, null, M16LimitedPartner3, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.CommitmentsPage, Workspace.InvestorWorkspace, fp.reverseFileNameForFileDistributor(files[0], M16LimitedPartner3))) {
						appLog.info("successfully verified "+files[0]+" on "+M16LimitedPartner3);
					}
					else {
						appLog.error("could not verify "+files[0]+" on "+M16LimitedPartner3);
						sa.assertTrue(false, "could not verify "+files[0]+" on "+M16LimitedPartner3);
					}
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.CommitmentsPage, Workspace.InvestorWorkspace,  fp.reverseFileNameForFileDistributor(files[1], M16LimitedPartner3))) {
						appLog.info("successfully verified "+files[1]+" on "+M16LimitedPartner3);
					}
					else {
						appLog.error("could not verify "+files[1]+" on "+M16LimitedPartner3);
						sa.assertTrue(false, "could not verify "+files[1]+" on "+M16LimitedPartner3);
					}
				}
				switchToDefaultContent(driver);
			}
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M16tc026_VerifyUploadValidAndInvalidDelimeterInvestor_Action() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		String parentID = null;
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String files = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.KeyWord_For_Search);
		SoftAssert saa = new SoftAssert();
		String folderpath1 = "UploadFiles\\Module16\\Bulk_File_Upload_5";
		String dropImage="BulkUpload.jpg";
		List<String> fail_list = null;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M16FundName1)) {
		switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, M16Institution1, M16LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					if (click(driver,fp.getUploadIcon(Workspace.InvestorWorkspace, 30), "upload icon fund/ws", action.BOOLEAN)) {
								parentID = switchOnWindow(driver);
								if (parentID!=null) {
									if (click(driver, fp.getBulkUploaderOrFileSplitterRadioButton(30), "bulk upload or file splitter radio button", action.BOOLEAN)) {
										if (fp.dragDropFiles(folderpath1, dropImage)) {
											ThreadSleep(5000);
											if (click(driver, FindElement(driver, "//li[text()='All Folders']/ul/li[text()='"+stdPath+"']/input", "parent folder radio button", action.BOOLEAN, 10), "parent folder radio button", action.BOOLEAN)) {
												if (click(driver, fp.getFileDistNextBtn(30), "file dist next button", action.BOOLEAN)) {
													if (fp.getFileDist2Of2Info(30).getText().trim().contains(FundsPageErrorMessage.bulkUploadProcessCompleted1+"4"+FundsPageErrorMessage.bulkUploadProcessCompleted2)) {
														appLog.info("correct msg info is being displayed");
													}
													else {
														appLog.error("could not verify info section of file dist window");
														sa.assertTrue(false, "could not verify info section of file dist window");
													}
													saa=fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16LimitedPartner1, files, false, false, null, null, null);
													sa.combineAssertions(saa);
													
													saa=fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16LimitedPartner2, files, false, false, null, null, null);
													sa.combineAssertions(saa);
													
													saa=fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16LimitedPartner3, files, false, false, null, null, null);
													sa.combineAssertions(saa);
													
													if (fp.findFileOnIssueUIFileDistributor(files, M16LimitedPartner4, false)!=null) {
														appLog.info("successfully found "+files+" of "+M16LimitedPartner4+" on issue ui");
													}
													else {
														appLog.error("could not find "+files+" of "+M16LimitedPartner4+" on issue ui");
														sa.assertTrue(false, "could not find "+files+" of "+M16LimitedPartner4+" on issue ui");
													}
													saa = fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16LimitedPartner3, files, false, true,null,null,action.ACCEPT);
													sa.combineAssertions(saa);
													//now inst 3 file will not be present in successfully applied ui
													
													if (click(driver, fp.getResolveIssuesFileDist2Of2(30), "resolve issues button", action.BOOLEAN)) {
														if (click(driver, fp.getSelectFolderLinkForUnassignedFileName(M16LimitedPartner4+"_"+files), "lp 4 file", action.BOOLEAN)) {
															if (fp.clickonFolderStructureFileDistInvestor(M16Institution3+"/"+M16LimitedPartner3, stdPath)) {
																if (click(driver, fp.getApplyButtonFileDist2bOf2(30), "apply button 2b of 2", action.BOOLEAN)) {
																	
																	ThreadSleep(4000);
																	saa=fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16LimitedPartner1, files, false, false, null, null, null);
																	sa.combineAssertions(saa);
																	
																	saa=fp.verifyFileDist2Of2SuccessfullyAppliedUI(M16LimitedPartner2, files, false, false, null, null, null);
																	sa.combineAssertions(saa);
																	
																	saa=fp.verifyFileDist2Of2SuccessfullyAppliedUIDiffInst(M16LimitedPartner3, M16LimitedPartner4, files, false, false, null, null, null);
																	sa.combineAssertions(saa);
																	//no issues will be present in issue ui
																	List<WebElement> issueUI = fp.getIssueUiOnFileDistributor(30, false);
																	if (!issueUI.isEmpty()) {
																		if (issueUI.get(0).getText().trim().equals("No issue File")) {
																				appLog.info("no issue file is successfully found on issue ui");
																	}
																	else {
																		appLog.error("no issue file is not visible on issue ui");
																		sa.assertTrue(false, "no issue file is not visible on issue ui");
																		}
																	}
																	if (fp.clickUsingCssSelectorPath("#lnlbuttonApply", "apply")) {
																		//if (click(driver, fp.getFileDist2Of2ApplyBtn(30), "apply button 2 of 2", action.BOOLEAN)) {
																		if (isAlertPresent(driver)) {
																			String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
																			switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
																			if (msg.trim().contains(FundsPageErrorMessage.DocumentUploadSuccessMsg)) {
																				appLog.info("successfully verified document upload message");
																			}
																			else {
																				appLog.error("could not verify document upload message");
																				sa.assertTrue(false, "could not verify document upload message");
																			}
																		}
																		else {
																			appLog.error("no alert is found when clicked on apply button");
																			sa.assertTrue(false, "no alert is found when clicked on apply button");
																		}
																	}
																	else {
																		appLog.error("2 of 2 apply button is not clickable");
																		sa.assertTrue(false, "2 of 2 apply button is not clickable");
																	}
																}
																else {
																	appLog.error("apply button 2b of 2 is not clickable");
																	sa.assertTrue(false, "apply button 2b of 2 is not clickable");
																}
															}
															else {
																appLog.error("could not find institution and folder in folder structure");
																sa.assertTrue(false, "could not find institution and folder in folder structure");
															}
														}
														else {
															appLog.error("select folder link is not clickable");
															sa.assertTrue(false, "select folder link is not clickable");
														}
													}
													else {
														appLog.error("resolve issues link is not clickable");
														sa.assertTrue(false, "resolve issues link is not clickable");
													}
												}
												else {
													appLog.error("next button is not clickable");
													sa.assertTrue(false, "next button is not clickable");
												}
												}
											else {
												appLog.error("std folder radio button is not clickable");
												sa.assertTrue(false, "std folder radio button is not clickable");
											}
										}
										else {
											appLog.error("could not drag and drop files to bulk upload window");
											sa.assertTrue(false, "could not drag and drop files to bulk upload window");
										}
									}
									else {
										appLog.error("bulk upload radio button is not clickable");
										sa.assertTrue(false, "bulk upload radio button is not clickable");
									}
									driver.switchTo().window(parentID);
									switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
								}
								else {
									appLog.error("could not find child window to switch");
									sa.assertTrue(false, "could not find child window to switch");
								}
								if (fp.verifyFolderPathdummy(stdPath, M16Institution1, M16LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
									if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.InvestorWorkspace, M16LimitedPartner1+"_"+files)) {
										appLog.info("successfully verified "+files+" on "+M16LimitedPartner1);
									}
									else {
										appLog.error("could not verify "+files+" on "+M16LimitedPartner1);
										sa.assertTrue(false, "could not verify "+files+" on "+M16LimitedPartner1);
									}
									if (fp.clickOnDocument(M16LimitedPartner1+"_"+files, 30)) {
										parentID = switchOnWindow(driver);
										ThreadSleep(5000);
										if (parentID!=null) {
											
											if (fp.verifyDataOnFileDistDocument(M16LimitedPartner1, "File 1")) {
												appLog.info("successfully verified data on file splitter doc for inst 1");
											}
											else {
												appLog.error("could not verify file splitter doc for inst 1");
												sa.assertTrue(false, "could not verify file splitter doc for inst 1");
											}
											driver.close();
											driver.switchTo().window(parentID);
											switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
										}
									}
									
								}
								else {
									appLog.error(stdPath+" is not found in folder structure");
									sa.assertTrue(false, stdPath+" is not found in folder structure");
								}
								if (fp.verifyFolderPathdummy(stdPath, M16Institution2, M16LimitedPartner2, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
									if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.InvestorWorkspace, M16LimitedPartner2+"_"+files)) {
										appLog.info("successfully verified files on "+M16LimitedPartner2);
									}
									else {
										appLog.error("could not verify file on "+M16LimitedPartner2);
										sa.assertTrue(false, "could not verify file on "+M16LimitedPartner2);
									}
									if (fp.clickOnDocument(M16LimitedPartner2+"_"+files, 30)) {
										parentID = switchOnWindow(driver);
										ThreadSleep(5000);
										if (parentID!=null) {
											
											if (fp.verifyDataOnFileDistDocument(M16LimitedPartner2, "File 2")) {
												appLog.info("successfully verified data on file splitter doc for inst 2");
											}
											else {
												appLog.error("could not verify file splitter doc for inst 2");
												sa.assertTrue(false, "could not verify file splitter doc for inst 2");
											}
											driver.close();
											driver.switchTo().window(parentID);
											switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
										}
									}
								}
								else {
									appLog.error(stdPath+" is not found in folder structure");
									sa.assertTrue(false, stdPath+" is not found in folder structure");
								}
								if (fp.verifyFolderPathdummy(stdPath, M16Institution3, M16LimitedPartner3, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
									if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.InvestorWorkspace, M16LimitedPartner3+"_"+M16LimitedPartner4+"_"+files)) {
										appLog.info("successfully verified files on inst 3");
									}
									else {
										appLog.error("could not verify file on inst 3");
										sa.assertTrue(false, "could not verify file on inst 3");
									}
									if (fp.clickOnDocument(M16LimitedPartner3+"_"+M16LimitedPartner4+"_"+files, 30)) {
										parentID = switchOnWindow(driver);
										ThreadSleep(5000);
										if (parentID!=null) {
											
											if (fp.verifyDataOnFileDistDocument(M16LimitedPartner4, "File 4")) {
												appLog.info("successfully verified data on file splitter doc for inst 3");
											}
											else {
												appLog.error("could not verify file splitter doc for inst 3");
												sa.assertTrue(false, "could not verify file splitter doc for inst 3");
											}
											driver.close();
											driver.switchTo().window(parentID);
											switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
										}
									}
								}
								else {
									appLog.error(stdPath+" is not found in folder structure");
									sa.assertTrue(false, stdPath+" is not found in folder structure");
								}
									
					}
					else {
						appLog.error("upload icon is not clickable");
						sa.assertTrue(false, "upload icon is not clickable");
					}
				}
				else {
					appLog.error(stdPath+" is not found in folder structure");
					sa.assertTrue(false, stdPath+" is not found in folder structure");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M16FundName1+" is not found on funds tab");
				sa.assertTrue(false, M16FundName1+" is not found on funds tab");
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
	public void M16tc026_VerifyUploadValidAndInvalidDelimeterInvestor_ImpactCRM() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		String parentID = null;
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String files = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M16tc026_VerifyUploadValidAndInvalidDelimeterInvestor_Action", excelLabel.KeyWord_For_Search);
		SoftAssert saa = new SoftAssert();
		String dropImage="BulkUpload.jpg";
		List<String> fail_list = null;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M16Institution1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.InstitutionsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, M16LimitedPartner1, null, M16FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.InstitutionsPage, Workspace.InvestorWorkspace, M16LimitedPartner1+"_"+files)) {
						appLog.info("successfully verified "+files+" on "+M16Institution1);
					}
					else {
						appLog.error("could not verify "+files+" on "+M16Institution1);
						sa.assertTrue(false, "could not verify "+files+" on "+M16Institution1);
					}
					}
				else {
					appLog.error("could not find "+stdPath+" in folder structure");
					sa.assertTrue(false, "could not find "+stdPath+" in folder structure");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("could not find institution "+M16Institution1);
				sa.assertTrue(false, "could not find institution "+M16Institution1);
			}
		}
		else {
			appLog.error("inst tab is not clickable");
			sa.assertTrue(false, "inst tab is not clickable");
		}
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M16Institution2)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.InstitutionsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, M16LimitedPartner2, null, M16FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.InstitutionsPage, Workspace.InvestorWorkspace, M16LimitedPartner2+"_"+files)) {
						appLog.info("successfully verified "+files+" on "+M16Institution2);
					}
					else {
						appLog.error("could not verify "+files+" on "+M16Institution2);
						sa.assertTrue(false, "could not verify "+files+" on "+M16Institution2);
					}
					}
				else {
					appLog.error("could not find "+stdPath+" in folder structure");
					sa.assertTrue(false, "could not find "+stdPath+" in folder structure");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("could not find institution "+M16Institution2);
				sa.assertTrue(false, "could not find institution "+M16Institution2);
			}
		}
		else {
			appLog.error("inst tab is not clickable");
			sa.assertTrue(false, "inst tab is not clickable");
		}
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M16Institution3)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.InstitutionsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, M16LimitedPartner3, null, M16FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.InstitutionsPage, Workspace.InvestorWorkspace, M16LimitedPartner3+"_"+M16LimitedPartner4+"_"+files)) {
						appLog.info("successfully verified "+files+" on "+M16Institution3);
					}
					else {
						appLog.error("could not verify "+files+" on "+M16Institution3);
						sa.assertTrue(false, "could not verify "+files+" on "+M16Institution3);
					}
					}
				else {
					appLog.error("could not find "+stdPath+" in folder structure");
					sa.assertTrue(false, "could not find "+stdPath+" in folder structure");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("could not find institution "+M16Institution3);
				sa.assertTrue(false, "could not find institution "+M16Institution3);
			}
		}
		else {
			appLog.error("inst tab is not clickable");
			sa.assertTrue(false, "inst tab is not clickable");
		}
		
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedLP(M16LimitedPartner1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.InstitutionsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, null, null, M16FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.InstitutionsPage, Workspace.InvestorWorkspace, M16LimitedPartner1+"_"+files)) {
						appLog.info("successfully verified "+files+" on "+M16LimitedPartner1);
					}
					else {
						appLog.error("could not verify "+files+" on "+M16LimitedPartner1);
						sa.assertTrue(false, "could not verify "+files+" on "+M16LimitedPartner1);
					}
					}
				else {
					appLog.error("could not find "+stdPath+" in folder structure");
					sa.assertTrue(false, "could not find "+stdPath+" in folder structure");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("could not find lp "+M16LimitedPartner1);
				sa.assertTrue(false, "could not find lp "+M16LimitedPartner1);
			}
		}
		else {
			appLog.error("inst tab is not clickable");
			sa.assertTrue(false, "inst tab is not clickable");
		}
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedLP(M16LimitedPartner2)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.InstitutionsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, null, null, M16FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.InstitutionsPage, Workspace.InvestorWorkspace, M16LimitedPartner2+"_"+files)) {
						appLog.info("successfully verified "+files+" on "+M16LimitedPartner2);
					}
					else {
						appLog.error("could not verify "+files+" on "+M16LimitedPartner2);
						sa.assertTrue(false, "could not verify "+files+" on "+M16LimitedPartner2);
					}
					}
				else {
					appLog.error("could not find "+stdPath+" in folder structure");
					sa.assertTrue(false, "could not find "+stdPath+" in folder structure");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("could not find lp "+M16LimitedPartner2);
				sa.assertTrue(false, "could not find lp "+M16LimitedPartner2);
			}
		}
		else {
			appLog.error("inst tab is not clickable");
			sa.assertTrue(false, "inst tab is not clickable");
		}
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedLP(M16LimitedPartner3)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.InstitutionsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, null, null, M16FundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.InstitutionsPage, Workspace.InvestorWorkspace, M16LimitedPartner3+"_"+M16LimitedPartner4+"_"+files)) {
						appLog.info("successfully verified "+files+" on "+M16LimitedPartner3);
					}
					else {
						appLog.error("could not verify "+files+" on "+M16LimitedPartner3);
						sa.assertTrue(false, "could not verify "+files+" on "+M16LimitedPartner3);
					}
					}
				else {
					appLog.error("could not find "+stdPath+" in folder structure");
					sa.assertTrue(false, "could not find "+stdPath+" in folder structure");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error("could not find lp "+M16LimitedPartner3);
				sa.assertTrue(false, "could not find lp "+M16LimitedPartner3);
			}
		}
		else {
			appLog.error("inst tab is not clickable");
			sa.assertTrue(false, "inst tab is not clickable");
		}
		
		if (bp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M16Contact1FirstName, M16Contact1LastName, null)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.ContactsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, M16LimitedPartner1, null, M16FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.ContactsPage, Workspace.InvestorWorkspace, M16LimitedPartner1+"_"+files)) {
						appLog.info("successfully verified "+files+" on "+M16Institution1);
					}
					else {
						appLog.error("could not verify "+files+" on "+M16Institution1);
						sa.assertTrue(false, "could not verify "+files+" on "+M16Institution1);
					}
					}
				else {
					appLog.error("could not find "+stdPath+" in folder structure");
					sa.assertTrue(false, "could not find "+stdPath+" in folder structure");
				}
				if (fp.verifyFolderPathdummy(stdPath, M16LimitedPartner2, null, M16FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.ContactsPage, Workspace.InvestorWorkspace, M16LimitedPartner2+"_"+files)) {
						appLog.info("successfully verified "+files+" on "+M16Institution2);
					}
					else {
						appLog.error("could not verify "+files+" on "+M16Institution2);
						sa.assertTrue(false, "could not verify "+files+" on "+M16Institution2);
					}
					}
				else {
					appLog.error("could not find "+stdPath+" in folder structure");
					sa.assertTrue(false, "could not find "+stdPath+" in folder structure");
				}
				
				if (fp.verifyFolderPathdummy(stdPath, M16LimitedPartner3, null, M16FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.ContactsPage, Workspace.InvestorWorkspace, M16LimitedPartner3+"_"+M16LimitedPartner4+"_"+files)) {
						appLog.info("successfully verified "+files+" on "+M16Institution3);
					}
					else {
						appLog.error("could not verify "+files+" on "+M16Institution3);
						sa.assertTrue(false, "could not verify "+files+" on "+M16Institution3);
					}
					}
				else {
					appLog.error("could not find "+stdPath+" in folder structure");
					sa.assertTrue(false, "could not find "+stdPath+" in folder structure");
				}
				switchToDefaultContent(driver);
			}
		}
		if (ip.clickOnTab(TabName.CommitmentsTab)) {
			if (cmp.clickOnCreatedCommitmentId(M16CommitmentId1)) {
				switchToFrame(driver, 30, cmp.getFrame( PageName.CommitmentsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, null, M16LimitedPartner1, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.CommitmentsPage, Workspace.InvestorWorkspace, M16LimitedPartner1+"_"+files)) {
						appLog.info("successfully verified "+files+" on "+M16Institution1);
					}
					else {
						appLog.error("could not verify "+files+" on "+M16Institution1);
						sa.assertTrue(false, "could not verify "+files+" on "+M16Institution1);
					}
				}
				switchToDefaultContent(driver);
			}
		}
		if (ip.clickOnTab(TabName.CommitmentsTab)) {
			if (cmp.clickOnCreatedCommitmentId(M16CommitmentId2)) {
				switchToFrame(driver, 30, cmp.getFrame( PageName.CommitmentsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, null, M16LimitedPartner2, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.CommitmentsPage, Workspace.InvestorWorkspace, M16LimitedPartner2+"_"+files)) {
						appLog.info("successfully verified "+files+" on "+M16LimitedPartner2);
					}
					else {
						appLog.error("could not verify "+files+" on "+M16LimitedPartner2);
						sa.assertTrue(false, "could not verify "+files+" on "+M16LimitedPartner2);
					}
				}
				switchToDefaultContent(driver);
			}
		}
		if (ip.clickOnTab(TabName.CommitmentsTab)) {
			if (cmp.clickOnCreatedCommitmentId(M16CommitmentId3)) {
				switchToFrame(driver, 30, cmp.getFrame( PageName.CommitmentsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, null, M16LimitedPartner3, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 30)) {
					if (fp.verifyFileInContentGridWithBigDocNames(PageName.CommitmentsPage, Workspace.InvestorWorkspace, M16LimitedPartner3+"_"+M16LimitedPartner4+"_"+files)) {
						appLog.info("successfully verified "+files+" on "+M16LimitedPartner3);
					}
					else {
						appLog.error("could not verify "+files+" on "+M16LimitedPartner3);
						sa.assertTrue(false, "could not verify "+files+" on "+M16LimitedPartner3);
					}
				}
				switchToDefaultContent(driver);
			}
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M16tc026_VerifyUploadValidAndInvalidDelimeterInvestor_ImpactInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		String parentID = null;
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M16tc026_VerifyUploadValidAndInvalidDelimeterInvestor_Action", excelLabel.StandardPath);
		String files = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M16tc026_VerifyUploadValidAndInvalidDelimeterInvestor_Action", excelLabel.KeyWord_For_Search);
		SoftAssert saa = new SoftAssert();
		List<String> fail_list = null;
		String date = getSystemDate("MM/dd/yyyy")+previousOrForwardDate(-1, "MM/dd/yyyy");
		lp.investorLogin(M16Contact1EmailId, adminPassword);
		if (click(driver, ifp.getAllDocumentsTab(30), "all documents tab", action.BOOLEAN)) {
			fail_list=ifp.verifyAlertOnRecentAllDocumentGrid(M16LimitedPartner1+"_"+files+"<break>"+M16LimitedPartner2+"_"+files+"<break>"+M16LimitedPartner3+"_"+M16LimitedPartner4+"_"+files, M16FundName1, investorSideWorkSpace.CurrentInvestment, TabName.AllDocuments, activityType.DocumentUpload, PageName.AllFirmsPage);
			if (fail_list.isEmpty()) {
				appLog.info("successfully verified all allerts on AllDocument");
			}
			else {
				for (int i = 0;i<fail_list.size();i++) {
					appLog.error(fail_list.get(i)+" is not present on AllDocument grid");
				}
				sa.assertTrue(false, "could not verify docs on AllDocument grid");
			}
		}
		else {
			appLog.error("all docs tab is not clickable");
			sa.assertTrue(false, "all docs tab is not clickable");
		}
		if (click(driver, ifp.getRecentActivitiesTab(30), "recent activities tab", action.BOOLEAN)) {
			fail_list=ifp.verifyAlertOnRecentAllDocumentGrid(M16LimitedPartner1+"_"+files+"<break>"+M16LimitedPartner2+"_"+files+"<break>"+M16LimitedPartner3+"_"+M16LimitedPartner4+"_"+files, M16FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities, activityType.DocumentUpload, PageName.AllFirmsPage);
			if (fail_list.isEmpty()) {
				appLog.info("successfully verified all allerts on recent activities");
			}
			else {
				for (int i = 0;i<fail_list.size();i++) {
					appLog.error(fail_list.get(i)+" is not present on recent docs grid");
				}
				sa.assertTrue(false, "could not verify docs on recent activities grid");
			}
		}
		else {
			appLog.error("RecentActivities tab is not clickable");
			sa.assertTrue(false, "RecentActivities tab is not clickable");
		}
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			if (fp.verifyFolderPathdummy(stdPath, null, M16LimitedPartner1, M16FundName1, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 30)) {
				saa=ifp.verifyContentGridInvestorSide(driver, PageName.CurrentInvestmentPgae, M16LimitedPartner1+"_"+files, CRMUser1FirstName+" "+CRMUser1LastName, date);
				sa.combineAssertions(saa);
				if (bp.clickOnDocument(M16LimitedPartner1+"_"+files, 30)) {
					parentID = switchOnWindow(driver);
					ThreadSleep(5000);
					if (parentID!=null) {
						
						if (fp.verifyDataOnFileDistDocument(M16LimitedPartner1, "File 1")) {
							appLog.info("successfully verified data on file splitter doc for inst 1");
						}
						else {
							appLog.error("could not verify file splitter doc for inst 1");
							sa.assertTrue(false, "could not verify file splitter doc for inst 1");
						}
						driver.close();
						driver.switchTo().window(parentID);
					}
				}
				else {
					appLog.error(M16LimitedPartner1+"_"+files + " document is not clickable");
					sa.assertTrue(false, M16LimitedPartner1+"_"+files + " document is not clickable");
				}
			}
			if (fp.verifyFolderPathdummy(stdPath, null, M16LimitedPartner2, M16FundName1, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 30)) {
				saa=ifp.verifyContentGridInvestorSide(driver, PageName.CurrentInvestmentPgae, M16LimitedPartner2+"_"+files, CRMUser1FirstName+" "+CRMUser1LastName, date);
				sa.combineAssertions(saa);
				if (bp.clickOnDocument(M16LimitedPartner2+"_"+files, 30)) {
					parentID = switchOnWindow(driver);
					ThreadSleep(5000);
					if (parentID!=null) {
						
						if (fp.verifyDataOnFileDistDocument(M16LimitedPartner2, "File 2")) {
							appLog.info("successfully verified data on file splitter doc for lp 2");
						}
						else {
							appLog.error("could not verify file splitter doc for lp 2");
							sa.assertTrue(false, "could not verify file splitter doc for lp 2");
						}
						driver.close();
						driver.switchTo().window(parentID);
					}
				}
				else {
					appLog.error(M16LimitedPartner2+"_"+files + " document is not clickable");
					sa.assertTrue(false, M16LimitedPartner2+"_"+files + " document is not clickable");
				}
			}
			if (fp.verifyFolderPathdummy(stdPath, null, M16LimitedPartner3, M16FundName1, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 30)) {
				saa=ifp.verifyContentGridInvestorSide(driver, PageName.CurrentInvestmentPgae, M16LimitedPartner3+"_"+M16LimitedPartner4+"_"+files, CRMUser1FirstName+" "+CRMUser1LastName, date);
				sa.combineAssertions(saa);
				if (bp.clickOnDocument(M16LimitedPartner3+"_"+M16LimitedPartner4+"_"+files, 30)) {
					parentID = switchOnWindow(driver);
					ThreadSleep(5000);
					if (parentID!=null) {
						
						if (fp.verifyDataOnFileDistDocument(M16LimitedPartner4, "File 4")) {
							appLog.info("successfully verified data on file splitter doc for lp 3");
						}
						else {
							appLog.error("could not verify file splitter doc for lp 3");
							sa.assertTrue(false, "could not verify file splitter doc for lp 3");
						}
						driver.close();
						driver.switchTo().window(parentID);
					}
				}
				else {
					appLog.error(M16LimitedPartner3+"_"+M16LimitedPartner4+"_"+files + " document is not clickable");
					sa.assertTrue(false, M16LimitedPartner3+"_"+M16LimitedPartner4+"_"+files + " document is not clickable");
				}
			}
				
		}
		lp.investorLogout();
		sa.assertAll();
	
	}
	
	@Test
	public void M16tc027_ChangeFileSplitterOptionsAndVerifyInvestors_Action() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		String parentID = null;
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String files = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.KeyWord_For_Search);
		SoftAssert saa = new SoftAssert();
		String folderpath1 = "UploadFiles\\Module16\\Bulk_File_Upload_6";
		String dropImage="BulkUpload.jpg";
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, np.getFrame(environment,mode, PageName.NavatarInvestorManager, 30));
			if (np.clickOnSideMenusTab(sideMenu.FileDistributorSettings)) {
				if (np.clickOnSideMenusTab(sideMenu.FileSplitterOptions)) {
					if (click(driver, np.getReverseNamingRadioButton(30), "reverse naming radio button", action.BOOLEAN)) {
						if (click(driver,np.getFileDistributorSaveButton(fileDistributor.FileSplitter), "save button", action.SCROLLANDBOOLEAN)) {
							if (isSelected(driver,np.getReverseNamingRadioButton(30),"reverse naming radio button") ) {
								appLog.info("successfully saved reverse naaming radio button");
							}
							else {
								appLog.error("could not save reverse naming radio button");
								sa.assertTrue(false, "could not save reverse naming radio button");
							}
							if (!isSelected(driver,np.getUseSuggestedNaming(30), "suggested naming radio button")) {
								appLog.info("successfully deselected suggested naming radio button");
							}
							else {
								appLog.error("suggested naming radio button is still selected");
								sa.assertTrue(false, "suggested naming radio button is still selected");
							}
						}
						else {
							appLog.error("save button on file distributor is not clickable");
							sa.assertTrue(false, "save button on file distributor is not clickable");
						}
					}
					else {
						appLog.error("reverse naming radio button is not clickable");
						sa.assertTrue(false, "reverse naming radio button is not clickable");
					}
				}
				else {
					appLog.error("file splitter link is not clickable");
					sa.assertTrue(false, "file splitter link is not clickable");
				}
			}
			else {
				appLog.error("file distributor link is not clickable");
				sa.assertTrue(false, "file distributor link is not clickable");
			}
			switchToDefaultContent(driver);
		}
		else {
			appLog.error("nim tab is not clickable");
			sa.assertTrue(false, "nim tab is not clickable");
		}
		lp.CRMlogout();
		ThreadSleep(6000);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M16FundName1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath, M16Institution1, M16LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					if (click(driver,fp.getUploadIcon(Workspace.InvestorWorkspace, 30), "upload icon fund/ws", action.BOOLEAN)) {
						parentID = switchOnWindow(driver);
						if (parentID!=null) {
							if (click(driver, fp.getBulkUploaderOrFileSplitterRadioButton(30), "bulk upload or file splitter radio button", action.BOOLEAN)) {
								if (fp.dragDropFiles(folderpath1, dropImage)) {
									ThreadSleep(5000);
									if (click(driver, FindElement(driver, "//li[text()='All Folders']/ul/li[text()='"+stdPath+"']/input", "parent folder radio button", action.BOOLEAN, 10), "parent folder radio button", action.BOOLEAN)) {
										if (click(driver, fp.getFileDistNextBtn(30), "file dist next button", action.BOOLEAN)) {
											ThreadSleep(4000);
											saa=fp.verifyFileDist2Of2SuccessfullyAppliedUIReverse(M16LimitedPartner1, files, false, false, null, null);
											sa.combineAssertions(saa);
											saa=fp.verifyFileDist2Of2SuccessfullyAppliedUIReverse(M16LimitedPartner2, files, false, false, null, null);
											sa.combineAssertions(saa);
											saa=fp.verifyFileDist2Of2SuccessfullyAppliedUIReverse(M16LimitedPartner3, files, false, false, null, null);
											sa.combineAssertions(saa);
											//issue ui
											if (fp.findFileOnIssueUIFileDistributor(files, M16LimitedPartner4, true)!=null) {
												appLog.info("successfully verified "+files+" of "+M16LimitedPartner4+" on issue ui");
													}
											else {
												appLog.error("could not verify "+files+" of "+M16Institution4+" on issue ui");
												sa.assertTrue(false, "successfully verified "+files+" of "+M16LimitedPartner4+" on issue ui");
											}
											if (fp.clickUsingCssSelectorPath("#lnlbuttonApply", "apply")) {
												//if (click(driver, fp.getFileDist2Of2ApplyBtn(30), "apply btn 2 of 2", action.BOOLEAN)) {
												if (isAlertPresent(driver)) {
													String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
													switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
													if (msg.trim().contains(FundsPageErrorMessage.DocumentUploadSuccessMsg)) {
														appLog.info("document upload success message is successfully verified");
													}
													else {
														appLog.error("could not verify document upload message");
														sa.assertTrue(false, "could not verify document upload message");
													}
												}
												else {
													appLog.error("alert is not present for clicking on apply button");
													sa.assertTrue(false, "alert is not present for clicking on apply button");
												}
											}
											else {
												appLog.error("apply button 2 of 2 is not clickable");
												sa.assertTrue(false, "apply button 2 of 2 is not clickable");
											}
										}
										else {
											appLog.error("next button is not clickable");
											sa.assertTrue(false, "next button is not clickable");
										}
									}
									else {
										appLog.error("std radio button is not clickable");
										sa.assertTrue(false, "std radio button is not clickable");
									}
								}
								else {
									appLog.error("could not drag and drop file");
									sa.assertTrue(false, "could not drag and drop file");
								}
							}
							else {
								appLog.error("bulk upload radio button is not clickable");
								sa.assertTrue(false, "bulk upload radio button is not clickable");
							}
							driver.switchTo().window(parentID);
							switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
						}
						else {
							appLog.error("could not find child window to switch");
							sa.assertTrue(false, "could not find child window to switch");
						}
						if (fp.verifyFolderPathdummy(stdPath, M16Institution1, M16LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
							if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.InvestorWorkspace, fp.reverseFileNameForFileDistributor(files, M16LimitedPartner1))) {
								appLog.info("successfully verified document "+files+" on "+M16LimitedPartner1);
							}
							else {
								appLog.error("could not verify "+files+" on "+M16LimitedPartner1);
								sa.assertTrue(false, "could not verify "+files+" on "+M16LimitedPartner1);
							}
							if (fp.clickOnDocument(fp.reverseFileNameForFileDistributor(files, M16LimitedPartner1), 30)) {
								parentID = switchOnWindow(driver);
								ThreadSleep(5000);
								if (parentID!=null) {
									ThreadSleep(5000);
									if (fp.verifyDataOnFileDistDocument(M16LimitedPartner1, "File 1")) {
										appLog.info("successfully verified data on file splitter doc for inst 1");
									}
									else {
										appLog.error("could not verify file splitter doc for inst 1");
										sa.assertTrue(false, "could not verify file splitter doc for inst 1");
									}
									driver.close();
									driver.switchTo().window(parentID);
									switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
								}
							}
							else {
								appLog.error("could not find document to click");
								sa.assertTrue(false, "could not find document to click");
							}
						}
						if (fp.verifyFolderPathdummy(stdPath, M16Institution2, M16LimitedPartner2, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
							if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.InvestorWorkspace, fp.reverseFileNameForFileDistributor(files, M16LimitedPartner2))) {
								appLog.info("successfully verified document "+files+" on "+M16LimitedPartner2);
							}
							else {
								appLog.error("could not verify "+files+" on "+M16LimitedPartner2);
								sa.assertTrue(false, "could not verify "+files+" on "+M16LimitedPartner2);
							}
							if (fp.clickOnDocument(fp.reverseFileNameForFileDistributor(files, M16LimitedPartner2), 30)) {
								parentID = switchOnWindow(driver);
								ThreadSleep(5000);
								if (parentID!=null) {
									if (fp.verifyDataOnFileDistDocument(M16LimitedPartner2, "File 2")) {
										appLog.info("successfully verified data on file splitter doc for inst 2");
									}
									else {
										appLog.error("could not verify file splitter doc for inst 2");
										sa.assertTrue(false, "could not verify file splitter doc for inst 2");
									}
									driver.close();
									driver.switchTo().window(parentID);
									switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
								}
							}
							else {
								appLog.error("could not find document to click");
								sa.assertTrue(false, "could not find document to click");
							}
						}
						else {
							appLog.error("could not find "+stdPath+" in folder structure");
							sa.assertTrue(false, "could not find "+stdPath+" in folder structure");
						}
						if (fp.verifyFolderPathdummy(stdPath, M16Institution3, M16LimitedPartner3, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
							if (fp.verifyFileInContentGridWithBigDocNames(PageName.FundsPage, Workspace.InvestorWorkspace, fp.reverseFileNameForFileDistributor(files, M16LimitedPartner3))) {
								appLog.info("successfully verified document "+files+" on "+M16LimitedPartner3);
							}
							else {
								appLog.error("could not verify "+files+" on "+M16LimitedPartner3);
								sa.assertTrue(false, "could not verify "+files+" on "+M16LimitedPartner3);
							}
							if (fp.clickOnDocument(fp.reverseFileNameForFileDistributor(files, M16LimitedPartner3), 30)) {
								parentID = switchOnWindow(driver);
								ThreadSleep(5000);
								if (parentID!=null) {
									ThreadSleep(5000);
									if (fp.verifyDataOnFileDistDocument(M16LimitedPartner3, "File 3")) {
										appLog.info("successfully verified data on file splitter doc for inst 3");
									}
									else {
										appLog.error("could not verify file splitter doc for inst 3");
										sa.assertTrue(false, "could not verify file splitter doc for inst 3");
									}
									driver.close();
									driver.switchTo().window(parentID);
									switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
								}
							}
							else {
								appLog.error("could not find document to click");
								sa.assertTrue(false, "could not find document to click");
							}
						}
						else {
							appLog.error("could not find "+stdPath+" in folder structure");
							sa.assertTrue(false, "could not find "+stdPath+" in folder structure");
						}
					}
					else {
						appLog.error("upload icon is not clickable");
						sa.assertTrue(false, "upload icon is not clickable");
					}
				}
				else {
					appLog.error("could not find "+stdPath+" in folder structure");
					sa.assertTrue(false, "could not find "+stdPath+" in folder structure");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M16FundName1+" is not found on funds page");
				sa.assertTrue(false, M16FundName1+" is not found on funds page");
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
	public void M16tc027_ChangeFileSplitterOptionsAndVerifyInvestors_Impact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M16tc027_ChangeFileSplitterOptionsAndVerifyInvestors_Action", excelLabel.StandardPath);
		String files = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, "M16tc027_ChangeFileSplitterOptionsAndVerifyInvestors_Action", excelLabel.KeyWord_For_Search);
		SoftAssert saa = new SoftAssert();
		String parentID = null;
		List<String> fail_list = null;
		String date = getSystemDate("MM/dd/yyyy")+previousOrForwardDate(-1, "MM/dd/yyyy");
		lp.investorLogin(M16Contact1EmailId, adminPassword);
		if (click(driver, ifp.getAllDocumentsTab(30), "all documents tab", action.BOOLEAN)) {
			fail_list=ifp.verifyAlertOnRecentAllDocumentGrid(fp.reverseFileNameForFileDistributor(files, M16LimitedPartner1)+"<break>"+fp.reverseFileNameForFileDistributor(files, M16LimitedPartner2)+"<break>"+fp.reverseFileNameForFileDistributor(files, M16LimitedPartner3), M16FundName1, investorSideWorkSpace.CurrentInvestment, TabName.AllDocuments, activityType.DocumentUpload, PageName.AllFirmsPage);
			if (fail_list.isEmpty()) {
				appLog.info("successfully verified all allerts on AllDocument");
			}
			else {
				for (int i = 0;i<fail_list.size();i++) {
					appLog.error(fail_list.get(i)+" is not present on AllDocument grid");
				}
				sa.assertTrue(false, "could not verify docs on AllDocument grid");
			}
		}
		else {
			appLog.error("all docs tab is not clickable");
			sa.assertTrue(false, "all docs tab is not clickable");
		}
		if (click(driver, ifp.getRecentActivitiesTab(30), "recent activities tab", action.BOOLEAN)) {
			fail_list=ifp.verifyAlertOnRecentAllDocumentGrid(fp.reverseFileNameForFileDistributor(files, M16LimitedPartner1)+"<break>"+fp.reverseFileNameForFileDistributor(files, M16LimitedPartner2)+"<break>"+fp.reverseFileNameForFileDistributor(files, M16LimitedPartner3), M16FundName1, investorSideWorkSpace.CurrentInvestment, TabName.RecentActivities, activityType.DocumentUpload, PageName.AllFirmsPage);
			if (fail_list.isEmpty()) {
				appLog.info("successfully verified all allerts on recent activities");
			}
			else {
				for (int i = 0;i<fail_list.size();i++) {
					appLog.error(fail_list.get(i)+" is not present on recent docs grid");
				}
				sa.assertTrue(false, "could not verify docs on recent activities grid");
			}
		}
		else {
			appLog.error("RecentActivities tab is not clickable");
			sa.assertTrue(false, "RecentActivities tab is not clickable");
		}
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			if (fp.verifyFolderPathdummy(stdPath, null, M16LimitedPartner1, M16FundName1, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 30)) {
				saa=ifp.verifyContentGridInvestorSide(driver, PageName.CurrentInvestmentPgae, fp.reverseFileNameForFileDistributor(files, M16LimitedPartner1), CRMUser1FirstName+" "+CRMUser1LastName, date);
				sa.combineAssertions(saa);
				if (fp.clickOnDocument(fp.reverseFileNameForFileDistributor(files, M16LimitedPartner1), 30)) {
					parentID = switchOnWindow(driver);
					ThreadSleep(5000);
					if (parentID!=null) {
						ThreadSleep(5000);
						if (fp.verifyDataOnFileDistDocument(M16LimitedPartner1, "File 1")) {
							appLog.info("successfully verified data on file splitter doc for inst 1");
						}
						else {
							appLog.error("could not verify file splitter doc for inst 1");
							sa.assertTrue(false, "could not verify file splitter doc for inst 1");
						}
						driver.close();
						driver.switchTo().window(parentID);
					}
				}
				else {
					appLog.error("could not find document to click");
					sa.assertTrue(false, "could not find document to click");
				}
			}
			if (fp.verifyFolderPathdummy(stdPath, null, M16LimitedPartner2, M16FundName1, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 30)) {
				saa=ifp.verifyContentGridInvestorSide(driver, PageName.CurrentInvestmentPgae, fp.reverseFileNameForFileDistributor(files, M16LimitedPartner2), CRMUser1FirstName+" "+CRMUser1LastName, date);
				sa.combineAssertions(saa);
				if (fp.clickOnDocument(fp.reverseFileNameForFileDistributor(files, M16LimitedPartner2), 30)) {
					parentID = switchOnWindow(driver);
					ThreadSleep(5000);
					if (parentID!=null) {
						ThreadSleep(5000);
						if (fp.verifyDataOnFileDistDocument(M16LimitedPartner2, "File 2")) {
							appLog.info("successfully verified data on file splitter doc for lp 2");
						}
						else {
							appLog.error("could not verify file splitter doc for lp 2");
							sa.assertTrue(false, "could not verify file splitter doc for lp 2");
						}
						driver.close();
						driver.switchTo().window(parentID);
					}
				}
				else {
					appLog.error("could not find document to click");
					sa.assertTrue(false, "could not find document to click");
				}
			}
			if (fp.verifyFolderPathdummy(stdPath, null, M16LimitedPartner3, M16FundName1, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 30)) {
				saa=ifp.verifyContentGridInvestorSide(driver, PageName.CurrentInvestmentPgae, fp.reverseFileNameForFileDistributor(files, M16LimitedPartner3), CRMUser1FirstName+" "+CRMUser1LastName, date);
				sa.combineAssertions(saa);
				if (fp.clickOnDocument(fp.reverseFileNameForFileDistributor(files, M16LimitedPartner3), 30)) {
					parentID = switchOnWindow(driver);
					ThreadSleep(5000);
					if (parentID!=null) {
						ThreadSleep(5000);
						if (fp.verifyDataOnFileDistDocument(M16LimitedPartner3, "File 3")) {
							appLog.info("successfully verified data on file splitter doc for lp 3");
						}
						else {
							appLog.error("could not verify file splitter doc for lp 3");
							sa.assertTrue(false, "could not verify file splitter doc for lp 3");
						}
						driver.close();
						driver.switchTo().window(parentID);
					}
				}
				else {
					appLog.error("could not find document to click");
					sa.assertTrue(false, "could not find document to click");
				}
			}
		}
		lp.investorLogout();
		sa.assertAll();
	}
}
