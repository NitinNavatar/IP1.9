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
public class DisclaimerPage extends BasePageBusinessLayer{

	/**
	 * 
	 */
	public DisclaimerPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@id='home']/h1")
	private WebElement disclaimerPageHeader;

	/**
	 * @return the disclaimerPageHeader
	 */
	public WebElement getDisclaimerPageHeader(int timeOut) {
		return isDisplayed(driver, disclaimerPageHeader, "Visibility", timeOut, "Disclaimer Page Header");
	}
	
	@FindBy(xpath="//div[@class='head_popup']")
	private WebElement confirmationPopUpHeader;

	/**
	 * @return the confirmationPopUpHeader
	 */
	public WebElement getConfirmationPopUpHeader(int timeOut) {
		return isDisplayed(driver, confirmationPopUpHeader, "Visibility", timeOut, "Confirmation Pop Up Header");
	}
	
	@FindBy(xpath="//div[@class='formbox']")
	private WebElement confirmationPopUpMessage;

	/**
	 * @return the confirmationPopUpMessage
	 */
	public WebElement getConfirmationPopUpMessage(int timeOut) {
		return isDisplayed(driver, confirmationPopUpMessage, "Visibility", timeOut, "Confirmation Pop Up Message");
	}
	
	@FindBy(xpath="//div[@class='paginationstyle']/a")
	private WebElement confirmationPopUpOkButton;

	/**
	 * @return the confirmationPopUpOkButton
	 */
	public WebElement getConfirmationPopUpOkButton(int timeOut) {
		return isDisplayed(driver, confirmationPopUpOkButton, "Visibility", timeOut, "Confirmation Pop Up Ok Button");
	}
	
	@FindBy(xpath="//div[@id='home']//div[2]/h1")
	private WebElement noPendingDisclaimerMessage;

	/**
	 * @return the noPendingDisclaimerMessage
	 */
	public WebElement getNoPendingDisclaimerMessage(int timeOut) {
		return isDisplayed(driver, noPendingDisclaimerMessage, "Visibility", timeOut, "No pending disclaimer message");
	}
	
	public List<WebElement> getCollapsedIcon() {
		return FindElements(driver, "//div[@class='heading_box_Investment']//img[contains(@src,'add-plus')]", "Expand Icon");
	}
	
	@FindBy(xpath="//div[@id='pendingPopup']//a[@title='Cancel']")
	private WebElement goToDisclaimerPopupCancelButton;

	/**
	 * @return the goToDisclaimerPopupCancelButton
	 */
	public WebElement getGoToDisclaimerPopupCancelButton(int timeOut) {
		return isDisplayed(driver, goToDisclaimerPopupCancelButton, "Visibility", timeOut, "Go to disclaimer popup cancel button");
	}
	
	@FindBy(xpath="//a[@title='Go to Disclaimers']")
	private WebElement goToDisclaimerButton;

	/**
	 * @return the goToDisclaimerButton
	 */
	public WebElement getGoToDisclaimerButton(int timeOut) {
		return isDisplayed(driver, goToDisclaimerButton, "Visibility", timeOut, "Go To Disclaimer button");
	} 
	 

	@FindBy (xpath="//span[@class='head']/following-sibling::span")
	private WebElement fundName;

	/**
	 * @return the fundName
	 */
	public WebElement getFundName(int timeOut) {
		return isDisplayed(driver, fundName, "Visibility", timeOut, "Fund Name");
	}

	
	@FindBy (xpath="//div[contains(@id,'filterGridContactDiva')]/div/b")
	private WebElement disclaimerName;

	/**
	 * @return the disclaimerName
	 */
	public WebElement getDisclaimerName(int timeOut) {
		return isDisplayed(driver, disclaimerName, "Visibility", timeOut, "Disclaimer Name");
	}

	@FindBy (xpath="//a[contains(text(),'Accept')]")
	private WebElement acceptButton;

	/**
	 * @return the acceptButton
	 */
	public WebElement getAcceptButton(int timeOut) {
		return isDisplayed(driver, acceptButton, "Visibility", timeOut, " Accept Button");
	}
}
