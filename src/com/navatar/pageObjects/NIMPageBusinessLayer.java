/**
 * 
 */
package com.navatar.pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import com.navatar.generic.BaseLib;
import com.navatar.generic.CommonLib;
import com.navatar.generic.ExcelUtils;
import com.navatar.generic.SoftAssert;
import com.navatar.generic.CommonLib.EditViewMode;
import com.navatar.generic.CommonLib.FolderType;
import com.navatar.generic.CommonLib.ManageApprovalTabs;
import com.navatar.generic.CommonLib.PageName;
import com.navatar.generic.CommonLib.SortOrder;
import com.navatar.generic.CommonLib.TabName;
import com.navatar.generic.CommonLib.Workspace;
import com.navatar.generic.CommonLib.accessType;
import com.navatar.generic.CommonLib.action;
import com.navatar.generic.CommonLib.excelLabel;
import com.navatar.generic.CommonLib.sideMenu;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

import static com.navatar.generic.AppListeners.appLog;
import static com.navatar.generic.CommonLib.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * @author Parul Singh
 *
 */
public class NIMPageBusinessLayer extends NIMPage implements NIMPageErrorMessage {

	/**
	 * @param driver
	 */
	public NIMPageBusinessLayer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @author Parul Singh
	 * @param linkType
	 * @return true/false
	 */
	public boolean verifyNavatarSalesTeamLinkFunctionality(String linkType) {
		String activateWinPath = System.getProperty("user.dir") + "\\AutoIT\\ActivateMailWin.exe";
		String closeWinPath = System.getProperty("user.dir") + "\\AutoIT\\CloseMailWin.exe";
		String mailIDImagePath = System.getProperty("user.dir") + "\\AutoIT\\MailIDImage.jpeg";
		boolean flag = true;
		try {
			Process activateMailWin = Runtime.getRuntime().exec(activateWinPath);
			activateMailWin.waitFor();
			if (activateMailWin.exitValue() == 1) {
				appLog.info("Mail window is successfully launced.");
				System.out.println("kindly delete the mail id");
				// Scanner scan = new Scanner(System.in);
				// scan.nextLine();
				ThreadSleep(10000);
				Screen screen = new Screen();
				if (linkType.equalsIgnoreCase("NavatarSalesTeamLink")) {
					try {
						screen.find(mailIDImagePath);
						appLog.info("MailID is Successfully matched.");
						flag = true;
					} catch (FindFailed e) {
						appLog.info("MailID is not matched.");
						flag = false;
					}
				} else {
					appLog.info("Mail Pop is verified Succesfully.");
				}
				try {
					Process closeWin = Runtime.getRuntime().exec(closeWinPath);
					closeWin.waitFor();
					if (closeWin.exitValue() == 1) {
						appLog.info("Successfully closed the mail Window.");
					}
				} catch (IOException e) {
					appLog.info("Kindly check weather CloseMailWin.exe file is present at the location " + closeWinPath
							+ "\nKindly close mail window manually.");
				}
				return flag;
			} else {
				appLog.info("Mail Window is not opening.");
				// exit("Mail Window is not opening.");
				return false;
			}
		} catch (IOException | InterruptedException e) {
			appLog.info("Kindly check weather ActivateMailWin.exe file is present at the location " + activateWinPath);
			return false;
		}

	}

	/**
	 * @param pageName
	 * @return true/false
	 */
	public boolean verifyLandingPageText(String pageName) {
		String ele = trim(getText(driver, getLandingPageText(60), "Landing Page Text", action.SCROLLANDBOOLEAN));
		if (ele.equalsIgnoreCase(pageName)) {
			appLog.info("Landing Page Text Is verified for page." + pageName);
			return true;
		} else {
			appLog.info("Landing Page Text is not verified for page ." + pageName);
		}
		return false;
	}
	/**
	 * @author Akul Bhutani
	 * @return true/false
	 */
	public boolean findRegistrationSuccessfulPopup() {
	switchToFrame(driver, 30, getFrame(PageName.NavatarInvestorManager, 30));
	WebElement el = isDisplayed(driver, FindElement(driver, "//div[contains(text(),'Registration Successful')]", "registration successful popup for 2nd time registering user", action.SCROLLANDBOOLEAN, 30), "visibility", 30, "registration successful popup for 2nd time registering user");
	if (el!=null) {
		appLog.info("registration successful popup is being displayed, the user is being registered 2nd time");
		if (click(driver,getRegistrationSuccessfulCloseBtn(60), "registration successful close button", action.SCROLLANDBOOLEAN)) {
			appLog.info("successfully clicked close button for successful registration");
		}
		else {
			appLog.error("registration popup close button is not clickable");
		}
		switchToDefaultContent(driver);
		return true;
	}else {
		appLog.error("cannot find registration successful popup, the user needs to complete registration");
	}
	switchToDefaultContent(driver);
	return false;
	
}
	/**
	 * @author Akul Bhutani
	 * @param adminOrUser
	 * @param userFirstName
	 * @param userLastName
	 * @return true/false
	 */
	public boolean NIMRegistration(userType adminOrUser, String userFirstName, String userLastName) {
		switchToFrame(driver, 60, getFrame(PageName.NavatarInvestorManager, 30));
		// if admin only then start button is displayed
		if (adminOrUser == userType.SuperAdmin) {
			if (click(driver, getStartButton(60), "Start button", action.BOOLEAN)) {
				appLog.info("clicked on start button");
			} else {
				appLog.error("start button not found so cannot register " + adminOrUser.toString() + ": "
						+ userFirstName + " " + userLastName + "");
			}
		}
		if (userFirstName != null && userLastName != null) {
			if (sendKeys(driver, getRegisterPopupFirstName(60), userFirstName, "First name of user", action.BOOLEAN)) {
				appLog.info("pass value in first name text box : " + userFirstName);
				if (sendKeys(driver, getRegisterPopupLastName(60), userLastName, "Last name of user", action.BOOLEAN)) {
					appLog.info("pass value in last name text box : " + userLastName);
				} else {
					appLog.error("Last name text box not found so cannot register " + adminOrUser.toString() + ": "
							+ userFirstName + " " + userLastName + "");
				}
			} else {
				appLog.error("First name text box not found so cannot register " + adminOrUser.toString() + ": "
						+ userFirstName + " " + userLastName + "");
			}
		}
		if (click(driver, getNextButton(60), "Next button", action.SCROLLANDBOOLEAN)) {
			if (click(driver, getAllowButton(60), "Allow button", action.BOOLEAN)) {
				switchToFrame(driver, 30, getNIMTabParentFrame_Lightning(PageName.NavatarInvestorManager));
				if (switchToFrame(driver, 30, getNIMTabFrame(30))) {
				
				// if admin only then asks which users to add
				if (adminOrUser == userType.SuperAdmin) {

					if (click(driver, getRegisterPopup2Of2CompleteButton(60), "Complete button in Users adding popup",
							action.BOOLEAN)) {
					} else {
						appLog.error("Complete button cannot be clicked so cannot register " + adminOrUser.toString()
								+ ": " + userFirstName + " " + userLastName + "");
					}
				}
				if (click(driver, getRegistrationSuccessfulCloseBtn(60), "Register successfull close button",
						action.BOOLEAN)) {
					appLog.info("clicked on registration close button.");
					appLog.info(adminOrUser + " is successfully registered");
					switchToDefaultContent(driver);
					return true;
				} else {
					appLog.error("Registration close button is not clickable so cannot register "
							+ adminOrUser.toString() + ": " + userFirstName + " " + userLastName + "");
				}
				
			} 
				 else {
						appLog.error("Cannot find NIM tab frame so cannot register " + adminOrUser.toString() + ": "
								+ userFirstName + " " + userLastName + "");
					}
			}
				else {
				
				appLog.error("Allow button not clicked not found so cannot register " + adminOrUser.toString() + ": "
						+ userFirstName + " " + userLastName + "");
			}
		} else {
			appLog.error("Next button not clicked not found so cannot register " + adminOrUser.toString() + ": "
					+ userFirstName + " " + userLastName + "");
		}
		switchToDefaultContent(driver);
		return false;
	}

	//Lightning Method....
	
