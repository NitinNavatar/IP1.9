/**
 * 
 */
package com.navatar.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.navatar.generic.BaseLib;
import com.navatar.generic.CommonLib.Mode;
import com.navatar.generic.CommonLib.Workspace;
import com.navatar.generic.CommonLib.action;

import static com.navatar.generic.CommonLib.*;
import static com.navatar.generic.CommonVariables.*;
import java.util.List;
/**
 * @author Parul Singh
 *
 */
public class ContactPage extends BasePageBusinessLayer{

	/**
	 * @param driver
	 */
	public ContactPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//span[text()='First Name']/..//following-sibling::input")
	private WebElement contactFirstName;

	/**
	 * @return the contactFirstName
	 */
	public WebElement getContactFirstName(int timeOut) {
		return isDisplayed(driver, contactFirstName, "Visibility", timeOut, "Contact First Name");
	}
	
	@FindBy(xpath="//span[text()='Last Name']/..//following-sibling::input")
	private WebElement contactLastName;

	/**
	 * @return the contactLastName
	 */
	public WebElement getContactLastName(int timeOut) {
		return isDisplayed(driver, contactLastName, "Visibility", timeOut, "Contact Last Name");
	}
	@FindBy(xpath = "//input[@title='Edit']")
	private WebElement editButtonContactsPage;
	
	
	/**
	 * @return the editButtonContactsPage
	 */
	public WebElement getEditButtonContactsPage(int timeOut) {
		return isDisplayed(driver, editButtonContactsPage, "Visibility", timeOut, "edit button contacts page");
	}

	@FindBy(xpath = "//input[@value='Delete']")
	private WebElement deleteButtonContactsPage;
	
	/**
	 * @return the deleteButtonContactsPage
	 */
	public WebElement getDeleteButtonContactsPage(int timeOut) {
	//	return isDisplayed(driver, deleteButtonContactsPage, "Visibility", timeOut, "delete button contacts page");
		WebElement ele = BaseLib.edriver.findElement(By.cssSelector("input[value=Delete]"));
		 return ele;
	}

	@FindBy(xpath="//div[@class='requiredInput']//span//input")
	private WebElement legalName;

	/**
	 * @return the legalName
	 */
	public WebElement getLegalName(int timeOut) {
		return isDisplayed(driver, legalName, "Visibility", timeOut, "Legal Name");
	} 
	
	@FindBy(xpath="//table[@class='detailList']//input[@name='con15']")
	private WebElement emailId;

