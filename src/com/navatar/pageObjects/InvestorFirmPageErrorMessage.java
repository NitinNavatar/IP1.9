/**
 * 
 */
package com.navatar.pageObjects;

/**
 * @author Ankit Jaiswal
 *
 */
public interface InvestorFirmPageErrorMessage  {
	public String NodatatoDisplayErrorMsg="No data to display.";
	public String noDocumentPresentInFolder = "No document(s) are present in this folder.";
	public String alldocumentNodatatodisplayErrorMsg="No document(s) to display.";
	public String DocumentUploadSuccessMsg = "Document uploaded successfully.";
	public String DocumentUploadUpdateSuccessMsg = "The document you're attempting to add already exists. Please choose how you'd like the system to handle this document.";
	public String pleaseSelectAdocumentToUploadMsg = "Please select a document to upload.";
	public String docAlreadyUploadedByOtherMsg = "A document with this name has already been uploaded by others. Please change the document name to be able to successfully upload the document.";
	public String pleaseEnterSomeTextMsg="Please enter some text.";
	public String documentNotFoundMsg="Document not found.";
	public static String LimitedAccessErrorMessageBulkDownload="You cannot download this folder as you do not have download permissions. Please contact the firm that provided you access.";
	public static String BulkDownloadConfirmationMessage1="Please confirm before starting bulk download:";
	public static String BulkDownloadConfirmationMessage2="Download window must be open for the length of the download process";
	public static String BulkDownloadConfirmationMessage3="Documents will be downloaded to a ZIP file on your local machine";
	public static String BulkDownloadConfirmationMessage4="If the download window is closed during download process, you will lose all progress";
	public static String BulkDownloadConfirmationMessage5="Are you sure you want to proceed?";
}
