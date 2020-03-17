/**
 * 
 */
package com.navatar.pageObjects;

import static com.navatar.generic.CommonLib.isDisplayed;
import static com.navatar.generic.CommonLib.FindElements;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.navatar.generic.CommonLib;
import com.navatar.generic.CommonLib.action;

/**
 * @author Parul Singh
 *
 */
public class NavatarInvestorAddOnsPage extends BasePageBusinessLayer {
	
	public NavatarInvestorAddOnsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//iframe[@id='NavatarInvestorAddOns']")
	private WebElement navatarInvestorAddOnFrame;
	/**
	 * @return the navatarInvestorAddOnFrame
	 */
	public WebElement getNavatarInvestorAddOnFrame(int timeOut) {
		return isDisplayed(driver, navatarInvestorAddOnFrame, "Visibility", timeOut, "Navatar Investor Add On Frame");
	}

	@FindBy(xpath="//div[@id='yesdata']//h1")
	private WebElement errorMessageBeforeAdminRegistration;

	/**
	 * @return the errorMessageBeforeAdminRegistration
	 */
	public WebElement getErrorMessageBeforeAdminRegistration(int timeOut) {
		return isDisplayed(driver, errorMessageBeforeAdminRegistration, "Visibility", timeOut, "Error Message Before Admin Registration");
	} 
	
	@FindBy(xpath="//a[@title='Disclaimer']")
	private WebElement disclaimerLabel;
	/**
	 * @return the disclaimerLabel
	 */
	public WebElement getDisclaimerLabel(int timeOut) {
		return isDisplayed(driver, disclaimerLabel, "Visibility", timeOut, "Disclaimer Label");
	}
	
	@FindBy(xpath="//iframe[@id='PEDisclaimersSetup']")
	private WebElement navatarInvestorAddOnFrame1;
	/**
	 * @return the navatarInvestorAddOnFrame1
	 */
	public WebElement getNavatarInvestorAddOnFrame1(int timeOut) {
		return isDisplayed(driver, navatarInvestorAddOnFrame1, "Visibility", timeOut, "Navatar investor add on frame 1");
	}
	
	@FindBy(xpath="//a[@title='Disclaimer']/..")
	private WebElement disclaimerTab;


	/**
	 * @return the disclaimerTab
	 */
	public WebElement getDisclaimerTab(int timeOut) {
		return isDisplayed(driver, disclaimerTab, "Visibility", timeOut, "Disclaimer Tab");
	}
	
	@FindBy(xpath="//*[@class='bodyDiv brdPalette brandPrimaryBrd']//a[contains(@href,'javascript:openPopupFocusEscapePounds')]/..")
	private WebElement insufficientPrivilegeErrorMessage;
	
	/**
	 * @return the insufficientPrivilegeErrorMessage
	 */
	public WebElement getInsufficientPrivilegeErrorMessage(int timeOut) {
		return isDisplayed(driver, insufficientPrivilegeErrorMessage, "Visibility", timeOut, "Insufficient Previlege Error Msg");
	}
	
	@FindBy(xpath="//div[@id='nodata']/h1")
	private WebElement NIFunctionlityDisabledErrorMessage;
	/**
	 * @return the nIFunctionlityDisabledErrorMessage
	 */
	public WebElement getNIFunctionlityDisabledErrorMessage(int timeOut) {
		return isDisplayed(driver, NIFunctionlityDisabledErrorMessage, "Visibility", timeOut, "NI Functionality Disabled Error Mesage.");
	}
	
	@FindBy(xpath="//td[contains(text(),'Name')]/../../../../preceding-sibling::div[2]")
	private WebElement headerName;
	/**
	 * @return the headerName
	 */
	public WebElement getHeaderName(int timeOut) {
		return isDisplayed(driver, headerName, "Visibility", timeOut, "Header name");
	}
	
	@FindBy(xpath="//a[@title='New Disclaimer']")
	private WebElement newDisclaimerButton;
	/**
	 * @return the newDisclaimerButton
	 */
	public WebElement getNewDisclaimerButton(int timeOut) {
		return isDisplayed(driver, newDisclaimerButton, "Visibility", timeOut, "New disclaimer button");
	}
	
