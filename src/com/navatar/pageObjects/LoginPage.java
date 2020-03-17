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
/**
 * @author Parul Singh
 *
 */
public class LoginPage extends BasePageBusinessLayer{

	/**
	 * @param driver
	 */


	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="username")
	private WebElement userNameTextBox;
	
	/**
	 * @return the userNameTextBox
	 */
	public WebElement getUserNameTextBox(int timeOut) {
		return isDisplayed(driver, userNameTextBox, "Visibility", timeOut, "Username Text Box.");
	}
	
	@FindBy(id="password")
	private WebElement passwordTextBox;
	
	/**
	 * @return the passwordTextBox
	 */
	public WebElement getPasswordTextBox(int timeOut) {
		return isDisplayed(driver, passwordTextBox, "Visibility", timeOut, "Password Text Box.");
	}

	@FindBy(id="Login")
	private WebElement loginButton;
	
	/**
	 * @return the loginButton
	 */
	public WebElement getLoginButton(int timeOut) {
		return isDisplayed(driver, loginButton, "Visibility", timeOut, "Login Button.");
	}
	/**********************************investor login************************************************/
	
	@FindBy (xpath = "//input[@id='page:frmid:username']")
	private WebElement investorUsernameTextbox;

	/**
	 * @return the investorUsernameText
	 */
	public WebElement getInvestorUsernameTextbox(int timeOut) {
		return isDisplayed(driver, investorUsernameTextbox, "Visibility", timeOut, "Investor username text box");
	}
	
	@FindBy(xpath = "//*[@id='page:frmid:password']")
	private WebElement investorPasswordTextbox;

	/**
	 * @return the investerPasswordTextbox
	 */
	public WebElement getInvestorPasswordTextbox(int timeOut) {
		return isDisplayed(driver, investorPasswordTextbox, "Visibility", timeOut, "Investor password textbox");
	}
	@FindBy(xpath = "//input[@title='Login']")
	private WebElement investorLoginButton;

	/**
	 * @return the investorLoginButton
	 */
	public WebElement getInvestorLoginButton(int timeOut) {
		return isDisplayed(driver, investorLoginButton, "Visibility", timeOut, "Investor login button ");
	}
	
	@FindBy(xpath = "//img[contains(@src,'twitter')]")
	private WebElement twitterIcon;

	/**
	 * @return the twitterIcon
	 */
	public WebElement getTwitterIcon(int timeOut) {
		return isDisplayed(driver, twitterIcon, "Visibility", timeOut, "Twitter icon");
	}
	@FindBy(xpath = "//img[contains(@src,'facebook')]")
	private WebElement facebookIcon;

	/**
	 * @return the facebookIcon
	 */
	public WebElement getFacebookIcon(int timeOut) {
		return isDisplayed(driver, facebookIcon, "Visibility", timeOut, "Facebook icon");
	}
	@FindBy(xpath = "//img[contains(@src,'navatar')]")
	private WebElement navatarImg;

	/**
	 * @return the navatarImg
	 */
	public WebElement getNavatarImg(int timeOut) {
		return isDisplayed(driver, navatarImg, "Visibility", timeOut, "Navatar image logo");
	}
	/*@FindBy(xpath = "//a[@title='Reset password']")
	private WebElement forgotPasswordInvestor;

	/**
	 * @return the forgotPasswordInvestor
	 */
	/*public WebElement getForgotPasswordInvestor(int timeOut) {
		return isDisplayed(driver, forgotPasswordInvestor, "Visibility", timeOut, "Forgot your password link on investor login page");
	}*/
	@FindBy(xpath = "//a[@title='Forgot your user name?']")
	private WebElement forgotUsernameInvestor;

	/**
	 * @return the forgotPasswordInvestor
	 */
	public WebElement getForgotUsernameInvestor(int timeOut) {
		return isDisplayed(driver, forgotUsernameInvestor, "Visibility", timeOut, "Forgot your username link on investor login page");
	}
	@FindBy(xpath = "(//a[@title='Signup'])[1]")
	private WebElement investorAlreadyInvitedSignupLink;

	/**
	 * @return the investorAlreadyInvitedSignupLink
	 */
	public WebElement getInvestorAlreadyInvitedSignupLink(int timeOut) {
		return isDisplayed(driver, investorAlreadyInvitedSignupLink, "Visibility", timeOut, "Sign up button for investor and already invited on investor login page");
	}
	@FindBy(xpath = "(//a[@title='Signup'])[2]")
	private WebElement generalPartnerAlreadyInvitedSignupLink;

	/**
	 * @return the generalPartnerAlreadyInvitedSignupLink
	 */
	public WebElement getGeneralPartnerAlreadyInvitedSignupLink(int timeOut) {
		return isDisplayed(driver, generalPartnerAlreadyInvitedSignupLink, "Visibility", timeOut, "Sign up button for general partner and already invited on investor login page");
	}
	

	/**************************investor forgot password*******************************/
	@FindBy(xpath = "//input[@id='forgotPassword:frmid:username']")
	private WebElement emailTextbox;

	/**
	 * @return the emailTextbox
	 */
	public WebElement getEmailTextbox(int timeOut) {
		return isDisplayed(driver, emailTextbox, "Visibility", timeOut, "Email textbox on forgot password page");
	}
	
	
	/***************************investor forgot password reset page**********************/
	@FindBy(xpath = "//div[@id='emailMsgDiv']/ancestor::tr/following-sibling::tr//span[@class='title']")
	private WebElement enterEmailIdTextForgotPasswordPage;
	
	
	/**
	 * @return the enterEmailIdTextForgotPasswordPage
	 */
	public WebElement getEnterEmailIdTextForgotPasswordPage(int timeOut) {
		return isDisplayed(driver, enterEmailIdTextForgotPasswordPage, "Visibility", timeOut, "Enter email id text present on forgot password page");
	}

	@FindBy(xpath = "//input[@id='changePassword:SiteTemplate:theForm:psw']")
	private WebElement forgotPasswordNewPasswordTextbox;

	/**
	 * @return the forgotPasswordNewPasswordTextbox
	 */
	public WebElement getForgotPasswordNewPasswordTextbox(int timeOut) {
		return isDisplayed(driver, forgotPasswordNewPasswordTextbox, "Visibility", timeOut, "new password textbox on forgot password page");
	}
	
	@FindBy(xpath = "//input[@id='changePassword:SiteTemplate:theForm:vpsw']")
	private WebElement forgotPasswordVerifyNewPasswordTextbox;

	/**
	 * @return the forgotPasswordNewPasswordTextbox
	 */
	public WebElement getForgotPasswordVerifyNewPasswordTextbox(int timeOut) {
		return isDisplayed(driver, forgotPasswordVerifyNewPasswordTextbox, "Visibility", timeOut, "verify new password textbox on forgot password page");
	}
	@FindBy(xpath = "//span[contains(text(),'An email has been sent to you with your temporary password')]")
	private WebElement emailHasBeenSentText;

	/**
	 * @return the emailHasBeenSentText
	 */
	public WebElement getEmailHasBeenSentText(int timeOut) {
		return isDisplayed(driver, emailHasBeenSentText, "Visibility", timeOut, "Email has been sent to reset password on forgot password page");
	}

	
	@FindBy(xpath = "(//form[@id='changePassword:SiteTemplate:theForm']//label)[1]")
	private WebElement newPasswordText;

	/**
	 * @return the newPasswordText
	 */
	public WebElement getNewPasswordText(int timeOut) {
		return isDisplayed(driver, newPasswordText, "Visibility", timeOut, "new password text present on forgot password page");
	}
	@FindBy(xpath = "(//form[@id='changePassword:SiteTemplate:theForm']//label)[2]")
	private WebElement verifyYourPasswordText;

	/**
	 * @return the verifyYourPasswordText
	 */
	public WebElement getVerifyYourPasswordText(int timeOut) {
		return isDisplayed(driver, verifyYourPasswordText, "Visibility", timeOut,
				"verify your password text present on forgot password page");
	}
	
	@FindBy(xpath = "//input[@id='changePassword:SiteTemplate:theForm:cpwbtn']")
	private WebElement changePasswordBtnForgotPassword;

	/**
	 * @return the changePasswordBtnForgotPassword
	 */
	public WebElement getChangePasswordBtnForgotPassword(int timeOut) {
		return isDisplayed(driver, changePasswordBtnForgotPassword, "Visibility",
				timeOut, "change password buton on forgot password page");
	}
	@FindBy(xpath = "//td[contains(text(),'Please contact our')]")
	private WebElement pleaseContactOurLabel;

	/**
	 * @return the pleaseContactOurLabel
	 */
	public WebElement getPleaseContactOurLabel(int timeOut) {
		return isDisplayed(driver, pleaseContactOurLabel, "Visibility", timeOut, "Please contact our, label text");
	}
	
	@FindBy(xpath = "//a[contains(text(),'Customer Support')]")
	private WebElement customerSupportLink;

	/**
	 * @return the customerSupportLink
	 */
	public WebElement getCustomerSupportLink(int timeOut) {
		return isDisplayed(driver, customerSupportLink, "Visibility", timeOut, "Customer support url on forgot password page");
	}
	
	@FindBy(xpath = "//a[contains(text(),'close')]")
	private WebElement closeButtonForgotPassword;

	/**
	 * @return the closeButtonForgotPassword
	 */
	public WebElement getCloseButtonForgotPassword(int timeOut) {
		return isDisplayed(driver, closeButtonForgotPassword, "Visibility", timeOut, "close button forgot password page");
	}
	@FindBy(xpath = "//a[contains(text(),'About Navatar Group')]")
	private WebElement aboutNavatarLink;

	/**
	 * @return the aboutNavatarLink
	 */
	public WebElement getAboutNavatarLink(int timeOut) {
		return isDisplayed(driver, aboutNavatarLink, "Visibility", timeOut, "About Navatar url on forgot page");
	}
	@FindBy(xpath = "//a[contains(text(),'Contact Us')]")
	private WebElement contactUsLink;

	/**
	 * @return the contactUsLink
	 */
	public WebElement getContactUsLink(int timeOut) {
		return isDisplayed(driver, contactUsLink, "Visibility", timeOut, "contact us url on forgot password url");
	}
	
	/****************************************forgot password error messages*********************************/
	@FindBy(xpath = "(//li[contains(text(),'Validation Error: Value is required')])[1]")
	private WebElement valueIsRequiredError;

	/**
	 * @return the valueIsRequiredError
	 */
	public WebElement getValueIsRequiredError(int timeOut) {
		return isDisplayed(driver, valueIsRequiredError, "Visibility", timeOut, "value is required error message on forgot password page");
	}
	
	@FindBy(xpath = "//div[contains(text(),'Error: You cannot reuse this old password')]")
	private WebElement cannotReuseOldPassword;

	/**
	 * @return the cannotReuseOldPassword
	 */
	public WebElement getCannotReuseOldPassword(int timeOut) {
		return isDisplayed(driver, cannotReuseOldPassword, "Visibility", timeOut, "cannot reuse old password error message on forgot password page");
	}
	@FindBy(xpath = "//div[contains(text(),'The passwords do not match')]")
	private WebElement passwordsNotMatch;

	/**
	 * @return the passwordsNotMatch
	 */
	public WebElement getPasswordsNotMatch(int timeOut) {
		return isDisplayed(driver, passwordsNotMatch, "Visibility", timeOut, "Passwords do not match error message on forgot password page");
	}
	
	@FindBy(xpath="//a[@title='Reset password']")
	private WebElement resetPasswordLink;

	/**
	 * @return the resetPasswordLink
	 */
	public WebElement getResetPasswordLink(int timeOut) {
		return isDisplayed(driver, resetPasswordLink, "Visibility", timeOut, "Reset Password Link");
	}
	
	@FindBy(xpath="//input[@name='page:frmid:username']/../..//preceding-sibling::tr//label[@class='datalabelDisplayForm']")
	private WebElement resetPasswordEmailText;

	/**
	 * @return the resetPasswordEmailText
	 */
	public WebElement getResetPasswordEmailText(int timeOut) {
		return isDisplayed(driver, resetPasswordEmailText, "Visibility", timeOut, "Email text");
	}
	
	@FindBy(xpath="//b[text()='Please enter your email address to reset your password.']")
	private WebElement targetResetPassUserMsg;

	/**
	 * @return the targetForgotPassUserMsg
	 */
	public WebElement getTargetResetPassUserMsg(int timeOut) {
		return isDisplayed(driver, targetResetPassUserMsg, "Visibility", timeOut, "target Reset Pass User Msg");
	}
	
	@FindBy(xpath="//input[@id='forgotPassword:frmid:username']")
	private WebElement resetPasswordUserNameTextBox;

	/**
	 * @return the forgotPasswordUserNameTextBox
	 */
	public WebElement getResetPasswordUserNameTextBox(int timeOut) {
		return isDisplayed(driver, resetPasswordUserNameTextBox, "Visibility", timeOut, "Reset Password Username Text Box");
	}
	
	@FindBy(xpath="//form[@id='forgotPassword:frmid']//tr[2]//span/b")
	private WebElement resetPasswordLabelText;
	
	
	
	/**
	 * @return the resetPasswordLabelText
	 */
	public WebElement getResetPasswordLabelText(int timeOut) {
		return isDisplayed(driver, resetPasswordLabelText, "Visibility", timeOut, "reset password label text");
	}

	@FindBy(xpath="//input[@title='Submit']")
	private WebElement resetPasswordSubmitButton;

	/**
	 * @return the forgotPasswordSubmitButton
	 */
	public WebElement getResetPasswordSubmitButton(int timeOut) {
		return isDisplayed(driver, resetPasswordSubmitButton, "Visibility", timeOut, "Reset Password Submit Button");
	}
	
	@FindBy(xpath="//span[@id='errormsg1']/label")
	private WebElement resetPasswordSubmitButtonErrorMessage;

	/**
	 * @return the resetPasswordSubmitButtonErrorMessage
	 */
	public WebElement getResetPasswordSubmitButtonErrorMessage(int timeOut) {
		return isDisplayed(driver, resetPasswordSubmitButtonErrorMessage, "Visibility", timeOut, "Reset Password SubmitButton ErrorMessage");
	}
	
	@FindBy(xpath="//span[@id='errormsg1']/label/a")
	private WebElement resetPasswordSubmitButtonClickLink;

	/**
	 * @return the forgotPasswordSubmitButtonClickLink
	 */
	public WebElement getResetPasswordSubmitButtonClickLink(int timeOut) {
		return isDisplayed(driver, resetPasswordSubmitButtonClickLink, "Visibility", timeOut, "reset Password Submit Button Click Link");
	}
	
	@FindBy(xpath="//i[@class='valdtnClass']")
	private WebElement resetPasswordregistrationpagePasswordErrorMsg;

	/**
	 * @return the resetPasswordregistrationpagePasswordErrorMsg
	 */
	public WebElement getResetPasswordregistrationpagePasswordErrorMsg(int timeOut) {
		return isDisplayed(driver, resetPasswordregistrationpagePasswordErrorMsg, "Visibility", timeOut, " Registration page password error msg");
	}
	
	@FindBy(xpath="//center/div")
	private WebElement resetPasswordRegistrationPageClickHereMessage;

	/**
	 * @return the resetPasswordRegistrationPageClickHereMessage
	 */
	public WebElement getResetPasswordRegistrationPageClickHereMessage(int timeOut) {
		return isDisplayed(driver, resetPasswordRegistrationPageClickHereMessage, "Visibility", timeOut, "Registration page click here message");
	}
	
	@FindBy(xpath="//div[@id='emailMsgDiv']//span")
	private WebElement resetPasswordConfirmationTemproryPasswdMsg;

	/**
	 * @return the forgotPasswordConfirmationTemproryPasswdMsg
	 */
	public WebElement getResetPasswordConfirmationTemproryPasswdMsg(int timeOut) {
		return isDisplayed(driver, resetPasswordConfirmationTemproryPasswdMsg, "Visibility", timeOut, "Reset Password Confirmation TemproryPasswd Msg");
	}
	
	@FindBy(xpath="//input[@id='User_FirstLogin:Form:pwd']/..//span[@id='pwrd']/font")
	private WebElement passwordTargetRegPageErrorMessage;

	/**
	 * @return the passwordTargetRegPageErrorMessage
	 */
	public WebElement getPasswordTargetRegPageErrorMessage(int timeOut) {
		return isDisplayed(driver, passwordTargetRegPageErrorMessage, "Visibility", timeOut, "Password Target Reg Page Error Message");
	}
	
	@FindBy(xpath="//a[@title='click here']")
	private WebElement clickHereLinkOnTargetLoginPage;

	/**
	 * @return the clickHereLinkOnTargetLoginPage
	 */
	public WebElement getClickHereLinkOnTargetLoginPage(int timeOut) {
		return isDisplayed(driver, clickHereLinkOnTargetLoginPage, "Visibility", timeOut, "Click Here Link On Target Login Page");
	}
	
	@FindBy(xpath="//a[@title='HOME']")
	private WebElement homeLink;

	/**
	 * @return the homeLink
	 */
	public WebElement getHomeLink(int timeOut) {
		return isDisplayed(driver, homeLink, "Visibility", timeOut, "home link");
	}
		
	@FindBy(xpath="//div[@class='Error_fancybox FancyboxContainer ui-draggable']/div[@class='head_popup']")
	private WebElement resetPasswordLinkExpiredErrorPopupHeader;

	/**
	 * @return the resetPasswordLinkExpiredErrorPopupHeader
	 */
	public WebElement getResetPasswordLinkExpiredErrorPopupHeader(int timeOut) {
		return isDisplayed(driver, resetPasswordLinkExpiredErrorPopupHeader, "Visibility", timeOut, "reset Password link expired error Popup header");
	}
	
	@FindBy(xpath="//div[@class='Error_fancybox FancyboxContainer ui-draggable']/div[@class='formbox contacts_n_name_div']")
	private WebElement resetPasswordLinkExpiredErrorMessage;

	/**
	 * @return the resetPasswordLinkExpiredErrorPopupHeader
	 */
	public WebElement getResetPasswordLinkExpiredErrorMessage(int timeOut) {
		return isDisplayed(driver, resetPasswordLinkExpiredErrorMessage, "Visibility", timeOut, "reset Password link expired error Message");
	}
	
	@FindBy(xpath="//a[@title='BLOG']")
	private WebElement blogLink;

	/**
	 * @return the blogLink
	 */
	public WebElement getBlogLink(int timeOut) {
		return isDisplayed(driver, blogLink, "Visibility", timeOut, "blog link");
	}
	
	
	
	@FindBy(xpath="//div[@class='Error_fancybox FancyboxContainer ui-draggable']//a[@title='OK']")
	private WebElement resetPasswordLinkExpiredErrorPopupOKButton;

	/**
	 * @return the resetPasswordLinkExpiredErrorPopupOKButton
	 */
	public WebElement getResetPasswordLinkExpiredErrorPopupOKButton(int timeOut) {
		return isDisplayed(driver, resetPasswordLinkExpiredErrorPopupOKButton, "Visibility", timeOut, "reset Password link expired OK button");
	}
	
	@FindBy(xpath="//div[@class='Error_fancybox FancyboxContainer ui-draggable']//a[@title='Close']")
	private WebElement resetPasswordLinkExpiredErrorPopupCloseIcon;

	/**
	 * @return the resetPasswordLinkExpiredErrorPopupOKButton
	 */
	public WebElement getResetPasswordLinkExpiredErrorPopupCloseIcon(int timeOut) {
		return isDisplayed(driver, resetPasswordLinkExpiredErrorPopupCloseIcon, "Visibility", timeOut, "reset Password link expired Close icon");
	}
	
	@FindBy(xpath="//a[@title='SUPPORT']")
	private WebElement supportLink;

	/**
	 * @return the supportLink
	 */
	public WebElement getSupportLink(int timeOut) {
		return isDisplayed(driver, supportLink, "Visibility", timeOut, "support link");
	}
	
	
	@FindBy(xpath="//a[@title='PRODUCTS']")
	private WebElement productsLink;

	/**
	 * @return the productsLink
	 */
	public WebElement getProductsLink(int timeOut) {
		return isDisplayed(driver, productsLink, "Visibility", timeOut, "products link");
	}
	
	@FindBy(xpath="//a[@title='DEAL SOURCING']")
	private WebElement dealSourcingLink;

	/**
	 * @return the dealSourcingLInk
	 */
	public WebElement getDealSourcingLink(int timeOut) {
		return isDisplayed(driver, dealSourcingLink, "Visibility", timeOut, "deal sourcing link");
	}
	
	@FindBy(xpath="//a[@title='LP NETWORK']")
	private WebElement lpNetworkLink;

	/**
	 * @return the lpNetworkLink
	 */
	public WebElement getLpNetworkLink(int timeOut) {
		return isDisplayed(driver, lpNetworkLink, "Visibility", timeOut, "lp network link");
	}
	
	
	@FindBy(xpath="//a[@title='CUSTOMERS']")
	private WebElement customersLink;

	/**
	 * @return the customersLink
	 */
	public WebElement getCustomersLink(int timeOut) {
		return isDisplayed(driver, customersLink, "Visibility", timeOut, "customers link");
	}
	
	@FindBy(xpath="//a[@title='COMPANY']")
	private WebElement companyLink;

	/**
	 * @return the companyLink
	 */
	public WebElement getCompanyLink(int timeOut) {
		return isDisplayed(driver, companyLink, "Visibility", timeOut, "Company Link");
	}
	
	@FindBy(xpath="//a[@title='CONTACT US']")
	private WebElement contactUsLink1;

	/**
	 * @return the contactUsLink1
	 */
	public WebElement getContactUsLink1(int timeOut) {
		return isDisplayed(driver, contactUsLink1, "Visibility", timeOut, "contact us link");
	}
	
}
