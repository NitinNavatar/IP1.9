/**
 * 
 */
package com.navatar.pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.navatar.generic.BaseLib;
import com.navatar.generic.CommonLib.Mode;
import com.navatar.generic.CommonLib.PageName;
import com.navatar.generic.CommonLib.Workspace;
import com.navatar.generic.CommonLib.action;
import com.navatar.generic.CommonLib.investorSideWorkSpace;
import static com.navatar.generic.CommonVariables.*;

import static com.navatar.generic.CommonLib.*;
/**
 * @author Parul Singh
 *
 */
import static com.navatar.generic.CommonVariables.M17Contact1EmailId;

import java.util.ArrayList;
import java.util.List;

import javax.management.loading.PrivateClassLoader;
public class FundsPage extends BasePageBusinessLayer {

	/**
	 * @param driver
	 */
	public FundsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//input[@name='Name']")
	private WebElement fundName;

	/**
	 * @return the fundName
	 */
	public WebElement getFundName(int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, fundName_Classic, "Visibility", timeOut, "Fund Name Classic");
		}else{
			return isDisplayed(driver, fundName_Lighting, "Visibility", timeOut, "Fund Name Lighting");
		}
	}
	
	@FindBy(xpath="//div[@class='requiredInput']//select")
	private WebElement fundType;

	/**
	 * @return the fundType
	 */
	public WebElement getFundType(int timeOut) {
		return isDisplayed(driver, fundType, "Visibility", timeOut, "Fund Type");
	}
	
	@FindBy(xpath="(//div[@class='requiredInput']//select)[2]")
	private WebElement investmentCategory;

	/**
	 * @return the investmentCategory
	 */
	public WebElement getInvestmentCategory(int timeOut) {
		return isDisplayed(driver, investmentCategory, "Visibility", timeOut, "Investment Category");
	} 
	
	@FindBy(xpath="//div[@id='Name_ileinner']")
	private WebElement fundNameInViewMode;
	
	@FindBy(xpath="//*[contains(text(),'Fund')]/..//*[@slot='primaryField']")
	private WebElement fundNameInViewModeLightning;

	/**
	 * @return the fundNameLabel
	 */
	public WebElement getFundNameInViewMode(int timeOut) {
		
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, fundNameInViewMode, "Visibility", timeOut, "Fund Name in View Mode");
		}else{
			return isDisplayed(driver, fundNameInViewModeLightning, "Visibility", timeOut, "Fund Name in View Mode");
		}
	}
	public WebElement getFundNameInViewMode1(String fund,int timeOut) {
		
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, fundNameInViewMode, "Visibility", timeOut, "Fund Name in View Mode");
		}else{
			String xpath="//*[text()='Fund']/following-sibling::*//*[text()='"+fund+"']";
			WebElement ele=FindElement(driver, xpath, "Fund Name in View Mode", action.BOOLEAN, 10);
			return isDisplayed(driver, ele, "Visibility", timeOut, "Fund Name in View Mode");
		}
	}
	@FindBy(xpath="//iframe[contains(@title,'PE_Fund_NothingEnabled')]")
	private WebElement workspaceFrameOnFundsPage;

	/**
	 * @return the workspaceFrameOnFundsPage
	 */
	public WebElement getWorkspaceFrameOnFundsPage(int timeOut) {
		return isDisplayed(driver, workspaceFrameOnFundsPage, "Visibility", timeOut, "workspace Frame On Funds Page");
	}
	
	@FindBy(xpath="//input[@title='Build Fundraising Workspace']")
	private WebElement buildFundraisinWorkspaceButton;

	/**
	 * @return the buildFundraisinWorkspaceButton
	 */
	public WebElement getBuildFundraisinWorkspaceButton(int timeOut) {
		return isDisplayed(driver, buildFundraisinWorkspaceButton, "Visibility", timeOut, "Build FundRaising Workspace");
	}
	
	@FindBy(xpath="//input[@title='Build Investor Workspace']")
	private WebElement buildInvestorWorkspace;

	/**
	 * @return the buildInvestorWorkspace
	 */
	public WebElement getBuildInvestorWorkspace(int timeOut) {
		return isDisplayed(driver, buildInvestorWorkspace, "Visibility", timeOut, "Build Investor Workspace");
	}
	/*************************************************** workspace*********************************************/
	
	@FindBy(xpath="//div[@id='headFRWS']/h3")
	private WebElement fundRaisingWorkSpaceSection;
	
	/**
	 * @return the fundRaisingWorkSpaceLabelText
	 */
	public WebElement getFundRaisingWorkSpaceSection(int timeOut) {
		return isDisplayed(driver, fundRaisingWorkSpaceSection, "Visibility", timeOut, "fundraising workspace label text");
	}
	
	@FindBy(xpath="//h3[text()='Investor Workspace']")
	private WebElement investorWorkSpaceSection;

	/**
	 * @return the investorWorkSpaceSection
	 */
	public WebElement getInvestorWorkSpaceSection(int timeOut) {
		return isDisplayed(driver, investorWorkSpaceSection, "Visibility", timeOut, "investor workspace label text");
	}

	public WebElement getContactAccessHeadText(Workspace workspace, int timeOut)
	{
		String xpath="",w="";
		if (workspace == Workspace.InvestorWorkspace) {
			w = "INV";
			
		}
		else {
			w="FR";
			
		}
		xpath = "//span[@id='CABW"+w+"']/parent::div";
		return isDisplayed(driver, FindElement(driver, xpath, "contact access text", action.SCROLLANDBOOLEAN, timeOut/2), "visibility",timeOut/2, "contact access text");
	}
	
	@FindBy(xpath = "//a[@id='demo-basicBWINV_MA']")
	private WebElement mouseOverIconContactAccess;
	
	
	/**
	 * @return the mouseOverIconContactAccess
	 */
	public WebElement getMouseOverIconContactAccess(int timeOut) {
		return isDisplayed(driver, mouseOverIconContactAccess, "Visibility", timeOut, "contact access i icon");
	}
	
	@FindBy(xpath = "(//a[@title='Close'][contains(@onclick,'closePopUpLastBWINV_MA')])[2]")
	private WebElement contactAccessConfirmationClose;
	
	
	/**
	 * @return the contactAccessConfirmationClose
	 */
	public WebElement getContactAccessConfirmationClose(Workspace workspace,int timeOut) {
		return isDisplayed(driver, contactAccessConfirmationClose, "Visibility", timeOut, "contact access confirmation clsoe");
	}

	@FindBy(xpath = "//a[@id='demo-basicBWINV_MA']/img")
	private WebElement mouseOverIconContactAccessimg;
	
	
	/**
	 * @return the mouseOverIconContactAccess
	 */
	public WebElement getMouseOverIconContactAccessimg(int timeOut) {
		return isDisplayed(driver, mouseOverIconContactAccessimg, "Visibility", timeOut, "contact access i icon");
	}

	public WebElement getContactAccessFolderName(Workspace workspace, int timeOut)
	{
		String xpath="",w="";
		if (workspace == Workspace.InvestorWorkspace) {
			w = "INV";
			
		}
		else {
			w="FR";
			
		}
		xpath = "//span[@id='CABW"+w+"']";
		return isDisplayed(driver, FindElement(driver, xpath, "contact access text", action.SCROLLANDBOOLEAN, timeOut/2), "visibility",timeOut/2, "contact access text");
	}
	
	public List<WebElement> listOfColumnsContactAccess(Workspace workspace) {
		String w="";
		if (workspace == Workspace.InvestorWorkspace)
			w="INV";
		else
			w="FR";
		return FindElements(driver, "//span[contains(@id,'DealDetailBW"+w+"-header-')and contains(@id,'-box')]/span[3]","list of column names");
		
		
	}
	
	@FindBy(xpath = "//img[@title='View Documents']")
	private WebElement viewDocsIcon;
	
	
	/**
	 * @return the viewDocsIcon
	 */
	public WebElement getViewDocsIcon(int timeOut) {
		return isDisplayed(driver, viewDocsIcon, "Visibility", timeOut, "view docs icon");
	}

	@FindBy(xpath = "//img[@title='Download / Print Documents']")
	private WebElement uploadDocsIcon;
	
	
	/**
	 * @return the printDocsIcon
	 */
	public WebElement getUploadDocsIcon(int timeOut) {
		return isDisplayed(driver, uploadDocsIcon, "Visibility", timeOut, "upload docs icon");
	}

	
	
	public WebElement uploadDocTooltipContactAccess(Workspace workspace, int timeOut) {
		String w="";
		if (workspace == Workspace.InvestorWorkspace)
			w="INV";
		else
			w="FR";
		return isDisplayed(driver, FindElement(driver, "//a[@id='access_tolltipBW"+w+"_MA']", "tooltip upload docs", action.SCROLLANDBOOLEAN, timeOut/2), "visibility", timeOut/2, "tooltip upload docs");
	}
	public WebElement getContactAccessCrossIcon(Workspace workspace, int timeOut) {
		String xpath="",w="";
		if (workspace == Workspace.InvestorWorkspace) {
			w="INV";
		}
		else {
			w="FR";
		}
		xpath = "//span[@id='CABW"+w+"']/following-sibling::a[@title='Close']";
		return isDisplayed(driver, FindElement(driver, xpath, "contact access cross icon", action.SCROLLANDBOOLEAN, timeOut/2), "visibility",timeOut/2, "contact access cross icon");

	}
	public WebElement applyButtonContactAccessDeactive(Workspace workspace){
		String xpath="",w="";
		if (workspace == Workspace.InvestorWorkspace) {
			w="INV";
		}
		else {
			w="FR";
		}
		xpath = "//a[@class='btn_deactive'][@title='Apply'][contains(@id,'"+w+"')]";
		return isDisplayed(driver, FindElement(driver, xpath, "apply button disabled", action.SCROLLANDBOOLEAN, 30), "visibility", 30, "apply button disabled");
	}
	
	public WebElement cancelButtonContactAccessActive(Workspace workspace){
		String xpath="",w="";
		if (workspace == Workspace.InvestorWorkspace) {
			w="INV";
		}
		else {
			w="FR";
		}
		xpath = "//a[@class='btn_active'][@title='Cancel'][contains(@onclick,'BW"+w+"')]";
		return isDisplayed(driver, FindElement(driver, xpath, "cancel button enabled", action.SCROLLANDBOOLEAN, 30), "visibility", 30, "cancel button enabled");
	}
	
	@FindBy(xpath="//a[@title='Contact Access']")
	private List<WebElement> contactAccessIcon;

	/**
	 * @return the contactAccessIcon
	 */
	public WebElement getContactAccessIcon(Workspace workspace,int timeOut) {
		int i=1;
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				List<WebElement> lst = FindElements(driver, "//div[@id='frworkspace']//a[@title='Contact Access']", "contact access icon in FR");
				if(!lst.isEmpty()) {
					for(int j=0;j<lst.size();j++) {
						if(isDisplayed(driver, lst.get(j), "visibility", 1, "")!=null) {
							return lst.get(j);
						}else {
							if(j==lst.size()-1) {
								return null;
							}
						}
					}
				}else {
					return null;
				}
			}else {
				i=0;
			}
		}else {
			if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				List<WebElement> lst = FindElements(driver, "//div[@id='invworkspace']//a[@title='Contact Access']", "contact access icon in FR");
				if(!lst.isEmpty()) {
					for(int j=0;j<lst.size();j++) {
						if(isDisplayed(driver, lst.get(j), "visibility", 1, "")!=null) {
							return lst.get(j);
						}else {
							if(j==lst.size()-1) {
								return null;
							}
						}
					}
				}else {
					return null;
				}
			}else {
				i=1;
			}
		}
		return isDisplayed(driver, contactAccessIcon.get(i), "Visibility", timeOut, "Contact Access Icon of "+workspace);
	} 
	
	@FindBy(xpath="//img[@id='minus_iconBWFR_MA']")
	private WebElement fWR_contactAccessMinusIcon;

	/**
	 * @return the fWR_contactAccessMinusIcon
	 */
	public WebElement getfWR_contactAccessMinusIcon(int timeOut) {
		return isDisplayed(driver, fWR_contactAccessMinusIcon, "Visibility", timeOut, "fundraising workspace minus icon");
	}
	
	@FindBy(xpath="//img[@id='plus_iconBWFR_MA']")
	private WebElement fWR_contactAccessPlusIcon;

	/**
	 * @return the fWR_contactAccessPlusIcon
	 */
	public WebElement getfWR_contactAccessPlusIcon(int timeOut) {
		return isDisplayed(driver, fWR_contactAccessPlusIcon, "Visibility", timeOut, "fundraising workspace plus icon");
	}
	
	@FindBy(xpath="//img[@id='minus_iconBWINV_MA']")
	private WebElement INV_contactAccessMinusIcon;

	/**
	 * @return the iNV_contactAccessMinusIcon
	 */
	public WebElement getINV_contactAccessMinusIcon(int timeOut) {
		return isDisplayed(driver, INV_contactAccessMinusIcon, "Visibility", timeOut, "investor workspace minus icon");
	}
	
	@FindBy(xpath="//img[@id='plus_iconBWINV_MA']")
	private WebElement INV_contactAccessPlusIcon;

	/**
	 * @return the iNV_contactAccessPlusIcon
	 */
	public WebElement getINV_contactAccessPlusIcon(int timeOut) {
		return isDisplayed(driver, INV_contactAccessPlusIcon, "Visibility", timeOut, "investor workspace plus icon");
	}
	
	@FindBy(xpath="//input[@id='searchcon_grid111BWINV_MA']")
	private WebElement INV_searchTextBox;

	
	@FindBy(xpath="//input[@id='searchcon_grid111BWFR_MA']")
	private WebElement fWR_searchTextBox;

	public WebElement getSearchTextBox(Workspace workspace, int timeOut) {
		WebElement ele=null;
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			ele= isDisplayed(driver, fWR_searchTextBox, "Visibility", timeOut, workspace+" serach text box");
		}else if (workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			ele=isDisplayed(driver, INV_searchTextBox, "Visibility", timeOut, workspace+" serach text box");
		}
		return ele;	
	}
	
	
	public List<WebElement> getSelectedContactDataList(Workspace workspace,int timeOut){
		String xpath="";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
			xpath="FR";
		}else {
			xpath="INV";
		}
		return FindElements(driver, "//span[contains(@id,'grid11_DealDetailBW"+xpath+"-cell-')]", "selected contacts data list");
	}
	
	
	public WebElement getContactViewOrUploadOrDownloadCheckBox(Workspace workspace,int timeOut,CheckBoxName checkBoxName) {
		WebElement ele= null;
		int i=0;
		String xpath="";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
			xpath="FR";
		}else {
			xpath="INV";
		}
		if(checkBoxName.toString().equalsIgnoreCase(CheckBoxName.View.toString())) {
			i=5;
		}else if (checkBoxName.toString().equalsIgnoreCase(CheckBoxName.Download.toString())) {
			i=6;
		}else {
			i=7;
		}
		ele=isDisplayed(driver, FindElement(driver, "//span[contains(@id,'grid11_DealDetailBW"+xpath+"-cell-"+i+"')]//input","Contact "+checkBoxName.toString()+" check box in selected contact grid", action.SCROLLANDBOOLEAN, 20),"visibility",30, "Contact "+checkBoxName.toString()+" check box in selected contact grid");
		return ele;
	}
	
	
	
	public WebElement noDataToDisplayContactAccess(Workspace workspace, int timeOut) {
		String w="";
		if (workspace == Workspace.InvestorWorkspace)
			w="INV";
		else
			w="FR";
		return isDisplayed(driver,FindElement(driver, "//div[@id='shwBottomGridBW"+w+"_MA']//span[contains(@id,'DealDetailBW"+w+"-cell-1-0')]", "no data to display", action.SCROLLANDBOOLEAN, timeOut/2), "visibility", timeOut/2, "no data to display");
	}
	
	
	@FindBy(xpath="//div[@id='addSelectBtnActiveBWINV_MA']/a")
	private WebElement INV_addSelectContactBtn;

	@FindBy(xpath="//div[@id='addSelectBtnActiveBWFR_MA']/a")
	private WebElement fWR_addselectContactBtn;
	
	public WebElement getaddselectContactBtn(Workspace workspace,int timeOut) {
		WebElement ele=null;
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			ele=isDisplayed(driver, fWR_addselectContactBtn, "Visibility", timeOut, workspace+" add select contact active button");
			
		}else if (workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			ele=isDisplayed(driver, INV_addSelectContactBtn, "Visibility", timeOut, workspace+" add select contact active button");
		}
		return ele;		
	}
	
	public List<WebElement> getselectContactEmailIDList(Workspace workspace){
		List<WebElement> lst= new ArrayList<WebElement>();
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			lst=FindElements(driver, "//span[contains(@id,'grid11_DealDetailBWFR-cell-3')]/a", workspace+" select contact email id list");
		}else if(workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			lst=FindElements(driver, "//span[contains(@id,'grid11_DealDetailBWINV-cell-3')]/a", workspace+" select contact email id list");
		}		
		return lst;
	}
	
	@FindBy(xpath="//a[@id='applyButtonBWFR_MA']")
	private WebElement fWR_applyBtn;
	
	@FindBy(xpath="//a[@id='applyButtonBWINV_MA']")
	private WebElement INV_applyBtn;
	public WebElement getManageApprovalIcon(Workspace workspace, int timeOut) {
		String xpath = "";
		if (workspace == Workspace.FundraisingWorkspace) {
			xpath = "(//a[@title='Manage Approvals']/img)[1]";
		}
		else if(workspace == Workspace.InvestorWorkspace) {
			xpath = "(//a[@title='Manage Approvals']/img)[2]";
		}
		return isDisplayed(driver, FindElement(driver, xpath, "manage approval icon", action.SCROLLANDBOOLEAN, timeOut/2), "visibility", timeOut/2, "manage approval icon");
	}
	public WebElement getApplyBtn(Workspace workspace,int timeOut) {
		WebElement ele = null;
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			ele=isDisplayed(driver, fWR_applyBtn, "Visibility", timeOut, workspace+"  contact access apply button");
		}else if(workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			ele=isDisplayed(driver, INV_applyBtn, "Visibility", timeOut, workspace+" contact access apply button");
		}	
		return ele;
	}
	
	
	public WebElement getInviteContactSuccessfulErrorMsg(Workspace workspace,int timeOut) {
		String xpath="";
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			xpath="FR";
		}else if(workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			xpath="INV";
		}	
		return isDisplayed(driver,FindElement(driver, "//div[contains(@class,'applyDoneBW"+xpath+"_MA')]//p","contact invite successful confirmation popup error message", action.SCROLLANDBOOLEAN,timeOut),"visibility",timeOut,"contact invite successful confirmation popup error message");
	}
	
	public WebElement getInviteContactSuccessfulPopUpCrossIcon(Workspace workspace,int timeOut) {
		String xpath="";
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			xpath="FR";
		}else if(workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			xpath="INV";
		}	
		return isDisplayed(driver,FindElement(driver, "//div[contains(@class,'applyDoneBW"+xpath+"_MA')]//span","contact invite successful confirmation popup error message", action.SCROLLANDBOOLEAN,timeOut),"visibility",timeOut,"contact invite successful confirmation popup error message");
	}
	
	
	
	
	
	@FindBy(xpath = "//div[@id='accessRemovedIdBWFR_MA']//a")
	private WebElement fwrRemoveAccessClose;
	@FindBy(xpath = "//div[@id='accessRemovedIdBWINV_MA']//a")
	private WebElement invRemoveAccessClose;
	
	/**
	 * @return the fwrRemoveAccessClose
	 */
	public WebElement getRemoveAccessClose(Workspace w,int timeOut) {
	WebElement ele=null;
	if (w==Workspace.FundraisingWorkspace) {
		ele = isDisplayed(driver, fwrRemoveAccessClose, "Visibility", 60, "remove access close button");
	}
	else if (w==Workspace.InvestorWorkspace) {
		ele = isDisplayed(driver, invRemoveAccessClose, "Visibility", 60, "remove access close button");
	}
	return ele;
	}

	public WebElement getInvestmentInfo(Workspace workspace) {
		WebElement ele = null;
		if (workspace == Workspace.FundraisingWorkspace) {
			ele = FindElement(driver, "(//a[@id='InvestmentInfoId1'])[1]", "investment info link for "+workspace, action.SCROLLANDBOOLEAN, 30);
		}
		else {
			ele = FindElement(driver, "(//a[@id='InvestmentInfoId1'])[2]", "investment info link for "+workspace, action.SCROLLANDBOOLEAN, 30);
		}
		return isDisplayed(driver, ele, "visibility", 30, "investment info link");
	}
	
	
	//div[@id='accessRemovedIdBWFR_MA']//a
	@FindBy(xpath="//div[contains(@class,'applyDoneBWFR_MA')]//a")
	private WebElement fWR_CloseBtn;
	
	@FindBy(xpath="//div[contains(@class,'applyDoneBWINV_MA')]//a")
	private WebElement INV_closeBtn;
	
	public WebElement getCloseBtn(Workspace workspace, int timeOut) {
		WebElement ele = null;
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			ele=isDisplayed(driver, fWR_CloseBtn, "Visibility", timeOut, workspace+"  contact access close button");
		}else if(workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			ele=isDisplayed(driver, INV_closeBtn, "Visibility", timeOut, workspace+" contact access close button");
		}	
		return ele;
	}
	
	@FindBy(xpath="//span[@id='serachTextIconDivBWFR_MA']//a[@title='Search']")
	private WebElement fWR_searchBtn;
	
	@FindBy(xpath="//span[@id='serachTextIconDivBWINV_MA']//a[@title='Search']")
	private WebElement INV_searchBtn;
	
	public WebElement getSearchBtn(Workspace workspace, int timeOut) {
		WebElement ele = null;
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			ele=isDisplayed(driver, fWR_searchBtn, "Visibility", timeOut, workspace+"  contact access search button");
		}else if(workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			ele=isDisplayed(driver, INV_searchBtn, "Visibility", timeOut, workspace+" contact access search button");
		}	
		return ele;
	}
	
	
	@FindBy(xpath="//div[contains(@class,'ContactAccess_fancyboxBWFR')]//a[@title='Cancel']")
	private WebElement fWR_cancelBtn;
	
	@FindBy(xpath="//div[contains(@class,'ContactAccess_fancyboxBWINV')]//a[@title='Cancel']")
	private WebElement INV_cancelBtn;
	
	public WebElement getCancelBtn(Workspace workspace, int timeOut) {
		WebElement ele = null;
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			ele=isDisplayed(driver, fWR_cancelBtn, "Visibility", timeOut, workspace+"  contact access cancel button");
		}else if(workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			ele=isDisplayed(driver, INV_cancelBtn, "Visibility", timeOut, workspace+" contact access cancel button");
		}	
		return ele;
	}
	
	@FindBy(xpath="(//img[@title='Refresh'])[1]")
	private WebElement FWR_ContentGridRefreshBtn;
	
	@FindBy(xpath="(//img[@title='Refresh'])[2]")
	private WebElement INV_ContentGridRefreshBtn;
	
	/**
	 * @return the fWR_ContentGridRefreshBtn
	 */
	public WebElement ContentGridRefreshBtn(Workspace Workspace,int timeOut) {
		WebElement ele;
		if(Workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
			ele= isDisplayed(driver, FWR_ContentGridRefreshBtn, "Visibility", timeOut, "fundraising workspace refresh button ");			
		}else {
			ele= isDisplayed(driver, INV_ContentGridRefreshBtn, "Visibility", timeOut, "investor workspace refresh button ");
		}
		return ele;
	}
	
	/**
	 * @deprecated Use {@link #getContentGridDocNameList(Workspace,PageName)} instead
	 */
	public List<WebElement> getContentGridDocNameList(Workspace workspace){
		return getContentGridDocNameList(workspace, PageName.FundsPage);
	}
	
	public List<WebElement> getContentGridUploadedByList(Workspace workspace, PageName pageName) {
		String workSpaceXpath = "";
		String docNameXpath = "";

			if (pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())) {

				if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
					workSpaceXpath = "//div[@id='invworkspace']";

				} else {
					workSpaceXpath = "//div[@id='frworkspace']";

				}

			} else if (pageName.toString().equalsIgnoreCase(PageName.InstitutionsPage.toString()) ||
					pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {

				if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
					workSpaceXpath = "//div[@id='Investorgrid_div']";

				} else {
					workSpaceXpath = "//div[@id='divFrWorkspace']";
				}
			} else if (pageName.toString().equalsIgnoreCase(PageName.CommitmentsPage.toString())) {

				if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
					workSpaceXpath = "//div[@class='content_div']";

				} else {
					workSpaceXpath = "//div[@class='content_div']";
				}
			} 
			
			if (workSpaceXpath != null) {

				if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
					docNameXpath = workSpaceXpath + "//span[contains(@id,'myGrid-cell-3-')]/span";
				} else {
					docNameXpath = workSpaceXpath + "//span[contains(@id,'myGridfundr-cell-3-')]/span";
				}
			}
		
			return FindElements(driver, docNameXpath, "document name list on : "+pageName.toString()+" - WorkSpace - "+workspace.toString());
		}
	
	public List<WebElement> getContentGridFirmNameList(Workspace workspace, PageName pageName) {
		String workSpaceXpath="",docNameXpath="";

			if (pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())) {

				if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
					workSpaceXpath = "//div[@id='invworkspace']";

				} else {
					workSpaceXpath = "//div[@id='frworkspace']";

				}

			} else if (pageName.toString().equalsIgnoreCase(PageName.InstitutionsPage.toString()) ||
					pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {

				if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
					workSpaceXpath = "//div[@id='Investorgrid_div']";

				} else {
					workSpaceXpath = "//div[@id='divFrWorkspace']";
				}
			} else if (pageName.toString().equalsIgnoreCase(PageName.CommitmentsPage.toString())) {

				if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
					workSpaceXpath = "//div[@class='content_div']";

				} else {
					workSpaceXpath = "//div[@class='content_div']";
				}
			} 
			
			if (workSpaceXpath!=null) {
				if (workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
					docNameXpath = workSpaceXpath + "//span[contains(@id,'myGridfundr-cell-4-')]/span";
				}
				else {
					docNameXpath = workSpaceXpath + "//span[contains(@id,'myGrid-cell-4-')]/span";
				}
			}
		
		return FindElements(driver, docNameXpath, "document name list on : "+pageName.toString()+" - WorkSpace - "+workspace.toString());
	}
	public List<WebElement> getContentGridUploadedOnList(Workspace workspace, PageName pageName) {
		String workSpaceXpath="",docNameXpath="";

			if (pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())) {

				if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
					workSpaceXpath = "//div[@id='invworkspace']";

				} else {
					workSpaceXpath = "//div[@id='frworkspace']";

				}

			} else if (pageName.toString().equalsIgnoreCase(PageName.InstitutionsPage.toString()) ||
					pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {

				if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
					workSpaceXpath = "//div[@id='Investorgrid_div']";

				} else {
					workSpaceXpath = "//div[@id='divFrWorkspace']";
				}
			} else if (pageName.toString().equalsIgnoreCase(PageName.CommitmentsPage.toString())) {

				if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
					workSpaceXpath = "//div[@class='content_div']";

				} else {
					workSpaceXpath = "//div[@class='content_div']";
				}
			} 
			
			if (workSpaceXpath !=null) {
			if (workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
				docNameXpath = workSpaceXpath + "//span[contains(@id,'myGridfundr-cell-5-')]";
			}
			else {
				docNameXpath = workSpaceXpath + "//span[contains(@id,'myGrid-cell-5-')]";
			}
			}
		
		return FindElements(driver, docNameXpath, "document name list on : "+pageName.toString()+" - WorkSpace - "+workspace.toString());
	
	}

	public List<WebElement> getContentGridDocNameList(Workspace workspace, PageName pageName) {

		String workSpaceXpath = "";
		String docNameXpath = "";

		if (pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())) {

			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath = "//div[@id='invworkspace']";

			} else if (workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
				workSpaceXpath = "//div[@id='frworkspace']";

			}
		} else if (pageName.toString().equalsIgnoreCase(PageName.InstitutionsPage.toString())
				|| pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {

			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath = "//div[@id='Investorgrid_div']";

			} else {
				workSpaceXpath = "//div[@id='divFrWorkspace']";
			}
		} else if (pageName.toString().equalsIgnoreCase(PageName.CommitmentsPage.toString())) {

			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath = "//div[@class='content_div']";

			} else {
				workSpaceXpath = "//div[@class='content_div']";
			}
		}
		if (!workSpaceXpath.isEmpty()) {

			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				docNameXpath = workSpaceXpath + "//span[contains(@id,'myGrid-cell-0-')]//a/u";
			} else {
				docNameXpath = workSpaceXpath + "//span[contains(@id,'myGridfundr-cell-0-')]//a/u";
			}
		}
		return FindElements(driver, docNameXpath,
				"document name list on : " + pageName.toString() + " - WorkSpace - " + workspace.toString());
	}
	
	public List<WebElement> getContentGridDocNameListOnBasisOfTitle(Workspace workspace, PageName pageName) {

		String workSpaceXpath = "";
		String docNameXpath = "";

		if (pageName.toString().equalsIgnoreCase(PageName.FundsPage.toString())) {

			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath = "//div[@id='invworkspace']";

			} else if (workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
				workSpaceXpath = "//div[@id='frworkspace']";

			}
		} else if (pageName.toString().equalsIgnoreCase(PageName.InstitutionsPage.toString())
				|| pageName.toString().equalsIgnoreCase(PageName.ContactsPage.toString())) {

			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath = "//div[@id='Investorgrid_div']";

			} else {
				workSpaceXpath = "//div[@id='divFrWorkspace']";
			}
		} else if (pageName.toString().equalsIgnoreCase(PageName.CommitmentsPage.toString())) {

			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				workSpaceXpath = "//div[@class='content_div']";

			} else {
				workSpaceXpath = "//div[@class='content_div']";
			}
		}
		if (!workSpaceXpath.isEmpty()) {

			if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())) {
				docNameXpath = workSpaceXpath + "//span[contains(@id,'myGrid-cell-0-')]//a";
			} else {
				docNameXpath = workSpaceXpath + "//span[contains(@id,'myGridfundr-cell-0-')]//a";
			}
		}
		return FindElements(driver, docNameXpath,
				"document name list on : " + pageName.toString() + " - WorkSpace - " + workspace.toString());
	}
	
	public WebElement getContactAccessCancelBtn(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR_MA";
		} else {
			workspaceSelector = "INV_MA";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='ManageAccessPopIdBW"+workspaceSelector+"']//a[@title='Cancel']", "Workspace cancel Button", action.BOOLEAN, timeOut), "visibility", timeOut, "Workspace cancel Button");
	}
	
	public WebElement getDocumentDeleteYesBtn(Workspace workspace,int timeOut) {
		WebElement ele=null;
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
			ele=isDisplayed(driver, FindElement(driver, "(//div[contains(text(),'Confirm Deletion')])[1]/following-sibling::div/a[@title='Yes']","document delete yes button", action.BOOLEAN,timeOut), "visibility", timeOut,"document delete yes button");
		}else {
			ele=isDisplayed(driver, FindElement(driver, "(//div[contains(text(),'Confirm Deletion')])[2]/following-sibling::div/a[@title='Yes']","document delete yes button", action.BOOLEAN,timeOut), "visibility", timeOut,"document delete yes button");
		}
		return ele;
	}
	
	public WebElement getDocumentDeleteNoBtn(Workspace workspace,int timeOut) {
		WebElement ele=null;
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
			ele=isDisplayed(driver, FindElement(driver, "(//div[contains(text(),'Confirm Deletion')])[1]/following-sibling::div/a[@title='No']","document delete no button", action.BOOLEAN,timeOut), "visibility", timeOut,"document delete no button");
		}else {
			ele=isDisplayed(driver, FindElement(driver, "(//div[contains(text(),'Confirm Deletion')])[2]/following-sibling::div/a[@title='No']","document delete no button", action.BOOLEAN,timeOut), "visibility", timeOut,"document delete no button");
		}
		return ele;
	}
	
	
	
	
	public WebElement getWorkSpaceCloseBtn(Workspace workspace,int timeOut) {
		String workSpaceSelector="";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
			workSpaceSelector="fr";
		}else {
			workSpaceSelector="inv";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='"+workSpaceSelector+"workspace']//a[@title='Close Workspace']","workspace close button", action.BOOLEAN,timeOut), "visibility", timeOut,"workspace close button");
	}
	
	public WebElement getWorkSpaceClearBtn(Workspace workspace,int timeOut) {
		String workSpaceSelector="";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
			workSpaceSelector="fr";
		}else {
			workSpaceSelector="inv";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='"+workSpaceSelector+"workspace']//a[@title='Clear Workspace']","workspace clear button", action.BOOLEAN,timeOut), "visibility", timeOut,"workspace close button");
	}
	
	@FindBy(xpath="//div[@id='create_savebtn']/a[@title='Yes']")
	private WebElement CloseAndClearWorkSpaceYesBtn;
	