	@FindBy(xpath="//td[contains(text(),'Name:')]/following-sibling::td/input")
	private WebElement newDisclaimerNameTextBox;
	/**
	 * @return the newDisclaimerNameTextBox
	 */
	public WebElement getNewDisclaimerNameTextBox(int timeOut) {
		return isDisplayed(driver, newDisclaimerNameTextBox, "Visibility", timeOut, "New disclaimer text box");
	}
	//body[contains(@id,'Disclaimer:disclaimerNewDescpFld')]
	
	@FindBy(xpath="//body[contains(@id,'Disclaimer:disclaimerNewDescpFld')]")
	private WebElement disclaimerDescriptionTextArea;
	/**
	 * @return the disclaimerDescriptionTextArea
	 */
	public WebElement getDisclaimerDescriptionTextArea(int timeOut) {
		if(CommonLib.switchToFrame(driver, 30, CommonLib.FindElement(driver, "//iframe[@class='cke_wysiwyg_frame cke_reset']", "", action.BOOLEAN, timeOut))){
			System.err.println("Switched to frame of description text box");
		}
		return isDisplayed(driver, disclaimerDescriptionTextArea, "Visibility", timeOut, "New disclaimer description text area");
	}
	
	@FindBy(xpath="//div[contains(text(),'Add New Disclaimer')]/a")
	private WebElement newDislaimerPopCrossIcon;
	/**
	 * @return the newDislaimerPopCrossIcon
	 */
	public WebElement getNewDislaimerPopCrossIcon(int timeOut) {
		return isDisplayed(driver, newDislaimerPopCrossIcon, "Visibility", timeOut, "New disclaimer Pop Up Cross Icon");
	}
	
	@FindBy(xpath="//div[contains(text(),'Add New Disclaimer')]/following-sibling::div//input[@title='Cancel']")
	private WebElement newDisclaimerCancelButton;
	/**
	 * @return the newDisclaimerCancelButton
	 */
	public WebElement getNewDisclaimerCancelButton(int timeOut) {
		return isDisplayed(driver, newDisclaimerCancelButton, "Visibility", timeOut, "");
	}
	
	@FindBy(xpath="//div[contains(text(),'Add New Disclaimer')]/following-sibling::div//input[@title='Save']")
	private WebElement newDiscalimerPopUpSaveButton;
	/**
	 * @return the newDiscalimerPopUpSaveButton
	 */
	public WebElement getNewDiscalimerPopUpSaveButton(int timeOut) {
		return isDisplayed(driver, newDiscalimerPopUpSaveButton, "Visibility", timeOut, "New disclaimerPop Up Save Button");
	}
	
	@FindBy(xpath="//div[@class='Add_New_Disclaimer FancyboxContainer ui-draggable']/div[@class='head_popup']")
	private WebElement newDisclaimerPopUpHeader;
	/**
	 * @return the newDisclaimerPopUpHeader
	 */
	public WebElement getNewDisclaimerPopUpHeader(int timeOut) {
		return isDisplayed(driver, newDisclaimerPopUpHeader, "Visibility", timeOut, "New disclaiamer Pop Up Header");
	}
	
	@FindBy(xpath="//span[@id='NameErrorId']")
	private WebElement newDisclaimerPopUpNameTextBoxErrorMsg;
	/**
	 * @return the newDisclaimerPopUpNameTextBoxErrorMsg
	 */
	public WebElement getNewDisclaimerPopUpNameTextBoxErrorMsg(int timeOut) {
		return isDisplayed(driver, newDisclaimerPopUpNameTextBoxErrorMsg, "Visibility", timeOut, "New disclaimer Pop Error Message");
	}
	
	@FindBy(xpath="//span[@id='descpErrorId']")
	private WebElement newDisclaimerPopUpDescriptionBoxErrorMsg;
	/**
	 * @return the newDisclaimerPopUpDescriptionBoxErrorMsg
	 */
	public WebElement getNewDisclaimerPopUpDescriptionBoxErrorMsg(int timeOut) {
		return isDisplayed(driver, newDisclaimerPopUpDescriptionBoxErrorMsg, "Visibility", timeOut, "New disclaimer Pop Error Message");
	}
	
	@FindBy(xpath="//div[@class='head_popup'][contains(text(),'Duplicate Error')]/following-sibling::div/p")
	private WebElement newDisclaimerDuplicateDisclaimerPopErrorMsg;
	/**
	 * @return the newDisclaimerDuplicateDisclaimerPopErrorMsg
	 */
	public WebElement getNewDisclaimerDuplicateDisclaimerPopErrorMsg(int timeOut) {
		return isDisplayed(driver, newDisclaimerDuplicateDisclaimerPopErrorMsg, "Visibility", timeOut, "Duplicate disclaimer error msg");
	}
	