	public boolean NIMRegistration(String environment, String mode,userType adminOrUser, String userFirstName, String userLastName) {
		if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			switchToFrame(driver, 60, getNIMTabParentFrame_Lightning());
			ThreadSleep(5000);
		}else {
			appLog.error("Not able to switch to NIM Tab Parent Frame so cannot register "+adminOrUser.toString());
			return false;
		}
		switchToFrame(driver, 60, getFrame(PageName.NavatarInvestorManager, 30));
		// if admin only then start button is displayed
		if (adminOrUser == userType.SuperAdmin) {
			if (click(driver, getStartButton(60), "Start button", action.BOOLEAN)) {
				appLog.info("clicked on start button");
			} else {
				appLog.error("start button not found so cannot register " + adminOrUser.toString() + ": "
						+ userFirstName + " " + userLastName + "");
			}
		}
		if (userFirstName != null && userLastName != null) {
			if (sendKeys(driver, getRegisterPopupFirstName(60), userFirstName, "First name of user", action.BOOLEAN)) {
				appLog.info("pass value in first name text box : " + userFirstName);
				if (sendKeys(driver, getRegisterPopupLastName(60), userLastName, "Last name of user", action.BOOLEAN)) {
					appLog.info("pass value in last name text box : " + userLastName);
				} else {
					appLog.error("Last name text box not found so cannot register " + adminOrUser.toString() + ": "
							+ userFirstName + " " + userLastName + "");
				}
			} else {
				appLog.error("First name text box not found so cannot register " + adminOrUser.toString() + ": "
						+ userFirstName + " " + userLastName + "");
			}
		}
		if (click(driver, getNextButton(60), "Next button", action.SCROLLANDBOOLEAN)) {
			if (click(driver, getAllowButton(60), "Allow button", action.BOOLEAN)) {
				if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					for(int i=0; i<10; i++) {
						if(switchToFrame(driver, 60, getNIMTabParentFrame_Lightning())) {
							ThreadSleep(5000);
							break;
						}else {
							if(i==9) {
								appLog.error("Not able to switch to NIM Tab Parent Frame so cannot register "+adminOrUser.toString());
								return false;
							}
						}
					}
				}
				if (switchToFrame(driver, 50, getNIMTabFrame(50))) {
					// if admin only then asks which users to add
					if (adminOrUser == userType.SuperAdmin) {

						if (click(driver, getRegisterPopup2Of2CompleteButton(60), "Complete button in Users adding popup",
								action.BOOLEAN)) {
						} else {
							appLog.error("Complete button cannot be clicked so cannot register " + adminOrUser.toString()
							+ ": " + userFirstName + " " + userLastName + "");
						}
					}
					if (click(driver, getRegistrationSuccessfulCloseBtn(60), "Register successfull close button",
							action.BOOLEAN)) {
						appLog.info("clicked on registration close button.");
						appLog.info(adminOrUser + " is successfully registered");
						switchToDefaultContent(driver);
						return true;
					} else {
						appLog.error("Registration close button is not clickable so cannot register "
								+ adminOrUser.toString() + ": " + userFirstName + " " + userLastName + "");
					}

				} 
				else {
					appLog.error("Cannot find NIM tab frame so cannot register " + adminOrUser.toString() + ": "
							+ userFirstName + " " + userLastName + "");
				}
			}
			else {

				appLog.error("Allow button not clicked not found so cannot register " + adminOrUser.toString() + ": "
						+ userFirstName + " " + userLastName + "");
			}
		} else {
			appLog.error("Next button not clicked not found so cannot register " + adminOrUser.toString() + ": "
					+ userFirstName + " " + userLastName + "");
		}
		switchToDefaultContent(driver);
		return false;
	}

	
	
	
	/**
	 * @author Ankit Jaiswal
	 * @return true/false
	 */
	public boolean clickOnEditIcon() {
		try {
			if (click(driver, getEditIcon(60), "Edit Icon", action.BOOLEAN)) {
				appLog.info("clicked on Edit Icon Successfully.");
				return true;
			} else {
				appLog.error("Edit Icon is not clickable.");
				throw new Exception();
			}
		} catch (Exception e) {
			appLog.info("Trying once again...");
			for (int i = 0; i < 2; i++) {
				if (click(driver, getEditIcon(60), "Edit Icon", action.BOOLEAN)) {
					appLog.info("clicked on Edit Icon Successfully.");
					break;
				} else {
					if (i == 1) {
						appLog.error("Edit Icon is not clickable.");
						return false;
					}
				}
			}
			return true;
		}
	}
	/**
	 * @author Akul Bhutani
	 * @param userName
	 * @param access
	 * @return true/false
	 */
	public boolean giveAccessToUserInNIMTabFromAdmin(String userName, accessType access) {
		switchToFrame(driver, 30, getNIMTabFrame(30));
		//internal User Tab click
		if (clickOnEditIcon()) {
			if (access == accessType.InternalUserAccess) {
				WebElement ele = isDisplayed(driver,
						FindElement(driver,
								"//span[@id='grid_Users-rows']/span/span[3]/span[text()='" + userName
										+ "']/../..//input[@type='checkbox']",
								"Access checkbox of user" + userName, action.SCROLLANDBOOLEAN, 30),
						"Visibility", 60, " Access checkbox for " + userName);
				
				if (ele != null) {
					if(!isSelected(driver, ele, "checkbox for "+userName)) {
					scrollDownThroughWebelement(driver, ele, "");
					if (click(driver, ele, "Access for " + userName, action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on checkbox of " + userName);
						if (click(driver, getYesAccessButton(60), "Yes button for User access", action.BOOLEAN)) {
							appLog.info("internal user access granted successfully to user: " + userName);
							if(click(driver,getGoBackLink(30),"internal user go back link", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on go back link");
								driver.switchTo().defaultContent();
								return true;
							}else {
								appLog.error("Not able to click on go back link");
							}
						} else {
							appLog.error(
									"Yes button for popup not found so cannot provide internal user access to user: "
											+ userName);
						}
					} else {
						appLog.error(
								"Not able to click on internal user check box so cannot provide internal user access to user: "
										+ userName);
					}
					}
					else {
						appLog.info(userName+" was given IP access already by default");
						return true;
					}
				}
				
					else {
				
					appLog.error("Checkbox for " + userName
							+ " not found so cannot provide internal user access to user: " + userName);
				}
				
			} else if (access == accessType.AdminUserAccess) {
				WebElement ele_check = isDisplayed(driver,
						FindElement(driver,
								"//span[@id='grid_Users-rows']/span/span[3]/span[text()='" + userName
										+ "']/../..//input[@type='radio']",
								"Access checkbox of user" + userName, action.SCROLLANDBOOLEAN, 30),
						"Visibility", 60, " Access checkbox for " + userName);
				if (ele_check != null) {
					if (click(driver, ele_check, "Access for " + userName, action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on  admin user radio button of user: " + userName);
						if (click(driver, getYesAdminButton(60), "Yes button for Admin access", action.BOOLEAN)) {
							appLog.info("successfully provided admin user access to user: " + userName);
								driver.switchTo().defaultContent();
								return true;
						}
					}
				} else {
					appLog.error("user radio button is not visible so cannot provide admin user access to user: "
							+ userName);
				}
			}
		} else {
			appLog.error("Edit icon cannot be clickable so cannot provide " + access.toString() + " access to user: "
					+ userName);
		}
		switchToDefaultContent(driver);
		return false;
	}
	
	
	
	//Give Access to User Lightning Method ...
	public boolean giveAccessToUserInNIMTabFromAdmin(String environment, String mode,String userName, accessType access) {
		if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			switchToFrame(driver, 60, getNIMTabParentFrame_Lightning());
			ThreadSleep(5000);
		}else {
			appLog.error("Not able to switch to NIM Tab Parent Frame so cannot give access to user");
			return false;
		}
		switchToFrame(driver, 30, getNIMTabFrame(30));
		//internal User Tab click
		if (clickOnEditIcon()) {
			if (access == accessType.InternalUserAccess) {
				WebElement ele = isDisplayed(driver,
						FindElement(driver,
								"//span[@id='grid_Users-rows']/span/span[3]/span[text()='" + userName
										+ "']/../..//input[@type='checkbox']",
								"Access checkbox of user" + userName, action.SCROLLANDBOOLEAN, 30),
						"Visibility", 60, " Access checkbox for " + userName);
				
				if (ele != null) {
					if(!isSelected(driver, ele, "checkbox for "+userName)) {
					scrollDownThroughWebelement(driver, ele, "");
					if (click(driver, ele, "Access for " + userName, action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on checkbox of " + userName);
						if (click(driver, getYesAccessButton(60), "Yes button for User access", action.BOOLEAN)) {
							appLog.info("internal user access granted successfully to user: " + userName);
							if(click(driver,getGoBackLink(30),"internal user go back link", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on go back link");
								driver.switchTo().defaultContent();
								return true;
							}else {
								appLog.error("Not able to click on go back link");
							}
						} else {
							appLog.error(
									"Yes button for popup not found so cannot provide internal user access to user: "
											+ userName);
						}
					} else {
						appLog.error(
								"Not able to click on internal user check box so cannot provide internal user access to user: "
										+ userName);
					}
					}
					else {
						appLog.info(userName+" was given IP access already by default");
						return true;
					}
				}
				
					else {
				
					appLog.error("Checkbox for " + userName
							+ " not found so cannot provide internal user access to user: " + userName);
				}
				
			} else if (access == accessType.AdminUserAccess) {
				WebElement ele_check = isDisplayed(driver,
						FindElement(driver,
								"//span[@id='grid_Users-rows']/span/span[3]/span[text()='" + userName
										+ "']/../..//input[@type='radio']",
								"Access checkbox of user" + userName, action.SCROLLANDBOOLEAN, 30),
						"Visibility", 60, " Access checkbox for " + userName);
				if (ele_check != null) {
					if (click(driver, ele_check, "Access for " + userName, action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on  admin user radio button of user: " + userName);
						if (click(driver, getYesAdminButton(60), "Yes button for Admin access", action.BOOLEAN)) {
							appLog.info("successfully provided admin user access to user: " + userName);
								driver.switchTo().defaultContent();
								return true;
						}
					}
				} else {
					appLog.error("user radio button is not visible so cannot provide admin user access to user: "
							+ userName);
				}
			}
		} else {
			appLog.error("Edit icon cannot be clickable so cannot provide " + access.toString() + " access to user: "
					+ userName);
		}
		switchToDefaultContent(driver);
		return false;
	}

	/**
	 * @author Parul Singh
	 * @param Menu
	 * @return true/false
	 */
	public boolean clickOnSideMenusTab(sideMenu Menu) {
		String sideMenu = null;
		switch (Menu) {
		case InternalUsers:
			sideMenu ="Internal Users";
			break;
		case FolderTemplates:
			sideMenu ="Folder Templates";
			break;
		case ManageApprovals:
			sideMenu ="Manage Approvals";
			break;
		case Watermarking:
			sideMenu ="Watermarking";
			break;
		case FileDistributorSettings:
			sideMenu ="File Distributor Settings";
			break;
		case FileSplitterOptions:
			sideMenu ="File Splitter Options";
			break;
		case Profiles:
			sideMenu ="Profiles";
			break;
		case MyFirmProfile:
			sideMenu="My Firm";
			break;
		default:
			return false;
		}
		for (int i = 0; i < 2; i++) {
			if (Menu.toString().equalsIgnoreCase(Menu.InternalUsers.toString()) || Menu.toString().equalsIgnoreCase(Menu.FolderTemplates.toString())
					|| Menu.toString().equalsIgnoreCase(Menu.ManageApprovals.toString())) {
				if (click(driver,
						isDisplayed(driver,
								FindElement(driver, "//a[contains(@title,'" + sideMenu + "')]", sideMenu,
										action.SCROLLANDBOOLEAN, 30),
								"visibility", 30, sideMenu),
						sideMenu, action.SCROLLANDBOOLEAN)) {
					appLog.info("Clicked on" + sideMenu);
					WebElement ele = isDisplayed(driver, getLandingPageText(60), "visibility", 60, sideMenu);
					if (ele != null) {
						appLog.info("Successfully clicked on " + sideMenu);
						return true;
					}
				}
			} else if (Menu.toString().equalsIgnoreCase(Menu.Watermarking.toString()) || Menu.toString().equalsIgnoreCase(Menu.FileDistributorSettings.toString())) {
				if (click(driver,
						isDisplayed(driver,
								FindElement(driver, "(//a[contains(@title,'" + sideMenu + "')])[2]", sideMenu,
										action.SCROLLANDBOOLEAN, 30),
								"visibility", 30, sideMenu),
						sideMenu, action.SCROLLANDBOOLEAN)) {
					appLog.info("Clicked on" + sideMenu);
					WebElement ele = isDisplayed(driver, getLandingPageText(60), "visibility", 60, sideMenu);
					if (ele != null) {
						appLog.info("Successfully clicked on " + sideMenu);
						return true;
					}
				}
			} else if (Menu.toString().equalsIgnoreCase(Menu.Profiles.toString())) {
				if (click(driver,
						isDisplayed(driver,
								FindElement(driver, "(//a[contains(@title,'" + sideMenu + "')])[3]", sideMenu,
										action.SCROLLANDBOOLEAN, 30),
								"visibility", 30, sideMenu),
						sideMenu, action.SCROLLANDBOOLEAN)) {
					appLog.info("Clicked on" + sideMenu);
					WebElement ele = isDisplayed(driver, getLandingPageText(60), "visibility", 60, sideMenu);
					if (ele != null) {
						appLog.info("Successfully clicked on " + sideMenu);
						return true;
					}
				}
			} else if (Menu.toString().equalsIgnoreCase(Menu.MyFirmProfile.toString())) {
				if (click(driver,
						isDisplayed(driver,
								FindElement(driver, "(//a[contains(@title,'Profiles')])[3]", sideMenu,
										action.SCROLLANDBOOLEAN, 30),
								"visibility", 30, sideMenu),
						sideMenu, action.SCROLLANDBOOLEAN)) {
				if (click(driver,
						isDisplayed(driver,
								FindElement(driver, "(//a[contains(@title,'" + sideMenu + "')])[2]", sideMenu,
										action.SCROLLANDBOOLEAN, 30),
								"visibility", 30, sideMenu),
						sideMenu, action.SCROLLANDBOOLEAN)) {
					appLog.info("Clicked on" + sideMenu);
					WebElement ele = isDisplayed(driver, getLandingPageText(60), "visibility", 60, sideMenu);
					if (ele != null) {
						appLog.info("Successfully clicked on " + sideMenu);
						return true;
					}

				}
			}
			
		}
			 else if (Menu.toString().equalsIgnoreCase(Menu.FileSplitterOptions.toString())) {
					if (click(driver,
							isDisplayed(driver,
									FindElement(driver, "(//a[contains(@title,'" + sideMenu.toString() + "')])[2]", sideMenu,
											action.SCROLLANDBOOLEAN, 30),
									"visibility", 30, sideMenu),
							sideMenu, action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on" + sideMenu);
						WebElement ele = isDisplayed(driver, getLandingPageText(60), "visibility", 60, sideMenu);
						if (ele != null) {
							appLog.info("Successfully clicked on " + sideMenu);
							return true;
						}
					}
				}
		}
		return false;
	}

	/*public boolean verifyCheckboxManageApproval(EditViewMode mode, userType adminOrUser) {
	String userXpath="",checkboxXpath="/../span[contains(@id,'cell-1-')]";
	if (mode == EditViewMode.View) {
		if (adminOrUser == userType.SuperAdmin)
			userXpath = "//span[contains(@id,'grid_CRMAdmin-cell-0')]";
		else if (adminOrUser == userType.CRMUser)
			userXpath = "//span[contains(@id,'grid_Users-cell-0')]";
	}
	else if(mode == EditViewMode.Edit) {
		if (adminOrUser == userType.SuperAdmin)
			userXpath = "//span[contains(@id,'grid_CRMAdmin1-cell-0')]";
		else if (adminOrUser == userType.CRMUser)
			userXpath = "//span[contains(@id,'grid_Users1-cell-0')]";
	}

	WebElement ele = isDisplayed(driver, FindElement(driver, userXpath+checkboxXpath, "checkbox in front of "+adminOrUser.toString(), action.BOOLEAN, 30), "visibility", 30, "checkbox in front of "+adminOrUser.toString());
	if (ele.getAttribute("class").contains("aw-value-true")) {
	appLog.info("checkbox in front of ");
	return true;
	}
	else if(ele.getAttribute("class").contains("aw-value-false")) {
	return false;
	}
	return false;
	}

	 */

	/**
	 * @author Akul Bhutani
	 * @param userName
	 * @return WebElement checkbox
	 */
	public WebElement findCheckboxForUserInternalUsers(String userName) {
		String xpath = "//span//span[text()='"+userName+"']/../../span/input[@type='checkbox']";
		return isDisplayed(driver, FindElement(driver, xpath,"checkbox in front of "+userName, action.BOOLEAN, 30), "visibility", 30,"checkbox in front of "+userName );
	}

	/**
	 * @author Akul Bhutani
	 * @param userName
	 * @return WebElement checkbox
	 */
	public WebElement findCheckboxForUserInManageApproval(String userName) {
		String xpath = "(//span[text()='"+userName+"']/../span[contains(@id,'cell-1-')]/span)[2]/span[1]";
		return isDisplayed(driver, FindElement(driver, xpath, "checkbox in front of "+userName, action.BOOLEAN, 30), "visibility", 30, "checkbox in front of "+userName);
	}
	
	
	/**
	 * @author Ankit Jaiswal
	 * @param driver
	 * @param sortOrder
	 * @param elements
	 * @return true/false
	 */
	public boolean checkSortingCheckBox(WebDriver driver, SortOrder sortOrder, List<WebElement> elements){
		List<String> ts=new ArrayList<String>();
		List<String> actual=new ArrayList<String>();
		CommonLib compare= new CommonLib();
		List<WebElement> ele=elements;
		boolean flag=true;
		int j=0;
		for(int i=0;i<ele.size();i++){
			scrollDownThroughWebelement(driver, ele.get(i), "");
			ts.add(ele.get(i).getAttribute("class"));
		}
		actual.addAll(ts);
		Collections.sort(ts,compare);
		Iterator<String> i= ts.iterator();
		if(sortOrder.toString().equalsIgnoreCase("Decending")){
			j=ele.size()-1;
		}
		while(i.hasNext()){
			String a=i.next();
			if(a.equalsIgnoreCase(actual.get(j))){
				appLog.info("Order of column is matched "+"Expected: "+ a + "\tActual: "+actual.get(j));
			} else {
				appLog.info("Order of column din't match. "+"Expected: "+ a + "\tActual: "+actual.get(j));
				BaseLib.sa.assertTrue(false,"Contact name coloumn is not sorted in "+sortOrder.toString()+" order"+ "Expected: "+ a + "\tActual: "+actual.get(j));
				flag=false;
			}
			if(sortOrder.toString().equalsIgnoreCase("Decending")){
				j--;
			} else {
				j++;
			}
		} 
		return flag;
	}
	
	
	/**
	 * @author Akul Bhutani
	 * @param boolArr
	 * @return true/false
	 */
	public boolean verifyAllContinuous(boolean[] boolArr) {
		int i=0;
		boolean flag = true;
		int breakingAt=0;
		while(i!=boolArr.length) {
			if (boolArr[i] == true) {
				
			}
			else {
				breakingAt=i;
				System.err.println("found unchecked");
				break;
			}
			i++;
		}
		while(i!=boolArr.length) {
			if (boolArr[i] == false) {
				System.err.println(i+" is unchecked in second half");
			}
			else if(boolArr[i] == true) {
				appLog.error("sorting is not correct at "+i);
				flag = false;
				break;
			}
			i++;
		}
		return flag;
	}
	
	
	/**
	 * @author Akul Bhutani
	 * @return true/false
	 */
	public boolean verifySortingOnCheckbox() {
		List<WebElement> ele = null;
		ele=FindElements(driver, "//span[contains(@id,'cell-1-') and contains(@id,'grid_Users') and @title='']", "list of checkboxes manage approvals");
		boolean[] arr = new boolean[ele.size()];
		boolean flag1 = false,flag2 = false;
		for (int i = 0;i<ele.size();i++) {
			if (ele.get(i).getAttribute("class").contains("aw-value-true")) {
				arr[i] = true;
				flag1 = true;
			}
			else if (ele.get(i).getAttribute("class").contains("aw-value-false")) {
				arr[i] = false;
				flag2 = true;
			}
		}
		for (int i = 0;i<arr.length;i++) {
			if (arr[i]) {
				appLog.error("true");
			}
			else {
				appLog.error("false");
			}
		}
		if (flag1&&flag2) {
			appLog.info("both types of checkbox are present, so sorting willbe checked");
			if (verifyAllContinuous(arr)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			
			appLog.info("all checkboxes are same, so no need to check sorting");
			return true;
		}
	}
	
	/**
	 * @author Akul Bhutani
	 * @param mode
	 * @param userName
	 * @return true/false
	 */
	public boolean verifyCheckedOrNotManageApproval(EditViewMode mode, String userName) {
		String xpath = "";
		if (mode == EditViewMode.Edit) {
			xpath = "(//span[text()='"+userName+"']/../span[contains(@id,'cell-1-')])[2]";
		}
		else if(mode == EditViewMode.View) {
			xpath = "(//span[text()='"+userName+"']/../span[contains(@id,'cell-1-')])[1]";
		}
		WebElement ele = isDisplayed(driver, FindElement(driver, xpath, "checkbox in front of "+userName, action.BOOLEAN, 30), "visibility", 30, "checkbox in front of "+userName);
		if (ele.getAttribute("class").contains("aw-value-true")) {
			appLog.info("checkbox in front of "+userName+" is selected");
			return true;
		}
		else if(ele.getAttribute("class").contains("aw-value-false")) {
			appLog.info("checkbox in front of "+userName+" is not selected");
			return false;
		}
		return false;

	}

	/**
	 * @author Parul Singh
	 * @return true/false
	 */
	public boolean removeAllUserAccess(){
		if (clickOnEditIcon()) {
			List<WebElement> checkboxes = getAllInternalUsersCheckbox();
			if (!checkboxes.isEmpty()) {
				int i = 0;
				for (WebElement checkbox : checkboxes) {
					i++;
					if (isSelected(driver, checkbox, "Access checkbox")) {
						if (click(driver, checkbox, "Checkbox", action.SCROLLANDBOOLEAN)) {
							if (click(driver, getUserPermissionRemovalPopupYesButton(60),
									"User permission Removal Yes Button", action.SCROLLANDBOOLEAN)) {
								ThreadSleep(2000);
							} else {
								appLog.info("Not able to click on User permission Removal Yes button");
								return false;
							}
						} else {
							appLog.info("Not able to click on checkbox:" + i);
							return false;
						}
					} else {
						appLog.info("Checkbox is not selected:" + i);

					}
				}
				ThreadSleep(2000);
				checkboxes.clear();
				checkboxes = getAllInternalUsersCheckbox();
				int i1 = 0;
				if (!checkboxes.isEmpty()) {
					for (WebElement checkbox : checkboxes) {
						i1++;
						if (isSelected(driver, checkbox, "Access checkbox")) {
							appLog.info("Access has not been removed for all users:" + i1);
							return false;
						} else {
							appLog.info("Access has been removed for users successfully: " + i1);
						}
					}
				}
			} else {
				appLog.info("No Checkboxes are available");
			}
			if (click(driver, getGoBackLink(60), "Go back llink", action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on go back link successfuuly");
			} else {
				appLog.info("Not able to click on go back link successfully");
				return false;
			}
		} else {
			appLog.info("Not able to click on edit icon");
			return false;
		}
		return true;
	}
		
	/**
	 * @author Parul Singh
	 * @return true/false
	 */
	public boolean deactivateManageApprovalsSetting(){
		if (clickOnSideMenusTab(sideMenu.ManageApprovals)) {
			if (isSelected(driver,getManageApprovalsActivateCheckbox(EditViewMode.View),
					"Manage Approval Activate checkbox")) {
				if (clickOnEditIcon()) {
					if (click(driver, getManageApprovalsActivateCheckbox(EditViewMode.Edit),
							"Manage Approval activate checkbox", action.SCROLLANDBOOLEAN)) {
						if (click(driver, getManageApprovalSaveButton(60), "Manage Approval Save button",
								action.SCROLLANDBOOLEAN)) {
							if (click(driver, getManageApprovalDeactivatePopupYesButton(60), "Yes Button",
									action.SCROLLANDBOOLEAN)) {
								ThreadSleep(2000);
								if (isSelected(driver, getManageApprovalsActivateCheckbox(EditViewMode.View),
										"Manage Approval Activate checkbox")) {
									appLog.info("Manage Approvals not get deactivated successfully");
									return false;
								} else {
									appLog.info("Manage Approvals get deactivated successfully");
								}
							} else {
								appLog.info("Not able to click on manage Approval deactivation popup yes button");
								return false;
							}
						} else {
							appLog.info("Not able to click on save button");
							return false;
						}
					} else {
						appLog.info("not able to click on Activate checkbox");
						return false;
					}
				} else {
					appLog.info("Not able to click on edit icon");
					return false;
				}
			} else {
				appLog.info("Manage Approvals is already deactivated");
			}
		} else {
			appLog.info("Not able to click on Manage Approvals tab");
			return false;
		}	
		return true;	
	}
	
	/**
	 * @author Parul Singh
	 * @return true/false
	 */
	public boolean deactivateWatermarkingSetting(){
		if (clickOnSideMenusTab(sideMenu.Watermarking)) {
			if (isSelected(driver, getWatermarkingActivateCheckbox(60), "Watermarking Activate checkbox")) {
				if (clickOnEditIcon()) {
					if (click(driver, getWatermarkingActivateCheckbox(60), "Watermarking activate checkbox",
							action.SCROLLANDBOOLEAN)) {
						if (click(driver, getWatermarkingSaveButton(60), "Watermarking Save button",
								action.SCROLLANDBOOLEAN)) {
							ThreadSleep(2000);
							if (isSelected(driver, getWatermarkingActivateCheckbox(60),
									"Watermarking Activate checkbox")) {
								appLog.info("Watermarking is  not deactivated successfully");
								return false;
							} else {
								appLog.info("Watermarking is deactivated successfully");
							}
						} else {
							appLog.info("Not able to click on watermarking save button");
							return false;
						}
					} else {
						appLog.info("Not able to click on watermarking Activate checkbox");
						return false;
					}
				} else {
					appLog.info("Not able to click on edit icon");
					return false;
				}
			} else {
				appLog.info("Watermarking is already deactivated");
			}

		} else {
			appLog.info("Not able to click on watermarking tab");
			return false;
		}
		return true;
	}
	
	public boolean createFolderTemplate(String sheetName, String folderTemplateName, String folderTemplateDescription, int timeOut){
		if(clickOnSideMenusTab(sideMenu.FolderTemplates)){
			if(click(driver, getAddFolderTemplateButton(timeOut), "Add folder template button", action.BOOLEAN)){
				if(sendKeys(driver, getFolderTemplateNameTextBox(timeOut), folderTemplateName, "Folder template name text box", action.BOOLEAN)){
					if(folderTemplateDescription!=null){
						if(sendKeys(driver, getFolderTemplateDescriptionTextBox(timeOut), folderTemplateDescription, "Folder template description text box", action.BOOLEAN)){
							if(createFolderStructureFromExcel(sheetName, timeOut)){
								appLog.info("Folder structure is created successfully.");
								if(click(driver, getFolderTemplateSaveButton(timeOut), "Folder template Save Button", action.BOOLEAN)){
									ThreadSleep(3000);
									if(click(driver, getSaveConfirmationYesButton(timeOut), "folder template save confirmation yes button", action.BOOLEAN)){
										appLog.info("Successfully saved the created folder structure.");
										return true;
									} else {
										appLog.error("Folder template save confirmaton yes button cannot be clicked, So cannot save the folder template.");
										BaseLib.sa.assertTrue(false,"Folder template save confirmaton yes button cannot be clicked, So cannot save the folder template.");
									}
								} else {
									appLog.error("Save button cannot be clicked, So won't be able to save folder strucutre.");
									BaseLib.sa.assertTrue(false,"Save button cannot be clicked, So won't be able to save folder strucutre.");
								}
							} else {
								appLog.error("folder structure is not created successfully.");
								BaseLib.sa.assertTrue(false,"folder structure is not created successfully.");
							}
						} else {
							appLog.error("Not able to pass value to folder template description text box.");
							BaseLib.sa.assertTrue(false,"Not able to pass value to folder template description text box.");
						}
					} else {
						appLog.info("Will create folder template without description.");
					}
				} else {
					appLog.error("Cannot pass value to folder template name text box.");
					BaseLib.sa.assertTrue(false,"Cannot pass value to folder template name text box.");
				}
			} else {
				appLog.info("Canno click on add folder template button, So cannot create folder template.");
				BaseLib.sa.assertTrue(false,"Canno click on add folder template button, So cannot create folder template.");
			}
		} else {
			appLog.error("Folder template menu cannot be clicked, So cannot create folder template.");
			BaseLib.sa.assertTrue(false,"Folder template menu cannot be clicked, So cannot create folder template.");
		}
		return false;
	}
	/**
	 * @author Ankit Jaiswal
	 * @param userVariableName
	 * @return True/False
	 */
	public boolean getMyProfileFistNameAndLastNameAndFirmName(String userVariableName) {
		boolean flag = false;
		if(clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60,getNIMTabParentFrame_Lightning(PageName.NavatarInvestorManager));
			ThreadSleep(3000);
			switchToFrame(driver, 20, getFrame(PageName.NavatarInvestorManager, 20));
			if(clickOnSideMenusTab(sideMenu.Profiles)) {
				if(getMyProfileNameInViewMode(60)!=null) {
					String[] aa=getMyProfileNameInViewMode(60).getText().trim().split(" ");
					appLog.info("User first name is : "+aa[0]+" last name is: "+aa[1]);
					ExcelUtils.writeData(aa[0], "Users",excelLabel.Variable_Name,userVariableName, excelLabel.MyProfile_FName);
					ExcelUtils.writeData(aa[1], "Users",excelLabel.Variable_Name,userVariableName, excelLabel.MyProfile_LName);
					appLog.info("My profile user first and last name is written in "+userVariableName+" "+ excelLabel.MyProfile_FName.toString()+" "+excelLabel.MyProfile_LName.toString()+" cloumn");
					flag=true;
				}else {
					appLog.error("my profile first name and last name is not visible so cannot get it.");
				}
				if(clickOnSideMenusTab(sideMenu.MyFirmProfile)) {
					if(getMyFirmPofilelabelText(60)!=null) {
						String aa=getMyFirmPofilelabelText(20).getText().trim();
						appLog.info("My firm profile name is : "+aa);
						ExcelUtils.writeData(aa, "Users",excelLabel.Variable_Name,userVariableName, excelLabel.Firm_Name);
						appLog.info("My firm profile is written in "+userVariableName+" "+ excelLabel.Firm_Name.toString()+" cloumn");
						flag = true;
					}else {
						appLog.error("My firm profile is not visible so cannot get it");
						flag = false;
					}
				}else {
					appLog.error("Not able to click on my firm profile tab so cannot get my firm name");
					flag =false;
				}
			}else {
				appLog.error("Not able to click on profile tab so cannot get my profile first name and last name");
			}
		}else {
			appLog.error("Not able to click on NIM tab so cannot get my profile first name and last name");
		}
		switchToDefaultContent(driver);
		return flag;
	}
	
	/**
	 * @author Akul Bhutani
	 * @return true/false
	 */
	public boolean checkForAlert(){
		if(isAlertPresent(driver)){
			appLog.info(switchToAlertAndGetMessage(driver, 20, action.GETTEXT));
			switchToAlertAndAcceptOrDecline(driver, 10, action.ACCEPT);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @author Ankur Rana
	 * @param labelsAndLocation
	 * @return List<String>
	 */
	public List<String> activateWatermarking2(String labelsAndLocation){
		String[] labelsWithLocation = labelsAndLocation.split(",");
		List<String> notSetLabels = new ArrayList<String>();
		int j = 1;
		ThreadSleep(7000);
		if(clickOnEditIcon()) {
			for(int i = 0; i < labelsWithLocation.length ; i++){
				if(i==0) {
					if(!getWatermarkingActivateCheckbox(10).isSelected()) {
						if(click(driver, getWatermarkingActivateCheckbox(10), "watermarking active check box", action.SCROLLANDBOOLEAN)){
							appLog.info("WaterMarking is activated successfully");
						}else {
							appLog.error("Not able to activate Watermarking");
							notSetLabels.add("Not able to activte Watermarking so cannot enable label and set watermarking locations");
							break;
						}
					}
				}
				System.err.println(labelsWithLocation[i].split("-")[0]);
				System.out.println("inside for");
				if((labelsWithLocation[i].split("-"))[0].equalsIgnoreCase("My Firm's Name") || labelsWithLocation[i].split("-")[0].equalsIgnoreCase("My Firm Name") || labelsWithLocation[i].split("-")[0].contains("Firm's Name") && (labelsWithLocation[i].split("-")[0].contains("Firm") && labelsWithLocation[i].split("-")[0].contains("Name") && labelsWithLocation[i].split("-")[0].contains("My"))){
					System.out.println("Inside if");
					if(getWatermarkingFirmNameLabel(10).isSelected()){
						appLog.info("firm name check box is already selected.");
						if(getWaterMarkingMyFirmNameDropDown(10)!=null){
							if(selectVisibleTextFromDropDown(driver, getWaterMarkingMyFirmNameDropDown(10), "My Firm Name Drop Down", labelsWithLocation[i].split("-")[1])){
								appLog.info("Successfully selected "+labelsWithLocation[i].split("-")[1]+" from the drop down");
							} else {
								appLog.error(labelsWithLocation[i].split("-")[1]+" value is not present in drop down");
								notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							}
						} else {
							appLog.error("Firm Name Drop Down is not available.");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
						}
					} else {
						if(click(driver, getWatermarkingFirmNameLabel(10), "watermarking firm name label", action.SCROLLANDBOOLEAN)){
							if(checkForAlert()){
								break;
							}
							appLog.info("Firm Name Label is successfully checked.");
							if(getWaterMarkingMyFirmNameDropDown(10)!=null){
								if(selectVisibleTextFromDropDown(driver, getWaterMarkingMyFirmNameDropDown(10), "My Firm Name Drop Down", labelsWithLocation[i].split("-")[1])){
									appLog.info("Successfully selected "+labelsWithLocation[i].split("-")[1]+" from the drop down");
								} else {
									appLog.error(labelsWithLocation[i].split("-")[1]+" value is not present in drop down");
									notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
								}
							} else {
								appLog.error("Firm Name Drop Down is not available.");
								notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							}
						} else {
							if(checkForAlert()){
								break;
							}
							appLog.error("Firm Name label is not checked.");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
						}
					}
				} else if((labelsWithLocation[i].split("-"))[0].equalsIgnoreCase("Investor Name") || labelsWithLocation[i].split("-")[0].equalsIgnoreCase("Investor Name") || labelsWithLocation[i].split("-")[0].contains("Investor Name") && (labelsWithLocation[i].split("-")[0].contains("Investor") && labelsWithLocation[i].split("-")[0].contains("Name"))){
					if(getInvestorNameLabel(10).isSelected()){
						appLog.info("Target Account name check box is already selected.");
						if(getInvestorNameLabelDropDown(10)!=null){
							if(selectVisibleTextFromDropDown(driver, getInvestorNameLabelDropDown(10), "Investor Name Drop Down", labelsWithLocation[i].split("-")[1])){
								appLog.info("Successfully selected "+labelsWithLocation[i].split("-")[1]+" from the drop down");
							} else {
								appLog.error(labelsWithLocation[i].split("-")[1]+" value is not present in drop down");
								notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							}
						} else {
							appLog.error("Investor Name Drop Down is not available.");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
						}
					} else {
						if(click(driver, getInvestorNameLabel(10), "watermarking Investor Name label", action.SCROLLANDBOOLEAN)){
							if(checkForAlert()){
								break;
							}
							appLog.info("Investor Name Label is successfully checked.");
							if(getInvestorNameLabelDropDown(10)!=null){
								if(selectVisibleTextFromDropDown(driver, getInvestorNameLabelDropDown(10), "Investor Name Drop Down", labelsWithLocation[i].split("-")[1])){
									appLog.info("Successfully selected "+labelsWithLocation[i].split("-")[1]+" from the drop down");
								} else {
									appLog.error(labelsWithLocation[i].split("-")[1]+" value is not present in drop down");
									notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
								}
							} else {
								appLog.error("Investor Name Drop Down is not available.");
								notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							}
						} else {
							if(checkForAlert()){
								break;
							}
							appLog.error("Investor Name label is not checked.");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
						}
					}
				} else if((labelsWithLocation[i].split("-"))[0].equalsIgnoreCase("Fund Name") || labelsWithLocation[i].split("-")[0].equalsIgnoreCase("Fund Name") || labelsWithLocation[i].split("-")[0].contains("Fund Name") && (labelsWithLocation[i].split("-")[0].contains("Fund") && labelsWithLocation[i].split("-")[0].contains("Name"))){
					if(getFundNameLabel(10).isSelected()){
						appLog.info("Fund Name check box is already selected.");
						if(getFundNameLabelDropDown(10)!=null){
							if(selectVisibleTextFromDropDown(driver, getFundNameLabelDropDown(10), "Fund Name Drop Down", labelsWithLocation[i].split("-")[1])){
								appLog.info("Successfully selected "+labelsWithLocation[i].split("-")[1]+" from the drop down");
							} else {
								appLog.error(labelsWithLocation[i].split("-")[1]+" value is not present in drop down");
								notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							}
						} else {
							appLog.error("Fund Name Drop Down is not available.");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
						}
					} else {
						if(click(driver, getFundNameLabel(10), "watermarking Fund Name label", action.SCROLLANDBOOLEAN)){
							if(checkForAlert()){
								break;
							}
							appLog.info("Fund Name Label is successfully checked.");
							if(getFundNameLabelDropDown(10)!=null){
								if(selectVisibleTextFromDropDown(driver, getFundNameLabelDropDown(10), "Fund Name Drop Down", labelsWithLocation[i].split("-")[1])){
									appLog.info("Successfully selected "+labelsWithLocation[i].split("-")[1]+" from the drop down");
								} else {
									appLog.error(labelsWithLocation[i].split("-")[1]+" value is not present in drop down");
									notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
								}
							} else {
								appLog.error("Fund Name Drop Down is not available.");
								notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							}
						} else {
							if(checkForAlert()){
								break;
							}
							appLog.error("Fund Name label is not checked.");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
						}
					}
				} else if((labelsWithLocation[i].split("-"))[0].equalsIgnoreCase("Download Date") || labelsWithLocation[i].split("-")[0].equalsIgnoreCase("Date") && (labelsWithLocation[i].split("-")[0].contains("Download"))){
					if(getDownloadDateLabel(10).isSelected()){
						appLog.info("Download Date check box is already selected.");
						if(getDownloadDateLabelDropDown(10)!=null){
							if(selectVisibleTextFromDropDown(driver, getDownloadDateLabelDropDown(10), "Download Date Drop Down", labelsWithLocation[i].split("-")[1])){
								appLog.info("Successfully selected "+labelsWithLocation[i].split("-")[1]+" from the drop down");
							} else {
								appLog.error(labelsWithLocation[i].split("-")[1]+" value is not present in drop down");
								notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							}
						} else {
							appLog.error("Download Date Drop Down is not available.");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
						}
					} else {
						if(click(driver, getDownloadDateLabel(10), "watermarking Download Date label", action.SCROLLANDBOOLEAN)){
							if(checkForAlert()){
								break;
							}
							appLog.info("Download Date Label is successfully checked.");
							if(getDownloadDateLabelDropDown(10)!=null){
								if(selectVisibleTextFromDropDown(driver, getDownloadDateLabelDropDown(10), "Download Date Drop Down", labelsWithLocation[i].split("-")[1])){
									appLog.info("Successfully selected "+labelsWithLocation[i].split("-")[1]+" from the drop down");
								} else {
									appLog.error(labelsWithLocation[i].split("-")[1]+" value is not present in drop down");
									notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
								}
							} else {
								appLog.error("Download Date Drop Down is not available.");
								notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							}
						} else {
							if(checkForAlert()){
								break;
							}
							appLog.error("Download Date label is not checked.");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
						}
					}
				} else if((labelsWithLocation[i].split("-"))[0].equalsIgnoreCase("IP Address") || labelsWithLocation[i].split("-")[0].equalsIgnoreCase("IP") && (labelsWithLocation[i].split("-")[0].contains("IP") && labelsWithLocation[i].split("-")[0].contains("Address"))){
					if(getIPAddressLabel(10).isSelected()){
						appLog.info("IP Address check box is already selected.");
						if(getIPAddressLabelDropDown(10)!=null){
							if(selectVisibleTextFromDropDown(driver, getIPAddressLabelDropDown(10), "IP Address Drop Down", labelsWithLocation[i].split("-")[1])){
								appLog.info("Successfully selected "+labelsWithLocation[i].split("-")[1]+" from the drop down");
							} else {
								appLog.error(labelsWithLocation[i].split("-")[1]+" value is not present in drop down");
								notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							}
						} else {
							appLog.error("IP Address Drop Down is not available.");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
						}
					} else {
						if(click(driver, getIPAddressLabel(10), "watermarking IP Address label", action.SCROLLANDBOOLEAN)){
							if(checkForAlert()){
								break;
							}
							appLog.info("IP Address Label is successfully checked.");
							if(getIPAddressLabelDropDown(10)!=null){
								if(selectVisibleTextFromDropDown(driver, getIPAddressLabelDropDown(10), "IP Address Drop Down", labelsWithLocation[i].split("-")[1])){
									appLog.info("Successfully selected "+labelsWithLocation[i].split("-")[1]+" from the drop down");
								} else {
									appLog.error(labelsWithLocation[i].split("-")[1]+" value is not present in drop down");
									notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
								}
							} else {
								appLog.error("IP Address Drop Down is not available.");
								notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							}
						} else {
							if(checkForAlert()){
								break;
							}
							appLog.error("IP Address label is not checked.");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
						}
					}
				} else if((labelsWithLocation[i].split("-"))[0].equalsIgnoreCase("Email Address") || labelsWithLocation[i].split("-")[0].equalsIgnoreCase("Email") && (labelsWithLocation[i].split("-")[0].contains("Email") && labelsWithLocation[i].split("-")[0].contains("Address"))){
					if(getEmailAddressLabel(10).isSelected()){
						appLog.info("Email Address check box is already selected.");
						if(getEmailAddressLabelDropDown(10)!=null){
							if(selectVisibleTextFromDropDown(driver, getEmailAddressLabelDropDown(10), "Email Address Drop Down", labelsWithLocation[i].split("-")[1])){
								appLog.info("Successfully selected "+labelsWithLocation[i].split("-")[1]+" from the drop down");
							} else {
								appLog.error(labelsWithLocation[i].split("-")[1]+" value is not present in drop down");
								notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							}
						} else {
							appLog.error("Email Address Drop Down is not available.");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
						}
					} else {
						if(click(driver, getEmailAddressLabel(10), "watermarking Email Address label", action.SCROLLANDBOOLEAN)){
							if(checkForAlert()){
								break;
							}
							appLog.info("Email Address Label is successfully checked.");
							if(getEmailAddressLabelDropDown(10)!=null){
								if(selectVisibleTextFromDropDown(driver, getEmailAddressLabelDropDown(10), "Email Address Drop Down", labelsWithLocation[i].split("-")[1])){
									appLog.info("Successfully selected "+labelsWithLocation[i].split("-")[1]+" from the drop down");
								} else {
									appLog.error(labelsWithLocation[i].split("-")[1]+" value is not present in drop down");
									notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
								}
							} else {
								appLog.error("Email Address Drop Down is not available.");
								notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							}
						} else {
							if(checkForAlert()){
								break;
							}
							appLog.error("Email Address label is not checked.");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
						}
					}
				} else {
					if(getCustomLabelCheckBox(10).isSelected()){
						appLog.info("Custom Label Check Box is Already Selected.");
					} else {
						if(click(driver, getCustomLabelCheckBox(10), "Custom Label CheckBox", action.SCROLLANDBOOLEAN)){
							appLog.info("Successfully selected custom label check box.");
						} else {
							appLog.error("custom label check box cannot be selected.");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							continue;
						}
					}
					if(j==1){
						if(sendKeys(driver, FindElement(driver, "//input[@id='criteriatextbox"+j+"']", "Custom Label Text Field", action.BOOLEAN, 20), labelsWithLocation[i].split("-")[0], "Custom Label Text Field", action.SCROLLANDBOOLEAN)){
//							j++;
							appLog.info("Successfully passed value to cutom label text box.");
						} else {
							appLog.error("Not Able to pass value to custom label text box.");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							continue;
						}
					} else {
						if(click(driver, getCustomLabelAddRowLink(10), "Custom Label Add Row Link", action.SCROLLANDBOOLEAN)){
							if(checkForAlert()){
								break;
							}
							if(sendKeys(driver, FindElement(driver, "//input[@id='criteriatextbox"+j+"']", "Custom Label Text Field", action.BOOLEAN, 20), labelsWithLocation[i].split("-")[0], "Custom Label Text Field", action.SCROLLANDBOOLEAN)){
//								j++;
								appLog.info("Successfully passed value to cutom label text box.");
							} else {
								appLog.error("Not Able to pass value to custom label text box.");
								notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
								continue;
							}
						} else {
							if(checkForAlert()){
								break;
							}
							appLog.error("Not able to click on add row link");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							continue;
						}
					}
					if(selectVisibleTextFromDropDown(driver, FindElement(driver, "//select[@id='a"+j+"aa']", "Custom Label Text Field", action.BOOLEAN, 10), "Cutom Label Drop Down", (labelsWithLocation[i].split("-"))[1])){
						appLog.info("Successfully selected "+(labelsWithLocation[i].split("-"))[1]+" from the drop down.");
//						j++;
					} else {
						appLog.error("Not able to selecte "+(labelsWithLocation[i].split("-"))[1]+" from the drop down.");
						notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
					}
					j++;
				}
			}
			if(click(driver, getWatermarkingSaveButton(60), "Save Button", action.SCROLLANDBOOLEAN)){
				if(isAlertPresent(driver)){
					String msg = switchToAlertAndGetMessage(driver, 10, action.GETTEXT);
					switchToAlertAndAcceptOrDecline(driver, 10, action.ACCEPT);
					appLog.info(msg);
					driver.navigate().refresh();
					switchToFrame(driver, 10,getFrame(PageName.NavatarInvestorManager, 10));
					clickOnSideMenusTab(sideMenu.Watermarking);
//					notSetLabels.add("No watermarking label is set. Due to: "+msg);
					return null;
				} else {
					appLog.info("Successfully saved the watermarking setting.");
				}
			} else {
				appLog.error("Not able to save watermarking setting.");
				notSetLabels.add("Not able to save watermarking setting.");
			}
		}else {
			appLog.error("Not able to click on Edit Icon so cannot Activate WaterMarking");
			notSetLabels.add("Not able to click on Edit Icon so cannot Activate WaterMarking");
		}
		return notSetLabels;
	}
	
	
	/**
	 * @author Ankur Rana
	 * @return List<String>
	 */
	public List<String> resetWaterMarkingSetting() {
		List<WebElement> lst = getWaterMarkingCheckBoxList();
		List<WebElement> checkBox=getWatermarkingDropDownList();
		List<String> result= new ArrayList<String>();
		if(!lst.isEmpty()) {
			for(int i=0 ;i<lst.size()-1; i++) {
				if(isSelected(driver, lst.get(i), "check box")) {
					if(click(driver,lst.get(i), "check box", action.SCROLLANDBOOLEAN)) {
						appLog.info("label clicked on check box");
					}else {
						appLog.error("Not able to uncheck check box");
						result.add("Not able to uncheck check box");
					}
				}else {
					appLog.info("label check Box is already unchecked");
				}
				if(getSelectedOptionOfDropDown(driver, checkBox.get(i), "drop down", "text").trim()!="None") {
					if(selectVisibleTextFromDropDown(driver,checkBox.get(i), "drop down","None")) {
						appLog.info("Select None value from drop down list");
					}else {
						appLog.error("Not able to select None value from drop down list");
						result.add("Not able to select None value from drop down list");
					}
				}else {
					appLog.info("None is alreadyed selected in drop down list");
				}
			}	
		}else {
			appLog.error("WaterMarking label check boxes are not available");
			result.add("WaterMarking label check boxes are not available");
		}
		return result;

	}
	
	/**
	 * @author Akul Bhutani
	 * @param userName1
	 * @param userName2
	 * @param menu
	 * @return true/false
	 */
	public boolean verifyPresenceOfUsersInManageApprovalsMenu(String userName1, String userName2, EditViewMode menu) {

		boolean user1flag = false, user2flag = false;
		int size =  getUserNamesManageApproval(60,menu).size();
		for (int i=0;i<size;i++) {
			if ( getUserNamesManageApproval(60,menu).get(i).getText().trim().equals(userName1)) {
				appLog.info("username 1 is successfully found");
				user1flag = true;
			}
			if ( getUserNamesManageApproval(60,menu).get(i).getText().trim().equals(userName2)) {
				appLog.info("username 2 is successfully found");
				user2flag = true;
			}
		}
		if (user1flag!=true) {
			appLog.error(userName1+" is not found in usernames list on manage approval page");
			BaseLib.sa.assertTrue(false, userName1+" is not found in usernames list on manage approval page");
		}
		if (user2flag!=true) {
			appLog.error(userName2+" is not found in usernames list on manage approval page");
			BaseLib.sa.assertTrue(false, userName2+" is not found in usernames list on manage approval page");
		}
		
		return (user1flag&&user2flag);
		
	}
	
	/**
	 * @author Ankur Rana
	 * @param userName
	 * @return List<String>
	 */
	public List<String> activateManageApprovalsSettings(String userName) {
		String[] userList=userName.split(",");
		List<String> result = new ArrayList<String>();
		boolean falg = false;
		if(clickOnEditIcon()) {
			appLog.info("clicked on edit icon");
			if (!isSelected(driver,getManageApprovalsActivateCheckbox(EditViewMode.Edit),
					"Manage Approval Activate checkbox")) {
				if (click(driver, getManageApprovalsActivateCheckbox(EditViewMode.Edit),
						"Manage Approval activate checkbox", action.SCROLLANDBOOLEAN)) {
					appLog.info("activate manage approvals settings");
				}else {
					appLog.error("Not able to activate manage approvals settings so cannot give access to user");
					result.add("Not able to activate manage approvals settings so cannot give access to user");
				}
			}else {
				appLog.info("Manage approvals settings is already activated");
			}
			for(int i=0 ;i<userList.length; i++) {
				if(!verifyCheckedOrNotManageApproval(EditViewMode.Edit, userList[i])) {
					String xpath = "(//span[text()='"+userList[i]+"']/../span[contains(@id,'cell-1-')])[2]//span[contains(@class,'aw-item-marker')]";
					WebElement ele = isDisplayed(driver, FindElement(driver, xpath, "checkbox in front of "+userList[i], action.SCROLLANDBOOLEAN, 10), "visibility", 10, "checkbox in front of "+userList[i]);
					if(ele!=null) {
						if(click(driver,ele,userList[i]+" check box", action.BOOLEAN)) {
							appLog.info(userList[i]+" clicked on user check box");
						}
					}else {
						appLog.error(userList[i]+" : Not able to select user in manage approvals");
						result.add(userList[i]+" : Not able to select user in manage approvals");
					}
				}else {
					appLog.info(userList[i]+" : user is already selected");
				}
			}
			if (click(driver, getManageApprovalSaveButton(60), "Manage Approval Save button",
					action.SCROLLANDBOOLEAN)) {
				if (click(driver, getManageApprovalActivateYesButton(60), "Yes Button",
						action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on manage approvals activation Yes button");
				}else {
					appLog.error("Not able to click manage approvals activation Yes button so cannot save manage approvals settings");
					result.add("Not able to click manage approvals activation Yes button so cannot save manage approvals settings");
				}
			}else {
				appLog.error("Not able to click save button so cannot save manage approvals settings");
				result.add("Not able to click save button so cannot save manage approvals settings");
			}
		}else {
			appLog.error("Not able to click on edit icon so cannot activate manage approvals settings");
			result.add("Not able to click on edit icon so cannot activate manage approvals settings");
		}
		return result;
	}
	
	/**
	 * @author Ankur Rana
	 * @param labelsAndLocation
	 * @param checkUncheck
	 * @return List<String>
	 */
	public List<String> reSetWatermarking(String labelsAndLocation,CheckUncheck checkUncheck){
		String[] labelsWithLocation = labelsAndLocation.split(",");
		List<String> notSetLabels = new ArrayList<String>();
		int j = 1;
		if(clickOnSideMenusTab(sideMenu.Watermarking)){
			if(clickOnEditIcon());
			ThreadSleep(2000);
			if(checkUncheck.toString().equalsIgnoreCase(CheckUncheck.UnCheck.toString())) {
				List<WebElement> lst = getWaterMarkingCheckBoxList();
				List<WebElement> dropdown=getWatermarkingDropDownList();
				if(!lst.isEmpty()) {
					for(int i=0 ;i<lst.size()-3; i++) {
						if(isSelected(driver, lst.get(i), "check box")) {
							if(click(driver,lst.get(i), "check box", action.SCROLLANDBOOLEAN)) {
								appLog.info("label clicked on check box");
							}else {
								appLog.error("Not able to click on check box so cannot uncheck check box");
								notSetLabels.add("Not able to click on check box so cannot uncheck check box");
							}
						}else {
							appLog.info("label check Box is not checked");
						}
					}
				}else {
					appLog.error("watermarking check box list is not visible so cannot uncheck it");
					notSetLabels.add("watermarking check box list is not visible so cannot uncheck it");
				}
				lst.clear();
				lst =getWaterMarkingCustomFieldCrossIconList();
				if(!lst.isEmpty()) {
					for(int i=0 ;i<lst.size(); i++) {
							if(click(driver,lst.get(i), "check box", action.SCROLLANDBOOLEAN)) {
								appLog.info("label clicked on check box");
							}else {
								appLog.error("Not able to click on check box so cannot uncheck check box");
								notSetLabels.add("Not able to click on check box so cannot uncheck check box");
							}
					}
				}else {
					appLog.error("custom watermarking check box list is not visible so cannot uncheck it");
					notSetLabels.add("custom watermarking check box list is not visible so cannot uncheck it");
				}
				if(!dropdown.isEmpty()) {
					for(int j1=0 ;j1<dropdown.size(); j1++) {
						if(getSelectedOptionOfDropDown(driver, dropdown.get(j1), "drop down", "text").trim()!="None") {
							if(selectVisibleTextFromDropDown(driver,dropdown.get(j1), "drop down","None")) {
								appLog.info("Select None value from drop down list");
							}else {
								appLog.error("Not able to select None value from drop down list");
								notSetLabels.add("Not able to select None value from drop down list");
							}
						}else {
							appLog.info("None is alreadyed selected in drop down list");
						}
					}
					
				}else {
					appLog.error("watermarking drop dwon list is not visible so cannot reset drop down");
					notSetLabels.add("watermarking drop dwon list is not visible so cannot reset drop down");
				}

			}
			for(int i = 0; i < labelsWithLocation.length ; i++){
				System.out.println("inside for");
				if((labelsWithLocation[i].split("-"))[0].equalsIgnoreCase("My Firm's Name") || labelsWithLocation[i].split("-")[0].equalsIgnoreCase("My Firm Name") || labelsWithLocation[i].split("-")[0].contains("Firm's Name") && (labelsWithLocation[i].split("-")[0].contains("Firm") && labelsWithLocation[i].split("-")[0].contains("Name") && labelsWithLocation[i].split("-")[0].contains("My"))){
					System.out.println("Inside if");
					if(getWatermarkingFirmNameLabel(60).isSelected()){
						appLog.info("firm name check box is already selected.");
						if(getWaterMarkingMyFirmNameDropDown(10)!=null){
							if(selectVisibleTextFromDropDown(driver, getWaterMarkingMyFirmNameDropDown(10), "My Firm Name Drop Down", labelsWithLocation[i].split("-")[1])){
								appLog.info("Successfully selected "+labelsWithLocation[i].split("-")[1]+" from the drop down");
							} else {
								appLog.error(labelsWithLocation[i].split("-")[1]+" value is not present in drop down");
								notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							}
						} else {
							appLog.error("Firm Name Drop Down is not available.");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
						}
					} else {
						if(click(driver, getWatermarkingFirmNameLabel(30), "watermarking firm name label", action.SCROLLANDBOOLEAN)){
							if(checkForAlert()){
								break;
							}
							appLog.info("Firm Name Label is successfully checked.");
							if(getWaterMarkingMyFirmNameDropDown(10)!=null){
								if(selectVisibleTextFromDropDown(driver, getWaterMarkingMyFirmNameDropDown(20), "My Firm Name Drop Down", labelsWithLocation[i].split("-")[1])){
									appLog.info("Successfully selected "+labelsWithLocation[i].split("-")[1]+" from the drop down");
								} else {
									appLog.error(labelsWithLocation[i].split("-")[1]+" value is not present in drop down");
									notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
								}
							} else {
								appLog.error("Firm Name Drop Down is not available.");
								notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							}
						} else {
							if(checkForAlert()){
								break;
							}
							appLog.error("Firm Name label is not checked.");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
						}
					}
				} else if((labelsWithLocation[i].split("-"))[0].equalsIgnoreCase("Investor Name") || labelsWithLocation[i].split("-")[0].equalsIgnoreCase("Investor") || labelsWithLocation[i].split("-")[0].contains("Name") && (labelsWithLocation[i].split("-")[0].contains("Investor") && labelsWithLocation[i].split("-")[0].contains("Name"))){
					if(getInvestorNameLabelDropDown(20).isSelected()){
						appLog.info("Target Account name check box is already selected.");
						if(getInvestorNameLabelDropDown(30)!=null){
							if(selectVisibleTextFromDropDown(driver, getInvestorNameLabelDropDown(30), "Target Account Name Drop Down", labelsWithLocation[i].split("-")[1])){
								appLog.info("Successfully selected "+labelsWithLocation[i].split("-")[1]+" from the drop down");
							} else {
								appLog.error(labelsWithLocation[i].split("-")[1]+" value is not present in drop down");
								notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							}
						} else {
							appLog.error("Target Account Name Drop Down is not available.");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
						}
					} else {
						if(click(driver, getInvestorNameLabel(20), "watermarking Target Account Name label", action.SCROLLANDBOOLEAN)){
							if(checkForAlert()){
								break;
							}
							appLog.info("Target Account Name Label is successfully checked.");
							if(getInvestorNameLabelDropDown(30)!=null){
								if(selectVisibleTextFromDropDown(driver, getInvestorNameLabelDropDown(30), "Target Account Name Drop Down", labelsWithLocation[i].split("-")[1])){
									appLog.info("Successfully selected "+labelsWithLocation[i].split("-")[1]+" from the drop down");
								} else {
									appLog.error(labelsWithLocation[i].split("-")[1]+" value is not present in drop down");
									notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
								}
							} else {
								appLog.error("Target Account Name Drop Down is not available.");
								notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							}
						} else {
							if(checkForAlert()){
								break;
							}
							appLog.error("Target Account Name label is not checked.");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
						}
					}
				} else if((labelsWithLocation[i].split("-"))[0].equalsIgnoreCase("Fund Name") || labelsWithLocation[i].split("-")[0].contains("Fund Name") && (labelsWithLocation[i].split("-")[0].contains("Fund") && labelsWithLocation[i].split("-")[0].contains("Name"))){
					if(getFundNameLabelDropDown(60).isSelected()){
						appLog.info("Deal Room Name check box is already selected.");
						if(getFundNameLabelDropDown(30)!=null){
							if(selectVisibleTextFromDropDown(driver, getFundNameLabelDropDown(30), "Deal Room Name Drop Down", labelsWithLocation[i].split("-")[1])){
								appLog.info("Successfully selected "+labelsWithLocation[i].split("-")[1]+" from the drop down");
							} else {
								appLog.error(labelsWithLocation[i].split("-")[1]+" value is not present in drop down");
								notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							}
						} else {
							appLog.error("Deal Room Name Drop Down is not available.");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
						}
					} else {
						if(click(driver, getFundNameLabel(30), "watermarking Deal Room Name label", action.SCROLLANDBOOLEAN)){
							if(checkForAlert()){
								break;
							}
							appLog.info("Deal Room Name Label is successfully checked.");
							if(getFundNameLabelDropDown(30)!=null){
								if(selectVisibleTextFromDropDown(driver, getFundNameLabelDropDown(30), "Deal Room Name Drop Down", labelsWithLocation[i].split("-")[1])){
									appLog.info("Successfully selected "+labelsWithLocation[i].split("-")[1]+" from the drop down");
								} else {
									appLog.error(labelsWithLocation[i].split("-")[1]+" value is not present in drop down");
									notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
								}
							} else {
								appLog.error("Deal Room Name Drop Down is not available.");
								notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							}
						} else {
							if(checkForAlert()){
								break;
							}
							appLog.error("Deal Room Name label is not checked.");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
						}
					}
				} else if((labelsWithLocation[i].split("-"))[0].equalsIgnoreCase("Download Date") || labelsWithLocation[i].split("-")[0].equalsIgnoreCase("Downloaded Date") && (labelsWithLocation[i].split("-")[0].contains("Download") && labelsWithLocation[i].split("-")[0].contains("Date"))){
					if(getDownloadDateLabel(30).isSelected()){
						appLog.info("Download Date check box is already selected.");
						if(getDownloadDateLabelDropDown(30)!=null){
							if(selectVisibleTextFromDropDown(driver, getDownloadDateLabelDropDown(30), "Download Date Drop Down", labelsWithLocation[i].split("-")[1])){
								appLog.info("Successfully selected "+labelsWithLocation[i].split("-")[1]+" from the drop down");
							} else {
								appLog.error(labelsWithLocation[i].split("-")[1]+" value is not present in drop down");
								notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							}
						} else {
							appLog.error("Download Date Drop Down is not available.");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
						}
					} else {
						if(click(driver, getDownloadDateLabel(30), "watermarking Download Date label", action.SCROLLANDBOOLEAN)){
							if(checkForAlert()){
								break;
							}
							appLog.info("Download Date Label is successfully checked.");
							if(getDownloadDateLabelDropDown(30)!=null){
								if(selectVisibleTextFromDropDown(driver, getDownloadDateLabelDropDown(30), "Download Date Drop Down", labelsWithLocation[i].split("-")[1])){
									appLog.info("Successfully selected "+labelsWithLocation[i].split("-")[1]+" from the drop down");
								} else {
									appLog.error(labelsWithLocation[i].split("-")[1]+" value is not present in drop down");
									notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
								}
							} else {
								appLog.error("Download Date Drop Down is not available.");
								notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							}
						} else {
							if(checkForAlert()){
								break;
							}
							appLog.error("Download Date label is not checked.");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
						}
					}
				} else if((labelsWithLocation[i].split("-"))[0].equalsIgnoreCase("IP Address") || labelsWithLocation[i].split("-")[0].equalsIgnoreCase("IP") && (labelsWithLocation[i].split("-")[0].contains("IP") && labelsWithLocation[i].split("-")[0].contains("Address"))){
					if(getIPAddressLabel(30).isSelected()){
						appLog.info("IP Address check box is already selected.");
						if(getIPAddressLabelDropDown(30)!=null){
							if(selectVisibleTextFromDropDown(driver, getIPAddressLabelDropDown(30), "IP Address Drop Down", labelsWithLocation[i].split("-")[1])){
								appLog.info("Successfully selected "+labelsWithLocation[i].split("-")[1]+" from the drop down");
							} else {
								appLog.error(labelsWithLocation[i].split("-")[1]+" value is not present in drop down");
								notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							}
						} else {
							appLog.error("IP Address Drop Down is not available.");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
						}
					} else {
						if(click(driver, getIPAddressLabel(30), "watermarking IP Address label", action.SCROLLANDBOOLEAN)){
							if(checkForAlert()){
								break;
							}
							appLog.info("IP Address Label is successfully checked.");
							if(getIPAddressLabelDropDown(30)!=null){
								if(selectVisibleTextFromDropDown(driver, getIPAddressLabelDropDown(30), "IP Address Drop Down", labelsWithLocation[i].split("-")[1])){
									appLog.info("Successfully selected "+labelsWithLocation[i].split("-")[1]+" from the drop down");
								} else {
									appLog.error(labelsWithLocation[i].split("-")[1]+" value is not present in drop down");
									notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
								}
							} else {
								appLog.error("IP Address Drop Down is not available.");
								notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							}
						} else {
							if(checkForAlert()){
								break;
							}
							appLog.error("IP Address label is not checked.");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
						}
					}
				} else if((labelsWithLocation[i].split("-"))[0].equalsIgnoreCase("Email Address") || labelsWithLocation[i].split("-")[0].equalsIgnoreCase("Email") && (labelsWithLocation[i].split("-")[0].contains("Email") && labelsWithLocation[i].split("-")[0].contains("Address"))){
					if(getEmailAddressLabel(30).isSelected()){
						appLog.info("Email Address check box is already selected.");
						if(getEmailAddressLabelDropDown(30)!=null){
							if(selectVisibleTextFromDropDown(driver, getEmailAddressLabelDropDown(30), "Email Address Drop Down", labelsWithLocation[i].split("-")[1])){
								appLog.info("Successfully selected "+labelsWithLocation[i].split("-")[1]+" from the drop down");
							} else {
								appLog.error(labelsWithLocation[i].split("-")[1]+" value is not present in drop down");
								notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							}
						} else {
							appLog.error("Email Address Drop Down is not available.");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
						}
					} else {
						if(click(driver, getEmailAddressLabel(30), "watermarking Email Address label", action.SCROLLANDBOOLEAN)){
							if(checkForAlert()){
								break;
							}
							appLog.info("Email Address Label is successfully checked.");
							if(getEmailAddressLabelDropDown(30)!=null){
								if(selectVisibleTextFromDropDown(driver, getEmailAddressLabelDropDown(30), "Email Address Drop Down", labelsWithLocation[i].split("-")[1])){
									appLog.info("Successfully selected "+labelsWithLocation[i].split("-")[1]+" from the drop down");
								} else {
									appLog.error(labelsWithLocation[i].split("-")[1]+" value is not present in drop down");
									notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
								}
							} else {
								appLog.error("Email Address Drop Down is not available.");
								notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							}
						} else {
							if(checkForAlert()){
								break;
							}
							appLog.error("Email Address label is not checked.");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
						}
					}
				} else {
					if(getCustomLabelCheckBox(30).isSelected()){
						appLog.info("Custom Label Check Box is Already Selected.");
					} else {
						if(click(driver, getCustomLabelCheckBox(60), "Custom Label CheckBox", action.SCROLLANDBOOLEAN)){
							appLog.info("Successfully selected custom label check box.");
						} else {
							appLog.error("custom label check box cannot be selected.");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							continue;
						}
					}
					if(j==1){
						WebElement ele = FindElement(driver, "//input[@id='criteriatextbox"+j+"']", "Custom Label Text Field", action.BOOLEAN, 30);
						if(getValueFromElementUsingJavaScript(driver, ele, "")==null || getValueFromElementUsingJavaScript(driver, ele, "").trim()=="")
							if(sendKeys(driver, FindElement(driver, "//input[@id='criteriatextbox"+j+"']", "Custom Label Text Field", action.BOOLEAN, 30), labelsWithLocation[i].split("-")[0], "Custom Label Text Field", action.SCROLLANDBOOLEAN)){
								j++;
								appLog.info("Successfully passed value to cutom label text box.");
							} else {
								appLog.error("Not Able to pass value to custom label text box.");
								notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
								continue;
							}
						else {
							String s = getValueFromElementUsingJavaScript(driver, ele, "");
							if(!s.equalsIgnoreCase(labelsWithLocation[i].split("-")[0])){
								if(sendKeys(driver, FindElement(driver, "//input[@id='criteriatextbox"+j+"']", "Custom Label Text Field", action.BOOLEAN, 30), labelsWithLocation[i].split("-")[0], "Custom Label Text Field", action.SCROLLANDBOOLEAN)){
//									j++;
									appLog.info("Successfully passed value to cutom label text box.");
								} else {
									appLog.error("Not Able to pass value to custom label text box.");
									notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
									continue;
								}
							} else {
//								j++;
							}
							
						}
					} else {
						boolean flag = true;
						WebElement ele = FindElement(driver, "//input[@id='criteriatextbox"+(j+1)+"' or @id='criteriatextbox"+(j)+"']", "Custom Label Text Field", action.BOOLEAN, 10);
						System.err.println(ele);
						if(ele!=null){
							if(ele.getAttribute("value").trim()!=null ||ele.getAttribute("value").trim()!=""){
								if(ele.getAttribute("value").trim().equalsIgnoreCase(labelsWithLocation[i].split("-")[0])){
									flag=false;
//									j++;
								} else {
									flag=true;
								}
							}
						}
						if(click(driver, getCustomLabelAddRowLink(30), "Custom Label Add Row Link", action.SCROLLANDBOOLEAN)){
							if(checkForAlert()){
								switchToAlertAndAcceptOrDecline(driver, 3, action.ACCEPT);
							}
							if(flag)
								if(sendKeys(driver, FindElement(driver, "//input[@id='criteriatextbox"+j+"' or @id='criteriatextbox"+(j+1)+"' or @id='criteriatextbox"+(j-1)+"'] ", "Custom Label Text Field", action.BOOLEAN, 30), labelsWithLocation[i].split("-")[0], "Custom Label Text Field", action.SCROLLANDBOOLEAN)){
									j++;
									appLog.info("Successfully passed value to cutom label text box.");
								} else {
									appLog.error("Not Able to pass value to custom label text box.");
									notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
									continue;
								}
						} else {
							if(checkForAlert()){
								break;
							}
							appLog.error("Not able to click on add row link");
							notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
							continue;
						}
					}
					if(selectVisibleTextFromDropDown(driver, FindElement(driver, "//select[@id='a"+(j)+"aa' or @id='a"+(j+1)+"aa']", "Custom Label Text Field", action.BOOLEAN, 30), "Cutom Label Drop Down", (labelsWithLocation[i].split("-"))[1])){
						appLog.info("Successfully selected "+(labelsWithLocation[i].split("-"))[1]+" from the drop down.");
					} else {
						appLog.error("Not able to selecte "+(labelsWithLocation[i].split("-"))[1]+" from the drop down.");
						notSetLabels.add((labelsWithLocation[i].split("-"))[0]);
					}
				}
			}
			if(click(driver, getWatermarkingSaveButton(60), "Save Button", action.SCROLLANDBOOLEAN)){
				if(isAlertPresent(driver)){
					String msg = switchToAlertAndGetMessage(driver, 10, action.GETTEXT);
					switchToAlertAndAcceptOrDecline(driver, 10, action.ACCEPT);
					appLog.info(msg);
					driver.navigate().refresh();
					switchToFrame(driver, 10,getFrame(PageName.NavatarInvestorManager, 20));
					clickOnSideMenusTab(sideMenu.Watermarking);
//					notSetLabels.add("No watermarking label is set. Due to: "+msg);
					return null;
				} else {
					appLog.info("Successfully saved the watermarking setting.");
				}
			} else {
				appLog.error("Not able to save watermarking setting.");
				notSetLabels.add("Not able to save watermarking setting.");
			}
		} else {
			appLog.error("watermarking tab cannot be clicked, So won't be able to activate watermarking setting.");
		}
		return notSetLabels;
	}
	
	/**
	 * @author Akul Bhutani
	 * @param fd
	 * @return true/false
	 */
	public boolean verifyFileDistributerUI(fileDistributor fd) {
		boolean flag = true;
		if (getFileDistributorHead(30)!=null) {
			appLog.info("file distributor Head is successfully verified");
		}
		else {
			appLog.error("file distributor Head is not visible");
			flag = false;
		}
		if (fd == fileDistributor.BulkUpload) {
			if (getBulkUploadHeader(30)!=null) {
				appLog.info("bulk upload Header is sucessfully displayed");
			}
			else {
				appLog.error("bulk upload Header is not visible on nim page");
				flag = false;
			}
			if (getFileSplitterOrBulkUploadText(30).getText().trim().equals(NIMPageErrorMessage.bulkUploadMessage)) {
				appLog.info("bulk upload instructions text message is successfully verified");
			}
			else {
				appLog.error("instructions for bulk upload are not visible on nim page");
				flag = false;
			}
			if (mouseOverOperation(driver, getFileDistrToolTip(fd))) {
				ThreadSleep(3000);
				if (getTooltipFileDistText(fd,30).getText().trim().contains(NIMPageErrorMessage.bulkUploadTooltip1) &&(getTooltipFileDistText(fd,30).getText().trim().contains(NIMPageErrorMessage.bulkUploadTooltip2)) && (getTooltipFileDistText(fd,30).getText().trim().contains(NIMPageErrorMessage.bulkUploadTooltip3)) && (getTooltipFileDistText(fd,30).getText().trim().contains(NIMPageErrorMessage.bulkUploadTooltip4))) {
					appLog.info("complete tooltip message is successfully verified");
				}
				else {
					appLog.error("complete tooltip message cannot be verified");
					flag = false;
				}
			if ((getTooltipFileDistText(fd,30).getText().trim().contains(NIMPageErrorMessage.bulkUploadTooltip1))) {
				appLog.info("correct text is present in tool tip message of bulk upload");
			}
			else {
				appLog.error("tool tip message is wrong of bulk upload");
				flag = false;
			}
			 if (getTooltipFileDistText(fd,30).getText().trim().contains(NIMPageErrorMessage.bulkUploadTooltip2)) {
				 appLog.info("2nd part of tooltip is correct on nim page");
			 }
			 else {
				 appLog.error("tool tip is incorrect for bulk upload on nim page");
				 flag = false;
			 }
			}
		}
		else {
			if (getFileSplitterHead(30)!=null) {
				appLog.info("file Splitter Header text is successfully verified");
			}
			else {
				appLog.error("file Splitter Header text is not visible");
				flag = false;
			}
			if (getFileSplitterOrBulkUploadText(30).getText().trim().contains(NIMPageErrorMessage.fileSplitterMeessage)) {
				appLog.info("FileSplitter instructions text message is successfully verified");
			}
			else {
				appLog.error("instructions for FileSplitter are not visible on nim page");
				flag = false;
			}
		 	if (mouseOverOperation(driver, getFileDistrToolTip(fd))) {
			 ThreadSleep(3000);
			 String a=getTooltipFileDistText(fd,30).getText().trim();
			 appLog.info("tooltip: '"+a);
			 if (a.contains(NIMPageErrorMessage.fileSplitterTooltip1)) {
				 appLog.info("1 successful");
			 }
			 else {
				 appLog.error("1 fail");
				 appLog.error("expected is"+NIMPageErrorMessage.fileSplitterTooltip1);
			 }
			 if (a.contains(NIMPageErrorMessage.fileSplitterTooltip2)) {
				 appLog.info("2 successful"); 
			 }
			 else {
				 appLog.error("2 fail");
				 appLog.error("expected"+NIMPageErrorMessage.fileSplitterTooltip2);
			 }
			 if (a.contains(NIMPageErrorMessage.fileSplitterTooltip3)) {
				 appLog.info("correct text is present in tool tip message of fileSplitter");
			 }
			 else {
				 appLog.error("3 fail");
				 appLog.error("expected"+NIMPageErrorMessage.fileSplitterTooltip3);
				 flag = false;
			 }
		 }
		}
		if (getUseSuggestedNaming(30)!=null) {
			appLog.info("use suggested naming option is displayed");
		}
		else {
			appLog.error("suggested naming convention is not displayed");
			flag = false;
		}
		if (getUserReverseNaming(30)!=null) {
			appLog.info("reverse naming convention option is successfully displayed");
		}
		else {
			appLog.error("reverse naming convention is not displayed");
			flag = false;
		}
		if (isSelected(driver, getDefaultNamingRadioButton(30), "reverse naming radio button")) {
			appLog.info("default naming radio button is by default selected");
		}
		else {
			appLog.error("default naming radio button is not selected");
			flag = false;
		}
		if (getFileDistributorSaveButton(fd)!=null) {
			appLog.info("save button of "+fd.toString()+" is successfully displayed");
		}
		else {
			appLog.error("save button is not visible on nim page");
			flag = false;
		}
		if (getNimRegisterPopupCancelButton(30)!=null) {
			appLog.info("cancel button on nim page is successfully displayed");
		}
		else {
			appLog.error("cancel button is not visible on nim page");
			flag = false;
		}
		return flag;
	}

	/**
	 * @author Akul Bhutani
	 * @return true/false
	 */
	public boolean deleteAllFolderTemplates() {
		boolean flag = true;
		selectVisibleTextFromDropDown(driver, getDropdownTempType(30), "dropdown template type", "All Templates");
		if (FindElement(driver, "//span[contains(@id,'templateGrid-cell-0')]//a", "template names", action.BOOLEAN, 10)!=null) {
			List<WebElement> listOfTempNames = FindElements(driver,"//span[contains(@id,'templateGrid-cell-0')]//a" , "first row of template names");
			while (true) {
				if (click(driver, listOfTempNames.get(0), "template name at first row", action.BOOLEAN)) {
					click(driver, getDeleteTempBtn(30), "delete template button", action.BOOLEAN);
					if (click(driver, getDeleteTemplateYesBtn(30), "yes button", action.BOOLEAN)) {
						if (clickOnSideMenusTab(sideMenu.FolderTemplates)) {
							appLog.info("successfully deleted 1 folder template");
						}
						else {
							flag = false;
							appLog.error("folder template could not be deleted");
						}
					}
					else {
						flag = false;
						appLog.error("delete template yyes button is not clickable");
					}
				}
				else {
					flag = false;
					appLog.error("link of template name is not clickable");
				}
				selectVisibleTextFromDropDown(driver, getDropdownTempType(30), "dropdown template type", "All Templates");
				ThreadSleep(5000);
				if (FindElement(driver, "//span[contains(@id,'templateGrid-cell-0')]//a", "row of template name", action.BOOLEAN, 10)!=null) {
					listOfTempNames = FindElements(driver, "//span[contains(@id,'templateGrid-cell-0')]//a", "first row of template names");	
				}
				else {
					appLog.info("successfully deleted all templates");
					break;
				}
			}
		}
		else {
			appLog.info("checking if no data to display is present.....");
			if (getText(driver, FindElement(driver, "//span[contains(@id,'templateGrid-cell-0')]//b//font", "no data to display", action.BOOLEAN, 10), "no data to display", action.BOOLEAN).contains(NIMPageErrorMessage.noDataToDisplay)) {
				appLog.info("no data to display is successfully found, no template is left now");
			}
			else {
				flag = false;
				appLog.error("could not find no data to display here");
			}
		}
		return flag;
	}
	
	/**
	 * @author Akul Bhutani
	 * @param TemplateName
	 * @param Description
	 * @param User
	 * @param CreatedOnDate
	 * @return true/false
	 */
	public boolean verifyfolderTemplateGrid(String TemplateName, String Description, String User,
			String CreatedOnDate) {
		WebElement FolderTemplateName = FindElement(driver,
				"//span[@id='templateGrid-rows']//a[@title='" + TemplateName + "']", "Template Name",
				action.SCROLLANDBOOLEAN, 60);
		WebElement FolderTemplateDescription = FindElement(driver,
				"(//span[@id='templateGrid-rows']//a[@title='" + TemplateName + "']/..//following-sibling::span)[1]",
				"Template Name Description", action.SCROLLANDBOOLEAN, 60);
		WebElement CreatedBy = FindElement(driver,
				"(//span[@id='templateGrid-rows']//a[@title='" + TemplateName + "']/..//following-sibling::span)[2]",
				"User Name", action.SCROLLANDBOOLEAN, 60);
		WebElement CreatedOn = FindElement(driver,
				"(//span[@id='templateGrid-rows']//a[@title='" + TemplateName + "']/..//following-sibling::span)[3]",
				"Created On Date", action.SCROLLANDBOOLEAN, 60);
		System.out.println("Go to Compare>>"+FolderTemplateName.getText().trim()+"with Expected Value"+TemplateName);
		System.out.println("Go to Compare>>"+FolderTemplateDescription.getText().trim()+"with Expected Value"+Description);
		System.out.println("Go to Compare>>"+CreatedBy.getText().trim()+"with Expected Value"+User);
		System.out.println("Go to Compare>>"+CreatedOn.getText().trim()+"with Expected Value"+CreatedOnDate);
		
		if (FolderTemplateName.getText().trim().equalsIgnoreCase(TemplateName)
				&& FolderTemplateDescription.getText().trim().equalsIgnoreCase(Description)
				&& CreatedBy.getText().trim().equalsIgnoreCase(User)
				&& (CreatedOnDate.contains(CreatedOn.getText().trim()))) {
		
		appLog.info("Folder template grid data is verified for :"+TemplateName);	
			
			return true;
		}
				
		return false;
	}

	/**
	 * @author Akul Bhutani
	 * @param tempName
	 * @param desc
	 * @param createdBy
	 * @param date
	 * @return true/false
	 */
	public boolean verifyTemplatesPresentInFolderTemplates(String tempName,
			String desc, String createdBy, String date) {

		String workSpaceXpath = "";
		String tempNameArr[]=tempName.split("<break>");
		boolean flag = true;
		String dcTemp=null;
		String dcDescr = null;
		String dcCreatedBy = null;
		String dcDate = null;
		

			String tempNameXpath="";
			String descXpath="";
			String createdByXpath="";
			String dateXpath="";
			
			tempNameXpath = workSpaceXpath + "//span[contains(@id,'templateGrid-cell-0')]//a";
			descXpath = workSpaceXpath + "//span[contains(@id,'templateGrid-cell-1')]/span";
			createdByXpath = workSpaceXpath + "//span[contains(@id,'templateGrid-cell-2')]/span";
			dateXpath = workSpaceXpath + "//span[contains(@id,'templateGrid-cell-3')]/span";

			List<WebElement> tempNames = FindElements(driver, tempNameXpath, "Document Name List");

			if (!tempNames.isEmpty()) {

				List<WebElement> descList = FindElements(driver, descXpath, "Document Status List");
				List<WebElement> createdByList= FindElements(driver, createdByXpath, "Document Uploaded By List");
				List<WebElement> dateList = FindElements(driver, dateXpath, "Document Uploaded On List");
				System.err.println("/n/n/tSize  :      Doc : "+tempNames.size()+" user : "+createdByList.size()+" dateList : "+dateList.size()+" uploaded on : "+dateList.size()+"/n/n");

				for (int i = 0; i < tempNameArr.length; i++) {

					for (int j = 0; j < tempNames.size(); j++) {
						dcTemp=tempNames.get(j).getText().trim();
						dcDescr = descList.get(j).getAttribute("title").trim();
						dcCreatedBy = createdByList.get(j).getText().trim();
						dcDate = dateList.get(j).getText().trim();
						if (dcTemp.equalsIgnoreCase(tempNameArr[i]) && dcDescr.equalsIgnoreCase(desc) && dcCreatedBy.equalsIgnoreCase(createdBy)
								&& (dcDate.contains(date) || previousOrForwardDate(-1, "MM/dd/yyyy").contains(date))) {

							appLog.info(tempNameArr[i]+" is present having desc "+desc+" created By "+createdBy+" Uploaded on"+date + " "+ " : "
									);
							break;
						}
						
						if (j == tempNames.size() - 1) {
							appLog.error(tempNameArr[i] + " File not Present having desc "+desc+" created By "+createdBy+" Uploaded on"+date +" : "
									);
							BaseLib.sa.assertTrue(false,tempNameArr[i] + " File not Present having desc "+desc+" created By "+createdBy+" Uploaded on"+date +" : "
									);
							flag = false;
						}

					}

				}

			} else {
				appLog.info("Document List is Empty "+" : ");
				BaseLib.sa.assertTrue(false, "Document List is Empty "+" : ");
			}

		return flag;
	}

	/**
	 * @author Akul Bhutani
	 * @param folderName
	 * @return true/false
	 */
	public boolean verifyPresenceOfFolderNameInFolderStructure(String folderName) {
		WebElement e=isDisplayed(driver, FindElement(driver, "//a//span//label[contains(text(),'"+folderName+"')]", "folder name in sub folder", action.SCROLLANDBOOLEAN, 30), "visibility", 10,"folder name in sub folder" );
		if (e!=null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * @author Akul Bhutani
	 * @param timeOut
	 * @return true/false
	 */
	public boolean deleteAllFolderTemplate(int timeOut){
		boolean flag = true;
		switchToFrame(driver, 30, getNIMTabFrame(30));
		if(click(driver, getFolderTemplatetab(timeOut), "Folder Template Tab", action.BOOLEAN)){
			if(selectVisibleTextFromDropDown(driver, getFolderTemplateDisplayDropDown(timeOut), "Folder Template display drop down", "All Templates")){
				List<WebElement> folderTemplates = FindElements(driver, "//span[contains(@id,'templateGrid-rows-start')]/following-sibling::span[contains(@id,'templateGrid-row-')]/span[contains(@id,'templateGrid-cell-0')]/a", "Folder Templates");
				for(int i = 0; i < folderTemplates.size(); i++){
					folderTemplates = FindElements(driver, "//span[contains(@id,'templateGrid-rows-start')]/following-sibling::span[contains(@id,'templateGrid-row-')]/span[contains(@id,'templateGrid-cell-0')]/a", "Folder Templates");
					WebElement ele = folderTemplates.get(i);
					String text = getText(driver, folderTemplates.get(i), "", action.BOOLEAN);
					if(click(driver, ele, "Folder template", action.BOOLEAN)){
						if(click(driver, getDeleteTemplateButton(timeOut), "Delete Template button", action.BOOLEAN)){
							if(click(driver, getDeleteFolderTemplateConfirmationYesButton(timeOut), "Yes button", action.BOOLEAN)){
								appLog.info("Folder template '"+text+"' successfully deleted.");
								ThreadSleep(5000);
								if(selectVisibleTextFromDropDown(driver, getFolderTemplateDisplayDropDown(timeOut), "Folder Template display drop down", "All Templates"));
							} else {
								appLog.error("Delete template confirmation yes button cannot be clicked, So cannot delete folder template '"+text+"'.");
								flag = false;
							}
						} else {
							appLog.error("Delete Template button cannot be clicked, So cannot delete template '"+text+"'.");
							flag = false;
						}
					} else {
						appLog.error("'"+text+"' Folder template cannot be clicked, So cannot delete the template.");
						flag = false;
					}
				}
				
			} else {
				appLog.error("Cannot select All Templates from the drop down, So cannot delete folder template.");
				flag = false;
			}
		} else {
			appLog.error("Folder Template Tab cannot be clicked, So cannot delete folder template.");
			flag = false;
		}
		return flag;
	}
}
