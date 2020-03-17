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
 * @author Ankur Rana
 *
 */
public class BoxPage {

	WebDriver driver = null;
	
	public BoxPage(WebDriver driver){
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	@FindBy(xpath="//input[@name='login']")
	private WebElement boxUserNameTextBox;

	/**
	 * @return the userNameTextBox
	 */
	public WebElement getBoxUserNameTextBox(int timeOut) {
		return isDisplayed(driver, boxUserNameTextBox, "Visibility", timeOut, "Box Username Text Box");
	}
	
	@FindBy(xpath="//button[text()=' Next']")
	private WebElement boxLoginNextButton;

	/**
	 * @return the boxLoginNextButton
	 */
	public WebElement getBoxLoginNextButton(int timeOut) {
		return isDisplayed(driver, boxLoginNextButton, "Visibility", timeOut, "next button");
	}

	@FindBy(xpath="//input[@name='password']")
	private WebElement boxPasswordTextBox;

	/**
	 * @return the boxPasswordTextBox
	 */
	public WebElement getBoxPasswordTextBox(int timeOut) {
		return isDisplayed(driver, boxPasswordTextBox, "Visibility", timeOut, "Password text box");
	}
	
	@FindBy(xpath="//button[text()=' Log In']")
	private WebElement boxLoginButton;

	/**
	 * @return the boxLoginButton
	 */
	public WebElement getBoxLoginButton(int timeOut) {
		return isDisplayed(driver, boxLoginButton, "Visibility", timeOut, "Login Button");
	}
	
	@FindBy(xpath="//span[text()='Admin Console']")
	private WebElement boxAdminConsoleLink;

	/**
	 * @return the boxAdminConsoleLink
	 */
	public WebElement getBoxAdminConsoleLink(int timeOut) {
		return isDisplayed(driver, boxAdminConsoleLink, "Visibility", timeOut, "Admin Console link");
	}
	
	@FindBy(xpath="//span[text()='Reports']")
	private WebElement boxReportLink;

	/**
	 * @return the boxReportLink
	 */
	public WebElement getBoxReportLink(int timeOut) {
		return isDisplayed(driver, boxReportLink, "Visibility", timeOut, "Report Link");
	}
	
	@FindBy(xpath="//button[@data-resin-target='filteredfoldersandfiles']")
	private WebElement boxFilesAndFoldersReportExport;

	/**
	 * @return the boxFilesAndFoldersReportExport
	 */
	public WebElement getBoxFilesAndFoldersReportExport(int timeOut) {
		return isDisplayed(driver, boxFilesAndFoldersReportExport, "Visibility", timeOut, "");
	}
	
	@FindBy(xpath="//span[@class='box-ui-icon-edit']")
	private WebElement boxBrowseFolderEditIcon;

	/**
	 * @return the boxBrowseFolderEditIcon
	 */
	public WebElement getBoxBrowseFolderEditIcon(int timeOut) {
		return isDisplayed(driver, boxBrowseFolderEditIcon, "Visibility", timeOut, "Edit Icon");
	}
	
	@FindBy(xpath="//button[@data-resin-target='foldersAndFiles']")
	private WebElement boxExportReportLink;

	/**
	 * @return the boxExportReportLink
	 */
	public WebElement getBoxExportReportLink(int timeOut) {
		return isDisplayed(driver, boxExportReportLink, "Visibility", timeOut, "Export Report Link");
	}
	
	@FindBy(xpath="//input[@data-resin-target='searchFolders']")
	private WebElement folderSearchTextBox;

	/**
	 * @return the folderSearchTextBox
	 */
	public WebElement getFolderSearchTextBox(int timeOut) {
		return isDisplayed(driver, folderSearchTextBox, "Visibility", timeOut, "Search folder text box");
	}
	
	@FindBy(xpath="//button[@data-resin-target='add-folder']")
	private WebElement boxDoneButton;

	/**
	 * @return the searchDoneButton
	 */
	public WebElement getBoxDoneButton(int timeOut) {
		return isDisplayed(driver, boxDoneButton, "Visibility", timeOut, "Done button");
	}
	
	@FindBy(xpath="//a[@data-resin-target='viewexportedreportsfolder']")
	private WebElement viewReportsLink;

	/**
	 * @return the viewReportsLink
	 */
	public WebElement getViewReportsLink(int timeOut) {
		return isDisplayed(driver, viewReportsLink, "Visibility", timeOut, "View Reports Link");
	}
	
	@FindBy(xpath="//span[text()='Choose']/../..")
	private WebElement boxChooseButton;

	/**
	 * @return the searchDoneButton
	 */
	public WebElement getBoxChooseButton(int timeOut) {
		return isDisplayed(driver, boxChooseButton, "Visibility", timeOut, "Choose button");
	}
	
	@FindBy(xpath="(//button[@class='btn pagination-menu-button'])[1]")
	private WebElement pagiNationButton;

	/**
	 * @return the pageNationButton
	 */
	public WebElement getPagiNationButton(int timeOut) {
		return isDisplayed(driver, pagiNationButton, "Visibility", timeOut, "page nation button");
	}
	
	public List<WebElement> getPagiNationDropDownList(){
		return FindElements(driver, "//div[contains(@class,'dropdown-menu-element dropdown-menu-')]//li", "pagination drop down list");
	}
	
	
}