	@FindBy(xpath="//div[@class='head_popup'][contains(text(),'Duplicate Error')]/following-sibling::div/a")
	private WebElement newDiscalaimerDuplicateErrorMsgOkbutton;
	/**
	 * @return the newDiscalaimerDuplicateErrorMsgOkbutton
	 */
	public WebElement getNewDiscalaimerDuplicateErrorMsgOkbutton(int timeOut) {
		return isDisplayed(driver, newDiscalaimerDuplicateErrorMsgOkbutton, "Visibility", timeOut, "Duplicate error message OK Button");
	}
	
	@FindBy(xpath="//span[@id='disclaimerCountSpan']")
	private WebElement disclaimerRecordCount;
	/**
	 * @return the disclaimerrecordCount
	 */
	public WebElement getDisclaimerrecordCount(int timeOut) {
		return isDisplayed(driver, disclaimerRecordCount, "Visibility", timeOut, "Record Count");
	}
	
	@FindBy(xpath="//div[@class='head_popup'][contains(text(),'Duplicate Error')]/span")
	private WebElement duplicateDisclaimerErrorMsgCrossIcon;
	/**
	 * @return the duplicateDisclaimerErrorMsgCrossIcon
	 */
	public WebElement getDuplicateDisclaimerErrorMsgCrossIcon(int timeOut) {
		return isDisplayed(driver, duplicateDisclaimerErrorMsgCrossIcon, "Visibility", timeOut, "Cross icon");
	}
	
	@FindBy(xpath="//div[@id='disclaimerNameDivId']/following-sibling::a")
	private WebElement disclaimerPopUpCrossIcon;
	/**
	 * @return the disclaimerPopUpCrossIcon
	 */
	public WebElement getDisclaimerPopUpCrossIcon(int timeOut) {
		return isDisplayed(driver, disclaimerPopUpCrossIcon, "Visibility", timeOut, "Disclaimer pop up cross icon");
	}
	
	@FindBy(xpath="//div[@id='disclaimerNameDivId']/../following-sibling::div[2]/a")
	private WebElement disclaimerPopUpCloseButton;
	/**
	 * @return the disclaimerPopUpCloseButton
	 */
	public WebElement getDisclaimerPopUpCloseButton(int timeOut) {
		return isDisplayed(driver, disclaimerPopUpCloseButton, "Visibility", timeOut, "Disclaimer pop up close button");
	}
	
	@FindBy(xpath="//span[@id='discStatsFundName']/..")
	private WebElement disclaimerViewPopUpHeaderName;
	/**
	 * @return the disclaimerViewPopUpHeaderName
	 */
	public WebElement getDisclaimerViewPopUpHeaderName(int timeOut) {
		return isDisplayed(driver, disclaimerViewPopUpHeaderName, "Visibility", timeOut, "Disclaimer View Pop Up Header");
	}
	
	@FindBy(xpath="//select[@id='selectDiscliamerList']")
	private WebElement disclaimerNamePickList;
	/**
	 * @return the disclaimerNamePickList
	 */
	public WebElement getDisclaimerNamePickList(int timeOut) {
		return isDisplayed(driver, disclaimerNamePickList, "Visibility", timeOut, "Disclaimer Name pick list");
	}
	
	@FindBy(xpath="//div[@class='SearchSectionFinal']//input")
	private WebElement disclaimerViewPopUpSearchBox;
	/**
	 * @return the disclaimerViewPopUpSearchBox
	 */
	public WebElement getDisclaimerViewPopUpSearchBox(int timeOut) {
		return isDisplayed(driver, disclaimerViewPopUpSearchBox, "Visibility", timeOut, "Search box");
	}
	
	
	@FindBy(xpath="//div[@class='SearchSectionFinal']//a[@class='icon_btn_search']")
	private WebElement disclaimerViewPopupSearchIcon;
	
