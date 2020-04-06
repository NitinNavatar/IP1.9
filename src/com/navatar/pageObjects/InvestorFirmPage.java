/**
 * 
 */
package com.navatar.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.navatar.generic.BaseLib;
import com.navatar.generic.CommonLib.Workspace;
import com.navatar.generic.CommonLib.action;

import static com.navatar.generic.CommonLib.*;

import java.util.List;

/**
 * @author Akul Bhutani
 *
 */
public class InvestorFirmPage extends BasePageBusinessLayer {
	public InvestorFirmPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/*******************************
	 * investor firm
	 ******************************/

	/**
	 * @return the homeTab
	 */
	public List<WebElement> gethomeTab(int timeOut) {
		return FindElements(driver, "//a[@title='Home']", "home tab");
	}

	public WebElement getHomeTab(int timeOut) {
		WebElement ele = null;
		List<WebElement> lst = gethomeTab(timeOut);
		if (!lst.isEmpty()) {
			for (int i = 0; i < lst.size(); i++) {
				if (isDisplayed(driver, lst.get(i), "visibility", timeOut, "Home tab") != null) {
					ele = lst.get(i);
					break;
				}
			}
		}
		return ele;
	}

	@FindBy(xpath = "//*[@id='divwidth1_new']/li[2]/a[@title='Investments']")
	private WebElement Investments;

	/**
	 * @return the investments
	 */
	public List<WebElement> getInvestmentsTab() {
		return FindElements(driver, "//a[@title='Investments']", "investment tab");
	}

	public WebElement getInvestmentsTab(int timeOut) {
		WebElement ele = null;
		List<WebElement> lst = getInvestmentsTab();
		if (!lst.isEmpty()) {
			for (int i = 0; i < lst.size(); i++) {
				if (isDisplayed(driver, lst.get(i), "visibility", timeOut, "investment tab") != null) {
					ele = lst.get(i);
					break;
				}
			}
		}

		return ele;
	}

	@FindBy(xpath = "//a[contains(@class,'SearchBasedOnPastDeal_tab') or text()='Recent Activities']")
	private WebElement recentActivitiesTab;

	/**
	 * @return the recentActivities
	 */
	public WebElement getRecentActivitiesTab(int timeOut) {
		return isDisplayed(driver, recentActivitiesTab, "Visibility", timeOut, "Recent Activites tab");
	}

	@FindBy(xpath = "//*[@id='SearchBasedOnAccountAndContacts_tab']")
	private WebElement allDocumentsTab;

	/**
	 * @return the allDocumentsTab
	 */
	public WebElement getAllDocumentsTab(int timeOut) {
		return isDisplayed(driver, allDocumentsTab, "Visibility", timeOut, "All Documents Tab");
	}

	/**
	 * @return the currentInvestments
	 */
	public List<WebElement> getCurrentInvestments() {
		return FindElements(driver, "//*[contains(@id,'NavatarInvestor_CurrentInvestments')]/a",
				"current investment tab");
	}

	public WebElement getCurrentInvestmentTab(int timeOut) {
		WebElement ele = null;
		List<WebElement> lst = getCurrentInvestments();
		if (!lst.isEmpty()) {
			for (int i = 0; i < lst.size(); i++) {
				if (isDisplayed(driver, lst.get(i), "visibility", timeOut, "current investment tab") != null) {
					ele = lst.get(i);
					break;
				}
			}
		}

		return ele;
	}

	/**
	 * @return the potentialInvestments
	 */
	public List<WebElement> getPotentialInvestments() {
		return FindElements(driver, "//*[contains(@id,'NavatarInvestor_PotentialInvestments')]/a",
				"potential investment tab");
	}

	public WebElement getPotentialInvestmentTab(int timeOut) {
		WebElement ele = null;
		List<WebElement> lst = getPotentialInvestments();
		if (!lst.isEmpty()) {
			for (int i = 0; i < lst.size(); i++) {
				if (isDisplayed(driver, lst.get(i), "visibility", timeOut, "potential investment tab") != null) {
					ele = lst.get(i);
					break;
				}
			}
		}
		return ele;
	}

/*******************************all document********************/

