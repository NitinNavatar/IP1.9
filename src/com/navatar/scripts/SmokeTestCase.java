/**
 * 
 */
package com.navatar.scripts;

import static com.navatar.generic.AppListeners.appLog;
import static com.navatar.generic.CommonLib.*;
import static com.navatar.generic.SmokeCommonVariable.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.navatar.generic.BaseLib;
import com.navatar.generic.EmailLib;
import com.navatar.generic.ExcelUtils;
import com.navatar.generic.SmokeCommonVariable;
import com.navatar.generic.SoftAssert;
import com.navatar.generic.CommonLib.ContentGridArrowKeyFunctions;
import com.navatar.generic.CommonLib.EnableDisable;
import com.navatar.generic.CommonLib.FolderType;
import com.navatar.generic.CommonLib.ManageApprovalTabs;
import com.navatar.generic.CommonLib.Mode;
import com.navatar.generic.CommonLib.OnlineImportFileAddTo;
import com.navatar.generic.CommonLib.PageName;
import com.navatar.generic.CommonLib.TabName;
import com.navatar.generic.CommonLib.UploadFileActions;
import com.navatar.generic.CommonLib.WorkSpaceAction;
import com.navatar.generic.CommonLib.Workspace;
import com.navatar.generic.CommonLib.YesNo;
import com.navatar.generic.CommonLib.accessType;
import com.navatar.generic.CommonLib.action;
import com.navatar.generic.CommonLib.customTabActionType;
import com.navatar.generic.CommonLib.excelLabel;
import com.navatar.generic.CommonLib.investorSideWorkSpace;
import com.navatar.generic.CommonLib.sideMenu;
import com.navatar.generic.CommonLib.userType;
import com.navatar.pageObjects.AllFirmsPageBusinesslayer;
import com.navatar.pageObjects.BasePageBusinessLayer;
import com.navatar.pageObjects.CommitmentPageBusinessLayer;
import com.navatar.pageObjects.ContactPageBusinessLayer;
import com.navatar.pageObjects.ContactPageErrorMessage;
import com.navatar.pageObjects.DisclaimerPageBussinessLayer;
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
import com.navatar.pageObjects.NIMPageErrorMessage;
import com.navatar.pageObjects.PartnershipPageBusinessLayer;
import com.relevantcodes.extentreports.LogStatus;
/**
 * @author Ankit Jaiswal
 *
 */
public class SmokeTestCase extends BaseLib {
	String passwordResetLink = null;
	
	@Parameters({ "environment", "mode" })
	@Test 
	public void Smoketc001_1_CreateCRMUser3(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String parentWindow = null;
		lp.CRMLogin(SmokeSuperAdminUserName, SmokePassword);
		String[] splitedUserName = removeNumbersFromString(SmokeCRMUser3LastName); 
		String UserLastName =splitedUserName[0]+bp.generateRandomNumber();
		ExcelUtils.writeData(smokeExcelPath,UserLastName, "Users", excelLabel.Variable_Name, "User3", excelLabel.User_Last_Name);
		scv = new SmokeCommonVariable(this);
		for (int i = 0; i < 3; i++) {
			try {
				if (bp.clickOnSetUpLink(environment, mode)) {
					if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						parentWindow = switchOnWindow(driver);
						if (parentWindow == null) {
							sa.assertTrue(false,"No new window is open after click on setup link in lighting mode so cannot create CRM User3");
							appLog.error("No new window is open after click on setup link in lighting mode so cannot create CRM User3");
							exit("No new window is open after click on setup link in lighting mode so cannot create CRM User3");
						}
					}
					if (bp.createPEUser(environment, mode, SmokeCRMUser3FirstName, UserLastName, cp.generateRandomEmailId(), SmokeCRMUser1UserLicense,
							SmokeCRMUser1Profile)) {
						appLog.info("PE User 3 created Successfully");
						if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
							ThreadSleep(5000);
							switchToDefaultContent(driver);
							switchToFrame(driver, 20, bp.getSetUpPageIframe(20));
							System.err.println(">>><<<<<<<<<<<<");
						}
						String emailID = isDisplayed(driver, bp.getUserEmailIDLabeltext(60), "visibility", 60,
								"User Email ID Text Label", action.THROWEXCEPTION).getText().trim();
						ExcelUtils.writeData(smokeExcelPath,emailID, "Users", excelLabel.Variable_Name, "User3", excelLabel.User_Email);
						if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
							driver.close();
							driver.switchTo().window(parentWindow);
						}
						break;
						
					}
				}
			} catch (Exception e) {
				appLog.error("could not find setup link, trying again..");
			}
			if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				driver.close();
				driver.switchTo().window(parentWindow);
			}
		}
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" })
	@Test
	public void Smoketc001_2_SuperAdminRegistration(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		List<String> headersText = new ArrayList<String>();
		List<String> sideMenusList = new ArrayList<String>();
		String registerPopup2of2Header = "Access<break>User<break>Profile";
		String sideMenusOnNIM = "Internal Users<break>Folder Templates<break>Manage Approvals<break>Watermarking<break>File Distributor Settings<break>Profiles";
		WebElement ele = null;
		lp.CRMLogin(SmokeSuperAdminUserName, SmokePassword);
		if (SmokeSuperAdminRegistered.equalsIgnoreCase("No")) {
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
									sa.assertTrue(false, "Header text is not verified");
								}
								List<WebElement> headers = nim.getregisterPopupColoumnHeader(60);
								for (int i = 0; i < headers.size(); i++) {
									headersText.add(headers.get(i).getText().trim());
								}
								if (compareMultipleListWithoutAssertion(registerPopup2of2Header, headersText)) {
									appLog.info("Headers are matched");
								} else {
									appLog.info("Headers are not matched");
									sa.assertTrue(false, "Headers are not matched");
								}
								String userData = SmokeCRMUser3FirstName + " " + SmokeCRMUser3LastName;
								ele = FindElement(driver, "//td[text()='" + userData
											+ "']/following-sibling::td[text()='" + SmokeCRMUser3Profile + "']", "Grid Data",
											action.SCROLLANDBOOLEAN, 10);
									if (ele != null) {
										appLog.info("Grid Data Is Verify for User." + userData);
									} else {
										appLog.info("Grid Data Is not Verify for User." + userData);
										sa.assertTrue(false, "Grid Data Is not Verify for User." + userData);
									}
								ele = FindElement(driver,
										"//td[text()='" + SmokeCRMUser3FirstName + " " + SmokeCRMUser3LastName + "']/..//input",
										"User CheckBox", action.SCROLLANDBOOLEAN, 60);
								if (click(driver, ele, "CRM User1 Checkbox", action.SCROLLANDBOOLEAN)) {
									appLog.info("Clicked on User 1 Checkbox");
								} else {
									appLog.info("Not able to click on User1 checkbox");
									sa.assertTrue(false, "Not able to click on User1 checkbox");
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
											sa.assertTrue(false,
													"Registration successful popup header text is not verified");
										}
										if (bp.verifyErrorMessageOnPage(
												NIMPageErrorMessage.registrationSuccessfulPopMessage,
												nim.getRegistrationSuccessfullPopupMessage(60),
												"Registarion successfull popup messgae")) {
											appLog.info("Registarion successfull popup messgae is verified");
										} else {
											sa.assertTrue(false,
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
														sa.assertTrue(false, "Landing page text is not verified");
													}
													ThreadSleep(2000);
													ele = FindElement(driver,
															"//span[text()='" + SmokeCRMUser3FirstName + " "
																	+ SmokeCRMUser3LastName
																	+ "']/../..//input[@type='checkbox']",
															"Checkbox for User3", action.SCROLLANDBOOLEAN, 60);
													if (isSelected(driver, ele, "USer3 Checkbox")) {
														appLog.info("User2 checkbox is selected");
													} else {
														appLog.info("User1 check box is not selected");
														sa.assertTrue(false, "User1 check box is not selected");
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
														sa.assertTrue(false, "All side menus are not veriified");
													}
												} else {
													appLog.info(
															"Not able to click on nim Tab  so cannot check side menus");
													sa.assertTrue(false,
															"Not able to click on nim Tab  so cannot check side menus");
												}
											} else {
												appLog.info(
														"Not able to click on registration successful Popup Close Button");
												sa.assertTrue(false,
														"Not able to click on registration successful Popup Close Button");
											}
										} else {
											appLog.info("Regsitartion successful popup Close button is not displaying");
											sa.assertTrue(false,
													"Regsitartion successful popup Close button is not displaying");
										}
									} else {
										appLog.info("Registration successful popup is not displaying");
										sa.assertTrue(false, "Registration successful popup is not displaying");
									}
								} else {
									appLog.info("Not able to click on complete button");
									sa.assertTrue(false, "Not able to click on complete button");
								}
							} else {
								appLog.info("Registr popup 2of 2 header is not displaying");
								sa.assertTrue(false, "Registr popup 2of 2 header is not displaying");
							}
						} else {
							appLog.info("not able to click on allow button");
							sa.assertTrue(false, "Not able to click on allow button");
						}
					} else {
						appLog.info("Not able to click on next button");
						sa.assertTrue(false, "Not able to click on close button");
					}
				} else {
					appLog.error("Not able to click on start button");
					sa.assertTrue(false, "Not able to click on start button");
				}
			} else {
				appLog.info("Not able to click on navatar investor manager tab");
				sa.assertTrue(false,
						"Not able to click on navatar investor manager tab so cannot verify error messages");
			}
		} else {
			appLog.info("Admin User is Already registered so we cannot check error messages");
			sa.assertTrue(false, "Admin User is Already registered so we cannot check error messages");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" })
	@Test
	public void Smoketc002_1_CreateCRMUser1InstallPackageAndThenCreatePassword(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		String parentWindow=null;
		boolean flag = false;
		List<String> lst=new ArrayList<String>();
		String addRemoveTabName="Partnerships"+","+"Commitments"+","+"Navatar Investor Manager";
		lp.CRMLogin(SmokeSuperAdminUserName, SmokePassword);
		String[] splitedUserName = removeNumbersFromString(SmokeCRMUser1LastName); 
		String UserLastName =splitedUserName[0]+bp.generateRandomNumber();
		ExcelUtils.writeData(smokeExcelPath,UserLastName, "Users", excelLabel.Variable_Name, "User1", excelLabel.User_Last_Name);
		WebElement ele=null;
		scv = new SmokeCommonVariable(this);
		if(mode.equalsIgnoreCase(Mode.Classic.toString())) {
			if(bp.removeUnusedTabs()){
				appLog.info("Unused tabs remove successfully");
			}else{
				appLog.info("Unused tabs not removed successfully");
				saa.assertTrue(false, "Unused tabs not removed successfully");
			}
			 ele = FindElement(driver, "//a[contains(@title,'Partnerships')]",
					"Partnerships tab", action.SCROLLANDBOOLEAN, 3);
			
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
					action.SCROLLANDBOOLEAN, 3);
			if(ele==null){
				lst=bp.addRemoveCustomTab("Commitments", customTabActionType.Add);
				if(!lst.isEmpty()){
					for (int i = 0; i < lst.size(); i++) {
						appLog.error(lst.get(i));
						saa.assertTrue(false,lst.get(i));
					}
				}
			}
		}else {
			if (lp.addTab_Lighting(mode, addRemoveTabName, 5)) {
				appLog.info("Tab added : "+addRemoveTabName);
//				log(LogStatus.INFO,"Tab added : "+addRemoveTabName,YesNo.No);
			} else {
				appLog.error("Tab not added : "+addRemoveTabName);
//				log(LogStatus.FAIL,"Tab not added : "+addRemoveTabName,YesNo.No);
				sa.assertTrue(false, "Tab not added : "+addRemoveTabName);
			}		
		}
		for (int i = 0; i < 3; i++) {
			try {
				if (bp.clickOnSetUpLink(environment, mode)) {
					if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						parentWindow = switchOnWindow(driver);
						if (parentWindow == null) {
							sa.assertTrue(false,"No new window is open after click on setup link in lighting mode so cannot create CRM User1");
							appLog.error("No new window is open after click on setup link in lighting mode so cannot create CRM User1");
							exit("No new window is open after click on setup link in lighting mode so cannot create CRM User1");
						}
					}
					if (bp.createPEUser(environment, mode,SmokeCRMUser1FirstName, UserLastName, cp.generateRandomEmailId(), SmokeCRMUser1UserLicense,
							SmokeCRMUser1Profile)) {
						appLog.info("PE User 1 created Successfully");
						if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
							ThreadSleep(5000);
							switchToDefaultContent(driver);
							switchToFrame(driver, 20, bp.getSetUpPageIframe(20));
							System.err.println(">>><<<<<<<<<<<<");
						}
						String emailID = isDisplayed(driver, bp.getUserEmailIDLabeltext(60), "visibility", 60,
								"User Email ID Text Label", action.THROWEXCEPTION).getText().trim();
						ExcelUtils.writeData(smokeExcelPath,emailID, "Users", excelLabel.Variable_Name, "User1", excelLabel.User_Email);
						flag = true;
						break;
						
					}
				}
			} catch (Exception e) {
				appLog.error("could not find setup link, trying again..");
			}
		}
		if (flag) {
			if (bp.installedPackages(environment, mode,SmokeCRMUser1FirstName,SmokeCRMUser1LastName)) {
				appLog.info("PE Package is installed Successfully in CRM User1: " + SmokeCRMUser1FirstName + " "+ SmokeCRMUser1LastName);
			} else {
				appLog.error(
						"Not able to install PE package in CRM User1: " + SmokeCRMUser1FirstName + " "+ SmokeCRMUser1LastName);
				sa.assertTrue(false,
						"Not able to install PE package in CRM User1: " + SmokeCRMUser1FirstName + " "+ SmokeCRMUser1LastName);
			}
			if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				driver.close();
				driver.switchTo().window(parentWindow);
			}
			lp.CRMlogout(environment,mode);
			driver.close();
			config(ExcelUtils.readDataFromPropertyFile("Browser"));
			bp = new BasePageBusinessLayer(driver);
			lp = new LoginPageBusinessLayer(driver);
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
			if(mode.equalsIgnoreCase(Mode.Classic.toString())) {
				if (bp.getSalesForceLightingIcon(10) != null) {
					lp.switchToClassic();
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
						action.SCROLLANDBOOLEAN, 5);
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
						action.SCROLLANDBOOLEAN, 5);
				if(ele==null){
					lst=bp.addRemoveCustomTab("Commitments", customTabActionType.Add);
					if(!lst.isEmpty()){
						for (int i = 0; i < lst.size(); i++) {
							appLog.error(lst.get(i));
							saa.assertTrue(false,lst.get(i));
						}
					}
				}
				if (bp.getNavatarInvestorManagerTab(5) == null) {
					lst=bp.addRemoveCustomTab("Navatar Investor Manager", customTabActionType.Add);
					if(!lst.isEmpty()){
						for (int i = 0; i < lst.size(); i++) {
							appLog.error(lst.get(i));
							saa.assertTrue(false,lst.get(i));
						}
					}
					if (bp.getNavatarInvestorManagerTab(5) != null) {
						appLog.info("Navatar Investor Manager Tab is displaying in Tab Row.");
					} else {
						appLog.info("Navatar Investor Manager Tab is not displaying in  Tab Row.");
						saa.assertTrue(false, "Navatar Investor Manager Tab is not displaying in Tab Row.");
					}
				}
			}else {
				if (lp.addTab_Lighting(mode, addRemoveTabName, 5)) {
					log(LogStatus.INFO,"Tab added : "+addRemoveTabName,YesNo.No);
				} else {
					log(LogStatus.FAIL,"Tab not added : "+addRemoveTabName,YesNo.No);
					sa.assertTrue(false, "Tab not added : "+addRemoveTabName);
				}	
			}
			lp.CRMlogout(environment,mode);
		}else{
			appLog.error("could not create CRM User so cannot installed Package and set password of created user");
			sa.assertTrue(false,"could not create CRM User so cannot installed Package and set password of created user");

		}
		sa.combineAssertions(saa);
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" })
	@Test 
	public void Smoketc002_2_provideAccesstoUser1(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(SmokeSuperAdminUserName, SmokePassword);
		if(lp.clickOnTab(environment,mode,TabName.NIMTab)) {
			if(nim.giveAccessToUserInNIMTabFromAdmin(environment,mode,SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName, accessType.InternalUserAccess)) {
				appLog.info("internal user access has been provided to the user : "+SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName);
			}else {
				appLog.error("Not able to provide internal user access to the user : "+SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName);
				sa.assertTrue(false, "Not able to provide internal user access to the user : "+SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName);
			}
		}else {
			appLog.error("Not able to click on NIM tab so cannot provide access to user "+SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName);
			sa.assertTrue(false, "Not able to click on NIM tab so cannot provide access to user "+SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName);
		}
		lp.CRMlogout(environment,mode);
		sa.assertAll();
		
	}

	
	@Parameters({ "environment", "mode" })
	@Test
	public void Smoketc002_3_user1CompleteRegistration(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(SmokeCRMUser1Email, SmokePassword);
		if (nim.clickOnTab(environment,mode,TabName.NIMTab)) {
			if (nim.NIMRegistration(environment,mode,userType.CRMUser, SmokeCRMUser1FirstName, SmokeCRMUser1LastName)) {
				appLog.info(SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName+" CRM User 1 is registered successfully ");
			} else {
				appLog.error(SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName+" CRM User 1 is not registered successfully ");
				sa.assertTrue(false, SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName+" CRM User 1 is not registered successfully ");
			}
		}else {
			appLog.error("Not able to click on NIM tab so cannot register CRM User 1 "+SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName);
			sa.assertTrue(false, "Not able to click on NIM tab so cannot register CRM User 1 "+SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName);
		}
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" })
	@Test 
	public void Smoketc003_1_CreateCRMUser2InstallPackageAndThenCreatePassword(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		String parentWindow=null;
		WebElement ele=null;
		boolean flag= false;
		List<String> lst=new ArrayList<String>();
		String addRemoveTabName="Partnerships"+","+"Commitments"+","+"Navatar Investor Manager";
		lp.CRMLogin(SmokeSuperAdminUserName, SmokePassword);
		String[] splitedUserName = removeNumbersFromString(SmokeCRMUser2LastName); 
		String UserLastName =splitedUserName[0]+bp.generateRandomNumber();
		ExcelUtils.writeData(smokeExcelPath,UserLastName, "Users", excelLabel.Variable_Name, "User2", excelLabel.User_Last_Name);
		scv = new SmokeCommonVariable(this);
		if(mode.equalsIgnoreCase(Mode.Classic.toString())) {
			if(bp.removeUnusedTabs()){
				appLog.info("Unused tabs remove successfully");
			}else{
				appLog.info("Unused tabs not removed successfully");
				saa.assertTrue(false, "Unused tabs not removed successfully");
			}
			 ele=FindElement(driver, "//a[contains(@title,'Partnerships')]", "Partnerships tab",
					action.SCROLLANDBOOLEAN, 3);
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
					action.SCROLLANDBOOLEAN, 3);
			if(ele==null){
				lst=bp.addRemoveCustomTab("Commitments", customTabActionType.Add);
				if(!lst.isEmpty()){
					for (int i = 0; i < lst.size(); i++) {
						appLog.error(lst.get(i));
						saa.assertTrue(false,lst.get(i));
					}
				}
			}
		}else {
			if (lp.addTab_Lighting(mode, addRemoveTabName, 5)) {
				appLog.info("Tab added : "+addRemoveTabName);
//				log(LogStatus.INFO,"Tab added : "+addRemoveTabName,YesNo.No);
			} else {
				appLog.error("Tab not added : "+addRemoveTabName);
//				log(LogStatus.FAIL,"Tab not added : "+addRemoveTabName,YesNo.No);
				sa.assertTrue(false, "Tab not added : "+addRemoveTabName);
			}		
		}
		
		for (int i = 0; i < 3; i++) {
			try {
				if (bp.clickOnSetUpLink(environment, mode)) {
					if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						parentWindow = switchOnWindow(driver);
						if (parentWindow == null) {
							sa.assertTrue(false,"No new window is open after click on setup link in lighting mode so cannot create CRM User1");
							appLog.error("No new window is open after click on setup link in lighting mode so cannot create CRM User1");
							exit("No new window is open after click on setup link in lighting mode so cannot create CRM User1");
						}
					}
					if (bp.createPEUser(environment, mode,SmokeCRMUser2FirstName, UserLastName, cp.generateRandomEmailId(), SmokeCRMUser1UserLicense,
							SmokeCRMUser1Profile)) {
						appLog.info("PE User 2 created Successfully");
						if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
							ThreadSleep(5000);
							switchToDefaultContent(driver);
							switchToFrame(driver, 20, bp.getSetUpPageIframe(20));
							System.err.println(">>><<<<<<<<<<<<");
						}
						String emailID = isDisplayed(driver, bp.getUserEmailIDLabeltext(60), "visibility", 60,
								"User Email ID Text Label", action.THROWEXCEPTION).getText().trim();
						ExcelUtils.writeData(smokeExcelPath,emailID, "Users", excelLabel.Variable_Name, "User2", excelLabel.User_Email);
						flag = true;
						break;
						
					}
				}
			} catch (Exception e) {
				appLog.error("could not find setup link, trying again..");
			}
		}
		
		
		if (flag) {
			if (bp.installedPackages(environment,mode,SmokeCRMUser2FirstName,SmokeCRMUser2LastName)) {
				appLog.info("Install package is done for PE User 2 succesfully");
			} else {
				appLog.info("Install package is not done for PE User 2 succesfully");
				saa.assertTrue(false, "Install package is not done for PE User 2 succesfully");
			}
			if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				driver.close();
				driver.switchTo().window(parentWindow);
			}
			lp.CRMlogout(environment,mode);
			driver.close();
			config(ExcelUtils.readDataFromPropertyFile("Browser"));
			bp = new BasePageBusinessLayer(driver);
			lp = new LoginPageBusinessLayer(driver);
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
				
				if(mode.equalsIgnoreCase(Mode.Classic.toString())) {
					if (bp.getSalesForceLightingIcon(10) != null) {
						lp.switchToClassic();
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
							action.SCROLLANDBOOLEAN, 5);
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
							action.SCROLLANDBOOLEAN, 5);
					if(ele==null){
						lst=bp.addRemoveCustomTab("Commitments", customTabActionType.Add);
						if(!lst.isEmpty()){
							for (int i = 0; i < lst.size(); i++) {
								appLog.error(lst.get(i));
								saa.assertTrue(false,lst.get(i));
							}
						}
					}
					if (bp.getNavatarInvestorManagerTab(5) == null) {
						lst=bp.addRemoveCustomTab("Navatar Investor Manager", customTabActionType.Add);
						if(!lst.isEmpty()){
							for (int i = 0; i < lst.size(); i++) {
								appLog.error(lst.get(i));
								saa.assertTrue(false,lst.get(i));
							}
						}
						if (bp.getNavatarInvestorManagerTab(5) != null) {
							appLog.info("Navatar Investor Manager Tab is displaying in Tab Row.");
						} else {
							appLog.info("Navatar Investor Manager Tab is not displaying in  Tab Row.");
							saa.assertTrue(false, "Navatar Investor Manager Tab is not displaying in Tab Row.");
						}
					}
				}else {
					if (lp.addTab_Lighting(mode, addRemoveTabName, 5)) {
						log(LogStatus.INFO,"Tab added : "+addRemoveTabName,YesNo.No);
					} else {
						log(LogStatus.FAIL,"Tab not added : "+addRemoveTabName,YesNo.No);
						sa.assertTrue(false, "Tab not added : "+addRemoveTabName);
					}	
				}
				lp.CRMlogout(environment,mode);
			} else {
				appLog.info("Password is not set for user2");
				saa.assertTrue(false, "Password is not set for user2");
			}
		}else{
			appLog.error("could not create CRM User so cannot installed Package and set password of created user");
			sa.assertTrue(false,"could not create CRM User so cannot installed Package and set password of created user");

		}
		sa.assertAll();
	}
	

	@Parameters({ "environment", "mode" })
	@Test 
	public void Smoketc003_2_provideAccesstoUser2(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(SmokeSuperAdminUserName, SmokePassword);
		if(lp.clickOnTab(environment,mode,TabName.NIMTab)) {
			if(nim.giveAccessToUserInNIMTabFromAdmin(environment,mode,SmokeCRMUser2FirstName+" "+SmokeCRMUser2LastName, accessType.InternalUserAccess)) {
				appLog.info("internal user access has been provided to the user : "+SmokeCRMUser2FirstName+" "+SmokeCRMUser2LastName);
			}else {
				appLog.error("Not able to provide internal user access to the user : "+SmokeCRMUser2FirstName+" "+SmokeCRMUser2LastName);
				sa.assertTrue(false, "Not able to provide internal user access to the user : "+SmokeCRMUser2FirstName+" "+SmokeCRMUser2LastName);
			}
			
			if(nim.giveAccessToUserInNIMTabFromAdmin(environment,mode,SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName, accessType.AdminUserAccess)) {
				appLog.info("internal user access has been provided to the user : "+SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName);
			}else {
				appLog.error("Not able to provide internal user access to the user : "+SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName);
				sa.assertTrue(false, "Not able to provide internal user access to the user : "+SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName);
			}
			
		}else {
			appLog.error("Not able to click on NIM tab so cannot provide access to user "+SmokeCRMUser2FirstName+" "+SmokeCRMUser2LastName);
			sa.assertTrue(false, "Not able to click on NIM tab so cannot provide access to user "+SmokeCRMUser2FirstName+" "+SmokeCRMUser2LastName);
		}
		lp.CRMlogout(environment,mode);
		sa.assertAll();
		
	}
	

	@Parameters({ "environment", "mode" })
	@Test
	public void Smoketc003_3_user2CompleteRegsitration(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(SmokeCRMUser2Email, SmokePassword);
		if (nim.clickOnTab(environment,mode,TabName.NIMTab)) {
			if (nim.NIMRegistration(environment,mode,userType.CRMUser, SmokeCRMUser2FirstName, SmokeCRMUser2LastName)) {
				appLog.info(SmokeCRMUser2FirstName+" "+SmokeCRMUser2LastName+" CRM User 2 is registered successfully ");
			} else {
				appLog.error(SmokeCRMUser2FirstName+" "+SmokeCRMUser2LastName+" CRM User 2 is not registered successfully ");
				sa.assertTrue(false, SmokeCRMUser2FirstName+" "+SmokeCRMUser2LastName+" CRM User 2 is not registered successfully ");
			}
		}else {
			appLog.error("Not able to click on NIM tab so cannot register CRM User 2 "+SmokeCRMUser2FirstName+" "+SmokeCRMUser2LastName);
			sa.assertTrue(false, "Not able to click on NIM tab so cannot register CRM User 2 "+SmokeCRMUser2FirstName+" "+SmokeCRMUser2LastName);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" })
	@Test 
	public void Smoketc004_createPreconditionData(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ins = new InstitutionPageBusinessLayer(driver);
		FundsPageBusinessLayer fund  = new FundsPageBusinessLayer(driver);
		FundRaisingPageBusinessLayer fr = new FundRaisingPageBusinessLayer(driver);
		PartnershipPageBusinessLayer p = new PartnershipPageBusinessLayer(driver);
		CommitmentPageBusinessLayer com = new CommitmentPageBusinessLayer(driver);
		lp.CRMLogin(SmokeCRMUser1Email, SmokePassword);
		if (nim.clickOnTab(environment,mode,TabName.NIMTab)) {
			if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				switchToFrame(driver, 60, nim.getNIMTabParentFrame_Lightning());
				ThreadSleep(5000);
				switchToFrame(driver, 30, nim.getFrame(PageName.NavatarInvestorManager, 30));
				if (nim.createFolderTemplate("FolderTemp", SmokefolderTemplateName, "", 60)) {
					appLog.info("folder template"+SmokefolderTemplateName+" is successfully created");
				}
				else {
					appLog.error("folder template could not be created");
					sa.assertTrue(false, "folder template could not be created");
				}
			}else {
				appLog.error("Not able to switch to NIM Tab Parent Frame so cannot create folder template");
				sa.assertTrue(false, "Not able to switch to NIM Tab Parent Frame so cannot create folder template");
				
			}
		}else {
			appLog.error("Not able to click on NIM tab so cannot create folder template");
			sa.assertTrue(false, "Not able to click on NIM tab so cannot create folder template");
		}
		switchToDefaultContent(driver);
		if(ins.clickOnTab(environment,mode,TabName.InstituitonsTab)) {
			if(ins.createInstitution(environment,mode,SmokeInstitution1,"Institution",null,null)) {
				
			}else {
				appLog.error("Not able to create institution: "+SmokeInstitution1);
				sa.assertTrue(false, "Not able to create institution: "+SmokeInstitution1);
			}
		}else {
				appLog.error("Not able to click on institution tab so cannot create institution: "+SmokeInstitution1);
				sa.assertTrue(false, "Not able to click on institution tab so cannot create institution: "+SmokeInstitution1);
		}
		if(ins.clickOnTab(environment,mode,TabName.InstituitonsTab)) {
			if(ins.createInstitution(environment,mode,SmokeInstitution2,"Institution",null,null)) {
				
			}else {
				appLog.error("Not able to create institution: "+SmokeInstitution2);
				sa.assertTrue(false, "Not able to create institution: "+SmokeInstitution2);
			}
		}else {
				appLog.error("Not able to click on institution tab so cannot create institution: "+SmokeInstitution2);
				sa.assertTrue(false, "Not able to click on institution tab so cannot create institution: "+SmokeInstitution2);
		}
		if(bp.clickOnTab(environment,mode,TabName.ContactTab)){
			String emailId = cp.generateRandomEmailId();
			if(cp.createContact(environment,mode,SmokeContact1FirstName, SmokeContact1LastName,SmokeInstitution1, emailId,null,null,CreationPage.ContactPage)) {
				appLog.info("contact is created: "+SmokeContact1FirstName+" "+SmokeContact1LastName);
				ExcelUtils.writeData(smokeExcelPath,emailId,"Contact", excelLabel.Variable_Name, "SmokeC1",excelLabel.Contact_EmailId);
				
			}else {
				appLog.error("Not able to create contact: "+SmokeContact1FirstName+" "+SmokeContact1LastName);
				sa.assertTrue(false, "Not able to create contact: "+SmokeContact1FirstName+" "+SmokeContact1LastName);
			}
		}
		if(bp.clickOnTab(environment,mode,TabName.ContactTab)){
			String emailId = cp.generateRandomEmailId();
			if(cp.createContact(environment,mode,SmokeContact2FirstName, SmokeContact2LastName,SmokeInstitution1, emailId,null,null,CreationPage.ContactPage)) {
				appLog.info("contact is created: "+SmokeContact2FirstName+" "+SmokeContact2LastName);
				ExcelUtils.writeData(smokeExcelPath,emailId,"Contact", excelLabel.Variable_Name, "SmokeC2",excelLabel.Contact_EmailId);
				
			}else {
				appLog.error("Not able to create contact: "+SmokeContact2FirstName+" "+SmokeContact2LastName);
				sa.assertTrue(false, "Not able to create contact: "+SmokeContact2FirstName+" "+SmokeContact2LastName);
			}
		}
		if(bp.clickOnTab(environment,mode,TabName.ContactTab)){
			String emailId = cp.generateRandomEmailId();
			if(cp.createContact(environment,mode,SmokeContact3FirstName, SmokeContact3LastName,SmokeInstitution1, emailId,null,null,CreationPage.ContactPage)) {
				appLog.info("contact is created: "+SmokeContact3FirstName+" "+SmokeContact3LastName);
				ExcelUtils.writeData(smokeExcelPath,emailId,"Contact", excelLabel.Variable_Name, "SmokeC3",excelLabel.Contact_EmailId);
				
			}else {
				appLog.error("Not able to create contact: "+SmokeContact3FirstName+" "+SmokeContact3LastName);
				sa.assertTrue(false, "Not able to create contact: "+SmokeContact3FirstName+" "+SmokeContact3LastName);
			}
		}
		if(fund.clickOnTab(environment,mode,TabName.FundsTab)) {
			if(fund.createFund(environment,mode,SmokeFundName1,SmokeFundType1,SmokeFundInvestmentCategory1,null,null)) {
				appLog.info("fund is created: "+SmokeFundName1);
			}else {
				appLog.error("Not able to create fund: "+SmokeFundName1);
				sa.assertTrue(false, "Not able to create fund: "+SmokeFundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot create fund: "+SmokeFundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot create fund: "+SmokeFundName1);
		}
		if(bp.clickOnTab(environment,mode,TabName.FundraisingsTab)) {
			if(fr.createFundRaising(environment,mode,SmokeFundRaisingName1,SmokeFundName1,SmokeInstitution1)) {
				appLog.info("fundraising is created : "+SmokeFundRaisingName1);
			}else {
				appLog.error("Not able to create fundraising: "+SmokeFundRaisingName1);
				sa.assertTrue(false, "Not able to create fundraising: "+SmokeFundRaisingName1);
			}
		}else {
			appLog.error("Not able to click on fundraising tab so cannot create fundraising: "+SmokeFundRaisingName1);
			sa.assertTrue(false,"Not able to click on fundraising tab so cannot create fundraising: "+SmokeFundRaisingName1);
		}
		if(bp.clickOnTab(environment,mode,TabName.FundraisingsTab)) {
			if(fr.createFundRaising(environment,mode,SmokeFundRaisingName2,SmokeFundName1,SmokeInstitution2)) {
				appLog.info("fundraising is created : "+SmokeFundRaisingName2);
			}else {
				appLog.error("Not able to create fundraising: "+SmokeFundRaisingName2);
				sa.assertTrue(false, "Not able to create fundraising: "+SmokeFundRaisingName2);
			}
		}else {
			appLog.error("Not able to click on fundraising tab so cannot create fundraising: "+SmokeFundRaisingName2);
			sa.assertTrue(false,"Not able to click on fundraising tab so cannot create fundraising: "+SmokeFundRaisingName2);
		}
		if(bp.clickOnTab(environment,mode,TabName.InstituitonsTab)) {
			
			if(ins.createInstitution(environment, mode, SmokeLimitedPartner1, "Limited Partner", InstitutionPageFieldLabelText.Parent_Institution.toString(), SmokeInstitution1)) {
				appLog.info("limited partner is created: "+SmokeLimitedPartner1);
			}else {
				appLog.error("Not able to create limited partner: "+SmokeLimitedPartner1);
				sa.assertTrue(false, "Not able to create limited partner: "+SmokeLimitedPartner1);
			}
		}else {
			appLog.error("Not able to click on institution tab so cannot create limite partner: "+SmokeLimitedPartner1);
			sa.assertTrue(false, "Not able to click on institution tab so cannot create limite partner: "+SmokeLimitedPartner1);
		}
		if(bp.clickOnTab(environment,mode,TabName.InstituitonsTab)) {
			if(ins.createInstitution(environment, mode, SmokeLimitedPartner2, "Limited Partner", InstitutionPageFieldLabelText.Parent_Institution.toString(), SmokeInstitution2)) {
				appLog.info("limited partner is created: "+SmokeLimitedPartner2);
			}else {
				appLog.error("Not able to create limited partner: "+SmokeLimitedPartner2);
				sa.assertTrue(false, "Not able to create limited partner: "+SmokeLimitedPartner2);
			}
		}else {
			appLog.error("Not able to click on institution tab so cannot create limite partner: "+SmokeLimitedPartner2);
			sa.assertTrue(false, "Not able to click on institution tab so cannot create limite partner: "+SmokeLimitedPartner2);
		}
		if(bp.clickOnTab(environment,mode,TabName.PartnershipsTab)) {
			if(p.createPartnership(environment,mode,SmokePartnership1,SmokeFundName1)) {
				appLog.info("partnership is created: "+SmokePartnership1);
			}else {
				appLog.error("Not able to create partnership: "+SmokePartnership1);
				sa.assertTrue(false, "Not able to create partnership: "+SmokePartnership1);
			}
		}else {
			appLog.error("Not able to click on partnership tab so cannot create partnership: "+SmokePartnership1);
			sa.assertTrue(false, "Not able to click on partnership tab so cannot create partnership: "+SmokePartnership1);
		}
		if(bp.clickOnTab(environment,mode,TabName.CommitmentsTab)) {
			if(com.createCommitment(environment, mode, SmokeLimitedPartner1, SmokePartnership1, "SmokeCom1", smokeExcelPath)) {
				appLog.info("commitment is created successfully");
			}else {
				appLog.error("Not able to create commitment for limited partner: "+SmokeLimitedPartner1+" and partnership Name: "+SmokePartnership1);
				sa.assertTrue(false, "Not able to create commitment for limited partner: "+SmokeLimitedPartner1+" and partnership Name: "+SmokePartnership1);
			}
		}else {
			appLog.error("Not able to click on commitment tab so cannot create committment for limite partner: "+SmokeLimitedPartner1+" and partnership Name: "+SmokePartnership1);
			sa.assertTrue(false, "Not able to click on commitment tab so cannot create committment for limite partner: "+SmokeLimitedPartner1+" and partnership Name: "+SmokePartnership1);
		}
		
		if(bp.clickOnTab(environment,mode,TabName.CommitmentsTab)) {
			
			if(com.createCommitment(environment, mode,SmokeLimitedPartner2,SmokePartnership1,"SmokeCom2", smokeExcelPath)) {
				appLog.info("commitment is created successfully");
			}else {
				appLog.error("Not able to create commitment for limited partner: "+SmokeLimitedPartner2+" and partnership Name: "+SmokePartnership1);
				sa.assertTrue(false, "Not able to create commitment for limited partner: "+SmokeLimitedPartner2+" and partnership Name: "+SmokePartnership1);
			}
		}else {
			appLog.error("Not able to click on commitment tab so cannot create committment for limite partner: "+SmokeLimitedPartner2+" and partnership Name: "+SmokePartnership1);
			sa.assertTrue(false, "Not able to click on commitment tab so cannot create committment for limite partner: "+SmokeLimitedPartner2+" and partnership Name: "+SmokePartnership1);
		}
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" })
	@Test 
	public void Smoketc005_1_buildFRWorkSpaceAndProvideContactAccess(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		String standardFolder=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		lp.CRMLogin(SmokeCRMUser1Email,SmokePassword);
		if(fp.clickOnTab(environment,mode,TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(environment,mode,SmokeFundName1)) {
				String Size=ExcelUtils.readData(smokeExcelPath,"Funds",excelLabel.Variable_Name, "SmokeFund1", excelLabel.Fund_Size);
				String vintageyear=ExcelUtils.readData(smokeExcelPath,"Funds",excelLabel.Variable_Name, "SmokeFund1", excelLabel.Fund_VintageYear);
				String con=ExcelUtils.readData(smokeExcelPath,"Funds",excelLabel.Variable_Name, "SmokeFund1", excelLabel.Fund_Contact);
				String phone=ExcelUtils.readData(smokeExcelPath,"Funds",excelLabel.Variable_Name, "SmokeFund1", excelLabel.Fund_Phone);
				String email=ExcelUtils.readData(smokeExcelPath,"Funds",excelLabel.Variable_Name, "SmokeFund1", excelLabel.Fund_Email);
				String description=ExcelUtils.readData(smokeExcelPath,"Funds",excelLabel.Variable_Name, "SmokeFund1", excelLabel.Fund_Description);
				String[] data= {Size,vintageyear,con,phone,email,description};
				if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					if(switchToFrame(driver, 30, fp.getNIMTabParentFrame_Lightning())) {
						
					}else {
						appLog.error("Not able to switch in parent frame so cannot build FR Workspace");
						exit("Not able to switch in parent frame so cannot build FR Workspace");
					}
				}else {
					switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				}
				System.err.println("Switched to frame.");
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 20), Workspace.FundraisingWorkspace.toString()+" View.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 10), Workspace.FundraisingWorkspace.toString()+" Workspace Button", action.BOOLEAN)){
					if(!sendKeys(driver, fp.getSizeInMillionTextBox(Workspace.FundraisingWorkspace, 10), data[0], "Size in Million text box", action.BOOLEAN)){
						sa.assertTrue(false,"Not able to pass data to size in million text box.");
					}
					if(!sendKeys(driver, fp.getVintageYear(Workspace.FundraisingWorkspace, 10), data[1], "vintage Year", action.BOOLEAN)){
						sa.assertTrue(false,"Not able to pass data to Vintage Year text box.");
					}
					String value = getAttribute(driver, fp.getContactTextBox(Workspace.FundraisingWorkspace, 10), "Contact Text Box", "value");
					if(value!=null && !value.isEmpty()){
						appLog.info("value is already present in contact text box.");
					} else {
						if(!sendKeys(driver, fp.getContactTextBox(Workspace.FundraisingWorkspace,10), data[2], "Contact text Box", action.BOOLEAN)){
							sa.assertTrue(false, "Not able to pass data to Contact text box.");
						}
					}
					value = getAttribute(driver, fp.getPhoneTextBox(Workspace.FundraisingWorkspace, 10), "Phone Text Box", "value");
					if(value!=null && !value.isEmpty()){
						appLog.info("value is already present in contact text box.");
					} else {
						if(!sendKeys(driver, fp.getPhoneTextBox(Workspace.FundraisingWorkspace, 10), data[3], "Phone text Box", action.BOOLEAN)){
							sa.assertTrue(false, "Not able to pass data to phone text box.");
						}
					}
					value = getAttribute(driver, fp.getEmailTextBox(Workspace.FundraisingWorkspace, 30), "Email Text Box", "value");
					if(value!=null && !value.isEmpty()){
						appLog.error("value is already present in contact text box.");
					} else {
						if(!sendKeys(driver, fp.getEmailTextBox(Workspace.FundraisingWorkspace, 10),data[4], "Email text Box", action.BOOLEAN)){
							appLog.error("Not able to pass data to email text box.");
						}
					}
					value = getAttribute(driver, fp.getDescriptionTextBox(Workspace.FundraisingWorkspace, 10), "Description Text Box", "value");
					if(value!=null && !value.isEmpty()){
						appLog.info("value is already present in contact text box.");
					} else {
						if(!sendKeys(driver, fp.getDescriptionTextBox(Workspace.FundraisingWorkspace, 10),data[5], "Description text Box", action.BOOLEAN)){
						}
					}
					if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 10), "Next Button", action.BOOLEAN)){
						if(!fp.importFolderTemplate(smokeExcelPath,"FolderTemp", SmokefolderTemplateName, WorkSpaceAction.IMPORTFOLDERTEMPLATE, Workspace.FundraisingWorkspace, 20)){
							appLog.error("Folder sructure is not created properly");
							sa.assertTrue(false,"Folder sructure is not created properly");
						} else {
							appLog.info("Folder Structure is created successfully.");
							if(click(driver, fp.getClearAllFolderButton(Workspace.FundraisingWorkspace, EnableDisable.Enable, 30), "clear all button", action.BOOLEAN)) {
								appLog.info("clicked on clear all button");
								ThreadSleep(2000);
								if(click(driver, fp.getClearAllFolderConfirmationPopUpNoButton(Workspace.FundraisingWorkspace, 30), "clear all folder no button", action.BOOLEAN)) {
									appLog.info("clicked on clear all No button");
								}else {
									appLog.error("Not able to click on clear all No button so cannot close pop up");
									sa.assertTrue(false, "Not able to click on clear all No button so cannot close pop up");
								}
							}else {
								appLog.error("Not able to click on clear all folder button so cannot check pop up");
								sa.assertTrue(false, "Not able to click on clear all folder button so cannot check pop up");
							}
							String id=null;
							id=fp.getCreatedFolderId(standardFolder, PageName.ManageFolderPopUp, 20);
							if(id!=null) {
								if(fp.clickOnRenameFolderButton(id)) {
									if(click(driver, fp.getParentRenameFolderCancelButton(Workspace.FundraisingWorkspace, PageName.FundsPage, 10), "parent folder pop up cancel button", action.SCROLLANDBOOLEAN)) {
										appLog.info(standardFolder+" folder  rename pop is open and close successfully");
									}else {
										appLog.error(standardFolder+" folder rename pop is not open so cannot click on cancel button");
										sa.assertTrue(false, standardFolder+" rename folder pop is not open so cannot click on cancel button");
									}
								}else {
									appLog.error("Not able to click on folder "+standardFolder+" rename icon so cannot check pop up ");
									sa.assertTrue(false, "Not able to click on folder "+standardFolder+" rename icon so cannot check pop up ");
								}
							}else {
								appLog.error(standardFolder+" is not visible so cannot click on edit icon and check edit pop up");
								sa.assertTrue(false, standardFolder+" is not visible so cannot click on edit icon and check edit pop up");
							}
							id=null;
							id=fp.getCreatedFolderId(standardFolder, PageName.ManageFolderPopUp, 20);
							if (bp.clickOnAddFolderButton(id)) {
								if(click(driver, fp.getAddFolderChildFolderCancelButton(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 10), "child add folder pop up cancel button", action.SCROLLANDBOOLEAN)) {
									appLog.info(standardFolder+" add folder pop is open and close successfully");
								}else {
									appLog.error(standardFolder+" add folder pop is not open so cannot click on cancel button");
									sa.assertTrue(false, standardFolder+" add folder pop is not open so cannot click on cancel button");
								}
								
							}else {
								appLog.error("Not able to click on add folder button so cannot check add folder pop up");
								sa.assertTrue(false, "Not able to click on add folder button so cannot check add folder pop up");
							}
							id=fp.getCreatedFolderId(standardFolder, PageName.ManageFolderPopUp, 20);
							if(id!=null) {
								if(fp.clickOnDeleteFolderButton(id)) {
									if(click(driver, fp.getFolderDeleteNoBtn(Workspace.FundraisingWorkspace,PageName.FundsPage, 10), "folder delete no Button", action.BOOLEAN)) {
										appLog.info("Folder delete pop up is open and close successfully");
									}else {
										appLog.error("No button is not displaying on folder delete pop up so cannot click on No button");
										sa.assertTrue(false, "No button is not displaying on folder delete pop up so cannot click on No button");
									}
								}else {
									appLog.error("Not able to click on folder "+standardFolder+" delete icon so cannot check pop up ");
									sa.assertTrue(false, "Not able to click on folder "+standardFolder+" delete icon so cannot check pop up ");
								}
							}else {
								appLog.error(standardFolder+" is not visible so cannot click on delete icon and check edit pop up");
								sa.assertTrue(false, standardFolder+" is not visible so cannot click on delete icon and check edit pop up");
							}
							
						}
						if(click(driver, fp.getNext2Of3Button(Workspace.FundraisingWorkspace, 10), "Next button 2Of3", action.BOOLEAN)){
							ThreadSleep(10000);
							if(click(driver, fp.getStep3Of3ApplyButton(Workspace.FundraisingWorkspace, 10), "apply button", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on apply button");
								if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.filterPleaseSelectAFieldErroresage,
										fp.getManageInvestorFilterfieldSelectErrorMessage(Workspace.FundraisingWorkspace).get(0),
										"Please select a field error Message in filter")) {
									appLog.info("Please select a field Error Message is verified at filter");
								} else {
									sa.assertTrue(false,
											"Please select a field error message is not verified at filter.Expected:"
													+ FundsPageErrorMessage.filterPleaseSelectAFieldErroresage + " Actual"
													+ getText(driver,
															fp.getManageInvestorFilterfieldSelectErrorMessage(
																	Workspace.FundraisingWorkspace).get(0),
															"Please select a field error Message in filter",
															action.BOOLEAN));
								}
							}else {
								appLog.error("Not able to click on apply button so cannot check error message "+FundsPageErrorMessage.filterPleaseSelectAFieldErroresage);
								sa.assertTrue(false, "Not able to click on apply button so cannot check error message "+FundsPageErrorMessage.filterPleaseSelectAFieldErroresage);
							}
							
							if(click(driver, fp.getStep3Of3ClearButton(Workspace.FundraisingWorkspace, 10), "clear button", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked  on clear button ");
								
							}else {
								appLog.error("Not able to click on clear button so cannot check clear button functionality");
								sa.assertTrue(false, "Not able to click on clear button so cannot check clear button functionality");
							}
							
							if(click(driver, fp.getStep3Of3AddRowLink(Workspace.FundraisingWorkspace, 10), "add row link", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on add rows link ");
								if(click(driver, fp.getManageInvestorFilterRemoveRowIcon(Workspace.FundraisingWorkspace).get(0), "delete added row cross icon", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on delete row cross icon");
								}else {
									appLog.error("Not able to click on delete row cross icon so cannot delete added row");
									sa.assertTrue(false, "Not able to click on delete row cross icon so cannot delete added row");
								}
							}else {
								appLog.error("Not able to click on add row link so cannot add more rows in filter investor");
								sa.assertTrue(false, "Not able to click on add row link so cannot add more rows in filter investor");
							}
							if(fp.selectInstitutionOrLP(SmokeInstitution1, Workspace.FundraisingWorkspace, 10)){
								if(click(driver, fp.getInvestorAddedConfirmationCloseButton(Workspace.FundraisingWorkspace, 10), "Investor Addition confirmation Close Button", action.BOOLEAN));
							}
							if(click(driver, fp.getDone3Of3Button(Workspace.FundraisingWorkspace, 10), "Done button", action.BOOLEAN)){
								appLog.info("Workspace has been created.");
								if(click(driver, fp.getCongratulationsCloseButton(Workspace.FundraisingWorkspace, 10), "Congratualtions pop close button", action.BOOLEAN)){
									appLog.info("Workspace Created Successfully.");
								} else {
									refresh(driver);
								}
							} else {
								appLog.error("Workspace is not created.");
								sa.assertTrue(false,"Workspace is not created.");
							}
						} else {
							appLog.error("Next button 2Of3 cannot be clicked, So cannot build the workspace");
							sa.assertTrue(false,"Next button 2Of3 cannot be clicked, So cannot build the workspace");
							
						}
					} else {
						appLog.error("Next button cannot be clicked, so cannot build the workspace.");
						sa.assertTrue(false,"Next button cannot be clicked, so cannot build the workspace.");
					}
				
				}else {
					appLog.error("Not able to click on build workspace button so cannot build fundraising workspace");
					sa.assertTrue(false, "Not able to click on build workspace button so cannot build fundraising workspace");
				}
				
				
			}else {
				appLog.error("Not able to click on created fund Name so cannot build fundraising workspace: "+SmokeFundName1);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot build fundraising workspace: "+SmokeFundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot build fundraising workspace: "+SmokeFundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot build fundraising workspace: "+SmokeFundName1);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	

	@Parameters({ "environment", "mode" })
	@Test 
	public void Smoketc006_inviteContactAndSendEmailToContact(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String shrdfolder=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		lp.CRMLogin(SmokeCRMUser1Email, SmokePassword);
		if(fp.clickOnTab(environment, mode,TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(environment, mode,SmokeFundName1)) {
				if(fp.inviteContact(environment, mode,SmokeInstitution1, SmokeContact1EmailId,null, FolderType.Standard,"Upload","Yes", "No",null, Workspace.FundraisingWorkspace, null)) {
					appLog.info("Contact "+SmokeContact1FirstName+" "+SmokeContact1LastName+" is invited successfuly");
				}else {
					appLog.error("Contact "+SmokeContact1FirstName+" "+SmokeContact1LastName+" is not invited successfuly");
					sa.assertTrue(false, "Contact "+SmokeContact1FirstName+" "+SmokeContact1LastName+" is not invited successfuly");
				}
				if(fp.inviteContact(environment, mode,SmokeInstitution1, SmokeContact3EmailId,null, FolderType.Standard,null,"Yes", "No",null, Workspace.FundraisingWorkspace, null)) {
					appLog.info("Contact "+SmokeContact3FirstName+" "+SmokeContact3LastName+" is invited successfuly");
				}else {
					appLog.error("Contact "+SmokeContact3FirstName+" "+SmokeContact3LastName+" is not invited successfuly");
					sa.assertTrue(false, "Contact "+SmokeContact3FirstName+" "+SmokeContact3LastName+" is not invited successfuly");
				}
				
				if(fp.inviteContact(environment, mode,SmokeInstitution1, SmokeContact2EmailId,null,FolderType.Standard,null,null, null,null, Workspace.FundraisingWorkspace, null)) {
					appLog.info("Contact "+SmokeContact2FirstName+" "+SmokeContact2LastName+" is invited successfuly");
					if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						switchToDefaultContent(driver);
						if(switchToFrame(driver, 10, fp.getNIMTabParentFrame_Lightning())) {
							
						}else {
							appLog.error("Not able to switch in parent frame in Lightning so cannot click on remove link of contact "+SmokeContact2EmailId);
							sa.assertTrue(false, "Not able to switch in parent frame in Lightning so cannot click on remove link of contact "+SmokeContact2EmailId);
						}
					}else {
						switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 30));
					}
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view.");
					if(click(driver, fp.getcontactaccessremoveLink(Workspace.FundraisingWorkspace, EnableDisable.Enable,SmokeContact2EmailId, 10),SmokeContact2EmailId+" contact remove link", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on contact "+SmokeContact2EmailId+" remove link");
						if(click(driver, fp.getContactAccessCancelBtn(Workspace.FundraisingWorkspace, 10), "cancel button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on contact access cancel button ");
						}else {
							appLog.error("Not able to click on contact access cancel button so cannot close contact access pop up");
							sa.assertTrue(false, "Not able to click on contact access cancel button so cannot close contact access pop up");
						}
					}else {
						appLog.error("Not able to click on contact "+SmokeContact1EmailId+" remove link so cannot remove contact form contact access pop up");
					}
				}else {
					appLog.error("Contact "+SmokeContact2FirstName+" "+SmokeContact2LastName+" is not invited successfuly");
					sa.assertTrue(false, "Contact "+SmokeContact2FirstName+" "+SmokeContact2LastName+" is not invited successfuly");
				}
				switchToDefaultContent(driver);
				if(fp.inviteContact(environment, mode,null, SmokeContact1EmailId,shrdfolder, FolderType.Shared,"download","Yes", "Yes",null, Workspace.FundraisingWorkspace, SmokeContact1EmailId)) {
					appLog.info("Contact "+SmokeContact1FirstName+" "+SmokeContact1LastName+" is invited successfuly from "+shrdfolder);
				}else {
					appLog.error("Contact "+SmokeContact1FirstName+" "+SmokeContact1LastName+" is not invited from "+shrdfolder);
					sa.assertTrue(false, "Contact "+SmokeContact1FirstName+" "+SmokeContact1LastName+" is not invited from "+shrdfolder);
				}
				if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					if(switchToFrame(driver, 10, fp.getNIMTabParentFrame_Lightning())) {
						
					}else {
						appLog.error("Not able to switch in parent frame in Lightning so cannot click on manage email");
						sa.assertTrue(false, "Not able to switch in parent frame in Lightning so cannot click on manage email");
						exit("Not able to switch in parent frame in Lightning so cannot click on manage email");
					}
				}else {
					switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 30));
				}
				if (click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 60), "Manage emails icon",action.SCROLLANDBOOLEAN)) {
					if(selectVisibleTextFromDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60), "manage emails drop down list", "All Folders")) {
						appLog.info("select all folders from drop down list");
					}else {
						appLog.error("Not able to all folders from drop down list");
						sa.assertTrue(false, "Not able to all folders from drop down list");
					}
					WebElement ele=  FindElement(driver, "//a[text()='"+SmokeContact1FirstName+" "+SmokeContact1LastName+"']", "contact name link", action.BOOLEAN, 10);
					if(click(driver, ele, "contact name link", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on contact name "+SmokeContact1FirstName+" "+SmokeContact1LastName);
						String parent = switchOnWindow(driver);
						if(parent!=null) {
							ele=FindElement(driver, "//*[contains(text(),'"+SmokeContact1FirstName+" "+SmokeContact1LastName+"')]", "contatc name ", action.BOOLEAN, 30);
							if(ele!=null) {
								appLog.info("Contact page is open ");
								driver.close();
								driver.switchTo().window(parent);
							}else {
								appLog.error("Contact page is not open ");
								sa.assertTrue(false, "Contact page is not open ");
								driver.close();
								driver.switchTo().window(parent);
							}
						}else {
							appLog.error("No new window is open ");
							sa.assertTrue(false, "No new window is open ");
						}

					}else {
						appLog.error("Not able to click on contact name "+SmokeContact1FirstName+" "+SmokeContact1LastName+"  so cannot check contact page");
						sa.assertTrue(false, "Not able to click on contact name "+SmokeContact1FirstName+" "+SmokeContact1LastName+"  so cannot check contact page");
					}
					switchToDefaultContent(driver);
					
					if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						if(switchToFrame(driver, 30, fp.getNIMTabParentFrame_Lightning())) {
							
						}else {
							appLog.error("Not able to switch in parent frame in Lightning so cannot click on manage email");
							sa.assertTrue(false, "Not able to switch in parent frame in Lightning so cannot click on manage email");
							exit("Not able to switch in parent frame in Lightning so cannot click on manage email");
						}
					}else {
						switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 30));
					}
					List<WebElement> lst=  FindElements(driver, "//div[@id='manageemailgrid_ME']//a[text()='"+SmokeInstitution1+"']","account name list");
					if(click(driver, lst.get(0), "account name link", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on account name of contact "+SmokeContact1FirstName+" "+SmokeContact1LastName);
						String parent = switchOnWindow(driver);
						if(parent!=null) {
							ele=FindElement(driver, "//*[contains(text(),'"+SmokeInstitution1+"')]", "contatc name ", action.BOOLEAN, 30);
							if(ele!=null) {
								appLog.info("account page is open ");
								driver.close();
								driver.switchTo().window(parent);
							}else {
								appLog.error("account page is not open ");
								sa.assertTrue(false, "account page is not open ");
								driver.close();
								driver.switchTo().window(parent);
							}
						}else {
							appLog.error("No new window is open ");
							sa.assertTrue(false, "No new window is open ");
						}

					}else {
						appLog.error("Not able to click on account name of contact "+SmokeContact1FirstName+" "+SmokeContact1LastName+"  so cannot check account page");
						sa.assertTrue(false, "Not able to click on account name of contact "+SmokeContact1FirstName+" "+SmokeContact1LastName+"  so cannot check account page");
					}
					String parentid=null;
					switchToDefaultContent(driver);
					if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						if(switchToFrame(driver, 30, fp.getNIMTabParentFrame_Lightning())) {
							
						}else {
							appLog.error("Not able to switch in parent frame in Lightning so cannot click on manage email");
							sa.assertTrue(false, "Not able to switch in parent frame in Lightning so cannot click on manage email");
							exit("Not able to switch in parent frame in Lightning so cannot click on manage email");
						}
					}else {
						switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 30));
					}
					if (click(driver, fp.getManageEmailInvitationEmailTemplateEditPreviewTextList().get(0), "Edit ",action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on template edit link ");
						if (fp.getManageEmailEditNotRegisteredClickHereLink(20) != null) {
							click(driver, fp.getManageEmailEditNotRegisteredClickHereLink(30),
									"Not Registered Click here Link", action.SCROLLANDTHROWEXCEPTION);
							parentid = switchOnWindow(driver);
							sa.assertTrue(getURL(driver, 60).contains("SiteRegisteration"),
									"Registration Page is not open after clicking on Resgister Click Here Link.");
							driver.close();
							driver.switchTo().window(parentid);
							if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
								if(switchToFrame(driver, 30, fp.getNIMTabParentFrame_Lightning())) {
									
								}else {
									appLog.error("Not able to switch in parent frame in Lightning so cannot click on manage email");
									sa.assertTrue(false, "Not able to switch in parent frame in Lightning so cannot click on manage email");
									exit("Not able to switch in parent frame in Lightning so cannot click on manage email");
								}
							}else {
								switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 30));
							}
						} else {
							appLog.info(
									"Not Registered Click Here Link is not clickable on Manage Email Invitation Edit Pop Up.");
							sa.assertTrue(false,
									"Not Registered Click Here Link is not clickable on Manage Email Invitation Edit Pop Up.");
							driver.close();
							driver.switchTo().window(parentid);
							if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
								if(switchToFrame(driver, 30, fp.getNIMTabParentFrame_Lightning())) {
									
								}else {
									appLog.error("Not able to switch in parent frame in Lightning so cannot click on manage email");
									sa.assertTrue(false, "Not able to switch in parent frame in Lightning so cannot click on manage email");
									exit("Not able to switch in parent frame in Lightning so cannot click on manage email");
								}
							}else {
								switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 30));
							}
						}
						if (fp.getManageEmailEditRegisteredClickHereLink(30) != null) {
							click(driver, fp.getManageEmailEditRegisteredClickHereLink(30),
									"Not Registered Click here Link", action.SCROLLANDTHROWEXCEPTION);
							parentid = switchOnWindow(driver);
							sa.assertTrue(getURL(driver, 60).contains("IP_login"),
									"Registration Page is not open after clicking on Resgister Click Here Link.");
							driver.close();
							driver.switchTo().window(parentid);
							if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
								if(switchToFrame(driver, 30, fp.getNIMTabParentFrame_Lightning())) {
									
								}else {
									appLog.error("Not able to switch in parent frame in Lightning so cannot click on manage email");
									sa.assertTrue(false, "Not able to switch in parent frame in Lightning so cannot click on manage email");
									exit("Not able to switch in parent frame in Lightning so cannot click on manage email");
								}
							}else {
								switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 30));
							}
						} else {
							appLog.info(
									"Registered Click Here Link is not clickable on Manage Email Invitation Edit Pop Up.");
							sa.assertTrue(false,
									"Registered Click Here Link is not clickable on Manage Email Invitation Edit Pop Up.");
						}
						if(click(driver, fp.getManageEmailInvitationTextBoxCancelButton(10), "cancel button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on cancel button and edit template pop up is closed");

						}else {
							appLog.error("Not able to click on cancel buton so cannot close edit template pop up");
							sa.assertTrue(false, "Not able to click on cancel buton so cannot close edit template pop up");
						}

					}else {
						appLog.error("Not able to click on edit link so cannot check template in edit mode");
						sa.assertTrue(false, "Not able to click on edit link so cannot check template in edit mode");
					}
					if (click(driver, fp.getManageEmailInvitationEmailTemplateEditPreviewTextList().get(1), "Edit ",action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on template preview link ");

						if (fp.getManageEmailPreviewNotRegisteredClickHereLink(20) != null) {
							if (click(driver, fp.getManageEmailPreviewNotRegisteredClickHereLink(30),
									"Not Registered Click here Link", action.SCROLLANDBOOLEAN)) {
								parentid = switchOnWindow(driver);
								sa.assertTrue(getURL(driver, 60).contains("SiteRegisteration"),
										"Registration Page is not open after clicking on Resgister Click Here Link.");
								driver.close();
								driver.switchTo().window(parentid);
								if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
									if(switchToFrame(driver, 30, fp.getNIMTabParentFrame_Lightning())) {
										
									}else {
										appLog.error("Not able to switch in parent frame in Lightning so cannot click on manage email");
										sa.assertTrue(false, "Not able to switch in parent frame in Lightning so cannot click on manage email");
										exit("Not able to switch in parent frame in Lightning so cannot click on manage email");
									}
								}else {
									switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 30));
								}
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
								parentid = switchOnWindow(driver);
								sa.assertTrue(getURL(driver, 60).contains("IP_login"),
										"Registration Page is not open after clicking on Resgister Click Here Link.");
								driver.close();
								driver.switchTo().window(parentid);
								if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
									if(switchToFrame(driver, 30, fp.getNIMTabParentFrame_Lightning())) {
										
									}else {
										appLog.error("Not able to switch in parent frame in Lightning so cannot click on manage email");
										sa.assertTrue(false, "Not able to switch in parent frame in Lightning so cannot click on manage email");
										exit("Not able to switch in parent frame in Lightning so cannot click on manage email");
									}
								}else {
									switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 30));
								}
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
						if (click(driver, fp.getManageEmailPreviewCloseIcon(30), "Close Button",action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on close icon and preview popup is closed");
						} else {
							appLog.info("Not able to click on close button of preview popup");
							sa.assertTrue(false, "Not able to click on close button of preview popup");
						}
					}else {
						appLog.error("Not able to click on preview link so cannot check template in preview mode");
						sa.assertTrue(false, "Not able to click on preview link so cannot check template in preview mode");
					}
					if (click(driver, fp.getManageEmailCustomRadioButton(60), "Custom template radio button",action.SCROLLANDBOOLEAN)) {
						if(click(driver, fp.getManageEmailCustomCloseIcon(10), "custom cross icon", action.BOOLEAN)) {
							appLog.info("clicked on custom email close icon");

						}else {
							appLog.error("Not able to click on close icon so cannot close custom email popup");
							sa.assertTrue(false, "Not able to click on close icon so cannot close custom email popup");
						}

					}else {
						appLog.error("Not able to click on custom radio button so cannot check custom template popup");
						sa.assertTrue(false, "Not able to click on custom radio button so cannot check custom template popup");
					}

					if (click(driver, fp.getManageEmailCustomPreviewLink(30), "Custom Email Preview Link",
							action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getManageEmailCustomPreviewCloseIcon(20), "Custom Email Id Close icon",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on cross icon ");
						} else {
							appLog.info("Not able to click on close icon");
							sa.assertTrue(false, "Not able to click on close icon");
						}
					}else {
						appLog.info("Not able to click on preview link");
						sa.assertTrue(false, "Not able to click on preview link");
					}
					if (fp.verifyManageEmailGrid(SmokeContact1FirstName + " " + SmokeContact1LastName,
							SmokeContact1EmailId, SmokeInstitution1, "Last Invite Date")) {
						appLog.info("Last invitation date for the contact: " + SmokeContact1FirstName+ " " + SmokeContact1LastName+" is verified ");
					} else {
						appLog.info("Last invitation date for the contact: " + SmokeContact1FirstName+ " " + SmokeContact1LastName+" is verified ");
						sa.assertTrue(false, "Last invitation date for the contact: " + SmokeContact1FirstName+ " " + SmokeContact1LastName+" is verified ");
					}
					if (click(driver, fp.getManageEmailCancelBtn(30), "manage email cancel button", action.SCROLLANDBOOLEAN)) {
						appLog.error("click on cancel button");
					}else {
						appLog.error("Not able to click on cancel button so cannot close manage emails popup");
						sa.assertTrue(false, "Not able to click on cancel button so cannot close manage emails popup");
					}
				} else {
					appLog.info("Not able to click on manage emails icon");
					sa.assertTrue(false, "Not able to click on manage emails icon");
				}
				
			}else {
				appLog.error(" Not able to click on created fund "+SmokeFundName1+" so cannot invite contatcs");
				sa.assertTrue(false, " Not able to click on created fund "+SmokeFundName1+" so cannot invite contatcs");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contacts");
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contacts");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" })
	@Test 
	public void Smoketc007_activateManageApprovalWatermarkingSettingAndUpdateProfilesInfo(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		String watermarkinglabel=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.Watermarking);
		lp.CRMLogin(SmokeCRMUser1Email, SmokePassword);
		if (bp.clickOnTab(environment,mode,TabName.NIMTab)) {
			if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				if(switchToFrame(driver, 30, np.getNIMTabParentFrame_Lightning())) {
					
				}else {
					appLog.error("Not able to switch in parent frame so cannot build FR Workspace");
					exit("Not able to switch in parent frame so cannot build FR Workspace");
				}
			}
			switchToFrame(driver, 30, np.getFrame(PageName.NavatarInvestorManager, 30));
			if (np.clickOnSideMenusTab(sideMenu.ManageApprovals)) {
				if(np.activateManageApprovalsSettings(SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName+","+SmokeCRMUser2FirstName+" "+SmokeCRMUser2LastName).isEmpty()) {
					appLog.info("manage approvals settings is activated");
				}else {
					appLog.error("manage approvals setting is not activated ");
					sa.assertTrue(false, "manage approvals setting is not activated ");
				}
			}else {
				appLog.error("Not able to click on manage approvals tab so cannot activate manage approvals settings");
				sa.assertTrue(false, "Not able to click on manage approvals tab so cannot activate manage approvals settings");
			}
			if(np.clickOnSideMenusTab(sideMenu.Watermarking)) {
				if(np.activateWatermarking2(watermarkinglabel).isEmpty()) {
					appLog.info("WaterMarking is activate Successfully");
				}else {
					appLog.error("WaterMarking setting is not activated");
					sa.assertTrue(false, "WaterMarking setting is not activated");
				}
			}else {
				appLog.error("Not able to click on watermarking side menu so cannot activate watermarking settings");
				sa.assertTrue(false, "Not able to click on watermarking side menu so cannot activate watermarking settings");
			}
			if (np.clickOnSideMenusTab(sideMenu.Profiles)) {
				if (click(driver, np.getmyprofileediticon(60), "Edit icon", action.SCROLLANDBOOLEAN)){
					if(sendKeys(driver, np.getMyProfilePhone(30), SmokeMyProfilePhone, "Phone", action.SCROLLANDBOOLEAN)){
						appLog.info("pass value in phone text box : "+SmokeMyProfilePhone);
					}else {
						appLog.error("Not able to pass value in phone text box  "+SmokeMyProfilePhone);
						sa.assertTrue(false, "Not able to pass value in phone text box  "+SmokeMyProfilePhone);
					}
					if(sendKeys(driver, np.getMyProfileTitle(30), SmokeMyProfileTitle, "Title", action.SCROLLANDBOOLEAN)){
						appLog.info("pass value in title text box : "+SmokeMyProfileTitle);
					}else {
						appLog.error("Not able to pass value title text box  "+SmokeMyProfileTitle);
						sa.assertTrue(false, "Not able to pass value title text box  "+SmokeMyProfileTitle);
					}
					if(click(driver, np.getMyProfileSaveButton(30), "Save Button", action.SCROLLANDBOOLEAN)){
						appLog.info("clicked on my profile save button");
						ThreadSleep(10000);
						
					}else {
						appLog.error("Not able to click on save button so cannot save my profile data");
						sa.assertTrue(false, "Not able to click on save button so cannot save my profile data");
					}
					
				}else {
					appLog.error("Not able to click on edit icon so cannot update my profile data");
					sa.assertTrue(false, "Not able to click on edit icon so cannot update my profile data");
				}
				
			}else {
				appLog.error("Not able to click on my profile tab so cannot update data");
				sa.assertTrue(false, "Not able to click on my profile tab so cannot update data");
			}
			
			if (np.clickOnSideMenusTab(sideMenu.MyFirmProfile)) {
				if (click(driver, np.getmyprofileediticon(10), "Edit icon", action.SCROLLANDBOOLEAN)) {
					if(sendKeys(driver, np.getMyFirmProfilePhone(10), SmokePhoneMyFirm, "Firm phone", action.SCROLLANDBOOLEAN)){
						appLog.info("pass value in phone text box : "+SmokePhoneMyFirm);
					}else {
						appLog.error("Not able to pass value in phone text box : "+SmokePhoneMyFirm);
					}
					if(sendKeys(driver, np.getmyfirmemail(30),SmokeEmailMyFirm, "Firm Email", action.SCROLLANDBOOLEAN)){
						appLog.info("pass value in email text box : "+SmokeEmailMyFirm);
					}else {
						appLog.error("Not able to pass value in email text box : "+SmokeEmailMyFirm);
						sa.assertTrue(false, "Not able to pass value in email text box : "+SmokeEmailMyFirm);
					}
					if(click(driver, np.getMyFirmProfilesaveBtn(30),"my Firm Save button", action.SCROLLANDBOOLEAN)){
						appLog.info("Successfully clicked on Save button.");
						ThreadSleep(10000);
					}else {
						appLog.error("Not able to save my firm profile data");
						sa.assertTrue(false, "Not able to save my firm profile data");
					}
				}else {
					appLog.error("Not able to click on edit icon so cannot update my firm profile data");
					sa.assertTrue(false, "Not able to click on edit icon so cannot update my firm profile data");
				}
				
			}else {
				appLog.error("Not able to click on my firm profile tab so cannot update data");
				sa.assertTrue(false, "Not able to click on my firm profile tab so cannot update data");
			}
			
		}else {
			appLog.error("Not able to click on nim tab so cannot activate manage approvals, watermarking settings and update profile, firm profile data");
			sa.assertTrue(false, "Not able to click on nim tab so cannot activate manage approvals, watermarking settings and update profile, firm profile data");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	

	@Parameters({ "environment", "mode" })
	@Test
	public void Smoketc008_addRenameAndDeleteFolderFromManageFolder(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String[] standrdFolder=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath).split("<break>");
		String shrdFolder=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		lp.CRMLogin(SmokeCRMUser1Email, SmokePassword);
		if (lp.clickOnTab(environment,mode,TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(environment,mode,SmokeFundName1)) {
				if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					if(switchToFrame(driver, 30, fp.getNIMTabParentFrame_Lightning())) {
						
					}else {
						appLog.error("Not able to switch in parent frame so cannot build FR Workspace");
						exit("Not able to switch in parent frame so cannot build FR Workspace");
					}
				}else {
					switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				}
				
				scrollDownThroughWebelement(driver,fp.getInvestorWorkSpaceSection(30) , "investor workspace section");
				if(click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 30), "Manage folder icon", action.BOOLEAN)){
					if(fp.createFolderStructure(standrdFolder[0], FolderType.Common, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
						appLog.info(standrdFolder[0]+ " folder structure is created.");
					} else {
						appLog.error(standrdFolder[0]+ " folder structure is not created.");
						sa.assertTrue(false,standrdFolder[0]+ " folder structure is not created.");
					}
					String id=null;
					id=fp.getCreatedFolderId(standrdFolder[1], PageName.ManageFolderPopUp, 20);
					if(id!=null) {
						if(fp.clickOnRenameFolderButton(id)) {
							if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,20),standrdFolder[2],standrdFolder[1]+" parent rename folder text box", action.BOOLEAN)) {
								if(click(driver,fp.getParentRenameFolderSaveButton(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20) ,standrdFolder[1]+"rename sub folder save button", action.BOOLEAN)) {
									appLog.info("clicked on parent rename folder save button");
									ThreadSleep(2000);
								
								}else {
									appLog.error("Not able to click on folder "+standrdFolder[1]+" save button so cannot Update Folder Name");
									sa.assertTrue(false, "Not able to click on folder "+standrdFolder[1]+" save button so cannot Update Folder Name");
								}
							}else {
								appLog.error("Not able to pass value in "+standrdFolder[1]+" rename folder text box so cannot Update folder Name");
								sa.assertTrue(false, "Not able to pass value in "+standrdFolder[1]+" rename folder text box so cannot Update folder Name");
							}
							
						}else {
							appLog.error("Not able to click on "+standrdFolder[1]+" rename icon so cannot update folder Name");
							sa.assertTrue(false, "Not able to click on "+standrdFolder[1]+" rename icon so cannot update folder Name");
						}
					}else {
						appLog.error(standrdFolder[1]+" folder is not available in manage folder so cannot Update folder Name");
						sa.assertTrue(false, standrdFolder[1]+" folder is not available in manage folder so cannot Update folder Name");
					}
					id=null;
					id=fp.getCreatedFolderId(shrdFolder, PageName.ManageFolderPopUp, 20);
					if(id!=null) {
						if(fp.clickOnDeleteFolderButton(id)) {
							
							if(click(driver, fp.getFolderDeleteYesBtn(Workspace.FundraisingWorkspace,PageName.ManageFolderPopUp, 10), "folder delete yes Button", action.BOOLEAN)) {
								appLog.info("clicked yes button");
							}else {
								appLog.error("No able to click on yes button so cannot delete folder "+shrdFolder);
								sa.assertTrue(false, "No able to click on yes button so cannot delete folder "+shrdFolder);
							}
						}else {
							appLog.error("Not able to click on folder: "+shrdFolder+" delete icon so cannot check delete folder functionality");
							sa.assertTrue(false, "Not able to click on folder: "+shrdFolder+" delete icon so cannot check delete folder functionality");
						}
					}else {
						appLog.error(shrdFolder+" is not available in the manage folder structure so cannot click on folder "+shrdFolder+" delete icon");
						sa.assertTrue(false, shrdFolder+" is not available in the manage folder structure so cannot click on folder "+shrdFolder+" delete icon");
					}
					if(click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 20), "close button", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on manage Folder close button");
					}else {
						appLog.error("Not able to click on manage folder close button so cannot close manage folder pop up");
						sa.assertTrue(false, "Not able to click on manage folder close button so cannot close manage folder pop up");
					}
				}else {
					appLog.error("Not able to click on Manage folder icon so cannot delete Common and shared folders ");
					sa.assertTrue(false, "Not able to click on Manage folder icon so cannot delete Common and shared folders ");
				}
			}else {
				appLog.error("could not find "+SmokeFundName1);
				sa.assertTrue(false, "could not find fund "+SmokeFundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab");
			sa.assertTrue(false, "Not able to click on fund tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	
	@Parameters({ "environment", "mode" })
	@Test 
	public void Smoketc009_addRemoveRenameInvestorFromManageInvestor(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		String errorMsg=FundsPageErrorMessage.filterPleaseSelectAFieldErroresage;
		lp.CRMLogin(SmokeCRMUser1Email, SmokePassword);
		if (lp.clickOnTab(environment,mode,TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(environment,mode,SmokeFundName1)) {
				if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					if(switchToFrame(driver, 30, fp.getNIMTabParentFrame_Lightning())) {
						
					}else {
						appLog.error("Not able to switch in parent frame so cannot build FR Workspace");
						exit("Not able to switch in parent frame so cannot build FR Workspace");
					}
				}else {
					switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				}
				scrollDownThroughWebelement(driver,fp.getInvestorWorkSpaceSection(30) , "investor workspace section");
				if (click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60), "Manage Investor icon",action.SCROLLANDBOOLEAN)) {
						ThreadSleep(5000);
						if (click(driver,fp.getManageInvestorFilterApplyButton(Workspace.FundraisingWorkspace, 60),"Apply buton", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on filter apply button");
							if (bp.verifyErrorMessageOnPage(errorMsg,fp.getManageInvestorFilterfieldSelectErrorMessage(Workspace.FundraisingWorkspace).get(0),"Please select a field error Message in filter")) {
								appLog.info("Please select a field Error Message is verified at filter");
							} else {
								sa.assertTrue(false,"Please select a field error message is not verified at filter.Expected:"+ errorMsg + " Actual"+ getText(driver,fp.getManageInvestorFilterfieldSelectErrorMessage(Workspace.FundraisingWorkspace).get(0),"Please select a field error Message in filter",action.BOOLEAN));
							}
						}else {
							appLog.error("Not able to click on filter apply button so cannot check error message "+errorMsg);
							sa.assertTrue(false, "Not able to click on filter apply button so cannot check error message "+errorMsg);
						}
						if (click(driver,fp.getManageInvestorFilterClearButton(Workspace.FundraisingWorkspace, 60),"Clear button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on filter clear button and error message is not displaying ");
							
						}else {
							appLog.error("Not able to click on filter clear button so cannot check clear button functionality");
							sa.assertTrue(false, "Not able to click on filter clear button so cannot check clear button functionality");
						}
					
						if(click(driver, fp.getManageInvestorFilterAddRowLink(Workspace.FundraisingWorkspace, 60), "add row link", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on add rows link ");
							if(click(driver, fp.getManageInvestorFilterRemoveRowIcon(Workspace.FundraisingWorkspace).get(0), "delete added row cross icon", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on delete row cross icon");
							}else {
								appLog.error("Not able to click on delete row cross icon so cannot delete added row");
								sa.assertTrue(false, "Not able to click on delete row cross icon so cannot delete added row");
							}
						}else {
							appLog.error("Not able to click on add row link so cannot add more rows in filter investor");
							sa.assertTrue(false, "Not able to click on add row link so cannot add more rows in filter investor");
						}
						
						if(fp.selectInstitutionOrLP(SmokeInstitution2, Workspace.FundraisingWorkspace,20)) {
							appLog.info("Investor add successfully : "+SmokeInstitution2);
							
							if(click(driver, fp.getManageInvestorAddedPopupCloseButton(Workspace.FundraisingWorkspace, 20), "manage investor close button", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on manage investor added close button");
								ThreadSleep(3000);
								if(fp.selectInstitutionOrLP(SmokeInstitution2, Workspace.FundraisingWorkspace,20)) {
									appLog.info("Investor removed successfully "+SmokeInstitution2);
									ThreadSleep(3000);
									if(click(driver, fp.getManageInvestorDeletedPopupCloseButton(Workspace.FundraisingWorkspace, 20), "manage investor close button", action.SCROLLANDBOOLEAN)) {
										appLog.info("clicked on manage investor remove close button");
										ThreadSleep(3000);
										
									}else {
										appLog.error("Not able to clicked on manage investor remove close button");
										sa.assertTrue(false, "Not able to clicked on manage investor remove close button");
									}
									
								}else {
									appLog.error("Not able to remove investor : "+SmokeInstitution2);
									sa.assertTrue(false, "Not able to remove investor : "+SmokeInstitution2);
								}
								
								
							}else {
								appLog.error("Not able to clicked on manage investor close button");
								sa.assertTrue(false, "Not able to clicked on manage investor close button");
							}
						}else {
							appLog.error("Not able to select investor : "+SmokeInstitution2);
							sa.assertTrue(false, "Not able to select investor : "+SmokeInstitution2);
						}
						
						if (fp.clickOnRenameManageTargetInstitution(SmokeInstitution1)) {
							appLog.info("clicked on Rename icon for institution" + SmokeInstitution1);
							ThreadSleep(3000);
							if (sendKeys(driver,fp.getManageInvestorRenamePopupTextBox(Workspace.FundraisingWorkspace, 60),SmokeInstitution1+"FR", "Text Box ", action.SCROLLANDBOOLEAN)) {
								appLog.info("passed value in rename manage investor text box:  "+SmokeInstitution1+"FR");
								ExcelUtils.writeData(smokeExcelPath, SmokeInstitution1+"FR", "Institutions", excelLabel.Variable_Name,"SmokeIns1", excelLabel.UpdateInstitution_NameFormManageInvestor);
								if (click(driver,fp.getManageInvestorRenamePopupApplyButton(Workspace.FundraisingWorkspace, 60),"Apply button", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on apply button ");
								}else {
									appLog.error("Not able to click on apply button so cannot update manage investor name "+SmokeInstitution1);
									sa.assertTrue(false, "Not able to click on apply button so cannot update manage investor name "+SmokeInstitution1);
								}
							}else {
								appLog.error("Not able to pass value in rename text box so cannot update institution name "+SmokeInstitution1);
								sa.assertTrue(false, "Not able to pass value in rename text box so cannot update institution name "+SmokeInstitution1);
							}
							
							
						} else {
							appLog.info("Rename icon is not visible for institution" + SmokeInstitution1+" so cannot update name ");
							sa.assertTrue(false, "Rename icon is not visible for institution" + SmokeInstitution1+" so cannot update name ");
						}
						
						if(click(driver, fp.getManageInvestorDoneButton(Workspace.FundraisingWorkspace, 20), "manage investor done button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on manage investor done button");
						}else {
							appLog.error("Not able to click on manage investor done button");
							sa.assertTrue(false, "Not able to click on manage investor done button");
						}

					
				}else {
					appLog.error("Not able to click on manage investor so cannot check manage investor functionality and update manage investor name ");
					sa.assertTrue(false, "Not able to click on manage investor so cannot check manage investor functionality and update manage investor name ");
				}
				
			}else {
				appLog.error("could not find "+SmokeFundName1);
				sa.assertTrue(false, "could not find fund "+SmokeFundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab");
			sa.assertTrue(false, "Not able to click on fund tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	

	@Parameters({ "environment", "mode" })
	@Test 
	public void Smoketc010_uploadDocumentCRMSide(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String standrdFolder=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shrdFolder=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String commonpath=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		lp.CRMLogin(SmokeCRMUser1Email,SmokePassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(SmokeFundName1)) {
				String common_docpath="UploadFiles\\SmokeUploadFile\\Common";
				if(fp.uploadFileBulk(SmokeCRMUser1Email, smokeExcelPath, commonpath, null, common_docpath, UploadFileActions.SelectAll, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
					appLog.info("Successfully uploaded file to "+commonpath);
					appLog.info("File is upload successfullly");
					
				} else {
					appLog.error("Not able to upload file in "+commonpath);
					sa.assertTrue(false,"Not able to upload file in "+commonpath);
				}
				
				String shrd_docpath="UploadFiles\\SmokeUploadFile\\Shared";
				if(fp.uploadFileBulk(SmokeCRMUser1Email, smokeExcelPath, shrdFolder, null, shrd_docpath, UploadFileActions.SelectAll, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
					appLog.info("Successfully uploaded file to "+shrdFolder);
					appLog.info("File is upload successfullly");
				
				} else {
					appLog.error("Not able to upload file in "+shrdFolder);
					sa.assertTrue(false,"Not able to upload file in "+shrdFolder);
				}
				
				String std_docpath="UploadFiles\\SmokeUploadFile\\Standard";
				if(fp.uploadFileBulk(SmokeCRMUser1Email, smokeExcelPath, standrdFolder, UpdateSmokeInstitution1, std_docpath, UploadFileActions.SelectAll, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
					appLog.info("Successfully uploaded file to "+standrdFolder);
					appLog.info("File is upload successfullly");
					
					
				} else {
					appLog.error("Not able to upload file in "+standrdFolder);
					sa.assertTrue(false,"Not able to upload file in "+standrdFolder);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+SmokeFundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+SmokeFundName1+" so cannot upload files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	

	@Parameters({ "environment", "mode" })
	@Test 
	public void Smoketc011_importOnlineDocument(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String fileName=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
		String docPath=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.OnlineImportPath);;
		lp.CRMLogin(SmokeCRMUser1Email,SmokePassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(SmokeFundName1)) {
				if(fp.onlineImport(UpdateSmokeInstitution1, null, null,folderpath,docPath,fileName, SmokeBoxUserName, SmokeBoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Standard, PageName.FundsPage, Workspace.FundraisingWorkspace,20)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
				}else {
					appLog.error("file is not imported: "+fileName+" in :"+folderpath);
					sa.assertTrue(false, "file is not imported: "+fileName+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+SmokeFundName1+" so cannot import files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+SmokeFundName1+" so cannot import files in fundraising workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot import files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot import files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	

	@Parameters({ "environment", "mode" })
	@Test
	public void Smoketc012_1_approvePendingDocumentAndCheckUI(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String filesStandard = ExcelUtils.readData(smokeExcelPath,"FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
		String std = ExcelUtils.readData(smokeExcelPath,"FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String parentID=null;
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		lp.CRMLogin(SmokeCRMUser1Email, SmokePassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(SmokeFundName1)) {
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approval icon fundraising workspace", action.SCROLLANDBOOLEAN)) {
					if (fp.clickOnDocumentManageApprovals(ManageApprovalTabs.PendingDocuments, filesStandard.split("<break>")[0], 30, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30))){
						parentID = switchOnWindow(driver);
						if (parentID != null) {
							if (fp.getDownloadLink(60) != null) {
								appLog.info("Download Button is displaying");
							} else {
								appLog.info("Document Download Button is not displaying");
								sa.assertTrue(false, "Document Download Button is not displaying");
							}
							driver.close();
							driver.switchTo().window(parentID);
							switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
						}else {
							appLog.error("No new window is open so cannot check document");
							sa.assertTrue(false, "No new window is open so cannot check document");
						}
					}else {
						appLog.error("Not able to click on file Name "+filesStandard.split("<break>")[0]);
						sa.assertTrue(false, "Not able to click on file Name "+filesStandard.split("<break>")[0]);
					}
					WebElement ele = fp.checkboxForFileInManageApprovals(filesStandard.split("<break>")[0],SmokeFundName1+" > "+UpdateSmokeInstitution1+" > "+std);
					if(ele!=null) {
						if(click(driver, ele, filesStandard.split("<break>")[0]+" check box", action.BOOLEAN)) {
							appLog.info("clicked on check box "+filesStandard.split("<break>")[0]);
							ThreadSleep(2000);
							for(int i=0; i<2; i++) {
								if (click(driver, fp.getdeleteBtnManageApprovals(60), "delete Btn Manage Approvals", action.BOOLEAN)) {
									appLog.info("clicked on delete button");
									if(i==0) {
										if (click(driver, fp.getManageApprovalDelYesorNo(YesNo.No,60), "manage approval del No button", action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on No button");
										}else {
											appLog.error("delete popup No button is not clickable");
											sa.assertTrue(false, "delete popup No button is not clickable");
										}
									}
									if(i==1) {
										if (click(driver, fp.getManageApprovalDelYesorNo(YesNo.Yes,60), "manage approval del yes button", action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on yes button");
											ThreadSleep(2000);
											if (fp.checkboxForFileInManageApprovals(filesStandard.split("<break>")[0])==null) {
												appLog.info("file is deleted successfully from pending document");
											}else {
												appLog.error("file is not deleted from pending document");
												sa.assertTrue(false, "file is not deleted from pending document");
											}
										}else {
											appLog.error("delete popup yes button is not clickable");
											sa.assertTrue(false, "delete popup yes button is not clickable");
										}
									}
								}else {
									appLog.error("delete button is not clickable");
									sa.assertTrue(false, "delete button is not clickable");
								}
							}
						}else {
							appLog.error("Not able to click on file Name check box");
							sa.assertTrue(false, "Not able to click on file Name check box");
						}
					}else {
						appLog.error(filesStandard.split("<break>")[0]+" file is not visible so cannot click on check box");
						sa.assertTrue(false, filesStandard.split("<break>")[0]+" file is not visible so cannot click on check box");
					}
					if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.PendingDocuments, 30), filesStandard.split("<break>")[1], "search box pending tabs", action.BOOLEAN)) {
						appLog.error("passed value in search text box "+filesStandard.split("<break>")[1]);
						ThreadSleep(3000);
						if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.PendingDocuments, 30), "pending docs manage approvals", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Manage Approvals search icon");
							ThreadSleep(2000);
							if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments,filesStandard.split("<break>")[1], SmokeFundName1+" > "+UpdateSmokeInstitution1+" > "+std, "pending", SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName, SmokeFirmName, date)) {
								appLog.info(filesStandard.split("<break>")[1] + " is successfully searched ");
								ThreadSleep(2000);
								if(click(driver, fp.getClearIconOnManageApprovalsSearchBox(ManageApprovalTabs.PendingDocuments, 20), "clear icon", action.BOOLEAN)) {
									appLog.info("clicked on search text box ");
								}else {
									appLog.error("Not able to click on search text box clear icon so cannot cleared searched document");
									sa.assertTrue(false, "Not able to click on search text box clear icon so cannot cleared searched document");
								}
							}
							else {
							appLog.error(filesStandard.split("<break>")[1] + "could not be verified after search");
							sa.assertTrue(false, filesStandard.split("<break>")[1] + "could not be verified after search");
							}
							
						}else {
							appLog.error("Not able to click on manage approvals search icon so cannot search document "+filesStandard.split("<break>")[1]);
							sa.assertTrue(false, "Not able to click on manage approvals search icon so cannot search document "+filesStandard.split("<break>")[1]);
						}
						
					}else {
						appLog.error("Not able to pass value in search text box "+filesStandard.split("<break>")[1]+" so cannot search file in manage approvals");
						sa.assertTrue(false, "Not able to pass value in search text box "+filesStandard.split("<break>")[1]+" so cannot search file in manage approvals");
					}
					WebElement alldocCheckBox=fp.getManageApprovalsAllDocumentSelectCheckBox(10);
					if(alldocCheckBox!=null) {
						if(click(driver, alldocCheckBox," header check box", action.BOOLEAN)) {
							appLog.info("clicked on header check box ");
							ThreadSleep(2000);
							for(int i=0; i<2; i++) {
								if (click(driver, fp.getApproveBtnManageApprovals(60), "approve Btn Manage Approvals", action.BOOLEAN)) {
									appLog.info("clicked on a button");
									if(i==0) {
										if (click(driver, fp.getManageApprovalApproveYesOrNo(YesNo.No, 10), "manage approval no button", action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on no button");
										}else {
											appLog.error("approve popup No button is not clickable");
											sa.assertTrue(false, "approve popup No button is not clickable");
										}
									}
									if(i==1) {
										if (click(driver, fp.getManageApprovalsConfirmYesBtn(10), "manage approval del yes button", action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on yes button");
											ThreadSleep(2000);
											if(click(driver, fp.getManageApprovalsCloseBtn(20), "manage approvals close button", action.SCROLLANDBOOLEAN)) {
												appLog.info("clicked on close button");
												ThreadSleep(2000);
												if (fp.noDataToDisplay(ManageApprovalTabs.PendingDocuments, 30)!=null) {
													appLog.info("no data to display is successfully displayed");
													
												}else {
													appLog.error("No data to display Error Message is not displaying after approve all document");
													sa.assertTrue(false, "No data to display Error Message is not displaying after approve all document");
												}
											}else {
												appLog.error("Not able to click on manage approvals close button");
												sa.assertTrue(false,"Not able to click on manage approvals close button");
											}
										}else {
											appLog.error("delete popup yes button is not clickable");
											sa.assertTrue(false, "delete popup yes button is not clickable");
										}
									}
								}else {
									appLog.error("delete button is not clickable");
									sa.assertTrue(false, "delete button is not clickable");
								}
							}
						}else {
							appLog.error("Not able to click on header check box");
							sa.assertTrue(false, "Not able to click on header check box");
						}
					}else {
						appLog.error("Not able to click on header check box so cannot select all file");
						sa.assertTrue(false,"Not able to click on header check box so cannot select all file");
					}
					if (click(driver, fp.getApprovedDocsTab(60),"approved docs tab", action.SCROLLANDBOOLEAN)) {
						ThreadSleep(5000);
						
						if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.ApprovedDocuments, 30), filesStandard.split("<break>")[1], "search box pending tabs", action.BOOLEAN)) {
							appLog.error("passed value in search text box "+filesStandard.split("<break>")[1]);
							ThreadSleep(3000);
							if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.ApprovedDocuments, 30), "pending docs manage approvals", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on Manage Approvals search icon");
								ThreadSleep(2000);
								if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.ApprovedDocuments,filesStandard.split("<break>")[1], SmokeFundName1+" > "+UpdateSmokeInstitution1+" > "+std, "Approved", SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName, SmokeFirmName, date)) {
									appLog.info(filesStandard.split("<break>")[1] + " is successfully searched ");
									ThreadSleep(2000);
									if(click(driver, fp.getClearIconOnManageApprovalsSearchBox(ManageApprovalTabs.ApprovedDocuments, 20), "clear icon", action.BOOLEAN)) {
										appLog.info("clicked on search text box ");
									}else {
										appLog.error("Not able to click on search text box clear icon so cannot cleared searched document");
										sa.assertTrue(false, "Not able to click on search text box clear icon so cannot cleared searched document");
									}
								}
								else {
								appLog.error(filesStandard.split("<break>")[1] + "could not be verified after search");
								sa.assertTrue(false, filesStandard.split("<break>")[1] + "could not be verified after search");
								}
								
							}else {
								appLog.error("Not able to click on manage approvals search icon so cannot search document "+filesStandard.split("<break>")[1]);
								sa.assertTrue(false, "Not able to click on manage approvals search icon so cannot search document "+filesStandard.split("<break>")[1]);
							}
							
						}else {
							appLog.error("Not able to pass value in search text box "+filesStandard.split("<break>")[1]+" so cannot search file in manage approvals");
							sa.assertTrue(false, "Not able to pass value in search text box "+filesStandard.split("<break>")[1]+" so cannot search file in manage approvals");
						}
						
						
						
						if (fp.clickOnDocumentManageApprovals(ManageApprovalTabs.ApprovedDocuments, filesStandard.split("<break>")[1], 30, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30))){
							parentID = switchOnWindow(driver);
							if (parentID != null) {
								if (fp.getDownloadLink(60) != null) {
									appLog.info("Download Button is displaying");
								} else {
									appLog.info("Document Download Button is not displaying");
									sa.assertTrue(false, "Document Download Button is not displaying");
								}
								driver.close();
								driver.switchTo().window(parentID);
								switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
							}else {
								appLog.error("No new window is open so cannot check document");
								sa.assertTrue(false, "No new window is open so cannot check document");
							}
						}else {
							appLog.error("Not able to click on file Name "+filesStandard.split("<break>")[0]);
							sa.assertTrue(false, "Not able to click on file Name "+filesStandard.split("<break>")[0]);
						}
						
						
					}else {
						appLog.error("approved docs tab is not clickable on manage approvals popup");
						sa.assertTrue(false, "approved docs tab is not clickable on manage approvals popup");
					}
				}else {
					appLog.error("Not able to click on manage approvals icon so cannot approve pending document");
					sa.assertTrue(false, "Not able to click on manage approvals icon so cannot approve pending document");
				}
			}else {
				appLog.error("Not able to click on created fund so cannot approve pending document");
				sa.assertTrue(false, "Not able to click on created fund so cannot approve pending document");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot approve pending documents");
			sa.assertTrue(false, "Not able to click on fund tab so cannot approve pending documents");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	

	@Parameters({ "environment", "mode" })
	@Test 
	public void Smoketc012_2_updateDocumentCRMSideAndCheckDuplicatePopUp(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String commonpath=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String folderpath=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String fileName=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
		String docPath=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.OnlineImportPath);;
		lp.CRMLogin(SmokeCRMUser1Email,SmokePassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(SmokeFundName1)) {
				
				String common_docpath="UploadFiles\\SmokeUploadFile\\Common";
				
				if(fp.uploadFileBulk(SmokeCRMUser1Email, smokeExcelPath, commonpath, null, common_docpath, UploadFileActions.SelectAll, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
					appLog.info("Successfully uploaded file to "+commonpath);
					appLog.info("File is upload successfullly");
					
				} else {
					appLog.error("Not able to upload file in "+commonpath);
					sa.assertTrue(false,"Not able to upload file in "+commonpath);
				}
				
				if(fp.uploadFileBulk(SmokeCRMUser1Email, smokeExcelPath, commonpath, null, common_docpath, UploadFileActions.SelectAll, UploadFileActions.Update, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
					appLog.info("Successfully uploaded file to "+commonpath);
					appLog.info("File is upload successfullly");
					
				} else {
					appLog.error("Not able to upload file in "+commonpath);
					sa.assertTrue(false,"Not able to upload file in "+commonpath);
				}
				
				if(fp.onlineImport(UpdateSmokeInstitution1, null, null,folderpath,docPath,fileName, SmokeBoxUserName, SmokeBoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Standard, PageName.FundsPage, Workspace.FundraisingWorkspace,20)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
				}else {
					appLog.error("file is not imported: "+fileName+" in :"+folderpath);
					sa.assertTrue(false, "file is not imported: "+fileName+" in :"+folderpath);
				}
				
				if(fp.onlineImport(UpdateSmokeInstitution1, null, null,folderpath,docPath,fileName, SmokeBoxUserName, SmokeBoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPDATE, FolderType.Standard, PageName.FundsPage, Workspace.FundraisingWorkspace,20)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
				}else {
					appLog.error("file is not imported: "+fileName+" in :"+folderpath);
					sa.assertTrue(false, "file is not imported: "+fileName+" in :"+folderpath);
				}
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				if (click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approval icon fundraising workspace", action.SCROLLANDBOOLEAN)) {
					if(selectVisibleTextFromDropDown(driver, fp.getManageAppPendingDropdown(60), "drop down list", "All Pending Documents")) {
					WebElement alldocCheckBox=fp.getManageApprovalsAllDocumentSelectCheckBox(10);
					if(alldocCheckBox!=null) {
						if(click(driver, alldocCheckBox," header check box", action.BOOLEAN)) {
							appLog.info("clicked on header check box ");
							ThreadSleep(2000);
							if (click(driver, fp.getApproveBtnManageApprovals(60), "approve Btn Manage Approvals", action.BOOLEAN)) {
								appLog.info("clicked on a button");
								ThreadSleep(3000);
								if (click(driver, fp.getManageApprovalsConfirmYesBtn(10), "manage approval del yes button", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on yes button");
									ThreadSleep(3000);
									if(click(driver, fp.getManageApprovalsUpdateAllDocument(20), "update all document", action.SCROLLANDBOOLEAN)) {
										appLog.info("clicked on update all button");
										ThreadSleep(3000);
										if(click(driver, fp.getManageApprovalsApprovedUpdatedDocumentCloseBtn(30), "close button", action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on close button");
										}else {
											appLog.error("Not able to click on manage approvals close button");
											sa.assertTrue(false, "Not able to click on manage approvals close button");
											
										}
									}else {
										appLog.error("Not able to click on manage approvals update all button");
										sa.assertTrue(false, "Not able to click on manage approvals update all button");
									}
								}else {
									appLog.error("delete popup yes button is not clickable");
									sa.assertTrue(false, "delete popup yes button is not clickable");
								}
								
							}else {
								appLog.error("delete button is not clickable");
								sa.assertTrue(false, "delete button is not clickable");
							}
						}else {
							appLog.error("Not able to click on header check box");
							sa.assertTrue(false, "Not able to click on header check box");
						}
					}else {
						appLog.error("Not able to find document check box so cannot approve pending document and check duplicate pop up on manage approvals pop up");
						sa.assertTrue(false, "Not able to find document check box so cannot approve pending document and check duplicate pop up on manage approvals pop up");
					}
				}else {
						appLog.error("Not able to select All Pending Documents from drop down list so cannot approve document");
						sa.assertTrue(false, "Not able to select All Pending Documents from drop down list so cannot approve document");
					}
					
				}else {
					appLog.error("Not able to click on manage approvals icon");
					sa.assertTrue(false, "Not able to click on manage approvals icon");
				}
			}else {
				appLog.error("Not able to click on created Fund: "+SmokeFundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+SmokeFundName1+" so cannot upload files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	
	
	

	@Parameters({ "environment", "mode" })
	@Test 
	public void Smoketc013_registerInvestorAndCheckFolder(String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		String[] Stdpath = ExcelUtils.readData(smokeExcelPath,"FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath).split("<break>");
		String[] Shrdpath = ExcelUtils.readData(smokeExcelPath,"FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath).split("<break>");
		String[] CommonPath = ExcelUtils.readData(smokeExcelPath,"FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath).split("<break>");
		String[] stdFile = ExcelUtils.readData(smokeExcelPath,"FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard).split("<break>");
		String docpath="UploadFiles\\SmokeUploadFile\\Investor Side";
		if (ExcelUtils.readData(smokeExcelPath,"Contact", excelLabel.Variable_Name,"SmokeC1", excelLabel.Registered).equalsIgnoreCase("Not Registered")) {
			try {
				investorRegLink = new EmailLib().getInvestorRegLink("InvitationMail",SmokeGmailUserName,SmokeGmailPassword,SmokeCRMUser1Email,SmokeContact1EmailId);
				appLog.info("Investor Registration Link: " + investorRegLink);
			} catch (InterruptedException e) {
				appLog.info("Invitation mail not found.");
				e.printStackTrace();
			}
			if (investorRegLink != null) {
				driver.get(investorRegLink);
				if (bp.targetRegistration(SmokeContact1FirstName,SmokeContact1LastName, SmokeContact1EmailId,SmokeInstitution1,SmokePassword)) {
					appLog.info(
							"Investor is registered successfully: " + SmokeContact1FirstName + " " + SmokeContact1LastName);
					ExcelUtils.writeData(smokeExcelPath,"Registered", "Contact",excelLabel.Variable_Name, "SmokeC1", excelLabel.Registered);
				} else {
					driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
					appLog.info("Investor Target URL through Direct URL..");
					if (bp.targetRegistration(SmokeContact1FirstName, SmokeContact1LastName, SmokeContact1EmailId,
							SmokeInstitution1, SmokePassword)) {
						appLog.info("Investor is registered successfully: " + SmokeContact1FirstName + " "+ SmokeContact1LastName);
						ExcelUtils.writeData(smokeExcelPath,"Registered", "Contact",excelLabel.Variable_Name, "SmokeC1", excelLabel.Registered);
					} else {
						appLog.error("Investor is not Registered through Invited Link: " + SmokeContact1FirstName + " "+ SmokeContact1LastName);
						sa.assertTrue(false, "Investor is not Registered through Invited Link: " + SmokeContact1FirstName+ " " + SmokeContact1LastName);
					}
				}
			} else {
				driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
				appLog.info("Register Investor URL through Direct URL..");
				if (bp.targetRegistration(SmokeContact1FirstName, SmokeContact1LastName, SmokeContact1EmailId,SmokeInstitution1,SmokePassword)) {
					appLog.info("Investor is registered successfully: " + SmokeContact1FirstName + " "+ SmokeContact1LastName);
					ExcelUtils.writeData(smokeExcelPath,"Registered", "Contact",excelLabel.Variable_Name, "SmokeC1", excelLabel.Registered);
				} else {
					appLog.error("Investor is not Registered through Invited Link: " + SmokeContact1FirstName + " "+ SmokeContact1LastName);
					sa.assertTrue(false, "Investor is not Registered through Invited Link: " + SmokeContact1FirstName+ " " + SmokeContact1LastName);
				}
			}
			if(ivp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
				for (int i = 0; i < CommonPath.length; i++) {
					if(fp.verifyFolderPathdummy(CommonPath[i], null, null, null, PageName.PotentialInvestmentPage, null, 30)) {
						appLog.info("folder structure is verified "+CommonPath[i]);
						
					}else {
						appLog.error("Folder is not available  "+CommonPath[i]+" so cannot verify it ");
						sa.assertTrue(false, "Folder is not available  "+CommonPath[i]+" so cannot verify it ");
					}
				}
				
				for (int i = 0; i < Shrdpath.length; i++) {
					if(fp.verifyFolderPathdummy(Shrdpath[i], null, null, null, PageName.PotentialInvestmentPage, null, 30)) {
						appLog.info("folder structure is verified "+Shrdpath[i]);
						
					}else {
						appLog.error("Folder is not available  "+Shrdpath[i]+" so cannot verify it ");
						sa.assertTrue(false, "Folder is not available  "+Shrdpath[i]+" so cannot verify it ");
					}
				}
				
				for (int i = 0; i < Stdpath.length; i++) {
					if(fp.verifyFolderPathdummy(Stdpath[i], UpdateSmokeInstitution1, null, null, PageName.PotentialInvestmentPage, null, 30)) {
						appLog.info("folder structure is verified "+Stdpath[i]);
						
					}else {
						appLog.error("Folder is not available  "+Stdpath[i]+" so cannot verify it ");
						sa.assertTrue(false, "Folder is not available  "+Stdpath[i]+" so cannot verify it ");
					}
				}
				
				if(fp.verifyFolderPathdummy(Stdpath[0], UpdateSmokeInstitution1, null, null, PageName.PotentialInvestmentPage, null, 30)) {
					appLog.info("folder structure is verified "+Stdpath[0]);
					
					if (bp.verifyDownloadFunctionality(PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, stdFile[0], false, false,true)) {
						appLog.info("download button and close button are successfully verified on manage version window");
					}
					else {
						appLog.error("download and close button could not be verified on manage version window");
						sa.assertTrue(false, "download and close button could not be verified on manage version window");
					}
				}else {
					appLog.error("Folder is not available  "+Stdpath[0]+" so cannot verify it ");
					sa.assertTrue(false, "Folder is not available  "+Stdpath[0]+" so cannot verify it ");
				}
				
				if (ivp.uploadUpdateFileInvestorSide(Stdpath[0],stdFile[1], UpdateSmokeInstitution1, null, FolderType.Standard, docpath, "yes", 30,PageName.PotentialInvestmentPage, null, null, WorkSpaceAction.UPLOAD)) {
					appLog.info("file is Upload Successfully in potential investment in folder: "+Stdpath[0]);

				}else{
					appLog.error("Upload Unsuccessful in folder "+Stdpath[0]+" file "+stdFile[1]);
					sa.assertTrue(false, "Upload Unsuccessful in folder "+Stdpath[0]+" file "+stdFile[1]);	
				}
				
				if (ivp.uploadUpdateFileInvestorSide(Stdpath[0],stdFile[1], UpdateSmokeInstitution1, null, FolderType.Standard, docpath, "yes", 30,PageName.PotentialInvestmentPage, null, null, WorkSpaceAction.UPDATE)) {
					appLog.info("file is updated Successfully in potential investment in folder: "+Stdpath[0]);

				}else{
					appLog.error("Update Unsuccessful in folder "+Stdpath[0]+" file "+stdFile[1]);
					sa.assertTrue(false, "Update Unsuccessful in folder "+Stdpath[0]+" file "+stdFile[1]);	
				}
				
				if(click(driver, ivp.getInvestmentDownArrow(30), "invesment down arrow", action.SCROLLANDBOOLEAN)) {
					ThreadSleep(2000);
					if(click(driver, ivp.getFirmNameInfoIcon(20), "firm name info icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on firm name info icon");
						if(getText(driver, ivp.getFirmNameHeaderText(20), "header pop up", action.BOOLEAN).split("-")[1].trim().contains(SmokeFirmName)) {
							appLog.info("firm name is displaying on firm name info pop up header "+SmokeFirmName);
						}else {
							appLog.error("firm name is not displaying on firm name info pop up header "+SmokeFirmName);
							sa.assertTrue(false, "firm name is not displaying on firm name info pop up header "+SmokeFirmName);
						}
						if(click(driver, ivp.getFirmNameInfoPopUpCrossIcon(20), "invesment down arrow", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on cross icon");
						}else {
							appLog.error("Not able to click on cross icon so cannot close pop up");
							sa.assertTrue(false, "Not able to click on cross icon so cannot close pop up");
						}
						
					}else {
						appLog.error("Not able to click on firm name info icon");
						sa.assertTrue(false, "Not able to click on firm name info icon");
					}
					
				}else {
					appLog.error("Not able to click on invesment down arrow so cannot verify info pop up");
					sa.assertTrue(false,"Not able to click on invesment down arrow so cannot verify info pop up");
				}
			}else {
				appLog.error("Not able to click on potential investment tab so cannot verify folder structure");
				sa.assertTrue(false, "Not able to click on potential investment tab so cannot verify folder structure");
			}
			lp.investorLogout();
		} else {
			appLog.info("investor "+SmokeContact1FirstName+" "+SmokeContact1LastName+" is already Registered.");
			sa.assertTrue(false, "investor "+SmokeContact1FirstName+" "+SmokeContact1LastName+" is already Registered.");
		}
		sa.assertAll();
	}
	

	@Parameters({ "environment", "mode" })
	@Test 
	public void Smoketc014_updateprofileAndFirmProfileInvestorSide(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer af = new AllFirmsPageBusinesslayer(driver);
		InvestorProfileBusinessLayer ip = new InvestorProfileBusinessLayer(driver);
		lp.investorLogin(SmokeContact1EmailId,SmokePassword);
		ThreadSleep(2000);
		if (click(driver, af.getProfileLink(60), "profile link on investor firm page", action.SCROLLANDBOOLEAN)) {
			appLog.info("clicked on profile link ");
			ThreadSleep(3000);
			if (click(driver, ip.getEditIcon(60), "edit icon on my profile page", action.SCROLLANDBOOLEAN)) {
				ThreadSleep(2000);
				if (sendKeys(driver, ip.getPhoneTextbox(60), Smokephone, "phone textbox on my profile page", action.SCROLLANDBOOLEAN)) {
					appLog.info("passed value in phone text box "+Smokephone);
					
				}else {
					appLog.error(" Not able to pass value in phone text so cannot update phone number");
					sa.assertTrue(false, " Not able to pass value in phone text so cannot update phone number");
				}
				
				if (sendKeys(driver, ip.getTitleTextbox(60), Smoketitle, "title textbox on my profile page", action.SCROLLANDBOOLEAN)) {
					appLog.info("passed value in title text box "+Smoketitle);
					
				}else {
					appLog.error(" Not able to pass value in title text so cannot update title");
					sa.assertTrue(false, " Not able to pass value in title text so cannot update title");
				}
				
				if (click(driver, ip.getNoEmailRadiobuttonEditMode(60), "no email radio button", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on no email radio button ");
					
				}else {
					appLog.error("Not able to click on no emails radio button ");
					sa.assertTrue(false, "Not able to click on no emails radio button ");
				}
				
				if (click(driver, ip.getSaveButtonMyProfilePage(60), "Save button on my profile page", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on profile save button");
				}else {
					appLog.error("Not able to click on profile save button");
					sa.assertTrue(false, "Not able to click on profile save button");
				}
				ThreadSleep(3000);
				if (click(driver, ip.getChangeProfilePictureLink(60), "change profile picture link", action.SCROLLANDBOOLEAN)) {
					ThreadSleep(2000);
					String parentID=switchOnWindow(driver);
					if (ip.getSubmitButtonChangePicture(10)!=null) {
						driver.close();
						driver.switchTo().window(parentID);
					}else {
						appLog.error("Submit button is not visible");
						sa.assertTrue(false, "Submit button is not visible");
					}
				}else {
					appLog.error("Not able to click on change profile picture link so cannot update change profile picture");
					sa.assertTrue(false, "Not able to click on change profile picture link so cannot update change profile picture");
				}
				if (click(driver,ip.getChangePasswordLink(60) , "change password url on my profile page", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on password link ");
					ThreadSleep(1000);
					if (click(driver, ip.getCancelButtonChangePassword(60), "cancel button on confirm password window", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on cancel button ");
					}else {
						appLog.error("Not able to click on cancel button so cannot close password link ");
						sa.assertTrue(false, "Not able to click on cancel button so cannot close password link ");
					}
					
				}else {
					appLog.error("Not able to click on change password link so cannot verify link");
					sa.assertTrue(false, "Not able to click on change password link so cannot verify link");
				}
				if (click(driver, ip.getMyFirmProfileTab(60), "my firm profile tab", action.BOOLEAN)) {
					ThreadSleep(2000);
					if (click(driver, ip.getEditIcon(60), "Edit icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on edit icon ");
						ThreadSleep(2000);
						if (selectVisibleTextFromDropDown(driver, ip.getIndustryDropdown(60), "Industry",SmokeContact1IndustryDropdown)) {
							appLog.info("select industry "+SmokeContact1IndustryDropdown);
							
						} else {
							appLog.info("Not able to Select Industry dropdown value "+SmokeContact1IndustryDropdown);
							sa.assertTrue(false, "Not able to Select Industry dropdown value "+SmokeContact1IndustryDropdown);
						}
						if (sendKeys(driver, ip.getBillingCityTextbox(60), SmokeContact1BillingCity, "Billing city text box",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("passed value in billing city text box : "+SmokeContact1BillingCity);
						} else {
							appLog.info("Not able to enter Billing city "+SmokeContact1BillingCity);
							sa.assertTrue(false, "Not able to enter Billing city "+SmokeContact1BillingCity);
						}
						if (click(driver, ip.getSaveButtonFirmProfile(60), "Firm profile", action.SCROLLANDBOOLEAN)) {
							ThreadSleep(2000);
							appLog.info("clicked on firm profile save button ");
						} else {
							appLog.info("Not able to click on firm profile save button");
							sa.assertTrue(false, "Not able to click on firm profile save button");
						}
					}else {
						appLog.error("Not able to click on edit icon so cannot update my firm profile ");
						sa.assertTrue(false, "Not able to click on edit icon so cannot update my firm profile ");
					}
					
					if (click(driver, ip.getChangeFirmLogoLink(60), "change firm logo link", action.SCROLLANDBOOLEAN)) {
						ThreadSleep(2000);
						String parentID = switchOnWindow(driver);
						if (ip.getChangeFirmLogoHeading(60)!=null) {
							appLog.info("change firm logo heading is visible");
						}else {
							appLog.error("Change firm logo header is not visible ");
							appLog.error("Change firm logo header is not visible ");
						}
						driver.close();
						driver.switchTo().window(parentID);
					}
					else {
						appLog.error("change firm logo link is not clickable on firm profile page");
						sa.assertTrue(false, "change firm logo link is not clickable on firm profile page");
					}
				}else {
					appLog.error("Not able to click on my firm profile tab so cannot update my firm profile ");
					sa.assertTrue(false, "Not able to click on my firm profile tab so cannot update my firm profile ");
				}
			}else {
				appLog.error("Not able to click on edit icon so cannot update profile");
				sa.assertTrue(false, "Not able to click on edit icon so cannot update profile");
			}
			
		}else {
			appLog.error("profile link is not clickable on all firms page");
			sa.assertTrue(false, "profile link is not clickable on all firms page");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc015_checkLinksOnHomePageAlert(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		HomePageBusineesLayer hp=new HomePageBusineesLayer(driver);
		DisclaimerPageBussinessLayer dp = new DisclaimerPageBussinessLayer(driver);
		String File = ExcelUtils.readData(smokeExcelPath,"FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
		lp.CRMLogin(SmokeCRMUser1Email, SmokePassword);
		ThreadSleep(5000);
		scrollDownThroughWebelement(driver, bp.getFrame(PageName.HomePage, 60), "Home Page alert Frame");
		switchToFrame(driver, 30, bp.getFrame(PageName.HomePage, 60));
		if (bp.clickOnActiivityTypeLinkBasedOnContact("Contact Profile Updated",SmokeContact1FirstName + " " + SmokeContact1LastName)) {
			appLog.info("Clicked on activity type");
			if (click(driver, bp.getGoToContactButton(PageName.HomePage,null,60), "Go to Contact button", action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on Go to Contact button");
				String parentID = switchOnWindow(driver);
				if (parentID != null) {
					WebElement ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
					if (ele != null) {
						if (ele.getText().trim().equalsIgnoreCase(SmokeContact1FirstName + " " + SmokeContact1LastName)) {
							appLog.info(SmokeContact1FirstName + " " + SmokeContact1LastName + " Page is opened");
						} else {
							appLog.info(SmokeContact1FirstName + " " + SmokeContact1LastName + " Page is not opened");
							sa.assertTrue(false,
									SmokeContact1FirstName + " " + SmokeContact1LastName + " Page is not opened");
						}
					} else {
						appLog.info("Not able to find page header");
						sa.assertTrue(false, "Not able to find page header");
					}
					driver.close();
					driver.switchTo().window(parentID);
					switchToFrame(driver, 30, bp.getFrame(PageName.HomePage, 60));
				} else {
					appLog.info("No new window is open");
					sa.assertTrue(false, "No new window is open");
				}
			} else {
				appLog.info("Not able to click on go to Contact button");
				sa.assertTrue(false, "Not able to click on go to contact button");
			}
			if (click(driver, bp.getContactProfileUpdateCloseButton(Workspace.FundraisingWorkspace,PageName.HomePage, 60), "Close Button", action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on close icon");
				if (bp.getContactProfileUpdateCloseButton(Workspace.FundraisingWorkspace,PageName.HomePage, 2) == null) {
					appLog.info("Contact profile updated popup get closed");
				} else {
					appLog.info("Contact Profile updtaed popup does not get closed");
					sa.assertTrue(false, "Contact Profile updtaed popup does not get closed");
				}
			} else {
				appLog.info("Not able to click on close icon");
				sa.assertTrue(false, "Not able to click on close icon");
			}

		} else {
			appLog.info("Not able to click on activity type");
			sa.assertTrue(false, "Not able to click on activity type");
		}
		if (bp.clickOnActiivityTypeLinkBasedOnContact("Firm Profile Updated",SmokeContact1FirstName + " " + SmokeContact1LastName)) {
			appLog.info("Clicked on activity type");
			if (click(driver, bp.getGoToFirmButton(PageName.HomePage,null,60), "Go to firm button", action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on Go to firm button");
				String parentID = switchOnWindow(driver);
				if (parentID != null) {
					WebElement ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
					if (ele != null) {
						if (ele.getText().trim().equalsIgnoreCase(SmokeInstitution1)) {
							appLog.info(SmokeInstitution1 + " Page is opened");
						} else {
							appLog.info(SmokeInstitution1 + " Page is not opened");
							sa.assertTrue(false, SmokeInstitution1 + " Page is not opened");
						}
					} else {
						appLog.info("Not able to find page header");
						sa.assertTrue(false, "Not able to find page header");
					}
					driver.close();
					driver.switchTo().window(parentID);
					switchToFrame(driver, 30, bp.getFrame(PageName.HomePage, 60));
				} else {
					appLog.info("No new window is open");
					sa.assertTrue(false, "No new window is open");
				}
			} else {
				appLog.info("Not able to click on got ot firm button");
				sa.assertTrue(false, "Not able to lcick on go to firm button");
			}
			if (click(driver,bp.getFirmProfileUpdateCloseButton(Workspace.FundraisingWorkspace, PageName.HomePage, 60),"Close Button", action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on close icon");
				if (bp.getFirmProfileUpdateCloseButton(Workspace.FundraisingWorkspace, PageName.HomePage,10) == null) {
					appLog.info("Firm profile updated popup get closed");
				} else {
					appLog.info("Firm Profile updtaed popup does not get closed");
					sa.assertTrue(false, "Firm Profile updtaed popup does not get closed");
				}
			} else {
				appLog.info("Not able to click on close icon");
				sa.assertTrue(false, "Not able to click on close icon");
			}

		} else {
			appLog.info("Not able to click on activity type");
			sa.assertTrue(false, "Not able to click on activity type");
		}
		
		WebElement ele=FindElement(driver, "//a[text()='"+SmokeContact1FirstName+" "+SmokeContact1LastName+"']", "contact name ", action.SCROLLANDBOOLEAN, 10);
		if(ele!=null) {
			if(click(driver, ele, "contact name link", action.BOOLEAN)) {
				String parentID = switchOnWindow(driver);
				if (parentID != null) {
					ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
					if (ele != null) {
						if (ele.getText().trim().equalsIgnoreCase(SmokeContact1FirstName + " " + SmokeContact1LastName)) {
							appLog.info(SmokeContact1FirstName + " " + SmokeContact1LastName + " Page is opened");
						} else {
							appLog.info(SmokeContact1FirstName + " " + SmokeContact1LastName + " Page is not opened");
							sa.assertTrue(false,
									SmokeContact1FirstName + " " + SmokeContact1LastName + " Page is not opened");
						}
					} else {
						appLog.info("Not able to find page header");
						sa.assertTrue(false, "Not able to find page header");
					}
					driver.close();
					driver.switchTo().window(parentID);
					switchToFrame(driver, 30, bp.getFrame(PageName.HomePage, 60));
				} else {
					appLog.info("No new window is open");
					sa.assertTrue(false, "No new window is open");
				}
				
			}else {
				appLog.error("Not able to click on contact name link so cannot verify contact page");
				sa.assertTrue(false, "Not able to click on contact name link so cannot verify contact page");
			}
		}else {
			appLog.error("contact name link is not visible so cannot verify contact page");
			sa.assertTrue(false, "contact name link is not visible so cannot verify contact page");
		}
		ele=FindElement(driver, "//a[text()='"+File+"']", File+" file name link ", action.SCROLLANDBOOLEAN, 10);
		if(ele!=null) {
			if(click(driver, ele, File+" name link", action.BOOLEAN)) {
				ThreadSleep(2000);
				String parentID = switchOnWindow(driver);
				if (parentID != null) {
					if(dp.getDownloadLink(30)!=null) {
						appLog.info("download button is displayed ");
					}else {
						appLog.error("Download button is not displayed ");
						sa.assertTrue(false, "Download button is not displayed ");
					}
					
					driver.close();
					driver.switchTo().window(parentID);
					switchToFrame(driver, 30, bp.getFrame(PageName.HomePage, 60));
				} else {
					appLog.info("No new window is open");
					sa.assertTrue(false, "No new window is open");
				}
				
			}else {
				appLog.error("Not able to click on file name link "+File);
				sa.assertTrue(false, "Not able to click on file name link "+File);
			}
		}else {
			appLog.error(File+" file is not visible so cannot click on it ");
			sa.assertTrue(false, File+" file is not visible so cannot click on it ");
		}
		ele=FindElement(driver, "//a[text()='"+SmokeFundName1+"']", SmokeFundName1+" fund name link", action.SCROLLANDBOOLEAN, 10);
		if(ele!=null) {
			if(click(driver, ele, "fund name link", action.BOOLEAN)) {
				ThreadSleep(2000);
				String parentID = switchOnWindow(driver);
				if (parentID != null) {
					ThreadSleep(5000);
					if(matchTitle(driver, SmokeFundName1,30)) {
						appLog.info("Fund Page is open successfully ");
					}else {
						appLog.error("Fund page is not open");
						sa.assertTrue(false, "Fund page is not open");
					}
					driver.close();
					driver.switchTo().window(parentID);
					switchToFrame(driver, 30, bp.getFrame(PageName.HomePage, 60));
				} else {
					appLog.info("No new window is open");
					sa.assertTrue(false, "No new window is open");
				}
			}else {
				appLog.error("Not able to click on fund name so cannot verify fund page");
				sa.assertTrue(false, "Not able to click on fund name so cannot verify fund page");
			}
		}else {
			appLog.error(SmokeFundName1+" fund name is not visible so cannot click on it ");
			sa.assertTrue(false, SmokeFundName1+" fund name is not visible so cannot click on it ");
		}
		if(selectVisibleTextFromDropDown(driver, hp.getShowDropdownOnHomeAlert(60), "Show dropdown", "All Except Profile Updates")){
			ThreadSleep(2000);
			appLog.info("All Except Profile Update Option is selected ");
			if(getSelectedOptionOfDropDown(driver,  hp.getShowDropdownOnHomeAlert(60), "Show DropDown List", "text").contains("All Except Profile Updates")) {
				appLog.info("All Except Profile Update Option is selected ");
				
			}else {
				appLog.error("All Except Profile Update Option is not selected in home alert grid drop down list ");
				sa.assertTrue(false, "All Except Profile Update Option is not selected in home alert grid drop down list ");
			}
		}else{
			appLog.error("Not able to select show dropdown value");
			sa.assertTrue(false, "Not able to select dropdown value");
		}
		if(selectVisibleTextFromDropDown(driver, hp.getRangeDropdownOnHomeAlert(60), "Range dropdown", "Last 30 Days")){
			ThreadSleep(2000);
			appLog.info("Last 30 Days Option is selected in range drop down list");
			if(getSelectedOptionOfDropDown(driver,  hp.getRangeDropdownOnHomeAlert(60), "Range dropdown", "text").contains("Last 30 Days")) {
				appLog.info("Last 30 Days Option is selected in range drop down list");
				
			}else {
				appLog.error("Last 30 Days Option Option is not selected in home alert grid drop down list ");
				sa.assertTrue(false, "Last 30 Days Option Option is not selected in home alert grid drop down list ");
			}
		}else{
			appLog.info("Not able to select range dropodwn value");
			sa.assertTrue(false, "Not able to select range dropdown value ");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
		
	}
	
	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc016_checkLinksOnFundDetailsPage(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		String standrdFolder=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String[] standrdfile=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard).split("<break>");
		String shrdfile=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileShared);
		String shrdFolder=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		lp.CRMLogin(SmokeCRMUser1Email,SmokePassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(SmokeFundName1)) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),Workspace.FundraisingWorkspace.toString() + " View.");
				/*if(fp.verifyFolderPathdummy(standrdFolder, UpdateSmokeInstitution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 60)){
					if (bp.verifyDownloadFunctionality(PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, standrdfile[0], true, false,false)) {
						appLog.info("download button is successfully verified");
						switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
					}
					else {
						appLog.error("download button is not successfully verified");
						sa.assertTrue(false, "download button is not successfully verified");
						switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
					}
					WebElement ele=FindElement(driver, "//a[@title='"+standrdfile[0]+"']/../../following-sibling::span//a/u[text()='View']", "view link", action.BOOLEAN, 10);
					if(ele!=null) {
						if(click(driver, ele, "view link", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on view link");
							
							if(selectVisibleTextFromDropDown(driver, fp.getDocumentStatisticsRangeDropDownList(30), "Range dropdown", "Last 30 Days")){
								ThreadSleep(2000);
								appLog.info("Last 30 Days Option is selected in range drop down list");
								if(getSelectedOptionOfDropDown(driver,  fp.getDocumentStatisticsRangeDropDownList(30), "Range dropdown", "text").contains("Last 30 Days")) {
									appLog.info("Last 30 Days Option is verified");

								}else {
									appLog.error("Last 30 Days Option Option is not selected in view statistics drop down list ");
									sa.assertTrue(false, "Last 30 Days Option Option is not selected in view statistics drop down list ");
								}
							}else{
								appLog.info("Not able to select range dropodwn value");
								sa.assertTrue(false, "Not able to select range dropdown value ");
							}
					
					
						
							if(selectVisibleTextFromDropDown(driver, fp.getDocumentStatisticsViewDropDownList(30), "Show dropdown", "Document Downloads")){
								ThreadSleep(2000);
								appLog.info("Document Downloads Option is selected ");
								if(getSelectedOptionOfDropDown(driver, fp.getDocumentStatisticsViewDropDownList(30), "Show DropDown List", "text").contains("Document Downloads")) {
									appLog.info("Document Downloads Option is verified");
									
								}else {
									appLog.error("Document Downloads Option is not selected in view statistics drop down list ");
									sa.assertTrue(false, "Document Downloads Option is not selected in view statistics drop down list ");
								}
							}else{
								appLog.error("Not able to select show dropdown value");
								sa.assertTrue(false, "Not able to select dropdown value");
							}
							
							
							
							if(click(driver, fp.getDocumentStatisticsPopUpCloseBtn(10), "close button", action.BOOLEAN)) {
								appLog.info("clicked on document statistics close button");
							}else {
								appLog.error("Not able to click on document statistics close button so cannot close pop up");
								sa.assertTrue(false, "Not able to click on document statistics close button so cannot close pop up");
							}
						}else {
							appLog.error("Not able to click on view link so cannot verify pop up");
							sa.assertTrue(false, "Not able to click on view link so cannot verify pop up");
						}
					}else {
						appLog.error("statistics view link is not visible so cannot click on it ");
						sa.assertTrue(false, "statistics view link is not visible so cannot click on it ");
					}
					ele=FindElement(driver, "//a[@title='"+standrdfile[0]+"']/../../following-sibling::span//a[@title='"+SmokeContact1FirstName+" "+SmokeContact1LastName+"']", "view link", action.BOOLEAN, 10);
					if(ele!=null) {
						if(click(driver, ele, "contact name link", action.BOOLEAN)) {
							String parentID = switchOnWindow(driver);
							if (parentID != null) {
								ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
								if (ele != null) {
									if (ele.getText().trim().equalsIgnoreCase(SmokeContact1FirstName + " " + SmokeContact1LastName)) {
										appLog.info(SmokeContact1FirstName + " " + SmokeContact1LastName + " Page is opened");
									} else {
										appLog.info(SmokeContact1FirstName + " " + SmokeContact1LastName + " Page is not opened");
										sa.assertTrue(false,
												SmokeContact1FirstName + " " + SmokeContact1LastName + " Page is not opened");
									}
								} else {
									appLog.info("Not able to find page header");
									sa.assertTrue(false, "Not able to find page header");
								}
								driver.close();
								driver.switchTo().window(parentID);
								switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
							} else {
								appLog.info("No new window is open");
								sa.assertTrue(false, "No new window is open");
							}
							
						}else {
							appLog.error("Not able to click on contact name link so cannot verify contact page");
							sa.assertTrue(false, "Not able to click on contact name link so cannot verify contact page");
						}
					}else {
						appLog.error("contact name link is not visible so cannot verify contact page");
						sa.assertTrue(false, "contact name link is not visible so cannot verify contact page");
					}
					ele=FindElement(driver, "//a[@title='"+standrdfile[0]+"']/../../following-sibling::span//a[@title='"+SmokeInstitution1+"']", "view link", action.BOOLEAN, 10);
					if(ele!=null) {
						if (click(driver,ele, "firm name link", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Go to firm button");
							String parentID = switchOnWindow(driver);
							if (parentID != null) {
								ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
								if (ele != null) {
									if (ele.getText().trim().equalsIgnoreCase(SmokeInstitution1)) {
										appLog.info(SmokeInstitution1 + " Page is opened");
									} else {
										appLog.info(SmokeInstitution1 + " Page is not opened");
										sa.assertTrue(false, SmokeInstitution1 + " Page is not opened");
									}
								} else {
									appLog.info("Not able to find page header");
									sa.assertTrue(false, "Not able to find page header");
								}
								driver.close();
								driver.switchTo().window(parentID);
								switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
							} else {
								appLog.info("No new window is open");
								sa.assertTrue(false, "No new window is open");
							}
						} else {
							appLog.info("Not able to click on got ot firm button");
							sa.assertTrue(false, "Not able to lcick on go to firm button");
						}

					}else {
						appLog.error("institution name is not visible so cannot click on it ");
						sa.assertTrue(false, "institution name is not visible so cannot click on it ");
					}
					if (fp.clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Open, standrdfile[1], Workspace.FundraisingWorkspace, 10, null)) {
						appLog.info("clicked on file "+standrdfile[1]);
						ThreadSleep(2000);
						String parentID = switchOnWindow(driver);
						if (parentID != null) {
							if(fp.getDownloadLink(30)!=null) {
								appLog.info("download button is displayed ");
							}else {
								appLog.error("Download button is not displayed ");
								sa.assertTrue(false, "Download button is not displayed ");
							}
							driver.close();
							driver.switchTo().window(parentID);
							switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
						} else {
							appLog.info("No new window is open");
							sa.assertTrue(false, "No new window is open");
						}
					}else {
						appLog.error("Not able to click on "+standrdfile[1]+" file down arrow open so cannot open file ");
						sa.assertTrue(false, "Not able to click on "+standrdfile[1]+" file down arrow open so cannot open file ");
					}
					
					for(int i=0; i<2; i++) {
						if (fp.clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Delete, standrdfile[1], Workspace.FundraisingWorkspace, 10, null)) {
							appLog.info("clicked on file "+standrdfile[1]+" delete option ");
							ThreadSleep(2000);
							if(i==1) {
								if (click(driver, fp.getDocumentDeleteYesBtn(Workspace.FundraisingWorkspace,60), "Yes button", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on delete pop up yes button");
								}else {
									appLog.error("Not able to click on delete pop up yes button so cannot delete file "+standrdfile[1]);
									sa.assertTrue(false, "Not able to click on delete pop up yes button so cannot delete file "+standrdfile[1]);
								}
							}
							if(i==0) {
								if (click(driver, fp.getDocumentDeleteNoBtn(Workspace.FundraisingWorkspace, 10), "No button", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on delete pop up no button");
								}else {
									appLog.error("Not able to click on delete pop up no button so cannot close pop up");
									sa.assertTrue(false, "Not able to click on delete pop up no button so cannot clsoe pop up");
								}
							}
						}else {
							appLog.error("Not able to click on "+standrdfile[1]+" file down arrow open so cannot delete file ");
							sa.assertTrue(false, "Not able to click on "+standrdfile[1]+" file down arrow open so cannot delete file ");
						}
						
					}
					if (click(driver, fp.getAlertHistoryLink(Workspace.FundraisingWorkspace, PageName.FundsPage, 60),"Alert History Link", action.SCROLLANDBOOLEAN)) {
						switchToDefaultContent(driver);
						switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
						if (bp.clickOnActiivityTypeLinkBasedOnContact("Contact Profile Updated",SmokeContact1FirstName + " " + SmokeContact1LastName)) {
							appLog.info("Clicked on activity type");
							if (click(driver, bp.getGoToContactButton(PageName.FundsPage,Workspace.FundraisingWorkspace,60), "Go to Contact button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on Go to Contact button");
								String parentID = switchOnWindow(driver);
								if (parentID != null) {
									ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
									if (ele != null) {
										if (ele.getText().trim().equalsIgnoreCase(SmokeContact1FirstName + " " + SmokeContact1LastName)) {
											appLog.info(SmokeContact1FirstName + " " + SmokeContact1LastName + " Page is opened");
										} else {
											appLog.info(SmokeContact1FirstName + " " + SmokeContact1LastName + " Page is not opened");
											sa.assertTrue(false,
													SmokeContact1FirstName + " " + SmokeContact1LastName + " Page is not opened");
										}
									} else {
										appLog.info("Not able to find page header");
										sa.assertTrue(false, "Not able to find page header");
									}
									driver.close();
									driver.switchTo().window(parentID);
									switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
								} else {
									appLog.info("No new window is open");
									sa.assertTrue(false, "No new window is open");
								}
							} else {
								appLog.info("Not able to click on go to Contact button");
								sa.assertTrue(false, "Not able to click on go to contact button");
							}
							if (click(driver, bp.getContactProfileUpdateCloseButton(Workspace.FundraisingWorkspace,PageName.FundsPage, 60), "Close Button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on close icon");
								if (bp.getContactProfileUpdateCloseButton(Workspace.FundraisingWorkspace,PageName.FundsPage, 2) == null) {
									appLog.info("Contact profile updated popup get closed");
								} else {
									appLog.info("Contact Profile updtaed popup does not get closed");
									sa.assertTrue(false, "Contact Profile updtaed popup does not get closed");
								}
							} else {
								appLog.info("Not able to click on close icon");
								sa.assertTrue(false, "Not able to click on close icon");
							}

						} else {
							appLog.info("Not able to click on activity type");
							sa.assertTrue(false, "Not able to click on activity type");
						}
						if (bp.clickOnActiivityTypeLinkBasedOnContact("Firm Profile Updated",SmokeContact1FirstName + " " + SmokeContact1LastName)) {
							appLog.info("Clicked on activity type");
							if (click(driver, bp.getGoToFirmButton(PageName.FundsPage,Workspace.FundraisingWorkspace,60), "Go to firm button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on Go to firm button");
								String parentID = switchOnWindow(driver);
								if (parentID != null) {
									ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
									if (ele != null) {
										if (ele.getText().trim().equalsIgnoreCase(SmokeInstitution1)) {
											appLog.info(SmokeInstitution1 + " Page is opened");
										} else {
											appLog.info(SmokeInstitution1 + " Page is not opened");
											sa.assertTrue(false, SmokeInstitution1 + " Page is not opened");
										}
									} else {
										appLog.info("Not able to find page header");
										sa.assertTrue(false, "Not able to find page header");
									}
									driver.close();
									driver.switchTo().window(parentID);
									switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
								} else {
									appLog.info("No new window is open");
									sa.assertTrue(false, "No new window is open");
								}
							} else {
								appLog.info("Not able to click on got ot firm button");
								sa.assertTrue(false, "Not able to lcick on go to firm button");
							}
							if (click(driver,bp.getFirmProfileUpdateCloseButton(Workspace.FundraisingWorkspace, PageName.FundsPage, 60),"Close Button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on close icon");
								if (bp.getFirmProfileUpdateCloseButton(Workspace.FundraisingWorkspace, PageName.FundsPage,10) == null) {
									appLog.info("Firm profile updated popup get closed");
								} else {
									appLog.info("Firm Profile updtaed popup does not get closed");
									sa.assertTrue(false, "Firm Profile updtaed popup does not get closed");
								}
							} else {
								appLog.info("Not able to click on close icon");
								sa.assertTrue(false, "Not able to click on close icon");
							}

						} else {
							appLog.info("Not able to click on activity type");
							sa.assertTrue(false, "Not able to click on activity type");
						}
						ele=FindElement(driver, "//a[text()='"+SmokeContact1FirstName+" "+SmokeContact1LastName+"']", "contact name ", action.SCROLLANDBOOLEAN, 10);
						if(ele!=null) {
							if(click(driver, ele, "contact name link", action.BOOLEAN)) {
								String parentID = switchOnWindow(driver);
								if (parentID != null) {
									ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
									if (ele != null) {
										if (ele.getText().trim().equalsIgnoreCase(SmokeContact1FirstName + " " + SmokeContact1LastName)) {
											appLog.info(SmokeContact1FirstName + " " + SmokeContact1LastName + " Page is opened");
										} else {
											appLog.info(SmokeContact1FirstName + " " + SmokeContact1LastName + " Page is not opened");
											sa.assertTrue(false,
													SmokeContact1FirstName + " " + SmokeContact1LastName + " Page is not opened");
										}
									} else {
										appLog.info("Not able to find page header");
										sa.assertTrue(false, "Not able to find page header");
									}
									driver.close();
									driver.switchTo().window(parentID);
									switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
								} else {
									appLog.info("No new window is open");
									sa.assertTrue(false, "No new window is open");
								}
								
							}else {
								appLog.error("Not able to click on contact name link so cannot verify contact page");
								sa.assertTrue(false, "Not able to click on contact name link so cannot verify contact page");
							}
						}else {
							appLog.error("contact name link is not visible so cannot verify contact page");
							sa.assertTrue(false, "contact name link is not visible so cannot verify contact page");
						}
						ele=FindElement(driver, "//a[text()='"+standrdfile[0]+"']", standrdfile[0]+" file name link ", action.SCROLLANDBOOLEAN, 10);
						if(ele!=null) {
							if(click(driver, ele, standrdfile[0]+" name link", action.BOOLEAN)) {
								ThreadSleep(2000);
								String parentID = switchOnWindow(driver);
								if (parentID != null) {
									if(fp.getDownloadLink(30)!=null) {
										appLog.info("download button is displayed ");
									}else {
										appLog.error("Download button is not displayed ");
										sa.assertTrue(false, "Download button is not displayed ");
									}
									
									driver.close();
									driver.switchTo().window(parentID);
									switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
								} else {
									appLog.info("No new window is open");
									sa.assertTrue(false, "No new window is open");
								}
								
							}else {
								appLog.error("Not able to click on file name link "+standrdfile[0]);
								sa.assertTrue(false, "Not able to click on file name link "+standrdfile[0]);
							}
						}else {
							appLog.error(standrdfile[0]+" file is not visible so cannot click on it ");
							sa.assertTrue(false, standrdfile[0]+" file is not visible so cannot click on it ");
						}
				
						if(selectVisibleTextFromDropDown(driver, fp.getAlertHistoryRangeDropDownList(Workspace.FundraisingWorkspace, 30), "Range dropdown", "Last 30 Days")){
							ThreadSleep(2000);
							appLog.info("Last 30 Days Option is selected in range drop down list");
							if(getSelectedOptionOfDropDown(driver,  fp.getAlertHistoryRangeDropDownList(Workspace.FundraisingWorkspace, 30), "Range dropdown", "text").contains("Last 30 Days")) {
								appLog.info("Last 30 Days Option is verified");

							}else {
								appLog.error("Last 30 Days Option Option is not selected in fund alert grid drop down list ");
								sa.assertTrue(false, "Last 30 Days Option Option is not selected in fund alert grid drop down list ");
							}
						}else{
							appLog.info("Not able to select range dropodwn value");
							sa.assertTrue(false, "Not able to select range dropdown value ");
						}
				
				
					
						if(selectVisibleTextFromDropDown(driver, fp.getAlertHistoryShowDropDownList(Workspace.FundraisingWorkspace,60), "Show dropdown", "All Except Profile Updates")){
							ThreadSleep(2000);
							appLog.info("All Except Profile Update Option is selected ");
							if(getSelectedOptionOfDropDown(driver, fp.getAlertHistoryShowDropDownList(Workspace.FundraisingWorkspace,60), "Show DropDown List", "text").contains("All Except Profile Updates")) {
								appLog.info("All Except Profile Update Option is verified");
								
							}else {
								appLog.error("All Except Profile Update Option is not selected in home alert grid drop down list ");
								sa.assertTrue(false, "All Except Profile Update Option is not selected in home alert grid drop down list ");
							}
						}else{
							appLog.error("Not able to select show dropdown value");
							sa.assertTrue(false, "Not able to select dropdown value");
						}
						
						
						
						if(click(driver, bp.getAlertHistoryCrossIcon(Workspace.FundraisingWorkspace, 60), "Alert history cross icon", action.SCROLLANDBOOLEAN)){
							appLog.info("Clicked on alert history aross icon");
						}else{
							appLog.error("Not able to click on alert history cross icon");
							sa.assertTrue(false, "Not able to click on alert history cross icon");
						}	
					}else {
						appLog.error("Not able to click on alter history link so cannot check pop up");
						sa.assertTrue(false, "Not able to click on alter history link so cannot check pop up");
					}
					if (click(driver, fp.getInvestmentInfo(Workspace.FundraisingWorkspace), "Investment info",action.SCROLLANDBOOLEAN)) {
						for(int i=0; i<2; i++) {
							if (click(driver, fp.getInvestmentInfoEdit(60), "investment info edit button",action.SCROLLANDBOOLEAN)) {
								if(i==0) {
									if (click(driver, fp.getInvestmentInfoPopUpCacnelBtnInEditMode(40), "Investment Info cancel button",action.SCROLLANDBOOLEAN)) {
										appLog.info("clicked on cancel button");
									} else {
										appLog.error("cancel button is not clicked");
										sa.assertTrue(false,"Cancel button is not clicked");
									}
								}
								if(i==1) {
									if (sendKeys(driver, fp.getInvestmentInfoPhoneTxtbox(60), "123456","phone number text box",action.BOOLEAN)) {
										if (click(driver, fp.getInvestmentInfoSaveBtn(40), "Investment Info save button",action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on save button");
										} else {
											appLog.error("Save button is not clicked so ,Updated fund name is not able to save.");
											sa.assertTrue(false,"Save button is not clicked so ,Updated fund name is not able to save.");
										}
									} else {
										appLog.error("fund name text box is not visible on Investment Info edit mode so, not able to enter value.");
										sa.assertTrue(false,"fund name text box is not visible on Investment Info edit mode so, not able to enter value.");
									}
								}
							} else {
								appLog.error("Investment Info edit button is not clickable so, not able to enter value.");
								sa.assertTrue(false,"Investment Info edit button is not clickable so, not able to enter value.");
							}
						}
						ThreadSleep(2000);
						if (click(driver, fp.getInvestmentInfoCancelButton(40), "Investment Info cancel button",action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on cancel button");
						} else {
							appLog.error("cancel button is not clicked");
							sa.assertTrue(false,"Cancel button is not clicked");
						}
					}else {
						appLog.error("Not able to click on investment update info link so cannot update phone number from investment update info");
						sa.assertTrue(false, "Not able to click on investment update info link so cannot update phone number from investment info");
					}
					
					if (click(driver, fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
						String parentid = switchOnWindow(driver);
						ThreadSleep(5000);
						System.err.println("Parent wind ID: "+parentid);
						String childWindowID = null;
						String childChildWindow = null;
						Set<String> lst = driver.getWindowHandles();
						Iterator<String> I1 = lst.iterator();
						while (I1.hasNext()) {
							String windowID = I1.next();
							if (windowID.equalsIgnoreCase(parentid)) {
								appLog.info("Parent Id is Matched");
							} else {
								childWindowID = windowID;
								appLog.info("Storged child Window Id");
								break;
							}
						}
						if (click(driver, fp.getIPAnalyticsMostViewedDocumentLink(30), "Most View Document Link",action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on most viewed document link ");
							ThreadSleep(1000);
							if (click(driver, fp.getMostviewedDocumentCloseBtn(10),"Document not viewed or download close button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on Most Viewed Document pop up Close button");
							} else {
								appLog.info("Clicked on Most Viewed Document pop up Close button");
								sa.assertTrue(false, "Clicked on CMost Viewed Document pop up Close button");
							}
						} else {
							appLog.info("Not able to click on Most Viewed Document link");
							sa.assertTrue(false, "Not able to click on Most Viewed Document link");
						}
						if (click(driver, fp.getIPAnalyticsMostActiveContactsLink(30), "Most Active Contact Link",action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on most active contact link");
							ThreadSleep(2000);
							if (click(driver, fp.getMostActiveContactCloseBtn(10), "Document not viewed or download close button",action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on Most Active Contact pop up Close button");
							} else {
								appLog.info("Clicked on Most Active Contact pop up Close button");
								sa.assertTrue(false, "Clicked on Most Active Contact pop up Close button");
							}
						} else {
							appLog.info("Not able to click on Most Active Contact link");
							sa.assertTrue(false, "Not able to click on Most Active Contact link");
						}
						List<WebElement> tabs = fp.getIPAnalyticsListofTab();
						String tab1 = tabs.get(1).getText().trim();
						if (click(driver, tabs.get(1), "DR Analytics Tab Link " + tab1, action.BOOLEAN)) {
							List<WebElement> exportLinks = fp.getIPAnalyticsListOfExportText();
							if (click(driver, exportLinks.get(0), "Workspace Documents Report",
									action.BOOLEAN)) {
								appLog.info("Clicked on Workspace Documents Report");
								ThreadSleep(10000);
								appLog.info("Workspace Documents Report File is downloaded");
								System.err.println("URL Of small window: "+getURL(driver, 2)+" "+driver.getWindowHandle());
								Robot rob;
								try {
									rob = new Robot();
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_ENTER);
									rob.keyRelease(KeyEvent.VK_ENTER);
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_CONTROL);
									rob.keyPress(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_CONTROL);
								} catch (AWTException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								// ele.sendKeys(Keys.CONTROL+"j");
								ThreadSleep(10000);
								String abc = driver.getWindowHandle();
								System.err.println("URL Of small window: "+getURL(driver, 2)+" "+abc);
								Set<String> lst1 = driver.getWindowHandles();
								Iterator<String> I2 = lst1.iterator();
								while (I2.hasNext()) {
									String windowID = I2.next();
									if (windowID.equalsIgnoreCase(parentid) || windowID.equalsIgnoreCase(childWindowID)) {
										appLog.info("Parent Id or child Id is Matched");
									} else {
										childChildWindow = windowID;
										appLog.info("Storged  child child Window Id");
										driver.switchTo().window(childChildWindow);
										System.err.println("URL Of Download window: "+getURL(driver, 2)+" "+driver.getWindowHandle());
										break;
									}
								}
								System.out.println(getURL(driver, 30));
								if (childChildWindow == null) {
									try {
										rob = new Robot();
										rob.keyPress(KeyEvent.VK_TAB);
										rob.keyRelease(KeyEvent.VK_TAB);
										rob.keyPress(KeyEvent.VK_ENTER);
										rob.keyRelease(KeyEvent.VK_ENTER);
										ThreadSleep(10000);
										rob.keyPress(KeyEvent.VK_CONTROL);
										rob.keyPress(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_CONTROL);
										parentid = switchOnWindow(driver);
									} catch (AWTException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								ThreadSleep(10000);
								String fileName = fp.getDownloadedFileName();
								if (fileName.equalsIgnoreCase("Fundraising_Documents_" + SmokeFundName1 + "_"
										+ getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv")) {
									appLog.info("file name is successfully matched. " + "Fundraising_Documents_" + SmokeFundName1 + "_"
											+ getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv");
								} else {
									appLog.info("File name is not the matched. Expected: " + "Fundraising_Documents_" + SmokeFundName1
											+ "_" + getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv"
											+ " Actual: " + fileName);
									sa.assertTrue(false,
											"File name is not the matched. Expected: " + "Fundraising_Documents_" + SmokeFundName1
													+ "_" + getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv"
													+ " Actual: " + fileName);
								}
								driver.close();
								driver.switchTo().window(childWindowID);

							} else {
								appLog.error("Not able to click on Workspace Documents Report Link");
								sa.assertTrue(false, "Not able to click on Workspace Documents Report Link");
							}
							exportLinks.clear();
							exportLinks = fp.getIPAnalyticsListOfExportText();
							if (click(driver, exportLinks.get(1), "Contact Permissions Report ", action.BOOLEAN)) {
								appLog.info("Clicked on Contact Permission Export Link");
								ThreadSleep(10000);
								appLog.info("Contact Permissions Report File downloaded");
								Robot rob;
								try {
									rob = new Robot();
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_ENTER);
									rob.keyRelease(KeyEvent.VK_ENTER);
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_CONTROL);
									rob.keyPress(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_CONTROL);
								} catch (AWTException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								// ele.sendKeys(Keys.CONTROL+"j");
								ThreadSleep(10000);
								Set<String> lst1 = driver.getWindowHandles();
								Iterator<String> I2 = lst1.iterator();
								while (I2.hasNext()) {
									String windowID = I2.next();
									if (windowID.equalsIgnoreCase(parentid) || windowID.equalsIgnoreCase(childWindowID)) {
										appLog.info("Parent Id or child Id is Matched");
									} else {
										childChildWindow = windowID;
										appLog.info("Storged  child child Window Id");
										driver.switchTo().window(childChildWindow);
										break;
									}
								}
								if (childChildWindow == null) {
									try {
										rob = new Robot();
										rob.keyPress(KeyEvent.VK_TAB);
										rob.keyRelease(KeyEvent.VK_TAB);
										rob.keyPress(KeyEvent.VK_ENTER);
										rob.keyRelease(KeyEvent.VK_ENTER);
										ThreadSleep(10000);
										rob.keyPress(KeyEvent.VK_CONTROL);
										rob.keyPress(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_CONTROL);
										parentid = switchOnWindow(driver);
									} catch (AWTException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								ThreadSleep(10000);
								String fileName = fp.getDownloadedFileName();
								if (fileName.equalsIgnoreCase("Fundraising_Contact_Permissions_" + SmokeFundName1 + "_"
										+ getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv")) {
									appLog.info("file name is successfully matched. " + "Fundraising_Contact_Permissions_" + SmokeFundName1 + "_"
											+ getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv");
								} else {
									appLog.info("File name is not the matched. Expected: " + "Fundraising_Contact_Permissions_"
											+ SmokeFundName1 + "_" + getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv"
											+ " Actual: " + fileName);
									sa.assertTrue(false,
											"File name is not the matched. Expected: " + "Fundraising_Contact_Permissions_"
													+ SmokeFundName1 + "_" + getDateAccToTimeZone("America/New_York", "MMddyyyy")
													+ ".csv" + " Actual: " + fileName);
								}
								driver.close();
								driver.switchTo().window(childWindowID);

							} else {
								appLog.error("Not able to click on Contact Permission Export Link");
								sa.assertTrue(false, "Not able to click on Contact Permission Export Link");
							}
						} else {
							appLog.error("Not able to click on DR Analytics Exports tab");
							sa.assertTrue(false, "Not able to click on DR Analytics Exports tab");
						}
						
						driver.close();
						driver.switchTo().window(parentid);
						switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
					}else {
						appLog.error("Not able to click on analytics icon so cannot verify links");
						sa.assertTrue(false, "Not able to click on analytics icon so cannot verify links");
					}
				}else{
					appLog.error("Folder path not visible "+standrdFolder+" so cannot click on file link");
					sa.assertTrue(false, "Folder path not visible "+standrdFolder+" so cannot click on file link");
				}
				*/String docPath=System.getProperty("user.dir") + "\\UploadFiles\\SmokeUploadFile\\Shared\\";
				if(fp.updateFile(shrdFolder, shrdfile, UpdateSmokeInstitution1, null, FolderType.Shared,docPath+shrdfile,null, null, ContentGridArrowKeyFunctions.Update,20, PageName.FundsPage,"Yes",null, Workspace.FundraisingWorkspace)) {
					appLog.info("file is updated successfully: "+shrdfile+" in :"+shrdFolder);
						
				
				}else {
					appLog.error("Not able to update file in shared folder "+shrdfile);
					sa.assertTrue(false, "Not able to update file in shared folder "+shrdfile);
				}
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(fp.verifyFolderPathdummy(shrdFolder, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 60)){
					if(fp.clickOnManageVersionOnContentGrid(shrdfile, Workspace.FundraisingWorkspace, 20)) {
						appLog.info("clicked on manage version link");
						
						if (click(driver, fp.getUpdateButtonOnManageVersionPopUp(60), "Update Button", action.BOOLEAN)) {
							appLog.info("clicked on update button ");
							String parentwindow=  switchOnWindow(driver);
							if(parentwindow!=null) {
								appLog.info("Update file window is open ");
								driver.close();
								driver.switchTo().window(parentwindow);
								switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
								
							}else {
								appLog.error("No new window is open ");
								sa.assertTrue(false, "No new window is open ");
							}
							
							WebElement ele=FindElement(driver, "//div[@id='idmangeversion']//a[text()='"+shrdfile+"']", shrdfile+" file name link ", action.SCROLLANDBOOLEAN, 10);
							if(ele!=null) {
								if(click(driver, ele, shrdfile+" name link", action.BOOLEAN)) {
									ThreadSleep(3000);
									String parentID = switchOnWindow(driver);
									if (parentID != null) {
										if(fp.getDownloadLink(30)!=null) {
											appLog.info("download button is displayed ");
										}else {
											appLog.error("Download button is not displayed ");
											sa.assertTrue(false, "Download button is not displayed ");
										}
										
										driver.close();
										driver.switchTo().window(parentID);
										switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
									} else {
										appLog.info("No new window is open");
										sa.assertTrue(false, "No new window is open");
									}
									
								}else {
									appLog.error("Not able to click on file name link "+shrdfile);
									sa.assertTrue(false, "Not able to click on file name link "+shrdfile);
								}
							}else {
								appLog.error(shrdfile+" file is not visible so cannot click on it ");
								sa.assertTrue(false, shrdfile+" file is not visible so cannot click on it ");
							}
							if (!click(driver, fp.getManageVersionPopUpCloseButton(30),"Manage Versions Pop Up close Button", action.BOOLEAN)) {
								appLog.error("Not able to click on Manage version Close button");
								sa.assertTrue(false, "Not able to click on Manage version Close button");
							}else {
								appLog.info("clicked on close button ");
							}
							
						}else {
							appLog.info("Update Button on manage version pop up is not clikable.");
							sa.assertTrue(false, "Update Button on manage version pop up is not available.");
						}
						switchToDefaultContent(driver);
						if(fp.makeCurrentversionDocViaManageVersion(null, null, shrdFolder,shrdfile, shrdfile, Workspace.FundraisingWorkspace,30)) {
							appLog.info("file is successfully make current: "+shrdfile+" in :"+shrdFolder);
							
						}else {
							appLog.error("Not able to make current file "+shrdfile);
							sa.assertTrue(false, "Not able to make current file "+shrdfile);
						}
						if(fp.verifyFolderPathdummy(standrdFolder, UpdateSmokeInstitution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 60)){
							String 	date = getSystemDate("MM/dd/yyyy")+previousOrForwardDate(-1, "MM/dd/yyyy");
							if (bp.enterValueAndClickonSearchBoxContentGrid(PageName.FundsPage, Workspace.FundraisingWorkspace,standrdfile[0], 30)) {
								ThreadSleep(2000);
								SoftAssert saa = bp.verifyContentGridForSearch(driver, UpdateSmokeInstitution1+" > "+standrdFolder, standrdfile[0], date);
								sa.combineAssertions(saa);
							}else {
								appLog.error("Not able to search file : "+standrdfile[0]+" in FR workspace");
								sa.assertTrue(false, "Not able to search file : "+standrdfile[0]+" in FR workspace");
							}
							if (click(driver, bp.getSearchPopCrossIcon(30), "Search Cross Icon", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on search pop up cross icon");
							}else {
								appLog.error("Not able to click search pop up cross icon so cannot close pop up");
								sa.assertTrue(false, "Not able to click search pop up cross icon so cannot close pop up");
							}
							
						}else {
							appLog.error("Not able to click on standard folder so cannot check search functionality");
							sa.assertTrue(false, "Not able to click on standard folder so cannot check search functionality");
						}
						
					}else {
						appLog.error("Not able to click on manage version link so cannot change updated file version");
						sa.assertTrue(false, "Not able to click on manage version link so cannot change updated file version");
					}
				
				}else{
					appLog.error("Folder path not visible "+shrdFolder+" so cannot click on file link");
					sa.assertTrue(false, "Folder path not visible "+shrdFolder+" so cannot click on file link");
				}
			}else {
				appLog.error("Not able to click on created Fund: "+SmokeFundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+SmokeFundName1+" so cannot upload files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" })
	@Test
	public void Smoketc017_checkLinksOnContactDetailsPage(String environment, String mode) {
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		ContactPageBusinessLayer contact = new ContactPageBusinessLayer(driver);
		String standrdFolder=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String[] standrdfile=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard).split("<break>");
		lp.CRMLogin(SmokeCRMUser1Email,SmokePassword);
		if(contact.clickOnTab(TabName.ContactTab)) {
			if(contact.clickOnCreatedContact(SmokeContact1FirstName, SmokeContact1LastName, null)) {
				switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),Workspace.FundraisingWorkspace.toString() + " View.");
				if(fp.verifyFolderPathdummy(standrdFolder, UpdateSmokeInstitution1, null, SmokeFundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 60)){
					if (bp.verifyDownloadFunctionality(PageName.ContactsPage, Workspace.FundraisingWorkspace, standrdfile[0], true, false,false)) {
						appLog.info("download button is successfully verified");
						switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
					}
					else {
						appLog.error("download button is not successfully verified");
						sa.assertTrue(false, "download button is not successfully verified");
						switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
					}
					
					String 	date = getSystemDate("MM/dd/yyyy")+previousOrForwardDate(-1, "MM/dd/yyyy");
					if (bp.enterValueAndClickonSearchBoxContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace,standrdfile[0], 30)) {
						ThreadSleep(2000);
						SoftAssert saa = bp.verifyContentGridForSearch(driver, UpdateSmokeInstitution1+" > "+standrdFolder, standrdfile[0], date);
						sa.combineAssertions(saa);
					}else {
						appLog.error("Not able to search file : "+standrdfile[0]+" in FR workspace");
						sa.assertTrue(false, "Not able to search file : "+standrdfile[0]+" in FR workspace");
					}
					if (click(driver, bp.getSearchPopCrossIcon(30), "Search Cross Icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on search pop up cross icon");
					}else {
						appLog.error("Not able to click search pop up cross icon so cannot close pop up");
						sa.assertTrue(false, "Not able to click search pop up cross icon so cannot close pop up");
					}
					
					
					
					WebElement ele=FindElement(driver, "//a[@title='"+standrdfile[0]+"']/../../following-sibling::span//a[text()='View']", "view link", action.BOOLEAN, 10);
					if(ele!=null) {
						if(click(driver, ele, "view link", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on view link");
							


							
							if(click(driver, fp.getDocumentStatisticsPopUpCloseBtn(10), "close button", action.BOOLEAN)) {
								appLog.info("clicked on document statistics close button");
							}else {
								appLog.error("Not able to click on document statistics close button so cannot close pop up");
								sa.assertTrue(false, "Not able to click on document statistics close button so cannot close pop up");
							}
						}else {
							appLog.error("Not able to click on view link so cannot verify pop up");
							sa.assertTrue(false, "Not able to click on view link so cannot verify pop up");
						}
					}else {
						appLog.error("statistics view link is not visible so cannot click on it ");
						sa.assertTrue(false, "statistics view link is not visible so cannot click on it ");
					}
					ele=FindElement(driver, "//a[@title='"+standrdfile[0]+"']/../../following-sibling::span//a[@title='"+SmokeContact1FirstName+" "+SmokeContact1LastName+"']", "view link", action.BOOLEAN, 10);
					if(ele!=null) {
						if(click(driver, ele, "contact name link", action.BOOLEAN)) {
							String parentID = switchOnWindow(driver);
							if (parentID != null) {
								ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
								if (ele != null) {
									if (ele.getText().trim().equalsIgnoreCase(SmokeContact1FirstName + " " + SmokeContact1LastName)) {
										appLog.info(SmokeContact1FirstName + " " + SmokeContact1LastName + " Page is opened");
									} else {
										appLog.info(SmokeContact1FirstName + " " + SmokeContact1LastName + " Page is not opened");
										sa.assertTrue(false,
												SmokeContact1FirstName + " " + SmokeContact1LastName + " Page is not opened");
									}
								} else {
									appLog.info("Not able to find page header");
									sa.assertTrue(false, "Not able to find page header");
								}
								driver.close();
								driver.switchTo().window(parentID);
								switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
							} else {
								appLog.info("No new window is open");
								sa.assertTrue(false, "No new window is open");
							}
							
						}else {
							appLog.error("Not able to click on contact name link so cannot verify contact page");
							sa.assertTrue(false, "Not able to click on contact name link so cannot verify contact page");
						}
					}else {
						appLog.error("contact name link is not visible so cannot verify contact page");
						sa.assertTrue(false, "contact name link is not visible so cannot verify contact page");
					}
					ele=FindElement(driver, "//a[@title='"+standrdfile[0]+"']/../../following-sibling::span//a[@title='"+SmokeInstitution1+"']", "view link", action.BOOLEAN, 10);
					if(ele!=null) {
						if (click(driver,ele, "firm name link", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Go to firm button");
							String parentID = switchOnWindow(driver);
							if (parentID != null) {
								ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
								if (ele != null) {
									if (ele.getText().trim().equalsIgnoreCase(SmokeInstitution1)) {
										appLog.info(SmokeInstitution1 + " Page is opened");
									} else {
										appLog.info(SmokeInstitution1 + " Page is not opened");
										sa.assertTrue(false, SmokeInstitution1 + " Page is not opened");
									}
								} else {
									appLog.info("Not able to find page header");
									sa.assertTrue(false, "Not able to find page header");
								}
								driver.close();
								driver.switchTo().window(parentID);
								switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
							} else {
								appLog.info("No new window is open");
								sa.assertTrue(false, "No new window is open");
							}
						} else {
							appLog.info("Not able to click on got ot firm button");
							sa.assertTrue(false, "Not able to lcick on go to firm button");
						}

					}else {
						appLog.error("institution name is not visible so cannot click on it ");
						sa.assertTrue(false, "institution name is not visible so cannot click on it ");
					}
					if (fp.clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Open, standrdfile[1], Workspace.FundraisingWorkspace, 10, null)) {
						appLog.info("clicked on file "+standrdfile[1]);
						ThreadSleep(2000);
						String parentID = switchOnWindow(driver);
						if (parentID != null) {
							if(fp.getDownloadLink(30)!=null) {
								appLog.info("download button is displayed ");
							}else {
								appLog.error("Download button is not displayed ");
								sa.assertTrue(false, "Download button is not displayed ");
							}
							driver.close();
							driver.switchTo().window(parentID);
							switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
						} else {
							appLog.info("No new window is open");
							sa.assertTrue(false, "No new window is open");
						}
					}else {
						appLog.error("Not able to click on "+standrdfile[1]+" file down arrow open so cannot open file ");
						sa.assertTrue(false, "Not able to click on "+standrdfile[1]+" file down arrow open so cannot open file ");
					}
					for(int i=0; i<2; i++) {
						if (fp.clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Delete, standrdfile[1], Workspace.FundraisingWorkspace, 10, null)) {
							appLog.info("clicked on file "+standrdfile[1]+" delete option ");
							ThreadSleep(2000);
							if(i==1) {
								if (click(driver, fp.getDocumentDeleteYesBtn(Workspace.FundraisingWorkspace,60), "Yes button", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on delete pop up yes button");
								}else {
									appLog.error("Not able to click on delete pop up yes button so cannot delete file "+standrdfile[1]);
									sa.assertTrue(false, "Not able to click on delete pop up yes button so cannot delete file "+standrdfile[1]);
								}
							}
							if(i==0) {
								if (click(driver, fp.getDocumentDeleteNoBtn(Workspace.FundraisingWorkspace, 10), "No button", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on delete pop up no button");
								}else {
									appLog.error("Not able to click on delete pop up no button so cannot close pop up");
									sa.assertTrue(false, "Not able to click on delete pop up no button so cannot clsoe pop up");
								}
							}
						}else {
							appLog.error("Not able to click on "+standrdfile[1]+" file down arrow open so cannot delete file ");
							sa.assertTrue(false, "Not able to click on "+standrdfile[1]+" file down arrow open so cannot delete file ");
						}
						
					}
					if (click(driver, fp.getAlertHistoryLink(Workspace.FundraisingWorkspace, PageName.ContactsPage, 60),"Alert History Link", action.SCROLLANDBOOLEAN)) {
						switchToDefaultContent(driver);
						switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
						if (bp.clickOnActiivityTypeLinkBasedOnContact("Contact Profile Updated",SmokeContact1FirstName + " " + SmokeContact1LastName)) {
							appLog.info("Clicked on activity type");
							if (click(driver, bp.getGoToContactButton(PageName.ContactsPage,Workspace.FundraisingWorkspace,60), "Go to Contact button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on Go to Contact button");
								String parentID = switchOnWindow(driver);
								if (parentID != null) {
									ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
									if (ele != null) {
										if (ele.getText().trim().equalsIgnoreCase(SmokeContact1FirstName + " " + SmokeContact1LastName)) {
											appLog.info(SmokeContact1FirstName + " " + SmokeContact1LastName + " Page is opened");
										} else {
											appLog.info(SmokeContact1FirstName + " " + SmokeContact1LastName + " Page is not opened");
											sa.assertTrue(false,
													SmokeContact1FirstName + " " + SmokeContact1LastName + " Page is not opened");
										}
									} else {
										appLog.info("Not able to find page header");
										sa.assertTrue(false, "Not able to find page header");
									}
									driver.close();
									driver.switchTo().window(parentID);
									switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
								} else {
									appLog.info("No new window is open");
									sa.assertTrue(false, "No new window is open");
								}
							} else {
								appLog.info("Not able to click on go to Contact button");
								sa.assertTrue(false, "Not able to click on go to contact button");
							}
							if (click(driver, bp.getContactProfileUpdateCloseButton(Workspace.FundraisingWorkspace,PageName.ContactsPage, 60), "Close Button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on close icon");
								if (bp.getContactProfileUpdateCloseButton(Workspace.FundraisingWorkspace,PageName.ContactsPage, 2) == null) {
									appLog.info("Contact profile updated popup get closed");
								} else {
									appLog.info("Contact Profile updtaed popup does not get closed");
									sa.assertTrue(false, "Contact Profile updtaed popup does not get closed");
								}
							} else {
								appLog.info("Not able to click on close icon");
								sa.assertTrue(false, "Not able to click on close icon");
							}

						} else {
							appLog.info("Not able to click on activity type");
							sa.assertTrue(false, "Not able to click on activity type");
						}
						if (bp.clickOnActiivityTypeLinkBasedOnContact("Firm Profile Updated",SmokeContact1FirstName + " " + SmokeContact1LastName)) {
							appLog.info("Clicked on activity type");
							if (click(driver, bp.getGoToFirmButton(PageName.ContactsPage,Workspace.FundraisingWorkspace,60), "Go to firm button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on Go to firm button");
								String parentID = switchOnWindow(driver);
								if (parentID != null) {
									ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
									if (ele != null) {
										if (ele.getText().trim().equalsIgnoreCase(SmokeInstitution1)) {
											appLog.info(SmokeInstitution1 + " Page is opened");
										} else {
											appLog.info(SmokeInstitution1 + " Page is not opened");
											sa.assertTrue(false, SmokeInstitution1 + " Page is not opened");
										}
									} else {
										appLog.info("Not able to find page header");
										sa.assertTrue(false, "Not able to find page header");
									}
									driver.close();
									driver.switchTo().window(parentID);
									switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
								} else {
									appLog.info("No new window is open");
									sa.assertTrue(false, "No new window is open");
								}
							} else {
								appLog.info("Not able to click on got ot firm button");
								sa.assertTrue(false, "Not able to lcick on go to firm button");
							}
							if (click(driver,bp.getFirmProfileUpdateCloseButton(Workspace.FundraisingWorkspace, PageName.ContactsPage, 60),"Close Button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on close icon");
								if (bp.getFirmProfileUpdateCloseButton(Workspace.FundraisingWorkspace, PageName.ContactsPage,10) == null) {
									appLog.info("Firm profile updated popup get closed");
								} else {
									appLog.info("Firm Profile updtaed popup does not get closed");
									sa.assertTrue(false, "Firm Profile updtaed popup does not get closed");
								}
							} else {
								appLog.info("Not able to click on close icon");
								sa.assertTrue(false, "Not able to click on close icon");
							}

						} else {
							appLog.info("Not able to click on activity type");
							sa.assertTrue(false, "Not able to click on activity type");
						}
						ele=FindElement(driver, "//a[text()='"+SmokeContact1FirstName+" "+SmokeContact1LastName+"']", "contact name ", action.SCROLLANDBOOLEAN, 10);
						if(ele!=null) {
							if(click(driver, ele, "contact name link", action.BOOLEAN)) {
								String parentID = switchOnWindow(driver);
								if (parentID != null) {
									ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
									if (ele != null) {
										if (ele.getText().trim().equalsIgnoreCase(SmokeContact1FirstName + " " + SmokeContact1LastName)) {
											appLog.info(SmokeContact1FirstName + " " + SmokeContact1LastName + " Page is opened");
										} else {
											appLog.info(SmokeContact1FirstName + " " + SmokeContact1LastName + " Page is not opened");
											sa.assertTrue(false,
													SmokeContact1FirstName + " " + SmokeContact1LastName + " Page is not opened");
										}
									} else {
										appLog.info("Not able to find page header");
										sa.assertTrue(false, "Not able to find page header");
									}
									driver.close();
									driver.switchTo().window(parentID);
									switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
								} else {
									appLog.info("No new window is open");
									sa.assertTrue(false, "No new window is open");
								}
								
							}else {
								appLog.error("Not able to click on contact name link so cannot verify contact page");
								sa.assertTrue(false, "Not able to click on contact name link so cannot verify contact page");
							}
						}else {
							appLog.error("contact name link is not visible so cannot verify contact page");
							sa.assertTrue(false, "contact name link is not visible so cannot verify contact page");
						}
						ele=FindElement(driver, "//a[text()='"+standrdfile[0]+"']", standrdfile[0]+" file name link ", action.SCROLLANDBOOLEAN, 10);
						if(ele!=null) {
							if(click(driver, ele, standrdfile[0]+" name link", action.BOOLEAN)) {
								ThreadSleep(2000);
								String parentID = switchOnWindow(driver);
								if (parentID != null) {
									if(fp.getDownloadLink(30)!=null) {
										appLog.info("download button is displayed ");
									}else {
										appLog.error("Download button is not displayed ");
										sa.assertTrue(false, "Download button is not displayed ");
									}
									
									driver.close();
									driver.switchTo().window(parentID);
									switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
								} else {
									appLog.info("No new window is open");
									sa.assertTrue(false, "No new window is open");
								}
								
							}else {
								appLog.error("Not able to click on file name link "+standrdfile[0]);
								sa.assertTrue(false, "Not able to click on file name link "+standrdfile[0]);
							}
						}else {
							appLog.error(standrdfile[0]+" file is not visible so cannot click on it ");
							sa.assertTrue(false, standrdfile[0]+" file is not visible so cannot click on it ");
						}
						
						if(selectVisibleTextFromDropDown(driver, contact.getAlertHistoryRangeDropDownList(Workspace.FundraisingWorkspace, 30), "Range dropdown", "Last 30 Days")){
							ThreadSleep(2000);
							appLog.info("Last 30 Days Option is selected in range drop down list");
							if(getSelectedOptionOfDropDown(driver,  contact.getAlertHistoryRangeDropDownList(Workspace.FundraisingWorkspace, 30), "Range dropdown", "text").contains("Last 30 Days")) {
								appLog.info("Last 30 Days Option is verified");

							}else {
								appLog.error("Last 30 Days Option Option is not selected in fund alert grid drop down list ");
								sa.assertTrue(false, "Last 30 Days Option Option is not selected in fund alert grid drop down list ");
							}
						}else{
							appLog.info("Not able to select range dropodwn value");
							sa.assertTrue(false, "Not able to select range dropdown value ");
						}
				
				
					
						if(selectVisibleTextFromDropDown(driver, contact.getAlertHistoryShowDropDownList(Workspace.FundraisingWorkspace,60), "Show dropdown", "All Except Profile Updates")){
							ThreadSleep(2000);
							appLog.info("All Except Profile Update Option is selected ");
							if(getSelectedOptionOfDropDown(driver, contact.getAlertHistoryShowDropDownList(Workspace.FundraisingWorkspace,60), "Show DropDown List", "text").contains("All Except Profile Updates")) {
								appLog.info("All Except Profile Update Option is verified");
								
							}else {
								appLog.error("All Except Profile Update Option is not selected in home alert grid drop down list ");
								sa.assertTrue(false, "All Except Profile Update Option is not selected in home alert grid drop down list ");
							}
						}else{
							appLog.error("Not able to select show dropdown value");
							sa.assertTrue(false, "Not able to select dropdown value");
						}
						
						
						
						
						
						if(click(driver, bp.getAlertHistoryCrossIcon(Workspace.FundraisingWorkspace, 60), "Alert history cross icon", action.SCROLLANDBOOLEAN)){
							appLog.info("Clicked on alert history aross icon");
						}else{
							appLog.error("Not able to click on alert history cross icon");
							sa.assertTrue(false, "Not able to click on alert history cross icon");
						}	
					}else {
						appLog.error("Not able to click on alter history link so cannot check pop up");
						sa.assertTrue(false, "Not able to click on alter history link so cannot check pop up");
					}
				}else {
					appLog.error("Not able to click on folder path "+standrdFolder);
					sa.assertTrue(false, "Not able to click on folder path "+standrdFolder);
				}
			}else {
				appLog.error("Not able to click on created contact "+SmokeContact1FirstName+" "+SmokeContact1LastName);
				sa.assertTrue(false, "Not able to click on created contact "+SmokeContact1FirstName+" "+SmokeContact1LastName);
			}
			
		}else {
			appLog.error("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}
		switchToDefaultContent(driver);
		if(contact.clickOnTab(TabName.ContactTab)) {
			if(contact.clickOnCreatedContact(SmokeContact3FirstName, SmokeContact3LastName, null)) {
				switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),Workspace.FundraisingWorkspace.toString() + " View.");
				
				if(click(driver, contact.getRemoveContactAccessButton(Workspace.FundraisingWorkspace, 60), "Remove contact access button", action.SCROLLANDBOOLEAN)){
					appLog.info("clicked on removed contact access button");
					WebElement ele=FindElement(driver,"//label[text()='"+SmokeFundName1+"']/../..//a[@title='Remove']", "Fund 1 Remove link", action.SCROLLANDBOOLEAN, 60);
					if(ele!=null) {
						if(click(driver, ele, "Remove Link", action.SCROLLANDBOOLEAN)){
							String ParentID=switchOnWindow(driver);
							if(ParentID!=null){
								ThreadSleep(5000);
								switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
								driver.switchTo().window(ParentID);
								refresh(driver);
								switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 30));
								scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising Workspace Section view");
							}else{
								appLog.info("No new window to switch");
								sa.assertTrue(false, "No new window to switch");
							}
						}else{
							appLog.info("Not bale ot click on remove link");
							sa.assertTrue(false, "Not able to click on remove link");
						}

					}else {
						appLog.error("Not able to find remove link so cannot remove contact access");
						sa.assertTrue(false, "Not able to find remove link so cannot remove contact access");
					}
				}else{
					appLog.error("Not able to clcik on remove contact access button");
					sa.assertTrue(false, "Not able to clcik on remove contact access button");
				}
				if (bp.verifyErrorMessageOnPage(ContactPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationFundraisingWorkspace,contact.getErrorMessageAfterAdminAndCRMUserRegistrationFundRaisingWorkspace(60),
						"Error Message after admin Registration on Contact page for investor workspace")) {
					appLog.info("Error Message is verified  on Contact page for Fundraising workspace");
				} else {
					appLog.error("Error Message is not verified on Contact page for Fundraising workspace.Expected:"+ ContactPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationFundraisingWorkspace+ " Actual"+ getText(driver,contact.getErrorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace(60),"Error Message after admin registration on Contact page for investor workspace",action.BOOLEAN));
					sa.assertTrue(false,"Error Message is not verified on Contact page for Fundraising workspace.Expected:"+ ContactPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationFundraisingWorkspace+ " Actual"+ getText(driver,contact.getErrorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace(60),"Error Message after admin registration on Contact page for investor workspace",action.BOOLEAN));
				}
				switchToDefaultContent(driver);
			}else {
				appLog.error("Not able to click on created contact "+SmokeContact3FirstName+" "+SmokeContact3LastName);
				sa.assertTrue(false, "Not able to click on created contact "+SmokeContact3FirstName+" "+SmokeContact3LastName);
			}
		}else {
			appLog.error("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}
		lp.CRMlogout(environment,mode);
		sa.assertAll();
		
	}

	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc018_checkLinkOnInstitutionPage(String environment, String mode) {
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ins = new InstitutionPageBusinessLayer(driver);
		String standrdFolder=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String[] standrdfile=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard).split("<break>");
		lp.CRMLogin(SmokeCRMUser1Email,SmokePassword);
		if(ins.clickOnTab(TabName.InstituitonsTab)) {
			if(ins.clickOnCreatedInstitution(SmokeInstitution1)) {
				switchToFrame(driver, 30, bp.getFrame(PageName.InstitutionsPage, 60));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),Workspace.FundraisingWorkspace.toString() + " View.");
				if(fp.verifyFolderPathdummy(standrdFolder, null, null, SmokeFundName1, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 60)){
					if (bp.verifyDownloadFunctionality(PageName.InstitutionsPage, Workspace.FundraisingWorkspace, standrdfile[0], true, false,false)) {
						appLog.info("download button is successfully verified");
						switchToFrame(driver, 30, bp.getFrame(PageName.InstitutionsPage, 60));
					}
					else {
						appLog.error("download button is not successfully verified");
						sa.assertTrue(false, "download button is not successfully verified");
						switchToFrame(driver, 30, bp.getFrame(PageName.InstitutionsPage, 60));
					}
					String 	date = getSystemDate("MM/dd/yyyy")+previousOrForwardDate(-1, "MM/dd/yyyy");
					if (bp.enterValueAndClickonSearchBoxContentGrid(PageName.ContactsPage, Workspace.FundraisingWorkspace,standrdfile[0], 30)) {
						ThreadSleep(2000);
						SoftAssert saa = bp.verifyContentGridForSearch(driver, UpdateSmokeInstitution1+" > "+standrdFolder, standrdfile[0], date);
						sa.combineAssertions(saa);
					}else {
						appLog.error("Not able to search file : "+standrdfile[0]+" in FR workspace");
						sa.assertTrue(false, "Not able to search file : "+standrdfile[0]+" in FR workspace");
					}
					if (click(driver, bp.getSearchPopCrossIcon(30), "Search Cross Icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on search pop up cross icon");
					}else {
						appLog.error("Not able to click search pop up cross icon so cannot close pop up");
						sa.assertTrue(false, "Not able to click search pop up cross icon so cannot close pop up");
					}
					
					
					WebElement ele=FindElement(driver, "//a[@title='"+standrdfile[0]+"']/../../following-sibling::span//a/u[text()='View']", "view link", action.BOOLEAN, 10);
					if(ele!=null) {
						if(click(driver, ele, "view link", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on view link");
							
							if(selectVisibleTextFromDropDown(driver, fp.getDocumentStatisticsRangeDropDownList(30), "Range dropdown", "Last 30 Days")){
								ThreadSleep(2000);
								appLog.info("Last 30 Days Option is selected in range drop down list");
								if(getSelectedOptionOfDropDown(driver,  fp.getDocumentStatisticsRangeDropDownList(30), "Range dropdown", "text").contains("Last 30 Days")) {
									appLog.info("Last 30 Days Option is verified");

								}else {
									appLog.error("Last 30 Days Option Option is not selected in view statistics drop down list ");
									sa.assertTrue(false, "Last 30 Days Option Option is not selected in view statistics drop down list ");
								}
							}else{
								appLog.info("Not able to select range dropodwn value");
								sa.assertTrue(false, "Not able to select range dropdown value ");
							}
					
					
						
							if(selectVisibleTextFromDropDown(driver, fp.getDocumentStatisticsViewDropDownList(30), "Show dropdown", "Document Downloads")){
								ThreadSleep(2000);
								appLog.info("Document Downloads Option is selected ");
								if(getSelectedOptionOfDropDown(driver,fp.getDocumentStatisticsViewDropDownList(30), "Show DropDown List", "text").contains("Document Downloads")) {
									appLog.info("Document Downloads Option is verified");
									
								}else {
									appLog.error("Document Downloads Option is not selected in view statistics drop down list ");
									sa.assertTrue(false, "Document Downloads Option is not selected in view statistics drop down list ");
								}
							}else{
								appLog.error("Not able to select show dropdown value");
								sa.assertTrue(false, "Not able to select dropdown value");
							}
							
							
							
							
							if(click(driver, fp.getDocumentStatisticsPopUpCloseBtn(10), "close button", action.BOOLEAN)) {
								appLog.info("clicked on document statistics close button");
							}else {
								appLog.error("Not able to click on document statistics close button so cannot close pop up");
								sa.assertTrue(false, "Not able to click on document statistics close button so cannot close pop up");
							}
						}else {
							appLog.error("Not able to click on view link so cannot verify pop up");
							sa.assertTrue(false, "Not able to click on view link so cannot verify pop up");
						}
					}else {
						appLog.error("statistics view link is not visible so cannot click on it ");
						sa.assertTrue(false, "statistics view link is not visible so cannot click on it ");
					}
					ele=FindElement(driver, "//a[@title='"+standrdfile[0]+"']/../../following-sibling::span//a[@title='"+SmokeContact1FirstName+" "+SmokeContact1LastName+"']", "view link", action.BOOLEAN, 10);
					if(ele!=null) {
						if(click(driver, ele, "contact name link", action.BOOLEAN)) {
							String parentID = switchOnWindow(driver);
							if (parentID != null) {
								ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
								if (ele != null) {
									if (ele.getText().trim().equalsIgnoreCase(SmokeContact1FirstName + " " + SmokeContact1LastName)) {
										appLog.info(SmokeContact1FirstName + " " + SmokeContact1LastName + " Page is opened");
									} else {
										appLog.info(SmokeContact1FirstName + " " + SmokeContact1LastName + " Page is not opened");
										sa.assertTrue(false,
												SmokeContact1FirstName + " " + SmokeContact1LastName + " Page is not opened");
									}
								} else {
									appLog.info("Not able to find page header");
									sa.assertTrue(false, "Not able to find page header");
								}
								driver.close();
								driver.switchTo().window(parentID);
								switchToFrame(driver, 30, bp.getFrame(PageName.InstitutionsPage, 60));
							} else {
								appLog.info("No new window is open");
								sa.assertTrue(false, "No new window is open");
							}
							
						}else {
							appLog.error("Not able to click on contact name link so cannot verify contact page");
							sa.assertTrue(false, "Not able to click on contact name link so cannot verify contact page");
						}
					}else {
						appLog.error("contact name link is not visible so cannot verify contact page");
						sa.assertTrue(false, "contact name link is not visible so cannot verify contact page");
					}
					ele=FindElement(driver, "//a[@title='"+standrdfile[0]+"']/../../following-sibling::span//a[@title='"+SmokeInstitution1+"']", "view link", action.BOOLEAN, 10);
					if(ele!=null) {
						if (click(driver,ele, "firm name link", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Go to firm button");
							String parentID = switchOnWindow(driver);
							if (parentID != null) {
								ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
								if (ele != null) {
									if (ele.getText().trim().equalsIgnoreCase(SmokeInstitution1)) {
										appLog.info(SmokeInstitution1 + " Page is opened");
									} else {
										appLog.info(SmokeInstitution1 + " Page is not opened");
										sa.assertTrue(false, SmokeInstitution1 + " Page is not opened");
									}
								} else {
									appLog.info("Not able to find page header");
									sa.assertTrue(false, "Not able to find page header");
								}
								driver.close();
								driver.switchTo().window(parentID);
								switchToFrame(driver, 30, bp.getFrame(PageName.InstitutionsPage, 60));
							} else {
								appLog.info("No new window is open");
								sa.assertTrue(false, "No new window is open");
							}
						} else {
							appLog.info("Not able to click on got ot firm button");
							sa.assertTrue(false, "Not able to lcick on go to firm button");
						}

					}else {
						appLog.error("institution name is not visible so cannot click on it ");
						sa.assertTrue(false, "institution name is not visible so cannot click on it ");
					}
					if (fp.clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Open, standrdfile[1], Workspace.FundraisingWorkspace, 10, null)) {
						appLog.info("clicked on file "+standrdfile[1]);
						ThreadSleep(2000);
						String parentID = switchOnWindow(driver);
						if (parentID != null) {
							if(fp.getDownloadLink(30)!=null) {
								appLog.info("download button is displayed ");
							}else {
								appLog.error("Download button is not displayed ");
								sa.assertTrue(false, "Download button is not displayed ");
							}
							driver.close();
							driver.switchTo().window(parentID);
							switchToFrame(driver, 30, bp.getFrame(PageName.InstitutionsPage, 60));
						} else {
							appLog.info("No new window is open");
							sa.assertTrue(false, "No new window is open");
						}
					}else {
						appLog.error("Not able to click on "+standrdfile[1]+" file down arrow open so cannot open file ");
						sa.assertTrue(false, "Not able to click on "+standrdfile[1]+" file down arrow open so cannot open file ");
					}
					for(int i=0; i<2; i++) {
						if (fp.clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Delete, standrdfile[1], Workspace.FundraisingWorkspace, 10, null)) {
							appLog.info("clicked on file "+standrdfile[1]+" delete option ");
							ThreadSleep(2000);
							if(i==1) {
								if (click(driver, fp.getDocumentDeleteYesBtn(Workspace.FundraisingWorkspace,60), "Yes button", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on delete pop up yes button");
								}else {
									appLog.error("Not able to click on delete pop up yes button so cannot delete file "+standrdfile[1]);
									sa.assertTrue(false, "Not able to click on delete pop up yes button so cannot delete file "+standrdfile[1]);
								}
							}
							if(i==0) {
								if (click(driver, fp.getDocumentDeleteNoBtn(Workspace.FundraisingWorkspace, 10), "No button", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on delete pop up no button");
								}else {
									appLog.error("Not able to click on delete pop up no button so cannot close pop up");
									sa.assertTrue(false, "Not able to click on delete pop up no button so cannot clsoe pop up");
								}
							}
						}else {
							appLog.error("Not able to click on "+standrdfile[1]+" file down arrow open so cannot delete file ");
							sa.assertTrue(false, "Not able to click on "+standrdfile[1]+" file down arrow open so cannot delete file ");
						}
						
					}
				}else {
					appLog.error("Not able to click on folder path "+standrdFolder);
					sa.assertTrue(false, "Not able to click on folder path "+standrdFolder);
				}
			}else {
				appLog.error("Not able to click on created institution "+SmokeInstitution1);
				sa.assertTrue(false, "Not able to click on created institution "+SmokeInstitution1);
			}
			
		}else {
			appLog.error("Not able to click on institution tab");
			sa.assertTrue(false, "Not able to click on institution tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc019_checkLinksOnNIMPage(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(SmokeCRMUser1Email,SmokePassword);
		if(nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, nim.getFrame(PageName.NavatarInvestorManager, 30));
			if(nim.clickOnEditIcon()) {
				ThreadSleep(3000);
				WebElement ele = isDisplayed(driver,
						FindElement(driver,"//span[@id='grid_Users-rows']/span/span[3]/span[text()='" + SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName+ "']/../..//input[@type='checkbox']","Access checkbox of user" + SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName, action.SCROLLANDBOOLEAN, 30),"Visibility", 60, " Access checkbox for " + SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName);
				
				if (ele != null) {
					if(!isEnabled(driver, ele,"Access checkbox of user" + SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName)) {
						appLog.info(SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName+" check box is disabled");
					}else {
						appLog.error(SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName+" check box is not disabled");
						sa.assertTrue(false, SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName+" check box is disabled");
					}
				}else {
					appLog.error("Checkbox for " + SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName+ " not found so cannot provide internal user access to user: " + SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName);
					sa.assertTrue(false, "Checkbox for " + SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName+ " not found so cannot provide internal user access to user: " + SmokeCRMUser1FirstName+" "+SmokeCRMUser1LastName);
				}
				
				if(click(driver, nim.getGoBackLink(20), "go back link", action.BOOLEAN)) {
					appLog.info("clicked on Go Back Link");
				}else {
					appLog.error("Not able to click on internal user tab Go Back Link");
					sa.assertTrue(false, "Not able to click on internal user tab Go Back Link");
				}
			}else {
				appLog.info("Not able to click on internal tab edit icon");
				sa.assertTrue(false, "Not able to click on internal tab edit icon");
			}
			if(nim.clickOnSideMenusTab(sideMenu.FolderTemplates)){
				ThreadSleep(3000);
				if(selectVisibleTextFromDropDown(driver,nim.getDropdownTempType(30),"All Templates" , "All Templates")){
					appLog.info("select all templates options");
					ThreadSleep(3000);
					if(click(driver, nim.getfoldertemplatelink(SmokefolderTemplateName,20), "Folder Template link", action.SCROLLANDBOOLEAN)){
						appLog.info("Successfully clicked on Template Link.");
						if(nim.clickOnEditIcon()) {
							ThreadSleep(3000);
							if(nim.getFolderTemplateSaveButton(30)!=null) {
								appLog.info("save button is displaying in edit mode ");
							}else {
								appLog.error("save button is not displaying in edit mode ");
								sa.assertTrue(false, "save button is not displaying in edit mode ");
							}
							for(int i=0; i<2; i++) {
								if(click(driver, nim.getCancelFolderTemplateButton(20),"cancel button", action.BOOLEAN)) {
									appLog.info("clicked on cancel button");
									ThreadSleep(2000);
									if(i==0) {
										if(click(driver, nim.getNoBtnCancelWindowAddFolder(20), "cancel button", action.BOOLEAN)) {
											appLog.info("clicked on No button");
											
										}else {
											appLog.error("Not able to click on cancel pop up NO button");
											sa.assertTrue(false, "Not able to click on cancel pop up NO button");
										}
									}
									if(i==1) {
										if(click(driver, nim.getYesBtnCancelWindowAddFolder(20), "cancel button", action.BOOLEAN)) {
											appLog.info("clicked on yes button");
											
										}else {
											appLog.error("Not able to click on cancel pop up Yes button");
											sa.assertTrue(false, "Not able to click on cancel pop up Yes button");
										}
									}
									
								}else {
									appLog.error("Not able to click on cancel button ");
									sa.assertTrue(false,"Not able to click on cancel button ");
								}
								
							}
							
						}else {
							appLog.info("Not able to click on folder template edit icon");
							sa.assertTrue(false, "Not able to click on folder template edit icon");
						}
						
					}else {
						appLog.error("Not able to click on folder template link "+SmokefolderTemplateName);
						sa.assertTrue(false, "Not able to click on folder template link "+SmokefolderTemplateName);
					}
					
				}else{
					appLog.error("Can not select All Templates Name from drop down.");
					sa.assertTrue(false, "Can not select All Templates Name from drop down.");
				}
			}else{
				appLog.error("Can not click on Folder Template Link.");
				sa.assertTrue(false, "Can not click on Folder Template Link.");
			}
			if(nim.clickOnSideMenusTab(sideMenu.FileDistributorSettings)){
				ThreadSleep(2000);
				if(click(driver, nim.getReverseNamingRadioButton(20), "use reverse name radio button", action.BOOLEAN)) {
					appLog.info("clicked on user reverse radio button ");
					if(click(driver, nim.getFileDistributorSaveButton(fileDistributor.BulkUpload),"save button", action.BOOLEAN)) {
						appLog.info("clicked on bulk upload save button");
						
					}else {
						appLog.error("Not able to click on bulk upload save button");
						sa.assertTrue(false, "Not able to click on bulk upload save button");
					}
					
				}else {
					appLog.error("Not able to click on user reverse name radio button");
					sa.assertTrue(false, "Not able to click on user reverse name radio button");
				}
				if(nim.clickOnSideMenusTab(sideMenu.FileSplitterOptions)) {
					ThreadSleep(2000);
					if(click(driver, nim.getReverseNamingRadioButton(20), "user reverse name radio button", action.BOOLEAN)) {
						appLog.info("clicked on user reverse radio button ");
						if(click(driver, nim.getFileDistributorSaveButton(fileDistributor.FileSplitter),"save button", action.BOOLEAN)) {
							appLog.info("clicked on bulk upload save button");
							
						}else {
							appLog.error("Not able to click on file spliter upload save button");
							sa.assertTrue(false, "Not able to click on file spliter upload save button");
						}
						
					}else {
						appLog.error("Not able to click on user reverse name radio button");
						sa.assertTrue(false, "Not able to click on user reverse name radio button");
					}
					
				}else {
					appLog.error("Not able to click on file spliter option");
					sa.assertTrue(false, "Not able to click on file spliter option");
				}
				
			}else {
				appLog.error("Not able to click on file distributor setting tab ");
				sa.assertTrue(false, "Not able to click on file distributor setting tab ");
			}
		}else {
			appLog.error("Not able to click on NIM Tab");
			sa.assertTrue(false, "Not able to click on NIM Tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
		
	}

	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc020_checkLinksOnAllFirmsPage(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer inv = new InvestorFirmPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(SmokeContact1EmailId,SmokePassword);
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			appLog.info("All firms is selected ");
			if(click(driver, allfp.getDocumentNameList().get(0), "document name link", action.SCROLLANDBOOLEAN)) {
				ThreadSleep(5000);
				String parentID = switchOnWindow(driver);
				if (parentID != null) {
					if(allfp.getDownloadLink(30)!=null) {
						appLog.info("download button is displayed ");
					}else {
						appLog.error("Download button is not displayed ");
						sa.assertTrue(false, "Download button is not displayed ");
					}
					driver.close();
					driver.switchTo().window(parentID);
				} else {
					appLog.info("No new window is open");
					sa.assertTrue(false, "No new window is open");
				}
			}else {
				appLog.error("Not able to click on document link so cannot verify document open fuctionality");
				sa.assertTrue(false,"Not able to click on document link so cannot verify document open fuctionality");
			}
			
			if(click(driver, allfp.getInvesmentNameList().get(0), "investment name link", action.SCROLLANDBOOLEAN)) {
				ThreadSleep(2000);
				String parentID = switchOnWindow(driver);
				if (parentID != null) {
					if(inv.getInvestmentDownArrow(20)!=null) {
						appLog.info("potential investment page is open ");
					}else {
						appLog.error("potential investment page is not opened ");
						sa.assertTrue(false, "potential investment page is not opened ");
					}
					driver.close();
					driver.switchTo().window(parentID);
				} else {
					appLog.info("No new window is open");
					sa.assertTrue(false, "No new window is open");
				}
			}else {
				appLog.error("Not able to click on investment name link");
				sa.assertTrue(false,"Not able to click on investment name link");
			}
			
			if(click(driver, allfp.getFirmNameList().get(0), "firm name link", action.SCROLLANDBOOLEAN)) {
				ThreadSleep(2000);
				String parentID = switchOnWindow(driver);
				if (parentID != null) {
					if(inv.getInvestmentDownArrow(20)!=null) {
						appLog.info("potential investment page is open ");
					}else {
						appLog.error("potential investment page is not opened ");
						sa.assertTrue(false, "potential investment page is not opened ");
					}
					driver.close();
					driver.switchTo().window(parentID);
				} else {
					appLog.info("No new window is open");
					sa.assertTrue(false, "No new window is open");
				}
			}else {
				appLog.error("Not able to click on firm name link");
				sa.assertTrue(false,"Not able to click on firm name link");
			}
			List<WebElement> activityType=allfp.getContactFirmUpdateList();
			for(int i=0; i<activityType.size(); i++) {
				scrollDownThroughWebelement(driver, activityType.get(i), "Profile link");
				if(activityType.get(i).getText().trim().contains("Firm Profile Updated")) {
					if(click(driver, activityType.get(i), "Firm Profile Updated link", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Firm Profile Updated link");
						ThreadSleep(2000);
						if(click(driver, ivp.getFirmProfileUpdateCloseBtn(30), "firm profile update close button", action.BOOLEAN)) {
							appLog.info("Click on close button");
							break;
						}else {
							appLog.error("Not able to click on close button so cannot close firm profile pop up");
							sa.assertTrue(false, "Not able to click on close button so cannot close firm profile pop up");
						}
						
					}else {
						appLog.error("Not able to click on Firm Profile Updated link so cannot check pop up");
						sa.assertTrue(false, "Not able to click on Firm Profile Updated link so cannot check pop up");
					}
				}else {
					if(i==activityType.size()-1) {
						appLog.error("Not able to find firm profile update link so cannot check pop up");
						sa.assertTrue(false, "Not able to find firm profile update link so cannot check pop up");
					}
				}
			}
			activityType=allfp.getContactFirmUpdateList();
			for(int i=0; i<activityType.size(); i++) {
				scrollDownThroughWebelement(driver, activityType.get(i), "Profile link");
				if(activityType.get(i).getText().trim().contains("Contact Profile Updated")) {
					if(click(driver, activityType.get(i), "Contact Profile Updated link", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Contact Profile Updated link");
						ThreadSleep(2000);
						if(click(driver, ivp.getContactProfileCloseBtn(30), "contact profile update close button", action.BOOLEAN)) {
							appLog.info("Click on close button");
							break;
						}else {
							appLog.error("Not able to click on close button so cannot close contact profile pop up");
							sa.assertTrue(false, "Not able to click on close button so cannot close contact profile pop up");
						}
					}else {
						appLog.error("Not able to click on Firm Profile Updated link so cannot check pop up");
						sa.assertTrue(false, "Not able to click on Firm Profile Updated link so cannot check pop up");
					}
				}else {
					if(i==activityType.size()-1) {
						appLog.error("Not able to find Contact profile update link so cannot check pop up");
						sa.assertTrue(false, "Not able to find Contact profile update link so cannot check pop up");
					}
				}
			}
			
		}else {
			appLog.error("Not able to select All Firms from firm name drop down list so  cannot verify dashboard UI");
			sa.assertTrue(false, "Not able to select All Firms from firm name drop down list so  cannot verify dashboard UI");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc021_checkLinksOnAllDocumentsAndRecentActivitiesGrid(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String standrdFile=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
		String WatermarkingLabels=ExcelUtils.readData(smokeExcelPath,"FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.Watermarking);
		lp.investorLogin(SmokeContact1EmailId,SmokePassword);
		if(allfp.selectFirmName(SmokeFirmName)) {
			ThreadSleep(7000);
			if(click(driver,ivp.getAllDocumentsTab(30),"all document tab", action.BOOLEAN)) {
				ThreadSleep(2000);
				if(click(driver, ivp.getAllDocumentFileNameList().get(0), "all document name link", action.SCROLLANDBOOLEAN)) {
					ThreadSleep(5000);
					String parentID = switchOnWindow(driver);
					if (parentID != null) {
						if(allfp.getDownloadLink(30)!=null) {
							appLog.info("download button is displayed ");
							
						}else {
							appLog.error("Download button is not displayed ");
							sa.assertTrue(false, "Download button is not displayed ");
						}
						driver.close();
						driver.switchTo().window(parentID);
					} else {
						appLog.info("No new window is open");
						sa.assertTrue(false, "No new window is open");
					}
				}else {
					appLog.error("Not able to click on document link so cannot verify document open fuctionality on all doucment grid");
					sa.assertTrue(false,"Not able to click on document link so cannot verify document open fuctionality on all doucment grid");
				}
				refresh(driver);
				List<String> aa=fp.verifyWatermarkingWithoutAssertion(standrdFile,FolderType.Standard,WatermarkingLabels,SmokeFirmName,UpdateSmokeInstitution1,SmokeFundName1,SmokeContact1EmailId,getSystemDate("MM-dd-YYYY"), PageName.AllDocumentTab, null);
				if(aa.isEmpty()) {
					appLog.info("Watermarking is verified successfully ");
				}else {
					appLog.error("Watermarking is not verified ");
					sa.assertTrue(false, "Watermarking is not verified ");
				}
				
				if(click(driver, ivp.getAllDocumentInvestmentNameList().get(0), "investment name link", action.SCROLLANDBOOLEAN)) {
					ThreadSleep(2000);
					String parentID = switchOnWindow(driver);
					if (parentID != null) {
						if(ivp.getInvestmentDownArrow(20)!=null) {
							appLog.info("potential investment page is open ");
						}else {
							appLog.error("potential investment page is not opened ");
							sa.assertTrue(false, "potential investment page is not opened ");
						}
						driver.close();
						driver.switchTo().window(parentID);
					} else {
						appLog.info("No new window is open");
						sa.assertTrue(false, "No new window is open");
					}
				}else {
					appLog.error("Not able to click on investment name link");
					sa.assertTrue(false,"Not able to click on investment name link");
				}
			}else {
				appLog.error("Not able to click on all document tab");
				sa.assertTrue(false,"Not able to click on all document tab");
			}
			 if(sendKeys(driver,ivp.getAllDocumentSearchTextBox(60),standrdFile,"all document search text box", action.BOOLEAN)) {
				 if(click(driver, ivp.getAlldocumentSearchBtn(30), "search button", action.BOOLEAN)) {
					 ThreadSleep(3000);
					 List<WebElement> searchedFileName=ivp.getallDocumentSearchedFileNameList();
					 if(!searchedFileName.isEmpty()) {
						 for(int i=0; i<searchedFileName.size(); i++) {
								 if(searchedFileName.get(i).getText().trim().contains(standrdFile)) {
									 appLog.info(standrdFile+" file is visible in searched grid");
									 if(click(driver, searchedFileName.get(i), standrdFile+" document name", action.BOOLEAN)) {
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
										appLog.error("Not able to click on document name "+standrdFile+" so cannot check doucment in box view mode");
										sa.assertTrue(false, "Not able to click on document name "+standrdFile+" so cannot check doucment in box view mode");
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
									 appLog.error(standrdFile+" file is not visible in searched file grid");
									 sa.assertTrue(false, standrdFile+" file is not visible in searched file grid");
								 }										 
							
						 }
						 if(click(driver, ivp.getAlldocumentsearchedPopUpCloseBtn(2), "searched pop up close button", action.BOOLEAN)) {
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
				 appLog.error("not able to pass value "+standrdFile+" in search text box so cannot verify document search fuctionality");
				 sa.assertTrue(false, "not able to pass value "+standrdFile+" in search text box so cannot verify document search fuctionality");
			 }
			if(click(driver,ivp.getRecentActivitiesTab(30),"recent activities tab", action.BOOLEAN)) {
				ThreadSleep(2000);
				if(click(driver, ivp.getRecentActivitiesFileNameList().get(0), "all document name link", action.SCROLLANDBOOLEAN)) {
					ThreadSleep(5000);
					String parentID = switchOnWindow(driver);
					if (parentID != null) {
						if(allfp.getDownloadLink(30)!=null) {
							appLog.info("download button is displayed ");
						}else {
							appLog.error("Download button is not displayed ");
							sa.assertTrue(false, "Download button is not displayed ");
						}
						driver.close();
						driver.switchTo().window(parentID);
					} else {
						appLog.info("No new window is open");
						sa.assertTrue(false, "No new window is open");
					}
				}else {
					appLog.error("Not able to click on document link so cannot verify document open fuctionality on recent activities grid");
					sa.assertTrue(false,"Not able to click on document link so cannot verify document open fuctionality on recent activities grid");
				}
				
				if(click(driver, ivp.getRecentActivitiestInvestmentNameList().get(0), "investment name link", action.SCROLLANDBOOLEAN)) {
					ThreadSleep(2000);
					String parentID = switchOnWindow(driver);
					if (parentID != null) {
						if(ivp.getInvestmentDownArrow(20)!=null) {
							appLog.info("potential investment page is open ");
						}else {
							appLog.error("potential investment page is not opened ");
							sa.assertTrue(false, "potential investment page is not opened ");
						}
						driver.close();
						driver.switchTo().window(parentID);
					} else {
						appLog.info("No new window is open");
						sa.assertTrue(false, "No new window is open");
					}
				}else {
					appLog.error("Not able to click on investment name link");
					sa.assertTrue(false,"Not able to click on investment name link");
				}
				for(int k=0; k<2; k++) {
					if(k==0) {
						List<WebElement> activityType=ivp.getRecentActivitiesContactFirmUpdateList();
						for(int i=0; i<activityType.size(); i++) {
							scrollDownThroughWebelement(driver, activityType.get(i), "");
							if(activityType.get(i).getText().trim().contains("Firm Profile Updated")) {
								if(click(driver, activityType.get(i), "Firm Profile Updated link", action.SCROLLANDBOOLEAN)) {
									appLog.info("Clicked on Firm Profile Updated link");
									ThreadSleep(2000);
									if(click(driver, ivp.getFirmProfileUpdateCloseBtn(30), "firm profile update close button", action.BOOLEAN)) {
										appLog.info("Click on close button");
										break;
									}else {
										appLog.error("Not able to click on close button so cannot close firm profile pop up");
										sa.assertTrue(false, "Not able to click on close button so cannot close firm profile pop up");
									}
									
								}else {
									appLog.error("Not able to click on Firm Profile Updated link so cannot check pop up");
									sa.assertTrue(false, "Not able to click on Firm Profile Updated link so cannot check pop up");
								}
							}else {
								if(i==activityType.size()-1) {
									appLog.error("Not able to find firm profile update link so cannot check pop up");
									sa.assertTrue(false, "Not able to find firm profile update link so cannot check pop up");
								}
							}
						}
						
					}
					if(k==1) {
						List<WebElement> activityType=ivp.getRecentActivitiesContactFirmUpdateList();
						for(int i=0; i<activityType.size(); i++) {
							scrollDownThroughWebelement(driver, activityType.get(i), "");
							 if (activityType.get(i).getText().trim().contains("Contact Profile Updated")) {
								if(click(driver, activityType.get(i), "Contact Profile Updated link", action.SCROLLANDBOOLEAN)) {
									appLog.info("Clicked on Firm Profile Updated link");
									ThreadSleep(2000);
									if(click(driver, ivp.getContactProfileCloseBtn(30), "contact profile update close button", action.BOOLEAN)) {
										appLog.info("Click on close button");
										break;
									}else {
										appLog.error("Not able to click on close button so cannot close contact profile pop up");
										sa.assertTrue(false, "Not able to click on close button so cannot close contact profile pop up");
									}
									
								}else {
									appLog.error("Not able to click on Firm Profile Updated link so cannot check pop up");
									sa.assertTrue(false, "Not able to click on Firm Profile Updated link so cannot check pop up");
								}
							}else {
								if(i==activityType.size()-1) {
									appLog.error("Not able to find contact profile update link so cannot check pop up");
									sa.assertTrue(false, "Not able to find contact profile update link so cannot check pop up");
								}
							}
						}
					}
					
				}
			}else {
				appLog.error("Not able to click on recent activities tab");
				sa.assertTrue(false,"Not able to click on recent activities tab");
			}
		}else {
			appLog.error("Not able to select Firms Name "+SmokeFirmName+" from firm name drop down list");
			sa.assertTrue(false, "Not able to select Firms Name "+SmokeFirmName+" from firm name drop down list");
		}
		lp.investorLogout();
		sa.assertAll();
	}

	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc022_checkLinkInvestorLoginPage(String environment, String mode) {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
     	driver.get(SmokeInvestorURL);
     	if (click(driver, lp.getResetPasswordLink(60), "Reset Password Link",action.BOOLEAN)) {
     		ThreadSleep(2000);
     		if(getText(driver, lp.getResetPasswordLabelText(20), "label text", action.BOOLEAN).contains(LoginErrorPage.resetPasswordErrorMsg)) {
     			appLog.info("Reste Password Error Message is verified ");
     		}else {
				appLog.error("Reset password error message is not verified");
				sa.assertTrue(false, "Reset password error message is not verified");
			}
     		if(click(driver, lp.getNavatarImg(20), "logo", action.BOOLEAN)) {
     			appLog.info("clicked on navatar logo");
     			ThreadSleep(2000);
     			if(click(driver, lp.getForgotUsernameInvestor(20), "forgot username link", action.BOOLEAN)) {
     				appLog.info("clicked on forgot password link");
     				String parentID=switchOnWindow(driver);
     				ThreadSleep(5000);
     				String second_wind_ID = driver.getWindowHandle();
     				if ((lp.getPleaseContactOurLabel(60)!=null)&&(lp.getCloseButtonForgotPassword(60)!=null)){
     					appLog.info("Please contact our customer support text and close link was successfully found on forgot your username window");

     					if (click(driver, lp.getCustomerSupportLink(60), "customer support link on forgot your username page", action.BOOLEAN)) {
     						Set<String> windows = driver.getWindowHandles();
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
     					}else {
     						appLog.error("customer support url was not clickable");
     						sa.assertTrue(false,"customer support url was not clickable");
     					}
     				}else {
     					appLog.error("Please contact our customer support and close link was not found");
     					sa.assertTrue(false, "Please contact our customer support and close link was not found");

     				}
     				driver.switchTo().window(parentID);
     				
     			}else {
					appLog.error("Not able to click on forgot password link");
					sa.assertTrue(false, "Not able to click on forgot password link");
				}
     			
     			if (click(driver, lp.getInvestorAlreadyInvitedSignupLink(60), "Sign up link", action.BOOLEAN)) {
     				ThreadSleep(5000);
     				if (getURL(driver, 60).contains("SiteRegisteration")) {
     					appLog.info("Registration page opened successfully after clicking on sign up link on investor login page");
     				}else {
     					appLog.error("Investor registration page is not opened");
     					sa.assertTrue(false, "Investor registration page is not opened");
     				}
     				if(click(driver, lp.getClickHereLinkOnTargetLoginPage(60), "Click here link", action.SCROLLANDBOOLEAN)){
     					appLog.info("Clicked on click here link successfully");
     					ThreadSleep(5000);
     					if (getURL(driver, 60).contains("Login")) {
     						appLog.info("Login page opened successfully after clicking on Click here link on investor registration page");
     					}else {
     						appLog.error("Investor Login page is not opened");
     						sa.assertTrue(false, "Investor Login page is not opened");
     					}
     					if (click(driver, lp.getGeneralPartnerAlreadyInvitedSignupLink(60), "Sign up link", action.BOOLEAN)) {
     						ThreadSleep(5000);
     						if (getURL(driver, 60).contains("NonUserRegisterStep1")) {
     								appLog.info("Registration page opened successfully after clicking on sign up link on investor login page");
     							}else {
     								appLog.error("URL is not matched after click on General Partner Already Invited Signup Link ");
     								sa.assertTrue(false, "URL is not matched after click on General Partner Already Invited Signup Link ");
     						}
     						if(click(driver, lp.getTargetcancelBtn(20), "cancel button", action.BOOLEAN)) {
     							appLog.info("clicked on cancel button");
     							
     						}else {
								appLog.error("Not able to clik on Cacnel button");
								sa.assertTrue(false, "Not able to clik on Cacnel button");
							}
     					}else {
     						sa.assertTrue(false,"Not able to click on General Partner Already Invited Signup Link");
     						sa.assertTrue(false, "Not able to click on General Partner Already Invited Signup Link");
     					}
     				} else{
     					appLog.error("Not able to click on click here link");
     					sa.assertTrue(false, "Not able to click on click here link");
     				}
     			}else {
     				appLog.error("sign up link was not clickable on investor login page");
     				sa.assertTrue(false,"sign up link was not clickable on investor login page");
     			}
     			if (click(driver, lp.getTwitterIcon(60), "twitter icon on investor login page", action.BOOLEAN)) {
    				ThreadSleep(5000);
    				String parentID=switchOnWindow(driver);
    				if(getURL(driver, 60).contains(ExcelUtils.readDataFromPropertyFile("TwitterURL"))) {
    					appLog.info("Navatar twitter page successfully opened");
    				}
    				else {
    					appLog.error("Navatar twitter page was not opened");
    					sa.assertTrue(false,"Navatar twitter page was not opened");
    				}
    				driver.close();
    				driver.switchTo().window(parentID);
    			}
    			else {
    				appLog.error("Twitter icon link is not clickable on investor login page");
    				sa.assertTrue(false,"Twitter icon link is not clickable on investor login page");
    				
    			}
     			ThreadSleep(5000);
     			if (click(driver, lp.getGeneralPartnerAlreadyInvitedSignupLink(60), "Sign up link", action.BOOLEAN)) {
     				ThreadSleep(5000);
     				if(click(driver, lp.getHomeLink(20), "home link", action.BOOLEAN)) {
     					appLog.info("clicked on home page link");
     					ThreadSleep(2000);
     					if(lp.getInvestorUsernameTextbox(20)!=null) {
     						appLog.info("Login page is open after click on home page ");
     					}else {
     						appLog.error("Login Page is not opened after click on home page");
     						sa.assertTrue(false, "Login Page is not opened after click on home page");
     					}
     				}else {
     					appLog.error("Not able to click on home link  so cannot verify login page");
     					sa.assertTrue(false, "Not able to click on home link  so cannot verify login page");
     				}

     			}else {
     				appLog.error("sign up link was not clickable on investor login page");
     				sa.assertTrue(false,"sign up link was not clickable on investor login page");
     			}
     			if (click(driver, lp.getGeneralPartnerAlreadyInvitedSignupLink(60), "Sign up link", action.BOOLEAN)) {
     				ThreadSleep(5000);
     				String parentId=null;
     				if(click(driver, lp.getBlogLink(20), "blog link", action.BOOLEAN)) {
     					appLog.info("clicked on home page link");
     					parentId=switchOnWindow(driver);
     					ThreadSleep(2000);
     					if(getURL(driver, 60).contains("blog")) {
        					appLog.info("Navatar Group blog Page is open ");
        				}
        				else {
        					appLog.error("Navatar Group  blog  page is not open after click on blog link");
        					sa.assertTrue(false,"Navatar Group blog  page is not open after click on blog link");
        				}
        				driver.close();
        				driver.switchTo().window(parentId);
     				}else {
     					appLog.error("Not able to click on blog link  so cannot verify navatar group blog page");
     					sa.assertTrue(false, "Not able to click on blog link  so cannot verify navatar group blog page");
     				}
     				
     				
     				if(click(driver, lp.getSupportLink(20), "support link", action.BOOLEAN)) {
     					appLog.info("clicked on home page link");
     					parentId=switchOnWindow(driver);
     					ThreadSleep(2000);
     					if(getURL(driver, 60).contains("support")) {
        					appLog.info("Navatar Group support Page is open ");
        				}
        				else {
        					appLog.error("Navatar Group  support  page is not open after click on blog link");
        					sa.assertTrue(false,"Navatar Group support page is not open after click on blog link");
        				}
        				driver.close();
        				driver.switchTo().window(parentId);
     				}else {
     					appLog.error("Not able to click on support link  so cannot verify navatar group support page");
     					sa.assertTrue(false, "Not able to click on support link  so cannot verify navatar group support page");
     				}
     				
     				if(click(driver, lp.getContactUsLink1(20), "contact us link", action.BOOLEAN)) {
     					appLog.info("clicked on home page link");
     					parentId=switchOnWindow(driver);
     					ThreadSleep(2000);
     					if(getURL(driver, 60).contains("contact-navatar")) {
        					appLog.info("Navatar Group contact navatar Page is open ");
        				}
        				else {
        					appLog.error("Navatar Group  contact-navatar  page is not open after click on contact us link");
        					sa.assertTrue(false,"Navatar Group contact-navatar page is not open after click on contact us link");
        				}
        				driver.close();
        				driver.switchTo().window(parentId);
     				}else {
     					appLog.error("Not able to click on contact us link  so cannot verify navatar group contact us page");
     					sa.assertTrue(false, "Not able to click on contact us link  so cannot verify navatar group contact us page");
     				}
     				
     				if(click(driver, lp.getProductsLink(20), "products link", action.BOOLEAN)) {
     					appLog.info("clicked on products link");
     					parentId=switchOnWindow(driver);
     					ThreadSleep(2000);
     					if(getURL(driver, 60).contains("products")) {
        					appLog.info("Navatar Group products Page is open ");
        				}
        				else {
        					appLog.error("Navatar Group  products page is not open after click on products link");
        					sa.assertTrue(false,"Navatar Group products page is not open after click on products link");
        				}
        				driver.close();
        				driver.switchTo().window(parentId);
     				}else {
     					appLog.error("Not able to click on products link  so cannot verify navatar group products page");
     					sa.assertTrue(false, "Not able to click on products link  so cannot verify navatar group products page");
     				}
     				if(click(driver, lp.getDealSourcingLink(20), "deal sourcing link", action.BOOLEAN)) {
     					appLog.info("clicked on deal sourcing link");
     					parentId=switchOnWindow(driver);
     					ThreadSleep(2000);
     					if(getURL(driver, 60).contains("navatardealconnect")) {
        					appLog.info("navatar deal connect Page is open ");
        				}
        				else {
        					appLog.error("navatar deal connect page is not open after click on deal sourcing link");
        					sa.assertTrue(false,"navatar deal connect page is not open after click on deal sourcing link");
        				}
        				driver.close();
        				driver.switchTo().window(parentId);
     				}else {
     					appLog.error("Not able to click on deal sourcing link  so cannot verify navatar deal connect page");
     					sa.assertTrue(false, "Not able to click on deal sourcing link  so cannot verify navatar deal connect page");
     				}
     				
     				
     				if(click(driver, lp.getLpNetworkLink(20), "lp network link", action.BOOLEAN)) {
     					appLog.info("clicked on lp network link");
     					parentId=switchOnWindow(driver);
     					ThreadSleep(2000);
     					if(getURL(driver, 60).contains("investor-portal-software")) {
        					appLog.info("navatar group investor portal Page is open ");
        				}
        				else {
        					appLog.error("navatar group investor portal page is not open after click on lp network link");
        					sa.assertTrue(false,"navatar group investor portal page is not open after click on lp network link");
        				}
        				driver.close();
        				driver.switchTo().window(parentId);
     				}else {
     					appLog.error("Not able to click on lp network link  so cannot verify navatar group investor portal page");
     					sa.assertTrue(false, "Not able to click on lp network link  so cannot verify navatar group investor portal page");
     				}
     				
     				if(click(driver, lp.getCustomersLink(20), "customers link", action.BOOLEAN)) {
     					appLog.info("clicked on customers link");
     					parentId=switchOnWindow(driver);
     					ThreadSleep(2000);
     					if(getURL(driver, 60).contains("case-studies")) {
        					appLog.info("case-studies Page is open ");
        				}
        				else {
        					appLog.error("case-studies page is not open after click on customers link");
        					sa.assertTrue(false,"case-studies page is not open after click on customers link");
        				}
        				driver.close();
        				driver.switchTo().window(parentId);
     				}else {
     					appLog.error("Not able to click on customers link  so cannot verify navatar group case-studies page");
     					sa.assertTrue(false, "Not able to click on customers link  so cannot verify navatar group case-studies page");
     				}
     				
     				if(click(driver, lp.getCompanyLink(20), "company link", action.BOOLEAN)) {
     					appLog.info("clicked on company link");
     					parentId=switchOnWindow(driver);
     					ThreadSleep(2000);
     					if(getURL(driver, 60).contains("navatar-company-and-leadership")) {
        					appLog.info("navatar-company-and-leadership Page is open ");
        				}
        				else {
        					appLog.error("navatar-company-and-leadership page is not open after click on company link");
        					sa.assertTrue(false,"navatar-company-and-leadership page is not open after click on company link");
        				}
        				driver.close();
        				driver.switchTo().window(parentId);
     				}else {
     					appLog.error("Not able to click on company link  so cannot verify navatar group navatar-company-and-leadership page");
     					sa.assertTrue(false, "Not able to click on company link  so cannot verify navatar group navatar-company-and-leadership page");
     				}
     				
     			}else {
     				appLog.error("sign up link was not clickable on investor login page");
     				sa.assertTrue(false,"sign up link was not clickable on investor login page");
     			}
     		}else {
				appLog.error("Not able to click on navatar logo");
				sa.assertTrue(false, "Not able to click on navatar logo");
			}
     	}else {
			appLog.error("Not able to click on reset password link so cannot verify link");
			sa.assertTrue(false, "Not able to click on reset password link so cannot verify link");
		}
     sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc023_1_SendForgotPasswordEmailToInvestor(String environment, String mode){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
	   	driver.get(SmokeInvestorURL);
	   	if (click(driver, lp.getResetPasswordLink(60), "Reset Password link",action.BOOLEAN)) {
				if (sendKeys(driver,lp.getResetPasswordUserNameTextBox(60), SmokeContact1EmailId,
					"Reset Username Text Box with Register Target Mail Id: " + SmokeContact1EmailId, action.BOOLEAN)) {
				if (click(driver, lp.getResetPasswordSubmitButton(60), "Reset Password Submit Button",
						action.BOOLEAN)) {
					ThreadSleep(4000);
					SoftAssert saa=bp.verifyResetPasswordMailContent(SmokeContact1FirstName, SmokeContact1EmailId, "InvestorPage", null, null,smokeExcelPath);
					sa.combineAssertions(saa);
				} else {
					appLog.error("Not Able to Click reset Submit Button");
					sa.assertTrue(false, "Not Able to Click reset Submit Button");
				}
				} else {
				appLog.error("Not Able to Enter Value to User Text");
				sa.assertTrue(false, "Not Able to Enter Value to User Text");
			}
	   	}else {
			appLog.error("Not Able to Click Forgot password Link");
			sa.assertTrue(false, "Not Able to Click Forgot password Link");
		}
		sa.assertAll();		
	}
	
	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc023_2_VerifySendingResetPasswordAndChangePassword(String environment, String mode){
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer afp =new AllFirmsPageBusinesslayer(driver);
		String updatedPassword = ExcelUtils.readData(smokeExcelPath,"Contact",excelLabel.Variable_Name, "SmokeC1", excelLabel.Updated_Password);
		driver.get(ExcelUtils.readData(smokeExcelPath,"Contact",excelLabel.Variable_Name, "SmokeC1", excelLabel.Click_HereLink));		
	if(sendKeys(driver, bp.getResetPasswordNewPasswordTextBox(60), updatedPassword, "New password text box", action.SCROLLANDBOOLEAN)){
		if(sendKeys(driver, bp.getResetPasswordConfirmPasswordTextBox(60), updatedPassword, "Confirm password text box", action.SCROLLANDBOOLEAN)){
		if(click(driver, bp.getResetPasswordButton(60), "Reset Password button", action.SCROLLANDBOOLEAN)){
			ThreadSleep(4000);
			if (getURL(driver, 60).contains("login")) {
				appLog.info("Target Login page is open in same window. ");
			} else {
				appLog.error("Target Login page is not opening. " + getURL(driver, 30));
				sa.assertTrue(false, "Some different url is opening. " + getURL(driver, 30));
			}			
		if(sendKeys(driver, lp.getInvestorPasswordTextbox(60),updatedPassword, "Target passowrd text box", action.SCROLLANDBOOLEAN)){
			if(click(driver, lp.getInvestorLoginButton(60), "Target login button", action.SCROLLANDBOOLEAN)){
				ThreadSleep(5000);
		if(afp.getFirmNameDropdown(60)!=null){
			if(getSelectedOptionOfDropDown(driver, afp.getFirmNameDropdown(60), "Select deal dropdown", "text").equalsIgnoreCase(SmokeFirmName)){
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
	
	////////////////////////// INvestor
	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc024_buildINWorkSpaceAndProvideContactAccess(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		String standardFolder=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		lp.CRMLogin(SmokeCRMUser2Email,SmokePassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(SmokeFundName1)) {
				String Size=ExcelUtils.readData(smokeExcelPath,"Funds",excelLabel.Variable_Name, "SmokeFund1", excelLabel.Fund_Size);
				String vintageyear=ExcelUtils.readData(smokeExcelPath,"Funds",excelLabel.Variable_Name, "SmokeFund1", excelLabel.Fund_VintageYear);
				String con=ExcelUtils.readData(smokeExcelPath,"Funds",excelLabel.Variable_Name, "SmokeFund1", excelLabel.Fund_Contact);
				String phone=ExcelUtils.readData(smokeExcelPath,"Funds",excelLabel.Variable_Name, "SmokeFund1", excelLabel.Fund_Phone);
				String email=ExcelUtils.readData(smokeExcelPath,"Funds",excelLabel.Variable_Name, "SmokeFund1", excelLabel.Fund_Email);
				String description=ExcelUtils.readData(smokeExcelPath,"Funds",excelLabel.Variable_Name, "SmokeFund1", excelLabel.Fund_Description);
				String[] data= {Size,vintageyear,con,phone,email,description};
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				System.err.println("Switched to frame.");
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 20), Workspace.InvestorWorkspace.toString()+" View.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.InvestorWorkspace, 10), Workspace.InvestorWorkspace.toString()+" Workspace Button", action.BOOLEAN)){
					if(!sendKeys(driver, fp.getSizeInMillionTextBox(Workspace.InvestorWorkspace, 10), data[0], "Size in Million text box", action.BOOLEAN)){
						sa.assertTrue(false,"Not able to pass data to size in million text box.");
					}
					if(!sendKeys(driver, fp.getVintageYear(Workspace.InvestorWorkspace, 10), data[1], "vintage Year", action.BOOLEAN)){
						sa.assertTrue(false,"Not able to pass data to Vintage Year text box.");
					}
					String value = getAttribute(driver, fp.getContactTextBox(Workspace.InvestorWorkspace, 10), "Contact Text Box", "value");
					if(value!=null && !value.isEmpty()){
						appLog.info("value is already present in contact text box.");
					} else {
						if(!sendKeys(driver, fp.getContactTextBox(Workspace.InvestorWorkspace,10), data[2], "Contact text Box", action.BOOLEAN)){
							sa.assertTrue(false, "Not able to pass data to Contact text box.");
						}
					}
					value = getAttribute(driver, fp.getPhoneTextBox(Workspace.InvestorWorkspace, 10), "Phone Text Box", "value");
					if(value!=null && !value.isEmpty()){
						appLog.info("value is already present in contact text box.");
					} else {
						if(!sendKeys(driver, fp.getPhoneTextBox(Workspace.InvestorWorkspace, 10), data[3], "Phone text Box", action.BOOLEAN)){
							sa.assertTrue(false, "Not able to pass data to phone text box.");
						}
					}
					value = getAttribute(driver, fp.getEmailTextBox(Workspace.InvestorWorkspace, 30), "Email Text Box", "value");
					if(value!=null && !value.isEmpty()){
						appLog.error("value is already present in contact text box.");
					} else {
						if(!sendKeys(driver, fp.getEmailTextBox(Workspace.InvestorWorkspace, 10),data[4], "Email text Box", action.BOOLEAN)){
							appLog.error("Not able to pass data to email text box.");
						}
					}
					value = getAttribute(driver, fp.getDescriptionTextBox(Workspace.InvestorWorkspace, 10), "Description Text Box", "value");
					if(value!=null && !value.isEmpty()){
						appLog.info("value is already present in contact text box.");
					} else {
						if(!sendKeys(driver, fp.getDescriptionTextBox(Workspace.InvestorWorkspace, 10),data[5], "Description text Box", action.BOOLEAN)){
						}
					}
					if(click(driver, fp.getNext1Of3Button(Workspace.InvestorWorkspace, 10), "Next Button", action.BOOLEAN)){
						if(!fp.importFolderTemplate(smokeExcelPath,"FolderTemp", SmokefolderTemplateName, WorkSpaceAction.IMPORTFOLDERTEMPLATE, Workspace.InvestorWorkspace, 20)){
							appLog.error("Folder sructure is not created properly");
							sa.assertTrue(false,"Folder sructure is not created properly");
						} else {
							appLog.info("Folder Structure is created successfully.");
							if(click(driver, fp.getClearAllFolderButton(Workspace.InvestorWorkspace, EnableDisable.Enable, 30), "clear all button", action.BOOLEAN)) {
								appLog.info("clicked on clear all button");
								ThreadSleep(2000);
								if(click(driver, fp.getClearAllFolderConfirmationPopUpNoButton(Workspace.InvestorWorkspace, 30), "clear all folder no button", action.BOOLEAN)) {
									appLog.info("clicked on clear all No button");
								}else {
									appLog.error("Not able to click on clear all No button so cannot close pop up");
									sa.assertTrue(false, "Not able to click on clear all No button so cannot close pop up");
								}
							}else {
								appLog.error("Not able to click on clear all folder button so cannot check pop up");
								sa.assertTrue(false, "Not able to click on clear all folder button so cannot check pop up");
							}
							String id=null;
							id=fp.getCreatedFolderId(standardFolder, PageName.ManageFolderPopUp, 20);
							if(id!=null) {
								if(fp.clickOnRenameFolderButton(id)) {
									if(click(driver, fp.getParentRenameFolderCancelButton(Workspace.InvestorWorkspace, PageName.FundsPage, 10), "parent folder pop up cancel button", action.SCROLLANDBOOLEAN)) {
										appLog.info(standardFolder+" folder  rename pop is open and close successfully");
									}else {
										appLog.error(standardFolder+" folder rename pop is not open so cannot click on cancel button");
										sa.assertTrue(false, standardFolder+" rename folder pop is not open so cannot click on cancel button");
									}
								}else {
									appLog.error("Not able to click on folder "+standardFolder+" rename icon so cannot check pop up ");
									sa.assertTrue(false, "Not able to click on folder "+standardFolder+" rename icon so cannot check pop up ");
								}
							}else {
								appLog.error(standardFolder+" is not visible so cannot click on edit icon and check edit pop up");
								sa.assertTrue(false, standardFolder+" is not visible so cannot click on edit icon and check edit pop up");
							}
							id=null;
							id=fp.getCreatedFolderId(standardFolder, PageName.ManageFolderPopUp, 20);
							if (bp.clickOnAddFolderButton(id)) {
								if(click(driver, fp.getAddFolderChildFolderCancelButton(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 10), "child add folder pop up cancel button", action.SCROLLANDBOOLEAN)) {
									appLog.info(standardFolder+" add folder pop is open and close successfully");
								}else {
									appLog.error(standardFolder+" add folder pop is not open so cannot click on cancel button");
									sa.assertTrue(false, standardFolder+" add folder pop is not open so cannot click on cancel button");
								}
								
							}else {
								appLog.error("Not able to click on add folder button so cannot check add folder pop up");
								sa.assertTrue(false, "Not able to click on add folder button so cannot check add folder pop up");
							}
							id=fp.getCreatedFolderId(standardFolder, PageName.ManageFolderPopUp, 20);
							if(id!=null) {
								if(fp.clickOnDeleteFolderButton(id)) {
									if(click(driver, fp.getFolderDeleteNoBtn(Workspace.InvestorWorkspace,PageName.FundsPage, 10), "folder delete no Button", action.BOOLEAN)) {
										appLog.info("Folder delete pop up is open and close successfully");
									}else {
										appLog.error("No button is not displaying on folder delete pop up so cannot click on No button");
										sa.assertTrue(false, "No button is not displaying on folder delete pop up so cannot click on No button");
									}
								}else {
									appLog.error("Not able to click on folder "+standardFolder+" delete icon so cannot check pop up ");
									sa.assertTrue(false, "Not able to click on folder "+standardFolder+" delete icon so cannot check pop up ");
								}
							}else {
								appLog.error(standardFolder+" is not visible so cannot click on delete icon and check edit pop up");
								sa.assertTrue(false, standardFolder+" is not visible so cannot click on delete icon and check edit pop up");
							}
							
						}
						if(click(driver, fp.getNext2Of3Button(Workspace.InvestorWorkspace, 10), "Next button 2Of3", action.BOOLEAN)){
							ThreadSleep(10000);
							if(click(driver, fp.getStep3Of3ApplyButton(Workspace.InvestorWorkspace, 10), "apply button", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on apply button");
								if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.filterPleaseSelectAFieldErroresage,
										fp.getManageInvestorFilterfieldSelectErrorMessage(Workspace.InvestorWorkspace).get(0),
										"Please select a field error Message in filter")) {
									appLog.info("Please select a field Error Message is verified at filter");
								} else {
									sa.assertTrue(false,
											"Please select a field error message is not verified at filter.Expected:"
													+ FundsPageErrorMessage.filterPleaseSelectAFieldErroresage + " Actual"
													+ getText(driver,
															fp.getManageInvestorFilterfieldSelectErrorMessage(
																	Workspace.InvestorWorkspace).get(0),
															"Please select a field error Message in filter",
															action.BOOLEAN));
								}
							}else {
								appLog.error("Not able to click on apply button so cannot check error message "+FundsPageErrorMessage.filterPleaseSelectAFieldErroresage);
								sa.assertTrue(false, "Not able to click on apply button so cannot check error message "+FundsPageErrorMessage.filterPleaseSelectAFieldErroresage);
							}
							
							if(click(driver, fp.getStep3Of3ClearButton(Workspace.InvestorWorkspace, 10), "clear button", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked  on clear button ");
								
							}else {
								appLog.error("Not able to click on clear button so cannot check clear button functionality");
								sa.assertTrue(false, "Not able to click on clear button so cannot check clear button functionality");
							}
							
							if(click(driver, fp.getStep3Of3AddRowLink(Workspace.InvestorWorkspace, 10), "add row link", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on add rows link ");
								if(click(driver, fp.getManageInvestorFilterRemoveRowIcon(Workspace.InvestorWorkspace).get(0), "delete added row cross icon", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on delete row cross icon");
								}else {
									appLog.error("Not able to click on delete row cross icon so cannot delete added row");
									sa.assertTrue(false, "Not able to click on delete row cross icon so cannot delete added row");
								}
							}else {
								appLog.error("Not able to click on add row link so cannot add more rows in filter investor");
								sa.assertTrue(false, "Not able to click on add row link so cannot add more rows in filter investor");
							}
							if(fp.selectInstitutionOrLP(SmokeInstitution1+"/"+SmokeLimitedPartner1, Workspace.InvestorWorkspace, 10)){
								if(click(driver, fp.getInvestorAddedConfirmationCloseButton(Workspace.InvestorWorkspace, 10), "Investor Addition confirmation Close Button", action.BOOLEAN));
							}
							if(click(driver, fp.getDone3Of3Button(Workspace.InvestorWorkspace, 10), "Done button", action.BOOLEAN)){
								appLog.info("Workspace has been created.");
								if(click(driver, fp.getCongratulationsCloseButton(Workspace.InvestorWorkspace, 10), "Congratualtions pop close button", action.BOOLEAN)){
									appLog.info("Workspace Created Successfully.");
								} else {
									refresh(driver);
								}
							} else {
								appLog.error("Workspace is not created.");
								sa.assertTrue(false,"Workspace is not created.");
							}
						} else {
							appLog.error("Next button 2Of3 cannot be clicked, So cannot build the Investor workspace");
							sa.assertTrue(false,"Next button 2Of3 cannot be clicked, So cannot build the Investor workspace");
							
						}
					} else {
						appLog.error("Next button cannot be clicked, so cannot build the Investor workspace.");
						sa.assertTrue(false,"Next button cannot be clicked, so cannot build the Investor workspace.");
					}
				
				}else {
					appLog.error("Not able to click on build workspace button so cannot build Investor workspace");
					sa.assertTrue(false, "Not able to click on build workspace button so cannot build Investor workspace");
				}
				
				
			}else {
				appLog.error("Not able to click on created fund Name so cannot build Investor workspace: "+SmokeFundName1);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot build Investor workspace: "+SmokeFundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot build Investor workspace: "+SmokeFundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot build Investor workspace: "+SmokeFundName1);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc025_inviteContactAndSendEmailToContact(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		String shrdfolder=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		lp.CRMLogin(SmokeCRMUser2Email, SmokePassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(SmokeFundName1)) {
				if(fp.inviteContact(environment, mode,SmokeInstitution1, SmokeContact2EmailId,null, FolderType.Standard,"Upload","Yes", "No",null, Workspace.InvestorWorkspace, null)) {
					appLog.info("Contact "+SmokeContact2FirstName+" "+SmokeContact2LastName+" is invited successfuly");
				}else {
					appLog.error("Contact "+SmokeContact2FirstName+" "+SmokeContact2LastName+" is not invited successfuly");
					sa.assertTrue(false, "Contact "+SmokeContact2FirstName+" "+SmokeContact2LastName+" is not invited successfuly");
				}
				if(fp.inviteContact(environment, mode,SmokeInstitution1+"/"+SmokeLimitedPartner1, SmokeContact3EmailId,null, FolderType.Standard,null,"Yes", "No",null, Workspace.InvestorWorkspace, null)) {
					appLog.info("Contact "+SmokeContact3FirstName+" "+SmokeContact3LastName+" is invited successfuly");
				}else {
					appLog.error("Contact "+SmokeContact3FirstName+" "+SmokeContact3LastName+" is not invited successfuly");
					sa.assertTrue(false, "Contact "+SmokeContact3FirstName+" "+SmokeContact3LastName+" is not invited successfuly");
				}
				if(fp.inviteContact(environment, mode,SmokeInstitution1, SmokeContact1EmailId,null,FolderType.Standard,null,null, null,null, Workspace.InvestorWorkspace, null)) {
					appLog.info("Contact "+SmokeContact1FirstName+" "+SmokeContact1LastName+" is invited successfuly");
					ThreadSleep(2000);
					switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view.");
					if(click(driver, fp.getcontactaccessremoveLink(Workspace.InvestorWorkspace, EnableDisable.Enable,SmokeContact1EmailId, 10),SmokeContact1EmailId+" contact remove link", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on contact "+SmokeContact1EmailId+" remove link");
						if(click(driver, fp.getContactAccessCancelBtn(Workspace.InvestorWorkspace, 10), "cancel button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on contact access cancel button ");
						}else {
							appLog.error("Not able to click on contact access cancel button so cannot close contact access pop up");
							sa.assertTrue(false, "Not able to click on contact access cancel button so cannot close contact access pop up");
						}
					}else {
						appLog.error("Not able to click on contact "+SmokeContact1EmailId+" remove link so cannot remove contact form contact access pop up");
					}
				}else {
					appLog.error("Contact "+SmokeContact1FirstName+" "+SmokeContact1LastName+" is not invited successfuly");
					sa.assertTrue(false, "Contact "+SmokeContact1FirstName+" "+SmokeContact1LastName+" is not invited successfuly");
				}
				switchToDefaultContent(driver);
				if(fp.inviteContact(environment, mode,null, SmokeContact2EmailId,shrdfolder, FolderType.Shared,"download","Yes", "Yes",null, Workspace.InvestorWorkspace, SmokeContact2EmailId)) {
					appLog.info("Contact "+SmokeContact2FirstName+" "+SmokeContact2LastName+" is invited successfuly from "+shrdfolder);
				}else {
					appLog.error("Contact "+SmokeContact2FirstName+" "+SmokeContact2LastName+" is not invited from "+shrdfolder);
					sa.assertTrue(false, "Contact "+SmokeContact2FirstName+" "+SmokeContact2LastName+" is not invited from "+shrdfolder);
				}
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				if (click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 60), "Manage emails icon",action.SCROLLANDBOOLEAN)) {
					if(selectVisibleTextFromDropDown(driver, fp.getManageEmailContactAccessViewDropDownList(60), "manage emails drop down list", "All Folders")) {
						appLog.info("select all folders from drop down list");
					}else {
						appLog.error("Not able to all folders from drop down list");
						sa.assertTrue(false, "Not able to all folders from drop down list");
					}
					WebElement ele=  FindElement(driver, "//a[text()='"+SmokeContact2FirstName+" "+SmokeContact2LastName+"']", "contact name link", action.BOOLEAN, 10);
					if(click(driver, ele, "contact name link", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on contact name "+SmokeContact2FirstName+" "+SmokeContact2LastName);
						String parent = switchOnWindow(driver);
						if(parent!=null) {
							ele=FindElement(driver, "//h2[contains(text(),'"+SmokeContact2FirstName+" "+SmokeContact2LastName+"')]", "contatc name ", action.BOOLEAN, 30);
							if(ele!=null) {
								appLog.info("Contact page is open ");
								driver.close();
								driver.switchTo().window(parent);
							}else {
								appLog.error("Contact page is not open ");
								sa.assertTrue(false, "Contact page is not open ");
								driver.close();
								driver.switchTo().window(parent);
							}
						}else {
							appLog.error("No new window is open ");
							sa.assertTrue(false, "No new window is open ");
						}

					}else {
						appLog.error("Not able to click on contact name "+SmokeContact2FirstName+" "+SmokeContact2LastName+"  so cannot check contact page");
						sa.assertTrue(false, "Not able to click on contact name "+SmokeContact2FirstName+" "+SmokeContact2LastName+"  so cannot check contact page");
					}
					switchToDefaultContent(driver);
					switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
					List<WebElement> lst=  FindElements(driver, "//div[@id='manageemailgrid_ME']//a[text()='"+SmokeInstitution1+"']","account name list");
					if(click(driver, lst.get(0), "account name link", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on account name of contact "+SmokeContact1FirstName+" "+SmokeContact1LastName);
						String parent = switchOnWindow(driver);
						if(parent!=null) {
							ele=FindElement(driver, "//h2[contains(text(),'"+SmokeInstitution1+"')]", "contatc name ", action.BOOLEAN, 30);
							if(ele!=null) {
								appLog.info("account page is open ");
								driver.close();
								driver.switchTo().window(parent);
							}else {
								appLog.error("account page is not open ");
								sa.assertTrue(false, "account page is not open ");
								driver.close();
								driver.switchTo().window(parent);
							}
						}else {
							appLog.error("No new window is open ");
							sa.assertTrue(false, "No new window is open ");
						}

					}else {
						appLog.error("Not able to click on account name of contact "+SmokeContact2FirstName+" "+SmokeContact2LastName+"  so cannot check account page");
						sa.assertTrue(false, "Not able to click on account name of contact "+SmokeContact2FirstName+" "+SmokeContact2LastName+"  so cannot check account page");
					}
					String parentid=null;
					switchToDefaultContent(driver);
					switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
					if (click(driver, fp.getManageEmailInvitationEmailTemplateEditPreviewTextList().get(0), "Edit ",action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on template edit link ");
						if (fp.getManageEmailEditNotRegisteredClickHereLink(20) != null) {
							click(driver, fp.getManageEmailEditNotRegisteredClickHereLink(30),
									"Not Registered Click here Link", action.SCROLLANDTHROWEXCEPTION);
							parentid = switchOnWindow(driver);
							sa.assertTrue(getURL(driver, 60).contains("SiteRegisteration"),
									"Registration Page is not open after clicking on Resgister Click Here Link.");
							driver.close();
							driver.switchTo().window(parentid);
							switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));

						} else {
							appLog.info(
									"Not Registered Click Here Link is not clickable on Manage Email Invitation Edit Pop Up.");
							sa.assertTrue(false,
									"Not Registered Click Here Link is not clickable on Manage Email Invitation Edit Pop Up.");
							driver.close();
							driver.switchTo().window(parentid);
							switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
						}
						if (fp.getManageEmailEditRegisteredClickHereLink(30) != null) {
							click(driver, fp.getManageEmailEditRegisteredClickHereLink(30),
									"Not Registered Click here Link", action.SCROLLANDTHROWEXCEPTION);
							parentid = switchOnWindow(driver);
							sa.assertTrue(getURL(driver, 60).contains("IP_login"),
									"Registration Page is not open after clicking on Resgister Click Here Link.");
							driver.close();
							driver.switchTo().window(parentid);
							switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));

						} else {
							appLog.info(
									"Registered Click Here Link is not clickable on Manage Email Invitation Edit Pop Up.");
							sa.assertTrue(false,
									"Registered Click Here Link is not clickable on Manage Email Invitation Edit Pop Up.");
						}
						if(click(driver, fp.getManageEmailInvitationTextBoxCancelButton(10), "cancel button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on cancel button and edit template pop up is closed");

						}else {
							appLog.error("Not able to click on cancel buton so cannot close edit template pop up");
							sa.assertTrue(false, "Not able to click on cancel buton so cannot close edit template pop up");
						}

					}else {
						appLog.error("Not able to click on edit link so cannot check template in edit mode");
						sa.assertTrue(false, "Not able to click on edit link so cannot check template in edit mode");
					}
					if (click(driver, fp.getManageEmailInvitationEmailTemplateEditPreviewTextList().get(1), "Edit ",action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on template preview link ");

						if (fp.getManageEmailPreviewNotRegisteredClickHereLink(20) != null) {
							if (click(driver, fp.getManageEmailPreviewNotRegisteredClickHereLink(30),
									"Not Registered Click here Link", action.SCROLLANDBOOLEAN)) {
								parentid = switchOnWindow(driver);
								sa.assertTrue(getURL(driver, 60).contains("SiteRegisteration"),
										"Registration Page is not open after clicking on Resgister Click Here Link.");
								driver.close();
								driver.switchTo().window(parentid);
								switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
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
								parentid = switchOnWindow(driver);
								sa.assertTrue(getURL(driver, 60).contains("IP_login"),
										"Registration Page is not open after clicking on Resgister Click Here Link.");
								driver.close();
								driver.switchTo().window(parentid);
								switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
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
						if (click(driver, fp.getManageEmailPreviewCloseIcon(30), "Close Button",action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on close icon and preview popup is closed");
						} else {
							appLog.info("Not able to click on close button of preview popup");
							sa.assertTrue(false, "Not able to click on close button of preview popup");
						}
					}else {
						appLog.error("Not able to click on preview link so cannot check template in preview mode");
						sa.assertTrue(false, "Not able to click on preview link so cannot check template in preview mode");
					}
					if (click(driver, fp.getManageEmailCustomRadioButton(60), "Custom template radio button",action.SCROLLANDBOOLEAN)) {
						if(click(driver, fp.getManageEmailCustomCloseIcon(10), "custom cross icon", action.BOOLEAN)) {
							appLog.info("clicked on custom email close icon");

						}else {
							appLog.error("Not able to click on close icon so cannot close custom email popup");
							sa.assertTrue(false, "Not able to click on close icon so cannot close custom email popup");
						}

					}else {
						appLog.error("Not able to click on custom radio button so cannot check custom template popup");
						sa.assertTrue(false, "Not able to click on custom radio button so cannot check custom template popup");
					}

					if (click(driver, fp.getManageEmailCustomPreviewLink(30), "Custom Email Preview Link",
							action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getManageEmailCustomPreviewCloseIcon(20), "Custom Email Id Close icon",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on cross icon ");
						} else {
							appLog.info("Not able to click on close icon");
							sa.assertTrue(false, "Not able to click on close icon");
						}
					}else {
						appLog.info("Not able to click on preview link");
						sa.assertTrue(false, "Not able to click on preview link");
					}
					if (fp.verifyManageEmailGrid(SmokeContact2FirstName + " " + SmokeContact2LastName,
							SmokeContact2EmailId, SmokeInstitution1, "Last Invite Date")) {
						appLog.info("Last invitation date for the contact: " + SmokeContact2FirstName+ " " + SmokeContact2LastName+" is verified ");
					} else {
						appLog.info("Last invitation date for the contact: " + SmokeContact2FirstName+ " " + SmokeContact2LastName+" is verified ");
						sa.assertTrue(false, "Last invitation date for the contact: " + SmokeContact2FirstName+ " " + SmokeContact2LastName+" is verified ");
					}
					if (click(driver, fp.getManageEmailCancelBtn(30), "manage email cancel button", action.SCROLLANDBOOLEAN)) {
						appLog.error("click on cancel button");
					}else {
						appLog.error("Not able to click on cancel button so cannot close manage emails popup");
						sa.assertTrue(false, "Not able to click on cancel button so cannot close manage emails popup");
					}
				} else {
					appLog.info("Not able to click on manage emails icon");
					sa.assertTrue(false, "Not able to click on manage emails icon");
				}
				
			}else {
				appLog.error(" Not able to click on created fund "+SmokeFundName1+" so cannot invite contatcs");
				sa.assertTrue(false, " Not able to click on created fund "+SmokeFundName1+" so cannot invite contatcs");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contacts");
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contacts");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	

	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc026_addRenameAndDeleteFolderFromManageFolder(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String[] standrdFolder=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath).split("<break>");
		String shrdFolder=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		lp.CRMLogin(SmokeCRMUser2Email, SmokePassword);
		if (lp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(SmokeFundName1)) {
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver,fp.getInvestorWorkSpaceSection(30) , "investor workspace section");
				if(click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 30), "Manage folder icon", action.BOOLEAN)){
					if(fp.createFolderStructure(standrdFolder[0], FolderType.Common, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
						appLog.info(standrdFolder[0]+ " folder structure is created.");
					} else {
						appLog.error(standrdFolder[0]+ " folder structure is not created.");
						sa.assertTrue(false,standrdFolder[0]+ " folder structure is not created.");
					}
					String id=null;
					id=fp.getCreatedFolderId(standrdFolder[1], PageName.ManageFolderPopUp, 20);
					if(id!=null) {
						if(fp.clickOnRenameFolderButton(id)) {
							if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,20),standrdFolder[2],standrdFolder[1]+" parent rename folder text box", action.BOOLEAN)) {
								if(click(driver,fp.getParentRenameFolderSaveButton(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20) ,standrdFolder[1]+"rename sub folder save button", action.BOOLEAN)) {
									appLog.info("clicked on parent rename folder save button");
									ThreadSleep(2000);
								
								}else {
									appLog.error("Not able to click on folder "+standrdFolder[1]+" save button so cannot Update Folder Name");
									sa.assertTrue(false, "Not able to click on folder "+standrdFolder[1]+" save button so cannot Update Folder Name");
								}
							}else {
								appLog.error("Not able to pass value in "+standrdFolder[1]+" rename folder text box so cannot Update folder Name");
								sa.assertTrue(false, "Not able to pass value in "+standrdFolder[1]+" rename folder text box so cannot Update folder Name");
							}
							
						}else {
							appLog.error("Not able to click on "+standrdFolder[1]+" rename icon so cannot update folder Name");
							sa.assertTrue(false, "Not able to click on "+standrdFolder[1]+" rename icon so cannot update folder Name");
						}
					}else {
						appLog.error(standrdFolder[1]+" folder is not available in manage folder so cannot Update folder Name");
						sa.assertTrue(false, standrdFolder[1]+" folder is not available in manage folder so cannot Update folder Name");
					}
					id=null;
					id=fp.getCreatedFolderId(shrdFolder, PageName.ManageFolderPopUp, 20);
					if(id!=null) {
						if(fp.clickOnDeleteFolderButton(id)) {
							
							if(click(driver, fp.getFolderDeleteYesBtn(Workspace.InvestorWorkspace,PageName.ManageFolderPopUp, 10), "folder delete yes Button", action.BOOLEAN)) {
								appLog.info("clicked yes button");
							}else {
								appLog.error("No able to click on yes button so cannot delete folder "+shrdFolder);
								sa.assertTrue(false, "No able to click on yes button so cannot delete folder "+shrdFolder);
							}
						}else {
							appLog.error("Not able to click on folder: "+shrdFolder+" delete icon so cannot check delete folder functionality");
							sa.assertTrue(false, "Not able to click on folder: "+shrdFolder+" delete icon so cannot check delete folder functionality");
						}
					}else {
						appLog.error(shrdFolder+" is not available in the manage folder structure so cannot click on folder "+shrdFolder+" delete icon");
						sa.assertTrue(false, shrdFolder+" is not available in the manage folder structure so cannot click on folder "+shrdFolder+" delete icon");
					}
					if(click(driver, fp.getManageFolderCloseButton(Workspace.InvestorWorkspace, 20), "close button", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on manage Folder close button");
					}else {
						appLog.error("Not able to click on manage folder close button so cannot close manage folder pop up");
						sa.assertTrue(false, "Not able to click on manage folder close button so cannot close manage folder pop up");
					}
				}else {
					appLog.error("Not able to click on Manage folder icon so cannot delete Common and shared folders ");
					sa.assertTrue(false, "Not able to click on Manage folder icon so cannot delete Common and shared folders ");
				}
			}else {
				appLog.error("could not find "+SmokeFundName1);
				sa.assertTrue(false, "could not find fund "+SmokeFundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab");
			sa.assertTrue(false, "Not able to click on fund tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc027_addRemoveRenameInvestorFromManageInvestor(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		String errorMsg=FundsPageErrorMessage.filterPleaseSelectAFieldErroresage;
		lp.CRMLogin(SmokeCRMUser2Email, SmokePassword);
		if (lp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(SmokeFundName1)) {
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver,fp.getInvestorWorkSpaceSection(30) , "investor workspace section");
				if (click(driver, fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60), "Manage Investor icon",action.SCROLLANDBOOLEAN)) {
						ThreadSleep(5000);
						if (click(driver,fp.getManageInvestorFilterApplyButton(Workspace.InvestorWorkspace, 60),"Apply buton", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on filter apply button");
							if (bp.verifyErrorMessageOnPage(errorMsg,fp.getManageInvestorFilterfieldSelectErrorMessage(Workspace.InvestorWorkspace).get(0),"Please select a field error Message in filter")) {
								appLog.info("Please select a field Error Message is verified at filter");
							} else {
								sa.assertTrue(false,"Please select a field error message is not verified at filter.Expected:"+ errorMsg + " Actual"+ getText(driver,fp.getManageInvestorFilterfieldSelectErrorMessage(Workspace.InvestorWorkspace).get(0),"Please select a field error Message in filter",action.BOOLEAN));
							}
						}else {
							appLog.error("Not able to click on filter apply button so cannot check error message "+errorMsg);
							sa.assertTrue(false, "Not able to click on filter apply button so cannot check error message "+errorMsg);
						}
						if (click(driver,fp.getManageInvestorFilterClearButton(Workspace.InvestorWorkspace, 60),"Clear button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on filter clear button and error message is not displaying ");
							
						}else {
							appLog.error("Not able to click on filter clear button so cannot check clear button functionality");
							sa.assertTrue(false, "Not able to click on filter clear button so cannot check clear button functionality");
						}
					
						if(click(driver, fp.getManageInvestorFilterAddRowLink(Workspace.InvestorWorkspace, 60), "add row link", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on add rows link ");
							if(click(driver, fp.getManageInvestorFilterRemoveRowIcon(Workspace.InvestorWorkspace).get(0), "delete added row cross icon", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on delete row cross icon");
							}else {
								appLog.error("Not able to click on delete row cross icon so cannot delete added row");
								sa.assertTrue(false, "Not able to click on delete row cross icon so cannot delete added row");
							}
						}else {
							appLog.error("Not able to click on add row link so cannot add more rows in filter investor");
							sa.assertTrue(false, "Not able to click on add row link so cannot add more rows in filter investor");
						}
						
						if(fp.selectInstitutionOrLP(SmokeInstitution2+"/"+SmokeLimitedPartner2, Workspace.InvestorWorkspace,20)) {
							appLog.info("Investor add successfully : "+SmokeInstitution2+"/"+SmokeLimitedPartner2);
							
							if(click(driver, fp.getManageInvestorAddedPopupCloseButton(Workspace.InvestorWorkspace, 20), "manage investor close button", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on manage investor added close button");
								ThreadSleep(3000);
								if(fp.selectInstitutionOrLP(SmokeInstitution2+"/"+SmokeLimitedPartner2, Workspace.InvestorWorkspace,20)) {
									appLog.info("Investor removed successfully "+SmokeInstitution2);
									ThreadSleep(3000);
									if(click(driver, fp.getManageInvestorDeletedPopupCloseButton(Workspace.InvestorWorkspace, 20), "manage investor close button", action.SCROLLANDBOOLEAN)) {
										appLog.info("clicked on manage investor remove close button");
										ThreadSleep(3000);
										
									}else {
										appLog.error("Not able to clicked on manage investor remove close button");
										sa.assertTrue(false, "Not able to clicked on manage investor remove close button");
									}
									
								}else {
									appLog.error("Not able to remove investor : "+SmokeInstitution2+"/"+SmokeLimitedPartner2);
									sa.assertTrue(false, "Not able to remove investor : "+SmokeInstitution2+"/"+SmokeLimitedPartner2);
								}
								
								
							}else {
								appLog.error("Not able to clicked on manage investor close button");
								sa.assertTrue(false, "Not able to clicked on manage investor close button");
							}
						}else {
							appLog.error("Not able to select investor : "+SmokeInstitution2+"/"+SmokeLimitedPartner2);
							sa.assertTrue(false, "Not able to select investor : "+SmokeInstitution2+"/"+SmokeLimitedPartner2);
						}
						
						if (fp.clickOnRenameManageTargetLimitedPartner(SmokeInstitution1, SmokeLimitedPartner1)) {
							appLog.info("clicked on Rename icon for institution" + SmokeInstitution1);
							ThreadSleep(3000);
							if (sendKeys(driver,fp.getManageInvestorRenamePopupTextBox(Workspace.InvestorWorkspace, 60),SmokeLimitedPartner1+"INV", "Text Box ", action.SCROLLANDBOOLEAN)) {
								appLog.info("passed value in rename manage investor text box:  "+SmokeLimitedPartner1+"INV");
								ExcelUtils.writeData(smokeExcelPath, SmokeLimitedPartner1+"INV", "Limited Partner", excelLabel.Variable_Name,"SmokeLP1", excelLabel.UpdatedLimitedPartner_NameFormManageInvestor);
								if (click(driver,fp.getManageInvestorRenamePopupApplyButton(Workspace.InvestorWorkspace, 60),"Apply button", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on apply button ");
								}else {
									appLog.error("Not able to click on apply button so cannot update manage investor name "+SmokeLimitedPartner1);
									sa.assertTrue(false, "Not able to click on apply button so cannot update manage investor name "+SmokeLimitedPartner1);
								}
							}else {
								appLog.error("Not able to pass value in rename text box so cannot update institution name "+SmokeLimitedPartner1);
								sa.assertTrue(false, "Not able to pass value in rename text box so cannot update institution name "+SmokeLimitedPartner1);
							}
							
							
						} else {
							appLog.info("Rename icon is not visible for institution" + SmokeLimitedPartner1+" so cannot update name ");
							sa.assertTrue(false, "Rename icon is not visible for institution" + SmokeLimitedPartner1+" so cannot update name ");
						}
						
						if(click(driver, fp.getManageInvestorDoneButton(Workspace.InvestorWorkspace, 20), "manage investor done button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on manage investor done button");
						}else {
							appLog.error("Not able to click on manage investor done button");
							sa.assertTrue(false, "Not able to click on manage investor done button");
						}

					
				}else {
					appLog.error("Not able to click on manage investor so cannot check manage investor functionality and update manage investor name ");
					sa.assertTrue(false, "Not able to click on manage investor so cannot check manage investor functionality and update manage investor name ");
				}
				
			}else {
				appLog.error("could not find "+SmokeFundName1);
				sa.assertTrue(false, "could not find fund "+SmokeFundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab");
			sa.assertTrue(false, "Not able to click on fund tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc028_uploadDocumentCRMSide(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String standrdFolder=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String shrdFolder=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String commonpath=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		lp.CRMLogin(SmokeCRMUser2Email,SmokePassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(SmokeFundName1)) {
				String common_docpath="UploadFiles\\SmokeUploadFile\\Common";
				if(fp.uploadFileBulk(SmokeCRMUser2Email, smokeExcelPath, commonpath, null, common_docpath, UploadFileActions.SelectAll, UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)){
					appLog.info("Successfully uploaded file to "+commonpath);
					appLog.info("File is upload successfullly");
					
				} else {
					appLog.error("Not able to upload file in "+commonpath);
					sa.assertTrue(false,"Not able to upload file in "+commonpath);
				}
				
				String shrd_docpath="UploadFiles\\SmokeUploadFile\\Shared";
				if(fp.uploadFileBulk(SmokeCRMUser2Email, smokeExcelPath, shrdFolder, null, shrd_docpath, UploadFileActions.SelectAll, UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)){
					appLog.info("Successfully uploaded file to "+shrdFolder);
					appLog.info("File is upload successfullly");
				
				} else {
					appLog.error("Not able to upload file in "+shrdFolder);
					sa.assertTrue(false,"Not able to upload file in "+shrdFolder);
				}
				
				String std_docpath="UploadFiles\\SmokeUploadFile\\Standard";
				if(fp.uploadFileBulk(SmokeCRMUser2Email, smokeExcelPath, standrdFolder, SmokeInstitution1+"/"+UpdatedSmokeLP1, std_docpath, UploadFileActions.SelectAll, UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)){
					appLog.info("Successfully uploaded file to "+standrdFolder);
					appLog.info("File is upload successfullly");
					
					
				} else {
					appLog.error("Not able to upload file in "+standrdFolder);
					sa.assertTrue(false,"Not able to upload file in "+standrdFolder);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+SmokeFundName1+" so cannot upload files in Investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+SmokeFundName1+" so cannot upload files in Investor workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in Investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in Investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc029_importOnlineDocument(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderpath=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String fileName=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
		String docPath=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.OnlineImportPath);;
		lp.CRMLogin(SmokeCRMUser2Email,SmokePassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(SmokeFundName1)) {
				if(fp.onlineImport(SmokeInstitution1, UpdatedSmokeLP1, null,folderpath,docPath,fileName, SmokeBoxUserName, SmokeBoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Standard, PageName.FundsPage, Workspace.InvestorWorkspace,20)) {
					appLog.info("file is imported successfully: "+fileName+" in :"+folderpath);
				}else {
					appLog.error("file is not imported: "+fileName+" in :"+folderpath);
					sa.assertTrue(false, "file is not imported: "+fileName+" in :"+folderpath);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+SmokeFundName1+" so cannot import files in Investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+SmokeFundName1+" so cannot import files in Investor workspace");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot import files in Investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot import files in Investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc030_1_approvePendingDocumentAndCheckUI(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		String filesStandard = ExcelUtils.readData(smokeExcelPath,"FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
		String std = ExcelUtils.readData(smokeExcelPath,"FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String parentID=null;
		lp.CRMLogin(SmokeCRMUser2Email, SmokePassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(SmokeFundName1)) {
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approval icon fundraising workspace", action.SCROLLANDBOOLEAN)) {
					
					if (fp.clickOnDocumentManageApprovals(ManageApprovalTabs.PendingDocuments, filesStandard.split("<break>")[0], 30, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30))){
						parentID = switchOnWindow(driver);
						if (parentID != null) {
							if (fp.getDownloadLink(60) != null) {
								appLog.info("Download Button is displaying");
							} else {
								appLog.info("Document Download Button is not displaying");
								sa.assertTrue(false, "Document Download Button is not displaying");
							}
							driver.close();
							driver.switchTo().window(parentID);
							switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
						}else {
							appLog.error("No new window is open so cannot check document");
							sa.assertTrue(false, "No new window is open so cannot check document");
						}
					}else {
						appLog.error("Not able to click on file Name "+filesStandard.split("<break>")[0]);
						sa.assertTrue(false, "Not able to click on file Name "+filesStandard.split("<break>")[0]);
					}
					
					WebElement ele = fp.checkboxForFileInManageApprovals(filesStandard.split("<break>")[0],SmokeFundName1+" > "+SmokeInstitution1+" > "+UpdatedSmokeLP1+" > "+std);
					if(ele!=null) {
						if(click(driver, ele, filesStandard.split("<break>")[0]+" check box", action.BOOLEAN)) {
							appLog.info("clicked on check box "+filesStandard.split("<break>")[0]);
							ThreadSleep(2000);
							for(int i=0; i<2; i++) {
								if (click(driver, fp.getdeleteBtnManageApprovals(60), "delete Btn Manage Approvals", action.BOOLEAN)) {
									appLog.info("clicked on delete button");
									if(i==0) {
										if (click(driver, fp.getManageApprovalDelYesorNo(YesNo.No,60), "manage approval del No button", action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on No button");
										}else {
											appLog.error("delete popup No button is not clickable");
											sa.assertTrue(false, "delete popup No button is not clickable");
										}
									}
									if(i==1) {
										if (click(driver, fp.getManageApprovalDelYesorNo(YesNo.Yes,60), "manage approval del yes button", action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on yes button");
											ThreadSleep(2000);
											if (fp.checkboxForFileInManageApprovals(filesStandard.split("<break>")[0])==null) {
												appLog.info("file is deleted successfully from pending document");
											}else {
												appLog.error("file is not deleted from pending document");
												sa.assertTrue(false, "file is not deleted from pending document");
											}
										}else {
											appLog.error("delete popup yes button is not clickable");
											sa.assertTrue(false, "delete popup yes button is not clickable");
										}
									}
								}else {
									appLog.error("delete button is not clickable");
									sa.assertTrue(false, "delete button is not clickable");
								}
							}
						}else {
							appLog.error("Not able to click on file Name check box");
							sa.assertTrue(false, "Not able to click on file Name check box");
						}
					}else {
						appLog.error(filesStandard.split("<break>")[0]+" file is not visible so cannot click on check box");
						sa.assertTrue(false, filesStandard.split("<break>")[0]+" file is not visible so cannot click on check box");
					}
					
					if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.PendingDocuments, 30), filesStandard.split("<break>")[1], "search box pending tabs", action.BOOLEAN)) {
						appLog.error("passed value in search text box "+filesStandard.split("<break>")[1]);
						ThreadSleep(3000);
						if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.PendingDocuments, 30), "pending docs manage approvals", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Manage Approvals search icon");
							ThreadSleep(2000);
							if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.PendingDocuments,filesStandard.split("<break>")[1],SmokeFundName1+" > "+SmokeInstitution1+" > "+UpdatedSmokeLP1+" > "+std, "pending", SmokeCRMUser2FirstName+" "+SmokeCRMUser2LastName, SmokeFirmName, date)) {
								appLog.info(filesStandard.split("<break>")[1] + " is successfully searched ");
							}else {
							appLog.error(filesStandard.split("<break>")[1] + "could not be verified after search");
							sa.assertTrue(false, filesStandard.split("<break>")[1] + "could not be verified after search");
							}
							ThreadSleep(2000);
							if(click(driver, fp.getClearIconOnManageApprovalsSearchBox(ManageApprovalTabs.PendingDocuments, 20), "clear icon", action.BOOLEAN)) {
								appLog.info("clicked on search text box ");
							}else {
								appLog.error("Not able to click on search text box clear icon so cannot cleared searched document");
								sa.assertTrue(false, "Not able to click on search text box clear icon so cannot cleared searched document");
							}
							
						}else {
							appLog.error("Not able to click on manage approvals search icon so cannot search document "+filesStandard.split("<break>")[1]);
							sa.assertTrue(false, "Not able to click on manage approvals search icon so cannot search document "+filesStandard.split("<break>")[1]);
						}
						
					}else {
						appLog.error("Not able to pass value in search text box "+filesStandard.split("<break>")[1]+" so cannot search file in manage approvals");
						sa.assertTrue(false, "Not able to pass value in search text box "+filesStandard.split("<break>")[1]+" so cannot search file in manage approvals");
					}
					WebElement alldocCheckBox=fp.getManageApprovalsAllDocumentSelectCheckBox(10);
					if(alldocCheckBox!=null) {
						if(click(driver, alldocCheckBox," header check box", action.BOOLEAN)) {
							appLog.info("clicked on header check box ");
							ThreadSleep(2000);
							for(int i=0; i<2; i++) {
								if (click(driver, fp.getApproveBtnManageApprovals(60), "approve Btn Manage Approvals", action.BOOLEAN)) {
									appLog.info("clicked on a button");
									if(i==0) {
										if (click(driver, fp.getManageApprovalApproveYesOrNo(YesNo.No, 10), "manage approval no button", action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on no button");
										}else {
											appLog.error("approve popup No button is not clickable");
											sa.assertTrue(false, "approve popup No button is not clickable");
										}
									}
									if(i==1) {
										if (click(driver, fp.getManageApprovalsConfirmYesBtn(10), "manage approval del yes button", action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on yes button");
											ThreadSleep(2000);
											if(click(driver, fp.getManageApprovalsCloseBtn(20), "manage approvals close button", action.SCROLLANDBOOLEAN)) {
												appLog.info("clicked on close button");
												ThreadSleep(2000);
												if (fp.noDataToDisplay(ManageApprovalTabs.PendingDocuments, 30)!=null) {
													appLog.info("no data to display is successfully displayed");
													
												}else {
													appLog.error("No data to display Error Message is not displaying after approve all document");
													sa.assertTrue(false, "No data to display Error Message is not displaying after approve all document");
												}
											}else {
												appLog.error("Not able to click on manage approvals close button");
												sa.assertTrue(false,"Not able to click on manage approvals close button");
											}
										}else {
											appLog.error("delete popup yes button is not clickable");
											sa.assertTrue(false, "delete popup yes button is not clickable");
										}
									}
								}else {
									appLog.error("delete button is not clickable");
									sa.assertTrue(false, "delete button is not clickable");
								}
							}
						}else {
							appLog.error("Not able to click on header check box");
							sa.assertTrue(false, "Not able to click on header check box");
						}
					}else {
						appLog.error("Not able to click on header check box so cannot select all file");
						sa.assertTrue(false,"Not able to click on header check box so cannot select all file");
					}
					if (click(driver, fp.getApprovedDocsTab(60),"approved docs tab", action.SCROLLANDBOOLEAN)) {
						ThreadSleep(2000);
						if (sendKeys(driver, fp.getSearchTextboxManageApp(ManageApprovalTabs.ApprovedDocuments, 30), filesStandard.split("<break>")[1], "search box pending tabs", action.BOOLEAN)) {
							appLog.error("passed value in search text box "+filesStandard.split("<break>")[1]);
							ThreadSleep(3000);
							if (click(driver, fp.getSearchIconManageApprovalsPopup(ManageApprovalTabs.ApprovedDocuments, 30), "pending docs manage approvals", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on Manage Approvals search icon");
								ThreadSleep(2000);
								if (fp.verifyFilesPresentInManageApprovals(Workspace.FundraisingWorkspace, ManageApprovalTabs.ApprovedDocuments,filesStandard.split("<break>")[1], SmokeFundName1+" > "+SmokeInstitution1+" > "+UpdatedSmokeLP1+" > "+std, "Approved", SmokeCRMUser2FirstName+" "+SmokeCRMUser2LastName, SmokeFirmName, date)) {
									appLog.info(filesStandard.split("<break>")[1] + " is successfully searched ");
								}else {
								appLog.error(filesStandard.split("<break>")[1] + "could not be verified after search");
								sa.assertTrue(false, filesStandard.split("<break>")[1] + "could not be verified after search");
								}
								ThreadSleep(2000);
								if(click(driver, fp.getClearIconOnManageApprovalsSearchBox(ManageApprovalTabs.ApprovedDocuments, 20), "clear icon", action.BOOLEAN)) {
									appLog.info("clicked on search text box ");
								}else {
									appLog.error("Not able to click on search text box clear icon so cannot cleared searched document");
									sa.assertTrue(false, "Not able to click on search text box clear icon so cannot cleared searched document");
								}
								
							}else {
								appLog.error("Not able to click on manage approvals search icon so cannot search document "+filesStandard.split("<break>")[1]);
								sa.assertTrue(false, "Not able to click on manage approvals search icon so cannot search document "+filesStandard.split("<break>")[1]);
							}
							
						}else {
							appLog.error("Not able to pass value in search text box "+filesStandard.split("<break>")[1]+" so cannot search file in manage approvals");
							sa.assertTrue(false, "Not able to pass value in search text box "+filesStandard.split("<break>")[1]+" so cannot search file in manage approvals");
						}
						
						
						
						ThreadSleep(2000);
						if (fp.clickOnDocumentManageApprovals(ManageApprovalTabs.ApprovedDocuments, filesStandard.split("<break>")[1], 30, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30))){
							parentID = switchOnWindow(driver);
							if (parentID != null) {
								if (fp.getDownloadLink(60) != null) {
									appLog.info("Download Button is displaying");
								} else {
									appLog.info("Document Download Button is not displaying");
									sa.assertTrue(false, "Document Download Button is not displaying");
								}
								driver.close();
								driver.switchTo().window(parentID);
								switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
							}else {
								appLog.error("No new window is open so cannot check document");
								sa.assertTrue(false, "No new window is open so cannot check document");
							}
						}else {
							appLog.error("Not able to click on file Name "+filesStandard.split("<break>")[0]);
							sa.assertTrue(false, "Not able to click on file Name "+filesStandard.split("<break>")[0]);
						}
						
						
					}else {
						appLog.error("approved docs tab is not clickable on manage approvals popup");
						sa.assertTrue(false, "approved docs tab is not clickable on manage approvals popup");
					}
				}else {
					appLog.error("Not able to click on manage approvals icon so cannot approve pending document");
					sa.assertTrue(false, "Not able to click on manage approvals icon so cannot approve pending document");
				}
			}else {
				appLog.error("Not able to click on created fund so cannot approve pending document");
				sa.assertTrue(false, "Not able to click on created fund so cannot approve pending document");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot approve pending documents");
			sa.assertTrue(false, "Not able to click on fund tab so cannot approve pending documents");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc030_2_updateDocumentCRMSideAndCheckDuplicatePopUp(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String commonpath=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String folderpath=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		lp.CRMLogin(SmokeCRMUser2Email,SmokePassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(SmokeFundName1)) {
				
				String common_docpath="UploadFiles\\SmokeUploadFile\\Common";
				
				if(fp.uploadFileBulk(SmokeCRMUser2Email, smokeExcelPath, commonpath, null, common_docpath, UploadFileActions.SelectAll, UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)){
					appLog.info("Successfully uploaded file to "+commonpath);
					appLog.info("File is upload successfullly");
					
				} else {
					appLog.error("Not able to upload file in "+commonpath);
					sa.assertTrue(false,"Not able to upload file in "+commonpath);
				}
				
				if(fp.uploadFileBulk(SmokeCRMUser2Email, smokeExcelPath, commonpath, null, common_docpath, UploadFileActions.SelectAll, UploadFileActions.Update, Workspace.InvestorWorkspace, PageName.FundsPage, 30)){
					appLog.info("Successfully uploaded file to "+commonpath);
					appLog.info("File is upload successfullly");
					
				} else {
					appLog.error("Not able to upload file in "+commonpath);
					sa.assertTrue(false,"Not able to upload file in "+commonpath);
				}
				
				String std_docpath="UploadFiles\\SmokeUploadFile\\Standard";
				if(fp.uploadFileBulk(SmokeCRMUser2Email, smokeExcelPath, folderpath, SmokeInstitution1+"/"+UpdatedSmokeLP1, std_docpath, UploadFileActions.SelectAll, UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)){
					appLog.info("Successfully uploaded file to "+folderpath);
					appLog.info("File is upload successfullly");
					
					
				} else {
					appLog.error("Not able to upload file in "+folderpath);
					sa.assertTrue(false,"Not able to upload file in "+folderpath);
				}
				
				if(fp.uploadFileBulk(SmokeCRMUser2Email, smokeExcelPath, folderpath, SmokeInstitution1+"/"+UpdatedSmokeLP1, std_docpath, UploadFileActions.SelectAll, UploadFileActions.Update, Workspace.InvestorWorkspace, PageName.FundsPage, 30)){
					appLog.info("Successfully uploaded file to "+folderpath);
					appLog.info("File is upload successfullly");
					
					
				} else {
					appLog.error("Not able to upload file in "+folderpath);
					sa.assertTrue(false,"Not able to upload file in "+folderpath);
				}
				ThreadSleep(5000);
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				if (click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approval icon fundraising workspace", action.SCROLLANDBOOLEAN)) {
					ThreadSleep(2000);
					if(selectVisibleTextFromDropDown(driver, fp.getManageAppPendingDropdown(60), "drop down list", "All Pending Documents")) {
						WebElement alldocCheckBox=fp.getManageApprovalsAllDocumentSelectCheckBox(10);
						if(alldocCheckBox!=null) {
							if(click(driver, alldocCheckBox," header check box", action.BOOLEAN)) {
								appLog.info("clicked on header check box ");
								ThreadSleep(2000);
								if (click(driver, fp.getApproveBtnManageApprovals(60), "approve Btn Manage Approvals", action.BOOLEAN)) {
									appLog.info("clicked on a button");
									ThreadSleep(3000);
									if (click(driver, fp.getManageApprovalsConfirmYesBtn(10), "manage approval del yes button", action.SCROLLANDBOOLEAN)) {
										appLog.info("clicked on yes button");
										ThreadSleep(3000);
										if(click(driver, fp.getManageApprovalsUpdateAllDocument(20), "update all document", action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on update all button");
											ThreadSleep(3000);
											if(click(driver, fp.getManageApprovalsApprovedUpdatedDocumentCloseBtn(30), "close button", action.SCROLLANDBOOLEAN)) {
												appLog.info("clicked on close button");
											}else {
												appLog.error("Not able to click on manage approvals close button");
												sa.assertTrue(false, "Not able to click on manage approvals close button");
												
											}
										}else {
											appLog.error("Not able to click on manage approvals update all button");
											sa.assertTrue(false, "Not able to click on manage approvals update all button");
										}
									}else {
										appLog.error("delete popup yes button is not clickable");
										sa.assertTrue(false, "delete popup yes button is not clickable");
									}
									
								}else {
									appLog.error("delete button is not clickable");
									sa.assertTrue(false, "delete button is not clickable");
								}
							}else {
								appLog.error("Not able to click on header check box");
								sa.assertTrue(false, "Not able to click on header check box");
							}
						}else {
							appLog.error("Not able to find document check box so cannot approve pending document and check duplicate pop up on manage approvals pop up");
							sa.assertTrue(false, "Not able to find document check box so cannot approve pending document and check duplicate pop up on manage approvals pop up");
						}
						
					}else {
						appLog.error("Not able to select All Pending Documents from drop down list so cannot approve document");
						sa.assertTrue(false, "Not able to select All Pending Documents from drop down list so cannot approve document");
					}
					
				}else {
					appLog.error("Not able to click on manage approvals icon");
					sa.assertTrue(false, "Not able to click on manage approvals icon");
				}
			}else {
				appLog.error("Not able to click on created Fund: "+SmokeFundName1+" so cannot upload files in Investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+SmokeFundName1+" so cannot upload files in Investor workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in Investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in Investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	
	
	
	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc031_registerInvestorAndCheckFolder(String environment, String mode) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		String[] Stdpath = ExcelUtils.readData(smokeExcelPath,"FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath).split("<break>");
		String[] Shrdpath = ExcelUtils.readData(smokeExcelPath,"FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath).split("<break>");
		String[] CommonPath = ExcelUtils.readData(smokeExcelPath,"FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath).split("<break>");
		String[] stdFile = ExcelUtils.readData(smokeExcelPath,"FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard).split("<break>");
		String docpath="UploadFiles\\SmokeUploadFile\\Investor Side";
		if (ExcelUtils.readData(smokeExcelPath,"Contact", excelLabel.Variable_Name,"SmokeC2", excelLabel.Registered).equalsIgnoreCase("Not Registered")) {
			try {
				investorRegLink = new EmailLib().getInvestorRegLink("InvitationMail",SmokeGmailUserName,SmokeGmailPassword,SmokeCRMUser2Email,SmokeContact2EmailId);
				appLog.info("Investor Registration Link: " + investorRegLink);
			} catch (InterruptedException e) {
				appLog.info("Invitation mail not found.");
				e.printStackTrace();
			}
			if (investorRegLink != null) {
				driver.get(investorRegLink);
				if (bp.targetRegistration(SmokeContact2FirstName,SmokeContact2LastName, SmokeContact2EmailId,SmokeInstitution1,SmokePassword)) {
					appLog.info(
							"Investor is registered successfully: " + SmokeContact2FirstName + " " + SmokeContact2LastName);
					ExcelUtils.writeData(smokeExcelPath,"Registered", "Contact",excelLabel.Variable_Name, "SmokeC2", excelLabel.Registered);
				} else {
					driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
					appLog.info("Investor Target URL through Direct URL..");
					if (bp.targetRegistration(SmokeContact2FirstName, SmokeContact2LastName, SmokeContact2EmailId,
							SmokeInstitution1, SmokePassword)) {
						appLog.info("Investor is registered successfully: " + SmokeContact2FirstName + " "+ SmokeContact2LastName);
						ExcelUtils.writeData(smokeExcelPath,"Registered", "Contact",excelLabel.Variable_Name, "SmokeC2", excelLabel.Registered);
					} else {
						appLog.error("Investor is not Registered through Invited Link: " + SmokeContact2FirstName + " "+ SmokeContact2LastName);
						sa.assertTrue(false, "Investor is not Registered through Invited Link: " + SmokeContact2FirstName+ " " + SmokeContact2LastName);
					}
				}
			} else {
				driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
				appLog.info("Register Investor URL through Direct URL..");
				if (bp.targetRegistration(SmokeContact2FirstName, SmokeContact2LastName, SmokeContact2EmailId,SmokeInstitution1,SmokePassword)) {
					appLog.info("Investor is registered successfully: " + SmokeContact2FirstName + " "+ SmokeContact2LastName);
					ExcelUtils.writeData(smokeExcelPath,"Registered", "Contact",excelLabel.Variable_Name, "SmokeC2", excelLabel.Registered);
				} else {
					appLog.error("Investor is not Registered through Invited Link: " + SmokeContact2FirstName + " "+ SmokeContact2LastName);
					sa.assertTrue(false, "Investor is not Registered through Invited Link: " + SmokeContact2FirstName+ " " + SmokeContact2LastName);
				}
			}
			if(ivp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
				for (int i = 0; i < CommonPath.length; i++) {
					if(fp.verifyFolderPathdummy(CommonPath[i], null, null, null, PageName.CurrentInvestmentPgae, null, 30)) {
						appLog.info("folder structure is verified "+CommonPath[i]);
						
					}else {
						appLog.error("Folder is not available  "+CommonPath[i]+" so cannot verify it ");
						sa.assertTrue(false, "Folder is not available  "+CommonPath[i]+" so cannot verify it ");
					}
				}
				
				for (int i = 0; i < Shrdpath.length; i++) {
					if(fp.verifyFolderPathdummy(Shrdpath[i], null, null, null, PageName.CurrentInvestmentPgae, null, 30)) {
						appLog.info("folder structure is verified "+Shrdpath[i]);
						
					}else {
						appLog.error("Folder is not available  "+Shrdpath[i]+" so cannot verify it ");
						sa.assertTrue(false, "Folder is not available  "+Shrdpath[i]+" so cannot verify it ");
					}
				}
				
				for (int i = 0; i < Stdpath.length; i++) {
					if(fp.verifyFolderPathdummy(Stdpath[i], SmokeInstitution1,UpdatedSmokeLP1, null, PageName.CurrentInvestmentPgae, null, 30)) {
						appLog.info("folder structure is verified "+Stdpath[i]);
						
					}else {
						appLog.error("Folder is not available  "+Stdpath[i]+" so cannot verify it ");
						sa.assertTrue(false, "Folder is not available  "+Stdpath[i]+" so cannot verify it ");
					}
				}
				
				if(fp.verifyFolderPathdummy(Stdpath[0], SmokeInstitution1, UpdatedSmokeLP1, null, PageName.CurrentInvestmentPgae, null, 30)) {
					appLog.info("folder structure is verified "+Stdpath[0]);
					
					if (bp.verifyDownloadFunctionality(PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, stdFile[0], false, false,true)) {
						appLog.info("download button and close button are successfully verified on manage version window");
					}
					else {
						appLog.error("download and close button could not be verified on manage version window");
						sa.assertTrue(false, "download and close button could not be verified on manage version window");
					}
				}else {
					appLog.error("Folder is not available  "+Stdpath[0]+" so cannot verify it ");
					sa.assertTrue(false, "Folder is not available  "+Stdpath[0]+" so cannot verify it ");
				}
				
				if (ivp.uploadUpdateFileInvestorSide(Stdpath[0],stdFile[1],SmokeInstitution1, UpdatedSmokeLP1, FolderType.Standard, docpath, "yes", 30,PageName.CurrentInvestmentPgae, null, null, WorkSpaceAction.UPLOAD)) {
					appLog.info("file is Upload Successfully in Current investment in folder: "+Stdpath[0]);

				}else{
					appLog.error("Upload Unsuccessful in folder "+Stdpath[0]+" file "+stdFile[1]);
					sa.assertTrue(false, "Upload Unsuccessful in folder "+Stdpath[0]+" file "+stdFile[1]);	
				}
				
				if (ivp.uploadUpdateFileInvestorSide(Stdpath[0],stdFile[1], SmokeInstitution1, UpdatedSmokeLP1, FolderType.Standard, docpath, "yes", 30,PageName.CurrentInvestmentPgae, null, null, WorkSpaceAction.UPDATE)) {
					appLog.info("file is updated Successfully in Current investment in folder: "+Stdpath[0]);

				}else{
					appLog.error("Update Unsuccessful in folder "+Stdpath[0]+" file "+stdFile[1]);
					sa.assertTrue(false, "Update Unsuccessful in folder "+Stdpath[0]+" file "+stdFile[1]);	
				}
				
				if(click(driver, ivp.getInvestmentDownArrow(30), "invesment down arrow", action.SCROLLANDBOOLEAN)) {
					ThreadSleep(2000);
					if(click(driver, ivp.getFirmNameInfoIcon(20), "firm name info icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on firm name info icon");
						if(getText(driver, ivp.getFirmNameHeaderText(20), "header pop up", action.BOOLEAN).split("-")[1].trim().contains(SmokeFirmName)) {
							appLog.info("firm name is displaying on firm name info pop up header "+SmokeFirmName);
						}else {
							appLog.error("firm name is not displaying on firm name info pop up header "+SmokeFirmName);
							sa.assertTrue(false, "firm name is not displaying on firm name info pop up header "+SmokeFirmName);
						}
						if(click(driver, ivp.getFirmNameInfoPopUpCrossIcon(20), "invesment down arrow", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on cross icon");
						}else {
							appLog.error("Not able to click on cross icon so cannot close pop up");
							sa.assertTrue(false, "Not able to click on cross icon so cannot close pop up");
						}
						
					}else {
						appLog.error("Not able to click on firm name info icon");
						sa.assertTrue(false, "Not able to click on firm name info icon");
					}
					
				}else {
					appLog.error("Not able to click on invesment down arrow so cannot verify info pop up");
					sa.assertTrue(false,"Not able to click on invesment down arrow so cannot verify info pop up");
				}
			}else {
				appLog.error("Not able to click on potential investment tab so cannot verify folder structure");
				sa.assertTrue(false, "Not able to click on potential investment tab so cannot verify folder structure");
			}
			lp.investorLogout();
		} else {
			appLog.info("investor "+SmokeContact2FirstName+" "+SmokeContact2LastName+" is already Registered.");
			sa.assertTrue(false, "investor "+SmokeContact2FirstName+" "+SmokeContact2LastName+" is already Registered.");
		}
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc032_updateprofileAndFirmProfileInvestorSide(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer af = new AllFirmsPageBusinesslayer(driver);
		InvestorProfileBusinessLayer ip = new InvestorProfileBusinessLayer(driver);
		lp.investorLogin(SmokeContact2EmailId,SmokePassword);
		ThreadSleep(2000);
		if (click(driver, af.getProfileLink(60), "profile link on investor firm page", action.SCROLLANDBOOLEAN)) {
			appLog.info("clicked on profile link ");
			ThreadSleep(3000);
			if (click(driver, ip.getEditIcon(60), "edit icon on my profile page", action.SCROLLANDBOOLEAN)) {
				ThreadSleep(2000);
				if (sendKeys(driver, ip.getPhoneTextbox(60), Smokephone, "phone textbox on my profile page", action.SCROLLANDBOOLEAN)) {
					appLog.info("passed value in phone text box "+Smokephone);
					
				}else {
					appLog.error(" Not able to pass value in phone text so cannot update phone number");
					sa.assertTrue(false, " Not able to pass value in phone text so cannot update phone number");
				}
				
				if (sendKeys(driver, ip.getTitleTextbox(60), Smoketitle, "title textbox on my profile page", action.SCROLLANDBOOLEAN)) {
					appLog.info("passed value in title text box "+Smoketitle);
					
				}else {
					appLog.error(" Not able to pass value in title text so cannot update title");
					sa.assertTrue(false, " Not able to pass value in title text so cannot update title");
				}
				
				if (click(driver, ip.getNoEmailRadiobuttonEditMode(60), "no email radio button", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on no email radio button ");
					
				}else {
					appLog.error("Not able to click on no emails radio button ");
					sa.assertTrue(false, "Not able to click on no emails radio button ");
				}
				
				if (click(driver, ip.getSaveButtonMyProfilePage(60), "Save button on my profile page", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on profile save button");
				}else {
					appLog.error("Not able to click on profile save button");
					sa.assertTrue(false, "Not able to click on profile save button");
				}
				ThreadSleep(3000);
				if (click(driver, ip.getMyFirmProfileTab(60), "my firm profile tab", action.BOOLEAN)) {
					ThreadSleep(2000);
					if (click(driver, ip.getEditIcon(60), "Edit icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on edit icon ");
						ThreadSleep(2000);
						if (selectVisibleTextFromDropDown(driver, ip.getIndustryDropdown(60), "Industry",SmokeContact1IndustryDropdown)) {
							appLog.info("select industry "+SmokeContact1IndustryDropdown);
							
						} else {
							appLog.info("Not able to Select Industry dropdown value "+SmokeContact1IndustryDropdown);
							sa.assertTrue(false, "Not able to Select Industry dropdown value "+SmokeContact1IndustryDropdown);
						}
						if (sendKeys(driver, ip.getBillingCityTextbox(60), SmokeContact1BillingCity, "Billing city text box",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("passed value in billing city text box : "+SmokeContact1BillingCity);
						} else {
							appLog.info("Not able to enter Billing city "+SmokeContact1BillingCity);
							sa.assertTrue(false, "Not able to enter Billing city "+SmokeContact1BillingCity);
						}
						if (click(driver, ip.getSaveButtonFirmProfile(60), "Firm profile", action.SCROLLANDBOOLEAN)) {
							ThreadSleep(2000);
							appLog.info("clicked on firm profile save button ");
						} else {
							appLog.info("Not able to click on firm profile save button");
							sa.assertTrue(false, "Not able to click on firm profile save button");
						}
					}else {
						appLog.error("Not able to click on edit icon so cannot update my firm profile ");
						sa.assertTrue(false, "Not able to click on edit icon so cannot update my firm profile ");
					}
				}else {
					appLog.error("Not able to click on my firm profile tab so cannot update my firm profile ");
					sa.assertTrue(false, "Not able to click on my firm profile tab so cannot update my firm profile ");
				}
			}else {
				appLog.error("Not able to click on edit icon so cannot update profile");
				sa.assertTrue(false, "Not able to click on edit icon so cannot update profile");
			}
			
		}else {
			appLog.error("profile link is not clickable on all firms page");
			sa.assertTrue(false, "profile link is not clickable on all firms page");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc033_checkLinksOnFundDetailsPage(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		String standrdFolder=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String[] standrdfile=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard).split("<break>");
		String shrdfile=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileShared);
		String shrdFolder=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		lp.CRMLogin(SmokeCRMUser2Email,SmokePassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(SmokeFundName1)) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),Workspace.InvestorWorkspace.toString() + " View.");
				if(fp.verifyFolderPathdummy(standrdFolder, SmokeInstitution1,UpdatedSmokeLP1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 60)){
					if (bp.verifyDownloadFunctionality(PageName.PotentialInvestmentPage, Workspace.InvestorWorkspace, standrdfile[0], true, false,false)) {
						appLog.info("download button is successfully verified");
						switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
					}
					else {
						appLog.error("download button is not successfully verified");
						sa.assertTrue(false, "download button is not successfully verified");
						switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
					}
					WebElement ele=FindElement(driver, "//a[@title='"+standrdfile[0]+"']/../../following-sibling::span//a/u[text()='View']", "view link", action.BOOLEAN, 10);
					if(ele!=null) {
						if(click(driver, ele, "view link", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on view link");
							
							if(selectVisibleTextFromDropDown(driver, fp.getDocumentStatisticsRangeDropDownList(30), "Range dropdown", "Last 30 Days")){
								ThreadSleep(2000);
								appLog.info("Last 30 Days Option is selected in range drop down list");
								if(getSelectedOptionOfDropDown(driver,  fp.getDocumentStatisticsRangeDropDownList(30), "Range dropdown", "text").contains("Last 30 Days")) {
									appLog.info("Last 30 Days Option is verified");

								}else {
									appLog.error("Last 30 Days Option Option is not selected in view statistics drop down list ");
									sa.assertTrue(false, "Last 30 Days Option Option is not selected in view statistics drop down list ");
								}
							}else{
								appLog.info("Not able to select range dropodwn value");
								sa.assertTrue(false, "Not able to select range dropdown value ");
							}
					
					
						
							if(selectVisibleTextFromDropDown(driver, fp.getDocumentStatisticsViewDropDownList(30), "Show dropdown", "Document Downloads")){
								ThreadSleep(2000);
								appLog.info("Document Downloads Option is selected ");
								if(getSelectedOptionOfDropDown(driver, fp.getDocumentStatisticsViewDropDownList(30), "Show DropDown List", "text").contains("Document Downloads")) {
									appLog.info("Document Downloads Option is verified");
									
								}else {
									appLog.error("Document Downloads Option is not selected in view statistics drop down list ");
									sa.assertTrue(false, "Document Downloads Option is not selected in view statistics drop down list ");
								}
							}else{
								appLog.error("Not able to select show dropdown value");
								sa.assertTrue(false, "Not able to select dropdown value");
							}
							
							
							if(click(driver, fp.getDocumentStatisticsPopUpCloseBtn(10), "close button", action.BOOLEAN)) {
								appLog.info("clicked on document statistics close button");
							}else {
								appLog.error("Not able to click on document statistics close button so cannot close pop up");
								sa.assertTrue(false, "Not able to click on document statistics close button so cannot close pop up");
							}
						}else {
							appLog.error("Not able to click on view link so cannot verify pop up");
							sa.assertTrue(false, "Not able to click on view link so cannot verify pop up");
						}
					}else {
						appLog.error("statistics view link is not visible so cannot click on it ");
						sa.assertTrue(false, "statistics view link is not visible so cannot click on it ");
					}
					
					ele=FindElement(driver, "//a[@title='"+standrdfile[0]+"']/../../following-sibling::span//a[@title='"+SmokeContact2FirstName+" "+SmokeContact2LastName+"']", "view link", action.BOOLEAN, 10);
					if(ele!=null) {
						if(click(driver, ele, "contact name link", action.BOOLEAN)) {
							String parentID = switchOnWindow(driver);
							if (parentID != null) {
								ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
								if (ele != null) {
									if (ele.getText().trim().equalsIgnoreCase(SmokeContact2FirstName + " " + SmokeContact2LastName)) {
										appLog.info(SmokeContact2FirstName + " " + SmokeContact2LastName + " Page is opened");
									} else {
										appLog.info(SmokeContact2FirstName + " " + SmokeContact2LastName + " Page is not opened");
										sa.assertTrue(false,
												SmokeContact2FirstName + " " + SmokeContact2LastName + " Page is not opened");
									}
								} else {
									appLog.info("Not able to find page header");
									sa.assertTrue(false, "Not able to find page header");
								}
								driver.close();
								driver.switchTo().window(parentID);
								switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
							} else {
								appLog.info("No new window is open");
								sa.assertTrue(false, "No new window is open");
							}
							
						}else {
							appLog.error("Not able to click on contact name link so cannot verify contact page");
							sa.assertTrue(false, "Not able to click on contact name link so cannot verify contact page");
						}
					}else {
						appLog.error("contact name link is not visible so cannot verify contact page");
						sa.assertTrue(false, "contact name link is not visible so cannot verify contact page");
					}
					ele=FindElement(driver, "//a[@title='"+standrdfile[0]+"']/../../following-sibling::span//a[@title='"+SmokeInstitution1+"']", "view link", action.BOOLEAN, 10);
					if(ele!=null) {
						if (click(driver,ele, "firm name link", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Go to firm button");
							String parentID = switchOnWindow(driver);
							if (parentID != null) {
								ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
								if (ele != null) {
									if (ele.getText().trim().equalsIgnoreCase(SmokeInstitution1)) {
										appLog.info(SmokeInstitution1 + " Page is opened");
									} else {
										appLog.info(SmokeInstitution1 + " Page is not opened");
										sa.assertTrue(false, SmokeInstitution1 + " Page is not opened");
									}
								} else {
									appLog.info("Not able to find page header");
									sa.assertTrue(false, "Not able to find page header");
								}
								driver.close();
								driver.switchTo().window(parentID);
								switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
							} else {
								appLog.info("No new window is open");
								sa.assertTrue(false, "No new window is open");
							}
						} else {
							appLog.info("Not able to click on got ot firm button");
							sa.assertTrue(false, "Not able to lcick on go to firm button");
						}

					}else {
						appLog.error("institution name is not visible so cannot click on it ");
						sa.assertTrue(false, "institution name is not visible so cannot click on it ");
					}
					if (fp.clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Open, standrdfile[1], Workspace.InvestorWorkspace, 10, null)) {
						appLog.info("clicked on file "+standrdfile[1]);
						ThreadSleep(2000);
						String parentID = switchOnWindow(driver);
						if (parentID != null) {
							if(fp.getDownloadLink(30)!=null) {
								appLog.info("download button is displayed ");
							}else {
								appLog.error("Download button is not displayed ");
								sa.assertTrue(false, "Download button is not displayed ");
							}
							driver.close();
							driver.switchTo().window(parentID);
							switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
						} else {
							appLog.info("No new window is open");
							sa.assertTrue(false, "No new window is open");
						}
					}else {
						appLog.error("Not able to click on "+standrdfile[1]+" file down arrow open so cannot open file ");
						sa.assertTrue(false, "Not able to click on "+standrdfile[1]+" file down arrow open so cannot open file ");
					}
					
					for(int i=0; i<2; i++) {
						if (fp.clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Delete, standrdfile[1], Workspace.InvestorWorkspace, 10, null)) {
							appLog.info("clicked on file "+standrdfile[1]+" delete option ");
							ThreadSleep(2000);
							if(i==1) {
								if (click(driver, fp.getDocumentDeleteYesBtn(Workspace.InvestorWorkspace,60), "Yes button", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on delete pop up yes button");
								}else {
									appLog.error("Not able to click on delete pop up yes button so cannot delete file "+standrdfile[1]);
									sa.assertTrue(false, "Not able to click on delete pop up yes button so cannot delete file "+standrdfile[1]);
								}
							}
							if(i==0) {
								if (click(driver, fp.getDocumentDeleteNoBtn(Workspace.InvestorWorkspace, 10), "No button", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on delete pop up no button");
								}else {
									appLog.error("Not able to click on delete pop up no button so cannot close pop up");
									sa.assertTrue(false, "Not able to click on delete pop up no button so cannot clsoe pop up");
								}
							}
						}else {
							appLog.error("Not able to click on "+standrdfile[1]+" file down arrow open so cannot delete file ");
							sa.assertTrue(false, "Not able to click on "+standrdfile[1]+" file down arrow open so cannot delete file ");
						}
						
					}
					if (click(driver, fp.getAlertHistoryLink(Workspace.InvestorWorkspace, PageName.FundsPage, 60),"Alert History Link", action.SCROLLANDBOOLEAN)) {
						switchToDefaultContent(driver);
						switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
						if (bp.clickOnActiivityTypeLinkBasedOnContact("Contact Profile Updated",SmokeContact2FirstName + " " + SmokeContact2LastName)) {
							appLog.info("Clicked on activity type");
							if (click(driver, bp.getGoToContactButton(PageName.FundsPage,Workspace.InvestorWorkspace,60), "Go to Contact button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on Go to Contact button");
								String parentID = switchOnWindow(driver);
								if (parentID != null) {
									ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
									if (ele != null) {
										if (ele.getText().trim().equalsIgnoreCase(SmokeContact2FirstName + " " + SmokeContact2LastName)) {
											appLog.info(SmokeContact2FirstName + " " + SmokeContact2LastName + " Page is opened");
										} else {
											appLog.info(SmokeContact2FirstName + " " + SmokeContact2LastName + " Page is not opened");
											sa.assertTrue(false,
													SmokeContact2FirstName + " " + SmokeContact2LastName + " Page is not opened");
										}
									} else {
										appLog.info("Not able to find page header");
										sa.assertTrue(false, "Not able to find page header");
									}
									driver.close();
									driver.switchTo().window(parentID);
									switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
								} else {
									appLog.info("No new window is open");
									sa.assertTrue(false, "No new window is open");
								}
							} else {
								appLog.info("Not able to click on go to Contact button");
								sa.assertTrue(false, "Not able to click on go to contact button");
							}
							if (click(driver, bp.getContactProfileUpdateCloseButton(Workspace.InvestorWorkspace,PageName.FundsPage, 60), "Close Button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on close icon");
								if (bp.getContactProfileUpdateCloseButton(Workspace.InvestorWorkspace,PageName.FundsPage, 2) == null) {
									appLog.info("Contact profile updated popup get closed");
								} else {
									appLog.info("Contact Profile updtaed popup does not get closed");
									sa.assertTrue(false, "Contact Profile updtaed popup does not get closed");
								}
							} else {
								appLog.info("Not able to click on close icon");
								sa.assertTrue(false, "Not able to click on close icon");
							}

						} else {
							appLog.info("Not able to click on activity type");
							sa.assertTrue(false, "Not able to click on activity type");
						}
						if (bp.clickOnActiivityTypeLinkBasedOnContact("Firm Profile Updated",SmokeContact2FirstName + " " + SmokeContact2LastName)) {
							appLog.info("Clicked on activity type");
							if (click(driver, bp.getGoToFirmButton(PageName.FundsPage,Workspace.InvestorWorkspace,60), "Go to firm button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on Go to firm button");
								String parentID = switchOnWindow(driver);
								if (parentID != null) {
									ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
									if (ele != null) {
										if (ele.getText().trim().equalsIgnoreCase(SmokeInstitution1)) {
											appLog.info(SmokeInstitution1 + " Page is opened");
										} else {
											appLog.info(SmokeInstitution1 + " Page is not opened");
											sa.assertTrue(false, SmokeInstitution1 + " Page is not opened");
										}
									} else {
										appLog.info("Not able to find page header");
										sa.assertTrue(false, "Not able to find page header");
									}
									driver.close();
									driver.switchTo().window(parentID);
									switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
								} else {
									appLog.info("No new window is open");
									sa.assertTrue(false, "No new window is open");
								}
							} else {
								appLog.info("Not able to click on got ot firm button");
								sa.assertTrue(false, "Not able to lcick on go to firm button");
							}
							if (click(driver,bp.getFirmProfileUpdateCloseButton(Workspace.InvestorWorkspace, PageName.FundsPage, 60),"Close Button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on close icon");
								if (bp.getFirmProfileUpdateCloseButton(Workspace.InvestorWorkspace, PageName.FundsPage,10) == null) {
									appLog.info("Firm profile updated popup get closed");
								} else {
									appLog.info("Firm Profile updtaed popup does not get closed");
									sa.assertTrue(false, "Firm Profile updtaed popup does not get closed");
								}
							} else {
								appLog.info("Not able to click on close icon");
								sa.assertTrue(false, "Not able to click on close icon");
							}

						} else {
							appLog.info("Not able to click on activity type");
							sa.assertTrue(false, "Not able to click on activity type");
						}
						ele=FindElement(driver, "//a[text()='"+SmokeContact2FirstName+" "+SmokeContact2LastName+"']", "contact name ", action.SCROLLANDBOOLEAN, 10);
						if(ele!=null) {
							if(click(driver, ele, "contact name link", action.BOOLEAN)) {
								String parentID = switchOnWindow(driver);
								if (parentID != null) {
									ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
									if (ele != null) {
										if (ele.getText().trim().equalsIgnoreCase(SmokeContact2FirstName + " " + SmokeContact2LastName)) {
											appLog.info(SmokeContact2FirstName + " " + SmokeContact2LastName + " Page is opened");
										} else {
											appLog.info(SmokeContact2FirstName + " " + SmokeContact2LastName + " Page is not opened");
											sa.assertTrue(false,
													SmokeContact2FirstName + " " + SmokeContact2LastName + " Page is not opened");
										}
									} else {
										appLog.info("Not able to find page header");
										sa.assertTrue(false, "Not able to find page header");
									}
									driver.close();
									driver.switchTo().window(parentID);
									switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
								} else {
									appLog.info("No new window is open");
									sa.assertTrue(false, "No new window is open");
								}
								
							}else {
								appLog.error("Not able to click on contact name link so cannot verify contact page");
								sa.assertTrue(false, "Not able to click on contact name link so cannot verify contact page");
							}
						}else {
							appLog.error("contact name link is not visible so cannot verify contact page");
							sa.assertTrue(false, "contact name link is not visible so cannot verify contact page");
						}
						ele=FindElement(driver, "//a[text()='"+standrdfile[0]+"']", standrdfile[0]+" file name link ", action.SCROLLANDBOOLEAN, 10);
						if(ele!=null) {
							if(click(driver, ele, standrdfile[0]+" name link", action.BOOLEAN)) {
								ThreadSleep(3000);
								String parentID = switchOnWindow(driver);
								if (parentID != null) {
									if(fp.getDownloadLink(30)!=null) {
										appLog.info("download button is displayed ");
									}else {
										appLog.error("Download button is not displayed ");
										sa.assertTrue(false, "Download button is not displayed ");
									}
									
									driver.close();
									driver.switchTo().window(parentID);
									switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
								} else {
									appLog.info("No new window is open");
									sa.assertTrue(false, "No new window is open");
								}
								
							}else {
								appLog.error("Not able to click on file name link "+standrdfile[0]);
								sa.assertTrue(false, "Not able to click on file name link "+standrdfile[0]);
							}
						}else {
							appLog.error(standrdfile[0]+" file is not visible so cannot click on it ");
							sa.assertTrue(false, standrdfile[0]+" file is not visible so cannot click on it ");
						}
						

						if(selectVisibleTextFromDropDown(driver, fp.getAlertHistoryRangeDropDownList(Workspace.InvestorWorkspace, 30), "Range dropdown", "Last 30 Days")){
							ThreadSleep(2000);
							appLog.info("Last 30 Days Option is selected in range drop down list");
							if(getSelectedOptionOfDropDown(driver,  fp.getAlertHistoryRangeDropDownList(Workspace.InvestorWorkspace, 30), "Range dropdown", "text").contains("Last 30 Days")) {
								appLog.info("Last 30 Days Option is verified");

							}else {
								appLog.error("Last 30 Days Option Option is not selected in fund alert grid drop down list ");
								sa.assertTrue(false, "Last 30 Days Option Option is not selected in fund alert grid drop down list ");
							}
						}else{
							appLog.info("Not able to select range dropodwn value");
							sa.assertTrue(false, "Not able to select range dropdown value ");
						}
				
				
					
						if(selectVisibleTextFromDropDown(driver, fp.getAlertHistoryShowDropDownList(Workspace.InvestorWorkspace,60), "Show dropdown", "All Except Profile Updates")){
							ThreadSleep(2000);
							appLog.info("All Except Profile Update Option is selected ");
							if(getSelectedOptionOfDropDown(driver, fp.getAlertHistoryShowDropDownList(Workspace.InvestorWorkspace,60), "Show DropDown List", "text").contains("All Except Profile Updates")) {
								appLog.info("All Except Profile Update Option is verified");
								
							}else {
								appLog.error("All Except Profile Update Option is not selected in home alert grid drop down list ");
								sa.assertTrue(false, "All Except Profile Update Option is not selected in home alert grid drop down list ");
							}
						}else{
							appLog.error("Not able to select show dropdown value");
							sa.assertTrue(false, "Not able to select dropdown value");
						}
						
						
						
						if(click(driver, bp.getAlertHistoryCrossIcon(Workspace.InvestorWorkspace, 60), "Alert history cross icon", action.SCROLLANDBOOLEAN)){
							appLog.info("Clicked on alert history aross icon");
						}else{
							appLog.error("Not able to click on alert history cross icon");
							sa.assertTrue(false, "Not able to click on alert history cross icon");
						}	
					}else {
						appLog.error("Not able to click on alter history link so cannot check pop up");
						sa.assertTrue(false, "Not able to click on alter history link so cannot check pop up");
					}
					if (click(driver, fp.getInvestmentInfo(Workspace.InvestorWorkspace), "Investment info",action.SCROLLANDBOOLEAN)) {
						for(int i=0; i<2; i++) {
							if (click(driver, fp.getInvestmentInfoEdit(60), "investment info edit button",action.SCROLLANDBOOLEAN)) {
								if(i==0) {
									if (click(driver, fp.getInvestmentInfoPopUpCacnelBtnInEditMode(40), "Investment Info cancel button",action.SCROLLANDBOOLEAN)) {
										appLog.info("clicked on cancel button");
									} else {
										appLog.error("cancel button is not clicked");
										sa.assertTrue(false,"Cancel button is not clicked");
									}
								}
								if(i==1) {
									if (sendKeys(driver, fp.getInvestmentInfoPhoneTxtbox(60), "123456","phone number text box",action.BOOLEAN)) {
										if (click(driver, fp.getInvestmentInfoSaveBtn(40), "Investment Info save button",action.SCROLLANDBOOLEAN)) {
											appLog.info("clicked on save button");
										} else {
											appLog.error("Save button is not clicked so ,Updated fund name is not able to save.");
											sa.assertTrue(false,"Save button is not clicked so ,Updated fund name is not able to save.");
										}
									} else {
										appLog.error("fund name text box is not visible on Investment Info edit mode so, not able to enter value.");
										sa.assertTrue(false,"fund name text box is not visible on Investment Info edit mode so, not able to enter value.");
									}
								}
							} else {
								appLog.error("Investment Info edit button is not clickable so, not able to enter value.");
								sa.assertTrue(false,"Investment Info edit button is not clickable so, not able to enter value.");
							}
						}
						ThreadSleep(2000);
						if (click(driver, fp.getInvestmentInfoCancelButton(40), "Investment Info cancel button",action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on cancel button");
						} else {
							appLog.error("cancel button is not clicked");
							sa.assertTrue(false,"Cancel button is not clicked");
						}
					}else {
						appLog.error("Not able to click on investment update info link so cannot update phone number from investment update info");
						sa.assertTrue(false, "Not able to click on investment update info link so cannot update phone number from investment info");
					}
					
					if (click(driver, fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
						String parentid = switchOnWindow(driver);
						ThreadSleep(5000);
						System.err.println("Parent wind ID: "+parentid);
						String childWindowID = null;
						String childChildWindow = null;
						Set<String> lst = driver.getWindowHandles();
						Iterator<String> I1 = lst.iterator();
						while (I1.hasNext()) {
							String windowID = I1.next();
							if (windowID.equalsIgnoreCase(parentid)) {
								appLog.info("Parent Id is Matched");
							} else {
								childWindowID = windowID;
								appLog.info("Storged child Window Id");
								break;
							}
						}
						if (click(driver, fp.getIPAnalyticsMostViewedDocumentLink(30), "Most View Document Link",action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on most viewed document link ");
							ThreadSleep(1000);
							if (click(driver, fp.getMostviewedDocumentCloseBtn(10),"Document not viewed or download close button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on Most Viewed Document pop up Close button");
							} else {
								appLog.info("Clicked on Most Viewed Document pop up Close button");
								sa.assertTrue(false, "Clicked on CMost Viewed Document pop up Close button");
							}
						} else {
							appLog.info("Not able to click on Most Viewed Document link");
							sa.assertTrue(false, "Not able to click on Most Viewed Document link");
						}
						if (click(driver, fp.getIPAnalyticsMostActiveContactsLink(30), "Most Active Contact Link",action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on most active contact link");
							ThreadSleep(2000);
							if (click(driver, fp.getMostActiveContactCloseBtn(10), "Document not viewed or download close button",action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on Most Active Contact pop up Close button");
							} else {
								appLog.info("Clicked on Most Active Contact pop up Close button");
								sa.assertTrue(false, "Clicked on Most Active Contact pop up Close button");
							}
						} else {
							appLog.info("Not able to click on Most Active Contact link");
							sa.assertTrue(false, "Not able to click on Most Active Contact link");
						}
						List<WebElement> tabs = fp.getIPAnalyticsListofTab();
						String tab1 = tabs.get(1).getText().trim();
						if (click(driver, tabs.get(1), "DR Analytics Tab Link " + tab1, action.BOOLEAN)) {
							List<WebElement> exportLinks = fp.getIPAnalyticsListOfExportText();
							if (click(driver, exportLinks.get(0), "Workspace Documents Report",
									action.BOOLEAN)) {
								appLog.info("Clicked on Workspace Documents Report");
								ThreadSleep(10000);
								appLog.info("Workspace Documents Report File is downloaded");
								System.err.println("URL Of small window: "+getURL(driver, 2)+" "+driver.getWindowHandle());
								Robot rob;
								try {
									rob = new Robot();
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_ENTER);
									rob.keyRelease(KeyEvent.VK_ENTER);
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_CONTROL);
									rob.keyPress(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_CONTROL);
								} catch (AWTException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								// ele.sendKeys(Keys.CONTROL+"j");
								ThreadSleep(10000);
								String abc = driver.getWindowHandle();
								System.err.println("URL Of small window: "+getURL(driver, 2)+" "+abc);
								Set<String> lst1 = driver.getWindowHandles();
								Iterator<String> I2 = lst1.iterator();
								while (I2.hasNext()) {
									String windowID = I2.next();
									if (windowID.equalsIgnoreCase(parentid) || windowID.equalsIgnoreCase(childWindowID)) {
										appLog.info("Parent Id or child Id is Matched");
									} else {
										childChildWindow = windowID;
										appLog.info("Storged  child child Window Id");
										driver.switchTo().window(childChildWindow);
										System.err.println("URL Of Download window: "+getURL(driver, 2)+" "+driver.getWindowHandle());
										break;
									}
								}
								System.out.println(getURL(driver, 30));
								if (childChildWindow == null) {
									try {
										rob = new Robot();
										rob.keyPress(KeyEvent.VK_TAB);
										rob.keyRelease(KeyEvent.VK_TAB);
										rob.keyPress(KeyEvent.VK_ENTER);
										rob.keyRelease(KeyEvent.VK_ENTER);
										ThreadSleep(10000);
										rob.keyPress(KeyEvent.VK_CONTROL);
										rob.keyPress(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_CONTROL);
										parentid = switchOnWindow(driver);
									} catch (AWTException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								ThreadSleep(10000);
								String fileName = fp.getDownloadedFileName();
								if (fileName.equalsIgnoreCase("Investor_Documents_" + SmokeFundName1 + "_"
										+ getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv")) {
									appLog.info("file name is successfully matched. " + "Investor_Documents_" + SmokeFundName1 + "_"
											+ getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv");
								} else {
									appLog.info("File name is not the matched. Expected: " + "Investor_Documents_" + SmokeFundName1
											+ "_" + getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv"
											+ " Actual: " + fileName);
									sa.assertTrue(false,
											"File name is not the matched. Expected: " + "Investor_Documents_" + SmokeFundName1
													+ "_" + getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv"
													+ " Actual: " + fileName);
								}
								driver.close();
								driver.switchTo().window(childWindowID);

							} else {
								appLog.error("Not able to click on Workspace Documents Report Link");
								sa.assertTrue(false, "Not able to click on Workspace Documents Report Link");
							}
							exportLinks.clear();
							exportLinks = fp.getIPAnalyticsListOfExportText();
							if (click(driver, exportLinks.get(1), "Contact Permissions Report ", action.BOOLEAN)) {
								appLog.info("Clicked on Contact Permission Export Link");
								ThreadSleep(10000);
								appLog.info("Contact Permissions Report File downloaded");
								Robot rob;
								try {
									rob = new Robot();
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_ENTER);
									rob.keyRelease(KeyEvent.VK_ENTER);
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_CONTROL);
									rob.keyPress(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_CONTROL);
								} catch (AWTException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								// ele.sendKeys(Keys.CONTROL+"j");
								ThreadSleep(10000);
								Set<String> lst1 = driver.getWindowHandles();
								Iterator<String> I2 = lst1.iterator();
								while (I2.hasNext()) {
									String windowID = I2.next();
									if (windowID.equalsIgnoreCase(parentid) || windowID.equalsIgnoreCase(childWindowID)) {
										appLog.info("Parent Id or child Id is Matched");
									} else {
										childChildWindow = windowID;
										appLog.info("Storged  child child Window Id");
										driver.switchTo().window(childChildWindow);
										break;
									}
								}
								if (childChildWindow == null) {
									try {
										rob = new Robot();
										rob.keyPress(KeyEvent.VK_TAB);
										rob.keyRelease(KeyEvent.VK_TAB);
										rob.keyPress(KeyEvent.VK_ENTER);
										rob.keyRelease(KeyEvent.VK_ENTER);
										ThreadSleep(10000);
										rob.keyPress(KeyEvent.VK_CONTROL);
										rob.keyPress(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_J);
										rob.keyRelease(KeyEvent.VK_CONTROL);
										parentid = switchOnWindow(driver);
									} catch (AWTException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								ThreadSleep(10000);
								String fileName = fp.getDownloadedFileName();
								if (fileName.equalsIgnoreCase("Investor_Contact_Permissions_" + SmokeFundName1 + "_"
										+ getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv")) {
									appLog.info("file name is successfully matched. " + "Investor_Contact_Permissions_" + SmokeFundName1 + "_"
											+ getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv");
								} else {
									appLog.info("File name is not the matched. Expected: " + "Investor_Contact_Permissions_"
											+ SmokeFundName1 + "_" + getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv"
											+ " Actual: " + fileName);
									sa.assertTrue(false,
											"File name is not the matched. Expected: " + "Investor_Contact_Permissions_"
													+ SmokeFundName1 + "_" + getDateAccToTimeZone("America/New_York", "MMddyyyy")
													+ ".csv" + " Actual: " + fileName);
								}
								driver.close();
								driver.switchTo().window(childWindowID);

							} else {
								appLog.error("Not able to click on Contact Permission Export Link");
								sa.assertTrue(false, "Not able to click on Contact Permission Export Link");
							}
						} else {
							appLog.error("Not able to click on DR Analytics Exports tab");
							sa.assertTrue(false, "Not able to click on DR Analytics Exports tab");
						}
						
						driver.close();
						driver.switchTo().window(parentid);
						switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
					}else {
						appLog.error("Not able to click on analytics icon so cannot verify links");
						sa.assertTrue(false, "Not able to click on analytics icon so cannot verify links");
					}
				}else{
					appLog.error("Folder path not visible "+standrdFolder+" so cannot click on file link");
					sa.assertTrue(false, "Folder path not visible "+standrdFolder+" so cannot click on file link");
				}
				String docPath=System.getProperty("user.dir") + "\\UploadFiles\\SmokeUploadFile\\Shared\\";
				if(fp.updateFile(shrdFolder, shrdfile, SmokeInstitution1, UpdatedSmokeLP1, FolderType.Shared,docPath+shrdfile,null, null, ContentGridArrowKeyFunctions.Update,20, PageName.FundsPage,"Yes",null, Workspace.InvestorWorkspace)) {
					appLog.info("file is updated successfully: "+shrdfile+" in :"+shrdFolder);
						
				
				}else {
					appLog.error("Not able to update file in shared folder "+shrdfile);
					sa.assertTrue(false, "Not able to update file in shared folder "+shrdfile);
				}
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(fp.verifyFolderPathdummy(shrdFolder, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 60)){
					if(fp.clickOnManageVersionOnContentGrid(shrdfile, Workspace.InvestorWorkspace, 20)) {
						appLog.info("clicked on manage version link");
						
						if (click(driver, fp.getUpdateButtonOnManageVersionPopUp(60), "Update Button", action.BOOLEAN)) {
							appLog.info("clicked on update button ");
							String parentwindow=  switchOnWindow(driver);
							if(parentwindow!=null) {
								appLog.info("Update file window is open ");
								driver.close();
								driver.switchTo().window(parentwindow);
								switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
								
							}else {
								appLog.error("No new window is open ");
								sa.assertTrue(false, "No new window is open ");
							}
							WebElement ele=FindElement(driver, "//div[@id='idmangeversion']//a[text()='"+shrdfile+"']", shrdfile+" file name link ", action.SCROLLANDBOOLEAN, 10);
							if(ele!=null) {
								if(click(driver, ele, shrdfile+" name link", action.BOOLEAN)) {
									ThreadSleep(3000);
									String parentID = switchOnWindow(driver);
									if (parentID != null) {
										if(fp.getDownloadLink(30)!=null) {
											appLog.info("download button is displayed ");
										}else {
											appLog.error("Download button is not displayed ");
											sa.assertTrue(false, "Download button is not displayed ");
										}
										
										driver.close();
										driver.switchTo().window(parentID);
										switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
									} else {
										appLog.info("No new window is open");
										sa.assertTrue(false, "No new window is open");
									}
									
								}else {
									appLog.error("Not able to click on file name link "+shrdfile);
									sa.assertTrue(false, "Not able to click on file name link "+shrdfile);
								}
							}else {
								appLog.error(shrdfile+" file is not visible so cannot click on it ");
								sa.assertTrue(false, shrdfile+" file is not visible so cannot click on it ");
							}
							
							if (!click(driver, fp.getManageVersionPopUpCloseButton(30),"Manage Versions Pop Up close Button", action.BOOLEAN)) {
								appLog.error("Not able to click on Manage version Close button");
								sa.assertTrue(false, "Not able to click on Manage version Close button");
							}else {
								appLog.info("clicked on close button ");
							}
							
						}else {
							appLog.info("Update Button on manage version pop up is not clikable.");
							sa.assertTrue(false, "Update Button on manage version pop up is not available.");
						}
						switchToDefaultContent(driver);
						if(fp.makeCurrentversionDocViaManageVersion(null, null, shrdFolder,shrdfile, shrdfile, Workspace.InvestorWorkspace,30)) {
							appLog.info("file is successfully make current: "+shrdfile+" in :"+shrdFolder);
							
						}else {
							appLog.error("Not able to make current file "+shrdfile);
							sa.assertTrue(false, "Not able to make current file "+shrdfile);
						}
					}else {
						appLog.error("Not able to click on manage version link so cannot change updated file version");
						sa.assertTrue(false, "Not able to click on manage version link so cannot change updated file version");
					}
				
				}else{
					appLog.error("Folder path not visible "+shrdFolder+" so cannot click on file link");
					sa.assertTrue(false, "Folder path not visible "+shrdFolder+" so cannot click on file link");
				}
				
				if(fp.verifyFolderPathdummy(standrdFolder,SmokeInstitution1,UpdatedSmokeLP1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 60)){
					String 	date = getSystemDate("MM/dd/yyyy")+previousOrForwardDate(-1, "MM/dd/yyyy");
					if (bp.enterValueAndClickonSearchBoxContentGrid(PageName.FundsPage, Workspace.InvestorWorkspace,standrdfile[0], 30)) {
						ThreadSleep(2000);
						SoftAssert saa = bp.verifyContentGridForSearch(driver,SmokeInstitution1+" > "+UpdatedSmokeLP1+" > "+standrdFolder, standrdfile[0], date);
						sa.combineAssertions(saa);
					}else {
						appLog.error("Not able to search file : "+standrdfile[0]+" in IVR workspace");
						sa.assertTrue(false, "Not able to search file : "+standrdfile[0]+" in IVR workspace");
					}
					if (click(driver, bp.getSearchPopCrossIcon(30), "Search Cross Icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on search pop up cross icon");
					}else {
						appLog.error("Not able to click search pop up cross icon so cannot close pop up");
						sa.assertTrue(false, "Not able to click search pop up cross icon so cannot close pop up");
					}
					
				}else {
					appLog.error("Not able to click on standard folder so cannot check search functionality");
					sa.assertTrue(false, "Not able to click on standard folder so cannot check search functionality");
				}
			}else {
				appLog.error("Not able to click on created Fund: "+SmokeFundName1+" so cannot upload files in Investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+SmokeFundName1+" so cannot upload files in Investor workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in Investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in Investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc034_checkLinksOnContactDetailsPage(String environment, String mode) {
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		ContactPageBusinessLayer contact = new ContactPageBusinessLayer(driver);
		String standrdFolder=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String[] standrdfile=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard).split("<break>");
		lp.CRMLogin(SmokeCRMUser2Email,SmokePassword);
		if(contact.clickOnTab(TabName.ContactTab)) {
			if(contact.clickOnCreatedContact(SmokeContact2FirstName, SmokeContact2LastName, null)) {
				switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),Workspace.InvestorWorkspace.toString() + " View.");
				if(fp.verifyFolderPathdummy(standrdFolder, SmokeInstitution1,UpdatedSmokeLP1, SmokeFundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 60)){
					if (bp.verifyDownloadFunctionality(PageName.ContactsPage, Workspace.InvestorWorkspace, standrdfile[0], true, false,false)) {
						appLog.info("download button is successfully verified");
						switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
					}
					else {
						appLog.error("download button is not successfully verified");
						sa.assertTrue(false, "download button is not successfully verified");
						switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
					}
					
					String 	date = getSystemDate("MM/dd/yyyy")+previousOrForwardDate(-1, "MM/dd/yyyy");
					if (bp.enterValueAndClickonSearchBoxContentGrid(PageName.ContactsPage, Workspace.InvestorWorkspace,standrdfile[0], 30)) {
						ThreadSleep(2000);
						SoftAssert saa = bp.verifyContentGridForSearch(driver,SmokeInstitution1+" > "+UpdatedSmokeLP1+" > "+standrdFolder, standrdfile[0], date);
						sa.combineAssertions(saa);
					}else {
						appLog.error("Not able to search file : "+standrdfile[0]+" in INV workspace");
						sa.assertTrue(false, "Not able to search file : "+standrdfile[0]+" in INV workspace");
					}
					if (click(driver, bp.getSearchPopCrossIcon(30), "Search Cross Icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on search pop up cross icon");
					}else {
						appLog.error("Not able to click search pop up cross icon so cannot close pop up");
						sa.assertTrue(false, "Not able to click search pop up cross icon so cannot close pop up");
					}
					WebElement ele=FindElement(driver, "//a[@title='"+standrdfile[0]+"']/../../following-sibling::span//a[text()='View']", "view link", action.BOOLEAN, 10);
					if(ele!=null) {
						if(click(driver, ele, "view link", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on view link");
							
							if(selectVisibleTextFromDropDown(driver, fp.getDocumentStatisticsRangeDropDownList(30), "Range dropdown", "Last 30 Days")){
								ThreadSleep(2000);
								appLog.info("Last 30 Days Option is selected in range drop down list");
								if(getSelectedOptionOfDropDown(driver,  fp.getDocumentStatisticsRangeDropDownList(30), "Range dropdown", "text").contains("Last 30 Days")) {
									appLog.info("Last 30 Days Option is verified");

								}else {
									appLog.error("Last 30 Days Option Option is not selected in view statistics drop down list ");
									sa.assertTrue(false, "Last 30 Days Option Option is not selected in view statistics drop down list ");
								}
							}else{
								appLog.info("Not able to select range dropodwn value");
								sa.assertTrue(false, "Not able to select range dropdown value ");
							}
					
					
						
							if(selectVisibleTextFromDropDown(driver, fp.getDocumentStatisticsViewDropDownList(30), "Show dropdown", "Document Downloads")){
								ThreadSleep(2000);
								appLog.info("Document Downloads Option is selected ");
								if(getSelectedOptionOfDropDown(driver,fp.getDocumentStatisticsViewDropDownList(30), "Show DropDown List", "text").contains("Document Downloads")) {
									appLog.info("Document Downloads Option is verified");
									
								}else {
									appLog.error("Document Downloads Option is not selected in view statistics drop down list ");
									sa.assertTrue(false, "Document Downloads Option is not selected in view statistics drop down list ");
								}
							}else{
								appLog.error("Not able to select show dropdown value");
								sa.assertTrue(false, "Not able to select dropdown value");
							}
							
							
							
							if(click(driver, fp.getDocumentStatisticsPopUpCloseBtn(10), "close button", action.BOOLEAN)) {
								appLog.info("clicked on document statistics close button");
							}else {
								appLog.error("Not able to click on document statistics close button so cannot close pop up");
								sa.assertTrue(false, "Not able to click on document statistics close button so cannot close pop up");
							}
						}else {
							appLog.error("Not able to click on view link so cannot verify pop up");
							sa.assertTrue(false, "Not able to click on view link so cannot verify pop up");
						}
					}else {
						appLog.error("statistics view link is not visible so cannot click on it ");
						sa.assertTrue(false, "statistics view link is not visible so cannot click on it ");
					}
					ele=FindElement(driver, "//a[@title='"+standrdfile[0]+"']/../../following-sibling::span//a[@title='"+SmokeContact2FirstName+" "+SmokeContact2LastName+"']", "view link", action.BOOLEAN, 10);
					if(ele!=null) {
						if(click(driver, ele, "contact name link", action.BOOLEAN)) {
							String parentID = switchOnWindow(driver);
							if (parentID != null) {
								ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
								if (ele != null) {
									if (ele.getText().trim().equalsIgnoreCase(SmokeContact2FirstName + " " + SmokeContact2LastName)) {
										appLog.info(SmokeContact2FirstName + " " + SmokeContact2LastName + " Page is opened");
									} else {
										appLog.info(SmokeContact2FirstName + " " + SmokeContact2LastName + " Page is not opened");
										sa.assertTrue(false,
												SmokeContact2FirstName + " " + SmokeContact2LastName + " Page is not opened");
									}
								} else {
									appLog.info("Not able to find page header");
									sa.assertTrue(false, "Not able to find page header");
								}
								driver.close();
								driver.switchTo().window(parentID);
								switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
							} else {
								appLog.info("No new window is open");
								sa.assertTrue(false, "No new window is open");
							}
							
						}else {
							appLog.error("Not able to click on contact name link so cannot verify contact page");
							sa.assertTrue(false, "Not able to click on contact name link so cannot verify contact page");
						}
					}else {
						appLog.error("contact name link is not visible so cannot verify contact page");
						sa.assertTrue(false, "contact name link is not visible so cannot verify contact page");
					}
					ele=FindElement(driver, "//a[@title='"+standrdfile[0]+"']/../../following-sibling::span//a[@title='"+SmokeInstitution1+"']", "view link", action.BOOLEAN, 10);
					if(ele!=null) {
						if (click(driver,ele, "firm name link", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Go to firm button");
							String parentID = switchOnWindow(driver);
							if (parentID != null) {
								ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
								if (ele != null) {
									if (ele.getText().trim().equalsIgnoreCase(SmokeInstitution1)) {
										appLog.info(SmokeInstitution1 + " Page is opened");
									} else {
										appLog.info(SmokeInstitution1 + " Page is not opened");
										sa.assertTrue(false, SmokeInstitution1 + " Page is not opened");
									}
								} else {
									appLog.info("Not able to find page header");
									sa.assertTrue(false, "Not able to find page header");
								}
								driver.close();
								driver.switchTo().window(parentID);
								switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
							} else {
								appLog.info("No new window is open");
								sa.assertTrue(false, "No new window is open");
							}
						} else {
							appLog.info("Not able to click on got ot firm button");
							sa.assertTrue(false, "Not able to lcick on go to firm button");
						}

					}else {
						appLog.error("institution name is not visible so cannot click on it ");
						sa.assertTrue(false, "institution name is not visible so cannot click on it ");
					}
					if (fp.clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Open, standrdfile[1], Workspace.InvestorWorkspace, 10, null)) {
						appLog.info("clicked on file "+standrdfile[1]);
						ThreadSleep(2000);
						String parentID = switchOnWindow(driver);
						if (parentID != null) {
							if(fp.getDownloadLink(30)!=null) {
								appLog.info("download button is displayed ");
							}else {
								appLog.error("Download button is not displayed ");
								sa.assertTrue(false, "Download button is not displayed ");
							}
							driver.close();
							driver.switchTo().window(parentID);
							switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
						} else {
							appLog.info("No new window is open");
							sa.assertTrue(false, "No new window is open");
						}
					}else {
						appLog.error("Not able to click on "+standrdfile[1]+" file down arrow open so cannot open file ");
						sa.assertTrue(false, "Not able to click on "+standrdfile[1]+" file down arrow open so cannot open file ");
					}
					for(int i=0; i<2; i++) {
						if (fp.clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Delete, standrdfile[1], Workspace.InvestorWorkspace, 10, null)) {
							appLog.info("clicked on file "+standrdfile[1]+" delete option ");
							ThreadSleep(2000);
							if(i==1) {
								if (click(driver, fp.getDocumentDeleteYesBtn(Workspace.InvestorWorkspace,60), "Yes button", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on delete pop up yes button");
								}else {
									appLog.error("Not able to click on delete pop up yes button so cannot delete file "+standrdfile[1]);
									sa.assertTrue(false, "Not able to click on delete pop up yes button so cannot delete file "+standrdfile[1]);
								}
							}
							if(i==0) {
								if (click(driver, fp.getDocumentDeleteNoBtn(Workspace.InvestorWorkspace, 10), "No button", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on delete pop up no button");
								}else {
									appLog.error("Not able to click on delete pop up no button so cannot close pop up");
									sa.assertTrue(false, "Not able to click on delete pop up no button so cannot clsoe pop up");
								}
							}
						}else {
							appLog.error("Not able to click on "+standrdfile[1]+" file down arrow open so cannot delete file ");
							sa.assertTrue(false, "Not able to click on "+standrdfile[1]+" file down arrow open so cannot delete file ");
						}
						
					}
					if (click(driver, fp.getAlertHistoryLink(Workspace.InvestorWorkspace, PageName.ContactsPage, 60),"Alert History Link", action.SCROLLANDBOOLEAN)) {
						switchToDefaultContent(driver);
						switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
						if (bp.clickOnActiivityTypeLinkBasedOnContact("Contact Profile Updated",SmokeContact2FirstName + " " + SmokeContact2LastName)) {
							appLog.info("Clicked on activity type");
							if (click(driver, bp.getGoToContactButton(PageName.ContactsPage,Workspace.InvestorWorkspace,60), "Go to Contact button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on Go to Contact button");
								String parentID = switchOnWindow(driver);
								if (parentID != null) {
									ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
									if (ele != null) {
										if (ele.getText().trim().equalsIgnoreCase(SmokeContact2FirstName + " " + SmokeContact2LastName)) {
											appLog.info(SmokeContact2FirstName + " " + SmokeContact2LastName + " Page is opened");
										} else {
											appLog.info(SmokeContact2FirstName + " " + SmokeContact2LastName + " Page is not opened");
											sa.assertTrue(false,
													SmokeContact2FirstName + " " + SmokeContact2LastName + " Page is not opened");
										}
									} else {
										appLog.info("Not able to find page header");
										sa.assertTrue(false, "Not able to find page header");
									}
									driver.close();
									driver.switchTo().window(parentID);
									switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
								} else {
									appLog.info("No new window is open");
									sa.assertTrue(false, "No new window is open");
								}
							} else {
								appLog.info("Not able to click on go to Contact button");
								sa.assertTrue(false, "Not able to click on go to contact button");
							}
							if (click(driver, bp.getContactProfileUpdateCloseButton(Workspace.InvestorWorkspace,PageName.ContactsPage, 60), "Close Button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on close icon");
								if (bp.getContactProfileUpdateCloseButton(Workspace.InvestorWorkspace,PageName.ContactsPage, 2) == null) {
									appLog.info("Contact profile updated popup get closed");
								} else {
									appLog.info("Contact Profile updtaed popup does not get closed");
									sa.assertTrue(false, "Contact Profile updtaed popup does not get closed");
								}
							} else {
								appLog.info("Not able to click on close icon");
								sa.assertTrue(false, "Not able to click on close icon");
							}

						} else {
							appLog.info("Not able to click on activity type");
							sa.assertTrue(false, "Not able to click on activity type");
						}
						if (bp.clickOnActiivityTypeLinkBasedOnContact("Firm Profile Updated",SmokeContact2FirstName + " " + SmokeContact2LastName)) {
							appLog.info("Clicked on activity type");
							if (click(driver, bp.getGoToFirmButton(PageName.ContactsPage,Workspace.InvestorWorkspace,60), "Go to firm button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on Go to firm button");
								String parentID = switchOnWindow(driver);
								if (parentID != null) {
									ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
									if (ele != null) {
										if (ele.getText().trim().equalsIgnoreCase(SmokeInstitution1)) {
											appLog.info(SmokeInstitution1 + " Page is opened");
										} else {
											appLog.info(SmokeInstitution1 + " Page is not opened");
											sa.assertTrue(false, SmokeInstitution1 + " Page is not opened");
										}
									} else {
										appLog.info("Not able to find page header");
										sa.assertTrue(false, "Not able to find page header");
									}
									driver.close();
									driver.switchTo().window(parentID);
									switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
								} else {
									appLog.info("No new window is open");
									sa.assertTrue(false, "No new window is open");
								}
							} else {
								appLog.info("Not able to click on got ot firm button");
								sa.assertTrue(false, "Not able to lcick on go to firm button");
							}
							if (click(driver,bp.getFirmProfileUpdateCloseButton(Workspace.InvestorWorkspace, PageName.ContactsPage, 60),"Close Button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on close icon");
								if (bp.getFirmProfileUpdateCloseButton(Workspace.InvestorWorkspace, PageName.ContactsPage,10) == null) {
									appLog.info("Firm profile updated popup get closed");
								} else {
									appLog.info("Firm Profile updtaed popup does not get closed");
									sa.assertTrue(false, "Firm Profile updtaed popup does not get closed");
								}
							} else {
								appLog.info("Not able to click on close icon");
								sa.assertTrue(false, "Not able to click on close icon");
							}

						} else {
							appLog.info("Not able to click on activity type");
							sa.assertTrue(false, "Not able to click on activity type");
						}
						ele=FindElement(driver, "//a[text()='"+SmokeContact2FirstName+" "+SmokeContact2LastName+"']", "contact name ", action.SCROLLANDBOOLEAN, 10);
						if(ele!=null) {
							if(click(driver, ele, "contact name link", action.BOOLEAN)) {
								String parentID = switchOnWindow(driver);
								if (parentID != null) {
									ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
									if (ele != null) {
										if (ele.getText().trim().equalsIgnoreCase(SmokeContact2FirstName + " " + SmokeContact2LastName)) {
											appLog.info(SmokeContact2FirstName + " " + SmokeContact2LastName + " Page is opened");
										} else {
											appLog.info(SmokeContact2FirstName + " " + SmokeContact2LastName + " Page is not opened");
											sa.assertTrue(false,
													SmokeContact2FirstName + " " + SmokeContact2LastName + " Page is not opened");
										}
									} else {
										appLog.info("Not able to find page header");
										sa.assertTrue(false, "Not able to find page header");
									}
									driver.close();
									driver.switchTo().window(parentID);
									switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
								} else {
									appLog.info("No new window is open");
									sa.assertTrue(false, "No new window is open");
								}
								
							}else {
								appLog.error("Not able to click on contact name link so cannot verify contact page");
								sa.assertTrue(false, "Not able to click on contact name link so cannot verify contact page");
							}
						}else {
							appLog.error("contact name link is not visible so cannot verify contact page");
							sa.assertTrue(false, "contact name link is not visible so cannot verify contact page");
						}
						ele=FindElement(driver, "//a[text()='"+standrdfile[0]+"']", standrdfile[0]+" file name link ", action.SCROLLANDBOOLEAN, 10);
						if(ele!=null) {
							if(click(driver, ele, standrdfile[0]+" name link", action.BOOLEAN)) {
								ThreadSleep(2000);
								String parentID = switchOnWindow(driver);
								if (parentID != null) {
									if(fp.getDownloadLink(30)!=null) {
										appLog.info("download button is displayed ");
									}else {
										appLog.error("Download button is not displayed ");
										sa.assertTrue(false, "Download button is not displayed ");
									}
									
									driver.close();
									driver.switchTo().window(parentID);
									switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
								} else {
									appLog.info("No new window is open");
									sa.assertTrue(false, "No new window is open");
								}
								
							}else {
								appLog.error("Not able to click on file name link "+standrdfile[0]);
								sa.assertTrue(false, "Not able to click on file name link "+standrdfile[0]);
							}
						}else {
							appLog.error(standrdfile[0]+" file is not visible so cannot click on it ");
							sa.assertTrue(false, standrdfile[0]+" file is not visible so cannot click on it ");
						}
						
						if(selectVisibleTextFromDropDown(driver, contact.getAlertHistoryRangeDropDownList(Workspace.InvestorWorkspace, 30), "Range dropdown", "Last 30 Days")){
							ThreadSleep(2000);
							appLog.info("Last 30 Days Option is selected in range drop down list");
							if(getSelectedOptionOfDropDown(driver,  contact.getAlertHistoryRangeDropDownList(Workspace.InvestorWorkspace, 30), "Range dropdown", "text").contains("Last 30 Days")) {
								appLog.info("Last 30 Days Option is verified");

							}else {
								appLog.error("Last 30 Days Option Option is not selected in fund alert grid drop down list ");
								sa.assertTrue(false, "Last 30 Days Option Option is not selected in fund alert grid drop down list ");
							}
						}else{
							appLog.info("Not able to select range dropodwn value");
							sa.assertTrue(false, "Not able to select range dropdown value ");
						}
				
				
					
						if(selectVisibleTextFromDropDown(driver, contact.getAlertHistoryShowDropDownList(Workspace.InvestorWorkspace,60), "Show dropdown", "All Except Profile Updates")){
							ThreadSleep(2000);
							appLog.info("All Except Profile Update Option is selected ");
							if(getSelectedOptionOfDropDown(driver, contact.getAlertHistoryShowDropDownList(Workspace.InvestorWorkspace,60), "Show DropDown List", "text").contains("All Except Profile Updates")) {
								appLog.info("All Except Profile Update Option is verified");
								
							}else {
								appLog.error("All Except Profile Update Option is not selected in home alert grid drop down list ");
								sa.assertTrue(false, "All Except Profile Update Option is not selected in home alert grid drop down list ");
							}
						}else{
							appLog.error("Not able to select show dropdown value");
							sa.assertTrue(false, "Not able to select dropdown value");
						}
						
						
						
						
						if(click(driver, bp.getAlertHistoryCrossIcon(Workspace.InvestorWorkspace, 60), "Alert history cross icon", action.SCROLLANDBOOLEAN)){
							appLog.info("Clicked on alert history aross icon");
						}else{
							appLog.error("Not able to click on alert history cross icon");
							sa.assertTrue(false, "Not able to click on alert history cross icon");
						}	
					}else {
						appLog.error("Not able to click on alter history link so cannot check pop up");
						sa.assertTrue(false, "Not able to click on alter history link so cannot check pop up");
					}
				}else {
					appLog.error("Not able to click on folder path "+standrdFolder);
					sa.assertTrue(false, "Not able to click on folder path "+standrdFolder);
				}
			}else {
				appLog.error("Not able to click on created contact "+SmokeContact2FirstName+" "+SmokeContact2LastName);
				sa.assertTrue(false, "Not able to click on created contact "+SmokeContact2FirstName+" "+SmokeContact2LastName);
			}
			
		}else {
			appLog.error("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}
		switchToDefaultContent(driver);
		if(contact.clickOnTab(TabName.ContactTab)) {
			if(contact.clickOnCreatedContact(SmokeContact3FirstName, SmokeContact3LastName, null)) {
				switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 60));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),Workspace.InvestorWorkspace.toString() + " View.");
				
				if(click(driver, contact.getRemoveContactAccessButton(Workspace.InvestorWorkspace, 60), "Remove contact access button", action.SCROLLANDBOOLEAN)){
					appLog.info("clicked on removed contact access button");
					ThreadSleep(3000);
					WebElement ele=FindElement(driver,"//label[text()='"+SmokeFundName1+"']/../..//a[@title='Remove']", "Fund 1 Remove link", action.SCROLLANDBOOLEAN, 60);
					if(ele!=null) {
						if(click(driver, ele, "Remove Link", action.SCROLLANDBOOLEAN)){
							String ParentID=switchOnWindow(driver);
							if(ParentID!=null){
								ThreadSleep(5000);
								switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
								driver.switchTo().window(ParentID);
								refresh(driver);
								switchToFrame(driver, 30, bp.getFrame(PageName.ContactsPage, 30));
								scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Fundraising Workspace Section view");
							}else{
								appLog.info("No new window to switch");
								sa.assertTrue(false, "No new window to switch");
							}
						}else{
							appLog.info("Not bale ot click on remove link");
							sa.assertTrue(false, "Not able to click on remove link");
						}

					}else {
						appLog.error("Not able to find remove link so cannot remove contact access");
						sa.assertTrue(false, "Not able to find remove link so cannot remove contact access");
					}
				}else{
					appLog.error("Not able to clcik on remove contact access button");
					sa.assertTrue(false, "Not able to clcik on remove contact access button");
				}
				if (bp.verifyErrorMessageOnPage(ContactPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace,contact.getErrorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace(60),
						"Error Message after admin Registration on Contact page for investor workspace")) {
					appLog.info("Error Message is verified  on Contact page for investor workspace");
				} else {
					appLog.error("Error Message is not verified on Contact page for investor workspace.Expected:"+ ContactPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace+ " Actual"+ getText(driver,contact.getErrorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace(60),"Error Message after admin registration on Contact page for investor workspace",action.BOOLEAN));
					sa.assertTrue(false,"Error Message is not verified on Contact page for investor workspace.Expected:"+ ContactPageErrorMessage.errorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace+ " Actual"+ getText(driver,contact.getErrorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace(60),"Error Message after admin registration on Contact page for investor workspace",action.BOOLEAN));
				}
				switchToDefaultContent(driver);
			}else {
				appLog.error("Not able to click on created contact "+SmokeContact3FirstName+" "+SmokeContact3LastName);
				sa.assertTrue(false, "Not able to click on created contact "+SmokeContact3FirstName+" "+SmokeContact3LastName);
			}
		}else {
			appLog.error("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}
		lp.CRMlogout(environment,mode);
		sa.assertAll();
		
	}

	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc035_checkLinkOnInstitutionPage(String environment, String mode) {
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ins = new InstitutionPageBusinessLayer(driver);
		String standrdFolder=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String[] standrdfile=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard).split("<break>");
		lp.CRMLogin(SmokeCRMUser2Email,SmokePassword);
		if(ins.clickOnTab(TabName.InstituitonsTab)) {
			if(ins.clickOnCreatedInstitution(SmokeInstitution1)) {
				switchToFrame(driver, 30, bp.getFrame(PageName.InstitutionsPage, 60));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),Workspace.InvestorWorkspace.toString() + " View.");
				if(fp.verifyFolderPathdummy(standrdFolder,UpdatedSmokeLP1,null, SmokeFundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 60)){
					if (bp.verifyDownloadFunctionality(PageName.InstitutionsPage, Workspace.InvestorWorkspace, standrdfile[0], true, false,false)) {
						appLog.info("download button is successfully verified");
						switchToFrame(driver, 30, bp.getFrame(PageName.InstitutionsPage, 60));
					}
					else {
						appLog.error("download button is not successfully verified");
						sa.assertTrue(false, "download button is not successfully verified");
						switchToFrame(driver, 30, bp.getFrame(PageName.InstitutionsPage, 60));
					}
					
					String 	date = getSystemDate("MM/dd/yyyy")+previousOrForwardDate(-1, "MM/dd/yyyy");
					if (bp.enterValueAndClickonSearchBoxContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace,standrdfile[0], 30)) {
						ThreadSleep(2000);
						SoftAssert saa = bp.verifyContentGridForSearch(driver, SmokeInstitution1+" > "+UpdatedSmokeLP1+" > "+standrdFolder, standrdfile[0], date);
						sa.combineAssertions(saa);
					}else {
						appLog.error("Not able to search file : "+standrdfile[0]+" in FR workspace");
						sa.assertTrue(false, "Not able to search file : "+standrdfile[0]+" in FR workspace");
					}
					if (click(driver, bp.getSearchPopCrossIcon(30), "Search Cross Icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on search pop up cross icon");
					}else {
						appLog.error("Not able to click search pop up cross icon so cannot close pop up");
						sa.assertTrue(false, "Not able to click search pop up cross icon so cannot close pop up");
					}
					
					
					WebElement ele=FindElement(driver, "//a[@title='"+standrdfile[0]+"']/../../following-sibling::span//a/u[text()='View']", "view link", action.BOOLEAN, 10);
					if(ele!=null) {
						if(click(driver, ele, "view link", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on view link");
							
							if(selectVisibleTextFromDropDown(driver, fp.getDocumentStatisticsRangeDropDownList(30), "Range dropdown", "Last 30 Days")){
								ThreadSleep(2000);
								appLog.info("Last 30 Days Option is selected in range drop down list");
								if(getSelectedOptionOfDropDown(driver,  fp.getDocumentStatisticsRangeDropDownList(30), "Range dropdown", "text").contains("Last 30 Days")) {
									appLog.info("Last 30 Days Option is verified");

								}else {
									appLog.error("Last 30 Days Option Option is not selected in view statistics drop down list ");
									sa.assertTrue(false, "Last 30 Days Option Option is not selected in view statistics drop down list ");
								}
							}else{
								appLog.info("Not able to select range dropodwn value");
								sa.assertTrue(false, "Not able to select range dropdown value ");
							}
					
					
						
							if(selectVisibleTextFromDropDown(driver, fp.getDocumentStatisticsViewDropDownList(30), "Show dropdown", "Document Downloads")){
								ThreadSleep(2000);
								appLog.info("Document Downloads Option is selected ");
								if(getSelectedOptionOfDropDown(driver,fp.getDocumentStatisticsViewDropDownList(30), "Show DropDown List", "text").contains("Document Downloads")) {
									appLog.info("Document Downloads Option is verified");
									
								}else {
									appLog.error("Document Downloads Option is not selected in view statistics drop down list ");
									sa.assertTrue(false, "Document Downloads Option is not selected in view statistics drop down list ");
								}
							}else{
								appLog.error("Not able to select show dropdown value");
								sa.assertTrue(false, "Not able to select dropdown value");
							}
							
							
							if(click(driver, fp.getDocumentStatisticsPopUpCloseBtn(10), "close button", action.BOOLEAN)) {
								appLog.info("clicked on document statistics close button");
							}else {
								appLog.error("Not able to click on document statistics close button so cannot close pop up");
								sa.assertTrue(false, "Not able to click on document statistics close button so cannot close pop up");
							}
						}else {
							appLog.error("Not able to click on view link so cannot verify pop up");
							sa.assertTrue(false, "Not able to click on view link so cannot verify pop up");
						}
					}else {
						appLog.error("statistics view link is not visible so cannot click on it ");
						sa.assertTrue(false, "statistics view link is not visible so cannot click on it ");
					}
					ele=FindElement(driver, "//a[@title='"+standrdfile[0]+"']/../../following-sibling::span//a[@title='"+SmokeContact2FirstName+" "+SmokeContact2LastName+"']", "view link", action.BOOLEAN, 10);
					if(ele!=null) {
						if(click(driver, ele, "contact name link", action.BOOLEAN)) {
							String parentID = switchOnWindow(driver);
							if (parentID != null) {
								ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
								if (ele != null) {
									if (ele.getText().trim().equalsIgnoreCase(SmokeContact2FirstName + " " + SmokeContact2LastName)) {
										appLog.info(SmokeContact2FirstName + " " + SmokeContact2LastName + " Page is opened");
									} else {
										appLog.info(SmokeContact2FirstName + " " + SmokeContact2LastName + " Page is not opened");
										sa.assertTrue(false,
												SmokeContact2FirstName + " " + SmokeContact2LastName + " Page is not opened");
									}
								} else {
									appLog.info("Not able to find page header");
									sa.assertTrue(false, "Not able to find page header");
								}
								driver.close();
								driver.switchTo().window(parentID);
								switchToFrame(driver, 30, bp.getFrame(PageName.InstitutionsPage, 60));
							} else {
								appLog.info("No new window is open");
								sa.assertTrue(false, "No new window is open");
							}
							
						}else {
							appLog.error("Not able to click on contact name link so cannot verify contact page");
							sa.assertTrue(false, "Not able to click on contact name link so cannot verify contact page");
						}
					}else {
						appLog.error("contact name link is not visible so cannot verify contact page");
						sa.assertTrue(false, "contact name link is not visible so cannot verify contact page");
					}
					ele=FindElement(driver, "//a[@title='"+standrdfile[0]+"']/../../following-sibling::span//a[@title='"+SmokeInstitution1+"']", "view link", action.BOOLEAN, 10);
					if(ele!=null) {
						if (click(driver,ele, "firm name link", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Go to firm button");
							String parentID = switchOnWindow(driver);
							if (parentID != null) {
								ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
								if (ele != null) {
									if (ele.getText().trim().equalsIgnoreCase(SmokeInstitution1)) {
										appLog.info(SmokeInstitution1 + " Page is opened");
									} else {
										appLog.info(SmokeInstitution1 + " Page is not opened");
										sa.assertTrue(false, SmokeInstitution1 + " Page is not opened");
									}
								} else {
									appLog.info("Not able to find page header");
									sa.assertTrue(false, "Not able to find page header");
								}
								driver.close();
								driver.switchTo().window(parentID);
								switchToFrame(driver, 30, bp.getFrame(PageName.InstitutionsPage, 60));
							} else {
								appLog.info("No new window is open");
								sa.assertTrue(false, "No new window is open");
							}
						} else {
							appLog.info("Not able to click on got ot firm button");
							sa.assertTrue(false, "Not able to lcick on go to firm button");
						}

					}else {
						appLog.error("institution name is not visible so cannot click on it ");
						sa.assertTrue(false, "institution name is not visible so cannot click on it ");
					}
					if (fp.clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Open, standrdfile[1], Workspace.InvestorWorkspace, 10, null)) {
						appLog.info("clicked on file "+standrdfile[1]);
						ThreadSleep(2000);
						String parentID = switchOnWindow(driver);
						if (parentID != null) {
							if(fp.getDownloadLink(30)!=null) {
								appLog.info("download button is displayed ");
							}else {
								appLog.error("Download button is not displayed ");
								sa.assertTrue(false, "Download button is not displayed ");
							}
							driver.close();
							driver.switchTo().window(parentID);
							switchToFrame(driver, 30, bp.getFrame(PageName.InstitutionsPage, 60));
						} else {
							appLog.info("No new window is open");
							sa.assertTrue(false, "No new window is open");
						}
					}else {
						appLog.error("Not able to click on "+standrdfile[1]+" file down arrow open so cannot open file ");
						sa.assertTrue(false, "Not able to click on "+standrdfile[1]+" file down arrow open so cannot open file ");
					}
					for(int i=0; i<2; i++) {
						if (fp.clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Delete, standrdfile[1], Workspace.InvestorWorkspace, 10, null)) {
							appLog.info("clicked on file "+standrdfile[1]+" delete option ");
							ThreadSleep(2000);
							if(i==1) {
								if (click(driver, fp.getDocumentDeleteYesBtn(Workspace.InvestorWorkspace,60), "Yes button", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on delete pop up yes button");
								}else {
									appLog.error("Not able to click on delete pop up yes button so cannot delete file "+standrdfile[1]);
									sa.assertTrue(false, "Not able to click on delete pop up yes button so cannot delete file "+standrdfile[1]);
								}
							}
							if(i==0) {
								if (click(driver, fp.getDocumentDeleteNoBtn(Workspace.InvestorWorkspace, 10), "No button", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on delete pop up no button");
								}else {
									appLog.error("Not able to click on delete pop up no button so cannot close pop up");
									sa.assertTrue(false, "Not able to click on delete pop up no button so cannot clsoe pop up");
								}
							}
						}else {
							appLog.error("Not able to click on "+standrdfile[1]+" file down arrow open so cannot delete file ");
							sa.assertTrue(false, "Not able to click on "+standrdfile[1]+" file down arrow open so cannot delete file ");
						}
						
					}
				}else {
					appLog.error("Not able to click on folder path "+standrdFolder);
					sa.assertTrue(false, "Not able to click on folder path "+standrdFolder);
				}
			}else {
				appLog.error("Not able to click on created institution "+SmokeInstitution1);
				sa.assertTrue(false, "Not able to click on created institution "+SmokeInstitution1);
			}
			
		}else {
			appLog.error("Not able to click on institution tab");
			sa.assertTrue(false, "Not able to click on institution tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc036_checkLinkOnLPPage(String environment, String mode) {
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ins = new InstitutionPageBusinessLayer(driver);
		String standrdFolder=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String[] standrdfile=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard).split("<break>");
		String CommonFolder=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String Commonfile=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
		lp.CRMLogin(SmokeCRMUser2Email,SmokePassword);
		if(ins.clickOnTab(TabName.InstituitonsTab)) {
			if(ins.clickOnCreatedLP(SmokeLimitedPartner1)) {
				switchToFrame(driver, 30, bp.getFrame(PageName.InstitutionsPage, 60));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),Workspace.InvestorWorkspace.toString() + " View.");
				if(fp.verifyFolderPathdummy(standrdFolder,null,null, SmokeFundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 60)){
					if (bp.verifyDownloadFunctionality(PageName.InstitutionsPage, Workspace.InvestorWorkspace, standrdfile[0], true, false,false)) {
						appLog.info("download button is successfully verified");
						switchToFrame(driver, 30, bp.getFrame(PageName.InstitutionsPage, 60));
					}
					else {
						appLog.error("download button is not successfully verified");
						sa.assertTrue(false, "download button is not successfully verified");
						switchToFrame(driver, 30, bp.getFrame(PageName.InstitutionsPage, 60));
					}
					String 	date = getSystemDate("MM/dd/yyyy")+previousOrForwardDate(-1, "MM/dd/yyyy");
					if (bp.enterValueAndClickonSearchBoxContentGrid(PageName.InstitutionsPage, Workspace.InvestorWorkspace,standrdfile[0], 30)) {
						
						SoftAssert saa = bp.verifyContentGridForSearch(driver, SmokeInstitution1+" > "+UpdatedSmokeLP1+" > "+standrdFolder, standrdfile[0], date);
						sa.combineAssertions(saa);
					}else {
						appLog.error("Not able to search file : "+standrdfile[0]+" in FR workspace");
						sa.assertTrue(false, "Not able to search file : "+standrdfile[0]+" in FR workspace");
					}
					ThreadSleep(2000);
					if (click(driver, bp.getSearchPopCrossIcon(30), "Search Cross Icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on search pop up cross icon");
					}else {
						appLog.error("Not able to click search pop up cross icon so cannot close pop up");
						sa.assertTrue(false, "Not able to click search pop up cross icon so cannot close pop up");
					}
					
					WebElement ele=FindElement(driver, "//a[@title='"+standrdfile[0]+"']/../../following-sibling::span//a/u[text()='View']", "view link", action.BOOLEAN, 10);
					if(ele!=null) {
						if(click(driver, ele, "view link", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on view link");
							if(selectVisibleTextFromDropDown(driver, fp.getDocumentStatisticsRangeDropDownList(30), "Range dropdown", "Last 30 Days")){
								ThreadSleep(2000);
								appLog.info("Last 30 Days Option is selected in range drop down list");
								if(getSelectedOptionOfDropDown(driver,  fp.getDocumentStatisticsRangeDropDownList(30), "Range dropdown", "text").contains("Last 30 Days")) {
									appLog.info("Last 30 Days Option is verified");

								}else {
									appLog.error("Last 30 Days Option Option is not selected in view statistics drop down list ");
									sa.assertTrue(false, "Last 30 Days Option Option is not selected in view statistics drop down list ");
								}
							}else{
								appLog.info("Not able to select range dropodwn value");
								sa.assertTrue(false, "Not able to select range dropdown value ");
							}
					
					
						
							if(selectVisibleTextFromDropDown(driver, fp.getDocumentStatisticsViewDropDownList(30), "Show dropdown", "Document Downloads")){
								ThreadSleep(2000);
								appLog.info("Document Downloads Option is selected ");
								if(getSelectedOptionOfDropDown(driver,fp.getDocumentStatisticsViewDropDownList(30), "Show DropDown List", "text").contains("Document Downloads")) {
									appLog.info("Document Downloads Option is verified");
									
								}else {
									appLog.error("Document Downloads Option is not selected in view statistics drop down list ");
									sa.assertTrue(false, "Document Downloads Option is not selected in view statistics drop down list ");
								}
							}else{
								appLog.error("Not able to select show dropdown value");
								sa.assertTrue(false, "Not able to select dropdown value");
							}
							
							if(click(driver, fp.getDocumentStatisticsPopUpCloseBtn(10), "close button", action.BOOLEAN)) {
								appLog.info("clicked on document statistics close button");
							}else {
								appLog.error("Not able to click on document statistics close button so cannot close pop up");
								sa.assertTrue(false, "Not able to click on document statistics close button so cannot close pop up");
							}
						}else {
							appLog.error("Not able to click on view link so cannot verify pop up");
							sa.assertTrue(false, "Not able to click on view link so cannot verify pop up");
						}
					}else {
						appLog.error("statistics view link is not visible so cannot click on it ");
						sa.assertTrue(false, "statistics view link is not visible so cannot click on it ");
					}
					ele=FindElement(driver, "//a[@title='"+standrdfile[0]+"']/../../following-sibling::span//a[@title='"+SmokeContact2FirstName+" "+SmokeContact2LastName+"']", "view link", action.BOOLEAN, 10);
					if(ele!=null) {
						if(click(driver, ele, "contact name link", action.BOOLEAN)) {
							String parentID = switchOnWindow(driver);
							if (parentID != null) {
								ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
								if (ele != null) {
									if (ele.getText().trim().equalsIgnoreCase(SmokeContact2FirstName + " " + SmokeContact2LastName)) {
										appLog.info(SmokeContact2FirstName + " " + SmokeContact2LastName + " Page is opened");
									} else {
										appLog.info(SmokeContact2FirstName + " " + SmokeContact2LastName + " Page is not opened");
										sa.assertTrue(false,
												SmokeContact2FirstName + " " + SmokeContact2LastName + " Page is not opened");
									}
								} else {
									appLog.info("Not able to find page header");
									sa.assertTrue(false, "Not able to find page header");
								}
								driver.close();
								driver.switchTo().window(parentID);
								switchToFrame(driver, 30, bp.getFrame(PageName.InstitutionsPage, 60));
							} else {
								appLog.info("No new window is open");
								sa.assertTrue(false, "No new window is open");
							}
							
						}else {
							appLog.error("Not able to click on contact name link so cannot verify contact page");
							sa.assertTrue(false, "Not able to click on contact name link so cannot verify contact page");
						}
					}else {
						appLog.error("contact name link is not visible so cannot verify contact page");
						sa.assertTrue(false, "contact name link is not visible so cannot verify contact page");
					}
					ele=FindElement(driver, "//a[@title='"+standrdfile[0]+"']/../../following-sibling::span//a[@title='"+SmokeInstitution1+"']", "firm name link", action.BOOLEAN, 10);
					if(ele!=null) {
						if (click(driver,ele, "view link", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Go to firm button");
							String parentID = switchOnWindow(driver);
							if (parentID != null) {
								ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
								if (ele != null) {
									if (ele.getText().trim().equalsIgnoreCase(SmokeInstitution1)) {
										appLog.info(SmokeInstitution1 + " Page is opened");
									} else {
										appLog.info(SmokeInstitution1 + " Page is not opened");
										sa.assertTrue(false, SmokeInstitution1 + " Page is not opened");
									}
								} else {
									appLog.info("Not able to find page header");
									sa.assertTrue(false, "Not able to find page header");
								}
								driver.close();
								driver.switchTo().window(parentID);
								switchToFrame(driver, 30, bp.getFrame(PageName.InstitutionsPage, 60));
							} else {
								appLog.info("No new window is open");
								sa.assertTrue(false, "No new window is open");
							}
						} else {
							appLog.info("Not able to click on got ot firm name");
							sa.assertTrue(false, "Not able to lcick on go to firm name");
						}

					}else {
						appLog.error("institution name is not visible so cannot click on it ");
						sa.assertTrue(false, "institution name is not visible so cannot click on it ");
					}
					if (fp.clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Open, standrdfile[1], Workspace.InvestorWorkspace, 10, null)) {
						appLog.info("clicked on file "+standrdfile[1]);
						ThreadSleep(2000);
						String parentID = switchOnWindow(driver);
						if (parentID != null) {
							if(fp.getDownloadLink(30)!=null) {
								appLog.info("download button is displayed ");
							}else {
								appLog.error("Download button is not displayed ");
								sa.assertTrue(false, "Download button is not displayed ");
							}
							driver.close();
							driver.switchTo().window(parentID);
							switchToFrame(driver, 30, bp.getFrame(PageName.InstitutionsPage, 60));
						} else {
							appLog.info("No new window is open");
							sa.assertTrue(false, "No new window is open");
						}
					}else {
						appLog.error("Not able to click on "+standrdfile[1]+" file down arrow open so cannot open file ");
						sa.assertTrue(false, "Not able to click on "+standrdfile[1]+" file down arrow open so cannot open file ");
					}
					
					if(fp.verifyFolderPathdummy(CommonFolder,null,null, SmokeFundName1, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 60)){
						appLog.info("clicked on Common folder path");
						for(int i=0; i<2; i++) {
							if (fp.clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Delete, Commonfile, Workspace.InvestorWorkspace, 10, null)) {
								appLog.info("clicked on file "+Commonfile+" delete option ");
								ThreadSleep(2000);
								if(i==1) {
									if (click(driver, fp.getDocumentDeleteYesBtn(Workspace.InvestorWorkspace,60), "Yes button", action.SCROLLANDBOOLEAN)) {
										appLog.info("clicked on delete pop up yes button");
									}else {
										appLog.error("Not able to click on delete pop up yes button so cannot delete file "+Commonfile);
										sa.assertTrue(false, "Not able to click on delete pop up yes button so cannot delete file "+Commonfile);
									}
								}
								if(i==0) {
									if (click(driver, fp.getDocumentDeleteNoBtn(Workspace.InvestorWorkspace, 10), "No button", action.SCROLLANDBOOLEAN)) {
										appLog.info("clicked on delete pop up no button");
									}else {
										appLog.error("Not able to click on delete pop up no button so cannot close pop up");
										sa.assertTrue(false, "Not able to click on delete pop up no button so cannot clsoe pop up");
									}
								}
							}else {
								appLog.error("Not able to click on "+Commonfile+" file down arrow open so cannot delete file ");
								sa.assertTrue(false, "Not able to click on "+Commonfile+" file down arrow open so cannot delete file ");
							}
							
						}
						
					}else {
						appLog.error("Not able to click on Common folder path so cannot delete file "+Commonfile);
						sa.assertTrue(false, "Not able to click on Common folder path so cannot delete file "+Commonfile);
					}
					
					
				}else {
					appLog.error("Not able to click on folder path "+standrdFolder);
					sa.assertTrue(false, "Not able to click on folder path "+standrdFolder);
				}
			}else {
				appLog.error("Not able to click on created LP "+SmokeLimitedPartner1);
				sa.assertTrue(false, "Not able to click on created LP "+SmokeLimitedPartner1);
			}
			
		}else {
			appLog.error("Not able to click on institution tab");
			sa.assertTrue(false, "Not able to click on institution tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" })
	@Test
	public void Smoketc037_checkLinkOnCommitmentPage(String environment, String mode) {
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		CommitmentPageBusinessLayer com = new CommitmentPageBusinessLayer(driver);
		String standrdFolder=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String[] standrdfile=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard).split("<break>");
		String CommonFolder=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String Commonfile=ExcelUtils.readData(smokeExcelPath,"FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
		lp.CRMLogin(SmokeCRMUser2Email,SmokePassword);
		if(com.clickOnTab(TabName.CommitmentsTab)) {
			if(com.clickOnCreatedCommitmentId(SmokeCommitment1Id)) {
				switchToFrame(driver, 30, bp.getFrame(PageName.CommitmentsPage, 60));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),Workspace.InvestorWorkspace.toString() + " View.");
				if(fp.verifyFolderPathdummy(standrdFolder,null,UpdatedSmokeLP1, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 60)){
					if (bp.verifyDownloadFunctionality(PageName.CommitmentsPage, Workspace.InvestorWorkspace, standrdfile[0], true, false,false)) {
						appLog.info("download button is successfully verified");
						switchToFrame(driver, 30, bp.getFrame(PageName.CommitmentsPage, 60));
					}
					else {
						appLog.error("download button is not successfully verified");
						sa.assertTrue(false, "download button is not successfully verified");
						switchToFrame(driver, 30, bp.getFrame(PageName.CommitmentsPage, 60));
					}
					
					String 	date = getSystemDate("MM/dd/yyyy")+previousOrForwardDate(-1, "MM/dd/yyyy");
					if (bp.enterValueAndClickonSearchBoxContentGrid(PageName.CommitmentsPage, Workspace.InvestorWorkspace,standrdfile[0], 30)) {
						
						SoftAssert saa = bp.verifyContentGridForSearch(driver, SmokeInstitution1+" > "+UpdatedSmokeLP1+" > "+standrdFolder, standrdfile[0], date);
						sa.combineAssertions(saa);
					}else {
						appLog.error("Not able to search file : "+standrdfile[0]+" in FR workspace");
						sa.assertTrue(false, "Not able to search file : "+standrdfile[0]+" in FR workspace");
					}
					ThreadSleep(2000);
					if (click(driver, bp.getSearchPopCrossIcon(30), "Search Cross Icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on search pop up cross icon");
					}else {
						appLog.error("Not able to click search pop up cross icon so cannot close pop up");
						sa.assertTrue(false, "Not able to click search pop up cross icon so cannot close pop up");
					}
					
					WebElement ele=FindElement(driver, "//a[@title='"+standrdfile[0]+"']/../../following-sibling::span//a[text()='View']", "view link", action.BOOLEAN, 10);
					if(ele!=null) {
						if(click(driver, ele, "view link", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on view link");
							
							if(selectVisibleTextFromDropDown(driver, fp.getDocumentStatisticsRangeDropDownList(30), "Range dropdown", "Last 30 Days")){
								ThreadSleep(2000);
								appLog.info("Last 30 Days Option is selected in range drop down list");
								if(getSelectedOptionOfDropDown(driver,  fp.getDocumentStatisticsRangeDropDownList(30), "Range dropdown", "text").contains("Last 30 Days")) {
									appLog.info("Last 30 Days Option is verified");

								}else {
									appLog.error("Last 30 Days Option Option is not selected in view statistics drop down list ");
									sa.assertTrue(false, "Last 30 Days Option Option is not selected in view statistics drop down list ");
								}
							}else{
								appLog.info("Not able to select range dropodwn value");
								sa.assertTrue(false, "Not able to select range dropdown value ");
							}
					
					
						
							if(selectVisibleTextFromDropDown(driver, fp.getDocumentStatisticsViewDropDownList(30), "Show dropdown", "Document Downloads")){
								ThreadSleep(2000);
								appLog.info("Document Downloads Option is selected ");
								if(getSelectedOptionOfDropDown(driver,fp.getDocumentStatisticsViewDropDownList(30), "Show DropDown List", "text").contains("Document Downloads")) {
									appLog.info("Document Downloads Option is verified");
									
								}else {
									appLog.error("Document Downloads Option is not selected in view statistics drop down list ");
									sa.assertTrue(false, "Document Downloads Option is not selected in view statistics drop down list ");
								}
							}else{
								appLog.error("Not able to select show dropdown value");
								sa.assertTrue(false, "Not able to select dropdown value");
							}
							
							
							if(click(driver, fp.getDocumentStatisticsPopUpCloseBtn(10), "close button", action.BOOLEAN)) {
								appLog.info("clicked on document statistics close button");
							}else {
								appLog.error("Not able to click on document statistics close button so cannot close pop up");
								sa.assertTrue(false, "Not able to click on document statistics close button so cannot close pop up");
							}
						}else {
							appLog.error("Not able to click on view link so cannot verify pop up");
							sa.assertTrue(false, "Not able to click on view link so cannot verify pop up");
						}
					}else {
						appLog.error("statistics view link is not visible so cannot click on it ");
						sa.assertTrue(false, "statistics view link is not visible so cannot click on it ");
					}
					ele=FindElement(driver, "//a[@title='"+standrdfile[0]+"']/../../following-sibling::span//a[@title='"+SmokeContact2FirstName+" "+SmokeContact2LastName+"']", "view link", action.BOOLEAN, 10);
					if(ele!=null) {
						if(click(driver, ele, "contact name link", action.BOOLEAN)) {
							String parentID = switchOnWindow(driver);
							if (parentID != null) {
								ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
								if (ele != null) {
									if (ele.getText().trim().equalsIgnoreCase(SmokeContact2FirstName + " " + SmokeContact2LastName)) {
										appLog.info(SmokeContact2FirstName + " " + SmokeContact2LastName + " Page is opened");
									} else {
										appLog.info(SmokeContact2FirstName + " " + SmokeContact2LastName + " Page is not opened");
										sa.assertTrue(false,
												SmokeContact2FirstName + " " + SmokeContact2LastName + " Page is not opened");
									}
								} else {
									appLog.info("Not able to find page header");
									sa.assertTrue(false, "Not able to find page header");
								}
								driver.close();
								driver.switchTo().window(parentID);
								switchToFrame(driver, 30, bp.getFrame(PageName.CommitmentsPage, 60));
							} else {
								appLog.info("No new window is open");
								sa.assertTrue(false, "No new window is open");
							}
							
						}else {
							appLog.error("Not able to click on contact name link so cannot verify contact page");
							sa.assertTrue(false, "Not able to click on contact name link so cannot verify contact page");
						}
					}else {
						appLog.error("contact name link is not visible so cannot verify contact page");
						sa.assertTrue(false, "contact name link is not visible so cannot verify contact page");
					}
					ele=FindElement(driver, "//a[@title='"+standrdfile[0]+"']/../../following-sibling::span//a[@title='"+SmokeInstitution1+"']", "firm name link", action.BOOLEAN, 10);
					if(ele!=null) {
						if (click(driver,ele, "view link", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Go to firm button");
							String parentID = switchOnWindow(driver);
							if (parentID != null) {
								ele = FindElement(driver, "//div[@class='content']", "Page Header", action.BOOLEAN, 40);
								if (ele != null) {
									if (ele.getText().trim().equalsIgnoreCase(SmokeInstitution1)) {
										appLog.info(SmokeInstitution1 + " Page is opened");
									} else {
										appLog.info(SmokeInstitution1 + " Page is not opened");
										sa.assertTrue(false, SmokeInstitution1 + " Page is not opened");
									}
								} else {
									appLog.info("Not able to find page header");
									sa.assertTrue(false, "Not able to find page header");
								}
								driver.close();
								driver.switchTo().window(parentID);
								switchToFrame(driver, 30, bp.getFrame(PageName.CommitmentsPage, 60));
							} else {
								appLog.info("No new window is open");
								sa.assertTrue(false, "No new window is open");
							}
						} else {
							appLog.info("Not able to click on got ot firm name");
							sa.assertTrue(false, "Not able to lcick on go to firm name");
						}

					}else {
						appLog.error("institution name is not visible so cannot click on it ");
						sa.assertTrue(false, "institution name is not visible so cannot click on it ");
					}
					if (fp.clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Open, standrdfile[1], Workspace.InvestorWorkspace, 10, null)) {
						appLog.info("clicked on file "+standrdfile[1]);
						ThreadSleep(2000);
						String parentID = switchOnWindow(driver);
						if (parentID != null) {
							if(fp.getDownloadLink(30)!=null) {
								appLog.info("download button is displayed ");
							}else {
								appLog.error("Download button is not displayed ");
								sa.assertTrue(false, "Download button is not displayed ");
							}
							driver.close();
							driver.switchTo().window(parentID);
							switchToFrame(driver, 30, bp.getFrame(PageName.CommitmentsPage, 60));
						} else {
							appLog.info("No new window is open");
							sa.assertTrue(false, "No new window is open");
						}
					}else {
						appLog.error("Not able to click on "+standrdfile[1]+" file down arrow open so cannot open file ");
						sa.assertTrue(false, "Not able to click on "+standrdfile[1]+" file down arrow open so cannot open file ");
					}
					if(fp.verifyFolderPathdummy(CommonFolder,null,null, SmokeFundName1, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 60)){
						appLog.info("clicked on Common folder path");
						for(int i=0; i<2; i++) {
							if (fp.clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Delete, Commonfile, Workspace.InvestorWorkspace, 10, null)) {
								appLog.info("clicked on file "+Commonfile+" delete option ");
								ThreadSleep(2000);
								if(i==1) {
									if (click(driver, fp.getDocumentDeleteYesBtn(Workspace.InvestorWorkspace,60), "Yes button", action.SCROLLANDBOOLEAN)) {
										appLog.info("clicked on delete pop up yes button");
									}else {
										appLog.error("Not able to click on delete pop up yes button so cannot delete file "+Commonfile);
										sa.assertTrue(false, "Not able to click on delete pop up yes button so cannot delete file "+Commonfile);
									}
								}
								if(i==0) {
									if (click(driver, fp.getDocumentDeleteNoBtn(Workspace.InvestorWorkspace, 10), "No button", action.SCROLLANDBOOLEAN)) {
										appLog.info("clicked on delete pop up no button");
									}else {
										appLog.error("Not able to click on delete pop up no button so cannot close pop up");
										sa.assertTrue(false, "Not able to click on delete pop up no button so cannot clsoe pop up");
									}
								}
							}else {
								appLog.error("Not able to click on "+Commonfile+" file down arrow open so cannot delete file ");
								sa.assertTrue(false, "Not able to click on "+Commonfile+" file down arrow open so cannot delete file ");
							}
							
						}
						
					}else {
						appLog.error("Not able to click on Common folder path so cannot delete file "+Commonfile);
						sa.assertTrue(false, "Not able to click on Common folder path so cannot delete file "+Commonfile);
					}
					
					
				}else {
					appLog.error("Not able to click on folder path "+standrdFolder);
					sa.assertTrue(false, "Not able to click on folder path "+standrdFolder);
				}
			}else {
				appLog.error("Not able to click on created LP "+SmokeLimitedPartner1);
				sa.assertTrue(false, "Not able to click on created LP "+SmokeLimitedPartner1);
			}
			
		}else {
			appLog.error("Not able to click on institution tab");
			sa.assertTrue(false, "Not able to click on institution tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc038_1_resetpasswordFromContactPage(String environment, String mode){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		ContactPageBusinessLayer cp=new ContactPageBusinessLayer(driver);
		lp.CRMLogin(SmokeCRMUser2Email, SmokePassword);
		if(bp.clickOnTab(TabName.ContactTab)) {
			if(cp.clickOnCreatedContact(SmokeContact2FirstName, SmokeContact2LastName, null)) {
				switchToFrame(driver, 30,bp. getFrame(PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), Workspace.InvestorWorkspace.toString()+" Section view");
			if(click(driver, cp.getResetPasswordActiveButton(Workspace.InvestorWorkspace, 60), "Reset password button", action.SCROLLANDBOOLEAN)) {
				if(cp.getResetPasswordpopupHeader(60)!=null){
					if(cp.getResetPasswordPopupsendMailRadioButton(60)!=null){
						appLog.info("send email radio button is displaying");
						if(isSelected(driver, cp.getResetPasswordPopupsendMailRadioButton(60), "Send Email radio button")){
							appLog.info("send email radio button is selected");
						}else{
							appLog.error("Send email radio button is not selected");
							sa.assertTrue(false, "Send email radio button is not selected");
						}		
					}else {
						appLog.error("Send email button is not displaying");
						sa.assertTrue(false, "Send email button is not displaying");
					}	
					if(click(driver, cp.getResetPasswordPopupCancelButton(60), "Cancel button", action.SCROLLANDBOOLEAN)){
						appLog.info("Clicked on cancel button");
						ThreadSleep(2000);
						if(cp.getResetPasswordpopupHeader(10)==null){
						appLog.info("Reset Password poup get closed successfully");
						}else{
							appLog.error("Reset Password popup does not get closed successfully");
							sa.assertTrue(false, "Reset Password popup does not get closed successfully");
						}
					}else{
						appLog.error("Not able to click on cancel button");
						sa.assertTrue(false, "Not bale ot click on cancel button");
					}						
				}else {
					appLog.error("Reset password popup is not displaying");
					sa.assertTrue(false, "Reset password popup is not displaying");
				}
			
				if(click(driver, cp.getResetPasswordActiveButton(Workspace.InvestorWorkspace, 60), "Reset password button", action.SCROLLANDBOOLEAN)) {
					if(click(driver, cp.getResetPasswordPopupSendEMailButton(60), "Send Email", action.SCROLLANDBOOLEAN)){
						if(cp.getResetPasswordPopupHeaderAfterSendEMail(60)!=null){
							if(cp.getResetPasswordPopupHeaderAfterSendEMail(60).getText().contains("Confirmation")){
								appLog.info("Confirmation Popup Header text is verified");
							}
							else{
								appLog.error("Confirmation Popup Header text is not verified");
								sa.assertTrue(false,"Confirmation Popup Header text is not verified");
							}							
							if(cp.getResetPasswordOKButton(60)!=null){
								appLog.info("OK button is available in Confirmation Popup");
							}
							else{
								appLog.error("OK button is not available in Confirmation Popup");
								sa.assertTrue(false,"OK button is not available in Confirmation Popup");
							}									
							if(click(driver, cp.getResetPasswordOKButton(60), "OK Button", action.SCROLLANDBOOLEAN)){
							appLog.info("Clicked on OK button");
							}else{
                         	   appLog.error("Not able to click on OK Button");
									sa.assertTrue(false, "Not able to click on OK button");
                            }
							SoftAssert saa=bp.verifyResetPasswordMailContent(SmokeContact2FirstName, SmokeContact2EmailId, "Contact Page", SmokeFirmName, SmokeCRMUser2Email,smokeExcelPath);
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
				
				}else {
					appLog.error("Not able to click on reset pasword button");
					sa.assertTrue(false, "Not able to click on reset pasword button");
				}
				if(click(driver, cp.getResetPasswordActiveButton(Workspace.InvestorWorkspace, 60), "Reset password button", action.SCROLLANDBOOLEAN)) {	
					if(click(driver, cp.getResetPasswordPopupcopyLinkRadioButton(60), "Copy email radio button", action.SCROLLANDBOOLEAN)){	
						if(click(driver, cp.getResetPasswordPopupCopyEmailButton(60), "Copy email button", action.SCROLLANDBOOLEAN)){	
							appLog.info("Clicked on copy email button");	
							String	 url=getClipBoardData();	
							switchToDefaultContent(driver);
								 String str = "window.open()";
									JavascriptExecutor jse = (JavascriptExecutor) driver;
									jse.executeScript(str, "");
								String	parentId = switchOnWindow(driver);
							if(parentId!=null){
							if(!url.isEmpty()){
							driver.get(url);
							if(cp.getResetPasswordButton(60)!=null) {
								appLog.info("Reset Password page is  open with Reset Password button");
							}else {
								appLog.error("Reset Password page is not open with Reset Password button");
								sa.assertTrue(false, "Reset Password page is not open with Reset Password button");
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
							
						}else{
							appLog.error("Not able to click on copy email button");
							sa.assertTrue(false, "Not able to click on copy email button");
						}
					}else{
						appLog.error("Not able to click on copy email radio button");
						sa.assertTrue(false, "Not able to click on copy email radio button");
					}
				}else {
					appLog.error("Not able to click on reset pasword button");
					sa.assertTrue(false, "Not able to click on reset pasword button");
				}
				
			}else {
				appLog.error("Not able to click on reset pasword button");
				sa.assertTrue(false, "Not able to click on reset pasword button");
			}
			}else {
				appLog.error("Not able to click on created contact");
				sa.assertTrue(false, "Not able to click on created contact");
			}
		}else {
			appLog.error("Not able to click on contact tab");
			sa.assertTrue(false, "Not able to click on contact tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();		
	}

	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc038_2_VerifySendingResetPasswordAndChangePassword(String environment, String mode){
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		String updatedPassword = ExcelUtils.readData(smokeExcelPath,"Contact",excelLabel.Variable_Name, "SmokeC2", excelLabel.Updated_Password);
		driver.get(ExcelUtils.readData(smokeExcelPath,"Contact",excelLabel.Variable_Name, "SmokeC2", excelLabel.Click_HereLink));		
	if(sendKeys(driver, bp.getResetPasswordNewPasswordTextBox(60), updatedPassword, "New password text box", action.SCROLLANDBOOLEAN)){
		if(sendKeys(driver, bp.getResetPasswordConfirmPasswordTextBox(60), updatedPassword, "Confirm password text box", action.SCROLLANDBOOLEAN)){
		if(click(driver, bp.getResetPasswordButton(60), "Reset Password button", action.SCROLLANDBOOLEAN)){
			ThreadSleep(4000);
			if (getURL(driver, 60).contains("login")) {
				appLog.info("Target Login page is open in same window. ");
			} else {
				appLog.error("Target Login page is not opening. " + getURL(driver, 30));
				sa.assertTrue(false, "Some different url is opening. " + getURL(driver, 30));
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
	
	
	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc039_1_closeWorkSpace(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(SmokeSuperAdminUserName,SmokePassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(SmokeFundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(fp.closeWorkSpace(Workspace.FundraisingWorkspace, 60)) {
					appLog.info("fundraising workspace is close successfully");
				}else {
					appLog.error("Fundraising Workspace is not closed");
					sa.assertTrue(false, "Fundraising Workspace is not closed");
				}
				switchToDefaultContent(driver);
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(fp.closeWorkSpace(Workspace.InvestorWorkspace, 60)) {
					appLog.info("investor workspace is close successfully");
				}else {
					appLog.error("investor Workspace is not closed");
					sa.assertTrue(false, "investor Workspace is not closed");
				}
			}else {
				appLog.error("Not able to click on created fund: "+SmokeFundName1+" so cannot close fundraising and investor workspace");
				sa.assertTrue(false, "Not able to click on created fund: "+SmokeFundName1+" so cannot close fundraising and investor workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot close fundraising and investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot close fundraising and investor workspace");
		}
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	

	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc039_2_checkFirmNameAfterCloseWorkSpace(String environment, String mode) {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfm = new AllFirmsPageBusinesslayer(driver);
		lp.investorLogin(SmokeContact1EmailId, SmokeContactUpdatedPassword);
		if(!allfm.selectFirmName(SmokeFirmName)) {
			appLog.info(SmokeFirmName+" firm name is not available in drop down in contact "+SmokeContact1FirstName+" "+SmokeContact1LastName);
		}else {
			appLog.error(SmokeFirmName+" firm name is available in drop down in contact "+SmokeContact1FirstName+" "+SmokeContact1LastName);
			sa.assertTrue(false, SmokeFirmName+" firm name is available in drop down in contact "+SmokeContact1FirstName+" "+SmokeContact1LastName);
		}
		lp.getInvestorLogout();
		driver.close();
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		lp=new LoginPageBusinessLayer(driver);
		allfm = new AllFirmsPageBusinesslayer(driver);
		lp.investorLogin(SmokeContact2EmailId, SmokeContactUpdatedPassword);
		if(!allfm.selectFirmName(SmokeFirmName)) {
			appLog.info(SmokeFirmName+" firm name is not available in drop down in contact "+SmokeContact2FirstName+" "+SmokeContact2LastName);
		}else {
			appLog.error(SmokeFirmName+" firm name is available in drop down in contact "+SmokeContact2FirstName+" "+SmokeContact2LastName);
			sa.assertTrue(false, SmokeFirmName+" firm name is available in drop down in contact "+SmokeContact2FirstName+" "+SmokeContact2LastName);
		}
		lp.getInvestorLogout();
		sa.assertAll();
	}
	
	
	@Parameters({ "environment", "mode" }) 
	@Test
	public void Smoketc040_clearWorkSpace(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(SmokeSuperAdminUserName,SmokePassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(SmokeFundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(click(driver, fp.getWorkSpaceClearBtn(Workspace.FundraisingWorkspace, 30), "workspace clear button", action.SCROLLANDBOOLEAN)) {
					String parentID=switchOnWindow(driver);
					String NewChildWindow=null;
					if(parentID!=null) {
						String childWindow= driver.getWindowHandle();
						ThreadSleep(2000);
						if(click(driver, fp.getBackUpLinkInClearWorkSpace(30), "backup link", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on back up link");
							ThreadSleep(3000);
							if(click(driver, fp.getBoxLinkInClearWorkSpace(30), "box link", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on box link");
								Set<String> windowIds=driver.getWindowHandles();
								Iterator<String> I2 = windowIds.iterator();
								while (I2.hasNext()) {
									String windowID = I2.next();
									if (windowID.equalsIgnoreCase(parentID) || windowID.equalsIgnoreCase(childWindow)) {
										appLog.info("Parent Id is Matched");
									} else {
										NewChildWindow = windowID;
										appLog.info("Storged child Window Id");
										break;
									}
								}
								if(NewChildWindow!=null) {
									driver.switchTo().window(NewChildWindow);
									ThreadSleep(5000);
									String aa =getURL(driver, 60);
									if(aa.contains("app.box.com")) {
										appLog.info("app.box.com is open ");
									}else {
										appLog.error("app.box.com is not open after click on box link");
										sa.assertTrue(false, "app.box.com is not open after click on box link");
									}
									driver.close();
									driver.switchTo().window(childWindow);
								}else {
									appLog.error("Box Link is not open after click on box link");
									sa.assertTrue(false, "Box Link is not open after click on box link");
								}
								try{
									WebElement ele= BaseLib.edriver.findElement(By.cssSelector("div#backup_savebtn>a[title=Yes]"));
									scrollDownThroughWebelement(driver, ele, "Yes Button");
									ele.click();
									appLog.info("clicked on Yes Button");
//									if (isAlertPresent(BaseLib.edriver)) {
										appLog.info(Workspace.FundraisingWorkspace.toString()+" has been successfully clear");
										String msg = switchToAlertAndGetMessage(BaseLib.edriver, 30, action.GETTEXT);
										switchToAlertAndAcceptOrDecline(BaseLib.edriver, 30, action.ACCEPT);
										if(msg.contains(FundsPageErrorMessage.clearWorkSpaceMsg)) {
											appLog.info(FundsPageErrorMessage.clearWorkSpaceMsg);
										}else {
											appLog.error("clear Workspace Error Message is not matched. Expected: "+FundsPageErrorMessage.closeWorkspaceMsg);
											sa.assertTrue(false, "clear Workspace Error Message is not matched. Expected: "+FundsPageErrorMessage.closeWorkspaceMsg);
										}
//									} else {
//										appLog.error(Workspace.FundraisingWorkspace.toString()+" clear Workspace alert message is not displayed");
//										sa.assertTrue(false, Workspace.FundraisingWorkspace.toString()+" clear Workspace alert message is not displayed");
//									}
								}catch(Exception e){
									appLog.error("Not able to click on Yes button so cannot clear fundraising workspace");
									BaseLib.sa.assertTrue(false, "Not able to click on Yes button so cannot clear fundraising workspace");
									appLog.error(e.getMessage().toString());
									driver.close();
									driver.switchTo().window(parentID);
								}
							}else {
								appLog.error("Not able to click on box link so cannot verify box link and close workspace");
								sa.assertTrue(false, "Not able to click on box link so cannot verify box link and close workspace");
							}
							
						}else {
							appLog.error("Not able to click on back up link so cannot verify box link and clear workspace");
							sa.assertTrue(false, "Not able to click on back up link so cannot verify box link and clear workspace");
						}
						driver.switchTo().window(parentID);
						
					}else {
						appLog.error("No new window is open so cannot clear workspace");
						sa.assertTrue(false, "No new window is open so cannot clear workspace");
					}
				}else {
					appLog.error("Not able to click on workspace clear button so cannot clear workspace");
					sa.assertTrue(false, "No new window is open so cannot clear workspace");
				}
				refresh(driver);
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 30)!=null) {
					appLog.info("build workspace fundraising button is displaying ");
				}else {
					appLog.error("build workspace fundraising button is not displaying ");
					sa.assertTrue(false, "build workspace fundraising button is not displaying ");
				}	
				if(click(driver, fp.getWorkSpaceClearBtn(Workspace.InvestorWorkspace, 30), "workspace clear button", action.SCROLLANDBOOLEAN)) {
					String parentID=switchOnWindow(driver);
					String NewChildWindow=null;
					if(parentID!=null) {
						String childWindow= driver.getWindowHandle();
						ThreadSleep(2000);
						if(click(driver, fp.getBackUpLinkInClearWorkSpace(30), "backup link", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on back up link");
							ThreadSleep(3000);
							if(click(driver, fp.getBoxLinkInClearWorkSpace(30), "box link", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on box link");
								Set<String> windowIds=driver.getWindowHandles();
								Iterator<String> I2 = windowIds.iterator();
								while (I2.hasNext()) {
									String windowID = I2.next();
									if (windowID.equalsIgnoreCase(parentID) || windowID.equalsIgnoreCase(childWindow)) {
										appLog.info("Parent Id is Matched");
									} else {
										NewChildWindow = windowID;
										appLog.info("Storged child Window Id");
										break;
									}
								}
								if(NewChildWindow!=null) {
									driver.switchTo().window(NewChildWindow);
									ThreadSleep(5000);
									String aa =getURL(driver, 60);
									if(aa.contains("app.box.com")) {
										appLog.info("app.box.com is open ");
									}else {
										appLog.error("app.box.com is not open after click on box link");
										sa.assertTrue(false, "app.box.com is not open after click on box link");
									}
									driver.close();
									driver.switchTo().window(childWindow);
								}else {
									appLog.error("Box Link is not open after click on box link");
									sa.assertTrue(false, "Box Link is not open after click on box link");
								}
								WebElement ele= BaseLib.edriver.findElement(By.cssSelector("div#backup_savebtn>a[title=Yes]"));
								try{
									scrollDownThroughWebelement(driver, ele, "Yes Button");
									ele.click();
									appLog.info("clicked on Yes Button");
									ThreadSleep(5000);
//									if (isAlertPresent(driver)) {
										appLog.info(Workspace.InvestorWorkspace.toString()+" has been successfully clear");
										String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
										switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
										if(msg.contains(FundsPageErrorMessage.clearWorkSpaceMsg)) {
											appLog.info(FundsPageErrorMessage.clearWorkSpaceMsg);
										}else {
											appLog.error("clear Workspace Error Message is not matched. Expected: "+FundsPageErrorMessage.closeWorkspaceMsg);
											sa.assertTrue(false, "clear Workspace Error Message is not matched. Expected: "+FundsPageErrorMessage.closeWorkspaceMsg);
										}
//									} else {
//										appLog.error(Workspace.InvestorWorkspace.toString()+" clear Workspace alert message is not displayed");
//										sa.assertTrue(false, Workspace.InvestorWorkspace.toString()+" clear Workspace alert message is not displayed");
//									}
								}catch(Exception e){
									appLog.error("Not able to click on Yes button so cannot clear investor workspace");
									BaseLib.sa.assertTrue(false, "Not able to click on Yes button so cannot clear investor workspace");
									appLog.error(e.getMessage().toString());
									driver.close();
									driver.switchTo().window(parentID);
								}
							}else {
								appLog.error("Not able to click on box link so cannot verify box link and close workspace");
								sa.assertTrue(false, "Not able to click on box link so cannot verify box link and close workspace");
							}
							
						}else {
							appLog.error("Not able to click on back up link so cannot verify box link and clear workspace");
							sa.assertTrue(false, "Not able to click on back up link so cannot verify box link and clear workspace");
						}
						driver.switchTo().window(parentID);
						
					}else {
						appLog.error("No new window is open so cannot clear workspace");
						sa.assertTrue(false, "No new window is open so cannot clear workspace");
					}
				}else {
					appLog.error("Not able to click on workspace clear button so cannot clear workspace");
					sa.assertTrue(false, "No new window is open so cannot clear workspace");
				}
				refresh(driver);
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(fp.getBuildWorkspaceButton(Workspace.InvestorWorkspace, 30)!=null) {
					appLog.info("build investor workspace  button is displaying ");
				}else {
					appLog.error("build investor workspace button is not displaying ");
					sa.assertTrue(false, "build investor workspace button is not displaying ");
				}
			}else {
				appLog.error("Not able to click on created fund: "+SmokeFundName1+" so cannot close fundraising and investor workspace");
				sa.assertTrue(false, "Not able to click on created fund: "+SmokeFundName1+" so cannot close fundraising and investor workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot close fundraising and investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot close fundraising and investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
}
