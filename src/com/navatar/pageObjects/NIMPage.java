/**
 * 
 */
package com.navatar.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.navatar.generic.CommonLib.EditViewMode;
import com.navatar.generic.CommonLib.action;

import static com.navatar.generic.CommonLib.*;

import java.util.List;

/**
 * @author Parul Singh
 *
 */
public class NIMPage extends BasePageBusinessLayer {

	/**
	 * @param driver
	 */
	public NIMPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	/******************************************** Registration *************************************************/

	@FindBy(xpath = "//a[@title='Start']")
	private WebElement startButton;

	/**
	 * @return the startButton
	 */
	public WebElement getStartButton(int timeOut) {
		return isDisplayed(driver, startButton, "Visibility", timeOut, "Start Button");
	}

	@FindBy(xpath = "//a[@title='Next']")
	private WebElement nextButton;

	/**
	 * @return the nextButton
	 */
	public WebElement getNextButton(int timeOut) {
		return isDisplayed(driver, nextButton, "Visibility", timeOut, "next Button");
	}

	@FindBy(xpath = "//iframe[@id='theIframe']")
	private WebElement NIMTabFrame;

	/**
	 * @return the nIMTabFrame
	 */
	public WebElement getNIMTabFrame(int timeOut) {
		return isDisplayed(driver, NIMTabFrame, "Visibility", timeOut, "NIM Tab Frame");
	}
	
	@FindBy(xpath = "(//div[@data-aura-class='lafPageHost']//div[contains(@class,'iframe-paren')]/iframe)[2]")
	private WebElement NIMTabParentFrame_Lightning;
	
	/**
	 * @return the nIMTabFrame
	 */
	public WebElement getNIMTabParentFrame_Lightning(int timeOut) {
		return NIMTabParentFrame_Lightning;
	}
	
	public WebElement getNIMTabParentFrame_Lightning(){
		List<WebElement> lst = FindElements(driver, "//div[contains(@class,'windowViewMode-normal')]//iframe[@title='accessibility title']", "NIM Page iFrame List");
		WebElement ele =null;
		
			for (WebElement element:lst) {
				if(isDisplayed(driver, element, "visibility",2, "NIM Page iFrame")!=null) {
					ele=element;
					break;
//				}else {
//					if(i==lst.size()-1) {
//						return null;
//					}
				}
			}
		
		return ele;
	}
	
	
	@FindBy(xpath = "//input[@name='page:j_id10:j_id12']")
	private WebElement registrationSuccessfulCloseBtn;

	/**
	 * @return the registrationSuccessfulCloseBtn
	 */
	public WebElement getRegistrationSuccessfulCloseBtn(int timeOut) {
		return isDisplayed(driver, registrationSuccessfulCloseBtn, "Visibility", timeOut,
				"registration successfully close button");
	}

	@FindBy(xpath = "//div[@id='start_fancybox']//div[@class='head_popup']")
	private WebElement nIMRegisterPopupHeader;

	/**
	 * @return the nIMRegisterPopupHeader
	 */
	public WebElement getnIMRegisterPopupHeader(int timeOut) {
		return isDisplayed(driver, nIMRegisterPopupHeader, "Visibility", timeOut, "NIM Register Popup Header");
	}

	@FindBy(xpath = "//a[@title='Cancel']")
	private WebElement nimRegisterPopupCancelButton;

	/**
	 * @return the nimRegisterPopupCancelButton
	 */
	public WebElement getNimRegisterPopupCancelButton(int timeOut) {
		return isDisplayed(driver, nimRegisterPopupCancelButton, "Visibility", timeOut,
				"NIM Register Popup Cancel Button");
	}

	@FindBy(xpath = "//div[@id='start_fancybox']//a[@title='Close']")
	private WebElement nimRegisterPopupCloseIcon;

	/**
	 * @return the nimRegisterPopupCloseIcon
	 */
	public WebElement getNimRegisterPopupCloseIcon(int timeOut) {
		return isDisplayed(driver, nimRegisterPopupCloseIcon, "Visibility", timeOut, "NIM Register Popup Close Icon");
	}

	@FindBy(xpath = "//a[@title='Navatar Sales Team']")
	private WebElement navatarSalesTeamLink;

	/**
	 * @return the navatarSalesTeamLink
	 */
	public WebElement getNavatarSalesTeamLink(int timeOut) {
		return isDisplayed(driver, navatarSalesTeamLink, "Visibility", timeOut, "Navatar Sales Team Link");
	}

	@FindBy(xpath = "//div[@class='head_popup newdivpopup']")
	private WebElement registerPopupHeaderStep1Of2;

	/**
	 * @return the registerPopupStep1Of2
	 */
	public WebElement getRegisterPopupHeaderStep1Of2(int timeOut) {
		return isDisplayed(driver, registerPopupHeaderStep1Of2, "Visibility", timeOut, "Register Popup Step 1 of 2");
	}

	@FindBy(xpath = "//input[@id='User_FirstLogin:Form:FirstName']")
	private WebElement registerPopupFirstName;

	/**
	 * @return the registerPopupFirstName
	 */
	public WebElement getRegisterPopupFirstName(int timeOut) {
		return isDisplayed(driver, registerPopupFirstName, "Visibility", timeOut, "Register Popup first Name");
	}

	@FindBy(xpath = "//input[@id='User_FirstLogin:Form:LastName']")
	private WebElement registerPopupLastName;

	/**
	 * @return the registerPopupLastName
	 */
	public WebElement getRegisterPopupLastName(int timeOut) {
		return isDisplayed(driver, registerPopupLastName, "Visibility", timeOut, "Register Popup Last Name");
	}

	@FindBy(xpath = "//input[@id='User_FirstLogin:Form:UserName_Email']")
	private WebElement registerPopupEmail;

	/**
	 * @return the registerPopupEmail
	 */
	public WebElement getRegisterPopupEmail(int timeOut) {
		return isDisplayed(driver, registerPopupEmail, "Visibility", timeOut, "Register Popup Email");
	}

	@FindBy(xpath = "//a[@title='Back']")
	private List<WebElement> registerPopupBackButton;

	/**
	 * @return the registerPopupBackButton
	 */
	public List<WebElement> getRegisterPopupBackButton(int timeOut) {
		return (FindElements(driver, "//a[@title='Back']", "Back Button"));
	}

	@FindBy(xpath = "//div[@class='head_popup newdivpopup']//a[@title='Close']")
	private WebElement registerPopup1Of2CloseIcon;

	/**
	 * @return the registerPopup1Of2CloseIcon
	 */
	public WebElement getRegisterPopup1Of2CloseIcon(int timeOut) {
		return isDisplayed(driver, registerPopup1Of2CloseIcon, "Visibility", timeOut,
				"close Icon on register popup 1 of2");
	}

	@FindBy(xpath = "//span[@id='User_FirstLogin:Form:LastNameError']")
	private WebElement registerPopupLastNameRequiresErrorMessage;

	/**
	 * @return the registerPopupLastNameRequiresErrorMessage
	 */
	public WebElement getRegisterPopupLastNameRequiresErrorMessage(int timeOut) {
		return isDisplayed(driver, registerPopupLastNameRequiresErrorMessage, "Visibility", timeOut,
				"Register Popup Last Name Requires Error Message");
	}

	@FindBy(xpath = "//input[@title='Deny']")
	private WebElement denyButton;

	/**
	 * @return the denyButton
	 */
	public WebElement getDenyButton(int timeOut) {
		return isDisplayed(driver, denyButton, "Visibility", timeOut, "Deny Button");
	}

	@FindBy(xpath = "//p[text()='Remote_Error: access_denied']")
	private WebElement deniedAccessLabeltext;

	/**
	 * @return the deniedAccessLabeltext
	 */
	public WebElement getDeniedAccessLabeltext(int timeOut) {
		return isDisplayed(driver, deniedAccessLabeltext, "Visibility", timeOut, "Remote denied text");
	}

	@FindBy(xpath = "//input[@title='Allow']")
	private WebElement allowButton;

	/**
	 * @return the allowButton
	 */
	public WebElement getAllowButton(int timeOut) {
		return isDisplayed(driver, allowButton, "Visibility", timeOut, "Allow Button");
	}

	@FindBy(xpath = "(//form[@id='editPage']//p)[1]")
	private WebElement problemLoggingInErrorMessage;

	/**
	 * @return the problemLoggingInErrorMessage
	 */
	public WebElement getProblemLoggingInErrorMessage(int timeOut) {
		return isDisplayed(driver, problemLoggingInErrorMessage, "Visibility", timeOut,
				"Problem Logging in Error Message");
	}

	@FindBy(xpath = "//div[@class='head_popup newvrsn_admin']")
	private WebElement registerpopup2of2Header;

	/**
	 * @return the registerpopup2of2Header
	 */
	public WebElement getRegisterpopup2of2Header(int timeOut) {
		return isDisplayed(driver, registerpopup2of2Header, "Visibility", timeOut, "Register popup 2 of 2 header");
	}

	@FindBys(@FindBy(xpath = "//td[@class='padding_none border_none']//tr[@class='bg']//th"))
	private List<WebElement> registerPopupColoumnHeader;

	/**
	 * @return the registerPopupColoumnHeader
	 */
	public List<WebElement> getregisterPopupColoumnHeader(int timeOut) {
		if (checkElementsVisibility(driver, registerPopupColoumnHeader, "Register popup coloumn headers", timeOut)) {
			return registerPopupColoumnHeader;
		}
		return null;
	}

	@FindBy(xpath = "//a[@title='Complete']")
	private WebElement registerPopup2Of2CompleteButton;

	/**
	 * @return the registerPopup2Of2CompleteButton
	 */
	public WebElement getRegisterPopup2Of2CompleteButton(int timeOut) {
		return isDisplayed(driver, registerPopup2Of2CompleteButton, "Visibility", timeOut, "Complete Button");
	}

	@FindBy(xpath = "//div[@id='regconfirmpopdivIdd']//div[@class='head_popup']")
	private WebElement registrationSuccessfulPopupHeader;

	/**
	 * @return the registrationSuccessfulPopupHeader
	 */
	public WebElement getRegistrationSuccessfulPopupHeader(int timeOut) {
		return isDisplayed(driver, registrationSuccessfulPopupHeader, "Visibility", timeOut,
				"Registration successful popup Header");
	}

	@FindBy(xpath = "//div[@id='regconfirmpopdivIdd']//div[@class='formbox']//div")
	private WebElement registrationSuccessfullPopupMessage;

	/**
	 * @return the registrationSuccessfullPopupMessage
	 */
	public WebElement getRegistrationSuccessfullPopupMessage(int timeOut) {
		return isDisplayed(driver, registrationSuccessfullPopupMessage, "Visibility", timeOut,
				"Registarion successfull popup messgae");
	}

	@FindBy(xpath = "//div[@class='head_popup newnoneadmin']")
	private WebElement registerPopupHeaderForCRMUser;

	/**
	 * @return the registerPopupHeaderForCRMUser
	 */
	public WebElement getRegisterPopupHeaderForCRMUser(int timeOut) {
		return isDisplayed(driver, registerPopupHeaderForCRMUser, "Visibility", timeOut,
				"Register popup header for CRM User");
	}

	@FindBy(xpath = "//div[@class='head_popup newnoneadmin']//a[@title='Close']")
	private WebElement registerPopupCRMUserCloseIcon;

	/**
	 * @return the registerPopupCRMUserCloseIcon
	 */
	public WebElement getRegisterPopupCRMUserCloseIcon(int timeOut) {
		return isDisplayed(driver, registerPopupCRMUserCloseIcon, "Visibility", timeOut,
				"Register Popup Close Icon on CRM User Popup");
	}

	@FindBy(xpath="//span[contains(text(),'Firm Name')]/../..//td[2]//div")
	private WebElement firmName; 
	 
	/**
	 * @return the firmName
	 */
	public WebElement getFirmName(int timeOut) {
		return isDisplayed(driver, firmName, "Visibility", timeOut, "Firm Name");
	}

	/******************************************** * internal user tab*****************************/

	@FindBy(xpath = "//a[@title='Internal Users']")
	private WebElement internalUsersTab;

	/**
	 * @return the internalUsersTab
	 */
	public WebElement getInternalUsersTab(int timeOut) {
		return isDisplayed(driver, internalUsersTab, "Visibility", timeOut, "Internal User Tab");
	}

	@FindBy(xpath = "//*[@id='ConfirmAccess']/a[1]")
	private WebElement yesAccessButton;

	/**
	 * @return the yesButton
	 */
	public WebElement getYesAccessButton(int timeOut) {
		return isDisplayed(driver, yesAccessButton, "Visibility", timeOut, "Yes button in pop up");
	}

	@FindBy(xpath = "//*[@id='ConfirmAdmin']/a[1]")
	private WebElement yesAdminButton;

	/**
	 * @return the yesButton
	 */
	public WebElement getYesAdminButton(int timeOut) {
		return isDisplayed(driver, yesAdminButton, "Visibility", timeOut, "Yes button in pop up");
	}

	@FindBy(xpath = "//img[@alt='Edit']")
	private WebElement editIcon;

	/**
	 * @return the editIcon
	 */
	public WebElement getEditIcon(int timeOut) {
		return isDisplayed(driver, editIcon, "Visibility", timeOut, "Edit icon in NIM tab");
	}

	@FindBy(xpath = "(//div[@class='heading_box']//span)[1]")
	private WebElement landingPageText;

	/**
	 * @return the landingPageText
	 */
	public WebElement getLandingPageText(int timeOut) {
		return isDisplayed(driver, landingPageText, "Visibility", timeOut, "Landing Page Text");
	}

	/**
	 * @return the nimPageSideMenus
	 */
	public List<WebElement> getnimPageSideMenus() {
		return FindElements(driver, "//div[@id='left_sectionnNew']//li//a", "NIM Page Side Menus");
	}

	public List<WebElement> getCRMAdminColounHeaders() {
		return FindElements(driver, "//span[contains(@id,'grid_CRMAdmin-header-')]//span[@class='aw-item-text ']",
				"CRM Admin Coloumn Headers");
	}

	@FindBy(xpath = "//span[@id='page:form:licenseDiv']//span")
	private WebElement internalUsersLiceneMessage;

	/**
	 * @return the internalUsersLiceneMessage
	 */
	public WebElement getInternalUsersLiceneMessage(int timeOut) {
		return isDisplayed(driver, internalUsersLiceneMessage, "Visibility", timeOut, "Internal User License Message");
	}

	@FindBy(xpath = "//a[@class='IconPos']//img")
	private WebElement infoIconOnAdminLabel;

	/**
	 * @return the infoIconOnAdminLabel
	 */
	public WebElement getInfoIconOnAdminLabel(int timeOut) {
		return isDisplayed(driver, infoIconOnAdminLabel, "Visibility", timeOut, "Info Icon on Admin Label");
	}

	@FindBy(xpath = "//div[@class='tip-inner tip-bg-image']")
	private WebElement infoIcontoolTipMessage;