	public List<WebElement> getAllDocumentFileNameList() {
		return FindElements(driver,
				"//span[@id='myGrid_firmAllDoc-box']//span[contains(@class,'aw-rows-normal')]/span[2]/a",
				"all documents file name list");
	}

	public List<WebElement> getAllDocumentInvestmentNameList() {
		return FindElements(driver,
				"//span[@id='myGrid_firmAllDoc-box']//span[contains(@class,'aw-rows-normal')]/span[3]/a",
				"all documents investment name list");
	}

	public List<WebElement> getAllDocumentWrokSpaceList() {
		return FindElements(driver,
				"//span[@id='myGrid_firmAllDoc-box']//span[contains(@class,'aw-rows-normal')]/span[4]",
				"all documents work space name list");
	}

	public List<WebElement> getAllDocumentHeaderTextList() {
		return FindElements(driver, "//span[contains(@id,'myGrid_firmAllDoc-header-')]/span[3]",
				"all documents header list text");
	}

	@FindBy(xpath = "//span[@id='myGrid_firmAllDoc-box']//span[contains(@class,'aw-rows-normal')]/span[2]/span")
	private WebElement alldocumentsNoDataToDisplayErrorMsg;

	/**
	 * @return the alldocumentsNoDataToDisplayErrorMsg
	 */
	public WebElement getAlldocumentsNoDataToDisplayErrorMsg(int timeOut) {
		return isDisplayed(driver, alldocumentsNoDataToDisplayErrorMsg, "Visibility", timeOut,
				"all document no data to display error message");
	}

	@FindBy(xpath = "//input[@name='page:srch:searchcon_grid2_dealroom_target']")
	private WebElement allDocumentSearchTextBox;

	/**
	 * @return the allDocumentSearchTextBox
	 */
	public WebElement getAllDocumentSearchTextBox(int timeOut) {
		return isDisplayed(driver, allDocumentSearchTextBox, "Visibility", timeOut, "all document search text box");
	}

	@FindBy(xpath = "//a[@title='Search']")
	private WebElement alldocumentSearchBtn;

	/**
	 * @return the alldocumentSearchBtn
	 */
	public WebElement getAlldocumentSearchBtn(int timeOut) {
		return isDisplayed(driver, alldocumentSearchBtn, "Visibility", timeOut, "all document search button");
	}
	

	@FindBy(xpath = "//div[@id='recordscount_firmAllDoc']")
	private WebElement allDocumentCount;

	/**
	 * @return the allDocumentCount
	 */
	public WebElement getAllDocumentCount(int timeOut) {
		return isDisplayed(driver, allDocumentCount, "Visibility", timeOut, "all document count text");
	}
	
	public List<WebElement> getAllDocumentHeaderTextSortingIcon(){
		return FindElements(driver, "//span[contains(@id,'myGrid_firmAllDoc-header-') and contains(@class,'aw-grid-header')]//span[3]/span", "all document sorting icon");
	}
	
	public List<WebElement> getallDocumentSearchedFileNameList(){
		return FindElements(driver, "//div[@id='search-result-id']//td/a[2]/b", "all document searched file name list");
	}
	
	@FindBy(xpath="//div[@id='SearchResultsFor_Fancybox-id']//input")
	private WebElement alldocumentsearchedPopUpCloseBtn;
	/**
	 * @return the alldocumentsearchedPopUpCloseBtn
	 */
	public WebElement getAlldocumentsearchedPopUpCloseBtn(int timeOut) {
		return isDisplayed(driver, alldocumentsearchedPopUpCloseBtn, "Visibility", timeOut, "all document searched pop up close button");
	}
	
	@FindBy(xpath="//a[@id='closelink']")
	private WebElement documentCloseBtn;

	/**
	 * @return the documentCloseBtn
	 */
	public WebElement getDocumentCloseBtn(int timeOut) {
		return isDisplayed(driver, documentCloseBtn, "Visibility", timeOut, "document close button");
	}
	@FindBy(xpath="//div[@id='btndownloadClose']/a[1]")
	private WebElement documentDownloadBtn;