	/**
	 * @return the emailId
	 */
	public WebElement getEmailId(int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, emailId_Clasic, "Visibility", timeOut, "Email Id Classic");
		}else{
			return isDisplayed(driver, emailId_Lighting, "Visibility", timeOut, "Email Id Lighting");
		}
	}
	@FindBy(xpath = "//td[text()='Email']/../td/div/a")
	private WebElement emailIdViewMode;
	
	@FindBy(xpath = "//*[text()='Email']/following-sibling::p//a")
	private WebElement emailIdViewModeLightning;
	
	/**
	 * @return the emailIdViewMode
	 */
	public WebElement getEmailIdViewMode(int timeOut) {
		
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, emailIdViewMode, "Visibility", timeOut, "email id in view mode");
		}else{
			return isDisplayed(driver, emailIdViewModeLightning, "Visibility", timeOut, "email id in view mode");

		}
		
	}

	@FindBy(xpath = "//span[@id='myGridfundr-scroll-box']")
	private WebElement scrollBoxContactPage;
	
	


	/**
	 * @param workSpace TODO
	 * @return the scrollBoxContactPage
	 */
	public WebElement getScrollBoxContactPage(int timeOut, Workspace workSpace) {
		WebElement ele ;
		if(workSpace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			ele = FindElement(driver,"//span[@id='myGridfundr-scroll-box']" , "scroll box fundraising workspace", action.SCROLLANDBOOLEAN, 60);
		}else{
			ele = FindElement(driver,"//span[@id='myGrid-scroll-box']" , "scroll box investor workspace", action.SCROLLANDBOOLEAN, 60);
		}
		return isDisplayed(driver, ele, "Visibility", timeOut, "scroll box on contact page");
	}

	@FindBy(xpath = "//a[@title='Remove']")
	private WebElement removeLink;
	
	public WebElement getremoveLink(int timeOut) {
		return isDisplayed(driver, removeLink, "Visibility", timeOut, "Contact Full Name In View Mode");
	}
	@FindBy(xpath="//div[@class='pbSubsection']//div[@id='con2_ileinner']")
	private WebElement contactFullNameInViewMode;

	/**
	 * @return the contactFullNameLabel
	 */
	public WebElement getContactFullNameInViewMode(int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, contactFullNameInViewMode_Classic, "Visibility", timeOut, "Contact Full Name In View Mode Classic");
		}else{
			return isDisplayed(driver, contactFullNameInViewMode_Lighting, "Visibility", timeOut, "Contact Full Name In View Mode Lighting");
		}
	}
	
	@FindBy(xpath="//iframe[@title='Investor_Portal_Contact_Enabled']")
	private WebElement workspaceFrameIncontactPage;

	/**
	 * @return the workspaceFrameIncontactPage
	 */
	public WebElement getWorkspaceFrameIncontactPage(int timeOut) {
		return isDisplayed(driver, workspaceFrameIncontactPage, "Visibility", timeOut, "Workspace frame in contact page");
	}
	
	@FindBy(xpath="//div[@id='errFrWs']//span")
	private WebElement errorMessageAfterAdminAndCRMUserRegistrationFundRaisingWorkspace;


	/**
	 * @return the errorMessageAfterAdminRegistrationFundRaisingWorkspace
	 */
	public WebElement getErrorMessageAfterAdminAndCRMUserRegistrationFundRaisingWorkspace(int timeOut) {
		return isDisplayed(driver, errorMessageAfterAdminAndCRMUserRegistrationFundRaisingWorkspace, "Visibility", timeOut, "Error Message after admin and CRM User Registration fundraising workspace");
	}
	
	@FindBy(xpath="//div[@id='errInvWs']//span")
	private WebElement errorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace;


	/**
	 * @return the errorMessageAfterAdminRegistrationInvestorWorkspace
	 */
	public WebElement getErrorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace(int timeOut) {
		return isDisplayed(driver, errorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace, "Visibility", timeOut, "Error Message after admin and CRM User Registration Investor workspace");
	}
	
	@FindBy(xpath = "//span[@id='myGrid-scroll-box']")
	private WebElement scrollBoxContactPageInvestorSpace;
	
	
	/**
	 * @return the scrollBoxContactPageInvestorSpace
	 */
	public WebElement getScrollBoxContactPageInvestorSpace(int timeOut) {
		return isDisplayed(driver, scrollBoxContactPageInvestorSpace, "Visibility", timeOut, "scroll box on contact page");
	}
	
	public WebElement getRemoveContactAccessButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "idfundr";
		} else {
			workspaceSelector = "idInves";
		}
