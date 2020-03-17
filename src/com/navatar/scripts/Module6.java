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
import com.navatar.pageObjects.CommitmentPageBusinessLayer;
import com.navatar.pageObjects.ContactPageBusinessLayer;
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

/**
 * @author Parul Singh
 *
 */
public class Module6 extends BaseLib {
	static String filterPath = System.getProperty("user.dir") + "/TestCases_Filter.xlsx";
	
	@Test
	public void M6tc001_Module6_preCondition() {
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
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String instutionName = null;
		for (int i = 0; i < 3; i++) {
			instutionName = ExcelUtils.readData("Institutions", excelLabel.Variable_Name, "M6Instituition" + (i + 1),
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
		for (int i = 0; i < 1; i++) {
			String fundName = ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M6Fund" + (i + 1),
					excelLabel.Fund_Name);
			if (bp.clickOnTab(TabName.FundsTab)) {
				String fundType = ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M6Fund" + (i + 1),
						excelLabel.Fund_Type);
				String investmentCategory = ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M6Fund" + (i + 1),
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
			instutionName = ExcelUtils.readData("Contacts", excelLabel.Variable_Name, "M6Contact" + (i + 1),
					excelLabel.Institutions_Name);
			String ContactFirstName = ExcelUtils.readData("Contacts", excelLabel.Variable_Name, "M6Contact" + (i + 1),
					excelLabel.Contact_FirstName);
			String ContactLastName = ExcelUtils.readData("Contacts", excelLabel.Variable_Name, "M6Contact" + (i + 1),
					excelLabel.Contact_LastName);
			if (bp.clickOnTab(TabName.ContactTab)) {
				if (cp.createContact(ContactFirstName, ContactLastName, instutionName, emailId)) {
					appLog.info("contact is created: " + ContactFirstName + " " + ContactLastName);
					if (emailId != "") {
						ExcelUtils.writeData(emailId, "Contacts", excelLabel.Variable_Name, "M6Contact" + (i + 1),
								excelLabel.Contact_EmailId);
					}
				} else {
					appLog.error("Not able to create contact: " + ContactFirstName + " " + ContactLastName);
					saa.assertTrue(false, "Not able to create contact: " + ContactFirstName + " " + ContactLastName);
				}
			}
		}
		for (int i = 0; i < 4; i++) {
			String lpName = ExcelUtils.readData("Limited Partner", excelLabel.Variable_Name,
					"M6LimitedPartner" + (i + 1), excelLabel.LimitedPartner_Name);
			if (bp.clickOnTab(TabName.InstituitonsTab)) {
				if (i != 3) {
					instutionName = ExcelUtils.readData("Institutions", excelLabel.Variable_Name,
							"M6Instituition" + (i + 1), excelLabel.Institutions_Name);
				}
				if (ip.createLimitedPartner(lpName, instutionName)) {
					appLog.info("limited partner is created: " + lpName);
				} else {
					appLog.error("Not able to create limited partner: " + lpName);
					saa.assertTrue(false, "Not able to create limited partner: " + lpName);
				}
			} else {
				appLog.error("Not able to click on institution tab so cannot create limite partner: " + lpName);
				saa.assertTrue(false, "Not able to click on institution tab so cannot create limite partner: " + lpName);
			}
	}
		if(nim.getMyProfileFistNameAndLastNameAndFirmName("User1")) {
			appLog.info("CRM User first,last name and firm name successfully write in excel");
		}else {
			appLog.error("Not able to write CRM User first,last name and firm profile in excel");
			saa.assertTrue(false, "Not able to write CRM User first,last name and firm profile in excel");
		}
			switchToDefaultContent(driver);
			lp.CRMlogout();
			sa.combineAssertions(saa);
			sa.assertAll();
		}
	
	@Test
	public void M6tc002_BuildWorkspaceAndImportFolderTemplateAndValidateManageInvestorUI() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String[] step1of3 = { "", "",
				ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M6Fund1", excelLabel.Fund_Contact),
				ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M6Fund1", excelLabel.Fund_Phone),
				ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M6Fund1", excelLabel.Fund_Email), "" };
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M6FundName1)) {
				if (fp.buildWorkspace(step1of3, WorkSpaceAction.IMPORTFOLDERTEMPLATE, folderTemplateName, null, null,
						Workspace.FundraisingWorkspace, 60)) {
					appLog.info("Workspace is created successfully");
					switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
					if (click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60),
							"Manage Investor icon", action.SCROLLANDBOOLEAN)) {
						if (fp.getManageInvestorHeader(Workspace.FundraisingWorkspace, 60) != null) {
							appLog.info("Manage Investor popup is displaying");
							if (fp.getManageInvestorFilterInvestorHeader(Workspace.FundraisingWorkspace, 60) != null) {
								appLog.info("Filter Invetsor header tab is displaying");
								if (fp.getManageInvestorInvestorAccountsHeader(Workspace.FundraisingWorkspace,
										60) != null) {
									if (fp.getManageInvestorInstitutionsColoumnLabel(60) != null) {
										appLog.info("Manage Investor Institutions coloumn label is displaying");
										if (fp.getManageInvestorShowDropdown(Workspace.FundraisingWorkspace,
												60) != null) {
											appLog.info("Show dropdown is  displaying");
											List<WebElement> showDropdownValues = allOptionsInDropDrop(driver, fp
													.getManageInvestorShowDropdown(Workspace.FundraisingWorkspace, 60),
													"Show dropdown values");
											for (int i = 0; i < showDropdownValues.size(); i++) {
												if (showDropdownValues.get(i).getText()
														.equalsIgnoreCase("All Institutions")) {
													appLog.info(showDropdownValues.get(i).getText()
															+ " :Opiton is available in the list.");
												} else if (showDropdownValues.get(i).getText()
														.equalsIgnoreCase("Institutions with Folder Built")) {
													appLog.info(showDropdownValues.get(i).getText()
															+ " :Opiton is available in the list.");
												} else if (showDropdownValues.get(i).getText()
														.equalsIgnoreCase("Institutions without Folder Built")) {
													appLog.info(showDropdownValues.get(i).getText()
															+ " :Opiton is available in the list.");
												} else {
													appLog.info(showDropdownValues.get(i).getText()
															+ " :Opiton is not available in the list.");
													saa.assertTrue(false, showDropdownValues.get(i).getText()
															+ " :Opiton is not available in the list.");
												}
											}
											if (fp.getManageInvestorDoneButton(Workspace.FundraisingWorkspace,
													60) != null) {
												appLog.info("Done button is displaying");
											} else {
												appLog.info("Done Button is not displaying");
												saa.assertTrue(false, "Done Button is not displaying");
											}
											if (bp.verifyErrorMessageOnPage(
													FundsPageErrorMessage.noDataToDisplayErrorMessage,
													fp.getManageInvestorNoDataDisplayErrorMesage(
															Workspace.FundraisingWorkspace, 60),
													"No Data to display error message  for fundraising workspace")) {
												appLog.info(
														"Error Message is verified  on Funds page for Fundraising workspace");
											} else {
												saa.assertTrue(false,
														"Error Message is not verified  on Funds page for Fundraising workspace.Expected:"
																+ FundsPageErrorMessage.noDataToDisplayErrorMessage
																+ " Actual"
																+ getText(driver,
																		fp.getManageInvestorNoDataDisplayErrorMesage(
																				Workspace.FundraisingWorkspace, 60),
																		"No Data to display error message  for fundraising workspace",
																		action.BOOLEAN));
											}
											switchToDefaultContent(driver);
											switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
											if (click(driver,
													fp.getManageInvestorDoneButton(Workspace.FundraisingWorkspace, 60),
													"Done Button", action.SCROLLANDBOOLEAN)) {
												appLog.info("Clicked on done button");
												ThreadSleep(2000);
												if (fp.getManageInvestorDoneButton(Workspace.FundraisingWorkspace,
														10) == null) {
													appLog.info("Manage Invetsor popup get closed successfully");
												} else {
													appLog.info("Manage Investor popup is not closed successfully");
													saa.assertTrue(false,
															"Manage Investor popup is not closed successfully");
												}
											} else {
												appLog.info("Not able to click on done button");
												saa.assertTrue(false, "Not able to click on done button");
											}
										} else {
											appLog.info("Show dropdown is not displaying");
											saa.assertTrue(false, "Show dropdown is not displaying");
										}
									} else {
										appLog.info("Manage Investor Institutions coloumn label is not displaying");
										saa.assertTrue(false,
												"Manage Investor Institutions coloumn label is not displaying");
									}
								} else {
									appLog.info("Invetsor account header is not displaying");
									saa.assertTrue(false, "Invetsor account header is not displaying");
								}
							} else {
								appLog.info("Filter Invetsor header tab is not displaying");
								saa.assertTrue(false, "Filter Invetsor header tab is not displaying");
							}
						} else {
							appLog.info("Manage Investor popup is not displaying");
							saa.assertTrue(false, "Manage Investor popup is not displaying");
						}
					} else {
						appLog.info("not able to click on manage investor icon so we cannot check manage investor UI");
						saa.assertTrue(false,
								"not able to click on manage investor icon so we cannot check manage investor UI");
					}
				} else {
					appLog.info("workspace is not created successfully");
					saa.assertTrue(false, "workspace is not craeted successfully");
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
	public void M6tc003_CheckManageInvestorWithFundraisingDone() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		WebElement ele = null;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		List<String> institutionName = new ArrayList<String>();
		institutionName.add(M6Institution1);
		institutionName.add(M6Institution2);
		institutionName.add(M6Institution3);
		for (int i = 0; i < 3; i++) {
			String fundraisingName = ExcelUtils.readData("Fundraisings", excelLabel.Variable_Name,
					"M6FundRaising" + (i + 1), excelLabel.FundRaising_Name);
			if (bp.clickOnTab(TabName.FundraisingsTab)) {
				String fundName = ExcelUtils.readData("Fundraisings", excelLabel.Variable_Name,
						"M6FundRaising" + (i + 1), excelLabel.Fund_Name);
				String instutionName = ExcelUtils.readData("Fundraisings", excelLabel.Variable_Name,
						"M6FundRaising" + (i + 1), excelLabel.Institutions_Name);
				if (frp.createFundRaising(fundraisingName, fundName, instutionName)) {
					appLog.info("fundraising is created : " + fundraisingName);
				} else {
					appLog.error("Not able to create fundraising: " + fundraisingName);
					sa.assertTrue(false, "Not able to create fundraising: " + fundraisingName);
				}
			} else {
				appLog.error("Not able to click on fundraising tab so cannot create fundraising: " + fundraisingName);
				sa.assertTrue(false,
						"Not able to click on fundraising tab so cannot create fundraising: " + fundraisingName);
			}
		}
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M6FundName1)) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				if (click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60), "Manage Investor icon",
						action.SCROLLANDBOOLEAN)) {
					List<WebElement> institutions = fp.getManageInvestorPopupInstitutionList();
					for (int j = 0; j < institutions.size(); j++) {
						String institutiontext = institutions.get(j).getText().trim();
						if (compareMultipleListWithoutAssertion(institutiontext, institutionName)) {
							appLog.info("ALl institution are displaying");
						} else {
							appLog.info("ALl institution are displaying");
							saa.assertTrue(false, "ALl institution are displaying");
						}
					}
					List<WebElement> allInstitutionCheckbox = FindElements(driver,
							"//span[contains(@id,'grid_SelectedTargetAccounts-cell-0-')]//input",
							"ALl Institution checkbox");
					for (WebElement Checkbox : allInstitutionCheckbox) {
						if (isSelected(driver, Checkbox, "Institution checkbox")) {
							appLog.info("Institution checkbox is selected");
							saa.assertTrue(false, "Institution checkbox is selected");
						} else {
							appLog.info("Institution checkbox is not selected");
						}
					}

					if (selectVisibleTextFromDropDown(driver,
							fp.getManageInvestorShowDropdown(Workspace.FundraisingWorkspace, 60), "Show Dropdown",
							"Institutions with Folder Built")) {
						appLog.info("Able to select value form show dropdown");
						if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.noDataToDisplayErrorMessage,
								fp.getManageInvestorNoDataDisplayErrorMesage(Workspace.FundraisingWorkspace, 60),
								"No Data to display error message  for fundraising workspace")) {
							appLog.info("Error Message is verified  on Funds page for Fundraising workspace");
						} else {
							saa.assertTrue(false,
									"Error Message is not verified  on Funds page for Fundraising workspace.Expected:"
											+ FundsPageErrorMessage.noDataToDisplayErrorMessage + " Actual"
											+ getText(driver,
													fp.getManageInvestorNoDataDisplayErrorMesage(
															Workspace.FundraisingWorkspace, 60),
													"No Data to display error message  for fundraising workspace",
													action.BOOLEAN));
						}
					} else {
						appLog.info("Not able to select value from show dropdown");
						saa.assertTrue(false, "Not able to select value from show dropdown");
					}
					if (selectVisibleTextFromDropDown(driver,
							fp.getManageInvestorShowDropdown(Workspace.FundraisingWorkspace, 60), "Show Dropdown",
							"Institutions without Folder Built")) {
						appLog.info("Able to select value form show dropdown");
						institutions = fp.getManageInvestorPopupInstitutionList();
						for (int j = 0; j < institutions.size(); j++) {
							String institutiontext = institutions.get(j).getText().trim();
							if (compareMultipleListWithoutAssertion(institutiontext, institutionName)) {
								appLog.info("ALl institution are displaying");
							} else {
								appLog.info("ALl institution are displaying");
								saa.assertTrue(false, "ALl institution are displaying");
							}
						}
						allInstitutionCheckbox = FindElements(driver,
								"//span[contains(@id,'grid_SelectedTargetAccounts-cell-0-')]//input",
								"ALl Institution checkbox");
						for (WebElement Checkbox : allInstitutionCheckbox) {
							if (isSelected(driver, Checkbox, "Institution checkbox")) {
								appLog.info("Institution checkbox is selected");
								saa.assertTrue(false, "Institution checkbox is selected");
							} else {
								appLog.info("Institution checkbox is not selected");
							}
						}

					} else {
						appLog.info("Not able to select value from show dropdown");
						saa.assertTrue(false, "Not able to select value from show dropdown");
					}
					ele = FindElement(driver, "//div[text()='" + M6Institution1 + "']/../..//input",
							"Institution 1 checkbox	", action.SCROLLANDBOOLEAN, 60);
					if (click(driver, ele, "Institution 1 checkbox", action.SCROLLANDBOOLEAN)) {
						switchToDefaultContent(driver);
						switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
						if (fp.getManageInvestorAddedPopupHeader(Workspace.FundraisingWorkspace, 60) != null) {
							appLog.info("Confirmation popup is displaying");
							if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.manageInvestorAddedPopupMessage,
									fp.getManageInvestorAddedPopupMessage(Workspace.FundraisingWorkspace, 60),
									"Investor Added Message ")) {
								appLog.info("Error Message is verified  Manage Investor Added Popup");
							} else {
								saa.assertTrue(false,
										"Error Message is not verified  Manage Investor Added Popup .Expected:"
												+ FundsPageErrorMessage.manageInvestorAddedPopupMessage + " Actual"
												+ getText(driver,
														fp.getManageInvestorAddedPopupMessage(
																Workspace.FundraisingWorkspace, 60),
														"Investor Added Message ", action.BOOLEAN));
							}
							if (fp.getManageInvestorAddedPopupCloseButton(Workspace.FundraisingWorkspace, 60) != null) {
								appLog.info("Close button is displaying");
							} else {
								appLog.info("Close Button is not displaying");
								saa.assertTrue(false, "Close Button is not displaying");
							}
							if (fp.getManageInvestorAddedPopupCrossIcon(Workspace.FundraisingWorkspace, 60) != null) {
								appLog.info("Cross icon is displaying");
							} else {
								appLog.info("Cross icon is not displaying");
								saa.assertTrue(false, "Cross icon is not displaying");
							}
							if (click(driver,
									fp.getManageInvestorAddedPopupCloseButton(Workspace.FundraisingWorkspace, 60),
									"Close Button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on close button");
								if (fp.getManageInvestorAddedPopupCloseButton(Workspace.FundraisingWorkspace,
										10) != null) {
									appLog.info("Popup is not closed");
									saa.assertTrue(false, "Popup is not closed");
								} else {
									appLog.info("Popup is closed");
								}
							} else {
								appLog.info("Not able to click on close button");
								saa.assertTrue(false, "Not able to click on close button");
							}
						} else {
							appLog.info("Confirmation popup is not displaying");
							saa.assertTrue(false, "Confirmation popup is not displaying");
						}
					} else {
						appLog.info("Not able to click on institution 1 checkbox");
						saa.assertTrue(false, "Not able to clik on Institution 1 checkbox");
					}
					ele = FindElement(driver, "//div[text()='" + M6Institution2 + "']/../..//input",
							"Institution 2 checkbox	", action.SCROLLANDBOOLEAN, 60);
					if (click(driver, ele, "Institution 2 checkbox", action.SCROLLANDBOOLEAN)) {
						switchToDefaultContent(driver);
						switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
						if (fp.getManageInvestorAddedPopupHeader(Workspace.FundraisingWorkspace, 60) != null) {
							appLog.info("Confirmation popup is displaying");
							if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.manageInvestorAddedPopupMessage,
									fp.getManageInvestorAddedPopupMessage(Workspace.FundraisingWorkspace, 60),
									"Investor Added Message ")) {
								appLog.info("Error Message is verified  Manage Investor Added Popup");
							} else {
								saa.assertTrue(false,
										"Error Message is not verified  Manage Investor Added Popup .Expected:"
												+ FundsPageErrorMessage.manageInvestorAddedPopupMessage + " Actual"
												+ getText(driver,
														fp.getManageInvestorAddedPopupMessage(
																Workspace.FundraisingWorkspace, 60),
														"Investor Added Message ", action.BOOLEAN));
							}
							if (fp.getManageInvestorAddedPopupCloseButton(Workspace.FundraisingWorkspace, 60) != null) {
								appLog.info("Close button is displaying");
							} else {
								appLog.info("Close Button is not displaying");
								saa.assertTrue(false, "Close Button is not displaying");
							}
							if (fp.getManageInvestorAddedPopupCrossIcon(Workspace.FundraisingWorkspace, 60) != null) {
								appLog.info("Cross icon is displaying");
							} else {
								appLog.info("Cross icon is not displaying");
								saa.assertTrue(false, "Cross icon is not displaying");
							}
							if (click(driver,
									fp.getManageInvestorAddedPopupCrossIcon(Workspace.FundraisingWorkspace, 60),
									"Cross icon", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on Cross icon");
								if (fp.getManageInvestorAddedPopupCrossIcon(Workspace.FundraisingWorkspace,
										10) != null) {
									appLog.info("Popup is not closed");
									saa.assertTrue(false, "Popup is not closed");
								} else {
									appLog.info("Popup is closed");
								}
							} else {
								appLog.info("Not able to click on Cross icon");
								saa.assertTrue(false, "Not able to click on Cross icon");
							}
						} else {
							appLog.info("Confirmation popup is not displaying");
							saa.assertTrue(false, "Confirmation popup is not displaying");
						}
					} else {
						appLog.info("Not able to click on institution 2 checkbox");
						saa.assertTrue(false, "Not able to click on Institution 2 checkbox");
					}
					if (click(driver, fp.getManageInvestorPopupCrossIcon(Workspace.FundraisingWorkspace, 60),
							"Cross icon", action.SCROLLANDBOOLEAN)) {
						if (fp.clickOnInstituionFolder(M6Institution1, Workspace.FundraisingWorkspace, 60)) {
							appLog.info("Institution1 is displaying");
						} else {
							appLog.info("Institution1 is not displaying");
							saa.assertTrue(false, "Institution1 is not displaying");
						}
						if (fp.clickOnInstituionFolder(M6Institution2, Workspace.FundraisingWorkspace, 60)) {
							appLog.info("Institution2 is displaying");
						} else {
							appLog.info("Institution2 is not displaying");
							saa.assertTrue(false, "Institution2 is not displaying");
						}
					} else {
						appLog.info("Not able to click on manage invetor cross icon");
						saa.assertTrue(false, "Not able to click on manage investor cross icon");
					}
					if (click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60),
							"Manage Investor Icon", action.SCROLLANDBOOLEAN)) {
						ThreadSleep(5000);
						if (selectVisibleTextFromDropDown(driver,
								fp.getManageInvestorShowDropdown(Workspace.FundraisingWorkspace, 60), "Show Dropdown",
								"Institutions with Folder Built")) {
							appLog.info("Able to select value form show dropdown");
							institutionName.clear();
							institutionName.add(M6Institution1);
							institutionName.add(M6Institution2);
							institutions = fp.getManageInvestorPopupInstitutionList();
							for (int j = 0; j < institutions.size(); j++) {
								String institutiontext = institutions.get(j).getText().trim();
								if (compareMultipleListWithoutAssertion(institutiontext, institutionName)) {
									appLog.info("ALl institution are displaying");
								} else {
									appLog.info("ALl institution are displaying");
									saa.assertTrue(false, "ALl institution are displaying");
								}
							}
							allInstitutionCheckbox = FindElements(driver,
									"//span[contains(@id,'grid_SelectedTargetAccounts-cell-0-')]//input",
									"ALl Institution checkbox");
							for (WebElement Checkbox : allInstitutionCheckbox) {
								if (isSelected(driver, Checkbox, "Institution checkbox")) {
									appLog.info("Institution checkbox is selected");

								} else {
									appLog.info("Institution checkbox is not selected");
									saa.assertTrue(false, "Institution checkbox is not selected");
								}
							}
							bp.checkSorting(SortOrder.Assecending, fp.getManageInvestorPopupInstitutionList());
							click(driver, fp.getManageInvestorInstitutionsColoumnLabel(60), "Institution cooumn header",
									action.SCROLLANDBOOLEAN);
							bp.checkSorting(SortOrder.Decending, fp.getManageInvestorPopupInstitutionList());
							click(driver, fp.getManageInvestorInstitutionsColoumnLabel(60), "Institution cooumn header",
									action.SCROLLANDBOOLEAN);
							bp.checkSorting(SortOrder.Assecending, fp.getManageInvestorPopupInstitutionList());
						} else {
							appLog.info("Not able to select value from show dropdown");
							saa.assertTrue(false, "Not able to select value from show dropdown");
						}
						ThreadSleep(3000);
						if (selectVisibleTextFromDropDown(driver,
								fp.getManageInvestorShowDropdown(Workspace.FundraisingWorkspace, 60), "Show Dropdown",
								"Institutions without Folder Built")) {
							appLog.info("Able to select value form show dropdown");
							institutionName.clear();
							institutionName.add(M6Institution3);
							institutions = fp.getManageInvestorPopupInstitutionList();
							for (int j = 0; j < institutions.size(); j++) {
								String institutiontext = institutions.get(j).getText().trim();
								if (compareMultipleListWithoutAssertion(institutiontext, institutionName)) {
									appLog.info("ALL institution are displaying");
								} else {
									appLog.info("ALL institution are displaying");
									saa.assertTrue(false, "ALL institution are displaying");
								}
							}
							allInstitutionCheckbox = FindElements(driver,
									"//span[contains(@id,'grid_SelectedTargetAccounts-cell-0-')]//input",
									"ALl Institution checkbox");
							for (WebElement Checkbox : allInstitutionCheckbox) {
								if (isSelected(driver, Checkbox, "Institution checkbox")) {
									appLog.info("Institution checkbox is selected");
									saa.assertTrue(false, "Institution checkbox is  selected");
								} else {
									appLog.info("Institution checkbox is not selected");

								}
							}
						} else {
							appLog.info("Not able to select value from show dropdown");
							saa.assertTrue(false, "Not able to select value from show dropdown");
						}
					} else {
						appLog.info("Not able to click on manage investor icon");
						saa.assertTrue(false, "Not able to click on manage investor icon");
					}

				} else {
					appLog.info("Not able to click on manage Investor icon");
					saa.assertTrue(false, "Not able to click on manage investor icon");
				}
			} else {
				appLog.info("Not able to click on created fund");
				saa.assertTrue(false, "Not able to click on created fund");
			}

		} else {
			appLog.info("Not able to click on funds tab");
			saa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M6tc004_ProvideContactAccess() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M6FundName1)) {
				if (fp.inviteContact(M6Institution1, M6Contact1EmailId, null, FolderType.Standard, "Upload", "Yes",
						"No", null, Workspace.FundraisingWorkspace, null)) {
					appLog.info("Contact get invited successfully");
				} else {
					appLog.info("Not able to invite contact");
					saa.assertTrue(false, "Not able to invite contact");
				}
				if (fp.inviteContact(M6Institution2, M6Contact2EmailId, null, FolderType.Standard, "Upload", "Yes",
						"No", null, Workspace.FundraisingWorkspace, M6Contact2EmailId)) {
					appLog.info("Contact get invited successfully");
				} else {
					appLog.info("Not able to invite contact");
					saa.assertTrue(false, "Not able to invite contact");
				}
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 30));
				if (fp.sendInvitationMail(Workspace.FundraisingWorkspace, M6Contact1EmailId, "All Folders", null)) {
					appLog.info("Mail sent succesfully to user 1");
				} else {
					appLog.info("Mail not sent succesfully to user 1");
					saa.assertTrue(false, "Mail not sent succesfully to user 1");
				}
				if (fp.sendInvitationMail(Workspace.FundraisingWorkspace, M6Contact2EmailId, M6Institution2, null)) {
					appLog.info("Mail sent succesfully to user 2");
				} else {
					appLog.info("Mail not sent succesfully to user 2");
					saa.assertTrue(false, "Mail not sent succesfully to user 2");
				}
			} else {
				appLog.info("Not able to click on created fund");
				saa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on Funds tab");
			saa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		bp = new BasePageBusinessLayer(driver);
		lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer afp = new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		if (ExcelUtils.readData("Contacts", excelLabel.Variable_Name, "M6Contact1", excelLabel.Registered)
				.equalsIgnoreCase("Not Registered")) {
			try {
				investorRegLink = new EmailLib().getInvestorRegLink("InvitationMail", gmailUserName, gmailPassword,
						CRMUser1EmailID, M6Contact1EmailId);
				appLog.info("Investor Registration Link: " + investorRegLink);
			} catch (InterruptedException e) {
				appLog.info("Invitation mail not found.");
				e.printStackTrace();
			}
			if (investorRegLink != null) {
				driver.get(investorRegLink);
				if (bp.targetRegistration(M6Contact1FirstName, M6Contact1LastName, M6Contact1EmailId, M6Institution1,
						adminPassword)) {
					appLog.info(
							"Investor is registered successfully: " + M6Contact1FirstName + " " + M6Contact1LastName);
					ExcelUtils.writeData("Registered", "Contact", excelLabel.Variable_Name, "M6Contact1",
							excelLabel.Registered);
				} else {
					driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
					appLog.info("Register Target URL through Direct URL..");
					if (bp.targetRegistration(M6Contact1FirstName, M6Contact1LastName, M6Contact1EmailId,
							M3Institution1, adminPassword)) {
						appLog.info("Investor is registered successfully: " + M6Contact1FirstName + " "
								+ M6Contact1LastName);
						ExcelUtils.writeData("Registered", "Contact", excelLabel.Variable_Name, "M6Contact1",
								excelLabel.Registered);
					} else {
						appLog.error("Investor is not Registered through Invited Link: " + M6Contact1FirstName + " "
								+ M6Contact1LastName);
						saa.assertTrue(false, "Investor is not Registered through Invited Link: " + M6Contact1FirstName
								+ " " + M6Contact1LastName);
					}
				}
			} else {
				driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
				appLog.info("Register Target URL through Direct URL..");
				if (bp.targetRegistration(M6Contact1FirstName, M6Contact1LastName, M6Contact1EmailId, M6Institution1,
						adminPassword)) {
					appLog.info(
							"Investor is registered successfully: " + M6Contact1FirstName + " " + M6Contact1LastName);
					ExcelUtils.writeData("Registered", "Contact", excelLabel.Variable_Name, "M6Contact1",
							excelLabel.Registered);
				} else {
					appLog.error("Investor is not Registered through Invited Link: " + M6Contact1FirstName + " "
							+ M6Contact1LastName);
					saa.assertTrue(false, "Investor is not Registered through Invited Link: " + M6Contact1FirstName
							+ " " + M6Contact1LastName);
				}
			}
			String firmName = getSelectedOptionOfDropDown(driver, afp.getFirmNameDropdown(60), "Firm name dropdown",
					"text");
			if (firmName.equalsIgnoreCase(Org1FirmName)) {
				appLog.info("Firm Page Is opened");
			} else {
				appLog.info("Firm Page is not opened");
				saa.assertTrue(false, "Firm Page is not opened");
			}
			if (isDisplayed(driver, ifp.getActivitiesCreatedOnLabel(60), "Visibility", 60,
					"Activity created on label") == null) {
				appLog.info("All Document Tab is opened");
			} else {
				appLog.info("All Document Tab is not opened");
				saa.assertTrue(false, "All Document Tab is not opened");
			}
			if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
				appLog.info("Clicked on potential investments tab");
				String investment = getSelectedOptionOfDropDown(driver,
						ifp.getPotentialAndCurrentInvestmentInvestmentDropdown(60), "Potential Investment dropdown",
						"text");
				if (investment.equalsIgnoreCase(M6FundName1)) {
					appLog.info("Workspace is visible");
				} else {
					appLog.info("Workspace is not visible");
					saa.assertTrue(false, "Workspace is not visible");
				}
			} else {
				appLog.info("Not able to click on potential investments tab");
				saa.assertTrue(false, "Not able to click on potential investments tab");
			}
			lp.investorLogout();
		} else {
			appLog.info("investor " + M6Contact1FirstName + " " + M6Contact1LastName + " is already Registered.");
			saa.assertTrue(false,
					"investor " + M6Contact1FirstName + " " + M6Contact1LastName + " is already Registered.");
		}
		driver.close();
		config(browserToLaunch);
		bp = new BasePageBusinessLayer(driver);
		lp = new LoginPageBusinessLayer(driver);
		afp = new AllFirmsPageBusinesslayer(driver);
		ifp = new InvestorFirmPageBusinesslayer(driver);
		if (ExcelUtils.readData("Contacts", excelLabel.Variable_Name, "M6Contact2", excelLabel.Registered)
				.equalsIgnoreCase("Not Registered")) {
			try {
				investorRegLink = new EmailLib().getInvestorRegLink("InvitationMail", gmailUserName, gmailPassword,
						CRMUser1EmailID, M6Contact2EmailId);
				appLog.info("Investor Registration Link: " + investorRegLink);
			} catch (InterruptedException e) {
				appLog.info("Invitation mail not found.");
				e.printStackTrace();
			}
			if (investorRegLink != null) {
				driver.get(investorRegLink);
				if (bp.targetRegistration(M6Contact2FirstName, M6Contact2LastName, M6Contact2EmailId, M6Institution1,
						adminPassword)) {
					appLog.info(
							"Investor is registered successfully: " + M6Contact2FirstName + " " + M6Contact2LastName);
					ExcelUtils.writeData("Registered", "Contact", excelLabel.Variable_Name, "M6Contact2",
							excelLabel.Registered);
				} else {
					driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
					appLog.info("Register Target URL through Direct URL..");
					if (bp.targetRegistration(M6Contact2FirstName, M6Contact2LastName, M6Contact2EmailId,
							M6Institution1, adminPassword)) {
						appLog.info("Investor is registered successfully: " + M6Contact2FirstName + " "
								+ M6Contact2LastName);
						ExcelUtils.writeData("Registered", "Contact", excelLabel.Variable_Name, "M6Contact2",
								excelLabel.Registered);
					} else {
						appLog.error("Investor is not Registered through Invited Link: " + M6Contact2FirstName + " "
								+ M6Contact2LastName);
						saa.assertTrue(false, "Investor is not Registered through Invited Link: " + M6Contact2FirstName
								+ " " + M6Contact2LastName);
					}
				}
			} else {
				driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
				appLog.info("Register Target URL through Direct URL..");
				if (bp.targetRegistration(M6Contact2FirstName, M6Contact2LastName, M6Contact2EmailId, M6Institution2,
						adminPassword)) {
					appLog.info(
							"Investor is registered successfully: " + M6Contact2FirstName + " " + M6Contact2LastName);
					ExcelUtils.writeData("Registered", "Contact", excelLabel.Variable_Name, "M6Contact2",
							excelLabel.Registered);
				} else {
					appLog.error("Investor is not Registered through Invited Link: " + M6Contact2FirstName + " "
							+ M6Contact2LastName);
					saa.assertTrue(false, "Investor is not Registered through Invited Link: " + M6Contact2FirstName
							+ " " + M6Contact2LastName);
				}
			}
			String firmName = getSelectedOptionOfDropDown(driver, afp.getFirmNameDropdown(60), "Firm name dropdown",
					"text");
			if (firmName.equalsIgnoreCase(Org1FirmName)) {
				appLog.info("Firm Page Is opened");
			} else {
				appLog.info("Firm Page is not opened");
				saa.assertTrue(false, "Firm Page is not opened");
			}
			if (isDisplayed(driver, ifp.getActivitiesCreatedOnLabel(60), "Visibility", 60,
					"Activity created on label") == null) {
				appLog.info("All Document Tab is opened");
			} else {
				appLog.info("All Document Tab is not opened");
				saa.assertTrue(false, "All Document Tab is not opened");
			}
			if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
				appLog.info("Clicked on potential investments tab");
				String investment = getSelectedOptionOfDropDown(driver,
						ifp.getPotentialAndCurrentInvestmentInvestmentDropdown(60), "Potential Investment dropdown",
						"text");
				if (investment.equalsIgnoreCase(M6FundName1)) {
					appLog.info("Workspace is visible");
				} else {
					appLog.info("Workspace is not visible");
					saa.assertTrue(false, "Workspace is not visible");
				}
			} else {
				appLog.info("Not able to click on potential investments tab");
				saa.assertTrue(false, "Not able to click on potential investments tab");
			}
			lp.investorLogout();
		} else {
			appLog.info("investor " + M6Contact2FirstName + " " + M6Contact2LastName + " is already Registered.");
			saa.assertTrue(false,
					"investor " + M6Contact2FirstName + " " + M6Contact2LastName + " is already Registered.");
		}
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M6tc005_RenameInvestorNameFromInstitutionPageAndCheckOnManageInvestorSection() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		List<String> institutionList = new ArrayList<String>();
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M6Institution1)) {
				if (click(driver, bp.getEditButton(60), "Edit Button", action.SCROLLANDBOOLEAN)) {
					if (sendKeys(driver, ip.getLegalNameTextBox(60), M6Institution1 + "Renamed", "Legal name text box",
							action.SCROLLANDBOOLEAN)) {
						if (click(driver, bp.getSaveButton(60), "Save Button", action.SCROLLANDBOOLEAN)) {
							String updtaedInstitutionName = ip.getLegalNameLabelTextbox(60).getText().trim();
							ThreadSleep(2000);
							if (updtaedInstitutionName.contains(M6Institution1 + "Renamed")) {
								appLog.info("Institution name has been updated successfully");
								ThreadSleep(2000);
								if (bp.clickOnTab(TabName.FundsTab)) {
									if (fp.clickOnCreatedFund(M6FundName1)) {
										switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
										scrollDownThroughWebelement(driver,
												fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
												"FundraisingWorkspace View.");
										if (click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60),
												"Manage Investor Icon", action.SCROLLANDBOOLEAN)) {
											List<WebElement> institutionName = fp
													.getManageInvestorPopupInstitutionList();
											for (int i = 0; i < institutionName.size(); i++) {
												institutionList.add(institutionName.get(i).getText().trim());
											}
											if (institutionList.contains(M6Institution1 + "Renamed")) {
												appLog.info("Updated institution Name is displaying");
												saa.assertTrue(false, "Updtaed institution Name is displaying");
											} else {
												appLog.info("Updated institution Name is not displaying");
											}

											if (institutionList.contains(M6Institution1)) {
												appLog.info("Old institution Name is displaying");
											} else {
												appLog.info("old institution Name is not displaying");
												saa.assertTrue(false, "Old institution Name is not displaying");
											}
										} else {
											appLog.info("Not able to click on Manage Investor icon");
											saa.assertTrue(false, "Not able to click on manage investor icon");
										}
									} else {
										appLog.info("Not able to click on created fund");
										saa.assertTrue(false, "Not able to click on created fund");
									}
								} else {
									appLog.info("Not able to click on funds tab");
									saa.assertTrue(false, "Not able to click on funds tab");
								}
							} else {
								appLog.info("Institution name has not been updated successfully");
								saa.assertTrue(false, "Institution name has not been updated successfully");
							}
						} else {
							appLog.info("Not able to click on save button");
							saa.assertTrue(false, "Not able ot click on save button");
						}
					} else {
						appLog.info("Not able to enter values in legal name text box");
						saa.assertTrue(false, "Not able to enter values in legal name text box");
					}
				} else {
					appLog.info("Not able to click on edit icon");
					saa.assertTrue(false, "Not able to click on edit icon");
				}
			} else {
				appLog.info("Not able to click on craeted institution");
				saa.assertTrue(false, "Not able to click on craeted institution");
			}
		} else {
			appLog.info("Not able to click on Instituitions tab");
			saa.assertTrue(false, "Not able to click on Instituitions tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M6Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			if (fp.clickOnInstituionFolder(M6Institution1, null, 60)) {
				appLog.info("Folder Structure is verified and old institution folder is displaying");
			} else {
				appLog.info("Folder Structure is not verified and old institution folder is displaying");
				saa.assertTrue(false, "Folder Structure is not verified and old institution folder is displaying");
			}
		} else {
			appLog.info("Not able to click on potential invetsment tab");
			saa.assertTrue(false, "Not able to click on potential invetsment tab");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		ip = new InstitutionPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		WebElement ele = null;
		institutionList = new ArrayList<String>();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M6FundName1)) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
						"FundraisingWorkspace View.");
				if (click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60), "Manage Investor Icon",
						action.SCROLLANDBOOLEAN)) {
					ele = FindElement(driver, "//div[@title='" + M6Institution1 + "']/../..//input",
							"Institution 1 checkbox", action.SCROLLANDBOOLEAN, 60);
					if (click(driver, ele, "Instituition 1 checkbox", action.SCROLLANDBOOLEAN)) {
						switchToDefaultContent(driver);
						switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
						if (fp.getManageInvestorDeletedPopupHeader(Workspace.FundraisingWorkspace, 60) != null) {
							appLog.info("Confirmation popup is displaying");
							if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.manageInvestorDeletedPopupMessage,
									fp.getManageInvestorDeletedPopupMessage(Workspace.FundraisingWorkspace, 60),
									"Investor removed Message ")) {
								appLog.info("Error Message is verified  Manage Investor removed Popup");
							} else {
								saa.assertTrue(false,
										"Error Message is not verified  Manage Investor removed Popup .Expected:"
												+ FundsPageErrorMessage.manageInvestorDeletedPopupMessage + " Actual"
												+ getText(driver,
														fp.getManageInvestorDeletedPopupMessage(
																Workspace.FundraisingWorkspace, 60),
														"Investor removed Message ", action.BOOLEAN));
							}
							if (fp.getManageInvestorDeletedPopupCloseButton(Workspace.FundraisingWorkspace,
									60) != null) {
								appLog.info("Close button is displaying");
							} else {
								appLog.info("Close Button is not displaying");
								saa.assertTrue(false, "Close Button is not displaying");
							}
							if (fp.getManageInvestorDeletedPopupCrossIcon(Workspace.FundraisingWorkspace, 60) != null) {
								appLog.info("Cross icon is displaying");
							} else {
								appLog.info("Cross icon is not displaying");
								saa.assertTrue(false, "Cross icon is not displaying");
							}
							if (click(driver,
									fp.getManageInvestorDeletedPopupCrossIcon(Workspace.FundraisingWorkspace, 60),
									"Cross icon", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on Cross icon");
								if (fp.getManageInvestorDeletedPopupCrossIcon(Workspace.FundraisingWorkspace,
										10) != null) {
									appLog.info("Popup is not closed");
									saa.assertTrue(false, "Popup is not closed");
								} else {
									appLog.info("Popup is closed");
									institutionList.clear();
									List<WebElement> institutionName = fp.getManageInvestorPopupInstitutionList();
									for (int i = 0; i < institutionName.size(); i++) {
										institutionList.add(institutionName.get(i).getText().trim());
									}
									if (institutionList.contains(M6Institution1 + "Renamed")) {
										appLog.info("Updated institution Name is displaying");
									} else {
										appLog.info("Updated institution Name is not displaying");
										saa.assertTrue(false, "Updtaed institution Name is not displaying");
									}
									ele = FindElement(driver,
											"//div[@title='" + M6Institution1 + "Renamed" + "']/../..//input",
											"Institution 1 checkbox", action.SCROLLANDBOOLEAN, 60);
									if (click(driver, ele, "Institution 1 checkbox", action.SCROLLANDBOOLEAN)) {
										switchToDefaultContent(driver);
										switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
										if (click(driver,
												fp.getManageInvestorAddedPopupCloseButton(
														Workspace.FundraisingWorkspace, 60),
												"Close Button", action.SCROLLANDBOOLEAN)) {
											if (click(driver,
													fp.getManageInvestorDoneButton(Workspace.FundraisingWorkspace, 60),
													"Done button", action.SCROLLANDBOOLEAN)) {
												switchToDefaultContent(driver);
												switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
												if (fp.clickOnInstituionFolder(M6Institution1 + "Renamed",
														Workspace.FundraisingWorkspace, 60)) {
													appLog.info("Renamed Institution is dipslaying");
												} else {
													appLog.info("Renamed Instituition is not displaying");
													saa.assertTrue(false, "Renamed Instituition is not displaying");
												}
												switchToDefaultContent(driver);
												if (fp.inviteContact(
														M6Institution1 + "Renamed",
														M6Contact1EmailId, null,
														FolderType.Standard, "Upload", "Yes",
														"No", null, Workspace.FundraisingWorkspace,
														null)) {
													appLog.info(
															"Contact get invited successfully");
												} else {
													appLog.info("Not able to invite contact");
													saa.assertTrue(false,
															"Not able to invite contact");
												}
											} else {
												appLog.info("Not able to click on done button");
												saa.assertTrue(false, "Not able to click on Done button");
											}
										} else {
											appLog.info("Not able to click on invetsor add popup close button");
											saa.assertTrue(false,
													"Not able to click on invetsor add popup close button");
										}
									} else {
										appLog.info("Not able to click on institution1 checkbox");
										saa.assertTrue(false, "Not able to click on institution1 checkbox");
									}
								}
							} else {
								appLog.info("Not able to click on Cross icon");
								saa.assertTrue(false, "Not able to click on Cross icon");
							}
						} else {
							appLog.info("Confirmation popup is not displaying");
							saa.assertTrue(false, "Confirmation popup is not displaying");
						}
					} else {
						appLog.info("Not able to click on institution1 checkbox");
						saa.assertTrue(false, "Not able to click on institution1 checkbox");
					}

				} else {
					appLog.info("Not able to click on manage investor icon");
					saa.assertTrue(false, "Not able to click on manage investor icon");
				}
			} else {
				appLog.info("Not able to click on created fund");
				saa.assertTrue(false, "Not able to clickon created fund");
			}
		} else {
			appLog.info("Not able to cick on funds tab");
			saa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M6tc006_RenameInvestorNameFromManageInvestorWindow() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		List<String> institutionList = new ArrayList<String>();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M6FundName1)) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
						"FundraisingWorkspace View.");
				if (click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60), "Manage Investor Icon",
						action.SCROLLANDBOOLEAN)) {
					if (fp.clickOnRenameManageTargetInstitution(M6Institution3)) {
						appLog.info("Rename icon is visible for institution" + M6Institution3);
						saa.assertTrue(false, "Rename icon is visible for institution" + M6Institution3);
					} else {
						appLog.info("Rename icon is not visible for institution" + M6Institution3);
					}
					switchToDefaultContent(driver);
					switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
					if (fp.clickOnRenameManageTargetInstitution(M6Institution1 + "Renamed")) {
						appLog.info("Rename icon is visible for institution" + M6Institution1);

						ThreadSleep(2000);
						String renamePopup = fp.getManageInvestorRenamePopupHeader(Workspace.FundraisingWorkspace, 60)
								.getText().trim();
						if (renamePopup.equalsIgnoreCase("Rename Investor Account")) {
							appLog.info("Rename Manage Investor poup is displaying");
							if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.manageInvestorRenamePopupMessage,
									fp.getManageInvestorRenamePopupMessage(Workspace.FundraisingWorkspace, 60),
									"Investor Rename Message ")) {
								appLog.info("Error Message is verified  Manage Investor Rename Popup");
							} else {
								saa.assertTrue(false,
										"Error Message is not verified  Manage Investor Rename Popup .Expected:"
												+ FundsPageErrorMessage.manageInvestorRenamePopupMessage + " Actual"
												+ getText(driver,
														fp.getManageInvestorRenamePopupMessage(
																Workspace.FundraisingWorkspace, 60),
														"Investor Rename Message ", action.BOOLEAN));
							}

							if (fp.getManageInvestorRenamePopupTextBox(Workspace.FundraisingWorkspace, 60) != null) {
								appLog.info("Rename Text Box is displaying");
							} else {
								appLog.info("Rename Tet Box is not displaying");
								saa.assertTrue(false, "Rename Tet Box is not displaying");
							}
							if (fp.getManageInvestorRenamePopupApplyButton(Workspace.FundraisingWorkspace,
									60) != null) {
								appLog.info("Rename popup apply button is displaying");
							} else {
								appLog.info("Rename popup apply button is not displaying");
								saa.assertTrue(false, "Rename popup apply button is not displaying");
							}
							if (fp.getManageInvestorRenamePopupCancelButton(Workspace.FundraisingWorkspace,
									60) != null) {
								appLog.info("Rename popup cancel button is displaying");
							} else {
								appLog.info("Rename popup cancel button is not displaying");
								saa.assertTrue(false, "Rename popup cancel button is not displaying");
							}
							if (sendKeys(driver,
									fp.getManageInvestorRenamePopupTextBox(Workspace.FundraisingWorkspace, 60),
									M6Institution1 + "Update", "Text Box ", action.SCROLLANDBOOLEAN)) {
								switchToDefaultContent(driver);
								switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
								if (click(driver,
										fp.getManageInvestorRenamePopupCancelButton(Workspace.FundraisingWorkspace, 60),
										"Cancel button", action.SCROLLANDBOOLEAN)) {
									appLog.info("Clicked on cancel button");
									List<WebElement> institutionName = fp.getManageInvestorPopupInstitutionList();
									for (int i = 0; i < institutionName.size(); i++) {
										institutionList.add(institutionName.get(i).getText().trim());
									}
									if (institutionList.contains(M6Institution1 + "Renamed")) {
										appLog.info("Old institution Name is displaying");
									} else {
										appLog.info("Old institution Name is not displaying");
										saa.assertTrue(false, "Old institution Name is not displaying");
									}
									if (institutionList.contains(M6Institution1 + "Update")) {
										appLog.info("Renamed institution Name is displaying");
										saa.assertTrue(false, "Renamed institution Name is displaying");
									} else {
										appLog.info("Renamed institution Name is not displaying");
									}
								} else {
									appLog.info("Not able to click on cancel button");
									saa.assertTrue(false, "Not able to click on cancel button");
								}
							} else {
								appLog.info("Not able to enter value in text box");
								saa.assertTrue(false, "Not able to enter value in text box");
							}
						} else {
							appLog.info("Rename Manage Invetsor Popup is not displaying");
							saa.assertTrue(false, "Rename Manage Invetsor Popup is not displaying");
						}
					} else {
						appLog.info("Rename icon is not visible for institution" + M6Institution1);
						saa.assertTrue(false, "Rename icon is visible for institution" + M6Institution1);
					}
					switchToDefaultContent(driver);
					switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
					if (fp.clickOnRenameManageTargetInstitution(M6Institution1 + "Renamed")) {
						switchToDefaultContent(driver);
						switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
						if (sendKeys(driver, fp.getManageInvestorRenamePopupTextBox(Workspace.FundraisingWorkspace, 60),
								M6Institution1 + "Update&*", "Text Box ", action.SCROLLANDBOOLEAN)) {
							switchToDefaultContent(driver);
							switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
							if (click(driver,
									fp.getManageInvestorRenamePopupApplyButton(Workspace.FundraisingWorkspace, 60),
									"Apply button", action.SCROLLANDBOOLEAN)) {
								String alerttext = switchToAlertAndGetMessage(driver, 60, action.GETTEXT);
								if (alerttext.equalsIgnoreCase(
										FundsPageErrorMessage.manageInvestorRenameSpecialCharacterMessage)) {
									appLog.info("Alert text is verified");
								} else {
									appLog.info("Alert text is not verified");
									saa.assertTrue(false, "Alert text is not veriifed");
								}
								switchToDefaultContent(driver);
								switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
								switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
								fp.getManageInvestorRenamePopupTextBox(Workspace.FundraisingWorkspace, 60).clear();
								if (click(driver,
										fp.getManageInvestorRenamePopupApplyButton(Workspace.FundraisingWorkspace, 60),
										"Apply button", action.SCROLLANDBOOLEAN)) {
									if (bp.verifyErrorMessageOnPage(
											FundsPageErrorMessage.manageInvestorRenameFieldBlankErrorMessage,
											fp.getManageInvestorRenamePopupFieldBlankErrorMessage(
													Workspace.FundraisingWorkspace, 60),
											"Rename Text Box Blank Error Message")) {
										appLog.info(
												"Error Message is verified  Manage Investor Rename Popup when field is blank");
									} else {
										saa.assertTrue(false,
												"Error Message is not verified  Manage Investor Rename Popup when field is blank .Expected:"
														+ FundsPageErrorMessage.manageInvestorRenameFieldBlankErrorMessage
														+ " Actual"
														+ getText(driver,
																fp.getManageInvestorRenamePopupFieldBlankErrorMessage(
																		Workspace.FundraisingWorkspace, 60),
																"Rename Text Box Blank Error Message ",
																action.BOOLEAN));
									}
								} else {
									appLog.info("Not able to click on apply button");
									saa.assertTrue(false, "Not able to click on apply button");
								}
							} else {
								appLog.info("Not able to click on Apply Button button");
								saa.assertTrue(false, "Not able to click on Apply button");
							}
						} else {
							appLog.info("Not able to enter value in text box");
							saa.assertTrue(false, "Not able to enter value in text box");
						}
						switchToDefaultContent(driver);
						switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
						if (sendKeys(driver, fp.getManageInvestorRenamePopupTextBox(Workspace.FundraisingWorkspace, 60),
								M6Institution1 + "Update", "Text Box ", action.SCROLLANDBOOLEAN)) {
							switchToDefaultContent(driver);
							switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
							if (click(driver,
									fp.getManageInvestorRenamePopupCrossIcon(Workspace.FundraisingWorkspace, 60),
									"Cross Icon", action.SCROLLANDBOOLEAN)) {
								switchToDefaultContent(driver);
								switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
								institutionList.clear();
								List<WebElement> institutionName = fp.getManageInvestorPopupInstitutionList();
								for (int i = 0; i < institutionName.size(); i++) {
									institutionList.add(institutionName.get(i).getText().trim());
									System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>" + institutionList);
								}
								switchToDefaultContent(driver);
								switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
								if (institutionList.contains(M6Institution1 + "Renamed")) {
									appLog.info("Old institution Name is displaying");
								} else {
									appLog.info("Old institution Name is not displaying");
									saa.assertTrue(false, "Old institution Name is not displaying");
								}
								if (institutionList.contains(M6Institution1 + "Update")) {
									appLog.info("Renamed institution Name is displaying");
									saa.assertTrue(false, "Renamed institution Name is displaying");
								} else {
									appLog.info("Renamed institution Name is not displaying");
								}
							} else {
								appLog.info("Not able to click on Cross Icon");
								saa.assertTrue(false, "Not able to click on Cross Icon");
							}
						} else {
							appLog.info("Not able to enter value in text box");
							saa.assertTrue(false, "Not able to enter value in text box");
						}

					} else {
						appLog.info("Not able to click on rename icon ");
						saa.assertTrue(false, "Not able to click on rename icon ");
					}
					switchToDefaultContent(driver);
					switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
					if (fp.clickOnRenameManageTargetInstitution(M6Institution1 + "Renamed")) {
						switchToDefaultContent(driver);
						switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
						if (sendKeys(driver, fp.getManageInvestorRenamePopupTextBox(Workspace.FundraisingWorkspace, 60),
								M6Institution1 + "Update", "Text Box ", action.SCROLLANDBOOLEAN)) {
							switchToDefaultContent(driver);
							switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
							if (click(driver,
									fp.getManageInvestorRenamePopupApplyButton(Workspace.FundraisingWorkspace, 60),
									"Apply button", action.SCROLLANDBOOLEAN)) {
								institutionList.clear();
								List<WebElement> institutionName = fp.getManageInvestorPopupInstitutionList();
								for (int i = 0; i < institutionName.size(); i++) {
									institutionList.add(institutionName.get(i).getText().trim());
								}
								switchToDefaultContent(driver);
								switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
								if (institutionList.contains(M6Institution1 + "Renamed")) {
									appLog.info("Old institution Name is displaying");
									saa.assertTrue(false, "Old institution Name is  displaying");
								} else {
									appLog.info("Old institution Name is not displaying");
								}
								switchToDefaultContent(driver);
								switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
								if (institutionList.contains(M6Institution1 + "Update")) {
									appLog.info("Renamed institution Name is displaying");
								} else {
									appLog.info("Renamed institution Name is  not displaying");
									saa.assertTrue(false, "Renamed institution Name is  not displaying");
								}
								switchToDefaultContent(driver);
								switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
								if (click(driver, fp.getManageInvestorDoneButton(Workspace.FundraisingWorkspace, 60),
										"Done Button", action.SCROLLANDBOOLEAN)) {
									switchToDefaultContent(driver);
									switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
									if (fp.clickOnInstituionFolder(M6Institution1 + "Update",
											Workspace.FundraisingWorkspace, 60)) {
										appLog.info("Institution 1 with rename is dipslaying");
									} else {
										appLog.info("Institution 1 with rename is not displaying");
										saa.assertTrue(false, "Institution 1 with rename is not displaying");
									}
									switchToDefaultContent(driver);
									if (bp.clickOnTab(TabName.InstituitonsTab)) {
										if (ip.verifyDeletedInstitution(M6Institution1 + "Update")) {
											appLog.info("Updated institution is not displaying");
										} else {
											appLog.info("Updated institution is displaying");
											saa.assertTrue(false, "Updated institution is displaying");
										}
									} else {
										appLog.info("Not able to click on institutions Tab");
										saa.assertTrue(false, "Not able to click on institution tab");
									}
									if (bp.clickOnTab(TabName.ContactTab)) {
										if (cp.clickOnCreatedContact(M6Contact1FirstName, M6Contact1LastName, null)) {
											switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
											scrollDownThroughWebelement(driver,
													fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
													"FundraisingWorkspace View.");
											if (fp.verifyFolderPathdummy("", M6Institution1 + "Update", null,
													M6FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace,
													60)) {
												appLog.info("Updated institution name is displaying");
											} else {
												appLog.info("Updated institution name is not displaying");
												saa.assertTrue(false, "Updated institution name is not displaying");
											}
										} else {
											appLog.info("Not able to click on craeted contact");
											saa.assertTrue(false, "Not able to click on created contact");
										}
									} else {
										appLog.info("Not able to click on Contact Tab");
										saa.assertTrue(false, "Not able to click on Contact tab");
									}

								} else {
									appLog.info("Not able to click on done butotn");
									saa.assertTrue(false, "Not able to click oin Done buton");
								}
							} else {
								appLog.info("Not able to click on Apply Button");
								saa.assertTrue(false, "Not able to click on Apply Button");
							}
						} else {
							appLog.info("Not able to enter value in text box");
							saa.assertTrue(false, "Not able to enter value in text box");
						}
					} else {
						appLog.info("Not able to click on rename icon ");
						saa.assertTrue(false, "Not able to click on rename icon ");
					}
				} else {
					appLog.info("Not able to click on manage Invetsor Icon");
					saa.assertTrue(false, "Not able to click on manage investor icon");

				}
			} else {
				appLog.info("Not able to click on created fund");
				saa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to lcick on funds tab");
			saa.assertTrue(false, "Not able to lcick on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M6Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			if (fp.clickOnInstituionFolder(M6Institution1 + "Update", null, 10)) {
				appLog.info("Folder Structure is verified and Updated institution folder is displaying");
			} else {
				appLog.info("Folder Structure is not verified and Updated institution folder is not displaying");
				saa.assertTrue(false,
						"Folder Structure is not verified and Updated institution folder is not displaying");
			}
		} else {
			appLog.info("Not able to click on potential invetsment tab");
			saa.assertTrue(false, "Not able to click on potential invetsment tab");
		}
		lp.investorLogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M6tc007_EditInvestorNameFromInstitutionsPageWhoseFolderStructureNotCreated() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		List<String> institutionList = new ArrayList<String>();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M6Institution3)) {
				if (click(driver, bp.getEditButton(60), "Edit button", action.SCROLLANDBOOLEAN)) {
					if (sendKeys(driver, ip.getLegalNameTextBox(60), M6Institution3 + "Renamed", "Legal name text box",
							action.SCROLLANDBOOLEAN)) {
						if (click(driver, bp.getSaveButton(60), "Save Button", action.SCROLLANDBOOLEAN)) {
							String updtaedInstitutionName = ip.getLegalNameLabelTextbox(60).getText().trim();
							ThreadSleep(2000);
							if (updtaedInstitutionName.contains(M6Institution3 + "Renamed")) {
								appLog.info("Institution name has been updated successfully");
								ThreadSleep(2000);
							} else {
								appLog.info("Institution name has not been updated successfully");
								saa.assertTrue(false, "Institution name has not been updated successfully");
							}
						} else {
							appLog.info("Not able to click on save button");
							saa.assertTrue(false, "Not able to click on save button");
						}
					} else {
						appLog.info("Not able to enter value in legal name text box");
						saa.assertTrue(false, "Not able to enter value in legal name text box");
					}
				} else {
					appLog.info("Not able to click on edit icon");
					saa.assertTrue(false, "Not able to click on edit icon");
				}
			} else {
				appLog.info("Not able to click on craeted institution");
				saa.assertTrue(false, "Not able to click on created institution");
			}
		} else {
			appLog.info("Not able to click on institution tab");
			saa.assertTrue(false, "Not able to click on institution tab");
		}
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M6FundName1)) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
						"FundraisingWorkspace View.");
				if (click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60), "Manage Investor Icon",
						action.SCROLLANDBOOLEAN)) {
					List<WebElement> institutionName = fp.getManageInvestorPopupInstitutionList();
					for (int i = 0; i < institutionName.size(); i++) {
						institutionList.add(institutionName.get(i).getText().trim());
					}
					if (institutionList.contains(M6Institution3 + "Renamed")) {
						appLog.info("Updated institution 3 name is displaying");
					} else {
						appLog.info("Updated institution 3 name is displaying");
						saa.assertTrue(false, "Updated institution 3 name is displaying");
					}
				} else {
					appLog.info("Not able to click on manage investor icon");
					saa.assertTrue(false, "Not able to click on manage investor icon");
				}
			} else {
				appLog.info("Not able to click on created fund");
				saa.assertTrue(false, "Not able to click on craeted fund");
			}
		} else {
			appLog.info("Not able to click on funds tab");
			saa.assertTrue(false, "not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M6tc008_UncheckInvestorFromManageInvestorPopupAndCheckFolderStructureAndContactAccess() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		WebElement ele = null;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M6FundName1)) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
						"FundraisingWorkspace View.");
				if (click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60), "Manage Investor Icon",
						action.SCROLLANDBOOLEAN)) {
					switchToDefaultContent(driver);
					switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
							"FundraisingWorkspace View.");
					ele = FindElement(driver, "//div[@title='" + M6Institution1 + "Update" + "']/../..//input",
							"Institution 1 checkbox", action.SCROLLANDBOOLEAN, 60);
					if (isSelected(driver, ele, "Institution 1 checkbox")) {
						appLog.info("Institution 1 checkbox is selected");
					} else {
						appLog.info("Institution 1 checkbox is not selected");
						saa.assertTrue(false, "Institution 1 checkbox is not selected");
					}
					switchToDefaultContent(driver);
					switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
					if (click(driver, ele, "Institution1 checkbox", action.BOOLEAN)) {
						switchToDefaultContent(driver);
						switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
						scrollDownThroughWebelement(driver,
								fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
								"FundraisingWorkspace View.");
						if (click(driver,
								fp.getManageInvestorDeletedPopupCloseButton(Workspace.FundraisingWorkspace, 60),
								"Manage Investor Deleted popup Close Button", action.BOOLEAN)) {
							switchToDefaultContent(driver);
							switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
							scrollDownThroughWebelement(driver,
									fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
									"FundraisingWorkspace View.");
							if (click(driver, fp.getManageInvestorDoneButton(Workspace.FundraisingWorkspace, 60),
									"Fundraising workspace", action.SCROLLANDBOOLEAN)) {
								switchToDefaultContent(driver);
								switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
								scrollDownThroughWebelement(driver,
										fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
										"FundraisingWorkspace View.");
								if (fp.verifyFolderPathdummy("", M6Institution1 + "Update", null, M6FundName1,
										PageName.FundsPage, Workspace.FundraisingWorkspace, 20)) {
									appLog.info("Institution 1 folder is displaying in folder Structure");
									saa.assertTrue(false, "Institution 1 folder is displaying in folder Structure");
								} else {
									appLog.info("Institution 1 folder is not displaying in folder Structure");
								}
							} else {
								appLog.info("Not able to click on done button");
								saa.assertTrue(false, "Not able to click on done button");
							}

						} else {
							appLog.info("Not able to click on Close button");
							saa.assertTrue(false, "Not able to click on close button");
						}
					} else {
						appLog.info("Not able to click on checkbox");
						saa.assertTrue(false, "Nt able ot click on institution 1 checkbox");
					}
				} else {
					appLog.info("Not able to click on manage investor icon");
					saa.assertTrue(false, "Not able to click on manage investor icon");
				}
			} else {
				appLog.info("Not able to click on Created fund");
				saa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on fundstab");
			saa.assertTrue(false, "Not able to click on funds tab ");
		}
		switchToDefaultContent(driver);
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M6Institution1 + "Renamed")) {
				switchToFrame(driver, 30, bp.getFrame(PageName.InstitutionsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
						"FundraisingWorkspace View.");

				if (ip.getErrorMessageAfterAdminAndCRMUserRegistrationFundRaisingWorkspace(60) != null) {
					appLog.info("Fundraising workspace has been removed successfully");
				} else {
					appLog.info("Fundraising workspace has not been removed successfully");
					saa.assertTrue(false, "Fundraising workspace has not been removed successfully");
				}
			} else {
				appLog.info("Not able to click on created institution");
				saa.assertTrue(false, "Not able to click on created institution");
			}
		} else {
			appLog.info("Not able to click on institution tab");
			saa.assertTrue(false, "Not able to click on institution tab");
		}
		switchToDefaultContent(driver);
		if (bp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M6Contact1FirstName, M6Contact1LastName, null)) {
				switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
						"FundraisingWorkspace View.");
				if (ip.getErrorMessageAfterAdminAndCRMUserRegistrationFundRaisingWorkspace(60) != null) {
					appLog.info("Fundraising workspace has been removed successfully");
				} else {
					appLog.info("Fundraising workspace has not been removed successfully");
					saa.assertTrue(false, "Fundraising workspace has not been removed successfully");
				}
			} else {
				appLog.info("Not able to click on created contact");
				saa.assertTrue(false, "Not able to click on created contact");
			}
		} else {
			appLog.info("Not able to click on Contact tab");
			saa.assertTrue(false, "Not able to click on contact tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		AllFirmsPageBusinesslayer afp = new AllFirmsPageBusinesslayer(driver);
		lp.investorLogin(M6Contact1EmailId, adminPassword);
		String firmName = getSelectedOptionOfDropDown(driver, afp.getFirmNameDropdown(60), "Firm name dropdown",
				"text");
		if (firmName.equalsIgnoreCase("All Firms")) {
			appLog.info("all Firm Page Is opened");
		} else {
			appLog.info("Firm Page is not opened");
			saa.assertTrue(false, "Firm Page is not opened");
		}
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			appLog.info("Potential investment tab is displaying");
			saa.assertTrue(false, "Potential investment tab is displaying");
		} else {
			appLog.info("Potential investment tab is not displaying");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		cp = new ContactPageBusinessLayer(driver);
		ip = new InstitutionPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M6FundName1)) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
						"FundraisingWorkspace View.");
				if (click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60), "Manage Investor Icon",
						action.SCROLLANDBOOLEAN)) {
					ele = FindElement(driver, "//div[@title='" + M6Institution1 + "Renamed" + "']/../..//input",
							"Institution 1 checkbox", action.SCROLLANDBOOLEAN, 60);
					switchToDefaultContent(driver);
					switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
							"FundraisingWorkspace View.");
					if (click(driver, ele, "Institution1 checkbox", action.BOOLEAN)) {
						switchToDefaultContent(driver);
						switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
						if (click(driver, fp.getManageInvestorAddedPopupCloseButton(Workspace.FundraisingWorkspace, 60),
								"Manage Investor Added popup Close Button", action.BOOLEAN)) {
							switchToDefaultContent(driver);
							switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
							scrollDownThroughWebelement(driver,
									fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
									"FundraisingWorkspace View.");
							if (click(driver, fp.getManageInvestorDoneButton(Workspace.FundraisingWorkspace, 60),
									"Fundraising workspace", action.SCROLLANDBOOLEAN)) {
								switchToDefaultContent(driver);
								switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
								if (fp.verifyFolderPathdummy("", M6Institution1 + "Renamed", null, M6FundName1,
										PageName.FundsPage, Workspace.FundraisingWorkspace, 20)) {
									appLog.info("Institution 1 folder is displaying in folder Structure");
								} else {
									appLog.info("Institution 1 folder is not displaying in folder Structure");
									saa.assertTrue(false, "Institution 1 folder is not displaying in folder Structure");
								}
							} else {
								appLog.info("Not able to click on done button");
								saa.assertTrue(false, "Not able to click on done button");
							}

						} else {
							appLog.info("Not able to click on Close button");
							saa.assertTrue(false, "Not able to click on close button");
						}
					} else {
						appLog.info("Not able to click on checkbox");
						saa.assertTrue(false, "Nt able ot click on institution 1 checkbox");
					}
				} else {
					appLog.info("Not able to click on manage investor icon");
					saa.assertTrue(false, "Not able to click on manage investor icon");
				}
			} else {
				appLog.info("Not able to click on Created fund");
				saa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on fundstab");
			saa.assertTrue(false, "Not able to click on funds tab ");
		}
		switchToDefaultContent(driver);
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M6Institution1 + "Renamed")) {
				switchToFrame(driver, 30, bp.getFrame(PageName.InstitutionsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
						"FundraisingWorkspace View.");
				if (fp.verifyFolderPathdummy("", null, null, M6FundName1, PageName.InstitutionsPage,
						Workspace.FundraisingWorkspace, 60)) {
					appLog.info("Fundraising workspace is displaying successfully");
				} else {
					appLog.info("Fundraising workspace is not displaying successfully");
					saa.assertTrue(false, "Fundraising workspace is not displaying successfully");
				}
			} else {
				appLog.info("Not able to click on created institution");
				saa.assertTrue(false, "Not able to click on created institution");
			}
		} else {
			appLog.info("Not able to click on institution tab");
			saa.assertTrue(false, "Not able to click on institution tab");
		}
		switchToDefaultContent(driver);
		if (bp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M6Contact1FirstName, M6Contact1LastName, null)) {
				switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
						"FundraisingWorkspace View.");
				if (ip.getErrorMessageAfterAdminAndCRMUserRegistrationFundRaisingWorkspace(60) != null) {
					appLog.info("Fundraising workspace has been removed successfully");
				} else {
					appLog.info("Fundraising workspace has not been removed successfully");
					saa.assertTrue(false, "Fundraising workspace has not been removed successfully");
				}
			} else {
				appLog.info("Not able to click on created contact");
				saa.assertTrue(false, "Not able to click on created contact");
			}
		} else {
			appLog.info("Not able to click on Contact tab");
			saa.assertTrue(false, "Not able to click on contact tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		ifp = new InvestorFirmPageBusinesslayer(driver);
		afp = new AllFirmsPageBusinesslayer(driver);
		lp.investorLogin(M6Contact1EmailId, adminPassword);
		firmName = getSelectedOptionOfDropDown(driver, afp.getFirmNameDropdown(60), "Firm name dropdown", "text");
		if (firmName.equalsIgnoreCase("All Firms")) {
			appLog.info("all Firm Page Is opened");
		} else {
			appLog.info("Firm Page is not opened");
			saa.assertTrue(false, "Firm Page is not opened");
		}
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			appLog.info("Potential investment tab is displaying");
			saa.assertTrue(false, "Potential investment tab is displaying");
		} else {
			appLog.info("Potential investment tab is not displaying");
		}
		lp.investorLogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M6tc009_AddContactAccessAgainToNewlyAddedInstitution() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M6FundName1)) {
				if (fp.inviteContact(M6Institution1 + "Renamed", M6Contact1EmailId, null, FolderType.Standard, "Upload",
						"Yes", "No", null, Workspace.FundraisingWorkspace, null)) {
					appLog.info("Contact get invited successfully");
				} else {
					appLog.info("Not able to invite contact");
					saa.assertTrue(false, "Not able to invite contact");
				}
				if (bp.clickOnTab(TabName.InstituitonsTab)) {
					if (ip.clickOnCreatedInstitution(M6Institution1 + "Renamed")) {
						switchToFrame(driver, 30, bp.getFrame(PageName.InstitutionsPage, 60));
						scrollDownThroughWebelement(driver,
								fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
								"FundraisingWorkspace View.");
						if (fp.verifyFolderPathdummy("", null, null, M6FundName1, PageName.InstitutionsPage,
								Workspace.FundraisingWorkspace, 60)) {
							appLog.info("Fundraising workspace is displaying successfully");
						} else {
							appLog.info("Fundraising workspace is not displaying successfully");
							saa.assertTrue(false, "Fundraising workspace is not displaying successfully");
						}
					} else {
						appLog.info("Not able to click on craeted institution");
						saa.assertTrue(false, "Not able to click on craeted institution");
					}
				} else {
					appLog.info("Not able to click on institution tab");
					saa.assertTrue(false, "Not able to click on institution tab");
				}
				switchToDefaultContent(driver);
				if (bp.clickOnTab(TabName.ContactTab)) {
					if (cp.clickOnCreatedContact(M6Contact1FirstName, M6Contact1LastName, null)) {
						switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
						scrollDownThroughWebelement(driver,
								fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
								"FundraisingWorkspace View.");
						if (fp.verifyFolderPathdummy("", M6Institution1 + "Renamed", null, M6FundName1,
								PageName.ContactsPage, Workspace.FundraisingWorkspace, 60)) {
							appLog.info("Fundraising workspace is displaying successfully with institution name");
						} else {
							appLog.info("Fundraising workspace is not displaying successfully with institution name");
							saa.assertTrue(false,
									"Fundraising workspace is not displaying successfully with institution name");
						}
					} else {
						appLog.info("Not able to click on created contact");
						saa.assertTrue(false, "Not able to click on created contact");
					}
				} else {
					appLog.info("Not able to click on Contact tab");
					saa.assertTrue(false, "Not able to click on contact tab");
				}
			} else {
				appLog.info("Not able to click on created fund");
				saa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on funds tab");
			saa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		AllFirmsPageBusinesslayer afp = new AllFirmsPageBusinesslayer(driver);
		lp.investorLogin(M6Contact1EmailId, adminPassword);
		String firmName = getSelectedOptionOfDropDown(driver, afp.getFirmNameDropdown(60), "Firm name dropdown",
				"text");
		if (firmName.equalsIgnoreCase(Org1FirmName)) {
			appLog.info("Firm Page Is opened");
		} else {
			appLog.info("Firm Page is not opened");
			saa.assertTrue(false, "Firm Page is not opened");
		}
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			appLog.info("Potential investment tab is displaying");
			if (fp.clickOnInstituionFolder(M6Institution1 + "Renamed", null, 10)) {
				appLog.info("Folder Structure is verified and institution folder is displaying");
			} else {
				appLog.info("Folder Structure is not verified and institution folder is not displaying");
				saa.assertTrue(false, "Folder Structure is not verified and institution folder is not displaying");
			}
		} else {
			appLog.info("Potential investment tab is not displaying");
			saa.assertTrue(false, "Potential investment tab is displaying");
		}
		lp.investorLogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M6tc010_DeleteInstitutionFromInstitutionsPageWhoseFolderStructureHasCreatedInWorkspace() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		List<String> institutionList = new ArrayList<String>();
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M6Institution1 + "Renamed")) {
				if (click(driver, bp.getDeleteButton(60), "Delete Button", action.SCROLLANDBOOLEAN)) {
					switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
					if (ip.verifyDeletedInstitution(M6Institution1 + "Renamed")) {
						appLog.info("Institution get deleted successfully");
					} else {
						appLog.info("Institution does not get deleted successfully");
						saa.assertTrue(false, "Institution does not get deleted successfully");
					}
					if (bp.clickOnTab(TabName.FundsTab)) {
						if (fp.clickOnCreatedFund(M6FundName1)) {
							switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
							scrollDownThroughWebelement(driver,
									fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
									"FundraisingWorkspace View.");
							if (click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60),
									"Manage Investor Icon", action.SCROLLANDBOOLEAN)) {
								switchToDefaultContent(driver);
								switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
								scrollDownThroughWebelement(driver,
										fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
										"FundraisingWorkspace View.");
								List<WebElement> institutionName = fp.getManageInvestorPopupInstitutionList();
								for (int i = 0; i < institutionName.size(); i++) {
									institutionList.add(institutionName.get(i).getText().trim());
								}
								if (institutionList.contains(M6Institution1 + "Renamed")) {
									appLog.info("Deleted institution 1 is displaying");
									saa.assertTrue(false, "Deleted institution 1 is displaying");
								} else {
									appLog.info("Deleted institution 1 is not displaying");
								}
							} else {
								appLog.info("Not able to click on manage invetsor icon");
								saa.assertTrue(false, "Not able to click on manage investor icon");
							}
						} else {
							appLog.info("Not able to click on created funds");
							saa.assertTrue(false, "Not able to click on created funds");
						}
					} else {
						appLog.info("Not able to click on funds tab");
						saa.assertTrue(false, "Not able to click on funds tab");
					}
				} else {
					appLog.info("Not able to click on delete button");
					saa.assertTrue(false, "Not able to click on delete button");
				}
			} else {
				appLog.info("Not able to click on created institution");
				saa.assertTrue(false, "Not able to click on created institution");
			}
		} else {
			appLog.info("Not able to click on Institution tab");
			saa.assertTrue(false, "Not able to click on Institution tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M6tc011_DeleteInstitutionFromInstitutionsPageWhoseFolderStructureHasNotCreated() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		List<String> institutionList = new ArrayList<String>();
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M6Institution3 + "Renamed")) {
				if (click(driver, bp.getDeleteButton(60), "Delete Button", action.SCROLLANDBOOLEAN)) {
					switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
					if (ip.verifyDeletedInstitution(M6Institution3 + "Renamed")) {
						appLog.info("Institution get deleted successfully");
					} else {
						appLog.info("Institution does not get deleted successfully");
						saa.assertTrue(false, "Institution does not get deleted successfully");
					}
					if (bp.clickOnTab(TabName.FundsTab)) {
						if (fp.clickOnCreatedFund(M6FundName1)) {
							switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
							scrollDownThroughWebelement(driver,
									fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
									"FundraisingWorkspace View.");
							if (click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60),
									"Manage Investor Icon", action.SCROLLANDBOOLEAN)) {
								switchToDefaultContent(driver);
								switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
								scrollDownThroughWebelement(driver,
										fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
										"FundraisingWorkspace View.");
								List<WebElement> institutionName = fp.getManageInvestorPopupInstitutionList();
								for (int i = 0; i < institutionName.size(); i++) {
									institutionList.add(institutionName.get(i).getText().trim());
								}
								if (institutionList.contains(M6Institution3 + "Renamed")) {
									appLog.info("Deleted institution 3 is displaying");
									saa.assertTrue(false, "Deleted institution 3 is displaying");
								} else {
									appLog.info("Deleted institution 3 is not displaying");
								}
							} else {
								appLog.info("Not able to click on manage invetsor icon");
								saa.assertTrue(false, "Not able to click on manage investor icon");
							}
						} else {
							appLog.info("Not able to click on created funds");
							saa.assertTrue(false, "Not able to click on created funds");
						}
					} else {
						appLog.info("Not able to click on funds tab");
						saa.assertTrue(false, "Not able to click on funds tab");
					}
				} else {
					appLog.info("Not able to click on delete button");
					saa.assertTrue(false, "Not able to click on delete button");
				}
			} else {
				appLog.info("Not able to click on created institution");
				saa.assertTrue(false, "Not able to click on created institution");
			}
		} else {
			appLog.info("Not able to click on Institution tab");
			saa.assertTrue(false, "Not able to click on Institution tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M6tc012_AddStandardFolderFromManageFoldersAndThenAddInstitutionFromManageInvestors() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M6FundName1)) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
						"FundraisingWorkspace View.");
				if (click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 60), "Manage Approval Icon",
						action.SCROLLANDBOOLEAN)) {
					if (bp.createFolderStructure("st", FolderType.Standard, Workspace.FundraisingWorkspace,
							PageName.ManageFolderPopUp, 60).isEmpty()) {
						ThreadSleep(2000);
						if (click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 60), "Manage Folder Close Button",
								action.SCROLLANDBOOLEAN)) {
							ThreadSleep(2000);
							switchToDefaultContent(driver);
							switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
							scrollDownThroughWebelement(driver,
									fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
									"FundraisingWorkspace View.");
							if (fp.verifyFolderPathdummy("st", M6Institution2, null, M6FundName1, PageName.FundsPage,
									Workspace.FundraisingWorkspace, 20)) {
								appLog.info("Institution 2 folder is displaying with st folder in folder Structure");
							} else {
								appLog.info(
										"Institution 2 folder is not displaying with st folder in folder Structure");
								saa.assertTrue(false,
										"Institution 2 folder is not displaying with st folder in folder Structure");
							}
						} else {
							appLog.info("Not able to click on manage folder close button");
							saa.assertTrue(false, "Not able to click on manage folder close button");
						}
					} else {
						appLog.info("Not able to create standard folder");
						saa.assertTrue(false, "Not able to create standard folder");
					}

				} else {
					appLog.info("Not able to click on manage approval icon");
					saa.assertTrue(false, "Not able to click on amnage approval icon");
				}
			} else {
				appLog.info("Not able to click on created fund");
				saa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on funds tab");
			saa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M6tc013_BuildWorkspaceAndImportFolderTemplateAndCheckManageInvestorUI() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String[] step1of3 = { "", "",
				ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M6Fund1", excelLabel.Fund_Contact),
				ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M6Fund1", excelLabel.Fund_Phone),
				ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M6Fund1", excelLabel.Fund_Email), "" };
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M6FundName1)) {
				if (fp.buildWorkspace(step1of3, WorkSpaceAction.IMPORTFOLDERTEMPLATE, folderTemplateName, null, null,
						Workspace.InvestorWorkspace, 60)) {
					appLog.info("Workspace is created successfully");
					switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
					if (click(driver, fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60), "Manage Investor icon",
							action.SCROLLANDBOOLEAN)) {
						if (fp.getManageInvestorHeader(Workspace.InvestorWorkspace, 60) != null) {
							appLog.info("Manage Investor popup is displaying");
							if (fp.getManageInvestorFilterInvestorHeader(Workspace.InvestorWorkspace, 60) != null) {
								appLog.info("Filter Invetsor header tab is displaying");
								if (fp.getManageInvestorInvestorAccountsHeader(Workspace.InvestorWorkspace,
										60) != null) {
									if (fp.getManageInvestorShowDropdown(Workspace.InvestorWorkspace, 60) != null) {
										appLog.info("Show dropdown is  displaying");
										List<WebElement> showDropdownValues = allOptionsInDropDrop(driver,
												fp.getManageInvestorShowDropdown(Workspace.InvestorWorkspace, 60),
												"Show dropdown values");
										for (int i = 0; i < showDropdownValues.size(); i++) {
											if (showDropdownValues.get(i).getText()
													.equalsIgnoreCase("All Institutions")) {
												appLog.info(showDropdownValues.get(i).getText()
														+ " :Opiton is available in the list.");
											} else if (showDropdownValues.get(i).getText()
													.equalsIgnoreCase("Institutions with Folder Built")) {
												appLog.info(showDropdownValues.get(i).getText()
														+ " :Opiton is available in the list.");
											} else if (showDropdownValues.get(i).getText()
													.equalsIgnoreCase("Institutions without Folder Built")) {
												appLog.info(showDropdownValues.get(i).getText()
														+ " :Opiton is available in the list.");
											} else {
												appLog.info(showDropdownValues.get(i).getText()
														+ " :Opiton is not available in the list.");
												saa.assertTrue(false, showDropdownValues.get(i).getText()
														+ " :Opiton is not available in the list.");
											}
										}
										if (fp.getManageInvestorDoneButton(Workspace.InvestorWorkspace, 60) != null) {
											appLog.info("Done button is displaying");
										} else {
											appLog.info("Done Button is not displaying");
											saa.assertTrue(false, "Done Button is not displaying");
										}
										switchToDefaultContent(driver);
										switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
										if (click(driver,
												fp.getManageInvestorDoneButton(Workspace.InvestorWorkspace, 60),
												"Done Button", action.SCROLLANDBOOLEAN)) {
											appLog.info("Clicked on done button");
											ThreadSleep(2000);
											if (fp.getManageInvestorDoneButton(Workspace.InvestorWorkspace,
													10) == null) {
												appLog.info("Manage Invetsor popup get closed successfully");
											} else {
												appLog.info("Manage Investor popup is not closed successfully");
												saa.assertTrue(false,
														"Manage Investor popup is not closed successfully");
											}
										} else {
											appLog.info("Not able to click on done button");
											saa.assertTrue(false, "Not able to click on done button");
										}
									} else {
										appLog.info("Show dropdown is not displaying");
										saa.assertTrue(false, "Show dropdown is not displaying");
									}
								} else {
									appLog.info("Invetsor account header is not displaying");
									saa.assertTrue(false, "Invetsor account header is not displaying");
								}
							} else {
								appLog.info("Filter Invetsor header tab is not displaying");
								saa.assertTrue(false, "Filter Invetsor header tab is not displaying");
							}
						} else {
							appLog.info("Manage Investor popup is not displaying");
							saa.assertTrue(false, "Manage Investor popup is not displaying");
						}
					} else {
						appLog.info("not able to click on manage investor icon so we cannot check manage investor UI");
						saa.assertTrue(false,
								"not able to click on manage investor icon so we cannot check manage investor UI");
					}
				} else {
					appLog.info("workspace is not created successfully");
					saa.assertTrue(false, "workspace is not craeted successfully");
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
	public void M6tc014_CheckManageInvestorWithCommitmentAndPartnershipDone() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		HomePageBusineesLayer hp = new HomePageBusineesLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		PartnershipPageBusinessLayer pp = new PartnershipPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		SoftAssert saa = new SoftAssert();
		WebElement ele = null;
		String[] restoreValue = { M6Institution1 + "Renamed", M6Institution3 + "Renamed" };
		for (String value : restoreValue) {
			if (hp.restoreValuesFromRecycleBin(value)) {
				appLog.info("Successfully restored value:" + value);
			} else {
				appLog.info("Not able to recover value:" + value);
				saa.assertTrue(false, "Not able to recover value:" + value);
			}
		}
		for (int i = 0; i < 4; i++) {
			String partnershipName = ExcelUtils.readData("Partnerships", excelLabel.Variable_Name,
					"M6Partnership" + (i + 1), excelLabel.PartnerShip_Name);
			if (bp.clickOnTab(TabName.PartnershipsTab)) {
				String fundName = ExcelUtils.readData("Funds", excelLabel.Variable_Name, "M6Fund1",
						excelLabel.Fund_Name);
				if (pp.createPartnership(partnershipName, fundName)) {
					appLog.info("partnership is created: " + partnershipName);
				} else {
					appLog.error("Not able to create partnership: " + partnershipName);
					sa.assertTrue(false, "Not able to create partnership: " + partnershipName);
				}
			} else {
				appLog.error("Not able to click on partnership tab so cannot create partnership: " + partnershipName);
				sa.assertTrue(false,
						"Not able to click on partnership tab so cannot create partnership: " + partnershipName);
			}
		}
		for (int i = 0; i < 4; i++) {
			if (bp.clickOnTab(TabName.CommitmentsTab)) {
				String LimitedPartnerName = ExcelUtils.readData("Limited Partner", excelLabel.Variable_Name,
						"M6LimitedPartner" + (i + 1), excelLabel.LimitedPartner_Name);
				String Partnershipname = ExcelUtils.readData("Partnerships", excelLabel.Variable_Name,
						"M6Partnership" + (i + 1), excelLabel.PartnerShip_Name);
				if (cmp.createCommitment(LimitedPartnerName, Partnershipname, "M6Commitment" + (i + 1), null)) {
					appLog.info("commitment is created successfully");
				} else {
					appLog.error("Not able to create commitment for limited partner");
					sa.assertTrue(false, "Not able to create commitment for limited partner");
				}
			} else {
				appLog.error("Not able to click on commitment tab so cannot create committment for limite partner");
				sa.assertTrue(false,
						"Not able to click on commitment tab so cannot create committment for limite partner");
			}
		}
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M6Institution1 + "Renamed")) {
				if (click(driver, bp.getEditButton(60), "Edit Button", action.SCROLLANDBOOLEAN)) {
					if (sendKeys(driver, ip.getLegalNameTextBox(60), M6Institution1, "Legal name text box",
							action.SCROLLANDBOOLEAN)) {
						if (click(driver, bp.getSaveButton(60), "Save Button", action.SCROLLANDBOOLEAN)) {
							String updtaedInstitutionName = ip.getLegalNameLabelTextbox(60).getText().trim();
							ThreadSleep(2000);
							if (updtaedInstitutionName.contains(M6Institution1)) {
								appLog.info("Institution name has been updated successfully");
								ThreadSleep(2000);
							} else {
								appLog.info("Institution name has not been updated successfully");
								saa.assertTrue(false, "Institution name has not been updated successfully");
							}
						} else {
							appLog.info("Not able to click on save button");
							saa.assertTrue(false, "Not able ot click on save button");
						}
					} else {
						appLog.info("Not able to enter values in legal name text box");
						saa.assertTrue(false, "Not able to enter values in legal name text box");
					}
				} else {
					appLog.info("Not able to click on edit icon");
					saa.assertTrue(false, "Not able to click on edit icon");
				}
			} else {
				appLog.info("Not able to click on craeted institution");
				saa.assertTrue(false, "Not able to click on craeted institution");
			}
		} else {
			appLog.info("Not able to click on Instituitions tab");
			saa.assertTrue(false, "Not able to click on Instituitions tab");
		}
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedInstitution(M6Institution3 + "Renamed")) {
				if (click(driver, bp.getEditButton(60), "Edit Button", action.SCROLLANDBOOLEAN)) {
					if (sendKeys(driver, ip.getLegalNameTextBox(60), M6Institution3, "Legal name text box",
							action.SCROLLANDBOOLEAN)) {
						if (click(driver, bp.getSaveButton(60), "Save Button", action.SCROLLANDBOOLEAN)) {
							String updtaedInstitutionName = ip.getLegalNameLabelTextbox(60).getText().trim();
							ThreadSleep(2000);
							if (updtaedInstitutionName.contains(M6Institution3)) {
								appLog.info("Institution name has been updated successfully");
								ThreadSleep(2000);
							} else {
								appLog.info("Institution name has not been updated successfully");
								saa.assertTrue(false, "Institution name has not been updated successfully");
							}
						} else {
							appLog.info("Not able to click on save button");
							saa.assertTrue(false, "Not able ot click on save button");
						}
					} else {
						appLog.info("Not able to enter values in legal name text box");
						saa.assertTrue(false, "Not able to enter values in legal name text box");
					}
				} else {
					appLog.info("Not able to click on edit icon");
					saa.assertTrue(false, "Not able to click on edit icon");
				}
			} else {
				appLog.info("Not able to click on craeted institution");
				saa.assertTrue(false, "Not able to click on craeted institution");
			}
		} else {
			appLog.info("Not able to click on Instituitions tab");
			saa.assertTrue(false, "Not able to click on Instituitions tab");
		}
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M6FundName1)) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				System.err.println("Switched to frame.");
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
						"Investor workspace view");
				if (click(driver, fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60), "Manage Investor icon",
						action.SCROLLANDBOOLEAN)) {
					if (fp.verifyLPStructureInManageInvestor(M6Institution1, M6LimitedPartner1)) {
						appLog.info("Limited Partner 1 is displaying under institution1");
					} else {
						appLog.info("Limited Partner 1 is not displaying under institution1");
						saa.assertTrue(false, "Limited Partner 1 is not displaying under institution1");
					}
					if (isSelected(driver, fp.getLimitedPartnerCheckBox(M6Institution1, M6LimitedPartner1,
							Workspace.InvestorWorkspace, 60), "LP1 checkbox")) {
						appLog.info("LP 1 checkbox is selected");
						saa.assertTrue(false, "LP1 checkox is selected");
					} else {
						appLog.info("LP 1 checkbox is not selected");
					}
					if (fp.verifyLPStructureInManageInvestor(M6Institution2, M6LimitedPartner2)) {
						appLog.info("Limited Partner 2 is displaying under institution2");
					} else {
						appLog.info("Limited Partner 2 is not displaying under institution2");
						saa.assertTrue(false, "Limited Partner 2 is not displaying under institution2");
					}
					if (isSelected(driver, fp.getLimitedPartnerCheckBox(M6Institution2, M6LimitedPartner2,
							Workspace.InvestorWorkspace, 60), "LP2 checkbox")) {
						appLog.info("LP 2 checkbox is selected");
						saa.assertTrue(false, "LP2 checkox is selected");
					} else {
						appLog.info("LP 2 checkbox is not selected");
					}
					if (fp.verifyLPStructureInManageInvestor(M6Institution3, M6LimitedPartner3)) {
						appLog.info("Limited Partner 3 is displaying under institution3");
					} else {
						appLog.info("Limited Partner 3 is not displaying under institution3");
						saa.assertTrue(false, "Limited Partner 3 is not displaying under institution3");
					}
					if (isSelected(driver, fp.getLimitedPartnerCheckBox(M6Institution3, M6LimitedPartner3,
							Workspace.InvestorWorkspace, 60), "LP3 checkbox")) {
						appLog.info("LP 3 checkbox is selected");
						saa.assertTrue(false, "LP3 checkox is selected");
					} else {
						appLog.info("LP 3 checkbox is not selected");
					}
					if (fp.verifyLPStructureInManageInvestor(M6Institution3, M6LimitedPartner4)) {
						appLog.info("Limited Partner 4 is displaying under institution3");
					} else {
						appLog.info("Limited Partner 4 is not displaying under institution3");
						saa.assertTrue(false, "Limited Partner 4 is not displaying under institution3");
					}
					if (isSelected(driver, fp.getLimitedPartnerCheckBox(M6Institution3, M6LimitedPartner4,
							Workspace.InvestorWorkspace, 60), "LP4 checkbox")) {
						appLog.info("LP 4 checkbox is selected");
						saa.assertTrue(false, "LP4 checkox is selected");
					} else {
						appLog.info("LP 4 checkbox is not selected");
					}
					if (selectVisibleTextFromDropDown(driver,
							fp.getManageInvestorShowDropdown(Workspace.InvestorWorkspace, 60), "Show Dropdown",
							"Institutions with Folder Built")) {
						appLog.info("Able to select value form show dropdown");
						ThreadSleep(2000);
						ele = FindElement(driver, "//div[@id='navtreefund11MIMININV']//a//span", "All Investor",
								action.SCROLLANDBOOLEAN, 60);
						if (ele != null) {
							appLog.info("All Invetsor label is displaying");
						} else {
							appLog.info("All Investor label is not displaying");
							saa.assertTrue(false, "ALl Investor label is not displaying");
						}
					} else {
						appLog.info("Not Able to select value form show dropdown");
						saa.assertTrue(false, "Not Able to select value form show dropdown");
					}
					if (selectVisibleTextFromDropDown(driver,
							fp.getManageInvestorShowDropdown(Workspace.InvestorWorkspace, 60), "Show Dropdown",
							"Institutions without Folder Built")) {
						appLog.info("Able to select value form show dropdown");
						if (fp.verifyLPStructureInManageInvestor(M6Institution1, M6LimitedPartner1)) {
							appLog.info("Limited Partner 1 is displaying under institution1");
						} else {
							appLog.info("Limited Partner 1 is not displaying under institution1");
							saa.assertTrue(false, "Limited Partner 1 is not displaying under institution1");
						}
						if (isSelected(driver, fp.getLimitedPartnerCheckBox(M6Institution1, M6LimitedPartner1,
								Workspace.InvestorWorkspace, 60), "LP1 checkbox")) {
							appLog.info("LP 1 checkbox is selected");
							saa.assertTrue(false, "LP1 checkox is selected");
						} else {
							appLog.info("LP 1 checkbox is not selected");
						}
						if (fp.verifyLPStructureInManageInvestor(M6Institution2, M6LimitedPartner2)) {
							appLog.info("Limited Partner 2 is displaying under institution2");
						} else {
							appLog.info("Limited Partner 2 is not displaying under institution2");
							saa.assertTrue(false, "Limited Partner 2 is not displaying under institution2");
						}
						if (isSelected(driver, fp.getLimitedPartnerCheckBox(M6Institution2, M6LimitedPartner2,
								Workspace.InvestorWorkspace, 60), "LP2 checkbox")) {
							appLog.info("LP 2 checkbox is selected");
							saa.assertTrue(false, "LP2 checkox is selected");
						} else {
							appLog.info("LP 2 checkbox is not selected");
						}
						if (fp.verifyLPStructureInManageInvestor(M6Institution3, M6LimitedPartner3)) {
							appLog.info("Limited Partner 3 is displaying under institution1");
						} else {
							appLog.info("Limited Partner 3 is not displaying under institution3");
							saa.assertTrue(false, "Limited Partner 3 is not displaying under institution3");
						}
						if (isSelected(driver, fp.getLimitedPartnerCheckBox(M6Institution3, M6LimitedPartner3,
								Workspace.InvestorWorkspace, 60), "LP3 checkbox")) {
							appLog.info("LP 3 checkbox is selected");
							saa.assertTrue(false, "LP3 checkox is selected");
						} else {
							appLog.info("LP 3 checkbox is not selected");
						}
						if (fp.verifyLPStructureInManageInvestor(M6Institution3, M6LimitedPartner4)) {
							appLog.info("Limited Partner 4 is displaying under institution3");
						} else {
							appLog.info("Limited Partner 4 is not displaying under institution3");
							saa.assertTrue(false, "Limited Partner 4 is not displaying under institution3");
						}
						if (isSelected(driver, fp.getLimitedPartnerCheckBox(M6Institution3, M6LimitedPartner4,
								Workspace.InvestorWorkspace, 60), "LP4 checkbox")) {
							appLog.info("LP 4 checkbox is selected");
							saa.assertTrue(false, "LP4 checkox is selected");
						} else {
							appLog.info("LP 4 checkbox is not selected");
						}
						if (fp.verifyLPStructureInManageInvestor(M6Institution1, M6LimitedPartner1)) {
							if (click(driver,
									fp.getLimitedPartnerCheckBox(M6Institution1, M6LimitedPartner1,
											Workspace.InvestorWorkspace, 60),
									"LP 1 Checkbox", action.SCROLLANDBOOLEAN)) {
								switchToDefaultContent(driver);
								switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
								scrollDownThroughWebelement(driver,
										fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
										"Investor workspace view");
								if (fp.getManageInvestorAddedPopupHeader(Workspace.InvestorWorkspace, 60) != null) {
									appLog.info("Confirmation popup is displaying");
									if (bp.verifyErrorMessageOnPage(
											FundsPageErrorMessage.manageInvestorAddedPopupMessage,
											fp.getManageInvestorAddedPopupMessage(Workspace.InvestorWorkspace, 60),
											"Investor Added Message ")) {
										appLog.info("Error Message is verified  Manage Investor Added Popup");
									} else {
										saa.assertTrue(false,
												"Error Message is not verified  Manage Investor Added Popup .Expected:"
														+ FundsPageErrorMessage.manageInvestorAddedPopupMessage
														+ " Actual"
														+ getText(driver,
																fp.getManageInvestorAddedPopupMessage(
																		Workspace.InvestorWorkspace, 60),
																"Investor Added Message ", action.BOOLEAN));
									}
									switchToDefaultContent(driver);
									switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
									scrollDownThroughWebelement(driver,
											fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
											"Investor workspace view");
									if (click(driver,
											fp.getManageInvestorAddedPopupCloseButton(Workspace.InvestorWorkspace, 60),
											"Close Button", action.SCROLLANDBOOLEAN)) {
										appLog.info("Clicked on close button");
										if (fp.getManageInvestorAddedPopupCloseButton(Workspace.InvestorWorkspace,
												10) != null) {
											appLog.info("Popup is not closed");
											saa.assertTrue(false, "Popup is not closed");
										} else {
											appLog.info("Popup is closed");
										}
									} else {
										appLog.info("Not able to click on close button");
										saa.assertTrue(false, "Not able to click on close button");
									}
								} else {
									appLog.info("Mange Invetsor added popup is not displaying");
									saa.assertTrue(false, "Mange Invetsor added popup is not displaying");
								}
							} else {
								appLog.info("Not able to click on LP 1 checkbox");
								saa.assertTrue(false, "Not able to click on LP 1 checkbox ");
							}
						} else {
							appLog.info("Not able to click on Institution 1");
							saa.assertTrue(false, "Not able to click on Institution 1");
						}
						switchToDefaultContent(driver);
						switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
						scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
								"Investor workspace view");
						if (fp.verifyLPStructureInManageInvestor(M6Institution2, M6LimitedPartner2)) {
							if (click(driver,
									fp.getLimitedPartnerCheckBox(M6Institution2, M6LimitedPartner2,
											Workspace.InvestorWorkspace, 60),
									"LP 2 Checkbox", action.SCROLLANDBOOLEAN)) {
								switchToDefaultContent(driver);
								switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
								if (fp.getManageInvestorAddedPopupHeader(Workspace.InvestorWorkspace, 60) != null) {
									appLog.info("Confirmation popup is displaying");
									if (bp.verifyErrorMessageOnPage(
											FundsPageErrorMessage.manageInvestorAddedPopupMessage,
											fp.getManageInvestorAddedPopupMessage(Workspace.InvestorWorkspace, 60),
											"Investor Added Message ")) {
										appLog.info("Error Message is verified  Manage Investor Added Popup");
									} else {
										saa.assertTrue(false,
												"Error Message is not verified  Manage Investor Added Popup .Expected:"
														+ FundsPageErrorMessage.manageInvestorAddedPopupMessage
														+ " Actual"
														+ getText(driver,
																fp.getManageInvestorAddedPopupMessage(
																		Workspace.InvestorWorkspace, 60),
																"Investor Added Message ", action.BOOLEAN));
									}
									if (click(driver,
											fp.getManageInvestorAddedPopupCrossIcon(Workspace.InvestorWorkspace, 60),
											"Close Button", action.SCROLLANDBOOLEAN)) {
										appLog.info("Clicked on close button");
										if (fp.getManageInvestorAddedPopupCrossIcon(Workspace.InvestorWorkspace,
												10) != null) {
											appLog.info("Popup is not closed");
											saa.assertTrue(false, "Popup is not closed");
										} else {
											appLog.info("Popup is closed");
										}
									} else {
										appLog.info("Not able to click on close button");
										saa.assertTrue(false, "Not able to click on close button");
									}
								} else {
									appLog.info("Mange Invetsor added popup is not displaying");
									saa.assertTrue(false, "Mange Invetsor added popup is not displaying");
								}
							} else {
								appLog.info("Not able to click on LP 2 checkbox");
								saa.assertTrue(false, "Not able to click on LP 2 checkbox ");
							}
						} else {
							appLog.info("Not able to click on Institution 2");
							saa.assertTrue(false, "Not able to click on Institution 2");
						}

						switchToDefaultContent(driver);
						switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
						scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
								"Investor workspace view");
						if (fp.verifyLPStructureInManageInvestor(M6Institution3, M6LimitedPartner4)) {
							if (click(driver,
									fp.getLimitedPartnerCheckBox(M6Institution3, M6LimitedPartner4,
											Workspace.InvestorWorkspace, 60),
									"LP 4 Checkbox", action.SCROLLANDBOOLEAN)) {
								switchToDefaultContent(driver);
								switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
								if (fp.getManageInvestorAddedPopupHeader(Workspace.InvestorWorkspace, 60) != null) {
									appLog.info("Confirmation popup is displaying");
									if (bp.verifyErrorMessageOnPage(
											FundsPageErrorMessage.manageInvestorAddedPopupMessage,
											fp.getManageInvestorAddedPopupMessage(Workspace.InvestorWorkspace, 60),
											"Investor Added Message ")) {
										appLog.info("Error Message is verified  Manage Investor Added Popup");
									} else {
										saa.assertTrue(false,
												"Error Message is not verified  Manage Investor Added Popup .Expected:"
														+ FundsPageErrorMessage.manageInvestorAddedPopupMessage
														+ " Actual"
														+ getText(driver,
																fp.getManageInvestorAddedPopupMessage(
																		Workspace.InvestorWorkspace, 60),
																"Investor Added Message ", action.BOOLEAN));
									}
									if (click(driver,
											fp.getManageInvestorAddedPopupCrossIcon(Workspace.InvestorWorkspace, 60),
											"Close Button", action.SCROLLANDBOOLEAN)) {
										appLog.info("Clicked on close button");
										if (fp.getManageInvestorAddedPopupCrossIcon(Workspace.InvestorWorkspace,
												10) != null) {
											appLog.info("Popup is not closed");
											saa.assertTrue(false, "Popup is not closed");
										} else {
											appLog.info("Popup is closed");
										}
									} else {
										appLog.info("Not able to click on close button");
										saa.assertTrue(false, "Not able to click on close button");
									}
								} else {
									appLog.info("Mange Invetsor added popup is not displaying");
									saa.assertTrue(false, "Mange Invetsor added popup is not displaying");
								}
							} else {
								appLog.info("Not able to click on LP 4 checkbox");
								saa.assertTrue(false, "Not able to click on LP 4 checkbox ");
							}
						} else {
							appLog.info("Not able to click on Institution 3");
							saa.assertTrue(false, "Not able to click on Institution 3");
						}

						if (click(driver, fp.getManageInvestorPopupCrossIcon(Workspace.InvestorWorkspace, 60),
								"Investor popup cross icon", action.SCROLLANDBOOLEAN)) {
							switchToDefaultContent(driver);
							switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
							scrollDownThroughWebelement(driver,
									fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
									"Investor workspace view");
							if (fp.clickOnLimitedPartnerFolder(M6Institution1, M6LimitedPartner1,
									Workspace.InvestorWorkspace, 60)) {
								appLog.info("Folder structure is verified");
							} else {
								appLog.info("Folder Structure is not verified");
								saa.assertTrue(false, "Folder structure is not verified");
							}
							if (fp.clickOnLimitedPartnerFolder(M6Institution2, M6LimitedPartner2,
									Workspace.InvestorWorkspace, 60)) {
								appLog.info("Folder structure is verified");
							} else {
								appLog.info("Folder Structure is not verified");
								saa.assertTrue(false, "Folder structure is not verified");
							}
							if (fp.clickOnLimitedPartnerFolder(M6Institution3, M6LimitedPartner4,
									Workspace.InvestorWorkspace, 60)) {
								appLog.info("Folder structure is verified");
							} else {
								appLog.info("Folder Structure is not verified");
								saa.assertTrue(false, "Folder structure is not verified");
							}
						} else {
							appLog.info("Not able to click on investor popup cross icon");
							saa.assertTrue(false, "Not able to click on manage invetsor popup cross icon");
						}
					} else {
						appLog.info("Not Able to select value form show dropdown");
						saa.assertTrue(false, "Not Able to select value form show dropdown");
					}
					if (click(driver, fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60), "Investor icon",
							action.SCROLLANDBOOLEAN)) {
						if (selectVisibleTextFromDropDown(driver,
								fp.getManageInvestorShowDropdown(Workspace.InvestorWorkspace, 60), "Show Dropdown",
								"Institutions with Folder Built")) {
							appLog.info("Able to select value form show dropdown");
							if (fp.verifyLPStructureInManageInvestor(M6Institution1, M6LimitedPartner1)) {
								appLog.info("Limited Partner 1 is displaying under institution1");
							} else {
								appLog.info("Limited Partner 1 is not displaying under institution1");
								saa.assertTrue(false, "Limited Partner 1 is not displaying under institution1");
							}

							if (fp.verifyLPStructureInManageInvestor(M6Institution2, M6LimitedPartner2)) {
								appLog.info("Limited Partner 2 is displaying under institution2");
							} else {
								appLog.info("Limited Partner 2 is not displaying under institution2");
								saa.assertTrue(false, "Limited Partner 2 is not displaying under institution2");
							}
							if (fp.verifyLPStructureInManageInvestor(M6Institution3, M6LimitedPartner4)) {
								appLog.info("Limited Partner 4 is displaying under institution3");
							} else {
								appLog.info("Limited Partner 4 is not displaying under institution3");
								saa.assertTrue(false, "Limited Partner 4 is not displaying under institution3");
							}

						} else {
							appLog.info("Not able to select value from the dropdown");
							saa.assertTrue(false, "Not able to select value from the dropdown");
						}
						if (selectVisibleTextFromDropDown(driver,
								fp.getManageInvestorShowDropdown(Workspace.InvestorWorkspace, 60), "Show Dropdown",
								"Institutions without Folder Built")) {
							appLog.info("Able to select value form show dropdown");
							ele = FindElement(driver, "//div[@id='navtreefund11MIMININV']//a//span", "All Investor",
									action.SCROLLANDBOOLEAN, 60);
							if (ele != null) {
								appLog.info("All Invetsor label is displaying");
							} else {
								appLog.info("All Investor label is not displaying");
								saa.assertTrue(false, "ALl Investor label is not displaying");
							}
						} else {
							appLog.info("Not able to select value from the dropdown");
							saa.assertTrue(false, "Not able to select value from the dropdown");
						}

					} else {
						appLog.info("Not able to click on manage investor icon");
						saa.assertTrue(false, "Not able to click on manage investor icon");
					}
				} else {
					appLog.info("Not able to click on manage investor icon");
					saa.assertTrue(false, "Not able to click on manage investor icon");
				}
			} else {
				appLog.info("Not able to click on created fund");
				saa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on funds tab");
			saa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M6tc015_ProvideContactAccess() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M6FundName1)) {
				if (fp.inviteContact(M6Institution1 + "/" + M6LimitedPartner1, M6Contact1EmailId, null,
						FolderType.Standard, "Upload", "Yes", "No", null, Workspace.InvestorWorkspace, null)) {
					appLog.info("Contact get invited successfully");
				} else {
					appLog.info("Not able to invite contact");
					saa.assertTrue(false, "Not able to invite contact");
				}
				if (fp.inviteContact(M6Institution2 + "/" + M6LimitedPartner2, M6Contact2EmailId, null,
						FolderType.Standard, "Upload", "Yes", "No", null, Workspace.InvestorWorkspace,
						M6Contact2EmailId)) {
					appLog.info("Contact get invited successfully");
				} else {
					appLog.info("Not able to invite contact");
					saa.assertTrue(false, "Not able to invite contact");
				}
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 30));
				if (fp.sendInvitationMail(Workspace.InvestorWorkspace, M6Contact1EmailId, "All Folders", null)) {
					appLog.info("Mail sent succesfully to user 1");
				} else {
					appLog.info("Mail not sent succesfully to user 1");
					saa.assertTrue(false, "Mail not sent succesfully to user 1");
				}
				if (fp.sendInvitationMail(Workspace.InvestorWorkspace, M6Contact2EmailId, M6LimitedPartner2, null)) {
					appLog.info("Mail sent succesfully to user 2");
				} else {
					appLog.info("Mail not sent succesfully to user 2");
					saa.assertTrue(false, "Mail not sent succesfully to user 2");
				}
			} else {
				appLog.info("Not able to click on created fund");
				saa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on Funds tab");
			saa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer afp = new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M6Contact1EmailId, adminPassword);
		String firmName = getSelectedOptionOfDropDown(driver, afp.getFirmNameDropdown(60), "Firm name dropdown",
				"text");
		if (firmName.equalsIgnoreCase(Org1FirmName)) {
			appLog.info("Firm Page Is opened");
		} else {
			appLog.info("Firm Page is not opened");
			saa.assertTrue(false, "Firm Page is not opened");
		}
		if (isDisplayed(driver, ifp.getActivitiesCreatedOnLabel(10), "Visibility", 10,
				"Activity created on label") == null) {
			appLog.info("All Document Tab is opened");
		} else {
			appLog.info("All Document Tab is not opened");
			saa.assertTrue(false, "All Document Tab is not opened");
		}
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			appLog.info("Clicked on Current investments tab");
			String investment = getSelectedOptionOfDropDown(driver,
					ifp.getPotentialAndCurrentInvestmentInvestmentDropdown(60), "Current Investment dropdown", "text");
			if (investment.equalsIgnoreCase(M6FundName1)) {
				appLog.info("Workspace is visible");
			} else {
				appLog.info("Workspace is not visible");
				saa.assertTrue(false, "Workspace is not visible");
			}
		} else {
			appLog.info("Not able to click on Current investments tab");
			saa.assertTrue(false, "Not able to click on Current investments tab");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		afp = new AllFirmsPageBusinesslayer(driver);
		ifp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M6Contact1EmailId, adminPassword);
		firmName = getSelectedOptionOfDropDown(driver, afp.getFirmNameDropdown(60), "Firm name dropdown", "text");
		if (firmName.equalsIgnoreCase(Org1FirmName)) {
			appLog.info("Firm Page Is opened");
		} else {
			appLog.info("Firm Page is not opened");
			saa.assertTrue(false, "Firm Page is not opened");
		}
		if (isDisplayed(driver, ifp.getActivitiesCreatedOnLabel(10), "Visibility", 10,
				"Activity created on label") == null) {
			appLog.info("All Document Tab is opened");
		} else {
			appLog.info("All Document Tab is not opened");
			saa.assertTrue(false, "All Document Tab is not opened");
		}
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			appLog.info("Clicked on Current investments tab");
			String investment = getSelectedOptionOfDropDown(driver,
					ifp.getPotentialAndCurrentInvestmentInvestmentDropdown(60), "Current Investment dropdown", "text");
			if (investment.equalsIgnoreCase(M6FundName1)) {
				appLog.info("Workspace is visible");
			} else {
				appLog.info("Workspace is not visible");
				saa.assertTrue(false, "Workspace is not visible");
			}
		} else {
			appLog.info("Not able to click on Current investments tab");
			saa.assertTrue(false, "Not able to click on Current investments tab");
		}
		lp.investorLogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M6tc016_RenamLPNameFromInstitutionPageAndCheckOnManageInvestorSectionWhoseFolderStructureCreated() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedLP(M6LimitedPartner1)) {
				if (click(driver, bp.getEditButton(60), "Edit Button", action.SCROLLANDBOOLEAN)) {
					if (sendKeys(driver, ip.getLegalNameTextBox(60), M6LimitedPartner1 + "Renamed",
							"Legal name text box", action.SCROLLANDBOOLEAN)) {
						if (click(driver, bp.getSaveButton(60), "Save Button", action.SCROLLANDBOOLEAN)) {
							String updtaedLimitedPartnerName = ip.getLegalNameLabelTextbox(60).getText().trim();
							ThreadSleep(2000);
							if (updtaedLimitedPartnerName.contains(M6LimitedPartner1 + "Renamed")) {
								appLog.info("Limited Partner name has been updated successfully");
								ThreadSleep(2000);
								if (bp.clickOnTab(TabName.FundsTab)) {
									if (fp.clickOnCreatedFund(M6FundName1)) {
										switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
										scrollDownThroughWebelement(driver,
												fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
												"InvestorWorkspace View.");
										if (click(driver, fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60),
												"Manage Investor Icon", action.SCROLLANDBOOLEAN)) {
											if (fp.verifyLPStructureInManageInvestor(M6Institution1,
													M6LimitedPartner1)) {
												appLog.info(
														"Renamed Limited Partner 1 is not displaying under institution1");
											} else {
												appLog.info(
														"Renamed Limited Partner 1 is displaying under institution1");
												saa.assertTrue(false,
														"Renamed Limited Partner 1 is displaying under institution1");
											}
											if (click(driver,
													fp.getLimitedPartnerCheckBox(M6Institution1, M6LimitedPartner1,
															Workspace.InvestorWorkspace, 60),
													"LP 1 Checkbox", action.SCROLLANDBOOLEAN)) {
												if (fp.getManageInvestorDeletedPopupHeader(Workspace.InvestorWorkspace,
														60) != null) {
													appLog.info("Confirmation popup is displaying");
													if (bp.verifyErrorMessageOnPage(
															FundsPageErrorMessage.manageInvestorDeletedPopupMessage,
															fp.getManageInvestorDeletedPopupMessage(
																	Workspace.InvestorWorkspace, 60),
															"Investor removed Message ")) {
														appLog.info(
																"Error Message is verified  Manage Investor removed Popup");
													} else {
														saa.assertTrue(false,
																"Error Message is not verified  Manage Investor removed Popup .Expected:"
																		+ FundsPageErrorMessage.manageInvestorDeletedPopupMessage
																		+ " Actual"
																		+ getText(driver,
																				fp.getManageInvestorDeletedPopupMessage(
																						Workspace.InvestorWorkspace,
																						60),
																				"Investor removed Message ",
																				action.BOOLEAN));
													}
													if (click(driver,
															fp.getManageInvestorDeletedPopupCloseButton(
																	Workspace.InvestorWorkspace, 60),
															"Cross icon", action.SCROLLANDBOOLEAN)) {
														appLog.info("Clicked on Close Button");
														if (fp.getManageInvestorDeletedPopupCloseButton(
																Workspace.InvestorWorkspace, 10) != null) {
															appLog.info("Popup is not closed");
															saa.assertTrue(false, "Popup is not closed");
														} else {
															appLog.info("Popup is closed");
															if (fp.verifyLPStructureInManageInvestor(M6Institution1,
																	M6LimitedPartner1 + "Renamed")) {
																appLog.info(
																		"Renamed Limited Partner 1 is displaying under institution1");
															} else {
																appLog.info(
																		"Renamed Limited Partner 1 is not displaying under institution1");
																saa.assertTrue(false,
																		"Renamed Limited Partner 1 is not displaying under institution1");
															}
															if (click(driver,
																	fp.getLimitedPartnerCheckBox(M6Institution1,
																			M6LimitedPartner1 + "Renamed",
																			Workspace.InvestorWorkspace, 60),
																	"LP 1 Checkbox", action.SCROLLANDBOOLEAN)) {
																if (click(driver,
																		fp.getManageInvestorAddedPopupCloseButton(
																				Workspace.InvestorWorkspace, 60),
																		"Close Button", action.SCROLLANDBOOLEAN)) {
																	if (click(driver,
																			fp.getManageInvestorDoneButton(
																					Workspace.InvestorWorkspace, 60),
																			"Done Button", action.SCROLLANDBOOLEAN)) {
																		switchToDefaultContent(driver);
																		switchToFrame(driver, 30,
																				bp.getFrame(PageName.FundsPage, 60));
																		scrollDownThroughWebelement(driver,
																				fp.getWorkspaceSectionView(
																						Workspace.InvestorWorkspace,
																						60),
																				"Investor workspace view");
																		if (fp.clickOnLimitedPartnerFolder(
																				M6Institution1,
																				M6LimitedPartner1 + "Renamed",
																				Workspace.InvestorWorkspace, 60)) {
																			appLog.info(
																					"Folder structure is verified and renamed LP name is displaying");
																		} else {
																			appLog.info(
																					"Folder Structure is not verified and renamed LP name is not displaying");
																			saa.assertTrue(false,
																					"Folder structure is not verified and renamed LP name is not displaying");
																		}
																		switchToDefaultContent(driver);
																		if (fp.inviteContact(
																				M6Institution1 + "/" + M6LimitedPartner1
																						+ "Renamed",
																				M6Contact1EmailId, null,
																				FolderType.Standard, "Upload", "Yes",
																				"No", null, Workspace.InvestorWorkspace,
																				null)) {
																			appLog.info(
																					"Contact get invited successfully");
																		} else {
																			appLog.info("Not able to invite contact");
																			saa.assertTrue(false,
																					"Not able to invite contact");
																		}
																	} else {
																		appLog.info("Not able to click on done button");
																		saa.assertTrue(false,
																				"not able to click on Done button");
																	}
																} else {
																	appLog.info("Not able to click on close button");
																	saa.assertTrue(false,
																			"Not able to click on close button");
																}
															} else {
																appLog.info(
																		"Not able to click on Limiter Partner 1 checkbox");
																saa.assertTrue(false,
																		"Not able to click on limited partner 1 checkbox");
															}
														}
													} else {
														appLog.info("Not able to click on close button");
														saa.assertTrue(false, "Not able to click on close button");
													}
												} else {
													appLog.info("Confirmation popup is not displaying");
													saa.assertTrue(false, "Confirmation popup is not displaying");
												}
											} else {
												appLog.info("Not able to click on Limiter Partner 1 checkbox");
												saa.assertTrue(false,
														"Not able to click on limited partner 1 checkbox");
											}
										} else {
											appLog.info("Not able to click on Manage Investor icon");
											saa.assertTrue(false, "Not able to click on manage investor icon");
										}
									} else {
										appLog.info("Not able to click on created fund");
										saa.assertTrue(false, "Not able to click on created fund");
									}
								} else {
									appLog.info("Not able to click on funds tab");
									saa.assertTrue(false, "Not able to click on funds tab");
								}
							} else {
								appLog.info("Limited Partner name has not been updated successfully");
								saa.assertTrue(false, "Limited Partner name has not been updated successfully");
							}
						} else {
							appLog.info("Not able to click on save button");
							saa.assertTrue(false, "Not able ot click on save button");
						}
					} else {
						appLog.info("Not able to enter values in legal name text box");
						saa.assertTrue(false, "Not able to enter values in legal name text box");
					}
				} else {
					appLog.info("Not able to click on edit icon");
					saa.assertTrue(false, "Not able to click on edit icon");
				}
			} else {
				appLog.info("Not able to click on Created Limited Partner ");
				saa.assertTrue(false, "Not able to click on Created Limited Partner ");
			}
		} else {
			appLog.info("Not able to click on Instituitions tab");
			saa.assertTrue(false, "Not able to click on Instituitions tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		saa.assertAll();
	}

	@Test
	public void M6tc017_RenameInvestorNameFromManageInvestorWindowWhoseFolderStructureCreated() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M6FundName1)) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
						"InvestorWorkspace View.");
				if (click(driver, fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60), "Manage Investor Icon",
						action.SCROLLANDBOOLEAN)) {
					if (fp.clickOnRenameManageTargetLimitedPartner(M6Institution3, M6LimitedPartner3)) {
						appLog.info("Rename icon is visible for Limited Partner " + M6LimitedPartner3);
						saa.assertTrue(false, "Rename icon is visible for Limited Partner " + M6LimitedPartner3);
					} else {
						appLog.info("Rename icon is not visible for Limited Partner " + M6LimitedPartner3);
					}
					if (fp.clickOnRenameManageTargetLimitedPartner(M6Institution1, M6LimitedPartner1 + "Renamed")) {
						appLog.info("Rename icon is visible for Limited Partner" + M6LimitedPartner1);
						ThreadSleep(2000);
						String renamePopup = fp.getManageInvestorRenamePopupHeader(Workspace.InvestorWorkspace, 60)
								.getText().trim();
						if (renamePopup.equalsIgnoreCase("Rename Investor Account")) {
							appLog.info("Rename Manage Investor poup is displaying");
							if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.manageInvestorRenamePopupMessage,
									fp.getManageInvestorRenamePopupMessage(Workspace.InvestorWorkspace, 60),
									"Investor Rename Message ")) {
								appLog.info("Error Message is verified  Manage Investor Rename Popup");
							} else {
								saa.assertTrue(false,
										"Error Message is not verified  Manage Investor Rename Popup .Expected:"
												+ FundsPageErrorMessage.manageInvestorRenamePopupMessage + " Actual"
												+ getText(driver,
														fp.getManageInvestorRenamePopupMessage(
																Workspace.InvestorWorkspace, 60),
														"Investor Rename Message ", action.BOOLEAN));
							}

							if (fp.getManageInvestorRenamePopupTextBox(Workspace.InvestorWorkspace, 60) != null) {
								appLog.info("Rename Text Box is displaying");
							} else {
								appLog.info("Rename Tet Box is not displaying");
								saa.assertTrue(false, "Rename Tet Box is not displaying");
							}
							if (fp.getManageInvestorRenamePopupApplyButton(Workspace.InvestorWorkspace, 60) != null) {
								appLog.info("Rename popup apply button is displaying");
							} else {
								appLog.info("Rename popup apply button is not displaying");
								saa.assertTrue(false, "Rename popup apply button is not displaying");
							}
							if (fp.getManageInvestorRenamePopupCancelButton(Workspace.InvestorWorkspace, 60) != null) {
								appLog.info("Rename popup cancel button is displaying");
							} else {
								appLog.info("Rename popup cancel button is not displaying");
								saa.assertTrue(false, "Rename popup cancel button is not displaying");
							}
							ThreadSleep(2000);
							if (sendKeys(driver,
									fp.getManageInvestorRenamePopupTextBox(Workspace.InvestorWorkspace, 60),
									M6LimitedPartner1+"Update", "Text Box ", action.SCROLLANDBOOLEAN)) {
								switchToDefaultContent(driver);
								switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
								if (click(driver,
										fp.getManageInvestorRenamePopupCancelButton(Workspace.InvestorWorkspace, 60),
										"Cancel button", action.SCROLLANDBOOLEAN)) {
									appLog.info("Clicked on cancel button");
									if (fp.verifyLPStructureInManageInvestor(M6Institution1,
											M6LimitedPartner1 + "Renamed")) {
										appLog.info("Old Limited Partner 1 name is displaying under institution1");
									} else {
										appLog.info("Old Limited Partner 1 name not displaying under institution1");
										saa.assertTrue(false,
												"Old Limited Partner 1 name not displaying under institution1");
									}
								} else {
									appLog.info("Not able to click on cancel button");
									saa.assertTrue(false, "Not able to click on cancel button");
								}
							} else {
								appLog.info("Not able to enter value in text box");
								saa.assertTrue(false, "Not able to enter value in text box");
							}
						} else {
							appLog.info("Rename Manage Invetsor Popup is not displaying");
							saa.assertTrue(false, "Rename Manage Invetsor Popup is not displaying");
						}
					} else {
						appLog.info("Rename icon is not visible for LimitedPartner" + M6LimitedPartner1);
						saa.assertTrue(false, "Rename icon is visible for LimitedPartner" + M6LimitedPartner1);
					}
					if (fp.clickOnRenameManageTargetLimitedPartner(M6Institution1, M6LimitedPartner1 + "Renamed")) {
						if (sendKeys(driver, fp.getManageInvestorRenamePopupTextBox(Workspace.InvestorWorkspace, 60),
								M6LimitedPartner1 + "Update&*", "Text Box ", action.SCROLLANDBOOLEAN)) {
							if (click(driver,
									fp.getManageInvestorRenamePopupApplyButton(Workspace.InvestorWorkspace, 60),
									"Apply button", action.SCROLLANDBOOLEAN)) {
								String alerttext = switchToAlertAndGetMessage(driver, 60, action.GETTEXT);
								if (alerttext.equalsIgnoreCase(
										FundsPageErrorMessage.manageInvestorRenameSpecialCharacterMessage)) {
									appLog.info("Alert text is verified");
								} else {
									appLog.info("Alert text is not verified");
									saa.assertTrue(false, "Alert text is not veriifed");
								}
								switchToDefaultContent(driver);
								switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
								switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
								fp.getManageInvestorRenamePopupTextBox(Workspace.InvestorWorkspace, 60).clear();
								if (click(driver,
										fp.getManageInvestorRenamePopupApplyButton(Workspace.InvestorWorkspace, 60),
										"Apply button", action.SCROLLANDBOOLEAN)) {
									if (bp.verifyErrorMessageOnPage(
											FundsPageErrorMessage.manageInvestorRenameFieldBlankErrorMessage,
											fp.getManageInvestorRenamePopupFieldBlankErrorMessage(
													Workspace.InvestorWorkspace, 60),
											"Rename Text Box Blank Error Message")) {
										appLog.info(
												"Error Message is verified  Manage Investor Rename Popup when field is blank");
									} else {
										saa.assertTrue(false,
												"Error Message is not verified  Manage Investor Rename Popup when field is blank .Expected:"
														+ FundsPageErrorMessage.manageInvestorRenameFieldBlankErrorMessage
														+ " Actual"
														+ getText(driver,
																fp.getManageInvestorRenamePopupFieldBlankErrorMessage(
																		Workspace.InvestorWorkspace, 60),
																"Rename Text Box Blank Error Message ",
																action.BOOLEAN));
									}
								} else {
									appLog.info("Not able to click on apply button");
									saa.assertTrue(false, "Not able to click on apply button");
								}
							} else {
								appLog.info("Not able to click on Apply Button button");
								saa.assertTrue(false, "Not able to click on Apply button");
							}
						} else {
							appLog.info("Not able to enter value in text box");
							saa.assertTrue(false, "Not able to enter value in text box");
						}
						switchToDefaultContent(driver);
						switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
						if (sendKeys(driver, fp.getManageInvestorRenamePopupTextBox(Workspace.InvestorWorkspace, 60),
								M6LimitedPartner1 + "Update", "Text Box ", action.SCROLLANDBOOLEAN)) {
							switchToDefaultContent(driver);
							switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
							if (click(driver, fp.getManageInvestorRenamePopupCrossIcon(Workspace.InvestorWorkspace, 60),
									"Cross Icon", action.SCROLLANDBOOLEAN)) {
								switchToDefaultContent(driver);
								switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
								if (fp.verifyLPStructureInManageInvestor(M6Institution1,
										M6LimitedPartner1 + "Renamed")) {
									appLog.info("Old Limited Partner 1 name is displaying under institution1");
								} else {
									appLog.info("Old Limited Partner 1 name not displaying under institution1");
									saa.assertTrue(false,
											"Old Limited Partner 1 name not displaying under institution1");
								}
							} else {
								appLog.info("Not able to click on Cross Icon");
								saa.assertTrue(false, "Not able to click on Cross Icon");
							}
						} else {
							appLog.info("Not able to enter value in text box");
							saa.assertTrue(false, "Not able to enter value in text box");
						}
					} else {
						appLog.info("Rename icon is not visible for LimitedPartner" + M6LimitedPartner1);
						saa.assertTrue(false, "Rename icon is visible for LimitedPartner" + M6LimitedPartner1);
					}
					if (fp.clickOnRenameManageTargetLimitedPartner(M6Institution1, M6LimitedPartner1 + "Renamed")) {
						if (sendKeys(driver, fp.getManageInvestorRenamePopupTextBox(Workspace.InvestorWorkspace, 60),
								M6LimitedPartner1 + "Update", "Text Box ", action.SCROLLANDBOOLEAN)) {
							if (click(driver,
									fp.getManageInvestorRenamePopupApplyButton(Workspace.InvestorWorkspace, 60),
									"Apply button", action.SCROLLANDBOOLEAN)) {
								ThreadSleep(3000);
								if (fp.verifyLPStructureInManageInvestor(M6Institution1,
										M6LimitedPartner1+"Update")) {
									appLog.info("New Limited Partner 1 name is displaying under institution1");
								} else {
									appLog.info("New Limited Partner 1 name not displaying under institution1");
									saa.assertTrue(false,
											"New Limited Partner 1 name not displaying under institution1");
								}
								if (click(driver, fp.getManageInvestorDoneButton(Workspace.InvestorWorkspace, 60),
										"done button", action.SCROLLANDBOOLEAN)) {
									switchToDefaultContent(driver);
									switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
									scrollDownThroughWebelement(driver,
											fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
											"Investor workspace view");
									if (fp.clickOnLimitedPartnerFolder(M6Institution1, M6LimitedPartner1 + "Update",
											Workspace.InvestorWorkspace, 60)) {
										appLog.info("Folder structure is verified and Updtaed LP name is displaying");
									} else {
										appLog.info(
												"Folder Structure is not verified and Updtaed LP name is not displaying");
										saa.assertTrue(false,
												"Folder structure is not verified and Updtaed LP name is not displaying");
									}
									switchToDefaultContent(driver);
									if (bp.clickOnTab(TabName.InstituitonsTab)) {
										if (ip.verifyDeletedLimitedPartner(M6LimitedPartner1 + "Update")) {
											appLog.info("Updated LimitedPartner is not displaying");
										} else {
											appLog.info("Updated LimitedPartner is displaying");
											saa.assertTrue(false, "Updated LimitedPartner is displaying");
										}
									} else {
										appLog.info("Not able to click on institutions Tab");
										saa.assertTrue(false, "Not able to click on institution tab");
									}
									if (bp.clickOnTab(TabName.ContactTab)) {
										if (cp.clickOnCreatedContact(M6Contact1FirstName, M6Contact1LastName, null)) {
											switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
											scrollDownThroughWebelement(driver,
													fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
													"InvestorWorkspace View.");
											if (fp.verifyFolderPathdummy("", null, M6LimitedPartner1 + "Update",
													M6FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace,
													60)) {
												appLog.info("Updated institution name is displaying");
											} else {
												appLog.info("Updated institution name is not displaying");
												saa.assertTrue(false, "Updated institution name is not displaying");
											}
										} else {
											appLog.info("Not able to click on craeted contact");
											saa.assertTrue(false, "Not able to click on created contact");
										}
									} else {
										appLog.info("Not able to click on Contact Tab");
										saa.assertTrue(false, "Not able to click on Contact tab");
									}
									switchToDefaultContent(driver);
									if (bp.clickOnTab(TabName.CommitmentsTab)) {
										if (click(driver, bp.getGoButton(60), "Go Button", action.SCROLLANDBOOLEAN)) {
											WebElement ele = FindElement(driver,
													"(//span[text()='" + M6LimitedPartner1 + "Renamed"
															+ "']/../../../..//a)[3]/span",
													"Commitment ID", action.SCROLLANDTHROWEXCEPTION, 60);
											if (ele != null) {
												if (click(driver, ele, "CommitmentId", action.SCROLLANDBOOLEAN)) {
													switchToFrame(driver, 30,
															bp.getFrame(PageName.CommitmentsPage, 60));
													scrollDownThroughWebelement(driver,
															fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
															"InvestorWorkspace View.");
													if (fp.verifyFolderPathdummy("", null, M6LimitedPartner1 + "Update",
															M6FundName1, PageName.CommitmentsPage,
															Workspace.InvestorWorkspace, 60)) {
														appLog.info(
																"Folder structure is verified in commitment page with updated limited partner name");
													} else {
														appLog.info(
																"Folder structure is not verified in commitment page with updated limited partner name");
														saa.assertTrue(false,
																"Folder structure is verified in commitment page with updated limited partner name");
													}

												} else {
													appLog.info("Not able to click on commitment ID");
													saa.assertTrue(false, "Not able to click on commitment ID");
												}
											} else {
												appLog.info("Not able to find commitmentID");
												saa.assertTrue(false, "Not able to find commitmentID");
											}
										} else {
											appLog.info("Not able to click on go Button");
											saa.assertTrue(false, "Not able to click on go button");
										}
									} else {
										appLog.info("Not able to click on Commitment Tab");
										saa.assertTrue(false, "Not able to click on Commitment tab");
									}
								} else {
									appLog.info("Not able to click on Done buton");
									saa.assertTrue(false, "Not able to click on done button");
								}
							} else {
								appLog.info("Not able to click on apply button");
								saa.assertTrue(false, "Not able to click on apply button");
							}
						} else {
							appLog.info("Not able to enter value in text box");
							saa.assertTrue(false, "Not able to enter value in text box");
						}
					} else {
						appLog.info("Rename icon is not visible for LimitedPartner" + M6LimitedPartner1);
						saa.assertTrue(false, "Rename icon is visible for LimitedPartner" + M6LimitedPartner1);
					}

				} else {
					appLog.info("Not able to click on manage Invetsor Icon");
					saa.assertTrue(false, "Not able to click on manage investor icon");

				}
			} else {
				appLog.info("Not able to click on created fund");
				saa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on funds tab");
			saa.assertTrue(false, "Not able to lcick on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M6Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			ThreadSleep(2000);
			if (fp.clickOnLimitedPartnerFolder(null, M6LimitedPartner1 + "Update", Workspace.CurrentInvestment, 60)) {
				appLog.info("Folder Structure is verified and Updated Limited Partner name is displaying");
			} else {
				appLog.info("Folder Structure is not verified and Updated Limited Partner name is not displaying");
				saa.assertTrue(false,
						"Folder Structure is not verified and Updated Limited Partner name is not displaying");
			}
		} else {
			appLog.info("Not able to click on Current invetsment tab");
			saa.assertTrue(false, "Not able to click on Current invetsment tab");
		}
		lp.investorLogout();
		sa.combineAssertions(saa);
		sa.assertAll();

	}

	@Test
	public void M6tc018_UncheckInvestorFromManageInvestorAndCheckContactAccess() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M6FundName1)) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
						"InvestorWorkspace View.");
				if (click(driver, fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60), "Manage Investor Icon",
						action.SCROLLANDBOOLEAN)) {
					switchToDefaultContent(driver);
					switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
							"InvestorWorkspace View.");

					if (fp.verifyLPStructureInManageInvestor(M6Institution1, M6LimitedPartner1 + "Update")) {
						appLog.info("Limited Partner 1 with rename is displaying under institution1");
					} else {
						appLog.info("Limited Partner 1 with rename is not displaying under institution1");
						saa.assertTrue(false, "Limited Partner 1 with rename is not displaying under institution1");
					}
					if (isSelected(driver, fp.getLimitedPartnerCheckBox(M6Institution1, M6LimitedPartner1 + "Update",
							Workspace.InvestorWorkspace, 60), "LP1 checkbox")) {
						appLog.info("LP 1 checkbox is selected");
					} else {
						appLog.info("LP 1 checkbox is not selected");
						saa.assertTrue(false, "LP1 checkox is not selected");
					}
					if (click(driver, fp.getLimitedPartnerCheckBox(M6Institution1, M6LimitedPartner1 + "Update",
							Workspace.InvestorWorkspace, 60), "LP1 checkbox", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getManageInvestorDeletedPopupCloseButton(Workspace.InvestorWorkspace, 60),
								"Manage Investor Deleted popup Close Button", action.BOOLEAN)) {
							switchToDefaultContent(driver);
							switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
							scrollDownThroughWebelement(driver,
									fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
									"InvestorWorkspace View.");
							if (click(driver, fp.getManageInvestorDoneButton(Workspace.InvestorWorkspace, 60),
									"Invetsor workspace", action.SCROLLANDBOOLEAN)) {
								switchToDefaultContent(driver);
								switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
								scrollDownThroughWebelement(driver,
										fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
										"InvestorWorkspace View.");
								if (fp.verifyFolderPathdummy("", M6Institution1, M6LimitedPartner1 + "Update",
										M6FundName1, PageName.FundsPage, Workspace.InvestorWorkspace, 20)) {
									appLog.info("Institution 1 folder is displaying in folder Structure");
									saa.assertTrue(false, "Institution 1 folder is displaying in folder Structure");
								} else {
									appLog.info("Institution 1 folder is not displaying in folder Structure");
								}
							} else {
								appLog.info("Not able to click on done button");
								saa.assertTrue(false, "Not able to click on done button");
							}

						} else {
							appLog.info("Not able to click on Close button");
							saa.assertTrue(false, "Not able to click on close button");
						}
					} else {
						appLog.info("Not able to click on LP1 checkbox");
						saa.assertTrue(false, "Not able to click on LP1 checkbox");
					}
				} else {
					appLog.info("Not able to click on manage investor icon");
					saa.assertTrue(false, "Not able to click on manage investor icon");
				}
			} else {
				appLog.info("Not able to click on Created fund");
				saa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on fundstab");
			saa.assertTrue(false, "Not able to click on funds tab ");
		}
		switchToDefaultContent(driver);
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedLP(M6LimitedPartner1 + "Renamed")) {
				switchToFrame(driver, 30, bp.getFrame(PageName.InstitutionsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
						"InvestorWorkspace View.");
				if (ip.getErrorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace(60) != null) {
					appLog.info("Investor workspace has been removed successfully");
				} else {
					appLog.info("Investor workspace has not been removed successfully");
					saa.assertTrue(false, "Investor workspace has not been removed successfully");
				}
			} else {
				appLog.info("Not able to click on created Limited Partner");
				saa.assertTrue(false, "Not able to click on created Limited Partner");
			}
		} else {
			appLog.info("Not able to click on institution tab");
			saa.assertTrue(false, "Not able to click on institution tab");
		}
		switchToDefaultContent(driver);
		if (bp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M6Contact1FirstName, M6Contact1LastName, null)) {
				switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
						"InvestorWorkspace View.");
				if (ip.getErrorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace(60) != null) {
					appLog.info("Investor workspace has been removed successfully");
				} else {
					appLog.info("Investor workspace has not been removed successfully");
					saa.assertTrue(false, "Investor workspace has not been removed successfully");
				}
			} else {
				appLog.info("Not able to click on created contact");
				saa.assertTrue(false, "Not able to click on created contact");
			}
		} else {
			appLog.info("Not able to click on Contact tab");
			saa.assertTrue(false, "Not able to click on contact tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		AllFirmsPageBusinesslayer afp = new AllFirmsPageBusinesslayer(driver);
		lp.investorLogin(M6Contact1EmailId, adminPassword);
		String firmName = getSelectedOptionOfDropDown(driver, afp.getFirmNameDropdown(60), "Firm name dropdown",
				"text");
		if (firmName.equalsIgnoreCase(Org1FirmName)) {
			appLog.info("all Firm Page Is opened");
		} else {
			appLog.info("Firm Page is not opened");
			saa.assertTrue(false, "Firm Page is not opened");
		}
		if (click(driver, ifp.getInvestmentsTab(60), "Invetsments Tab", action.SCROLLANDBOOLEAN)) {
			if (ifp.getCurrentInvestmentTab(10) != null) {
				appLog.info("Current investment tab is displaying");
				saa.assertTrue(false, "Current investment tab is displaying");
			} else {
				appLog.info("Current investment tab is not displaying");
			}
		} else {
			appLog.info("Not able to click on Investment tab");
			saa.assertTrue(false, "Not able to click on investments tab");
		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		cp = new ContactPageBusinessLayer(driver);
		ip = new InstitutionPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M6FundName1)) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
						"InvestorWorkspace View.");
				if (click(driver, fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60), "Manage Investor Icon",
						action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getLimitedPartnerCheckBox(M6Institution1, M6LimitedPartner1 + "Renamed",
							Workspace.InvestorWorkspace, 60), "LP1 checkbox", action.SCROLLANDBOOLEAN)) {
						switchToDefaultContent(driver);
						switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
						scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
								" InvestorWorkspace View.");
						switchToDefaultContent(driver);
						switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
						if (click(driver, fp.getManageInvestorAddedPopupCloseButton(Workspace.InvestorWorkspace, 60),
								"Manage Investor Added popup Close Button", action.BOOLEAN)) {
							switchToDefaultContent(driver);
							switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
							scrollDownThroughWebelement(driver,
									fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
									"InvestorWorkspace View.");
							if (click(driver, fp.getManageInvestorDoneButton(Workspace.InvestorWorkspace, 60),
									"InvestorWorkspace workspace", action.SCROLLANDBOOLEAN)) {
								switchToDefaultContent(driver);
								switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
								if (fp.verifyFolderPathdummy("", M6Institution1, M6LimitedPartner1 + "Renamed",
										M6FundName1, PageName.FundsPage, Workspace.InvestorWorkspace, 20)) {
									appLog.info("Limited Partner 1 folder is displaying in folder Structure");
								} else {
									appLog.info("Limited Partner 1 folder is not displaying in folder Structure");
									saa.assertTrue(false,
											"Limited Partner 1 folder is not displaying in folder Structure");
								}
							} else {
								appLog.info("Not able to click on done button");
								saa.assertTrue(false, "Not able to click on done button");
							}

						} else {
							appLog.info("Not able to click on Close button");
							saa.assertTrue(false, "Not able to click on close button");
						}
					} else {
						appLog.info("Not able to click on checkbox");
						saa.assertTrue(false, "Nt able to click on LP 1 checkbox");
					}
				} else {
					appLog.info("Not able to click on manage investor icon");
					saa.assertTrue(false, "Not able to click on manage investor icon");
				}
			} else {
				appLog.info("Not able to click on Created fund");
				saa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on fundstab");
			saa.assertTrue(false, "Not able to click on funds tab ");
		}
		switchToDefaultContent(driver);
		if (bp.clickOnTab(TabName.InstituitonsTab)) {
			if (ip.clickOnCreatedLP(M6LimitedPartner1 + "Renamed")) {
				switchToFrame(driver, 30, bp.getFrame(PageName.InstitutionsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
						"InvestorWorkspace View.");
				if (fp.verifyFolderPathdummy("", null, null, M6FundName1, PageName.InstitutionsPage,
						Workspace.InvestorWorkspace, 60)) {
					appLog.info("Investor workspace is displaying successfully");
				} else {
					appLog.info("Investor workspace is not displaying successfully");
					saa.assertTrue(false, "Investor workspace is not displaying successfully");
				}
			} else {
				appLog.info("Not able to click on created Limited Partner");
				saa.assertTrue(false, "Not able to click on created Limited Partner");
			}
		} else {
			appLog.info("Not able to click on institution tab");
			saa.assertTrue(false, "Not able to click on institution tab");
		}
		switchToDefaultContent(driver);
		if (bp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M6Contact1FirstName, M6Contact1LastName, null)) {
				switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
						"InvestorWorkspace View.");
				if (ip.getErrorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace(60) != null) {
					appLog.info("Investor workspace has been removed successfully");
				} else {
					appLog.info("Investor workspace has not been removed successfully");
					saa.assertTrue(false, "Investor workspace has not been removed successfully");
				}
			} else {
				appLog.info("Not able to click on created contact");
				saa.assertTrue(false, "Not able to click on created contact");
			}
		} else {
			appLog.info("Not able to click on Contact tab");
			saa.assertTrue(false, "Not able to click on contact tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		ifp = new InvestorFirmPageBusinesslayer(driver);
		afp = new AllFirmsPageBusinesslayer(driver);
		lp.investorLogin(M6Contact1EmailId, adminPassword);
		firmName = getSelectedOptionOfDropDown(driver, afp.getFirmNameDropdown(60), "Firm name dropdown", "text");
		if (firmName.equalsIgnoreCase(Org1FirmName)) {
			appLog.info("Firm Page Is opened");
		} else {
			appLog.info("Firm Page is not opened");
			saa.assertTrue(false, "Firm Page is not opened");
		}
		if (click(driver, ifp.getInvestmentsTab(60), "Invetsments Tab", action.SCROLLANDBOOLEAN)) {
			if (ifp.getCurrentInvestmentTab(10) != null) {
				appLog.info("Current investment tab is displaying");
				saa.assertTrue(false, "Current investment tab is displaying");
			} else {
				appLog.info("Current investment tab is not displaying");
			}
		} else {
			appLog.info("Not able to click on Investment tab");
			saa.assertTrue(false, "Not able to click on investments tab");
		}
		lp.investorLogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M6tc019_AddContactAccessAgainToNewlyAddedLP() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M6FundName1)) {
				if (fp.inviteContact(M6Institution1 + "/" + M6LimitedPartner1 + "Renamed", M6Contact1EmailId, null,
						FolderType.Standard, "Upload", "Yes", "No", null, Workspace.InvestorWorkspace, null)) {
					appLog.info("Contact get invited successfully");
				} else {
					appLog.info("Not able to invite contact");
					saa.assertTrue(false, "Not able to invite contact");
				}
				if (bp.clickOnTab(TabName.InstituitonsTab)) {
					if (ip.clickOnCreatedInstitution(M6Institution1)) {
						switchToFrame(driver, 30, bp.getFrame(PageName.InstitutionsPage, 60));
						scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
								"investorWorkspace View.");
						if (fp.verifyFolderPathdummy("", null, M6LimitedPartner1 + "Renamed", M6FundName1,
								PageName.InstitutionsPage, Workspace.InvestorWorkspace, 60)) {
							appLog.info("Investor workspace is displaying successfully");
						} else {
							appLog.info("Investor workspace is not displaying successfully");
							saa.assertTrue(false, "Investor workspace is not displaying successfully");
						}
					} else {
						appLog.info("Not able to click on craeted institution");
						saa.assertTrue(false, "Not able to click on craeted institution");
					}
				} else {
					appLog.info("Not able to click on institution tab");
					saa.assertTrue(false, "Not able to click on institution tab");
				}
				switchToDefaultContent(driver);
				if (bp.clickOnTab(TabName.ContactTab)) {
					if (cp.clickOnCreatedContact(M6Contact1FirstName, M6Contact1LastName, null)) {
						switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
						scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
								"InvestorWorkspace View.");
						if (fp.verifyFolderPathdummy("", null, M6LimitedPartner1 + "Renamed", M6FundName1,
								PageName.ContactsPage, Workspace.InvestorWorkspace, 60)) {
							appLog.info("Investor workspace is displaying successfully with Limited Partner name");
						} else {
							appLog.info("Investor workspace is not displaying successfully with Limited Partner name");
							saa.assertTrue(false,
									"Investor workspace is not displaying successfully with Limited Partner name");
						}
					} else {
						appLog.info("Not able to click on created contact");
						saa.assertTrue(false, "Not able to click on created contact");
					}
				} else {
					appLog.info("Not able to click on Contact tab");
					saa.assertTrue(false, "Not able to click on contact tab");
				}
			} else {
				appLog.info("Not able to click on created fund");
				saa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on funds tab");
			saa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		AllFirmsPageBusinesslayer afp = new AllFirmsPageBusinesslayer(driver);
		lp.investorLogin(M6Contact1EmailId, adminPassword);
		String firmName = getSelectedOptionOfDropDown(driver, afp.getFirmNameDropdown(60), "Firm name dropdown",
				"text");
		if (firmName.equalsIgnoreCase(Org1FirmName)) {
			appLog.info("Firm Page Is opened");
		} else {
			appLog.info("Firm Page is not opened");
			saa.assertTrue(false, "Firm Page is not opened");
		}
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			ThreadSleep(2000);
			if (fp.clickOnLimitedPartnerFolder(null, M6LimitedPartner1 + "Renamed", Workspace.CurrentInvestment, 60)) {
				appLog.info("Folder Structure is verified and Updated Limited Partner name is displaying");
			} else {
				appLog.info("Folder Structure is not verified and Updated Limited Partner name is not displaying");
				saa.assertTrue(false,
						"Folder Structure is not verified and Updated Limited Partner name is not displaying");
			}
		} else {
			appLog.info("Not able to click on Current invetsment tab");
			saa.assertTrue(false, "Not able to click on Current invetsment tab");
		}
		lp.investorLogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M6tc020_DeleteInstitutionFromInstitutionsPageWhoseFolderStructureHasCreated() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		ContactPageBusinessLayer cp=new ContactPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.CommitmentsTab)) {
			if (click(driver, bp.getGoButton(60), "Go Button", action.SCROLLANDBOOLEAN)) {
				WebElement ele = FindElement(driver,
						"(//span[text()='" + M6LimitedPartner1 + "Renamed" + "']/../../../..//a)[3]/span",
						"Commitment ID", action.SCROLLANDTHROWEXCEPTION, 60);
				if (ele != null) {
					if (click(driver, ele, "CommitmentId", action.SCROLLANDBOOLEAN)) {
					if (click(driver, cmp.getDeleteButton(60), "Delete Button", action.SCROLLANDBOOLEAN)) {
							switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
						if(click(driver, cmp.getCommitmentDeletedOKButton(60), "OK button", action.SCROLLANDBOOLEAN)){	
							if (cmp.verifyDeletedCommitmentID(M6commitmentID1)) {
								appLog.info("Commitment get deleted successfully");
								if (bp.clickOnTab(TabName.InstituitonsTab)) {
									if (ip.clickOnCreatedLP(M6LimitedPartner1 + "Renamed")) {
										if (click(driver, bp.getDeleteButton(60), "Delete Button",
												action.SCROLLANDBOOLEAN)) {
											switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
											if (ip.verifyDeletedLimitedPartner(M6LimitedPartner1 + "Renamed")) {
												appLog.info("Limited Partner get deleted successfully");
											} else {
												appLog.info("Limited Partner does not get deleted successfully");
												saa.assertTrue(false,
														"Limited Partner does not get deleted successfully");
											}
											if (bp.clickOnTab(TabName.FundsTab)) {
												if (fp.clickOnCreatedFund(M6FundName1)) {
													switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
													scrollDownThroughWebelement(driver,
															fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
															"InvestorWorkspace View.");
													if (click(driver,
															fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60),
															"Manage Investor Icon", action.SCROLLANDBOOLEAN)) {
														switchToDefaultContent(driver);
														switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
														scrollDownThroughWebelement(driver,
																fp.getWorkspaceSectionView(Workspace.InvestorWorkspace,
																		60),
																"InvestorWorkspace View.");
														if (fp.verifyLPStructureInManageInvestor(M6Institution1,
																M6LimitedPartner1 + "Update")) {
															appLog.info(
																	"Institution 1 is displaying and Limited Partner 1 is Displaying");
															saa.assertTrue(false,
																	"Institution 1 is displaying and Limited Partner 1 is Displaying");
														} else {
															appLog.info(
																	"Institution 1 is not displaying and Limited Partner 1 is not Displaying");
														}
													} else {
														appLog.info("Not able to click on manage invetsor icon");
														saa.assertTrue(false,
																"Not able to click on manage investor icon");
													}
												} else {
													appLog.info("Not able to click on created funds");
													saa.assertTrue(false, "Not able to click on created funds");
												}
											} else {
												appLog.info("Not able to click on funds tab");
												saa.assertTrue(false, "Not able to click on funds tab");
											}
								switchToDefaultContent(driver);
								if(bp.clickOnTab(TabName.ContactTab)){
									if(cp.clickOnCreatedContact(M6Contact1FirstName, M6Contact1LastName, null)){
										switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
										scrollDownThroughWebelement(driver,
												fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
												"InvestorWorkspace View.");	
										if(fp.verifyFolderPathdummy("", null, M6LimitedPartner1+"Renamed", M6FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 60)){
											appLog.info("Limited partner is displaying in folder structure");
									}else{
											appLog.info("Limited partner is not displaying in folder structure");
											saa.assertTrue(false, "Limited Partner is not displaying in folder structure");
										}									
									}else{
										appLog.info("Not able to click on craeted contact");
										saa.assertTrue(false, "Not able to click on created contact");
									}						
									
								}else{
									appLog.info("Not able to click on contact tab");
									saa.assertTrue(false, "Not able to click on contact tab");
								}							
									} else {
											appLog.info("Not able to click on delete button");
											saa.assertTrue(false, "Not able to click on delete button");
										}
									} else {
										appLog.info("Not able to click on created Limited Partner");
										saa.assertTrue(false, "Not able to click on created Limited Partner");
									}
								} else {
									appLog.info("Not able to click on Institution tab");
									saa.assertTrue(false, "Not able to click on Institution tab");
								}
							} else {
								appLog.info("Commitment not get deleted successfully");
								saa.assertTrue(false, "Commitment not get deleted successfully");
							}
						}else{
							appLog.info("Not able to click on OK Button");
							saa.assertTrue(false, "Not able to click on OK Button");
						}
						} else {
							appLog.info("Not able to click on Delete Button");
							saa.assertTrue(false, "Not able to click on Delete Button");
						}
					} else {
						appLog.info("Not able to click on commitment ID");
						saa.assertTrue(false, "Not able to click on commitment ID");
					}
				} else {
					appLog.info("Not able to find commitmentID");
					saa.assertTrue(false, "Not able to find commitmentID");
				}
			} else {
				appLog.info("Not able to click on go Button");
				saa.assertTrue(false, "Not able to click on go button");
			}
		} else {
			appLog.info("Not able to click on Commitment Tab");
			saa.assertTrue(false, "Not able to click on Commitment tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M6tc021_DeleteInstitutionFromInstitutionsPageWhoseFolderStructureHasNotBeenCreated(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.CommitmentsTab)) {
			if (click(driver, bp.getGoButton(60), "Go Button", action.SCROLLANDBOOLEAN)) {
				WebElement ele = FindElement(driver,
						"(//span[text()='" + M6LimitedPartner3 + "']/../../../..//a)[3]/span",
						"Commitment ID", action.SCROLLANDTHROWEXCEPTION, 60);
				if (ele != null) {
					if (click(driver, ele, "CommitmentId", action.SCROLLANDBOOLEAN)) {
					if (click(driver, cmp.getDeleteButton(60), "Delete Button", action.SCROLLANDBOOLEAN)) {
							switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
						if(click(driver, cmp.getCommitmentDeletedOKButton(60), "OK button", action.SCROLLANDBOOLEAN)){	
							if (cmp.verifyDeletedCommitmentID(M6commitmentID3)) {
								appLog.info("Commitment get deleted successfully");
								if (bp.clickOnTab(TabName.InstituitonsTab)) {
									if (ip.clickOnCreatedLP(M6LimitedPartner3)) {
										if (click(driver, bp.getDeleteButton(60), "Delete Button",
												action.SCROLLANDBOOLEAN)) {
											switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
											if (ip.verifyDeletedLimitedPartner(M6LimitedPartner3)) {
												appLog.info("Limited Partner get deleted successfully");
											} else {
												appLog.info("Limited Partner does not get deleted successfully");
												saa.assertTrue(false,
														"Limited Partner does not get deleted successfully");
											}
											if (bp.clickOnTab(TabName.FundsTab)) {
												if (fp.clickOnCreatedFund(M6FundName1)) {
													switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
													scrollDownThroughWebelement(driver,
															fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
															"InvestorWorkspace View.");
													if (click(driver,
															fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60),
															"Manage Investor Icon", action.SCROLLANDBOOLEAN)) {
														switchToDefaultContent(driver);
														switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
														scrollDownThroughWebelement(driver,
																fp.getWorkspaceSectionView(Workspace.InvestorWorkspace,
																		60),
																"InvestorWorkspace View.");
														if (fp.verifyLPStructureInManageInvestor(M6Institution3,
																M6LimitedPartner3)) {
															appLog.info(
																	"Limited Partner 3 is Displaying");
															saa.assertTrue(false,
																	"Limited Partner 3 is Displaying");
														} else {
															appLog.info(
																	"Limited Partner 3 is not Displaying");
														}
													} else {
														appLog.info("Not able to click on manage invetsor icon");
														saa.assertTrue(false,
																"Not able to click on manage investor icon");
													}
												} else {
													appLog.info("Not able to click on created funds");
													saa.assertTrue(false, "Not able to click on created funds");
												}
											} else {
												appLog.info("Not able to click on funds tab");
												saa.assertTrue(false, "Not able to click on funds tab");
											}
										} else {
											appLog.info("Not able to click on delete button");
											saa.assertTrue(false, "Not able to click on delete button");
										}
									} else {
										appLog.info("Not able to click on created Limited Partner");
										saa.assertTrue(false, "Not able to click on created Limited Partner");
									}
								} else {
									appLog.info("Not able to click on Institution tab");
									saa.assertTrue(false, "Not able to click on Institution tab");
								}
							} else {
								appLog.info("Commitment not get deleted successfully");
								saa.assertTrue(false, "Commitment not get deleted successfully");
							}
						}else{
							appLog.info("Not able to click on OK Button");
							saa.assertTrue(false, "Not able to click on OK Button");
						}
						} else {
							appLog.info("Not able to click on Delete Button");
							saa.assertTrue(false, "Not able to click on Delete Button");
						}
					} else {
						appLog.info("Not able to click on commitment ID");
						saa.assertTrue(false, "Not able to click on commitment ID");
					}
				} else {
					appLog.info("Not able to find commitmentID");
					saa.assertTrue(false, "Not able to find commitmentID");
				}
			} else {
				appLog.info("Not able to click on go Button");
				saa.assertTrue(false, "Not able to click on go button");
			}
		} else {
			appLog.info("Not able to click on Commitment Tab");
			saa.assertTrue(false, "Not able to click on Commitment tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}
	
	@Test
	public void M6tc022_AddStandardFolderFromManageFoldersAndThenAddInstitutionFromManageInvestors(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M6FundName1)) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
						"InvestorWorkspace View.");
				if (click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 60), "Manage Approval Icon",
						action.SCROLLANDBOOLEAN)) {
					if (bp.createFolderStructure("st", FolderType.Standard, Workspace.InvestorWorkspace,
							PageName.ManageFolderPopUp, 60).isEmpty()) {
						ThreadSleep(2000);
						if (click(driver, fp.getManageFolderCloseButton(Workspace.InvestorWorkspace, 60), "Manage Folder Close Button",
								action.SCROLLANDBOOLEAN)) {
							ThreadSleep(2000);
							switchToDefaultContent(driver);
							switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
							scrollDownThroughWebelement(driver,
									fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
									"InvestorWorkspace View.");
								if (fp.verifyFolderPathdummy("st", M6Institution2, M6LimitedPartner2, M6FundName1, PageName.FundsPage,
									Workspace.InvestorWorkspace, 20)) {
								appLog.info("Institution 2 folder is displaying with st folder in folder Structure");
							} else {
								appLog.info(
										"Institution 2 folder is not displaying with st folder in folder Structure");
								saa.assertTrue(false,
										"Institution 2 folder is not displaying with st folder in folder Structure");
							}
						} else {
							appLog.info("Not able to click on manage folder close button");
							saa.assertTrue(false, "Not able to click on manage folder close button");
						}
					} else {
						appLog.info("Not able to create standard folder");
						saa.assertTrue(false, "Not able to create standard folder");
					}

				} else {
					appLog.info("Not able to click on manage approval icon");
					saa.assertTrue(false, "Not able to click on amnage approval icon");
				}
			} else {
				appLog.info("Not able to click on created fund");
				saa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on funds tab");
			saa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
//		driver.close();
//		config(ExcelUtils.readDataFromPropertyFile("Browser"));
//		lp = new LoginPageBusinessLayer(driver);
//		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
//		bp = new BasePageBusinessLayer(driver);
//		saa=new SoftAssert();
//		lp.CRMLogin(superAdminUserName, adminPassword);
//		if (bp.clickOnTab(TabName.NIMTab)) {
//			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
//			if(nim.removeAllUserAccess()){
//				appLog.info("Remove all user access successfully");
//			}else{
//				appLog.info("Not able to remove all user access");
//				saa.assertTrue(false, "Not able to use all user access");
//			}
//			if(nim.deactivateManageApprovalsSetting()){
//				appLog.info("Deactivate manage Approval Setting successfully");
//			}else{
//				appLog.info("Manage Approval setting is not deactivated successfully");		
//				saa.assertTrue(false, "Manage Approval setting is not deactivated successfully");
//			}
//			if(nim.deactivateWatermarkingSetting()){
//				appLog.info("Deactivate watermarking Setting successfully");
//			}else{
//				appLog.info("watermarking setting is not deactivated successfully");		
//				saa.assertTrue(false, "watermarking setting is not deactivated successfully");
//			}	
//			switchToDefaultContent(driver);
//		}else{
//			appLog.info("Not able to click on NIM Tab");
//			saa.assertTrue(false, "Not able to click on NIM Tab");
//		}
//		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M6tc023_CreatePreconditionData() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		PartnershipPageBusinessLayer pp = new PartnershipPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(superAdminUserName, adminPassword);
		if (click(driver, bp.getUserMenuTab(60), "User Menu Button", action.SCROLLANDBOOLEAN)) {
			if (click(driver, bp.getUserMenuSetupLink(60), "Setup link", action.SCROLLANDBOOLEAN)) {
				if (click(driver, bp.getCustomizeIcon(60), "Customize icon", action.SCROLLANDBOOLEAN)) {
					if (click(driver, bp.getAccountsIconUnderCustomizeIcon(60), "Accounts Icon under customize icon",
							action.SCROLLANDBOOLEAN)) {
						if (click(driver, bp.getFieldsLabelUnderAccountIcon(60), "Fields Label",
								action.SCROLLANDBOOLEAN)) {
						} else {
							appLog.info("Not able to click on Fields Label");
							saa.assertTrue(false, "Not able to click on fileds label");
						}
					} else {
						appLog.info("Not able to click on Accounts tab");
						saa.assertTrue(false, "Not able to click on Accounts tab");
					}
				} else {
					appLog.info("Not able to click on Customize icon");
					saa.assertTrue(false, "Not able to click on Customize icon");
				}
			} else {
				appLog.info("Not able to click on setup link");
				saa.assertTrue(false, "Not able to click on setup link");
			}
		} else {
			appLog.info("Not able to click on User menu button");
			saa.assertTrue(false, "Not able to click on User menu button");
		}						
		for (int i = 1; i < 7; i++) {
			String accountDatatpe = ExcelUtils.readData(filterPath,"CustomLabels", i, 1);
			String fieldLabel = ExcelUtils.readData(filterPath,"CustomLabels", i, 2);
			String startingNumber = ExcelUtils.readData(filterPath,"CustomLabels", i, 4);
			String decimalValues = ExcelUtils.readData(filterPath,"CustomLabels", i, 7);
			System.err.println(accountDatatpe);
			if (accountDatatpe != null && fieldLabel != null) {
				if (ip.addCustomFieldInAccount(accountDatatpe, fieldLabel, startingNumber, decimalValues,50)) {
					appLog.info("Field added successfully");
				} else {
					saa.assertTrue(false, "Field not added successfully");
				}
			}
		}
		if (click(driver, bp.getUserMenuTab(60), "User Menu Button", action.SCROLLANDBOOLEAN)) {
			if (click(driver, bp.getUserMenuSetupLink(60), "Setup link", action.SCROLLANDBOOLEAN)) {
				if (click(driver, bp.getCreateIcon(60), "Create Icon", action.SCROLLANDBOOLEAN)) {
					if (click(driver, bp.getObjectsLabel(60), "Objects Label", action.SCROLLANDBOOLEAN)) {
						if (click(driver, bp.getFundraisingLabelInObject(60), "FundRaising Label",
								action.SCROLLANDBOOLEAN)) {
							
						} else {
							appLog.info("Not able to click on FundRaising LAbel");
							saa.assertTrue(false, "Not able to click on FundRaising LAbel");
						}
					} else {
						appLog.info("Not able to click on Objects LAbel");
						saa.assertTrue(false, "Not able to click on Objects LAbel");
					}
				} else {
					appLog.info("Not able to click on create icon");
					saa.assertTrue(false,"Not able to click on create icon");
				}
			} else {
				appLog.info("Not able to click on setup link");
				saa.assertTrue(false,"Not able to click on setup link");
			}
		} else {
			appLog.info("Not able to click on User menu button");
			saa.assertTrue(false,"Not able to click on User menu button");
		}
		for (int i = 8; i < 20; i++) {
			String accountDatatpe = ExcelUtils.readData(filterPath,"CustomLabels", i, 1);
			String fieldLabel = ExcelUtils.readData(filterPath,"CustomLabels", i, 2);
			String startingNumber = ExcelUtils.readData(filterPath,"CustomLabels", i, 4);
			String decimalValues = ExcelUtils.readData(filterPath,"CustomLabels", i, 7);
			String Value = ExcelUtils.readData(filterPath,"CustomLabels", i, 3);
			String formulaReturnType = ExcelUtils.readData(filterPath,"CustomLabels", i, 8);
			String SimpleFormulaValue = ExcelUtils.readData(filterPath,"CustomLabels", i, 9);
			System.err.println(accountDatatpe);
			if (accountDatatpe != null && fieldLabel != null) {
				if (frp.addCustomFieldInFundRaising(accountDatatpe, fieldLabel, startingNumber, decimalValues, Value,
						formulaReturnType, SimpleFormulaValue,60)) {
					appLog.info("Field added successfully");
				} else {
					saa.assertTrue(false, "Field not added successfully");
				}
			} else {
				appLog.info("Value is null");
				saa.assertTrue(false, "Value is null");
			}
		}
		if (click(driver, bp.getUserMenuTab(60), "User Menu Button", action.SCROLLANDBOOLEAN)) {
			if (click(driver, bp.getUserMenuSetupLink(60), "Setup link", action.SCROLLANDBOOLEAN)) {
				if (click(driver, bp.getCreateIcon(60), "Create Icon", action.SCROLLANDBOOLEAN)) {
					if (click(driver, bp.getObjectsLabel(60), "Objects Label", action.SCROLLANDBOOLEAN)) {
						if (click(driver, bp.getCommitmentLabelInObject(60), "Commitment Label",
								action.SCROLLANDBOOLEAN)) {							
						} else {
							appLog.info("Not able to click on Commitment LAbel");
							saa.assertTrue(false, "Not able to click on Commitment LAbel");
						}
					} else {
						appLog.info("Not able to click on Objects LAbel");
						saa.assertTrue(false, "Not able to click on Objects LAbel");
					}
				} else {
					appLog.info("Not able to click on create icon");
					saa.assertTrue(false, "Not able to click on create icon");
				}
			} else {
				appLog.info("Not able to click on setup link");
				saa.assertTrue(false, "Not able to click on setup link");
			}
		} else {
			appLog.info("Not able to click on User menu button");
			saa.assertTrue(false, "Not able to click on User menu button");
		}
		
		for (int i = 21; i < 30; i++) {
			String accountDatatpe = ExcelUtils.readData(filterPath,"CustomLabels", i, 1);
			String fieldLabel = ExcelUtils.readData(filterPath,"CustomLabels", i, 2);
			String decimalValues = ExcelUtils.readData(filterPath,"CustomLabels", i, 7);
			String Value = ExcelUtils.readData(filterPath,"CustomLabels", i, 3);
			String Limit = ExcelUtils.readData(filterPath,"CustomLabels", i, 10);
			if (accountDatatpe != null && fieldLabel != null) {
				if (cmp.addCustomFieldInCommitment(accountDatatpe, fieldLabel, decimalValues, Value, Limit,60)) {
					appLog.info("Field added successfully");
				} else {
					saa.assertTrue(false, "Field not added successfully");
				}
			} else {
				appLog.info("Value is null");
				saa.assertTrue(false, "Value is null");
			}
		}
		for (int i = 1; i < 5; i++) {
			if (bp.clickOnTab(TabName.InstituitonsTab)) {
				String institutionName = ExcelUtils.readData(filterPath,"Institutions", i, 0);
				String TestDateA = ExcelUtils.readData(filterPath,"Institutions", i, 2);
				String TestDatetimeA = ExcelUtils.readData(filterPath,"Institutions", i, 3);
				String TestEmailA = ExcelUtils.readData(filterPath,"Institutions", i, 4);
				String CurrentAllocationPrivate = ExcelUtils.readData(filterPath,"Institutions", i, 5);
				String TestGeolocationALatitude = ExcelUtils.readData(filterPath,"Institutions", i, 6);
				String TestGeolocationALongitude = ExcelUtils.readData(filterPath,"Institutions", i, 7);
				String EmployeesNumber = ExcelUtils.readData(filterPath,"Institutions", i, 8);
				String AlternativePercent = ExcelUtils.readData(filterPath,"Institutions", i, 9);
				String Phone = ExcelUtils.readData(filterPath,"Institutions", i, 10);
				String OwnershipPicklist = ExcelUtils.readData(filterPath,"Institutions", i, 11);
				String FundPreferencesPicklistMultiSelect = ExcelUtils.readData(filterPath,"Institutions", i, 12);
				String KeywordsText = ExcelUtils.readData(filterPath,"Institutions", i, 13);
				String ReferralSourceDistributon = ExcelUtils.readData(filterPath,"Institutions", i, 14);
				String TestTimeBetaA = ExcelUtils.readData(filterPath,"Institutions", i, 15);
				String Website = ExcelUtils.readData(filterPath,"Institutions", i, 16);
				String InvestInFirstTimeFunds = ExcelUtils.readData(filterPath,"Institutions", i, 17);
				if (institutionName != null) {
					if (ip.createInstitutionForFilter(institutionName, TestDateA, TestDatetimeA, TestEmailA,
							CurrentAllocationPrivate, TestGeolocationALatitude, TestGeolocationALongitude,
							EmployeesNumber, AlternativePercent, Phone, OwnershipPicklist,
							FundPreferencesPicklistMultiSelect, KeywordsText, ReferralSourceDistributon, TestTimeBetaA,
							Website, InvestInFirstTimeFunds)) {
						appLog.info("Institution created successfully" + institutionName);
					} else {
						appLog.info("Instituition not created successfully");
						saa.assertTrue(false, "Instituition not created successfully");
					}
				} else {
					appLog.info("Value is null so we cannot create institution");
					saa.assertTrue(false, "Value is null so we cannot create institution");
				}
			} else {
				appLog.info("Not able to click on institution tab so we cannot create institution");
				saa.assertTrue(false, "Not able to click on institution tab so we cannot create institution");
			}
		}
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.createFund(ExcelUtils.readData(filterPath,"Funds", 1, 0), ExcelUtils.readData(filterPath,"Funds", 1, 1),
					ExcelUtils.readData(filterPath,"Funds", 1, 2))) {
				appLog.info("Fund Cretaed successfully");
			} else {
				appLog.info("Not able to crete fund");
				saa.assertTrue(false, "Not able to create fund");
			}
		} else {
			appLog.info("Not able to click on funds tab");
			saa.assertTrue(false, "Not able to click on funds tab");
		}
		for (int i = 1; i < 5; i++) {
			if (bp.clickOnTab(TabName.FundraisingsTab)) {
				String fundraisingName = ExcelUtils.readData(filterPath,"Fundraisings", i, 0);
				String fundName = ExcelUtils.readData(filterPath,"Fundraisings", i, 1);
				String legalName = ExcelUtils.readData(filterPath,"Fundraisings", i, 2);
				String targetCloseDate = ExcelUtils.readData(filterPath,"Fundraisings", i, 4);
				String TestDatetime = ExcelUtils.readData(filterPath,"Fundraisings", i, 5);
				String testEmail = ExcelUtils.readData(filterPath,"Fundraisings", i, 6);
				String InvestmentHighAmount = ExcelUtils.readData(filterPath,"Fundraisings", i, 7);
				String testGeolocationLatiitude = ExcelUtils.readData(filterPath,"Fundraisings", i, 8);
				String testGeoLocationLongitude = ExcelUtils.readData(filterPath,"Fundraisings", i, 9);
				String testnumber = ExcelUtils.readData(filterPath,"Fundraisings", i, 10);
				String potentialManageMentFee = ExcelUtils.readData(filterPath,"Fundraisings", i, 11);
				String phone = ExcelUtils.readData(filterPath,"Fundraisings", i, 12);
				String stageDropdownVlaue = ExcelUtils.readData(filterPath,"Fundraisings", i, 13);
				String reasonForDeclinedValue = ExcelUtils.readData(filterPath,"Fundraisings", i, 14);
				String multiselectValue = ExcelUtils.readData(filterPath,"Fundraisings", i, 15);
				String nextStep = ExcelUtils.readData(filterPath,"Fundraisings", i, 16);
				String testTextArea = ExcelUtils.readData(filterPath,"Fundraisings", i, 17);
				String testTimeBeta = ExcelUtils.readData(filterPath,"Fundraisings", i, 18);
				String testUrl = ExcelUtils.readData(filterPath,"Fundraisings", i, 19);
				String TestCheckbox = ExcelUtils.readData(filterPath,"Fundraisings", i, 3);
				if (fundraisingName != null) {
					if (frp.createFundRaisingWithFilterData(fundraisingName, fundName, legalName, TestCheckbox,
							targetCloseDate, TestDatetime, testEmail, InvestmentHighAmount, testGeolocationLatiitude,
							testGeoLocationLongitude, testnumber, potentialManageMentFee, phone, stageDropdownVlaue,
							reasonForDeclinedValue, multiselectValue, nextStep, testTextArea, testTimeBeta, testUrl)) {
						appLog.info("fundraising created successfully" + fundraisingName);
					} else {
						appLog.info("fundraising not created successfully");
						saa.assertTrue(false, "fundraising not created successfully");
					}
				} else {
					appLog.info("Value is null so we cannot create fundraising");
					saa.assertTrue(false, "Value is null so we cannot create fundraising");
				}
			} else {
				appLog.info("Not able to click on fundraising tab");
				saa.assertTrue(false, "Not able to click on fundraising tab");
			}
		}
		for (int i = 1; i < 5; i++) {
			if (bp.clickOnTab(TabName.InstituitonsTab)) {
				String lp_name = ExcelUtils.readData(filterPath,"Limited Partner", i, 0);
				String inst_name = ExcelUtils.readData(filterPath,"Limited Partner", i, 1);
				if (ip.createLimitedPartner(lp_name, inst_name)) {
					appLog.info("Limited Partner Created successfully");
				} else {
					appLog.info("Limited Partner not craeted successfully");
					saa.assertTrue(false, "Not able to create limited partner");
				}
			} else {
				appLog.info("Not able to click on institution tab so cannot create limited Partner");
				saa.assertTrue(false, "Not able to click on institution tab so cannot create limited Partner");
			}
		}
		if (bp.clickOnTab(TabName.PartnershipsTab)) {
			if (pp.createPartnership(ExcelUtils.readData(filterPath,"Partnerships", 1, 0),
					ExcelUtils.readData(filterPath,"Partnerships", 1, 1))) {
				appLog.info("Partnership Created successfully");
			} else {
				appLog.info("Partnership not craeted successfully");
				saa.assertTrue(false, "Not able to create Partnership");
			}
		} else {
			appLog.info("Not able to click on Partnership tab so cannot create Partnership");
			saa.assertTrue(false, "Not able to click on Partnership tab so cannot create Partnership");
		}
		for (int i = 1; i < 5; i++) {
			if (bp.clickOnTab(TabName.CommitmentsTab)) {
				String LimitedPartner = ExcelUtils.readData(filterPath,"Commitments", i, 0);
				String Partnership = ExcelUtils.readData(filterPath,"Commitments", i, 1);
				String AntiMoneyCheckbox = ExcelUtils.readData(filterPath,"Commitments", i, 2);
				String finalCommitmentDate = ExcelUtils.readData(filterPath,"Commitments", i, 3);
				String testDatetime = ExcelUtils.readData(filterPath,"Commitments", i, 4);
				String email = ExcelUtils.readData(filterPath,"Commitments", i, 5);
				String commitmentAmount = ExcelUtils.readData(filterPath,"Commitments", i, 6);
				String geolocationLatitude = ExcelUtils.readData(filterPath,"Commitments", i, 7);
				String GeolocationLongitude = ExcelUtils.readData(filterPath,"Commitments", i, 8);
				String number = ExcelUtils.readData(filterPath,"Commitments", i, 9);
				String CarriedInterestPercent = ExcelUtils.readData(filterPath,"Commitments", i, 10);
				String phone = ExcelUtils.readData(filterPath,"Commitments", i, 11);
				String partnerType = ExcelUtils.readData(filterPath,"Commitments", i, 12);
				String multiPickList = ExcelUtils.readData(filterPath,"Commitments", i, 13);
				String testText = ExcelUtils.readData(filterPath,"Commitments", i, 14);
				String subdocDesc = ExcelUtils.readData(filterPath,"Commitments", i, 15);
				String testTimeBeta = ExcelUtils.readData(filterPath,"Commitments", i, 16);
				String testUrl = ExcelUtils.readData(filterPath,"Commitments", i, 17);
				String basedOnValue = ExcelUtils.readData(filterPath,"Commitments", i, 18);
				if (LimitedPartner != null) {
					if (cmp.createCommitmentInFilterData(LimitedPartner, Partnership, basedOnValue, AntiMoneyCheckbox,
							finalCommitmentDate, testDatetime, email, commitmentAmount, geolocationLatitude,
							GeolocationLongitude, number, CarriedInterestPercent, phone, partnerType, multiPickList,
							testText, subdocDesc, testTimeBeta, testUrl)) {
						appLog.info("Commitments created successfully");
					} else {
						appLog.info("Commitments not created successfully");
						saa.assertTrue(false, "Commitments not created successfully");
					}
				} else {
					appLog.info("Value is null so we cannot create Commitments");
					saa.assertTrue(false, "Value is null so we cannot create Commitments");
				}
			} else {
				appLog.info("Not able to click on Commitments tab");
				saa.assertTrue(false, "Not able to click on Commitments tab");
			}
		}
		for (int i = 1; i < 3; i++) {
			if (bp.clickOnTab(TabName.ContactTab)) {
				String contactFirstName = ExcelUtils.readData(filterPath,"Contacts", i, 0);
				String contactLastName = ExcelUtils.readData(filterPath,"Contacts", i, 1);
				String legalName = ExcelUtils.readData(filterPath,"Contacts", i, 2);
				String emailID = ExcelUtils.readData(filterPath,"Contacts", i, 3);
				if (cp.createContact(contactFirstName, contactLastName, legalName, emailID)) {
					appLog.info("Contacts Created successfully");
				} else {
					appLog.info("Contacts not craeted successfully");
					saa.assertTrue(false, "Not able to create Contacts");
				}
			} else {
				appLog.info("Not able to click on Contacts tab so cannot create Contacts");
				saa.assertTrue(false, "Not able to click on Contacts tab so cannot create Contacts");
			}
		}
		for (int i = 1; i < 3; i++) {
			if (bp.clickOnTab(TabName.InstituitonsTab)) {
				if (ip.clickOnCreatedInstitution(ExcelUtils.readData(filterPath,"AdvisorInvolvement", 1, 0))) {
					String DilgenceExpenseActual = ExcelUtils.readData(filterPath,"AdvisorInvolvement", i, 2);
					String basedOnValue = ExcelUtils.readData(filterPath,"AdvisorInvolvement", i, 3);
					if (ip.createadvisorInvolvements(ExcelUtils.readData(filterPath,"AdvisorInvolvement", 1, 1),
							DilgenceExpenseActual, basedOnValue)) {
						appLog.info("Advisor Involvement is created successfully");
					} else {
						appLog.info("Advisor Involvement is not created successfully");
						saa.assertTrue(false, "Advisor Involvement is not created successfully");
					}
				} else {
					appLog.info("Not able to click on created institution");
					saa.assertTrue(false, "Not able to click on created institution");
				}
			} else {
				appLog.info("Not able to click on Institutions tab so cannot create Advisor Involvement");
				saa.assertTrue(false, "Not able to click on Institutions tab so cannot create Advisor Involvement");
			}
		}
		for (int i = 1; i < 3; i++) {
			if (bp.clickOnTab(TabName.FundraisingsTab)) {
				if (frp.clickOnCreatedFundRaising(ExcelUtils.readData(filterPath,"FundraisingContact", 1, 0))) {
					String contact = ExcelUtils.readData(filterPath,"FundraisingContact", i, 1);
					if (frp.createFundRaisingContact(contact)) {
						appLog.info("FundRaising Contact created successfully");
					} else {
						appLog.info("FundRaising Contact not created successfully");
						saa.assertTrue(false, "FundRaising Contact not created successfully");
					}
				} else {
					appLog.info("Not able to click on created fundraising");
					saa.assertTrue(false, "Not able to click on created fundraising");
				}
			} else {
				appLog.info("Not able to click on FundRaising tab so cannot create FundRaising Contacts");
				saa.assertTrue(false, "Not able to click on FundRaising tab so cannot create FundRaising Contacts");
			}
		}
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M6tc024_BuildWorkspace() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String[] step1of3 = { ExcelUtils.readData(filterPath,"Funds", 1, 3), ExcelUtils.readData(filterPath,"Funds", 1, 4),
				ExcelUtils.readData(filterPath,"Funds", 1, 5), ExcelUtils.readData(filterPath,"Funds", 1, 6),
				ExcelUtils.readData(filterPath,"Funds", 1, 7), ExcelUtils.readData(filterPath,"Funds", 1, 8) };
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(ExcelUtils.readData(filterPath,"Funds", 1, 0))) {
				if (fp.buildWorkspace(step1of3, WorkSpaceAction.IMPORTFOLDERTEMPLATE, folderTemplateName, null, null,
						Workspace.FundraisingWorkspace, 60)) {
					appLog.info("Fundraising Workspace is created successfully");
					if (fp.buildWorkspace(step1of3, WorkSpaceAction.IMPORTFOLDERTEMPLATE, folderTemplateName, null,
							null, Workspace.InvestorWorkspace, 60)) {
						appLog.info("Investor Workspace is created successfully");
					} else {
						appLog.info("Investor workspace is not created successfully");
						saa.assertTrue(false, " Investor workspace is not craeted successfully");
					}
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
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M6tc025_CheckUIOfManageInvestorPopup() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		List<String> filterHeaderList = new ArrayList<String>();
		filterHeaderList.add("Field");
		filterHeaderList.add("Operator");
		filterHeaderList.add("Value");
		filterHeaderList.add("");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(ExcelUtils.readData(filterPath,"Funds", 1, 0))) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
						"FundraisingWorkspace View.");
				if (click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60), "Manage Investor Icon",
						action.SCROLLANDBOOLEAN)) {
					if (fp.getManageInvestorFilterExpandedIcon(Workspace.FundraisingWorkspace, 60) != null) {
						appLog.info("Filter is displaying in expanded form");
					} else {
						appLog.info("Filter is not displaying in expanded form");
						saa.assertTrue(false, "Filter is not displaying in expanded form");
					}
					List<WebElement> headerList = fp.getManageInvestorFilterHeaderList(Workspace.FundraisingWorkspace);
					for (int i = 0; i < headerList.size(); i++) {
						String headertext = headerList.get(i).getText().trim();
						if (compareMultipleListWithoutAssertion(headertext, filterHeaderList)) {
							appLog.info("Filter header is verified");
						} else {
							appLog.info("Filter header is not verified");
							saa.assertTrue(false, "Filter header is not verified:" + headertext);
						}
					}
					if (fp.getManageInvestorFilterFieldDropdown(Workspace.FundraisingWorkspace) != null) {
						appLog.info("Field dropdown is displaying");
					} else {
						appLog.info("Field dropdown is not displaying");
						saa.assertTrue(false, "Field dropdown is not displaying");
					}
					if (fp.getManageInvestorFilterOperatorDropdown(Workspace.FundraisingWorkspace) != null) {
						appLog.info("Operator dropdown is displaying");
					} else {
						appLog.info("Operator dropdown is not displaying");
						saa.assertTrue(false, "Operator dropdown is not displaying");
					}

					if (fp.getManageInvestorFilterValueTextBox(Workspace.FundraisingWorkspace) != null) {
						appLog.info("Value TextBox is displaying");
					} else {
						appLog.info("Value TextBox is not displaying");
						saa.assertTrue(false, "Value TextBox is not displaying");
					}
					if (click(driver, fp.getManageInvestorFilterExpandedIcon(Workspace.FundraisingWorkspace, 60),
							"Expanded Icon", action.SCROLLANDBOOLEAN)) {
						if (fp.getManageInvestorFilterCollapsedIcon(Workspace.FundraisingWorkspace, 60) != null) {
							appLog.info("Filter section get collapsed");
							if (fp.getManageInvestorFilterCollapsedHeader(Workspace.FundraisingWorkspace, 60) != null) {
								appLog.info("Filter Invetsor header tab is displaying");
							} else {
								appLog.info("Filter investor header is not displaying");
								saa.assertTrue(false, "Filter investor header is not displaying");
							}
						} else {
							appLog.info("Filter section is not get cpollapsed");
							saa.assertTrue(false, "Filter section does not get collapsed");
						}
					} else {
						appLog.info("Not able to click on manage Investor Filter Expanded Icon");
						saa.assertTrue(false, "Not able to click on manage Investor Filter Expanded Icon");
					}
				} else {
					appLog.info("Not able to click on manage investor icon");
					saa.assertTrue(false, "Not able to click on manage investor button");
				}
			} else {
				appLog.info("Not able to click on created fund");
				saa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on Funds tab");
			saa.assertTrue(false, "Not able to click on Funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M6tc026_CheckFunctionalityOfAddRowButton() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(ExcelUtils.readData(filterPath,"Funds", 1, 0))) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
						"FundraisingWorkspace View.");
				if (click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60), "Manage Investor Icon",
						action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getManageInvestorFilterAddRowLink(Workspace.FundraisingWorkspace, 60),
							"Add Row Link", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on add row link successfully");
						int filterRow = fp.getManageInvestorFilterRows(Workspace.FundraisingWorkspace).size();
						if (filterRow == 2) {
							appLog.info("One other row is added successfully after clicking on add row link");
							if (fp.getManageInvestorFilterOperatorDropdown(Workspace.FundraisingWorkspace)
									.size() == 2) {
								appLog.info("2 operator dropdown is displaying");
							} else {
								appLog.info("2 operator Dropdown is not displaying");
								saa.assertTrue(false, "2 operator Dropdown is not displaying");
							}
							if (fp.getManageInvestorFilterFieldDropdown(Workspace.FundraisingWorkspace).size() == 2) {
								appLog.info("2 Field dropdown is displaying");
							} else {
								appLog.info("2 Field Dropdown is not displaying");
								saa.assertTrue(false, "2 Field Dropdown is not displaying");
							}
							if (fp.getManageInvestorFilterValueTextBox(Workspace.FundraisingWorkspace).size() == 2) {
								appLog.info("2 Value textbox is displaying");
							} else {
								appLog.info("2 Value textbox is not displaying");
								saa.assertTrue(false, "2 Value textbox is not displaying");
							}
						} else {
							appLog.info("One other row is not added successfully after clicking on add row link");
							saa.assertTrue(false,
									"One other row is not added successfully after clicking on add row link");
						}
					} else {
						appLog.info("Not able to click on add row link");
						saa.assertTrue(false, "Not able to click on add row link");
					}
					for (int i = 1; i < 9; i++) {
						if (click(driver, fp.getManageInvestorFilterAddRowLink(Workspace.FundraisingWorkspace, 60),
								"Add Row Link", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on add row link successfully");
						} else {
							appLog.info("Not able to click on add row link");
							saa.assertTrue(false, "Not able to click on add row link");
						}
					}
					int filterRow = fp.getManageInvestorFilterRows(Workspace.FundraisingWorkspace).size();
					if (filterRow == 10) {
						appLog.info("10 rows are added successfully after clicking on add row link");
						if (fp.getManageInvestorFilterOperatorDropdown(Workspace.FundraisingWorkspace).size() == 10) {
							appLog.info("10 operator dropdown is displaying");
						} else {
							appLog.info("10 operator Dropdown is not displaying");
							saa.assertTrue(false, "10 operator Dropdown is not displaying");
						}
						if (fp.getManageInvestorFilterFieldDropdown(Workspace.FundraisingWorkspace).size() == 10) {
							appLog.info("10 Field dropdown is displaying");
						} else {
							appLog.info("10 Field Dropdown is not displaying");
							saa.assertTrue(false, "10 Field Dropdown is not displaying");
						}
						if (fp.getManageInvestorFilterValueTextBox(Workspace.FundraisingWorkspace).size() == 10) {
							appLog.info("10 Value textbox is displaying");
						} else {
							appLog.info("10 Value textbox is not displaying");
							saa.assertTrue(false, "10 Value textbox is not displaying");
						}
					} else {
						appLog.info("10 rows are not added successfully after clicking on add row link");
						saa.assertTrue(false, "10 rows are not added successfully after clicking on add row link");
					}
					switchToDefaultContent(driver);
					switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
							"FundraisingWorkspace View.");
					ThreadSleep(4000);
					String addRow = fp.getManageInvestorFilterAddRowLink(Workspace.FundraisingWorkspace, 60)
							.getAttribute("class");
					if (addRow.equalsIgnoreCase("add deactive")) {
						appLog.info("Add row link is not enabled");
					} else {
						appLog.info("Add row link is enabled");
						saa.assertTrue(false, "Add row link is enabled");
					}
					for (int i = 0; i < 2; i++) {
						if (click(driver,
								fp.getManageInvestorFilterRemoveRowIcon(Workspace.FundraisingWorkspace).get(i),
								"Remove Row icon", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on remove row icon");
						} else {
							appLog.info("Not able to click on remove row icon");
							saa.assertTrue(false, "Not able to click on remove row icon");
						}
					}
					filterRow = fp.getManageInvestorFilterRows(Workspace.FundraisingWorkspace).size();
					if (filterRow == 8) {
						appLog.info("2 rows get deleted successfully");
					} else {
						appLog.info("2 rows not get deleted successfully");
						saa.assertTrue(false, "2 rows not get deleted successfully");
					}
					if (isEnabled(driver, fp.getManageInvestorFilterAddRowLink(Workspace.FundraisingWorkspace, 60),
							"Add Row Link")) {
						appLog.info("Add row link is enabled");
					} else {
						appLog.info("Add row link is not enabled");
						saa.assertTrue(false, "Add row link is not enabled");
					}
				} else {
					appLog.info("Not able to click on manage investor icon");
					saa.assertTrue(false, "Not able to click on manage investor button");
				}
			} else {
				appLog.info("Not able to click on created fund");
				saa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on Funds tab");
			saa.assertTrue(false, "Not able to click on Funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M6tc027_CheckFunctionalityOfClearButton() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(ExcelUtils.readData(filterPath,"Funds", 1, 0))) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
						"FundraisingWorkspace View.");
				if (click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60), "Manage Investor Icon",
						action.SCROLLANDBOOLEAN)) {

					if (selectVisibleTextFromDropDown(driver,
							fp.getManageInvestorFilterFieldDropdown(Workspace.FundraisingWorkspace).get(0),
							"Field Dropdown", "Account:TestAutoNumberA")) {
						appLog.info("Selected field dropdown value");
						if (selectVisibleTextFromDropDown(driver,
								fp.getManageInvestorFilterOperatorDropdown(Workspace.FundraisingWorkspace).get(0),
								"Operator Dropdown", "equals")) {
							appLog.info("Selected Operator dropdown value");
							if (sendKeys(driver,
									fp.getManageInvestorFilterValueTextBox(Workspace.FundraisingWorkspace).get(0),
									"abcd1234", "Value text box", action.SCROLLANDBOOLEAN)) {
								if (click(driver,
										fp.getManageInvestorFilterAddRowLink(Workspace.FundraisingWorkspace, 60),
										"Add row link", action.SCROLLANDBOOLEAN)) {
									if (selectVisibleTextFromDropDown(driver,
											fp.getManageInvestorFilterFieldDropdown(Workspace.FundraisingWorkspace)
													.get(1),
											"Field Dropdown", "Fundraising:TestURLF")) {
										appLog.info("Selected field dropdown value");
										if (selectVisibleTextFromDropDown(driver,
												fp.getManageInvestorFilterOperatorDropdown(
														Workspace.FundraisingWorkspace).get(1),
												"Operator Dropdown", "equals")) {
											appLog.info("Selected Operator dropdown value");
											if (sendKeys(driver,
													fp.getManageInvestorFilterValueTextBox(
															Workspace.FundraisingWorkspace).get(1),
													"www.", "Value text box", action.SCROLLANDBOOLEAN)) {
												if (click(driver,
														fp.getManageInvestorFilterClearButton(
																Workspace.FundraisingWorkspace, 60),
														"Clear button", action.SCROLLANDBOOLEAN)) {
													ThreadSleep(5000);
													if (fp.getManageInvestorFilterOperatorDropdown(
															Workspace.FundraisingWorkspace).size() == 2) {
														appLog.info("2 operator dropdown is displaying");
														for (int i = 0; i < 2; i++) {
															String selectedValue = getSelectedOptionOfDropDown(driver,
																	fp.getManageInvestorFilterOperatorDropdown(
																			Workspace.FundraisingWorkspace).get(i),
																	"Opearator Dropdown", "text");
															if (selectedValue.equalsIgnoreCase("equals")) {
																appLog.info("Equals value is selected in dropdown");
															} else {
																appLog.info("Equals value is not selected in dropdown");
																saa.assertTrue(false,
																		"Equals value is not selected in dropdown");
															}
														}
													} else {
														appLog.info("2 operator Dropdown is not displaying");
														saa.assertTrue(false, "2 operator Dropdown is not displaying");
													}
													if (fp.getManageInvestorFilterFieldDropdown(
															Workspace.FundraisingWorkspace).size() == 2) {
														appLog.info("2 Field dropdown is displaying");
														for (int i = 0; i < 2; i++) {
															String selectedValue = getSelectedOptionOfDropDown(driver,
																	fp.getManageInvestorFilterFieldDropdown(
																			Workspace.FundraisingWorkspace).get(i),
																	"Field Dropdown", "text");
															if (selectedValue.equalsIgnoreCase("--None--")) {
																appLog.info("None value is selected in dropdown");
															} else {
																appLog.info("None value is not selected in dropdown");
																saa.assertTrue(false,
																		"None value is not selected in dropdown");
															}
														}
													} else {
														appLog.info("2 Field Dropdown is not displaying");
														saa.assertTrue(false, "2 Field Dropdown is not displaying");
													}
													if (fp.getManageInvestorFilterValueTextBox(
															Workspace.FundraisingWorkspace).size() == 2) {
														appLog.info("2 Value textbox is displaying");

													} else {
														appLog.info("2 Value textbox is not displaying");
														saa.assertTrue(false, "2 Value textbox is not displaying");
													}
												} else {
													appLog.info("Not able to click on clear button");
													saa.assertTrue(false, "Not able to click on clear button");
												}
											} else {
												appLog.info("Not able to enter value in value tetxbox");
												saa.assertTrue(false, "Not able to enter value in value tetxbox");
											}
										} else {
											appLog.info("Not able to select Opeartor dropdown value");
											saa.assertTrue(false, "Not able to select Opeartor dropdown value");
										}
									} else {
										appLog.info("Not able to select field dropdown value");
										saa.assertTrue(false, "Not able to select field dropdown value");
									}
								} else {
									appLog.info("Not able to click on add row link");
									saa.assertTrue(false, "Not able to click on add row link");
								}
							} else {
								appLog.info("Not able to enter value in value tetxbox");
								saa.assertTrue(false, "Not able to enter value in value tetxbox");
							}
						} else {
							appLog.info("Not able to select Opeartor dropdown value");
							saa.assertTrue(false, "Not able to select Opeartor dropdown value");
						}
					} else {
						appLog.info("Not able to select field dropdown value");
						saa.assertTrue(false, "Not able to select field dropdown value");
					}
				} else {
					appLog.info("Not able to click on manage investor icon");
					saa.assertTrue(false, "Not able to click on manage investor button");
				}
			} else {
				appLog.info("Not able to click on created fund");
				saa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on Funds tab");
			saa.assertTrue(false, "Not able to click on Funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M6tc028_CheckFilterValidationOnManageInvestorPopUp() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(ExcelUtils.readData(filterPath,"Funds", 1, 0))) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
						"FundraisingWorkspace View.");
				if (click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60), "Manage Investor Icon",
						action.SCROLLANDBOOLEAN)) {
					if (selectVisibleTextFromDropDown(driver,
							fp.getManageInvestorFilterFieldDropdown(Workspace.FundraisingWorkspace).get(0),
							"Field Dropdown", "Account:Employees")) {
						appLog.info("Selected field dropdown value");
						if (selectVisibleTextFromDropDown(driver,
								fp.getManageInvestorFilterOperatorDropdown(Workspace.FundraisingWorkspace).get(0),
								"Operator Dropdown", "equals")) {
							appLog.info("Selected Operator dropdown value");
							if (sendKeys(driver,
									fp.getManageInvestorFilterValueTextBox(Workspace.FundraisingWorkspace).get(0),
									"ABC", "Value text box", action.SCROLLANDBOOLEAN)) {
								if (click(driver,
										fp.getManageInvestorFilterApplyButton(Workspace.FundraisingWorkspace, 60),
										"Apply buton", action.SCROLLANDBOOLEAN)) {
									if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.filterInvalidNumberErroresage,
											fp.getManageInvestorFilterErrorMessage(Workspace.FundraisingWorkspace, 60),
											"Invalid number error Message in filter")) {
										appLog.info("Invalid number Error Message is verified at filter");
									} else {
										saa.assertTrue(false,
												"Invalid number Error Message is not verified at filter.Expected:"
														+ FundsPageErrorMessage.filterInvalidNumberErroresage
														+ " Actual"
														+ getText(driver,
																fp.getManageInvestorFilterErrorMessage(
																		Workspace.FundraisingWorkspace, 60),
																"Invalid number error Message in filter",
																action.BOOLEAN));
									}
								} else {
									appLog.info("Not able to click on apply button");
									saa.assertTrue(false, "Not able to click on apply button");
								}
							} else {
								appLog.info("Not able to enter value in value tetxbox");
								saa.assertTrue(false, "Not able to enter value in value tetxbox");
							}
						} else {
							appLog.info("Not able to select Opeartor dropdown value");
							saa.assertTrue(false, "Not able to select Opeartor dropdown value");
						}
					} else {
						appLog.info("Not able to select field dropdown value");
						saa.assertTrue(false, "Not able to select field dropdown value");
					}
					if (click(driver, fp.getManageInvestorFilterClearButton(Workspace.FundraisingWorkspace, 60),
							"Clear Buton", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on clear button successfully");
					} else {
						appLog.info("Not able to click on clear button");
						saa.assertTrue(false, "Not able to click on clear button");
					}
					if (selectVisibleTextFromDropDown(driver,
							fp.getManageInvestorFilterFieldDropdown(Workspace.FundraisingWorkspace).get(0),
							"Field Dropdown", "Account:Ownership")) {
						appLog.info("Selected field dropdown value");
						if (selectVisibleTextFromDropDown(driver,
								fp.getManageInvestorFilterOperatorDropdown(Workspace.FundraisingWorkspace).get(0),
								"Operator Dropdown", "equals")) {
							appLog.info("Selected Operator dropdown value");
							if (sendKeys(driver,
									fp.getManageInvestorFilterValueTextBox(Workspace.FundraisingWorkspace).get(0),
									"ABC", "Value text box", action.SCROLLANDBOOLEAN)) {
								if (click(driver,
										fp.getManageInvestorFilterApplyButton(Workspace.FundraisingWorkspace, 60),
										"Apply buton", action.SCROLLANDBOOLEAN)) {
									if (bp.verifyErrorMessageOnPage(
											FundsPageErrorMessage.filterPicklistValueNotExistErroresage,
											fp.getManageInvestorFilterErrorMessage(Workspace.FundraisingWorkspace, 60),
											"Picklist value not exist error Message in filter")) {
										appLog.info("Picklist value not exist Error Message is verified at filter");
									} else {
										saa.assertTrue(false,
												"Picklist value not exist is not verified at filter.Expected:"
														+ FundsPageErrorMessage.filterPicklistValueNotExistErroresage
														+ " Actual"
														+ getText(driver,
																fp.getManageInvestorFilterErrorMessage(
																		Workspace.FundraisingWorkspace, 60),
																"Picklist value not exist error Message in filter",
																action.BOOLEAN));
									}
								} else {
									appLog.info("Not able to click on apply button");
									saa.assertTrue(false, "Not able to click on apply button");
								}
							} else {
								appLog.info("Not able to enter value in value tetxbox");
								saa.assertTrue(false, "Not able to enter value in value tetxbox");
							}
						} else {
							appLog.info("Not able to select Opeartor dropdown value");
							saa.assertTrue(false, "Not able to select Opeartor dropdown value");
						}
					} else {
						appLog.info("Not able to select field dropdown value");
						saa.assertTrue(false, "Not able to select field dropdown value");
					}
					if (click(driver, fp.getManageInvestorFilterClearButton(Workspace.FundraisingWorkspace, 60),
							"Clear Buton", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on clear button successfully");
					} else {
						appLog.info("Not able to click on clear button");
						saa.assertTrue(false, "Not able to click on clear button");
					}

					if (selectVisibleTextFromDropDown(driver,
							fp.getManageInvestorFilterFieldDropdown(Workspace.FundraisingWorkspace).get(0),
							"Field Dropdown", "Account:Total Fund Commitments")) {
						appLog.info("Selected field dropdown value");
						if (selectVisibleTextFromDropDown(driver,
								fp.getManageInvestorFilterOperatorDropdown(Workspace.FundraisingWorkspace).get(0),
								"Operator Dropdown", "equals")) {
							appLog.info("Selected Operator dropdown value");
							if (sendKeys(driver,
									fp.getManageInvestorFilterValueTextBox(Workspace.FundraisingWorkspace).get(0),
									"ABC", "Value text box", action.SCROLLANDBOOLEAN)) {
								if (click(driver,
										fp.getManageInvestorFilterApplyButton(Workspace.FundraisingWorkspace, 60),
										"Apply buton", action.SCROLLANDBOOLEAN)) {
									if (bp.verifyErrorMessageOnPage(
											FundsPageErrorMessage.filterInvalidCurrencyErroresage,
											fp.getManageInvestorFilterErrorMessage(Workspace.FundraisingWorkspace, 60),
											"Invalid Currency error Message in filter")) {
										appLog.info("Invalid Currency Error Message is verified at filter");
									} else {
										saa.assertTrue(false,
												"Invalid Currency error message is not verified at filter.Expected:"
														+ FundsPageErrorMessage.filterInvalidCurrencyErroresage
														+ " Actual"
														+ getText(driver,
																fp.getManageInvestorFilterErrorMessage(
																		Workspace.FundraisingWorkspace, 60),
																"Invalid Currency error Message in filter",
																action.BOOLEAN));
									}
								} else {
									appLog.info("Not able to click on apply button");
									saa.assertTrue(false, "Not able to click on apply button");
								}
							} else {
								appLog.info("Not able to enter value in value tetxbox");
								saa.assertTrue(false, "Not able to enter value in value tetxbox");
							}
						} else {
							appLog.info("Not able to select Opeartor dropdown value");
							saa.assertTrue(false, "Not able to select Opeartor dropdown value");
						}
					} else {
						appLog.info("Not able to select field dropdown value");
						saa.assertTrue(false, "Not able to select field dropdown value");
					}

					if (click(driver, fp.getManageInvestorFilterClearButton(Workspace.FundraisingWorkspace, 60),
							"Clear Buton", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on clear button successfully");
					} else {
						appLog.info("Not able to click on clear button");
						saa.assertTrue(false, "Not able to click on clear button");
					}

					if (selectVisibleTextFromDropDown(driver,
							fp.getManageInvestorFilterFieldDropdown(Workspace.FundraisingWorkspace).get(0),
							"Field Dropdown", "Fundraising:Target Close Date")) {
						appLog.info("Selected field dropdown value");
						if (selectVisibleTextFromDropDown(driver,
								fp.getManageInvestorFilterOperatorDropdown(Workspace.FundraisingWorkspace).get(0),
								"Operator Dropdown", "equals")) {
							appLog.info("Selected Operator dropdown value");
							if (sendKeys(driver,
									fp.getManageInvestorFilterValueTextBox(Workspace.FundraisingWorkspace).get(0),
									"ABC12345", "Value text box", action.SCROLLANDBOOLEAN)) {
								if (click(driver,
										fp.getManageInvestorFilterApplyButton(Workspace.FundraisingWorkspace, 60),
										"Apply buton", action.SCROLLANDBOOLEAN)) {
									if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.filterInvalidDateErroresage,
											fp.getManageInvestorFilterErrorMessage(Workspace.FundraisingWorkspace, 60),
											"Invalid date error Message in filter")) {
										appLog.info("Invalid date Error Message is verified at filter");
									} else {
										saa.assertTrue(false,
												"Invalid date error message is not verified at filter.Expected:"
														+ FundsPageErrorMessage.filterInvalidDateErroresage + " Actual"
														+ getText(driver,
																fp.getManageInvestorFilterErrorMessage(
																		Workspace.FundraisingWorkspace, 60),
																"Invalid date error Message in filter",
																action.BOOLEAN));
									}
								} else {
									appLog.info("Not able to click on apply button");
									saa.assertTrue(false, "Not able to click on apply button");
								}
							} else {
								appLog.info("Not able to enter value in value tetxbox");
								saa.assertTrue(false, "Not able to enter value in value tetxbox");
							}
						} else {
							appLog.info("Not able to select Opeartor dropdown value");
							saa.assertTrue(false, "Not able to select Opeartor dropdown value");
						}
					} else {
						appLog.info("Not able to select field dropdown value");
						saa.assertTrue(false, "Not able to select field dropdown value");
					}
					if (click(driver, fp.getManageInvestorFilterClearButton(Workspace.FundraisingWorkspace, 60),
							"Clear Buton", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on clear button successfully");
					} else {
						appLog.info("Not able to click on clear button");
						saa.assertTrue(false, "Not able to click on clear button");
					}

					if (selectVisibleTextFromDropDown(driver,
							fp.getManageInvestorFilterFieldDropdown(Workspace.FundraisingWorkspace).get(0),
							"Field Dropdown", "Account:Existing LP")) {
						appLog.info("Selected field dropdown value");
						if (selectVisibleTextFromDropDown(driver,
								fp.getManageInvestorFilterOperatorDropdown(Workspace.FundraisingWorkspace).get(0),
								"Operator Dropdown", "equals")) {
							appLog.info("Selected Operator dropdown value");
							if (sendKeys(driver,
									fp.getManageInvestorFilterValueTextBox(Workspace.FundraisingWorkspace).get(0),
									"test", "Value text box", action.SCROLLANDBOOLEAN)) {
								if (click(driver,
										fp.getManageInvestorFilterApplyButton(Workspace.FundraisingWorkspace, 60),
										"Apply buton", action.SCROLLANDBOOLEAN)) {
									if (bp.verifyErrorMessageOnPage(
											FundsPageErrorMessage.filterPleaseUseTrueOrFalseErroresage,
											fp.getManageInvestorFilterErrorMessage(Workspace.FundraisingWorkspace, 60),
											"Please use true or false error Message in filter")) {
										appLog.info("Please use true or false Error Message is verified at filter");
									} else {
										saa.assertTrue(false,
												"Please use true or false error message is not verified at filter.Expected:"
														+ FundsPageErrorMessage.filterPleaseUseTrueOrFalseErroresage
														+ " Actual"
														+ getText(driver,
																fp.getManageInvestorFilterErrorMessage(
																		Workspace.FundraisingWorkspace, 60),
																"Please use true or falseerror Message in filter",
																action.BOOLEAN));
									}
								} else {
									appLog.info("Not able to click on apply button");
									saa.assertTrue(false, "Not able to click on apply button");
								}
							} else {
								appLog.info("Not able to enter value in value tetxbox");
								saa.assertTrue(false, "Not able to enter value in value tetxbox");
							}
						} else {
							appLog.info("Not able to select Opeartor dropdown value");
							saa.assertTrue(false, "Not able to select Opeartor dropdown value");
						}
					} else {
						appLog.info("Not able to select field dropdown value");
						saa.assertTrue(false, "Not able to select field dropdown value");
					}
					if (click(driver, fp.getManageInvestorFilterClearButton(Workspace.FundraisingWorkspace, 60),
							"Clear Buton", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on clear button successfully");
					} else {
						appLog.info("Not able to click on clear button");
						saa.assertTrue(false, "Not able to click on clear button");
					}
					if (click(driver, fp.getManageInvestorFilterAddRowLink(Workspace.FundraisingWorkspace, 60),
							"Add Row Link", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getManageInvestorFilterApplyButton(Workspace.FundraisingWorkspace, 60),
								"Apply buton", action.SCROLLANDBOOLEAN)) {
							if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.filterPleaseSelectAFieldErroresage,
									fp.getManageInvestorFilterfieldSelectErrorMessage(Workspace.FundraisingWorkspace)
											.get(0),
									"Please select a field error Message in filter")) {
								appLog.info("Please select a field Error Message is verified at filter");
							} else {
								saa.assertTrue(false,
										"Please select a field error message is not verified at filter.Expected:"
												+ FundsPageErrorMessage.filterPleaseSelectAFieldErroresage + " Actual"
												+ getText(driver,
														fp.getManageInvestorFilterfieldSelectErrorMessage(
																Workspace.FundraisingWorkspace).get(0),
														"Please select a field error Message in filter",
														action.BOOLEAN));
							}
							if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.filterPleaseSelectAFieldErroresage,
									fp.getManageInvestorFilterfieldSelectErrorMessage(Workspace.FundraisingWorkspace)
											.get(1),
									"Please select a field error Message in filter")) {
								appLog.info("Please select a field Error Message is verified at filter");
							} else {
								saa.assertTrue(false,
										"Please select a field error message is not verified at filter.Expected:"
												+ FundsPageErrorMessage.filterPleaseSelectAFieldErroresage + " Actual"
												+ getText(driver,
														fp.getManageInvestorFilterfieldSelectErrorMessage(
																Workspace.FundraisingWorkspace).get(1),
														"Please select a field error Message in filter",
														action.BOOLEAN));
							}
						} else {
							appLog.info("Not able to click on apply button");
							saa.assertTrue(false, "Not able to click on apply button");
						}

					} else {
						appLog.info("Not able to click on add row link");
						saa.assertTrue(false, "Not able to click on add row link");

					}
				} else {
					appLog.info("Not able to click on manage investor icon");
					saa.assertTrue(false, "Not able to click on manage investor button");
				}
			} else {
				appLog.info("Not able to click on created fund");
				saa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on Funds tab");
			saa.assertTrue(false, "Not able to click on Funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M6tc029_VerifyFilterAsPerFilterSingleRowFilterInvestorAndMultipleRowFilterInvestorSheetInManageInvestor(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		SoftAssert saa1=new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		for (int i = 0; i < 2; i++) {
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(ExcelUtils.readData(filterPath,"Funds", 1, 0))) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
						"FundraisingWorkspace View.");
				if (click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60), "Manage Investor Icon",
						action.SCROLLANDBOOLEAN)) {
					if (i == 0) {
						sa = fp.checkFilterSingle(filterPath,Workspace.FundraisingWorkspace, "Single row filter_Fundraising",60);
						switchToDefaultContent(driver);
						appLog.info("Single Filter is Done Successfully.");
					} else if (i == 1) {
						saa1 = fp.applyCriterionOnManageInvestor(filterPath,"Multiple row filter_Fundraising", Workspace.FundraisingWorkspace,60);
						switchToDefaultContent(driver);
						appLog.info("Done");
					}
						
			} else {
				appLog.info("Not able to click on manage investor icon");
				saa.assertTrue(false, "Not able to click on manage investor button");
			}
		} else {
			appLog.info("Not able to click on created fund");
			saa.assertTrue(false, "Not able to click on created fund");
		}
	} else {
		appLog.info("Not able to click on Funds tab");
		saa.assertTrue(false, "Not able to click on Funds tab");
	}
	}
	switchToDefaultContent(driver);
	lp.CRMlogout();
	try{
		sa.combineAssertions(saa);
		sa.assertAll();
	} catch(Throwable th){
		appLog.error("Following asserts failed in single filter: ");
		appLog.info(th.getLocalizedMessage());
		saa1.assertTrue(false,"Some filters failed in single assert.");
	}
	saa.assertAll();
	saa1.assertAll();
	sa.assertAll();
}
	
	@Test
	public void M6tc030_CheckUIOfManageInvestorPopup() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		List<String> filterHeaderList = new ArrayList<String>();
		filterHeaderList.add("Field");
		filterHeaderList.add("Operator");
		filterHeaderList.add("Value");
		filterHeaderList.add("");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(ExcelUtils.readData(filterPath,"Funds", 1, 0))) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
						"InvestorWorkspace View.");
				if (click(driver, fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60), "Manage Investor Icon",
						action.SCROLLANDBOOLEAN)) {
					if (fp.getManageInvestorFilterCollapsedIcon(Workspace.InvestorWorkspace, 60) != null) {
						appLog.info("Filter is displaying in expanded form");
					} else {
						appLog.info("Filter is not displaying in expanded form");
						saa.assertTrue(false, "Filter is not displaying in expanded form");
					}
					List<WebElement> headerList = fp.getManageInvestorFilterHeaderList(Workspace.InvestorWorkspace);
					for (int i = 0; i < headerList.size(); i++) {
						String headertext = headerList.get(i).getText().trim();
						if (compareMultipleListWithoutAssertion(headertext, filterHeaderList)) {
							appLog.info("Filter header is verified");
						} else {
							appLog.info("Filter header is not verified");
							saa.assertTrue(false, "Filter header is not verified:" + headertext);
						}
					}
					if (fp.getManageInvestorFilterFieldDropdown(Workspace.InvestorWorkspace) != null) {
						appLog.info("Field dropdown is displaying");
					} else {
						appLog.info("Field dropdown is not displaying");
						saa.assertTrue(false, "Field dropdown is not displaying");
					}
					if (fp.getManageInvestorFilterOperatorDropdown(Workspace.InvestorWorkspace) != null) {
						appLog.info("Operator dropdown is displaying");
					} else {
						appLog.info("Operator dropdown is not displaying");
						saa.assertTrue(false, "Operator dropdown is not displaying");
					}

					if (fp.getManageInvestorFilterValueTextBox(Workspace.InvestorWorkspace) != null) {
						appLog.info("Value TextBox is displaying");
					} else {
						appLog.info("Value TextBox is not displaying");
						saa.assertTrue(false, "Value TextBox is not displaying");
					}
					if (click(driver, fp.getManageInvestorFilterCollapsedIcon(Workspace.InvestorWorkspace, 60),
							"Expanded Icon", action.SCROLLANDBOOLEAN)) {
						if (fp.getManageInvestorFilterExpandedIcon(Workspace.InvestorWorkspace, 60) != null) {
							appLog.info("Filter section get collapsed");
							if (fp.getManageInvestorFilterCollapsedHeader(Workspace.InvestorWorkspace, 60) != null) {
								appLog.info("Filter Invetsor header tab is displaying");
							} else {
								appLog.info("Filter investor header is not displaying");
								saa.assertTrue(false, "Filter investor header is not displaying");
							}
						} else {
							appLog.info("Filter section is not get cpollapsed");
							saa.assertTrue(false, "Filter section does not get collapsed");
						}
					} else {
						appLog.info("Not able to click on manage Investor Filter Expanded Icon");
						saa.assertTrue(false, "Not able to click on manage Investor Filter Expanded Icon");
					}
				} else {
					appLog.info("Not able to click on manage investor icon");
					saa.assertTrue(false, "Not able to click on manage investor button");
				}
			} else {
				appLog.info("Not able to click on created fund");
				saa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on Funds tab");
			saa.assertTrue(false, "Not able to click on Funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M6tc031_CheckFunctionalityOfAddRowButton() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(ExcelUtils.readData(filterPath,"Funds", 1, 0))) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
						"InvestorWorkspace View.");
				if (click(driver, fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60), "Manage Investor Icon",
						action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getManageInvestorFilterAddRowLink(Workspace.InvestorWorkspace, 60),
							"Add Row Link", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on add row link successfully");
						int filterRow = fp.getManageInvestorFilterRows(Workspace.InvestorWorkspace).size();
						if (filterRow == 2) {
							appLog.info("One other row is added successfully after clicking on add row link");
							if (fp.getManageInvestorFilterOperatorDropdown(Workspace.InvestorWorkspace).size() == 2) {
								appLog.info("2 operator dropdown is displaying");
							} else {
								appLog.info("2 operator Dropdown is not displaying");
								saa.assertTrue(false, "2 operator Dropdown is not displaying");
							}
							if (fp.getManageInvestorFilterFieldDropdown(Workspace.InvestorWorkspace).size() == 2) {
								appLog.info("2 Field dropdown is displaying");
							} else {
								appLog.info("2 Field Dropdown is not displaying");
								saa.assertTrue(false, "2 Field Dropdown is not displaying");
							}
							if (fp.getManageInvestorFilterValueTextBox(Workspace.InvestorWorkspace).size() == 2) {
								appLog.info("2 Value textbox is displaying");
							} else {
								appLog.info("2 Value textbox is not displaying");
								saa.assertTrue(false, "2 Value textbox is not displaying");
							}
						} else {
							appLog.info("One other row is not added successfully after clicking on add row link");
							saa.assertTrue(false,
									"One other row is not added successfully after clicking on add row link");
						}
					} else {
						appLog.info("Not able to click on add row link");
						saa.assertTrue(false, "Not able to click on add row link");
					}
					for (int i = 1; i < 9; i++) {
						if (click(driver, fp.getManageInvestorFilterAddRowLink(Workspace.InvestorWorkspace, 60),
								"Add Row Link", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on add row link successfully");
						} else {
							appLog.info("Not able to click on add row link");
							saa.assertTrue(false, "Not able to click on add row link");
						}
					}
					int filterRow = fp.getManageInvestorFilterRows(Workspace.InvestorWorkspace).size();
					if (filterRow == 10) {
						appLog.info("10 rows are added successfully after clicking on add row link");
						if (fp.getManageInvestorFilterOperatorDropdown(Workspace.InvestorWorkspace).size() == 10) {
							appLog.info("10 operator dropdown is displaying");
						} else {
							appLog.info("10 operator Dropdown is not displaying");
							saa.assertTrue(false, "10 operator Dropdown is not displaying");
						}
						if (fp.getManageInvestorFilterFieldDropdown(Workspace.InvestorWorkspace).size() == 10) {
							appLog.info("10 Field dropdown is displaying");
						} else {
							appLog.info("10 Field Dropdown is not displaying");
							saa.assertTrue(false, "10 Field Dropdown is not displaying");
						}
						if (fp.getManageInvestorFilterValueTextBox(Workspace.InvestorWorkspace).size() == 10) {
							appLog.info("10 Value textbox is displaying");
						} else {
							appLog.info("10 Value textbox is not displaying");
							saa.assertTrue(false, "10 Value textbox is not displaying");
						}
					} else {
						appLog.info("10 rows are not added successfully after clicking on add row link");
						saa.assertTrue(false, "10 rows are not added successfully after clicking on add row link");
					}
					switchToDefaultContent(driver);
					switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
							"InvestorWorkspace View.");
					ThreadSleep(4000);
					String addRow = fp.getManageInvestorFilterAddRowLink(Workspace.InvestorWorkspace, 60)
							.getAttribute("class");
					if (addRow.equalsIgnoreCase("add deactive")) {
						appLog.info("Add row link is not enabled");
					} else {
						appLog.info("Add row link is enabled");
						saa.assertTrue(false, "Add row link is enabled");
					}
					for (int i = 0; i < 2; i++) {
						if (click(driver, fp.getManageInvestorFilterRemoveRowIcon(Workspace.InvestorWorkspace).get(i),
								"Remove Row icon", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on remove row icon");
						} else {
							appLog.info("Not able to click on remove row icon");
							saa.assertTrue(false, "Not able to click on remove row icon");
						}
					}
					filterRow = fp.getManageInvestorFilterRows(Workspace.InvestorWorkspace).size();
					if (filterRow == 8) {
						appLog.info("2 rows get deleted successfully");
					} else {
						appLog.info("2 rows not get deleted successfully");
						saa.assertTrue(false, "2 rows not get deleted successfully");
					}
					if (isEnabled(driver, fp.getManageInvestorFilterAddRowLink(Workspace.InvestorWorkspace, 60),
							"Add Row Link")) {
						appLog.info("Add row link is enabled");
					} else {
						appLog.info("Add row link is not enabled");
						saa.assertTrue(false, "Add row link is not enabled");
					}
				} else {
					appLog.info("Not able to click on manage investor icon");
					saa.assertTrue(false, "Not able to click on manage investor button");
				}
			} else {
				appLog.info("Not able to click on created fund");
				saa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on Funds tab");
			saa.assertTrue(false, "Not able to click on Funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M6tc032_CheckFunctionalityOfClearButton() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(ExcelUtils.readData(filterPath,"Funds", 1, 0))) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
						"Investor Workspace View.");
				if (click(driver, fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60), "Manage Investor Icon",
						action.SCROLLANDBOOLEAN)) {

					if (selectVisibleTextFromDropDown(driver,
							fp.getManageInvestorFilterFieldDropdown(Workspace.InvestorWorkspace).get(0),
							"Field Dropdown", "Account:TestAutoNumberA")) {
						appLog.info("Selected field dropdown value");
						if (selectVisibleTextFromDropDown(driver,
								fp.getManageInvestorFilterOperatorDropdown(Workspace.InvestorWorkspace).get(0),
								"Operator Dropdown", "equals")) {
							appLog.info("Selected Operator dropdown value");
							if (sendKeys(driver,
									fp.getManageInvestorFilterValueTextBox(Workspace.InvestorWorkspace).get(0),
									"abcd1234", "Value text box", action.SCROLLANDBOOLEAN)) {
								ThreadSleep(2000);
								if (click(driver, fp.getManageInvestorFilterAddRowLink(Workspace.InvestorWorkspace, 60),
										"Add row link", action.SCROLLANDBOOLEAN)) {
									ThreadSleep(2000);
									if (selectVisibleTextFromDropDown(driver,
											fp.getManageInvestorFilterFieldDropdown(Workspace.InvestorWorkspace).get(1),
											"Field Dropdown", "Commitment:TestURLC")) {
										appLog.info("Selected field dropdown value");
										if (selectVisibleTextFromDropDown(driver,
												fp.getManageInvestorFilterOperatorDropdown(Workspace.InvestorWorkspace)
														.get(1),
												"Operator Dropdown", "equals")) {
											appLog.info("Selected Operator dropdown value");
											if (sendKeys(driver,
													fp.getManageInvestorFilterValueTextBox(Workspace.InvestorWorkspace)
															.get(1),
													"www.", "Value text box", action.SCROLLANDBOOLEAN)) {
												if (click(driver,
														fp.getManageInvestorFilterClearButton(
																Workspace.InvestorWorkspace, 60),
														"Clear button", action.SCROLLANDBOOLEAN)) {
													ThreadSleep(5000);
													if (fp.getManageInvestorFilterOperatorDropdown(
															Workspace.InvestorWorkspace).size() == 2) {
														appLog.info("2 operator dropdown is displaying");
														for (int i = 0; i < 2; i++) {
															String selectedValue = getSelectedOptionOfDropDown(driver,
																	fp.getManageInvestorFilterOperatorDropdown(
																			Workspace.InvestorWorkspace).get(i),
																	"Opearator Dropdown", "text");
															if (selectedValue.equalsIgnoreCase("equals")) {
																appLog.info("Equals value is selected in dropdown");
															} else {
																appLog.info("Equals value is not selected in dropdown");
																saa.assertTrue(false,
																		"Equals value is not selected in dropdown");
															}
														}
													} else {
														appLog.info("2 operator Dropdown is not displaying");
														saa.assertTrue(false, "2 operator Dropdown is not displaying");
													}
													if (fp.getManageInvestorFilterFieldDropdown(
															Workspace.InvestorWorkspace).size() == 2) {
														appLog.info("2 Field dropdown is displaying");
														for (int i = 0; i < 2; i++) {
															String selectedValue = getSelectedOptionOfDropDown(driver,
																	fp.getManageInvestorFilterFieldDropdown(
																			Workspace.InvestorWorkspace).get(i),
																	"Field Dropdown", "text");
															if (selectedValue.equalsIgnoreCase("--None--")) {
																appLog.info("None value is selected in dropdown");
															} else {
																appLog.info("None value is not selected in dropdown");
																saa.assertTrue(false,
																		"None value is not selected in dropdown");
															}
														}
													} else {
														appLog.info("2 Field Dropdown is not displaying");
														saa.assertTrue(false, "2 Field Dropdown is not displaying");
													}
													if (fp.getManageInvestorFilterValueTextBox(
															Workspace.InvestorWorkspace).size() == 2) {
														appLog.info("2 Value textbox is displaying");

													} else {
														appLog.info("2 Value textbox is not displaying");
														saa.assertTrue(false, "2 Value textbox is not displaying");
													}
												} else {
													appLog.info("Not able to click on clear button");
													saa.assertTrue(false, "Not able to click on clear button");
												}
											} else {
												appLog.info("Not able to enter value in value tetxbox");
												saa.assertTrue(false, "Not able to enter value in value tetxbox");
											}
										} else {
											appLog.info("Not able to select Opeartor dropdown value");
											saa.assertTrue(false, "Not able to select Opeartor dropdown value");
										}
									} else {
										appLog.info("Not able to select field dropdown value");
										saa.assertTrue(false, "Not able to select field dropdown value");
									}
								} else {
									appLog.info("Not able to click on add row link");
									saa.assertTrue(false, "Not able to click on add row link");
								}
							} else {
								appLog.info("Not able to enter value in value tetxbox");
								saa.assertTrue(false, "Not able to enter value in value tetxbox");
							}
						} else {
							appLog.info("Not able to select Opeartor dropdown value");
							saa.assertTrue(false, "Not able to select Opeartor dropdown value");
						}
					} else {
						appLog.info("Not able to select field dropdown value");
						saa.assertTrue(false, "Not able to select field dropdown value");
					}
				} else {
					appLog.info("Not able to click on manage investor icon");
					saa.assertTrue(false, "Not able to click on manage investor button");
				}
			} else {
				appLog.info("Not able to click on created fund");
				saa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on Funds tab");
			saa.assertTrue(false, "Not able to click on Funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M6tc033_CheckFilterValidationOnManageInvestorPopUp(){
	LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
	BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
	FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
	SoftAssert saa = new SoftAssert();
	lp.CRMLogin(CRMUser1EmailID,adminPassword);
	if(bp.clickOnTab(TabName.FundsTab)){
		if (fp.clickOnCreatedFund(ExcelUtils.readData(filterPath,"Funds", 1, 0))) {
			switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
			scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
					"InvestorWorkspace View.");
			if (click(driver, fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60), "Manage Investor Icon",
					action.SCROLLANDBOOLEAN)) {
				if (selectVisibleTextFromDropDown(driver,
						fp.getManageInvestorFilterFieldDropdown(Workspace.InvestorWorkspace).get(0),
						"Field Dropdown", "Account:Employees")) {
					appLog.info("Selected field dropdown value");
					if (selectVisibleTextFromDropDown(driver,
							fp.getManageInvestorFilterOperatorDropdown(Workspace.InvestorWorkspace).get(0),
							"Operator Dropdown", "equals")) {
						appLog.info("Selected Operator dropdown value");
						if (sendKeys(driver,
								fp.getManageInvestorFilterValueTextBox(Workspace.InvestorWorkspace).get(0), "ABC",
								"Value text box", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageInvestorFilterApplyButton(Workspace.InvestorWorkspace, 60),
									"Apply buton", action.SCROLLANDBOOLEAN)) {
								if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.filterInvalidNumberErroresage,
										fp.getManageInvestorFilterErrorMessage(Workspace.InvestorWorkspace, 60),
										"Invalid number error Message in filter")) {
									appLog.info("Invalid number Error Message is verified at filter");
								} else {
									saa.assertTrue(false,
											"Invalid number Error Message is not verified at filter.Expected:"
													+ FundsPageErrorMessage.filterInvalidNumberErroresage + " Actual"
													+ getText(driver,
															fp.getManageInvestorFilterErrorMessage(
																	Workspace.InvestorWorkspace, 60),
															"Invalid number error Message in filter", action.BOOLEAN));
								}
							} else {
								appLog.info("Not able to click on apply button");
								saa.assertTrue(false, "Not able to click on apply button");
							}
						} else {
							appLog.info("Not able to enter value in value tetxbox");
							saa.assertTrue(false, "Not able to enter value in value tetxbox");
						}
					} else {
						appLog.info("Not able to select Opeartor dropdown value");
						saa.assertTrue(false, "Not able to select Opeartor dropdown value");
					}
				} else {
					appLog.info("Not able to select field dropdown value");
					saa.assertTrue(false, "Not able to select field dropdown value");
				}
				if (click(driver, fp.getManageInvestorFilterClearButton(Workspace.InvestorWorkspace, 60),
						"Clear Buton", action.SCROLLANDBOOLEAN)) {
					appLog.info("Clicked on clear button successfully");
				} else {
					appLog.info("Not able to click on clear button");
					saa.assertTrue(false, "Not able to click on clear button");
				}
				switchToDefaultContent(driver);
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
						"Investor Workspace View.");
				if (selectVisibleTextFromDropDown(driver,
						fp.getManageInvestorFilterFieldDropdown(Workspace.InvestorWorkspace).get(0),
						"Field Dropdown", "Account:Ownership")) {
					appLog.info("Selected field dropdown value");
					if (selectVisibleTextFromDropDown(driver,
							fp.getManageInvestorFilterOperatorDropdown(Workspace.InvestorWorkspace).get(0),
							"Operator Dropdown", "equals")) {
						appLog.info("Selected Operator dropdown value");
						if (sendKeys(driver,
								fp.getManageInvestorFilterValueTextBox(Workspace.InvestorWorkspace).get(0), "ABC",
								"Value text box", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageInvestorFilterApplyButton(Workspace.InvestorWorkspace, 60),
									"Apply buton", action.SCROLLANDBOOLEAN)) {
								if (bp.verifyErrorMessageOnPage(
										FundsPageErrorMessage.filterPicklistValueNotExistErroresage,
										fp.getManageInvestorFilterErrorMessage(Workspace.InvestorWorkspace, 60),
										"Picklist value not exist error Message in filter")) {
									appLog.info("Picklist value not exist Error Message is verified at filter");
								} else {
									saa.assertTrue(false, "Picklist value not exist is not verified at filter.Expected:"
											+ FundsPageErrorMessage.filterPicklistValueNotExistErroresage + " Actual"
											+ getText(driver,
													fp.getManageInvestorFilterErrorMessage(
															Workspace.FundraisingWorkspace, 60),
													"Picklist value not exist error Message in filter",
													action.BOOLEAN));
								}
							} else {
								appLog.info("Not able to click on apply button");
								saa.assertTrue(false, "Not able to click on apply button");
							}
						} else {
							appLog.info("Not able to enter value in value tetxbox");
							saa.assertTrue(false, "Not able to enter value in value tetxbox");
						}
					} else {
						appLog.info("Not able to select Opeartor dropdown value");
						saa.assertTrue(false, "Not able to select Opeartor dropdown value");
					}
				} else {
					appLog.info("Not able to select field dropdown value");
					saa.assertTrue(false, "Not able to select field dropdown value");
				}
				if (click(driver, fp.getManageInvestorFilterClearButton(Workspace.InvestorWorkspace, 60),
						"Clear Buton", action.SCROLLANDBOOLEAN)) {
					appLog.info("Clicked on clear button successfully");
				} else {
					appLog.info("Not able to click on clear button");
					saa.assertTrue(false, "Not able to click on clear button");
				}
				switchToDefaultContent(driver);
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
						"Investor Workspace View.");
				if (selectVisibleTextFromDropDown(driver,
						fp.getManageInvestorFilterFieldDropdown(Workspace.InvestorWorkspace).get(0),
						"Field Dropdown", "Account:Total Fund Commitments")) {
					appLog.info("Selected field dropdown value");
					if (selectVisibleTextFromDropDown(driver,
							fp.getManageInvestorFilterOperatorDropdown(Workspace.InvestorWorkspace).get(0),
							"Operator Dropdown", "equals")) {
						appLog.info("Selected Operator dropdown value");
						if (sendKeys(driver,
								fp.getManageInvestorFilterValueTextBox(Workspace.InvestorWorkspace).get(0), "ABC",
								"Value text box", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageInvestorFilterApplyButton(Workspace.InvestorWorkspace, 60),
									"Apply buton", action.SCROLLANDBOOLEAN)) {
								if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.filterInvalidCurrencyErroresage,
										fp.getManageInvestorFilterErrorMessage(Workspace.InvestorWorkspace, 60),
										"Invalid Currency error Message in filter")) {
									appLog.info("Invalid Currency Error Message is verified at filter");
								} else {
									saa.assertTrue(false,
											"Invalid Currency error message is not verified at filter.Expected:"
													+ FundsPageErrorMessage.filterInvalidCurrencyErroresage + " Actual"
													+ getText(driver,
															fp.getManageInvestorFilterErrorMessage(
																	Workspace.InvestorWorkspace, 60),
															"Invalid Currency error Message in filter",
															action.BOOLEAN));
								}
							} else {
								appLog.info("Not able to click on apply button");
								saa.assertTrue(false, "Not able to click on apply button");
							}
						} else {
							appLog.info("Not able to enter value in value tetxbox");
							saa.assertTrue(false, "Not able to enter value in value tetxbox");
						}
					} else {
						appLog.info("Not able to select Opeartor dropdown value");
						saa.assertTrue(false, "Not able to select Opeartor dropdown value");
					}
				} else {
					appLog.info("Not able to select field dropdown value");
					saa.assertTrue(false, "Not able to select field dropdown value");
				}

				if (click(driver, fp.getManageInvestorFilterClearButton(Workspace.InvestorWorkspace, 60),
						"Clear Buton", action.SCROLLANDBOOLEAN)) {
					appLog.info("Clicked on clear button successfully");
				} else {
					appLog.info("Not able to click on clear button");
					saa.assertTrue(false, "Not able to click on clear button");
				}
				switchToDefaultContent(driver);
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
						"Investor Workspace View.");
				if (selectVisibleTextFromDropDown(driver,
						fp.getManageInvestorFilterFieldDropdown(Workspace.InvestorWorkspace).get(0),
						"Field Dropdown", "Commitment:Final Commitment Date")) {
					appLog.info("Selected field dropdown value");
					if (selectVisibleTextFromDropDown(driver,
							fp.getManageInvestorFilterOperatorDropdown(Workspace.InvestorWorkspace).get(0),
							"Operator Dropdown", "equals")) {
						appLog.info("Selected Operator dropdown value");
						if (sendKeys(driver,
								fp.getManageInvestorFilterValueTextBox(Workspace.InvestorWorkspace).get(0),
								"ABC12345", "Value text box", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageInvestorFilterApplyButton(Workspace.InvestorWorkspace, 60),
									"Apply buton", action.SCROLLANDBOOLEAN)) {
								if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.filterInvalidDateErroresage,
										fp.getManageInvestorFilterErrorMessage(Workspace.InvestorWorkspace, 60),
										"Invalid date error Message in filter")) {
									appLog.info("Invalid date Error Message is verified at filter");
								} else {
									saa.assertTrue(false,
											"Invalid date error message is not verified at filter.Expected:"
													+ FundsPageErrorMessage.filterInvalidDateErroresage + " Actual"
													+ getText(driver,
															fp.getManageInvestorFilterErrorMessage(
																	Workspace.InvestorWorkspace, 60),
															"Invalid date error Message in filter", action.BOOLEAN));
								}
							} else {
								appLog.info("Not able to click on apply button");
								saa.assertTrue(false, "Not able to click on apply button");
							}
						} else {
							appLog.info("Not able to enter value in value tetxbox");
							saa.assertTrue(false, "Not able to enter value in value tetxbox");
						}
					} else {
						appLog.info("Not able to select Opeartor dropdown value");
						saa.assertTrue(false, "Not able to select Opeartor dropdown value");
					}
				} else {
					appLog.info("Not able to select field dropdown value");
					saa.assertTrue(false, "Not able to select field dropdown value");
				}
				if (click(driver, fp.getManageInvestorFilterClearButton(Workspace.InvestorWorkspace, 60),
						"Clear Buton", action.SCROLLANDBOOLEAN)) {
					appLog.info("Clicked on clear button successfully");
				} else {
					appLog.info("Not able to click on clear button");
					saa.assertTrue(false, "Not able to click on clear button");
				}
				switchToDefaultContent(driver);
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
						"Investor Workspace View.");
				if (selectVisibleTextFromDropDown(driver,
						fp.getManageInvestorFilterFieldDropdown(Workspace.InvestorWorkspace).get(0),
						"Field Dropdown", "Account:Existing LP")) {
					appLog.info("Selected field dropdown value");
					if (selectVisibleTextFromDropDown(driver,
							fp.getManageInvestorFilterOperatorDropdown(Workspace.InvestorWorkspace).get(0),
							"Operator Dropdown", "equals")) {
						appLog.info("Selected Operator dropdown value");
						if (sendKeys(driver,
								fp.getManageInvestorFilterValueTextBox(Workspace.InvestorWorkspace).get(0), "test",
								"Value text box", action.SCROLLANDBOOLEAN)) {
							if (click(driver, fp.getManageInvestorFilterApplyButton(Workspace.InvestorWorkspace, 60),
									"Apply buton", action.SCROLLANDBOOLEAN)) {
								if (bp.verifyErrorMessageOnPage(
										FundsPageErrorMessage.filterPleaseUseTrueOrFalseErroresage,
										fp.getManageInvestorFilterErrorMessage(Workspace.InvestorWorkspace, 60),
										"Please use true or false error Message in filter")) {
									appLog.info("Please use true or false Error Message is verified at filter");
								} else {
									saa.assertTrue(false,
											"Please use true or false error message is not verified at filter.Expected:"
													+ FundsPageErrorMessage.filterPleaseUseTrueOrFalseErroresage
													+ " Actual"
													+ getText(driver,
															fp.getManageInvestorFilterErrorMessage(
																	Workspace.InvestorWorkspace, 60),
															"Please use true or falseerror Message in filter",
															action.BOOLEAN));
								}
							} else {
								appLog.info("Not able to click on apply button");
								saa.assertTrue(false, "Not able to click on apply button");
							}
						} else {
							appLog.info("Not able to enter value in value tetxbox");
							saa.assertTrue(false, "Not able to enter value in value tetxbox");
						}
					} else {
						appLog.info("Not able to select Opeartor dropdown value");
						saa.assertTrue(false, "Not able to select Opeartor dropdown value");
					}
				} else {
					appLog.info("Not able to select field dropdown value");
					saa.assertTrue(false, "Not able to select field dropdown value");
				}
				if (click(driver, fp.getManageInvestorFilterClearButton(Workspace.InvestorWorkspace, 60),
						"Clear Buton", action.SCROLLANDBOOLEAN)) {
					appLog.info("Clicked on clear button successfully");
				} else {
					appLog.info("Not able to click on clear button");
					saa.assertTrue(false, "Not able to click on clear button");
				}
				if (click(driver, fp.getManageInvestorFilterAddRowLink(Workspace.InvestorWorkspace, 60),
						"Add Row Link", action.SCROLLANDBOOLEAN)) {
					switchToDefaultContent(driver);
					switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
							"Investor Workspace View.");
					if (click(driver, fp.getManageInvestorFilterApplyButton(Workspace.InvestorWorkspace, 60),
							"Apply buton", action.SCROLLANDBOOLEAN)) {
						if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.filterPleaseSelectAFieldErroresage, fp
								.getManageInvestorFilterfieldSelectErrorMessage(Workspace.InvestorWorkspace).get(0),
								"Please select a field error Message in filter")) {
							appLog.info("Please select a field Error Message is verified at filter");
						} else {
							saa.assertTrue(false,
									"Please select a field error message is not verified at filter.Expected:"
											+ FundsPageErrorMessage.filterPleaseSelectAFieldErroresage + " Actual"
											+ getText(driver,
													fp.getManageInvestorFilterfieldSelectErrorMessage(
															Workspace.InvestorWorkspace).get(0),
													"Please select a field error Message in filter", action.BOOLEAN));
						}
						if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.filterPleaseSelectAFieldErroresage, fp
								.getManageInvestorFilterfieldSelectErrorMessage(Workspace.InvestorWorkspace).get(1),
								"Please select a field error Message in filter")) {
							appLog.info("Please select a field Error Message is verified at filter");
						} else {
							saa.assertTrue(false,
									"Please select a field error message is not verified at filter.Expected:"
											+ FundsPageErrorMessage.filterPleaseSelectAFieldErroresage + " Actual"
											+ getText(driver,
													fp.getManageInvestorFilterfieldSelectErrorMessage(
															Workspace.InvestorWorkspace).get(1),
													"Please select a field error Message in filter", action.BOOLEAN));
						}
					} else {
						appLog.info("Not able to click on apply button");
						saa.assertTrue(false, "Not able to click on apply button");
					}

				} else {
					appLog.info("Not able to click on add row link");
					saa.assertTrue(false, "Not able to click on add row link");

				}
			} else {
				appLog.info("Not able to click on manage investor icon");
				saa.assertTrue(false, "Not able to click on manage investor button");
			}
		} else {
			appLog.info("Not able to click on created fund");
			saa.assertTrue(false, "Not able to click on created fund");
		}
	} else

	{
		appLog.info("Not able to click on Funds tab");
		saa.assertTrue(false, "Not able to click on Funds tab");
	}

	switchToDefaultContent(driver);
	lp.CRMlogout();
	sa.combineAssertions(saa);
	sa.assertAll();
}
	
	@Test
	public void M6tc034_VerifyFilterAsPerFilterSingleRowFilterInvestorMultipleRowFilterInvestorSheetInManageInvestorPopup(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		SoftAssert saa1=new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		for (int i = 0; i < 2; i++) {
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(ExcelUtils.readData(filterPath,"Funds", 1, 0))) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
						"FundraisingWorkspace View.");
				if (click(driver, fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60), "Manage Investor Icon",
						action.SCROLLANDBOOLEAN)) {
					if (i == 0) {
						sa = fp.checkFilterSingle(filterPath,Workspace.InvestorWorkspace, "Single row filter_Investor",60);
						switchToDefaultContent(driver);
						appLog.info("Single Filter is Done Successfully.");
					} else if (i == 1) {
						saa1 = fp.applyCriterionOnManageInvestor(filterPath,"Multiple row filter_Investor", Workspace.InvestorWorkspace,60);
						switchToDefaultContent(driver);
						appLog.info("Done");
					}
						
			} else {
				appLog.info("Not able to click on manage investor icon");
				saa.assertTrue(false, "Not able to click on manage investor button");
			}
		} else {
			appLog.info("Not able to click on created fund");
			saa.assertTrue(false, "Not able to click on created fund");
		}
	} else {
		appLog.info("Not able to click on Funds tab");
		saa.assertTrue(false, "Not able to click on Funds tab");
	}
	}
	switchToDefaultContent(driver);
	lp.CRMlogout();	
	try{
		sa.combineAssertions(saa);
		sa.assertAll();
	} catch(Throwable th){
		appLog.error("Following asserts failed in single filter: ");
		appLog.info(th.getLocalizedMessage());
		saa1.assertTrue(false,"Some filters failed in single assert.");
	}
	saa.assertAll();
	saa1.assertAll();	
	sa.combineAssertions(saa1);
	sa.assertAll();
	}
	
	@Test
	public void M6tc035_postCondition(){
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
