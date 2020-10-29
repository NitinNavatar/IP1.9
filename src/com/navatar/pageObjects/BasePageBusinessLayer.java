/**
 * 
 */
package com.navatar.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.navatar.generic.BaseLib;
import com.navatar.generic.CommonLib;
import com.navatar.generic.ExcelUtils;
import com.navatar.generic.SoftAssert;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.navatar.generic.CommonLib.ContentGridArrowKeyFunctions;
import com.navatar.generic.CommonLib.EnableDisable;
import com.navatar.generic.CommonLib.FolderType;
import com.navatar.generic.CommonLib.PageName;
import com.navatar.generic.CommonLib.SortOrder;
import com.navatar.generic.CommonLib.TabName;
import com.navatar.generic.CommonLib.Workspace;
import com.navatar.generic.CommonLib.action;
import com.navatar.generic.CommonLib.customTabActionType;
import com.navatar.generic.CommonLib.excelLabel;
import com.navatar.generic.CommonLib.sideMenu;
import com.relevantcodes.extentreports.LogStatus;
import static com.navatar.generic.CommonLib.*;
import static com.navatar.generic.CommonVariables.*;
import com.navatar.generic.CommonVariables;
import com.navatar.generic.EmailLib;
import static com.navatar.generic.AppListeners.*;
import static com.navatar.generic.BaseLib.sa;
import java.util.Random;
import java.util.Set;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
/**
 * @author Parul Singh
 *
 */
public class BasePageBusinessLayer extends BasePage implements BasePageErrorMessage{

