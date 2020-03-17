/**
 * 
 */
package com.navatar.pageObjects;

import static com.navatar.generic.CommonLib.*;
import static com.navatar.generic.AppListeners.*;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Ankit Jaiswal
 *
 */
public class AllFirmsPage extends BasePageBusinessLayer{
	 
	public AllFirmsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//*[@id='_panel0']/a/img")
	private WebElement investorImage;

	/**
	 * @return the investorImage
	 */
	public WebElement getInvestorImage(int timeOut) {
		return isDisplayed(driver, investorImage, "Visibility", timeOut, "Investor image");
	}
	
	/****************************Error page investor registration*****************************************/
	@FindBy(xpath = "//input[@id='User_FirstLogin:Form:FirstName']/../span/font")
	private WebElement errorFirstName;

	/**
	 * @return the errorFirstName
	 */
	public WebElement getErrorFirstName(int timeOut) {
		return isDisplayed(driver, errorFirstName, "Visibility", timeOut, "Error message below first name text box");
	}
	
	@FindBy(xpath = "//input[@id='User_FirstLogin:Form:LastName']/../span/font")
	private WebElement errorLastName;

	/**
	 * @return the errorFirstName
	 */
	public WebElement getErrorLastName(int timeOut) {
		return isDisplayed(driver, errorLastName, "Visibility", timeOut, "Error message below last name text box");
	}
	@FindBy(xpath = "//input[@id='User_FirstLogin:Form:communityNickname']/../span/font")
	private WebElement errorNickname;

	/**
	 * @return the errorNickname
	 */
	public WebElement getErrorNickname(int timeOut) {
		return isDisplayed(driver, errorNickname, "Visibility", timeOut, "Error message below nick name text box");
	}
	@FindBy(xpath = "//input[@id='User_FirstLogin:Form:UserName_Email']/../span/font[contains(text(),'Please')]")
	private WebElement errorPleaseEnterRequiredFieldsEmailID;

	/**
	 * @return the errorPleaseEnterRequiredFieldsEmailID
	 */
	public WebElement getErrorPleaseEnterRequiredFieldsEmailID(int timeOut) {
		return isDisplayed(driver, errorPleaseEnterRequiredFieldsEmailID, "Visibility", timeOut, "Please enter required fields error message below email id textbox");
	}

	@FindBy(xpath = "//input[@id='User_FirstLogin:Form:UserName_Email']/../span/font[contains(text(),'Invalid')]")
	private WebElement errorInvalidEmailAddressbelowEmail;

	/**
	 * @return the errorInvalidEmailAddressbelowEmail
	 */
	public WebElement getErrorInvalidEmailAddressbelowEmail(int timeOut) {
		return isDisplayed(driver, errorInvalidEmailAddressbelowEmail, "Visibility", timeOut, "Invalid email error message below email textbox");
	}
	@FindBy(xpath = "//input[@id='User_FirstLogin:Form:investorfirm']/../span/font")
	private WebElement errorFirmName;

	/**
	 * @return the errorFirmName
	 */
	public WebElement getErrorFirmName(int timeOut) {
		return isDisplayed(driver, errorFirmName, "Visibility", timeOut, "Error message below firm name textbox");
	}
	@FindBy(xpath = "//span[@id='pwrd']/font")
	private WebElement errorPleaseEnterRequiredFieldsPassword;

	/**
	 * @return the errorPleaseEnterRequiredFieldsPassword
	 */
	public WebElement getErrorPleaseEnterRequiredFieldsPassword(int timeOut) {
		return isDisplayed(driver, errorPleaseEnterRequiredFieldsPassword, "Visibility", timeOut, "Please enter required fields error message below password textbox");
	}
	@FindBy(xpath = "//span[@id='pwderror']/font")
	private WebElement errorAtLeastEightCharsPassword;

	/**
	 * @return the errorAtLeastEightCharsPassword
	 */
	public WebElement getErrorAtLeastEightCharsPassword(int timeOut) {
		return isDisplayed(driver, errorAtLeastEightCharsPassword, "Visibility", timeOut, "Please enter at least 8 characters error message below password textbox");
	}
	@FindBy(xpath = "//span[@id='cnfrmpwd']/font")
	private WebElement errorRetypeConfirmPassword;

	/**
	 * @return the errorRetypeConfirmPassword
	 */
	public WebElement getErrorRetypeConfirmPassword(int timeOut) {
		return isDisplayed(driver, errorRetypeConfirmPassword, "Visibility", timeOut, "Please retype confirm password error message below confirm password textbox");
	}
	
	@FindBy(xpath = "//span[@id='pwdnotmatch']/font")
	private WebElement errorConfirmPasswordNotMatch;

	/**
	 * @return the errorConfirmPasswordNotMatch
	 */
	public WebElement getErrorConfirmPasswordNotMatch(int timeOut) {
		return isDisplayed(driver, errorConfirmPasswordNotMatch, "Visibility", timeOut, "Passwords entered do not match error message below confirm password textbox");
	}
	
	/***********************************all firm alert grid*********************/
	
	public List<WebElement> getDocumentNameList(){
		return FindElements(driver, "//span[@id='myGrid-rows']//span[contains(@class,'aw-rows-normal')]/span[2]/a", "document name list on all firms page");
	}
	
	public List<WebElement> getFirmNameList(){
		return FindElements(driver, "//span[@id='myGrid-rows']//span[contains(@class,'aw-rows-normal')]/span[3]/a", "firm name list on all firm page");
	}
	