//		return isDisplayed(driver, FindElement(driver, "//div[@id='div"+workspaceSelector+"Workspace']//a[contains(text(),'Remove Contact') and contains(text(),'Access')]", workspace+" Remove contact access button", action.BOOLEAN, 30), "visibility", 60, workspace+" Remove contact access button");
		return isDisplayed(driver, FindElement(driver, "//a[@id='"+workspaceSelector+"']", workspace+" Remove contact access button", action.BOOLEAN, 30), "visibility", 60, workspace+" Remove contact access button");
	}

	public WebElement getRemoveContactAccessPopupCloseButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='RemoveContactAccessPopUp"+workspaceSelector+"']//a[text()='Close']", workspace+" Remove contact access button", action.BOOLEAN, 30), "visibility", 60, workspace+" Remove contact access button");
	}
	
	@FindBy(xpath="//h3[text()='Activity History']")
	private WebElement contactActivityHistory;

	/**
	 * @return the contactActivityHistory
	 */
	public WebElement getContactActivityHistory(int timeOut) {
		return isDisplayed(driver, contactActivityHistory, "Visibility", timeOut, "Contact Activity History");
	}
	@FindBy(xpath="//div[contains(@id,'RelatedHistoryList_body')]/table/tbody/tr/td[2]/a")
	private WebElement contactActivityAlertAccountNameText;
	/**
	 * @return the contactActivityAlertAccountNameText
	 */
	public WebElement getContactActivityAlertAccountNameText(int timeOut) {
		return isDisplayed(driver, contactActivityAlertAccountNameText, "Visibility", timeOut, "Target Account Name in Activity Alert");
	}
	
	@FindBy(xpath="//div[contains(@id,'RelatedHistoryList_body')]/table/tbody/tr[2]/th/a")
	private WebElement contactAcitivityAlertSubjectLink;


	/**
	 * @return the contactAcitivityAlertSubjectLink
	 */
	public WebElement getContactAcitivityAlertSubjectLink(int timeOut) {
		return isDisplayed(driver, contactAcitivityAlertSubjectLink, "Visibility", timeOut, "Contact Activity Alert Subject Email Id");
	}
	
	@FindBy(xpath="//td[text()='Assigned To']/../td[2]//a[2]")
	private WebElement ContactAcitivityAlertAssignedToEmailText;


	/**
	 * @return the contactAcitivityAlertAssignedToEmailText
	 */
	public WebElement getContactAcitivityAlertAssignedToEmailText(int timeOut) {
		return isDisplayed(driver, ContactAcitivityAlertAssignedToEmailText, "Visibility", timeOut, "Contact Activity Alert Assigned to email id");
	}
	
	@FindBy(xpath="//td[text()='Subject']/../td[2]/div")
	private WebElement ContactAcitivityAlertSubjectText;


	/**
	 * @return the contactAcitivityAlertSubjectText
	 */
	public WebElement getContactAcitivityAlertSubjectText(int timeOut) {
		return isDisplayed(driver, ContactAcitivityAlertSubjectText, "Visibility", timeOut, "Contact Activity Alert Subject Text");
	}
	
	@FindBy(xpath="//td[text()='Due Date']/../td[2]/div")
	private WebElement contactActivityAlertDueDateText;


	/**
	 * @return the contactActivityAlertDueDateText
	 */
	public WebElement getContactActivityAlertDueDateText(int timeOut) {
		return isDisplayed(driver, contactActivityAlertDueDateText, "Visibility", timeOut, "Contact Activity Alert Due Date Text");
	}
	
	@FindBy(xpath="//td[text()='Comments']/../td[2]/div")
	private WebElement contactAcitivityAlertCommentsText;


	/**
	 * @return the contactAcitivityAlertCommentsText
	 */
	public WebElement getContactAcitivityAlertCommentsText(String mode,int timeOut) {
		String xpath="";
		if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			xpath="//span[text()='Comments']/../following-sibling::div//span[contains(@class,'Text')]";
			return isDisplayed(driver, FindElement(driver, xpath, "comments", action.SCROLLANDBOOLEAN, timeOut), "Visibility", timeOut, "Contact Activity Alert Comments Text");

		}
		else
		return isDisplayed(driver, contactAcitivityAlertCommentsText, "Visibility", timeOut, "Contact Activity Alert Comments Text");
	}
	
	@FindBy(xpath="//td[text()='Status']/..//td[4]/div")
	private WebElement contactActivityAlertStatusText;


	/**
	 * @return the contactActivityAlertStatusText
	 */
	public WebElement getContactActivityAlertStatusText(int timeOut) {
		return isDisplayed(driver, contactActivityAlertStatusText, "Visibility", timeOut, "Contact Activity Alert Status text");
	}
	
	@FindBy(xpath="//td[text()='Priority']/../td[2]/div")
	private WebElement contactActivityAlertPriorityText;


	/**
	 * @return the contactActivityAlertPriorityText
	 */
	public WebElement getContactActivityAlertPriorityText(int timeOut) {
		return isDisplayed(driver, contactActivityAlertPriorityText, "Visibility", timeOut, "Contact Activity Alert Priority text");
	}
	
	@FindBy(xpath="//td[text()='Email']/../td[4]//a")
	private WebElement contactActivityAlertEmailIdtext;


	/**
	 * @return the contactActivityAlertEmailIdtext
	 */
	public WebElement getContactActivityAlertEmailIdtext(int timeOut) {
		return isDisplayed(driver, contactActivityAlertEmailIdtext, "Visibility", timeOut, "Contact Activity Alert Email Id text");
	}
	
	@FindBy(xpath="//td[text()='Created By']/../td[2]//a")
	private WebElement contactActivityAlertCreatedByText;


	/**
	 * @return the contactActivityAlertCreatedByText
	 */
	public WebElement getContactActivityAlertCreatedByText(int timeOut) {
		return isDisplayed(driver, contactActivityAlertCreatedByText, "Visibility", timeOut, "contact Activity Alert Created By text");
	}
	@FindBy(xpath="//td[text()='Related To']/../td[4]//a")
	private WebElement AccountActivityAlertRelatedToText;

	/**
	 * @return the accountActivityAlertRelatedToText
	 */
	public WebElement getAccountActivityAlertRelatedToText(int timeOut) {
		return isDisplayed(driver, AccountActivityAlertRelatedToText, "Visibility", timeOut, "contact Activity Alert Related To Text");
	}
	
	
	@FindBy(xpath="//td[text()='Name']/..//td[4]//a")
	private WebElement AccountActivityAlertNameText;

	/**
	 * @return the accountActivityAlertRelatedToText
	 */
	public WebElement getAccountActivityAlertNameText(int timeOut) {
		return isDisplayed(driver, AccountActivityAlertNameText, "Visibility", timeOut, "contact Activity Alert Name Text");
	}
	
	@FindBy(xpath="//td[text()='Created By']/../td[2]//div")
	private WebElement contactActivityAlertCreatedByDate;

	/**
	 * @return the contactActivityAlertCreatedByDate
	 */
	public WebElement getContactActivityAlertCreatedByDate(int timeOut) {
		return isDisplayed(driver, contactActivityAlertCreatedByDate, "Visibility", timeOut, "contact Activity Alert Created by dtae");
	}
	
	
	@FindBy(xpath="//td[text()='Last Modified By']/..//td[2]//a")
	private WebElement contactActivityAlertLastModifiedName;

	/**
	 * @return the contactActivityAlertCreatedByDate
	 */
	public WebElement getContactActivityAlertLastModifiedName(int timeOut) {
		return isDisplayed(driver, contactActivityAlertLastModifiedName, "Visibility", timeOut, "contact Activity Alert Last Modified NAme");
	}
	
	@FindBy(xpath="//td[text()='Last Modified By']/..//td//div")
	private WebElement contactActivityAlertLastModifiedDate;

	/**
	 * @return the contactActivityAlertCreatedByDate
	 */
	public WebElement getContactActivityAlertLastModifiedDate(int timeOut) {
		return isDisplayed(driver, contactActivityAlertLastModifiedDate, "Visibility", timeOut, "contact Activity Alert Last Modified Date");
	}
	
	@FindBy(xpath="//h3[text()='Activity History']/../../../../..//following-sibling::div//th")
	private WebElement contactActivityHistoryErrorMessage;

	/**
	 * @return the contactActivityHistoryErrorMessage
	 */
	public WebElement getContactActivityHistoryErrorMessage(int timeOut) {
		return isDisplayed(driver, contactActivityHistoryErrorMessage, "Visibility", timeOut, "Contact Activity History Error Messgae");
	}
	
	
	public List<WebElement> getAllContactsList() {
		return FindElements(driver, "//div[contains(@class,'x-grid3-cell-inner x-grid3-col-FULL_NAME')]//span", "All Contacts");
	}
	
	@FindBy(xpath="//div[@id='contactHeaderRow']/div[(@class='textBlock')]/h2")
	private WebElement contactHeaderNameTextBlock;
	
	/**
	 * @return the contactHeaderNameTextBlock
	 */
	public WebElement getContactHeaderNameTextBlock(int timeOut) {
		return isDisplayed(driver, contactHeaderNameTextBlock, "Visibility", timeOut, "Contact Header Name Text Block");
	}
	

	public WebElement getViewAccessIcon(Workspace workspace, int timeOut) {
		String w="";
		if (workspace == Workspace.InvestorWorkspace)
			w="2";
		else
			w="1";
		return isDisplayed(driver, FindElement(driver, "//div[@id='header"+w+"']//img[@title='View']", "view access icon", action.SCROLLANDBOOLEAN, timeOut), "Visibility", timeOut, "view access icon");
	}

	public WebElement getDownloadAccessIcon(Workspace workspace,int timeOut) {
		String w="";
		if (workspace == Workspace.InvestorWorkspace)
			w="2";
		else
			w="1";
		return isDisplayed(driver, FindElement(driver, "//div[@id='header"+w+"']//img[@title='Download']", "Download access icon", action.SCROLLANDBOOLEAN, timeOut), "Visibility", timeOut, "Download access icon");
	}
	
	public WebElement getUploadAccessIcon(Workspace workspace,int timeOut) {
		String w="";
		if (workspace == Workspace.InvestorWorkspace)
			w="2";
		else
			w="1";
		return isDisplayed(driver, FindElement(driver, "//div[@id='header"+w+"']//img[@title='Upload']", "Upload access icon", action.SCROLLANDBOOLEAN, timeOut), "Visibility", timeOut, "Upload access icon");
	}
	
	
	public WebElement getResetPasswordInactiveButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "IW";
		}
		return isDisplayed(driver, FindElement(driver, "//a[@id='resetDeActive"+workspaceSelector+"']", workspace+" Reset Password Inactive button", action.BOOLEAN, 30), "visibility", 60, workspace+" Reset Password Inactive button");
	}
	
	public WebElement getResetPasswordActiveButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "IW";
		}
		return isDisplayed(driver, FindElement(driver, "//a[@id='resetActive"+workspaceSelector+"']", workspace+" Reset Password Active button", action.BOOLEAN, 30), "visibility", 60, workspace+" Reset Password Active button");
	}
	
	@FindBy(xpath="//div[contains(@class,'ResetPassword_fancybox FancyboxContainer ui-draggable')]/div[text()='Reset Password']")
	private WebElement resetPasswordpopupHeader;

	/**
	 * @return the resetPasswordpopupHeader
	 */
	public WebElement getResetPasswordpopupHeader(int timeOut) {
		return isDisplayed(driver, resetPasswordpopupHeader, "Visibility", timeOut, "Reset password popup header");
	}
	
	//@FindBy(xpath="//div[@class='OneDiv']/div")
	@FindBy(xpath="//div[@class='Confirmation_fancybox FancyboxContainer ui-draggable']//div[@class='formbox contacts_n_name_div']")
	private WebElement resetPasswordPopupMessage;

	/**
	 * @return the resetPasswordPopupMessage
	 */
	public WebElement getResetPasswordPopupMessage(int timeOut) {
		return isDisplayed(driver, resetPasswordPopupMessage, "Visibility", timeOut, "Reset Passowrd popup message");
	}
	
	@FindBy(xpath="//div[@class='OneDiv']/div/b")
	private WebElement resetPasswordPopupMessageContactName;

	/**
	 * @return the resetPasswordPopupMessage
	 */
	public WebElement getResetPasswordPopupMessageContactName(int timeOut) {
		return isDisplayed(driver, resetPasswordPopupMessageContactName, "Visibility", timeOut, "Reset Passowrd popup message Contact name");
	}
	
	@FindBy(xpath="//div[@class='ResetPassword_fancybox FancyboxContainer ui-draggable']//div[@class='formbox contacts_n_name_div']")
	private WebElement resetPasswordpopupHeaderMessage;
	
	/**
	 * @return the resetPasswordpopupMessage
	 */
	public WebElement getResetPasswordpopupHeaderMessage(int timeOut) {
		return isDisplayed(driver, resetPasswordpopupHeaderMessage, "Visibility", timeOut, "Reset password popup mesage");
	}

	@FindBy(xpath="//input[@id='sendEmailRd']")
	private WebElement resetPasswordPopupsendMailRadioButton;

	/**
	 * @return the resetPasswordPopupsendMailRadioButton
	 */
	public WebElement getResetPasswordPopupsendMailRadioButton(int timeOut) {
		return isDisplayed(driver, resetPasswordPopupsendMailRadioButton, "Visibility", timeOut, "reset password popup send email radio button");
	}
	
	@FindBy(xpath="(//input[@id='sendEmailRd']/..//following-sibling::div/div)[1]")
	private WebElement resetPasswordPopupSendMailText1;

	/**
	 * @return the resetPasswordPopupSendMailText
	 */
	public WebElement getResetPasswordPopupSendMailText1(int timeOut) {
		return isDisplayed(driver, resetPasswordPopupSendMailText1, "Visibility", timeOut, "reset Passowrd popup  send email text 1");
	}
	
	@FindBy(xpath="(//input[@id='sendEmailRd']/..//following-sibling::div/div)[2]")
	private WebElement resetPasswordPopupSendMailText2;

	/**
	 * @return the resetPasswordPopupSendMailText
	 */
	public WebElement getResetPasswordPopupSendMailText2(int timeOut) {
		return isDisplayed(driver, resetPasswordPopupSendMailText2, "Visibility", timeOut, "reset Passowrd popup  send email text 2");
	}
	
	@FindBy(xpath="//input[@id='cpLinkRd']")
	private WebElement resetPasswordPopupcopyLinkRadioButton;

	/**
	 * @return the resetPasswordPopupcopyLinkRadioButton
	 */
	public WebElement getResetPasswordPopupcopyLinkRadioButton(int timeOut) {
		return isDisplayed(driver, resetPasswordPopupcopyLinkRadioButton, "Visibility", timeOut, "Reset Pasword Popup Copy link radio button");
	}
	
	@FindBy(xpath="//a[@title='Send Email']")
	private WebElement resetPasswordPopupSendEMailButton;

	/**
	 * @return the resetPasswordPopupSendEMailButton
	 */
	public WebElement getResetPasswordPopupSendEMailButton(int timeOut) {
		return isDisplayed(driver, resetPasswordPopupSendEMailButton, "Visibility", timeOut, "Reset Password Popup Send Email Button");
	}
	
	@FindBy(xpath="//a[@title='Copy Link']")
	private WebElement resetPasswordPopupCopyLinkButton;

	/**
	 * @return the resetPasswordPopupSendEMailButton
	 */
	public WebElement getResetPasswordPopupCopyEmailButton(int timeOut) {
		return isDisplayed(driver, resetPasswordPopupCopyLinkButton, "Visibility", timeOut, "Reset Password Popup Copy Email Button");
	}
	
	@FindBy(xpath="//a[@id='cancelBtn']")
	private WebElement resetPasswordPopupCancelButton;

	/**
	 * @return the resetPasswordPopupCancelButton
	 */
	public WebElement getResetPasswordPopupCancelButton(int timeOut) {
		return isDisplayed(driver, resetPasswordPopupCancelButton, "Visibility", timeOut, "Reset Password Popup Cancel Button");
	}
	
	@FindBy(xpath="//div[contains(@class,'ResetPassword_fancybox FancyboxContainer ui-draggable')]/div[text()='Reset Password']/a[@title='Close']")
	private WebElement resetPasswordPopupCloseIcon;

	/**
	 * @return the resetPasswordPopupCloseIcon
	 */
	public WebElement getResetPasswordPopupCloseIcon(int timeOut) {
		return isDisplayed(driver, resetPasswordPopupCloseIcon, "Visibility", timeOut, "Reset Password Popup Close Icon");
	}
	
	@FindBy(xpath="//input[@id='cpLinkRd']/..")
	private WebElement resetPasswordCopyLinkText;

	/**
	 * @return the resetPasswordCopyLinkText
	 */
	public WebElement getResetPasswordCopyLinkText(int timeOut) {
		return isDisplayed(driver, resetPasswordCopyLinkText, "Visibility", timeOut, "reset Password Copy Link Text");
	}
	
	@FindBy(xpath="//div[@class='Confirmation_fancybox FancyboxContainer ui-draggable']//div[@class='head_popup']")
	private WebElement resetPasswordPopupHeaderAfterSendEMail;

	public WebElement getResetPasswordPopupHeaderAfterSendEMail(int timeOut) {
		return isDisplayed(driver, resetPasswordPopupHeaderAfterSendEMail, "Visibility", timeOut, "Reset Password popup header after clicking on send email");
	}
	@FindBy(xpath="//div[@class='Confirmation_fancybox FancyboxContainer ui-draggable']//div[@class='formbox contacts_n_name_div']")
	private WebElement resetPasswordMessageAfterSendEMail;


	/**
	 * @return the resetPasswordMessageAfterSendEMail
	 */
	public WebElement getResetPasswordMessageAfterSendEMail(int timeOut) {
		return isDisplayed(driver, resetPasswordMessageAfterSendEMail, "Visibility", timeOut, "Reset Password message after clicking on send email");
	}
	
	@FindBy(xpath="//div[@class='Confirmation_fancybox FancyboxContainer ui-draggable']//div[@class='head_popup']/a[@title='Close']")
	private WebElement resetPasswordPopupCrossIconAfterSendEMail;

	public WebElement getResetPasswordPopupCrossIconAfterSendEMail(int timeOut) {
		return isDisplayed(driver, resetPasswordPopupCrossIconAfterSendEMail, "Visibility", timeOut, "Reset Password popup cross icon after clicking on send email");
	}
	
	@FindBy(xpath="//div[@class='Confirmation_fancybox FancyboxContainer ui-draggable']//a[@title='OK']")
	private WebElement resetPasswordOKButton;

	/**
	 * @return the resetPasswordOKButton
	 */
	public WebElement getResetPasswordOKButton(int timeOut) {
		return isDisplayed(driver, resetPasswordOKButton, "Visibility", timeOut, "Reset Password OK Button");
	}
	
	public WebElement getResetPasswordPopupCopyEmailText(int timeOut) {
		return FindElement(driver, "//div[@id='flashMsg']//div", "Reset Password popup copyemail text", action.SCROLLANDBOOLEAN, timeOut);
				
	}
	
	
	public WebElement getAlertHistoryShowDropDownList(Workspace workspace, int timeOut) {
		String xpath="";
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			xpath="FR";
		}else if(workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			xpath="INV";
		}	
		return isDisplayed(driver,FindElement(driver, "//select[@id='range1ACTALT"+xpath+"']", "Bulk DownLoad CheckBox"+workspace, action.SCROLLANDBOOLEAN, timeOut), "Visibility", timeOut, "Bulk DownLoad CheckBox"+workspace);
	}
	
	
	public WebElement getAlertHistoryRangeDropDownList(Workspace workspace, int timeOut) {
		String xpath="";
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			xpath="FR";
		}else if(workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			xpath="INV";
		}	
		return isDisplayed(driver,FindElement(driver, "//select[@id='range2ACTALT"+xpath+"']", "Bulk DownLoad CheckBox"+workspace, action.SCROLLANDBOOLEAN, timeOut), "Visibility", timeOut, "Bulk DownLoad CheckBox"+workspace);
	}
	
	@FindBy(xpath="//td[@class='pbButton']//input[@name='newContact']")
	private WebElement newContactBtn_Classic;
	
	@FindBy(xpath="//span[contains(text(),'Contacts')]/ancestor::header/following-sibling::div[@class='slds-no-flex']//a")
	private WebElement newContactBtn_Lighting;
	
	/**
	 * @param environment
	 * @param mode
	 * @param timeOut
	 * @return
	 */
	public WebElement getNewContactBtn(String environment,String mode,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, newContactBtn_Classic, "Visibility", timeOut, "new contact button Classic");
		}else{
			return isDisplayed(driver, newContactBtn_Lighting, "Visibility", timeOut, "new contact butto Lighting");
		}
	}
	
	
	@FindBy(xpath="//input[@name='name_firstcon2']")
	private WebElement contactFirstName_Classic;
	
	@FindBy(xpath="//*[text()='First Name']/following-sibling::*//input")
	private WebElement contactFirstName_Lighting;

	/**
	 * @return the contactFirstName
	 */
	public WebElement getContactFirstName(String environment,String mode,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, contactFirstName_Classic, "Visibility", timeOut, "Contact First Name Classic");
		}else{
			return isDisplayed(driver, contactFirstName_Lighting, "Visibility", timeOut, "Contact First Name Lighting");
		}
	
	}
	
	@FindBy(xpath="//input[@name='name_lastcon2']")
	private WebElement contactLastName_Classic;
	
	@FindBy(xpath="//*[text()='Last Name']/following-sibling::*//input")
	private WebElement contactLastName_Lighting;

	/**
	 * @return the contactLastName
	 */
	public WebElement getContactLastName(String environment,String mode,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, contactLastName_Classic, "Visibility", timeOut, "Contact Last Name Classic");
		}else{
			return isDisplayed(driver, contactLastName_Lighting, "Visibility", timeOut, "Contact Last Name Lighting");
		}
		
	}
	
	
	@FindBy(xpath="//div[@class='requiredInput']//span//input")
	private WebElement legalName_Classic;
	
	@FindBy(xpath="//*[text()='Legal Name']/following-sibling::*//input[contains(@placeholder,'Search Institutions')]")
	private WebElement legalName_Lighting;

	/**
	 * @return the legalName
	 */
	public WebElement getLegalName(String environment,String mode,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, legalName_Classic, "Visibility", timeOut, "Legal Name Classic");
		}else{
			return isDisplayed(driver, legalName_Lighting, "Visibility", timeOut, "Legal Name Lighting");
		}
	
	} 
	
	
	@FindBy(xpath="//table[@class='detailList']//input[@name='con15']")
	private WebElement emailId_Clasic;
	

	@FindBy(xpath="//*[text()='Email']/following-sibling::*//input[@inputmode='email' or @type='email']")
	private WebElement emailId_Lighting;

	/**
	 * @return the emailId
	 */
	public WebElement getEmailId(String environment,String mode,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, emailId_Clasic, "Visibility", timeOut, "Email Id Classic");
		}else{
			return isDisplayed(driver, emailId_Lighting, "Visibility", timeOut, "Email Id Lighting");
		}
		
	}
	
	@FindBy(xpath="//div[@class='pbSubsection']//div[@id='con2_ileinner']")
	private WebElement contactFullNameInViewMode_Classic;
	
	@FindBy(xpath="//span[@class='custom-truncate uiOutputText']")
	private WebElement contactFullNameInViewMode_Lighting;

	/**
	 * @return the contactFullNameLabel
	 */
	public WebElement getContactFullNameInViewMode(String environment,String mode,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, contactFullNameInViewMode_Classic, "Visibility", timeOut, "Contact Full Name In View Mode Classic");
		}else{
			return isDisplayed(driver, contactFullNameInViewMode_Lighting, "Visibility", timeOut, "Contact Full Name In View Mode Lighting");
		}
		
	}
	
	public WebElement getContactPageTextBoxOrRichTextBoxWebElement(String environment,String mode, String labelName, int timeOut) {
		WebElement ele=null;
		String xpath ="",inputXpath="", textAreaXpath="",finalXpath="",finalLabelName="";
		
		if(labelName.equalsIgnoreCase(excelLabel.Mailing_State.toString())) {
			labelName=ContactPageFieldLabelText.Mailing_State.toString();
		}else if (labelName.equalsIgnoreCase(excelLabel.Mailing_Zip.toString())) {
			labelName=ContactPageFieldLabelText.Mailing_Zip.toString();
		}else if (labelName.equalsIgnoreCase(excelLabel.Other_State.toString())) {
			labelName=ContactPageFieldLabelText.Other_State.toString();
		}else if (labelName.equalsIgnoreCase(excelLabel.Other_Zip.toString())) {
			labelName=ContactPageFieldLabelText.Other_Zip.toString();
		}
		
		if(labelName.contains("_")) {
			finalLabelName=labelName.replace("_", " ");
		}else {
			finalLabelName=labelName;
		}
		
		if(mode.equalsIgnoreCase(Mode.Classic.toString())) {
			xpath="//label[text()='"+finalLabelName+"']";
			inputXpath="/../following-sibling::td//input";
			textAreaXpath="/../following-sibling::td//textarea";
		}else {
			//span[text()='Description']/..//following-sibling::textarea
			xpath="//*[text()='"+finalLabelName+"']";
			inputXpath="/following-sibling::*//input";
			textAreaXpath="/following-sibling::*//textarea";
		}
		
		if(labelName.equalsIgnoreCase(ContactPageFieldLabelText.Description.toString()) || labelName.equalsIgnoreCase(ContactPageFieldLabelText.Mailing_Street.toString()) || 
			labelName.equalsIgnoreCase(ContactPageFieldLabelText.Other_Street.toString())) {
			finalXpath=xpath+textAreaXpath;
		}else {
			finalXpath=xpath+inputXpath;
		}
		ele=isDisplayed(driver, FindElement(driver, finalXpath, finalLabelName+" text box in "+mode, action.SCROLLANDBOOLEAN,30), "Visibility", timeOut, finalLabelName+"text box in "+mode);
		return ele;
		
	}

}