	/**
	 * @param driver
	 */
	public BasePageBusinessLayer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @author Ankit Jaiswal
	 * @param TabName
	 * @return true/false
	 */
	public boolean clickOnTab(TabName TabName) {
		String tabName = null;
		switch (TabName) {
		case ContactTab:
			tabName = "Contacts Tab";
			break;
		case InstituitonsTab:
			tabName = "Institutions Tab";
			break;
		case NIMTab:
			tabName = "Navatar Investor Manager Tab";
			break;
		case FundraisingsTab:
			tabName = "Fundraisings Tab";
			break;
		case FundsTab:
			tabName = "Funds Tab";
			break;
		case CommitmentsTab:
			tabName = "Commitments Tab";
			break;
		case PartnershipsTab:
			tabName = "Partnerships Tab";
			break;
		case NavatarInvestorAddOns:
			tabName = "Navatar Investor Add-ons Tab";
			break;
		case HomeTab:
			tabName="Home Tab";
			break;
		case FolderTemplate:
			tabName = "Folder Templates Tab";
			break;
		case RecycleBinTab:
			tabName = "Recycle Bin";
			break;
		default:
			return false;
		}
		System.err.println("Passed switch statement");
		if (Mode.Lightning.toString().equalsIgnoreCase(mode)) {
			return clickOnTab(environment, mode, TabName);
		} else {

		}
		for (int i = 0; i < 2; i++)
			if (click(driver,
					isDisplayed(driver,
							FindElement(driver, "//a[contains(@title,'" + tabName + "')]", tabName,
									action.SCROLLANDBOOLEAN, 30),
							"visibility", 30, tabName),
					tabName, action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked On " + tabName);
				if (tabName.equalsIgnoreCase("Contacts Tab") || tabName.equalsIgnoreCase("Institutions Tab")
						|| tabName.equalsIgnoreCase("Fundraisings Tab") || tabName.equalsIgnoreCase("Funds Tab")
						|| tabName.equalsIgnoreCase("Commitments Tab")
						|| tabName.equalsIgnoreCase("Partnerships Tab")) {
					WebElement ele = isDisplayed(driver,
							FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40),
							"visibility", 60, tabName);
					if (ele != null) {
						String header = ele.getText().trim();
						if (header.contains(tabName.split(" ")[0])) {
							appLog.info("Successfully clicked on " + tabName);
							return true;
						}
					}
				} else if (tabName.equalsIgnoreCase("Navatar Investor Manager Tab")) {
					NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
					BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
					if (nim.getNIMTabFrame(20) != null) {
						appLog.info("NIM Frame Loaded Successfully.");
						switchToFrame(driver, 30, nim.getNIMTabFrame(30));
						for(int j = 0; j < 20; j++ )
							if (nim.getFolderTemplatetab(0) != null) {
								switchToDefaultContent(driver);
								appLog.info("NIM Tab is loaded successfully.");
								return true;
							} else if (nim.getStartButton(0) != null) {
								switchToDefaultContent(driver);
								appLog.info("NIM Tab is loaded successfully.");
								return true;
							} else if (nim.getNextButton(0) != null) {
								switchToDefaultContent(driver);
								appLog.info("NIM Tab is loaded successfully");
								return true;
							} else if (nim.getRegistrationSuccessfulCloseBtn(0) != null) {
								System.err.println("Inside reg close button");
								switchToDefaultContent(driver);
								appLog.info("NIM Tab is loaded successfully.");
								return true;
							} else if (bp.getErrorMessageBeforeGivingInternalUserAccess(0) != null) {
								switchToDefaultContent(driver);
								appLog.info("NIM Tab is loaded successfully.");
								return true;
							} else {
								appLog.error("NIM Tab is not loaded");
							}
						switchToDefaultContent(driver);
					}
				} else if (tabName.equalsIgnoreCase("Navatar Investor Add-ons Tab")) {
					if (new NavatarInvestorAddonsPageBusinessLayer(driver).getDisclaimerLabel(60) != null) {
						appLog.info("Navatar investor add ons page is loaded successfully");
						switchToDefaultContent(driver);
						return true;
					} else {
						appLog.error("Navatar investor add on page is not loaded successfully");
					}
				}else if(tabName.equalsIgnoreCase("Home Tab")){
					WebElement ele=new HomePageBusineesLayer(driver).getDashboardLabelOnHomePage(60);
					if (ele != null) {
						appLog.info("Successfully clicked on " + tabName);
						return true;
					}
				} else if (tabName.equalsIgnoreCase("Folder Templates Tab")){
					return true;
				}
			}
		return false;
	}
	
	
	// Lightning Method.....
	public boolean clickOnTab(String environment, String mode,TabName TabName) {
		boolean flag = false;
		String tabName = null;
		switch (TabName) {
		case ContactTab:
			tabName = "Contacts";
			break;
		case InstituitonsTab:
			tabName = "Institutions";
			break;
		case NIMTab:
			tabName = "Navatar Investor Manager";
			break;
		case FundraisingsTab:
			tabName = "Fundraisings";
			break;
		case FundsTab:
			tabName = "Funds";
			break;
		case CommitmentsTab:
			tabName = "Commitments";
			break;
		case PartnershipsTab:
			tabName = "Partnerships";
			break;
		case NavatarInvestorAddOns:
			tabName = "Navatar Investor Add-ons";
			break;
		case HomeTab:
			tabName="Home";
			break;
		case FolderTemplate:
			tabName = "Folder Templates";
			break;
		case RecycleBinTab:
			tabName = "Recycle Bin";
			break;
		default:
			return false;
		}
		System.err.println("Passed switch statement");
		for (int i = 0; i < 2; i++)
			if(mode.equalsIgnoreCase(Mode.Classic.toString())) {
				if (click(driver,
						isDisplayed(driver,
								FindElement(driver, "//a[contains(@title,'"+tabName+" Tab')]", tabName,
										action.SCROLLANDBOOLEAN, 30),
								"visibility", 30, tabName),
						tabName, action.SCROLLANDBOOLEAN)) {
					appLog.info("Clicked On " + tabName);
					if (tabName.equalsIgnoreCase("Contacts Tab") || tabName.equalsIgnoreCase("Institutions Tab")
							|| tabName.equalsIgnoreCase("Fundraisings Tab") || tabName.equalsIgnoreCase("Funds Tab")
							|| tabName.equalsIgnoreCase("Commitments Tab")
							|| tabName.equalsIgnoreCase("Partnerships Tab")) {
						WebElement ele = isDisplayed(driver,
								FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40),
								"visibility", 60, tabName);
						if (ele != null) {
							String header = ele.getText().trim();
							if (header.contains(tabName.split(" ")[0])) {
								appLog.info("Successfully clicked on " + tabName);
								return true;
							}
						}
					} else if (tabName.equalsIgnoreCase("Navatar Investor Manager Tab")) {
						NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
						BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
						if (nim.getNIMTabFrame(20) != null) {
							appLog.info("NIM Frame Loaded Successfully.");
							switchToFrame(driver, 30, nim.getNIMTabFrame(30));
							for(int j = 0; j < 20; j++ )
								if (nim.getFolderTemplatetab(0) != null) {
									switchToDefaultContent(driver);
									appLog.info("NIM Tab is loaded successfully.");
									return true;
								} else if (nim.getStartButton(0) != null) {
									switchToDefaultContent(driver);
									appLog.info("NIM Tab is loaded successfully.");
									return true;
								} else if (nim.getNextButton(0) != null) {
									switchToDefaultContent(driver);
									appLog.info("NIM Tab is loaded successfully");
									return true;
								} else if (nim.getRegistrationSuccessfulCloseBtn(0) != null) {
									System.err.println("Inside reg close button");
									switchToDefaultContent(driver);
									appLog.info("NIM Tab is loaded successfully.");
									return true;
								} else if (bp.getErrorMessageBeforeGivingInternalUserAccess(0) != null) {
									switchToDefaultContent(driver);
									appLog.info("NIM Tab is loaded successfully.");
									return true;
								} else {
									appLog.error("NIM Tab is not loaded");
								}
							switchToDefaultContent(driver);
						}
					} else if (tabName.equalsIgnoreCase("Navatar Investor Add-ons Tab")) {
						if (new NavatarInvestorAddonsPageBusinessLayer(driver).getDisclaimerLabel(60) != null) {
							appLog.info("Navatar investor add ons page is loaded successfully");
							switchToDefaultContent(driver);
							return true;
						} else {
							appLog.error("Navatar investor add on page is not loaded successfully");
						}
					}else if(tabName.equalsIgnoreCase("Home Tab")){
						WebElement ele=new HomePageBusineesLayer(driver).getDashboardLabelOnHomePage(60);
						if (ele != null) {
							appLog.info("Successfully clicked on " + tabName);
							return true;
						}
					} else if (tabName.equalsIgnoreCase("Folder Templates Tab")){
						return true;
					}
				}
			}else {
				WebElement ele = isDisplayed(driver,
						FindElement(driver, "//a[contains(@href,'lightning') and contains(@title,'" + tabName + "')]/span/..",
								tabName, action.SCROLLANDBOOLEAN,30),
						"visibility", 30, tabName);
				if (ele != null) {
					appLog.info("Tab Found");
					ThreadSleep(5000);
					if (clickUsingJavaScript(driver, ele, tabName+" :Tab")) {
						CommonLib.log(LogStatus.INFO, "Tab found", YesNo.No);
						appLog.info("Clicked on Tab : "+tabName);
						if(!tabName.equalsIgnoreCase("Navatar Investor Manager")) {
							return true;
						}
					} else {
						appLog.error("Not Able to Click on Tab : "+tabName);
					}

				} else {
					CommonLib.log(LogStatus.INFO, "Going to found tab after clicking on More Icon", YesNo.No);
					if (click(driver, getMoreTabIcon(environment, mode, 10), "More Icon", action.SCROLLANDBOOLEAN)) {
						ele = isDisplayed(driver,
								FindElement(driver,
										"//a[contains(@href,'lightning')]/span[@class='slds-truncate']/span[contains(text(),'"
												+ tabName + "')]",
										tabName, action.SCROLLANDBOOLEAN, 10),
								"visibility", 10, tabName);
						if (ele!=null) {
							if (clickUsingJavaScript(driver, ele, tabName+" :Tab")) {
								appLog.info("Clicked on Tab on More Icon: "+tabName);
								CommonLib.log(LogStatus.INFO, "Tab found on More Icon", YesNo.No);
								if(!tabName.equalsIgnoreCase("Navatar Investor Manager")) {
									return true;
								}
							}	
						}
					} else {
						appLog.error("Not Able to Clicked on Tab on More Icon: "+tabName);
					}
				}
				if (tabName.equalsIgnoreCase("Navatar Investor Manager")) {
					NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
					BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
					for(int K=0; K<20; K++) {
						if(nim.getNIMTabParentFrame_Lightning()!=null) {
							ThreadSleep(3000);
							switchToFrame(driver, 2, nim.getNIMTabParentFrame_Lightning());
							if (nim.getNIMTabFrame(2) != null) {
								appLog.info("NIM  Parent Frame is Loaded Successfully.");
								switchToFrame(driver, 30, nim.getNIMTabFrame(30));
								for(int j = 0; j < 20; j++ ) {
									if (nim.getFolderTemplatetab(0) != null) {
										switchToDefaultContent(driver);
										appLog.info("NIM Tab is loaded successfully.");
										switchToFrame(driver, 30, nim.getNIMTabParentFrame_Lightning());
										return true;
									} else if (nim.getStartButton(0) != null) {
										switchToDefaultContent(driver);
										appLog.info("NIM Tab is loaded successfully.");
										switchToFrame(driver, 30, nim.getNIMTabParentFrame_Lightning());
										return true;
									} else if (nim.getNextButton(0) != null) {
										switchToDefaultContent(driver);
										appLog.info("NIM Tab is loaded successfully");
										switchToFrame(driver, 30, nim.getNIMTabParentFrame_Lightning());
										return true;
									} else if (nim.getRegistrationSuccessfulCloseBtn(0) != null) {
										System.err.println("Inside reg close button");
										switchToDefaultContent(driver);
										appLog.info("NIM Tab is loaded successfully.");
										switchToFrame(driver, 30, nim.getNIMTabParentFrame_Lightning());
										return true;
									} else if (bp.getErrorMessageBeforeGivingInternalUserAccess(0) != null) {
										switchToDefaultContent(driver);
										appLog.info("NIM Tab is loaded successfully.");
										switchToFrame(driver, 30, nim.getNIMTabParentFrame_Lightning());
										return true;
									} else {
										appLog.error("NIM Tab is not loaded");
									}
								}
							}
						}
					}
					switchToDefaultContent(driver);
				}
			}
		return false;
	}
	
	
	/**
	 * @author Ankit Jaiswal
	 * @param userfirstname
	 * @param userlastname
	 * @param email
	 * @param userLicense
	 * @param userProfile
	 * @return true/false
	 */
	public boolean createPEUser(String userfirstname, String userlastname, String email, String userLicense,
			String userProfile) {
		if (click(driver, getUserMenuTab(60), "User Menu Button", action.SCROLLANDBOOLEAN)) {
			appLog.info("clicked on user menu tab");
			if (click(driver, getUserMenuSetupLink(60), "Setup Link", action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on setup link");
				if (click(driver, getExpandManageUserIcon(60), "Manage User Icon", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on manage user expand icon");
					if (click(driver, getUsersLink(60), "User Link", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on users link");
						if (click(driver, getNewUserLink(60), "New User Button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on new users button");
							if (sendKeys(driver, getUserFirstName(60), userfirstname, "User First Name",
									action.SCROLLANDBOOLEAN)) {
								if (sendKeys(driver, getUserLastName(60), userlastname, "User Last Name",
										action.SCROLLANDBOOLEAN)) {
									if (sendKeys(driver, getUserEmailId(60), email, "User Email Id",
											action.SCROLLANDBOOLEAN)) {
										if (selectVisibleTextFromDropDown(driver, getUserUserLicenseDropDownList(60),
												"User License drop down list", userLicense)) {
											appLog.info("select user license from drop downlist: " + userLicense);
											if (selectVisibleTextFromDropDown(driver, getUserProfileDropDownList(60),
													"User profile drop down list", userProfile)) {
												appLog.info("select user profile from drop downlist: " + userProfile);
												if(click(driver, getSalesforceCRMContentUserCheckBox(60), "Salesforce CRM Content User check Box",
														action.SCROLLANDBOOLEAN)){
													appLog.info("Clicked on Content User Check Box");
												}else{
													appLog.error("Not able to click on content user checkbox");
												}
												if (click(driver, getSaveButton(60), "Save Button",
														action.SCROLLANDBOOLEAN)) {
													appLog.info("clicked on save button");
													appLog.info("CRM User is created successfully: " + userfirstname
															+ " " + userlastname);
													return true;
												} else {
													appLog.error(
															"Not able to click on save buttton so cannot create user: "
																	+ userfirstname + " " + userlastname);
												}
											} else {
												appLog.error("Not able to select profile from drop downlist: "
														+ userProfile + " so cannot create user: " + userfirstname + " "
														+ userlastname);
											}

										} else {
											appLog.error("Not able to select user license from drop downlist: "
													+ userLicense + " so cannot create user: " + userfirstname + " "
													+ userlastname);
										}

									} else {
										appLog.error("Not able to pass email id in text box: " + email
												+ " so cannot create user: " + userfirstname + " " + userlastname);
									}

								} else {
									appLog.error("Not able to pass user last name in text box: " + userlastname
											+ " so cannot create user: " + userfirstname + " " + userlastname);
								}
							} else {
								appLog.error("Not able pass user first name in text box: " + userfirstname
										+ " so cannot create user: " + userfirstname + " " + userlastname);
							}

						} else {
							appLog.error("Not able to click on new user button so cannot create user: " + userfirstname
									+ " " + userlastname);
						}

					} else {
						appLog.error("Not able to click on users link so cannot create user: " + userfirstname + " "
								+ userlastname);
					}

				} else {
					appLog.error("Not able to click on manage user expand icon so cannot create user: " + userfirstname
							+ " " + userlastname);
				}

			} else {
				appLog.error(
						"Not able to click on setup link so cannot create user: " + userfirstname + " " + userlastname);
			}

		} else {
			appLog.error(
					"Not able to click on user menu tab so cannot create user: " + userfirstname + " " + userlastname);
		}
		return false;
	}
	
	
	
	
	//Lightning Method...............
	/**
	 * @author Ankit Jaiswal
	 * @param userfirstname
	 * @param userlastname
	 * @param email
	 * @param userLicense
	 * @param userProfile
	 * @return true/false
	 */
	public boolean createPEUser(String environment, String mode,String userfirstname, String userlastname, String email, String userLicense,
			String userProfile) {
			if (click(driver, getExpandUserIcon(environment, mode, 30), "expand User Icon", action.SCROLLANDBOOLEAN)) {
				appLog.info("clicked on user expand icon");
				if (click(driver, getUsersLink(environment, mode, 30), "User Link", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on users link");
					if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						switchToFrame(driver, 20, getSetUpPageIframe(20));
					}
					ThreadSleep(2000);
					if (click(driver, getNewUserLink(20), "New User Button", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on new users button");
						ThreadSleep(2000);
						if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
							switchToDefaultContent(driver);
							switchToFrame(driver, 20, getSetUpPageIframe(20));
							System.err.println(">>><<<<<<<<<<<<");
						}else{
							System.err.println(">>>11111111111111<<<<<<<<<<<<");	
						}
						if (sendKeys(driver, getUserFirstName(60), userfirstname, "User First Name",
								action.SCROLLANDBOOLEAN)) {
							if (sendKeys(driver, getUserLastName(60), userlastname, "User Last Name",
									action.SCROLLANDBOOLEAN)) {
								if (sendKeys(driver, getUserEmailId(60), email, "User Email Id",
										action.SCROLLANDBOOLEAN)) {
									if (selectVisibleTextFromDropDown(driver, getUserUserLicenseDropDownList(60),
											"User License drop down list", userLicense)) {
										appLog.info("select user license from drop downlist: " + userLicense);
										if (selectVisibleTextFromDropDown(driver, getUserProfileDropDownList(60),
												"User profile drop down list", userProfile)) {
											appLog.info("select user profile from drop downlist: " + userProfile);
											if(click(driver, getSalesforceCRMContentUserCheckBox(60), "Salesforce CRM Content User check Box",
													action.SCROLLANDBOOLEAN)){
												if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
													if (click(driver, getCreateUserSaveBtn_Lighting(30), "Save Button",
															action.SCROLLANDBOOLEAN)) {
														appLog.info("clicked on save button");
														appLog.info("CRM User is created successfully: " + userfirstname
																+ " " + userlastname);
														return true;
													} else {
														appLog.error(
																"Not able to click on save buttton so cannot create user: "
																		+ userfirstname + " " + userlastname);
													}
												}else {
													if (click(driver, getSaveButton(environment, mode, 20), "Save Button",
															action.SCROLLANDBOOLEAN)) {
														appLog.info("clicked on save button");
														appLog.info("CRM User is created successfully: " + userfirstname
																+ " " + userlastname);
														return true;
													} else {
														appLog.error(
																"Not able to click on save buttton so cannot create user: "
																		+ userfirstname + " " + userlastname);
													}
												}
											}else{
												appLog.info("Not able to click on content user checkbox");
											}
										} else {
											appLog.error("Not able to select profile from drop downlist: "
													+ userProfile + " so cannot create user: " + userfirstname + " "
													+ userlastname);
										}
										
									} else {
										appLog.error("Not able to select user license from drop downlist: "
												+ userLicense + " so cannot create user: " + userfirstname + " "
												+ userlastname);
									}
									
								} else {
									appLog.error("Not able to pass email id in text box: " + email
											+ " so cannot create user: " + userfirstname + " " + userlastname);
								}
								
							} else {
								appLog.error("Not able to pass user last name in text box: " + userlastname
										+ " so cannot create user: " + userfirstname + " " + userlastname);
							}
						} else {
							appLog.error("Not able pass user first name in text box: " + userfirstname
									+ " so cannot create user: " + userfirstname + " " + userlastname);
						}
						
					} else {
						appLog.error("Not able to click on new user button so cannot create user: " + userfirstname
								+ " " + userlastname);
					}
					
				} else {
					appLog.error("Not able to click on users link so cannot create user: " + userfirstname + " "
							+ userlastname);
				}
				
			} else {
				appLog.error("Not able to click on manage user expand icon so cannot create user: " + userfirstname
						+ " " + userlastname);
			}
			if(mode.equalsIgnoreCase(Mode.Lightning.toString())){
				switchToDefaultContent(driver);
			}
		return false;
	}

	/**
	 * @author Ankit Jaiswal
	 * @param firstName
	 * @param lastName
	 * @return true/false
	 */
	public boolean installedPackages(String firstName, String lastName) {
		if (click(driver, getUserMenuTab(60), "User Menu Button", action.SCROLLANDBOOLEAN)) {
			appLog.info("clicked on user menu tab");
			if (click(driver, getUserMenuSetupLink(60), "Setup link", action.SCROLLANDBOOLEAN)) {
				appLog.info("clicked on setup link");
				if (click(driver, getInstalledpackageLink(60), "Installed Package link", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on installed package link");
					if (click(driver, getManageLicensesLink(60), "Manage licenses link", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on manage licenses link");
						if (click(driver, getAddUsersbutton(60), "Add Users link", action.BOOLEAN)) {
							appLog.info("clicked on add users button");
							if (switchToFrame(driver, 30, getInstalledPackageFrame(30))) {
								for (int i = 0; i < 2; i++) {
									if (click(driver, getActiveUserTab(60), "Active Users Tab",
											action.SCROLLANDBOOLEAN)) {
										appLog.info("Clicked on active user tab ");
									} else {
										if (i == 1) {
											switchToDefaultContent(driver);
											appLog.error("Not able to click on active user tab");
										}
									}
								}
								WebElement ele = FindElement(driver,
										"//img[@title='Checked']/../..//span[contains(text(),'" + lastName + ", "
												+ firstName + "')]/../..//input",
										"Activate User Check Box", action.BOOLEAN, 20);
								if (ele != null) {
									for (int i = 0; i < 2; i++) {
										if (click(driver, ele, firstName + " " + lastName + " check box",
												action.BOOLEAN)) {
											ThreadSleep(2000);
											WebElement checkBox = FindElement(driver,
													"//img[@title='Checked']/../..//span[contains(text(),'" + lastName
															+ ", " + firstName + "')]/../..//input",
													"Activate User Check Box", action.BOOLEAN, 20);
											if (isSelected(driver, checkBox,
													firstName + " " + lastName + " check box")) {
												appLog.info("clicked on user check box: " + firstName + " " + lastName);
												switchToDefaultContent(driver);
												if (click(driver, getActiveUserAddButton(60), "Active User Add Button",
														action.BOOLEAN)) {
													appLog.info("clicked on add button");
													appLog.info("package is installed successfully: " + firstName + " "
															+ lastName);
													return true;

												} else {
													switchToDefaultContent(driver);
													appLog.error(
															"Not able to click on add button so cannot install user package: "
																	+ firstName + " " + lastName);
												}
											} else {
												if (i == 1) {
													switchToDefaultContent(driver);
													appLog.error(
															"username checkbox is not selected in istalled package : "
																	+ firstName + " " + lastName);
												}
											}
										} else {
											if (i == 1) {
												switchToDefaultContent(driver);
												appLog.error("Not able to click on user check box: " + firstName + " "
														+ lastName);
											}
										}
									}
								} else {
									switchToDefaultContent(driver);
									appLog.error("create user " + firstName + " " + lastName
											+ " is not visible so cannot istall user package: " + firstName + " "
											+ lastName);
								}
							} else {
								switchToDefaultContent(driver);
								appLog.error("installed package frame is not loaded so cannot install user package: "
										+ firstName + " " + lastName);
							}
						} else {
							appLog.error("Not able to click on add users button so cannot install user package: "
									+ firstName + " " + lastName);
						}
					} else {
						appLog.error("Not able to click on manage licenses link so cannot install user package: "
								+ firstName + " " + lastName);
					}
				} else {
					appLog.error("Not able to click on installed packages link so cannot istall user package: "
							+ firstName + " " + lastName);
				}
			} else {
				appLog.error(
						"Not able to click on setp link so cannot install user package: " + firstName + " " + lastName);
			}
		} else {
			appLog.error(
					"Not able to click on user menu tab so cannot intsall user package: " + firstName + " " + lastName);
		}
		return false;
	}
	
	//Lightning Method ...................
	public boolean installedPackages(String environment, String mode,String firstName, String lastName) {
		if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			switchToDefaultContent(driver);
			if(sendKeys(driver,getQucikSearchInSetupPage(environment, mode, 30),"Installed package","quick search text box in setup page", action.SCROLLANDBOOLEAN)) {
				log(LogStatus.INFO, "passed value in serach text box installed package ", YesNo.No);
				
			}else {
				log(LogStatus.INFO, "Not able to search installed package in search text box so cannot click on installed package link in lightning", YesNo.Yes);
				return false;
			}
		}
		if (click(driver, getInstalledPackageLink(environment, mode, 30), "Installed Package link", action.SCROLLANDBOOLEAN)) {
			log(LogStatus.INFO,"clicked on installed package link", YesNo.No);
			if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				switchToFrame(driver, 30, getSetUpPageIframe(30));
			}
			if (click(driver, getManageLicensesLink(60), "Manage licenses link", action.SCROLLANDBOOLEAN)) {
				log(LogStatus.INFO,"clicked on manage licenses link", YesNo.No);
				if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					switchToDefaultContent(driver);
					switchToFrame(driver, 30, getSetUpPageIframe(30));
				}
				if (click(driver, getAddUsersbutton(60), "Add Users link", action.BOOLEAN)) {
					log(LogStatus.INFO,"clicked on add users button", YesNo.No);
					if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						switchToDefaultContent(driver);
					}
					if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						switchToFrame(driver, 30, getInstalledPackageParentFrame_Lighting(20));
					}
					if (switchToFrame(driver, 30, getInstalledPackageFrame(20))) {
						appLog.info("Inside Installerd Package Frame");
						for (int i = 0; i < 2; i++) {
							if (click(driver, getActiveUserTab(60), "Active Users Tab",
									action.SCROLLANDBOOLEAN)) {
								log(LogStatus.INFO,"Clicked on active user tab ", YesNo.No);
							} else {
								if (i == 1) {
									switchToDefaultContent(driver);
									log(LogStatus.INFO,"Not able to click on active user tab", YesNo.Yes);
								}
							}
						}
						WebElement ele = FindElement(driver,
								"//img[@title='Checked']/../..//span[contains(text(),'" + lastName + ", "
										+ firstName + "')]/../..//input",
										"Activate User Check Box", action.BOOLEAN, 20);
						if (ele != null) {
							for (int i = 0; i < 2; i++) {
								if (click(driver, ele, firstName + " " + lastName + " check box",
										action.BOOLEAN)) {
									ThreadSleep(2000);
									WebElement checkBox = FindElement(driver,
											"//img[@title='Checked']/../..//span[contains(text(),'" + lastName
											+ ", " + firstName + "')]/../..//input",
											"Activate User Check Box", action.BOOLEAN, 20);
									if (isSelected(driver, checkBox,
											firstName + " " + lastName + " check box")) {
										log(LogStatus.INFO,"clicked on user check box: " + firstName + " " + lastName, YesNo.No);
										if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
											switchToDefaultContent(driver);
											switchToFrame(driver, 30, getInstalledPackageParentFrame_Lighting(20));
										}else {
											switchToDefaultContent(driver);
										}
										if (click(driver, getActiveUserAddButton(60), "Active User Add Button",
												action.BOOLEAN)) {
											log(LogStatus.INFO,"clicked on add button", YesNo.No);
											log(LogStatus.INFO,"package is installed successfully: " + firstName + " "
													+ lastName, YesNo.No);
											switchToDefaultContent(driver);
											return true;

										} else {
											switchToDefaultContent(driver);
											log(LogStatus.INFO,"Not able to click on add button so cannot install user package: "
													+ firstName + " " + lastName, YesNo.Yes);
										}
									} else {
										if (i == 1) {
											switchToDefaultContent(driver);
											log(LogStatus.INFO,"username checkbox is not selected in istalled package : "
													+ firstName + " " + lastName, YesNo.Yes);
										}
									}
								} else {
									if (i == 1) {
										switchToDefaultContent(driver);
										log(LogStatus.INFO,"Not able to click on user check box: " + firstName + " "
												+ lastName, YesNo.Yes);
									}
								}
							}
						} else {
							switchToDefaultContent(driver);
							log(LogStatus.INFO,"create user " + firstName + " " + lastName
									+ " is not visible so cannot istall user package: " + firstName + " "
									+ lastName, YesNo.Yes);
						}
					} else {
						switchToDefaultContent(driver);
						log(LogStatus.INFO,"installed package frame is not loaded so cannot install user package: "
								+ firstName + " " + lastName, YesNo.Yes);
					}
				} else {
					log(LogStatus.INFO,"Not able to click on add users button so cannot install user package: "
							+ firstName + " " + lastName, YesNo.Yes);
					if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						switchToDefaultContent(driver);
					}
				}
			} else {
				log(LogStatus.INFO,"Not able to click on manage licenses link so cannot install user package: "
						+ firstName + " " + lastName, YesNo.Yes);
				if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					switchToDefaultContent(driver);
				}
			}
		} else {
			log(LogStatus.INFO,"Not able to click on installed packages link so cannot istall user package: "
					+ firstName + " " + lastName, YesNo.Yes);
		}
		if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			switchToDefaultContent(driver);
		}
		return false;
	}
	
	
	public boolean searchStandardOrCustomObject(String environment, String mode, object objectName) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())) {
			if(sendKeys(driver,getQucikSearchInSetupPage(environment, mode, 30),objectName.toString(),"quick search text box in setup page", action.SCROLLANDBOOLEAN)) {
				appLog.info("passed value in serach text box: "+objectName);
				return true;
			}else {
				appLog.error("Not able to search object in classic : "+objectName);
			}
		}else {
			if (click(driver, getObjectManager_Lighting(30), "object manager tab", action.SCROLLANDBOOLEAN)) {
				appLog.info("clicked on object manager tab");
				if(sendKeys(driver, getQuickSearchInObjectManager_Lighting(30), objectName.toString(), "quick search text box in lighting", action.SCROLLANDBOOLEAN)) {
					appLog.info("passed value in quick search text box: "+ objectName);
					return true;
				}else {
					appLog.error("Not able to search object in lighting : "+objectName);
				}
			} else {
				appLog.error("Not able to click on object manager tab so cannot search object: "+objectName);
			}
	}
		return false;
}
	
	
	
	public boolean quickSearchOnSetupHomePage(String environment, String mode, object objectName) {
		if(sendKeys(driver,getQucikSearchInSetupPage(environment, mode, 30),objectName.toString(),"quick search text box in setup page", action.SCROLLANDBOOLEAN)) {
			appLog.info("passed value in serach text box: "+objectName);
			return true;
		}else {
			appLog.error("Not able to search object in classic : "+objectName);
		}
		return false;
	}
	
	
	/**
	 * @author Ankit Jaiswal
	 * @description- This method is used to set new password for CRM Users
	 */
	public boolean setNewPassword() {
		try {
			Assert.assertTrue(getChnageYourPassword(60).getText().trim().contains("Change Your Password"),
					"Change Your Password text is not verified");
		} catch (Exception e) {
			driver.navigate().refresh();
			e.printStackTrace();
		}
		appLog.info("Password To Be Entered: " + ExcelUtils.readDataFromPropertyFile("password"));
		if (sendKeys(driver, getNewPassword(60), ExcelUtils.readDataFromPropertyFile("password"),
				"New Password Text box", action.SCROLLANDBOOLEAN)) {
			appLog.info("Password Entered: " + getNewPassword(10).getAttribute("value"));
			appLog.info("Confirm Password To Be Entered: " + ExcelUtils.readDataFromPropertyFile("password"));
			ThreadSleep(5000);
			if (sendKeys(driver, getConfimpassword(60), ExcelUtils.readDataFromPropertyFile("password"),
					"Confirm Password text Box", action.SCROLLANDBOOLEAN)) {
				appLog.info("Confirm Password Entered: " + getConfimpassword(60).getAttribute("value"));
				CommonLib.selectVisibleTextFromDropDown(driver, getQuestion(60), "In what city were you born?",
						"Question drop down list");
				sendKeys(driver, getAnswer(60), "New York", "Answer Text Box", action.SCROLLANDBOOLEAN);
				ThreadSleep(5000);
				if (click(driver, getChangePassword(60), "Chnage Password Button", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on change password button");
					appLog.info("CRM User Password is set successfully.");
					return true;
				} else {
					appLog.error("Not able to click on change password button so cannot set user password");
				}

			} else {
				appLog.error("Not able to exter confirm password in text box so cannot set user password");
			}
		} else {
			appLog.error("Not able to exter password in text box so cannot set user password");
		}
		return false;
	}

	/**
	 * @author Ankit Jaiswal
	 * @param addRemoveTabName
	 * @param customTabActionType
	 * @return list
	 */
	public List<String> addRemoveCustomTab(String addRemoveTabName, customTabActionType customTabActionType) {
		List<String> result = new ArrayList<String>();
		String[] splitedTabs = addRemoveTabName.split(",");
		if (click(driver, getAllTabBtn(60), "All Tab Button", action.SCROLLANDBOOLEAN)) {
			appLog.info("clicked on all tabs icon");
			if (click(driver, getAddTabLink(60), "Add a Tab Link", action.SCROLLANDBOOLEAN)) {
				appLog.info("clicked on add a tab link");
				if (customTabActionType.toString().equalsIgnoreCase("Add")) {
					System.err.println("inside Add");
					for (int i = 0; i < splitedTabs.length; i++) {
						if (selectVisibleTextFromDropDown(driver, getAvailableTabList(60), "Available Tab List",
								splitedTabs[i])) {
							appLog.info(splitedTabs[i] + " is selected successfully in available tabs");
							if (click(driver, getCustomTabAddBtn(60), "Custom Tab Add Button",
									action.SCROLLANDBOOLEAN)) {
								appLog.error("clicked on add button");
							} else {
								result.add("Not able to click on add button so cannot add custom tabs");
								appLog.error("Not able to click on add button so cannot add custom tabs");
							}
						} else {
							appLog.error(splitedTabs[i] + " custom tab name is not Available list Tab.");
							result.add(splitedTabs[i] + " custom tab name is not Available list Tab.");
						}
					}
				} else if (customTabActionType.toString().equalsIgnoreCase("Remove")) {
					System.err.println("inside remove");
					for (int i = 0; i < splitedTabs.length; i++) {
						if (selectVisibleTextFromDropDown(driver, getCustomTabSelectedList(60), "Selected Tab List",
								splitedTabs[i])) {
							appLog.info(splitedTabs[i] + " is selected successfully in Selected tabs");
							if (click(driver, getCustomTabRemoveBtn(60), "Remove Button", action.SCROLLANDBOOLEAN)) {
								appLog.error("clicked on remove button");
							} else {
								result.add("Not able to click on add button so cannot add custom tabs");
								appLog.error("Not able to click on add button so cannot add custom tabs");
							}
						} else {
							appLog.error(splitedTabs[i] + " custom tab name is not selected list Tab.");
							result.add(splitedTabs[i] + " custom tab name is not selected list Tab.");
						}
					}
				} else {
					result.add(
							"custom tab action type is not mtached so cannot add or remove custom tab please pass correct arrgument");
					appLog.error(
							"custom tab action type is not mtached so cannot add or remove custom tab please pass correct arrgument");
				}

				if (click(driver, getCustomTabSaveBtn(60), "Custom Tab Save Button", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on save button");

				} else {
					result.add("Not able to click on save button so cannot save custom tabs");
					appLog.error("Not able to click on save button so cannot save custom tabs");
				}

			} else {
				result.add("Not able to click on add a tab link so cannot add custom tabs");
				appLog.error("Not able to click on add a tab link so cannot add custom tabs");
			}
		} else {
			result.add("Not able to click on all tabs icon so cannot add custom tabs");
			appLog.error("Not able to click on all tabs icon so cannot add custom tabs");
		}
		return result;
	}

	/**
	 *  @author Ankur Rana
	 * @return random number
	 */
	public String generateRandomNumber() {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(99999);
		String RandomNumber = String.valueOf(randomInt);
		return RandomNumber;
	}

	/**
	 * @author Parul Singh
	 * @param errorMessage
	 * @param errorMessageElement
	 * @param elementName
	 * @return true/false
	 */
	public boolean verifyErrorMessageOnPage(String errorMessage, WebElement errorMessageElement, String elementName) {
		System.err.println(errorMessageElement.getText());
		String actualResult = errorMessageElement.getText().trim();
		System.out.println(">>>>>>>>>>>>>>" + actualResult);
		if (actualResult.contains(errorMessage)) {
			appLog.info(elementName);
			return true;
		}
		return false;
	}

	/**
	 * @author Ankit Jaiswal
	 * @param firstName
	 * @param lastName
	 * @param emailId
	 * @param firmName
	 * @param password
	 * @return true/false
	 */
	public boolean targetRegistration(String firstName, String lastName, String emailId, String firmName,
			String password) {
		String fName = firstName.substring(0, 3);
		String lName = lastName.substring(0, 3);
		if (sendKeys(driver, getTargetFirstName(60), firstName, "first name text box", action.BOOLEAN)) {
			if (sendKeys(driver, getTargetlastName(30), lastName, "last name text box", action.BOOLEAN)) {
				if (sendKeys(driver, getTargetNickName(30), fName + lName + generateRandomNumber(),
						"nick name text box", action.BOOLEAN)) {
					if (emailId != null) {
						if (sendKeys(driver, getTargetEmailId(30), emailId, "email id text box", action.BOOLEAN)) {
						} else {
							appLog.error("Not able to pass value in email id text box so cannot register user: "
									+ firstName + " " + lastName);
						}
					}
					if (sendKeys(driver, getTargetFirmName(30), firmName, "firm name text box", action.BOOLEAN)) {
						if (sendKeys(driver, getTargetpassword(30), password, "password text box", action.BOOLEAN)) {
							if (sendKeys(driver, getTargetConfirmPassword(30), password, "confirm password text box",
									action.BOOLEAN)) {
								if (click(driver, getTargetSignUpBtn(30), "signUp button", action.SCROLLANDBOOLEAN)) {
									if (!isAlertPresent(driver)) {
										appLog.info("" + firstName + " " + lastName + " is successfully registered");
										return true;
									} else {
										String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
										switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
										appLog.error("Error Message is displaying : "+msg);
									}

								} else {
									appLog.error("Not able to click on signUp button so cannot register user: "
											+ firstName + " " + lastName);
								}
							} else {
								appLog.error(
										"Not able to pass value in confirm password text box so cannot register user: "
												+ firmName + " " + lastName);
							}
						} else {
							appLog.error("Not able to pass value in password text box so cannot register user: "
									+ firstName + " " + lastName);
						}
					} else {
						appLog.error("Not able to pass value in firm name text box so cannot register user: "
								+ firstName + " " + lastName);
					}
				} else {
					appLog.error("Not able to pass value in nick name text box so cannot register user: " + firstName
							+ " " + lastName);
				}
			} else {
				appLog.error("Not able to pass value in last name text box so cannot register user: " + firstName + " "
						+ lastName);
			}
		} else {
			appLog.error("Not able to pass value in first name text box so cannot register user :" + firmName + " "
					+ lastName);
		}
		return false;

	}

	/**
	 * @author Ankur Rana
	 * @param sortOrder
	 * @param elements
	 * @return true/false
	 */
	public boolean checkSorting(SortOrder sortOrder, List<WebElement> elements) {
		List<String> ts = new ArrayList<String>();
		List<String> actual = new ArrayList<String>();
		CommonLib compare = new CommonLib();
		List<WebElement> ele = elements;
		boolean flag = true;
		int j = 0;
		for (int i = 0; i < ele.size(); i++) {
			scrollDownThroughWebelement(driver, ele.get(i), "");
			ts.add(ele.get(i).getText());
		}
		actual.addAll(ts);
		Collections.sort(ts, compare);
		Iterator<String> i = ts.iterator();
		if (sortOrder.toString().equalsIgnoreCase("Decending")) {
			j = ele.size() - 1;
		}
		while (i.hasNext()) {
			String a = i.next();
			if (a.equalsIgnoreCase(actual.get(j))) {
				appLog.info("Order of column is matched " + "Expected: " + a + "\tActual: " + actual.get(j));
			} else {
				appLog.info("Order of column din't match. " + "Expected: " + a + "\tActual: " + actual.get(j));
				BaseLib.sa.assertTrue(false, "coloumn is not sorted in " + sortOrder.toString() + " order"
						+ "Expected: " + a + "\tActual: " + actual.get(j));
				flag = false;
			}
			if (sortOrder.toString().equalsIgnoreCase("Decending")) {
				j--;
			} else {
				j++;
			}
		}
		return flag;
	}

	/**
	 * @author Parul Singh
	 * @param viewUser
	 * @param userFirstName
	 * @param userLastName
	 * @param userStatus
	 * @return true/false
	 */
	public boolean deactivateAndActivateCreatedUser(String viewUser, String userFirstName, String userLastName,
			String userStatus) {
		if (click(driver, getUserMenuTab(120), "User Menu Button", action.SCROLLANDBOOLEAN)) {
			if (click(driver, getUserMenuSetupLink(120), "Setup Link", action.SCROLLANDBOOLEAN)) {
				ThreadSleep(1000);
				if (click(driver, getExpandManageUserIcon(120), "Manage User Icon", action.SCROLLANDBOOLEAN)) {
					if (click(driver, getUsersLink(120), "User Link", action.SCROLLANDBOOLEAN)) {
						if (selectVisibleTextFromDropDown(driver, getViewAllDropdownList(120), "View dropdown list",
								viewUser)) {
							ThreadSleep(2000);
							if (click(driver, getLastLogin(120), "Last Login", action.SCROLLANDBOOLEAN)) {
								if (click(driver, getLastLogin(120), "Last Login", action.SCROLLANDBOOLEAN)) {
									WebElement ele = FindElement(driver,
											"//a[text()='" + userLastName + "," + " " + userFirstName
													+ "']/..//preceding-sibling::td//a",
											"Edit icon", action.SCROLLANDBOOLEAN, 120);
									if (ele != null) {
										if (click(driver, ele, "Edit icon", action.SCROLLANDBOOLEAN)) {
											if (click(driver, getActiveCheckboxInUserCreation(120),
													"Active User Checkbox in User Creation", action.BOOLEAN)) {
												if (userStatus.equalsIgnoreCase("Deactivate")) {
													switchToAlertAndAcceptOrDecline(driver, 120, action.ACCEPT);
												}
												if (click(driver, getSaveButton(120), "Save Button",
														action.SCROLLANDBOOLEAN)) {
													appLog.info("Clicked on save button");
													return true;
												} else {
													appLog.info("Not able to click on save button");
												}

											} else {
												appLog.info("Not able to click on activate checkbox");
											}

										} else {
											appLog.info("Not able to click on edit icon");
										}
									} else {
										appLog.info("Element is not present");
									}

								} else {
									appLog.info("Not able to click on Last Login");
								}
							} else {
								appLog.info("Not able to click on Last Login");
							}
						} else {
							appLog.info("Not able to select visbible text from the dropdown");
						}
					} else {
						appLog.info("Not able to click on users Link");
					}
				} else {
					appLog.info("Not able to click on Manage User Icon");
				}
			} else {
				appLog.info("Not able to click on Setup link");
			}
		} else {
			appLog.info("Not able to click on user menu tab");
		}
		return false;
	}
	
	
	
	//Lightning Method.....
	/**
	 * @author Parul Singh
	 * @param viewUser
	 * @param userFirstName
	 * @param userLastName
	 * @param userStatus
	 * @return true/false
	 */
	public boolean deactivateAndActivateCreatedUser(String environment, String mode,String viewUser, String userFirstName, String userLastName,
			String userStatus) {
	
		if(quickSearchOnSetupHomePage(environment, mode, object.Users)) {
			if (click(driver, getUsersLink(environment, mode, 30), "User Link", action.SCROLLANDBOOLEAN)) {
				if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					ThreadSleep(3000);
					switchToFrame(driver, 20, getSetUpPageIframe(20));
				}
				if (selectVisibleTextFromDropDown(driver, getViewAllDropdownList(20), "View dropdown list",
						viewUser)) {
					if (click(driver, getLastLogin(10), "Last Login", action.SCROLLANDBOOLEAN)) {
						if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
							ThreadSleep(3000);
							switchToFrame(driver, 20, getSetUpPageIframe(20));
						}
						if (click(driver, getLastLogin(10), "Last Login", action.SCROLLANDBOOLEAN)) {
							if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
								ThreadSleep(3000);
								switchToFrame(driver, 20, getSetUpPageIframe(20));
							}
							WebElement ele = FindElement(driver,
									"//a[text()='" + userLastName + "," + " " + userFirstName
									+ "']/..//preceding-sibling::td//a",
									"Edit icon", action.SCROLLANDBOOLEAN, 10);
							if (ele != null) {
								if (click(driver, ele, "Edit icon", action.SCROLLANDBOOLEAN)) {
									if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
										ThreadSleep(3000);
										switchToFrame(driver, 20, getSetUpPageIframe(20));
									}
									if (CommonLib.clickUsingCssSelectorPath(driver, CssPath.cssPathForUserCreationActiveCheckBox, "Active check box")) {
										if (userStatus.equalsIgnoreCase("Deactivate")) {
											switchToAlertAndAcceptOrDecline(driver, 20, action.ACCEPT);
										}
										if (CommonLib.clickUsingCssSelectorPath(driver,CssPath.cssPathForUserCreationSaveButton, "Save button")) {
											appLog.info("Clicked on save button");
											switchToDefaultContent(driver);
											return true;
										} else {
											appLog.info("Not able to click on save button");
										}

									} else {
										appLog.info("Not able to click on activate checkbox");
									}

								} else {
									appLog.info("Not able to click on edit icon");
								}
							} else {
								appLog.info("Element is not present");
							}

						} else {
							appLog.info("Not able to click on Last Login");
						}
					} else {
						appLog.info("Not able to click on Last Login");
					}
				} else {
					appLog.info("Not able to select visbible text from the dropdown");
				}
			} else {
				appLog.info("Not able to click on users Link");
			}
		} else {
			appLog.info("Not able to click on Manage User Icon");
		}
		switchToDefaultContent(driver);
		return false;
	}
	
	/**
	 * @author Parul Singh
	 * @param userFirstName
	 * @param userLastName
	 * @param userStatus
	 * @return true/false
	 */
	public boolean verifyUserGetDeactivatedAndActivated(String userFirstName, String userLastName, String userStatus) {
		if (click(driver, getUserMenuTab(120), "User Menu Button", action.SCROLLANDBOOLEAN)) {
			if (click(driver, getUserMenuSetupLink(120), "Setup Link", action.SCROLLANDBOOLEAN)) {
				ThreadSleep(1000);
				if (click(driver, getExpandManageUserIcon(120), "Manage User Icon", action.SCROLLANDBOOLEAN)) {
					if (click(driver, getUsersLink(120), "User Link", action.SCROLLANDBOOLEAN)) {
						if (selectVisibleTextFromDropDown(driver, getViewAllDropdownList(120), "View dropdown list",
								"Active Users")) {
							ThreadSleep(2000);
									if (userStatus.equalsIgnoreCase("Deactivate")) {
										ThreadSleep(2000);
										if (FindElement(driver, "//a[text()='" + userLastName + "," + " " + userFirstName + "']/../..",
												"Deactivated User", action.SCROLLANDTHROWEXCEPTION, 20) == null) {
											appLog.info("User is Deactivated");
											} else {
											appLog.info("User is not Deactivated");
											return false;
										}
									}			
									if (userStatus.equalsIgnoreCase("Activate")) {
										ThreadSleep(2000);
										if (FindElement(driver, "//a[text()='" + userLastName + "," + " " + userFirstName + "']/../..",
												"Activated User", action.SCROLLANDTHROWEXCEPTION, 20) != null) {
											appLog.info("User is Activated");
											} else {
											appLog.info("User is not Activated");
											return false;
										}
									}	
							} else {
							appLog.info("Not able to select visbible text from the dropdown");
						}
					} else {
						appLog.info("Not able to click on users Link");
					}
				} else {
					appLog.info("Not able to click on Manage User Icon");
				}
			} else {
				appLog.info("Not able to click on Setup link");
			}
		} else {
			appLog.info("Not able to click on user menu tab");
		}
	return true;
	
	
	}
	
	
	//Lightning Method....
	/**
	 * @author Parul Singh
	 * @param userFirstName
	 * @param userLastName
	 * @param userStatus
	 * @return true/false
	 */
	public boolean verifyUserGetDeactivatedAndActivated(String environment, String mode,String userFirstName, String userLastName, String userStatus) {
		if (quickSearchOnSetupHomePage(environment, mode, object.Users)) {
			if (click(driver, getUsersLink(environment, mode, 30), "User Link", action.SCROLLANDBOOLEAN)) {
				if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					ThreadSleep(3000);
					switchToFrame(driver, 20, getSetUpPageIframe(20));
				}
				if (selectVisibleTextFromDropDown(driver, getViewAllDropdownList(20), "View dropdown list",
						"Active Users")) {
					ThreadSleep(2000);
					if (userStatus.equalsIgnoreCase("Deactivate")) {
						ThreadSleep(2000);
						if (FindElement(driver, "//a[text()='" + userLastName + "," + " " + userFirstName + "']/../..",
								"Deactivated User", action.SCROLLANDTHROWEXCEPTION, 5) == null) {
							appLog.info("User is Deactivated");
						} else {
							appLog.info("User is not Deactivated");
							switchToDefaultContent(driver);
							return false;
						}
					}			
					if (userStatus.equalsIgnoreCase("Activate")) {
						ThreadSleep(2000);
						if (FindElement(driver, "//a[text()='" + userLastName + "," + " " + userFirstName + "']/../..",
								"Activated User", action.SCROLLANDTHROWEXCEPTION, 5) != null) {
							appLog.info("User is Activated");
						} else {
							appLog.info("User is not Activated");
							switchToDefaultContent(driver);
							return false;
						}
					}	
				} else {
					appLog.info("Not able to select visbible text from the dropdown");
				}
			} else {
				appLog.info("Not able to click on users Link");
			}
		} else {
			appLog.info("Not able to click on Manage User Icon");
		}
		switchToDefaultContent(driver);
		return true;
	}
	
	/**
	 * @author Parul Singh
	 * @return true/false
	 */
	public boolean verifyWorkspaceCollapseOrExpanded(){
		String expandIcon = getAttribute(driver, getWorkspaceExpandIcon(60), "WorkspaceIcon", "aria-expanded");
		if (expandIcon.equalsIgnoreCase("false")) {
			if (click(driver,getWorkspaceExpandIcon(60), "Workspace Expand Icon",
					action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on workspace expand icon");
			} else {
				appLog.info("Not able to click on workspace expand icon");
				return false;
				}
		} else {
			appLog.info("Workspace is in expanded form");
		}
		return true;
	}
	
	/**
	 * @author Parul Singh
	 * @return true/false
	 */
	public boolean removeUnusedTabs(){
		WebElement ele=null;
		List<String> lst=new ArrayList<String>();
		ele=FindElement(driver, "//a[contains(@title,'Reports')]", "Reports tab",
				action.SCROLLANDBOOLEAN, 3);
	 if(ele!=null){
		 lst=addRemoveCustomTab("Reports", customTabActionType.Remove);
		 if(!lst.isEmpty()){
			 for(int i=0; i<lst.size();i++){
				 BaseLib.sa.assertTrue(false, lst.get(i));
			 }
		 }
	 }
	 ThreadSleep(1000);
	 ele=FindElement(driver, "//a[contains(@title,'Dashboards')]", "Dashboards tab",
				action.SCROLLANDBOOLEAN, 3);
	 if(ele!=null){
		 lst= addRemoveCustomTab("Dashboards", customTabActionType.Remove);
		 lst.clear();
		 if(!lst.isEmpty()){
			 for(int i=0; i<lst.size();i++){
				 BaseLib.sa.assertTrue(false, lst.get(i));
			 }
		 }
	 }	
	 ThreadSleep(1000);
	 ele=FindElement(driver, "//a[contains(@title,'Marketing')]", "Marketing Initiatives tab",
				action.SCROLLANDBOOLEAN, 3);
	 if(ele!=null){
		 lst=addRemoveCustomTab("Marketing Initiatives", customTabActionType.Remove);
		 lst.clear();
		 if(!lst.isEmpty()){
			 for(int i=0; i<lst.size();i++){
				 BaseLib.sa.assertTrue(false, lst.get(i));
			 }
		 }
	 }
	 ThreadSleep(1000);
	 ele=FindElement(driver, "//a[contains(@title,'Navatar Setup')]", "Navatar setup tab",
				action.SCROLLANDBOOLEAN, 3);
	 if(ele!=null){
		 lst=addRemoveCustomTab("Navatar Setup", customTabActionType.Remove);
		 lst.clear();
		 if(!lst.isEmpty()){
			 for(int i=0; i<lst.size();i++){
				 BaseLib.sa.assertTrue(false, lst.get(i));
			 }
		 }
	 }
	 ThreadSleep(1000);
	 ele=FindElement(driver, "//a[contains(@title,'Navatar Deal')]", "Navatar Deal connect tab",
				action.SCROLLANDBOOLEAN, 3);
	 if(ele!=null){
		 lst=addRemoveCustomTab("Navatar Deal Connect", customTabActionType.Remove);
		 lst.clear();
		 if(!lst.isEmpty()){
			 for(int i=0; i<lst.size();i++){
				 BaseLib.sa.assertTrue(false, lst.get(i));
			 }
		 }
	 }	 
	 ThreadSleep(1000);
	 ele=FindElement(driver, "//a[contains(@title,'Pipelines')]", "Pipelines tab",
				action.SCROLLANDBOOLEAN, 3);
	 if(ele!=null){
		 lst=addRemoveCustomTab("Pipelines", customTabActionType.Remove);
		 lst.clear();
		 if(!lst.isEmpty()){
			 for(int i=0; i<lst.size();i++){
				 BaseLib.sa.assertTrue(false, lst.get(i));
			 }
		 }
	 }	 
	return true;	
	}
	
	/**
	 * @author Ankur Rana
	 * @param sheetName
	 * @return Map<String, String>
	 */
	public Map<String, String> folderStructureInExcel(String sheetName) {
		int i = 2;
		Map<String, String> struct = new LinkedHashMap<String, String>();
		while (true) {
			String value = ExcelUtils.readData(sheetName, i, 0);
			if (value != null) {
				int totalValues = ExcelUtils.getLastColumn(sheetName, i);
				for (int j = 1; j < totalValues; j++) {
					String path = ExcelUtils.readData(sheetName, i, j);
					String[] paths = path.split(",");
					for (int k = 0; k < paths.length; k++) {
						struct.put(paths[k], ExcelUtils.readData(sheetName, i, 0));
					}
				}
			} else {
				break;
			}
			i++;
		}
		return struct;
	}
	
	/**
	 * @author Ankur Rana
	 * @param sheetName
	 * @param timeOut
	 * @return true/false
	 */
	public boolean createFolderStructureFromExcel(String sheetName, int timeOut){
		Map<String, String> s = folderStructureInExcel(sheetName);
		Set<String> paths = s.keySet();
		Iterator<String> i = paths.iterator();
		i = paths.iterator();
		FolderType folderType = null;
		boolean flag = true;
		while (i.hasNext()) {
			String string = i.next();
			if (string.isEmpty())
				continue;
			System.out.println("\n\n\nCreating folder template\n\n\n");
			if(s.get(string).equalsIgnoreCase("Shared")){
				folderType=FolderType.Shared;
			} else if (s.get(string).equalsIgnoreCase("Common")){
				folderType=FolderType.Common;
			} else if (s.get(string).equalsIgnoreCase("Internal")){
				folderType=FolderType.Internal;
			} else if (s.get(string).equalsIgnoreCase("Standard")){
				folderType=FolderType.Standard;
			}
			List<String> notCreatedFolders = createFolderStructure(string, folderType, Workspace.FundraisingWorkspace, PageName.NavatarInvestorManager, timeOut);
			if(notCreatedFolders.isEmpty()){
				appLog.info("Successfully created: "+string);
			} else {
				String folderNames = createStringOutOfList(notCreatedFolders);
				BaseLib.sa.assertTrue(false,"Following folders are not created: "+folderNames);
				flag = false;
			}
		}
		return flag;
	}
	
	/**
	 * @author Ankur Rana
	 * @param folderPath
	 * @param folderType
	 * @param workspace
	 * @param pageName
	 * @param timeOut
	 * @return List<String> 
	 */
	@SuppressWarnings("unused")
	public List<String> createFolderStructure(String folderPath, FolderType folderType, Workspace workspace, PageName pageName, int timeOut) {
		String folderStruct[] = folderPath.split("/");
		List<String> notCreatedFolders = new ArrayList<String>();
		String folderStructTemp=null;
		if(folderStruct[0].contains("(Common)")||folderStruct[0].contains("(Internal)")||folderStruct[0].contains("(Shared)")){
			folderStructTemp=(folderStruct[0].split("\\("))[0];
			folderStruct[0]=folderStructTemp;
		}
		int trying = 0;
		String xpath = "//span[contains(text(),'All Folders')]/span/../../../following-sibling::ul/li/div//label[contains(text(),'"
				+ folderStruct[0] + "')]";
		for (int i = 0; i < folderStruct.length; i++) {
			if (checkElementVisibility(driver, FindElement(driver, xpath, "folder", action.BOOLEAN, 0), "", 2)) {
				// if (i != folderStruct.length - 1) {
				try {
					xpath = xpath + "/../../../following-sibling::ul/li/div//label[contains(text(),'"
							+ folderStruct[i + 1] + "')]";
					System.out.println("\n\n\nSkipping the iteration: " + i + "\n\n\n\n");
				} catch (Exception e) {
					appLog.info(folderPath + " Folder structure is already present.");
				}
				continue;
				// }
			}
			if (i == 0) {
				if(!createParentFolder(folderStruct[i], folderType, pageName, workspace, timeOut)){
					notCreatedFolders.add(folderStruct[i]);
				}
			} else {
				int len = xpath.length();
				String spitby = "/../../../following-sibling::ul/li/div//label[contains(text(),'" + folderStruct[i]
						+ "')]";
				int len1 = spitby.length();
				int lenReq = len - len1;
				String xp = xpath.substring(0, lenReq);
				ec:System.out.println("Value of i: " + i + "XPath after split: " + xp);
				String id = FindElement(driver, xp, "", action.BOOLEAN, 10).getAttribute("id");
				if (!clickOnAddFolderButton(id)) {
					System.err.println("Returning false.");
				}
				if(!createChildFolder(folderStruct[i], workspace, pageName, timeOut)){
					notCreatedFolders.add(folderStruct[i]);
					appLog.error(trying+" Count number when not matched.");
					System.err.println(trying+" Count number when not matched.");
					if(trying!=0){
						trying++;
						GOTO ec;
					}
				}
			}
			if (i != folderStruct.length - 1) {
				xpath = xpath + "/../../../following-sibling::ul/li/div//label[contains(text(),'" + folderStruct[i + 1]
						+ "')]";
				System.out.println("xpath before split " + i + " creation: " + xpath);
			}
		}
		return notCreatedFolders;
	}

	/**
	 * @author Ankit Jaiswal
	 * @param id
	 * @return true/false
	 */
	public boolean clickOnAddFolderButton(String id) {
		((JavascriptExecutor) driver).executeScript("document.getElementById('" + "add" + id.substring(3, id.length())
				+ "').setAttribute('style', 'height: 8px; position: absolute; padding: 12px 22px 0px; margin-left: 22px; display: inline-block;');");
		scrollDownThroughWebelement(driver, FindElement(driver,
				"//label[@id='" + id + "']//span[@title='Add a Folder']", "Add folder Button", action.BOOLEAN, 20),
				"Add Folder Button");
		return clickUsingJavaScript(driver, FindElement(driver, "//label[@id='" + id + "']//span[@title='Add a Folder']",
				"Add folder Button", action.BOOLEAN, 20), "Add a folder", action.BOOLEAN);
	}

	/**
	 * @author Ankit Jaiswal
	 * @param folderName
	 * @param folderType
	 * @param pageName
	 * @param workspace
	 * @param timeOut
	 * @return true/false
	 */
	public boolean createParentFolder(String folderName, FolderType folderType, PageName pageName, Workspace workspace,
			int timeOut) {
		WebElement ele = null;
		for (int i = 0; i < 2; i++)
			ele = FindElement(driver, "//span[@id='add0000'][@title='Add a Folder']", "+Icon",
					action.SCROLLANDBOOLEAN, timeOut);
		ThreadSleep(1000);
			scrollDownThroughWebelement(driver, ele, "Add");
			ThreadSleep(3000);
			if (clickUsingJavaScript(driver, ele, "Add >", action.SCROLLANDBOOLEAN)) {
				if (click(driver, getFolderTypeRadioButton(folderType, workspace, pageName, timeOut),
						folderType.toString() + " Folder Radio Button", action.BOOLEAN)) {
					if (sendKeys(driver, getParentFolderNameTextBox(workspace, pageName, timeOut), folderName,
							"folder name textbox", action.BOOLEAN)) {
						if (click(driver, getParentFolderSaveButton(workspace, pageName, timeOut),
								"Parent folder Save Button folder creation", action.BOOLEAN)) {
							appLog.info("Folder successfully created. folder Name '" + folderName + "' of type '"
									+ folderType.toString() + "'");
							return true;
						} else {
							appLog.error("Not able to click on save button to create folder '" + folderName
									+ "' of type '" + folderType.toString() + "'");
							BaseLib.sa.assertTrue(false, "Not able to click on save button to create folder '"
									+ folderName + "' of type '" + folderType.toString() + "'");
						}
					} else {
						appLog.error("Not able to pass value to folder text box for folder '" + folderName
								+ "' of type '" + folderType.toString() + "'");
						BaseLib.sa.assertTrue(false, "Not able to pass value to folder text box for folder '"
								+ folderName + "' of type '" + folderType.toString() + "'");
					}
				} else {
					appLog.error(
							"Not able to create folder '" + folderName + "' of type '" + folderType.toString() + "'");
					BaseLib.sa.assertTrue(false,
							"Not able to create folder '" + folderName + "' of type '" + folderType.toString() + "'");
				}
			} else {
				appLog.error("Add Icon cannot be clicked, So wont be able to continue with the template creation.");
				BaseLib.sa.assertTrue(false,
						"Add Icon cannot be clicked, So wont be able to continue with the template creation.");
			}
		return false;
	}

	/**
	 * @author Ankit Jaiswal
	 * @param folderName
	 * @param workspace
	 * @param pageName
	 * @param timeOut
	 * @return true/false
	 */
	public boolean createChildFolder(String folderName, Workspace workspace, PageName pageName, int timeOut) {

		for (int i = 0; i < 2; i++)
			if (sendKeys(driver, getChildFolderNameTextBox(workspace, pageName, timeOut), folderName,
					"Child Folder name text box", action.BOOLEAN)) {
				if (click(driver, getChildFolderSaveButton(workspace, pageName, timeOut), "Save Button",
						action.BOOLEAN)) {
					appLog.info(folderName + " Folder created successfully.");
					return true;
				} else {
					appLog.error("Not able to click on save button for folder '" + folderName + "'");
					BaseLib.sa.assertTrue(false, "Cannot enter folder name to create folder '" + folderName + "'");
				}
			} else {
				appLog.error("Cannot enter folder name to create folder '" + folderName + "'");
				BaseLib.sa.assertTrue(false, "Cannot enter folder name to create folder '" + folderName + "'");
			}
		return false;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param date
	 * @param typeOfDate
	 * @return true/false
	 */
	public boolean verifyDate(String date, String typeOfDate){

		if(date.contains(getDateAccToTimeZone("America/New_York", "M/d/YYYY"))){
			appLog.info(typeOfDate+" date is verified : "+getDateAccToTimeZone("America/New_York", "M/d/YYYY"));
			return true;
		} else if (date.contains(getDateAccToTimeZone("America/New_York", "M/dd/YYYY"))) {
			appLog.info(typeOfDate+" date is verified : "+getDateAccToTimeZone("America/New_York", "M/dd/YYYY"));
			return true;
		} else if (date.contains(getDateAccToTimeZone("America/New_York", "MM/dd/YYYY"))) {
			appLog.info(typeOfDate+" date is verified : "+getDateAccToTimeZone("America/New_York", "MM/dd/YYYY"));
			return true;
		} else if (date.contains(getDateAccToTimeZone("America/New_York", "MM/d/YYYY"))) {
			appLog.info(typeOfDate+" date is verified : "+getDateAccToTimeZone("America/New_York", "MM/dd/YYYY"));
			return true;
		} else {
			appLog.info(typeOfDate+" date is not verified. found result : "+date);
			appLog.info("Expected Date is : "+getDateAccToTimeZone("America/New_York", "M/d/YYYY")+ " or "+getDateAccToTimeZone("America/New_York", "M/dd/YYYY")+" or "+getDateAccToTimeZone("America/New_York", "MM/dd/YYYY")+" or "+date.contains(getDateAccToTimeZone("America/New_York", "MM/d/YYYY")));
			return false;
		}

	}
	
	/**
	 * @author Ankur Rana
	 * @param timeOut
	 * @return true/false
	 */
	public boolean verifyPendingDisclaimerPopUpMessage(int timeOut){
		String text = trim(getText(driver, getPendingDisclaimerPopUpMessage(timeOut), "Pending disclaimer pop up message", action.BOOLEAN));
		if(text.equalsIgnoreCase(PendingDisclaimerPopUpMessage)){
			return true;
		} else {
			appLog.error("Pending disclaimer pop up msg is not verified. Expected: "+PendingDisclaimerPopUpMessage+"\tActual: "+text);
			BaseLib.sa.assertTrue(false,"Pending disclaimer pop up msg is not verified. Expected: "+PendingDisclaimerPopUpMessage+"\tActual: "+text);
			return false;
		}
	}
	
	/**
	 * @author Ankur Rana
	 * @param pageName
	 * @param timeOut
	 * @return true/false
	 */
	public boolean verifyAccessDeniedPopUpMessage(PageName pageName, int timeOut){
		String text = trim(getText(driver, getAccessDeniedPopUpMessage(timeOut), "", action.BOOLEAN));
		if(pageName != null && pageName.toString().equalsIgnoreCase(PageName.BulkDownload.toString())){
			if(text.equalsIgnoreCase(BulkDownloadAccessDeniedPopUpMessage)){
				return true;
			} else {
				appLog.error("Access denied pop up message is not verified for bulk download. Expected: "+BulkDownloadAccessDeniedPopUpMessage+"\tActual: "+text);
				BaseLib.sa.assertTrue(false,"Access denied pop up message is not verified for bulk download. Expected: "+BulkDownloadAccessDeniedPopUpMessage+"\tActual: "+text);
				return false;
			}
		} else {
			if(text.equalsIgnoreCase(AccessDeniedPopUpMessage)){
				return true;
			} else {
				
				appLog.error("Access denied pop up message is not verified. Expected: "+AccessDeniedPopUpMessage+"\tActual: "+text);
				BaseLib.sa.assertTrue(false,"Access denied pop up message is not verified. Expected: "+AccessDeniedPopUpMessage+"\tActual: "+text);
				return false;
			}
		}
	}
	
	/**
	 * @author Ankur Rana
	 * @param clickOnGoToDisclaimerButton
	 * @param PageName
	 * @param timeOut
	 * @return true/false
	 */
	public boolean verifyAccessDeniedPopUp(String clickOnGoToDisclaimerButton, PageName PageName, int timeOut){
		boolean returnValue=true;
		if(trim(getText(driver, getAccessDeniedPopUpHeader(timeOut), "", action.BOOLEAN)).equalsIgnoreCase("Access Denied")){
			appLog.info("Access denied pop up header is verified.");
		} else {
			appLog.error("Access denied pop up header is not verified. Expected: Access Denied\tActual: "+trim(getText(driver, getAccessDeniedPopUpHeader(timeOut), "", action.BOOLEAN)));
			BaseLib.sa.assertTrue(false,"Access denied pop up header is not verified. Expected: Access Denied\tActual: "+trim(getText(driver, getAccessDeniedPopUpHeader(timeOut), "", action.BOOLEAN)));
			returnValue = false;
		}
		if(verifyAccessDeniedPopUpMessage(PageName, timeOut)){
			appLog.info("Access denied message is verifed.");
		} else {
			returnValue = false;
		}
		if(getAccessDeniedPopUpGoToDisclaimerButton(timeOut)!=null){
			appLog.info("Go to Disclaimer button is present and is verifed.");
		} else {
			appLog.error("Go to disclaimer button is not present and is not verified.");
			BaseLib.sa.assertTrue(false,"Go to disclaimer button is not present and is not verified.");
			returnValue = false;
		}
		
		if(clickOnGoToDisclaimerButton!=null && clickOnGoToDisclaimerButton.equalsIgnoreCase("Yes")){
			if(click(driver, getAccessDeniedPopUpGoToDisclaimerButton(10), "Go to Disclaimer Button", action.BOOLEAN)){
			} else {
				try{
					BaseLib.sa.assertTrue(false,"Not able to click on Go to Disclaimer Button of access denied pop up, So cannot check the functionality.");
					appLog.error("Not able to click on Go to Disclaimer Button of access denied pop up, So cannot check the functionality.");
					returnValue = false;
				} catch (Exception e){
					returnValue = true;
				}
			}
		}
		return returnValue;
	}
	
	/**
	 * @author Azhar Alam
	 * @param documentName
	 * @param timeOut
	 * @return
	 */
	public boolean clickOnDocument(String documentName, int timeOut){
		//*[text()='Name']
		
		WebElement nameColumn1 = FindElement(driver, "(//*[text()='Name'])[1]", "Name Column1", action.BOOLEAN, 3);
		if(click(driver, nameColumn1, "Name Column1", action.BOOLEAN)){
			ThreadSleep(2000);
			click(driver, nameColumn1, "Name Column1", action.BOOLEAN);
		}else{
			WebElement nameColumn2 = FindElement(driver, "(//*[text()='Name'])[2]", "Name Column2", action.BOOLEAN, 3);
			click(driver, nameColumn2, "Name Column2", action.BOOLEAN);
			ThreadSleep(2000);
			click(driver, nameColumn2, "Name Column2", action.BOOLEAN);
		}
		ThreadSleep(3000);
		
		
		WebElement ele = FindElement(driver, "//a[@title='"+documentName+"']", documentName+" document link", action.BOOLEAN, timeOut);

		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
			return true;
		} catch (Exception e){
			
		}
		return false;
	}
	/**
	 * @author Azhar Alam
	 * @param pageName
	 * @param workSpace
	 * @param fileName
	 * @param dnlbtnVerify
	 * @param closeBtnVerify
	 * @param dnload
	 * @return true/false
	 */
	public boolean verifyDownloadFunctionality(PageName pgName, Workspace workSpace, String fileName,
			boolean dnlbtnVerify, boolean closeBtnVerify,boolean dnload) {
		WebElement ele=null;
		boolean flag = true;	
		scrollDownThroughWebelement(driver, getWorkspaceSectionView(workSpace, 30),"Investor workspace view");
		ThreadSleep(1000);
		ele=FindElement(driver, "//a[@title='"+fileName+"']", "File: "+fileName, action.SCROLLANDBOOLEAN, 60);
		scrollDownThroughWebelement(driver, ele, "scrolling to document "+fileName);
		if(ele!=null){
			click(driver, ele, "File: "+fileName, action.SCROLLANDBOOLEAN);
			ThreadSleep(5000);
			String parentID = switchOnWindow(driver);
			if(parentID!=null){
				
				if (dnlbtnVerify) {

					if (getDownloadLink(60) != null) {
						appLog.info("Download Button is displaying");
					} else {
						appLog.info("Document Download Button is not displaying");
						sa.assertTrue(false, "Document Download Button is not displaying");
					}
				}
					
				if (closeBtnVerify) {
					if (getDocumentCloseBtn(60) != null) {

						appLog.info("Close Button is displaying");
					} else {
						appLog.info("Close Button is not displaying");
						sa.assertTrue(false, "Close Button is not displaying");
					}

				}
				
				if (dnload) {
					if (click(driver, getDownloadLink(60), "Download Link", action.BOOLEAN)) {
						Robot rob;
						try {
							Process proc = Runtime.getRuntime()
									.exec(System.getProperty("user.dir") + "/AutoIT/DownloadWinCheck.exe");
							proc.waitFor();
							if (proc.exitValue() == 1) {
								appLog.info("Successfully downloaded file.");
								
								rob = new Robot();
								ThreadSleep(2000);
								rob.keyPress(KeyEvent.VK_TAB);
								ThreadSleep(500);
								rob.keyRelease(KeyEvent.VK_TAB);
								ThreadSleep(500);
								ThreadSleep(500);
								rob.keyRelease(KeyEvent.VK_TAB);
								ThreadSleep(500);
								rob.keyPress(KeyEvent.VK_TAB);
								ThreadSleep(500);
								rob.keyRelease(KeyEvent.VK_TAB);
								ThreadSleep(500);
								rob.keyPress(KeyEvent.VK_TAB);
								ThreadSleep(500);
								rob.keyRelease(KeyEvent.VK_TAB);
								ThreadSleep(500);
								rob.keyPress(KeyEvent.VK_TAB);
								ThreadSleep(500);
								rob.keyPress(KeyEvent.VK_ENTER);
								ThreadSleep(500);
								rob.keyRelease(KeyEvent.VK_ENTER);
								ThreadSleep(500);
								
								
								/*rob.keyPress(KeyEvent.VK_TAB);
								ThreadSleep(500);
								rob.keyRelease(KeyEvent.VK_TAB);
								ThreadSleep(500);
								rob.keyPress(KeyEvent.VK_TAB);
								ThreadSleep(500);
								rob.keyRelease(KeyEvent.VK_TAB);
								ThreadSleep(500);
								rob.keyPress(KeyEvent.VK_TAB);
								ThreadSleep(500);
								rob.keyRelease(KeyEvent.VK_TAB);
								ThreadSleep(500);
								rob.keyPress(KeyEvent.VK_TAB);
								ThreadSleep(500);
								rob.keyRelease(KeyEvent.VK_TAB);
								ThreadSleep(500);
								rob.keyPress(KeyEvent.VK_ENTER);
								ThreadSleep(500);
								rob.keyRelease(KeyEvent.VK_ENTER);*/
								ThreadSleep(500);
							} else {
								appLog.info("File Download link is working");
//								BaseLib.sa.assertTrue(false, "File Download Link is not working.");
//								flag = false;
							}
						} catch (AWTException | IOException | InterruptedException e) {

						}
					} else {
						appLog.info("Download Link Is Not Present");
						BaseLib.sa.assertTrue(false, "Download Link is not present.");
						return false;
					}
				}
				ThreadSleep(10000);
				if ((pgName == PageName.PotentialInvestmentPage) || (pgName == PageName.CurrentInvestmentPgae) || (pgName == PageName.AllFirmsPage)) {

					click(driver, getDocumentCloseBtn(60), "Close Button", action.SCROLLANDBOOLEAN);
				}else{
					

					driver.close();
					//click(driver, getDocumentCloseBtn(60), "Close Button", action.SCROLLANDBOOLEAN);
				}
			
				driver.switchTo().window(parentID);
				return flag;
			} else {
				appLog.info("New Window is not opening.");
				BaseLib.sa.assertTrue(false,"On clicking the file name link on the "+pgName.toString()+" page "+workSpace.toString()+" Workspace  new window is not opening.");
			}
		} else {
			appLog.info(fileName+" :File Is Not Present on "+pgName.toString() +" Page. "+workSpace.toString()+" Workspace");
			BaseLib.sa.assertTrue(false,fileName+" :File Is Not Present on "+pgName.toString()+" Page."+workSpace.toString()+" Workspace");
		}
		return false;
	}
	/**
	 * @author Azhar Alam
	 * @param pgName
	 * @param workSpace
	 * @param filename
	 * @param timeOut
	 * @param scroll
	 * @return true/false
	 */
	public boolean verifyFileOpenViaDownArrow(PageName pgName, Workspace workSpace, String filename, int timeOut,
			String scroll) {

		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		if (fp.clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Open, filename, workSpace, timeOut, scroll)) {
			appLog.info("File Opened Via Down Arrow: " + filename);
			String parentID = switchOnWindow(driver);
			if (parentID != null) {

				if (getDownloadLink(60) != null) {
					appLog.info("Download Button is displaying");
				} else {
					appLog.info("Document Download Button is not displaying");
					sa.assertTrue(false, "Document Download Button is not displaying");
				}

				if (getDocumentCloseBtn(60) != null) {

					appLog.info("Close Button is displaying");
				} else {
					appLog.info("Close Button is not displaying");
					sa.assertTrue(false, "Close Button is not displaying");
				}
				
				/*if(click(driver, getDocumentCloseBtn(60), "Close Button", action.SCROLLANDBOOLEAN)){
					appLog.info("Click on Close Button ");	
				}else{
					appLog.error("Not Able to Click on Close Button ");	
					sa.assertTrue(false, " Not Able to Click on Close Button ");
				}*/
				driver.close();
				driver.switchTo().window(parentID);
				return true;
			} else {
				appLog.info("New Window is not opening.");
				BaseLib.sa.assertTrue(false, "On clicking the file name link on the " + pgName.toString() + " page "
						+ workSpace.toString() + " Workspace  new window is not opening.");
			}
		} 
		return false;

	}
	/**
	 * @author Azhar Alam
	 * @param pgName
	 * @param workSpace
	 * @param filename
	 * @param timeOut
	 * @param scroll
	 * @return true/false
	 */
	public boolean verifyDeleteViaDownArrow(PageName pgName, Workspace workSpace, String filename, int timeOut,
			String scroll){
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		if (fp.clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Delete, filename, workSpace, timeOut, scroll)) {
			ThreadSleep(5000);
			if (click(driver, fp.getDeleteFileYesButtonContentGrid(workSpace,timeOut), "Yes Button", action.SCROLLANDBOOLEAN)) {
				ThreadSleep(5000);
				
				if(pgName.toString().equalsIgnoreCase(PageName.CommitmentsPage.toString())){
					fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 25);	
				}else{
					fp.ContentGridRefreshBtn(workSpace, 25);
				}
				
				if(verifyFileinContentGrid(pgName, workSpace, filename)){
					
					appLog.error("File has not been deleted and is present: "+filename);
					sa.assertTrue(false, "File has not been deleted and is present: "+filename);
				} else {
					appLog.info("File has been deleted and not Present : "+filename);
					return true;
				}
				
			} else {
				appLog.error("Not able to Click on Delete Yes Button .");
				sa.assertTrue(false,"Not able to Click on Delete Yes Button .");
			}

		} else {
			appLog.info("Not able to Click on Delete File Link. : "+filename);
			sa.assertTrue(false, "Not able to Click on Delete File Link. : "+filename);
		}
		return false;
		
	}
	/**
	 * @author Azhar Alam
	 * @param pName
	 * @param workspace
	 * @param filename
	 * @return true/false
	 */
	public boolean verifyFileinContentGrid(PageName pName,Workspace workspace,String filename)
	{
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		
		if(pName.toString().equalsIgnoreCase(PageName.CommitmentsPage.toString())){
			fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30);	
		}else{
			fp.ContentGridRefreshBtn(workspace, 30);
		}
		
		List<String> docName = new ArrayList<String>();
		String workSpaceXpath = null;
		if (pName.toString().equalsIgnoreCase(PageName.FundsPage.toString())) {
			System.err.println("<<<<<<<<<<Fund>>>>>>>>>>>>>");

			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath = "//div[@id='invworkspace']";

			} else {
			workSpaceXpath = "//div[@id='frworkspace']";

			}

		} else if (pName.toString().equalsIgnoreCase(PageName.InstitutionsPage.toString())) {
			System.err.println("<<<<<<<<<<InstitutionsPage.toString()>>>>>>>>>>>>>");
			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath = "//div[@id='Investorgrid_div']";

			} else {
				workSpaceXpath = "//div[@id='divFrWorkspace']";
			}
		} else if (pName.toString().equalsIgnoreCase(PageName.CommitmentsPage.toString())) {
			System.err.println("<<<<<<<<<<CommitmentsPage.toString()>>>>>>>>>>>>>");
			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath = "//div[@class='content_div']";

			}
			else {
				workSpaceXpath = "//div[@class='content_div']";
			}
		} else if (pName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {
			System.err.println("<<<<<<<<<<ContactsPage.toString()>>>>>>>>>>>>>");
			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath = "//div[@id='Investorgrid_div']";

			} else {
				workSpaceXpath = "//div[@id='divFrWorkspace']";
			}
		}else if(pName.toString().equalsIgnoreCase(PageName.CurrentInvestmentPgae.toString()) || 
				pName.toString().equalsIgnoreCase(PageName.PotentialInvestmentPage.toString()))  {
			System.err.println("<<<<<<<<<<CurrentInvestmentPgae.toString()>>>>>>>>>>>>>");
			workSpaceXpath = "//span[contains(@id,'grid_Investor')]";
			
		}
		if (workSpaceXpath != null) {

			String docNameXpath="";
			
			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				docNameXpath = workSpaceXpath + "//span[contains(@id,'myGrid-cell-0')]//a";
				
			}
			else if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
				docNameXpath = workSpaceXpath + "//span[contains(@id,'myGridfundr-cell-0')]//a";
				
			}else if(workspace.toString().equalsIgnoreCase(Workspace.Other.toString()) || 
					workspace.toString().equalsIgnoreCase(PageName.PotentialInvestmentPage.toString())){
					docNameXpath = workSpaceXpath + "//span[contains(@id,'grid_Investor-cell-0')]/a";	
				}
			
			if (isDisplayed(driver, FindElement(driver, docNameXpath, "Document Name List", action.SCROLLANDBOOLEAN,
					3), "visibility", 3, "Document Name List")!=null) {
			List<WebElement> docList = FindElements(driver, docNameXpath, "Document Name List");
			System.err.println("xpath for Files : "+docNameXpath);
			if (!docList.isEmpty()) {
				
				for (WebElement ele1 : docList) {
					
					docName.add(ele1.getText().trim());
				}
				appLog.info("Document List :  "+docName);
				if(docName.contains(filename)){
					appLog.info("Page : "+pName.toString()+" WorkSpace : "+workspace.toString()+" Document Present: "+filename);
					return true;
					
				}else{
					appLog.error("Page : "+pName.toString()+" WorkSpace : "+workspace.toString()+" Document Not Present: "+filename);
					
					
				}
				
			}else{
				appLog.info("Document List is Empty " + pName.toString() + " : " + workspace.toString());
				sa.assertTrue(false, "Document List is Empty " + pName.toString() + " : " + workspace.toString());
				
			}
			}
			else {
			appLog.error("not found documents");}
			
	}else{
		appLog.info(" Xpath Not Found for Content Grid Verification having " + pName.toString() + " : "
				+ workspace.toString());
		sa.assertTrue(false, " Xpath Not Found for Content Grid Verification having " + pName.toString() + " : "
				+ workspace.toString());
	}
		return false;
	}
	
	/**
	 * @author Akul Bhutani
	 * @param pName
	 * @param workspace
	 * @param fileName
	 * @return Status Pending/Approved/Both
	 */
	public Status getStatusOfFile(PageName pName, Workspace workspace, String fileName) {
		WebElement el=null;
		boolean pending_flag = false,approved_flag=false;
		String xpath = "//div//span//u[text()='"+fileName+"']/../../../../span//img[contains(@class,'status')]";
		List<WebElement> li=FindElements(driver, xpath, "status of file in content grid");
		if (li.size() == 1) {
			el = isDisplayed(driver, FindElement(driver, xpath, "status of file "+fileName, action.SCROLLANDBOOLEAN, 30), "visibility", 30, "status of file "+fileName);

			if (el.getAttribute("title").equalsIgnoreCase("pending")) {
				return Status.Pending;
			}
			else {
				return Status.Approved;
			}
		}
		else if(li.size()==2){
			for (int i = 0;i<li.size();i++) {
				if (li.get(i).getAttribute("title").equalsIgnoreCase("pending")) {
					pending_flag = true;
				}
				else {
					approved_flag = true;
				}
			}
			/*if ((pending_flag == true)&&(approved_flag == false)) {
				return Status.Pending;
			}
			else if ((pending_flag == false)&&(approved_flag == true)) {
				return Status.Approved;
			}
			*/ if ((pending_flag == true) && (approved_flag == true)) {
				return Status.Both;
			}
		}
		return null;
	}
	
	/**
	 * @author Akul Bhutani
	 * @param pName
	 * @param workspace
	 * @param filename
	 * @param statusValue
	 * @return true/false
	 */
	public boolean verifyFileinContentGrid(PageName pName,Workspace workspace,String filename, boolean statusValue) {
		Status arg=null;
		if (statusValue == false) {
			arg = Status.Pending;
		}
		else if(statusValue == true) {
			arg = Status.Approved;
		}
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		
		if(pName.toString().equalsIgnoreCase(PageName.CommitmentsPage.toString())){
			fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30);	
		}else{
			fp.ContentGridRefreshBtn(workspace, 30);
		}
		
		List<String> docName = new ArrayList<String>();
		String workSpaceXpath = null;
		if (pName.toString().equalsIgnoreCase(PageName.FundsPage.toString())) {
			System.err.println("<<<<<<<<<<Fund>>>>>>>>>>>>>");

			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath = "//div[@id='invworkspace']";

			} else {
			workSpaceXpath = "//div[@id='frworkspace']";

			}

		} else if (pName.toString().equalsIgnoreCase(PageName.InstitutionsPage.toString())) {
			System.err.println("<<<<<<<<<<InstitutionsPage.toString()>>>>>>>>>>>>>");
			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath = "//div[@id='Investorgrid_div']";

			} else {
				workSpaceXpath = "//div[@id='divFrWorkspace']";
			}
		} else if (pName.toString().equalsIgnoreCase(PageName.CommitmentsPage.toString())) {
			System.err.println("<<<<<<<<<<CommitmentsPage.toString()>>>>>>>>>>>>>");
			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath = "//div[@class='content_div']";

			}
			else {
				workSpaceXpath = "//div[@class='content_div']";
			}
		} else if (pName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {
			System.err.println("<<<<<<<<<<ContactsPage.toString()>>>>>>>>>>>>>");
			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath = "//div[@id='Investorgrid_div']";

			} else {
				workSpaceXpath = "//div[@id='divFrWorkspace']";
			}
		}else if(pName.toString().equalsIgnoreCase(PageName.CurrentInvestmentPgae.toString()) || 
				pName.toString().equalsIgnoreCase(PageName.PotentialInvestmentPage.toString()))  {
			System.err.println("<<<<<<<<<<CurrentInvestmentPgae.toString()>>>>>>>>>>>>>");
			workSpaceXpath = "//span[contains(@id,'grid_Investor')]";
			
		}
		if (workSpaceXpath != null) {

			String docNameXpath="";
			
			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				docNameXpath = workSpaceXpath + "//span[contains(@id,'myGrid-cell-0-')]//a/u";
				
			}
			else if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
				docNameXpath = workSpaceXpath + "//span[contains(@id,'myGridfundr-cell-0-')]//a/u";
				
			}else if(workspace.toString().equalsIgnoreCase(Workspace.Other.toString()) || 
					workspace.toString().equalsIgnoreCase(PageName.PotentialInvestmentPage.toString())){
					docNameXpath = workSpaceXpath + "//span[contains(@id,'grid_Investor-cell-0')]/a";	
				}
			
			List<WebElement> docList = FindElements(driver, docNameXpath, "Document Name List");
			System.err.println("xpath for Files : "+docNameXpath);
			Status var;
			if (!docList.isEmpty()) {
				
				for (WebElement ele1 : docList) {
					
					docName.add(ele1.getText().trim());
				}
				appLog.info("Document List :  "+docName);
				if(docName.contains(filename)){
					appLog.info("Page : "+pName.toString()+" WorkSpace : "+workspace.toString()+" Document Present: "+filename);
					var = getStatusOfFile(pName, workspace, filename);
					if ((var==arg) || (var==Status.Both)) {
						appLog.info("status matches. expected value is "+arg.toString()+" and actual is "+var.toString());
						return true;
					}
					else if(var!=arg) {
						appLog.error("expected status is "+arg.toString()+" but actual is "+var.toString());
					}
					
					
				}else{
					appLog.error("Page : "+pName.toString()+" WorkSpace : "+workspace.toString()+" Document Not Present: "+filename);
					
					
				}
				
			}else{
				appLog.info("Document List is Empty " + pName.toString() + " : " + workspace.toString());
				sa.assertTrue(false, "Document List is Empty " + pName.toString() + " : " + workspace.toString());
				
			}
			
			
	}else{
		appLog.info(" Xpath Not Found for Content Grid Verification having " + pName.toString() + " : "
				+ workspace.toString());
		sa.assertTrue(false, " Xpath Not Found for Content Grid Verification having " + pName.toString() + " : "
				+ workspace.toString());
	}
		return false;
		
	}
	
	public boolean VerifyFolderStructure(String sheetName,String institutionName, String limitedPartnerName, String fundName, Workspace workspace, PageName pageName, int timeOut){
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		Map<String, String> s = folderStructureInExcel(sheetName);
		Set<String> paths = s.keySet();
		Iterator<String> i = paths.iterator();
		String lpName = null;
		String instiName = null;
		i = paths.iterator();
		FolderType folderType = null;
		boolean flag = true;
		while (i.hasNext()) {
			String string = i.next();
			lpName = null;
			instiName = null;
			if (string.isEmpty())
				continue;
			System.err.println("Folder path searching: "+string);
			System.err.println("Folder type searching: "+s.get(string));
			if(s.get(string).equalsIgnoreCase("Shared")){
				folderType=FolderType.Shared;
			} else if (s.get(string).equalsIgnoreCase("Common")){
				folderType=FolderType.Common;
			} else if (s.get(string).equalsIgnoreCase("Internal")){
				folderType=FolderType.Internal;
			} else if (s.get(string).equalsIgnoreCase("Standard")){
				folderType=FolderType.Standard;
				lpName = limitedPartnerName;
				instiName = institutionName;
			}
			if(pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.PotentialInvestmentPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.CurrentInvestmentPgae.toString())){
				timeOut = timeOut/5;
			} else if (pageName.toString().equalsIgnoreCase(PageName.CommitmentsPage.toString())){
				fundName = null;
			}
			System.err.println("LP Name: "+lpName);
			System.err.println("Insti Name: "+instiName);
			System.err.println("fundname: "+fundName);
			if(fp.verifyFolderPathdummy(string, instiName, lpName, fundName, pageName, workspace, timeOut)){
				if((pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.PotentialInvestmentPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.CurrentInvestmentPgae.toString())) && string.contains("(Internal)")){
					appLog.error("Internal folder is present on the contact page and is not verified.");
					sa.assertTrue(false,"Internal folder is present on the contact page and is not verified.");
					flag=false;
				} else {
					appLog.info("Folder path '"+string+"' is verified on '"+pageName.toString()+"'.");
				}
			} else {
				if((pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.PotentialInvestmentPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.CurrentInvestmentPgae.toString())) && string.contains("(Internal)")){
					appLog.info("Internal folder is not present on the contact page and is verified.");
				} else {
					appLog.error("Folder Path '"+string+"' is not verified on '"+pageName.toString()+"'.");
					sa.assertTrue(false,"Folder Path '"+string+"' is not verified on '"+pageName.toString()+"'.");
					flag=false;
				}
			}
