/**
 * 
 */
package com.navatar.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.navatar.generic.BaseLib;
import com.navatar.generic.SoftAssert;
import com.navatar.generic.CommonLib.Mode;
import com.navatar.generic.CommonLib.PageName;
import com.navatar.generic.CommonLib.Workspace;

import com.navatar.generic.CommonLib.action;
import com.relevantcodes.extentreports.LogStatus;

import static com.navatar.generic.AppListeners.appLog;
import static com.navatar.generic.CommonLib.*;
import static com.navatar.generic.CommonVariables.*;

import java.nio.channels.InterruptedByTimeoutException;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Parul Singh
 *
 */
public abstract class BasePage {
	
	protected WebDriver driver;

	public BasePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(xpath = "//a[@title='Logout']")
	private WebElement logout;
	
	public WebElement getLogoutButton(int timeOut) {
		return isDisplayed(driver, logout, "Visibility", timeOut, "Logout in User menu");
	}
	@FindBy(id="red")
	private WebElement scheduledMaintenancePopUp;

	/**
	 * @return the scheduledMaintenancePopUp
	 */
	public WebElement getScheduledMaintenancePopUp(int timeOut) {
		return isDisplayed(driver, scheduledMaintenancePopUp, "Visibility", timeOut, "Scheduled Maintenance Pop Up", action.SCROLL);
	}
	
	@FindBy(xpath="//a[@class='continue']")
	private WebElement scheduledMaintenanceContinueLink;

	/**
	 * @return the scheduledMaintenanceContinueLink
	 */
	public WebElement getScheduledMaintenanceContinueLink(int timeOut) {
		return isDisplayed(driver, scheduledMaintenanceContinueLink, "Visibility", timeOut, "scheduled Maintenance Continue Link");
	}
	
	@FindBy(xpath="//a[@title='Contacts Tab']")
	private WebElement contactsTab;

	/**
	 * @return the contactsTab
	 */
	public WebElement getContactsTab(int timeOut) {
		return isDisplayed(driver, contactsTab, "Visibility", timeOut, "Contacts Tab");
	}
	
	@FindBy(xpath="//a[@title='Funds Tab']")
	private WebElement fundsTab;

	/**
	 * @return the fundsTab
	 */
	public WebElement getFundsTab(int timeOut) {
		return isDisplayed(driver, fundsTab, "Visibility", timeOut, "Funds Tab");
	}
	
	@FindBy(xpath="//a[@title='Institutions Tab']")
	private WebElement institutionsTab;

	/**
	 * @return the fundsTab
	 */
	public WebElement getInstitutionsTab(int timeOut) {
		return isDisplayed(driver, institutionsTab, "Visibility", timeOut, "Institutions Tab");
	}
	
	@FindBy(xpath="//a[@title='Fundraisings Tab']")
	private WebElement fundRaisingsTab;

	/**
	 * @return the fundRaisingsTab
	 */
	public WebElement getFundRaisingsTab(int timeOut) {
		return isDisplayed(driver, fundRaisingsTab, "Visibility", timeOut, "FundRaisings Tab");
	} 
	
	@FindBy(xpath="//select[@id='fcf']")
	private WebElement viewDropdown;

	/**
	 * @return the viewDropdown
	 */
	public WebElement getViewDropdown(int timeOut) {
		return isDisplayed(driver, viewDropdown, "Visibility", timeOut, "View Dropdown");
	}
	@FindBy(xpath="//input[@name='go']")
	private WebElement goButton;

	/**
	 * @return the goButton
	 */
	public WebElement getGoButton(int timeOut) {
		return isDisplayed(driver, goButton, "Visibility", timeOut, "Go Button");
	} 
	
	@FindBy(xpath="//input[@name='new']")
	private WebElement newButton;

	/**
	 * @return the newButton
	 */
	public WebElement getNewButton(int timeOut) {
		return isDisplayed(driver, newButton, "Visibility", timeOut, "New Button");
	}
	
	@FindBy(xpath="//input[@name='save']")
	private WebElement saveButton;