/**
	 * @return the closeworkSpaceYesBtn
	 */
	public WebElement getCloseAndClearWorkSpaceYesBtn(int timeOut) {
		return isDisplayed(driver, CloseAndClearWorkSpaceYesBtn, "Visibility", timeOut, "close workspace yes button");
	}

/***********************************************online import******************************************/	
	@FindBy(xpath="(//a[@title='Online Import'])[1]")
	private WebElement fundRaisingWorkSpaceOnlineImportLink;
	
	@FindBy(xpath="(//a[@title='Online Import'])[2]")
	private WebElement investorWorkSpaceOnlineImportLink;
	
	/**
	 * @return the onlineImportLink1
	 */
	public WebElement getOnlineImportLink(Workspace workspace,int timeOut) {
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {			
			return isDisplayed(driver,fundRaisingWorkSpaceOnlineImportLink, "Visibility", timeOut, "Online Import Link");
		}else {
			return isDisplayed(driver,investorWorkSpaceOnlineImportLink, "Visibility", timeOut, "Online Import Link");
		}
	}
	
	@FindBy(id="login")
	private WebElement boxUserName;

	/**
	 * @return the boxUserName
	 */
	public WebElement getBoxUserName(int timeOut) {
		return isDisplayed(driver, boxUserName, "Visibility", timeOut, "Box username text box");
	}
	
	@FindBy(id="password")
	private WebElement boxPasswordTextBox;

	/**
	 * @return the boxPasswordTextBox
	 */
	public WebElement getBoxPasswordTextBox(int timeOut) {
		return isDisplayed(driver, boxPasswordTextBox, "Visibility", timeOut, "Box Password text box");
	}
	
	@FindBy(xpath="//input[@title='Authorize']")
	private WebElement boxAuthorizeButton;

	/**
	 * @return the boxAuthorizeButton
	 */
	public WebElement getBoxAuthorizeButton(int timeOut) {
		return isDisplayed(driver, boxAuthorizeButton, "Visibility", timeOut, "Box authorize Button");
	}
	
	@FindBy(id="consent_reject_button")
	private WebElement boxSSODenyLink;

	/**
	 * @return the boxSSODenyLink
	 */
	public WebElement getBoxSSODenyLink(int timeOut) {
		return isDisplayed(driver, boxSSODenyLink, "Visibility", timeOut, "SSO Deny Link");
	}
	
	@FindBy(id="consent_accept_button")
	private WebElement grantAccessToBoxButton;

	/**
	 * @return the grantAccessToBoxButton
	 */
	public WebElement getGrantAccessToBoxButton(int timeOut) {
			return isDisplayed(driver, grantAccessToBoxButton, "Visibility", timeOut, "Grant Access To Box Button");
	}
	
	@FindBy(id="Button2")
	private WebElement onlineImportCancelButton;

	/**
	 * @return the onlineImportCancelButton
	 */
	public WebElement getOnlineImportCancelButton(int timeOut) {
		return isDisplayed(driver, onlineImportCancelButton, "Visibility", timeOut, "Online Import cancel Button");
	}
	
	@FindBy(xpath="//a[@id='lbtOnlinImportSave']")
	private WebElement importButton;

	/**
	 * @return the importButton
	 */
	public WebElement getImportButton(int timeOut) {
		return isDisplayed(driver, importButton, "Visibility", timeOut, "Import Button");
	}
	
	@FindBy(id="lblselectFolderName")
	private WebElement OnlineImportSelectedFolderName;

	/**
	 * @return the onlineImportSelectedFolderName
	 */
	public WebElement getOnlineImportSelectedFolderName(int timeOut) {
		return isDisplayed(driver, OnlineImportSelectedFolderName, "Visibility", timeOut, "Online Import Selected Folder name");
	}
	
	@FindBy(xpath="(//div[text()='Confirmation '])[2]/following-sibling::div[1]")
	private WebElement onlineImportConfirmationMessage;

	/**
	 * @return the onlineImportConfirmationMessage
	 */
	public WebElement getOnlineImportConfirmationMessage(int timeOut) {
		return isDisplayed(driver, onlineImportConfirmationMessage, "Visibility", timeOut, "online Import Confirmation Message.");
	}
	
	@FindBy(xpath="(//div[text()='Confirmation '])[2]/following-sibling::div[2]/input")
	private WebElement onlineImportConfirmationMessageCloseButton;

	/**
	 * @return the onlineImportConfirmationMessageCloseButton
	 */
	public WebElement getOnlineImportConfirmationMessageCloseButton(int timeOut) {
		return isDisplayed(driver, onlineImportConfirmationMessageCloseButton, "Visibility", timeOut, "Online Import confirmation Pop Up Close Button.");
	}
	
	@FindBy(xpath="//a[@id='btnNext']")
	private WebElement fWR_uploadNextBtn;
	
	@FindBy(xpath="//a[@id='btnNextInvestor']")
	private WebElement INV_uploadNextBtn;

	/**
	 * @return the uploadNextButton
	 */
	public WebElement getUploadNextButton(Workspace workspace,int timeOut) {
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
			return isDisplayed(driver, fWR_uploadNextBtn, "Visibility", timeOut, "Upload Window Next Button");			
		}else {
			return isDisplayed(driver, INV_uploadNextBtn, "Visibility", timeOut, "Upload Window Next Button");	
		}
	}
	
	@FindBy(id="lnkReplaceAll")
	private WebElement updateAllButton;

	/**
	 * @return the updateAllButton
	 */
	public WebElement getUpdateAllButton(int timeOut) {
		return isDisplayed(driver, updateAllButton, "Visibility", timeOut, "updateAll Button");
	}
	
	@FindBy(xpath="//input[@id='rbtMultipleInstitution']")
	private WebElement ImportOnlineaddMultipleRadioBtn;
	
	/**
	 * @return the importOnlineaddMultipleRadioBtn
	 */
	public WebElement getImportOnlineaddMultipleRadioBtn(int timeOut) {
		return isDisplayed(driver, ImportOnlineaddMultipleRadioBtn, "Visibility", timeOut, "import online add multiple radio button");
	}
	
	/********************************upload window************************************************/
	
	@FindBy(xpath = "//a[@title='Ignore All']")
	private WebElement ignoreAllButton;
	
	
	/**
	 * @return the ignoreAllButton
	 */
	public WebElement getIgnoreAllButton(int timeOut) {
		return isDisplayed(driver, ignoreAllButton, "Visibility", timeOut, "ignore all button");
	}

	@FindBy(xpath = "//div[contains(text(),'Upload Document to Folder')]")
	private WebElement uploadDocumentToFolderTextLabel;
	
	
	
	
	/**
	 * @return the uploadDocumentToFolderTextLabel
	 */
	public WebElement getUploadDocumentToFolderTextLabel(int timeOut) {
		return isDisplayed(driver, uploadDocumentToFolderTextLabel, "Visibility", timeOut, "Upload document to folder text present on upload window");
	}
	@FindBy(xpath = "//img[contains(@src,'Clear-Icon')]")
	private WebElement clearIconUpload;
	
	
	/**
	 * @return the clearIconUpload
	 */
	public WebElement getClearIconUploadWindow(int timeOut) {
		return isDisplayed(driver, clearIconUpload, "Visibility", timeOut, "clear icon on upload window");
	}
	@FindBy(xpath = "//div[@id='pnlSelectedInstitutions']//a[@title='Cancel']")
	private WebElement uploadWindowCancelBtn;
	
	
	/**
	 * @return the uploadWindowCancelBtn
	 */
	public WebElement getUploadWindowCancelBtn(int timeOut) {
		return isDisplayed(driver, uploadWindowCancelBtn, "Visibility", timeOut, "Upload window cancel button");
	}
	
	@FindBy(xpath = "//div[@id='msgInfo']")
	private WebElement fileDist2Of2Info;
	
	
	/**
	 * @return the fileDist2Of2Info
	 */
	public WebElement getFileDist2Of2Info(int timeOut) {
		return isDisplayed(driver, fileDist2Of2Info, "Visibility", timeOut, "file Dist 2 Of 2 Info");
	}

	@FindBy(xpath = "//div[@id='pnlFileDistributor']/div/div")
	private WebElement fileDistributorHead;
	
	
	/**
	 * @return the fileDistributorHead
	 */
	public WebElement getFileDistributorHead(int timeOut) {
		return isDisplayed(driver, fileDistributorHead, "Visibility", timeOut, "File Distributor Head");
	}
	@FindBy(xpath = "//div[@id='pnlFileDistributor']//..//a[@title='Cancel']")
	private WebElement fileDistributorCancelBtn;
	
	

	/**
	 * @return the fileDistributorCancelBtn
	 */
	public WebElement getFileDistributorCancelBtn(int timeOut) {
		return isDisplayed(driver, fileDistributorCancelBtn, "Visibility", timeOut, "File Distributor Cancel Btn");
	}
	
	public List<WebElement> getAllFilesInSuccessFullyAppliedFileDist2Of2() {
		return FindElements(driver, "//ul[@class='decimal']//li", "all files present in successfully applied column");
	}
	@FindBy(xpath = "//div[@id='divpaginationstyle']//..//a[@title='Cancel']")
	private WebElement fileDist2Of2CancelBtn;
	
	
	/**
	 * @return the fileDist2Of2CancelBtn
	 */
	public WebElement getFileDist2Of2CancelBtn(int timeOut) {
		return isDisplayed(driver, fileDist2Of2CancelBtn, "Visibility", timeOut, "file dist 2 of 2 cancel button");
	}
	@FindBy(xpath = "//div[@id='divpaginationstyle']//..//a[@title='Apply']")
	private WebElement fileDist2Of2ApplyBtn;
	
	
	/**
	 * @return the fileDist2Of2ApplyBtn
	 */
	public WebElement getFileDist2Of2ApplyBtn(int timeOut) {
		return isDisplayed(driver, fileDist2Of2ApplyBtn, "Visibility", timeOut, "file dist 2 of 2 apply btn");
	}
	
	@FindBy(xpath = "//div[contains(text(),'2b of 2')]/..//div//a[@title='Apply']")
	private WebElement applyButtonFileDist2bOf2;
	
	
	/**
	 * @return the applyButtonFileDist2bOf2
	 */
	public WebElement getApplyButtonFileDist2bOf2(int timeOut) {
		return isDisplayed(driver, applyButtonFileDist2bOf2, "Visibility", timeOut, "Apply Button File Dist 2bOf2");
	}

	@FindBy(xpath = "//div[contains(text(),'2b of 2')]/..//div//a[@title='Back']")
	private WebElement backButtonFileDist2bOf2;
	
	
	/**
	 * @return the backButtonFileDist2bOf2
	 */
	public WebElement getBackButtonFileDist2bOf2(int timeOut) {
		return isDisplayed(driver, backButtonFileDist2bOf2, "Visibility", timeOut, "BackButtonFileDist2bOf2");
	}
	@FindBy(xpath = "//div[contains(text(),'2b of 2')]/..//div//a[@title='Cancel']")
	private WebElement fileDist2bOf2CancelBtn;
	
	
	/**
	 * @return the fileDist2bOf2CancelBtn
	 */
	public WebElement getFileDist2bOf2CancelBtn(int timeOut) {
		return isDisplayed(driver, fileDist2bOf2CancelBtn, "Visibility", timeOut, "FileDist2bOf2CancelBtn");
	}

	@FindBy(xpath = "//div[@id='divpaginationstyle']//a[@title='Back']")
	private WebElement fileDist2Of2BackBtn;
	
	
	/**
	 * @return the fileDist2Of2BackBtn
	 */
	public WebElement getFileDist2Of2BackBtn(int timeOut) {
		return isDisplayed(driver, fileDist2Of2BackBtn, "Visibility", timeOut, "FileDist2Of2BackBtn");
	}

	@FindBy(xpath = "//div[contains(text(),'File Distributor')]/../..//table//a[@title='Back']")
	private WebElement fileDistBackBtn;
	
	
	/**
	 * @return the fileDistBackBtn
	 */
	public WebElement getFileDistBackBtn(int timeOut) {
		return isDisplayed(driver, fileDistBackBtn, "Visibility", timeOut, "FileDistBackBtn");
	}
	
	@FindBy(xpath = "//div[@id='BulkDocument_step_2of2']//div[contains(text(),'File Distributor (Step 2 of 2)')]")
	private WebElement fileDistHead2Of2;
	
	
	/**
	 * @return the fileDist2Of2
	 */
	public WebElement getFileDist2Of2(int timeOut) {
		return isDisplayed(driver, fileDistHead2Of2, "Visibility", timeOut, "file Dist Head 2 Of 2");
	}
	
	@FindBy(xpath = "//ul[@id='issueui']/li")
	private WebElement issueUiOnFileDistributor;
	
	
	/**
	 * @param list TODO
	 * @return the issueUiOnFileDistributor
	 */
	public List<WebElement> getIssueUiOnFileDistributor(int timeOut, boolean list) {
		if(list)
			return FindElements(driver, "//ul[@id='issueui']/li","IssueUiOnFileDistributor");	
		else
			return FindElements(driver, "//ul[@id='issueui']","IssueUiOnFileDistributor");	
	}

	@FindBy(xpath = "//div[@id='DisplayInvestorName']/ul/li")
	private WebElement successfullyAppliedOnFileDistributor;
	
	
	/**
	 * @return the successfullyApplied
	 */
	public WebElement getSuccessfullyAppliedOnFileDistributor(int timeOut) {
		return isDisplayed(driver, successfullyAppliedOnFileDistributor, "Visibility", timeOut, "");
	}

	@FindBy(xpath = "//a[@id='resolve_enable']")
	private WebElement resolveIssuesFileDist2Of2;
	
	
	/**
	 * @return the resolveIssuesFileDist2Of2
	 */
	public WebElement getResolveIssuesFileDist2Of2(int timeOut) {
		return isDisplayed(driver, resolveIssuesFileDist2Of2, "Visibility", timeOut, "Resolve Issues Button File Dist 2Of2");
	}
	@FindBy(xpath = "//div[@id='Unassigned_Filesdiv']//div")
	private WebElement unassignedFileNamesFileDist2bOf2;
	
	
	/**
	 * @return the unassignedFileNamesFileDist2bOf2
	 */
	public WebElement getUnassignedFileNamesFileDist2bOf2(int timeOut) {
		return isDisplayed(driver, unassignedFileNamesFileDist2bOf2, "Visibility", timeOut, "UnassignedFileNamesFileDist2bOf2");
	}

	@FindBy(xpath = "//span[@id='lblDocumentInformation']")
	private WebElement fileDistributorDocInfo;
	
	
	/**
	 * @return the fileDistributorDocInfo
	 */
	public WebElement getFileDistributorDocInfo(int timeOut) {
		return isDisplayed(driver, fileDistributorDocInfo, "Visibility", timeOut, "FileDistributorDocInfo");
	}
	public WebElement getViewLinkForUnassignedFileName(String fileName) {
		return isDisplayed(driver, FindElement(driver, "//div[contains(text(),'"+fileName+"')]//a[@title='View']", "view link", action.BOOLEAN, 30), "visibility", 10, "view link");
	}
	
	public WebElement getSelectFolderLinkForUnassignedFileName(String fileName) {
		return isDisplayed(driver, FindElement(driver, "//div[contains(text(),'"+fileName+"')]//a[@title='Select Folder']", "view link", action.BOOLEAN, 30), "visibility", 10, "view link");
	}
	@FindBy(xpath = "//div[contains(text(),'File Distributor')]/../..//table//a[@title='Next']")
	private WebElement fileDistNextBtn;
	
	
	/**
	 * @return the fileDistNextBtn
	 */
	public WebElement getFileDistNextBtn(int timeOut) {
		return isDisplayed(driver, fileDistNextBtn, "Visibility", timeOut, "FileDistNextBtn");
	}

	@FindBy(xpath = "//div[@id='pnlFundraising']//..//a[@title='Cancel']")
	private WebElement uploadStandard1stCancel;
	
	
	/**
	 * @return the uploadStandardCancel
	 */
	public WebElement getUploadStandard1stCancel(int timeOut) {
		return isDisplayed(driver,uploadStandard1stCancel, "Visibility", timeOut, "upload window standard folder cancel button");
	}

	public List<WebElement> standardFolderUploadWindowLabels(){
		return FindElements(driver, "//label", "standard upload window options");
	}
	@FindBy(xpath = "//input[@id='GridViewInstitutions_CheckBox1_0']")
	private WebElement uploadWindowStandardInst1Checkbox;
	@FindBy(xpath = "//input[@id='GridViewInstitutions_CheckBox1_1']")
	private WebElement uploadWindowStandardInst2Checkbox;
	/**
	 * @return the uploadWindowStandardInst2Checkbox
	 */
	public WebElement getUploadWindowStandardInst2Checkbox(int timeOut) {
		return isDisplayed(driver, uploadWindowStandardInst2Checkbox, "Visibility", timeOut, "upload window for standard folder checkbox institution 2");
	}

	/**
	 * @return the uploadWindowStandardInst1Checkbox
	 */
	public WebElement getUploadWindowStandardInst1Checkbox(int timeOut) {
		return isDisplayed(driver, uploadWindowStandardInst1Checkbox, "Visibility", timeOut, "upload window for standard folder checkbox institution 1");
	}
	
	@FindBy(xpath = "//input[contains(@id,'checkAll')]")
	private WebElement checkAllInstitutions;
	
	
	/**
	 * @return the checkAllInstitutions
	 */
	public WebElement getCheckAllInstitutions(int timeOut) {
		return isDisplayed(driver, checkAllInstitutions, "Visibility", timeOut, "check All Institutions");
	}
	
	@FindBy(xpath = "//input[@id='TVInstcheckAll']")
	private WebElement checkAllInstitutionsAndLimitedPartner;
	
	
	/**
	 * @return the checkAllInstitutions
	 */
	public WebElement getCheckAllInstitutionsAndLimitedPartner(int timeOut) {
		return isDisplayed(driver, checkAllInstitutionsAndLimitedPartner, "Visibility", timeOut, "check All Institutions And Limited Partner");
	}


	@FindBy(xpath = "//input[@id='rbtMultipleInstitution']")
	private WebElement multipleInstitutionsRbt;
	
	
	/**
	 * @return the multipleInstitutionsRbt
	 */
	public WebElement getMultipleInstitutionsRbt(int timeOut) {
		return isDisplayed(driver, multipleInstitutionsRbt, "Visibility", timeOut, "multiple institutions radio button on upload window");
	}
	
	@FindBy(xpath = "//div[contains(text(),'Duplicate Documents')]")
	private WebElement duplicateDocumentsHeadText;
	/**
	 * @return the duplicateDocumentsHeadText
	 */
	public WebElement getDuplicateDocumentsHeadText(int timeOut) {
		return isDisplayed(driver, duplicateDocumentsHeadText, "Visibility", timeOut, "duplicate documents heading  on upload window");
	}
	@FindBy(xpath = "//th[contains(text(),'Duplicate Documents')]")
	private WebElement duplicateDocumentsSubHeadText;

	/**
	 * @return the duplicateDocumentsSubHeadText
	 */
	public WebElement getDuplicateDocumentsSubHeadText(int timeOut) {
		return isDisplayed(driver, duplicateDocumentsSubHeadText, "Visibility", timeOut, "duplicate documents sub head text on upload window");
	}
	@FindBy(xpath = "//th[contains(text(),'Folder Location')]")
	private WebElement folderLocationSubHead;
	
	
	/**
	 * @return the folderLocationSubHead
	 */
	public WebElement getFolderLocationSubHead(int timeOut) {
		return isDisplayed(driver, folderLocationSubHead, "Visibility", timeOut, "folder location sub heading text on upload window");
	}
	@FindBy(xpath = "//p[contains(text(),'Some of the Documents')]")
	private WebElement duplicateDocumentsTextUnderHeading;
	
	
	/**
	 * @return the duplicateDocumentsTextUnderHeading
	 */
	public WebElement getDuplicateDocumentsTextUnderHeading(int timeOut) {
		return isDisplayed(driver, duplicateDocumentsTextUnderHeading, "Visibility", timeOut, "Some of the documents already exist text");
	}
	@FindBy(xpath = "//span[@id='GridViewDuplicateFiles_Label1_0']")
	private WebElement duplicateFileInFolder;
	
	
	/**
	 * @return the duplicateFileInFolder
	 */
	public WebElement getDuplicateFileInFolder(int timeOut) {
		return isDisplayed(driver, duplicateFileInFolder, "Visibility", timeOut, "duplicate files folder name");
	}

	@FindBy(xpath = "//span[@id='GridViewDuplicateFiles_Label2_0']")
	private WebElement duplicateDocumentValue;
	
	/**
	 * @return the duplicateDocumentValue
	 */
	public WebElement getDuplicateDocumentValue(int timeOut) {
		return isDisplayed(driver, duplicateDocumentValue, "Visibility", timeOut, "duplicate document name");
	}
	public List<WebElement> getDuplicateDocumentValueList(String duplicateDocumentsOrFolderLocation,int timeOut) {
		String xpath = "";
		if (duplicateDocumentsOrFolderLocation.equalsIgnoreCase("duplicate documents")) {
			xpath = "//span[contains(@id,'GridViewDuplicateFiles_Label2')]";
		}
		else {
			xpath = "//span[contains(@id,'GridViewDuplicateFiles_Label1')]";
		}
		return FindElements(driver, xpath, "list of duplicate documents");
	}
	
	/*************************************************down arrow*******************************/
	@FindBy(xpath="//a[@title='Open']")
	private WebElement openLinkContentGrid;

	/**
	 * @return the openLinkContentGrid
	 */
	public WebElement getOpenLinkContentGrid(int timeOut) {
		return isDisplayed(driver, openLinkContentGrid, "Visibility", timeOut, "Open Link");
	}
	
	@FindBy(xpath="//div[@id='Popupdata1']//a[@title='Manage Versions']")
	private WebElement fWR_manageVersionLinkContentGrid;
	
	@FindBy(xpath="//div[@id='Popupdata2']//a[@title='Manage Versions']")
	private WebElement INV_manageVersionLinkContentGrid;

	/**
	 * @return the manageVersionLinkContentGrid
	 */
	public WebElement getManageVersionLinkContentGrid(Workspace workspace,int timeOut) {
		WebElement ele=null;
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
			ele= isDisplayed(driver, fWR_manageVersionLinkContentGrid, "Visibility", timeOut, "fundraising workspace Manage Version Link");			
		}else {
			ele= isDisplayed(driver, INV_manageVersionLinkContentGrid, "Visibility", timeOut, "investor workspace Manage Version Link");
		}
		return ele;
	}
	public List<WebElement> getActionsColumnManageVersion() {
		return FindElements(driver, "//span[@id='grid12_DealDetail-box']//*[contains(text(),'Make Current')]", "actions column list on manage version popup");
	}
	public List<WebElement> getVersionColumnManageVersion() {
		return FindElements(driver, "//span[@id='grid12_DealDetail-box']//*[contains(text(),'Make Current')]/../..//span[3]", "version column list on manage version popup");
	}
	public List<WebElement> getDocumentNameColumnManageVersion() {
		return FindElements(driver, "//span[@id='grid12_DealDetail-box']//*[contains(text(),'Make Current')]/../..//span[4]", "document name column on manage version popup");
	}
	public List<WebElement> getDocumentNameColumnForSortingManageVersion() {
		return FindElements(driver, "//span[@id='grid12_DealDetail-box']//*[contains(text(),'Make Current')]/../..//span[4]/span", "document name column on manage version popup");
	}
	public List<WebElement> getCurrentColumnManageVersion() {
		return FindElements(driver, "//span[@id='grid12_DealDetail-box']//*[contains(text(),'Make Current')]/../..//span[5]", "current? column on manage version window");
	}
	public List<WebElement> getUploadedOnColumnManageVersion() {
		return FindElements(driver, "//span[@id='grid12_DealDetail-box']//*[contains(text(),'Make Current')]/../..//span[6]", "current? column on manage version window");
	}
	public List<WebElement> getUploadedByColumnManageVersion() {
		return FindElements(driver, "//span[@id='grid12_DealDetail-box']//*[contains(text(),'Make Current')]/../..//span[7]", "current? column on manage version window");
	}
	
	@FindBy(xpath="(//a[@title='Delete'])[2]")
	private WebElement deleteFileLinkContentGrid;

	/**
	 * @return the deleteFileLinkContentGrid
	 */
	public WebElement getDeleteFileLinkContentGrid(Workspace workspace,int timeOut) {
		BaseLib.PublicFlag = false;
		WebElement ele;
		ele = isDisplayed(driver, FindElement(driver, "(//a[@title='Delete'])[1]", "investor workspace doc delete link 1",
				action.BOOLEAN, 15), "visibility", 15, "investor workspace doc delete link 1");

		if (ele == null) {
			ele = isDisplayed(driver, FindElement(driver, "(//a[@title='Delete'])[2]",
					"investor workspace doc delete link 2", action.BOOLEAN, 15), "visibility", 15,
					"investor workspace doc delete link 2");

		}

		/*if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
			if ((pName == PageName.InstitutionsPage) || (pName == PageName.ContactsPage)){
				ele=isDisplayed(driver, FindElement(driver, "(//a[@title='Delete'])[1]", "fundraising workspace doc delete link", action.BOOLEAN, timeOut), "visibility", timeOut, "fundraising workspace doc delete link");
			}
			else {
			ele=isDisplayed(driver, FindElement(driver, "(//a[@title='Delete'])[2]", "fundraising workspace doc delete link", action.BOOLEAN, timeOut), "visibility", timeOut, "fundraising workspace doc delete link");
			}
		}else {
			ele=isDisplayed(driver, FindElement(driver, "(//a[@title='Delete'])[2]", "investor workspace doc delete link", action.BOOLEAN, timeOut), "visibility", timeOut, "investor workspace doc delete link");
		}*/
		return ele;
	}
	
	@FindBy(xpath="//*[@id='FUReplaceFile']")
	private WebElement chooseFileButton;

	/**
	 * @return the chooseFileButton
	 */
	public WebElement getChooseFileButton(int timeOut) {
		return isDisplayed(driver, chooseFileButton, "Visibility", timeOut, "Choose File Button");
	}
	
	@FindBy(xpath="//a[@title='Update']")
	private WebElement updateLinkContentGrid;

	/**
	 * @return the updateLinkContentGrid
	 */
	public WebElement getUpdateLinkContentGrid(int timeOut) {
		return isDisplayed(driver, updateLinkContentGrid, "Visibility", timeOut, "Update link");
	}
	
	@FindBy(xpath="//input[@title='Update']")
	private WebElement updateButtonOnManageVersionPopUp;

	/**
	 * @return the updateButtonOnManageVersionPopUp
	 */
	public WebElement getUpdateButtonOnManageVersionPopUp(int timeOut) {
		return isDisplayed(driver, updateButtonOnManageVersionPopUp, "Visibility", timeOut, "Update Button");
	}
	@FindBy(xpath = "//span[text()='Version']")
	private WebElement versionColumnName;
	
	
	/**
	 * @return the versionColumnName
	 */
	public WebElement getVersionColumnName(int timeOut) {
		return isDisplayed(driver, versionColumnName, "Visibility", timeOut, "version column name");
	}
	@FindBy(xpath = "//div[contains(text(),'Manage Version')]/..//span[text()='Uploaded By']")
	private WebElement uploadedByElementManageVersion;
	
	
	/**
	 * @return the uploadedByElementManageVersion
	 */
	public WebElement getUploadedByElementManageVersion(int timeOut) {
		return isDisplayed(driver, uploadedByElementManageVersion, "Visibility",
				timeOut, "uploaded by column head");
	}

	@FindBy(xpath = "//div[contains(text(),'Manage Version')]/..//span[text()='Document Name']")
	private WebElement documentNameElementManageVersion;
	
	public WebElement getdocumentNameElementManageVersion(int timeOut) {
		return isDisplayed(driver, documentNameElementManageVersion, "Visibility", timeOut, "document name column");
	}
	@FindBy(xpath = "//div[contains(text(),'Manage Version')]/..//span[text()='Current?']")
	private WebElement currentColumnManageVersion;
	
	public WebElement getCurrentColumnManageVersion(int timeOut) {
		return isDisplayed(driver, currentColumnManageVersion, "Visibility", timeOut, "current column manage version");
	}
	@FindBy(xpath = "//div[contains(text(),'Manage Version')]/..//span[text()='Uploaded On']")
	private WebElement uploadedOnColumnName;
	
	
	/**
	 * @return the uploadedOnColumnName
	 */
	public WebElement getUploadedOnColumnName(int timeOut) {
		return isDisplayed(driver, uploadedOnColumnName, "Visibility", timeOut, "uploaded on column name on manage version");
	}

	@FindBy(xpath="//a[@title='All Investors']")
	private WebElement allInvestorButton;

	/**
	 * @return the allTargetButton
	 */
	public WebElement getAllInvestorButton(int timeOut) {
		return isDisplayed(driver, allInvestorButton, "Visibility", timeOut, "All Target Button");
	}
	
	@FindBy(xpath="//a[@title='This Investor Only']")
	private WebElement thisInvestorOnlyButton;

	/**
	 * @return the thisTargetOnlyButton
	 */
	public WebElement getThisInvestorOnlyButton(int timeOut) {
		return isDisplayed(driver, thisInvestorOnlyButton, "Visibility", timeOut, "This target only button");
	}
	
	@FindBy(id="Message")
	private WebElement selectDocumentErrorMsg;

	/**
	 * @return the selectDocumentErrorMsg
	 */
	public WebElement getSelectDocumentErrorMsg(int timeOut) {
		return isDisplayed(driver, selectDocumentErrorMsg, "Visibility", timeOut, "Select Document Error Message");
	}
	@FindBy(xpath = "//div[contains(text(),'Manage Versions')]")
	private WebElement manageVersionHead;
	
	
	/**
	 * @return the multipleInstancesHead
	 */
	public WebElement getManageVersionHead(int timeOut) {
		return isDisplayed(driver, manageVersionHead, "Visibility", timeOut, "manage version heading on funds page");
	}

	@FindBy(xpath="//div[text()='Manage Versions ']/a")
	private WebElement manageVersionsPopUpCrossIcon;

	/**
	 * @return the manageVersionsPopUpButton
	 */
	public WebElement getManageVersionsPopUpCrossIcon(int timeOut) {
		return isDisplayed(driver, manageVersionsPopUpCrossIcon, "Visibility", timeOut, "Manage Versions Pop up Cross Icon");
	}
	
	@FindBy(xpath="//div[text()='Manage Versions ']/..//input[@value='Close']")
	private WebElement manageVersionPopUpCloseButton;

	/**
	 * @return the manageVersionPopUpCloseButton
	 */
	public WebElement getManageVersionPopUpCloseButton(int timeOut) {
		return isDisplayed(driver, manageVersionPopUpCloseButton, "Visibility", timeOut, "manage versions pop up close button");
	}
	@FindBy(xpath = "//span[@title='Refresh']")
	private WebElement manageVersionRefresh;
	
	
	/**
	 * @return the manageVersionRefresh
	 */
	public WebElement getManageVersionRefresh(int timeOut) {
		return isDisplayed(driver, manageVersionRefresh, "Visibility", timeOut, "manage version refresh button");
	}

	/**********************update window**************************/
	@FindBy(xpath = "//div[@id='subFolderHeading']")
	private WebElement updateWindowHeading;
	
	
	/**
	 * @return the updateWindowHeading
	 */
	public WebElement getUpdateWindowHeading(int timeOut) {
		return isDisplayed(driver, updateWindowHeading, "Visibility", timeOut, "update window heading");
	}
	@FindBy(xpath = "//div[contains(@class,'contacts_n_name_div')]/..//strong")
	private WebElement updatingTextLabel;
	
	
	/**
	 * @return the updatingTextLabel
	 */
	public WebElement getUpdatingTextLabel(int timeOut) {
		return isDisplayed(driver, updatingTextLabel, "Visibility", timeOut, "updating text label");
	}
	@FindBy(xpath = "//span[@id='lbl_fileName']")
	private WebElement fileNameOnUpdateWindow;
	
	

	/**
	 * @return the fileNameOnUpdateWindow
	 */
	public WebElement getFileNameOnUpdateWindow(int timeOut) {
		return isDisplayed(driver, fileNameOnUpdateWindow, "Visibility", timeOut, "file name on update window");
	}
	@FindBy(xpath = "//input[@id='FUReplaceFile']")
	private WebElement browseButtonUpdateWindow;
	
	
	/**
	 * @return the browseButtonUpdateWindow
	 */
	public WebElement getBrowseButtonUpdateWindow(int timeOut) {
		return isDisplayed(driver, browseButtonUpdateWindow, "Visibility", timeOut, "browse button update window");
	}
	@FindBy(xpath = "//a[@id='LinkButton1']")
	private WebElement updateButtonUpdateWindow;
	
	
	/**
	 * @return the updateButtonUpdateWindow
	 */
	public WebElement getUpdateButtonUpdateWindow(int timeOut) {
		return isDisplayed(driver, updateButtonUpdateWindow, "Visibility", timeOut, "update button on update window");
	}
	@FindBy(xpath = "//div[@id='Div2']")
	private WebElement multipleInstancesHead;
	
	
	/**
	 * @return the multipleInstancesHead
	 */
	public WebElement getMultipleInstancesHead(int timeOut) {
		return isDisplayed(driver, multipleInstancesHead, "Visibility", timeOut, "multiple instances heading");
	}
	@FindBy(xpath = "//div[contains(text(),'Are you updating')]")
	
	private WebElement thisOrAllInvestorText;
	
	
	/**
	 * @return the thisOrAllInvestorText
	 */
	public WebElement getThisOrAllInvestorText(int timeOut) {
		return isDisplayed(driver, thisOrAllInvestorText, "Visibility", timeOut, "select this or all investor text on update window");
	}

	/**********************************************************************Manage Emails*****************************************/
	
	@FindBy(xpath="//div[@id='frworkspace']//a[@title='Manage Emails']/img")
	private WebElement fWR_manageEmailIcon;
	
	@FindBy(xpath="//div[@id='invworkspace']//a[@title='Manage Emails']/img")
	private WebElement INV_manageEmailIcon;
	
	public WebElement getmanageEmails(Workspace workspace, int timeOut) {
		WebElement ele = null;
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			ele=isDisplayed(driver, fWR_manageEmailIcon, "Visibility", timeOut, workspace+"  manage emails icon");
		}else if(workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			ele=isDisplayed(driver, INV_manageEmailIcon, "Visibility", timeOut, workspace+" manage emails icon");
		}	
		return ele;
	}
	
	@FindBy(xpath="//select[@id='page:manageEmail:ManageEmailcompID:Manage_Email_Component:piclistvalid']")
	private WebElement manageEmailContactAccessViewDropDownList;

	/**
	 * @return the contactAccessViewDropDownList
	 */
	public WebElement getManageEmailContactAccessViewDropDownList(int timeOut) {
		return isDisplayed(driver, manageEmailContactAccessViewDropDownList, "Visibility", timeOut, "Manage emails contact access view drop down list");
	}
	
	@FindBy(xpath="//input[@id='page:manageEmail:ManageEmailcompID:Manage_Email_Component:searchcon_grid_ME']")
	private WebElement manageEmailSearchTextBox;

	/**
	 * @return the manageEmailsSearchTextBox
	 */
	public WebElement getManageEmailSearchTextBox(int timeOut) {
		return isDisplayed(driver, manageEmailSearchTextBox, "Visibility", timeOut, "manage emails search text box");
	}
	
	@FindBy(xpath="//div[@id='searchIddiv']/a")
	private WebElement manageEmailSearchBtn;

	/**
	 * @return the manageEmailSearchBtn
	 */
	public WebElement getManageEmailSearchBtn(int timeOut) {
		return isDisplayed(driver, manageEmailSearchBtn, "Visibility", timeOut, "manage email search button");
	}
	
	@FindBy(xpath="//a[@title='Send']")
	private WebElement manageEmailsendBtn;

	/**
	 * @return the manageEmailBtn
	 */
	public WebElement getmanageEmailsendBtn(int timeOut) {
		return isDisplayed(driver, manageEmailsendBtn, "Visibility", timeOut, "manage email send button");
	}
	
	@FindBy(xpath="//div[@id='confirmationpopup_emailID_ME']//a[@title='Yes']")
	private WebElement manageEmailYesBtn;

	/**
	 * @return the manageEmailYesButton
	 */
	public WebElement getManageEmailYesBtn(int timeOut) {
		return isDisplayed(driver, manageEmailYesBtn, "Visibility", timeOut, "manage email yes button");
	}
	
	@FindBy(xpath="//div[@id='confirmationpopup_emailID_ME']//a[@title='No']")
	private WebElement manageEmailNoBtn;

	/**
	 * @return the manageEmailNoBtn
	 */
	public WebElement getManageEmailNoBtn(int timeOut) {
		return isDisplayed(driver, manageEmailNoBtn, "Visibility", timeOut, "manage email no button");
	}
	
	@FindBy(xpath="//div[@id='parentdivId_ME']//a[@title='Cancel']")
	private WebElement manageEmailCancelBtn;

	/**
	 * @return the manageEmailCancelBtn
	 */
	public WebElement getManageEmailCancelBtn(int timeOut) {
		return isDisplayed(driver, manageEmailCancelBtn, "Visibility", timeOut, "manage email cancel buttton");
	}
	
	@FindBy(xpath="//div[@id='parentdivId_ME']//div[@class='head_popup']")
	private WebElement manageEmailsHeader;
	
	/**
	 * @return the manageEmailsHeader
	 */
	public WebElement getManageEmailsHeader(int timeOut) {
		return isDisplayed(driver, manageEmailsHeader, "Visibility", timeOut, "MAnageEmails Popup Header");
	}
	
	@FindBy(xpath="//div[@id='parentdivId_ME']//div[@id='clearsearchdsbEmail']//a[@title='Clear Search']")
	private WebElement manageEmailClearSearchIcon; 
		 

	/**
	 * @return the manageEmailClearSearchIcon
	 */
	public WebElement getManageEmailClearSearchIcon(int timeOut) {
		return isDisplayed(driver, manageEmailClearSearchIcon, "Visibility", timeOut, "Clear serach icon");
	}

	@FindBy(xpath="//div[@id='manageemailgrid_ME']//input")
	private WebElement getManageEmailSelectAllCheckBox; 
	
	/**
	 * @return the getManageEmailSelectAllCheckBox
	 */
	public WebElement getGetManageEmailSelectAllCheckBox(int timeOut) {
		return isDisplayed(driver, getManageEmailSelectAllCheckBox, "Visibility", timeOut, "Select All Checkbox");
	}
	
	@FindBy(xpath="//div[@id='manageemailgrid_ME']//span[contains(@class,'aw-row-0')]/span[3]/span")
	private WebElement contactAccessManageEmailGridMsg;

	/**
	 * @return the manageEmailGridMsg
	 */
	public WebElement getContactAccessManageEmailGridMsg(int timeOut) {
		return isDisplayed(driver, contactAccessManageEmailGridMsg, "Visibility", timeOut, "Manage Email Grid Msg");
		
	}
	
	@FindBy(xpath="//div[text()='Manage Emails']/a[@title='Close']")
	private WebElement manageEmailsCloseIcon;

	/**
	 * @return the manageEmailsCloseIcon
	 */
	public WebElement getManageEmailsCloseIcon(int timeOut) {
		return isDisplayed(driver, manageEmailsCloseIcon, "Visibility", timeOut, "Manage Emails Close Icon");
	}
	
	@FindBy(id="myRadio1_ME")
	private WebElement manageEmailInvitationEmailTemplateRadioBtn;

	/**
	 * @return the manageEmailInvitationEmailTemplateRadioBtn
	 */
	public WebElement getManageEmailInvitationEmailTemplateRadioBtn(int timeOut) {
		return isDisplayed(driver, manageEmailInvitationEmailTemplateRadioBtn, "Visibility", timeOut, "Manage Email Invitation  Template Radio Button");
	}

	public List<WebElement> getManageEmailInvitationEmailTemplateEditPreviewTextList(){
		return FindElements(driver, "//*[@id='myRadio1_ME']/..//a", "Manage Email Invitation Email Template Edit and Preview Text List");
	}
	
	public List<WebElement> getManageEmailCustomEmailTemplateEditPreviewTextList(){
		return FindElements(driver, "//*[@id='myRadio2_ME']/..//a", "Manage Email Custom Email Template Edit and Preview Text List");
	}
	
	@FindBy(xpath=".//*[@id='myRadio2_ME']")
	private WebElement manageEmailCustomRadioButton;

	/**
	 * @return the manageEmailCustomRadioButton
	 */
	public WebElement getManageEmailCustomRadioButton(int timeOut) {
		return isDisplayed(driver, manageEmailCustomRadioButton, "Visibility", timeOut, "Custom Email Template Radio Button");
	}
	
	@FindBy(xpath="//div[@id='custIdTemp']/..//a[@title='Cancel']")
	private WebElement manageEmailCustomTemplateCancelButton;
	
	/**
	 * @return the manageEmailCustomTemplateCancelButton
	 */
	public WebElement getManageEmailCustomTemplateCancelButton(int timeOut) {
		return isDisplayed(driver, manageEmailCustomTemplateCancelButton, "Visibility", timeOut, "Manage Email custom template cancel button");
	}
	
	@FindBy(xpath="//div[@id='EmailEditPreview_popupid_ME']//div[@class='head_popup']")
	private WebElement getManageEmailInvitationMailPreviewHeader;
	/**
	 * @return the getManageEmailInvitationMailPreviewHeader
	 */
	public WebElement getGetManageEmailInvitationMailPreviewHeader(int timeOut) {
		return isDisplayed(driver, getManageEmailInvitationMailPreviewHeader, "Visibility", timeOut, "Invitation email template preview header");
	}
	
	@FindBy(id="invitationpreviewID1")
	private WebElement manageEmailInvitaionEmailPreviewLink;

	/**
	 * @return the manageEmailInvitaionEmailPreviewLink
	 */
	public WebElement getManageEmailInvitaionEmailPreviewLink(int timeOut) {
		return isDisplayed(driver, manageEmailInvitaionEmailPreviewLink, "Visibility", timeOut, "Manage Email Invitation Email Preview Link");
	}
	
	@FindBy(xpath="//div[contains(@class,'invitationpreviewcls')]//td[contains(text(),'From')]/../td[2]/div")
	private WebElement manageEmailPreviewFormEmailIdText;

	/**
	 * @return the manageEmailPreviewFormEmailIdText
	 */
	public WebElement getManageEmailPreviewFormEmailIdText(int timeOut) {
		return isDisplayed(driver, manageEmailPreviewFormEmailIdText, "Visibility", timeOut, "Manage Email Preview Form Email Id Text");
	}
	
	@FindBy(xpath="//div[contains(@class,'invitationpreviewcls')]//td[contains(text(),'Subject')]/../td[2]")
	private WebElement manageEmailPreviewSubjectText;

	/**
	 * @return the manageEmailPreviewSubjectText
	 */
	public WebElement getManageEmailPreviewSubjectText(int timeOut) {
		return isDisplayed(driver, manageEmailPreviewSubjectText, "Visibility", timeOut, "Manage Emails Preview Subject Text");
	}
	
	@FindBy(xpath="//div[contains(@class,'invitationpreviewcls')]//tr[3]/td")
	private WebElement manageEmailPreviewHelloText;

	/**
	 * @return the manageEmailsPreviewHelloText
	 */
	public WebElement getManageEmailPreviewHelloText(int timeOut) {
		return isDisplayed(driver, manageEmailPreviewHelloText, "Visibility", timeOut, "Manage Emails Preview Hello Text");
	}
	
	@FindBy(xpath="//div[contains(@class,'invitationpreviewcls')]//tr[4]/td")
	private WebElement manageEmailPreviewGrantAccessText;

	/**
	 * @return the manageEmailPreviewGrantAccessText
	 */
	public WebElement getManageEmailPreviewGrantAccessText(int timeOut) {
		return isDisplayed(driver, manageEmailPreviewGrantAccessText, "Visibility", timeOut, "Manage Email Preview Grant Access Text");
	}
	
	@FindBy(xpath="//div[contains(@class,'invitationpreviewcls')]//tr[6]/td")
	private WebElement manageEmailPreviewRegisterNotRegisterText;

	/**
	 * @return the manageEmailPreviewRegisterNotRegisterText
	 */
	public WebElement getManageEmailPreviewRegisterNotRegisterText(int timeOut) {
		return isDisplayed(driver, manageEmailPreviewRegisterNotRegisterText, "Visibility", timeOut, "Manage Email Preview Register Not Register Text");
	}
	
	@FindBy(xpath="//div[contains(@class,'invitationpreviewcls')]//tr[7]/td")
	private WebElement manageEmailPreviewBottomText;

	/**
	 * @return the manageEmailPreviewBottomText
	 */
	public WebElement getManageEmailPreviewBottomText(int timeOut) {
		return isDisplayed(driver, manageEmailPreviewBottomText, "Visibility", timeOut, "Manage Emails Preiview Bottom text");
	}
	
	@FindBy(xpath="//div[@id='EmailEditPreview_popupid_ME']//a[@title='Close']")
	private WebElement manageEmailPreviewClosebtn;

	/**
	 * @return the manageEmailPreviewClosebtn
	 */
	public WebElement getManageEmailPreviewClosebtn(int timeOut) {
		return isDisplayed(driver, manageEmailPreviewClosebtn, "Visibility", timeOut, "Manage Email Preview Close Button");
	}
	
	@FindBy(xpath="//div[@id='EmailEditPreview_popupid_ME']//span[@title='Close']")
	private WebElement manageEmailPreviewCloseIcon;

	/**
	 * @return the manageEmailPreviewCloseIcon
	 */
	public WebElement getManageEmailPreviewCloseIcon(int timeOut) {
		return isDisplayed(driver, manageEmailPreviewCloseIcon, "Visibility", timeOut, "Manage Mail Preview Close Icon");
	}
	
	@FindBy(xpath="//div[contains(@class,'invitationpreviewcls')]//tr[6]/td/p//a[1]")
	private WebElement manageEmailPreviewNotRegisteredClickHereLink;

	/**
	 * @return the manageEmailPreviewNotRegisteredClickHereLink
	 */
	public WebElement getManageEmailPreviewNotRegisteredClickHereLink(int timeOut) {
		return isDisplayed(driver, manageEmailPreviewNotRegisteredClickHereLink, "Visibility", timeOut, "Manage Email Preview Not Registered Click here link");
	}
	
	@FindBy(xpath="//div[contains(@class,'invitationpreviewcls')]//tr[6]/td/p//a[2]")
	private WebElement manageEmailPreviewRegisteredClickHereLink;

	/**
	 * @return the manageEmailPreviewRegisteredClickHereLink
	 */
	public WebElement getManageEmailPreviewRegisteredClickHereLink(int timeOut) {
		return isDisplayed(driver, manageEmailPreviewRegisteredClickHereLink, "Visibility", timeOut, "Manage Email Preview Registered Click here link");
	}
	
	@FindBy(xpath="//*[@id='invitationEditID1']")
	private WebElement invitationEmailEditLinkInManageEmails;
	/**
	 * @return the invitationEmailEditLinkInManageEmails
	 */
	public WebElement getInvitationEmailEditLinkInManageEmails(int timeOut) {
		return isDisplayed(driver, invitationEmailEditLinkInManageEmails, "Visibility", timeOut, "Invitation Email Edit Link In Manage Emails");
	}
	
	@FindBy(xpath="//div[contains(@class,'invitationedittemp')]//td[contains(text(),'From')]/../td[2]")
	private WebElement manageEmailEditFormEmailIdtext;

	/**
	 * @return the manageEmailEditFormEmailIdtext
	 */
	public WebElement getManageEmailEditFormEmailIdtext(int timeOut) {
		return isDisplayed(driver, manageEmailEditFormEmailIdtext, "Visibility", timeOut, "Manage Email Edit Template Form Email Id Text");
	}
	@FindBy(xpath="//div[contains(@class,'invitationedittemp')]//td[contains(text(),'Subject')]/../td[2]")
	private WebElement manageEmailEditSubjectText;

	/**
	 * @return the manageEmailEditSubjectText
	 */
	public WebElement getManageEmailEditSubjectText(int timeOut) {
		return isDisplayed(driver, manageEmailEditSubjectText, "Visibility", timeOut, "Manage Email Edit Template Subject Text");
	}
	@FindBy(xpath="//div[contains(@class,'invitationedittemp')]//tr[3]/td")
	private WebElement manageEmailEditHelloText;

	/**
	 * @return the manageEmailEditHelloText
	 */
	public WebElement getManageEmailEditHelloText(int timeOut) {
		return isDisplayed(driver, manageEmailEditHelloText, "Visibility", timeOut, "Manage Email Edit Template Hello Text");
	}
	@FindBy(xpath="//div[contains(@class,'invitationedittemp')]//tr[4]/td")
	private WebElement getManageEmailEditGrantAccessText;

	/**
	 * @return the getManageEmailEditGrantAccessText
	 */
	public WebElement getGetManageEmailEditGrantAccessText(int timeOut) {
		return isDisplayed(driver, getManageEmailEditGrantAccessText, "Visibility", timeOut, "Manage Email Grant Access Text");
	}
	@FindBy(xpath="//div[contains(@class,'invitationedittemp')]//tr[8]/td")
	private WebElement manageEmailEditRegisterNotRegisterText;

	/**
	 * @return the manageEmailEditRegisterNotRegisterText
	 */
	public WebElement getManageEmailEditRegisterNotRegisterText(int timeOut) {
		return isDisplayed(driver, manageEmailEditRegisterNotRegisterText, "Visibility", timeOut, "Manage Email Edit Template Register Not Register Text");
	}
	@FindBy(xpath="//div[contains(@class,'invitationedittemp')]//tr[9]/td")
	private WebElement manageEmailEditBottomText;

	/**
	 * @return the manageEmailEditBottomText
	 */
	public WebElement getManageEmailEditBottomText(int timeOut) {
		return isDisplayed(driver, manageEmailEditBottomText, "Visibility", timeOut, "Manage Email Edit template Bottom text");
	}
	@FindBy(xpath="//div[contains(@class,'invitationedittemp')]//tr[8]/td/p/a[1]")
	private WebElement manageEmailEditNotRegisteredClickHereLink;

	/**
	 * @return the manageEmailEditNotRegisteredClickHereLink
	 */
	public WebElement getManageEmailEditNotRegisteredClickHereLink(int timeOut) {
		return isDisplayed(driver, manageEmailEditNotRegisteredClickHereLink, "Visibility", timeOut, "Manage Email Edit Template Not registered click here link");
	}
	@FindBy(xpath="//div[contains(@class,'invitationedittemp')]//tr[8]/td/p/a[2]")
	private WebElement manageEmailEditRegisteredClickHereLink;

	/**
	 * @return the manageEmailEditRegisteredClickHereLink
	 */
	public WebElement getManageEmailEditRegisteredClickHereLink(int timeOut) {
		return isDisplayed(driver, manageEmailEditRegisteredClickHereLink, "Visibility", timeOut, "Manage Email Edit Template registered click here link");
	}
	@FindBy(xpath=".//*[@id='EmailEdit_popupID_ME']//a[text()='Apply']")
	private WebElement manageEmailInvitationTextBoxApplyButton;

	/**
	 * @return the manageEmailInvitationTextBoxApplyButton
	 */
	public WebElement getManageEmailInvitationTextBoxApplyButton(int timeOut) {
		return isDisplayed(driver, manageEmailInvitationTextBoxApplyButton, "Visibility", timeOut, "Manage Email Invitation TextBox Apply Button");
	}
	@FindBy(xpath=".//*[@id='EmailEdit_popupID_ME']//a[text()='Cancel']")
	private WebElement manageEmailInvitationTextBoxCancelButton;

	/**
	 * @return the manageEmailInvitationTextBoxCancelButton
	 */
	public WebElement getManageEmailInvitationTextBoxCancelButton(int timeOut) {
		return isDisplayed(driver, manageEmailInvitationTextBoxCancelButton, "Visibility", timeOut, "Manage Email Invitation TextBox Cancel Button");
	}
	@FindBy(xpath=".//*[@id='EmailEdit_popupID_ME']//span[@title='Close']")
	private WebElement manageEmailEditTemplateCloseIcon;

	/**
	 * @return the manageEmailEditTemplateCloseIcon
	 */
	public WebElement getManageEmailEditTemplateCloseIcon(int timeOut) {
		return isDisplayed(driver, manageEmailEditTemplateCloseIcon, "Visibility", timeOut, "Manage Email Edit Template Close Icon");
	}
	@FindBy(xpath=".//*[@id='invitetempmsgID_ME']")
	private WebElement manageEmailInvitationDescriptionTextBox;

	/**
	 * @return the manageEmailInvitationDescriptionTextBox
	 */
	public WebElement getManageEmailInvitationDescriptionTextBox(int timeOut) {
		return isDisplayed(driver, manageEmailInvitationDescriptionTextBox, "Visibility", timeOut, "Manage Email Invitation Description TextBox");
	}
	@FindBy(xpath="//div[contains(@class,'invitationpreviewcls')]//tr[5]/td//span")
	private WebElement manageEmailPreviewTextMassage;
	
	/**
	 * @return the manageEmailPreviewTextMassage
	 */
	public WebElement getManageEmailPreviewTextMassage(int timeOut) {
		return isDisplayed(driver, manageEmailPreviewTextMassage, "Visibility", timeOut, "Manage Email Preview Text Massage");
	}
	@FindBy(xpath="//span[@id='invitationbodyerrormsg']//span")
	private WebElement manageEmailInvitationemailEditErrorMessage;
	
	/**
	 * @return the manageEmailInvitationemailEditErrorMessage
	 */
	public WebElement getManageEmailInvitationemailEditErrorMessage(int timeOut) {
		return isDisplayed(driver, manageEmailInvitationemailEditErrorMessage, "Visibility", timeOut, "Manage Email Invitation EMail Edit Error Message");
	}
	
	@FindBy(xpath=".//*[@id='page:manageEmail:ManageEmailcompID:Manage_Email_Component:cust_subject_tempid']")
	private WebElement manageEmailCustomSubjectTextBox;

	/**
	 * @return the manageEmailCustomSubjectTextBox
	 */
	public WebElement getManageEmailCustomSubjectTextBox(int timeOut) {
		return isDisplayed(driver, manageEmailCustomSubjectTextBox, "Visibility", timeOut, "Manage Email Custom Subject TextBox");
	}
	
	@FindBy(xpath="//div[@id='custEdit_popupID_ME']//td[contains(text(),'From')]/../td[2]//div")
	private WebElement manageEmailCustomTemplateEditEmailIdText;

	/**
	 * @return the manageEmailCustomTemplateEditEmailIdText
	 */
	public WebElement getManageEmailCustomTemplateEditEmailIdText(int timeOut) {
		return isDisplayed(driver, manageEmailCustomTemplateEditEmailIdText, "Visibility", timeOut, "Manage Emails Custom Template Edit Template Form Email Id Text");
	}
	
	@FindBy(xpath="//iframe[contains(@title,'Rich Text')]")
	private WebElement manageEmailCustomSubjectBodyFrame;

	/**
	 * @return the manageEmailCustomSubjectBodyFrame
	 */
	public WebElement getManageEmailCustomSubjectBodyFrame(int timeOut) {
		return isDisplayed(driver, manageEmailCustomSubjectBodyFrame, "Visibility", timeOut, "Manage Email Custom Subject Body From");
	}
	
	@FindBy(xpath=".//*[@id='page:manageEmail:ManageEmailcompID:Manage_Email_Component:custtempid_rta_body']")
	private WebElement manageEmailCustomSubjectBody;

	/**
	 * @return the manageEmailCustomSubjectBody
	 */
	public WebElement getManageEmailCustomSubjectBody(int timeOut) {
		return isDisplayed(driver, manageEmailCustomSubjectBody, "Visibility", timeOut, "Manage Email Custom Subject Body");
	}
	@FindBy(xpath=".//*[@id='custEdit_popupID_ME']//a[text()='Apply']")
	private WebElement manageEmailCustomApplyButton;

	/**
	 * @return the manageEmailCustomApplyButton
	 */
	public WebElement getManageEmailCustomApplyButton(int timeOut) {
		return isDisplayed(driver, manageEmailCustomApplyButton, "Visibility", timeOut, "Manage Email Custom Apply Button");
	}
	
	@FindBy(xpath=".//*[@id='custEdit_popupID_ME']//a[text()='Cancel']")
	private WebElement manageEmailCustomCancelButton;

	/**
	 * @return the manageEmailCustomCancelButton
	 */
	public WebElement getManageEmailCustomTextBoxCancelButton(int timeOut) {
		return isDisplayed(driver, manageEmailCustomCancelButton, "Visibility", timeOut, "Manage Email Custom Cancel Button");
	}
	@FindBy(xpath="//div[@id='custEdit_popupID_ME']//span[@title='Close']")
	private WebElement manageEmailCustomCloseIcon;
	
	/**
	 * @return the manageEmailCustomCloseIcon
	 */
	public WebElement getManageEmailCustomCloseIcon(int timeOut) {
		return isDisplayed(driver, manageEmailCustomCloseIcon, "Visibility", timeOut, "Manage Email Custom Invitation Close Icon");
	}
	@FindBy(xpath="//span[@id='erroridforsubject']/span")
	private WebElement manageEmailEditSubjectErrorMessage;

	/**
	 * @return the manageEmailEditSubjectErrorMessage
	 */
	public WebElement getManageEmailEditSubjectErrorMessage(int timeOut) {
		return isDisplayed(driver, manageEmailEditSubjectErrorMessage, "Visibility", timeOut, "Manage Email Custom Template Edit Subject Error Message");
	}
	@FindBy(xpath="//*[@id='customEditID2']")
	private WebElement customEmailEditLinkInManageEmails;
	/**
	 * @return the customEmailEditLinkInManageEmails
	 */
	public WebElement getCustomEmailEditLinkInManageEmails(int timeOut) {
		return isDisplayed(driver, customEmailEditLinkInManageEmails, "Visibility", timeOut, "Custom Email Edit Link In Manage Emails");
	}
	@FindBy(id="customPreviewID2")
	private WebElement manageEmailCustomPreviewLink;
	
	/**
	 * @return the manageEmailCustomPreviewLink
	 */
	public WebElement getManageEmailCustomPreviewLink(int timeOut) {
		return isDisplayed(driver, manageEmailCustomPreviewLink, "Visibility", timeOut, "Manage Email Custom Invitation Preview Link");
	}
	@FindBy(xpath="//span[@id='erroridforbody']/span")
	private WebElement manageEmailCustomEditBodyErrorMessage;

	/**
	 * @return the manageEmailCustomEditBodyErrorMessage
	 */
	public WebElement getManageEmailCustomEditBodyErrorMessage(int timeOut) {
		return isDisplayed(driver, manageEmailCustomEditBodyErrorMessage, "Visibility", timeOut, "Manage Email Custom Email Template Body Error Message");
	}
	@FindBy(xpath="//div[contains(@class,'EmailCustomPreview_popup')]//td[contains(text(),'From')]/../td[2]/div")
	private WebElement manageEmailCustomPreviewFormEmailId;

	/**
	 * @return the manageEmailCustomPreviewFormEmailId
	 */
	public WebElement getManageEmailCustomPreviewFormEmailId(int timeOut) {
		return isDisplayed(driver, manageEmailCustomPreviewFormEmailId, "Visibility", timeOut, "Manage Email Custom Template Preview Form Email Id Text");
	}
	@FindBy(xpath="//div[contains(@class,'EmailCustomPreview_popup')]//td[contains(text(),'Subject')]/../td[2]/div/span")
	private WebElement manageEmailCustomTemplateSubjectText;

	/**
	 * @return the manageEmailCustomTemplateSubjectText
	 */
	public WebElement getManageEmailCustomTemplateSubjectText(int timeOut) {
		return isDisplayed(driver, manageEmailCustomTemplateSubjectText, "Visibility", timeOut, "Manage Email Custom Template Subject Text");
	}
	@FindBy(xpath="//div[contains(@class,'EmailCustomPreview_popup')]//tr[3]/td/span")
	private WebElement manageEmailCustomTemplateBodyText;

	/**
	 * @return the manageEmailCustomTemplateBodyText
	 */
	public WebElement getManageEmailCustomTemplateBodyText(int timeOut) {
		return isDisplayed(driver, manageEmailCustomTemplateBodyText, "Visibility", timeOut, "Manage Email Custom Template Bosy Text");
	}
	@FindBy(xpath="//div[contains(@class,'EmailCustomPreview_popup')]//a[@title='Close']")
	private WebElement manageEmailCustomPreviewCloseBtn;

	/**
	 * @return the manageEmailCustomPreviewCloseBtn
	 */
	public WebElement getManageEmailCustomPreviewCloseBtn(int timeOut) {
		return isDisplayed(driver, manageEmailCustomPreviewCloseBtn, "Visibility", timeOut, "Manage Email Custom Template Close Button");
	}
	
	@FindBy(xpath="//div[contains(@class,'EmailCustomPreview_popup')]//span[@title='Close']")
	private WebElement manageEmailCustomPreviewCloseIcon;

	/**
	 * @return the manageEmailCustomPreviewCloseIcon
	 */
	public WebElement getManageEmailCustomPreviewCloseIcon(int timeOut) {
		return isDisplayed(driver, manageEmailCustomPreviewCloseIcon, "Visibility", timeOut, "Manage Email Custom Template Close Icon");
	}
	@FindBy(xpath="//span[@id='erroridforsubject']//span")
	private WebElement manageEmailCustomTemplateSubjectErrorMessage;
	
		/**
	 * @return the manageEmailCustomTemplateSubjectErrorMessage
	 */
	
	public WebElement getManageEmailCustomTemplateSubjectErrorMessage(int timeOut) {
		return isDisplayed(driver, manageEmailCustomTemplateSubjectErrorMessage, "Visibility", timeOut, "Manage Email Custom Subject Error Message");
	}
	@FindBy(xpath="//input[@id='headerChk_ gridobj']")
	private WebElement manageEMailContactAllCheckBox;

	/**
	 * @return the manageEMailContactAllCheckBox
	 */
	public WebElement getManageEMailContactAllCheckBox(int timeOut) {
		return isDisplayed(driver, manageEMailContactAllCheckBox, "Visibility", timeOut, "Manage Email Contact All Check Box ");
	}
	
	public List<WebElement> getManageEmailListOfContactCheckBox(){
		return FindElements(driver, "//input[contains(@id,'checkbox_ME')]", "Manage Email List of Contact Check Box");
	}
	@FindBy(xpath="//div[@id='confirmationpopup_emailID_ME']/div[3]//span")
	private WebElement manageEmailSendInvitationConfirmationCountErrorMsg;

	/**
	 * @return the manageEmailSendInvitationConfirmationErrorMsg
	 */
	public WebElement getManageEmailSendInvitationConfirmationCountErrorMsg(int timeOut) {
		return isDisplayed(driver, manageEmailSendInvitationConfirmationCountErrorMsg, "Visibility", timeOut, "Manage Emails Send Invitation confirmation error message");
	}
	@FindBy(xpath="//div[@id='confirmationpopup_emailID_ME']/div[3]//p")
	private WebElement manageEmailSendInvitationConfirmationErrorMsg;

	/**
	 * @return the manageEmailSendInvitationConfirmationErrorMsg
	 */
	public WebElement getManageEmailSendInvitationConfirmationErrorMsg(int timeOut) {
		return isDisplayed(driver, manageEmailSendInvitationConfirmationErrorMsg, "Visibility", timeOut, "manage email Contact Send Invitation Confirmation Text Message");
	}
	@FindBy(xpath="//div[@id='confirmationpopup_emailID_ME']//a[@title='No']")
	private WebElement manageEmailSendInvitationConfirmationNoBtn;

	/**
	 * @return the manageEmailSendInvitationConfirmationNoBtn
	 */
	public WebElement getManageEmailSendInvitationConfirmationNoBtn(int timeOut) {
		return isDisplayed(driver, manageEmailSendInvitationConfirmationNoBtn, "Visibility", timeOut, "Manage Email Send Invitation Confirmation No Button");
	}
	
	@FindBy(xpath="//div[@id='confirmationpopup_emailID_ME']//a[@title='Yes']")
	private WebElement manageEmailSendInvitationConfirmationYesBtn;

	/**
	 * @return the manageEmailSendInvitationConfirmationYesBtn
	 */
	public WebElement getManageEmailSendInvitationConfirmationYesBtn(int timeOut) {
		return isDisplayed(driver, manageEmailSendInvitationConfirmationYesBtn, "Visibility", timeOut, "Manage Email Send Invitation Yes Button");
	}
	
	@FindBy(xpath="//div[@id='confirmationpopup_emailID_ME']//span[@title='Close']")
	private WebElement manageEmailSendInvitationConfirmationCloseIcon;

	/**
	 * @return the manageEmailSendInvitationConfirmationCloseIcon
	 */
	public WebElement getManageEmailSendInvitationConfirmationCloseIcon(int timeOut) {
		return isDisplayed(driver, manageEmailSendInvitationConfirmationCloseIcon, "Visibility", timeOut, "manage Email Send Invitation Close Icon");
	}
	
	@FindBy(xpath="//span[text()='Email']")
	private WebElement manageEmailEmailLabel;
	
	/**
	 * @return the manageEmailEmailLabel
	 */
	public WebElement getManageEmailEmailLabel(int timeOut) {
		return isDisplayed(driver, manageEmailEmailLabel, "Visibility", timeOut, "Manage email Email Label");
	}
	
	public List<WebElement> getManageEmailListOfContactEmails(){
		return FindElements(driver, "//span[@class='aw-bars-content ']//span[contains(@class,'aw-item-template aw-templates-cell aw-grid-cell aw-column-2 ')]//a", "Manage Email List of Contact Emails");
	}
	@FindBy(xpath="//div[@id='manageemailgrid_ME']//span[text()='Firm']")
	private WebElement manageEMailFirmHeaderText;

	/**
	 * @return the manageEMailFirmHeaderText
	 */
	public WebElement getManageEMailFirmHeaderText(int timeOut) {
		return isDisplayed(driver, manageEMailFirmHeaderText, "Visibility",
				timeOut, "Manage EMail Firm Header Text");
	}
	public List<WebElement> getFirmsInGridInManageEmails(int timeOut) {
		return FindElements(driver,"//span[contains(@class,'grid')][contains(@id,'cell-3-')]/a", "Firms In Manage Emails");
		
	}
	@FindBy(xpath="//div[@id='manageemailgrid_ME']//span[text()='Last Invite Date']")
	private WebElement manageEMailLastInviteDateHeaderText;

	/**
	 * @return the manageEMailLastInviteDateHeaderText
	 */
	public WebElement getManageEMailLastInviteDateHeaderText(int timeOut) {
		return isDisplayed(driver, manageEMailLastInviteDateHeaderText, "Visibility",
				timeOut, "Manage EMail Last Invite date Header Text");
	}
	public List<WebElement> getLastInviteDateInGridInManageEmails(int timeOut) {
		return FindElements(driver, "//span[@class='aw-bars-content ']//span[contains(@class,'aw-item-template aw-templates-cell aw-grid-cell aw-column-4 ')]//div", "Manage EMail Last Invited List");
	}
	@FindBy(xpath="//div[@id='manageemailgrid_ME']//span[text()='Contact Name']")
	private WebElement manageEMailContactNameHeaderText;

	/**
	 * @return the manageEMailContactNameHeaderText
	 */
	public WebElement getManageEMailContactNameHeaderText(int timeOut) {
		return isDisplayed(driver, manageEMailContactNameHeaderText, "Visibility",
				timeOut, "Manage EMail COntact Name Header Text");
	}
	
	public List<WebElement> getManageEmailsListofContacts(){
		return FindElements(driver, "//div[@id='manageemailgrid_ME']//span[contains(@class,'aw-grid-cell aw-column-1')]/a", "Manage Emails Contacts List");
	}
	@FindBy(xpath="//div[@id='manageemailgrid_ME']//span[contains(@class,'aw-hpanel-middle')]//span[contains(@class,'aw-column-1')]/span")
	private WebElement manageEMailContactGridErrorMsg;

	/**
	 * @return the manageEMailContactGridErrorMsg
	 */
	public WebElement getManageEMailContactGridErrorMsg(int timeOut) {
		return isDisplayed(driver, manageEMailContactGridErrorMsg, "Visibility",
				timeOut, "Manage Emails Contact Grid Error Message");
	}
	
	@FindBy(xpath="//div[@id='parentdivId_ME']//div[@id='clearsearchenbEmail']//a[@title='Clear Search']//img")
	private WebElement manageEmailClearSearchEnableIcon;
	
	/**
	 * @return the manageEmailClearSearchEnableIcon
	 */
	public WebElement getManageEmailClearSearchEnableIcon(int timeOut) {
		return isDisplayed(driver, manageEmailClearSearchEnableIcon, "Visibility", timeOut, "Clear Serach Icon");
	}
	
	
	public WebElement geManageEmailIconInContactAccessPopup(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//a[@id='fromCMEIDBW"+workspaceSelector+"_MA']//img", "Manage Email Icon contact access popup", action.BOOLEAN, 30), "visibility", 60, "Manage Email Icon contact access popup");
	}
	public WebElement geManageEmailPopupHeaderInContactAccessPopup(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='EmailConformBW"+workspaceSelector+"_MA']//div[@class='head_popup']", "Manage Email Popup contact access popup", action.BOOLEAN, 30), "visibility", 60, "Manage Email Confirmation popuyp header contact access popup");
	}
	
	public WebElement geManageEmailPopupErrorMessageInContactAccessPopup(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='EmailConformBW"+workspaceSelector+"_MA']//p", "Manage Email Popup error Message contact access popup", action.BOOLEAN, 30), "visibility", 60, "Manage Email Confirmation popuyp error Message  contact access popup");
	}
	public WebElement geManageEmailPopupYesButtonInContactAccessPopup(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='EmailConformBW"+workspaceSelector+"_MA']//a[@title='Yes']", "Manage Email Popup Yes Button contact access popup", action.BOOLEAN, 30), "visibility", 60, "Manage Email Confirmation popuyp Yes Button contact access popup");
	}
	public WebElement geManageEmailPopupNoButtonInContactAccessPopup(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='EmailConformBW"+workspaceSelector+"_MA']//a[@title='No']", "Manage Email Popup No Button contact access popup", action.BOOLEAN, 30), "visibility", 60, "Manage Email Confirmation popuyp No Button contact access popup");
	}
	public WebElement geManageEmailPopupCloseIconInContactAccessPopup(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='EmailConformBW"+workspaceSelector+"_MA']//span[@title='Close']", "Manage Email Popup Close Icon contact access popup", action.BOOLEAN, 30), "visibility", 60, "Manage Email Confirmation popuyp Close Icon contact access popup");
	}
	

	public WebElement getErrorMessageInContactAccessPopup(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//span[@id='grid11_DealDetailBW"+workspaceSelector+"-view']//span[contains(@id,'grid11_DealDetailBW"+workspaceSelector+"-cell-1')]/span", "Contact access popup error Message", action.BOOLEAN, 30), "visibility", 60, "Contact access popup error Message");
	}

	public WebElement getBuildWorkspaceButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "Fundraising";
		} else {
			workspaceSelector = "Investor";
		}
		return isDisplayed(driver, FindElement(driver, "//input[@value='Build "+workspaceSelector+" Workspace']", "Build Workspace Button", action.BOOLEAN, 30), "visibility", 60, "Build Workspace Button");
	}
	
	public WebElement getSizeInMillionTextBox(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "BWFR";
		} else {
			workspaceSelector = "BWINV";
		}
		return isDisplayed(driver, FindElement(driver, "//input[@id='txtFundSize"+workspaceSelector+"']", "Size in million", action.BOOLEAN, 30), "visibility", 60, "Size in million");
	}
	
	public WebElement getVintageYear(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "BWFR";
		} else {
			workspaceSelector = "BWINV";
		}
		return isDisplayed(driver, FindElement(driver, "//input[@id='txtVintageYr"+workspaceSelector+"']", "Vintage Year", action.BOOLEAN, 30), "visibility", 60, "Vintage Year");
	}
	
	public WebElement getContactTextBox(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "BWFR";
		} else {
			workspaceSelector = "BWINV";
		}
		return isDisplayed(driver, FindElement(driver, "//input[@id='txtFundContact"+workspaceSelector+"']", "Contact Text Box", action.BOOLEAN, 30), "visibility", 60, "Contact text Box");
	}
	
	public WebElement getPhoneTextBox(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "BWFR";
		} else {
			workspaceSelector = "BWINV";
		}
		return isDisplayed(driver, FindElement(driver, "//input[@id='txtFundPhone"+workspaceSelector+"']", "Contact Text Box", action.BOOLEAN, 30), "visibility", 60, "Contact text Box");
	}
	
	public WebElement getEmailTextBox(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "BWFR";
		} else {
			workspaceSelector = "BWINV";
		}
		return isDisplayed(driver, FindElement(driver, "//input[@id='txtFundEmail"+workspaceSelector+"']", "Contact Text Box", action.BOOLEAN, 30), "visibility", 60, "Contact text Box");
	}
	
	public WebElement getDescriptionTextBox(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "BWFR";
		} else {
			workspaceSelector = "BWINV";
		}
		return isDisplayed(driver, FindElement(driver, "//textarea[@id='txtFundDesc"+workspaceSelector+"']", "Description Text Box", action.BOOLEAN, 30), "visibility", 60, "Description text Box");
	}
	
	public WebElement getNext1Of3Button(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR:BWFR";
		} else {
			workspaceSelector = "Inv:BWINV";
		}
		return isDisplayed(driver, FindElement(driver, "//input[contains(@name,'page:form"+workspaceSelector+":CompBuiltWorkspace')][@value='Next']", "Next 1 Of 3", action.BOOLEAN, 30), "visibility", 60, "Next 1 Of 3");
	}
	
	public WebElement getImportFolderTemplateButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "BWFR";
		} else {
			workspaceSelector = "BWINV";
		}
		return isDisplayed(driver, FindElement(driver, "//a[@title='Import Folder Template'][contains(@onclick,'importBtnclick"+workspaceSelector+"')]", "Import Folder template Button", action.BOOLEAN, 30), "visibility", 60, "Import Folder template Button");
	}
	
	public WebElement getDisplayDropDown(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR:BWFR";
		} else {
			workspaceSelector = "Inv:BWINV";
		}
		return isDisplayed(driver, FindElement(driver, "//select[@id='page:form"+workspaceSelector+":CompBuiltWorkspace:selectlstId']", "Display Drop Down", action.BOOLEAN, 30), "visibility", 60, "Display Drop Down");
	}
	
	public WebElement getFolderTemplateRadioButton(String folderTemplateName, Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "BWFR";
		} else {
			workspaceSelector = "BWINV";
		}
		return isDisplayed(driver, FindElement(driver, "(//span[@title='"+folderTemplateName+"']/../preceding-sibling::span)[2]/input[contains(@onclick,'selectTemplate"+workspaceSelector+"')]", folderTemplateName+" Template Raido Button", action.BOOLEAN, 30), "visibility", 60, folderTemplateName+" Template Raido Button");
	}
	
	public WebElement getFolderTemplateImportButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "BWFR";
		} else {
			workspaceSelector = "BWINV";
		}
		return isDisplayed(driver, FindElement(driver, "//a[@id='ImportActive"+workspaceSelector+"']", "Folder Template Import Button", action.BOOLEAN, 30), "visibility", 60, "Folder Template Import Button");
	}
	
	public WebElement getNext2Of3Button(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "BWFR";
		} else {
			workspaceSelector = "BWINV";
		}
		return isDisplayed(driver, FindElement(driver, "//a[@onclick='showPopup3"+workspaceSelector+"(event);return false;']", "Next 2 Of 3", action.BOOLEAN, 30), "visibility", 60, "Next 2 Of 3");
	}
	
	public WebElement getDone3Of3Button(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR:BWFR";
		} else {
			workspaceSelector = "Inv:BWINV";
		}
		return isDisplayed(driver, FindElement(driver, "//input[contains(@name,'page:form"+workspaceSelector+":CompBuiltWorkspace')][@value='Done']", "Next 3 Of 3", action.BOOLEAN, 30), "visibility", 60, "Next 3 Of 3");
	}
	
	public WebElement getInstituionCheckBoxOn3Of3(String instituionName, Workspace workspace, int timeOut){
		return isDisplayed(driver, FindElement(driver, "(//div[@title='"+instituionName+"']/../preceding-sibling::span)[2]/input", instituionName+" Institution Check Box", action.BOOLEAN, timeOut), "Visibility", timeOut, instituionName+" Institution Check Box");
	}
	
	public WebElement getLimitedPartnerCheckBox(String InstitutionName, String LPName, Workspace workspace, int timeOut){
		if(click(driver, FindElement(driver, "//label[@title='"+InstitutionName+"']", InstitutionName+" Instituion Folder", action.BOOLEAN, timeOut), InstitutionName+" Institution Folder", action.BOOLEAN)){
			return isDisplayed(driver, FindElement(driver, "//label[@title='"+InstitutionName+"']/../../../following-sibling::ul/li/div/a//label[@title='"+LPName+"']/../../preceding-sibling::input", LPName+" LP Check Box", action.BOOLEAN, timeOut), "Visibility", timeOut, LPName+" LP Check Box");
		}
		return null;
	}
	
	public WebElement getCongratulationsCloseButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "BWFR";
		} else {
			workspaceSelector = "BWINV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[text()='Workspace Built ']/following-sibling::div[2]/a[contains(@onclick,'load"+workspaceSelector+"')]", "Congratulations Close Button", action.BOOLEAN, timeOut/2), "visibility", timeOut/2, "Congratulations Close Button");
	}
	
	public WebElement getCongratulationsMessage(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "BWFR";
		} else {
			workspaceSelector = "BWINV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[text()='Workspace Built ']/following-sibling::div[2]/a[contains(@onclick,'load"+ workspaceSelector +"')]/../preceding-sibling::div[1]", "Congratulations Close Button", action.BOOLEAN, timeOut/2), "visibility", timeOut/2, "Congratulations message");
	}
	
	public WebElement getCongratulationsHeader(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "BWFR";
		} else {
			workspaceSelector = "BWINV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[text()='Workspace Built ']/following-sibling::div[2]/a[contains(@onclick,'load"+ workspaceSelector +"')]/../preceding-sibling::div[2]", "Congratulations Header", action.BOOLEAN, timeOut/2), "visibility", timeOut/2, "Congratulations Header");
	}
	
	public WebElement getCongratulationsCrossIcon(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "BWFR";
		} else {
			workspaceSelector = "BWINV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[text()='Workspace Built ']/following-sibling::div[2]/a[contains(@onclick,'load"+ workspaceSelector +"')]/../preceding-sibling::div[2]/a", "Congratulations cross icon", action.BOOLEAN, timeOut/2), "visibility", timeOut/2, "Congratulations Cross Icon");
	}
	
	public WebElement getInvestorAddedConfirmationCloseButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "(//div[text()='Confirmation ']/following-sibling::div/a[contains(@onclick,'MIidMIN"+workspaceSelector+"')])[1]", workspace+" view section", action.BOOLEAN, 30), "visibility", 60, workspace+" view section");
	}
	
	public WebElement getUploadIcon(Workspace workspace, int timeOut){
		String workspaceSelector="";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "fr";
		} else {
			workspaceSelector = "inv";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='"+workspaceSelector+"workspace']//a[@title='Upload Documents']", "Upload Icon", action.BOOLEAN, timeOut), "Visibility", timeOut, "Upload Icon");
	}
	
	@FindBy(xpath="//label[@for='rbtSingleInstitution']/preceding-sibling::input")
	private WebElement singleInstitutionRadioButton;

	/**
	 * @return the singleInstitutionRadioButton
	 */
	public WebElement getSingleInstitutionRadioButton(int timeOut) {
		return isDisplayed(driver, singleInstitutionRadioButton, "Visibility", timeOut, "Single insitution radio button");
	}
	
	@FindBy(xpath="//label[@for='rbtMultipleInstitution']/preceding-sibling::input")
	private WebElement multipleInstituionRadioButton;

	/**
	 * @return the multipleInstituionRadioButton
	 */
	public WebElement getMultipleInstituionRadioButton(int timeOut) {
		return isDisplayed(driver, multipleInstituionRadioButton, "Visibility", timeOut, "Multiple institution radio button");
	}
	
	@FindBy(xpath="//label[@for='rbtBulkUploader']/preceding-sibling::input")
	private WebElement bulkUploaderOrFileSplitterRadioButton;

	/**
	 * @return the bulkUploaderOrFileSplitterRadioButton
	 */
	public WebElement getBulkUploaderOrFileSplitterRadioButton(int timeOut) {
		return isDisplayed(driver, bulkUploaderOrFileSplitterRadioButton, "Visibility", timeOut, "Bulk Uploader or file splitter radio button");
	}
	
	@FindBy(css="#btnSave")
	private WebElement uploadSaveButton;

	/**
	 * @return the uploadSaveButton
	 */
	public WebElement getUploadSaveButton(int timeOut) {
		return isDisplayed(driver, uploadSaveButton, "Visibility", timeOut, "Save Button");
	}
	
	@FindBy(xpath="//a[@title='Update All']")
	private WebElement uploadWinUpdateButton;

	/**
	 * @return the uploadWinUpdateButton
	 */
	public WebElement getUploadWinUpdateButton(int timeOut) {
		return isDisplayed(driver, uploadWinUpdateButton, "Visibility", timeOut, "Update All button");
	}
	
	public WebElement getDeleteFolderButton(String folderName,int timeOut) {
		WebElement ele=null;
		ele=isDisplayed(driver, FindElement(driver, "//label[contains(text(),'"+folderName+"')]//span[@title='Delete Folder']", "created folder delete button", action.BOOLEAN, timeOut), "Visibility", timeOut, "created folder delete button"); 
		return ele;
	}
	
	public WebElement getRenameFolderButton(String folderName,int timeOut) {
		WebElement ele=null;
		ele=isDisplayed(driver, FindElement(driver, "//label[contains(text(),'"+folderName+"')]/span[1]", "created folder rename button", action.BOOLEAN, timeOut), "Visibility", timeOut, "created folder rename button"); 
		return ele;
	}
	
	public WebElement getAddFolderButton(String folderName,int timeOut) {
		WebElement ele=null;
		ele=isDisplayed(driver, FindElement(driver, "//label[contains(text(),'"+folderName+"')]//span[@title='Add a Folder']", "created folder add button", action.BOOLEAN, timeOut), "Visibility", timeOut, "created folder add button"); 
		return ele;
	}
	

	public WebElement getBuildWorkspaceStep3Of3Header(Workspace workspace, int timeOut){
		String workspaceSelector="";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='step_3of3BW"+workspaceSelector+"']/div[@class='head_popup']", "Build workspace step 3 of 3", action.BOOLEAN, timeOut), "Visibility", timeOut, "Build workspace step 3 of 3");
	}
	
	
	public List<WebElement> getInstitutionsNameInBuildWorkspaceStep3FilterSection(int timeOut) {
		return FindElements(driver,"//span[contains(@id,'grid_SelectedTargetAccounts-cell-1-')]/div", "Institution name");		
	}
	
	public List<WebElement> getInvestorNameInBuildInvestorWorkspaceStep3FilterSection(int timeOut) {
		return FindElements(driver,"//span[contains(text(),'All Investors')]/../..//following-sibling::ul//a//label", "Institution name");		
	}
	
	
	
	//***************************************Manage Approvals****************//
	
	@FindBy(xpath = "//div[contains(@class,'ManageApprovals')]/div[contains(text(),'Manage Approvals')]")
	private WebElement manageAppHeadText;
	/**
	 * @return the manageAppHeadText
	 */
	public WebElement getManageAppHeadText(int timeOut) {
		return isDisplayed(driver, manageAppHeadText, "Visibility", timeOut, "manage approval popup header text");
	}
	@FindBy(xpath = "//div[contains(@class,'ManageApprovals')]/div[contains(text(),'Manage Approvals')]//a[@title='Close']")
	private WebElement crossIconManageApp;
	
	
	/**
	 * @return the crossIconManageApp
	 */
	public WebElement getCrossIconManageApp(int timeOut) {
		return isDisplayed(driver, crossIconManageApp, "Visibility", timeOut, "cross icon manage approvals");
	}

	@FindBy(xpath = "//input[@class='checkall']")
	private WebElement checkAllDocsManageApprovals;
	
	
	/**
	 * @return the checkAllDocsManageApprovals
	 */
	public WebElement getCheckAllDocsManageApprovals(int timeOut) {
		return isDisplayed(driver, checkAllDocsManageApprovals, "Visibility", timeOut, "checkbox to select all documents manage approvals");
	}

	@FindBy(xpath = "//a[contains(@class,'ManageApprovals')]/..//a[@title='Cancel']")
	private WebElement manageAppCancelBtn;

	/**
	 * @return the manageAppCancelBtn
	 */
	public WebElement getManageAppCancelBtn(int timeOut) {
		return isDisplayed(driver, manageAppCancelBtn, "Visibility", timeOut, "manage approval popup cancel button");
	}
	@FindBy(xpath = "//a[text()='Pending Documents']")
	private WebElement pendingDocsTab;
	
	@FindBy(xpath="//div[contains(@id,'mainForm')]/div[1]")
	private WebElement manageApprovalWindow;
	
	/**
	 * @return the manageApprovalWindow
	 */
	public WebElement getManageApprovalWindow(int timeOut) {
		return isDisplayed(driver, manageApprovalWindow, "Visibility", timeOut, "manage approvals header pop up");
	}
	
	@FindBy(xpath="//span[@id='pendingGrid-header-1-box-text']")
	private WebElement documentNameManageApproval;
	
	/**
	 * @return the documentNameManageApproval
	 */
	public WebElement getDocumentNameManageApproval(ManageApprovalTabs mat,int timeOut) {
		String xpath = "";
		if (mat == ManageApprovalTabs.PendingDocuments) {
			xpath = "//span[@id='pendingGrid-header-1-box-text']";
		}
		else {
			xpath = "//span[contains(@id,'AprovedGrid') and contains(@id,'header-1-box-text')]";
		}
		return isDisplayed(driver, FindElement(driver, xpath, "document name on manage approval popup", action.BOOLEAN, timeOut), "Visibility", timeOut, "manage approvals document name header");
	}
	
	@FindBy(xpath="//span[@id='pendingGrid-view-box-top']/span//input")
	private WebElement manageApprovalsAllDocumentSelectCheckBox;
	
	
	/**
	 * @return the manageApprovalsAllDocumentSelectCheckBox
	 */
	public WebElement getManageApprovalsAllDocumentSelectCheckBox(int timeOut) {
		return isDisplayed(driver, manageApprovalsAllDocumentSelectCheckBox, "Visibility", timeOut, "manage approvals all documenyt select check box");
	}
	public List<WebElement> getManageApprovalFolderPathList(ManageApprovalTabs mat) {
		String gridSel="",num="2";
		if (mat == ManageApprovalTabs.PendingDocuments) {
			gridSel = "pendingGrid";
		}
		else {
			gridSel="aw";
		}
		return FindElements(driver, "//span[contains(@id,'"+gridSel+"') and contains(@id,'cell-"+num+"-')]", "folder path list manage approvals");
	}
	
	public List<WebElement> getManageApprovalUploadedByList(ManageApprovalTabs mat) {
		String gridSel="",num="4";
		if (mat == ManageApprovalTabs.PendingDocuments) {
			gridSel = "pendingGrid";
		}
		else {
			gridSel="AprovedGrid";
		}
		return FindElements(driver, "//span[contains(@id,'"+gridSel+"') and contains(@id,'cell-"+num+"-')]", "uploaded by list manage approvals");
	}
	
	public List<WebElement> getCrossIconListUploadDoc() {
		return FindElements(driver, "//li[@id='newfile']//img", "list of cross icon upload window");
		
	}
	public List<WebElement> getManageApprovalDocumentNameList(ManageApprovalTabs mat) {
		String gridSel="",num="1";
		if (mat == ManageApprovalTabs.PendingDocuments) {
			gridSel = "pendingGrid";
		}
		else {
			gridSel="AprovedGrid";
		}
		return FindElements(driver, "//span[contains(@id,'"+gridSel+"') and contains(@id,'cell-"+num+"-')]", "document name list manage approvals");
	}
	
	public List<WebElement> getManageApprovalFirmNameList(ManageApprovalTabs mat) {
		String gridSel="",num="5";
		if (mat == ManageApprovalTabs.PendingDocuments) {
			gridSel = "pendingGrid";
		}
		else {
			gridSel="AprovedGrid";
		}
		return FindElements(driver, "//span[contains(@id,'"+gridSel+"') and contains(@id,'cell-"+num+"-')]", "firm name list manage approvals");
	}
	public List<WebElement> getManageApprovalUploadedOnList(ManageApprovalTabs mat) {
		String gridSel="",num="";
		if (mat == ManageApprovalTabs.PendingDocuments) {
			gridSel = "pendingGrid";
			num="6";
		}
		else {
			gridSel="AprovedGrid";
			num="6";
		}
		return FindElements(driver, "//span[contains(@id,'"+gridSel+"') and contains(@id,'cell-"+num+"-')]", "uploaded on list manage approvals");
	}
	public List<WebElement> getManageApprovalsAllPendingFilesCheckBoxList(){
		return FindElements(driver, "//span[contains(@id,'pendingGrid-row-')]/span[2]//input", "manage approvals all pending files check box");
	}
	
	@FindBy(xpath="//div[contains(@class,'ManageApprovals')]//div/a[@title='Approve']")
	private WebElement manageApprovalsApproveBtn;
	
	/**
	 * @return the manageApprovalsApproveBtn
	 */
	public WebElement getManageApprovalsApproveBtn(int timeOut) {
		return isDisplayed(driver, manageApprovalsApproveBtn, "Visibility", timeOut, "approve button");
	}
	
	@FindBy(xpath="//div[contains(@class,'ManageApprovals')]//div/a[@title='Cancel']")
	private WebElement manageApprovalsCancelBtn;
	
	
	/**
	 * @return the manageApprovalsCancelBtn
	 */
	public WebElement getManageApprovalsCancelBtn(int timeOut) {
		return isDisplayed(driver, manageApprovalsCancelBtn, "Visibility", timeOut, "manage approvals cancel button");
	}

	@FindBy(xpath="//a[@id='confirm_move']")
	private WebElement manageApprovalsConfirmYesBtn;
	
	/**
	 * @return the manageApprovalsConfirmYesBtn
	 */
	public WebElement getManageApprovalsConfirmYesBtn(int timeOut) {
		return isDisplayed(driver, manageApprovalsConfirmYesBtn, "Visibility", timeOut, "manage approvals confirm yes button");
	}
	
	@FindBy(xpath="//a[contains(@onclick,'refreshPendingGrid();')]")
	private WebElement manageApprovalsCloseBtn;
	
	/**
	 * @return the manageApprovalsConfirmYesBtn
	 */
	public WebElement getManageApprovalsCloseBtn(int timeOut) {
		return isDisplayed(driver, manageApprovalsCloseBtn, "Visibility", timeOut, "manage approvals close button");
	}
	
	@FindBy(xpath="//div[contains(@class,'DuplicateDocumentsFound_Confirmation')]//div//a[@title='Close']")
	private WebElement manageApprovalsApprovedUpdatedDocumentCloseBtn;
	
	/**
	 * @return the manageApprovalsApprovedUpdatedDocumentCloseBtn
	 */
	public WebElement getManageApprovalsApprovedUpdatedDocumentCloseBtn(int timeOut) {
		return isDisplayed(driver, manageApprovalsApprovedUpdatedDocumentCloseBtn, "Visibility", timeOut, "manage approvals update document close button");
	}
	@FindBy(xpath = "//a[@title='Ignore All']")
	private WebElement manageApprovalsIgnoreAll;
	
	
	/**
	 * @return the manageApprovalsIgnoreAll
	 */
	public WebElement getManageApprovalsIgnoreAll(int timeOut) {
		return isDisplayed(driver, manageApprovalsIgnoreAll, "Visibility", timeOut, "manage approvals ignore all");
	}

	@FindBy(xpath="//a[@title='Update All']")
	private WebElement manageApprovalsUpdateAllDocument;
	
	/**
	 * @return the manageApprovalsUpdateAllDocument
	 */
	public WebElement getManageApprovalsUpdateAllDocument(int timeOut) {
		return isDisplayed(driver, manageApprovalsUpdateAllDocument, "Visibility", timeOut, "manage approvals update all document");
	}

	@FindBy(xpath = "//div[@id='clearsearchenb_MP']//a//img")
	private WebElement searchIconCrossButtonManageApprovals;
	
	/**
	 * @return the searchIconCrossButtonManageApprovals
	 */
	public WebElement getSearchIconCrossButtonManageApprovals(ManageApprovalTabs mat,int timeOut) {
		String xpath = "";
		if (mat == ManageApprovalTabs.PendingDocuments) {
			xpath = "//div[@id='clearsearchenb_MP']//a//img";
		}
		else {
			xpath = "//div[@id='clearsearchenb_MP2']//a//img";
		}
		return isDisplayed(driver, FindElement(driver, xpath,  "Search Icon Cross Button Manage Approvals", action.BOOLEAN, timeOut), "Visibility", timeOut, "Search Icon Cross Button Manage Approvals");
	}
	
	public WebElement manageApprovalsScrollBox(ManageApprovalTabs mat, int timeOut) {
		String xpath = "";
		if (mat == ManageApprovalTabs.ApprovedDocuments) {
			xpath = "//div[contains(@id,'grid_ManageApprovalTab21')]/span[@id='AprovedGrid']//span[contains(@class,'bars-box') and contains(@id,'scroll-box')]";
		}
		else {
			xpath = "//span[@id='pendingGrid-scroll-box']";
			
		}
		return isDisplayed(driver, FindElement(driver, xpath, "scroll box manage approvals", action.SCROLLANDBOOLEAN, timeOut), "visibility", timeOut,  "scroll box manage approvals");
	}
	
	@FindBy(xpath = "//div[contains(@class,'DuplicateDocumentsFound')]//div[text()='Duplicate Document(s) Found ']//span[@title='Close']")
	private WebElement duplicateDocsCloseButton;
	
	
	/**
	 * @return the duplicateDocsCloseButton
	 */
	public WebElement getDuplicateDocsCloseButton(int timeOut) {
		return isDisplayed(driver, duplicateDocsCloseButton, "Visibility", timeOut, "");
	}

	@FindBy(xpath = "(//div[contains(@class,'DuplicateDocumentsFound')])[1]")
	private WebElement approveDuplicateDocsHead;
	/**
	 * @return the approveDuplicateDocsHeadFolderPath
	 */
	public WebElement getDuplicateDocsHeadFolderPath(int timeOut, boolean scrollIconOrNo) {
		String xpath = "(//span[contains(@id,'aw') and contains(@title,'Folder Path')]//span[contains(@id,'box-text')])";
		if (scrollIconOrNo == false) {
			xpath = xpath + "[1]";
		}
		else {
			xpath = xpath + "[2]";
		}
		return isDisplayed(driver, FindElement(driver, xpath, "folder path column on duplicate doc window", action.BOOLEAN, timeOut), "Visibility", timeOut, "folder path column on duplicate doc window");
	}
	/**
	 * @return the approveDuplicateDocsHeadColumn
	 */
	public WebElement getDuplicateDocsHeadColumn(int timeOut, boolean scrollIconOrNo) {
		String xpath = "//span[contains(@id,'aw')]//span[contains(text(),'Duplicate Document(s)')]";
		if (scrollIconOrNo==true) {
			xpath = xpath + "//span";
		}
		return isDisplayed(driver, FindElement(driver, xpath, "duplicate docs head column", action.BOOLEAN, timeOut), "Visibility", timeOut, "");
	}
	
	public List<WebElement> duplicateDocColumnList(int timeOut) {
		return FindElements(driver, "//span[@id='grid_DuplicateDocumentsFound']//span[contains(@id,'cell-0')]", "list of documents in duplicate window");
	}
	
	public List<WebElement> deleteDocsColumnList(int timeOut) {
		return FindElements(driver, "//div[@id='grid_FoldersDeleted']//span[contains(@id,'cell-0')]", "list of documents in delete docs window");
	}
	
	public List<WebElement> duplicateDocFolderPathList(int timeOut) {
		return FindElements(driver, "//span[@id='grid_DuplicateDocumentsFound']//span[contains(@id,'cell-1')]", "list of folder path in duplicate docs window");
	}
	public List<WebElement> folderDeleteFolderPathList(int timeOut) {
		return FindElements(driver, "//div[@id='grid_FoldersDeleted']//span[contains(@id,'cell-1')]", "list of folder path in delete folder");
	}
	@FindBy(xpath = "//div[@id='dup2']//p")
	private WebElement approveDuplicateDocsHeadText;
	
	
	/**
	 * @return the approveDuplicateDocsHeadText
	 */
	public WebElement getApproveDuplicateDocsHeadText(int timeOut) {
		return isDisplayed(driver, approveDuplicateDocsHeadText, "Visibility", timeOut, "");
	}
	@FindBy(xpath = "//div[contains(@class,'FoldersDeleted')]//p")
	private WebElement folderDeletedText;
	
	

	/**
	 * @return the folderDeletedText
	 */
	public WebElement getFolderDeletedText(int timeOut) {
		return isDisplayed(driver, folderDeletedText, "Visibility", timeOut, "Folder Deleted Text");
	}
	@FindBy(xpath = "(//span[@id='grid_DuplicateDocumentsFound']//span[contains(@id,'scroll-box')])[1]")
	private WebElement duplicateDocScrollBox;
	
	
	/**
	 * @return the duplicateDocScrollBox
	 */
	public WebElement getDuplicateDocScrollBox(int timeOut) {
		return isDisplayed(driver, duplicateDocScrollBox, "Visibility", timeOut, "");
	}
	
	public WebElement getDocumentsHeadOnFolderDeletePopup(int timeOut, boolean scrollIconOrNo) {
		String xpath = "//span[@title='Document(s)']/span/span[3]";
		if (scrollIconOrNo == true) {
			xpath =xpath+"/span";
		}
		return isDisplayed(driver, FindElement(driver, xpath, "documents head folder deleteup", action.BOOLEAN, timeOut), "Visibility", timeOut, "documents head on folder delete popup");
	}
	
	
	@FindBy(xpath = "(//div[text()='Confirmation']/a/span)[1]")
	private WebElement manageApprovalsApproveConfirmationCrossIcon;
	
	
	/**
	 * @return the manageApprovalsApproveConfirmationCrossIcon
	 */
	public WebElement getManageApprovalsApproveConfirmationCrossIcon(int timeOut) {
		return isDisplayed(driver, manageApprovalsApproveConfirmationCrossIcon, "Visibility", timeOut, "");
	}
	
	@FindBy(xpath = "//div[contains(@class,'FoldersDeleted')]//a[@title='OK']")
	private WebElement foldersDeletedOkButton;
	
	
	/**
	 * @return the foldersDeletedOkButton
	 */
	public WebElement getFoldersDeletedOkButton(int timeOut) {
		return isDisplayed(driver, foldersDeletedOkButton, "Visibility", timeOut, "folders deleted ok button");
	}
	@FindBy(xpath = "//div[contains(@class,'FoldersDeleted')]//span[@title='Close']")
	private WebElement foldersDeletedCrossButton;
	
	

	/**
	 * @return the foldersDeletedCloseButton
	 */
	public WebElement getFoldersDeletedCrossButton(int timeOut) {
		return isDisplayed(driver, foldersDeletedCrossButton, "Visibility", timeOut, "cross icon on folders deleted popup");
	}

	@FindBy(xpath = "//span[@id='txtwarning_div']")
	private WebElement uploadBulkDelayedMessage;
	
	
	/**
	 * @return the uploadBulkDelayedMessage
	 */
	public WebElement getUploadBulkDelayedMessage(int timeOut) {
		return isDisplayed(driver, uploadBulkDelayedMessage, "Visibility", timeOut, "upload Bulk Delayed Message");
	}

	//*****************************************Manage Investor***************************************************************//
	/**
	 * @return the pendingDocsTab
	 */
	public WebElement getPendingDocsTab(int timeOut) {
		return isDisplayed(driver, pendingDocsTab, "Visibility", timeOut, "manage approvals pending docs tabs");
	}
	@FindBy(xpath = "//a[text()='Approved Documents']")
	private WebElement approvedDocsTab;


	/**
	 * @return the approvedDocsTab
	 */
	public WebElement getApprovedDocsTab(int timeOut) {
		return isDisplayed(driver, approvedDocsTab, "Visibility", timeOut, "approved docs tab");
	}
	@FindBy(xpath = "//select[@id='page:manageapprovalcomponent:innercomponent:Manage_Approval_Component:picklistView1']")
	private WebElement manageAppPendingDropdown;
	
	/**
	 * @return the manageAppViewDropdown
	 */
	public WebElement getManageAppPendingDropdown(int timeOut) {
		return isDisplayed(driver, manageAppPendingDropdown, "Visibility", timeOut, "manage approvals view dropdown");
	}
	@FindBy(xpath = "//select[@id='page:manageapprovalcomponent:innercomponent:Manage_Approval_Component:picklistView2']")
	private WebElement manageAppovalsApprovedDocsDropdown;
	
	/**
	 * @return the manageAppovalsApprovedDocsDropdown
	 */
	public WebElement getManageAppovalsApprovedDocsDropdown(int timeOut) {
		return isDisplayed(driver, manageAppovalsApprovedDocsDropdown, "Visibility", timeOut, "manage approvals approved documents dropdown");
	}
	public WebElement getManageApprovalsDropdown(ManageApprovalTabs mat, int timeOut) {
		if (mat == ManageApprovalTabs.PendingDocuments) {
			return getManageAppPendingDropdown(timeOut);
		}
		else if(mat == ManageApprovalTabs.ApprovedDocuments) {
			return getManageAppovalsApprovedDocsDropdown(timeOut);
		}
		return null;
	}
	

	public WebElement getSearchTextboxManageApp(ManageApprovalTabs mat,int timeOut) {
		String xpath = "";
		if (mat == ManageApprovalTabs.PendingDocuments) {
			xpath = "//input[@id='page:manageapprovalcomponent:innercomponent:Manage_Approval_Component:searchcon_grid_MP']";
		}
		else if(mat == ManageApprovalTabs.ApprovedDocuments) {
			xpath = "//input[@id='page:manageapprovalcomponent:innercomponent:Manage_Approval_Component:searchcon_grid_MP2']";
		}
		return isDisplayed(driver, FindElement(driver, xpath, "search text box manage approvals", action.SCROLLANDBOOLEAN, timeOut), "Visibility", timeOut, "search textbox");
	}
	
	public WebElement getClearIconOnManageApprovalsSearchBox(ManageApprovalTabs mat,int timeOut) {
		WebElement ele = null;
		String xpath="";
		if(mat == ManageApprovalTabs.PendingDocuments) {
			xpath="//div[@id='clearsearchenb_MP']/a/img";
		}else if(mat == ManageApprovalTabs.ApprovedDocuments) {
			xpath="//div[@id='clearsearchenb_MP2']/a/img";
		}	
		ele= FindElement(driver, xpath, "clear button in search text box manage approvals", action.SCROLLANDBOOLEAN, timeOut);
		ele=isDisplayed(driver, ele, "Visibility", timeOut, "Clear Icon Manage approvals search text box ");
		return ele;
	}
	
	
	public WebElement getSearchIconManageApprovalsPopup(ManageApprovalTabs mat, int timeOut) {
		String xpath = "";
		if (mat == ManageApprovalTabs.PendingDocuments) {
			xpath = "(//div[contains(text(),'Manage Approvals')]/../div//table//a[@class='icon_btn_search'])[1]";
		}
		else if(mat == ManageApprovalTabs.ApprovedDocuments) {
			xpath = "(//div[contains(text(),'Manage Approvals')]/../div//table//a[@class='icon_btn_search'])[2]";
		}
		return isDisplayed(driver, FindElement(driver, xpath, "search icon manage approvals", action.BOOLEAN, timeOut), "Visibility", timeOut, "search icon manage approvals popup");
	}
	
	public List<WebElement> getColumnListManageApproval(ManageApprovalTabs mat,int timeOut) {
		if (mat == ManageApprovalTabs.PendingDocuments) {
			return FindElements(driver, "//div[contains(text(),'Manage Approvals')]/..//span[contains(@id,'pendingGrid-header-')]/span[3]", "get Column List Manage Approval");
		}
		else {
			return FindElements(driver, "//div[contains(text(),'Manage Approvals')]/..//span[starts-with(@id,'AprovedGrid') and contains(@id,'-header-')  ]/span[3]", "get Column List Manage Approval");
		}
	}
	public WebElement noDataToDisplay(ManageApprovalTabs mat, int timeOut) {
		String id = "";
		if (mat == ManageApprovalTabs.PendingDocuments) {
			id = "pendingGrid";
		}else {
			id = "AprovedGrid-rows";
		}
		return isDisplayed(driver, FindElement(driver, "//span[contains(@id,'"+id+"')]//span[text()='No data to display.']", "no data to display for manage approvals popup", action.SCROLLANDBOOLEAN, timeOut), "visibility", timeOut, "no data to display for manage approvals popup");
	}
	@FindBy(xpath = "//a[text()='Approved Documents']/../../../../..//div/a[@title='Close']")
	private WebElement closeBtnApprovedDocs;
	
	/**
	 * @return the closeBtnApprovedDocs
	 */
	public WebElement getCloseBtnApprovedDocs(int timeOut) {
		return isDisplayed(driver, closeBtnApprovedDocs, "Visibility", timeOut, "close button manage approvals");
	}

	@FindBy(xpath = "//a[@id='ApproveId']")
	private WebElement approveBtnManageApprovals;
	/**
	 * @return the approveBtnManageApprovals
	 */
	public WebElement getApproveBtnManageApprovals(int timeOut) {
		return isDisplayed(driver, approveBtnManageApprovals, "Visibility", timeOut, "approve btn manage approvals");
	}
	@FindBy(xpath = "//div[contains(@class,'ManageApprovalsDelete')]//a[@title='No']")
	private WebElement manageApprovalsDelNo;
	
	@FindBy(xpath = "//div[contains(@class,'ManageApprovalsDelete')]//a[@title='Yes']")
	private WebElement manageApprovalsDelYes;
	
	public WebElement getManageApprovalApproveYesOrNo(YesNo yn, int timeOut) {
		String xpath = "";
		if (yn == YesNo.Yes) {
			xpath = "//div[contains(@class,'ConfirmationDocument_popup')]//a[@title='Yes']";
		}
		else {
			xpath = "//div[contains(@class,'ConfirmationDocument_popup')]//a[@title='No']";
		}
		return isDisplayed(driver, FindElement(driver, xpath, "manage approval "+yn.toString()+" button", action.SCROLLANDBOOLEAN, timeOut), "visibility", timeOut, "manage approval "+yn.toString()+" button");
	}
	public WebElement getManageApprovalDelYesorNo(YesNo yn, int timeOut) {
		if (yn == YesNo.Yes) {
			return isDisplayed(driver, manageApprovalsDelYes, "Visibility", timeOut, "manage approvals delete yes");
		}
		else {
			return isDisplayed(driver, manageApprovalsDelNo, "Visibility", timeOut, "manage approvals delete no");
		}
	}
	@FindBy(xpath = "//div[contains(@class,'ManageApprovalsDelete')]//p")
	private WebElement manageApprovalDeletePopupText;
	
	
	/**
	 * @return the manageApprovalDeletePopupText
	 */
	public WebElement getManageApprovalDeletePopupText(int timeOut) {
		return isDisplayed(driver, manageApprovalDeletePopupText, "Visibility", timeOut, "manage approvals delete popup text");
	}
	@FindBy(xpath = "//div[contains(@class,'ConfirmationDocument_popup')]//span[@title='Close']")
	private WebElement manageApprovalApproveCrossIcon;
	
	
	/**
	 * @return the manageApprovalApproveCrossIcon
	 */
	public WebElement getManageApprovalApproveCrossIcon(int timeOut) {
		return isDisplayed(driver, manageApprovalApproveCrossIcon, "Visibility", timeOut, "manage approval approve cross icon");
	}
	

	@FindBy(xpath = "//p[@id='totalStatus']")
	private WebElement approveConfirmPopupText;
	
	
	/**
	 * @return the approveConfirmPopupText
	 */
	public WebElement getApproveConfirmPopupText(int timeOut) {
		return isDisplayed(driver, approveConfirmPopupText, "Visibility", timeOut, "manage approvals approve Confirm Popup Text");
	}

	@FindBy(xpath = "//div[contains(@class,'ManageApprovalsDelete')]//div[text()='Confirmation ']//span[@title='Close']")
	private WebElement manageApprovalDelCrossIcon;
	
	
	/**
	 * @return the manageApprovalDelCrossIcon
	 */
	public WebElement getManageApprovalDelCrossIcon(int timeOut) {
		return isDisplayed(driver, manageApprovalDelCrossIcon, "Visibility", timeOut, "delete manage approvals cross icon");
	}
	

	@FindBy(xpath = "//a[@id='DeleteId']")
	private WebElement deleteBtnManageApprovals;
	
	public WebElement getdeleteBtnManageApprovals(int timeOut) {
		return isDisplayed(driver, deleteBtnManageApprovals, "Visibility", timeOut, "delete btn manage approvals");
	}
	//*****************************************Manage Investor***************************************************************//
	

	
	public WebElement getManageInvestorIcon(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "fr";
		} else {
			workspaceSelector = "inv";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='"+workspaceSelector+"workspace']//a[@title='Manage Investors']//img", workspace+" Manage Investor icon", action.BOOLEAN, 30), "visibility", 60, workspace+"  Manage Investor icon");
	}
	
	
	public WebElement getManageInvestorHeader(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "1MINFR";
		} else {
			workspaceSelector = "2MININV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='manageinv"+workspaceSelector+"']//div[text()='Manage Investors ']", workspace+" Manage Investor icon", action.BOOLEAN, 30), "visibility", 60, workspace+"  Manage Investor icon");
	}

	public WebElement getManageInvestorFilterInvestorHeader(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//span[@id='displayFilterText_MInvestorMIN"+workspaceSelector+"']//a", workspace+" Manage Investor Filter investor header", action.BOOLEAN, 30), "visibility", 60, workspace+" Manage Investor Filter investor header");
	}
	

	public WebElement getManageInvestorFilterCollapsedHeader(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//span[@id='hideFilterText_MInvestorMIN"+workspaceSelector+"']//a", workspace+" Manage Investor Filter investor header collapsed", action.BOOLEAN, 30), "visibility", 60, workspace+" Manage Investor Filter investor header collapsed");
	}
	
	
	public WebElement getManageInvestorFilterAddRowLink(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//span[@id='AdvanceFlterAddRowNewMIN"+workspaceSelector+"']", workspace+" Add Row Link", action.BOOLEAN, 30), "visibility", 60, workspace+"Add Row Link");
	}
	
	
	public WebElement getManageInvestorInvestorAccountsHeader(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='buildPopUpMIN"+workspaceSelector+"']//span[text()='Investor Accounts']", workspace+" Manage Investor Invetsor Accounts header", action.BOOLEAN, 30), "visibility", 60, workspace+" Manage Investor Invetsor Accounts header");
	}
	
	@FindBy(xpath="//span[@id='grid_SelectedTargetAccounts-view-box-top']//span[@title='Institutions']")
	private WebElement manageInvestorInstitutionsColoumnLabel;

	/**
	 * @return the manageInvestorInstitutionsColoumnLabel
	 */
	public WebElement getManageInvestorInstitutionsColoumnLabel(int timeOut) {
		return isDisplayed(driver, manageInvestorInstitutionsColoumnLabel, "Visibility", timeOut, "Institutions coloumn label");
	} 
	 
	public WebElement getManageInvestorShowDropdown(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='buildPopUpMIN"+workspaceSelector+"']//div[@class='pickListData']//select", workspace+" Manage Investor Show dropdown", action.BOOLEAN, 30), "visibility", 60, workspace+"  Manage Investor show dropdown");
	}
	
	public WebElement getManageInvestorDoneButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "1MINFR";
		} else {
			workspaceSelector = "2MININV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='manageinv"+workspaceSelector+"']//a[@title='Done']", workspace+" Manage Investor Done button", action.BOOLEAN, 30), "visibility", 60, workspace+"  Manage Investor Done button");
	}
	
	public WebElement getManageInvestorNoDataDisplayErrorMesage(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='manageinv1MIN"+workspaceSelector+"']//span[@id='grid_SelectedTargetAccounts-cell-1-0']/span", workspace+" Manage Investor Error Message", action.BOOLEAN, 30), "visibility", 60, workspace+"  Manage Investor Error Message");
	}
	
	public List<WebElement> getManageInvestorPopupInstitutionList(){
		return FindElements(driver, "//span[contains(@id,'grid_SelectedTargetAccounts-cell-1-')]//div", "Institution list");
	}
	
	
	public WebElement getManageInvestorAddedPopupCloseButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='Confirmation_added_MIidMIN"+workspaceSelector+"']//a[@title='Close']", workspace+"Manage Investor Added Popup Close Button", action.BOOLEAN, 30), "visibility", 60, workspace+"Manage Investor Added Popup Close Button");
	}
	
	public WebElement getManageInvestorAddedPopupCrossIcon(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='Confirmation_added_MIidMIN"+workspaceSelector+"']//span[@title='Close']", workspace+"Manage Investor Added Popup Cross icon", action.BOOLEAN, 30), "visibility", 60, workspace+"Manage Investor Added Popup Cross icon");
	}
	 
	
	public WebElement getManageInvestorAddedPopupMessage(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='Confirmation_added_MIidMIN"+workspaceSelector+"']//p", workspace+"Manage Investor Added Popup Error Message", action.BOOLEAN, 30), "visibility", 60, workspace+"Manage Investor Added Popup Error Message");
	}
	
	
	public WebElement getManageInvestorAddedPopupHeader(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='Confirmation_added_MIidMIN"+workspaceSelector+"']//div[@class='head_popup']", workspace+" Manage Investor Added Popup Header", action.BOOLEAN, 30), "visibility", 60, workspace+"  Manage Investor Added popup Header");
	}
	
	
	public WebElement getManageInvestorPopupCrossIcon(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "1MINFR";
		} else {
			workspaceSelector = "2MININV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='manageinv"+workspaceSelector+"']/div[@class='head_popup']//a[@title='Close']", workspace+" Manage Investor popup cross icon", action.BOOLEAN, 30), "visibility", 60, workspace+"Manage Investor popup cross icon");
	}
	
	public WebElement getManageInvestorDeletedPopupHeader(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='Confirmation_removed_MIidMIN"+workspaceSelector+"']//div[@class='head_popup']", workspace+"Manage Investor Deleted Ppoup header", action.BOOLEAN, 30), "visibility", 60, workspace+"Manage Investor Deleted Ppoup header");
	}
	
	public WebElement getManageInvestorDeletedPopupMessage(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='Confirmation_removed_MIidMIN"+workspaceSelector+"']//p", workspace+"Manage Invetsor Deleted Popup Error Message", action.BOOLEAN, 30), "visibility", 60, workspace+"Manage Invetsor Deleted Popup Error Message");
	}
	
	public WebElement getManageInvestorDeletedPopupCloseButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='Confirmation_removed_MIidMIN"+workspaceSelector+"']//a[@title='Close']", workspace+"Investor Deleted Confirmationpopup Close Button", action.BOOLEAN, 30), "visibility", 60, workspace+"Investor Deleted Confirmationpopup Close Button");
	}
	
	public WebElement getManageInvestorDeletedPopupCrossIcon(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='Confirmation_removed_MIidMIN"+workspaceSelector+"']//div[@class='head_popup']//span[@title='Close']", workspace+"Investor Deleted Confirmationpopup Cross Icon", action.BOOLEAN, 30), "visibility", 60, workspace+"Investor Deleted Confirmationpopup Cross Icon");
	}
	
	public WebElement getManageInvestorRenamePopupHeader(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='instpopupMIN"+workspaceSelector+"']//div[@class='head_popup']", workspace+"Manage Investor Rename Popup Header", action.BOOLEAN, 30), "visibility", 60, workspace+"Manage Investor Rename Popup Header");
	}
	
	public WebElement getManageInvestorRenamePopupMessage(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='instpopupMIN"+workspaceSelector+"']//p", workspace+"Manage Investor Rename Popup Error Message", action.BOOLEAN, 30), "visibility", 60, workspace+"Manage Investor Rename Popup Error Message");
	}
	
	public WebElement getManageInvestorRenamePopupTextBox(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='instpopupMIN"+workspaceSelector+"']//input[@id='newnameMIN"+workspaceSelector+"']", workspace+"Manage Investor Rename popup Text Box", action.BOOLEAN, 30), "visibility", 60, workspace+"Manage Investor Rename popup Text Box");
	}
	
	public WebElement getManageInvestorRenamePopupApplyButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='instpopupMIN"+workspaceSelector+"']//a[@title='Apply']", workspace+"Manage Investor Rename popup Apply Button", action.BOOLEAN, 30), "visibility", 60, workspace+"Manage Investor Rename popup Apply Button");
	}
		
	
	public WebElement getManageInvestorFilterClearButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='filterGridContactDivId_MInvestorMIN"+workspaceSelector+"']//a[@title='Clear']", workspace+"Manage Investor filter clear Button", action.BOOLEAN, 30), "visibility", 60, workspace+"Manage Investor filter clear Button");
	}
	
	public WebElement getManageInvestorRenamePopupCancelButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='instpopupMIN"+workspaceSelector+"']//a[@title='Cancel']", workspace+"Manage Investor Rename popup Cancel Button", action.BOOLEAN, 30), "visibility", 60, workspace+"Manage Investor Rename popup Cancel Button");
	}
	
	public WebElement getManageInvestorRenamePopupFieldBlankErrorMessage(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='insterrorMIN"+workspaceSelector+"']", workspace+"Rename Field Blank Error Mesage", action.BOOLEAN, 30), "visibility", 60, workspace+"Rename Field Blank Error Mesage");
	}
	
	public WebElement getManageInvestorRenamePopupCrossIcon(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='instpopupMIN"+workspaceSelector+"']//span[@title='Close']", workspace+"Cross Icon On Rename Popup", action.BOOLEAN, 30), "visibility", 60, workspace+"Cross Icon On Rename Popup");
	}
	
	public WebElement getManageInvestorFilterExpandedIcon(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//img[@id='minus_iconMIN"+workspaceSelector+"']", workspace+"Expanded Icon On Filter", action.BOOLEAN, 30), "visibility", 60, workspace+"Expanded Icon On Filter");
	}
	
	public List<WebElement> getManageInvestorFilterHeaderList(Workspace workspace){
		List<WebElement> lst= new ArrayList<WebElement>();
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			lst=FindElements(driver, "//div[@id='filterGridContactDivId_MInvestorMINFR']//tr[@class='bg']//td", workspace+"Filter popup header list");
		}else if(workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			lst=FindElements(driver, "//div[@id='filterGridContactDivId_MInvestorMININV']//tr[@class='bg']//td", workspace+"Filter popup header list");
		}		
		return lst;
	}

	
	
	public List<WebElement> getManageInvestorFilterRows(Workspace workspace){
		List<WebElement> lst= new ArrayList<WebElement>();
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			lst=FindElements(driver, "//table[@id='advancefltr_tablecustomMINFR']//tr[@class='bg_gray_light row']", workspace+"Filter rows");
		}else if(workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			lst=FindElements(driver, "//table[@id='advancefltr_tablecustomMININV']//tr[@class='bg_gray_light row']", workspace+"Filter rows");
		}		
		return lst;
	}
	
	public List<WebElement> getManageInvestorFilterFieldDropdown(Workspace workspace){
		List<WebElement> lst= new ArrayList<WebElement>();
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			lst=FindElements(driver, "//table[@id='advancefltr_tablecustomMINFR']//select[contains(@id,'aaMINFR')]", workspace+"Fields Dropdown");
		}else if(workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			lst=FindElements(driver, "//table[@id='advancefltr_tablecustomMININV']//select[contains(@id,'aaMININV')]", workspace+"Fields Dropdown");
		}		
		return lst;
	}

	public List<WebElement> getManageInvestorFilterOperatorDropdown(Workspace workspace){
		List<WebElement> lst= new ArrayList<WebElement>();
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			lst=FindElements(driver, "//table[@id='advancefltr_tablecustomMINFR']//select[contains(@id,'optMINFR')]", workspace+"Operators Dropdown");
		}else if(workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			lst=FindElements(driver, "//table[@id='advancefltr_tablecustomMININV']//select[contains(@id,'optMININV')]", workspace+"Operators Dropdown");
		}		
		return lst;
	}
	
	public List<WebElement> getManageInvestorFilterValueTextBox(Workspace workspace){
		List<WebElement> lst= new ArrayList<WebElement>();
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			lst=FindElements(driver, "//table[@id='advancefltr_tablecustomMINFR']//input", workspace+"Value Textbox");
		}else if(workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			lst=FindElements(driver, "//table[@id='advancefltr_tablecustomMININV']//input", workspace+"Value Textbox");
		}		
		return lst;
	}
	
	public List<WebElement> getManageInvestorFilterRemoveRowIcon(Workspace workspace){
		List<WebElement> lst= new ArrayList<WebElement>();
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			lst=FindElements(driver, "//table[@id='advancefltr_tablecustomMINFR']//img[@title='Remove Row']", workspace+"Remove Row Icon");
		}else if(workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			lst=FindElements(driver, "//table[@id='advancefltr_tablecustomMININV']//img[@title='Remove Row']", workspace+"Remove Row Icon");
		}		
		return lst;
	}
	public WebElement getManageInvestorFilterApplyButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='filterGridContactDivId_MInvestorMIN"+workspaceSelector+"']//a[@title='Apply']", workspace+" Apply button", action.BOOLEAN, 30), "visibility", 60, workspace+"Apply button");
	}
	
	
	public WebElement getManageInvestorFilterCollapsedIcon(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//img[@id='plus_iconMIN"+workspaceSelector+"']", workspace+"Collapsed Icon On Filter", action.BOOLEAN, 30), "visibility", 60, workspace+"Collapsed Icon On Filter");
	}
	
	
	
	public WebElement getManageInvestorFilterErrorMessage(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='filterGridContactDivId_MInvestorMIN"+workspaceSelector+"']//div[@class='error_txt']", workspace+"Error Message On Filter", action.BOOLEAN, 30), "visibility", 60, workspace+"Error Message On Filter");
	}
	

	public List<WebElement> getManageInvestorFilterfieldSelectErrorMessage(Workspace workspace){
		List<WebElement> lst= new ArrayList<WebElement>();
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			lst=FindElements(driver, "//div[contains(@id,'divfname_optMINFR')]//div", workspace+"Please select a field error message");
		}else if(workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			lst=FindElements(driver, "//div[contains(@id,'divfname_optMININV')]//div", workspace+"Please select a field error message");
		}		
		return lst;
	}
	
	@FindBy(id="theButton3")
	private WebElement insertSelectedButton;

	/**
	 * @return the insertSelectedButton
	 */
	public WebElement getInsertSelectedButton(int timeOut) {
		return isDisplayed(driver, insertSelectedButton, "Visibility", timeOut, "Look up insert Selected");
	}
	@FindBy(xpath="//a[@title='Select Year']")
	private WebElement year;

	/**
	 * @return the year
	 */
	public WebElement getYear(int timeOut) {
		return isDisplayed(driver, year, "Visibility", timeOut, "Year");
	}
	
	@FindBy(xpath="//a[@title='Previous 15 Years']")
	private WebElement yearBack;

	/**
	 * @return the year
	 */
	public WebElement getYearBack(int timeOut) {
		return isDisplayed(driver, yearBack, "Visibility", timeOut, "Year");
	}
	
	@FindBy(xpath="//a[@title='Select Month']")
	private WebElement selectMonth;

	/**
	 * @return the selectMonth
	 */
	public WebElement getSelectMonth(int timeOut) {
		return isDisplayed(driver, selectMonth, "Visibility", timeOut, "Month");
	}
	
	@FindBy(id="dtediv1")
	private WebElement calendarIcon;

	/**
	 * @return the calendarIcon
	 */
	public WebElement getCalendarIcon(int timeOut) {
		return isDisplayed(driver, calendarIcon, "Visibility", timeOut, "Calendar Icon");
	}
	//************************************Investment Info*********************************************************//
	@FindBy(xpath = "//img[@alt='Edit']")
	private WebElement investmentInfoEdit;
	
	
	/**
	 * @return the investmentInfoEdit
	 */
	public WebElement getInvestmentInfoEdit(int timeOut) {
		return isDisplayed(driver, investmentInfoEdit, "Visibility", timeOut, "investment info edit button");
	}

	public List<WebElement> getFirmName()
	{
		return FindElements(driver, "//div[@id='investmentPopId']//b[contains(text(),'Name:')]/../following-sibling::td", "view mode elements investment info window");
	}
	
	@FindBy(xpath="//div[@id='investmentPopId']//b[contains(text(),'Name:')]/../following-sibling::td")
	private WebElement firmName;
	
	/**
	 * @return the investmentInfoSaveBtn
	 */
	public WebElement getFirmName(int timeOut) {
		return isDisplayed(driver, firmName, "Visibility", timeOut, "firm name in view mode investment info window");
	}
	
	
	
	
	
	@FindBy (xpath="//div[@id='investmentPopId']//input[@value='Save']")
	private WebElement investmentInfoSaveBtn;
	
	/**
	 * @return the investmentInfoSaveBtn
	 */
	public WebElement getInvestmentInfoSaveBtn(int timeOut) {
		return isDisplayed(driver, investmentInfoSaveBtn, "Visibility", timeOut, "Investment Info Save Button");
	}

	@FindBy(xpath="//input[@id='page:frmInvest:txtFundName']")
	private WebElement investmentInfoFundNameTxtbox;
	
	/**
	 * @return the investmentInfoFundNameTxtbox
	 */
	public WebElement getInvestmentInfoFundNameTxtbox(int timeOut) {
		return isDisplayed(driver, investmentInfoFundNameTxtbox, "Visibility", timeOut, "investment Info Fund Name Txtbox in edit mode");
	}
	
	@FindBy(xpath="//input[@id='page:frmInvest:txtFundPhone']")
	private WebElement investmentInfoPhoneTxtbox;
	
	/**
	 * @return the investmentInfoFundNameTxtbox
	 */
	public WebElement getInvestmentInfoPhoneTxtbox(int timeOut) {
		return isDisplayed(driver, investmentInfoPhoneTxtbox, "Visibility", timeOut, "investment Info phone Txtbox in edit mode");
	}
	
	
	
	
	@FindBy(xpath="//span[@id='page:frmInvest:div_view']//a[@title='Cancel']")
	private WebElement investmentInfoCancelButton;
	
	/**
	 * @return the investmentInfoSaveButton
	 */
	public WebElement getInvestmentInfoCancelButton(int timeOut) {
		return isDisplayed(driver, investmentInfoCancelButton, "Visibility", timeOut, "Cancel button");
	}
	
	
	@FindBy(xpath="//div[@id='investmentPopId']//input[@value='Cancel']")
	private WebElement investmentInfoPopUpCacnelBtnInEditMode;
	
	
	
	
	/**
	 * @return the investmentInfoPopUpCacnelBtnInEditMode
	 */
	public WebElement getInvestmentInfoPopUpCacnelBtnInEditMode(int timeOut) {
		return isDisplayed(driver, investmentInfoPopUpCacnelBtnInEditMode, "Visibility", timeOut, "cancel button in edit mode");
	}

	//*****************************************************************Manage Folder************************************************************//
	public WebElement getManageFolderIcon(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "fr";
		} else {
			workspaceSelector = "inv";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='"+workspaceSelector+"workspace']//a[@title='Manage Folders']//img", workspace+" Manage Folder Icon", action.BOOLEAN, 30), "visibility", 60, workspace+"  Manage folder Icon");
	}
	
	public WebElement getManageFolderCloseButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "fundraising";
		} else {
			workspaceSelector = "investor";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@class='paginationstyle txt_center']//a[contains(@onclick,'create_remove_Investor_pop1"+workspaceSelector+"();')]", workspace+" Manage Folder Icon", action.BOOLEAN, 30), "visibility", 60, workspace+"  Manage folder Close Button");
	}
	
	@FindBy(xpath="//div[@id='recordscount2']")
	private WebElement INV_RecordCount;

	@FindBy(xpath="//div[@id='recordscount']")
	private WebElement fWR_RecordCount;
	
	/**
	 * @return the Record Count
	 * For Commitment Page use FundraisingWorkspace
	 * 
	 */
	public WebElement getRecordCount(Workspace workspace, int timeOut) {
		WebElement ele = null;
		if (workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			ele = isDisplayed(driver, fWR_RecordCount, "Visibility", timeOut, workspace + " Record Count");

		} else if (workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			ele = isDisplayed(driver, INV_RecordCount, "Visibility", timeOut, workspace + " Record Count");

		}
		return ele;

	}
	
	@FindBy(xpath="(//a[@title='Delete'])[1]")
	private WebElement deleteFileLinkContentGrid2;

	/**
	 * @return the deleteFileLinkContentGrid2
	 */
	public WebElement getDeleteFileLinkContentGrid2(int timeOut) {
		BaseLib.PublicFlag = false;
		return isDisplayed(driver, deleteFileLinkContentGrid2, "Visibility", timeOut, "Delete File link");

	}	

	
	public WebElement getAllFolderLabel(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "fr";
		} else {
			workspaceSelector = "inv";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='"+workspaceSelector+"workspace']//span[@id='add0000']/..", workspace+" all folder label", action.BOOLEAN, 30), "visibility", 60, workspace+"  All Folder Label");
	}
	
	public WebElement getAllFolderAddIcon(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "fr";
		} else {
			workspaceSelector = "inv";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='"+workspaceSelector+"workspace']//span[@id='add0000']", workspace+" all folder add icon", action.BOOLEAN, 30), "visibility", 60, workspace+"  All Folder Add Icon");
	}

	
	public WebElement getManageFolderCrossIcon(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "fr";
		} else {
			workspaceSelector = "inv";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='"+workspaceSelector+"workspace']//div[contains(@class,'ManageFolders_')]/div[@class='head_popup']/a", workspace+" Manage Folder Cross Icon", action.BOOLEAN, 30), "visibility", 60, workspace+"  Manage Folder Cross Icon");
	}
	
	public WebElement getManageFolderHeader(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "fr";
		} else {
			workspaceSelector = "inv";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='"+workspaceSelector+"workspace']//div[contains(@class,'ManageFolders_')]/div[@class='head_popup']", workspace+" Manage Folder Header", action.BOOLEAN, 30), "visibility", 60, workspace+"  Manage Folder Header");
	}
	