//			if(pageName.toString().equalsIgnoreCase(PageName.InstitutionsPage.toString())){
//				if(!string.contains("(Common)") || !string.contains("(Shared)") || !string.contains("(Internal)")){
//					if(fp.verifyFolderPathdummy(string, null, lpName, fundName, PageName.InstitutionsPage, workspace, timeOut)){
//						appLog.info("Folder path '"+string+"' is verified.");
//					} else {
//						appLog.error("Folder Path '"+string+"' is not verified on '"+institutionName+"' institutions page.");
//						sa.assertTrue(false,"Folder Path '"+string+"' is not verified on '"+institutionName+"' institutions page.");
//						flag=false;
//					}
//				} else {
//					if(fp.verifyFolderPathdummy(string, null, null, fundName, PageName.InstitutionsPage, workspace, timeOut)){
//						appLog.info("Folder path '"+string+"' is verified.");
//					} else {
//						appLog.error("Folder Path '"+string+"' is not verified on institutions page.");
//						sa.assertTrue(false,"Folder Path '"+string+"' is not verified on institutions page.");
//						flag=false;
//					}
//				}
//			} else if (pageName.toString().equalsIgnoreCase(PageName.CommitmentsPage.toString())) {
//				if(fp.verifyFolderPathdummy(string, null, lpName, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, timeOut)){
//					appLog.info("Folder path '"+string+"' is verified."); 
//				} else {
//					appLog.error("Folder path '"+string+"' is not verified on commitment page.");
//					sa.assertTrue(false,"Folder path '"+string+"' is not verified on commitment page.");
//					flag=false;
//				}
//			} else if (pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString()) && !string.contains("(Internal)")){
//				if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
//					if(fp.verifyFolderPathdummy(string, institutionName, null, fundName, PageName.ContactsPage, workspace, timeOut)){
//						appLog.info("Folder path '"+string+"' is verified."); 
//					} else {
//						appLog.error("Folder path '"+string+"' is not verified on contact page for workspace "+workspace.toString());
//						sa.assertTrue(false,"Folder path '"+string+"' is not verified on contact page for workspace "+workspace.toString());
//						flag=false;
//					}
//				} else {
//					if(fp.verifyFolderPathdummy(string, institutionName, lpName, fundName, PageName.ContactsPage, workspace, timeOut)){
//						appLog.info("Folder path '"+string+"' is verified."); 
//					} else {
//						appLog.error("Folder path '"+string+"' is not verified on contact page for workspace "+workspace.toString());
//						sa.assertTrue(false,"Folder path '"+string+"' is not verified on contact page for workspace "+workspace.toString());
//						flag=false;
//					}
//				}
//			}
		}
		return flag;
	}
	/**
	 * @author Azhar Alam
	 * @param pgName
	 * @param workSpace
	 * @param filename
	 * @param timeOut
	 * @param scroll
	 * @param noButton
	 * @return true/false
	 */
	public boolean verifyDeletePopUpUIViaDownArrow(PageName pgName, Workspace workSpace, String filename, int timeOut,
			String scroll, boolean noButton) {
		
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		
		if(pgName.toString().equalsIgnoreCase(PageName.CommitmentsPage.toString())){
			click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, timeOut),"Refresh button CMI", action.SCROLLANDBOOLEAN);	
		}else{
			click(driver, fp.ContentGridRefreshBtn(workSpace, timeOut),"Refresh button", action.SCROLLANDBOOLEAN);
		}
		
		if (fp.clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Delete, filename, workSpace, timeOut, scroll)) {
			ThreadSleep(5000);
			WebElement ele;
			String msg;
			
			// Header
			ele = getDeleteFilePopUpHeaderContentGrid(workSpace, timeOut);
			if (ele != null) {
				msg = ele.getText().trim();
				if (msg.equalsIgnoreCase(deleteHeaderMessage)) {

					appLog.info("Delete PopUp Header Verified - Actual :" + msg + "   " + " Expected  : "
							+ deleteHeaderMessage);
				} else {
					appLog.error("Delete PopUp Header Not Verified - Actual :" + msg + "   " + " Expected  : "
							+ deleteHeaderMessage);
					sa.assertTrue(false, "Delete PopUp Header Not Verified - Actual :" + msg + "   " + " Expected  : "
							+ deleteHeaderMessage);
				}

			} else {
				appLog.error("Delete PopUp Header Element is null ");
				sa.assertTrue(false, "Delete PopUp Header Element is null ");

			}

			// Msg

			ele = getDeleteFilePopUpMsgContentGrid(workSpace, timeOut);
			if (ele != null) {
				msg = ele.getText().trim();
				if (msg.equalsIgnoreCase(deleteTextMessage)) {

					appLog.info(
							"Delete PopUp Msg Verified - Actual :" + msg + "   " + " Expected  : " + deleteTextMessage);
				} else {
					appLog.error("Delete PopUp Msg Not Verified - Actual :" + msg + "   " + " Expected  : "
							+ deleteTextMessage);
					sa.assertTrue(false, "Delete PopUp Msg Not Verified - Actual :" + msg + "   " + " Expected  : "
							+ deleteTextMessage);
				}

			} else {
				appLog.error("Delete PopUp Msg Element is null ");
				sa.assertTrue(false, "Delete PopUp Msg Element is null ");

			}

			// yes

			ele = getDeleteFileYesButtonContentGrid(workSpace, timeOut);
			if (ele != null) {
				msg = ele.getText().trim();
				if (msg.equalsIgnoreCase("yes")) {

					appLog.info("Delete PopUp Yes Butoon Present and Verified");
				} else {
					appLog.error("Delete PopUp Yes Butoon Not Verified ");
					sa.assertTrue(false, "Delete PopUp Yes Butoon Not Verified ");
				}

			} else {
				appLog.error("Delete PopUp Yes Element is null ");
				sa.assertTrue(false, "Delete PopUp Yes Element is null ");

			}

			if (noButton) {

				// no

				ele = getDeleteFileNoButtonContentGrid(workSpace, timeOut);
				if (ele != null) {
					msg = ele.getText().trim();
					if (msg.equalsIgnoreCase("no")) {

						appLog.info("Delete PopUp No Button Present and Verified");
					} else {
						appLog.error("Delete PopUp No Button Not Verified ");
						sa.assertTrue(false, "Delete PopUp No Butoon Not Verified ");
					}

					if (click(driver, ele, "No Button ", action.BOOLEAN)) {

					} else {
						appLog.info("Not able to Click on Deleteb PopUp No Button " + filename);
						sa.assertTrue(false, "Not able to Click on Delete PopUp No Button  " + filename);
					}

				} else {
					appLog.error("Delete PopUp no Element is null ");
					sa.assertTrue(false, "Delete PopUp no Element is null ");

				}

			} else {

				// CrossIcon
				ele = getDeleteFileCrossIconLinkContentGrid(workSpace, timeOut);
				if (ele != null) {
					msg = ele.getAttribute("title").trim();
					if (msg.equalsIgnoreCase("Close")) {

						appLog.info("Delete PopUp CrossIcon Present and Verified");
					} else {
						appLog.error("Delete PopUp CrossIcon Not Verified ");
						sa.assertTrue(false, "Delete PopUp CrossIcon Not Verified ");
					}

					if (click(driver, ele, "Cross Icon ", action.BOOLEAN)) {

					} else {
						appLog.info("Not able to Click on Deleteb PopUp CrossIcon " + filename);
						sa.assertTrue(false, "Not able to Click on Delete PopUp CrossIcon " + filename);
					}

				} else {
					appLog.error("Delete PopUp CrossIcon Element is null ");
					sa.assertTrue(false, "Delete PopUp CrossIcon Element is null ");

				}
			}
			
			if(pgName.toString().equalsIgnoreCase(PageName.CommitmentsPage.toString())){
				click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, timeOut),"Refresh button CMI", action.SCROLLANDBOOLEAN);	
			}else{
				click(driver, fp.ContentGridRefreshBtn(workSpace, timeOut),"Refresh button", action.SCROLLANDBOOLEAN);
			}
			
			if(verifyFileinContentGrid(pgName, workSpace, filename)){
				
				appLog.info("File is present after Clicking on No/Cros Button: "+filename);
				return true;
			} else {
				appLog.error("File is not present after Clicking on No/Cross Button : "+filename);
				sa.assertTrue(false, "File is not present after Clicking on No/Cross Button:  "+filename);
			
			}

		} else {
			appLog.info("Not able to Click on Delete File Link. : " + filename);
			sa.assertTrue(false, "Not able to Click on Delete File Link. : " + filename);
		}
		return false;

	}
	/**
	 * @author Azhar Alam
	 * @param folder
	 * @param timeOut
	 * @param pName
	 * @return SoftAssert true/false
	 */
	public SoftAssert verifySearchUI(String folder,int timeOut,PageName pName){
		SoftAssert sa = new SoftAssert();
		WebElement ele;
		String value ;
		List<WebElement> eleList ;
		
		// Header
		ele = getSearchHeader(timeOut);
		
		if(ele!=null){
			
			value=ele.getText().trim();
			if(value.equalsIgnoreCase("Search Results")){
				appLog.info(" Header Value  verified  " );	
			}else{
				appLog.error(" Header Value Not verified  " );
				sa.assertTrue(false, " Header Value Not verified  " );		
			}
			
		}else{
			appLog.error(" Header is Null " );
			sa.assertTrue(false, " Header is Null " );	
		}
		
		eleList=getAllLabelsOnSearchPopUp();
		
		String[] columnVlaue= {"Folder Path","Document Name","Size (in Kb)","Last Update"};
		
		if(eleList.size()==columnVlaue.length){
			appLog.info(" 4 Column Are Showing " );	
			
			for(int i=0;i<eleList.size();i++){
			value = eleList.get(i).getAttribute("title").trim();
			if(value.equalsIgnoreCase(columnVlaue[i])){
				appLog.info(" Column Value Matched :  " +value);	
				
			}else{
				appLog.error(" Column Value Not Matched : " +value);
				sa.assertTrue(false, " Column Value Not Matched : " +value);	
			}
			}
			
		}else{
			appLog.error(" 4 Column Are Not Showing " );
			sa.assertTrue(false, " 4 Column Are Not Showing " );		
		}
		
		ele = getFolderRadioButton(folder, timeOut);
		if(ele!=null){
		if(ele.isSelected()){
			appLog.info(folder+"  Folder Radio is Selected By Default " );
		}else{
			appLog.error(folder+"  Folder Radio is not Selected By Default " );
			sa.assertTrue(false, folder+"  Folder Radio is not Selected By Default " );		
		}
		}else{
			appLog.error(folder+"  Folder Radio is Null " );
			sa.assertTrue(false, folder+"  Folder Radio is Null ");	
		}
		
		if ((pName == PageName.FundsPage) || (pName == PageName.PotentialInvestmentPage) || (pName == PageName.CurrentInvestmentPgae)) {
			ele = getAllFolderRadioButton(timeOut);
			if(ele!=null){

			}else{
				appLog.error(" All Folder Radio is Null " );
				sa.assertTrue(false, " All Folder Radio is Null ");		
			}
		}
		return sa;
		
	}
	/**
	 * @author Azhar Alam
	 * @param driver
	 * @param folder
	 * @param fileName
	 * @param date
	 * @return SoftAssert true/false
	 */
	public  SoftAssert verifyContentGridForSearch(WebDriver driver, String folder, String fileName,String date) {

		SoftAssert saa = new SoftAssert();
	
		
		String folderName=null;
		String docName = null;
		String sizeValue = null;
		String updatedDate = null;

			
			List<WebElement> folderPathList = getSearchFolderPathColumnValue();

			if (!folderPathList.isEmpty()) {

				List<WebElement> docNameList = getSearchDocumentNameColumnValue();
				List<WebElement> sizeList = getSearchSizeColumnValue();
				List<WebElement> lastUpdatedDateList = getSearchLastUpdatedColumnValue();
				System.err.println("Size  :      Path : "+folderPathList.size()+" Doc : "+docNameList.size()+" size : "+sizeList.size()+" Last Updated Date: "+lastUpdatedDateList.size());

				String[] folders = folder.split(",");

				for (int i = 0; i < folders.length; i++) {

					for (int j = 0; j < folderPathList.size(); j++) {
						
						folderName=folderPathList.get(j).getAttribute("title").trim();
						docName = docNameList.get(j).getText().trim();
						sizeValue = sizeList.get(j).getText().trim();
						updatedDate = lastUpdatedDateList.get(j).getText().trim();
						
						System.err.println(">>> WebPage " + folderName + "  " + docName + " " + sizeValue+" "+updatedDate+ " <<<<");
						System.out.println("<<<<<<<<< pASSING " + folders[i] + "  " + fileName + " "+date+ " <<<<<<<<<");
						appLog.info(">>> WebPage " + folderName + "  " + docName + " " + sizeValue+" "+updatedDate+ " <<<<");
						appLog.info("<<<<<<<<< pASSING " + folders[i] + "  " + fileName + " "+date+ " <<<<<<<<<");
						if (folderName.contains(folders[i])
							&& docName.equalsIgnoreCase(fileName) && !sizeValue.contains("0.00")
							&& date.contains(updatedDate)
															 ) {

									appLog.info("  <<<<Verified>>>>  "+folders[i] + " having "+fileName+" size "+sizeValue+" date "+updatedDate);
							break;
						}

						if (j == folderPathList.size() - 1) {
							appLog.info(folders[i] + "  not Present " );
							saa.assertTrue(false,
									folders[i] + "  not Present " );
						}

					}

				}

			} else {
				appLog.info("Folder List is Empty " );
				saa.assertTrue(false, "Folder List is Empty ");
			}

		

		return saa;
	}
	/**
	 * @author Azhar Alam
	 * @param pageName
	 * @param workspace
	 * @param value
	 * @param timeOut
	 * @return true/false
	 */
	public boolean enterValueAndClickonSearchBoxContentGrid(PageName pageName,Workspace workspace,String value,int timeOut){
		
		WebElement ele;
		ele = getSearchTextBox(driver, pageName, workspace, timeOut);
		
		if(ele!=null){
			
			if(sendKeys(driver, ele, value, "Search Text Box Value", action.SCROLLANDBOOLEAN)){
				
				ele = getSearchIcon(driver, pageName, workspace, timeOut);
				if (ele != null) {
					try {
						scrollDownThroughWebelement(driver, ele, "");
						if(clickUsingJavaScript(driver, ele, "search icon on "+pageName)) {
							appLog.error("Clicked on Search Icon ");
							return true;
						}else {
							new Throwable();
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						appLog.error(" Not Able to Click Search Icon " );
						sa.assertTrue(false," Not Able to Click Search Icon " );
						//e.printStackTrace();
					}
				} else {
					appLog.error(" Search Icon Element is Null " );
					sa.assertTrue(false, " Search Icon Element is Null " );
				}
			} else{
				appLog.error(" Not Able to Write Value on Search Text Box " );
				sa.assertTrue(false, " Not Able to Write Value on Search Text Box " );		
			}
			
		}else{
			appLog.error(" Search Text Box Element is Null " );
			sa.assertTrue(false, " Search Text Box Element is Null " );	
		}
		
		return false;
		
	}
	/**
	 * @author Azhar Alam
	 * @param value
	 * @param timeOut
	 * @return true/false
	 */
	public boolean enterValueAndClickonSearchPopUpResult(String value,int timeOut){
		
		WebElement ele;
		ele = getSearchResultPopUpTextBox(timeOut);
		
		if(ele!=null){
			
			if(sendKeys(driver, ele, value, "Search Text Box Value", action.SCROLLANDBOOLEAN)){
				
				ele = getSearchResultPopSearchIcon(timeOut);
				if (ele != null) {

					if (click(driver, ele, "Search Icon", action.SCROLLANDBOOLEAN)) {
						
						return true;
						
					} else {
						appLog.error(" Not Able to Click Search Icon " );
						sa.assertTrue(false," Not Able to Click Search Icon " );
					}
				} else {
					appLog.error(" Search Icon Element is Null " );
					sa.assertTrue(false, " Search Icon Element is Null " );
				}
			} else{
				appLog.error(" Not Able to Write Value on Search Text Box " );
				sa.assertTrue(false, " Not Able to Write Value on Search Text Box " );		
			}
			
		}else{
			appLog.error(" Search Text Box Element is Null " );
			sa.assertTrue(false, " Search Text Box Element is Null " );	
		}
		
		return false;
		
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param folderPath
	 * @param pageName
	 * @param timeOut
	 * @return folderid/null
	 */
	public String getCreatedFolderId(String folderPath,PageName pageName,int timeOut) {
		WebElement ele = null;
		String id = null;
		String folderStruct[] = folderPath.split("/");
		if(folderStruct.length==1) {
			ele = isDisplayed(driver, FindElement(driver,
					"//span[contains(text(),'All Folders')]/span/../../../following-sibling::ul/li/div//label[contains(text(),'"
							+ folderStruct[0] + "')]",
							"folder id", action.SCROLLANDBOOLEAN, timeOut), "visibility", timeOut, "folder id");
		}else {
			ele = isDisplayed(driver, FindElement(driver,
					"//span[contains(text(),'All Folders')]/span/../../../following-sibling::ul/li/div//label[contains(text(),'"
							+ folderStruct[0] + "')]/../../../following-sibling::ul/li/div//label[contains(text(),'"
							+ folderStruct[1] + "')]",
							"folder id", action.SCROLLANDBOOLEAN, timeOut), "visibility", timeOut, "folder id");
		}
		if (ele != null) {			
			id = ele.getAttribute("id");
		} else {
			appLog.error(folderPath + " folder is not visible in folder structure");
		}
		return id;
	}
	
	
	/**
	 * @author Ankit Jaiswal
	 * @param folderPath
	 * @param pageName
	 * @param timeOut
	 * @return id/null
	 */
	public String getCreatedFolderIdFullTraverse(String folderPath,PageName pageName,int timeOut) {
		WebElement ele = null;
		String id = null;
		String xpathAddition="";
		String xpath = "//span[contains(text(),'All Folders')]/span/../../../following-sibling::ul/li/div//label[contains(text(),'";
		int i = 0;
		String folderStruct[] = folderPath.split("/");
		if(folderStruct.length==1) {
			ele = isDisplayed(driver, FindElement(driver,
					xpath	+ folderStruct[0] + "')]",
					"folder id", action.SCROLLANDBOOLEAN, timeOut), "visibility", timeOut, "folder id");
		}
		else {
			for (i = 1;i<=folderStruct.length;i++) {


				ele = isDisplayed(driver, FindElement(driver,
						xpath+folderStruct[0] +xpathAddition+"')]","folder id", action.SCROLLANDBOOLEAN, timeOut), "visibility", timeOut, "folder id");


				appLog.info("finding "+xpath+folderStruct[0]
						+xpathAddition+"')]");
				click(driver, ele, "folder in hierarchy", action.BOOLEAN);
				try
				{
					xpathAddition += "')]/../../../following-sibling::ul/li/div//label[contains(text(),'"+folderStruct[i];
				}
				catch(ArrayIndexOutOfBoundsException e) {
					appLog.info("array index finished");
				}
			}	
			ele = isDisplayed(driver, FindElement(driver,
					xpath+folderStruct[0] +xpathAddition+"')]",
					"folder id", action.SCROLLANDBOOLEAN, timeOut), "visibility", timeOut, "folder id");
		}
		if (ele != null) {			
			id = ele.getAttribute("id");
		} else {
			appLog.error(folderPath + " folder is not visible in folder structure");
		}
		return id;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param id
	 * @return True/False
	 */
	public boolean clickOnDeleteFolderButton(String id) {
		((JavascriptExecutor) driver).executeScript("document.getElementById('" + "del" + id.substring(3, id.length())
				+ "').setAttribute('style', 'height: 8px; position: absolute; padding: 12px 22px 0px; margin-left: 45px; display: inline;');");
		scrollDownThroughWebelement(driver, FindElement(driver,
				"//label[@id='" + id + "']//span[@title='Delete Folder']", "Delete folder Button", action.BOOLEAN, 20),
				"Add Folder Button");
		return click(driver, FindElement(driver, "//label[@id='" + id + "']//span[@title='Delete Folder']",
				"Delete Folder Button", action.BOOLEAN, 20), "Delete Folder", action.BOOLEAN);
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param id
	 * @return True/False
	 */
	public boolean clickOnRenameFolderButton(String id) {
		((JavascriptExecutor) driver).executeScript("document.getElementById('" + "ren" + id.substring(3, id.length())
				+ "').setAttribute('style', 'height: 8px; position: absolute; padding: 12px 22px 0px; display: inline;');");
		scrollDownThroughWebelement(driver, FindElement(driver,
				"//label[@id='"+id+"']/span[1]", "Rename Folder Button", action.BOOLEAN, 20),"Rename Folder Button");
		return click(driver, FindElement(driver, "//label[@id='"+id+"']/span[1]",
				"Rename Folder Button", action.BOOLEAN, 20), "Rename a folder", action.BOOLEAN);
	}
	
//	/**
//	 * @author Ankit Jaiswal
//	 * @param id
//	 * @return True/False
//	 */
//	public String getValueFromElementUsingJavaScript(WebElement element) {
//		String text =null;
//		//text=(String) ((JavascriptExecutor) driver).executeScript("return $('"+Jquery+"')[0].value");
//		text=(String) ((JavascriptExecutor) driver).executeScript("return arguments[0].value;",element);
//		return text;
//	}
	/**
	 * @author Azhar Alam
	 * @param workspace
	 * @param pageName
	 * @return true/false
	 */
	public boolean performSortingCheckOnAllColumns(Workspace workspace, PageName pageName) {
		boolean flag = true;
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		
		appLog.info("checking Default sorting on Upload On column");
		if (checkSorting(SortOrder.Decending, fp.getContentGridUploadedOnList(workspace, pageName))){
			appLog.info(" Default sorting Verified on Upload On column");
		}
		else {
			appLog.error(" Default sorting Not Verified on Upload On column");
			flag= false;
		}
		
		
		
		appLog.info("checking sorting for document name column");
		//checking sorting for descending order
		click(driver, getColumnHeads(pageName, workspace).get(0), "column head document names", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		/*if (checkSorting(SortOrder.Assecending, fp.getContentGridDocNameList(workspace, pageName))){
			appLog.info("correct sorting is present for document name list in Assecending order");
		}
		else {
			appLog.error("Incorrect sorting is present for document name list in Assecending order");
			flag = false;
		}*/
		//checking sorting for ascending order
		click(driver, getSortingArrow(pageName, workspace).get(0), "column head document names", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		/*if (checkSorting(SortOrder.Decending, fp.getContentGridDocNameList(workspace, pageName))){
			appLog.info("correct sorting is present for document name in Decending order");
		}
		else {
			appLog.error("Incorrect sorting is present for document name list  in Decending order");
			flag = false;
		}*/
		appLog.info("checking sorting for uploaded by column");
		click(driver, getColumnHeads(pageName, workspace).get(3), "uploaded by column", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		if (checkSorting(SortOrder.Assecending, fp.getContentGridUploadedByList(workspace, pageName))){
			appLog.info("correct sorting is present for uploaded by column in Assecending order");
		}
		else {
			appLog.error("Incorrect sorting is present for uploaded by column in Assecending order");
			flag = false;
		}
		
		click(driver, getSortingArrow(pageName, workspace).get(3), "uploaded by column", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		if (checkSorting(SortOrder.Decending, fp.getContentGridUploadedByList(workspace, pageName))){
			appLog.info("correct sorting is present for uploaded by column in Decending order");
		}
		else {
			appLog.error("Incorrect sorting is present for uploaded by column  in Decending order");
			flag = false;
		}
		
		appLog.info("checking sorting for firm name column");
		click(driver, getColumnHeads(pageName, workspace).get(4), "firm column", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		if (checkSorting(SortOrder.Assecending, fp.getContentGridFirmNameList(workspace, pageName))){
			appLog.info("correct sorting is present for firm column in Assecending order");
		}
		else {
			appLog.error("Incorrect sorting is present for firm column in Assecending order");
			flag = false;
		}
		
		click(driver, getSortingArrow(pageName, workspace).get(4), "firm column", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		if (checkSorting(SortOrder.Decending, fp.getContentGridFirmNameList(workspace, pageName))){
			appLog.info("correct sorting is present for firm column in Decending order");
		}
		else {
			appLog.error("Incorrect sorting is present for firm column  in Decending order");
			flag = false;
		}
		
		appLog.info("checking sroting for uploaded on column");
		click(driver, getColumnHeads(pageName, workspace).get(5), "uploaded on column", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		if (checkSorting(SortOrder.Assecending, fp.getContentGridUploadedOnList(workspace, pageName))){
			appLog.info("correct sorting is present for uploaded on column in Assecending order");
		}
		else {
			appLog.error("Incorrect sorting is present for uploaded on column in Assecending order");
			flag = false;
		}
		
		click(driver, getSortingArrow(pageName, workspace).get(5), "uploaded on column", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		if (checkSorting(SortOrder.Decending, fp.getContentGridUploadedOnList(workspace, pageName))){
			appLog.info("correct sorting is present for uploaded on column in Decending order");
		}
		else {
			appLog.error("Incorrect sorting is present for uploaded on column  in Decending order");
			flag = false;
		}
		return flag;
		
	}
	
	/**
	 * @author Azhar Alam
	 * @param noSorting
	 * @param sortingonBasisofFolderPath
	 * @return SoftAssert true/false
	 */
	
	public SoftAssert performSortingCheckOnSearchPopUpForAllColumns(boolean noSorting,boolean sortingonBasisofFolderPath) {
		
		SoftAssert sa = new SoftAssert();
		List<WebElement> eleList;
		List<WebElement> eleValue = null;
		eleList = getAllLabelsOnSearchPopUp();
		String value;
		
		if (noSorting) {
			System.err.println("**********inside no sorting*********");
			eleValue = getSearchFolderPathColumnValue();
			if (checkSorting(SortOrder.Assecending, eleValue)) {
				appLog.error(" Some sorting has been applied  "
						+ SortOrder.Assecending.toString());
				sa.assertTrue(false, " Some sorting has been applied  "
						+ SortOrder.Assecending.toString());
			} else {
				appLog.info("  No sorting has been applied " );
			}

			if (checkSorting(SortOrder.Decending, eleValue)) {
				appLog.error(" Some sorting has been applied " 
						+ SortOrder.Decending.toString());
				sa.assertTrue(false, " Some sorting has been applied " 
						+ SortOrder.Decending.toString());
			} else {
				appLog.info("  No sorting has been applied " );
			}

		} else{
		//	System.err.println("*****inside All sorting**********");
			String[] columnVlaue = { "Folder Path", "Document Name", "Size (in Kb)", "Last Update" };

			if (eleList.size() == columnVlaue.length) {
				appLog.info(" 4 Column Are Showing ");

				for (int i = 0; i < eleList.size(); i++) {
					System.err.println("*****inside All sorting**********");
					value = eleList.get(i).getAttribute("title").trim();
					if (value.equalsIgnoreCase(columnVlaue[i])) {
						appLog.info(" Column Value Matched :  " + value);

						

							if (click(driver, eleList.get(i), value, action.SCROLLANDBOOLEAN)) {

								if (i == 0) {
									eleValue = getSearchFolderPathColumnValue();
								} else if (i == 1) {
									eleValue = getSearchDocumentNameColumnValue();
								} else if (i == 2) {
									eleValue = getSearchSizeColumnValue();
								} else {
									eleValue = getSearchLastUpdatedColumnValue();
								}

								if (checkSorting(SortOrder.Assecending, eleValue)) {
									appLog.info(
											" Sorting Verified on : " + value + " for " + SortOrder.Assecending.toString());
								} else {
									appLog.error(" Sorting Not Verified on : " + value + " for "
											+ SortOrder.Assecending.toString());
									sa.assertTrue(false, " Sorting Not Verified on : " + value + " for "
											+ SortOrder.Assecending.toString());
								}

							} else {
								appLog.error(" Not Able to Click Column " + value);
								sa.assertTrue(false, " Not Able to Click Column " + value);
							}
							ThreadSleep(3000);
							if (click(driver, eleList.get(i), value, action.SCROLLANDBOOLEAN)) {

								if (i == 0) {
									eleValue = getSearchFolderPathColumnValue();
								} else if (i == 1) {
									eleValue = getSearchDocumentNameColumnValue();
								} else if (i == 2) {
									eleValue = getSearchSizeColumnValue();
								} else {
									eleValue = getSearchLastUpdatedColumnValue();
								}

								if (checkSorting(SortOrder.Decending, eleValue)) {
									appLog.info(
											" Sorting Verified on : " + value + " for " + SortOrder.Decending.toString());
								} else {
									appLog.error(" Sorting Not Verified on : " + value + " for "
											+ SortOrder.Decending.toString());
									sa.assertTrue(false, " Sorting Not Verified on : " + value + " for "
											+ SortOrder.Decending.toString());
								}

							} else {
								appLog.error(" Not Able to Click Column " + value);
								sa.assertTrue(false, " Not Able to Click Column " + value);
							}

							if (i == 0) {
								if (sortingonBasisofFolderPath) {
									System.err.println("****inside Folder sorting*****");
									break;
								}
							}
						

					} else {
						appLog.error(" Column Value Not Matched : " + value);
						sa.assertTrue(false, " Column Value Not Matched : " + value);
					}
				}

			} else {
				appLog.error(" 4 Column Are Not Showing ");
				sa.assertTrue(false, " 4 Column Are Not Showing ");
			}	
		}
	

		
		return sa;

	}
	
	public boolean fileinContengGRID(PageName pgName,Workspace workSpace,String fileName){
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		int i =0;
		boolean flag=false;
		while(true) {
			List<WebElement> ele=fp.getColumnHeads(pgName, workSpace);
			List<WebElement>listOfVisibleElements = fp.getContentGridDocNameList(workSpace, pgName);
			for (int j = 0;j<listOfVisibleElements.size();j++) {
				String a = listOfVisibleElements.get(j).getText().trim();
				if (a.equalsIgnoreCase(fileName)) {
					appLog.info(fileName+" is available in the"+j+"th iteration content grid");
					flag=true;
					break;
				}
				if(j==listOfVisibleElements.size()-1){
					appLog.error(fileName+"file is not present in the  content grid");
				}
			}
			if(flag){
				break;					
			}else {
				i++;
				if(i==2){
					appLog.error(fileName+" is not available in the content grid ");
					sa.assertTrue(false,fileName+" is not available in the content grid");
					break;
				}
			}
			click(driver, ele.get(0),"document name column head", action.SCROLLANDBOOLEAN);
		}
		
		return flag;
		
	}
	
	/**
	 * @author Akul Bhutani
	 * @param adminEmail
	 * @param userName
	 * @param userEmailID
	 * @param watermarking
	 * @param manageApproval
	 * @param access
	 * @return true/false
	 */
	public boolean preCondition(String adminEmail,String userName, String userEmailID, EnableDisable watermarking, EnableDisable manageApproval, accessType access) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(adminEmail, adminPassword);
		boolean flag1 = false,flag2=false;
		if (clickOnTab(TabName.NIMTab)) {
			if (manageApproval == EnableDisable.Enable) {
				appLog.info("enabling manage approval");
				switchToFrame(driver, 30, getFrame(PageName.NavatarInvestorManager, 10));
				if (np.clickOnSideMenusTab(sideMenu.ManageApprovals)) {
					if (np.clickOnEditIcon()) {
						if (!isSelected(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.Edit), "checkbox to tick manage approvals")) {
							if (click(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.Edit),
									"Manage Approval activate checkbox", action.SCROLLANDBOOLEAN)) {
								if (click(driver, np.getManageApprovalSaveButton(60), "Manage Approval Save button",
										action.SCROLLANDBOOLEAN)) {
									if (click(driver, np.getManageApprovalActivateYesButton(60), "Yes Button",
											action.SCROLLANDBOOLEAN)) {
										ThreadSleep(2000);
										if (isSelected(driver,np.getManageApprovalsActivateCheckbox(EditViewMode.View),
												"Manage Approval Activate checkbox")) {
											appLog.info("manage approval is now activated successfully");
											flag1 = true;
										}
										else {
											appLog.error("manage approval checkbox is not activated");
										}
									}
									else {
										appLog.error("manage approval yes button is not clickable");
									}
								}
								else {
									appLog.error("save button on manage approval page is not clickable");
								}
							}
							else {
								appLog.error("manage approval checkbox is not clickable");
							}
						}
						else {
							appLog.info("manage approval checkbox is already selected by default");
						}
					}
					else {
						appLog.error("edit icon is not clickable so cannot activate manage approval");
					}
				}
				switchToDefaultContent(driver);		
			}
			else if (manageApproval == EnableDisable.Disable){
				appLog.info("disabling manage approval");
				switchToFrame(driver, 30, getFrame(PageName.NavatarInvestorManager, 10));
				flag1 = np.deactivateManageApprovalsSetting();
				switchToDefaultContent(driver);

			}
			if (watermarking == EnableDisable.Enable) {
				appLog.info("enabling watermarking");
				switchToFrame(driver, 30, getFrame(PageName.NavatarInvestorManager, 10));
				if (np.clickOnSideMenusTab(sideMenu.Watermarking)) {
					if (!isSelected(driver, np.getWatermarkingActivateCheckbox(60), "Watermarking Activate checkbox")) {
						if ( np.clickOnEditIcon()) {
							if (click(driver,  np.getWatermarkingActivateCheckbox(60), "Watermarking activate checkbox",
									action.SCROLLANDBOOLEAN)) {
								if (click(driver, np.getWatermarkingSaveButton(60), "Watermarking Save button",
										action.SCROLLANDBOOLEAN)) {
									ThreadSleep(2000);
									if (isSelected(driver, np.getWatermarkingActivateCheckbox(60),
											"Watermarking Activate checkbox")) {
										appLog.info("Watermarking is  now activated successfully");
										flag2 = true;
									} else {
										appLog.info("Watermarking could not be activated successfully");
									}
								} else {
									appLog.info("Not able to click on watermarking save button");
								}
							} else {
								appLog.info("Not able to click on watermarking Activate checkbox");
							}
						} else {
							appLog.info("Not able to click on edit icon");
						}
					} else {
						appLog.info("Watermarking is already activated");
					}

				} else {
					appLog.info("Not able to click on watermarking tab");
				}
				switchToDefaultContent(driver);
			}
			else if (watermarking == EnableDisable.Disable){
				appLog.info("deactivating watermarking setting");
				switchToFrame(driver, 30, getFrame(PageName.NavatarInvestorManager, 10));
				flag2 = np.deactivateWatermarkingSetting();
				switchToDefaultContent(driver);
			}
			}
		appLog.info("providing access to user "+userName);
		String[] users = userName.split("<break>");
		for (int i = 0; i < users.length; i++) {
			switchToFrame(driver, 30, getFrame(PageName.NavatarInvestorManager, 10));
			if (np.clickOnSideMenusTab(sideMenu.InternalUsers)) {
				switchToDefaultContent(driver);
				if (np.giveAccessToUserInNIMTabFromAdmin(users[i], access)) {
					appLog.info(users[i] + " was given access " + access + " successfully");
				} else {
					appLog.error("could not provide access to " + users[i]);
				}
			} else {
				appLog.error("internal users side tab is not clickable : "+users[i]);
			}
			switchToDefaultContent(driver);
		}
		
		lp.CRMlogout();
		String[] mailId = userEmailID.split("<break>");
		for(int k =0;k<mailId.length;k++){
		lp.CRMLogin(mailId[k], adminPassword);
		 if(np.clickOnTab(TabName.NIMTab)){
			 switchToFrame(driver, 30, getFrame(PageName.NavatarInvestorManager, 10));
			 if(click(driver, np.getRegistrationSuccessfulCloseBtn(60), "Registration successful popup close button", action.SCROLLANDBOOLEAN)){
				 appLog.info("clicked on registration successful popup close button");
			 }else{
				 appLog.info("Not able to click on close button");
				 sa.assertTrue(false, "Not able to click on registration successful popup close button");
			 }		 
		 }else{
			 appLog.info("Not able to click on Nim Tab");
			 sa.assertTrue(false, "Not able to click on Nim Tab");
			 
		 }
		 lp.CRMlogout();
		}
		 return flag1&&flag2;

	}

	
	/**
	 * @author Parul Singh
	 * @param fundName
	 * @param ContactFirstName
	 * @param ContactLastName
	 * @param workspace
	 * @param ContactVariableName
	 * @param fundVariableName
	 * @return SoftAssert
	 */
	public SoftAssert writeAlertCountInExcel(String fundName,String ContactFirstName,String ContactLastName,Workspace workspace,String ContactVariableName,String fundVariableName){
		HomePageBusineesLayer hp=new HomePageBusineesLayer(driver);
		FundsPageBusinessLayer fp=new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp=new ContactPageBusinessLayer(driver);
		SoftAssert saa=new SoftAssert();
		scrollDownThroughWebelement(driver, getFrame( PageName.HomePage, 60), "Home Page alert Frame");
		switchToFrame(driver, 30, getFrame( PageName.HomePage, 60));
		ThreadSleep(2000);
		if(hp.alertRecordCountAtHomePage()){
			appLog.info("Successfully write home page count");
		}else{
			saa.assertTrue(false, "Not able to write home page count");
		}			
		switchToDefaultContent(driver);
		if(clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(fundName)){
				switchToFrame(driver, 30, getFrame( PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, getWorkspaceSectionView(workspace, 60),
						workspace + " View.");
			if(fp.alertRecordCountAtFundPage(workspace, PageName.FundsPage,fundVariableName)){
				appLog.info("Successfully write fund page count");			
			}else{
				saa.assertTrue(false, "Not able to write fund page count");
			}					
			}else{
				appLog.info("Not able to click on craeted fund");
				saa.assertTrue(false, "Not able to click on craeted fund");
			}
		}else{
			appLog.info("Not able to click on fund tab");
			saa.assertTrue(false, "Not able to click on fund tab");
		}
		switchToDefaultContent(driver);
		if(clickOnTab(TabName.ContactTab)){
			if(cp.clickOnCreatedContact(ContactFirstName,ContactLastName, null)){
				switchToFrame(driver, 30,getFrame( PageName.ContactsPage, 60));
				scrollDownThroughWebelement(driver, getWorkspaceSectionView(workspace, 60),
						workspace + " View.");
				if(cp.alertRecordCountAtContactPage(workspace, PageName.ContactsPage,ContactVariableName)){
					appLog.info("Successfully write Contact page count");			
				}else{
					saa.assertTrue(false, "Not able to write Contact page count");
				}		
			}else{
				appLog.info("Not able to click on craeted contact");
				saa.assertTrue(false, "Not able to click on created contact");
			}
			
		}else{
			appLog.info("Not able to click on contact tab");
			saa.assertTrue(false, "Not able to click on contact tab");			
		}
		switchToDefaultContent(driver);
		return saa;
		}
	
	/**
	 * @author Parul Singh
	 * @param recValue
	 * @param pageName
	 * @param workspace
	 * @return Boolean
	 */
	public boolean matchALertRecordCOunt(String recValue,PageName pageName,Workspace workspace){
		HomePageBusineesLayer hp=new HomePageBusineesLayer(driver);
		FundsPageBusinessLayer fp=new FundsPageBusinessLayer(driver);
		int ActualResult=0;
		if(recValue!=null){
		if(pageName.toString().equalsIgnoreCase(PageName.HomePage.toString())){
		if(hp.getRecordLabelWithValueOnHomeAlert(20)!=null){
		 ActualResult=Integer.parseInt(recValue);
		String[] recWithValue = hp.getRecordLabelWithValueOnHomeAlert(60).getText().trim().split(":");
		if(ActualResult==Integer.parseInt(recWithValue[1].trim())){
			appLog.info("Record count is verified : "+recWithValue[1]);
			return true;
		}else{
			appLog.info("Record count is not verified. Expected: "+ActualResult+" \t Actual : "+recWithValue[1]);			
		}
			}else{
				appLog.info("Not able to find alert history record label");
			}
		}else if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())){
		if(fp.getAlertHistoryPopupRecordLabelWithValue(workspace, 60)!=null){
			String[] recWithValue = fp.getAlertHistoryPopupRecordLabelWithValue(workspace, 60).getText().trim().split(":");
			ActualResult=Integer.parseInt(recValue);
			if(ActualResult==Integer.parseInt(recWithValue[1].trim())){
			appLog.info("Record count is verified : "+recWithValue[1]);
			return true;
		}else{
			appLog.info("Record count is not verified Expected: "+ActualResult+" \t Actual : "+recWithValue[1]);			
		}	
		}else{
			appLog.info("Not able to find alert history record label");
		}	
		}
	}else{
		appLog.info("There is no value in excel so we cannot verify record count");
	}
	return false;
	}
	
	/**
	 * @author Parul Singh
	 * @param ActivityType
	 * @param contactName
	 * @return true/False
	 */
	public boolean clickOnActiivityTypeLinkBasedOnContact(String ActivityType,String contactName){
		WebElement ele=FindElement(driver, "//a[text()='"+contactName+"']/../..//span[2]//a[text()='"+ActivityType+"']", "Activity type", action.SCROLLANDBOOLEAN, 60);
		if(ele!=null){
			if(click(driver, ele, "Activity type", action.SCROLLANDBOOLEAN)){
				appLog.info("Clicked on activity type");
				return true;
			}else{
				appLog.info("Not able to click on activity type");
			}
		}else{
			appLog.info("Not able to find element so not able to click");
		}
			return false;		
	}
	
//	public boolean verifyDocumentAlerts(PageName pageName,String ActivityType,String fundNAme,String workspace,String DocumentName,String ContactName,String date,String firmName){
//		if(pageName.toString().equalsIgnoreCase(PageName.HomePage.toString())){
//			WebElement ele=FindElement(driver, "//span[text()='"+ActivityType+"']/../..//span[contains(@id,'myGridACTALT-cell-1')]/../..//span[text()='"+workspace+"']/../..//a[text()='"+fundNAme+"']/../..//span[4]/../..//span[5]//a[text()='"+DocumentName+"']/../..//span[6]/span[text()='"+firmName+"']/../..//span[7]//a[text()='"+ContactName+"']/../..//span[8]/div[text()='"+getSystemDate("M/dd/yyyy")+"' or text()='"+previousOrForwardDate(-1, "M/dd/yyyy")+"' or text()='"+getSystemDate("MM/dd/yyyy")+"' or text()='"+previousOrForwardDate(-1, "MM/dd/yyyy")+"']", "Alert grid", action.SCROLLANDBOOLEAN, 60);
//			if(ele!=null){
//				return true;
//			} else {
//			appLog.info("Not able to find the element");
//			}		
//		}else if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())){
//			WebElement ele=FindElement(driver, "//span[text()='"+ActivityType+"']/../..//span[3]//a[text()='"+DocumentName+"']/../..//span[4]/span[text()='"+firmName+"']/../..//span[5]/a[text()='"+ContactName+"']/../..//span[6]/div[text()='"+getSystemDate("M/dd/yyyy")+"' or text()='"+previousOrForwardDate(-1, "M/dd/yyyy")+"' or text()='"+getSystemDate("MM/dd/yyyy")+"' or text()='"+previousOrForwardDate(-1, "MM/dd/yyyy")+"']", "Alert grid", action.SCROLLANDBOOLEAN, 60);
//			if(ele!=null){
//				return true;
//			} else {
//				appLog.info("Not able to find the element");
//			}	
//		}
//			return false;
//		}
	
	
	/**
	 * @author Parul Singh
	 */
	public void clickOnDownloadDocumentCancelButton(){
		Robot rob;
		try {
			rob = new Robot();ThreadSleep(2000);
			for(int i=0;i<4;i++){
				rob.keyPress(KeyEvent.VK_TAB);
				ThreadSleep(500);
				rob.keyRelease(KeyEvent.VK_TAB);
				}
				ThreadSleep(500);
				rob.keyPress(KeyEvent.VK_ENTER);
				ThreadSleep(500);
				rob.keyRelease(KeyEvent.VK_ENTER);
				ThreadSleep(500);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			appLog.info("Inside catch block");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @author Parul Singh
	 */
	public void clickOnDownloadDocumentSaveButton(){
		Robot rob;
		try {
			rob = new Robot();ThreadSleep(2000);
			for(int i=0;i<3;i++){
				rob.keyPress(KeyEvent.VK_TAB);
				ThreadSleep(500);
				rob.keyRelease(KeyEvent.VK_TAB);
				}
				ThreadSleep(500);
				rob.keyPress(KeyEvent.VK_ENTER);
				ThreadSleep(500);
				rob.keyRelease(KeyEvent.VK_ENTER);
				ThreadSleep(500);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			appLog.info("Inside catch block");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @author Ankur Rana
	 * @return downlodedFileName
	 */
	public String getDownloadedFileName(){		  
		  WebElement ele=driver.findElement(By.cssSelector("downloads-manager"));
		  System.out.println("Successfully found download manager");
		  ele=expandRootElement(ele);
		  System.out.println("Successfully expanded shadow root1");
		  ele=ele.findElement(By.cssSelector("downloads-item"));
		  System.out.println("Successfully found download item");
		  ele=expandRootElement(ele);
		  System.out.println("Successfully expanded shadow root3");
		  ele=ele.findElement(By.cssSelector("#file-link"));
		  System.out.println("Successfully found name");
		  return ele.getText();
		 }
	
	/**
	 * @author Ankur Rana
	 * @param element
	 * @return WebElement
	 */
	public WebElement expandRootElement(WebElement element) {
		  WebElement ele = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot",element);
		  return ele;
		 }

	/**
	 * @author Parul Singh
	 * @param fundName
	 * @param scrollBoxele
	 * @return true/False
	 */
	public boolean clickOnfundNameInAlert(String fundName,WebElement scrollBoxele){
		WebElement ele=null;
		WebElement pageHeader=null;
		  int j = 0;
		  //  WebElement scrollBoxele = FindElement(driver, "//span[@id='myGrid_firmAllDoc-scroll-box']", "Scroll Pop Up",action.SCROLLANDBOOLEAN, timeout);
		  By elementToSearch = By.xpath("//a[text()=\""+fundName+"\"]");
		  String elementTOSearch = "//a[text()=\""+fundName+"\"]";
		  int widgetTotalScrollingHeight = Integer.parseInt(String.valueOf(((JavascriptExecutor) driver)
		    .executeScript("return arguments[0].scrollHeight", scrollBoxele)));
		  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(0,0)",scrollBoxele);
		  for (int i = 0; i <= widgetTotalScrollingHeight / 25; i++) {
		   if (!driver.findElements(elementToSearch).isEmpty() && driver.findElement(elementToSearch).isDisplayed()) {
		    appLog.info("Element Successfully Found and displayed");
		    ThreadSleep(500);
		    ele=FindElement(driver, elementTOSearch, "", action.BOOLEAN, 20);
		    if(ele!=null) {
		     if(click(driver, ele, "", action.BOOLEAN)){
		      System.err.println("Clicked ON Element");
		      String ParentID=switchOnWindow(driver);
				if(ParentID!=null){
			 pageHeader=FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
			if(pageHeader.getText().trim().contains(fundName))	{
				appLog.info("Fund Page is opening");
				}else{
				appLog.info("Fund page is not opening");
				return false;
				}				
			driver.close();
			driver.switchTo().window(ParentID);
				}else{
					appLog.info("No new window is open");
			}		      
		     }else {
		      appLog.error("Not able to click on Contact "+fundName);
		      return false;
		     }
		    }
		    break;
		   } else {
		    System.out.println("Not FOund: "+elementToSearch.toString());
		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(" + j + "," + (j = j + 45) + ")",
		      scrollBoxele);
		    try {
		     Thread.sleep(1000);
		    } catch (InterruptedException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }
		    if (i == widgetTotalScrollingHeight / 50) {
		     return false;
		    }
		   }
		  }
		  return true;
	}
	
	
	/**
	 * @author Parul Singh
	 * @param ContactName
	 * @param scrollBoxele
	 * @param workspace
	 * @return true/False
	 */
	public boolean clickOnContactNameInAlert(String ContactName,WebElement scrollBoxele,Workspace workspace){
		WebElement ele=null;
	  int j = 0;
		  //  WebElement scrollBoxele = FindElement(driver, "//span[@id='myGrid_firmAllDoc-scroll-box']", "Scroll Pop Up",action.SCROLLANDBOOLEAN, timeout);
		  By elementToSearch = By.xpath("//a[text()=\""+ContactName+"\"]");
		  String elementTOSearch = "//a[text()=\""+ContactName+"\"]";
		  int widgetTotalScrollingHeight = Integer.parseInt(String.valueOf(((JavascriptExecutor) driver)
		    .executeScript("return arguments[0].scrollHeight", scrollBoxele)));
		  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(0,0)",scrollBoxele);
		  for (int i = 0; i <= widgetTotalScrollingHeight / 25; i++) {
		   if (!driver.findElements(elementToSearch).isEmpty() && driver.findElement(elementToSearch).isDisplayed()) {
		    appLog.info("Element Successfully Found and displayed");
		    ThreadSleep(500);
		    ele=FindElement(driver, elementTOSearch, "", action.BOOLEAN, 20);
		    if(ele!=null) {
		     if(click(driver, ele, "", action.BOOLEAN)){
		      System.err.println("Clicked ON Element");
		      String ParentID=switchOnWindow(driver);
				if(ParentID!=null){
			switchToFrame(driver, 30, getFrame( PageName.ContactsPage, 30));
			scrollDownThroughWebelement(driver, getWorkspaceSectionView(workspace, 30), workspace.toString()+" Section view");
			if(new ContactPageBusinessLayer(driver).getRemoveContactAccessButton(workspace, 60)!=null)	{
				appLog.info("contact Page is opening");
				}else{
				appLog.info("contact page is not opening");
				return false;
				}				
			driver.close();
			driver.switchTo().window(ParentID);
				}else{
					appLog.info("No new window is open");
			}		      
		     }else {
		      appLog.error("Not able to click on Contact "+ContactName);
		      return false;
		     }
		    }
		    break;
		   } else {
		    System.out.println("Not FOund: "+elementToSearch.toString());
		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(" + j + "," + (j = j + 45) + ")",
		      scrollBoxele);
		    try {
		     Thread.sleep(1000);
		    } catch (InterruptedException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }
		    if (i == widgetTotalScrollingHeight / 50) {
		     return false;
		    }
		   }
		  }
		  return true;
	}
	
	/**
	 * @author Parul Singh
	 * @param DocumentName
	 * @param activityType
	 * @param contactName
	 * @param scrollBoxele
	 * @param pageName
	 * @param fundName
	 * @param workspace
	 * @return true/False
	 */
	public boolean clickOnDocumentNameInAlert(String DocumentName, String activityType, String contactName,
			WebElement scrollBoxele, PageName pageName, String fundName, String workspace) {
		WebElement ele = null;
		int j = 0;
		if (pageName.toString().equalsIgnoreCase(PageName.HomePage.toString())) {
			By elementToSearch = By.xpath("//span[text()=\"" + activityType + "\"]/following-sibling::span[1]/a[text()=\""
					+ fundName + "\"]/../following-sibling::span[text()=\"" + workspace
					+ "\"]/following-sibling::span[3]/a[text()=\"" + contactName
					+ "\"]/../preceding-sibling::span[2]/a[text()=\"" + DocumentName + "\"]");
			String elementTOSearch = "//span[text()=\"" + activityType + "\"]/following-sibling::span[1]/a[text()=\""
					+ fundName + "\"]/../following-sibling::span[text()=\"" + workspace
					+ "\"]/following-sibling::span[3]/a[text()=\"" + contactName
					+ "\"]/../preceding-sibling::span[2]/a[text()=\"" + DocumentName + "\"]";
			int widgetTotalScrollingHeight = Integer.parseInt(String.valueOf(
					((JavascriptExecutor) driver).executeScript("return arguments[0].scrollHeight", scrollBoxele)));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(0,0)", scrollBoxele);
			for (int i = 0; i <= widgetTotalScrollingHeight / 25; i++) {
				if (!driver.findElements(elementToSearch).isEmpty()
						&& driver.findElement(elementToSearch).isDisplayed()) {
					appLog.info("Element Successfully Found and displayed");
					ThreadSleep(500);
					ele = FindElement(driver, elementTOSearch, "", action.BOOLEAN, 20);
					if (ele != null) {
						if (click(driver, ele, "", action.BOOLEAN)) {
							System.err.println("Clicked ON Element");			
						} else {
							appLog.error("Not able to click on Contact " + contactName);
							return false;
						}
					}
					break;
				} else {
					System.out.println("Not FOund: " + elementToSearch.toString());
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(" + j + "," + (j = j + 45) + ")",
							scrollBoxele);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (i == widgetTotalScrollingHeight / 50) {
						return false;
					}
				}
			}
		} else if (pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())
				|| pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {
			By elementToSearch = By.xpath("//span[text()=\"" + activityType + "\"]/following-sibling::span[3]/a[text()=\""
					+ contactName + "\"]/../preceding-sibling::span[2]/a[text()=\"" + DocumentName + "\"]");
			String elementTOSearch = "//span[text()=\"" + activityType + "\"]/following-sibling::span[3]/a[text()=\""
					+ contactName + "\"]/../preceding-sibling::span[2]/a[text()=\"" + DocumentName + "\"]";
			int widgetTotalScrollingHeight = Integer.parseInt(String.valueOf(
					((JavascriptExecutor) driver).executeScript("return arguments[0].scrollHeight", scrollBoxele)));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(0,0)", scrollBoxele);
			for (int i = 0; i <= widgetTotalScrollingHeight / 25; i++) {
				if (!driver.findElements(elementToSearch).isEmpty()
						&& driver.findElement(elementToSearch).isDisplayed()) {
					appLog.info("Element Successfully Found and displayed");
					ThreadSleep(500);
					ele = FindElement(driver, elementTOSearch, "", action.BOOLEAN, 20);
					if (ele != null) {
						if (click(driver, ele, "", action.BOOLEAN)) {
							System.err.println("Clicked ON Element");
						} else {
							appLog.error("Not able to click on Contact " + contactName);
							return false;
						}
					}
					break;
				} else {
					System.out.println("Not FOund: " + elementToSearch.toString());
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(" + j + "," + (j = j + 45) + ")",
							scrollBoxele);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (i == widgetTotalScrollingHeight / 50) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * @author Parul Singh
	 * @param activityType
	 * @param scrollBoxEle
	 * @return true/False
	 */
	public boolean verifyAlerts(String activityType,WebElement scrollBoxEle) {
		String xpath = "//span[text()='" + activityType + "']";
		Integer[] values = null;
		ThreadSleep(5000);
		boolean flag = true;
		if(activityType.equalsIgnoreCase("Contact Profile Updated") || activityType.equalsIgnoreCase("Firm Profile Updated")){
			xpath = "//a[text()='"+activityType+"']";
		}
		for(int j = 0; j >= 0; j=j+100){
			List<WebElement> activityTypeAlert = driver.findElements(By.xpath(xpath));
			if(!activityTypeAlert.isEmpty()){
				for (int i = 0; i < activityTypeAlert.size(); i++) {
					String alertType = "";
					if(activityType.equalsIgnoreCase("Contact Profile Updated") || activityType.equalsIgnoreCase("Firm Profile Updated")){
						alertType = activityTypeAlert.get(i).getAttribute("title").trim();
					} else {
						alertType = activityTypeAlert.get(i).getText().trim();
					}
					System.out.println(alertType);
					if (!alertType.isEmpty() && alertType.contains(activityType)) {
						appLog.info(activityType + " Alerts are displaying");

					} else if(!alertType.isEmpty()) {
						appLog.info(activityType + " Alerts are not displaying");
						return false;
					}
				}
			} else {
				appLog.info(activityType+"  alert is not present in the Alert Section.");
//				return false; 
				flag = false;
			}
			values = scrollActiveWidget(driver, scrollBoxEle, 200, j);
			if(values[0]<=values[1]){
				break;
			}
		}
		return flag;
	}

	/**
	 * @author Parul Singh
	 * @param pageName
	 * @param workspace
	 * @return softAssert
	 */
	public SoftAssert verifySortingOnAllPagesInALert(PageName pageName,Workspace workspace){
		SoftAssert sa=new SoftAssert();
		WebElement ele=null;
		List<WebElement> colValue=null;
		if(pageName.toString().equalsIgnoreCase(PageName.HomePage.toString())){
			String colName[]={"//span[text()='Activity Type']","//span[text()='Fund']","//span[text()='Workspace']","//span[text()='Document']"," //span[text()='Firm']","//span[text()='Contact']"," //span[text()='Date']"};
			String values[]={"//span[@id='myGridACTALT-rows']//span[contains(@id,'myGridACTALT-cell-0-')]","//span[@id='myGridACTALT-rows']//span[contains(@id,'myGridACTALT-cell-1-')]/a","//span[@id='myGridACTALT-rows']//span[contains(@id,'myGridACTALT-cell-2-')]","//span[@id='myGridACTALT-rows']//span[contains(@id,'myGridACTALT-cell-3-')]/a"
							,"//span[@id='myGridACTALT-rows']//span[contains(@id,'myGridACTALT-cell-4-')]/span","//span[@id='myGridACTALT-rows']//span[contains(@id,'myGridACTALT-cell-5-')]/a","//span[@id='myGridACTALT-rows']//span[contains(@id,'myGridACTALT-cell-6-')]/div"};
			System.err.println(colName.length);
			System.err.println(values.length);
			for (int i = colName.length-1; i >= 0; i--) {
			if(i!=2){
				ele=FindElement(driver, colName[i], "Coloumn Label", action.SCROLLANDBOOLEAN, 60);		
				ThreadSleep(3000);
				if(click(driver, ele, "Coloumn Name", action.SCROLLANDBOOLEAN)){
					ThreadSleep(5000);
					colValue=FindElements(driver, values[i], "Coloumn Xpath");
					if(checkSorting(SortOrder.Assecending, colValue)){
						appLog.info("Ascendind sorting is verified : "+i);
					}else{
						appLog.info("Ascending sorting is not verified :"+i);
						sa.assertTrue(false, "Ascending sorting is not verified :"+i);
					}					
				}else{
					appLog.info("Not able to click on coloumn name :"+i);
					sa.assertTrue(false, "Not able to click on coloumn name :"+i);
				}				
				ele=FindElement(driver, colName[i], "Coloumn Label", action.SCROLLANDBOOLEAN, 60);				
				if(click(driver, ele, "Coloumn Name", action.SCROLLANDBOOLEAN)){
					ThreadSleep(5000);
					colValue=FindElements(driver, values[i], "Coloumn Xpath");
					if(checkSorting(SortOrder.Decending, colValue)){
						appLog.info("Decending sorting is verified : "+i);
					}else{
						appLog.info("Decending sorting is not verified :"+i);
						sa.assertTrue(false, "Decending sorting is not verified :"+i);
					}					
				}else{
					appLog.info("Not able to click on coloumn name :"+i);
					sa.assertTrue(false, "Not able to click on coloumn name :"+i);
				}			
			}	
			}				
		}else if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())){
			String workspaceSelector = "";
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "FR";
				} else {
				workspaceSelector = "INV";
				}			
			String colName[]={"//span[text()='Activity Type']","//span[text()='Document']","//span[contains(@id,'myGridACTALT"+workspaceSelector+"')]//span[text()='Firm']","//span[text()='Contact']"," //span[text()='Date']"};
			String values[]={"//span[@id='myGridACTALT"+workspaceSelector+"-rows']//span[contains(@id,'myGridACTALT"+workspaceSelector+"-cell-0-')]","//span[@id='myGridACTALT"+workspaceSelector+"-rows']//span[contains(@id,'myGridACTALT"+workspaceSelector+"-cell-1-')]/a"," //span[@id='myGridACTALT"+workspaceSelector+"-rows']//span[contains(@id,'myGridACTALT"+workspaceSelector+"-cell-2-')]/span",
							"//span[@id='myGridACTALT"+workspaceSelector+"-rows']//span[contains(@id,'myGridACTALT"+workspaceSelector+"-cell-3-')]/a","//span[@id='myGridACTALT"+workspaceSelector+"-rows']//span[contains(@id,'myGridACTALT"+workspaceSelector+"-cell-4-')]/div"};	
			System.err.println(colName.length);
			System.err.println(values.length);
			for (int i = colName.length-1; i >= 0; i--) {
				ele=FindElement(driver, colName[i], "Coloumn Label", action.SCROLLANDBOOLEAN, 60);	
				ThreadSleep(3000);
				if(click(driver, ele, "Coloumn Name", action.SCROLLANDBOOLEAN)){
					ThreadSleep(5000);
					colValue=FindElements(driver, values[i], "Coloumn Xpath");
					if(checkSorting(SortOrder.Assecending, colValue)){
						appLog.info("Ascendind sorting is verified : "+i);
					}else{
						appLog.info("Ascending sorting is not verified :"+i);
						sa.assertTrue(false, "Ascending sorting is not verified :"+i);
					}					
				}else{
					appLog.info("Not able to click on coloumn name :"+i);
					sa.assertTrue(false, "Not able to click on coloumn name :"+i);
				}				
				ele=FindElement(driver, colName[i], "Coloumn Label", action.SCROLLANDBOOLEAN, 60);				
				if(click(driver, ele, "Coloumn Name", action.SCROLLANDBOOLEAN)){
					ThreadSleep(5000);
					colValue=FindElements(driver, values[i], "Coloumn Xpath");
					if(checkSorting(SortOrder.Decending, colValue)){
						appLog.info("Decending sorting is verified : "+i);
					}else{
						appLog.info("Decending sorting is not verified :"+i);
						sa.assertTrue(false, "Decending sorting is not verified :"+i);
					}					
				}else{
					appLog.info("Not able to click on coloumn name :"+i);
					sa.assertTrue(false, "Not able to click on coloumn name :"+i);
				}			
			}				
		}
		return sa;		
	}
		
	/**
	 * @author Parul Singh
	 * @param pageName
	 * @param workspace
	 * @param profileUpdatedAlert
	 * @return softassert
	 */
	public SoftAssert verifySortingOnProfileUpdatedAlerts(PageName pageName,Workspace workspace,profileUpdatedAlert profileUpdatedAlert){
		SoftAssert sa=new SoftAssert();
		WebElement ele=null;
		List<String> Listvalues=new ArrayList<String>();
		List<String> columnName=new ArrayList<String>();
		List<WebElement> colValue=null;
		if(pageName.toString().equalsIgnoreCase(PageName.HomePage.toString())){
			String colName[]={"//span[text()='Field']","//span[text()='New Value']"};
			if(profileUpdatedAlert.toString().equalsIgnoreCase(profileUpdatedAlert.FirmProfileUpdated.toString())){
				String[] values={"//span[@id='grid_AccountProfileUpdatedACTALT-rows']/span/span[contains(@id,'grid_AccountProfileUpdatedACTALT-cell-0-')]","//span[@id='grid_AccountProfileUpdatedACTALT-rows']/span/span[contains(@id,'grid_AccountProfileUpdatedACTALT-cell-1-')]"};
			for (String string : values) {
				Listvalues.add(string);
			}
			}else{
				String[] values ={"//span[@id='grid_ContactProfileUpdatedACTALT-rows']/span/span[contains(@id,'grid_ContactProfileUpdatedACTALT-cell-0-')]","//span[@id='grid_ContactProfileUpdatedACTALT-rows']/span/span[contains(@id,'grid_ContactProfileUpdatedACTALT-cell-1-')]"};
				for (String string : values) {
					Listvalues.add(string);
				}
			}
			for (int i = colName.length-1; i >= 0; i--) {
				ele=FindElement(driver, colName[i], "Coloumn Label", action.SCROLLANDBOOLEAN, 60);				
				if(click(driver, ele, "Coloumn Name", action.SCROLLANDBOOLEAN)){
					ThreadSleep(5000);
					colValue=FindElements(driver, Listvalues.get(i), "Coloumn Xpath");
					if(checkSorting(SortOrder.Assecending, colValue)){
						appLog.info("Ascendind sorting is verified : "+i);
					}else{
						appLog.info("Ascending sorting is not verified :"+i);
						sa.assertTrue(false, "Ascending sorting is not verified :"+i);
					}					
				}else{
					appLog.info("Not able to click on coloumn name :"+i);
					sa.assertTrue(false, "Not able to click on coloumn name :"+i);
				}				
				ele=FindElement(driver, colName[i], "Coloumn Label", action.SCROLLANDBOOLEAN, 60);				
				if(click(driver, ele, "Coloumn Name", action.SCROLLANDBOOLEAN)){
					ThreadSleep(5000);
					colValue=FindElements(driver, Listvalues.get(i), "Coloumn Xpath");
					if(checkSorting(SortOrder.Decending, colValue)){
						appLog.info("Decending sorting is verified : "+i);
					}else{
						appLog.info("Decending sorting is not verified :"+i);
						sa.assertTrue(false, "Decending sorting is not verified :"+i);
					}					
				}else{
					appLog.info("Not able to click on coloumn name :"+i);
					sa.assertTrue(false, "Not able to click on coloumn name :"+i);
				}			
			}						
		}else if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())){
			String workspaceSelector = "";
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "FR";
				} else {
				workspaceSelector = "INV";
				}			
			if(profileUpdatedAlert.toString().equalsIgnoreCase(profileUpdatedAlert.FirmProfileUpdated.toString())){
				String colName[]={"//span[contains(@id,'grid_AccountProfileUpdatedACTALT"+workspaceSelector+"-header-0-')]//span[text()='Field']","//span[contains(@id,'grid_AccountProfileUpdatedACTALT"+workspaceSelector+"-header-1-')]//span[text()='New Value']"};
				for (String string : colName) {
					columnName.add(string);
				}
				String[] values={"//span[@id='grid_AccountProfileUpdatedACTALT"+workspaceSelector+"-rows']/span/span[contains(@id,'grid_AccountProfileUpdatedACTALT"+workspaceSelector+"-cell-0-')]","//span[@id='grid_AccountProfileUpdatedACTALT"+workspaceSelector+"-rows']/span/span[contains(@id,'grid_AccountProfileUpdatedACTALT"+workspaceSelector+"-cell-1-')]"};
			for (String string : values) {
				Listvalues.add(string);
			}
			}else{
				String colName[]={"//span[contains(@id,'grid_ContactProfileUpdatedACTALT"+workspaceSelector+"-header-0-')]//span[text()='Field']","//span[contains(@id,'grid_ContactProfileUpdatedACTALT"+workspaceSelector+"-header-1-')]//span[text()='New Value']"};
				for (String string : colName) {
					columnName.add(string);
				}
				String[] values ={"//span[@id='grid_ContactProfileUpdatedACTALT"+workspaceSelector+"-rows']/span/span[contains(@id,'grid_ContactProfileUpdatedACTALT"+workspaceSelector+"-cell-0-')]","//span[@id='grid_ContactProfileUpdatedACTALT"+workspaceSelector+"-rows']/span/span[contains(@id,'grid_ContactProfileUpdatedACTALT"+workspaceSelector+"-cell-1-')]"};
				for (String string : values) {
					Listvalues.add(string);
				}
			}
			for (int i = columnName.size()-1; i >= 0; i--) {
				ele=FindElement(driver, columnName.get(i), "Coloumn Label", action.SCROLLANDBOOLEAN, 60);				
				if(click(driver, ele, "Coloumn Name", action.SCROLLANDBOOLEAN)){
					ThreadSleep(5000);
					colValue=FindElements(driver,Listvalues.get(i), "Coloumn Xpath");
					if(checkSorting(SortOrder.Assecending, colValue)){
						appLog.info("Ascending sorting is verified : "+i);
					}else{
						appLog.info("Ascending sorting is not verified :"+i);
						sa.assertTrue(false, "Ascending sorting is not verified :"+i);
					}					
				}else{
					appLog.info("Not able to click on coloumn name :"+i);
					sa.assertTrue(false, "Not able to click on coloumn name :"+i);
				}				
				ele=FindElement(driver,columnName.get(i), "Coloumn Label", action.SCROLLANDBOOLEAN, 60);				
				if(click(driver, ele, "Coloumn Name", action.SCROLLANDBOOLEAN)){
					ThreadSleep(5000);
					colValue=FindElements(driver, Listvalues.get(i), "Coloumn Xpath");
					if(checkSorting(SortOrder.Decending, colValue)){
						appLog.info("Decending sorting is verified : "+i);
					}else{
						appLog.info("Decending sorting is not verified :"+i);
						sa.assertTrue(false, "Decending sorting is not verified :"+i);
					}					
				}else{
					appLog.info("Not able to click on coloumn name :"+i);
					sa.assertTrue(false, "Not able to click on coloumn name :"+i);
				}			
			}				
		}
		return sa;		
	}
	
	/**
	 * @author Parul Singh
	 * @param pageName
	 * @param ActivityType
	 * @param fundNAme
	 * @param workspace
	 * @param DocumentName
	 * @param ContactName
	 * @param firmName
	 * @param scrollBoxele
	 * @param date
	 * @return true/false
	 */
	public boolean verifyDocumentAlerts(PageName pageName,String ActivityType,String fundNAme,String workspace,String DocumentName,String ContactName,String firmName,WebElement scrollBoxele,String date){
	WebElement ele=null;
	  int j = 0;
		 if(pageName.toString().equalsIgnoreCase(PageName.HomePage.toString())){
			  By elementToSearch = By.xpath("//a[text()=\""+DocumentName+"\"]/..//following-sibling::span/span[@title=\""+firmName+"\"]/..//preceding-sibling::span[3]/a[text()=\""+fundNAme+"\"]/..//following-sibling::span[4]/a[text()=\""+ContactName+"\"]/..//preceding-sibling::span[text()=\""+workspace+"\"]/..//preceding-sibling::span[text()=\""+ActivityType+"\"]/..//following-sibling::span[6]/div[text()=\""+getSystemDate("M/dd/yyyy")+"\" or text()=\""+previousOrForwardDate(-1, "M/dd/yyyy")+"\" or text()=\""+getSystemDate("MM/dd/yyyy")+"\" or text()=\""+previousOrForwardDate(-1, "MM/dd/yyyy")+"\"]");
		  String elementTOSearch = "//a[text()=\""+DocumentName+"\"]/..//following-sibling::span/span[@title=\""+firmName+"\"]/..//preceding-sibling::span[3]/a[text()=\""+fundNAme+"\"]/..//following-sibling::span[4]/a[text()=\""+ContactName+"\"]/..//preceding-sibling::span[text()=\""+workspace+"\"]/..//preceding-sibling::span[text()=\""+ActivityType+"\"]/..//following-sibling::span[6]/div[text()=\""+getSystemDate("M/dd/yyyy")+"\" or text()=\""+previousOrForwardDate(-1, "M/dd/yyyy")+"\" or text()=\""+getSystemDate("MM/dd/yyyy")+"\" or text()=\""+previousOrForwardDate(-1, "MM/dd/yyyy")+"\"]";
		  int widgetTotalScrollingHeight = Integer.parseInt(String.valueOf(((JavascriptExecutor) driver)
		    .executeScript("return arguments[0].scrollHeight", scrollBoxele)));
		  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(0,0)",scrollBoxele);
		  for (int i = 0; i <= widgetTotalScrollingHeight / 25; i++) {
			   if (!driver.findElements(elementToSearch).isEmpty() && driver.findElement(elementToSearch).isDisplayed()) {
			    appLog.info("Element Successfully Found and displayed");
			    ThreadSleep(500);
			    ele=FindElement(driver, elementTOSearch, "", action.BOOLEAN, 20);
			    if(ele!=null) {
			    	return true;
			    }
			    break;
			   } else {
			    System.out.println("Not FOund: "+elementToSearch.toString());
			    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(" + j + "," + (j = j + 100) + ")",
			      scrollBoxele);
			    try {
			     Thread.sleep(1000);
			    } catch (InterruptedException e) {
			     // TODO Auto-generated catch block
			     e.printStackTrace();
			    }
			    if (i == widgetTotalScrollingHeight / 50) {
			     return false;
			    }
			   }
			  }
		 }else if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())){
			  By elementToSearch = By.xpath("//span[text()=\""+ActivityType+"\"]/../..//span[3]//a[text()=\""+DocumentName+"\"]/../..//span[4]/span[text()=\""+firmName+"\"]/../..//span[5]/a[text()=\""+ContactName+"\"]/../..//span[6]/div[text()=\""+getSystemDate("M/dd/yyyy")+"\" or text()=\""+previousOrForwardDate(-1, "M/dd/yyyy")+"\" or text()=\""+getSystemDate("MM/dd/yyyy")+"\" or text()=\""+previousOrForwardDate(-1, "MM/dd/yyyy")+"\"]");
			  String elementTOSearch = "//span[text()=\""+ActivityType+"\"]/../..//span[3]//a[text()=\""+DocumentName+"\"]/../..//span[4]/span[text()=\""+firmName+"\"]/../..//span[5]/a[text()=\""+ContactName+"\"]/../..//span[6]/div[text()=\""+getSystemDate("M/dd/yyyy")+"\" or text()=\""+previousOrForwardDate(-1, "M/dd/yyyy")+"\" or text()=\""+getSystemDate("MM/dd/yyyy")+"\" or text()=\""+previousOrForwardDate(-1, "MM/dd/yyyy")+"\"]";
			  int widgetTotalScrollingHeight = Integer.parseInt(String.valueOf(((JavascriptExecutor) driver)
			    .executeScript("return arguments[0].scrollHeight", scrollBoxele)));
			  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(0,0)",scrollBoxele);
			  for (int i = 0; i <= widgetTotalScrollingHeight / 25; i++) {
				   if (!driver.findElements(elementToSearch).isEmpty() && driver.findElement(elementToSearch).isDisplayed()) {
				    appLog.info("Element Successfully Found and displayed");
				    ThreadSleep(500);
				    ele=FindElement(driver, elementTOSearch, "", action.BOOLEAN, 20);
				    if(ele!=null) {
				    	return true;
				    }
				    break;
				   } else {
				    System.out.println("Not FOund: "+elementToSearch.toString());
				    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(" + j + "," + (j = j + 45) + ")",
				      scrollBoxele);
				    try {
				     Thread.sleep(1000);
				    } catch (InterruptedException e) {
				     // TODO Auto-generated catch block
				     e.printStackTrace();
				    }
				    if (i == widgetTotalScrollingHeight / 50) {
				     return false;
				    }
				   }
				  } 
		 }
		 return false;
	}

	/**
	 * @author Parul Singh
	 * @param PageName
	 * @param fundNameOrContactNameOrInsName
	 * @param columnName
	 * @param workspace
	 * @return softassert
	 */
	public SoftAssert verifyUpdatedNameinALerts(PageName PageName,String fundNameOrContactNameOrInsName, columnName columnName,Workspace workspace){
		SoftAssert saa=new SoftAssert();
		List< WebElement> List=new ArrayList<WebElement>();
		if(PageName.toString().equalsIgnoreCase(PageName.HomePage.toString())){
			if(columnName.toString().equalsIgnoreCase(columnName.fundName.toString())){
				List=FindElements(driver, "//span[contains(@id,'myGridACTALT-cell-1-')]/a", "Fund Name");			
			}else if(columnName.toString().equalsIgnoreCase(columnName.contactName.toString())) {
				List=FindElements(driver, "//span[contains(@id,'myGridACTALT-cell-5-')]/a", "Contact Name");		
			}else if(columnName.toString().equalsIgnoreCase(columnName.institutionName.toString())){				
				List=FindElements(driver, "//span[contains(@id,'myGridACTALT-cell-4-')]/span", "Institution Name");		
			}
		}else if(PageName.toString().equalsIgnoreCase(PageName.FundsPage.toString()) || PageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())){
			if (columnName.toString().equalsIgnoreCase(columnName.contactName.toString())) {
				if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
					List=FindElements(driver, "//span[contains(@id,'myGridACTALTFR-cell-3-')]/a", "Contact  Name");					
				}else if (workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
					List=FindElements(driver, "//span[contains(@id,'myGridACTALTINV-cell-3-')]/a", "Contact Name");					
				}
			}
			if(columnName.toString().equalsIgnoreCase(columnName.institutionName.toString())){
				if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
					List =FindElements(driver, "//span[contains(@id,'myGridACTALTFR-cell-2-')]/span", "Institution Name");					
				}else if (workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
					List =FindElements(driver, "//span[contains(@id,'myGridACTALTINV-cell-2-')]/span", "Institution Name");					
				}			
			}
		}
		if(fundNameOrContactNameOrInsName!=null){
			if(!List.isEmpty()){
				String[] splitedList=fundNameOrContactNameOrInsName.split("<break>");
				for (int i = 0; i < splitedList.length; i++) {
					for (int j = 0; j < List.size(); j++) {
						scrollDownThroughWebelement(driver, List.get(j), "");
						String aa=List.get(j).getText().trim();
						if(aa.contains(splitedList[i])){
							appLog.info(splitedList[i]+" is displaying in "+columnName+" alert grid column");
							break;					
						}else{
							if(j==List.size()-1){
								appLog.error(splitedList[i]+" is not displaying in "+columnName+" alert grid column. Expected:"+splitedList[i]+" \t Actual:"+aa);
								saa.assertTrue(false, splitedList[i]+" is not displaying in "+columnName+" alert grid column. Expected:"+splitedList[i]+" \t Actual:"+aa);
							}
						}
					}
				}
			}else{
				appLog.error(columnName+" list is not visible so cannot verify "+fundNameOrContactNameOrInsName);
				saa.assertTrue(false, columnName+" list is not visible so cannot verify "+fundNameOrContactNameOrInsName);
			}
		}
