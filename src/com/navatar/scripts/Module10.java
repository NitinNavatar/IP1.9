/**
 * 
 */

package com.navatar.scripts;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import com.navatar.generic.BaseLib;
import com.navatar.generic.CommonVariables;
import com.navatar.generic.EmailLib;
import com.navatar.generic.ExcelUtils;
import com.navatar.generic.SoftAssert;
import com.navatar.generic.CommonLib.CreationPage;
import com.navatar.generic.CommonLib.EnableDisable;
import com.navatar.generic.CommonLib.FolderType;
import com.navatar.generic.CommonLib.InstitutionPageFieldLabelText;
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
import com.navatar.pageObjects.CommitmentPageBusinessLayer;
import com.navatar.pageObjects.ContactPageBusinessLayer;
import com.navatar.pageObjects.ContactPageErrorMessage;
import com.navatar.pageObjects.FundRaisingPageBusinessLayer;
import com.navatar.pageObjects.FundsPageBusinessLayer;
import com.navatar.pageObjects.FundsPageErrorMessage;
import com.navatar.pageObjects.HomePageBusineesLayer;
import com.navatar.pageObjects.InstitutionPageBusinessLayer;
import com.navatar.pageObjects.InvestorFirmPageBusinesslayer;
import com.navatar.pageObjects.LoginPageBusinessLayer;
import com.navatar.pageObjects.NIMPageBusinessLayer;
import com.navatar.pageObjects.PartnershipPageBusinessLayer;

import static com.navatar.generic.AppListeners.appLog;
import static com.navatar.generic.CommonLib.*;
import static com.navatar.generic.CommonVariables.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Parul Singh
 *
 */
public class Module10 extends BaseLib {

	@Test
	public void M10tc001_CreatePreconditionData() {
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
			instutionName = ExcelUtils.readData("Institutions", excelLabel.Variable_Name, "M10Institution" + (i + 1),
					excelLabel.Institutions_Name);
			ThreadSleep(3000);
			if (bp.clickOnTab(TabName.InstituitonsTab)) {
				if (ip.createInstitution(environment,mode,instutionName,"Institution",null,null)) {
					appLog.info("successfully created "+instutionName);
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
			String fundName = ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M10Fund" + (i + 1),
					excelLabel.Fund_Name);
			if (bp.clickOnTab(TabName.FundsTab)) {
				String fundType = ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M10Fund" + (i + 1),
						excelLabel.Fund_Type);
				String investmentCategory = ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M10Fund" + (i + 1),
						excelLabel.Fund_InvestmentCategory);
				if (fp.createFund(environment,mode,fundName, fundType, investmentCategory,null,null)) {
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
		for (int i = 0; i < 2; i++) {
			String emailId = cp.generateRandomEmailId();
			instutionName = ExcelUtils.readData("Contacts", excelLabel.Variable_Name, "M10Contact" + (i + 1),
					excelLabel.Institutions_Name);
			String ContactFirstName = ExcelUtils.readData("Contacts", excelLabel.Variable_Name, "M10Contact" + (i + 1),
					excelLabel.Contact_FirstName);
			String ContactLastName = ExcelUtils.readData("Contacts", excelLabel.Variable_Name, "M10Contact" + (i + 1),
					excelLabel.Contact_LastName);
			if (bp.clickOnTab(TabName.ContactTab)) {
				if (cp.createContact(environment,mode,ContactFirstName, ContactLastName, instutionName, emailId,null,null,CreationPage.ContactPage)) {
					appLog.info("contact is created: " + ContactFirstName + " " + ContactLastName);
					if (emailId != "") {
						ExcelUtils.writeData(emailId, "Contacts", excelLabel.Variable_Name, "M10Contact" + (i + 1),
								excelLabel.Contact_EmailId);
					}
				} else {
					appLog.error("Not able to create contact: " + ContactFirstName + " " + ContactLastName);
					saa.assertTrue(false, "Not able to create contact: " + ContactFirstName + " " + ContactLastName);
				}
			}
			else {
				appLog.error("contacts tab is not clickable");
				sa.assertTrue(false, "contacts tab is not clickable");
			}
		}
		for (int i = 0; i < 3; i++) {
			String fundraisingName = ExcelUtils.readData("Fundraisings", excelLabel.Variable_Name,
					"M10FundRaising" + (i + 1), excelLabel.FundRaising_Name);
			if (bp.clickOnTab(TabName.FundraisingsTab)) {
				String fundName = ExcelUtils.readData("Fundraisings", excelLabel.Variable_Name,
						"M10FundRaising" + (i + 1), excelLabel.Fund_Name);
				instutionName = ExcelUtils.readData("Fundraisings", excelLabel.Variable_Name,
						"M10FundRaising" + (i + 1), excelLabel.Institutions_Name);
				if (frp.createFundRaising(environment,mode,fundraisingName, fundName, instutionName)) {
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
					"M10LimitedPartner" + (i + 1), excelLabel.LimitedPartner_Name);
			if (bp.clickOnTab(TabName.InstituitonsTab)) {
				instutionName = ExcelUtils.readData("Institutions", excelLabel.Variable_Name,
						"M10Institution" + (i + 1), excelLabel.Institutions_Name);
				if(ip.createInstitution(environment, mode, lpName, "Limited Partner", InstitutionPageFieldLabelText.Parent_Institution.toString(), instutionName)) {
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

		String partnershipName = ExcelUtils.readData("Partnerships", excelLabel.Variable_Name, "M10Partnership1",
				excelLabel.PartnerShip_Name);
		if (bp.clickOnTab(TabName.PartnershipsTab)) {
			String fundName = ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M10Fund1", excelLabel.Fund_Name);
			if (pp.createPartnership(environment, mode,partnershipName, fundName)) {
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

		for (int i = 0; i < 2; i++) {
			String lpName;
			partnershipName = null;
			if (i == 0) {
				lpName = ExcelUtils.readData("Limited Partner", excelLabel.Variable_Name, "M10LimitedPartner1",
						excelLabel.LimitedPartner_Name);
			} else {
				lpName = ExcelUtils.readData("Limited Partner", excelLabel.Variable_Name, "M10LimitedPartner2",
						excelLabel.LimitedPartner_Name);
			}
			if (bp.clickOnTab(TabName.CommitmentsTab)) {
				partnershipName = ExcelUtils.readData("Partnerships", excelLabel.Variable_Name, "M10Partnership1",
						excelLabel.PartnerShip_Name);

				if (cmp.createCommitment(environment, mode,lpName, partnershipName, "M10Commitment" + (i + 1), null)) {
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
		if (nim.getMyProfileFistNameAndLastNameAndFirmName("User1")) {
			appLog.info("CRM User first,last name and firm name successfully write in excel");
		} else {
			appLog.error("Not able to write CRM User first,last name and firm profile in excel");
			saa.assertTrue(false, "Not able to write CRM User first,last name and firm profile in excel");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M10tc002_CreateFundraisingAndInvestorWorkspaceForFund1AndVerifyManageEmailIcon() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String[] step1of3 = {ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M10Fund1", excelLabel.Fund_Size), ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M10Fund1", excelLabel.Fund_VintageYear),
				ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M10Fund1", excelLabel.Fund_Contact), ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M10Fund1", excelLabel.Fund_Phone),
				ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M10Fund1", excelLabel.Fund_Email), ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M10Fund1", excelLabel.Fund_Description) };
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M10FundName1)) {
				if (fp.buildWorkspace(step1of3, WorkSpaceAction.IMPORTFOLDERTEMPLATE, folderTemplateName, null,
						M10Institution1 + "<break>" + M10Institution2, Workspace.FundraisingWorkspace, 60)) {
					appLog.info("Fundraising Workspace is created successfully");
				} else {
					appLog.info("Fundraising workspace is not created successfully");
					saa.assertTrue(false, " Fundraising workspace is not craeted successfully");
				}
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
				System.err.println("Switched to frame.");
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
						"Fundraising Workspace View.");
				if (fp.getmanageEmails(Workspace.FundraisingWorkspace, 60) != null) {
					appLog.info("Manage emails icon is displaying for fundraising workspace");
				} else {
					appLog.info("Manage emails icon is not displaying for fundraisng workspace");
					saa.assertTrue(false, "Manage emails icon is not displaying for fundraisng workspace");
				}
				switchToDefaultContent(driver);
				if (fp.buildWorkspace(step1of3,
						WorkSpaceAction.IMPORTFOLDERTEMPLATE, folderTemplateName, null, M10Institution1 + "/"
								+ M10LimitedPartner1 + "<break>" + M10Institution2 + "/" + M10LimitedPartner2,
						Workspace.InvestorWorkspace, 60)) {
					appLog.info("Investor Workspace is created successfully");
				} else {
					appLog.info("Investor workspace is not created successfully");
					saa.assertTrue(false, " Investor workspace is not craeted successfully");
				}
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
				System.err.println("Switched to frame.");
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
						"Investor Workspace View.");
				if (fp.getmanageEmails(Workspace.InvestorWorkspace, 60) != null) {
					appLog.info("Manage emails icon is displaying for Investor workspace");
				} else {
					appLog.info("Manage emails icon is not displaying for Investor workspace");
					saa.assertTrue(false, "Manage emails icon is not displaying for Investor workspace");
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
		lp.CRMlogout(environment, mode);
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M10tc003_VerifyManageEmailPopUpUI() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M10FundName1)) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
				System.err.println("Switched to frame.");
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
						"Fundraising Workspace View.");
				if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage emails icon",
						action.SCROLLANDBOOLEAN)) {
					saa = fp.verifyManageEmailUI();
					if (click(driver, fp.getManageEmailCancelBtn(60), "Cancel button", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on manage email cancel buttton");
					} else {
						appLog.info("Not able to click on close button");
						sa.assertTrue(false, "Not able to clickon manage email cancel button");
					}
					sa.combineAssertions(saa);
				} else {
					appLog.info("Not able to click on manage emails icon");
					sa.assertTrue(false, "Not able to click on manage emails icon");
				}
				switchToDefaultContent(driver);
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
				System.err.println("Switched to frame.");
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
						"Investor Workspace View.");
				if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage emails icon",
						action.SCROLLANDBOOLEAN)) {
					saa = fp.verifyManageEmailUI();
					if (click(driver, fp.getManageEmailCancelBtn(60), "Cancel button", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on manage email cancel buttton");
					} else {
						appLog.info("Not able to click on close button");
						sa.assertTrue(false, "Not able to clickon manage email cancel button");
					}
					sa.combineAssertions(saa);
				} else {
					appLog.info("Not able to click on manage emails icon");
					sa.assertTrue(false, "Not able to click on manage emails icon");
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
		lp.CRMlogout(environment, mode);
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M10tc004_CheckVariousErrorMessageOnManageEmails() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M10FundName1)) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
				System.err.println("Switched to frame.");
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
						"Fundraising Workspace View.");
				if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage emails icon",
						action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getManageEmailsCloseIcon(60), "Close icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on close icon");
						if (fp.getManageEmailsHeader(10) == null) {
							appLog.info("Manage email popup get closed");
						} else {
							appLog.error("Manage email popup does not get closed");
							sa.assertTrue(false, "manage email popup does not get closed");
						}
					} else {
						appLog.error("Not able to click on close icon");
						sa.assertTrue(false, "Not able to click on close icon");
					}
				} else {
					appLog.error("Not able to click on manage emails");
					sa.assertTrue(false, "Not able to click on manage emails");
				}
				if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage emails icon",
						action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getManageEmailCancelBtn(60), "Cancel button", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Cancel button");
						if (fp.getManageEmailsHeader(10) == null) {
							appLog.info("Manage email popup get closed");
						} else {
							appLog.error("Manage email popup does not get closed");
							sa.assertTrue(false, "manage email popup does not get closed");
						}
					} else {
						appLog.error("Not able to click on Cancel button");
						sa.assertTrue(false, "Not able to click on Cancel button");
					}
				} else {
					appLog.error("Not able to click on manage emails");
					sa.assertTrue(false, "Not able to click on manage emails");
				}
				if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage emails icon",
						action.SCROLLANDBOOLEAN)) {
					String contactaccessDropdownOption = getSelectedOptionOfDropDown(driver,
							fp.getManageEmailContactAccessViewDropDownList(60), "Contact access view dropodown",
							"text");
					if (contactaccessDropdownOption.equalsIgnoreCase("All Folders")) {
						appLog.info("All folder option is displaying in the contact access view dropdown ");
					} else {
						appLog.error("All folder option is not displaying in contact access view dropdown");
						sa.assertTrue(false, "All folder option is not displaying in contact access view dropdown");
					}
					if (click(driver, fp.getManageEmailCancelBtn(60), "Cancel button", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Cancel button");
					} else {
						appLog.error("Not able to click on Cancel button");
						sa.assertTrue(false, "Not able to click on Cancel button");
					}
				} else {
					appLog.info("Not able to click on manage emails");
					sa.assertTrue(false, "Not able to click on manage emails");
				}
				if (fp.verifyFolderPathdummy("", M10Institution1, null, M10FundName1, PageName.FundsPage,
						Workspace.FundraisingWorkspace, 60)) {
					if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage emails icon",
							action.SCROLLANDBOOLEAN)) {
						String contactaccessDropdownOption = getSelectedOptionOfDropDown(driver,
								fp.getManageEmailContactAccessViewDropDownList(60), "Contact access view dropodown",
								"text");
						if (contactaccessDropdownOption.equalsIgnoreCase(M10Institution1)) {
							appLog.info(
									M10Institution1 + " is selected by default in the contact access view dropdown ");
						} else {
							appLog.info(
									M10Institution1 + " is not selected by default in contact access view dropdown");
							sa.assertTrue(false,
									M10Institution1 + " is not selected by default in contact access view dropdown");
						}
						List<WebElement> contactAccessViewDropdownValues = allOptionsInDropDrop(driver,
								fp.getManageEmailContactAccessViewDropDownList(60), "Show dropdown values");
						for (int i = 0; i < contactAccessViewDropdownValues.size(); i++) {
							if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase(M10Institution1)) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else
								if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase("All Folders")) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else {
								appLog.error(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
								sa.assertTrue(false, contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
							}
						}
						if ( fp.clickUsingCssSelectorPath("a[title=Send]", "send button")) {
						//if (click(driver, fp.getmanageEmailsendBtn(60), "Send Button", action.SCROLLANDBOOLEAN)) {
							String alertmessage = switchToAlertAndGetMessage(driver, 60, action.GETTEXT);
							if (alertmessage.equalsIgnoreCase(
									FundsPageErrorMessage.pleaseSelectOneInvestorErrorMessageInManageEmails)) {
								appLog.info("Please select one investor eror message is verified");
							} else {
								appLog.error("Please select one invetsor error mesage is not verified");
								sa.assertTrue(false, "Please select one investor error message is not verified");
							}
							switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
						} else {
							appLog.error("Not able to click on send button");
							sa.assertTrue(false, "Not able to click on send button");
						}
						if (click(driver, fp.getManageEmailCustomRadioButton(60), "Custom email radio button",
								action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageEmailCustomTemplateCancelButton(60),
									"Manage Email custom template cancel button", action.SCROLLANDBOOLEAN)) {
								if ( fp.clickUsingCssSelectorPath("a[title=Send]", "send button")) {
								//if (click(driver, fp.getmanageEmailsendBtn(60), "Send Button",
								//		action.SCROLLANDBOOLEAN)) {
									String alertmessage = switchToAlertAndGetMessage(driver, 60, action.GETTEXT);
									if (alertmessage.equalsIgnoreCase(
											FundsPageErrorMessage.pleaseSelectOneInvestorErrorMessageInManageEmails)) {
										appLog.info("Please select one investor eror message is verified");
									} else {
										appLog.error("Please select one invetsor error mesage is not verified");
										sa.assertTrue(false,
												"Please select one investor error message is not verified");
									}
									switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
								} else {
									appLog.info("Not able to click on send button");
									sa.assertTrue(false, "Not able to click on send button");
								}
								//if ( fp.clickUsingCssSelectorPath("div#searchIddiv > a", "search button")) {
								if (clickUsingJavaScript(driver, fp.getManageEmailSearchBtn(60), "Search button",
										action.SCROLLANDBOOLEAN)) {
									String alertmessage = switchToAlertAndGetMessage(driver, 60, action.GETTEXT);
									if (alertmessage.equalsIgnoreCase(
											FundsPageErrorMessage.pleaseEnterAValueErrorMessageInManageEmails)) {
										appLog.info("Please enter a value eror message is verified");
									} else {
										appLog.info("Please enter a value error mesage is not verified");
										sa.assertTrue(false, "Please enter a value error message is not verified");
									}
									switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
								} else {
									appLog.info("Not able to click on search icon");
									sa.assertTrue(false, "Not able to click on search icon");
								}
							} else {
								appLog.info("Not able to click on manage email custom template cancel button");
								sa.assertTrue(false, "Not able to click on manage email custom template cancel button");
							}
						} else {
							appLog.info("Not able to click on custom email radio button");
							sa.assertTrue(false, "Not able to click on custom email radio button");
						}
					} else {
						appLog.info("Not able to click on manage emails");
						sa.assertTrue(false, "Not able to click on manage emails");
					}
				} else {
					appLog.info("Not able to select institution");
					sa.assertTrue(false, "Not able ot select institution");
				}
			} else {
				appLog.info("Not able to click on created fund");
				sa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on Funds tab");
			sa.assertTrue(false, "Not able to click on Funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}

	@Test
	public void M10tc005_CheckPreviewAndEditLinkForInvitationEmailTemplateOption() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M10FundName1)) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
				System.err.println("Switched to frame.");
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
						"Fundraising Workspace View.");
				if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage emails icon",
						action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getManageEmailInvitaionEmailPreviewLink(60), "Preview link",
							action.SCROLLANDBOOLEAN)) {
						String headertext = fp.getGetManageEmailInvitationMailPreviewHeader(60).getText().trim();
						if (headertext.equalsIgnoreCase("Preview")) {
							appLog.info("Preview popup is displaying");
						} else {
							appLog.info("Preview popup is not displaying");
							sa.assertTrue(false, "Preview popup is displaying");
						}
						if (fp.getManageEmailPreviewFormEmailIdText(60).getText().trim().contains(CRMUser1EmailID)) {
							appLog.info("Manage Email Invitation Email Preview Form Email Id is verified.");
						} else {
							appLog.info("Manage Email Invitation Email Preview Form Email Id is not verified.");
							sa.assertTrue(false,
									"Manage Email Invitation Email Preview Form Email Id is not verified.");
						}
						if (fp.getManageEmailPreviewSubjectText(60).getText().trim().contains("Invitation from")) {
							appLog.info("Manage Email Invitation Email Preview Subjetc Text is verified.");
						} else {
							appLog.info("Manage Email Invitation Email Preview Subjetc Text is not verified.");
							sa.assertTrue(false, "Manage Email Invitation Email Preview Subjetc Text is not verified.");
						}
						if (fp.getManageEmailPreviewSubjectText(60).getText().trim().contains(Org1FirmName)) {
							appLog.info("Manage Email Invitation Email Preview Subjetc Text is  verified.");
						} else {
							appLog.info("Manage Email Invitation Email Preview Subjetc Text is not verified.");
							sa.assertTrue(false, "Manage Email Invitation Email Preview Subjetc Text is not verified.");
						}
						if (fp.getManageEmailPreviewHelloText(60).getText().trim().contains("Hello")) {
							appLog.info("Manage Email Invitation Email Preview Hello Text is verified.");
						} else {
							appLog.info("Manage Email Invitation Email Preview Hello Text is not verified.");
							sa.assertTrue(false, "Manage Email Invitation Email Preview Hello Text is not verified.");
						}
						ThreadSleep(2000);
						if (fp.getManageEmailPreviewHelloText(60).getText().trim().contains("Recipient first Name},")) {
							appLog.info(
									"Manage Email Invitation Email Preview Recipient First Name Text is verified.");
						} else {
							appLog.info(
									"Manage Email Invitation Email Preview Recipient First Name Text is not verified.");
							sa.assertTrue(false,
									"Manage Email Invitation Email Preview Recipient First Name Text is not verified.");
						}
						if (fp.getManageEmailPreviewGrantAccessText(60).getText().trim().contains(
								"You have been granted access to Potential investments of " + M10FundName1 + " by")) {
							appLog.info("Manage Email Invitation Email Preview Grant Access Text is verified");
						} else {
							appLog.info("Manage Email Invitation Email Preview Grant Access Text is not verified");
							sa.assertTrue(false,
									"Manage Email Invitation Email Preview Grant Access Text is not verified");
						}
						if (fp.getManageEmailPreviewRegisterNotRegisterText(60).getText().trim()
								.contains("If you have not yet registered, Click here to register.")) {
							appLog.info(
									"Manage Email Invitation Email Preview If you have not yet registered Text is verified.");
						} else {
							appLog.info(
									"Manage Email Invitation Email Preview If you have not yet registered Text is not verified.");
							sa.assertTrue(false,
									"Manage Email Invitation Email Preview If you have not yet registered Text is not verified.");
						}
						if (fp.getManageEmailPreviewRegisterNotRegisterText(60).getText().trim()
								.contains("If you have already registered, Click here to login")) {
							appLog.info(
									"Manage Email Invitation Email Preview If you have already registered Text is verified.");
						} else {
							appLog.info(
									"Manage Email Invitation Email Preview If you have already registered Text is not verified.");
							sa.assertTrue(false,
									"Manage Email Invitation Email Preview If you have already registered Text is not verified.");
						}
						if (fp.getManageEmailPreviewBottomText(60).getText().trim().contains(
								"If you believe this has been sent in error, or if you cannot login, please contact")) {
							appLog.info("Manage Email Invitation Email Preview Bottom Text is verified.");
						} else {
							appLog.info("Manage Email Invitation Email Preview Bottom Text is not verified.");
							sa.assertTrue(false, "Manage Email Invitation Email Preview Bottom Text is not verified.");
						}
						if (fp.getManageEmailPreviewBottomText(60).getText().trim().contains(Org1FirmName)) {
							appLog.info("Manage Email Invitation Email Preview Bottom Text is verified.");
						} else {
							appLog.info("Manage Email Invitation Email Preview Bottom Text is not verified."+fp.getManageEmailPreviewBottomText(60).getText().trim()+" and "+Org1FirmName);
							sa.assertTrue(false, "Manage Email Invitation Email Preview Bottom Text is not verified.");
						}
						if (isDisplayed(driver, fp.getManageEmailPreviewClosebtn(60), "Visibility", 30,
								"Close Button") != null) {
							appLog.info("Close Button in preview link is displaying");
						} else {
							appLog.info("Close Button in preview link is not displaying");
							sa.assertTrue(false, "Close Button in preview link is not displaying");
						}
						if (isDisplayed(driver, fp.getManageEmailPreviewCloseIcon(60), "Visibility", 30,
								"Cross icon") != null) {
							appLog.info("Cross icon in preview link is displaying");
						} else {
							appLog.info("Cross icon in preview link is not displaying");
							sa.assertTrue(false, "Cross icon in preview link is not displaying");
						}
						if (fp.getManageEmailPreviewNotRegisteredClickHereLink(20) != null) {
							if (click(driver, fp.getManageEmailPreviewNotRegisteredClickHereLink(30),
									"Not Registered Click here Link", action.SCROLLANDBOOLEAN)) {
								String parentid = switchOnWindow(driver);
								sa.assertTrue(getURL(driver, 60).contains("SiteRegisteration"),
										"Registration Page is not open after clicking on Resgister Click Here Link.");
								driver.close();
								driver.switchTo().window(parentid);
								switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
							} else {
								appLog.info("Not able to click on not registered click here link");
								sa.assertTrue(false, "Not able to click on not registered click here link");
							}
						} else {
							appLog.info(
									"Not Registered Click Here Link is not clickable on Manage Email Invitation Preview Pop Up.");
							sa.assertTrue(false,
									"Not Registered Click Here Link is not clickable on Manage Email Invitation Preview Pop Up.");
						}
						if (fp.getManageEmailPreviewRegisteredClickHereLink(30) != null) {
							if (click(driver, fp.getManageEmailPreviewRegisteredClickHereLink(30),
									"Not Registered Click here Link", action.SCROLLANDBOOLEAN)) {
								String parentid = switchOnWindow(driver);
								sa.assertTrue(getURL(driver, 60).contains("IP_login"),
										"Registration Page is not open after clicking on Resgister Click Here Link.");
								driver.close();
								driver.switchTo().window(parentid);
								switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
							} else {
								appLog.info("Not able to click on Registered Click Here Link");
								sa.assertTrue(false, "Not able to click on Registered Click Here Link");
							}
						} else {
							appLog.info(
									"Registered Click Here Link is not clickable on Manage Email Invitation Preview Pop Up.");
							sa.assertTrue(false,
									"Registered Click Here Link is not clickable on Manage Email Invitation Preview Pop Up.");
						}
						if (click(driver, fp.getManageEmailPreviewCloseIcon(30), "Close Button",
								action.SCROLLANDBOOLEAN)) {
							if (fp.getManageEmailPreviewCloseIcon(5) == null) {
								appLog.info("Manage Emails preview Pop Up is closed");
							} else {
								appLog.info("Manage Emails preview Pop Up is not closed");
								sa.assertTrue(false, "Manage Emails preview Pop Up is not closed");
							}
						} else {
							appLog.info("Not able to click on close button of preview popup");
							sa.assertTrue(false, "Not able to click on close button of preview popup");
						}
					} else {
						appLog.info("Not able to click on preview link");
						sa.assertTrue(false, "Not able to click on preview link");
					}
					if (click(driver, fp.getInvitationEmailEditLinkInManageEmails(30),
							"Manage Email Invitation Edit Link", action.SCROLLANDBOOLEAN)) {
						if (fp.getManageEmailEditFormEmailIdtext(60).getText().trim().contains(CRMUser1EmailID)) {
							appLog.info("Manage Email Invitation Email Edit Form Email Id is verified.");
						} else {
							appLog.info("Manage Email Invitation Email Edit Form Email Id is not verified.");
							sa.assertTrue(false, "Manage Email Invitation Email Edit Form Email Id is not verified.");
						}
						if (fp.getManageEmailEditSubjectText(60).getText().trim().contains("Invitation from")) {
							appLog.info("Manage Email Invitation Email Edit Subjetc Text is verified.");
						} else {
							appLog.info("Manage Email Invitation Email Edit Subjetc Text is not verified.");
							sa.assertTrue(false, "Manage Email Invitation Email Edit Subjetc Text is not verified.");
						}
						if (fp.getManageEmailEditSubjectText(60).getText().trim().contains(Org1FirmName)) {
							appLog.info("Manage Email Invitation Email Edit Subjetc Text is  verified.");
						} else {
							appLog.info("Manage Email Invitation Email Edit Subjetc Text is not verified.");
							sa.assertTrue(false, "Manage Email Invitation Email Edit Subjetc Text is not verified.");
						}
						if (fp.getManageEmailEditHelloText(60).getText().trim().contains("Hello")) {
							appLog.info("Manage Email Invitation Email Edit Hello Text is verified.");
						} else {
							appLog.info("Manage Email Invitation Email Edit Hello Text is not verified.");
							sa.assertTrue(false, "Manage Email Invitation Email Edit Hello Text is not verified.");
						}
						if (fp.getManageEmailEditHelloText(60).getText().trim().contains("Recipient first Name},")) {
							appLog.info("Manage Email Invitation Email Edit Recipient First Name Text is verified.");
						} else {
							appLog.info(
									"Manage Email Invitation Email Edit Recipient First Name Text is not verified.");
							sa.assertTrue(false,
									"Manage Email Invitation Email Edit Recipient First Name Text is not verified.");
						}
						if (fp.getGetManageEmailEditGrantAccessText(60).getText().trim().contains(
								"You have been granted access to Potential investments of " + M10FundName1 + " by")) {
							appLog.info("Manage Email Invitation Email Edit Grant Access Text is verified.");
						} else {
							appLog.info("Manage Email Invitation Email Edit Grant Access Text is not verified.");
							sa.assertTrue(false,
									"Manage Email Invitation Email Edit Grant Access Text is not verified.");
						}
						if (fp.getGetManageEmailEditGrantAccessText(60).getText().trim().contains(Org1FirmName)) {
							appLog.info("Manage Email Invitation Email Edit Grant Access Text is verified.");
						} else {
							appLog.info("Manage Email Invitation Email Edit Grant Access Text is not verified.");
							sa.assertTrue(false,
									"Manage Email Invitation Email Edit Grant Access Text is not verified.");
						}
						if (fp.getManageEmailEditRegisterNotRegisterText(60).getText().trim()
								.contains("If you have not yet registered, Click here to register.")) {
							appLog.info(
									"Manage Email Invitation Email Edit If you have not yet registered Text is verified.");
						} else {
							appLog.info(
									"Manage Email Invitation Email Edit If you have not yet registered Text is not verified.");
							sa.assertTrue(false,
									"Manage Email Invitation Email Edit If you have not yet registered Text is not verified.");
						}
						if (fp.getManageEmailEditRegisterNotRegisterText(60).getText().trim()
								.contains("If you have already registered, Click here to login")) {
							appLog.info(
									"Manage Email Invitation Email Edit If you have already registered Text is verified.");
						} else {
							appLog.info(
									"Manage Email Invitation Email Edit If you have already registered Text is not verified.");
							sa.assertTrue(false,
									"Manage Email Invitation Email Edit If you have already registered Text is not verified.");
						}
						if (fp.getManageEmailEditBottomText(60).getText().trim().contains(
								"If you believe this has been sent in error, or if you cannot login, please contact")) {
							appLog.info("Manage Email Invitation Email Edit Bottom Text is verified.");
						} else {
							appLog.info("Manage Email Invitation Email Edit Bottom Text is not verified.");
							sa.assertTrue(false, "Manage Email Invitation Email Edit Bottom Text is not verified.");
						}
						if (fp.getManageEmailEditBottomText(60).getText().trim().contains(Org1FirmName)) {
							appLog.info("Manage Email Invitation Email Edit Bottom Text is verified.");
						} else {
							appLog.info("Manage Email Invitation Email Edit Bottom Text is not verified.");
							sa.assertTrue(false, "Manage Email Invitation Email Edit Bottom Text is not verified.");
						}
						if (isDisplayed(driver, fp.getManageEmailInvitationTextBoxApplyButton(60), "Visibility", 60,
								"Apply Button") != null) {
							appLog.info("Apply button is displaying");
						} else {
							appLog.info("Apply button is not displaying");
							sa.assertTrue(false, "Apply button is not displaying");
						}
						if (isDisplayed(driver, fp.getManageEmailInvitationTextBoxCancelButton(60), "Visibility", 60,
								"Cancel Button") != null) {
							appLog.info("Cancel button is displaying");
						} else {
							appLog.info("Cancel button is not displaying");
							sa.assertTrue(false, "Cancel button is not displaying");
						}
						if (isDisplayed(driver, fp.getManageEmailEditTemplateCloseIcon(60), "Visibility", 60,
								"Close Icon") != null) {
							appLog.info("Close Icon is displaying");
						} else {
							appLog.info("Close Icon is not displaying");
							sa.assertTrue(false, "Close Icon is not displaying");
						}
						if (click(driver, fp.getManageEmailInvitationTextBoxCancelButton(30), "Cancel button",
								action.SCROLLANDBOOLEAN)) {
							if (fp.getManageEmailInvitationTextBoxCancelButton(10) == null) {
								appLog.info("Edit popup get closed");
							} else {
								appLog.info("Edit popup does not get closed");
								sa.assertTrue(false, "Edit popup does not get closed");
							}
						} else {
							appLog.info("Clicked on cancel button");
							sa.assertTrue(false, "Clicked on cancel button");
						}
						if (click(driver, fp.getInvitationEmailEditLinkInManageEmails(30),
								"Manage Email Invitation Edit Link", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageEmailEditTemplateCloseIcon(30), "Close icon",
									action.SCROLLANDBOOLEAN)) {
								if (fp.getManageEmailEditTemplateCloseIcon(10) == null) {
									appLog.info("Edit popup get closed");
								} else {
									appLog.info("Edit popup does not get closed");
									sa.assertTrue(false, "Edit popup does not get closed");
								}
							} else {
								appLog.info("Clicked on Close icon");
								sa.assertTrue(false, "Clicked on Close icon");
							}
						} else {
							appLog.info("Not able to click on edit icon");
							sa.assertTrue(false, "Not able to click on edit icon");
						}
					} else {
						appLog.info("Not able to click on manage email edit link");
						sa.assertTrue(false, "Not able to click on manage email edit link");
					}
					if (click(driver, fp.getInvitationEmailEditLinkInManageEmails(30),
							"Manage Email Invitation Edit Link", action.SCROLLANDBOOLEAN)) {
						if (fp.getManageEmailEditNotRegisteredClickHereLink(20) != null) {
							click(driver, fp.getManageEmailEditNotRegisteredClickHereLink(30),
									"Not Registered Click here Link", action.SCROLLANDTHROWEXCEPTION);
							String parentid = switchOnWindow(driver);
							sa.assertTrue(getURL(driver, 60).contains("SiteRegisteration"),
									"Registration Page is not open after clicking on Resgister Click Here Link.");
							driver.close();
							driver.switchTo().window(parentid);
							switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));

						} else {
							appLog.info(
									"Not Registered Click Here Link is not clickable on Manage Email Invitation Edit Pop Up.");
							sa.assertTrue(false,
									"Not Registered Click Here Link is not clickable on Manage Email Invitation Edit Pop Up.");
						}
						if (fp.getManageEmailEditRegisteredClickHereLink(30) != null) {
							click(driver, fp.getManageEmailEditRegisteredClickHereLink(30),
									"Not Registered Click here Link", action.SCROLLANDTHROWEXCEPTION);
							String parentid = switchOnWindow(driver);
							sa.assertTrue(getURL(driver, 60).contains("IP_login"),
									"Registration Page is not open after clicking on Resgister Click Here Link.");
							driver.close();
							driver.switchTo().window(parentid);
							switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));

						} else {
							appLog.info(
									"Registered Click Here Link is not clickable on Manage Email Invitation Edit Pop Up.");
							sa.assertTrue(false,
									"Registered Click Here Link is not clickable on Manage Email Invitation Edit Pop Up.");
						}
						if (sendKeys(driver, fp.getManageEmailInvitationDescriptionTextBox(60), "This is test mail",
								"Manage Email Edit Template Text Box", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageEmailInvitationTextBoxApplyButton(30), "Apply button",
									action.SCROLLANDBOOLEAN)) {
								ThreadSleep(3000);
								if (click(driver, fp.getManageEmailInvitaionEmailPreviewLink(30),
										"Manage Emails Preview Link", action.SCROLLANDBOOLEAN)) {
									ThreadSleep(3000);
									if (fp.getManageEmailPreviewTextMassage(30).getText().trim()
											.contains("This is test mail")) {
										appLog.info("Manage Email Preview Text Message is verified");
									} else {
										appLog.info("Manage Email Preview Text Message is not verified");
										sa.assertTrue(false, "Manage Email Preview Text Message is not verified");
									}
									if (click(driver, fp.getManageEmailPreviewClosebtn(30), "Close Button",
											action.SCROLLANDBOOLEAN)) {
										appLog.info("Clicked on close button");
									} else {
										appLog.info("Not able to click on preview close button");
										sa.assertTrue(false, "Not able to click on preview close button");
									}
								} else {
									appLog.info("Not able to click on preview link");
									sa.assertTrue(false, "Not able to click on preview link");
								}
							} else {
								appLog.info("Not able to click on apply button");
								sa.assertTrue(false, "Not able to click on apply button");
							}
						} else {
							appLog.info("Not able to enter value in description tetx box");
							sa.assertTrue(false, "Not able to enter value in description tetx box");
						}
					} else {
						appLog.info("Not able to click on manage email edit link");
						sa.assertTrue(false, "Not able to click on manage email edit link");
					}
					if (click(driver, fp.getInvitationEmailEditLinkInManageEmails(30),
							"Manage Email Invitation Edit Link", action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, fp.getManageEmailInvitationDescriptionTextBox(60), "",
								"Manage Email Edit Template Text Box", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageEmailInvitationTextBoxApplyButton(30), "Apply button",
									action.SCROLLANDBOOLEAN)) {
								ThreadSleep(3000);
								if (click(driver, fp.getManageEmailInvitaionEmailPreviewLink(30),
										"Manage Emails Preview Link", action.SCROLLANDBOOLEAN)) {
									ThreadSleep(3000);
									if (fp.getManageEmailPreviewTextMassage(30) == null) {
										appLog.info("Text Message is removed form manage email preview template.");
									} else {
										appLog.info("Manage Email Preview Text Message is not verified");
										sa.assertTrue(false,
												"Manage Email Preview Text Message is not verified. Expected: Blank text Box");
									}
									if (click(driver, fp.getManageEmailPreviewClosebtn(30), "Close Button",
											action.SCROLLANDBOOLEAN)) {
										appLog.info("Clicked on close button");
									} else {
										appLog.info("Not able to click on preview close button");
										sa.assertTrue(false, "Not able to click on preview close button");
									}
								} else {
									appLog.info("Not able to click on preview link");
									sa.assertTrue(false, "Not able to click on preview link");
								}
							} else {
								appLog.info("Not able to click on apply button");
								sa.assertTrue(false, "Not able to click on apply button");
							}
						} else {
							appLog.info("Not able to enter value in description tetx box");
							sa.assertTrue(false, "Not able to enter value in description tetx box");
						}
					} else {
						appLog.info("Not able to click on manage email edit link");
						sa.assertTrue(false, "Not able to click on manage email edit link");
					}
					if (click(driver, fp.getInvitationEmailEditLinkInManageEmails(30),
							"Manage Email Invitation Edit Link", action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, fp.getManageEmailInvitationDescriptionTextBox(60),
								"This is Max Data Text to Test this area. This is Max Data Text to Test this area. This is Max Data Text to Test this area. This is Max Data Text to Test this area. This is Max Data Text to Test this area. This is Max Data Text to Test this area. This is Max",
								"Manage Email Edit Template Text Box", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageEmailInvitationTextBoxApplyButton(30), "Apply button",
									action.SCROLLANDBOOLEAN)) {
								ThreadSleep(3000);
								if (fp.getManageEmailInvitationemailEditErrorMessage(60).getText().trim()
										.contains(FundsPageErrorMessage.manageEmailInvitationEditBodyErrorMessage)) {
									appLog.info("Error Message is verified");
								} else {
									appLog.info("Error Message is not verified");
									sa.assertTrue(false, "Error Message is not verified");
								}
							} else {
								appLog.info("Not able to click on apply button");
								sa.assertTrue(false, "Not able to click on apply button");
							}
						} else {
							appLog.info("Not able to enter value in description tetx box");
							sa.assertTrue(false, "Not able to enter value in description tetx box");
						}
					} else {
						appLog.info("Not able to click on manage email edit link");
						sa.assertTrue(false, "Not able to click on manage email edit link");
					}
				} else {
					appLog.info("Not able to click on manage emails");
					sa.assertTrue(false, "Not able to click on manage emails");
				}
			} else {
				appLog.info("Not able to click on created fund");
				sa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on Funds tab");
			sa.assertTrue(false, "Not able to click on Funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}

	@Test
	public void M10tc006_CheckPreviewAndEditLinkForCustomEmailTemplateOption() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M10FundName1)) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
				System.err.println("Switched to frame.");
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
						"Fundraising Workspace View.");
				if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage emails icon",
						action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getManageEmailCustomRadioButton(60), "Custom template radio button",
							action.SCROLLANDBOOLEAN)) {
						if (fp.getManageEmailCustomSubjectTextBox(20) != null) {
							appLog.info("Subject Text Box is available in custom email pop up.");
						} else {
							appLog.info("Subject Text Box is not available in custom email pop up.");
							sa.assertTrue(false, "Subject Text Box is not available in custom email pop up.");
						}
						if (fp.getManageEmailCustomTemplateEditEmailIdText(30).getText().trim()
								.contains(CRMUser1EmailID)) {
							appLog.info("Manage Emails Custom Template Form Email id is verified.");
						} else {
							appLog.info("Manage Emails Custom Template Form Email id is not verified.");
							sa.assertTrue(false, "Manage Emails Custom Template Form Email id is not verified.");
						}
						switchToFrame(driver, 30, fp.getManageEmailCustomSubjectBodyFrame(30));
						if (fp.getManageEmailCustomSubjectBody(20) != null) {
							appLog.info("Body Rich Text is available in the Custom Template Pop Up");
						} else {
							appLog.info("Body Rich Text is not available in the Custom Template Pop Up");
							sa.assertTrue(false, "Body Rich Text is not available in the Custom Template Pop Up");
						}
						switchToDefaultContent(driver);
						switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
						if (fp.getManageEmailCustomApplyButton(20) != null) {
							appLog.info("Apply Button is available.");
						} else {
							appLog.info("Apply Button is available.");
							sa.assertTrue(false, "Apply Button is not available.");
						}
						if (fp.getManageEmailCustomTextBoxCancelButton(20) != null) {
							appLog.info("Cancel Button is available");
						} else {
							appLog.info("Cancel Button is not available");
							sa.assertTrue(false, "Cancel Button is not available");
						}
						if (fp.getManageEmailCustomCloseIcon(20) != null) {
							appLog.info("Close Icon is available");
						} else {
							appLog.info("Close Icon is not available");
							sa.assertTrue(false, "Close Icon is not available");
						}
						if (click(driver, fp.getManageEmailCustomApplyButton(20), "Apply Button",
								action.SCROLLANDBOOLEAN)) {
							if (fp.getManageEmailEditSubjectErrorMessage(20).getText().trim()
									.contains(FundsPageErrorMessage.pleaseEnterAValueErrorMessageInManageEmails)) {
								appLog.info("Custom Email Template Blank Subject Error Message is verified.");
							} else {
								appLog.info("Custom Email Template Blank Subject Error Message is not verified.");
								sa.assertTrue(false,
										"Custom Email Template Blank Subject Error Message is not verified.");
							}
							if (fp.getManageEmailCustomEditBodyErrorMessage(20).getText().trim()
									.contains(FundsPageErrorMessage.pleaseEnterAValueErrorMessageInManageEmails)) {
								appLog.info("Custom Email Template Blank Subject Error Message is verified.");
							} else {
								appLog.info("Custom Email Template Blank Subject Error Message is not verified.");
								sa.assertTrue(false,
										"Custom Email Template Blank Subject Error Message is not verified.");
							}
						} else {
							appLog.info("Not able to click on apply button");
							sa.assertTrue(false, "Not able to clcik on apply button");
						}
						if (click(driver, fp.getManageEmailCustomCloseIcon(20), "Close Icon",
								action.SCROLLANDBOOLEAN)) {
							if (fp.getManageEmailCustomCloseIcon(20) == null) {
								appLog.info("Custom Email Template PopUp is closed");
							} else {
								appLog.info("Custom Email Template PopUp is not closed");
								sa.assertTrue(false, "Custom Email Template PopUp is not closed");
							}
						} else {
							appLog.info("Clicked on close icon");
							sa.assertTrue(false, "Not able to click on close icon");
						}
						if (isEnabled(driver, fp.getCustomEmailEditLinkInManageEmails(60), "Edit link")) {
							appLog.info("Edit link is enabled");
						} else {
							appLog.info("Edit link is not enabled");
							sa.assertTrue(false, "Edit link is not enabled");
						}
						if (isEnabled(driver, fp.getManageEmailCustomPreviewLink(60), "Preview link")) {
							appLog.info("Preview link is enabled");
						} else {
							appLog.info("Preview link is not enabled");
							sa.assertTrue(false, "Preview link is not enabled");
						}
						if (isEnabled(driver, fp.getInvitationEmailEditLinkInManageEmails(60), "Edit link")) {
							appLog.info("Edit link of invitation template is not enabled");
						} else {
							appLog.info("Edit link of invitation template is enabled");
							sa.assertTrue(false, "Edit link of invitation template is enabled");
						}
						if (isEnabled(driver, fp.getManageEmailInvitaionEmailPreviewLink(60), "Preview link")) {
							appLog.info("Preview link of invitation template is not enabled");
						} else {
							appLog.info("Preview link of invitation template is enabled");
							sa.assertTrue(false, "Preview link of invitation template is enabled");
						}
					} else {
						appLog.info("Not able to click on manage email custom radio button");
						sa.assertTrue(false, "Not able to click on manage email custom radio button");					
					}
					if (click(driver, fp.getCustomEmailEditLinkInManageEmails(30), "Custom Email Template Edit Link",
							action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getManageEmailCustomTextBoxCancelButton(20), "Cancel button",
								action.SCROLLANDBOOLEAN)) {
							if (fp.getManageEmailCustomTextBoxCancelButton(20) == null) {
								appLog.info("Custom Email Template PopUp is closed");
							} else {
								appLog.info("Custom Email Template PopUp is not closed");
								sa.assertTrue(false, "Custom Email Template PopUp is not closed");
							}
						} else {
							appLog.info("Clicked on Cancel button");
							sa.assertTrue(false, "Not able to click on Cancel button");
						}
						if (isEnabled(driver, fp.getCustomEmailEditLinkInManageEmails(60), "Edit link")) {
							appLog.info("Edit link is enabled");
						} else {
							appLog.info("Edit link is not enabled");
							sa.assertTrue(false, "Edit link is not enabled");
						}
						if (isEnabled(driver, fp.getManageEmailCustomPreviewLink(60), "Preview link")) {
							appLog.info("Preview link is enabled");
						} else {
							appLog.info("Preview link is not enabled");
							sa.assertTrue(false, "Preview link is not enabled");
						}
						if (isEnabled(driver, fp.getInvitationEmailEditLinkInManageEmails(60), "Edit link")) {
							appLog.info("Edit link of invitation template is not enabled");
						} else {
							appLog.info("Edit link of invitation template is enabled");
							sa.assertTrue(false, "Edit link of invitation template is enabled");
						}
						if (isEnabled(driver, fp.getManageEmailInvitaionEmailPreviewLink(60), "Preview link")) {
							appLog.info("Preview link of invitation template is not enabled");
						} else {
							appLog.info("Preview link of invitation template is enabled");
							sa.assertTrue(false, "Preview link of invitation template is enabled");
						}
						
					} else {
						appLog.info("Not able to click on edit link");
						sa.assertTrue(false, "Not able to click on edit link");
					}
					if (click(driver, fp.getCustomEmailEditLinkInManageEmails(30), "Custom Email Template Edit Link",
							action.SCROLLANDBOOLEAN)) {
						ThreadSleep(2000);
						if (click(driver, fp.getManageEmailCustomTemplateEditEmailIdText(30),
								"Custom Email Template Edit Email ID", action.SCROLLANDBOOLEAN)) {
							if (!new NIMPageBusinessLayer(driver)
									.verifyNavatarSalesTeamLinkFunctionality("ContactEmailLink")) {
								appLog.info("Verification of Contact Email link is unsuccessfull.");
								sa.assertTrue(false, "Verification of Contact Email link is unsuccessfull.");
							} else {
								appLog.info("Verification of Contact Email Link is successfully");
							}
						} else {
							appLog.info("Not able to click on email id of user");
							sa.assertTrue(false, "Not able to click on email id of user");
						}
						if (sendKeys(driver, fp.getManageEmailCustomSubjectTextBox(30), "Test Subject",
								"Custom Email Subject Text Box", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageEmailCustomApplyButton(20), "Apply Button",
									action.SCROLLANDBOOLEAN)) {
								if (fp.getManageEmailCustomEditBodyErrorMessage(20).getText().trim()
										.contains(FundsPageErrorMessage.pleaseEnterAValueErrorMessageInManageEmails)) {
									appLog.info("Custom Email Template Blank Subject Error Message is verified.");
								} else {
									appLog.info("Custom Email Template Blank Subject Error Message is not verified.");
									sa.assertTrue(false,
											"Custom Email Template Blank Subject Error Message is not verified.");
								}
							} else {
								appLog.info("Not able to click on apply button");
								sa.assertTrue(false, "Not able to clcik on apply button");
							}
							switchToFrame(driver, 30, fp.getManageEmailCustomSubjectBodyFrame(30));
							if (sendKeys(driver, fp.getManageEmailCustomSubjectBody(20),
									"This is Test Mail Data for Email", "Custom Email Body Text Box",
									action.SCROLLANDBOOLEAN)) {
								switchToDefaultContent(driver);
								switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
								if (click(driver, fp.getManageEmailCustomApplyButton(20), "Apply Button",
										action.SCROLLANDBOOLEAN)) {
									appLog.info("click on apply button");
									if (fp.getManageEmailCustomApplyButton(20) == null) {
										appLog.info("Custom Email Template PopUp is closed with custom text.");
									} else {
										appLog.info("Custom Email Template PopUp is not closed with custom text.");
										sa.assertTrue(false,
												"Custom Email Template PopUp is not closed with custom text.");
									}
								} else {
									appLog.info("Not able to click on apply button");
									sa.assertTrue(false, "Not able to clcik on apply button");
								}
							} else {
								appLog.info("Not able to enter value in body text box");
								sa.assertTrue(false, "Not able to enter value in body text box");
							}
						} else {
							appLog.info("Not able to enter value in subject text box");
							sa.assertTrue(false, "Not able to enter value in subject text box");
						}
					} else {
						appLog.info("Not able to click on edit link");
						sa.assertTrue(false, "Not able to click on edit link");
					}
					if (click(driver, fp.getManageEmailCustomPreviewLink(30), "Custom Email Preview Link",
							action.SCROLLANDBOOLEAN)) {
						if (fp.getManageEmailCustomPreviewFormEmailId(20).getText().trim().contains(CRMUser1EmailID)) {
							appLog.info("Custom Email Preview Email Id Text is verified.");
						} else {
							appLog.info("Custom Email Preview Email Id Text is not verified.");
							sa.assertTrue(false, "Custom Email Preview Email Id Text is not verified.");
						}
						if (fp.getManageEmailCustomTemplateSubjectText(20).getText().trim().contains("Test Subject")) {
							appLog.info("Custom Email Subject is verified.");
						} else {
							appLog.info("Custom Email Subject is not verified.");
							sa.assertTrue(false, "Custom Email Subject is not verified.");
						}
						if (fp.getManageEmailCustomTemplateBodyText(20).getText().trim()
								.contains("This is Test Mail Data for Email")) {
							appLog.info("Custom Email Body Text is verified.");
						} else {
							appLog.info("Custom Email Body Text is not verified.");
							sa.assertTrue(false, "Custom Email Body Text is not verified.");
						}
						if (click(driver, fp.getManageEmailCustomPreviewCloseBtn(20), "Custom Email Id Close Button",
								action.SCROLLANDBOOLEAN)) {
							if (fp.getManageEmailCustomPreviewCloseBtn(20) == null) {
								appLog.info("Custom Email Template PopUp is closed with custom text.");
							} else {
								appLog.info("Custom Email Template PopUp is not closed with custom text.");
								sa.assertTrue(false, "Custom Email Template PopUp is not closed with custom text.");
							}
						} else {
							appLog.info("Not able to click on close button");
							sa.assertTrue(false, "Not able to click on close button");
						}
					} else {
						appLog.info("Not able to click on preview link");
						sa.assertTrue(false, "Not able to click on preview link");
					}
					if (click(driver, fp.getManageEmailCustomPreviewLink(30), "Custom Email Preview Link",
							action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getManageEmailCustomPreviewCloseIcon(20), "Custom Email Id Close icon",
								action.SCROLLANDBOOLEAN)) {
							if (fp.getManageEmailCustomPreviewCloseIcon(20) == null) {
								appLog.info("Custom Email Template PopUp is closed with custom text.");
							} else {
								appLog.info("Custom Email Template PopUp is not closed with custom text.");
								sa.assertTrue(false, "Custom Email Template PopUp is not closed with custom text.");
							}
						} else {
							appLog.info("Not able to click on close icon");
							sa.assertTrue(false, "Not able to click on close icon");
						}
					} else {
						appLog.info("Not able to click on preview link");
						sa.assertTrue(false, "Not able to click on preview link");
					}
					if (click(driver, fp.getCustomEmailEditLinkInManageEmails(30), "Custom Email Template Edit Link",
							action.SCROLLANDBOOLEAN)) {
						ThreadSleep(2000);
						if (sendKeys(driver, fp.getManageEmailCustomSubjectTextBox(30),
								"This is Max Subject text to test Data. This is Max Subject text to test Data. This is Max Subject text to test Data. This is Max Subject text to test Data. This is Max Subject text to test Data. This is Max Subject text to test Data. This is Max Subject text to test Data.",
								"Custom Email Subject Text Box", action.SCROLLANDBOOLEAN)) {
							ThreadSleep(2000);
							switchToFrame(driver, 30, fp.getManageEmailCustomSubjectBodyFrame(30));
							if (sendKeys(driver, fp.getManageEmailCustomSubjectBody(20),
									"This is Test Mail Data for Email", "Custom Email Body Text Box",
									action.SCROLLANDBOOLEAN)) {
								switchToDefaultContent(driver);
								switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
								if (click(driver, fp.getManageEmailCustomApplyButton(20), "Apply Button",
										action.SCROLLANDBOOLEAN)) {
									appLog.info("click on apply button");
									if (fp.getManageEmailCustomTemplateSubjectErrorMessage(60).getText().trim()
											.contains(FundsPageErrorMessage.manageEmailCustomEditSubjectErrorMessage)) {
										appLog.info("Error Message is verified");
									} else {
										appLog.info("Error Message is not verified");
										sa.assertTrue(false, "Error Message is not verified");
									}
								} else {
									appLog.info("Not able to click on apply button");
									sa.assertTrue(false, "Not able to clcik on apply button");
								}
							} else {
								appLog.info("Not able to enter value in body text box");
								sa.assertTrue(false, "Not able to enter value in body text box");
							}
						} else {
							appLog.info("Not able to enter value in body text box");
							sa.assertTrue(false, "Not able to enter value in body text box");
						}
					} else {
						appLog.info("Not able to click on edit link");
						sa.assertTrue(false, "Not able to click on edit link");
					}
				} else {
					appLog.info("Not able to click on manage emails");
					sa.assertTrue(false, "Not able to click on manage emails");
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
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" })
	@Test
	public void M10tc007_InviteContactAndVerifyManageEmailsPopUp() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		String[] stdPath = ExcelUtils
				.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath)
				.split(",");
		String[] sharedPath = ExcelUtils
				.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath)
				.split(",");
		String[] commonPath = ExcelUtils
				.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath)
				.split(",");
		String[] internalPath = ExcelUtils
				.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath)
				.split(",");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M10FundName1)) {
				if (fp.inviteContact(environment, mode,M10Institution1, M10Contact1EmailId, stdPath[0],
						FolderType.Standard, "Upload", "Yes", "No", null, Workspace.FundraisingWorkspace, null)) {
					appLog.info("Successfully provided access to contact '" + M10Contact1FirstName + " "
							+ M10Contact1LastName + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M10Contact1FirstName + " "
							+ M10Contact1LastName + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M10Contact1FirstName + " "
							+ M10Contact1LastName + "'.");
				}
				if (fp.inviteContact(environment, mode, M10Institution1, M10Contact1EmailId, null, FolderType.Standard,
						"Upload", "Yes", "No", null, Workspace.FundraisingWorkspace, null)) {
					appLog.info("Successfully provided access to contact '" + M10Contact1FirstName + " "
							+ M10Contact1LastName + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M10Contact1FirstName + " "
							+ M10Contact1LastName + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M10Contact1FirstName + " "
							+ M10Contact1LastName + "'.");
				}
				if (fp.inviteContact(environment, mode, M10Institution1, M10Contact1EmailId, sharedPath[1],
						FolderType.Shared, "Download", "Yes", "No", null, Workspace.FundraisingWorkspace, null)) {
					appLog.info("Successfully provided access to contact '" + M10Contact1FirstName + " "
							+ M10Contact1LastName + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M10Contact1FirstName + " "
							+ M10Contact1LastName + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M10Contact1FirstName + " "
							+ M10Contact1LastName + "'.");
				}
				if (fp.inviteContact(environment, mode, M10Institution2, M10Contact2EmailId, stdPath[0],
						FolderType.Standard, "Upload", "Yes", "No", null, Workspace.FundraisingWorkspace, null)) {
					appLog.info("Successfully provided access to contact '" + M10Contact1FirstName + " "
							+ M10Contact1LastName + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M10Contact1FirstName + " "
							+ M10Contact1LastName + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M10Contact1FirstName + " "
							+ M10Contact1LastName + "'.");
				}
				driver.navigate().refresh();
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
						"Fundraising Section view");
				if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage email icon",
						action.SCROLLANDBOOLEAN)) {
					String contactAccessDropdown = getSelectedOptionOfDropDown(driver,
							fp.getManageEmailContactAccessViewDropDownList(60), "Contact access view dropodown list",
							"text");
					if (contactAccessDropdown.contains("All Folders")) {
						appLog.info("All folders is selected in the contact access view dropdown");
					} else {
						appLog.info("All folders is not selected in the Contact access view dropodwn ");
						sa.assertTrue(false, "All folders is not selected in the Contact access view dropodwn ");
					}
					if (fp.verifyManageEmailGrid(M10Contact1FirstName + " " + M10Contact1LastName, M10Contact1EmailId,
							M10Institution1, null)) {
						appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
								+ M10Contact1LastName);
					} else {
						appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
								+ M10Contact1LastName);
						sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
								+ M10Contact1LastName);
					}
					if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName, M10Contact2EmailId,
							M10Institution2, null)) {
						appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
								+ M10Contact2LastName);
					} else {
						appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
								+ M10Contact2LastName);
						sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
								+ M10Contact2LastName);
					}
					if (click(driver, fp.getManageEmailCancelBtn(60), "Manage Email Cancel Button",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on cancel button");
					} else {
						appLog.info("Clicked on cancel button");
						sa.assertTrue(false, "clicked on cancel button");
					}
				} else {
					appLog.info("Not able to clcik on manage email");
					sa.assertTrue(false, "Not able to click on manage email");
				}
				if (fp.verifyFolderPathdummy(commonPath[0], null, null, M10FundName1, PageName.FundsPage,
						Workspace.FundraisingWorkspace, 60)) {
					if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage email icon",
							action.SCROLLANDBOOLEAN)) {
						String contactAccessDropdown = getSelectedOptionOfDropDown(driver,
								fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropodown list", "text");
						if (contactAccessDropdown.contains("All Folders")) {
							appLog.info("All folders is selected in the contact access view dropdown");
						} else {
							appLog.info("All folders is not selected in the Contact access view dropodwn ");
							sa.assertTrue(false, "All folders is not selected in the Contact access view dropodwn ");
						}
						if (fp.verifyManageEmailGrid(M10Contact1FirstName + " " + M10Contact1LastName,
								M10Contact1EmailId, M10Institution1, null)) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName
									+ " " + M10Contact1LastName);
						}
						if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,
								M10Contact2EmailId, M10Institution2, null)) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact2FirstName
									+ " " + M10Contact2LastName);
						}
						if (click(driver, fp.getManageEmailCancelBtn(60), "Manage Email Cancel Button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on cancel button");
						} else {
							appLog.info("Clicked on cancel button");
							sa.assertTrue(false, "clicked on cancel button");
						}
					} else {
						appLog.info("Not able to clcik on manage email");
						sa.assertTrue(false, "Not able to click on manage email");
					}
				} else {
					appLog.info("Not able to find folder path");
					sa.assertTrue(false, "Not able to find folder path");
				}
				if (fp.verifyFolderPathdummy(commonPath[1], null, null, M10FundName1, PageName.FundsPage,
						Workspace.FundraisingWorkspace, 60)) {
					if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage email icon",
							action.SCROLLANDBOOLEAN)) {
						String contactAccessDropdown = getSelectedOptionOfDropDown(driver,
								fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropodown list", "text");
						if (contactAccessDropdown.contains("All Folders")) {
							appLog.info("All folders is selected in the contact access view dropdown");
						} else {
							appLog.info("All folders is not selected in the Contact access view dropodwn ");
							sa.assertTrue(false, "All folders is not selected in the Contact access view dropodwn ");
						}
						if (fp.verifyManageEmailGrid(M10Contact1FirstName + " " + M10Contact1LastName,
								M10Contact1EmailId, M10Institution1, null)) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName
									+ " " + M10Contact1LastName);
						}
						if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,
								M10Contact2EmailId, M10Institution2, null)) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact2FirstName
									+ " " + M10Contact2LastName);
						}
						if (click(driver, fp.getManageEmailCancelBtn(60), "Manage Email Cancel Button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on cancel button");
						} else {
							appLog.info("Clicked on cancel button");
							sa.assertTrue(false, "clicked on cancel button");
						}

					} else {
						appLog.info("Not able to clcik on manage email");
						sa.assertTrue(false, "Not able to click on manage email");
					}
				} else {
					appLog.info("Not able to find folder path");
					sa.assertTrue(false, "Not able to find folder path");
				}
				if (fp.verifyFolderPathdummy(internalPath[0], null, null, M10FundName1, PageName.FundsPage,
						Workspace.FundraisingWorkspace, 60)) {
					if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage email icon",
							action.SCROLLANDBOOLEAN)) {
						String contactAccessDropdown = getSelectedOptionOfDropDown(driver,
								fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropodown list", "text");
						if (contactAccessDropdown.contains("All Folders")) {
							appLog.info("All folders is selected in the contact access view dropdown");
						} else {
							appLog.info("All folders is not selected in the Contact access view dropodwn ");
							sa.assertTrue(false, "All folders is not selected in the Contact access view dropodwn ");
						}
						if (fp.verifyManageEmailGrid(M10Contact1FirstName + " " + M10Contact1LastName,
								M10Contact1EmailId, M10Institution1, null)) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName
									+ " " + M10Contact1LastName);
						}
						if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,
								M10Contact2EmailId, M10Institution2, null)) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact2FirstName
									+ " " + M10Contact2LastName);
						}
						if (click(driver, fp.getManageEmailCancelBtn(60), "Manage Email Cancel Button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on cancel button");
						} else {
							appLog.info("Clicked on cancel button");
							sa.assertTrue(false, "clicked on cancel button");
						}
					} else {
						appLog.info("Not able to clcik on manage email");
						sa.assertTrue(false, "Not able to click on manage email");
					}
				} else {
					appLog.info("Not able to find folder path");
					sa.assertTrue(false, "Not able to find folder path");
				}
				if (fp.verifyFolderPathdummy(internalPath[1], null, null, M10FundName1, PageName.FundsPage,
						Workspace.FundraisingWorkspace, 60)) {
					if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage email icon",
							action.SCROLLANDBOOLEAN)) {
						String contactAccessDropdown = getSelectedOptionOfDropDown(driver,
								fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropodown list", "text");
						if (contactAccessDropdown.contains("All Folders")) {
							appLog.info("All folders is selected in the contact access view dropdown");
						} else {
							appLog.info("All folders is not selected in the Contact access view dropodwn ");
							sa.assertTrue(false, "All folders is not selected in the Contact access view dropodwn ");
						}
						if (fp.verifyManageEmailGrid(M10Contact1FirstName + " " + M10Contact1LastName,
								M10Contact1EmailId, M10Institution1, null)) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName
									+ " " + M10Contact1LastName);
						}
						if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,
								M10Contact2EmailId, M10Institution2, null)) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact2FirstName
									+ " " + M10Contact2LastName);
						}
						if (click(driver, fp.getManageEmailCancelBtn(60), "Manage Email Cancel Button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on cancel button");
						} else {
							appLog.info("Clicked on cancel button");
							sa.assertTrue(false, "clicked on cancel button");
						}
					} else {
						appLog.info("Not able to clcik on manage email");
						sa.assertTrue(false, "Not able to click on manage email");
					}
				} else {
					appLog.info("Not able to find folder path");
					sa.assertTrue(false, "Not able to find folder path");
				}
				if (fp.verifyFolderPathdummy(sharedPath[0], null, null, M10FundName1, PageName.FundsPage,
						Workspace.FundraisingWorkspace, 60)) {
					if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage email icon",
							action.SCROLLANDBOOLEAN)) {
						String contactAccessDropdown = getSelectedOptionOfDropDown(driver,
								fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropodown list", "text");
						if (contactAccessDropdown.contains(sharedPath[0])) {
							appLog.info(sharedPath[0] + "  is selected in the contact access view dropdown");
						} else {
							appLog.info(sharedPath[0] + " is not selected in the Contact access view dropodwn ");
							sa.assertTrue(false,
									sharedPath[0] + "  is not selected in the Contact access view dropodwn ");
						}
						List<WebElement> contactAccessViewDropdownValues = allOptionsInDropDrop(driver,
								fp.getManageEmailContactAccessViewDropDownList(60), "Show dropdown values");
						for (int i = 0; i < contactAccessViewDropdownValues.size(); i++) {
							if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase(sharedPath[0])) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else
								if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase("All Folders")) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
								sa.assertTrue(false, contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
							}
						}
						sa.assertTrue(
								fp.getContactAccessManageEmailGridMsg(30).getText().trim()
										.contains(FundsPageErrorMessage.noDataToDisplayErrorMessage),
								"Manage Emails Contacts Grid Error Message is not verified. Expected: "
										+ FundsPageErrorMessage.noDataToDisplayErrorMessage);
						if (selectVisibleTextFromDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropdown list", "All Folders")) {
							contactAccessDropdown = getSelectedOptionOfDropDown(driver,
									fp.getManageEmailContactAccessViewDropDownList(60),
									"Contact access view dropodown list", "text");
							if (contactAccessDropdown.contains("All Folders")) {
								appLog.info("All folders is selected in the contact access view dropdown");
							} else {
								appLog.info("All folders is not selected in the Contact access view dropodwn ");
								sa.assertTrue(false,
										"All folders is not selected in the Contact access view dropodwn ");
							}
							if (fp.verifyManageEmailGrid(M10Contact1FirstName + " " + M10Contact1LastName,
									M10Contact1EmailId, M10Institution1, null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: "
										+ M10Contact1FirstName + " " + M10Contact1LastName);
							}
							if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,
									M10Contact2EmailId, M10Institution2, null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: "
										+ M10Contact2FirstName + " " + M10Contact2LastName);
							}
						} else {
							appLog.info("Not able to select value from the dropdown");
							sa.assertTrue(false, "Not able to select value from the dropodown");
						}
						if (click(driver, fp.getManageEmailCancelBtn(60), "Manage Email Cancel Button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on cancel button");
						} else {
							appLog.info("Clicked on cancel button");
							sa.assertTrue(false, "clicked on cancel button");
						}
					} else {
						appLog.info("Not able to clcik on manage email");
						sa.assertTrue(false, "Not able to click on manage email");
					}
				} else {
					appLog.info("Not able to find folder path");
					sa.assertTrue(false, "Not able to find folder path");
				}
				if (fp.verifyFolderPathdummy(sharedPath[1], null, null, M10FundName1, PageName.FundsPage,
						Workspace.FundraisingWorkspace, 60)) {
					if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage email icon",
							action.SCROLLANDBOOLEAN)) {
						String contactAccessDropdown = getSelectedOptionOfDropDown(driver,
								fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropodown list", "text");
						if (contactAccessDropdown.contains((sharedPath[1].split("/"))[1])) {
							appLog.info((sharedPath[1].split("/"))[1]
									+ "  is selected in the contact access view dropdown");
						} else {
							appLog.info((sharedPath[1].split("/"))[1]
									+ " is not selected in the Contact access view dropodwn ");
							sa.assertTrue(false, (sharedPath[1].split("/"))[1]
									+ "  is not selected in the Contact access view dropodwn ");
						}
						List<WebElement> contactAccessViewDropdownValues = allOptionsInDropDrop(driver,
								fp.getManageEmailContactAccessViewDropDownList(60), "Show dropdown values");
						for (int i = 0; i < contactAccessViewDropdownValues.size(); i++) {
							if (contactAccessViewDropdownValues.get(i).getText()
									.equalsIgnoreCase((sharedPath[1].split("/"))[1])) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else
								if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase("All Folders")) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
								sa.assertTrue(false, contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
							}
						}
						if (fp.verifyManageEmailGrid(M10Contact1FirstName + " " + M10Contact1LastName,
								M10Contact1EmailId, M10Institution1, null)) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName
									+ " " + M10Contact1LastName);
						}
						if (selectVisibleTextFromDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropdown list", "All Folders")) {
							contactAccessDropdown = getSelectedOptionOfDropDown(driver,
									fp.getManageEmailContactAccessViewDropDownList(60),
									"Contact access view dropodown list", "text");
							if (contactAccessDropdown.contains("All Folders")) {
								appLog.info("All folders is selected in the contact access view dropdown");
							} else {
								appLog.info("All folders is not selected in the Contact access view dropodwn ");
								sa.assertTrue(false,
										"All folders is not selected in the Contact access view dropodwn ");
							}
							if (fp.verifyManageEmailGrid(M10Contact1FirstName + " " + M10Contact1LastName,
									M10Contact1EmailId, M10Institution1, null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: "
										+ M10Contact1FirstName + " " + M10Contact1LastName);
							}
							if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,
									M10Contact2EmailId, M10Institution2, null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: "
										+ M10Contact2FirstName + " " + M10Contact2LastName);
							}
						} else {
							appLog.info("Not able to select value from the dropdown");
							sa.assertTrue(false, "Not able to select value from the dropodown");
						}
						if (click(driver, fp.getManageEmailCancelBtn(60), "Manage Email Cancel Button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on cancel button");
						} else {
							appLog.info("Clicked on cancel button");
							sa.assertTrue(false, "clicked on cancel button");
						}
					} else {
						appLog.info("Not able to clcik on manage email");
						sa.assertTrue(false, "Not able to click on manage email");
					}
				} else {
					appLog.info("Not able to find folder path");
					sa.assertTrue(false, "Not able to find folder path");
				}

				if (fp.verifyFolderPathdummy("", M10Institution1, null, M10FundName1, PageName.FundsPage,
						Workspace.FundraisingWorkspace, 60)) {
					if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage email icon",
							action.SCROLLANDBOOLEAN)) {
						String contactAccessDropdown = getSelectedOptionOfDropDown(driver,
								fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropodown list", "text");
						if (contactAccessDropdown.contains(M10Institution1)) {
							appLog.info(M10Institution1 + "  is selected in the contact access view dropdown");
						} else {
							appLog.info(M10Institution1 + " is not selected in the Contact access view dropodwn ");
							sa.assertTrue(false,
									M10Institution1 + "  is not selected in the Contact access view dropodwn ");
						}
						List<WebElement> contactAccessViewDropdownValues = allOptionsInDropDrop(driver,
								fp.getManageEmailContactAccessViewDropDownList(60), "Show dropdown values");
						for (int i = 0; i < contactAccessViewDropdownValues.size(); i++) {
							if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase(M10Institution1)) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else
								if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase("All Folders")) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
								sa.assertTrue(false, contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
							}
						}
						if (fp.verifyManageEmailGrid(M10Contact1FirstName + " " + M10Contact1LastName,
								M10Contact1EmailId, M10Institution1, null)) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName
									+ " " + M10Contact1LastName);
						}
						if (selectVisibleTextFromDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropdown list", "All Folders")) {
							contactAccessDropdown = getSelectedOptionOfDropDown(driver,
									fp.getManageEmailContactAccessViewDropDownList(60),
									"Contact access view dropodown list", "text");
							if (contactAccessDropdown.contains("All Folders")) {
								appLog.info("All folders is selected in the contact access view dropdown");
							} else {
								appLog.info("All folders is not selected in the Contact access view dropodwn ");
								sa.assertTrue(false,
										"All folders is not selected in the Contact access view dropodwn ");
							}
							if (fp.verifyManageEmailGrid(M10Contact1FirstName + " " + M10Contact1LastName,
									M10Contact1EmailId, M10Institution1, null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: "
										+ M10Contact1FirstName + " " + M10Contact1LastName);
							}
							if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,
									M10Contact2EmailId, M10Institution2, null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: "
										+ M10Contact2FirstName + " " + M10Contact2LastName);
							}
						} else {
							appLog.info("Not able to select value from the dropdown");
							sa.assertTrue(false, "Not able to select value from the dropodown");
						}
						if (click(driver, fp.getManageEmailCancelBtn(60), "Manage Email Cancel Button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on cancel button");
						} else {
							appLog.info("Clicked on cancel button");
							sa.assertTrue(false, "clicked on cancel button");
						}
					} else {
						appLog.info("Not able to clcik on manage email");
						sa.assertTrue(false, "Not able to click on manage email");
					}
				} else {
					appLog.info("Not able to find folder path");
					sa.assertTrue(false, "Not able to find folder path");
				}
				if (fp.verifyFolderPathdummy(stdPath[0], M10Institution1, null, M10FundName1, PageName.FundsPage,
						Workspace.FundraisingWorkspace, 60)) {
					if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage email icon",
							action.SCROLLANDBOOLEAN)) {
						String contactAccessDropdown = getSelectedOptionOfDropDown(driver,
								fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropodown list", "text");
						if (contactAccessDropdown.contains(stdPath[0])) {
							appLog.info(stdPath[0] + "  is selected in the contact access view dropdown");
						} else {
							appLog.info(stdPath[0] + " is not selected in the Contact access view dropodwn ");
							sa.assertTrue(false, stdPath[0] + "  is not selected in the Contact access view dropodwn ");
						}
						List<WebElement> contactAccessViewDropdownValues = allOptionsInDropDrop(driver,
								fp.getManageEmailContactAccessViewDropDownList(60), "Show dropdown values");
						for (int i = 0; i < contactAccessViewDropdownValues.size(); i++) {
							if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase(stdPath[0])) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else
								if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase("All Folders")) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
								sa.assertTrue(false, contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
							}
						}
						if (fp.verifyManageEmailGrid(M10Contact1FirstName + " " + M10Contact1LastName,
								M10Contact1EmailId, M10Institution1, null)) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName
									+ " " + M10Contact1LastName);
						}
						if (selectVisibleTextFromDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropdown list", "All Folders")) {
							contactAccessDropdown = getSelectedOptionOfDropDown(driver,
									fp.getManageEmailContactAccessViewDropDownList(60),
									"Contact access view dropodown list", "text");
							if (contactAccessDropdown.contains("All Folders")) {
								appLog.info("All folders is selected in the contact access view dropdown");
							} else {
								appLog.info("All folders is not selected in the Contact access view dropodwn ");
								sa.assertTrue(false,
										"All folders is not selected in the Contact access view dropodwn ");
							}
							if (fp.verifyManageEmailGrid(M10Contact1FirstName + " " + M10Contact1LastName,
									M10Contact1EmailId, M10Institution1, null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: "
										+ M10Contact1FirstName + " " + M10Contact1LastName);
							}
							if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,
									M10Contact2EmailId, M10Institution2, null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: "
										+ M10Contact2FirstName + " " + M10Contact2LastName);
							}
						} else {
							appLog.info("Not able to select value from the dropdown");
							sa.assertTrue(false, "Not able to select value from the dropodown");
						}
						if (click(driver, fp.getManageEmailCancelBtn(60), "Manage Email Cancel Button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on cancel button");
						} else {
							appLog.info("Clicked on cancel button");
							sa.assertTrue(false, "clicked on cancel button");
						}
					} else {
						appLog.info("Not able to clcik on manage email");
						sa.assertTrue(false, "Not able to click on manage email");
					}
				} else {
					appLog.info("Not able to find folder path");
					sa.assertTrue(false, "Not able to find folder path");
				}
				if (fp.verifyFolderPathdummy("", M10Institution2, null, M10FundName1, PageName.FundsPage,
						Workspace.FundraisingWorkspace, 60)) {
					if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage email icon",
							action.SCROLLANDBOOLEAN)) {
						String contactAccessDropdown = getSelectedOptionOfDropDown(driver,
								fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropodown list", "text");
						if (contactAccessDropdown.contains(M10Institution2)) {
							appLog.info(M10Institution2 + "  is selected in the contact access view dropdown");
						} else {
							appLog.info(M10Institution2 + " is not selected in the Contact access view dropodwn ");
							sa.assertTrue(false,
									M10Institution2 + "  is not selected in the Contact access view dropodwn ");
						}
						List<WebElement> contactAccessViewDropdownValues = allOptionsInDropDrop(driver,
								fp.getManageEmailContactAccessViewDropDownList(60), "Show dropdown values");
						for (int i = 0; i < contactAccessViewDropdownValues.size(); i++) {
							if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase(M10Institution2)) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else
								if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase("All Folders")) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
								sa.assertTrue(false, contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
							}
						}
						sa.assertTrue(
								fp.getContactAccessManageEmailGridMsg(30).getText().trim()
										.contains(FundsPageErrorMessage.noDataToDisplayErrorMessage),
								"Manage Emails Contacts Grid Error Message is not verified. Expected: "
										+ FundsPageErrorMessage.noDataToDisplayErrorMessage);
						if (selectVisibleTextFromDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropdown list", "All Folders")) {
							contactAccessDropdown = getSelectedOptionOfDropDown(driver,
									fp.getManageEmailContactAccessViewDropDownList(60),
									"Contact access view dropodown list", "text");
							if (contactAccessDropdown.contains("All Folders")) {
								appLog.info("All folders is selected in the contact access view dropdown");
							} else {
								appLog.info("All folders is not selected in the Contact access view dropodwn ");
								sa.assertTrue(false,
										"All folders is not selected in the Contact access view dropodwn ");
							}
							if (fp.verifyManageEmailGrid(M10Contact1FirstName + " " + M10Contact1LastName,
									M10Contact1EmailId, M10Institution1, null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: "
										+ M10Contact1FirstName + " " + M10Contact1LastName);
							}
							if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,
									M10Contact2EmailId, M10Institution2, null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: "
										+ M10Contact2FirstName + " " + M10Contact2LastName);
							}
						} else {
							appLog.info("Not able to select value from the dropdown");
							sa.assertTrue(false, "Not able to select value from the dropodown");
						}
						if (click(driver, fp.getManageEmailCancelBtn(60), "Manage Email Cancel Button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on cancel button");
						} else {
							appLog.info("Clicked on cancel button");
							sa.assertTrue(false, "clicked on cancel button");
						}
					} else {
						appLog.info("Not able to clcik on manage email");
						sa.assertTrue(false, "Not able to click on manage email");
					}
				} else {
					appLog.info("Not able to find folder path");
					sa.assertTrue(false, "Not able to find folder path");
				}
				if (fp.verifyFolderPathdummy(stdPath[0], M10Institution2, null, M10FundName1, PageName.FundsPage,
						Workspace.FundraisingWorkspace, 60)) {
					if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage email icon",
							action.SCROLLANDBOOLEAN)) {
						String contactAccessDropdown = getSelectedOptionOfDropDown(driver,
								fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropodown list", "text");
						if (contactAccessDropdown.contains(stdPath[0])) {
							appLog.info(stdPath[0] + "  is selected in the contact access view dropdown");
						} else {
							appLog.info(stdPath[0] + " is not selected in the Contact access view dropodwn ");
							sa.assertTrue(false, stdPath[0] + "  is not selected in the Contact access view dropodwn ");
						}
						List<WebElement> contactAccessViewDropdownValues = allOptionsInDropDrop(driver,
								fp.getManageEmailContactAccessViewDropDownList(60), "Show dropdown values");
						for (int i = 0; i < contactAccessViewDropdownValues.size(); i++) {
							if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase(stdPath[0])) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else
								if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase("All Folders")) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
								sa.assertTrue(false, contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
							}
						}
						if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,
								M10Contact2EmailId, M10Institution2, null)) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact2FirstName
									+ " " + M10Contact2LastName);
						}
						if (selectVisibleTextFromDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropdown list", "All Folders")) {
							contactAccessDropdown = getSelectedOptionOfDropDown(driver,
									fp.getManageEmailContactAccessViewDropDownList(60),
									"Contact access view dropodown list", "text");
							if (contactAccessDropdown.contains("All Folders")) {
								appLog.info("All folders is selected in the contact access view dropdown");
							} else {
								appLog.info("All folders is not selected in the Contact access view dropodwn ");
								sa.assertTrue(false,
										"All folders is not selected in the Contact access view dropodwn ");
							}
							if (fp.verifyManageEmailGrid(M10Contact1FirstName + " " + M10Contact1LastName,
									M10Contact1EmailId, M10Institution1, null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: "
										+ M10Contact1FirstName + " " + M10Contact1LastName);
							}
							if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,
									M10Contact2EmailId, M10Institution2, null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: "
										+ M10Contact2FirstName + " " + M10Contact2LastName);
							}
						} else {
							appLog.info("Not able to select value from the dropdown");
							sa.assertTrue(false, "Not able to select value from the dropodown");
						}
						if (click(driver, fp.getManageEmailCancelBtn(60), "Manage Email Cancel Button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on cancel button");
						} else {
							appLog.info("Clicked on cancel button");
							sa.assertTrue(false, "clicked on cancel button");
						}
					} else {
						appLog.info("Not able to clcik on manage email");
						sa.assertTrue(false, "Not able to click on manage email");
					}
				} else {
					appLog.info("Not able to find folder path");
					sa.assertTrue(false, "Not able to find folder path");
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
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}

	@Test
	public void M10tc008_VerifyConfirmationPopUpReceivedMailAtInvestorSideVerifyCreateActivityAtContactAndAccountPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		WebElement ele = null;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M10FundName1)) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
						"Fundraising Section view");
				if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Fundarising workspace",
						action.SCROLLANDBOOLEAN)) {
					ele = FindElement(driver, "//a[text()='" + M10Contact1EmailId + "']", "Contact 1 Email id",
							action.SCROLLANDBOOLEAN, 60);
					if (click(driver, ele, "Contact1 ", action.SCROLLANDBOOLEAN)) {
						if (!new NIMPageBusinessLayer(driver)
								.verifyNavatarSalesTeamLinkFunctionality("ContactEmailLink")) {
							appLog.info("Verification of Contact Email link is unsuccessfull.");
							sa.assertTrue(false, "Verification of Contact Email link is unsuccessfull.");
						} else {
							appLog.info("Verification of Contact Email Link is successfully");
						}
					} else {
						appLog.info("Not able to click on contact 1 checkbox");
						sa.assertTrue(false, "Not able to click on contat1 checkbox");
					}
				ThreadSleep(5000);
					if (click(driver, fp.getManageEMailContactAllCheckBox(30), "Manage Email All Contact Check Box",
							action.SCROLLANDBOOLEAN)) {
						List<WebElement> listofcontact = fp.getManageEmailListOfContactCheckBox();
						for (int i = 0; i < listofcontact.size(); i++) {
							if (isSelected(driver, listofcontact.get(i), "Contact Check Box")) {
								appLog.info("Contact Check Box is Selected.");
							} else {
								appLog.info("Contact Check Box is not selected");
								sa.assertTrue(false, "Contact Check Box is not selected");
							}
						}
					} else {
						appLog.info("Not able to clcik on select all checkbox");
						sa.assertTrue(false, "Not able to click on select all checkbox");
					}
					ThreadSleep(5000);
					WebElement contactcheckbox = FindElement(driver,"//a[text()='"+M10Contact2FirstName+" "+M10Contact2LastName+"']/../..//input","Contact Check Box", action.SCROLLANDBOOLEAN, 30);
					if (contactcheckbox != null) {
						if (click(driver, contactcheckbox, "Manage Emails Contact Check Box",
								action.SCROLLANDBOOLEAN)) {
							if (isSelected(driver, fp.getManageEMailContactAllCheckBox(30),
									"Manage Emails All Contact Check Box")) {
								appLog.info("Manage Email All Contact Check Box is selected after uncheck contact.");
								sa.assertTrue(false,
										"Manage Email All Contact Check Box is selected after uncheck contact.");
							} else {
								appLog.info(
										"Manage Email All Contact Check Box is not selected after uncheck contact.");
							}
						} else {
							appLog.info("Not able to click on contact checkbox");
							sa.assertTrue(false, "Not able to clciik on manage email text box");
						}
					} else {
						appLog.info("Contact Check Box is not available on Manage Email Contact");
						sa.assertTrue(false, "Contact Check Box is not available on Manage Email Contact");
					}
					if (click(driver, fp.getManageEMailContactAllCheckBox(30), "Manage Email All Contact Check Box",
							action.SCROLLANDBOOLEAN)) {
						ThreadSleep(2000);
						if (click(driver, fp.getManageEMailContactAllCheckBox(30), "Manage Email All Contact Check Box",
								action.SCROLLANDBOOLEAN)) {
							ThreadSleep(2000);
							if ( fp.clickUsingCssSelectorPath("a[title=Send]", "send button")) {
								
							//if (click(driver, fp.getmanageEmailsendBtn(30), "Manage Email Send Button",
							//		action.SCROLLANDBOOLEAN)) {
								String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
								if (msg.equalsIgnoreCase(
										FundsPageErrorMessage.pleaseSelectOneInvestorErrorMessageInManageEmails)) {
									appLog.info("Please select one invetsor erro message is verified");
								} else {
									appLog.info("Please select one invetsor erro message is not verified");
									sa.assertTrue(false, "Please select one invetsor erro message is not verified");
								}
								switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
							} else {
								appLog.info("Not able to click on send button");
								sa.assertTrue(false, "Not able to click on send button");
							}
						} else {
							appLog.info("Not able ot click on select all checkbox");
							sa.assertTrue(false, "Not able to click on select all checkbox");
						}
					} else {
						appLog.info("Not able ot click on select all checkbox");
						sa.assertTrue(false, "Not able to click on select all checkbox");
					}
					ele = FindElement(driver,
							"//a[text()='" + M10Contact1FirstName + " " + M10Contact1LastName + "']/../..//input",
							"Contact Check Box", action.SCROLLANDBOOLEAN, 30);
					if (click(driver, ele, "Contact1 ", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getmanageEmailsendBtn(30), "Manage Email Send Button",
								action.SCROLLANDBOOLEAN)) {
							if (fp.getManageEmailSendInvitationConfirmationCountErrorMsg(30).getText().trim()
									.contains("1")) {
								appLog.info("Manage Emails Contact Send Invitation Confirmation Count is  verified");
							} else {
								appLog.info("Manage Emails Contact Send Invitation Confirmation Count is not verified");
								sa.assertTrue(false,
										"Manage Emails Contact Send Invitation Confirmation Count is not verified");
							}
							if (fp.getManageEmailSendInvitationConfirmationErrorMsg(30).getText().trim()
									.contains(FundsPageErrorMessage.ManageEmailSendContactInvitationErrorMsg1)) {
								appLog.info(
										"Manage Emails Contact Send Invitation Confirmation Error Message is verified.");
							} else {
								appLog.info(
										"Manage Emails Contact Send Invitation Confirmation Error Message is not verified.");
								sa.assertTrue(false,
										"Manage Emails Contact Send Invitation Confirmation Error Message is not verified.");
							}
							if (fp.getManageEmailSendInvitationConfirmationErrorMsg(30).getText().trim()
									.contains(FundsPageErrorMessage.ManageEmailSendContactInvitationErrorMsg1)) {
								appLog.info(
										"Manage Emails Contact Send Invitation Confirmation Error Message is verified.");
							} else {
								appLog.info(
										"Manage Emails Contact Send Invitation Confirmation Error Message is not verified.");
								sa.assertTrue(false,
										"Manage Emails Contact Send Invitation Confirmation Error Message is not verified.");
							}
							if (isDisplayed(driver, fp.getManageEmailSendInvitationConfirmationYesBtn(60), "Visibility",
									30, "Yes Button") != null) {
								appLog.info("Yes Button is displaying");
							} else {
								appLog.info("Yes Button is not displaying");
								sa.assertTrue(false, "Yes Button is not displaying");
							}
							if (isDisplayed(driver, fp.getManageEmailSendInvitationConfirmationCloseIcon(60),
									"Visibility", 30, "Close Icon") != null) {
								appLog.info("Close icon is displaying");
							} else {
								appLog.info("Close icon is not displaying");
								sa.assertTrue(false, "Close icon is not displaying");
							}
							if (isDisplayed(driver, fp.getManageEmailSendInvitationConfirmationNoBtn(60), "Visibility",
									30, "No Button") != null) {
								appLog.info("No Button is displaying");
							} else {
								appLog.info("No Button is not displaying");
								sa.assertTrue(false, "No Button is not displaying");
							}
							if (click(driver, fp.getManageEmailSendInvitationConfirmationCloseIcon(60), "Close icon",
									action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on close icon");
								if (fp.getManageEmailSendInvitationConfirmationCloseIcon(20) == null) {
									appLog.info("Confirmation Pop up is clsoed");
								} else {
									appLog.info("Confirmation Pop up is clsoed");
									sa.assertTrue(false, "Confirmation Pop up is not clsoed");
								}
							} else {
								appLog.info("Not able to click on close icon");
								sa.assertTrue(false, "Not able to clcik on close icon");
							}
						} else {
							appLog.info("Not able to click on send button");
							sa.assertTrue(false, "Not able to click on send button");
						}
							if (click(driver, fp.getmanageEmailsendBtn(30), "Manage Email Send Button",
									action.SCROLLANDBOOLEAN)) {
								if (click(driver, fp.getManageEmailSendInvitationConfirmationNoBtn(60), "No Button",
										action.SCROLLANDBOOLEAN)) {
									appLog.info("Clicked on No Button");
									if (fp.getManageEmailSendInvitationConfirmationNoBtn(20) == null) {
										appLog.info("Confirmation Pop up is clsoed");
									} else {
										appLog.info("Confirmation Pop up is clsoed");
										sa.assertTrue(false, "Confirmation Pop up is not clsoed");
									}
								} else {
									appLog.info("Not able to click on No button");
									sa.assertTrue(false, "Not able to clcik on No button");
								}
							} else {
								appLog.info("Not able to click on send button");
								sa.assertTrue(false, "Not able to click on send button");
							}
							if(click(driver, fp.getManageEmailEmailLabel(60), "Email label", action.SCROLLANDBOOLEAN)){
							if (click(driver, fp.getmanageEmailsendBtn(30), "Manage Email Send Button",
									action.SCROLLANDBOOLEAN)) {
								if (click(driver, fp.getManageEmailSendInvitationConfirmationYesBtn(60), "Yes Button",
										action.SCROLLANDBOOLEAN)) {
									appLog.info("Clicked on yes Button");
									if (fp.getManageEmailSendInvitationConfirmationYesBtn(20) == null) {
										appLog.info("Confirmation Pop up is clsoed");
									} else {
										appLog.info("Confirmation Pop up is clsoed");
										sa.assertTrue(false, "Confirmation Pop up is not clsoed");
									}
									if (fp.verifyManageEmailGrid(M10Contact1FirstName + " " + M10Contact1LastName,
											M10Contact1EmailId, M10Institution1, "Last Invite Date")) {
										appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName
												+ " " + M10Contact1LastName);
									} else {
										appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName
												+ " " + M10Contact1LastName);
										sa.assertTrue(false, "Grid Data is not verified for the contact: "
												+ M10Contact1FirstName + " " + M10Contact1LastName);
									}
									if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,
											M10Contact2EmailId, M10Institution2, null)) {
										appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName
												+ " " + M10Contact2LastName);
									} else {
										appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName
												+ " " + M10Contact2LastName);
										sa.assertTrue(false, "Grid Data is not verified for the contact: "
												+ M10Contact2FirstName + " " + M10Contact2LastName);
									}
									contactcheckbox = isDisplayed(driver,
											FindElement(driver,
													"//a[text()='" + M10Contact2FirstName + " " + M10Contact2LastName
															+ "']/../..//input",
													"Contact Check Box", action.SCROLLANDBOOLEAN, 30),
											"visibility", 30, "Manage Emails Contact Check Box");
									if (isSelected(driver, contactcheckbox, "Manage Emails Contact 2 Check Box")) {
										appLog.info("Manage Email Contact 2 Check Box is selected");
										sa.assertTrue(false, "Manage Email Contact 2 Check Box is selected");
									} else {
										appLog.info("Manage Email Contact 2 Check Box is not selected");
									}
									WebElement contact1CheckBox = isDisplayed(driver,
											FindElement(driver,
													"//a[text()='" + M10Contact1FirstName + " " + M10Contact1LastName
															+ "']/../..//input",
													"Contact Check Box", action.SCROLLANDBOOLEAN, 30),
											"visibility", 30, "Manage Emails Contact Check Box");
									if (isSelected(driver, contact1CheckBox, "Manage Emails Contact 1 Check Box")) {
										appLog.info("Manage Email Contact 1 Check Box is selected");
										sa.assertTrue(false, "Manage Email Contact 1 Check Box is selected");
									} else {
										appLog.info("Manage Email Contact 1 Check Box is not selected");
									}
								} else {
									appLog.info("Not able to click on Yes button");
									sa.assertTrue(false, "Not able to clcik on Yes button");
								}
							} else {
								appLog.info("Not able to click on send button");
								sa.assertTrue(false, "Not able to click on send button");
							}
							}else{
								appLog.error("Not able ot click on email label");
								sa.assertTrue(false, "Not able ot click on email label");
							}
					} else {
						appLog.info("Not able to click on contact1 checkbox");
						sa.assertTrue(false, "Not able ot click on contact1 checkbox");
					}
					appLog.info(">>>>");
					Scanner scn = new Scanner(System.in);
					scn.next();
					ele = FindElement(driver, "//a[text()='" + M10Contact1FirstName + " " + M10Contact1LastName + "']",
							"Contact 1", action.SCROLLANDBOOLEAN, 30);
					if (click(driver, ele, "Contact 1 name", action.SCROLLANDBOOLEAN)) {
						String parentID = switchOnWindow(driver);
						if (parentID != null) {
							if (cp.ClickonRelatedTab_Lighting(environment, RecordType.Contact)) {
								if (bp.clickOnViewAllRelatedList(environment, mode, RelatedList.Activity_History)) {

									scrollDownThroughWebelement(driver, cp.getContactActivityHistory(30), "Activity History");
									if (cp.getContactActivityAlertAccountNameText(30).getText().trim()
											.contains(M10Institution1)) {
										appLog.info("Activity Alert is available in Contact page.");
									} else {
										appLog.info("Activity Alert is not available in Contact page.");
										sa.assertTrue(false, "Activity Alert is not available in Contact page.");
									}
									if (click(driver, cp.getContactAcitivityAlertSubjectLink(30),
											"Contact Activity Alert Subject Link", action.SCROLLANDBOOLEAN)) {
										if (cp.getContactAcitivityAlertAssignedToEmailText(30).getText().trim()
												.contains(CRMUser1LastName)) {
											appLog.info("Assigned To user name is verified.");
										} else {
											appLog.info("Assigned To user name is not verified.");
											sa.assertTrue(false, "Assigned To user name is not verified.");
										}
										if (cp.getContactAcitivityAlertSubjectText(30).getText().trim()
												.contains("Invitation from")) {
											appLog.info("Subject Text is  verified.");
										} else {
											appLog.info("Subject Text is not verified.");
											sa.assertTrue(false, "Subject Text is not verified.");
										}
										if (cp.getContactAcitivityAlertSubjectText(30).getText().trim()
												.contains(Org1FirmName)) {
											appLog.info("Subject Text is  verified.");
										} else {
											appLog.info("Subject Text is not verified."+cp.getContactAcitivityAlertSubjectText(30).getText().trim()+" and "+Org1FirmName);
											sa.assertTrue(false, "Subject Text is not verified.");
										}
										String date = cp.getContactActivityAlertDueDateText(30).getText().trim();
										if (bp.verifyDate(date, "Due")) {
											appLog.info("Due Date is verified.");
										} else {
											appLog.error("Due Date is not verified. Actual Result: " + date);
											sa.assertTrue(false, "Due Date is not verified. Actual Result: " + date);
										}
										if (cp.getContactAcitivityAlertCommentsText(30).getText().trim().contains("Subject:")) {
											appLog.info("Comments Text is verified.");
										} else {
											appLog.error("Comments Text is not verified.");
											sa.assertTrue(false, "Comments Text is not verified.");
										}
										if (cp.getContactAcitivityAlertCommentsText(30).getText().trim()
												.contains("Invitation from " + Org1FirmName)) {
											appLog.info("Comments subject Text is verified.");
										} else {
											appLog.info("Comments subject Text is not verified.."+cp.getContactAcitivityAlertCommentsText(30).getText().trim()+" and "+Org1FirmName);
											sa.assertTrue(false, "Comments subject Text is not verified.");
										}
										if (cp.getContactAcitivityAlertCommentsText(30).getText().trim()
												.contains("Hello " + M10Contact1FirstName)) {
											appLog.info("Comments body Text is verified.");
										} else {
											appLog.info("Comments body Text is not verified..");
											sa.assertTrue(false, "Comments body Text is not verified.");
										}
										if (cp.getContactAcitivityAlertCommentsText(30).getText().trim()
												.contains("You have been granted access to Potential investments of "
														+ M10FundName1 + " by")) {
											appLog.info("Comments body Text is verified.");
										} else {
											appLog.info("Comments body Text is not verified..");
											sa.assertTrue(false, "Comments body Text is not verified.");
										}
										if (cp.getContactAcitivityAlertCommentsText(30).getText().trim()
												.contains("If you have not yet registered, Click here to register.")) {
											appLog.info("Comments body Text is verified.");
										} else {
											appLog.info("Comments body Text is not verified..");
											sa.assertTrue(false, "Comments body Text is not verified.");
										}
										if (cp.getContactAcitivityAlertCommentsText(30).getText().trim()
												.contains("If you have already registered, Click here to login.")) {
											appLog.info("Comments body Text is verified.");
										} else {
											appLog.info("Comments body Text is not verified..");
											sa.assertTrue(false, "Comments body Text is not verified.");
										}
										if (cp.getContactAcitivityAlertCommentsText(30).getText().trim().contains(
												"If you believe this has been sent in error, or if you cannot login, please contact")) {
											appLog.info("Comments body Text is verified.");
										} else {
											appLog.info("Comments body Text is not verified..");
											sa.assertTrue(false, "Comments body Text is not verified.");
										}
										if (cp.getContactActivityAlertStatusText(30).getText().trim().contains("Completed")) {
											appLog.info("Status text is verified.");
										} else {
											appLog.info("Status text is not verified.");
											sa.assertTrue(false, "Status text is not verified.");
										}
										if (cp.getContactActivityAlertPriorityText(30).getText().trim().contains("Normal")) {
											appLog.info("Priority text is verified.");
										} else {
											appLog.info("Priority text is not verified.");
											sa.assertTrue(false, "Priority text is not verified.");
										}
										if (cp.getAccountActivityAlertRelatedToText(60).getText().trim()
												.contains(M10Institution1)) {
											appLog.info("Institution Name is verified in Activity Alert on Institution Page.");
										} else {
											appLog.info(
													"Institution Name is not verified in Activity Alert on Institution Page.");
											sa.assertTrue(false,
													"Institution Name is not verified in Activity Alert on Institution Page.");
										}
										if (cp.getContactActivityAlertEmailIdtext(30).getText().trim()
												.contains(M10Contact1EmailId)) {
											appLog.info("Email ID is verified.");
										} else {
											appLog.info("Email ID is not verified.");
											sa.assertTrue(false, "Email ID is not verified.");
										}
										if (cp.getContactActivityAlertCreatedByText(30).getText().trim()
												.contains(CRMUser1LastName)) {
											appLog.info("Created By text is verified.");
										} else {
											appLog.info("Created By text is not verified.");
											sa.assertTrue(false, "Created By text is not verified.");
										}
										if (cp.getAccountActivityAlertNameText(30).getText().trim()
												.contains(M10Contact1LastName)) {
											appLog.info("Name text is verified.");
										} else {
											appLog.info("Name text is not verified.");
											sa.assertTrue(false, "Name text is not verified.");
										}
										String createdByDate = cp.getContactActivityAlertCreatedByDate(60).getText().trim();
										if (bp.verifyDate(createdByDate, "Created Date")) {
											appLog.info("Cretaed by date is verified");
										} else {
											appLog.info("Created by date is not verified");
											sa.assertTrue(false, "Created by date is not verified");
										}
										if (cp.getContactActivityAlertLastModifiedName(30).getText().trim()
												.contains(CRMUser1LastName)) {
											appLog.info("Name text is verified.");
										} else {
											appLog.info("Name text is not verified.");
											sa.assertTrue(false, "Name text is not verified.");
										}
										String lastModifiedDate = cp.getContactActivityAlertLastModifiedDate(60).getText()
												.trim();
										if (bp.verifyDate(lastModifiedDate, "Last Modified Date")) {
											appLog.info("Last Modified Date is verified");
										} else {
											appLog.info("Last Modified Date is not verified");
											sa.assertTrue(false, "Last Modified Date is not verified");
										}
									} else {
										appLog.info("Not able to click on activity alert subject link");
										sa.assertTrue(false, "Not able to click on activity alert subject link");
									}
								}
								else {
									appLog.error("could not click on view all on activity history");
									sa.assertTrue(false,"could not click on view all on activity history" );
								}
							}
						else {
							appLog.error("related tab is not clickable");
							sa.assertTrue(false, "related tab is not clickable");
						}
							driver.close();
							driver.switchTo().window(parentID);

						} else {
							appLog.info("No new weindow is open");
							sa.assertTrue(false, "No new window is open");
						}
					} else {
						appLog.info("Not able to click on contact 1 name");
						sa.assertTrue(false, "Not able to click on contact1 name");
					}
					ThreadSleep(3000);
					switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
					scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
							"Fundraising Section view");
					ele = FindElement(driver, "//a[text()='" + M10Institution1 + "']", "Contact 1 firm name",
							action.SCROLLANDBOOLEAN, 60);
					if (click(driver, ele, "Contact1 firm name ", action.SCROLLANDBOOLEAN)) {
						String parentID = switchOnWindow(driver);
						if (parentID != null) {
							scrollDownThroughWebelement(driver, ip.getNoteAndAttachmentText(30), "Activity History");
							List<WebElement> list = ip.getContactActivityHistoryNameRecords();
							String contactFullName = M10Contact1FirstName + " " + M10Contact1LastName;
							String[] contactnames = contactFullName.split(",");
							if (!list.isEmpty()) {
								for (int i = 0; i < contactnames.length; i++) {
									for (int j = 0; j < list.size(); j++) {
										if (list.get(j).getText().trim().contains(contactnames[i])) {
											appLog.info("Contact Name is verified: " + contactnames[i]);
											break;
										} else {
											if (j == list.size() - 1) {
												appLog.info("Contact Name is not verified: " + contactnames[i]);
												sa.assertTrue(false,
														"Contact Name is not verified: " + contactnames[i]);
											}
										}
									}
								}
							} else {
								appLog.info("Contact Name list is not available");
								sa.assertTrue(false, "Contact Name list is not available");
							}
							List<WebElement> list1 = ip.getAccountActivityAlertSubjectEmailIdList();
							if (!list1.isEmpty()) {
								for (int i = 0; i < list1.size(); i++) {
									System.out.println("Inside Loop, iteration number: " + i);
									String text = list1.get(i).getText().trim();
									if (click(driver, list1.get(i), "Subject Email ID", action.SCROLLANDBOOLEAN)) {
										appLog.info("Clicked on Email ID: " + text);
										break;
									} else {
										if (i == list1.size() - 1) {
											appLog.info("Not able to click on Account Activity Alert Subject Email Id");
											sa.assertTrue(false,
													"Not able to click on Account Activity Alert Subject Email Id");
										}
									}
								}
							} else {
								appLog.info("Contact Email Id list is not available");
								sa.assertTrue(false, "Contact Email Id list is not available");
							}
							if (cp.getContactAcitivityAlertAssignedToEmailText(30).getText().trim()
									.contains(CRMUser1LastName)) {
								appLog.info("Assigned To user name is verified.");
							} else {
								appLog.info("Assigned To user name is not verified.");
								sa.assertTrue(false, "Assigned To user name is not verified.");
							}
							if (cp.getContactAcitivityAlertSubjectText(30).getText().trim()
									.contains("Invitation from")) {
								appLog.info("Subject Text is  verified.");
							} else {
								appLog.info("Subject Text is not verified.");
								sa.assertTrue(false, "Subject Text is not verified.");
							}
							if (cp.getContactAcitivityAlertSubjectText(30).getText().trim().contains(Org1FirmName)) {
								appLog.info("Subject Text is  verified.");
							} else {
								appLog.info("Subject Text is not verified."+cp.getContactAcitivityAlertSubjectText(30).getText().trim()+ " and " +Org1FirmName);
								sa.assertTrue(false, "Subject Text is not verified.");
							}
							String date = cp.getContactActivityAlertDueDateText(30).getText().trim();
							if (bp.verifyDate(date, "Due")) {
								appLog.info("Due Date is verified.");
							} else {
								appLog.info("Due Date is not verified. Actual Result: " + date);
								sa.assertTrue(false, "Due Date is not verified. Actual Result: " + date);
							}
							if (cp.getContactAcitivityAlertCommentsText(30).getText().trim().contains("Subject:")) {
								appLog.info("Comments Text is verified.");
							} else {
								appLog.info("Comments Text is not verified.");
								sa.assertTrue(false, "Comments Text is not verified.");
							}
							if (cp.getContactAcitivityAlertCommentsText(30).getText().trim()
									.contains("Invitation from " + Org1FirmName)) {
								appLog.info("Comments subject Text is verified.");
							} else {
								appLog.info("Comments subject Text is not verified.."+Org1FirmName);
								sa.assertTrue(false, "Comments subject Text is not verified.");
							}
							if (cp.getContactAcitivityAlertCommentsText(30).getText().trim()
									.contains("Hello " + M10Contact1FirstName)) {
								appLog.info("Comments body Text is verified.");
							} else {
								appLog.info("Comments body Text is not verified..");
								sa.assertTrue(false, "Comments body Text is not verified.");
							}
							if (cp.getContactAcitivityAlertCommentsText(30).getText().trim()
									.contains("You have been granted access to Potential investments of " + M10FundName1
											+ " by")) {
								appLog.info("Comments body Text is verified.");
							} else {
								appLog.info("Comments body Text is not verified..");
								sa.assertTrue(false, "Comments body Text is not verified.");
							}
							if (cp.getContactAcitivityAlertCommentsText(30).getText().trim().contains(
									"If you believe this has been sent in error, or if you cannot login, please contact")) {
								appLog.info("Comments body Text is verified.");
							} else {
								appLog.info("Comments body Text is not verified..");
								sa.assertTrue(false, "Comments body Text is not verified.");
							}
							if (cp.getContactAcitivityAlertCommentsText(30).getText().trim()
									.contains("If you have not yet registered, Click here to register.")) {
								appLog.info("Comments body Text is verified.");
							} else {
								appLog.info("Comments body Text is not verified..");
								sa.assertTrue(false, "Comments body Text is not verified.");
							}
							if (cp.getContactAcitivityAlertCommentsText(30).getText().trim()
									.contains("If you have already registered, Click here to login.")) {
								appLog.info("Comments body Text is verified.");
							} else {
								appLog.info("Comments body Text is not verified..");
								sa.assertTrue(false, "Comments body Text is not verified.");
							}
							if (cp.getContactActivityAlertStatusText(30).getText().trim().contains("Completed")) {
								appLog.info("Status text is verified.");
							} else {
								appLog.info("Status text is not verified.");
								sa.assertTrue(false, "Status text is not verified.");
							}
							if (cp.getContactActivityAlertPriorityText(30).getText().trim().contains("Normal")) {
								appLog.info("Priority text is verified.");
							} else {
								appLog.info("Priority text is not verified.");
								sa.assertTrue(false, "Priority text is not verified.");
							}
							if (cp.getAccountActivityAlertRelatedToText(60).getText().trim()
									.contains(M10Institution1)) {
								appLog.info("Institution Name is verified in Activity Alert on Institution Page.");
							} else {
								appLog.info("Institution Name is not verified in Activity Alert on Institution Page.");
								sa.assertTrue(false,
										"Institution Name is not verified in Activity Alert on Institution Page.");
							}
							if (cp.getContactActivityAlertEmailIdtext(30).getText().trim()
									.contains(M10Contact1EmailId)) {
								appLog.info("Email ID is verified.");
							} else {
								appLog.info("Email ID is not verified.");
								sa.assertTrue(false, "Email ID is not verified.");
							}
							if (cp.getContactActivityAlertCreatedByText(30).getText().trim()
									.contains(CRMUser1LastName)) {
								appLog.info("Created By text is verified.");
							} else {
								appLog.info("Created By text is not verified.");
								sa.assertTrue(false, "Created By text is not verified.");
							}
							if (cp.getAccountActivityAlertNameText(30).getText().trim().contains(M10Contact1LastName)) {
								appLog.info("Name text is verified.");
							} else {
								appLog.info("Name text is not verified.");
								sa.assertTrue(false, "Name text is not verified.");
							}
							String createdByDate = cp.getContactActivityAlertCreatedByDate(60).getText().trim();
							if (bp.verifyDate(createdByDate, "Created By Date")) {
								appLog.info("Cretaed by date is verified");
							} else {
								appLog.info("Created by date is not verified");
								sa.assertTrue(false, "Created by date is not verified");
							}
							if (cp.getContactActivityAlertLastModifiedName(30).getText().trim()
									.contains(CRMUser1LastName)) {
								appLog.info("Last Modified Name text is verified.");
							} else {
								appLog.info("Last Modified Name text is not verified.");
								sa.assertTrue(false, "Last Modified Name text is not verified.");
							}
							String lastModifiedDate = cp.getContactActivityAlertLastModifiedDate(60).getText().trim();
							if (bp.verifyDate(lastModifiedDate, "Last Modified Date")) {
								appLog.info("Last Modified Date is verified");
							} else {
								appLog.info("Last Modified Date is not verified");
								sa.assertTrue(false, "Last Modified Date is not verified");
							}
							String result = ip.getAccountActivityAlertNameText(60).getText().trim();
							for (int i = 0; i < contactnames.length; i++) {
								if (contactnames[i].equalsIgnoreCase(result)) {
									appLog.info("Coontact Name is verified: " + result);
									break;
								} else {
									if (i == contactnames.length - 1) {
										appLog.info("Contact Name is not verified :" + result);
										sa.assertTrue(false, "Contact Name is not verified :" + result);
									}
								}
							}
							driver.close();
							driver.switchTo().window(parentID);
						} else {
							appLog.info("No new window is open");
							sa.assertTrue(false, "No new window is open");
						}
					} else {
						appLog.info("Not able to click on firm name");
						sa.assertTrue(false, "Not able to click on firm name");
					}
				} else {
					appLog.info("Not able to click on manage email");
					sa.assertTrue(false, "Not able to click on manage emails");
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
		lp.CRMlogout(environment, mode);
		String investormailcontent = null;
		try {
			investormailcontent = new EmailLib().getInvestorMailContent("invitationMail", gmailUserName, gmailPassword,
					CRMUser1EmailID, M10Contact1EmailId);
		} catch (InterruptedException e) {
			appLog.info("Invitation mail not found.");

			e.printStackTrace();
		}
		if (investormailcontent != null) {
			if (investormailcontent.contains("You have been granted access to Potential investments of " + M10FundName1
					+ " by " + Org1FirmName + ".")) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}
			if (investormailcontent.contains("If you have not yet registered")) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}
			if (investormailcontent.contains("If you have already registered")) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}
			if (investormailcontent.contains("Click here") && investormailcontent.contains("to register.")) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}
			if (investormailcontent.contains("Click here") && investormailcontent.contains("to login")) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}
			if (investormailcontent
					.contains("If you believe this has been sent in error, or if you cannot login, please contact "
							+ Org1FirmName + ".")) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}

			if (investormailcontent.contains("Hello "+ M10Contact1FirstName)) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}
			String[] ss = investormailcontent.split("href=\"");
			String[] ss1 = ss[1].split("\"");
			String investorregLink = ss1[0];
			String[] spilt = investormailcontent.split("If you have already registered");
			System.err.println("0" + spilt[0]);
			System.err.println("1" + spilt[1]);
			String[] ssa = spilt[1].split("href=\"");
			String[] ss2 = ssa[1].split("\"");
			String loginLink = ss2[0];
			ExcelUtils.writeData(investorregLink, "Contacts", excelLabel.Variable_Name, "M10Contact1",
					excelLabel.TargetRegistrationURL);
			ExcelUtils.writeData(loginLink, "Contacts", excelLabel.Variable_Name, "M10Contact1",
					excelLabel.TargetLoginURL);
			driver.get(investorregLink);
			sa.assertTrue(getURL(driver, 60).contains("SiteRegisteration"),
					"Registration Page is not open after clicking on Resgister Click Here Link.");
			ThreadSleep(4000);
			driver.close();
			config(browserToLaunch);
			driver.get(loginLink);
			lp = new LoginPageBusinessLayer(driver);
			if (isDisplayed(driver, lp.getInvestorLoginButton(60), "Visibility", 60, "Login Button") != null) {
				appLog.info("Login Page is  open after clicking on login Click Here Link.");
			} else {
				appLog.info("Login Page is  not open after clicking on login Click Here Link.");
				sa.assertTrue(false, "Login Page is  not open after clicking on login Click Here Link.");
			}
		} else {
			appLog.info("Mail didn't receive");
			sa.assertTrue(false, "Mail didn't receive");
		}
		sa.assertAll();
	}

	@Test
	public void M10tc009_RegisterInvestorAndCheckImpactInManageEmailsPopUp() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		AllFirmsPageBusinesslayer afp = new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		SoftAssert sa = new SoftAssert();
		String InvestorRegLink = ExcelUtils.readData("Contacts", excelLabel.Variable_Name, "M10Contact1",
				excelLabel.TargetRegistrationURL);
		driver.get(InvestorRegLink);
		if (bp.targetRegistration(M10Contact1FirstName + "RUP", M10Contact1LastName + "RUP", M10Contact1EmailId,
				M10Institution1 + "RUP", adminPassword)) {
			appLog.info("Investor is registered successfully: " + M10Contact1FirstName + " " + M10Contact1LastName);
			ExcelUtils.writeData("Registered", "Contacts", excelLabel.Variable_Name, "M10Contact1",
					excelLabel.Registered);
		} else {
			driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
			appLog.info("Investor Target URL through Direct URL..");
			if (bp.targetRegistration(M10Contact1FirstName + "RUP", M10Contact1LastName + "RUP", M10Contact1EmailId,
					M10Institution1 + "RUP", adminPassword)) {
				appLog.info("Investor is registered successfully: " + M10Contact1FirstName + " " + M10Contact1LastName);
				ExcelUtils.writeData("Registered", "Contacts", excelLabel.Variable_Name, "M10Contact1",
						excelLabel.Registered);
			} else {
				appLog.error("Investor is not Registered through Invited Link: " + M10Contact1FirstName + " "
						+ M10Contact1LastName);
				sa.assertTrue(false, "Investor is not Registered through Invited Link: " + M10Contact1FirstName + " "
						+ M10Contact1LastName);
			}
		}
		String firmName = getSelectedOptionOfDropDown(driver, afp.getFirmNameDropdown(60), "Firm name dropdown",
				"text");
		if (firmName.equalsIgnoreCase(Org1FirmName)) {
			appLog.info("Firm Page Is opened");
		} else {
			appLog.info("Firm Page is not opened");
			sa.assertTrue(false, "Firm Page is not opened");
		}
		if (isDisplayed(driver, ifp.getActivitiesCreatedOnLabel(20), "Visibility", 30,
				"Activity created on label") == null) {
			appLog.info("All Document Tab is opened");
		} else {
			appLog.info("All Document Tab is not opened");
			sa.assertTrue(false, "All Document Tab is not opened");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M10FundName1)) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
						"Fundraising Section view");
				if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Fundarising workspace",
						action.SCROLLANDBOOLEAN)) {
					if (fp.verifyManageEmailGrid(M10Contact1FirstName + "RUP" + " " + M10Contact1LastName + "RUP",
							M10Contact1EmailId, M10Institution1 + "RUP", "Last Invite Date")) {
						appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
								+ M10Contact1LastName);
					} else {
						appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
								+ M10Contact1LastName);
						sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
								+ M10Contact1LastName);
					}
					if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName, M10Contact2EmailId,
							M10Institution2, null)) {
						appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
								+ M10Contact2LastName);
					} else {
						appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
								+ M10Contact2LastName);
						sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
								+ M10Contact2LastName);
					}
					WebElement ele = FindElement(driver,
							"//a[text()='" + M10Contact1FirstName + "RUP" + " " + M10Contact1LastName + "RUP" + "']",
							"Contact 1", action.SCROLLANDBOOLEAN, 30);
					if (click(driver, ele, "Contact 1 name", action.SCROLLANDBOOLEAN)) {
						String parentID = switchOnWindow(driver);
						if (parentID != null) {
							ele = isDisplayed(driver,
									FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40),
									"visibility", 60, "Contact tab");
							if (ele != null) {
								String header = ele.getText().trim();
								if (header.contains(M10Contact1FirstName + " " + M10Contact1LastName)) {
									appLog.info("Contact 1 page open successfully");
								} else {
									appLog.info("Contact 1 page is not open successfully");
									sa.assertTrue(false, "Contact 1 page is not open successfully");
								}
							} else {
								appLog.info("Not able to find header of contact tab");
								sa.assertTrue(false, "Not able to find header of contact tab");
							}
							driver.close();
							driver.switchTo().window(parentID);
						} else {
							appLog.info("no new window is open");
							sa.assertTrue(false, "No new window is open");
						}
					} else {
						appLog.info("Not able to click on contact 1 name");
						sa.assertTrue(false, "Not able to click on contact 1 name");
					}
					switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
					scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
							"Fundraising Section view");
					ele = FindElement(driver, "//a[text()='" + M10Institution1 + "RUP" + "']", "Contact 1 firm name",
							action.SCROLLANDBOOLEAN, 60);
					if (click(driver, ele, "Contact1 firm name ", action.SCROLLANDBOOLEAN)) {
						String parentID = switchOnWindow(driver);
						if (parentID != null) {
							ele = isDisplayed(driver,
									FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40),
									"visibility", 60, "Institution tab");
							if (ele != null) {
								String header = ele.getText().trim();
								if (header.contains(M10Institution1)) {
									appLog.info("Institution 1 page is open ");
								} else {
									appLog.info("Institution 1 page is not open ");
									sa.assertTrue(false, "Institution 1 page is not open ");
								}
							} else {
								appLog.info("Not able to find header of Institution tab");
								sa.assertTrue(false, "Not able to find header of Institution tab");
							}
							driver.close();
							driver.switchTo().window(parentID);
						} else {
							appLog.info("no new window is open");
							sa.assertTrue(false, "No new window is open");
						}
					} else {
						appLog.info("Not able to click on firm name");
						sa.assertTrue(false, "Not able to click on firm name");
					}
				} else {
					appLog.info("Not able to click on manage email icon");
					sa.assertTrue(false, "Not able ot click on manage email icon");
				}
			} else {
				appLog.info("Not able to click on craeted fund");
				sa.assertTrue(false, "Not able to click on craeted fund");
			}
		} else {
			appLog.info("Not able to click on funds tab");
			sa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}

	@Test
	public void M10tc010_CheckSearchingAndSortingOnManageEmail() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,
				excelLabel.StandardPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M10FundName1)) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
						"Fundraising Section view");
				if (fp.verifyFolderPathdummy(stdPath, M10Institution1, null, M10FundName1, PageName.FundsPage,
						Workspace.FundraisingWorkspace, 60)) {
				} else {
					appLog.info("Not able to find folder path");
					sa.assertTrue(false, "Not able to find folder path");
				}
				if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage Email",
						action.SCROLLANDBOOLEAN)) {
					if (selectVisibleTextFromDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60),
							"Contact access view dropdown list", "All Folders")) {
						if (sendKeys(driver, fp.getManageEmailSearchTextBox(60), "123", "Search box",
								action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageEmailSearchBtn(60), "Search icon", action.SCROLLANDBOOLEAN)) {
								if (fp.getManageEMailContactGridErrorMsg(30).getText().trim()
										.contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
									appLog.info("No data to display error message is verified");
								} else {
									appLog.error("No data to display error messsage is not verified");
									sa.assertTrue(false, "No data to display error messsage is not verified");
								}
							} else {
								appLog.info("Not able to click on search icon");
								sa.assertTrue(false, "Not able to click on search icon");
							}
						} else {
							appLog.info("Not able to enter value in search text box");
							sa.assertTrue(false, "Not able to enter value in search text box");
						}
						if (click(driver, fp.getManageEmailClearSearchEnableIcon(60), "Clear search icon",
								action.SCROLLANDBOOLEAN)) {
							if (fp.verifyManageEmailGrid(
									M10Contact1FirstName + "RUP" + " " + M10Contact1LastName + "RUP",
									M10Contact1EmailId, M10Institution1 + "RUP", "Last Invite Date")) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: "
										+ M10Contact1FirstName + " " + M10Contact1LastName);
							}
							if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,
									M10Contact2EmailId, M10Institution2, null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: "
										+ M10Contact2FirstName + " " + M10Contact2LastName);
							}
						} else {
							appLog.info("Not able to clcik on clear search icon");
							sa.assertTrue(false, "Not able to click on clear search icon");
						}
						if (sendKeys(driver, fp.getManageEmailSearchTextBox(60), M10Institution1 + "RUP", "Search box",
								action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageEmailSearchBtn(60), "Search icon", action.SCROLLANDBOOLEAN)) {
								if (fp.verifyManageEmailGrid(
										M10Contact1FirstName + "RUP" + " " + M10Contact1LastName + "RUP",
										M10Contact1EmailId, M10Institution1 + "RUP", "Last Invite Date")) {
									appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
											+ M10Contact1LastName);
								} else {
									appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName
											+ " " + M10Contact1LastName);
									sa.assertTrue(false, "Grid Data is not verified for the contact: "
											+ M10Contact1FirstName + " " + M10Contact1LastName);
								}
							} else {
								appLog.info("Not able to click on search icon");
								sa.assertTrue(false, "Not able to click on search icon");
							}
						} else {
							appLog.info("Not able to enter value in search text box");
							sa.assertTrue(false, "Not able to enter value in search text box");
						}
						if (click(driver, fp.getManageEmailClearSearchEnableIcon(60), "Clear search icon",
								action.SCROLLANDBOOLEAN)) {
							if (fp.verifyManageEmailGrid(
									M10Contact1FirstName + "RUP" + " " + M10Contact1LastName + "RUP",
									M10Contact1EmailId, M10Institution1 + "RUP", "Last Invite Date")) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: "
										+ M10Contact1FirstName + " " + M10Contact1LastName);
							}
							if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,
									M10Contact2EmailId, M10Institution2, null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: "
										+ M10Contact2FirstName + " " + M10Contact2LastName);
							}
						} else {
							appLog.info("Not able to clcik on clear search icon");
							sa.assertTrue(false, "Not able to click on clear search icon");
						}
						WebElement contact1CheckBox = FindElement(driver, "//a[text()='" + M10Contact1FirstName + "RUP"
								+ " " + M10Contact1LastName + "RUP" + "']/../..//input", "Contact Check Box",
								action.SCROLLANDBOOLEAN, 30);
						if (click(driver, contact1CheckBox, "Contact1 checkbox", action.SCROLLANDBOOLEAN)) {
							if (sendKeys(driver, fp.getManageEmailSearchTextBox(60), M10Contact1FirstName + "RUP",
									"Search box", action.SCROLLANDBOOLEAN)) {
								if (click(driver, fp.getManageEmailSearchBtn(60), "Search icon",
										action.SCROLLANDBOOLEAN)) {
									if (fp.verifyManageEmailGrid(
											M10Contact1FirstName + "RUP" + " " + M10Contact1LastName + "RUP",
											M10Contact1EmailId, M10Institution1 + "RUP", "Last Invite Date")) {
										appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName
												+ " " + M10Contact1LastName);
									} else {
										appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName
												+ " " + M10Contact1LastName);
										sa.assertTrue(false, "Grid Data is not verified for the contact: "
												+ M10Contact1FirstName + " " + M10Contact1LastName);
									}
									ThreadSleep(2000);
									contact1CheckBox = FindElement(driver,
											"//a[text()='" + M10Contact1FirstName + "RUP" + " " + M10Contact1LastName
													+ "RUP" + "']/../..//input",
											"Contact Check Box", action.SCROLLANDBOOLEAN, 30);
									if (isSelected(driver, contact1CheckBox, "Contact1 checkbox")) {
										appLog.info("Contact1 checkbox is selected");
									} else {
										appLog.info("Contact 1 checkbox is not selected");
										sa.assertTrue(false, "Contact1  checkbox is not selected");
									}
									ThreadSleep(2000);
									if (isSelected(driver, fp.getManageEMailContactAllCheckBox(60),
											"Contact all checkbox")) {
										appLog.info("Contact all checkbox is selected");
									} else {
										appLog.info("Contact all checkbox is not selected");
										sa.assertTrue(false, "Contact all  checkbox is not selected");
									}
								} else {
									appLog.info("Not able to click on search icon");
									sa.assertTrue(false, "Not able to click on search icon");
								}
							} else {
								appLog.info("Not able to enter value in search text box");
								sa.assertTrue(false, "Not able to enter value in search text box");
							}
						} else {
							appLog.info("Not able to click on contact 1 checkbox");
							sa.assertTrue(false, "Not able to click on contact1 checkbox");
						}
						if (click(driver, fp.getManageEmailClearSearchEnableIcon(60), "Clear search icon",
								action.SCROLLANDBOOLEAN)) {
							if (fp.verifyManageEmailGrid(
									M10Contact1FirstName + "RUP" + " " + M10Contact1LastName + "RUP",
									M10Contact1EmailId, M10Institution1 + "RUP", "Last Invite Date")) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: "
										+ M10Contact1FirstName + " " + M10Contact1LastName);
							}
							if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,
									M10Contact2EmailId, M10Institution2, null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: "
										+ M10Contact2FirstName + " " + M10Contact2LastName);
							}
							contact1CheckBox = FindElement(
									driver, "//a[text()='" + M10Contact1FirstName + "RUP" + " " + M10Contact1LastName
											+ "RUP" + "']/../..//input",
									"Contact Check Box", action.SCROLLANDBOOLEAN, 30);
							if (isSelected(driver, contact1CheckBox, "Contact1 checkbox")) {
								appLog.info("Contact1 checkbox is selected");
							} else {
								appLog.info("Contact 1 checkbox is not selected");
								sa.assertTrue(false, "Contact1  checkbox is not selected");
							}
							if (!isSelected(driver, fp.getManageEMailContactAllCheckBox(60), "Contact all checkbox")) {
								appLog.info("Contact all checkbox is not selected");
							} else {
								appLog.info("Contact all checkbox is selected");
								sa.assertTrue(false, "Contact all  checkbox is selected");
							}
							WebElement contact2CheckBox = isDisplayed(driver,
									FindElement(driver,
											"//a[text()='" + M10Contact2FirstName + " " + M10Contact2LastName
													+ "']/../..//input",
											"Contact Check Box", action.SCROLLANDBOOLEAN, 30),
									"visibility", 30, "Manage Emails Contact Check Box");
							if (!isSelected(driver, contact2CheckBox, "Contact1 checkbox")) {
								appLog.info("Contact2 checkbox is not selected");
							} else {
								appLog.info("Contact 2 checkbox is selected");
								sa.assertTrue(false, "Contact2  checkbox is selected");
							}
						} else {
							appLog.info("Not able to clcik on clear search icon");
							sa.assertTrue(false, "Not able to click on clear search icon");
						}
						if (sendKeys(driver, fp.getManageEmailSearchTextBox(60), M10Contact2EmailId, "Search box",
								action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageEmailSearchBtn(60), "Search icon", action.SCROLLANDBOOLEAN)) {
								if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,
										M10Contact2EmailId, M10Institution2, null)) {
									appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
											+ M10Contact2LastName);
								} else {
									appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName
											+ " " + M10Contact2LastName);
									sa.assertTrue(false, "Grid Data is not verified for the contact: "
											+ M10Contact2FirstName + " " + M10Contact2LastName);
								}
							} else {
								appLog.info("Not able to click on search icon");
								sa.assertTrue(false, "Not able to click on search icon");
							}
						} else {
							appLog.info("Not able to enter value in search text box");
							sa.assertTrue(false, "Not able to enter value in search text box");
						}
						if (click(driver, fp.getManageEmailClearSearchEnableIcon(60), "Clear search icon",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Able to clcik on clear search icon");
						} else {
							appLog.info("Not able to clcik on clear search icon");
							sa.assertTrue(false, "Not able to click on clear search icon");
						}
						if (sendKeys(driver, fp.getManageEmailSearchTextBox(60), getSystemDate("MM/dd/yyyy"),
								"Search box", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageEmailSearchBtn(60), "Search icon", action.SCROLLANDBOOLEAN)) {
								if (fp.getManageEMailContactGridErrorMsg(30).getText().trim()
										.contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
									appLog.info("No data to display error message is verified");
								} else {
									appLog.error("No data to display error messsage is not verified");
									sa.assertTrue(false, "No data to display error messsage is not verified");
								}
							} else {
								appLog.info("Not able to click on search icon");
								sa.assertTrue(false, "Not able to click on search icon");
							}
						} else {
							appLog.info("Not able to enter value in search text box");
							sa.assertTrue(false, "Not able to enter value in search text box");
						}
						if (click(driver, fp.getManageEmailClearSearchEnableIcon(60), "Clear search icon",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Able to clcik on clear search icon");
						} else {
							appLog.info("Not able to clcik on clear search icon");
							sa.assertTrue(false, "Not able to click on clear search icon");
						}
						if (click(driver, fp.getManageEmailEmailLabel(60), "Email label", action.SCROLLANDBOOLEAN)) {
							if (sendKeys(driver, fp.getManageEmailSearchTextBox(60),
									M10Contact1FirstName + "RUP" + " " + M10Contact1LastName + "RUP", "Search box",
									action.SCROLLANDBOOLEAN)) {
								if (click(driver, fp.getManageEmailSearchBtn(60), "Search icon",
										action.SCROLLANDBOOLEAN)) {
									if (fp.verifyManageEmailGrid(
											M10Contact1FirstName + "RUP" + " " + M10Contact1LastName + "RUP",
											M10Contact1EmailId, M10Institution1 + "RUP", "Last Invite Date")) {
										appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName
												+ " " + M10Contact1LastName);
									} else {
										appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName
												+ " " + M10Contact1LastName);
										sa.assertTrue(false, "Grid Data is not verified for the contact: "
												+ M10Contact1FirstName + " " + M10Contact1LastName);
									}
									if (bp.checkSorting(SortOrder.Assecending,
											fp.getManageEmailListOfContactEmails())) {
										appLog.info("Contact email is displaying is ascending order");
									} else {
										appLog.info("Contact email is not displaying is ascending order");
										sa.assertTrue(false, "Contact email is not displaying is ascending order");
									}
								} else {
									appLog.info("Not able to click on search icon");
									sa.assertTrue(false, "Not able to click on search icon");
								}
							} else {
								appLog.info("Not able to enter value in search text box");
								sa.assertTrue(false, "Not able to enter value in search text box");
							}
						} else {
							appLog.info("Not able to sleect email coloumn");
							sa.assertTrue(false, "Not able to select email coloumn");
						}
					} else {
						appLog.info("Not able to select All Folder Option from the contact access view dropdown");
						sa.assertTrue(false,
								"Not able to select All Folder Option from the contact access view dropdown");
					}
					if (selectVisibleTextFromDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60),
							"Contact access view dropdown list", stdPath)) {
						if (fp.verifyManageEmailGrid(M10Contact1FirstName + "RUP" + " " + M10Contact1LastName + "RUP",
								M10Contact1EmailId, M10Institution1 + "RUP", "Last Invite Date")) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName
									+ " " + M10Contact1LastName);
						}
						WebElement sortingIcon = isDisplayed(driver,
								FindElement(driver,
										"//div[@id='manageemailgrid_ME']//span[text()='Last Invite Date']/span",
										"Manage Emails Sorting Icon", action.SCROLLANDBOOLEAN, 30),
								"visibility", 30, "Manage Emails Sorting Icon");
						if (sortingIcon != null) {
							appLog.info("Sorting Icon is displaying by default on last invite date column");
						} else {
							appLog.info("Sorting Icon is not displaying by default on last invite date column");
							sa.assertTrue(false,
									"Sorting Icon is not displaying by default on last invite date column");
						}
					} else {
						appLog.info(
								"Not able to select standard parent folder Option from the contact access view dropdown");
						sa.assertTrue(false,
								"Not able to select standard parent folder  Option from the contact access view dropdown");
					}
					if (selectVisibleTextFromDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60),
							"Contact access view dropdown list", "All Folders")) {
						if (click(driver, fp.getManageEMailFirmHeaderText(30), "Manage Email Firm Header Text",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on firm name header text");
							if (fp.checkSorting(SortOrder.Assecending, fp.getFirmsInGridInManageEmails(60))) {
								appLog.info("Ascending sorting is verified on firm name ");
							} else {
								appLog.info("Ascending sorting is not verified on firm name ");
								sa.assertTrue(false, "Ascending sorting is not verified on firm name ");
							}
						} else {
							appLog.info("Not able to Clicke on firm name header text");
							sa.assertTrue(false, "Not able to Clicke on firm name header text");
						}
						if (click(driver, fp.getManageEMailFirmHeaderText(30), "Manage Email Firm Header Text",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on firm name header text");
							if (fp.checkSorting(SortOrder.Decending, fp.getFirmsInGridInManageEmails(60))) {
								appLog.info("Decending sorting is verified on firm name ");
							} else {
								appLog.info("Decending sorting is not verified on firm name ");
								sa.assertTrue(false, "Decending sorting is not verified on firm name ");
							}
						} else {
							appLog.info("Not able to Clicke on firm name header text");
							sa.assertTrue(false, "Not able to Clicke on firm name header text");
						}
						if (click(driver, fp.getManageEMailLastInviteDateHeaderText(30),
								"Manage Email Last Invite Date Text", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on  Last Invite Date text");
							if (fp.checkSorting(SortOrder.Assecending, fp.getLastInviteDateInGridInManageEmails(60))) {
								appLog.info("Ascending sorting is verified on  Last Invite Date ");
							} else {
								appLog.info("Ascending sorting is not verified on  Last Invite Date ");
								sa.assertTrue(false, "Ascending sorting is not verified on  Last Invite Date");
							}
						} else {
							appLog.info("Not able to Clicke on  Last Invite Date header text");
							sa.assertTrue(false, "Not able to Clicke on  Last Invite Date header text");
						}
						if (click(driver, fp.getManageEMailLastInviteDateHeaderText(30),
								"Manage Email  Last Invite Date Header Text", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Last Invite Date header text");
							if (fp.checkSorting(SortOrder.Decending, fp.getLastInviteDateInGridInManageEmails(60))) {
								appLog.info("Decending sorting is verified on Last Invite Date ");
							} else {
								appLog.info("Decending sorting is not verified on Last Invite Date");
								sa.assertTrue(false, "Decending sorting is not verified on Last Invite Date");
							}
						} else {
							appLog.info("Not able to Clicke on Last Invite Date header text");
							sa.assertTrue(false, "Not able to Clicke on Last Invite Dateheader text");
						}
						if (click(driver, fp.getManageEMailContactNameHeaderText(30), "Manage Email Contact name Text",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on  Contact name Text");
							if (fp.checkSorting(SortOrder.Assecending, fp.getManageEmailsListofContacts())) {
								appLog.info("Ascending sorting is verified on  Contact name");
							} else {
								appLog.info("Ascending sorting is not verified on Contact name");
								sa.assertTrue(false, "Ascending sorting is not verified on  Contact name");
							}
						} else {
							appLog.info("Not able to Clicke on  Contact name header text");
							sa.assertTrue(false, "Not able to Clicke on  Contact name header text");
						}
						if (click(driver, fp.getManageEMailContactNameHeaderText(30),
								"Manage Email  Contact name Header Text", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Contact name header text");
							if (fp.checkSorting(SortOrder.Decending, fp.getManageEmailsListofContacts())) {
								appLog.info("Decending sorting is verified on Contact name");
							} else {
								appLog.info("Decending sorting is not verified on Contact name");
								sa.assertTrue(false, "Decending sorting is not verified on Contact name");
							}
						} else {
							appLog.info("Not able to Clicke on Contact name header Contact name");
							sa.assertTrue(false, "Not able to Click on Contact name header text");
						}
						if (click(driver, fp.getManageEmailEmailLabel(30), "Manage Email email Text",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on  email Text");
							if (fp.checkSorting(SortOrder.Assecending, fp.getManageEmailListOfContactEmails())) {
								appLog.info("Ascending sorting is verified on  email");
							} else {
								appLog.info("Ascending sorting is not verified on email");
								sa.assertTrue(false, "Ascending sorting is not verified on  email");
							}
						} else {
							appLog.info("Not able to Clicke on  email header text");
							sa.assertTrue(false, "Not able to Clicke on  email header text");
						}
						if (click(driver, fp.getManageEmailEmailLabel(30), "Manage Email email Header Text",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on email header text");
							if (fp.checkSorting(SortOrder.Decending, fp.getManageEmailListOfContactEmails())) {
								appLog.info("Decending sorting is verified on email");
							} else {
								appLog.info("Decending sorting is not verified on email");
								sa.assertTrue(false, "Decending sorting is not verified on email");
							}
						} else {
							appLog.info("Not able to Clicke on email header text");
							sa.assertTrue(false, "Not able to Click on email header text");
						}

					} else {
						appLog.info("Not able to select All Folder Option from the contact access view dropdown");
						sa.assertTrue(false,
								"Not able to select All Folder Option from the contact access view dropdown");
					}
				} else {
					appLog.info("Not able to click on manage email icon");
					sa.assertTrue(false, "Not able to click on manage emails");
				}
			} else {
				appLog.info("Not able to click on cretaed fund");
				sa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on funds tab");
			sa.assertTrue(false, "Not able ot click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) 
	@Test
	public void M10tc011_InviteContactFromSharedParentFolderAndVerifyfManageEmailIconInContactAccessPopup() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String sharedPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,
				excelLabel.SharedPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M10FundName1)) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
						"Fundraising Section view");
				if (fp.verifyFolderPathdummy(sharedPath, null, null, M10FundName1, PageName.FundsPage,
						Workspace.FundraisingWorkspace, 60)) {
					if (click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 60),
							"Contact access icon", action.SCROLLANDBOOLEAN)) {
						if (fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)) {
							appLog.info(
									Workspace.FundraisingWorkspace + " contact access popUp is expanded successfully.");
							if (sendKeys(driver, fp.getSearchTextBox(Workspace.FundraisingWorkspace, 60),
									M10Contact2EmailId, Workspace.FundraisingWorkspace + " search text box",
									action.SCROLLANDBOOLEAN)) {
								appLog.info("enter the value in search text box : " + M10Contact2EmailId);
								if (click(driver, fp.getSearchBtn(Workspace.FundraisingWorkspace, 60),
										Workspace.FundraisingWorkspace + " search button", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on  " + Workspace.FundraisingWorkspace + " search button");
									WebElement contactcheckBox = FindElement(driver,
											"//div[@id='shwTopGridBWFR_MA']//a[text()='" + M10Contact2EmailId
													+ "']/../..//input",
											M10Contact2EmailId + " contact check box", action.SCROLLANDBOOLEAN, 30);
									scrollDownThroughWebelement(driver, contactcheckBox, "Checkbox");
									if (click(driver, contactcheckBox, "check box", action.BOOLEAN)) {
										appLog.info("clicked on contact check box : " + M10Contact2EmailId);
										if (click(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30),
												Workspace.FundraisingWorkspace + " add select contact active button",
												action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on add selected contact active button in "
													+ Workspace.FundraisingWorkspace);
											ThreadSleep(5000);
											List<WebElement> listofContact = fp
													.getselectContactEmailIDList(Workspace.FundraisingWorkspace);

											if (!listofContact.isEmpty()) {
												for (int j = 0; j < listofContact.size(); j++) {
													String contactemail = listofContact.get(j).getText().trim();
													if (contactemail.equalsIgnoreCase(M10Contact2EmailId)) {
														appLog.info(M10Contact2EmailId
																+ " contact is displaying in selected contact access grid");
														break;
													} else {
														if (j == listofContact.size() - 1) {
															appLog.error(contactemail + " is not matched with >>> "
																	+ M10Contact2EmailId);
															sa.assertTrue(false, contactemail
																	+ " is not matched with >>> " + M10Contact2EmailId);
														}
													}
												}
											}
											if (click(driver,
													fp.geManageEmailIconInContactAccessPopup(
															Workspace.FundraisingWorkspace, 60),
													"Mange Amil icon in contact access popup",
													action.SCROLLANDBOOLEAN)) {
												String header = fp.geManageEmailPopupHeaderInContactAccessPopup(
														Workspace.FundraisingWorkspace, 60).getText().trim();
												if (header.equalsIgnoreCase("Confirmation")) {
													appLog.info("Confirmation popup is displaying");
												} else {
													appLog.info("Confirmation popup is not displaying");
													sa.assertTrue(false, "Confirmation popup is not displaying");
												}
												if (fp.geManageEmailPopupErrorMessageInContactAccessPopup(
														Workspace.FundraisingWorkspace, 60).getText().trim().contains(
																FundsPageErrorMessage.MnaageEmailContactAccessErrorMessage1)) {
													appLog.info("Error Message is verified");
												} else {
													appLog.info("Error Message is not verified");
													sa.assertTrue(false, "Error Message is not verified");
												}
												if (fp.geManageEmailPopupErrorMessageInContactAccessPopup(
														Workspace.FundraisingWorkspace, 60).getText().trim().contains(
																FundsPageErrorMessage.MnaageEmailContactAccessErrorMessage2)) {
													appLog.info("Error Message is verified");
												} else {
													appLog.info("Error Message is not verified");
													sa.assertTrue(false, "Error Message is not verified");
												}
												if (isDisplayed(driver,
														fp.geManageEmailPopupYesButtonInContactAccessPopup(
																Workspace.FundraisingWorkspace, 60),
														"Visibility", 60, "Yes Button") != null) {
													appLog.info("YEs Button is displaying");
												} else {
													appLog.info("Yes Button is not displaying");
													sa.assertTrue(false, "Yes Button is not displaying");
												}
												if (isDisplayed(driver,
														fp.geManageEmailPopupNoButtonInContactAccessPopup(
																Workspace.FundraisingWorkspace, 60),
														"Visibility", 60, "No Button") != null) {
													appLog.info("No Button is displaying");
												} else {
													appLog.info("No Button is not displaying");
													sa.assertTrue(false, "No Button is not displaying");
												}
												if (isDisplayed(driver,
														fp.geManageEmailPopupCloseIconInContactAccessPopup(
																Workspace.FundraisingWorkspace, 60),
														"Visibility", 60, "Close Icon") != null) {
													appLog.info("Close Icon is displaying");
												} else {
													appLog.info("Close Icon is not displaying");
													sa.assertTrue(false, "Close Icon is not displaying");
												}
												if (click(driver,
														fp.geManageEmailPopupNoButtonInContactAccessPopup(
																Workspace.FundraisingWorkspace, 60),
														"No Button", action.SCROLLANDBOOLEAN)) {
													if (fp.geManageEmailPopupNoButtonInContactAccessPopup(
															Workspace.FundraisingWorkspace, 20) == null) {
														appLog.info("Confirmation popup is closed");
													} else {
														appLog.info("Confirmation popup is not closed");
														sa.assertTrue(false, "Confirmation popup is not closed");
													}
												} else {
													appLog.info("Not able ot click on no button");
													sa.assertTrue(false, "Not able to click on no button");
												}
											} else {
												appLog.info("Not able to click on manage email icon");
												sa.assertTrue(false, "Not able to click on manage email icon");
											}
											if (click(driver,
													fp.geManageEmailIconInContactAccessPopup(
															Workspace.FundraisingWorkspace, 60),
													"Mange mail icon in contact access popup",
													action.SCROLLANDBOOLEAN)) {
												if (click(driver,
														fp.geManageEmailPopupCloseIconInContactAccessPopup(
																Workspace.FundraisingWorkspace, 60),
														"Close icon", action.SCROLLANDBOOLEAN)) {
													if (fp.geManageEmailPopupCloseIconInContactAccessPopup(
															Workspace.FundraisingWorkspace, 20) == null) {
														appLog.info("Confirmation popup is closed");
													} else {
														appLog.info("Confirmation popup is not closed");
														sa.assertTrue(false, "Confirmation popup is not closed");
													}
												} else {
													appLog.info("Not able ot click on Close icon");
													sa.assertTrue(false, "Not able to click on Close icon");
												}
											} else {
												appLog.info("Not able to click on manage email icon");
												sa.assertTrue(false, "Not able to click on manage email icon");
											}
											if (click(driver,
													fp.geManageEmailIconInContactAccessPopup(
															Workspace.FundraisingWorkspace, 60),
													"Mange Amil icon in contact access popup",
													action.SCROLLANDBOOLEAN)) {
												if (click(driver,
														fp.geManageEmailPopupYesButtonInContactAccessPopup(
																Workspace.FundraisingWorkspace, 60),
														"Yes Button", action.SCROLLANDBOOLEAN)) {
													if (fp.getManageEmailsHeader(60) != null) {
														appLog.info("Manage Email popup is displaying");
														if (fp.getManageEMailContactGridErrorMsg(30).getText().trim()
																.contains(
																		FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
															appLog.info("No data to display error message is verified");
														} else {
															appLog.error(
																	"No data to display error messsage is not verified");
															sa.assertTrue(false,
																	"No data to display error messsage is not verified");
														}
														List<WebElement> contactAccessViewDropdownValues = allOptionsInDropDrop(
																driver,
																fp.getManageEmailContactAccessViewDropDownList(60),
																"Show dropdown values");
														for (int i = 0; i < contactAccessViewDropdownValues
																.size(); i++) {
															if (contactAccessViewDropdownValues.get(i).getText()
																	.equalsIgnoreCase(sharedPath)) {
																appLog.info(
																		contactAccessViewDropdownValues.get(i).getText()
																				+ " :Opiton is available in the list.");
															} else if (contactAccessViewDropdownValues.get(i).getText()
																	.equalsIgnoreCase("All Folders")) {
																appLog.info(
																		contactAccessViewDropdownValues.get(i).getText()
																				+ " :Opiton is available in the list.");
															} else {
																appLog.info(contactAccessViewDropdownValues.get(i)
																		.getText()
																		+ " :Opiton is not available in the list.");
																sa.assertTrue(false, contactAccessViewDropdownValues
																		.get(i).getText()
																		+ " :Opiton is not available in the list.");
															}
														}
														if (click(driver, fp.getManageEmailsCloseIcon(60), "Close icon",
																action.SCROLLANDBOOLEAN)) {
															appLog.info("Clicked on close icon");
															if (fp.getManageEmailsCloseIcon(10) == null) {
																appLog.info("Manage email popup get closed ");
																ThreadSleep(3000);
																if (fp.getfWR_contactAccessMinusIcon(60) != null) {
																	appLog.info(
																			"Select Contact access grid is collapsed");
																} else {
																	appLog.info(
																			"Select Contact access grid is not collapsed");
																	sa.assertTrue(false,
																			"Select Contact access grid is not collapsed");
																}
																if (fp.getErrorMessageInContactAccessPopup(
																		Workspace.FundraisingWorkspace, 60).getText()
																		.trim().contains(
																				FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
																	appLog.info(
																			"No data to display error message is verified in contact access popup");
																} else {
																	appLog.info(
																			"No data to display error message is not verified in contact access popup");
																	sa.assertTrue(false,
																			"No data to display error message is not verified in contact access popup");
																}
															} else {
																appLog.info(
																		"Manage email popup does not get collapsed");
																sa.assertTrue(false,
																		"Manage email popup does not get collapsed");
															}
														} else {
															appLog.info("Not able to click on close icon successfully");
															sa.assertTrue(false, "Not able to click on close icon ");
														}
													} else {
														appLog.info("Manage email popup is not displaying");
														sa.assertTrue(false, "Manage Email popup is not displying");
													}
												} else {
													appLog.info("Not able to click on yes button");
													sa.assertTrue(false, "Not able to click on yes button");
												}
											} else {
												appLog.info("Not able to click on manage email icon");
												sa.assertTrue(false, "Not able to click on manage email icon");
											}
										} else {
											appLog.error("Not able to click on add select contact button in "
													+ Workspace.FundraisingWorkspace);
											sa.assertTrue(false, "Not able to click on add select contact button in "
													+ Workspace.FundraisingWorkspace);
										}

									} else {
										appLog.error("Not able to click on " + M10Contact2EmailId
												+ " contact check box so cannot invite contact: " + M10Contact2EmailId);
										sa.assertTrue(false, "Not able to click on " + M10Contact2EmailId
												+ " contact check box so cannot invite contact: " + M10Contact2EmailId);
									}
								} else {
									appLog.error("Not able to click on search button");
									sa.assertTrue(false, "Not able to click on search button");
								}
							} else {
								appLog.error("Not able to enter value in textbox");
								sa.assertTrue(false, "Not able to enter value in textbox");
							}
						} else {
							appLog.info("Not able to expand the select contact grid");
							sa.assertTrue(false, "Not able to expand the select contact grid");
						}
						if (click(driver, fp.getCancelBtn(Workspace.FundraisingWorkspace, 60),
								Workspace.FundraisingWorkspace + " cancel button", action.SCROLLANDBOOLEAN)) {
							appLog.info("Able to click on cancel button");
							if (fp.getCancelBtn(Workspace.FundraisingWorkspace, 30) == null) {
								appLog.info("Contact access popup get closed");
							} else {
								appLog.info("Contact access popup does not get closed");
								sa.assertTrue(false, "Contact access popup does not get closed");
							}
						} else {
							appLog.info("Not able to click on cancel button");
							sa.assertTrue(false, "Not able to click on cancel button");
						}
					} else {
						appLog.info("Not able to click on contact access button");
						sa.assertTrue(false, "Not able to click on contact access buttomn");
					}
					switchToDefaultContent(driver);
					if (fp.inviteContact(environment, mode, M10Institution1, M10Contact1EmailId, sharedPath, FolderType.Shared,
							"", "Yes", "No", null, Workspace.FundraisingWorkspace, null)) {
						appLog.info(M10Contact1FirstName + " " + M10Contact1LastName + " is invited successfully");
					} else {
						appLog.info(M10Contact1FirstName + " " + M10Contact1LastName + " is not invited successfully");
						sa.assertTrue(false,
								M10Contact1FirstName + " " + M10Contact1LastName + " is not invited  successfully");
					}
					if (fp.inviteContact(environment, mode, M10Institution1, M10Contact2EmailId, sharedPath, FolderType.Shared,
							"", "Yes", "No", null, Workspace.FundraisingWorkspace, null)) {
						appLog.info(M10Contact2FirstName + " " + M10Contact2LastName + "  is invited successfully");
					} else {
						appLog.info(M10Contact2FirstName + " " + M10Contact2LastName + "is not invited successfully");
						sa.assertTrue(false,
								M10Contact2FirstName + " " + M10Contact2LastName + "is not invited  successfully");
					}
					switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
					scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
							Workspace.FundraisingWorkspace.toString() + " Section view");
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 60), "Contact Access Icon of "+Workspace.InvestorWorkspace, action.SCROLLANDBOOLEAN)) {
						if (click(driver,fp.geManageEmailIconInContactAccessPopup(Workspace.FundraisingWorkspace, 60),"Mange Amil icon in contact access popup",
								action.SCROLLANDBOOLEAN)) {
						if (fp.verifyManageEmailGrid(M10Contact1FirstName + "RUP" + " " + M10Contact1LastName + "RUP",
								M10Contact1EmailId, M10Institution1 + "RUP", "Last Invite Date")) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName
									+ " " + M10Contact1LastName);
						}
						if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,
								M10Contact2EmailId, M10Institution2, null)) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact2FirstName
									+ " " + M10Contact2LastName);
						}
					} else {
						appLog.info("Not able to clcik on manage email");
						sa.assertTrue(false, "Not able to clcik on manage email");
					}
					}else{
						appLog.error("Not able to click on contact access icon");
						sa.assertTrue(false, "Not able to click on contact access icon");
					}
				} else {
					appLog.info("Not able to find folder path");
					sa.assertTrue(false, "Not able to find folder path");
				}
			} else {
				appLog.info("Not able to click on cretaed fund");
				sa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on funds tab");
			sa.assertTrue(false, "Not able ot click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}

	@Test
	public void M10tc012_VerifyInvitationEmailsPopUpWithCustomEmailTemplate() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		WebElement ele = null;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M10FundName1)) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
						"Fundraising Section view");
				if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Fundarising workspace",
						action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getManageEmailCustomRadioButton(60), "Custom template radio button",
							action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getManageEmailCustomTemplateCancelButton(60), "Cancel button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on cancel button");
						} else {
							appLog.info("Not able to click on cancel button");
							sa.assertTrue(false, "Not able to click on cancel button");
						}
						ele = FindElement(driver,
								"//a[text()='" + M10Contact2FirstName + " " + M10Contact2LastName + "']/../..//input",
								"Contact2 checkbox", action.SCROLLANDBOOLEAN, 60);
						if (click(driver, ele, "Contact2 checkbox", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on contact2 checkbox");
							if (click(driver, fp.getmanageEmailsendBtn(60), "Send Button", action.SCROLLANDBOOLEAN)) {
								if (fp.clickUsingCssSelectorPath("div#confirmationpopup_emailID_ME a[title=Yes]", "yes button")) {
								//if (click(driver, fp.getManageEmailSendInvitationConfirmationYesBtn(60),
								//		"Confirmation poup yes button", action.SCROLLANDBOOLEAN)) {
									String alertText = switchToAlertAndGetMessage(driver, 60, action.GETTEXT);
									if (alertText.equalsIgnoreCase(
											FundsPageErrorMessage.MnaageEmailCustomTemplateErrorMessage)) {
										appLog.info("Error message is verified");
									} else {
										appLog.info("Erorr Message is not verified");
										sa.assertTrue(false, "Error Message is not verified");
									}
									switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
									if (!isAlertPresent(driver)) {
										appLog.info("Alert popup get closed ");
									} else {
										appLog.info("Alert popup does not get closed successfully");
										sa.assertTrue(false, "Alert popup does not get closed successfully");
									}
								} else {
									appLog.info("Not able to click on Yes Button");
									sa.assertTrue(false, "Not able to click on yes button");
								}
							} else {
								appLog.info("Not able to click on send button");
								sa.assertTrue(false, "Not bale to click on send button");
							}
						} else {
							appLog.info("Not able to click on contact2 checkbox");
							sa.assertTrue(false, "Not able to click on contact2 checkbox");
						}
						if (click(driver, fp.getManageEmailCustomEmailTemplateEditPreviewTextList().get(0), "Edit ",
								action.SCROLLANDBOOLEAN)) {
							if (sendKeys(driver, fp.getManageEmailCustomSubjectTextBox(60),
									"Custom Invitation Email for PE", "Subject text box", action.SCROLLANDBOOLEAN)) {
								switchToFrame(driver, 60, fp.getManageEmailCustomSubjectBodyFrame(60));
								if (sendKeys(driver, fp.getManageEmailCustomSubjectBody(60),
										"We are happy to inform you that You are invited to Fund. Please Invest in this Fund.",
										"Body text box", action.SCROLLANDBOOLEAN)) {
									switchToDefaultContent(driver);
									switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
									scrollDownThroughWebelement(driver,
											bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
											"Fundraising Section view");
									if (click(driver, fp.getManageEmailCustomApplyButton(60), "Apply button",
											action.SCROLLANDBOOLEAN)) {
										if (click(driver, fp.getmanageEmailsendBtn(60), "Send Button",
												action.SCROLLANDBOOLEAN)) {
											if (fp.getManageEmailSendInvitationConfirmationCountErrorMsg(30).getText()
													.trim().contains("1")) {
												appLog.info(
														"Manage Emails Contact Send Invitation Confirmation Count is  verified");
											} else {
												appLog.info(
														"Manage Emails Contact Send Invitation Confirmation Count is not verified");
												sa.assertTrue(false,
														"Manage Emails Contact Send Invitation Confirmation Count is not verified");
											}
											if (fp.getManageEmailSendInvitationConfirmationErrorMsg(30).getText().trim()
													.contains(
															FundsPageErrorMessage.ManageEmailSendContactInvitationErrorMsg1)) {
												appLog.info(
														"Manage Emails Contact Send Invitation Confirmation Error Message is verified.");
											} else {
												appLog.info(
														"Manage Emails Contact Send Invitation Confirmation Error Message is not verified.");
												sa.assertTrue(false,
														"Manage Emails Contact Send Invitation Confirmation Error Message is not verified.");
											}
											if (fp.getManageEmailSendInvitationConfirmationErrorMsg(30).getText().trim()
													.contains(
															FundsPageErrorMessage.MnaageEmailSendContactInvitationErrorMsg2)) {
												appLog.info(
														"Manage Emails Contact Send Invitation Confirmation Error Message is verified.");
											} else {
												appLog.info(
														"Manage Emails Contact Send Invitation Confirmation Error Message is not verified.");
												sa.assertTrue(false,
														"Manage Emails Contact Send Invitation Confirmation Error Message is not verified.");
											}
											if (click(driver, fp.getManageEmailSendInvitationConfirmationYesBtn(60),
													"Yes button", action.SCROLLANDBOOLEAN)) {
												if (fp.getManageEmailSendInvitationConfirmationYesBtn(10) == null) {
													appLog.info("confirmation popup get closed");
												} else {
													appLog.info("Confirmation popup does not get closed");
													sa.assertTrue(false, "Confirmation popup does not get closed");
												}
												if (fp.verifyManageEmailGrid(
														M10Contact1FirstName + "RUP" + " " + M10Contact1LastName
																+ "RUP",
														M10Contact1EmailId, M10Institution1 + "RUP",
														"Last Invite Date")) {
													appLog.info("Grid Data is verified for the contact: "
															+ M10Contact1FirstName + " " + M10Contact1LastName);
												} else {
													appLog.info("Grid Data is not verified for the contact: "
															+ M10Contact1FirstName + " " + M10Contact1LastName);
													sa.assertTrue(false, "Grid Data is not verified for the contact: "
															+ M10Contact1FirstName + " " + M10Contact1LastName);
												}
												if (fp.verifyManageEmailGrid(
														M10Contact2FirstName + " " + M10Contact2LastName,
														M10Contact2EmailId, M10Institution2, null)) {
													appLog.info("Grid Data is verified for the contact: "
															+ M10Contact2FirstName + " " + M10Contact2LastName);
												} else {
													appLog.info("Grid Data is not verified for the contact: "
															+ M10Contact2FirstName + " " + M10Contact2LastName);
													sa.assertTrue(false, "Grid Data is not verified for the contact: "
															+ M10Contact2FirstName + " " + M10Contact2LastName);
												}
											} else {
												appLog.info("Not able to click on yes button");
												sa.assertTrue(false, "Not ableto click on yes button");
											}
										} else {
											appLog.info("Not able to click on send button");
											sa.assertTrue(false, "Not able to click on send button");
										}
									} else {
										appLog.info("Not able to click on apply button");
										sa.assertTrue(false, "Not able to click on apply button");
									}
								} else {
									appLog.info("Not able to enter value in body text box");
									sa.assertTrue(false, "Not able to enter value in body text box");
								}
							} else {
								appLog.info("Not able to enter value in subject text box");
								sa.assertTrue(false, "Not able to enter value in subject text box");
							}
						} else {
							appLog.info("Not able to click on edit link of custom templte");
							sa.assertTrue(false, "Not able to click on edit link of custom template");
						}
					} else {
						appLog.info("Not able to click on custome template radio button");
						sa.assertTrue(false, "Not able to click on custom template radio button");
					}
					if (click(driver, fp.getManageEmailCancelBtn(60), "Manage email cancel button",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on cancel button");
					} else {
						appLog.info("Not able ot click on cancel button");
						sa.assertTrue(false, "Not able to click on cancel button");
					}
				} else {
					appLog.info("Not able to click on manage emails");
					sa.assertTrue(false, "Not able to click on manage emails");
				}
				if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage email",
						action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getManageEmailCustomRadioButton(60), "Custom template radio button",
							action.SCROLLANDBOOLEAN)) {
						ThreadSleep(2000);
						if (getValueFromElementUsingJavaScript(driver, fp.getManageEmailCustomSubjectTextBox(60),
								"Subject text box").isEmpty()) {
							appLog.info("Subject field is blank");

						} else {
							appLog.info("Subject field is not blank");
							sa.assertTrue(false, "Subject field is not blank");
						}

						switchToFrame(driver, 60, fp.getManageEmailCustomSubjectBodyFrame(60));
						if (getValueFromElementUsingJavaScript(driver, fp.getManageEmailCustomSubjectBody(60),
								"Body text box") == null) {
							appLog.info("Body field is blank");
						} else {
							appLog.info("Body field is not blank");
							sa.assertTrue(false, "Body field is not blank");
						}
						switchToDefaultContent(driver);
						switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
						scrollDownThroughWebelement(driver,
								bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
								"Fundraising Section view");
						if (click(driver, fp.getManageEmailCustomTemplateCancelButton(60), "Cancel button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("clcik on cancel button");
						} else {
							appLog.info("Not able to clcik on cancel button");
							sa.assertTrue(false, "Not able to clcik on cancel button");
						}
					} else {
						appLog.info("Not able to click on custome template radio button");
						sa.assertTrue(false, "Not able to click on custom template radio button");
					}
					ele = FindElement(driver, "//a[text()='" + M10Contact2FirstName + " " + M10Contact2LastName + "']",
							"Contact 2", action.SCROLLANDBOOLEAN, 30);
					if (click(driver, ele, "Contact 2 name", action.SCROLLANDBOOLEAN)) {
						String parentID = switchOnWindow(driver);
						if (parentID != null) {
							scrollDownThroughWebelement(driver, cp.getContactActivityHistory(30), "Activity History");
							if (cp.getContactActivityHistoryErrorMessage(30).getText().trim()
									.contains(ContactPageErrorMessage.activityHistoryErrorMessage)) {
								appLog.info("Activity Alert is not available in Contact page.");
							} else {
								appLog.info("Activity Alert is available in Contact page.");
								sa.assertTrue(false, "Activity Alert is available in Contact page.");
							}
							driver.close();
							driver.switchTo().window(parentID);
						} else {
							appLog.info("No new weindow is open");
							sa.assertTrue(false, "No new window is open");
						}
					} else {
						appLog.info("Not able to click on contact 2 name");
						sa.assertTrue(false, "Not able to click on contact2 name");
					}
					switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
					scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
							"Fundraising Section view");
					ele = FindElement(driver, "//a[text()='" + M10Institution2 + "']", "Contact 2 firm name",
							action.SCROLLANDBOOLEAN, 60);
					if (click(driver, ele, "Contact2 firm name ", action.SCROLLANDBOOLEAN)) {
						String parentID = switchOnWindow(driver);
						if (parentID != null) {
							scrollDownThroughWebelement(driver, ip.getNoteAndAttachmentText(30), "Activity History");
							if (cp.getContactActivityHistoryErrorMessage(30).getText().trim()
									.contains(ContactPageErrorMessage.activityHistoryErrorMessage)) {
								appLog.info("Activity Alert is not available in Contact page.");
							} else {
								appLog.info("Activity Alert is available in Contact page.");
								sa.assertTrue(false, "Activity Alert is available in Contact page.");
							}
							driver.close();
							driver.switchTo().window(parentID);
						} else {
							appLog.info("No new window is open");
							sa.assertTrue(false, "No new window is open");
						}
					} else {
						appLog.info("Not able to click on firm name");
						sa.assertTrue(false, "Not able to click on firm name");
					}
				} else {
					appLog.info("Not able to click on manage emails");
					sa.assertTrue(false, "Not able to click on manage emails");
				}
			} else {
				appLog.info("Not able to click on created fund");
				sa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on funds tab");
			sa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		String investormailcontent = null;
		try {
			investormailcontent = new EmailLib().getInvestorCustomMailContent("InvitationMail", gmailUserName,
					gmailPassword, CRMUser1EmailID, M10Contact2EmailId, "Custom Invitation Email for PE");
		} catch (InterruptedException e) {
			appLog.info("Invitation mail not found.");

			e.printStackTrace();
		}
		System.out.println(investormailcontent);
		if (investormailcontent != null) {
			if (investormailcontent
					.contains("We are happy to inform you that You are invited to Fund. Please Invest in this Fund.")) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}
		}else{
			appLog.info("Mail didn't receive");
			sa.assertTrue(false, "Mail didn't receive");
		}
		sa.assertAll();
	}

	@Test
	public void M10tc013_UpdateFolderNameFundNamePEFirmNameAndVerifyItsImpactInManageEmailsPopUpAndReceivedMail() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			appLog.info("Clicked on NIM Tab");
			switchToFrame(driver, 60, bp.getFrame( PageName.NavatarInvestorManager, 60));
			if (nim.clickOnSideMenusTab(sideMenu.MyFirmProfile)) {
				if (nim.clickOnEditIcon()) {
					if (sendKeys(driver, nim.getMyFirmProfileNameTextBox(60), Org1FirmName + "UP",
							"My firm profile name", action.SCROLLANDBOOLEAN)) {
						if (click(driver, nim.getMyFirmProfilesaveBtn(60), "Save button", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on save button");
						} else {
							appLog.info("Not able to click on save button");
							sa.assertTrue(false, "Not able to click on save button");
						}
					} else {
						appLog.info("Not able to enter value in my firm profile name text box");
						sa.assertTrue(false, "Not able to enter value in my firm profile name text box");
					}
				} else {
					appLog.info("Not able to click on edit icon");
					sa.assertTrue(false, "Not able ot click on eidt icon");
				}
			} else {
				appLog.info("Not able to click on My firm profile tab");
				sa.assertTrue(false, "Not able to click on my firm profile tab");
			}
		} else {
			appLog.info("Not able to click on NIM Tab");
			sa.assertTrue(false, "Not able to click on NIM Tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,
				excelLabel.StandardPath);
		String sharedPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,
				excelLabel.SharedPath);
		String commonPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,
				excelLabel.CommonPath);
		String[] folderPaths = {stdPath, sharedPath, commonPath };
		sa = new SoftAssert();
		String id = null;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M10FundName1)) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
						"Fundraising Section view");
				if (click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 60), "Manage folder icon",
						action.SCROLLANDBOOLEAN)) {
					for (int i = 0; i < 3; i++) {
						id = fp.getCreatedFolderId(folderPaths[i], PageName.ManageFolderPopUp, 20);
						System.err.println("Folder Id is: >>>>>>" + id);
						if (id != null) {
							if (fp.clickOnRenameFolderButton(id)) {
								ThreadSleep(2000);
								if (sendKeys(driver,
										fp.getParentRenameFolderNameTextBox(Workspace.FundraisingWorkspace,
												PageName.ManageFolderPopUp, 20),
										"UP"+folderPaths[i].split(" \\(")[0], commonPath + " folder text box", action.BOOLEAN)) {
									if (click(driver,
											fp.getParentRenameFolderSaveButton(Workspace.FundraisingWorkspace,
													PageName.ManageFolderPopUp, 20),
											"sub folder save button", action.BOOLEAN)) {
										appLog.info("click on save button");
									} else {
										appLog.info("Not able to click on save button");
										sa.assertTrue(false, "Not able to click on save button");
									}
								} else {
									appLog.info("Not able to enter value to rename text box");
									sa.assertTrue(false, "Not able to enter value in rename text box");
								}
							} else {
								appLog.info("Not bale to clcik on rename folder button");
								sa.assertTrue(false, "Not able to click on rename folder button");
							}
						} else {
							appLog.info("folder is not available in manage folder");
							sa.assertTrue(false, "folder is not available in manage folder");
						}
					}
					if (click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 60),
							"Manage Folder Close Button", action.SCROLLANDBOOLEAN)) {
						appLog.info("click on manage folder close button");
					} else {
						appLog.error("Not able to click on manage folder close button");
						sa.assertTrue(false, "Not able to click on manage folder folder close button ");
					}
					if (fp.verifyFolderPathdummy("UP"+stdPath, M10Institution1, null, M10FundName1,
							PageName.FundsPage, Workspace.FundraisingWorkspace, 60)) {
						appLog.info("Updated standard folder is displaying");
					} else {
						appLog.info("Updated folder is not displaying");
						sa.assertTrue(false, "Updtaed folder is not displaying");
					}
					if (fp.verifyFolderPathdummy("UP"+commonPath, null, null, M10FundName1, PageName.FundsPage,
							Workspace.FundraisingWorkspace, 60)) {
						appLog.info("Updated Common folder is displaying");
					} else {
						appLog.info("Updated Common is not displaying");
						sa.assertTrue(false, "Updtaed Common folder is not displaying");
					}
					if (fp.verifyFolderPathdummy("UP"+sharedPath, null, null, M10FundName1, PageName.FundsPage,
							Workspace.FundraisingWorkspace, 60)) {
						appLog.info("Updated Shared folder is displaying");
					} else {
						appLog.info("Updated Shared is not displaying");
						sa.assertTrue(false, "Updtaed Shared folder is not displaying");
					}
				} else {
					appLog.info("Not able to click on manage folder icon");
					sa.assertTrue(false, "Not able to click on manage folder icon");
				}

				if (click(driver, fp.getInvestmentInfo(Workspace.FundraisingWorkspace), "Investment info",
						action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getInvestmentInfoEdit(60), "investment info edit button",
							action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, fp.getInvestmentInfoFundNameTxtbox(60), M10FundName1+"UP","Fund name text box",
								action.BOOLEAN)) {
							if (click(driver, fp.getInvestmentInfoSaveBtn(40), "Investment Info save button",
									action.SCROLLANDBOOLEAN)) {
								if (getText(driver, fp.getFirmName(30), "Fund Name", action.SCROLLANDBOOLEAN)
										.equals(M10FundName1+"UP")) {
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
					if(click(driver, fp.getInvestmentInfoCancelButton(60), "Investment info cancel button", action.SCROLLANDBOOLEAN)){
						appLog.info("Clicked on cancel button");
					}else{
						appLog.info("Not able to click on cancel button");
						sa.assertTrue(false, "Not able to click on cancel button");						
					}
				} else {
					appLog.info("Not able to click on investment info link");
					sa.assertTrue(false, "Not able to click on investment info link");
				}
				switchToDefaultContent(driver);
//		scrollDownThroughWebelement(driver,bp.getFundsTab(60), "Funds Tab");
			if(click(driver, bp.getEditButton(60), "Edit button", action.SCROLLANDBOOLEAN)){
		if(sendKeys(driver, fp.getFundName(60), M10FundName1+"NUP", "Fund name 1", action.SCROLLANDBOOLEAN)){
			if(click(driver, bp.getSaveButton(60), "Save button", action.SCROLLANDBOOLEAN)){
				if(fp.getFundNameInViewMode(60).getText().trim().contains(M10FundName1+"NUP")){
					appLog.info("Fund NAme get updated successfully");
				}else{
					appLog.info("Fund name does not get updated successfully");
					sa.assertTrue(false, "Fund Anme does not get updated successfully");
				}			
			}else{
				appLog.info("Not able to click on save button");
				sa.assertTrue(false, "Not able to click on save buton");
			}			
		}else{
			appLog.info("Not able to enter value in fund name text box");
			sa.assertTrue(false, "Not able to enter value in fund name text box");
		}				
			}	else{
				appLog.info("Not able to click on edit button");
				sa.assertTrue(false, "Not able to click on edit icon");				
			}
			switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
			scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
					"Fundraising Section view");		
			if (fp.verifyFolderPathdummy("UP"+commonPath, null, null, M10FundName1, PageName.FundsPage,
					Workspace.FundraisingWorkspace, 60)) {
				appLog.info("Updated Common folder is displaying");
				if(click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage EMail icon", action.SCROLLANDBOOLEAN)){
					String contactaccessDropdown=getSelectedOptionOfDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60), "Contact access view dropodwn", "text");
					if(contactaccessDropdown.equalsIgnoreCase("All Folders")){
						appLog.info("All folders is selected in the dropdown");
					}else{
						appLog.info("All folder is ot slected in the dropdown");
						sa.assertTrue(false, "All folder is ot slected in the dropdown");
					}
					if (fp.verifyManageEmailGrid(
							M10Contact1FirstName + "RUP" + " " + M10Contact1LastName+ "RUP",M10Contact1EmailId, M10Institution1 + "RUP",
							"Last Invite Date")) {
						appLog.info("Grid Data is verified for the contact: "+ M10Contact1FirstName + " " + M10Contact1LastName);
					} else {
						appLog.info("Grid Data is not verified for the contact: "
								+ M10Contact1FirstName + " " + M10Contact1LastName);
						sa.assertTrue(false, "Grid Data is not verified for the contact: "
								+ M10Contact1FirstName + " " + M10Contact1LastName);
					}
					if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,M10Contact2EmailId, M10Institution2, null)) {
						appLog.info("Grid Data is verified for the contact: "+ M10Contact2FirstName + " " + M10Contact2LastName);
					} else {
						appLog.info("Grid Data is not verified for the contact: "+ M10Contact2FirstName + " " + M10Contact2LastName);
						sa.assertTrue(false, "Grid Data is not verified for the contact: "+ M10Contact2FirstName + " " + M10Contact2LastName);
					}					
					if(click(driver, fp.getManageEmailCancelBtn(60), "Manage mail cancel button", action.SCROLLANDBOOLEAN)){
						appLog.info("Clicked on cancel button");
					}else{
						appLog.info("Not able to click on cancel button");
						sa.assertTrue(false, "Not able toclick on cancel button");
					}
				}else{
					appLog.info("Not able to click on manage email icon");
					sa.assertTrue(false, "Not able to click on manage email icon");
				}
			} else {
				appLog.info("Updated Common is not displaying");
				sa.assertTrue(false, "Updtaed Common folder is not displaying");
			}		
			if (fp.verifyFolderPathdummy("UP"+sharedPath, null, null, M10FundName1, PageName.FundsPage,
					Workspace.FundraisingWorkspace, 60)) {
				appLog.info("Updated shared folder is displaying");
				if(click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage EMail icon", action.SCROLLANDBOOLEAN)){
					String contactaccessDropdown=getSelectedOptionOfDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60), "Contact access view dropodwn", "text");
					if(contactaccessDropdown.equalsIgnoreCase("UP"+sharedPath)){
						appLog.info("UP"+sharedPath+"  is selected in the dropdown");
					}else{
						appLog.info("UP"+sharedPath+"  is not slected in the dropdown");
						sa.assertTrue(false, "UP"+sharedPath+"  is not slected in the dropdown");
					}
					if (fp.verifyManageEmailGrid(
							M10Contact1FirstName + "RUP" + " " + M10Contact1LastName+ "RUP",M10Contact1EmailId, M10Institution1 + "RUP",
							"Last Invite Date")) {
						appLog.info("Grid Data is verified for the contact: "+ M10Contact1FirstName + " " + M10Contact1LastName);
					} else {
						appLog.info("Grid Data is not verified for the contact: "
								+ M10Contact1FirstName + " " + M10Contact1LastName);
						sa.assertTrue(false, "Grid Data is not verified for the contact: "
								+ M10Contact1FirstName + " " + M10Contact1LastName);
					}
					if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,M10Contact2EmailId, M10Institution2, null)) {
						appLog.info("Grid Data is verified for the contact: "+ M10Contact2FirstName + " " + M10Contact2LastName);
					} else {
						appLog.info("Grid Data is not verified for the contact: "+ M10Contact2FirstName + " " + M10Contact2LastName);
						sa.assertTrue(false, "Grid Data is not verified for the contact: "+ M10Contact2FirstName + " " + M10Contact2LastName);
					}					
					if(click(driver, fp.getManageEmailCancelBtn(60), "Manage mail cancel button", action.SCROLLANDBOOLEAN)){
						appLog.info("Clicked on cancel button");
					}else{
						appLog.info("Not able to click on cancel button");
						sa.assertTrue(false, "Not able toclick on cancel button");
					}
				}else{
					appLog.info("Not able to click on manage email icon");
					sa.assertTrue(false, "Not able to click on manage email icon");
				}
			} else {
				appLog.info("Updated shared is not displaying");
				sa.assertTrue(false, "Updtaed shared folder is not displaying");
			}		
			if (fp.verifyFolderPathdummy("UP"+stdPath, M10Institution1, null, M10FundName1, PageName.FundsPage,
					Workspace.FundraisingWorkspace, 60)) {
				appLog.info("Updated shared folder is displaying");
				if(click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage EMail icon", action.SCROLLANDBOOLEAN)){
					String contactaccessDropdown=getSelectedOptionOfDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60), "Contact access view dropodwn", "text");
					if(contactaccessDropdown.equalsIgnoreCase("UP"+stdPath)){
						appLog.info("UP"+stdPath+"  is selected in the dropdown");
					}else{
						appLog.info("UP"+stdPath+"  is not slected in the dropdown");
						sa.assertTrue(false, "UP"+stdPath+"  is not slected in the dropdown");
					}
					if (fp.verifyManageEmailGrid(
							M10Contact1FirstName + "RUP" + " " + M10Contact1LastName+ "RUP",M10Contact1EmailId, M10Institution1 + "RUP",
							"Last Invite Date")) {
						appLog.info("Grid Data is verified for the contact: "+ M10Contact1FirstName + " " + M10Contact1LastName);
					} else {
						appLog.info("Grid Data is not verified for the contact: "
								+ M10Contact1FirstName + " " + M10Contact1LastName);
						sa.assertTrue(false, "Grid Data is not verified for the contact: "
								+ M10Contact1FirstName + " " + M10Contact1LastName);
					}
				if(click(driver, fp.getManageEmailInvitationEmailTemplateEditPreviewTextList().get(0), "Edit link", action.SCROLLANDBOOLEAN)){
					if (fp.getManageEmailEditFormEmailIdtext(60).getText().trim().contains(CRMUser1EmailID)) {
						appLog.info("Manage Email Invitation Email Edit Form Email Id is verified.");
					} else {
						appLog.info("Manage Email Invitation Email Edit Form Email Id is not verified.");
						sa.assertTrue(false, "Manage Email Invitation Email Edit Form Email Id is not verified.");
					}
					if (fp.getManageEmailEditSubjectText(60).getText().trim().contains("Invitation from")) {
						appLog.info("Manage Email Invitation Email Edit Subjetc Text is verified.");
					} else {
						appLog.info("Manage Email Invitation Email Edit Subjetc Text is not verified.");
						sa.assertTrue(false, "Manage Email Invitation Email Edit Subjetc Text is not verified.");
					}
					if (fp.getManageEmailEditSubjectText(60).getText().trim().contains(Org1FirmName+"UP")) {
						appLog.info("Manage Email Invitation Email Edit Subjetc Text is  verified.");
					} else {
						appLog.info("Manage Email Invitation Email Edit Subjetc Text is not verified.");
						sa.assertTrue(false, "Manage Email Invitation Email Edit Subjetc Text is not verified.");
					}
					if (fp.getManageEmailEditHelloText(60).getText().trim().contains("Hello")) {
						appLog.info("Manage Email Invitation Email Edit Hello Text is verified.");
					} else {
						appLog.info("Manage Email Invitation Email Edit Hello Text is not verified.");
						sa.assertTrue(false, "Manage Email Invitation Email Edit Hello Text is not verified.");
					}
					if (fp.getManageEmailEditHelloText(60).getText().trim().contains("Recipient first Name},")) {
						appLog.info("Manage Email Invitation Email Edit Recipient First Name Text is verified.");
					} else {
						appLog.info(
								"Manage Email Invitation Email Edit Recipient First Name Text is not verified.");
						sa.assertTrue(false,
								"Manage Email Invitation Email Edit Recipient First Name Text is not verified.");
					}
					if (fp.getGetManageEmailEditGrantAccessText(60).getText().trim().contains(
							"You have been granted access to Potential investments of " + M10FundName1+"UP" + " by")) {
						appLog.info("Manage Email Invitation Email Edit Grant Access Text is verified.");
					} else {
						appLog.info("Manage Email Invitation Email Edit Grant Access Text is not verified.");
						sa.assertTrue(false,
								"Manage Email Invitation Email Edit Grant Access Text is not verified.");
					}
					if (fp.getGetManageEmailEditGrantAccessText(60).getText().trim().contains(Org1FirmName+"UP")) {
						appLog.info("Manage Email Invitation Email Edit Grant Access Text is verified.");
					} else {
						appLog.info("Manage Email Invitation Email Edit Grant Access Text is not verified.");
						sa.assertTrue(false,
								"Manage Email Invitation Email Edit Grant Access Text is not verified.");
					}
					if (fp.getManageEmailEditRegisterNotRegisterText(60).getText().trim()
							.contains("If you have not yet registered, Click here to register.")) {
						appLog.info(
								"Manage Email Invitation Email Edit If you have not yet registered Text is verified.");
					} else {
						appLog.info(
								"Manage Email Invitation Email Edit If you have not yet registered Text is not verified.");
						sa.assertTrue(false,
								"Manage Email Invitation Email Edit If you have not yet registered Text is not verified.");
					}
					if (fp.getManageEmailEditRegisterNotRegisterText(60).getText().trim()
							.contains("If you have already registered, Click here to login")) {
						appLog.info(
								"Manage Email Invitation Email Edit If you have already registered Text is verified.");
					} else {
						appLog.info(
								"Manage Email Invitation Email Edit If you have already registered Text is not verified.");
						sa.assertTrue(false,
								"Manage Email Invitation Email Edit If you have already registered Text is not verified.");
					}
					if (fp.getManageEmailEditBottomText(60).getText().trim().contains(
							"If you believe this has been sent in error, or if you cannot login, please contact")) {
						appLog.info("Manage Email Invitation Email Edit Bottom Text is verified.");
					} else {
						appLog.info("Manage Email Invitation Email Edit Bottom Text is not verified.");
						sa.assertTrue(false, "Manage Email Invitation Email Edit Bottom Text is not verified.");
					}
					if (fp.getManageEmailEditBottomText(60).getText().trim().contains(Org1FirmName+"UP")) {
						appLog.info("Manage Email Invitation Email Edit Bottom Text is verified.");
					} else {
						appLog.info("Manage Email Invitation Email Edit Bottom Text is not verified.");
						sa.assertTrue(false, "Manage Email Invitation Email Edit Bottom Text is not verified.");
					}
				if (click(driver, fp.getManageEmailInvitationTextBoxCancelButton(30), "Cancel button",
							action.SCROLLANDBOOLEAN)) {
					}else{
					appLog.info("Not able to click on cancel button");
					sa.assertTrue(false,"Not able to click on cancel button");
				}				
				}else{
					appLog.info("Not able to clcik on edit link");
					sa.assertTrue(false, "Not able to click on edit link");
				}
				
	WebElement	ele=FindElement(driver,"//a[text()='"+M10Contact1FirstName+"RUP"+" "+M10Contact1LastName+"RUP"+"']/../..//input", "Contact1 checkbox", action.SCROLLANDBOOLEAN, 60);
		if(click(driver, ele, "Contact1 checkbox", action.SCROLLANDBOOLEAN)){
			if (click(driver, fp.getmanageEmailsendBtn(60), "Send Button",
					action.SCROLLANDBOOLEAN)) {
				if (click(driver, fp.getManageEmailSendInvitationConfirmationYesBtn(60),
						"Yes button", action.SCROLLANDBOOLEAN)) {
							
				}else{
					appLog.info("Not able to clcik on yes button");
					sa.assertTrue(false, "Not able to click on yes button");
				}
			}else{
				appLog.info("Not able to click on manage email send button ");
				sa.assertTrue(false, "Not able to click on send button");
			}
		}	else{
			appLog.info("Not able to click on contact 1 checkbox");
			sa.assertTrue(false, "Not able to click on contact1 checkbox");
		}			
				}else{
					appLog.info("Not able to click on manage email icon");
					sa.assertTrue(false, "Not able to click on manage email icon");
				}				
			} else {
				appLog.info("Updated standard is not displaying");
				sa.assertTrue(false, "Updtaed standard folder is not displaying");
			}	
			} else {
				appLog.info("Not able to click on created fund");
				sa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able ot click on funds tab");
			sa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		String investormailcontent = null;
		ThreadSleep(2000);
		try {
			investormailcontent = new EmailLib().getInvestorMailContent("invitationMail", gmailUserName, gmailPassword,
					CRMUser1EmailID, M10Contact1EmailId);
		} catch (InterruptedException e) {
			appLog.info("Invitation mail not found.");

			e.printStackTrace();
		}
		ThreadSleep(2000);
		if (investormailcontent != null) {
			if (investormailcontent.contains("You have been granted access to Potential investments of "+M10FundName1+"UP"+" by "+Org1FirmName+"UP"+".")) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}
			if (investormailcontent.contains("If you have not yet registered")) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}
			if (investormailcontent.contains("If you have already registered")) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}
			if (investormailcontent.contains("Click here") && investormailcontent.contains("to register.")) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}
			if (investormailcontent.contains("Click here") && investormailcontent.contains("to login")) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}
			if (investormailcontent
					.contains("If you believe this has been sent in error, or if you cannot login, please contact "
							+ Org1FirmName+"UP"+ ".")) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}

			if (investormailcontent.contains("Hello "+M10Contact1FirstName)) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}
		}else{
			appLog.info("Mail didn't receive");
			sa.assertTrue(false, "Mail disn't receive");
		}
		sa.assertAll();		
		}
	
	@Test
	public void M10tc014_CheckRenamingOfContactAndAccountNameImpactOnManageEmail(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip=new InstitutionPageBusinessLayer(driver);
		FundsPageBusinessLayer fp=new FundsPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.ContactTab)) {
			appLog.info("Clicked on COntact Tab");
		if(cp.clickOnCreatedContact(M10Contact2FirstName, M10Contact2LastName, null)){
			if(click(driver, bp.getEditButton(60), "Edit button", action.SCROLLANDBOOLEAN)){
				if(sendKeys(driver, cp.getContactFirstName(60), M10Contact2FirstName+"NUP", "Contact 2 first name", action.SCROLLANDBOOLEAN)){
				if(sendKeys(driver, cp.getContactLastName(60), M10Contact2LastName+"NUP", "COntact2 last name", action.SCROLLANDBOOLEAN)){
					if(click(driver, bp.getSaveButton(60), "Save button", action.SCROLLANDBOOLEAN)){
						if(cp.getContactFullNameInViewMode(60).getText().trim().contains(M10Contact2FirstName+"NUP"+" "+M10Contact2LastName+"NUP")){
							appLog.info("Contact details updtaed successfully");
						}else{
							appLog.info("Contact details does not updtae successfully");
							sa.assertTrue(false, "Contact details does not updtae successfully");
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
				appLog.info("Not able to lcik on edit button");
				sa.assertTrue(false, "Not able to click on edit button");
			}
			
		}else{
			appLog.info("Not able to click on created contact");
			sa.assertTrue(false, "Not able to click on created contact");
		}
		}else{
			appLog.info("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}
		
		if(bp.clickOnTab(TabName.InstituitonsTab)){
			if(ip.clickOnCreatedInstitution(M10Institution2)){
				if(click(driver, bp.getEditButton(60), "Edit button", action.SCROLLANDBOOLEAN)){
					if(sendKeys(driver, ip.getLegalNameTextBox(60), M10Institution2+"NUP", "Institution name", action.SCROLLANDBOOLEAN)){
						if(click(driver, bp.getSaveButton(60), "Save buton", action.SCROLLANDBOOLEAN)){
							if(ip.getLegalNameLabelTextbox(60).getText().trim().contains(M10Institution2+"NUP")){
								appLog.info("Institution details get updtaed successfully");
							}else{
								appLog.info("Institution fdetails does not updated successfully");
								sa.assertTrue(false, "Institution details does not updtaed successfully");
							}							
						}else{
							appLog.info("Not able to click on save button");
							sa.assertTrue(false, "Not able to click on save button");
						}						
					}else{
						appLog.info("Not able to enter value in legal name text box");
						sa.assertTrue(false, "Not able to enter value in legal name text bo");
					}				
				}else{
					appLog.info("Not able to click on edit button");
					sa.assertTrue(false, "Not able to click on edit button");
				}			
			}else{
				appLog.info("Not able to click on craeted institution");
				sa.assertTrue(false, "Not able to click on created institution");
			}		
		}else{
			appLog.info("Not able to click on institutions tab");
			sa.assertTrue(false, "Not able o click on institutions tab");
		}
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M10FundName1+"NUP")) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
						"Fundraising Section view");
				if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage Email icon",
						action.SCROLLANDBOOLEAN)) {
					if (fp.verifyManageEmailGrid(
							M10Contact1FirstName + "RUP" + " " + M10Contact1LastName+ "RUP",M10Contact1EmailId, M10Institution1 + "RUP",
							"Last Invite Date")) {
						appLog.info("Grid Data is verified for the contact: "+ M10Contact1FirstName + " " + M10Contact1LastName);
					} else {
						appLog.info("Grid Data is not verified for the contact: "
								+ M10Contact1FirstName + " " + M10Contact1LastName);
						sa.assertTrue(false, "Grid Data is not verified for the contact: "
								+ M10Contact1FirstName + " " + M10Contact1LastName);
					}
					if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,M10Contact2EmailId, M10Institution2, null)) {
						appLog.info("Grid Data is verified for the contact: "+ M10Contact2FirstName + " " + M10Contact2LastName);
					} else {
						appLog.info("Grid Data is not verified for the contact: "+ M10Contact2FirstName + " " + M10Contact2LastName);
						sa.assertTrue(false, "Grid Data is not verified for the contact: "+ M10Contact2FirstName + " " + M10Contact2LastName);
					}					
					if(click(driver, fp.getManageEmailCancelBtn(60), "Manage mail cancel button", action.SCROLLANDBOOLEAN)){
						appLog.info("Clicked on cancel button");
					}else{
						appLog.info("Not able to click on cancel button");
						sa.assertTrue(false, "Not able toclick on cancel button");
					}				
				}else{
					appLog.info("Not bale to click on manage email icon");
					sa.assertTrue(false, "Not able to click on manage email");
				}
			}else{
				appLog.info("Not able to click on created fund");
				sa.assertTrue(false, "Not able to click on craeted fund");
			}
		}else{
			appLog.info("Not able to click on funds tab");
			sa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		if (bp.clickOnTab(TabName.ContactTab)) {
			appLog.info("Clicked on COntact Tab");
			String ContactEMailID ="eal.moralas+"+bp.generateRandomNumber()+"@gmail.com";
			ExcelUtils.writeData(ContactEMailID, "Contacts", excelLabel.Variable_Name, "M10Contact2", excelLabel.ContactUpdatedEmailID);
		if(cp.clickOnCreatedContact(M10Contact2FirstName+"NUP", M10Contact2LastName+"NUP", null)){
		if(click(driver, bp.getEditButton(60), "Edit button", action.SCROLLANDBOOLEAN)){
		if(sendKeys(driver, cp.getEmailId(60), ContactEMailID, "Contact2 email id", action.SCROLLANDBOOLEAN)){
		if(click(driver, bp.getSaveButton(60), "Save button", action.SCROLLANDBOOLEAN)){
			appLog.info("click on save button");
			if(cp.getEmailIdViewMode(60).getText().trim().contains(ContactEMailID)){
				appLog.info("Email id get changed successfully");
			}else{
				appLog.info("Email id does not get changed successfully");
				sa.assertTrue(false, "Email id does not get changed successfully");
			}	
		}else{
			appLog.info("Not able to click on save button");
			sa.assertTrue(false, "Not able to click on save button");
		}			
		}else{
			appLog.info("Not able to enter value in e,mail text box");
			sa.assertTrue(false, "Not able to enter value in email tetx box");
		}		
		}else{
			appLog.info("Not able ot click on edit button");
			sa.assertTrue(false, "Not able to click on edit button");
		}		
		}else{
			appLog.info("Not able to click on created contact");
			sa.assertTrue(false, "Not able to click on created contact");
		}
		}else{
			appLog.info("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}
		if (bp.clickOnTab(TabName.FundsTab)){
			if (fp.clickOnCreatedFund(M10FundName1+"NUP")) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
						"Fundraising Section view");
				if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage Email icon",
						action.SCROLLANDBOOLEAN)) {
					//if (fp.clickUsingCssSelectorPath("a:contains(^'"+M10Contact2FirstName+" "+M10Contact2LastName+"']$)", "Contact2 name")) {
					WebElement	ele=FindElement(driver,"//a[text()='"+M10Contact2FirstName+" "+M10Contact2LastName+"']", "Contact2 name", action.SCROLLANDBOOLEAN, 60);
					if (clickUsingJavaScript(driver, ele, "Contact 2")) {
					//if(click(driver, ele, "Contact 2", action.SCROLLANDBOOLEAN)){
				String errorMessage=switchToAlertAndGetMessage(driver, 60, action.GETTEXT);   
				if(errorMessage.contains(ContactPageErrorMessage.ContactemailChangedErrorMessage)){
					appLog.info("Error Message is verified");
				}else{
					appLog.info("Error Message is not verified");
					sa.assertTrue(false, "Error Message is not verified");
				}
				switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);		
				}else{
						appLog.info("Not able to click on contact2 name");
						sa.assertTrue(false, "Not able to click on contact2 name ");
				}
				//if (fp.clickUsingCssSelectorPath("a:contains(^'"+M10Institution2+"']$)", "Contact2 firm name")) {
					ele=FindElement(driver,"//a[text()='"+M10Institution2+"']", "Contact2 firm name", action.SCROLLANDBOOLEAN, 60);
				if (clickUsingJavaScript(driver, ele, "Contact 2 firm name")) {
					//if(click(driver, ele, "Contact 2 firm name", action.SCROLLANDBOOLEAN)){
				String errorMessage=switchToAlertAndGetMessage(driver, 60, action.GETTEXT);   
				if(errorMessage.contains(ContactPageErrorMessage.ContactemailChangedErrorMessage)){
					appLog.info("Error Message is verified");
				}else{
					appLog.info("Error Message is not verified");
					sa.assertTrue(false, "Error Message is not verified");
				}
				switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);		
				}else{
						appLog.info("Not able to click on contact2 Firm Name");
						sa.assertTrue(false, "Not able to click on contact2 Firm Name ");
				}					
				}else{
					appLog.info("Not bale to click on manage email icon");
					sa.assertTrue(false, "Not able to click on manage email");
				}
			}else{
				appLog.info("Not able to click on created fund");
				sa.assertTrue(false, "Not able to click on craeted fund");
			}
		}else{
			appLog.info("Not able to click on funds tab");
			sa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		if (bp.clickOnTab(TabName.ContactTab)) {
			appLog.info("Clicked on COntact Tab");
			if(cp.clickOnCreatedContact(M10Contact1FirstName, M10Contact1LastName, null)){
				if (fp.clickUsingCssSelectorPath("input[value=Delete]", "delete button")) {
		//		if(click(driver, cp.getDeleteButtonContactsPage(60), "Delete button	", action.SCROLLANDBOOLEAN)){
		switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
		if(cp.verifyDeletedContact(M10Contact1FirstName+" "+M10Contact1LastName)){
			appLog.info("Contact get deleted successfully");
		}else{
			appLog.info("Contact does not get delete successfully");
			sa.assertTrue(false, "Contact does not get deleted successfulluy");
		}
		}else{
		appLog.info("Not able to click on delete button");
		sa.assertTrue(false, "Not able to click on delete bvutton");
	}
			}else{
				appLog.info("Not able to click on created ocntact");
				sa.assertTrue(false, "Not able to click on created contact");
			}
		}else{
			appLog.info("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M10FundName1+"NUP")) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
						"Fundraising Section view");
				if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage Email icon",
						action.SCROLLANDBOOLEAN)) {
					//if (fp.clickUsingCssSelectorPath("a:contains(^'"+M10Contact1FirstName+"RUP"+" "+M10Contact1LastName+"RUP"+"']$)", "Contact1 name")) {
						
					WebElement	ele=FindElement(driver,"//a[text()='"+M10Contact1FirstName+"RUP"+" "+M10Contact1LastName+"RUP"+"']", "Contact1 name", action.SCROLLANDBOOLEAN, 60);
					if (clickUsingJavaScript(driver, ele,  "Contact 1")) {
					//if(click(driver, ele, "Contact 1", action.SCROLLANDBOOLEAN)){
						if(isAlertPresent(driver)){
							String errorMessage = switchToAlertAndGetMessage(driver, 60, action.GETTEXT);
							if (errorMessage.contains(ContactPageErrorMessage.ContactemailChangedErrorMessage)) {
								appLog.info("Error Message is verified");
							} else {
								appLog.info("Error Message is not verified");
								sa.assertTrue(false, "Error Message is not verified");
							}
							switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
						} else {
							appLog.error("No Alert is Present for contact1");
							sa.assertTrue(false, "No Alert is Present for contact1");
						}
					} else {
						appLog.info("Not able to click on contact1 name");
						sa.assertTrue(false, "Not able to click on contact1 name ");
					}
					//if (fp.clickUsingCssSelectorPath("a:contains(^'"+M10Institution1+"RUP"+"']$)", "Contact1 firm name")) {
						
					ele=FindElement(driver,"//a[text()='"+M10Institution1+"RUP"+"']", "Contact1 firm name", action.SCROLLANDBOOLEAN, 60);
					if (clickUsingJavaScript(driver, ele, "Contact1 firm name")) {
					//if(click(driver, ele, "Contact 1 firm name", action.SCROLLANDBOOLEAN)){
				String errorMessage=switchToAlertAndGetMessage(driver, 60, action.GETTEXT);   
				if(errorMessage.contains(ContactPageErrorMessage.ContactemailChangedErrorMessage)){
					appLog.info("Error Message is verified");
				}else{
					appLog.info("Error Message is not verified");
					sa.assertTrue(false, "Error Message is not verified");
				}
				switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);		
				}else{
						appLog.info("Not able to click on contact1 Firm Name");
						sa.assertTrue(false, "Not able to click on contact1 Firm Name ");
				}					
				}else{
					appLog.info("Not bale to click on manage email icon");
					sa.assertTrue(false, "Not able to click on manage email");
				}
			}else{
				appLog.info("Not able to click on created fund");
				sa.assertTrue(false, "Not able to click on craeted fund");
			}
		}else{
			appLog.info("Not able to click on funds tab");
			sa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		if(bp.clickOnTab(TabName.HomeTab)){
		if(new HomePageBusineesLayer(driver).restoreValuesFromRecycleBin(M10Contact1FirstName+" "+M10Contact1LastName)){
			appLog.info("Successfully restore value from recycle bin");			
		}else{
			appLog.info("Not able to restore value from recycle bin");
			sa.assertTrue(false, "Not able to restore value from recycle bin");
		}
		}else{
			appLog.info("Not able to click on home tab");
			sa.assertTrue(false, "Not able to click on home tab");
		}
		if (bp.clickOnTab(TabName.ContactTab)) {
			appLog.info("Clicked on COntact Tab");
		if(cp.clickOnCreatedContact(M10Contact2FirstName+"NUP", M10Contact2LastName+"NUP", null)){
		if(click(driver, bp.getEditButton(60), "Edit button", action.SCROLLANDBOOLEAN)){
		if(sendKeys(driver, cp.getEmailId(60), M10Contact2EmailId, "Contact2 email id", action.SCROLLANDBOOLEAN)){
		if(click(driver, bp.getSaveButton(60), "Save button", action.SCROLLANDBOOLEAN)){
			appLog.info("click on save button");
			if(cp.getEmailIdViewMode(60).getText().trim().contains(M10Contact2EmailId)){
				appLog.info("Email id get changed successfully");
			}else{
				appLog.info("Email id does not get changed successfully");
				sa.assertTrue(false, "Email id does not get changed successfully");
			}	
		}else{
			appLog.info("Not able to click on save button");
			sa.assertTrue(false, "Not able to click on save button");
		}			
		}else{
			appLog.info("Not able to enter value in e,mail text box");
			sa.assertTrue(false, "Not able to enter value in email tetx box");
		}		
		}else{
			appLog.info("Not able ot click on edit button");
			sa.assertTrue(false, "Not able to click on edit button");
		}		
		}else{
			appLog.info("Not able to click on created contact");
			sa.assertTrue(false, "Not able to click on created contact");
		}
		}else{
			appLog.info("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M10FundName1+"NUP")) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
						"Fundraising Section view");
				if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage Email icon",
						action.SCROLLANDBOOLEAN)) {
					WebElement	ele=FindElement(driver,"//a[text()='"+M10Contact1FirstName+"RUP"+" "+M10Contact1LastName+"RUP"+"']", "Contact1 name", action.SCROLLANDBOOLEAN, 60);
					if(click(driver, ele, "Contact 1", action.SCROLLANDBOOLEAN)){
						String parentID=switchOnWindow(driver);
						if(parentID!=null){
							ele = isDisplayed(driver,
									FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40),
									"visibility", 60, "Contact tab");
							if (ele != null) {
								String header = ele.getText().trim();
								if (header.contains(M10Contact1FirstName+" " +M10Contact1LastName)) {
									appLog.info("Contact 1 page open successfully");
								} else {
									appLog.info("Contact 1 page is not open successfully");
									sa.assertTrue(false, "Contact 1 page is not open successfully");
								}
							} else {
								appLog.info("Not able to find header of contact tab");
								sa.assertTrue(false, "Not able to find header of contact tab");
							}
							driver.close();
							driver.switchTo().window(parentID);						
						}else{
							appLog.info("No new window is open");
							sa.assertTrue(false, "No new window is open");
						}						
					}else{
						appLog.info("Not able to click on contact 1 name");
						sa.assertTrue(false, "Not able to click on contact 1 name");
					}
				}else{
					appLog.info("Not able to click on manage email icon");
					sa.assertTrue(false, "Not able to click on manage email");
				}
			}else{
				appLog.info("Not able to click on created fund");
				sa.assertTrue(false, "Not able to click on craeted fund");
			}
		}else{
			appLog.info("Not able to click on funds tab");
			sa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();	
	}
	
	@Test
	public void M10tc015_CreateFundraisingWorkspaceForFund2InviteAlreadyInvitedContactAndVerifyManageEmailsPopUp(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp=new FundsPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		String[] step1of3 = {ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M10Fund2", excelLabel.Fund_Size), ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M10Fund2", excelLabel.Fund_VintageYear),
				ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M10Fund2", excelLabel.Fund_Contact), ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M10Fund2", excelLabel.Fund_Phone),
				ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M10Fund2", excelLabel.Fund_Email), ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M10Fund2", excelLabel.Fund_Description) };
		String[] stdPath = ExcelUtils
				.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath)
				.split(",");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			appLog.info("Clicked on COntact Tab");
		if(fp.clickOnCreatedFund(M10FundName2)){
			if (fp.buildWorkspace(step1of3, WorkSpaceAction.IMPORTFOLDERTEMPLATE, folderTemplateName, null,
					M10Institution1 , Workspace.FundraisingWorkspace, 60)) {
				appLog.info("Fundraising Workspace is created successfully");
			} else {
				appLog.info("Fundraising workspace is not created successfully");
				sa.assertTrue(false, " Fundraising workspace is not craeted successfully");
			}
			if (fp.inviteContact(environment, mode, M10Institution1, M10Contact1EmailId, stdPath[0], FolderType.Standard,
					"Upload", "Yes", "No", null, Workspace.FundraisingWorkspace, null)) {
				appLog.info("Successfully provided access to contact '" + M10Contact1FirstName+"RUP" + " "
						+ M10Contact1LastName+"RUP" + "'.");
			} else {
				appLog.error("Not able to provide access to contact '" + M10Contact1FirstName+"RUP" + " "
						+ M10Contact1LastName+"RUP" + "'.");
				sa.assertTrue(false, "Not able to provide access to contact '" + M10Contact1FirstName+"RUP" + " "
						+ M10Contact1LastName+"RUP" + "'.");
			}	
			switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
			scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), Workspace.FundraisingWorkspace.toString()+" Section view");	
			if(click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Mange emails ", action.SCROLLANDBOOLEAN)){
				if (fp.verifyManageEmailGrid(
						M10Contact1FirstName + "RUP" + " " + M10Contact1LastName+ "RUP",M10Contact1EmailId, M10Institution1 + "RUP",
						null)) {
					appLog.info("Grid Data is verified for the contact: "+ M10Contact1FirstName + " " + M10Contact1LastName);
				} else {
					appLog.info("Grid Data is not verified for the contact: "
							+ M10Contact1FirstName + " " + M10Contact1LastName);
					sa.assertTrue(false, "Grid Data is not verified for the contact: "
							+ M10Contact1FirstName + " " + M10Contact1LastName);
				}
							
				if(click(driver, fp.getManageEmailCancelBtn(60), "Manage mail cancel button", action.SCROLLANDBOOLEAN)){
					appLog.info("Clicked on cancel button");
				}else{
					appLog.info("Not able to click on cancel button");
					sa.assertTrue(false, "Not able toclick on cancel button");
				}					
			}else{
				appLog.info("Not able to click on manage emails icon");
				sa.assertTrue(false, "Not able to click on manage emails icon");
			}				
		}else{
			appLog.info("Not able to click on created fund");
			sa.assertTrue(false, "Not able to click on craeted fund");
		}
		}else{
			appLog.info("Not able to click on funds tab");
			sa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();	
	}
		
	@Test
	public void M10tc016_CheckVariousErrorMessageOnManageEmails(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M10FundName1+"NUP")) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
				System.err.println("Switched to frame.");
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
						"Investor Workspace View.");
				if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage emails icon",
						action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getManageEmailsCloseIcon(60), "Close icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on close icon");
						if (fp.getManageEmailsHeader(10) == null) {
							appLog.info("Manage email popup get closed");
						} else {
							appLog.info("Manage email popup does not get closed");
							sa.assertTrue(false, "manage email popup does not get closed");
						}
					} else {
						appLog.info("Not able to click on close icon");
						sa.assertTrue(false, "Not able to click on close icon");
					}
				} else {
					appLog.info("Not able to click on manage emails");
					sa.assertTrue(false, "Not able to click on manage emails");
				}
				if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage emails icon",
						action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getManageEmailCancelBtn(60), "Cancel button", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Cancel button");
						if (fp.getManageEmailsHeader(10) == null) {
							appLog.info("Manage email popup get closed");
						} else {
							appLog.info("Manage email popup does not get closed");
							sa.assertTrue(false, "manage email popup does not get closed");
						}
					} else {
						appLog.info("Not able to click on Cancel button");
						sa.assertTrue(false, "Not able to click on Cancel button");
					}
				} else {
					appLog.info("Not able to click on manage emails");
					sa.assertTrue(false, "Not able to click on manage emails");
				}
				if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage emails icon",
						action.SCROLLANDBOOLEAN)) {
					String contactaccessDropdownOption = getSelectedOptionOfDropDown(driver,
							fp.getManageEmailContactAccessViewDropDownList(60), "Contact access view dropodown",
							"text");
					if (contactaccessDropdownOption.equalsIgnoreCase("All Folders")) {
						appLog.info("All folder option is displaying in the contact access view dropdown ");
					} else {
						appLog.info("All folder option is not displaying in contact access view dropdown");
						sa.assertTrue(false, "All folder option is not displaying in contact access view dropdown");
					}
					if (click(driver, fp.getManageEmailCancelBtn(60), "Cancel button", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Cancel button");
					} else {
						appLog.info("Not able to click on Cancel button");
						sa.assertTrue(false, "Not able to click on Cancel button");
					}
				} else {
					appLog.info("Not able to click on manage emails");
					sa.assertTrue(false, "Not able to click on manage emails");
				}
				if (fp.verifyFolderPathdummy("", M10Institution1, null, M10FundName1+"UP", PageName.FundsPage,
						Workspace.InvestorWorkspace, 60)) {
					if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage emails icon",
							action.SCROLLANDBOOLEAN)) {
						String contactaccessDropdownOption = getSelectedOptionOfDropDown(driver,
								fp.getManageEmailContactAccessViewDropDownList(60), "Contact access view dropodown",
								"text");
						if (contactaccessDropdownOption.equalsIgnoreCase(M10Institution1)) {
							appLog.info(
									M10Institution1 + " is selected by default in the contact access view dropdown ");
						} else {
							appLog.info(
									M10Institution1 + " is not selected by default in contact access view dropdown");
							sa.assertTrue(false,
									M10Institution1 + " is not selected by default in contact access view dropdown");
						}
						List<WebElement> contactAccessViewDropdownValues = allOptionsInDropDrop(driver,
								fp.getManageEmailContactAccessViewDropDownList(60), "Show dropdown values");
						for (int i = 0; i < contactAccessViewDropdownValues.size(); i++) {
							if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase(M10Institution1)) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else
								if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase("All Folders")) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
								sa.assertTrue(false, contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
							}
						}
						if ( fp.clickUsingCssSelectorPath("a[title=Send]", "send button")) {
						//if (click(driver, fp.getmanageEmailsendBtn(60), "Send Button", action.SCROLLANDBOOLEAN)) {
							String alertmessage = switchToAlertAndGetMessage(driver, 60, action.GETTEXT);
							if (alertmessage.equalsIgnoreCase(
									FundsPageErrorMessage.pleaseSelectOneInvestorErrorMessageInManageEmails)) {
								appLog.info("Please select one investor eror message is verified");
							} else {
								appLog.info("Please select one invetsor error mesage is not verified");
								sa.assertTrue(false, "Please select one investor error message is not verified");
							}
							switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
						} else {
							appLog.info("Not able to click on send button");
							sa.assertTrue(false, "Not able to click on send button");
						}
						if (click(driver, fp.getManageEmailCustomRadioButton(60), "Custom email radio button",
								action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageEmailCustomTemplateCancelButton(60),
									"Manage Email custom template cancel button", action.SCROLLANDBOOLEAN)) {
								if ( fp.clickUsingCssSelectorPath("a[title=Send]", "send button")) {
									
								//if (click(driver, fp.getmanageEmailsendBtn(60), "Send Button",
								//		action.SCROLLANDBOOLEAN)) {
									String alertmessage = switchToAlertAndGetMessage(driver, 60, action.GETTEXT);
									if (alertmessage.equalsIgnoreCase(
											FundsPageErrorMessage.pleaseSelectOneInvestorErrorMessageInManageEmails)) {
										appLog.info("Please select one investor eror message is verified");
									} else {
										appLog.info("Please select one invetsor error mesage is not verified");
										sa.assertTrue(false,
												"Please select one investor error message is not verified");
									}
									switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
								} else {
									appLog.info("Not able to click on send button");
									sa.assertTrue(false, "Not able to click on send button");
								}
								if (fp.clickUsingCssSelectorPath("div#searchIddiv > a", "search")) {
								//if (click(driver, fp.getManageEmailSearchBtn(60), "Search button",
								//		action.SCROLLANDBOOLEAN)) {
									String alertmessage = switchToAlertAndGetMessage(driver, 60, action.GETTEXT);
									if (alertmessage.equalsIgnoreCase(
											FundsPageErrorMessage.pleaseEnterAValueErrorMessageInManageEmails)) {
										appLog.info("Please enter a value eror message is verified");
									} else {
										appLog.info("Please enter a value error mesage is not verified");
										sa.assertTrue(false, "Please enter a value error message is not verified");
									}
									switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
								} else {
									appLog.info("Not able to click on search icon");
									sa.assertTrue(false, "Not able to click on serach icon");
								}
							} else {
								appLog.info("Not able to click on manage email custom template cancel button");
								sa.assertTrue(false, "Not able to click on manage email custom template cancel button");
							}
						} else {
							appLog.info("Not able to click on custom email radio button");
							sa.assertTrue(false, "Not able to click on custom email radio button");
						}
					} else {
						appLog.info("Not able to click on manage emails");
						sa.assertTrue(false, "Not able to click on manage emails");
					}
				} else {
					appLog.info("Not able to select institution");
					sa.assertTrue(false, "Not able ot select institution");
				}
			} else {
				appLog.info("Not able to click on created fund");
				sa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on Funds tab");
			sa.assertTrue(false, "Not able to click on Funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();	
	}
	
	@Test
	public void M10tc017_CheckPreviewAndEditLinkForIInvitationEmailTemplateOption(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M10FundName1+"NUP")) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
				System.err.println("Switched to frame.");
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
						"Investor Workspace View.");
				if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage emails icon",
						action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getManageEmailInvitaionEmailPreviewLink(60), "Preview link",
							action.SCROLLANDBOOLEAN)) {
						String headertext = fp.getGetManageEmailInvitationMailPreviewHeader(60).getText().trim();
						if (headertext.equalsIgnoreCase("Preview")) {
							appLog.info("Preview popup is displaying");
						} else {
							appLog.info("Preview popup is not displaying");
							sa.assertTrue(false, "Preview popup is displaying");
						}
						if (fp.getManageEmailPreviewFormEmailIdText(60).getText().trim().contains(CRMUser1EmailID)) {
							appLog.info("Manage Email Invitation Email Preview Form Email Id is verified.");
						} else {
							appLog.info("Manage Email Invitation Email Preview Form Email Id is not verified.");
							sa.assertTrue(false,
									"Manage Email Invitation Email Preview Form Email Id is not verified.");
						}
						if (fp.getManageEmailPreviewSubjectText(60).getText().trim().contains("Invitation from")) {
							appLog.info("Manage Email Invitation Email Preview Subjetc Text is verified.");
						} else {
							appLog.info("Manage Email Invitation Email Preview Subjetc Text is not verified.");
							sa.assertTrue(false, "Manage Email Invitation Email Preview Subjetc Text is not verified.");
						}
						if (fp.getManageEmailPreviewSubjectText(60).getText().trim().contains(Org1FirmName+"UP")) {
							appLog.info("Manage Email Invitation Email Preview Subjetc Text is  verified.");
						} else {
							appLog.info("Manage Email Invitation Email Preview Subjetc Text is not verified.");
							sa.assertTrue(false, "Manage Email Invitation Email Preview Subjetc Text is not verified.");
						}
						if (fp.getManageEmailPreviewHelloText(60).getText().trim().contains("Hello")) {
							appLog.info("Manage Email Invitation Email Preview Hello Text is verified.");
						} else {
							appLog.info("Manage Email Invitation Email Preview Hello Text is not verified.");
							sa.assertTrue(false, "Manage Email Invitation Email Preview Hello Text is not verified.");
						}
						if (fp.getManageEmailPreviewHelloText(60).getText().trim().contains("Recipient first Name},")) {
							appLog.info(
									"Manage Email Invitation Email Preview Recipient First Name Text is verified.");
						} else {
							appLog.info(
									"Manage Email Invitation Email Preview Recipient First Name Text is not verified.");
							sa.assertTrue(false,
									"Manage Email Invitation Email Preview Recipient First Name Text is not verified.");
						}
						if (fp.getManageEmailPreviewGrantAccessText(60).getText().trim().contains(
								"You have been granted access to Current investments of "+M10FundName1+"UP"+" by")) {
							appLog.info("Manage Email Invitation Email Preview Grant Access Text is verified");
						} else {
							appLog.info("Manage Email Invitation Email Preview Grant Access Text is not verified");
							sa.assertTrue(false,
									"Manage Email Invitation Email Preview Grant Access Text is not verified");
						}
						if (fp.getManageEmailPreviewRegisterNotRegisterText(60).getText().trim()
								.contains("If you have not yet registered, Click here to register.")) {
							appLog.info(
									"Manage Email Invitation Email Preview If you have not yet registered Text is verified.");
						} else {
							appLog.info(
									"Manage Email Invitation Email Preview If you have not yet registered Text is not verified.");
							sa.assertTrue(false,
									"Manage Email Invitation Email Preview If you have not yet registered Text is not verified.");
						}
						if (fp.getManageEmailPreviewRegisterNotRegisterText(60).getText().trim()
								.contains("If you have already registered, Click here to login")) {
							appLog.info(
									"Manage Email Invitation Email Preview If you have already registered Text is verified.");
						} else {
							appLog.info(
									"Manage Email Invitation Email Preview If you have already registered Text is not verified.");
							sa.assertTrue(false,
									"Manage Email Invitation Email Preview If you have already registered Text is not verified.");
						}
						if (fp.getManageEmailPreviewBottomText(60).getText().trim().contains(
								"If you believe this has been sent in error, or if you cannot login, please contact")) {
							appLog.info("Manage Email Invitation Email Preview Bottom Text is verified.");
						} else {
							appLog.info("Manage Email Invitation Email Preview Bottom Text is not verified.");
							sa.assertTrue(false, "Manage Email Invitation Email Preview Bottom Text is not verified.");
						}
						if (fp.getManageEmailPreviewBottomText(60).getText().trim().contains(Org1FirmName+"UP")) {
							appLog.info("Manage Email Invitation Email Preview Bottom Text is verified.");
						} else {
							appLog.info("Manage Email Invitation Email Preview Bottom Text is not verified.");
							sa.assertTrue(false, "Manage Email Invitation Email Preview Bottom Text is not verified.");
						}
						if (isDisplayed(driver, fp.getManageEmailPreviewClosebtn(60), "Visibility", 30,
								"Close Button") != null) {
							appLog.info("Close Button in preview link is displaying");
						} else {
							appLog.info("Close Button in preview link is not displaying");
							sa.assertTrue(false, "Close Button in preview link is not displaying");
						}
						if (isDisplayed(driver, fp.getManageEmailPreviewCloseIcon(60), "Visibility", 30,
								"Cross icon") != null) {
							appLog.info("Cross icon in preview link is displaying");
						} else {
							appLog.info("Cross icon in preview link is not displaying");
							sa.assertTrue(false, "Cross icon in preview link is not displaying");
						}
						if (fp.getManageEmailPreviewNotRegisteredClickHereLink(20) != null) {
							if (click(driver, fp.getManageEmailPreviewNotRegisteredClickHereLink(30),
									"Not Registered Click here Link", action.SCROLLANDBOOLEAN)) {
								String parentid = switchOnWindow(driver);
								sa.assertTrue(getURL(driver, 60).contains("SiteRegisteration"),
										"Registration Page is not open after clicking on Resgister Click Here Link.");
								driver.close();
								driver.switchTo().window(parentid);
								switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
							} else {
								appLog.info("Not able to click on not registered click here link");
								sa.assertTrue(false, "Not able to click on not registered click here link");
							}
						} else {
							appLog.info(
									"Not Registered Click Here Link is not clickable on Manage Email Invitation Preview Pop Up.");
							sa.assertTrue(false,
									"Not Registered Click Here Link is not clickable on Manage Email Invitation Preview Pop Up.");
						}
						if (fp.getManageEmailPreviewRegisteredClickHereLink(30) != null) {
							if (click(driver, fp.getManageEmailPreviewRegisteredClickHereLink(30),
									"Not Registered Click here Link", action.SCROLLANDBOOLEAN)) {
								String parentid = switchOnWindow(driver);
								sa.assertTrue(getURL(driver, 60).contains("IP_login"),
										"Registration Page is not open after clicking on Resgister Click Here Link.");
								driver.close();
								driver.switchTo().window(parentid);
								switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
							} else {
								appLog.info("Not able to click on Registered Click Here Link");
								sa.assertTrue(false, "Not able to click on Registered Click Here Link");
							}
						} else {
							appLog.info(
									"Registered Click Here Link is not clickable on Manage Email Invitation Preview Pop Up.");
							sa.assertTrue(false,
									"Registered Click Here Link is not clickable on Manage Email Invitation Preview Pop Up.");
						}
						if (click(driver, fp.getManageEmailPreviewCloseIcon(30), "Close Button",
								action.SCROLLANDBOOLEAN)) {
							if (fp.getManageEmailPreviewCloseIcon(30) == null) {
								appLog.info("Manage Emails preview Pop Up is closed");
							} else {
								appLog.info("Manage Emails preview Pop Up is not closed");
								sa.assertTrue(false, "Manage Emails preview Pop Up is not closed");
							}
						} else {
							appLog.info("Not able to click on close button of preview popup");
							sa.assertTrue(false, "Not able to click on close button of preview popup");
						}
					} else {
						appLog.info("Not able to click on preview link");
						sa.assertTrue(false, "Not able to click on preview link");
					}
					if (click(driver, fp.getInvitationEmailEditLinkInManageEmails(30),
							"Manage Email Invitation Edit Link", action.SCROLLANDBOOLEAN)) {
						if (fp.getManageEmailEditFormEmailIdtext(60).getText().trim().contains(CRMUser1EmailID)) {
							appLog.info("Manage Email Invitation Email Edit Form Email Id is verified.");
						} else {
							appLog.info("Manage Email Invitation Email Edit Form Email Id is not verified.");
							sa.assertTrue(false, "Manage Email Invitation Email Edit Form Email Id is not verified.");
						}
						if (fp.getManageEmailEditSubjectText(60).getText().trim().contains("Invitation from")) {
							appLog.info("Manage Email Invitation Email Edit Subjetc Text is verified.");
						} else {
							appLog.info("Manage Email Invitation Email Edit Subjetc Text is not verified.");
							sa.assertTrue(false, "Manage Email Invitation Email Edit Subjetc Text is not verified.");
						}
						if (fp.getManageEmailEditSubjectText(60).getText().trim().contains(Org1FirmName+"UP")) {
							appLog.info("Manage Email Invitation Email Edit Subjetc Text is  verified.");
						} else {
							appLog.info("Manage Email Invitation Email Edit Subjetc Text is not verified.");
							sa.assertTrue(false, "Manage Email Invitation Email Edit Subjetc Text is not verified.");
						}
						if (fp.getManageEmailEditHelloText(60).getText().trim().contains("Hello")) {
							appLog.info("Manage Email Invitation Email Edit Hello Text is verified.");
						} else {
							appLog.info("Manage Email Invitation Email Edit Hello Text is not verified.");
							sa.assertTrue(false, "Manage Email Invitation Email Edit Hello Text is not verified.");
						}
						if (fp.getManageEmailEditHelloText(60).getText().trim().contains("Recipient first Name},")) {
							appLog.info("Manage Email Invitation Email Edit Recipient First Name Text is verified.");
						} else {
							appLog.info(
									"Manage Email Invitation Email Edit Recipient First Name Text is not verified.");
							sa.assertTrue(false,
									"Manage Email Invitation Email Edit Recipient First Name Text is not verified.");
						}
						if (fp.getGetManageEmailEditGrantAccessText(60).getText().trim().contains(
								"You have been granted access to Current investments of "+M10FundName1+"UP"+" by")) {
							appLog.info("Manage Email Invitation Email Edit Grant Access Text is verified.");
						} else {
							appLog.info("Manage Email Invitation Email Edit Grant Access Text is not verified.");
							sa.assertTrue(false,
									"Manage Email Invitation Email Edit Grant Access Text is not verified.");
						}
						if (fp.getGetManageEmailEditGrantAccessText(60).getText().trim().contains(Org1FirmName+"UP")) {
							appLog.info("Manage Email Invitation Email Edit Grant Access Text is verified.");
						} else {
							appLog.info("Manage Email Invitation Email Edit Grant Access Text is not verified.");
							sa.assertTrue(false,
									"Manage Email Invitation Email Edit Grant Access Text is not verified.");
						}
						if (fp.getManageEmailEditRegisterNotRegisterText(60).getText().trim()
								.contains("If you have not yet registered, Click here to register.")) {
							appLog.info(
									"Manage Email Invitation Email Edit If you have not yet registered Text is verified.");
						} else {
							appLog.info(
									"Manage Email Invitation Email Edit If you have not yet registered Text is not verified.");
							sa.assertTrue(false,
									"Manage Email Invitation Email Edit If you have not yet registered Text is not verified.");
						}
						if (fp.getManageEmailEditRegisterNotRegisterText(60).getText().trim()
								.contains("If you have already registered, Click here to login")) {
							appLog.info(
									"Manage Email Invitation Email Edit If you have already registered Text is verified.");
						} else {
							appLog.info(
									"Manage Email Invitation Email Edit If you have already registered Text is not verified.");
							sa.assertTrue(false,
									"Manage Email Invitation Email Edit If you have already registered Text is not verified.");
						}
						if (fp.getManageEmailEditBottomText(60).getText().trim().contains(
								"If you believe this has been sent in error, or if you cannot login, please contact")) {
							appLog.info("Manage Email Invitation Email Edit Bottom Text is verified.");
						} else {
							appLog.info("Manage Email Invitation Email Edit Bottom Text is not verified.");
							sa.assertTrue(false, "Manage Email Invitation Email Edit Bottom Text is not verified.");
						}
						if (fp.getManageEmailEditBottomText(60).getText().trim().contains(Org1FirmName+"UP")) {
							appLog.info("Manage Email Invitation Email Edit Bottom Text is verified.");
						} else {
							appLog.info("Manage Email Invitation Email Edit Bottom Text is not verified.");
							sa.assertTrue(false, "Manage Email Invitation Email Edit Bottom Text is not verified.");
						}
						if (isDisplayed(driver, fp.getManageEmailInvitationTextBoxApplyButton(60), "Visibility", 60,
								"Apply Button") != null) {
							appLog.info("Apply button is displaying");
						} else {
							appLog.info("Apply button is not displaying");
							sa.assertTrue(false, "Apply button is not displaying");
						}
						if (isDisplayed(driver, fp.getManageEmailInvitationTextBoxCancelButton(60), "Visibility", 60,
								"Cancel Button") != null) {
							appLog.info("Cancel button is displaying");
						} else {
							appLog.info("Cancel button is not displaying");
							sa.assertTrue(false, "Cancel button is not displaying");
						}
						if (isDisplayed(driver, fp.getManageEmailEditTemplateCloseIcon(60), "Visibility", 60,
								"Close Icon") != null) {
							appLog.info("Close Icon is displaying");
						} else {
							appLog.info("Close Icon is not displaying");
							sa.assertTrue(false, "Close Icon is not displaying");
						}
						if (click(driver, fp.getManageEmailInvitationTextBoxCancelButton(30), "Cancel button",
								action.SCROLLANDBOOLEAN)) {
							if (fp.getManageEmailInvitationTextBoxCancelButton(10) == null) {
								appLog.info("Edit popup get closed");
							} else {
								appLog.info("Edit popup does not get closed");
								sa.assertTrue(false, "Edit popup does not get closed");
							}
						} else {
							appLog.info("Clicked on cancel button");
							sa.assertTrue(false, "Clicked on cancel button");
						}
						if (click(driver, fp.getInvitationEmailEditLinkInManageEmails(30),
								"Manage Email Invitation Edit Link", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageEmailEditTemplateCloseIcon(30), "Close icon",
									action.SCROLLANDBOOLEAN)) {
								if (fp.getManageEmailEditTemplateCloseIcon(10) == null) {
									appLog.info("Edit popup get closed");
								} else {
									appLog.info("Edit popup does not get closed");
									sa.assertTrue(false, "Edit popup does not get closed");
								}
							} else {
								appLog.info("Clicked on Close icon");
								sa.assertTrue(false, "Clicked on Close icon");
							}
						} else {
							appLog.info("Not able to click on edit icon");
							sa.assertTrue(false, "Not able to click on edit icon");
						}
					} else {
						appLog.info("Not able to click on manage email edit link");
						sa.assertTrue(false, "Not able to click on manage email edit link");
					}
					if (click(driver, fp.getInvitationEmailEditLinkInManageEmails(30),
							"Manage Email Invitation Edit Link", action.SCROLLANDBOOLEAN)) {
						if (fp.getManageEmailEditNotRegisteredClickHereLink(20) != null) {
							click(driver, fp.getManageEmailEditNotRegisteredClickHereLink(30),
									"Not Registered Click here Link", action.SCROLLANDTHROWEXCEPTION);
							String parentid = switchOnWindow(driver);
							sa.assertTrue(getURL(driver, 60).contains("SiteRegisteration"),
									"Registration Page is not open after clicking on Resgister Click Here Link.");
							driver.close();
							driver.switchTo().window(parentid);
							switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));

						} else {
							appLog.info(
									"Not Registered Click Here Link is not clickable on Manage Email Invitation Edit Pop Up.");
							sa.assertTrue(false,
									"Not Registered Click Here Link is not clickable on Manage Email Invitation Edit Pop Up.");
						}
						if (fp.getManageEmailEditRegisteredClickHereLink(30) != null) {
							click(driver, fp.getManageEmailEditRegisteredClickHereLink(30),
									"Not Registered Click here Link", action.SCROLLANDTHROWEXCEPTION);
							String parentid = switchOnWindow(driver);
							sa.assertTrue(getURL(driver, 60).contains("IP_login"),
									"Registration Page is not open after clicking on Resgister Click Here Link.");
							driver.close();
							driver.switchTo().window(parentid);
							switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));

						} else {
							appLog.info(
									"Registered Click Here Link is not clickable on Manage Email Invitation Edit Pop Up.");
							sa.assertTrue(false,
									"Registered Click Here Link is not clickable on Manage Email Invitation Edit Pop Up.");
						}
						if (sendKeys(driver, fp.getManageEmailInvitationDescriptionTextBox(60), "This is test mail",
								"Manage Email Edit Template Text Box", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageEmailInvitationTextBoxApplyButton(30), "Apply button",
									action.SCROLLANDBOOLEAN)) {
								ThreadSleep(3000);
								if (click(driver, fp.getManageEmailInvitaionEmailPreviewLink(30),
										"Manage Emails Preview Link", action.SCROLLANDBOOLEAN)) {
									ThreadSleep(3000);
									if (fp.getManageEmailPreviewTextMassage(30).getText().trim()
											.contains("This is test mail")) {
										appLog.info("Manage Email Preview Text Message is verified");
									} else {
										appLog.info("Manage Email Preview Text Message is not verified");
										sa.assertTrue(false, "Manage Email Preview Text Message is not verified");
									}
									if (click(driver, fp.getManageEmailPreviewClosebtn(30), "Close Button",
											action.SCROLLANDBOOLEAN)) {
										appLog.info("Clicked on close button");
									} else {
										appLog.info("Not able to click on preview close button");
										sa.assertTrue(false, "Not able to click on preview close button");
									}
								} else {
									appLog.info("Not able to click on preview link");
									sa.assertTrue(false, "Not able to click on preview link");
								}
							} else {
								appLog.info("Not able to click on apply button");
								sa.assertTrue(false, "Not able to click on apply button");
							}
						} else {
							appLog.info("Not able to enter value in description tetx box");
							sa.assertTrue(false, "Not able to enter value in description tetx box");
						}
					} else {
						appLog.info("Not able to click on manage email edit link");
						sa.assertTrue(false, "Not able to click on manage email edit link");
					}
					if (click(driver, fp.getInvitationEmailEditLinkInManageEmails(30),
							"Manage Email Invitation Edit Link", action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, fp.getManageEmailInvitationDescriptionTextBox(60), "",
								"Manage Email Edit Template Text Box", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageEmailInvitationTextBoxApplyButton(30), "Apply button",
									action.SCROLLANDBOOLEAN)) {
								ThreadSleep(3000);
								if (click(driver, fp.getManageEmailInvitaionEmailPreviewLink(30),
										"Manage Emails Preview Link", action.SCROLLANDBOOLEAN)) {
									ThreadSleep(3000);
									if (fp.getManageEmailPreviewTextMassage(30) == null) {
										appLog.info("Text Message is removed form manage email preview template.");
									} else {
										appLog.info("Manage Email Preview Text Message is not verified");
										sa.assertTrue(false,
												"Manage Email Preview Text Message is not verified. Expected: Blank text Box");
									}
									if (click(driver, fp.getManageEmailPreviewClosebtn(30), "Close Button",
											action.SCROLLANDBOOLEAN)) {
										appLog.info("Clicked on close button");
									} else {
										appLog.info("Not able to click on preview close button");
										sa.assertTrue(false, "Not able to click on preview close button");
									}
								} else {
									appLog.info("Not able to click on preview link");
									sa.assertTrue(false, "Not able to click on preview link");
								}
							} else {
								appLog.info("Not able to click on apply button");
								sa.assertTrue(false, "Not able to click on apply button");
							}
						} else {
							appLog.info("Not able to enter value in description tetx box");
							sa.assertTrue(false, "Not able to enter value in description tetx box");
						}
					} else {
						appLog.info("Not able to click on manage email edit link");
						sa.assertTrue(false, "Not able to click on manage email edit link");
					}
					if (click(driver, fp.getInvitationEmailEditLinkInManageEmails(30),
							"Manage Email Invitation Edit Link", action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, fp.getManageEmailInvitationDescriptionTextBox(60),
								"This is Max Data Text to Test this area. This is Max Data Text to Test this area. This is Max Data Text to Test this area. This is Max Data Text to Test this area. This is Max Data Text to Test this area. This is Max Data Text to Test this area. This is Max",
								"Manage Email Edit Template Text Box", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageEmailInvitationTextBoxApplyButton(30), "Apply button",
									action.SCROLLANDBOOLEAN)) {
								ThreadSleep(3000);
								if (fp.getManageEmailInvitationemailEditErrorMessage(60).getText().trim()
										.contains(FundsPageErrorMessage.manageEmailInvitationEditBodyErrorMessage)) {
									appLog.info("Error Message is verified");
								} else {
									appLog.info("Error Message is not verified");
									sa.assertTrue(false, "Error Message is not verified");
								}
							} else {
								appLog.info("Not able to click on apply button");
								sa.assertTrue(false, "Not able to click on apply button");
							}
						} else {
							appLog.info("Not able to enter value in description tetx box");
							sa.assertTrue(false, "Not able to enter value in description tetx box");
						}
					} else {
						appLog.info("Not able to click on manage email edit link");
						sa.assertTrue(false, "Not able to click on manage email edit link");
					}
				} else {
					appLog.info("Not able to click on manage emails");
					sa.assertTrue(false, "Not able to click on manage emails");
				}
			} else {
				appLog.info("Not able to click on created fund");
				sa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on Funds tab");
			sa.assertTrue(false, "Not able to click on Funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();	
	}
	
	@Test
	public void M10tc018_CheckPreviewAndEditLinkForCustomEmailTemplateOption(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M10FundName1+"NUP")) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
				System.err.println("Switched to frame.");
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
						"Investor Workspace View.");
				if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage emails icon",
						action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getManageEmailCustomRadioButton(60), "Custom template radio button",
							action.SCROLLANDBOOLEAN)) {
						if (fp.getManageEmailCustomSubjectTextBox(20) != null) {
							appLog.info("Subject Text Box is available in custom email pop up.");
						} else {
							appLog.info("Subject Text Box is not available in custom email pop up.");
							sa.assertTrue(false, "Subject Text Box is not available in custom email pop up.");
						}
						if (fp.getManageEmailCustomTemplateEditEmailIdText(30).getText().trim()
								.contains(CRMUser1EmailID)) {
							appLog.info("Manage Emails Custom Template Form Email id is verified.");
						} else {
							appLog.info("Manage Emails Custom Template Form Email id is not verified.");
							sa.assertTrue(false, "Manage Emails Custom Template Form Email id is not verified.");
						}
						switchToFrame(driver, 30, fp.getManageEmailCustomSubjectBodyFrame(30));
						if (fp.getManageEmailCustomSubjectBody(20) != null) {
							appLog.info("Body Rich Text is available in the Custom Template Pop Up");
						} else {
							appLog.info("Body Rich Text is not available in the Custom Template Pop Up");
							sa.assertTrue(false, "Body Rich Text is not available in the Custom Template Pop Up");
						}
						switchToDefaultContent(driver);
						switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
						if (fp.getManageEmailCustomApplyButton(20) != null) {
							appLog.info("Apply Button is available.");
						} else {
							appLog.info("Apply Button is available.");
							sa.assertTrue(false, "Apply Button is not available.");
						}
						if (fp.getManageEmailCustomTextBoxCancelButton(20) != null) {
							appLog.info("Cancel Button is available");
						} else {
							appLog.info("Cancel Button is not available");
							sa.assertTrue(false, "Cancel Button is not available");
						}
						if (fp.getManageEmailCustomCloseIcon(20) != null) {
							appLog.info("Close Icon is available");
						} else {
							appLog.info("Close Icon is not available");
							sa.assertTrue(false, "Close Icon is not available");
						}
						if (click(driver, fp.getManageEmailCustomApplyButton(20), "Apply Button",
								action.SCROLLANDBOOLEAN)) {
							if (fp.getManageEmailEditSubjectErrorMessage(20).getText().trim()
									.contains(FundsPageErrorMessage.pleaseEnterAValueErrorMessageInManageEmails)) {
								appLog.info("Custom Email Template Blank Subject Error Message is verified.");
							} else {
								appLog.info("Custom Email Template Blank Subject Error Message is not verified.");
								sa.assertTrue(false,
										"Custom Email Template Blank Subject Error Message is not verified.");
							}
							if (fp.getManageEmailCustomEditBodyErrorMessage(20).getText().trim()
									.contains(FundsPageErrorMessage.pleaseEnterAValueErrorMessageInManageEmails)) {
								appLog.info("Custom Email Template Blank Subject Error Message is verified.");
							} else {
								appLog.info("Custom Email Template Blank Subject Error Message is not verified.");
								sa.assertTrue(false,
										"Custom Email Template Blank Subject Error Message is not verified.");
							}
						} else {
							appLog.info("Not able to click on apply button");
							sa.assertTrue(false, "Not able to clcik on apply button");
						}
						if (click(driver, fp.getManageEmailCustomCloseIcon(20), "Close Icon",
								action.SCROLLANDBOOLEAN)) {
							if (fp.getManageEmailCustomCloseIcon(20) == null) {
								appLog.info("Custom Email Template PopUp is closed");
							} else {
								appLog.info("Custom Email Template PopUp is not closed");
								sa.assertTrue(false, "Custom Email Template PopUp is not closed");
							}
						} else {
							appLog.info("Clicked on close icon");
							sa.assertTrue(false, "Not able to click on close icon");
						}
						if (isEnabled(driver, fp.getCustomEmailEditLinkInManageEmails(60), "Edit link")) {
							appLog.info("Edit link is enabled");
						} else {
							appLog.info("Edit link is not enabled");
							sa.assertTrue(false, "Edit link is not enabled");
						}
						if (isEnabled(driver, fp.getManageEmailCustomPreviewLink(60), "Preview link")) {
							appLog.info("Preview link is enabled");
						} else {
							appLog.info("Preview link is not enabled");
							sa.assertTrue(false, "Preview link is not enabled");
						}
						if (isEnabled(driver, fp.getInvitationEmailEditLinkInManageEmails(60), "Edit link")) {
							appLog.info("Edit link of invitation template is not enabled");
						} else {
							appLog.info("Edit link of invitation template is enabled");
							sa.assertTrue(false, "Edit link of invitation template is enabled");
						}
						if (isEnabled(driver, fp.getManageEmailInvitaionEmailPreviewLink(60), "Preview link")) {
							appLog.info("Preview link of invitation template is not enabled");
						} else {
							appLog.info("Preview link of invitation template is enabled");
							sa.assertTrue(false, "Preview link of invitation template is enabled");
						}	
					} else {
						appLog.info("Not able to click on manage email custom radio button");
						sa.assertTrue(false, "Not able to click on manage email custom radio button");
					}
					if (click(driver, fp.getCustomEmailEditLinkInManageEmails(30), "Custom Email Template Edit Link",
							action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getManageEmailCustomTextBoxCancelButton(20), "Cancel button",
								action.SCROLLANDBOOLEAN)) {
							if (fp.getManageEmailCustomTextBoxCancelButton(20) == null) {
								appLog.info("Custom Email Template PopUp is closed");
							} else {
								appLog.info("Custom Email Template PopUp is not closed");
								sa.assertTrue(false, "Custom Email Template PopUp is not closed");
							}
						} else {
							appLog.info("Clicked on Cancel button");
							sa.assertTrue(false, "Not able to click on Cancel button");
						}
						if (isEnabled(driver, fp.getCustomEmailEditLinkInManageEmails(60), "Edit link")) {
							appLog.info("Edit link is enabled");
						} else {
							appLog.info("Edit link is not enabled");
							sa.assertTrue(false, "Edit link is not enabled");
						}
						if (isEnabled(driver, fp.getManageEmailCustomPreviewLink(60), "Preview link")) {
							appLog.info("Preview link is enabled");
						} else {
							appLog.info("Preview link is not enabled");
							sa.assertTrue(false, "Preview link is not enabled");
						}
						if (isEnabled(driver, fp.getInvitationEmailEditLinkInManageEmails(60), "Edit link")) {
							appLog.info("Edit link of invitation template is not enabled");
						} else {
							appLog.info("Edit link of invitation template is enabled");
							sa.assertTrue(false, "Edit link of invitation template is enabled");
						}
						if (isEnabled(driver, fp.getManageEmailInvitaionEmailPreviewLink(60), "Preview link")) {
							appLog.info("Preview link of invitation template is not enabled");
						} else {
							appLog.info("Preview link of invitation template is enabled");
							sa.assertTrue(false, "Preview link of invitation template is enabled");
						}						
					} else {
						appLog.info("Not able to click on edit link");
						sa.assertTrue(false, "Not able to click on edit link");
					}
					if (click(driver, fp.getCustomEmailEditLinkInManageEmails(30), "Custom Email Template Edit Link",
							action.SCROLLANDBOOLEAN)) {
						ThreadSleep(2000);
						if (click(driver, fp.getManageEmailCustomTemplateEditEmailIdText(30),
								"Custom Email Template Edit Email ID", action.SCROLLANDBOOLEAN)) {
							if (!new NIMPageBusinessLayer(driver)
									.verifyNavatarSalesTeamLinkFunctionality("ContactEmailLink")) {
								appLog.info("Verification of Contact Email link is unsuccessfull.");
								sa.assertTrue(false, "Verification of Contact Email link is unsuccessfull.");
							} else {
								appLog.info("Verification of Contact Email Link is successfully");
							}
						} else {
							appLog.info("Not able to click on email id of user");
							sa.assertTrue(false, "Not able to click on email id of user");
						}
						if (sendKeys(driver, fp.getManageEmailCustomSubjectTextBox(30), "Test Subject",
								"Custom Email Subject Text Box", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageEmailCustomApplyButton(20), "Apply Button",
									action.SCROLLANDBOOLEAN)) {
								if (fp.getManageEmailCustomEditBodyErrorMessage(20).getText().trim()
										.contains(FundsPageErrorMessage.pleaseEnterAValueErrorMessageInManageEmails)) {
									appLog.info("Custom Email Template Blank Subject Error Message is verified.");
								} else {
									appLog.info("Custom Email Template Blank Subject Error Message is not verified.");
									sa.assertTrue(false,
											"Custom Email Template Blank Subject Error Message is not verified.");
								}
							} else {
								appLog.info("Not able to click on apply button");
								sa.assertTrue(false, "Not able to clcik on apply button");
							}
							switchToFrame(driver, 30, fp.getManageEmailCustomSubjectBodyFrame(30));
							if (sendKeys(driver, fp.getManageEmailCustomSubjectBody(20),
									"This is Test Mail Data for Email", "Custom Email Body Text Box",
									action.SCROLLANDBOOLEAN)) {
								switchToDefaultContent(driver);
								switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
								if (click(driver, fp.getManageEmailCustomApplyButton(20), "Apply Button",
										action.SCROLLANDBOOLEAN)) {
									appLog.info("click on apply button");
									if (fp.getManageEmailCustomApplyButton(20) == null) {
										appLog.info("Custom Email Template PopUp is closed with custom text.");
									} else {
										appLog.info("Custom Email Template PopUp is not closed with custom text.");
										sa.assertTrue(false,
												"Custom Email Template PopUp is not closed with custom text.");
									}
								} else {
									appLog.info("Not able to click on apply button");
									sa.assertTrue(false, "Not able to clcik on apply button");
								}
							} else {
								appLog.info("Not able to enter value in body text box");
								sa.assertTrue(false, "Not able to enter value in body text box");
							}
						} else {
							appLog.info("Not able to enter value in subject text box");
							sa.assertTrue(false, "Not able to enter value in subject text box");
						}
					} else {
						appLog.info("Not able to click on edit link");
						sa.assertTrue(false, "Not able to click on edit link");
					}
					if (click(driver, fp.getManageEmailCustomPreviewLink(30), "Custom Email Preview Link",
							action.SCROLLANDBOOLEAN)) {
						if (fp.getManageEmailCustomPreviewFormEmailId(20).getText().trim().contains(CRMUser1EmailID)) {
							appLog.info("Custom Email Preview Email Id Text is verified.");
						} else {
							appLog.info("Custom Email Preview Email Id Text is not verified.");
							sa.assertTrue(false, "Custom Email Preview Email Id Text is not verified.");
						}
						if (fp.getManageEmailCustomTemplateSubjectText(20).getText().trim().contains("Test Subject")) {
							appLog.info("Custom Email Subject is verified.");
						} else {
							appLog.info("Custom Email Subject is not verified.");
							sa.assertTrue(false, "Custom Email Subject is not verified.");
						}
						if (fp.getManageEmailCustomTemplateBodyText(20).getText().trim()
								.contains("This is Test Mail Data for Email")) {
							appLog.info("Custom Email Body Text is verified.");
						} else {
							appLog.info("Custom Email Body Text is not verified.");
							sa.assertTrue(false, "Custom Email Body Text is not verified.");
						}
						if (click(driver, fp.getManageEmailCustomPreviewCloseBtn(20), "Custom Email Id Close Button",
								action.SCROLLANDBOOLEAN)) {
							if (fp.getManageEmailCustomPreviewCloseBtn(20) == null) {
								appLog.info("Custom Email Template PopUp is closed with custom text.");
							} else {
								appLog.info("Custom Email Template PopUp is not closed with custom text.");
								sa.assertTrue(false, "Custom Email Template PopUp is not closed with custom text.");
							}
						} else {
							appLog.info("Not able to click on close button");
							sa.assertTrue(false, "Not able to click on close button");
						}
					} else {
						appLog.info("Not able to click on preview link");
						sa.assertTrue(false, "Not able to click on preview link");
					}
					if (click(driver, fp.getManageEmailCustomPreviewLink(30), "Custom Email Preview Link",
							action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getManageEmailCustomPreviewCloseIcon(20), "Custom Email Id Close icon",
								action.SCROLLANDBOOLEAN)) {
							if (fp.getManageEmailCustomPreviewCloseIcon(20) == null) {
								appLog.info("Custom Email Template PopUp is closed with custom text.");
							} else {
								appLog.info("Custom Email Template PopUp is not closed with custom text.");
								sa.assertTrue(false, "Custom Email Template PopUp is not closed with custom text.");
							}
						} else {
							appLog.info("Not able to click on close icon");
							sa.assertTrue(false, "Not able to click on close icon");
						}
					} else {
						appLog.info("Not able to click on preview link");
						sa.assertTrue(false, "Not able to click on preview link");
					}
					if (click(driver, fp.getCustomEmailEditLinkInManageEmails(30), "Custom Email Template Edit Link",
							action.SCROLLANDBOOLEAN)) {
						ThreadSleep(2000);
						if (sendKeys(driver, fp.getManageEmailCustomSubjectTextBox(30),
								"This is Max Subject text to test Data. This is Max Subject text to test Data. This is Max Subject text to test Data. This is Max Subject text to test Data. This is Max Subject text to test Data. This is Max Subject text to test Data. This is Max Subject text to test Data.",
								"Custom Email Subject Text Box", action.SCROLLANDBOOLEAN)) {
							ThreadSleep(2000);
							switchToFrame(driver, 30, fp.getManageEmailCustomSubjectBodyFrame(30));
							if (sendKeys(driver, fp.getManageEmailCustomSubjectBody(20),
									"This is Test Mail Data for Email", "Custom Email Body Text Box",
									action.SCROLLANDBOOLEAN)) {
								switchToDefaultContent(driver);
								switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
								if (click(driver, fp.getManageEmailCustomApplyButton(20), "Apply Button",
										action.SCROLLANDBOOLEAN)) {
									appLog.info("click on apply button");
									if (fp.getManageEmailCustomTemplateSubjectErrorMessage(60).getText().trim()
											.contains(FundsPageErrorMessage.manageEmailCustomEditSubjectErrorMessage)) {
										appLog.info("Error Message is verified");
									} else {
										appLog.info("Error Message is not verified");
										sa.assertTrue(false, "Error Message is not verified");
									}
								} else {
									appLog.info("Not able to click on apply button");
									sa.assertTrue(false, "Not able to clcik on apply button");
								}
							} else {
								appLog.info("Not able to enter value in body text box");
								sa.assertTrue(false, "Not able to enter value in body text box");
							}
						} else {
							appLog.info("Not able to enter value in body text box");
							sa.assertTrue(false, "Not able to enter value in body text box");
						}
					} else {
						appLog.info("Not able to click on edit link");
						sa.assertTrue(false, "Not able to click on edit link");
					}
				} else {
					appLog.info("Not able to click on manage emails");
					sa.assertTrue(false, "Not able to click on manage emails");
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
		lp.CRMlogout(environment, mode);
		sa.assertAll();	
	}
	
	@Test
	public void M10tc019_InviteContactAndVerifyManageEmailsPopUp(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		String[] stdPath = ExcelUtils
				.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath)
				.split(",");
		String[] sharedPath = ExcelUtils
				.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath)
				.split(",");
		String[] commonPath = ExcelUtils
				.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath)
				.split(",");
		String[] internalPath = ExcelUtils
				.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath)
				.split(",");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M10FundName1+"NUP")) {
				if (fp.inviteContact(environment, mode, M10Institution1+"/"+M10LimitedPartner1, M10Contact1EmailId, stdPath[0],
						FolderType.Standard, "Upload", "Yes", "No", null, Workspace.InvestorWorkspace, null)) {
					appLog.info("Successfully provided access to contact '" + M10Contact1FirstName + " "
							+ M10Contact1LastName + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M10Contact1FirstName + " "
							+ M10Contact1LastName + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M10Contact1FirstName + " "
							+ M10Contact1LastName + "'.");
				}
				if (fp.inviteContact(environment, mode, M10Institution1+"/"+M10LimitedPartner1, M10Contact1EmailId, null, FolderType.Standard,
						"Upload", "Yes", "No", null, Workspace.InvestorWorkspace, null)) {
					appLog.info("Successfully provided access to contact '" + M10Contact1FirstName + " "
							+ M10Contact1LastName + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M10Contact1FirstName + " "
							+ M10Contact1LastName + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M10Contact1FirstName + " "
							+ M10Contact1LastName + "'.");
				}
				if (fp.inviteContact(environment, mode, M10Institution1, M10Contact1EmailId, null, FolderType.Standard,
						"Upload", "Yes", "No", null, Workspace.InvestorWorkspace, null)) {
					appLog.info("Successfully provided access to contact '" + M10Contact1FirstName + " "
							+ M10Contact1LastName + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M10Contact1FirstName + " "
							+ M10Contact1LastName + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M10Contact1FirstName + " "
							+ M10Contact1LastName + "'.");
				}	
				
				if (fp.inviteContact(environment, mode, M10Institution1, M10Contact1EmailId, sharedPath[1],
						FolderType.Shared, "Download", "Yes", "No", null, Workspace.InvestorWorkspace, null)) {
					appLog.info("Successfully provided access to contact '" + M10Contact1FirstName + " "
							+ M10Contact1LastName + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M10Contact1FirstName + " "
							+ M10Contact1LastName + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M10Contact1FirstName + " "
							+ M10Contact1LastName + "'.");
				}
				if (fp.inviteContact(environment, mode, M10Institution2+"/"+M10LimitedPartner2, M10Contact2EmailId, stdPath[0],
						FolderType.Standard, "Upload", "Yes", "No", null, Workspace.InvestorWorkspace, null)) {
					appLog.info("Successfully provided access to contact '" + M10Contact2FirstName + " "
							+ M10Contact2LastName + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M10Contact2FirstName + " "
							+ M10Contact2LastName + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M10Contact2FirstName + " "
							+ M10Contact2LastName + "'.");
				}
		
				driver.navigate().refresh();
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30),
						"Investor Section view");
				if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage email icon",
						action.SCROLLANDBOOLEAN)) {
					String contactAccessDropdown = getSelectedOptionOfDropDown(driver,
							fp.getManageEmailContactAccessViewDropDownList(60), "Contact access view dropodown list",
							"text");
					if (contactAccessDropdown.contains("All Folders")) {
						appLog.info("All folders is selected in the contact access view dropdown");
					} else {
						appLog.info("All folders is not selected in the Contact access view dropodwn ");
						sa.assertTrue(false, "All folders is not selected in the Contact access view dropodwn ");
					}
					if (fp.verifyManageEmailGrid(M10Contact1FirstName+"RUP" + " " + M10Contact1LastName+"RUP", M10Contact1EmailId,
							M10Institution1+"RUP", null)) {
						appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
								+ M10Contact1LastName);
					} else {
						appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
								+ M10Contact1LastName);
						sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
								+ M10Contact1LastName);
					}
					if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName, M10Contact2EmailId,
							M10Institution2, null)) {
						appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
								+ M10Contact2LastName);
					} else {
						appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
								+ M10Contact2LastName);
						sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
								+ M10Contact2LastName);
					}
					if (click(driver, fp.getManageEmailCancelBtn(60), "Manage Email Cancel Button",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on cancel button");
					} else {
						appLog.info("Clicked on cancel button");
						sa.assertTrue(false, "clicked on cancel button");
					}
				} else {
					appLog.info("Not able to clcik on manage email");
					sa.assertTrue(false, "Not able to click on manage email");
				}
				if (fp.verifyFolderPathdummy(commonPath[0], null, null, M10FundName1, PageName.FundsPage,
						Workspace.InvestorWorkspace, 60)) {
					if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage email icon",
							action.SCROLLANDBOOLEAN)) {
						String contactAccessDropdown = getSelectedOptionOfDropDown(driver,
								fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropodown list", "text");
						if (contactAccessDropdown.contains("All Folders")) {
							appLog.info("All folders is selected in the contact access view dropdown");
						} else {
							appLog.info("All folders is not selected in the Contact access view dropodwn ");
							sa.assertTrue(false, "All folders is not selected in the Contact access view dropodwn ");
						}
						if (fp.verifyManageEmailGrid(M10Contact1FirstName+"RUP" + " " + M10Contact1LastName+"RUP", M10Contact1EmailId,
								M10Institution1+"RUP", null)) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
						}
						if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName, M10Contact2EmailId,
								M10Institution2, null)) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
						}
						if (click(driver, fp.getManageEmailCancelBtn(60), "Manage Email Cancel Button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on cancel button");
						} else {
							appLog.info("Clicked on cancel button");
							sa.assertTrue(false, "clicked on cancel button");
						}
					} else {
						appLog.info("Not able to clcik on manage email");
						sa.assertTrue(false, "Not able to click on manage email");
					}
				} else {
					appLog.info("Not able to find folder path");
					sa.assertTrue(false, "Not able to find folder path");
				}
				if (fp.verifyFolderPathdummy(commonPath[1], null, null, M10FundName1, PageName.FundsPage,
						Workspace.InvestorWorkspace, 60)) {
					if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage email icon",
							action.SCROLLANDBOOLEAN)) {
						String contactAccessDropdown = getSelectedOptionOfDropDown(driver,
								fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropodown list", "text");
						if (contactAccessDropdown.contains("All Folders")) {
							appLog.info("All folders is selected in the contact access view dropdown");
						} else {
							appLog.info("All folders is not selected in the Contact access view dropodwn ");
							sa.assertTrue(false, "All folders is not selected in the Contact access view dropodwn ");
						}
						if (fp.verifyManageEmailGrid(M10Contact1FirstName+"RUP" + " " + M10Contact1LastName+"RUP", M10Contact1EmailId,
								M10Institution1+"RUP", null)) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
						}
						if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName, M10Contact2EmailId,
								M10Institution2, null)) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
						}
						if (click(driver, fp.getManageEmailCancelBtn(60), "Manage Email Cancel Button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on cancel button");
						} else {
							appLog.info("Clicked on cancel button");
							sa.assertTrue(false, "clicked on cancel button");
						}

					} else {
						appLog.info("Not able to clcik on manage email");
						sa.assertTrue(false, "Not able to click on manage email");
					}
				} else {
					appLog.info("Not able to find folder path");
					sa.assertTrue(false, "Not able to find folder path");
				}
				if (fp.verifyFolderPathdummy(internalPath[0], null, null, M10FundName1, PageName.FundsPage,
						Workspace.InvestorWorkspace, 60)) {
					if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage email icon",
							action.SCROLLANDBOOLEAN)) {
						String contactAccessDropdown = getSelectedOptionOfDropDown(driver,
								fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropodown list", "text");
						if (contactAccessDropdown.contains("All Folders")) {
							appLog.info("All folders is selected in the contact access view dropdown");
						} else {
							appLog.info("All folders is not selected in the Contact access view dropodwn ");
							sa.assertTrue(false, "All folders is not selected in the Contact access view dropodwn ");
						}
						if (fp.verifyManageEmailGrid(M10Contact1FirstName+"RUP" + " " + M10Contact1LastName+"RUP", M10Contact1EmailId,
								M10Institution1+"RUP", null)) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
						}
						if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName, M10Contact2EmailId,
								M10Institution2, null)) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
						}
						if (click(driver, fp.getManageEmailCancelBtn(60), "Manage Email Cancel Button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on cancel button");
						} else {
							appLog.info("Clicked on cancel button");
							sa.assertTrue(false, "clicked on cancel button");
						}
					} else {
						appLog.info("Not able to clcik on manage email");
						sa.assertTrue(false, "Not able to click on manage email");
					}
				} else {
					appLog.info("Not able to find folder path");
					sa.assertTrue(false, "Not able to find folder path");
				}
				if (fp.verifyFolderPathdummy(internalPath[1], null, null, M10FundName1, PageName.FundsPage,
						Workspace.InvestorWorkspace, 60)) {
					if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage email icon",
							action.SCROLLANDBOOLEAN)) {
						String contactAccessDropdown = getSelectedOptionOfDropDown(driver,
								fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropodown list", "text");
						if (contactAccessDropdown.contains("All Folders")) {
							appLog.info("All folders is selected in the contact access view dropdown");
						} else {
							appLog.info("All folders is not selected in the Contact access view dropodwn ");
							sa.assertTrue(false, "All folders is not selected in the Contact access view dropodwn ");
						}
						if (fp.verifyManageEmailGrid(M10Contact1FirstName+"RUP" + " " + M10Contact1LastName+"RUP", M10Contact1EmailId,
								M10Institution1+"RUP", null)) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
						}
						if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName, M10Contact2EmailId,
								M10Institution2, null)) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
						}
						if (click(driver, fp.getManageEmailCancelBtn(60), "Manage Email Cancel Button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on cancel button");
						} else {
							appLog.info("Clicked on cancel button");
							sa.assertTrue(false, "clicked on cancel button");
						}
					} else {
						appLog.info("Not able to clcik on manage email");
						sa.assertTrue(false, "Not able to click on manage email");
					}
				} else {
					appLog.info("Not able to find folder path");
					sa.assertTrue(false, "Not able to find folder path");
				}
				if (fp.verifyFolderPathdummy(sharedPath[0], null, null, M10FundName1, PageName.FundsPage,
						Workspace.InvestorWorkspace, 60)) {
					if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage email icon",
							action.SCROLLANDBOOLEAN)) {
						String contactAccessDropdown = getSelectedOptionOfDropDown(driver,
								fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropodown list", "text");
						if (contactAccessDropdown.contains(sharedPath[0])) {
							appLog.info(sharedPath[0] + "  is selected in the contact access view dropdown");
						} else {
							appLog.info(sharedPath[0] + " is not selected in the Contact access view dropodwn ");
							sa.assertTrue(false,
									sharedPath[0] + "  is not selected in the Contact access view dropodwn ");
						}
						List<WebElement> contactAccessViewDropdownValues = allOptionsInDropDrop(driver,
								fp.getManageEmailContactAccessViewDropDownList(60), "Show dropdown values");
						for (int i = 0; i < contactAccessViewDropdownValues.size(); i++) {
							if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase(sharedPath[0])) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else
								if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase("All Folders")) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
								sa.assertTrue(false, contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
							}
						}
						sa.assertTrue(
								fp.getContactAccessManageEmailGridMsg(30).getText().trim()
										.contains(FundsPageErrorMessage.noDataToDisplayErrorMessage),
								"Manage Emails Contacts Grid Error Message is not verified. Expected: "
										+ FundsPageErrorMessage.noDataToDisplayErrorMessage);
						if (selectVisibleTextFromDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropdown list", "All Folders")) {
							contactAccessDropdown = getSelectedOptionOfDropDown(driver,
									fp.getManageEmailContactAccessViewDropDownList(60),
									"Contact access view dropodown list", "text");
							if (contactAccessDropdown.contains("All Folders")) {
								appLog.info("All folders is selected in the contact access view dropdown");
							} else {
								appLog.info("All folders is not selected in the Contact access view dropodwn ");
								sa.assertTrue(false,
										"All folders is not selected in the Contact access view dropodwn ");
							}
							if (fp.verifyManageEmailGrid(M10Contact1FirstName+"RUP" + " " + M10Contact1LastName+"RUP", M10Contact1EmailId,
									M10Institution1+"RUP", null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							}
							if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName, M10Contact2EmailId,
									M10Institution2, null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							}
						} else {
							appLog.info("Not able to select value from the dropdown");
							sa.assertTrue(false, "Not able to select value from the dropodown");
						}
						if (click(driver, fp.getManageEmailCancelBtn(60), "Manage Email Cancel Button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on cancel button");
						} else {
							appLog.info("Clicked on cancel button");
							sa.assertTrue(false, "clicked on cancel button");
						}
					} else {
						appLog.info("Not able to clcik on manage email");
						sa.assertTrue(false, "Not able to click on manage email");
					}
				} else {
					appLog.info("Not able to find folder path");
					sa.assertTrue(false, "Not able to find folder path");
				}
				if (fp.verifyFolderPathdummy(sharedPath[1], null, null, M10FundName1, PageName.FundsPage,
						Workspace.InvestorWorkspace, 60)) {
					if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage email icon",
							action.SCROLLANDBOOLEAN)) {
						String contactAccessDropdown = getSelectedOptionOfDropDown(driver,
								fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropodown list", "text");
						if (contactAccessDropdown.contains((sharedPath[1].split("/"))[1])) {
							appLog.info((sharedPath[1].split("/"))[1]
									+ "  is selected in the contact access view dropdown");
						} else {
							appLog.info((sharedPath[1].split("/"))[1]
									+ " is not selected in the Contact access view dropodwn ");
							sa.assertTrue(false, (sharedPath[1].split("/"))[1]
									+ "  is not selected in the Contact access view dropodwn ");
						}
						List<WebElement> contactAccessViewDropdownValues = allOptionsInDropDrop(driver,
								fp.getManageEmailContactAccessViewDropDownList(60), "Show dropdown values");
						for (int i = 0; i < contactAccessViewDropdownValues.size(); i++) {
							if (contactAccessViewDropdownValues.get(i).getText()
									.equalsIgnoreCase((sharedPath[1].split("/"))[1])) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else
								if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase("All Folders")) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
								sa.assertTrue(false, contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
							}
						}
						if (fp.verifyManageEmailGrid(M10Contact1FirstName+"RUP" + " " + M10Contact1LastName+"RUP", M10Contact1EmailId,
								M10Institution1+"RUP", null)) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
						}
					if (selectVisibleTextFromDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropdown list", "All Folders")) {
							contactAccessDropdown = getSelectedOptionOfDropDown(driver,
									fp.getManageEmailContactAccessViewDropDownList(60),
									"Contact access view dropodown list", "text");
							if (contactAccessDropdown.contains("All Folders")) {
								appLog.info("All folders is selected in the contact access view dropdown");
							} else {
								appLog.info("All folders is not selected in the Contact access view dropodwn ");
								sa.assertTrue(false,
										"All folders is not selected in the Contact access view dropodwn ");
							}
							if (fp.verifyManageEmailGrid(M10Contact1FirstName+"RUP" + " " + M10Contact1LastName+"RUP", M10Contact1EmailId,
									M10Institution1+"RUP", null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							}
							if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName, M10Contact2EmailId,
									M10Institution2, null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							}
						} else {
							appLog.info("Not able to select value from the dropdown");
							sa.assertTrue(false, "Not able to select value from the dropodown");
						}
						if (click(driver, fp.getManageEmailCancelBtn(60), "Manage Email Cancel Button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on cancel button");
						} else {
							appLog.info("Clicked on cancel button");
							sa.assertTrue(false, "clicked on cancel button");
						}
					} else {
						appLog.info("Not able to clcik on manage email");
						sa.assertTrue(false, "Not able to click on manage email");
					}
				} else {
					appLog.info("Not able to find folder path");
					sa.assertTrue(false, "Not able to find folder path");
				}

				if (fp.verifyFolderPathdummy("", M10Institution1, null, M10FundName1, PageName.FundsPage,
						Workspace.InvestorWorkspace, 60)) {
					if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage email icon",
							action.SCROLLANDBOOLEAN)) {
						String contactAccessDropdown = getSelectedOptionOfDropDown(driver,
								fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropodown list", "text");
						if (contactAccessDropdown.contains(M10Institution1)) {
							appLog.info(M10Institution1 + "  is selected in the contact access view dropdown");
						} else {
							appLog.info(M10Institution1 + " is not selected in the Contact access view dropodwn ");
							sa.assertTrue(false,
									M10Institution1 + "  is not selected in the Contact access view dropodwn ");
						}
						List<WebElement> contactAccessViewDropdownValues = allOptionsInDropDrop(driver,
								fp.getManageEmailContactAccessViewDropDownList(60), "Show dropdown values");
						for (int i = 0; i < contactAccessViewDropdownValues.size(); i++) {
							if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase(M10Institution1)) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else
								if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase("All Folders")) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
								sa.assertTrue(false, contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
							}
						}
						if (fp.verifyManageEmailGrid(M10Contact1FirstName+"RUP" + " " + M10Contact1LastName+"RUP", M10Contact1EmailId,
								M10Institution1+"RUP", null)) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
						}
					
						if (selectVisibleTextFromDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropdown list", "All Folders")) {
							contactAccessDropdown = getSelectedOptionOfDropDown(driver,
									fp.getManageEmailContactAccessViewDropDownList(60),
									"Contact access view dropodown list", "text");
							if (contactAccessDropdown.contains("All Folders")) {
								appLog.info("All folders is selected in the contact access view dropdown");
							} else {
								appLog.info("All folders is not selected in the Contact access view dropodwn ");
								sa.assertTrue(false,
										"All folders is not selected in the Contact access view dropodwn ");
							}
							if (fp.verifyManageEmailGrid(M10Contact1FirstName+"RUP" + " " + M10Contact1LastName+"RUP", M10Contact1EmailId,
									M10Institution1+"RUP", null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							}
							if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName, M10Contact2EmailId,
									M10Institution2, null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							}
						} else {
							appLog.info("Not able to select value from the dropdown");
							sa.assertTrue(false, "Not able to select value from the dropodown");
						}
						if (click(driver, fp.getManageEmailCancelBtn(60), "Manage Email Cancel Button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on cancel button");
						} else {
							appLog.info("Clicked on cancel button");
							sa.assertTrue(false, "clicked on cancel button");
						}
					} else {
						appLog.info("Not able to clcik on manage email");
						sa.assertTrue(false, "Not able to click on manage email");
					}
				} else {
					appLog.info("Not able to find folder path");
					sa.assertTrue(false, "Not able to find folder path");
				}
				if (fp.verifyFolderPathdummy("", M10Institution1, M10LimitedPartner1, M10FundName1, PageName.FundsPage,
						Workspace.InvestorWorkspace, 60)) {
					if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage email icon",
							action.SCROLLANDBOOLEAN)) {
						String contactAccessDropdown = getSelectedOptionOfDropDown(driver,
								fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropodown list", "text");
						if (contactAccessDropdown.contains(M10LimitedPartner1)) {
							appLog.info(M10Institution1 + "  is selected in the contact access view dropdown");
						} else {
							appLog.info(M10Institution1 + " is not selected in the Contact access view dropodwn ");
							sa.assertTrue(false,
									M10Institution1 + "  is not selected in the Contact access view dropodwn ");
						}
						List<WebElement> contactAccessViewDropdownValues = allOptionsInDropDrop(driver,
								fp.getManageEmailContactAccessViewDropDownList(60), "Show dropdown values");
						for (int i = 0; i < contactAccessViewDropdownValues.size(); i++) {
							if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase(M10LimitedPartner1)) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else
								if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase("All Folders")) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
								sa.assertTrue(false, contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
							}
						}
						if (fp.verifyManageEmailGrid(M10Contact1FirstName+"RUP" + " " + M10Contact1LastName+"RUP", M10Contact1EmailId,
								M10Institution1+"RUP", null)) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
						}
					
						if (selectVisibleTextFromDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropdown list", "All Folders")) {
							contactAccessDropdown = getSelectedOptionOfDropDown(driver,
									fp.getManageEmailContactAccessViewDropDownList(60),
									"Contact access view dropodown list", "text");
							if (contactAccessDropdown.contains("All Folders")) {
								appLog.info("All folders is selected in the contact access view dropdown");
							} else {
								appLog.info("All folders is not selected in the Contact access view dropodwn ");
								sa.assertTrue(false,
										"All folders is not selected in the Contact access view dropodwn ");
							}
							if (fp.verifyManageEmailGrid(M10Contact1FirstName+"RUP" + " " + M10Contact1LastName+"RUP", M10Contact1EmailId,
									M10Institution1+"RUP", null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							}
							if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName, M10Contact2EmailId,
									M10Institution2, null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							}
						} else {
							appLog.info("Not able to select value from the dropdown");
							sa.assertTrue(false, "Not able to select value from the dropodown");
						}
						if (click(driver, fp.getManageEmailCancelBtn(60), "Manage Email Cancel Button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on cancel button");
						} else {
							appLog.info("Clicked on cancel button");
							sa.assertTrue(false, "clicked on cancel button");
						}
					} else {
						appLog.info("Not able to clcik on manage email");
						sa.assertTrue(false, "Not able to click on manage email");
					}
				} else {
					appLog.info("Not able to find folder path");
					sa.assertTrue(false, "Not able to find folder path");
				}		
				if (fp.verifyFolderPathdummy(stdPath[0], M10Institution1, M10LimitedPartner1, M10FundName1, PageName.FundsPage,
						Workspace.InvestorWorkspace, 60)) {
					if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage email icon",
							action.SCROLLANDBOOLEAN)) {
						String contactAccessDropdown = getSelectedOptionOfDropDown(driver,
								fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropodown list", "text");
						if (contactAccessDropdown.contains(stdPath[0])) {
							appLog.info(stdPath[0] + "  is selected in the contact access view dropdown");
						} else {
							appLog.info(stdPath[0] + " is not selected in the Contact access view dropodwn ");
							sa.assertTrue(false, stdPath[0] + "  is not selected in the Contact access view dropodwn ");
						}
						List<WebElement> contactAccessViewDropdownValues = allOptionsInDropDrop(driver,
								fp.getManageEmailContactAccessViewDropDownList(60), "Show dropdown values");
						for (int i = 0; i < contactAccessViewDropdownValues.size(); i++) {
							if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase(stdPath[0])) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else
								if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase("All Folders")) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
								sa.assertTrue(false, contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
							}
						}
						if (fp.verifyManageEmailGrid(M10Contact1FirstName+"RUP" + " " + M10Contact1LastName+"RUP", M10Contact1EmailId,
								M10Institution1+"RUP", null)) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
						}
						
						if (selectVisibleTextFromDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropdown list", "All Folders")) {
							contactAccessDropdown = getSelectedOptionOfDropDown(driver,
									fp.getManageEmailContactAccessViewDropDownList(60),
									"Contact access view dropodown list", "text");
							if (contactAccessDropdown.contains("All Folders")) {
								appLog.info("All folders is selected in the contact access view dropdown");
							} else {
								appLog.info("All folders is not selected in the Contact access view dropodwn ");
								sa.assertTrue(false,
										"All folders is not selected in the Contact access view dropodwn ");
							}
							if (fp.verifyManageEmailGrid(M10Contact1FirstName+"RUP" + " " + M10Contact1LastName+"RUP", M10Contact1EmailId,
									M10Institution1+"RUP", null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							}
							if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName, M10Contact2EmailId,
									M10Institution2, null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							}
						} else {
							appLog.info("Not able to select value from the dropdown");
							sa.assertTrue(false, "Not able to select value from the dropodown");
						}
						if (click(driver, fp.getManageEmailCancelBtn(60), "Manage Email Cancel Button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on cancel button");
						} else {
							appLog.info("Clicked on cancel button");
							sa.assertTrue(false, "clicked on cancel button");
						}
					} else {
						appLog.info("Not able to clcik on manage email");
						sa.assertTrue(false, "Not able to click on manage email");
					}
				} else {
					appLog.info("Not able to find folder path");
					sa.assertTrue(false, "Not able to find folder path");
				}
				if (fp.verifyFolderPathdummy("", M10Institution2, null, M10FundName1, PageName.FundsPage,
						Workspace.InvestorWorkspace, 60)) {
					if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage email icon",
							action.SCROLLANDBOOLEAN)) {
						String contactAccessDropdown = getSelectedOptionOfDropDown(driver,
								fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropodown list", "text");
						if (contactAccessDropdown.contains(M10Institution2)) {
							appLog.info(M10Institution2 + "  is selected in the contact access view dropdown");
						} else {
							appLog.info(M10Institution2 + " is not selected in the Contact access view dropodwn ");
							sa.assertTrue(false,
									M10Institution2 + "  is not selected in the Contact access view dropodwn ");
						}
						List<WebElement> contactAccessViewDropdownValues = allOptionsInDropDrop(driver,
								fp.getManageEmailContactAccessViewDropDownList(60), "Show dropdown values");
						for (int i = 0; i < contactAccessViewDropdownValues.size(); i++) {
							if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase(M10Institution2)) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else
								if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase("All Folders")) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
								sa.assertTrue(false, contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
							}
						}
						sa.assertTrue(
								fp.getContactAccessManageEmailGridMsg(30).getText().trim()
										.contains(FundsPageErrorMessage.noDataToDisplayErrorMessage),
								"Manage Emails Contacts Grid Error Message is not verified. Expected: "
										+ FundsPageErrorMessage.noDataToDisplayErrorMessage);
						if (selectVisibleTextFromDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropdown list", "All Folders")) {
							contactAccessDropdown = getSelectedOptionOfDropDown(driver,
									fp.getManageEmailContactAccessViewDropDownList(60),
									"Contact access view dropodown list", "text");
							if (contactAccessDropdown.contains("All Folders")) {
								appLog.info("All folders is selected in the contact access view dropdown");
							} else {
								appLog.info("All folders is not selected in the Contact access view dropodwn ");
								sa.assertTrue(false,
										"All folders is not selected in the Contact access view dropodwn ");
							}
							if (fp.verifyManageEmailGrid(M10Contact1FirstName+"RUP" + " " + M10Contact1LastName+"RUP", M10Contact1EmailId,
									M10Institution1+"RUP", null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							}
							if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName, M10Contact2EmailId,
									M10Institution2, null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							}
						} else {
							appLog.info("Not able to select value from the dropdown");
							sa.assertTrue(false, "Not able to select value from the dropodown");
						}
						if (click(driver, fp.getManageEmailCancelBtn(60), "Manage Email Cancel Button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on cancel button");
						} else {
							appLog.info("Clicked on cancel button");
							sa.assertTrue(false, "clicked on cancel button");
						}
					} else {
						appLog.info("Not able to clcik on manage email");
						sa.assertTrue(false, "Not able to click on manage email");
					}
				} else {
					appLog.info("Not able to find folder path");
					sa.assertTrue(false, "Not able to find folder path");
				}
				if (fp.verifyFolderPathdummy("", M10Institution2, M10LimitedPartner2, M10FundName1, PageName.FundsPage,
						Workspace.InvestorWorkspace, 60)) {
					if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage email icon",
							action.SCROLLANDBOOLEAN)) {
						String contactAccessDropdown = getSelectedOptionOfDropDown(driver,
								fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropodown list", "text");
						if (contactAccessDropdown.contains(M10LimitedPartner2)) {
							appLog.info(M10Institution2 + "  is selected in the contact access view dropdown");
						} else {
							appLog.info(M10Institution2 + " is not selected in the Contact access view dropodwn ");
							sa.assertTrue(false,
									M10Institution2 + "  is not selected in the Contact access view dropodwn ");
						}
						List<WebElement> contactAccessViewDropdownValues = allOptionsInDropDrop(driver,
								fp.getManageEmailContactAccessViewDropDownList(60), "Show dropdown values");
						for (int i = 0; i < contactAccessViewDropdownValues.size(); i++) {
							if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase(M10LimitedPartner2)) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else
								if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase("All Folders")) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
								sa.assertTrue(false, contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
							}
						}
						sa.assertTrue(
								fp.getContactAccessManageEmailGridMsg(30).getText().trim()
										.contains(FundsPageErrorMessage.noDataToDisplayErrorMessage),
								"Manage Emails Contacts Grid Error Message is not verified. Expected: "
										+ FundsPageErrorMessage.noDataToDisplayErrorMessage);
						if (selectVisibleTextFromDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropdown list", "All Folders")) {
							contactAccessDropdown = getSelectedOptionOfDropDown(driver,
									fp.getManageEmailContactAccessViewDropDownList(60),
									"Contact access view dropodown list", "text");
							if (contactAccessDropdown.contains("All Folders")) {
								appLog.info("All folders is selected in the contact access view dropdown");
							} else {
								appLog.info("All folders is not selected in the Contact access view dropodwn ");
								sa.assertTrue(false,
										"All folders is not selected in the Contact access view dropodwn ");
							}
							if (fp.verifyManageEmailGrid(M10Contact1FirstName+"RUP" + " " + M10Contact1LastName+"RUP", M10Contact1EmailId,
									M10Institution1+"RUP", null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							}
							if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName, M10Contact2EmailId,
									M10Institution2, null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							}
						} else {
							appLog.info("Not able to select value from the dropdown");
							sa.assertTrue(false, "Not able to select value from the dropodown");
						}
						if (click(driver, fp.getManageEmailCancelBtn(60), "Manage Email Cancel Button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on cancel button");
						} else {
							appLog.info("Clicked on cancel button");
							sa.assertTrue(false, "clicked on cancel button");
						}
					} else {
						appLog.info("Not able to clcik on manage email");
						sa.assertTrue(false, "Not able to click on manage email");
					}
				} else {
					appLog.info("Not able to find folder path");
					sa.assertTrue(false, "Not able to find folder path");
				}				
				if (fp.verifyFolderPathdummy(stdPath[0], M10Institution2, M10LimitedPartner2, M10FundName1, PageName.FundsPage,
						Workspace.InvestorWorkspace, 60)) {
					if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage email icon",
							action.SCROLLANDBOOLEAN)) {
						String contactAccessDropdown = getSelectedOptionOfDropDown(driver,
								fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropodown list", "text");
						if (contactAccessDropdown.contains(stdPath[0])) {
							appLog.info(stdPath[0] + "  is selected in the contact access view dropdown");
						} else {
							appLog.info(stdPath[0] + " is not selected in the Contact access view dropodwn ");
							sa.assertTrue(false, stdPath[0] + "  is not selected in the Contact access view dropodwn ");
						}
						List<WebElement> contactAccessViewDropdownValues = allOptionsInDropDrop(driver,
								fp.getManageEmailContactAccessViewDropDownList(60), "Show dropdown values");
						for (int i = 0; i < contactAccessViewDropdownValues.size(); i++) {
							if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase(stdPath[0])) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else
								if (contactAccessViewDropdownValues.get(i).getText().equalsIgnoreCase("All Folders")) {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is available in the list.");
							} else {
								appLog.info(contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
								sa.assertTrue(false, contactAccessViewDropdownValues.get(i).getText()
										+ " :Opiton is not available in the list.");
							}
						}
						if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName, M10Contact2EmailId,
								M10Institution2, null)) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
						}
						if (selectVisibleTextFromDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60),
								"Contact access view dropdown list", "All Folders")) {
							contactAccessDropdown = getSelectedOptionOfDropDown(driver,
									fp.getManageEmailContactAccessViewDropDownList(60),
									"Contact access view dropodown list", "text");
							if (contactAccessDropdown.contains("All Folders")) {
								appLog.info("All folders is selected in the contact access view dropdown");
							} else {
								appLog.info("All folders is not selected in the Contact access view dropodwn ");
								sa.assertTrue(false,
										"All folders is not selected in the Contact access view dropodwn ");
							}
							if (fp.verifyManageEmailGrid(M10Contact1FirstName+"RUP" + " " + M10Contact1LastName+"RUP", M10Contact1EmailId,
									M10Institution1+"RUP", null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							}
							if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName, M10Contact2EmailId,
									M10Institution2, null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							}
						} else {
							appLog.info("Not able to select value from the dropdown");
							sa.assertTrue(false, "Not able to select value from the dropodown");
						}
						if (click(driver, fp.getManageEmailCancelBtn(60), "Manage Email Cancel Button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on cancel button");
						} else {
							appLog.info("Clicked on cancel button");
							sa.assertTrue(false, "clicked on cancel button");
						}
					} else {
						appLog.info("Not able to clcik on manage email");
						sa.assertTrue(false, "Not able to click on manage email");
					}
				} else {
					appLog.info("Not able to find folder path");
					sa.assertTrue(false, "Not able to find folder path");
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
		lp.CRMlogout(environment, mode);
		sa.assertAll();		
	}
	
	@Test
	public void M10tc020_VerifyConfirmationPopUpReceivedMailAtInvestorSideVerifyCreateActivityAtContactAndAccountPage(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		WebElement ele = null;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M10FundName1+"NUP")) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30),
						"Investor  Section view");
				if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Fundarising workspace",
						action.SCROLLANDBOOLEAN)) {
					ele = FindElement(driver, "//a[text()='" + M10Contact1EmailId + "']", "Contact 1 Email id",
							action.SCROLLANDBOOLEAN, 60);
					if (click(driver, ele, "Contact1 ", action.SCROLLANDBOOLEAN)) {
						if (!new NIMPageBusinessLayer(driver)
								.verifyNavatarSalesTeamLinkFunctionality("ContactEmailLink")) {
							appLog.info("Verification of Contact Email link is unsuccessfull.");
							sa.assertTrue(false, "Verification of Contact Email link is unsuccessfull.");
						} else {
							appLog.info("Verification of Contact Email Link is successfully");
						}
					} else {
						appLog.info("Not able to click on contact 1 checkbox");
						sa.assertTrue(false, "Not able to click on contat1 checkbox");
					}
					if (click(driver, fp.getManageEMailContactAllCheckBox(30), "Manage Email All Contact Check Box",
							action.SCROLLANDBOOLEAN)) {
						List<WebElement> listofcontact = fp.getManageEmailListOfContactCheckBox();
						for (int i = 0; i < listofcontact.size(); i++) {
							if (isSelected(driver, listofcontact.get(i), "Contact Check Box")) {
								appLog.info("Contact Check Box is Selected.");
							} else {
								appLog.info("Contact Check Box is not selected");
								sa.assertTrue(false, "Contact Check Box is not selected");
							}
						}
					} else {
						appLog.info("Not able to clcik on select all checkbox");
						sa.assertTrue(false, "Not able to click on select all checkbox");
					}
					WebElement contactcheckbox = FindElement(driver,"//a[text()='" + M10Contact2FirstName + " " + M10Contact2LastName+ "']/../..//input","Contact Check Box", action.SCROLLANDBOOLEAN, 30);
					if (contactcheckbox != null) {
						if (click(driver, contactcheckbox, "Manage Emails Contact Check Box",
								action.SCROLLANDBOOLEAN)) {
							if (isSelected(driver, fp.getManageEMailContactAllCheckBox(30),
									"Manage Emails All Contact Check Box")) {
								appLog.info("Manage Email All Contact Check Box is selected after uncheck contact.");
								sa.assertTrue(false,
										"Manage Email All Contact Check Box is selected after uncheck contact.");
							} else {
								appLog.info(
										"Manage Email All Contact Check Box is not selected after uncheck contact.");
							}
						} else {
							appLog.info("Not able to click on contact checkbox");
							sa.assertTrue(false, "Not able to clciik on manage email text box");
						}
					} else {
						appLog.info("Contact Check Box is not available on Manage Email Contact");
						sa.assertTrue(false, "Contact Check Box is not available on Manage Email Contact");
					}
					if (click(driver, fp.getManageEMailContactAllCheckBox(30), "Manage Email All Contact Check Box",
							action.SCROLLANDBOOLEAN)) {
						ThreadSleep(2000);
						if (click(driver, fp.getManageEMailContactAllCheckBox(30), "Manage Email All Contact Check Box",
								action.SCROLLANDBOOLEAN)) {
							ThreadSleep(2000);
							if ( fp.clickUsingCssSelectorPath("a[title=Send]", "send button")) {
								
							//if (click(driver, fp.getmanageEmailsendBtn(30), "Manage Email Send Button",
							//		action.SCROLLANDBOOLEAN)) {
								String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
								if (msg.equalsIgnoreCase(
										FundsPageErrorMessage.pleaseSelectOneInvestorErrorMessageInManageEmails)) {
									appLog.info("Please select one invetsor erro message is verified");
								} else {
									appLog.info("Please select one invetsor erro message is not verified");
									sa.assertTrue(false, "Please select one invetsor erro message is not verified");
								}
								switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
							} else {
								appLog.info("Not able to click on send button");
								sa.assertTrue(false, "Not able to click on send button");
							}
						} else {
							appLog.info("Not able ot click on select all checkbox");
							sa.assertTrue(false, "Not able to click on select all checkbox");
						}
					} else {
						appLog.info("Not able ot click on select all checkbox");
						sa.assertTrue(false, "Not able to click on select all checkbox");
					}
					ele = FindElement(driver,
							"//a[text()='" + M10Contact1FirstName+"RUP" + " " + M10Contact1LastName+"RUP" + "']/../..//input",
							"Contact Check Box", action.SCROLLANDBOOLEAN, 30);
					if (click(driver, ele, "Contact1 ", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getmanageEmailsendBtn(30), "Manage Email Send Button",
								action.SCROLLANDBOOLEAN)) {
							if (fp.getManageEmailSendInvitationConfirmationCountErrorMsg(30).getText().trim()
									.contains("1")) {
								appLog.info("Manage Emails Contact Send Invitation Confirmation Count is  verified");
							} else {
								appLog.info("Manage Emails Contact Send Invitation Confirmation Count is not verified");
								sa.assertTrue(false,
										"Manage Emails Contact Send Invitation Confirmation Count is not verified");
							}
							if (fp.getManageEmailSendInvitationConfirmationErrorMsg(30).getText().trim()
									.contains(FundsPageErrorMessage.ManageEmailSendContactInvitationErrorMsg1)) {
								appLog.info(
										"Manage Emails Contact Send Invitation Confirmation Error Message is verified.");
							} else {
								appLog.info(
										"Manage Emails Contact Send Invitation Confirmation Error Message is not verified.");
								sa.assertTrue(false,
										"Manage Emails Contact Send Invitation Confirmation Error Message is not verified.");
							}
							if (fp.getManageEmailSendInvitationConfirmationErrorMsg(30).getText().trim()
									.contains(FundsPageErrorMessage.ManageEmailSendContactInvitationErrorMsg1)) {
								appLog.info(
										"Manage Emails Contact Send Invitation Confirmation Error Message is verified.");
							} else {
								appLog.info(
										"Manage Emails Contact Send Invitation Confirmation Error Message is not verified.");
								sa.assertTrue(false,
										"Manage Emails Contact Send Invitation Confirmation Error Message is not verified.");
							}
							if (isDisplayed(driver, fp.getManageEmailSendInvitationConfirmationYesBtn(60), "Visibility",
									30, "Yes Button") != null) {
								appLog.info("Yes Button is displaying");
							} else {
								appLog.info("Yes Button is not displaying");
								sa.assertTrue(false, "Yes Button is not displaying");
							}
							if (isDisplayed(driver, fp.getManageEmailSendInvitationConfirmationCloseIcon(60),
									"Visibility", 30, "Close Icon") != null) {
								appLog.info("Close icon is displaying");
							} else {
								appLog.info("Close icon is not displaying");
								sa.assertTrue(false, "Close icon is not displaying");
							}
							if (isDisplayed(driver, fp.getManageEmailSendInvitationConfirmationNoBtn(60), "Visibility",
									30, "No Button") != null) {
								appLog.info("No Button is displaying");
							} else {
								appLog.info("No Button is not displaying");
								sa.assertTrue(false, "No Button is not displaying");
							}
							if (click(driver, fp.getManageEmailSendInvitationConfirmationCloseIcon(60), "Close icon",
									action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on close icon");
								if (fp.getManageEmailSendInvitationConfirmationCloseIcon(20) == null) {
									appLog.info("Confirmation Pop up is clsoed");
								} else {
									appLog.info("Confirmation Pop up is clsoed");
									sa.assertTrue(false, "Confirmation Pop up is not clsoed");
								}
							} else {
								appLog.info("Not able to click on close icon");
								sa.assertTrue(false, "Not able to clcik on close icon");
							}
						} else {
							appLog.info("Not able to click on send button");
							sa.assertTrue(false, "Not able to click on send button");
						}
							if (click(driver, fp.getmanageEmailsendBtn(30), "Manage Email Send Button",
									action.SCROLLANDBOOLEAN)) {
								if (click(driver, fp.getManageEmailSendInvitationConfirmationNoBtn(60), "No Button",
										action.SCROLLANDBOOLEAN)) {
									appLog.info("Clicked on No Button");
									if (fp.getManageEmailSendInvitationConfirmationNoBtn(20) == null) {
										appLog.info("Confirmation Pop up is clsoed");
									} else {
										appLog.info("Confirmation Pop up is clsoed");
										sa.assertTrue(false, "Confirmation Pop up is not clsoed");
									}
								} else {
									appLog.info("Not able to click on No button");
									sa.assertTrue(false, "Not able to clcik on No button");
								}
							} else {
								appLog.info("Not able to click on send button");
								sa.assertTrue(false, "Not able to click on send button");
							}
							if(click(driver, fp.getManageEmailEmailLabel(60), "Email label", action.SCROLLANDBOOLEAN)){
							if (click(driver, fp.getmanageEmailsendBtn(30), "Manage Email Send Button",
									action.SCROLLANDBOOLEAN)) {
								if (click(driver, fp.getManageEmailSendInvitationConfirmationYesBtn(60), "Yes Button",
										action.SCROLLANDBOOLEAN)) {
									appLog.info("Clicked on yes Button");
									if (fp.getManageEmailSendInvitationConfirmationYesBtn(20) == null) {
										appLog.info("Confirmation Pop up is clsoed");
									} else {
										appLog.info("Confirmation Pop up is clsoed");
										sa.assertTrue(false, "Confirmation Pop up is not clsoed");
									}
									if (fp.verifyManageEmailGrid(M10Contact1FirstName+"RUP" + " " + M10Contact1LastName+"RUP",
											M10Contact1EmailId, M10Institution1+"RUP", "Last Invite Date")) {
										appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName
												+ " " + M10Contact1LastName);
									} else {
										appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName
												+ " " + M10Contact1LastName);
										sa.assertTrue(false, "Grid Data is not verified for the contact: "
												+ M10Contact1FirstName + " " + M10Contact1LastName);
									}
									if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,
											M10Contact2EmailId, M10Institution2, null)) {
										appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName
												+ " " + M10Contact2LastName);
									} else {
										appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName
												+ " " + M10Contact2LastName);
										sa.assertTrue(false, "Grid Data is not verified for the contact: "
												+ M10Contact2FirstName + " " + M10Contact2LastName);
									}
									contactcheckbox = isDisplayed(driver,
											FindElement(driver,
													"//a[text()='" + M10Contact2FirstName + " " + M10Contact2LastName
															+ "']/../..//input",
													"Contact Check Box", action.SCROLLANDBOOLEAN, 30),
											"visibility", 30, "Manage Emails Contact Check Box");
									if (isSelected(driver, contactcheckbox, "Manage Emails Contact 2 Check Box")) {
										appLog.info("Manage Email Contact 2 Check Box is selected");
										sa.assertTrue(false, "Manage Email Contact 2 Check Box is selected");
									} else {
										appLog.info("Manage Email Contact 2 Check Box is not selected");
									}
									WebElement contact1CheckBox = isDisplayed(driver,
											FindElement(driver,
													"//a[text()='" + M10Contact1FirstName+"RUP" + " " + M10Contact1LastName+"RUP"
															+ "']/../..//input",
													"Contact Check Box", action.SCROLLANDBOOLEAN, 30),
											"visibility", 30, "Manage Emails Contact Check Box");
									if (isSelected(driver, contact1CheckBox, "Manage Emails Contact 1 Check Box")) {
										appLog.info("Manage Email Contact 1 Check Box is selected");
										sa.assertTrue(false, "Manage Email Contact 1 Check Box is selected");
									} else {
										appLog.info("Manage Email Contact 1 Check Box is not selected");
									}
								} else {
									appLog.info("Not able to click on Yes button");
									sa.assertTrue(false, "Not able to clcik on Yes button");
								}
							} else {
								appLog.info("Not able to click on send button");
								sa.assertTrue(false, "Not able to click on send button");
							}
							}else{
								appLog.error("Not able ot click on email label");
								sa.assertTrue(false, "Not able to click on email label");
							}
					} else {
						appLog.info("Not able to click on contact1 checkbox");
						sa.assertTrue(false, "Not able ot click on contact1 checkbox");
					}
					ele = FindElement(driver, "//a[text()='" + M10Contact1FirstName+"RUP" + " " + M10Contact1LastName+"RUP" + "']",
							"Contact 1", action.SCROLLANDBOOLEAN, 30);
					if (click(driver, ele, "Contact 1 name", action.SCROLLANDBOOLEAN)) {
						String parentID = switchOnWindow(driver);
						if (parentID != null) {
							scrollDownThroughWebelement(driver, cp.getContactActivityHistory(30), "Activity History");
							if (cp.getContactActivityAlertAccountNameText(30).getText().trim()
									.contains(M10Institution1)) {
								appLog.info("Activity Alert is available in Contact page.");
							} else {
								appLog.info("Activity Alert is not available in Contact page.");
								sa.assertTrue(false, "Activity Alert is not available in Contact page.");
							}
							if (click(driver, cp.getContactAcitivityAlertSubjectLink(30),
									"Contact Activity Alert Subject Link", action.SCROLLANDBOOLEAN)) {
								if (cp.getContactAcitivityAlertAssignedToEmailText(30).getText().trim()
										.contains(CRMUser1LastName)) {
									appLog.info("Assigned To user name is verified.");
								} else {
									appLog.info("Assigned To user name is not verified.");
									sa.assertTrue(false, "Assigned To user name is not verified.");
								}
								if (cp.getContactAcitivityAlertSubjectText(30).getText().trim()
										.contains("Invitation from")) {
									appLog.info("Subject Text is  verified.");
								} else {
									appLog.info("Subject Text is not verified.");
									sa.assertTrue(false, "Subject Text is not verified.");
								}
								if (cp.getContactAcitivityAlertSubjectText(30).getText().trim()
										.contains(Org1FirmName+"UP")) {
									appLog.info("Subject Text is  verified.");
								} else {
									appLog.info("Subject Text is not verified.");
									sa.assertTrue(false, "Subject Text is not verified.");
								}
								String date = cp.getContactActivityAlertDueDateText(30).getText().trim();
								if (bp.verifyDate(date, "Due")) {
									appLog.info("Due Date is verified.");
								} else {
									appLog.info("Due Date is not verified. Actual Result: " + date);
									sa.assertTrue(false, "Due Date is not verified. Actual Result: " + date);
								}
								if (cp.getContactAcitivityAlertCommentsText(30).getText().trim().contains("Subject:")) {
									appLog.info("Comments Text is verified.");
								} else {
									appLog.info("Comments Text is not verified.");
									sa.assertTrue(false, "Comments Text is not verified.");
								}
								if (cp.getContactAcitivityAlertCommentsText(30).getText().trim()
										.contains("Invitation from " + Org1FirmName+"UP")) {
									appLog.info("Comments subject Text is verified.");
								} else {
									appLog.info("Comments subject Text is not verified..");
									sa.assertTrue(false, "Comments subject Text is not verified.");
								}
								if (cp.getContactAcitivityAlertCommentsText(30).getText().trim()
										.contains("Hello " + M10Contact1FirstName)) {
									appLog.info("Comments body Text is verified.");
								} else {
									appLog.info("Comments body Text is not verified..");
									sa.assertTrue(false, "Comments body Text is not verified.");
								}
								if (cp.getContactAcitivityAlertCommentsText(30).getText().trim()
										.contains("You have been granted access to Current investments of "+ M10FundName1+"UP" + " by")) {
									appLog.info("Comments body Text is verified.");
								} else {
									appLog.info("Comments body Text is not verified..");
									sa.assertTrue(false, "Comments body Text is not verified.");
								}
								if (cp.getContactAcitivityAlertCommentsText(30).getText().trim()
										.contains("If you have not yet registered, Click here to register.")) {
									appLog.info("Comments body Text is verified.");
								} else {
									appLog.info("Comments body Text is not verified..");
									sa.assertTrue(false, "Comments body Text is not verified.");
								}
								if (cp.getContactAcitivityAlertCommentsText(30).getText().trim()
										.contains("If you have already registered, Click here to login.")) {
									appLog.info("Comments body Text is verified.");
								} else {
									appLog.info("Comments body Text is not verified..");
									sa.assertTrue(false, "Comments body Text is not verified.");
								}
								if (cp.getContactAcitivityAlertCommentsText(30).getText().trim().contains(
										"If you believe this has been sent in error, or if you cannot login, please contact")) {
									appLog.info("Comments body Text is verified.");
								} else {
									appLog.info("Comments body Text is not verified..");
									sa.assertTrue(false, "Comments body Text is not verified.");
								}
								if (cp.getContactActivityAlertStatusText(30).getText().trim().contains("Completed")) {
									appLog.info("Status text is verified.");
								} else {
									appLog.info("Status text is not verified.");
									sa.assertTrue(false, "Status text is not verified.");
								}
								if (cp.getContactActivityAlertPriorityText(30).getText().trim().contains("Normal")) {
									appLog.info("Priority text is verified.");
								} else {
									appLog.info("Priority text is not verified.");
									sa.assertTrue(false, "Priority text is not verified.");
								}
								if (cp.getAccountActivityAlertRelatedToText(60).getText().trim()
										.contains(M10Institution1)) {
									appLog.info("Institution Name is verified in Activity Alert on Institution Page.");
								} else {
									appLog.info(
											"Institution Name is not verified in Activity Alert on Institution Page.");
									sa.assertTrue(false,
											"Institution Name is not verified in Activity Alert on Institution Page.");
								}
								if (cp.getContactActivityAlertEmailIdtext(30).getText().trim()
										.contains(M10Contact1EmailId)) {
									appLog.info("Email ID is verified.");
								} else {
									appLog.info("Email ID is not verified.");
									sa.assertTrue(false, "Email ID is not verified.");
								}
								if (cp.getContactActivityAlertCreatedByText(30).getText().trim()
										.contains(CRMUser1LastName)) {
									appLog.info("Created By text is verified.");
								} else {
									appLog.info("Created By text is not verified.");
									sa.assertTrue(false, "Created By text is not verified.");
								}
								if (cp.getAccountActivityAlertNameText(30).getText().trim()
										.contains(M10Contact1LastName)) {
									appLog.info("Name text is verified.");
								} else {
									appLog.info("Name text is not verified.");
									sa.assertTrue(false, "Name text is not verified.");
								}
								String createdByDate = cp.getContactActivityAlertCreatedByDate(60).getText().trim();
								if (bp.verifyDate(createdByDate, "Created By Date")) {
									appLog.info("Cretaed by date is verified");
								} else {
									appLog.info("Created by date is not verified");
									sa.assertTrue(false, "Created by date is not verified");
								}
								if (cp.getContactActivityAlertLastModifiedName(30).getText().trim()
										.contains(CRMUser1LastName)) {
									appLog.info("Name text is verified.");
								} else {
									appLog.info("Name text is not verified.");
									sa.assertTrue(false, "Name text is not verified.");
								}
								String lastModifiedDate = cp.getContactActivityAlertLastModifiedDate(60).getText()
										.trim();
								if (bp.verifyDate(lastModifiedDate, "Last Modified Date")) {
									appLog.info("Last Modified Date is verified");
								} else {
									appLog.info("Last Modified Date is not verified");
									sa.assertTrue(false, "Last Modified Date is not verified");
								}
							} else {
								appLog.info("Not able to click on activity alert subject link");
								sa.assertTrue(false, "Not able to click on activity alert subject link");
							}
							driver.close();
							driver.switchTo().window(parentID);

						} else {
							appLog.info("No new weindow is open");
							sa.assertTrue(false, "No new window is open");
						}
					} else {
						appLog.info("Not able to click on contact 1 name");
						sa.assertTrue(false, "Not able to click on contact1 name");
					}
					switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
					scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30),
							"Investor Section view");
					ele = FindElement(driver, "//a[text()='" + M10Institution1+"RUP" + "']", "Contact 1 firm name",
							action.SCROLLANDBOOLEAN, 60);
					if (click(driver, ele, "Contact1 firm name ", action.SCROLLANDBOOLEAN)) {
						String parentID = switchOnWindow(driver);
						if (parentID != null) {
							scrollDownThroughWebelement(driver, ip.getNoteAndAttachmentText(30), "Activity History");
							List<WebElement> list = ip.getContactActivityHistoryNameRecords();
							String contactFullName = M10Contact1FirstName+ " " + M10Contact1LastName;
							String[] contactnames = contactFullName.split(",");
							if (!list.isEmpty()) {
								for (int i = 0; i < contactnames.length; i++) {
									for (int j = 0; j < list.size(); j++) {
										if (list.get(j).getText().trim().contains(contactnames[i])) {
											appLog.info("Contact Name is verified: " + contactnames[i]);
											break;
										} else {
											if (j == list.size() - 1) {
												appLog.info("Contact Name is not verified: " + contactnames[i]);
												sa.assertTrue(false,
														"Contact Name is not verified: " + contactnames[i]);
											}
										}
									}
								}
							} else {
								appLog.info("Contact Name list is not available");
								sa.assertTrue(false, "Contact Name list is not available");
							}
							List<WebElement> list1 = ip.getAccountActivityAlertSubjectEmailIdList();
							if (!list1.isEmpty()) {
								for (int i = 0; i < list1.size(); i++) {
									System.out.println("Inside Loop, iteration number: " + i);
									String text = list1.get(i).getText().trim();
									if (click(driver, list1.get(i), "Subject Email ID", action.SCROLLANDBOOLEAN)) {
										appLog.info("Clicked on Email ID: " + text);
										break;
									} else {
										if (i == list1.size() - 1) {
											appLog.info("Not able to click on Account Activity Alert Subject Email Id");
											sa.assertTrue(false,
													"Not able to click on Account Activity Alert Subject Email Id");
										}
									}
								}
							} else {
								appLog.info("Contact Email Id list is not available");
								sa.assertTrue(false, "Contact Email Id list is not available");
							}
							if (cp.getContactAcitivityAlertAssignedToEmailText(30).getText().trim()
									.contains(CRMUser1LastName)) {
								appLog.info("Assigned To user name is verified.");
							} else {
								appLog.info("Assigned To user name is not verified.");
								sa.assertTrue(false, "Assigned To user name is not verified.");
							}
							if (cp.getContactAcitivityAlertSubjectText(30).getText().trim()
									.contains("Invitation from")) {
								appLog.info("Subject Text is  verified.");
							} else {
								appLog.info("Subject Text is not verified.");
								sa.assertTrue(false, "Subject Text is not verified.");
							}
							if (cp.getContactAcitivityAlertSubjectText(30).getText().trim().contains(Org1FirmName+"UP")) {
								appLog.info("Subject Text is  verified.");
							} else {
								appLog.info("Subject Text is not verified.");
								sa.assertTrue(false, "Subject Text is not verified.");
							}
							String date = cp.getContactActivityAlertDueDateText(30).getText().trim();
							if (bp.verifyDate(date, "Due")) {
								appLog.info("Due Date is verified.");
							} else {
								appLog.info("Due Date is not verified. Actual Result: " + date);
								sa.assertTrue(false, "Due Date is not verified. Actual Result: " + date);
							}
							if (cp.getContactAcitivityAlertCommentsText(30).getText().trim().contains("Subject:")) {
								appLog.info("Comments Text is verified.");
							} else {
								appLog.info("Comments Text is not verified.");
								sa.assertTrue(false, "Comments Text is not verified.");
							}
							if (cp.getContactAcitivityAlertCommentsText(30).getText().trim()
									.contains("Invitation from " + Org1FirmName+"UP")) {
								appLog.info("Comments subject Text is verified.");
							} else {
								appLog.info("Comments subject Text is not verified..");
								sa.assertTrue(false, "Comments subject Text is not verified.");
							}
							if (cp.getContactAcitivityAlertCommentsText(30).getText().trim()
									.contains("Hello " + M10Contact1FirstName)) {
								appLog.info("Comments body Text is verified.");
							} else {
								appLog.info("Comments body Text is not verified..");
								sa.assertTrue(false, "Comments body Text is not verified.");
							}
							if (cp.getContactAcitivityAlertCommentsText(30).getText().trim()
									.contains("You have been granted access to Current investments of "+M10FundName1+"UP"+" by")) {
								appLog.info("Comments body Text is verified.");
							} else {
								appLog.info("Comments body Text is not verified..");
								sa.assertTrue(false, "Comments body Text is not verified.");
							}
							if (cp.getContactAcitivityAlertCommentsText(30).getText().trim().contains(
									"If you believe this has been sent in error, or if you cannot login, please contact")) {
								appLog.info("Comments body Text is verified.");
							} else {
								appLog.info("Comments body Text is not verified..");
								sa.assertTrue(false, "Comments body Text is not verified.");
							}
							if (cp.getContactAcitivityAlertCommentsText(30).getText().trim()
									.contains("If you have not yet registered, Click here to register.")) {
								appLog.info("Comments body Text is verified.");
							} else {
								appLog.info("Comments body Text is not verified..");
								sa.assertTrue(false, "Comments body Text is not verified.");
							}
							if (cp.getContactAcitivityAlertCommentsText(30).getText().trim()
									.contains("If you have already registered, Click here to login.")) {
								appLog.info("Comments body Text is verified.");
							} else {
								appLog.info("Comments body Text is not verified..");
								sa.assertTrue(false, "Comments body Text is not verified.");
							}
							if (cp.getContactActivityAlertStatusText(30).getText().trim().contains("Completed")) {
								appLog.info("Status text is verified.");
							} else {
								appLog.info("Status text is not verified.");
								sa.assertTrue(false, "Status text is not verified.");
							}
							if (cp.getContactActivityAlertPriorityText(30).getText().trim().contains("Normal")) {
								appLog.info("Priority text is verified.");
							} else {
								appLog.info("Priority text is not verified.");
								sa.assertTrue(false, "Priority text is not verified.");
							}
							if (cp.getAccountActivityAlertRelatedToText(60).getText().trim()
									.contains(M10Institution1)) {
								appLog.info("Institution Name is verified in Activity Alert on Institution Page.");
							} else {
								appLog.info("Institution Name is not verified in Activity Alert on Institution Page.");
								sa.assertTrue(false,
										"Institution Name is not verified in Activity Alert on Institution Page.");
							}
							if (cp.getContactActivityAlertEmailIdtext(30).getText().trim()
									.contains(M10Contact1EmailId)) {
								appLog.info("Email ID is verified.");
							} else {
								appLog.info("Email ID is not verified.");
								sa.assertTrue(false, "Email ID is not verified.");
							}
							if (cp.getContactActivityAlertCreatedByText(30).getText().trim()
									.contains(CRMUser1LastName)) {
								appLog.info("Created By text is verified.");
							} else {
								appLog.info("Created By text is not verified.");
								sa.assertTrue(false, "Created By text is not verified.");
							}
							if (cp.getAccountActivityAlertNameText(30).getText().trim().contains(M10Contact1LastName)) {
								appLog.info("Name text is verified.");
							} else {
								appLog.info("Name text is not verified.");
								sa.assertTrue(false, "Name text is not verified.");
							}
							String createdByDate = cp.getContactActivityAlertCreatedByDate(60).getText().trim();
							if (bp.verifyDate(createdByDate, "Created By Date")) {
								appLog.info("Cretaed by date is verified");
							} else {
								appLog.info("Created by date is not verified");
								sa.assertTrue(false, "Created by date is not verified");
							}
							if (cp.getContactActivityAlertLastModifiedName(30).getText().trim()
									.contains(CRMUser1LastName)) {
								appLog.info("Last Modified Name text is verified.");
							} else {
								appLog.info("Last Modified Name text is not verified.");
								sa.assertTrue(false, "Last Modified Name text is not verified.");
							}
							String lastModifiedDate = cp.getContactActivityAlertLastModifiedDate(60).getText().trim();
							if (bp.verifyDate(lastModifiedDate, "Last Modified Date")) {
								appLog.info("Last Modified Date is verified");
							} else {
								appLog.info("Last Modified Date is not verified");
								sa.assertTrue(false, "Last Modified Date is not verified");
							}
							String result = ip.getAccountActivityAlertNameText(60).getText().trim();
							for (int i = 0; i < contactnames.length; i++) {
								if (contactnames[i].equalsIgnoreCase(result)) {
									appLog.info("Coontact Name is verified: " + result);
									break;
								} else {
									if (i == contactnames.length - 1) {
										appLog.info("Contact Name is not verified :" + result);
										sa.assertTrue(false, "Contact Name is not verified :" + result);
									}
								}
							}
							driver.close();
							driver.switchTo().window(parentID);
						} else {
							appLog.info("No new window is open");
							sa.assertTrue(false, "No new window is open");
						}
					} else {
						appLog.info("Not able to click on firm name");
						sa.assertTrue(false, "Not able to click on firm name");
					}
				} else {
					appLog.info("Not able to click on manage email");
					sa.assertTrue(false, "Not able to click on manage emails");
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
		lp.CRMlogout(environment, mode);
		String investormailcontent = null;
		try {
			investormailcontent = new EmailLib().getInvestorMailContent("invitationMail", gmailUserName, gmailPassword,
					CRMUser1EmailID, M10Contact1EmailId);
		} catch (InterruptedException e) {
			appLog.info("Invitation mail not found.");

			e.printStackTrace();
		}
		if (investormailcontent != null) {
			if (investormailcontent.contains("You have been granted access to Current investments of "+M10FundName1+"UP"+" by "+Org1FirmName+"UP"+".")) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}
			if (investormailcontent.contains("If you have not yet registered")) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}
			if (investormailcontent.contains("If you have already registered")) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}
			if (investormailcontent.contains("Click here") && investormailcontent.contains("to register.")) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}
			if (investormailcontent.contains("Click here") && investormailcontent.contains("to login")) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}
			if (investormailcontent
					.contains("If you believe this has been sent in error, or if you cannot login, please contact "
							+ Org1FirmName+"UP" + ".")) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}

			if (investormailcontent.contains("Hello "+ M10Contact1FirstName)) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}
			String[] ss = investormailcontent.split("href=\"");
			String[] ss1 = ss[1].split("\"");
			String investorregLink = ss1[0];
			String[] spilt = investormailcontent.split("If you have already registered");
			System.err.println("0" + spilt[0]);
			System.err.println("1" + spilt[1]);
			String[] ssa = spilt[1].split("href=\"");
			String[] ss2 = ssa[1].split("\"");
			String loginLink = ss2[0];
			ExcelUtils.writeData(ss2[0], "Contacts", excelLabel.Variable_Name, "M10Contact1",
					excelLabel.TargetLoginURL);
			ExcelUtils.writeData(investorregLink, "Contacts", excelLabel.Variable_Name, "M10Contact1",
					excelLabel.TargetRegistrationURL);
			driver.get(investorregLink);
			sa.assertTrue(getURL(driver, 60).contains("ip_login"),
					"Login Page is not open after clicking on Resgister Click Here Link.");
			ThreadSleep(4000);
			driver.close();
			config(browserToLaunch);
			driver.get(loginLink);
			lp = new LoginPageBusinessLayer(driver);
			if (isDisplayed(driver, lp.getInvestorLoginButton(60), "Visibility", 60, "Login Button") != null) {
				appLog.info("Login Page is  open after clicking on login Click Here Link.");
			} else {
				appLog.info("Login Page is  not open after clicking on login Click Here Link.");
				sa.assertTrue(false, "Login Page is  not open after clicking on login Click Here Link.");
			}
		} else {
			appLog.info("Mail didn't receive");
			sa.assertTrue(false, "Mail didn't receive");
		}
		sa.assertAll();		
	}
	
	@Test
	public void M10tc021_CheckSearchingAndSortingOnManageEmail(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,
				excelLabel.StandardPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M10FundName1+"NUP")) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30),
						"Investor Section view");
				if (fp.verifyFolderPathdummy(stdPath, M10Institution1, M10LimitedPartner1, M10FundName1, PageName.FundsPage,
						Workspace.InvestorWorkspace, 60)) {
				} else {
					appLog.info("Not able to find folder path");
					sa.assertTrue(false, "Not able to find folder path");
				}
				if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage Email",
						action.SCROLLANDBOOLEAN)) {
					if (selectVisibleTextFromDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60),
							"Contact access view dropdown list", "All Folders")) {
						if (sendKeys(driver, fp.getManageEmailSearchTextBox(60), "123", "Search box",
								action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageEmailSearchBtn(60), "Search icon", action.SCROLLANDBOOLEAN)) {
								if (fp.getManageEMailContactGridErrorMsg(30).getText().trim()
										.contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
									appLog.info("No data to display error message is verified");
								} else {
									appLog.error("No data to display error messsage is not verified");
									sa.assertTrue(false, "No data to display error messsage is not verified");
								}
							} else {
								appLog.info("Not able to click on search icon");
								sa.assertTrue(false, "Not able to click on search icon");
							}
						} else {
							appLog.info("Not able to enter value in search text box");
							sa.assertTrue(false, "Not able to enter value in search text box");
						}
						if (click(driver, fp.getManageEmailClearSearchEnableIcon(60), "Clear search icon",
								action.SCROLLANDBOOLEAN)) {
							if (fp.verifyManageEmailGrid(
									M10Contact1FirstName + "RUP" + " " + M10Contact1LastName + "RUP",
									M10Contact1EmailId, M10Institution1 + "RUP", "Last Invite Date")) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: "
										+ M10Contact1FirstName + " " + M10Contact1LastName);
							}
							if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,
									M10Contact2EmailId, M10Institution2, null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: "
										+ M10Contact2FirstName + " " + M10Contact2LastName);
							}
						} else {
							appLog.info("Not able to clcik on clear search icon");
							sa.assertTrue(false, "Not able to click on clear search icon");
						}
						if (sendKeys(driver, fp.getManageEmailSearchTextBox(60), M10Institution1 + "RUP", "Search box",
								action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageEmailSearchBtn(60), "Search icon", action.SCROLLANDBOOLEAN)) {
								if (fp.verifyManageEmailGrid(
										M10Contact1FirstName + "RUP" + " " + M10Contact1LastName + "RUP",
										M10Contact1EmailId, M10Institution1 + "RUP", "Last Invite Date")) {
									appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
											+ M10Contact1LastName);
								} else {
									appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName
											+ " " + M10Contact1LastName);
									sa.assertTrue(false, "Grid Data is not verified for the contact: "
											+ M10Contact1FirstName + " " + M10Contact1LastName);
								}
							} else {
								appLog.info("Not able to click on search icon");
								sa.assertTrue(false, "Not able to click on search icon");
							}
						} else {
							appLog.info("Not able to enter value in search text box");
							sa.assertTrue(false, "Not able to enter value in search text box");
						}
						if (click(driver, fp.getManageEmailClearSearchEnableIcon(60), "Clear search icon",
								action.SCROLLANDBOOLEAN)) {
							if (fp.verifyManageEmailGrid(
									M10Contact1FirstName + "RUP" + " " + M10Contact1LastName + "RUP",
									M10Contact1EmailId, M10Institution1 + "RUP", "Last Invite Date")) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: "
										+ M10Contact1FirstName + " " + M10Contact1LastName);
							}
							if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,
									M10Contact2EmailId, M10Institution2, null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: "
										+ M10Contact2FirstName + " " + M10Contact2LastName);
							}
						} else {
							appLog.info("Not able to clcik on clear search icon");
							sa.assertTrue(false, "Not able to click on clear search icon");
						}
						WebElement contact1CheckBox = FindElement(driver, "//a[text()='" + M10Contact1FirstName + "RUP"
								+ " " + M10Contact1LastName + "RUP" + "']/../..//input", "Contact Check Box",
								action.SCROLLANDBOOLEAN, 30);
						if (click(driver, contact1CheckBox, "Contact1 checkbox", action.SCROLLANDBOOLEAN)) {
							if (sendKeys(driver, fp.getManageEmailSearchTextBox(60), M10Contact1FirstName + "RUP",
									"Search box", action.SCROLLANDBOOLEAN)) {
								if (click(driver, fp.getManageEmailSearchBtn(60), "Search icon",
										action.SCROLLANDBOOLEAN)) {
									if (fp.verifyManageEmailGrid(
											M10Contact1FirstName + "RUP" + " " + M10Contact1LastName + "RUP",
											M10Contact1EmailId, M10Institution1 + "RUP", "Last Invite Date")) {
										appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName
												+ " " + M10Contact1LastName);
									} else {
										appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName
												+ " " + M10Contact1LastName);
										sa.assertTrue(false, "Grid Data is not verified for the contact: "
												+ M10Contact1FirstName + " " + M10Contact1LastName);
									}
									ThreadSleep(2000);
									contact1CheckBox = FindElement(driver,
											"//a[text()='" + M10Contact1FirstName + "RUP" + " " + M10Contact1LastName
													+ "RUP" + "']/../..//input",
											"Contact Check Box", action.SCROLLANDBOOLEAN, 30);
									if (isSelected(driver, contact1CheckBox, "Contact1 checkbox")) {
										appLog.info("Contact1 checkbox is selected");
									} else {
										appLog.info("Contact 1 checkbox is not selected");
										sa.assertTrue(false, "Contact1  checkbox is not selected");
									}
									ThreadSleep(2000);
									if (isSelected(driver, fp.getManageEMailContactAllCheckBox(60),
											"Contact all checkbox")) {
										appLog.info("Contact all checkbox is selected");
									} else {
										appLog.info("Contact all checkbox is not selected");
										sa.assertTrue(false, "Contact all  checkbox is not selected");
									}
								} else {
									appLog.info("Not able to click on search icon");
									sa.assertTrue(false, "Not able to click on search icon");
								}
							} else {
								appLog.info("Not able to enter value in search text box");
								sa.assertTrue(false, "Not able to enter value in search text box");
							}
						} else {
							appLog.info("Not able to click on contact 1 checkbox");
							sa.assertTrue(false, "Not able to click on contact1 checkbox");
						}
						if (click(driver, fp.getManageEmailClearSearchEnableIcon(60), "Clear search icon",
								action.SCROLLANDBOOLEAN)) {
							if (fp.verifyManageEmailGrid(
									M10Contact1FirstName + "RUP" + " " + M10Contact1LastName + "RUP",
									M10Contact1EmailId, M10Institution1 + "RUP", "Last Invite Date")) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
										+ M10Contact1LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: "
										+ M10Contact1FirstName + " " + M10Contact1LastName);
							}
							if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,
									M10Contact2EmailId, M10Institution2, null)) {
								appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
							} else {
								appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
										+ M10Contact2LastName);
								sa.assertTrue(false, "Grid Data is not verified for the contact: "
										+ M10Contact2FirstName + " " + M10Contact2LastName);
							}
							contact1CheckBox = FindElement(
									driver, "//a[text()='" + M10Contact1FirstName + "RUP" + " " + M10Contact1LastName
											+ "RUP" + "']/../..//input",
									"Contact Check Box", action.SCROLLANDBOOLEAN, 30);
							if (isSelected(driver, contact1CheckBox, "Contact1 checkbox")) {
								appLog.info("Contact1 checkbox is selected");
							} else {
								appLog.info("Contact 1 checkbox is not selected");
								sa.assertTrue(false, "Contact1  checkbox is not selected");
							}
							if (!isSelected(driver, fp.getManageEMailContactAllCheckBox(60), "Contact all checkbox")) {
								appLog.info("Contact all checkbox is not selected");
							} else {
								appLog.info("Contact all checkbox is selected");
								sa.assertTrue(false, "Contact all  checkbox is selected");
							}
							WebElement contact2CheckBox = isDisplayed(driver,
									FindElement(driver,
											"//a[text()='" + M10Contact2FirstName + " " + M10Contact2LastName
													+ "']/../..//input",
											"Contact Check Box", action.SCROLLANDBOOLEAN, 30),
									"visibility", 30, "Manage Emails Contact Check Box");
							if (!isSelected(driver, contact2CheckBox, "Contact1 checkbox")) {
								appLog.info("Contact2 checkbox is not selected");
							} else {
								appLog.info("Contact 2 checkbox is selected");
								sa.assertTrue(false, "Contact2  checkbox is selected");
							}
						} else {
							appLog.info("Not able to clcik on clear search icon");
							sa.assertTrue(false, "Not able to click on clear search icon");
						}
						if (sendKeys(driver, fp.getManageEmailSearchTextBox(60), M10Contact2EmailId, "Search box",
								action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageEmailSearchBtn(60), "Search icon", action.SCROLLANDBOOLEAN)) {
								if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,
										M10Contact2EmailId, M10Institution2, null)) {
									appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
											+ M10Contact2LastName);
								} else {
									appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName
											+ " " + M10Contact2LastName);
									sa.assertTrue(false, "Grid Data is not verified for the contact: "
											+ M10Contact2FirstName + " " + M10Contact2LastName);
								}
							} else {
								appLog.info("Not able to click on search icon");
								sa.assertTrue(false, "Not able to click on search icon");
							}
						} else {
							appLog.info("Not able to enter value in search text box");
							sa.assertTrue(false, "Not able to enter value in search text box");
						}
						if (click(driver, fp.getManageEmailClearSearchEnableIcon(60), "Clear search icon",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Able to clcik on clear search icon");
						} else {
							appLog.info("Not able to clcik on clear search icon");
							sa.assertTrue(false, "Not able to click on clear search icon");
						}
						if (sendKeys(driver, fp.getManageEmailSearchTextBox(60), getSystemDate("MM/dd/yyyy"),
								"Search box", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageEmailSearchBtn(60), "Search icon", action.SCROLLANDBOOLEAN)) {
								if (fp.getManageEMailContactGridErrorMsg(30).getText().trim()
										.contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
									appLog.info("No data to display error message is verified");
								} else {
									appLog.error("No data to display error messsage is not verified");
									sa.assertTrue(false, "No data to display error messsage is not verified");
								}
							} else {
								appLog.info("Not able to click on search icon");
								sa.assertTrue(false, "Not able to click on search icon");
							}
						} else {
							appLog.info("Not able to enter value in search text box");
							sa.assertTrue(false, "Not able to enter value in search text box");
						}
						if (click(driver, fp.getManageEmailClearSearchEnableIcon(60), "Clear search icon",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Able to clcik on clear search icon");
						} else {
							appLog.info("Not able to clcik on clear search icon");
							sa.assertTrue(false, "Not able to click on clear search icon");
						}
						if (click(driver, fp.getManageEmailEmailLabel(60), "Email label", action.SCROLLANDBOOLEAN)) {
							if (sendKeys(driver, fp.getManageEmailSearchTextBox(60),
									M10Contact1FirstName + "RUP" + " " + M10Contact1LastName + "RUP", "Search box",
									action.SCROLLANDBOOLEAN)) {
								if (click(driver, fp.getManageEmailSearchBtn(60), "Search icon",
										action.SCROLLANDBOOLEAN)) {
									if (fp.verifyManageEmailGrid(
											M10Contact1FirstName + "RUP" + " " + M10Contact1LastName + "RUP",
											M10Contact1EmailId, M10Institution1 + "RUP", "Last Invite Date")) {
										appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName
												+ " " + M10Contact1LastName);
									} else {
										appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName
												+ " " + M10Contact1LastName);
										sa.assertTrue(false, "Grid Data is not verified for the contact: "
												+ M10Contact1FirstName + " " + M10Contact1LastName);
									}
									if (bp.checkSorting(SortOrder.Assecending,
											fp.getManageEmailListOfContactEmails())) {
										appLog.info("Contact email is displaying is ascending order");
									} else {
										appLog.info("Contact email is not displaying is ascending order");
										sa.assertTrue(false, "Contact email is not displaying is ascending order");
									}
								} else {
									appLog.info("Not able to click on search icon");
									sa.assertTrue(false, "Not able to click on search icon");
								}
							} else {
								appLog.info("Not able to enter value in search text box");
								sa.assertTrue(false, "Not able to enter value in search text box");
							}
						} else {
							appLog.info("Not able to sleect email coloumn");
							sa.assertTrue(false, "Not able to select email coloumn");
						}
					} else {
						appLog.info("Not able to select All Folder Option from the contact access view dropdown");
						sa.assertTrue(false,
								"Not able to select All Folder Option from the contact access view dropdown");
					}
					if (selectVisibleTextFromDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60),
							"Contact access view dropdown list", stdPath)) {
						if (fp.verifyManageEmailGrid(M10Contact1FirstName + "RUP" + " " + M10Contact1LastName + "RUP",
								M10Contact1EmailId, M10Institution1 + "RUP", "Last Invite Date")) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName
									+ " " + M10Contact1LastName);
						}
						WebElement sortingIcon = isDisplayed(driver,
								FindElement(driver,
										"//div[@id='manageemailgrid_ME']//span[text()='Last Invite Date']/span",
										"Manage Emails Sorting Icon", action.SCROLLANDBOOLEAN, 30),
								"visibility", 30, "Manage Emails Sorting Icon");
						if (sortingIcon != null) {
							appLog.info("Sorting Icon is displaying by default on last invite date column");
						} else {
							appLog.info("Sorting Icon is not displaying by default on last invite date column");
							sa.assertTrue(false,
									"Sorting Icon is not displaying by default on last invite date column");
						}
					} else {
						appLog.info(
								"Not able to select standard parent folder Option from the contact access view dropdown");
						sa.assertTrue(false,
								"Not able to select standard parent folder  Option from the contact access view dropdown");
					}
					if (selectVisibleTextFromDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60),
							"Contact access view dropdown list", "All Folders")) {
						if (click(driver, fp.getManageEMailFirmHeaderText(30), "Manage Email Firm Header Text",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on firm name header text");
							if (fp.checkSorting(SortOrder.Assecending, fp.getFirmsInGridInManageEmails(60))) {
								appLog.info("Ascending sorting is verified on firm name ");
							} else {
								appLog.info("Ascending sorting is not verified on firm name ");
								sa.assertTrue(false, "Ascending sorting is not verified on firm name ");
							}
						} else {
							appLog.info("Not able to Clicke on firm name header text");
							sa.assertTrue(false, "Not able to Clicke on firm name header text");
						}
						if (click(driver, fp.getManageEMailFirmHeaderText(30), "Manage Email Firm Header Text",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on firm name header text");
							if (fp.checkSorting(SortOrder.Decending, fp.getFirmsInGridInManageEmails(60))) {
								appLog.info("Decending sorting is verified on firm name ");
							} else {
								appLog.info("Decending sorting is not verified on firm name ");
								sa.assertTrue(false, "Decending sorting is not verified on firm name ");
							}
						} else {
							appLog.info("Not able to Clicke on firm name header text");
							sa.assertTrue(false, "Not able to Clicke on firm name header text");
						}
						if (click(driver, fp.getManageEMailLastInviteDateHeaderText(30),
								"Manage Email Last Invite Date Text", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on  Last Invite Date text");
							if (fp.checkSorting(SortOrder.Assecending, fp.getLastInviteDateInGridInManageEmails(60))) {
								appLog.info("Ascending sorting is verified on  Last Invite Date ");
							} else {
								appLog.info("Ascending sorting is not verified on  Last Invite Date ");
								sa.assertTrue(false, "Ascending sorting is not verified on  Last Invite Date");
							}
						} else {
							appLog.info("Not able to Clicke on  Last Invite Date header text");
							sa.assertTrue(false, "Not able to Clicke on  Last Invite Date header text");
						}
						if (click(driver, fp.getManageEMailLastInviteDateHeaderText(30),
								"Manage Email  Last Invite Date Header Text", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Last Invite Date header text");
							if (fp.checkSorting(SortOrder.Decending, fp.getLastInviteDateInGridInManageEmails(60))) {
								appLog.info("Decending sorting is verified on Last Invite Date ");
							} else {
								appLog.info("Decending sorting is not verified on Last Invite Date");
								sa.assertTrue(false, "Decending sorting is not verified on Last Invite Date");
							}
						} else {
							appLog.info("Not able to Clicke on Last Invite Date header text");
							sa.assertTrue(false, "Not able to Clicke on Last Invite Dateheader text");
						}
						if (click(driver, fp.getManageEMailContactNameHeaderText(30), "Manage Email Contact name Text",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on  Contact name Text");
							if (fp.checkSorting(SortOrder.Assecending, fp.getManageEmailsListofContacts())) {
								appLog.info("Ascending sorting is verified on  Contact name");
							} else {
								appLog.info("Ascending sorting is not verified on Contact name");
								sa.assertTrue(false, "Ascending sorting is not verified on  Contact name");
							}
						} else {
							appLog.info("Not able to Clicke on  Contact name header text");
							sa.assertTrue(false, "Not able to Clicke on  Contact name header text");
						}
						if (click(driver, fp.getManageEMailContactNameHeaderText(30),
								"Manage Email  Contact name Header Text", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Contact name header text");
							if (fp.checkSorting(SortOrder.Decending, fp.getManageEmailsListofContacts())) {
								appLog.info("Decending sorting is verified on Contact name");
							} else {
								appLog.info("Decending sorting is not verified on Contact name");
								sa.assertTrue(false, "Decending sorting is not verified on Contact name");
							}
						} else {
							appLog.info("Not able to Clicke on Contact name header Contact name");
							sa.assertTrue(false, "Not able to Click on Contact name header text");
						}
						if (click(driver, fp.getManageEmailEmailLabel(30), "Manage Email email Text",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on  email Text");
							if (fp.checkSorting(SortOrder.Assecending, fp.getManageEmailListOfContactEmails())) {
								appLog.info("Ascending sorting is verified on  email");
							} else {
								appLog.info("Ascending sorting is not verified on email");
								sa.assertTrue(false, "Ascending sorting is not verified on  email");
							}
						} else {
							appLog.info("Not able to Clicke on  email header text");
							sa.assertTrue(false, "Not able to Clicke on  email header text");
						}
						if (click(driver, fp.getManageEmailEmailLabel(30), "Manage Email email Header Text",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on email header text");
							if (fp.checkSorting(SortOrder.Decending, fp.getManageEmailListOfContactEmails())) {
								appLog.info("Decending sorting is verified on email");
							} else {
								appLog.info("Decending sorting is not verified on email");
								sa.assertTrue(false, "Decending sorting is not verified on email");
							}
						} else {
							appLog.info("Not able to Clicke on email header text");
							sa.assertTrue(false, "Not able to Click on email header text");
						}
					} else {
						appLog.info("Not able to select All Folder Option from the contact access view dropdown");
						sa.assertTrue(false,
								"Not able to select All Folder Option from the contact access view dropdown");
					}
				} else {
					appLog.info("Not able to click on manage email icon");
					sa.assertTrue(false, "Not able to click on manage emails");
				}
			} else {
				appLog.info("Not able to click on cretaed fund");
				sa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on funds tab");
			sa.assertTrue(false, "Not able ot click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();	
	}
	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void M10tc022_InviteContactFromSharedParentFolderAgainInviteContactAndVerifyManageEmailIconInContactAccessPopup(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String sharedPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,
				excelLabel.SharedPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M10FundName1+"NUP")) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30),
						"Investor Section view");
				if (fp.verifyFolderPathdummy(sharedPath, null, null, M10FundName1, PageName.FundsPage,
						Workspace.InvestorWorkspace, 60)) {
					if (click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 60),
							"Contact access icon", action.SCROLLANDBOOLEAN)) {
						if (fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)) {
							appLog.info(
									Workspace.InvestorWorkspace + " contact access popUp is expanded successfully.");
							if (sendKeys(driver, fp.getSearchTextBox(Workspace.InvestorWorkspace, 60),
									M10Contact2EmailId, Workspace.InvestorWorkspace + " search text box",
									action.SCROLLANDBOOLEAN)) {
								appLog.info("enter the value in search text box : " + M10Contact2EmailId);
								if (click(driver, fp.getSearchBtn(Workspace.InvestorWorkspace, 60),
										Workspace.InvestorWorkspace + " search button", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on  " + Workspace.InvestorWorkspace + " search button");
									WebElement contactcheckBox = FindElement(driver,
											"//div[@id='shwTopGridBWINV_MA']//a[text()='" + M10Contact2EmailId
													+ "']/../..//input",
											M10Contact2EmailId + " contact check box", action.SCROLLANDBOOLEAN, 30);
									scrollDownThroughWebelement(driver, contactcheckBox, "Checkbox");
									if (click(driver, contactcheckBox, "check box", action.BOOLEAN)) {
										appLog.info("clicked on contact check box : " + M10Contact2EmailId);
										if (click(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30),
												Workspace.InvestorWorkspace + " add select contact active button",
												action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on add selected contact active button in "
													+ Workspace.InvestorWorkspace);
											ThreadSleep(5000);
											List<WebElement> listofContact = fp
													.getselectContactEmailIDList(Workspace.InvestorWorkspace);

											if (!listofContact.isEmpty()) {
												for (int j = 0; j < listofContact.size(); j++) {
													String contactemail = listofContact.get(j).getText().trim();
													if (contactemail.equalsIgnoreCase(M10Contact2EmailId)) {
														appLog.info(M10Contact2EmailId
																+ " contact is displaying in selected contact access grid");
														break;
													} else {
														if (j == listofContact.size() - 1) {
															appLog.error(contactemail + " is not matched with >>> "
																	+ M10Contact2EmailId);
															sa.assertTrue(false, contactemail
																	+ " is not matched with >>> " + M10Contact2EmailId);
														}
													}
												}
											}
											if (click(driver,
													fp.geManageEmailIconInContactAccessPopup(
															Workspace.InvestorWorkspace, 60),
													"Mange Amil icon in contact access popup",
													action.SCROLLANDBOOLEAN)) {
												String header = fp.geManageEmailPopupHeaderInContactAccessPopup(
														Workspace.InvestorWorkspace, 60).getText().trim();
												if (header.equalsIgnoreCase("Confirmation")) {
													appLog.info("Confirmation popup is displaying");
												} else {
													appLog.info("Confirmation popup is not displaying");
													sa.assertTrue(false, "Confirmation popup is not displaying");
												}
												if (fp.geManageEmailPopupErrorMessageInContactAccessPopup(
														Workspace.InvestorWorkspace, 60).getText().trim().contains(
																FundsPageErrorMessage.MnaageEmailContactAccessErrorMessage1)) {
													appLog.info("Error Message is verified");
												} else {
													appLog.info("Error Message is not verified");
													sa.assertTrue(false, "Error Message is not verified");
												}
												if (fp.geManageEmailPopupErrorMessageInContactAccessPopup(
														Workspace.InvestorWorkspace, 60).getText().trim().contains(
																FundsPageErrorMessage.MnaageEmailContactAccessErrorMessage2)) {
													appLog.info("Error Message is verified");
												} else {
													appLog.info("Error Message is not verified");
													sa.assertTrue(false, "Error Message is not verified");
												}
												if (isDisplayed(driver,
														fp.geManageEmailPopupYesButtonInContactAccessPopup(
																Workspace.InvestorWorkspace, 60),
														"Visibility", 60, "Yes Button") != null) {
													appLog.info("YEs Button is displaying");
												} else {
													appLog.info("Yes Button is not displaying");
													sa.assertTrue(false, "Yes Button is not displaying");
												}
												if (isDisplayed(driver,
														fp.geManageEmailPopupNoButtonInContactAccessPopup(
																Workspace.InvestorWorkspace, 60),
														"Visibility", 60, "No Button") != null) {
													appLog.info("No Button is displaying");
												} else {
													appLog.info("No Button is not displaying");
													sa.assertTrue(false, "No Button is not displaying");
												}
												if (isDisplayed(driver,
														fp.geManageEmailPopupCloseIconInContactAccessPopup(
																Workspace.InvestorWorkspace, 60),
														"Visibility", 60, "Close Icon") != null) {
													appLog.info("Close Icon is displaying");
												} else {
													appLog.info("Close Icon is not displaying");
													sa.assertTrue(false, "Close Icon is not displaying");
												}
												if (click(driver,
														fp.geManageEmailPopupNoButtonInContactAccessPopup(
																Workspace.InvestorWorkspace, 60),
														"No Button", action.SCROLLANDBOOLEAN)) {
													if (fp.geManageEmailPopupNoButtonInContactAccessPopup(
															Workspace.InvestorWorkspace, 20) == null) {
														appLog.info("Confirmation popup is closed");
													} else {
														appLog.info("Confirmation popup is not closed");
														sa.assertTrue(false, "Confirmation popup is not closed");
													}
												} else {
													appLog.info("Not able ot click on no button");
													sa.assertTrue(false, "Not able to click on no button");
												}
											} else {
												appLog.info("Not able to click on manage email icon");
												sa.assertTrue(false, "Not able to click on manage email icon");
											}
											if (click(driver,
													fp.geManageEmailIconInContactAccessPopup(
															Workspace.InvestorWorkspace, 60),
													"Mange mail icon in contact access popup",
													action.SCROLLANDBOOLEAN)) {
												if (click(driver,
														fp.geManageEmailPopupCloseIconInContactAccessPopup(
																Workspace.InvestorWorkspace, 60),
														"Close icon", action.SCROLLANDBOOLEAN)) {
													if (fp.geManageEmailPopupCloseIconInContactAccessPopup(
															Workspace.InvestorWorkspace, 20) == null) {
														appLog.info("Confirmation popup is closed");
													} else {
														appLog.info("Confirmation popup is not closed");
														sa.assertTrue(false, "Confirmation popup is not closed");
													}
												} else {
													appLog.info("Not able ot click on Close icon");
													sa.assertTrue(false, "Not able to click on Close icon");
												}
											} else {
												appLog.info("Not able to click on manage email icon");
												sa.assertTrue(false, "Not able to click on manage email icon");
											}
											if (click(driver,
													fp.geManageEmailIconInContactAccessPopup(
															Workspace.InvestorWorkspace, 60),
													"Mange Amil icon in contact access popup",
													action.SCROLLANDBOOLEAN)) {
												if (click(driver,
														fp.geManageEmailPopupYesButtonInContactAccessPopup(
																Workspace.InvestorWorkspace, 60),
														"Yes Button", action.SCROLLANDBOOLEAN)) {
													if (fp.getManageEmailsHeader(60) != null) {
														appLog.info("Manage Email popup is displaying");
														if (fp.getManageEMailContactGridErrorMsg(30).getText().trim()
																.contains(
																		FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
															appLog.info("No data to display error message is verified");
														} else {
															appLog.error(
																	"No data to display error messsage is not verified");
															sa.assertTrue(false,
																	"No data to display error messsage is not verified");
														}
														List<WebElement> contactAccessViewDropdownValues = allOptionsInDropDrop(
																driver,
																fp.getManageEmailContactAccessViewDropDownList(60),
																"Show dropdown values");
														for (int i = 0; i < contactAccessViewDropdownValues
																.size(); i++) {
															if (contactAccessViewDropdownValues.get(i).getText()
																	.equalsIgnoreCase(sharedPath)) {
																appLog.info(
																		contactAccessViewDropdownValues.get(i).getText()
																				+ " :Opiton is available in the list.");
															} else if (contactAccessViewDropdownValues.get(i).getText()
																	.equalsIgnoreCase("All Folders")) {
																appLog.info(
																		contactAccessViewDropdownValues.get(i).getText()
																				+ " :Opiton is available in the list.");
															} else {
																appLog.info(contactAccessViewDropdownValues.get(i)
																		.getText()
																		+ " :Opiton is not available in the list.");
																sa.assertTrue(false, contactAccessViewDropdownValues
																		.get(i).getText()
																		+ " :Opiton is not available in the list.");
															}
														}
														if (click(driver, fp.getManageEmailsCloseIcon(60), "Close icon",
																action.SCROLLANDBOOLEAN)) {
															appLog.info("Clicked on close icon");
															if (fp.getManageEmailsCloseIcon(10) == null) {
																appLog.info("Manage email popup get closed ");
																ThreadSleep(3000);
																if (fp.getINV_contactAccessMinusIcon(60) != null) {
																	appLog.info(
																			"Select Contact access grid is collapsed");
																} else {
																	appLog.info(
																			"Select Contact access grid is not collapsed");
																	sa.assertTrue(false,
																			"Select Contact access grid is not collapsed");
																}
																if (fp.getErrorMessageInContactAccessPopup(
																		Workspace.InvestorWorkspace, 60).getText()
																		.trim().contains(
																				FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
																	appLog.info(
																			"No data to display error message is verified in contact access popup");
																} else {
																	appLog.info(
																			"No data to display error message is not verified in contact access popup");
																	sa.assertTrue(false,
																			"No data to display error message is not verified in contact access popup");
																}
															} else {
																appLog.info(
																		"Manage email popup does not get collapsed");
																sa.assertTrue(false,
																		"Manage email popup does not get collapsed");
															}
														} else {
															appLog.info("Not able to click on close icon successfully");
															sa.assertTrue(false, "Not able to click on close icon ");
														}
													} else {
														appLog.info("Manage email popup is not displaying");
														sa.assertTrue(false, "Manage Email popup is not displying");
													}
												} else {
													appLog.info("Not able to click on yes button");
													sa.assertTrue(false, "Not able to click on yes button");
												}
											} else {
												appLog.info("Not able to click on manage email icon");
												sa.assertTrue(false, "Not able to click on manage email icon");
											}
										} else {
											appLog.error("Not able to click on add select contact button in "
													+ Workspace.InvestorWorkspace);
											sa.assertTrue(false, "Not able to click on add select contact button in "
													+ Workspace.InvestorWorkspace);
										}

									} else {
										appLog.error("Not able to click on " + M10Contact2EmailId
												+ " contact check box so cannot invite contact: " + M10Contact2EmailId);
										sa.assertTrue(false, "Not able to click on " + M10Contact2EmailId
												+ " contact check box so cannot invite contact: " + M10Contact2EmailId);
									}
								} else {
									appLog.error("Not able to click on search button");
									sa.assertTrue(false, "Not able to click on search button");
								}
							} else {
								appLog.error("Not able to enter value in textbox");
								sa.assertTrue(false, "Not able to enter value in textbox");
							}
						} else {
							appLog.info("Not able to expand the select contact grid");
							sa.assertTrue(false, "Not able to expand the select contact grid");
						}
						if (click(driver, fp.getCancelBtn(Workspace.InvestorWorkspace, 60),
								Workspace.InvestorWorkspace + " cancel button", action.SCROLLANDBOOLEAN)) {
							appLog.info("Able to click on cancel button");
							if (fp.getCancelBtn(Workspace.InvestorWorkspace, 30) == null) {
								appLog.info("Contact access popup get closed");
							} else {
								appLog.info("Contact access popup does not get closed");
								sa.assertTrue(false, "Contact access popup does not get closed");
							}
						} else {
							appLog.info("Not able to click on cancel button");
							sa.assertTrue(false, "Not able to click on cancel button");
						}
					} else {
						appLog.info("Not able to click on contact access button");
						sa.assertTrue(false, "Not able to click on contact access buttomn");
					}
					switchToDefaultContent(driver);
					if (fp.inviteContact(environment, mode, M10Institution1, M10Contact1EmailId, sharedPath, FolderType.Shared,
							"", "Yes", "No", null, Workspace.InvestorWorkspace, null)) {
						appLog.info(M10Contact1FirstName + " " + M10Contact1LastName + " is invited successfully");
					} else {
						appLog.info(M10Contact1FirstName + " " + M10Contact1LastName + " is not invited successfully");
						sa.assertTrue(false,
								M10Contact1FirstName + " " + M10Contact1LastName + " is not invited  successfully");
					}
					if (fp.inviteContact(environment, mode, M10Institution1, M10Contact2EmailId, sharedPath, FolderType.Shared,
							"", "Yes", "No", null, Workspace.InvestorWorkspace, null)) {
						appLog.info(M10Contact2FirstName + " " + M10Contact2LastName + "  is invited successfully");
					} else {
						appLog.info(M10Contact2FirstName + " " + M10Contact2LastName + "is not invited successfully");
						sa.assertTrue(false,
								M10Contact2FirstName + " " + M10Contact2LastName + "is not invited  successfully");
					}
					switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
					scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30),
							Workspace.InvestorWorkspace.toString() + " Section view");					
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 60), "Contact Access Icon of "+Workspace.InvestorWorkspace, action.SCROLLANDBOOLEAN)) {
						if (click(driver,fp.geManageEmailIconInContactAccessPopup(Workspace.InvestorWorkspace, 60),"Mange Amil icon in contact access popup",
								action.SCROLLANDBOOLEAN)) {
						if (fp.verifyManageEmailGrid(M10Contact1FirstName + "RUP" + " " + M10Contact1LastName + "RUP",
								M10Contact1EmailId, M10Institution1 + "RUP", "Last Invite Date")) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact1FirstName + " "
									+ M10Contact1LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact1FirstName
									+ " " + M10Contact1LastName);
						}
						if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,
								M10Contact2EmailId, M10Institution2, null)) {
							appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
						} else {
							appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
									+ M10Contact2LastName);
							sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact2FirstName
									+ " " + M10Contact2LastName);
						}
					} else {
						appLog.info("Not able to clcik on manage email");
						sa.assertTrue(false, "Not able to clcik on manage email");
					}
					}else{
						appLog.error("Not able to click on contact access icon");
						sa.assertTrue(false, "Not able to click on contact access icon");
					}
				} else {
					appLog.info("Not able to find folder path");
					sa.assertTrue(false, "Not able to find folder path");
				}
			} else {
				appLog.info("Not able to click on cretaed fund");
				sa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on funds tab");
			sa.assertTrue(false, "Not able ot click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();		
	}
	
	@Test
	public void M10tc023_VerifyInvitationEmailsPopUpWithCustomEmailTemplate(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		WebElement ele = null;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M10FundName1+"NUP")) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30),
						"Investor Section view");
				if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Investor workspace",
						action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getManageEmailCustomRadioButton(60), "Custom template radio button",
							action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getManageEmailCustomTemplateCancelButton(60), "Cancel button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on cancel button");
						} else {
							appLog.info("Not able to click on cancel button");
							sa.assertTrue(false, "Not able to click on cancel button");
						}
						ele = FindElement(driver,
								"//a[text()='" + M10Contact2FirstName + " " + M10Contact2LastName + "']/../..//input",
								"Contact2 checkbox", action.SCROLLANDBOOLEAN, 60);
						if (click(driver, ele, "Contact2 checkbox", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on contact2 checkbox");
							if (click(driver, fp.getmanageEmailsendBtn(60), "Send Button", action.SCROLLANDBOOLEAN)) {
								if (fp.clickUsingCssSelectorPath("div#confirmationpopup_emailID_ME a[title=Yes]", "yes button")) {
									
								//if (click(driver, fp.getManageEmailSendInvitationConfirmationYesBtn(60),
								//		"Confirmation poup yes button", action.SCROLLANDBOOLEAN)) {
									String alertText = switchToAlertAndGetMessage(driver, 60, action.GETTEXT);
									if (alertText.equalsIgnoreCase(
											FundsPageErrorMessage.MnaageEmailCustomTemplateErrorMessage)) {
										appLog.info("Error message is verified");
									} else {
										appLog.info("Erorr Message is not verified");
										sa.assertTrue(false, "Error Message is not verified");
									}
									switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
									if (!isAlertPresent(driver)) {
										appLog.info("Alert popup get closed ");
									} else {
										appLog.info("Alert popup does not get closed successfully");
										sa.assertTrue(false, "Alert popup does not get closed successfully");
									}
								} else {
									appLog.info("Not able to click on Yes Button");
									sa.assertTrue(false, "Not able to click on yes button");
								}
							} else {
								appLog.info("Not able to click on send button");
								sa.assertTrue(false, "Not bale to click on send button");
							}
						} else {
							appLog.info("Not able to click on contact2 checkbox");
							sa.assertTrue(false, "Not able to click on contact2 checkbox");
						}
						if (click(driver, fp.getManageEmailCustomEmailTemplateEditPreviewTextList().get(0), "Edit ",
								action.SCROLLANDBOOLEAN)) {
							if (sendKeys(driver, fp.getManageEmailCustomSubjectTextBox(60),
									"Custom Invitation Email for PE", "Subject text box", action.SCROLLANDBOOLEAN)) {
								switchToFrame(driver, 60, fp.getManageEmailCustomSubjectBodyFrame(60));
								if (sendKeys(driver, fp.getManageEmailCustomSubjectBody(60),
										"We are happy to inform you that You are invited to Fund. Please Invest in this Fund.",
										"Body text box", action.SCROLLANDBOOLEAN)) {
									switchToDefaultContent(driver);
									switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
									scrollDownThroughWebelement(driver,
											bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30),
											"Fundraising Section view");
									if (click(driver, fp.getManageEmailCustomApplyButton(60), "Apply button",
											action.SCROLLANDBOOLEAN)) {
										if (click(driver, fp.getmanageEmailsendBtn(60), "Send Button",
												action.SCROLLANDBOOLEAN)) {
											if (fp.getManageEmailSendInvitationConfirmationCountErrorMsg(30).getText()
													.trim().contains("1")) {
												appLog.info(
														"Manage Emails Contact Send Invitation Confirmation Count is  verified");
											} else {
												appLog.info(
														"Manage Emails Contact Send Invitation Confirmation Count is not verified");
												sa.assertTrue(false,
														"Manage Emails Contact Send Invitation Confirmation Count is not verified");
											}
											if (fp.getManageEmailSendInvitationConfirmationErrorMsg(30).getText().trim()
													.contains(
															FundsPageErrorMessage.ManageEmailSendContactInvitationErrorMsg1)) {
												appLog.info(
														"Manage Emails Contact Send Invitation Confirmation Error Message is verified.");
											} else {
												appLog.info(
														"Manage Emails Contact Send Invitation Confirmation Error Message is not verified.");
												sa.assertTrue(false,
														"Manage Emails Contact Send Invitation Confirmation Error Message is not verified.");
											}
											if (fp.getManageEmailSendInvitationConfirmationErrorMsg(30).getText().trim()
													.contains(
															FundsPageErrorMessage.MnaageEmailSendContactInvitationErrorMsg2)) {
												appLog.info(
														"Manage Emails Contact Send Invitation Confirmation Error Message is verified.");
											} else {
												appLog.info(
														"Manage Emails Contact Send Invitation Confirmation Error Message is not verified.");
												sa.assertTrue(false,
														"Manage Emails Contact Send Invitation Confirmation Error Message is not verified.");
											}
											if (click(driver, fp.getManageEmailSendInvitationConfirmationYesBtn(60),
													"Yes button", action.SCROLLANDBOOLEAN)) {
												if (fp.getManageEmailSendInvitationConfirmationYesBtn(10) == null) {
													appLog.info("confirmation popup get closed");
												} else {
													appLog.info("Confirmation popup does not get closed");
													sa.assertTrue(false, "Confirmation popup does not get closed");
												}
												if (fp.verifyManageEmailGrid(
														M10Contact1FirstName + "RUP" + " " + M10Contact1LastName
																+ "RUP",
														M10Contact1EmailId, M10Institution1 + "RUP",
														"Last Invite Date")) {
													appLog.info("Grid Data is verified for the contact: "
															+ M10Contact1FirstName + " " + M10Contact1LastName);
												} else {
													appLog.info("Grid Data is not verified for the contact: "
															+ M10Contact1FirstName + " " + M10Contact1LastName);
													sa.assertTrue(false, "Grid Data is not verified for the contact: "
															+ M10Contact1FirstName + " " + M10Contact1LastName);
												}
												if (fp.verifyManageEmailGrid(
														M10Contact2FirstName + " " + M10Contact2LastName,
														M10Contact2EmailId, M10Institution2, null)) {
													appLog.info("Grid Data is verified for the contact: "
															+ M10Contact2FirstName + " " + M10Contact2LastName);
												} else {
													appLog.info("Grid Data is not verified for the contact: "
															+ M10Contact2FirstName + " " + M10Contact2LastName);
													sa.assertTrue(false, "Grid Data is not verified for the contact: "
															+ M10Contact2FirstName + " " + M10Contact2LastName);
												}
											} else {
												appLog.info("Not able to click on yes button");
												sa.assertTrue(false, "Not ableto click on yes button");
											}
										} else {
											appLog.info("Not able to click on send button");
											sa.assertTrue(false, "Not able to click on send button");
										}
									} else {
										appLog.info("Not able to click on apply button");
										sa.assertTrue(false, "Not able to click on apply button");
									}
								} else {
									appLog.info("Not able to enter value in body text box");
									sa.assertTrue(false, "Not able to enter value in body text box");
								}
							} else {
								appLog.info("Not able to enter value in subject text box");
								sa.assertTrue(false, "Not able to enter value in subject text box");
							}
						} else {
							appLog.info("Not able to click on edit link of custom templte");
							sa.assertTrue(false, "Not able to click on edit link of custom template");
						}
					} else {
						appLog.info("Not able to click on custome template radio button");
						sa.assertTrue(false, "Not able to click on custom template radio button");
					}
					if (click(driver, fp.getManageEmailCancelBtn(60), "Manage email cancel button",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on cancel button");
					} else {
						appLog.info("Not able ot click on cancel button");
						sa.assertTrue(false, "Not able to click on cancel button");
					}
				} else {
					appLog.info("Not able to click on manage emails");
					sa.assertTrue(false, "Not able to click on manage emails");
				}
				if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage email",
						action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getManageEmailCustomRadioButton(60), "Custom template radio button",
							action.SCROLLANDBOOLEAN)) {
						ThreadSleep(2000);
						if (getValueFromElementUsingJavaScript(driver, fp.getManageEmailCustomSubjectTextBox(60),
								"Subject text box").isEmpty()) {
							appLog.info("Subject field is blank");

						} else {
							appLog.info("Subject field is not blank");
							sa.assertTrue(false, "Subject field is not blank");
						}

						switchToFrame(driver, 60, fp.getManageEmailCustomSubjectBodyFrame(60));
						if (getValueFromElementUsingJavaScript(driver, fp.getManageEmailCustomSubjectBody(60),
								"Body text box") == null) {
							appLog.info("Body field is blank");
						} else {
							appLog.info("Body field is not blank");
							sa.assertTrue(false, "Body field is not blank");
						}
						switchToDefaultContent(driver);
						switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
						scrollDownThroughWebelement(driver,
								bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30),
								"Fundraising Section view");
						if (click(driver, fp.getManageEmailCustomTemplateCancelButton(60), "Cancel button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("clcik on cancel button");
						} else {
							appLog.info("Not able to clcik on cancel button");
							sa.assertTrue(false, "Not able to clcik on cancel button");
						}
					} else {
						appLog.info("Not able to click on custome template radio button");
						sa.assertTrue(false, "Not able to click on custom template radio button");
					}
					ele = FindElement(driver, "//a[text()='" + M10Contact2FirstName + " " + M10Contact2LastName + "']",
							"Contact 2", action.SCROLLANDBOOLEAN, 30);
					if (click(driver, ele, "Contact 2 name", action.SCROLLANDBOOLEAN)) {
						String parentID = switchOnWindow(driver);
						if (parentID != null) {
							scrollDownThroughWebelement(driver, cp.getContactActivityHistory(30), "Activity History");
							if (cp.getContactActivityHistoryErrorMessage(30).getText().trim()
									.contains(ContactPageErrorMessage.activityHistoryErrorMessage)) {
								appLog.info("Activity Alert is not available in Contact page.");
							} else {
								appLog.info("Activity Alert is available in Contact page.");
								sa.assertTrue(false, "Activity Alert is available in Contact page.");
							}
							driver.close();
							driver.switchTo().window(parentID);
						} else {
							appLog.info("No new weindow is open");
							sa.assertTrue(false, "No new window is open");
						}
					} else {
						appLog.info("Not able to click on contact 2 name");
						sa.assertTrue(false, "Not able to click on contact2 name");
					}
					switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
					scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30),
							"Fundraising Section view");
					ele = FindElement(driver, "//a[text()='" + M10Institution2 + "']", "Contact 2 firm name",
							action.SCROLLANDBOOLEAN, 60);
					if (click(driver, ele, "Contact2 firm name ", action.SCROLLANDBOOLEAN)) {
						String parentID = switchOnWindow(driver);
						if (parentID != null) {
							scrollDownThroughWebelement(driver, ip.getNoteAndAttachmentText(30), "Activity History");
							if (cp.getContactActivityHistoryErrorMessage(30).getText().trim()
									.contains(ContactPageErrorMessage.activityHistoryErrorMessage)) {
								appLog.info("Activity Alert is not available in Contact page.");
							} else {
								appLog.info("Activity Alert is available in Contact page.");
								sa.assertTrue(false, "Activity Alert is available in Contact page.");
							}
							driver.close();
							driver.switchTo().window(parentID);
						} else {
							appLog.info("No new window is open");
							sa.assertTrue(false, "No new window is open");
						}
					} else {
						appLog.info("Not able to click on firm name");
						sa.assertTrue(false, "Not able to click on firm name");
					}
				} else {
					appLog.info("Not able to click on manage emails");
					sa.assertTrue(false, "Not able to click on manage emails");
				}
			} else {
				appLog.info("Not able to click on created fund");
				sa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on funds tab");
			sa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		String investormailcontent = null;
		try {
			investormailcontent = new EmailLib().getInvestorCustomMailContent("InvitationMail", gmailUserName,
					gmailPassword, CRMUser1EmailID, M10Contact2EmailId, "Custom Invitation Email for PE");
		} catch (InterruptedException e) {
			appLog.info("Invitation mail not found.");

			e.printStackTrace();
		}
		System.out.println(investormailcontent);
		if (investormailcontent != null) {
			if (investormailcontent
					.contains("We are happy to inform you that You are invited to Fund. Please Invest in this Fund.")) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}
		}else{
			appLog.error("Mail didn't receive");
			sa.assertTrue(false, "Mail didn't receive");
		}
		sa.assertAll();		
		}
		
	@Test
	public void M10tc024_UpdateFolderNameFundNameAndVerifyItsImpactInManageEmailsPopUpAndReceivedMail(){
	LoginPageBusinessLayer	lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,
				excelLabel.StandardPath);
		String sharedPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,
				excelLabel.SharedPath);
		String commonPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,
				excelLabel.CommonPath);
		String[] folderPaths = {stdPath, sharedPath, commonPath };
		SoftAssert sa = new SoftAssert();
		String id = null;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M10FundName1+"NUP")) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30),
						"Investor Workspace Section view");
				if (click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 60), "Manage folder icon",
						action.SCROLLANDBOOLEAN)) {
					for (int i = 0; i < 3; i++) {
						id = fp.getCreatedFolderId(folderPaths[i], PageName.ManageFolderPopUp, 20);
						System.err.println("Folder Id is: >>>>>>" + id);
						if (id != null) {
							if (fp.clickOnRenameFolderButton(id)) {
								ThreadSleep(2000);
								if (sendKeys(driver,
										fp.getParentRenameFolderNameTextBox(Workspace.InvestorWorkspace,
												PageName.ManageFolderPopUp, 20),
										"UP"+folderPaths[i].split(" \\(")[0], commonPath + " folder text box", action.BOOLEAN)) {
									if (click(driver,
											fp.getParentRenameFolderSaveButton(Workspace.InvestorWorkspace,
													PageName.ManageFolderPopUp, 20),
											"sub folder save button", action.BOOLEAN)) {
										appLog.info("click on save button");
									} else {
										appLog.info("Not able to click on save button");
										sa.assertTrue(false, "Not able to click on save button");
									}
								} else {
									appLog.info("Not able to enter value to rename text box");
									sa.assertTrue(false, "Not able to enter value in rename text box");
								}
							} else {
								appLog.info("Not bale to clcik on rename folder button");
								sa.assertTrue(false, "Not able to click on rename folder button");
							}
						} else {
							appLog.info("folder is not available in manage folder");
							sa.assertTrue(false, "folder is not available in manage folder");
						}
					}
					if (click(driver, fp.getManageFolderCloseButton(Workspace.InvestorWorkspace, 60),
							"Manage Folder Close Button", action.SCROLLANDBOOLEAN)) {
						appLog.info("click on manage folder close button");
					} else {
						appLog.error("Not able to click on manage folder close button");
						sa.assertTrue(false, "Not able to click on manage folder folder close button ");
					}
					if (fp.verifyFolderPathdummy("UP"+stdPath, M10Institution1, M10LimitedPartner1, M10FundName1,
							PageName.FundsPage, Workspace.InvestorWorkspace, 60)) {
						appLog.info("Updated standard folder is displaying");
					} else {
						appLog.info("Updated folder is not displaying");
						sa.assertTrue(false, "Updtaed folder is not displaying");
					}
					if (fp.verifyFolderPathdummy("UP"+commonPath, null, null, M10FundName1, PageName.FundsPage,
							Workspace.FundraisingWorkspace, 60)) {
						appLog.info("Updated Common folder is displaying");
					} else {
						appLog.info("Updated Common is not displaying");
						sa.assertTrue(false, "Updtaed Common folder is not displaying");
					}
					if (fp.verifyFolderPathdummy("UP"+sharedPath, null, null, M10FundName1, PageName.FundsPage,
							Workspace.FundraisingWorkspace, 60)) {
						appLog.info("Updated Shared folder is displaying");
					} else {
						appLog.info("Updated Shared is not displaying");
						sa.assertTrue(false, "Updtaed Shared folder is not displaying");
					}
				} else {
					appLog.info("Not able to click on manage folder icon");
					sa.assertTrue(false, "Not able to click on manage folder icon");
				}

				if (click(driver, fp.getInvestmentInfo(Workspace.InvestorWorkspace), "Investment info",
						action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getInvestmentInfoEdit(60), "investment info edit button",
							action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, fp.getInvestmentInfoFundNameTxtbox(60), M10FundName1+"UPINVW","Fund name text box",
								action.BOOLEAN)) {
							if (click(driver, fp.getInvestmentInfoSaveBtn(40), "Investment Info save button",
									action.SCROLLANDBOOLEAN)) {
								if (getText(driver, fp.getFirmName(30), "Fund Name", action.SCROLLANDBOOLEAN)
										.equals(M10FundName1+"UPINVW")) {
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
					if(click(driver, fp.getInvestmentInfoCancelButton(60), "Investment info cancel button", action.SCROLLANDBOOLEAN)){
						appLog.info("Clicked on cancel button");
					}else{
						appLog.info("Not able to click on cancel button");
						sa.assertTrue(false, "Not able to click on cancel button");						
					}
				} else {
					appLog.info("Not able to click on investment info link");
					sa.assertTrue(false, "Not able to click on investment info link");
				}
				switchToDefaultContent(driver);
//		scrollDownThroughWebelement(driver,bp.getFundsTab(60), "Funds Tab");
			if(click(driver, bp.getEditButton(60), "Edit button", action.SCROLLANDBOOLEAN)){
		if(sendKeys(driver, fp.getFundName(60), M10FundName1+"NUPINV", "Fund name 1", action.SCROLLANDBOOLEAN)){
			if(click(driver, bp.getSaveButton(60), "Save button", action.SCROLLANDBOOLEAN)){
				if(fp.getFundNameInViewMode(60).getText().trim().contains(M10FundName1+"NUPINV")){
					appLog.info("Fund NAme get updated successfully");
				}else{
					appLog.info("Fund name does not get updated successfully");
					sa.assertTrue(false, "Fund Anme does not get updated successfully");
				}			
			}else{
				appLog.info("Not able to click on save button");
				sa.assertTrue(false, "Not able to click on save buton");
			}			
		}else{
			appLog.info("Not able to enter value in fund name text box");
			sa.assertTrue(false, "Not able to enter value in fund name text box");
		}				
			}	else{
				appLog.info("Not able to click on edit button");
				sa.assertTrue(false, "Not able to click on edit icon");				
			}
			switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
			scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30),
					"Investor Workspace Section view");		
			if (fp.verifyFolderPathdummy("UP"+commonPath, null, null, M10FundName1, PageName.FundsPage,
					Workspace.InvestorWorkspace, 60)) {
				appLog.info("Updated Common folder is displaying");
				if(click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage EMail icon", action.SCROLLANDBOOLEAN)){
					String contactaccessDropdown=getSelectedOptionOfDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60), "Contact access view dropodwn", "text");
					if(contactaccessDropdown.equalsIgnoreCase("All Folders")){
						appLog.info("All folders is selected in the dropdown");
					}else{
						appLog.info("All folder is ot slected in the dropdown");
						sa.assertTrue(false, "All folder is ot slected in the dropdown");
					}
					if (fp.verifyManageEmailGrid(
							M10Contact1FirstName + "RUP" + " " + M10Contact1LastName+ "RUP",M10Contact1EmailId, M10Institution1 + "RUP",
							"Last Invite Date")) {
						appLog.info("Grid Data is verified for the contact: "+ M10Contact1FirstName + " " + M10Contact1LastName);
					} else {
						appLog.info("Grid Data is not verified for the contact: "
								+ M10Contact1FirstName + " " + M10Contact1LastName);
						sa.assertTrue(false, "Grid Data is not verified for the contact: "
								+ M10Contact1FirstName + " " + M10Contact1LastName);
					}
					if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,M10Contact2EmailId, M10Institution2, null)) {
						appLog.info("Grid Data is verified for the contact: "+ M10Contact2FirstName + " " + M10Contact2LastName);
					} else {
						appLog.info("Grid Data is not verified for the contact: "+ M10Contact2FirstName + " " + M10Contact2LastName);
						sa.assertTrue(false, "Grid Data is not verified for the contact: "+ M10Contact2FirstName + " " + M10Contact2LastName);
					}					
					if(click(driver, fp.getManageEmailCancelBtn(60), "Manage mail cancel button", action.SCROLLANDBOOLEAN)){
						appLog.info("Clicked on cancel button");
					}else{
						appLog.info("Not able to click on cancel button");
						sa.assertTrue(false, "Not able toclick on cancel button");
					}
				}else{
					appLog.info("Not able to click on manage email icon");
					sa.assertTrue(false, "Not able to click on manage email icon");
				}
			} else {
				appLog.info("Updated Common is not displaying");
				sa.assertTrue(false, "Updtaed Common folder is not displaying");
			}		
			if (fp.verifyFolderPathdummy("UP"+sharedPath, null, null, M10FundName1, PageName.FundsPage,
					Workspace.InvestorWorkspace, 60)) {
				appLog.info("Updated shared folder is displaying");
				if(click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage EMail icon", action.SCROLLANDBOOLEAN)){
					String contactaccessDropdown=getSelectedOptionOfDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60), "Contact access view dropodwn", "text");
					if(contactaccessDropdown.equalsIgnoreCase("UP"+sharedPath)){
						appLog.info("UP"+sharedPath+"  is selected in the dropdown");
					}else{
						appLog.info("UP"+sharedPath+"  is not slected in the dropdown");
						sa.assertTrue(false, "UP"+sharedPath+"  is not slected in the dropdown");
					}
					if (fp.verifyManageEmailGrid(
							M10Contact1FirstName + "RUP" + " " + M10Contact1LastName+ "RUP",M10Contact1EmailId, M10Institution1 + "RUP",
							"Last Invite Date")) {
						appLog.info("Grid Data is verified for the contact: "+ M10Contact1FirstName + " " + M10Contact1LastName);
					} else {
						appLog.info("Grid Data is not verified for the contact: "
								+ M10Contact1FirstName + " " + M10Contact1LastName);
						sa.assertTrue(false, "Grid Data is not verified for the contact: "
								+ M10Contact1FirstName + " " + M10Contact1LastName);
					}
					if (fp.verifyManageEmailGrid(M10Contact2FirstName + " " + M10Contact2LastName,M10Contact2EmailId, M10Institution2, null)) {
						appLog.info("Grid Data is verified for the contact: "+ M10Contact2FirstName + " " + M10Contact2LastName);
					} else {
						appLog.info("Grid Data is not verified for the contact: "+ M10Contact2FirstName + " " + M10Contact2LastName);
						sa.assertTrue(false, "Grid Data is not verified for the contact: "+ M10Contact2FirstName + " " + M10Contact2LastName);
					}					
					if(click(driver, fp.getManageEmailCancelBtn(60), "Manage mail cancel button", action.SCROLLANDBOOLEAN)){
						appLog.info("Clicked on cancel button");
					}else{
						appLog.info("Not able to click on cancel button");
						sa.assertTrue(false, "Not able toclick on cancel button");
					}
				}else{
					appLog.info("Not able to click on manage email icon");
					sa.assertTrue(false, "Not able to click on manage email icon");
				}
			} else {
				appLog.info("Updated shared is not displaying");
				sa.assertTrue(false, "Updtaed shared folder is not displaying");
			}		
			if (fp.verifyFolderPathdummy("UP"+stdPath, M10Institution1, M10LimitedPartner1, M10FundName1, PageName.FundsPage,
					Workspace.InvestorWorkspace, 60)) {
				appLog.info("Updated shared folder is displaying");
				if(click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage EMail icon", action.SCROLLANDBOOLEAN)){
					String contactaccessDropdown=getSelectedOptionOfDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60), "Contact access view dropodwn", "text");
					if(contactaccessDropdown.equalsIgnoreCase("UP"+stdPath)){
						appLog.info("UP"+stdPath+"  is selected in the dropdown");
					}else{
						appLog.info("UP"+stdPath+"  is not slected in the dropdown");
						sa.assertTrue(false, "UP"+stdPath+"  is not slected in the dropdown");
					}
					if (fp.verifyManageEmailGrid(
							M10Contact1FirstName + "RUP" + " " + M10Contact1LastName+ "RUP",M10Contact1EmailId, M10Institution1 + "RUP",
							"Last Invite Date")) {
						appLog.info("Grid Data is verified for the contact: "+ M10Contact1FirstName + " " + M10Contact1LastName);
					} else {
						appLog.info("Grid Data is not verified for the contact: "
								+ M10Contact1FirstName + " " + M10Contact1LastName);
						sa.assertTrue(false, "Grid Data is not verified for the contact: "
								+ M10Contact1FirstName + " " + M10Contact1LastName);
					}
				if(click(driver, fp.getManageEmailInvitationEmailTemplateEditPreviewTextList().get(0), "Edit link", action.SCROLLANDBOOLEAN)){
					if (fp.getManageEmailEditFormEmailIdtext(60).getText().trim().contains(CRMUser1EmailID)) {
						appLog.info("Manage Email Invitation Email Edit Form Email Id is verified.");
					} else {
						appLog.info("Manage Email Invitation Email Edit Form Email Id is not verified.");
						sa.assertTrue(false, "Manage Email Invitation Email Edit Form Email Id is not verified.");
					}
					if (fp.getManageEmailEditSubjectText(60).getText().trim().contains("Invitation from")) {
						appLog.info("Manage Email Invitation Email Edit Subjetc Text is verified.");
					} else {
						appLog.info("Manage Email Invitation Email Edit Subjetc Text is not verified.");
						sa.assertTrue(false, "Manage Email Invitation Email Edit Subjetc Text is not verified.");
					}
					if (fp.getManageEmailEditSubjectText(60).getText().trim().contains(Org1FirmName+"UP")) {
						appLog.info("Manage Email Invitation Email Edit Subjetc Text is  verified.");
					} else {
						appLog.info("Manage Email Invitation Email Edit Subjetc Text is not verified.");
						sa.assertTrue(false, "Manage Email Invitation Email Edit Subjetc Text is not verified.");
					}
					if (fp.getManageEmailEditHelloText(60).getText().trim().contains("Hello")) {
						appLog.info("Manage Email Invitation Email Edit Hello Text is verified.");
					} else {
						appLog.info("Manage Email Invitation Email Edit Hello Text is not verified.");
						sa.assertTrue(false, "Manage Email Invitation Email Edit Hello Text is not verified.");
					}
					if (fp.getManageEmailEditHelloText(60).getText().trim().contains("Recipient first Name},")) {
						appLog.info("Manage Email Invitation Email Edit Recipient First Name Text is verified.");
					} else {
						appLog.info(
								"Manage Email Invitation Email Edit Recipient First Name Text is not verified.");
						sa.assertTrue(false,
								"Manage Email Invitation Email Edit Recipient First Name Text is not verified.");
					}
					if (fp.getGetManageEmailEditGrantAccessText(60).getText().trim().contains(
							"You have been granted access to Current investments of " + M10FundName1+"UPINVW" + " by")) {
						appLog.info("Manage Email Invitation Email Edit Grant Access Text is verified.");
					} else {
						appLog.info("Manage Email Invitation Email Edit Grant Access Text is not verified.");
						sa.assertTrue(false,
								"Manage Email Invitation Email Edit Grant Access Text is not verified.");
					}
					if (fp.getGetManageEmailEditGrantAccessText(60).getText().trim().contains(Org1FirmName+"UP")) {
						appLog.info("Manage Email Invitation Email Edit Grant Access Text is verified.");
					} else {
						appLog.info("Manage Email Invitation Email Edit Grant Access Text is not verified.");
						sa.assertTrue(false,
								"Manage Email Invitation Email Edit Grant Access Text is not verified.");
					}
					if (fp.getManageEmailEditRegisterNotRegisterText(60).getText().trim()
							.contains("If you have not yet registered, Click here to register.")) {
						appLog.info(
								"Manage Email Invitation Email Edit If you have not yet registered Text is verified.");
					} else {
						appLog.info(
								"Manage Email Invitation Email Edit If you have not yet registered Text is not verified.");
						sa.assertTrue(false,
								"Manage Email Invitation Email Edit If you have not yet registered Text is not verified.");
					}
					if (fp.getManageEmailEditRegisterNotRegisterText(60).getText().trim()
							.contains("If you have already registered, Click here to login")) {
						appLog.info(
								"Manage Email Invitation Email Edit If you have already registered Text is verified.");
					} else {
						appLog.info(
								"Manage Email Invitation Email Edit If you have already registered Text is not verified.");
						sa.assertTrue(false,
								"Manage Email Invitation Email Edit If you have already registered Text is not verified.");
					}
					if (fp.getManageEmailEditBottomText(60).getText().trim().contains(
							"If you believe this has been sent in error, or if you cannot login, please contact")) {
						appLog.info("Manage Email Invitation Email Edit Bottom Text is verified.");
					} else {
						appLog.info("Manage Email Invitation Email Edit Bottom Text is not verified.");
						sa.assertTrue(false, "Manage Email Invitation Email Edit Bottom Text is not verified.");
					}
					if (fp.getManageEmailEditBottomText(60).getText().trim().contains(Org1FirmName+"UP")) {
						appLog.info("Manage Email Invitation Email Edit Bottom Text is verified.");
					} else {
						appLog.info("Manage Email Invitation Email Edit Bottom Text is not verified.");
						sa.assertTrue(false, "Manage Email Invitation Email Edit Bottom Text is not verified.");
					}
				if (click(driver, fp.getManageEmailInvitationTextBoxCancelButton(30), "Cancel button",
							action.SCROLLANDBOOLEAN)) {
					}else{
					appLog.info("Not able to click on cancel button");
					sa.assertTrue(false,"Not able to click on cancel button");
				}				
				}else{
					appLog.info("Not able to clcik on edit link");
					sa.assertTrue(false, "Not able to click on edit link");
				}
				
	WebElement	ele=FindElement(driver,"//a[text()='"+M10Contact1FirstName+"RUP"+" "+M10Contact1LastName+"RUP"+"']/../..//input", "Contact1 checkbox", action.SCROLLANDBOOLEAN, 60);
		if(click(driver, ele, "Contact1 checkbox", action.SCROLLANDBOOLEAN)){
			if (click(driver, fp.getmanageEmailsendBtn(60), "Send Button",
					action.SCROLLANDBOOLEAN)) {
				if (click(driver, fp.getManageEmailSendInvitationConfirmationYesBtn(60),
						"Yes button", action.SCROLLANDBOOLEAN)) {
							
				}else{
					appLog.info("Not able to clcik on yes button");
					sa.assertTrue(false, "Not able to click on yes button");
				}
			}else{
				appLog.info("Not able to click on manage email send button ");
				sa.assertTrue(false, "Not able to click on send button");
			}
		}	else{
			appLog.info("Not able to click on contact 1 checkbox");
			sa.assertTrue(false, "Not able to click on contact1 checkbox");
		}			
				}else{
					appLog.info("Not able to click on manage email icon");
					sa.assertTrue(false, "Not able to click on manage email icon");
				}				
			} else {
				appLog.info("Updated standard is not displaying");
				sa.assertTrue(false, "Updtaed standard folder is not displaying");
			}	
			} else {
				appLog.info("Not able to click on created fund");
				sa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able ot click on funds tab");
			sa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		String investormailcontent = null;
		ThreadSleep(2000);
		try {
			investormailcontent = new EmailLib().getInvestorMailContent("invitationMail", gmailUserName, gmailPassword,
					CRMUser1EmailID, M10Contact1EmailId);
		} catch (InterruptedException e) {
			appLog.info("Invitation mail not found.");

			e.printStackTrace();
		}
		ThreadSleep(5000);
		if (investormailcontent != null) {
			if (investormailcontent.contains("You have been granted access to Current investments of " + M10FundName1+"UPINVW"+ " by " + Org1FirmName+"UP" + ".")) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}
			if (investormailcontent.contains("If you have not yet registered")) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}
			if (investormailcontent.contains("If you have already registered")) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}
			if (investormailcontent.contains("Click here") && investormailcontent.contains("to register.")) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}
			if (investormailcontent.contains("Click here") && investormailcontent.contains("to login")) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}
			if (investormailcontent
					.contains("If you believe this has been sent in error, or if you cannot login, please contact "
							+ Org1FirmName+"UP"+ ".")) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}

			if (investormailcontent.contains("Hello "+M10Contact1FirstName)) {
				appLog.info("Mail content is verified");
			} else {
				appLog.info("Mail content is not verified");
				sa.assertTrue(false, "Mail content is not verified");
			}
		}else{
			appLog.info("Mail didn't receive");
			sa.assertTrue(false, "Mail didn't receive");
		}
		sa.assertAll();			
	}
	
	@Test
	public void M10tc025_CheckRenamingOfContactAndAccountNameImpactOnManageEmailCheckDeletionOfContactAndChangeOfEmailImpactOnManageEmails(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		FundsPageBusinessLayer fp=new FundsPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.ContactTab)) {
			appLog.info("Clicked on COntact Tab");
		if(cp.clickOnCreatedContact(M10Contact2FirstName+"NUP", M10Contact2LastName+"NUP", null)){
		if(click(driver, bp.getEditButton(60), "Edit button", action.SCROLLANDBOOLEAN)){
		if(sendKeys(driver, cp.getEmailId(60), M10Contact2UpdatedEmailID, "Contact2 email id", action.SCROLLANDBOOLEAN)){
		if(click(driver, bp.getSaveButton(60), "Save button", action.SCROLLANDBOOLEAN)){
			appLog.info("click on save button");
			if(cp.getEmailIdViewMode(60).getText().trim().contains(M10Contact2UpdatedEmailID)){
				appLog.info("Email id get changed successfully");
			}else{
				appLog.info("Email id does not get changed successfully");
				sa.assertTrue(false, "Email id does not get changed successfully");
			}	
		}else{
			appLog.info("Not able to click on save button");
			sa.assertTrue(false, "Not able to click on save button");
		}			
		}else{
			appLog.info("Not able to enter value in e,mail text box");
			sa.assertTrue(false, "Not able to enter value in email tetx box");
		}		
		}else{
			appLog.info("Not able ot click on edit button");
			sa.assertTrue(false, "Not able to click on edit button");
		}		
		}else{
			appLog.info("Not able to click on created contact");
			sa.assertTrue(false, "Not able to click on created contact");
		}
		}else{
			appLog.info("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M10FundName1+"NUPINV")) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30),
						"Investor Section view");
				if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage Email icon",
						action.SCROLLANDBOOLEAN)) {
					WebElement	ele=FindElement(driver,"//a[text()='"+M10Contact2FirstName+" "+M10Contact2LastName+"']", "Contact2 name", action.SCROLLANDBOOLEAN, 60);
					if (clickUsingJavaScript(driver, ele, "Contact 2")) {
					//if(click(driver, ele, "Contact 2", action.SCROLLANDBOOLEAN)){
				String errorMessage=switchToAlertAndGetMessage(driver, 60, action.GETTEXT);   
				if(errorMessage.contains(ContactPageErrorMessage.ContactemailChangedErrorMessage)){
					appLog.info("Error Message is verified");
				}else{
					appLog.info("Error Message is not verified");
					sa.assertTrue(false, "Error Message is not verified");
				}
				switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);		
				}else{
						appLog.info("Not able to click on contact2 name");
						sa.assertTrue(false, "Not able to click on contact2 name ");
				}
				ele=FindElement(driver,"//a[text()='"+M10Institution2+"']", "Contact2 firm name", action.SCROLLANDBOOLEAN, 60);
				if (clickUsingJavaScript(driver, ele, "Contact 2 firm name")) {
				//if(click(driver, ele, "Contact 2 firm name", action.SCROLLANDBOOLEAN)){
				String errorMessage=switchToAlertAndGetMessage(driver, 60, action.GETTEXT);   
				if(errorMessage.contains(ContactPageErrorMessage.ContactemailChangedErrorMessage)){
					appLog.info("Error Message is verified");
				}else{
					appLog.info("Error Message is not verified");
					sa.assertTrue(false, "Error Message is not verified");
				}
				switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);		
				}else{
						appLog.info("Not able to click on contact2 Firm Name");
						sa.assertTrue(false, "Not able to click on contact2 Firm Name ");
				}					
				}else{
					appLog.info("Not bale to click on manage email icon");
					sa.assertTrue(false, "Not able to click on manage email");
				}
			}else{
				appLog.info("Not able to click on created fund");
				sa.assertTrue(false, "Not able to click on craeted fund");
			}
		}else{
			appLog.info("Not able to click on funds tab");
			sa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		if (bp.clickOnTab(TabName.ContactTab)) {
			appLog.info("Clicked on COntact Tab");
			if(cp.clickOnCreatedContact(M10Contact1FirstName, M10Contact1LastName, null)){
				if (fp.clickUsingCssSelectorPath("input[value=Delete]",  "Delete button	")) {
				//if(click(driver, cp.getDeleteButtonContactsPage(60), "Delete button	", action.SCROLLANDBOOLEAN)){
					switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
					if(cp.verifyDeletedContact(M10Contact1FirstName+" "+M10Contact1LastName)){
						appLog.info("Contact get deleted successfully");
					}else{
						appLog.info("Contact does not get delete successfully");
						sa.assertTrue(false, "Contact does not get deleted successfulluy");
					}
				}else{
					appLog.info("Not able to click on delete button");
					sa.assertTrue(false, "Not able to click on delete bvutton");
				}
			}else{
				appLog.info("Not able to click on created ocntact");
				sa.assertTrue(false, "Not able to click on created contact");
			}
		}else{
			appLog.info("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M10FundName1+"NUPINV")) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30),
						"Investor Section view");
				if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage Email icon",
						action.SCROLLANDBOOLEAN)) {
					WebElement	ele=FindElement(driver,"//a[text()='"+M10Contact1FirstName+"RUP"+" "+M10Contact1LastName+"RUP"+"']", "Contact1 name", action.SCROLLANDBOOLEAN, 60);
					if (clickUsingJavaScript(driver, ele,  "Contact 1")) {
					//if(click(driver, ele, "Contact 1", action.SCROLLANDBOOLEAN)){
				String errorMessage=switchToAlertAndGetMessage(driver, 60, action.GETTEXT);   
				if(errorMessage.contains(ContactPageErrorMessage.ContactemailChangedErrorMessage)){
					appLog.info("Error Message is verified");
				}else{
					appLog.info("Error Message is not verified");
					sa.assertTrue(false, "Error Message is not verified");
				}
				switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);		
				}else{
						appLog.info("Not able to click on contact2 name");
						sa.assertTrue(false, "Not able to click on contact2 name ");
				}
				ele=FindElement(driver,"//a[text()='"+M10Institution1+"RUP"+"']", "Contact1 firm name", action.SCROLLANDBOOLEAN, 60);
				if (clickUsingJavaScript(driver, ele, "Contact 1 firm name")) {
				//if(click(driver, ele, "Contact 1 firm name", action.SCROLLANDBOOLEAN)){
				String errorMessage=switchToAlertAndGetMessage(driver, 60, action.GETTEXT);   
				if(errorMessage.contains(ContactPageErrorMessage.ContactemailChangedErrorMessage)){
					appLog.info("Error Message is verified");
				}else{
					appLog.info("Error Message is not verified");
					sa.assertTrue(false, "Error Message is not verified");
				}
				switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);		
				}else{
						appLog.info("Not able to click on contact1 Firm Name");
						sa.assertTrue(false, "Not able to click on contact1 Firm Name ");
				}					
				}else{
					appLog.info("Not bale to click on manage email icon");
					sa.assertTrue(false, "Not able to click on manage email");
				}
			}else{
				appLog.info("Not able to click on created fund");
				sa.assertTrue(false, "Not able to click on craeted fund");
			}
		}else{
			appLog.info("Not able to click on funds tab");
			sa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();	
	}
		
	@Test
	public void M10tc026_VerifyManageEmailsPopUpAfterRemovingCompleteAccessForAContact(){
			LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
			BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
			FundsPageBusinessLayer fp=new FundsPageBusinessLayer(driver);
			SoftAssert sa = new SoftAssert();
			lp.CRMLogin(superAdminUserName, adminPassword);
			if(bp.clickOnTab(TabName.FundsTab)){
				if(fp.clickOnCreatedFund(M10FundName1+"NUPINV")){
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
							"fundraising Section view");	
				if(fp.closeWorkSpace(Workspace.FundraisingWorkspace, 60)){
					appLog.info("workspace get closed successfully");					
				}else{
					appLog.info("Workspace does not get closed successfully");
					sa.assertTrue(false, "Workspace does not get closed successfully");
				}
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30),
							"Investor Section view");	
				if(fp.closeWorkSpace(Workspace.InvestorWorkspace, 60)){
					appLog.info("workspace get closed successfully");					
				}else{
					appLog.info("Workspace does not get closed successfully");
					sa.assertTrue(false, "Workspace does not get closed successfully");
				}			
				}else{
					appLog.info("Not able to click on created fund");
					sa.assertTrue(false, "Not able to click on created fund");
				}
			}else{
				appLog.info("Not able to click on funds tab");
				sa.assertTrue(false, "Not able to click on funds tab");
			}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		driver.close();
		config(browserToLaunch);
		lp=new LoginPageBusinessLayer(driver);
		bp=new BasePageBusinessLayer(driver);
		fp=new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(bp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M10FundName1+"NUPINV")){
			switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
			scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
						"fundraising Section view");	
		if(click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Mange email icon", action.SCROLLANDBOOLEAN)){
			if (fp.getManageEMailContactGridErrorMsg(30).getText().trim().contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
				appLog.info("No data to display error message is verified");
			} else {
				appLog.error("No data to display error messsage is not verified");
				sa.assertTrue(false,"No data to display error messsage is not verified");
			}	
			if(click(driver, fp.getManageEmailCancelBtn(60), "Cancel button", action.SCROLLANDBOOLEAN)){
				appLog.info("CLicked on manage email cancel button");
			}else{
				appLog.info("Not able to click on manage email cancel button");
				sa.assertTrue(false, "Not able to click on manage email cancel button");
			}
		}else{
			appLog.info("Not able to click on manage email icon");
			sa.assertTrue(false, "Not able to click on manage email icon");
		}
		switchToDefaultContent(driver);
		switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
		scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30),
					"Investor  Section view");	
	if(click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Mange email icon", action.SCROLLANDBOOLEAN)){
		if (fp.getManageEMailContactGridErrorMsg(30).getText().trim().contains(FundsPageErrorMessage.noDataToDisplayErrorMessage)) {
			appLog.info("No data to display error message is verified");
		} else {
			appLog.error("No data to display error messsage is not verified");
			sa.assertTrue(false,"No data to display error messsage is not verified");
		}	
		if(click(driver, fp.getManageEmailCancelBtn(60), "Cancel button", action.SCROLLANDBOOLEAN)){
			appLog.info("CLicked on manage email cancel button");
		}else{
			appLog.info("Not able to click on manage email cancel button");
			sa.assertTrue(false, "Not able to click on manage email cancel button");
		}
	}else{
		appLog.info("Not able to click on manage email icon");
		sa.assertTrue(false, "Not able to click on manage email icon");
	}
	}else{
		appLog.info("Not able to click on created fund");
		sa.assertTrue(false, "Not able to click on created fund");
			}
	}else{
		appLog.info("Not able to click on funds tab");
		sa.assertTrue(false, "Not able to click on funds tab");
	}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();	
	}
		
	@Test
	public void M10tc027_VerifyManageEmailsPopUpAfterInvitingContactAgain(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		String sharedPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M10FundName1+"NUPINV")) {
				if (fp.inviteContact(environment, mode, "", M10Contact2UpdatedEmailID, "UP"+sharedPath, FolderType.Shared,
						"Download", "Yes", "No", null, Workspace.FundraisingWorkspace, M10Contact2UpdatedEmailID)) {
					appLog.info("Successfully provided access to contact '" + M10Contact2FirstName + " "
							+ M10Contact2LastName + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M10Contact2FirstName + " "
							+ M10Contact2LastName + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M10Contact2FirstName + " "
						+ M10Contact2LastName + "'.");
				}		
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
							"fundraising Section view");	
				if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage email icon",
						action.SCROLLANDBOOLEAN)) {
					if (fp.verifyManageEmailGrid(M10Contact2FirstName+"NUP" + " " + M10Contact2LastName+"NUP", M10Contact2UpdatedEmailID,
							M10Institution2+"NUP", null)) {
						appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
								+ M10Contact2LastName);
					} else {
						appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
								+ M10Contact2LastName);
						sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
								+ M10Contact2LastName);
					}
					if (click(driver, fp.getManageEmailCancelBtn(60), "Manage Email Cancel Button",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on cancel button");
					} else {
						appLog.info("Clicked on cancel button");
						sa.assertTrue(false, "clicked on cancel button");
					}
				} else {
					appLog.info("Not able to clcik on manage email");
					sa.assertTrue(false, "Not able to click on manage email");
				}
				switchToDefaultContent(driver);
				if (fp.inviteContact(environment, mode, "", M10Contact2UpdatedEmailID, "UP"+sharedPath, FolderType.Shared,
						"Download", "Yes", "No", null, Workspace.InvestorWorkspace, M10Contact2UpdatedEmailID)) {
					appLog.info("Successfully provided access to contact '" + M10Contact2FirstName + " "
							+ M10Contact2LastName + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M10Contact2FirstName + " "
							+ M10Contact2LastName + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M10Contact2FirstName + " "
						+ M10Contact2LastName + "'.");
				}		
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30),
							"fundraising Section view");	
				if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage email icon",
						action.SCROLLANDBOOLEAN)) {
					if (fp.verifyManageEmailGrid(M10Contact2FirstName+"NUP" + " " + M10Contact2LastName+"NUP", M10Contact2UpdatedEmailID,
							M10Institution2+"NUP", null)) {
						appLog.info("Grid Data is verified for the contact: " + M10Contact2FirstName + " "
								+ M10Contact2LastName);
					} else {
						appLog.info("Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
								+ M10Contact2LastName);
						sa.assertTrue(false, "Grid Data is not verified for the contact: " + M10Contact2FirstName + " "
								+ M10Contact2LastName);
					}
					if (click(driver, fp.getManageEmailCancelBtn(60), "Manage Email Cancel Button",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on cancel button");
					} else {
						appLog.info("Clicked on cancel button");
						sa.assertTrue(false, "clicked on cancel button");
					}
				} else {
					appLog.info("Not able to clcik on manage email");
					sa.assertTrue(false, "Not able to click on manage email");
				}				
			}else{
				appLog.info("Not able to click on created fund");
				sa.assertTrue(false, "Not able to click on created fund");
			}
		}else{
			appLog.info("Not able to click on funds tab");
			sa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);		
		sa.assertAll();		
	}
	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void M10tc028_postCondition(){
		LoginPageBusinessLayer	 lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert saa1 = new SoftAssert();
		lp.CRMLogin(superAdminUserName, adminPassword);
		saa1=bp.postCondition();
		sa.combineAssertions(saa1);
		lp.CRMlogout(environment, mode);
		sa.assertAll();		
		}
}


