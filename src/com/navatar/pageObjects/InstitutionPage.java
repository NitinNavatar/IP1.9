/**
 * 
 */
package com.navatar.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.navatar.generic.ExcelUtils;

import static com.navatar.generic.CommonLib.*;
import java.util.List;
/**
 * @author Parul Singh
 *
 */
public class InstitutionPage extends BasePageBusinessLayer{

	/**
	 * @param driver
	 */
	public InstitutionPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath="//select[@name='p3']")
	private WebElement recordTypeOfNewRecordDropDownList;


	/**
	 * @return the recordTypeOfNewRecordDropDownList
	 */
	public WebElement getRecordTypeOfNewRecordDropDownList(int timeOut) {
		return isDisplayed(driver, recordTypeOfNewRecordDropDownList, "Visibility", timeOut, "Record type of new record drop down list");
	}
	
	@FindBy(xpath = "//select[@id='p3']")
	private WebElement recordType;
	
	
	/**
	 * @return the recordType
	 */
	public WebElement getRecordType() {
		return isDisplayed(driver, recordType, "Visibility", 60, "Record type");
	}


	/**
	 * @param recordType the recordType to set
	 */
	public void setRecordType(WebElement recordType) {
		this.recordType = recordType;
	}


	@FindBy(xpath = "//input[@name='new']")
	private WebElement newButton;
	
	
	/**
	 * @return the newButton
	 */
	public WebElement getNewButton() {
		return isDisplayed(driver, newButton, "Visibility", 60, "New button in institution");
	}


	/**
	 * @param newButton the newButton to set
	 */
	public void setNewButton(WebElement newButton) {
		this.newButton = newButton;
	}


	@FindBy(xpath="//input[@title='Continue']")
	private WebElement continueBtn;


	/**
	 * @return the continueBtn
	 */
	public WebElement getContinueBtn(int timeOut) {
		return isDisplayed(driver, continueBtn, "Visibility", timeOut, "Continue Button");
	}

	
	@FindBy(xpath="//input[@title='Cancel']")
	private WebElement cancelBtn;


	/**
	 * @return the cancelBtn
	 */
	public WebElement getCancelBtn(int timeOut) {
		return isDisplayed(driver, cancelBtn, "Visibility", timeOut, "Cancel Button");
	}
	
	@FindBy(xpath="//input[@name='acc2']")
	private WebElement legalNameTextBox;


	/**
	 * @return the legalNameTextBox
	 */
	public WebElement getLegalNameTextBox(int timeOut) {
		return isDisplayed(driver, legalNameTextBox, "Visibility", timeOut, "Legal Name Text Box");
	}
	
	@FindBy(xpath="//input[@name='acc3']")
	private WebElement parentInstitutionTextBox;


	/**
	 * @return the parentInstitutionTextBox
	 */
	public WebElement getParentInstitutionTextBox(int timeOut) {
		return isDisplayed(driver, parentInstitutionTextBox, "Visibility", timeOut, "Parent Institution Text Box");
	}
	
	@FindBy(xpath="//div[@id='acc2_ileinner']")
	private WebElement legalNameLabelTextbox;


	/**
	 * @return the legalNameLabelTextbox
	 */
	public WebElement getLegalNameLabelTextbox(int timeOut) {
		return isDisplayed(driver, legalNameLabelTextbox, "Visibility", timeOut, "Legal Name Label Text Box");
	}
	
	@FindBy(xpath="//iframe[@title='Investor_Portal_Institution_Enabled']")
	private WebElement workspaceFrame;


	/**
	 * @return the workspaceFrame
	 */
	public WebElement getWorkspaceFrame(int timeOut) {
		return isDisplayed(driver, workspaceFrame, "Visibility", timeOut, "Workspace frame on Instituition page");
	}
	
	@FindBy(xpath="//div[@id='errFrWs']//span")
	private WebElement errorMessageAfterAdminAndCRMUserRegistrationFundRaisingWorkspace;


	/**
	 * @return the errorMessageAfterAdminRegistrationFundRaisingWorkspace
	 */
	public WebElement getErrorMessageAfterAdminAndCRMUserRegistrationFundRaisingWorkspace(int timeOut) {
		return isDisplayed(driver, errorMessageAfterAdminAndCRMUserRegistrationFundRaisingWorkspace, "Visibility", timeOut, "Error Message after admin and CRM UserRegistration fundraising workspace");
	}
	
	@FindBy(xpath="//div[@id='errInvWs']//span")
	private WebElement errorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace;


	/**
	 * @return the errorMessageAfterAdminRegistrationInvestorWorkspace
	 */
	public WebElement getErrorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace(int timeOut) {
		return isDisplayed(driver, errorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace, "Visibility", timeOut, "Error Message after admin And CRM UserRegistration Investor workspace");
	}
	
	public List<WebElement> getAllInstitutions() {
		return FindElements(driver, "//div[contains(@class,'x-grid3-cell-inner x-grid3-col-Name')]//span", "All Institution");
	}
	
	public List<WebElement> getAllAccountscustomFields() {
		return FindElements(driver, "//div[@id='CustomFieldRelatedList_body']//th[@class=' dataCell  ']//a", "All Accounts Custom Fields");
	}
	
	@FindBy(xpath="//span[@class='dateInput dateOnlyInput']//input")
	private WebElement testDateATextBox;


	/**
	 * @return the testDateATextBox
	 */
	public WebElement getTestDateATextBox(int timeOut) {
		return isDisplayed(driver, testDateATextBox, "Visibility", timeOut, "Test Date A TextBox");
	}
	
	@FindBy(xpath="//span[@class='dateInput']//input")
	private WebElement testDateTimeATextBox;


	/**
	 * @return the testDateTimeATextBox
	 */
	public WebElement getTestDateTimeATextBox(int timeOut) {
		return isDisplayed(driver, testDateTimeATextBox, "Visibility", timeOut, "TestDateTimeATextBox");
	}
	
	public WebElement getTestEmailAtTextBox(String emailFieldLabel,int timeOut){
		WebElement ele =null;
		ele=isDisplayed(driver, FindElement(driver, "//label[text()='"+emailFieldLabel+"']/../..//input", "Email field label", action.SCROLLANDBOOLEAN, timeOut), "Visibility", timeOut, "Email field label");
		return ele;
	}

	@FindBy(xpath="(//label[text()='Current Allocation - Private Equity (mn)']/../..//input)[1]")
	private WebElement currentAllocationPrivateEquityTextBox;


	/**
	 * @return the currentAllocationPrivateEquityTextBox
	 */
	public WebElement getCurrentAllocationPrivateEquityTextBox(int timeOut) {
		return isDisplayed(driver, currentAllocationPrivateEquityTextBox, "Visibility", timeOut, "");
	}
	
	@FindBy(xpath="//label[text()='TestGeolocationA (Latitude)']/../..//input")
	private WebElement testGeolocationALatitudeTextBox;


	/**
	 * @return the testGeolocationALatitudeTextBox
	 */
	public WebElement getTestGeolocationALatitudeTextBox(int timeOut) {
		return isDisplayed(driver, testGeolocationALatitudeTextBox, "Visibility", timeOut, "Geolocation latitude Textbox");
	}
	
	@FindBy(xpath="//label[text()='TestGeolocationA (Longitude)']/../..//input") 
	private WebElement testGeolocationALongitudeTextBox;


	/**
	 * @return the testGeolocationALongitudeTextBox
	 */
	public WebElement getTestGeolocationALongitudeTextBox(int timeOut) {
		return isDisplayed(driver, testGeolocationALongitudeTextBox, "Visibility", timeOut, "Geolocation longitude Textbox");
	}
	
	@FindBy(xpath="(//label[text()='Employees']/../..//input)[1]")
	private WebElement employeesTextBox;


	/**
	 * @return the employeesTextBox
	 */
	public WebElement getEmployeesTextBox(int timeOut) {
		return isDisplayed(driver, employeesTextBox, "Visibility", timeOut, "EmployeesTextBox");
	}
	
	@FindBy(xpath="(//label[text()='Alternative %']/../..//input)[2]")
	private WebElement alternativePercentTextBox;


	/**
	 * @return the alternativePercentTextBox
	 */
	public WebElement getAlternativePercentTextBox(int timeOut) {
		return isDisplayed(driver, alternativePercentTextBox, "Visibility", timeOut, "Alternative Percent Textbox");
	}
	
	@FindBy(xpath="//label[text()='Phone']/../..//input")
	private WebElement phoneTextbox;


	/**
	 * @return the phoneTextbox
	 */
	public WebElement getPhoneTextbox(int timeOut) {
		return isDisplayed(driver, phoneTextbox, "Visibility", timeOut, "Phone Textbox");
	} 
	
	@FindBy(xpath="(//label[text()='Ownership']/../..//select)[1]")
	private WebElement ownershipDropdown;


	/**
	 * @return the ownershipDropdown
	 */
	public WebElement getOwnershipDropdown(int timeOut) {
		return isDisplayed(driver, ownershipDropdown, "Visibility", timeOut, "Ownership dropdown");
	}
	
	@FindBy(xpath="//label[text()='Keywords']/../..//input")
	private WebElement keywordsTextbox;


	/**
	 * @return the keywordsTextbox
	 */
	public WebElement getKeywordsTextbox(int timeOut) {
		return isDisplayed(driver, keywordsTextbox, "Visibility", timeOut, "Keywords TextBox");
	}
	
	@FindBy(xpath="//label[text()='Referral Source Description']/../..//textArea")
	private WebElement rreferralSourceDescTextBox;


	/**
	 * @return the rreferralSourceDescTextBox
	 */
	public WebElement getRreferralSourceDescTextBox(int timeOut) {
		return isDisplayed(driver, rreferralSourceDescTextBox, "Visibility", timeOut, "Referral Source Desc Text Box");
	} 
	 
	@FindBy(xpath="//label[text()='Website']/../..//input")
	private WebElement websiteTextBox;


	/**
	 * @return the websiteTextBox
	 */
	public WebElement getWebsiteTextBox(int timeOut) {
		return isDisplayed(driver, websiteTextBox, "Visibility", timeOut, "WebSiteText Box");
	} 
	 
	@FindBy(xpath="//label[text()='Invest in First-Time Funds ?']/../..//input")
	private WebElement investInFirstTimeCheckbox;


	/**
	 * @return the investInFirstTimeCheckbox
	 */
	public WebElement getInvestInFirstTimeCheckbox(int timeOut) {
		return isDisplayed(driver, investInFirstTimeCheckbox, "Visibility", timeOut, "Invest In First Checkbox");
	}
	
	public WebElement getTestTimeBetaATextBox(String TestTimeBetaAFieldLabel,int timeOut){
		WebElement ele =null;
		ele=isDisplayed(driver, FindElement(driver, "//label[text()='"+TestTimeBetaAFieldLabel+"']/../..//input", "Test timeTextbox", action.SCROLLANDBOOLEAN, timeOut), "Visibility", timeOut, "Test timeTextbox");
		return ele;
	} 
	
		
	@FindBy(xpath="//select[@title='Fund Preferences - Available']")
	private WebElement fundPreferenceMultiPickList;


	/**
	 * @return the fundPreferenceMultiPickList
	 */
	public WebElement getFundPreferenceMultiPickList(int timeOut) {
		return isDisplayed(driver, fundPreferenceMultiPickList, "Visibility", timeOut, "Funds Prefernece Multi picklist");
	}
	
	@FindBy(xpath="//label[text()='Fund Preferences']/../..//a[@title='Add']//img")
	private WebElement fundPreferncesAddButton;


	/**
	 * @return the fundPreferncesAddButton
	 */
	public WebElement getFundPreferncesAddButton(int timeOut) {
		return isDisplayed(driver, fundPreferncesAddButton, "Visibility", timeOut, "FundPrefernces Add button");
	} 
	
	@FindBy(xpath="//input[@value='New Advisor Involvement']")
	private WebElement newAdvisorInvolvementButton;


	/**
	 * @return the advisorInvolvementsTab
	 */
	public WebElement getNewAdvisorInvolvementButton(int timeOut) {
		return isDisplayed(driver, newAdvisorInvolvementButton, "Visibility", timeOut, "New Advisor involvement Button");
	} 
	 
	@FindBy(xpath="//label[text()='Fund']/../..//span//input")
	private WebElement fundtNameTextBox;


	/**
	 * @return the contactNameTextBox
	 */
	public WebElement getFundTextBox(int timeOut) {
		return isDisplayed(driver, fundtNameTextBox, "Visibility", timeOut, "Fund Name Text Box");
	}
	
	@FindBy(xpath="//label[text()='Diligence Expense Actuals']/../..//input")
	private WebElement dilgenceExpenseActualTextBox;


	/**
	 * @return the dilgenceExpenseActualTextBox
	 */
	public WebElement getDilgenceExpenseActualTextBox(int timeOut) {
		return isDisplayed(driver, dilgenceExpenseActualTextBox, "Visibility", timeOut, "Diligence Expense Actual Text Box");
	}
	
	@FindBy(xpath="//div[@id='Name_ileinner']")
	private WebElement advisorInvolvementID;


	/**
	 * @return the advisorInvolvementID
	 */
	public WebElement getAdvisorInvolvementID(int timeOut) {
		return isDisplayed(driver, advisorInvolvementID, "Visibility", timeOut, "Advisor Involvement ID");
	}
	
	@FindBy(xpath="//h3[contains(text(),'Attachments')]")
	private WebElement NoteAndAttachmentText;

	/**
	 * @return the noteAndActtachmentText
	 */
	public WebElement getNoteAndAttachmentText(int timeOut) {
		return isDisplayed(driver, NoteAndAttachmentText, "Visibility", timeOut, "Note and Attachment text");
	}
	
	public List<WebElement> getContactActivityHistoryNameRecords() {
		return FindElements(driver, "//div[contains(@id,'RelatedHistoryList_body')]/table/tbody/tr/td[2]/a", "Account Activity Alert target Accounts Names List");
	}
	
	public List<WebElement> getAccountActivityAlertSubjectEmailIdList(){
		return FindElements(driver, "//div[contains(@id,'RelatedHistoryList_body')]/table/tbody/tr[contains(@class,'dataRow')]/th/a", "Account Activity Alert List of Subject Email Id");
	}
	
	@FindBy(xpath="//td[text()='Related To']/../td[4]//a")
	private WebElement AccountActivityAlertRelatedToText;

	/**
	 * @return the accountActivityAlertRelatedToText
	 */
	public WebElement getAccountActivityAlertRelatedToText(int timeOut) {
		return isDisplayed(driver, AccountActivityAlertRelatedToText, "Visibility", timeOut, "Account Activity Alert Related To Text");
	}
	
	@FindBy(xpath="//td[text()='Name']/../td[4]//a")
	private WebElement AccountActivityAlertNameText;

	/**
	 * @return the accountActivityAlertNameText
	 */
	public WebElement getAccountActivityAlertNameText(int timeOut) {
		return isDisplayed(driver, AccountActivityAlertNameText, "Visibility", timeOut, "Account Activity Alert Name Text");
	}
}
