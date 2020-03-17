/**
 * 
 */
package com.navatar.pageObjects;
import static com.navatar.generic.CommonVariables.*;
/**
 * @author Parul Singh
 *
 */
public interface NIMPageErrorMessage {

	public String lastNameErrorMessageOnRegisterPopup="Please enter the required field.";
	public String invalidNameErrorMessage="Invalid Name";
	public String problemLoggingInErrorMessage="We can’t log you in because of the following error. For more information, contact your Salesforce administrator.";
	public String registrationSuccessfulPopMessage="You have registered successfully for Navatar Investor.";
    public String errorMessageBeforeGivingInternalUserAccess="The Navatar Investor functionality is not available for you. Please contact your Navatar Administrator.";      
    public String adminLabelToolTipMessage="To be granted permission, users first need to register with Navatar Investor.";
    public String adminAccessConfirmationMessage="Are you sure you want to provide Admin permissions to "+CRMUser1FirstName+" "+CRMUser1LastName;
    public String insufficientPermissionErrorMessage="You do not have permissions to edit this information. Please contact your Navatar or Investor Administrator.";
    public String InvestorNameToolTipErrorMsg="Investor's Name will not be included in any document in a Common or Shared folder.";
    public String watermarkingInsufficientPermissionErrorMsg="You do not have permissions to edit this information. Please contact your Navatar or Investor Administrator.";
    public String toolTipMessage = "Once enabled, all documents uploaded to the workspace will need to be approved before being accessible to Investors. Only those users who are selected below will have the ability to approve documents.";
    public String noDataToDisplay = "No data to display.";
    public String allSelectedUsersText = "All selected users will be granted permission to Manage Approvals.";
    public String areYouSureYouWantToProceed="Are you sure you want to proceed?";
    public String AddRowErrorMsg="You can't add more rows.";
    public String FirmNamePleaseSelectLocationErrorMsg="Please select a Location for My Firm's Name.";
    public String InvestorNamePleaseSelectLocationErrorMsg="Please select a Location for Investor Name.";
    public String FundNamePleaseSelectLocationErrorMsg="Please select a Location for Fund Name.";
    public String DatePleaseSelectLocationErrorMsg="Please select a Location for Date.";
    public String IPAddressPleaseSelectLocationErrorMsg="Please select a Location for IP Address.";
    public String EmailAddressPleaseSelectLocationErrorMsg="Please select a Location for Email Address.";
    public String SameLocationErrorMsg="Multiple labels cannot occupy the same position. Please select a unique position for each label and then save.";
    public String CustomLabelValueErrorMsg="Please enter Additional Custom Label Value1.";
    public String CustomLabelDropDownListErrorMsg="Please select Location for value1.";
    public String WaterMarkingspiecalCharErrorMsg="Special characters @ ; : are not allowed in row 1.";
    public String pleaseSelectAleastOneLabelErrorMsg="Please select at least one label to continue.";
    public String myProfileInfoIconMessage="To change your email address, please contact Navatar Support.";
    public String myFirmProfileInfoIconMessage="The Public Login Link can be used on your firm's corporate website in order to direct investors directly to your branded Navatar Investor page.";
    public String myProfileNameErrorMessage="Please enter the required fields.";
    public String myFirmProfileFirmNameErrorMessage="Please enter the required fields.";
    public String myFirmProfileEmailErrorMessage="Invalid Email address.";
    public String myFirmProfileAUMErrorMessage="Please enter a valid AUM.";
    public String myFirmProfileLogoErrorMessage="Error: Please specify a file to upload, or use the \"Browse\" button to locate it in your local file system.";
    public String bulkUploadMessage = "For the system to properly distribute file to their destination when uploading a group of individual files, they must be named in one of the following methods.";
    public String fileSplitterMeessage = "The system will determine how to split and distribute PDF files in Workspaces based on the delimiters below. These must be included in your mail merge template used in creating the combined PDF to be split.";
    public String bulkUploadTooltip1 = "For Fundraising Workspace uploads: Investor Name should match the";
    public String bulkUploadTooltip2 = "Institution name used in the Fundraising.";
    public String bulkUploadTooltip3 = "For Investor Workspace uploads: Investor Name should match the";
    public String bulkUploadTooltip4 = "Institution name used in the Commitment.";
    public String fileSplitterTooltip1 = "Starting delimiter must always be located at the start of a new page.";
    public String fileSplitterTooltip2 = "Investor Name should match the Institution name used in the Fundraising.";
    public String fileSplitterTooltip3 = "Investor LP Name should match the Institution name used in the Commitment.";
    public String ChangeLogoInvalidTypeErrorMessage="Error: File Type Not Correct.";
    public String LogoUploadConfirmationMessage="Are you sure you want to change the logo ?";
    public String addFolderToolTip1 = "You define contact access; All subfolders will be Standard as well";
    public String addFolderToolTip2 = "All contacts with any access to the Workspace have access to this folder;";
    public String addFolderToolTip3 = "All subfolders will be Common as well";
    public String addFolderToolTip4 = "You define contact access; All subfolders will be Shared as well";
    public String addFolderToolTip5 = "Only for internal users within your firm; All subfolders will be Internal as well";
    public String addFolderCancelPopup = "Are you sure you want to cancel?";
    public String pleaseEnterValue = "Please enter a value.";
    public String saveFolderTemp = "Are you sure you want to save the Folder Template?";
    public String FolderTemplatenoDataToDisplay = "No data to display.";
    public String InsufficientHeader="Insufficient Permissions";
    public String FolderTemplateEditInsufficientErrorMessage="You do not have permissions to edit this Template. Please contact your Navatar or Investor Administrator.";
    public String FolderTemplateDeleteInsufficientErrorMessage="You do not have permissions to delete this Template. Please contact your Navatar or Investor Administrator.";
    public String FolderTemplateDeleteConfirmationMessage="Are you sure you want to delete the Folder Template?";
    public String DeleteTemplateConfirmationHeader="Confirm Deletion";
    public String BulkAddFolderErrorMessage="You cannot add more than 100 folders.";
    public String RenameFolderPopUpHeader="Rename Folder";
    public String DeleteFolderPopUpHeader="Delete Folder";
    public String DeleteFolderConfirmationMessage="Are you sure you want to delete this folder and all subfolders?";
    public String DuplicateTemp = "Template name already exists.";
    public String PleaseEnterFolderNameErrorMsg="Please enter a folder name.";
    public String invalidChars1 = "Invalid Folder Name. Folder name cannot have special characters like ";
    public String invalidChars2="\" <,>,:,\",/,\\,?,*,{ \"";
    public String cannotAddFolderNames = "You cannot add (Common) or (Internal) or (Shared) in folder name.";
    public String secondCommonFolder = "You can only create one Common folder. Please create subfolders under your existing Common folder.";
    public String secondInternalFolder = "You can only create one Internal folder. Please create subfolders under your existing Internal folder.";
    public String FolderAlreadyExistErrormsg="Folder name already exists.";

}