	/**
	 * @return the infoIcontoolTipMessage
	 */
	public WebElement getInfoIcontoolTipMessage(int timeOut) {
		return isDisplayed(driver, infoIcontoolTipMessage, "Visibility", timeOut, "Info Icon Message");
	}

	@FindBy(xpath = "//span[@id='grid_Users-view']//span[text()='User']//span[@class='aw-grid-sort ']")
	private WebElement sortingIconOnUserLabel;

	/**
	 * @return the sortingIconOnUserLabel
	 */
	public WebElement getSortingIconOnUserLabel(int timeOut) {
		return isDisplayed(driver, sortingIconOnUserLabel, "Visibility", timeOut, "Sorting Icon on User label");
	}

	@FindBy(xpath = "//span[@id='grid_Users-view']//span[text()='User']/../..")
	private WebElement userLabelInInternalUserHeaders;

	/**
	 * @return the userLabelInInternalUserHeaders
	 */
	public WebElement getUserLabelInInternalUserHeaders(int timeOut) {
		return isDisplayed(driver, userLabelInInternalUserHeaders, "Visibility", timeOut,
				"User label in Internal User Label");
	}

	public List<WebElement> getUsersListInInternalUsers() {
		return FindElements(driver, "//span[contains(@id,'grid_Users-cell-1-')]//span",
				"Users List In Internal Users Section");
	}

	@FindBy(xpath = "//a[@title='Go Back']")
	private WebElement goBackLink;

	/**
	 * @return the goBackLink
	 */
	public WebElement getGoBackLink(int timeOut) {
		return isDisplayed(driver, goBackLink, "Visibility", timeOut, "Go Back Link");
	}

	@FindBy(xpath = "//div[@id='ConfirmRemoval']//a[@title='Yes']")
	private WebElement userPermissionRemovalPopupYesButton;

	/**
	 * @return the userPermissionRemovalPopupYesButton
	 */
	public WebElement getUserPermissionRemovalPopupYesButton(int timeOut) {
		return isDisplayed(driver, userPermissionRemovalPopupYesButton, "Visibility", timeOut,
				"User Permission Removal Popup Yes button");
	}

	@FindBy(xpath = "//div[@id='ConfirmRemoval']//a[@title='No']")
	private WebElement userPermissionRemovalPopupNoButton;

	/**
	 * @return the userPermissionRemovalPopupNoButton
	 */
	public WebElement getUserPermissionRemovalPopupNoButton(int timeOut) {
		return isDisplayed(driver, userPermissionRemovalPopupNoButton, "Visibility", timeOut,
				"User Permission Removal Popup No button");
	}

	@FindBy(xpath = "//div[@id='ConRemove']//a[@title='Close']")
	private WebElement userPermissionRemovalPopupCloseButton;

	/**
	 * @return the userPermissionRemovalPopupCloseButton
	 */
	public WebElement getUserPermissionRemovalPopupCloseButton(int timeOut) {
		return isDisplayed(driver, userPermissionRemovalPopupCloseButton, "Visibility", timeOut,
				"User Permission Removal Popup Close button");
	}

	@FindBy(xpath = "//div[@id='ConRemove']//div[@class='head_popup']")
	private WebElement confirmUserPermissionRemovalPopupHeader;

	/**
	 * @return the confirmUserPermissionRemovalPopupHeader
	 */
	public WebElement getConfirmUserPermissionRemovalPopupHeader(int timeOut) {
		return isDisplayed(driver, confirmUserPermissionRemovalPopupHeader, "Visibility", timeOut,
				"Confirm User Permission Removal Popup Header ");
	}

	@FindBy(xpath = "//div[@id='ConfirmAccess']//a[@title='Yes']")
	private WebElement confirmUserPermissionAdditionPopupYesButton;

	/**
	 * @return the confirmUserPermissionAdditionPopupYesButton
	 */
	public WebElement getConfirmUserPermissionAdditionPopupYesButton(int timeOut) {
		return isDisplayed(driver, confirmUserPermissionAdditionPopupYesButton, "Visibility", timeOut,
				"Confirm User Permission addition popup Yes Button");
	}

	@FindBy(xpath = "//div[@id='ConfirmAccess']//a[@title='No']")
	private WebElement confirmUserPermissionAdditionPopupNoButton;

	/**
	 * @return the confirmUserPermissionAdditionPopupNoButton
	 */
	public WebElement getConfirmUserPermissionAdditionPopupNoButton(int timeOut) {
		return isDisplayed(driver, confirmUserPermissionAdditionPopupNoButton, "Visibility", timeOut,
				"Confirm User Permission addition popup No Button");
	}

	@FindBy(xpath = "//div[@id='ConAccess']//a[@title='Close']")
	private WebElement confirmUserPermissionAdditionPopupCloseButton;

	/**
	 * @return the confirmUserPermissionAdditionPopupCloseButton
	 */
	public WebElement getConfirmUserPermissionAdditionPopupCloseButton(int timeOut) {
		return isDisplayed(driver, confirmUserPermissionAdditionPopupCloseButton, "Visibility", timeOut,
				"Confirm User Permission addition popup close Button");
	}

	@FindBy(xpath = "//div[@id='ConAccess']//div[@class='head_popup']")
	private WebElement confirmUserPermissionAdditionpopupHeader;

	/**
	 * @return the confirmUserPermissionAdditionpopupHeader
	 */
	public WebElement getConfirmUserPermissionAdditionpopupHeader(int timeOut) {
		return isDisplayed(driver, confirmUserPermissionAdditionpopupHeader, "Visibility", timeOut,
				"Confirm User Permission Addition Popup Header");
	}

	@FindBy(xpath = "//div[@id='ConfirmAdmin']//a[@title='Yes']")
	private WebElement confirmAccessPopupYesButton;

	/**
	 * @return the confirmAccessPopupYesButton
	 */
	public WebElement getConfirmAccessPopupYesButton(int timeOut) {
		return isDisplayed(driver, confirmAccessPopupYesButton, "Visibility", timeOut,
				"Confirm Access popup Yes Button");
	}

	@FindBy(xpath = "//div[@id='ConfirmAdmin']//a[@title='No']")
	private WebElement confirmAccessPopupNoButton;

	/**
	 * @return the confirmAccessPopupNoButton
	 */
	public WebElement getConfirmAccessPopupNoButton(int timeOut) {
		return isDisplayed(driver, confirmAccessPopupNoButton, "Visibility", timeOut, "Confirm Access popup No Button");
	}

	@FindBy(xpath = "//div[@id='ConAdmin']//a[@title='Close']")
	private WebElement confirmAccessPopupCloseButton;

	/**
	 * @return the confirmAccessPopupCloseButton
	 */
	public WebElement getConfirmAccessPopupCloseButton(int timeOut) {
		return isDisplayed(driver, confirmAccessPopupCloseButton, "Visibility", timeOut,
				"Confirm Access popup Close button");
	}

	@FindBy(xpath = "//div[@id='ConAdmin']//div[@class='head_popup']")
	private WebElement confirmAccessPopupHeader;

	/**
	 * @return the confirmAccessPopupHeader
	 */
	public WebElement getConfirmAccessPopupHeader(int timeOut) {
		return isDisplayed(driver, confirmAccessPopupHeader, "Visibility", timeOut, "Confirm Access Popup No Header");
	}

	@FindBy(xpath = "//div[@id='ConAdmin']//div[@class='formbox']/left")
	private WebElement confirmAccessPopupErrorMessage;

	/**
	 * @return the confirmAccessPopupErrorMessage
	 */
	public WebElement getConfirmAccessPopupErrorMessage(int timeOut) {
		return isDisplayed(driver, confirmAccessPopupErrorMessage, "Visibility", timeOut,
				"Confirm Access Popup Error Message");
	}

	@FindBy(xpath = "//div[@id='permit_div']//div[@class='head_popup']")
	private WebElement insufficientPermissionHeader;

	/**
	 * @return the insufficientPermissionHeader
	 */
	public WebElement getInsufficientPermissionHeader(int timeOut, sideMenu menu) {
		String headXpath ="",popupXpath="//div[@class='head_popup']"; 
		if (menu==sideMenu.InternalUsers) {
			headXpath = "//div[@id='permit_div']";
		}
		else if(menu==sideMenu.ManageApprovals) {
			headXpath = "//div[@id='insufficientpriveledge']";
			
		}else if(menu==sideMenu.MyFirmProfile){
			headXpath = "//div[@class='InsufficientPermissions_fancybox FancyboxContainer ui-draggable']";
		}
		return isDisplayed(driver, FindElement(driver, headXpath + popupXpath, "Insufficient Permission header", action.SCROLLANDBOOLEAN, timeOut/2), "Visibility", timeOut/2,
				"Insufficient Permission header");
	}

	@FindBy(xpath = "//div[@id='permit_div']/p")
	private WebElement insufficientPermissionErrorMesage;

	/**
	 * @return the insufficientPermissionErrorMesage
	 */
	public WebElement getInsufficientPermissionErrorMesage(int timeOut, sideMenu menu) {
		String headXpath ="",popupXpath="/p"; 
		if (menu==sideMenu.InternalUsers) {
			headXpath = "//div[@id='permit_div']";
		}
		else if(menu==sideMenu.ManageApprovals) {
			headXpath = "//div[@id='insufficientpriveledge']";
		} else if(menu==sideMenu.MyFirmProfile){
			headXpath = "//div[@class='InsufficientPermissions_fancybox FancyboxContainer ui-draggable']";
		}
		return isDisplayed(driver, FindElement(driver, headXpath + popupXpath, "Insufficient Permission error message", action.SCROLLANDBOOLEAN, timeOut/2), "Visibility", timeOut/2,
				"Insufficient Permission error message");
	}

	@FindBy(xpath = "//div[@id='permit_div']//input[@title='Close']")
	private WebElement insufficientPermissionClosButton;

	/**
	 * @return the insufficientPermissionClosButton
	 */
	public WebElement getInsufficientPermissionClosButton(int timeOut,sideMenu menu) {
		String headXpath ="",popupXpath="//input[@title='Close']"; 
		if (menu==sideMenu.InternalUsers) {
			headXpath = "//div[@id='permit_div']";
		}
		else if(menu==sideMenu.ManageApprovals) {
			headXpath = "//div[@id='insufficientpriveledge']";
		} else if(menu==sideMenu.MyFirmProfile){
			headXpath = "//div[@class='InsufficientPermissions_fancybox FancyboxContainer ui-draggable']";
		}
		return isDisplayed(driver, FindElement(driver, headXpath + popupXpath, "Insufficient Permission Close button", action.SCROLLANDBOOLEAN, timeOut/2), "Visibility", timeOut/2,
				"Insufficient Permission Close button");
	}
	public WebElement getInsufficientPermissionCrossIcon(int timeOut,sideMenu menu) {
		String headXpath ="",popupXpath="//a[@title='Close']"; 
		if (menu==sideMenu.InternalUsers) {
			headXpath = "//div[@id='permit_div']";
		}
		else if(menu==sideMenu.ManageApprovals) {
			headXpath = "//div[@id='insufficientpriveledge']";
		}else if(menu==sideMenu.MyFirmProfile){
			headXpath = "//div[@class='InsufficientPermissions_fancybox FancyboxContainer ui-draggable']";
		}
		return isDisplayed(driver, FindElement(driver, headXpath + popupXpath, "Insufficient Permission cross icon", action.SCROLLANDBOOLEAN, timeOut/2), "Visibility", timeOut/2,
				"Insufficient Permission cross icon");
	}
	public List<WebElement> getAllInternalUsersCheckbox(){
		return FindElements(driver, "//span[@id='grid_Users-rows']//input[@type='checkbox']", "All Internal Users checkbox");
	}
	
	
	
	
	
	
	
	
	
	
	/********************************************* Folder Templates *************************************************************/

	@FindBy(xpath = "//span[@id='add0000']")
	private WebElement parentAddFolderBtn;
	
	
	/**
	 * @return the parentAddFolderBtn
	 */
	public WebElement getParentAddFolderBtn(int timeOut) {
		return isDisplayed(driver, parentAddFolderBtn, "Visibility", timeOut, "Parent Add Folder Btn");
	}

	@FindBy(xpath = "//a[@title='Folder Templates']")
	private WebElement folderTemplatetab;

	/**
	 * @return the folderTemplatetab
	 */
	public WebElement getFolderTemplatetab(int timeOut) {
		return isDisplayed(driver, folderTemplatetab, "Visibility", timeOut, "Folder Template Tab");
	}
	@FindBy(xpath = "//a[@id='btnCancelTempActiveTop']")
	private WebElement cancelFolderTemplateButton;
	
	
	/**
	 * @return the cancelFolderTemplateButton
	 */
	public WebElement getCancelFolderTemplateButton(int timeOut) {
		return isDisplayed(driver, cancelFolderTemplateButton, "Visibility", timeOut, "cancel Folder Template Button");
	}

	@FindBy(xpath="//a[@title='Add Folder Template']")
	private WebElement addFolderTemplateButton;
	
	/**
	 * @return the addFolderTemplateButton
	 */
	public WebElement getAddFolderTemplateButton(int timeOut) {
		return isDisplayed(driver, addFolderTemplateButton, "Visibility", timeOut, "Add folder template Button");
	}
	
	@FindBy(xpath="//input[@id='newname']")
	private WebElement folderTemplateNameTextBox;

	/**
	 * @return the folderTemplateNameTextBox
	 */
	public WebElement getFolderTemplateNameTextBox(int timeOut) {
		return isDisplayed(driver, folderTemplateNameTextBox, "Visibility", timeOut, "Folder template name text box");
	}
	
	@FindBy(xpath = "//div[contains(@class,'cancelTemplate')][2]//div[contains(text(),'cancel')]")
	private WebElement cancelTextAddFolder;
	
	
	/**
	 * @return the cancelTextAddFolder
	 */
	public WebElement getCancelTextAddFolder(int timeOut) {
		return isDisplayed(driver, cancelTextAddFolder, "Visibility", timeOut, "cancel text add folder");
	}
	@FindBy(xpath = "//div[contains(@class,'cancelTemplate')][2]//a[@title='Yes']")
	private WebElement yesBtnCancelWindowAddFolder;
	
	
	/**
	 * @return the yesBtnCancelWindowAddFolder
	 */
	public WebElement getYesBtnCancelWindowAddFolder(int timeOut) {
		return isDisplayed(driver, yesBtnCancelWindowAddFolder, "Visibility", timeOut, "yes Btn Cancel Window Add Folder");
	}

	
	@FindBy(xpath = "//div[contains(@class,'cancelTemplate')][2]//a[@title='No']")
	private WebElement noBtnCancelWindowAddFolder;
	
	
	/**
	 * @return the noBtnCancelWindowAddFolder
	 */
	public WebElement getNoBtnCancelWindowAddFolder(int timeOut) {
		return isDisplayed(driver, noBtnCancelWindowAddFolder, "Visibility", timeOut, "no Btn Cancel Window Add Folder");
	}