	/**
	 * @return the documentDownloadBtn
	 */
	public WebElement getDocumentDownloadBtn(int timeOut) {
		return isDisplayed(driver, documentDownloadBtn, "Visibility", timeOut, "document download button");
	}
	
	@FindBy(xpath="//div[@id='SearchResultsFor_Fancybox-id']//a[@title='Close']")
	private WebElement allDocumentSearchedPopUpCrossBtn;

	/**
	 * @return the allDocumentSearchedPopUpCrossBtn
	 */
	public WebElement getAllDocumentSearchedPopUpCrossBtn(int timeOut) {
		return isDisplayed(driver, allDocumentSearchedPopUpCrossBtn, "Visibility", timeOut, "all document searched pop up cross icon");
	}

	/************************************************** recent activity********************/

	public List<WebElement> getRecentActivitiesFileNameList() {
		return FindElements(driver, "//span[@id='myGrid']//span[contains(@class,'aw-rows-normal')]/span[2]/a",
				"recent activities file name list");
	}

	public List<WebElement> getRecentActivitiestInvestmentNameList() {
		return FindElements(driver,
				"//span[@id='myGrid']//span[contains(@class,'aw-rows-normal')]/span[2]/a/../../span[3]/a",
				"recent activities investment name list");
	}

	public List<WebElement> getRecentActivitiesWorkSpaceList() {
		return FindElements(driver,
				"//span[@id='myGrid']//span[contains(@class,'aw-rows-normal')]/span[2]/a/../../span[4]",
				"recent document work space list");
	}

	public List<WebElement> getRecentActivitiesActivityTypeList() {
		return FindElements(driver,
				"//span[@id='myGrid']//span[contains(@class,'aw-rows-normal')]/span[2]/a/../../span[6]",
				"recent document Activity type list");
	}

	public List<WebElement> getRecentActivitiesContactFirmUpdateList() {
		return FindElements(driver, "//span[@id='myGrid']//span[contains(@class,'aw-rows-normal')]/span[6]/a",
				"recent document contact and firm update list");
	}

	public List<WebElement> getRecentActivitiesAlertHeaderTextList() {
		return FindElements(driver, "//span[contains(@id,'myGrid-header-')]/span[3]",
				"recent activity alert header list");
	}
	
	@FindBy(xpath = "//a[text()='Recent Activities']/../../../..//span[text()='Activity Created On']")
	private WebElement activityCreatedOnLabel;

	/**
	 * @return the activityCreatedOnLabel
	 */
	public WebElement getActivitiesCreatedOnLabel(int timeOut) {
		return isDisplayed(driver, activityCreatedOnLabel, "Visibility", timeOut,
				"Activity created on text, column name");
	}

	@FindBy(xpath = "//span[contains(@id,'myGrid-cell-')]/span")
	private WebElement recentActivityAlertNoDataToDisplayErrorMsg;

	/**
	 * @return the recentActivityAlertNoDataToDisplayErrorMsg
	 */
	public WebElement getRecentActivitiesAlertNoDataToDisplayErrorMsg(int timeOut) {
		return isDisplayed(driver, recentActivityAlertNoDataToDisplayErrorMsg, "Visibility", timeOut,
				"recent activity alert no data to display error message");
	}

	@FindBy(xpath = "(//span[contains(@id,'myGrid-header-')]/span[3])[4]/span")
	private WebElement recentActivityActivityCreatedOnSortinIcon;

	/**
	 * @return the recentActivityActivityCreatedOnSortinIcon
	 */
	public WebElement getRecentActivitiesActivityCreatedOnSortinIcon(int timeOut) {
		return isDisplayed(driver, recentActivityActivityCreatedOnSortinIcon, "Visibility", timeOut,
				"recent activity alert activity created on sorting icon");
	}

	@FindBy(xpath = "//div[@id='recordscount']")
	private WebElement recentActivitiesCount;