	/**
	 * @return the disclaimerViewPopupSearchIcon
	 */
	public WebElement getDisclaimerViewPopupSearchIcon(int timeOut) {
		return isDisplayed(driver, disclaimerViewPopupSearchIcon, "Visibility", timeOut, "Search Icon");
	}

	
	@FindBy(xpath="//div[@class='SearchSectionFinal']//a[@class='icon_clear_search_active']")
	private WebElement disclaimerViewPopupSearchCrossIcon;
	/**
	 * @return the disclaimerViewPopupSearchCrossIcon
	 */
	public WebElement getDisclaimerViewPopupSearchCrossIcon(int timeOut) {
		return isDisplayed(driver, disclaimerViewPopupSearchCrossIcon, "Visibility", timeOut, "Cross Icon");
	}
	
	@FindBy(xpath="//select[@id='discStatsSelectLst']")
	private WebElement disclaimerViewPopUpStatusPickList;
	/**
	 * @return the disclaimerViewPopUpStatusPickList
	 */
	public WebElement getDisclaimerViewPopUpStatusPickList(int timeOut) {
		return isDisplayed(driver, disclaimerViewPopUpStatusPickList, "Visibility", timeOut, "Status pick list");
	}
	
	@FindBy(xpath="//span[@id='disclaimerStatCountSpan']")
	private WebElement disclaimerViewPopUpRecordLabel;
	/**
	 * @return the disclaimerViewPopUpRecordLabel
	 */
	public WebElement getDisclaimerViewPopUpRecordLabel(int timeOut) {
		return isDisplayed(driver, disclaimerViewPopUpRecordLabel, "Visibility", timeOut, "Record Label");
	}
	
	@FindBy(xpath="//span[@id='disclaimerStatCountSpan']/span")
	private WebElement disclaimerViewPopUpRecordCount;
	/**
	 * @return the disclaimerViewPopUpRecordCount
	 */
	public WebElement getDisclaimerViewPopUpRecordCount(int timeOut) {
		return isDisplayed(driver, disclaimerViewPopUpRecordCount, "Visibility", timeOut, "Record Count");
	}
	
	@FindBy(xpath="//span[@id='clickHereSpan']")
	private WebElement disclaimerViewPopUpClickHereText;
	/**
	 * @return the disclaimerViewPopUpClickHereText
	 */
	public WebElement getDisclaimerViewPopUpClickHereText(int timeOut) {
		return isDisplayed(driver, disclaimerViewPopUpClickHereText, "Visibility", timeOut, "click here text");
	}
	
	@FindBy(xpath="//span[@id='clickHereSpan']/a")
	private WebElement disclaimerViewPopUpClickHereLink;
	/**
	 * @return the disclaimerViewPopUpClickHereLink
	 */
	public WebElement getDisclaimerViewPopUpClickHereLink(int timeOut) {
		return isDisplayed(driver, disclaimerViewPopUpClickHereLink, "Visibility", timeOut, "click here link");
	}
	
	@FindBy(xpath="//span[@id='discStatsFundName']/../following-sibling::a")
	private WebElement disclaimerViewPopUpCrossIcon;
	/**
	 * @return the disclaimerViewPopUpCrossIcon
	 */
	public WebElement getDisclaimerViewPopUpCrossIcon(int timeOut) {
		return isDisplayed(driver, disclaimerViewPopUpCrossIcon, "Visibility", timeOut, "");
	}
	
	@FindBy(xpath="//div[@class='head_popup'][contains(text(),'Confirm Disclaimer Activation')]")
	private WebElement disclaimerActivationPopUpHeader;
	/**
	 * @return the disclaimerActivationPopUp
	 */
	public WebElement getDisclaimerActivationPopUp(int timeOut) {
		return isDisplayed(driver, disclaimerActivationPopUpHeader, "Visibility", timeOut, "Disclaimer Activation Pop Up header");
	}
	
	@FindBy(xpath="//div[@class='head_popup'][contains(text(),'Confirm Disclaimer Activation')]/a")
	private WebElement disclaimerActivationPopUpCrossIcon;
	/**
	 * @return the disclaimerActivationPopUpCrossIcon
	 */
	public WebElement getDisclaimerActivationPopUpCrossIcon(int timeOut) {
		return isDisplayed(driver, disclaimerActivationPopUpCrossIcon, "Visibility", timeOut, "Activate pop up cross icon");
	}
	
	@FindBy(xpath="//div[@class='head_popup'][contains(text(),'Confirm Disclaimer Activation')]/following-sibling::div[1]")
	private WebElement disclaimerActivationPopUpMessage;
	/**
	 * @return the disclaimerActivationPopUpMessage
	 */
	public WebElement getDisclaimerActivationPopUpMessage(int timeOut) {
		return isDisplayed(driver, disclaimerActivationPopUpMessage, "Visibility", timeOut, "Disclaimer actication pop up message");
	}
	
