/**
 * 
 */
package com.navatar.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.navatar.generic.CommonLib.action;

import static com.navatar.generic.CommonLib.*;

import java.util.List;
/**
 * @author Parul Singh
 *
 */
public class CommitmentPage extends BasePageBusinessLayer{

	public CommitmentPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//label[text()='Limited Partner']/../..//span[@class='lookupInput']/input")
	private WebElement limitedPartnerTextbox;

	/**
	 * @return the limitedPArtnerTextbox
	 */
	public WebElement getLimitedPartnerTextbox(int timeOut) {
		return isDisplayed(driver, limitedPartnerTextbox, "Visibility", timeOut, "Limited Partner Text Box");
	}
	
	@FindBy(xpath="//label[text()='Partnership']/../..//span[@class='lookupInput']/input")
	private WebElement partnershipTextBox;

	/**
	 * @return the partnershipTextBox
	 */
	public WebElement getPartnershipTextBox(int timeOut) {
		return isDisplayed(driver, partnershipTextBox, "Visibility", timeOut, "Partnership Text Box");
	}
	
	@FindBy(xpath="//div[@id='Name_ileinner']")
	private WebElement commitmentIdInViewMode;

	/**
	 * @return the commitmentIdInViewMode
	 */
	public WebElement getCommitmentIdInViewMode(int timeOut) {
		return isDisplayed(driver, commitmentIdInViewMode, "Visibility", timeOut, "Commitment ID in View Mode");
	}
	
	@FindBy(xpath="//iframe[@title='Investor_Portal_Commitment_Enabled']")
	private WebElement workspaceFrameOnCommitmentPage;

	/**
	 * @return the workspaceFrameOnCommitmentPage
	 */
	public WebElement getWorkspaceFrameOnCommitmentPage(int timeOut) {
		return isDisplayed(driver, workspaceFrameOnCommitmentPage, "Visibility", timeOut, "Workspace Frame In Commitment Page");
	}

	@FindBy(xpath="//div[@id='errordiv']//span")
	private WebElement errorMessageAfterAdminAndCRMUserRegistration;

	/**
	 * @return the errorMessageAfterAdminRegistration
	 */
	public WebElement getErrorMessageAfterAdminAndCRMUserRegistration(int timeOut) {
		return isDisplayed(driver, errorMessageAfterAdminAndCRMUserRegistration, "Visibility", timeOut, "Error Message After Admin and CRM User Registration");
	}
	
	public List<WebElement> getAllCommitmentID() {
		return FindElements(driver, "//div[contains(@class,'x-grid3-cell-inner x-grid3-col-NAME')]//span", "All Commitment IDs");
	}
	
	@FindBy(xpath="//input[@value='OK']")
	private WebElement commitmentDeletedOKButton;

	/**
	 * @return the commitmentDeletedOKButton
	 */
	public WebElement getCommitmentDeletedOKButton(int timeOut) {
		return isDisplayed(driver, commitmentDeletedOKButton, "Visibility", timeOut, "Commitment Deleted OK Button");
	} 
	 
	@FindBy(xpath="//td[@id='topButtonRow']//input[@title='Delete']")
	private WebElement deleteButton;

	/**
	 * @return the deleteButton
	 */
	public WebElement getDeleteButton(int timeOut) {
		return isDisplayed(driver, deleteButton, "Visibility", timeOut, "Delete Button");
	} 
	
	@FindBy(xpath="//label[text()='Anti-money laundering']/../..//input")
	private WebElement antiMoneyLaunderingcheckbox;

	/**
	 * @return the antiMoneyLaunderingcheckbox
	 */
	public WebElement getAntiMoneyLaunderingcheckbox(int timeOut ) {
		return isDisplayed(driver, antiMoneyLaunderingcheckbox, "Visibility", timeOut, "AntiMoney Laundering checkbox");
	}
	
	@FindBy(xpath="//label[text()='Final Commitment Date']/../..//span[@class='dateInput dateOnlyInput']//input")
	private WebElement finalCommitmentDate;

	/**
	 * @return the finalCommitmentDate
	 */
	public WebElement getFinalCommitmentDate(int timeOut) {
		return isDisplayed(driver, finalCommitmentDate, "Visibility", timeOut, "Final Commitment Date");
	}
	