	@FindBy(xpath = "//div[contains(@class,'cancelTemplate')][2]//a[@title='Close']")
	private WebElement crossIconCancelWindowAddFolder;
	
	
	/**
	 * @return the crossIconCancelWindowAddFolder
	 */
	public WebElement getCrossIconCancelWindowAddFolder(int timeOut) {
		return isDisplayed(driver, crossIconCancelWindowAddFolder, "Visibility", timeOut, "cross icon add folder on cancel window");
	}
	@FindBy(xpath = "//div[@id='errMsgDupFolder']")
	private WebElement folderNameAlreadyExistsText;
	
	
	/**
	 * @return the folderNameAlreadyExistsText
	 */
	public WebElement getFolderNameAlreadyExistsText(int timeOut) {
		return isDisplayed(driver, folderNameAlreadyExistsText, "Visibility", timeOut, "folder Name Already Exists Text");
	}

	@FindBy(xpath = "//div[@id='idCharError']//a[@class='close_facnybox']")
	private WebElement folderNameAlreadyExistsCross;
	
	
	/**
	 * @return the folderNameAlreadyExistsCross
	 */
	public WebElement getFolderNameAlreadyExistsCross(int timeOut) {
		return isDisplayed(driver, folderNameAlreadyExistsCross, "Visibility", timeOut, "folder name already exists");
	}

	@FindBy(xpath = "//div[contains(@class,'DuplicacyAlert')]//div//div[contains(text(),'name')]")
	private WebElement errMessageDuplicateTemp;
	
	
	/**
	 * @return the errMessageDuplicateTemp
	 */
	public WebElement getErrMessageDuplicateTemp(int timeOut) {
		return isDisplayed(driver, errMessageDuplicateTemp, "Visibility", timeOut, "err Message Duplicate Temp");
	}
	
	@FindBy(xpath = "//div[@id='errMsgSecondGlobalFolder']/../..//div//a[@title='Close'][@class='btn_active']")
	private WebElement closeBtnErrMessageSecondCommon;
	
	
	/**
	 * @return the closeBtnErrMessageSecondCommon
	 */
	public WebElement getCloseBtnErrMessageSecondCommon(int timeOut) {
		return isDisplayed(driver, closeBtnErrMessageSecondCommon, "Visibility", timeOut, "close Btn Err Message Second Common");
	}

	/**
	 * @return the errMessageSecondCommon
	 */
	public WebElement getErrMessageSecondCommonOrInternal(FolderType folderType,int timeOut) {
		if (folderType == folderType.Common) {
			return isDisplayed(driver, FindElement(driver, "//div[@id='errMsgSecondGlobalFolder']", "folder for "+folderType.toString(), action.BOOLEAN, timeOut/2), "Visibility", timeOut, "err Message Second Common");
		}
		else {
			return isDisplayed(driver, FindElement(driver, "//div[@id='errMsgSecondInternalFolder']", "folder for "+folderType.toString(), action.BOOLEAN, timeOut/2), "Visibility", timeOut, "err Message Second Common");

		}
	}

	@FindBy(xpath = "//div[contains(@class,'DuplicacyAlert')]//a[@title='Close'][@class='close_facnybox']")
	private WebElement crossIconErrMessageDuplicateTemp;
	
	

	/**
	 * @return the crossIconErrMessageDuplicateTemp
	 */
	public WebElement getCrossIconErrMessageDuplicateTemp(int timeOut) {
		return isDisplayed(driver, crossIconErrMessageDuplicateTemp, "Visibility", timeOut, "cross Icon Err Message Duplicate Temp");
	}

	@FindBy(xpath = "//div[contains(@class,'DuplicacyAlert')]//a[@title='Close'][@class='btn_active']")
	private WebElement closeBtnErrMessageDuplicateTemp;
	
	
	/**
	 * @return the closeBtnErrMessageDuplicateTemp
	 */
	public WebElement getCloseBtnErrMessageDuplicateTemp(int timeOut) {
		return isDisplayed(driver, closeBtnErrMessageDuplicateTemp, "Visibility", timeOut, "close Btn Err Message Duplicate Temp");
	}

	@FindBy(xpath = "//input[@id='txtChildFolderName']")
	private WebElement addSubFolderTextBox;
	
	
	/**
	 * @return the addSubFolderTextBox
	 */
	public WebElement getAddSubFolderTextBox(int timeOut) {
		return isDisplayed(driver, addSubFolderTextBox, "Visibility", timeOut, "add sub folder text box");
	}

	@FindBy(xpath = "//div[@id='blankTextErr']")
	private WebElement errMessageBlankNameAddFolderTemp;
	
	/**
	 * @return the errMessageBlankNameAddFolderTemp
	 */
	public WebElement getErrMessageBlankNameAddFolderTemp(int timeOut) {
		return isDisplayed(driver, errMessageBlankNameAddFolderTemp, "Visibility", timeOut, "err Message Blank Name Add Folder Temp");
	}

	@FindBy(xpath = "//span[contains(@id,'templateGrid-cell-0')]//b//font")
	private WebElement nodatatodisplay;
	
	
	/**
	 * @return the nodatatodisplay
	 */
	public WebElement getNodatatodisplay(int timeOut) {
		return isDisplayed(driver, nodatatodisplay, "Visibility", timeOut, "no data to display");
	}

	@FindBy(xpath = "(//div[contains(@class,'AddFirstLevel')]//a[@title='Save'])[2]")
	private WebElement saveButtonAddSubFolder;
	
	
	/**
	 * @return the saveButtonAddSubFolder
	 */
	public WebElement getSaveButtonAddSubFolder(int timeOut) {
		return isDisplayed(driver, saveButtonAddSubFolder, "Visibility", timeOut, "save Button Add Sub Folder");
	}

	@FindBy(xpath = "(//div[contains(@class,'AddFirstLevel')]//a[@title='Close'])[2]")
	private WebElement crossIconAddSubFolder;
	
	
	/**
	 * @return the cancelButtonAddSubFolder
	 */
	public WebElement getCrossIconAddSubFolder(int timeOut) {
		return isDisplayed(driver, crossIconAddSubFolder, "Visibility", timeOut, "crossIcon Add Sub Folder");
	}

	@FindBy(xpath = "(//div[contains(@class,'AddFirstLevel')]//a[@title='Cancel'])[2]")
	private WebElement cancelButtonAddSubFolder;
	
	
	/**
	 * @return the cancelButtonAddSubFolder
	 */
	public WebElement getCancelButtonAddSubFolder(int timeOut) {
		return isDisplayed(driver, cancelButtonAddSubFolder, "Visibility", timeOut, "cancel Button Add Sub Folder");
	}

	@FindBy(xpath = "//div[@id='AddFolderPopup']//a[@title='Save']")
	private WebElement saveButtonAddFolder;
	
	
	/**
	 * @return the saveButtonAddFolder
	 */
	public WebElement getSaveButtonAddFolder(int timeOut) {
		return isDisplayed(driver, saveButtonAddFolder, "Visibility", timeOut, "save button add folder popup");
	}
	@FindBy(xpath = "//div[contains(@class,'saveTemplate')]//a[@title='Yes']")
	private WebElement saveYesBtnAddFolderTemplate;
	
	
	/**
	 * @return the saveYesBtnAddFolderTemplate
	 */
	public WebElement getSaveYesBtnAddFolderTemplate(int timeOut) {
		return isDisplayed(driver, saveYesBtnAddFolderTemplate, "Visibility", timeOut, "save Yes Btn Add Folder Template");
	}

	@FindBy(xpath = "//div[contains(@class,'saveTemplate')]//a[@title='No']")
	private WebElement saveNoBtnAddFolderTemplate;
	
	
	/**
	 * @return the saveNoBtnAddFolderTemplate
	 */
	public WebElement getSaveNoBtnAddFolderTemplate(int timeOut) {
		return isDisplayed(driver, saveNoBtnAddFolderTemplate, "Visibility", timeOut, "save No Btn Add Folder Template");
	}

	
	@FindBy(xpath = "//div[contains(@class,'saveTemplate')]//a[@title='Close']")
	private WebElement saveCrossBtnAddFolderTemplate;
	
	
	
	/**
	 * @return the saveCrossBtnAddFolderTemplate
	 */
	public WebElement getSaveCrossBtnAddFolderTemplate(int timeOut) {
		return isDisplayed(driver, saveCrossBtnAddFolderTemplate, "Visibility", timeOut, "save Cross Btn Add Folder Template");
	}

	@FindBy(xpath = "//div[contains(@class,'saveTemplate')]//div[contains(text(),'save')]")
	private WebElement saveTextQuesAddFolderTemp;
	
	
	/**
	 * @return the saveTextQuesAddFolderTemp
	 */
	public WebElement getSaveTextQuesAddFolderTemp(int timeOut) {
		return isDisplayed(driver, saveTextQuesAddFolderTemp, "Visibility", timeOut, "save text on add folder template");
	}

	@FindBy(xpath = "//span[@id='errRenFolderNameForGlobalCreate']")
	private WebElement errRenameFolderNameForGlobal;
	
	
	/**
	 * @return the errRenameFolderNameForGlobal
	 */
	public WebElement getErrRenameFolderNameForGlobal(int timeOut) {
		return isDisplayed(driver, errRenameFolderNameForGlobal, "Visibility", timeOut, "err Rename Folder Name For Global");
	}

	@FindBy(xpath = "//span[@id='errforInvalidCharFirst']")
	private WebElement invalidCharErrAddFolder;
	
	
	/**
	 * @return the invalidCharErrAddFolder
	 */
	public WebElement getInvalidCharErrAddFolder(int timeOut) {
		return isDisplayed(driver, invalidCharErrAddFolder, "Visibility", timeOut, "invalid Char Err Add Folder");
	}

	@FindBy(xpath = "//span[@id='err1stLvlFolder']")
	private WebElement errMessageAddFolder;
	
	
	/**
	 * @return the errMessageAddFolder
	 */
	public WebElement getErrMessageAddFolder(int timeOut) {
		return isDisplayed(driver, errMessageAddFolder, "Visibility", timeOut, "err Message Add Folder");
	}

	@FindBy(xpath = "//div[@id='AddFolderPopup']//a[@title='Cancel']")
	private WebElement cancelButtonAddFolder;
	
	
	/**
	 * @return the cancelButtonAddFolder
	 */
	public WebElement getcancelButtonAddFolder(int timeOut) {
		return isDisplayed(driver, cancelButtonAddFolder, "Visibility", timeOut, "cancel button add folder popup");
	}

	@FindBy(xpath = "//a[@id='demo-basicDF']//img")
	private WebElement tooltipAddFolder;
	
	
	/**
	 * @return the tooltipAddFolder
	 */
	public WebElement getTooltipAddFolder(int timeOut) {
		return isDisplayed(driver, tooltipAddFolder, "Visibility", timeOut, "tooltip of add folder");
	}

	@FindBy(xpath = "//div[contains(@class,'tip-bg-image')]")
	private WebElement tooltipMessageAddFolder;
	
	
	/**
	 * @return the tooltipMessageAddFolder
	 */
	public WebElement getTooltipMessageAddFolder(int timeOut) {
		return isDisplayed(driver, tooltipMessageAddFolder, "Visibility", timeOut, "tool tip message add folder");
	}

	@FindBy(xpath = "//input[@id='chkStandardFolder']")
	private WebElement standardFolderRadioBtnAddFolderWindow;
	
	
	
	/**
	 * @return the standardFolderRadioBtnAddFolderWindow
	 */
	public WebElement getStandardFolderRadioBtnAddFolderWindow(int timeOut) {
		return isDisplayed(driver, standardFolderRadioBtnAddFolderWindow, "Visibility", timeOut, "Standard Folder Radio Btn on Add Folder Window");
	}

	@FindBy(xpath = "//span[contains(text(),'Folders (100 max)')]")
	private WebElement folders100max;
	
	
	/**
	 * @return the folders100max
	 */
	public WebElement getFolders100max(int timeOut) {
		return isDisplayed(driver, folders100max, "Visibility", timeOut, "folder max text");
	}

	@FindBy(xpath = "//div[@id='AddFolderPopup']/div[@class='head_popup']")
	private WebElement addFolderText;
	
	
	/**
	 * @return the addFolderText
	 */
	public WebElement getAddFolderText(int timeOut) {
		return isDisplayed(driver, addFolderText, "Visibility", timeOut, "add folder text");
	}
	@FindBy(xpath = "//div[@id='subFolderParent']")
	private WebElement folderNameInSubFolderWindow;
	
	
	/**
	 * @return the folderNameInSubFolderWindow
	 */
	public WebElement getFolderNameInSubFolderWindow(int timeOut) {
		return isDisplayed(driver, folderNameInSubFolderWindow, "Visibility", timeOut, "folder Name In Sub Folder Window");
	}

	@FindBy(xpath = "(//div[contains(@class,'AddFirstLevelFolder')]/div[@class='head_popup'])[2]")
	private WebElement addSubFolderText;
	
	

	/**
	 * @return the addSubFolderText
	 */
	public WebElement getAddSubFolderText(int timeOut) {
		return isDisplayed(driver, addSubFolderText, "Visibility", timeOut, "add Sub Folder Text");
	}

	@FindBy(xpath = "//a[@id='btnCancelTempActiveTop']")
	private WebElement folderTempAddCancelBtn;
	
	
	/**
	 * @return the folderTempAddCancelBtn
	 */
	public WebElement getFolderTempAddCancelBtn(int timeOut) {
		return isDisplayed(driver, folderTempAddCancelBtn, "Visibility", timeOut, "folder template add cancel button");
	}

	@FindBy(xpath = "//textarea[@id='page:form:newdescviewmode']")
	private WebElement textboxDescFolderTempPage;
	
	
	/**
	 * @return the textboxDescFolderTempPage
	 */
	public WebElement getTextboxDescFolderTempPage(int timeOut) {
		return isDisplayed(driver, textboxDescFolderTempPage, "Visibility", timeOut, "textbox Desc Folder Temp Page");
	}

	@FindBy(xpath="//textarea[@id='page:form:newdesc']")
	private WebElement folderTemplateDescriptionTextBox;

	/**
	 * @return the folderTemplateDescriptionTextBox
	 */
	public WebElement getFolderTemplateDescriptionTextBox(int timeOut) {
		return isDisplayed(driver, folderTemplateDescriptionTextBox, "Visibility", timeOut, "Folder template description text box");
	}
	
	@FindBy(xpath="//a[@id='btnSaveTempActiveTop']")
	private WebElement folderTemplateSaveButton;

	/**
	 * @return the folderTemplateSaveButton
	 */
	public WebElement getFolderTemplateSaveButton(int timeOut) {
		return isDisplayed(driver, folderTemplateSaveButton, "Visibility", timeOut, "Folder template save button");
	}
	
	@FindBy(xpath="//a[@onclick='saveTemp();']")
	private WebElement saveConfirmationYesButton;

	/**
	 * @return the saveConfirmationYesButton
	 */
	public WebElement getSaveConfirmationYesButton(int timeOut) {
		return isDisplayed(driver, saveConfirmationYesButton, "Visibility", timeOut, "Save confirmation Yes button");
	}
		