	public List<WebElement> getInvesmentNameList(){
		return FindElements(driver, "//span[@id='myGrid-rows']//span[contains(@class,'aw-rows-normal')]/span[4]/a", "invesment name list on all firm page");
	}
	
	public List<WebElement> getWorkSpaceNameList(){
		return FindElements(driver, "//span[@id='myGrid-rows']//span[contains(@class,'aw-rows-normal')]/span[2]/a/../../span[5]", "work space name list on all firm page");
	}
	
	public List<WebElement> getActivityTypeList(){
		return FindElements(driver, "//span[@id='myGrid-rows']//span[contains(@class,'aw-rows-normal')]/span[2]/a/../../span[7]", "activity type alert on all firm page");
	}
	
	public List<WebElement> getContactFirmUpdateList(){
		return FindElements(driver, "//span[@id='myGrid-rows']//span[contains(@class,'aw-rows-normal')]/span[7]/a", "contact firm profile update list on all firm page");
	}
	/*public List<WebElement> getSortingIconList(){
		return FindElements(driver, "//span[contains(@id,'myGrid-header-') and contains(@class,'aw-grid-header')]//span[3]/span", "all firms sorting icon list");
	}
	*/
	public List<WebElement> getSortingIconList(){
		return FindElements(driver, "//span[contains(@class,'aw-grid-header')]//span[3]/span", "all firms sorting icon list");
	}
	public List<WebElement> getActivitiesCreatedOnList(){
		return FindElements(driver, "//span[@id='myGrid']//span[contains(@class,'aw-rows-normal')]/span[2]/a/../../span[6]", "all firms activities created on list");
	}
	
	@FindBy(xpath="//div[@class='heading_box']/span")
	private WebElement activitisHeadertext;
	
	/**
	 * @return the activitisHeadertext
	 */
	public WebElement getActivitisHeadertext(int timeOut) {
		return isDisplayed(driver, activitisHeadertext, "Visibility", timeOut, "activities header text");
	}
	
	public List<WebElement> getAllfirmalertHeaderTextList(){
		return FindElements(driver, "//span[contains(@id,'myGrid-header-')]/span[3]", "all firm alerts header text list");
	}
	
	@FindBy(xpath="(//span[contains(@id,'myGrid-header-')]/span[3])[5]/span")
	private WebElement activitiyCreatedOnSortingIcon;
	/**
	 * @return the activitiyCreatedOnSortingIcon
	 */
	public WebElement getActivitiyCreatedOnSortingIcon(int timeOut) {
		return isDisplayed(driver, activitiyCreatedOnSortingIcon, "Visibility", timeOut, "activity created on sorting icon");
	}
	
	@FindBy(xpath="//span[@id='myGrid-rows']//span[contains(@class,'aw-rows-normal')]/span[2]/span")
	private WebElement activityAlertNoDatatoDisplayErrorMsg;
	/**
	 * @return the allfirmsNoDatatoDisplayErrorMsg
	 */
	public WebElement getActivityAlertNoDatatoDisplayErrorMsg(int timeOut) {
		return isDisplayed(driver, activityAlertNoDatatoDisplayErrorMsg, "Visibility", timeOut, "No data to display error message in all firm alert grid");
	}
	//***********************logo*****************************//
	public List<WebElement> getLogoListAtAllFirmsPage(){
		return FindElements(driver, "//div[@class='stepcarousel']//img", "logo list at all firms page");
	}
	
	@FindBy(xpath="//form[@id='page:headerFormID']//img")
	private WebElement logoOnInvestorFirmsPage;
	
	
	
	/**
	 * @return the logoOnInvestorFirmsPage
	 */
	public WebElement getLogoOnInvestorFirmsPage(int timeOut) {
		return isDisplayed(driver, logoOnInvestorFirmsPage, "Visibility", timeOut, "logo on investor firms page");
	}

	//*******************Investments section//*****************
	@FindBy(xpath="//div[@class='heading_box_Investment']/span")
	private WebElement investmentHeaderText;

	/**
	 * @return the invesmentHeaderText
	 */
	public WebElement getInvestmentHeaderText(int timeOut) {
		return isDisplayed(driver, investmentHeaderText, "Visibility", timeOut, "investment tab header text");
	}
	
	@FindBy(xpath="//a[@title='Navatar'][@class='logo']")
	private WebElement navatarLogo;

	/**
	 * @return the navatarLogo
	 */
	public WebElement getNavatarLogo(int timeOut) {
		return isDisplayed(driver, navatarLogo, "Visibility", timeOut, "Navatar Logo");
	}
	
	@FindBy(xpath="//span[@id='myGrid-scroll-box']")
	private WebElement scrollBox;

	/**
	 * @return the scrollBox
	 */
	public WebElement getScrollBox(int timeOut) {
		return isDisplayed(driver, scrollBox, "Visibility", timeOut, "scroll Box");
	}

	@FindBy(xpath="//span[@id='myGrid_firmAllDoc-scroll-box']")
	private WebElement allDocumentsScrollBox;

	/**
	 * @return the allDocumentsScrollBox
	 */
	public WebElement getAllDocumentsScrollBox(int timeOut) {
		return isDisplayed(driver, allDocumentsScrollBox, "Visibility", timeOut, "All Document scroll box");
	}
}
