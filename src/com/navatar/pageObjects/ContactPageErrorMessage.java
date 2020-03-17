/**
 * 
 */
package com.navatar.pageObjects;

/**
 * @author Parul Singh
 *
 */
public interface ContactPageErrorMessage {
	public String errorMessageBeforeAdminRegistration="You have not been provided access to create Workspaces by your Navatar Administrator.";
	public String errorMessageAfterAdminAndCRMUserRegistrationFundraisingWorkspace="No Fundraising Workspace has been built for this Investor";
	public String errorMessageAfterAdminAndCRMUserRegistrationInvestorWorkspace="No Investor Workspace has been built for this Investor";
	public String errorMessageBeforeGivingInternalUserAccess="You have not been provided access to create Workspaces by your Navatar Administrator.";
	public String errorMessageAfterGivingInternalUserAccess="Congratulations! Your Administrator has provided you access to create Workspaces. Please click on the Navatar Investor Manager tab to complete your registration.";
	public String activityHistoryErrorMessage="No records to display";
	public String ContactemailChangedErrorMessage="System could not find the specified Contact/Firm. Please contact your Navatar or Investor Administrator.";
	public static String resetPasswordToolTipMessage="This contact has not yet registered.";
	public static String resetPasswordPopupMessage="Please select one of the following options to reset the password for";
	public static String resetPasswordsendLinkMessage="Send the reset password email to ";
	public static String resetPasswordCopyLinkMessage="Copy reset password link";
	public static String resetPasswordCopyEmailText1="Link is copied.";
	public static String resetPasswordCopyEmailText2=" Please use it in a new browser.";



}
