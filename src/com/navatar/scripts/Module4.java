/**
 * 
 */
package com.navatar.scripts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static com.navatar.generic.CommonVariables.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import com.navatar.generic.BaseLib;
import com.navatar.generic.CommonVariables;
import com.navatar.generic.EmailLib;
import com.navatar.generic.CommonLib.FolderType;
import com.navatar.generic.CommonLib.Mode;
import com.navatar.generic.CommonLib.PageName;
import com.navatar.generic.CommonLib.SortOrder;
import com.navatar.generic.CommonLib.TabName;
import com.navatar.generic.CommonLib.WorkSpaceAction;
import com.navatar.generic.CommonLib.Workspace;
import com.navatar.generic.CommonLib.action;
import com.navatar.generic.CommonLib.customTabActionType;
import com.navatar.generic.CommonLib.excelLabel;
import com.navatar.generic.CommonLib.investorSideWorkSpace;
import com.navatar.generic.CommonLib.userType;
import com.navatar.generic.ExcelUtils;
import com.navatar.generic.SoftAssert;
import com.navatar.pageObjects.AllFirmsPageBusinesslayer;
import com.navatar.pageObjects.BasePageBusinessLayer;
import com.navatar.pageObjects.CommitmentPageBusinessLayer;
import com.navatar.pageObjects.ContactPageBusinessLayer;
import com.navatar.pageObjects.DisclaimerPageBussinessLayer;
import com.navatar.pageObjects.FundRaisingPageBusinessLayer;
import com.navatar.pageObjects.FundsPageBusinessLayer;
import com.navatar.pageObjects.FundsPageErrorMessage;
import com.navatar.pageObjects.HomePageBusineesLayer;
import com.navatar.pageObjects.InstitutionPageBusinessLayer;
import com.navatar.pageObjects.InvestorFirmPageBusinesslayer;
import com.navatar.pageObjects.InvestorProfileBusinessLayer;
import com.navatar.pageObjects.LoginPageBusinessLayer;
import com.navatar.pageObjects.NIMPageBusinessLayer;
import com.navatar.pageObjects.NavatarInvestorAddOnsErrorMessage;
import com.navatar.pageObjects.NavatarInvestorAddonsPageBusinessLayer;
import com.navatar.pageObjects.PartnershipPageBusinessLayer;
import com.relevantcodes.extentreports.LogStatus;

import static com.navatar.generic.AppListeners.appLog;
import static com.navatar.generic.CommonLib.*;

/**
 * @author Ankur Rana
 *
 */
public class Module4 extends BaseLib{

	public String passwordResetLink = null;
	Scanner scn = new Scanner(System.in);

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc000_CreateCRMUser1InstallPackageAndThenCreatePassword() {

		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		String[] splitedUserName = removeNumbersFromString(Org3CRMUser1LastName); 
		String UserLastName =splitedUserName[0]+bp.generateRandomNumber();
		ExcelUtils.writeData(UserLastName, "Users", excelLabel.Variable_Name, "Org3User1", excelLabel.User_Last_Name);
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
		String parentWindow = null;
		boolean flag=false;
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
					if (bp.createPEUser(environment, mode,Org3CRMUser1FirstName, Org3CRMUser1LastName, cp.generateRandomEmailId(), CRMUserLicense,
							CRMUserProfile)) {
						appLog.info("PE User 1 created Successfully");
						if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
							ThreadSleep(5000);
							switchToDefaultContent(driver);
							switchToFrame(driver, 20, bp.getSetUpPageIframe(20));
							System.err.println(">>><<<<<<<<<<<<");
						}
						String emailID = isDisplayed(driver, bp.getUserEmailIDLabeltext(60), "visibility", 60,
								"User Email ID Text Label", action.THROWEXCEPTION).getText().trim();
						ExcelUtils.writeData(emailID, "Users", excelLabel.Variable_Name, "Org3User1", excelLabel.User_Email);
						flag = true;
						break;

					}
				}
			} catch (Exception e) {
				appLog.error("could not find setup link, trying again..");
			}
		}
		if (flag) {
			if (bp.installedPackages(environment, mode,Org3CRMUser1FirstName,Org3CRMUser1LastName)) {
				appLog.info("PE Package is installed Successfully in CRM User1: " + Org3CRMUser1FirstName + " "+ Org3CRMUser1LastName);
			} else {
				appLog.error(
						"Not able to install PE package in CRM User1: " + Org3CRMUser1FirstName + " "+ Org3CRMUser1LastName);
				sa.assertTrue(false,
						"Not able to install PE package in CRM User1: " + Org3CRMUser1FirstName + " "+ Org3CRMUser1LastName);
			}
			ThreadSleep(5000);
			if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				driver.close();
				driver.switchTo().window(parentWindow);
			}
		}

		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		driver.close();
		config(ExcelUtils.readDataFromPropertyFile("Browser"));
		bp = new BasePageBusinessLayer(driver);
		List<String> lst=new ArrayList<String>();
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
			sa.assertTrue(false, "Password is not set for user1");
		}
		if (lp.switchToLighting()) {
			appLog.info("Successfully Switched to Lighting");

		} else{
			appLog.error("Not Able to Switched to Lighting");
			sa.assertTrue(false, "Not Able to Switched to Lighting");
		}


	//	lp.CRMLogin(Org3CRMUser1EmailID, adminPassword);
		String addRemoveTabName="Navatar Investor Manager"+","+"Partnerships"+"Commitments";
		if (bp.addTab_Lighting(mode, addRemoveTabName, 5)) {
			log(LogStatus.INFO,"Tab added : "+addRemoveTabName,YesNo.No);
		} else {
			log(LogStatus.FAIL,"Tab not added : "+addRemoveTabName,YesNo.No);
			sa.assertTrue(false, "Tab not added : "+addRemoveTabName);
		}	
		ThreadSleep(1000);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc001_VerifyErrorMsgOnNavetarInvestorAddOnsTab(){
		LoginPageBusinessLayer lp= new LoginPageBusinessLayer(driver);
		lp.CRMLogin(Org3CRMUser1EmailID, adminPassword);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		if(lp.clickOnTab(TabName.NavatarInvestorAddOns)){
			  switchToFrame(driver,2,lp.getNavatarInvestorAddOnParentFrame(10));
			String text = getAttribute(driver, niam.getDisclaimerTab(30), "Disclaimer Tab", "class");
			if(text.equalsIgnoreCase("Selected")){
				appLog.info("Disclaimer Tab is selected.");
			} else {
				appLog.error("Disclaimer Tab is not selected.");
				sa.assertTrue(false,"Disclaimer Tab is not selected.");
			}
			if(switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60))){
				System.err.println("Successfully switched to frame.");
			}
			text = getText(driver, niam.getInsufficientPrivilegeErrorMessage(60), "Error message", action.BOOLEAN);
			System.err.println(text);
			if(text.equalsIgnoreCase(NavatarInvestorAddOnsErrorMessage.insufficientPrivilegeErrorMessage)){
				appLog.info("Insufficient Error message is verified.");
			} else {
				appLog.error("Insufficient Error message is not verified.Expected: "+NavatarInvestorAddOnsErrorMessage.insufficientPrivilegeErrorMessage+"\tActual: "+text);
				sa.assertTrue(false,"Insufficient Error message is not verified.Expected: "+NavatarInvestorAddOnsErrorMessage.insufficientPrivilegeErrorMessage+"\tActual: "+text);
			}
		} else {
			appLog.error("NI Addon Tab cannot be clicked.");
			sa.assertTrue(false,"NI Addon Tab cannot be clicked.");
		}
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc002_VerifyErrorMessageAfterVFPageAccess(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		String parentWindow = null;
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if(lp.clickOnSetUpLink(environment, mode)) {
			appLog.info("clicked on user menu tab & set up Link");
			if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				 parentWindow = switchOnWindow(driver);
				if (parentWindow == null) {
					sa.assertTrue(false,"No new window is open after click on setup link");
					appLog.error("No new window is open after click on setup link");
					exit("No new window is open after click on setup link");
				}
			}
		
				if(click(driver, lp.getExpandUserIcon(environment,mode,60), "Manage User Icon", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on manage user expand icon");
					if(click(driver, lp.getUsersLink(environment,mode,60), "User Link", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on users link");
						if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
							switchToDefaultContent(driver);
							switchToFrame(driver, 20, lp.getSetUpPageIframe(20));
							System.err.println(">>><<<<<<<<<<<<");
						}else{
							System.err.println(">>>11111111111111<<<<<<<<<<<<");	
						}
						WebElement ele = FindElement(driver, "//a[text()='PE Standard User']", "PE Standard User visible", action.BOOLEAN, 60);
						if(click(driver, ele, "Profile", action.BOOLEAN)){
							if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
								switchToDefaultContent(driver);
								switchToFrame(driver, 20, lp.getSetUpPageIframe(20));
								System.err.println(">>><<<<<<<<<<<<");
							}else{
								System.err.println(">>>11111111111111<<<<<<<<<<<<");	
							}
							
							if(click(driver, lp.getEnableVisualForcePageAccessLink(60), "Visiual Force Page Access Link", action.BOOLEAN)){
								if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
									switchToDefaultContent(driver);
									switchToFrame(driver, 20, lp.getSetUpPageIframe(20));
									System.err.println(">>><<<<<<<<<<<<");
								}else{
									System.err.println(">>>11111111111111<<<<<<<<<<<<");	
								}
								if(click(driver, lp.getEnableVisualForcePageAccessEditButton(60), "Edit Button", action.BOOLEAN)){
									if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
										switchToDefaultContent(driver);
										switchToFrame(driver, 20, lp.getSetUpPageIframe(20));
										System.err.println(">>><<<<<<<<<<<<");
									}else{
										System.err.println(">>>11111111111111<<<<<<<<<<<<");	
									}
									if(selectVisibleTextFromDropDown(driver, lp.getVFPageMultiSelect(60), "VF Page multi select", "PE_Disclaimers_Setup")){
										if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
											switchToDefaultContent(driver);
											switchToFrame(driver, 20, lp.getSetUpPageIframe(20));
											System.err.println(">>><<<<<<<<<<<<");
										}else{
											System.err.println(">>>11111111111111<<<<<<<<<<<<");	
										}
										if(click(driver, lp.getMultiSelectAddButton(30), "Add button", action.BOOLEAN)){
											if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
												switchToDefaultContent(driver);
												switchToFrame(driver, 20, lp.getSetUpPageIframe(20));
												System.err.println(">>><<<<<<<<<<<<");
											}else{
												System.err.println(">>>11111111111111<<<<<<<<<<<<");	
											}
											if(click(driver, lp.getSaveButton(60), "Save Button", action.BOOLEAN)){
												appLog.info("Successfully provide VF Page access.");
												if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
													switchToDefaultContent(driver);
													driver.close();
													driver.switchTo().window(parentWindow);
												}
											} else {
												appLog.error("Save button cannot be clicked, So cannot give VF Page access,");
												sa.assertTrue(false,"Save button cannot be clicked, So cannot give VF Page access,");
											}
										} else {
											appLog.error("Cannot click on ad button, so cannot give VF Page access.");
											sa.assertTrue(false,"Cannot click on ad button, so cannot give VF Page access.");
										}
									} else {
										appLog.error("Cannot select value from the multiselect, So cannot give VF page access.");
										sa.assertTrue(false,"Cannot select value from the multiselect, So cannot give VF page access.");
									}
								} else {
									appLog.error("Cannot click on the edit icon, So cannnot give VF Page access.");
									sa.assertTrue(false,"Cannot click on the edit icon, So cannnot give VF Page access.");
								}
							} else {
								appLog.error("Cannot click on access link, So cannot give VF Page access.");
								sa.assertTrue(false,"Cannot click on access link, So cannot give VF Page access.");
							}
						} else {
							appLog.error("Profile link cannot be clicked, So cannot give VF Page access.");
							sa.assertTrue(false,"Profile link cannot be clicked, So cannot give VF Page access.");
						}
					}else {
						appLog.error("Not able to click on users link so cannot give VF Page access.");
						sa.assertTrue(false,"Not able to click on users link so cannot give VF Page access.");
					}
				}else {
					appLog.error("Not able to click on manage user expand icon so cannot give VF Page access.");
					sa.assertTrue(false,"Not able to click on manage user expand icon so cannot give VF Page access.");
				}
			
		}else {
			appLog.error("Not able to click on user menu tab & set up link so cannot give VF Page access.");
			sa.assertTrue(false,"Not able to click on user menu tab & set up link so cannot give VF Page access.");
		}
		lp.CRMlogout(environment,mode);
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		lp.CRMLogin(Org3CRMUser1EmailID, adminPassword);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		if(lp.clickOnTab(TabName.NavatarInvestorAddOns)){
			if(switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60))){
				System.err.println("Successfully switched to frame.");
				switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
			}
			String text = getText(driver, niam.getNIFunctionlityDisabledErrorMessage(60), "NI Functionality Disabled Error Message", action.BOOLEAN);
			if(text.equalsIgnoreCase(NavatarInvestorAddOnsErrorMessage.NIFunctionalityDisabledErrorMessage)){
				appLog.info("NI Functionality Disabled Error Message is verified.");
			} else {
				appLog.error("NI Functionality Disabled Error Message is not verified.");
				sa.assertTrue(false,"NI Functionality Disabled Error Message is not verified.");
			}
		} else {
			appLog.error("NI Addon cannot be clicked, So cannot check the error message.");
			sa.assertTrue(false,"NI Addon cannot be clicked, So cannot check the error message.");
		}
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc003_ProvideAccessToCRMUser1AndVerifyNIAddOn(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		boolean flag = true;
		if(lp.clickOnTab(TabName.NIMTab)){
			if(nim.giveAccessToUserInNIMTabFromAdmin(environment,mode,Org3CRMUser1FirstName+" "+Org3CRMUser1LastName, accessType.InternalUserAccess)){
				appLog.info("Successfully provided access to user "+Org3CRMUser1FirstName+" "+Org3CRMUser1LastName);
			} else {
				refresh(driver);
				if(nim.giveAccessToUserInNIMTabFromAdmin(environment,mode,Org3CRMUser1FirstName+" "+Org3CRMUser1LastName, accessType.InternalUserAccess)){
					appLog.info("Successfully provided access to user "+Org3CRMUser1FirstName+" "+Org3CRMUser1LastName);
				} else {
					appLog.error("Cannot provided access to user "+Org3CRMUser1FirstName+" "+Org3CRMUser1LastName);
					sa.assertTrue(false,"Cannot provided access to user "+Org3CRMUser1FirstName+" "+Org3CRMUser1LastName);
					flag = false;
				}
			}
		} else {
			appLog.error("NIM tab cannot be clicked, so canot continue with the testcase.");
			sa.assertTrue(false,"NIM tab cannot be clicked, so canot continue with the testcase.");
			flag=false;
		}
		driver.close();
		config(browserToLaunch);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		nim = new NIMPageBusinessLayer(driver);
		lp = new LoginPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		lp.CRMLogin(Org3CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.NavatarInvestorAddOns)){
			if(switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60))){
				System.err.println("Successfully switched to frame.");
				switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
			}
			String text = trim(getText(driver, niam.getNIFunctionlityDisabledErrorMessage(60), "Error Message", action.BOOLEAN));
			if(text.equalsIgnoreCase(NavatarInvestorAddOnsErrorMessage.accessToCreateWorkspaceErrorMsg)){
				appLog.info("Access to create workspace error message verified successfully.");
			} else {
				appLog.error("Access to create workspace error message is not verified.Expected: "+NavatarInvestorAddOnsErrorMessage.accessToCreateWorkspaceErrorMsg+"\tActual: "+text);
				sa.assertTrue(false,"Access to create workspace error message is not verified.Expected: "+NavatarInvestorAddOnsErrorMessage.accessToCreateWorkspaceErrorMsg+"\tActual: "+text);
			}
			switchToDefaultContent(driver);
		} else {
			appLog.error("Navatar Investor Add ons tab cannot be clicked, So cannot check the error message on page.");
			sa.assertTrue(false,"Navatar Investor Add ons tab cannot be clicked, So cannot check the error message on page.");
			
		}
		if(bp.clickOnTab(TabName.NIMTab)){
			if(nim.NIMRegistration(environment,mode,userType.CRMUser, Org3CRMUser1FirstName, Org3CRMUser1LastName)){
				appLog.info("Crm user "+Org3CRMUser1FirstName+" "+Org3CRMUser1LastName+" is successfull registered.");
			} else {
				appLog.error("Not able to register crm user "+Org3CRMUser1FirstName+" "+Org3CRMUser1LastName);
				sa.assertTrue(false,"Not able to register crm user "+Org3CRMUser1FirstName+" "+Org3CRMUser1LastName);
			}
			switchToDefaultContent(driver);
		} else {
			appLog.error("Not able to click on nim tab, So cannot register user "+Org3CRMUser1FirstName+" "+Org3CRMUser1LastName);
			sa.assertTrue(false,"Not able to click on nim tab, So cannot register user "+Org3CRMUser1FirstName+" "+Org3CRMUser1LastName);
		}
		if(lp.clickOnTab(TabName.NavatarInvestorAddOns)){
			if(switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60))){
				System.err.println("Successfully switched to frame.");
				switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
			}
			String text = trim(getText(driver, niam.getDisclaimerPageHeader(60), "Header", action.BOOLEAN));
			if(text.equalsIgnoreCase("Disclaimer")){
				appLog.info("Disclaimer page is opening.");
			} else {
				appLog.error("Disclaimer page is not opening.Expected: Disclaimer"+"\tActual: "+text);
				sa.assertTrue(false,"Disclaimer page is not opening.Expected: Disclaimer"+"\tActual: "+text);
			}
			switchToDefaultContent(driver);
		} else {
			appLog.error("Navatr investor add on tab is not opening.");
			sa.assertTrue(false,"Navatr investor add on tab is not opening.");
		}
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc004_CheckDisclaimerUI(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		if(lp.clickOnTab(TabName.NavatarInvestorAddOns)){
			if(switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60))){
				System.err.println("Successfully switched to frame.");
				switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
			}
			String title = getTitle(driver);
			if(title.contains("Navatar Investor Add-ons")){
				appLog.info("title is verified.");
			} else {
				appLog.error("title is not verified.");
				sa.assertTrue(false,"title is not verified.");
			}
			WebElement ele = FindElement(driver, "//strong[text()='Select Fund:']/following-sibling::select", "Select Fund and drop down", action.BOOLEAN, 30);
			if(ele!=null){
				appLog.info("Select fund label and drop down is verified.");
			} else {
				appLog.error("Select fund label and drop down is not present on the UI.");
				sa.assertTrue(false,"Select fund label and drop down is not present on the UI.");
			}
			List<WebElement> allOptions=allOptionsInDropDrop(driver, ele, "Fund select drop down.");
			if(allOptions.isEmpty()){
				appLog.info("No Option is selected in the drop down and is verified.");
			} else {
				appLog.error("Option is selected in the drop down and is not verified.Expected: Blank\tActual: "+getSelectedOptionOfDropDown(driver, ele, "Fund select drop down", "text"));
				sa.assertTrue(false,"Option is selected in the drop down and is not verified.Expected: Blank\tActual: "+getSelectedOptionOfDropDown(driver, ele, "Fund select drop down", "text"));
			}
			ele = FindElement(driver, "//strong[text()='Status:']/following-sibling::b", "Status Label", action.BOOLEAN, 30);
			String text = trim(getText(driver, ele, "Status label", action.BOOLEAN));
			if(text.contains("Inactive")){
				appLog.info("Status is successfully verified.");
			} else {
				appLog.error("Status is not verified.Expected: Inactive\tActual: "+text);
				sa.assertTrue(false,"Status is not verified.Expected: Inactive\tActual: "+text);
			}
			text = getAttribute(driver, ele, "Color of status", "style");
			if(text.contains("background: red; color: white;")){
				appLog.info("Color of the status is verified.");
			} else {
				appLog.error("Color of the status is not verified.Expected: red; color: white;\tActual: "+text);
				sa.assertTrue(false,"Color of the status is not verified.Expected: red; color: white;\tActual: "+text);
			}
			ele = FindElement(driver, "//strong[text()='Status:']/../preceding-sibling::div//a", "New Disclaimer Button", action.BOOLEAN, 30);
			text = getAttribute(driver, ele, "New Disclaimer Button", "style");
			if(text.contains("opacity: 0.5;")){
				appLog.info("New disclaimer Button is in disabled mode.");
			} else {
				appLog.error("New disclaimer Button is not in disabled mode.");
				sa.assertTrue(false,"New disclaimer Button is not in disabled mode.");
			}
			List<WebElement> eles = FindElements(driver, "//span[@id='grid_DefaultFoldersView1-headers']/span[contains(@id,'grid_DefaultFoldersView1-header-')]", "Headers of grid");
			for(int i = 0; i < eles.size(); i++){
				text = getAttribute(driver, eles.get(i), "Hearder", "title");
				if(text.equalsIgnoreCase("Action")||text.equalsIgnoreCase("Name")||text.equalsIgnoreCase("Statistics")||text.equalsIgnoreCase("Last Activated On")||text.equalsIgnoreCase("Created Date")){
					appLog.info(text+" header si verified.");
				} else {
					appLog.error("Headers are not verified.");
					sa.assertTrue(false,"Headers are not verified.");
				}
			}
			ele = FindElement(driver, "//span[@id='grid_DefaultFoldersView1-cell-1-0']/span", "No data to display msg", action.BOOLEAN, 30);
			text = getAttribute(driver, ele, "No data to display msg", "title");
			if(text.contains(NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage)){
				appLog.info(NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage+" error msg is verified.");
			} else {
				appLog.error(NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage+" error msg is verified.expected: "+NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage+"\tActual: "+text);
				sa.assertTrue(false,NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage+" error msg is verified.expected: "+NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage+"\tActual: "+text);
			}
			ele = FindElement(driver, "//span[@id='disclaimerCountSpan']", "Disclaimer Count", action.BOOLEAN, 30);
			text = getText(driver, ele, "Disclaimer Count", action.BOOLEAN);
			List<Integer> count=getIntegerFromString(text);
			if(!count.isEmpty()){
				if(count.get(0)==0)
					appLog.info("count is verified.");
				else {
					appLog.error("Count is not verified. expected: 0\tActual: "+count);
					sa.assertTrue(false,"Count is not verified. expected: 0\tActual: "+count);
				}
			} else {
				appLog.error("Disclaimer count is not showing.");
				sa.assertTrue(false,"Disclaimer count is not showing.");
			}
			ele = FindElement(driver, "//span[contains(@id,'Disclaimer:disclaimerGridBottomP')]//td[1]/div", "Active Disclaimer Stats", action.BOOLEAN, 30);
			text = trim(getText(driver, ele, "Active Disclaimer Stats", action.BOOLEAN));
			if(text.contains("Active Disclaimer Statistics:")&&text.contains("None")){
				appLog.info("Active disclaimer stats is verified.");
			} else {
				appLog.error("Active disclaimer stats is not verified.Expected: Active Disclaimer Statistics: None\tActual: "+text);
				sa.assertTrue(false,"Active disclaimer stats is not verified.Expected: Active Disclaimer Statistics: None\tActual: "+text);
			}
			ele = FindElement(driver, "//span[contains(@id,'Disclaimer:disclaimerGridBottomP')]//td[2]/div", "Accepted Disclamer", action.BOOLEAN, 30);
			text = trim(getText(driver, ele, "Accpted Siclaimer", action.BOOLEAN));
			if(text.contains("Accepted")&&text.contains("0")){
				appLog.info("Accepted label and count is verified");
			} else {
				appLog.error("Accepted label and count is not verified.Expected: Accepted: 0\tActual: "+text);
				sa.assertTrue(false,"Accepted label and count is not verified.Expected: Accepted: 0\tActual: "+text);
			}
			ele = FindElement(driver, "//span[contains(@id,'Disclaimer:disclaimerGridBottomP')]//td[2]/div/img", "Accepted Disclamer img", action.BOOLEAN, 30);
			text = getAttribute(driver, ele, "Accepted Disclaimer img", "src");
			if(text.contains("approved_new1.png")){
				appLog.info("Accepted Disclaimer img is verified.");
			} else {
				appLog.error("Accepted Disclaimer img is not verified.Expected: approved_new1.png\tActula: "+text);
				sa.assertTrue(false,"Accepted Disclaimer img is not verified.Expected: approved_new1.png\tActula: "+text);
			}
			ele = FindElement(driver, "//span[contains(@id,'Disclaimer:disclaimerGridBottomP')]//td[3]/div", "Waiting Disclamer", action.BOOLEAN, 30);
			text = trim(getText(driver, ele, "Waiting Diclaimer", action.BOOLEAN));
			if(text.contains("Waiting")&&text.contains("0")){
				appLog.info("Waiting label and count is verified");
			} else {
				appLog.error("Waiting label and count is not verified.Expected: Waiting: 0\tActual: "+text);
				sa.assertTrue(false,"Waiting label and count is not verified.Expected: Waiting: 0\tActual: "+text);
			}
			ele = FindElement(driver, "//span[contains(@id,'Disclaimer:disclaimerGridBottomP')]//td[3]/div/img", "Waiting Disclamer img", action.BOOLEAN, 30);
			text = getAttribute(driver, ele, "Waiting Disclaimer img", "src");
			if(text.contains("waiting-new1.png")){
				appLog.info("Waiting Disclaimer img is verified.");
			} else {
				appLog.error("Waiting Disclaimer img is not verified.Expected: approved_new1.png\tActula: "+text);
				sa.assertTrue(false,"Waiting Disclaimer img is not verified.Expected: approved_new1.png\tActula: "+text);
			}
		} else {
			appLog.error("Navatar addon tab cannot be clicked, So cannot check the UI.");
			sa.assertTrue(false,"Navatar addon tab cannot be clicked, So cannot check the UI.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc005_PreConditionData(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		lp.CRMLogin(Org3CRMUser1EmailID, adminPassword);
		InstitutionPageBusinessLayer inst = new InstitutionPageBusinessLayer(driver);
		for(int i = 1; i < 5; i++)
			if(lp.clickOnTab(TabName.InstituitonsTab)){
				if(inst.createInstitution(environment,mode,ExcelUtils.readData("Institutions", excelLabel.Variable_Name, "M4I"+i, excelLabel.Institutions_Name),"Institution",null,null)){
					appLog.info("Institution '"+ExcelUtils.readData("Institutions", excelLabel.Variable_Name, "M4I"+i, excelLabel.Institutions_Name)+"' is created successfully.");
				} else {
					appLog.error(ExcelUtils.readData("Institutions", excelLabel.Variable_Name, "M4I"+i, excelLabel.Institutions_Name)+" institution is not created.");
					sa.assertTrue(false,ExcelUtils.readData("Institutions", excelLabel.Variable_Name, "M4I"+i, excelLabel.Institutions_Name)+" institution is not created.");
				}
			} else {
				appLog.error("Insitution Tab cannot be clicked, So cannot create institution.");
				sa.assertTrue(false,"Insitution Tab cannot be clicked, So cannot create institution.");
			}
		if(lp.clickOnTab(TabName.InstituitonsTab)){

			if(inst.createInstitution(environment, mode, M4LP1, "Limited Partner", InstitutionPageFieldLabelText.Parent_Institution.toString(), M4I3)){
				appLog.info(M4LP1+" LP Created successfully.");
			} else {
				appLog.error(M4LP1+" LP created successfully.");
				sa.assertTrue(false,M4LP1+" LP created successfully.");
			}
		} else {
			appLog.error("Insitution Tab cannot be clicked, So cannot create LP.");
			sa.assertTrue(false,"Insitution Tab cannot be clicked, So cannot create LP.");
		}
		if(lp.clickOnTab(TabName.InstituitonsTab)){
			//inst.createLimitedPartner(M4LP2, M4I4)
			if(inst.createInstitution(environment, mode, M4LP2, "Limited Partner", InstitutionPageFieldLabelText.Parent_Institution.toString(), M4I4)){
				appLog.info(M4LP2+" LP Created successfully.");
			} else {
				appLog.error(M4LP2+" LP created successfully.");
				sa.assertTrue(false,M4LP2+" LP created successfully.");
			}
		} else {
			appLog.error("Insitution Tab cannot be clicked, So cannot create LP.");
			sa.assertTrue(false,"Insitution Tab cannot be clicked, So cannot create LP.");
		}
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String emailId = cp.generateRandomEmailId();
		if(lp.clickOnTab(TabName.ContactTab)){
			if(cp.createContact(environment,mode,M4CFN1, M4CLN1, M4I1, emailId,null,null,CreationPage.ContactPage)){
				appLog.info(M4CFN1+" "+M4CLN1+" contact is created successfully.");
				ExcelUtils.writeData(emailId, "Contacts", excelLabel.Variable_Name, "M4Contact1", excelLabel.Contact_EmailId);
			} else {
				appLog.error(M4CFN1+" "+M4CLN1+" contact is not created.");
				sa.assertTrue(false,M4CFN1+" "+M4CLN1+" contact is not created.");
			}
		} else {
			appLog.error("Contact tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"Contact tab cannot be clicked, So cannot continue with the TC.");
		}
		
		emailId = cp.generateRandomEmailId();
		if(lp.clickOnTab(TabName.ContactTab)){
			if(cp.createContact(environment,mode,M4CFN2, M4CLN2, M4I2, emailId,null,null,CreationPage.ContactPage)){
				appLog.info(M4CFN2+" "+M4CLN2+" contact is created successfully.");
				ExcelUtils.writeData(emailId, "Contacts", excelLabel.Variable_Name, "M4Contact2", excelLabel.Contact_EmailId);
			} else {
				appLog.error(M4CFN2+" "+M4CLN2+" contact is not created.");
				sa.assertTrue(false,M4CFN2+" "+M4CLN2+" contact is not created.");
			}
		} else {
			appLog.error("Contact tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"Contact tab cannot be clicked, So cannot continue with the TC.");
		}
		
		emailId = cp.generateRandomEmailId();
		if(lp.clickOnTab(TabName.ContactTab)){
			if(cp.createContact(environment,mode,M4CFN3, M4CLN3, M4I3, emailId,null,null,CreationPage.ContactPage)){
				appLog.info(M4CFN3+" "+M4CLN3+" contact is created successfully.");
				ExcelUtils.writeData(emailId, "Contacts", excelLabel.Variable_Name, "M4Contact3", excelLabel.Contact_EmailId);
			} else {
				appLog.error(M4CFN3+" "+M4CLN3+" contact is not created.");
				sa.assertTrue(false,M4CFN3+" "+M4CLN3+" contact is not created.");
			}
		} else {
			appLog.error("Contact tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"Contact tab cannot be clicked, So cannot continue with the TC.");
		}
		
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.createFund(environment,mode,M4F1, M4FT1, M4FIC1,null,null)){
				appLog.info(M4F1+" fund created successfully");
			} else {
				appLog.error(M4F1+" fund cannot be created.");
				sa.assertTrue(false,M4F1+" fund cannot be created.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot continue with the TC.");
		}
		
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.createFund(environment,mode,M4F2, M4FT2, M4FIC2,null,null)){
				appLog.info(M4F2+" fund created successfully");
			} else {
				appLog.error(M4F2+" fund cannot be created.");
				sa.assertTrue(false,M4F2+" fund cannot be created.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot continue with the TC.");
		}
		
		FundRaisingPageBusinessLayer fdr = new FundRaisingPageBusinessLayer(driver);
		if(lp.clickOnTab(TabName.FundraisingsTab)){
			if(fdr.createFundRaising(environment,mode,M4FR1, M4F1, M4I1)){
				appLog.info(M4FR1+" fundraising created successfully.");
			} else {
				appLog.error(M4FR1+" fundraising not created.");
				sa.assertTrue(false,M4FR1+" fundraising not created.");
			}
		} else {
			appLog.error("fundraising tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"fundraising tab cannot be clicked, So cannot continue with the TC.");
		}
		
		if(lp.clickOnTab(TabName.FundraisingsTab)){
			if(fdr.createFundRaising(environment,mode,M4FR2, M4F2, M4I1)){
				appLog.info(M4FR2+" fundraising created successfully.");
			} else {
				appLog.error(M4FR2+" fundraising not created.");
				sa.assertTrue(false,M4FR2+" fundraising not created.");
			}
		} else {
			appLog.error("fundraising tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"fundraising tab cannot be clicked, So cannot continue with the TC.");
		}
		
		if(lp.clickOnTab(TabName.FundraisingsTab)){
			if(fdr.createFundRaising(environment,mode,M4FR3, M4F2, M4I2)){
				appLog.info(M4FR3+" fundraising created successfully.");
			} else {
				appLog.error(M4FR3+" fundraising not created.");
				sa.assertTrue(false,M4FR3+" fundraising not created.");
			}
		} else {
			appLog.error("fundraising tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"fundraising tab cannot be clicked, So cannot continue with the TC.");
		}
		
		PartnershipPageBusinessLayer prt= new PartnershipPageBusinessLayer(driver);
		if(lp.clickOnTab(TabName.PartnershipsTab)){
			if(prt.createPartnership(environment,mode,M4P1, M4F2)){
				appLog.info(M4P1+" PartnerShip created successfully.");
			} else {
				appLog.error(M4P1+" partnership not created.");
				sa.assertTrue(false,M4P1+" partnership not created.");
			}
		} else {
			appLog.error("PartnerShip tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"PartnerShip tab cannot be clicked, So cannot continue with the TC.");
		}
		
		CommitmentPageBusinessLayer cmt = new CommitmentPageBusinessLayer(driver);
		if(lp.clickOnTab(TabName.CommitmentsTab)){
			if(cmt.createCommitment(environment,mode,M4LP1, M4P1, "M4CMT1", null)){
				appLog.info("Commitment created successfully.");
			} else {
				appLog.error("Commitment not created.");
				sa.assertTrue(false,"Commitment not created.");
			}
		} else {
			appLog.error("Commitments tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"Commitments tab cannot be clicked, So cannot continue with the TC.");
		}
		
		if(lp.clickOnTab(TabName.CommitmentsTab)){
			if(cmt.createCommitment(environment,mode,M4LP2, M4P1, "M4CMT2", null)){
				appLog.info("Commitment created successfully.");
			} else {
				appLog.error("Commitment not created.");
				sa.assertTrue(false,"Commitment not created.");
			}
		} else {
			appLog.error("Commitments tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"Commitments tab cannot be clicked, So cannot continue with the TC.");
		}
		
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		if(lp.clickOnTab(TabName.NIMTab)){
			switchToFrame(driver, 60, nim.getNIMTabParentFrame_Lightning());
			ThreadSleep(5000);
			switchToFrame(driver, 30, nim.getFrame(environment,mode,PageName.NavatarInvestorManager, 30));
			if(nim.createFolderTemplate("FolderTemp", folderTemplateName, "abc", 60)){	
				appLog.info("Folder template created successfully.");
			} else {
				appLog.error("Folder structure is not created.");
				sa.assertTrue(false,"Folder structure is not created.");
			}
			switchToDefaultContent(driver);
		} else {
			appLog.error("NIM tab cannot be clicked, So cannot create folder template.");
			sa.assertTrue(false,"NIM tab cannot be clicked, So cannot create folder template.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc006_VerifyNIAddOnAfterCreatingFund(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		if(lp.clickOnTab(TabName.NavatarInvestorAddOns)){
			if(switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60))){
				System.err.println("Successfully switched to frame.");
				switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
			}
			String title = getTitle(driver);
			if(title.contains("Navatar Investor Add-ons")){
				appLog.info("title is verified.");
			} else {
				appLog.error("title is not verified.");
				sa.assertTrue(false,"title is not verified.");
			}
			WebElement ele = FindElement(driver, "//strong[text()='Select Fund:']/following-sibling::select", "Select Fund and drop down", action.BOOLEAN, 30);
			if(ele!=null){
				appLog.info("Select fund label and drop down is verified.");
			} else {
				appLog.error("Select fund label and drop down is not present on the UI.");
				sa.assertTrue(false,"Select fund label and drop down is not present on the UI.");
			}
			List<WebElement> allOptions=allOptionsInDropDrop(driver, ele, "Fund select drop down.");
			if(allOptions.isEmpty()){
				appLog.info("No Option is selected in the drop down and is verified.");
			} else {
				appLog.error("Option is selected in the drop down and is not verified.Expected: Blank\tActual: "+getSelectedOptionOfDropDown(driver, ele, "Fund select drop down", "text"));
				sa.assertTrue(false,"Option is selected in the drop down and is not verified.Expected: Blank\tActual: "+getSelectedOptionOfDropDown(driver, ele, "Fund select drop down", "text"));
			}
			ele = FindElement(driver, "//strong[text()='Status:']/following-sibling::b", "Status Label", action.BOOLEAN, 30);
			String text = trim(getText(driver, ele, "Status label", action.BOOLEAN));
			if(text.contains("Inactive")){
				appLog.info("Status is successfully verified.");
			} else {
				appLog.error("Status is not verified.Expected: Inactive\tActual: "+text);
				sa.assertTrue(false,"Status is not verified.Expected: Inactive\tActual: "+text);
			}
			text = getAttribute(driver, ele, "Color of status", "style");
			if(text.contains("background: red; color: white;")){
				appLog.info("Color of the status is verified.");
			} else {
				appLog.error("Color of the status is not verified.Expected: red; color: white;\tActual: "+text);
				sa.assertTrue(false,"Color of the status is not verified.Expected: red; color: white;\tActual: "+text);
			}
			ele = FindElement(driver, "//strong[text()='Status:']/../preceding-sibling::div//a", "New Disclaimer Button", action.BOOLEAN, 30);
			text = getAttribute(driver, ele, "New Disclaimer Button", "style");
			if(text.contains("opacity: 0.5;")){
				appLog.info("New disclaimer Button is in disabled mode.");
			} else {
				appLog.error("New disclaimer Button is not in disabled mode.");
				sa.assertTrue(false,"New disclaimer Button is not in disabled mode.");
			}
			List<WebElement> eles = FindElements(driver, "//span[@id='grid_DefaultFoldersView1-headers']/span[contains(@id,'grid_DefaultFoldersView1-header-')]", "Headers of grid");
			for(int i = 0; i < eles.size(); i++){
				text = getAttribute(driver, eles.get(i), "Hearder", "title");
				if(text.equalsIgnoreCase("Action")||text.equalsIgnoreCase("Name")||text.equalsIgnoreCase("Statistics")||text.equalsIgnoreCase("Last Activated On")||text.equalsIgnoreCase("Created Date")){
					appLog.info(text+" header si verified.");
				} else {
					appLog.error("Headers are not verified.");
					sa.assertTrue(false,"Headers are not verified.");
				}
			}
			ele = FindElement(driver, "//span[@id='grid_DefaultFoldersView1-cell-1-0']/span", "No data to display msg", action.BOOLEAN, 30);
			text = getAttribute(driver, ele, "No data to display msg", "title");
			if(text.contains(NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage)){
				appLog.info(NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage+" error msg is verified.");
			} else {
				appLog.error(NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage+" error msg is verified.expected: "+NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage+"\tActual: "+text);
				sa.assertTrue(false,NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage+" error msg is verified.expected: "+NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage+"\tActual: "+text);
			}
			ele = FindElement(driver, "//span[@id='disclaimerCountSpan']", "Disclaimer Count", action.BOOLEAN, 30);
			text = getText(driver, ele, "Disclaimer Count", action.BOOLEAN);
			List<Integer> count=getIntegerFromString(text);
			if(!count.isEmpty()){
				if(count.get(0)==0)
					appLog.info("count is verified.");
				else {
					appLog.error("Count is not verified. expected: 0\tActual: "+count);
					sa.assertTrue(false,"Count is not verified. expected: 0\tActual: "+count);
				}
			} else {
				appLog.error("Disclaimer count is not showing.");
				sa.assertTrue(false,"Disclaimer count is not showing.");
			}
			ele = FindElement(driver, "//span[contains(@id,'Disclaimer:disclaimerGridBottomP')]//td[1]/div", "Active Disclaimer Stats", action.BOOLEAN, 30);
			text = trim(getText(driver, ele, "Active Disclaimer Stats", action.BOOLEAN));
			if(text.contains("Active Disclaimer Statistics:")&&text.contains("None")){
				appLog.info("Active disclaimer stats is verified.");
			} else {
				appLog.error("Active disclaimer stats is not verified.Expected: Active Disclaimer Statistics: None\tActual: "+text);
				sa.assertTrue(false,"Active disclaimer stats is not verified.Expected: Active Disclaimer Statistics: None\tActual: "+text);
			}
			ele = FindElement(driver, "//span[contains(@id,'Disclaimer:disclaimerGridBottomP')]//td[2]/div", "Accepted Disclamer", action.BOOLEAN, 30);
			text = trim(getText(driver, ele, "Accpted Siclaimer", action.BOOLEAN));
			if(text.contains("Accepted")&&text.contains("0")){
				appLog.info("Accepted label and count is verified");
			} else {
				appLog.error("Accepted label and count is not verified.Expected: Accepted: 0\tActual: "+text);
				sa.assertTrue(false,"Accepted label and count is not verified.Expected: Accepted: 0\tActual: "+text);
			}
			ele = FindElement(driver, "//span[contains(@id,'Disclaimer:disclaimerGridBottomP')]//td[2]/div/img", "Accepted Disclamer img", action.BOOLEAN, 30);
			text = getAttribute(driver, ele, "Accepted Disclaimer img", "src");
			if(text.contains("approved_new1.png")){
				appLog.info("Accepted Disclaimer img is verified.");
			} else {
				appLog.error("Accepted Disclaimer img is not verified.Expected: approved_new1.png\tActula: "+text);
				sa.assertTrue(false,"Accepted Disclaimer img is not verified.Expected: approved_new1.png\tActula: "+text);
			}
			ele = FindElement(driver, "//span[contains(@id,'Disclaimer:disclaimerGridBottomP')]//td[3]/div", "Waiting Disclamer", action.BOOLEAN, 30);
			text = trim(getText(driver, ele, "Waiting Diclaimer", action.BOOLEAN));
			if(text.contains("Waiting")&&text.contains("0")){
				appLog.info("Waiting label and count is verified");
			} else {
				appLog.error("Waiting label and count is not verified.Expected: Waiting: 0\tActual: "+text);
				sa.assertTrue(false,"Waiting label and count is not verified.Expected: Waiting: 0\tActual: "+text);
			}
			ele = FindElement(driver, "//span[contains(@id,'Disclaimer:disclaimerGridBottomP')]//td[3]/div/img", "Waiting Disclamer img", action.BOOLEAN, 30);
			text = getAttribute(driver, ele, "Waiting Disclaimer img", "src");
			if(text.contains("waiting-new1.png")){
				appLog.info("Waiting Disclaimer img is verified.");
			} else {
				appLog.error("Waiting Disclaimer img is not verified.Expected: approved_new1.png\tActula: "+text);
				sa.assertTrue(false,"Waiting Disclaimer img is not verified.Expected: approved_new1.png\tActula: "+text);
			}
		} else {
			appLog.error("Navatar addon tab cannot be clicked, So cannot check the UI.");
			sa.assertTrue(false,"Navatar addon tab cannot be clicked, So cannot check the UI.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String[] stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath).split(",");
		String[] cmnPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath).split(",");
		String[] shdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath).split(",");
		String[] intPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath).split(",");
		String StdFromFolder = "UploadFiles\\Module4\\Standard";
		String CommonFromFolder = "UploadFiles\\Module4\\Common";
		String InternalFromFolder = "UploadFiles\\Module4\\Internal";
		String SharedFromFolder = "UploadFiles\\Module4\\Shared";
		lp.CRMLogin(Org3CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(environment,mode,M4F1)){
				
				String Size=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M4F1", excelLabel.Fund_Size);
				String vintageyear=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M4F1", excelLabel.Fund_VintageYear);
				String contact=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M4F1", excelLabel.Fund_Contact);
				String phone=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M4F1", excelLabel.Fund_Phone);
				String email=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M4F1", excelLabel.Fund_Email);
				String description=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M4F1", excelLabel.Fund_Description);
				String[] step1Of3Data= {Size,vintageyear,contact,phone,email,description};
				
				//String step1Of3Data[] = {"2254","2015","acb","987654321","abc@bcd.com","Sample description"};
				if(fp.buildWorkspace(step1Of3Data, WorkSpaceAction.IMPORTFOLDERTEMPLATE, folderTemplateName, null, M4I1, Workspace.FundraisingWorkspace, 60)){
					appLog.info("Workspace is built successfully.");
					if(fp.inviteContact(environment, mode, M4I1, M4C1Email, null, FolderType.Standard, "Upload", "Yes", "Yes", null, Workspace.FundraisingWorkspace, M4C1Email)){
						appLog.info("Successfully provided access to contact '"+M4CFN1+" "+M4CLN1+"'.");
					} else {
						appLog.error("Not able to provide access to contact '"+M4CFN1+" "+M4CLN1+"'.");
						sa.assertTrue(false,"Not able to provide access to contact '"+M4CFN1+" "+M4CLN1+"'.");
					}
					if(fp.inviteContact(environment, mode, M4I1, M4C2Email, null, FolderType.Standard, "Upload", "Yes", "Yes", null, Workspace.FundraisingWorkspace, M4C2Email)){
						appLog.info("Successfully provided access to contact '"+M4CFN2+" "+M4CLN2+"'.");
					} else {
						appLog.error("Not able to provide access to contact '"+M4CFN2+" "+M4CLN2+"'.");
						sa.assertTrue(false,"Not able to provide access to contact '"+M4CFN2+" "+M4CLN2+"'.");
					}
					if(fp.inviteContact(environment, mode, null, M4C1Email, shdPath[0], FolderType.Shared, "Download", "Yes", "No", null, Workspace.FundraisingWorkspace, M4C1Email)){
						appLog.info("Successfully provided access to contact '"+M4CFN1+" "+M4CLN1+"'.");
					} else {
						appLog.error("Not able to provide access to contact '"+M4CFN1+" "+M4CLN1+"'.");
						sa.assertTrue(false,"Not able to provide access to contact '"+M4CFN1+" "+M4CLN1+"'.");
					}
					if(fp.inviteContact(environment, mode, null, M4C2Email, shdPath[0], FolderType.Shared, "Download", "Yes", "No", null, Workspace.FundraisingWorkspace, M4C2Email)){
						appLog.info("Successfully provided access to contact '"+M4CFN2+" "+M4CLN2+"'.");
					} else {
						appLog.error("Not able to provide access to contact '"+M4CFN2+" "+M4CLN2+"'.");
						sa.assertTrue(false,"Not able to provide access to contact '"+M4CFN2+" "+M4CLN2+"'.");
					}
				System.err.println(M4I1);
					if(fp.uploadFile(stdPath[0], M4I1, StdFromFolder, null, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
						appLog.info("Successfully uploaded files in '"+stdPath[0]+"'");
					} else {
						appLog.error("Not able to upload files in '"+stdPath[0]+"'");
						sa.assertTrue(false,"Not able to upload files in '"+stdPath[0]+"'");
					}
					StdFromFolder = "UploadFiles\\Module4\\StandardSub";
					if(fp.uploadFile(stdPath[1], M4I1, StdFromFolder, null, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
						appLog.info("Successfully uploaded files in '"+stdPath[1]+"'");
					} else {
						appLog.error("Not able to upload files in '"+stdPath[1]+"'");
						sa.assertTrue(false,"Not able to upload files in '"+stdPath[1]+"'");
					}
					if(fp.uploadFile(cmnPath[0], null, CommonFromFolder, null, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
						appLog.info("Successfully uploaded files in '"+cmnPath[0]+"'");
					} else {
						appLog.error("Not able to upload files in '"+cmnPath[0]+"'");
						sa.assertTrue(false,"Not able to upload files in '"+cmnPath[0]+"'");
					}
					CommonFromFolder = CommonFromFolder+"Sub";
					if(fp.uploadFile(cmnPath[1], null, CommonFromFolder, null, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
						appLog.info("Successfully uploaded files in '"+cmnPath[1]+"'");
					} else {
						appLog.error("Not able to upload files in '"+cmnPath[1]+"'");
						sa.assertTrue(false,"Not able to upload files in '"+cmnPath[1]+"'");
					}
					if(fp.uploadFile(shdPath[0], null, SharedFromFolder, null, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
						appLog.info("Successfully uploaded files in '"+shdPath[0]+"'");
					} else {
						appLog.error("Not able to upload files in '"+shdPath[0]+"'");
						sa.assertTrue(false,"Not able to upload files in '"+shdPath[0]+"'");
					}
					SharedFromFolder = SharedFromFolder+"Sub";
					if(fp.uploadFile(shdPath[1], null, SharedFromFolder, null, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
						appLog.info("Successfully uploaded files in '"+shdPath[1]+"'");
					} else {
						appLog.error("Not able to upload files in '"+shdPath[1]+"'");
						sa.assertTrue(false,"Not able to upload files in '"+shdPath[1]+"'");
					}
					if(fp.uploadFile(intPath[0], null, InternalFromFolder, null, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
						appLog.info("Successfully uploaded files in '"+intPath[0]+"'");
					} else {
						appLog.error("Not able to upload files in '"+intPath[0]+"'");
						sa.assertTrue(false,"Not able to upload files in '"+intPath[0]+"'");
					}
					InternalFromFolder = InternalFromFolder+"Sub";
					if(fp.uploadFile(intPath[1], null, InternalFromFolder, null, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
						appLog.info("Successfully uploaded files in '"+intPath[1]+"'");
					} else {
						appLog.error("Not able to upload files in '"+intPath[1]+"'");
						sa.assertTrue(false,"Not able to upload files in '"+intPath[1]+"'");
					}
				} else {
					appLog.error("Fundraising workspace is not built successfully.");
					sa.assertTrue(false,"Fundraising workspace is not built successfully.");
				}
			} else {
				appLog.error(M4F1+" fund is not availabe in the list.");
				sa.assertTrue(false,M4F1+" fund is not availabe in the list.");
			}
		} else {
			appLog.error("Funds Tab cannot be clicked, so cannot build the workspace.");
			sa.assertTrue(false,"Funds Tab cannot be clicked, so cannot build the workspace.");
		}
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc007_BuildWorkspaceForF1AndVerifyNIAddOnCheckImpact(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		if(lp.clickOnTab(TabName.NavatarInvestorAddOns)){

			 switchToFrame(driver,20,lp.getNavatarInvestorAddOnParentFrame(20));
			String title = getTitle(driver);
			if(title.contains("Navatar Investor Add-ons")){
				appLog.info("title is verified.");
			} else {
				appLog.error("title is not verified.");
				sa.assertTrue(false,"title is not verified.");
			}
			if(switchToFrame(driver, 20, niam.getNavatarInvestorAddOnFrame(30))){
				System.err.println("Successfully switched to frame.");
				  switchToFrame(driver,20,niam.getPEDisclaimersSetupFrame(20));
			}
			WebElement ele = FindElement(driver, "//strong[text()='Select Fund:']/following-sibling::select", "Select Fund and drop down", action.BOOLEAN, 30);
			if(ele!=null){
				appLog.info("Select fund label and drop down is verified.");
			} else {
				appLog.error("Select fund label and drop down is not present on the UI.");
				sa.assertTrue(false,"Select fund label and drop down is not present on the UI.");
			}
			String selectedValue = getSelectedOptionOfDropDown(driver, ele, "Fund Drop Down", "text");
			if(selectedValue!=null && selectedValue.equalsIgnoreCase(M4F1)){
				appLog.info(M4F1+" Option is selected in the drop down and is verified.");
			} else {
				appLog.error(M4F1+" Option is not selected in the drop down and is not verified.Expected: "+M4F1+"\tActual: "+selectedValue);
				sa.assertTrue(false,M4F1+" Option is not selected in the drop down and is not verified.Expected: "+M4F1+"\tActual: "+selectedValue);
			}
			ele = FindElement(driver, "//strong[text()='Status:']/following-sibling::b", "Status Label", action.BOOLEAN, 30);
			String text = trim(getText(driver, ele, "Status label", action.BOOLEAN));
			if(text.contains("Inactive")){
				appLog.info("Status is successfully verified.");
			} else {
				appLog.error("Status is not verified.Expected: Inactive\tActual: "+text);
				sa.assertTrue(false,"Status is not verified.Expected: Inactive\tActual: "+text);
			}
			text = getAttribute(driver, ele, "Color of status", "style");
			if(text.contains("background: red; color: white;")){
				appLog.info("Color of the status is verified.");
			} else {
				appLog.error("Color of the status is not verified.Expected: red; color: white;\tActual: "+text);
				sa.assertTrue(false,"Color of the status is not verified.Expected: red; color: white;\tActual: "+text);
			}
			ele = FindElement(driver, "//strong[text()='Status:']/../preceding-sibling::div//a", "New Disclaimer Button", action.BOOLEAN, 30);
			text = getAttribute(driver, ele, "New Disclaimer Button", "style");
			if(text.contains("opacity: 0.5;")){
				appLog.error("New disclaimer Button is in disabled mode.");
				sa.assertTrue(false,"New disclaimer Button is in disabled mode.");
			} else {
				appLog.info("New disclaimer Button is Enable.");
			}
			List<WebElement> eles = FindElements(driver, "//span[@id='grid_DefaultFoldersView1-headers']/span[contains(@id,'grid_DefaultFoldersView1-header-')]", "Headers of grid");
			for(int i = 0; i < eles.size(); i++){
				text = getAttribute(driver, eles.get(i), "Hearder", "title");
				if(text.equalsIgnoreCase("Action")||text.equalsIgnoreCase("Name")||text.equalsIgnoreCase("Statistics")||text.equalsIgnoreCase("Last Activated On")||text.equalsIgnoreCase("Created Date")){
					appLog.info(text+" header si verified.");
				} else {
					appLog.error("Headers are not verified.");
					sa.assertTrue(false,"Headers are not verified.");
				}
			}
			ele = FindElement(driver, "//span[@id='grid_DefaultFoldersView1-cell-1-0']/span", "No data to display msg", action.BOOLEAN, 30);
			text = getAttribute(driver, ele, "No data to display msg", "title");
			if(text.contains(NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage)){
				appLog.info(NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage+" error msg is verified.");
			} else {
				appLog.error(NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage+" error msg is verified.expected: "+NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage+"\tActual: "+text);
				sa.assertTrue(false,NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage+" error msg is verified.expected: "+NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage+"\tActual: "+text);
			}
			ele = FindElement(driver, "//span[@id='disclaimerCountSpan']", "Disclaimer Count", action.BOOLEAN, 30);
			text = getText(driver, ele, "Disclaimer Count", action.BOOLEAN);
			List<Integer> count=getIntegerFromString(text);
			if(!count.isEmpty()){
				if(count.get(0)==0)
					appLog.info("count is verified.");
				else {
					appLog.error("Count is not verified. expected: 0\tActual: "+count);
					sa.assertTrue(false,"Count is not verified. expected: 0\tActual: "+count);
				}
			} else {
				appLog.error("Disclaimer count is not showing.");
				sa.assertTrue(false,"Disclaimer count is not showing.");
			}
			ele = FindElement(driver, "//span[contains(@id,'Disclaimer:disclaimerGridBottomP')]//td[1]/div", "Active Disclaimer Stats", action.BOOLEAN, 30);
			text = trim(getText(driver, ele, "Active Disclaimer Stats", action.BOOLEAN));
			if(text.contains("Active Disclaimer Statistics:")&&text.contains("None")){
				appLog.info("Active disclaimer stats is verified.");
			} else {
				appLog.error("Active disclaimer stats is not verified.Expected: Active Disclaimer Statistics: None\tActual: "+text);
				sa.assertTrue(false,"Active disclaimer stats is not verified.Expected: Active Disclaimer Statistics: None\tActual: "+text);
			}
			ele = FindElement(driver, "//span[contains(@id,'Disclaimer:disclaimerGridBottomP')]//td[2]/div", "Accepted Disclamer", action.BOOLEAN, 30);
			text = trim(getText(driver, ele, "Accpted Siclaimer", action.BOOLEAN));
			if(text.contains("Accepted")&&text.contains("0")){
				appLog.info("Accepted label and count is verified");
			} else {
				appLog.error("Accepted label and count is not verified.Expected: Accepted: 0\tActual: "+text);
				sa.assertTrue(false,"Accepted label and count is not verified.Expected: Accepted: 0\tActual: "+text);
			}
			ele = FindElement(driver, "//span[contains(@id,'Disclaimer:disclaimerGridBottomP')]//td[2]/div/img", "Accepted Disclamer img", action.BOOLEAN, 30);
			text = getAttribute(driver, ele, "Accepted Disclaimer img", "src");
			if(text.contains("approved_new1.png")){
				appLog.info("Accepted Disclaimer img is verified.");
			} else {
				appLog.error("Accepted Disclaimer img is not verified.Expected: approved_new1.png\tActula: "+text);
				sa.assertTrue(false,"Accepted Disclaimer img is not verified.Expected: approved_new1.png\tActula: "+text);
			}
			ele = FindElement(driver, "//span[contains(@id,'Disclaimer:disclaimerGridBottomP')]//td[3]/div", "Waiting Disclamer", action.BOOLEAN, 30);
			text = trim(getText(driver, ele, "Waiting Diclaimer", action.BOOLEAN));
			if(text.contains("Waiting")&&text.contains("0")){
				appLog.info("Waiting label and count is verified");
			} else {
				appLog.error("Waiting label and count is not verified.Expected: Waiting: 0\tActual: "+text);
				sa.assertTrue(false,"Waiting label and count is not verified.Expected: Waiting: 0\tActual: "+text);
			}
			ele = FindElement(driver, "//span[contains(@id,'Disclaimer:disclaimerGridBottomP')]//td[3]/div/img", "Waiting Disclamer img", action.BOOLEAN, 30);
			text = getAttribute(driver, ele, "Waiting Disclaimer img", "src");
			if(text.contains("waiting-new1.png")){
				appLog.info("Waiting Disclaimer img is verified.");
			} else {
				appLog.error("Waiting Disclaimer img is not verified.Expected: approved_new1.png\tActula: "+text);
				sa.assertTrue(false,"Waiting Disclaimer img is not verified.Expected: approved_new1.png\tActula: "+text);
			}
		} else {
			appLog.error("Navatar addon tab cannot be clicked, So cannot check the UI.");
			sa.assertTrue(false,"Navatar addon tab cannot be clicked, So cannot check the UI.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc008_VerifyFunctionalityOfNewDisclaimerButtonAndErrorMsg(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if(lp.clickOnTab(TabName.NavatarInvestorAddOns)){
			 switchToFrame(driver,20,lp.getNavatarInvestorAddOnParentFrame(20));
			 if(switchToFrame(driver, 20, niam.getNavatarInvestorAddOnFrame(30))){
					System.err.println("Successfully switched to frame.");
					  switchToFrame(driver,20,niam.getPEDisclaimersSetupFrame(20));
				}
			if(click(driver, niam.getNewDisclaimerButton(60), "New discliamer Button", action.BOOLEAN)){
				String text = getText(driver, niam.getHeaderName(60), "Header name", action.BOOLEAN);
				if(text.equalsIgnoreCase("Add New Disclaimer")){
					appLog.info("Header is verified.");
				} else {
					appLog.error("Pop Up header is not verified.Expected: Add New Disclaimer\tActual: "+text);
					sa.assertTrue(false,"Pop Up header is not verified.Expected: Add New Disclaimer\tActual: "+text);
				}
				if(niam.getNewDisclaimerNameTextBox(60)!=null){
					appLog.info("Name label and text box s verifed.");
				} else {
					appLog.error("Name text box is not available.");
					sa.assertTrue(false,"Name text box is not available.");
				}
				if(niam.getDisclaimerDescriptionTextArea(60)!=null){
					appLog.info("New disclaimer Descripiton text box is verified.");
				} else {
					appLog.error("New disclaimer description box is not available.");
					sa.assertTrue(false,"New disclaimer description box is not available.");
				}
				switchToDefaultContent(driver);
				if(switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60))){
					System.err.println("Successfully switched to frame.");
					switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
				}
				if(click(driver, niam.getNewDislaimerPopCrossIcon(60), "Cross Icon", action.BOOLEAN)){
					ThreadSleep(1500);
					if(niam.getNewDisclaimerPopUpHeader(5)==null){
						appLog.info("Cross icon is verified.");
					} else {
						appLog.error("Pop us not closed after clicking on the croos icon.");
						sa.assertTrue(false,"Pop us not closed after clicking on the croos icon.");
					}
				} else {
					appLog.error("Cross icon cannot be clicked, So cannot check the cross icon functionality.");
					sa.assertTrue(false,"Cross icon cannot be clicked, So cannot check the cross icon functionality.");
				}
				if(click(driver, niam.getNewDisclaimerButton(60), "New discliamer Button", action.BOOLEAN)){
					if(click(driver, niam.getNewDisclaimerCancelButton(60), "Cancel Button", action.BOOLEAN)){
						ThreadSleep(1500);
						if(niam.getNewDisclaimerPopUpHeader(6)==null){
							appLog.info("Cancel Button is verified.");
						} else {
							appLog.error("Pop us not closed after clicking on the cancel button.");
							sa.assertTrue(false,"Pop us not closed after clicking on the Cancel button.");
						}
					} else {
						appLog.error("Cross icon cannot be clicked, So cannot check the cross icon functionality.");
						sa.assertTrue(false,"Cross icon cannot be clicked, So cannot check the cross icon functionality.");
					}
				} else {
					appLog.error("New disclaimer button cannot be clikced so cannot check the new disclaimer pop up UI.");
					sa.assertTrue(false,"New disclaimer button cannot be clikced so cannot check the new disclaimer pop up UI.");
				}
				if(click(driver, niam.getNewDisclaimerButton(60), "New discliamer Button", action.BOOLEAN)){
					if(click(driver, niam.getNewDiscalimerPopUpSaveButton(60), "Save Button", action.BOOLEAN)){
						text = trim(getText(driver, niam.getNewDisclaimerPopUpNameTextBoxErrorMsg(60), "Error msg", action.BOOLEAN));
						if(text.equalsIgnoreCase(NavatarInvestorAddOnsErrorMessage.PleaseEnterValueErrorMsg.trim())){
							appLog.info("Error message is verified.");
						} else {
							appLog.error("Error message text is not verified for disclaimer Name text box. Expected: "+NavatarInvestorAddOnsErrorMessage.PleaseEnterValueErrorMsg.trim()+"\tActual: "+text);
							sa.assertTrue(false,"Error message text is not verified for disclaimer Name text box. Expected: "+NavatarInvestorAddOnsErrorMessage.PleaseEnterValueErrorMsg.trim()+"\tActual: "+text);
						}
						text = trim(getText(driver, niam.getNewDisclaimerPopUpDescriptionBoxErrorMsg(60), "Error msg", action.BOOLEAN));
						if(text.equalsIgnoreCase(NavatarInvestorAddOnsErrorMessage.PleaseEnterValueErrorMsg.trim())){
							appLog.info("Error message is verified.");
						} else {
							appLog.error("Error message text is not verified for disclaimer description text box. Expected: "+NavatarInvestorAddOnsErrorMessage.PleaseEnterValueErrorMsg.trim()+"\tActual: "+text);
							sa.assertTrue(false,"Error message text is not verified for disclaimer description text box. Expected: "+NavatarInvestorAddOnsErrorMessage.PleaseEnterValueErrorMsg.trim()+"\tActual: "+text);
						}
					} else {
						appLog.error("Cross icon cannot be clicked, So cannot check the cross icon functionality.");
						sa.assertTrue(false,"Cross icon cannot be clicked, So cannot check the cross icon functionality.");
					}
					boolean flag = false;
					if(sendKeys(driver, niam.getNewDisclaimerNameTextBox(30), "xyz", "New disclaimer text box", action.BOOLEAN)){
						if(sendKeys(driver, niam.getDisclaimerDescriptionTextArea(10), M4DISDEC1, "Description text box", action.BOOLEAN)){
							switchToDefaultContent(driver);
							if(switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60))){
								System.err.println("Successfully switched to frame.");
								switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
							}
							if(click(driver, niam.getNewDisclaimerCancelButton(60), "Cancel Button", action.BOOLEAN)){
								ThreadSleep(1500);
								if(niam.getNewDisclaimerPopUpHeader(1)==null){
									appLog.info("Cancel Button is verified.");
									if(click(driver, niam.getNewDisclaimerButton(60), "New discliamer Button", action.BOOLEAN)){
										flag = true;
										String value = getValueFromElementUsingJavaScript(driver, niam.getNewDisclaimerNameTextBox(30), "Disclaimer name text box.");
										if(value!=null && !value.isEmpty()){
											appLog.error("Value in disclaimer name text box is present even after closing and reopening the new disclaimer pop up.");
											sa.assertTrue(false,"Value in disclaimer name text box is present even after closing and reopening the new disclaimer pop up.");
										} else {
											appLog.info("Value is successfully cleared from disclaimer name text box."); 
										}
									} else {
										appLog.error("Not able to click on new disclaimer button, So cannot check whether info is still filled or not.");
										sa.assertTrue(false,"Not able to click on new disclaimer button, So cannot check whether info is still filled or not.");
									}
								} else {
									appLog.error("Pop us not closed after clicking on the cancel button.");
									sa.assertTrue(false,"Pop us not closed after clicking on the Cancel button.");
								}
							} else {
								appLog.error("Cross icon cannot be clicked, So cannot check the cancel button functionality.");
								sa.assertTrue(false,"Cross icon cannot be clicked, So cannot check the cancel button functionality.");
							}
						} else {
							appLog.error("cannot pass value to new discalimer description box.");
							sa.assertTrue(false,"cannot pass value to new discalimer description box.");
						}
					} else {
						appLog.info("Cannot pass value to new disclaimer name text box, So cannot check cancel button functionality.");
						sa.assertTrue(false,"Cannot pass value to new disclaimer name text box, So cannot check cancel button functionality.");
					}
					if(!flag){
						if(click(driver, niam.getNewDisclaimerButton(60), "New discliamer Button", action.BOOLEAN));
					}
					if(sendKeys(driver, niam.getNewDisclaimerNameTextBox(60), M4DIS1, "DisclaimerName text Box", action.BOOLEAN)){
						if(sendKeys(driver, niam.getDisclaimerDescriptionTextArea(10), M4DISDEC1, "Description text box", action.BOOLEAN)){
							switchToDefaultContent(driver);
							if(switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60))){
								System.err.println("Successfully switched to frame.");
								switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
							}
							if(click(driver, niam.getNewDiscalimerPopUpSaveButton(60), "Save Button", action.BOOLEAN)){
								appLog.info("Disclaimer has been saved successfully.");
								WebElement ele = FindElement(driver, "//a[@title='"+M4DIS1+"']/../preceding-sibling::span[1]/a[@title='Activate']/../following-sibling::span[2]/a[@title='View']/../following-sibling::span[1]/div[@title='']/../following-sibling::span[1]/div[@title='"+getSystemDate("MM/dd/yyyy")+"']", "Disclaimer grid", action.BOOLEAN, 60);
								if(ele!=null){
									appLog.info("UI is verified.");
								} else {
									appLog.error("Some information are missing from the grid.");
									sa.assertTrue(false,"Some information are missing from the grid.");
								}
								text = getText(driver, niam.getDisclaimerrecordCount(60), "Disclaimer Count", action.BOOLEAN);
								List<Integer> count=getIntegerFromString(text);
								if(!count.isEmpty()){
									if(count.get(0)>=1)
										appLog.info("count is verified.");
									else {
										appLog.error("Count is not verified. expected: 1\tActual: "+count);
										sa.assertTrue(false,"Count is not verified. expected: 1\tActual: "+count);
									}
								} else {
									appLog.error("Disclaimer count is not showing.");
									sa.assertTrue(false,"Disclaimer count is not showing.");
								}
								ele = FindElement(driver, "//span[contains(@id,'Disclaimer:disclaimerGridBottomP')]//td[1]/div", "Active Disclaimer Stats", action.BOOLEAN, 30);
								text = trim(getText(driver, ele, "Active Disclaimer Stats", action.BOOLEAN));
								if(text.contains("Active Disclaimer Statistics:")&&text.contains("None")){
									appLog.info("Active disclaimer stats is verified.");
								} else {
									appLog.error("Active disclaimer stats is not verified.Expected: Active Disclaimer Statistics: None\tActual: "+text);
									sa.assertTrue(false,"Active disclaimer stats is not verified.Expected: Active Disclaimer Statistics: None\tActual: "+text);
								}
								ele = FindElement(driver, "//span[contains(@id,'Disclaimer:disclaimerGridBottomP')]//td[2]/div", "Accepted Disclamer", action.BOOLEAN, 30);
								text = trim(getText(driver, ele, "Accpted Siclaimer", action.BOOLEAN));
								if(text.contains("Accepted")&&text.contains("0")){
									appLog.info("Accepted label and count is verified");
								} else {
									appLog.error("Accepted label and count is not verified.Expected: Accepted: 0\tActual: "+text);
									sa.assertTrue(false,"Accepted label and count is not verified.Expected: Accepted: 0\tActual: "+text);
								}
								ele = FindElement(driver, "//span[contains(@id,'Disclaimer:disclaimerGridBottomP')]//td[2]/div", "Accepted Disclamer", action.BOOLEAN, 30);
								text = trim(getText(driver, ele, "Accpted Siclaimer", action.BOOLEAN));
								if(text.contains("Accepted")&&text.contains("0")){
									appLog.info("Accepted label and count is verified");
								} else {
									appLog.error("Accepted label and count is not verified.Expected: Accepted: 0\tActual: "+text);
									sa.assertTrue(false,"Accepted label and count is not verified.Expected: Accepted: 0\tActual: "+text);
								}
								ele = FindElement(driver, "//span[contains(@id,'Disclaimer:disclaimerGridBottomP')]//td[3]/div", "Waiting Disclamer", action.BOOLEAN, 30);
								text = trim(getText(driver, ele, "Waiting Diclaimer", action.BOOLEAN));
								if(text.contains("Waiting")&&text.contains("0")){
									appLog.info("Waiting label and count is verified");
								} else {
									appLog.error("Waiting label and count is not verified.Expected: Waiting: 0\tActual: "+text);
									sa.assertTrue(false,"Waiting label and count is not verified.Expected: Waiting: 0\tActual: "+text);
								}
								if(click(driver, niam.getNewDisclaimerButton(60), "New discliamer Button", action.BOOLEAN)){
									if(sendKeys(driver, niam.getNewDisclaimerNameTextBox(60), M4DIS1, "DisclaimerName text Box", action.BOOLEAN)){
										if(sendKeys(driver, niam.getDisclaimerDescriptionTextArea(10), M4DISDEC1, "Description text box", action.BOOLEAN)){
											switchToDefaultContent(driver);
											if(switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60))){
												System.err.println("Successfully switched to frame.");
												switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
											}
											if(click(driver, niam.getNewDiscalimerPopUpSaveButton(60), "Save Button", action.BOOLEAN)){
												text = trim(getText(driver, niam.getNewDisclaimerDuplicateDisclaimerPopErrorMsg(60), "duplicate disclaimer error msg", action.BOOLEAN));
												if(text.equalsIgnoreCase(NavatarInvestorAddOnsErrorMessage.DuplicateDisclaimerErrorMsg.trim())){
													appLog.info("Duplicate disclaimer error message and header is verified.");
												} else {
													appLog.error("Duplicate disclaimer error message is not verifed. Expected: "+NavatarInvestorAddOnsErrorMessage.DuplicateDisclaimerErrorMsg+"\tActual: "+text);
													sa.assertTrue(false,"Duplicate disclaimer error message is not verifed. Expected: "+NavatarInvestorAddOnsErrorMessage.DuplicateDisclaimerErrorMsg+"\tActual: "+text);
												}
												if(click(driver, niam.getDuplicateDisclaimerErrorMsgCrossIcon(60), "Duplicate pop up cross ico", action.BOOLEAN)){
													ThreadSleep(1500);
													if(niam.getDuplicateDisclaimerErrorMsgCrossIcon(30)==null){
														appLog.info("Cross icon is working.");
														if(click(driver, niam.getNewDiscalimerPopUpSaveButton(60), "Save Button", action.BOOLEAN)){
															if(click(driver, niam.getNewDiscalaimerDuplicateErrorMsgOkbutton(60), "Duplicate error messgae Ok button", action.BOOLEAN)){
																ThreadSleep(1500);
																if(niam.getDuplicateDisclaimerErrorMsgCrossIcon(30)==null){
																	appLog.info("Ok Button is verified.");
																} else {
																	appLog.error("Ok button is not working.");
																	sa.assertTrue(false,"Ok Button is not working.");
																}
															} else {
																appLog.error("Ok Button cannot be clicked.");
																sa.assertTrue(false,"Ok Button cannot be clicked.");
															}
														} else {
															appLog.error("Save button cannot be clikced.");
															sa.assertTrue(false,"Save button cannot be clikced.");
														}
													} else {
														appLog.error("Cross icon is not working.");
														sa.assertTrue(false,"Cross icon is not working.");
													}
												} else {
													appLog.error("Cross icon cannot be clicked, so cannot check cross icon functionality.");
													sa.assertTrue(false,"Cross icon cannot be clicked, so cannot check cross icon functionality.");
												}
												if(sendKeys(driver, niam.getNewDisclaimerNameTextBox(60), M4DIS1.toLowerCase(), "Disclaimer name text box", action.BOOLEAN)){
													if(click(driver, niam.getNewDiscalimerPopUpSaveButton(60), "Save button", action.BOOLEAN)){
														if(niam.getNewDisclaimerDuplicateDisclaimerPopErrorMsg(60)!=null){
															appLog.info("Disclaimer name is not case sensitive is verified.");
															if(click(driver, niam.getNewDiscalaimerDuplicateErrorMsgOkbutton(60), "Duplicate error messgae Ok button", action.BOOLEAN)){
																ThreadSleep(1500);
																if(niam.getDuplicateDisclaimerErrorMsgCrossIcon(30)==null){
																	appLog.info("Ok Button is verified.");
																} else {
																	appLog.error("Ok button is not working.");
																	sa.assertTrue(false,"Ok Button is not working.");
																}
															} else {
																appLog.error("Ok Button cannot be clicked.");
																sa.assertTrue(false,"Ok Button cannot be clicked.");
															}
														} else {
															appLog.error("Disclaimer name is case sensitive is not verified.");
															sa.assertTrue(false,"Disclaimer name is case sensitive is not verified.");
														}
														
													} else {
														appLog.error("cannot click on the save button, So cannot check the case sensitivity on Disclaimer Name");
														sa.assertTrue(false,"cannot click on the save button, So cannot check the case sensitivity on Disclaimer Name");
													}
												} else {
													appLog.error("cannot enter text in disclaimer name text box.");
													sa.assertTrue(false,"cannot enter text in disclaimer name text box, So cannot check case sensitivity.");
												}
											} else {
												appLog.error("Save button cannot be clikced, So cannot create disclaimer"+M4DIS1);
												sa.assertTrue(false,"Save button cannot be clikced, So cannot create disclaimer"+M4DIS1);
											}
										} else {
											appLog.error("Not able to pass value to disclimer description text box.");
											sa.assertTrue(false,"Not able to pass value to disclimer description text box.");
										}
									} else {
										appLog.error("Not able to pass value to disclimer name text box.");
										sa.assertTrue(false,"Not able to pass value to disclimer name text box.");
									}
								} else {
									appLog.error("New disclaimer button cannot be clikced so cannot check the new disclaimer pop up UI.");
									sa.assertTrue(false,"New disclaimer button cannot be clikced so cannot check the new disclaimer pop up UI.");
								}
							} else {
								appLog.error("Save button cannot be clikced, So cannot create disclaimer"+M4DIS1);
								sa.assertTrue(false,"Save button cannot be clikced, So cannot create disclaimer"+M4DIS1);
							}
						} else {
							appLog.error("Not able to pass value to disclimer description text box.");
							sa.assertTrue(false,"Not able to pass value to disclimer description text box.");
						}
					} else {
						appLog.error("Not able to pass value to disclimer name text box.");
						sa.assertTrue(false,"Not able to pass value to disclimer name text box.");
					}
				} else {
					appLog.error("New disclaimer button cannot be clikced so cannot check the new disclaimer pop up UI.");
					sa.assertTrue(false,"New disclaimer button cannot be clikced so cannot check the new disclaimer pop up UI.");
				}
			} else {
				appLog.error("New disclaimer button cannot be clikced so cannot check the new disclaimer pop up UI.");
				sa.assertTrue(false,"New disclaimer button cannot be clikced so cannot check the new disclaimer pop up UI.");
			}
		} else {
			appLog.error("Navatar addon tab cannot be clicked, So cannot check the Disclaimer Button Functionality.");
			sa.assertTrue(false,"Navatar addon tab cannot be clicked, So cannot check the Disclaimer Button Functionality.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc009_VerifyDisclaimerNameLink(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		if(lp.clickOnTab(TabName.NavatarInvestorAddOns)){
			 switchToFrame(driver,20,lp.getNavatarInvestorAddOnParentFrame(20));
			 if(switchToFrame(driver, 20, niam.getNavatarInvestorAddOnFrame(30))){
					System.err.println("Successfully switched to frame.");
					  switchToFrame(driver,20,niam.getPEDisclaimersSetupFrame(20));
				}
			WebElement ele = FindElement(driver, "//span[contains(@id,'grid_DefaultFoldersView1-cell-1')]/a[@title='"+M4DIS1+"']", "Disclaimer Name link", action.BOOLEAN, 30);
			if(ele != null){
				if(click(driver, ele, M4DIS1+" Disclaimer name link", action.BOOLEAN)){
					if(click(driver, niam.getDisclaimerPopUpCrossIcon(60), "Disclaimer pop up cross icon", action.BOOLEAN)){
						ThreadSleep(1500);
						if(niam.getDisclaimerPopUpCrossIcon(5)==null){
							appLog.info("cross icon is working.");
						} else {
							appLog.error("Cross icon is not working.");
							sa.assertTrue(false,"Cross icon is not working.");
							refresh(driver);
							if(switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60))){
								System.err.println("Successfully switched to frame.");
								switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
							}
						}
					} else {
						appLog.error("Cross icon cannot be clicked, so cannot check cross icon functionality.");
						sa.assertTrue(false,"Cross icon cannot be clicked, so cannot check cross icon functionality.");
						refresh(driver);
						if(switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60))){
							System.err.println("Successfully switched to frame.");
							switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
						}
					}
				} else {
					appLog.error("Disclaimer name link cannot be clicked, So cannot check the functionality.");
					sa.assertTrue(false,"Disclaimer name link cannot be clicked, So cannot check the functionality.");
				}
			} else {
				appLog.error("Disclaimer Name Link is not present, So cannot check the link functionality.");
				sa.assertTrue(false,"Disclaimer Name Link is not present, So cannot check the link functionality.");
			}
			ele = FindElement(driver, "//span[contains(@id,'grid_DefaultFoldersView1-cell-1')]/a[@title='"+M4DIS1+"']", "Disclaimer Name link", action.BOOLEAN, 30);
			if(ele != null){
				if(click(driver, ele, M4DIS1+" Disclaimer name link", action.BOOLEAN)){
					if(click(driver, niam.getDisclaimerPopUpCloseButton(60), "Disclaimer pop up cross icon", action.BOOLEAN)){
						ThreadSleep(1500);
						if(niam.getDisclaimerPopUpCrossIcon(5)==null){
							appLog.info("Close Button is working.");
						} else {
							appLog.error("Close Button is not working.");
							sa.assertTrue(false,"Close Button is not working.");
						}
					} else {
						appLog.error("Close Button cannot be clicked, so cannot check Close Button functionality.");
						sa.assertTrue(false,"Close Button cannot be clicked, so cannot check Close Button functionality.");
					}
				} else {
					appLog.error("Disclaimer name link cannot be clicked, So cannot check the functionality.");
					sa.assertTrue(false,"Disclaimer name link cannot be clicked, So cannot check the functionality.");
				}
			} else {
				appLog.error("Disclaimer Name Link is not present, So cannot check the link functionality.");
				sa.assertTrue(false,"Disclaimer Name Link is not present, So cannot check the link functionality.");
			}
		} else {
			appLog.error("NI Add on tab cannot be clicked, So cannot check the disclaimer name link.");
			sa.assertTrue(false,"NI Add on tab cannot be clicked, So cannot check the disclaimer name link.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc010_VerifyViewLinkPopUpUIAndDownloadReport(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		if(lp.clickOnTab(TabName.NavatarInvestorAddOns)){
			 switchToFrame(driver,20,lp.getNavatarInvestorAddOnParentFrame(20));
			 if(switchToFrame(driver, 20, niam.getNavatarInvestorAddOnFrame(30))){
					System.err.println("Successfully switched to frame.");
					  switchToFrame(driver,20,niam.getPEDisclaimersSetupFrame(20));
				}
			if(niam.clickOnViewLinkOfDisclaimer(M4DIS1)){
				String text = trim(getText(driver, niam.getDisclaimerViewPopUpHeaderName(60), "View pop up header", action.BOOLEAN));
				if(text.contains("Disclaimer Statistics -") && text.contains(M4F1)){
					appLog.info("Header is successfully verified.");
				} else {
					appLog.error("Pop Up header is not matched. Excpected: Disclaimer Statistics - "+M4DIS1+"\tActual: "+text);
					sa.assertTrue(false,"Pop Up header is not matched. Excpected: Disclaimer Statistics - "+M4DIS1+"\tActual: "+text);
				}
				text = getSelectedOptionOfDropDown(driver, niam.getDisclaimerNamePickList(60), "Disclaimer name pick list", "text");
				if(text.equalsIgnoreCase(M4DIS1)){
					appLog.info(M4DIS1+" is selected and is present in the list.");
				} else {
					appLog.error(M4DIS1+" disclaimer is not present in the list.");
					sa.assertTrue(false,M4DIS1+" disclaimer is not present in the list.");
				}
				if(niam.getDisclaimerViewPopUpSearchBox(60)!=null){
					appLog.info("Search box is present on the pop up.");
				} else {
					appLog.error("Seach box is not present on the pop up.");
					sa.assertTrue(false,"Seach box is not present on the pop up.");
				}
				text = getSelectedOptionOfDropDown(driver, niam.getDisclaimerViewPopUpStatusPickList(60), "Status pick list", "text");
				if(text.equalsIgnoreCase("All")){
					appLog.info("By default selected is verified.");
				} else {
					appLog.error("All is not by default selected in the status pick list. Actual: "+text);
					sa.assertTrue(false,"All is not by default selected in the status pick list. Actual: "+text);
				}
				List<WebElement> allOptionsOfStatusPickList = allOptionsInDropDrop(driver, niam.getDisclaimerViewPopUpStatusPickList(60), "Status pick list.");
				for(int i = 0; i < allOptionsOfStatusPickList.size(); i++){
					if(allOptionsOfStatusPickList.get(i).getText().equalsIgnoreCase("All") || allOptionsOfStatusPickList.get(i).getText().equalsIgnoreCase("Waiting") || allOptionsOfStatusPickList.get(i).getText().equalsIgnoreCase("Accepted")){
						appLog.info(allOptionsOfStatusPickList.get(i).getText()+" is .verified.");
					} else {
						appLog.error(allOptionsOfStatusPickList.get(i).getText()+" is not verified.");
						sa.assertTrue(false,allOptionsOfStatusPickList.get(i).getText()+" is not verified.");
					}
				}
				List<WebElement> header = FindElements(driver, "//span[contains(@id,'Disclaimer_Statistics-header-')][contains(@id,'-box-text')][@class='aw-item-text ']", "Disclaimer view pop up grid header");
				if(header.get(0).getText().trim().equalsIgnoreCase("Contact Name")){
					appLog.info("Contact Name header is verified.");
				} else {
					appLog.error("Contact Name header is not verifed. Actual: "+header.get(0).getText().trim());
					sa.assertTrue(false,"Contact Name header is not verifed. Actual: "+header.get(0).getText().trim());
				}
				if(header.get(1).getText().trim().equalsIgnoreCase("Email")){
					appLog.info("Email header is verified.");
				} else {
					appLog.error("Email header is not verifed. Actual: "+header.get(1).getText().trim());
					sa.assertTrue(false,"Email header is not verifed. Actual: "+header.get(1).getText().trim());
				}
				if(header.get(2).getText().trim().equalsIgnoreCase("Firm")){
					appLog.info("Firm header is verified.");
				} else {
					appLog.error("Firm header is not verifed. Actual: "+header.get(2).getText().trim());
					sa.assertTrue(false,"Firm header is not verifed. Actual: "+header.get(2).getText().trim());
				}
				if(header.get(3).getText().trim().equalsIgnoreCase("Status")){
					appLog.info("Status header is verified.");
				} else {
					appLog.error("Status header is not verifed. Actual: "+header.get(3).getText().trim());
					sa.assertTrue(false,"Status header is not verifed. Actual: "+header.get(3).getText().trim());
				}
				if(header.get(4).getText().trim().equalsIgnoreCase("Accepted On")){
					appLog.info("Accepted On header is verified.");
				} else {
					appLog.error("Accepted On header is not verifed. Actual: "+header.get(4).getText().trim());
					sa.assertTrue(false,"Accepted On header is not verifed. Actual: "+header.get(4).getText().trim());
				}
				WebElement ele = FindElement(driver, "//span[@id='Disclaimer_Statistics-cell-0-0']/span", "No data error msg", action.BOOLEAN, 30);
				text = getAttribute(driver, ele, "No Data Error msg", "title");
				if(text.equalsIgnoreCase(NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage)){
					appLog.info("No data to display error msg is not verified.");
				} else {
					appLog.error("No data error messgae is not verified. Expected: "+NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage+"\tActual: "+text);
					sa.assertTrue(false,"No data error messgae is not verified. Expected: "+NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage+"\tActual: "+text);
				}
				
				text = getText(driver, niam.getDisclaimerViewPopUpRecordLabel(60), "record label", action.BOOLEAN);
				if(text.contains("Records:")){
					appLog.info("record label is verified.");
				} else {
					appLog.error("Record label is not verified. Expected: Report\tActual: "+text);
					sa.assertTrue(false,"Record label is not verified. Expected: Report\tActual: "+text);
				}
				
				text = getText(driver, niam.getDisclaimerViewPopUpRecordCount(60), "Record Count", action.BOOLEAN);
				if(Integer.parseInt(text)==0){
					appLog.info("Record coutn is verified.");
				} else {
					appLog.error("Record count is not verified.Expected: 0\tActual: "+Integer.parseInt(text));
					sa.assertTrue(false,"Record count is not verified.Expected: 0\tActual: "+Integer.parseInt(text));
				}
				
				text = getText(driver, niam.getDisclaimerViewPopUpClickHereText(60), "Click here text", action.BOOLEAN);
				if(text.contains("Click here") && text.contains("to export all the statistics for this fund.")){
					appLog.info("click here is verified.");
				} else {
					appLog.error("Click here link is not verified.Expected: Click here to export all the statistics for this fund.\tActual: "+text);
					sa.assertTrue(false,"Click here link is not verified.Expected: Click here to export all the statistics for this fund.\tActual: "+text);
				}
				
				if(niam.getDisclaimerViewPopUpCrossIcon(60)!=null){
					appLog.info("cross icon is verified.");
				} else {
					appLog.error("Cross icon is not present ont he pop up");
					sa.assertTrue(false,"Cross icon is not present on the pop up");
				}
				
				if(click(driver, niam.getDisclaimerViewPopUpCrossIcon(60), "Cross icon", action.BOOLEAN)){
					ThreadSleep(1500);
					if(niam.getDisclaimerViewPopUpCrossIcon(2)==null){
						appLog.info("cross icon is working.");
					} else {
						appLog.error("Cross icon is not working.");
						sa.assertTrue(false,"Cross icon is not working.");
					}
				} else {
					appLog.error("Cross icon is not clicked. So cannot check the functionality.");
					sa.assertTrue(false,"Cross icon is not clicked. So cannot check the functionality.");
				}
			} else {
				appLog.error("Not able to click on disclaimer view link.");
				sa.assertTrue(false,"Not able to click on disclaimer view link.");
			}
			if(niam.clickOnViewLinkOfDisclaimer(M4DIS1)){
				if(click(driver, niam.getDisclaimerViewPopUpClickHereLink(60), "Click here link", action.BOOLEAN)){
					try {
						Robot rob = new Robot();
						ThreadSleep(5000);
						rob.keyPress(KeyEvent.VK_CONTROL);
						rob.keyPress(KeyEvent.VK_J);
						ThreadSleep(500);
						rob.keyRelease(KeyEvent.VK_J);
						rob.keyRelease(KeyEvent.VK_CONTROL);
						ThreadSleep(500);
					} catch (AWTException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String parentWin = switchOnWindow(driver);
					BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
					if(parentWin!=null){
						String text = bp.getDownloadedFileName();
						if(text.contains("Disclaimers_"+M4F1+"_"+getSystemDate("yyyyMMdd"))){
							appLog.info("File name is verified successfully.");
						} else {
							appLog.info("Downloading file name is not verified. Expected: "+"Disclaimers_"+M4F1+"_"+getSystemDate("yyyyMMdd")+"\tActual: "+text);
							sa.assertTrue(false,"Downloading file name is not verified. Expected: "+"Disclaimers_"+M4F1+"_"+getSystemDate("yyyyMMdd")+"\tActual: "+text);
						}
					} else {
						appLog.error("Downloaded file name cannot be fetched, So cannot verify downloaded file name.");
						sa.assertTrue(false,"Downloaded file name cannot be fetched, So cannot verify downloaded file name.");
					}
				} else {
					appLog.error("Click here link cannot be clicked, So cannot check the click here functionality.");
					sa.assertTrue(false,"Click here link cannot be clicked, So cannot check the click here functionality.");
				}
			} else {
				appLog.error("Not able to click on disclaimer view link. So cannot check the click here functionality.");
				sa.assertTrue(false,"Not able to click on disclaimer view link. So cannot check the click here functionality.");
			}
		} else {
			appLog.error("Navatar Investor Add On tab cannot be clicked, so cannot check view UI");
			sa.assertTrue(false,"Navatar Investor Add On tab cannot be clicked, so cannot check view UI");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc011_VerifyActivationOfADisclaimerAndItsImpact(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if(lp.clickOnTab(TabName.NavatarInvestorAddOns)){
			 switchToFrame(driver,20,lp.getNavatarInvestorAddOnParentFrame(20));
			 if(switchToFrame(driver, 20, niam.getNavatarInvestorAddOnFrame(30))){
					System.err.println("Successfully switched to frame.");
					  switchToFrame(driver,20,niam.getPEDisclaimersSetupFrame(20));
				}
			if(niam.clickOnActivationLinkOfDisclaimer(M4DIS1, 60)){
				String text = trim(getText(driver, niam.getDisclaimerActivationPopUp(60), "Activation pop up header", action.BOOLEAN));
				if(text.equalsIgnoreCase("Confirm Disclaimer Activation")){
					appLog.info("Disclaimer confirmation pop up header is verified.");
				} else {
					appLog.error("Disclaimer activation po up header is not verified.");
					sa.assertTrue(false,"Disclaimer activation po up header is not verified.");
				}
				if(niam.getDisclaimerActivationPopUpCrossIcon(60)!=null){
					appLog.info("Cross icon is verified.");
				} else {
					appLog.error("Cross icon is not verified.");
					sa.assertTrue(false,"Cross icon is not verified.");
				}
				if(niam.getDisclaimerActivationPopUpYesButton(60)!=null){
					appLog.info("Yes button is verifed.");
				} else {
					appLog.error("Yes button is not verifed.");
					sa.assertTrue(false,"Yes button is not verifed.");
				}
				if(niam.getDisclaimerActivationPopUpNoButton(60)!=null){
					appLog.info("No button is verified.");
				} else {
					appLog.error("No button is not verified.");
					sa.assertTrue(false,"No button is not verified.");
				}
				text = trim(getText(driver, niam.getDisclaimerActivationPopUpMessage(60), "Discalimer activation Pop up message", action.BOOLEAN));
				if(text.contains(NavatarInvestorAddOnsErrorMessage.DisclaimerActivationPopUpMsg1)&&text.contains(NavatarInvestorAddOnsErrorMessage.DisclaimerActivationPopUpMsg2)&&text.contains(NavatarInvestorAddOnsErrorMessage.DisclaimerActivationPopUpMsg3)&&text.contains(NavatarInvestorAddOnsErrorMessage.DisclaimerActivationPopUpMsg4)&&text.contains(NavatarInvestorAddOnsErrorMessage.DisclaimerActivationPopUpMsg5)){
					appLog.info("Activation confirmation error messgae is verfied.");
				} else {
					appLog.error("Discalimer activation error message is not verifed.Expected: "+NavatarInvestorAddOnsErrorMessage.DisclaimerActivationPopUpMsg1+" "+NavatarInvestorAddOnsErrorMessage.DisclaimerActivationPopUpMsg2+" "+NavatarInvestorAddOnsErrorMessage.DisclaimerActivationPopUpMsg3+" "+NavatarInvestorAddOnsErrorMessage.DisclaimerActivationPopUpMsg4+" "+NavatarInvestorAddOnsErrorMessage.DisclaimerActivationPopUpMsg5+"\tActual: "+text);
					sa.assertTrue(false,"Discalimer activation error message is not verifed.Expected: "+NavatarInvestorAddOnsErrorMessage.DisclaimerActivationPopUpMsg1+" "+NavatarInvestorAddOnsErrorMessage.DisclaimerActivationPopUpMsg2+" "+NavatarInvestorAddOnsErrorMessage.DisclaimerActivationPopUpMsg3+" "+NavatarInvestorAddOnsErrorMessage.DisclaimerActivationPopUpMsg4+" "+NavatarInvestorAddOnsErrorMessage.DisclaimerActivationPopUpMsg5+"\tActual: "+text);
				}
				
				if(click(driver, niam.getDisclaimerActivationPopUpCrossIcon(60), "cross icon", action.BOOLEAN)){
					if(niam.getDisclaimerActivationPopUpCrossIcon(5)==null){
						appLog.info("Cross icon is Working.");
					} else {
						appLog.error("Cross icon is not working.");
						sa.assertTrue(false,"Cross icon is not working.");
						niam.recover();
					}
				} else {
					appLog.error("Cross icon cannot be clicked, So cannot check the functionality");
					sa.assertTrue(false,"Cross icon cannot be clicked, So cannot check the functionality");
					niam.recover();
				}
				
				if(niam.clickOnActivationLinkOfDisclaimer(M4DIS1, 60)){
					if(click(driver, niam.getDisclaimerActivationPopUpNoButton(60), "No button", action.BOOLEAN)){
						ThreadSleep(1500);
						if(niam.getDisclaimerActivationPopUpNoButton(2)!=null){
							appLog.error("No button is not working.");
							sa.assertTrue(false,"No button is not working.");
							niam.recover();
						} else {
							appLog.info("No button is verifed.");
						}
					} else {
						appLog.error("No Button cannot be clikced, So cannot check the functionality.");
						sa.assertTrue(false,"No Button cannot be clikced, So cannot check the functionality.");
						niam.recover();
					}
				} else {
					appLog.error(M4DIS1+" Activation button cannot be clicked, So cannot continue with the test case.");
					sa.assertTrue(false,M4DIS1+" Activation button cannot be clicked, So cannot continue with the test case.");
				}
				
			} else {
				appLog.error(M4DIS1+" Activation button cannot be clicked, So cannot continue with the test case.");
				sa.assertTrue(false,M4DIS1+" Activation button cannot be clicked, So cannot continue with the test case.");
			}
			if(niam.clickOnActivationLinkOfDisclaimer(M4DIS1, 60)){
				if(click(driver, niam.getDisclaimerActivationPopUpYesButton(60), "Yes button", action.BOOLEAN)){
					ThreadSleep(1500);
					if(niam.getDisclaimerActivationPopUp(5)!=null){
						appLog.error("Pop up is not getting closed.");
						sa.assertTrue(false,"Pop up is not getting closed.");
						niam.recover();
					} else {
						appLog.info("Pop us closed successfully.");
					}
					
					if(niam.verifyDisclaimerGrid(DisclaimerGrid.Deactivate, M4DIS1, DisclaimerGrid.View, DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)){
						appLog.info("content grid is verfied successfully.");
					} else {
						appLog.error("Content grid is not verfied.");
						sa.assertTrue(false,"Content grid is not verfied.");
					}
					if(niam.getCurrentStatusOfDisclaimer().length==2){
						if(niam.getCurrentStatusOfDisclaimer()[0].equalsIgnoreCase("Active")){
							appLog.info("Status is successfully verifed.");
						} else {
							appLog.error("Status is not verifed. Expected: Active\tActual: "+niam.getCurrentStatusOfDisclaimer()[0]);
							sa.assertTrue(false,"Status is not verifed. Expected: Active\tActual: "+niam.getCurrentStatusOfDisclaimer()[0]);
						}
						if(niam.getCurrentStatusOfDisclaimer()[1].equalsIgnoreCase("green")){
							appLog.info("Status color is successfully verifed.");
						} else {
							appLog.error("Status color is not verifed. Expected: Green\tActual: "+niam.getCurrentStatusOfDisclaimer()[1]);
							sa.assertTrue(false,"Status color is not verifed. Expected: Green\tActual: "+niam.getCurrentStatusOfDisclaimer()[1]);
						}
					} else {
						appLog.error("Status is not verifed.");
						sa.assertTrue(false,"Status is not verifed.");
					}
					
					if(niam.getActiveDisclaimerStatisticsValue().equalsIgnoreCase(M4DIS1)){
						appLog.info("Statistics value is verifed.");
					} else {
						appLog.error("Statistics value is not verifed. Expected: "+M4DIS1+"\tActual: "+niam.getActiveDisclaimerStatisticsValue());
						sa.assertTrue(false,"Statistics value is not verifed. Expected: "+M4DIS1+"\tActual: "+niam.getActiveDisclaimerStatisticsValue());
					}
					
					if(niam.getAcceptedDiscalimerCount()==0){
						appLog.info("Accespted disclaimer count is verified.");
					} else {
						appLog.error("Accepted disclaimer count is not verifed. Expected: 0\tActual: "+niam.getAcceptedDiscalimerCount());
						sa.assertTrue(false,"Accepted disclaimer count is not verifed. Expected: 0\tActual: "+niam.getAcceptedDiscalimerCount());
					}
					
					if(niam.getWaitingDisclaimerCount()==2){
						appLog.info("Waiting disclaimer count is verifed.");
					} else {
						appLog.error("Waiting disclaimer count is not verifed.Expected: 2\tActual: "+niam.getWaitingDisclaimerCount());
						sa.assertTrue(false,"Waiting disclaimer count is not verifed.Expected: 2\tActual: "+niam.getWaitingDisclaimerCount());
					}
					if(niam.clickOnViewLinkOfDisclaimer(M4DIS1)){
						String text = getSelectedOptionOfDropDown(driver, niam.getDisclaimerNamePickList(60), "Disclaimer name pick list", "text");
						if(text.equalsIgnoreCase(M4DIS1+" (Active)")){
							appLog.info(M4DIS1+" (Active)"+" is selected and is present in the list.");
						} else {
							appLog.error(M4DIS1+" (Active)"+" disclaimer is not present in the list.");
							sa.assertTrue(false,M4DIS1+" (Active)"+" disclaimer is not present in the list.");
						}
						if(niam.getContactsInVeiwPopUpGrid(60).size()==2){
							appLog.info("Contacts are verifed.");
						} else {
							appLog.error("Number of contacts in view grid is not verified.Expected: 2\tActual: "+niam.getContactsInVeiwPopUpGrid(60).size());
							sa.assertTrue(false,"Number of contacts in view grid is not verified.Expected: 2\tActual: "+niam.getContactsInVeiwPopUpGrid(60).size());
						}
						for(int i = 0; i < niam.getContactsInVeiwPopUpGrid(60).size(); i++){
							text = getText(driver, niam.getContactsInVeiwPopUpGrid(60).get(i), "Contact Data", action.BOOLEAN);
							if(text.contains(M4CFN1+" "+M4CLN1) || text.contains(M4CFN2+" "+M4CLN2)){
								appLog.info("Contact Name is verifed.");
							} else {
								appLog.error("Contact Name is not verifed.");
								sa.assertTrue(false,"Contact Name is not verifed.");
							}
							if(text.contains(M4C1Email)||text.contains(M4C2Email)){
								appLog.info("Contact email is verifed.");
							} else {
								appLog.error("Contact email is not verifed.");
								sa.assertTrue(false,"Contact email is not verifed.");
							}
							if(text.contains(M4I1)||text.contains(M4I2)){
								appLog.info("Contact institution is verifed.");
							} else {
								appLog.error("Contact institution is not verifed.");
								sa.assertTrue(false,"Contact institution is not verifed.");
							}
						}
						WebElement ele = FindElement(driver, "//a[@title='"+M4C1Email+"']/../following-sibling::span[3]/span", "Accepted Date", action.BOOLEAN, 30);
						text = getAttribute(driver, ele, "", "title");
						if(text.isEmpty()){
							appLog.info("Accepted date for contact 1 is verifed.");
						} else {
							appLog.error("Accepted date in not blank for contact 1.");
							sa.assertTrue(false,"Accepted date in not blank for contact 1.");
						}
						ele = FindElement(driver, "//a[@title='"+M4C1Email+"']/../following-sibling::span[2]/img", "Waiting img", action.BOOLEAN, 30);
						text = getAttribute(driver, ele, "", "title");
						if(text.equalsIgnoreCase("Waiting")){
							appLog.info("Waiting image for contact 1 is verifed.");
						} else {
							appLog.error("Waiting image in not verified for contact 1.Expected: Waiting\tActual: "+text);
							sa.assertTrue(false,"Waiting image in not verified for contact 1.Expected: Waiting\tActual: "+text);
						}
						
						ele = FindElement(driver, "//a[@title='"+M4C2Email+"']/../following-sibling::span[3]/span", "Accepted Date", action.BOOLEAN, 30);
						text = getAttribute(driver, ele, "", "title");
						if(text.isEmpty()){
							appLog.info("Accepted date for contact 2 is verifed.");
						} else {
							appLog.error("Accepted date in not blank for contact 2.");
							sa.assertTrue(false,"Accepted date in not blank for contact 2.");
						}
						ele = FindElement(driver, "//a[@title='"+M4C2Email+"']/../following-sibling::span[2]/img", "Waiting img", action.BOOLEAN, 30);
						text = getAttribute(driver, ele, "", "title");
						if(text.equalsIgnoreCase("Waiting")){
							appLog.info("Waiting image for contact 2 is verifed.");
						} else {
							appLog.error("Waiting image in not verified for contact 2.Expected: Waiting\tActual: "+text);
							sa.assertTrue(false,"Waiting image in not verified for contact 2.Expected: Waiting\tActual: "+text);
						}
						if(niam.getDisclaimerViewPopUpRecordCount()==2){
							appLog.info("Record count is verifed.");
						} else {
							appLog.error("View record count is not verified.Expected: 2\tActual: "+niam.getDisclaimerViewPopUpRecordCount());
							sa.assertTrue(false,"View record count is not verified.Expected: 2\tActual: "+niam.getDisclaimerViewPopUpRecordCount());
						}
					} else {
						appLog.error("View link cannot be clikced, So cannot check the view pop up.");
						sa.assertTrue(false,"View link cannot be clikced, So cannot check the view pop up.");
					}
				} else {
					appLog.error("Yes Button cannot be clikced, So cannot check the functionality.");
					sa.assertTrue(false,"Yes Button cannot be clikced, So cannot check the functionality.");
				}
			} else {
				appLog.error(M4DIS1+" Activation button cannot be clicked, So cannot continue with the test case.");
				sa.assertTrue(false,M4DIS1+" Activation button cannot be clicked, So cannot continue with the test case.");
			}
		} else {
			appLog.error("Navatar investor add on tab cannot be clicked, So cannot continue with the activtion process.");
			sa.assertTrue(false,"Navatar investor add on tab cannot be clicked, So cannot continue with the activtion process.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc012_VerifyPendingDisclaimerAtInvestorFirmHomeAllDocumentPage(){
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer afp = new AllFirmsPageBusinesslayer(driver);
		DisclaimerPageBussinessLayer disPage = new DisclaimerPageBussinessLayer(driver);
		boolean flag = false;
		if (ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M4Contact2", excelLabel.Registered).equalsIgnoreCase("Not Registered")) {
			try {
				investorRegLink = new EmailLib().getInvestorRegLink("InvitationMail",gmailUserName,gmailPassword,Org3CRMUser1EmailID,M4C2Email);
				appLog.info("Investor Registration Link: " + investorRegLink);
			} catch (InterruptedException e) {
				appLog.info("Invitation mail not found.");
				e.printStackTrace();
			}
			if (investorRegLink != null) {
				driver.get(investorRegLink);
				if (bp.targetRegistration(M4CFN2, M4CLN2, M4C2Email, M4I2,
						adminPassword)) {
					appLog.info(
							"Investor is registered successfully: " + M4CFN2 + " " + M4CLN2);
					flag = true;
					ExcelUtils.writeData("Registered", "Contacts", excelLabel.Variable_Name, "M4Contact2", excelLabel.Registered);
				} else {
					driver.get(ExcelUtils.readDataFromPropertyFile("TargetRegistrationURL"));
					appLog.info("Register Target URL through Direct URL..");
					if (bp.targetRegistration(M4CFN2, M4CLN2, M4C2Email,
							M4I2, adminPassword)) {
						appLog.info("Investor is registered successfully: " + M4CFN2 + " "
								+ M4CLN2);
						flag = true;
						ExcelUtils.writeData("Registered", "Contacts", excelLabel.Variable_Name, "M4Contact2", excelLabel.Registered);
					} else {
						appLog.error("Investor is not Registered through Invited Link: " + M4CFN2 + " "
								+ M4CLN2);
						sa.assertTrue(false, "Investor is not Registered through Invited Link: " + M4CFN2
								+ " " + M4CLN2);
					}
				}
			} else {
				driver.get(ExcelUtils.readDataFromPropertyFile("TargetRegistrationURL"));
				appLog.info("Register Target URL through Direct URL..");
				if (bp.targetRegistration(M4CFN2, M4CLN2, M4C2Email,
						M4I2, adminPassword)) {
					appLog.info("Investor is registered successfully: " + M4CFN2 + " "
							+ M4CLN2);
					flag = true;
					ExcelUtils.writeData("Registered", "Contacts", excelLabel.Variable_Name, "M4Contact2", excelLabel.Registered);
				} else {
					appLog.error("Investor is not Registered through Invited Link: " + M4CFN2 + " "
							+ M4CLN2);
					sa.assertTrue(false, "Investor is not Registered through Invited Link: " + M4CFN2
							+ " " + M4CLN2);
				}
			}		
		} else {
			appLog.info("investor "+M4CFN2+" "+M4CLN2+" is already Registered.");
			sa.assertTrue(false, "investor "+M4CFN2+" "+M4CLN2+" is already Registered.");
			if(lp.investorLogin(M4C2Email, adminPassword)){
				flag = true;
			}
		}
		
		if(flag){
			String text = getSelectedOptionOfDropDown(driver, bp.getFirmNameDropdown(60), "Firm selection drop down", "text");
			if(text.equalsIgnoreCase(Org3FirmName)){
				appLog.info(Org3FirmName+" is selected");
			} else {
				appLog.error(Org3FirmName+" Firm is not selected.");
				sa.assertTrue(false,Org3FirmName+" Firm is not selected.");
			}
			if(bp.getPendingDisclaimerPopUpHeader(60)!=null){
				appLog.info("Pending disclaimer pop up is open.");
				text = trim(getText(driver, bp.getPendingDisclaimerPopUpHeader(60), "Header", action.BOOLEAN));
				if(text.equalsIgnoreCase("Pending Disclaimer(s)")){
					appLog.info("Pending disclaimer pop up header is verified");
				} else {
					appLog.error("Pending disclaimer pop up header text is not verified.Expected: Pending Disclaimer(s)\tActual: "+text);
					sa.assertTrue(false,"Pending disclaimer pop up header text is not verified.Expected: Pending Disclaimer(s)\tActual: "+text);
				}
			} else {
				appLog.error("Pending disclaimer pop up is not open.");
				sa.assertTrue(false,"Pending disclaimer pop up is not open.");
			}
			if(bp.getPendingDisclaimerPopUpCrossIcon(60)!=null){
				appLog.info("Pending disclaimer pop up cross icon is present.");
			} else {
				appLog.error("Pending disclaimer pop up cross icon is not present.");
				sa.assertTrue(false,"Pending disclaimer pop up cross icon is not present.");
			}
			if(bp.getPendingDisclaimerPopGoToDisclaimerButton(60)!=null){
				appLog.info("Pending disclaimer pop up Go to disclaimer button is present.");
			} else {
				appLog.error("Pending disclaimer pop up Go to disclaimer button is not present.");
				sa.assertTrue(false,"Pending disclaimer pop up Go to disclaimer button is not present.");
			}
			if(bp.getPendingDisclaimerPopUpCancelBUtton(60)!=null){
				appLog.info("Pending disclaimer pop up Cancel button is present.");
			} else {
				appLog.error("Pending disclaimer pop up Cancel button is not present.");
				sa.assertTrue(false,"Pending disclaimer pop up Cancel button is not present.");
			}
			
			if(isEnabled(driver, bp.getFirmNameDropdown(60), "Firm Name drop down")){
				appLog.info("Drop down is enabled.");
			} else {
				appLog.error("Drop down is not enabled");
				sa.assertTrue(false,"Drop down is not enabled");
			}
			
			if(isEnabled(driver, bp.getProfileLink(60), "Profile link")){
				appLog.info("Profile link is enabled.");
			} else {
				appLog.error("Profile link is not enabled");
				sa.assertTrue(false,"Profile link is not enabled");
			}
			
			if(isEnabled(driver, lp.getInvestorLogout(), "Logout link")){
				appLog.info("Logout link is enabled.");
			} else {
				appLog.error("Logout link is not enabled");
				sa.assertTrue(false,"Logout link is not enabled");
			}
			if(selectVisibleTextFromDropDown(driver, bp.getFirmNameDropdown(60), "Firm name drop down", "All Firms")){
				if(click(driver, afp.getNavatarLogo(60), "Navatar Logo", action.BOOLEAN)){
					if(bp.getPendingDisclaimerPopUpHeader(60)!=null){
						appLog.info("Pending disclaimer pop up is open.");
						text = trim(getText(driver, bp.getPendingDisclaimerPopUpHeader(60), "Header", action.BOOLEAN));
						if(text.equalsIgnoreCase("Pending Disclaimer(s)")){
							appLog.info("Pending disclaimer pop up header is verified");
						} else {
							appLog.error("Pending disclaimer pop up header text is not verified.Expected: Pending Disclaimer(s)\tActual: "+text);
							sa.assertTrue(false,"Pending disclaimer pop up header text is not verified.Expected: Pending Disclaimer(s)\tActual: "+text);
						}
					} else {
						appLog.error("Pending disclaimer pop up is not open.");
						sa.assertTrue(false,"Pending disclaimer pop up is not open.");
					}
					if(bp.verifyPendingDisclaimerPopUpMessage(60)){
						appLog.info("Pending disclaimer pop up message is verified.");
					}
					if(bp.getPendingDisclaimerPopUpCrossIcon(60)!=null){
						appLog.info("Pending disclaimer pop up cross icon is present.");
					} else {
						appLog.error("Pending disclaimer pop up cross icon is not present.");
						sa.assertTrue(false,"Pending disclaimer pop up cross icon is not present.");
					}
					if(bp.getPendingDisclaimerPopGoToDisclaimerButton(60)!=null){
						appLog.info("Pending disclaimer pop up Go to disclaimer button is present.");
					} else {
						appLog.error("Pending disclaimer pop up Go to disclaimer button is not present.");
						sa.assertTrue(false,"Pending disclaimer pop up Go to disclaimer button is not present.");
					}
					if(bp.getPendingDisclaimerPopUpCancelBUtton(60)!=null){
						appLog.info("Pending disclaimer pop up Cancel button is present.");
					} else {
						appLog.error("Pending disclaimer pop up Cancel button is not present.");
						sa.assertTrue(false,"Pending disclaimer pop up Cancel button is not present.");
					}
					
					if(isEnabled(driver, bp.getFirmNameDropdown(60), "Firm Name drop down")){
						appLog.info("Drop down is enabled.");
					} else {
						appLog.error("Drop down is not enabled");
						sa.assertTrue(false,"Drop down is not enabled");
					}
					
					if(isEnabled(driver, bp.getProfileLink(60), "Profile link")){
						appLog.info("Profile link is enabled.");
					} else {
						appLog.error("Profile link is not enabled");
						sa.assertTrue(false,"Profile link is not enabled");
					}
					
					if(isEnabled(driver, lp.getInvestorLogout(), "Logout link")){
						appLog.info("Logout link is enabled.");
					} else {
						appLog.error("Logout link is not enabled");
						sa.assertTrue(false,"Logout link is not enabled");
					}
				} else {
					appLog.error("Navatar logo cannot be clicked, So cannot check the Pending disclaimer Pop Up after click.");
					sa.assertTrue(false,"Navatar logo cannot be clicked, So cannot check the Pending disclaimer Pop Up after click.");
				}
				
				refresh(driver);
				if(bp.getPendingDisclaimerPopUpHeader(60)!=null){
					appLog.info("Pending disclaimer pop up is open.");
					text = trim(getText(driver, bp.getPendingDisclaimerPopUpHeader(60), "Header", action.BOOLEAN));
					if(text.equalsIgnoreCase("Pending Disclaimer(s)")){
						appLog.info("Pending disclaimer pop up header is verified");
					} else {
						appLog.error("Pending disclaimer pop up header text is not verified.Expected: Pending Disclaimer(s)\tActual: "+text);
						sa.assertTrue(false,"Pending disclaimer pop up header text is not verified.Expected: Pending Disclaimer(s)\tActual: "+text);
					}
					if(bp.getPendingDisclaimerPopUpCrossIcon(60)!=null){
						appLog.info("Pending disclaimer pop up cross icon is present.");
					} else {
						appLog.error("Pending disclaimer pop up cross icon is not present.");
						sa.assertTrue(false,"Pending disclaimer pop up cross icon is not present.");
					}
					if(bp.getPendingDisclaimerPopGoToDisclaimerButton(60)!=null){
						appLog.info("Pending disclaimer pop up Go to disclaimer button is present.");
					} else {
						appLog.error("Pending disclaimer pop up Go to disclaimer button is not present.");
						sa.assertTrue(false,"Pending disclaimer pop up Go to disclaimer button is not present.");
					}
					if(bp.getPendingDisclaimerPopUpCancelBUtton(60)!=null){
						appLog.info("Pending disclaimer pop up Cancel button is present.");
					} else {
						appLog.error("Pending disclaimer pop up Cancel button is not present.");
						sa.assertTrue(false,"Pending disclaimer pop up Cancel button is not present.");
					}
					
					if(isEnabled(driver, bp.getFirmNameDropdown(60), "Firm Name drop down")){
						appLog.info("Drop down is enabled.");
					} else {
						appLog.error("Drop down is not enabled");
						sa.assertTrue(false,"Drop down is not enabled");
					}
					
					if(isEnabled(driver, bp.getProfileLink(60), "Profile link")){
						appLog.info("Profile link is enabled.");
					} else {
						appLog.error("Profile link is not enabled");
						sa.assertTrue(false,"Profile link is not enabled");
					}
					
					if(isEnabled(driver, lp.getInvestorLogout(), "Logout link")){
						appLog.info("Logout link is enabled.");
					} else {
						appLog.error("Logout link is not enabled");
						sa.assertTrue(false,"Logout link is not enabled");
					}
					
					if(click(driver, bp.getPendingDisclaimerPopUpCrossIcon(60), "Cross icon", action.BOOLEAN)){
						ThreadSleep(1500);
						if(bp.getPendingDisclaimerPopUpHeader(5)!=null){
							appLog.error("Pending disclaimer pop up is still open after clicking on cross icon.");
							sa.assertTrue(false,"Pending disclaimer pop up is still open  after clicking on cross icon.");
						} else {
							appLog.info("Cross icon is working");
						}
					} else {
						appLog.error("Cross icon cannot be clicked, So cannot check cross icon fnctionality.");
						sa.assertTrue(false,"Cross icon cannot be clicked, So cannot check cross icon fnctionality.");
					}
					refresh(driver);
					if(click(driver, bp.getPendingDisclaimerPopUpCancelBUtton(60), "cancel button", action.BOOLEAN)){
						ThreadSleep(1500);
						if(bp.getPendingDisclaimerPopUpHeader(5)!=null){
							appLog.error("Pending disclaimer pop up is still open after clicking on cancel button.");
							sa.assertTrue(false,"Pending disclaimer pop up is still open after clicking on cancel button.");
						} else {
							appLog.info("cancel button is working");
						}
					} else {
						appLog.error("Cancel button cannot be clicked, So cannot check cancel button fnctionality.");
						sa.assertTrue(false,"Cancel button cannot be clicked, So cannot check cancel button fnctionality.");
					}
					refresh(driver);
					if(click(driver, bp.getPendingDisclaimerPopGoToDisclaimerButton(60), "Pending Disclaimer Pop Up", action.BOOLEAN)){
						ThreadSleep(2000);
						String parentID = switchOnWindow(driver);
						if(parentID != null){
							if(disPage.verifyDisclaimerPage(Org3FirmName, M4F1, M4DIS1, M4DISDEC1, Org3FirmName, 60)){
								appLog.info("Disclaimer page is verified.");
							} else {
								appLog.error("Disclaimer page is not verifed.");
								sa.assertTrue(false,"Disclaimer page is not verifed.");
							}
						} else {
							appLog.error("New tab is not opening after clicking on Go to Disclaimer button on all firm page.");
							sa.assertTrue(false,"New tab is not opening after clicking on Go to Disclaimer button on all firm page.");
						}
						driver.close();
						driver.switchTo().window(parentID);
					} else {
						appLog.error("Go to disclaimer button cannot be clicked, So cannot check the functionality.");
						sa.assertTrue(false,"Go to disclaimer button cannot be clicked, So cannot check the functionality.");
					}
				} else {
					appLog.error("Pending disclaimer pop up is not open.");
					sa.assertTrue(false,"Pending disclaimer pop up is not open.");
				}
			} else {
				appLog.error("All firm is not present in the list.");
				sa.assertTrue(false,"All firm is not present in the list.");
			}
		} else {
			appLog.error("Registration and login is failed, So cannot continue wih the testcase.");
			sa.assertTrue(false,"Registration and login is failed, So cannot continue wih the testcase.");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc013_VerifyPendingDisclaimerPopAtFirmPageAllDocument(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M4C2Email, adminPassword);
		if(click(driver, ifp.getPendingDisclaimerPopUpCancelBUtton(60), "Pending Disclaimer Cancel Button", action.BOOLEAN)){
			appLog.info("clicked on pending disclaimer close button.");
		} else {
			appLog.error("Pending disclaimer Cancel button is not present after loging in.");
			sa.assertTrue(false,"Pending disclaimer Cancel button is not present after loging in.");
		}
		String documentNameStandardFolder = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, "M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn", excelLabel.UploadedFileStandard);
		String documentNameCommonFolder = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, "M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn", excelLabel.UploadedFileCommon);
		String documentNameSharedFolder = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, "M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn", excelLabel.UploadedFileShared);
		if(!documentNameStandardFolder.equalsIgnoreCase(excelLabel.UploadedFileStandard.toString())){
			for(int i = 0; i < documentNameStandardFolder.split("<break>").length; i++){
				if(ifp.clickOnDocument(documentNameStandardFolder.split("<break>")[i], 30)){
					String parentID = switchOnWindow(driver);
					if(parentID!=null){
						if(ifp.verifyAccessDeniedPopUp(null, null, 60)){
							appLog.info("Access Denied pop up is verified.");
						} else {
							appLog.error("Access denied pop up is not verifed.");
							sa.assertTrue(false,"Access denied pop up is not verifed.");
						}
					} else {
						appLog.error("No new window is opening after clicking on the document name link of '"+documentNameStandardFolder.split("<break>")[i]+"'.");
						sa.assertTrue(false,"No new window is opening after clicking on the document name link of '"+documentNameStandardFolder.split("<break>")[i]+"'.");
						continue;
					}
					driver.close();
					driver.switchTo().window(parentID);
				} else {
					appLog.error(documentNameStandardFolder.split("<break>")[i]+" document is not present in the grid, So cannot check the functionality.");
					sa.assertTrue(false,documentNameStandardFolder.split("<break>")[i]+" document is not present in the grid, So cannot check the functionality.");
				}
			}
			
		} else {
			appLog.error("There is no file in the standard folder uploaded file column of testcase: M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn, So cannot access the file. If you want to run this testcase, kinldy put value in the column.");
			sa.assertTrue(false,"There is no file in the standard folder uploaded file column of testcase: M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn, So cannot access the file. If you want to run this testcase, kinldy put value in the column.");
		}
		
		if(!documentNameSharedFolder.equalsIgnoreCase(excelLabel.UploadedFileShared.toString())){
			for(int i = 0; i < documentNameSharedFolder.split("<break>").length; i++){
				if(ifp.clickOnDocument(documentNameSharedFolder.split("<break>")[i], 30)){
					String parentID = switchOnWindow(driver);
					if(parentID!=null){
						if(ifp.verifyAccessDeniedPopUp(null, null, 60)){
							appLog.info("Access Denied pop up is verified.");
						} else {
							appLog.error("Access denied pop up is not verifed.");
							sa.assertTrue(false,"Access denied pop up is not verifed.");
						}
					} else {
						appLog.error("No new window is opening after clicking on the document name link of '"+documentNameSharedFolder.split("<break>")[i]+"'.");
						sa.assertTrue(false,"No new window is opening after clicking on the document name link of '"+documentNameSharedFolder.split("<break>")[i]+"'.");
						continue;
					}
					driver.close();
					driver.switchTo().window(parentID);
				} else {
					appLog.error(documentNameSharedFolder.split("<break>")[i]+" document is not present in the grid, So cannot check the functionality.");
					sa.assertTrue(false,documentNameSharedFolder.split("<break>")[i]+" document is not present in the grid, So cannot check the functionality.");
				}
			}
			
		} else {
			appLog.error("There is no file in the shared folder uploaded file column of testcase: M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn, So cannot access the file. If you want to run this testcase, kinldy put value in the column.");
			sa.assertTrue(false,"There is no file in the shared folder uploaded file column of testcase: M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn, So cannot access the file. If you want to run this testcase, kinldy put value in the column.");
		}
		DisclaimerPageBussinessLayer dis = new DisclaimerPageBussinessLayer(driver);
		if(!documentNameCommonFolder.equalsIgnoreCase(excelLabel.UploadedFileCommon.toString())){
			for(int i = 0; i < documentNameCommonFolder.split("<break>").length; i++){
				if(ifp.clickOnDocument(documentNameCommonFolder.split("<break>")[i], 30)){
					String parentID = switchOnWindow(driver);
					if(parentID!=null){
						if(ifp.verifyAccessDeniedPopUp("Yes", null, 60)){
							appLog.info("Access Denied pop up is verified.");
							if(dis.verifyDisclaimerPage(Org3FirmName, M4F1, M4DIS1, M4DISDEC1, Org3FirmName, 60)){
								appLog.info("Diclaimer page is verified.");
							} else {
								appLog.error("Disclaimer page is not verified.");
								sa.assertTrue(false,"Disclaimer page is not verified.");
							}
						} else {
							appLog.error("Access denied pop up is not verifed.");
							sa.assertTrue(false,"Access denied pop up is not verifed.");
						}
					} else {
						appLog.error("No new window is opening after clicking on the document name link of '"+documentNameCommonFolder.split("<break>")[i]+"'.");
						sa.assertTrue(false,"No new window is opening after clicking on the document name link of '"+documentNameCommonFolder.split("<break>")[i]+"'.");
						continue;
					}
					driver.close();
					driver.switchTo().window(parentID);
				} else {
					appLog.error(documentNameCommonFolder.split("<break>")[i]+" document is not present in the grid, So cannot check the functionality.");
					sa.assertTrue(false,documentNameCommonFolder.split("<break>")[i]+" document is not present in the grid, So cannot check the functionality.");
				}
			}
		} else {
			appLog.error("There is no file in the common folder uploaded file column of testcase: M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn, So cannot access the file. If you want to run this testcase, kinldy put value in the column.");
			sa.assertTrue(false,"There is no file in the common folder uploaded file column of testcase: M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn, So cannot access the file. If you want to run this testcase, kinldy put value in the column.");
		}
		
		lp.investorLogout();
		sa.assertAll();
	}
		
	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc014_VerifyPendingDisclaimerPopAtFirmPageRecentActivities(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M4C2Email, adminPassword);
		String documentNameStandardFolder = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, "M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn", excelLabel.UploadedFileStandard);
		String documentNameCommonFolder = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, "M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn", excelLabel.UploadedFileCommon);
		String documentNameSharedFolder = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, "M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn", excelLabel.UploadedFileShared);
		if(click(driver, ifp.getPendingDisclaimerPopUpCancelBUtton(60), "Pending Disclaimer Cancel Button", action.BOOLEAN)){
			appLog.info("clicked on pending disclaimer close button.");
		} else {
			appLog.error("Pending disclaimer Cancel button is not present after loging in.");
			sa.assertTrue(false,"Pending disclaimer Cancel button is not present after loging in.");
		}
		if(click(driver, ifp.getRecentActivitiesTab(60), "Recent Activities Tab", action.BOOLEAN)){
			if(!documentNameStandardFolder.equalsIgnoreCase(excelLabel.UploadedFileStandard.toString())){
				for(int i = 0; i < documentNameStandardFolder.split("<break>").length; i++){
					if(ifp.clickOnDocument(documentNameStandardFolder.split("<break>")[i], 30)){
						String parentID = switchOnWindow(driver);
						if(parentID!=null){
							if(ifp.verifyAccessDeniedPopUp(null, null, 60)){
								appLog.info("Access Denied pop up is verified.");
							} else {
								appLog.error("Access denied pop up is not verifed.");
								sa.assertTrue(false,"Access denied pop up is not verifed.");
							}
						} else {
							appLog.error("No new window is opening after clicking on the document name link of '"+documentNameStandardFolder.split("<break>")[i]+"'.");
							sa.assertTrue(false,"No new window is opening after clicking on the document name link of '"+documentNameStandardFolder.split("<break>")[i]+"'.");
							continue;
						}
						driver.close();
						driver.switchTo().window(parentID);
					} else {
						appLog.error(documentNameStandardFolder.split("<break>")[i]+" document is not present in the grid, So cannot check the functionality.");
						sa.assertTrue(false,documentNameStandardFolder.split("<break>")[i]+" document is not present in the grid, So cannot check the functionality.");
					}
				}
				
			} else {
				appLog.error("There is no file in the standard folder uploaded file column of testcase: M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn, So cannot access the file. If you want to run this testcase, kinldy put value in the column.");
				sa.assertTrue(false,"There is no file in the standard folder uploaded file column of testcase: M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn, So cannot access the file. If you want to run this testcase, kinldy put value in the column.");
			}
			
			if(!documentNameSharedFolder.equalsIgnoreCase(excelLabel.UploadedFileShared.toString())){
				for(int i = 0; i < documentNameSharedFolder.split("<break>").length; i++){
					if(ifp.clickOnDocument(documentNameSharedFolder.split("<break>")[i], 30)){
						String parentID = switchOnWindow(driver);
						if(parentID!=null){
							if(ifp.verifyAccessDeniedPopUp(null, null, 60)){
								appLog.info("Access Denied pop up is verified.");
							} else {
								appLog.error("Access denied pop up is not verifed.");
								sa.assertTrue(false,"Access denied pop up is not verifed.");
							}
						} else {
							appLog.error("No new window is opening after clicking on the document name link of '"+documentNameSharedFolder.split("<break>")[i]+"'.");
							sa.assertTrue(false,"No new window is opening after clicking on the document name link of '"+documentNameSharedFolder.split("<break>")[i]+"'.");
							continue;
						}
						driver.close();
						driver.switchTo().window(parentID);
					} else {
						appLog.error(documentNameSharedFolder.split("<break>")[i]+" document is not present in the grid, So cannot check the functionality.");
						sa.assertTrue(false,documentNameSharedFolder.split("<break>")[i]+" document is not present in the grid, So cannot check the functionality.");
					}
				}
				
			} else {
				appLog.error("There is no file in the shared folder uploaded file column of testcase: M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn, So cannot access the file. If you want to run this testcase, kinldy put value in the column.");
				sa.assertTrue(false,"There is no file in the shared folder uploaded file column of testcase: M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn, So cannot access the file. If you want to run this testcase, kinldy put value in the column.");
			}
			DisclaimerPageBussinessLayer dis = new DisclaimerPageBussinessLayer(driver);
			if(!documentNameCommonFolder.equalsIgnoreCase(excelLabel.UploadedFileCommon.toString())){
				for(int i = 0; i < documentNameCommonFolder.split("<break>").length; i++){
					if(ifp.clickOnDocument(documentNameCommonFolder.split("<break>")[i], 30)){
						String parentID = switchOnWindow(driver);
						if(parentID!=null){
							if(ifp.verifyAccessDeniedPopUp("Yes", null, 60)){
								appLog.info("Access Denied pop up is verified.");
								if(dis.verifyDisclaimerPage(Org3FirmName, M4F1, M4DIS1, M4DISDEC1, Org3FirmName, 60)){
									appLog.info("Diclaimer page is verified.");
								} else {
									appLog.error("Disclaimer page is not verified.");
									sa.assertTrue(false,"Disclaimer page is not verified.");
								}
							} else {
								appLog.error("Access denied pop up is not verifed.");
								sa.assertTrue(false,"Access denied pop up is not verifed.");
							}
						} else {
							appLog.error("No new window is opening after clicking on the document name link of '"+documentNameCommonFolder.split("<break>")[i]+"'.");
							sa.assertTrue(false,"No new window is opening after clicking on the document name link of '"+documentNameCommonFolder.split("<break>")[i]+"'.");
							continue;
						}
						driver.close();
						driver.switchTo().window(parentID);
					} else {
						appLog.error(documentNameCommonFolder.split("<break>")[i]+" document is not present in the grid, So cannot check the functionality.");
						sa.assertTrue(false,documentNameCommonFolder.split("<break>")[i]+" document is not present in the grid, So cannot check the functionality.");
					}
				}
			} else {
				appLog.error("There is no file in the common folder uploaded file column of testcase: M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn, So cannot access the file. If you want to run this testcase, kinldy put value in the column.");
				sa.assertTrue(false,"There is no file in the common folder uploaded file column of testcase: M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn, So cannot access the file. If you want to run this testcase, kinldy put value in the column.");
			}
		} else {
			appLog.error("Recent activities tab cannot be clicked, So cannot access the files from recent activities page and canot check access deneid pop up.");
			sa.assertTrue(false,"Recent activities tab cannot be clicked, So cannot access the files from recent activities page and canot check access deneid pop up.");
		}
		
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc015_VerifyPendingDisclaimerPopUpOnFirmPage(){
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer afp = new AllFirmsPageBusinesslayer(driver);
		DisclaimerPageBussinessLayer disPage = new DisclaimerPageBussinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M4C2Email, adminPassword);
		String text = getSelectedOptionOfDropDown(driver, bp.getFirmNameDropdown(60), "Firm selection drop down", "text");
		if(text.equalsIgnoreCase(Org3FirmName)){
			appLog.info(Org3FirmName+" is selected");
		} else {
			appLog.error(Org3FirmName+" Firm is not selected.");
			sa.assertTrue(false,Org3FirmName+" Firm is not selected.");
		}
		if(bp.getPendingDisclaimerPopUpHeader(60)!=null){
			appLog.info("Pending disclaimer pop up is open.");
			text = trim(getText(driver, bp.getPendingDisclaimerPopUpHeader(60), "Header", action.BOOLEAN));
			if(text.equalsIgnoreCase("Pending Disclaimer(s)")){
				appLog.info("Pending disclaimer pop up header is verified");
			} else {
				appLog.error("Pending disclaimer pop up header text is not verified.Expected: Pending Disclaimer(s)\tActual: "+text);
				sa.assertTrue(false,"Pending disclaimer pop up header text is not verified.Expected: Pending Disclaimer(s)\tActual: "+text);
			}
		} else {
			appLog.error("Pending disclaimer pop up is not open.");
			sa.assertTrue(false,"Pending disclaimer pop up is not open.");
		}
		if(bp.verifyPendingDisclaimerPopUpMessage(60)){
			appLog.info("Pending disclaimer pop up message is verified.");
		}
		if(bp.getPendingDisclaimerPopUpCrossIcon(60)!=null){
			appLog.info("Pending disclaimer pop up cross icon is present.");
		} else {
			appLog.error("Pending disclaimer pop up cross icon is not present.");
			sa.assertTrue(false,"Pending disclaimer pop up cross icon is not present.");
		}
		if(bp.getPendingDisclaimerPopGoToDisclaimerButton(60)!=null){
			appLog.info("Pending disclaimer pop up Go to disclaimer button is present.");
		} else {
			appLog.error("Pending disclaimer pop up Go to disclaimer button is not present.");
			sa.assertTrue(false,"Pending disclaimer pop up Go to disclaimer button is not present.");
		}
		if(bp.getPendingDisclaimerPopUpCancelBUtton(60)!=null){
			appLog.info("Pending disclaimer pop up Cancel button is present.");
		} else {
			appLog.error("Pending disclaimer pop up Cancel button is not present.");
			sa.assertTrue(false,"Pending disclaimer pop up Cancel button is not present.");
		}

		if(isEnabled(driver, bp.getFirmNameDropdown(60), "Firm Name drop down")){
			appLog.info("Drop down is enabled.");
		} else {
			appLog.error("Drop down is not enabled");
			sa.assertTrue(false,"Drop down is not enabled");
		}

		if(isEnabled(driver, bp.getProfileLink(60), "Profile link")){
			appLog.info("Profile link is enabled.");
		} else {
			appLog.error("Profile link is not enabled");
			sa.assertTrue(false,"Profile link is not enabled");
		}

		if(isEnabled(driver, lp.getInvestorLogout(), "Logout link")){
			appLog.info("Logout link is enabled.");
		} else {
			appLog.error("Logout link is not enabled");
			sa.assertTrue(false,"Logout link is not enabled");
		}

		refresh(driver);
		if(bp.getPendingDisclaimerPopUpHeader(60)!=null){
			appLog.info("Pending disclaimer pop up is open.");
			text = trim(getText(driver, bp.getPendingDisclaimerPopUpHeader(60), "Header", action.BOOLEAN));
			if(text.equalsIgnoreCase("Pending Disclaimer(s)")){
				appLog.info("Pending disclaimer pop up header is verified");
			} else {
				appLog.error("Pending disclaimer pop up header text is not verified.Expected: Pending Disclaimer(s)\tActual: "+text);
				sa.assertTrue(false,"Pending disclaimer pop up header text is not verified.Expected: Pending Disclaimer(s)\tActual: "+text);
			}
			if(bp.getPendingDisclaimerPopUpCrossIcon(60)!=null){
				appLog.info("Pending disclaimer pop up cross icon is present.");
			} else {
				appLog.error("Pending disclaimer pop up cross icon is not present.");
				sa.assertTrue(false,"Pending disclaimer pop up cross icon is not present.");
			}
			if(bp.getPendingDisclaimerPopGoToDisclaimerButton(60)!=null){
				appLog.info("Pending disclaimer pop up Go to disclaimer button is present.");
			} else {
				appLog.error("Pending disclaimer pop up Go to disclaimer button is not present.");
				sa.assertTrue(false,"Pending disclaimer pop up Go to disclaimer button is not present.");
			}
			if(bp.getPendingDisclaimerPopUpCancelBUtton(60)!=null){
				appLog.info("Pending disclaimer pop up Cancel button is present.");
			} else {
				appLog.error("Pending disclaimer pop up Cancel button is not present.");
				sa.assertTrue(false,"Pending disclaimer pop up Cancel button is not present.");
			}

			if(isEnabled(driver, bp.getFirmNameDropdown(60), "Firm Name drop down")){
				appLog.info("Drop down is enabled.");
			} else {
				appLog.error("Drop down is not enabled");
				sa.assertTrue(false,"Drop down is not enabled");
			}

			if(isEnabled(driver, bp.getProfileLink(60), "Profile link")){
				appLog.info("Profile link is enabled.");
			} else {
				appLog.error("Profile link is not enabled");
				sa.assertTrue(false,"Profile link is not enabled");
			}

			if(isEnabled(driver, lp.getInvestorLogout(), "Logout link")){
				appLog.info("Logout link is enabled.");
			} else {
				appLog.error("Logout link is not enabled");
				sa.assertTrue(false,"Logout link is not enabled");
			}

			if(click(driver, bp.getPendingDisclaimerPopUpCrossIcon(60), "Cross icon", action.BOOLEAN)){
				ThreadSleep(1500);
				if(bp.getPendingDisclaimerPopUpHeader(5)!=null){
					appLog.error("Pending disclaimer pop up is still open after clicking on cross icon.");
					sa.assertTrue(false,"Pending disclaimer pop up is still open  after clicking on cross icon.");
				} else {
					appLog.info("Cross icon is working");
				}
			} else {
				appLog.error("Cross icon cannot be clicked, So cannot check cross icon fnctionality.");
				sa.assertTrue(false,"Cross icon cannot be clicked, So cannot check cross icon fnctionality.");
			}
			refresh(driver);
			if(click(driver, bp.getPendingDisclaimerPopUpCancelBUtton(60), "cancel button", action.BOOLEAN)){
				ThreadSleep(1500);
				if(bp.getPendingDisclaimerPopUpHeader(5)!=null){
					appLog.error("Pending disclaimer pop up is still open after clicking on cancel button.");
					sa.assertTrue(false,"Pending disclaimer pop up is still open after clicking on cancel button.");
				} else {
					appLog.info("cancel button is working");
				}
				
				if(click(driver, ifp.getRecentActivitiesTab(60), "Recent Activities Tab", action.BOOLEAN)){
					appLog.info("Clicked on Recent Activities Tab" );		
					ThreadSleep(1500);
					if(bp.getPendingDisclaimerPopUpHeader(5)!=null){
						appLog.error("Pending disclaimer pop up is present after switch to Recent Activity Tab.");
						sa.assertTrue(false,"Pending disclaimer pop up is present after switch to Recent Activity Tab.");
					} else {
						appLog.info("Pending disclaimer pop up is not present after switch to Recent Activity Tab.");
					}
					
					if(click(driver, ifp.getAllDocumentsTab(60), "All Documents Tab", action.BOOLEAN)){
						appLog.info("Clicked on All Documents Tab." );		
					}else{
						appLog.error("Not Able to Click on All Documents Tab.");
						sa.assertTrue(false,"Not Able to Click on All Documents Tab.");
					}
					
				}else{
					appLog.error("Not Able to Click on Recent Activity Tab.");
					sa.assertTrue(false,"Not Able to Click on Recent Activity Tab.");
				}
				
			} else {
				appLog.error("Cancel button cannot be clicked, So cannot check cancel button fnctionality.");
				sa.assertTrue(false,"Cancel button cannot be clicked, So cannot check cancel button fnctionality.");
			}
			refresh(driver);
			if(click(driver, bp.getPendingDisclaimerPopGoToDisclaimerButton(60), "Pending Disclaimer Pop Up", action.BOOLEAN)){
				ThreadSleep(2000);
				String parentID = switchOnWindow(driver);
				if(parentID != null){
					if(disPage.verifyDisclaimerPage(Org3FirmName, M4F1, M4DIS1, M4DISDEC1, Org3FirmName, 60)){
						appLog.info("Disclaimer page is verified.");
					} else {
						appLog.error("Disclaimer page is not verifed.");
						sa.assertTrue(false,"Disclaimer page is not verifed.");
					}
				} else {
					appLog.error("New tab is not opening after clicking on Go to Disclaimer button on all firm page.");
					sa.assertTrue(false,"New tab is not opening after clicking on Go to Disclaimer button on all firm page.");
				}
				driver.close();
				driver.switchTo().window(parentID);
			} else {
				appLog.error("Go to disclaimer button cannot be clicked, So cannot check the functionality.");
				sa.assertTrue(false,"Go to disclaimer button cannot be clicked, So cannot check the functionality.");
			}
		} else {
			appLog.error("Pending disclaimer pop up is not open.");
			sa.assertTrue(false,"Pending disclaimer pop up is not open.");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc016_VerifyPendingDisclaimerPopAtAllFirm(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		lp.investorLogin(M4C2Email, adminPassword);
		String text;
		String documentNameStandardFolder = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, "M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn", excelLabel.UploadedFileStandard);
		String documentNameCommonFolder = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, "M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn", excelLabel.UploadedFileCommon);
		String documentNameSharedFolder = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, "M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn", excelLabel.UploadedFileShared);
		if (selectVisibleTextFromDropDown(driver, ifp.getFirmNameDropdown(60), "firm Name Drop Down", "All Firms")){
			

			if(click(driver, ifp.getNavatarInvestorLogo(60), "Navatar Logo", action.BOOLEAN)){
				appLog.info("clicked on Navatar Logo.");
				
				if(bp.getPendingDisclaimerPopUpHeader(60)!=null){
					appLog.info("Pending disclaimer pop up is open.");
					text = trim(getText(driver, bp.getPendingDisclaimerPopUpHeader(60), "Header", action.BOOLEAN));
					if(text.equalsIgnoreCase("Pending Disclaimer(s)")){
						appLog.info("Pending disclaimer pop up header is verified");
					} else {
						appLog.error("Pending disclaimer pop up header text is not verified.Expected: Pending Disclaimer(s)\tActual: "+text);
						sa.assertTrue(false,"Pending disclaimer pop up header text is not verified.Expected: Pending Disclaimer(s)\tActual: "+text);
					}
				} else {
					appLog.error("Pending disclaimer pop up is not open.");
					sa.assertTrue(false,"Pending disclaimer pop up is not open.");
				}
				if(bp.verifyPendingDisclaimerPopUpMessage(60)){
					appLog.info("Pending disclaimer pop up message is verified.");
				}
				if(bp.getPendingDisclaimerPopUpCrossIcon(60)!=null){
					appLog.info("Pending disclaimer pop up cross icon is present.");
				} else {
					appLog.error("Pending disclaimer pop up cross icon is not present.");
					sa.assertTrue(false,"Pending disclaimer pop up cross icon is not present.");
				}
				if(bp.getPendingDisclaimerPopGoToDisclaimerButton(60)!=null){
					appLog.info("Pending disclaimer pop up Go to disclaimer button is present.");
				} else {
					appLog.error("Pending disclaimer pop up Go to disclaimer button is not present.");
					sa.assertTrue(false,"Pending disclaimer pop up Go to disclaimer button is not present.");
				}
				if(bp.getPendingDisclaimerPopUpCancelBUtton(60)!=null){
					appLog.info("Pending disclaimer pop up Cancel button is present.");
				} else {
					appLog.error("Pending disclaimer pop up Cancel button is not present.");
					sa.assertTrue(false,"Pending disclaimer pop up Cancel button is not present.");
				}

				if(isEnabled(driver, bp.getFirmNameDropdown(60), "Firm Name drop down")){
					appLog.info("Drop down is enabled.");
				} else {
					appLog.error("Drop down is not enabled");
					sa.assertTrue(false,"Drop down is not enabled");
				}

				if(isEnabled(driver, bp.getProfileLink(60), "Profile link")){
					appLog.info("Profile link is enabled.");
				} else {
					appLog.error("Profile link is not enabled");
					sa.assertTrue(false,"Profile link is not enabled");
				}

				if(isEnabled(driver, lp.getInvestorLogout(), "Logout link")){
					appLog.info("Logout link is enabled.");
				} else {
					appLog.error("Logout link is not enabled");
					sa.assertTrue(false,"Logout link is not enabled");
				}
				
			} else {
				appLog.error("Not Able to click on Navatar Logo.");
				sa.assertTrue(false,"Not Able to click on Navatar Logo.");
			}
			
			
			if(click(driver, ifp.getPendingDisclaimerPopUpCancelBUtton(60), "Pending Disclaimer Cancel Button", action.BOOLEAN)){
				appLog.info("clicked on pending disclaimer close button.");
			} else {
				appLog.error("Pending disclaimer Cancel button is not present after loging in.");
				sa.assertTrue(false,"Pending disclaimer Cancel button is not present after loging in.");
			}
			if(!documentNameStandardFolder.equalsIgnoreCase(excelLabel.UploadedFileStandard.toString())){
				for(int i = 0; i < documentNameStandardFolder.split("<break>").length; i++){
					if(ifp.clickOnDocument(documentNameStandardFolder.split("<break>")[i], 30)){
						String parentID = switchOnWindow(driver);
						if(parentID!=null){
							if(ifp.verifyAccessDeniedPopUp(null, null, 60)){
								appLog.info("Access Denied pop up is verified.");
							} else {
								appLog.error("Access denied pop up is not verifed.");
								sa.assertTrue(false,"Access denied pop up is not verifed.");
							}
						} else {
							appLog.error("No new window is opening after clicking on the document name link of '"+documentNameStandardFolder.split("<break>")[i]+"'.");
							sa.assertTrue(false,"No new window is opening after clicking on the document name link of '"+documentNameStandardFolder.split("<break>")[i]+"'.");
							continue;
						}
						driver.close();
						driver.switchTo().window(parentID);
					} else {
						appLog.error(documentNameStandardFolder.split("<break>")[i]+" document is not present in the grid, So cannot check the functionality.");
						sa.assertTrue(false,documentNameStandardFolder.split("<break>")[i]+" document is not present in the grid, So cannot check the functionality.");
					}
				}

			} else {
				appLog.error("There is no file in the standard folder uploaded file column of testcase: M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn, So cannot access the file. If you want to run this testcase, kinldy put value in the column.");
				sa.assertTrue(false,"There is no file in the standard folder uploaded file column of testcase: M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn, So cannot access the file. If you want to run this testcase, kinldy put value in the column.");
			}

			if(!documentNameSharedFolder.equalsIgnoreCase(excelLabel.UploadedFileShared.toString())){
				for(int i = 0; i < documentNameSharedFolder.split("<break>").length; i++){
					if(ifp.clickOnDocument(documentNameSharedFolder.split("<break>")[i], 30)){
						String parentID = switchOnWindow(driver);
						if(parentID!=null){
							if(ifp.verifyAccessDeniedPopUp(null, null, 60)){
								appLog.info("Access Denied pop up is verified.");
							} else {
								appLog.error("Access denied pop up is not verifed.");
								sa.assertTrue(false,"Access denied pop up is not verifed.");
							}
						} else {
							appLog.error("No new window is opening after clicking on the document name link of '"+documentNameSharedFolder.split("<break>")[i]+"'.");
							sa.assertTrue(false,"No new window is opening after clicking on the document name link of '"+documentNameSharedFolder.split("<break>")[i]+"'.");
							continue;
						}
						driver.close();
						driver.switchTo().window(parentID);
					} else {
						appLog.error(documentNameSharedFolder.split("<break>")[i]+" document is not present in the grid, So cannot check the functionality.");
						sa.assertTrue(false,documentNameSharedFolder.split("<break>")[i]+" document is not present in the grid, So cannot check the functionality.");
					}
				}

			} else {
				appLog.error("There is no file in the shared folder uploaded file column of testcase: M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn, So cannot access the file. If you want to run this testcase, kinldy put value in the column.");
				sa.assertTrue(false,"There is no file in the shared folder uploaded file column of testcase: M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn, So cannot access the file. If you want to run this testcase, kinldy put value in the column.");
			}
			DisclaimerPageBussinessLayer dis = new DisclaimerPageBussinessLayer(driver);
			if(!documentNameCommonFolder.equalsIgnoreCase(excelLabel.UploadedFileCommon.toString())){
				for(int i = 0; i < documentNameCommonFolder.split("<break>").length; i++){
					if(ifp.clickOnDocument(documentNameCommonFolder.split("<break>")[i], 30)){
						String parentID = switchOnWindow(driver);
						if(parentID!=null){
							if(ifp.verifyAccessDeniedPopUp("Yes", null, 60)){
								appLog.info("Access Denied pop up is verified.");
								if(dis.verifyDisclaimerPage(Org3FirmName, M4F1, M4DIS1, M4DISDEC1, Org3FirmName, 60)){
									appLog.info("Diclaimer page is verified.");
								} else {
									appLog.error("Disclaimer page is not verified.");
									sa.assertTrue(false,"Disclaimer page is not verified.");
								}
							} else {
								appLog.error("Access denied pop up is not verifed.");
								sa.assertTrue(false,"Access denied pop up is not verifed.");
							}
						} else {
							appLog.error("No new window is opening after clicking on the document name link of '"+documentNameCommonFolder.split("<break>")[i]+"'.");
							sa.assertTrue(false,"No new window is opening after clicking on the document name link of '"+documentNameCommonFolder.split("<break>")[i]+"'.");
							continue;
						}
						driver.close();
						driver.switchTo().window(parentID);
					} else {
						appLog.error(documentNameCommonFolder.split("<break>")[i]+" document is not present in the grid, So cannot check the functionality.");
						sa.assertTrue(false,documentNameCommonFolder.split("<break>")[i]+" document is not present in the grid, So cannot check the functionality.");
					}
				}
			} else {
				appLog.error("There is no file in the common folder uploaded file column of testcase: M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn, So cannot access the file. If you want to run this testcase, kinldy put value in the column.");
				sa.assertTrue(false,"There is no file in the common folder uploaded file column of testcase: M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn, So cannot access the file. If you want to run this testcase, kinldy put value in the column.");
			}
		} else {
			appLog.error("All Firm option is not available in the drop down, So cannot check the access denied pop up on All Firm Page.");
			sa.assertTrue(false,"All Firm option is not available in the drop down, So cannot check the access denied pop up on All Firm Page.");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc017_VerifyPendingDisclaimerPopAtPotentialInvestmentWorkspace(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		DisclaimerPageBussinessLayer dis = new DisclaimerPageBussinessLayer(driver);
		lp.investorLogin(M4C2Email, adminPassword);
		String documentNameStandardFolder = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, "M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn", excelLabel.UploadedFileStandard);
		String folderPathStandardFolder = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, "M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn", excelLabel.StandardPath);
		String documentNameCommonFolder = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, "M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn", excelLabel.UploadedFileCommon);
		String folderPathCommonFolder = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, "M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn", excelLabel.CommonPath);
		String documentNameSharedFolder = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, "M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn", excelLabel.UploadedFileShared);
		String folderPathSharedFolder = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, "M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn", excelLabel.SharedPath);
		if(click(driver, ifp.getPendingDisclaimerPopUpCancelBUtton(60), "Pending Disclaimer Cancel Button", action.BOOLEAN)){
			appLog.info("clicked on pending disclaimer close button.");
		} else {
			appLog.error("Pending disclaimer Cancel button is not present after loging in.");
			sa.assertTrue(false,"Pending disclaimer Cancel button is not present after loging in.");
		}
		if(ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)){
			for(int i = 0; i < folderPathStandardFolder.split(",").length; i++){
				if(fp.verifyFolderPathdummy(folderPathStandardFolder.split(",")[i], M4I1, null, null, PageName.PotentialInvestmentPage, null, 60)){
					if(!documentNameStandardFolder.equalsIgnoreCase(excelLabel.UploadedFileStandard.toString())){
						if(ifp.clickOnDocument(documentNameStandardFolder.split("<break>")[i], 30)){
							String parentID = switchOnWindow(driver);
							if(parentID!=null){
								if(ifp.verifyAccessDeniedPopUp(null, null, 60)){
									appLog.info("Access Denied pop up is verified.");
								} else {
									appLog.error("Access denied pop up is not verifed.");
									sa.assertTrue(false,"Access denied pop up is not verifed.");
								}
							} else {
								appLog.error("No new window is opening after clicking on the document name link of '"+documentNameStandardFolder.split("<break>")[i]+"'.");
								sa.assertTrue(false,"No new window is opening after clicking on the document name link of '"+documentNameStandardFolder.split("<break>")[i]+"'.");
								continue;
							}
							driver.close();
							driver.switchTo().window(parentID);
						} else {
							appLog.error(documentNameStandardFolder.split("<break>")[i]+" document is not present in the grid, So cannot check the functionality.");
							sa.assertTrue(false,documentNameStandardFolder.split("<break>")[i]+" document is not present in the grid, So cannot check the functionality.");
						}
						
					} else {
						appLog.error("There is no file in the standard folder uploaded file column of testcase: M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn, So cannot access the file. If you want to run this testcase, kinldy put value in the column.");
						sa.assertTrue(false,"There is no file in the standard folder uploaded file column of testcase: M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn, So cannot access the file. If you want to run this testcase, kinldy put value in the column.");
					}
					
				} else {
					appLog.error(folderPathStandardFolder.split(",")[i]+" folder path is not verified. So cannot check the file in Standard Folder.");
					sa.assertTrue(false,folderPathStandardFolder.split(",")[i]+" folder path is not verified. So cannot check the file in Standard Folder.");
				}
			}
			
			for(int i = 0; i < folderPathSharedFolder.split(",").length; i++){
				if(fp.verifyFolderPathdummy(folderPathSharedFolder.split(",")[i], null, null, null, PageName.PotentialInvestmentPage, null, 60)){
					if(!documentNameSharedFolder.equalsIgnoreCase(excelLabel.UploadedFileStandard.toString())){
						if(ifp.clickOnDocument(documentNameSharedFolder.split("<break>")[i], 30)){
							String parentID = switchOnWindow(driver);
							if(parentID!=null){
								if(ifp.verifyAccessDeniedPopUp(null, null, 60)){
									appLog.info("Access Denied pop up is verified.");
								} else {
									appLog.error("Access denied pop up is not verifed.");
									sa.assertTrue(false,"Access denied pop up is not verifed.");
								}
							} else {
								appLog.error("No new window is opening after clicking on the document name link of '"+documentNameSharedFolder.split("<break>")[i]+"'.");
								sa.assertTrue(false,"No new window is opening after clicking on the document name link of '"+documentNameSharedFolder.split("<break>")[i]+"'.");
								continue;
							}
							driver.close();
							driver.switchTo().window(parentID);
						} else {
							appLog.error(documentNameSharedFolder.split("<break>")[i]+" document is not present in the grid, So cannot check the functionality.");
							sa.assertTrue(false,documentNameSharedFolder.split("<break>")[i]+" document is not present in the grid, So cannot check the functionality.");
						}
						
					} else {
						appLog.error("There is no file in the standard folder uploaded file column of testcase: M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn, So cannot access the file. If you want to run this testcase, kinldy put value in the column.");
						sa.assertTrue(false,"There is no file in the standard folder uploaded file column of testcase: M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn, So cannot access the file. If you want to run this testcase, kinldy put value in the column.");
					}
					
				} else {
					appLog.error(folderPathSharedFolder.split(",")[i]+" folder path is not verified. So cannot check the file in Standard Folder.");
					sa.assertTrue(false,folderPathSharedFolder.split(",")[i]+" folder path is not verified. So cannot check the file in Standard Folder.");
				}
			}
			
			for(int i = 0; i < folderPathCommonFolder.split(",").length; i++){
				if(fp.verifyFolderPathdummy(folderPathCommonFolder.split(",")[i], null, null, null, PageName.PotentialInvestmentPage, null, 60)){
					if(!documentNameCommonFolder.equalsIgnoreCase(excelLabel.UploadedFileStandard.toString())){
						if(ifp.clickOnDocument(documentNameCommonFolder.split("<break>")[i], 30)){
							String parentID = switchOnWindow(driver);
							if(parentID!=null){
								if(ifp.verifyAccessDeniedPopUp("Yes", null, 60)){
									appLog.info("Access Denied pop up is verified.");
									if(dis.verifyDisclaimerPage(Org3FirmName, M4F1, M4DIS1, M4DISDEC1, Org3FirmName, 60)){
										appLog.info("Diclaimer page is verified.");
									} else {
										appLog.error("Disclaimer page is not verified.");
										sa.assertTrue(false,"Disclaimer page is not verified.");
									}
								} else {
									appLog.error("Access denied pop up is not verifed.");
									sa.assertTrue(false,"Access denied pop up is not verifed.");
								}
							} else {
								appLog.error("No new window is opening after clicking on the document name link of '"+documentNameCommonFolder.split("<break>")[i]+"'.");
								sa.assertTrue(false,"No new window is opening after clicking on the document name link of '"+documentNameCommonFolder.split("<break>")[i]+"'.");
								continue;
							}
							driver.close();
							driver.switchTo().window(parentID);
						} else {
							appLog.error(documentNameCommonFolder.split("<break>")[i]+" document is not present in the grid, So cannot check the functionality.");
							sa.assertTrue(false,documentNameCommonFolder.split("<break>")[i]+" document is not present in the grid, So cannot check the functionality.");
						}
						
					} else {
						appLog.error("There is no file in the standard folder uploaded file column of testcase: M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn, So cannot access the file. If you want to run this testcase, kinldy put value in the column.");
						sa.assertTrue(false,"There is no file in the standard folder uploaded file column of testcase: M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn, So cannot access the file. If you want to run this testcase, kinldy put value in the column.");
					}
					
				} else {
					appLog.error(folderPathCommonFolder.split(",")[i]+" folder path is not verified. So cannot check the file in Standard Folder.");
					sa.assertTrue(false,folderPathCommonFolder.split(",")[i]+" folder path is not verified. So cannot check the file in Standard Folder.");
				}
			}
		} else {
			appLog.error("Potential investment tab cannot be clicked, SO cannot check the Access denied Pop Up on files of content grid.");
			sa.assertTrue(false,"Potential investment tab cannot be clicked, SO cannot check the Access denied Pop Up on files of content grid.");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc018_VerifyPendingDisclaimerPopAtBulkDownload(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		DisclaimerPageBussinessLayer dis = new DisclaimerPageBussinessLayer(driver);
		AllFirmsPageBusinesslayer allFirm = new AllFirmsPageBusinesslayer(driver);
		lp.investorLogin(M4C2Email, adminPassword);
		String documentNameStandardFolder = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, "M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn", excelLabel.UploadedFileStandard);
		String folderPathStandardFolder = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, "M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn", excelLabel.StandardPath);
		String documentNameCommonFolder = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, "M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn", excelLabel.UploadedFileCommon);
		String folderPathCommonFolder = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, "M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn", excelLabel.CommonPath);
		String documentNameSharedFolder = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, "M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn", excelLabel.UploadedFileShared);
		String folderPathSharedFolder = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, "M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn", excelLabel.SharedPath);
		if(click(driver, ifp.getPendingDisclaimerPopUpCancelBUtton(60), "Pending Disclaimer Cancel Button", action.BOOLEAN)){
			appLog.info("clicked on pending disclaimer close button.");
		} else {
			appLog.error("Pending disclaimer Cancel button is not present after loging in.");
			sa.assertTrue(false,"Pending disclaimer Cancel button is not present after loging in.");
		}
		if(ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)){
			if(ifp.clickOnBulkDownload()){
				String parentID = switchOnWindow(driver);
				if(parentID != null ){
					if(ifp.verifyAccessDeniedPopUp("Yes", PageName.BulkDownload, 30)){
							driver.switchTo().window(parentID);
							parentID = switchOnWindow(driver);
							if(parentID!=null){
								if(dis.verifyDisclaimerPage(Org3FirmName, M4F1, M4DIS1, M4DISDEC1, Org3FirmName, 60)){
									appLog.info("Diclaimer page is verified.");
								} else {
									appLog.error("Disclaimer page is not verified.");
									sa.assertTrue(false,"Disclaimer page is not verified.");
								}
								if(dis.clickOnDisclaimerAcceptButton(M4DIS1, true, 30)){
									if(dis.verifyNoPendingDisclaimerErrorMessage(30)){
										appLog.info("No Pending disclimaer message is verified.");
										if(selectVisibleTextFromDropDown(driver, dis.getFirmNameDropdown(60), "Firm Name drop down", Org3FirmName)){
											if(ifp.getPendingDisclaimerPopUpHeader(20)==null){
												appLog.info("pending diclaimer pop up is not visible on firm page after acceptance and is verified.");
											} else {
												appLog.error("Pending disclaimer pop up is present on firm page even after acceptance.");
												sa.assertTrue(false,"Pending disclaimer pop up is present on firm page even after acceptance.");
											}
										} else {
											appLog.error(Org3FirmName+" firm name is not present in the drop down, So cannot check the functionality.");
											sa.assertTrue(false,Org3FirmName+" firm name is not present in the drop down, So cannot check the functionality.");
										}
										String childID = driver.getWindowHandle();
										driver.switchTo().window(parentID);
										driver.close();
										parentID = childID;
										driver.switchTo().window(childID);
										if(selectVisibleTextFromDropDown(driver, dis.getFirmNameDropdown(60), "Firm Name drop down", "All Firms")){
											if(allFirm.getPendingDisclaimerPopUpHeader(20)==null){
												appLog.info("pending diclaimer pop up is not visible on all firms page after acceptance and is verified.");
											} else {
												appLog.error("Pending disclaimer pop up is present on all firms page even after acceptance.");
												sa.assertTrue(false,"Pending disclaimer pop up is present on all firms page even after acceptance.");
											}
											if(allFirm.clickOnDocument(documentNameCommonFolder.split("<break>")[0], 30)){
												parentID = switchOnWindow(driver);
												if(parentID != null){
													if(allFirm.getAccessDeniedPopUpHeader(3)==null){
														appLog.info("Document is accessible and is verified.");
													} else {
														appLog.error("Access denied pop up is present even after accepting the disclaimer.");
														sa.assertTrue(false,"Access denied pop up is present even after accepting the disclaimer.");
													}
													if(allFirm.getDownloadLink(30)!= null){
														appLog.info("Download link is verified.");
													} else {
														appLog.error("Download lnk is not present.");
														sa.assertTrue(false,"Download lnk is not present.");
													}
													driver.close();
													driver.switchTo().window(parentID);
												} else {
													appLog.error("No new window is opening, So cannot check file access from all firm page.");
													sa.assertTrue(false,"No new window is opening, So cannot check file access from all firm page.");
												}
											} else {
												appLog.error(documentNameCommonFolder.split("<break>")[0]+" cannot be clicked, So cannot check the functionality.");
												sa.assertTrue(false,documentNameCommonFolder.split("<break>")[0]+" cannot be clicked, So cannot check the functionality.");
											}
										} else {
											appLog.error("All Firms name is not present in the drop down, So cannot check the functionality.");
											sa.assertTrue(false,"All Firms name is not present in the drop down, So cannot check the functionality.");
										}
									}
									if(selectVisibleTextFromDropDown(driver, allFirm.getFirmNameDropdown(60), "Firm Name drop down", Org3FirmName)){
										if(ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)){
											if(fp.verifyFolderPathdummy(folderPathSharedFolder.split(",")[0], null, null, null, PageName.PotentialInvestmentPage, null, 30)){
												if(allFirm.clickOnDocument(documentNameSharedFolder.split("<break>")[0], 30)){
													parentID = switchOnWindow(driver);
													if(parentID != null){
														if(allFirm.getAccessDeniedPopUpHeader(3)==null){
															appLog.info("Document is accessible and is verified.");
														} else {
															appLog.error("Access denied pop up is present even after accepting the disclaimer.");
															sa.assertTrue(false,"Access denied pop up is present even after accepting the disclaimer.");
														}
														if(allFirm.getDownloadLink(30)!= null){
															appLog.info("Download link is verified.");
														} else {
															appLog.error("Download lnk is not present.");
															sa.assertTrue(false,"Download lnk is not present.");
														}
														driver.close();
														driver.switchTo().window(parentID);
													} else {
														appLog.error("No new window is opening, So cannot check the file access from folder.");
														sa.assertTrue(false,"No new window is opening, So cannot check file access from folder.");
													}
												} else {
													appLog.error(documentNameSharedFolder.split("<break>")[0]+" cannot be clicked, So cannot check the functionality.");
													sa.assertTrue(false,documentNameSharedFolder.split("<break>")[0]+" cannot be clicked, So cannot check the functionality.");
												}
											} else {
												appLog.error(folderPathSharedFolder.split(",")[0]+" folder path is not verified.");
												sa.assertTrue(false,folderPathSharedFolder.split(",")[0]+" folder path is not verified.");
											}
											if(ifp.clickOnBulkDownload()){
												parentID = switchOnWindow(driver);
												if(parentID != null ){
													if(ifp.getAllFoldersOnBulkDownloadWindow(60)!=null){
														appLog.info("Bulk download is accessible and is verified.");
													} else {
														appLog.error("Bulk download is not accessible even after accepting the disclaimer.");
														sa.assertTrue(false,"Bulk download is not accessible even after accepting the disclaimer.");
													}
													driver.close();
													driver.switchTo().window(parentID);
												} else {
													appLog.error("No new window is opening after clicking bulk download icon, So cannot check bulk download access.");
													sa.assertTrue(false,"No new window is opening after clicking bulk download icon, So cannot check bulk download access.");
												}
											} else {
												appLog.error("Bulk Download button cannot be clikced, So cannot check the access functionality.");
												sa.assertTrue(false,"Bulk Download button cannot be clikced, So cannot check the access functionality.");
											}
										} else {
											appLog.error("Potential investment tab cannot be clicked, SO cannot check the Access denied Pop Up on files of content grid.");
											sa.assertTrue(false,"Potential investment tab cannot be clicked, SO cannot check the Access denied Pop Up on files of content grid.");
										}
									} else {
										appLog.error(Org3FirmName+" firm name is not present in the drop down, So cannot check the functionality.");
										sa.assertTrue(false,Org3FirmName+" firm name is not present in the drop down, So cannot check the functionality.");
									}
								}
							} else {
								appLog.error("New window is not opening after clicking on Go to Disclaimer Button.");
								sa.assertTrue(false,"New window is not opening after clicking on Go to Disclaimer Button.");
							}
					} else {
						appLog.error("Access denied pop up is not verified on bulk download window.");
						sa.assertTrue(false,"Access denied pop up is not verified on bulk download window.");
					}
				} else {
					appLog.error("New window is not opening after clicking on bulk upload icon.");
					sa.assertTrue(false,"New window is not opening after clicking on bulk upload icon.");
				}
			} else {
				appLog.error("Bulk Dwnload cannot be clicked, So cannot check the Pending disclaimer pop up on bulk upload.");
				sa.assertTrue(false,"Bulk Dwnload cannot be clicked, So cannot check the Pending disclaimer pop up on bulk upload.");
			}
		
		} else {
			appLog.error("Potential investment tab cannot be clicked, SO cannot check the Access denied Pop Up on files of content grid.");
			sa.assertTrue(false,"Potential investment tab cannot be clicked, SO cannot check the Access denied Pop Up on files of content grid.");
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc018_VerifyPendingDisclaimerPopAtBulkDownloadCheckImpactCRMSide(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if(lp.clickOnTab(TabName.NavatarInvestorAddOns)){
			if(switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60))){
				System.err.println("Successfully switched to frame.");
				switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
			}
			if(selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60), "Fund Select drop down", M4F1)){
				if(niam.clickOnViewLinkOfDisclaimer(M4DIS1)){
					WebElement ele = FindElement(driver, "//span[@id='Disclaimer_Statistics-rows']//span[contains(@id,'Disclaimer_Statistics-cell-')]/a[@title='"+M4CFN2+" "+M4CLN2+"']/../following-sibling::span/a[@title='"+M4C2Email+"']/../following-sibling::span/a[@title='"+M4I2+"']/../following-sibling::span/img[@title='Accepted']/../following-sibling::span/span[@title='"+getSystemDate("MM/dd/yyyy")+"' or @title='"+previousOrForwardDate(-1, "MM/dd/yyyy")+"']", M4CFN2+" "+M4CLN2+" Contact in contact grid", action.BOOLEAN, 30);
					if(ele!=null){
						appLog.info("Contact2 details are verified.");
					} else {
						appLog.error(M4CFN2+" "+M4CLN2+" contact details are not verified.");
						sa.assertTrue(false,M4CFN2+" "+M4CLN2+" contact details are not verified.");
					}
					
					ele = FindElement(driver, "//span[@id='Disclaimer_Statistics-rows']//span[contains(@id,'Disclaimer_Statistics-cell-')]/a[@title='"+M4CFN1+" "+M4CLN1+"']/../following-sibling::span/a[@title='"+M4C1Email+"']/../following-sibling::span/a[@title='"+M4I1+"']/../following-sibling::span/img[@title='Waiting']/../following-sibling::span/span[@title='']", M4CFN1+" "+M4CLN1+" Contact in contact grid", action.BOOLEAN, 30);
					if(ele!=null){
						appLog.info("Contact1 details are verified.");
					} else {
						appLog.error(M4CFN1+" "+M4CLN1+" contact details are not verified.");
						sa.assertTrue(false,M4CFN1+" "+M4CLN1+" contact details are not verified.");
					}
					
					if(niam.getDisclaimerViewPopUpRecordCount()>=2){
						appLog.info("Record coutn is verified.");
					} else {
						appLog.error("record Count is not verified. Expected: >2\tActual: "+niam.getDisclaimerViewPopUpRecordCount());
						sa.assertTrue(false,"record Count is not verified. Expected: >2\tActual: "+niam.getDisclaimerViewPopUpRecordCount());
					}
				} else {
					appLog.error("Cannot click on view link of disclaimer '"+M4DIS1+"', So cannot continue with tc.");
					sa.assertTrue(false,"Cannot click on view link of disclaimer '"+M4DIS1+"', So cannot continue with tc.");
				}
			} else {
				appLog.error(M4F1+" is not present in drop down.");
				sa.assertTrue(false,M4F1+" is not present in drop down.");
			}
		} else {
			appLog.error("Navatar Investor Add ons Tab cannot be clicked, So cannot continue with the tc.");
			sa.assertTrue(false,"Navatar Investor Add ons Tab cannot be clicked, So cannot continue with the tc.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
		
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc019_VerifyAccessDeniedPopUpForFilesUploadedByInvestor(){
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		AllFirmsPageBusinesslayer afp = new AllFirmsPageBusinesslayer(driver);
		DisclaimerPageBussinessLayer disPage = new DisclaimerPageBussinessLayer(driver);
		FundsPageBusinessLayer fpb=new FundsPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		
		boolean flag = false;
		if (ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M4Contact1", excelLabel.Registered).equalsIgnoreCase("Not Registered")) {
			try {
				investorRegLink = new EmailLib().getInvestorRegLink("InvitationMail",gmailUserName,gmailPassword,Org3CRMUser1EmailID,M4C1Email);
				appLog.info("Investor Registration Link: " + investorRegLink);
			} catch (InterruptedException e) {
				appLog.info("Invitation mail not found.");
				e.printStackTrace();
			}
			if (investorRegLink != null) {
				driver.get(investorRegLink);
				if (bp.targetRegistration(M4CFN1, M4CLN1, M4C1Email, M4I1,
						adminPassword)) {
					appLog.info(
							"Investor is registered successfully: " + M4CFN1 + " " + M4CLN1);
					flag = true;
					ExcelUtils.writeData("Registered", "Contacts", excelLabel.Variable_Name, "M4Contact1", excelLabel.Registered);
				} else {
					driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
					appLog.info("Register Target URL through Direct URL..");
					if (bp.targetRegistration(M4CFN1, M4CLN1, M4C1Email,
							M4I1, adminPassword)) {
						appLog.info("Investor is registered successfully: " + M4CFN1 + " "
								+ M4CLN1);
						flag = true;
						ExcelUtils.writeData("Registered", "Contacts", excelLabel.Variable_Name, "M4Contact1", excelLabel.Registered);
					} else {
						appLog.error("Investor is not Registered through Invited Link: " + M4CFN1 + " "
								+ M4CLN1);
						sa.assertTrue(false, "Investor is not Registered through Invited Link: " + M4CFN1
								+ " " + M4CLN1);
					}
				}
			} else {
				driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
				appLog.info("Register Target URL through Direct URL..");
				if (bp.targetRegistration(M4CFN1, M4CLN1, M4C1Email,
						M4I1, adminPassword)) {
					appLog.info("Investor is registered successfully: " + M4CFN1 + " "
							+ M4CLN1);
					flag = true;
					ExcelUtils.writeData("Registered", "Contacts", excelLabel.Variable_Name, "M4Contact1", excelLabel.Registered);
				} else {
					appLog.error("Investor is not Registered through Invited Link: " + M4CFN1 + " "
							+ M4CLN1);
					sa.assertTrue(false, "Investor is not Registered through Invited Link: " + M4CFN1
							+ " " + M4CLN1);
				}
			}		
		}else {
			appLog.info("investor "+M4CFN1+" "+M4CLN1+" is already Registered.");
			sa.assertTrue(false, "investor "+M4CFN1+" "+M4CLN1+" is already Registered.");
			if(lp.investorLogin(M4C1Email, adminPassword)){
				flag = true;
			} else {
				sa.assertTrue(false, "Investor password is wrong so cannot login");
				flag = false;
			}
		}
		if(flag){
			if(click(driver, ifp.getPendingDisclaimerPopUpCancelBUtton(60), "Pending Disclaimer Cancel Button", action.BOOLEAN)){
				appLog.info("clicked on pending disclaimer close button.");
				if(ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment))
				{
					appLog.info("clicked on Potentail Investment Tab.");
					String stdPath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
					String documentName =ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,
							excelLabel.UploadedFileStandard);
					String docPath="UploadFiles\\Module4\\InvestorUpload\\StandardSub";
					if (ifp.uploadUpdateFileInvestorSide(stdPath, documentName, M4I1, null, FolderType.Standard, docPath, null, 30, PageName.PotentialInvestmentPage, null, null, WorkSpaceAction.UPLOAD))
					{
						appLog.info(documentName+"upload successfully and available in"+stdPath);
						if(click(driver, ifp.getRefreshIcon(20), "Refresh Icon", action.SCROLLANDBOOLEAN)){
							appLog.info("clicked on Refresh	Icon");
							if(bp.clickOnDocument(documentName, 60))	
							{
								String parentID = switchOnWindow(driver);
								appLog.info("clicked on document"+"+documentName+");
								if(bp.verifyAccessDeniedPopUp("Yes", PageName.PotentialInvestmentPage,60))
								{
									appLog.info("clicked on Go to Disclaimers Button");
								}else
								{
									appLog.error("Not Able to Click on Go to Disclaimers Button");
									sa.assertTrue(false, "Not Able to Click on Go to Disclaimers Button");	
								}
								driver.close();
								driver.switchTo().window(parentID);
							}
							else{
								appLog.error("Not Able to Click on document '"+documentName+"+'So Access Denied popup is not coming");
								sa.assertTrue(false, "Not Able to Click on document '"+documentName+"+'So Access Denied popup is not coming");
							}
						}
						else{
							appLog.error("Not Able to Click Refresh Icon so cannot click on Upoaded File");
							sa.assertTrue(false, "Not Able to Click Refresh Icon so cannot click on Upoaded File");	
						}
					}
					else{
						appLog.error(documentName+"upload Unsuccessfully and not available in"+stdPath);
						sa.assertTrue(false, documentName+"upload Unsuccessfully and not available in"+stdPath);	
					}
				}
				else {
					appLog.error("Potential investment tab cannot be clicked, SO cannot check the Access denied Pop Up on files of content grid.");
					sa.assertTrue(false,"Potential investment tab cannot be clicked, SO cannot check the Access denied Pop Up on files of content grid.");
				}
			} else {
				appLog.error("Pending disclaimer Cancel button is not clickable after loging in.");
				sa.assertTrue(false,"Pending disclaimer Cancel button is not clickable after loging in.");
			}
		}
		lp.investorLogout();
		sa.assertAll();
}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc020_VerifyDeactivateDisclaimer() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if (lp.clickOnTab(TabName.NavatarInvestorAddOns)) {
			System.out.println("inside NavatarInvestorAddonsTab");

			if (switchToFrame(driver, 60, niam.getNavatarInvestorAddOnFrame(60))) {
				appLog.info("Successfully switch to  Parent Navatar add-ons inverstor disclaimer frame.");
				if (switchToFrame(driver, 60, niam.getNavatarInvestorAddOnFrame1(60))) {
					appLog.info("Successfully switched to Child Navatar add-ons inverstor disclaimer frame.");

					if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60),
							"Select fund drop down on Navatar Investor add-ons page", M4F1)) {
						appLog.info(M4F1 + "value is select from dropdown.");

						appLog.info(
								"*******************  check result when i click on <No button> of Confirm Disclaimer Deactivation Popup **********************");

						if (click(driver, niam.getDeactivateFundButton(60), "Click on Deactivate button",//need to change
								action.BOOLEAN)) {
							appLog.info("Confirm Disclaimer Deactivation Popup is openend.");
							if (niam.getConfirmDisclaimerHeading(60).getText().trim()
									.equals("Confirm Disclaimer Deactivation")) {
								appLog.info("Popup header text is verified which is : '"
										+ niam.getConfirmDisclaimerHeading(60).getText() + "' ");
								if (!niam.getConfirmDisclaimerDeactivationPopupMsg().isEmpty()) {
									int msgSize = niam.getConfirmDisclaimerDeactivationPopupMsg().size();
									StringBuffer sb = new StringBuffer();
									for (int i = 1; i <= msgSize; i++) {
										String txt = driver.findElement(By
												.xpath("//div[contains(text(),'Confirm Disclaimer Deactivation')]/following-sibling::div[@class='formbox contacts_n_name_div']/p["
														+ i + "]"))
												.getText();
										sb.append(txt);
									}
									if (sb.toString().trim().equals(
											"Once deactivated, all investors will NOT be required to accept the disclaimer before accessing documents related to this fund.Are you sure you want to proceed?")) {
										appLog.info("Popup message is verified which is :" + sb);
									} else {
										appLog.error("Popup message  is not verified.");
										sa.assertTrue(false, "Popup message is not verified.");
									}
								} else {
									appLog.error("Popup message list is empty");
									sa.assertTrue(false, "Popup message list is empty.");
								}
								if (niam.getDisclaimerDeactivationCrossBtn(60) != null) {
									appLog.info("Cross button is present on popup and has been verfied.");
								} else {
									appLog.error("Cross button is not present on popup.");
									sa.assertTrue(false, "Cross button is not present on popup.");

								}
								if (niam.getDisclaimerDeactivationNoBtn(60) != null) {
									appLog.info("No button is present on popup and has been verfied.");
								} else {
									appLog.error("No button is not present on popup.");
									sa.assertTrue(false, "No button is not present on popup.");

								}
								if (niam.getDisclaimerDeactivationYesBtn(60) != null) {
									appLog.info("Yes button is present on popup and has been verfied.");
								} else {
									appLog.error("Yes button is not present on popup.");
									sa.assertTrue(false, "Yes button is not present on popup.");

								}
								if (click(driver, niam.getDisclaimerDeactivationNoBtn(60), "click on popup No button",
										action.BOOLEAN)) {
									appLog.info("Confirm Disclaimer Deactivation Popup is closed");

									if (niam.verifyDisclaimerGrid(DisclaimerGrid.Deactivate,
											M4DIS1,
											DisclaimerGrid.View, DisclaimerGrid.LastActivatedOn,
											DisclaimerGrid.CreatedOn)) {
										appLog.info("Disclaimer grid has been verified.");

									} else {
										appLog.error("Disclaimer grid not verified.");
										sa.assertTrue(false, "Disclaimer grid not verified.");
									}

									if (niam.getDisclaimerRecordCount() >= 1) {
										appLog.info("record count is verfied and equals to : "
												+ niam.getDisclaimerRecordCount());
									} else {
										appLog.error("record count is not verfied.");
										sa.assertTrue(false, "record count is not verfied.");
									}
									if (niam.getActiveDisclaimerStatisticsValue()
											.equals(M4DIS1)) {
										appLog.info(
												"Active Disclaimer Statics : '"
														+ M4DIS1
														+ "' is available");
									} else {
										appLog.error("Active Disclaimer Statics value  is not verified.");
										sa.assertTrue(false, "Active Disclaimer Statics value is not verified.");
									}
									System.err.println("Going verify acctive and waiting disclaimer count");
									if (niam.getAcceptedDiscalimerCount() >= 1
											&& niam.getWaitingDisclaimerCount() >= 1) {
										appLog.info("Active Disclaimer Statics status accepted count : "
												+ niam.getAcceptedDiscalimerCount()
												+ " Active Disclaimer Statics status waiting count : "
												+ niam.getWaitingDisclaimerCount() + "has been verified.");
									} else {
										appLog.error(
												"Active Disclaimer Statics status : accepted and waiting count is not verified.");
										sa.assertTrue(false,
												"Active Disclaimer Statics status : accepted and waiting count is not verified.");
									}
								} else {
									appLog.error("Confirm Disclaimer Deactivation Popup is not closed.");
									sa.assertTrue(false, "Confirm Disclaimer Deactivation Popup is not closed.");
								}

							} else {
								appLog.error("Confirm Disclaimer Deactivation Popup header text is not verified.");
								sa.assertTrue(false,
										"Confirm Disclaimer Deactivation Popup header text is not verified.");
							}
						} else {
							appLog.error(
									"Deactivate button is not clickable and Confirm Disclaimer Deactivation Popup is not openend.");
							sa.assertTrue(false,
									"Deactivate button is not clickable and Confirm Disclaimer Deactivation Popup is not openend.");
						}

						appLog.info(
								"*******************  check result when i click on <cross button> of Confirm Disclaimer Deactivation Popup **********************");

						if (click(driver, niam.getDeactivateFundButton(60), "Click on Deactivate button",
								action.BOOLEAN)) {
							appLog.info("Confirm Disclaimer Deactivation Popup is openend.");
							if (niam.getConfirmDisclaimerHeading(60).getText().trim()
									.equals("Confirm Disclaimer Deactivation")) {
								appLog.info("Popup header text is verified which is : '"
										+ niam.getConfirmDisclaimerHeading(60).getText() + "' ");

								if (click(driver, niam.getDisclaimerDeactivationCrossBtn(60),
										"click on popup Cross button", action.BOOLEAN)) {
									appLog.info("Confirm Disclaimer Deactivation Popup is closed by Cross button");

									if (niam.verifyDisclaimerGrid(DisclaimerGrid.Deactivate,
											M4DIS1,
											DisclaimerGrid.View, DisclaimerGrid.LastActivatedOn,
											DisclaimerGrid.CreatedOn)) {
										appLog.info("Disclaimer grid has been verified.");

									} else {
										appLog.error("Disclaimer grid not verified.");
										sa.assertTrue(false, "Disclaimer grid not verified.");
									}

									if (niam.getDisclaimerRecordCount() >= 1) {
										appLog.info("record count is verfied and equals to : "
												+ niam.getDisclaimerRecordCount());
									} else {
										appLog.error("record count is not verfied.");
										sa.assertTrue(false, "record count is not verfied.");
									}
									if (niam.getActiveDisclaimerStatisticsValue()
											.equals(M4DIS1)) {
										appLog.info(
												"Active Disclaimer Statics : '"
														+M4DIS1
														+ "' is available");
									} else {
										appLog.error("Active Disclaimer Statics value  is not verified.");
										sa.assertTrue(false, "Active Disclaimer Statics value is not verified.");
									}
									if (niam.getAcceptedDiscalimerCount() >= 1
											&& niam.getWaitingDisclaimerCount() >= 1) {
										appLog.info("Active Disclaimer Statics status accepted count : "
												+ niam.getAcceptedDiscalimerCount()
												+ " Active Disclaimer Statics status waiting count : "
												+ niam.getWaitingDisclaimerCount() + "has been verified.");
									} else {
										appLog.error(
												"Active Disclaimer Statics status : accepted and waiting count is not verified.");
										sa.assertTrue(false,
												"Active Disclaimer Statics status : accepted and waiting count is not verified.");
									}
								} else {
									appLog.error("Confirm Disclaimer Deactivation Popup is not closed.");
									sa.assertTrue(false, "Confirm Disclaimer Deactivation Popup is not closed.");
								}

							} else {
								appLog.error("Confirm Disclaimer Deactivation Popup header text is not verified.");
								sa.assertTrue(false,
										"Confirm Disclaimer Deactivation Popup header text is not verified.");
							}
						} else {
							appLog.error(
									"Deactivate button is not clickable and Confirm Disclaimer Deactivation Popup is not openend.");
							sa.assertTrue(false,
									"Deactivate button is not clickable and Confirm Disclaimer Deactivation Popup is not openend.");
						}

						appLog.info(
								"*******************  check result when i click on <yes button> of Confirm Disclaimer Deactivation Popup **********************");

						if (click(driver, niam.getDeactivateFundButton(60), "Click on Deactivate button",
								action.BOOLEAN)) {
							appLog.info("Confirm Disclaimer Deactivation Popup is openend.");
							if (niam.getConfirmDisclaimerHeading(60).getText().trim()
									.equals("Confirm Disclaimer Deactivation")) {
								appLog.info("Popup header text is verified which is : '"
										+ niam.getConfirmDisclaimerHeading(60).getText() + "' ");

								if (click(driver, niam.getDisclaimerDeactivationYesBtn(60), "click on popup Yes button",
										action.BOOLEAN)) {
									appLog.info("Confirm Disclaimer Deactivation Popup is closed by Yes button");

									if (niam.verifyDisclaimerGrid(DisclaimerGrid.Activate,
											M4DIS1,
											DisclaimerGrid.View, DisclaimerGrid.LastActivatedOn,
											DisclaimerGrid.CreatedOn)) {
										appLog.info("Disclaimer grid has been verified.");

									} else {
										appLog.error("Disclaimer grid not verified.");
										sa.assertTrue(false, "Disclaimer grid not verified.");
									}

									if (niam.getDisclaimerRecordCount() >= 1) {
										appLog.info("record count is verfied and equals to : "
												+ niam.getDisclaimerRecordCount());
									} else {
										appLog.error("record count is not verfied.");
										sa.assertTrue(false, "record count is not verfied.");
									}
									if (niam.getActiveDisclaimerStatisticsValue().equals("None")) {
										appLog.info("Active Disclaimer Statics : "
												+ niam.getActiveDisclaimerStatisticsValue() + " is available");
									} else {
										appLog.error("Active Disclaimer Statics value  is not verified.");
										sa.assertTrue(false, "Active Disclaimer Statics value is not verified.");
									}
									if (niam.getAcceptedDiscalimerCount() == 0
											&& niam.getWaitingDisclaimerCount() == 0) {
										appLog.info("Active Disclaimer Statics status accepted count : "
												+ niam.getAcceptedDiscalimerCount()
												+ " Active Disclaimer Statics status waiting count : "
												+ niam.getWaitingDisclaimerCount() + "has been verified.");
									} else {
										appLog.error(
												"Active Disclaimer Statics status : accepted and waiting count is not verified.");
										sa.assertTrue(false,
												"Active Disclaimer Statics status : accepted and waiting count is not verified.");
									}
								} else {
									appLog.error("Confirm Disclaimer Deactivation Popup is not closed.");
									sa.assertTrue(false, "Confirm Disclaimer Deactivation Popup is not closed.");
								}

							} else {
								appLog.error("Confirm Disclaimer Deactivation Popup header text is not verified.");
								sa.assertTrue(false,
										"Confirm Disclaimer Deactivation Popup header text is not verified.");
							}
						} else {
							appLog.error(
									"Deactivate button is not clickable and Confirm Disclaimer Deactivation Popup is not openend.");
							sa.assertTrue(false,
									"Deactivate button is not clickable and Confirm Disclaimer Deactivation Popup is not openend.");
						}

						appLog.info(
								"******************* check result when i click on <View Button> against 'Disclaimer 1' **********************");

						if (niam.clickOnViewLinkOfDisclaimer(
								M4DIS1)) {
							appLog.info("Disclaimer Statistics Popup is opened.");
							String selectedOpt = getSelectedOptionOfDropDown(driver, niam.getDisclaimerNamePickList(80),
									"get selected option from name picklist", "text");
							System.out.println("selected option is  : " + selectedOpt);
							if (selectedOpt.equals(M4DIS1)) {
								appLog.info(selectedOpt + "is selected in name picklist.");

							} else {
								appLog.error("Disclaimer 1 is not selected in name picklist.");
								sa.assertTrue(false, "Disclaimer 1 is not selected in name picklist.");
							}
							if (niam.verifyDisclaimerStatisticsGrid(M4CFN2 + " " + M4CLN2, M4C2Email, M4I2, "Accepted","")) {
								appLog.info(M4CFN2 + " " + M4CLN2 + "contact detail is verfied ");
							} else {
								appLog.error("contact detail is not verfied.");
								sa.assertTrue(false, "contact detail 1 is not verfied.");
							}
							if (niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1, "Waiting",
									"")) {
								appLog.info(M4CFN1 + " " + M4CLN1 + "contact detail is verfied ");
							} else {
								appLog.error("contact detail is not verfied.");
								sa.assertTrue(false, "contact detail 2  is not verfied.");
							}
							if (niam.getDisclaimerViewPopUpRecordCount() == 2) {
								appLog.info("Disclaimer Statistics popup record count is verfied and equals to : "
										+ niam.getDisclaimerViewPopUpRecordCount());
							} else {
								appLog.error("record count is not verfied.");
								sa.assertTrue(false, "record count is not verfied.");
							}
						}

						else {
							appLog.error("Disclaimer Statistics Popup is not openend.");
							sa.assertTrue(false, "Disclaimer Statistics Popup is not openend.");
						}

					} else {
						appLog.error(M4F1 + " fund name is not present in the drop down, So not able to select.");
						sa.assertTrue(false,
								M4F1 + " fund name is not present in the drop down, So not able to select.");
					}
				} else {
					appLog.error("not able to switch to  Child Navatar add-ons inverstor disclaimer frame.");
					sa.assertTrue(false, "not able to switch to Child Navatar add-ons inverstor disclaimer frame.");

				}

			} else {
				appLog.error("not able to switch to  Parent Navatar add-ons inverstor disclaimer frame.");
				sa.assertTrue(false, "not able to switch to Parent  Navatar add-ons inverstor disclaimer frame.");
			}

		} else {
			appLog.error("Navatar investor add on tab cannot be clicked.");
			sa.assertTrue(false, "Navatar investor add on tab cannot be clicked.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);

		driver.close();
		config(browserToLaunch);

		lp = new LoginPageBusinessLayer(driver);
		appLog.info("******************* logout to admin user and login to contact 1 **********************");
		InvestorFirmPageBusinesslayer ifpb = new InvestorFirmPageBusinesslayer(driver);
		niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		if (lp.investorLogin(M4C1Email, adminPassword)) {
			if (matchTitle(driver, "Firm Alerts", 60)) {
				appLog.info("Page title is successfully matched.");
			} else {
				appLog.info("Page title is not matched.");
				sa.assertTrue(false, "Page title is not matched.");
			}
			if (niam.getPendingDisclaimerPopGoToDisclaimerButton(60) == null) {
				appLog.info("Pending Disclaimer Popup is not available.");
			} else {
				appLog.info("Pending Disclaimer Popup is available.");
				sa.assertTrue(false, "Pending Disclaimer Popup is available.");
			}

			String fileName = ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC,
					excelLabel.UploadedFileStandard);
			boolean flag = false;
			int i = 0;
			while (true) {
				List<WebElement> ele = ifpb.getAllDocumentHeaderTextList();
				List<WebElement> listOfDoc = ifpb.getAllDocumentFileNameList();
				for (int j = 0; j < listOfDoc.size(); j++) {
					String a = listOfDoc.get(j).getText().trim();
					if (a.equalsIgnoreCase(fileName)) {
						appLog.info(fileName + " is available in the content grid");
						flag = true;

						break;
					}
					if (j == listOfDoc.size() - 1) {
						appLog.error(fileName + "file is not present in the content grid");
					}
				}
				if (flag) {
					break;
				} else {
					i++;
					if (i == 2) {
						appLog.error(fileName + " is not available in the content grid ");
						sa.assertTrue(false, fileName + " is not available in the content grid");
						break;
					}
				}
				click(driver, ele.get(0), "document name column head", action.SCROLLANDBOOLEAN);
			}
			if (flag == true) {
				if (bp.verifyDownloadFunctionality(PageName.InvestorFirmPage, Workspace.FundraisingWorkspace, fileName,
						true, true, true)) {

					appLog.info("download functionality has been successfully verified");

				} else {
					appLog.error("download functionality has failed");
					sa.assertTrue(false, "download functionality has failed");

				}
			}
		} else {
			appLog.info("Investor password is wrong so cannot login.");
			sa.assertTrue(false, "Investor password is wrong so cannot login");

		}
		lp.investorLogout();
		sa.assertAll();

	}
	
	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc026_CreateBothWorkspaceForFund2AndVerifySelectFundPicklist() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		WebElement ele = null;
		lp.CRMLogin(Org3CRMUser1EmailID, adminPassword);
		String[] stdPath = ExcelUtils
				.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath)
				.split(",");
		String[] cmnPath = ExcelUtils
				.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath)
				.split(",");
		String[] shdPath = ExcelUtils
				.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath)
				.split(",");
		String[] intPath = ExcelUtils
				.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath)
				.split(",");
		String StdFromFolder = "UploadFiles\\Module4\\M4F2\\FWR\\Standard";
		String CommonFromFolder = "UploadFiles\\Module4\\M4F2\\FWR\\Common";
		String InternalFromFolder = "UploadFiles\\Module4\\M4F2\\FWR\\Internal";
		String SharedFromFolder = "UploadFiles\\Module4\\M4F2\\FWR\\Shared";
		if (lp.clickOnTab(environment, mode,TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(environment, mode,M4F2)) {
				String Size=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M4F2", excelLabel.Fund_Size);
				String vintageyear=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M4F2", excelLabel.Fund_VintageYear);
				String contact=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M4F2", excelLabel.Fund_Contact);
				String phone=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M4F2", excelLabel.Fund_Phone);
				String email=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M4F2", excelLabel.Fund_Email);
				String description=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M4F2", excelLabel.Fund_Description);
				String[] step1Of3Data= {Size,vintageyear,contact,phone,email,description};
				
				if (fp.buildWorkspace(step1Of3Data, WorkSpaceAction.IMPORTFOLDERTEMPLATE, folderTemplateName, null,
						M4I1 + "<break>" + M4I2, Workspace.FundraisingWorkspace, 60)) {
					appLog.info("Fundraising Workspace is built successfully.");
				} else {
					appLog.error("Fundraising workspace is not built successfully.");
					sa.assertTrue(false, "Fundraising workspace is not built successfully.");
				}
				if (fp.buildWorkspace(step1Of3Data, WorkSpaceAction.IMPORTFOLDERTEMPLATE, folderTemplateName, null,
						M4I3 + "/" + M4LP1 + "<break>" + M4I4 + "/" + M4LP2, Workspace.InvestorWorkspace, 60)) {
					appLog.info("Investor Workspace is built successfully.");
				} else {
					appLog.error("Investor workspace is not built successfully.");
					sa.assertTrue(false, "Investor workspace is not built successfully.");
				}
				if (fp.inviteContact(environment, mode, M4I1, M4C1Email, stdPath[1], FolderType.Standard, "Upload", "Yes",
						"No", null, Workspace.FundraisingWorkspace, null)) {
					appLog.info("Successfully provided access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
				}
				if (fp.inviteContact(environment, mode, M4I1, M4C2Email, stdPath[1], FolderType.Standard, "Upload", "Yes",
						"No", null, Workspace.FundraisingWorkspace, M4C2Email)) {
					appLog.info("Successfully provided access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
				}
				if (fp.inviteContact(environment, mode, M4I1, M4C1Email, stdPath[0], FolderType.Standard, "Upload", "Yes",
						"No", null, Workspace.FundraisingWorkspace, null)) {
					appLog.info("Successfully provided access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
				}
				if (fp.inviteContact(environment, mode, M4I1, M4C2Email, stdPath[0], FolderType.Standard, "Upload", "Yes",
						"No", null, Workspace.FundraisingWorkspace, M4C2Email)) {
					appLog.info("Successfully provided access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
				}
				if (fp.uploadFile(stdPath[0], M4I1, StdFromFolder, null, UploadFileActions.Upload,
						Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("Successfully uploaded files in '" + stdPath[0] + "'");
				} else {
					appLog.error("Not able to upload files in '" + stdPath[0] + "'");
					sa.assertTrue(false, "Not able to upload files in '" + stdPath[0] + "'");
				}
				StdFromFolder = "UploadFiles\\Module4\\M4F2\\FWR\\SubStandard";
				if (fp.uploadFile(stdPath[1], M4I1, StdFromFolder, null, UploadFileActions.Upload,
						Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("Successfully uploaded files in '" + stdPath[1] + "'");
				} else {
					appLog.error("Not able to upload files in '" + stdPath[1] + "'");
					sa.assertTrue(false, "Not able to upload files in '" + stdPath[1] + "'");
				}
				if (fp.uploadFile(cmnPath[0], null, CommonFromFolder, null, UploadFileActions.Upload,
						Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("Successfully uploaded files in '" + cmnPath[0] + "'");
				} else {
					appLog.error("Not able to upload files in '" + cmnPath[0] + "'");
					sa.assertTrue(false, "Not able to upload files in '" + cmnPath[0] + "'");
				}
				CommonFromFolder = CommonFromFolder + "Sub";
				if (fp.uploadFile(cmnPath[1], null, CommonFromFolder, null, UploadFileActions.Upload,
						Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("Successfully uploaded files in '" + cmnPath[1] + "'");
				} else {
					appLog.error("Not able to upload files in '" + cmnPath[1] + "'");
					sa.assertTrue(false, "Not able to upload files in '" + cmnPath[1] + "'");
				}
				if (fp.uploadFile(shdPath[0], null, SharedFromFolder, null, UploadFileActions.Upload,
						Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("Successfully uploaded files in '" + shdPath[0] + "'");
				} else {
					appLog.error("Not able to upload files in '" + shdPath[0] + "'");
					sa.assertTrue(false, "Not able to upload files in '" + shdPath[0] + "'");
				}
				SharedFromFolder = SharedFromFolder + "Sub";
				if (fp.uploadFile(shdPath[1], null, SharedFromFolder, null, UploadFileActions.Upload,
						Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("Successfully uploaded files in '" + shdPath[1] + "'");
				} else {
					appLog.error("Not able to upload files in '" + shdPath[1] + "'");
					sa.assertTrue(false, "Not able to upload files in '" + shdPath[1] + "'");
				}
				if (fp.uploadFile(intPath[0], null, InternalFromFolder, null, UploadFileActions.Upload,
						Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("Successfully uploaded files in '" + intPath[0] + "'");
				} else {
					appLog.error("Not able to upload files in '" + intPath[0] + "'");
					sa.assertTrue(false, "Not able to upload files in '" + intPath[0] + "'");
				}
				InternalFromFolder = InternalFromFolder + "Sub";
				if (fp.uploadFile(intPath[1], null, InternalFromFolder, null, UploadFileActions.Upload,
						Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("Successfully uploaded files in '" + intPath[1] + "'");
				} else {
					appLog.error("Not able to upload files in '" + intPath[1] + "'");
					sa.assertTrue(false, "Not able to upload files in '" + intPath[1] + "'");
				}
				if (fp.inviteContact(environment, mode, M4I3 + "/" + M4LP1, M4C1Email, stdPath[1], FolderType.Standard,
						"Upload", "Yes", "No", null, Workspace.InvestorWorkspace, M4C1Email)) {
					appLog.info("Successfully provided access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
				}
				if (fp.inviteContact(environment, mode, M4I3 + "/" + M4LP1, M4C2Email, stdPath[1], FolderType.Standard,
						"Upload", "Yes", "No", null, Workspace.InvestorWorkspace, M4C2Email)) {
					appLog.info("Successfully provided access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
				}
				if (fp.inviteContact(environment, mode, M4I3 + "/" + M4LP1, M4C1Email, stdPath[0], FolderType.Standard,
						"Upload", "Yes", "No", null, Workspace.InvestorWorkspace, M4C1Email)) {
					appLog.info("Successfully provided access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
				}
				if (fp.inviteContact(environment, mode, M4I3 + "/" + M4LP1, M4C2Email, stdPath[0], FolderType.Standard,
						"Upload", "Yes", "No", null, Workspace.InvestorWorkspace, M4C2Email)) {
					appLog.info("Successfully provided access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
				}
				StdFromFolder = "UploadFiles\\Module4\\M4F2\\INV\\Standard";
				CommonFromFolder = "UploadFiles\\Module4\\M4F2\\INV\\Common";
				InternalFromFolder = "UploadFiles\\Module4\\M4F2\\INV\\Internal";
				SharedFromFolder = "UploadFiles\\Module4\\M4F2\\INV\\Shared";
				if (fp.uploadFile(stdPath[0], M4I3 + "/" + M4LP1, StdFromFolder, null, UploadFileActions.Upload,
						Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("Successfully uploaded files in '" + stdPath[0] + "'");
				} else {
					appLog.error("Not able to upload files in '" + stdPath[0] + "'");
					sa.assertTrue(false, "Not able to upload files in '" + stdPath[0] + "'");
				}
				StdFromFolder = "UploadFiles\\Module4\\M4F2\\INV\\SubStandard";
				if (fp.uploadFile(stdPath[1], M4I3 + "/" + M4LP1, StdFromFolder, null, UploadFileActions.Upload,
						Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("Successfully uploaded files in '" + stdPath[1] + "'");
				} else {
					appLog.error("Not able to upload files in '" + stdPath[1] + "'");
					sa.assertTrue(false, "Not able to upload files in '" + stdPath[1] + "'");
				}
				if (fp.uploadFile(cmnPath[0], null, CommonFromFolder, null, UploadFileActions.Upload,
						Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("Successfully uploaded files in '" + cmnPath[0] + "'");
				} else {
					appLog.error("Not able to upload files in '" + cmnPath[0] + "'");
					sa.assertTrue(false, "Not able to upload files in '" + cmnPath[0] + "'");
				}
				CommonFromFolder = CommonFromFolder + "Sub";
				if (fp.uploadFile(cmnPath[1], null, CommonFromFolder, null, UploadFileActions.Upload,
						Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("Successfully uploaded files in '" + cmnPath[1] + "'");
				} else {
					appLog.error("Not able to upload files in '" + cmnPath[1] + "'");
					sa.assertTrue(false, "Not able to upload files in '" + cmnPath[1] + "'");
				}
				if (fp.uploadFile(shdPath[0], null, SharedFromFolder, null, UploadFileActions.Upload,
						Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("Successfully uploaded files in '" + shdPath[0] + "'");
				} else {
					appLog.error("Not able to upload files in '" + shdPath[0] + "'");
					sa.assertTrue(false, "Not able to upload files in '" + shdPath[0] + "'");
				}
				SharedFromFolder = SharedFromFolder + "Sub";
				if (fp.uploadFile(shdPath[1], null, SharedFromFolder, null, UploadFileActions.Upload,
						Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("Successfully uploaded files in '" + shdPath[1] + "'");
				} else {
					appLog.error("Not able to upload files in '" + shdPath[1] + "'");
					sa.assertTrue(false, "Not able to upload files in '" + shdPath[1] + "'");
				}
				if (fp.uploadFile(intPath[0], null, InternalFromFolder, null, UploadFileActions.Upload,
						Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("Successfully uploaded files in '" + intPath[0] + "'");
				} else {
					appLog.error("Not able to upload files in '" + intPath[0] + "'");
					sa.assertTrue(false, "Not able to upload files in '" + intPath[0] + "'");
				}
				InternalFromFolder = InternalFromFolder + "Sub";
				if (fp.uploadFile(intPath[1], null, InternalFromFolder, null, UploadFileActions.Upload,
						Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("Successfully uploaded files in '" + intPath[1] + "'");
				} else {
					appLog.error("Not able to upload files in '" + intPath[1] + "'");
					sa.assertTrue(false, "Not able to upload files in '" + intPath[1] + "'");
				}
			} else {
				appLog.error(M4F2 + " fund is not availabe in the list.");
				sa.assertTrue(false, M4F2 + " fund is not availabe in the list.");
			}
		} else {
			appLog.error("Funds Tab cannot be clicked, so cannot build the workspace.");
			sa.assertTrue(false, "Funds Tab cannot be clicked, so cannot build the workspace.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		bp = new BasePageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		if (bp.clickOnTab(TabName.NavatarInvestorAddOns)) {
			switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60));
			switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
			List<WebElement> lstOfEle = allOptionsInDropDrop(driver, niam.getSelectFundDropDown(60),
					"Options in name picklist");
			boolean flag_Fund1 = false;
			boolean flag_Fund2 = false;
			for (int i = 0; i < lstOfEle.size(); i++) {
				if (lstOfEle.get(i).getText().trim().equals(M4F2)) {
					flag_Fund2 = true;
					appLog.info(lstOfEle.get(i).getText().trim() + "is present in name picklist");
				} else if (lstOfEle.get(i).getText().trim().equals(M4F1)) {
					flag_Fund1 = true;
					appLog.info(lstOfEle.get(i).getText().trim() + "is present in name picklist");
				}
			}
			if (flag_Fund1 == false) {
				appLog.error(M4F1+" :Fund is not present in name picklist");
				sa.assertTrue(false, M4F1+" :Fund is not present in name picklist");
			}
			if (flag_Fund2 == false) {
				appLog.error(M4F2+" :Fund is not present in name picklist");
				sa.assertTrue(false,M4F2+" :Fund is not present in name picklist");
			}
			String selectedValue = getSelectedOptionOfDropDown(driver, niam.getSelectFundDropDown(60), "Fund Drop Down",
					"text");
			TreeSet<String> funds = new TreeSet<String>();
			funds.add(M4F1);
			funds.add(M4F2);
			if (selectedValue != null && selectedValue.equalsIgnoreCase(funds.first())) {
				appLog.info(funds.first() + " Option is selected in the drop down and is verified.");
			} else {
				appLog.error(funds.first() + " Option is not selected in the drop down and is not verified.Expected: " + M4F1
						+ "\tActual: " + selectedValue);
				sa.assertTrue(false, funds.first() + " Option is not selected in the drop down and is not verified.Expected: "
						+ funds.first() + "\tActual: " + selectedValue);
			}
			if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60), "Select Fund Dropdown", M4F1)) {
				if (niam.verifyDisclaimerGrid(DisclaimerGrid.Deactivate, M4DIS2, DisclaimerGrid.View,
						DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
					appLog.info("content grid is verfied successfully.");
				} else {
					appLog.error("Content grid is not verfied.");
					sa.assertTrue(false, "Content grid is not verfied.");
				}
				if (niam.verifyDisclaimerGrid(DisclaimerGrid.Activate, M4DIS1, DisclaimerGrid.View,
						DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
					appLog.info("content grid is verfied successfully.");
				} else {
					appLog.error("Content grid is not verfied.");
					sa.assertTrue(false, "Content grid is not verfied.");
				}
				int recordCount = niam.getDisclaimerRecordCount();
				if (recordCount >= 2) {
					appLog.info("Record count is verified");
				} else {
					appLog.info("Record count is not verified");
					sa.assertTrue(false, "Record Count is not verified");
				}
				String activeDisclaimerStatistics = niam.getActiveDisclaimerStatisticsValue();
				if (activeDisclaimerStatistics.equalsIgnoreCase(M4DIS2)) {
					appLog.info("Disclaimer 2 is displaying");
				} else {
					appLog.info("Discalimer 2 is not displaying");
					sa.assertTrue(false, "Disclaimer 2 is not displaying");
				}
				int waitingRecordCount = niam.getWaitingDisclaimerCount();
				if (waitingRecordCount == 2) {
					appLog.info("Waiting disclaimer count is veroified");
				} else {
					appLog.info("Waiting Disclaimer count is not verified");
					sa.assertTrue(false, "Waiting disclaimer count is not verified");
				}
				int AcceptedRecordCount = niam.getAcceptedDiscalimerCount();
				if (AcceptedRecordCount == 0) {
					appLog.info("Accepted disclaimer count is verified");
				} else {
					appLog.info("Accepted Disclaimer count is not verified");
					sa.assertTrue(false, "Accepted disclaimer count is not verified");
				}
			} else {
				appLog.info("Not able to select fund from dropdown");
				sa.assertTrue(false, "Not able to sleect fund from dropdown");
			}
			if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60), "Select Fund Dropdown", M4F2)) {
				ThreadSleep(2000);
				if (niam.getCurrentStatusOfDisclaimer().length == 2) {
					if (niam.getCurrentStatusOfDisclaimer()[0].equalsIgnoreCase("Inactive")) {
						appLog.info("Status is successfully verifed.");
					} else {
						appLog.error("Status is not verifed. Expected: Active\tActual: "
								+ niam.getCurrentStatusOfDisclaimer()[0]);
						sa.assertTrue(false, "Status is not verifed. Expected: Active\tActual: "
								+ niam.getCurrentStatusOfDisclaimer()[0]);
					}
					if (niam.getCurrentStatusOfDisclaimer()[1].equalsIgnoreCase("Red")) {
						appLog.info("Status color is successfully verifed.");
					} else {
						appLog.error("Status color is not verifed. Expected: Green\tActual: "
								+ niam.getCurrentStatusOfDisclaimer()[1]);
						sa.assertTrue(false, "Status color is not verifed. Expected: Green\tActual: "
								+ niam.getCurrentStatusOfDisclaimer()[1]);
					}
				} else {
					appLog.error("Status is not verifed.");
					sa.assertTrue(false, "Status is not verifed.");
				}
				if (isEnabled(driver, niam.getNewDisclaimerButton(60), "New disclaimer button")) {
					appLog.info("New Disclaimer button is enabled");
				} else {
					appLog.info("New Disclaimer button is not enabled");
					sa.assertTrue(false, "New Disclaimer button is not enabled");
				}
				ele = FindElement(driver, "//span[@id='grid_DefaultFoldersView1-cell-1-0']/span",
						"No data to display msg", action.BOOLEAN, 30);
				String text = getAttribute(driver, ele, "No data to display msg", "title");
				if (text.contains(NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage)) {
					appLog.info(
							NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage + " error msg is verified.");
				} else {
					appLog.error(NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage
							+ " error msg is not verified.expected: "
							+ NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage + "\tActual: " + text);
					sa.assertTrue(false, NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage
							+ " error msg is  not verified.expected: "
							+ NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage + "\tActual: " + text);
				}
				int recordCount = niam.getDisclaimerRecordCount();
				if (recordCount == 0) {
					appLog.info("Record count is verified for fund 2");
				} else {
					appLog.info("Record count is not verified for fund 2");
					sa.assertTrue(false, "Record Count is not verified for fund 2");
				}
				String activeDisclaimerStatistics = niam.getActiveDisclaimerStatisticsValue();
				if (activeDisclaimerStatistics.equalsIgnoreCase("None")) {
					appLog.info("Active Disclaimer Statistics is verified");
				} else {
					appLog.info("Active Disclaimer Statistics is not verified");
					sa.assertTrue(false, "Active Disclaimer Statistics is not verified");
				}
				int waitingRecordCount = niam.getWaitingDisclaimerCount();
				if (waitingRecordCount == 0) {
					appLog.info("Waiting disclaimer count is veroified");
				} else {
					appLog.info("Waiting Disclaimer count is not verified");
					sa.assertTrue(false, "Waiting disclaimer count is not verified");
				}
				int AcceptedRecordCount = niam.getAcceptedDiscalimerCount();
				if (AcceptedRecordCount == 0) {
					appLog.info("Accepted disclaimer count is verified");
				} else {
					appLog.info("Accepted Disclaimer count is not verified");
					sa.assertTrue(false, "Accepted disclaimer count is not verified");
				}
			} else {
				appLog.info("Not able to select fund from dropdown");
				sa.assertTrue(false, "Not able to sleect fund from dropdown");
			}
		} else {
			appLog.info("Not able to click on navatar investor add ons tab");
			sa.assertTrue(false, "Not able to click on navatar investor add ons tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc027_CreateDisclaimerForFund2AndActivateItAndVerifyItsImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if (lp.clickOnTab(TabName.NavatarInvestorAddOns)) {
			switchToFrame(driver, 60, niam.getNavatarInvestorAddOnFrame(60));
			switchToFrame(driver, 60, niam.getNavatarInvestorAddOnFrame1(60));
			if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60),
					"Select fund drop down on Navatar Investor add-ons page", M4F2)) {
				appLog.info(M4F2 + "value is select from dropdown.");
				if (click(driver, niam.getNewDisclaimerButton(60), "New discliamer Button", action.SCROLLANDBOOLEAN)) {
					if (sendKeys(driver, niam.getNewDisclaimerNameTextBox(60), M4DIS1, "DisclaimerName text Box",
							action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, niam.getDisclaimerDescriptionTextArea(10), M4DISDEC1,
								"Description text box", action.BOOLEAN)) {
							switchToDefaultContent(driver);
							switchToFrame(driver, 60, niam.getNavatarInvestorAddOnFrame(60));
							switchToFrame(driver, 60, niam.getNavatarInvestorAddOnFrame1(60));
							if (click(driver, niam.getNewDiscalimerPopUpSaveButton(60), "Save Button",
									action.SCROLLANDBOOLEAN)) {
								appLog.info("Disclaimer has been saved successfully.");
								if (niam.verifyDisclaimerGrid(DisclaimerGrid.Activate, M4DIS1, DisclaimerGrid.View,
										null, DisclaimerGrid.CreatedOn)) {
									appLog.info("content grid is verfied successfully.");
								} else {
									appLog.error("Content grid is not verfied.");
									sa.assertTrue(false, "Content grid is not verfied.");
								}
								int recordCount = niam.getDisclaimerRecordCount();
								if (recordCount == 1) {
									appLog.info("Record count is verified for fund 2");
								} else {
									appLog.info("Record count is not verified for fund 2");
									sa.assertTrue(false, "Record Count is not verified for fund 2");
								}
								String activeDisclaimerStatistics = niam.getActiveDisclaimerStatisticsValue();
								if (activeDisclaimerStatistics.equalsIgnoreCase("None")) {
									appLog.info("Active Disclaimer Statistics is verified");
								} else {
									appLog.info("Active Disclaimer Statistics is not verified");
									sa.assertTrue(false, "Active Disclaimer Statistics is not verified");
								}
								int waitingRecordCount = niam.getWaitingDisclaimerCount();
								if (waitingRecordCount == 0) {
									appLog.info("Waiting disclaimer count is veroified");
								} else {
									appLog.info("Waiting Disclaimer count is not verified");
									sa.assertTrue(false, "Waiting disclaimer count is not verified");
								}
								int AcceptedRecordCount = niam.getAcceptedDiscalimerCount();
								if (AcceptedRecordCount == 0) {
									appLog.info("Accepted disclaimer count is verified");
								} else {
									appLog.info("Accepted Disclaimer count is not verified");
									sa.assertTrue(false, "Accepted disclaimer count is not verified");
								}
								if (niam.activateDisclaimer(M4DIS1, 60)) {
									if (niam.verifyDisclaimerGrid(DisclaimerGrid.Deactivate, M4DIS1,
											DisclaimerGrid.View, DisclaimerGrid.LastActivatedOn,
											DisclaimerGrid.CreatedOn)) {
										appLog.info("content grid is verfied successfully.");
									} else {
										appLog.error("Content grid is not verfied.");
										sa.assertTrue(false, "Content grid is not verfied.");
									}
									recordCount = niam.getDisclaimerRecordCount();
									if (recordCount == 1) {
										appLog.info("Record count is verified for fund 2");
									} else {
										appLog.info("Record count is not verified for fund 2");
										sa.assertTrue(false, "Record Count is not verified for fund 2");
									}
									activeDisclaimerStatistics = niam.getActiveDisclaimerStatisticsValue();
									if (activeDisclaimerStatistics.equalsIgnoreCase(M4DIS1)) {
										appLog.info("Active Disclaimer Statistics is verified");
									} else {
										appLog.info("Active Disclaimer Statistics is not verified");
										sa.assertTrue(false, "Active Disclaimer Statistics is not verified");
									}
									waitingRecordCount = niam.getWaitingDisclaimerCount();
									if (waitingRecordCount == 2) {
										appLog.info("Waiting disclaimer count is veroified");
									} else {
										appLog.info("Waiting Disclaimer count is not verified");
										sa.assertTrue(false, "Waiting disclaimer count is not verified");
									}
									AcceptedRecordCount = niam.getAcceptedDiscalimerCount();
									if (AcceptedRecordCount == 0) {
										appLog.info("Accepted disclaimer count is verified");
									} else {
										appLog.info("Accepted Disclaimer count is not verified");
										sa.assertTrue(false, "Accepted disclaimer count is not verified");
									}
								} else {
									appLog.info("Not able to activate disclaimer 1");
									sa.assertTrue(false, "Not able to activate disclaimer 1");
								}
							} else {
								appLog.error(M4DIS1 + "Not able to click on save button");
								sa.assertTrue(false, "Not able to click on save button");
							}
						} else {
							appLog.error("Not able to pass value to discalimer description text box.");
							sa.assertTrue(false, "Not able to pass value to disclaimer description text box.");
						}
					} else {
						appLog.error("Not able to pass value to disclimer name text box.");
						sa.assertTrue(false, "Not able to pass value to disclimer name text box.");
					}
				} else {
					appLog.error(
							"New disclaimer button cannot be clikced so cannot check the new disclaimer pop up UI.");
					sa.assertTrue(false,
							"New disclaimer button cannot be clikced so cannot check the new disclaimer pop up UI.");
				}
			} else {
				appLog.error(M4F2 + " fund name is not present in the drop down, So not able to select.");
				sa.assertTrue(false, M4F2 + " fund name is not present in the drop down, So not able to select.");
			}
		} else {
			appLog.info("Not able to click on navatar investor add ons tab");
			sa.assertTrue(false, "Not able to click on navatar investor add ons tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc027_CreateDisclaimerForFund2AndActivateItAndVerifyItsImpactOnInvestorside() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		DisclaimerPageBussinessLayer dp = new DisclaimerPageBussinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M4C1Email, adminPassword);
		WebElement ele = null;
		if (click(driver, dp.getPendingDisclaimerPopGoToDisclaimerButton(60), "Go to disclaimer button",
				action.SCROLLANDBOOLEAN)) {
			String parentID = switchOnWindow(driver);
			if (parentID != null) {
				List<WebElement> totalFundRow = FindElements(driver, "//div[@class='contacts_n_name_div_Investment']",
						"Total FundsRow");
				if (totalFundRow.size() == 2) {
					appLog.info("2 funds rea displaying");
				} else {
					appLog.info("2 Funds are not displaying");
					sa.assertTrue(false, "2 Funds are not displaying");
				}
				if (dp.verifyFundDisplaying(M4F1, Org3FirmName)) {
					appLog.info("Fund is displaying and fund name is verified");
				} else {
					appLog.info("Fund is not displaying and fund name is not verified");
					sa.assertTrue(false, "Fund Is Not displaying and fund name is not verified");
				}
				if (dp.verifyFundDisplaying(M4F2, Org3FirmName)) {
					appLog.info("Fund is displaying and fund name is verified");
				} else {
					appLog.info("Fund is not displaying and fund name is not verified");
					sa.assertTrue(false, "Fund Is Not displaying and fund name is not verified");
				}
				if (dp.verifyFundCollapsed(M4F1)) {
					appLog.info(M4F1 + " is collapsed");
				} else {
					appLog.info(M4F1 + " is not collapsed");
					sa.assertTrue(false, M4F1 + " is not collapsed");
				}
				if (dp.verifyFundCollapsed(M4F2)) {
					appLog.info(M4F2 + " is collapsed");
				} else {
					appLog.info(M4F2 + " is not collapsed");
					sa.assertTrue(false, M4F2 + " is not collapsed");
				}
				if (dp.clickOnExpandIcon(M4F2, 60)) {
					if (dp.verifyFundDisclaimerDescription(M4F2, M4DIS1, M4DISDEC1)) {
						appLog.info("disclaimer description is verified");
					} else {
						appLog.error(" disclaimer description is not verifed");
						sa.assertTrue(false, " disclaimer description is not verifed.");
					}
				} else {
					appLog.info("Not bale to click on expand icon");
					sa.assertTrue(false, "Not able to click on expand icon");
				}
				if (dp.clickOnCollapseIcon(M4F2, 60)) {
					if (dp.verifyFundCollapsed(M4F2)) {
						appLog.info(M4F2 + " is collapsed");
					} else {
						appLog.info(M4F2 + " is not collapsed");
						sa.assertTrue(false, M4F2 + " is not collapsed");
					}
				} else {
					appLog.info("Not able to click on collapse icon");
					sa.assertTrue(false, "Not able to click on collapse icon");
				}
				driver.close();
				driver.switchTo().window(parentID);
			}
		} else {
			appLog.info("Not able to click on  got to discalimer button");
			sa.assertTrue(false, "Not able to click on got to disclaimer button");
		}

		String[] docName = ExcelUtils.readData("filepath", excelLabel.TestCases_Name,
				"M4tc026_CreateBothWorkspaceForFund2AndVerifySelectFundPicklist", excelLabel.UploadedFileStandard)
				.split("<break>");
		if (dp.clickOnDocument("Potential Investment", M4F2, docName[0], 30, ifp.getScrollBoxAtFirmPage(60))) {
			appLog.info("Clicked on document");
			String parentID = switchOnWindow(driver);
			if (parentID != null) {
				if (click(driver, dp.getGoToDisclaimerButton(60), "Go to discalimer button", action.SCROLLANDBOOLEAN)) {
					ele = FindElement(driver,
							"//span[@title='" + M4F2 + "']/preceding-sibling::span[@title='" + Org3FirmName
							+ "']/preceding-sibling::div//span[contains(@id,'hideFilterText')]//img[contains(@src,'add-plus')]",
							"Expand or Collapse Icomn", action.BOOLEAN, 10);
					if (ele == null) {
						appLog.info("Disclaimer for fund '" + M4F2 + "' is in expanded state and is verfied.");
					} else {
						appLog.error("Disclaimer for fund '" + M4F2 + "' is in collapsed state.");
						sa.assertTrue(false, "Disclaimer for fund '" + M4F2 + "' is in collapsed state.");
					}
					if (dp.verifyFundDisplaying(M4F2, Org3FirmName)) {
						appLog.info("Fund 2 is displaying and fund name is verified");
					} else {
						appLog.info("Fund 2 is not displaying and fund name is not verified");
						sa.assertTrue(false, "Fund 2 Is Not displaying and fund name is not verified");
					}
					if (dp.verifyFundDisclaimerDescription(M4F2, M4DIS1, M4DISDEC1)) {
						appLog.info("disclaimer description is verified");
					} else {
						appLog.error(" disclaimer description is not verifed");
						sa.assertTrue(false, " disclaimer description is not verifed.");
					}
					if (dp.verifyAcceptButton(M4F2, M4DIS1)) {
						appLog.info("Accpet button is displaying");
					} else {
						appLog.info("Accept button is not displaying");
						sa.assertTrue(false, "Accept button is not displaying");
					}
				} else {
					appLog.info("Not able to click on go to discalimer button");
					sa.assertTrue(false, "Not able to click on go to disclaimer button");
				}
				driver.close();
				driver.switchTo().window(parentID);
			} else {
				appLog.info("No new window open");
				sa.assertTrue(false, "No new window open");
			}
		} else {
			appLog.info("Not able to click on document name");
			sa.assertTrue(false, "Not able to click on document name");
		}
		docName = ExcelUtils.readData("filepath", excelLabel.TestCases_Name,
				"M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn", excelLabel.UploadedFileStandard).split("<break>");
		if (dp.clickOnDocument("Potential Investment", M4F1, docName[0], 30, ifp.getScrollBoxAtFirmPage(60))) {
			appLog.info("Clicked on document");
			String parentID = switchOnWindow(driver);
			if (parentID != null) {
				if (click(driver, dp.getGoToDisclaimerButton(60), "Go to discalimer button", action.SCROLLANDBOOLEAN)) {
					ele = FindElement(driver,
							"//span[@title='" + M4F1 + "']/preceding-sibling::span[@title='" + Org3FirmName
							+ "']/preceding-sibling::div//span[contains(@id,'hideFilterText')]//img[contains(@src,'add-plus')]",
							"Expand or Collapse Icomn", action.BOOLEAN, 10);
					if (ele == null) {
						appLog.info("Disclaimer for fund '" + M4F1 + "' is in expanded state and is verfied.");
					} else {
						appLog.error("Disclaimer for fund '" + M4F1 + "' is in collapsed state.");
						sa.assertTrue(false, "Disclaimer for fund '" + M4F1 + "' is in collapsed state.");
					}
					if (dp.verifyFundDisplaying(M4F1, Org3FirmName)) {
						appLog.info("Fund 1 is displaying and fund name is verified");
					} else {
						appLog.info("Fund 1 is not displaying and fund name is not verified");
						sa.assertTrue(false, "Fund 1 Is Not displaying and fund name is not verified");
					}
					if (dp.verifyFundDisclaimerDescription(M4F1, M4DIS2, M4DISDEC2)) {
						appLog.info("disclaimer description is verified");
					} else {
						appLog.error(" disclaimer description is not verifed");
						sa.assertTrue(false, " disclaimer description is not verifed.");
					}
					if (dp.verifyAcceptButton(M4F1, M4DIS2)) {
						appLog.info("Accpet button is displaying");
					} else {
						appLog.info("Accept button is not displaying");
						sa.assertTrue(false, "Accept button is not displaying");
					}
				} else {
					appLog.info("Not able to click on go to discalimer button");
					sa.assertTrue(false, "Not able to click on go to disclaimer button");
				}
				driver.close();
				driver.switchTo().window(parentID);
			} else {
				appLog.info("No new window open");
				sa.assertTrue(false, "No new window open");
			}
		} else {
			appLog.info("Not able to click on document name");
			sa.assertTrue(false, "Not able to click on document name");
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc028_VerifyPendingDisclaimerFromBothInvestmentPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		DisclaimerPageBussinessLayer dp = new DisclaimerPageBussinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,
				excelLabel.StandardPath);
		SoftAssert sa = new SoftAssert();
		WebElement ele = null;
		lp.investorLogin(M4C1Email, adminPassword);
		if (isDisplayed(driver, bp.getPendingDisclaimerPopGoToDisclaimerButton(60), "visibility", 30,
				"Go to discliamer popup") != null) {
			appLog.info("go to disclaimer popup is displaying");
			if (click(driver, bp.getPendingDisclaimerPopGoToDisclaimerButton(60), "Go to disclaimer button",
					action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on go to discalimer button");
				String parentID = switchOnWindow(driver);
				if (parentID != null) {
					List<WebElement> totalFundRow = FindElements(driver,
							"//div[@class='contacts_n_name_div_Investment']", "Total FundsRow");
					if (totalFundRow.size() == 2) {
						appLog.info("2 funds are displaying");
					} else {
						appLog.info("2 Funds are not displaying");
						sa.assertTrue(false, "2 Funds are not displaying");
					}
					if (dp.verifyFundDisplaying(M4F1, Org3FirmName)) {
						appLog.info("Fund is displaying");
					} else {
						appLog.info("Fund is not displaying and fund name is not verified");
						sa.assertTrue(false, "Fund Is Not displaying and fund name is not verified");
					}
					if (dp.verifyFundDisplaying(M4F2, Org3FirmName)) {
						appLog.info("Fund is displaying and fund name is verified");
					} else {
						appLog.info("Fund is not displaying and fund name is not verified");
						sa.assertTrue(false, "Fund Is Not displaying and fund name is not verified");
					}
					if (dp.verifyFundCollapsed(M4F1)) {
						appLog.info(M4F1 + " is collapsed");
					} else {
						appLog.info(M4F1 + " is not collapsed");
						sa.assertTrue(false, M4F1 + " is not collapsed");
					}
					if (dp.verifyFundCollapsed(M4F2)) {
						appLog.info(M4F2 + " is collapsed");
					} else {
						appLog.info(M4F2 + " is not collapsed");
						sa.assertTrue(false, M4F2 + " is not collapsed");
					}
					driver.close();
					driver.switchTo().window(parentID);
				} else {
					appLog.info("No new window open");
					sa.assertTrue(false, "No new window is open");
				}
			} else {
				appLog.info("Not able to click on go to discalimer button");
				sa.assertTrue(false, "Not able to click on go to disclaimer button");
			}
		} else {
			appLog.info("go to disclaimer popup is not displaying");
			sa.assertTrue(false, "go to disclaimer popup is not displaying");
		}
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			if (selectVisibleTextFromDropDown(driver, ifp.getPotentialAndCurrentInvestmentInvestmentDropdown(60),
					"Investment dropdown", M4F1)) {
				if (fp.verifyFolderPathdummy(folderPath, M4I1, null, null, PageName.PotentialInvestmentPage, null,
						60)) {
					String[] documentNameStandardFolder = ExcelUtils
							.readData("filepath", excelLabel.TestCases_Name,
									"M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn", excelLabel.UploadedFileStandard)
							.split("<break>");
					if (dp.clickOnDocumentInWorkspaceContentGrid(documentNameStandardFolder[0])) {
						String parentID = switchOnWindow(driver);
						if (parentID != null) {
							if (click(driver, dp.getGoToDisclaimerButton(60), "Go to discalimer button",
									action.SCROLLANDBOOLEAN)) {
								ele = FindElement(driver,
										"//span[@title='" + M4F1 + "']/preceding-sibling::span[@title='" + Org3FirmName
										+ "']/preceding-sibling::div//span[contains(@id,'hideFilterText')]//img[contains(@src,'add-plus')]",
										"Expand or Collapse Icomn", action.BOOLEAN, 10);
								if (ele == null) {
									appLog.info(
											"Disclaimer for fund '" + M4F1 + "' is in expanded state and is verfied.");
								} else {
									appLog.error("Disclaimer for fund '" + M4F1 + "' is in collapsed state.");
									sa.assertTrue(false, "Disclaimer for fund '" + M4F1 + "' is in collapsed state.");
								}
								if (dp.verifyFundDisplaying(M4F1, Org3FirmName)) {
									appLog.info("Fund is displaying and fund name is verified");
								} else {
									appLog.info("Fund is not displaying and fund name is not verified");
									sa.assertTrue(false, "Fund Is Not displaying and fund name is not verified");
								}
								if (dp.verifyFundDisclaimerDescription(M4F1, M4DIS2, M4DISDEC2)) {
									appLog.info("disclaimer description is verified");
								} else {
									appLog.error(" disclaimer description is not verifed");
									sa.assertTrue(false, " disclaimer description is not verifed.");
								}
								if (dp.verifyAcceptButton(M4F1, M4DIS2)) {
									appLog.info("Accpet button is displaying");
								} else {
									appLog.info("Accept button is not displaying");
									sa.assertTrue(false, "Accept button is not displaying");
								}
								driver.close();
								driver.switchTo().window(parentID);
							} else {
								appLog.info("Not able to click on got to disclaimer button");
								sa.assertTrue(false, "Not bale to click on go to disclaimer button");
							}
						} else {
							appLog.info("No new window open");
							sa.assertTrue(false, "No new window is open");
						}
					} else {
						appLog.info("Not able to click on document name");
						sa.assertTrue(false, "Not able to click on document name");
					}
				} else {
					appLog.info("Folder path is not verified");
					sa.assertTrue(false, "Folder path is not verified");
				}
			} else {
				appLog.info("Not able to select fund value from invest,ment dropdown");
				sa.assertTrue(false, "Not able to select fund value from invest,ment dropdown");
			}
		} else {
			appLog.info("Not able to click on investment tab");
			sa.assertTrue(false, "Not able to click on investment tab");
		}
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			if (selectVisibleTextFromDropDown(driver, ifp.getPotentialAndCurrentInvestmentInvestmentDropdown(60),
					"Investment dropdown", M4F2)) {
				if (fp.verifyFolderPathdummy(folderPath, null, null, null, PageName.CurrentInvestmentPgae, null, 60)) {
					String[] documentNameStandardFolder = ExcelUtils.readData("filepath", excelLabel.TestCases_Name,
							"M4tc026_CreateBothWorkspaceForFund2AndVerifySelectFundPicklist",
							excelLabel.UploadedFileStandard).split("<break>");
					if (dp.clickOnDocumentInWorkspaceContentGrid(documentNameStandardFolder[3])) {
						String parentID = switchOnWindow(driver);
						if (parentID != null) {
							if (click(driver, dp.getGoToDisclaimerButton(60), "Go to discalimer button",
									action.SCROLLANDBOOLEAN)) {
								ele = FindElement(driver,
										"//span[@title='" + M4F2 + "']/preceding-sibling::span[@title='" + Org3FirmName
										+ "']/preceding-sibling::div//span[contains(@id,'hideFilterText')]//img[contains(@src,'add-plus')]",
										"Expand or Collapse Icomn", action.BOOLEAN, 10);
								if (ele == null) {
									appLog.info(
											"Disclaimer for fund '" + M4F2 + "' is in expanded state and is verfied.");
								} else {
									appLog.error("Disclaimer for fund '" + M4F2 + "' is in collapsed state.");
									sa.assertTrue(false, "Disclaimer for fund '" + M4F2 + "' is in collapsed state.");
								}
								if (dp.verifyFundDisplaying(M4F2, Org3FirmName)) {
									appLog.info("Fund is displaying and fund name is verified");
								} else {
									appLog.info("Fund is not displaying and fund name is not verified");
									sa.assertTrue(false, "Fund Is Not displaying and fund name is not verified");
								}
								if (dp.verifyFundDisclaimerDescription(M4F2, M4DIS1, M4DISDEC1)) {
									appLog.info("disclaimer description is verified");
								} else {
									appLog.error(" disclaimer description is not verifed");
									sa.assertTrue(false, " disclaimer description is not verifed.");
								}
								if (dp.verifyAcceptButton(M4F2, M4DIS1)) {
									appLog.info("Accpet button is displaying");
								} else {
									appLog.info("Accept button is not displaying");
									sa.assertTrue(false, "Accept button is not displaying");
								}
								driver.close();
								driver.switchTo().window(parentID);
							} else {
								appLog.info("Not able to click on got to disclaimer button");
								sa.assertTrue(false, "Not bale to click on go to disclaimer button");
							}
						} else {
							appLog.info("No new window open");
							sa.assertTrue(false, "No new window is open");
						}
					} else {
						appLog.info("Not able to click on document name");
						sa.assertTrue(false, "Not able to click on document name");
					}
				} else {
					appLog.info("Folder path is not verified");
					sa.assertTrue(false, "Folder path is not verified");
				}
			} else {
				appLog.info("Not able to select fund value from invest,ment dropdown");
				sa.assertTrue(false, "Not able to select fund value from invest,ment dropdown");
			}
		} else {
			appLog.info("Not able to click on investment tab");
			sa.assertTrue(false, "Not able to click on investment tab");
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc029_AcceptDisclaimerForFund2() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		DisclaimerPageBussinessLayer dp = new DisclaimerPageBussinessLayer(driver);
		AllFirmsPageBusinesslayer ap = new AllFirmsPageBusinesslayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		SoftAssert sa = new SoftAssert();
		String childChildWindow = null;
		String childId;
		lp.investorLogin(M4C1Email, adminPassword);
		if (click(driver, bp.getPendingDisclaimerPopGoToDisclaimerButton(60), "Go to disclaimer button",
				action.SCROLLANDBOOLEAN)) {
			appLog.info("Clicked on go to discalimer button");
			String parentID = switchOnWindow(driver);
			if (parentID != null) {
				if (dp.clickOnExpandIcon(M4F2, 60)) {
					if (dp.clickOnDisclaimerAcceptButton(M4DIS1, false, 60)) {
						List<WebElement> totalFundRow = FindElements(driver,
								"//div[@class='contacts_n_name_div_Investment']", "Total FundsRow");
						if (totalFundRow.size() == 1) {
							appLog.info("1 fund is displaying");
						} else {
							appLog.info("1 Fund is not displaying");
							sa.assertTrue(false, "1 Fund is not displaying");
						}
						if (dp.verifyFundDisplaying(M4F1, Org3FirmName)) {
							appLog.info(M4F1 + "Fund is displaying");
						} else {
							appLog.info(M4F1 + "Fund is not displaying and fund name is not verified");
							sa.assertTrue(false, M4F1 + "Fund Is Not displaying and fund name is not verified");
						}
						if (click(driver, ap.getNavatarLogo(60), "Navatar logo", action.SCROLLANDBOOLEAN)) {
							if (click(driver, dp.getGoToDisclaimerPopupCancelButton(60), "Cancel button",
									action.SCROLLANDBOOLEAN)) {
								String[] documentNameStandardFolder = ExcelUtils
										.readData("filepath", excelLabel.TestCases_Name,
												"M4tc026_CreateBothWorkspaceForFund2AndVerifySelectFundPicklist",
												excelLabel.UploadedFileStandard)
										.split("<break>");
								if (dp.clickOnDocument("Potential Investment", M4F2, documentNameStandardFolder[0], 60,
										ap.getScrollBox(60))) {
									childId = driver.getWindowHandle();
									System.err.println("2nd window: " + childId);
									Set<String> allWindows = driver.getWindowHandles();
									int i = 0;
									for (String string : allWindows) {
										System.err.println("IDs " + (++i) + ": " + string);
									}
									Iterator<String> i1 = allWindows.iterator();
									while (i1.hasNext()) {
										childChildWindow = i1.next();
										if (!childChildWindow.equalsIgnoreCase(parentID)) {
											if (!childChildWindow.equalsIgnoreCase(childId)) {
												driver.switchTo().window(childChildWindow);
												if (isDisplayed(driver, ifp.getDocumentDownloadBtn(60), "Visibility",
														60, "Document Download button") != null) {
													appLog.info("Document download button is  displaying");
												} else {
													appLog.info("Document download button is not displaying");
													sa.assertTrue(false, "Document download button is not displaying");
												}
												driver.close();
											}
										}
									}
									driver.switchTo().window(childId);
								} else {
									appLog.info("Not able to click on document name");
									sa.assertTrue(false, "Not able to click on document name");
								}
								documentNameStandardFolder = ExcelUtils.readData("filepath", excelLabel.TestCases_Name,
										"M4tc007_BuildWorkspaceForF1AndVerifyNIAddOn", excelLabel.UploadedFileStandard)
										.split("<break>");
								if (dp.clickOnDocument("Potential Investment", M4F1, documentNameStandardFolder[0], 60,
										ap.getScrollBox(60))) {
									childId = driver.getWindowHandle();
									System.err.println("2nd window: " + childId);
									Set<String> allWindows = driver.getWindowHandles();
									int i = 0;
									for (String string : allWindows) {
										System.err.println("IDs " + (++i) + ": " + string);
									}
									Iterator<String> i1 = allWindows.iterator();
									while (i1.hasNext()) {
										childChildWindow = i1.next();
										if (!childChildWindow.equalsIgnoreCase(parentID)) {
											if (!childChildWindow.equalsIgnoreCase(childId)) {
												driver.switchTo().window(childChildWindow);
												if (click(driver, dp.getGoToDisclaimerButton(60),
														"Go to Disclaimer Button", action.SCROLLANDBOOLEAN)) {
													totalFundRow = FindElements(driver,
															"//div[@class='contacts_n_name_div_Investment']",
															"Total FundsRow");
													if (totalFundRow.size() == 1) {
														appLog.info("1 fund is displaying");
													} else {
														appLog.info("1 Fund is not displaying");
														sa.assertTrue(false, "1 Fund is not displaying");
													}
													if (dp.verifyFundDisplaying(M4F1, Org3FirmName)) {
														appLog.info(M4F1 + "Fund is displaying");
													} else {
														appLog.info(M4F1
																+ "Fund is not displaying and fund name is not verified");
														sa.assertTrue(false, M4F1
																+ "Fund Is Not displaying and fund name is not verified");
													}
												} else {
													appLog.info("Not able to click on go to discalimer button");
													sa.assertTrue(false,
															"Not able to click on go to discalimer button");
												}
												driver.close();
											}
										}
									}
									driver.switchTo().window(childId);
								} else {
									appLog.info("Not able to click on document name");
									sa.assertTrue(false, "Not able to click on document name");
								}
							} else {
								appLog.info("Not able to click on cancel button");
								sa.assertTrue(false, "Not able ot click on cancel button");
							}
						} else {
							appLog.info("Not able to click on navatar logo");
							sa.assertTrue(false, "Not able to click on navatar logo");
						}
					} else {
						appLog.info("Not able to click on accept button");
						sa.assertTrue(false, "Not able to click on accept button");
					}
				} else {
					appLog.info("Not able to click on expand icon");
					sa.assertTrue(false, "Not able to click on expand icon");
				}
				driver.close();
				driver.switchTo().window(parentID);
			} else {
				appLog.info("No new window open");
				sa.assertTrue(false, "No new window is open");
			}
		} else {
			appLog.info("Not able to click on go to discalimer button");
			sa.assertTrue(false, "Not able to click on go to disclaimer button");
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc030_RemoveCompleteAccessForContact1AndContact2ForFund2() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		String[] stdPath = ExcelUtils
				.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath)
				.split(",");
		SoftAssert sa = new SoftAssert();
		lp.CRMLogin(Org3CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(environment, mode,TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(environment, mode,M4F2)) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
						"FundRaising View.");
				if (fp.verifyFolderPathdummy(stdPath[0], M4I1, null, M4F2, PageName.FundsPage,
						Workspace.FundraisingWorkspace, 60)) {
					if (fp.revokeContactAccess(M4C1Email, Workspace.FundraisingWorkspace)) {
						appLog.info("Contact access for contact 1 get removed successfully");
					} else {
						appLog.info("Contact access for contact 1 is not removed successfully");
						sa.assertTrue(false, "Contact access for contact 1 is not removed successfully");
					}
					if (fp.revokeContactAccess(M4C2Email, Workspace.FundraisingWorkspace)) {
						appLog.info("Contact access for contact 2 get removed successfully");
					} else {
						appLog.info("Contact access for contact 2 is not removed successfully");
						sa.assertTrue(false, "Contact access for contact 2 is not removed successfully");
					}
				} else {
					appLog.info("Folder structure is not verified");
					sa.assertTrue(false, "Folder structure is not verified");
				}
				if (fp.verifyFolderPathdummy(stdPath[1], M4I1, null, M4F2, PageName.FundsPage,
						Workspace.FundraisingWorkspace, 60)) {
					if (fp.revokeContactAccess(M4C1Email, Workspace.FundraisingWorkspace)) {
						appLog.info("Contact access for contact 1 get removed successfully");
					} else {
						appLog.info("Contact access for contact 1 is not removed successfully");
						sa.assertTrue(false, "Contact access for contact 1 is not removed successfully");
					}
					if (fp.revokeContactAccess(M4C2Email, Workspace.FundraisingWorkspace)) {
						appLog.info("Contact access for contact 2 get removed successfully");
					} else {
						appLog.info("Contact access for contact 2 is not removed successfully");
						sa.assertTrue(false, "Contact access for contact 2 is not removed successfully");
					}
				} else {
					appLog.info("Folder structure is not verified");
					sa.assertTrue(false, "Folder structure is not verified");
				}
				switchToDefaultContent(driver);
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
						"InvestorWorkspace View.");
				if (fp.verifyFolderPathdummy(stdPath[0], M4I3, M4LP1, M4F2, PageName.FundsPage,
						Workspace.InvestorWorkspace, 60)) {
					if (fp.revokeContactAccess(M4C1Email, Workspace.InvestorWorkspace)) {
						appLog.info("Contact access for contact 1 get removed successfully");
					} else {
						appLog.info("Contact access for contact 1 is not removed successfully");
						sa.assertTrue(false, "Contact access for contact 1 is not removed successfully");
					}
					ThreadSleep(5000);
					if (fp.revokeContactAccess(M4C2Email, Workspace.InvestorWorkspace)) {
						appLog.info("Contact access for contact 2 get removed successfully");
					} else {
						appLog.info("Contact access for contact 2 is not removed successfully");
						sa.assertTrue(false, "Contact access for contact 2 is not removed successfully");
					}
				} else {
					appLog.info("Folder structure is not verified");
					sa.assertTrue(false, "Folder structure is not verified");
				}
				if (fp.verifyFolderPathdummy(stdPath[1], M4I3, M4LP1, M4F2, PageName.FundsPage,
						Workspace.InvestorWorkspace, 60)) {
					if (fp.revokeContactAccess(M4C1Email, Workspace.InvestorWorkspace)) {
						appLog.info("Contact access for contact 1 get removed successfully");
					} else {
						appLog.info("Contact access for contact 1 is not removed successfully");
						sa.assertTrue(false, "Contact access for contact 1 is not removed successfully");
					}
					if (fp.revokeContactAccess(M4C2Email, Workspace.InvestorWorkspace)) {
						appLog.info("Contact access for contact 2 get removed successfully");
					} else {
						appLog.info("Contact access for contact 2 is not removed successfully");
						sa.assertTrue(false, "Contact access for contact 2 is not removed successfully");
					}
				} else {
					appLog.info("Folder structure is not verified");
					sa.assertTrue(false, "Folder structure is not verified");
				}
			} else {
				appLog.info("Not able to click on created fund");
				sa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on funds tab");
			sa.assertTrue(false, "Not able to click on funds tab ");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc030_RemoveCompleteAccessForContact1AndContact2ForFund2AndVerifyNavatarInvestorAddOnsTab() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if (bp.clickOnTab(TabName.NavatarInvestorAddOns)) {
			switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60));
			switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
			if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60), "Select Fund Dropdown", M4F1)) {
				if (niam.verifyDisclaimerGrid(DisclaimerGrid.Deactivate, M4DIS2, DisclaimerGrid.View,
						DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
					appLog.info("content grid is verfied successfully.");
				} else {
					appLog.error("Content grid is not verfied.");
					sa.assertTrue(false, "Content grid is not verfied.");
				}
				if (niam.verifyDisclaimerGrid(DisclaimerGrid.Activate, M4DIS1, DisclaimerGrid.View,
						DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
					appLog.info("content grid is verfied successfully.");
				} else {
					appLog.error("Content grid is not verfied.");
					sa.assertTrue(false, "Content grid is not verfied.");
				}
				int recordCount = niam.getDisclaimerRecordCount();
				if (recordCount >= 2) {
					appLog.info("Record count is verified");
				} else {
					appLog.info("Record count is not verified");
					sa.assertTrue(false, "Record Count is not verified");
				}
				String activeDisclaimerStatistics = niam.getActiveDisclaimerStatisticsValue();
				if (activeDisclaimerStatistics.equalsIgnoreCase(M4DIS2)) {
					appLog.info("Disclaimer 2 is displaying");
				} else {
					appLog.info("Discalimer 2 is not displaying");
					sa.assertTrue(false, "Disclaimer 2 is not displaying");
				}
				int waitingRecordCount = niam.getWaitingDisclaimerCount();
				if (waitingRecordCount == 2) {
					appLog.info("Waiting disclaimer count is veroified");
				} else {
					appLog.info("Waiting Disclaimer count is not verified");
					sa.assertTrue(false, "Waiting disclaimer count is not verified");
				}
				int AcceptedRecordCount = niam.getAcceptedDiscalimerCount();
				if (AcceptedRecordCount == 0) {
					appLog.info("Accepted disclaimer count is verified");
				} else {
					appLog.info("Accepted Disclaimer count is not verified");
					sa.assertTrue(false, "Accepted disclaimer count is not verified");
				}
			} else {
				appLog.info("Not able to select fund from dropdown");
				sa.assertTrue(false, "Not able to sleect fund from dropdown");
			}
			if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60), "Select Fund Dropdown", M4F2)) {
				if (niam.verifyDisclaimerGrid(DisclaimerGrid.Deactivate, M4DIS1, DisclaimerGrid.View,
						DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
					appLog.info("content grid is verfied successfully.");
				} else {
					appLog.error("Content grid is not verfied.");
					sa.assertTrue(false, "Content grid is not verfied.");
				}
				int recordCount = niam.getDisclaimerRecordCount();
				if (recordCount >= 1) {
					appLog.info("Record count is verified");
				} else {
					appLog.info("Record count is not verified");
					sa.assertTrue(false, "Record Count is not verified");
				}
				String activeDisclaimerStatistics = niam.getActiveDisclaimerStatisticsValue();
				if (activeDisclaimerStatistics.equalsIgnoreCase(M4DIS1)) {
					appLog.info("Disclaimer 1 is displaying");
				} else {
					appLog.info("Discalimer 1 is not displaying");
					sa.assertTrue(false, "Disclaimer 1 is not displaying");
				}
				int waitingRecordCount = niam.getWaitingDisclaimerCount();
				if (waitingRecordCount == 1) {
					appLog.info("Waiting disclaimer count is veroified");
				} else {
					appLog.info("Waiting Disclaimer count is not verified");
					sa.assertTrue(false, "Waiting disclaimer count is not verified");
				}
				int AcceptedRecordCount = niam.getAcceptedDiscalimerCount();
				if (AcceptedRecordCount == 1) {
					appLog.info("Accepted disclaimer count is verified");
				} else {
					appLog.info("Accepted Disclaimer count is not verified");
					sa.assertTrue(false, "Accepted disclaimer count is not verified");
				}
			} else {
				appLog.info("Not able to select fund from dropdown");
				sa.assertTrue(false, "Not able to sleect fund from dropdown");
			}
			if (niam.clickOnViewLinkOfDisclaimer(M4DIS1)) {
				appLog.info("clicked on view link");
				if (niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1, "Accepted",
						getSystemDate("MM/dd/yyyy"))
						|| niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1, "Accepted",
								previousOrForwardDate(-1, "MM/dd/yyyy"))) {
					appLog.info("Discalimer statistics grid is verified for contact:" + M4CFN1 + " " + M4CLN1);
				} else {
					appLog.info("Discalimer statistics grid is not verified for contact:" + M4CFN1 + " " + M4CLN1);
					sa.assertTrue(false, "Discalimer statistics grid is verified for contact:" + M4CFN1 + " " + M4CLN1);
				}
				if (niam.verifyDisclaimerStatisticsGrid(M4CFN2 + " " + M4CLN2, M4C2Email, M4I2, "Waiting", "")) {
					appLog.info("Discalimer statistics grid is verified for contact:" + M4CFN2 + " " + M4CLN2);
				} else {
					appLog.info("Discalimer statistics grid is not verified for contact:" + M4CFN2 + " " + M4CLN2);
					sa.assertTrue(false, "Discalimer statistics grid is verified for contact:" + M4CFN2 + " " + M4CLN2);
				}
				if (niam.getDisclaimerViewPopUpRecordCount() == 2) {
					appLog.info("Disclaimer view popup record cout is verified");
				} else {
					appLog.info("Disclaimer view popup record cout is not verified");
					sa.assertTrue(false, "Disclaimer view popup record cout is not verified");
				}
			} else {
				appLog.info("Not able to click on view link");
				sa.assertTrue(false, "Not able to click on view link");
			}
		} else {
			appLog.info("Not able to click on navatar investor add ons tab");
			sa.assertTrue(false, "Not able to click on navatar invetsor add ons tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc031_AgainInviteSameContactsForFund2() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		String[] stdPath = ExcelUtils
				.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath)
				.split(",");
		lp.CRMLogin(Org3CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M4F2)) {
				if (fp.inviteContact(environment, mode, M4I1, M4C1Email, stdPath[1], FolderType.Standard, "Upload", "Yes",
						"No", null, Workspace.FundraisingWorkspace, null)) {
					appLog.info("Successfully provided access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
				}
				if (fp.inviteContact(environment, mode, M4I1, M4C2Email, stdPath[1], FolderType.Standard, "Upload", "Yes",
						"No", null, Workspace.FundraisingWorkspace, M4C2Email)) {
					appLog.info("Successfully provided access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
				}
				if (fp.inviteContact(environment, mode, M4I1, M4C1Email, stdPath[0], FolderType.Standard, "Upload", "Yes",
						"No", null, Workspace.FundraisingWorkspace, null)) {
					appLog.info("Successfully provided access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
				}
				if (fp.inviteContact(environment, mode, M4I1, M4C2Email, stdPath[0], FolderType.Standard, "Upload", "Yes",
						"No", null, Workspace.FundraisingWorkspace, M4C2Email)) {
					appLog.info("Successfully provided access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
				}
				if (fp.inviteContact(environment, mode, M4I3 + "/" + M4LP1, M4C1Email, stdPath[1], FolderType.Standard,
						"Upload", "Yes", "No", null, Workspace.InvestorWorkspace, M4C1Email)) {
					appLog.info("Successfully provided access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
				}
				if (fp.inviteContact(environment, mode, M4I3 + "/" + M4LP1, M4C2Email, stdPath[1], FolderType.Standard,
						"Upload", "Yes", "No", null, Workspace.InvestorWorkspace, M4C2Email)) {
					appLog.info("Successfully provided access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
				}
				if (fp.inviteContact(environment, mode, M4I3 + "/" + M4LP1, M4C1Email, stdPath[0], FolderType.Standard,
						"Upload", "Yes", "No", null, Workspace.InvestorWorkspace, M4C1Email)) {
					appLog.info("Successfully provided access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
				}
				if (fp.inviteContact(environment, mode, M4I3 + "/" + M4LP1, M4C2Email, stdPath[0], FolderType.Standard,
						"Upload", "Yes", "No", null, Workspace.InvestorWorkspace, M4C2Email)) {
					appLog.info("Successfully provided access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
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
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc031_AgainInviteSameContactsForFund2andCheckImpactatContact1InvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		DisclaimerPageBussinessLayer dp = new DisclaimerPageBussinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.investorLogin(M4C1Email, adminPassword);
		if (isDisplayed(driver, bp.getPendingDisclaimerPopGoToDisclaimerButton(60), "visibility", 30,
				"Go to discliamer popup") != null) {
			appLog.info("go to disclaimer popup is displaying");
			if (click(driver, bp.getPendingDisclaimerPopGoToDisclaimerButton(60), "Go to disclaimer button",
					action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on go to discalimer button");
				String parentID = switchOnWindow(driver);
				if (parentID != null) {
					List<WebElement> totalFundRow = FindElements(driver,
							"//div[@class='contacts_n_name_div_Investment']", "Total FundsRow");
					if (totalFundRow.size() == 1) {
						appLog.info("1 fund is displaying");
					} else {
						appLog.info("1 Fund is not displaying");
						sa.assertTrue(false, "1 Fund is not displaying");
					}
					if (dp.verifyFundDisplaying(M4F1, Org3FirmName)) {
						appLog.info("Fund is displaying");
					} else {
						appLog.info("Fund is not displaying and fund name is not verified");
						sa.assertTrue(false, "Fund Is Not displaying and fund name is not verified");
					}
					List<WebElement> disclaimerNames = FindElements(driver,
							"//div[contains(@id,'filterGridContact')]//b", "DisclaimerNames");
					if (disclaimerNames.isEmpty()) {
						appLog.error("No disclaimer is present on the disclaimer page.");
						sa.assertTrue(false, "No disclaimer is present on the disclaimer page.");
					} else {
						for (int i = 0; i < disclaimerNames.size(); i++) {
							if (trim(getText(driver, disclaimerNames.get(i), "Disclaimer name", action.BOOLEAN))
									.equalsIgnoreCase(M4DIS2)) {
								appLog.info("disclaimerName is verified.");
							} else {
								if (i == disclaimerNames.size()) {
									appLog.error("disclaimer Name is not present on the page.");
									sa.assertTrue(false, "disclaimer Name is not present on the page.");
								}
							}
						}
					}
					if (dp.verifyAcceptButton(M4F1, M4DIS2)) {
						appLog.info("Accept button is displaying for discalimer 2 for fund 1");
					} else {
						appLog.info("Accept button is not displaying for discalimer 2 for fund 1");
						sa.assertTrue(false, "Accept button is not displaying for discalimer 2 for fund 1");
					}
					driver.close();
					driver.switchTo().window(parentID);
				} else {
					appLog.info("No new window open");
					sa.assertTrue(false, "No new window is open");
				}
			} else {
				appLog.info("Not able to click on go to discalimer button");
				sa.assertTrue(false, "Not able to click on go to disclaimer button");
			}
		} else {
			appLog.info("go to disclaimer popup is not displaying");
			sa.assertTrue(false, "go to disclaimer popup is not displaying");
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc031_AgainInviteSameContactsForFund2andCheckImpactatContact2InvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		DisclaimerPageBussinessLayer dp = new DisclaimerPageBussinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.investorLogin(M4C2Email, adminPassword);
		if (isDisplayed(driver, bp.getPendingDisclaimerPopGoToDisclaimerButton(60), "visibility", 30,
				"Go to discliamer popup") != null) {
			appLog.info("go to disclaimer popup is displaying");
			if (click(driver, bp.getPendingDisclaimerPopGoToDisclaimerButton(60), "Go to disclaimer button",
					action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on go to discalimer button");
				String parentID = switchOnWindow(driver);
				if (parentID != null) {
					List<WebElement> totalFundRow = FindElements(driver,
							"//div[@class='contacts_n_name_div_Investment']", "Total FundsRow");
					if (totalFundRow.size() == 2) {
						appLog.info("2 fund are displaying");
					} else {
						appLog.info("2 Fund are not displaying");
						sa.assertTrue(false, "2 Fund are not displaying");
					}
					if (dp.verifyFundDisplaying(M4F1, Org3FirmName)) {
						appLog.info(M4F1 + "Fund is displaying");
					} else {
						appLog.info(M4F1 + "Fund is not displaying and fund name is not verified");
						sa.assertTrue(false, M4F1 + "Fund Is Not displaying and fund name is not verified");
					}
					if (dp.verifyFundDisplaying(M4F2, Org3FirmName)) {
						appLog.info(M4F2 + "Fund is displaying");
					} else {
						appLog.info(M4F2 + "Fund is not displaying and fund name is not verified");
						sa.assertTrue(false, M4F2 + "Fund Is Not displaying and fund name is not verified");
					}
					if (dp.verifyAcceptButton(M4F1, M4DIS2)) {
						appLog.info("Accept button is displaying for discalimer 2 for fund 1");
					} else {
						appLog.info("Accept button is not displaying for discalimer 2 for fund 1");
						sa.assertTrue(false, "Accept button is not displaying for discalimer 2 for fund 1");
					}
					if (dp.verifyAcceptButton(M4F2, M4DIS1)) {
						appLog.info("Accept button is displaying for discalimer 1 for fund 2");
					} else {
						appLog.info("Accept button is not displaying for discalimer 1 for fund 2");
						sa.assertTrue(false, "Accept button is not displaying for discalimer 1 for fund 2");
					}
					driver.close();
					driver.switchTo().window(parentID);
				} else {
					appLog.info("No new window open");
					sa.assertTrue(false, "No new window is open");
				}
			} else {
				appLog.info("Not able to click on go to discalimer button");
				sa.assertTrue(false, "Not able to click on go to disclaimer button");
			}
		} else {
			appLog.info("go to disclaimer popup is not displaying");
			sa.assertTrue(false, "go to disclaimer popup is not displaying");
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc031_AgainInviteSameContactsForFund2andCheckImpactatAdminUserSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if (bp.clickOnTab(TabName.NavatarInvestorAddOns)) {
			switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60));
			switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
			if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60), "Select Fund Dropdown", M4F1)) {
				if (niam.verifyDisclaimerGrid(DisclaimerGrid.Deactivate, M4DIS2, DisclaimerGrid.View,
						DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
					appLog.info("content grid is verfied successfully.");
				} else {
					appLog.error("Content grid is not verfied.");
					sa.assertTrue(false, "Content grid is not verfied.");
				}
				if (niam.verifyDisclaimerGrid(DisclaimerGrid.Activate, M4DIS1, DisclaimerGrid.View,
						DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
					appLog.info("content grid is verfied successfully.");
				} else {
					appLog.error("Content grid is not verfied.");
					sa.assertTrue(false, "Content grid is not verfied.");
				}
				int recordCount = niam.getDisclaimerRecordCount();
				if (recordCount >= 2) {
					appLog.info("Record count is verified");
				} else {
					appLog.info("Record count is not verified");
					sa.assertTrue(false, "Record Count is not verified");
				}
				String activeDisclaimerStatistics = niam.getActiveDisclaimerStatisticsValue();
				if (activeDisclaimerStatistics.equalsIgnoreCase(M4DIS2)) {
					appLog.info("Disclaimer 2 is displaying");
				} else {
					appLog.info("Discalimer 2 is not displaying");
					sa.assertTrue(false, "Disclaimer 2 is not displaying");
				}
				int waitingRecordCount = niam.getWaitingDisclaimerCount();
				if (waitingRecordCount == 2) {
					appLog.info("Waiting disclaimer count is veroified");
				} else {
					appLog.info("Waiting Disclaimer count is not verified");
					sa.assertTrue(false, "Waiting disclaimer count is not verified");
				}
				int AcceptedRecordCount = niam.getAcceptedDiscalimerCount();
				if (AcceptedRecordCount == 0) {
					appLog.info("Accepted disclaimer count is verified");
				} else {
					appLog.info("Accepted Disclaimer count is not verified");
					sa.assertTrue(false, "Accepted disclaimer count is not verified");
				}
			} else {
				appLog.info("Not able to select fund from dropdown");
				sa.assertTrue(false, "Not able to sleect fund from dropdown");
			}
			if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60), "Select Fund Dropdown", M4F2)) {
				if (niam.verifyDisclaimerGrid(DisclaimerGrid.Deactivate, M4DIS1, DisclaimerGrid.View,
						DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
					appLog.info("content grid is verfied successfully.");
				} else {
					appLog.error("Content grid is not verfied.");
					sa.assertTrue(false, "Content grid is not verfied.");
				}
				int recordCount = niam.getDisclaimerRecordCount();
				if (recordCount == 1) {
					appLog.info("Record count is verified");
				} else {
					appLog.info("Record count is not verified");
					sa.assertTrue(false, "Record Count is not verified");
				}
				String activeDisclaimerStatistics = niam.getActiveDisclaimerStatisticsValue();
				if (activeDisclaimerStatistics.equalsIgnoreCase(M4DIS1)) {
					appLog.info("Disclaimer 1 is displaying");
				} else {
					appLog.info("Discalimer 1 is not displaying");
					sa.assertTrue(false, "Disclaimer 1 is not displaying");
				}
				int waitingRecordCount = niam.getWaitingDisclaimerCount();
				if (waitingRecordCount == 1) {
					appLog.info("Waiting disclaimer count is veroified");
				} else {
					appLog.info("Waiting Disclaimer count is not verified");
					sa.assertTrue(false, "Waiting disclaimer count is not verified");
				}
				int AcceptedRecordCount = niam.getAcceptedDiscalimerCount();
				if (AcceptedRecordCount == 1) {
					appLog.info("Accepted disclaimer count is verified");
				} else {
					appLog.info("Accepted Disclaimer count is not verified");
					sa.assertTrue(false, "Accepted disclaimer count is not verified");
				}
			} else {
				appLog.info("Not able to select fund from dropdown");
				sa.assertTrue(false, "Not able to sleect fund from dropdown");
			}
			if (niam.clickOnViewLinkOfDisclaimer(M4DIS1)) {
				appLog.info("clicked on view link");
				if (niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1, "Accepted",
						getSystemDate("MM/dd/yyyy"))
						|| niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1, "Accepted",
								previousOrForwardDate(-1, "MM/dd/yyyy"))) {
					appLog.info("Discalimer statistics grid is verified for contact:" + M4CFN1 + " " + M4CLN1);
				} else {
					appLog.info("Discalimer statistics grid is not verified for contact:" + M4CFN1 + " " + M4CLN1);
					sa.assertTrue(false, "Discalimer statistics grid is verified for contact:" + M4CFN1 + " " + M4CLN1);
				}
				if (niam.verifyDisclaimerStatisticsGrid(M4CFN2 + " " + M4CLN2, M4C2Email, M4I2, "Waiting", "")) {
					appLog.info("Discalimer statistics grid is verified for contact:" + M4CFN2 + " " + M4CLN2);
				} else {
					appLog.info("Discalimer statistics grid is not verified for contact:" + M4CFN2 + " " + M4CLN2);
					sa.assertTrue(false, "Discalimer statistics grid is verified for contact:" + M4CFN2 + " " + M4CLN2);
				}
				if (niam.getDisclaimerViewPopUpRecordCount() == 2) {
					appLog.info("Disclaimer view popup record cout is verified");
				} else {
					appLog.info("Disclaimer view popup record cout is not verified");
					sa.assertTrue(false, "Disclaimer view popup record cout is not verified");
				}
			} else {
				appLog.info("Not able to click on view link");
				sa.assertTrue(false, "Not able to click on view link");
			}
		} else {
			appLog.info("Not able to click on navatar investor add ons tab");
			sa.assertTrue(false, "Not able to click on navatar invetsor add ons tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc032_ManageInvestorsForFund2() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		WebElement ele = null;
		lp.CRMLogin(Org3CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M4F2)) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
						"FundRaising View.");
				if (click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60), "Manage investor icon",
						action.SCROLLANDBOOLEAN)) {
					ele = FindElement(driver, "//div[@title='" + M4I1 + "']/../..//input", "Institution 1 checkbox",
							action.SCROLLANDBOOLEAN, 60);
					switchToDefaultContent(driver);
					switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
					if (click(driver, ele, "Institution1 checkbox", action.BOOLEAN)) {
						switchToDefaultContent(driver);
						switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
						scrollDownThroughWebelement(driver,
								fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
								"FundraisingWorkspace View.");
						if (click(driver,
								fp.getManageInvestorDeletedPopupCloseButton(Workspace.FundraisingWorkspace, 60),
								"Manage Investor Deleted popup Close Button", action.BOOLEAN)) {
							switchToDefaultContent(driver);
							switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
							scrollDownThroughWebelement(driver,
									fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
									"FundraisingWorkspace View.");
							if (click(driver, fp.getManageInvestorDoneButton(Workspace.FundraisingWorkspace, 60),
									"Done Button", action.SCROLLANDBOOLEAN)) {
								switchToDefaultContent(driver);
								switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
								scrollDownThroughWebelement(driver,
										fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
										"FundraisingWorkspace View.");
								if (fp.verifyFolderPathdummy("", M4I1, null, M4F2, PageName.FundsPage,
										Workspace.FundraisingWorkspace, 20)) {
									appLog.info("Institution 1 folder is displaying in folder Structure");
									sa.assertTrue(false, "Institution 1 folder is displaying in folder Structure");
								} else {
									appLog.info("Institution 1 folder is not displaying in folder Structure");
								}
							} else {
								appLog.info("Not able to click on done button");
								sa.assertTrue(false, "Not able to click on done button");
							}

						} else {
							appLog.info("Not able to click on Close button");
							sa.assertTrue(false, "Not able to click on close button");
						}
					} else {
						appLog.info("Not able to click on checkbox");
						sa.assertTrue(false, "Nt able ot click on institution 1 checkbox");
					}
				} else {
					appLog.info("Not able to click on manage investor icon");
					sa.assertTrue(false, "Not able to click on manage investor icon");
				}
				switchToDefaultContent(driver);
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
						"InvestorWorkspace View.");
				if (click(driver, fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60), "Manage Investor Icon",
						action.SCROLLANDBOOLEAN)) {
					switchToDefaultContent(driver);
					switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
							"InvestorWorkspace View.");
					if (click(driver, fp.getLimitedPartnerCheckBox(M4I3, M4LP1, Workspace.InvestorWorkspace, 60),
							"LP1 checkbox", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getManageInvestorDeletedPopupCloseButton(Workspace.InvestorWorkspace, 60),
								"Manage Investor Deleted popup Close Button", action.BOOLEAN)) {
							switchToDefaultContent(driver);
							switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
							scrollDownThroughWebelement(driver,
									fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
									"InvestorWorkspace View.");
							if (click(driver, fp.getManageInvestorDoneButton(Workspace.InvestorWorkspace, 60),
									"Invetsor workspace", action.SCROLLANDBOOLEAN)) {
								switchToDefaultContent(driver);
								switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
								scrollDownThroughWebelement(driver,
										fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
										"InvestorWorkspace View.");
								if (fp.verifyFolderPathdummy("", M4I3, M4LP1, M4F2, PageName.FundsPage,
										Workspace.InvestorWorkspace, 20)) {
									appLog.info("LP is displaying in folder Structure");
									sa.assertTrue(false, "LP is displaying in folder Structure");
								} else {
									appLog.info("LP is not displaying in folder Structure");
								}
							} else {
								appLog.info("Not able to click on done button");
								sa.assertTrue(false, "Not able to click on done button");
							}
						} else {
							appLog.info("Not able to click on Close button");
							sa.assertTrue(false, "Not able to click on close button");
						}
					} else {
						appLog.info("Not able to click on LP1 checkbox");
						sa.assertTrue(false, "Not able to click on LP1 checkbox");
					}
				} else {
					appLog.info("Not able to click on manage investor icon");
					sa.assertTrue(false, "Not able to click on manage investor icon");
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
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc032_ManageInvestorsForFund2AndCheckImpactOnAdminUserSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if (bp.clickOnTab(TabName.NavatarInvestorAddOns)) {
			switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60));
			switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
			if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60), "Select Fund Dropdown", M4F1)) {
				if (niam.verifyDisclaimerGrid(DisclaimerGrid.Deactivate, M4DIS2, DisclaimerGrid.View,
						DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
					appLog.info("content grid is verfied successfully.");
				} else {
					appLog.error("Content grid is not verfied.");
					sa.assertTrue(false, "Content grid is not verfied.");
				}
				if (niam.verifyDisclaimerGrid(DisclaimerGrid.Activate, M4DIS1, DisclaimerGrid.View,
						DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
					appLog.info("content grid is verfied successfully.");
				} else {
					appLog.error("Content grid is not verfied.");
					sa.assertTrue(false, "Content grid is not verfied.");
				}
				int recordCount = niam.getDisclaimerRecordCount();
				if (recordCount >= 2) {
					appLog.info("Record count is verified");
				} else {
					appLog.info("Record count is not verified");
					sa.assertTrue(false, "Record Count is not verified");
				}
				String activeDisclaimerStatistics = niam.getActiveDisclaimerStatisticsValue();
				if (activeDisclaimerStatistics.equalsIgnoreCase(M4DIS2)) {
					appLog.info("Disclaimer 2 is displaying");
				} else {
					appLog.info("Discalimer 2 is not displaying");
					sa.assertTrue(false, "Disclaimer 2 is not displaying");
				}
				int waitingRecordCount = niam.getWaitingDisclaimerCount();
				if (waitingRecordCount == 2) {
					appLog.info("Waiting disclaimer count is veroified");
				} else {
					appLog.info("Waiting Disclaimer count is not verified");
					sa.assertTrue(false, "Waiting disclaimer count is not verified");
				}
				int AcceptedRecordCount = niam.getAcceptedDiscalimerCount();
				if (AcceptedRecordCount == 0) {
					appLog.info("Accepted disclaimer count is verified");
				} else {
					appLog.info("Accepted Disclaimer count is not verified");
					sa.assertTrue(false, "Accepted disclaimer count is not verified");
				}
			} else {
				appLog.info("Not able to select fund from dropdown");
				sa.assertTrue(false, "Not able to sleect fund from dropdown");
			}
			if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60), "Select Fund Dropdown", M4F2)) {
				if (niam.verifyDisclaimerGrid(DisclaimerGrid.Deactivate, M4DIS1, DisclaimerGrid.View,
						DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
					appLog.info("content grid is verfied successfully.");
				} else {
					appLog.error("Content grid is not verfied.");
					sa.assertTrue(false, "Content grid is not verfied.");
				}
				int recordCount = niam.getDisclaimerRecordCount();
				if (recordCount == 1) {
					appLog.info("Record count is verified");
				} else {
					appLog.info("Record count is not verified");
					sa.assertTrue(false, "Record Count is not verified");
				}
				String activeDisclaimerStatistics = niam.getActiveDisclaimerStatisticsValue();
				if (activeDisclaimerStatistics.equalsIgnoreCase(M4DIS1)) {
					appLog.info("Disclaimer 1 is displaying");
				} else {
					appLog.info("Discalimer 1 is not displaying");
					sa.assertTrue(false, "Disclaimer 1 is not displaying");
				}
				int waitingRecordCount = niam.getWaitingDisclaimerCount();
				if (waitingRecordCount == 1) {
					appLog.info("Waiting disclaimer count is veroified");
				} else {
					appLog.info("Waiting Disclaimer count is not verified");
					sa.assertTrue(false, "Waiting disclaimer count is not verified");
				}
				int AcceptedRecordCount = niam.getAcceptedDiscalimerCount();
				if (AcceptedRecordCount == 1) {
					appLog.info("Accepted disclaimer count is verified");
				} else {
					appLog.info("Accepted Disclaimer count is not verified");
					sa.assertTrue(false, "Accepted disclaimer count is not verified");
				}
			} else {
				appLog.info("Not able to select fund from dropdown");
				sa.assertTrue(false, "Not able to sleect fund from dropdown");
			}
			if (niam.clickOnViewLinkOfDisclaimer(M4DIS1)) {
				appLog.info("clicked on view link");
				if (niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1, "Accepted",
						getSystemDate("MM/dd/yyyy"))
						|| niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1, "Accepted",
								previousOrForwardDate(-1, "MM/dd/yyyy"))) {
					appLog.info("Discalimer statistics grid is verified for contact:" + M4CFN1 + " " + M4CLN1);
				} else {
					appLog.info("Discalimer statistics grid is not verified for contact:" + M4CFN1 + " " + M4CLN1);
					sa.assertTrue(false, "Discalimer statistics grid is verified for contact:" + M4CFN1 + " " + M4CLN1);
				}
				if (niam.verifyDisclaimerStatisticsGrid(M4CFN2 + " " + M4CLN2, M4C2Email, M4I2, "Waiting", "")) {
					appLog.info("Discalimer statistics grid is verified for contact:" + M4CFN2 + " " + M4CLN2);
				} else {
					appLog.info("Discalimer statistics grid is not verified for contact:" + M4CFN2 + " " + M4CLN2);
					sa.assertTrue(false, "Discalimer statistics grid is verified for contact:" + M4CFN2 + " " + M4CLN2);
				}
				if (niam.getDisclaimerViewPopUpRecordCount() == 2) {
					appLog.info("Disclaimer view popup record cout is verified");
				} else {
					appLog.info("Disclaimer view popup record cout is not verified");
					sa.assertTrue(false, "Disclaimer view popup record cout is not verified");
				}
			} else {
				appLog.info("Not able to click on view link");
				sa.assertTrue(false, "Not able to click on view link");
			}
		} else {
			appLog.info("Not able to click on navatar investor add ons tab");
			sa.assertTrue(false, "Not able to click on navatar invetsor add ons tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc033_ManageInvestorsForFund2AndInviteContacts() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		String[] stdPath = ExcelUtils
				.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath)
				.split(",");
		lp.CRMLogin(Org3CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M4F2)) {
				if (fp.inviteContact(environment, mode, M4I2, M4C1Email, stdPath[1], FolderType.Standard, "Upload", "Yes",
						"No", null, Workspace.FundraisingWorkspace, M4C1Email)) {
					appLog.info("Successfully provided access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
				}
				if (fp.inviteContact(environment, mode, M4I2, M4C2Email, stdPath[1], FolderType.Standard, "Upload", "Yes",
						"No", null, Workspace.FundraisingWorkspace, null)) {
					appLog.info("Successfully provided access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
				}
				if (fp.inviteContact(environment, mode, M4I2, M4C1Email, stdPath[0], FolderType.Standard, "Upload", "Yes",
						"No", null, Workspace.FundraisingWorkspace, M4C1Email)) {
					appLog.info("Successfully provided access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
				}
				if (fp.inviteContact(environment, mode, M4I2, M4C2Email, stdPath[0], FolderType.Standard, "Upload", "Yes",
						"No", null, Workspace.FundraisingWorkspace, null)) {
					appLog.info("Successfully provided access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
				}
				switchToDefaultContent(driver);
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
						"InvestorWorkspace View.");
				if (click(driver, fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60), "Manage Investor Icon",
						action.SCROLLANDBOOLEAN)) {
					switchToDefaultContent(driver);
					switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
							"InvestorWorkspace View.");
					if (click(driver, fp.getLimitedPartnerCheckBox(M4I3, M4LP1, Workspace.InvestorWorkspace, 60),
							"LP1 checkbox", action.SCROLLANDBOOLEAN)) {
						if (click(driver, fp.getManageInvestorAddedPopupCloseButton(Workspace.InvestorWorkspace, 60),
								"Manage Investor added popup Close Button", action.BOOLEAN)) {
							switchToDefaultContent(driver);
							switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
							scrollDownThroughWebelement(driver,
									fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
									"InvestorWorkspace View.");
							if (click(driver, fp.getManageInvestorDoneButton(Workspace.InvestorWorkspace, 60),
									"Investor workspace", action.SCROLLANDBOOLEAN)) {
								switchToDefaultContent(driver);
								switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 60));
								scrollDownThroughWebelement(driver,
										fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
										"InvestorWorkspace View.");
								if (fp.inviteContact(environment, mode, M4I3 + "/" + M4LP1, M4C1Email,
										stdPath[1], FolderType.Standard, "Upload", "Yes", "No", null, Workspace.InvestorWorkspace, M4C1Email)) {
									appLog.info(
											"Successfully provided access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
								} else {
									appLog.error(
											"Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
									sa.assertTrue(false,
											"Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
								}
								if (fp.inviteContact(environment, mode, M4I3 + "/" + M4LP1, M4C2Email,
										stdPath[1], FolderType.Standard, "Upload", "Yes", "No", null, Workspace.InvestorWorkspace, M4C2Email)) {
									appLog.info(
											"Successfully provided access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
								} else {
									appLog.error(
											"Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
									sa.assertTrue(false,
											"Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
								}
								if (fp.inviteContact(environment, mode, M4I3 + "/" + M4LP1, M4C1Email,
										stdPath[0], FolderType.Standard, "Upload", "Yes", "No", null, Workspace.InvestorWorkspace, M4C1Email)) {
									appLog.info(
											"Successfully provided access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
								} else {
									appLog.error(
											"Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
									sa.assertTrue(false,
											"Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
								}
								if (fp.inviteContact(environment, mode, M4I3 + "/" + M4LP1, M4C2Email,
										stdPath[0], FolderType.Standard, "Upload", "Yes", "No", null, Workspace.InvestorWorkspace, M4C2Email)) {
									appLog.info(
											"Successfully provided access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
								} else {
									appLog.error(
											"Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
									sa.assertTrue(false,
											"Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
								}
							} else {
								appLog.info("Not able to click on done button");
								sa.assertTrue(false, "Not able to clcik on done button");
							}
						} else {
							appLog.info("Not able to click on manage investor added popup close button");
							sa.assertTrue(false, "Not able to click on manage investor added popup close button");
						}
					} else {
						appLog.info("Not able to click on limited partner checkbox");
						sa.assertTrue(false, "Not able to click on limited partenr checkbox");
					}
				} else {
					appLog.info("Not able to click on manage investor icon");
					sa.assertTrue(false, "Not able to click on manage investor icon");
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
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc033_ManageInvestorsForFund2AndInviteContactsAndCheckImpactAtContact1Side() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		DisclaimerPageBussinessLayer dp = new DisclaimerPageBussinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.investorLogin(M4C1Email, adminPassword);
		if (isDisplayed(driver, bp.getPendingDisclaimerPopGoToDisclaimerButton(60), "visibility", 30,
				"Go to discliamer popup") != null) {
			appLog.info("go to disclaimer popup is displaying");
			if (click(driver, bp.getPendingDisclaimerPopGoToDisclaimerButton(60), "Go to disclaimer button",
					action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on go to discalimer button");
				String parentID = switchOnWindow(driver);
				if (parentID != null) {
					List<WebElement> totalFundRow = FindElements(driver,
							"//div[@class='contacts_n_name_div_Investment']", "Total FundsRow");
					if (totalFundRow.size() == 1) {
						appLog.info("1 fund is displaying");
					} else {
						appLog.info("1 Fund is not displaying");
						sa.assertTrue(false, "1 Fund is not displaying");
					}
					if (dp.verifyFundDisplaying(M4F1, Org3FirmName)) {
						appLog.info("Fund is displaying");
					} else {
						appLog.info("Fund is not displaying and fund name is not verified");
						sa.assertTrue(false, "Fund Is Not displaying and fund name is not verified");
					}
					List<WebElement> disclaimerNames = FindElements(driver,
							"//div[contains(@id,'filterGridContact')]//b", "DisclaimerNames");
					if (disclaimerNames.isEmpty()) {
						appLog.error("No disclaimer is present on the disclaimer page.");
						sa.assertTrue(false, "No disclaimer is present on the disclaimer page.");
					} else {
						for (int i = 0; i < disclaimerNames.size(); i++) {
							if (trim(getText(driver, disclaimerNames.get(i), "Disclaimer name", action.BOOLEAN))
									.equalsIgnoreCase(M4DIS2)) {
								appLog.info("disclaimerName is verified.");
							} else {
								if (i == disclaimerNames.size()) {
									appLog.error("disclaimer Name is not present on the page.");
									sa.assertTrue(false, "disclaimer Name is not present on the page.");
								}
							}
						}
					}
					if (dp.verifyAcceptButton(M4F1, M4DIS2)) {
						appLog.info("Accept button is displaying for discalimer 2 for fund 1");
					} else {
						appLog.info("Accept button is not displaying for discalimer 2 for fund 1");
						sa.assertTrue(false, "Accept button is not displaying for discalimer 2 for fund 1");
					}
					driver.close();
					driver.switchTo().window(parentID);
				} else {
					appLog.info("No new window open");
					sa.assertTrue(false, "No new window is open");
				}
			} else {
				appLog.info("Not able to click on go to discalimer button");
				sa.assertTrue(false, "Not able to click on go to disclaimer button");
			}
		} else {
			appLog.info("go to disclaimer popup is not displaying");
			sa.assertTrue(false, "go to disclaimer popup is not displaying");
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc033_ManageInvestorsForFund2AndInviteContactsAndCheckImpactAtContact2Side() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		DisclaimerPageBussinessLayer dp = new DisclaimerPageBussinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.investorLogin(M4C2Email, adminPassword);
		if (isDisplayed(driver, bp.getPendingDisclaimerPopGoToDisclaimerButton(60), "visibility", 30,
				"Go to discliamer popup") != null) {
			appLog.info("go to disclaimer popup is displaying");
			if (click(driver, bp.getPendingDisclaimerPopGoToDisclaimerButton(60), "Go to disclaimer button",
					action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on go to discalimer button");
				String parentID = switchOnWindow(driver);
				if (parentID != null) {
					List<WebElement> totalFundRow = FindElements(driver,
							"//div[@class='contacts_n_name_div_Investment']", "Total FundsRow");
					if (totalFundRow.size() == 2) {
						appLog.info("2 fund are displaying");
					} else {
						appLog.info("2 Fund are not displaying");
						sa.assertTrue(false, "2 Fund are not displaying");
					}
					if (dp.verifyFundDisplaying(M4F1, Org3FirmName)) {
						appLog.info(M4F1 + "Fund is displaying");
					} else {
						appLog.info(M4F1 + "Fund is not displaying and fund name is not verified");
						sa.assertTrue(false, M4F1 + "Fund Is Not displaying and fund name is not verified");
					}
					if (dp.verifyFundDisplaying(M4F2, Org3FirmName)) {
						appLog.info(M4F2 + "Fund is displaying");
					} else {
						appLog.info(M4F2 + "Fund is not displaying and fund name is not verified");
						sa.assertTrue(false, M4F2 + "Fund Is Not displaying and fund name is not verified");
					}
					if (dp.verifyAcceptButton(M4F1, M4DIS2)) {
						appLog.info("Accept button is displaying for discalimer 2 for fund 1");
					} else {
						appLog.info("Accept button is not displaying for discalimer 2 for fund 1");
						sa.assertTrue(false, "Accept button is not displaying for discalimer 2 for fund 1");
					}
					if (dp.verifyAcceptButton(M4F2, M4DIS1)) {
						appLog.info("Accept button is displaying for discalimer 1 for fund 2");
					} else {
						appLog.info("Accept button is not displaying for discalimer 1 for fund 2");
						sa.assertTrue(false, "Accept button is not displaying for discalimer 1 for fund 2");
					}
					driver.close();
					driver.switchTo().window(parentID);
				} else {
					appLog.info("No new window open");
					sa.assertTrue(false, "No new window is open");
				}
			} else {
				appLog.info("Not able to click on go to discalimer button");
				sa.assertTrue(false, "Not able to click on go to disclaimer button");
			}
		} else {
			appLog.info("go to disclaimer popup is not displaying");
			sa.assertTrue(false, "go to disclaimer popup is not displaying");
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc033_ManageInvestorsForFund2AndInviteContactsAndCheckImpactAtAdminUserSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if (bp.clickOnTab(TabName.NavatarInvestorAddOns)) {
			switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60));
			switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
			if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60), "Select Fund Dropdown", M4F1)) {
				if (niam.verifyDisclaimerGrid(DisclaimerGrid.Deactivate, M4DIS2, DisclaimerGrid.View,
						DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
					appLog.info("content grid is verfied successfully.");
				} else {
					appLog.error("Content grid is not verfied.");
					sa.assertTrue(false, "Content grid is not verfied.");
				}
				if (niam.verifyDisclaimerGrid(DisclaimerGrid.Activate, M4DIS1, DisclaimerGrid.View,
						DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
					appLog.info("content grid is verfied successfully.");
				} else {
					appLog.error("Content grid is not verfied.");
					sa.assertTrue(false, "Content grid is not verfied.");
				}
				int recordCount = niam.getDisclaimerRecordCount();
				if (recordCount >= 2) {
					appLog.info("Record count is verified");
				} else {
					appLog.info("Record count is not verified");
					sa.assertTrue(false, "Record Count is not verified");
				}
				String activeDisclaimerStatistics = niam.getActiveDisclaimerStatisticsValue();
				if (activeDisclaimerStatistics.equalsIgnoreCase(M4DIS2)) {
					appLog.info("Disclaimer 2 is displaying");
				} else {
					appLog.info("Discalimer 2 is not displaying");
					sa.assertTrue(false, "Disclaimer 2 is not displaying");
				}
				int waitingRecordCount = niam.getWaitingDisclaimerCount();
				if (waitingRecordCount == 2) {
					appLog.info("Waiting disclaimer count is veroified");
				} else {
					appLog.info("Waiting Disclaimer count is not verified");
					sa.assertTrue(false, "Waiting disclaimer count is not verified");
				}
				int AcceptedRecordCount = niam.getAcceptedDiscalimerCount();
				if (AcceptedRecordCount == 0) {
					appLog.info("Accepted disclaimer count is verified");
				} else {
					appLog.info("Accepted Disclaimer count is not verified");
					sa.assertTrue(false, "Accepted disclaimer count is not verified");
				}
			} else {
				appLog.info("Not able to select fund from dropdown");
				sa.assertTrue(false, "Not able to sleect fund from dropdown");
			}
			if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60), "Select Fund Dropdown", M4F2)) {
				if (niam.verifyDisclaimerGrid(DisclaimerGrid.Deactivate, M4DIS1, DisclaimerGrid.View,
						DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
					appLog.info("content grid is verfied successfully.");
				} else {
					appLog.error("Content grid is not verfied.");
					sa.assertTrue(false, "Content grid is not verfied.");
				}
				int recordCount = niam.getDisclaimerRecordCount();
				if (recordCount >= 1) {
					appLog.info("Record count is verified");
				} else {
					appLog.info("Record count is not verified");
					sa.assertTrue(false, "Record Count is not verified");
				}
				String activeDisclaimerStatistics = niam.getActiveDisclaimerStatisticsValue();
				if (activeDisclaimerStatistics.equalsIgnoreCase(M4DIS1)) {
					appLog.info("Disclaimer 1 is displaying");
				} else {
					appLog.info("Discalimer 1 is not displaying");
					sa.assertTrue(false, "Disclaimer 1 is not displaying");
				}
				int waitingRecordCount = niam.getWaitingDisclaimerCount();
				if (waitingRecordCount == 1) {
					appLog.info("Waiting disclaimer count is veroified");
				} else {
					appLog.info("Waiting Disclaimer count is not verified");
					sa.assertTrue(false, "Waiting disclaimer count is not verified");
				}
				int AcceptedRecordCount = niam.getAcceptedDiscalimerCount();
				if (AcceptedRecordCount == 1) {
					appLog.info("Accepted disclaimer count is verified");
				} else {
					appLog.info("Accepted Disclaimer count is not verified");
					sa.assertTrue(false, "Accepted disclaimer count is not verified");
				}
			} else {
				appLog.info("Not able to select fund from dropdown");
				sa.assertTrue(false, "Not able to sleect fund from dropdown");
			}
			if (niam.clickOnViewLinkOfDisclaimer(M4DIS1)) {
				appLog.info("clicked on view link");
				if (niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1, "Accepted",
						getSystemDate("MM/dd/yyyy"))
						|| niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1, "Accepted",
								previousOrForwardDate(-1, "MM/dd/yyyy"))) {
					appLog.info("Discalimer statistics grid is verified for contact:" + M4CFN1 + " " + M4CLN1);
				} else {
					appLog.info("Discalimer statistics grid is not verified for contact:" + M4CFN1 + " " + M4CLN1);
					sa.assertTrue(false, "Discalimer statistics grid is verified for contact:" + M4CFN1 + " " + M4CLN1);
				}
				if (niam.verifyDisclaimerStatisticsGrid(M4CFN2 + " " + M4CLN2, M4C2Email, M4I2, "Waiting", "")) {
					appLog.info("Discalimer statistics grid is verified for contact:" + M4CFN2 + " " + M4CLN2);
				} else {
					appLog.info("Discalimer statistics grid is not verified for contact:" + M4CFN2 + " " + M4CLN2);
					sa.assertTrue(false, "Discalimer statistics grid is verified for contact:" + M4CFN2 + " " + M4CLN2);
				}
				if (niam.getDisclaimerViewPopUpRecordCount() == 2) {
					appLog.info("Disclaimer view popup record cout is verified");
				} else {
					appLog.info("Disclaimer view popup record cout is not verified");
					sa.assertTrue(false, "Disclaimer view popup record cout is not verified");
				}
			} else {
				appLog.info("Not able to click on view link");
				sa.assertTrue(false, "Not able to click on view link");
			}
		} else {
			appLog.info("Not able to click on navatar investor add ons tab");
			sa.assertTrue(false, "Not able to click on navatar invetsor add ons tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc021_verifyActivatDisclaimerAndAccept() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if (lp.clickOnTab(TabName.NavatarInvestorAddOns)) {
			System.out.println("inside NavatarInvestorAddonsTab");

			if (switchToFrame(driver, 60, niam.getNavatarInvestorAddOnFrame(60))) {
				appLog.info("Successfully switch to  Parent Navatar add-ons inverstor disclaimer frame.");
				if (switchToFrame(driver, 60, niam.getNavatarInvestorAddOnFrame1(60))) {
					appLog.info("Successfully switched to Child Navatar add-ons inverstor disclaimer frame.");

					if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60),
							"Select fund drop down on Navatar Investor add-ons page", M4F1)) {
						appLog.info(M4F1 + "value is select from dropdown.");

					} else {
						appLog.error(M4F1 + " fund name is not present in the drop down, So not able to select.");
						sa.assertTrue(false,
								M4F1 + " fund name is not present in the drop down, So not able to select.");
					}
					if (niam.activateDisclaimer(M4DIS1, 40)) {
						appLog.info("Disclaimer is successfully activated");
						if (niam.verifyDisclaimerGrid(DisclaimerGrid.Deactivate, M4DIS1, DisclaimerGrid.View,
								DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
							appLog.info("Disclaimer grid has been verified.");

						} else {
							appLog.error("Disclaimer grid not verified.");
							sa.assertTrue(false, "Disclaimer grid not verified.");
						}
						if (niam.getDisclaimerRecordCount() >= 1) {
							appLog.info("record count is verfied and equals to : " + niam.getDisclaimerRecordCount());
						} else {
							appLog.error("record count is not verfied.");
							sa.assertTrue(false, "record count is not verfied.");
						}
						if (niam.getActiveDisclaimerStatisticsValue().equals(M4DIS1)) {
							appLog.info("Active Disclaimer Statics : " + niam.getActiveDisclaimerStatisticsValue()
							+ " is available");
						} else {
							appLog.error("Active Disclaimer Statics value  is not verified.");
							sa.assertTrue(false, "Active Disclaimer Statics value is not verified.");
						}
						if (niam.getAcceptedDiscalimerCount() == 0 && niam.getWaitingDisclaimerCount() == 2) {
							appLog.info("Active Disclaimer Statics status accepted count : "
									+ niam.getAcceptedDiscalimerCount()
									+ " Active Disclaimer Statics status waiting count : "
									+ niam.getWaitingDisclaimerCount() + "has been verified.");
						} else {
							appLog.error(
									"Active Disclaimer Statics status : accepted and waiting count is not verified.");
							sa.assertTrue(false,
									"Active Disclaimer Statics status : accepted and waiting count is not verified.");
						}

						appLog.info(
								"******************* check result when i click on <View Button> against 'Disclaimer 1' **********************");

						if (niam.clickOnViewLinkOfDisclaimer(M4DIS1)) {
							appLog.info("Disclaimer Statistics Popup is opened.");
							String selectedOpt = getSelectedOptionOfDropDown(driver, niam.getDisclaimerNamePickList(80),
									"get selected option from name picklist", "text");
							System.out.println("selected option is  : " + selectedOpt);
							if (selectedOpt.equals(M4DIS1 + " (Active)")) {
								appLog.info(selectedOpt + "is selected in name picklist.");

							} else {
								appLog.error(M4DIS1 + " is not selected in name picklist.");
								sa.assertTrue(false, M4DIS1 + " is not selected in name picklist.");
							}
							if (niam.verifyDisclaimerStatisticsGrid(M4CFN2 + " " + M4CLN2, M4C2Email, M4I2, "Waiting",
									"")) {
								appLog.info(M4CFN2 + " " + M4CLN2 + "contact detail is verfied ");
							} else {
								appLog.error("contact detail is not verfied.");
								sa.assertTrue(false, "contact detail 1 is not verfied.");
							}
							if (niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1, "Waiting",
									"")) {
								appLog.info(M4CFN1 + " " + M4CLN1 + "contact detail is verfied ");
							} else {
								appLog.error("contact detail is not verfied.");
								sa.assertTrue(false, "contact detail 2  is not verfied.");
							}
							if (niam.getDisclaimerViewPopUpRecordCount() == 2) {
								appLog.info("Disclaimer Statistics popup record count is verfied and equals to : "
										+ niam.getDisclaimerViewPopUpRecordCount());
							} else {
								appLog.error("record count is not verfied.");
								sa.assertTrue(false, "record count is not verfied.");
							}

						} else {
							appLog.error("Disclaimer Statistics Popup is not openend.");
							sa.assertTrue(false, "Disclaimer Statistics Popup is not openend.");
						}
					} else {
						appLog.error(M4DIS1 + " Disclaimer is not activated. ");
						sa.assertTrue(false, M4DIS1 + " Disclaimer is not activated. ");
					}
				} else {
					appLog.error("not able to switch to  Child Navatar add-ons inverstor disclaimer frame.");
					sa.assertTrue(false, "not able to switch to Child Navatar add-ons inverstor disclaimer frame.");

				}

			} else {
				appLog.error("not able to switch to  Parent Navatar add-ons inverstor disclaimer frame.");
				sa.assertTrue(false, "not able to switch to Parent  Navatar add-ons inverstor disclaimer frame.");
			}

		} else {
			appLog.error("Navatar investor add on tab cannot be clicked.");
			sa.assertTrue(false, "Navatar investor add on tab cannot be clicked.");
		}
		switchToDefaultContent(driver);
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		appLog.info("******************* login to contact 2 **********************");
		InvestorFirmPageBusinesslayer ifpb = new InvestorFirmPageBusinesslayer(driver);
		niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		String parentID = null;
		if (lp.investorLogin(M4C2Email, adminPassword)) {
			if (matchTitle(driver, "Firm Alerts", 60)) {
				appLog.info("Page title is successfully matched.");
			} else {
				appLog.info("Page title is not matched.");
				sa.assertTrue(false, "Page title is not matched.");
			}
			if (niam.getPendingDisclaimerPopGoToDisclaimerButton(60) != null) {
				appLog.info("Pending Disclaimer Popup is available.");
				if (click(driver, niam.getPendingDisclaimerPopUpCancelBUtton(60),
						"Pending Disclaimer Popup Cancel Button", action.BOOLEAN)) {
					appLog.info("Click on Pending Disclaimer Popup Cancel Button Successfully.");
				} else {
					appLog.info("Not able to click on Pending Disclaimer Popup Cancel Button Successfully.");
					sa.assertTrue(false, "Not able to click on Pending Disclaimer Popup Cancel Button Successfully.");

				}
				String fileName = ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC,
						excelLabel.UploadedFileStandard);
				if (click(driver, ifpb.getAllDocumentsTab(30), "All Document Tab", action.BOOLEAN)) {
					boolean flag = false;
					int i = 0;
					while (true) {
						List<WebElement> ele = ifpb.getAllDocumentHeaderTextList();
						List<WebElement> listOfDoc = ifpb.getAllDocumentFileNameList();
						for (int j = 0; j < listOfDoc.size(); j++) {
							String a = listOfDoc.get(j).getText().trim();
							if (a.equalsIgnoreCase(fileName)) {
								appLog.info(fileName + " is available in the content grid");
								flag = true;

								break;
							}
							if (j == listOfDoc.size() - 1) {
								appLog.error(fileName + "file is not present in the content grid");
							}
						}
						if (flag) {
							break;
						} else {
							i++;
							if (i == 2) {
								appLog.error(fileName + " is not available in the content grid ");
								sa.assertTrue(false, fileName + " is not available in the content grid");
								break;
							}
						}
						click(driver, ele.get(0), "document name column head", action.SCROLLANDBOOLEAN);
					}
					if (flag == true) {
						ThreadSleep(10000);
						if (bp.clickOnDocument(fileName, 60)) {
							appLog.info("File '" + fileName + "'is successfully clicked ");
							parentID = switchOnWindow(driver);
							if (parentID != null) {
								if (bp.verifyAccessDeniedPopUpMessage(PageName.NavatarInvestorAddOnsPage, 30)) {
									appLog.info(
											"access denied popup is available and popup message is successfuly verified");
								} else {
									appLog.error("access denied popup is not available .");
									sa.assertTrue(false, "access denied popup is not available .");
								}
								driver.close();
								driver.switchTo().window(parentID);
							} else {
								appLog.error(
										"New window is not opening after clicking on the file name, So cannot chekc the disclaimer on file.");
								sa.assertTrue(false,
										"New window is not opening after clicking on the file name, So cannot chekc the disclaimer on file.");
							}

						} else {
							appLog.error("File '" + fileName + "'is not clicked");
							sa.assertTrue(false, "File '" + fileName + "'is successfully not clicked");
						}

					} else {
						appLog.error(fileName
								+ " file is not present on all document tab, So cannot check disclaimer pop up on file.");
						sa.assertTrue(false, fileName
								+ " file is not present on all document tab, So cannot check disclaimer pop up on file.");
					}
				} else {
					appLog.error("cannot click on all document tab, so cannot check disclamer pop up on all document.");
					sa.assertTrue(false,
							"cannot click on all document tab, so cannot check disclamer pop up on all document and on documents..");
				}

			} else {
				appLog.info("Pending Disclaimer Popup is available.");
				sa.assertTrue(false, "Pending Disclaimer Popup is not available.");
			}

		} else {
			appLog.info("Investor password is wrong so cannot login.");
			sa.assertTrue(false, "Investor password is wrong so cannot login");

		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		appLog.info("******************* login to contact 1 **********************");
		ifpb = new InvestorFirmPageBusinesslayer(driver);
		niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		DisclaimerPageBussinessLayer dpbl = new DisclaimerPageBussinessLayer(driver);
		String parentID1 = null;
		if (lp.investorLogin(M4C1Email, adminPassword)) {

			if (niam.getPendingDisclaimerPopGoToDisclaimerButton(60) != null) {
				appLog.info("Pending Disclaimer Popup is available.");
				if (click(driver, niam.getPendingDisclaimerPopGoToDisclaimerButton(60),
						"Pending Disclaimer Popup Go to Disclaimer Button", action.BOOLEAN)) {
					appLog.info("Click on Go to Disclaimer Button Successfully.");
					parentID1 = switchOnWindow(driver);
					if (dpbl.clickOnDisclaimerAcceptButton(M4DIS1, true, 60)) {
						appLog.info("Disclaimer is accepted Successfully.");
					} else {
						appLog.info("Disclaimer is not accepted.");
						sa.assertTrue(false, "Disclaimer is not accepted.");
					}
					driver.close();
					driver.switchTo().window(parentID1);
				} else {
					appLog.info("Not able to click on Go to Disclaimer Button .");
					sa.assertTrue(false, "Not able to click on Go to Disclaimer Button.");

				}
			} else {
				appLog.info("Pending Disclaimer Popup is available.");
				sa.assertTrue(false, "Pending Disclaimer Popup is not available.");
			}

		} else {
			appLog.info("Investor password is wrong so cannot login.");
			sa.assertTrue(false, "Investor password is wrong so cannot login");

		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if (lp.clickOnTab(TabName.NavatarInvestorAddOns)) {
			System.out.println("inside NavatarInvestorAddonsTab");

			if (switchToFrame(driver, 60, niam.getNavatarInvestorAddOnFrame(60))) {
				appLog.info("Successfully switch to  Parent Navatar add-ons inverstor disclaimer frame.");
				if (switchToFrame(driver, 60, niam.getNavatarInvestorAddOnFrame1(60))) {
					appLog.info("Successfully switched to Child Navatar add-ons inverstor disclaimer frame.");

					if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60),
							"Select fund drop down on Navatar Investor add-ons page", M4F1)) {
						appLog.info(M4F1 + "value is select from dropdown.");

						if (niam.verifyDisclaimerGrid(DisclaimerGrid.Deactivate, M4DIS1, DisclaimerGrid.View,
								DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
							appLog.info("Disclaimer grid has been verified.");

						} else {
							appLog.error("Disclaimer grid not verified.");
							sa.assertTrue(false, "Disclaimer grid not verified.");
						}
						if (niam.getDisclaimerRecordCount() >= 1) {
							appLog.info("record count is verfied and equals to : " + niam.getDisclaimerRecordCount());
						} else {
							appLog.error("record count is not verfied.");
							sa.assertTrue(false, "record count is not verfied.");
						}
						if (niam.getActiveDisclaimerStatisticsValue().equals(M4DIS1)) {
							appLog.info("Active Disclaimer Statics : " + niam.getActiveDisclaimerStatisticsValue()
							+ " is available");
						} else {
							appLog.error("Active Disclaimer Statics value  is not verified.");
							sa.assertTrue(false, "Active Disclaimer Statics value is not verified.");
						}
						if (niam.getAcceptedDiscalimerCount() >= 1 && niam.getWaitingDisclaimerCount() >= 1) {
							appLog.info("Active Disclaimer Statics status accepted count : "
									+ niam.getAcceptedDiscalimerCount()
									+ " Active Disclaimer Statics status waiting count : "
									+ niam.getWaitingDisclaimerCount() + "has been verified.");
						} else {
							appLog.error(
									"Active Disclaimer Statics status : accepted and waiting count is not verified.");
							sa.assertTrue(false,
									"Active Disclaimer Statics status : accepted and waiting count is not verified.");
						}

						appLog.info(
								"******************* check result when i click on <View Button> against 'Disclaimer 1' **********************");

						if (niam.clickOnViewLinkOfDisclaimer(M4DIS1)) {
							appLog.info("Disclaimer Statistics Popup is opened.");
							String selectedOpt = getSelectedOptionOfDropDown(driver, niam.getDisclaimerNamePickList(80),
									"get selected option from name picklist", "text");
							System.out.println("selected option is  : " + selectedOpt);
							if (selectedOpt.equals(M4DIS1 + " (Active)")) {
								appLog.info(selectedOpt + "is selected in name picklist.");

							} else {
								appLog.error("Disclaimer 1 is not selected in name picklist.");
								sa.assertTrue(false, "Disclaimer 1 is not selected in name picklist.");
							}
							if (niam.verifyDisclaimerStatisticsGrid(M4CFN2 + " " + M4CLN2, M4C2Email, M4I2, "Waiting",
									"")) {
								appLog.info(M4CFN2 + " " + M4CLN2 + "contact detail is verfied ");
							} else {
								appLog.error("contact detail 2 is not verfied.");
								sa.assertTrue(false, "contact detail 2 is not verfied.");
							}
							if (niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1, "Accepted",
									getSystemDate("MM/dd/yyyy"))) {
								appLog.info(M4CFN1 + " " + M4CLN1 + "contact detail is verfied ");
							} else {
								appLog.error("contact detail 1 is not verfied.");
								sa.assertTrue(false, "contact detail 1  is not verfied.");
							}
							if (niam.getDisclaimerViewPopUpRecordCount() == 2) {
								appLog.info("Disclaimer Statistics popup record count is verfied and equals to : "
										+ niam.getDisclaimerViewPopUpRecordCount());
							} else {
								appLog.error("record count is not verfied.");
								sa.assertTrue(false, "record count is not verfied.");
							}

						} else {
							appLog.error("Disclaimer Statistics Popup is not openend.");
							sa.assertTrue(false, "Disclaimer Statistics Popup is not openend.");
						}

					} else {
						appLog.error(M4F1 + " fund name is not present in the drop down, So not able to select.");
						sa.assertTrue(false,
								M4F1 + " fund name is not present in the drop down, So not able to select.");
					}
				} else {
					appLog.error("not able to switch to  Child Navatar add-ons inverstor disclaimer frame.");
					sa.assertTrue(false, "not able to switch to Child Navatar add-ons inverstor disclaimer frame.");

				}

			} else {
				appLog.error("not able to switch to  Parent Navatar add-ons inverstor disclaimer frame.");
				sa.assertTrue(false, "not able to switch to Parent  Navatar add-ons inverstor disclaimer frame.");
			}

		} else {
			appLog.error("Navatar investor add on tab cannot be clicked.");
			sa.assertTrue(false, "Navatar investor add on tab cannot be clicked.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();

	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc022_CreateDisclaimerAndVerfiyImpact() {

		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if (lp.clickOnTab(TabName.NavatarInvestorAddOns)) {
			System.out.println("inside NavatarInvestorAddonsTab");
			 switchToFrame(driver,20,lp.getNavatarInvestorAddOnParentFrame(20));
			
			if (switchToFrame(driver, 60, niam.getNavatarInvestorAddOnFrame(60))) {
				appLog.info("Successfully switch to  Parent Navatar add-ons inverstor disclaimer frame.");
				if (switchToFrame(driver, 60, niam.getPEDisclaimersSetupFrame(60))) {
					appLog.info("Successfully switched to Child Navatar add-ons inverstor disclaimer frame.");

					if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60),
							"Select fund drop down on Navatar Investor add-ons page", M4F1)) {
						appLog.info(M4F1 + "value is select from dropdown.");

					} else {
						appLog.error(M4F1 + " fund name is not present in the drop down, So not able to select.");
						sa.assertTrue(false,
								M4F1 + " fund name is not present in the drop down, So not able to select.");
					}

					if (click(driver, niam.getNewDisclaimerButton(60), "New discliamer Button", action.BOOLEAN)) {
						if (sendKeys(driver, niam.getNewDisclaimerNameTextBox(60), M4DIS2, "DisclaimerName text Box",
								action.BOOLEAN)) {
							if (sendKeys(driver, niam.getDisclaimerDescriptionTextArea(10), M4DISDEC2,
									"Description text box", action.BOOLEAN)) {
								switchToDefaultContent(driver);
								if (switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60))) {
									System.err.println("Successfully switched to frame.");
									switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
								} else {
									appLog.error("Not able to switch to frame");
									sa.assertTrue(false, "Not able to switch to frame.");
								}
								if (click(driver, niam.getNewDiscalimerPopUpSaveButton(60), "Save Button",
										action.BOOLEAN)) {
									appLog.info("Disclaimer has been saved successfully.");
								} else {
									appLog.error(M4DIS2 + "Not able to save.");
									sa.assertTrue(false, "Not able to save.");
								}

							} else {
								appLog.error("Not able to pass value to disclimer description text box.");
								sa.assertTrue(false, "Not able to pass value to disclimer description text box.");
							}
						} else {
							appLog.error("Not able to pass value to disclimer name text box.");
							sa.assertTrue(false, "Not able to pass value to disclimer name text box.");
						}
					} else {
						appLog.error(
								"New disclaimer button cannot be clikced so cannot check the new disclaimer pop up UI.");
						sa.assertTrue(false,
								"New disclaimer button cannot be clikced so cannot check the new disclaimer pop up UI.");
					}

					if (niam.verifyDisclaimerGrid(DisclaimerGrid.Deactivate, M4DIS1, DisclaimerGrid.View,
							DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
						appLog.info(M4DIS1 + "Disclaimer grid has been verified.");

					} else {
						appLog.error(M4DIS1 + "Disclaimer grid not verified.");
						sa.assertTrue(false, " '" + M4DIS1 + "' Disclaimer grid not verified.");
					}

					if (niam.verifyDisclaimerGrid(DisclaimerGrid.Activate, M4DIS2, DisclaimerGrid.View, null,
							DisclaimerGrid.CreatedOn)) {
						appLog.info(M4DIS2 + "Disclaimer grid has been verified.");

					} else {
						appLog.error(M4DIS2 + "Disclaimer grid not verified.");
						sa.assertTrue(false, " '" + M4DIS2 + "' Disclaimer grid not verified.");
					}
					if (niam.getDisclaimerRecordCount() >= 2) {
						appLog.info("record count is verfied and equals to : " + niam.getDisclaimerRecordCount());
					} else {
						appLog.error("record count is not verfied.");
						sa.assertTrue(false, "record count is not verfied.");
					}
					if (niam.getActiveDisclaimerStatisticsValue().equalsIgnoreCase(M4DIS1)) {
						appLog.info("Active Disclaimer Statics : " + niam.getActiveDisclaimerStatisticsValue()
						+ " is available");
					} else {
						appLog.error("Active Disclaimer Statics value is not verified. Expected: " + M4DIS1
								+ "\tActual: " + niam.getActiveDisclaimerStatisticsValue());
						sa.assertTrue(false, "Active Disclaimer Statics value is not verified. Expected: " + M4DIS1
								+ "\tActual: " + niam.getActiveDisclaimerStatisticsValue());
					}
					if (niam.getAcceptedDiscalimerCount() >= 1 && niam.getWaitingDisclaimerCount() >= 1) {
						appLog.info(
								"Active Disclaimer Statics status accepted count : " + niam.getAcceptedDiscalimerCount()
								+ " Active Disclaimer Statics status waiting count : "
								+ niam.getWaitingDisclaimerCount() + "has been verified.");
					} else {
						appLog.error("Active Disclaimer Statics status : accepted and waiting count is not verified.");
						sa.assertTrue(false,
								"Active Disclaimer Statics status : accepted and waiting count is not verified.");
					}

					appLog.info(
							"******************* check result when i click on <View Button> against 'Disclaimer 2' **********************");

					if (niam.clickOnViewLinkOfDisclaimer(M4DIS2)) {
						appLog.info("Disclaimer Statistics Popup is opened.");

						List<WebElement> lstOfEle = allOptionsInDropDrop(driver, niam.getDisclaimerNamePickList(80),
								"get selected option from name picklist");

						boolean flag_dis1 = false;
						boolean flag_dis2 = false;
						for (int i = 0; i < lstOfEle.size(); i++) {
							if (lstOfEle.get(i).getText().trim().equals(M4DIS2)) {
								flag_dis1 = true;
								appLog.info(lstOfEle.get(i).getText().trim() + "is present in name picklist");
							} else if (lstOfEle.get(i).getText().trim().equals(M4DIS1 + " (Active)")) {
								flag_dis2 = true;
								appLog.info(lstOfEle.get(i).getText().trim() + "is present in name picklist");
							}

						}
						if (flag_dis1 == false) {
							appLog.error("dis1 is not present in name picklist");
							sa.assertTrue(false, "dis 1 is not present in name picklist");

						}
						if (flag_dis2 == false) {
							appLog.error("dis2 is not present in name picklist");
							sa.assertTrue(false, "dis2 is not present in name picklist");

						}
						String selectedOpt = getSelectedOptionOfDropDown(driver, niam.getDisclaimerNamePickList(80),
								"get selected option from name picklist", "text");
						System.out.println("selected option is  : " + selectedOpt);
						if (selectedOpt.equals(M4DIS2)) {
							appLog.info(selectedOpt + "is selected in name picklist.");

						} else {
							appLog.error("Disclaimer 2 is not selected in name picklist.");
							sa.assertTrue(false, "Disclaimer 2 is not selected in name picklist.");
						}

						WebElement ele = FindElement(driver, "//span[@id='Disclaimer_Statistics-cell-0-0']/span",
								"No data error msg", action.BOOLEAN, 30);
						String text = getAttribute(driver, ele, "No Data Error msg", "title");
						if (text.equalsIgnoreCase(NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage)) {
							appLog.info("No data to display error msg is verified.");
						} else {
							appLog.error("No data error messgae is not verified. Expected: "
									+ NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage + "\tActual: "
									+ text);
							sa.assertTrue(false,
									"No data error messgae is not verified. Expected: "
											+ NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage
											+ "\tActual: " + text);
						}

						String clkHereText1 = getText(driver, niam.getDisclaimerViewPopUpClickHereText(60),
								"Click here text", action.BOOLEAN);
						System.out.println("click here link text is :" + clkHereText1);
						if (clkHereText1.equals("Click here to export all the statistics for this fund.")) {

							appLog.info(
									"Click here link text verified.Expected: Click here to export all the statistics for this fund.\tActual: "
											+ clkHereText1);
						} else {
							appLog.error(
									"Click here link text is not verified.Expected: Click here to export all the statistics for this fund.\tActual: "
											+ clkHereText1);
							sa.assertTrue(false,
									"Click here link is not verified.Expected: Click here to export all the statistics for this fund.\tActual: "
											+ clkHereText1);

						}
						if (niam.getDisclaimerViewPopUpRecordCount() == 0) {
							appLog.info("Disclaimer Statistics popup record count is verfied and equals to : "
									+ niam.getDisclaimerViewPopUpRecordCount());
						} else {
							appLog.error("record count is not verfied.");
							sa.assertTrue(false, "record count is not verfied.");
						}

					} else {
						appLog.error("Disclaimer Statistics Popup is not openend.");
						sa.assertTrue(false, "Disclaimer Statistics Popup is not openend.");
					}

					appLog.info("******************* Select and Check  'Disclaimer 1' details **********************");

					if (selectVisibleTextFromDropDown(driver, niam.getDisclaimerNamePickList(60),
							"Select Option from name picklist", M4DIS1 + " (Active)")) {
						appLog.info("Disclaimer 1 is selected from Name Picklist.");

						List<WebElement> lstOfEle = allOptionsInDropDrop(driver, niam.getDisclaimerNamePickList(80),
								"Options in name picklist");

						boolean flag_dis1 = false;
						boolean flag_dis2 = false;
						for (int i = 0; i < lstOfEle.size(); i++) {
							if (lstOfEle.get(i).getText().trim().equals(M4DIS1 + " (Active)")) {
								flag_dis1 = true;
								appLog.info(lstOfEle.get(i).getText().trim() + "is present in name picklist");
							} else if (lstOfEle.get(i).getText().trim().equals(M4DIS2)) {
								flag_dis2 = true;
								appLog.info(lstOfEle.get(i).getText().trim() + "is present in name picklist");
							}

						}
						if (flag_dis1 == false) {
							appLog.error("dis1 is not present in name picklist");
							sa.assertTrue(false, "dis 1 is not present in name picklist");

						}
						if (flag_dis2 == false) {
							appLog.error("dis2 is not present in name picklist");
							sa.assertTrue(false, "dis2 is not present in name picklist");

						}

						String selectedOpt = getSelectedOptionOfDropDown(driver, niam.getDisclaimerNamePickList(80),
								"get selected option from name picklist", "text");
						System.out.println("selected option is  : " + selectedOpt);
						if (selectedOpt.equals(M4DIS1 + " (Active)")) {
							appLog.info(selectedOpt + "is selected in name picklist.");

						} else {
							appLog.error("Disclaimer 1 is not selected in name picklist.");
							sa.assertTrue(false, "Disclaimer 1 is not selected in name picklist.");
						}

						if (niam.verifyDisclaimerStatisticsGrid(M4CFN2 + " " + M4CLN2, M4C2Email, M4I2, "Waiting",
								"")) {
							appLog.info(M4CFN2 + " " + M4CLN2 + "contact detail is verfied ");
						} else {
							appLog.error("contact detail 2 is not verfied.");
							sa.assertTrue(false, "contact detail 2 is not verfied.");
						}
						if (niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1, "Accepted",
								getSystemDate("MM/dd/yyyy"))) {
							appLog.info(M4CFN1 + " " + M4CLN1 + "contact detail is verfied ");
						} else {
							appLog.error("contact detail 1 is not verfied.");
							sa.assertTrue(false, "contact detail 1 is not verfied.");
						}
						if (niam.getDisclaimerViewPopUpRecordCount() >= 2) {
							appLog.info("Disclaimer Statistics popup record count is verfied and equals to : "
									+ niam.getDisclaimerViewPopUpRecordCount());
						} else {
							appLog.error("record count is not verfied.");
							sa.assertTrue(false, "record count is not verfied.");
						}

					} else {
						appLog.error("Disclaimer 1 is not selected from Name Picklist");
						sa.assertTrue(false, "Disclaimer 1 is selected from Name Picklist");
					}

				} else {
					appLog.error("not able to switch to  Child Navatar add-ons inverstor disclaimer frame.");
					sa.assertTrue(false, "not able to switch to Child Navatar add-ons inverstor disclaimer frame.");

				}

			} else {
				appLog.error("not able to switch to  Parent Navatar add-ons inverstor disclaimer frame.");
				sa.assertTrue(false, "not able to switch to Parent  Navatar add-ons inverstor disclaimer frame.");
			}

		} else {
			appLog.error("Navatar investor add on tab cannot be clicked.");
			sa.assertTrue(false, "Navatar investor add on tab cannot be clicked.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc023_ActivateDisclaimerForFundAndVerifyImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		if (lp.clickOnTab(TabName.NavatarInvestorAddOns)) {
			 switchToFrame(driver,20,lp.getNavatarInvestorAddOnParentFrame(20));
			 if(switchToFrame(driver, 20, niam.getNavatarInvestorAddOnFrame(30))){
			 				System.err.println("Successfully switched to frame.");
			 				  switchToFrame(driver,20,niam.getPEDisclaimersSetupFrame(20));
			 			}
			if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60),
					"Select fund drop down on Navatar Investor add-ons page", M4F1)) {
				appLog.info(M4F1 + "value is select from dropdown.");
				if (niam.activateDisclaimer(M4DIS2, 40)) {
					appLog.info("Disclaimer 2 is successfully activated");
					if (niam.verifyDisclaimerGrid(DisclaimerGrid.Activate, M4DIS1, DisclaimerGrid.View,
							DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
						appLog.info(M4DIS1 + "Disclaimer 1 grid has been verified.");
					} else {
						appLog.error(M4DIS1 + " Disclaimer 1 grid not verified.");
						sa.assertTrue(false, M4DIS1 + " Disclaimer grid not verified.");
					}
					if (niam.verifyDisclaimerGrid(DisclaimerGrid.Deactivate, M4DIS2, DisclaimerGrid.View,
							DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
						appLog.info(M4DIS2 + "Disclaimer grid has been verified.");

					} else {
						appLog.error(M4DIS2 + " Disclaimer grid not verified.");
						sa.assertTrue(false, M4DIS2 + "Disclaimer grid not verified.");
					}
					if (niam.getDisclaimerRecordCount() >= 2) {
						appLog.info("record count is verfied and equals to : " + niam.getDisclaimerRecordCount());
					} else {
						appLog.error("record count is not verfied.");
						sa.assertTrue(false, "record count is not verfied.");
					}
					if (niam.getActiveDisclaimerStatisticsValue().equals(M4DIS2)) {
						appLog.info("Active Disclaimer Statics : '" + M4DIS2 + "' is available");
					} else {
						appLog.error("Active Disclaimer Statics value  is not verified.");
						sa.assertTrue(false, "Active Disclaimer Statics value is not verified.");
					}
					if (niam.getAcceptedDiscalimerCount() == 0 && niam.getWaitingDisclaimerCount() == 2) {
						appLog.info(
								"Active Disclaimer Statics status accepted count : " + niam.getAcceptedDiscalimerCount()
								+ " Active Disclaimer Statics status waiting count : "
								+ niam.getWaitingDisclaimerCount() + "has been verified.");
					} else {
						appLog.error("Active Disclaimer Statics status : accepted and waiting count is not verified.");
						sa.assertTrue(false,
								"Active Disclaimer Statics status : accepted and waiting count is not verified.");
					}
					appLog.info(
							"******************* check result when i click on <View Button> against 'Disclaimer 2' **********************");

					if (niam.clickOnViewLinkOfDisclaimer(M4DIS2)) {
						appLog.info("Disclaimer Statistics Popup is opened.");

						List<WebElement> lstOfEle = allOptionsInDropDrop(driver, niam.getDisclaimerNamePickList(80),
								"get selected option from name picklist");

						boolean flag_dis1 = false;
						boolean flag_dis2 = false;
						for (int i = 0; i < lstOfEle.size(); i++) {
							if (lstOfEle.get(i).getText().trim().equals(M4DIS1)) {
								flag_dis1 = true;
								appLog.info(lstOfEle.get(i).getText().trim() + " is present in name picklist ");
							} else if (lstOfEle.get(i).getText().trim().equals(M4DIS2 + " (Active)")) {
								flag_dis2 = true;
								appLog.info(lstOfEle.get(i).getText().trim() + " is present in name picklist ");
							}

						}
						if (flag_dis1 == false) {
							appLog.error("dis1 is not present in name picklist");
							sa.assertTrue(false, "dis 1 is not present in name picklist");

						}
						if (flag_dis2 == false) {
							appLog.error("dis2 is not present in name picklist");
							sa.assertTrue(false, "dis2 is not present in name picklist");

						}
						String selectedOpt = getSelectedOptionOfDropDown(driver, niam.getDisclaimerNamePickList(80),
								"get selected option from name picklist", "text");
						System.out.println("selected option is  : " + selectedOpt);
						if (selectedOpt.equals(M4DIS2 + " (Active)")) {
							appLog.info(selectedOpt + "is selected in name picklist.");

						} else {
							appLog.error("Disclaimer 2 is not selected in name picklist.");
							sa.assertTrue(false, "Disclaimer 2 is not selected in name picklist.");
						}
						if (niam.verifyDisclaimerStatisticsGrid(M4CFN2 + " " + M4CLN2, M4C2Email, M4I2, "Waiting",
								"")) {
							appLog.info(M4CFN2 + " " + M4CLN2 + " contact detail is verfied ");
						} else {
							appLog.error("contact detail 2 is not verfied.");
							sa.assertTrue(false, "contact detail 2 is not verfied.");
						}
						if (niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1, "Waiting",
								"")) {
							appLog.info(M4CFN1 + " " + M4CLN1 + " contact detail is verfied ");
						} else {
							appLog.error("contact detail 1 is not verfied.");
							sa.assertTrue(false, "contact detail 1  is not verfied.");
						}
						if (niam.getDisclaimerViewPopUpRecordCount() == 2) {
							appLog.info("Disclaimer Statistics popup record count is verfied and equals to : "
									+ niam.getDisclaimerViewPopUpRecordCount());
						} else {
							appLog.error("record count is not verfied.");
							sa.assertTrue(false, "record count is not verfied.");
						}
						if (click(driver, niam.getDisclaimerViewPopUpCrossIcon(40), "disclaimerViewPopUpCrossIcon",
								action.BOOLEAN)) {
							if (niam.clickOnViewLinkOfDisclaimer(M4DIS1)) {
								appLog.info("Disclaimer Statistics Popup is opened.");
								lstOfEle.clear();
								lstOfEle = allOptionsInDropDrop(driver, niam.getDisclaimerNamePickList(80),
										"get selected option from name picklist");
								flag_dis1 = false;
								flag_dis2 = false;
								for (int i = 0; i < lstOfEle.size(); i++) {
									if (lstOfEle.get(i).getText().trim().equals(M4DIS1)) {
										flag_dis1 = true;
										appLog.info(lstOfEle.get(i).getText().trim() + " is present in name picklist ");
									} else if (lstOfEle.get(i).getText().trim().equals(M4DIS2 + " (Active)")) {
										flag_dis2 = true;
										appLog.info(lstOfEle.get(i).getText().trim() + " is present in name picklist ");
									}
								}
								if (flag_dis1 == false) {
									appLog.error("dis1 is not present in name picklist");
									sa.assertTrue(false, "dis 1 is not present in name picklist");
								}
								if (flag_dis2 == false) {
									appLog.error("dis2 is not present in name picklist");
									sa.assertTrue(false, "dis2 is not present in name picklist");
								}
								selectedOpt = getSelectedOptionOfDropDown(driver, niam.getDisclaimerNamePickList(80),
										"get selected option from name picklist", "text");
								System.out.println("selected option is  : " + selectedOpt);
								if (selectedOpt.equals(M4DIS1)) {
									appLog.info(selectedOpt + "is selected in name picklist.");
								} else {
									appLog.error("Disclaimer 1 is not selected in name picklist.");
									sa.assertTrue(false, "Disclaimer 1 is not selected in name picklist.");
								}
								if (niam.verifyDisclaimerStatisticsGrid(M4CFN2 + " " + M4CLN2, M4C2Email, M4I2,
										"Waiting", "")) {
									appLog.info(M4CFN2 + " " + M4CLN2 + " contact detail is verfied ");
								} else {
									appLog.error("contact detail 2 is not verfied.");
									sa.assertTrue(false, "contact detail 2 is not verfied.");
								}
								if (niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1,
										"Accepted", getSystemDate("MM/dd/yyyy"))) {
									appLog.info(M4CFN1 + " " + M4CLN1 + " contact detail is verfied ");
								} else {
									appLog.error("contact detail 1 is not verfied.");
									sa.assertTrue(false, "contact detail 1  is not verfied.");
								}
								if (niam.getDisclaimerViewPopUpRecordCount() == 2) {
									appLog.info("Disclaimer Statistics popup record count is verfied and equals to : "
											+ niam.getDisclaimerViewPopUpRecordCount());
								} else {
									appLog.error("record count is not verfied.");
									sa.assertTrue(false, "record count is not verfied.");
								}
							} else {
								appLog.error("Disclaimer Statistics Popup is not openend.");
								sa.assertTrue(false, "Disclaimer Statistics Popup is not openend.");
							}
						} else {
							appLog.error(
									" Disclaimer View PopUp Cross Icon is not clickable so not able to verify Disclaimer 1 detail. ");
							sa.assertTrue(false,
									" Disclaimer View PopUp Cross Icon is not clickable so not able to verify Disclaimer 1 detail. ");
						}
					} else {
						appLog.error("Disclaimer Statistics Popup is not openend.");
						sa.assertTrue(false, "Disclaimer Statistics Popup is not openend.");
					}

				} else {
					appLog.error(" Disclaimer 2 is not activated. ");
					sa.assertTrue(false, M4DIS2 + " Disclaimer 2 is not activated. ");
				}
			} else {
				appLog.error(M4F1 + " fund name is not present in the drop down, So not able to select.");
				sa.assertTrue(false, M4F1 + " fund name is not present in the drop down, So not able to select.");
			}

		} else {
			appLog.error("Navatar addon tab cannot be clicked, So cannot check the UI.");
			sa.assertTrue(false, "Navatar addon tab cannot be clicked, So cannot check the UI.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		appLog.info("******************* login to contact 1 **********************");
		InvestorFirmPageBusinesslayer ifpb = new InvestorFirmPageBusinesslayer(driver);
		niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		DisclaimerPageBussinessLayer dpbl = new DisclaimerPageBussinessLayer(driver);
		String parentID = null;
		if (lp.investorLogin(M4C1Email, adminPassword)) {
			if (matchTitle(driver, "Firm Alerts", 60)) {
				appLog.info("Page title is successfully matched.");
			} else {
				appLog.info("Page title is not matched.");
				sa.assertTrue(false, "Page title is not matched.");
			}
			if (niam.getPendingDisclaimerPopGoToDisclaimerButton(60) != null) {
				appLog.info("Pending Disclaimer Popup is available.");
				if (click(driver, niam.getPendingDisclaimerPopGoToDisclaimerButton(60),
						"Pending Disclaimer Popup Go to Disclaimer Button", action.BOOLEAN)) {
					appLog.info("Click on Go to Disclaimer Button Successfully.");
					parentID = switchOnWindow(driver);
					if (parentID!=null) {
						
						String text = trim(getText(driver, dpbl.getDisclaimerPageHeader(60), "Header", action.BOOLEAN));
						if (text.equalsIgnoreCase("Disclaimers")) {
							appLog.info("Header is verified.");
						} else {
							appLog.error("Header is not present on the page.Expected: Disclaimers\tActual: " + text);
							sa.assertTrue(false,
									"Header is not present on the page.Expected: Disclaimers\tActual: " + text);

						}
						text = trim(getText(driver, dpbl.getFundName(60), "FundName", action.BOOLEAN));
						if (text.equalsIgnoreCase(M4F1)) {
							appLog.info(M4F1 + "FundName is verified.");
						} else {
							appLog.error(M4F1 + "Fund Name is not verified.");
							sa.assertTrue(false, "FundName is not verified.");

						}
						text = trim(getText(driver, dpbl.getDisclaimerName(60), "DisclaimerName", action.BOOLEAN));
						if (text.equalsIgnoreCase(M4DIS2)) {
							appLog.info(M4DIS2 + "DisclaimerName is verified.");
						} else {
							appLog.error(M4DIS2 + "DisclaimerName is not verified.");
							sa.assertTrue(false, " '" + M4DIS2 + "'DisclaimerName is not verified.");
						}
						if (dpbl.getAcceptButton(60) != null) {
							appLog.info("AcceptButton is available and verified.");
						} else {
							appLog.error("AcceptButton is not available and verified.");
							sa.assertTrue(false, "AcceptButton is not available and  verified.");
						}
						

						driver.close();
						driver.switchTo().window(parentID);
						
					} else {
						appLog.info("No New Window open");
						sa.assertTrue(false, "No New Window open");
					}
					
				} else {
					appLog.info("Not able to click on Go to Disclaimer Button .");
					sa.assertTrue(false, "Not able to click on Go to Disclaimer Button.");

				}
			} else {
				appLog.info("Pending Disclaimer Popup is available.");
				sa.assertTrue(false, "Pending Disclaimer Popup is not available.");
			}

		} else {
			appLog.info("Investor password is wrong so cannot login.");
			sa.assertTrue(false, "Investor password is wrong so cannot login");

		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc024_VerifyStatusPicklistAndSearchResult() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		if (lp.clickOnTab(TabName.NavatarInvestorAddOns)) {
			if (switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60))) {
				System.err.println("Successfully switched to frame.");
				switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
			}
			if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60),
					"Select fund drop down on Navatar Investor add-ons page", M4F1)) {
				appLog.info(M4F1 + " value is select from dropdown.");
				if (niam.clickOnViewLinkOfDisclaimer(M4DIS2)) {
					appLog.info("Disclaimer Statistics Popup is opened.");
					if (clickUsingJavaScript(driver, niam.getDisclaimerViewPopupSearchIcon(60), "Search Icon", action.BOOLEAN)) {
						appLog.info("Click on search icon successfully.");
						ThreadSleep(4000);
						if (isAlertPresent(driver)) {
							String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
							if (msg.equalsIgnoreCase(
									NavatarInvestorAddOnsErrorMessage.PleaseEnterValueErrorMsg.trim())) {
								appLog.info("Error Message is verified : " + msg);
							} else {
								appLog.error("Error Message is not verified : ");
								sa.assertTrue(false, "Error Message is not verified : ");
							}
							if (switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT)) {
								appLog.info("Clicked on ok button successfully.");
							} else {
								appLog.error(" not able to click on ok button. ");
								sa.assertTrue(false, "Error Message is not verified : ");
							}
						} else {
							appLog.error(" No Alert is Present ");
							sa.assertTrue(false, " No Alert is Present ");
						}
					} else {
						appLog.error("Search Icon is not clicked.");
						sa.assertTrue(false, "Search Icon is not clicked.");
					}
					if (selectVisibleTextFromDropDown(driver, niam.getDisclaimerViewPopUpStatusPickList(40),
							"Status Picklist", "Waiting")) {
						appLog.info(" Waiting Option is selected from dropdown successfully.");
						List<WebElement> lstOfEle = allOptionsInDropDrop(driver, niam.getDisclaimerNamePickList(80),
								"get selected option from name picklist");

						boolean flag_dis1 = false;
						boolean flag_dis2 = false;
						for (int i = 0; i < lstOfEle.size(); i++) {
							if (lstOfEle.get(i).getText().trim().equals(M4DIS1)) {
								flag_dis1 = true;
								appLog.info(lstOfEle.get(i).getText().trim() + " is present in name picklist ");
							} else if (lstOfEle.get(i).getText().trim().equals(M4DIS2 + " (Active)")) {
								flag_dis2 = true;
								appLog.info(lstOfEle.get(i).getText().trim() + " is present in name picklist ");
							}

						}
						if (flag_dis1 == false) {
							appLog.error("dis1 is not present in name picklist");
							sa.assertTrue(false, "dis 1 is not present in name picklist");

						}
						if (flag_dis2 == false) {
							appLog.error("dis2 is not present in name picklist");
							sa.assertTrue(false, "dis2 is not present in name picklist");

						}
						String selectedOpt = getSelectedOptionOfDropDown(driver, niam.getDisclaimerNamePickList(80),
								"get selected option from name picklist", "text");
						System.out.println("selected option is  : " + selectedOpt);
						if (selectedOpt.equals(M4DIS2 + " (Active)")) {
							appLog.info(selectedOpt + "is selected in name picklist.");

						} else {
							appLog.error("Disclaimer 2 is not selected in name picklist.");
							sa.assertTrue(false, "Disclaimer 2 is not selected in name picklist.");
						}
						if (niam.verifyDisclaimerStatisticsGrid(M4CFN2 + " " + M4CLN2, M4C2Email, M4I2, "Waiting",
								"")) {
							appLog.info(M4CFN2 + " " + M4CLN2 + " contact detail is verfied ");
						} else {
							appLog.error("contact detail 2 is not verfied.");
							sa.assertTrue(false, "contact detail 2 is not verfied.");
						}
						if (niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1, "Waiting",
								"")) {
							appLog.info(M4CFN1 + " " + M4CLN1 + " contact detail is verfied ");
						} else {
							appLog.error("contact detail 1 is not verfied.");
							sa.assertTrue(false, "contact detail 1  is not verfied.");
						}
						if (niam.getDisclaimerViewPopUpRecordCount() == 2) {
							appLog.info("Disclaimer Statistics popup record count is verfied and equals to : "
									+ niam.getDisclaimerViewPopUpRecordCount());
						} else {
							appLog.error("record count is not verfied.");
							sa.assertTrue(false, "record count is not verfied.");
						}
					} else {
						appLog.error("Waiting Option is not selected from status picklist.");
						sa.assertTrue(false, "Waiting Option is not selected drom status picklist.");
					}
					if (selectVisibleTextFromDropDown(driver, niam.getDisclaimerViewPopUpStatusPickList(40),
							"Status Picklist", "Accepted")) {
						appLog.info(" Accepted Option is selected from dropdown successfully.");
						WebElement ele = FindElement(driver, "//span[@id='Disclaimer_Statistics-cell-0-0']/span",
								"No data error msg", action.BOOLEAN, 30);
						String text = getAttribute(driver, ele, "No Data Error msg", "title");
						if (text.equalsIgnoreCase(NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage)) {
							appLog.info("No data to display error msg is verified.");
						} else {
							appLog.error("No data error messgae is not verified. Expected: "
									+ NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage + "\tActual: "
									+ text);
							sa.assertTrue(false,
									"No data error messgae is not verified. Expected: "
											+ NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage
											+ "\tActual: " + text);
						}
						if (niam.getDisclaimerViewPopUpRecordCount() == 0) {
							appLog.info("Disclaimer Statistics popup record count is verfied and equals to : "
									+ niam.getDisclaimerViewPopUpRecordCount());
						} else {
							appLog.error("record count is not verfied.");
							sa.assertTrue(false, "record count is not verfied.");
						}

						appLog.info(
								"******************************** Select Disclaimer 1 *****************************");
						if (selectVisibleTextFromDropDown(driver, niam.getDisclaimerNamePickList(60), "Name Picklist",
								M4DIS1)) {
							appLog.info(M4DIS1 + "is selected from disclaimer name picklist.");
							if (niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1, "Accepted",
									getSystemDate("MM/dd/yyyy"))) {
								appLog.info(M4CFN1 + " " + M4CLN1 + " contact detail is verfied ");
							} else {
								appLog.error("contact detail 1 is not verfied.");
								sa.assertTrue(false, "contact detail 1  is not verfied.");
							}
							String selectedOpt = getSelectedOptionOfDropDown(driver,
									niam.getDisclaimerViewPopUpStatusPickList(60),
									"get selected option from status picklist", "text");
							System.out.println("selected option is  : " + selectedOpt);
							if (selectedOpt.equals("Accepted")) {
								appLog.info(selectedOpt + " is selected in name picklist.");

							} else {
								appLog.error("'" + selectedOpt + "' is not selected in name picklist.");
								sa.assertTrue(false, "'" + selectedOpt + "' is not selected in name picklist.");
							}
							if (niam.getDisclaimerViewPopUpRecordCount() == 1) {
								appLog.info("Disclaimer Statistics popup record count is verfied and equals to : "
										+ niam.getDisclaimerViewPopUpRecordCount());
							} else {
								appLog.error("record count is not verfied.");
								sa.assertTrue(false, "record count is not verfied.");
							}

							appLog.info(
									"******************** Select Waiting from status picklist***********************");

							if (selectVisibleTextFromDropDown(driver, niam.getDisclaimerViewPopUpStatusPickList(40),
									"Status Picklist", "Waiting")) {
								appLog.info(" Waiting Option is selected from dropdown successfully.");

								if (niam.verifyDisclaimerStatisticsGrid(M4CFN2 + " " + M4CLN2, M4C2Email, M4I2,
										"Waiting", "")) {
									appLog.info(M4CFN2 + " " + M4CLN2 + " contact detail is verfied ");
								} else {
									appLog.error("contact detail 2 is not verfied.");
									sa.assertTrue(false, "contact detail 2 is not verfied.");
								}
								if (niam.getDisclaimerViewPopUpRecordCount() == 1) {
									appLog.info("Disclaimer Statistics popup record count is verfied and equals to : "
											+ niam.getDisclaimerViewPopUpRecordCount());
								} else {
									appLog.error("record count is not verfied.");
									sa.assertTrue(false, "record count is not verfied.");
								}
							} else {
								appLog.error("Waiting Option is not selected from status picklist.");
								sa.assertTrue(false, "Waiting Option is not selected drom status picklist.");
							}
							appLog.info(
									"*********************  Select All from status picklist**************************");
							if (selectVisibleTextFromDropDown(driver, niam.getDisclaimerViewPopUpStatusPickList(40),
									"Status Picklist", "All")) {
								appLog.info(" All Option is selected from dropdown successfully.");
								if (niam.verifyDisclaimerStatisticsGrid(M4CFN2 + " " + M4CLN2, M4C2Email, M4I2,
										"Waiting", "")) {
									appLog.info(M4CFN2 + " " + M4CLN2 + " contact detail is verfied ");
								} else {
									appLog.error("contact detail 2 is not verfied.");
									sa.assertTrue(false, "contact detail 2 is not verfied.");
								}
								if (niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1,
										"Accepted", getSystemDate("MM/dd/yyyy"))) {
									appLog.info(M4CFN1 + " " + M4CLN1 + " contact detail is verfied ");
								} else {
									appLog.error("contact detail 1 is not verfied.");
									sa.assertTrue(false, "contact detail 1  is not verfied.");
								}
								if (niam.getDisclaimerViewPopUpRecordCount() == 2) {
									appLog.info("Disclaimer Statistics popup record count is verfied and equals to : "
											+ niam.getDisclaimerViewPopUpRecordCount());
								} else {
									appLog.error("record count is not verfied.");
									sa.assertTrue(false, "record count is not verfied.");
								}
							} else {
								appLog.error("All Option is not selected from status picklist.");
								sa.assertTrue(false, "All Option is not selected drom status picklist.");
							}

							String contact1 = M4CFN1 + " " + M4CLN1;
							if (sendKeys(driver, niam.getDisclaimerViewPopUpSearchBox(60), contact1,
									"send value in searchbox", action.BOOLEAN)) {
								appLog.info(contact1 + "value successfully  passed in search box");
								if (click(driver, niam.getDisclaimerViewPopupSearchIcon(60), "click on Search icon ",
										action.BOOLEAN)) {
									appLog.info(" click on search icon sucessfully. ");
									if (niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1,
											"Accepted", getSystemDate("MM/dd/yyyy"))) {
										appLog.info(M4CFN1 + " " + M4CLN1 + " contact detail is verfied ");
									} else {
										appLog.error("contact detail 1 is not verfied.");
										sa.assertTrue(false, "contact detail 1  is not verfied.");
									}
									if (niam.getDisclaimerViewPopUpRecordCount() == 1) {
										appLog.info(
												"Disclaimer Statistics popup record count is verfied and equals to : "
														+ niam.getDisclaimerViewPopUpRecordCount());
									} else {
										appLog.error("record count is not verfied.");
										sa.assertTrue(false, "record count is not verfied.");
									}
									selectedOpt = getSelectedOptionOfDropDown(driver,
											niam.getDisclaimerViewPopUpStatusPickList(60),
											"get selected option from status picklist", "text");
									System.out.println("selected option is  : " + selectedOpt);
									if (selectedOpt.equals("All")) {
										appLog.info(selectedOpt + "is selected in name picklist.");

									} else {
										appLog.error("'" + selectedOpt + "' is not selected in name picklist.");
										sa.assertTrue(false, "'" + selectedOpt + "' is not selected in name picklist.");
									}
									if (selectVisibleTextFromDropDown(driver,
											niam.getDisclaimerViewPopUpStatusPickList(40), "Status Picklist",
											"Waiting")) {
										appLog.info(" Waiting Option is selected from dropdown successfully.");
										ele = FindElement(driver, "//span[@id='Disclaimer_Statistics-cell-0-0']/span",
												"No data error msg", action.BOOLEAN, 30);
										text = getAttribute(driver, ele, "No Data Error msg", "title");
										if (text.equalsIgnoreCase(
												NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage)) {
											appLog.info("No data to display error msg is verified.");
										} else {
											appLog.error("No data error messgae is not verified. Expected: "
													+ NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage
													+ "\tActual: " + text);
											sa.assertTrue(false,
													"No data error messgae is not verified. Expected: "
															+ NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage
															+ "\tActual: " + text);
										}

										String clkHereText1 = getText(driver,
												niam.getDisclaimerViewPopUpClickHereText(60), "Click here text",
												action.BOOLEAN);
										System.out.println("click here link text is :" + clkHereText1);
										if (clkHereText1
												.equals("Click here to export all the statistics for this fund.")) {

											appLog.info(
													"Click here link text verified.Expected: Click here to export all the statistics for this fund.\tActual: "
															+ clkHereText1);
										} else {
											appLog.error(
													"Click here link text is not verified.Expected: Click here to export all the statistics for this fund.\tActual: "
															+ clkHereText1);
											sa.assertTrue(false,
													"Click here link is not verified.Expected: Click here to export all the statistics for this fund.\tActual: "
															+ clkHereText1);

										}
										if (niam.getDisclaimerViewPopUpRecordCount() == 0) {
											appLog.info(
													"Disclaimer Statistics popup record count is verfied and equals to : "
															+ niam.getDisclaimerViewPopUpRecordCount());
										} else {
											appLog.error("record count is not verfied.");
											sa.assertTrue(false, "record count is not verfied.");
										}
										selectedOpt = getSelectedOptionOfDropDown(driver,
												niam.getDisclaimerViewPopUpStatusPickList(60),
												"get selected option from status picklist", "text");
										System.out.println("selected option is  : " + selectedOpt);
										if (selectedOpt.equals("Waiting")) {
											appLog.info(selectedOpt + "is selected in name picklist.");

										} else {
											appLog.error("'" + selectedOpt + "' is not selected in name picklist.");
											sa.assertTrue(false,
													"'" + selectedOpt + "' is not selected in name picklist.");
										}
									} else {
										appLog.error("Waiting Option is not selected from status picklist.");
										sa.assertTrue(false, "Waiting Option is not selected drom status picklist.");
									}
									if (selectVisibleTextFromDropDown(driver,
											niam.getDisclaimerViewPopUpStatusPickList(40), "Status Picklist",
											"Accepted")) {
										appLog.info(" Accepted Option is selected from dropdown successfully.");

										if (niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1,
												"Accepted", getSystemDate("MM/dd/yyyy"))) {
											appLog.info(M4CFN1 + " " + M4CLN1 + " contact detail is verfied ");
										} else {
											appLog.error("contact detail 1 is not verfied.");
											sa.assertTrue(false, "contact detail 1  is not verfied.");
										}
										if (niam.getDisclaimerViewPopUpRecordCount() == 1) {
											appLog.info(
													"Disclaimer Statistics popup record count is verfied and equals to : "
															+ niam.getDisclaimerViewPopUpRecordCount());
										} else {
											appLog.error("record count is not verfied.");
											sa.assertTrue(false, "record count is not verfied.");
										}
										selectedOpt = getSelectedOptionOfDropDown(driver,
												niam.getDisclaimerViewPopUpStatusPickList(60),
												"get selected option from status picklist", "text");
										System.out.println("selected option is  : " + selectedOpt);
										if (selectedOpt.equals("Accepted")) {
											appLog.info(selectedOpt + "is selected in name picklist.");

										} else {
											appLog.error("'" + selectedOpt + "' is not selected in name picklist.");
											sa.assertTrue(false,
													"'" + selectedOpt + "' is not selected in name picklist.");
										}
									} else {
										appLog.error("Accepted Option is not selected from status picklist.");
										sa.assertTrue(false, "Accepted Option is not selected drom status picklist.");
									}

									appLog.info("*******************************************");
									if (selectVisibleTextFromDropDown(driver, niam.getDisclaimerNamePickList(60),
											"Name Picklist", M4DIS2 + " (Active)")) {
										appLog.info(M4DIS2 + "is selected from disclaimer name picklist.");

										ele = FindElement(driver, "//span[@id='Disclaimer_Statistics-cell-0-0']/span",
												"No data error msg", action.BOOLEAN, 30);
										text = getAttribute(driver, ele, "No Data Error msg", "title");
										if (text.equalsIgnoreCase(
												NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage)) {
											appLog.info("No data to display error msg is verified.");
										} else {
											appLog.error("No data error messgae is not verified. Expected: "
													+ NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage
													+ "\tActual: " + text);
											sa.assertTrue(false,
													"No data error messgae is not verified. Expected: "
															+ NavatarInvestorAddOnsErrorMessage.NoDataToDisplayErrorMessage
															+ "\tActual: " + text);
										}

										String clkHereText1 = getText(driver,
												niam.getDisclaimerViewPopUpClickHereText(60), "Click here text",
												action.BOOLEAN);
										System.out.println("click here link text is :" + clkHereText1);
										if (clkHereText1
												.equals("Click here to export all the statistics for this fund.")) {

											appLog.info(
													"Click here link text verified.Expected: Click here to export all the statistics for this fund.\tActual: "
															+ clkHereText1);
										} else {
											appLog.error(
													"Click here link text is not verified.Expected: Click here to export all the statistics for this fund.\tActual: "
															+ clkHereText1);
											sa.assertTrue(false,
													"Click here link is not verified.Expected: Click here to export all the statistics for this fund.\tActual: "
															+ clkHereText1);

										}
										if (niam.getDisclaimerViewPopUpRecordCount() == 0) {
											appLog.info(
													"Disclaimer Statistics popup record count is verfied and equals to : "
															+ niam.getDisclaimerViewPopUpRecordCount());
										} else {
											appLog.error("record count is not verfied.");
											sa.assertTrue(false, "record count is not verfied.");
										}
										selectedOpt = getSelectedOptionOfDropDown(driver,
												niam.getDisclaimerNamePickList(80),
												"get selected option from name picklist", "text");
										System.out.println("selected option is  : " + selectedOpt);
										if (selectedOpt.equals(M4DIS2 + " (Active)")) {
											appLog.info(selectedOpt + "is selected in name picklist.");
										} else {
											appLog.error("Disclaimer 2 is not selected in name picklist.");
											sa.assertTrue(false, "Disclaimer 2 is not selected in name picklist.");
										}
										appLog.info("*******************************");
										if (selectVisibleTextFromDropDown(driver,
												niam.getDisclaimerViewPopUpStatusPickList(40), "Status Picklist",
												"All")) {
											appLog.info(" All Option is selected from dropdown successfully.");
											if (niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email,
													M4I1, "Waiting", "")) {
												appLog.info(M4CFN1 + " " + M4CLN1 + " contact detail is verfied ");
											} else {
												appLog.error("contact detail 1 is not verfied.");
												sa.assertTrue(false, "contact detail 1  is not verfied.");
											}
											if (niam.getDisclaimerViewPopUpRecordCount() == 1) {
												appLog.info(
														"Disclaimer Statistics popup record count is verfied and equals to : "
																+ niam.getDisclaimerViewPopUpRecordCount());
											} else {
												appLog.error("record count is not verfied.");
												sa.assertTrue(false, "record count is not verfied.");
											}
											selectedOpt = getSelectedOptionOfDropDown(driver,
													niam.getDisclaimerNamePickList(80),
													"get selected option from name picklist", "text");
											System.out.println("selected option is  : " + selectedOpt);
											if (selectedOpt.equals(M4DIS2 + " (Active)")) {
												appLog.info(selectedOpt + "is selected in name picklist.");
											} else {
												appLog.error("Disclaimer 2 is not selected in name picklist.");
												sa.assertTrue(false, "Disclaimer 2 is not selected in name picklist.");
											}
											selectedOpt = getSelectedOptionOfDropDown(driver,
													niam.getDisclaimerViewPopUpStatusPickList(60),
													"get selected option from status picklist", "text");
											System.out.println("selected option is  : " + selectedOpt);
											if (selectedOpt.equals("All")) {
												appLog.info(selectedOpt + "is selected in name picklist.");

											} else {
												appLog.error("'" + selectedOpt + "' is not selected in name picklist.");
												sa.assertTrue(false,
														"'" + selectedOpt + "' is not selected in name picklist.");
											}
											appLog.info("*******************************");
											if (click(driver, niam.getDisclaimerViewPopupSearchCrossIcon(60),
													"Search Box Cross Icon", action.BOOLEAN)) {
												appLog.info("Successfully clicked on Cross Icon ");
												if (niam.verifyDisclaimerStatisticsGrid(M4CFN2 + " " + M4CLN2,
														M4C2Email, M4I2, "Waiting", "")) {
													appLog.info(M4CFN2 + " " + M4CLN2 + " contact detail is verfied ");
												} else {
													appLog.error("contact detail 2 is not verfied.");
													sa.assertTrue(false, "contact detail 2 is not verfied.");
												}
												if (niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1,
														M4C1Email, M4I1, "Waiting", "")) {
													appLog.info(M4CFN1 + " " + M4CLN1 + " contact detail is verfied ");
												} else {
													appLog.error("contact detail 1 is not verfied.");
													sa.assertTrue(false, "contact detail 1  is not verfied.");
												}
												selectedOpt = getSelectedOptionOfDropDown(driver,
														niam.getDisclaimerNamePickList(80),
														"get selected option from name picklist", "text");
												System.out.println("selected option is  : " + selectedOpt);
												if (selectedOpt.equals(M4DIS2 + " (Active)")) {
													appLog.info(selectedOpt + "is selected in name picklist.");

												} else {
													appLog.error("Disclaimer 2 is not selected in name picklist.");
													sa.assertTrue(false,
															"Disclaimer 2 is not selected in name picklist.");
												}
												selectedOpt = getSelectedOptionOfDropDown(driver,
														niam.getDisclaimerViewPopUpStatusPickList(60),
														"get selected option from status picklist", "text");
												System.out.println("selected option is  : " + selectedOpt);
												if (selectedOpt.equals("All")) {
													appLog.info(selectedOpt + "is selected in name picklist.");

												} else {
													appLog.error(
															"'" + selectedOpt + "' is not selected in name picklist.");
													sa.assertTrue(false,
															"'" + selectedOpt + "' is not selected in name picklist.");
												}
											} else {
												appLog.error(" Not able to click on search box cross icon. ");
												sa.assertTrue(false, " Not able to click on search box cross icon. ");
											}
											if (sendKeys(driver, niam.getDisclaimerViewPopUpSearchBox(60), contact1,
													"send value in searchbox", action.BOOLEAN)) {
												appLog.info(contact1 + "value successfully  passed in search box");
												if (click(driver, niam.getDisclaimerViewPopupSearchIcon(60),
														"click on Search icon ", action.BOOLEAN)) {
													appLog.info(" click on search icon sucessfully. ");
													if (selectVisibleTextFromDropDown(driver,
															niam.getDisclaimerViewPopUpStatusPickList(40),
															"Status Picklist", "Accepted")) {
														appLog.info(
																" Accepted Option is selected from dropdown successfully.");
														if (click(driver, niam.getDisclaimerViewPopUpCrossIcon(60),
																"disclaimer View Popup Cross Icon", action.BOOLEAN)) {
															appLog.info(
																	"Clicked on View Popup Cross Icon Successfully");
															if (niam.clickOnViewLinkOfDisclaimer(M4DIS2)) {
																appLog.info("Disclaimer Statistics Popup is opened.");
																selectedOpt = getSelectedOptionOfDropDown(driver,
																		niam.getDisclaimerViewPopUpStatusPickList(60),
																		"get selected option from status picklist",
																		"text");
																System.out.println(
																		"selected option is  : " + selectedOpt);
																if (selectedOpt.equals("All")) {
																	appLog.info(selectedOpt
																			+ "is selected in name picklist.");

																} else {
																	appLog.error("'" + selectedOpt
																			+ "' is not selected in name picklist.");
																	sa.assertTrue(false, "'" + selectedOpt
																			+ "' is not selected in name picklist.");
																}
															} else {
																appLog.error(
																		"Disclaimer Statistics Popup is not openend.");
																sa.assertTrue(false,
																		"Disclaimer Statistics Popup is not openend.");
															}

														} else {
															appLog.error(
																	" not able to Clicked on View Popup Cross Icon. ");
															sa.assertTrue(false,
																	" not able to Clicked on View Popup Cross Icon ");
														}
													} else {
														appLog.error(" not able to select Accepted Option. ");
														sa.assertTrue(false, " not able to select Accepted Option. ");
													}
												} else {
													appLog.error(" not able to click on search icon. ");
													sa.assertTrue(false, " not able to click on search icon. ");
												}
											} else {
												appLog.error(" '" + contact1 + "' value not passed in search box. ");
												sa.assertTrue(false,
														" '" + contact1 + "' value not passed in search box. ");
											}
										} else {
											appLog.error("All Option is not selected from status picklist.");
											sa.assertTrue(false, "All Option is not selected drom status picklist.");
										}
									} else {
										appLog.error(M4DIS2 + "is not selected from disclaimer name picklist.");
										sa.assertTrue(false,
												"'" + M4DIS2 + "'is not selected from disclaimer name picklist.");
									}
								} else {
									appLog.error(" not able to click on search icon. ");
									sa.assertTrue(false, " not able to click on search icon. ");
								}
							} else {
								appLog.error(" '" + contact1 + "' value not passed in search box. ");
								sa.assertTrue(false, " '" + contact1 + "' value not passed in search box. ");
							}
						} else {
							appLog.error(M4DIS1 + "is not selected from disclaimer name picklist.");
							sa.assertTrue(false, "'" + M4DIS1 + "'is not selected from disclaimer name picklist.");
						}
					} else {
						appLog.error("Accepted  Option is not selected from status picklist.");
						sa.assertTrue(false, "Accepted Option is not selected drom status picklist.");
					}

				} else {
					appLog.error("Disclaimer Statistics Popup is not openend.");
					sa.assertTrue(false, "Disclaimer Statistics Popup is not openend.");
				}
			} else {
				appLog.error(M4F1 + " fund name is not present in the drop down, So not able to select.");
				sa.assertTrue(false, M4F1 + " fund name is not present in the drop down, So not able to select.");
			}

		} else {
			appLog.error("Navatar addon tab cannot be clicked, So cannot check the UI.");
			sa.assertTrue(false, "Navatar addon tab cannot be clicked, So cannot check the UI.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();

	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc025_VerifyLinksDisclaimerStatisticsPopup() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		String parentID = null;
		WebElement ele;
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		if (lp.clickOnTab(TabName.NavatarInvestorAddOns)) {
			if (switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60))) {
				System.err.println("Successfully switched to frame.");
				switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
			}
			if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60),
					"Select fund drop down on Navatar Investor add-ons page", M4F1)) {
				appLog.info(M4F1 + "value is select from dropdown.");
				if (niam.clickOnViewLinkOfDisclaimer(M4DIS1)) {
					appLog.info("Disclaimer Statistics Popup is opened.");
					String contactName1 = M4CFN1 + " " + M4CLN1;
					if (niam.clickOnContactNameLink(contactName1)) {
						appLog.info("Clicked on Contact 1 Successfully.");
						ThreadSleep(5000);
						parentID = switchOnWindow(driver);
						if (parentID != null) {
							ele = cp.getContactFullNameInViewMode(environment,mode,60);
							if (ele!=null && ele.getText().equalsIgnoreCase(contactName1)) {
								appLog.info(
										"" + contactName1 + " detail Page is opened in new tab and has been verified.");
							} else {
								appLog.error(
										"Contact detail Page is not opened in new tab after click on Contact Name Link.");
								sa.assertTrue(false,
										"Contact detail Page is not opened in new tab after click on Contact Name Link.");
							}
							driver.close();
							driver.switchTo().window(parentID);
							if (switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60))) {
								System.err.println("Successfully switched to frame.");
								switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
							}
						} else {
							appLog.error("not switched to Contact detail page.");
							sa.assertTrue(false, "not switched to Contact detail page.");
						}
					} else {
						appLog.error("Not able click on Contact1 Link");
						sa.assertTrue(false, "Not able click on Contact1 Link.");
					}

					if (niam.clickOnFirmNameLink(M4I1)) {
						appLog.info("Clicked on contact1 firm" + M4I1 + "Successfully.");
						ThreadSleep(5000);
						parentID = switchOnWindow(driver);
						if (parentID != null) {
							ele = ip.getLegalNameLabelTextbox(60);
							if (ele!=null && ele.getText().trim().contains(M4I1)) {
								appLog.info("institutions page for " + M4I1
										+ " is opened successfully after clicking on firm name in content grid");
							} else {
								appLog.error(
										"instittuions page could not be opened after clicking on firm name on content grid");
								sa.assertTrue(false,
										"instittuions page could not be opened after clicking on firm name on content grid");
							}
							driver.close();
							driver.switchTo().window(parentID);
							if (switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60))) {
								System.err.println("Successfully switched to frame.");
								switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
							}
						} else {
							appLog.error("not switched to Firm detail page.");
							sa.assertTrue(false, "not switched to Firm detail page.");
						}
					} else {
						appLog.error("Not able click on Firm Link");
						sa.assertTrue(false, "Not able click on Firm Link.");
					}
					String emailId = M4C1Email;
					System.out.println(emailId);
					if (niam.clickOnEmailLink(emailId)) {
						appLog.info("Clicked on Contact 1 Email " + emailId + " Successfully.");
						if (!np.verifyNavatarSalesTeamLinkFunctionality("ContactEmail")) {
							appLog.info("Verification of Contact Email link is unsuccessfull.");
							sa.assertTrue(false, "Verification of Contact Email link is unsuccessfull.");
						} else {
							appLog.info("Verification of Contact Email Link is successfully");
						}
					} else {
						appLog.error("Not able click on Contact1 Link");
						sa.assertTrue(false, "Not able click on Contact1 Link.");
					}
					if (click(driver, niam.getDisclaimerViewPopUpCrossIcon(60), "Cross Icon", action.BOOLEAN)) {
						appLog.info("Click on View Popup Cross Icon Successfully");
					}
				} else {
					appLog.error(" Not able to click on View Popup Cross Icon. ");
					sa.assertTrue(false, " Not able to click on View Popup Cross Icon.");
				}
			} else {
				appLog.error(M4F1 + " fund name is not present in the drop down, So not able to select.");
				sa.assertTrue(false, M4F1 + " fund name is not present in the drop down, So not able to select.");
			}

		} else {
			appLog.error("Navatar addon tab cannot be clicked, So cannot check the UI.");
			sa.assertTrue(false, "Navatar addon tab cannot be clicked, So cannot check the UI.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();

	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc034_CloseWorkspaceForFund2() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M4F2)) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
						"Fundraising Section view");
				if (fp.closeWorkSpace(Workspace.FundraisingWorkspace, 60)) {
					appLog.info("Able to close fundraising workspace");
				} else {
					appLog.info("Not able to close fundraising workspace");
					sa.assertTrue(false, "Not able to click on close workspace");
				}
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30),
						"Investor Section view");
				if (fp.closeWorkSpace(Workspace.InvestorWorkspace, 60)) {
					appLog.info("Able to close investor workspace");
				} else {
					appLog.info("Not able to close investor workspace");
					sa.assertTrue(false, "Not able to click on close workspace");
				}
				if (bp.clickOnTab(TabName.NavatarInvestorAddOns)) {
					switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60));
					switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
					if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60), "Select Fund Dropdown",
							M4F1)) {
						if (niam.verifyDisclaimerGrid(DisclaimerGrid.Deactivate, M4DIS2, DisclaimerGrid.View,
								DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
							appLog.info("content grid is verfied successfully.");
						} else {
							appLog.error("Content grid is not verfied.");
							sa.assertTrue(false, "Content grid is not verfied.");
						}
						if (niam.verifyDisclaimerGrid(DisclaimerGrid.Activate, M4DIS1, DisclaimerGrid.View,
								DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
							appLog.info("content grid is verfied successfully.");
						} else {
							appLog.error("Content grid is not verfied.");
							sa.assertTrue(false, "Content grid is not verfied.");
						}
						int recordCount = niam.getDisclaimerRecordCount();
						if (recordCount >= 2) {
							appLog.info("Record count is verified");
						} else {
							appLog.info("Record count is not verified");
							sa.assertTrue(false, "Record Count is not verified");
						}
						String activeDisclaimerStatistics = niam.getActiveDisclaimerStatisticsValue();
						if (activeDisclaimerStatistics.equalsIgnoreCase(M4DIS2)) {
							appLog.info("Disclaimer 2 is displaying");
						} else {
							appLog.info("Discalimer 2 is not displaying");
							sa.assertTrue(false, "Disclaimer 2 is not displaying");
						}
						int waitingRecordCount = niam.getWaitingDisclaimerCount();
						if (waitingRecordCount == 2) {
							appLog.info("Waiting disclaimer count is veroified");
						} else {
							appLog.info("Waiting Disclaimer count is not verified");
							sa.assertTrue(false, "Waiting disclaimer count is not verified");
						}
						int AcceptedRecordCount = niam.getAcceptedDiscalimerCount();
						if (AcceptedRecordCount == 0) {
							appLog.info("Accepted disclaimer count is verified");
						} else {
							appLog.info("Accepted Disclaimer count is not verified");
							sa.assertTrue(false, "Accepted disclaimer count is not verified");
						}
					} else {
						appLog.info("Not able to select fund from dropdown");
						sa.assertTrue(false, "Not able to sleect fund from dropdown");
					}
					if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60), "Select Fund Dropdown",
							M4F2)) {
						if (niam.verifyDisclaimerGrid(DisclaimerGrid.Deactivate, M4DIS1, DisclaimerGrid.View,
								DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
							appLog.info("content grid is verfied successfully.");
						} else {
							appLog.error("Content grid is not verfied.");
							sa.assertTrue(false, "Content grid is not verfied.");
						}
						int recordCount = niam.getDisclaimerRecordCount();
						if (recordCount >= 1) {
							appLog.info("Record count is verified");
						} else {
							appLog.info("Record count is not verified");
							sa.assertTrue(false, "Record Count is not verified");
						}
						String activeDisclaimerStatistics = niam.getActiveDisclaimerStatisticsValue();
						if (activeDisclaimerStatistics.equalsIgnoreCase(M4DIS1)) {
							appLog.info("Disclaimer 1 is displaying");
						} else {
							appLog.info("Discalimer 1 is not displaying");
							sa.assertTrue(false, "Disclaimer 1 is not displaying");
						}
						int waitingRecordCount = niam.getWaitingDisclaimerCount();
						if (waitingRecordCount == 1) {
							appLog.info("Waiting disclaimer count is veroified");
						} else {
							appLog.info("Waiting Disclaimer count is not verified");
							sa.assertTrue(false, "Waiting disclaimer count is not verified");
						}
						int AcceptedRecordCount = niam.getAcceptedDiscalimerCount();
						if (AcceptedRecordCount == 1) {
							appLog.info("Accepted disclaimer count is verified");
						} else {
							appLog.info("Accepted Disclaimer count is not verified");
							sa.assertTrue(false, "Accepted disclaimer count is not verified");
						}
					} else {
						appLog.info("Not able to select fund from dropdown");
						sa.assertTrue(false, "Not able to sleect fund from dropdown");
					}
					if (niam.clickOnViewLinkOfDisclaimer(M4DIS1)) {
						appLog.info("clicked on view link");
						if (niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1, "Accepted",
								getSystemDate("MM/dd/yyyy"))
								|| niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1,
										"Accepted", previousOrForwardDate(-1, "MM/dd/yyyy"))) {
							appLog.info("Discalimer statistics grid is verified for contact:" + M4CFN1 + " " + M4CLN1);
						} else {
							appLog.info(
									"Discalimer statistics grid is not verified for contact:" + M4CFN1 + " " + M4CLN1);
							sa.assertTrue(false,
									"Discalimer statistics grid is verified for contact:" + M4CFN1 + " " + M4CLN1);
						}
						if (niam.verifyDisclaimerStatisticsGrid(M4CFN2 + " " + M4CLN2, M4C2Email, M4I2, "Waiting",
								"")) {
							appLog.info("Discalimer statistics grid is verified for contact:" + M4CFN2 + " " + M4CLN2);
						} else {
							appLog.info(
									"Discalimer statistics grid is not verified for contact:" + M4CFN2 + " " + M4CLN2);
							sa.assertTrue(false,
									"Discalimer statistics grid is verified for contact:" + M4CFN2 + " " + M4CLN2);
						}
						if (niam.getDisclaimerViewPopUpRecordCount() == 2) {
							appLog.info("Disclaimer view popup record cout is verified");
						} else {
							appLog.info("Disclaimer view popup record cout is not verified");
							sa.assertTrue(false, "Disclaimer view popup record cout is not verified");
						}
					} else {
						appLog.info("Not able to click on view link");
						sa.assertTrue(false, "Not able to click on view link");
					}
				} else {
					appLog.info("Not able to click on navatar investor add ons tab");
					sa.assertTrue(false, "Not able to click on navatar invetsor add ons tab");
				}
			} else {
				appLog.info("Not able to click on created fund: " + M4F2);
				sa.assertTrue(false, "Not able to click on created fund: " + M4F2);
			}
		} else {
			appLog.info("Not able to click on funds tab");
			sa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc035_AgainInviteContactsForFund2() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		String[] stdPath = ExcelUtils
				.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath)
				.split(",");
		lp.CRMLogin(Org3CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M4F2)) {
				if (fp.inviteContact(environment, mode, M4I2, M4C1Email, stdPath[0], FolderType.Standard, "Upload", "Yes",
						"No", null, Workspace.FundraisingWorkspace, M4C1Email)) {
					appLog.info("Successfully provided access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
				}
				if (fp.inviteContact(environment, mode, M4I2, M4C2Email, stdPath[0], FolderType.Standard, "Upload", "Yes",
						"No", null, Workspace.FundraisingWorkspace, null)) {
					appLog.info("Successfully provided access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
				}
				if (fp.inviteContact(environment, mode, M4I3 + "/" + M4LP1, M4C1Email, stdPath[0], FolderType.Standard,
						"Upload", "Yes", "No", null, Workspace.InvestorWorkspace, M4C1Email)) {
					appLog.info("Successfully provided access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
				}
				if (fp.inviteContact(environment, mode, M4I3 + "/" + M4LP1, M4C2Email, stdPath[0], FolderType.Standard,
						"Upload", "Yes", "No", null, Workspace.InvestorWorkspace, M4C2Email)) {
					appLog.info("Successfully provided access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
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
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc035_AgainInviteContactsForFund2AndCheckImpactAtContact1InvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		DisclaimerPageBussinessLayer dp = new DisclaimerPageBussinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.investorLogin(M4C1Email, adminPassword);
		if (isDisplayed(driver, bp.getPendingDisclaimerPopGoToDisclaimerButton(60), "visibility", 30,
				"Go to discliamer popup") != null) {
			appLog.info("go to disclaimer popup is displaying");
			if (click(driver, bp.getPendingDisclaimerPopGoToDisclaimerButton(60), "Go to disclaimer button",
					action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on go to discalimer button");
				String parentID = switchOnWindow(driver);
				if (parentID != null) {
					List<WebElement> totalFundRow = FindElements(driver,
							"//div[@class='contacts_n_name_div_Investment']", "Total FundsRow");
					if (totalFundRow.size() == 1) {
						appLog.info("1 fund is displaying");
					} else {
						appLog.info("1 Fund is not displaying");
						sa.assertTrue(false, "1 Fund is not displaying");
					}
					if (dp.verifyFundDisplaying(M4F1, Org3FirmName)) {
						appLog.info("Fund is displaying");
					} else {
						appLog.info("Fund is not displaying and fund name is not verified");
						sa.assertTrue(false, "Fund Is Not displaying and fund name is not verified");
					}
					List<WebElement> disclaimerNames = FindElements(driver,
							"//div[contains(@id,'filterGridContact')]//b", "DisclaimerNames");
					if (disclaimerNames.isEmpty()) {
						appLog.error("No disclaimer is present on the disclaimer page.");
						sa.assertTrue(false, "No disclaimer is present on the disclaimer page.");
					} else {
						for (int i = 0; i < disclaimerNames.size(); i++) {
							if (trim(getText(driver, disclaimerNames.get(i), "Disclaimer name", action.BOOLEAN))
									.equalsIgnoreCase(M4DIS2)) {
								appLog.info("disclaimerName is verified.");
							} else {
								if (i == disclaimerNames.size()) {
									appLog.error("disclaimer Name is not present on the page.");
									sa.assertTrue(false, "disclaimer Name is not present on the page.");
								}
							}
						}
					}
					if (dp.verifyAcceptButton(M4F1, M4DIS2)) {
						appLog.info("Accept button is displaying for discalimer 2 for fund 1");
					} else {
						appLog.info("Accept button is not displaying for discalimer 2 for fund 1");
						sa.assertTrue(false, "Accept button is not displaying for discalimer 2 for fund 1");
					}
					driver.close();
					driver.switchTo().window(parentID);
				} else {
					appLog.info("No new window open");
					sa.assertTrue(false, "No new window is open");
				}
			} else {
				appLog.info("Not able to click on go to discalimer button");
				sa.assertTrue(false, "Not able to click on go to disclaimer button");
			}
		} else {
			appLog.info("go to disclaimer popup is not displaying");
			sa.assertTrue(false, "go to disclaimer popup is not displaying");
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc035_AgainInviteContactsForFund2AndCheckImpactAtContact2InvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		DisclaimerPageBussinessLayer dp = new DisclaimerPageBussinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.investorLogin(M4C2Email, adminPassword);
		if (isDisplayed(driver, bp.getPendingDisclaimerPopGoToDisclaimerButton(60), "visibility", 30,
				"Go to discliamer popup") != null) {
			appLog.info("go to disclaimer popup is displaying");
			if (click(driver, bp.getPendingDisclaimerPopGoToDisclaimerButton(60), "Go to disclaimer button",
					action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on go to discalimer button");
				String parentID = switchOnWindow(driver);
				if (parentID != null) {
					List<WebElement> totalFundRow = FindElements(driver,
							"//div[@class='contacts_n_name_div_Investment']", "Total FundsRow");
					if (totalFundRow.size() == 2) {
						appLog.info("2 fund are displaying");
					} else {
						appLog.info("2 Fund are not displaying");
						sa.assertTrue(false, "2 Fund are not displaying");
					}
					if (dp.verifyFundDisplaying(M4F1, Org3FirmName)) {
						appLog.info(M4F1 + "Fund is displaying");
					} else {
						appLog.info(M4F1 + "Fund is not displaying and fund name is not verified");
						sa.assertTrue(false, M4F1 + "Fund Is Not displaying and fund name is not verified");
					}
					if (dp.verifyFundDisplaying(M4F2, Org3FirmName)) {
						appLog.info(M4F2 + "Fund is displaying");
					} else {
						appLog.info(M4F2 + "Fund is not displaying and fund name is not verified");
						sa.assertTrue(false, M4F2 + "Fund Is Not displaying and fund name is not verified");
					}
					if (dp.verifyAcceptButton(M4F1, M4DIS2)) {
						appLog.info("Accept button is displaying for discalimer 2 for fund 1");
					} else {
						appLog.info("Accept button is not displaying for discalimer 2 for fund 1");
						sa.assertTrue(false, "Accept button is not displaying for discalimer 2 for fund 1");
					}
					if (dp.verifyAcceptButton(M4F2, M4DIS1)) {
						appLog.info("Accept button is displaying for discalimer 1 for fund 2");
					} else {
						appLog.info("Accept button is not displaying for discalimer 1 for fund 2");
						sa.assertTrue(false, "Accept button is not displaying for discalimer 1 for fund 2");
					}
					driver.close();
					driver.switchTo().window(parentID);
				} else {
					appLog.info("No new window open");
					sa.assertTrue(false, "No new window is open");
				}
			} else {
				appLog.info("Not able to click on go to discalimer button");
				sa.assertTrue(false, "Not able to click on go to disclaimer button");
			}
		} else {
			appLog.info("go to disclaimer popup is not displaying");
			sa.assertTrue(false, "go to disclaimer popup is not displaying");
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc035_AgainInviteContactsForFund2AndCheckImpactAtAdminUserSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if (bp.clickOnTab(TabName.NavatarInvestorAddOns)) {
			switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60));
			switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
			if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60), "Select Fund Dropdown", M4F1)) {
				if (niam.verifyDisclaimerGrid(DisclaimerGrid.Deactivate, M4DIS2, DisclaimerGrid.View,
						DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
					appLog.info("content grid is verfied successfully.");
				} else {
					appLog.error("Content grid is not verfied.");
					sa.assertTrue(false, "Content grid is not verfied.");
				}
				if (niam.verifyDisclaimerGrid(DisclaimerGrid.Activate, M4DIS1, DisclaimerGrid.View,
						DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
					appLog.info("content grid is verfied successfully.");
				} else {
					appLog.error("Content grid is not verfied.");
					sa.assertTrue(false, "Content grid is not verfied.");
				}
				int recordCount = niam.getDisclaimerRecordCount();
				if (recordCount >= 2) {
					appLog.info("Record count is verified");
				} else {
					appLog.info("Record count is not verified");
					sa.assertTrue(false, "Record Count is not verified");
				}
				String activeDisclaimerStatistics = niam.getActiveDisclaimerStatisticsValue();
				if (activeDisclaimerStatistics.equalsIgnoreCase(M4DIS2)) {
					appLog.info("Disclaimer 2 is displaying");
				} else {
					appLog.info("Discalimer 2 is not displaying");
					sa.assertTrue(false, "Disclaimer 2 is not displaying");
				}
				int waitingRecordCount = niam.getWaitingDisclaimerCount();
				if (waitingRecordCount == 2) {
					appLog.info("Waiting disclaimer count is veroified");
				} else {
					appLog.info("Waiting Disclaimer count is not verified");
					sa.assertTrue(false, "Waiting disclaimer count is not verified");
				}
				int AcceptedRecordCount = niam.getAcceptedDiscalimerCount();
				if (AcceptedRecordCount == 0) {
					appLog.info("Accepted disclaimer count is verified");
				} else {
					appLog.info("Accepted Disclaimer count is not verified");
					sa.assertTrue(false, "Accepted disclaimer count is not verified");
				}
			} else {
				appLog.info("Not able to select fund from dropdown");
				sa.assertTrue(false, "Not able to sleect fund from dropdown");
			}
			if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60), "Select Fund Dropdown", M4F2)) {
				if (niam.verifyDisclaimerGrid(DisclaimerGrid.Deactivate, M4DIS1, DisclaimerGrid.View,
						DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
					appLog.info("content grid is verfied successfully.");
				} else {
					appLog.error("Content grid is not verfied.");
					sa.assertTrue(false, "Content grid is not verfied.");
				}
				int recordCount = niam.getDisclaimerRecordCount();
				if (recordCount >= 1) {
					appLog.info("Record count is verified");
				} else {
					appLog.info("Record count is not verified");
					sa.assertTrue(false, "Record Count is not verified");
				}
				String activeDisclaimerStatistics = niam.getActiveDisclaimerStatisticsValue();
				if (activeDisclaimerStatistics.equalsIgnoreCase(M4DIS1)) {
					appLog.info("Disclaimer 1 is displaying");
				} else {
					appLog.info("Discalimer 1 is not displaying");
					sa.assertTrue(false, "Disclaimer 1 is not displaying");
				}
				int waitingRecordCount = niam.getWaitingDisclaimerCount();
				if (waitingRecordCount == 1) {
					appLog.info("Waiting disclaimer count is veroified");
				} else {
					appLog.info("Waiting Disclaimer count is not verified");
					sa.assertTrue(false, "Waiting disclaimer count is not verified");
				}
				int AcceptedRecordCount = niam.getAcceptedDiscalimerCount();
				if (AcceptedRecordCount == 1) {
					appLog.info("Accepted disclaimer count is verified");
				} else {
					appLog.info("Accepted Disclaimer count is not verified");
					sa.assertTrue(false, "Accepted disclaimer count is not verified");
				}
			} else {
				appLog.info("Not able to select fund from dropdown");
				sa.assertTrue(false, "Not able to sleect fund from dropdown");
			}
			if (niam.clickOnViewLinkOfDisclaimer(M4DIS1)) {
				appLog.info("clicked on view link");
				if (niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1, "Accepted",
						getSystemDate("MM/dd/yyyy"))
						|| niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1, "Accepted",
								previousOrForwardDate(-1, "MM/dd/yyyy"))) {
					appLog.info("Discalimer statistics grid is verified for contact:" + M4CFN1 + " " + M4CLN1);
				} else {
					appLog.info("Discalimer statistics grid is not verified for contact:" + M4CFN1 + " " + M4CLN1);
					sa.assertTrue(false,
							"Discalimer statistics grid is not verified for contact:" + M4CFN1 + " " + M4CLN1);
				}
				if (niam.verifyDisclaimerStatisticsGrid(M4CFN2 + " " + M4CLN2, M4C2Email, M4I2, "Waiting", "")) {
					appLog.info("Discalimer statistics grid is verified for contact:" + M4CFN2 + " " + M4CLN2);
				} else {
					appLog.info("Discalimer statistics grid is not verified for contact:" + M4CFN2 + " " + M4CLN2);
					sa.assertTrue(false,
							"Discalimer statistics grid is not verified for contact:" + M4CFN2 + " " + M4CLN2);
				}
				if (niam.getDisclaimerViewPopUpRecordCount() == 2) {
					appLog.info("Disclaimer view popup record cout is verified");
				} else {
					appLog.info("Disclaimer view popup record cout is not verified");
					sa.assertTrue(false, "Disclaimer view popup record cout is not verified");
				}
			} else {
				appLog.info("Not able to click on view link");
				sa.assertTrue(false, "Not able to click on view link");
			}
		} else {
			appLog.info("Not able to click on navatar investor add ons tab");
			sa.assertTrue(false, "Not able to click on navatar invetsor add ons tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc036_ClearWorkspaceForFund2() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M4F2)) {
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
						"Fundraising Section view");
				if (fp.clearWorkSpace(Workspace.FundraisingWorkspace, 60)) {
					appLog.info("Able to clear fundraising workspace");
				} else {
					appLog.info("Not able to clear fundraising workspace");
					sa.assertTrue(false, "Not able to clear workspace");
				}
				switchToFrame(driver, 30, bp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30),
						"Investor Section view");
				if (fp.clearWorkSpace(Workspace.InvestorWorkspace, 60)) {
					appLog.info("Able to clear investor workspace");
				} else {
					appLog.info("Not able to clear investor workspace");
					sa.assertTrue(false, "Not able to clear workspace");
				}
				if (bp.clickOnTab(TabName.NavatarInvestorAddOns)) {
					switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60));
					switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
					List<WebElement> lstOfEle = allOptionsInDropDrop(driver, niam.getSelectFundDropDown(60),
							"Options in name picklist");
					boolean flag_Fund1 = false;
					boolean flag_Fund2 = false;
					for (int i = 0; i < lstOfEle.size(); i++) {
						if (lstOfEle.get(i).getText().trim().equals(M4F1)) {
							flag_Fund1 = true;
							appLog.info(lstOfEle.get(i).getText().trim() + "is present in name picklist");
						} else if (lstOfEle.get(i).getText().trim().equals(M4F2)) {
							flag_Fund1 = true;
							appLog.info(lstOfEle.get(i).getText().trim() + "is present in name picklist");
						}
					}
					if (flag_Fund1 == false) {
						appLog.error("Fund 1 is not present in name picklist");
						sa.assertTrue(false, "Fund 1 is not present in name picklist");
					}
					if (flag_Fund2 == true) {
						appLog.error("Fund 2 is present in name picklist");
						sa.assertTrue(false, "Fund 2 is present in name picklist");
					}
				} else {
					appLog.info("not able to click on navatar investor add ons tab");
					sa.assertTrue(false, "not able to click on navatar investor add ons tab");
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
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc037_CreateWorkspaceForFund2AndVerifySelectFundPicklist() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		lp.CRMLogin(Org3CRMUser1EmailID, adminPassword);
		if (lp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M4F2)) {
				String Size=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M4F2", excelLabel.Fund_Size);
				String vintageyear=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M4F2", excelLabel.Fund_VintageYear);
				String contact=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M4F2", excelLabel.Fund_Contact);
				String phone=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M4F2", excelLabel.Fund_Phone);
				String email=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M4F2", excelLabel.Fund_Email);
				String description=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M4F2", excelLabel.Fund_Description);
				String[] step1Of3Data= {Size,vintageyear,contact,phone,email,description};
				
				if (fp.buildWorkspace(step1Of3Data, WorkSpaceAction.IMPORTFOLDERTEMPLATE, folderTemplateName, null,
						M4I1 + "<break>" + M4I2, Workspace.FundraisingWorkspace, 60)) {
					appLog.info("Fundraising Workspace is built successfully.");
				} else {
					appLog.error("Fundraising workspace is not built successfully.");
					sa.assertTrue(false, "Fundraising workspace is not built successfully.");
				}
			} else {
				appLog.error(M4F2 + " fund is not availabe in the list.");
				sa.assertTrue(false, M4F2 + " fund is not availabe in the list.");
			}
		} else {
			appLog.error("Funds Tab cannot be clicked, so cannot build the workspace.");
			sa.assertTrue(false, "Funds Tab cannot be clicked, so cannot build the workspace.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		bp = new BasePageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		if (bp.clickOnTab(TabName.NavatarInvestorAddOns)) {
			switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60));
			switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
			List<WebElement> lstOfEle = allOptionsInDropDrop(driver, niam.getSelectFundDropDown(60),
					"Options in name picklist");
			boolean flag_Fund1 = false;
			boolean flag_Fund2 = false;
			for (int i = 0; i < lstOfEle.size(); i++) {
				if (lstOfEle.get(i).getText().trim().equals(M4F2)) {
					flag_Fund2 = true;
					appLog.info(lstOfEle.get(i).getText().trim() + "is present in name picklist");
				} else if (lstOfEle.get(i).getText().trim().equals(M4F1)) {
					flag_Fund1 = true;
					appLog.info(lstOfEle.get(i).getText().trim() + "is present in name picklist");
				}
			}
			if (flag_Fund1 == false) {
				appLog.error("Fund 1 is not present in name picklist");
				sa.assertTrue(false, "Fund 1 is not present in name picklist");
			}
			if (flag_Fund2 == false) {
				appLog.error("Fund 2 is not present in name picklist");
				sa.assertTrue(false, "Fund 2 is not present in name picklist");
			}
			if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60), "Select Fund Dropdown", M4F2)) {
				if (niam.verifyDisclaimerGrid(DisclaimerGrid.Deactivate, M4DIS1, DisclaimerGrid.View,
						DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
					appLog.info("content grid is verfied successfully.");
				} else {
					appLog.error("Content grid is not verfied.");
					sa.assertTrue(false, "Content grid is not verfied.");
				}
				int recordCount = niam.getDisclaimerRecordCount();
				if (recordCount == 1) {
					appLog.info("Record count is verified");
				} else {
					appLog.info("Record count is not verified");
					sa.assertTrue(false, "Record Count is not verified");
				}
				String activeDisclaimerStatistics = niam.getActiveDisclaimerStatisticsValue();
				if (activeDisclaimerStatistics.equalsIgnoreCase(M4DIS1)) {
					appLog.info("Disclaimer 1 is displaying");
				} else {
					appLog.info("Discalimer 1 is not displaying");
					sa.assertTrue(false, "Disclaimer 1 is not displaying");
				}
				int waitingRecordCount = niam.getWaitingDisclaimerCount();
				if (waitingRecordCount == 1) {
					appLog.info("Waiting disclaimer count is veroified");
				} else {
					appLog.info("Waiting Disclaimer count is not verified");
					sa.assertTrue(false, "Waiting disclaimer count is not verified");
				}
				int AcceptedRecordCount = niam.getAcceptedDiscalimerCount();
				if (AcceptedRecordCount == 1) {
					appLog.info("Accepted disclaimer count is verified");
				} else {
					appLog.info("Accepted Disclaimer count is not verified");
					sa.assertTrue(false, "Accepted disclaimer count is not verified");
				}
				if (niam.clickOnViewLinkOfDisclaimer(M4DIS1)) {
					appLog.info("clicked on view link");
					if (niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1, "Accepted",
							getSystemDate("MM/dd/yyyy"))
							|| niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1, "Accepted",
									previousOrForwardDate(-1, "MM/dd/yyyy"))) {
						appLog.info("Discalimer statistics grid is verified for contact:" + M4CFN1 + " " + M4CLN1);
					} else {
						appLog.info("Discalimer statistics grid is not verified for contact:" + M4CFN1 + " " + M4CLN1);
						sa.assertTrue(false,
								"Discalimer statistics grid is not verified for contact:" + M4CFN1 + " " + M4CLN1);
					}
					if (niam.verifyDisclaimerStatisticsGrid(M4CFN2 + " " + M4CLN2, M4C2Email, M4I2, "Waiting", "")) {
						appLog.info("Discalimer statistics grid is verified for contact:" + M4CFN2 + " " + M4CLN2);
					} else {
						appLog.info("Discalimer statistics grid is not verified for contact:" + M4CFN2 + " " + M4CLN2);
						sa.assertTrue(false,
								"Discalimer statistics grid is not verified for contact:" + M4CFN2 + " " + M4CLN2);
					}
					if (niam.getDisclaimerViewPopUpRecordCount() == 2) {
						appLog.info("Disclaimer view popup record cout is verified");
					} else {
						appLog.info("Disclaimer view popup record cout is not verified");
						sa.assertTrue(false, "Disclaimer view popup record cout is not verified");
					}
				} else {
					appLog.info("Not able to click on view link");
					sa.assertTrue(false, "Not able to click on view link");
				}
			} else {
				appLog.info("Not able to select fund from dropdown");
				sa.assertTrue(false, "Not able to sleect fund from dropdown");
			}
		} else {
			appLog.info("Not able to click on navatar investor add ons tab");
			sa.assertTrue(false, "Not able to click on navatar investor add ons tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc038_AgainInviteContactsForFund2AndAlsoVerifySearchingOfNonRegisteredContact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		String[] stdPath = ExcelUtils
				.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath)
				.split(",");
		lp.CRMLogin(Org3CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M4F2)) {
				if (fp.inviteContact(environment, mode, M4I2, M4C1Email, stdPath[0], FolderType.Standard, "Upload", "Yes",
						"No", null, Workspace.FundraisingWorkspace, M4C1Email)) {
					appLog.info("Successfully provided access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
				}
				if (fp.inviteContact(environment, mode, M4I2, M4C2Email, stdPath[0], FolderType.Standard, "Upload", "Yes",
						"No", null, Workspace.FundraisingWorkspace, null)) {
					appLog.info("Successfully provided access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
				}
				if (fp.inviteContact(environment, mode, M4I2, M4C3Email, stdPath[0], FolderType.Standard, "Upload", "Yes",
						"No", null, Workspace.FundraisingWorkspace, M4C3Email)) {
					appLog.info("Successfully provided access to contact '" + M4CFN3 + " " + M4CLN3 + "'.");
				} else {
					appLog.error("Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
					sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN3 + " " + M4CLN3 + "'.");
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
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc038_AgainInviteContactsForFund2AndAndCheckImpactAtContact1Side() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		DisclaimerPageBussinessLayer dp = new DisclaimerPageBussinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.investorLogin(M4C1Email, adminPassword);
		if (isDisplayed(driver, bp.getPendingDisclaimerPopGoToDisclaimerButton(60), "visibility", 30,
				"Go to discliamer popup") != null) {
			appLog.info("go to disclaimer popup is displaying");
			if (click(driver, bp.getPendingDisclaimerPopGoToDisclaimerButton(60), "Go to disclaimer button",
					action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on go to discalimer button");
				String parentID = switchOnWindow(driver);
				if (parentID != null) {
					List<WebElement> totalFundRow = FindElements(driver,
							"//div[@class='contacts_n_name_div_Investment']", "Total FundsRow");
					if (totalFundRow.size() == 1) {
						appLog.info("1 fund is displaying");
					} else {
						appLog.info("1 Fund is not displaying");
						sa.assertTrue(false, "1 Fund is not displaying");
					}
					if (dp.verifyFundDisplaying(M4F1, Org3FirmName)) {
						appLog.info("Fund is displaying");
					} else {
						appLog.info("Fund is not displaying and fund name is not verified");
						sa.assertTrue(false, "Fund Is Not displaying and fund name is not verified");
					}
					List<WebElement> disclaimerNames = FindElements(driver,
							"//div[contains(@id,'filterGridContact')]//b", "DisclaimerNames");
					if (disclaimerNames.isEmpty()) {
						appLog.error("No disclaimer is present on the disclaimer page.");
						sa.assertTrue(false, "No disclaimer is present on the disclaimer page.");
					} else {
						for (int i = 0; i < disclaimerNames.size(); i++) {
							if (trim(getText(driver, disclaimerNames.get(i), "Disclaimer name", action.BOOLEAN))
									.equalsIgnoreCase(M4DIS2)) {
								appLog.info("disclaimerName is verified.");
							} else {
								if (i == disclaimerNames.size()) {
									appLog.error("disclaimer Name is not present on the page.");
									sa.assertTrue(false, "disclaimer Name is not present on the page.");
								}
							}
						}
					}
					if (dp.verifyAcceptButton(M4F1, M4DIS2)) {
						appLog.info("Accept button is displaying for discalimer 2 for fund 1");
					} else {
						appLog.info("Accept button is not displaying for discalimer 2 for fund 1");
						sa.assertTrue(false, "Accept button is not displaying for discalimer 2 for fund 1");
					}
					driver.close();
					driver.switchTo().window(parentID);
				} else {
					appLog.info("No new window open");
					sa.assertTrue(false, "No new window is open");
				}
			} else {
				appLog.info("Not able to click on go to discalimer button");
				sa.assertTrue(false, "Not able to click on go to disclaimer button");
			}
		} else {
			appLog.info("go to disclaimer popup is not displaying");
			sa.assertTrue(false, "go to disclaimer popup is not displaying");
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc038_AgainInviteContactsForFund2AndAndCheckImpactAtContact2Side() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		DisclaimerPageBussinessLayer dp = new DisclaimerPageBussinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.investorLogin(M4C2Email, adminPassword);
		if (isDisplayed(driver, bp.getPendingDisclaimerPopGoToDisclaimerButton(60), "visibility", 30,
				"Go to discliamer popup") != null) {
			appLog.info("go to disclaimer popup is displaying");
			if (click(driver, bp.getPendingDisclaimerPopGoToDisclaimerButton(60), "Go to disclaimer button",
					action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on go to discalimer button");
				String parentID = switchOnWindow(driver);
				if (parentID != null) {
					List<WebElement> totalFundRow = FindElements(driver,
							"//div[@class='contacts_n_name_div_Investment']", "Total FundsRow");
					if (totalFundRow.size() == 2) {
						appLog.info("2 fund are displaying");
					} else {
						appLog.info("2 Fund are not displaying");
						sa.assertTrue(false, "2 Fund are not displaying");
					}
					if (dp.verifyFundDisplaying(M4F1, Org3FirmName)) {
						appLog.info(M4F1 + "Fund is displaying");
					} else {
						appLog.info(M4F1 + "Fund is not displaying and fund name is not verified");
						sa.assertTrue(false, M4F1 + "Fund Is Not displaying and fund name is not verified");
					}
					if (dp.verifyFundDisplaying(M4F2, Org3FirmName)) {
						appLog.info(M4F2 + "Fund is displaying");
					} else {
						appLog.info(M4F2 + "Fund is not displaying and fund name is not verified");
						sa.assertTrue(false, M4F2 + "Fund Is Not displaying and fund name is not verified");
					}
					if (dp.verifyAcceptButton(M4F1, M4DIS2)) {
						appLog.info("Accept button is displaying for discalimer 2 for fund 1");
					} else {
						appLog.info("Accept button is not displaying for discalimer 2 for fund 1");
						sa.assertTrue(false, "Accept button is not displaying for discalimer 2 for fund 1");
					}
					if (dp.verifyAcceptButton(M4F2, M4DIS1)) {
						appLog.info("Accept button is displaying for discalimer 1 for fund 2");
					} else {
						appLog.info("Accept button is not displaying for discalimer 1 for fund 2");
						sa.assertTrue(false, "Accept button is not displaying for discalimer 1 for fund 2");
					}
					driver.close();
					driver.switchTo().window(parentID);
				} else {
					appLog.info("No new window open");
					sa.assertTrue(false, "No new window is open");
				}
			} else {
				appLog.info("Not able to click on go to discalimer button");
				sa.assertTrue(false, "Not able to click on go to disclaimer button");
			}
		} else {
			appLog.info("go to disclaimer popup is not displaying");
			sa.assertTrue(false, "go to disclaimer popup is not displaying");
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc038_AgainInviteContactsForFund2AndAndCheckImpactAtAdminUserSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if (bp.clickOnTab(TabName.NavatarInvestorAddOns)) {
			switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60));
			switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
			if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60), "Select Fund Dropdown", M4F2)) {
				if (niam.clickOnViewLinkOfDisclaimer(M4DIS1)) {
					appLog.info("clicked on view link");
					if (sendKeys(driver, niam.getDisclaimerViewPopUpSearchBox(60), M4CFN3 + " " + M4CLN3, "Contact 3",
							action.SCROLLANDBOOLEAN)) {
						if (click(driver, niam.getDisclaimerViewPopupSearchIcon(60), "Search Icon",
								action.SCROLLANDBOOLEAN)) {
							if (niam.verifyDisclaimerStatisticsGrid(M4CFN3 + " " + M4CLN3, M4C3Email, M4I3, "Waiting",
									"")) {
								appLog.info(
										"Discalimer statistics grid is verified for contact:" + M4CFN3 + " " + M4CLN3);
							} else {
								appLog.info("Discalimer statistics grid is not verified for contact:" + M4CFN3 + " "
										+ M4CLN3);
								sa.assertTrue(false, "Discalimer statistics grid is not verified for contact:" + M4CFN3
										+ " " + M4CLN3);
							}
						} else {
							appLog.info("Not able to click on search icon");
							sa.assertTrue(false, "Not able to click on search icon");
						}
					} else {
						appLog.info("Not able to enter value in search box");
						sa.assertTrue(false, "not able to enter value in search box");
					}
				} else {
					appLog.info("Not able to click on view link");
					sa.assertTrue(false, "Not able to click on view link");
				}
			} else {
				appLog.info("Not able to select fund from the dropdown");
				sa.assertTrue(false, "Not able to select fund from the dropdown");
			}
		} else {
			appLog.info("Not able to click on navatar investor add ons tab");
			sa.assertTrue(false, "Not able to click on navatar invetsor add ons tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc039_DeleteInvitedFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,
				excelLabel.StandardPath);
		lp.CRMLogin(Org3CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M4F2)) {
				switchToFrame(driver, 60, fp.getFrame( PageName.FundsPage, 60));
				if (click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 20), "manage folder icon",
						action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on manage foler icon");
					ThreadSleep(3000);
					String id = fp.getCreatedFolderId(stdPath, PageName.FundsPage, 60);
					if (id != null) {
						if (fp.clickOnDeleteFolderButton(id)) {
							ThreadSleep(2000);
							if (click(
									driver, fp.getFolderDeleteYesBtn(Workspace.FundraisingWorkspace,
											PageName.ManageFolderPopUp, 60),
									"folder delete yes button", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on " + stdPath + " folder delete yes button");
								if (click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 30),
										"manage folder close icon", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on manage folder close icon");
								} else {
									appLog.error("Not able to click on manage folder close icon");
									sa.assertTrue(false, "Not able to click on manage folder close icon");
								}
							} else {
								appLog.error("Not able to click on folder delete Yes button so cannot delete foler: "
										+ stdPath);
								sa.assertTrue(false,
										"Not able to click on folder delete Yes button so cannot delete foler: "
												+ stdPath);
							}
						} else {
							appLog.error("Not able to click on folder : " + stdPath
									+ " delete icon so cannot delete folder in fundraising workspace");
							sa.assertTrue(false, "Not able to click on folder : " + stdPath
									+ " delete icon so cannot delete folder in fundraising workspace");
						}
					} else {
						appLog.error("Not able to get child folder: " + stdPath + " id cannot delete it");
						sa.assertTrue(false, "Not able to get child folder: " + stdPath + " id cannot delete it");
					}
				} else {
					appLog.info("Not able to click on manage Folder icon");
					sa.assertTrue(false, "Not able ot click on manage folder icon");
				}
			} else {
				appLog.info("Not able to click on created fund");
				sa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on funds tab");
			sa.assertTrue(false, "Not able to click on created fund");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc039_DeleteInvitedFolderAndCheckImpactAtAdminUserside() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if (bp.clickOnTab(TabName.NavatarInvestorAddOns)) {
			scn.nextLine();
			switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60));
			switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
			List<WebElement> lstOfEle = allOptionsInDropDrop(driver, niam.getSelectFundDropDown(60),
					"Options in name picklist");
			boolean flag_Fund1 = false;
			boolean flag_Fund2 = false;
			for (int i = 0; i < lstOfEle.size(); i++) {
				if (lstOfEle.get(i).getText().trim().equals(M4F2)) {
					flag_Fund2 = true;
					appLog.info(lstOfEle.get(i).getText().trim() + "is present in name picklist");
				} else if (lstOfEle.get(i).getText().trim().equals(M4F1)) {
					flag_Fund1 = true;
					appLog.info(lstOfEle.get(i).getText().trim() + "is present in name picklist");
				}
			}
			if (flag_Fund1 == false) {
				appLog.error("Fund 1 is not present in name picklist");
				sa.assertTrue(false, "Fund 1 is not present in name picklist");
			}
			if (flag_Fund2 == false) {
				appLog.error("Fund 2 is not present in name picklist");
				sa.assertTrue(false, "Fund 2 is not present in name picklist");
			}
			if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60), "Select Fund Dropdown", M4F2)) {
				if (niam.verifyDisclaimerGrid(DisclaimerGrid.Deactivate, M4DIS1, DisclaimerGrid.View,
						DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
					appLog.info("content grid is verfied successfully.");
				} else {
					appLog.error("Content grid is not verfied.");
					sa.assertTrue(false, "Content grid is not verfied.");
				}
				int recordCount = niam.getDisclaimerRecordCount();
				if (recordCount == 1) {
					appLog.info("Record count is verified");
				} else {
					appLog.info("Record count is not verified");
					sa.assertTrue(false, "Record Count is not verified");
				}
				String activeDisclaimerStatistics = niam.getActiveDisclaimerStatisticsValue();
				if (activeDisclaimerStatistics.equalsIgnoreCase(M4DIS1)) {
					appLog.info("Disclaimer 1 is displaying");
				} else {
					appLog.info("Discalimer 1 is not displaying");
					sa.assertTrue(false, "Disclaimer 1 is not displaying");
				}
				int waitingRecordCount = niam.getWaitingDisclaimerCount();
				if (waitingRecordCount == 2) {
					appLog.info("Waiting disclaimer count is veroified");
				} else {
					appLog.info("Waiting Disclaimer count is not verified");
					sa.assertTrue(false, "Waiting disclaimer count is not verified");
				}
				int AcceptedRecordCount = niam.getAcceptedDiscalimerCount();
				if (AcceptedRecordCount == 1) {
					appLog.info("Accepted disclaimer count is verified");
				} else {
					appLog.info("Accepted Disclaimer count is not verified");
					sa.assertTrue(false, "Accepted disclaimer count is not verified");
				}
				if (niam.clickOnViewLinkOfDisclaimer(M4DIS1)) {
					appLog.info("clicked on view link");
					if (niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1, "Accepted",
							getSystemDate("MM/dd/yyyy"))
							|| niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1, "Accepted",
									previousOrForwardDate(-1, "MM/dd/yyyy"))) {
						appLog.info("Discalimer statistics grid is verified for contact:" + M4CFN1 + " " + M4CLN1);
					} else {
						appLog.info("Discalimer statistics grid is not verified for contact:" + M4CFN1 + " " + M4CLN1);
						sa.assertTrue(false,
								"Discalimer statistics grid is not verified for contact:" + M4CFN1 + " " + M4CLN1);
					}
					if (niam.verifyDisclaimerStatisticsGrid(M4CFN2 + " " + M4CLN2, M4C2Email, M4I2, "Waiting", "")) {
						appLog.info("Discalimer statistics grid is verified for contact:" + M4CFN2 + " " + M4CLN2);
					} else {
						appLog.info("Discalimer statistics grid is not verified for contact:" + M4CFN2 + " " + M4CLN2);
						sa.assertTrue(false,
								"Discalimer statistics grid is not verified for contact:" + M4CFN2 + " " + M4CLN2);
					}
					if (niam.verifyDisclaimerStatisticsGrid(M4CFN3 + " " + M4CLN3, M4C3Email, M4I3, "Waiting", "")) {
						appLog.info("Discalimer statistics grid is verified for contact:" + M4CFN3 + " " + M4CLN3);
					} else {
						appLog.info("Discalimer statistics grid is not verified for contact:" + M4CFN3 + " " + M4CLN3);
						sa.assertTrue(false,
								"Discalimer statistics grid is not verified for contact:" + M4CFN3 + " " + M4CLN3);
					}
					if (niam.getDisclaimerViewPopUpRecordCount() == 3) {
						appLog.info("Disclaimer view popup record cout is verified");
					} else {
						appLog.info("Disclaimer view popup record cout is not verified");
						sa.assertTrue(false, "Disclaimer view popup record cout is not verified");
					}
				} else {
					appLog.info("Not able to click on view link");
					sa.assertTrue(false, "Not able to click on view link");
				}
			} else {
				appLog.info("Not able to select fund from dropdown");
				sa.assertTrue(false, "Not able to sleect fund from dropdown");
			}
		} else {
			appLog.info("Not able to click on navatar investor add ons tab");
			sa.assertTrue(false, "Not able to click on navatar invetsor add ons tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc040_CreateFolderAndInviteContacts() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,
				excelLabel.StandardPath);

		lp.CRMLogin(Org3CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M4F2)) {
				switchToFrame(driver, 60, fp.getFrame( PageName.FundsPage, 60));
				if (click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 20), "manage folder icon",
						action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on manage foler icon");
					ThreadSleep(3000);
					if (fp.createParentFolder(stdPath, FolderType.Standard, PageName.ManageFolderPopUp,
							Workspace.FundraisingWorkspace, 60)) {
						if (click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 60),
								"Mansge folder lcose button", action.SCROLLANDBOOLEAN)) {
							switchToDefaultContent(driver);
							if (fp.inviteContact(environment, mode, M4I2, M4C1Email,
									(stdPath.split(","))[0], FolderType.Standard, "Upload", "Yes", "No", null, Workspace.FundraisingWorkspace, M4C1Email)) {
								appLog.info("Successfully provided access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
							} else {
								appLog.error("Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
								sa.assertTrue(false,
										"Not able to provide access to contact '" + M4CFN1 + " " + M4CLN1 + "'.");
							}
							if (fp.inviteContact(environment, mode, M4I2, M4C2Email,
									(stdPath.split(","))[0], FolderType.Standard, "Upload", "Yes", "No", null, Workspace.FundraisingWorkspace, null)) {
								appLog.info("Successfully provided access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
							} else {
								appLog.error("Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
								sa.assertTrue(false,
										"Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
							}
						} else {
							appLog.info("Not able to click on close button");
							sa.assertTrue(false, "Not able to click on close button");
						}
					} else {
						appLog.info("Not able to create standard folder");
						sa.assertTrue(false, "Not able to create standard folder");
					}
				} else {
					appLog.info("Not able to click on manage Folder icon");
					sa.assertTrue(false, "Not able ot click on manage folder icon");
				}
			} else {
				appLog.info("Not able to click on created fund");
				sa.assertTrue(false, "Not able to click on created fund");
			}
		} else {
			appLog.info("Not able to click on funds tab");
			sa.assertTrue(false, "Not able to click on created fund");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc040_CreateFolderAndInviteContactsAndCheckImpactAtContact1Side() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		DisclaimerPageBussinessLayer dp = new DisclaimerPageBussinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.investorLogin(M4C1Email, adminPassword);
		if (isDisplayed(driver, bp.getPendingDisclaimerPopGoToDisclaimerButton(60), "visibility", 30,
				"Go to discliamer popup") != null) {
			appLog.info("go to disclaimer popup is displaying");
			if (click(driver, bp.getPendingDisclaimerPopGoToDisclaimerButton(60), "Go to disclaimer button",
					action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on go to discalimer button");
				String parentID = switchOnWindow(driver);
				if (parentID != null) {
					List<WebElement> totalFundRow = FindElements(driver,
							"//div[@class='contacts_n_name_div_Investment']", "Total FundsRow");
					if (totalFundRow.size() == 1) {
						appLog.info("1 fund is displaying");
					} else {
						appLog.info("1 Fund is not displaying");
						sa.assertTrue(false, "1 Fund is not displaying");
					}
					if (dp.verifyFundDisplaying(M4F1, Org3FirmName)) {
						appLog.info("Fund is displaying");
					} else {
						appLog.info("Fund is not displaying and fund name is not verified");
						sa.assertTrue(false, "Fund Is Not displaying and fund name is not verified");
					}
					List<WebElement> disclaimerNames = FindElements(driver,
							"//div[contains(@id,'filterGridContact')]//b", "DisclaimerNames");
					if (disclaimerNames.isEmpty()) {
						appLog.error("No disclaimer is present on the disclaimer page.");
						sa.assertTrue(false, "No disclaimer is present on the disclaimer page.");
					} else {
						for (int i = 0; i < disclaimerNames.size(); i++) {
							if (trim(getText(driver, disclaimerNames.get(i), "Disclaimer name", action.BOOLEAN))
									.equalsIgnoreCase(M4DIS2)) {
								appLog.info("disclaimerName is verified.");
							} else {
								if (i == disclaimerNames.size()) {
									appLog.error("disclaimer Name is not present on the page.");
									sa.assertTrue(false, "disclaimer Name is not present on the page.");
								}
							}
						}
					}
					if (dp.verifyAcceptButton(M4F1, M4DIS2)) {
						appLog.info("Accept button is displaying for discalimer 2 for fund 1");
					} else {
						appLog.info("Accept button is not displaying for discalimer 2 for fund 1");
						sa.assertTrue(false, "Accept button is not displaying for discalimer 2 for fund 1");
					}
					driver.close();
					driver.switchTo().window(parentID);
				} else {
					appLog.info("No new window open");
					sa.assertTrue(false, "No new window is open");
				}
			} else {
				appLog.info("Not able to click on go to discalimer button");
				sa.assertTrue(false, "Not able to click on go to disclaimer button");
			}
		} else {
			appLog.info("go to disclaimer popup is not displaying");
			sa.assertTrue(false, "go to disclaimer popup is not displaying");
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc040_CreateFolderAndInviteContactsAndCheckImpactAtContact2Side() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		DisclaimerPageBussinessLayer dp = new DisclaimerPageBussinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.investorLogin(M4C2Email, adminPassword);
		if (isDisplayed(driver, bp.getPendingDisclaimerPopGoToDisclaimerButton(60), "visibility", 30,
				"Go to discliamer popup") != null) {
			appLog.info("go to disclaimer popup is displaying");
			if (click(driver, bp.getPendingDisclaimerPopGoToDisclaimerButton(60), "Go to disclaimer button",
					action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on go to discalimer button");
				String parentID = switchOnWindow(driver);
				if (parentID != null) {
					List<WebElement> totalFundRow = FindElements(driver,
							"//div[@class='contacts_n_name_div_Investment']", "Total FundsRow");
					if (totalFundRow.size() == 2) {
						appLog.info("2 fund are displaying");
					} else {
						appLog.info("2 Fund are not displaying");
						sa.assertTrue(false, "2 Fund are not displaying");
					}
					if (dp.verifyFundDisplaying(M4F1, Org3FirmName)) {
						appLog.info(M4F1 + "Fund is displaying");
					} else {
						appLog.info(M4F1 + "Fund is not displaying and fund name is not verified");
						sa.assertTrue(false, M4F1 + "Fund Is Not displaying and fund name is not verified");
					}
					if (dp.verifyFundDisplaying(M4F2, Org3FirmName)) {
						appLog.info(M4F2 + "Fund is displaying");
					} else {
						appLog.info(M4F2 + "Fund is not displaying and fund name is not verified");
						sa.assertTrue(false, M4F2 + "Fund Is Not displaying and fund name is not verified");
					}
					if (dp.verifyAcceptButton(M4F1, M4DIS2)) {
						appLog.info("Accept button is displaying for discalimer 2 for fund 1");
					} else {
						appLog.info("Accept button is not displaying for discalimer 2 for fund 1");
						sa.assertTrue(false, "Accept button is not displaying for discalimer 2 for fund 1");
					}
					if (dp.verifyAcceptButton(M4F2, M4DIS1)) {
						appLog.info("Accept button is displaying for discalimer 1 for fund 2");
					} else {
						appLog.info("Accept button is not displaying for discalimer 1 for fund 2");
						sa.assertTrue(false, "Accept button is not displaying for discalimer 1 for fund 2");
					}
					driver.close();
					driver.switchTo().window(parentID);
				} else {
					appLog.info("No new window open");
					sa.assertTrue(false, "No new window is open");
				}
			} else {
				appLog.info("Not able to click on go to discalimer button");
				sa.assertTrue(false, "Not able to click on go to disclaimer button");
			}
		} else {
			appLog.info("go to disclaimer popup is not displaying");
			sa.assertTrue(false, "go to disclaimer popup is not displaying");
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc040_CreateFolderAndInviteContactsAndCheckImpactAtAdminUserSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if (bp.clickOnTab(TabName.NavatarInvestorAddOns)) {
			switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60));
			switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
			List<WebElement> lstOfEle = allOptionsInDropDrop(driver, niam.getSelectFundDropDown(60),
					"Options in name picklist");
			boolean flag_Fund1 = false;
			boolean flag_Fund2 = false;
			for (int i = 0; i < lstOfEle.size(); i++) {
				if (lstOfEle.get(i).getText().trim().equals(M4F2)) {
					flag_Fund2 = true;
					appLog.info(lstOfEle.get(i).getText().trim() + "is present in name picklist");
				} else if (lstOfEle.get(i).getText().trim().equals(M4F1)) {
					flag_Fund1 = true;
					appLog.info(lstOfEle.get(i).getText().trim() + "is present in name picklist");
				}
			}
			if (flag_Fund1 == false) {
				appLog.error("Fund 1 is not present in name picklist");
				sa.assertTrue(false, "Fund 1 is not present in name picklist");
			}
			if (flag_Fund2 == false) {
				appLog.error("Fund 2 is not present in name picklist");
				sa.assertTrue(false, "Fund 2 is not present in name picklist");
			}
			if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60), "Select Fund Dropdown", M4F2)) {
				if (niam.verifyDisclaimerGrid(DisclaimerGrid.Deactivate, M4DIS1, DisclaimerGrid.View,
						DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
					appLog.info("content grid is verfied successfully.");
				} else {
					appLog.error("Content grid is not verfied.");
					sa.assertTrue(false, "Content grid is not verfied.");
				}
				int recordCount = niam.getDisclaimerRecordCount();
				if (recordCount == 1) {
					appLog.info("Record count is verified");
				} else {
					appLog.info("Record count is not verified");
					sa.assertTrue(false, "Record Count is not verified");
				}
				String activeDisclaimerStatistics = niam.getActiveDisclaimerStatisticsValue();
				if (activeDisclaimerStatistics.equalsIgnoreCase(M4DIS1)) {
					appLog.info("Disclaimer 1 is displaying");
				} else {
					appLog.info("Discalimer 1 is not displaying");
					sa.assertTrue(false, "Disclaimer 1 is not displaying");
				}
				int waitingRecordCount = niam.getWaitingDisclaimerCount();
				if (waitingRecordCount == 2) {
					appLog.info("Waiting disclaimer count is veroified");
				} else {
					appLog.info("Waiting Disclaimer count is not verified");
					sa.assertTrue(false, "Waiting disclaimer count is not verified");
				}
				int AcceptedRecordCount = niam.getAcceptedDiscalimerCount();
				if (AcceptedRecordCount == 1) {
					appLog.info("Accepted disclaimer count is verified");
				} else {
					appLog.info("Accepted Disclaimer count is not verified");
					sa.assertTrue(false, "Accepted disclaimer count is not verified");
				}
				if (niam.clickOnViewLinkOfDisclaimer(M4DIS1)) {
					appLog.info("clicked on view link");
					if (niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1, "Accepted",
							getSystemDate("MM/dd/yyyy"))
							|| niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1, "Accepted",
									previousOrForwardDate(-1, "MM/dd/yyyy"))) {
						appLog.info("Discalimer statistics grid is verified for contact:" + M4CFN1 + " " + M4CLN1);
					} else {
						appLog.info("Discalimer statistics grid is not verified for contact:" + M4CFN1 + " " + M4CLN1);
						sa.assertTrue(false,
								"Discalimer statistics grid is not verified for contact:" + M4CFN1 + " " + M4CLN1);
					}
					if (niam.verifyDisclaimerStatisticsGrid(M4CFN2 + " " + M4CLN2, M4C2Email, M4I2, "Waiting", "")) {
						appLog.info("Discalimer statistics grid is verified for contact:" + M4CFN2 + " " + M4CLN2);
					} else {
						appLog.info("Discalimer statistics grid is not verified for contact:" + M4CFN2 + " " + M4CLN2);
						sa.assertTrue(false,
								"Discalimer statistics grid is not verified for contact:" + M4CFN2 + " " + M4CLN2);
					}
					if (niam.verifyDisclaimerStatisticsGrid(M4CFN3 + " " + M4CLN3, M4C3Email, M4I3, "Waiting", "")) {
						appLog.info("Discalimer statistics grid is verified for contact:" + M4CFN3 + " " + M4CLN3);
					} else {
						appLog.info("Discalimer statistics grid is not verified for contact:" + M4CFN3 + " " + M4CLN3);
						sa.assertTrue(false,
								"Discalimer statistics grid is not verified for contact:" + M4CFN3 + " " + M4CLN3);
					}
					if (niam.getDisclaimerViewPopUpRecordCount() == 3) {
						appLog.info("Disclaimer view popup record count is verified");
					} else {
						appLog.info("Disclaimer view popup record count is not verified");
						sa.assertTrue(false, "Disclaimer view popup record count is not verified");
					}
				} else {
					appLog.info("Not able to click on view link");
					sa.assertTrue(false, "Not able to click on view link");
				}
			} else {
				appLog.info("Not able to select fund from dropdown");
				sa.assertTrue(false, "Not able to sleect fund from dropdown");
			}
		} else {
			appLog.info("Not able to click on navatar investor add ons tab");
			sa.assertTrue(false, "Not able to click on navatar invetsor add ons tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc041_RemoveContactAccessFromContactDetailPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		WebElement ele = null;
		lp.CRMLogin(Org3CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M4CFN1, M4CLN1, null)) {
				switchToFrame(driver, 30, bp.getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
						"Fundraising Workspace Section view");
				if (click(driver, cp.getRemoveContactAccessButton(Workspace.FundraisingWorkspace, 60),
						"Remove contact access button", action.SCROLLANDBOOLEAN)) {
					ThreadSleep(4000);
					ele = FindElement(driver, "//label[text()='" + M4F2 + "']/../..//a[@title='Remove']",
							"Fund 2 Remove link", action.SCROLLANDBOOLEAN, 60);
					if (click(driver, ele, "Remove Link", action.SCROLLANDBOOLEAN)) {
						String ParentID = switchOnWindow(driver);
						if (ParentID != null) {
							ThreadSleep(5000);
							switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
							driver.switchTo().window(ParentID);
							switchToFrame(driver, 30, bp.getFrame( PageName.ContactsPage, 30));
							scrollDownThroughWebelement(driver,
									bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
									"Fundraising Workspace Section view");
							if (click(driver, cp.getRemoveContactAccessButton(Workspace.FundraisingWorkspace, 60),
									"Remove contact access close button", action.SCROLLANDBOOLEAN)) {
								List<WebElement> listOfWorkspace = FindElements(driver,
										"//span[contains(@id,'ContactDetail_grid1-cell-1-')]//label", "WorkspacesName");
								if (listOfWorkspace.contains(M4F2)) {
									appLog.info("Contact access does not remove for fund " + M4F2);
									sa.assertTrue(false, "Contact access does not remove for fund " + M4F2);
								} else {
									appLog.info("Contact access successfully remove for fund " + M4F2);
								}
								if (click(driver,
										cp.getRemoveContactAccessPopupCloseButton(Workspace.FundraisingWorkspace, 60),
										"Close button", action.SCROLLANDBOOLEAN)) {
									appLog.info("Clicked on close button");
								} else {
									appLog.info("Not able to click on close button");
									sa.assertTrue(false, "Not able to click on close button");
								}
							} else {
								appLog.info("Not able to clcik on remove contact access close button");
								sa.assertTrue(false, "Not able to click on remove contact access close button");
							}
						} else {
							appLog.info("No new window to switch");
							sa.assertTrue(false, "No new window to switch");
						}
					} else {
						appLog.info("Not bale ot click on remove link");
						sa.assertTrue(false, "Not able to click on remove link");
					}
				} else {
					appLog.info("Not able to clcik on remove contact access close button");
					sa.assertTrue(false, "Not able to click on remove contact access close button");
				}
			} else {
				appLog.info("Not able to click on created contact");
				sa.assertTrue(false, "Not able to click on created contact");
			}
		} else {
			appLog.info("Not able to click on contacts tab");
			sa.assertTrue(false, "Not able to click on contacts tab");
		}
		switchToDefaultContent(driver);
		if (bp.clickOnTab(TabName.ContactTab)) {
			if (cp.clickOnCreatedContact(M4CFN2, M4CLN2, null)) {
				switchToFrame(driver, 30, bp.getFrame( PageName.ContactsPage, 30));
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
						"Fundraising Workspace Section view");
				if (click(driver, cp.getRemoveContactAccessButton(Workspace.FundraisingWorkspace, 60),
						"Remove contact access button", action.SCROLLANDBOOLEAN)) {
					ThreadSleep(4000);
					ele = FindElement(driver, "//label[text()='" + M4F2 + "']/../..//a[@title='Remove']",
							"Fund 2 Remove link", action.SCROLLANDBOOLEAN, 60);
					if (click(driver, ele, "Remove Link", action.SCROLLANDBOOLEAN)) {
						String ParentID = switchOnWindow(driver);
						if (ParentID != null) {
							ThreadSleep(5000);
							switchToAlertAndAcceptOrDecline(driver, 60, action.ACCEPT);
							driver.switchTo().window(ParentID);
							switchToFrame(driver, 30, bp.getFrame( PageName.ContactsPage, 30));
							scrollDownThroughWebelement(driver,
									bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30),
									"Fundraising Workspace Section view");
							if (click(driver, cp.getRemoveContactAccessButton(Workspace.FundraisingWorkspace, 60),
									"Remove contact access close button", action.SCROLLANDBOOLEAN)) {
								List<WebElement> listOfWorkspace = FindElements(driver,
										"//span[contains(@id,'ContactDetail_grid1-cell-1-')]//label", "WorkspacesName");
								if (listOfWorkspace.contains(M4F2)) {
									appLog.info("Contact access does not remove for fund " + M4F2);
									sa.assertTrue(false, "Contact access does not remove for fund " + M4F2);
								} else {
									appLog.info("Contact access successfully remove for fund " + M4F2);
								}
								if (click(driver,
										cp.getRemoveContactAccessPopupCloseButton(Workspace.FundraisingWorkspace, 60),
										"Close button", action.SCROLLANDBOOLEAN)) {
									appLog.info("Clicked on close button");
								} else {
									appLog.info("Not able to click on close button");
									sa.assertTrue(false, "Not able to click on close button");
								}
							} else {
								appLog.info("Not able to clcik on remove contact access close button");
								sa.assertTrue(false, "Not able to click on remove contact access close button");
							}
						} else {
							appLog.info("No new window to switch");
							sa.assertTrue(false, "No new window to switch");
						}
					} else {
						appLog.info("Not bale ot click on remove link");
						sa.assertTrue(false, "Not able to click on remove link");
					}
				} else {
					appLog.info("Not able to clcik on remove contact access close button");
					sa.assertTrue(false, "Not able to click on remove contact access close button");
				}
			} else {
				appLog.info("Not able to click on created contact");
				sa.assertTrue(false, "Not able to click on created contact");
			}
		} else {
			appLog.info("Not able to click on contacts tab");
			sa.assertTrue(false, "Not able to click on contacts tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();

	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc041_RemoveContactAccessFromContactDetailPageAndCheckImpactAtAdminUserside() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if (bp.clickOnTab(TabName.NavatarInvestorAddOns)) {
			switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60));
			switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
			List<WebElement> lstOfEle = allOptionsInDropDrop(driver, niam.getSelectFundDropDown(60),
					"Options in name picklist");
			boolean flag_Fund1 = false;
			boolean flag_Fund2 = false;
			for (int i = 0; i < lstOfEle.size(); i++) {
				if (lstOfEle.get(i).getText().trim().equals(M4F2)) {
					flag_Fund2 = true;
					appLog.info(lstOfEle.get(i).getText().trim() + "is present in name picklist");
				} else if (lstOfEle.get(i).getText().trim().equals(M4F1)) {
					flag_Fund1 = true;
					appLog.info(lstOfEle.get(i).getText().trim() + "is present in name picklist");
				}
			}
			if (flag_Fund1 == false) {
				appLog.error("Fund 1 is not present in name picklist");
				sa.assertTrue(false, "Fund 1 is not present in name picklist");
			}
			if (flag_Fund2 == false) {
				appLog.error("Fund 2 is not present in name picklist");
				sa.assertTrue(false, "Fund 2 is not present in name picklist");
			}
			if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60), "Select Fund Dropdown", M4F2)) {
				if (niam.verifyDisclaimerGrid(DisclaimerGrid.Deactivate, M4DIS1, DisclaimerGrid.View,
						DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
					appLog.info("content grid is verfied successfully.");
				} else {
					appLog.error("Content grid is not verfied.");
					sa.assertTrue(false, "Content grid is not verfied.");
				}
				int recordCount = niam.getDisclaimerRecordCount();
				if (recordCount == 1) {
					appLog.info("Record count is verified");
				} else {
					appLog.info("Record count is not verified");
					sa.assertTrue(false, "Record Count is not verified");
				}
				String activeDisclaimerStatistics = niam.getActiveDisclaimerStatisticsValue();
				if (activeDisclaimerStatistics.equalsIgnoreCase(M4DIS1)) {
					appLog.info("Disclaimer 1 is displaying");
				} else {
					appLog.info("Discalimer 1 is not displaying");
					sa.assertTrue(false, "Disclaimer 1 is not displaying");
				}
				int waitingRecordCount = niam.getWaitingDisclaimerCount();
				if (waitingRecordCount == 2) {
					appLog.info("Waiting disclaimer count is veroified");
				} else {
					appLog.info("Waiting Disclaimer count is not verified");
					sa.assertTrue(false, "Waiting disclaimer count is not verified");
				}
				int AcceptedRecordCount = niam.getAcceptedDiscalimerCount();
				if (AcceptedRecordCount == 1) {
					appLog.info("Accepted disclaimer count is verified");
				} else {
					appLog.info("Accepted Disclaimer count is not verified");
					sa.assertTrue(false, "Accepted disclaimer count is not verified");
				}
				if (niam.clickOnViewLinkOfDisclaimer(M4DIS1)) {
					appLog.info("clicked on view link");
					if (niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1, "Accepted",
							getSystemDate("MM/dd/yyyy"))
							|| niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1, "Accepted",
									previousOrForwardDate(-1, "MM/dd/yyyy"))) {
						appLog.info("Discalimer statistics grid is verified for contact:" + M4CFN1 + " " + M4CLN1);
					} else {
						appLog.info("Discalimer statistics grid is not verified for contact:" + M4CFN1 + " " + M4CLN1);
						sa.assertTrue(false,
								"Discalimer statistics grid is not verified for contact:" + M4CFN1 + " " + M4CLN1);
					}
					if (niam.verifyDisclaimerStatisticsGrid(M4CFN2 + " " + M4CLN2, M4C2Email, M4I2, "Waiting", "")) {
						appLog.info("Discalimer statistics grid is verified for contact:" + M4CFN2 + " " + M4CLN2);
					} else {
						appLog.info("Discalimer statistics grid is not verified for contact:" + M4CFN2 + " " + M4CLN2);
						sa.assertTrue(false,
								"Discalimer statistics grid is not verified for contact:" + M4CFN2 + " " + M4CLN2);
					}
					if (niam.verifyDisclaimerStatisticsGrid(M4CFN3 + " " + M4CLN3, M4C3Email, M4I3, "Waiting", "")) {
						appLog.info("Discalimer statistics grid is verified for contact:" + M4CFN3 + " " + M4CLN3);
					} else {
						appLog.info("Discalimer statistics grid is not verified for contact:" + M4CFN3 + " " + M4CLN3);
						sa.assertTrue(false,
								"Discalimer statistics grid is not verified for contact:" + M4CFN3 + " " + M4CLN3);
					}
					if (niam.getDisclaimerViewPopUpRecordCount() == 3) {
						appLog.info("Disclaimer view popup record count is verified");
					} else {
						appLog.info("Disclaimer view popup record count is not verified");
						sa.assertTrue(false, "Disclaimer view popup record count is not verified");
					}
				} else {
					appLog.info("Not able to click on view link");
					sa.assertTrue(false, "Not able to click on view link");
				}
			} else {
				appLog.info("Not able to select fund from dropdown");
				sa.assertTrue(false, "Not able to sleect fund from dropdown");
			}
		} else {
			appLog.info("Not able to click on navatar investor add ons tab");
			sa.assertTrue(false, "Not able to click on navatar invetsor add ons tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}


	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc046_VerifySortingInDisclaimerGrid(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if(lp.clickOnTab(TabName.NavatarInvestorAddOns)){
			switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60));
			switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
			if(selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60), "Select Fund Dropdown", M4F1)){
				
				/************************************Created On Date*************************************************/
				WebElement ele = FindElement(driver, "(//span[@id='grid_DefaultFoldersView1-headers']/span[contains(@id,'grid_DefaultFoldersView1-header-')]//span[@class='aw-grid-sort '])[5]", "Created on Date Sort Icon", action.BOOLEAN, 30);
				if(isDisplayed(driver, ele, "visibility", 30, "Created On Date Sort Icon")!=null){
					appLog.info("By default sorting is on created on date.");
				} else {
					appLog.error("By Default Sorting is not on created on date.");
					sa.assertTrue(false,"By Default Sorting is not on created on date.");
				}
				List<WebElement> dataInColumn = FindElements(driver, "//span[contains(@id,'grid_DefaultFoldersView1-row-')]//span[6]/div", "Created on date column data.");
				if(checkSorting(driver, SortOrder.Decending, dataInColumn)){
					appLog.info("Created on date data is in decending order.");
				} else {
					appLog.error("Created on date data is not in decending order.");
					sa.assertTrue(false,"Created on date data is not in decending order.");
				}
				ele = FindElement(driver, "(//span[@id='grid_DefaultFoldersView1-headers']/span[contains(@id,'grid_DefaultFoldersView1-header-')]//span[@class='aw-item-text '])[5]", "Created on Date", action.BOOLEAN, 30);
				if(click(driver, ele, "Created Date", action.BOOLEAN)){
					dataInColumn = FindElements(driver, "//span[contains(@id,'grid_DefaultFoldersView1-row-')]//span[6]/div", "Created on date column data.");
					if(checkSorting(driver, SortOrder.Assecending, dataInColumn)){
						appLog.info("Created on date data is in Assecending order.");
					} else {
						appLog.error("Created on date data is not in Assecending order.");
						sa.assertTrue(false,"Created on date data is not in Assecending order.");
					}
				} else {
					appLog.error("Cannot click on created date header, So cannot check sorting in assecending order.");
					sa.assertTrue(false,"Cannot click on created date header, So cannot check sorting in assecending order.");
				}
				
				/************************************Activated On Date*************************************************/
				ele = FindElement(driver, "(//span[@id='grid_DefaultFoldersView1-headers']/span[contains(@id,'grid_DefaultFoldersView1-header-')]//span[@class='aw-item-text '])[4]", "Activated on Date", action.BOOLEAN, 30);
				if(click(driver, ele, "Activated on date", action.BOOLEAN)){
					dataInColumn = FindElements(driver, "//span[contains(@id,'grid_DefaultFoldersView1-row-')]//span[5]/div", "Activated on date column data.");
					if(checkSorting(driver, SortOrder.Assecending, dataInColumn)){
						appLog.info("Activated on date data is in Assecending order.");
					} else {
						appLog.error("Activated on date data is not in Assecending order.");
						sa.assertTrue(false,"Activated on date data is not in Assecending order.");
					}
				} else {
					appLog.error("Cannot click on Activated on date header, So cannot check sorting in assecending order.");
					sa.assertTrue(false,"Cannot click on Activated on date header, So cannot check sorting in assecending order.");
				}
				ele = FindElement(driver, "(//span[@id='grid_DefaultFoldersView1-headers']/span[contains(@id,'grid_DefaultFoldersView1-header-')]//span[@class='aw-item-text '])[4]", "Activated on Date", action.BOOLEAN, 30);
				if(click(driver, ele, "Created Date", action.BOOLEAN)){
					dataInColumn = FindElements(driver, "//span[contains(@id,'grid_DefaultFoldersView1-row-')]//span[5]/div", "Activated on date column data.");
					if(checkSorting(driver, SortOrder.Decending, dataInColumn)){
						appLog.info("Activated on date data is in Decending order.");
					} else {
						appLog.error("Activated on date data is not in Decending order.");
						sa.assertTrue(false,"Activated on date data is not in Decending order.");
					}
				} else {
					appLog.error("Cannot click on Activated date header, So cannot check sorting in Decending order.");
					sa.assertTrue(false,"Cannot click on Activated date header, So cannot check sorting in Decending order.");
				}
				
				/*************************************Disclaimer Name Column************************************************/
				ele = FindElement(driver, "(//span[@id='grid_DefaultFoldersView1-headers']/span[contains(@id,'grid_DefaultFoldersView1-header-')]//span[@class='aw-item-text '])[2]", "Disclaimer Name", action.BOOLEAN, 30);
				if(click(driver, ele, "Disclaimer Name", action.BOOLEAN)){
					dataInColumn = FindElements(driver, "//span[contains(@id,'grid_DefaultFoldersView1-row-')]//span[3]/a", "Disclaimer Name column data.");
					if(checkSorting(driver, SortOrder.Assecending, dataInColumn)){
						appLog.info("Disclaimer Name data is in Assecending order.");
					} else {
						appLog.error("Disclaimer Name data is not in Assecending order.");
						sa.assertTrue(false,"Disclaimer Name data is not in Assecending order.");
					}
				} else {
					appLog.error("Cannot click on Disclaimer Name header, So cannot check sorting in assecending order.");
					sa.assertTrue(false,"Cannot click on Disclaimer Name header, So cannot check sorting in assecending order.");
				}
				ele = FindElement(driver, "(//span[@id='grid_DefaultFoldersView1-headers']/span[contains(@id,'grid_DefaultFoldersView1-header-')]//span[@class='aw-item-text '])[2]", "Disclaimer Name", action.BOOLEAN, 30);
				if(click(driver, ele, "Created Date", action.BOOLEAN)){
					dataInColumn = FindElements(driver, "//span[contains(@id,'grid_DefaultFoldersView1-row-')]//span[3]/a", "Disclaimer Name column data.");
					if(checkSorting(driver, SortOrder.Decending, dataInColumn)){
						appLog.info("Disclaimer Name data is in Decending order.");
					} else {
						appLog.error("Disclaimer Name data is not in Decending order.");
						sa.assertTrue(false,"Disclaimer Name data is not in Decending order.");
					}
				} else {
					appLog.error("Cannot click on Disclaimer Name header, So cannot check sorting in Decending order.");
					sa.assertTrue(false,"Cannot click on Disclaimer Name header, So cannot check sorting in Decending order.");
				}
				
				/*************************************Statistics Column************************************************/
				ele = FindElement(driver, "(//span[@id='grid_DefaultFoldersView1-headers']/span[contains(@id,'grid_DefaultFoldersView1-header-')]//span[@class='aw-item-text '])[3]", "Statistics", action.BOOLEAN, 30);
				if(click(driver, ele, "Statistics column", action.BOOLEAN)){
					ele = FindElement(driver, "(//span[@id='grid_DefaultFoldersView1-headers']/span[contains(@id,'grid_DefaultFoldersView1-header-')]//span[@class='aw-grid-sort '])[3]", "Statistics Sort Icon", action.BOOLEAN, 30);
					if(isDisplayed(driver, ele, "visibility", 5, "Statistics Sort Icon")==null){
						appLog.info("Sorting is not working on statistics column.");
					} else {
						appLog.error("Sorting is working on statistics column.");
						sa.assertTrue(false,"Sorting is working on statistics column.");
					}
				} else {
					appLog.error("Cannot click on statistics column, So cannot check if sorting is working or not.");
					sa.assertTrue(false,"Cannot click on statistics column, So cannot check if sorting is working or not.");
				}
				
				/*************************************Actions Column************************************************/
				ele = FindElement(driver, "(//span[@id='grid_DefaultFoldersView1-headers']/span[contains(@id,'grid_DefaultFoldersView1-header-')]//span[@class='aw-item-text '])[1]", "Actions", action.BOOLEAN, 30);
				if(click(driver, ele, "Actions column", action.BOOLEAN)){
					ele = FindElement(driver, "(//span[@id='grid_DefaultFoldersView1-headers']/span[contains(@id,'grid_DefaultFoldersView1-header-')]//span[@class='aw-grid-sort '])[1]", "Actions Sort Icon", action.BOOLEAN, 30);
					if(isDisplayed(driver, ele, "visibility", 5, "Actions Sort Icon")==null){
						appLog.info("Sorting is not working on Actions column.");
					} else {
						appLog.error("Sorting is working on Actions column.");
						sa.assertTrue(false,"Sorting is working on Actions column.");
					}
				} else {
					appLog.error("Cannot click on Actions column, So cannot check if sorting is working or not.");
					sa.assertTrue(false,"Cannot click on Actions column, So cannot check if sorting is working or not.");
				}
				
			} else {
				appLog.error(M4F1+" fund is not present in the drop down.");
				sa.assertTrue(false,M4F1+" fund is not present in the drop down.");
			}
			
		} else {
			appLog.error("Navatar Investor Add Ons tab cannot be clicked, So cannot check sorting on disclaimer grid.");
			sa.assertTrue(false,"Navatar Investor Add Ons tab cannot be clicked, So cannot check sorting on disclaimer grid.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc047_VerifySortingInDisclaimerGrid(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if(lp.clickOnTab(TabName.NavatarInvestorAddOns)){
			switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60));
			switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
			if(selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60), "Select Fund Dropdown", "Updated"+M4F2)){
				if(niam.clickOnViewLinkOfDisclaimer(M4DIS1)){
					/***********************************Accepted on date**********************************/
					WebElement ele = FindElement(driver, "//span[contains(@id,'Disclaimer_Statistics-header-')][5]//span[@class='aw-grid-sort ']", "Accepted on Date Sort Icon", action.BOOLEAN, 30);
					if(isDisplayed(driver, ele, "visibility", 30, "Accepted On Date Sort Icon")!=null){
						appLog.info("By default sorting is on Accepted on date.");
					} else {
						appLog.error("By Default Sorting is not on Accepted on date.");
						sa.assertTrue(false,"By Default Sorting is not on Accepted on date.");
					}
					List<WebElement> dataInColumn = FindElements(driver, "//span[@id='Disclaimer_Statistics-rows-start']/following-sibling::span[contains(@id,'Disclaimer_Statistics-row-')]/span[6]/span", "Accepted on date column data.");
					if(checkSorting(driver, SortOrder.Decending, dataInColumn)){
						appLog.info("Accepted on date data is in decending order.");
					} else {
						appLog.error("Accepted on date data is not in decending order.");
						sa.assertTrue(false,"Accepted on date data is not in decending order.");
					}
					ele = FindElement(driver, "//span[contains(@id,'Disclaimer_Statistics-header-')][5]//span[@class='aw-item-text ']", "Accepted on Date", action.BOOLEAN, 30);
					if(click(driver, ele, "Accepted on Date", action.BOOLEAN)){
						dataInColumn = FindElements(driver, "//span[@id='Disclaimer_Statistics-rows-start']/following-sibling::span[contains(@id,'Disclaimer_Statistics-row-')]/span[6]/span", "Accepted on date column data.");
						if(checkSorting(driver, SortOrder.Assecending, dataInColumn)){
							appLog.info("Accepted on date data is in Assecending order.");
						} else {
							appLog.error("Accepted on date data is not in Assecending order.");
							sa.assertTrue(false,"Accepted on date data is not in Assecending order.");
						}
					} else {
						appLog.error("Cannot click on Accepted On date header, So cannot check sorting in assecending order.");
						sa.assertTrue(false,"Cannot click on Accepted On date header, So cannot check sorting in assecending order.");
					}
					
					/************************************Contact Name***************************************/
					
					ele = FindElement(driver, "(//span[contains(@id,'Disclaimer_Statistics-header-')])[1]//span[@class='aw-item-text ']", "Contact Name", action.BOOLEAN, 30);
					if(click(driver, ele, "Contact Name", action.BOOLEAN)){
						dataInColumn = FindElements(driver, "//span[@id='Disclaimer_Statistics-rows-start']/following-sibling::span[contains(@id,'Disclaimer_Statistics-row-')]/span[2]/a", "Contact Name column data.");
						if(checkSorting(driver, SortOrder.Assecending, dataInColumn)){
							appLog.info("Contact Name data is in Assecending order.");
						} else {
							appLog.error("Contact Name data is not in Assecending order.");
							sa.assertTrue(false,"Contact Name data is not in Assecending order.");
						}
					} else {
						appLog.error("Cannot click on Contact Name header, So cannot check sorting in assecending order.");
						sa.assertTrue(false,"Cannot click on Contact Name header, So cannot check sorting in assecending order.");
					}
					ele = FindElement(driver, "(//span[contains(@id,'Disclaimer_Statistics-header-')])[1]//span[@class='aw-item-text ']", "Contact Name", action.BOOLEAN, 30);
					if(click(driver, ele, "Contact Name", action.BOOLEAN)){
						dataInColumn = FindElements(driver, "//span[@id='Disclaimer_Statistics-rows-start']/following-sibling::span[contains(@id,'Disclaimer_Statistics-row-')]/span[2]/a", "Contact Name column data.");
						if(checkSorting(driver, SortOrder.Decending, dataInColumn)){
							appLog.info("Contact Name data is in Decending order.");
						} else {
							appLog.error("Contact Name data is not in Decending order.");
							sa.assertTrue(false,"Contact Name data is not in Decending order.");
						}
					} else {
						appLog.error("Cannot click on Contact Name header, So cannot check sorting in Decending order.");
						sa.assertTrue(false,"Cannot click on Contact Name header, So cannot check sorting in Decending order.");
					}
					
					/*********************************Email Column*************************************/
					
					ele = FindElement(driver, "//span[contains(@id,'Disclaimer_Statistics-header-')][2]//span[@class='aw-item-text ']", "Email", action.BOOLEAN, 30);
					if(click(driver, ele, "Email", action.BOOLEAN)){
						dataInColumn = FindElements(driver, "//span[@id='Disclaimer_Statistics-rows-start']/following-sibling::span[contains(@id,'Disclaimer_Statistics-row-')]/span[3]/a", "Email column data.");
						if(checkSorting(driver, SortOrder.Assecending, dataInColumn)){
							appLog.info("Email data is in Assecending order.");
						} else {
							appLog.error("Email data is not in Assecending order.");
							sa.assertTrue(false,"Email data is not in Assecending order.");
						}
					} else {
						appLog.error("Cannot click on Email header, So cannot check sorting in assecending order.");
						sa.assertTrue(false,"Cannot click on Email header, So cannot check sorting in assecending order.");
					}
					ele = FindElement(driver, "//span[contains(@id,'Disclaimer_Statistics-header-')][2]//span[@class='aw-item-text ']", "Email", action.BOOLEAN, 30);
					if(click(driver, ele, "Email", action.BOOLEAN)){
						dataInColumn = FindElements(driver, "//span[@id='Disclaimer_Statistics-rows-start']/following-sibling::span[contains(@id,'Disclaimer_Statistics-row-')]/span[3]/a", "Email column data.");
						if(checkSorting(driver, SortOrder.Decending, dataInColumn)){
							appLog.info("Email column data is in Decending order.");
						} else {
							appLog.error("Email column data is not in Decending order.");
							sa.assertTrue(false,"Email column data is not in Decending order.");
						}
					} else {
						appLog.error("Cannot click on Email header, So cannot check sorting in Decending order.");
						sa.assertTrue(false,"Cannot click on Email header, So cannot check sorting in Decending order.");
					}
					
					/*************************************Firm*****************************************************/
					
					ele = FindElement(driver, "//span[contains(@id,'Disclaimer_Statistics-header-')][3]//span[@class='aw-item-text ']", "Firm", action.BOOLEAN, 30);
					if(click(driver, ele, "Firm", action.BOOLEAN)){
						dataInColumn = FindElements(driver, "//span[@id='Disclaimer_Statistics-rows-start']/following-sibling::span[contains(@id,'Disclaimer_Statistics-row-')]/span[4]/a", "Firm column data.");
						if(checkSorting(driver, SortOrder.Assecending, dataInColumn)){
							appLog.info("Firm column data is in Assecending order.");
						} else {
							appLog.error("Firm column data is not in Assecending order.");
							sa.assertTrue(false,"Firm column data is not in Assecending order.");
						}
					} else {
						appLog.error("Cannot click on Firm header, So cannot check sorting in assecending order.");
						sa.assertTrue(false,"Cannot click on Firm header, So cannot check sorting in assecending order.");
					}
					ele = FindElement(driver, "//span[contains(@id,'Disclaimer_Statistics-header-')][3]//span[@class='aw-item-text ']", "Firm", action.BOOLEAN, 30);
					if(click(driver, ele, "Email", action.BOOLEAN)){
						dataInColumn = FindElements(driver, "//span[@id='Disclaimer_Statistics-rows-start']/following-sibling::span[contains(@id,'Disclaimer_Statistics-row-')]/span[4]/a", "Firm column data.");
						if(checkSorting(driver, SortOrder.Decending, dataInColumn)){
							appLog.info("Firm column data is in Decending order.");
						} else {
							appLog.error("Firm column data is not in Decending order.");
							sa.assertTrue(false,"Firm column data is not in Decending order.");
						}
					} else {
						appLog.error("Cannot click on Firm header, So cannot check sorting in Decending order.");
						sa.assertTrue(false,"Cannot click on Firm header, So cannot check sorting in Decending order.");
					}
					
					/******************************************Status Column**********************************************/
					
					ele = FindElement(driver, "//span[contains(@id,'Disclaimer_Statistics-header-')][4]//span[@class='aw-item-text ']", "Status", action.BOOLEAN, 30);
					if(click(driver, ele, "Status column", action.BOOLEAN)){
						ele = FindElement(driver, "//span[contains(@id,'Disclaimer_Statistics-header-')][4]//span[@class='aw-grid-sort ']", "Status Sort Icon", action.BOOLEAN, 30);
						if(isDisplayed(driver, ele, "visibility", 5, "Status Sort Icon")==null){
							appLog.info("Sorting is not working on Status column.");
						} else {
							appLog.error("Sorting is working on Status column.");
							sa.assertTrue(false,"Sorting is working on Status column.");
						}
					} else {
						appLog.error("Cannot click on Status column, So cannot check if sorting is working or not.");
						sa.assertTrue(false,"Cannot click on Status column, So cannot check if sorting is working or not.");
					}
					
				} else {
					appLog.error(M4DIS1+" is not present in the list, So cannot click on the view link and will not be able to check sorting on view window.");
					sa.assertTrue(false,M4DIS1+" is not present in the list, So cannot click on the view link and will not be able to check sorting on view window.");
				}
				
			} else {
				appLog.error("Updated"+M4F2+" fund is not present in the drop down.");
				sa.assertTrue(false,"Updated"+M4F2+" fund is not present in the drop down.");
			}
			
		} else {
			appLog.error("Navatar Investor Add Ons tab cannot be clicked, So cannot check sorting on disclaimer grid.");
			sa.assertTrue(false,"Navatar Investor Add Ons tab cannot be clicked, So cannot check sorting on disclaimer grid.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc048_PreconditionOrg2(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InstitutionPageBusinessLayer inst = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		FundRaisingPageBusinessLayer fdr = new FundRaisingPageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		lp.CRMLogin(Org2CRMUser1EmailID, adminPasswordOrg2);
		if(lp.clickOnTab(TabName.InstituitonsTab)){
			//inst.createInstitution(environment,mode,M4I2,"Institution",null,null);
			//inst.createInstitution(M4I2)
			if(inst.createInstitution(environment,mode,M4I2,"Institution",null,null)){
				appLog.info("Institution '"+M4I2+"' is created successfully.");
			} else {
				appLog.error(M4I2+" institution is not created.");
				sa.assertTrue(false,M4I2+" institution is not created.");
			}
		} else {
			appLog.error("Insitution Tab cannot be clicked, So cannot create institution.");
			sa.assertTrue(false,"Insitution Tab cannot be clicked, So cannot create institution.");
		}
		
		if(lp.clickOnTab(TabName.ContactTab)){
			//cp.createContact(environment,mode,M4CFN1, M4CLN1, M4I1, emailId,null,null,CreationPage.ContactPage)
		//	cp.createContact(M4CFN2, M4CLN2, M4I2, M4C2Email)
			if(cp.createContact(environment,mode,M4CFN2, M4CLN2, M4I2, M4C2Email,null,null,CreationPage.ContactPage)){
				appLog.info(M4CFN2+" "+M4CLN2+" contact is created successfully.");
			} else {
				appLog.error(M4CFN2+" "+M4CLN2+" contact is not created.");
				sa.assertTrue(false,M4CFN2+" "+M4CLN2+" contact is not created.");
			}
		} else {
			appLog.error("Contact tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"Contact tab cannot be clicked, So cannot continue with the TC.");
		}
		
		if(lp.clickOnTab(TabName.FundsTab)){
		//	fp.createFund(environment,mode,M4F1, M4FT1, M4FIC1,null,null)
		//	fp.createFund(M4F1, M4FT1, M4FIC1)
			if(fp.createFund(environment,mode,M4F1, M4FT1, M4FIC1,null,null)){
				appLog.info(M4F1+" fund created successfully");
			} else {
				appLog.error(M4F1+" fund cannot be created.");
				sa.assertTrue(false,M4F1+" fund cannot be created.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot continue with the TC.");
		}
		
		if(lp.clickOnTab(TabName.FundraisingsTab)){
		//	fdr.createFundRaising(environment,mode,M4FR1, M4F1, M4I1)
			// fdr.createFundRaising(M4FR1, M4F1, M4I2)
			if(fdr.createFundRaising(environment,mode,M4FR1, M4F1, M4I2)){
				appLog.info(M4FR1+" fundraising created successfully.");
			} else {
				appLog.error(M4FR1+" fundraising not created.");
				sa.assertTrue(false,M4FR1+" fundraising not created.");
			}
		} else {
			appLog.error("fundraising tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"fundraising tab cannot be clicked, So cannot continue with the TC.");
		}
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc049_BuildFundraisingWorkspaceAndCreateDisclaimer() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(Org2CRMUser1EmailID, adminPasswordOrg2);
		if (lp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M4F1)) {
				String Size=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M4F1", excelLabel.Fund_Size);
				String vintageyear=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M4F1", excelLabel.Fund_VintageYear);
				String contact=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M4F1", excelLabel.Fund_Contact);
				String phone=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M4F1", excelLabel.Fund_Phone);
				String email=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M4F1", excelLabel.Fund_Email);
				String description=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M4F1", excelLabel.Fund_Description);
				String[] step1Of3Data= {Size,vintageyear,contact,phone,email,description};
				if (fp.buildWorkspace(step1Of3Data, WorkSpaceAction.IMPORTFOLDERTEMPLATE, folderTemplateName, null,
						M4I2, Workspace.FundraisingWorkspace, 30)) {
					if (fp.inviteContact(environment, mode, M4I2, M4C2Email, null, FolderType.Standard, "Upload", "Yes",
							"No", null, Workspace.FundraisingWorkspace, M4C2Email)) {
						appLog.info("Successfully provided access to contact '" + M4CFN2 + " " + M4CLN2 + "'.");
					} else {
						appLog.error("Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2
								+ "', So this tc will fail.");
						sa.assertTrue(false, "Not able to provide access to contact '" + M4CFN2 + " " + M4CLN2
								+ "', So this tc will fail.");
					}
				} else {
					appLog.error("Fundraising workspace cannot be created, So cannot continue with the tc.");
					sa.assertTrue(false, "Fundraising workspace cannot be created, So cannot continue with the tc.");
				}
			} else {
				appLog.error(M4F1 + " fund is not available in the list, So cannot continue with tc.");
				sa.assertTrue(false, M4F1 + " fund is not available in the list, So cannot continue with tc.");
			}
		} else {
			appLog.error("FundsTab cannot be clicked, So cannot continue with the tc.");
			sa.assertTrue(false, "FundsTab cannot be clicked, So cannot continue with the tc.");
		}
		lp.CRMlogout(environment,mode);
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		lp.CRMLogin(superAdminOrg2UserName, adminPasswordOrg2);
		if (lp.clickOnTab(TabName.NavatarInvestorAddOns)) {
			switchToFrame(driver, 60, niam.getNavatarInvestorAddOnFrame(60));
			switchToFrame(driver, 60, niam.getNavatarInvestorAddOnFrame1(60));
			if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60),
					"Select fund drop down on Navatar Investor add-ons page", M4F1)) {
				appLog.info(M4F1 + "value is select from dropdown.");
				if (click(driver, niam.getNewDisclaimerButton(60), "New discliamer Button", action.SCROLLANDBOOLEAN)) {
					if (sendKeys(driver, niam.getNewDisclaimerNameTextBox(60), M4DIS1, "DisclaimerName text Box",
							action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, niam.getDisclaimerDescriptionTextArea(10), M4DISDEC1,
								"Description text box", action.BOOLEAN)) {
							switchToDefaultContent(driver);
							switchToFrame(driver, 60, niam.getNavatarInvestorAddOnFrame(60));
							switchToFrame(driver, 60, niam.getNavatarInvestorAddOnFrame1(60));
							if (click(driver, niam.getNewDiscalimerPopUpSaveButton(60), "Save Button",
									action.SCROLLANDBOOLEAN)) {
								appLog.info("Disclaimer has been saved successfully.");
								if (niam.activateDisclaimer(M4DIS1, 60)) {
									appLog.info("Disclaimer has been successfully activated.");
								} else {
									appLog.info("Not able to activate disclaimer 1");
									sa.assertTrue(false, "Not able to activate disclaimer 1");
								}
							} else {
								appLog.error(M4DIS1 + "Not able to click on save button");
								sa.assertTrue(false, "Not able to click on save button");
							}
						} else {
							appLog.error("Not able to pass value to discalimer description text box.");
							sa.assertTrue(false, "Not able to pass value to disclaimer description text box.");
						}
					} else {
						appLog.error("Not able to pass value to disclimer name text box.");
						sa.assertTrue(false, "Not able to pass value to disclimer name text box.");
					}
				} else {
					appLog.error("New disclaimer button cannot be clikced so cannot create new disclaimer.");
					sa.assertTrue(false, "New disclaimer button cannot be clikced so cannot create new disclaimer.");
				}
			} else {
				appLog.error(M4F1 + " fund name is not present in the drop down, So not able to select.");
				sa.assertTrue(false, M4F1 + " fund name is not present in the drop down, So not able to select.");
			}
		} else {
			appLog.info("Not able to click on navatar investor add ons tab.");
			sa.assertTrue(false, "Not able to click on navatar investor add ons tab.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc049_BuildFundraisingWorkspaceAndCreateDisclaimerCheckImpactInvestorSide(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		lp.investorLogin(M4C2Email, adminPassword);
		if(selectVisibleTextFromDropDown(driver, lp.getFirmNameDropdown(60), "Firm name drop down", Org3UpdatedFirmName)){
			if(lp.getPendingDisclaimerPopUpHeader(60)!=null){
				appLog.info("Pending disclaimer pop up is open.");
				String text = trim(getText(driver, lp.getPendingDisclaimerPopUpHeader(60), "Header", action.BOOLEAN));
				if(text.equalsIgnoreCase("Pending Disclaimer(s)")){
					appLog.info("Pending disclaimer pop up header is verified");
				} else {
					appLog.error("Pending disclaimer pop up header text is not verified.Expected: Pending Disclaimer(s)\tActual: "+text);
					sa.assertTrue(false,"Pending disclaimer pop up header text is not verified.Expected: Pending Disclaimer(s)\tActual: "+text);
				}
			} else {
				appLog.error("Pending disclaimer pop up is not open.");
				sa.assertTrue(false,"Pending disclaimer pop up is not open.");
			}
			if(lp.getPendingDisclaimerPopUpCrossIcon(60)!=null){
				appLog.info("Pending disclaimer pop up cross icon is present.");
			} else {
				appLog.error("Pending disclaimer pop up cross icon is not present.");
				sa.assertTrue(false,"Pending disclaimer pop up cross icon is not present.");
			}
			if(lp.getPendingDisclaimerPopGoToDisclaimerButton(60)!=null){
				appLog.info("Pending disclaimer pop up Go to disclaimer button is present.");
			} else {
				appLog.error("Pending disclaimer pop up Go to disclaimer button is not present.");
				sa.assertTrue(false,"Pending disclaimer pop up Go to disclaimer button is not present.");
			}
			if(lp.getPendingDisclaimerPopUpCancelBUtton(60)!=null){
				appLog.info("Pending disclaimer pop up Cancel button is present.");
			} else {
				appLog.error("Pending disclaimer pop up Cancel button is not present.");
				sa.assertTrue(false,"Pending disclaimer pop up Cancel button is not present.");
			}
		} else {
			appLog.error(Org3UpdatedFirmName+" is not present in the drop down, So cannot check the pending disclaimer on the page.");
			sa.assertTrue(false,Org3UpdatedFirmName+" is not present in the drop down, So cannot check the pending disclaimer on the page.");
		}
		
		if(selectVisibleTextFromDropDown(driver, lp.getFirmNameDropdown(60), "Firm name drop down", Org2FirmName)){
			if(lp.getPendingDisclaimerPopUpHeader(60)!=null){
				appLog.info("Pending disclaimer pop up is open.");
				String text = trim(getText(driver, lp.getPendingDisclaimerPopUpHeader(60), "Header", action.BOOLEAN));
				if(text.equalsIgnoreCase("Pending Disclaimer(s)")){
					appLog.info("Pending disclaimer pop up header is verified");
				} else {
					appLog.error("Pending disclaimer pop up header text is not verified.Expected: Pending Disclaimer(s)\tActual: "+text);
					sa.assertTrue(false,"Pending disclaimer pop up header text is not verified.Expected: Pending Disclaimer(s)\tActual: "+text);
				}
			} else {
				appLog.error("Pending disclaimer pop up is not open.");
				sa.assertTrue(false,"Pending disclaimer pop up is not open.");
			}
			if(lp.getPendingDisclaimerPopUpCrossIcon(60)!=null){
				appLog.info("Pending disclaimer pop up cross icon is present.");
			} else {
				appLog.error("Pending disclaimer pop up cross icon is not present.");
				sa.assertTrue(false,"Pending disclaimer pop up cross icon is not present.");
			}
			if(lp.getPendingDisclaimerPopGoToDisclaimerButton(60)!=null){
				appLog.info("Pending disclaimer pop up Go to disclaimer button is present.");
			} else {
				appLog.error("Pending disclaimer pop up Go to disclaimer button is not present.");
				sa.assertTrue(false,"Pending disclaimer pop up Go to disclaimer button is not present.");
			}
			if(lp.getPendingDisclaimerPopUpCancelBUtton(60)!=null){
				appLog.info("Pending disclaimer pop up Cancel button is present.");
			} else {
				appLog.error("Pending disclaimer pop up Cancel button is not present.");
				sa.assertTrue(false,"Pending disclaimer pop up Cancel button is not present.");
			}
			
			if(click(driver, lp.getPendingDisclaimerPopGoToDisclaimerButton(30), "Go To Disclaimer", action.BOOLEAN)){
				String parentID = switchOnWindow(driver);
				if(parentID!=null){
					List<WebElement> expandIcon = FindElements(driver, "//div[@class='main_box_div_Investment']/div[@class='contacts_n_name_div_Investment']/div[1]/span[1]/preceding-sibling::div", "Expand Icon");
					for(int i = 0; i < expandIcon.size(); i++){
						if(click(driver, expandIcon.get(i), "Expand icon", action.BOOLEAN)){
							appLog.info("Successfully expanded disclaimer number "+i+".");
						} else {
							appLog.error("Cannot expand disclaimer number "+i+".");
							sa.assertTrue(false,"Cannot expand disclaimer number "+i+".");
						}
					}
					List<WebElement> orgNames = FindElements(driver, "//div[@class='main_box_div_Investment']/div[@class='contacts_n_name_div_Investment']/div[1]/span[1]", "org Names");
					if(checkSorting(driver, SortOrder.Assecending, orgNames)){
						appLog.info("Org Names are in assecending order.");
					} else {
						appLog.error("Org Names are not in assecending order.");
						sa.assertTrue(false,"Org Names are not in assecending order.");
					}
					
					orgNames = FindElements(driver, "//div[@class='main_box_div_Investment']/div[@class='contacts_n_name_div_Investment']/div[1]/span[1]", "org Names");
					for(int i = 0; i < orgNames.size(); i++){
						String orgName = getAttribute(driver, orgNames.get(i), "", "title");
						List<WebElement> fundNames = FindElements(driver, "//span[@title='"+orgName+"']/following-sibling::span", "fund name");
						if(checkSorting(driver, SortOrder.Assecending, fundNames)){
							appLog.info("Fund Names of org "+orgName+" are in assecending order.");
						} else {
							appLog.error("Fund Names of org "+orgName+" are not in assecending order.");
							sa.assertTrue(false,"Fund Names of org "+orgName+" are not in assecending order.");
						}
					}
					driver.close();
					driver.switchTo().window(parentID);
				} else {
					appLog.error("Cannot switch to disclaimer window, So cannot check sorting on disclaimer page.");
					sa.assertTrue(false,"Cannot switch to disclaimer window, So cannot check sorting on disclaimer page.");
				}
			} else {
				appLog.error("Go To Disclaimer button cannot be clicked, SO cannot check sorting on disclaimer page.");
				sa.assertTrue(false,"Go To Disclaimer button cannot be clicked, SO cannot check sorting on disclaimer page.");
			}
		} else {
			appLog.error(Org2FirmName+" is not present in the drop down, So cannot check the pending disclaimer on the page.");
			sa.assertTrue(false,Org2FirmName+" is not present in the drop down, So cannot check the pending disclaimer on the page.");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc042_InviteContacts() {

		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(Org3CRMUser1EmailID, adminPassword);
		String stdPath = ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC,
				excelLabel.StandardPath);
		if (lp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M4F2)) {
				scrollDownThroughWebelement(driver, fp.getFundRaisingWorkSpaceSection(60),
						"fundrasing workspace section");
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.inviteContact(environment, mode, M4I2, M4C1Email, stdPath, FolderType.Standard, "upload", "Yes",
						"no", "Standard", Workspace.FundraisingWorkspace, M4C1Email)) {
					appLog.info("contact is invited for fundraising workspace successfully :" + M4CFN1 + " " + M4CLN1);
				} else {
					appLog.error(
							" not able to invited for fundraising workspace successfully :" + M4CFN1 + " " + M4CLN1);
					sa.assertTrue(false, "contact is not invited for for fundraising workspace successfully :" + M4CFN1
							+ " " + M4CLN1);
				}
				if (fp.inviteContact(environment, mode, M4I2, M4C2Email, stdPath, FolderType.Standard, "upload", "Yes",
						"no", "Standard", Workspace.FundraisingWorkspace, M4C2Email)) {
					appLog.info("contact is invited for fundraising workspace successfully :" + M4CFN2 + " " + M4CLN2);
				} else {
					appLog.error(
							" not able to invited for fundraising workspace successfully :" + M4CFN2 + " " + M4CLN2);
					sa.assertTrue(false,
							" not able to invited for fundraising workspace successfully :" + M4CFN2 + " " + M4CLN2);
				}
			} else {
				appLog.error("not able to click on created fund Name so not able to invite from:" + M4F2);
				sa.assertTrue(false, "not able to click on created fund Name so not able to invite from:" + M4F2);
			}

		} else {
			appLog.error("Funds tab cannot be clicked, So cannot invite contacts.");
			sa.assertTrue(false, "Funds tab cannot be clicked, So cannot invite contacts.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		driver.close();
		config(browserToLaunch);
		appLog.info("******************* login to contact 1 **********************");
		lp = new LoginPageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		DisclaimerPageBussinessLayer dpbl = new DisclaimerPageBussinessLayer(driver);
		String parentID = null;
		if (lp.investorLogin(M4C1Email, adminPassword)) {
			if (matchTitle(driver, "Firm Alerts", 60)) {
				appLog.info("Page title is successfully matched.");
			} else {
				appLog.info("Page title is not matched.");
				sa.assertTrue(false, "Page title is not matched.");
			}
			if (niam.getPendingDisclaimerPopGoToDisclaimerButton(60) != null) {
				appLog.info("Pending Disclaimer Popup is available.");
				if (click(driver, niam.getPendingDisclaimerPopGoToDisclaimerButton(60),
						"Pending Disclaimer Popup Go to Disclaimer Button", action.BOOLEAN)) {
					appLog.info("Click on Go to Disclaimer Button Successfully.");
					parentID = switchOnWindow(driver);
					String text = trim(getText(driver, dpbl.getDisclaimerPageHeader(60), "Header", action.BOOLEAN));
					if (text.equalsIgnoreCase("Disclaimers")) {
						appLog.info("Header is verified.");
					} else {
						appLog.error("Header is not present on the page.Expected: Disclaimers\tActual: " + text);
						sa.assertTrue(false,
								"Header is not present on the page.Expected: Disclaimers\tActual: " + text);

					}
					text = trim(getText(driver, dpbl.getFundName(60), "FundName", action.BOOLEAN));
					if (text.equalsIgnoreCase(M4F1)) {
						appLog.info(M4F1 + "FundName is verified.");
					} else {
						appLog.error(M4F1 + "Fund Name is not verified.");
						sa.assertTrue(false, "FundName is not verified.");

					}
					text = trim(getText(driver, dpbl.getDisclaimerName(60), "DisclaimerName", action.BOOLEAN));
					if (text.equalsIgnoreCase(M4DIS2)) {
						appLog.info(M4DIS2 + "DisclaimerName is verified.");
					} else {
						appLog.error(M4DIS2 + "DisclaimerName is not verified.");
						sa.assertTrue(false, " '" + M4DIS2 + "'DisclaimerName is not verified.");
					}
					if (dpbl.getAcceptButton(60) != null) {
						appLog.info("AcceptButton is available and verified.");
					} else {
						appLog.error("AcceptButton is not available and verified.");
						sa.assertTrue(false, "AcceptButton is not available and  verified.");
					}
				} else {
					appLog.info("Not able to click on Go to Disclaimer Button .");
					sa.assertTrue(false, "Not able to click on Go to Disclaimer Button.");

				}
			} else {
				appLog.info("Pending Disclaimer Popup is available.");
				sa.assertTrue(false, "Pending Disclaimer Popup is not available.");
			}

		} else {
			appLog.info("Investor password is wrong so cannot login.");
			sa.assertTrue(false, "Investor password is wrong so cannot login");
		}
		driver.close();
		driver.switchTo().window(parentID);
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		appLog.info("******************* login to contact 2 **********************");
		lp = new LoginPageBusinessLayer(driver);
		niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		dpbl = new DisclaimerPageBussinessLayer(driver);
		if (lp.investorLogin(M4C2Email, adminPassword)) {
			if (matchTitle(driver, "Firm Alerts", 60)) {
				appLog.info("Page title is successfully matched.");
			} else {
				appLog.info("Page title is not matched.");
				sa.assertTrue(false, "Page title is not matched.");
			}
			if (niam.getPendingDisclaimerPopGoToDisclaimerButton(60) != null) {
				appLog.info("Pending Disclaimer Popup is available.");
				if (click(driver, niam.getPendingDisclaimerPopGoToDisclaimerButton(60),
						"Pending Disclaimer Popup Go to Disclaimer Button", action.BOOLEAN)) {
					appLog.info("Click on Go to Disclaimer Button Successfully.");
					parentID = switchOnWindow(driver);
					String text = trim(getText(driver, dpbl.getDisclaimerPageHeader(60), "Header", action.BOOLEAN));
					if (text.equalsIgnoreCase("Disclaimers")) {
						appLog.info("Header is verified.");
					} else {
						appLog.error("Header is not present on the page.Expected: Disclaimers\tActual: " + text);
						sa.assertTrue(false,
								"Header is not present on the page.Expected: Disclaimers\tActual: " + text);
					}
					if (dpbl.clickOnExpandIcon(M4F1, 60)) {
						if (dpbl.verifyFundDisplaying(M4F1, Org3FirmName)) {
							appLog.info("Fund is displaying and fund name is verified");
						} else {
							appLog.info("Fund is not displaying and fund name is not verified");
							sa.assertTrue(false, "Fund Is Not displaying and fund name is not verified");
						}
						if (dpbl.verifyAcceptButton(M4F1, M4DIS2)) {
							appLog.info("Accept button is verified for :" + M4F1);
						} else {
							appLog.info("Accept button is not verified for :" + M4F1);
							sa.assertTrue(false, "Accept button is not verified for :" + M4F1);
						}
					} else {
						appLog.info("Not bale to click on expand icon");
						sa.assertTrue(false, "Not able to click on expand icon");
					}
					if (dpbl.clickOnExpandIcon(M4F2, 60)) {
						if (dpbl.verifyFundDisplaying(M4F2, Org3FirmName)) {
							appLog.info("Fund is displaying and fund name is verified");
						} else {
							appLog.info("Fund is not displaying and fund name is not verified");
							sa.assertTrue(false, "Fund Is Not displaying and fund name is not verified");
						}
						if (dpbl.verifyAcceptButton(M4F2, M4DIS1)) {
							appLog.info("Accept button is verified for :" + M4F2);
						} else {
							appLog.info("Accept button is not verified for :" + M4F2);
							sa.assertTrue(false, "Accept button is not verified for :" + M4F2);
						}
					} else {
						appLog.info("Not bale to click on expand icon");
						sa.assertTrue(false, "Not able to click on expand icon");
					}
				} else {
					appLog.info("Not able to click on Go to Disclaimer Button .");
					sa.assertTrue(false, "Not able to click on Go to Disclaimer Button.");

				}
			} else {
				appLog.info("Pending Disclaimer Popup is available.");
				sa.assertTrue(false, "Pending Disclaimer Popup is not available.");
			}
		} else {
			appLog.info("Investor password is wrong so cannot login.");
			sa.assertTrue(false, "Investor password is wrong so cannot login");
		}
		driver.close();
		driver.switchTo().window(parentID);
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if (lp.clickOnTab(TabName.NavatarInvestorAddOns)) {
			if (switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60))) {
				System.err.println("Successfully switched to frame.");
				switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
			}
			List<WebElement> lstOfEle = allOptionsInDropDrop(driver, niam.getSelectFundDropDown(60),
					"get options from name Fund Name Dropdown");
			boolean flag_dis1 = false;
			boolean flag_dis2 = false;
			for (int i = 0; i < lstOfEle.size(); i++) {
				if (lstOfEle.get(i).getText().trim().equals(M4F1)) {
					flag_dis1 = true;
					appLog.info(lstOfEle.get(i).getText().trim() + " is present in name picklist ");
				} else if (lstOfEle.get(i).getText().trim().equals(M4F2)) {
					flag_dis2 = true;
					appLog.info(lstOfEle.get(i).getText().trim() + " is present in name picklist ");
				}

			}
			if (flag_dis1 == false) {
				appLog.error(M4F1 + " fund is not present in fund name dropdown. ");
				sa.assertTrue(false, M4F1 + " fund is not present in fund name dropdown. ");

			}
			if (flag_dis2 == false) {
				appLog.error(M4F2 + " fund is not present in fund name dropdown.");
				sa.assertTrue(false, M4F2 + " fund is not present in fund name dropdown.");

			}
			if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60),
					"Select fund drop down on Navatar Investor add-ons page", M4F2)) {
				appLog.info(M4F2 + "value is select from dropdown.");
				if (niam.verifyDisclaimerGrid(DisclaimerGrid.Deactivate, M4DIS1, DisclaimerGrid.View,
						DisclaimerGrid.LastActivatedOn, DisclaimerGrid.CreatedOn)) {
					appLog.info("Disclaimer grid has been verified.");

				} else {
					appLog.error("Disclaimer grid not verified.");
					sa.assertTrue(false, "Disclaimer grid not verified.");
				}
				if (niam.getDisclaimerRecordCount() == 1) {
					appLog.info("record count is verfied and equals to : " + niam.getDisclaimerRecordCount());
				} else {
					appLog.error("record count is not verfied.");
					sa.assertTrue(false, "record count is not verfied.");
				}
				if (niam.getActiveDisclaimerStatisticsValue().equals(M4DIS1)) {
					appLog.info("Active Disclaimer Statics : " + niam.getActiveDisclaimerStatisticsValue()
					+ " is available");
				} else {
					appLog.error("Active Disclaimer Statics value  is not verified.");
					sa.assertTrue(false, "Active Disclaimer Statics value is not verified.");
				}
				if (niam.getAcceptedDiscalimerCount() == 1 && niam.getWaitingDisclaimerCount() == 2) {
					appLog.info("Active Disclaimer Statics status accepted count : " + niam.getAcceptedDiscalimerCount()
					+ " Active Disclaimer Statics status waiting count : " + niam.getWaitingDisclaimerCount()
					+ "has been verified.");
				} else {
					appLog.error("Active Disclaimer Statics status : accepted and waiting count is not verified.");
					sa.assertTrue(false,
							"Active Disclaimer Statics status : accepted and waiting count is not verified.");
				}
				if (niam.clickOnViewLinkOfDisclaimer(M4DIS1)) {
					appLog.info("Disclaimer Statistics Popup is opened.");
					if (niam.verifyDisclaimerStatisticsGrid(M4CFN1 + " " + M4CLN1, M4C1Email, M4I1, "Accepted",
							getSystemDate("MM/dd/yyyy"))) {
						appLog.info(M4CFN1 + " " + M4CLN1 + "contact detail is verfied ");
					} else {
						appLog.error("contact detail 1 is not verfied.");
						sa.assertTrue(false, "contact detail 1  is not verfied.");
					}
					if (niam.verifyDisclaimerStatisticsGrid(M4CFN2 + " " + M4CLN2, M4C2Email, M4I2, "Waiting", "")) {
						appLog.info(M4CFN2 + " " + M4CLN2 + "contact detail is verfied ");
					} else {
						appLog.error("contact detail 2 is not verfied.");
						sa.assertTrue(false, "contact detail 2 is not verfied.");
					}
					if (niam.verifyDisclaimerStatisticsGrid(M4CFN3 + " " + M4CLN3, M4C3Email, M4I3, "Waiting", "")) {
						appLog.info(M4CFN3 + " " + M4CLN3 + "contact detail is verfied ");
					} else {
						appLog.error("contact detail 3 is not verfied.");
						sa.assertTrue(false, "contact detail 3 is not verfied.");
					}

					if (niam.getDisclaimerViewPopUpRecordCount() == 3) {
						appLog.info("Disclaimer Statistics popup record count is verfied and equals to : "
								+ niam.getDisclaimerViewPopUpRecordCount());
					} else {
						appLog.error("record count is not verfied.");
						sa.assertTrue(false, "record count is not verfied.");
					}
				} else {
					appLog.error("Disclaimer Statistics Popup is not openend.");
					sa.assertTrue(false, "Disclaimer Statistics Popup is not openend.");
				}
			} else {
				appLog.error(M4F2 + " fund name is not present in the drop down, So not able to select.");
				sa.assertTrue(false, M4F2 + " fund name is not present in the drop down, So not able to select.");
			}

		} else {
			appLog.error("Navatar addon tab cannot be clicked, So cannot check the UI.");
			sa.assertTrue(false, "Navatar addon tab cannot be clicked, So cannot check the UI.");
		}

		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc043_1_UpdateContactEmailidAndVerifyErrorMessage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		HomePageBusineesLayer hp = new HomePageBusineesLayer(driver);
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if (lp.clickOnTab(TabName.ContactTab)) {
			appLog.info("Click on Contact tab succesfully.");
			if (cp.clickOnCreatedContact(M4CFN1, M4CLN1, null)) {
				appLog.info("Successfully Click on Contact : " + M4CFN1 + " " + M4CLN1);
				if (click(driver, cp.getEditButton(60), "edit button on contacts page", action.SCROLLANDBOOLEAN)) {
					appLog.info(" Click on edit button successfuly. ");
					if (sendKeys(driver, cp.getEmailId(60), "**" + M4C1Email.substring(2),
							"email id textbox on contact page", action.SCROLLANDBOOLEAN)) {
						appLog.info("enter updated EmailId in EmailId  text box");
						if (click(driver, cp.getSaveButton(60), "contacts page save button", action.SCROLLANDBOOLEAN)) {
							appLog.info(" Click on save button successfuly. ");
							scrollDownThroughWebelement(driver, cp.getEmailIdViewMode(60),
									"contact email id view mode");
							if (getText(driver, cp.getEmailIdViewMode(60), "email id view mode",
									action.SCROLLANDBOOLEAN).equalsIgnoreCase("**" + M4C1Email.substring(2))) {
								appLog.info("contact email id has been successfully updated");

							
							} else {
								appLog.error("contact email id could not be updated");
								sa.assertTrue(false, "contact email id could not be updated");
							}
						} else {
							appLog.error(" can't click on save button so updated email id is not saved. ");
							sa.assertTrue(false, " can't click on save button so updated email id is not saved. ");
						}
					} else {
						appLog.error(" not able to enter updated value in Email Id text box. ");
						sa.assertTrue(false, " can't click on save button so updated Email Id is not saved. ");
					}

				} else {
					appLog.error("Not able to click on edit button ,so not able to edit contact.");
					sa.assertTrue(false, "Not able to click on edit button ,so not able to edit contact.");
				}

			} else {
				appLog.error("not able to click on contact1 so not able to check update contact email fuctionality.");
				sa.assertTrue(false,
						"not able to click on contact1 so not able to check update contact email fuctionality.");
			}
		} else {
			appLog.error("Contacts tab cannot be clicked, So cannot update conatct email and verify error message.");
			sa.assertTrue(false,
					"Contacts tab cannot be clicked, So cannot update conatct email and verify error message.");
		}
		

		switchToDefaultContent(driver);
		if (lp.clickOnTab(TabName.NavatarInvestorAddOns)) {
			if (switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60))) {
				System.err.println("Successfully switched to frame.");
				switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
			}
			if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60),
					"Select fund drop down on Navatar Investor add-ons page", M4F1)) {
				appLog.info(M4F1 + "value is select from dropdown.");
				if (niam.clickOnViewLinkOfDisclaimer(M4DIS1)) {
					appLog.info("Disclaimer Statistics Popup is opened.");
					if (niam.clickOnContactNameLink(M4CFN1 + " " + M4CLN1)) {
						appLog.info("Successfully click on contact : " + M4CFN1 + " " + M4CLN1);
						ThreadSleep(5000);
						try {
							if (isAlertPresent(driver)) {
								String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
								switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
								if (msg.trim()
										.equals(FundsPageErrorMessage.couldNotFindContactOrFirm)) {
									appLog.info(
											"correct error message alert is present after editing email id of contact and clicking on contact name");
									switchToDefaultContent(driver);
								} else {
									appLog.error(
											"error message is wrong when updated contact name and clicking on contact");
									sa.assertTrue(false,
											"error message is wrong when updated contact name and clicking on contact");
								}
							} else {
								appLog.error(
										"no alert message is present when clicked on changed email id of contact");
								sa.assertTrue(false,
										"no alert message is present when clicked on changed email id of contact");
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							appLog.error(
									"no alert message is present when clicked on changed email id of contact");
							sa.assertTrue(false,
									"no alert message is present when clicked on changed email id of contact");
						}
					} else {
						appLog.error(
								" Not able to click on Contact 1 so error message is not verified. ");
						sa.assertTrue(false,
								" Not able to click on Contact 1 so error message is not verified. ");
					}
				} else {
					appLog.error(" Disclaimer View Popup is not opened. ");
					sa.assertTrue(false, " Disclaimer View Popup is not opened.");
				}
			} else {
				appLog.error(M4F1
						+ " fund name is not present in the drop down, So not able to select.");
				sa.assertTrue(false, M4F1
						+ " fund name is not present in the drop down, So not able to select.");
			}

		} else {
			appLog.error("Navatar addon tab cannot be clicked, So cannot check the UI.");
			sa.assertTrue(false,
					"Navatar addon tab cannot be clicked, So cannot check the UI.");
		}
		
		if (lp.clickOnTab(TabName.ContactTab)) {
			appLog.info("Click on Contact tab succesfully.");
			if (cp.clickOnCreatedContact(M4CFN1, M4CLN1, null)) {
				appLog.info("Successfully Click on Contact : " + M4CFN1 + " " + M4CLN1);
				if (click(driver, cp.getEditButton(60), "edit button on contacts page", action.SCROLLANDBOOLEAN)) {
					appLog.info(" Click on edit button successfuly. ");
					ThreadSleep(5000);
					if (sendKeys(driver, cp.getEmailId(60), M4C1Email, "email id textbox on contact page",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("enter updated EmailId in EmailId  text box");
						if (click(driver, cp.getSaveButton(60), "contacts page save button", action.SCROLLANDBOOLEAN)) {
							appLog.info(" Click on save button successfuly. ");
							scrollDownThroughWebelement(driver, cp.getEmailIdViewMode(60),
									"contact email id view mode");
							if (getText(driver, cp.getEmailIdViewMode(60), "email id view mode",
									action.SCROLLANDBOOLEAN).equalsIgnoreCase(M4C1Email)) {
								appLog.info("contact email id has been successfully updated");
							} else {
								appLog.error("contact email id could not be updated");
								sa.assertTrue(false, "contact email id could not be updated");
							}
						} else {
							appLog.error(" can't click on save button so updated email id is not saved. ");
							sa.assertTrue(false, " can't click on save button so updated email id is not saved. ");
						}
					} else {
						appLog.error(" not able to enter updated value in Email Id text box. ");
						sa.assertTrue(false, " can't click on save button so updated Email Id is not saved. ");
					}

				} else {
					appLog.error("Not able to click on edit button ,so not able to edit contact.");
					sa.assertTrue(false, "Not able to click on edit button ,so not able to edit contact.");
				}

			} else {
				appLog.error("not able to click on contact1 so not able to check update contact email fuctionality.");
				sa.assertTrue(false,
						"not able to click on contact1 so not able to check update contact email fuctionality.");
			}
		} else {
			appLog.error("Contacts tab cannot be clicked, So cannot update conatct email and verify error message.");
			sa.assertTrue(false,
					"Contacts tab cannot be clicked, So cannot update conatct email and verify error message.");
		}

		if (lp.clickOnTab(TabName.InstituitonsTab)) {
			appLog.info("Click on Institution tab succesfully.");
			if (ip.clickOnCreatedInstitution(environment,mode,M4I1)) {
				appLog.info("Successfully click on institute .");
				if (clickUsingJavaScript(driver, bp.getDeleteButton(60), " Institute Page Detail Page", action.BOOLEAN)) {
					appLog.info("Cliked On delete button Successfully");
					ThreadSleep(2000);
						if (!ip.clickOnCreatedInstitution(environment,mode,M4I1)) {
							appLog.info("Institution get deleted successfully and verified");
						} else {
							appLog.info("Institution does not get deleted successfully and verified. ");
							sa.assertTrue(false, "Institution does not get deleted successfully");
						}

					
					
				} else {
					appLog.error(" not able to click on delete button so not able to delete Institute. ");
					sa.assertTrue(false, " not able to click on delete button so not able to delete Institute. ");
				}
			} else {
				appLog.info(" Institution does not get clicked. ");
				sa.assertTrue(false, " Institution does not get clicked. ");
			}
		} else {
			appLog.error("Institution tab cannot be clicked, So cannot update conatct email and verify error message.");
			sa.assertTrue(false,
					"Institution tab cannot be clicked, So cannot update conatct email and verify error message.");
		}

		if (lp.clickOnTab(TabName.ContactTab)) {
			appLog.info("Click on Contact tab succesfully.");
			if (cp.clickOnCreatedContact(M4CFN2, M4CLN2, null)) {
				appLog.info("Successfully Click on Contact : " + M4CFN2 + " " + M4CLN2);
				if (clickUsingJavaScript(driver, cp.getDeleteButton(60), "Delete Button", action.SCROLLANDBOOLEAN)) {
					appLog.info("Click on delete button Successfully. ");

					ThreadSleep(2000);
						if (!cp.clickOnCreatedContact(M4CFN2, M4CLN2, null)) {
							appLog.info("not able to click that means this contact get deleted . ");

							if (lp.clickOnTab(TabName.NavatarInvestorAddOns)) {
								if (switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60))) {
									System.err.println("Successfully switched to frame.");
									switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
								}
								if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60),
										"Select fund drop down on Navatar Investor add-ons page", M4F1)) {
									appLog.info(M4F1 + "value is select from dropdown.");
									if (niam.clickOnViewLinkOfDisclaimer(M4DIS1)) {
										appLog.info("Disclaimer Statistics Popup is opened.");
										if (niam.clickOnContactNameLink(M4CFN2 + " " + M4CLN2)) {
											appLog.info("Successfully click on contact : " + M4CFN2 + " " + M4CLN2);
											ThreadSleep(3000);
											if (isAlertPresent(driver)) {
												String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
												switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
												ThreadSleep(6000);
												if (msg.trim()
														.equals(FundsPageErrorMessage.couldNotFindContactOrFirm)) {
													appLog.info(
															"correct error message alert is present after editing email id of contact and clicking on contact name");
												} else {
													appLog.error(
															"error message is wrong when delete contact and clicking on contact. ");
													sa.assertTrue(false,
															"error message is wrong when delete contact and clicking on contact.");
												}
											} else {
												appLog.error(
														" no alert message is present when clicked on deleted contact name . ");
												sa.assertTrue(false,
														" no alert message is present when clicked on deleted contact name. ");
											}
										} else {
											appLog.error(
													" Not able to click on Contact 2 so error message is not verified. ");
											sa.assertTrue(false,
													" Not able to click on Contact 2 so error message is not verified. ");
										}
										if (niam.clickOnFirmNameLink(M4I1)) {
											ThreadSleep(6000);
											appLog.info("Successfully click on Institute : " + M4I1);
											if (isAlertPresent(driver)) {
												String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
												switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
												if (msg.trim()
														.equals(FundsPageErrorMessage.couldNotFindContactOrFirm)) {
													appLog.info(
															"correct error message alert is present after editing email id of contact and clicking on contact name");

												} else {
													appLog.error(
															"error message is wrong when institute name is deleted and clicking on institute 1 name. ");
													sa.assertTrue(false,
															"error message is wrong when institute name is deleted and clicking on institute 1 name. ");
												}
											} else {
												appLog.error(
														" no alert message is present when clicked on Institute 1 name. ");
												sa.assertTrue(false,
														" no alert message is present when clicked on Institute 1 name. ");
											}
										} else {
											appLog.error(" not able to click on Institute1 name. ");
											sa.assertTrue(false, " not able to click on Institute1 name. ");
										}
									} else {
										appLog.error(" Disclaimer View Popup is not opened. ");
										sa.assertTrue(false, " Disclaimer View Popup is not opened.");
									}
								} else {
									appLog.error(M4F1
											+ " fund name is not present in the drop down, So not able to select.");
									sa.assertTrue(false, M4F1
											+ " fund name is not present in the drop down, So not able to select.");
								}

							} else {
								appLog.error("Navatar addon tab cannot be clicked, So cannot check the UI.");
								sa.assertTrue(false, "Navatar addon tab cannot be clicked, So cannot check the UI.");
							}
						} else {
							appLog.error(" Contact2 is not deleted . ");
							sa.assertTrue(false, " Contact2 is not deleted .");
						}

				} else {
					appLog.error(" not able to click on delete button. ");
					sa.assertTrue(false, " not able to click on delete button. ");
				}
			} else {
				appLog.error("not able to click on contact1 so not able to delete.");
				sa.assertTrue(false, "not able to click on contact1 so not able to delete.");
			}
		} else {
			appLog.error("Contacts tab cannot be clicked, So cannot update conatct email and verify error message.");
			sa.assertTrue(false,
					"Contacts tab cannot be clicked, So cannot update conatct email and verify error message.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc043_2_UpdateContactEmailidAndVerifyErrorMessage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		HomePageBusineesLayer hp = new HomePageBusineesLayer(driver);
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		
//		
		
		String contactName = M4CFN2 + " " + M4CLN2;
		String insName = M4I1;
		
		TabName[] tabNames = {TabName.RecycleBinTab,TabName.RecycleBinTab};
		String[] names = {contactName,insName};
		
		TabName tabName ;
		int i=0;
		for (String name : names) {
			name=names[i];
			tabName=tabNames[i];
			if (cp.clickOnTab(tabName)) {
				log(LogStatus.INFO,"Clicked on Tab : "+tabName+" For : "+name,YesNo.No);
				ThreadSleep(1000);
				if (cp.clickOnAlreadyCreatedItem(tabName, name, 20)) {
					log(LogStatus.INFO,"Clicked on  : "+name+" For : "+tabName,YesNo.No);
					ThreadSleep(2000);
					
					 WebElement ele = cp.getCheckboxOfRestoreItemOnRecycleBin( name, 10);
					 if (clickUsingJavaScript(driver, ele, "Check box against : "+name, action.BOOLEAN)) {
						 log(LogStatus.INFO,"Click on checkbox for "+name,YesNo.No);
						 
						 ThreadSleep(1000);
							//					scn.nextLine();
						 ele=cp.getRestoreButtonOnRecycleBin( 10);
						 if (clickUsingJavaScript(driver, ele, "Restore Button : "+name, action.BOOLEAN)) {
							 log(LogStatus.INFO,"Click on Restore Button for "+name,YesNo.No);
							 ThreadSleep(3000);

								
						} else {
							sa.assertTrue(false,"Not Able to Click on Restore Button for "+name);
							log(LogStatus.SKIP,"Not Able to Click on Restore Button for "+name,YesNo.Yes);
						}
						 
					} else {
						sa.assertTrue(false,"Not Able to Click on checkbox for "+name);
						log(LogStatus.SKIP,"Not Able to Click on checkbox for "+name,YesNo.Yes);
					}



				} else {
					sa.assertTrue(false,"Item Not Found : "+name+" For : "+tabName);
					log(LogStatus.SKIP,"Item Not Found : "+name+" For : "+tabName,YesNo.Yes);
				}

			} else {
				sa.assertTrue(false,"Not Able to Click on Tab : "+tabName+" For : "+name);
				log(LogStatus.SKIP,"Not Able to Click on Tab : "+tabName+" For : "+name,YesNo.Yes);
			}
			i++;
			switchToDefaultContent(driver);
		}
		
		String parentID = null;
		if (lp.clickOnTab(TabName.NavatarInvestorAddOns)) {
			if (switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60))) {
				System.err.println("Successfully switched to frame.");
				switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
			}
			if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60),
					"Select fund drop down on Navatar Investor add-ons page", M4F1)) {
				appLog.info(M4F1 + "value is select from dropdown.");
				if (niam.clickOnViewLinkOfDisclaimer(M4DIS1)) {
					appLog.info("Disclaimer Statistics Popup is opened.");
					if (niam.clickOnContactNameLink(M4CFN2 + " " + M4CLN2)) {
						appLog.info("Successfully click on contact : " + M4CFN2 + " " + M4CLN2);
						ThreadSleep(5000);
						parentID = switchOnWindow(driver);
						if (parentID != null) {
							
							WebElement ele = cp.getContactFullNameInViewMode(environment,mode,60);
							if (ele!=null &&  ele.getText().trim().equalsIgnoreCase(M4CFN2 + " " + M4CLN2)) {
								appLog.info("" + M4CFN2 + " " + M4CLN2
										+ " detail Page is opened in new tab and has been verified.");
							} else {
								appLog.error(
										"Contact detail Page is not opened in new tab after click on Contact Name Link.");
								sa.assertTrue(false,
										"Contact detail Page is not opened in new tab after click on Contact Name Link.");
							}
							driver.close();
							driver.switchTo().window(parentID);
							ThreadSleep(5000);
							if (switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60))) {
								System.err.println("Successfully switched to frame.");
								switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
							}
						} else {
							appLog.error("not switched to Contact detail page.");
							sa.assertTrue(false, "not switched to Contact detail page.");
						}
					} else {
						appLog.error("Not able click on Contact2 Link");
						sa.assertTrue(false, "Not able click on Contact2 Link.");
					}

					if (niam.clickOnFirmNameLink(M4I1)) {
						appLog.info("Clicked on contact1 firm " + M4I1 + " Successfully.");
						ThreadSleep(5000);
						parentID = switchOnWindow(driver);
						if (parentID != null) {
							WebElement ele = ip.getLegalNameLabelTextbox(60);
							if (ele!=null && ele.getText().trim().contains(M4I1)) {
								appLog.info("institutions page for " + M4I1
										+ " is opened successfully after clicking on firm name in content grid");
							} else {
								appLog.error(
										"instittuions page could not be opened after clicking on firm name on content grid");
								sa.assertTrue(false,
										"instittuions page could not be opened after clicking on firm name on content grid");
							}
							driver.close();
							driver.switchTo().window(parentID);
							driver.switchTo().defaultContent();
						} else {
							appLog.error("not switched to Firm detail page.");
							sa.assertTrue(false, "not switched to Firm detail page.");
						}
					} else {
						appLog.error("Not able click on Firm Link");
						sa.assertTrue(false, "Not able click on Firm Link.");
					}
				} else {
					appLog.error(" Disclaimer View Popup is not opened. ");
					sa.assertTrue(false, " Disclaimer View Popup is not opened.");
				}
			} else {
				appLog.error(M4F1 + " fund name is not present in the drop down, So not able to select.");
				sa.assertTrue(false, M4F1 + " fund name is not present in the drop down, So not able to select.");
			}

		} else {
			appLog.error("Navatar addon tab cannot be clicked, So cannot check the UI.");
			sa.assertTrue(false, "Navatar addon tab cannot be clicked, So cannot check the UI.");
		}

		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc044_UpdateInformationFromInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifpb = new InvestorFirmPageBusinesslayer(driver);
		InvestorProfileBusinessLayer ifp = new InvestorProfileBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		DisclaimerPageBussinessLayer dpbl = new DisclaimerPageBussinessLayer(driver);
		if (lp.investorLogin(M4C1Email, adminPassword)) {
			if (click(driver, ifp.getProfileLink(60), "profile link on investor all firms page", action.BOOLEAN)) {
				if (click(driver, ifp.getEditIcon(60), "edit icon on my profile page", action.SCROLLANDBOOLEAN)) {
					if (sendKeys(driver, ifp.getFirstNameTextBox(60), "updated" + M4CFN1,
							"contact first name on investor profile", action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, ifp.getLastNameTextBox(60), "updated" + M4CLN1,
								"contact last name on investor profile", action.SCROLLANDBOOLEAN)) {
							if (click(driver, ifp.getSaveButtonMyProfilePage(60), "save button on my profie page",
									action.SCROLLANDBOOLEAN)) {
								appLog.info("first name and last name has been succesfully updated.");
							} else {
								appLog.error("save button on my profile page is not clickable");
								sa.assertTrue(false, "save button on my profile page is not clickable");
							}
						} else {
							appLog.error("last name textbox is not visible on my profile page");
							sa.assertTrue(false, "last name textbox is not visible on my profile page");
						}
					} else {
						appLog.error("first name textbox is not visible on my profile page");
						sa.assertTrue(false, "first name textbox is not visible on my profile page");
					}
				} else {
					appLog.error(
							"not able to click on edit button on My Profile page so first name and last name can't update.");
					sa.assertTrue(false,
							"not able to click on edit button on My Profile page so first name and last name can't update.");
				}
			} else {
				appLog.error("not able to click on profile link so first name and last name can't update.");
				sa.assertTrue(false, "not able to click on profile link so first name and last name can't update.");
			}

			if (click(driver, ifp.getMyFirmProfileTab(60), "my firm profile link", action.BOOLEAN)) {
				ThreadSleep(3000);
				if (click(driver, ifp.getEditIcon(60), "edit button on my firm profiel page",
						action.SCROLLANDBOOLEAN)) {
					if (sendKeys(driver, ifp.getFirmNameTextbox(60), "updated" + M4I1,
							"firm name textbox on my firm profile page", action.SCROLLANDBOOLEAN)) {
						if (click(driver, ifp.getSaveButtonFirmProfile(60), "save button on my firm profile paeg",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("firm name is successfully updated");
						} else {
							appLog.error("save button on my profile page is not clickable.");
							sa.assertTrue(false, "save button on my profile page is not clickable");
						}
					} else {
						appLog.error("firm name textbox is not visible on my profile page");
						sa.assertTrue(false, "firm name textbox is not visible on my profile page");
					}
				} else {
					appLog.error("not able to click on edit button on My Firm Profile page so firm name can't update.");
					sa.assertTrue(false,
							"not able to click on edit button on My Firm Profile page so firm name can't update.");

				}
			} else {
				appLog.error("not able to click on My Firm's Profile link so firm name can't update.");
				sa.assertTrue(false, "not able to click on My Firm's Profile link so firm name can't update.");
			}
		} else {
			appLog.info("Investor password is wrong so cannot login.");
			sa.assertTrue(false, "Investor password is wrong so cannot login");

		}
		lp.investorLogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if (lp.clickOnTab(TabName.NavatarInvestorAddOns)) {
			if (switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60))) {
				System.err.println("Successfully switched to frame.");
				switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
			}
			if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60),
					"Select fund drop down on Navatar Investor add-ons page", M4F2)) {
				appLog.info(M4F2 + "value is select from dropdown.");
				if (niam.clickOnViewLinkOfDisclaimer(M4DIS1)) {
					appLog.info("Disclaimer Statistics Popup is opened.");
					if (niam.verifyDisclaimerStatisticsGrid("updated" + M4CFN1 + " " + "updated" + M4CLN1, M4C1Email,
							"updated" + M4I1, "Accepted", getSystemDate("MM/dd/yyyy"))) {
						appLog.info("contact 1 detail is verfied ");
					} else {
						appLog.error("contact detail 1 is not verfied.");
						sa.assertTrue(false, "contact detail 1  is not verfied.");
					}
					if (niam.verifyDisclaimerStatisticsGrid(M4CFN2 + " " + M4CLN2, M4C2Email, M4I2, "Waiting", "")) {
						appLog.info(M4CFN2 + " " + M4CLN2 + " contact detail is verfied ");
					} else {
						appLog.error("contact detail 2 is not verfied.");
						sa.assertTrue(false, "contact detail 2 is not verfied.");
					}
					if (niam.verifyDisclaimerStatisticsGrid(M4CFN3 + " " + M4CLN3, M4C3Email, M4I3, "Waiting", "")) {
						appLog.info(M4CFN3 + " " + M4CLN3 + " contact detail is verfied ");
					} else {
						appLog.error("contact detail 3 is not verfied.");
						sa.assertTrue(false, "contact detail 3 is not verfied.");
					}

					if (niam.getDisclaimerViewPopUpRecordCount() == 3) {
						appLog.info("Disclaimer Statistics popup record count is verfied and equals to : "
								+ niam.getDisclaimerViewPopUpRecordCount());
					} else {
						appLog.error("record count is not verfied.");
						sa.assertTrue(false, "record count is not verfied.");
					}
				} else {
					appLog.error(" Disclaimer View Popup is not opened. ");
					sa.assertTrue(false, " Disclaimer View Popup is not opened.");
				}
			} else {
				appLog.error(M4F2 + " fund name is not present in the drop down, So not able to select.");
				sa.assertTrue(false, M4F2 + " fund name is not present in the drop down, So not able to select.");
			}

		} else {
			appLog.error("Navatar addon tab cannot be clicked, So cannot check the UI.");
			sa.assertTrue(false, "Navatar addon tab cannot be clicked, So cannot check the UI.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();

	}

	@Parameters({ "environment", "mode" }) @Test 
	public void M4tc045_UpdateInvestmentAndFirmInformationAndVerifyItsImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		NIMPageBusinessLayer np = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(Org3CRMUser1EmailID, adminPassword);
		if (lp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M4F1)) {
				if (click(driver, fp.getEditButton1(60), "Fund Deatil Page Edit Button", action.BOOLEAN)) {
					if (sendKeys(driver, fp.getFundName(60), "Updated" + M4F1, "Fund Name Text Page on Fund Page",
							action.BOOLEAN)) {
						if (click(driver, fp.getSaveButton(60), "Fund Detail Page save button", action.BOOLEAN)) {
							if (getText(driver, fp.getFundNameInViewMode(60), "Fund Name", action.SCROLLANDBOOLEAN)
									.equals("Updated" + M4F1)) {
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
						appLog.error("fund name text box is not visible on fund page so, not able to enter value.");
						sa.assertTrue(false,
								"fund name text box is not visible on fund page so, not able to enter value.");
					}
				} else {
					appLog.error("Not able click on Edit button, So cannot update fund 1 name.");
					sa.assertTrue(false, "Not able click on Edit button, So cannot update fund 1 name.");
				}
			} else {
				appLog.error("Not able click on Fund 1, So cannot update fund 1 name.");
				sa.assertTrue(false, "Not able click on Fund 1 , So cannot update fund 1 name.");
			}
			switchToDefaultContent(driver);
		} else {
			appLog.error("Not able click on Fund Tab , So cannot update fund name.");
			sa.assertTrue(false, "Not able click on Fund Tab , So cannot update fund name.");
		}
		if (lp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M4F2)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getFundRaisingWorkSpaceSection(60),
						"fundraising workspace section");
				if (click(driver, fp.getInvestmentInfo(Workspace.FundraisingWorkspace), "Investment info",
						action.SCROLLANDBOOLEAN)) {
					if (click(driver, fp.getInvestmentInfoEdit(60), "investment info edit button",
							action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, fp.getInvestmentInfoFundNameTxtbox(60), "Updated" + M4F2, "",
								action.BOOLEAN)) {
							if (click(driver, fp.getInvestmentInfoSaveBtn(40), "Investment Info save button",
									action.SCROLLANDBOOLEAN)) {
								if (getText(driver, fp.getInvestmentInfoName(60), "Fund Name", action.SCROLLANDBOOLEAN)
										.equals("Updated" + M4F2)) {
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
				} else {
					appLog.error("Not able click on Investment Info link, So cannot update fund 2 name.");
					sa.assertTrue(false, "Not able click on Investment Info link, So cannot update fund 2 name.");
				}
			} else {
				appLog.error("Not able click on Fund 2, So cannot update fund 1 name.");
				sa.assertTrue(false, "Not able click on Fund 2 , So cannot update fund 2 name.");
			}
			switchToDefaultContent(driver);
		} else {
			appLog.error("Not able click on Fund Tab , So cannot update fund name.");
			sa.assertTrue(false, "Not able click on Fund Tab , So cannot update fund name.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		np = new NIMPageBusinessLayer(driver);
		bp = new BasePageBusinessLayer(driver);
		NavatarInvestorAddonsPageBusinessLayer niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
		if (bp.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 60, np.getNIMTabParentFrame_Lightning());
			ThreadSleep(5000);
			switchToFrame(driver, 30, np.getFrame(environment,mode,PageName.NavatarInvestorManager, 30));
			if (np.clickOnSideMenusTab(sideMenu.MyFirmProfile)) {
				if (np.clickOnEditIcon()) {
					if (sendKeys(driver, np.getMyFirmProfileNameTextBox(60), Org3UpdatedFirmName, "firm name textbox",
							action.SCROLLANDBOOLEAN)) {
						if (click(driver, np.getMyFirmProfilesaveBtn(60), "firm profile save btuton",
								action.SCROLLANDBOOLEAN)) {
							if (getText(driver, np.getMyFirmPofilelabelText(60), "firm name text",
									action.SCROLLANDBOOLEAN).equalsIgnoreCase(Org3UpdatedFirmName)) {
								appLog.info("firm name has been updated");
							} else {
								appLog.error("Firm name is not saved.");
								sa.assertTrue(false, "Firm name is not saved.");
							}
						} else {
							appLog.error("Save button is not clicked so ,Updated firm name is not able to save.");
							sa.assertTrue(false,
									"Save button is not clicked so ,Updated firm name is not able to save.");
						}
					} else {
						appLog.error(
								"Firm name text box is not visible so, not able to enter value inside firm name textbox.");
						sa.assertTrue(false,
								"Firm name text box is not visible so, not able to enter value inside firm name textbox.");
					}
				} else {
					appLog.error("Not able to click on edit button so firm name is not editable.");
					sa.assertTrue(false, "Not able to click on edit button so firm name is not editable.");
				}
			} else {
				appLog.error("Not able click on My Firm Profile Tab, So cannot update firm name.");
				sa.assertTrue(false, "Not able click on My Firm Profile Tab, So cannot update firm name.");
			}
			switchToDefaultContent(driver);
		} else {
			appLog.error("Not able click on Navatar Investor Managaer Tab , So cannot update firm name.");
			sa.assertTrue(false, "Not able click on Navatar Investor Managaer Tab , So cannot update firm name.");
		}
		if (lp.clickOnTab(TabName.NavatarInvestorAddOns)) {
			if (switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame(60))) {
				System.err.println("Successfully switched to frame.");
				switchToFrame(driver, 30, niam.getNavatarInvestorAddOnFrame1(60));
			}
			if (selectVisibleTextFromDropDown(driver, niam.getSelectFundDropDown(60),
					"Select fund drop down on Navatar Investor add-ons page", "Updated" + M4F2)) {
				appLog.info("Updated" + M4F2 + "value is select from dropdown.");
				if (niam.clickOnViewLinkOfDisclaimer(M4DIS1)) {
					appLog.info("Disclaimer Statistics Popup is opened.");
					if (niam.verifyDisclaimerStatisticsGrid("updated" + M4CFN1 + " " + "updated" + M4CLN1, M4C1Email,
							"updated" + M4I1, "Accepted", getSystemDate("MM/dd/yyyy"))) {
						appLog.info("contact 1 detail is verfied ");
					} else {
						appLog.error("contact detail 1 is not verfied.");
						sa.assertTrue(false, "contact detail 1  is not verfied.");
					}
					if (niam.verifyDisclaimerStatisticsGrid(M4CFN2 + " " + M4CLN2, M4C2Email, M4I2, "Waiting", "")) {
						appLog.info(M4CFN2 + " " + M4CLN2 + " contact detail is verfied ");
					} else {
						appLog.error("contact detail 2 is not verfied.");
						sa.assertTrue(false, "contact detail 2 is not verfied.");
					}
					if (niam.verifyDisclaimerStatisticsGrid(M4CFN3 + " " + M4CLN3, M4C3Email, M4I3, "Waiting", "")) {
						appLog.info(M4CFN3 + " " + M4CLN3 + " contact detail is verfied ");
					} else {
						appLog.error("contact detail 3 is not verfied.");
						sa.assertTrue(false, "contact detail 3 is not verfied.");
					}

					if (niam.getDisclaimerViewPopUpRecordCount() == 3) {
						appLog.info("Disclaimer Statistics popup record count is verfied and equals to : "
								+ niam.getDisclaimerViewPopUpRecordCount());
					} else {
						appLog.error("record count is not verfied.");
						sa.assertTrue(false, "record count is not verfied.");
					}
				} else {
					appLog.error(" Disclaimer View Popup is not opened. ");
					sa.assertTrue(false, " Disclaimer View Popup is not opened.");
				}
			} else {
				appLog.error("Updated" + M4F2 + " fund name is not present in the drop down, So not able to select.");
				sa.assertTrue(false,
						"Updated" + M4F2 + " fund name is not present in the drop down, So not able to select.");
			}

		} else {
			appLog.error("Navatar addon tab cannot be clicked, So cannot check the UI.");
			sa.assertTrue(false, "Navatar addon tab cannot be clicked, So cannot check the UI.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		 niam = new NavatarInvestorAddonsPageBusinessLayer(driver);
		DisclaimerPageBussinessLayer dp = new DisclaimerPageBussinessLayer(driver);
		String parentID = null;
		if (lp.investorLogin(M4C2Email, adminPassword)) {
			if (matchTitle(driver, "Firm Alerts", 60)) {
				appLog.info("Page title is successfully matched.");
			} else {
				appLog.info("Page title is not matched.");
				sa.assertTrue(false, "Page title is not matched.");
			}
			if (niam.getPendingDisclaimerPopGoToDisclaimerButton(60) != null) {
				appLog.info("Pending Disclaimer Popup is available.");
				if (click(driver, niam.getPendingDisclaimerPopGoToDisclaimerButton(60),
						"Pending Disclaimer Popup Go to Disclaimer Button", action.BOOLEAN)) {
					appLog.info("Click on Go to Disclaimer Button Successfully.");
					parentID = switchOnWindow(driver);
					if (dp.clickOnExpandIcon(M4F1, 60)) {
						if (dp.verifyFundDisplaying(M4F1, Org3UpdatedFirmName)) {
							appLog.info("Fund is displaying and fund name is verified");
						} else {
							appLog.info("Fund is not displaying and fund name is not verified");
							sa.assertTrue(false, "Fund Is Not displaying and fund name is not verified");
						}
						if (dp.verifyFundDisclaimerDescription(M4F1, M4DIS2, M4DISDEC2)) {
							appLog.info("Disclaimer 2 detail is successsfully verified");
						} else {
							appLog.info("Disclaimer 2 detail is not verified.");
							sa.assertTrue(false, "Disclaimer 2 detail is not verified.");
						}
					} else {
						appLog.info("Not bale to click on expand icon");
						sa.assertTrue(false, "Not able to click on expand icon");
					}
					if (dp.clickOnExpandIcon("Updated" + M4F2, 60)) {
						if (dp.verifyFundDisplaying("Updated" + M4F2, Org3UpdatedFirmName)) {
							appLog.info("Fund is displaying and fund name is verified");
						} else {
							appLog.info("Fund is not displaying and fund name is not verified");
							sa.assertTrue(false, "Fund Is Not displaying and fund name is not verified");
						}
						if (dp.verifyFundDisclaimerDescription("Updated" + M4F2, M4DIS1, M4DISDEC1)) {
							appLog.info("Disclaimer 1 detail is successsfully verified");
						} else {
							appLog.info("Disclaimer 1 detail is not verified.");
							sa.assertTrue(false, "Disclaimer 1 detail is not verified.");
						}
					} else {
						appLog.info("Not able to click on expand icon");
						sa.assertTrue(false, "Not able to click on expand icon");
					}
				} else {
					appLog.info("Not able to click on Go to Disclaimer Button .");
					sa.assertTrue(false, "Not able to click on Go to Disclaimer Button.");

				}
			} else {
				appLog.info("Pending Disclaimer Popup is available.");
				sa.assertTrue(false, "Pending Disclaimer Popup is not available.");
			}
		} else {
			appLog.info("Investor password is wrong so cannot login.");
			sa.assertTrue(false, "Investor password is wrong so cannot login");
		}
		driver.close();
		driver.switchTo().window(parentID);
		lp.investorLogout();
		sa.assertAll();
	}

//	@Parameters({ "environment", "mode" }) @Test 
//	public void M4tc050PostConditionForAll() {
//		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
//		lp.CRMLogin(superAdminOrg3UserName, adminPassword);
//		lp.postCondition().assertAll();
//		lp.CRMlogout(environment,mode);
//
//	}
}