	@FindBy(xpath="//div[@class='head_popup'][contains(text(),'Confirm Disclaimer Activation')]/following-sibling::div[2]/input[@title='Yes']")
	private WebElement disclaimerActivationPopUpYesButton;
	/**
	 * @return the disclaimerActivationPopUpYesButton
	 */
	public WebElement getDisclaimerActivationPopUpYesButton(int timeOut) {
		return isDisplayed(driver, disclaimerActivationPopUpYesButton, "Visibility", timeOut, "Disclaimer Activation pop up Yes button");
	}
	
	@FindBy(xpath="//div[@class='head_popup'][contains(text(),'Confirm Disclaimer Activation')]/following-sibling::div[2]/input[@title='No']")
	private WebElement disclaimerActivationPopUpNoButton;
	/**
	 * @return the disclaimerActivationPopUpNoButton
	 */
	public WebElement getDisclaimerActivationPopUpNoButton(int timeOut) {
		return isDisplayed(driver, disclaimerActivationPopUpNoButton, "Visibility", timeOut, "Disclaimer activation pop up No button");
	}
	
	@FindBy(xpath="//span[contains(@id,'Disclaimer:disclaimerGridBottomP')]//td[3]/div/span")
	private WebElement waitingDisclaimerCount;
	/**
	 * @return the waitingDisclaimerCount
	 */
	public WebElement getWaitingDisclaimerCount(int timeOut) {
		return isDisplayed(driver, waitingDisclaimerCount, "Visibility", timeOut, "Waiting discalimer count");
	}
	
	@FindBy(xpath="//span[contains(@id,'Disclaimer:disclaimerGridBottomP')]//td[2]/div/span")
	private WebElement acceptedDisclaimerCount;
	/**
	 * @return the acceptedDisclaimerCount
	 */
	public WebElement getAcceptedDisclaimerCount(int timeOut) {
		return isDisplayed(driver, acceptedDisclaimerCount, "Visibility", timeOut, "Acceceted Disclaimer Count");
	}
	
	@FindBy(xpath="//span[contains(@id,'Disclaimer:disclaimerGridBottomP')]//td[1]/div/span")
	private WebElement activeDisclaimerStatisticValue;
	/**
	 * @return the activeDisclaimerStatisticValue
	 */
	public WebElement getActiveDisclaimerStatisticValue(int timeOut) {
		return isDisplayed(driver, activeDisclaimerStatisticValue, "Visibility", timeOut, "Active disclaimer statistics value");
	}
	
	@FindBy(xpath="//strong[text()='Status:']/following-sibling::b")
	private WebElement currentStatusOfDisclaimer;
	/**
	 * @return the getCurrentStatusOfDisclaimer
	 */
	public WebElement getCurrentStatusOfDisclaimer(int timeOut) {
		return isDisplayed(driver, currentStatusOfDisclaimer, "Visibility", timeOut, "Current status");
	}
	
	@FindBy(xpath="//span[@id='Disclaimer_Statistics-rows']/span[contains(@id,'Disclaimer_Statistics-row-')]")
	private List<WebElement> contactsInVeiwPopUpGrid;
	/**
	 * @return the contactsInVeiwPopUpGrid
	 */
	public List<WebElement> getContactsInVeiwPopUpGrid(int timeOut) {
		if(CommonLib.checkElementsVisibility(driver, contactsInVeiwPopUpGrid, "Contacts in view grid", timeOut)){
			return contactsInVeiwPopUpGrid;
		} else {
			return null;
		}
	}
	