	/**
	 * @return the saveButton
	 */
	public WebElement getSaveButton(int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, saveButtonClassic, "Visibility", timeOut, "Save Button Classic");
		}else{
			return isDisplayed(driver, saveButtonLighting, "Visibility", timeOut, "Save Button Lighting");
		}
	} 
	@FindBy(xpath = "//a[text()='Close']")
	private WebElement lightingCloseButton;

	/**
	 * @return the lightingCloseButton
	 */
	public WebElement getLightingCloseButton(int timeOut) {
		return isDisplayed(driver, lightingCloseButton, "Visibility", timeOut, "Linghting Pop-Up Close Icon");
	}
	

	@FindBy(id = "userNavLabel")
	private WebElement userMenuTab;

	/**
	 * @return the userMenuTab
	 */
	public WebElement getUserMenuTab(int timeOut) {
		return isDisplayed(driver, userMenuTab, "Visibility", timeOut, "User Menu Tab");
	}
	
	@FindBy(xpath="//div[@class='menuButton menuButtonRounded']")
	private WebElement UserNameAtUserMenuTab;
	

	/**
	 * @return the userNameAtUserMenuTab
	 */
	public WebElement getUserNameAtUserMenuTab(int timeOut) {
		return isDisplayed(driver, UserNameAtUserMenuTab, "Visibility", timeOut, "User Name at user menu tab");
	}

	@FindBy(xpath = "//a[@title='Setup']")
	private WebElement userMenuSetupLink;

	/**
	 * @return the userMenuSetupLink
	 */
	public WebElement getUserMenuSetupLink(int timeOut) {
		return isDisplayed(driver, userMenuSetupLink, "Visibility", timeOut, "User Menu Setup Link");
	}

	@FindBy(xpath = "(//a[@title='Logout'])[1]")
	private WebElement logOutButton;

	/**
	 * @return the logOutButton
	 */
	public WebElement getLogOutButton(int timeOut) {
		return isDisplayed(driver, logOutButton, "Visibility", timeOut, "Logout Button");
	}
	
	@FindBy(xpath = "//img[contains(@title,'Expand - Manage Users')]")
	private WebElement expandManageUserIcon;

	/**
	 * @return the expandManageUserIcon
	 */
	public WebElement getExpandManageUserIcon(int timeOut) {
		return isDisplayed(driver, expandManageUserIcon, "Visibility", timeOut, "Expand Manage User Icon");
	}
	
	@FindBy(xpath = "//a[@id='ManageUsers_font']")
	private WebElement usersLink;

	/**
	 * @return the usersLink
	 */
	public WebElement getUsersLink(int timeOut) {
		return isDisplayed(driver, usersLink, "Visibility", timeOut, "Users Link");
	}
	
	@FindBy(xpath = "(//input[@title='New User'])[1]")
	private WebElement newUserLink;

	/**
	 * @return the newUserLink
	 */
	public WebElement getNewUserLink(int timeOut) {
		return isDisplayed(driver, newUserLink, "Visibility", timeOut, "New User Button");
	}
	
	@FindBy(id="name_firstName")
	private WebElement userFirstName;

	/**
	 * @return the userFirstName
	 */
	public WebElement getUserFirstName(int timeOut) {
		return isDisplayed(driver, userFirstName, "Visibility", timeOut, "User First Name");
	}
	
	@FindBy(id="name_lastName")
	private WebElement userLastName;

	/**
	 * @return the userLastName
	 */
	public WebElement getUserLastName(int timeOut) {
		return isDisplayed(driver, userLastName, "Visibility", timeOut, "User Last Name");
	}
	@FindBy(id="user_license_id")
	private WebElement userUserLicenseDropDownList;

	/**
	 * @return the userUserLicenseDropDownList
	 */
	public WebElement getUserUserLicenseDropDownList(int timeOut) {
		return isDisplayed(driver, userUserLicenseDropDownList, "Visibility", timeOut, "User License Drop Down List");
	}
	
	@FindBy(id="Profile")
	private WebElement userProfileDropDownList;

	/**
	 * @return the userProfileDropDownList
	 */
	public WebElement getUserProfileDropDownList(int timeOut) {
		return isDisplayed(driver, userProfileDropDownList, "Visibility", timeOut, "User Profile Drop Down List");
	}
	
	@FindBy(id="Email")
	private WebElement userEmailId;

	/**
	 * @return the userEmailId
	 */
	public WebElement getUserEmailId(int timeOut) {
		return isDisplayed(driver, userEmailId, "Visibility", timeOut, "User Email Id");
	}
	
	@FindBy(xpath="//td[text()='Email']/../td[2]/a")
	private WebElement userEmailIDLabeltext;

	/**
	 * @return the userEmailIDLabeltext
	 */
	public WebElement getUserEmailIDLabeltext(int timeOut) {
		return isDisplayed(driver, userEmailIDLabeltext, "Visibility", timeOut, "Email ID Label Text");
	}
	
	@FindBy(id = "ImportedPackage_font")
	private WebElement installedpackageLink;

	/**
	 * @return the installedpackageLink
	 */
	public WebElement getInstalledpackageLink(int timeOut) {
		return isDisplayed(driver, installedpackageLink, "Visibility", timeOut, "Installed package");
	}
	
	@FindBy(xpath = "//a[contains(@title,'Manage Licenses')]")
	private WebElement manageLicensesLink;

	/**
	 * @return the manageLicensesLink
	 */
	public WebElement getManageLicensesLink(int timeOut) {
		return isDisplayed(driver, manageLicensesLink, "Visibility", timeOut, "Manage Licenses Link");
	}
	
	@FindBy(xpath = "//input[@title='Add Users']")
	private WebElement addUsersbutton;

	/**
	 * @return the addUsersbutton
	 */
	public WebElement getAddUsersbutton(int timeOut) {
		return isDisplayed(driver, addUsersbutton, "Visibility", timeOut, "Add Users Button");
	}
	
	@FindBy(id="available")
	private WebElement installedPackageFrame;

	/**
	 * @return the installedPackageFrame
	 */
	public WebElement getInstalledPackageFrame(int timeOut) {
		return isDisplayed(driver, installedPackageFrame, "Visibility", timeOut, "Add Users frame in installed package.");
	}
	
	@FindBy(xpath="//div[@class='listRelatedObject userBlock']//form/div[2]/table//tr[1]//a[contains(@title,'Active')]")
	private WebElement ActiveUserTab;

	/**
	 * @return the activeUserTab
	 */
	public WebElement getActiveUserTab(int timeOut) {
		return isDisplayed(driver, ActiveUserTab, "Visibility", timeOut, "Active User Tab");
	}
	
	@FindBy(xpath="//input[@title='Add']")
	private WebElement activeUserAddButton;

	/**
	 * @return the activeUserAddButton
	 */
	public WebElement getActiveUserAddButton(int timeOut) {
		return isDisplayed(driver, activeUserAddButton, "Visibility", timeOut, "Active User Add Button");
	}
	@FindBy(xpath="//label[text()='Salesforce CRM Content User']/../..//input")
	private WebElement SalesforceCRMContentUserCheckBox;

	/**
	 * @return the salesforceCRMContentUserCheckBox
	 */
	public WebElement getSalesforceCRMContentUserCheckBox(int timeOut) {
		return isDisplayed(driver, SalesforceCRMContentUserCheckBox, "Visibility", timeOut, "Salesforce CRM Content User Check Box");
	}
	
	@FindBy(xpath = "//h2[contains(text(),'Change Your Password')]")
	private WebElement chnageYourPassword;

	/**
	 * @return the chnageYourPassword
	 */
	public WebElement getChnageYourPassword(int timeOut) {
		return isDisplayed(driver, chnageYourPassword, "Visibility", timeOut, "Change password Text label");
	}
	
	@FindBy(id = "newpassword")
	private WebElement newPassword;

	/**
	 * @return the newPassword
	 */
	public WebElement getNewPassword(int timeOut) {
		return isDisplayed(driver, newPassword,"Visibility", timeOut, "New password Text Box");
	}
	
	@FindBy(id = "confirmpassword")
	private WebElement confimpassword;

	/**
	 * @return the confimpassword
	 */
	public WebElement getConfimpassword(int timeOut) {
		return isDisplayed(driver, confimpassword, "Visibility", timeOut, "Confirm password Text Box");
	}

	@FindBy(id = "question")
	private WebElement question;

	/**
	 * @return the question
	 */
	public WebElement getQuestion(int timeOut) {
		return isDisplayed(driver, question, "Visibility", timeOut, "Question drop Doown List");
	}

	@FindBy(id = "answer")
	private WebElement answer;

	/**
	 * @return the answer
	 */
	public WebElement getAnswer(int timeOut) {
		return isDisplayed(driver, answer, "Visibility", timeOut, "Answer Text Box");
	}

	@FindBy(xpath = "//button[contains(text(),' Change Password')]")
	private WebElement changePassword;

	/**
	 * @return the changePassword
	 */
	public WebElement getChangePassword(int timeOut) {
		return isDisplayed(driver, changePassword, "Visibility", timeOut, "Change Password Button");
	}

	@FindBy(xpath="//li[@id='AllTab_Tab']/a")
	private WebElement allTabBtn;

	/**
	 * @return the allTabBtn
	 */
	public WebElement getAllTabBtn(int timeOut) {
		return isDisplayed(driver, allTabBtn, "Visibility", timeOut, "All Tab Button");
	}
	
	@FindBy(xpath="//div[@class='bDescription']/a")
	private WebElement addTabLink;

	/**
	 * @return the addTabLink
	 */
	public WebElement getAddTabLink(int timeOut) {
		return isDisplayed(driver, addTabLink, "Visibility", timeOut, "Add a Tab Link");
	}
	@FindBy(xpath="//tr[@ class='last detailRow']//table//td[1]/select")
	private WebElement availableTabList;

	/**
	 * @return the availableTabList
	 */
	public WebElement getAvailableTabList(int timeOut) {
		return isDisplayed(driver, availableTabList, "Visibility", timeOut, "Available Tab Drop Down List");
	}
	
	@FindBy(xpath="//tr[@ class='last detailRow']//td[2]/div[2]/a")
	private WebElement customTabAddBtn;

	/**
	 * @return the customTabAddBtn
	 */
	public WebElement getCustomTabAddBtn(int timeOut) {
		return isDisplayed(driver, customTabAddBtn, "Visibility", timeOut, "Custom Tab Add Button");
	}
	
	@FindBy(xpath="//div[@id='ep']/div[3]//input[@name='save']")
	private WebElement customTabSaveBtn;

	/**
	 * @return the customTabSaveBtn
	 */
	public WebElement getCustomTabSaveBtn(int timeOut) {
		return isDisplayed(driver, customTabSaveBtn, "Visibility", timeOut, "Custom Tab Save Button");
	}
	
	@FindBy(xpath="//tr[@ class='last detailRow']//table//td[3]/select")
	private WebElement customTabSelectedList;

	/**
	 * @return the customTabSelectedList
	 */
	public WebElement getCustomTabSelectedList(int timeOut) {
		return isDisplayed(driver, customTabSelectedList, "Visibility", timeOut, "Custom Tab Selected List");
	}
	@FindBy(xpath="//tr[@ class='last detailRow']//td[2]/div[3]/a")
	private WebElement customTabRemoveBtn;

	/**
	 * @return the customTabRemoveBtn
	 */
	public WebElement getCustomTabRemoveBtn(int timeOut) {
		return isDisplayed(driver, customTabRemoveBtn, "Visibility", timeOut, "Custom Tab Remove Button");
	}
	
	@FindBy(xpath="//a[@title='Navatar Investor Manager Tab']")
	private WebElement navatarInvestorManagerTab;

	/**
	 * @return the navatarInvestorManagerTab
	 */
	public WebElement getNavatarInvestorManagerTab(int timeOut) {
		return isDisplayed(driver, navatarInvestorManagerTab, "Visibility", timeOut, "Navatar Investor Manager Tab");
	}

	@FindBy(xpath="(//div[contains(@class,'uiTooltip')])[7]")
	private WebElement salesForceLightingIcon;

	/**
	 * @return the salesForceLightingIcon
	 */
	public WebElement getSalesForceLightingIcon(int timeOut) {
		return isDisplayed(driver, salesForceLightingIcon, "Visibility", timeOut, "Sales Force Lighting Icon");
	}
	@FindBy(xpath="//div[contains(@class,'userProfilePanel')]//a[contains(@class,'switch-to-aloha')]")
	private WebElement switchToClassic;

	/**
	 * @return the switchToClassic
	 */
	public WebElement getSwitchToClassic(int timeOut) {
		return isDisplayed(driver, switchToClassic, "Visibility", timeOut, "Sales force switch to classic mode");
	}
	
	@FindBy(xpath="//span[text()='Workspace']/../../button")
	private WebElement workspaceExpandIcon;

	/**
	 * @return the workspaceExpandIcon
	 */
	public WebElement getWorkspaceExpandIcon(int timeOut) {
		return isDisplayed(driver, workspaceExpandIcon, "Visibility", timeOut, "Workspace Expand Icon");
	}
	
	@FindBys(@FindBy(xpath="//span[@id='setupErrorFR']"))
	private List<WebElement>  getErrorMessageOnAllPages;

	/**
	 * @return the errorMessageBeforeAdminRegistrationOnAllPages
	 */
	
	public List<WebElement> getErrorMessageOnAllPages(int timeOut) {
		if(checkElementsVisibility(driver, getErrorMessageOnAllPages, "Error Messages", timeOut)){
			return getErrorMessageOnAllPages;
		} else {
			return null;
		}
	}
	
	public WebElement getFolderTypeRadioButton(FolderType folderType, Workspace workspace,PageName pageName, int timeOut){
		String workspaceSelector = "";
		if(folderType.toString().equalsIgnoreCase(FolderType.Common.toString())&&pageName.toString().equalsIgnoreCase(PageName.NavatarInvestorManager.toString())){
			folderType=FolderType.Global;
		}
		String xpath = "//input[@id='chk"+folderType.toString()+"Folder']";
		if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "BWFR";
			} else {
				workspaceSelector = "BWINV";
			}
			xpath = "//input[@id='chk"+folderType.toString()+"Folder"+workspaceSelector+"']";
		} else if (pageName.toString().equalsIgnoreCase(PageName.ManageFolderPopUp.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "fundraising";
			} else {
				workspaceSelector = "investor";
			}
			xpath = "//input[@id='checkbox"+folderType.toString().toLowerCase()+"_grid1"+workspaceSelector+"']";
		}
		return isDisplayed(driver, FindElement(driver, xpath, folderType.toString()+" Folder Radio button", action.BOOLEAN, timeOut), "Visibility", timeOut, folderType.toString()+" Folder Radio button");
	}
	
	public WebElement getParentFolderNameTextBox(Workspace workspace,PageName pageName, int timeOut){
		String workspaceSelector = "";
		String xpath = "//input[@id='txtParentFolderName']";
		if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "BWFR";
			} else {
				workspaceSelector = "BWINV";
			}
			xpath = "//input[@id='txtParentFolderName"+workspaceSelector+"']";
		} else if (pageName.toString().equalsIgnoreCase(PageName.ManageFolderPopUp.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "fundraising";
			} else {
				workspaceSelector = "investor";
			}
			xpath = "//input[@id='foldecommonaddfund"+workspaceSelector+"']";
		}
		return isDisplayed(driver, FindElement(driver, xpath, "Parent Folder Name Text Box", action.BOOLEAN, timeOut), "Visibility", timeOut, "Parent Folder Name Text Box");
	}
	
	public WebElement getParentFolderSaveButton(Workspace workspace, PageName pageName, int timeOut){
		String workspaceSelector = "";
		String xpath = "//a[contains(@onclick,'checkAndCreateFolder')][contains(@onclick,'txtParentFolderName')]";
		if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "BWFR";
			} else {
				workspaceSelector = "BWINV";
			}
			xpath = "//a[contains(@onclick,'checkAndCreateFolder"+workspaceSelector+"')][contains(@onclick,'txtParentFolderName"+workspaceSelector+"')]";
		} else if (pageName.toString().equalsIgnoreCase(PageName.ManageFolderPopUp.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "fundraising";
			} else {
				workspaceSelector = "investor";
			}
			xpath = "//a[contains(@onclick,'CreateCommon_pop1"+workspaceSelector+"();')]";
		}
		return isDisplayed(driver, FindElement(driver, xpath, "Parent Folder Save Button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Parent Folder Save Button");
	}
	
	public WebElement getChildFolderNameTextBox(Workspace workspace, PageName pageName, int timeOut){
		String workspaceSelector = "";
		String xpath = "//input[@id='txtChildFolderName']";
		if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "BWFR";
			} else {
				workspaceSelector = "BWINV";
			}
			xpath = "//input[@id='txtChildFolderName"+workspaceSelector+"']";
		} else if (pageName.toString().equalsIgnoreCase(PageName.ManageFolderPopUp.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "fundraising";
			} else {
				workspaceSelector = "investor";
			}
			xpath = "//input[@id='foldernameaddfundr2"+workspaceSelector+"']";
		}
		return isDisplayed(driver, FindElement(driver, xpath, "Child Folder Name Text Box", action.BOOLEAN, timeOut), "Visibility", timeOut, "child Folder Name Text Box");
	}
	
	public WebElement getParentRenameFolderNameTextBox(Workspace workspace, PageName pageName, int timeOut){
		String workspaceSelector = "";
		String xpath = "";
		if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.BuildStep2Of3.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "FR";
			} else {
				workspaceSelector = "INV";
			}
			xpath = "//input[@id='txtRenameFolderBW"+workspaceSelector+"']";
		} else if (pageName.toString().equalsIgnoreCase(PageName.ManageFolderPopUp.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "fundraising";
			} else {
				workspaceSelector = "investor";
			}
			xpath = "//input[@id='renamefol_pop1"+workspaceSelector+"']";
		}
		return isDisplayed(driver, FindElement(driver, xpath, "parent rename Folder Name Text Box", action.BOOLEAN, timeOut), "Visibility", timeOut, "parent rename Folder Name Text Box");
	}
	
	public WebElement getChildRenameFolderNameTextBox(Workspace workspace, PageName pageName, int timeOut){
		String workspaceSelector = "";
		String xpath = "";
		if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())){
			//code needs to be write
		} else if (pageName.toString().equalsIgnoreCase(PageName.ManageFolderPopUp.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "fundraising";
			} else {
				workspaceSelector = "investor";
			}
			xpath = "//input[@id='renamefol_pop1"+workspaceSelector+"']";
		}
		return isDisplayed(driver, FindElement(driver, xpath, "parent rename Folder Name Text Box", action.BOOLEAN, timeOut), "Visibility", timeOut, "parent rename Folder Name Text Box");
	}
	
	public WebElement getCreatedFolderWebelement(String folderPath,PageName pageName,int timeOut) {
		WebElement ele = null;
		String folderStruct[] = folderPath.split("/");
		if(folderStruct.length==1) {
			ele = isDisplayed(driver, FindElement(driver,
					"//span[contains(text(),'All Folders')]/span/../../../following-sibling::ul/li/div//label[contains(text(),'"
							+ folderStruct[0] + "')]",
							" created folder", action.SCROLLANDBOOLEAN, timeOut), "visibility", timeOut, "created folder");
		}else {
			ele = isDisplayed(driver, FindElement(driver,
					"//span[contains(text(),'All Folders')]/span/../../../following-sibling::ul/li/div//label[contains(text(),'"
							+ folderStruct[0] + "')]/../../../following-sibling::ul/li/div//label[contains(text(),'"
							+ folderStruct[1] + "')]",
							"created sub folder", action.SCROLLANDBOOLEAN, timeOut), "visibility", timeOut, "created sub folder");
		}
		return ele;
	}
	
	
	
	
	public WebElement getChildFolderSaveButton(Workspace workspace, PageName pageName, int timeOut){
		String workspaceSelector = "";
		String xpath = "(//a[contains(@onclick,'checkAndCreateFolder')])[2]";
		if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "BWFR";
			} else {
				workspaceSelector = "BWINV";
			}
			xpath = "//a[contains(@onclick,'checkAndCreateFolder"+workspaceSelector+"')][contains(@id,'btnSubFolderSave')]";
		} else if (pageName.toString().equalsIgnoreCase(PageName.ManageFolderPopUp.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "fundraising";
			} else {
				workspaceSelector = "investor";
			}
			xpath = "//a[contains(@onclick,'CreateFolder_pop1"+workspaceSelector+"();')]";
		}
		return isDisplayed(driver, FindElement(driver, xpath, "Parent Folder Save Button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Parent Folder Save Button");
	}
	
	public WebElement getParentFolderErrorMsg(Workspace workspace, PageName pageName, ErrorMessageType ErrorMessageType, int timeOut){
		String workspaceSelector = "";
		String xpath = "";
		if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())){
			// code need to be write for fund page
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "FR";
			} else {
				workspaceSelector = "INV";
			}
			if(ErrorMessageType.toString().equalsIgnoreCase(ErrorMessageType.BlankErrorMsg.toString())) {
				xpath = "//span[@id='err1stLvlFolderBW"+workspaceSelector+"']";
			}else if (ErrorMessageType.toString().equalsIgnoreCase(ErrorMessageType.PrefixErrorMsg.toString())) {
				xpath = "//span[@id='err1stLvlFolderForCommonBW"+workspaceSelector+"']";
			}else if (ErrorMessageType.toString().equalsIgnoreCase(ErrorMessageType.SpiecalCharErrorMsg.toString())) {
				xpath = "//span[@id='errforInvalidCharFirstFundBW"+workspaceSelector+"']";
			}else if (ErrorMessageType.toString().equalsIgnoreCase(ErrorMessageType.FolderCreationRestrictionErrorMsg.toString())) {
				xpath="//div[@id='errMsgSecondCommonFolderBW"+ workspaceSelector +"']";
			} else {
				xpath = "//div[@id='errMsgDupFolderBW"+ workspaceSelector +"']";
			}
		} else if (pageName.toString().equalsIgnoreCase(PageName.ManageFolderPopUp.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
					workspaceSelector = "fundraising";
			} else {
				workspaceSelector = "investor";
			}
			if(ErrorMessageType.toString().equalsIgnoreCase(ErrorMessageType.BlankErrorMsg.toString())) {
				xpath = "//span[@id='errorfoldecommonaddfund"+workspaceSelector+"']";
			}else if (ErrorMessageType.toString().equalsIgnoreCase(ErrorMessageType.PrefixErrorMsg.toString())) {
				xpath = "//span[@id='errorfoldecommonaddfund22"+workspaceSelector+"']";
			}else if (ErrorMessageType.toString().equalsIgnoreCase(ErrorMessageType.SpiecalCharErrorMsg.toString())) {
				xpath = "//span[@id='errforInvalidCharFirst1"+workspaceSelector+"']";
			}else if (ErrorMessageType.toString().equalsIgnoreCase(ErrorMessageType.FolderCreationRestrictionErrorMsg.toString())) {
				xpath="//div[@id='CommonError1investor']//p";
			}
		}
		return isDisplayed(driver, FindElement(driver, xpath, "Folder Error Message", action.BOOLEAN, timeOut), "Visibility", timeOut, "Folder Error Message");
	}
	
	public WebElement getSubFolderErrorMsg(Workspace workspace, PageName pageName, ErrorMessageType ErrorMessageType, int timeOut){
		String workspaceSelector = "";
		String xpath = "";
		if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())){
			// code need to be write for fund page
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "FR";
			} else {
				workspaceSelector = "INV";
			}
			if(ErrorMessageType.toString().equalsIgnoreCase(ErrorMessageType.BlankErrorMsg.toString())) {
				xpath = "//span[@id='errSubFolderNameBW"+workspaceSelector+"']";
			}else if (ErrorMessageType.toString().equalsIgnoreCase(ErrorMessageType.PrefixErrorMsg.toString())) {
				xpath = "//span[@id='errSubFolderNameforCommonBW"+workspaceSelector+"']";
			}else if (ErrorMessageType.toString().equalsIgnoreCase(ErrorMessageType.SpiecalCharErrorMsg.toString())) {
				xpath = "//span[@id='errforInvalidCharSubFundBW"+workspaceSelector+"']";
			} else {
				xpath="//span[@id='errDupSubFolderNameBW" + workspaceSelector + "']";
			}
		} else if (pageName.toString().equalsIgnoreCase(PageName.ManageFolderPopUp.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
					workspaceSelector = "fundraising";
			} else {
				workspaceSelector = "investor";
			}
			if(ErrorMessageType.toString().equalsIgnoreCase(ErrorMessageType.BlankErrorMsg.toString())) {
				xpath = "//span[@id='errorfoldernameaddfundr2"+workspaceSelector+"']";
			}else if (ErrorMessageType.toString().equalsIgnoreCase(ErrorMessageType.PrefixErrorMsg.toString())) {
				xpath = "//span[@id='errorfoldernameaddfundr2"+workspaceSelector+"22']";
			}else if (ErrorMessageType.toString().equalsIgnoreCase(ErrorMessageType.SpiecalCharErrorMsg.toString())) {
				xpath = "//span[@id='addsubInvalid1"+workspaceSelector+"']";
			}
		}
		return isDisplayed(driver, FindElement(driver, xpath, "Sub Folder Error Message", action.BOOLEAN, timeOut), "Visibility", timeOut, "Sub Folder Error Message");
	}
	
	
	public WebElement getParentRenameFolderErrorMsg(Workspace workspace, PageName pageName, ErrorMessageType ErrorMessageType, int timeOut){
		String workspaceSelector = "";
		String xpath = "";
		if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.BuildStep2Of3.toString())){
			// code need to be write for fund page
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "FR";
			} else {
				workspaceSelector = "INV";
			}
			if(ErrorMessageType.toString().equalsIgnoreCase(ErrorMessageType.BlankErrorMsg.toString())) {
				xpath = "//span[@id='errRenFolderNameBW"+workspaceSelector+"']";
			}else if (ErrorMessageType.toString().equalsIgnoreCase(ErrorMessageType.PrefixErrorMsg.toString())) {
//				xpath = "//span[@id='errRenFolderNameForCommonBW"+workspaceSelector+"11']";
				xpath = "//span[@id='errRenFolderNameForCommonBW"+workspaceSelector+"']";
			}else if (ErrorMessageType.toString().equalsIgnoreCase(ErrorMessageType.SpiecalCharErrorMsg.toString())) {
				xpath = "//span[@id='errforCharRenameBW"+workspaceSelector+"']";
			}
		} else if (pageName.toString().equalsIgnoreCase(PageName.ManageFolderPopUp.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
					workspaceSelector = "fundraising";
			} else {
				workspaceSelector = "investor";
			}
			if(ErrorMessageType.toString().equalsIgnoreCase(ErrorMessageType.BlankErrorMsg.toString())) {
				xpath = "//span[@id='errorrenamefol_pop1"+workspaceSelector+"']";
			}else if (ErrorMessageType.toString().equalsIgnoreCase(ErrorMessageType.PrefixErrorMsg.toString())) {
				xpath = "//span[@id='errorrenamefol_pop1"+workspaceSelector+"11']";
				//xpath changed to previous - Avinash
//				xpath = "//span[@id='errorrenamefol_pop1"+workspaceSelector+"']";
			}else if (ErrorMessageType.toString().equalsIgnoreCase(ErrorMessageType.SpiecalCharErrorMsg.toString())) {
				xpath = "//span[@id='renameInvalid1"+workspaceSelector+"']";
			}
		}
		return isDisplayed(driver, FindElement(driver, xpath, "Rename Folder Error Message", action.BOOLEAN, timeOut), "Visibility", timeOut, "Rename Folder Error Message");
	}
	
	public WebElement getParentRenameFolderSaveButton(Workspace workspace, PageName pageName, int timeOut){
		String workspaceSelector = "";
		String xpath = "(//a[contains(@onclick,'checkAndCreateFolder')])[2]";
		if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.BuildStep2Of3.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "FR";
			} else {
				workspaceSelector = "INV";
			}
			if(pageName.toString().equalsIgnoreCase(PageName.BuildStep2Of3.toString())){
				xpath = "//a[contains(@onclick,'saveRenameFolderBW"+workspaceSelector+"()')]";
			} else {
				xpath = "//a[contains(@onclick,'saveRenameFolderBW"+workspaceSelector+"();')]";
			}
		} else if (pageName.toString().equalsIgnoreCase(PageName.ManageFolderPopUp.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "fundraising";
			} else {
				workspaceSelector = "investor";
			}
			xpath = "//a[contains(@onclick,'Save_Rename_folder_pop1"+workspaceSelector+"();')]";
		}
		return isDisplayed(driver, FindElement(driver, xpath, "Parent Rename Folder Save Button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Parent Rename Folder Save Button");
	}
	
	public WebElement getParentRenameFolderCrossIcon(Workspace workspace, PageName pageName, int timeOut){
		String workspaceSelector = "";
		String xpath = "";
		if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())){
			//needs to be write
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "FR";
			} else {
				workspaceSelector = "INV";
			}