	@FindBy(xpath="//span[@class='dateInput']//input")
	private WebElement testDateTimeTextBox;

	/**
	 * @return the testDateTimeTextBox
	 */
	public WebElement getTestDateTimeTextBox(int timeOut) {
		return isDisplayed(driver, testDateTimeTextBox, "Visibility", timeOut, "Test Date Time Text Box");
	}
	
	public WebElement getTestEmailAtTextBox(String emailFieldLabel,int timeOut){
		WebElement ele =null;
		ele=isDisplayed(driver, FindElement(driver, "//label[text()='"+emailFieldLabel+"']/../..//input", "Email field label", action.SCROLLANDBOOLEAN, timeOut), "Visibility", timeOut, "Email field label");
		return ele;
	}
	
	@FindBy(xpath="//label[text()='Commitment Amount']/../..//input")
	private WebElement commitmentAmountCurrencyTextbox;

	/**
	 * @return the commitmentAmountCurrencyTextbox
	 */
	public WebElement getCommitmentAmountCurrencyTextbox(int timeOut) {
		return isDisplayed(driver, commitmentAmountCurrencyTextbox, "Visibility", timeOut, "Commitment Amount currency Text Box");
	}
	
	@FindBy(xpath="//label[text()='TestGeolocC (Latitude)']/../..//input")
	private WebElement testGeolocationLatitudeTextbox;

	/**
	 * @return the testGeolocationLatitudeTextbox
	 */
	public WebElement getTestGeolocationLatitudeTextbox(int timeOut) {
		return isDisplayed(driver, testGeolocationLatitudeTextbox, "Visibility", timeOut, "Test Geolocation latitude checkbox");
	}
	
	@FindBy(xpath="//label[text()='TestGeolocC (Longitude)']/../..//input")
	private WebElement testGeolocationLongitudeTextbox;

	/**
	 * @return the testGeolocationLatitudeTextbox
	 */
	public WebElement getTestGeolocationLongitudeTextbox(int timeOut) {
		return isDisplayed(driver, testGeolocationLongitudeTextbox, "Visibility", timeOut, "Test Geolocation Longitude checkbox");
	}
	
	public WebElement getTestNumberTextbox(String TestNumberFieldLabel,int timeOut){
		WebElement ele =null;
		ele=isDisplayed(driver, FindElement(driver, "//label[text()='"+TestNumberFieldLabel+"']/../..//input", "Test Number Field Label", action.SCROLLANDBOOLEAN, timeOut), "Visibility", timeOut, "Test Number Field Label");
		return ele;
	}

	@FindBy(xpath="//label[text()='Carried Interest %']/..//following-sibling::td//input")
	private WebElement carriedInterestTextBox;

	/**
	 * @return the carriedInterestTextBox
	 */
	public WebElement getCarriedInterestTextBox(int timeOut) {
		return isDisplayed(driver, carriedInterestTextBox, "Visibility", timeOut, "Carried Interest Percent Text box");
	}
	
	public WebElement getTestPhoneTextbox(String TestPhoneFieldLabel,int timeOut){
		WebElement ele =null;
		ele=isDisplayed(driver, FindElement(driver, "//label[text()='"+TestPhoneFieldLabel+"']/../..//input", "Test Phone Field Label", action.SCROLLANDBOOLEAN, timeOut), "Visibility", timeOut, "Test Phone Field Label");
		return ele;
	}
	
	@FindBy(xpath="//label[text()='Partner Type']/../..//select")
	private WebElement partnerTypeDropdown;

	/**
	 * @return the partnerTypeDropdown
	 */
	public WebElement getPartnerTypeDropdown(int timeOut) {
		return isDisplayed(driver, partnerTypeDropdown, "Visibility", timeOut, "Partner Type Dropdown");
	}
	
	@FindBy(xpath="//select[@title='TestMultipicklistC - Available']")
	private WebElement  multiplePicklist;

	/**
	 * @return the multiplePicklist
	 */
	public WebElement getMultiplePicklist(int timeOut) {
		return isDisplayed(driver, multiplePicklist, "Visibility", timeOut, "Multiple picklist");
	} 
	