//		if(contactName!=null){
//		if(!contactNameList.isEmpty()){
//			contactNameList=FindElements(driver, "//span[contains(@id,'myGridACTALT-cell-5-')]/a", "Contact Name");	
//		String[] contact=contactName.split("<break>");
//		for (int i = 0; i < contact.length; i++) {
//			for (int j = 0; j < contactNameList.size(); j++) {
//				String aa=contactNameList.get(j).getText().trim();
//				if(aa.contains(contact[i])){
//					appLog.info("Contact name is displaying:"+contact[i]);
//					break;					
//				}else{
//					if(j==contactNameList.size()-1){
//						appLog.error("Contact Name is not displaying. Expected:" +contact[i] +"Actual:"+aa);
//						saa.assertTrue(false, "Contact Name is not displaying. Expected:" +contact[i] +"Actual:"+aa);
//					}
//				}
//			}
//		}
//		}else{
//			appLog.error("contact name list is empty so cannot verify contact name");
//			saa.assertTrue(false, "contact name list is empty so cannot verify contact name");
//		}
//		}
//		if(InstitutionName!=null){
//			institutionNameList=FindElements(driver, "//span[contains(@id,'myGridACTALT-cell-4-')]/span", "Institution Name");
//			if(!institutionNameList.isEmpty()){
//		String[] institution=InstitutionName.split("<break>");
//		for (int i = 0; i < institution.length; i++) {
//			for (int j = 0; j < institutionNameList.size(); j++) {
//				String aa=institutionNameList.get(j).getText().trim();
//				if(aa.contains(institution[i])){
//					appLog.info("Fund name is displaying:"+institution[i]);
//					break;					
//				}else{
//					if(j==institutionNameList.size()-1){
//						appLog.error("Fund Name is not displaying. Expected:" +institution[i] +"Actual:"+aa);
//						saa.assertTrue(false, "Fund Name is not displaying. Expected:" +institution[i] +"Actual:"+aa);
//					}
//				}
//			}
//		}
//		}else{
//			appLog.error("institution name list is empty so cannot verify institution name");
//			saa.assertTrue(false, "institution name list is empty so cannot verify institution name");
//		}
//		}		
		return saa;	
	}
	
	/**
	 * @author Azhar Alam
	 * @param workspace
	 * @param pName
	 * @return int
	 */
	public int getNoOfDocumentsOnGrid(Workspace workspace, PageName pName) {
		String workSpaceXpath = "",fileXpath = "";
		
		if (pName.toString().equalsIgnoreCase(PageName.FundsPage.toString())) {

			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath = "//div[@id='invworkspace']";

			} else {
			workSpaceXpath = "//div[@id='frworkspace']";

			}

		} else if (pName.toString().equalsIgnoreCase(PageName.InstitutionsPage.toString())) {

			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath = "//div[@id='Investorgrid_div']";

			} else {
				workSpaceXpath = "//div[@id='divFrWorkspace']";
			}
		} else if (pName.toString().equalsIgnoreCase(PageName.CommitmentsPage.toString())) {

			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath = "//div[@class='content_div']";

			}
			else {
				workSpaceXpath = "//div[@class='content_div']";
			}
		} else if (pName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {

			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath = "//div[@id='Investorgrid_div']";

			} else {
				workSpaceXpath = "//div[@id='divFrWorkspace']";
			}
		}
		
		if (workspace == Workspace.FundraisingWorkspace) {
			fileXpath = "//span[contains(@id,'myGridfundr-cell-0-')]//a/u";
		}
		else {
			fileXpath = "//span[contains(@id,'myGrid-cell-0-')]//a/u";
		}
		return FindElements(driver, workSpaceXpath+fileXpath, "list of documents").size();
	}
	
	/**
	 * @author Akul Bhutani
	 * @return SoftAssert
	 */
	public SoftAssert postCondition(){
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		if (np.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
			if (np.removeAllUserAccess()) {
				appLog.info("IP access has been removed for all users");
			} else {
				appLog.error("could not remove access for all users");
				sa.assertTrue(false, "could not remove access for all users");
			}
			if (np.deactivateManageApprovalsSetting()) {
				appLog.info("manage approval has been removed successfully");
			} else {
				appLog.error("could not deactivate manage approval setting");
				sa.assertTrue(false, "could not deactivate manage approval setting");
			}
			if (np.deactivateWatermarkingSetting()) {
				appLog.info("watermarking settings has been deactivated from NIM page");
			} else {
				appLog.error("could not remove watermarking setting from NIM page");
				sa.assertTrue(false, "could not remove watermarking setting from NIM page");
			}
			switchToDefaultContent(driver);
			np.clickOnTab(TabName.FundsTab);
			if (np.getMyProfileFistNameAndLastNameAndFirmName("AdminUser")) {
				appLog.info("written firm name, and user first, last name to excel for admin");
			}
			switchToDefaultContent(driver);
		} else {
			appLog.error("NIM Tab cannot be clicked, So cannot execute the post condition.");
			sa.assertTrue(false,"NIM Tab cannot be clicked, So didnot execute the post condition.");
		}
		return sa;
	}
	
	/**
	 * @author Ankur Rana
	 * @param sheetName
	 * @param institutionName
	 * @param limitedPartnerName
	 * @param fundName
	 * @param workspace
	 * @param pageName
	 * @param timeOut
	 * @return true/false
	 */
	public boolean VerifyFolderStructureBulk(String sheetName,String institutionName, String limitedPartnerName, String fundName, Workspace workspace, PageName pageName, int timeOut){
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		Map<String, String> s = folderStructureInExcel(sheetName);
		Set<String> paths = s.keySet();
		Iterator<String> i = paths.iterator();
		String lpName = null;
		String instiName = null;
		i = paths.iterator();
		FolderType folderType = null;
		boolean flag = true;
		while (i.hasNext()) {
			String string = i.next();
			lpName = null;
			instiName = null;
			if (string.isEmpty())
				continue;
			System.err.println("Folder path searching: "+string);
			System.err.println("Folder type searching: "+s.get(string));
			if(s.get(string).equalsIgnoreCase("Shared")){
				folderType=FolderType.Shared;
			} else if (s.get(string).equalsIgnoreCase("Common")){
				folderType=FolderType.Common;
			} else if (s.get(string).equalsIgnoreCase("Internal")){
				folderType=FolderType.Internal;
			} else if (s.get(string).equalsIgnoreCase("Standard")){
				folderType=FolderType.Standard;
				lpName = limitedPartnerName;
				instiName = institutionName;
			}
			if(pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.PotentialInvestmentPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.CurrentInvestmentPgae.toString())){
				timeOut = timeOut/5;
			} else if (pageName.toString().equalsIgnoreCase(PageName.CommitmentsPage.toString())){
				fundName = null;
			}
			System.err.println("LP Name: "+lpName);
			System.err.println("Insti Name: "+instiName);
			System.err.println("fundname: "+fundName);
			String path = string;
			if(instiName!=null){
				if(lpName!=null){
					path =instiName+"/"+lpName+"/"+path;
				} else {
					path =instiName+"/"+path;
				}
			}else if(lpName!=null && (pageName.toString().equalsIgnoreCase("CommitmentsPage")||pageName.toString().equalsIgnoreCase("InstitutionsPage")||pageName.toString().equalsIgnoreCase(PageName.CurrentInvestmentPgae.toString()))){
				path = lpName+"/"+path;
			}
			if(pageName.toString().equalsIgnoreCase("ContactsPage")||pageName.toString().equalsIgnoreCase("InstitutionsPage")){
				path = fundName+"/"+path;
			}
			if(fp.verifyFolderPathBulk2(string, instiName, lpName, fundName, pageName, workspace, timeOut)){
				if((pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.PotentialInvestmentPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.CurrentInvestmentPgae.toString())) && string.contains("(Internal)")){
					appLog.error("Internal folder is present on the contact page and is not verified.");
					sa.assertTrue(false,"Internal folder is present on the contact page and is not verified.");
					flag=false;
				} else {
					appLog.info("Folder path '"+string+"' is verified on '"+pageName.toString()+"'.");
				}
			} else {
				if((pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.PotentialInvestmentPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.CurrentInvestmentPgae.toString())) && string.contains("(Internal)")){
					appLog.info("Internal folder is not present on the contact page and is verified.");
				} else {
					appLog.error("Folder Path '"+string+"' is not verified on '"+pageName.toString()+"'.");
					sa.assertTrue(false,"Folder Path '"+string+"' is not verified on '"+pageName.toString()+"'.");
					flag=false;
				}
			}
		}