	@FindBy(xpath="//strong[text()='Select Fund:']/following-sibling::select[@id='selectFundList']")
	private WebElement selectFundDropDown;
	/**
	 * @return the selectFundDropDown
	 */
	public WebElement getSelectFundDropDown(int timeOut) {
		return isDisplayed(driver, selectFundDropDown, "Visibility", timeOut, "Select Fund drop down");
	}
	
	
	@FindBy(xpath="//a[contains(text(),'Deactivate')]")
	private WebElement deactivateFundBtn;
	/**
	 * 
	 * @return click on fund deactivate button
	 */
	public WebElement getDeactivateFundButton(int timeOut) {
		return isDisplayed(driver, deactivateFundBtn, "Visibility", timeOut, "Deactivate fund");
	}
	
	
	@FindBy(xpath = "//div[contains(text(),'Confirm Disclaimer Deactivation')]")
	private WebElement confirmDisclaimerHeading;
	/**
	 * @return the confirmDisclaimerHeading
	 */
	public WebElement getConfirmDisclaimerHeading(int timeOut) {
		return isDisplayed(driver, confirmDisclaimerHeading, "Visibility", timeOut, "confirm disclaimer deactivation popup heading");
	}
	
	
	@FindBy(xpath="//div[contains(text(),'Confirm Disclaimer Deactivation')]/a")
	private WebElement disclaimerDeactivationCrossBtn;
	/**
	 * 
	 * @return click on Disclaimer Deactivation CrossButton
	 */
	public WebElement getDisclaimerDeactivationCrossBtn(int timeOut) {
		return isDisplayed(driver, disclaimerDeactivationCrossBtn, "Visibility", timeOut, "Click on Disclaimer Deactivation CrossBtn");
	}
	
	@FindBy(xpath="//div[@class='Confirm_Disclaimer_Deactivation FancyboxContainer ui-draggable']/div/input[@title='No']")
	private WebElement disclaimerDeactivationNoBtn;
	/**
	 * 
	 * @return click on Disclaimer Deactivation No Button
	 */
	public WebElement getDisclaimerDeactivationNoBtn(int timeOut) {
		return isDisplayed(driver, disclaimerDeactivationNoBtn, "Visibility", timeOut, "Click on Disclaimer Deactivation No Btn");
	}
	
	@FindBy(xpath="//div[@class='Confirm_Disclaimer_Deactivation FancyboxContainer ui-draggable']/div/input[@title='Yes']")
	private WebElement disclaimerDeactivationYesBtn;
	/**
	 * 
	 * @return click on Disclaimer Deactivation Yes Button
	 */
	public WebElement getDisclaimerDeactivationYesBtn(int timeOut) {
		return isDisplayed(driver, disclaimerDeactivationYesBtn, "Visibility", timeOut, "Click on Disclaimer Deactivation Yes Btn");
	}

	@FindBy (xpath="//span[@id='disclaimerCountSpan']")
	private WebElement disclaimeRecordCount;
	/**
	 * @return the disclaimeRecordCount
	 */
	public WebElement getDisclaimeRecordCount(int timeOut) {
		return isDisplayed(driver, disclaimeRecordCount, "Visibility", timeOut, "disclaimer record count");
	}


	/**
	 * @return the ConfirmDisclaimerDeactivationPopupMsg
	 */
 public List<WebElement> getConfirmDisclaimerDeactivationPopupMsg()
 {
	return FindElements(driver, "//div[contains(text(),'Confirm Disclaimer Deactivation')]/following-sibling::div[@class='formbox contacts_n_name_div']/p", "ConfirmDisclaimerDeactivationPopupMsg");	 
 }
	

 public List<WebElement> getDisclaimerStatisticsContactName()
 {
	 return FindElements(driver, "//span[contains(@id,'Disclaimer_Statistics-cell-0')]/a", "Contact Name");
 }
	
 public List<WebElement> getDisclaimerStatisticsEmail()
 {
	 return FindElements(driver, "//span[contains(@id,'Disclaimer_Statistics-cell-1')]/a", "Email");
 }
 public List<WebElement> getDisclaimerStatisticsFirm()
 {
	 return FindElements(driver, "//span[contains(@id,'Disclaimer_Statistics-cell-2')]/a", "Firm");
 }
 public List<WebElement> getDisclaimerStatisticsStatus()
 {
	 return FindElements(driver, "//span[contains(@id,'Disclaimer_Statistics-cell-3')]/img", "Status");
 }
 public List<WebElement> getDisclaimerStatisticsAcceptedOn()
 {
	 return FindElements(driver, "//span[contains(@id,'Disclaimer_Statistics-cell-4')]/span", "AcceptedOn");
 }
 
 @FindBy(xpath="//span[text()='Disclaimer']")
 private WebElement disclaimerPageHeader;
/**
 * @return the disclaimerPageheader
 */
public WebElement getDisclaimerPageHeader(int timeOut) {
	return isDisplayed(driver, disclaimerPageHeader, "Visibility", timeOut, "Discalimer section header");
}
 	
	
}