//			xpath = "//span[contains(@onclick,'clearTextBW"+workspaceSelector+"(') and contains(@onclick,'rename')  and contains(@onclick,';')]";
			xpath = "//a[contains(@onclick,'clearTextBW"+workspaceSelector+"(') and contains(@onclick,'rename')  and contains(@onclick,';')]";
		} else if (pageName.toString().equalsIgnoreCase(PageName.ManageFolderPopUp.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "fundraising";
			} else {
				workspaceSelector = "investor";
			}
			xpath = "//span[contains(@onclick,'closeRenamepop1"+workspaceSelector+"()')]";
		}
		return isDisplayed(driver, FindElement(driver, xpath, "Parent Rename Folder Cross Icon", action.BOOLEAN, timeOut), "Visibility", timeOut, "Parent Rename Folder Cross Icon");
	}
	
	public WebElement getParentRenameFolderCancelButton(Workspace workspace, PageName pageName, int timeOut){
		String workspaceSelector = "";
		String xpath = "";
		if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())){
			//needs to be write
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "FR";
			} else {
				workspaceSelector = "INV";
			}
			xpath = "//a[contains(@onclick,'clearTextBW"+workspaceSelector+"(')  and contains(@onclick,'rename')  and contains(@title,'Cancel') ]";
		} else if (pageName.toString().equalsIgnoreCase(PageName.ManageFolderPopUp.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "fundraising";
			} else {
				workspaceSelector = "investor";
			}
			xpath = "//a[contains(@onclick,'closeRenamepop1"+workspaceSelector+"();')]";
		}
		return isDisplayed(driver, FindElement(driver, xpath, "Parent Rename Folder cancel button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Parent Rename Folder Cancel button");
	}
	
	
	public WebElement getFolderErrorMsg(Workspace workspace,PageName pageName,ErrorMessageType ErrorMessageType,FolderType Foldertype,int timeOut) {
		String workspaceSelector = "";
		String xpath = "";
		if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())){
			// code need to be write for fund page
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				if(Foldertype.toString().equalsIgnoreCase(FolderType.Common.toString())) {
					xpath = "//div[@id='errMsgSecondCommonFolderBWFR']";
				}else {
					xpath = "//div[@id='errMsgSecondInternalFolderBWFR']";
				}
					
			} else {
				if(Foldertype.toString().equalsIgnoreCase(FolderType.Common.toString())) {
					xpath = "//div[@id='errMsgSecondCommonFolderBWINV']";
				}else {
					xpath = "//div[@id='errMsgSecondInternalFolderBWINV']";
				}
			}
		} else if (pageName.toString().equalsIgnoreCase(PageName.ManageFolderPopUp.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				if(Foldertype.toString().equalsIgnoreCase(FolderType.Common.toString())) {
					workspaceSelector = "1fundraising";
				}else {
					workspaceSelector = "2fundraising";
				}
					
			} else {
				if(Foldertype.toString().equalsIgnoreCase(FolderType.Common.toString())) {
					workspaceSelector = "1investor";
				}else {
					workspaceSelector = "2investor";
				}
			}
			xpath="//div[@id='CommonError"+workspaceSelector+"']//p";
		}
		return isDisplayed(driver, FindElement(driver, xpath, "Folder Error Message", action.BOOLEAN, timeOut), "Visibility", timeOut, "Folder Error Message");
	}
	
	public WebElement getFolderCreationErrorMessageCrossIcon(Workspace workspace,PageName pageName,FolderType Foldertype,int timeOut) {
		String workspaceSelector = "";
		String xpath = "";
		if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				if(Foldertype.toString().equalsIgnoreCase(FolderType.Common.toString())) {
					xpath = "//div[@id='errMsgSecondCommonFolderBWFR']/../preceding-sibling::div[1]/a";
				}else {
					xpath = "//div[@id='errMsgSecondInternalFolderBWFR']/../preceding-sibling::div[1]/a";
				}
			} else {
				if(Foldertype.toString().equalsIgnoreCase(FolderType.Common.toString())) {
					xpath = "//div[@id='errMsgSecondCommonFolderBWINV']/../preceding-sibling::div[1]/a";
				}else {
					xpath = "//div[@id='errMsgSecondInternalFolderBWINV']/../preceding-sibling::div[1]/a";
				}
			}
		} else if (pageName.toString().equalsIgnoreCase(PageName.ManageFolderPopUp.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				if(Foldertype.toString().equalsIgnoreCase(FolderType.Common.toString())) {
					workspaceSelector = "1fundraising";
				}else {
					workspaceSelector = "2fundraising";
				}
					
			} else {
				if(Foldertype.toString().equalsIgnoreCase(FolderType.Common.toString())) {
					workspaceSelector = "1investor";
				}else {
					workspaceSelector = "2investor";
				}
			}
			xpath="(//div[@id='CommonError"+workspaceSelector+"']//a)[1]";
		}
		return isDisplayed(driver, FindElement(driver, xpath, "folder creation error message cross icon on parent level", action.BOOLEAN, timeOut), "Visibility", timeOut, "folder creation error message cross icon on parent level");
	}
	
	public WebElement getFolderCreationErrorMessageOkBtn(Workspace workspace,PageName pageName,FolderType Foldertype,int timeOut) {
		String workspaceSelector = "";
		String xpath = "";
		if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())){
			// code need to be write for fund page
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				if(Foldertype.toString().equalsIgnoreCase(FolderType.Common.toString())) {
					xpath = "//div[@id='errMsgSecondCommonFolderBWFR']/../following-sibling::div[1]/a";
				}else {
					xpath = "//div[@id='errMsgSecondInternalFolderBWFR']/../following-sibling::div[1]/a";
				}
			} else {
				if(Foldertype.toString().equalsIgnoreCase(FolderType.Common.toString())) {
					xpath = "//div[@id='errMsgSecondCommonFolderBWINV']/../following-sibling::div[1]/a";
				}else {
					xpath = "//div[@id='errMsgSecondInternalFolderBWINV']/../following-sibling::div[1]/a";
				}
			}
		} else if (pageName.toString().equalsIgnoreCase(PageName.ManageFolderPopUp.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				if(Foldertype.toString().equalsIgnoreCase(FolderType.Common.toString())) {
					workspaceSelector = "1fundraising";
				}else {
					workspaceSelector = "2fundraising";
				}
					
			} else {
				if(Foldertype.toString().equalsIgnoreCase(FolderType.Common.toString())) {
					workspaceSelector = "1investor";
				}else {
					workspaceSelector = "2investor";
				}
			}
			xpath="(//div[@id='CommonError"+workspaceSelector+"']//a)[2]";
		}
		return isDisplayed(driver, FindElement(driver, xpath, "folder creation error message OK button on parent level", action.BOOLEAN, timeOut), "Visibility", timeOut, "folder creation error message Ok button on parent level");
	}
	
	public WebElement getFolderDeleteYesBtn(Workspace workspace, PageName pageName, int timeOut) {
		String workspaceSelector="";
		if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "FR";
			} else {
				workspaceSelector = "INV";
			}
			return isDisplayed(driver, FindElement(driver, "//a[@onclick='deleteFolderBW"+ workspaceSelector +"();']", "delete yes button", action.SCROLLANDBOOLEAN, timeOut),"visibility", timeOut,"delete yes button");
		} else {
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
				workspaceSelector="fundraising";
			}else {
				workspaceSelector="investor";
			}
			return isDisplayed(driver, FindElement(driver, "//div[@id='deletefolderpopup1"+workspaceSelector+"']//a[text()='Yes']", "delete yes button", action.SCROLLANDBOOLEAN, timeOut),"visibility", timeOut,"delete yes button");
		}
	}
	
	public WebElement getFolderDeleteNoBtn(Workspace workspace, PageName pageName, int timeOut) {
		String workspaceSelector="";
		if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
				workspaceSelector="FR";
			}else {
				workspaceSelector="INV";
			}
			return isDisplayed(driver, FindElement(driver, "//a[contains(@onclick,'idConfirmDeletionBW"+ workspaceSelector +"')]", "folder delete No button", action.SCROLLANDBOOLEAN, timeOut),"visibility", timeOut,"folder delete No button");
		} else {
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
				workspaceSelector="fundraising";
			}else {
				workspaceSelector="investor";
			}
			return isDisplayed(driver, FindElement(driver, "//a[contains(@onclick,'closeaddpop1"+workspaceSelector+"();')]", "folder delete No button", action.SCROLLANDBOOLEAN, timeOut),"visibility", timeOut,"folder delete No button");
		}
	}
	
	public WebElement getFolderDeleteCrossIcon(Workspace workspace, PageName pageName, int timeOut) {
		String workspaceSelector="";
		if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
				workspaceSelector="FR";
			}else {
				workspaceSelector="INV";
			}
			return isDisplayed(driver, FindElement(driver, "//span[contains(@onclick,'idConfirmDeletionBW"+ workspaceSelector +"')]", "folder delete Cross Icon", action.SCROLLANDBOOLEAN, timeOut),"visibility", timeOut,"folder delete Cross Icon");
		} else {
			
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
				workspaceSelector="fundraising";
			}else {
				workspaceSelector="investor";
			}
			return isDisplayed(driver, FindElement(driver, "//a[contains(@onclick,'closeaddpop1"+workspaceSelector+"();')]", "folder delete Cross Icon", action.SCROLLANDBOOLEAN, timeOut),"visibility", timeOut,"folder delete Cross Icon");
		}
	}
	
	public WebElement getFolderDeleteErrorMsg1(Workspace workspace, PageName pageName, int timeOut) {
		String workspaceSelector="";
		if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
				workspaceSelector="FR";
			}else {
				workspaceSelector="INV";
			}
			return isDisplayed(driver, FindElement(driver, "//span[contains(@onclick,'idConfirmDeletionBW"+ workspaceSelector +"')]/../following-sibling::p", "folder delete Error Message1", action.SCROLLANDBOOLEAN, timeOut),"visibility", timeOut,"folder delete Error Message1");
		} else {
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
				workspaceSelector="fundraising";
			}else {
				workspaceSelector="investor";
			}
			return isDisplayed(driver, FindElement(driver, "//div[@id='deletefolderpopup1"+workspaceSelector+"content']/p[1]", "folder delete Error Message1", action.SCROLLANDBOOLEAN, timeOut),"visibility", timeOut,"folder delete Error Message1");
		}
	}
	

	public WebElement getFolderDeleteErrorMsg2(Workspace workspace,int timeOut) {
		String workspaceSelector="";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
			workspaceSelector="fundraising";
		}else {
			workspaceSelector="investor";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='deletefolderpopup1"+workspaceSelector+"content']/p[2]", "folder delete Error Message2", action.SCROLLANDBOOLEAN, timeOut),"visibility", timeOut,"folder delete Error Message2");
	}
	
	 @FindBy(xpath="//iframe[contains(@title,'PE_Fund_NothingEnabled')]")
	 private WebElement fundPageFrame;
	 
	 @FindBy(xpath="//iframe[@title='Investor_Portal_Institution_Enabled']")
	 private WebElement institutionPageFrame;
	 
	 @FindBy(xpath="//iframe[@title='Investor_Portal_Contact_Enabled']")
	 private WebElement contactPageFrame;
	
	 @FindBy(xpath="//iframe[contains(@title,'Investor_Portal_Commitment_Enabled')]")
	 private WebElement commitmentPageFrame;
	 
