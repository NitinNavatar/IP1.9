/**
 * 
 */
package com.navatar.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static com.navatar.generic.CommonLib.*;

import java.util.List;
/**
 * @author Parul Singh
 *
 */
public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[text()='Navatar Investor Activities']")
	private WebElement navatarInvestorActivitiesLabel;

	/**
	 * @return the navatarInvestorActivitiesLabel
	 */
	public WebElement getNavatarInvestorActivitiesLabel(int timeOut) {
		return isDisplayed(driver, navatarInvestorActivitiesLabel, "Visibility", timeOut, "Navatar Investor Activities Label");
	}
	
	@FindBy(xpath="//div[@id='yesdata']//h1")
	private WebElement errorMessageBeforeAdminRegistration;

	/**
	 * @return the errorMessageBeforeAdminRegistration
	 */
	public WebElement getErrorMessageBeforeAdminRegistration(int timeOut) {
		return isDisplayed(driver, errorMessageBeforeAdminRegistration, "Visibility", timeOut, "Error Message Before Admin Registration");
	} 
	
	@FindBy(xpath="//iframe[@title='InvestorPortal_Alerts']")
	private  WebElement homePageAlertsFrame;

	/**
	 * @return the homePageAlertsFrame
	 */
	public WebElement getHomePageAlertsFrame(int timeOut) {
		return isDisplayed(driver, homePageAlertsFrame, "Visibility", timeOut, "Home Page Alerts Frame");
	}	
	
	@FindBy(xpath="//td[@class='pbTitle']//h2")
	private WebElement dashboardLabelOnHomePage;

	/**
	 * @return the dashboardLabelOnHomePage
	 */
	public WebElement getDashboardLabelOnHomePage(int timeOut) {
		return isDisplayed(driver, dashboardLabelOnHomePage, "Visibility", timeOut, "Dashboard label on home page");
	} 
	
	@FindBy(xpath="//span[text()='No data to display.']")
	private WebElement errorMessageAfterAdminAndCRMUserRegistration;

	/**
	 * @return the errorMessageAfterAdminRegistration
	 */
	public WebElement getErrorMessageAfterAdminAndCRMUserRegistration(int timeOut) {
		return isDisplayed(driver, errorMessageAfterAdminAndCRMUserRegistration, "Visibility", timeOut, "Error Message After Admin and CRM User Registration");
	}
	
	@FindBy(xpath="//div[@id='sidebarDiv']//a/span[text()='Recycle Bin']")
	private WebElement recycleBinLink;
	
	/**
	 * @return the recycleBinLink
	 */
	public WebElement getRecycleBinLink(int timeOut) {
		return isDisplayed(driver, recycleBinLink, "Visibility", timeOut, "Recycle Bin");
	}
	
	@FindBy(xpath="//form[@id='del']/div[2]/input[@title='Undelete']")
	private WebElement unDeleteButton;
	
	/**
	 * @return the unDeleteButton
	 */
	public WebElement getUnDeleteButton(int timeOut) {
		return isDisplayed(driver, unDeleteButton, "Visibility", timeOut, "Undelete Button");
	}
	
	@FindBy(xpath="//a[@id='handlebarContainer']")
	private WebElement sideBarIcon;

	/**
	 * @return the sideBarIcon
	 */
	public WebElement getSideBarIcon(int timeOut) {
		return isDisplayed(driver, sideBarIcon, "Visibility", timeOut, "SideBar Icon");
	}
	
	@FindBy(xpath="//span[text()='No data to display.']")
	private WebElement errorMessage;

	/**
	 * @return the errorMessage
	 */
	public WebElement getErrorMessage(int timeOut) {
		return isDisplayed(driver, errorMessage, "Visibility", timeOut, "Error Message");
	}
	@FindBy(xpath = "//span[text()='Navatar Investor Activities']/..")
	private WebElement navatarInvetsorActivityGridOnHomeAlert;

	/**
	 * @return the dealRoomActivityGridOnHomeAlert
	 */
	public WebElement getnavatarInvetsorActivityGridOnHomeAlert(int timeOut) {
		return isDisplayed(driver, navatarInvetsorActivityGridOnHomeAlert, "Visibility", timeOut,
				"navatar Invetsor Activity Grid On Home Alert");
	}
	
	@FindBy(xpath = "//div[@id='temp']//div[@class='heading_box']/span")
	private WebElement headingOfHomeAlert;

	/**
	 * @return the headingOfHomeAlert
	 */
	public WebElement getHeadingOfHomeAlert(int timeOut) {
		return isDisplayed(driver, headingOfHomeAlert, "Visibility", timeOut,"Heading of Home Alert");
	}
	
	@FindBy(xpath = "(//div[@id='temp']//strong)[1]")
	private WebElement showDropDownLabelOnHomeAlert;

	/**
	 * @return the showDropDownLabelOnHomeAlert
	 */
	public WebElement getShowDropDownLabelONHomeAlert(int timeOut) {
		return isDisplayed(driver, showDropDownLabelOnHomeAlert, "Visibility", timeOut,"Show Drop Down Label on Home Alert");
	}	
	
	
	@FindBy(xpath = "(//div[@id='temp']//strong)[2]")
	private WebElement rangeDropDownLabelOnHomeAlert;

	/**
	 * @return the rangeDropDownLabelOnHomeAlert
	 */
	public WebElement getRangeDropDownLabelONHomeAlert(int timeOut) {
		return isDisplayed(driver, rangeDropDownLabelOnHomeAlert, "Visibility", timeOut,"Range Drop Down Label on Home Alert");
	}
	@FindBy(xpath="//select[@id='range1ACTALT']")
	private WebElement showDropdownOnHomeAlert;

	/**
	 * @return the showDropdownOnHomeAlert
	 */
	public WebElement getShowDropdownOnHomeAlert(int timeOut) {
		return isDisplayed(driver, showDropdownOnHomeAlert, "Visibility", timeOut, "Show Drop Down On Home Alert");
	}
	
	
	@FindBy(xpath="//select[@id='range2ACTALT']")
	private WebElement rangeDropdownOnHomeAlert;

	/**
	 * @return the rangeDropdownOnHomeAlert
	 */
	public WebElement getRangeDropdownOnHomeAlert(int timeOut) {
		return isDisplayed(driver, rangeDropdownOnHomeAlert, "Visibility", timeOut, "Range Drop Down On Home Alert");
	}
	
	public List<WebElement> getColumnTextonHomeAlert(int timeOut) {
		return FindElements(driver, "//span[@id='myGridACTALT-headers']//span[contains(@class,'aw-item-text ')]", "Coloumn text on home alert");
	}
	
	@FindBy(xpath = "//div[@id='recordscountACTALT']")
	private WebElement recordLabelWithValueOnHomeAlert;

	/**
	 * @return the recordLabelWithValueOnHomeAlert
	 */
	public WebElement getRecordLabelWithValueOnHomeAlert(int timeOut) {
		return isDisplayed(driver, recordLabelWithValueOnHomeAlert, "Visibility", timeOut,"Record Label With Value On Home Alert");
	}
	
	@FindBy(xpath="//span[@id='myGridACTALT-scroll-box']")
	private WebElement homeAlertScrollBox;

	/**
	 * @return the homeAlertScrollBox
	 */
	public WebElement getHomeAlertScrollBox(int timeOut) {
		return isDisplayed(driver, homeAlertScrollBox, "Visibility", timeOut, "home Alert Scroll Box");
	}
	
	@FindBy(xpath="//input[@id='recycleSearch']")
	private WebElement recycleBinSearchBox;

	/**
	 * @return the recycleBinSearchBox
	 */
	public WebElement getRecycleBinSearchBox(int timeOut) {
		return isDisplayed(driver, recycleBinSearchBox, "Visibility", timeOut, "Recycle bin search box");
	}
	
	@FindBy(xpath="//input[@name='doSearch']")
	private WebElement recycleBinSearchButton;

	/**
	 * @return the recycleBinSearchBox
	 */
	public WebElement getRecycleBinSearchButton(int timeOut) {
		return isDisplayed(driver, recycleBinSearchButton, "Visibility", timeOut, "Recycle bin search Button");
	}
	
	
	}
