/**
 * 
 */
package com.navatar.pageObjects;

/**
 * @author Parul Singh
 *
 */
public interface FundsPageErrorMessage {

	public String errorMessageBeforeAdminRegistration="You have not been provided access to create Workspaces by your Navatar Administrator.";
	public String errorMessageBeforeGivingInternalUseraccess="You have not been provided access to create Workspaces by your Navatar Administrator.";
	public String errorMessageAfterGivingInternalUseraccess="Congratulations! Your Administrator has provided you access to create Workspaces. Please click on the Navatar Investor Manager tab to complete your registration.";
	public static String OnlineImportSuccessMsg="Document(S) imported successfully.";
	public static String UploadSelectTargetMessage="Please select at least one Document to continue.";
	public static String  updateDocumentErrorMsg="Please select a document to update.";
	public static String updateDocumentSuccessAlertMsg="Your document has been updated. Please refresh the Contents list.";
	public String DocumentUploadSuccessMsg = "Document(s) uploaded successfully.";
	public String DocumentUploadRepeatSuccessMsg = "Document uploaded successfully.";
	public String noDataToDisplayErrorMessage="No data to display.";
	public String manageInvestorAddedPopupMessage="Investor added successfully.";
	public String manageInvestorDeletedPopupMessage="Investor removed successfully.";
	public String manageInvestorRenamePopupMessage="Please note that renaming the Investor Account only affects the name shown in the Workspace, and not other sections of the Navatar CRM.";
	public String manageInvestorRenameSpecialCharacterMessage="You cannot renamed an Investor whose name contains special characters.";
	public String manageInvestorRenameFieldBlankErrorMessage="Please enter a value";
	public String noDocumentUploaded = "To upload document(s), please drop it in upload area.";
	public String zeroSizeFile = "Document size should be greater than 0 KB.";
	public String documentNotUploadedInUploadedArea = "Document(s) cannot be uploaded, not dropped into the upload area.";
	public String ManageFolderPopUPYellowBlockMessage = "Please note that any changes made to this folder structure are immediate and will be applied to all Investor Accounts with access to this workspace. Upto 100 folders can be created.";
	public String pleaseSelectOneInstitution = "Please select at least one Institution to continue.";
	public String duplicateDocumentsTextUnderHeading = "Some of the Documents you attempting to add already exist. Please select how you'd like the system to handle these duplicates:";
	public String documentUpldSuccessfully = "Document(s) uploaded successfully.";
	public String filterInvalidNumberErroresage="Invalid number.";
	public String filterPicklistValueNotExistErroresage="Picklist value does not exist.";
	public String filterInvalidCurrencyErroresage="Invalid currency.";
	public String filterInvalidDateErroresage="Invalid date.";
	public String filterPleaseUseTrueOrFalseErroresage="Please use \"True\" or \"False\".";
	public String filterPleaseSelectAFieldErroresage="Please select a field.";
	public String thisOrAllInvestor = "Are you updating this document for only this Investor or all Investors?";
	public String closeWorkspaceMsg="Your Workspace has been closed.";
	public String clearWorkSpaceMsg="Your Workspace has been cleared successfully. Please Refresh the page.";
	public String couldNotFindContactOrFirm = "System could not find the specified Contact/Firm. Please contact your Navatar or Investor Administrator.";
	public String PleaseEnterFolderNameErrorMsg="Please enter a folder name.";
	public String prefixErrorMsg="You cannot add (Common) or (Internal) or (Shared) in folder name.";
	public String speicalCharErrorMsg="Invalid Folder Name. Folder name cannot have special characters like \"<,>,:,\",/,\\,?,*,{\"";
	public String speicalCharErrorMsgFundsPage="Invalid Folder Name. Folder name cannot have special characters like \" <,>,:,\",/,\\,?,*,{\"";
	public String CommonFolderCreationErrorMsg="You can create only one Common folder per Workspace. Please create subfolders under your existing Common folder.";
	public String InternalFolderCreationErrorMsg="You can create only one Internal folder per Workspace. Please create subfolders under your existing Internal folder.";
	public String FolderAlreadyExistErrormsg="Folder name already exists.";
	public String deleteFolderErrorMsg1="Are you sure you want to delete this folder and all subfolders and contents?";
	public String deleteFolderErrorMsg2="This cannot be undone.";
	public String pleaseSelectOneLP = "Please select at least one Limited Partner to continue.";
	public String manageApprovalPermissionError = "You do not have permission to Manage Approvals. Please contact your Navatar or Investor Administrator.";
	public String manageApprovalNoDocError = "Please select at least one document to approve.";
	public String manageApprovalDelError = "Please select at least one document to delete.";
	public String manageApprovalDelSuccess1 = "All the selected documents will get deleted.";
	public String manageApprovalDelSuccess2 = "Are you sure you want to proceed?";
	public String manageApprovalApproveSuccess = "selected document(s) have been approved successfully.";
	public String manageApprovalApproveMoreThan10 = "You can not update more than 10 files at a time.";
	public String manageApprovalDuplicateError = "Some of the documents you are attempting to approve already exist. Please select how you'd like the system to handle these duplicates:";
	public String manageApprovalFolderDeleted = "Folders for the below documents have been deleted and cannot be approved. The folder path is given for additional information. These documents can be deleted from the Manage Approvals popup.";
	public String pleaseSelectOneInvestorErrorMessageInManageEmails="Please select at least one Investor to send an email to.";
	public String pleaseEnterAValueErrorMessageInManageEmails="Please enter a value.";
	public String manageEmailInvitationEditBodyErrorMessage="Body cannot exceed 255 characters.";
	public String manageEmailCustomEditSubjectErrorMessage="Subject cannot exceed 255 characters.";
	public String PleaseEnterValueErrorMessage = "Please enter a value.";
	public String InvalidNameErrorMessage = "Invalid Name.";
	public String InvalidFundSizeErrorMessage = "Invalid Fund Size.";
	public String InvalidFundVintageYearErrorMessage = "Invalid Vintage(year).";
	public String InvalidFundPhoneErrorMessage = "Invalid Phone.";
	public String InvalidFundEmailErrorMessage = "Invalid Email address.";
	public String Step2Of3YellowBoxText = "Here you can define the folders that make up this Workspace.";
	public String Step2Of3PopUpBottomMessage = "Please note that this folder structure will be automatically duplicated for each Investor that you selected. Upto 100 folders can be created.";
	public String Step3Of3YellowBoxText = "Please select the Investors for which the system should create folders.";
	public String Step3Of3NoDataToDisplayMessage = "No data to display.";
	public String suffixErrorMsg = "You cannot add (common) or (Internal) or (Shared) in folder name.";
	public String SelectFolderTemplateNoDataMessage = "No data to display";
	public static String ImportFolderTemplateErrorMessage = "Please clear all the folders before importing another template.";
	public static String ClearAllFolderConfirmationMessage = "Are you sure you want to delete all folders ?";
	public static String Step2Of3ConfirmDeletionErrorMessage="Are you sure you want to delete this folder ?";
	public static String Step3Of3CongratulationMessage="Congratulations! The workspace has been built successfully. You can now add documents and define folder level permissions for your investors.";
	public static String Step3Of3InvestorAddedMessage="Investor added successfully.";
	public static String Step3Of3InvestorRemovedMessage="Investor removed successfully.";
	public static String InvestmentInfoEditFundNamePleaseEnterTextErrorMessage = "Please enter a value.";
	public static String InvestmentInfoEditFundContactNamePleaseEnterTextErrorMessage = "Please enter a value.";
	public static String InvestmentInfoEditFundPhonePleaseEnterTextErrorMessage = "Please enter a value.";
	public static String InvestmentInfoEditFundEmailPleaseEnterTextErrorMessage = "Please enter a value.";
	public static String InvestmentInfoEditInvalidFundNameErrorMessage = "Invalid Name.";
	public static String InvestmentInfoEditInvalidFundPhoneErrorMessage = "Invalid phone.";
	public static String InvestmentInfoEditInvalidFundEmailErrorMessage = "Invalid Email address.";
	public static String ManageEmailSendContactInvitationErrorMsg1="selected Investor(s) will be sent an email using the selected template.";
	public static String MnaageEmailSendContactInvitationErrorMsg2="Are you sure you want to proceed?";
	public static String MnaageEmailContactAccessErrorMessage1="Any changes in Contact Access will get lost if not applied before going to Manage Emails.";
	public static String MnaageEmailContactAccessErrorMessage2="Are you sure you want to proceed?";
	public static String contactAccessHoverText = "Below you can identify which contacts have access to the contents of this folder and what level of access each is given.";
	public static String contactAccessUploadToolTipText="Note: The Upload Documents permission requires Download / Print Documents permission.";
	public static String MnaageEmailCustomTemplateErrorMessage="You must to provide content for custom email";
	public static String invalidDelimeterError1 = "The document you're trying to upload does not have a valid delimiter";
	public static String invalidDelimeterError2 = "Please check the document or try uploading a different document with a valid delimiter";
	public static String pleaseSelectFolder = "Please select a folder to proceed";
	public static String fileDistributorDocInfo = "We've identified the object(s) you selected to be a file or files. This utility will distribute the file(s) to the appropriate investor folder(s) based on your Bulk Upload Settings. Please select the appropriate subfolder for the document(s) to be distributed to below";
	public static String fileDistributionUploadprocesswillallowyoutosplit = "This process will allow you to split a document or distribute multiple files to multiple investors.";
	public static String bulkUploadProcessCompleted1 = "The Bulk Upload process has completed successfully with ";
	public static String bulkUploadProcessCompleted2 = " files created. Some issues have been identified. You can choose to address these here or skip them and commit the changes completed successfully.";
	public static String successfullyAppliedFileDistributor = "Out of 3 potential investors, the following Investor(s) received files";
	public static String issueUiFileDistributor = " Investor(s) did not receive files";
	public static String noIssueFile = "No issue File";
	public static String noInvestorReceivedFiles = "No Investor(s) received files.";
	public static String resolveIssueFileDistri2bOf2 = "To resolve the issues, click Select Folder and identify the correct folder for the document";
	public static String YourRequestInProgressErrorMsg="Your request is in progress. Once complete, you will receive an email notification. Please refrain from making updates until that time.";
	public static String YourRequestInProgressErrorMsg1="Message of the alert:  Your request is in progress. Once complete, you will receive an email notification. Please refrain from making updates until that time.";
	public static String removeSuccessfullyAppliedText = "This will Remove the file. Click ok to continue!";
	public static String bulkAddFolderErrorMessage="You cannot add more than 100 folders.";
	public static String bulkAddInvestorErrorMessage="You cannot select more than 180 investors.";
	public static String CannotAddMoreThan200ContactErrorMesssage = "A maximum of 200 contacts can be granted access at a time. Please repeat the process if you wish to add more contact(s).";
	public static String WorkspaceLockedErrorMsg = "Your request is in progress. Once complete, you will receive an email notification. Workspace will be locked for internal users till that time.";
	public static String ManageEmailsMailSendLimitErrorMsg = "A maximum of 200 contact(s) can be selected from Manage Emails";
	public static String FilesUploadedAtATimeLimitErrorMsg = "You cannot upload more than 100 documents at a time. Please try again.";
	public static String ManageApprovalFileSizeLimitErrorMsg = "duplicate document(s) have not been approved due to size limit.";
	public static String ApproveMoreThan100FilesErrorMsg = "You cannot approve more than 100 documents at a time.";
	public static String InviteContactSuccessfulErrorMsg="All updates have been successfully applied.";
	public static String ContactEmailblankErrorMsg="Please enter email/firm details of the selected contact.";
	public static String AllContactSelectErrorMsg="Some of the selected investor contact(s) have been already invited\\Selected as investor contact or Email address\\Firm details are missing.";
	public static String contactAccessRemovedPopUpMsg="Contact access successfully removed.";
	public static String alreadyInvitedContactAlertMsg="The selected contact is already Selected or Invited to this folder.";
	public static String toolTipinheritedPermissiononRemoveLink="Contact Access permission is inherited from Parent Folder and can be removed from there only.";
	
	
	//**************************************** IP Analytics***********************************************************//
	public static String MostViewedDocErrorMsg="No data to display.";
	public static String MostActiveContactErrorMsg="No data to display.";
	public static String MostActiveContactActivityCountToolTip="Total count of document views, downloads, uploads & updates by Investors.";
	public static String DocumentNotViewedAnyDoc="No data to display.";
	public static String DocumentNotViewedOrDownload="No data to display.";
	public static String DocumentNotViewedOrDownloadText="Please find below list of documents that have not been viewed or downloaded by contacts in this workspace";
	public static String ContactNotViewedAnyDocumentHeaderText="Please find below list of contacts who have not viewed any documents in this workspace";
	public static String ContactNotFoundErrorMessage="System could not find the specified contact. Please contact your Navatar or Investor Administrator.";
	public static String DocumentNotFoundErrorMsg="Document not found.";
}