	@FindBy(xpath = "//span[contains(text(),'Folder Templates')]")
	private WebElement folderTemplatesHead;
	
	/**
	 * @return the folderTemplatesHead
	 */
	public WebElement getFolderTemplatesHead(int timeOut) {
		return isDisplayed(driver, folderTemplatesHead, "Visibility", timeOut, "Folder templates heading on NIM page");
	}
	
	@FindBy(xpath = "//select[@id='page:form:temptype']")
	private WebElement dropdownTempType;

	/**
	 * @return the dropdownTempType
	 */
	public WebElement getDropdownTempType(int timeOut) {
		return isDisplayed(driver, dropdownTempType, "Visibility", timeOut, "template dropdown");
	}

	@FindBy(xpath = "//a[@title='Delete Template']")
	private WebElement deleteTempBtn;
	
	
	/**
	 * @return the deleteTempBtn
	 */
	public WebElement getDeleteTempBtn(int timeOut) {
		return isDisplayed(driver, deleteTempBtn, "Visibility", timeOut, "delete temp button");
	}
	
	@FindBy(xpath = "//div[contains(@class,'deleteTemplate')]//a[@title='Yes']")
	private WebElement deleteTemplateYesBtn;
	
	

	/**
	 * @return the deleteTemplateYesBtn
	 */
	public WebElement getDeleteTemplateYesBtn(int timeOut) {
		return isDisplayed(driver, deleteTemplateYesBtn, "Visibility", timeOut, "yes button");
	}
	@FindBy(xpath = "//div[@id='recordscount']")
	private WebElement recordsCount;
	
	

	/**
	 * @return the recordsCount
	 */
	public WebElement getRecordsCount(int timeOut) {
		return isDisplayed(driver, recordsCount, "Visibility", timeOut, "records count text");
	}
	
	@FindBy(xpath="//span[contains(@id,'templateGrid-cell-0')]//b//font")
	private WebElement defaultFolderTemplateMessage;
	
	/*
	 * @return defaultFolderTemplateMessage
	 */
	public WebElement getdefaultFolderTemplateMessage(int timeout){
		return isDisplayed(driver, defaultFolderTemplateMessage, "visibility", timeout, "No data to display.");
	}
	
	/*
	 * @return folder template link
	 */
	public WebElement getfoldertemplatelink(String TemplateName,int timeout){
		
		return isDisplayed(driver, FindElement(driver,
				"//span[@id='templateGrid-rows']//a[@title='"+TemplateName+"']", "Template Name",
				action.SCROLLANDBOOLEAN, timeout), "visibility", timeout, "Template Name");
	}
	
	@FindBy(xpath="//a[@id='editIcon']//img")
	private WebElement foldertemplateediticon;
	
	/*
	 * @return folder template edit icon
	 */
	public WebElement getFolderTemplateEditIcon(int timeout){
		return isDisplayed(driver, foldertemplateediticon, "visibility", timeout,"Folder template edit icon");
	}
	
	@FindBy(xpath="//div[@id='editpermit']//div[1]")
	private WebElement foldertemplateerrorheader;
	
	/*
	 * @return folder template error header
	 */
	public WebElement getFolderTemplateErrorPermissionHeader(int timeout){
		return isDisplayed(driver, foldertemplateerrorheader, "visibility", timeout, "folder template error header");
	}
	
	@FindBy(xpath="//div[@id='deletetpermit']//div[1]")
	private WebElement foldertemplateerrorheaderdelete;
	
	/*
	 * @return folder template error header delete
	 */
	public WebElement getFolderTemplateErrorPermissionHeaderdelete(int timeout){
		return isDisplayed(driver, foldertemplateerrorheaderdelete, "visibility", timeout, "folder template error header");
	}
	
	@FindBy(xpath="(//div[@id='editpermit']//a[@title='Close'])[1]")
	private WebElement foldertemplateerrorheadercrossicon;
	
	/*
	 * @return folder template error popup cross icon
	 */
	public WebElement getFolderTemplateErrorPermissionHeadercrossicon(int timeout){
		return isDisplayed(driver, foldertemplateerrorheadercrossicon, "visibility", timeout, "folder template error header");
	}
	
	@FindBy(xpath="(//div[@id='deletetpermit']//a[@title='Close'])[1]")
	private WebElement foldertemplateerrorheadercrossicondelete;
	
	/*
	 * @return folder template error popup cross icon delete
	 */
	public WebElement getFolderTemplateErrorPermissionHeadercrossicondelete(int timeout){
		return isDisplayed(driver, foldertemplateerrorheadercrossicondelete, "visibility", timeout, "folder template error header");
	}
	
	@FindBy(xpath="(//div[@id='editpermit']//a[@title='Close'])[2]")
	private WebElement foldertemplateerrorheaderclosebutton;
	
	/*
	 * @return folder template error popup close button
	 */
	public WebElement getFolderTemplateErrorPermissionHeaderclosebutton(int timeout){
		return isDisplayed(driver, foldertemplateerrorheaderclosebutton, "visibility", timeout, "folder template error header");
	}
	
	@FindBy(xpath="(//div[@id='deletetpermit']//a[@title='Close'])[2]")
	private WebElement foldertemplateerrorheaderclosebuttondelete;
	
	/*
	 * @return folder template error popup close button delete
	 */
	public WebElement getFolderTemplateErrorPermissionHeaderclosebuttondelete(int timeout){
		return isDisplayed(driver, foldertemplateerrorheaderclosebuttondelete, "visibility", timeout, "folder template error header");
	}
	
	@FindBy(xpath="//div[@id='editpermit']//p")
	private WebElement foldertemplateerrormessage;
	
	/*
	 * @return folder template error message
	 */
	public WebElement getfoldertemplateerrormessage(int timeout){
		return isDisplayed(driver, foldertemplateerrormessage, "visibility", timeout, "folder template error header");
	}
	
	@FindBy(xpath="//div[@id='deletetpermit']//p")
	private WebElement foldertemplateerrormessagedelete;
	
	/*
	 * @return folder template error message delete
	 */
	public WebElement getfoldertemplateerrormessagedelete(int timeout){
		return isDisplayed(driver, foldertemplateerrormessagedelete, "visibility", timeout, "folder template error header");
	}
	
	@FindBy(xpath = "//div[contains(@class,'deleteTemplate')]//a[@title='No']")
	private WebElement deleteTemplateNoBtn;
	
	/**
	 * @return the deleteTemplateNoBtn
	 */
	public WebElement getDeleteTemplateNoBtn(int timeOut) {
		return isDisplayed(driver, deleteTemplateNoBtn, "Visibility", timeOut, "yes button");
	
	}
	
	@FindBy(xpath = "//div[contains(@class,'deleteTemplate')]//a[@title='Close']")
	private WebElement deleteTemplatecrossIcon;
	
	/**
	 * @return the deleteTemplatecross icon
	 */
	public WebElement getdeleteTemplatecrossIcon(int timeOut) {
		return isDisplayed(driver, deleteTemplatecrossIcon, "Visibility", timeOut, "Cross icon");
	
	}
	
	@FindBy(xpath = "(//div[contains(@class,'deleteTemplate')])/div[2]/div")
	private WebElement deleteTemplateConfirmationMsg;
	
	/**
	 * @return the deleteTemplate Confirmation message.
	 */
	public WebElement getdeleteTemplateConfirmationMsg(int timeOut) {
		return isDisplayed(driver, deleteTemplateConfirmationMsg, "Visibility", timeOut, "Confirmation Message");
	}
	
	@FindBy(xpath = "(//div[contains(@class,'deleteTemplate')])/div[1]")
	private WebElement deleteTemplateConfirmationHeader;
	
	/**
	 * @return the deleteTemplate Confirmation header.
	 */
	public WebElement getdeleteTemplateConfirmationHeader(int timeOut) {
		return isDisplayed(driver, deleteTemplateConfirmationHeader, "Visibility", timeOut, "Confirmation header");
	}
	
	@FindBy(xpath="(//span[@title='Created On']//span)[4]/span")
	private WebElement createdOnSortingIcon;
	
	/*
	 * return sorting icon folder template.
	 */
	public WebElement getcreatedOnSortingIcon(int timeOut){
		return isDisplayed(driver, createdOnSortingIcon, "visibility", timeOut, "Created On Sorting icon");
	}
	
	@FindBy(xpath="(//span[@title='Created By']//span)[4]/span")
	private WebElement createdBySortingIcon;
	
	/*
	 * return sorting icon (Created By)folder template.
	 */
	public WebElement getcreatedBySortingIcon(int timeOut){
		return isDisplayed(driver, createdBySortingIcon, "visibility", timeOut, "Created By Sorting icon");
	}
	
	@FindBy(xpath="//span[@title='Created By']")
	private WebElement createdByColumnHeader;
	
	/*
	 * return Created By Header.
	 */
	public WebElement getcreatedByColumnHeader(int timeOut){
		return isDisplayed(driver, createdByColumnHeader, "visibility", timeOut, "Created By Sorting icon");
	}
	
	
	
	@FindBy(xpath="(//span[@title='Template Name']//span)[4]/span")
	private WebElement templateNameSortingIcon;
	
	/*
	 * return sorting icon (Template Name)folder template.
	 */
	public WebElement getTemplateNameSortingIcon(int timeOut){
		return isDisplayed(driver, templateNameSortingIcon, "visibility", timeOut, "Template Name Sorting icon");
	}
	
	@FindBy(xpath="//span[@title='Template Name']")
	private WebElement templateNameHeader;
	
	/*
	 * return template Name Header.
	 */
	public WebElement gettemplateNameHeader(int timeOut){
		return isDisplayed(driver, templateNameHeader, "visibility", timeOut, "Template Name Sorting icon");
	}
	
	
	/*
	 * return dynamic folder element on Folder Template
	 */
	public WebElement getFolderElement(String foldername,int timeout){
		return isDisplayed(driver, FindElement(driver, "//label[contains(text(),'"+foldername+"')]", "Folder Name", action.SCROLLANDBOOLEAN, timeout),foldername+"Folder", timeout, foldername+"Folder");
	}
	
	
	/*renameFolder elements*/
	
	@FindBy(xpath="(//div[contains(@class,'RenameFolder')]/div)[1]")
	private WebElement renameFolderHeader;
	
	/*
	 * return rename folder header.
	 */
	public WebElement getRenameFolderHeader(int timeout){
		return isDisplayed(driver, renameFolderHeader, "visibility", timeout, "Rename Folder Header");
	}
	
	@FindBy(xpath="(//div[contains(@class,'RenameFolder')]/div)[1]/a")
	private WebElement renameFolderCrossIcon;
	
	/*
	 * return rename folder cross icon.
	 */
	public WebElement getRenameFolderCrossIcon(int timeout){
		return isDisplayed(driver, renameFolderCrossIcon, "visibility", timeout, "Rename Folder Cross icon");
	}
	
	@FindBy(xpath="(//div[contains(@class,'RenameFolder')]/div)[3]/a[@title='Cancel']")
	private WebElement renameFolderCloseButton;
	
	/*
	 * return rename folder close button.
	 */
	public WebElement getRenameFolderCloseButton(int timeout){
		return isDisplayed(driver, renameFolderCloseButton, "visibility", timeout, "Rename Folder Close button");
	}
	
	@FindBy(xpath="(//div[contains(@class,'RenameFolder')]/div)[3]/a[@title='Save']")
	private WebElement renameFolderSaveButton;
	
	/*
	 * return rename folder Save button.
	 */
	public WebElement getRenameFolderSaveButton(int timeout){
		return isDisplayed(driver, renameFolderSaveButton, "visibility", timeout, "Rename Folder Save button");
	}
	

	@FindBy(xpath="(//div[contains(@class,'RenameFolder')]/div)[2]//td//div/b")
	private WebElement renameFolderNameText;
	
	/*
	 * return rename folder 'Folder Name:' text.
	 */
	public WebElement getRenameFolderNameText(int timeout){
		return isDisplayed(driver, renameFolderNameText, "visibility", timeout, "Folder Name Text");
	}
	
	
	@FindBy(xpath="//input[@id='txtRenameFolder']")
	private WebElement renameFolderTextBoxValue;
	
	/*
	 * return rename folder 'Folder Name:' textbox value.
	 */
	public WebElement getRenameFolderTextBoxValue(int timeout){
		return isDisplayed(driver, renameFolderTextBoxValue, "visibility", timeout, "Folder Name Text Box");
	}
	
	
	/*
	 * return dynamic delete icon on Folder Template
	 */
	public WebElement getFolderDeleteIcon(String foldername,int timeout){
		return isDisplayed(driver, FindElement(driver, "//label[contains(text(),'"+foldername+"')]/lable[3]", "Folder Name", action.SCROLLANDBOOLEAN, timeout),foldername+"Folder", timeout, foldername+"Folder");
	}
	
	@FindBy(xpath="//div[@id='idConfirmDeletion']/div[1]")
	private WebElement deleteFolderHeader;
	
	/*
	 * return delete folder header.
	 */
	public WebElement getDeleteFolderHeader(int timeout){
		return isDisplayed(driver, deleteFolderHeader, "visibility", timeout, "Delete Folder Header");
	}
	
	@FindBy(xpath="//div[@id='idConfirmDeletion']/div[1]/a")
	private WebElement deleteFolderCrossIcon;
	
	/*
	 * return delete folder cross icon.
	 */
	public WebElement getDeleteFolderCrossIcon(int timeout){
		return isDisplayed(driver, deleteFolderCrossIcon, "visibility", timeout, "Delete Folder Cross icon");
	}
	
	@FindBy(xpath="//div[@id='idConfirmDeletion']/div[1]/a")
	private WebElement deleteFolderCloseButton;
	
	/*
	 * return delete folder close button.
	 */
	public WebElement getDeleteFolderCloseButton(int timeout){
		return isDisplayed(driver, deleteFolderCloseButton, "visibility", timeout, "Delete Folder Close button");
	}
	
	@FindBy(xpath="//div[@id='idConfirmDeletion']/div[3]/a[@title='Yes']")
	private WebElement deleteFolderYesButton;
	
	/*
	 * return delete folder Yes button.
	 */
	public WebElement getDeleteFolderYesButton(int timeout){
		return isDisplayed(driver, deleteFolderYesButton, "visibility", timeout, "Delete Folder Yes button");
	}
	
	@FindBy(xpath="//div[@id='idConfirmDeletion']/div[3]/a[@title='No']")
	private WebElement deleteFolderNoButton;
	
	/*
	 * return delete folder No button.
	 */
	public WebElement getDeleteFolderNoButton(int timeout){
		return isDisplayed(driver, deleteFolderNoButton, "visibility", timeout, "Delete Folder No button");
	}
	
	@FindBy(xpath="//div[@id='idConfirmDeletion']/div[2]/div")
	private WebElement deleteFolderConfirmationMessage;
	