//	 @FindBy(xpath="//iframe[@id='PEDisclaimersSetup']")
//	 private WebElement navatarInvestorAddOnFrame;

	 @FindBy(xpath="//iframe[@title='InvestorPortal_Alerts']")
	 private  WebElement homePageAlertsFrame;


	 @FindBy(xpath="//iframe[@id='theIframe']")
	 private WebElement NIMTabFrame;

	 @FindBy(xpath="//iframe[@id='NavatarInvestorAddOns']")
	private WebElement navatarInvestorAddOnFrame;
	 
	 @FindBy(xpath="//iframe[@title='Navatar Investor Add-ons']")
	 private WebElement navatarInvestorAddOnParentFrame;
	 /**
	  * @return the navatarInvestorAddOnFrame
	  */
	 public WebElement getNavatarInvestorAddOnParentFrame(int timeOut) {
	 	return isDisplayed(driver, navatarInvestorAddOnParentFrame, "Visibility", timeOut, "Navatar Investor Add On Parent Frame");
	 }
	 
	 /**
	  * @author Ankit Jaiswal
	  * @param pageName
	  * @param timeOut
	  * @return webelement/null
	  */
	 public WebElement getFrame(PageName pageName,int timeOut) {
		 WebElement ele=null;
		 if(pageName.toString().equalsIgnoreCase(PageName.InstitutionsPage.toString())) {
			 switchToDefaultContent(driver);
			 switchToFrame(driver, 20, getNIMTabParentFrame_Lightning(PageName.InstitutionsPage));
			 ele=isDisplayed(driver, institutionPageFrame, "Visibility", timeOut, pageName+" frame");
		 }else if (pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {
			 switchToDefaultContent(driver);
			 switchToFrame(driver, 20, getNIMTabParentFrame_Lightning(PageName.ContactsPage));
			 ele=isDisplayed(driver, contactPageFrame, "Visibility", timeOut, pageName+" frame");
		 }else if (pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())) {
			 switchToDefaultContent(driver);
			 switchToFrame(driver, 20, getNIMTabParentFrame_Lightning(PageName.FundsPage));
			 ele=isDisplayed(driver, fundPageFrame, "Visibility", timeOut, pageName+" frame");
		 }else if (pageName.toString().equalsIgnoreCase(PageName.CommitmentsPage.toString())) {
			 switchToDefaultContent(driver);
			 switchToFrame(driver, 20, getNIMTabParentFrame_Lightning(PageName.CommitmentsPage));
			 ele=isDisplayed(driver, commitmentPageFrame, "Visibility", timeOut, pageName+" frame");
		 }else if(pageName.toString().equalsIgnoreCase(PageName.HomePage.toString())){
			 switchToDefaultContent(driver);
			 //  switchToFrame(driver, 20, getNIMTabParentFrame_Lightning(PageName.InstitutionsPage));
			 //  ele=isDisplayed(driver, homePageAlertsFrame, "Visibility", timeOut, pageName+" frame");
			 ele=isDisplayed(driver, getNIMTabParentFrame_Lightning(PageName.HomePage), "Visibility", timeOut, pageName+" frame");
		 }else if(pageName.toString().equalsIgnoreCase(PageName.NavatarInvestorAddOnsPage.toString())){
			 switchToDefaultContent(driver);
			 switchToFrame(driver,10,getNavatarInvestorAddOnParentFrame(20));
			 ele=isDisplayed(driver, navatarInvestorAddOnFrame, "Visibility", timeOut, pageName+" frame");
		 } else if (pageName.toString().equalsIgnoreCase(PageName.NavatarInvestorManager.toString())){

			 ele=isDisplayed(driver, NIMTabFrame, "Visibility", timeOut, "NIM Frame");
		 }
		 return ele;
	 }
	 
	 //Lightning Method..........
	 
	 /**
	  * @author Ankit Jaiswal
	  * @param pageName
	  * @param timeOut
	  * @return webelement/null
	  */
	 public WebElement getFrame(String environment, String mode,PageName pageName,int timeOut) {
		 WebElement ele=null;
		 if(pageName.toString().equalsIgnoreCase(PageName.InstitutionsPage.toString())) {
			 if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				 ele=getNIMTabParentFrame_Lightning(pageName);
			 }else {
				 ele=isDisplayed(driver, institutionPageFrame, "Visibility", timeOut, pageName+" frame");
			 }
		 }else if (pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {
			 if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				 ele=getNIMTabParentFrame_Lightning(pageName);
			 }else {
				 ele=isDisplayed(driver, contactPageFrame, "Visibility", timeOut, pageName+" frame");
			 }
		 }else if (pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())) {
			 if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				 ele=getNIMTabParentFrame_Lightning(pageName);
			 }else {
				 ele=isDisplayed(driver, fundPageFrame, "Visibility", timeOut, pageName+" frame");
			 }
		 }else if (pageName.toString().equalsIgnoreCase(PageName.CommitmentsPage.toString())) {
			 if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				 ele=getNIMTabParentFrame_Lightning(pageName);
			 }else {
				 ele=isDisplayed(driver, commitmentPageFrame, "Visibility", timeOut, pageName+" frame");
			 }
		 }else if(pageName.toString().equalsIgnoreCase(PageName.HomePage.toString())){
			 if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				 ele=getNIMTabParentFrame_Lightning(pageName);
			 }else {
				 ele=isDisplayed(driver, homePageAlertsFrame, "Visibility", timeOut, pageName+" frame");
			 }
		 }else if(pageName.toString().equalsIgnoreCase(PageName.NavatarInvestorAddOnsPage.toString())){
			 ele=isDisplayed(driver, navatarInvestorAddOnFrame, "Visibility", timeOut, pageName+" frame");
		 } else if (pageName.toString().equalsIgnoreCase(PageName.NavatarInvestorManager.toString())){
			 ele=isDisplayed(driver, NIMTabFrame, "Visibility", timeOut, "NIM Frame");
		 }else if (pageName.toString().equalsIgnoreCase(PageName.LimitedPartnerPage.toString())) {
			 if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				 ele=getNIMTabParentFrame_Lightning(pageName);
			 }else {
				 ele=isDisplayed(driver, institutionPageFrame, "Visibility", timeOut, pageName+" frame");
			 }
		 }
		 return ele; 
	 }
	 
	 @FindBy(xpath="//div[@class='pbHeader']//input[@title='Edit']")
	 private WebElement editButton;
	
	 /**
	 * @return the editButton
	 */
	public WebElement getEditButton(int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, editButton_Classic, "Visibility", timeOut, "Edit Button Classic");	
		}else{
			return isDisplayed(driver, editButton_Lighting, "Visibility", timeOut, "Edit Button Lighting");	

		}
	}
	
	/**
	 * @param editButton the editButton to set
	 */
	public void setEditButton(WebElement editButton) {
		this.editButton = editButton;
	}

	/*****************************************************Investor registration*************************/
	 
	 
	@FindBy(xpath = "//label[contains(text(),'First Name')]")
	private WebElement firstNameLabel;
	
	
	 /**
	 * @return the firstNameLabel
	 */
	public WebElement getFirstNameLabel(int timeOut) {
		return isDisplayed(driver, firstNameLabel, "Visibility", timeOut, "first name text on investor registration page");
	}
	
	@FindBy(xpath = "//label[contains(text(),'Last Name')]")
	private WebElement lastNameLabel;
	
	
	/**
	 * @return the lastNameLabel
	 */
	public WebElement getLastNameLabel(int timeOut) {
		return isDisplayed(driver, lastNameLabel, "Visibility", timeOut, "last name text on investor registration page");
	}
	
	@FindBy(xpath = "//label[contains(text(),'Nick Name')]")
	private WebElement nickNameLabel;
	
	
	/**
	 * @return the nickNameLabel
	 */
	public WebElement getNickNameLabel(int timeOut) {
		return isDisplayed(driver, nickNameLabel, "Visibility", timeOut, "nick name label text on investor registratin page");
	}

	@FindBy(xpath = "//label[contains(text(),'E-mail (Username)')]")
	private WebElement emailLabel;
	
	
	/**
	 * @return the emailLabel
	 */
	public WebElement getEmailLabel(int timeOut) {
		return isDisplayed(driver, emailLabel, "Visibility", timeOut, "email text text on investor registration page");
	}
	@FindBy(xpath = "//label[contains(text(),'Firm Name')]")
	private WebElement firmNameLabel;
	
	
	/**
	 * @return the firmNameLabel
	 */
	public WebElement getFirmNameLabel(int timeOut) {
		return isDisplayed(driver, firmNameLabel, "Visibility", timeOut, "firm name text on investor registration page");
	}
	@FindBy(xpath = "(//label[contains(text(),'Password')])[1]")
	private WebElement passwordLabel;
	
	
	/**
	 * @return the passwordLabel
	 */
	public WebElement getPasswordLabel(int timeOut) {
		return isDisplayed(driver, passwordLabel, "Visibility", timeOut, "password text on investor registration page");
	}
	@FindBy(xpath = "//label[contains(text(),'Confirm Password')]")
	private WebElement confirmPasswordLabel;
	
	
	/**
	 * @return the confirmPasswordLabel
	 */
	public WebElement getConfirmPasswordLabel(int timeOut) {
		return isDisplayed(driver, confirmPasswordLabel, "Visibility", timeOut, "confirm password text on investor registration page");
	}
	@FindBy(xpath = "//span[@id='passValidtn']//i")
	private WebElement eightCharactersInfoLabel;
	
	
	/**
	 * @return the eightCharactersInfoLabel
	 */
	public WebElement getEightCharactersInfoLabel(int timeOut) {
		return isDisplayed(driver, eightCharactersInfoLabel, "Visibility", timeOut, "use at least 8 characters information on investor registration page");
	}

	@FindBy(xpath="//input[@name='User_FirstLogin:Form:FirstName']")
	 private WebElement targetFirstName;

	/**
	 * @return the targetFirstName
	 */
	public WebElement getTargetFirstName(int timeOut) {
		return isDisplayed(driver, targetFirstName, "Visibility", timeOut, "target first name");
	}
	
	@FindBy(xpath="//input[@name='User_FirstLogin:Form:LastName']")
	private WebElement targetlastName;

	/**
	 * @return the targetlastName
	 */
	public WebElement getTargetlastName(int timeOut) {
		return isDisplayed(driver, targetlastName, "Visibility", timeOut, "target last name");
	}
	
	@FindBy(xpath="//input[@name='User_FirstLogin:Form:communityNickname']")
	private WebElement targetNickName;

	/**
	 * @return the targetNickName
	 */
	public WebElement getTargetNickName(int timeOut) {
		return isDisplayed(driver, targetNickName, "Visibility", timeOut, "target nick name");
	}
	
	@FindBy(xpath="//input[@name='User_FirstLogin:Form:UserName_Email']")
	private WebElement targetEmailId;
	
	
	/**
	 * @return the targetEmailId
	 */
	public WebElement getTargetEmailId(int timeOut) {
		return isDisplayed(driver, targetEmailId, "Visibility", timeOut, "target email id");
	}

	@FindBy(xpath="//input[@name='User_FirstLogin:Form:investorfirm']")
	private WebElement targetFirmName;

	/**
	 * @return the targetFirmName
	 */
	public WebElement getTargetFirmName(int timeOut) {
		return isDisplayed(driver, targetFirmName, "Visibility", timeOut, "target firm name");
	}
	
	@FindBy(xpath="//input[@name='User_FirstLogin:Form:pwd']")
	private WebElement targetpassword;

	/**
	 * @return the targetpassword
	 */
	public WebElement getTargetpassword(int timeOut) {
		return isDisplayed(driver, targetpassword, "Visibility", timeOut, "target password");
	}
	
	@FindBy(xpath="//input[@name='User_FirstLogin:Form:cpwd']")
	private WebElement targetConfirmPassword;

	/**
	 * @return the targetConfirmPassword
	 */
	public WebElement getTargetConfirmPassword(int timeOut) {
		return isDisplayed(driver, targetConfirmPassword, "Visibility", timeOut, "target confirm password");
	}
	
	@FindBy(xpath="//a[@class='GlobalButton']/span")
	private WebElement targetSignUpBtn;

	/**
	 * @return the targetSignUpBtn
	 */
	public WebElement getTargetSignUpBtn(int timeOut) {
		return isDisplayed(driver, targetSignUpBtn, "Visibility", timeOut, "sign button");
	}
	
	@FindBy(xpath="//a[@class='GlobalButtonCancel']/span")
	private WebElement targetcancelBtn;

	/**
	 * @return the targetcancelBtn
	 */
	public WebElement getTargetcancelBtn(int timeOut) {
		return isDisplayed(driver, targetcancelBtn, "Visibility", timeOut, "cancel button");
	}
	
	@FindBy(xpath = "//a[@title='Logout']")
	private WebElement investorLogout;

	/**
	 * @return the logout
	 */
	public WebElement getInvestorLogout() {
		return isDisplayed(driver, investorLogout, "Visibility", 30, "Investor logout button");
	}
	@FindBy(xpath = "//img[contains(@src,'navatar')]")
	private WebElement navatarImageInvRegistration;

	/**
	 * @return the navatarImage
	 */
	public WebElement getnavatarImageInvRegistration(int timeOut) {
		return isDisplayed(driver, navatarImageInvRegistration, "Visibility", timeOut, "Navatar image on investor registration page");
	}
	
	@FindBy(xpath="//div[@id='nodata']//h1")
	private WebElement errorMessageBeforeGivingInternalUserAccess;

	/**
	 * @return the errorMessageBeforeGivingInternalUserAccess
	 */
	public WebElement getErrorMessageBeforeGivingInternalUserAccess(int timeOut) {
		return isDisplayed(driver, errorMessageBeforeGivingInternalUserAccess, "Visibility", timeOut, "Error Message Before Giving Internal User Access");
	}
	@FindBy(xpath = "//a[@title='click here']")
	private WebElement signInLink;

	@FindBy(xpath="//span[text()='Enabled Visualforce Page Access']")
	private WebElement enableVisualForcePageAccessLink;

	/**
	 * @return the enableVisualForcePageAccessLink
	 */
	public WebElement getEnableVisualForcePageAccessLink(int timeOut) {
		return isDisplayed(driver, enableVisualForcePageAccessLink, "Visibility", timeOut, "Enable VisiualForce Page Access Link");
	}
	
	@FindBy(xpath="//h3[text()='Enabled Visualforce Page Access']/../following-sibling::td//input")
	private WebElement enableVisualForcePageAccessEditButton;

	/**
	 * @return the enableVisualForcePageAccessEditButton
	 */
	public WebElement getEnableVisualForcePageAccessEditButton(int timeOut) {
		return isDisplayed(driver, enableVisualForcePageAccessEditButton, "Visibility", timeOut, "Edit Button");
	}
		
	/**
	 * @return the signInButton
	 */
	public WebElement getSignInLink(int timeOut) {
		return isDisplayed(driver, signInLink, "Visibility", timeOut, "Sign in button on investor registration page");
	}
	
	@FindBy(xpath="//a[contains(@title,'Last Login')]")
	private WebElement lastLogin;

	/**
	 * @return the lastLogin
	 */
	public WebElement getLastLogin(int timeOut) {
		return isDisplayed(driver, lastLogin, "Visibility", timeOut, "Last Login");
	}
	
	@FindBy(xpath="//select[@id='fcf']")
	private WebElement viewAllDropdownList;

	
	
	/**
	 * @return the viewAllDropdownList
	 */
	public WebElement getViewAllDropdownList(int timeOut) {
		return isDisplayed(driver, viewAllDropdownList, "Visibility", timeOut, "View All Dropdown List");
	}
	
	@FindBy(xpath="//input[@id='active']")
	private WebElement activeCheckboxInUserCreation;

	/**
	 * @return the activeCheckboxINUSerCreation
	 */
	public WebElement getActiveCheckboxInUserCreation(int timeOut) {
		return isDisplayed(driver, activeCheckboxInUserCreation, "Visibility", timeOut, "Active Checkbox In User Creation Page");
	}
	
	@FindBy(xpath="//select[@id='duel_select_0']")
	private WebElement VFPageMultiSelect;

	/**
	 * @return the vFPageMultiSelect
	 */
	public WebElement getVFPageMultiSelect(int timeOut) {
		return isDisplayed(driver, VFPageMultiSelect, "Visibility", timeOut, "VF Page Multi Select");
	}
	
	@FindBy(xpath="//img[@class='rightArrowIcon']")
	private WebElement multiSelectAddButton;

	/**
	 * @return the multiSelectAddButton
	 */
	public WebElement getMultiSelectAddButton(int timeOut) {
		return isDisplayed(driver, multiSelectAddButton, "Visibility", timeOut, "Multi Select Add Button");
	}
	
	@FindBy(xpath="//div[@id='pendingPopup']/div[@class='head_popup']")
	private WebElement pendingDisclaimerPopUpHeader;
	
	@FindBy(xpath="//td[@id='topButtonRow']//input[@title='Delete']")
	private WebElement deleteButton;

	/**
	 * @return the deleteButton
	 */
	public WebElement getDeleteButton(int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, deleteButton, "Visibility", timeOut, "Delete Button");
		}else{
			clickOnShowMoreDropdownOnly();
			log(LogStatus.INFO,"Able to Click on Show more Icon : ",YesNo.No);
			ThreadSleep(500);
			WebElement ele = actionDropdownElement(ShowMoreActionDropDownList.Delete, 15);

			click(driver, getDeleteButton1(30), "Delete button ", action.BOOLEAN);
			return getDeleteButtonOnDeletePopUp(timeOut);
		}


	}

	@FindBy(xpath="//*[@title='Delete' or text()='Delete']")
	private WebElement deleteButton1;
	
	/**
	 * @return the addFilterLogicLink
	 */
	public WebElement getDeleteButton1(int timeOut) {
		return isDisplayed(driver, deleteButton1, "Visibility", timeOut, "Delete Button");
	}
	/**
	 * @return the pendingDisclaimerPopUpHeader
	 */
	public WebElement getPendingDisclaimerPopUpHeader(int timeOut) {
		return isDisplayed(driver, pendingDisclaimerPopUpHeader, "Visibility", timeOut, "Pending disclaimer Pop Header");
	}

	@FindBy(xpath="//div[@id='pendingPopup']/div[@class='formbox']")
	private WebElement pendingDisclaimerPopUpMessage;

	/**
	 * @return the pendingDisclaimerPopUpMessage
	 */
	public WebElement getPendingDisclaimerPopUpMessage(int timeOut) {
		return isDisplayed(driver, pendingDisclaimerPopUpMessage, "Visibility", timeOut, "Pending disclaimer pop up message");
	}
	
	@FindBy(xpath="//div[@id='pendingPopup']/div[@class='paginationstyle']/a[@title='Go to Disclaimers']")
	private WebElement pendingDisclaimerPopGoToDisclaimerButton;

	/**
	 * @return the pendingDisclaimerPopGoToDisclaimerButton
	 */
	public WebElement getPendingDisclaimerPopGoToDisclaimerButton(int timeOut) {
		return isDisplayed(driver, pendingDisclaimerPopGoToDisclaimerButton, "Visibility", timeOut, "Goto Disclaimer Button");
	}
	
	@FindBy(xpath="//div[@id='pendingPopup']/div[@class='paginationstyle']/a[@title='Cancel']")
	private WebElement pendingDisclaimerPopUpCancelBUtton;

	/**
	 * @return the pendingDisclaimerPopUpCancelBUtton
	 */
	public WebElement getPendingDisclaimerPopUpCancelBUtton(int timeOut) {
		return isDisplayed(driver, pendingDisclaimerPopUpCancelBUtton, "Visibility", timeOut, "Pending disclaimer Pop Up Cancel Button");
	}
	
	@FindBy(xpath="//div[@id='pendingPopup']/div[@class='head_popup']/a")
	private WebElement pendingDisclaimerPopUpCrossIcon;

	/**
	 * @return the pendingDisclaimerPopUpCrossIcon
	 */
	public WebElement getPendingDisclaimerPopUpCrossIcon(int timeOut) {
		return isDisplayed(driver, pendingDisclaimerPopUpCrossIcon, "Visibility", timeOut, "");
	}
	
	
	@FindBy(xpath = "//select[contains(@id,'compHeadID:headerCompID:selectValue')]")
	private WebElement firmNameDropdown;

	/**
	 * @return the firmNameDropdown
	 */
	public WebElement getFirmNameDropdown(int timeOut) {
		return isDisplayed(driver, firmNameDropdown, "Visibility", timeOut, "");
	}
	public WebElement getFirmNameDropdownWRTPage(PageName pagename) {
		if (pagename == PageName.InvestorFirmPage) {
		return isDisplayed(driver, FindElement(driver, "//select[@id='pg:frm:compHeadID:headerCompID:selectValue']", "dropdown for investor firm page", action.BOOLEAN, 30), "visibility", 10,"dropdown for investor firm page");
		}
		else {
			return firmNameDropdown;
		}
	}
	@FindBy(xpath = "//a[@title='Profile']")
	private WebElement profileLink;

	/**
	 * @return the profileLink
	 */
	public WebElement getProfileLink(int timeOut) {
		return isDisplayed(driver, profileLink, "Visibility", timeOut, "Profile link on top right");
	}
	
	@FindBy(xpath="//div[@class='head_popup head_popupDisclaimer']")
	private WebElement accessDeniedPopUpHeader;

	/**
	 * @return the accessDeniedPopUpHeader
	 */
	public WebElement getAccessDeniedPopUpHeader(int timeOut) {
		return isDisplayed(driver, accessDeniedPopUpHeader, "Visibility", timeOut, "Access Denied Pop Up Header");
	}
	
	@FindBy(xpath="//div[@class='formbox formboxDisclaimer']")
	private WebElement accessDeniedPopUpMessage;

	/**
	 * @return the accessDeniedPopUpMessage
	 */
	public WebElement getAccessDeniedPopUpMessage(int timeOut) {
		return isDisplayed(driver, accessDeniedPopUpMessage, "Visibility", timeOut, "Access denied Pop Up message");
	}
	
	@FindBy(xpath="//div[@class='paginationstyle']/a[@title='Go to Disclaimers']")
	private WebElement accessDeniedPopUpGoToDisclaimerButton;

	/**
	 * @return the accessDeniedGoToDisclaimerButton
	 */
	public WebElement getAccessDeniedPopUpGoToDisclaimerButton(int timeOut) {
		return isDisplayed(driver, accessDeniedPopUpGoToDisclaimerButton, "Visibility", timeOut, "Access denied pop up Go to Disclaimer button");
	}
	
	@FindBy(xpath="//a[@id='downloadLink']")
	private WebElement downloadLink;

	/**
	 * @return the downloadLink
	 */
	public WebElement getDownloadLink(int timeOut) {
		return isDisplayed(driver, downloadLink, "Visibility", timeOut, "Download Button");
	}
	@FindBy(xpath = "//a[@title='Close']")
	private WebElement viewFileOnFileDistClose;
	
	
	/**
	 * @return the viewFileOnFileDistClose
	 */
	public WebElement getViewFileOnFileDistClose(int timeOut) {
		return isDisplayed(driver, viewFileOnFileDistClose, "Visibility", timeOut, "");
	}

	@FindBy(xpath = "//a[@title='Download']")
	private WebElement viewFileOnfileDistDownload;
	
	
	
	/**
	 * @return the fileDistDownload
	 */
	public WebElement getViewFileOnfileDistDownload(int timeOut) {
		return isDisplayed(driver, viewFileOnfileDistDownload, "Visibility", timeOut, "File Dist Download");
	}

	@FindBy(xpath="//a[@class='dynatree-title'][text()='All Folders']")
	private WebElement allFoldersOnBulkDownloadWindow;

	/**
	 * @return the allFoldersOnBulkDownloadWindow
	 */
	public WebElement getAllFoldersOnBulkDownloadWindow(int timeOut) {
		return isDisplayed(driver, allFoldersOnBulkDownloadWindow, "Visibility", timeOut, "All folders");
	}
	

	@FindBy(css="#closelink")
	private WebElement documentCloseBtn;


	/**
	 * @return the doucmentCloseBtn
	 */
	public WebElement getDocumentCloseBtn(int timeOut) {
		return isDisplayed(driver, documentCloseBtn, "Visibility", timeOut, "Document Close Button");
	}
	
	
	
	@FindBy(xpath ="//span[@id='myGridfundr-scroll-box']")
	private WebElement scrollBoxforFundPage;

	/**
	 * @return the scrollBoxforFundPage
	 */
	public WebElement getScrollBoxforFundPage(int timeOut) {
		return isDisplayed(driver, scrollBoxforFundPage, "Visibility", timeOut,
				"Scroll Box");
	}
	

	public WebElement getWorkspaceSectionView(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "Fundraising";
		} else {
			workspaceSelector = "Investor";
		}
		return isDisplayed(driver, FindElement(driver, "//h3[text()='"+workspaceSelector+" Workspace']", workspace+" view section", action.BOOLEAN, 30), "visibility", 60, workspace+" view section");
	}
	
	public WebElement getAddFolderParentFolderCancelButton(Workspace workspace, PageName pageName, int timeOut){
		String workspaceSelector = "";
		
		if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "FR";
			} else {
				workspaceSelector = "INV";
			}
			return isDisplayed(driver, FindElement(driver, "//a[contains(@onclick,'clearTextBW"+ workspaceSelector +"')][contains(@onclick,''none'')][contains(@onclick,';')]", "Parent Folder cancel Button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Parent Folder cancel Button");
		} else {
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "fundraising";
			} else {
				workspaceSelector = "investor";
			}
			return isDisplayed(driver, FindElement(driver, "//a[contains(@onclick,'CreateCommon_pop1"+workspaceSelector+"();')]/following-sibling::a", "Parent Folder cancel Button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Parent Folder cancel Button");
		}
	}
	
	public WebElement getAddFolderChildFolderCancelButton(Workspace workspace, PageName pageName, int timeOut){
		String workspaceSelector = "";
		
		if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())){
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "FR";
			} else {
				workspaceSelector = "INV";
			}
			return isDisplayed(driver, FindElement(driver, "//a[contains(@onclick,'clearTextBW"+ workspaceSelector +"')][contains(@onclick,''none'')][contains(@onclick,';')]", "child Folder cancel Button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Parent Folder cancel Button");
		}else if(pageName.toString().equalsIgnoreCase(PageName.ManageFolderPopUp.toString())) {
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "FR";
			} else {
				workspaceSelector = "INV";
			}
			return isDisplayed(driver, FindElement(driver, "//div[@id='subFolderHeading']/..//a[contains(@onclick,'clearTextBW"+workspaceSelector+"') and @title='Cancel']", "child Folder cancel Button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Parent Folder cancel Button");
		} else {
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "fundraising";
			} else {
				workspaceSelector = "investor";
			}
			return isDisplayed(driver, FindElement(driver, "//a[contains(@onclick,'CreateCommon_pop1"+workspaceSelector+"();')]/following-sibling::a", "child Folder cancel Button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Parent Folder cancel Button");
		}
	}
	
	
	
	
	@FindBy(xpath="//div[@class='tip-inner tip-bg-image']/span")
	public WebElement addFolderInfoIconMessage;

	/**
	 * @return the addFolderInfoIconMessage
	 */
	public WebElement getAddFolderInfoIconMessage(int timeOut) {
		return isDisplayed(driver, addFolderInfoIconMessage, "Visibility", timeOut, "Add folder info icon message;");
	}
	
	@FindBy(xpath="//a[@id='Customize_icon']//img")
	private WebElement customizeIcon;

	/**
	 * @return the customizeIcon
	 */
	public WebElement getCustomizeIcon(int timeOut) {
		return isDisplayed(driver, customizeIcon, "Visibility", timeOut, "Customize Icon");
	}
	
	@FindBy(xpath="//a[@id='Account_icon']//img")
	private WebElement accountsIconUnderCustomizeIcon;

	/**
	 * @return the accountsTabUnderCustomizeIcon
	 */
	public WebElement getAccountsIconUnderCustomizeIcon(int timeOut) {
		return isDisplayed(driver, accountsIconUnderCustomizeIcon, "Visibility", timeOut, "Accounts Icon under Customize Icon");
	}
	
	@FindBy(xpath="//a[@id='AccountFields_font']")
	private WebElement  fieldsLabelUnderAccountIcon;

	/**
	 * @return the fieldsLabelUnderAccountIcon
	 */
	public WebElement getFieldsLabelUnderAccountIcon(int timeOut) {
		return isDisplayed(driver, fieldsLabelUnderAccountIcon, "Visibility", timeOut, "Fields Label");
	}
	 
	@FindBy(xpath="//input[@name='new_field']")
	private WebElement newButtonInAccountsField;

	/**
	 * @return the newButtonInAccountsField
	 */
	public WebElement getNewButtonInAccountsField(int timeOut) {
		return isDisplayed(driver, newButtonInAccountsField, "Visibility", timeOut, "New Button");
	} 
	
	@FindBy(xpath="//div[@class='pbBottomButtons']//input[@title='Next']")
	private WebElement nextButton;

	/**
	 * @return the nextButton
	 */
	public WebElement getNextButton(int timeOut) {
		return isDisplayed(driver, nextButton, "Visibility", timeOut, "Next Button");
	} 
	
	@FindBy(xpath="//input[@id='MasterLabel']")
	private WebElement fieldLabelTextBox;

	/**
	 * @return the fieldLabelTextBox
	 */
	public WebElement getFieldLabelTextBox(int timeOut) {
		return isDisplayed(driver, fieldLabelTextBox, "Visibility", timeOut, "Field Label Text Box");
	}
	
	 @FindBy(xpath="//input[@id='startNum']")
	 private WebElement startingNumberFieldTextBox;

	/**
	 * @return the startingNumberFieldTextBox
	 */
	public WebElement getStartingNumberFieldTextBox(int timeOut) {
		return isDisplayed(driver, startingNumberFieldTextBox, "Visibility", timeOut, "Starting Number Field TextBox");
	}
	
	@FindBy(xpath="//input[@id='Scale']")
	private WebElement decimalPlacesTextbox;

	/**
	 * @return the decimalPlacesTextbox
	 */
	public WebElement getDecimalPlacesTextbox(int timeOut) {
		return isDisplayed(driver, decimalPlacesTextbox, "Visibility", timeOut, "Decimal Places Text box");
	}
	
	@FindBy(xpath="//div[@class='pbBottomButtons']//input[@name='save']")
	private WebElement saveButtonInCustomFields;

	/**
	 * @return the saveButtonInCustomFields
	 */
	public WebElement getSaveButtonInCustomFields(int timeOut) {
		return isDisplayed(driver, saveButtonInCustomFields, "Visibility", timeOut, "Save Button In custom Fields");
	}
	
	@FindBy(xpath="//a[@id='DevTools_icon']//img")
	private WebElement createIcon;

	/**
	 * @return the createIcon
	 */
	public WebElement getCreateIcon(int timeOut) {
		return isDisplayed(driver, createIcon, "Visibility", timeOut, "Create Icon");
	}
	
	@FindBy(xpath="//a[text()='Objects']")
	private WebElement objectsLabel;

	/**
	 * @return the objectsLabel
	 */
	public WebElement getObjectsLabel(int timeOut) {
		return isDisplayed(driver, objectsLabel, "Visibility", timeOut, "Objects Label");
	}
	
	@FindBy(xpath="//th[@class=' dataCell  ']//a[text()='Fundraising']")
	private WebElement fundraisingLabelInObject;

	/**
	 * @return the fundraisingLabelInObject
	 */
	public WebElement getFundraisingLabelInObject(int timeOut) {
		return isDisplayed(driver, fundraisingLabelInObject, "Visibility", timeOut, "FundRaisingLabel");
	}
	

	@FindBy(xpath="//textarea[@class='FormulaText']")
	private WebElement formulaTextBox;

	/**
	 * @return the folrulaTextBox
	 */
	public WebElement getFormulaTextBox(int timeOut) {
		return isDisplayed(driver, formulaTextBox, "Visibility", timeOut, "Formula TextBox");
	} 
	
	@FindBy(xpath="//input[@id='picklistTypeLOCAL_PICKLIST']")
	private WebElement multiPicklistValueRadioButton;

	/**
	 * @return the multiPicklistValueRadioButton
	 */
	public WebElement getMultiPicklistValueRadioButton(int timeOut) {
		return isDisplayed(driver, multiPicklistValueRadioButton, "Visibility", timeOut, "MultiPickListRadio Button");
	}
	
	@FindBy(xpath="//textarea[@id='ptext']")
	private WebElement valueTextBox;

	/**
	 * @return the valueTextBox
	 */
	public WebElement getValueTextBox(int timeOut) {
		return isDisplayed(driver, valueTextBox, "Visibility", timeOut, "Value Text Box");
	}
	
	public List<WebElement> getAllFundraisingscustomFields() {
		return FindElements(driver, "//div[@class='noStandardTab']//th[@class=' dataCell  ']//a", "All FundRaisings Custom Fields");
	}
	
	
	@FindBy(xpath="//th[@class=' dataCell  ']//a[text()='Commitment']")
	private WebElement commitmentLabelInObject;

	/**
	 * @return the commitmentLabelInObject
	 */
	public WebElement getCommitmentLabelInObject(int timeOut) {
		return isDisplayed(driver, commitmentLabelInObject, "Visibility", timeOut, "Commitment Label");
	}
	
	@FindBy(xpath="//input[@id='Length']")
	private WebElement lengthTextbox;

	/**
	 * @return the lengthTextbox
	 */
	public WebElement getLengthTextbox(int timeOut) {
		return isDisplayed(driver, lengthTextbox, "Visibility", timeOut, "Length TextBox");
	}
	
	public List<WebElement> getAllCommitmentscustomFields() {
		return FindElements(driver, "//div[@class='noStandardTab']//th[@class=' dataCell  ']//a", "All Commitments Custom Fields");
	}
	
	 
	/**
	 * @return the deleteFileNoButtonLinkContentGrid
	 */
	public WebElement getDeleteFileNoButtonContentGrid(Workspace workSpace,int timeOut) {
		int i =0;
		String prefixXpath = "(//div[contains(text(),'Confirm Deletion')])[";
		String suffixXpath = "]/following-sibling::div/a[@title='No']";
		String fullXpath="";
		WebElement ele;
		if(workSpace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())){
			i=2;
			fullXpath=prefixXpath+i+suffixXpath;
			ele= FindElement(driver, fullXpath, "Delete File No Button", action.BOOLEAN, timeOut);
			return isDisplayed(driver, ele, "Visibility", timeOut, "Delete File No Button");
		}else{
			i=1;
			fullXpath=prefixXpath+i+suffixXpath;
			ele= FindElement(driver, fullXpath, "Delete File No Button", action.BOOLEAN, timeOut);
			return isDisplayed(driver, ele, "Visibility", timeOut, "Delete File No Button");
		}
	
		
		
	}
	

	/**
	 * @return the deleteFileCrossIconLinkContentGrid
	 */
	public WebElement getDeleteFileCrossIconLinkContentGrid(Workspace workSpace,int timeOut) {
		
		int i =0;
		String prefixXpath = "(//div[contains(text(),'Confirm Deletion')])[";
		String suffixXpath = "]/span[@title='Close']";
		String fullXpath="";
		WebElement ele;
		if(workSpace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())){
			i=2;
			fullXpath=prefixXpath+i+suffixXpath;
			ele= FindElement(driver, fullXpath, "Delete File Cross Icon", action.BOOLEAN, timeOut);
			return isDisplayed(driver, ele, "Visibility", timeOut, "Delete File Cross Icon");
		}else{
			i=1;
			fullXpath=prefixXpath+i+suffixXpath;
			ele= FindElement(driver, fullXpath, "Delete File Cross Icon", action.BOOLEAN, timeOut);
			return isDisplayed(driver, ele, "Visibility", timeOut, "Delete File Cross Icon");
		}
	}
	

	/**
	 * @return the deleteFileYesButtonLinkContentGrid
	 */
	public WebElement getDeleteFileYesButtonContentGrid(Workspace workSpace , int timeOut) {
		
		int i =0;
		String prefixXpath = "(//div[contains(text(),'Confirm Deletion')])[";
		String suffixXpath = "]/following-sibling::div/a[@title='Yes']";
		String fullXpath="";
		WebElement ele;
		if(workSpace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())){
			i=2;
			fullXpath=prefixXpath+i+suffixXpath;
			ele= FindElement(driver, fullXpath, "Delete File Yes Button", action.BOOLEAN, timeOut);
			return isDisplayed(driver, ele, "Visibility", timeOut, "Delete File Yes Button");
		}else{
			i=1;
			fullXpath=prefixXpath+i+suffixXpath;
			ele= FindElement(driver, fullXpath, "Delete File Yes Button", action.BOOLEAN, timeOut);
			return isDisplayed(driver, ele, "Visibility", timeOut, "Delete File Yes Button");
		}
	}
	
	public WebElement getDeleteFilePopUpMsgContentGrid(Workspace workSpace , int timeOut) {
		
		int i =0;
		String prefixXpath = "(//div[contains(text(),'Confirm Deletion')])[";
		String suffixXpath = "]/../p";
		String fullXpath="";
		WebElement ele;
		if(workSpace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())){
			i=2;
			fullXpath=prefixXpath+i+suffixXpath;
			ele= FindElement(driver, fullXpath, "Delete File Msg", action.BOOLEAN, timeOut);
			return isDisplayed(driver, ele, "Visibility", timeOut, "Delete File Msg");
		}else{
			i=1;
			fullXpath=prefixXpath+i+suffixXpath;
			ele= FindElement(driver, fullXpath, "Delete File Msg", action.BOOLEAN, timeOut);
			return isDisplayed(driver, ele, "Visibility", timeOut, "Delete File Msg");
		}
	}
	
	public WebElement getDeleteFilePopUpHeaderContentGrid(Workspace workSpace , int timeOut) {
		
		int i =0;
		String prefixXpath = "(//div[contains(text(),'Confirm Deletion')])[";
		String suffixXpath = "]";
		String fullXpath="";
		WebElement ele;
		if(workSpace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())){
			i=2;
			fullXpath=prefixXpath+i+suffixXpath;
			ele= FindElement(driver, fullXpath, "Delete File Pop Up Header", action.BOOLEAN, timeOut);
			return isDisplayed(driver, ele, "Visibility", timeOut, "Delete File Pop Up Header");
		}else{
			i=1;
			fullXpath=prefixXpath+i+suffixXpath;
			ele= FindElement(driver, fullXpath, "Delete  File Pop Up Header", action.BOOLEAN, timeOut);
			return isDisplayed(driver, ele, "Visibility", timeOut, "Delete File Pop Up Header");
		}
	}


