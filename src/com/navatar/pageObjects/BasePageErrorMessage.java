/**
 * 
 */
package com.navatar.pageObjects;

/**
 * @author Ankur Rana
 *
 */
public interface BasePageErrorMessage {

	public String PendingDisclaimerPopUpMessage="There are disclaimer(s) that need to be accepted before accessing the documents. Please click on the button below to view the disclaimer(s).";
	public String AccessDeniedPopUpMessage = "You are required to accept the disclaimer in order to access this document. Please click on the button below to view the disclaimer.";
	public String BulkDownloadAccessDeniedPopUpMessage = "You are required to accept the disclaimer in order to download document(s).Please click on the button below to view the disclaimer.";
	public String AddFolderInfoIconMessage="Standard: You define contact access; All subfolders will be Standard as well";
	public String AddFolderInfoIconMessage1 = "Common: All contacts with any access to the Workspace have access to this folder;";
	public String AddFolderInfoIconMessage2 = "All subfolders will be Common as well";
	public String AddFolderInfoIconMessage3 = "Shared: You define contact access; All subfolders will be Shared as well";
	public String AddFolderInfoIconMessage4 = "Internal: Only for internal users within your firm; All subfolders will be Internal as well";
	public String YouAreAlreadyRegistered = "You are already registered for Navatar Investor"; 
	public String eightCharactersMessage = "Enter at least 8 characters including letters and numbers.";
	public String deleteHeaderMessage = "Confirm Deletion";
	public String deleteTextMessage = "Are you sure you want to delete this Document?";
	public String alertMsgWithoutSelectingAFolder="Please Select a folder for search.";
	public String nodataDisplayMsg="No data to display.";
	public String alertMsgWithoutEnteringValue="Please enter a value.";
	public static String resetPasswordRegistrationPagePasswordErrorMessage="Enter at least 8 characters including letters and numbers.";
    public static String resetPasswordEnterAValueErrorMsg="Please enter a value.";
    public static String resetPasswordContainingletterandNumberErrorMessage="Password must have at least 8 characters including letters and numbers.";
    public static String resetPasswordLast3PasswordSameErrorMessage="The new password cannot be the same as the last 3 passwords.";
    public static String resetPasswordConfirmPasswordNotSameErrorMessage="Password and confirm password should be same.";
    public static String resetPasswordPasswordChangeMessage="Your password has been changed. Please log in.";
    public static String resetPasswordPasswordDoNotMatchErrorMessage="The Username or Password you provided do not match. Please check your credentials.";
   
    //America/New_York America/Los_Angeles
    
    public String AmericaLosAngelesTimeZone="America/New_York";
}