	/*
	 * return delete Folder Confirmation Message.
	 */
	public WebElement getDeleteFolderConfirmationMessage(int timeout){
		return isDisplayed(driver, deleteFolderConfirmationMessage, "visibility", timeout, "Delete Folder confirmation message");
	}
	
	
	
	
	
	
	/********************************************** Profiles ********************************************************************************/
	@FindBy(xpath = "//input[@name='pg:frm:confname']")
	private WebElement myProfileFirstName;

	/**
	 * @return the myProfileFirstName
	 */
	public WebElement getMyProfileFirstName(int timeOut) {
		return isDisplayed(driver, myProfileFirstName, "Visibility", timeOut, "My Profile First Name");
	}

	@FindBy(xpath = "//input[@name='pg:frm:conlname']")
	private WebElement myProfileLastName;

	/**
	 * @return the myProfileLastName
	 */
	public WebElement getMyProfileLastName(int timeOut) {
		return isDisplayed(driver, myProfileLastName, "Visibility", timeOut, "My Profile Last Name");
	}

	@FindBy(xpath="//input[@name='pg:frm:conphone']")
	private WebElement myProfilePhone;
	
	
	/**
	 * @return the myProfilePhone
	 */
	public WebElement getMyProfilePhone(int timeOut) {
		return isDisplayed(driver, myProfilePhone, "Visibility", timeOut, "my profile phone text box");
	}
	
	@FindBy(xpath="//input[@name='pg:frm:ConTitle']")
	private WebElement myProfileTitle;

	/**
	 * @return the myProfileTitle
	 */
	public WebElement getMyProfileTitle(int timeOut) {
		return isDisplayed(driver, myProfileTitle, "Visibility", timeOut, "my profile title text box");
	}
	
	@FindBy(xpath="//textarea[@name='pg:frm:ConmailingStreet']")
	private WebElement myProfileMailingStreet;
	
	/**
	 * @return the myProfileMailingStreet
	 */
	public WebElement getMyProfileMailingStreet(int timeOut) {
		return isDisplayed(driver, myProfileMailingStreet, "Visibility", timeOut, "my profile mailing street");
	}
	
	@FindBy(xpath="//input[@name='pg:frm:ConmailingCity']")
	private WebElement myprofileMailingcity;

	/**
	 * @return the myprofileMailingcity
	 */
	public WebElement getMyprofileMailingcity(int timeOut) {
		return isDisplayed(driver, myprofileMailingcity, "Visibility", timeOut, "my profile mailing city");
	}
	
	@FindBy(xpath="//input[@name='pg:frm:ConmailingState']")
	private WebElement myProfilemailingState;

	/**
	 * @return the myProfilemailingState
	 */
	public WebElement getMyProfilemailingState(int timeOut) {
		return isDisplayed(driver, myProfilemailingState, "Visibility", timeOut, "my profile mailing state");
	}
	
	@FindBy(xpath="//input[@name='pg:frm:ConmailingPostalCode']")
	private WebElement myProfilemailingZip;

	/**
	 * @return the myProfilemailingZip
	 */
	public WebElement getMyProfilemailingZip(int timeOut) {
		return isDisplayed(driver, myProfilemailingZip, "Visibility", timeOut, "my profile mailing zip");
	}
	
	@FindBy(xpath="//input[@name='pg:frm:ConmailingCountry']")
	private WebElement myProfileMailingCountry;
	/**
	 * @return the myProfileMailingCountry
	 */
	public WebElement getMyProfileMailingCountry(int timeOut) {
		return isDisplayed(driver, myProfileMailingCountry, "Visibility", timeOut, "my profile mailing country");
	}

	@FindBy(xpath = "//div[@class='left_myprofile_div']//a[@title='Save']")
	private WebElement myProfileSaveButton;

	/**
	 * @return the myProfileSaveButton
	 */
	public WebElement getMyProfileSaveButton(int timeOut) {
		return isDisplayed(driver, myProfileSaveButton, "Visibility", timeOut, "My Profile Save Button");
	}
	
	@FindBy(xpath="//input[@id='pg:frm:ConFacebook']")
	private WebElement myProfileFaceBookTextBox;
	
	
	/**
	 * @return the myProfileFaceBookTextBox
	 */
	public WebElement getMyProfileFaceBookTextBox(int timeOut) {
		return isDisplayed(driver, myProfileFaceBookTextBox, "Visibility", timeOut, "my profile facebook text box");
	}
	
	@FindBy(xpath="//input[@id='pg:frm:ConlinkedIn']")
	private WebElement myProfileLinkeDinTextBox;

	/**
	 * @return the myProfileLinkeDinTextBox
	 */
	public WebElement getMyProfileLinkeDinTextBox(int timeOut) {
		return isDisplayed(driver, myProfileLinkeDinTextBox, "Visibility", timeOut, "my profile linkedin text box");
	}

	@FindBy(xpath = "(//div[@class='BasicInformation_box']//td[contains(text(),'Name')]/../td[@class='td2'])[1]")
	private WebElement myProfileNameInViewMode;

	/**
	 * @return the myProfileNameInViewMode
	 */
	public WebElement getMyProfileNameInViewMode(int timeOut) {
		return isDisplayed(driver, myProfileNameInViewMode, "Visibility", timeOut, "My Profile Name In View Mode");
	}
	
	
	/******************************************* Avinash **********************/
	@FindBy(xpath = "//div[@id='home']/div/div/span")
	private WebElement profileheader;
	
	/*
	 * return profile header on my profile page.
	 */
	public WebElement getprofileheader(int timeout){
		return isDisplayed(driver, profileheader, "visibility", timeout, "Profile header on My Profile page");
	}
	
	@FindBy(xpath = "//a[@title='Edit']/img")
	private WebElement myprofileediticon;
	
	public WebElement getmyprofileediticon(int timeout)
	{
		return isDisplayed(driver, myprofileediticon, "Visibility", timeout, "My Profile Edit icon");
	}
	
	@FindBy(xpath = "(//a[@title='My Profile'])[2]")
	private WebElement myprofilelink;

	/*
	 * return the myprofilelink
	 */
	public WebElement getmyprofilelink(int timeout)	{
		return isDisplayed(driver, myprofilelink, "Visibility", timeout, "my profile link");
	}
	
	@FindBy(xpath = "(//a[contains(@title,'My Firm')])[2]")
	private WebElement myfirmprofilelink;
	
	/*
	 * return the myfirmprofilelink
	 */
	public WebElement getmyfirmprofilelink(int timeout)
	{
		return isDisplayed(driver, myfirmprofilelink, "Visibility", timeout, "my profile link");
	}
	
	@FindBy (xpath = "(//span[@class='head'])[2]")
	private WebElement myprofiletext;
	
	/*
	 * return the my profiletext
	 */
	public WebElement getmyprofiletext(int timeout)
	{
		return isDisplayed(driver, myprofiletext, "Visibility", timeout, "My profile text at My Profile page");
	}			
			
	@FindBy (xpath = "//div[contains(@class,'BasicInformation_box')]/div")	
	private WebElement basicinfotext;
	
	/*
	 * return the basic information text
	 */
	public WebElement getbasicinfotext(int timeout)
	{
		return isDisplayed(driver, basicinfotext, "Visibility", timeout,"Basic Information label");
	}
	
	@FindBy (xpath = "(//div[contains(@class,'BasicInformation_box border_none')]//div//div)[1]")	
	private WebElement logininfotext;
	
	/*
	 * return the login information text
	 */
	public WebElement getlogininfotext(int timeout)
	{
		return isDisplayed(driver, logininfotext, "Visibility", timeout,"Basic Information label");
	}
	
	/*
	 * return all My Profile Lables
	 */
	public List<WebElement> myprofilelables()
	{
		return FindElements(driver, "//td[@class='td1']", "myprofilelables");
	}
	
	/*
	 * return all my profile fields values
	 */
	public List<WebElement> getmyprofilelabelsvalues(){
		return FindElements(driver, "//td[@class='td2']", "myprofilelablesvalues");
	}
	
	/*
	 * return All input fields at my profile edit page (except Mailing Street)
	 */
	public List<WebElement> myprofileinputboxes(){
		return FindElements(driver, "//td[@class='td2']/input", "myprofileinputboxes");
	}
	
	@FindBy(xpath="//div[@id='contactName']")
	private WebElement firmnameeditmode;
	
	/*
	 * return firm name on my profile edit page.
	 */
	public WebElement getfirmnameeditmodevalue(int timeout){
		return isDisplayed(driver, firmnameeditmode, "visibility", timeout, "Firm name of edit my profile page.");
	}
	
	@FindBy(xpath="//div[@id='emailInEdit']")
	private WebElement emaileditmode;
	
	/*
	 * return email on my profile edit page.
	 */
	public WebElement getemaileditmodevalue(int timeout){
		return isDisplayed(driver, emaileditmode, "visibility", timeout, "Firm name of edit my profile page.");
	}
	
	@FindBy(xpath = "//div[@class='left_myprofile_div']//a[@title='Cancel']")
	private WebElement myProfileCancelButton;

	/**
	 * @return the myProfileCancelButton
	 */
	public WebElement getmyProfileCancelButton(int timeOut) {
		return isDisplayed(driver, myProfileCancelButton, "Visibility", timeOut, "My Profile Cancel Button");
	}
	
	@FindBy(xpath = "//a[@class='yellow-tooltip']/img")
	private WebElement myprofileinfoicon;
	
	/*
	 * return my profile info icon
	 */
	public WebElement getmyprofileinfoicon(int timeout){
		return isDisplayed(driver, myprofileinfoicon, "visibility", timeout, "Myprofileinfoicon");
	}
	
	@FindBy(xpath = "//div[@class='tip-yellow']//span")
	private WebElement myprofileinfoicontext;
	
	/*
	 * return my profile info icon
	 */
	public WebElement getmyprofileinfoicontext(int timeout){
		return isDisplayed(driver, myprofileinfoicontext, "visibility", timeout, "myprofileinfoicontext");
	}
	
	/*
	 * return First Name and Last Name Error Message
	 */
	public List <WebElement> getmyprofileerrorfield(){
		return FindElements(driver, "//div[@class='error_txt']", "First Name,Last Name error");
	}
	
	/*
	 * basic info field values except Address
	 */
	public List<WebElement> getbasicinfofieldvalues()
	{
		return FindElements(driver, "//div[@class='divwrap1']", "basicinfofieldvalues except Address");
	}
	
	@FindBy(xpath="//div[@class='divwrap2']")
	private WebElement basicinfoaddress;
		
	/*
	 * return Address at my profile page.
	 */
	public WebElement getbasicinfoaddress(int timeout)
	{
		return isDisplayed(driver, basicinfoaddress, "visibility", timeout, "Address");
	}

	/**************************************my firm profile***********************************/
	@FindBy(xpath="//input[@id='pageid:frmid:fname']")
	private WebElement myFirmProfileNameTextBox;
	
	/**
	 * @return the myFirmProfileNameTextBox
	 */
	public WebElement getMyFirmProfileNameTextBox(int timeOut) {
		return isDisplayed(driver, myFirmProfileNameTextBox, "Visibility", timeOut, "my firm profile name text box");
	}

	@FindBy(xpath="//input[@name='pageid:frmid:j_id34']")
	private WebElement myFirmProfileFirmContact;	
	
	/**
	 * @return the myFirmProfileFirmContact
	 */
	public WebElement getMyFirmProfileFirmContact(int timeOut) {
		return isDisplayed(driver, myFirmProfileFirmContact, "Visibility", timeOut, "my firm firm contact text box");
	}
	
	@FindBy(xpath="//input[@name='pageid:frmid:fwebsite']")
	private WebElement myFirmProfileWebsite;

	
	/**
	 * @return the myFirmProfileWebsite
	 */
	public WebElement getMyFirmProfileWebsite(int timeOut) {
		return isDisplayed(driver, myFirmProfileWebsite, "Visibility", timeOut, "my firm profile website text box");
	}
	
	@FindBy(xpath="//input[@name='pageid:frmid:j_id36']")
	private WebElement myFirmProfilePhone;
	
	/**
	 * @return the myFirmProfilePhone
	 */
	public WebElement getMyFirmProfilePhone(int timeOut) {
		return isDisplayed(driver, myFirmProfilePhone, "Visibility", timeOut, "my firm profile phone text box");
	}

	@FindBy(xpath="//textarea[@name='pageid:frmid:j_id41']")
	private WebElement myFirmPrpofileMailingStreet;

	/**
	 * @return the myFirmPrpofileMailingStreet
	 */
	public WebElement getMyFirmPrpofileMailingStreet(int timeOut) {
		return isDisplayed(driver, myFirmPrpofileMailingStreet, "Visibility", timeOut, "my firm profile mailing street text box");
	}
	
	@FindBy(xpath="//input[@name='pageid:frmid:j_id43']")
	private WebElement myFirmProfileMailingCity;

	/**
	 * @return the myFirmProfileMailingCity
	 */
	public WebElement getMyFirmProfileMailingCity(int timeOut) {
		return isDisplayed(driver, myFirmProfileMailingCity, "Visibility", timeOut, "my firm profile mailing city");
	}

	@FindBy(xpath="//input[@name='pageid:frmid:j_id45']")
	private WebElement myFirmProfileMailingState;
	/**
	 * @return the myFirmProfileMailingState
	 */
	public WebElement getMyFirmProfileMailingState(int timeOut) {
		return isDisplayed(driver, myFirmProfileMailingState, "Visibility", timeOut, "my firm profile mailing state");
	}
	
	@FindBy(xpath="//input[@name='pageid:frmid:j_id47']")
	private WebElement myFirmProfileMailingzip;
	/**
	 * @return the myFirmProfileMailingzip
	 */
	public WebElement getMyFirmProfileMailingzip(int timeOut) {
		return isDisplayed(driver, myFirmProfileMailingzip, "Visibility", timeOut, "my firm profile mailing zip");
	}
	
	@FindBy(xpath="//input[@name='pageid:frmid:j_id49']")
	private WebElement myFirmprofileMailingCountry;

	/**
	 * @return the myFirmprofileMailingCountry
	 */
	public WebElement getMyFirmprofileMailingCountry(int timeOut ) {
		return isDisplayed(driver, myFirmprofileMailingCountry, "Visibility", timeOut, "my firm profile mailing country");
	}
	
	@FindBy(xpath="//select[@id='AvailableFundTypes']")
	private WebElement myFirmProfileAvailableTypesDropDownList;
	
	/**
	 * @return the myFirmProfileTypesDropDownList
	 */
	public WebElement getMyFirmProfileAvailableTypesDropDownList(int timeOut) {
		return isDisplayed(driver, myFirmProfileAvailableTypesDropDownList, "Visibility", timeOut, "my firm profile types drop down list");
	}
	@FindBy(xpath="//select[@id='AvailableFundTypes']/../div/a[@id='addbuttonFundSelect']")
	private WebElement myFirmProfileTypesAddBtn;
	
	/**
	 * @return the myFirmProfileTypesAddBtn
	 */
	public WebElement getMyFirmProfileTypesAddBtn(int timeOut) {
		return isDisplayed(driver, myFirmProfileTypesAddBtn, "Visibility", timeOut, "my firm profile types add button");
	}
	