//	public WebElement getManageFolderCloseButton(Workspace workspace, int timeOut){
//		String workspaceSelector = "";
//		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
//			workspaceSelector = "fr";
//		} else {
//			workspaceSelector = "inv";
//		}
//		return isDisplayed(driver, FindElement(driver, "//div[@id='"+workspaceSelector+"workspace']//div[@class='paginationstyle txt_center']//a[contains(@onclick,'create')]", workspace+" Manage Folder Close Button", action.BOOLEAN, 30), "visibility", 60, workspace+"  Manage Folder Close Button");
//	}
	
	public WebElement getManageFolderYellowBoxMessage(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "fr";
		} else {
			workspaceSelector = "inv";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='"+workspaceSelector+"workspace']//div[@class='yellow_txt_box']/p", workspace+" Manage Folder Message", action.BOOLEAN, 30), "visibility", 60, workspace+"  Manage Folder Message");
	}
	
	@FindBy(xpath="//a[contains(@*,'demo-basicDF')]/img")
	private WebElement addFolderInformationIcon;

	/**
	 * @return the addFolderInformationIcon
	 */
	public WebElement getAddFolderInformationIcon(Workspace workspace, int timeOut) {
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "fr";
		} else {
			workspaceSelector = "inv";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='"+workspaceSelector+"workspace']//a[contains(@*,'demo-basicDF')]/img", workspace+" parent folder info icon", action.BOOLEAN, 30), "visibility", 60, workspace+"  parent folder info icon");
	}
	
	public WebElement getAddFolderHeader(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "fr";
		} else {
			workspaceSelector = "inv";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='"+workspaceSelector+"workspace']//div[contains(@id,'addfirstlevelpop')]//div[@class='head_popup']", workspace+" Add folder pop up header", action.BOOLEAN, 30), "visibility", 60, workspace+"  Add folder pop up header");
	}
	
	public WebElement getAddFolderCrossIcon(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "fr";
		} else {
			workspaceSelector = "inv";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='"+workspaceSelector+"workspace']//div[contains(@id,'addfirstlevelpop')]//div[@class='head_popup']/span", workspace+" Add folder pop up cross icon", action.BOOLEAN, 30), "visibility", 60, workspace+"  Add folder pop up cross icon");

	}
	
	
	
	public WebElement uploadWindowInstitutionTextElementInvestorSide(String inst,String lp) {
		return isDisplayed(driver, FindElement(driver,"//span[text()='"+inst+"']/../../../../..//span[text()='"+lp+"']" , "institution and LP  present in standard folder upload window", action.SCROLLANDBOOLEAN, 30), "visibility", 30, "institution present in standard folder uplaod window");
	}
	
	public WebElement uploadWindowInstitutionInputElementInvestorSide(String inst,String lp) {
		return isDisplayed(driver, FindElement(driver,"//*[text()='"+inst+"']/../../../../..//*[text()='"+lp+"']/../input" , "institution and LP Input in standard folder upload window", action.SCROLLANDBOOLEAN, 30), "visibility", 30, "institution present in standard folder uplaod window");
	}
	
	public WebElement uploadWindowLimitedTextElementInvestorSide(String lp) {
		return isDisplayed(driver, FindElement(driver,"//span[text()='"+lp+"']" , "LP  present in standard folder upload window", action.SCROLLANDBOOLEAN, 30), "visibility", 30, "institution present in standard folder uplaod window");
	}
	
	public WebElement getAddFolderChildHeader(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "fr";
		} else {
			workspaceSelector = "inv";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='"+workspaceSelector+"workspace']//div[contains(@id,'addfolderpop')]//div[@class='head_popup']", workspace+" Add folder pop up header", action.BOOLEAN, 30), "visibility", 60, workspace+"  Add folder pop up header");
	}
	
	public WebElement getAddFolderChildCrossIcon(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "fr";
		} else {
			workspaceSelector = "inv";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='"+workspaceSelector+"workspace']//div[contains(@id,'addfolderpop')]//div[@class='head_popup']/span", workspace+" Add folder pop up cross icon", action.BOOLEAN, 30), "visibility", 60, workspace+"  Add folder pop up cross icon");
	}

	
	public WebElement getAddFolderChildCancelButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "fr";
		} else {
			workspaceSelector = "inv";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='"+workspaceSelector+"workspace']//div[contains(@id,'addfolderpop')]//div[@class='head_popup']/following-sibling::div[@class='paginationstyle txt_center']/a[@title='Cancel']", workspace+" Add folder pop up cancel button", action.BOOLEAN, 30), "visibility", 60, workspace+"  Add folder pop up Cancel Button");
	}
	
	public WebElement getAddFolderChildSaveButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "fr";
		} else {
			workspaceSelector = "inv";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='"+workspaceSelector+"workspace']//div[contains(@id,'addfolderpop')]//div[@class='head_popup']/following-sibling::div[@class='paginationstyle txt_center']/a[@title='Save']", workspace+" Add folder pop up Save button", action.BOOLEAN, 30), "visibility", 60, workspace+"  Add folder pop up Save Button");
	}
	
	public WebElement getAddFolderChildAddingUnderFolderName(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "fr";
		} else {
			workspaceSelector = "inv";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='"+workspaceSelector+"workspace']//span[text()='Adding Folder Under:']/../following-sibling::td//span", workspace+" Add folder pop up Adding under label", action.BOOLEAN, 30), "visibility", 60, workspace+"  Add folder pop up Adding under label");
	}
	

	public WebElement getFundNameField(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW" + workspaceSelector+ "']//b[text()='Name:']/../following-sibling::td//input", "Fund Name Field", action.BOOLEAN, timeOut), "Visibility", timeOut, "Fund Name Field");
	}
	
	public WebElement getFundSizeField(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW" + workspaceSelector+ "']//b[text()='Size (in millions):']/../following-sibling::td//input", "Fund Size Field", action.BOOLEAN, timeOut), "Visibility", timeOut, "Fund Size Field");
	}
	
	public WebElement getFundVintageYearField(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW" + workspaceSelector+ "']//b[text()='Vintage (year):']/../following-sibling::td//input", "Fund Vintage Year Field", action.BOOLEAN, timeOut), "Visibility", timeOut, "Fund Vintage Year Field");
	}
	
	public WebElement getFundContactField(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW" + workspaceSelector+ "']//b[text()='Contact:']/../following-sibling::td//input", "Fund Contact Field", action.BOOLEAN, timeOut), "Visibility", timeOut, "Fund Contact Field");
	}
	
	public WebElement getFundPhoneField(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW" + workspaceSelector+ "']//b[text()='Phone:']/../following-sibling::td//input", "Fund Phone Field", action.BOOLEAN, timeOut), "Visibility", timeOut, "Fund Phone Field");
	}
	
	public WebElement getFundEmailField(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW" + workspaceSelector+ "']//b[text()='E-mail:']/../following-sibling::td//input", "Fund Email Field", action.BOOLEAN, timeOut), "Visibility", timeOut, "Fund Email Field");
	}
	
	public WebElement getFundDescriptionField(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW" + workspaceSelector+ "']//td[text()='Description:']/following-sibling::td/textarea", "Fund Description Field", action.BOOLEAN, timeOut), "Visibility", timeOut, "Fund Description Field");
	}
	
	public WebElement getFundFundraisingYesRadioButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW"+ workspaceSelector +"']//b[text()='Fundraising?']/../following-sibling::td//input[@id='chkFundraisingYBW"+ workspaceSelector+"']", "Fundraising Yes Radio Button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Fundraising Yes Radio Button");
	}
	
	public WebElement getFundFundraisingNoRadioButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW"+ workspaceSelector +"']//b[text()='Fundraising?']/../following-sibling::td//input[@id='chkFundraisingNBW"+ workspaceSelector+"']", "Fundraising No Radio Button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Fundraising No Radio Button");
	}
	
	public WebElement getStep1Of3CancelButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW"+ workspaceSelector +"']//input[@title='Cancel']", "Step 1Of3 Cancel Button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Step 1Of3 Cancel Button");
	}
	
	public WebElement getStep2Of3CancelButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW"+ workspaceSelector +"']//a[@title='Cancel'][contains(@onclick,'clearFund')]", "Step 2Of3 Cancel Button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Step 2Of3 Cancel Button");
	}
	
	public WebElement getStep1Of3CrossIcon(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW"+ workspaceSelector +"']//div[text()='Build Workspace (Step 1 of 3) ']//a[@title='Close']", "Step 1Of3 Cross Icon", action.BOOLEAN, timeOut), "Visibility", timeOut, "Step 1Of3 Cross Icon");
	}
	
	public WebElement getStep2Of3CrossIcon(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW"+ workspaceSelector +"']//div[text()='Build Workspace (Step 2 of 3) ']//a[@title='Close']", "Step 2Of3 Cross Icon", action.BOOLEAN, timeOut), "Visibility", timeOut, "Step 2Of3 Cross Icon");
	}
	
	public WebElement getStep3Of3CrossIcon(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW"+ workspaceSelector +"']//div[text()='Build Workspace (Step 3 of 3) ']//a[@title='Close']", "Step 3Of3 Cross Icon", action.BOOLEAN, timeOut), "Visibility", timeOut, "Step 3Of3 Cross Icon");
	}
	
	public WebElement getFundNameFieldErrorMessage(Workspace workspace, int errorMessageNumber, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW"+ workspaceSelector +"']//b[text()='Name:']/../following-sibling::td/span["+ (errorMessageNumber + 1) +"]", "Fund Name Field Error Message", action.BOOLEAN, timeOut), "Visibility", timeOut, "Fund Name Field Error Message");
	}
	
	public WebElement getFundContactFieldErrorMessage(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW"+ workspaceSelector +"']//b[text()='Contact:']/../following-sibling::td/span[2]", "Fund Contact Field Error Message", action.BOOLEAN, timeOut), "Visibility", timeOut, "Fund Contact Field Error Message");
	}
	
	public WebElement getFundPhoneFieldErrorMessage(Workspace workspace, int errorMessageNumber, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW"+ workspaceSelector +"']//b[text()='Phone:']/../following-sibling::td/span["+ (errorMessageNumber + 1) +"]", "Fund Phone Field Error Message", action.BOOLEAN, timeOut), "Visibility", timeOut, "Fund Phone Field Error Message");
	}
	
	public WebElement getFundEmailFieldErrorMessage(Workspace workspace, int errorMessageNumber, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW"+ workspaceSelector +"']//b[text()='E-mail:']/../following-sibling::td/span["+ (errorMessageNumber + 1) +"]", "Fund Email Field Error Message", action.BOOLEAN, timeOut), "Visibility", timeOut, "Fund Email Field Error Message");
	}
	
	public WebElement getFundSizeFieldErrorMessage(Workspace workspace, int errorMessageNumber, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW"+ workspaceSelector +"']//b[text()='Size (in millions):']/../following-sibling::td/span["+ (errorMessageNumber + 1) +"]", "Fund Size Field Error Message", action.BOOLEAN, timeOut), "Visibility", timeOut, "Fund Size Field Error Message");
	}
	
	public WebElement getFundVintageYearFieldErrorMessage(Workspace workspace, int errorMessageNumber, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW"+ workspaceSelector +"']//b[text()='Vintage (year):']/../following-sibling::td/span["+ (errorMessageNumber + 1) +"]", "Fund vintage year Field Error Message", action.BOOLEAN, timeOut), "Visibility", timeOut, "Fund vintage year Field Error Message");
	}
	
	public WebElement getStep1Of3Header(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW"+ workspaceSelector +"']//div[text()='Build Workspace (Step 1 of 3) ']", "Step 1 Of 3 Header", action.BOOLEAN, timeOut), "Visibility", timeOut, "Step 1 Of 3 Header");
	}
		//////////////////////////////////////////AlertHistory////////////////////////////////////////////////
	
	public WebElement getAlertHistoryPopupHeader(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='alert_gridACTALT"+workspaceSelector+"']//div[@class='head_popup']", "Alert history popup", action.BOOLEAN, timeOut), "Visibility", timeOut, "Alert history popup");
	}
	
	public WebElement getStep2Of3Header(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW"+ workspaceSelector +"']//div[text()='Build Workspace (Step 2 of 3) ']", "Step 2 Of 3 Header", action.BOOLEAN, timeOut), "Visibility", timeOut, "Step 2 Of 3 Header");
	}
	
	public WebElement getStep3Of3Header(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW"+ workspaceSelector +"']//div[text()='Build Workspace (Step 3 of 3) ']", "Step 3 Of 3 Header", action.BOOLEAN, timeOut), "Visibility", timeOut, "Step 3 Of 3 Header");
	}
	
	public WebElement getStep2Of3YellowBoxText(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='step_2of3BW"+ workspaceSelector +"']//div[@class='yellow_txt_box']", "Step 2 Of 3 Yellow box text", action.BOOLEAN, timeOut), "Visibility", timeOut, "Step 2 Of 3 Yellow box text");
	}
	
	public WebElement getImportFolderTemplateButton(Workspace workspace, EnableDisable enableDisable, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		if(enableDisable.toString().equalsIgnoreCase(EnableDisable.Enable.toString())){
			return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW"+ workspaceSelector +"']//a[@title='Import Folder Template'][contains(@class,'btn_active')]", "Enabled Import Folder Template Button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Enabled Import Folder Template Button");
		} else {
			return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW"+ workspaceSelector +"']//a[@title='Import Folder Template'][contains(@class,'btn_deactive')]", "Disabled Import Folder Template Button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Disabled Import Folder Template Button");
		}
	}
	
	public WebElement getClearAllFolderButton(Workspace workspace, EnableDisable enableDisable, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		if(enableDisable.toString().equalsIgnoreCase(EnableDisable.Enable.toString())){
			return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW"+ workspaceSelector +"']//a[@title='Clear All Folders'][contains(@class,'btn_active')]", "Enabled Clear All Folder Button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Enabled Clear All Folder Button");
		} else {
			return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW"+ workspaceSelector +"']//a[@title='Clear All Folders'][contains(@class,'btn_deactive')]", "Disabled Clear All Folder Button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Disabled Clear All Folder Button");
		}
	}
	
	public WebElement getAllFolderAddAFolderButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
			return isDisplayed(driver, FindElement(driver, "//div[@id='mainDivOfBuildWorkspaceBW"+ workspaceSelector +"']//span[contains(text(),'All Folders')]//span[@title='Add a Folder']", "All Folder Add a folder Button", action.BOOLEAN, timeOut), "Visibility", timeOut, "All Folder Add a folder Button");
	}
	
	public WebElement getPopUpButtomMessage(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
			return isDisplayed(driver, FindElement(driver, "//div[@id='idImportTemplateBW"+ workspaceSelector +"']/following-sibling::div[2]", "Pop Up Bottom Message", action.BOOLEAN, timeOut), "Visibility", timeOut, "Pop Up Bottom Message");
	}
	
	public WebElement getStep3Of3YellowBoxText(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='step_3of3BW"+ workspaceSelector +"']//div[@class='yellow_txt_box']", "Step 3 Of 3 Yellow box text", action.BOOLEAN, timeOut), "Visibility", timeOut, "Step 3 Of 3 Yellow box text");
	}
	
	public WebElement getInvestorFilterExpandOrCollapseIcon(Workspace workspace, ExpandCollapse expandcollapse, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		if(expandcollapse.toString().equalsIgnoreCase(ExpandCollapse.Expand.toString())){
			return isDisplayed(driver, FindElement(driver, "//img[contains(@id,'minus_iconMIN" + workspaceSelector + "')]", "Expand collapse Icon", action.BOOLEAN, timeOut), "Visibility", timeOut, "Expand collapse Icon");
		} else {
			return isDisplayed(driver, FindElement(driver, "//img[contains(@id,'plus_iconMIN" + workspaceSelector + "')]", "Expand collapse Icon", action.BOOLEAN, timeOut), "Visibility", timeOut, "Expand collapse Icon");
		}
	}
		
	
	public WebElement getAlertHistoryPopupShowLabel(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
		workspaceSelector = "FR";
		} else {
		workspaceSelector = "INV";
		}
	return isDisplayed(driver, FindElement(driver, "(//div[@id='popupDataActivityACTALT"+workspaceSelector+"']//strong)[1]", "Alert history popup show label", action.BOOLEAN, timeOut), "Visibility", timeOut, "Alert history popup show label");
	}
	
	public WebElement getAlertHistoryPopupRangeLabel(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
		workspaceSelector = "FR";
		} else {
		workspaceSelector = "INV";
		}
	return isDisplayed(driver, FindElement(driver, "(//div[@id='popupDataActivityACTALT"+workspaceSelector+"']//strong)[2]", "Alert history popup Range label", action.BOOLEAN, timeOut), "Visibility", timeOut, "Alert history popup Range label");
	}

	public WebElement getAlertHistoryPopupShowDropdown(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
		workspaceSelector = "FR";
		} else {
		workspaceSelector = "INV";
		}
	return isDisplayed(driver, FindElement(driver, "//select[@id='range1ACTALT"+workspaceSelector+"']", "Alert history popup show dropdown", action.BOOLEAN, timeOut), "Visibility", timeOut, "Alert history popup show dropdown");
	}
	
	public WebElement getAlertHistoryPopupRangeDropdown(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
		workspaceSelector = "FR";
		} else {
		workspaceSelector = "INV";
		}
	return isDisplayed(driver, FindElement(driver, "//select[@id='range2ACTALT"+workspaceSelector+"']", "Alert history popup range dropdown", action.BOOLEAN, timeOut), "Visibility", timeOut, "Alert history popup Range dropdown");
	}
	
	
	public List<WebElement> getAlertHitoryColoumnList(Workspace workspace){
		List<WebElement> lst= new ArrayList<WebElement>();
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			lst=FindElements(driver, "//span[@id='myGridACTALTFR-headers']//span[contains(@class,'aw-item-text ')]", workspace+"Alert History Coloumns");
		}else if(workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			lst=FindElements(driver, "//span[@id='myGridACTALTINV-headers']//span[contains(@class,'aw-item-text ')]", workspace+"Alert History Coloumns");
		}		
		return lst;
	}
	
	public WebElement getAlertHistoryPopupRecordLabelWithValue(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
		workspaceSelector = "FR";
		} else {
		workspaceSelector = "INV";
		}
	return isDisplayed(driver, FindElement(driver, "//div[@id='recordscountACTALT"+workspaceSelector+"']", "Alert history popup record label with value", action.BOOLEAN, timeOut), "Visibility", timeOut, "Alert history popup record label with value");
	}
	
	
	/**
	 * Should not be used by anyOne 
	 */
	public WebElement getStep3Of3FieldDropDown(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//select[@class='input_txt'][@id='a1aaMIN" + workspaceSelector + "']", "Field Drop down", action.BOOLEAN, timeOut), "Visibility", timeOut, "Field Drop down");
	}
	
	/**
	 * Should not be used by anyOne 
	 */
	public WebElement getStep3Of3OperatorDropDown(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//select[@class='input_txt'][@id='optMIN" + workspaceSelector + "1']", "Operator Drop down", action.BOOLEAN, timeOut), "Visibility", timeOut, "Operator Drop down");
	}
	
	/**
	 * Should not be used by anyOne 
	 */
	public WebElement getStep3Of3FilterTextBox(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//input[@class='input_txt'][@id='criteriatextboxMIN" + workspaceSelector + "1']", "Criterion text box", action.BOOLEAN, timeOut), "Visibility", timeOut, "Criterion text box");
	}
	
	public WebElement getStep3Of3AddRowLink(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//span[@id='AdvanceFlterAddRowNewMIN" + workspaceSelector + "']", "Add Row link", action.BOOLEAN, timeOut), "Visibility", timeOut, "Add Row link");
	}
	
	public WebElement getStep3Of3ApplyButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//a[text()='Filter Investors']/../../following-sibling::div//a[@title='Apply'][contains(@onclick,'MIN" + workspaceSelector + "')]", "Apply Button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Apply Button");
	}
	
	public WebElement getStep3Of3ClearButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//a[text()='Filter Investors']/../../following-sibling::div//a[@title='Clear'][contains(@onclick,'MIN" + workspaceSelector + "')]", "Clear Button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Clear Button");
	}
	
	@FindBy(xpath="//span[@id='grid_SelectedTargetAccounts-cell-1-0']/span")
	private WebElement step3Of3NoDataToDisplayErrorMessage;

	/**
	 * @return the step3Of3NoDataToDisplayErrorMessage
	 */
	public WebElement getStep3Of3NoDataToDisplayErrorMessage(int timeOut) {
		return isDisplayed(driver, step3Of3NoDataToDisplayErrorMessage, "Visibility", timeOut, "");
	}
	
	public WebElement getSelectFolderTemplateDropDown(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//select[contains(@id,'BW"+workspaceSelector+":CompBuiltWorkspace:selectlstId')]", "Select Folder Template Drop down", action.BOOLEAN, timeOut), "Visibility", timeOut, "Select Folder Template Drop Down");
	}
	
	public WebElement getSelectFolderTemplateCancelButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//a[@class='btn_active ShowHide_SelectFolderTemplateBW"+ workspaceSelector +"'][@title='Cancel']", "Select Folder Template Cancel Button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Select Folder Template Cancel Button");
	}
	
	@FindBy(xpath = "//span[@id='BuildDealDetail_grid-cell-1-0']/b")
	private WebElement selectFolderTemplateErrorMessage;

	/**
	 * @return the selectFolderTemplateErrorMessage
	 */
	public WebElement getSelectFolderTemplateErrorMessage(int timeOut) {
		return isDisplayed(driver, selectFolderTemplateErrorMessage, "Visibility", timeOut, "No Data To display message");
	}
	
	public WebElement getSelectFolderTemplateHeader(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//span[contains(@class,'SelectFolderTemplateBW"+ workspaceSelector +"')]/..", "Select Folder Template Header", action.BOOLEAN, timeOut), "Visibility", timeOut, "Select Folder Template Header");
	}
	
	public WebElement getSelectFolderTemplateDisplayText(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//select[contains(@id,'BW"+ workspaceSelector +":CompBuiltWorkspace:selectlstId')]/preceding-sibling::strong", "Select Folder Template Display text", action.BOOLEAN, timeOut), "Visibility", timeOut, "Select Folder Template Display text");
	}
	
	@FindBy(xpath="//span[@id='BuildDealDetail_grid-headers-start']/following-sibling::span[contains(@id,'BuildDealDetail_grid-header-')]")
	private List<WebElement> selectFolderTemplateGridHeader;

	/**
	 * @return the selectFolderTemplateGridHeader
	 */
	public List<WebElement> getSelectFolderTemplateGridHeader(int timeOut) {
		return selectFolderTemplateGridHeader;
	}
	
	@FindBy(xpath="//span[@id='BuildDealDetail_grid-rows-start']/following-sibling::span//span[contains(@id,'BuildDealDetail_grid-cell-1')][1]/span")
	private List<WebElement> selectFolderTemplateTemplateName;

	/**
	 * @return the selectFolderTemplateTemplateName
	 */
	public List<WebElement> getSelectFolderTemplateTemplateName(int timeOut) {
		return selectFolderTemplateTemplateName;
	}
	
	public WebElement getSelectFolderTemplateRecordLabel(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//span[@id='grid2BW"+ workspaceSelector +"']/..", "Select Folder Template record label", action.BOOLEAN, timeOut), "Visibility", timeOut, "Select Folder Template Record label");
	}
	
	public WebElement getSelectFolderTemplateRecordCount(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//span[@id='grid2BW"+ workspaceSelector +"']", "Select Folder Template record Count", action.BOOLEAN, timeOut), "Visibility", timeOut, "Select Folder Template Record Count");
	}
	
	public WebElement getSelectFolderTemplateImportButton(Workspace workspace, EnableDisable enableDisable, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		if(enableDisable.toString().equalsIgnoreCase(EnableDisable.Enable.toString())){
			return isDisplayed(driver, FindElement(driver, "//a[@id='ImportActiveBW"+ workspaceSelector +"']", "Enabled Import Button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Enabled Import Button");
		} else {
			return isDisplayed(driver, FindElement(driver, "//a[@id='ImportDeactiveBW"+ workspaceSelector +"']", "Disabled Import Button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Disabled Import Button");
		}
	}
	
	public WebElement getSelectFolderTemplateCrossIcon(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[text()='Select Folder Template ']/span[@class='fancybox-close ShowHide_SelectFolderTemplateBW"+ workspaceSelector +"']", "Select Folder Template Cross Icon", action.BOOLEAN, timeOut), "Visibility", timeOut, "Select Folder Template Cross Icon");
	}
	
	public WebElement getImportFolderTemplateErrorMessage(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='errMsgImportBW"+ workspaceSelector +"']", "Import Folder Template ErrorMessage", action.BOOLEAN, timeOut), "Visibility", timeOut, "Import Folder Template Error Message");
	}
	
	public WebElement getImportFolderTemplateErrorMessageOkButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='errMsgImportBW"+ workspaceSelector +"']/../following-sibling::div//a", "Import Folder Template ErrorMessage Ok Button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Import Folder Template Error Message Ok Button");
	}
	
	public WebElement getImportFolderTemplateErrorMessageCrossIcon(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='errMsgImportBW"+ workspaceSelector +"']/../preceding-sibling::div//a", "Import Folder Template ErrorMessage Cross Icon", action.BOOLEAN, timeOut), "Visibility", timeOut, "Import Folder Template Error Message Cross Icon");
	}
	
	public WebElement getImportFolderTemplateErrorMessageHeader(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='errMsgImportBW"+ workspaceSelector +"']/../preceding-sibling::div[@class='head_popup']", "Import Folder Template ErrorMessage Header", action.BOOLEAN, timeOut), "Visibility", timeOut, "Import Folder Template Error Message Header");
	}
	
	public WebElement getClearAllFolderConfirmationPopUpMessage(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//a[@onclick='funClearAllFolderBW"+ workspaceSelector +"();']/../preceding-sibling::p", "Clear All Folder Confirmation Pop Up Message", action.BOOLEAN, timeOut), "Visibility", timeOut, "Clear All Folder Confirmation Pop Up Message");
	}
	
	public WebElement getClearAllFolderConfirmationPopUpYesButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//a[@onclick='funClearAllFolderBW"+ workspaceSelector +"();']", "Yes Button", action.BOOLEAN, timeOut), "Visibility", timeOut, "Yes Button");
	}
	
	public WebElement getClearAllFolderConfirmationPopUpNoButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//a[@onclick='funClearAllFolderBW"+ workspaceSelector +"();']/following-sibling::a", "No Button", action.BOOLEAN, timeOut), "Visibility", timeOut, "No Button");
	}
	
	public WebElement getClearAllFolderConfirmationPopUpCrossIcon(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//a[@onclick='funClearAllFolderBW"+ workspaceSelector +"();']/../preceding-sibling::div[@class='head_popup']/span", "Cross Icon", action.BOOLEAN, timeOut), "Visibility", timeOut, "Cross Icon");
	}
	
	public WebElement getClearAllFolderConfirmationPopUpHeader(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//a[@onclick='funClearAllFolderBW"+ workspaceSelector +"();']/../preceding-sibling::div[@class='head_popup']", "Confirmation Header", action.BOOLEAN, timeOut), "Visibility", timeOut, "Confirmation Header");
	}
	
	public WebElement getInvestmentInfoLink(Workspace workspace, String fundName, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "frworkspace";
		} else {
			workspaceSelector = "invworkspace";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='"+ workspaceSelector +"']//span[@title='"+ fundName +"']//a", "Investment Info Link", action.BOOLEAN, timeOut), "Visibility", timeOut, "Investment Info Link");
	}
	
	@FindBy(xpath="//div[contains(text(),'Investment Info')]/following-sibling::div//b[text()='Name:']/../following-sibling::td")
	private WebElement investmentInfoName;

	/**
	 * @return the investmentInfoName
	 */
	public WebElement getInvestmentInfoName(int timeOut) {
		return isDisplayed(driver, investmentInfoName, "Visibility", timeOut, "Name:");
	}
	
	@FindBy(xpath="//div[contains(text(),'Investment Info')]/following-sibling::div//b[text()='Size (in millions):']/../following-sibling::td")
	private WebElement investmentInfoSize;

	/**
	 * @return the investmentInfoSize
	 */
	public WebElement getInvestmentInfoSize(int timeOut) {
		return isDisplayed(driver, investmentInfoSize, "Visibility", timeOut, "Size:");
	}
	
	@FindBy(xpath="//div[contains(text(),'Investment Info')]/following-sibling::div//b[text()='Vintage (year):']/../following-sibling::td")
	private WebElement investmentInfoVintageYear;

	/**
	 * @return the investmentInfoVintageYear
	 */
	public WebElement getInvestmentInfoVintageYear(int timeOut) {
		return isDisplayed(driver, investmentInfoVintageYear, "Visibility", timeOut, "Vintage YEar");
	}
	
	@FindBy(xpath="//div[contains(text(),'Investment Info')]/following-sibling::div//b[text()='Contact:']/../following-sibling::td")
	private WebElement investmentInfoContact;

	/**
	 * @return the investmentInfoContact
	 */
	public WebElement getInvestmentInfoContact(int timeOut) {
		return isDisplayed(driver, investmentInfoContact, "Visibility", timeOut, "Contact: ");
	}
	
	@FindBy(xpath="//div[contains(text(),'Investment Info')]/following-sibling::div//b[text()='Phone:']/../following-sibling::td")
	private WebElement investmentInfoPhone;

	/**
	 * @return the investmentInfoPhone
	 */
	public WebElement getInvestmentInfoPhone(int timeOut) {
		return isDisplayed(driver, investmentInfoPhone, "Visibility", timeOut, "Phone");
	}
	
	@FindBy(xpath="//div[contains(text(),'Investment Info')]/following-sibling::div//b[text()='E-mail:']/../following-sibling::td")
	private WebElement investmentInfoEmail;

	/**
	 * @return the investmentInfoEmail
	 */
	public WebElement getInvestmentInfoEmail(int timeOut) {
		return isDisplayed(driver, investmentInfoEmail, "Visibility", timeOut, "Email: ");
	}
	
	@FindBy(xpath="//div[contains(text(),'Investment Info')]/following-sibling::div//b[text()='Description:']/../following-sibling::td")
	private WebElement investmentInfoDescription;

	/**
	 * @return the investmentInfoDescription
	 */
	public WebElement getInvestmentInfoDescription(int timeOut) {
		return isDisplayed(driver, investmentInfoDescription, "Visibility", timeOut, "Description");
	}
	
	@FindBy(xpath="//div[contains(text(),'Investment Info')]//img")
	private WebElement investmentInfoEditIcon;

	/**
	 * @return the investmentInfoEditIcon
	 */
	public WebElement getInvestmentInfoEditIcon(int timeOut) {
		return isDisplayed(driver, investmentInfoEditIcon, "Visibility", timeOut, "Edit Icon");
	}
	
	@FindBy(xpath="//div[contains(text(),'Investment Info')]/following-sibling::div//b[text()='Fundraising?']/../following-sibling::td//input[@id='chkFundraisingY']")
	private WebElement investmentInfoFundraisingYesRadioButton;

	/**
	 * @return the investmentInfoFundraisingYesRadioButton
	 */
	public WebElement getInvestmentInfoFundraisingYesRadioButton(int timeOut) {
		return isDisplayed(driver, investmentInfoFundraisingYesRadioButton, "Visibility", timeOut, "Yes Radio Button");
	}
	
	@FindBy(xpath="//div[contains(text(),'Investment Info')]/following-sibling::div//b[text()='Fundraising?']/../following-sibling::td//input[@id='chkFundraisingN']")
	private WebElement investmentInfoFundraisingNoRadioButton;

	/**
	 * @return the investmentInfoFundraisingNoRadioButton
	 */
	public WebElement getInvestmentInfoFundraisingNoRadioButton(int timeOut) {
		return isDisplayed(driver, investmentInfoFundraisingNoRadioButton, "Visibility", timeOut, "No Radio Button");
	}
	
	@FindBy(xpath="//span[text()='Institutions']")
	private WebElement insitutionsColumnHeader;

	/**
	 * @return the insitutionsColumn
	 */
	public WebElement getInsitutionsColumnHeader(int timeOut) {
		return isDisplayed(driver, insitutionsColumnHeader, "Visibility", timeOut, "Institution Column Header");
	}
	
	@FindBy(xpath="//span[text()='Institutions']/../../../../following-sibling::span[1]//span[contains(@id,'grid_SelectedTargetAccounts-row-')]//span[contains(@id,'grid_SelectedTargetAccounts-cell-1-')]/div")
	private List<WebElement> step3Of3InstitutionsList;

	/**
	 * @return the step3Of3InstitutionsList
	 */
	public List<WebElement> getStep3Of3InstitutionsList() {
		return step3Of3InstitutionsList;
	}

	public WebElement getInvestorAddedConfirmationMessage(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='Confirmation_added_MIidMIN"+ workspaceSelector +"']//div[text()='Confirmation ']/following-sibling::p", "Investor added message", action.BOOLEAN, timeOut), "Visibility", timeOut, "Investor added message");
	}
	
	public WebElement getInvestorAddedConfirmationCrossIcon(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='Confirmation_added_MIidMIN"+ workspaceSelector +"']//div[text()='Confirmation ']/following-sibling::div/a", "Investor added Cross icon", action.BOOLEAN, timeOut), "Visibility", timeOut, "Investor added Cross icon");
	}
	
	public WebElement getInvestorAddedConfirmationHeader(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='Confirmation_added_MIidMIN"+ workspaceSelector +"']//div[text()='Confirmation ']", "Investor added Header", action.BOOLEAN, timeOut), "Visibility", timeOut, "Investor added Header");
	}
	
	public WebElement getInvestorRemoveConfirmationMessage(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='Confirmation_removed_MIidMIN"+ workspaceSelector +"']//div[text()='Confirmation ']/following-sibling::p", "Investor remove message", action.BOOLEAN, timeOut), "Visibility", timeOut, "Investor remove message");
	}
	
	public WebElement getInvestorRemoveConfirmationCrossIcon(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='Confirmation_removed_MIidMIN"+ workspaceSelector +"']//div[text()='Confirmation ']/following-sibling::div/a", "Investor remove Cross icon", action.BOOLEAN, timeOut), "Visibility", timeOut, "Investor remove Cross icon");
	}
	
	public WebElement getInvestorRemoveConfirmationHeader(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//div[@id='Confirmation_removed_MIidMIN"+ workspaceSelector +"']//div[text()='Confirmation ']", "Investor remove Header", action.BOOLEAN, timeOut), "Visibility", timeOut, "Investor remove Header");
	}
	
	public WebElement getInvestorRemoveConfirmationCloseButton(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "(//div[text()='Confirmation ']/following-sibling::div/a[contains(@onclick,'MIidMIN"+workspaceSelector+"')])[2]", "Investor removed cancel button", action.BOOLEAN, 30), "visibility", 60, "Investor removed cancel button");
	}
	
	@FindBy(xpath="//strong[text()='Size (in millions):']/../following-sibling::td//input")
	private WebElement investmentInfoEditSize;

	/**
	 * @return the investmentInfoSize
	 */
	public WebElement getInvestmentInfoEditSize(int timeOut) {
		return isDisplayed(driver, investmentInfoEditSize, "Visibility", timeOut, "Size in million");
	}
	
	@FindBy(xpath="//strong[text()='Vintage (year):']/../following-sibling::td//input")
	private WebElement investmentInfoEditVintageYear;

	/**
	 * @return the investmentInfoEditVintageYear
	 */
	public WebElement getInvestmentInfoEditVintageYear(int timeOut) {
		return isDisplayed(driver, investmentInfoEditVintageYear, "Visibility", timeOut, "Vintage year");
	}
	
	@FindBy(xpath="//strong[text()='Contact:']/../following-sibling::td//input")
	private WebElement investmentInfoEditContactName;

	/**
	 * @return the investmentInfoEditContactName
	 */
	public WebElement getInvestmentInfoEditContactName(int timeOut) {
		return isDisplayed(driver, investmentInfoEditContactName, "Visibility", timeOut, "Contact name");
	}
	
	@FindBy(xpath="//strong[text()='Phone:']/../following-sibling::td//input")
	private WebElement investmentInfoEditPhone;

	/**
	 * @return the investmentInfoEditPhone
	 */
	public WebElement getInvestmentInfoEditPhone(int timeOut) {
		return isDisplayed(driver, investmentInfoEditPhone, "Visibility", timeOut, "Phone");
	}
	
	@FindBy(xpath="(//b[text()='E-mail:']/../following-sibling::td//input)[2]")
	private WebElement investmentInfoEditEmail;

	/**
	 * @return the investmentInfoEditEmail
	 */
	public WebElement getInvestmentInfoEditEmail(int timeOut) {
		return isDisplayed(driver, investmentInfoEditEmail, "Visibility", timeOut, "Email");
	}
	
	@FindBy(xpath="//b[text()=' Description:']/../following-sibling::td/textarea")
	private WebElement investmentInfoEditDescription;

	/**
	 * @return the investmentInfoEditDescription
	 */
	public WebElement getInvestmentInfoEditDescription(int timeOut) {
		return isDisplayed(driver, investmentInfoEditDescription, "Visibility", timeOut, "Description");
	}
	
	@FindBy(xpath="//span[@id='vldfndname']")
	private WebElement investmentInfoEditFundNameEmptyErrorMessage;

	public WebElement getInvestmentInfoEditFundNameEmptyErrorMessage(int timeOut) {
		return isDisplayed(driver, investmentInfoEditFundNameEmptyErrorMessage, "Visibility", timeOut, "empty text box error message");
	}
	
	@FindBy(xpath="//span[@id='errEnterFundCon']")
	private WebElement investmentInfoEditFundContactEmptyErrorMessage;

	public WebElement getInvestmentInfoEditFundContactEmptyErrorMessage(int timeOut) {
		return isDisplayed(driver, investmentInfoEditFundContactEmptyErrorMessage, "Visibility", timeOut, "empty text box error message");
	}
	@FindBy(xpath="//span[@id='errEnterPhone']")
	private WebElement investmentInfoEditFundPhoneEmptyErrorMessage;

	public WebElement getInvestmentInfoEditFundPhoneEmptyErrorMessage(int timeOut) {
		return isDisplayed(driver, investmentInfoEditFundPhoneEmptyErrorMessage, "Visibility", timeOut, "empty text box error message");
	}
	@FindBy(xpath="//span[@id='errEnterEmail']")
	private WebElement investmentInfoEditFundEmailEmptyErrorMessage;

	public WebElement getInvestmentInfoEditFundEmailEmptyErrorMessage(int timeOut) {
		return isDisplayed(driver, investmentInfoEditFundEmailEmptyErrorMessage, "Visibility", timeOut, "empty text box error message");
	}
	
	@FindBy(xpath="//span[@id='vldfndname']")
	private WebElement investmentInfoEditInvalidFundNameErrorMessage;

	public WebElement getInvestmentInfoEditInvalidFundNameErrorMessage(int timeOut) {
		return isDisplayed(driver, investmentInfoEditInvalidFundNameErrorMessage, "Visibility", timeOut, "Invalid data error message");
	}
	
	@FindBy(xpath="//span[@id='vldPhone']")
	private WebElement investmentInfoEditInvalidFundPhoneErrorMessage;

	public WebElement getInvestmentInfoEditInvalidFundPhoneErrorMessage(int timeOut) {
		return isDisplayed(driver, investmentInfoEditInvalidFundPhoneErrorMessage, "Visibility", timeOut, "Invalid data error message");
	}
	
	@FindBy(xpath="//span[@id='vldusrnam']")
	private WebElement investmentInfoEditInvalidFundEmailErrorMessage;

	public WebElement getInvestmentInfoEditInvalidFundEmailErrorMessage(int timeOut) {
		return isDisplayed(driver, investmentInfoEditInvalidFundEmailErrorMessage, "Visibility", timeOut, "Invalid data error message");
	}
	
	@FindBy(xpath="//a[@onclick='closepopupInvest();return false;']")
	private WebElement investmentInfoEditCloseButton;

	/**
	 * @return the investmentInfoEditCloseButton
	 */
	public WebElement getInvestmentInfoEditCloseButton(int timeOut) {
		return isDisplayed(driver, investmentInfoEditCloseButton, "Visibility", timeOut, "Close button");
	}
	/******************************************IP Analytics****************************************/
	public WebElement getIPAnalyticsIcon(Workspace workspace) {
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "Fundraising";
		} else {
			workspaceSelector = "Investor";
		}
		return isDisplayed(driver, FindElement(driver, "//img[@id='"+workspaceSelector+"Analytics']", "IP Analytics Icon", action.BOOLEAN, 30), "visibility", 60, "IP Analytics Icon");
	}
	
	public List<WebElement> getIPAnalyticsListofTab(){
		return FindElements(driver, "//div[contains(@class,'formbox contacts_n_name_div')]//ul/li/a", "Ip Analytics List of Tab");
	}
	
	public List<WebElement> getIPAnalyticsListofTargetStatisticsText(){
		return FindElements(driver, "//div[@class='IPAnalytics_Statistics']//table//tr/td[1]", "IP Analytics List of Target Statistics Text");
	}
	
	public List<WebElement> getIPAnalyticsListofValuesTetx(){
		return FindElements(driver, "//div[@class='IPAnalytics_Statistics']//table//tr/td[2]","IP Analytics List of Values Text");
	}
	
	public List<WebElement> getIPAnalyticsExportListofDealRoomReport(){
		return FindElements(driver, "//div[@class='IPAnalytics_Exports']//table//tr/td[1]","IP Analytics Expots Repots Text List");
	}
	
	public List<WebElement> getIPAnalyticsListOfExportText(){
		return FindElements(driver, "//div[@class='IPAnalytics_Exports']//table//tr/td[2]//a", "IP Analytics Exports List of Link text");
	}
	
	
	
	@FindBy(xpath="//img[@title='Deal Room Analytics']")
	private WebElement dealRoomAnalyticsIcon;

	/**
	 * @return the dealRoomAnalyticsIcon
	 */
	public WebElement getDealRoomAnalyticsIcon(int timeOut) {
		return isDisplayed(driver, dealRoomAnalyticsIcon, "Visibility", timeOut, "deal Room Anaytics Icon");
	}
	
	@FindBy(xpath="//div[contains(@class,'IPAnalytics_fancybox')]/div[1]")
	private WebElement ipAnalyticsHeaderText;

	/**
	 * @return the dealRoomAnalyticsHeaderText
	 */
	public WebElement getIPAnalyticsHeaderText(int timeOut) {
		return isDisplayed(driver, ipAnalyticsHeaderText, "Visibility", timeOut, "IP Analytics Header Text");
	}
	
	@FindBy(xpath="//a[@id='IPAnalytics_Exports_tab']")
	private WebElement ipAnalyticsExportTab;

	/**
	 * @return the dealRoomAnalyticsExportTab
	 */
	public WebElement getIPAnalyticsExportTab(int timeOut) {
		return isDisplayed(driver, ipAnalyticsExportTab, "Visibility", timeOut, "IP Analytics Export Tab");
	}
	
	@FindBy(xpath="//a[text()='Statistics']")
	private WebElement ipAnalyticsStaticsTab;

	/**
	 * @return the dealRoomAnalyticsStaticsTab
	 */
	public WebElement getIPAnalyticsStaticsTab(int timeOut) {
		return isDisplayed(driver, ipAnalyticsStaticsTab, "Visibility", timeOut, "IP Analytics Statistics Tab");
	}
	
	@FindBy(xpath="//div[@class='IPAnalytics_Exports']//table//tr/td[2]//img")
	private WebElement ipAnalyticsExportIconOnExportTab;

	/**
	 * @return the dealRoomAnalyticsExportIconOnExportTab
	 */
	public WebElement getIPAnalyticsExportIconOnExportTab(int timeOut) {
		return isDisplayed(driver, ipAnalyticsExportIconOnExportTab, "Visibility", timeOut, "Export Icon on IP Analytics Export Tab");
	}
	
	
	@FindBy(xpath="(//div[contains(@class,'IPAnalytics_fancybox')]//a[@title='Close' and text()='Close'])[5]")
	private WebElement ipAnalyticsCloseBtn;

	/**
	 * @return the dealRoomAnalyticsCloseBtn
	 */
	public WebElement getIPAnalyticsCloseBtn(int timeOut) {
		return isDisplayed(driver, ipAnalyticsCloseBtn, "Visibility", timeOut, "IP Analytics Close Button");
	}
	@FindBy(xpath="//div[@class='IPAnalytics_Statistics']//table//tr/td[contains(text(),'# of Unique Documents')]/../td[2]/span")
	private WebElement ipAnalyticsUniqueDocumentvalue;
	
	/**
	 * @return the dealRoomAnalyticsUniqueDocumentvalue
	 */
	public WebElement getIPAnalyticsUniqueDocumentvalue(int timeOut) {
		return isDisplayed(driver, ipAnalyticsUniqueDocumentvalue, "Visibility", timeOut, "IP Analytics No of Unique Document");
	}
	
	@FindBy(xpath="//div[@class='IPAnalytics_Statistics']//table//tr/td[contains(text(),'# of Document Views')]/../td[2]/span")
	private WebElement ipAnalyticsDocumentViewsvalue;

	/**
	 * @return the dealRoomAnalyticsDocumentViewsvalue
	 */
	public WebElement getIPAnalyticsDocumentViewsvalue(int timeOut) {
		return isDisplayed(driver, ipAnalyticsDocumentViewsvalue, "Visibility", timeOut, "IP Analytics No of Document Views");
	}
	
	@FindBy(xpath="//div[@class='IPAnalytics_Statistics']//table//tr/td[contains(text(),'# of Document Downloads')]/../td[2]/span")
	private WebElement ipAnalyticsDocumentDownload;

	/**
	 * @return the dealRoomAnalyticsDocumentDownload
	 */
	public WebElement getIPAnalyticsDocumentDownload(int timeOut) {
		return isDisplayed(driver, ipAnalyticsDocumentDownload, "Visibility", timeOut, "IP Analytics No of Document Download");
	}
	
	@FindBy(xpath="//div[@class='IPAnalytics_Statistics']//table//tr/td[contains(text(),'# of Documents not Viewed or Downloaded')]/../td[2]/a")
	private WebElement ipAnalyticsDocumentNotViwedorDownloadLink;

	/**
	 * @return the dealRoomAnalyticsDocumentNotViwedorDownloadLink
	 */
	public WebElement getIPAnalyticsDocumentNotViwedorDownloadLink(int timeOut) {
		return isDisplayed(driver, ipAnalyticsDocumentNotViwedorDownloadLink, "Visibility", timeOut, "IP Analytics Document not viewed or downloaded Value Link");
	}
	
	@FindBy(xpath="//div[@class='IPAnalytics_Statistics']//table//tr/td[contains(text(),'# of Contacts Granted Access')]/../td[2]/span")
	private WebElement ipAnalyticsContactGrantedAccessvalue;
	
	/**
	 * @return the dealRoomAnalyticsContactGrantedAccess
	 */
	public WebElement getIPAnalyticsContactGrantedAccessvalue(int timeOut) {
		return isDisplayed(driver, ipAnalyticsContactGrantedAccessvalue, "Visibility", timeOut, "IP Analytics No of Contact Granted Access");
	}
	
	@FindBy(xpath="//div[@class='IPAnalytics_Statistics']//table//tr/td[contains(text(),'# of Contacts who have not Viewed any Document')]/../td[2]/a")
	private WebElement ipAnalyticsNotViewedAnyDocumentLink;

	/**
	 * @return the dealRoomAnalyticsnotViewedAnyDocumentLink
	 */
	public WebElement getIPAnalyticsnotViewedAnyDocumentLink(int timeOut) {
		return isDisplayed(driver, ipAnalyticsNotViewedAnyDocumentLink, "Visibility", timeOut, "# of Contacts who have not Viewed any Document Value Link");
	}
	
	
	public List<WebElement> getMostActiveContactNameList(){
		return FindElements(driver, "//span[contains(@id,'MostActiveContacts_Grid-row-')]/span[2]/a", "Most Active Contact Name List");
	}
	
	public List<WebElement> getMostActiveContactFirmNameList(){
		return FindElements(driver, "//span[contains(@id,'MostActiveContacts_Grid-row-')]/span[3]", "Most Active Contact Firm Name List");
	}
	
	public List<WebElement> getMostActiveContactActivityCountList(){
		return FindElements(driver, "//span[contains(@id,'MostActiveContacts_Grid-row-')]/span[4]", "Most Active Contact Activity Count List");
	}
	
	public List<WebElement> getMostViewedDocumentName(){
		return FindElements(driver, "//span[contains(@id,'MostViewedDocuments_Grid-cell-')]/a", "Most viewed document Name list");
	}
	
	//span[contains(@id,'MostViewedDocuments_Grid-row-')]/span[2]/following-sibling::span[1]

	@FindBy(xpath="//span[contains(@id,'MostViewedDocuments_Grid-cell-1-')]")
	private List<WebElement> mostViewDocumentsViews;

	/**
	 * @return the mostViewDocumentsViews
	 */
	public List<WebElement> getMostViewDocumentsViews(int timeOut) {
		if(checkElementsVisibility(driver, mostViewDocumentsViews, "Most viewed document views count", timeOut)){
			return mostViewDocumentsViews;
		} else {
			return null;
		}
	}
	
	
	
	
	@FindBy(xpath="//div[@class='IPAnalytics_Statistics']//table//tr/td[contains(text(),'Most Viewed Documents (Top 5)')]/../td[2]/a")
	private WebElement ipAnalyticsMostViewedDocumentLink;

	/**
	 * @return the dealRoomAnalyticsMostViewedDocumentLink
	 */
	public WebElement getIPAnalyticsMostViewedDocumentLink(int timeOut) {
		return isDisplayed(driver,ipAnalyticsMostViewedDocumentLink, "Visibility", timeOut, "Most Viewed Documents (Top 5) Link");
	}
	
	
	
	@FindBy(xpath="//span[contains(@id,'MostViewedDocuments_Grid-row-')]/span[2]/span")
	private WebElement mostViewedDocumentErrorMsg;

	/**
	 * @return the mostViewedDocumentErrorMsg
	 */
	public WebElement getMostViewedDocumentErrorMsg(int timeOut) {
		return isDisplayed(driver, mostViewedDocumentErrorMsg, "Visibility", timeOut, "Most Viewed Document Error Message");
	}
	
	@FindBy(xpath="(//span[contains(@id,'MostViewedDocuments_Grid-header-')]/span[3]/span)[2]")
	private WebElement mostviewedDocumentViewSortingIcon;

	/**
	 * @return the mostviewedDocumentViewSortingIcon
	 */
	public WebElement getMostviewedDocumentViewSortingIcon(int timeOut) {
		return isDisplayed(driver, mostviewedDocumentViewSortingIcon, "Visibility", timeOut, "Most Viewed document View Sorting Icon");
	}
	
	public List<WebElement>getMostViewedDocumentSortingIconList(){
		return FindElements(driver, "//span[contains(@id,'MostViewedDocuments_Grid-header-')]/span[3]/span", "most viewed document sorting icon list");
	}
	
	public List<WebElement>getMostViewedDocumentHeaderTextList(){
		return FindElements(driver, "//span[contains(@id,'MostViewedDocuments_Grid-header-')]/span[3]", "most viewed document header text list");
	}
	
	@FindBy(xpath="//div[contains(@class,'MostViewedDocuments_popup')]//a[@title='Close']")
	private WebElement mostviewedDocumentCloseBtn;

	/**
	 * @return the mostviewedDocumentCloseBtn
	 */
	public WebElement getMostviewedDocumentCloseBtn(int timeOut) {
		return isDisplayed(driver, mostviewedDocumentCloseBtn, "Visibility", timeOut, "Most Viewed Document Close Button");
	}
	

	@FindBy(xpath="//div[contains(@class,'MostViewedDocuments_popup')]//span[@title='Close']")
	private WebElement mostViewedDocumentCloseIcon;

	/**
	 * @return the mostViewedDocumentCloseIcon
	 */
	public WebElement getMostViewedDocumentCloseIcon(int timeOut) {
		return isDisplayed(driver, mostViewedDocumentCloseIcon, "Visibility", timeOut, "Most Viewed Document Close Icon");
	}
	
	@FindBy(xpath="//div[@class='IPAnalytics_Statistics']//table//tr/td[contains(text(),'Most Active Contacts (Top 5)')]/../td[2]/a")
	private WebElement ipAnalyticsMostActiveContactsLink;

	/**
	 * @return the dealRoomAnalyticsMostActiveContactsLink
	 */
	public WebElement getIPAnalyticsMostActiveContactsLink(int timeOut) {
		return isDisplayed(driver, ipAnalyticsMostActiveContactsLink, "Visibility", timeOut, "Most Active Contacts (Top 5) Link");
	}
	
	public List<WebElement> getMostActiveContactHeaderTextList(){
		return FindElements(driver, "//span[contains(@id,'MostActiveContacts_Grid-header-')]/span[3]", "Most Active Contact Header Text List");
	}
	
	@FindBy(xpath="//span[contains(@id,'MostActiveContacts_Grid-row-')]/span[2]/span")
	private WebElement mostActiveContactErrorMsg;

	/**
	 * @return the mostActiveContactErrorMsg
	 */
	public WebElement getMostActiveContactErrorMsg(int timeOut) {
		return isDisplayed(driver, mostActiveContactErrorMsg, "Visibility", timeOut, "Most Active Contact Error Message");
	}
	
	@FindBy(xpath="//span[contains(@id,'MostActiveContacts_Grid-header-2')]/span[3]/a")
	private WebElement mostviewedContactActivityCountToolTip;

	/**
	 * @return the getmostviewedContactActivityCountToolTip
	 */
	public WebElement getGetmostviewedContactActivityCountToolTip(int timeOut) {
		return isDisplayed(driver, mostviewedContactActivityCountToolTip, "Visibility", timeOut, "Most Active Contact Activity Count tooltip");
	}
	
	@FindBy(xpath="//span[contains(@id,'MostActiveContacts_Grid-header-2')]/span[3]/span")
	private WebElement mostActiveContactActivityCountSortingIcon;

	/**
	 * @return the mostViewedContactActivityCountSortingIcon
	 */
	public WebElement getMostActiveContactActivityCountSortingIcon(int timeOut) {
		return isDisplayed(driver, mostActiveContactActivityCountSortingIcon, "Visibility", timeOut, "Most active Contact Activity Count Sorting Icon");
	}
	
	public List<WebElement> getMostActiveDocumentSortingIconList(){
		return FindElements(driver, "//span[contains(@id,'MostActiveContacts_Grid-header-') and contains(@class,'aw-grid-header')]/span/span[3]/span", "most active contact sorting icon list");
	}

	@FindBy(xpath="//div[contains(@class,'MostActiveContacts_popup')]//a[text()='Close']")
	private WebElement mostActiveContactCloseBtn;

	/**
	 * @return the mostActiveContactCloseBtn
	 */
	public WebElement getMostActiveContactCloseBtn(int timeOut) {
		return isDisplayed(driver, mostActiveContactCloseBtn, "Visibility", timeOut, "Most Active Contact Close Button");
	}
	
	@FindBy(xpath="//div[contains(@class,'MostActiveContacts_popup')]//span[@title='Close']")
	private WebElement mostActiveContactCloseIcon;

	/**
	 * @return the mostActiveContactCloseIcon
	 */
	public WebElement getMostActiveContactCloseIcon(int timeOut) {
		return isDisplayed(driver, mostActiveContactCloseIcon, "Visibility", timeOut, "Most Active Contact Close Icon");
	}
	
	public List<WebElement> draggedFilesInFileUploadAtCRMSide(){
		return FindElements(driver, "(//div[@id='divselectInvestor']//ul)[2]/li/b", "Dragged Files in Uplod Files At CRM Side");
	}
	
	@FindBy(xpath="//span[@id='ContactNotViewedDocument_Grid-cell-0-0']/span")
	private WebElement documentNotViewedAnyDocumentErrorMsg;

	/**
	 * @return the documentNotViewedAnyDocumentErrorMsg
	 */
	public WebElement getContactNotViewedAnyDocumentErrorMsg(int timeOut) {
		return isDisplayed(driver, documentNotViewedAnyDocumentErrorMsg, "Visibility", timeOut, "document not viewed any document error message");
	}
	
	@FindBy(xpath="//div[contains(@class,'ContantNotVieweddocument_popup')]//p")
	private WebElement contactNotViewedAnyDocument;
	
	/**
	 * @return the contactNotViewedAnyDocument
	 */
	public WebElement getContactNotViewedAnyDocument(int timeOut) {
		return isDisplayed(driver, contactNotViewedAnyDocument, "Visibility", timeOut, "Contact not viewed any document header text");
	}

	@FindBy(xpath="(//div[contains(@class,'ContantNotVieweddocument_popup')]//a[@title='Close'])[2]")
	private WebElement documentNotViewedAnyDocumentCloseBtn;

	/**
	 * @return the documentNotViewedAnyDocumentCloseBtn
	 */
	public WebElement getContactNotViewedAnyDocumentCloseBtn(int timeOut) {
		return isDisplayed(driver, documentNotViewedAnyDocumentCloseBtn, "Visibility", timeOut, "document not viewed any document close button");
	}
	
	@FindBy(xpath="(//div[contains(@class,'ContantNotVieweddocument_popup')]//a[@title='Close'])[1]")
	private WebElement documentNotViewedAnyDocumentCloseIcon;

	/**
	 * @return the documentNotViewedAnyDocumentCloseBtn
	 */
	public WebElement getContactNotViewedAnyDocumentCloseIcon(int timeOut) {
		return isDisplayed(driver, documentNotViewedAnyDocumentCloseIcon, "Visibility", timeOut, "document not viewed any document close Icon");
	}
	
	
	
	
	@FindBy(xpath="(//div[contains(@class,'NotVieweddocument_popup')]//a[@title='Close'])[2]")
	private WebElement documentNotViewedOrDownloadedCloseBtn;

	/**
	 * @return the documentNotViewedOrDownloadedCloseBtn
	 */
	public WebElement getDocumentNotViewedOrDownloadedCloseBtn(int timeOut) {
		return isDisplayed(driver, documentNotViewedOrDownloadedCloseBtn, "Visibility", timeOut, "document not viewed or downloaded close button");
	}
	
	@FindBy(xpath="(//div[contains(@class,'NotVieweddocument_popup')]//a[@title='Close'])[1]")
	private WebElement documentNotViewedOrDownloadedCloseIcon;

	/**
	 * @return the documentNotViewedOrDownloadedCloseBtn
	 */
	public WebElement getDocumentNotViewedOrDownloadedCloseIcon(int timeOut) {
		return isDisplayed(driver, documentNotViewedOrDownloadedCloseIcon, "Visibility", timeOut, "document not viewed or downloaded close Icon");
	}
	
	@FindBy(xpath="(//div[contains(@class,'NotVieweddocument_popup')]//p)[1]")
	private WebElement documentNotViewedOrDownloadTextMsg;
	
	
	/**
	 * @return the documentNotViewedOrDownloadTextMsg
	 */
	public WebElement getDocumentNotViewedOrDownloadTextMsg(int timeOut) {
		return isDisplayed(driver, documentNotViewedOrDownloadTextMsg, "Visibility", timeOut, "document not viewed or download text msg");
	}

	@FindBy(xpath="//span[@id='NotViewedDocument_Grid-cell-0-0']/span")
	private WebElement documentNotViewedOrDownloadedErrorMsg;

	/**
	 * @return the documentNotViewedAnyDocumentErrorMsg
	 */
	public WebElement getDocumentNotViewedOrDownloadedErrorMsg(int timeOut) {
		return isDisplayed(driver, documentNotViewedOrDownloadedErrorMsg, "Visibility", timeOut, "document not viewed any document error message");
	}
	public List<WebElement> getDocumentNotViewedOrDownloadDocumentList(){
		return FindElements(driver, "//span[@id='NotViewedDocument_Grid-view-box-middle']//span[contains(@id,'NotViewedDocument_Grid-cell-0-')]/a", "document not viewed or download document list");
	}
	
	@FindBy(xpath="//span[@id='NotViewedDocument_Grid-header-0']/span/span[3]/span")
	private WebElement documentNotViewedOrDownloadSortingIcon;
	
	/**
	 * @return the documentNotViewedOrDownloadSortingIcon
	 */
	public WebElement getDocumentNotViewedOrDownloadSortingIcon(int timeOut) {
		return isDisplayed(driver, documentNotViewedOrDownloadSortingIcon, "Visibility", timeOut, "document not viewed or download sorting icon");
	}
	
	/**
	 * @return the documentNotViewedOrDownloadHeaderText
	 */
	public List<WebElement> getDocumentNotViewedOrDownloadHeaderText() {
		return FindElements(driver, "//span[@id='NotViewedDocument_Grid-header-0']/span/span[3]", "document not viewed or download header text");
	}

	public List<WebElement> getContactNotViewedAnyDocumentSortingIcon(){
		return FindElements(driver, "//span[contains(@id,'ContactNotViewedDocument_Grid-header-') and @class='aw-item-text ']/span", "contact not viewed any document sorting icon");
	}
	
	public List<WebElement> getContactNotViewedAnyDocumentHeaderTextList(){
		return FindElements(driver, "//span[contains(@id,'ContactNotViewedDocument_Grid-header-') and @class='aw-item-text ']", "contact not viewed any document header text list");
	}
	
	public List<WebElement> getContactNotViewedAnyDocumentContactNameList(){
		return FindElements(driver, "(//span[contains(@id,'ContactNotViewedDocument_Grid-rows')])[1]//span[contains(@id,'ContactNotViewedDocument_Grid-cell-0-')]/a", "contact not viewed any document contact name list");
	}
	
	public List<WebElement> getContactNotViewedAnyDocumentFirmNameList(){
		return FindElements(driver, "(//span[contains(@id,'ContactNotViewedDocument_Grid-rows')])[1]//span[contains(@id,'ContactNotViewedDocument_Grid-cell-1-')]", "contact not viewed any document firm name list");
	}
	
	@FindBy(xpath="//span[@id='NotViewedDocument_Grid-scroll-box']")
	private WebElement documentNotViewedOrDownloadScrollBox;
	
	/**
	 * @return the firmProfileUpdatePopUpScroll
	 */
	public WebElement getDocumentNotViewedOrDownloadScrollBox(int timeOut) {
		return isDisplayed(driver, documentNotViewedOrDownloadScrollBox, "Visibility", timeOut, "document Not Viewed Or Download ScrollBox");
	}
	
	public WebElement getFolderInfoIcon(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//a[@id='demo-basicDFBW" + workspaceSelector + "']//img[@class='Build_info']", "Folder Info Icon", action.BOOLEAN, timeOut), "Visibility", timeOut, "Folder Info Icon");
	}
	
	public WebElement getAddFolderInformationIconOnBuildProcess(Workspace workspace, int timeOut) {
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//a[contains(@id,'demo-basicDFBW"+ workspaceSelector +"')]//img", workspace+" parent folder info icon", action.BOOLEAN, 30), "visibility", 60, workspace+"  parent folder info icon");
	}
	
	public WebElement getSelectAllCheckBoxContactAccess(Workspace workspace, int timeOut){
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR";
		} else {
			workspaceSelector = "INV";
		}
		return isDisplayed(driver, FindElement(driver, "//input[@id='headBoxBW" + workspaceSelector + "_MA']", "Select all check box", action.BOOLEAN, timeOut), "Visibility", timeOut, "Select all check box");
	}
	
	@FindBy(xpath="//p[@id='totalStatus']")
	private WebElement sizeLimitErrorMessage;

	/**
	 * @return the sizeLimitErrorMessage
	 */
	public WebElement getSizeLimitErrorMessage(int timeOut) {
		return isDisplayed(driver, sizeLimitErrorMessage, "Visibility", timeOut, "Size limit error message");
	}
	
	public WebElement getFundNameAtFundPage(String fundName,int timeOut){
		WebElement ele = FindElement(driver,
				"//div[@class='x-panel-bwrap']//a//span[contains(text(),'" + fundName + "')]", "Fund Name",
				action.SCROLLANDBOOLEAN, 60);
		
		return isDisplayed(driver, ele, "Visibility", timeOut, "Select all check box");
	}
	
	public WebElement getContactNameOrAllContactCheckBox(Workspace workspace,String contactName, int timeOut) {
		WebElement ele=null;
		String xpath="";
		String xpath1="";
		if(contactName!=null) {
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
				xpath="FR";
			}else{
				xpath="INV";
			}
			ele=FindElement(driver, "//div[@id='shwTopGridBW"+xpath+"_MA']//span[text()='"+contactName+"']/preceding-sibling::span//input", contactName+" contact check box", action.SCROLLANDBOOLEAN, 30);
			scrollDownThroughWebelement(driver,ele, "");
			
		}else {
			if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
				xpath1="FR";
			}else{
				xpath1="INV";
			}
			ele=FindElement(driver, "//input[@id='headBoxBW"+xpath1+"_MA']","all contact check box", action.SCROLLANDBOOLEAN, 30);
			scrollDownThroughWebelement(driver,ele, "");
		}
		return ele;
	}
	public WebElement getContactAccessRemovedPopUpMsg(Workspace workspace, int timeOut){
		String xpath;
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR_MA";
		} else {
			workspaceSelector = "INV_MA";
		}
		xpath="//div[@id='accessRemovedIdBW"+workspaceSelector+"']/div/p";
		return isDisplayed(driver, FindElement(driver, xpath, "Access Remove Pop Up Msg : "+workspace, action.BOOLEAN, timeOut), "visibility", timeOut, "Access Remove Pop Up Msg : "+workspace);
	}
	
	public WebElement getContactAccessRemovedPopUp(Workspace workspace, int timeOut){
		String xpath;
		String workspaceSelector = "";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			workspaceSelector = "FR_MA";
		} else {
			workspaceSelector = "INV_MA";
		}
		xpath="//div[@id='accessRemovedIdBW"+workspaceSelector+"']/div[text()='Access Removed']";
		return isDisplayed(driver, FindElement(driver, xpath, "Access Remove Pop Up : "+workspace, action.BOOLEAN, timeOut), "visibility", timeOut, "Access Remove Pop Up  : "+workspace);
	}
	
	
	
	public WebElement getCrossIconOnContactAccess(Workspace workspace, int timeOut) {
		WebElement ele = null;
		String xpath="";
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			xpath="//div[contains(@class,'ContactAccess_fancyboxBWFR')]/span/div/a[@title='Close']";
		}else if(workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			xpath="//div[contains(@class,'ContactAccess_fancyboxBWINV')]/span/div/a[@title='Close']";
		}	
		ele= FindElement(driver, xpath, "Contact Access Cross Icon "+workspace, action.SCROLLANDBOOLEAN, timeOut);
		ele=isDisplayed(driver, ele, "Visibility", timeOut, "Contact Access Cross Icon "+workspace);
		return ele;
	}
	
	public WebElement getClearIconOnContactAccessSearchBox(Workspace workspace, int timeOut) {
		WebElement ele = null;
		String xpath="";
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			xpath="//div[@id='clearsearchenb111BWFR_MA']/a";
		}else if(workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			xpath="//div[@id='clearsearchenb111BWINV_MA']/a";
		}	
		ele= FindElement(driver, xpath, "Clear Icon on Contact Access Search Box "+workspace, action.SCROLLANDBOOLEAN, timeOut);
		ele=isDisplayed(driver, ele, "Visibility", timeOut, "Clear Icon on Contact Access Search Box "+workspace);
		return ele;
	}
	
	public WebElement getcontactaccessremoveLink(Workspace workspace, EnableDisable enableDisable, String contactEmail,  int timeOut) {
		WebElement ele = null;
		String xpath="",enable="";
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			xpath="FR";
		}else if(workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			xpath="INV";
		}
		if(enableDisable.toString().equalsIgnoreCase(EnableDisable.Enable.toString())) {
			enable="/a[contains(text(),'Remove')]";
		}else {
			enable="[contains(text(),'Remove')]";
		}
		ele= FindElement(driver, "//span[contains(@id,'grid11_DealDetailBW"+xpath+"-cell-3-')]/a[text()='"+contactEmail+"']/../preceding-sibling::span"+enable+"", "Clear Icon on Contact Access Search Box "+workspace, action.SCROLLANDBOOLEAN, timeOut);
		ele=isDisplayed(driver, ele, "Visibility", timeOut, "Clear Icon on Contact Access Search Box "+workspace);
		return ele;
	}
	
	public List<WebElement> getSelectedContactNameListInSelectContactGrid(Workspace workspace){
		String xpath="";
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())) {
			xpath="FR";
		}else {
			xpath="INV";
		}
		return FindElements(driver, "//span[contains(@id,'grid11_DealDetailBW"+xpath+"-cell-1-')]", "Contact name list in select contact grid");
	}
	
	@FindBy(xpath = "//img[@title='Upload Documents']")
	private WebElement uploadicon;
	
	/*
	 * return upload column on Contact access pop up.
	 */
	public WebElement getUploadColumn(int timeout){
		return isDisplayed(driver, uploadicon, "visibility", timeout, "Upload Column Image");
	}
	
	public WebElement getremoveToolTipInfo(Workspace workspace, String contactEmail,  int timeOut) {
		WebElement ele = null;
		String xpath="";
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			xpath="FR";
		}else if(workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			xpath="INV";
		}
		
		ele= FindElement(driver, "//span[@id='grid11_DealDetailBW"+xpath+"-cell-3-0']/a[text()='"+contactEmail+"']/../preceding-sibling::span[contains(text(),'Remove')]/a", "Tool tip : "+workspace, action.SCROLLANDBOOLEAN, timeOut);
		ele=isDisplayed(driver, ele, "Visibility", timeOut, "Tool Tip "+workspace);
		return ele;
	}
	
	@FindBy(xpath="//div[contains(@class,'FileStatistics_fancybox')]//a[text()='Close']")
	private WebElement documentStatisticsPopUpCloseBtn;

	/**
	 * @return the documentStatisticsPopUpCloseBtn
	 */
	public WebElement getDocumentStatisticsPopUpCloseBtn(int timeOut) {
		return isDisplayed(driver, documentStatisticsPopUpCloseBtn, "Visibility", timeOut, "document statistics close btn");
		
		
		
	}
	
	
	public WebElement getClearSearchIcon(Workspace workspace, EnableDisable enableDisable,  int timeOut) {
		WebElement ele = null;
		String xpath="",enable="";
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			xpath="FR_MA";
		}else if(workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			xpath="INV_MA";
		}
		if(enableDisable.toString().equalsIgnoreCase(EnableDisable.Enable.toString())) {
			enable="icon_clear_search_active";
		}else {
			enable="icon_clear_search";
		}
		ele= FindElement(driver, "//span[@id='serachTextIconDivBW"+xpath+"']//a[@class='"+enable+"']", "Clear Search Icon on Contact Access Search Box "+workspace, action.SCROLLANDBOOLEAN, timeOut);
		ele=isDisplayed(driver, ele, "Visibility", timeOut, "Clear Search Icon on Contact Access Search Box "+workspace);
		return ele;
	}
	
	
	public WebElement noDataToDisplayContactAccessTopGrid(Workspace workspace, int timeOut) {
		String w="";
		if (workspace == Workspace.InvestorWorkspace)
			w="INV";
		else
			w="FR";
		return isDisplayed(driver,FindElement(driver, "//div[@id='shwTopGridBW"+w+"_MA']//span[contains(@id,'DealDetailBW"+w+"-cell-1-0')]", "no data to display", action.SCROLLANDBOOLEAN, timeOut/2), "visibility", timeOut/2, "no data to display");
	}
	
	public List<WebElement> getContactAccessPopUpSelectHeader(Workspace workSpace){
		if(workSpace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			return FindElements(driver, "//div[@id='shwTopGridBWFR_MA']//span[contains(@id,'DealDetailBWFR-header-')and contains(@id,'-box')]/span[3]", "column heads");	
		}else{
			return FindElements(driver, "//div[@id='shwTopGridBWINV_MA']//span[contains(@id,'DealDetailBWINV-header-')and contains(@id,'-box')]/span[3]", "column heads");		
		}
		
	}
	
	public List<WebElement> getContactAccessPopUpSelectedHeader(Workspace workSpace){
		if(workSpace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			return FindElements(driver, "//div[@id='shwBottomGridBWFR_MA']//span[contains(@id,'DealDetailBWFR-header-')and contains(@id,'-box')]/span[3]", "column heads");	
		}else{
			return FindElements(driver, "//div[@id='shwBottomGridBWINV_MA']//span[contains(@id,'DealDetailBWINV-header-')and contains(@id,'-box')]/span[3]", "column heads");		
		}
		
	}
	
	@FindBy(xpath="//a[@title='Backup']")
	private WebElement backUpLinkInClearWorkSpace;

	/**
	 * @return the backUpLinkInClearWorkSpace
	 */
	public WebElement getBackUpLinkInClearWorkSpace(int timeOut) {
		return isDisplayed(driver, backUpLinkInClearWorkSpace, "Visibility", timeOut, "back up link in clear workspace pop up");
	}
	
	@FindBy(xpath="//a[@id='backurlfromresult']")
	private WebElement boxLinkInClearWorkSpace;

	/**
	 * @return the boxLinkInClearWorkSpace
	 */
	public WebElement getBoxLinkInClearWorkSpace(int timeOut) {
		return isDisplayed(driver, boxLinkInClearWorkSpace, "Visibility", timeOut, "box link in clear workspace pop up");
	}
	
	@FindBy(xpath="//div[@id='backup_savebtn']//a[@title='Yes']")
	private WebElement clearWorkSpaceYesOnBackUpPage;

	/**
	 * @return the clearWorkSpaceYesOnBackUpPage
	 */
	public WebElement getClearWorkSpaceYesOnBackUpPage(int timeOut) {
		return isDisplayed(driver, clearWorkSpaceYesOnBackUpPage, "Visibility", timeOut, "clear workspace yes button on backup page");
	}
	
	public List<WebElement> getHeaderAndContactCheckBoxesofSelectGridonContactAccessPopUp(Workspace workSpace){
		if(workSpace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())){
			return FindElements(driver, "//input[contains(@onclick,'BWINV_MA(this);')]", "CheckBoxes ");	
		}else{
			return FindElements(driver, "//input[contains(@onclick,'BWFR_MA(this);')]", "CheckBoxes ");		
		}
		
	}

	public WebElement getBulkDownLoadCheckBox(Workspace workspace, int timeOut) {
		WebElement ele = null;
		String xpath="";
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			xpath="//*[@name=\"Bulk_Download_Fundraising__c\"]/../span";
		}else if(workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			xpath="//*[@name=\"Bulk_Download_Investor__c\"]/../span";
		}	
		ele= FindElement(driver, xpath, "Bulk DownLoad CheckBox"+workspace, action.SCROLLANDBOOLEAN, timeOut);
		ele=isDisplayed(driver, ele, "Visibility", timeOut, "Bulk DownLoad CheckBox"+workspace);
		return ele;
	}
	
	
	public WebElement getAlertHistoryShowDropDownList(Workspace workspace, int timeOut) {
		String xpath="";
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			xpath="FR";
		}else if(workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			xpath="INV";
		}	
		return isDisplayed(driver,FindElement(driver, "//select[@id='range1ACTALT"+xpath+"']", "Bulk DownLoad CheckBox"+workspace, action.SCROLLANDBOOLEAN, timeOut), "Visibility", timeOut, "Bulk DownLoad CheckBox"+workspace);
	}
	
	
	public WebElement getAlertHistoryRangeDropDownList(Workspace workspace, int timeOut) {
		String xpath="";
		if(workspace.toString().equalsIgnoreCase(workspace.FundraisingWorkspace.toString())) {
			xpath="FR";
		}else if(workspace.toString().equalsIgnoreCase(workspace.InvestorWorkspace.toString())) {
			xpath="INV";
		}	
		return isDisplayed(driver,FindElement(driver, "//select[@id='range2ACTALT"+xpath+"']", "Bulk DownLoad CheckBox"+workspace, action.SCROLLANDBOOLEAN, timeOut), "Visibility", timeOut, "Bulk DownLoad CheckBox"+workspace);
	}
	
	
	@FindBy(xpath="searchcon_grid2")
	private WebElement WorkSpaceSearchTextBox;

	/**
	 * @return the workSpaceSearchTextBox
	 */
	public WebElement getWorkSpaceSearchTextBox(int timeOut) {
		return isDisplayed(driver, WorkSpaceSearchTextBox, "Visibility", timeOut, "workspace search text box");
	}
	
	
	@FindBy(xpath="//a[@onclick='SearchResultsFor_Fancybox_cntr(); ']")
	private WebElement workSpaceSearchBtn;

	/**
	 * @return the workSpaceSearchBtn
	 */
	public WebElement getWorkSpaceSearchBtn(int timeOut) {
		return isDisplayed(driver, workSpaceSearchBtn, "Visibility", timeOut, "workspace search button");
	}
	
	@FindBy(xpath="//select[@id='Filestatid_grid']")
	private WebElement documentStatisticsViewDropDownList;

	/**
	 * @return the documentStatisticsViewDropDownList
	 */
	public WebElement getDocumentStatisticsViewDropDownList(int timeOut) {
		return isDisplayed(driver, documentStatisticsViewDropDownList, "Visibility", timeOut, "document statistics view drop down list");
	}
	
	@FindBy(xpath="//select[@id='Filestatid_grid7575']")
	private WebElement documentStatisticsRangeDropDownList;

	/**
	 * @return the documentStatisticsRangeDropDownList
	 */
	public WebElement getDocumentStatisticsRangeDropDownList(int timeOut) {
		return isDisplayed(driver, documentStatisticsRangeDropDownList, "Visibility", timeOut, "document statistics range drop down list");
	}
	
	
	@FindBy(xpath="//input[@name='Name']")
	private WebElement fundName_Classic;
	
	@FindBy(xpath="//*[text()='Fund Name']/following-sibling::*//input")
	private WebElement fundName_Lighting;

	/**
	 * @return the fundName
	 */
	public WebElement getFundName(String environment,String mode,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, fundName_Classic, "Visibility", timeOut, "Fund Name Classic");
		}else{
			return isDisplayed(driver, fundName_Lighting, "Visibility", timeOut, "Fund Name Lighting");
		}
		
	}
	
	
	@FindBy(xpath="(//div[@class='requiredInput']//select)[2]")
	private WebElement investmentCategory_Classic;
	
	@FindBy(xpath="//*[text()='Investment Category']/following-sibling::div//button")
	private WebElement investmentCategory_Lighting;

	/**
	 * @return the investmentCategory
	 */
	public WebElement getInvestmentCategory(String environment,String mode,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, investmentCategory_Classic, "Visibility", timeOut, "Investment Category Classic");
		}else{
			return isDisplayed(driver, investmentCategory_Lighting, "Visibility", timeOut, "Investment Category Lighting");
		}
	} 
	
	@FindBy(xpath="//div[@id='Name_ileinner']")
	private WebElement fundNameInViewMode_Classic;
	
	@FindBy(xpath="//div//h1/div[contains(text(),'Fund')]/..")
	private WebElement fundNameInViewMode_Lighting;

	/**
	 * @return the fundNameLabel
	 */
	public WebElement getFundNameInViewMode(String environment,String mode,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, fundNameInViewMode_Classic, "Visibility", timeOut, "Fund Name in View Mode Classic");
		}else{
			return isDisplayed(driver, fundNameInViewMode_Lighting, "Visibility", timeOut, "Fund Name in View Mode Lighting");
		}
		
	}
	@FindBy(xpath="//div[@class='requiredInput']//select")
	private WebElement fundType_Classic;
	
	@FindBy(xpath="//*[text()='Fund Type']/following-sibling::div//button")
	private WebElement fundType_Lighting;

	/**
	 * @return the fundType
	 */
	public WebElement getFundType(String environment,String mode,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, fundType_Classic, "Visibility", timeOut, "Fund Type Classic");
		}else{
			return isDisplayed(driver, fundType_Lighting, "Visibility", timeOut, "Fund Type Lighting");
		}
		
	}
	
	public WebElement getFundtPageTextBoxOrRichTextBoxWebElement(String environment,String mode, String labelName, int timeOut) {
		WebElement ele=null;
		String xpath ="",inputXpath="", dateXpath="",finalXpath="",finalLabelName="";
		if(labelName.contains("_")) {
			finalLabelName=labelName.replace("_", " ");
		}else {
			finalLabelName=labelName;
		}
		if(mode.equalsIgnoreCase(Mode.Classic.toString())) {
			xpath="//label[contains(text(),'"+finalLabelName+"')]";
			inputXpath="/../following-sibling::td/input";
			dateXpath="/../following-sibling::td[1]/span/input";
		}else {
			//span[text()='Description']/..//following-sibling::textarea
			xpath="//span[contains(text(),'"+finalLabelName+"')]";
			inputXpath="/..//following-sibling::input";
			dateXpath="/../following-sibling::div/input";
		}
		if(labelName.contains("Date")) {
			finalXpath=xpath+dateXpath;
		}else {
			finalXpath=xpath+inputXpath;
		}
		ele=isDisplayed(driver, FindElement(driver, finalXpath, finalLabelName+" text box in "+mode, action.SCROLLANDBOOLEAN,30), "Visibility", timeOut, finalLabelName+"text box in "+mode);
		return ele;
	}
	
	@FindBy(xpath="//div[@id='mainForm']/div[contains(text(),'Manage Approvals')]")
		private WebElement manageApprovalsHeaderText;
	
		/**
		 * @return the manageApprovalsHeaderText
		 */
		public WebElement getManageApprovalsHeaderText(int timeOut) {
			return isDisplayed(driver, manageApprovalsHeaderText, "Visibility", timeOut, "manage approvals header text");
		}
}
