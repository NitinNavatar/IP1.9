/**
 * 
 */
package com.navatar.pageObjects;

import java.util.List;
import static com.navatar.generic.CommonLib.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Akul Bhutani
 *
 */
public class InvestorProfile extends BasePage{
	 
		public InvestorProfile(WebDriver driver) {
			super(driver);
			// TODO Auto-generated constructor stub
			PageFactory.initElements(driver, this);
		}
/********************************My profile page******************************************/
		public List<WebElement> getBasicInfoLabelTextList(){
			return FindElements(driver, "//div[@class='BasicInformation_box']//td[contains(@class,'td1')]", "basic info label text list");
		}
		
		public List<WebElement> getLoginInfoLabelTextList(){
			return FindElements(driver, "//div[@class='InvestmentPreferences_box topspace ']//td[contains(@class,'td1')]", "login info label text list");
		}
		
		public List<WebElement> getSubHeads(){
			return FindElements(driver, "//div[@class='head']", "sub heads list");
		}
		
		public List<WebElement> getEditModeBasicInfoList(){
			return FindElements(driver, "//div[@class='BasicInformation_box']//td[@class='td1']", "edit mode list of text labels in my profile page");
		}
@FindBy(xpath = "//a[@title='My Profile']")
private WebElement myProfileTab;