	@FindBy(xpath="//select[@id='AvailableIndustries']")
	private WebElement myFirmProfileAvailableIndustriesDropDownList;

	/**
	 * @return the myFirmProfileAvailableIndustriesDropDownList
	 */
	public WebElement getMyFirmProfileAvailableIndustriesDropDownList(int timeOut) {
		return isDisplayed(driver, myFirmProfileAvailableIndustriesDropDownList, "Visibility", timeOut, "my firm profile available industries drop down list");
	}
	
	@FindBy(xpath="//select[@id='AvailableIndustries']/../div/a[@id='addbuttonIndustriesselect']")
	private WebElement myFirmProfileIndustriesAddBtn;
	
	/**
	 * @return the myFirmProfileIndustriesAddBtn
	 */
	public WebElement getMyFirmProfileIndustriesAddBtn(int timeOut) {
		return isDisplayed(driver, myFirmProfileIndustriesAddBtn, "Visibility", timeOut, "my firm profile industries add button");
	}
	
	@FindBy(xpath="//select[@id='AvailableGeographic']")
	private WebElement myFirmProfileAvailableGeoFocusDropDownList;
	/**
	 * @return the myFirmProfileAvailableGeographicDropDownList
	 */
	public WebElement getMyFirmProfileAvailableGeoFocusDropDownList(int timeOut) {
		return isDisplayed(driver, myFirmProfileAvailableGeoFocusDropDownList, "Visibility", timeOut, "my firm profile available geographic drop down list");
	}

	@FindBy(xpath="//select[@id='AvailableGeographic']/../div/a[@id='addbuttonGeographicselect']")
	private WebElement myFirmProfileGeoFocusAddBtn;
	
	/**
	 * @return the myFirmProfileGeographicAddBtn
	 */
	public WebElement getMyFirmProfileGeoFocusAddBtn(int timeOut) {
		return isDisplayed(driver, myFirmProfileGeoFocusAddBtn, "Visibility", timeOut, "my firm profile geographic add button");
	}
	
	@FindBy(xpath="//textarea[@id='pageid:frmid:accdesc']")
	private WebElement myFirmProfileDescriptionTextArea;
	
	/**
	 * @return the myFirmProfileDescriptionTextArea
	 */
	public WebElement getMyFirmProfileDescriptionTextArea(int timeOut) {
		return isDisplayed(driver, myFirmProfileDescriptionTextArea, "Visibility", timeOut, "my firm profile text area");
	}

	@FindBy(xpath="//input[@id='pageid:frmid:aum']")
	private WebElement myFirmProfileAUMTextBox;

	/**
	 * @return the myFirmProfileAUMTextBox
	 */
	public WebElement getMyFirmProfileAUMTextBox(int timeOut) {
		return isDisplayed(driver, myFirmProfileAUMTextBox, "Visibility", timeOut, "my firm profile aum text box");
	}
	
	

	@FindBy(xpath="//span[@id='pageid:frmid:editoption']//a[@title='Save']")
	private WebElement myFirmProfilesaveBtn;
	
	/**
	 * @return the myFirmProfilesaveBtn
	 */
	public WebElement getMyFirmProfilesaveBtn(int timeOut) {
		return isDisplayed(driver, myFirmProfilesaveBtn, "Visibility", timeOut, "my firm profile save button");
	}
	
	@FindBy(xpath="//a[contains(text(),'Change Logo')]")
	private WebElement changeLogoLink;
	
	/**
	 * @return the changeLogoLink
	 */
	public WebElement getChangeLogoLink(int timeOut) {
		return isDisplayed(driver, changeLogoLink, "Visibility", timeOut, "change logo link");
	}
	
	@FindBy(xpath="//input[@title='Browse']")
	private WebElement browseButton;

	/**
	 * @return the browseButton
	 */
	public WebElement getBrowseButton(int timeOut) {
		return isDisplayed(driver, browseButton, "Visibility", timeOut, "Browser Button");
	}
	
	@FindBy(xpath="//a[@title='Submit']")
	private WebElement submitButtonImageUpload;

	/**
	 * @return the submitButton
	 */
	public WebElement getSubmitButtonImageUpload(int timeOut) {
		return isDisplayed(driver, submitButtonImageUpload, "Visibility", timeOut, "Save Button");
	}
	
	@FindBy(xpath="//a[text()='Save']")
	private WebElement saveButtonImageUpload;
	
	/**
	 * @return the saveButtonImageUpload
	 */
	public WebElement getSaveButtonImageUpload(int timeOut) {
		return isDisplayed(driver, saveButtonImageUpload, "Visibility", timeOut, "save button image upload");
	}

	@FindBy(id="pg:frm1:errmessages")
	private WebElement OtherTypeFileUploadInLogoErrorMessage;
	
	/**
	 * @return the otherTypeFileUploadInLogoErrorMessage
	 */
	public WebElement getOtherTypeFileUploadInLogoErrorMessage(int timeOut) {
		return isDisplayed(driver, OtherTypeFileUploadInLogoErrorMessage, "Visibility", timeOut, "File Type not correct error message");
	}

	@FindBy(xpath="//a[@title='Cancel']")
	private WebElement cancelButton;
	
	/**
	 * @return the cancelButton
	 */
	public WebElement getCancelButton(int timeOut) {
		return isDisplayed(driver, cancelButton, "Visibility", timeOut, "Cancel Button");
	}

	@FindBy(id="ErrorDiv")
	private WebElement errorMsgOnLogoPopUp;

	/**
	 * @return the errorMsgOnLogoPopUp
	 */
	public WebElement getErrorMsgOnLogoPopUp(int timeOut) {
		return isDisplayed(driver, errorMsgOnLogoPopUp, "Visibility", timeOut, "Error Message on logo pop Up");
	}

	@FindBy(id="btnCrop")
	private WebElement cropButton;

	/**
	 * @return the cropButton
	 */
	public WebElement getCropButton(int timeOut) {
		return isDisplayed(driver, cropButton, "Visibility", timeOut, "Crop Button");
	}
	@FindBy(xpath="//span[contains(text(),'Firm Name')]/../..//td[2]/div")
	private WebElement myFirmPofilelabelText;
	
	/**
	 * @return the myFirmPofilelabelText
	 */
	public WebElement getMyFirmPofilelabelText(int timeOut) {
		return isDisplayed(driver, myFirmPofilelabelText, "Visibility", timeOut, "my firm profile label text");
	}
	
	/******************************* Avinash ****************************/
	
	@FindBy(xpath="//td[@class='td1']/span")
	private WebElement myfirmprofilelabels;
	
	/*
	 * return all my firm profile labels.
	 */
	public List<WebElement> getmyfirmprofilelabels(){
		return FindElements(driver, "//td[@class='td1']/span", "My firm profile lables");
	}
	
	@FindBy(xpath="//span[@id='lastModify']")
	private WebElement lastupdatedontext;
	
	/*
	 * return last updated on text.
	 */
	public WebElement getlastupdatedontext(int timeout){
		return isDisplayed(driver, lastupdatedontext, "visibility", timeout, "Last Updated On Text");
	}
	
	@FindBy(xpath="(//div[@class='image_box'])[1]/span/img")
	private WebElement logoimage;
	
	/*
	 * return my firm profile logo image.
	 */
	public WebElement getlogoimage(int timeout)
	{
		return isDisplayed(driver, logoimage, "visibility", timeout, "Logo Image");
	}
	
	@FindBy(xpath="(//div[@class='image_box'])[2]/span/img")
	private WebElement editmyfirmlogoimage;
	
	/*
	 * return my firm profile logo image.
	 */
	public WebElement geteditmyfirmlogoimage(int timeout)
	{
		return isDisplayed(driver, editmyfirmlogoimage, "visibility", timeout, "Logo Image");
	}
	
	
	@FindBy(xpath="//div[@id='TD_height']/..")
	private WebElement descriptionvalue;
	
	/*
	 * return description value at my firm's profile view page.
	 */
	public WebElement getdescriptionvalue(int timeout){
		return isDisplayed(driver, descriptionvalue, "visibility", timeout, "Description value");
	}
	
	@FindBy(xpath="//div[@id='Address']/..")
	private WebElement addressvalue;
	
	/*
	 * return address value at my firm's profile view page.
	 */
	public WebElement getaddressvalue(int timeout){
		return isDisplayed(driver, addressvalue, "visibility", timeout, "Description value");
	}
	
	/*
	 * return all my firm profile fields values
	 */
	public List<WebElement> getmyfirmprofilelabelsvalues(){
		return FindElements(driver, "//div[contains(@class,'comnBackgroundWhite')]//table[2]//table/tbody/tr/td[2]", "myfirmprofilelablesvalues");
	}
	
	@FindBy(xpath = "//a[@id='demo-basicMFI']/img")
	private WebElement myfirmprofileinfoicon;
	
	/*
	 * return my firm profile info icon
	 */
	public WebElement getmyfirmprofileinfoicon(int timeout){
		return isDisplayed(driver, myfirmprofileinfoicon, "visibility", timeout, "Myprofileinfoicon");
	}
	
	@FindBy(xpath = "(//div[@class='tip-inner tip-bg-image'])[2]")
	private WebElement myfirmprofileinfoicontext;
	
	/*
	 * return my firm profile info icon text
	 */
	public WebElement getmyfirmprofileinfoicontext(int timeout){
		return isDisplayed(driver, myfirmprofileinfoicontext, "visibility", timeout, "myprofileinfoicontext");
	}

	/*
	 * retun my firm edit page all labels.
	 */
	public List<WebElement> getmyfirmprofileeditpagelabels(){
		return FindElements(driver, "//span[@id='pageid:frmid:editoption']//table[1]//table[1]/tbody/tr/td[1]", "myfirmprofileeditpagelabels");
	}
	
	@FindBy (xpath = "(//span[@class='head'])[3]")
	private WebElement myfirmprofiletext;
	
	/*
	 * return the my profiletext
	 */
	public WebElement getmyfirmprofiletext(int timeout)
	{
		return isDisplayed(driver, myfirmprofiletext, "Visibility", timeout, "My profile text at My Profile page");
	}
	
	@FindBy(xpath="//select[@id='SelectedFundTypes']")
	private WebElement selectedfundtypes;
	
	/*
	 * return selected fund types
	 */
	public WebElement getselectedfundtypes(int timeout){
		return isDisplayed(driver, selectedfundtypes, "Visibility", timeout, "Selected Fund Types");
	}
	
	@FindBy(xpath="//select[@id='SelectedIndustries']")
	private WebElement selectedindustries;
	
	/*
	 * return selected industries
	 */
	public WebElement getselectedindustries(int timeout){
		return isDisplayed(driver, selectedindustries, "Visibility", timeout, "Selected Industries");
	}
	
	@FindBy(xpath="//select[@id='SelectedGeographic']")
	private WebElement selectedgeotypes;
	
	/*
	 * return selected Geo Types
	 */
	public WebElement getselectedgeotypes(int timeout){
		return isDisplayed(driver, selectedgeotypes, "Visibility", timeout, "Selected Geo Types");
	}
	
	/*
	 * return my firm profile edit page input boxes.
	 */
	public List<WebElement> getmyfirmprofileeditpageinputboxes(){
		return FindElements(driver, "//span[@id='pageid:frmid:editoption']//table[1]//table[1]/tbody/tr/td[2]/input", "My Firm profile input boxes");
	}
	
	
	@FindBy(xpath="(//span[contains(text(),'Public Login Link')])[2]/../..//td[2]/div")
	private WebElement myfirmeditpagepubliclink;
	
	/*
	 * return public login link at my firm's edit page.
	 */
	public WebElement getmyfirmeditpagepubliclink(int timeout){
		return isDisplayed(driver, myfirmeditpagepubliclink, "Visibility", timeout, "Public Login link");
	}
	
	@FindBy(xpath="(//div[@id='fnameerror'])[1]")
	private WebElement firmnameerror;
	
	/*
	 * return firm name error
	 */
	public WebElement getmyprofilefirmnameerror(int timeout){
		return isDisplayed(driver, firmnameerror, "visibility", timeout, "Firm Name Error");
	}
	
	@FindBy(xpath="//span[@id='pageid:frmid:Email_Error']")
	private WebElement emailnameerror;
	
	/*
	 * return Email error
	 */
	public WebElement getemailnameerror(int timeout){
		return isDisplayed(driver, emailnameerror, "visibility", timeout, "Email Error");
	}
	
	@FindBy(xpath="//div[@id='validAUM']")
	private WebElement aumerror;
	
	/*
	 * return AUM error
	 */
	public WebElement geteaumerror(int timeout){
		return isDisplayed(driver, aumerror, "visibility", timeout, "AUM Error");
	}

	@FindBy(xpath="//input[@id='pageid:frmid:email1']")
	private WebElement myfirmemail;
	
	/*
	 * return myfirm email
	 */
	public WebElement getmyfirmemail(int timeout){
		return isDisplayed(driver, myfirmemail, "visibility", timeout, "Firm Email");
	}
	
	@FindBy(xpath="(//div[@class='divwrap3'])[4]")
	private WebElement myfirmphonevalue;
	
	/*
	 * return myfirm Phone
	 */
	public WebElement getmyfirmphonevalue(int timeout){
		return isDisplayed(driver, myfirmphonevalue, "visibility", timeout, "My Firm phone value");
	}

	public List<WebElement> getaddicon(){
		return FindElements(driver, "//img[contains(@id,'addbutton')]", "Add button");
	}
	
	@FindBy(xpath="(//div[@class='divwrap3'])[6]//a")
	private WebElement websitelink;
	
	/*
	 * return Website link
	 */
	public WebElement getwebsitelink(int timeout){
		return isDisplayed(driver, websitelink, "visibility", timeout, "WebSite link");
	}
	
	@FindBy(xpath="(//table)[3]//tr[2]/td[2]/div")
	private WebElement FirmNAme;
	
	/*
	 * return firm name
	 */
	public WebElement getfirmname(int timeout){
		return isDisplayed(driver, FirmNAme, "Visibility", timeout, "Firm Name");
	}
	
	@FindBy(xpath="//a[text()='Submit']")
	private WebElement submitButtonFirmLogo;
	
	/**
	 * @return the SubmitButtonLogoUpload
	 */
	public WebElement getsubmitButtonFirmLogo(int timeOut) {
		return isDisplayed(driver, submitButtonFirmLogo, "Visibility", timeOut, "save button image upload");
	}
	
	@FindBy(xpath="//div[@id='ErrorDiv']")
	private WebElement submitButtonError;
	
	/**
	 * @return the SubmitButtonError
	 */
	public WebElement getsubmitButtonError(int timeOut) {
		return isDisplayed(driver, submitButtonError, "Visibility", timeOut, "save button image upload");
	}
	
	@FindBy(xpath="//div[@id='mess']//ul//li")
	private WebElement FiletypeError;
	
	/**
	 * @return the FileTypeError
	 */
	public WebElement getFiletypeError(int timeOut) {
		return isDisplayed(driver, FiletypeError, "Visibility", timeOut, "save button image upload");
	}
	