//			if(pageName.toString().equalsIgnoreCase(PageName.InstitutionsPage.toString())){
//				if(!string.contains("(Common)") || !string.contains("(Shared)") || !string.contains("(Internal)")){
//					if(fp.verifyFolderPathdummy(string, null, lpName, fundName, PageName.InstitutionsPage, workspace, timeOut)){
//						appLog.info("Folder path '"+string+"' is verified.");
//					} else {
//						appLog.error("Folder Path '"+string+"' is not verified on '"+institutionName+"' institutions page.");
//						sa.assertTrue(false,"Folder Path '"+string+"' is not verified on '"+institutionName+"' institutions page.");
//						flag=false;
//					}
//				} else {
//					if(fp.verifyFolderPathdummy(string, null, null, fundName, PageName.InstitutionsPage, workspace, timeOut)){
//						appLog.info("Folder path '"+string+"' is verified.");
//					} else {
//						appLog.error("Folder Path '"+string+"' is not verified on institutions page.");
//						sa.assertTrue(false,"Folder Path '"+string+"' is not verified on institutions page.");
//						flag=false;
//					}
//				}
//			} else if (pageName.toString().equalsIgnoreCase(PageName.CommitmentsPage.toString())) {
//				if(fp.verifyFolderPathdummy(string, null, lpName, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, timeOut)){
//					appLog.info("Folder path '"+string+"' is verified."); 
//				} else {
//					appLog.error("Folder path '"+string+"' is not verified on commitment page.");
//					sa.assertTrue(false,"Folder path '"+string+"' is not verified on commitment page.");
//					flag=false;
//				}
//			} else if (pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString()) && !string.contains("(Internal)")){
//				if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
//					if(fp.verifyFolderPathdummy(string, institutionName, null, fundName, PageName.ContactsPage, workspace, timeOut)){
//						appLog.info("Folder path '"+string+"' is verified."); 
//					} else {
//						appLog.error("Folder path '"+string+"' is not verified on contact page for workspace "+workspace.toString());
//						sa.assertTrue(false,"Folder path '"+string+"' is not verified on contact page for workspace "+workspace.toString());
//						flag=false;
//					}
//				} else {
//					if(fp.verifyFolderPathdummy(string, institutionName, lpName, fundName, PageName.ContactsPage, workspace, timeOut)){
//						appLog.info("Folder path '"+string+"' is verified."); 
//					} else {
//						appLog.error("Folder path '"+string+"' is not verified on contact page for workspace "+workspace.toString());
//						sa.assertTrue(false,"Folder path '"+string+"' is not verified on contact page for workspace "+workspace.toString());
//						flag=false;
//					}
//				}
//			}
		return flag;
	}

	/**
	 * @author Ankur Rana
	 * @param pName
	 * @param workspace
	 * @param fileName
	 * @return true/false
	 */
	public boolean verifyFileInContentGridWithBigDocNames(PageName pName, Workspace workspace,String fileName) {

		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		
		if(pName.toString().equalsIgnoreCase(PageName.CommitmentsPage.toString())){
			fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30);	
		}else{
			fp.ContentGridRefreshBtn(workspace, 30);
		}
		
		List<String> docName = new ArrayList<String>();
		String workSpaceXpath = null;
		if (pName.toString().equalsIgnoreCase(PageName.FundsPage.toString())) {
			System.err.println("<<<<<<<<<<Fund>>>>>>>>>>>>>");

			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath = "//div[@id='invworkspace']";

			} else {
			workSpaceXpath = "//div[@id='frworkspace']";

			}

		} else if (pName.toString().equalsIgnoreCase(PageName.InstitutionsPage.toString())) {
			System.err.println("<<<<<<<<<<InstitutionsPage.toString()>>>>>>>>>>>>>");
			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath = "//div[@id='Investorgrid_div']";

			} else {
				workSpaceXpath = "//div[@id='divFrWorkspace']";
			}
		} else if (pName.toString().equalsIgnoreCase(PageName.CommitmentsPage.toString())) {
			System.err.println("<<<<<<<<<<CommitmentsPage.toString()>>>>>>>>>>>>>");
			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath = "//div[@class='content_div']";

			}
			else {
				workSpaceXpath = "//div[@class='content_div']";
			}
		} else if (pName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {
			System.err.println("<<<<<<<<<<ContactsPage.toString()>>>>>>>>>>>>>");
			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath = "//div[@id='Investorgrid_div']";

			} else {
				workSpaceXpath = "//div[@id='divFrWorkspace']";
			}
		}else if(pName.toString().equalsIgnoreCase(PageName.CurrentInvestmentPgae.toString()) || 
				pName.toString().equalsIgnoreCase(PageName.PotentialInvestmentPage.toString()))  {
			System.err.println("<<<<<<<<<<CurrentInvestmentPgae.toString()>>>>>>>>>>>>>");
			workSpaceXpath = "//span[contains(@id,'grid_Investor')]";
			
		}
		if (workSpaceXpath != null) {

			String docNameXpath="";
			
			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				docNameXpath = workSpaceXpath + "//span[contains(@id,'myGrid-cell-0-')]//a";
				
			}
			else if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
				docNameXpath = workSpaceXpath + "//span[contains(@id,'myGridfundr-cell-0-')]//a";
				
			}else if(workspace.toString().equalsIgnoreCase(Workspace.Other.toString()) || 
					workspace.toString().equalsIgnoreCase(PageName.PotentialInvestmentPage.toString())){
					docNameXpath = workSpaceXpath + "//span[contains(@id,'grid_Investor-cell-0')]/a";	
				}
			
			List<WebElement> docList = FindElements(driver, docNameXpath, "Document Name List");
			System.err.println("xpath for Files : "+docNameXpath);

			if (!docList.isEmpty()) {
				
				for (WebElement ele1 : docList) {
					
					docName.add(ele1.getAttribute("title"));
				}
				appLog.info("Document List :  "+docName);
				if(docName.contains(fileName)){
					appLog.info("Page : "+pName.toString()+" WorkSpace : "+workspace.toString()+" Document Present: "+fileName);
					return true;
					
				}else{
					appLog.error("Page : "+pName.toString()+" WorkSpace : "+workspace.toString()+" Document Not Present: "+fileName);
					
					
				}
				
			}else{
				appLog.info("Document List is Empty " + pName.toString() + " : " + workspace.toString());
				sa.assertTrue(false, "Document List is Empty " + pName.toString() + " : " + workspace.toString());
				
			}
			
			
	}else{
		appLog.info(" Xpath Not Found for Content Grid Verification having " + pName.toString() + " : "
				+ workspace.toString());
		sa.assertTrue(false, " Xpath Not Found for Content Grid Verification having " + pName.toString() + " : "
				+ workspace.toString());
	}
		return false;
	}
	
	/**
	 * @author Ankur Rana
	 * @param fileToViewOrDownload
	 * @param contactVariableName
	 * @param viewDownload
	 * @param workspace
	 * @param alreadyUploadedDocUpdateWithDiffName
	 * @return true/false
	 */
	public boolean viewDownloadDocument(String fileToViewOrDownload, String contactVariableName, viewDownload viewDownload,Workspace workspace, boolean alreadyUploadedDocUpdateWithDiffName){
		boolean flag = false;
		boolean flag1 = false;
		String parentid=null;
		int contactActivityCount=0;
		String fileViewedOrDownloaded=null;
		BaseLib.mostActiveContactActivityCount=0;
		WebElement ele = FindElement(driver, "//a[@title='"+fileToViewOrDownload+"']", fileToViewOrDownload+" file link", action.BOOLEAN, 30);
		if(ele!=null){
			if(click(driver, ele, fileToViewOrDownload+" File in Document Content Grid", action.SCROLLANDBOOLEAN)){
				ThreadSleep(5000);
				appLog.info("clicked on file : "+ele.getText().trim());
				parentid=switchOnWindow(driver);
				if(parentid!=null){
					if(workspace!=null) {
						if(workspace.toString().equalsIgnoreCase(Workspace.PotentialInvestment.toString())) {
							CommonVariables.FRW_DocumentViews++;
							
						}else {
							CommonVariables.INV_DocumentViews++;
						}
						BaseLib.mostActiveContactActivityCount++;
						contactActivityCount++;
						if(workspace.toString().equalsIgnoreCase(Workspace.PotentialInvestment.toString())) {
							fileViewedOrDownloaded = ExcelUtils.readData("IPAnalytics", excelLabel.Statistics,"No of Documents not Viewed or Downloaded",excelLabel.FRW_DocumentsName);
							
						}else {
							fileViewedOrDownloaded = ExcelUtils.readData("IPAnalytics", excelLabel.Statistics,"No of Documents not Viewed or Downloaded",excelLabel.INV_DocumentsName);
						}
						if(!fileViewedOrDownloaded.isEmpty() && ! fileViewedOrDownloaded.equalsIgnoreCase(excelLabel.FRW_DocumentsName.toString()) && !fileViewedOrDownloaded.equalsIgnoreCase(excelLabel.INV_DocumentsName.toString())){
							for(int i = 0 ; i < fileViewedOrDownloaded.split("<break>").length ; i++){
								if(fileViewedOrDownloaded.split("<break>")[i].equalsIgnoreCase(fileToViewOrDownload)){
									appLog.info(fileToViewOrDownload+" file is already been viewed or downloaded.");
									break;
								} else {
									if(i == fileViewedOrDownloaded.split("<break>").length-1){
										appLog.info(fileToViewOrDownload+" file is not view or downloaded at all.");
										flag1 = true;
									}
								}
							}
						} else {
							appLog.info(fileToViewOrDownload+" File is not viewed or downloaded at all.");
							flag1 = true;
						}
						if(alreadyUploadedDocUpdateWithDiffName) {
							appLog.info("this document is already viewed and downloaded brefore update to diffrenet name document");
						}else {
							if(flag1){
								if(workspace.toString().equalsIgnoreCase(Workspace.PotentialInvestment.toString())) {
									CommonVariables.FRW_DocumentNotViewedOrDownload--;
								}else {
									CommonVariables.INV_DocumentNotViewedOrDownload--;
								}
							} else {
								appLog.info(fileToViewOrDownload+" file is already viewed or downloaded.");
							}
						}
						if(!fileViewedOrDownloaded.isEmpty() && !fileViewedOrDownloaded.equalsIgnoreCase(excelLabel.FRW_DocumentsName.toString()) && !fileViewedOrDownloaded.equalsIgnoreCase(excelLabel.INV_DocumentsName.toString())) {
							if(workspace.toString().equalsIgnoreCase(Workspace.PotentialInvestment.toString())) {
								ExcelUtils.writeData(fileViewedOrDownloaded+"<break>"+fileToViewOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.FRW_DocumentsName);
							}else {
								ExcelUtils.writeData(fileViewedOrDownloaded+"<break>"+fileToViewOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.INV_DocumentsName);
							}
						}else {
							if(workspace.toString().equalsIgnoreCase(Workspace.PotentialInvestment.toString())) {
								ExcelUtils.writeData(fileToViewOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.FRW_DocumentsName);
							}else {
								ExcelUtils.writeData(fileToViewOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.INV_DocumentsName);
							}
						}
						String viewedOrNot = ExcelUtils.readData("Contacts", excelLabel.Variable_Name,contactVariableName,excelLabel.Viewed_Or_DownloadedAnyFile);
						if(!viewedOrNot.isEmpty() &&  viewedOrNot.equalsIgnoreCase("No") && !viewedOrNot.equalsIgnoreCase(excelLabel.Viewed_Or_DownloadedAnyFile.toString())){
							if(workspace.toString().equalsIgnoreCase(Workspace.PotentialInvestment.toString())) {
								CommonVariables.FRW_ContactNotViewedAnyDocument--;
							}else {
								CommonVariables.INV_ContactNotViewedAnyDocument--;
							}
							ExcelUtils.writeData("Yes", "Contacts", excelLabel.Variable_Name, contactVariableName, excelLabel.Viewed_Or_DownloadedAnyFile);
						}else {
							appLog.info(contactVariableName+ " Contacts is already viewed or download uploaded files");
						}
					}
					ThreadSleep(10000);
					if(viewDownload!=null) {
						if(viewDownload.toString().equalsIgnoreCase(viewDownload.Download.toString())){
							if (click(driver,getDownloadLink(20), "Document Download Button", action.SCROLLANDBOOLEAN)) {
								appLog.info("document is download successfully");
								if(workspace.toString().equalsIgnoreCase(Workspace.PotentialInvestment.toString())) {
									CommonVariables.FRW_DocumentDownload++;
								}else {
									CommonVariables.INV_DocumentDownload++;
								}
								BaseLib.mostActiveContactActivityCount++;
								contactActivityCount++;
								Robot rob;
								try {
									rob = new Robot();
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_TAB);
									rob.keyRelease(KeyEvent.VK_TAB);
									ThreadSleep(1000);
									rob.keyPress(KeyEvent.VK_TAB);
									rob.keyRelease(KeyEvent.VK_TAB);
									ThreadSleep(1000);
									rob.keyPress(KeyEvent.VK_TAB);
									rob.keyRelease(KeyEvent.VK_TAB);
									ThreadSleep(1000);
									rob.keyPress(KeyEvent.VK_TAB);
									rob.keyRelease(KeyEvent.VK_TAB);
									ThreadSleep(1000);
									rob.keyPress(KeyEvent.VK_ENTER);
									rob.keyRelease(KeyEvent.VK_ENTER);
									ThreadSleep(1000);
								} catch (AWTException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {
								appLog.info("Download is not possible for file: "+fileToViewOrDownload);
								BaseLib.sa.assertTrue(false, "Download is not possible for file: "+fileToViewOrDownload);
							}
						}
					}
					driver.close();
					driver.switchTo().window(parentid);
					flag=true;
				} else {
					appLog.error("No new window is opening after clicking on the file name, So cannot check view and download functionality for file: "+fileToViewOrDownload);
					BaseLib.sa.assertTrue(false,"No new window is opening after clicking on the file name, So cannot check view and download functionality for file: "+fileToViewOrDownload);
				}
			} else {
				appLog.error(fileToViewOrDownload+" file cannot be clicked.");
				BaseLib.sa.assertTrue(false,fileToViewOrDownload+" file cannot be clicked.");
			}
		} else {
			appLog.error(fileToViewOrDownload+" file is not present in the grid.");
			BaseLib.sa.assertTrue(false,fileToViewOrDownload+" file is not present in the grid.");
		}
		ExcelUtils.writeData(String.valueOf(Integer.parseInt(ExcelUtils.readData("Contacts",excelLabel.Variable_Name,contactVariableName,excelLabel.Activity_Count))+contactActivityCount), "Contacts", excelLabel.Variable_Name,contactVariableName, excelLabel.Activity_Count);	
		return flag;
	}
	
	/**
	 * @author Ankur Rana
	 * @param sheetName
	 * @return Map<String, String>
	 */
	public Map<String, String> folderStructureInExcel(String filePath, String sheetName) {
		int i = 2;
		Map<String, String> struct = new LinkedHashMap<String, String>();
		while (true) {
			String value = ExcelUtils.readData(filePath, sheetName, i, 0);
			if (value != null) {
				int totalValues = ExcelUtils.getLastColumn(filePath, sheetName, i);
				for (int j = 1; j < totalValues; j++) {
					String path = ExcelUtils.readData(filePath, sheetName, i, j);
					String[] paths = path.split(",");
					for (int k = 0; k < paths.length; k++) {
						struct.put(paths[k], ExcelUtils.readData(filePath, sheetName, i, 0));
					}
				}
			} else {
				break;
			}
			i++;
		}
		return struct;
	}

	/**
	 * @author Ankur Rana
	 * @param adminEmail
	 * @param userName
	 * @param userEmailID
	 * @param watermarking
	 * @param manageApproval
	 * @param access
	 * @return true/false
	 */
	public boolean preConditionWithoutClickingRegisterrationPopupClose(String adminEmail,String userName, String userEmailID, EnableDisable watermarking, EnableDisable manageApproval, accessType access) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(adminEmail, adminPassword);
		boolean flag1 = false,flag2=false;
		if (clickOnTab(TabName.NIMTab)) {
			if (manageApproval == EnableDisable.Enable) {
				appLog.info("enabling manage approval");
				switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
				if (np.clickOnSideMenusTab(sideMenu.ManageApprovals)) {
					if (np.clickOnEditIcon()) {
						if (!isSelected(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.Edit), "checkbox to tick manage approvals")) {
							if (click(driver, np.getManageApprovalsActivateCheckbox(EditViewMode.Edit),
									"Manage Approval activate checkbox", action.SCROLLANDBOOLEAN)) {
								if (click(driver, np.getManageApprovalSaveButton(60), "Manage Approval Save button",
										action.SCROLLANDBOOLEAN)) {
									if (click(driver, np.getManageApprovalActivateYesButton(60), "Yes Button",
											action.SCROLLANDBOOLEAN)) {
										ThreadSleep(2000);
										if (isSelected(driver,np.getManageApprovalsActivateCheckbox(EditViewMode.View),
												"Manage Approval Activate checkbox")) {
											appLog.info("manage approval is now activated successfully");
											flag1 = true;
										}
										else {
											appLog.error("manage approval checkbox is not activated");
										}
									}
									else {
										appLog.error("manage approval yes button is not clickable");
									}
								}
								else {
									appLog.error("save button on manage approval page is not clickable");
								}
							}
							else {
								appLog.error("manage approval checkbox is not clickable");
							}
						}
						else {
							appLog.info("manage approval checkbox is already selected by default");
						}
					}
					else {
						appLog.error("edit icon is not clickable so cannot activate manage approval");
					}
				}
				switchToDefaultContent(driver);		
			}
			else if (manageApproval == EnableDisable.Disable){
				appLog.info("disabling manage approval");
				switchToFrame(driver, 30, getFrame( PageName.NavatarInvestorManager, 30));
				flag1 = np.deactivateManageApprovalsSetting();
				switchToDefaultContent(driver);

			}
			if (watermarking == EnableDisable.Enable) {
				appLog.info("enabling watermarking");
				switchToFrame(driver, 30, getFrame( PageName.NavatarInvestorManager, 30));
				if (np.clickOnSideMenusTab(sideMenu.Watermarking)) {
					if (!isSelected(driver, np.getWatermarkingActivateCheckbox(60), "Watermarking Activate checkbox")) {
						if ( np.clickOnEditIcon()) {
							if (click(driver,  np.getWatermarkingActivateCheckbox(60), "Watermarking activate checkbox",
									action.SCROLLANDBOOLEAN)) {
								if (click(driver, np.getWatermarkingSaveButton(60), "Watermarking Save button",
										action.SCROLLANDBOOLEAN)) {
									ThreadSleep(2000);
									if (isSelected(driver, np.getWatermarkingActivateCheckbox(60),
											"Watermarking Activate checkbox")) {
										appLog.info("Watermarking is  now activated successfully");
										flag2 = true;
									} else {
										appLog.info("Watermarking could not be activated successfully");
									}
								} else {
									appLog.info("Not able to click on watermarking save button");
								}
							} else {
								appLog.info("Not able to click on watermarking Activate checkbox");
							}
						} else {
							appLog.info("Not able to click on edit icon");
						}
					} else {
						appLog.info("Watermarking is already activated");
					}

				} else {
					appLog.info("Not able to click on watermarking tab");
				}
				switchToDefaultContent(driver);
			}
			else if (watermarking == EnableDisable.Disable){
				appLog.info("deactivating watermarking setting");
				switchToFrame(driver, 30, getFrame( PageName.NavatarInvestorManager, 30));
				flag2 = np.deactivateWatermarkingSetting();
				switchToDefaultContent(driver);
			}
			}
		appLog.info("providing access to user "+userName);
		switchToFrame(driver, 30, getFrame( PageName.NavatarInvestorManager, 30));
		if (np.clickOnSideMenusTab(sideMenu.InternalUsers)) {
			switchToDefaultContent(driver);
			if (np.giveAccessToUserInNIMTabFromAdmin(userName, access)) {
				appLog.info(userName + " was given access "+access+" successfully");
			}
			else {
				appLog.error("could not provide access to "+userName);
			}
		}
		else {
			appLog.error("internal users side tab is not clickable");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		return flag1&&flag2;
	}

	/**
	 * @author Ankur Rana
	 * @param filePath
	 * @param sheetName
	 * @param workspace
	 * @param pageName
	 * @param timeOut
	 * @return true/false
	 */
	public boolean createFolderStructureFromExcelBulk(String filePath, String sheetName, Workspace workspace, PageName pageName, int timeOut){
		Map<String, String> s = folderStructureInExcel(filePath, sheetName);
		Set<String> paths = s.keySet();
		Iterator<String> i = paths.iterator();
		i = paths.iterator();
		FolderType folderType = null;
		boolean flag = true;
		while (i.hasNext()) {
			String string = i.next();
			if (string.isEmpty())
				continue;
			System.out.println("\n\n\nCreating folder template\n\n\n");
			if(s.get(string).equalsIgnoreCase("Shared")){
				folderType=FolderType.Shared;
			} else if (s.get(string).equalsIgnoreCase("Common")){
				folderType=FolderType.Common;
			} else if (s.get(string).equalsIgnoreCase("Internal")){
				folderType=FolderType.Internal;
			} else if (s.get(string).equalsIgnoreCase("Standard")){
				folderType=FolderType.Standard;
			}
			List<String> notCreatedFolders = createFolderStructure(string, folderType, workspace, pageName, timeOut);
			if(notCreatedFolders.isEmpty()){
				appLog.info("Successfully created: "+string);
			} else {
				String folderNames = createStringOutOfList(notCreatedFolders);
				BaseLib.sa.assertTrue(false,"Following folders are not created: "+folderNames);
				flag = false;
			}
		}
		return flag;
	}
	
	/**
	 * @author Ankur Rana
	 * @param contactFirstName
	 * @param contactLastName
	 * @return String
	 */
	public String getContactId(String contactFirstName, String contactLastName){
		WebElement ele = FindElement(driver, "//a[text()='"+contactFirstName+" "+contactLastName+"']", "Contact Link", action.BOOLEAN, 30);
		if(ele!=null){
			return getAttribute(driver, ele, "COntact Link", "data-seclki");
		} else {
			appLog.error(contactFirstName+" "+contactLastName+" Contact Link is not available");
		}
		return null;
	}
	
	/**
	 * @author Ankur Rana
	 * @param fundName
	 * @return String 
	 */
	public String getFundId(String fundName){
		WebElement ele = FindElement(driver, "//a[text()='"+fundName+"']", "Contact Link", action.BOOLEAN, 30);
		if(ele!=null){
			return getAttribute(driver, ele, "COntact Link", "data-seclki");
		} else {
			appLog.error(fundName+" Fund Link is not available");
			sa.assertTrue(false,fundName+" Fund Link is not available to get fund ID.");
		}
		return null;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param ContactIdQuery
	 * @param contactEmailId
	 * @return text or null
	 */
	public String getContactIdFromDevConsole(String contactEmailId,String fundName,String createdByLastName){
		String xpath="";
		if(fundName==null && createdByLastName ==null) {
			xpath="//textarea[contains(text(),'select id from contact where email') and contains(text(),'"+contactEmailId+"')]/../../../../following-sibling::div//div[@class='x-grid-cell-inner ']";
		}else {
			xpath="//textarea[contains(text(),'select id, name from fund__c where fund__c.CreatedBy.LastName') and contains(text(),'"+createdByLastName+"') and contains(text(),'"+fundName+"')]/../../../../following-sibling::div//td[contains(@class,'x-grid-cell-first')]//div[@class='x-grid-cell-inner ']";
			
		}
		WebElement ele = FindElement(driver,xpath, "id", action.BOOLEAN, 20);
		if(ele!=null){
			return getText(driver, ele, "id", action.BOOLEAN);
		} else {
			appLog.error("contact id is not available in dev console");
			sa.assertTrue(false,"contact id is not available in dev console");
		}
		return null;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param sqlQuery
	 * @return SoftAssert
	 */
	public SoftAssert executeQueryOnDevConsole(String sqlQuery) {
		SoftAssert saa = new SoftAssert();
		if(getDevEditorEditor(20)!=null) {
			if(sendKeys(driver, getDevEditorEditor(10), sqlQuery, "sql query editor", action.BOOLEAN)) {
				appLog.info("passed sql query: "+sqlQuery);
				if(getDevEditorQueryExecutorBtn(10)!=null) {
					if(click(driver, getDevEditorQueryExecutorBtn(10), "execute query button", action.BOOLEAN)) {
						appLog.info("clicked on sql query execute button");
					}else {
						appLog.error("Not able to click on sql query execute button so cannot execute sql query: "+sqlQuery);
						saa.assertTrue(false, "Not able to click on sql query execute button so cannot execute sql query: "+sqlQuery);
					}
				}else {
					appLog.error("sql query execute button is not visible so cannot execute sql query: "+sqlQuery);
					saa.assertTrue(false, "sql query execute button is not visible so cannot execute sql query: "+sqlQuery);
				}
			}else {
				appLog.error("Not able to pass sql query in dev console editor : "+sqlQuery);
				saa.assertTrue(false, "Not able to pass sql query in dev console editor : "+sqlQuery);
			}
		}else {
			appLog.error("sql query is not visible in dev console so cannot insert sql query: "+sqlQuery);
			saa.assertTrue(false, "sql query is not visible in dev console so cannot insert sql query: "+sqlQuery);
		}
		return saa;
		
	}
	
	public boolean devConsole() {
		if (click(driver, getUserMenuTab(60), "User menu", action.SCROLLANDBOOLEAN)) {
			if (click(driver, getDevConsoleLink(30), "Log out button", action.BOOLEAN) ) {
				return true;
			}
			else {
				appLog.error("Log out button in user menu tab not found");
			}
		}
		else {
			appLog.error("User menu tab not found");
		}
		return false;
	}
	
	public SoftAssert verifyInvestorUI(String firstName, String lastName, String nickName, String emailID,
			String firmName, String password) {
		SoftAssert sa = new SoftAssert();
		String s = getTargetFirstName(60).getAttribute("value");
		System.out.println(s);
		if (firstName.equalsIgnoreCase(s)) {
			appLog.info("First name is prefilled");
		} else {
			appLog.error("First name is not prefilled");
			sa.assertTrue(false, "first name is not prefilled");
		}
		s = getTargetlastName(60).getAttribute("value");
		System.out.println(s);
		if (lastName.equalsIgnoreCase(s)) {
			appLog.info("Last name is prefilled");
		} else {
			appLog.error("Last name is not prefilled");
			sa.assertTrue(false, "Last name is not prefilled");
		}
		s = getTargetNickName(60).getAttribute("value");
		System.out.println(s);
		if (nickName.equalsIgnoreCase(s)) {
			appLog.info("Nick name is prefilled");
		} else {
			appLog.error("Nick name is not prefilled");
			sa.assertTrue(false, "Nick name is not prefilled");
		}
		s = getTargetEmailId(60).getAttribute("value");
		System.out.println(s);
		if (emailID.equalsIgnoreCase(s)) {
			appLog.info("Emial ID is prefilled");
		} else {
			appLog.error("Email ID is not prefilled");
			sa.assertTrue(false, "Email ID is not prefilled");
		}
		s = getTargetFirmName(60).getAttribute("value");
		System.out.println(s);
		if (firmName.equalsIgnoreCase(s)) {
			appLog.info("Firm name is prefilled");
		} else {
			appLog.error("Firm name is not prefilled");
			sa.assertTrue(false, "Firm name is not prefilled");
		}
		if (sendKeys(driver, getTargetpassword(60), password, "Target Password", action.SCROLLANDBOOLEAN)) {
			if (sendKeys(driver, getTargetConfirmPassword(60), password, "Target Confirm Password",
					action.SCROLLANDBOOLEAN)) {
				if (click(driver, getTargetSignUpBtn(60), "Target Sign Up Button", action.SCROLLANDBOOLEAN)) {
					appLog.info("Clicked on signupbutton successfully");
				} else {
					appLog.error("Not able to click on signup button");
					sa.assertTrue(false, "Not able to click on signup button");
				}
			} else {
				appLog.error("Not able to enter confirm password");
				sa.assertTrue(false, "Not able to enter confirm password");
			}
		} else {
			appLog.error("Not able to enter password");
			sa.assertTrue(false, "Not able to enter password");
		}
		return sa;
	}
	
	
	public SoftAssert verifyResetPasswordMailContent(String ContactFirstName, String ContactEmail, String pageName, String firmName, String crmMailID,String excelPath) {
		SoftAssert sa = new SoftAssert();
		String forgotPasswordMsg;
		String mailID;
		try {
			if(pageName.equalsIgnoreCase("InvestorPage")){
				mailID="noreply@navatarinvestor.com";				
				}else{
				mailID=crmMailID;
				}
			forgotPasswordMsg = new EmailLib().getForgotPasswordMsg("forgotPasswordMail",
					ExcelUtils.readDataFromPropertyFile("gmailUserName"),
					ExcelUtils.readDataFromPropertyFile("gmailPassword"), mailID, ContactEmail);
			if (forgotPasswordMsg != null) {
				if ((forgotPasswordMsg).contains("Hello " + ContactFirstName + ",")) {
					appLog.info("Mail Contains  : " + "Hello " + ContactFirstName + ",");
				} else {
					appLog.error("Mail does not Contain  : " + "Hello " + ContactFirstName + ",");
					sa.assertTrue(false, "Mail does not Contain  : " + "Hello " + ContactFirstName + ",");
				}
				if ((forgotPasswordMsg).contains("Click here")) {
					appLog.info("Click here is verified");
				} else {
					appLog.error("Mail does not Contain  : " + "Click here");
					sa.assertTrue(false, "Mail does not Contain  : " + "Click here");
				}
				if ((forgotPasswordMsg).contains(" to reset your password.")) {
					appLog.info(" to reset your password.mesage is verified");
				} else {
					appLog.error("Mail does not Contain  : " + " to reset your password.");
					sa.assertTrue(false, "Mail does not Contain  : " + " to reset your password.");
				}

				if ((forgotPasswordMsg).contains(
						"If you did not request a password change, you may ignore this message and your password will remain unchanged.")) {
					appLog.info(
							"Mail contains the message-If you did not request a password change, you may ignore this message and your password will remain unchanged.");
				} else {
					appLog.error("Mail does not Contain  : "
							+ "If you did not request a password change, you may ignore this message and your password will remain unchanged.");
					sa.assertTrue(false, "Mail does not Contain  : "
							+ "If you did not request a password change, you may ignore this message and your password will remain unchanged.");
				}
				String s;
				if(pageName.equalsIgnoreCase("InvestorPage")){
				s="Please do not respond to this email.";
				}else{
					s="Thank You,";	
					
					if ((forgotPasswordMsg).contains(firmName)) {
						appLog.info("Mail contains the message-: "+firmName);;
					} else {
						appLog.error("Mail does not Contain  : " + firmName);
						sa.assertTrue(false, "Mail does not Contain  : " + firmName);
					}					
				}
				if ((forgotPasswordMsg).contains(s)) {
					appLog.info("Mail contains the message-:"+s);;
				} else {
					appLog.error("Mail does not Contain  : " + s);
					sa.assertTrue(false, "Mail does not Contain  : " + s);
				}
			} else {
				appLog.error("forgot Password Msg element is not present");
				sa.assertTrue(false, "forgot Password Msg element is not present");
			}
			System.err.println(forgotPasswordMsg);
			String[] ss = forgotPasswordMsg.split("href=\"");
			String[] ss1 = ss[1].split("\"");

			System.err.println(" URL>>>>>" + ss1[0]);
			if(ss1[0].contains("http://") || ss1[0].contains("https://")){
				if(excelPath!=null){
					if(ExcelUtils.writeData(excelPath,ss1[0], "Contacts", excelLabel.Contact_EmailId, ContactEmail,
							excelLabel.Click_HereLink)) {
						
					}else {
						appLog.error("Contacts Sheet Name is not available in excel sheet so try to write in contact sheet");
						ExcelUtils.writeData(excelPath,ss1[0], "Contact", excelLabel.Contact_EmailId, ContactEmail,
								excelLabel.Click_HereLink);
					}
				}else{
					ExcelUtils.writeData(ss1[0], "Contacts", excelLabel.Contact_EmailId, ContactEmail,
							excelLabel.Click_HereLink);	
				}
			}else{
				if(excelPath!=null){
					if(ExcelUtils.writeData(excelPath,"http://"+ss1[0], "Contacts", excelLabel.Contact_EmailId, ContactEmail,
							excelLabel.Click_HereLink)) {
						
					}else {
						appLog.error("Contacts Sheet Name is not available in excel sheet so try to write in contact sheet");
						ExcelUtils.writeData(excelPath,"http://"+ss1[0], "Contact", excelLabel.Contact_EmailId, ContactEmail,
								excelLabel.Click_HereLink);
					}
				}else{
					ExcelUtils.writeData("http://"+ss1[0], "Contacts", excelLabel.Contact_EmailId, ContactEmail,
							excelLabel.Click_HereLink);
				}
			}
			System.err.println(">>>>>>>>>" + ss1[1]);

		} catch (InterruptedException e) {
			appLog.error("Forgot Password Mail not found.");
			sa.assertTrue(false, "Forgot Password Mail not found.");
			e.printStackTrace();
		}
		return sa;
	}

	public boolean clickUsingCssSelectorPath(String cssSelectorPath,String buttonName) {
		boolean cssFlag=false;
		appLog.info("Css Selector Path for "+buttonName+" is  >>>>   "+cssSelectorPath);
		try {
			cssFlag=false;
			WebElement ele = BaseLib.edriver.findElement(By.cssSelector(cssSelectorPath));
			ele.click();
			appLog.info("click on "+buttonName);
			cssFlag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			appLog.info("Not able to click on "+buttonName);
			BaseLib.sa.assertTrue(false, "Not able to click on "+buttonName);
			cssFlag=false;

		}
		return cssFlag;
	}
	
	public boolean elementClick(WebElement ele,String buttonName) {
		boolean clickFlag=false;;
		try {
			clickFlag=false;
			ele.click();
			appLog.info("Using elementClick click on "+buttonName);
			clickFlag = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				if (!clickFlag) {
					clickFlag=clickUsingJavaScript(driver, ele, buttonName, action.SCROLLANDBOOLEAN);
					appLog.info("Using javaScript click on "+buttonName);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				if (click(driver, ele, buttonName, action.SCROLLANDBOOLEAN)) {
					return true;
				}
				appLog.info("Not able to click on "+buttonName);
				BaseLib.sa.assertTrue(false, "Not able to click on "+buttonName);
				clickFlag=false;
			}
		

		}
		return clickFlag;
	}
	
	// Lightning Method....................
	/**
	 * @author Ankit Jaiswal
	 * @param environment
	 * @param mode
	 * @return true/false
	 */
	public boolean clickOnSetUpLink(String environment,String mode) {
		boolean flag = false;
		if(mode.equalsIgnoreCase(Mode.Classic.toString())) {
			if(click(driver, getUserMenuTab(20), "user menu tab", action.SCROLLANDBOOLEAN)) {
				appLog.info("clicked on user menu tab");
				
			}else {
				appLog.error("user menu tab");
				return flag;
			}
		}else {
			if(click(driver, getSettingLink_Lighting(20), "setting icon", action.SCROLLANDBOOLEAN)) {
				appLog.info("clicked on setting icon");
				ThreadSleep(3000);
			}else {
				appLog.error("Not able to click on setting icon");
				return flag;
			}
		}
		if(click(driver, getUserMenuSetupLink(environment, mode, 20), "setup link", action.SCROLLANDBOOLEAN)) {
			appLog.info("setup link");
			flag=true;
		}else {
			appLog.error("user setup link");
		}
		return flag;
	}
	
	public boolean addTab_Lighting(String mode,String tabToBeAdded,int timeOut){
		String xpath;
		WebElement ele;
		boolean flag = true;
		int count =0;
		if (click(driver, getPersonalizePencilIcon(mode, timeOut), "Personalize Pencil Icon", action.SCROLLANDBOOLEAN)) {
			ThreadSleep(2000);
			if (click(driver, getAddMoreItemsLink(mode, timeOut), "Add More items Link", action.SCROLLANDBOOLEAN)) {
				ThreadSleep(2000);
				if (click(driver, getAllAddLink(mode, timeOut), "All Link", action.SCROLLANDBOOLEAN)) {
					ThreadSleep(2000);
					click(driver, getAllAddLink(mode, timeOut), "All Link", action.SCROLLANDBOOLEAN);
					ThreadSleep(2000);
					String[] tabs = tabToBeAdded.split(",");
					for (int i = 0; i < tabs.length; i++) {
						xpath ="//h3[contains(text(),'"+tabs[i]+"')]/..//preceding-sibling::label/div";
						ele = FindElement(driver, xpath, "Tab to be add : "+tabs[i], action.SCROLLANDBOOLEAN, 1);
						if (ele!=null) {
							scrollDownThroughWebelement(driver, ele, "TABS : "+tabs[i]);	
							if (click(driver, ele, "Tab to be add : "+tabs[i], action.SCROLLANDBOOLEAN)) {
								appLog.error("Tab Added : "+tabs[i]);
//								log(LogStatus.INFO, "Tab Added : "+tabs[i], YesNo.No);
							} else {
								flag = false;
								appLog.error("Not Able to add Tab : "+tabs[i]);
//								log(LogStatus.INFO, "Not Able to add Tab : "+tabs[i], YesNo.Yes);
							}
							
						} else {
							appLog.error("Tab Already Added : "+tabs[i]);
//							log(LogStatus.INFO, "Tab Already Added : "+tabs[i], YesNo.No);
							count++;
						}
					}
					if(count!=tabs.length) {
						if (click(driver, getAddNavButton(mode, timeOut), "Add Nav Button", action.SCROLLANDBOOLEAN)) {
							if (click(driver, getTabSaveButton(mode, timeOut), "Save Button", action.SCROLLANDBOOLEAN)) {
							} else {
								appLog.error("Not Able to click on Save Button");
//							log(LogStatus.FAIL, "Not Able to click on Save Button", YesNo.Yes);
								flag = false;
							}
						} else {
							appLog.error("Not Able to click on Add Nav Button");
//						log(LogStatus.FAIL, "Not Able to click on Add Nav Button", YesNo.Yes);
							flag = false;
						}
					}else {
						click(driver, getAddTabCloseTheWindowCrossIcon(), "cross icon", action.BOOLEAN);
//						click(driver, getAddTabCloseTheWindowCrossIcon(), "cross icon", action.BOOLEAN);
					}
				} else {
					appLog.error("Not Able to click on All Link");
//					log(LogStatus.FAIL, "Not Able to click on All Link", YesNo.Yes);
					flag = false;
				}
			} else {
				appLog.error("Not Able to click on Add More items Link");
//				log(LogStatus.FAIL, "Not Able to click on Add More items Link", YesNo.Yes);
				flag = false;
			}
		} else {
			appLog.error("Not Able to click on personalize Pencil Icon");
//			log(LogStatus.FAIL, "Not Able to click on personalize Pencil Icon", YesNo.Yes);
			flag = false;
		}
		return flag;
	}
	
	public WebElement verifyCreatedItemOnPage(Header header,String itemName)
	{
		WebElement ele;
		String xpath ="";
		String head =header.toString().replace("_", " ");
		ThreadSleep(3000);
		xpath="//*[contains(text(),'"+head+"')]/..//*[text()='"+itemName+"']";
		 ele = FindElement(driver, xpath, "Header : "+itemName, action.BOOLEAN, 30);
		 ele = isDisplayed(driver, ele, "Visibility", 10, head+" : "+itemName);
		return ele;
	}
	
	public static String convertNumberIntoMillions(String number){
		double d = Double.parseDouble(number);
		double aa = d/1000000;
		String output = new DecimalFormatSymbols(Locale.US).getCurrencySymbol()+aa;
		System.err.println("convertNumberIntoMillions  outpurttt >>>> "+output);
		return output;
	}
	
	
	/**
	 * @author Ankit Jaiswal
	 * @param environment
	 * @param RecordType
	 * @return true/false
	 */
	public boolean ClickonRelatedTab_Lighting(String environment,RecordType recordType) {
		String xpath1 = "//*[text()='Related']";
		String xpath2 = "//*[text()='Related']";
		String xpath="";
		
		if (recordType == RecordType.PipeLine) 
			return true;
		if ((recordType == RecordType.Partnerships) || (recordType == RecordType.Fund)|| (recordType == RecordType.Fundraising)||(recordType == RecordType.Company) || (recordType == RecordType.IndividualInvestor) ||(recordType == RecordType.Institution)|| (recordType == RecordType.Contact))
		xpath = xpath1;
		else
			xpath = xpath2;
		for(int i=0;i<2; i++){
			refresh(driver);
			ThreadSleep(3000);
			
			List<WebElement> eleList = FindElements(driver, xpath, "Related Tab");
			for (WebElement ele : eleList) {
				if(click(driver, ele, recordType+" related tab", action.BOOLEAN)) {
					log(LogStatus.INFO, "clicked on "+recordType+" related tab", YesNo.No);
					return true;
				}
			}
		}		
		log(LogStatus.ERROR,"Not able to click on related tab "+recordType ,YesNo.Yes);
		return false;
	}
	
	
	public boolean clickOnViewAllRelatedList(String environment,String mode, RelatedList RelatedList) {
		if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
			if (clickOnRelatedList_Classic(environment, RelatedList)) {
				return true;
			}
		} else {
			String relatedList = null;
			WebElement ele;
			switch (RelatedList) {
			case Fundraising_Contacts:
				relatedList = "Fundraising Contacts";
				break;
			case Office_Locations:
				relatedList = "Office Locations";
				break;
			case Affiliations:
				relatedList = "Affiliations";
				break;
			case Activities:
				relatedList = "Activities";
				break;
			case Activity_History:
				relatedList = "Activity History";
				break;	
			case Deals_Sourced:
				relatedList = "Deals Sourced";
				break;
			case Partnerships:
				relatedList = "Partnerships";
				break;
			case FundDrawdown:
				relatedList = "Fund Drawdown";
				break;
			case FundDistribution:
				relatedList = "Fund Distribution";
				break;
			case CapitalCalls:
				relatedList = "Capital Calls";
				break;
			case InvestorDistributions:
				relatedList = "Investor Distributions";
				break;	
			case Pipeline_Stage_Logs:
				relatedList = "Pipeline Stage Logs";
				break;
			case Correspondence_Lists:
				relatedList = "Correspondence Lists";
				break;
			case Commitments:
				relatedList = "Commitments";
				break;
			default:
				return false;
			}
			ThreadSleep(2000);
			System.err.println("Passed switch statement");
		
				
				ele = isDisplayed(driver, FindElement(driver, "//span[text()='"+relatedList+"']/ancestor::article//span[text()='View All']", relatedList,
						action.SCROLLANDBOOLEAN, 10), "visibility", 10, relatedList);
				if (ele != null) {
					if (click(driver, ele, relatedList, action.SCROLLANDBOOLEAN)) {
						CommonLib.log(LogStatus.INFO, "Related List found : "+relatedList, YesNo.No);
						ThreadSleep(2000);
						return true;
					}
				}
			
		}
		

		return false;
	}

	public boolean clickOnRelatedList_Classic(String environment, RelatedList RelatedList) {
		String relatedList = null;
		WebElement ele;
		switch (RelatedList) {
		case Fundraising_Contacts:
			relatedList = "Fundraising Contacts";
			break;
		case Office_Locations:
			relatedList = "Office Locations";
			break;
		case Open_Activities:
			relatedList = "Open Activities";
			break;
		case Fundraisings:
			relatedList = "Fundraisings";
			break;
		case FundDrawdown:
			relatedList = "Fund Drawdown";
			break;
		case CapitalCalls:
			relatedList = "Capital Calls";
			break;
		case Affiliations:
			relatedList = "Affiliations";
			break;
		case Activities:
			relatedList = "Activities";
			break;
		case Activity_History:
			relatedList = "Activity History";
			break;
		case Commitments:
			relatedList = "Commitments";
			break;
		case Partnerships:
			relatedList = "Partnerships";
			break;
		case Deals_Sourced:
			relatedList = "Deals Sourced";
			break;
		case Pipeline_Stage_Logs:
			relatedList = "Pipeline Stage Logs";
			break;
			
		default:
			return false;
		}
		ThreadSleep(2000);
		System.err.println("Passed switch statement");
		
			ele = isDisplayed(driver, FindElement(driver, "//span[@class='listTitle'][text()='"+relatedList+"']", relatedList,
					action.SCROLLANDBOOLEAN, 10), "visibility", 10, relatedList);
			if (ele != null) {
				if (click(driver, ele, relatedList, action.SCROLLANDBOOLEAN)) {
					CommonLib.log(LogStatus.INFO, "Related List found : "+relatedList, YesNo.No);
					ThreadSleep(2000);
					return true;
				}
			}
		

		return false;
	}
	
	public boolean clickOnGridSection_Lightning(String environment,String mode,RelatedList gridSectionName ,int timeOut) {
		WebElement ele = null;
		boolean flag=false;
		String xpath1="//span[@title='"+gridSectionName+"']";
		ele = isDisplayed(driver, FindElement(driver,xpath1, gridSectionName.toString()+ " link", action.SCROLLANDBOOLEAN,timeOut),"visibility", timeOut, gridSectionName.toString()+ " link");
		if(click(driver, ele, gridSectionName.toString()+ " link", action.SCROLLANDBOOLEAN)) {
			log(LogStatus.INFO, "clicked on "+gridSectionName.toString()+" link", YesNo.No);
			flag=true;
		}else {
			log(LogStatus.ERROR, "Not able to click on "+gridSectionName.toString()+" link so cannot verify error message", YesNo.Yes);
		}
		return flag;
	}
	
	
	public boolean verifyDate(String date, String dateFormat, String typeOfDate){
		if(dateFormat==null) {
			if(date.contains(getDateAccToTimeZone("America/New_York", "M/dd/yyyy"))){
				appLog.info(typeOfDate+" date is verified : "+getDateAccToTimeZone("America/New_York", "M/dd/yyyy"));
				return true;
			} else if (date.contains(getDateAccToTimeZone("America/New_York", "MM/dd/yyyy"))) {
				appLog.info(typeOfDate+" date is verified : "+getDateAccToTimeZone("America/New_York", "MM/dd/yyyy"));
				return true;
			} else if (date.contains(getDateAccToTimeZone("America/New_York", "dd/M/yyyy"))) {
				appLog.info(typeOfDate+" date is verified : "+getDateAccToTimeZone("America/New_York", "dd/M/yyyy"));
				return true;
			} else if (date.contains(getDateAccToTimeZone("America/New_York", "dd/MM/yyyy"))) {
				appLog.info(typeOfDate+" date is verified : "+getDateAccToTimeZone("America/New_York", "dd/MM/yyyy"));
				return true;
			}else if (date.contains(getDateAccToTimeZone("America/New_York",  "M/d/yyyy"))) {
				appLog.info(typeOfDate+" date is verified : "+getDateAccToTimeZone("America/New_York", "M/d/yyyy"));
				return true;
			}else if (date.contains(getDateAccToTimeZone("America/New_York",  "d/M/yyyy"))) {
				appLog.info(typeOfDate+" date is verified : "+getDateAccToTimeZone("America/New_York", "d/M/yyyy"));
				return true;
			}else {
				appLog.info(typeOfDate+" date is not verified. found result : "+date);
				appLog.info("Expected Date is : "+getDateAccToTimeZone("America/New_York","M/dd/yyyy")+ " or "+getDateAccToTimeZone("America/New_York", "MM/dd/yyyy")+" or "+getDateAccToTimeZone("America/New_York", "dd/M/yyyy")+" or "+getDateAccToTimeZone("America/New_York", "dd/MM/yyyy")+" or "+getDateAccToTimeZone("America/New_York", "M/d/yyyy"));
				return false;
			}
		}else {
			if(date.contains(getDateAccToTimeZone("America/New_York", dateFormat))){
				appLog.info(typeOfDate+" date is verified : "+getDateAccToTimeZone("America/New_York", dateFormat));
				return true;
			}else {
				appLog.info(typeOfDate+" date is not verified. found result : "+date);
				appLog.info("Expected Date is : "+getDateAccToTimeZone("America/New_York", dateFormat)+ " or "+getDateAccToTimeZone("America/New_York", dateFormat)+" or "+getDateAccToTimeZone("America/New_York", dateFormat)+" or "+date.contains(getDateAccToTimeZone("America/New_York", dateFormat)));
				return false;
			}
			
		}
		
		

	}

	
	public boolean clickOnAlreadyCreated_Lighting(String environment, String mode, TabName tabName,
			String alreadyCreated, int timeout) {

		String viewList = null;
		switch (tabName) {
		case ContactTab:
			viewList = "All Contacts";
			break;
		case InstituitonsTab:
			viewList = "All Institutions";
			break;
		case CompaniesTab:
			viewList = "All Companies";
			break;
		case LimitedPartner:
			viewList = "All Limited Partners";
			break;
		case FundraisingsTab:
			viewList = "All";
			break;
		case FundsTab:
			viewList = "All";
			break;
		case CommitmentsTab:
			viewList = "All";
			break;
		case PartnershipsTab:
			viewList = "All";
			break;
		case FundDistributions:
			viewList = "All";
			break;
		case InvestorDistributions:
			viewList = "All";
			break;
		case MarketingInitiatives:
			viewList = "All";
			break;
		case MarketingProspects:
			viewList = "Marketing Prospects";
			break;
		case Pipelines:
			viewList = "All";
			break;
		case CapitalCalls:
			viewList = "All";
			break;
		case FundDrawdowns:
			viewList = "All";
			break;
		case FundraisingContacts:
			viewList = "All";
			break;
		default:
			return false;
		}
		System.err.println("Passed switch statement");
		WebElement ele, selectListView;
		ele = null;
		if (click(driver, getSelectListIcon(60), "Select List Icon", action.SCROLLANDBOOLEAN)) {
			ThreadSleep(3000);
			selectListView = FindElement(driver, "//div[@class='listContent']//li/a/span[text()='" + viewList + "']",
					"Select List View", action.SCROLLANDBOOLEAN, 30);
			if (click(driver, selectListView, "select List View", action.SCROLLANDBOOLEAN)) {
				ThreadSleep(3000);
				if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					refresh(driver);
					ThreadSleep(5000);
				}
				if (sendKeys(driver, getSearchIcon_Lighting(20), alreadyCreated+"\n", "Search Icon Text",
						action.SCROLLANDBOOLEAN)) {
					ThreadSleep(5000);
					ele = FindElement(driver,
							"//table[@data-aura-class='uiVirtualDataTable']//tbody//tr//th//span//a[text()='"
									+ alreadyCreated + "']",
							alreadyCreated, action.BOOLEAN, 30);
					ThreadSleep(2000);
					if (click(driver, ele, alreadyCreated, action.BOOLEAN)) {
						ThreadSleep(3000);
						return true;
					} else {
						appLog.error("Not able to Click on Already Created : " + alreadyCreated);
					}
				} else {
					appLog.error("Not able to enter value on Search Box");
				}
			} else {
				appLog.error("Not able to select on Select View List");
			}
		} else {
			appLog.error("Not able to click on Select List Icon");
		}
		return false;
	}
	
	public boolean clickOnAlreadyCreatedItem( TabName tabName,
			String alreadyCreated, int timeout) {
		boolean flag=false;
		String xpath="";
		String viewList = null;
		switch (tabName) {
		case InstituitonsTab:
			viewList = "All Institutions";
			
			break;

		case CompaniesTab:
			viewList = "All Companies";
			break;
		case FundsTab:
			viewList = "All";
			break;
		
		case NavatarSetup:
			viewList = "All";
			break;
		case RecycleBinTab:
			viewList = "Org Recycle Bin";
			break;
		default:
			return false;
		}
		System.err.println("Passed switch statement");
		WebElement ele, selectListView;
		ele = null;

		refresh(driver);
		if (click(driver, getSelectListIcon(60), "Select List Icon", action.SCROLLANDBOOLEAN)) {
			ThreadSleep(3000);
			xpath="//div[@class='listContent']//li/a/span[text()='" + viewList + "']";
			selectListView = FindElement(driver, xpath,"Select List View : "+viewList, action.SCROLLANDBOOLEAN, 30);
			if (click(driver, selectListView, "select List View : "+viewList, action.SCROLLANDBOOLEAN)) {
				
			ThreadSleep(3000);
				ThreadSleep(5000);

				if (sendKeys(driver, getSearchIcon_Lighting(20), alreadyCreated+"\n", "Search Icon Text",action.SCROLLANDBOOLEAN)) {
					
		ThreadSleep(5000);

					xpath = "//table[@data-aura-class='uiVirtualDataTable']//tbody//tr//th//span//*[text()='"+ alreadyCreated + "']";
					ele = FindElement(driver,xpath,alreadyCreated, action.BOOLEAN, 30);
					ThreadSleep(2000);

					if (click(driver, ele, alreadyCreated, action.BOOLEAN)) {
						ThreadSleep(3000);
						flag=true;
					} else {
						appLog.error("Not able to Click on Already Created : " + alreadyCreated);
					}
				} else {
					appLog.error("Not able to enter value on Search Box");
				}
			} else {
				appLog.error("Not able to select on Select View List : "+viewList);
			}
		} else {
			appLog.error("Not able to click on Select List Icon");
		}
		return flag;
	}
	
	public boolean verifyActivitiesRelatedList(String environment,String mode,TabName tabName,String subject,String contactName,String relatedTo, String date, String user){
		WebElement ele;
		String xpath;
		if (tabName.toString().equalsIgnoreCase(TabName.InstituitonsTab.toString())) {
			if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
				if (relatedTo==null) {
					xpath = "(//h3[text()='Activities']/ancestor::div[@class='bRelatedList']//div[@class='pbBody']//th//a[text()='"+subject+"']/../following-sibling::td/a[text()='"+contactName+"']/../following-sibling::td)[1]";
					ele = FindElement(driver, xpath, "", action.SCROLLANDBOOLEAN, 10);
					System.err.println(">>>>>ele:");
					if (ele!=null) {
						String msg= ele.getText().trim();
						System.err.println(">>>>>msg: "+msg);
						if (msg.isEmpty()) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else {
					xpath = "//h3[text()='Activities']/ancestor::div[@class='bRelatedList']//div[@class='pbBody']//th//a[text()='"+subject+"']/../following-sibling::td/a[text()='"+contactName+"']/../following-sibling::td/a[text()='"+relatedTo+"']";
				}
				ele = FindElement(driver, xpath, "", action.SCROLLANDBOOLEAN, 10);
			} else {
				if (relatedTo==null) {
					xpath = "//table[@data-aura-class='uiVirtualDataTable']/tbody/tr/th/span/a[contains(text(),'"+subject+"')]/../../following-sibling::td/span/a[text()='"+contactName+"']/../../following-sibling::td";
					ele = FindElement(driver, xpath, "", action.SCROLLANDBOOLEAN, 10);
					System.err.println(">>>>>ele:");
					if (ele!=null) {
						String msg= ele.getText().trim();
						System.err.println(">>>>>msg: "+msg);
						if (msg.isEmpty()) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else {
					xpath = "//table[@data-aura-class='uiVirtualDataTable']/tbody/tr/th/span/a[contains(text(),'"+subject+"')]/../../following-sibling::td/span/a[text()='"+contactName+"']/../../following-sibling::td/span/a[text()='"+relatedTo+"']/../../following-sibling::td/span//*[text()='"+date+"']/../../following-sibling::td/span/a[text()='"+user+"']";
				}
				ele = FindElement(driver, xpath, "", action.SCROLLANDBOOLEAN, 10);
			}	
		}
		else if(tabName.toString().equalsIgnoreCase(TabName.ContactTab.toString())) {
			xpath="//table[@data-aura-class='uiVirtualDataTable']/tbody/tr/th/span/a[contains(text(),'"+subject+"')]/../../following-sibling::td/span/a[text()='"+relatedTo+"']/../../following-sibling::td/span//*[text()='"+date+"']/../../following-sibling::td/span/a[text()='"+user+"']";
			//table[@data-aura-class='uiVirtualDataTable']/tbody/tr/th/span/a[contains(text(),'"+subject+"')]/../../following-sibling::td/span/a[text()='"+instOrContact+"']/../../following-sibling::td/span//*[text()='"+date+"']/../../following-sibling::td/span/a[text()='"+user+"']
			ele = FindElement(driver, xpath, "", action.SCROLLANDBOOLEAN, 10);
		}
		else{
			return false;
		}
		
		if (ele!=null) {
			return true;
		} else {
			return false;
		}
		
		
	}

	public boolean clickOncreatedRecordOnActivityRelatedList(String environment, String mode,String activity) {
		WebElement ele=null;
		boolean flag = false;
		String xpath="";
		if(mode.toString().equalsIgnoreCase(Mode.Lightning.toString())) {
			xpath="//h1[text()='Activity History']/ancestor::div/div[contains(@class,'slds-grid listDisplays')]//tbody/tr//th//a[contains(text(),'"+activity+"')]";
		}else {
			xpath="//h3[text()='Activity History']/../../../../../following-sibling::div//tr/th/a[text()='"+activity+"']";
		}
		ele = isDisplayed(driver, FindElement(driver, xpath, activity + " label text in "+mode, action.SCROLLANDBOOLEAN, 30), "visibility", 30, activity + " label text in "+mode);
		if(ele!=null) {
			if(click(driver, ele, activity+" label text", action.SCROLLANDBOOLEAN)) {
				log(LogStatus.INFO, "clicked on "+activity+" label text", YesNo.No);
				flag=true;
			}else {
				log(LogStatus.ERROR, "Not able to click on "+activity+" label text", YesNo.Yes);
			}
		}else {
			log(LogStatus.ERROR, activity+" label text is not visible so cannot click on it ", YesNo.Yes);
		}
		return flag;
	}

	public WebElement getLabelForTaskInViewMode(PageName pageName,String label,action action,int timeOut) {

		String xpath="";
		String fieldLabel=label.replace("_", " ");;
		switchToDefaultContent(driver);
		/*if (TaskPageLabel.Related_Associations.toString().equals(label) || PageLabel.Related_Contacts.toString().equals(label)) {
			switchToDefaultContent(driver);
			switchToFrame(driver, 20, getTaskPageFrame(projectName,timeOut));
			xpath="//label[text()='"+fieldLabel+"']//following-sibling::div";

		}*/ if(TaskPageLabel.Comments.toString().equals(label)) {
		xpath="//span[text()='"+fieldLabel+"']/../following-sibling::div//span/span"	;
		}else if(TaskPageLabel.Related_To.toString().equals(label)||(TaskPageLabel.Assigned_To.toString().equals(label))||(TaskPageLabel.Name.toString().equals(label))) {
			xpath="//span[text()='"+fieldLabel+"']/../following-sibling::div//span//a"	;
		}else if(TaskPageLabel.Due_Date.toString().equals(label)) {
			xpath="//span[text()='"+fieldLabel+"']/../following-sibling::div//span/span"	;
		}else {
			xpath ="//span[text()='"+fieldLabel+"']/../following-sibling::div";
		} //span/span
		WebElement ele = FindElement(driver, xpath,fieldLabel , action, timeOut);
		scrollDownThroughWebelement(driver, ele, fieldLabel);
		ele = isDisplayed(driver, ele, "Visibility", timeOut, fieldLabel);
		return ele;
		
	}
	
	public boolean scrollToRelatedListViewAll_Lightning(String environment, String mode, RelatedList rl, boolean viewAllOrNew) {
		if (mode.toString().equalsIgnoreCase(Mode.Lightning.toString())) {
			String xpath = "";
			if (viewAllOrNew)
				xpath = "/ancestor::article//span[text()='View All']";
			else
				xpath = "/ancestor::header/following-sibling::div//a[@title='New']";
			((JavascriptExecutor) driver)
			.executeScript("window.scrollTo(0,0);");
			
			int widgetTotalScrollingWidth = Integer.parseInt(String.valueOf(((JavascriptExecutor) driver)
					.executeScript("return window.outerHeight")));
			appLog.info("total height is: "+widgetTotalScrollingWidth);
			int j = 50;
			int i = 0;
			WebElement el=null;
			while (el==null) {
				el=isDisplayed(driver,FindElement(driver, "//span[text()='"+rl.toString().replace("_", " ")+"']"+xpath, rl.toString(), action.BOOLEAN, 5) , "visibility", 5, rl.toString());
				((JavascriptExecutor) driver).executeScript("window.scrollBy( 0 ,"+j+")");
				i+=j;
				if (i >= widgetTotalScrollingWidth) {
					return false;
				}
				else if (el!=null)
					return true;
			}
			return false;
		}
		else
			return true;
	}
	/**
	 * @param projectName
	 * @param pageName
	 * @return  true if able to click o Show more action Icon
	 */
	public boolean clickOnShowMoreDropdownOnly(PageName pageName) {
		String xpath = "";int i =1;
		WebElement ele=null;
		boolean flag = true;

		xpath="(//a[contains(@title,'more actions')])["+i+"]";
//		if (PageName.TestCustomObjectPage.equals(pageName) || PageName.Object3Page.equals(pageName)) {
//			xpath="(//span[contains(text(),'more actions')])[1]/..";
//		}
//		else if(PageName.TaskPage.equals(pageName)) {
//			xpath="//a[@title='Show one more action']";
//		}
		ele=FindElement(driver, xpath, "show more action down arrow", action.SCROLLANDBOOLEAN, 30);
		if(click(driver, ele, "show more action on "+pageName.toString(), action.SCROLLANDBOOLEAN)) {
			log(LogStatus.INFO, "clicked on show more actions icon", YesNo.No);

		}
		else {
			log(LogStatus.FAIL, "cannot click on show more actions icon", YesNo.Yes);
			flag = false;
		}
		return flag;
	}
	
}