	/**
	 * @return the recentActivitiesCount
	 */
	public WebElement getRecentActivitiesCount(int timeOut) {
		return isDisplayed(driver, recentActivitiesCount, "Visibility", timeOut, "recent activities count");
	}
	
	public List<WebElement> getRecentDocumentHeadertextList(){
		return FindElements(driver, "//span[contains(@id,'myGrid-header-')]/span[3]", "recent document header text list");
	}
	
	public List<WebElement> getRecentDocumentSortingIconList(){
		return FindElements(driver, "//span[contains(@id,'myGrid-header-') and contains(@class,'aw-grid-header')]//span[3]/span", "recent document sorting icon list");
	}
	public List<WebElement> getRecentActivitiesCreatedOnList(){
		return FindElements(driver, "//span[@id='myGrid']//span[contains(@class,'aw-rows-normal')]/span[2]/a/../../span[5]", "recent activities created on list");
	}
	public List<WebElement> getFirmProfileUpdateFieldList(){
		return FindElements(driver, "//span[contains(@id,'grid_AccountProfileUpdated-cell-0-')]", "firm profile update value update list");
	}
	public List<WebElement> getFirmProfileUpdateNewvalueList(){
		return FindElements(driver, "//span[contains(@id,'grid_AccountProfileUpdated-cell-1-')]", "firm profile update new value list");
	}
	public List<WebElement> getFirmProfileUpdateHeaderTextList(){
		return FindElements(driver, "//span[contains(@id,'grid_AccountProfileUpdated-header-')]/span[3]", "firm profile update header text list");
	}
	public List<WebElement> getFirmProfileUpdateSortingIconList(){
		return FindElements(driver, "//span[contains(@id,'grid_AccountProfileUpdated-header-')]/span[3]/span", "firm profile update sorting icon list");
	}
	public List<WebElement> getContactProfileUpdateHeaderTextList(){
		return FindElements(driver, "//span[contains(@id,'grid_ContactProfileUpdated-header-')]/span[3]", "contact profile update header text list");
	}
	
	public List<WebElement> getContactProfileUpdateSortingIcon(){
		return FindElements(driver, "//span[contains(@id,'grid_ContactProfileUpdated-header-')]/span[3]/span", "contact profile update sorting icon");
	}
	
	public List<WebElement> getContactProfileUpdateFieldList(){
		return FindElements(driver, "//span[contains(@id,'grid_ContactProfileUpdated-cell-0-')]", "contact profile update field list");
	}
	
	public List<WebElement> getContactProfileUpdateValueList(){
		return FindElements(driver, "//span[contains(@id,'grid_ContactProfileUpdated-cell-1-')]", "contact profile update value list");
	}
	@FindBy(xpath = "//span[@id='grid_Investor-cell-0-0']//span")
	private WebElement noDocumentToDisplay;
	
	
	/**
	 * @return the noDocumentToDisplay
	 */
	public WebElement getNoDocumentToDisplay(int timeOut) {
		return isDisplayed(driver, noDocumentToDisplay, "Visibility", timeOut, "no document to display");
	}

	@FindBy(xpath="//div[contains(@class,'TargetAccountProfileUpdated_fancybox')]//a")
	private WebElement firmprofileUpdateCrossIcon;
	/**
	 * @return the firmprofileUpdateCrossIcon
	 */
	public WebElement getFirmprofileUpdateCrossIcon(int timeOut) {
		return isDisplayed(driver, firmprofileUpdateCrossIcon, "Visibility", timeOut, "firm profile update cross icon");
	}
	
	@FindBy(xpath="//div[contains(@class,'TargetAccountProfileUpdated_fancybox')]//input[@value='Close']")
	private WebElement firmProfileUpdateCloseBtn;

	/**
	 * @return the firmProfileUpdateCloseBtn
	 */
	public WebElement getFirmProfileUpdateCloseBtn(int timeOut) {
		return isDisplayed(driver, firmProfileUpdateCloseBtn, "Visibility", timeOut, "firm profile update close button");
	}
	