	public WebElement getTestTextbox(String TestTextFieldLabel,int timeOut){
		WebElement ele =null;
		ele=isDisplayed(driver, FindElement(driver, "//label[text()='"+TestTextFieldLabel+"']/../..//input", "Test Text Field Label", action.SCROLLANDBOOLEAN, timeOut), "Visibility", timeOut, "Test Text Field Label");
		return ele;
	}
	
	@FindBy(xpath="//label[text()='Sub-doc Description']/../..//textarea")
	private WebElement sudDocDescriptionTextBox;

	/**
	 * @return the sudDocDescriptionTextBox
	 */
	public WebElement getSudDocDescriptionTextBox(int timeOut) {
		return isDisplayed(driver, sudDocDescriptionTextBox, "Visibility", timeOut, "SubDoc Description Text Box");
	}
	
	@FindBy(xpath="//span[@class='timeContainer']/input")
	private WebElement testTimeBetaFTextBox;

	/**
	 * @return the testTimeBetaFTextBox
	 */
	public WebElement getTestTimeBetaFTextBox(int timeOut) {
		return isDisplayed(driver, testTimeBetaFTextBox, "Visibility", timeOut, "Test Time Beta F Text Box");
	}
	
	public WebElement getTestURL(String TestURLFieldLabel,int timeOut){
		WebElement ele =null;
		ele=isDisplayed(driver, FindElement(driver, "//label[text()='"+TestURLFieldLabel+"']/../..//input", "Test URL Field Label", action.SCROLLANDBOOLEAN, timeOut), "Visibility", timeOut, "Test URL Field Label");
		return ele;
	}
	
	@FindBy(xpath="//label[text()='Partnership']/../..//span[@class='lookupInput']/input")
	private WebElement partnershipTextBox_Classic;
	
	@FindBy(xpath="//*[text()='Partnership']/following-sibling::div//input[@title='Search Partnerships' or contains(@placeholder,'Search Partnerships')]")
	private WebElement partnershipTextBox_Lighting;

	/**
	 * @return the partnershipTextBox
	 */
	public WebElement getPartnershipTextBox(String environment,String mode,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, partnershipTextBox_Classic, "Visibility", timeOut, "Partnership Text Box Classic");
	}else{
		return isDisplayed(driver, partnershipTextBox_Lighting, "Visibility", timeOut, "Partnership Text Box Lighting");
	}
	
	}
	
	
	@FindBy(xpath="//div[@id='Name_ileinner']")
	private WebElement commitmentIdInViewMode_Classic;
	
	@FindBy(xpath="//div[@class='slds-media__body']//h1//span[@data-aura-class='uiOutputText']")
	private WebElement commitmentIdInViewMode_Lighting;

	/**
	 * @return the commitmentIdInViewMode
	 */
	public WebElement getCommitmentIdInViewMode(String environment,String mode,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, commitmentIdInViewMode_Classic, "Visibility", timeOut, "Commitment ID in View Mode Classic");
	}else{
		String xpath="//*[text()='Commitment']/../*/*[@slot='primaryField']/*";
		WebElement ele = FindElement(driver,xpath, "commitment id xpath", action.SCROLLANDBOOLEAN, timeOut);
		return ele;
	}

	}
	
	@FindBy(xpath="//label[text()='Limited Partner']/../..//span[@class='lookupInput']/input")
	private WebElement limitedPartnerTextbox_Classic;
	
	@FindBy(xpath="//*[text()='Limited Partner']/following-sibling::div//input[@title='Search Institutions' or contains(@placeholder,'Search Institutions')]")
	private WebElement limitedPartnerTextbox_Lighting;

	/**
	 * @return the limitedPArtnerTextbox
	 */
	public WebElement getLimitedPartnerTextbox(String environment,String mode,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, limitedPartnerTextbox_Classic, "Visibility", timeOut, "Limited Partner Text Box Classic");
	}else{
			return isDisplayed(driver, limitedPartnerTextbox_Lighting, "Visibility", timeOut, "Limited Partner Text Box Lighting");
	}
		
	}

}