		/**
 * @return the myProfileTab
 */
public WebElement getMyProfileTab(int timeOut) {
	return isDisplayed(driver, myProfileTab, "Visibility", timeOut, "my profile tab on investor login");
}
		@FindBy(xpath = "//img[@alt='Edit']")
private WebElement editIcon;
/**
 * @return the editIcon
 */
public WebElement getEditIcon(int timeOut) {
	return isDisplayed(driver, editIcon, "Visibility", timeOut, "edit icon on my profile page");
}
@FindBy(xpath = "//i[@id='lastModify']")
private WebElement lastModifiedText;


/**
 * @return the lastModifiedText
 */
public WebElement getLastModifiedText(int timeOut) {
	return isDisplayed(driver, lastModifiedText, "Visibility", timeOut, "Last modified on date and by user, text label present on my firm profile page");
}
@FindBy(xpath = "//input[@id='pg:frm:confname']")
private WebElement firstNameTextBox;
/**
 * @return the firstNameTextBox
 */
public WebElement getFirstNameTextBox(int timeOut) {
	return isDisplayed(driver, firstNameTextBox, "Visibility", timeOut, "First name textbox ");
}

@FindBy(xpath = "(//td[contains(text(),'Firm')]/../td/div)[1]")
private WebElement firmNameValueMyProfilePage;


/**
 * @return the firmNameValueMyProfilePage
 */
public WebElement getFirmNameValueMyProfilePage(int timeOut) {
	return isDisplayed(driver, firmNameValueMyProfilePage, "Visibility",
			timeOut, "firm name value present in my profile page");
}
@FindBy(xpath = "//input[@id='pg:frm:conlname']")
private WebElement lastNameTextBox;
/**
 * @return the lastNameTextBox
 */
public WebElement getLastNameTextBox(int timeOut) {
	return isDisplayed(driver, lastNameTextBox, "Visibility", timeOut, "Last name textbox");
}

@FindBy(xpath = "//img[@align='save']")
private WebElement saveButtonMyProfilePage;
/**
 * @return the saveButton
 */
public WebElement getSaveButtonMyProfilePage(int timeOut) {
	return isDisplayed(driver, saveButtonMyProfilePage, "Visibility", timeOut, "Save button edit mode");
}

@FindBy(xpath = "//img[@align='cancel']")
private WebElement cancelButtonMyProfilePage;
/**
 * @return the cancelButton
 */
public WebElement getCancelButtonMyProfilePage(int timeOut) {
	return isDisplayed(driver, cancelButtonMyProfilePage, "Visibility", timeOut, "Cancel button edit mode");
}

@FindBy(xpath = "//td[contains(text(),'E-mail:')]/..//a")
private WebElement emailUrl;
/**
 * @return the emailUrl
 */
public WebElement getEmailUrl(int timeOut) {
	return isDisplayed(driver, emailUrl, "Visibility", timeOut, "Email link of contact");
}
@FindBy(xpath = "//input[@id='Eachalert1']")
private WebElement dailyDigestRadioBtnView;


/**
 * @return the dailyDigestRadioBtnView
 */
public WebElement getDailyDigestRadioBtnView(int timeOut) {
	return isDisplayed(driver, dailyDigestRadioBtnView, "Visibility", timeOut, "daily digest radio button in view mode");
}
@FindBy(xpath = "//input[@id='Investoralert1']")
private WebElement noEmailRadioBtnView;


/**
 * @return the noEmailRadioBtnView
 */
public WebElement getNoEmailRadioBtnView(int timeOut) {
	return isDisplayed(driver, noEmailRadioBtnView, "Visibility", timeOut, "no email radio button in view mode");
}
@FindBy(xpath = "//li[@id='Eachalert']")
private WebElement dailyDigestEmailText;
/**
 * @return the dailyDigestEmailText
 */
public WebElement getDailyDigestEmailText(int timeOut) {
	return isDisplayed(driver, dailyDigestEmailText, "Visibility", timeOut, "Daily digest email text");
}

@FindBy(xpath = "//li[@id='Investoralert']")
private WebElement noEmailText;
/**
 * @return the noEmailText
 */
public WebElement getNoEmailText(int timeOut) {
	return isDisplayed(driver, noEmailText, "Visibility", timeOut, "no email text label");
}
@FindBy(xpath = "//a[text()='Change Profile Picture']")
private WebElement changeProfilePictureLink;
/**
 * @return the changeProfilePictureLink
 */
public WebElement getChangeProfilePictureLink(int timeOut) {
	return isDisplayed(driver, changeProfilePictureLink, "Visibility", timeOut, "change profile picture link");
}

@FindBy(xpath = "//input[@id='viaemailinvestor']")
private WebElement dailyDigestRadiobuttonEditMode;


/**
 * @return the dailyDigestRadiobutton
 */
public WebElement getDailyDigestRadiobuttonEditMode(int timeOut) {
	return isDisplayed(driver, dailyDigestRadiobuttonEditMode, "Visibility", timeOut, "Daily digest radio button");
}

@FindBy(xpath = "//input[@id='viainvestor']")
private WebElement noEmailRadiobuttonEditMode;


/**
 * @return the noEmailRadiobutton
 */
public WebElement getNoEmailRadiobuttonEditMode(int timeOut) {
	return isDisplayed(driver, noEmailRadiobuttonEditMode, "Visibility", timeOut, "No email radio button");
}

@FindBy(xpath = "//input[@id='pg:frm:ConTitle']")
private WebElement titleTextbox;
/**
 * @return the titleTextbox
 */
public WebElement getTitleTextbox(int timeOut) {
	return isDisplayed(driver, titleTextbox, "Visibility", timeOut, "Title textbox my profile page");
}

@FindBy(xpath = "(//td[contains(text(),'Title:')]/../td/div)[1]")
private WebElement titleValue;


/**
 * @return the titleValue
 */
public WebElement getTitleValue(int timeOut) {
	return isDisplayed(driver, titleValue, "Visibility", timeOut, "title value on my profile page");
}
@FindBy(xpath = "(//td[contains(text(),'LinkedIn:')]/../td/div)[2]")
private WebElement linkedinValue;


/**
 * @return the linkedinValue
 */
public WebElement getLinkedinValue(int timeOut) {
	return isDisplayed(driver, linkedinValue, "Visibility", timeOut, "linkedin profile link on my profile page");
}
@FindBy(xpath = "(//td[contains(text(),'Facebook:')]/../td/a)[2]")
private WebElement facebookValue;


/**
 * @return the facebookValue
 */
public WebElement getFacebookValue(int timeOut) {
	return isDisplayed(driver, facebookValue, "Visibility", timeOut, "Facebook profile link on my profile page");
}

@FindBy(xpath = "(//td[contains(text(),'Address:')]/../td/div)[1]")
private WebElement addressValue;


/**
 * @return the addressValue
 */
public WebElement getAddressValue(int timeOut) {
	return isDisplayed(driver, addressValue, "Visibility", timeOut, "address value on my profile page");
}

@FindBy(xpath = "(//td[contains(text(),'Phone:')]/../td/div)[2]")
private WebElement phoneValue;


/**
 * @return the phoneValue
 */
public WebElement getPhoneValue(int timeOut) {
	return isDisplayed(driver, phoneValue, "Visibility", timeOut, "phone value on my profile page");
}
@FindBy(xpath = "//input[@id='pg:frm:conphone']")
private WebElement phoneTextbox;


/**
 * @return the phoneTextbox
 */
public WebElement getPhoneTextbox(int timeOut) {
	return isDisplayed(driver, phoneTextbox, "Visibility", timeOut, "Phone textbox on my profile page");
}
@FindBy(xpath = "//input[@id='pg:frm:ConFacebook']")
private WebElement facebookTextbox;


/**
 * @return the facebookTextbox
 */
public WebElement getFacebookTextbox(int timeOut) {
	return isDisplayed(driver, facebookTextbox, "Visibility", timeOut, "Facebook textbox on my profile page");
}

@FindBy(xpath = "//input[@id='pg:frm:ConlinkedIn']")
private WebElement linkedinTextbox;


/**
 * @return the linkedinTextbox
 */
public WebElement getLinkedinTextbox(int timeOut) {
	return isDisplayed(driver, linkedinTextbox, "Visibility", timeOut, "Linkedin textbox on my profile page");
}
@FindBy(xpath = "//textarea[@id='pg:frm:ConmailingStreet']")
private WebElement mailingStreetTextbox;


/**
 * @return the mailingStreetTextbox
 */
public WebElement getMailingStreetTextbox(int timeOut) {
	return isDisplayed(driver, mailingStreetTextbox, "Visibility", timeOut, "Mailing street textbox on my profile page");
}


@FindBy(xpath = "//td[contains(text(),'Mailing City')]/../td/input[@type='text']")
private WebElement mailingCityTextbox;


/**
* @return the billingCityText
*/
public WebElement getMailingCityTextbox(int timeOut) {
	return isDisplayed(driver, mailingCityTextbox, "Visibility", timeOut, "Mailing city textbox on my  profile page");
}

@FindBy(xpath = "//td[contains(text(),'Mailing State')]/../td/input[@type='text']")
private WebElement mailingStateTextbox;


/**
* @return the billingCityText
*/
public WebElement getMailingStateTextbox(int timeOut) {
	return isDisplayed(driver, mailingStateTextbox, "Visibility", timeOut, "Mailing State textbox on my profile page");
}

@FindBy(xpath = "//td[contains(text(),'Mailing Zip')]/../td/input[@type='text']")
private WebElement mailingZipTextbox;


/**
* @return the billingCityText
*/
public WebElement getMailingZipTextbox(int timeOut) {
	return isDisplayed(driver, mailingZipTextbox, "Visibility", timeOut, "Mailing Zip code textbox on my profile page");
}

@FindBy(xpath = "//td[contains(text(),'Mailing Country')]/../td/input[@type='text']")
private WebElement mailingCountryTextbox;


/**
* @return the billingCityText
*/
public WebElement getMailingCountryTextbox(int timeOut) {
	return isDisplayed(driver, mailingCountryTextbox, "Visibility", timeOut, "Mailing country textbox on my profile page");
}

@FindBy(xpath="(//div[@class='divwrap1'])[1]")
private WebElement getFirstNameInViewMode;

/**
 * @return the getFirstNameInViewMode
 */
public WebElement getGetFirstNameInViewMode(int timeOut) {
	return isDisplayed(driver, getFirstNameInViewMode, "Visibility", timeOut, "First NAme In View Mode");
}
/**************************************Change profile picture***********************/
@FindBy(xpath = "//div[text()='Change Profile Picture ']")
private WebElement changeProfilePictureText;
/**
 * @return the changeProfilePictureText
 */
public WebElement getChangeProfilePictureText(int timeOut) {
	return isDisplayed(driver, changeProfilePictureText, "Visibility", timeOut, "Change profile picture text on change picture window");
}

@FindBy(xpath = "//a[text()='Submit']")
private WebElement submitButtonChangePicture;
/**
 * @return the submitButtonChangePicture
 */
public WebElement getSubmitButtonChangePicture(int timeOut) {
	return isDisplayed(driver, submitButtonChangePicture, "Visibility", timeOut, "Submit button on change profile picture window");
}

@FindBy(xpath = "//input[@id='pg:frm1:fileid']")
private WebElement browseButton;
/**
 * @return the browseButton
 */
public WebElement getBrowseButton(int timeOut) {
	return isDisplayed(driver, browseButton, "Visibility", timeOut, "Browse button on change picture window");
}

@FindBy(xpath = "//a[@title='Save']")
private WebElement changeProfileSaveButton;
/**
 * @return the saveButton
 */
public WebElement getChangeProfileSaveButton(int timeOut) {
	return isDisplayed(driver, changeProfileSaveButton, "Visibility", timeOut, "Save button on change profile window");
}
/*********************************************Change password*******************/
@FindBy(xpath = "//a[@title='Change Password']")
private WebElement changePasswordLink;
/**
 * @return the changePasswordLink
 */
public WebElement getChangePasswordLink(int timeOut) {
	return isDisplayed(driver, changePasswordLink, "Visibility", timeOut, "Change password link on my profile page");
}

@FindBy(xpath = "//div[contains(text(),'Change Password')]")
private WebElement changePasswordHeading;


/**
 * @return the changePasswordHeading
 */
public WebElement getChangePasswordHeading(int timeOut) {
	return isDisplayed(driver, changePasswordHeading, "Visibility", timeOut, "Change password heading on change password window");
}
@FindBy(xpath = "//td[contains(text(),'Old Password')]")
private WebElement oldPasswordText;
/**
 * @return the oldPasswordText
 */
public WebElement getOldPasswordText(int timeOut) {
	return isDisplayed(driver, oldPasswordText, "Visibility", timeOut, "Old password text present in change password window");
}

@FindBy(xpath = "//td[contains(text(),'New Password')]")
private WebElement newPasswordText;
/**
 * @return the oldPasswordText
 */
public WebElement getNewPasswordText(int timeOut) {
	return isDisplayed(driver, newPasswordText, "Visibility", timeOut, "New password text present in change password window");
}
@FindBy(xpath = "//td[contains(text(),'Confirm Password')]")
private WebElement confirmPasswordText;
/**
 * @return the confirmPasswordText
 */
public WebElement getConfirmPasswordText(int timeOut) {
	return isDisplayed(driver, confirmPasswordText, "Visibility", timeOut, "Confirm password text present in change password window");
}
@FindBy(xpath = "//input[@id='oldpassword']")
private WebElement oldPasswordTextbox;
/**
 * @return the oldPasswordTextbox
 */
public WebElement getOldPasswordTextbox(int timeOut) {
	return isDisplayed(driver, oldPasswordTextbox, "Visibility", timeOut, "Old password textbox in change password window");
}

@FindBy(xpath = "//input[@id='newpassword']")
private WebElement newPasswordTextbox;
/**
 * @return the newPasswordTextbox
 */
public WebElement getNewPasswordTextbox(int timeOut) {
	return isDisplayed(driver, newPasswordTextbox, "Visibility", timeOut, "New password textbox in change password window");
}
@FindBy(xpath = "//input[@id='confirmpassword']")
private WebElement confirmPasswordTextbox;
/**
 * @return the confirmPasswordTextbox
 */
public WebElement getConfirmPasswordTextbox(int timeOut) {
	return isDisplayed(driver, confirmPasswordTextbox, "Visibility", timeOut, "Confirm password textbox in change password window");
}
@FindBy(xpath = "//a[@title='Save']")
private WebElement saveButtonChangePassword;
/**
 * @return the saveButtonChangePassword
 */
public WebElement getSaveButtonChangePassword(int timeOut) {
	return isDisplayed(driver, saveButtonChangePassword, "Visibility", timeOut, "save button on change password window");
}
@FindBy(xpath = "//a[@title='Cancel']")
private WebElement cancelButtonChangePassword;
/**
 * @return the cancelButtonChangePassword
 */
public WebElement getCancelButtonChangePassword(int timeOut) {
	return isDisplayed(driver, cancelButtonChangePassword, "Visibility", timeOut, "cancel button change password window");
}


@FindBy(xpath = "//a[@title='Close']")
private WebElement crossIcon;
/**
 * @return the crossIcon
 */
public WebElement getCrossIcon(int timeOut) {
	return isDisplayed(driver, crossIcon, "Visibility", timeOut, "cross icon change password window");
}



/********************************My Firms Profile Page*********************************************/


public List<WebElement> getInvestmentPrefLabelListFirmProfile(){
	return FindElements(driver, "//div[@class='InvestmentPreferences_box']//td[contains(@class,'td1')]", "login info label text list");
}

public List<WebElement> getSubHeadsFirmProfile(){
	return FindElements(driver, "//div[@class='head']", "sub heads list");
}

public List<WebElement> getEditModeBasicInfoListFirmProfile(){
	return FindElements(driver, "//div[@class='BasicInformation_box']//td[contains(@class,'td1')]", "edit mode list of text labels in firm profile page");
}

public List<WebElement> getViewModeValueFirmProfile() {
	return FindElements(driver,"//td[@class='td2']","all values present in firm profile page in view mode");
}
@FindBy(xpath = "(//td[contains(text(),'Max. Investment')]/../td[@class='td2'])[2]")
private WebElement maxInvestmentSizeValue;


/**
 * @return the maxInvestmentSizeValue
 */
public WebElement getMaxInvestmentSizeValue(int timeOut) {
	return isDisplayed(driver, maxInvestmentSizeValue, "Visibility", timeOut, "max investment size value on my firm profile page");
}
@FindBy(xpath = "(//td[contains(text(),'Min. Investment')]/../td[@class='td2'])[2]")
private WebElement minInvestmentSizeValue;


/**
 * @return the minInvestmentSizeValue
 */
public WebElement getMinInvestmentSizeValue(int timeOut) {
	return isDisplayed(driver, minInvestmentSizeValue, "Visibility", timeOut, "min investment size value present on my firm profile page");
}
@FindBy(xpath = "(//td[contains(text(),'AUM')]/../td[@class='td2'])[2]")
private WebElement aumValue;


/**
 * @return the aumValue
 */
public WebElement getAumValue(int timeOut) {
	return isDisplayed(driver, aumValue, "Visibility", timeOut, "aum value text");
}
@FindBy(xpath = "//a[contains(@title,'My Firm')]")
private WebElement myFirmProfileTab;


/**
 * @return the myFirmProfileTab
 */
public WebElement getMyFirmProfileTab(int timeOut) {
	return isDisplayed(driver, myFirmProfileTab, "Visibility", timeOut, "my firm profiles tab on all firms page");
}
@FindBy(xpath = "//span[@id='pageid:frmid:viewotp']/div[1]/span[1]")
private WebElement myFirmsProfileText;
/**
 * @return the myFirmsProfileText
 */
public WebElement getMyFirmsProfileHeading(int timeOut) {
	return isDisplayed(driver, myFirmsProfileText, "Visibility", timeOut, "My firms profile heading");
}
@FindBy(xpath = "//div[@id='TD_height']")
private WebElement descriptionValue;


/**
 * @return the descriptionValue
 */
public WebElement getDescriptionValue(int timeOut) {
	return isDisplayed(driver, descriptionValue, "Visibility", timeOut, "description value present in firm profile page");
}
@FindBy(xpath = "//div[@id='description']")
private WebElement descriptionError;


/**
 * @return the descriptionError
 */
public WebElement getDescriptionError(int timeOut) {
	return isDisplayed(driver, descriptionError, "Visibility", timeOut, "description error message");
}
@FindBy(xpath = "//input[@id='fvalue2']")
private WebElement weWishToBeContactedNoEditModeRadiobtn;


/**
 * @return the weWishToBeContactedNoEditModeRadiobtn
 */
public WebElement getWeWishToBeContactedNoEditModeRadiobtn(int timeOut) {
	return isDisplayed(driver, weWishToBeContactedNoEditModeRadiobtn,
			"Visibility", timeOut, "we wish to be contacted no radio button in edit mode");
}
@FindBy(xpath = "//input[@id='fvalue4']")
private WebElement weWishToBeContactedYesViewModeRadiobtn;
/**
 * @return the weWishToBeContactedInvOpportunitiesRadiobtn
 */
public WebElement getWeWishToBeContactedYesViewModeRadiobtn(int timeOut) {
	return isDisplayed(driver, weWishToBeContactedYesViewModeRadiobtn, "Visibility", timeOut, "we wish to be contacted about inv opportunities yes radio button");
}

@FindBy(xpath = "//div[@id='fnameerror']")
private WebElement firstNameTextboxError;

/**
 * @return the firstNameTextboxError
 */
public WebElement getFirstNameTextboxError(int timeOut) {
	return isDisplayed(driver, firstNameTextboxError, "Visibility", timeOut, "error message below first name textbox");
}
@FindBy (xpath ="//div[@id='lnameerror']")
private WebElement lastNameTextboxError;

/**
 * @return the lastNameTextboxError
 */
public WebElement getLastNameTextboxError(int timeOut) {
	return isDisplayed(driver, lastNameTextboxError, "Visibility", timeOut, "error message below last name textbox");
}

@FindBy(xpath = "//div[@id='AccountNameiderror']")
private WebElement firmNameErrorText;


/**
 * @return the firmNameErrorText
 */
public WebElement getFirmNameErrorText(int timeOut) {
	return isDisplayed(driver, firmNameErrorText, "Visibility", timeOut, "Firm name error text");
}
@FindBy(xpath = "//a[@title='Change Firm Logo']")
private WebElement changeFirmLogoLink;


/**
 * @return the changeFirmLogoLink
 */
public WebElement getChangeFirmLogoLink(int timeOut) {
	return isDisplayed(driver, changeFirmLogoLink, "Visibility", timeOut, "change firm logo link on firms profile page");
}

@FindBy(xpath = "//td[contains(text(),'Firm Name')]/../td/input[@type='text']")
private WebElement firmNameTextbox;


/**
 * @return the firmNameTextbox
 */
public WebElement getFirmNameTextbox(int timeOut) {
	return isDisplayed(driver, firmNameTextbox, "Visibility", timeOut, "firm name textbox on my firms profile page");
}

@FindBy(xpath = "//td[contains(text(),'Industry')]/../td/select")
private WebElement industryDropdown;


/**
 * @return the industryDropdown
 */
public WebElement getIndustryDropdown(int timeOut) {
	return isDisplayed(driver, industryDropdown, "Visibility", timeOut, "industry dropdown on my firm profile page");
}

@FindBy(xpath = "//td[contains(text(),'Billing Street:')]/../td/div/textarea")
private WebElement billingStreetTextArea;


/**
 * @return the billingStreetTextArea
 */
public WebElement getBillingStreetTextArea(int timeOut) {
	return isDisplayed(driver, billingStreetTextArea, "Visibility", timeOut, "billing street text area  on my firm profile page");
}

@FindBy(xpath = "//td[contains(text(),'Billing City')]/../td/input[@type='text']")
private WebElement billingCityTextbox;


/**
 * @return the billingCityText
 */
public WebElement getBillingCityTextbox(int timeOut) {
	return isDisplayed(driver, billingCityTextbox, "Visibility", timeOut, "billing city textbox on my firm profile page");
}

@FindBy(xpath = "//td[contains(text(),'Billing State')]/../td/input[@type='text']")
private WebElement billingStateTextbox;


/**
 * @return the billingStateTextbox
 */
public WebElement getBillingStateTextbox(int timeOut) {
	return isDisplayed(driver, billingStateTextbox, "Visibility", timeOut, "billing state textbox on my firm profile page");
}

@FindBy(xpath = "//td[contains(text(),'Billing Zip')]/../td/input[@type='text']")
private WebElement billingZipCodeTextbox;

/**
 * @return the billingZipCodeTextbox
 */
public WebElement getBillingZipCodeTextbox(int timeOut) {
	return isDisplayed(driver, billingZipCodeTextbox, "Visibility", timeOut, "billing zip code textbox on my firm profile page");
}

@FindBy(xpath = "//td[contains(text(),'Billing Country')]/../td/input[@type='text']")
private WebElement billingCountryTextbox;


/**
 * @return the billingCountryTextbox
 */
public WebElement getBillingCountryTextbox(int timeOut) {
	return isDisplayed(driver, billingCountryTextbox, "Visibility", timeOut, "billing country textbox on my firm profile page");
}

@FindBy(xpath = "//td[contains(text(),'Description')]/../td/textarea")
private WebElement descriptionTextArea;

//

/**
 * @return the descriptionTextArea
 */
public WebElement getDescriptionTextArea(int timeOut) {
	return isDisplayed(driver, descriptionTextArea, "Visibility", timeOut, "description text box on my firm profile page");
}

@FindBy(xpath = "//td[contains(text(),'Institution Type')]/../td/select")
private WebElement institutionTypeDropdown;


/**
 * @return the institutionTypeDropdown
 */
public WebElement getInstitutionTypeDropdown(int timeOut) {
	return isDisplayed(driver, institutionTypeDropdown, "Visibility", timeOut,"institution type dropdown my firm profile page");
}

@FindBy(xpath = "//select[@id='AvailableFundTypes']")
private WebElement fundTypeSelectbox;


/**
 * @return the fundTypeSelectbox
 */
public WebElement getFundTypeSelectbox(int timeOut) {
	return isDisplayed(driver, fundTypeSelectbox, "Visibility", timeOut, "fund type select box firm profile page");
}

@FindBy(xpath = "//select[@id='AvailableIndustries']")
private WebElement industriesSelectbox;


/**
 * @return the insdustriesSelectbox
 */
public WebElement getIndustriesSelectbox(int timeOut) {
	return isDisplayed(driver, industriesSelectbox, "Visibility", timeOut, "insdustries select box firm profiles page");
}

@FindBy(xpath = "//select[@id='AvailableGeographic']")
private WebElement geoFocusSelectbox;


/**
 * @return the geoFocusSelectbox
 */
public WebElement getGeoFocusSelectbox(int timeOut) {
	return isDisplayed(driver, geoFocusSelectbox, "Visibility", timeOut, "geo focus select box on firm profile page");
}
@FindBy(xpath = "//a[@id='addbuttonFundSelect']")
private WebElement addButtonFundsSelectbox;



/**
 * @return the addButtonFundsSelectbox
 */
public WebElement getAddButtonFundsSelectbox(int timeOut) {
	return isDisplayed(driver, addButtonFundsSelectbox, "Visibility", timeOut,
			"add button in funds select box my firms profile page");
}

@FindBy(xpath = "//a[@id='addbuttonIndustriesselect']")
private WebElement addButtonIndustriesSelectbox;


/**
 * @return the addButtonIndustriesSelectbox
 */
public WebElement getAddButtonIndustriesSelectbox(int timeOut) {
	return isDisplayed(driver, addButtonIndustriesSelectbox, "Visibility",
			timeOut, "add button in industries select box firms profile page");
}

@FindBy(xpath = "//a[@id='addbuttonGeographicselect']")
private WebElement addButtonGeographicSelectbox;


/**
 * @return the addButtonGeographicSelectbox
 */
public WebElement getAddButtonGeographicSelectbox(int timeOut) {
	return isDisplayed(driver, addButtonGeographicSelectbox, "Visibility",
			timeOut, "add button geographic select box in firms profile page");
}
@FindBy(xpath = "//td[contains(text(),'Min.Investment')]/../td/input[@type='text']")
private WebElement minInvestmentTextbox;


/**
 * @return the minInvestmentTextbox
 */
public WebElement getMinInvestmentTextbox(int timeOut) {
	return isDisplayed(driver, minInvestmentTextbox, "Visibility", timeOut, "minimum investment size textbox firm profile page");
}

@FindBy(xpath = "//td[contains(text(),'Max. Investment')]/../td/input[@type='text']")
private WebElement maxInvestmentTextbox;


/**
 * @return the maxInvestmentTextbox
 */
public WebElement getMaxInvestmentTextbox(int timeOut) {
	return isDisplayed(driver, maxInvestmentTextbox, "Visibility", timeOut, "max investment texbox firm profile page");
}

@FindBy(xpath = "//td[contains(text(),'AUM')]/../td/input[@type='text']")
private WebElement AUMtextbox;


/**
 * @return the aUMtextbox
 */
public WebElement getAUMtextbox(int timeOut) {
	return isDisplayed(driver, AUMtextbox, "Visibility", timeOut, "aum textbox on firm profile page");
}

@FindBy(xpath = "//img[@align='Cancel']")
private WebElement cancelButtonFirmProfile;


/**
 * @return the cancelButtonFirmProfile
 */
public WebElement getCancelButtonFirmProfile(int timeOut) {
	return isDisplayed(driver, cancelButtonFirmProfile, "Visibility", timeOut,
			"Cancel button on myfirm profile page");
}

@FindBy(xpath = "//a[@title='Save']")
private WebElement saveButtonFirmProfile;


/**
 * @return the saveButtonFirmProfile
 */
public WebElement getSaveButtonFirmProfile(int timeOut) {
	return isDisplayed(driver, saveButtonFirmProfile, "Visibility", timeOut,"save button on my firm profile page");
}


/****************My Firm profile errors***************/
@FindBy(xpath = "//div[@id='validminfundsize']")
private WebElement minInvestmentSizeError;


/**
 * @return the minFundsInvestmentError
 */
public WebElement getMinInvestmentSizeError(int timeOut) {
	return isDisplayed(driver, minInvestmentSizeError, "Visibility", timeOut, "min investments amount error text");
}

@FindBy(xpath = "//div[@id='validmaxfundsize']")
private WebElement maxInvestmentSizeError;


/**
 * @return the maxInvestmentSizeError
 */
public WebElement getMaxInvestmentSizeError(int timeOut) {
	return isDisplayed(driver, maxInvestmentSizeError, "Visibility", timeOut, "max investments amount error text");
}

@FindBy(xpath = "//div[@id='validAUM']")
private WebElement pleaseEnterValidAUMError;


/**
 * @return the pleaseEnterValidAUMError
 */
public WebElement getPleaseEnterValidAUMError(int timeOut) {
	return isDisplayed(driver, pleaseEnterValidAUMError, "Visibility", timeOut, "aum error text");
}
/****************Change Firm Logo*****************************/

@FindBy(xpath = "//div[text()='Change Firm Logo ']")
private WebElement changeFirmLogoHeading;
/**
 * @return the changeFirmLogoHeading
 */
public WebElement getChangeFirmLogoHeading(int timeOut) {
	return isDisplayed(driver, changeFirmLogoHeading, "Visibility", timeOut, "change firm logo heading");
}

/*****************************Change Profile Picture errors**********************/
@FindBy(xpath = "//div[@id='ErrorDiv']")
private WebElement specifyFileToUploadError;

/**
 * @return the specifyFileToUploadError
 */
public WebElement getSpecifyFileToUploadError(int timeOut) {
	return isDisplayed(driver, specifyFileToUploadError, "Visibility", timeOut, "Please specify file to upload error on change profile picture window");
}
@FindBy(xpath = "//ul[@id='pg:frm1:errmessages']")
private WebElement sizeGreaterUploadError;

/**
 * @return the sizeGreaterUploadError
 */
public WebElement getSizeGreaterUploadError(int timeOut) {
	return isDisplayed(driver, sizeGreaterUploadError, "Visibility", timeOut, "File greater than 2mb error on change profile picture window");
}

/****************************Change password errors****************************/

@FindBy(xpath = "//div[@id='oldpass']")
private WebElement oldPasswordTextboxError;

/**
 * @return the oldPasswordTextboxError
 */
public WebElement getOldPasswordTextboxError(int timeOut) {
	return isDisplayed(driver, oldPasswordTextboxError, "Visibility", timeOut, "old password textbox error message");
	
}
@FindBy(xpath = "//div[@id='newpass']")
private WebElement newPasswordTextboxError;

/**
* @return the newPasswordTextboxError
*/
public WebElement getNewPasswordTextboxError(int timeOut) {
return isDisplayed(driver, newPasswordTextboxError, "Visibility", timeOut, "new password textbox error message");
}

@FindBy(xpath = "//div[@id='confirmpass']")
private WebElement confirmPasswordTextboxError;

/**
* @return the confirmPasswordTextboxError
*/
public WebElement getConfirmPasswordTextboxError(int timeOut) {
return isDisplayed(driver, confirmPasswordTextboxError, "Visibility", timeOut, "confirm password textbox error message");
}

@FindBy(xpath = "//ul[@id='pg:frm:err1']")
private WebElement commonErrorChangePassword;

/**
* @return the commonErrorChangePassword
*/
public WebElement getCommonErrorChangePassword(int timeOut) {
return isDisplayed(driver, commonErrorChangePassword, "Visibility", timeOut, "Common error message in change password window");
}



}