	@FindBy(xpath="//div[contains(@class,'TargetContactProfileUpdated_fancybox')]//a")
	private WebElement contactProfileUpdateCrossIcon;

	/**
	 * @return the contactProfileUpdateCrossIcon
	 */
	public WebElement getContactProfileUpdateCrossIcon(int timeOut) {
		return isDisplayed(driver, contactProfileUpdateCrossIcon, "Visibility", timeOut, "contact profile update cross icon");
	}
	
	@FindBy(xpath="//div[contains(@class,'TargetContactProfileUpdated_fancybox')]//input[@value='Close']")
	private WebElement contactProfileCloseBtn;
	/**
	 * @return the contactProfileCloseBtn
	 */
	public WebElement getContactProfileCloseBtn(int timeOut) {
		return isDisplayed(driver, contactProfileCloseBtn, "Visibility", timeOut, "contact profile update close button");
	}
	
	@FindBy(xpath = "//span[@id='grid_ContactProfileUpdated-scroll-box']")
	private WebElement contactProfileUpdatedPopUpScroll;

	/**
	 * @return the contactProfileUpdatedPopUpScroll
	 */
	public WebElement getContactProfileUpdatedPopUpScroll(int timeOut) {
		return isDisplayed(driver, contactProfileUpdatedPopUpScroll, "Visibility", timeOut,"Contact Profile Upadated Scroll Box");
	}
	
	@FindBy(xpath="//span[@id='grid_AccountProfileUpdated-scroll-box']")
	private WebElement firmProfileUpdatePopUpScroll;
	
	/**
	 * @return the firmProfileUpdatePopUpScroll
	 */
	public WebElement getFirmProfileUpdatePopUpScroll(int timeOut) {
		return isDisplayed(driver, firmProfileUpdatePopUpScroll, "Visibility", timeOut, "firm profile updated scroll box");
	}

	@FindBy(xpath="//div[@id='fileNotFound']")
	private WebElement documentNotFound;
	
	/**
	 * @return the documentNotFound
	 */
	public WebElement getDocumentNotFound(int timeOut) {
		return isDisplayed(driver, documentNotFound, "Visibility", timeOut, "document not found text message");
	}

	/*****************************************/
@FindBy(xpath = "//span[@id='grid_Investor-scroll-box']")
private WebElement scrollBoxPotentialInvestments;


	/**
	 * @return the scrollBoxPotentialInvestments
	 */
	public WebElement getScrollBoxPotentialInvestments(int timeOut) {
		return isDisplayed(driver, scrollBoxPotentialInvestments, "Visibility", timeOut, "scroll box potential investments");
	}

	@FindBy(xpath = "//img[@alt='Bulk Download']")
	private WebElement bulkDownloadIcon;