/**
 * @return the searchIcon for AllPage  FundRaising or Investor Side
 */
	public  WebElement getSearchIcon(WebDriver driver, PageName pName, Workspace workspace, int timeOut) {

		String workSpaceXpath = null;
		WebElement ele = null;
		
		String searchIconXpath = "a[title='Search']";//"//a[@title='Search']";
		String fullXPath;

		if (pName.toString().equalsIgnoreCase(PageName.FundsPage.toString())) {

			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath = "div#invworkspace";//"//div[@id='invworkspace']";

			} else {
				workSpaceXpath = "div#frworkspace";//"//div[@id='frworkspace']";

			}

		} else if (pName.toString().equalsIgnoreCase(PageName.InstitutionsPage.toString())
				|| pName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {

			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath = "div#Investorgrid_div";//"//div[@id='Investorgrid_div']";

			} else {
				workSpaceXpath = "div#divFrWorkspace";//"//div[@id='divFrWorkspace']";
			}
		} else if (pName.toString().equalsIgnoreCase(PageName.CommitmentsPage.toString())
				|| pName.toString().equalsIgnoreCase(PageName.CurrentInvestmentPgae.toString())
				|| pName.toString().equalsIgnoreCase(PageName.PotentialInvestmentPage.toString())) {

			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath ="div.content_div";// "//div[@class='content_div']";

			} else {
				workSpaceXpath = "div.content_div";//"//div[@class='content_div']";
			}
		}

		if (workSpaceXpath != null) {

			fullXPath = workSpaceXpath +" "+ searchIconXpath;
			appLog.info("Css FullXpath : "+fullXPath);
			scrollDownThroughWebelement(driver,getWorkspaceSectionView(workspace, 60),workspace + " View.");
		//	ele = FindElement(driver, fullXPath, "Search Icon", action.SCROLLANDBOOLEAN, timeOut);
			 ele= BaseLib.edriver.findElement(By.cssSelector(fullXPath));
			 

		} else {
			appLog.info(" Xpath Not Found for Seacrh Icon for " + pName.toString() + " : " + workspace.toString());
			BaseLib.sa.assertTrue(false,
					" Xpath Not Found for Seacrh Icon for " + pName.toString() + " : " + workspace.toString());
		}

		return ele;

	}
	
	/**
	 * @return the searchTextBox for AllPage  FundRaising or Investor Side
	 */

	public  WebElement getSearchTextBox(WebDriver driver, PageName pName, Workspace workspace, int timeOut) {

		String workSpaceXpath = null;
		WebElement ele = null;
		String searchTextBoxXpath = "//input[@class='input_txt_search']";
		String fullXPath;

		if (pName.toString().equalsIgnoreCase(PageName.FundsPage.toString())) {

			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath = "//div[@id='invworkspace']";

			} else {
				workSpaceXpath = "//div[@id='frworkspace']";

			}

		} else if (pName.toString().equalsIgnoreCase(PageName.InstitutionsPage.toString())
				|| pName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {

			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath = "//div[@id='Investorgrid_div']";

			} else {
				workSpaceXpath = "//div[@id='divFrWorkspace']";
			}
		} else if (pName.toString().equalsIgnoreCase(PageName.CommitmentsPage.toString())
				|| pName.toString().equalsIgnoreCase(PageName.CurrentInvestmentPgae.toString())
				|| pName.toString().equalsIgnoreCase(PageName.PotentialInvestmentPage.toString())) {

			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath = "//div[@class='content_div']";

			} else {
				workSpaceXpath = "//div[@class='content_div']";
			}
		}

		if (workSpaceXpath != null) {

			fullXPath = workSpaceXpath + searchTextBoxXpath;

			ele = FindElement(driver, fullXPath, "Search Text Box", action.SCROLLANDBOOLEAN, timeOut);

		} else {
			appLog.info(" Xpath Not Found for Seacrh Text Box for " + pName.toString() + " : " + workspace.toString());
			BaseLib.sa.assertTrue(false,
					" Xpath Not Found for Seacrh Text Box for " + pName.toString() + " : " + workspace.toString());
		}

		return ele;

	}

	
	@FindBy(xpath="//div[@id='SearchId']//div[@class='head_popup']")
	private WebElement searchHeader;

	/**
	 * @return the searchHeader
	 */
	public WebElement getSearchHeader(int timeOut) {
		return isDisplayed(driver, searchHeader, "Visibility", timeOut, "Search Header");
	}
	
	@FindBy(xpath="//span[@id='SearchResultsFor_DealDetail_FileSearch-headers']/span[contains(@id,'SearchResultsFor_DealDetail_FileSearch-header-')]")
	private List<WebElement> allLabelsOnSearchPopUp;

	/**
	 * @return the allLabelsOnSearchPopUp
	 */
	public List<WebElement> getAllLabelsOnSearchPopUp() {
		if(checkElementsVisibility(driver, allLabelsOnSearchPopUp, "All Label Search Pop", 60)){
			return allLabelsOnSearchPopUp;
		} else {
			return null;
		}
	}
	
	/**
	 * @return the getAllRadioButton
	 */
	public WebElement getAllFolderRadioButton(int timeOut) {
		String path = "//span[text()='Search All Folders']/..//input";
		WebElement ele =FindElement(driver, path, "All Folder Radio Button", action.SCROLLANDBOOLEAN, 30);
		return isDisplayed(driver, ele, "Visibility", timeOut, "Selected Folder Radio Button");
	}

	/**
	 * @return the getFolderRadioButton
	 */
	public WebElement getFolderRadioButton(String folder,int timeOut) {
		String path = "//span[@title='"+folder+"']/../..//input[@id='selectedFolder']";
		WebElement ele =FindElement(driver, path, folder, action.SCROLLANDBOOLEAN, 30);
		return isDisplayed(driver, ele, "Visibility", timeOut, " Folder Radio Button");
	}
	
	@FindBy(xpath="//a[contains(@onclick,'clear_gridDD')]")
	private WebElement clearSearchContentButtonSearchResultPopUp;

	/**
	 * @return the clearSearchContentButtonSearchResultPopUp
	 */
	public WebElement getClearSearchContentButtonSearchResultPopUp(int timeOut) {
		return isDisplayed(driver, clearSearchContentButtonSearchResultPopUp, "Visibility", timeOut, "clear Search Result Button On Search Result Popup.");
	}
	
	@FindBy(xpath="//div[contains(text(),'Search Results')]/following-sibling::div//input[@class='input_txt_search']")
	private WebElement searchResultPopUpTextBox;

	/**
	 * @return the searchResultPopUpTextBox
	 */
	public WebElement getSearchResultPopUpTextBox(int timeOut) {
		return isDisplayed(driver, searchResultPopUpTextBox, "Visibility", timeOut, "Search Result Pop Up Text Box.");
	}
	
	@FindBy(xpath="//div[contains(text(),'Search Results')]/following-sibling::div//a[@class='icon_btn_search']")
	private WebElement searchResultPopSearchIcon;

	/**
	 * @return the searchResultPopSearchIcon
	 */
	public WebElement getSearchResultPopSearchIcon(int timeOut) {
		return isDisplayed(driver, searchResultPopSearchIcon, "Visibility", timeOut, "search Result Search Icon Button");
	}
	
	@FindBy(xpath="//a[@onclick='refreshAllData(); return false;']")
	private WebElement searchPopCrossIcon;

	/**
	 * @return the searchPopCrossIcon
	 */
	public WebElement getSearchPopCrossIcon(int timeOut) {
		return isDisplayed(driver, searchPopCrossIcon, "Visibility", timeOut, "Search Pop Up Cross Icon");
	}
	
	/**
	 * @return the getSearchFolderPathColumnValue
	 */
	public List<WebElement> getSearchFolderPathColumnValue(){
		String xpath ="//span[contains(@id,'SearchResultsFor_DealDetail_FileSearch-cell-0')]/span";
		return FindElements(driver, xpath, "Folder Path Column Value");
	}
	
	/**
	 * @return the getSearchFolderPathColumnValue
	 */
	public List<WebElement> getSearchDocumentNameColumnValue(){
		String xpath ="//span[contains(@id,'SearchResultsFor_DealDetail_FileSearch-cell-1')]/a";
		return FindElements(driver, xpath, "Document Name Column Value");
	}
	
	/**
	 * @return the getSearchSizeColumnValue
	 */
	public List<WebElement> getSearchSizeColumnValue(){
		String xpath ="//span[contains(@id,'SearchResultsFor_DealDetail_FileSearch-cell-2')]";
		return FindElements(driver, xpath, "Size Column Value");
	}
	
	/**
	 * @return the getSearchLastUpdatedColumnValue
	 */
	public List<WebElement> getSearchLastUpdatedColumnValue(){
		String xpath ="//span[contains(@id,'SearchResultsFor_DealDetail_FileSearch-cell-3')]/span";
		return FindElements(driver, xpath, "Last Updated Column Value");
	}
	
	@FindBy(xpath="//div[@id='clearsearchenbDD']/a")
	private WebElement searchPopSearchTextBoxCrossIcon;

	/**
	 * @return the searchPopSearchTextBoxCrossIcon
	 */
	public WebElement getSearchPopSearchTextBoxCrossIcon(int timeOut) {
		return isDisplayed(driver, searchPopSearchTextBoxCrossIcon, "Visibility", timeOut, "Search Pop Up Search Text Box Cross Icon");
	}
	
	@FindBy(xpath="//span[contains(@id,'SearchResultsFor_DealDetail_FileSearch-cell-')]/span")
	private WebElement noDataToDisplaySearchPopMsg;

	/**
	 * @return the noDataToDisplaySearchPopMsg
	 */
	public WebElement getNoDataToDisplaySearchPopMsg(int timeOut) {
		return isDisplayed(driver, noDataToDisplaySearchPopMsg, "Visibility", timeOut, "No Data To display Msg");
	}
	
	@FindBy(xpath="//div[@id='SearchId']//b/font")
	private WebElement noDataToDisplaySearchPopMsg1;

	/**
	 * @return the noDataToDisplaySearchPopMsg1
	 */
	public WebElement getNoDataToDisplaySearchPopMsg1(int timeOut) {
		return isDisplayed(driver, noDataToDisplaySearchPopMsg1, "Visibility", timeOut, "No Data To display Msg");
	}
	
	/**
	 * @deprecated Use {@link #getColumnHeads(PageName,Workspace)} instead
	 */
	public List<WebElement> getColumnHeads(){
		return getColumnHeads(PageName.FundsPage, Workspace.FundraisingWorkspace);
	}

	public List<WebElement> getColumnHeads(PageName pageName, Workspace workSpace){
		if(workSpace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			return FindElements(driver, "//span[contains(@id,'myGridfundr-header-')]/span[3]", "column heads");	
		}else{
			return FindElements(driver, "//span[contains(@id,'myGrid-header-')]/span[3]", "column heads");		
		}
		
	}
	
	/**
	 * @deprecated Use {@link #getSortingArrow(PageName,Workspace)} instead
	 */
	public List<WebElement> getSortingArrow() {
		return getSortingArrow(PageName.FundsPage, Workspace.FundraisingWorkspace);
	}

	public List<WebElement> getSortingArrow(PageName pageNAME, Workspace workSpace) {
		if (workSpace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
			return FindElements(driver, "//span[contains(@id,'myGridfundr-header-')]/span[3]/span",
					"sorting arrow for all columns");
		} else {
			return FindElements(driver, "//span[contains(@id,'myGrid-header-')]/span[3]/span",
					"sorting arrow for all columns");
		}
		}
	
	public List<WebElement> getColumnHeadsInvestor(){
		return FindElements(driver, "//span[contains(@id,'myGrid-header-')]/span[3]", "column heads Investor");
	}
	public List<WebElement> getSortingArrowInvestor() {
		return FindElements(driver, "//span[contains(@id,'myGrid-header-')]/span[3]/span", "sorting arrow for all columns Investor");
	}
	
	public WebElement getAlertHistoryLink(Workspace workspace,PageName pageName,int timeOut) {
		
		String workspaceSelector="";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			if(pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {
				workspaceSelector="pg:frm:ACTALTFR:AVTALT:theLinkPanel";
			}else {
				workspaceSelector = "page:formACTALTFR:ACTALTFR:AVTALT:theLinkPanel";
			}
		} else {
			if(pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {
				workspaceSelector="pg:frm:ACTALTINV:AVTALT:theLinkPanel";
			}else {
				workspaceSelector = "page:formACTALTINV:ACTALTINV:AVTALT:theLinkPanel";
			}
		}
		return isDisplayed(driver, FindElement(driver,"//span[@id='"+workspaceSelector+"']//a[@title='Alert History']", "alert history link", action.BOOLEAN, timeOut), "Visibility", timeOut, "alert history link");
	}
	
	public WebElement getAlertHistoryCrossIcon(Workspace workspace, int timeOut){
		String workspaceSelector="";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[contains(@class,'ActivityAlerts_fancyboxACTALT"+workspaceSelector+"')]//a[@title='Close']", "alert history cross icon", action.BOOLEAN, timeOut), "Visibility", timeOut, "alert history cross icon");
	}
	
	
	public WebElement getFirmProfileUpdatedHeader(Workspace workspace,PageName pageName,int timeOut) {
		
		String workspaceSelector="";
			if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {
				if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector="ACTALTFR:AVTALT:rendrAccountHistory";
			}else {
				workspaceSelector = "ACTALTINV:AVTALT:rendrAccountHistory";
			}
		} else {
			if(pageName.toString().equalsIgnoreCase(PageName.HomePage.toString())) {
				workspaceSelector="page:formACTALT_new:ACTALT:AVTALT:rendrAccountHistory";
			}
		}
		return isDisplayed(driver, FindElement(driver,"//span[contains(@id,'"+workspaceSelector+"')]//div[@class='head_popup']", "alert history link", action.BOOLEAN, timeOut), "Visibility", timeOut, "Firm profile updtaed header");
	}
	
			
	public WebElement getFirmTextLabel(Workspace workspace,PageName pageName,int timeOut) {
			String workspaceSelector="";
					if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {
						if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
						workspaceSelector="formboxACTALTFR";
					}else {
						workspaceSelector = "formboxACTALTINV";
					}
				} else {
					if(pageName.toString().equalsIgnoreCase(PageName.HomePage.toString())) {
						workspaceSelector="formboxACTALT";
					}
				}
				return isDisplayed(driver, FindElement(driver,"(//div[@id='"+workspaceSelector+"']//strong[text()='Firm:']/..//following-sibling::td[@class='maxcharswordwrap'])[1]", "alert history link", action.BOOLEAN, timeOut), "Visibility", timeOut, "Firm value");
			}	
			
	@FindBy(xpath="//span[text()='Field']")
	private WebElement fieldLabelInFirmProfilePopup;

	/**
	 * @return the fieldLabelInFirmProfilePopup
	 */
	public WebElement getFieldLabelInFirmProfilePopup(int timeOut) {
		return isDisplayed(driver, fieldLabelInFirmProfilePopup, "Visibility", timeOut, "Field Label in firm profile popup");
	}
	@FindBy(xpath="//span[text()='New Value']")
	private WebElement valueLabelInFirmProfilePopup;

	/**
	 * @return the fieldLabelInFirmProfilePopup
	 */
	public WebElement getValueLabelInFirmProfilePopup(int timeOut) {
		return isDisplayed(driver, valueLabelInFirmProfilePopup, "Visibility", timeOut, "Value Label in firm profile popup");
	}	
			
	
	public WebElement getFirmProfileUpdateScrollBox(Workspace workspace,PageName pageName,int timeOut) {
		String workspaceSelector="";
				if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {
					if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
					workspaceSelector="grid_AccountProfileUpdatedACTALTFR-scroll-box";
				}else {
					workspaceSelector = "grid_AccountProfileUpdatedACTALTINV-scroll-box";
				}
			} else {
				if(pageName.toString().equalsIgnoreCase(PageName.HomePage.toString())) {
					workspaceSelector="grid_AccountProfileUpdatedACTALT-scroll-box";
				}
			}
			return isDisplayed(driver, FindElement(driver,"//span[@id='"+workspaceSelector+"']", "Firm Profile Updtae scrollBOx", action.BOOLEAN, timeOut), "Visibility", timeOut, "Firm Profile Updae scroll ox");
		}			
			
	public WebElement getGoToFirmButton(PageName pageName,Workspace workspace, int timeOut){
		String workspaceSelector="";
		if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())){
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "IDInvestorAccountACTALTFR";
		} else {
			workspaceSelector = "IDInvestorAccountACTALTINV";
		}
	}else{
		if(pageName.toString().equalsIgnoreCase(PageName.HomePage.toString())) {
			workspaceSelector="IDInvestorAccountACTALT";
	}
	}
		return isDisplayed(driver, FindElement(driver, "//div[@id='"+workspaceSelector+"']//a[text()='Go to Firm']", "Go to firm button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Go to firm button");
	}
	
	
			
	public WebElement getFirmProfileUpdateCloseButton(Workspace workspace,PageName pageName,int timeOut) {
		String workspaceSelector="";
				if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {
					if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
					workspaceSelector="ACTALTFR:AVTALT:rendrAccountHistory";
				}else {
					workspaceSelector = "ACTALTINV:AVTALT:rendrAccountHistory";
				}
			} else {
				if(pageName.toString().equalsIgnoreCase(PageName.HomePage.toString())) {
					workspaceSelector="page:formACTALT_new:ACTALT:AVTALT:rendrAccountHistory";
				}
			}
			return isDisplayed(driver, FindElement(driver,"//span[contains(@id,'"+workspaceSelector+"')]//a[text()='Close']", "Firm Profile Updtae Close Button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Firm Profile Update close button");
		}		
	
	public WebElement getFirmProfileUpdateCloseIcon(Workspace workspace,PageName pageName,int timeOut) {
		String workspaceSelector="";
				if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {
					if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
					workspaceSelector="IDInvestorAccountACTALTFR";
				}else {
					workspaceSelector = "IDInvestorAccountACTALTINV";
				}
			} else {
				if(pageName.toString().equalsIgnoreCase(PageName.HomePage.toString())) {
					workspaceSelector="IDInvestorAccountACTALT";
				}
			}
			return isDisplayed(driver, FindElement(driver,"//div[@id='"+workspaceSelector+"']//div[@class='head_popup']//a[@title='Close']", "Firm Profile Updtae Close icon", action.BOOLEAN, timeOut), "Visibility", timeOut, "Firm Profile Update close icon");
		}	
		
	public WebElement getContactProfileUpdatedHeader(Workspace workspace,PageName pageName,int timeOut) {
		
		String workspaceSelector="";
			if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {
				if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector="ACTALTFR:AVTALT:rendrContactHistory";
			}else {
				workspaceSelector = "ACTALTINV:AVTALT:rendrContactHistory";
			}
		} else {
			if(pageName.toString().equalsIgnoreCase(PageName.HomePage.toString())) {
				workspaceSelector="page:formACTALT_new:ACTALT:AVTALT:rendrContactHistory";
			}
		}
		return isDisplayed(driver, FindElement(driver,"//span[contains(@id,'"+workspaceSelector+"')]//div[@class='head_popup']", "Contact Profile header", action.BOOLEAN, timeOut), "Visibility", timeOut, "Contact profile updtaed header");
	}
	
	public WebElement getFirmTextLabelInContactProfileUpdated(Workspace workspace,PageName pageName,int timeOut) {
		String workspaceSelector="";
				if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {
					if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
					workspaceSelector="formboxACTALTFR";
				}else {
					workspaceSelector = "formboxACTALTINV";
				}
			} else {
				if(pageName.toString().equalsIgnoreCase(PageName.HomePage.toString())) {
					workspaceSelector="formboxACTALT";
				}
			}
			return isDisplayed(driver, FindElement(driver,"(//div[@id='"+workspaceSelector+"']//strong[text()='Firm:']/..//following-sibling::td[@class='maxcharswordwrap'])[2]", "Firm label in contact profile updtaed", action.BOOLEAN, timeOut), "Visibility", timeOut, "Firm value");
		}
	public WebElement getContactTextLabelInContactProfileUpdated(Workspace workspace,PageName pageName,int timeOut) {
		String workspaceSelector="";
				if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {
					if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
					workspaceSelector="formboxACTALTFR";
				}else {
					workspaceSelector = "formboxACTALTINV";
				}
			} else {
				if(pageName.toString().equalsIgnoreCase(PageName.HomePage.toString())) {
					workspaceSelector="formboxACTALT";
				}
			}
			return isDisplayed(driver, FindElement(driver,"(//div[@id='"+workspaceSelector+"']//strong[text()='Contact:']/..//following-sibling::td[@class='maxcharswordwrap'])[1]", "Contact label in contact profile updtaed", action.BOOLEAN, timeOut), "Visibility", timeOut, "Contact value");
		}
	public WebElement getContactProfileUpdateScrollBox(Workspace workspace,PageName pageName,int timeOut) {
		String workspaceSelector="";
				if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {
					if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
					workspaceSelector="grid_ContactProfileUpdatedACTALTFR-scroll-box";
				}else {
					workspaceSelector = "grid_ContactProfileUpdatedACTALTINV-scroll-box";
				}
			} else {
				if(pageName.toString().equalsIgnoreCase(PageName.HomePage.toString())) {
					workspaceSelector="grid_ContactProfileUpdatedACTALT-scroll-box";
				}
			}
			return isDisplayed(driver, FindElement(driver,"//span[@id='"+workspaceSelector+"']", "Contact Profile Updtae scrollBOx", action.BOOLEAN, timeOut), "Visibility", timeOut, "Contact Profile Updae scroll ox");
		}	
	

	public WebElement getGoToContactButton(PageName pageName,Workspace workspace, int timeOut){
		String workspaceSelector="";
		if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "IDInvestorContactACTALTFR";
		} else {
			workspaceSelector = "IDInvestorContactACTALTINV";
		}
		}else{
			if(pageName.toString().equalsIgnoreCase(PageName.HomePage.toString())) {
				workspaceSelector="IDInvestorContactACTALT";
			}
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='"+workspaceSelector+"']//a[text()='Go to Contact']", "Go to Contact button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Go to Contact button");
	}
	
	public WebElement getContactProfileUpdateCloseButton(Workspace workspace,PageName pageName,int timeOut) {
		String workspaceSelector="";
				if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {
					if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
					workspaceSelector="ACTALTFR:AVTALT:rendrContactHistory";
				}else {
					workspaceSelector = "ACTALTINV:AVTALT:rendrContactHistory";
				}
			} else {
				if(pageName.toString().equalsIgnoreCase(PageName.HomePage.toString())) {
					workspaceSelector="page:formACTALT_new:ACTALT:AVTALT:rendrContactHistory";
				}
			}
			return isDisplayed(driver, FindElement(driver,"//span[contains(@id,'"+workspaceSelector+"')]//a[text()='Close']", "Contact Profile Updtae Close Button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Contact Profile Update close button");
		}		
	
	public WebElement getContactProfileUpdateCloseIcon(Workspace workspace,PageName pageName,int timeOut) {
		String workspaceSelector="";
				if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {
					if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
					workspaceSelector="ACTALTFR:AVTALT:rendrContactHistory";
				}else {
					workspaceSelector = "ACTALTINV:AVTALT:rendrContactHistory";
				}
			} else {
				if(pageName.toString().equalsIgnoreCase(PageName.HomePage.toString())) {
					workspaceSelector="page:formACTALT_new:ACTALT:AVTALT:rendrContactHistory";
				}
			}
			return isDisplayed(driver, FindElement(driver,"//span[contains(@id,'"+workspaceSelector+"')]//div[@class='head_popup']//a[@title='Close']", "Contact Profile Updtae Close icon", action.BOOLEAN, timeOut), "Visibility", timeOut, "Contact Profile Update close icon");
		}	
	
	public WebElement getAlertHistoryScrollbox(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
		workspaceSelector = "FR";
		} else {
		workspaceSelector = "INV";
		}
	return isDisplayed(driver, FindElement(driver, "//span[@id='myGridACTALT"+workspaceSelector+"-scroll-box']", "Alert history popup scrollbox", action.BOOLEAN, timeOut), "Visibility", timeOut, "Alert history popup Scroll box");
	}
	
	@FindBy(xpath="//span[text()='Date']//span")
	private WebElement dateSortIcon;

	/**
	 * @return the dateSortIcon
	 */
	public WebElement getDateSortIcon(int timeOut) {
		return isDisplayed(driver, dateSortIcon, "Visibility", timeOut, "Date sort icon");
	}	
	
	@FindBy(xpath="//span[text()='Activity Type']//span")
	private WebElement activityTypeSortIcon;

	/**
	 * @return the dateSortIcon
	 */
	public WebElement getActivityTypeSortIcon(int timeOut) {
		return isDisplayed(driver, activityTypeSortIcon, "Visibility", timeOut, "activity Type sort icon");
	}
	
	public List<WebElement> getActivityTypeColumnValue(PageName pageName,Workspace workspace){		
		if(pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString()) || pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())){
			String workspaceSelector = "";
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
				workspaceSelector = "FR";
				} else {
				workspaceSelector = "INV";
				}
			String xpath ="//span[@id='myGridACTALT"+workspaceSelector+"-rows']//span[contains(@id,'myGridACTALT"+workspaceSelector+"-cell-0-')]";
			return FindElements(driver, xpath, "Activity type Column Value");
		}else{
			String xpath ="//span[@id='myGridACTALT-rows']//span[contains(@id,'myGridACTALT-cell-0-')]";
			return FindElements(driver, xpath, "Activity type Column Value");
		}
		
	}	
	
	@FindBy(xpath="//span[text()='Workspace']/span")
	private WebElement workspaceSortIconOnHomePageAlert;

	/**
	 * @return the workspaceSortIconOnHomePageAlert
	 */
	public WebElement getWorkspaceSortIconOnHomePageAlert(int timeOut) {
		return isDisplayed(driver, workspaceSortIconOnHomePageAlert, "Visibility", timeOut, "workspace Sort Icon On Home Page Alert");
	}
	@FindBy(xpath="//span[text()='Workspace']")
	private WebElement workspaceLabelOnHomePageAlert;

	/**
	 * @return the workspaceSortIconOnHomePageAlert
	 */
	public WebElement getWorkspaceLabelOnHomePageAlert(int timeOut) {
		return isDisplayed(driver, workspaceLabelOnHomePageAlert, "Visibility", timeOut, "workspace Label On Home Page Alert");
	}
	
	@FindBy(xpath="//span[text()='Field']/span")
	private WebElement fieldLabelSortIconOnProfileUpdatedPopupAlert;

	/**
	 * @return the fieldLabelSortIconOnProfileUpdatedPopupAlert
	 */
	public WebElement getFieldLabelSortIconOnProfileUpdatedPopupAlert(int timeOut) {
		return isDisplayed(driver, fieldLabelSortIconOnProfileUpdatedPopupAlert, "Visibility", timeOut, "field Label Sort Icon On Profile Updated Popup Alert ");
	}
	
	@FindBy(xpath="//div[@id='fileNotFound']")
	private WebElement fileNotFoundErrorMessage;

	/**
	 * @return the fileNotFoundErrorMessage
	 */
	public WebElement getFileNotFoundErrorMessage(int timeOut) {
		return isDisplayed(driver, fileNotFoundErrorMessage, "Visibility", timeOut, "File Not found error Message");
	}
	
	@FindBy(xpath="//a[text()='Create New View']")
	private WebElement createViewLink;

	/**
	 * @return the createViewLink
	 */
	public WebElement getCreateViewLink(int timeOut) {
		return isDisplayed(driver, createViewLink, "Visibility", timeOut, "Create View Link");
	}
	
	@FindBy(xpath="//label[text()='View Name:']/../following-sibling::td//input")
	private WebElement viewName;

	/**
	 * @return the viewName
	 */
	public WebElement getViewName(int timeOut) {
		return isDisplayed(driver, viewName, "Visibility", timeOut, "View Name");
	}
	
	@FindBy(xpath="//label[text()='View Unique Name:']/../following-sibling::td//input")
	private WebElement viewUniqueName;

	/**
	 * @return the viewUniqueName
	 */
	public WebElement getViewUniqueName(int timeOut) {
		return isDisplayed(driver, viewUniqueName, "Visibility", timeOut, "View Unique Name");
	}
	
	@FindBy(xpath="//select[@id='fcol1']")
	private WebElement fieldDropDown;

	/**
	 * @return the fieldDropDown
	 */
	public WebElement getFieldDropDown(int timeOut) {
		return isDisplayed(driver, fieldDropDown, "Visibility", timeOut, "Field Drop Down");
	}
	
	@FindBy(xpath="//select[@id='fcol2']")
	private WebElement fieldDropDown1;

	/**
	 * @return the fieldDropDown
	 */
	public WebElement getFieldDropDown1(int timeOut) {
		return isDisplayed(driver, fieldDropDown1, "Visibility", timeOut, "Field Drop Down");
	}
	
	@FindBy(xpath="//select[@id='fop1']")
	private WebElement viewOperatorDropDown;

	/**
	 * @return the viewOperatorDropDown
	 */
	public WebElement getViewOperatorDropDown(int timeOut) {
		return isDisplayed(driver, viewOperatorDropDown, "Visibility", timeOut, "View Operator Drop Down");
	}
	
	@FindBy(xpath="//select[@id='fop2']")
	private WebElement viewOperatorDropDown1;

	/**
	 * @return the viewOperatorDropDown
	 */
	public WebElement getViewOperatorDropDown1(int timeOut) {
		return isDisplayed(driver, viewOperatorDropDown1, "Visibility", timeOut, "View Operator Drop Down");
	}
	
	@FindBy(xpath="//input[@id='fval1']")
	private WebElement viewValueBox;

	/**
	 * @return the viewValueBox
	 */
	public WebElement getViewValueBox(int timeOut) {
		return isDisplayed(driver, viewValueBox, "Visibility", timeOut, "View Text Box");
	}
	
	@FindBy(xpath="//input[@id='fval2']")
	private WebElement viewValueBox1;

	/**
	 * @return the viewValueBox
	 */
	public WebElement getViewValueBox1(int timeOut) {
		return isDisplayed(driver, viewValueBox1, "Visibility", timeOut, "View Text Box");
	}
	
	@FindBy(xpath="//td[text()='Folder Structure']/following-sibling::td/div")
	private WebElement folderStructureInHub;

	/**
	 * @return the folderStructureInHub
	 */
	public WebElement getFolderStructureInHub(int timeOut) {
		return isDisplayed(driver, folderStructureInHub, "Visibility", timeOut, "Folder Structure in hub");
	}
	
	@FindBy(xpath="(//td[text()='Related Folder IDs1']/following-sibling::td/div)[1]")
	private WebElement relatedFolderIds;

	/**
	 * @return the relatedFolderIds
	 */
	public WebElement getRelatedFolderIds(int timeOut) {
		return isDisplayed(driver, relatedFolderIds, "Visibility", timeOut, "");
	}
	
	@FindBy(xpath="(//td[text()='Related Folder IDs2']/following-sibling::td/div)[1]")
	private WebElement relatedFolderIds2;

	/**
	 * @return the folderStrucutreIds2
	 */
	public WebElement getRelatedFolderIds2(int timeOut) {
		return isDisplayed(driver, relatedFolderIds2, "Visibility", timeOut, "Folder Structure ids2");
	}
	
	@FindBy(xpath="//input[@id='phSearchInput']")
	private WebElement globalSearch;

	/**
	 * @return the globalSearch
	 */
	public WebElement getGlobalSearch(int timeOut) {
		return isDisplayed(driver, globalSearch, "Visibility", timeOut, "Global Search");
	}
	
	@FindBy(xpath="//input[@id='phSearchButton']")
	private WebElement globalSearchButton;

	/**
	 * @return the globalSearch
	 */
	public WebElement getGlobalSearchButton(int timeOut) {
		return isDisplayed(driver, globalSearchButton, "Visibility", timeOut, "Global Search Button");
	}
	
	@FindBy(xpath="//a[@title='Developer Console (New Window)']")
	private WebElement devConsoleLink;

	/**
	 * @return the devConsoleLink
	 */
	public WebElement getDevConsoleLink(int timeOut) {
		return isDisplayed(driver, devConsoleLink, "Visibility", timeOut, "Dev Console");
	}
	
	@FindBy(xpath="//span[text()='Execute']")
	private WebElement executeButton;

	/**
	 * @return the executeButton
	 */
	public WebElement getExecuteButton(int timeOut) {
		return isDisplayed(driver, executeButton, "Visibility", timeOut, "Execute Button");
	}
	
	@FindBy(xpath="(//span[text()='Execute'])[2]")
	private WebElement executeButton1;

	/**
	 * @return the executeButton
	 */
	public WebElement getExecuteButton1(int timeOut) {
		return isDisplayed(driver, executeButton1, "Visibility", timeOut, "Execute Button");
	}
	
	@FindBy(xpath="//div[@id='recordscountACTALT']")
	private WebElement recordCountOnHomePage;
	
	/**
	 * @return the recordCountOnHomePage
	 */
	public WebElement getRecordCountOnHomePage(int timeOut) {
		return isDisplayed(driver, recordCountOnHomePage, "Visibility", timeOut, "Record Count");
	}
	
	@FindBy(xpath="(//a[@title='Show All'])[1]")
	private WebElement showAllLinkFWR;
	
	@FindBy(xpath="(//a[@title='Show All'])[2]")
	private WebElement showAllLinkINV;
	
	@FindBy(xpath="//a[@title='Show All']")
	private WebElement showAllLink;

	/**
	 * @return the showAllLink
	 */
	public WebElement getShowAllLink(Workspace workspace, int timeOut) {
		if(workspace!=null && workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			return isDisplayed(driver, showAllLinkFWR, "Visibility", timeOut, "Show All Link");
		} else if(workspace!=null && workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
			return isDisplayed(driver, showAllLinkINV, "Visibility", timeOut, "Show All Link");
		} else {
			return isDisplayed(driver, showAllLink, "Visibility", timeOut, "Show All Link");
		}
	}
	
	public WebElement getRecordCountAlertHistoryPopUp(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='recordscountACTALT"+workspaceSelector+"']", "Record Count", action.BOOLEAN, 30), "Visibility", timeOut, "record Count");
	}

	@FindBy(xpath="//img[@title='Next']/..")
	private WebElement nextImageonPage;

	/**
	 * @return the nextImageonPage
	 */
	public WebElement getNextImageonPage(int timeOut) {
		return isDisplayed(driver, nextImageonPage, "Visibility", timeOut, "Next Image");
	}
	
	@FindBy(xpath="//span[@id='gridDivRecords']")
	private WebElement investorRecordCount;

	/**
	 * @return the investorRecordCount
	 */
	public WebElement getInvestorRecordCount(int timeOut) {
		return isDisplayed(driver, investorRecordCount, "Visibility", timeOut, "Investor Files Record Count");
	}
	
	@FindBy(xpath="//textarea[@id='queryEditorText-inputEl']")
	private WebElement devEditorEditor;

	/**
	 * @return the devEditorEditor
	 */
	public WebElement getDevEditorEditor(int timeOut) {
		return isDisplayed(driver, devEditorEditor, "Visibility", timeOut, "dev console query editor");
	}
	
	@FindBy(xpath="//span[@id='queryExecuteButton-btnInnerEl']")
	private WebElement devEditorQueryExecutorBtn;

	/**
	 * @return the devEditorQueryExecutorBtn
	 */
	public WebElement getDevEditorQueryExecutorBtn(int timeOut) {
		return isDisplayed(driver, devEditorQueryExecutorBtn, "Visibility", timeOut, "dev editor query executor button");
	}
	
	@FindBy(xpath="//span[@class='title']")
	private WebElement resetPasswordPageHeader;

	/**
	 * @return the resetPasswordPageHeader
	 */
	public WebElement getResetPasswordPageHeader(int timeOut) {
		return isDisplayed(driver, resetPasswordPageHeader, "Visibility", timeOut, "Reset PAssword page header");
	}
	
	@FindBy(xpath="//input[@id='setPassword:frmid:psw']")
	private WebElement resetPasswordNewPasswordTextBox;

	/**
	 * @return the resetPasswordPageHeader
	 */
	public WebElement getResetPasswordNewPasswordTextBox(int timeOut) {
		return isDisplayed(driver, resetPasswordNewPasswordTextBox, "Visibility", timeOut, "Reset PAssword new password textbox");
	}
	
	@FindBy(xpath="//input[@id='setPassword:frmid:vpsw']")
	private WebElement resetPasswordConfirmPasswordTextBox;

	/**
	 * @return the resetPasswordPageHeader
	 */
	public WebElement getResetPasswordConfirmPasswordTextBox(int timeOut) {
		return isDisplayed(driver, resetPasswordConfirmPasswordTextBox, "Visibility", timeOut, "Reset PAssword Confirm password textbox");
	}
	
	
	@FindBy(xpath="//span[@class='help_txt']/i")
	private WebElement resetPasswordErrorMessageText;

	/**
	 * @return the resetPasswordPageHeader
	 */
	public WebElement getResetPasswordErrorMessageText(int timeOut) {
		return isDisplayed(driver, resetPasswordErrorMessageText, "Visibility", timeOut, "Reset PAssword error Message text");
	}
		
	@FindBy(xpath="//a[contains(@title,'Navatar')]/img")
	private WebElement resetPasswordNavatarLogo;

	/**
	 * @return the resetPasswordPageHeader
	 */
	public WebElement getResetPasswordNavatarLogo(int timeOut) {
		return isDisplayed(driver, resetPasswordNavatarLogo, "Visibility", timeOut, "Reset PAssword Navatar logo");
	}	
	
	@FindBy(xpath="//input[@title='Reset Password']")
	private WebElement resetPasswordButton;

	/**
	 * @return the resetPasswordPageHeader
	 */
	public WebElement getResetPasswordButton(int timeOut) {
		return isDisplayed(driver, resetPasswordButton, "Visibility", timeOut, "Reset PAssword Button");
	}
	
	@FindBy(xpath="(//label[@class='datalabelDisplayForm'])[1]")
	private WebElement resetPasswordNewPasswordText;

	/**
	 * @return the resetPasswordPageHeader
	 */
	public WebElement getResetPasswordNewPasswordText(int timeOut) {
		return isDisplayed(driver, resetPasswordNewPasswordText, "Visibility", timeOut, "Reset PAssword new password text");
	}
		
	@FindBy(xpath="(//label[@class='datalabelDisplayForm'])[2]")
	private WebElement resetPasswordConfirmPasswordText;

	/**
	 * @return the resetPasswordPageHeader
	 */
	public WebElement getResetPasswordConfirmPasswordText(int timeOut) {
		return isDisplayed(driver, resetPasswordConfirmPasswordText, "Visibility", timeOut, "Reset PAssword confirm password text");
	}
		
	@FindBy(xpath="//span[@id='validtnErr']")
	private WebElement resetPasswordEnterValueErrorMsg;

	/**
	 * @return the resetPasswordEnterValueErrorMsg
	 */
	public WebElement getResetPasswordEnterValueErrorMsg(int timeOut) {
		return isDisplayed(driver, resetPasswordEnterValueErrorMsg, "Visibility", timeOut, "Reset Password enter value error message");
	}
	
	@FindBy(xpath="//span[@id='pasMtch']")
	private WebElement resetPasswordPasswordAndConfirmPasswordNotSameErrorMessage;

	/**
	 * @return the resetPasswordPasswordAndConfirmPasswordNotSameErrorMessage
	 */
	public WebElement getResetPasswordPasswordAndConfirmPasswordNotSameErrorMessage(int timeOut) {
		return isDisplayed(driver, resetPasswordPasswordAndConfirmPasswordNotSameErrorMessage, "Visibility", timeOut, "Reset Password Pasword and Confirm password not same error messag");
	}
	
	@FindBy(xpath="//div[@id='lognMsgID']/span")
	private WebElement resetPasswordPasswordChangeMessage;

	/**
	 * @return the resetPasswordPasswordChangeMessage
	 */
	public WebElement getResetPasswordPasswordChangeMessage(int timeOut) {
		return isDisplayed(driver, resetPasswordPasswordChangeMessage, "Visibility", timeOut, "Passowrd change message");
	}
	
	@FindBy(xpath="//img[@title='Last Page']/..")
	private WebElement LastImageonPage;

	/**
	 * @return the nextImageonPage
	 */
	public WebElement getLastImageonPage(int timeOut) {
		return isDisplayed(driver, LastImageonPage, "Visibility", timeOut, "Last Page Image");
	}
	
	@FindBy(xpath = "//img[contains(@title,'Expand - Manage Users')]")
	private WebElement expandManageUserIcon_Classic;
	
	@FindBy(xpath="//a[text()='Users']/../button")
	private WebElement expandUserIcon_Lighting;
	/**
	 * @return the expandManageUserIcon
	 */
	public WebElement getExpandUserIcon(String environment, String mode,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			return isDisplayed(driver, expandUserIcon_Lighting, "Visibility", timeOut, "Expand User Icon in "+mode);
		}else {
			return isDisplayed(driver, expandManageUserIcon_Classic, "Visibility", timeOut, "Expand Manage User Icon in "+mode);
		}
	}
	
	@FindBy(xpath = "//a[@id='ManageUsers_font']")
	private WebElement userLink_Classic;
	
	@FindBy(xpath="//a[text()='Users'][contains(@href,'ManageUsers/home')]")
	private WebElement userLink_Lighting;
	/**
	 * @return the usersLink
	 */
	public WebElement getUsersLink(String environment, String mode,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			return isDisplayed(driver, userLink_Lighting, "Visibility", timeOut, "Users Link in "+mode);
		}else {
			return isDisplayed(driver, userLink_Classic, "Visibility", timeOut, "Users Link in "+mode);
		}
	}
	
	@FindBy(xpath="//iframe[contains(@title,'Salesforce - Enterprise Edition')]")
	private WebElement setupPageIframe;

	/**
	 * @return the userIframe
	 */
	public WebElement getSetUpPageIframe(int timeOut) {
		return isDisplayed(driver, setupPageIframe, "Visibility", timeOut, "active users iframe");
	}
	
	@FindBy(xpath="//td[@id='topButtonRow']//input[@name='save']")
	private WebElement createUserSaveBtn_Lighting;

	/**
	 * @return the createUserSaveBtn
	 */
	public WebElement getCreateUserSaveBtn_Lighting(int timeOut) {
		return isDisplayed(driver, createUserSaveBtn_Lighting, "Visibility", timeOut, "create user save button in lighting");
	}
	
	@FindBy(xpath="//input[@name='save']")
	private WebElement saveButtonClassic;
	
	@FindBy(xpath="//button[@title='Save']/span[text()='Save']")
	private WebElement saveButtonLighting;

	/**
	 * @return the saveButton
	 */
	public WebElement getSaveButton(String environment,String mode,int timeOut) {
		ThreadSleep(2000);
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, saveButtonClassic, "Visibility", timeOut, "Save Button Classic");
		}else{
			return isDisplayed(driver, saveButtonLighting, "Visibility", timeOut, "Save Button Lighting");
		}
	}
	
	@FindBy(xpath="//a[contains(@class,'menuTriggerLink slds-button')]//div[contains(@class,'tooltip-trigger uiTooltip')]")
	private WebElement settingIcon_Lighting;
	
	
	/**
	 * @return the settingTab_Lighting
	 */
	public WebElement getSettingLink_Lighting(int timeOut) {
		return isDisplayed(driver, settingIcon_Lighting, "Visibility", timeOut, "setting tab in lighting");
	}
	
	
	@FindBy(xpath="//li[@data-aura-class='uiMenuItem onesetupSetupMenuItem']/a[@title='Setup']")
	private WebElement setupLink_Lighting;
	
	/**
	 * @return the userMenuSetupLink
	 */
	public WebElement getUserMenuSetupLink(String environment, String mode,int timeOut) {
		WebElement ele=null;
		if(mode.equalsIgnoreCase(Mode.Classic.toString())) {
			ele=userMenuSetupLink;
		}else {
			ele= setupLink_Lighting;
		}
		return isDisplayed(driver, ele, "Visibility", timeOut, "Setup Link");
	}
	
	@FindBy(xpath="//a[@class='switch-to-lightning']")
	private WebElement switchToLightingLink;

	/**
	 * @return the switchToLightingLink
	 */
	public WebElement getSwitchToLightingLink(int timeOut) {
		return isDisplayed(driver, switchToLightingLink, "Visibility", timeOut, "Switch To Lighting Link");
	}
	
	public List<WebElement> getUserMenuTab_Lightning(){
		return FindElements(driver, "//span[contains(@class,'userProfileCardTriggerRoot')]//img[@alt='User']", "user menu tab in lightning");
	}
	
	@FindBy(xpath = "//a[contains(@href,'logout')]")
	private WebElement logout_Lightning;
	
	
	
	public WebElement getLogoutButton(String environment,String mode,int timeOut) {
		if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			return isDisplayed(driver, logout_Lightning, "Visibility", timeOut, "Logout in User menu");

		}else {
			return isDisplayed(driver, logout, "Visibility", timeOut, "Logout in User menu");
			
		}
	}
	
	
	@FindBy(xpath="//button[@title='Personalize your nav bar']")
	private WebElement personalizePencilIcon;

	/**
	 * @return the personalizePencilIcon
	 */


	public WebElement getPersonalizePencilIcon(String mode, int timeOut) {
		return isDisplayed(driver, personalizePencilIcon, "Visibility", timeOut, "Personalize Pencil Icon");

	}

	@FindBy(xpath="//button[contains(text(),'Add More Items')]")
	private WebElement addMoreItemsLink;

	/**
	 * @return the addMoreItemsLink
	 */
	public WebElement getAddMoreItemsLink(String mode, int timeOut) {
		return isDisplayed(driver, addMoreItemsLink, "Visibility", timeOut, "Add More Items");

	}

	@FindBy(xpath="//div[@role='list']//a[text()='All']")
	private WebElement allAddLink;

	/**
	 * @return the allAddLink
	 */
	public WebElement getAllAddLink(String mode, int timeOut) {
		return isDisplayed(driver, allAddLink, "Visibility", timeOut, "Add More Items");

	}
	
	@FindBy(xpath="//button/span[contains(text(),'Add')]")
	private WebElement addNavButton;

	/**
	 * @return the addNavButton
	 */
	public WebElement getAddNavButton(String mode, int timeOut) {
				return isDisplayed(driver, addNavButton, "Visibility", timeOut, "Add Nav Button");
	
	}
	
	@FindBy(xpath="//div[@data-aura-class='oneEditMyNav']/../following-sibling::div//button/span[contains(text(),'Save')]")
	private WebElement tabSaveButton;

	/**
	 * @return the addNavButton
	 */
	public WebElement getTabSaveButton(String mode, int timeOut) {
				return isDisplayed(driver, tabSaveButton, "Visibility", timeOut, "tAB Save Button");
	
	}
	
	public WebElement getAddTabCloseTheWindowCrossIcon() {
		String xpath="//button[@title='Close this window']";
		List<WebElement> lst = FindElements(driver, xpath, "add tab cross icon");
		if(!lst.isEmpty()) {
			for (int i = 0; i < lst.size(); i++) {
				if(isDisplayed(driver, lst.get(i), "visibility", 1, "cross icon")!=null) {
					return lst.get(i);
				}
			}
		}
		return null;
	}
	
	
	public WebElement getQucikSearchInSetupPage(String environment, String mode,int timeOut ) {
		WebElement ele = null;
		String xpath="";
		if(mode.equalsIgnoreCase(Mode.Classic.toString())) {
			xpath = "//input[@id='setupSearch']";
		}else {
			xpath="//input[@placeholder='Quick Find']";
		}
		ele=FindElement(driver, xpath, "search text box in "+mode, action.SCROLLANDBOOLEAN,30);
		return isDisplayed(driver,ele,"visibility",30,"quick search text box in "+mode);
	}
	
	@FindBy(id = "ImportedPackage_font")
	private WebElement installedPackageLink_Classic;

	/**
	 * @return the installedpackageLink
	 */
	@FindBy(xpath="//a[contains(@href,'/setup/ImportedPackage/home')]")
	private WebElement installedPackageLink_Lighting;
	
	public WebElement getInstalledPackageLink(String environment, String mode,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			return isDisplayed(driver, installedPackageLink_Lighting, "Visibility", timeOut, "Installed package in "+mode);
		}else {
			return isDisplayed(driver, installedPackageLink_Classic, "Visibility", timeOut, "Installed package in "+mode);
		}
	}
	
	
	@FindBy(xpath="//iframe[contains(@title,'Add Users:')]")
	private WebElement installedPackageParentFrame_Lighting;
	
	/**
	 * @return the installedPackageFrame_Lighting
	 */
	public WebElement getInstalledPackageParentFrame_Lighting(int timeOut) {
		return isDisplayed(driver, installedPackageParentFrame_Lighting, "Visibility", timeOut, " Installed Package Parent Frame in Lightning");
	}
	
	
	@FindBy(xpath="//ul[contains(@class,'tabBarItems slds-grid')]//span[contains(@class,'title slds-truncate')][contains(text(),'Object Manager')]")
	private WebElement objectManager_Lighting;

	/**
	 * @return the objectManage_Lighting
	 */
	public WebElement getObjectManager_Lighting(int timeOut) {
		return isDisplayed(driver, objectManager_Lighting, "Visibility", timeOut, "object manage");
	}
	
	@FindBy(xpath="//input[@id='globalQuickfind']")
	private WebElement quickSearchInObjectManager_Lighting;

	/**
	 * @return the quickSearchInObjectManager_Lighting
	 */
	public WebElement getQuickSearchInObjectManager_Lighting(int timeOut) {
		return isDisplayed(driver, quickSearchInObjectManager_Lighting, "Visibility", timeOut, "quick search in object manager in lighting");
	}
	
	@FindBy(xpath = "//li[@id='MoreTabs_Tab']")
	private WebElement moreTabIConClassic;
	
	@FindBy(xpath = "//span[contains(text(),'More')]/..//lightning-primitive-icon")
	private WebElement moreTabIConLighting;
	
	public WebElement getMoreTabIcon(String environment,String mode,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
		return isDisplayed(driver, moreTabIConClassic, "Visibility", timeOut, "More Tab ICon Classic");
		}else{
			return isDisplayed(driver, moreTabIConLighting, "Visibility", timeOut, "More Tab ICon Lighting");	
		}
	}
	
	@FindBy(xpath="//input[@name='new']")
	private WebElement newButtonClassic;

	@FindBy(xpath="//a[@title='New']")
	private WebElement newButtonLighting;
	
	/**
	 * @return the newButton
	 */
	public WebElement getNewButton(String environment,String mode,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, newButtonClassic, "Visibility", timeOut, "New Button Classic");	
		}else{
			return newButtonLighting;	
		}
		
	}
	
	public WebElement getNIMTabParentFrame_Lightning(PageName pageName){
		List<WebElement> lst = new ArrayList<WebElement>();
		lst = FindElements(driver, "//div[@data-aura-class='lafPageHost']//div[contains(@class,'iframe-paren')]/iframe", "NIM Page iFrame List");
		if(pageName.toString().equalsIgnoreCase(PageName.InstitutionsPage.toString())) {
			return lst.get(1);
		}else {
			if(!lst.isEmpty()) {
				for (int i = 0; i < lst.size(); i++) {
					if(isDisplayed(driver, lst.get(i), "visibility",2, "NIM Page iFrame")!=null) {
						return lst.get(i);
					}else {
						if(i==lst.size()-1) {
							return null;
						}
					}
				}
			}
		}
		return null;
	}
	
	@FindBy(xpath="//a[@title='Select List View']")
	private WebElement selectListIcon_Lighting;

	/**
	 * @return the selectListIcon_Lighting
	 */
	public WebElement getSelectListIcon(int timeOut) {
		return isDisplayed(driver, selectListIcon_Lighting, "Visibility", timeOut, "Select List Icon");
		}
	
	
	@FindBy(xpath="//input[contains(@name,'search-input')]")
	private WebElement searchIcon_Lighting;

	/**
	 * @return the searchIcon_Lighting
	 */
	public WebElement getSearchIcon_Lighting(int timeOut) {
		return isDisplayed(driver, searchIcon_Lighting, "Visibility", timeOut, "Search Icon Lighting");
	}

	 @FindBy(xpath="//div[@class='pbHeader']//input[@title='Edit']")
	 private WebElement editButton_Classic;
	 
	 @FindBy(xpath="//a[@title='Edit']")
	 private WebElement editButton_Lighting;
	
	 /**
	 * @return the editButton
	 */
	public WebElement getEditButton_lightning(String environment,String mode,int timeOut) {
		
		return isDisplayed(driver, editButton_Lighting, "Visibility", timeOut, "Edit Button Lighting");	
		
	}
	
	/**
	 * @param projectName
	 * @param pageName
	 * @param smaddl
	 * @param timeOut
	 * @return webelement for show more action item
	 */
	public WebElement actionDropdownElement(ShowMoreActionDropDownList smaddl, int timeOut) {
		String actionDropDown = smaddl.toString().replace("_", " ");
		String xpath ="//li/a[@title='"+actionDropDown+"']";
		
		
		return isDisplayed(driver, FindElement(driver, xpath, "show more action down arrow", action.SCROLLANDBOOLEAN, 10), "visibility", timeOut, actionDropDown);
	}
	
	/**
	 * @param projectName
	 * @param pageName
	 * @return  true if able to click o Show more action Icon
	 */
	public boolean clickOnShowMoreDropdownOnly() {
		String xpath = "";int i =1;
		WebElement ele=null;
		boolean flag = true;

		xpath="(//a[contains(@title,'more actions')])["+i+"]";
		
		ele=FindElement(driver, xpath, "show more action down arrow", action.SCROLLANDBOOLEAN, 30);
		if(click(driver, ele, "show more action ", action.SCROLLANDBOOLEAN)) {
			log(LogStatus.INFO, "clicked on show more actions icon", YesNo.No);

		}
		else {
			log(LogStatus.FAIL, "cannot click on show more actions icon", YesNo.Yes);
			flag = false;
		}
		return flag;
	}
	
	@FindBy(xpath="//h2[contains(text(),'Delete')]/../following-sibling::div//*[@title='Delete']")
	private WebElement deleteButtonOnDeletePopUp;


	/**
	 * @return the deleteButtonOnDeletePopUp
	 */

	public WebElement getDeleteButtonOnDeletePopUp(int timeOut) {
		return isDisplayed(driver, deleteButtonOnDeletePopUp, "Visibility", timeOut, "Delete Button on Delete Popup");
	}
	
	public WebElement getCheckboxOfRestoreItemOnRecycleBin( String restoreItem, int timeOut) {
        String xpath="//span[@title='"+restoreItem+"']/../../preceding-sibling::td//input/..";
        return isDisplayed(driver, FindElement(driver, xpath, "checkbox : "+restoreItem, action.SCROLLANDBOOLEAN, timeOut), "visibility", timeOut, "checkbox");
    }
	
	@FindBy(xpath = "//div[@title='Restore']")
	private WebElement restoreButtonOnRecycleBin;
	
	public WebElement getRestoreButtonOnRecycleBin(int timeOut) {
		return isDisplayed(driver, restoreButtonOnRecycleBin, "Visibility", timeOut, "restore Button On Recycle Bin");
	}
	
	/**
	 * @return the editButton
	 */
	
	@FindBy(xpath="//button[@name='Edit']")
	 private WebElement editButton_Lighting1;
	
	public WebElement getEditButton1(int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, editButton_Classic, "Visibility", timeOut, "Edit Button Classic");	
		}else{
			return isDisplayed(driver, editButton_Lighting1, "Visibility", timeOut, "Edit Button Lighting");	

		}
	}
	
	@FindBy(xpath="//iframe[@title='Navatar Investor Add-ons']")
	 private WebElement nimParentFrame;
	 /**
	  * @return the navatarInvestorAddOnFrame
	  */
	 public WebElement getNimParentFrame(int timeOut) {
	 	return isDisplayed(driver, nimParentFrame, "Visibility", timeOut, "NIM Parent Frame");
	 }
	 
	
	}
