/**
 * 
 */
package com.navatar.scripts;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.navatar.generic.BaseLib;
import com.navatar.generic.SoftAssert;
import com.navatar.generic.CommonLib.EnableDisable;
import com.navatar.generic.CommonLib.FolderType;
import com.navatar.generic.CommonLib.Mode;
import com.navatar.generic.CommonLib.PageName;
import com.navatar.generic.CommonLib.SortOrder;
import com.navatar.generic.CommonLib.TabName;
import com.navatar.generic.CommonLib.accessType;
import com.navatar.generic.CommonLib.action;
import com.navatar.generic.CommonLib.customTabActionType;
import com.navatar.generic.CommonLib.excelLabel;
import com.navatar.generic.CommonLib.sideMenu;
import com.navatar.generic.CommonLib.userType;
import com.navatar.generic.CommonVariables;
import com.navatar.generic.EmailLib;
import com.navatar.generic.ExcelUtils;
import com.navatar.pageObjects.BasePageBusinessLayer;
import com.navatar.pageObjects.CommitmentPageBusinessLayer;
import com.navatar.pageObjects.ContactPageBusinessLayer;
import com.navatar.pageObjects.FundRaisingPageBusinessLayer;
import com.navatar.pageObjects.FundsPageBusinessLayer;
import com.navatar.pageObjects.InstitutionPageBusinessLayer;
import com.navatar.pageObjects.LoginPageBusinessLayer;
import com.navatar.pageObjects.NIMPage;
import com.navatar.pageObjects.NIMPageBusinessLayer;
import com.navatar.pageObjects.NIMPageErrorMessage;
import com.navatar.pageObjects.PartnershipPageBusinessLayer;

import static com.navatar.generic.AppListeners.appLog;
import static com.navatar.generic.CommonLib.*;
import static com.navatar.generic.CommonVariables.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Avinash Agrawal
 *
 */
public class Module11 extends BaseLib {
	
	public static String Org3CRMUser2UpdatedFirstName1=ExcelUtils.readData("Users",excelLabel.Variable_Name, "Org3User2", excelLabel.Updated_FirstName);
	public static String Org3CRMUser2UpdatedFirstName2=Org3CRMUser2UpdatedFirstName1+"Fname";
	public static String Org3CRMUser2UpdatedLastName1=ExcelUtils.readData("Users",excelLabel.Variable_Name, "Org3User2", excelLabel.Updated_LastName);
	public static String Org3CRMUser2UpdatedLastName2=Org3CRMUser2UpdatedLastName1+"Lname";
	
	@Test
	public void M11tc001_preCondition(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		String passwordResetLink = null;
		SoftAssert saa = new SoftAssert();
		List<String> lst=new ArrayList<String>();
		String[] splitedUserName = removeNumbersFromString(Org3CRMUser1LastName); 
		String UserLastName =splitedUserName[0]+bp.generateRandomNumber();
		ExcelUtils.writeData(UserLastName, "Users", excelLabel.Variable_Name, "Org3User2", excelLabel.User_Last_Name);
		cv = new CommonVariables(this);
		
		// Azhar add
		if (bp.clickOnTab(TabName.NIMTab)) {
			if (superAdminRegisteredOrg3.equalsIgnoreCase("No")) {
				if (np.NIMRegistration(userType.SuperAdmin, org3SuperAdminFirstName, org3SuperAdminLastName)) {
					appLog.info("ip access was given succesfully to admin " + superAdminOrg3UserName);

				} else {
					appLog.error("Registration process not successful : " + superAdminRegisteredOrg3);
					sa.assertTrue(false, "Registration process not successful : " + superAdminRegisteredOrg3);
				}
			} else {
				appLog.error("SuperAdmin Already Registerd : " + superAdminOrg3UserName);
			}
		}
		// Azhar end
		boolean flag=false;
		String parentWindow=null;
		switchToDefaultContent(driver);
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
					String emailID=cp.generateRandomEmailId();
					if (bp.createPEUser(environment, mode,Org3CRMUser2FirstName, UserLastName, emailID, CRMUserLicense,
							CRMUserProfile)) {
						appLog.info("PE User 1 created Successfully");
						if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
							ThreadSleep(5000);
						}
						ExcelUtils.writeData(emailID, "Users", excelLabel.Variable_Name, "Org3User2", excelLabel.User_Email);
						flag = true;
						break;
						
					}
					
				}
			} catch (Exception e) {
				appLog.error("could not find setup link, trying again..");
			}
			
		}
		if (flag) {
			if (bp.installedPackages(environment, mode,Org3CRMUser2FirstName, Org3CRMUser2LastName)) {
				appLog.info("Install package is done for PE User 2 succesfully");
			} else {
				appLog.info("Install package is not done for PE User 2 succesfully");
				saa.assertTrue(false, "Install package is not done for PE User 2 succesfully");
			}
		}
		else {
			appLog.error("could not create PE user");
			exit("could not create PE user");
		}
		if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			driver.close();
			driver.switchTo().window(parentWindow);
		}
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, np.getNIMTabFrame(60));
			if (np.clickOnEditIcon()) {
				WebElement ele = FindElement(driver,
						"//span[text()='" + Org3CRMUser2FirstName + " " + Org3CRMUser2LastName
								+ "']/../..//input[@type='checkbox']",
						"CRM User 2 Checkbox", action.SCROLLANDBOOLEAN, 60);
				if (click(driver, ele, "CRM User2 Checkbox", action.SCROLLANDBOOLEAN)) {
					ThreadSleep(2000);
					if (np.getConfirmUserPermissionAdditionpopupHeader(20) != null) {
						ThreadSleep(2000);
						if (isDisplayed(driver, np.getConfirmUserPermissionAdditionPopupYesButton(60), "Visibility", 60,
								"User Permission Removal Popup Yes button") != null) {
							appLog.info("Yes Button is displaying on confirm user permission removal popup");
							
							if(click(driver, np.getConfirmUserPermissionAdditionPopupYesButton(60), "Yes button", action.SCROLLANDBOOLEAN)){
								if (isSelected(driver, ele, "CRM User2 Access checkbox.")) {
									appLog.info("CRM User2 Access checkbox.");
									appLog.info("Successfully provide access to CRM User -"+Org3CRMUser2FirstName+","+Org3CRMUser2LastName);
								} else {
									appLog.info("CRM User radio button is not selected");
									saa.assertTrue(false, "CRM User access checkbox is not selected.");
								}
							}else{
								appLog.info("Yes Button is not clickable.");
								saa.assertTrue(false, "Yes Button is not clickable.");
							}
						} else {
							appLog.info("Yes Button is not displaying on confirm user permission removal popup");
							saa.assertTrue(false,
									"Yes Button is not displaying on confirm user permission removal popup");
						}
					} else {
						appLog.info("Confirm User permission Removal popup is not displaying");
						saa.assertTrue(false, "Confirm User permission Removal popup is not displaying");
					}
				} else {
					appLog.info("Not able to click on CRM User2 Checkbox");
					saa.assertTrue(false, "Not able to click on CRM User2 Checkbox");
				}
			} else {
				appLog.info("Not able to click on edit icon");
				saa.assertTrue(false, "Not able to click on edit icon");
			}
		} else {
			appLog.info("Not able to click on NIM Tab so cannot verify different popups");
			saa.assertTrue(false, "Not able to click on NIM Tab so cannot verify different popups");
		}
		//***********/
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		driver.close();
		config(browserToLaunch);
		bp = new BasePageBusinessLayer(driver);
		lp=new LoginPageBusinessLayer(driver);
		cp = new ContactPageBusinessLayer(driver);
		np = new NIMPageBusinessLayer(driver);
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
		
		
		 String addRemoveTabName="Navatar Investor Manager";
			if (lp.addTab_Lighting(mode,addRemoveTabName , 5)) {
				appLog.info("Tab added : "+addRemoveTabName);
//				log(LogStatus.INFO,"Tab added : "+addRemoveTabName,YesNo.No);
			} else {
				appLog.error("Tab not added : "+addRemoveTabName);
//				log(LogStatus.FAIL,"Tab not added : "+addRemoveTabName,YesNo.No);
				sa.assertTrue(false, "Tab not added : "+addRemoveTabName);
			}	
		/*********registering CRM User*********/
			if (bp.clickOnTab(TabName.NIMTab)){
				ThreadSleep(5000);
				switchToFrame(driver, 60, np.getNIMTabFrame(60));
					if (click(driver, np.getNextButton(60), "NextButton", action.SCROLLANDBOOLEAN)) {
						if (click(driver, np.getAllowButton(60), "Allow Button", action.SCROLLANDBOOLEAN)) {
							switchToFrame(driver, 60, np.getNIMTabFrame(60));
									if (np.getRegistrationSuccessfulCloseBtn(60) != null) {
										if (click(driver, np.getRegistrationSuccessfulCloseBtn(60), "Close button",
												action.SCROLLANDBOOLEAN)) {
											appLog.info("Successfully register CRM User -"+Org3CRMUser2FirstName+","+Org3CRMUser2LastName);
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
							appLog.info("not able to click on allow button");
							saa.assertTrue(false, "Not able to click on allow button");
						}
					} else {
						appLog.info("Not able to click on next button");
						saa.assertTrue(false, "Not able to click on close button");
					}
			} else {
				appLog.info("Not able to click on navatar investor manager tab");
				saa.assertTrue(false,
						"Not able to click on navatar investor manager tab so cannot verify error messages");
			}

		/**************************************/
		ThreadSleep(1000);
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		driver.close();
		config(browserToLaunch);
		bp = new BasePageBusinessLayer(driver);
		lp = new LoginPageBusinessLayer(driver);
		cp = new ContactPageBusinessLayer(driver);
		np = new NIMPageBusinessLayer(driver);
		ThreadSleep(5000);
		/******** for 3rd user******/
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		parentWindow=null;
		flag=false;
		
		lst.clear();
		 splitedUserName = removeNumbersFromString(Org3CRMUser3LastName); 
		 UserLastName =splitedUserName[0]+bp.generateRandomNumber();
		ExcelUtils.writeData(UserLastName, "Users", excelLabel.Variable_Name, "Org3User3", excelLabel.User_Last_Name);
		cv = new CommonVariables(this);
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
					String emailID=cp.generateRandomEmailId();
					if (bp.createPEUser(environment, mode,Org3CRMUser3FirstName, UserLastName, emailID, CRMUserLicense,
							CRMUserProfile)) {
						appLog.info("PE User 3 created Successfully");
						ExcelUtils.writeData(emailID, "Users", excelLabel.Variable_Name, "Org3User3", excelLabel.User_Email);
						flag = true;
						break;
					} else {
						appLog.info("PE User3 is not created successfully");
						saa.assertTrue(false, "PE User3 is not created successfully");
					}	
					
					if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
							ThreadSleep(5000);
						}
						
					
				}
			} catch (Exception e) {
				appLog.error("could not find setup link, trying again..");
			}
			
		}
		if (flag) {
			if (bp.installedPackages(environment, mode,Org3CRMUser3FirstName, Org3CRMUser3LastName)) {
				appLog.info("Install package is done for PE User 3 succesfully");
			} else {
				appLog.info("Install package is not done for PE User 3 succesfully");
				saa.assertTrue(false, "Install package is not done for PE User 3 succesfully");
			}
		}
		if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			driver.close();
			driver.switchTo().window(parentWindow);
		}
		
		switchToDefaultContent(driver);
		/**********provide NIM access to CRM User**********/
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, np.getNIMTabFrame(60));
			if (np.clickOnEditIcon()) {
				WebElement ele = FindElement(driver,
						"//span[text()='" + Org3CRMUser3FirstName + " " + Org3CRMUser3LastName
								+ "']/../..//input[@type='checkbox']",
						"CRM User 2 Checkbox", action.SCROLLANDBOOLEAN, 60);
				if (click(driver, ele, "CRM User3 Checkbox", action.SCROLLANDBOOLEAN)) {
					ThreadSleep(2000);
					if (np.getConfirmUserPermissionAdditionpopupHeader(60) != null) {
						ThreadSleep(2000);
						if (isDisplayed(driver, np.getConfirmUserPermissionAdditionPopupYesButton(60), "Visibility", 60,
								"User Permission Removal Popup Yes button") != null) {
							appLog.info("Yes Button is displaying on confirm user permission removal popup");
							
							if(click(driver, np.getConfirmUserPermissionAdditionPopupYesButton(60), "Yes button", action.SCROLLANDBOOLEAN)){
								if (isSelected(driver, ele, "CRM User3 Access checkbox.")) {
									appLog.info("CRM User3 Access checkbox.");
									appLog.info("Successfully provide access to CRM User -"+Org3CRMUser3FirstName+","+Org3CRMUser3LastName);
								} else {
									appLog.info("CRM User radio button is not selected");
									saa.assertTrue(false, "CRM User access checkbox is not selected.");
								}
							}else{
								appLog.info("Yes Button is not clickable.");
								saa.assertTrue(false, "Yes Button is not clickable.");
							}
						} else {
							appLog.info("Yes Button is not displaying on confirm user permission removal popup");
							saa.assertTrue(false,
									"Yes Button is not displaying on confirm user permission removal popup");
						}
					} else {
						appLog.info("Confirm User permission Removal popup is not displaying");
						saa.assertTrue(false, "Confirm User permission Removal popup is not displaying");
					}
				} else {
					appLog.info("Not able to click on CRM User2 Checkbox");
					saa.assertTrue(false, "Not able to click on CRM User2 Checkbox");
				}
			} else {
				appLog.info("Not able to click on edit icon");
				saa.assertTrue(false, "Not able to click on edit icon");
			}
		} else {
			appLog.info("Not able to click on NIM Tab so cannot verify different popups");
			saa.assertTrue(false, "Not able to click on NIM Tab so cannot verify different popups");
		}
		/***********/
		
		
		lp.CRMlogout(environment, mode);
		driver.close();
		config(browserToLaunch);
		passwordResetLink =null;
		bp = new BasePageBusinessLayer(driver);
		lp = new LoginPageBusinessLayer(driver);
		cp = new ContactPageBusinessLayer(driver);
		np = new NIMPageBusinessLayer(driver);
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
		addRemoveTabName="Navatar Investor Manager";
		if (lp.addTab_Lighting(mode,addRemoveTabName , 5)) {
			appLog.info("Tab added : "+addRemoveTabName);
//			log(LogStatus.INFO,"Tab added : "+addRemoveTabName,YesNo.No);
		} else {
			appLog.error("Tab not added : "+addRemoveTabName);
//			log(LogStatus.FAIL,"Tab not added : "+addRemoveTabName,YesNo.No);
			sa.assertTrue(false, "Tab not added : "+addRemoveTabName);
		}	