	/**
	 * @return the bulkDownloadIcon
	 */
	public WebElement getBulkDownloadIcon(int timeOut) {
		return isDisplayed(driver, bulkDownloadIcon, "Visibility", timeOut, "Bulk Download Icon");
	}
	
@FindBy(xpath="//select[@id='pg:frm:selectfund']")
private WebElement potentialAndCurrentInvestmentInvestmentDropdown;


/**
 * @return the potentialInvestmentInvestmentDropdown
 */
public WebElement getPotentialAndCurrentInvestmentInvestmentDropdown(int timeOut) {
	return isDisplayed(driver, potentialAndCurrentInvestmentInvestmentDropdown, "Visibility", timeOut, "Potential And Current  Investment Dropdown");
}

@FindBy(xpath="//span[@id='gridDivRecords']")
private WebElement InvSide_RecordCount;

/**
 * @return the Record Count
 * 
 * 
 */
public WebElement getRecordCount(investorSideWorkSpace workspace,int timeOut) {
		return isDisplayed(driver, InvSide_RecordCount, "Visibility", timeOut, workspace+" Investor Side Record Count");
}

@FindBy(xpath="//a[@id='btnaUpload']/img")
private WebElement uploadIcon;

/**
 * @return the uploadIcon
 */
public WebElement getUploadIcon(int timeOut) {
	return isDisplayed(driver, uploadIcon, "Visibility", timeOut, "Upload Icon");
}
@FindBy(xpath = "//input[@name='FUReplaceFile']")
private WebElement chooseFileButton;


/**
 * @return the chooseFileButton
 */
public WebElement getChooseFileButton(int timeOut) {
	return isDisplayed(driver, chooseFileButton, "Visibility", timeOut, "choose file button on upload window");
}

@FindBy(xpath="//a[@id='LinkButton1']")
private WebElement addButton;

/**
 * @return the addButton
 */
public WebElement getAddButton(int timeOut) {
	return isDisplayed(driver, addButton, "Visibility", timeOut, "Add Button");
}

@FindBy(xpath="//p[contains(text(),'Document uploaded successfully.')]")
private WebElement uploadMsg;

/**
 * @return the addButton
 */
public WebElement getUploadMsg(int timeOut) {
	return isDisplayed(driver, uploadMsg, "Visibility", timeOut, "Upload Msg");
}

@FindBy(xpath="(//div[@class='formbox']/p)[3]")
private WebElement updateMsg;

/**
 * @return the updateButton
 */
public WebElement getUpdateMsg(int timeOut) {
	return isDisplayed(driver, updateMsg, "Visibility", timeOut, "Update Msg");
}

//@FindBy(css ="")
//private WebElement updateButton;

/**
 * @return the updateButton
 */
public WebElement getUpdateButton(int timeOut) {
	WebElement ele= BaseLib.edriver.findElement(By.cssSelector("#lnkUpdate"));
	return isDisplayed(driver, ele, "Visibility", timeOut, "Update Button");
}
@FindBy(xpath = "//a[@title='Ignore']")
private WebElement ignoreBtn;


/**
 * @return the ignoreBtn
 */
public WebElement getIgnoreBtn(int timeOut) {
	return isDisplayed(driver, ignoreBtn, "Visibility", timeOut, "ignore button on upload window investor page");
}

@FindBy(xpath="//div[@class='ConfirmationImport_fancybox_success FancyboxContainer']//a[@class='btn_active']")
private WebElement closeButton;

/**
 * @return the closeButton
 */
public WebElement getCloseButton(int timeOut) {
	return isDisplayed(driver, closeButton, "Visibility", timeOut, "Close Button");
}
@FindBy(xpath = "//p[contains(text(),'attempting to add already exists')]")
private WebElement duplicateDocText;




/**
 * @return the duplicateDocText
 */
public WebElement getDuplicateDocText(int timeOut) {
	return isDisplayed(driver, duplicateDocText, "Visibility", timeOut, "duplicate document text");
}

@FindBy(xpath="(//span[text()='Contents']/..//img)[1]")
private WebElement refreshIcon;

/**
 * @return the refreshIcon
 */
public WebElement getRefreshIcon(int timeOut) {
	return isDisplayed(driver, refreshIcon, "Visibility", timeOut, "Refresh Icon");
}


@FindBy(xpath="//a[@id='LinkButton1']/..//a[2]")
private WebElement cancelBtn;

/**
 * @return the cancelBtn
 */
public WebElement getcancelBtn(int timeOut) {
	return isDisplayed(driver, cancelBtn, "Visibility", timeOut, "Cancel Button");
}

@FindBy(xpath="//span[@id='Message']")
private WebElement pleaseSelectDocToUpload;

/**
 * @return the pleaseSelectDocToUpload
 */
public WebElement getPleaseSelectDocToUpload(int timeOut) {
	return isDisplayed(driver, pleaseSelectDocToUpload, "Visibility", timeOut, "please Select DocToUpload Msg");
}

@FindBy(xpath="(//div[@class='formbox']/p)[2]")
private WebElement docAlreadyMsg;

/**
 * @return the docAlreadyMsg
 */
public WebElement getDocAlreadyMsg(int timeOut) {
	return isDisplayed(driver, docAlreadyMsg, "Visibility", timeOut, "Doc Already Msg");
}

@FindBy(xpath="(//div[@class='formbox']/p)[2]")
private WebElement docAlreadyMsgCloseBtn;

/**
 * @return the docAlreadyMsgCloseBtn
 */
public WebElement getDocAlreadyMsgCloseBtn(int timeOut) {
	return isDisplayed(driver, docAlreadyMsgCloseBtn, "Visibility", timeOut, "Doc Already Msg Close Button");
}

public List<WebElement> getDocListonInvestmentTab(){
	return FindElements(driver, "//span[contains(@id,'grid_Investor')]//span[contains(@id,'grid_Investor-cell-0')]/a", "Doc List on Investment Tab");
}
public List<WebElement> getUploadedByListInvestmentTab(){
	return FindElements(driver, "//span[contains(@id,'grid_Investor')]//span[contains(@id,'grid_Investor-cell-1')]/span", "Doc List on Investment Tab");
}
public List<WebElement> getFirmListInvestmentTab(){
	return FindElements(driver, "//span[contains(@id,'grid_Investor')]//span[contains(@id,'grid_Investor-cell-2')]", "Doc List on Investment Tab");
}
//span[contains(@id,'grid_Investor-cell-2')]/span
public List<WebElement> getUploadedOnInvestmentTab(){
	return FindElements(driver, "//span[contains(@id,'grid_Investor')]//span[contains(@id,'grid_Investor-cell-3')]", "Doc List on Investment Tab");
}
public List<WebElement> getColumnHeadsInvestor() {
	return FindElements(driver, "//span[contains(@id,'grid_Investor-header')]/span/span[3]", "column heads on investor firm page");
}

@FindBy(xpath="//span[@id='myGrid_firmAllDoc-scroll-box']")
private WebElement scrollBoxAtfirmPage;

/**
 * @return the scrollBox
 */
public WebElement getScrollBoxAtFirmPage(int timeOut) {
	return isDisplayed(driver, scrollBoxAtfirmPage, "Visibility", timeOut, "scroll Box");
}

