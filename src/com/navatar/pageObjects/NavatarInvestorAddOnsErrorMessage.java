/**
 * 
 */
package com.navatar.pageObjects;

/**
 * @author Parul Singh
 *
 */
public interface NavatarInvestorAddOnsErrorMessage{

	public String errorMessageBeforeAdminRegistration="Please register for Navatar Investor from the Navatar Investor Manager tab in order to view this information.";
	public String insufficientPrivilegeErrorMessage = "You do not have the level of access necessary to perform the operation you requested. Please contact the owner of the record or your administrator if access is necessary. For more information, see Insufficient Privileges Errors.";
	public String NIFunctionalityDisabledErrorMessage = "The Navatar Investor functionality has not been enabled for your login. Please contact your System Administrator.";
	public String accessToCreateWorkspaceErrorMsg="Congratulations! Your Administrator has provided you access to create Workspaces. Please click on the Navatar Investor Manager tab to complete your registration.";
	public String NoDataToDisplayErrorMessage = "No data to display.";
	public String PleaseEnterValueErrorMsg = "Please enter a value.";
	public String DuplicateDisclaimerErrorMsg = "Disclaimer name already exists.";
	public String DisclaimerActivationPopUpMsg1 = "Please note that activating the disclaimer will result in the following:";
	public String DisclaimerActivationPopUpMsg2 = "All investors will be required to accept the disclaimer before accessing documents for this fund.";
	public String DisclaimerActivationPopUpMsg3 = "Activating this disclaimer will deactivate any existing disclaimer for this fund.";
	public String DisclaimerActivationPopUpMsg4 = "All existing statistics for this disclaimer will be reset.";
	public String DisclaimerActivationPopUpMsg5 = "Are you sure you want to proceed?";
}