	@FindBy(xpath="//a[@title='Zoom Out']")
	private WebElement LogoZoomOutbutton;
	/*
	 * return logo zoom out button
	 */
	public WebElement getLogoZoomOutbutton(int timeout){
		return isDisplayed(driver, LogoZoomOutbutton,"visibility" , timeout, "Logo Zoom Out button");
	}
	
	@FindBy(xpath="//a[@title='Zoom In']")
	private WebElement LogoZoomInbutton;
	/*
	 * return logo zoom out button
	 */
	public WebElement getLogoZoomInbutton(int timeout){
		return isDisplayed(driver, LogoZoomInbutton,"visibility" , timeout, "Logo Zoom In button");
	}
	
	
	
	
	/********************************************************** Manage Approvals*************************************************************************************/
	

	@FindBy(xpath = "(//div[@class='heading_box']//span[contains(text(),'Manage Approvals')])[1]")
	private WebElement manageApprovalsHead;
	
	
	/**
	 * @return the manageApprovalsHead
	 */
	public WebElement getManageApprovalsHead(int timeOut) {
		return isDisplayed(driver, manageApprovalsHead, "Visibility", timeOut, "manage approvals head");
	}
	
	
	@FindBy(xpath = "//a[@id='demo-basic']")
	private WebElement infoIconManageApproval;
	
	
	/**
	 * @return the infoIcon
	 */
	public WebElement getInfoIconManageApproval(int timeOut) {
		return isDisplayed(driver, infoIconManageApproval, "Visibility", timeOut, "info icon on manage approval page");
	}

	/**
	 * @return the manageApprovalsActivateCheckbox
	 */
	public WebElement getManageApprovalsActivateCheckbox(EditViewMode mode) {
		String xpath = "";
		if (mode == EditViewMode.Edit) {
			xpath = "(//input[contains(@id,'manageapproval')])[2]";
		}
		else if(mode == EditViewMode.View) {
			xpath = "(//input[contains(@id,'manageapproval')])[1]";
		}
		return isDisplayed(driver, FindElement(driver, xpath, "Manage Approval checkbox", action.SCROLLANDBOOLEAN, 30), "visibility", 30, "manage approval checkbox");
	}

	@FindBy(xpath = "//div[@id='managediv']//a[@title='Save']")
	private WebElement manageApprovalSaveButton;

	/**
	 * @return the manageApprovalSaveButton
	 */
	public WebElement getManageApprovalSaveButton(int timeOut) {
		return isDisplayed(driver, manageApprovalSaveButton, "Visibility", timeOut, "Manage Approval Save button");
	}
	@FindBy(xpath = "//span[contains(@id,'grid_CRMAdmin-cell-0')]")
	private WebElement rowsCRMAdminManageApproval;
	
	
	/**
	 * @return the rowsCRMAdminManageApproval
	 */
	public WebElement getRowsCRMAdminManageApproval(int timeOut) {
		return isDisplayed(driver, rowsCRMAdminManageApproval, "Visibility", timeOut, "rows crm admin manage approval");
	}
	
	@FindBy(xpath = "//span[contains(@id,'grid_CRMAdmin-header')]/span[3]")
	private WebElement CRMAdminHeadsManageApproval;
	
	

	/**
	 * @return the cRMAdminHeadsManageApproval
	 */
	public List<WebElement> getCRMAdminHeadsManageApproval(int timeOut) {
		return FindElements(driver, "//span[contains(@id,'grid_CRMAdmin-header')]/span/span[3]", "crm admin heads in manage approval page");
	}
	
	public List<WebElement> getCheckboxesManageApproval(EditViewMode mode,int timeOut) {
		if (mode == EditViewMode.View) {
		return FindElements(driver, "//span[contains(@id,'cell-1-') and contains(@id,'grid_Users') and @title='']", "crm admin heads in manage approval page");
		}
		else {
		return FindElements(driver, "//span[contains(@id,'cell-1-') and contains(@id,'grid_Users1') and @title='']", "crm admin heads in manage approval page");
		}
	}
	public List<WebElement> getUserHeadsManageApproval(int timeOut,EditViewMode mode) {
		String xpath = "";
		if (mode == EditViewMode.View) {
			xpath = "//span[contains(@id,'grid_Users-header-')]/span/span[3]";
		}
		else if(mode == EditViewMode.Edit) {
			xpath = "//span[contains(@id,'grid_Users1-header-')]/span/span[3]";
		}
		return FindElements(driver, xpath, "crm user heads in manage approval page");
	}
	public List<WebElement> getUserNamesManageApproval(int timeOut,EditViewMode mode) {
		String xpath = "";
		if (mode == EditViewMode.View)
			xpath = "//span[contains(@id,'grid_Users-cell-0')]";
		else if(mode == EditViewMode.Edit)
			xpath = "//span[contains(@id,'grid_Users1-cell-0')]";
		return FindElements(driver, xpath, "user names in manage approval page");
	}
	@FindBy(xpath = "//input[@id='manageapprovals']/..")
	private WebElement activateManageAppText;
	
	
	/**
	 * @return the activateManageAppText
	 */
	public WebElement getActivateManageAppText(int timeOut) {
		return isDisplayed(driver, activateManageAppText, "Visibility", timeOut, "activate manage approval text");
	}

	@FindBy(xpath = "(//span[contains(@id,'grid_CRMAdmin-cell-0')]/..//span[contains(@id,'grid_CRMAdmin-cell-1')]/span)[2]")
	private WebElement adminCheckboxManageApproval;
	
	
	/**
	 * @return the adminCheckboxManageApproval
	 */
	public WebElement getAdminCheckboxManageApproval(int timeOut) {
		return isDisplayed(driver, adminCheckboxManageApproval, "Visibility", timeOut, "admin checkbox for manage approval");
	}
	@FindBy(xpath = "//span[contains(@id,'grid_Users1-cell-0')]/span")
	private WebElement userRowManageApprovalEditMode;
	
	
	/**
	 * @return the userRowManageApprovalEditMode
	 */
	public WebElement getNoDataToDisplayInUsersTable(int timeOut, EditViewMode mode) {
		WebElement ele=null;
		if (mode == EditViewMode.Edit)
			ele = userRowManageApprovalEditMode;
		else if (mode == EditViewMode.View)
			ele = usersRowManageApprovalViewMode;
		return isDisplayed(driver, ele, "Visibility", timeOut, "no data to display message");
	}

	@FindBy(xpath = "//span[contains(@id,'grid_Users-cell-0')]/span")
	private WebElement usersRowManageApprovalViewMode;
	
	
	/**
	 * @return the usersRowManageApproval
	 */
	public WebElement getUsersRowManageApprovalViewMode(int timeOut) {
		return isDisplayed(driver, usersRowManageApprovalViewMode, "Visibility", timeOut, "users 1st row manage approval");
	}

	@FindBy(xpath = "//div[contains(@class,'ConfirmUserPermissionAddition')]//a[@title='Yes']")
	private WebElement manageApprovalDeactivatePopupYesButton;

	/**
	 * @return the manageApprovalDeactivatePopupYesButton
	 */
	public WebElement getManageApprovalDeactivatePopupYesButton(int timeOut) {
		return isDisplayed(driver, manageApprovalDeactivatePopupYesButton, "Visibility", timeOut,
				"Manage Approval Deactivate popup yes button");

	}
	@FindBy(xpath = "//div[contains(@class,'ConfirmUserPermissionAddition')]//a[@title='No']")
	private WebElement manageApprovalDeactivatePopupNoButton;
	
	
	
	/**
	 * @return the manageApprovalDeactivatePopupNoButton
	 */
	public WebElement getManageApprovalDeactivatePopupNoButton(int timeOut) {
		return isDisplayed(driver, manageApprovalDeactivatePopupNoButton, "Visibility", timeOut, "mange approval deactivate no button");
	}

	@FindBy(xpath = "//div[contains(text(),'Confirm Manage Approvals Activation')]/..//a[@title='Yes']")
	private WebElement manageApprovalActivateYesButton;
	
	/**
	 * @return the manageApprovalActivateYesButton
	 */
	public WebElement getManageApprovalActivateYesButton(int timeOut) {
		return isDisplayed(driver, manageApprovalActivateYesButton, "Visibility", timeOut, "manage approval activate yes button");
	}
	
	@FindBy(xpath = "//table[@class='tip-table']//span[contains(@style,'normal')]")
	private WebElement toolTipInfoIconManageApproval;

	/**
	 * @return the toolTipInfoIconManageApproval
	 */
	public WebElement getToolTipInfoIconManageApproval(int timeOut) {
		return isDisplayed(driver, toolTipInfoIconManageApproval, "Visibility", timeOut, "tool tip for info icon on manage approval page");
	}
	
	@FindBy(xpath = "//div[contains(text(),'Confirm Manage Approvals Activation')]/..//a[@title='No']")
	private WebElement manageApprovalActivateNoButton;
	
	/**
	 * @return the manageApprovalActivateNoButton
	 */
	public WebElement getManageApprovalActivateNoButton(int timeOut) {
		return isDisplayed(driver, manageApprovalActivateNoButton, "Visibility", timeOut, "manage approval activate no button");
	}
	@FindBy(xpath = "//div[contains(text(),'Confirm Manage Approvals Activation')]/../p[1]")
	private WebElement allSelectedUsersWillBeGrantedText;
	
	
	/**
	 * @return the allSelectedUsersWillBeGrantedText
	 */
	public WebElement getAllSelectedUsersWillBeGrantedText(int timeOut) {
		return isDisplayed(driver, allSelectedUsersWillBeGrantedText, "Visibility", timeOut, "all selected users will be granted text");
	}
	@FindBy(xpath = "//div[contains(text(),'Confirm Manage Approvals Activation')]/../p[2]")
	private WebElement areYouSureWantToProceedText;
	
	
	/**
	 * @return the doYouWantToProceedText
	 */
	public WebElement getDoYouWantToProceedText(int timeOut) {
		return isDisplayed(driver, areYouSureWantToProceedText, "Visibility", timeOut, "are You Sure you Want To Proceed Text");
	}
	@FindBy(xpath = "//div[contains(@class,'ConfirmUserPermissionRemoval')]/div/a[@title='Close']")
	private WebElement crossIconOnManageApprovalActivationPopup;
	
	
	/**
	 * @return the crossIconOnUserAdditionPopup
	 */
	public WebElement getCrossIconOnManageApprovalActivationPopup(int timeOut) {
		return isDisplayed(driver, crossIconOnManageApprovalActivationPopup, "Visibility", timeOut, "cross Icon On Manage Approval Activation Popup");
	}
	@FindBy(xpath = "//div[contains(@class,'ConfirmUserPermissionAddition')]/div/a[@title='Close']")
	private WebElement crossIconOnManageApprovalDeactivationPopup;
	
	
	/**
	 * @return the crossIconOnManageApprovalDeactivationPopup
	 */
	public WebElement getCrossIconOnManageApprovalDeactivationPopup(int timeOut) {
		return isDisplayed(driver, crossIconOnManageApprovalDeactivationPopup, "Visibility", timeOut, "cross icon manage approvals deactivation popup");
	}

	/************************************************************ Watermarking **************************************************************/
	@FindBy(xpath="//span[text()='Watermarking']")
	private WebElement waterMarkingLabeltext;
	
	/**
	 * @return the waterMarkingLabeltext
	 */
	public WebElement getWaterMarkingLabeltext(int timeOut) {
		return isDisplayed(driver, waterMarkingLabeltext, "Visibility", timeOut, "watermarking label text");
	}
	
	@FindBy(xpath="//b[text()='Apply Watermarking to PDF Documents']")
	private WebElement waterMarkingCheckBoxText;
	
	/**
	 * @return the waterMarkingCheckBoxText
	 */
	public WebElement getWaterMarkingCheckBoxText(int timeOut) {
		return isDisplayed(driver, waterMarkingCheckBoxText, "Visibility", timeOut, "watermarking check apply text");
	}

	@FindBy(xpath = "//b[text()='Apply Watermarking to PDF Documents']/../..//input")
	private WebElement watermarkingActivateCheckbox;

	/**
	 * @return the watermarkingActivateCheckbox
	 */
	public WebElement getWatermarkingActivateCheckbox(int timeOut) {
		return isDisplayed(driver, watermarkingActivateCheckbox, "Visibility", timeOut,
				"Watermarking activate checkbox");
	}
	
	
	@FindBy(xpath="//span[@id='pgid:frmid:outid']//a[@title='Save']")
	private WebElement watermarkingSaveButton;

	/**
	 * @return the watermarkingSaveButton
	 */
	public WebElement getWatermarkingSaveButton(int timeOut) {
		return isDisplayed(driver, watermarkingSaveButton, "Visibility", timeOut, "Watermarking Save button");
	}
	
	@FindBy(xpath="//td[contains(text(),'My Firm')]/preceding-sibling::td/input")
	private WebElement watermarkingFirmNameLabel;

	/**
	 * @return the watermarkingFirmNameLabel
	 */
	public WebElement getWatermarkingFirmNameLabel(int timeOut) {
		return isDisplayed(driver, watermarkingFirmNameLabel, "Visibility", timeOut, "Firm Name Label");
	}
	
	@FindBy(xpath="//td[contains(text(),'My')][contains(text(),'Firm')][contains(text(),'Name')]/following-sibling::td//select")
	private WebElement waterMarkingMyFirmNameDropDown;

	/**
	 * @return the myFirmNameDropDown
	 */
	public WebElement getWaterMarkingMyFirmNameDropDown(int timeOut) {
		return isDisplayed(driver, waterMarkingMyFirmNameDropDown, "Visibility", timeOut, "My Firm Name Drop Down");
	}
	
	@FindBy(xpath="//span[contains(text(),'Investor Name')]/../preceding-sibling::td/input")
	private WebElement investorNameLabel;

	/**
	 * @return the targetAccountNameLabel
	 */
	public WebElement getInvestorNameLabel(int timeOut) {
		return isDisplayed(driver, investorNameLabel, "Visibility", timeOut, "Investor Name Label");
	}
	
	@FindBy(xpath="//span[contains(text(),'Investor')][contains(text(),'Name')][contains(text(),'Name')]/../following-sibling::td//select")
	private WebElement investorNameLabelDropDown;

	/**
	 * @return the targetAccountNameLabelDropDown
	 */
	public WebElement getInvestorNameLabelDropDown(int timeOut) {
		return isDisplayed(driver, investorNameLabelDropDown, "Visibility", timeOut, "investor Name Drop Down");
	}
	
	@FindBy(xpath="//td[contains(text(),'Fund Name')]/preceding-sibling::td/input")
	private WebElement fundNameLabel;

	/**
	 * @return the dealRoomNameLabel
	 */
	public WebElement getFundNameLabel(int timeOut) {
		return isDisplayed(driver, fundNameLabel, "Visibility", timeOut, "fund Name Label");
	}
	
	@FindBy(xpath="//td[contains(text(),'Fund')][contains(text(),'Name')][contains(text(),'Name')]/following-sibling::td//select")
	private WebElement fundNameLabelDropDown;