	@FindBy(xpath="//a[@title='Show More']")
	private WebElement showMoreLink;

	/**
	 * @return the showMoreLink
	 */
	public WebElement getShowMoreLink(int timeOut) {
		return isDisplayed(driver, showMoreLink, "Visibility", timeOut, "Show More Link");
	}
	
@FindBy(xpath="//div[@id='header']//div//a[@class='logo']")
private WebElement navatarInvestorLogo;

/*
 * return Navatar Investor Logo.
 */
public WebElement getNavatarInvestorLogo(int timeout)
{
	return isDisplayed(driver, navatarInvestorLogo, "visibility", timeout, "Navatar Investor Logo");
}

@FindBy(xpath="//div[@class='BulkDownload_fancybox']//input[@id='Yes']")
private WebElement bulkDownloadConfirmationPopUpYesBtn;

/**
 * @return the bulkDownloadConfirmationPopUpYesBtn
 */
public WebElement getBulkDownloadConfirmationPopUpYesBtn(int timeOut) {
	return isDisplayed(driver, bulkDownloadConfirmationPopUpYesBtn, "Visibility", timeOut, "Bulk Download Confirmation PopUp Yes Btn");
}


@FindBy(xpath="//div[@class='BulkDownload_fancybox']//input[@title='No']")
private WebElement bulkDownloadConfirmationPopUpNoBtn;

/**
 * @return the bulkDownloadConfirmationPopUpNoBtn
 */
public WebElement getBulkDownloadConfirmationPopUpNoBtn(int timeOut) {
	return isDisplayed(driver, bulkDownloadConfirmationPopUpNoBtn, "Visibility", timeOut, "Bulk Download Confirmation PopUp No Btn");
}

public boolean getProcessingPleasewait(int timeout){
	
	FindElement(driver, "p[text(),'Please wait']", "Please wait", action.BOOLEAN, 10);
	
	return false;
	
}

@FindBy(xpath="//a[@title='Download']")
private WebElement bulkDownloadDownloadButton;

/**
 * @return the bulkDownloadDownloadButton
 */
public WebElement getBulkDownloadDownloadButton(int timeOut) {
	return isDisplayed(driver, bulkDownloadDownloadButton, "Visibility", timeOut, "Bulk download download button");
}

@FindBy(xpath="(//div[@class='head_popup'][text()='Confirmation '])[1]/following-sibling::p[1]")
private WebElement bulkDownloadConfirmationMessage1;

/**
 * @return the bulkDownloadConfirmationMessage1
 */
public WebElement getbulkDownloadConfirmationMessage1(int timeOut) {
	return isDisplayed(driver, bulkDownloadConfirmationMessage1, "Visibility", timeOut, "Confirmation Message");
}

@FindBy(xpath="(//div[@class='head_popup'][text()='Confirmation '])[1]/following-sibling::ul/li[1]")
private WebElement bulkDownloadConfirmationMessage2;

/**
 * @return the bulkDownloadConfirmationMessage2
 */
public WebElement getbulkDownloadConfirmationMessage2(int timeOut) {
	return isDisplayed(driver, bulkDownloadConfirmationMessage2, "Visibility", timeOut, "Confirmation Message");
}

@FindBy(xpath="(//div[@class='head_popup'][text()='Confirmation '])[1]/following-sibling::ul/li[2]")
private WebElement bulkDownloadConfirmationMessage3;

/**
 * @return the bulkDownloadConfirmationMessage3
 */
public WebElement getbulkDownloadConfirmationMessage3(int timeOut) {
	return isDisplayed(driver, bulkDownloadConfirmationMessage3, "Visibility", timeOut, "Confirmation Message");
}

@FindBy(xpath="(//div[@class='head_popup'][text()='Confirmation '])[1]/following-sibling::ul/li[3]")
private WebElement bulkDownloadConfirmationMessage4;

/**
 * @return the bulkDownloadConfirmationMessage4
 */
public WebElement getbulkDownloadConfirmationMessage4(int timeOut) {
	return isDisplayed(driver, bulkDownloadConfirmationMessage4, "Visibility", timeOut, "Confirmation Message");
}

@FindBy(xpath="(//div[@class='head_popup'][text()='Confirmation '])[1]/following-sibling::p[2]")
private WebElement bulkDownloadConfirmationMessage5;

/**
 * @return the bulkDownloadConfirmationMessage5
 */
public WebElement getbulkDownloadConfirmationMessage5(int timeOut) {
	return isDisplayed(driver, bulkDownloadConfirmationMessage5, "Visibility", timeOut, "Confirmation Message");
}


@FindBy(xpath="(//div[contains(@class,'comnBackgroundWhite')]/div/span/img)[2]")
private WebElement InvestmentDownArrow;

/**
 * @return the getInvestmentDownArrow
 */
public WebElement getInvestmentDownArrow(int timeOut) {
	return isDisplayed(driver, InvestmentDownArrow, "Visibility", timeOut, "investment down arrow");
}

@FindBy(xpath="//img[@alt='Edit']")
private WebElement firmNameInfoIcon;

/**
 * @return the firmNameInfoIcon
 */
public WebElement getFirmNameInfoIcon(int timeOut) {
	return isDisplayed(driver, firmNameInfoIcon, "Visibility", timeOut, "firm name info icon");
}

@FindBy(xpath="//div[@id='NameMax']")
private WebElement firmNameHeaderText;

/**
 * @return the firmNameHeaderText
 */
public WebElement getFirmNameHeaderText(int timeOut) {
	return isDisplayed(driver, firmNameHeaderText, "Visibility", timeOut, "firm name header text");
}


@FindBy(xpath="//div[contains(@class,'FirmInformation_Tequity')]/a")
private WebElement firmNameInfoPopUpCrossIcon;

/**
 * @return the firmNameInfoPopUpCrossIcon
 */
public WebElement getFirmNameInfoPopUpCrossIcon(int timeOut) {
	return isDisplayed(driver, firmNameInfoPopUpCrossIcon, "Visibility", timeOut, "firm name information pop up cross icon");
}


}