/*********registering CRM User*********/
			if (bp.clickOnTab(TabName.NIMTab)) {
				ThreadSleep(5000);
					switchToFrame(driver, 60, np.getNIMTabFrame(60));
					if (click(driver, np.getNextButton(60), "NextButton", action.SCROLLANDBOOLEAN)) {
						if (click(driver, np.getAllowButton(60), "Allow Button", action.SCROLLANDBOOLEAN)) {
							switchToFrame(driver, 60, np.getNIMTabFrame(60));
									if (np.getRegistrationSuccessfulCloseBtn(60) != null) {
										if (click(driver, np.getRegistrationSuccessfulCloseBtn(60), "Close button",
												action.SCROLLANDBOOLEAN)) {
											appLog.info("Successfully register CRM User -"+Org3CRMUser3FirstName+","+Org3CRMUser3LastName);
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
							appLog.info("not able to click on allow button");
							saa.assertTrue(false, "Not able to click on allow button");
						}
					} else {
						appLog.info("Not able to click on next button");
						saa.assertTrue(false, "Not able to click on close button");
					}
			} else {
				appLog.info("Not able to click on navatar investor manager tab");
				saa.assertTrue(false,
						"Not able to click on navatar investor manager tab so cannot verify error messages");
			}
		/**************************************/
			
		ThreadSleep(1000);
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		driver.close();
		config(browserToLaunch);
		bp = new BasePageBusinessLayer(driver);
		lp = new LoginPageBusinessLayer(driver);
		cp = new ContactPageBusinessLayer(driver);
		np = new NIMPageBusinessLayer(driver);
		NIMPage nim = new NIMPage(driver);
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
			if (np.clickOnSideMenusTab(sideMenu.FolderTemplates)) {
				if (np.deleteAllFolderTemplates()) {
					appLog.info("successfully deleted folder template");
				}
				else {
					appLog.error("could not delete folder templates");
					sa.assertTrue(false, "could not delete folder templates");
				}
			}
			//Updating Firm Name in Excel
			if (np.clickOnSideMenusTab(sideMenu.Profiles)){
				if (np.clickOnSideMenusTab(sideMenu.MyFirmProfile)) {
					String firm_name = np.getFirmName(60).getText().trim();
					ExcelUtils.writeData(firm_name, "Users", excelLabel.Variable_Name, "Org3SuperAdmin", excelLabel.Updated_FirmName);
					appLog.info("firm name "+firm_name+" is successfully written to excel");
				}
				else {
					sa.assertTrue(false, "my firm profile tab is not clickable");
				}
			}
			else {
				sa.assertTrue(false, "profile side menu is not cllickable on nim page");
			}
			//Providing Delegate Admin access to CRM User2.
			if (np.clickOnSideMenusTab(sideMenu.InternalUsers)){
				if (np.clickOnEditIcon()) {
					WebElement ele_check = isDisplayed(driver,
							FindElement(driver,
									"//span[@id='grid_Users-rows']/span/span[3]/span[text()='" + Org3CRMUser2FirstName+" "+Org3CRMUser2LastName
											+ "']/../..//input[@type='radio']",
									"Access checkbox of user" + Org3CRMUser2FirstName+" "+Org3CRMUser2LastName, action.SCROLLANDBOOLEAN, 30),
							"Visibility", 60, " Access checkbox for " + Org3CRMUser2FirstName+" "+Org3CRMUser2LastName);
					if (ele_check != null) {
						if (click(driver, ele_check, "Access for " + Org3CRMUser2FirstName+" "+Org3CRMUser2LastName, action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on  admin user radio button of user: " + Org3CRMUser2FirstName+" "+Org3CRMUser2LastName);
							if (click(driver, nim.getYesAdminButton(60), "Yes button for Admin access", action.BOOLEAN)) {
								appLog.info("successfully provided admin user access to user: " + Org3CRMUser2FirstName+" "+Org3CRMUser2LastName);
							}
						}
					} else {
						appLog.error("user radio button is not visible so cannot provide admin user access to user: "
								+ Org3CRMUser2FirstName+" "+Org3CRMUser2LastName);
					}
				}else{
					appLog.error("Can not click on Edit icon.");
					sa.assertTrue(false,"Can not click on Edit icon.");
				}
			}
			else {
				sa.assertTrue(false, "Internal Users menu is not cllickable on nim page");
			}	
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Test
	public void M11tc002_VerifyMyProfilePageUI() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		String myprofilepagelabels = "Name:<break>Phone:<break>Firm:<break>LinkedIn:<break>Title:<break>Facebook:<break>E-mail:<break>Address:<break>Username:";
		String[] myProfileValues = {Org3CRMUser2FirstName+" "+Org3CRMUser2LastName,"",Org3UpdatedFirmName,"","","",Org3CRMUser2EmailID,"",Org3CRMUser2EmailID };

		lp.CRMLogin(Org3CRMUser2EmailID, adminPassword);
		if (nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
			if (nim.clickOnSideMenusTab(sideMenu.Profiles)) {

				// Verifying Visibility of both "My profile" & "My Firm's
				// Profile link

				if(nim.getmyprofilelink(30)!=null){
					appLog.info("My Profile Link is available under Profile.");
					if(nim.getmyprofilelink(30).getText().trim().contains("My Profile")){
						appLog.info("My Profile link text is verified.");	
					}else{
						appLog.error("My Profile link text is not verified.");
						sa.assertTrue(false,"My Profile link text is not verified.");
					}
				}else{
					appLog.error("My Profile Link is not available under Profile.");
					sa.assertTrue(false,"My Profile Link is not available under Profile.");
				}

				if(nim.getmyfirmprofilelink(30)!=null){
					appLog.info("My firm Profile Link is available under Profile.");
					if(nim.getmyfirmprofilelink(30).getText().trim().contains("My Firm's Profile")){
						appLog.info("My firm Profile link text is verified.");	
					}else{
						appLog.error("My firm Profile link text is not verified.");
						sa.assertTrue(false,"My firm Profile link text is not verified.");
					}
				}else{
					appLog.error("My Profile Link is not available under Profile.");
					sa.assertTrue(false,"My Profile Link is not available under Profile.");
				}

				// Verify Profile header
				if (nim.getprofileheader(60)!= null) {
					appLog.info("Profile Header is available on My profile page.");
				}
				else {
					appLog.info("Profile Header is not available on My profile page.");
					sa.assertTrue(false, "Profile Header is not available on My profile page.");
				}

				// Verify Edit icon
				if (nim.getmyprofileediticon(60) != null) {
					appLog.info("Edit icon is available on My profile page.");
				} else {
					appLog.info("Edit icon is not available on My profile page.");
					sa.assertTrue(false, "Edit icon is not available on My profile page.");
				}

				// Verify My Profile header
				if (nim.getmyprofiletext(30).getText().trim().equals("My Profile")) {
					appLog.info("My Profile page is opened with default My Profile selected");
				} else {
					appLog.error("My Profile page is not default opened.");
					sa.assertTrue(false, "My Profile page is not default opened.");
				}

				// Verify Basic information label
				if (nim.getbasicinfotext(60).getText().trim().equals("Basic Information")) {
					appLog.info("Basic Information label is displayed.");
				} else {
					appLog.error("Basic Information label is not displayed.");
					sa.assertTrue(false, "Basic Information label is displayed.");
				}

				// Verify Login Information Label
				if (nim.getlogininfotext(60).getText().trim().equals("Login Information")) {
					appLog.info("Login Information label is displayed.");
				} else {
					appLog.error("Login Information label is not displayed.");
					sa.assertTrue(false, "Login Information label is displayed.");
				}

				// Verify All Labels at My Profile view page
				List<String> result_labels = compareMultipleList(driver, myprofilepagelabels, nim.myprofilelables());
				if (result_labels.isEmpty()) {
					appLog.info("all profile labels are verified");
				} else {
					for (int i = 0; i < result_labels.size(); i++) {
						appLog.error(result_labels.get(i));
						sa.assertTrue(false, result_labels.get(i));
					}
				}

				// Verify All Field values at My Profile View page
				List<WebElement> value = nim.getmyprofilelabelsvalues();
				if (!value.isEmpty()) {
					for(int i=0;i<myProfileValues.length;i++)
					{
						String res = value.get(i).getText().trim();
						if(myProfileValues[i].equalsIgnoreCase(res)){
							appLog.info("Expected Values:>>"+myProfileValues[i]+"is matched with"+res);
						}else{
							appLog.error("Expected Values:>>"+myProfileValues[i]+"is not matched with"+res);
							sa.assertTrue(false,"Expected Values:>>"+myProfileValues[i]+"is not matched with"+res);
						}
					}
				} else {
					appLog.error("Fields are not available on my profile page.");
					sa.assertTrue(false, "Fields are not available on my profile page.");
				}

			} else {
				appLog.error("Can not click on Profile Link");
				sa.assertTrue(false, "Can not click on Profile Link");
			}
			switchToDefaultContent(driver);
		} else {
			appLog.error("Can not click on NIM Tab");
			sa.assertTrue(false, "Can not click on NIM Tab");
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}

	@Test
	public void M11tc003_VerifyEditingMyProfile() {

		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		String myprofilepagelabelsoneditpage = "* First Name:<break>* Last Name:<break>Phone:<break>Firm:<break>LinkedIn:<break>Title:<break>Facebook:<break>E-mail:<break>Mailing Street:<break>Mailing City:<break>Mailing State/Province:<break>Mailing ZIP/Postal Code:<break>Mailing Country:<break>Username:";
		String[] myproilepageoneditvaluesinputboxes = { Org3CRMUser2FirstName, Org3CRMUser2LastName, "", "", "", "", "", "", "","" };

		lp.CRMLogin(Org3CRMUser2EmailID, adminPassword);
		if (nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
			if (nim.clickOnSideMenusTab(sideMenu.Profiles)) {

				// Verifying Editing My Profile Page
				if (click(driver, nim.getmyprofileediticon(60), "Edit icon", action.SCROLLANDBOOLEAN)) {
					if (isDisplayed(driver, nim.getMyProfileSaveButton(60), "visibility", 60,
							"Edit icon on Edit page") != null) {
						appLog.info("My Profile page is opened in Edit mode.");

						// Verify My Profile header
						if (nim.getmyprofiletext(30).getText().trim().equals("My Profile")) {
							appLog.info("My Profile page is opened with default My Profile selected");
						} else {
							appLog.error("My Profile page is not default opened.");
							sa.assertTrue(false, "My Profile page is not default opened.");
						}

						// Verify Basic information label
						if (nim.getbasicinfotext(60).getText().trim().equals("Basic Information")) {
							appLog.info("Basic Information label is displayed.");
						} else {
							appLog.error("Basic Information label is not displayed.");
							sa.assertTrue(false, "Basic Information label is displayed.");
						}

						// Verify Login Information Label
						if (nim.getlogininfotext(60).getText().trim().equals("Login Information")) {
							appLog.info("Login Information label is displayed.");
						} else {
							appLog.error("Login Information label is not displayed.");
							sa.assertTrue(false, "Login Information label is displayed.");
						}

						// Verify cancel button
						if (isDisplayed(driver, nim.getmyProfileCancelButton(60), "visibility", 60,
								"Cancel button") != null) {
							appLog.info("Cancel button is available in my profile Edit page. Hence passed.");
						} else {
							appLog.error("Cancel button is not available in my profile Edit page. Hence failed.");
							sa.assertFalse(true,
									"Cancel button is not available in my profile Edit page. Hence failed.");
						}

						// Verify All Labels on My profile Edit page
						List<String> result_labels_editpage = compareMultipleList(driver, myprofilepagelabelsoneditpage,
								nim.myprofilelables());
						if (result_labels_editpage.isEmpty()) {
							appLog.info("all my profile labels on edit my profile page are verified");
						} else {
							for (int i = 0; i < result_labels_editpage.size(); i++) {
								appLog.error(result_labels_editpage.get(i));
								sa.assertTrue(false, result_labels_editpage.get(i));
							}
						}
						
						// Verify Values in all input boxes
						List<WebElement> value = nim.myprofileinputboxes();
						for(int i=0;i<value.size();i++){
							System.out.println("Actual Values:>>"+value.get(i).getAttribute("value"));
						}

						if (!value.isEmpty()) {
							for(int i=0;i<myproilepageoneditvaluesinputboxes.length;i++)
							{
								String res = value.get(i).getAttribute("value").trim();
								if(myproilepageoneditvaluesinputboxes[i].equalsIgnoreCase(res)){
									appLog.info("Expected Values:>>"+myproilepageoneditvaluesinputboxes[i]+"is matched with"+res);
								}else{
									appLog.error("Expected Values:>>"+myproilepageoneditvaluesinputboxes[i]+"is not matched with"+res);
									sa.assertTrue(false,"Expected Values:>>"+myproilepageoneditvaluesinputboxes[i]+"is not matched with"+res);
								}
							}
						} else {
							appLog.error("Fields are not available on my profile page.");
							sa.assertTrue(false, "Fields are not available on my profile page.");
						}

						if (isDisplayed(driver, nim.getMyProfileMailingStreet(60), "visibility", 60, "Mailing Street")
								.getAttribute("value").equals("")) {
							appLog.info("Mailing street field is available as Blank. Hence passed.");
						} else {
							appLog.error("Mailing street field is not available as Blank. Hence failed.");
							sa.assertFalse(true, "Mailing street field is not available as Blank. Hence failed.");
						}

						if (isDisplayed(driver, nim.getfirmnameeditmodevalue(60), "visibility", 60, "Firm name")
								.getText().trim().equalsIgnoreCase(Org3UpdatedFirmName)) {
							appLog.info("Firm Name is verified.");
						} else {
							appLog.error("Firm Name is not verified.");
							sa.assertFalse(true, "Firm name is not available.");
						}

						if (isDisplayed(driver, nim.getemaileditmodevalue(60), "visibility", 60, "Email Link").getText()
								.trim().equalsIgnoreCase(Org3CRMUser2EmailID)) {
							appLog.info("E-mail: value is verified.");
						} else {
							appLog.error("E-mail: value is not verified.");
							sa.assertFalse(true, "E-mail: value is not verified.");
						}

						if (isDisplayed(driver, nim.getmyprofilelabelsvalues().get(13), "visibility", 60,
								"Username value").getText().trim().equalsIgnoreCase(Org3CRMUser2EmailID)) {
							appLog.info("Username: value is verified.");
						} else {
							appLog.error("Username: value is not verified.");
							sa.assertFalse(true, "Username: value is not verified.");
						}

						// Verify my profile info icon and hover text.
						if (mouseOverOperation(driver, nim.getmyprofileinfoicon(10))) {
							String actual_message = getText(driver, nim.getmyprofileinfoicontext(10), "",
									action.BOOLEAN).trim();
							if (actual_message.equalsIgnoreCase(NIMPageErrorMessage.myProfileInfoIconMessage)) {
								appLog.info("tool tip is verified at my profile edit page.");
							} else {
								appLog.error("tool tip is not verified at my profile edit page. Expected: "
										+ NIMPageErrorMessage.myProfileInfoIconMessage + "/t Actual: "
										+ actual_message);
								sa.assertTrue(false,
										"tool tip is not verified at my profile edit page. Expected: "
												+ NIMPageErrorMessage.myProfileInfoIconMessage + "/t Actual: "
												+ actual_message);
							}
						} else {
							appLog.error("Not able to mouse hover to info icon at my profile page.");
							sa.assertTrue(false, "Not able to mouse hover to info icon at my profile page.");
						}
					} else {
						appLog.error("My Proile page is not opened in edit mode.");
						sa.assertTrue(false, "My Proile page is not opened in edit mode.");
					}

				} else {
					appLog.error("My Proile edit icon is not displayed.");
					sa.assertTrue(false, "My profile edit icon is not displayed.");
				}

			} else {
				appLog.error("Can not click on Profile Link");
				sa.assertTrue(false, "Can not click on Profile Link");
			}
			switchToDefaultContent(driver);			
		} else {
			appLog.error("Can not click on NIM Tab");
			sa.assertTrue(false, "Can not click on NIM Tab");
		}

		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}

	@Test
	public void M11tc004_verifyMandatoryMessageandCancelbutton()
	{
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();

		lp.CRMLogin(Org3CRMUser2EmailID, adminPassword);
		if (nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
			if (nim.clickOnSideMenusTab(sideMenu.Profiles)) {
				if (click(driver, nim.getmyprofileediticon(60), "Edit icon", action.SCROLLANDBOOLEAN)){
					sendKeys(driver, nim.myprofileinputboxes().get(0), M11UpFname, "First Name", action.SCROLLANDBOOLEAN);
					sendKeys(driver, nim.myprofileinputboxes().get(1), M11UpLname, "Last Name", action.SCROLLANDBOOLEAN);
					sendKeys(driver, nim.myprofileinputboxes().get(2), M11UpPhone, "Phone", action.SCROLLANDBOOLEAN);
					if(click(driver, nim.getmyProfileCancelButton(30), "Cancel button", action.SCROLLANDBOOLEAN)){
						ThreadSleep(3000);
						if(nim.getmyProfileCancelButton(30)==null){
							appLog.info("My Profile View page is available.");

							if(nim.getmyprofilelabelsvalues().get(0).getText().contains(M11UpFname)||(nim.getmyprofilelabelsvalues().get(1).getText().equalsIgnoreCase(M11UpPhone))){
								appLog.error("After Clicking Cancel button, Name and Phone values are saved.");
								appLog.error("Expected Value:"+Org3CRMUser2FirstName+" "+Org3CRMUser2LastName+"Actual Value:"+nim.getmyprofilelabelsvalues().get(0).getText());
								appLog.error("Expected Value:"+" "+"Actual Value:"+nim.getmyprofilelabelsvalues().get(1).getText());
								sa.assertTrue(false, "After Clicking Cancel button, Name OR Phone values are saved.");
							} else{
								appLog.info("After Clicking Cancel button, Name and Phone values are not saved.");
							}
						} else{
							appLog.error("Cancel button is not working properly. So cann't verify.");
							sa.assertTrue(false,"Cancel button is not working properly. So cann't verify.");}
					} else{
						appLog.error("Can not click on cancel button.");
						sa.assertTrue(false, "Can not click on cancel button.");}
				} else{
					appLog.error("Can not click on my profile Edit icon.");
					sa.assertTrue(false, "Can not click on my profile Edit icon.");}

				if(click(driver, nim.getmyprofileediticon(60), "Edit icon", action.SCROLLANDBOOLEAN)) {
					sendKeys(driver, nim.myprofileinputboxes().get(0), " ", "First Name", action.SCROLLANDBOOLEAN);
					sendKeys(driver, nim.myprofileinputboxes().get(1), " ", "Last Name", action.SCROLLANDBOOLEAN);
					if(click(driver, nim.getMyProfileSaveButton(30), "my Profile save button", action.SCROLLANDBOOLEAN)){

						//Verify Mandatory Error message for First Name and Last Name.
						for(int i=0;i<nim.getmyprofileerrorfield().size();i++)
						{
							if(nim.getmyprofileerrorfield().get(i)!=null){
								if(nim.getmyprofileerrorfield().get(i).getText().equalsIgnoreCase(NIMPageErrorMessage.myProfileNameErrorMessage)){
									appLog.info("Error Message is Verified Successfully.");
									appLog.info("Expected:"+NIMPageErrorMessage.myProfileNameErrorMessage+"Actual :"+nim.getmyprofileerrorfield().get(i).getText());

								} else{
									appLog.error("Error Message is not Verified Successfully.");
									appLog.error("Expected:"+NIMPageErrorMessage.myProfileNameErrorMessage+"Actual :"+nim.getmyprofileerrorfield().get(i).getText());
									sa.assertTrue(false, "Error Message is not Verified Successfully.");}

							} else{
								appLog.error("Error Message is not Displayed.");
								sa.assertTrue(false, "Error Message is not Displayed.");}
						}
					}else{
						appLog.error("Can not click on my profile Save button.");
						sa.assertTrue(false, "Can not click on my profile Save button.");}

				} else{
					appLog.error("Can not click on my profile Edit icon.");
					sa.assertTrue(false, "Can not click on my profile Edit icon.");}
			}
			else {
				appLog.error("Can not click on Profile Link");
				sa.assertTrue(false, "Can not click on Profile Link");}
			switchToDefaultContent(driver);				
		} else {
			appLog.error("Can not click on NIM Tab");
			sa.assertTrue(false, "Can not click on NIM Tab");}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}

	@Test
	public void M11tc005_VerifySavingOfMyProfileWithValidData()
	{	
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		String myProfileValues = M11UpFname+" "+M11UpLname+"<break>"+M11UpPhone+"<break>"+Org3UpdatedFirmName+"<break>"+"http://"+M11Uplinkedin+"<break>"+M11UpTitle+"<break>"+"http://"+M11Upfacebook+"<break>"+Org3CRMUser2EmailID;

		lp.CRMLogin(Org3CRMUser2EmailID, adminPassword);
		if (nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
			if (nim.clickOnSideMenusTab(sideMenu.Profiles)) {
				if (click(driver, nim.getmyprofileediticon(60), "Edit icon", action.SCROLLANDBOOLEAN)){

					if(sendKeys(driver, nim.getMyProfileFirstName(30), M11UpFname, "FirstName", action.SCROLLANDBOOLEAN)){
						if(sendKeys(driver, nim.getMyProfileLastName(30), M11UpLname, "LastName", action.SCROLLANDBOOLEAN)){
							if(sendKeys(driver, nim.getMyProfilePhone(30), M11UpPhone, "Phone", action.SCROLLANDBOOLEAN)){
								if(sendKeys(driver, nim.getMyProfileLinkeDinTextBox(30), M11Uplinkedin, "LinkedIn", action.SCROLLANDBOOLEAN)){
									if(sendKeys(driver, nim.getMyProfileFaceBookTextBox(30), M11Upfacebook, "Facebook", action.SCROLLANDBOOLEAN)){
										if(sendKeys(driver, nim.getMyProfileTitle(30), M11UpTitle, "Title", action.SCROLLANDBOOLEAN)){
											if(sendKeys(driver, nim.getMyProfileMailingStreet(30), M11UpMailingStreet, "Mailing Street", action.SCROLLANDBOOLEAN)){
												if(sendKeys(driver, nim.getMyprofileMailingcity(30), M11UpMailingCity, "Mailing City", action.SCROLLANDBOOLEAN)){
													if(sendKeys(driver, nim.getMyProfilemailingState(30), M11UpMailingState, "Mailing State/Province", action.SCROLLANDBOOLEAN)){
														if(sendKeys(driver, nim.getMyProfilemailingZip(30), M11UpMailingZip, "Mailing ZIP/Postal Code", action.SCROLLANDBOOLEAN)){
															if(sendKeys(driver, nim.getMyProfileMailingCountry(30), M11UpMailingCounty, "Mailing country", action.SCROLLANDBOOLEAN)){

																if(click(driver, nim.getMyProfileSaveButton(30), "Save Button", action.SCROLLANDBOOLEAN)){
																	ThreadSleep(3000);
																	//Verify Values After Saving My Profile (Except Address)
																	if(nim.getmyProfileCancelButton(60)==null){
																		List<String> result_values = compareMultipleList(driver, myProfileValues, nim.getbasicinfofieldvalues());
																		if (result_values.isEmpty()) {
																			appLog.info("all my profile values on my profile view page are verified successfully.");
																		} else {
																			for (int i = 0; i < result_values.size(); i++) {
																				appLog.error("Following values are not matched"+result_values.get(i));
																				sa.assertTrue(false, "Following values are not matched"+result_values.get(i));
																			}
																		}

																		//Verify Address	
																		if(nim.getbasicinfoaddress(30).getText().contains(M11UpMailingStreet)){
																			if(nim.getbasicinfoaddress(30).getText().contains(M11UpMailingCity)){
																				if(nim.getbasicinfoaddress(30).getText().contains(M11UpMailingState)){
																					if(nim.getbasicinfoaddress(30).getText().contains(M11UpMailingZip)){
																						if(nim.getbasicinfoaddress(30).getText().contains(M11UpMailingCounty)){
																							appLog.info("Address is verified after saving my profile page.");

																						}else{
																							appLog.error("Address is not verified after saving my profile page. Expected:"+M11UpMailingCounty+"Actual :"+nim.getbasicinfoaddress(30).getText());
																							sa.assertTrue(false, "Address is not verified after saving my profile page. Expected:"+M11UpMailingCounty+"Actual :"+nim.getbasicinfoaddress(30).getText());}
																					}else{
																						appLog.error("Address is not verified after saving my profile page. Expected:"+M11UpMailingZip+"Actual :"+nim.getbasicinfoaddress(30).getText());
																						sa.assertTrue(false, "Address is not verified after saving my profile page. Expected:"+M11UpMailingZip+"Actual :"+nim.getbasicinfoaddress(30).getText());}
																				}else{
																					appLog.error("Address is not verified after saving my profile page. Expected:"+M11UpMailingState+"Actual :"+nim.getbasicinfoaddress(30).getText());
																					sa.assertTrue(false, "Address is not verified after saving my profile page. Expected:"+M11UpMailingState+"Actual :"+nim.getbasicinfoaddress(30).getText());}
																			}else{
																				appLog.error("Address is not verified after saving my profile page. Expected:"+M11UpMailingCity+"Actual :"+nim.getbasicinfoaddress(30).getText());
																				sa.assertTrue(false, "Address is not verified after saving my profile page. Expected:"+M11UpMailingCity+"Actual :"+nim.getbasicinfoaddress(30).getText());}
																		}else{
																			appLog.error("Address is not verified after saving my profile page. Expected:"+M11UpMailingStreet+"Actual :"+nim.getbasicinfoaddress(30).getText());
																			sa.assertTrue(false, "Address is not verified after saving my profile page. Expected:"+M11UpMailingStreet+"Actual :"+nim.getbasicinfoaddress(30).getText());}

																		//Verify LinkedIn link.
																		if(click(driver, nim.getbasicinfofieldvalues().get(3), "Linkedinlink", action.SCROLLANDBOOLEAN)){
																			String parentId=switchOnWindow(driver);
																			if(parentId!=null) {
																				appLog.info("Successfully Clicked on LinkedIn link.");
																				if(getTitle(driver).contains("LinkedIn: Log In or Sign Up")){
																					appLog.info("Linkedin Link is opened successfully.");

																				}else{
																					appLog.error("On Clicking Linkedin link user in not directed to right URL.");
																					sa.assertTrue(false, "On Clicking Linkedin link user in not directed to right URL.");
																				}
																				driver.close();
																				driver.switchTo().window(parentId);
																				switchToFrame(driver, 30,lp.getNIMTabParentFrame_Lightning(PageName.NavatarInvestorManager));
																				switchToFrame(driver, 30, lp.getFrame(PageName.NavatarInvestorManager, 30));
																			} else {
																				appLog.error("LinkedIn Link is not opened in New tab.");
																				sa.assertTrue(false, "LinkedIn Link is not opened in New tab.");
																			}	
																		}else{
																			appLog.error("Not able to Click on Linkedin link.");
																			sa.assertTrue(false, "Not able to Click on Linkedin link.");}

																		//verify Facebook link
																		if(click(driver, nim.getbasicinfofieldvalues().get(5), "Facebooklink", action.SCROLLANDBOOLEAN)){
																			String parentId=switchOnWindow(driver);
																			if(parentId!=null) {
																				appLog.info("Successfully Clicked on Facebook link.");
																				ThreadSleep(5000);
																				String tempHandle = driver.getWindowHandle();
																				if(!tempHandle.equals(parentId)){
																					appLog.info("Facebook Link is opened successfully.");
																					
																				}else{
																					appLog.error("On Clicking Facebook link user in not directed to right URL.");
																					sa.assertTrue(false, "On Clicking Facebook link user in not directed to right URL.");
																					} 
																				driver.close();
																				driver.switchTo().window(parentId);
																			} else {
																				appLog.error("Facebook Link is not opened in New tab.");
																				sa.assertTrue(false, "Facebook Link is not opened in New tab.");
																			}	
																		}else{
																			appLog.error("Not able to Click on Facebook link.");
																			sa.assertTrue(false, "Not able to Click on Facebook link.");}
																	}else{
																		appLog.error("After saving my profile page my profile view page is not available.");
																		sa.assertTrue(false, "After saving my profile page my profile view page is not available.");}	
																}else{
																	appLog.error("Can not click on my profile Save button.");
																	sa.assertTrue(false, "Can not click on my profile Save button.");}
															}else{
																appLog.error("Can not enter values in Mailing Country field.");
																sa.assertTrue(false, "Can not enter values in Mailing Country field.");}
														}else{
															appLog.error("Can not enter values in Mailing ZIP/Postal Code field.");
															sa.assertTrue(false, "Can not enter values in Mailing ZIP/Postal Code field.");}
													}else{
														appLog.error("Can not enter values in Mailing State/Province field.");
														sa.assertTrue(false, "Can not enter values in Mailing State/Province field.");}
												}else{
													appLog.error("Can not enter values in Mailing City field.");
													sa.assertTrue(false, "Can not enter values in Mailing City field.");}
											}else{
												appLog.error("Can not enter values in Mailing Street field.");
												sa.assertTrue(false, "Can not enter values in Mailing Street field.");}
										}else{
											appLog.error("Can not enter values in Title field.");
											sa.assertTrue(false, "Can not enter values in Title field.");}
									}else{
										appLog.error("Can not enter values in facebook field.");
										sa.assertTrue(false, "Can not enter values in facebook field.");}
								} else{
									appLog.error("Can not enter values in LinkedIn field.");
									sa.assertTrue(false, "Can not enter values in LinkedIn field.");}
							} else{
								appLog.error("Can not enter values in Phone field.");
								sa.assertTrue(false, "Can not enter values in Phone field.");}
						}else{
							appLog.error("Can not enter values in Last name field.");
							sa.assertTrue(false, "Can not enter values in Last name field.");}
					} else{
						appLog.error("Can not enter values in First name field.");
						sa.assertTrue(false, "Can not enter values in First name field.");}

				} else{
					appLog.error("Can not click on my profile Edit icon.");
					sa.assertTrue(false, "Can not click on my profile Edit icon.");}
			} else {
				appLog.error("Can not click on Profile Link");
				sa.assertTrue(false, "Can not click on Profile Link");}
			
		} else {
			appLog.error("Can not click on NIM Tab");
			sa.assertTrue(false, "Can not click on NIM Tab");}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}

	@Test
	public void M11tc006_verifyUIOfMyFirmsProfilepage(){
		
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);

		SoftAssert sa = new SoftAssert();
		String myfirmprofileexpectedlabels = "Header Background Color:"+"<break>"+"Firm Name:"+"<break>"+"Public Login Link"+"<break>"+":"+"<break>"+"Firm Contact:"+"<break>"+"Phone:"+"<break>"+"E-mail:"+"<break>"+"Website:"+"<break>"+"Address:"+"<break>"+"AUM (in mn):"+"<break>"+"Types:"+"<break>"+"Industries:"+"<break>"+"Geo Focus:"+"<break>"+"Description:";
		String date=getDateAccToTimeZone("America/New_York", "YYYY");
		String[] lastupdatedonexpectedvalue = {"Last","Updated","on",date,"by",org3SuperAdminFirstName,org3SuperAdminLastName};
		
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if (nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
			if (nim.clickOnSideMenusTab(sideMenu.MyFirmProfile)) {

				// Verify Profile header
				appLog.info("profile header is:"+nim.getprofileheader(20).getText());
				if(nim.getprofileheader(20)!= null) {
					appLog.info("Profile Header is available at My firm profile page.");
				} else {
					appLog.error("Profile Header is not available at My firm profile page.");
					sa.assertTrue(false, "Profile Header is not available at My firm profile page.");}

				// Verify Edit icon
				if(nim.getmyprofileediticon(60)!= null) {
					appLog.info("Edit icon is available at My firm profile page.");
				} else {
					appLog.info("Edit icon is not available at My firm profile page.");
					sa.assertTrue(false, "Edit icon is not available at My firm profile page.");}

				// Verify My firm Profile header
				if(nim.getmyprofiletext(30).getText().trim().equals("Firm Profile")) {
					appLog.info("'Firm Profile' header text is verified successfully.");
				} else {
					appLog.error("'Firm Profile' header text is not verified successfully.");
					sa.assertTrue(false, "'Firm Profile' header text is not verified successfully.");}

				// Verify My Firm's Profile All Labels
				List<String> result_values = compareMultipleList(driver, myfirmprofileexpectedlabels, nim.getmyfirmprofilelabels());
				if(result_values.isEmpty()) {
					appLog.info("all my firm profile labels on my firm profile view page are verified successfully.");
				} else {
					for (int i = 0; i < result_values.size(); i++) {
						appLog.error("Following values are not matched"+result_values.get(i));
						sa.assertTrue(false, "Following values are not matched"+result_values.get(i));
					}
				}

				// Verify Last Update On Text and value
				if(nim.getlastupdatedontext(30)!=null){
					String lastupdatedonactualvalue = nim.getlastupdatedontext(30).getText();
					if(lastupdatedonactualvalue!=null){
						String[] value = lastupdatedonactualvalue.split(" ");
						for(int i = 0; i<value.length;i++)
						{
							appLog.info("going to Compare:>>"+" "+lastupdatedonexpectedvalue[i]+"with:>>"+" "+value[i]);
							if(value[i].contains(lastupdatedonexpectedvalue[i])){
								appLog.info("Last Updated On Text with values is verified successfully. Expected:"+lastupdatedonexpectedvalue[i]+"Actual:"+value[i]);
							}else{
								appLog.info("Last Updated On Text with values is not verified successfully. Expected:"+lastupdatedonexpectedvalue[i]+"Actual:"+value[i]);
								sa.assertTrue(false,"Last Updated On Text with values is not verified successfully. Expected:"+lastupdatedonexpectedvalue[i]+"Actual:"+value[i]);}
						}
					}else{
						appLog.error("Last Updated on Text is not Displayed.");
						sa.assertTrue(false,"Last Updated on Text is not Displayed.");}
				}else{
					appLog.error("Last Updated on Text is not Displayed.");
					sa.assertTrue(false,"Last Updated on Text is not Displayed.");}


				//Verify No Logo Selected
				if(nim.getlogoimage(30)!=null){
					if(nim.getlogoimage(30).getAttribute("Alt").equalsIgnoreCase("No Logo Selected")){
						appLog.info("'No Logo Selected' is verified Successfully.");
					}else{
						appLog.error("'No Logo Selected' is not verified.");
						sa.assertTrue(false, "'No Logo Selected' is not verified.");}
				} else {
					appLog.error("No Logo image is not available.");
					sa.assertTrue(false, "No Logo image is not available.");}

				//Verify Change Logo Link
				if(nim.getChangeLogoLink(30)!=null){
					if(nim.getChangeLogoLink(30).getText().trim().equalsIgnoreCase("Change Logo")){
						appLog.info("Change Logo link text is Verified.");
					}else{
						appLog.error("Change Logo link text is not Verified.");
						sa.assertTrue(false, "Change Logo link text is not Verified.");}
				}else{
					appLog.error("Change Logo link is not available to click.");
					sa.assertTrue(false, "Change Logo link is not available to click.");}

				//Verify Address default value
				if(nim.getaddressvalue(30).getText().trim().isEmpty()){
					appLog.info("Address Default value is verified. Expected: "+ " "+"Actual:"+nim.getaddressvalue(30).getText());
				}else{
					appLog.error("Address Default value is not verified. Expected: "+ " "+"Actual:"+nim.getaddressvalue(30).getText());
					sa.assertTrue(false,"Address Default value is not verified. Expected: "+ " "+"Actual:"+nim.getaddressvalue(30).getText());}

				//Verify Description default value
				if(nim.getdescriptionvalue(60).getText().trim().isEmpty()){
					appLog.info("Description Default value is verified. Expected: "+ " "+"Actual:"+nim.getdescriptionvalue(30).getText());
				}else{
					appLog.error("Description Default value is not verified. Expected: "+ " "+"Actual:"+nim.getdescriptionvalue(30).getText());
					sa.assertTrue(false,"Description Default value is not verified. Expected: "+ " "+"Actual:"+nim.getdescriptionvalue(30).getText());}

				//Verify Default values at My Firm profile view page
				String[] myfirmprofileexpectedvalues = {Org3UpdatedFirmName,InvestorURL,M11UpFname+" "+M11UpLname,"",Org3CRMUser2EmailID,"","","$ 0.00 mn USD","","","",""};
				List<WebElement> myfirmprofileactualvalues = nim.getmyfirmprofilelabelsvalues();
				List<String> values = new ArrayList<String>();
				for(int i=1;i<13;i++)
				{	
					if(myfirmprofileactualvalues.get(i)!=null)
					{
						values.add(myfirmprofileactualvalues.get(i).getText().trim());}
					else{
						appLog.error("Element: "+myfirmprofileactualvalues.get(i)+"not found to add in list.");
						sa.assertTrue(false,"Element: "+myfirmprofileactualvalues.get(i)+"not found to add in list.");
					}
				}
				
				for(int i=0;i<values.size();i++){
					
					if(i==1){
						if(values.get(i).contains("https://")){
							appLog.info("Public Login Link is verified as a link.");
						}else{
							appLog.error("Public Login Link is not verified as a link.");
							sa.assertTrue(false, "Public Login Link is not verified as a link.");}
					}else if(i==2){
						if(values.get(i)!=null){
							appLog.info("Firm Contact: is not coming blank. Hence verified the Firm Contact.");
						}else{
							appLog.error("Firm Contact: is coming blank. Hence not verified the Firm Contact.");
							sa.assertTrue(false, "Firm Contact: is coming blank. Hence not verified the Firm Contact.");}
					}else if(i==4){
						if(values.get(i)!=null){
							appLog.info("E-mail value at My firm profile page is not null. Hence passed.");
						} else{
							appLog.error("E-mail value at My firm profile page is coming as blank.");
							sa.assertTrue(false, "E-mail value at My firm profile page is coming as blank.");}
					}else{
						if(myfirmprofileexpectedvalues[i].equalsIgnoreCase(values.get(i))){
							appLog.info("Following value at My firm profile value is matched. Expected Value :>>"+myfirmprofileexpectedvalues[i]+"Actual Vaule:"+values.get(i));

						}else{
							appLog.error("Following value at My firm profile page is not matched. Expected Value :>>"+myfirmprofileexpectedvalues[i]+"Actual Vaule:"+values.get(i));
							sa.assertTrue(false, "Following value at My firm profile page is not matched. Expected Value :>>"+myfirmprofileexpectedvalues[i]+"Actual Vaule:"+values.get(i));}
					}
				}
				
				// Verify my firm profile info icon and hover text.
				if (mouseOverOperation(driver, nim.getmyfirmprofileinfoicon(10))) {
					String actual_message = getText(driver, nim.getmyfirmprofileinfoicontext(10), "",
							action.BOOLEAN).trim();
					if (actual_message.equalsIgnoreCase(NIMPageErrorMessage.myFirmProfileInfoIconMessage)) {
						appLog.info("tool tip is verified at my firm profile view page.");
					} else {
						appLog.error("tool tip is not verified at my firm profile view page. Expected: "
								+ NIMPageErrorMessage.myFirmProfileInfoIconMessage + "/t Actual: "
								+ actual_message);
						sa.assertTrue(false,
								"tool tip is not verified at my firm profile view page. Expected: "
										+ NIMPageErrorMessage.myFirmProfileInfoIconMessage + "/t Actual: "
										+ actual_message);
					}
				} else {
					appLog.error("Not able to mouse hover to info icon at my profile page.");
					sa.assertTrue(false, "Not able to mouse hover to info icon at my profile page.");
				}

			} else {
				appLog.error("Can not click on MyFirmProfile Link");
				sa.assertTrue(false, "Can not click on MyFirmProfile Link");}
			switchToDefaultContent(driver);
		} else {
			appLog.error("Can not click on NIM Tab");
			sa.assertTrue(false, "Can not click on NIM Tab");}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}
	
	@Test
	public void M11tc007_verifyeditingmyfirmprofilepage() {

		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		String myfirmprofileeditpageexpectedlabels = "Header Background Color:" + "<break>" + "*Firm Name:" + "<break>"
				+ "Public Login Link :" + "<break>" + "<break>" + "Firm Contact:" + "<break>" + "Phone:" + "<break>"
				+ "E-mail:" + "<break>" + "Website:" + "<break>" + "Mailing Street:" + "<break>" + "Mailing City:"
				+ "<break>" + "Mailing State/Province:" + "<break>" + "Mailing ZIP/Postal Code:" + "<break>"
				+ "Mailing Country:" + "<break>" + "AUM (in mn):" + "<break>" + "Types:" + "<break>" + "Industries:"
				+ "<break>" + "Geo Focus:" + "<break>" + "Description:";
		String fundtypesoptions = "Balanced" + "<break>" + "Bridge Fund" + "<break>" + "Buyout" + "<break>"
				+ "Co-investment" + "<break>" + "Early Stage" + "<break>" + "Early Stage: Seed" + "<break>"
				+ "Early Stage: Start-up" + "<break>" + "Expansion/Late Stage" + "<break>" + "Fund of Funds"
				+ "<break>" + "Growth" + "<break>" + "Hotel" + "<break>" + "Land" + "<break>" + "LBO" + "<break>"
				+ "Mezzanine" + "<break>" + "Multi-Family" + "<break>" + "Natural Resources" + "<break>" + "Office"
				+ "<break>" + "Public/Private" + "<break>" + "Retail" + "<break>" + "Secondaries" + "<break>"
				+ "Special Situations" + "<break>" + "Turnaround" + "<break>" + "Venture (General)" + "<break>";
		String industriesoptions = "Agriculture, Forestry, and Fishing" + "<break>" + "Mining" + "<break>"
				+ "Construction" + "<break>" + "Manufacturing" + "<break>" + "Wholesale Trade" + "<break>"
				+ "Retail Trade" + "<break>" + "Transportation, Communications" + "<break>"
				+ "Electric, Gas, and Sanitary Services" + "<break>" + "Finance, Insurance, and Real Estate" + "<break>"
				+ "Services" + "<break>" + "Public Administration" + "<break>" + "Non classifiable Establishments";
		String geofocusoptions = "North America" + "<break>" + "South America" + "<break>" + "Central America"
				+ "<break>" + "Caribbean" + "<break>" + "Africa" + "<break>" + "Middle East" + "<break>" + "Europe"
				+ "<break>" + "Asia Pacific";

		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if (nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
			if (nim.clickOnSideMenusTab(sideMenu.MyFirmProfile)) {
				if (click(driver, nim.getmyprofileediticon(30), "Edit icon", action.SCROLLANDBOOLEAN)) {
					if (nim.getmyprofileediticon(20) == null) {
						
						// Verify Profile header
						if (nim.getprofileheader(60)!= null) {
							appLog.info("Profile Header is available at My firm profile page.");
						} else {
							appLog.info("Profile Header is not available at My firm profile page.");
							sa.assertTrue(false, "Profile Header is not available at My firm profile page.");
						}

						// Verify My firm Profile header
						if (nim.getmyfirmprofiletext(30).getText().trim().equals("Firm Profile")) {
							appLog.info("'Firm Profile' header text is verified successfully.");
						} else {
							appLog.error("'Firm Profile' header text is not verified successfully.");
							sa.assertTrue(false, "'Firm Profile' header text is not verified successfully.");
						}

						// Verify No Logo Selected
						if (nim.geteditmyfirmlogoimage(30) != null) {
							if (nim.geteditmyfirmlogoimage(30).getAttribute("Alt")
									.equalsIgnoreCase("No Logo Selected")) {
								appLog.info("'No Logo Selected' is verified Successfully.");

							} else {
								appLog.error("'No Logo Selected' is not verified.");
								sa.assertTrue(false, "'No Logo Selected' is not verified.");
							}
						} else {
							appLog.error("No Logo image is not available.");
							sa.assertTrue(false, "No Logo image is not available.");
						}

						// Verify change Logo link in Edit mode.
						if(nim.getChangeLogoLink(30)==null){
							appLog.info("Change Logo link text is not available in My firm profile Edit mode.");
						}else{
							appLog.error("Change Logo link text is available in My firm profile Edit mode.");
							sa.assertTrue(false, "Change Logo link text is available in My firm profile Edit mode.");
						}

						// Verify Save button
						if (nim.getMyFirmProfilesaveBtn(30) != null) {
							appLog.info("Save button is available on My Profile Edit page.");

						} else {
							appLog.error("Save button is not available on My Profile Edit page.");
							sa.assertTrue(false, "Save button is not available on My Profile Edit page.");
						}

						// Verify Cancel button
						if (nim.getCancelButton(30) != null) {
							appLog.info("Cancel button is available on My Profile Edit page.");

						} else {
							appLog.error("Cancel button is not available on My Profile Edit page.");
							sa.assertTrue(false, "Cancel button is not available on My Profile Edit page.");
						}

						// Verify My Firm's Profile All Labels
						List<String> result_values = compareMultipleList(driver, myfirmprofileeditpageexpectedlabels,
								nim.getmyfirmprofileeditpagelabels());
						if (result_values.isEmpty()) {
							appLog.info("all my firm profile edit page labels are verified successfully.");
						} else {
							for (int i = 0; i < result_values.size(); i++) {
								appLog.error("Following values are not matched" + result_values.get(i));
								sa.assertTrue(false, "Following values are not matched" + result_values.get(i));
							}
						}

						// Verify All options in Fund Types
						if (nim.getMyFirmProfileAvailableTypesDropDownList(30) != null) {
							List<WebElement> actualfundtypesvalue = allOptionsInDropDrop(driver,
									nim.getMyFirmProfileAvailableTypesDropDownList(30), "Fundtype");
							if (actualfundtypesvalue.size() > 0) {
								List<String> result_values_1 = compareMultipleList(driver, fundtypesoptions,
										actualfundtypesvalue);
								if (result_values_1.isEmpty()) {
									appLog.info("All fund types options are verified successfully.");
								} else {
									for (int i = 0; i < result_values_1.size(); i++) {
										appLog.error("Following values are not matched" + result_values_1.get(i));
										sa.assertTrue(false,
												"Following values are not matched" + result_values_1.get(i));
									}
								}
							} else {
								appLog.info("Fund Type Options are Blank.");
								sa.assertTrue(false, "Fund Type Options are Blank.");
							}
						} else {
							appLog.error("Fund Type is not available.");
							sa.assertTrue(false, "Fund Type is not available.");
						}

						// Verify All Industries options
						if (nim.getMyFirmProfileAvailableIndustriesDropDownList(30) != null) {
							List<WebElement> actualindustriessvalue = allOptionsInDropDrop(driver,
									nim.getMyFirmProfileAvailableIndustriesDropDownList(30), "industries");
							if (actualindustriessvalue.size() > 0) {
								List<String> result_values_1 = compareMultipleList(driver, industriesoptions,
										actualindustriessvalue);
								if (result_values_1.isEmpty()) {
									appLog.info("All Industries options are verified successfully.");
								} else {
									for (int i = 0; i < result_values_1.size(); i++) {
										appLog.error("Following values are not matched" + result_values_1.get(i));
										sa.assertTrue(false,
												"Following values are not matched" + result_values_1.get(i));
									}
								}
							} else {
								appLog.info("Industries Options are Blank.");
								sa.assertTrue(false, "Industries Options are Blank.");
							}
						} else {
							appLog.error("Industries is not available.");
							sa.assertTrue(false, "Industries is not available.");
						}

						// Verify All Geofocus options
						if (nim.getMyFirmProfileAvailableGeoFocusDropDownList(30) != null) {
							List<WebElement> actualgeofocusvalue = allOptionsInDropDrop(driver,
									nim.getMyFirmProfileAvailableGeoFocusDropDownList(30), "geofocus");
							if (actualgeofocusvalue.size() > 0) {
								List<String> result_values_1 = compareMultipleList(driver, geofocusoptions,
										actualgeofocusvalue);
								if (result_values_1.isEmpty()) {
									appLog.info("All GeoFocus options are verified successfully.");
								} else {
									for (int i = 0; i < result_values_1.size(); i++) {
										appLog.error("Following values are not matched" + result_values_1.get(i));
										sa.assertTrue(false,
												"Following values are not matched" + result_values_1.get(i));
									}
								}
							} else {
								appLog.info("GeoFocus Options are Blank.");
								sa.assertTrue(false, "GeoFocus Options are Blank.");
							}
						} else {
							appLog.error("GeoFocus is not available.");
							sa.assertTrue(false, "GeoFocuse is not available.");
						}

						// Verifying default value in Selected Fund types.
						if (nim.getselectedfundtypes(30) != null) {
							List<WebElement> actualfundtypesselectedvalue = allOptionsInDropDrop(driver,
									nim.getselectedfundtypes(30), "Selected Fundtype");

							if (actualfundtypesselectedvalue.isEmpty()) {
								appLog.info("Selected Fund Types values are blank.");

							} else {
								appLog.error("Selected Fund Types option is not Blank.");
								sa.assertTrue(false, "Selected Fund Types option is not Blank.");
							}
						} else {
							appLog.error("Select Fund Type is not available.");
							sa.assertTrue(false, "Select Fund Type is not available.");
						}
						
						// Verifying default value in Selected Industries.
						if (nim.getselectedindustries(30) != null) {
							List<WebElement> actualindustriesselectedvalue = allOptionsInDropDrop(driver,
									nim.getselectedindustries(30), "Selected Industries");
							if (actualindustriesselectedvalue.isEmpty()) {
								appLog.info("Selected Industries values are blank.");

							} else {
								appLog.error("Selected Industries option is not Blank.");
								sa.assertTrue(false, "Selected Industries option is not Blank.");
							}
						} else {
							appLog.error("Select Industries is not available.");
							sa.assertTrue(false, "Select Industries is not available.");
						}
						
						// Verifying default value in Selected GeoFocus.
						if (nim.getselectedgeotypes(30) != null) {
							List<WebElement> actualgeofocusselectedvalue = allOptionsInDropDrop(driver,
									nim.getselectedgeotypes(30), "Selected Geo Focus");
							if (actualgeofocusselectedvalue.isEmpty()) {
								appLog.info("Selected Geo Focus values are blank.");

							} else {
								appLog.error("Selected Geo Focus option is not Blank.");
								sa.assertTrue(false, "Selected Geo Focus option is not Blank.");
							}
						} else {
							appLog.error("Select Geo Focuss is not available.");
							sa.assertTrue(false, "Select Geo Focus is not available.");
						}
						
						// Verify Values in all input boxes
						String[] myfirmprofileinputboxesexpectedvalues = { Org3UpdatedFirmName, M11UpFname + " " + M11UpLname,
								"", Org3CRMUser2EmailID, "", "", "", "", "", "0.00" };
						List<WebElement> value = nim.getmyfirmprofileeditpageinputboxes();

						if (!value.isEmpty()&&(value.size()==myfirmprofileinputboxesexpectedvalues.length)) {
							for (int i = 0; i < myfirmprofileinputboxesexpectedvalues.length; i++) {
								String res = value.get(i).getAttribute("value").trim();
								if (i == 3) {
									if (res != null) {
										appLog.info("E-mail: is available as prefilled.");

									} else {
										appLog.error("E-mail: is not available as prefilled.");
										sa.assertTrue(false, "E-mail: is not available as prefilled.");
									}
								}
								if (i != 3) {
									if(i==1){
										if(res!=null){
											appLog.info("Firm Contact: is not coming blank. Hence verified the Firm Contact.");
										}else{
											appLog.error("Firm Contact: is coming blank. Hence not verified the Firm Contact.");
											sa.assertTrue(false, "Firm Contact: is coming blank. Hence not verified the Firm Contact.");
										}
									}if(i!=1) {
										if (myfirmprofileinputboxesexpectedvalues[i].equalsIgnoreCase(res)) {
											appLog.info("Expected Value:>>"+myfirmprofileinputboxesexpectedvalues[i]+"is matched. Actual:>"+value.get(i).getAttribute("value").trim());

										} else{
											appLog.error("Expected Value:>>"+myfirmprofileinputboxesexpectedvalues[i]+"is not matched. Actual:>"+value.get(i).getAttribute("value").trim());
											sa.assertTrue(false, "Expected Value:>>"+myfirmprofileinputboxesexpectedvalues[i]+"is not matched. Actual:>"+value.get(i).getAttribute("value").trim());
										}
									}
								}
							}
						} else {
							appLog.error("Fields are not available on my firm profile page.");
							sa.assertTrue(false, "Fields are not available on my firm profile page.");
						}

						// Verify Mailing Street input box
						if (nim.getMyFirmPrpofileMailingStreet(30) != null) {
							if (nim.getMyFirmPrpofileMailingStreet(30).getText().trim().equals("")) {
								appLog.info("Mailing Street inputbox is verified successfully.");

							} else {
								appLog.error("Mailing Street inputbox is not verified.");
								sa.assertTrue(false, "Mailing Street inputbox is not verified.");
							}
						} else {
							appLog.error("Mailing Street Text box is not present on my Firm profile edit page.");
							sa.assertTrue(false,
									"Mailing Street Text box is not present on my Firm profile edit page.");
						}

						// Verify Description input box
						if (nim.getMyFirmProfileDescriptionTextArea(30) != null) {
							if (nim.getMyFirmProfileDescriptionTextArea(30).getText().trim().equals("")) {
								appLog.info("Description inputbox is verified successfully.");

							} else {
								appLog.error("Description inputbox is not verified successfully.");
								sa.assertTrue(false, "Description inputbox is not verified successfully.");
							}
						} else {
							appLog.error("Description Text box is not present on my Firm profile edit page.");
							sa.assertTrue(false, "Description Text box is not present on my Firm profile edit page.");
						}

						// verify my firm public login link
						if (nim.getmyfirmeditpagepubliclink(30) != null) {
							if (nim.getmyfirmeditpagepubliclink(30).getText().contains("https://")) {
								appLog.info("Public Login Link is verified successfully.");

							} else {
								appLog.error("Public Login Link is not verified.");
								sa.assertTrue(false, "Public Login Link is not verified.");
							}
						} else {
							appLog.error("Public Login link is not present on my Firm profile edit page.");
							sa.assertTrue(false, "Public Login link is not present on my Firm profile edit page.");
						}

					} else {
						appLog.error("My Firm profile is not opened in edit mode.");
						sa.assertTrue(false, "My Firm profile is not opened in edit mode.");
					}
				} else {
					appLog.error("My Firm profile edit icon is not available.");
					sa.assertTrue(false, "My Firm profile edit icon is not available.");
				}
			} else {
				appLog.error("Can not click on MyFirmProfile Link");
				sa.assertTrue(false, "Can not click on MyFirmProfile Link");
			}
			switchToDefaultContent(driver);
		} else {
			appLog.error("Can not click on NIM Tab");
			sa.assertTrue(false, "Can not click on NIM Tab");
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();

	}
	
	@Test
	public void M11tc008_verifyeditmyfirmerrormessage() {

		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();

		lp.CRMLogin(Org3CRMUser3EmailID, adminPassword);
		if (nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
			if (nim.clickOnSideMenusTab(sideMenu.MyFirmProfile)) {
				if (click(driver, nim.getmyprofileediticon(30), "Edit icon", action.SCROLLANDBOOLEAN)) {
					if (nim.getInsufficientPermissionHeader(60, sideMenu.MyFirmProfile) != null) {
						if (bp.verifyErrorMessageOnPage(NIMPageErrorMessage.insufficientPermissionErrorMessage,
								nim.getInsufficientPermissionErrorMesage(60, sideMenu.MyFirmProfile),
								"Insufficient Permission Error messgae")) {
							appLog.info("Insufficient Permission Error messgae is verified successfully.");
							if (nim.getInsufficientPermissionClosButton(30, sideMenu.MyFirmProfile) != null) {
								appLog.info(
										"Successfully verified Close button in Insufficient Permission Error pop up.");
							} else {
								appLog.error("Close button is not available in Insufficient Permission Error pop up.");
								sa.assertTrue(false,
										"Close button is not available in Insufficient Permission Error pop up.");
							}
							if (nim.getInsufficientPermissionCrossIcon(30, sideMenu.MyFirmProfile) != null) {
								appLog.info(
										"Successfully verified Cross icon in Insufficient Permission Error pop up.");
							} else {
								appLog.error("Cross icon is not available in Insufficient Permission Error pop up.");
								sa.assertTrue(false,
										"Cross icon is not available in Insufficient Permission Error pop up.");
							}

						} else {
							sa.assertTrue(false,
									"Insufficient Permission Error messgae is not verified.Expected:"
											+ NIMPageErrorMessage.insufficientPermissionErrorMessage + " Actual"
											+ getText(driver,
													nim.getInsufficientPermissionErrorMesage(60,
															sideMenu.MyFirmProfile),
													"Insufficient Permission Error messgae", action.BOOLEAN));
						}

						if (click(driver, nim.getInsufficientPermissionClosButton(30, sideMenu.MyFirmProfile),
								"Insufficient Error Close button", action.SCROLLANDBOOLEAN)) {
							if (nim.getInsufficientPermissionHeader(60, sideMenu.MyFirmProfile) == null) {
								appLog.info("Insufficient Error pop up is closed by clicking close button.");
							} else {
								appLog.error("Insufficient Error pop up is not closed.");
								sa.assertTrue(false, "Insufficient Error pop up is not closed.");
							}
						} else {
							appLog.error("Close button is not clicable.");
							sa.assertTrue(false, "Close button is not clicable.");
						}

						if (click(driver, nim.getmyprofileediticon(30), "Edit icon", action.SCROLLANDBOOLEAN)) {
							if (nim.getInsufficientPermissionHeader(60, sideMenu.MyFirmProfile) != null) {
								if (bp.verifyErrorMessageOnPage(NIMPageErrorMessage.insufficientPermissionErrorMessage,
										nim.getInsufficientPermissionErrorMesage(60, sideMenu.MyFirmProfile),
										"Insufficient Permission Error messgae")) {
									appLog.info("Insufficient Permission Error messgae is verified successfully.");

									if (click(driver,
											nim.getInsufficientPermissionCrossIcon(30, sideMenu.MyFirmProfile),
											"Insufficient Error Close button", action.SCROLLANDBOOLEAN)) {
										if (nim.getInsufficientPermissionHeader(60, sideMenu.MyFirmProfile) == null) {
											appLog.info("Insufficient Error pop up is closed by clicking Cross Icon.");
										} else {
											appLog.error("Insufficient Error pop up is not closed.");
											sa.assertTrue(false, "Insufficient Error pop up is not closed.");
										}
									} else {
										appLog.error("Close icon is not clicable.");
										sa.assertTrue(false, "Close icon is not clicable.");
									}
								} else {
									sa.assertTrue(false,
											"Insufficient Permission Error messgae is not verified.Expected:"
													+ NIMPageErrorMessage.insufficientPermissionErrorMessage + " Actual"
													+ getText(driver,
															nim.getInsufficientPermissionErrorMesage(60,
																	sideMenu.MyFirmProfile),
															"Insufficient Permission Error messgae", action.BOOLEAN));
								}

							} else {
								appLog.info("Insufficient Error pop up is not available.");
								sa.assertTrue(false, "Insufficient Error pop up is not available.");
							}
						} else {
							appLog.error("My Firm profile edit icon is not available.");
							sa.assertTrue(false, "My Firm profile edit icon is not available.");
						}
					} else {
						appLog.info("Insufficient Error pop up is not available.");
						sa.assertTrue(false, "Insufficient Error pop up is not available.");
					}
				} else {
					appLog.error("My Firm profile edit icon is not available.");
					sa.assertTrue(false, "My Firm profile edit icon is not available.");
				}
			} else {
				appLog.error("Can not click on MyFirmProfile Link");
				sa.assertTrue(false, "Can not click on MyFirmProfile Link");
			}
			switchToDefaultContent(driver);
		} else {
			appLog.error("Can not click on NIM Tab");
			sa.assertTrue(false, "Can not click on NIM Tab");
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}
	
	@Test
	public void M11tc009_verifymandatoryfielderrormessage(){
		
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();

		lp.CRMLogin(superAdminOrg3UserName,adminPassword);
		if (nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
			if (nim.clickOnSideMenusTab(sideMenu.MyFirmProfile)) {
				if (click(driver, nim.getmyprofileediticon(30), "Edit icon", action.SCROLLANDBOOLEAN)) {
					if(nim.getMyFirmProfileNameTextBox(30)!=null){
						if(sendKeys(driver, nim.getMyFirmProfileNameTextBox(30), "", "Firm Name Text box", action.SCROLLANDBOOLEAN)){
							if(click(driver, nim.getMyFirmProfilesaveBtn(10), "My Firm profile save button", action.SCROLLANDBOOLEAN)){
								if(nim.getmyprofilefirmnameerror(30).getText().trim().contains(NIMPageErrorMessage.myFirmProfileFirmNameErrorMessage)){
									appLog.info("Firm Name mandatory error message is verified Successfully. Expected :>>"+NIMPageErrorMessage.myFirmProfileFirmNameErrorMessage+"Actual :>>"+nim.getmyprofilefirmnameerror(30).getText());

								} else {
									appLog.error("Firm Name mandatory error message is not verified. Expected :>>"+NIMPageErrorMessage.myFirmProfileFirmNameErrorMessage+"Actual :>>"+nim.getmyprofilefirmnameerror(30).getText());
									sa.assertTrue(false,"Firm Name mandatory error message is not verified. Expected :>>"+NIMPageErrorMessage.myFirmProfileFirmNameErrorMessage+"Actual :>>"+nim.getmyprofilefirmnameerror(30).getText());
								}
							} else {
								appLog.error("Can not click on Save button");
								sa.assertTrue(false, "Can not click on Save button");
							}
						} else {
							appLog.error("Can not enter values in Firm name field.");
							sa.assertTrue(false, "Can not enter values in Firm name field.");
						}
					}else{
						appLog.error("Firm Name text box is not available.");
						sa.assertTrue(false, "Firm Name text box is not available.");
					}

					if(nim.getmyfirmemail(30)!=null){
						if(sendKeys(driver, nim.getmyfirmemail(30), "Test123", "Firm Email", action.SCROLLANDBOOLEAN)){
							if(sendKeys(driver, nim.getMyFirmProfileAUMTextBox(30), "Test123", "AUM", action.SCROLLANDBOOLEAN)){
								if(click(driver, nim.getMyFirmProfilesaveBtn(10), "My Firm profile save button", action.SCROLLANDBOOLEAN)){
									if(nim.getemailnameerror(30).getText().trim().contains(NIMPageErrorMessage.myFirmProfileEmailErrorMessage)){
										appLog.info("Invalid Email error message is verified Successfully. Expected :>>"+NIMPageErrorMessage.myFirmProfileEmailErrorMessage+"Actual :>>"+nim.getemailnameerror(30).getText());

									} else {
										appLog.error("Invalid Email error message is not verified. Expected :>>"+NIMPageErrorMessage.myFirmProfileEmailErrorMessage+"Actual :>>"+nim.getemailnameerror(30).getText());
										sa.assertTrue(false, "Invalid Email error message is not verified. Expected :>>"+NIMPageErrorMessage.myFirmProfileEmailErrorMessage+"Actual :>>"+nim.getemailnameerror(30).getText());
									}

									if(nim.geteaumerror(30).getText().trim().contains(NIMPageErrorMessage.myFirmProfileAUMErrorMessage)){
										appLog.info("Invalid AUM error message is verified. Expected :>>"+NIMPageErrorMessage.myFirmProfileAUMErrorMessage+"Actual :>>"+nim.geteaumerror(30).getText());

									}else{
										appLog.error("Invalid AUM error message is not verified. Expected :>>"+NIMPageErrorMessage.myFirmProfileAUMErrorMessage+"Actual :>>"+nim.geteaumerror(30).getText());
										sa.assertTrue(false, "Invalid AUM error message is not verified. Expected :>>"+NIMPageErrorMessage.myFirmProfileAUMErrorMessage+"Actual :>>"+nim.geteaumerror(30).getText());
									}

									if(nim.getMyFirmProfilePhone(10)!=null){
										if(sendKeys(driver, nim.getMyFirmProfilePhone(30), M11UpPhoneMyFirm, "AUM", action.SCROLLANDBOOLEAN)){
											appLog.info("Successfully Entered value in My Firm Phone Field.");
										}else{
											appLog.error("Can not enter value in My Firm Phone Field.");
											sa.assertTrue(false, "Can not enter value in My Firm Phone Field.");
										}
									}else{
										appLog.error("My Firm Phone Field is not available.");
										sa.assertTrue(false, "My Firm Phone Field is not available.");
									}

									if(click(driver, nim.getCancelButton(30),"Cancel button", action.SCROLLANDBOOLEAN)){
										if(nim.getmyfirmphonevalue(30).getText().trim().contains("")){
											appLog.info("After Clicking Cancel button, Phone Value is not saved.");
										}else{
											appLog.error("After Clicking Cancel button, Phone Value is saved.");
											sa.assertTrue(false, "After Clicking Cancel button, Phone Value is saved.");
										}
									}else{
										appLog.error("Not able to click on Cancel button.");
										sa.assertTrue(false, "Not able to click on Cancel button.");
									}
								} else {
									appLog.error("Can not click on Save button");
									sa.assertTrue(false, "Can not click on Save button");
								}
							} else {
								appLog.error("Can not enter values in AUM field.");
								sa.assertTrue(false, "Can not enter values in AUM field.");
							}
						}else{
							appLog.error("Can not enter values in Firm Email field.");
							sa.assertTrue(false, "Can not enter values in Firm Email field.");
						}
					}else{
						appLog.error("Email text box is not available.");
						sa.assertTrue(false, "Email text box is not available.");
					}
				} else {
					appLog.error("My Firm profile edit icon is not available.");
					sa.assertTrue(false, "My Firm profile edit icon is not available.");
				}
			} else {
				appLog.error("Can not click on MyFirmProfile Link");
				sa.assertTrue(false, "Can not click on MyFirmProfile Link");
			}
			switchToDefaultContent(driver);
		} else {
			appLog.error("Can not click on NIM Tab");
			sa.assertTrue(false, "Can not click on NIM Tab");
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}
	
	@Test
	public void M11tc010_verifysavingofmyfirmprofile(){

		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();

		lp.CRMLogin(superAdminOrg3UserName,adminPassword);
		if (nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
			if (nim.clickOnSideMenusTab(sideMenu.MyFirmProfile)) {
				if (click(driver, nim.getmyprofileediticon(10), "Edit icon", action.SCROLLANDBOOLEAN)) {
					if(sendKeys(driver, nim.getMyFirmProfileNameTextBox(10), Org3UpdatedFirmName+"UP", "Firm Name", action.SCROLLANDBOOLEAN)){
						if(sendKeys(driver, nim.getMyFirmProfileFirmContact(10), M11UpFirmContact, "Firm Contact", action.SCROLLANDBOOLEAN)){
							if(sendKeys(driver, nim.getMyFirmProfilePhone(10), M11UpPhoneMyFirm, "Firm phone", action.SCROLLANDBOOLEAN)){
								if(sendKeys(driver, nim.getMyFirmProfileWebsite(10), M11UpWebsiteMyFirm, "Firm phone", action.SCROLLANDBOOLEAN)){
									if(sendKeys(driver, nim.getMyFirmPrpofileMailingStreet(10), M11UpMailingStreetMyFirm, "Firm Mailing Street", action.SCROLLANDBOOLEAN)){
										if(sendKeys(driver, nim.getMyFirmProfileMailingCity(10), M11UpMailingCityMyFirm, "Firm Mailing City", action.SCROLLANDBOOLEAN)){
											if(sendKeys(driver, nim.getMyFirmProfileMailingState(10), M11UpMailingStateMyFirm, "Firm Mailing State", action.SCROLLANDBOOLEAN)){
												if(sendKeys(driver, nim.getMyFirmProfileMailingzip(10), M11UpMailingZipMyFirm, "Firm Mailing Zip", action.SCROLLANDBOOLEAN)){
													if(sendKeys(driver, nim.getMyFirmprofileMailingCountry(10), M11UpMailingCountyMyFirm, "Firm Mailing Country", action.SCROLLANDBOOLEAN)){
														if(sendKeys(driver, nim.getMyFirmProfileAUMTextBox(10), M11UpAUM, "Firm AUM", action.SCROLLANDBOOLEAN)){
															if(nim.getselectedfundtypes(20)!=null){
																Select fundoption = new Select(nim.getMyFirmProfileAvailableTypesDropDownList(20));
																try{
																	fundoption.selectByVisibleText(M11FundType);
																	if(click(driver, nim.getMyFirmProfileTypesAddBtn(10), "Fund Type Add Button", action.SCROLLANDBOOLEAN)){
																		appLog.info("Successfully Added "+M11FundType+"in Selected Types.");
																	}else{
																		appLog.error("Not able to select :>>"+M11FundType+"in Types.");
																		sa.assertTrue(false,"Not able to select :>>"+M11FundType+"in Types.");	
																	}
																}catch(Exception E){
																	List<WebElement> actualfundtypesselectedvalue = allOptionsInDropDrop(driver,
																			nim.getselectedfundtypes(30), "Selected Fundtype");
																	if(actualfundtypesselectedvalue.get(0).getText().trim().equalsIgnoreCase(M11FundType)){
																		appLog.error("FundType"+M11FundType+" is already available in Selected Fund Type. It Should be available in Selected option.");
																		sa.assertTrue(false,"FundType"+M11FundType+" is already available in Selected Fund Type. It Should be available in Selected option.");
																	}else{
																		appLog.error("FundType"+M11FundType+" is not available in Fund Type to Select.");
																	}
																}
															}else{
																appLog.error("Types option is not available.");
																sa.assertTrue(false, "Types option is not available.");
															}

															if(nim.getselectedindustries(20)!=null){
																Select industryoption = new Select(nim.getMyFirmProfileAvailableIndustriesDropDownList(20));
																try{
																	industryoption.selectByVisibleText(M11Industries);
																	if(click(driver, nim.getMyFirmProfileIndustriesAddBtn(10), "Industries Add Button", action.SCROLLANDBOOLEAN)){
																		appLog.info("Successfully Added "+M11Industries+"in Selected Industries.");
																	}else{
																		appLog.error("Not able to select :>>"+M11Industries+"in Industries.");
																		sa.assertTrue(false,"Not able to select :>>"+M11Industries+"in Industries.");	
																	}
																}catch(Exception E){
																	List<WebElement> actualindustrysselectedvalue = allOptionsInDropDrop(driver,
																			nim.getselectedindustries(30), "Selected Industry");
																	if(actualindustrysselectedvalue.get(0).getText().trim().equalsIgnoreCase(M11Industries)){
																		appLog.error("Industry "+M11Industries+" is already available in Selected Industry. It Should be available in Selected option.");
																		sa.assertTrue(false,"Industry "+M11Industries+" is already available in Selected Industry. It Should be available in Selected option.");
																	}else{
																		appLog.error("Industry "+M11Industries+" is not available in Industry to Select.");
																	}
																}
															}else{
																appLog.error("Industries option is not available.");
																sa.assertTrue(false, "Industries option is not available.");
															}

															if(nim.getselectedgeotypes(20)!=null){
																Select geofocusoption = new Select(nim.getMyFirmProfileAvailableGeoFocusDropDownList(20));
																try{
																	geofocusoption.selectByVisibleText(M11GeoFocus);
																	if(click(driver, nim.getMyFirmProfileGeoFocusAddBtn(10), "GeoFocus Add Button", action.SCROLLANDBOOLEAN)){
																		appLog.info("Successfully Added "+M11GeoFocus+"in Selected GeoFocus.");
																	}else{
																		appLog.error("Not able to select :>>"+M11GeoFocus+"in GeoFocus.");
																		sa.assertTrue(false,"Not able to select :>>"+M11GeoFocus+"in GeoFocus.");	
																	}
																}catch(Exception E){
																	List<WebElement> actualgeotypeselectedvalue = allOptionsInDropDrop(driver,
																			nim.getselectedgeotypes(30), "Selected GeoFocus");
																	if(actualgeotypeselectedvalue.get(0).getText().trim().equalsIgnoreCase(M11GeoFocus)){
																		appLog.error("Geo Focus "+M11GeoFocus+" is already available in Selected Geo Focus. It Should be available in Selected option.");
																		sa.assertTrue(false,"Geo Focus "+M11GeoFocus+" is already available in Selected Geo Focus. It Should be available in Selected option.");
																	}else{
																		appLog.error("Geo Focus "+M11GeoFocus+" is not available in Industry to Geo Focus.");
																	}
																}
															}else{
																appLog.error("GeoFocus option is not available.");
																sa.assertTrue(false, "GeoFocus option is not available.");
															}

															if(nim.getMyFirmProfileDescriptionTextArea(10)!=null){
																if(sendKeys(driver, nim.getMyFirmProfileDescriptionTextArea(10), M11Description, "Firm Description", action.SCROLLANDBOOLEAN)){
																	appLog.info("Successfully enter value in Firm Description Textbox. > "+M11Description);
																}else{
																	appLog.error("Not able to enter value in Description Text box.");
																	sa.assertTrue(false, "Not able to enter value in Description Text box.");
																}
															}else{
																appLog.error("Description Text box is not available.");
																sa.assertTrue(false, "Description Text box is not available.");
															}

															//click on save button
															if(click(driver, nim.getMyFirmProfilesaveBtn(10),"my Firm Save button", action.SCROLLANDBOOLEAN)){
																appLog.info("Successfully clicked on Save button.");
																System.out.println("address values after save."+nim.getaddressvalue(60).getText());

																//Verify Default values at My Firm profile view page
																String[] myfirmprofileexpectedvalues = {Org3UpdatedFirmName+"UP","public Login link",M11UpFirmContact,M11UpPhoneMyFirm,superAdminOrg3UserName,"http://"+M11UpWebsiteMyFirm,"Full Address","AUM Value",M11FundType,M11Industries,M11GeoFocus,M11Description};
																List<WebElement> myfirmprofileactualvalues = nim.getmyfirmprofilelabelsvalues();
																List<String> values = new ArrayList<String>();
																for(int i=1;i<13;i++)
																{	
																	if(myfirmprofileactualvalues.get(i)!=null)
																	{
																		values.add(myfirmprofileactualvalues.get(i).getText().trim());}
																	else{
																		appLog.error("Element: "+myfirmprofileactualvalues.get(i)+"not found to add in list.");
																		sa.assertTrue(false,"Element: "+myfirmprofileactualvalues.get(i)+"not found to add in list.");
																	}
																}
																String AUMAfterSave = "$ "+M11UpAUM+" mn USD";

																for(int i=0;i<values.size();i++){
																	appLog.info("Going to Compare :>>"+myfirmprofileexpectedvalues[i]+"With Actual value"+values.get(i));
																	if(i==1){
																		if(values.get(i).contains("https://")){
																			appLog.info("Public Login Link is verified as a link.");

																		}else{
																			appLog.error("Public Login Link is not verified as a link.");
																			sa.assertTrue(false, "Public Login Link is not verified as a link.");}
																	}else if(i==2){
																		if(values.get(i)!=null){
																			appLog.info("Firm Contact: is not coming blank. Hence verified the Firm Contact.");
																		}else{
																			appLog.error("Firm Contact: is coming blank. Hence not verified the Firm Contact.");
																			sa.assertTrue(false, "Firm Contact: is coming blank. Hence not verified the Firm Contact.");}
																	}else if(i==4){
																		if(values.get(i)!=null){
																			appLog.info("E-mail value at My firm profile page is not null. Hence passed.");

																		}else{
																			appLog.error("E-mail value at My firm profile page is coming as blank.");
																			sa.assertTrue(false, "E-mail value at My firm profile page is coming as blank.");}
																	}else if(i==6){
																		//Verify Address value after saving my firm page.
																		if(nim.getaddressvalue(30).getText().contains(M11UpMailingStreetMyFirm)){
																			if(nim.getaddressvalue(30).getText().contains(M11UpMailingCityMyFirm)){
																				if(nim.getaddressvalue(30).getText().contains(M11UpMailingStateMyFirm)){
																					if(nim.getaddressvalue(30).getText().contains(M11UpMailingZipMyFirm)){
																						if(nim.getaddressvalue(30).getText().contains(M11UpMailingCountyMyFirm)){
																							appLog.info("Address is verified after saving my profile page.");

																						}else{
																							appLog.error("Address is not verified after saving my profile page. Expected:"+M11UpMailingCountyMyFirm+"Actual :"+nim.getaddressvalue(30).getText());
																							sa.assertTrue(false, "Address is not verified after saving my profile page. Expected:"+M11UpMailingCountyMyFirm+"Actual :"+nim.getaddressvalue(30).getText());}
																					}else{
																						appLog.error("Address is not verified after saving my profile page. Expected:"+M11UpMailingZipMyFirm+"Actual :"+nim.getaddressvalue(30).getText());
																						sa.assertTrue(false, "Address is not verified after saving my profile page. Expected:"+M11UpMailingZipMyFirm+"Actual :"+nim.getaddressvalue(30).getText());}
																				}else{
																					appLog.error("Address is not verified after saving my profile page. Expected:"+M11UpMailingStateMyFirm+"Actual :"+nim.getaddressvalue(30).getText());
																					sa.assertTrue(false, "Address is not verified after saving my profile page. Expected:"+M11UpMailingStateMyFirm+"Actual :"+nim.getaddressvalue(30).getText());}
																			}else{
																				appLog.error("Address is not verified after saving my profile page. Expected:"+M11UpMailingCityMyFirm+"Actual :"+nim.getaddressvalue(30).getText());
																				sa.assertTrue(false, "Address is not verified after saving my profile page. Expected:"+M11UpMailingCityMyFirm+"Actual :"+nim.getaddressvalue(30).getText());}
																		}else{
																			appLog.error("Address is not verified after saving my profile page. Expected:"+M11UpMailingStreetMyFirm+"Actual :"+nim.getaddressvalue(30).getText());
																			sa.assertTrue(false, "Address is not verified after saving my profile page. Expected:"+M11UpMailingStreetMyFirm+"Actual :"+nim.getaddressvalue(30).getText());}
																	}else if(i==7){
																		if(values.get(i).equalsIgnoreCase(AUMAfterSave)){
																			appLog.info("Successfully verified AUM value on My firm page.");
																		}else{
																			appLog.error("AUM values is not available.");
																			sa.assertTrue(false, "AUM values is not available.");
																		}
																	}else{
																		if(myfirmprofileexpectedvalues[i].equalsIgnoreCase(values.get(i))){
																			appLog.info("Following value at My firm profile value is matched. Expected Value :>>"+myfirmprofileexpectedvalues[i]+"Actual Vaule:"+values.get(i));

																		}else{
																			appLog.error("Following value at My firm profile page is not matched. Expected Value :>>"+myfirmprofileexpectedvalues[i]+"Actual Vaule:"+values.get(i));
																			sa.assertTrue(false, "Following value at My firm profile page is not matched. Expected Value :>>"+myfirmprofileexpectedvalues[i]+"Actual Vaule:"+values.get(i));}
																	}
																}
																
																//Verify Click of 'WebSite' link.
																if(click(driver, nim.getwebsitelink(30), "WebSitelink", action.SCROLLANDBOOLEAN)){
																	String parentId=switchOnWindow(driver);
																	if(parentId!=null) {
																		appLog.info("Successfully Clicked on WebSite link.");
																		if(getURL(driver, 30).contains(M11UpWebsiteMyFirm)){
																			appLog.info("WebSite Link is opened successfully.");

																		}else{
																			appLog.error("On Clicking WebSite link user in not directed to right URL.");
																			sa.assertTrue(false, "On Clicking WebSite link user in not directed to right URL.");
																		}
																		driver.close();
																		driver.switchTo().window(parentId);
																		switchToFrame(driver, 10, nim.getFrame( PageName.NavatarInvestorManager, 10));

																	} else {
																		appLog.error("WebSite is not opened in New tab.");
																		sa.assertTrue(false, "WebSite is not opened in New tab.");
																	}	
																}else{
																	appLog.error("Not able to Click on WebSite link.");
																	sa.assertTrue(false, "Not able to Click on WebSite link.");}


															}else{
																appLog.error("Can not click on Save button.");
																sa.assertTrue(false, "Can not click on Save button.");
															}
														} else{
															appLog.error("Can not enter values in Firm AUM text box");
															sa.assertTrue(false, "Can not enter values in Firm AUM text box");
														}
													} else{
														appLog.error("Can not enter values in Firm Mailing Country text box");
														sa.assertTrue(false, "Can not enter values in Firm Mailing Country text box");
													}
												} else{
													appLog.error("Can not enter values in Firm Mailing Zip text box");
													sa.assertTrue(false, "Can not enter values in Firm Mailing Zip text box");
												}
											} else{
												appLog.error("Can not enter values in Firm Mailing State text box");
												sa.assertTrue(false, "Can not enter values in Firm Mailing State text box");
											}
										} else{
											appLog.error("Can not enter values in Firm Mailing City text box");
											sa.assertTrue(false, "Can not enter values in Firm Mailing City text box");
										}
									} else{
										appLog.error("Can not enter values in Firm Mailing Street text box");
										sa.assertTrue(false, "Can not enter values in Firm Mailing Street text box");
									}
								} else{
									appLog.error("Can not enter values in Firm WebSite text box");
									sa.assertTrue(false, "Can not enter values in Firm WebSite text box");
								}
							}else{
								appLog.error("Can not enter values in Firm Phone text box");
								sa.assertTrue(false, "Can not enter values in Firm Phone text box");
							}
						}else{
							appLog.error("Can not enter values in Firm Contact text box");
							sa.assertTrue(false, "Can not enter values in Firm Contact text box");
						}
					}else{
						appLog.error("Can not enter values in Firm name text box");
						sa.assertTrue(false, "Can not enter values in Firm name text box");
					}
				} else {
					appLog.error("My Firm profile edit icon is not available.");
					sa.assertTrue(false, "My Firm profile edit icon is not available.");
				}
			} else {
				appLog.error("Can not click on MyFirmProfile Link");
				sa.assertTrue(false, "Can not click on MyFirmProfile Link");
			}
			switchToDefaultContent(driver);
		} else {
			appLog.error("Can not click on NIM Tab");
			sa.assertTrue(false, "Can not click on NIM Tab");
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}

	@Test
	public void M11tc011_verifysavingofmyfirmprofilefromDelegateAdmin(){

		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();

		lp.CRMLogin(Org3CRMUser2EmailID,adminPassword);
		if (nim.clickOnTab(TabName.NIMTab)){
			switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
			if (nim.clickOnSideMenusTab(sideMenu.MyFirmProfile)) {
				if (click(driver, nim.getmyprofileediticon(10), "Edit icon", action.SCROLLANDBOOLEAN)) {
					//verify Save and cancel button
					sa.assertTrue(nim.getCancelButton(15).isDisplayed(),"Cancel button is displayed.");
					sa.assertTrue(nim.getMyFirmProfilesaveBtn(15).isDisplayed(),"Save button is available.");
					if(sendKeys(driver, nim.getMyFirmProfileNameTextBox(10), Org3UpdatedFirmName+"Delegate Up", "Firm Name", action.SCROLLANDBOOLEAN)){

						//click on save button
						if(click(driver, nim.getMyFirmProfilesaveBtn(10),"my Firm Save button", action.SCROLLANDBOOLEAN)){
							appLog.info("Successfully clicked on Save button from Delegate Admin");
							//Verify Firm Name after saving after saving my firm profile from Delegate Admin.
							if(nim.getfirmname(40).getText().equalsIgnoreCase(Org3UpdatedFirmName+"Delegate Up")){
								appLog.info("Successfully Verified Firm name after saving my firm by Delegate Admin.");
							}else{
								sa.assertTrue(false,"Not Verifed Firm name after saving my firm profile from delegate admin.");
								appLog.error("Not Verifed Firm name after saving my firm profile from delegate admin.");
							}
							if(nim.getlastupdatedontext(40).getText().contains(M11UpFname)&&(nim.getlastupdatedontext(40).getText().contains(M11UpLname))&&(nim.getlastupdatedontext(40).getText().contains(getDateAccToTimeZone("America/New_York", "M/d/YYYY")))){
								appLog.info("Successfully Verified Last Updated On after saving my firm from delegate admin");
							}else{
								appLog.error("Not Verify Last Updated on after saving my firm from delegate admin."+"Expected:>>"+Org3CRMUser2FirstName+" "+Org3CRMUser2LastName+" "+getDateAccToTimeZone("America/New_York", "M/d/YYYY")+"Actual Value:>>"+nim.getlastupdatedontext(40).getText());
								sa.assertTrue(false,"Not Verify Last Updated on after saving my firm from delegate admin.");
							}
						}else{
							appLog.error("Can not click on Save button.");
							sa.assertTrue(false, "Can not click on Save button.");
						}
					}else{
						appLog.error("Can not enter values in Firm name text box");
						sa.assertTrue(false, "Can not enter values in Firm name text box");
					}
				} else {
					appLog.error("My Firm profile edit icon is not available.");
					sa.assertTrue(false, "My Firm profile edit icon is not available.");
				}
			} else {
				appLog.error("Can not click on MyFirmProfile Link");
				sa.assertTrue(false, "Can not click on MyFirmProfile Link");
			}
			switchToDefaultContent(driver);
		} else {
			appLog.error("Can not click on NIM Tab");
			sa.assertTrue(false, "Can not click on NIM Tab");
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}

	@Test
	public void M11tc012_VerifyUpdateOfNameinLastUpdatedOn(){

		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();

		lp.CRMLogin(Org3CRMUser2EmailID,adminPassword);
		if (nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
			if (nim.clickOnSideMenusTab(sideMenu.Profiles)) {
				if (click(driver, nim.getmyprofileediticon(60), "Edit icon", action.SCROLLANDBOOLEAN)){
					if(sendKeys(driver, nim.getMyProfileFirstName(30), Org3CRMUser2UpdatedFirstName1+"Fname", "FirstName", action.SCROLLANDBOOLEAN)){
						if(sendKeys(driver, nim.getMyProfileLastName(30), Org3CRMUser2UpdatedLastName1+"Lname", "LastName", action.SCROLLANDBOOLEAN)){

							//click on save button
							if(click(driver, nim.getMyProfileSaveButton(30),"my profile Save button", action.SCROLLANDBOOLEAN)){
								appLog.info("Successfully Saved My Profile from Delegate Admin");
							}else{
								appLog.error("Can not click on Save button.");
								sa.assertTrue(false, "Can not click on Save button.");
							}
							if (nim.clickOnSideMenusTab(sideMenu.MyFirmProfile)){
								if(nim.getlastupdatedontext(40).getText().contains(Org3CRMUser2UpdatedFirstName1+"Fname")&&(nim.getlastupdatedontext(40).getText().contains(Org3CRMUser2UpdatedLastName1+"Lname"))){
									appLog.info("Successfully Verified Last Updated On after saving my profile from delegate admin");
								} else {
									appLog.error("Not Verify Last Updated on after saving my profile from delegate admin."+"Expected:>>"+M11UpFname+"Fname"+" "+M11UpLname+"Lname"+" "+getDateAccToTimeZone("America/New_York", "M/d/YYYY")+"Actual Value:>>"+nim.getlastupdatedontext(40).getText());
									sa.assertTrue(false,"Not Verify Last Updated on after saving my firm from delegate admin.");
								}
							} else {
								appLog.error("Can not click on MyFirmProfile Link");
								sa.assertTrue(false, "Can not click on MyFirmProfile Link");
							}
						} else {
							appLog.error("Can not enter values in Last name text box");
							sa.assertTrue(false, "Can not enter values in Last name text box");
						}
					} else {
						appLog.error("Can not enter values in First name text box");
						sa.assertTrue(false, "Can not enter values in First name text box");
					}
				} else {
					appLog.error("My profile edit icon is not available.");
					sa.assertTrue(false, "My profile edit icon is not available.");
				}
			} else {
				appLog.error("Can not click on My Profile Link");
				sa.assertTrue(false, "Can not click on My Profile Link");
			}
			switchToDefaultContent(driver);
		} else {
			appLog.error("Can not click on NIM Tab");
			sa.assertTrue(false, "Can not click on NIM Tab");
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	} 

	@Test
	public void M11tc013_1_VerifyChangeLogoWindow(){

		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();

		lp.CRMLogin(superAdminOrg3UserName,adminPassword);
		if (nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
			if (nim.clickOnSideMenusTab(sideMenu.MyFirmProfile)) {
				if(click(driver, nim.getChangeLogoLink(30), "Change Logo Link", action.SCROLLANDBOOLEAN)){
					String parentID = switchOnWindow(driver);
					if(parentID!=null) {
						if(nim.getBrowseButton(60)!=null) {
							appLog.info("Browser Button is displaying on logo pop up");
						}else {
							appLog.error("Browser Button is not displaying on logo pop up");
							sa.assertTrue(false,"Browser Button is not displaying on logo pop up");
						}
						if(nim.getsubmitButtonFirmLogo(60)!=null) {
							appLog.info("Submit button is displaying on logo pop up");
						}else {
							appLog.error("Submit button is not displaying on logo pop up");
							sa.assertTrue(false, "Submit button is not displaying on logo pop up");
						}

						driver.close();
						driver.switchTo().window(parentID);
						switchToFrame(driver,10,nim.getFrame( PageName.NavatarInvestorManager, 20));
						if(nim.getsubmitButtonFirmLogo(10)==null) {
							appLog.info("browse pop is closed Successfully.");
						}else {
							appLog.error("Browse pop is not closed after click on cancel button");
							sa.assertTrue(false, "Browse pop is not closed after click on cancel button");
						}

					}else{
						appLog.info("Change logo Link is not working.");
						sa.assertTrue(false, "Change logo link is not working so cannot check the functionality.");
					}
				}else{
					appLog.error("Change Logo link is not clickable.");
					sa.assertTrue(false, "Change Logo link is not clickable.");
				}
			} else {
				appLog.error("Can not click on MyFirmProfile Link");
				sa.assertTrue(false, "Can not click on MyFirmProfile Link");
			}
			switchToDefaultContent(driver);
		} else {
			appLog.error("Can not click on NIM Tab");
			sa.assertTrue(false, "Can not click on NIM Tab");
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}

	@Test
	public void M11tc013_2_VerifyChangeLogoErrorMessage(){

		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();

		lp.CRMLogin(superAdminOrg3UserName,adminPassword);
		if (nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
			if (nim.clickOnSideMenusTab(sideMenu.MyFirmProfile)) {
				if(click(driver, nim.getChangeLogoLink(30), "Change Logo Link", action.SCROLLANDBOOLEAN)){
					String parentID = switchOnWindow(driver);
					if(parentID!=null) {
						if(click(driver, nim.getsubmitButtonFirmLogo(30), "Change Logo Submit Button", action.SCROLLANDBOOLEAN)){
							if(nim.getsubmitButtonError(30).getText().trim().equalsIgnoreCase(NIMPageErrorMessage.myFirmProfileLogoErrorMessage)){
								appLog.info("Successfully Verified Error message after clicking Upload button."+"Expected:>>"+NIMPageErrorMessage.myFirmProfileLogoErrorMessage+"Actual:>>"+nim.getsubmitButtonError(30).getText().trim());
								driver.close();
								driver.switchTo().window(parentID);
								switchToFrame(driver, 30, nim.getFrame( PageName.NavatarInvestorManager, 20));

							}else{
								driver.close();
								driver.switchTo().window(parentID);
								switchToFrame(driver, 30, nim.getFrame( PageName.NavatarInvestorManager, 20));
								appLog.error("Error message is not verified after clicking Upload button.");
								sa.assertTrue(false,"Error message is not verified after clicking Upload button."+"Expected:>>"+NIMPageErrorMessage.myFirmProfileLogoErrorMessage+"Actual:>>"+nim.getsubmitButtonError(30).getText().trim());
							}
						}else{
							appLog.error("Submit button is not clicked Successfully.");
							sa.assertTrue(false, "Submit button is not clicked Successfully.");
						}
					}else{
						appLog.info("Change logo Link is not working.");
						sa.assertTrue(false, "Change logo link is not working so cannot check the functionality.");
					}
				}else{
					appLog.error("Change Logo link is not clickable.");
					sa.assertTrue(false, "Change Logo link is not clickable.");
				}
			} else {
				appLog.error("Can not click on MyFirmProfile Link");
				sa.assertTrue(false, "Can not click on MyFirmProfile Link");
			}
			switchToDefaultContent(driver);
		} else {
			appLog.error("Can not click on NIM Tab");
			sa.assertTrue(false, "Can not click on NIM Tab");
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();		
	}

	@Test
	public void M11tc013_3_VerifyChangeLogoFileTypeError(){

		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();

		lp.CRMLogin(superAdminOrg3UserName,adminPassword);
		if (nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
			if (nim.clickOnSideMenusTab(sideMenu.MyFirmProfile)) {
				if(click(driver, nim.getChangeLogoLink(30), "Change Logo Link", action.SCROLLANDBOOLEAN)){
					String[] UploadImage = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.Logo_Name).split(",");
					//System.out.println("Noofimages"+UploadImage.length);
					//String[] UploadImage = ExcelUtils.readData(path, "FilePath", 440, 19).split(",");
					int length=UploadImage.length;
					String parentID = switchOnWindow(driver);
					while(length>0){
						String imagePath = System.getProperty("user.dir") + "\\FilesToUpdate\\DifferentTypeFormat\\"+UploadImage[length-1];
						if(parentID!=null) {
							ThreadSleep(5000);
							if (sendKeys(driver, nim.getBrowseButton(60), imagePath, "Browser Button", action.BOOLEAN)) {
								ThreadSleep(5000);
								if(click(driver, nim.getsubmitButtonFirmLogo(30), "Change Logo Submit Button", action.SCROLLANDBOOLEAN)){
									if(nim.getFiletypeError(20).getText().trim().equalsIgnoreCase(NIMPageErrorMessage.ChangeLogoInvalidTypeErrorMessage)){
										appLog.info("Error Message is verified For."+UploadImage[length-1]);

									}else{
										appLog.error("Error Message is not Displayed For."+UploadImage[length-1]);
										sa.assertTrue(false,"Error Message is not Displayed For."+UploadImage[length-1]);
									}
								}else{
									appLog.error("Submit button is not working right.");
									sa.assertTrue(false,"Submit button is not working right.");
								}
							}else{
								appLog.error("Broswe Button is not working, Can not proceed.");
								sa.assertTrue(false, "Broswe Button is not working so cannot check functionality.");
							}
						}else{
							appLog.info("Change logo Link is not working.");
							sa.assertTrue(false, "Change logo link is not working so cannot check the functionality.");
						}
						length--;
					}
					driver.close();
					driver.switchTo().window(parentID);
					switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
				}else{
					appLog.error("Change Logo link is not clickable.");
					sa.assertTrue(false, "Change Logo link is not clickable.");
				}
			} else {
				appLog.error("Can not click on MyFirmProfile Link");
				sa.assertTrue(false, "Can not click on MyFirmProfile Link");
			}
			switchToDefaultContent(driver);
		} else {
			appLog.error("Can not click on NIM Tab");
			sa.assertTrue(false, "Can not click on NIM Tab");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M11tc014_VerifyUpdationofLogo(){

		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();

		lp.CRMLogin(superAdminOrg3UserName,adminPassword);
		if (nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
			if (nim.clickOnSideMenusTab(sideMenu.MyFirmProfile)) {

				String[] UploadImage = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.Logo_Name).split(",");
				int length=UploadImage.length;					
				while(length>0){
					if(click(driver, nim.getChangeLogoLink(30), "Change Logo Link", action.SCROLLANDBOOLEAN)){
						String parentID = switchOnWindow(driver);
						String imagePath = System.getProperty("user.dir") + "\\FilesToUpdate\\LogoImage\\"+UploadImage[length-1];
						if(parentID!=null) {
							//ThreadSleep(5000);
							if (sendKeys(driver, nim.getBrowseButton(60), imagePath, "Browser Button", action.BOOLEAN)) {
								//ThreadSleep(5000);
								if(click(driver, nim.getsubmitButtonFirmLogo(30), "Change Logo Submit Button", action.SCROLLANDBOOLEAN)){
									//verifying All Buttons
									if(nim.getLogoZoomOutbutton(30)!=null) {
										appLog.info("Zoom Out button is displayed for."+UploadImage[length-1]);
									}else {
										appLog.error("Zoom Out button is not displayed for."+UploadImage[length-1]);
										sa.assertTrue(false,"Zoom Out button is not displayed for."+UploadImage[length-1]);
									}
									if(nim.getLogoZoomInbutton(30)!=null) {
										appLog.info("Zoom In button is displayed for."+UploadImage[length-1]);
									}else {
										appLog.error("Zoom In button is not displayed for."+UploadImage[length-1]);
										sa.assertTrue(false,"Zoom In button is not displayedfor."+UploadImage[length-1]);
									}
									if(nim.getSaveButtonImageUpload(30)!=null) {
										appLog.info("Save button is displayed while updating Firm Logofor."+UploadImage[length-1]);
									}else {
										appLog.error("Save button is not displayed while updating Firm Logo for."+UploadImage[length-1]);
										sa.assertTrue(false,"Save button is not displayed while updating Firm Logofor."+UploadImage[length-1]);
									}
									if (nim.clickUsingCssSelectorPath("a[title=Save]", "save button")) {
									//if(click(driver, nim.getSaveButtonImageUpload(20), "Save button", action.SCROLLANDBOOLEAN)){
										appLog.info("Upload Logo Save button is clicked successfulyfor."+UploadImage[length-1]);

										if(switchToAlertAndGetMessage(driver, 20, action.GETTEXT).equalsIgnoreCase(NIMPageErrorMessage.LogoUploadConfirmationMessage)){
											if (switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT)) {
												appLog.info("Succesffuly accepted Alert while uploading logo and Logo is Upload successfully for."+UploadImage[length-1]);
												ThreadSleep(10000);
												driver.switchTo().window(parentID);
												switchToFrame(driver, 10, lp.getNIMTabParentFrame_Lightning(PageName.NavatarInvestorManager));
												switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
											} else{
												driver.close();
												driver.switchTo().window(parentID);
												switchToFrame(driver, 10, lp.getNIMTabParentFrame_Lightning(PageName.NavatarInvestorManager));
												switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
												appLog.error("Alert is not accepted.for."+UploadImage[length-1]);
												sa.assertTrue(false, "Alert is not accepted.for."+UploadImage[length-1]);
											}
										}else{
											driver.close();
											driver.switchTo().window(parentID);
											switchToFrame(driver, 10, lp.getNIMTabParentFrame_Lightning(PageName.NavatarInvestorManager));
											switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
											appLog.error("Alert is not available after clicking on Save button so not able to upload logo for."+UploadImage[length-1]);
											sa.assertTrue(false,"Alert is not available after clicking on Save button so not able to upload logofor."+UploadImage[length-1]);
										}
									}else{
										driver.close();
										driver.switchTo().window(parentID);
										switchToFrame(driver, 10, lp.getNIMTabParentFrame_Lightning(PageName.NavatarInvestorManager));
										switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
										appLog.error("Save button is not clicked.");
										sa.assertTrue(false,"Save button is not clicked.");
									}
								}else{
									driver.close();
									driver.switchTo().window(parentID);
									switchToFrame(driver, 10, lp.getNIMTabParentFrame_Lightning(PageName.NavatarInvestorManager));
									switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
									appLog.error("Submit button is not working right.");
									sa.assertTrue(false,"Submit button is not working right.");
								}
							}else{
								driver.close();
								driver.switchTo().window(parentID);
								switchToFrame(driver, 10, lp.getNIMTabParentFrame_Lightning(PageName.NavatarInvestorManager));
								switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
								appLog.error("Broswe Button is not working, Can not proceed.");
								sa.assertTrue(false, "Broswe Button is not working so cannot check functionality.");
							}
						}else{
							driver.close();
							driver.switchTo().window(parentID);
							switchToFrame(driver, 10, lp.getNIMTabParentFrame_Lightning(PageName.NavatarInvestorManager));
							switchToFrame(driver, 20, nim.getFrame( PageName.NavatarInvestorManager, 20));
							appLog.info("Change logo link is not working so cannot check the functionality.");
							sa.assertTrue(false, "Change logo link is not working so cannot check the functionality.");
						}
					}else{
						appLog.error("Change Logo link is not clickable.");
						sa.assertTrue(false, "Change Logo link is not clickable.");
					}
					length--;
				}	
			} else {
				appLog.error("Can not click on MyFirmProfile Link");
				sa.assertTrue(false, "Can not click on MyFirmProfile Link");
			}
			switchToDefaultContent(driver);
		} else {
			appLog.error("Can not click on NIM Tab");
			sa.assertTrue(false, "Can not click on NIM Tab");
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}
	
	@Test
	public void M11tc015_AddFolderTemplate() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		PartnershipPageBusinessLayer pp = new PartnershipPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(Org3CRMUser3EmailID, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
			
			if (np.getFolderTemplatesHead(30)!=null) {
				appLog.info("successfully verified default side menu opened is folder templates");
			}
			else {
				appLog.error("folder templates is not the default opened tab");
				sa.assertTrue(false, "folder templates is not the default opened tab");
			}
			if (np.getDropdownTempType(30)!=null) {
				appLog.info("temp type dropdown is successfully displayed");
			}
			else {
				appLog.error("temp type dropdown could not be displayed");
				sa.assertTrue(false, "temp type dropdown could not be displayed");
			}
			if (np.getRecordsCount(30)!=null) {
				appLog.info("records count text is successfully displayed");
			}
			else {
				appLog.error("records count is not visible on folder template page");
				sa.assertTrue(false, "records count is not visible on folder template page");
			}
			if (np.getAddFolderTemplateButton(30)!=null) {
				appLog.info("add folder template button is successfully displayed");
			}
			else {
				appLog.error("add folder template button is visible");
				sa.assertTrue(false, "add folder template button is visible");
			}
			if (selectVisibleTextFromDropDown(driver, np.getDropdownTempType(30), "text", "All Templates")) {
				appLog.info("successfully selected all templates");
			}
			else {
				appLog.error("could not select all templates");
				sa.assertTrue(false, "could not select all templates");
			}
			//added after bug
			if (selectVisibleTextFromDropDown(driver, np.getDropdownTempType(30), "text", "Other Users' Templates")) {
				appLog.info("successfully selected all templates");
			}
			else {
				appLog.error("could not select all templates");
				sa.assertTrue(false, "could not select all templates");
			}

			if (selectVisibleTextFromDropDown(driver, np.getDropdownTempType(30), "text", "My Templates")) {
				appLog.info("successfully selected My templates");
			}
			else {
				appLog.error("could not select My templates");
				sa.assertTrue(false, "could not select My templates");
			}
			
			if (click(driver, np.getAddFolderTemplateButton(30), "add folder template button", action.BOOLEAN)) {
				if (np.getFolderTemplateSaveButton(30)!=null) {
					appLog.info("save button is successfully displayed");
				}
				else {
					appLog.error("save button is not visible on folder templates page");
					sa.assertTrue(false, "save button is not visible on folder templates page");
				}
				if (np.getFolderTemplatesHead(30)!=null) {
					appLog.info("folder templates head is successfully displayed");
				}
				else {
					appLog.error("folder templates head is not visible");
					sa.assertTrue(false, "folder templates head is not visible");
				}
				if (np.getFolderTempAddCancelBtn(30)!=null) {
					appLog.info("folder template cancel button is successfully displayed");
					
				}
				else {
					appLog.error("folder template cancel button is not visible");
					sa.assertTrue(false, "folder template cancel button is not visible");
				}
				if (np.getFolders100max(30)!=null) {
					appLog.info("folders 100 max text is successfully verified");
				}
				else {
					appLog.error("could not verify folders 100 max text");
					sa.assertTrue(false, "could not verify folders 100 max text");
				}
				if (np.getParentAddFolderBtn(30)!=null) {
					appLog.info("+ icon is successfully displayed");
				}
				else {
					appLog.error("+ iconn is not visible in front of add a folders");
					sa.assertTrue(false, "+ iconn is not visible in front of add a folders");
				}
				if (click(driver, np.getParentAddFolderBtn(30), "+ icon on add folders", action.BOOLEAN)) {
					if (np.getAddFolderText(30)!=null) {
						appLog.info("add a folder text is successfully displayed");
					}
					else {
						appLog.error("add a folder text is not visible");
						sa.assertTrue(false, "add a folder text is not visible");
					}
					if (isSelected(driver, np.getStandardFolderRadioBtnAddFolderWindow(30), "std folder radio button")) {
						appLog.info("std folder radio button is already selected");
					}
					else {
						appLog.error("std folder button is not selected by default");
						sa.assertTrue(false, "std folder button is not selected by default");
					}
					if (isDisplayed(driver, FindElement(driver, "//span[contains(text(),'Folder Name:')]", "folder name text on add folder popup", action.BOOLEAN, 30), "visibility", 10, "folder name text on add folder popup")!=null) {
						appLog.info("successfully displayed folder name on add folder popup");
					}
					else {
						appLog.error("could not verify folder name text present on folder template add folder");
						sa.assertTrue(false, "could not verify folder name text present on folder template add folder");
					}
					if (np.getSaveButtonAddFolder(20)!=null) {
						appLog.info("successfully verified save button on add folder");
					}
					else {
						appLog.error("save button is not displayed on add folder popup");
						sa.assertTrue(false, "save button is not displayed on add folder popup");
					}
					if (np.getcancelButtonAddFolder(20)!=null) {
						appLog.info("successfully verified cancel button on add folder");
					}
					else {
						appLog.error("cancel button is not dispalyed on add folder popup");
						sa.assertTrue(false, "cancel button is not dispalyed on add folder popup");
					}
					boolean flag = true;
					if (mouseOverOperation(driver, np.getTooltipAddFolder(30))) {
						ThreadSleep(5000);
						if (!np.getTooltipMessageAddFolder(30).getText().trim().contains(NIMPageErrorMessage.addFolderToolTip1)) {
							flag = false;
							appLog.info("tooltip 1 is wrong");
						}
						if (!np.getTooltipMessageAddFolder(30).getText().trim().contains(NIMPageErrorMessage.addFolderToolTip2)) {
							flag = false;
							appLog.info("tooltip 2 is wrong");
						}
						if (!np.getTooltipMessageAddFolder(30).getText().trim().contains(NIMPageErrorMessage.addFolderToolTip3)) {
							flag = false;
							appLog.info("tooltip 3 is wrong");
						}
						if (!np.getTooltipMessageAddFolder(30).getText().trim().contains(NIMPageErrorMessage.addFolderToolTip4)) {
							flag = false;
							appLog.info("tooltip 4 is wrong");
						}
						if (!np.getTooltipMessageAddFolder(30).getText().trim().contains(NIMPageErrorMessage.addFolderToolTip5)) {
							flag = false;
							appLog.info("tooltip 5 is wrong");
						}
						if (flag) {
							appLog.info("successfully verified tooltip text on add folder page");
						}
						else {
							appLog.error("tooltip text for add folder is not correct");
							sa.assertTrue(false, "tooltip text for add folder is not correct");
						}
						click(driver, np.getcancelButtonAddFolder(30), "add folder cancel button", action.BOOLEAN);
					}
					else {
						appLog.error("mouse over operation could not be done");
						sa.assertTrue(false, "mouse over operation could not be done");
					}
				}
				else {
					appLog.error("add folder + icon is not clickable");
					sa.assertTrue(false, "add folder + icon is not clickable");
				}
			}
			else {
				appLog.error("add folder template button is not clickable");
				sa.assertTrue(false, "add folder template button is not clickable");
			}
			switchToDefaultContent(driver);
		}
		else {
			appLog.error("nim tab is not clickable");
			sa.assertTrue(false, "nim tab is not clickable");
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}
	
	@Test
	public void M11tc016_CheckErrorMessageAndCreateTemplateWithoutAnyFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		PartnershipPageBusinessLayer pp = new PartnershipPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(Org3CRMUser3EmailID, adminPassword);
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		WebElement ele;
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
			if (click(driver, np.getAddFolderTemplateButton(30), "add folder template button", action.BOOLEAN)) {
				if (click(driver, np.getCancelFolderTemplateButton(30), "cancel button", action.BOOLEAN)) {
					if (getText(driver, np.getCancelTextAddFolder(30), "cancel window text", action.BOOLEAN).trim().contains(NIMPageErrorMessage.addFolderCancelPopup)) {
						appLog.info("successfully verified cancel window popup");
					}
					else {
						appLog.error("cancel window popup could not be verified");
						sa.assertTrue(false, "cancel window popup could not be verified");
					}
					click(driver, np.getCrossIconCancelWindowAddFolder(30), "cross icon cancel window", action.BOOLEAN);
					if (sendKeys(driver,np.getFolderTemplateNameTextBox(30), folderTemplateName+"T1"+"U2", "folder temp name", action.BOOLEAN)) {
						
					}
					else {
						appLog.error("folder temp name text box is not visible");
						sa.assertTrue(false, "folder temp name text box is not visible");
					}
					if (sendKeys(driver, np.getFolderTemplateDescriptionTextBox(30), folderTemplateName+"Desc U2", "folder template name", action.BOOLEAN)) {
						
					}
					else {
						appLog.error("folder temp description textbox is not visible");
						sa.assertTrue(false, "folder temp description textbox is not visible");
					}
					if (click(driver,np.getCancelFolderTemplateButton(30) , "cnacel btn add folder", action.BOOLEAN)) {
						click(driver, np.getNoBtnCancelWindowAddFolder(30), "no btn", action.BOOLEAN);
						ThreadSleep(5000);
						ele=np.getFolderTemplateNameTextBox(30);
						if (getValueFromElementUsingJavaScript(driver, ele, "text box folder template name").contains(folderTemplateName+"T1"+"U2")) {
							appLog.info("after clicking cancel button still text is present in template name");
						}
						else {
							appLog.error("after clicking cancel btn text is not present in template name");
							sa.assertTrue(false, "after clicking cancel btn text is not present in template name");
						}

						ele=np.getFolderTemplateDescriptionTextBox(30);
						
						if ( getValueFromElementUsingJavaScript(driver, ele,"description text box").contains(folderTemplateName+"Desc U2")) {
							appLog.info("after clicking cancel button still text is present in template desc");
						}
						else {
							appLog.error("after clicking cancel btn text is not present in template desc");
							sa.assertTrue(false, "after clicking cancel btn text is not present in template desc");
						}
					}
					if (click(driver,np.getCancelFolderTemplateButton(30) , "cnacel btn add folder", action.BOOLEAN)) {
						if (click(driver, np.getYesBtnCancelWindowAddFolder(30), "cancel yes btn", action.BOOLEAN)) {

						}
						if (np.getNodatatodisplay(30).getText().trim().equals(NIMPageErrorMessage.noDataToDisplay)) {
							appLog.info("successfully verified no data to display");
						}
						else {
							appLog.error("no data to display is not present");
							sa.assertTrue(false, "no data to display is not present");
						}
					}
					if (click(driver, np.getAddFolderTemplateButton(30)," add folder template btn", action.BOOLEAN)) {
						if (click(driver, np.getFolderTemplateSaveButton(30), "save btn folder temp", action.BOOLEAN)) {
							if (getText(driver, np.getErrMessageBlankNameAddFolderTemp(30), "error message of blank name", action.BOOLEAN).trim().contains(NIMPageErrorMessage.pleaseEnterValue)) {
								appLog.info("successfully verified blank name error message");
							}
							else {
								appLog.error("blank name error message could not be verified");
								sa.assertTrue(false, "blank name error message could not be verified");
							}
							if (sendKeys(driver,np.getFolderTemplateNameTextBox(30), folderTemplateName+"T1"+"U2", "folder temp name", action.BOOLEAN)) {
								
							}
							else {
								appLog.error("folder temp name text box is not visible");
								sa.assertTrue(false, "folder temp name text box is not visible");
							}
							if (sendKeys(driver, np.getFolderTemplateDescriptionTextBox(30), folderTemplateName+"Desc U2", "folder template name", action.BOOLEAN)) {
								
							}
							else {
								appLog.error("folder temp description textbox is not visible");
								sa.assertTrue(false, "folder temp description textbox is not visible");
							}
							if (click(driver, np.getFolderTemplateSaveButton(30), "save button", action.BOOLEAN)) {
								if (np.getSaveTextQuesAddFolderTemp(30).getText().trim().contains(NIMPageErrorMessage.saveFolderTemp)) {
									appLog.info("save text is successfully verified");
								}
								else {
									appLog.error("save text is not correct on add folder template text");
									sa.assertTrue(false, "save text is not correct on add folder template text");
								}
								if (click(driver, np.getSaveNoBtnAddFolderTemplate(30), "save button", action.BOOLEAN)) {
									ele=np.getFolderTemplateNameTextBox(30);
									if (getValueFromElementUsingJavaScript(driver, ele, "folder temp name textbox").contains(folderTemplateName+"T1"+"U2")) {
										appLog.info("after clicking save no button still text is present in template name");
									}
									else {
										appLog.error("after clicking save no btn text is not present in template name");
										sa.assertTrue(false, "after clicking save no btn text is not present in template name");
									}
									ele=np.getFolderTemplateDescriptionTextBox(30);
									if (getValueFromElementUsingJavaScript(driver,ele, "folder temp desc textbox").contains(folderTemplateName+"Desc U2")) {
										appLog.info("after clicking save no button still text is present in template desc");
									}
									else {
										appLog.error("after clicking save no btn text is not present in template desc");
										sa.assertTrue(false, "after clicking save no btn text is not present in template desc");
									}
								}
								else {
									appLog.error("no button is not clickable");
									sa.assertTrue(false, "no button is not clickable");
								}
							}
							else {
								appLog.error("save button is not clickable");
								sa.assertTrue(false, "save button is not clickable");
							}
							if (click(driver, np.getFolderTemplateSaveButton(30), "save button", action.BOOLEAN)) {
								if (click(driver, np.getSaveCrossBtnAddFolderTemplate(30), "save cross btn", action.BOOLEAN)) {
									if (getValueFromElementUsingJavaScript(driver, np.getFolderTemplateNameTextBox(30), "folder temp name textbox").contains(folderTemplateName+"T1"+"U2")) {
										appLog.info("after clicking save cross button still text is present in template name");
									}
									else {
										appLog.error("after clicking save cross btn text is not present in template name");
										sa.assertTrue(false, "after clicking save cross btn text is not present in template name");
									}
									if (getValueFromElementUsingJavaScript(driver, np.getFolderTemplateDescriptionTextBox(30), "folder temp desc textbox").contains(folderTemplateName+"Desc U2")) {
										appLog.info("after clicking save cross button still text is present in template desc");
									}
									else {
										appLog.error("after clicking save cross btn text is not present in template desc");
										sa.assertTrue(false, "after clicking save cross btn text is not present in template desc");
									}
								}
								else {
									appLog.error("cross button is not clickable");
									sa.assertTrue(false, "cross button is not clickable");
								}
							}
							else {
								appLog.error("save button is not clickable");
								sa.assertTrue(false, "save button is not clickable");
							}
							if (click(driver, np.getFolderTemplateSaveButton(30), "save button", action.BOOLEAN)) {
								if (click(driver, np.getSaveYesBtnAddFolderTemplate(30), "save yes button", action.BOOLEAN)) {
									appLog.info("successfully created new template");
									if (np.getGoBackLink(30)!=null) {
										appLog.info("go back link is present in folder template detail page");
									}
									else {
										appLog.error("go back link is not present in folder template detail page");
										sa.assertTrue(false, "go back link is not present in folder template detail page");
									}
									if (np.getDeleteTempBtn(30)!=null) {
										appLog.info("successfully found delete template button");
									}
									else {
										appLog.error("delete template btn is visible");
										sa.assertTrue(false, "delete template btn is visible");
									}
									if (np.getEditIcon(30)!=null) {
										appLog.info("edit icon is successfully visible");
									}
									else {
										appLog.error("edit icon is not visible");
										sa.assertTrue(false, "edit icon is not visible");
									}
									if (getText(driver, FindElement(driver, "//input[@id='lblFolderName']/../../td[2]", "name of folder template", action.BOOLEAN, 30), "folder template name text", action.BOOLEAN).contains(folderTemplateName+"T1"+"U2")) {
										appLog.info("after clicking save cross button still text is present in template name");
									}
									else {
										appLog.error("after clicking save cross btn text is not present in template name");
										sa.assertTrue(false, "after clicking save cross btn text is not present in template name");
									}
									if (getValueFromElementUsingJavaScript(driver, np.getTextboxDescFolderTempPage(30), "folder temp desc textbox").contains(folderTemplateName+"Desc U2")) {
										appLog.info("after clicking save cross button still text is present in template desc");
									}
									else {
										appLog.error("after clicking save cross btn text is not present in template desc");
										sa.assertTrue(false, "after clicking save cross btn text is not present in template desc");
									}
									if (click(driver, np.getGoBackLink(30), "go back link", action.SCROLLANDBOOLEAN)) {
										if (np.verifyTemplatesPresentInFolderTemplates(folderTemplateName+"T1"+"U2", folderTemplateName+"Desc U2", Org3CRMUser3FirstName+" "+Org3CRMUser3LastName, date)) {
											appLog.info("successfully verified files on folder templates page");
										}
										else {
											appLog.error("could not verify files on folder templates page");
											sa.assertTrue(false, "could not verify files on folder templates page");
										}
									}
									
								}
								else {
									appLog.error("cannot create new template");
									sa.assertTrue(false, "cannot create new template");
								}
							}
							else {
								appLog.error("save button is not clickable");
								sa.assertTrue(false, "save button is not clickable");
							}
						}
						else {
							appLog.error("save button is not clickable");
							sa.assertTrue(false, "save button is not clickable");
						}
					}
				}
			}
			switchToDefaultContent(driver);
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}
	
	@Test
	public void M11tc017_CheckVariousValidationsAddFolderPopup() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		PartnershipPageBusinessLayer pp = new PartnershipPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(Org3CRMUser3EmailID, adminPassword);
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		WebElement ele;
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
			if (click(driver, np.getAddFolderTemplateButton(30), "add folder template button", action.BOOLEAN)) {
				if (sendKeys(driver,np.getFolderTemplateNameTextBox(30), folderTemplateName+"T1"+"U2", "folder temp name", action.BOOLEAN)) {
					
				}
				else {
					appLog.error("folder temp name text box is not visible");
					sa.assertTrue(false, "folder temp name text box is not visible");
				}
				if (sendKeys(driver, np.getFolderTemplateDescriptionTextBox(30), folderTemplateName+"Desc U2", "folder template name", action.BOOLEAN)) {
					
				}
				else {
					appLog.error("folder temp description textbox is not visible");
					sa.assertTrue(false, "folder temp description textbox is not visible");
				}
				if (click(driver, np.getFolderTemplateSaveButton(30), "save button", action.BOOLEAN)) {
					if (np.getErrMessageDuplicateTemp(30).getText().trim().contains(NIMPageErrorMessage.DuplicateTemp)) {
						appLog.info("successfully verified error message for duplicate temp name");
					}
					else {
						appLog.error("template name error message could not be verified");
						sa.assertTrue(false, "template name error message could not be verified");
					}
					if (np.getCrossIconErrMessageDuplicateTemp(30)!=null) {
						appLog.info("cross icon is successfully verified on duplicate temp popup");
					}
					else {
						appLog.error("cross icon could not be verified on duplicate temp popup");
						sa.assertTrue(false, "cross icon could not be verified on duplicate temp popup");
					}
					if (np.getCloseBtnErrMessageDuplicateTemp(30)!=null) {
						appLog.info("successfully verified close btn duplicate temp popup");
					}
					else {
						appLog.error("close btn is not visible on duplicate temp popup");
						sa.assertTrue(false, "close btn is not visible on duplicate temp popup");
					}
					if (click(driver, np.getCloseBtnErrMessageDuplicateTemp(30), "close btn", action.BOOLEAN)) {
						if (getValueFromElementUsingJavaScript(driver, np.getFolderTemplateNameTextBox(30), "folder temp name textbox").equalsIgnoreCase(folderTemplateName+"T1"+"U2")) {
							appLog.info("after clicking save cross button still text is present in template name");
						}
						else {
							appLog.error("after clicking save cross btn text is not present in template name");
							sa.assertTrue(false, "after clicking save cross btn text is not present in template name");
						}
						if (getValueFromElementUsingJavaScript(driver, np.getFolderTemplateDescriptionTextBox(30), "folder temp desc textbox").equalsIgnoreCase(folderTemplateName+"Desc U2")) {
							appLog.info("after clicking save cross button still text is present in template desc");
						}
						else {
							appLog.error("after clicking save cross btn text is not present in template desc");
							sa.assertTrue(false, "after clicking save cross btn text is not present in template desc");
						}
					}
					else {
						appLog.error("close button of err message is not clickable");
						sa.assertTrue(false, "close button of err message is not clickable");
					}
				}
				else {
					appLog.error("save button of folder temp is not clickable");
					sa.assertTrue(false, "save button of folder temp is not clickable");
				}
				//lower case
				if (sendKeys(driver,np.getFolderTemplateNameTextBox(30), (folderTemplateName+"T1"+"U2").toLowerCase(), "folder temp name", action.BOOLEAN)) {
					
				}
				else {
					appLog.error("folder temp name text box is not visible");
					sa.assertTrue(false, "folder temp name text box is not visible");
				}
				if (sendKeys(driver, np.getFolderTemplateDescriptionTextBox(30), (folderTemplateName+"Desc U2").toLowerCase(), "folder template name", action.BOOLEAN)) {
					
				}
				else {
					appLog.error("folder temp description textbox is not visible");
					sa.assertTrue(false, "folder temp description textbox is not visible");
				}
				if (click(driver, np.getFolderTemplateSaveButton(30), "save button", action.BOOLEAN)) {
					if (click(driver,np.getCrossIconErrMessageDuplicateTemp(30) , "cross icon", action.BOOLEAN)) {
						if (getValueFromElementUsingJavaScript(driver, np.getFolderTemplateNameTextBox(30), "folder temp name textbox").equalsIgnoreCase(folderTemplateName+"T1"+"U2")) {
							appLog.info("after clicking save cross button still text is present in template name");
						}
						else {
							appLog.error("after clicking save cross btn text is not present in template name");
							sa.assertTrue(false, "after clicking save cross btn text is not present in template name");
						}
						if (getValueFromElementUsingJavaScript(driver, np.getFolderTemplateDescriptionTextBox(30), "folder temp desc textbox").equalsIgnoreCase(folderTemplateName+"Desc U2")) {
							appLog.info("after clicking save cross button still text is present in template desc");
						}
						else {
							appLog.error("after clicking save cross btn text is not present in template desc");
							sa.assertTrue(false, "after clicking save cross btn text is not present in template desc");
						}
						click(driver, np.getParentAddFolderBtn(30), "add folder plus icon", action.BOOLEAN);
						if (click(driver, np.getFolderTypeRadioButton(FolderType.Shared, null, PageName.NavatarInvestorManager, 30), "shared folder radio btn", action.BOOLEAN)) {
							if (click(driver, np.getSaveButtonAddFolder(30), "save button add folder", action.BOOLEAN)) {
								if (np.getErrMessageAddFolder(30).getText().trim().contains(NIMPageErrorMessage.PleaseEnterFolderNameErrorMsg)) {
									appLog.info("successfully verified no folder name err message");
								}
								else {
									appLog.error("folder name error message could not be verified");
									sa.assertTrue(false, "folder name error message could not be verified");
								}
							}
							else {
								appLog.error("save button is not clickable");
								sa.assertTrue(false, "save button is not clickable");
							}
						}
						else {
							appLog.error("folder type radio button is not clickable");
							sa.assertTrue(false, "folder type radio button is not clickable");
						}
						
						if (click(driver, np.getFolderTypeRadioButton(FolderType.Common, null, PageName.NavatarInvestorManager, 30), "Common folder radio btn", action.BOOLEAN)) {
							if (click(driver, np.getSaveButtonAddFolder(30), "save button add folder", action.BOOLEAN)) {
								if (np.getErrMessageAddFolder(30).getText().trim().contains(NIMPageErrorMessage.PleaseEnterFolderNameErrorMsg)) {
									appLog.info("successfully verified no folder name err message");
								}
								else {
									appLog.error("folder name error message could not be verified");
									sa.assertTrue(false, "folder name error message could not be verified");
								}
							}
								else {
									appLog.error("save button is not clickable");
									sa.assertTrue(false, "save button is not clickable");
								}
							}
							else {
								appLog.error("folder type radio button is not clickable");
								sa.assertTrue(false, "folder type radio button is not clickable");
							}
							
						if (click(driver, np.getFolderTypeRadioButton(FolderType.Internal, null, PageName.NavatarInvestorManager, 30), "Internal folder radio btn", action.BOOLEAN)) {
							if (click(driver, np.getSaveButtonAddFolder(30), "save button add folder", action.BOOLEAN)) {
								if (np.getErrMessageAddFolder(30).getText().trim().contains(NIMPageErrorMessage.PleaseEnterFolderNameErrorMsg)) {
									appLog.info("successfully verified no folder name err message");
								}
								else {
									appLog.error("folder name error message could not be verified");
									sa.assertTrue(false, "folder name error message could not be verified");
								}
							}
							else {
								appLog.error("save button is not clickable");
								sa.assertTrue(false, "save button is not clickable");
							}
						}
						else {
							appLog.error("folder type radio button is not clickable");
							sa.assertTrue(false, "folder type radio button is not clickable");
						}
						String text;
						if (click(driver, np.getFolderTypeRadioButton(FolderType.Standard, null, PageName.NavatarInvestorManager, 30), "Standard folder radio btn", action.BOOLEAN)) {
							if (sendKeys(driver, np.getParentFolderNameTextBox(null, PageName.NavatarInvestorManager, 30),"Folder []{}/\\?|<>*:" , "folder name textbox", action.BOOLEAN)) {
								if (click(driver, np.getSaveButtonAddFolder(30), "save button add folder", action.BOOLEAN)) {
									text =np.getInvalidCharErrAddFolder(30).getText();
									if (text.trim().contains(NIMPageErrorMessage.invalidChars1) && text.trim().contains(NIMPageErrorMessage.invalidChars2)) {
										appLog.info("successfully verified invalid folder name err message");
								}
								else {
									appLog.error("folder name error message for invalid folder namecould not be verified");
									sa.assertTrue(false, "folder name error message for invalid folder name could not be verified");
								}
								}
							}
							else {
								appLog.error("parent folder name textbox is not visible");
								sa.assertTrue(false, "parent folder name textbox is not visible");
							}
						}
						else {
							appLog.error("folder type radio btn is not clickable");
							sa.assertTrue(false, "folder type radio btn is not clickable");
						}
						
						if (click(driver, np.getFolderTypeRadioButton(FolderType.Shared, null, PageName.NavatarInvestorManager, 30), "shared folder radio btn", action.BOOLEAN)) {
							if (sendKeys(driver, np.getParentFolderNameTextBox(null, PageName.NavatarInvestorManager, 30),"Folder []{}/\\?|<>*:" , "folder name textbox", action.BOOLEAN)) {
								if (click(driver, np.getSaveButtonAddFolder(30), "save button add folder", action.BOOLEAN)) {
									text =np.getInvalidCharErrAddFolder(30).getText();
									if (text.trim().contains(NIMPageErrorMessage.invalidChars1) && text.trim().contains(NIMPageErrorMessage.invalidChars2)) {
										appLog.info("successfully verified invalid folder name err message");
								}
								else {
									appLog.error("folder name error message for invalid folder namecould not be verified");
									sa.assertTrue(false, "folder name error message for invalid folder name could not be verified");
								}
								}
							}
							else {
								appLog.error("parent folder name textbox is not visible");
								sa.assertTrue(false, "parent folder name textbox is not visible");
							}
						}
						else {
							appLog.error("folder type radio btn is not clickable");
							sa.assertTrue(false, "folder type radio btn is not clickable");
						}
						if (click(driver, np.getFolderTypeRadioButton(FolderType.Common, null, PageName.NavatarInvestorManager, 30), "Common folder radio btn", action.BOOLEAN)) {
							if (sendKeys(driver, np.getParentFolderNameTextBox(null, PageName.NavatarInvestorManager, 30),"Folder []{}/\\?|<>*:" , "folder name textbox", action.BOOLEAN)) {
								if (click(driver, np.getSaveButtonAddFolder(30), "save button add folder", action.BOOLEAN)) {
									text =np.getInvalidCharErrAddFolder(30).getText();
									if (text.trim().contains(NIMPageErrorMessage.invalidChars1) && text.trim().contains(NIMPageErrorMessage.invalidChars2)) {
									appLog.info("successfully verified invalid folder name err message");
								}
								else {
									appLog.error("folder name error message for invalid folder namecould not be verified");
									sa.assertTrue(false, "folder name error message for invalid folder name could not be verified");
								}
								}
							}
							else {
								appLog.error("parent folder name textbox is not visible");
								sa.assertTrue(false, "parent folder name textbox is not visible");
							}
						}
						else {
							appLog.error("folder type radio btn is not clickable");
							sa.assertTrue(false, "folder type radio btn is not clickable");
						}
						if (click(driver, np.getFolderTypeRadioButton(FolderType.Internal, null, PageName.NavatarInvestorManager, 30), "Internal folder radio btn", action.BOOLEAN)) {
							if (sendKeys(driver, np.getParentFolderNameTextBox(null, PageName.NavatarInvestorManager, 30),"Folder []{}/\\?|<>*:" , "folder name textbox", action.BOOLEAN)) {
								if (click(driver, np.getSaveButtonAddFolder(30), "save button add folder", action.BOOLEAN)) {
									text =np.getInvalidCharErrAddFolder(30).getText();
									if (text.trim().contains(NIMPageErrorMessage.invalidChars1) && text.trim().contains(NIMPageErrorMessage.invalidChars2)) {
										appLog.info("successfully verified invalid folder name err message");
								}
								else {
									appLog.error("folder name error message for invalid folder namecould not be verified");
									sa.assertTrue(false, "folder name error message for invalid folder name could not be verified");
								}
								}
							}
							else {
								appLog.error("parent folder name textbox is not visible");
								sa.assertTrue(false, "parent folder name textbox is not visible");
							}
						}
						else {
							appLog.error("folder type radio btn is not clickable");
							sa.assertTrue(false, "folder type radio btn is not clickable");
						}
						if (click(driver, np.getFolderTypeRadioButton(FolderType.Shared, null, PageName.NavatarInvestorManager, 30), "shared folder radio btn", action.BOOLEAN)) {
							if (sendKeys(driver, np.getParentFolderNameTextBox(null, PageName.NavatarInvestorManager, 30),"Folder (Shared)" , "folder name textbox", action.BOOLEAN)) {
								if (click(driver, np.getSaveButtonAddFolder(30), "save button add folder", action.BOOLEAN)) {
									if (np.getErrRenameFolderNameForGlobal(30).getText().trim().contains(NIMPageErrorMessage.cannotAddFolderNames)) {
									appLog.info("successfully verified invalid folder name err message");
								}
								else {
									appLog.error("folder name error message for invalid folder namecould not be verified");
									sa.assertTrue(false, "folder name error message for invalid folder name could not be verified");
								}
								}
							}
							else {
								appLog.error("parent folder name textbox is not visible");
								sa.assertTrue(false, "parent folder name textbox is not visible");
							}
						}
						else {
							appLog.error("folder type radio btn is not clickable");
							sa.assertTrue(false, "folder type radio btn is not clickable");
						}
						
						if (click(driver, np.getFolderTypeRadioButton(FolderType.Common, null, PageName.NavatarInvestorManager, 30), "Common folder radio btn", action.BOOLEAN)) {
							if (sendKeys(driver, np.getParentFolderNameTextBox(null, PageName.NavatarInvestorManager, 30),"Folder (Common)" , "folder name textbox", action.BOOLEAN)) {
								if (click(driver, np.getSaveButtonAddFolder(30), "save button add folder", action.BOOLEAN)) {
									if (np.getErrRenameFolderNameForGlobal(30).getText().trim().contains(NIMPageErrorMessage.cannotAddFolderNames)) {
									appLog.info("successfully verified invalid folder name err message");
								}
								else {
									appLog.error("folder name error message for invalid folder namecould not be verified");
									sa.assertTrue(false, "folder name error message for invalid folder name could not be verified");
								}
								}
							}
							else {
								appLog.error("parent folder name textbox is not visible");
								sa.assertTrue(false, "parent folder name textbox is not visible");
							}
						}
						else {
							appLog.error("folder type radio btn is not clickable");
							sa.assertTrue(false, "folder type radio btn is not clickable");
						}
						if (click(driver, np.getFolderTypeRadioButton(FolderType.Internal, null, PageName.NavatarInvestorManager, 30), "Internal folder radio btn", action.BOOLEAN)) {
							if (sendKeys(driver, np.getParentFolderNameTextBox(null, PageName.NavatarInvestorManager, 30),"Folder (Internal)" , "folder name textbox", action.BOOLEAN)) {
								if (click(driver, np.getSaveButtonAddFolder(30), "save button add folder", action.BOOLEAN)) {
									if (np.getErrRenameFolderNameForGlobal(30).getText().trim().contains(NIMPageErrorMessage.cannotAddFolderNames)) {
									appLog.info("successfully verified invalid folder name err message");
								}
								else {
									appLog.error("folder name error message for invalid folder namecould not be verified");
									sa.assertTrue(false, "folder name error message for invalid folder name could not be verified");
								}
								}
							}
							else {
								appLog.error("parent folder name textbox is not visible");
								sa.assertTrue(false, "parent folder name textbox is not visible");
							}
						}
						else {
							appLog.error("folder type radio btn is not clickable");
							sa.assertTrue(false, "folder type radio btn is not clickable");
						}
					}
					else {
						appLog.error("cross icon is not clickable");
						sa.assertTrue(false, "cross icon is not clickable");
					}
				}
				else {
					appLog.error("folder template save btn is not clickable");
					sa.assertTrue(false, "folder template save btn is not clickable");
				}
				
			}
			else {
				appLog.error(" add folder template btn is not clickable");
				sa.assertTrue(false, " add folder template btn is not clickable");
			}
			switchToDefaultContent(driver);
		}
		else {
			appLog.error("nim tab is not clickable");
			sa.assertTrue(false, "nim tab is not clickable");
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}
	
	@Test
	public void M11tc018_CreationFolderTemplates() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		PartnershipPageBusinessLayer pp = new PartnershipPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(Org3CRMUser3EmailID, adminPassword);
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
			if (click(driver, np.getAddFolderTemplateButton(30), "add folder template button", action.BOOLEAN)) {
				if (sendKeys(driver,np.getFolderTemplateNameTextBox(30), folderTemplateName+"T2"+"U2", "folder temp name", action.BOOLEAN)) {
					
				}
				else {
					appLog.error("folder temp name text box is not visible");
					sa.assertTrue(false, "folder temp name text box is not visible");
				}
				if (sendKeys(driver, np.getFolderTemplateDescriptionTextBox(30), folderTemplateName+"Desc U2", "folder template name", action.BOOLEAN)) {
					
				}
				else {
					appLog.error("folder temp description textbox is not visible");
					sa.assertTrue(false, "folder temp description textbox is not visible");
				}
				if (bp.createFolderStructureFromExcel("FolderTemp", 30)) {
					if (click(driver, np.getFolderTemplateSaveButton(30), "save button", action.BOOLEAN)) {
						if (click(driver, np.getSaveYesBtnAddFolderTemplate(30), "yes button", action.BOOLEAN)) {

							if (np.getGoBackLink(30)!=null) {
								appLog.info("go back link is present in folder template detail page");
							}
							else {
								appLog.error("go back link is not present in folder template detail page");
								sa.assertTrue(false, "go back link is not present in folder template detail page");
							}
							if (np.getDeleteTempBtn(30)!=null) {
								appLog.info("successfully found delete template button");
							}
							else {
								appLog.error("delete template btn is visible");
								sa.assertTrue(false, "delete template btn is visible");
							}
							if (np.getEditIcon(30)!=null) {
								appLog.info("edit icon is successfully visible");
							}
							else {
								appLog.error("edit icon is not visible");
								sa.assertTrue(false, "edit icon is not visible");
							}
							if (getText(driver, FindElement(driver, "//input[@id='lblFolderName']/../../td[2]", "name of folder template", action.BOOLEAN, 30), "folder template name text", action.BOOLEAN).contains(folderTemplateName+"T2"+"U2")) {
								appLog.info("after clicking save cross button still text is present in template name");
							}
							else {
								appLog.error("after clicking save cross btn text is not present in template name");
								sa.assertTrue(false, "after clicking save cross btn text is not present in template name");
							}if (getValueFromElementUsingJavaScript(driver, np.getTextboxDescFolderTempPage(30), "folder temp desc textbox").contains(folderTemplateName+"Desc U2")) {
								appLog.info("after clicking save cross button still text is present in template desc");
							}
							else {
								appLog.error("after clicking save cross btn text is not present in template desc");
								sa.assertTrue(false, "after clicking save cross btn text is not present in template desc");
							}
							
							
							if (click(driver, np.getGoBackLink(30), "go back link", action.BOOLEAN)) {
								if (np.verifyfolderTemplateGrid(folderTemplateName+"T2"+"U2", folderTemplateName+"Desc U2", Org3CRMUser3FirstName+" "+Org3CRMUser3LastName, date)) {
									appLog.info("successfully verified "+folderTemplateName+"T2"+"U2");
								}
								else {
									appLog.error("could not verify template on template grid");
									sa.assertTrue(false, "could not verify template on template grid");
								}
								if (np.verifyTemplatesPresentInFolderTemplates(folderTemplateName+"T1"+"U2", folderTemplateName+"Desc U2", Org3CRMUser3FirstName+" "+Org3CRMUser3LastName, date)) {
									appLog.info("successfully verified "+folderTemplateName+"T1"+"U2");
								}
								else {
									appLog.error("could not verify template on template grid");
									sa.assertTrue(false, "could not verify template on template grid");
								}
							}
							else {
								appLog.error("go back link is not clickable");
								sa.assertTrue(false, "go back link is not clickable");
							}
							
							
						}
						else {
							appLog.error("yes button is not clickable");
							sa.assertTrue(false, "yes button is not clickable");
						}
					}
					else {
						appLog.error("save button is not clickable");
						sa.assertTrue(false, "save button is not clickable");
					}
				}
				else {
					appLog.error("folder structure could not be created");
					sa.assertTrue(false, "folder structure could not be created");
				}
			}
			else {
				appLog.error("add folder template button is not clickable");
				sa.assertTrue(false, "add folder template button is not clickable");
			}
			switchToDefaultContent(driver);
		}
		else {
			appLog.error("nim tab is not clickable");
			sa.assertTrue(false, "nim tab is not clickable");
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}
	
	@Test
	public void M11tc019_EditFolderTemplateAddFolders() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		PartnershipPageBusinessLayer pp = new PartnershipPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String intPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		
		lp.CRMLogin(Org3CRMUser3EmailID, adminPassword);
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
			if (np.clickOnSideMenusTab(sideMenu.FolderTemplates)) {
				if (click(driver, np.getfoldertemplatelink(folderTemplateName+"T2"+"U2",30), "folder template link", action.BOOLEAN)) {
					if (np.getGoBackLink(30)!=null) {
						appLog.info("go back link is present in folder template detail page");
					}
					else {
						appLog.error("go back link is not present in folder template detail page");
						sa.assertTrue(false, "go back link is not present in folder template detail page");
					}
					if (np.getDeleteTempBtn(30)!=null) {
						appLog.info("successfully found delete template button");
					}
					else {
						appLog.error("delete template btn is visible");
						sa.assertTrue(false, "delete template btn is visible");
					}
					if (np.getEditIcon(30)!=null) {
						appLog.info("edit icon is successfully visible");
					}
					else {
						appLog.error("edit icon is not visible");
						sa.assertTrue(false, "edit icon is not visible");
					}
					if (getText(driver, FindElement(driver, "//input[@id='lblFolderName']/../../td[2]", "name of folder template", action.BOOLEAN, 30), "folder template name text", action.BOOLEAN).contains(folderTemplateName+"T2"+"U2")) {
						appLog.info("after clicking save cross button still text is present in template name");
					}
					else {
						appLog.error("after clicking save cross btn text is not present in template name");
						sa.assertTrue(false, "after clicking save cross btn text is not present in template name");
					}if (getValueFromElementUsingJavaScript(driver, np.getTextboxDescFolderTempPage(30), "folder temp desc textbox").contains(folderTemplateName+"Desc U2")) {
						appLog.info("after clicking save cross button still text is present in template desc");
					}
					else {
						appLog.error("after clicking save cross btn text is not present in template desc");
						sa.assertTrue(false, "after clicking save cross btn text is not present in template desc");
					}
					if (click(driver, np.getEditIcon(30), "edit icon", action.BOOLEAN)) {
						if (np.getFolderTemplateSaveButton(30)!=null) {
							appLog.info("save button is successfully displayed");
						}
						else {
							appLog.error("save button is not visible on folder templates page");
							sa.assertTrue(false, "save button is not visible on folder templates page");
						}
						if (np.getFolderTempAddCancelBtn(30)!=null) {
							appLog.info("folder template cancel button is successfully displayed");
							
						}
						else {
							appLog.error("folder template cancel button is not visible");
							sa.assertTrue(false, "folder template cancel button is not visible");
						}
						if (getValueFromElementUsingJavaScript(driver, np.getFolderTemplateNameTextBox(30), "folder temp name textbox").equalsIgnoreCase(folderTemplateName+"T2"+"U2")) {
							appLog.info("after clicking save cross button still text is present in template name");
						}
						else {
							appLog.error("after clicking save cross btn text is not present in template name");
							sa.assertTrue(false, "after clicking save cross btn text is not present in template name");
						}
						if (getValueFromElementUsingJavaScript(driver, np.getFolderTemplateDescriptionTextBox(30), "folder temp desc textbox").equalsIgnoreCase(folderTemplateName+"Desc U2")) {
							appLog.info("after clicking save cross button still text is present in template desc");
						}
						else {
							appLog.error("after clicking save cross btn text is not present in template desc");
							sa.assertTrue(false, "after clicking save cross btn text is not present in template desc");
						}
						if (sendKeys(driver,np.getFolderTemplateNameTextBox(30), folderTemplateName+"T2"+"U2UP", "folder temp name", action.BOOLEAN)) {
							
						}
						else {
							appLog.error("folder temp name text box is not visible");
							sa.assertTrue(false, "folder temp name text box is not visible");
						}
						if (sendKeys(driver, np.getFolderTemplateDescriptionTextBox(30), folderTemplateName+"Desc U2UP", "folder template name", action.BOOLEAN)) {
							
						}
						else {
							appLog.error("folder temp description textbox is not visible");
							sa.assertTrue(false, "folder temp description textbox is not visible");
						}
						if (click(driver, np.getParentAddFolderBtn(30), "+ icon add folders", action.BOOLEAN)) {
							if (click(driver, np.getFolderTypeRadioButton(FolderType.Common, null, PageName.NavatarInvestorManager, 30), "Common folder radio btn", action.BOOLEAN)) {
								if (sendKeys(driver, np.getParentFolderNameTextBox(null, PageName.NavatarInvestorManager, 30),cmnPath.split("\\(")[0] , "folder name textbox", action.BOOLEAN)) {
									if (click(driver, np.getSaveButtonAddFolder(30), "save button add folder", action.BOOLEAN)) {
										if (np.getErrMessageSecondCommonOrInternal(FolderType.Common,30).getText().trim().contains(NIMPageErrorMessage.secondCommonFolder)) {
											appLog.info("successfully verified second attempt of adding common folder");
										}
										else {
											appLog.error("could not verify 2nd attempt of adding common folder");
											sa.assertTrue(false, "could not verify 2nd attempt of adding common folder");
										}
										if (click(driver, np.getCloseBtnErrMessageSecondCommon(30), "close btn", action.BOOLEAN)) {

										}
										else {
											appLog.error("close btn is not clickable");
											sa.assertTrue(false, "close btn is not clickable");
										}
									}
									else {
										appLog.error("save btn is not clickable");
										sa.assertTrue(false, "save btn is not clickable");
									}
								}
								else {
									appLog.error("parent folder name text box is not visible");
									sa.assertTrue(false, "parent folder name text box is not visible");
								}
							}
							else {
								appLog.error("folder type radio btn is not visible");
								sa.assertTrue(false, "folder type radio btn is not visible");
							}
						}
						else {
							appLog.error("add parent folder btn is not clickable");
							sa.assertTrue(false, "add parent folder btn is not clickable");
						}
						
						
						if (click(driver, np.getParentAddFolderBtn(30), "+ icon add folders", action.BOOLEAN)) {
							if (click(driver, np.getFolderTypeRadioButton(FolderType.Internal, null, PageName.NavatarInvestorManager, 30), "Internal folder radio btn", action.BOOLEAN)) {
								if (sendKeys(driver, np.getParentFolderNameTextBox(null, PageName.NavatarInvestorManager, 30),intPath.split("\\(")[0] , "folder name textbox", action.BOOLEAN)) {
									if (click(driver, np.getSaveButtonAddFolder(30), "save button add folder", action.BOOLEAN)) {
										if (np.getErrMessageSecondCommonOrInternal(FolderType.Internal,30).getText().trim().contains(NIMPageErrorMessage.secondInternalFolder)) {
											appLog.info("successfully verified second attempt of adding common folder");
										}
										else {
											appLog.error("could not verify 2nd attempt of adding common folder");
											sa.assertTrue(false, "could not verify 2nd attempt of adding common folder");
										}
										if (click(driver, np.getCloseBtnErrMessageSecondCommon(30), "close btn", action.BOOLEAN)) {

										}
										else {
											appLog.error("close btn is not clickable");
											sa.assertTrue(false, "close btn is not clickable");
										}
									}
									else {
										appLog.error("save btn is not clickable");
										sa.assertTrue(false, "save btn is not clickable");
									}
								}
								else {
									appLog.error("parent folder name text box is not visible");
									sa.assertTrue(false, "parent folder name text box is not visible");
								}
							}
							else {
								appLog.error("folder type radio btn is not visible");
								sa.assertTrue(false, "folder type radio btn is not visible");
							}
						}
						else {
							appLog.error("add parent folder btn is not clickable");
							sa.assertTrue(false, "add parent folder btn is not clickable");
						}
						if (click(driver, np.getParentAddFolderBtn(30), "+ icon add folders", action.BOOLEAN)) {
							if (click(driver, np.getFolderTypeRadioButton(FolderType.Shared, null, PageName.NavatarInvestorManager, 30), "shared folder radio btn", action.BOOLEAN)) {
								if (sendKeys(driver, np.getParentFolderNameTextBox(null, PageName.NavatarInvestorManager, 30),shdPath.split("\\(")[0]+"New" , "folder name textbox", action.BOOLEAN)) {
									if (click(driver, np.getSaveButtonAddFolder(30), "save button add folder", action.BOOLEAN)) {
										appLog.info("successfully created shared new folder");
									}
									else {
										appLog.error("save btn is not clickable");
										sa.assertTrue(false, "save btn is not clickable");
									}
								}
								else {
									appLog.error("parent folder name text box is not visible");
									sa.assertTrue(false, "parent folder name text box is not visible");
								}
							}
							else {
								appLog.error("folder type radio btn is not visible");
								sa.assertTrue(false, "folder type radio btn is not visible");
							}
						}
						else {
							appLog.error("add parent folder btn is not clickable");
							sa.assertTrue(false, "add parent folder btn is not clickable");
						}
						
						
						if (click(driver, np.getParentAddFolderBtn(30), "+ icon add folders", action.BOOLEAN)) {
							if (click(driver, np.getFolderTypeRadioButton(FolderType.Standard, null, PageName.NavatarInvestorManager, 30), "standard folder radio btn", action.BOOLEAN)) {
								if (sendKeys(driver, np.getParentFolderNameTextBox(null, PageName.NavatarInvestorManager, 30),stdPath+"New" , "folder name textbox", action.BOOLEAN)) {
									if (click(driver, np.getSaveButtonAddFolder(30), "save button add folder", action.BOOLEAN)) {
										appLog.info("successfully created std new folder");
									}
									else {
										appLog.error("save btn is not clickable");
										sa.assertTrue(false, "save btn is not clickable");
									}
									
								}
								else {
									appLog.error("parent folder name text box is not visible");
									sa.assertTrue(false, "parent folder name text box is not visible");
								}
							}
							else {
								appLog.error("folder type radio btn is not visible");
								sa.assertTrue(false, "folder type radio btn is not visible");
							}
						}
						else {
							appLog.error("add parent folder btn is not clickable");
							sa.assertTrue(false, "add parent folder btn is not clickable");
						}
						
						if (click(driver, np.getParentAddFolderBtn(30), "+ icon add folders", action.BOOLEAN)) {
							if (click(driver, np.getFolderTypeRadioButton(FolderType.Standard, null, PageName.NavatarInvestorManager, 30), "standard folder radio btn", action.BOOLEAN)) {
								if (sendKeys(driver, np.getParentFolderNameTextBox(null, PageName.NavatarInvestorManager, 30),stdPath+"New" , "folder name textbox", action.BOOLEAN)) {
									if (click(driver, np.getSaveButtonAddFolder(30), "save button add folder", action.BOOLEAN)) {
										if (np.getFolderNameAlreadyExistsText(30).getText().trim().contains(NIMPageErrorMessage.FolderAlreadyExistErrormsg)) {
											appLog.info("successfully verified duplicate folder message");
										}
										else {
											appLog.error("could not verify duplciate folder message");
											sa.assertTrue(false, "could not verify duplciate folder message");
										}
										if (click(driver, np.getFolderNameAlreadyExistsCross(30), "cross icon folder name already exists", action.BOOLEAN)) {

										}
										else {
											appLog.error("close btn is not clickable");
											sa.assertTrue(false, "close btn is not clickable");
										}
									}
									else {
										appLog.error("save btn is not clickable");
										sa.assertTrue(false, "save btn is not clickable");
									}
								}
								else {
									appLog.error("parent folder name text box is not visible");
									sa.assertTrue(false, "parent folder name text box is not visible");
								}
							}
							else {
								appLog.error("folder type radio btn is not visible");
								sa.assertTrue(false, "folder type radio btn is not visible");
							}
						}
						else {
							appLog.error("add parent folder btn is not clickable");
							sa.assertTrue(false, "add parent folder btn is not clickable");
						}
						
						if (click(driver, np.getParentAddFolderBtn(30), "+ icon add folders", action.BOOLEAN)) {
							if (click(driver, np.getFolderTypeRadioButton(FolderType.Shared, null, PageName.NavatarInvestorManager, 30), "shared folder radio btn", action.BOOLEAN)) {
								if (sendKeys(driver, np.getParentFolderNameTextBox(null, PageName.NavatarInvestorManager, 30),shdPath.split("\\(")[0]+"New" , "folder name textbox", action.BOOLEAN)) {
									if (click(driver, np.getSaveButtonAddFolder(30), "save button add folder", action.BOOLEAN)) {
										if (np.getFolderNameAlreadyExistsText(30).getText().trim().contains(NIMPageErrorMessage.FolderAlreadyExistErrormsg)) {
											appLog.info("successfully verified duplicate folder message");
										}
										else {
											appLog.error("could not verify duplciate folder message");
											sa.assertTrue(false, "could not verify duplciate folder message");
										}
										if (click(driver, np.getFolderNameAlreadyExistsCross(30), "close btn", action.BOOLEAN)) {
											if (click(driver, np.getFolderTemplateSaveButton(30), "save btn folder template", action.BOOLEAN)) {
												if (click(driver, np.getSaveYesBtnAddFolderTemplate(30), "save btn folder template", action.BOOLEAN)) {
													appLog.info("successfully saved folder template");
												}
												else {
													appLog.error("could not save folder template");
													sa.assertTrue(false, "could not save folder template");
												}
												if (getText(driver, FindElement(driver, "//input[@id='lblFolderName']/../../td[2]", "name of folder template", action.BOOLEAN, 30), "folder template name text", action.BOOLEAN).contains(folderTemplateName+"T2"+"U2UP")) {
													appLog.info("after clicking save cross button still text is present in template name");
												}
												else {
													appLog.error("after clicking save cross btn text is not present in template name");
													sa.assertTrue(false, "after clicking save cross btn text is not present in template name");
												}
												if (click(driver, np.getGoBackLink(30), "go back link", action.BOOLEAN)) {
													if (np.verifyTemplatesPresentInFolderTemplates(folderTemplateName+"T2"+"U2UP", folderTemplateName+"Desc U2UP", Org3CRMUser3FirstName+" "+Org3CRMUser3LastName, date)) {
														appLog.info("successfully verified folder templates");
													}
													else {
														appLog.error("could not verify folder template on ui");
														sa.assertTrue(false, "could not verify folder template on ui");
														
													}
												}
												else {
													appLog.error("go back link is not clickable");
													sa.assertTrue(false, "go back link is not clickable");
												}
											}
										}
										else {
											appLog.error("close btn is not clickable");
											sa.assertTrue(false, "close btn is not clickable");
										}
										
									}
									else {
										appLog.error("save btn is not clickable");
										sa.assertTrue(false, "save btn is not clickable");
									}
								}
								else {
									appLog.error("parent folder name text box is not visible");
									sa.assertTrue(false, "parent folder name text box is not visible");
								}
							}
							else {
								appLog.error("folder type radio btn is not visible");
								sa.assertTrue(false, "folder type radio btn is not visible");
							}
						}
						else {
							appLog.error("add parent folder btn is not clickable");
							sa.assertTrue(false, "add parent folder btn is not clickable");
						}
					}
					else {
						appLog.error("edit icon is not clickable");
						sa.assertTrue(false, "edit icon is not clickable");
					}
				}
				else {
					appLog.error("folder template link is not clickable");
					sa.assertTrue(false, "folder template link is not clickable");
				}
			}
			switchToDefaultContent(driver);
		}
		lp.CRMlogout(environment, mode);
	sa.assertAll();
	}
	
	@Test
	public void M11tc020_EditFolderTemplateAddSubFolders() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		PartnershipPageBusinessLayer pp = new PartnershipPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		String cmnPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String intPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath);
		String shdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String stdPath = ExcelUtils.readData("FilePath",excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		lp.CRMLogin(Org3CRMUser3EmailID, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, np.getFrame( PageName.NavatarInvestorManager, 30));
			if (click(driver, np.getfoldertemplatelink(folderTemplateName+"T2"+"U2UP", 30), "template link", action.BOOLEAN)) {
				if (click(driver, np.getEditIcon(30), "edit icon", action.BOOLEAN)) {
					String id = fp.getCreatedFolderId(cmnPath, PageName.NavatarInvestorManager, 30);
					if (np.clickOnAddFolderButton(id)) {
						if (np.getAddSubFolderText(30)!=null) {
							appLog.info("add a folder text is successfully displayed");
						}
						else {
							appLog.error("add a folder text is not visible");
							sa.assertTrue(false, "add a folder text is not visible");
						}
						if (np.getFolderNameInSubFolderWindow(30).getText().trim().contains(cmnPath)) {
							appLog.info("successfully found common folder name");
						}
						else {
							appLog.error("could not find common folder name on add sub folder window");
							sa.assertTrue(false, "could not find common folder name on add sub folder window");
						}
						if (np.getSaveButtonAddSubFolder(30)!=null) {
							appLog.info("save button is successfully verified");
						}
						else {
							appLog.error("save button is not visible on add sub folder window");
							sa.assertTrue(false, "save button is not visible on add sub folder window");
						}
						if (np.getCancelButtonAddSubFolder(30)!=null) {
							appLog.info("cancel button is successfully verified");
						}
						else {
							appLog.error("cancel button is not visible on add sub folder window");
							sa.assertTrue(false, "cancel button is not visible on add sub folder window");
						}
						if (np.getAddSubFolderTextBox(30).getText().trim().equals("")) {
							appLog.info("successfully verified empty textbox of sub folder");
						}
						else {
							appLog.error("could not verify empty textbox of sub folder");
							sa.assertTrue(false, "could not verify empty textbox of sub folder");
						}
						if (sendKeys(driver, np.getAddSubFolderTextBox(30),cmnPath.split("\\(")[0] + "Sub Folder 2" , "sub folder textbox", action.BOOLEAN)) {

						}
						else {
							appLog.error("add sub folder textbox is not visible");
							sa.assertTrue(false, "add sub folder textbox is not visible");
						}
						if (click(driver, np.getCancelButtonAddSubFolder(30), "cancel btn add sub folder", action.BOOLEAN)) {

							if (fp.getCreatedFolderId(cmnPath+"/"+ cmnPath.split("\\(")[0]+ "Sub Folder 2", PageName.NavatarInvestorManager, 30) == null) {
								appLog.info("after clicking on cancel btn cmn sub folder is not found successfully");
							}
							else {
								appLog.error("after clicking on cancel btn cmn sub folder is still created");
								sa.assertTrue(false, "after clicking on cancel btn cmn sub folder is still created");
							}
						}

						else {
							appLog.error("cancel button on add sub folder window is not clickable");
							sa.assertTrue(false, "cancel button on add sub folder window is not clickable");
						}

					}
					else {
						appLog.error(id + " could not be found of any folder");
						sa.assertTrue(false, id + " could not be found of any folder");
					}
					if (np.clickOnAddFolderButton(id)) {
						if (click(driver, np.getCrossIconAddSubFolder(30), "cross icon add sub folder", action.BOOLEAN)) {

						}
						else {
							appLog.error("cross icon add sub folder window is not clickable");
							sa.assertTrue(false, "cross icon add sub folder window is not clickable");
						}
					}
					else {
						appLog.error(id + " could not be found linked to any folder");
						sa.assertTrue(false, id + " could not be found linked to any folder");
					}
					if (np.clickOnAddFolderButton(id)) {
						if (sendKeys(driver, np.getAddSubFolderTextBox(30),cmnPath.split("\\(")[0] + "Sub Folder 2" , "sub folder textbox", action.BOOLEAN)) {
							if (click(driver, np.getSaveButtonAddSubFolder(30), "save button add sub folder", action.BOOLEAN)) {

							}
							else {
								appLog.error("save button of add sub folder window is not clickable");
								sa.assertTrue(false, "save button of add sub folder window is not clickable");
							}
						}
						else {
							appLog.error("sub folder textbox is not visible");
							sa.assertTrue(false, "sub folder textbox is not visible");
						}
					}
					else {
						appLog.error(id + " could not be found linked to any folder");
						sa.assertTrue(false, id + " could not be found linked to any folder");
					}
					id = fp.getCreatedFolderId(intPath, PageName.NavatarInvestorManager, 30);
					if (np.clickOnAddFolderButton(id)) {
						if (np.getAddSubFolderText(30)!=null) {
							appLog.info("add a folder text is successfully displayed");
						}
						else {
							appLog.error("add a folder text is not visible");
							sa.assertTrue(false, "add a folder text is not visible");
						}
						if (np.getFolderNameInSubFolderWindow(30).getText().trim().contains(intPath)) {
							appLog.info("successfully found common folder name");
						}
						else {
							appLog.error("could not find common folder name on add sub folder window");
							sa.assertTrue(false, "could not find common folder name on add sub folder window");
						}
						if (np.getSaveButtonAddSubFolder(30)!=null) {
							appLog.info("save button is successfully verified");
						}
						else {
							appLog.error("save button is not visible on add sub folder window");
							sa.assertTrue(false, "save button is not visible on add sub folder window");
						}
						if (np.getCancelButtonAddSubFolder(30)!=null) {
							appLog.info("cancel button is successfully verified");
						}
						else {
							appLog.error("cancel button is not visible on add sub folder window");
							sa.assertTrue(false, "cancel button is not visible on add sub folder window");
						}
						if (sendKeys(driver, np.getAddSubFolderTextBox(30),intPath.split("\\(")[0] + "Sub Folder 2" , "sub folder textbox", action.BOOLEAN)) {
							if (click(driver, np.getSaveButtonAddSubFolder(30), "save button add sub folder", action.BOOLEAN)) {

							}
							else {
								appLog.error("save button of add sub folder window is not clickable");
								sa.assertTrue(false, "save button of add sub folder window is not clickable");
							}
						}
						else {
							appLog.error("sub folder textbox is not visible");
							sa.assertTrue(false, "sub folder textbox is not visible");
						}
					}
					else {
						appLog.error(id + " could not be found linked to any folder");
						sa.assertTrue(false, id + " could not be found linked to any folder");
					}
					//verify presence of internal folder
					id = fp.getCreatedFolderId(intPath+"/"+intPath.split("\\(")[0]+ "Sub Folder 2", PageName.NavatarInvestorManager, 30);
					if (id!=null) {
						appLog.info("successfully found internal sub folder in folder structure");
					}
					else {
						appLog.error("could not find internal sub folder in folder structure");
						sa.assertTrue(false, "could not find internal sub folder in folder structure");
					}
					id = fp.getCreatedFolderId(shdPath, PageName.NavatarInvestorManager, 30);
					if (np.clickOnAddFolderButton(id)) {
						if (np.getAddSubFolderText(30)!=null) {
							appLog.info("add a folder text is successfully displayed");
						}
						else {
							appLog.error("add a folder text is not visible");
							sa.assertTrue(false, "add a folder text is not visible");
						}
						if (np.getFolderNameInSubFolderWindow(30).getText().trim().contains(shdPath)) {
							appLog.info("successfully found common folder name");
						}
						else {
							appLog.error("could not find common folder name on add sub folder window");
							sa.assertTrue(false, "could not find common folder name on add sub folder window");
						}
						if (sendKeys(driver, np.getAddSubFolderTextBox(30),shdPath.split("\\(")[0] + "Sub Folder 2" , "sub folder textbox", action.BOOLEAN)) {
							if (click(driver, np.getSaveButtonAddSubFolder(30), "save button add sub folder", action.BOOLEAN)) {

							}
							else {
								appLog.error("save button of add sub folder window is not clickable");
								sa.assertTrue(false, "save button of add sub folder window is not clickable");
							}
						}
						else {
							appLog.error("sub folder textbox is not visible");
							sa.assertTrue(false, "sub folder textbox is not visible");
						}
					}
					else {
						appLog.error(id + " could not be found linked to any folder");
						sa.assertTrue(false, id + " could not be found linked to any folder");
					}
					//verify shared folder presence
					id = fp.getCreatedFolderId(shdPath+"/"+shdPath.split("\\(")[0]+ "Sub Folder 2", PageName.NavatarInvestorManager,30);
					if (id!=null) {
						appLog.info("successfully found shared sub folder in folder structure");
					}
					else {
						appLog.error("could not find shared sub folder under shared parent folder");
						sa.assertTrue(false, "could not find shared sub folder under shared parent folder");
					}
					id = fp.getCreatedFolderId(stdPath, PageName.NavatarInvestorManager, 30);
					if (np.clickOnAddFolderButton(id)) {
						if (np.getAddSubFolderText(30)!=null) {
							appLog.info("add a folder text is successfully displayed");
						}
						else {
							appLog.error("add a folder text is not visible");
							sa.assertTrue(false, "add a folder text is not visible");
						}
						if (np.getFolderNameInSubFolderWindow(30).getText().trim().contains(stdPath)) {
							appLog.info("successfully found common folder name");
						}
						else {
							appLog.error("could not find common folder name on add sub folder window");
							sa.assertTrue(false, "could not find common folder name on add sub folder window");
						}
						if (sendKeys(driver, np.getAddSubFolderTextBox(30),stdPath + "Sub Folder 2" , "sub folder textbox", action.BOOLEAN)) {
							if (click(driver, np.getSaveButtonAddSubFolder(30), "save button add sub folder", action.BOOLEAN)) {

							}
							else {
								appLog.error("save button of add sub folder window is not clickable");
								sa.assertTrue(false, "save button of add sub folder window is not clickable");
							}
						}
						else {
							appLog.error("sub folder textbox is not visible");
							sa.assertTrue(false, "sub folder textbox is not visible");
						}
					}
					else {
						appLog.error(id + " could not be found linked to any folder");
						sa.assertTrue(false, id + " could not be found linked to any folder");
					}
					//verify std sub folder presence
					id = fp.getCreatedFolderId(stdPath+"/"+stdPath+"Sub Folder 2", PageName.NavatarInvestorManager, 30);
					if (id!=null) {
						appLog.info("successfully found standard child folder under standard parent folder");
					}
					else {
						appLog.error("could not find std child folder under std parent folder");
						sa.assertTrue(false, "could not find std child folder under std parent folder");
					}
					if (click(driver, np.getFolderTemplateSaveButton(30), "save button folder template", action.SCROLLANDBOOLEAN)) {
						appLog.info("successfully clicked save button");
						if (click(driver, np.getSaveConfirmationYesButton(30), "yes button", action.BOOLEAN)) {
							
						}
						else {
							appLog.error("could not click on yes button");
							sa.assertTrue(false, "could not click on yes button");
						}
					}
					else {
						appLog.error("save button is not clickable");
						sa.assertTrue(false, "save button is not clickable");
					}
					//verify all sub folders presence
					boolean found = false;
					found = np.verifyPresenceOfFolderNameInFolderStructure(cmnPath.split("\\(")[0]+"Sub Folder 2");
					if (found) {
						appLog.info("successfully verified cmn sub folder path");
					}
					else {
						appLog.error("could not verify cmn sub folder");
						sa.assertTrue(false, "could not verify cmn sub folder");
					}
					
					found = np.verifyPresenceOfFolderNameInFolderStructure(intPath.split("\\(")[0]+"Sub Folder 2");
					if (found) {
						appLog.info("successfully verified int sub folder path");
					}
					else {
						appLog.error("could not verify int sub folder");
						sa.assertTrue(false, "could not verify int sub folder");
					}
					found = np.verifyPresenceOfFolderNameInFolderStructure(shdPath.split("\\(")[0]+"Sub Folder 2");
					if (found) {
						appLog.info("successfully verified shd sub folder path");
					}
					else {
						appLog.error("could not verify shd sub folder");
						sa.assertTrue(false, "could not verify shd sub folder");
					}
					found = np.verifyPresenceOfFolderNameInFolderStructure(stdPath+"Sub Folder 2");
					if (found) {
						appLog.info("successfully verified std sub folder path");
					}
					else {
						appLog.error("could not verify std sub folder");
						sa.assertTrue(false, "could not verify std sub folder");
					}
				}
				else {
					appLog.error("edit icon is not clickable");
					sa.assertTrue(false, "edit icon is not clickable");
				}
			}
			else {
				appLog.error("folder template link is not clickable");
				sa.assertTrue(false, "folder template link is not clickable");
			}
			switchToDefaultContent(driver);
		}
		else {
			appLog.error("nim tab is not clickable");
			sa.assertTrue(false, "nim tab is not clickable");
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}
		
	@Test
	public void M11tc021_VerifyEditingFolderTemplateAndFolder(){
		
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		
		//Verifying Editing Folder Template and Folder.
		lp.CRMLogin(Org3CRMUser3EmailID, adminPassword);
		if (nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, nim.getFrame( PageName.NavatarInvestorManager, 30));
			if(nim.clickOnSideMenusTab(sideMenu.FolderTemplates)){
				if(selectVisibleTextFromDropDown(driver,nim.getDropdownTempType(30),"All Templates" , "All Templates")){
					if(click(driver, nim.getfoldertemplatelink(folderTemplateName+"T2"+"U2UP",20), "Folder Template link", action.SCROLLANDBOOLEAN)){
						if(click(driver, nim.getFolderTemplateEditIcon(30), "Edit icon", action.SCROLLANDBOOLEAN)){

							//Verifying Renaming Common Folder
							String id=nim.getCreatedFolderId(ExcelUtils.readData("FolderTemp", 2, 1), PageName.NavatarInvestorManager, 30);
							if(id!=null){
								appLog.info("Successfully get Folder ID.");
								if(nim.clickOnRenameFolderButton(id)){
									appLog.info("Successfully Clicked of Edit icon against - "+ExcelUtils.readData("FolderTemp", 2, 1)+" "+"Folder.");

									if(nim.getRenameFolderHeader(20).getText().trim().equalsIgnoreCase(NIMPageErrorMessage.RenameFolderPopUpHeader)){
										appLog.info("Rename Folder pop up header is verified successfully. Expected:>"+NIMPageErrorMessage.RenameFolderPopUpHeader+"Actual >"+nim.getRenameFolderHeader(20).getText().trim());
									}else{
										appLog.info("Rename Folder pop up header is not verified successfully. Expected:>"+NIMPageErrorMessage.RenameFolderPopUpHeader+"Actual >"+nim.getRenameFolderHeader(20).getText().trim());
										sa.assertTrue(false, "Rename Folder pop up header is not verified successfully. Expected:>"+NIMPageErrorMessage.RenameFolderPopUpHeader+"Actual >"+nim.getRenameFolderHeader(20).getText().trim());
									}

									if(nim.getRenameFolderCrossIcon(10)!=null){
										appLog.info("Rename Folder pop up cross icon is available.");
									}else{
										appLog.info("Rename Folder pop up cross icon is not available.");
										sa.assertTrue(false, "Rename Folder pop up cross icon is not available.");
									}

									if(nim.getRenameFolderCloseButton(10)!=null){
										appLog.info("Rename Folder pop up close button is available.");
									}else{
										appLog.info("Rename Folder pop up close buttonn is not available.");
										sa.assertTrue(false, "Rename Folder pop up close button is not available.");
									}

									if(nim.getRenameFolderSaveButton(10)!=null){
										appLog.info("Rename Folder pop up Save button is available.");
									}else{
										appLog.info("Rename Folder pop up header Save buttonn is not available.");
										sa.assertTrue(false, "Rename Folder pop up Save button is not available.");
									}

									if(nim.getRenameFolderNameText(10).getText().trim().equalsIgnoreCase("Folder Name:")){
										appLog.info("Rename Folder pop up text is verified Successfully.");
									}else{
										appLog.info("Rename Folder pop up text is verified Successfully.");
										sa.assertTrue(false, "Rename Folder pop up text is verified Successfully.");
									}

									if(nim.getRenameFolderTextBoxValue(10)!=null){
										appLog.info("Rename Folder pop up, Rename Text box is verified Successfully.");
										{
											String FolderName=getValueFromElementUsingJavaScript(driver, nim.getRenameFolderTextBoxValue(10),"");

											if(FolderName.trim().equalsIgnoreCase(ExcelUtils.readData("FolderTemp", 2, 1).split("\\(Co")[0].trim())){
												appLog.info("Successfully Verified Value in Rename Folder Textbox Expeected:> "+ExcelUtils.readData("FolderTemp", 2, 1).split("\\(Co")[0]+"Actual:>"+FolderName);
											}else{
												appLog.info("Rename Folder Textbox value is not verified Expected:> "+ExcelUtils.readData("FolderTemp", 2, 1).split("\\(Co")[0].trim()+"Actual:>"+FolderName);
												sa.assertTrue(false, "Rename Folder Textbox value is not verified Expeected:> "+ExcelUtils.readData("FolderTemp", 2, 1).split("\\(Co")[0].trim()+"Actual:>"+FolderName);
											}

											if(sendKeys(driver, nim.getRenameFolderTextBoxValue(10), ExcelUtils.readData("FolderTemp", 2, 1).split("\\(Co")[0].trim()+"UP", "Rename Folder Textbox", action.SCROLLANDBOOLEAN)){
												appLog.info("Successfully Entered Value in "+ExcelUtils.readData("FolderTemp", 2, 1).split("\\(Co")[0].trim()+"UP"+" Folder.");
												if(click(driver, nim.getRenameFolderSaveButton(10), "Save Button", action.SCROLLANDBOOLEAN)){
													String FolderNameAfterUpdate=nim.getFolderElement(ExcelUtils.readData("FolderTemp", 2, 1).split("\\(Co")[0].trim()+"UP"+" "+"(Common)".trim(), 20).getText().trim();
													String ExpectedFolderName=ExcelUtils.readData("FolderTemp", 2, 1).split("\\(Co")[0].trim()+"UP"+" "+"(Common)";
													if(FolderNameAfterUpdate.equalsIgnoreCase(ExpectedFolderName)){
														appLog.info("Successfully saved folder. Expected:>"+ExpectedFolderName+"Actual "+FolderNameAfterUpdate);
													}else{
														appLog.info("Folder is not saved. Expected:>"+ExpectedFolderName+"Actual "+FolderNameAfterUpdate);
														sa.assertTrue(false, "Folder is not saved. Expected:>"+ExpectedFolderName+"Actual "+FolderNameAfterUpdate);
													}
												}else{
													appLog.info("Can not click on Save button of Rename Folder pop up.");
													sa.assertTrue(false, "Can not click on Save button of Rename Folder pop up.");
												}
											}else{
												appLog.info("Not able to enter value in "+ExcelUtils.readData("FolderTemp", 2, 1).split("\\(Co")[0].trim()+"UP"+" Folder.");
												sa.assertTrue(false, "Not able to enter value in "+ExcelUtils.readData("FolderTemp", 2, 1).split("\\(Co")[0].trim()+"UP"+" Folder.");
											}
										}
									}else{
										appLog.info("Rename Folder pop up, Rename Text box is verified Successfully.");
										sa.assertTrue(false, "Rename Folder pop up, Rename Text box is verified Successfully.");
									}
								} else{
									appLog.error("Can not Click on Edit icon against - "+ExcelUtils.readData("FolderTemp", 2, 1)+" "+"Folder.");
									sa.assertTrue(false,"Can not Click on Edit icon against - "+ExcelUtils.readData("FolderTemp", 2, 1)+" "+"Folder.");
								}
							}else{
								appLog.error("Can not get Folder ID of "+ExcelUtils.readData("FolderTemp", 2, 1)+" "+"Folder.");
								sa.assertTrue(false,"Can not get Folder ID of "+ExcelUtils.readData("FolderTemp", 2, 1)+" "+"Folder.");
							}

							//Verifying Renaming Internal Folder
							String id_internal=nim.getCreatedFolderId(ExcelUtils.readData("FolderTemp", 4, 1), PageName.NavatarInvestorManager, 30);
							if(id_internal!=null){
								appLog.info("Successfully get Folder ID for Internal.");
								if(nim.clickOnRenameFolderButton(id_internal)){
									appLog.info("Successfully Clicked of Edit icon against - "+ExcelUtils.readData("FolderTemp", 4, 1)+" "+"Folder.");

									if(nim.getRenameFolderHeader(20).getText().trim().equalsIgnoreCase(NIMPageErrorMessage.RenameFolderPopUpHeader)){
										appLog.info("Rename Folder pop up header is verified successfully. Expected:>"+NIMPageErrorMessage.RenameFolderPopUpHeader+"Actual >"+nim.getRenameFolderHeader(20).getText().trim());
									}else{
										appLog.info("Rename Folder pop up header is not verified successfully. Expected:>"+NIMPageErrorMessage.RenameFolderPopUpHeader+"Actual >"+nim.getRenameFolderHeader(20).getText().trim());
										sa.assertTrue(false, "Rename Folder pop up header is not verified successfully. Expected:>"+NIMPageErrorMessage.RenameFolderPopUpHeader+"Actual >"+nim.getRenameFolderHeader(20).getText().trim());
									}

									if(nim.getRenameFolderCrossIcon(10)!=null){
										appLog.info("Rename Folder pop up cross icon is available.");
									}else{
										appLog.info("Rename Folder pop up cross icon is not available.");
										sa.assertTrue(false, "Rename Folder pop up cross icon is not available.");
									}

									if(nim.getRenameFolderCloseButton(10)!=null){
										appLog.info("Rename Folder pop up close button is available.");
									}else{
										appLog.info("Rename Folder pop up close buttonn is not available.");
										sa.assertTrue(false, "Rename Folder pop up close button is not available.");
									}

									if(nim.getRenameFolderSaveButton(10)!=null){
										appLog.info("Rename Folder pop up Save button is available.");
									}else{
										appLog.info("Rename Folder pop up header Save buttonn is not available.");
										sa.assertTrue(false, "Rename Folder pop up Save button is not available.");
									}

									if(nim.getRenameFolderNameText(10).getText().trim().equalsIgnoreCase("Folder Name:")){
										appLog.info("Rename Folder pop up text is verified Successfully.");
									}else{
										appLog.info("Rename Folder pop up text is verified Successfully.");
										sa.assertTrue(false, "Rename Folder pop up text is verified Successfully.");
									}

									if(nim.getRenameFolderTextBoxValue(10)!=null){
										appLog.info("Rename Folder pop up, Rename Text box is verified Successfully.");
										{
											String FolderName=getValueFromElementUsingJavaScript(driver, nim.getRenameFolderTextBoxValue(10),"");

											if(FolderName.trim().equalsIgnoreCase(ExcelUtils.readData("FolderTemp", 4, 1).split("\\(In")[0].trim())){
												appLog.info("Successfully Verified Value in Rename Folder Textbox Expeected:> "+ExcelUtils.readData("FolderTemp", 4, 1).split("\\(In")[0]+"Actual:>"+FolderName);
											}else{
												appLog.info("Rename Folder Textbox value is not verified Expected:> "+ExcelUtils.readData("FolderTemp", 4, 1).split("\\(In")[0].trim()+"Actual:>"+FolderName);
												sa.assertTrue(false, "Rename Folder Textbox value is not verified Expeected:> "+ExcelUtils.readData("FolderTemp", 4, 1).split("\\(In")[0].trim()+"Actual:>"+FolderName);
											}

											if(sendKeys(driver, nim.getRenameFolderTextBoxValue(10), ExcelUtils.readData("FolderTemp", 4, 1).split("\\(In")[0].trim()+"UP", "Rename Folder Textbox", action.SCROLLANDBOOLEAN)){
												appLog.info("Successfully Entered Value in "+ExcelUtils.readData("FolderTemp", 4, 1).split("\\(In")[0].trim()+"UP"+" Folder.");
												if(click(driver, nim.getRenameFolderSaveButton(10), "Save Button", action.SCROLLANDBOOLEAN)){
													String FolderNameAfterUpdate=nim.getFolderElement(ExcelUtils.readData("FolderTemp", 4, 1).split("\\(In")[0].trim()+"UP"+" "+"(Internal)".trim(), 20).getText().trim();
													String ExpectedFolderName=ExcelUtils.readData("FolderTemp", 4, 1).split("\\(In")[0].trim()+"UP"+" "+"(Internal)";
													if(FolderNameAfterUpdate.equalsIgnoreCase(ExpectedFolderName)){
														appLog.info("Successfully saved folder. Expected:>"+ExpectedFolderName+"Actual "+FolderNameAfterUpdate);
													}else{
														appLog.info("Folder is not saved. Expected:>"+ExpectedFolderName+"Actual "+FolderNameAfterUpdate);
														sa.assertTrue(false, "Folder is not saved. Expected:>"+ExpectedFolderName+"Actual "+FolderNameAfterUpdate);
													}
												}else{
													appLog.info("Can not click on Save button of Rename Folder pop up.");
													sa.assertTrue(false, "Can not click on Save button of Rename Folder pop up.");
												}
											}else{
												appLog.info("Not able to enter value in "+ExcelUtils.readData("FolderTemp", 4, 1).split("\\(In")[0].trim()+"UP"+" Folder.");
												sa.assertTrue(false, "Not able to enter value in "+ExcelUtils.readData("FolderTemp", 4, 1).split("\\(In")[0].trim()+"UP"+" Folder.");
											}
										}
									}else{
										appLog.info("Rename Folder pop up, Rename Text box is verified Successfully.");
										sa.assertTrue(false, "Rename Folder pop up, Rename Text box is verified Successfully.");
									}
								} else{
									appLog.error("Can not Click on Edit icon against - "+ExcelUtils.readData("FolderTemp", 4, 1)+" "+"Folder.");
									sa.assertTrue(false,"Can not Click on Edit icon against - "+ExcelUtils.readData("FolderTemp", 4, 1)+" "+"Folder.");
								}
							}else{
								appLog.error("Can not get Folder ID of "+ExcelUtils.readData("FolderTemp", 4, 1)+" "+"Folder.");
								sa.assertTrue(false,"Can not get Folder ID of "+ExcelUtils.readData("FolderTemp", 4, 1)+" "+"Folder.");
							}

							//Verifying Renaming Shared Folder
							String id_Shared=nim.getCreatedFolderId(ExcelUtils.readData("FolderTemp", 3, 1), PageName.NavatarInvestorManager, 30);
							if(id_Shared!=null){
								appLog.info("Successfully get Folder ID for Shared.");
								if(nim.clickOnRenameFolderButton(id_Shared)){
									appLog.info("Successfully Clicked of Edit icon against - "+ExcelUtils.readData("FolderTemp", 3, 1)+" "+"Folder.");

									if(nim.getRenameFolderHeader(20).getText().trim().equalsIgnoreCase(NIMPageErrorMessage.RenameFolderPopUpHeader)){
										appLog.info("Rename Folder pop up header is verified successfully. Expected:>"+NIMPageErrorMessage.RenameFolderPopUpHeader+"Actual >"+nim.getRenameFolderHeader(20).getText().trim());
									}else{
										appLog.info("Rename Folder pop up header is not verified successfully. Expected:>"+NIMPageErrorMessage.RenameFolderPopUpHeader+"Actual >"+nim.getRenameFolderHeader(20).getText().trim());
										sa.assertTrue(false, "Rename Folder pop up header is not verified successfully. Expected:>"+NIMPageErrorMessage.RenameFolderPopUpHeader+"Actual >"+nim.getRenameFolderHeader(20).getText().trim());
									}

									if(nim.getRenameFolderCrossIcon(10)!=null){
										appLog.info("Rename Folder pop up cross icon is available.");
									}else{
										appLog.info("Rename Folder pop up cross icon is not available.");
										sa.assertTrue(false, "Rename Folder pop up cross icon is not available.");
									}

									if(nim.getRenameFolderCloseButton(10)!=null){
										appLog.info("Rename Folder pop up close button is available.");
									}else{
										appLog.info("Rename Folder pop up close buttonn is not available.");
										sa.assertTrue(false, "Rename Folder pop up close button is not available.");
									}

									if(nim.getRenameFolderSaveButton(10)!=null){
										appLog.info("Rename Folder pop up Save button is available.");
									}else{
										appLog.info("Rename Folder pop up header Save buttonn is not available.");
										sa.assertTrue(false, "Rename Folder pop up Save button is not available.");
									}

									if(nim.getRenameFolderNameText(10).getText().trim().equalsIgnoreCase("Folder Name:")){
										appLog.info("Rename Folder pop up text is verified Successfully.");
									}else{
										appLog.info("Rename Folder pop up text is verified Successfully.");
										sa.assertTrue(false, "Rename Folder pop up text is verified Successfully.");
									}

									if(nim.getRenameFolderTextBoxValue(10)!=null){
										appLog.info("Rename Folder pop up, Rename Text box is verified Successfully.");
										{
											String FolderName=getValueFromElementUsingJavaScript(driver, nim.getRenameFolderTextBoxValue(10),"");

											if(FolderName.trim().equalsIgnoreCase(ExcelUtils.readData("FolderTemp", 3, 1).split("\\(Sh")[0].trim())){
												appLog.info("Successfully Verified Value in Rename Folder Textbox Expeected:> "+ExcelUtils.readData("FolderTemp", 3, 1).split("\\(Sh")[0]+"Actual:>"+FolderName);
											}else{
												appLog.info("Rename Folder Textbox value is not verified Expected:> "+ExcelUtils.readData("FolderTemp", 3, 1).split("\\(Sh")[0].trim()+"Actual:>"+FolderName);
												sa.assertTrue(false, "Rename Folder Textbox value is not verified Expeected:> "+ExcelUtils.readData("FolderTemp", 3, 1).split("\\(Sh")[0].trim()+"Actual:>"+FolderName);
											}

											if(sendKeys(driver, nim.getRenameFolderTextBoxValue(10), ExcelUtils.readData("FolderTemp", 3, 1).split("\\(Sh")[0].trim()+"UP", "Rename Folder Textbox", action.SCROLLANDBOOLEAN)){
												appLog.info("Successfully Entered Value in "+ExcelUtils.readData("FolderTemp", 3, 1).split("\\(Sh")[0].trim()+"UP"+" Folder.");
												if(click(driver, nim.getRenameFolderSaveButton(10), "Save Button", action.SCROLLANDBOOLEAN)){
													String FolderNameAfterUpdate=nim.getFolderElement(ExcelUtils.readData("FolderTemp", 3, 1).split("\\(Sh")[0].trim()+"UP"+" "+"(Shared)".trim(), 20).getText().trim();
													String ExpectedFolderName=ExcelUtils.readData("FolderTemp", 3, 1).split("\\(Sh")[0].trim()+"UP"+" "+"(Shared)";
													if(FolderNameAfterUpdate.equalsIgnoreCase(ExpectedFolderName)){
														appLog.info("Successfully saved folder. Expected:>"+ExpectedFolderName+"Actual "+FolderNameAfterUpdate);
													}else{
														appLog.info("Folder is not saved. Expected:>"+ExpectedFolderName+"Actual "+FolderNameAfterUpdate);
														sa.assertTrue(false, "Folder is not saved. Expected:>"+ExpectedFolderName+"Actual "+FolderNameAfterUpdate);
													}
												}else{
													appLog.info("Can not click on Save button of Rename Folder pop up.");
													sa.assertTrue(false, "Can not click on Save button of Rename Folder pop up.");
												}
											}else{
												appLog.info("Not able to enter value in "+ExcelUtils.readData("FolderTemp", 3, 1).split("\\(Sh")[0].trim()+"UP"+" Folder.");
												sa.assertTrue(false, "Not able to enter value in "+ExcelUtils.readData("FolderTemp", 3, 1).split("\\(Sh")[0].trim()+"UP"+" Folder.");
											}
										}
									}else{
										appLog.info("Rename Folder pop up, Rename Text box is verified Successfully.");
										sa.assertTrue(false, "Rename Folder pop up, Rename Text box is verified Successfully.");
									}
								} else{
									appLog.error("Can not Click on Edit icon against - "+ExcelUtils.readData("FolderTemp", 3, 1)+" "+"Folder.");
									sa.assertTrue(false,"Can not Click on Edit icon against - "+ExcelUtils.readData("FolderTemp", 3, 1)+" "+"Folder.");
								}
							}else{
								appLog.error("Can not get Folder ID of "+ExcelUtils.readData("FolderTemp", 3, 1)+" "+"Folder.");
								sa.assertTrue(false,"Can not get Folder ID of "+ExcelUtils.readData("FolderTemp", 3, 1)+" "+"Folder.");
							}


							//Verifying Renaming Standard Folder
							String id_Standard=nim.getCreatedFolderId(ExcelUtils.readData("FolderTemp", 5, 1), PageName.NavatarInvestorManager, 30);
							if(id_Standard!=null){
								appLog.info("Successfully get Folder ID for Standard.");
								if(nim.clickOnRenameFolderButton(id_Standard)){
									appLog.info("Successfully Clicked of Edit icon against - "+ExcelUtils.readData("FolderTemp", 5, 1)+" "+"Folder.");

									if(nim.getRenameFolderHeader(20).getText().trim().equalsIgnoreCase(NIMPageErrorMessage.RenameFolderPopUpHeader)){
										appLog.info("Rename Folder pop up header is verified successfully. Expected:>"+NIMPageErrorMessage.RenameFolderPopUpHeader+"Actual >"+nim.getRenameFolderHeader(20).getText().trim());
									}else{
										appLog.info("Rename Folder pop up header is not verified successfully. Expected:>"+NIMPageErrorMessage.RenameFolderPopUpHeader+"Actual >"+nim.getRenameFolderHeader(20).getText().trim());
										sa.assertTrue(false, "Rename Folder pop up header is not verified successfully. Expected:>"+NIMPageErrorMessage.RenameFolderPopUpHeader+"Actual >"+nim.getRenameFolderHeader(20).getText().trim());
									}

									if(nim.getRenameFolderCrossIcon(10)!=null){
										appLog.info("Rename Folder pop up cross icon is available.");
									}else{
										appLog.info("Rename Folder pop up cross icon is not available.");
										sa.assertTrue(false, "Rename Folder pop up cross icon is not available.");
									}

									if(nim.getRenameFolderCloseButton(10)!=null){
										appLog.info("Rename Folder pop up close button is available.");
									}else{
										appLog.info("Rename Folder pop up close buttonn is not available.");
										sa.assertTrue(false, "Rename Folder pop up close button is not available.");
									}

									if(nim.getRenameFolderSaveButton(10)!=null){
										appLog.info("Rename Folder pop up Save button is available.");
									}else{
										appLog.info("Rename Folder pop up header Save buttonn is not available.");
										sa.assertTrue(false, "Rename Folder pop up Save button is not available.");
									}

									if(nim.getRenameFolderNameText(10).getText().trim().equalsIgnoreCase("Folder Name:")){
										appLog.info("Rename Folder pop up text is verified Successfully.");
									}else{
										appLog.info("Rename Folder pop up text is verified Successfully.");
										sa.assertTrue(false, "Rename Folder pop up text is verified Successfully.");
									}

									if(nim.getRenameFolderTextBoxValue(10)!=null){
										appLog.info("Rename Folder pop up, Rename Text box is verified Successfully.");
										{
											String FolderName=getValueFromElementUsingJavaScript(driver, nim.getRenameFolderTextBoxValue(10),"");

											if(FolderName.trim().equalsIgnoreCase(ExcelUtils.readData("FolderTemp", 5, 1).trim())){
												appLog.info("Successfully Verified Value in Rename Folder Textbox Expeected:> "+ExcelUtils.readData("FolderTemp", 5, 1)+"Actual:>"+FolderName);
											}else{
												appLog.info("Rename Folder Textbox value is not verified Expected:> "+ExcelUtils.readData("FolderTemp", 5, 1).trim()+"Actual:>"+FolderName);
												sa.assertTrue(false, "Rename Folder Textbox value is not verified Expeected:> "+ExcelUtils.readData("FolderTemp", 5, 1).trim()+"Actual:>"+FolderName);
											}

											if(sendKeys(driver, nim.getRenameFolderTextBoxValue(10), ExcelUtils.readData("FolderTemp", 5, 1).trim()+"UP", "Rename Folder Textbox", action.SCROLLANDBOOLEAN)){
												appLog.info("Successfully Entered Value in "+ExcelUtils.readData("FolderTemp", 5, 1).trim()+"UP"+" Folder.");
												if(click(driver, nim.getRenameFolderSaveButton(10), "Save Button", action.SCROLLANDBOOLEAN)){
													String FolderNameAfterUpdate=nim.getFolderElement(ExcelUtils.readData("FolderTemp", 5, 1).trim()+"UP".trim(), 20).getText().trim();
													String ExpectedFolderName=ExcelUtils.readData("FolderTemp", 5, 1).trim()+"UP";
													if(FolderNameAfterUpdate.equalsIgnoreCase(ExpectedFolderName)){
														appLog.info("Successfully saved folder. Expected:>"+ExpectedFolderName+"Actual "+FolderNameAfterUpdate);
													}else{
														appLog.info("Folder is not saved. Expected:>"+ExpectedFolderName+"Actual "+FolderNameAfterUpdate);
														sa.assertTrue(false, "Folder is not saved. Expected:>"+ExpectedFolderName+"Actual "+FolderNameAfterUpdate);
													}
												}else{
													appLog.info("Can not click on Save button of Rename Folder pop up.");
													sa.assertTrue(false, "Can not click on Save button of Rename Folder pop up.");
												}
											}else{
												appLog.info("Not able to enter value in "+ExcelUtils.readData("FolderTemp", 5, 1).trim()+"UP"+" Folder.");
												sa.assertTrue(false, "Not able to enter value in "+ExcelUtils.readData("FolderTemp", 5, 1).trim()+"UP"+" Folder.");
											}
										}
									}else{
										appLog.info("Rename Folder pop up, Rename Text box is verified Successfully.");
										sa.assertTrue(false, "Rename Folder pop up, Rename Text box is verified Successfully.");
									}
								} else{
									appLog.error("Can not Click on Edit icon against - "+ExcelUtils.readData("FolderTemp", 5, 1)+" "+"Folder.");
									sa.assertTrue(false,"Can not Click on Edit icon against - "+ExcelUtils.readData("FolderTemp", 5, 1)+" "+"Folder.");
								}
							}else{
								appLog.error("Can not get Folder ID of "+ExcelUtils.readData("FolderTemp", 5, 1)+" "+"Folder.");
								sa.assertTrue(false,"Can not get Folder ID of "+ExcelUtils.readData("FolderTemp", 5, 1)+" "+"Folder.");
							}

							//Verifying saving of folder template.
							if(click(driver, nim.getFolderTemplateSaveButton(10), "Save button", action.SCROLLANDBOOLEAN)){
								appLog.info("Successfully Click on Save button fot template "+folderTemplateName+"T2"+"U2UP");
								if(click(driver, nim.getSaveConfirmationYesButton(15), "Yes button", action.SCROLLANDBOOLEAN)){
									appLog.info("Successfully clicked on Yes Button while saving "+folderTemplateName+"T2"+"U2UP"+"Template.");
									//Verifying Save folder Template after editing folders.
									//Standard Folder.
									String FolderNameAfterUpdate=nim.getFolderElement(ExcelUtils.readData("FolderTemp", 5, 1).trim()+"UP".trim(), 20).getText().trim();
									String ExpectedFolderName=ExcelUtils.readData("FolderTemp", 5, 1).trim()+"UP";
									if(FolderNameAfterUpdate.equalsIgnoreCase(ExpectedFolderName)){
										appLog.info("Successfully saved Template with folder. Expected:>"+ExpectedFolderName+"Actual "+FolderNameAfterUpdate);
									}else{
										appLog.info("Folder is not saved. Expected:>"+ExpectedFolderName+"Actual "+FolderNameAfterUpdate);
										sa.assertTrue(false, "Folder is not saved. Expected:>"+ExpectedFolderName+"Actual "+FolderNameAfterUpdate);
									}

									//Shared Folder
									FolderNameAfterUpdate=nim.getFolderElement(ExcelUtils.readData("FolderTemp", 3, 1).split("\\(Sh")[0].trim()+"UP"+" "+"(Shared)".trim(), 20).getText().trim();
									ExpectedFolderName=ExcelUtils.readData("FolderTemp", 3, 1).split("\\(Sh")[0].trim()+"UP"+" "+"(Shared)";
									if(FolderNameAfterUpdate.equalsIgnoreCase(ExpectedFolderName)){
										appLog.info("Successfully saved Template with folder. Expected:>"+ExpectedFolderName+"Actual "+FolderNameAfterUpdate);
									}else{
										appLog.info("Folder is not saved. Expected:>"+ExpectedFolderName+"Actual "+FolderNameAfterUpdate);
										sa.assertTrue(false, "Folder is not saved. Expected:>"+ExpectedFolderName+"Actual "+FolderNameAfterUpdate);
									}

									//Internal Folder
									FolderNameAfterUpdate=nim.getFolderElement(ExcelUtils.readData("FolderTemp", 4, 1).split("\\(In")[0].trim()+"UP"+" "+"(Internal)".trim(), 20).getText().trim();
									ExpectedFolderName=ExcelUtils.readData("FolderTemp", 4, 1).split("\\(In")[0].trim()+"UP"+" "+"(Internal)";
									if(FolderNameAfterUpdate.equalsIgnoreCase(ExpectedFolderName)){
										appLog.info("Successfully saved Template with folder. Expected:>"+ExpectedFolderName+"Actual "+FolderNameAfterUpdate);
									}else{
										appLog.info("Folder is not saved. Expected:>"+ExpectedFolderName+"Actual "+FolderNameAfterUpdate);
										sa.assertTrue(false, "Folder is not saved. Expected:>"+ExpectedFolderName+"Actual "+FolderNameAfterUpdate);
									}

									//Common Folder
									FolderNameAfterUpdate=nim.getFolderElement(ExcelUtils.readData("FolderTemp", 2, 1).split("\\(Co")[0].trim()+"UP"+" "+"(Common)".trim(), 20).getText().trim();
									ExpectedFolderName=ExcelUtils.readData("FolderTemp", 2, 1).split("\\(Co")[0].trim()+"UP"+" "+"(Common)";
									if(FolderNameAfterUpdate.equalsIgnoreCase(ExpectedFolderName)){
										appLog.info("Successfully saved Template with folder. Expected:>"+ExpectedFolderName+"Actual "+FolderNameAfterUpdate);
									}else{
										appLog.info("Folder is not saved. Expected:>"+ExpectedFolderName+"Actual "+FolderNameAfterUpdate);
										sa.assertTrue(false, "Folder is not saved. Expected:>"+ExpectedFolderName+"Actual "+FolderNameAfterUpdate);
									}
								}else{
									appLog.error("Can not Click on Yes Button while saving Folder Template :"+folderTemplateName+"T2"+"U2UP");
									sa.assertTrue(false, "Can not Click on Yes Button while saving Folder Template :"+folderTemplateName+"T2"+"U2UP");
								}
							}else{
								appLog.error("Can not Click Template"+folderTemplateName+"T2"+"U2UP"+" Save button.");
								sa.assertTrue(false, "Can not Click Template"+folderTemplateName+"T2"+"U2UP"+" Save button.");
							}

						}else {
							appLog.error("Can not Click Template"+folderTemplateName+"T2"+"U2UP"+" Edit icon.");
							sa.assertTrue(false, "Can not Click Template"+folderTemplateName+"T2"+"U2UP"+" Edit icon.");
						}
					}else{
						appLog.error("Can not Click on Template Name.");
						sa.assertTrue(false, "Can not Click on Template Name.");
					}
				}else{
					appLog.error("Can not select All templates.");
					sa.assertTrue(false, "Can not select All templates.");
				}
			}else{
				appLog.error("Can not click on Folder Template Link.");
				sa.assertTrue(false, "Can not click on Folder Template Link.");
			}
			switchToDefaultContent(driver);
		}else{
			appLog.error("Can not click on NIM Tab");
			sa.assertTrue(false, "Can not click on NIM Tab");
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();

	}
	
	@Test
	public void M11tc022_VerifyEditingFolderTemplateAndDeleteFolder(){
		
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		
		lp.CRMLogin(Org3CRMUser3EmailID, adminPassword);
		if (nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, nim.getFrame( PageName.NavatarInvestorManager, 30));
			if(nim.clickOnSideMenusTab(sideMenu.FolderTemplates)){
				if(selectVisibleTextFromDropDown(driver,nim.getDropdownTempType(30),"All Templates" , "All Templates")){
					if(click(driver, nim.getfoldertemplatelink(folderTemplateName+"T2"+"U2UP",20), "Folder Template link", action.SCROLLANDBOOLEAN)){
						appLog.info("Successfully clicked on Template Link.");
						if(click(driver, nim.getFolderTemplateEditIcon(30), "Edit icon", action.SCROLLANDBOOLEAN)){
						
						String id=nim.getCreatedFolderId(ExcelUtils.readData("FolderTemp", 2, 1).split("\\(Co")[0].trim()+"UP"+" "+"(Common)", PageName.NavatarInvestorManager, 10);
						if(id!=null){
							appLog.info("Successfully get Folder ID.");
							if(nim.clickOnDeleteFolderButton(id)){
								appLog.info("Successfully Clicked of Delete icon against - "+ExcelUtils.readData("FolderTemp", 2, 1).split("\\(Co")[0].trim()+"UP"+" "+"(Common)"+" "+"Folder.");

								if(nim.getDeleteFolderHeader(10).getText().trim().equalsIgnoreCase(NIMPageErrorMessage.DeleteFolderPopUpHeader)){
									appLog.info("Delete Folder pop up header is verified successfully. Expected:>"+NIMPageErrorMessage.DeleteFolderPopUpHeader+"Actual >"+nim.getDeleteFolderHeader(10).getText().trim());
								}else{
									appLog.info("Delete Folder pop up header is not verified successfully. Expected:>"+NIMPageErrorMessage.DeleteFolderPopUpHeader+"Actual >"+nim.getDeleteFolderHeader(10).getText().trim());
									sa.assertTrue(false, "Delete Folder pop up header is not verified successfully. Expected:>"+NIMPageErrorMessage.DeleteFolderPopUpHeader+"Actual >"+nim.getDeleteFolderHeader(10).getText().trim());
								}

								if(nim.getDeleteFolderCrossIcon(10)!=null){
									appLog.info("Delete Folder pop up cross icon is available.");
								}else{
									appLog.info("Delete Folder pop up cross icon is not available.");
									sa.assertTrue(false, "Delete Folder pop up cross icon is not available.");
								}

								if(nim.getDeleteFolderYesButton(10)!=null){
									appLog.info("Delete Folder pop up Yes button is available.");
								}else{
									appLog.info("Delete Folder pop up Yes button is not available.");
									sa.assertTrue(false, "Delete Folder pop up Yes button is not available.");
								}

								if(nim.getDeleteFolderNoButton(10)!=null){
									appLog.info("Delete Folder pop up No button is available.");
								}else{
									appLog.info("Delete Folder pop up No button is not available.");
									sa.assertTrue(false, "Delete Folder pop up No button is not available.");
								}
								
								if(nim.getDeleteFolderConfirmationMessage(10).getText().trim().equalsIgnoreCase(NIMPageErrorMessage.DeleteFolderConfirmationMessage)){
									appLog.info("Delete Folder pop up Confirmation Message is verified successfully. Expected:>"+NIMPageErrorMessage.DeleteFolderConfirmationMessage+"Actual >"+nim.getDeleteFolderConfirmationMessage(10).getText().trim());
								}else{
									appLog.info("Delete Folder pop up Confirmation Message is not verified successfully. Expected:>"+NIMPageErrorMessage.DeleteFolderConfirmationMessage+"Actual >"+nim.getDeleteFolderConfirmationMessage(10).getText().trim());
									sa.assertTrue(false, "Delete Folder pop up Confirmation Message is not verified successfully. Expected:>"+NIMPageErrorMessage.DeleteFolderConfirmationMessage+"Actual >"+nim.getDeleteFolderConfirmationMessage(10).getText().trim());
								}

								if(click(driver, nim.getDeleteFolderNoButton(10), "No button", action.SCROLLANDBOOLEAN)){
									appLog.info("Successfully clicked on No button.");

									if(nim.getDeleteFolderHeader(10)==null){
										appLog.info("After clicking 'No' button, Delete folder pop up is closed successfully.");
									}else{
										appLog.info("After clicking 'No' button, Delete folder pop up is not closed.");
										sa.assertTrue(false, "After clicking 'No' button, Delete folder pop up is not closed.");
									}
								}else{
									appLog.info("Delete Folder pop up No button is not clickable.");
									sa.assertTrue(false, "Delete Folder pop up No button is not clickable.");
								}

							} else{
								appLog.error("Can not Click on Edit icon against - "+ExcelUtils.readData("FolderTemp", 2, 1)+" "+"Folder.");
								sa.assertTrue(false,"Can not Click on Edit icon against - "+ExcelUtils.readData("FolderTemp", 2, 1)+" "+"Folder.");
							}

							if(nim.clickOnDeleteFolderButton(id)){
								appLog.info("Successfully Clicked of Delete icon against - "+ExcelUtils.readData("FolderTemp", 2, 1).split("\\(Co")[0].trim()+"UP"+" "+"(Common)"+" "+"Folder.");

								if(nim.getDeleteFolderHeader(10).getText().trim().equalsIgnoreCase(NIMPageErrorMessage.DeleteFolderPopUpHeader)){
									appLog.info("Delete Folder pop up header is verified successfully. Expected:>"+NIMPageErrorMessage.DeleteFolderPopUpHeader+"Actual >"+nim.getDeleteFolderHeader(10).getText().trim());
								}else{
									appLog.info("Delete Folder pop up header is not verified successfully. Expected:>"+NIMPageErrorMessage.DeleteFolderPopUpHeader+"Actual >"+nim.getDeleteFolderHeader(10).getText().trim());
									sa.assertTrue(false, "Delete Folder pop up header is not verified successfully. Expected:>"+NIMPageErrorMessage.DeleteFolderPopUpHeader+"Actual >"+nim.getDeleteFolderHeader(10).getText().trim());
								}

								if(click(driver, nim.getDeleteFolderCrossIcon(10), "Cross icon", action.SCROLLANDBOOLEAN)){
									appLog.info("Successfully clicked on Cros icon.");

									if(nim.getDeleteFolderHeader(10)==null){
										appLog.info("After clicking 'Cross' icon, Delete folder pop up is closed successfully.");
									}else{
										appLog.info("After clicking 'Cross' icon, Delete folder pop up is not closed.");
										sa.assertTrue(false, "After clicking 'Cross' icon, Delete folder pop up is not closed.");
									}
								}else{
									appLog.info("Delete Folder pop up 'Cross' icon is not clickable.");
									sa.assertTrue(false, "Delete Folder pop up 'Cross' icon is not clickable.");
								}
							} else{
								appLog.error("Can not Click on Delete button - "+ExcelUtils.readData("FolderTemp", 2, 1)+" "+"Folder.");
								sa.assertTrue(false,"Can not Click on Delete button against - "+ExcelUtils.readData("FolderTemp", 2, 1)+" "+"Folder.");
							}

							if(nim.clickOnDeleteFolderButton(id)){
								appLog.info("Successfully Clicked of Delete icon against - "+ExcelUtils.readData("FolderTemp", 2, 1).split("\\(Co")[0].trim()+"UP"+" "+"(Common)"+" "+"Folder.");

								if(click(driver, nim.getDeleteFolderYesButton(10), "Yes button", action.SCROLLANDBOOLEAN)){
									appLog.info("Successfully clicked on Yes button");
									//Verify deleted folder
									if(nim.getFolderElement(ExcelUtils.readData("FolderTemp", 2, 1).split("\\(Co")[0].trim()+"UP"+" "+"(Common)".trim(), 15)==null){
										appLog.info("Successfully Deleted "+ExcelUtils.readData("FolderTemp", 2, 1).split("\\(Co")[0].trim()+"UP"+" "+"(Common)".trim()+"Folder.");
									}else{
										appLog.info("Folder "+ExcelUtils.readData("FolderTemp", 2, 1).split("\\(Co")[0].trim()+"UP"+" "+"(Common)".trim()+"is not deleted successfully.");
										sa.assertTrue(false, "Folder "+ExcelUtils.readData("FolderTemp", 2, 1).split("\\(Co")[0].trim()+"UP"+" "+"(Common)".trim()+"is not deleted successfully.");
									}
								}else{
									appLog.info("Delete Folder pop up Yes is not clickable.");
									sa.assertTrue(false, "Delete Folder pop up Yes is not clickable.");
								}
							} else{
								appLog.error("Can not Click on Delete button - "+ExcelUtils.readData("FolderTemp", 2, 1)+" "+"Folder.");
								sa.assertTrue(false,"Can not Click on Delete button against - "+ExcelUtils.readData("FolderTemp", 2, 1)+" "+"Folder.");
							}						
						}else{
							appLog.error("Can not get Folder ID of "+ExcelUtils.readData("FolderTemp", 2, 1)+" "+"Folder.");
							sa.assertTrue(false,"Can not get Folder ID of "+ExcelUtils.readData("FolderTemp", 2, 1)+" "+"Folder.");
						}

						String id_Shared=nim.getCreatedFolderId(ExcelUtils.readData("FolderTemp", 3, 1).split("\\(Sh")[0].trim()+"UP"+" "+"(Shared)", PageName.NavatarInvestorManager, 10);
						if(id_Shared!=null){
							appLog.info("Successfully get Folder ID.");

							//Verifying Delete Shared Folder
							if(nim.clickOnDeleteFolderButton(id_Shared)){
								appLog.info("Successfully Clicked of Delete icon against - "+ExcelUtils.readData("FolderTemp", 3, 1).split("\\(Sh")[0].trim()+"UP"+" "+"(Shared)"+" "+"Folder.");

								if(click(driver, nim.getDeleteFolderYesButton(10), "Yes button", action.SCROLLANDBOOLEAN)){
									appLog.info("Successfully clicked on Yes button");
									//Verify deleted folder
									if(nim.getFolderElement(ExcelUtils.readData("FolderTemp", 3, 1).split("\\(Sh")[0].trim()+"UP"+" "+"(Shared)".trim(), 15)==null){
										appLog.info("Successfully Deleted "+ExcelUtils.readData("FolderTemp", 3, 1).split("\\(Sh")[0].trim()+"UP"+" "+"(Shared)".trim()+"Folder.");
									}else{
										appLog.info("Folder "+ExcelUtils.readData("FolderTemp", 3, 1).split("\\(Sh")[0].trim()+"UP"+" "+"(Shared)".trim()+"is not deleted successfully.");
										sa.assertTrue(false, "Folder "+ExcelUtils.readData("FolderTemp", 3, 1).split("\\(Sh")[0].trim()+"UP"+" "+"(Shared)".trim()+"is not deleted successfully.");
									}
								}else{
									appLog.info("Delete Folder pop up Yes is not clickable.");
									sa.assertTrue(false, "Delete Folder pop up Yes is not clickable.");
								}
							} else{
								appLog.error("Can not Click on Delete button - "+ExcelUtils.readData("FolderTemp", 3, 1)+" "+"Folder.");
								sa.assertTrue(false,"Can not Click on Delete button against - "+ExcelUtils.readData("FolderTemp", 3, 1)+" "+"Folder.");
							}

						}else{
							appLog.error("Can not get Folder ID of "+ExcelUtils.readData("FolderTemp", 3, 1)+" "+"Folder.");
							sa.assertTrue(false,"Can not get Folder ID of "+ExcelUtils.readData("FolderTemp", 3, 1)+" "+"Folder.");
						}

						String id_Internal=nim.getCreatedFolderId(ExcelUtils.readData("FolderTemp", 4, 1).split("\\(In")[0].trim()+"UP"+" "+"(Internal)", PageName.NavatarInvestorManager, 10);
						if(id_Internal!=null){
							appLog.info("Successfully get Folder ID.");
							
							//Verifying Delete Internal Folder
							if(nim.clickOnDeleteFolderButton(id_Internal)){
								appLog.info("Successfully Clicked of Delete icon against - "+ExcelUtils.readData("FolderTemp", 4, 1).split("\\(In")[0].trim()+"UP"+" "+"(Internal)"+" "+"Folder.");

								if(click(driver, nim.getDeleteFolderYesButton(10), "Yes button", action.SCROLLANDBOOLEAN)){
									appLog.info("Successfully clicked on Yes button");
									//Verify deleted folder
									if(nim.getFolderElement(ExcelUtils.readData("FolderTemp", 4, 1).split("\\(In")[0].trim()+"UP"+" "+"(Internal)".trim(), 15)==null){
										appLog.info("Successfully Deleted "+ExcelUtils.readData("FolderTemp", 4, 1).split("\\(In")[0].trim()+"UP"+" "+"(Internal)".trim()+"Folder.");
									}else{
										appLog.info("Folder "+ExcelUtils.readData("FolderTemp", 4, 1).split("\\(In")[0].trim()+"UP"+" "+"(Internal)".trim()+"is not deleted successfully.");
										sa.assertTrue(false, "Folder "+ExcelUtils.readData("FolderTemp", 4, 1).split("\\(In")[0].trim()+"UP"+" "+"(Internal)".trim()+"is not deleted successfully.");
									}
								}else{
									appLog.info("Delete Folder pop up Yes is not clickable.");
									sa.assertTrue(false, "Delete Folder pop up Yes is not clickable.");
								}
							} else{
								appLog.error("Can not Click on Delete button - "+ExcelUtils.readData("FolderTemp", 4, 1)+" "+"Folder.");
								sa.assertTrue(false,"Can not Click on Delete button against - "+ExcelUtils.readData("FolderTemp", 4, 1)+" "+"Folder.");
							}
						}else{
							appLog.error("Can not get Folder ID of "+ExcelUtils.readData("FolderTemp", 4, 1)+" "+"Folder.");
							sa.assertTrue(false,"Can not get Folder ID of "+ExcelUtils.readData("FolderTemp", 4, 1)+" "+"Folder.");
						}

						
						String id_Standard=nim.getCreatedFolderId(ExcelUtils.readData("FolderTemp", 5, 1).split("\\(")[0].trim()+"UP", PageName.NavatarInvestorManager, 10);
						if(id_Standard!=null){
							appLog.info("Successfully get Folder ID.");

							//Verifying Delete Standard Folder
							if(nim.clickOnDeleteFolderButton(id_Standard)){
								appLog.info("Successfully Clicked of Delete icon against - "+ExcelUtils.readData("FolderTemp", 5, 1).split("\\(")[0].trim()+"UP"+" "+"Folder.");

								if(click(driver, nim.getDeleteFolderYesButton(10), "Yes button", action.SCROLLANDBOOLEAN)){
									appLog.info("Successfully clicked on Yes button");
									//Verify deleted folder
									if(nim.getFolderElement(ExcelUtils.readData("FolderTemp", 5, 1).split("\\(")[0].trim()+"UP".trim(), 15)==null){
										appLog.info("Successfully Deleted "+ExcelUtils.readData("FolderTemp", 5, 1).split("\\(")[0].trim()+"UP".trim()+"Folder.");
									}else{
										appLog.info("Folder "+ExcelUtils.readData("FolderTemp", 5, 1).split("\\(")[0].trim()+"UP".trim()+"is not deleted successfully.");
										sa.assertTrue(false, "Folder "+ExcelUtils.readData("FolderTemp", 5, 1).split("\\(")[0].trim()+"UP".trim()+"is not deleted successfully.");
									}
								}else{
									appLog.info("Delete Folder pop up Yes is not clickable.");
									sa.assertTrue(false, "Delete Folder pop up Yes is not clickable.");
								}
							} else{
								appLog.error("Can not Click on Delete button - "+ExcelUtils.readData("FolderTemp", 5, 1)+" "+"Folder.");
								sa.assertTrue(false,"Can not Click on Delete button against - "+ExcelUtils.readData("FolderTemp", 5, 1)+" "+"Folder.");
							}
						}else{
							appLog.error("Can not get Folder ID of "+ExcelUtils.readData("FolderTemp", 5, 1)+" "+"Folder.");
							sa.assertTrue(false,"Can not get Folder ID of "+ExcelUtils.readData("FolderTemp", 5, 1)+" "+"Folder.");
						}
						ThreadSleep(5000);
						//Verifying saving of folder template.
						if(click(driver, nim.getFolderTemplateSaveButton(10), "Save button", action.SCROLLANDBOOLEAN)){
							appLog.info("Successfully Click on Save button for template "+folderTemplateName+"T2"+"U2UP");
							if(click(driver, nim.getSaveConfirmationYesButton(15), "Yes button", action.SCROLLANDBOOLEAN)){
								appLog.info("Successfully clicked on Yes Button while saving "+folderTemplateName+"T2"+"U2UP"+"Template.");
								//Verifying Save folder Template after editing folders.
								//Standard Folder.
								if(nim.getFolderElement(ExcelUtils.readData("FolderTemp", 5, 1).split("\\(")[0].trim()+"UP".trim(), 10)==null){
									appLog.info("Successfully Deleted "+ExcelUtils.readData("FolderTemp", 5, 1).split("\\(")[0].trim()+"UP".trim()+"Folder.");
								}else{
									appLog.info("Folder "+ExcelUtils.readData("FolderTemp", 5, 1).split("\\(")[0].trim()+"UP".trim()+"is not deleted successfully.");
									sa.assertTrue(false, "Folder "+ExcelUtils.readData("FolderTemp", 5, 1).split("\\(")[0].trim()+"UP".trim()+"is not deleted successfully.");
								}
								
								//Shared Folder
								if(nim.getFolderElement(ExcelUtils.readData("FolderTemp", 3, 1).split("\\(Sh")[0].trim()+"UP"+" "+"(Shared)".trim(), 10)==null){
									appLog.info("Successfully Deleted "+ExcelUtils.readData("FolderTemp", 3, 1).split("\\(Sh")[0].trim()+"UP"+" "+"(Shared)".trim()+"Folder.");
								}else{
									appLog.info("Folder "+ExcelUtils.readData("FolderTemp", 3, 1).split("\\(Sh")[0].trim()+"UP"+" "+"(Shared)".trim()+"is not deleted successfully.");
									sa.assertTrue(false, "Folder "+ExcelUtils.readData("FolderTemp", 3, 1).split("\\(Sh")[0].trim()+"UP"+" "+"(Shared)".trim()+"is not deleted successfully.");
								}

								//Internal Folder
								if(nim.getFolderElement(ExcelUtils.readData("FolderTemp", 4, 1).split("\\(In")[0].trim()+"UP"+" "+"(Internal)".trim(), 10)==null){
									appLog.info("Successfully Deleted "+ExcelUtils.readData("FolderTemp", 4, 1).split("\\(In")[0].trim()+"UP"+" "+"(Internal)".trim()+"Folder.");
								}else{
									appLog.info("Folder "+ExcelUtils.readData("FolderTemp", 4, 1).split("\\(In")[0].trim()+"UP"+" "+"(Internal)".trim()+"is not deleted successfully.");
									sa.assertTrue(false, "Folder "+ExcelUtils.readData("FolderTemp", 4, 1).split("\\(In")[0].trim()+"UP"+" "+"(Internal)".trim()+"is not deleted successfully.");
								}
								
								//Common Folder
								if(nim.getFolderElement(ExcelUtils.readData("FolderTemp", 2, 1).split("\\(Co")[0].trim()+"UP"+" "+"(Common)".trim(), 10)==null){
									appLog.info("Successfully Deleted "+ExcelUtils.readData("FolderTemp", 2, 1).split("\\(Co")[0].trim()+"UP"+" "+"(Common)".trim()+"Folder.");
								}else{
									appLog.info("Folder "+ExcelUtils.readData("FolderTemp", 2, 1).split("\\(Co")[0].trim()+"UP"+" "+"(Common)".trim()+"is not deleted successfully.");
									sa.assertTrue(false, "Folder "+ExcelUtils.readData("FolderTemp", 2, 1).split("\\(Co")[0].trim()+"UP"+" "+"(Common)".trim()+"is not deleted successfully.");
								}
								
							}else{
								appLog.error("Can not Click on Yes Button while saving Folder Template :"+folderTemplateName+"T2"+"U2UP");
								sa.assertTrue(false, "Can not Click on Yes Button while saving Folder Template :"+folderTemplateName+"T2"+"U2UP");
							}
						}else{
							appLog.error("Can not Click Template"+folderTemplateName+"T2"+"U2UP"+" Save button.");
							sa.assertTrue(false, "Can not Click Template"+folderTemplateName+"T2"+"U2UP"+" Save button.");
						}
					}else{
						appLog.error("Can not Click on Edit icon for "+folderTemplateName+"T2"+"U2UP");
						sa.assertTrue(false, "Can not Click on Edit icon for "+folderTemplateName+"T2"+"U2UP");
					}
					}else{
						appLog.error("Can not Click on Template Name.");
						sa.assertTrue(false, "Can not Click on Template Name.");
					}
				}else{
					appLog.error("Can not select \"All Templates\"");
					sa.assertTrue(false, "Can not select \"All Templates\"");
				}
			}else{
				appLog.error("Can not click on Folder Template Link.");
				sa.assertTrue(false, "Can not click on Folder Template Link.");
			}
			switchToDefaultContent(driver);
		}else{
			appLog.error("Can not click on NIM Tab");
			sa.assertTrue(false, "Can not click on NIM Tab");
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}
	
	@Test
	public void M11tc023_CreateTemplateFromUser2AndVerifyIt(){
		
		String date = getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.CRMLogin(Org3CRMUser2EmailID, adminPassword);
		if (nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, nim.getFrame( PageName.NavatarInvestorManager, 30));
			if(nim.clickOnSideMenusTab(sideMenu.FolderTemplates)){
			if (nim.getdefaultFolderTemplateMessage(20).getText().trim().equalsIgnoreCase(NIMPageErrorMessage.FolderTemplatenoDataToDisplay)) {
				appLog.info(NIMPageErrorMessage.FolderTemplatenoDataToDisplay+"is successfully verified.");
			}
			else {
				appLog.error(NIMPageErrorMessage.FolderTemplatenoDataToDisplay+"is not verified.");
				sa.assertTrue(false,NIMPageErrorMessage.FolderTemplatenoDataToDisplay+"is not verified.");
			}
			if(selectVisibleTextFromDropDown(driver, nim.getDropdownTempType(30), "All Templates", "All Templates")) {
				appLog.info("successfully selected all templates");
				
				if(nim.createFolderTemplate("FolderTemp", folderTemplateName+"U1", folderTemplateName+"Desc U1", 60)) {
					appLog.info("folder template"+folderTemplateName+" is created.");

					if(click(driver, nim.getGoBackLink(20), "Go Back Link", action.SCROLLANDBOOLEAN)){
						if(nim.verifyfolderTemplateGrid(folderTemplateName+"U1", folderTemplateName+"Desc U1",Org3CRMUser2UpdatedFirstName2+" "+Org3CRMUser2UpdatedLastName2,date)){
							appLog.info(folderTemplateName+"U1"+"folder template details are verified in grid.");
						}else{
							appLog.error(folderTemplateName+"U1"+"folder template details are not verified in grid.");
							sa.assertTrue(false,folderTemplateName+"U1"+"folder template details are not verified in grid.");
						}
						String[] recordCount = nim.getRecordsCount(60).getText().trim().split(" ");

						if (recordCount[1].equals("1")) {
							appLog.info("Record Count is verified");
						}else{
							appLog.info("Record Count is not verified");
							sa.assertTrue(false, "Record Count is not verified");
						}
					}else{
						appLog.error("Can not click on Go back link.");
						sa.assertTrue(false, "Can not click on Go back link.");
					}
				}
				else {
					appLog.error(folderTemplateName+"U1"+"folder template can't be created.");
					sa.assertTrue(false, folderTemplateName+"U1"+"folder template can't be created.");
				}	
			}
			else {
				appLog.error("Can not select all templates.");
				sa.assertTrue(false, "Can not select all templates.");
			}
			if(selectVisibleTextFromDropDown(driver,nim.getDropdownTempType(30),"All Templates" , "All Templates")){
				String[][] templatedata = {
						{folderTemplateName+"U1", folderTemplateName+"Desc U1",Org3CRMUser2UpdatedFirstName2+" "+Org3CRMUser2UpdatedLastName2,date},
						{folderTemplateName+"T1"+"U2", folderTemplateName+"Desc U2",Org3CRMUser3FirstName+" "+Org3CRMUser3LastName,date},
						{folderTemplateName+"T2"+"U2UP", folderTemplateName+"Desc U2UP",Org3CRMUser3FirstName+" "+Org3CRMUser3LastName,date}
				};
				for (int i = 0; i < templatedata.length; i++) {
					if (nim.verifyfolderTemplateGrid(templatedata[i][0], templatedata[i][1], templatedata[i][2],
							templatedata[i][3])) {
						appLog.info("Folder Template Grid Data is verified for :" + templatedata[i][0]);
					} else {
						appLog.info("Folder Template Grid Data is not verified for :" + templatedata[i][0]);
						sa.assertTrue(false, "Folder Template Grid Data is not verified for :" + templatedata[i][0]);
					}
				}
				String[] recordCount = nim.getRecordsCount(60).getText().trim().split(" ");
				if (recordCount[1].equals("3")) {
					appLog.info("Record Count is verified");
				}else{
					appLog.info("Record Count is not verified");
					sa.assertTrue(false, "Record Count is not verified");
				}
			}else{
				appLog.error("Can not select all templates.");
				sa.assertTrue(false, "Can not select all templates.");
			}

			if(selectVisibleTextFromDropDown(driver,nim.getDropdownTempType(30),"Other Users' Templates" , "Other Users' Templates")){
				String[][] templatedata = {
						{folderTemplateName+"T1"+"U2", folderTemplateName+"Desc U2",Org3CRMUser3FirstName+" "+Org3CRMUser3LastName,date},
						{folderTemplateName+"T2"+"U2UP", folderTemplateName+"Desc U2UP",Org3CRMUser3FirstName+" "+Org3CRMUser3LastName,date}
				};
				for (int i = 0; i < templatedata.length; i++) {
					if (nim.verifyfolderTemplateGrid(templatedata[i][0], templatedata[i][1], templatedata[i][2],
							templatedata[i][3])) {
						appLog.info("Folder Template Grid Data is verified for :" + templatedata[i][0]);
					} else {
						appLog.info("Folder Template Grid Data is not verified for :" + templatedata[i][0]);
						sa.assertTrue(false, "Folder Template Grid Data is not verified for :" + templatedata[i][0]);
					}
				}
				String[] recordCount = nim.getRecordsCount(60).getText().trim().split(" ");
				if (recordCount[1].equals("2")) {
					appLog.info("Record Count is verified");
				}else{
					appLog.info("Record Count is not verified");
					sa.assertTrue(false, "Record Count is not verified");
				}
			 }else{
				appLog.error("Can not select Other Users' templates.");
				sa.assertTrue(false, "Can not select Other Users' templates.");
			 }
			}else{
				appLog.error("Can not click on Folder Template Link.");
				sa.assertTrue(false, "Can not click on Folder Template Link.");
			}
			switchToDefaultContent(driver);
		}else{
			appLog.error("Can not click on NIM Tab");
			sa.assertTrue(false, "Can not click on NIM Tab");
		}

		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}
	
	@Test
	public void M11tc024_VerifyErrorMessageforEditandDeleteTemplate(){
		
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		
		lp.CRMLogin(Org3CRMUser3EmailID, adminPassword);
		if (nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, nim.getFrame( PageName.NavatarInvestorManager, 30));
			if(selectVisibleTextFromDropDown(driver,nim.getDropdownTempType(30),"Other Users' Templates" , "Other Users' Templates")){
				if(click(driver, nim.getfoldertemplatelink(folderTemplateName+"U1",20), "Folder Template link", action.SCROLLANDBOOLEAN)){
					//verify click on template link.
					appLog.info("Successfully clicked on Template Link.");
					//Edit button error message
					if(click(driver, nim.getFolderTemplateEditIcon(30), "Edit icon", action.SCROLLANDBOOLEAN)){
						if(nim.getFolderTemplateErrorPermissionHeader(30).getText().trim().equalsIgnoreCase(NIMPageErrorMessage.InsufficientHeader)){
							appLog.info("Insufficient Header is available.");
						}else{
							appLog.error("Folder Template Header is not available.");
							sa.assertTrue(false, "Folder Template Header is not available.");
						}
						if(nim.getFolderTemplateErrorPermissionHeadercrossicon(10)!=null){
							appLog.info("Insufficient Error cross icon is available.");
						}else{
							appLog.error("Insufficient Error cross icon is not available.");
							sa.assertTrue(false, "Insufficient Error cross icon is not available.");
						}
						if(nim.getFolderTemplateErrorPermissionHeaderclosebutton(10)!=null){
							appLog.info("Insufficient Error cross icon is available.");
						}else{
							appLog.error("Insufficient Error close button is not available.");
							sa.assertTrue(false, "Insufficient Error close button is not available.");
						}
						if(nim.getfoldertemplateerrormessage(10).getText().trim().equalsIgnoreCase(NIMPageErrorMessage.FolderTemplateEditInsufficientErrorMessage)){
							appLog.info("Insufficient Error message is verified Successfully verified while editing Folder Template. Expected:>>"+NIMPageErrorMessage.FolderTemplateEditInsufficientErrorMessage+"Actual:>"+nim.getfoldertemplateerrormessage(10).getText().trim());
						}else{
							appLog.error("Insufficient Error message is not verified Successfully. Expected:>>"+NIMPageErrorMessage.FolderTemplateEditInsufficientErrorMessage+"Actual:>"+nim.getfoldertemplateerrormessage(10).getText().trim());
							sa.assertTrue(false, "Insufficient Error message is not verified Successfully. Expected:>>"+NIMPageErrorMessage.FolderTemplateEditInsufficientErrorMessage+"Actual:>"+nim.getfoldertemplateerrormessage(10).getText().trim());
						}

						if(click(driver, nim.getFolderTemplateErrorPermissionHeadercrossicon(10), "Insufficient Cross icon", action.SCROLLANDBOOLEAN)){
							if(nim.getFolderTemplateErrorPermissionHeader(10)==null){
								appLog.info("Successfully clicked on Insufficient Error pop up cross icon.");
							}else{
								appLog.error("Insufficient Error pop up is not closed.");
								sa.assertTrue(false, "Insufficient Error pop up is not closed.");
							}
						}else{
							appLog.error("Insufficient error cross icon is not clickable.");
							sa.assertTrue(false, "Insufficient error cross icon is not clickable.");
						}
					}else{
						appLog.error("Folder Template Edit Icon is not clickable.");
						sa.assertTrue(false, "Folder Template Edit Icon is not clickable.");
					}

					if(click(driver, nim.getFolderTemplateEditIcon(30), "Edit icon", action.SCROLLANDBOOLEAN)){
						if(click(driver, nim.getFolderTemplateErrorPermissionHeaderclosebutton(10), "Insufficient close button", action.SCROLLANDBOOLEAN)){
							if(nim.getFolderTemplateErrorPermissionHeader(10)==null){
								appLog.info("Successfully clicked on Insufficient Error pop up close button.");
							}else{
								appLog.error("Insufficient Error pop up is not closed.");
								sa.assertTrue(false, "Insufficient Error pop up is not closed.");
							}
						}else{
							appLog.error("Insufficient error close button is not clickable.");
							sa.assertTrue(false, "Insufficient error close button is not clickable.");
						}

					}else{
						appLog.error("Folder Template Edit Icon is not clickable.");
						sa.assertTrue(false, "Folder Template Edit Icon is not clickable.");
					}

					//Delete button error message
					if(click(driver, nim.getDeleteTempBtn(30), "Delete Button", action.SCROLLANDBOOLEAN)){
						if(nim.getFolderTemplateErrorPermissionHeaderdelete(30).getText().trim().equalsIgnoreCase(NIMPageErrorMessage.InsufficientHeader)){
							appLog.info("Insufficient Header is available.");
						}else{
							appLog.error("Folder Template Header is not available.");
							sa.assertTrue(false, "Folder Template Header is not available.");
						}
						if(nim.getFolderTemplateErrorPermissionHeadercrossicondelete(10)!=null){
							appLog.info("Insufficient Error cross icon is available.");
						}else{
							appLog.error("Insufficient Error cross icon is not available.");
							sa.assertTrue(false, "Insufficient Error cross icon is not available.");
						}
						if(nim.getFolderTemplateErrorPermissionHeaderclosebuttondelete(10)!=null){
							appLog.info("Insufficient Error cross icon is available.");
						}else{
							appLog.error("Insufficient Error close button is not available.");
							sa.assertTrue(false, "Insufficient Error close button is not available.");
						}
						if(nim.getfoldertemplateerrormessagedelete(10).getText().trim().equalsIgnoreCase(NIMPageErrorMessage.FolderTemplateDeleteInsufficientErrorMessage)){
							appLog.info("Insufficient Error message is verified Successfully while Deleting Folder Template. Expected:>>"+NIMPageErrorMessage.FolderTemplateDeleteInsufficientErrorMessage+"Actual:>"+nim.getfoldertemplateerrormessagedelete(10).getText().trim());
						}else{
							appLog.error("Insufficient Error message is not verified Successfully. Expected:>>"+NIMPageErrorMessage.FolderTemplateDeleteInsufficientErrorMessage+"Actual:>"+nim.getfoldertemplateerrormessagedelete(10).getText().trim());
							sa.assertTrue(false, "Insufficient Error message is not verified Successfully. Expected:>>"+NIMPageErrorMessage.FolderTemplateDeleteInsufficientErrorMessage+"Actual:>"+nim.getfoldertemplateerrormessagedelete(10).getText().trim());
						}

						if(click(driver, nim.getFolderTemplateErrorPermissionHeadercrossicondelete(10), "Insufficient Cross icon", action.SCROLLANDBOOLEAN)){
							if(nim.getFolderTemplateErrorPermissionHeaderdelete(10)==null){
								appLog.info("Successfully clicked on Insufficient Error pop up cross icon.");
							}else{
								appLog.error("Insufficient Error pop up is not closed.");
								sa.assertTrue(false, "Insufficient Error pop up is not closed.");
							}
						}else{
							appLog.error("Insufficient error cross icon is not clickable.");
							sa.assertTrue(false, "Insufficient error cross icon is not clickable.");
						}

						if(click(driver, nim.getDeleteTempBtn(30), "Delete Button", action.SCROLLANDBOOLEAN)){
							if(click(driver, nim.getFolderTemplateErrorPermissionHeaderclosebuttondelete(10), "Insufficient close button", action.SCROLLANDBOOLEAN)){
								if(nim.getFolderTemplateErrorPermissionHeaderdelete(10)==null){
									appLog.info("Successfully clicked on Insufficient Error pop up close button.");
								}else{
									appLog.error("Insufficient Error pop up is not closed.");
									sa.assertTrue(false, "Insufficient Error pop up is not closed.");
								}
							}else{
								appLog.error("Insufficient error close button is not clickable.");
								sa.assertTrue(false, "Insufficient error close button is not clickable.");
							}
						}else{
							appLog.error("Folder Template Delete button is not clickable.");
							sa.assertTrue(false, "Folder Template Delete button is not clickable.");
						}
					}else{
						appLog.error("Folder Template Delete Button is not clickable.");
						sa.assertTrue(false, "Folder Template Delete Button is not clickable.");
					}
				}else{
					appLog.error("Can not Click on Template Name.");
					sa.assertTrue(false, "Can not Click on Template Name.");
				}
			}else{
				appLog.error("Can not select Other Users' templates.");
				sa.assertTrue(false, "Can not select Other Users' templates.");
			}
			switchToDefaultContent(driver);
		}else{
			appLog.error("Can not click on NIM Tab");
			sa.assertTrue(false, "Can not click on NIM Tab");
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}
	
	@Test
	public void M11tc025_VerifySortingInTemplateGrid(){

		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();

		lp.CRMLogin(Org3CRMUser2EmailID, adminPassword);
		if (nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, nim.getFrame( PageName.NavatarInvestorManager, 30));
			if(nim.clickOnSideMenusTab(sideMenu.FolderTemplates)){
				if(selectVisibleTextFromDropDown(driver,nim.getDropdownTempType(30),"All Templates" , "All Templates")){

					//Verifying sorting on 'Created On' column
					/******************Created On Sorting********************/
					if(nim.getcreatedOnSortingIcon(10)!=null){
						appLog.info("Default Sorting on 'Created On' is verified.");
						//Verifying default sorting icon on Created On Column
					}else{
						appLog.error("Default Sorting on 'Created On' is not verified.");
						sa.assertTrue(false, "Default Sorting on 'Created On' is not verified.");
					}

					List<WebElement> dataInColumn = FindElements(driver, "//span[contains(@id,'templateGrid-cell-3')]/span", "Created on date column data.");
					if(checkSorting(driver, SortOrder.Decending, dataInColumn)){
						appLog.info("Created on date data is in descending order.");
					} else {
						appLog.error("Created on date data is not in descending order.");
						sa.assertTrue(false,"Created on date data is not in descending order.");
					}

					if(click(driver, nim.getcreatedOnSortingIcon(30), "Created On Sorting icon", action.SCROLLANDBOOLEAN)){
						dataInColumn = FindElements(driver, "//span[contains(@id,'templateGrid-cell-3')]/span", "Created on date column data.");
						if(checkSorting(driver, SortOrder.Assecending, dataInColumn)){
							appLog.info("Created on date data is in Ascending order.");
						} else {
							appLog.error("Created on date data is not in Ascending order.");
							sa.assertTrue(false,"Created on date data is not in Ascending order.");
						}
					}else{
						appLog.error("Created on icon is not clickable.");
						sa.assertTrue(false,"Created on icon is not clickable.");
					}

					//Verifying sorting on Created By column
					/******************Created By Sorting**********************/
					if(click(driver, nim.getcreatedByColumnHeader(30), "Created By Column", action.SCROLLANDBOOLEAN)){
						if(nim.getcreatedBySortingIcon(10)!=null){
							appLog.info("Sorting icon on 'Created By' is verified.");
							//Verifying sorting icon on Created By Column
						}else{
							appLog.error("Sorting icon on 'Created By' is not verified.");
							sa.assertTrue(false, "Sorting icon on 'Created By' is not verified.");
						}

						dataInColumn = FindElements(driver, "//span[contains(@id,'templateGrid-cell-2')]/span", "Created By date column data.");
						if(checkSorting(driver, SortOrder.Assecending, dataInColumn)){
							appLog.info("Created By data is in Ascending order.");
						} else {
							appLog.error("Created By data is not in Ascending order.");
							sa.assertTrue(false,"Created By data is not in Ascending order.");
						}
					}else{
						appLog.error("Created By icon is not clickable.");
						sa.assertTrue(false,"Created By icon is not clickable.");
					}

					if(click(driver, nim.getcreatedBySortingIcon(30), "Created By Sorting icon", action.SCROLLANDBOOLEAN)){
						dataInColumn = FindElements(driver, "//span[contains(@id,'templateGrid-cell-2')]/span", "Created By date column data.");
						if(checkSorting(driver, SortOrder.Decending, dataInColumn)){
							appLog.info("Created By data is in Descending order.");
						} else {
							appLog.error("Created on date data is not in Descending order.");
							sa.assertTrue(false,"Created on date data is not in Descending order.");
						}
					}else{
						appLog.error("Created By icon is not clickable.");
						sa.assertTrue(false,"Created By icon is not clickable.");
					}


					/******************Template Name Sorting***********************/
					//Verifying sorting on Template Name column
					if(click(driver, nim.gettemplateNameHeader(30), "Template Name Column", action.SCROLLANDBOOLEAN)){
						if(nim.getTemplateNameSortingIcon(10)!=null){
							appLog.info("Sorting icon on 'Template Name' is verified.");
							//Verifying sorting icon on Template Name Column
						}else{
							appLog.error("Sorting icon on 'Template Name' is not verified.");
							sa.assertTrue(false, "Sorting icon on 'Template Name' is not verified.");
						}

						dataInColumn = FindElements(driver, "//span[contains(@id,'templateGrid-cell-0')]/span", "Template Name date column data.");
						if(checkSorting(driver, SortOrder.Assecending, dataInColumn)){
							appLog.info("Template Name data is in Ascending order.");
						} else {
							appLog.error("Template Name data is not in Ascending order.");
							sa.assertTrue(false,"Template Name data is not in Ascending order.");
						}
					}else{
						appLog.error("Template Name icon is not clickable.");
						sa.assertTrue(false,"Template Name icon is not clickable.");
					}

					if(click(driver, nim.getTemplateNameSortingIcon(30), "Template Name Sorting icon", action.SCROLLANDBOOLEAN)){
						dataInColumn = FindElements(driver, "//span[contains(@id,'templateGrid-cell-0')]/a", "Template Name date column data.");
						if(checkSorting(driver, SortOrder.Decending, dataInColumn)){
							appLog.info("Template Name date data is in Descending order.");
						} else {
							appLog.error("Template Name date data is not in Descending order.");
							sa.assertTrue(false,"Template Name date data is not in Descending order.");
						}
					}else{
						appLog.error("Template Name icon is not clickable.");
						sa.assertTrue(false,"Template Name icon is not clickable.");
					}
				}else{
					appLog.error("Can not select Other Users' templates.");
					sa.assertTrue(false, "Can not select Other Users' templates.");
				}
			}else{
				appLog.error("Can not click on Folder Template Link.");
				sa.assertTrue(false, "Can not click on Folder Template Link.");
			}
			switchToDefaultContent(driver);
		}else{
			appLog.error("Can not click on NIM Tab");
			sa.assertTrue(false, "Can not click on NIM Tab");
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
	}
	
	@Test
	public void M11tc026_1_VerifyEditingFolderTemplateFromDelegateAdmin(){

		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		
		lp.CRMLogin(Org3CRMUser2EmailID, adminPassword);
		if (nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, nim.getFrame( PageName.NavatarInvestorManager, 30));
			if(nim.clickOnSideMenusTab(sideMenu.FolderTemplates)){
				if(selectVisibleTextFromDropDown(driver,nim.getDropdownTempType(30),"All Templates" , "All Templates")){
					if(click(driver, nim.getfoldertemplatelink(folderTemplateName+"T2"+"U2UP",20), "Folder Template link", action.SCROLLANDBOOLEAN)){
						appLog.info("Successfully clicked on Template Link.");
						if(click(driver, nim.getFolderTemplateEditIcon(30), "Edit icon", action.SCROLLANDBOOLEAN)){
							//verify editing folder template from delegate admin.
							if(nim.getFolderTemplateSaveButton(20)!=null){
								appLog.info("Delegate Admin is able to Edit Folder Template.");
							}else{
								appLog.info("Delegate Admin is not able to Edit Folder Template.");
								sa.assertTrue(false, "Delegate Admin is not able to Edit Folder Template.");
							}
							if(click(driver, nim.getFolderTemplateSaveButton(20), "Folder Template Save Button", action.SCROLLANDBOOLEAN)){
								appLog.info("Succesfully clicked in Save button.");
								if(click(driver, nim.getSaveConfirmationYesButton(20), "Confirm Save yes button", action.SCROLLANDBOOLEAN)){
									if(nim.getGoBackLink(10)!=null){
										appLog.info("Successfully saved folder template from delegate admin.");

									}else{
										appLog.error("Folder Template is not saving successfully by delegate admin.");
										sa.assertTrue(false, "Folder Template is not saving successfully by delegate admin.");
									}
								}else{
									appLog.error("Yes button is not able to save folder template.");
									sa.assertTrue(false, "Yes button is not able to save folder template.");
								}
							}else{
								appLog.error("Save button is not available to save template.");
								sa.assertTrue(false, "Save button is not available to save template.");
							}
						}else{
							appLog.error("Folder Template Edit Icon is not clickable.");
							sa.assertTrue(false, "Folder Template Edit Icon is not clickable.");
						}
					}else{
						appLog.error("Can not Click on Template Name.");
						sa.assertTrue(false, "Can not Click on Template Name.");
					}
				}else{
					appLog.error("Can not select All templates.");
					sa.assertTrue(false, "Can not select All templates.");
				}
			}else{
				appLog.error("Can not click on Folder Template Link.");
				sa.assertTrue(false, "Can not click on Folder Template Link.");
			}
			switchToDefaultContent(driver);
		}else{
			appLog.error("Can not click on NIM Tab");
			sa.assertTrue(false, "Can not click on NIM Tab");
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		
	}

	@Test
	public void M11tc026_2_VerifyingDeletingFolderTemplateFromDelegateAdmin(){
		
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();

		lp.CRMLogin(Org3CRMUser2EmailID, adminPassword);
		if (nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, nim.getFrame( PageName.NavatarInvestorManager, 30));
			if(nim.clickOnSideMenusTab(sideMenu.FolderTemplates)){
				if(selectVisibleTextFromDropDown(driver,nim.getDropdownTempType(30),"All Templates" , "All Templates")){
					if(click(driver, nim.getfoldertemplatelink(folderTemplateName+"T2"+"U2UP",20), "Folder Template link", action.SCROLLANDBOOLEAN)){
						appLog.info("Successfully clicked on Template Link.");

						if(click(driver, nim.getDeleteTempBtn(20), "Delete Button", action.SCROLLANDBOOLEAN)){
							//verify Deleting folder template from delegate admin.
							//Verifying Confirm Delete Pop up.
							if(nim.getdeleteTemplateConfirmationHeader(20).getText().trim().equalsIgnoreCase(NIMPageErrorMessage.DeleteTemplateConfirmationHeader)){
								appLog.info("Delete Template Confirmation header is verified successfully.");
							}else{
								appLog.info("Delete Template Confirmation header is not verified successfully.");
								sa.assertTrue(false, "Delete Template Confirmation header is verified successfully.");
							}
							if(nim.getDeleteTemplateYesBtn(20)!=null){
								appLog.info("Yes button is available while deleting folder template from delegate admin.");
							}else{
								appLog.info("Yes button is not available while deleting folder template from delegate admin.");
								sa.assertTrue(false, "Yes button is not available while deleting folder template from delegate admin.");
							}
							if(nim.getDeleteTemplateNoBtn(20)!=null){
								appLog.info("No button is available while deleting folder template from delegate admin.");
							}else{
								appLog.info("No button is not available while deleting folder template from delegate admin.");
								sa.assertTrue(false, "No button is not available while deleting folder template from delegate admin.");
							}
							if(nim.getdeleteTemplatecrossIcon(20)!=null){
								appLog.info("Cross icon is available while deleting folder template from delegate admin.");
							}else{
								appLog.info("Cross icon is not available while deleting folder template from delegate admin.");
								sa.assertTrue(false, "Cross icon is not available while deleting folder template from delegate admin.");
							}
							if(nim.getdeleteTemplateConfirmationMsg(20).getText().trim().equalsIgnoreCase(NIMPageErrorMessage.FolderTemplateDeleteConfirmationMessage)){
								appLog.info("Delete Folder template confirmation message is verified successfully. Expected:>"+NIMPageErrorMessage.FolderTemplateDeleteConfirmationMessage+"Actual:>"+nim.getdeleteTemplateConfirmationMsg(20).getText().trim());
							}else{
								appLog.info("Delete Folder template confirmation message is not verified successfully. Expected:>"+NIMPageErrorMessage.FolderTemplateDeleteConfirmationMessage+"Actual:>"+nim.getdeleteTemplateConfirmationMsg(20).getText().trim());
								sa.assertTrue(false, "Delete Folder template confirmation message is not verified successfully. Expected:>"+NIMPageErrorMessage.FolderTemplateDeleteConfirmationMessage+"Actual:>"+nim.getdeleteTemplateConfirmationMsg(20).getText().trim());
							}

							if(click(driver,nim.getDeleteTemplateNoBtn(20), "No button", action.SCROLLANDBOOLEAN)){
								appLog.info("Successfully Click on No button.");
								//Verifying No button
								if(nim.getdeleteTemplateConfirmationHeader(10)==null){
									appLog.info("After Clicking No button "+NIMPageErrorMessage.FolderTemplateDeleteConfirmationMessage+"Pop up is closed successfully.");
								}else{
									appLog.error("After Clicking No button "+NIMPageErrorMessage.FolderTemplateDeleteConfirmationMessage+"Pop up is not closed.");
									sa.assertTrue(false, "After Clicking No button "+NIMPageErrorMessage.FolderTemplateDeleteConfirmationMessage+"Pop up is not closed.");
								}
							}else{
								appLog.error("Delete Folder Template No Button is not clickable.");
								sa.assertTrue(false, "Delete Folder Template No Button is not clickable.");
							}
						}else{
							appLog.error("Folder Template Delete Button is not available to click.");
							sa.assertTrue(false, "Folder Template Delete Button is not available to click.");
						}

						if(click(driver, nim.getDeleteTempBtn(20), "Delete Button", action.SCROLLANDBOOLEAN)){
							//verify Deleting folder template from delegate admin.
							if(click(driver,nim.getdeleteTemplatecrossIcon(20), "cross icon", action.SCROLLANDBOOLEAN)){
								//Verifying cross icon.
								appLog.info("Successfully Click on cross icon.");
								if(nim.getdeleteTemplateConfirmationHeader(10)==null){
									appLog.info("After Clicking cross icon "+NIMPageErrorMessage.FolderTemplateDeleteConfirmationMessage+"Pop up is closed successfully.");
								}else{
									appLog.error("After Clicking cross icon "+NIMPageErrorMessage.FolderTemplateDeleteConfirmationMessage+"Pop up is not closed.");
									sa.assertTrue(false, "After Clicking cross icon "+NIMPageErrorMessage.FolderTemplateDeleteConfirmationMessage+"Pop up is not closed.");
								}
							}else{
								appLog.error("Delete Folder Template cross icon is not clickable.");
								sa.assertTrue(false, "Delete Folder Template cross icon is not clickable.");
							}
						}else{
							appLog.error("Folder Template Delete Button is not available to click.");
							sa.assertTrue(false, "Folder Template Delete Button is not available to click.");
						}

						if(click(driver, nim.getDeleteTempBtn(20), "Delete Button", action.SCROLLANDBOOLEAN)){
							//verify Deleting folder template from delegate admin.
							if(click(driver,nim.getDeleteTemplateYesBtn(20), "yes button", action.SCROLLANDBOOLEAN)){
								appLog.info("Successfully Click on Yes button.");
								if (nim.getFolderTemplatesHead(30)!=null) {
									//Verifying redirection after deleting folder template.
									appLog.info("After Deleting Template User is directed to Folder Template page.");

								}else{
									appLog.error("After Deleting Template User is not directed to Folder Template page.");
									sa.assertTrue(false, "After Deleting Template User is not directed to Folder Template page.");
								}
								if(nim.getfoldertemplatelink(folderTemplateName+"T2"+"U2UP",10)==null){
									appLog.info("Successfully deleted "+folderTemplateName+"T2"+"U2UP"+"Template from delegate admin.");
									//Verifying availability of folder template after deleting.
								}else{
									appLog.error("Folder Template "+folderTemplateName+"T2"+"U2UP"+"is not deleted succesfully.");
									sa.assertTrue(false, "Folder Template "+folderTemplateName+"T2"+"U2UP"+"is not deleted succesfully.");
								}
								String[] recordCount = nim.getRecordsCount(60).getText().trim().split(" ");
								if(recordCount[1].equals("1")) {
									//Verifying record count after deleting folder template from delegate admin.
									appLog.info("Record Count is verified successfully after deleting folder Template "+folderTemplateName+"T2"+"U2UP");
								}else{
									appLog.info("Record Count is not verified successfully after deleting folder Template "+folderTemplateName+"T2"+"U2UP");
									sa.assertTrue(false, "Record Count is not verified successfully after deleting folder Template "+folderTemplateName+"T2"+"U2UP");
								}
							} else {
								appLog.error("Delete Folder Template Yes Button is not clickable.");
								sa.assertTrue(false, "Delete Folder Template Yes Button is not clickable.");
							}
						}else{
							appLog.error("Folder Template Delete Button is not available to click.");
							sa.assertTrue(false, "Folder Template Delete Button is not available to click.");
						}
					}else{
						appLog.error("Can not Click on Template Name.");
						sa.assertTrue(false, "Can not Click on Template Name.");
					}
				}else{
					appLog.error("Can not select All templates.");
					sa.assertTrue(false, "Can not select All templates.");
				}
			}else{
				appLog.error("Can not click on Folder Template Link.");
				sa.assertTrue(false, "Can not click on Folder Template Link.");
			}
			switchToDefaultContent(driver);
		}else{
			appLog.error("Can not click on NIM Tab");
			sa.assertTrue(false, "Can not click on NIM Tab");
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		
	}
	
	@Test
	public void M11tc027_VerifyUpdationofCreatedByAfterUpdatingMyProfile(){
		
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		WebElement ele = null;
		SoftAssert saa = new SoftAssert();
		lp.CRMLogin(Org3CRMUser3EmailID, adminPassword);
		if (click(driver, bp.getUserMenuTab(120), "User Menu", action.SCROLLANDBOOLEAN)) {
			if (click(driver, bp.getUserMenuSetupLink(120), "SetUp", action.SCROLLANDBOOLEAN)) {
				ThreadSleep(1000);
				if (click(driver, bp.getExpandManageUserIcon(120), "Manage User Icon", action.SCROLLANDBOOLEAN)) {
					if (click(driver, bp.getUsersLink(120), "User Link", action.SCROLLANDBOOLEAN)) {
						if (selectVisibleTextFromDropDown(driver, bp.getViewAllDropdownList(120), "View dropdown list",
								"Active Users")) {
							ThreadSleep(2000);
							ele = FindElement(driver,
									"//a[text()='" + Org3CRMUser3LastName+ "," + " " + Org3CRMUser3FirstName
									+ "']/..//preceding-sibling::td//a",
									"Edit icon", action.SCROLLANDBOOLEAN, 120);
							if (ele != null) {
								if (click(driver, ele, "Edit icon", action.SCROLLANDBOOLEAN)) {
									if (sendKeys(driver, bp.getUserFirstName(60), Org3CRMUser3FirstName + "NUP",
											"First updated name", action.SCROLLANDBOOLEAN)) {
										if (sendKeys(driver, bp.getUserLastName(60), Org3CRMUser3LastName + "NUP",
												"Last updated name", action.SCROLLANDBOOLEAN)) {
											if (click(driver, bp.getSaveButton(120), "Save Button",
													action.SCROLLANDBOOLEAN)) {
												appLog.info("Clicked on save button");
												ThreadSleep(2000);
												ele = FindElement(driver,
														"//a[text()='" + Org3CRMUser3LastName + "NUP" + "," + " "
																+ Org3CRMUser3FirstName + "NUP" + "']",
																"Updated CRM User name", action.SCROLLANDBOOLEAN, 120);

												if (ele != null) {
													appLog.info("Updated CRM User name is displaying");
												} else {
													appLog.info("Updated CRm User name is not displaying");
													saa.assertTrue(false, "Updated CRm User name is not displaying");
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
					if (sendKeys(driver, nim.getMyProfileFirstName(60), Org3CRMUser3FirstName + "UP", "first Name",
							action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, nim.getMyProfileLastName(60), Org3CRMUser3LastName + "UP", "Last Name",
								action.SCROLLANDBOOLEAN)) {
							if (click(driver, nim.getMyProfileSaveButton(60), "Save Button", action.SCROLLANDBOOLEAN)) {
								ThreadSleep(3000);
								String nameInViewMode = nim.getMyProfileNameInViewMode(60).getText().trim();
								if (nameInViewMode
										.equalsIgnoreCase(Org3CRMUser3FirstName + "UP" + " " + Org3CRMUser3LastName + "UP")) {
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

		//verifying Updated 'Created By' in Folder Template Grid.

		if(nim.clickOnSideMenusTab(sideMenu.FolderTemplates)){

			String date = getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
			String[] templatedata = {folderTemplateName+"T1"+"U2", folderTemplateName+"Desc U2",Org3CRMUser3FirstName+"UP"+" "+Org3CRMUser3LastName+"UP",date};

			if (nim.verifyfolderTemplateGrid(templatedata[0], templatedata[1], templatedata[2],
					templatedata[3])) {
				appLog.info("Folder Template Grid Data is verified for :" + templatedata[0]+"After Updating My Profile.");
			} else {
				appLog.info("Folder Template Grid Data is not verified for :" + templatedata[0]+"After Updating My Profile.");
				sa.assertTrue(false, "Folder Template Grid Data is not verified for :" + templatedata[0]+"After Updating My Profile.");
			}
		} else{
			appLog.info("Not able to click on Folder template link in side menu");
			sa.assertTrue(false, "Not able to click on Folder template link in side menu");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		
	}
	
	@Test
	public void M11tc028_VerifyDeleteTemplateFromUserSide(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		
		lp.CRMLogin(Org3CRMUser3EmailID, adminPassword);
		if (nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, nim.getFrame( PageName.NavatarInvestorManager, 30));
			if(nim.clickOnSideMenusTab(sideMenu.FolderTemplates)){
				if(selectVisibleTextFromDropDown(driver,nim.getDropdownTempType(30),"All Templates" , "All Templates")){
					if(click(driver, nim.getfoldertemplatelink(folderTemplateName+"T1"+"U2",20), "Folder Template link", action.SCROLLANDBOOLEAN)){
						appLog.info("Successfully clicked on Template Link.");

						//Verifying delete folder template From User.
						if(click(driver, nim.getDeleteTempBtn(20), "Delete Button", action.SCROLLANDBOOLEAN)){
							//Verifying Confirm Delete pop up.
							if(nim.getdeleteTemplateConfirmationHeader(20).getText().trim().equalsIgnoreCase(NIMPageErrorMessage.DeleteTemplateConfirmationHeader)){
								appLog.info("Delete Template Confirmation header is verified successfully.");
							}else{
								appLog.info("Delete Template Confirmation header is not verified successfully.");
								sa.assertTrue(false, "Delete Template Confirmation header is verified successfully.");
							}
							if(nim.getDeleteTemplateYesBtn(20)!=null){
								appLog.info("Yes button is available while deleting folder template from CRM User.");
							}else{
								appLog.info("Yes button is not available while deleting folder template from CRM User.");
								sa.assertTrue(false, "Yes button is not available while deleting folder template from CRM User.");
							}
							if(nim.getDeleteTemplateNoBtn(20)!=null){
								appLog.info("No button is available while deleting folder template from CRM User.");
							}else{
								appLog.info("No button is not available while deleting folder template from CRM User.");
								sa.assertTrue(false, "No button is not available while deleting folder template from CRM User.");
							}
							if(nim.getdeleteTemplatecrossIcon(20)!=null){
								appLog.info("Cross icon is available while deleting folder template from CRM User.");
							}else{
								appLog.info("Cross icon is not available while deleting folder template from CRM User.");
								sa.assertTrue(false, "Cross icon is not available while deleting folder template from CRM User.");
							}
							if(nim.getdeleteTemplateConfirmationMsg(20).getText().trim().equalsIgnoreCase(NIMPageErrorMessage.FolderTemplateDeleteConfirmationMessage)){
								appLog.info("Delete Folder template confirmation message is verified successfully. Expected:>"+NIMPageErrorMessage.FolderTemplateDeleteConfirmationMessage+"Actual:>"+nim.getdeleteTemplateConfirmationMsg(20).getText().trim());
							}else{
								appLog.info("Delete Folder template confirmation message is not verified successfully. Expected:>"+NIMPageErrorMessage.FolderTemplateDeleteConfirmationMessage+"Actual:>"+nim.getdeleteTemplateConfirmationMsg(20).getText().trim());
								sa.assertTrue(false, "Delete Folder template confirmation message is not verified successfully. Expected:>"+NIMPageErrorMessage.FolderTemplateDeleteConfirmationMessage+"Actual:>"+nim.getdeleteTemplateConfirmationMsg(20).getText().trim());
							}
							if(click(driver,nim.getDeleteTemplateYesBtn(20), "yes button", action.SCROLLANDBOOLEAN)){
								appLog.info("Successfully Click on Yes button.");
								if (nim.getFolderTemplatesHead(30)!=null) {
									//Verifying redirection of user after deleting folder template by crm user.
									appLog.info("After Deleting Template By CRM User, User is directed to Folder Template page.");
								}else{
									appLog.error("After Deleting Template User is not directed to Folder Template page.");
									sa.assertTrue(false, "After Deleting Template User is not directed to Folder Template page.");
								}
								if(nim.getfoldertemplatelink(folderTemplateName+"T1"+"U2",10)==null){
									//verifying availability of folder template after deleting.
									appLog.info("Successfully deleted "+folderTemplateName+"T1"+"U2"+"Template from delegate admin.");
								}else{
									appLog.error("Folder Template "+folderTemplateName+"T1"+"U2"+"is not deleted succesfully.");
									sa.assertTrue(false, "Folder Template "+folderTemplateName+"T1"+"U2"+"is not deleted succesfully.");
								}
								String[] recordCount = nim.getRecordsCount(60).getText().trim().split(" ");
								if(recordCount[1].equals("0")) {
									//Verifying record count after deleting template from CRM User.
									appLog.info("Record Count is verified successfully after deleting folder Template "+folderTemplateName+"T1"+"U2");
								}else{
									appLog.info("Record Count is not verified successfully after deleting folder Template "+folderTemplateName+"T1"+"U2");
									sa.assertTrue(false, "Record Count is not verified successfully after deleting folder Template "+folderTemplateName+"T1"+"U2");
								}
							} else {
								appLog.error("Delete Folder Template Yes Button is not clickable.");
								sa.assertTrue(false, "Delete Folder Template Yes Button is not clickable.");
							}
						}else{
							appLog.error("Folder Template Delete Button is not available to click.");
							sa.assertTrue(false, "Folder Template Delete Button is not available to click.");
						}
					}else{
						appLog.error("Can not Click on Template Name.");
						sa.assertTrue(false, "Can not Click on Template Name.");
					}
				}else{
					appLog.error("Can not select \"All Templates\"");
					sa.assertTrue(false, "Can not select \"All Templates\"");
				}
			}else{
				appLog.error("Can not click on Folder Template Link.");
				sa.assertTrue(false, "Can not click on Folder Template Link.");
			}
			switchToDefaultContent(driver);
		}else{
			appLog.error("Can not click on NIM Tab");
			sa.assertTrue(false, "Can not click on NIM Tab");
		}
		lp.CRMlogout(environment, mode);
		sa.assertAll();
		

	}
	
	


}