	/**
	 * @return the dealRoomNameLabelDropDown
	 */
	public WebElement getFundNameLabelDropDown(int timeOut) {
		return isDisplayed(driver, fundNameLabelDropDown, "Visibility", timeOut, "Fund Name Label Drop Down");
	}
	
	@FindBy(xpath="//td[contains(text(),'Download Date')]/preceding-sibling::td/input")
	private WebElement downloadDateLabel;

	/**
	 * @return the downloadDateLabel
	 */
	public WebElement getDownloadDateLabel(int timeOut) {
		return isDisplayed(driver, downloadDateLabel, "Visibility", timeOut, "Download Date Label");
	}
	
	@FindBy(xpath="//td[contains(text(),'Download')][contains(text(),'Date')]/following-sibling::td//select")
	private WebElement downloadDateLabelDropDown;

	/**
	 * @return the downloadDateLabelDropDown
	 */
	public WebElement getDownloadDateLabelDropDown(int timeOut) {
		return isDisplayed(driver, downloadDateLabelDropDown, "Visibility", timeOut, "DownLoad Date Drop Down");
	}
	
	@FindBy(xpath="//td[contains(text(),'IP Address')]/preceding-sibling::td/input")
	private WebElement IPAddressLabel;

	/**
	 * @return the iPAddressLabel
	 */
	public WebElement getIPAddressLabel(int timeOut) {
		return isDisplayed(driver, IPAddressLabel, "Visibility", timeOut, "IP Address Label");
	}
	
	@FindBy(xpath="//td[contains(text(),'IP')][contains(text(),'Address')]/following-sibling::td//select")
	private WebElement IPAddressLabelDropDown;

	/**
	 * @return the iPAddressLabelDropDown
	 */
	public WebElement getIPAddressLabelDropDown(int timeOut) {
		return isDisplayed(driver, IPAddressLabelDropDown, "Visibility", timeOut, "IP Address Drop Down");
	}
	
	@FindBy(xpath="//td[contains(text(),'Email Address')]/preceding-sibling::td/input")
	private WebElement emailAddressLabel;

	/**
	 * @return the emailAddressLabel
	 */
	public WebElement getEmailAddressLabel(int timeOut) {
		return isDisplayed(driver, emailAddressLabel, "Visibility", timeOut, "E-mail Address Label");
	}
	
	@FindBy(xpath="//td[contains(text(),'Email')][contains(text(),'Address')]/following-sibling::td//select")
	private WebElement emailAddressLabelDropDown;

	/**
	 * @return the emailAddressLabelDropDown
	 */
	public WebElement getEmailAddressLabelDropDown(int timeOut) {
		return isDisplayed(driver, emailAddressLabelDropDown, "Visibility", timeOut, "email Address Label Drop Down");
	}
	
	@FindBy(id="custLabel")
	private WebElement customLabelCheckBox;

	/**
	 * @return the customLabelCheckBox
	 */
	public WebElement getCustomLabelCheckBox(int timeOut) {
		return isDisplayed(driver, customLabelCheckBox, "Visibility", timeOut, "Custom Label CheckBox");
	}
	
	@FindBy(id="btnAddMore")
	private WebElement customLabelAddRowLink;

	/**
	 * @return the customLabelAddRowLink
	 */
	public WebElement getCustomLabelAddRowLink(int timeOut) {
		return isDisplayed(driver, customLabelAddRowLink, "Visibility", timeOut, "Cutom Label Add Row Link");
	}
	
	public List<WebElement> getWaterMakringSectionslist(){
		return FindElements(driver, "//div[@class='heading_box']/span", "watermarking sections list");
	}
	
	public List<WebElement> getWatermakringAppliedOnUploadHeaderTextList(){
		return FindElements(driver, "((//div[@class='heading_box']/span)[3]/../..//tr//table//tr[1])[2]/td", "watermakring applied on upload header text list");
	}
	
	
	public List<WebElement>getWaterMarkingAppliedOnDownloadHeaderTextList(){
		return FindElements(driver, "((//div[@class='heading_box']/span)[4]/../..//tr//table//tr[1])[1]/td","watermarking applied on download header text list");
	}
	
	@FindBy(xpath="//span[@id='pgid:frmid:outid']//a[@title='Cancel']")
	private WebElement waterMarkingCancelBtn;

	/**
	 * @return the waterMarkingCancelBtn
	 */
	public WebElement getWaterMarkingCancelBtn(int timeOut) {
		return isDisplayed(driver, waterMarkingCancelBtn, "Visibility", timeOut, "watermarking cancel button");
	}
	
	public List<WebElement> getWatermarkingCustomTextBoxList(){
		return FindElements(driver, "//input[contains(@id,'criteriatextbox')]", "watermarking custom text box list");
	}
	
	public List<WebElement> getWaterMarkingCustomDropDownList(){
		return FindElements(driver, "//select[contains(@id,'aa')]", "custom drop downlist");
	}
	
	@FindBy(xpath="//a[@id='demo-basic']/img")
	private WebElement waterMarkingInvestorNameTooltip;

	/**
	 * @return the waterMarkingInvestorNameInformationText
	 */
	public WebElement getWaterMarkingInvestorNameToolTip(int timeOut) {
		return isDisplayed(driver, waterMarkingInvestorNameTooltip, "Visibility", timeOut, "watermarking investor name tootltip");
	}
	@FindBy(xpath="//div[@class='tip-yellow']//span")
	private WebElement waterMarkingInvestorNametoolTipText;

	/**
	 * @return the waterMarkingInvestorNametoolTipText
	 */
	public WebElement getWaterMarkingInvestorNametoolTipText(int timeOut) {
		return isDisplayed(driver, waterMarkingInvestorNametoolTipText, "Visibility", timeOut, "watermarking investor name tooltip text");
	}
	
	@FindBy(xpath="//div[contains(@class,'InsufficientPermissions_fancybox')]/div[1]")
	private WebElement waterMarkingInsufficientPermissionHeadertext;

	/**
	 * @return the waterMarkingInsufficientPermissionHeadertext
	 */
	public WebElement getWaterMarkingInsufficientPermissionHeadertext(int timeOut) {
		return isDisplayed(driver, waterMarkingInsufficientPermissionHeadertext, "Visibility", timeOut, "watermarking infficient permission header text");
	}
	
	@FindBy(xpath="//div[contains(@class,'InsufficientPermissions_fancybox')]/div[1]/a")
	private WebElement watermarkingInsufficientPermissionCrossIcon;

	/**
	 * @return the watermarkingInsfficientPermissionCrossIcon
	 */
	public WebElement getWatermarkingInsufficientPermissionCrossIcon(int timeOut) {
		return isDisplayed(driver, watermarkingInsufficientPermissionCrossIcon, "Visibility", timeOut, "watermarking inufficient permission cross icon");
	}
	
	@FindBy(xpath="//div[contains(@class,'InsufficientPermissions_fancybox')]/p")
	private WebElement waterMarkingInsufficientPermissionErrorMsg;

	/**
	 * @return the waterMarkingInfficientPermissionErrorMsg
	 */
	public WebElement getWaterMarkingInsufficientPermissionErrorMsg(int timeOut) {
		return isDisplayed(driver, waterMarkingInsufficientPermissionErrorMsg, "Visibility", timeOut, "watermarking insufficient permission error message");
	}
	
	@FindBy(xpath="//div[contains(@class,'InsufficientPermissions_fancybox')]/div[2]/input")
	private WebElement waterMarkingInsufficientPermissioncloseBtn;

	/**
	 * @return the waterMarkingInsufficientPermissioncloseBtn
	 */
	public WebElement getWaterMarkingInsufficientPermissioncloseBtn(int timeOut) {
		return isDisplayed(driver, waterMarkingInsufficientPermissioncloseBtn, "Visibility", timeOut, "watermarking insufficient permission close button");
	}
	
	public List<WebElement> getWaterMarkingCustomFieldCrossIconList(){
		return FindElements(driver, "//input[contains(@id,'removebtn') and contains(@onclick,'advanceflter_removerow')]", "watermarking custom field cross icon list");
	}
	
	public List<WebElement> getWaterMarkingCheckBoxList(){
		return FindElements(driver, "//td/preceding-sibling::td/input", "watermarking chcek box list");
	}
	
	public List<WebElement> getWatermarkingDropDownList(){
		return FindElements(driver, "//td/preceding-sibling::td/input/../..//select", "watermarking drop dwon list");
	}
	
	/****************************File Distributor***************************************/
	@FindBy(xpath = "//span[@class='head'][contains(text(),'File Distributor Settings')]")
	private WebElement fileDistributorHead;

	/**
	 * @return the fileDistributorHead
	 */
	public WebElement getFileDistributorHead(int timeOut) {
		return isDisplayed(driver, fileDistributorHead, "Visibility", timeOut, "FileDistributorHead");
	}
	
	@FindBy(xpath = "//span[@class='head'][text()='Bulk Upload Options']")
	private WebElement bulkUploadHeader;

	/**
	 * @return the bulkUploadHeader
	 */
	public WebElement getBulkUploadHeader(int timeOut) {
		return isDisplayed(driver, bulkUploadHeader, "Visibility", timeOut, "BulkUploadHeader");
	}
	@FindBy(xpath = "//span[@class='head'][text()='File Splitter Options']")
	private WebElement fileSplitterHead;
	
	
	
	/**
	 * @return the fileSplitterHead
	 */
	public WebElement getFileSplitterHead(int timeOut) {
		return isDisplayed(driver, fileSplitterHead, "Visibility", timeOut, "file splitter head");
	}

	@FindBy(xpath = "//div[@id='home_main']//div[@class='grid_div']/span")
	private WebElement fileSplitterOrBulkUploadText;

	/**
	 * @return the fileSplitterText
	 */
	public WebElement getFileSplitterOrBulkUploadText(int timeOut) {
		return isDisplayed(driver, fileSplitterOrBulkUploadText, "Visibility", timeOut, "file splitter text");
	}
	
	@FindBy(xpath = "//input[@id='blkdefault']/../../td//span")
	private WebElement useSuggestedNaming;

	/**
	 * @return the useSuggestedNaming
	 */
	public WebElement getUseSuggestedNaming(int timeOut) {
		return isDisplayed(driver, useSuggestedNaming, "Visibility", timeOut, "use suggested naming");
	}
	@FindBy(xpath="//input[@id='blkreverse']/../../td//span")
	private WebElement userReverseNaming;

	/**
	 * @return the userReverseNaming
	 */
	public WebElement getUserReverseNaming(int timeOut) {
		return isDisplayed(driver, userReverseNaming, "Visibility", timeOut, "reverse naming convention");
	}
	@FindBy(xpath = "//input[@id='blkdefault']")
	private WebElement defaultNamingRadioButton;

	/**
	 * @return the defaultNamingRadioButton
	 */
	public WebElement getDefaultNamingRadioButton(int timeOut) {
		return isDisplayed(driver, defaultNamingRadioButton, "Visibility", timeOut, "DefaultNamingRadioButton");
	}
	@FindBy(xpath = "//input[@id='blkreverse']")
	private WebElement reverseNamingRadioButton;

	/**
	 * @return the reverseNamingRadioButton
	 */
	public WebElement getReverseNamingRadioButton(int timeOut) {
		return isDisplayed(driver, reverseNamingRadioButton, "Visibility", timeOut, "reverse naming convention radio button");
	}
	
	@FindBy(xpath = "//a[@id='demo-tip-yellow-pe']/img")
	private WebElement tooltipBulkUpload;
	
	@FindBy(xpath = "//a[@id='demo-basic-pe']/img")
	private WebElement tooltipFileSpl;
	
	public WebElement getFileDistrToolTip(fileDistributor fd) {
		if (fd == fileDistributor.BulkUpload) {
			return isDisplayed(driver, tooltipBulkUpload, "visibility", 30, "tool tip of bulk upload");
		}
		else {
			return isDisplayed(driver, tooltipFileSpl, "visibility", 30, "tool tip of bulk upload");
		
		}
	}
	@FindBy(xpath = "(//div[@class='tip-yellow']//div[contains(@class,'tip-inner')])[2]")
	private WebElement tooltipFileSplitterText;
	
	/**
	 * @return the tooltipFileSplitterText
	 */
	public WebElement getTooltipFileDistText(fileDistributor fd,int timeOut) {
		return isDisplayed(driver, tooltipFileSplitterText, "Visibility", timeOut, "TooltipFileSplitterText");
		
	}

	public WebElement getFileDistributorSaveButton(fileDistributor fd) {
		String xpath = "";
		int fdSel;
		if (fd == fileDistributor.BulkUpload) {
			fdSel=8;
		}
		else {
			fdSel=10;
		}
		xpath = "//a[@id='pg:frm:j_id"+fdSel+"']";
		return isDisplayed(driver, FindElement(driver, xpath, "save button", action.SCROLLANDBOOLEAN, 10), "visibility", 10, "save button");
	}
	
	//********************************************************************Bulk*****************************************************************************************//
	
	@FindBy(xpath="//div[@id='errMsgAddFolder']")
	private WebElement bulkAddFolderErrorMessage;

	/**
	 * @return the bulkAddFolderErrorMessage
	 */
	public WebElement getBulkAddFolderErrorMessage(int timeOut) {
		return isDisplayed(driver, bulkAddFolderErrorMessage, "Visibility", timeOut, "Bulk Folder Add error Message");
	}
	
	@FindBy(xpath="//div[@id='idCharError']//a[@title='Close']")
	private WebElement BulkAddFolderErrorPopupCloseButton;

	/**
	 * @return the bulkAddFolderErrorPopupCloseButton
	 */
	public WebElement getBulkAddFolderErrorPopupCloseButton(int timeOut) {
		return isDisplayed(driver, BulkAddFolderErrorPopupCloseButton, "Visibility", timeOut, "Bulk Add Folder Error popup Close Button");
	}
	
	@FindBy(xpath="//select[contains(@id,'temptype')]")
	private WebElement folderTemplateDisplayDropDown;

	/**
	 * @return the folderTemplateDisplayDropDown
	 */
	public WebElement getFolderTemplateDisplayDropDown(int timeOut) {
		return isDisplayed(driver, folderTemplateDisplayDropDown, "Visibility", timeOut, "Display drop down");
	}
	
	@FindBy(xpath="//a[text()='Delete Template']")
	private WebElement deleteTemplateButton;

	/**
	 * @return the deleteTemplateButton
	 */
	public WebElement getDeleteTemplateButton(int timeOut) {
		return isDisplayed(driver, deleteTemplateButton, "Visibility", timeOut, "Delete Template button");
	}
	
	@FindBy(xpath="//a[contains(@onclick,'deleteTemplate()')]")
	private WebElement deleteFolderTemplateConfirmationYesButton;

	/**
	 * @return the deleteFolderTemplateConfirmationYesButton
	 */
	public WebElement getDeleteFolderTemplateConfirmationYesButton(int timeOut) {
		return isDisplayed(driver, deleteFolderTemplateConfirmationYesButton, "Visibility", timeOut, "");
	}
}
