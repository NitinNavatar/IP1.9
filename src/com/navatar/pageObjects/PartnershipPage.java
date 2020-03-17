/**
 * 
 */
package com.navatar.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static com.navatar.generic.CommonLib.*;
/**
 * @author Parul Singh
 *
 */
public class PartnershipPage extends BasePage {

	/**
	 * @param driver
	 */
	public PartnershipPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//input[@name='Name']")
	private WebElement partnershipLegalName;

	/**
	 * @return the partnershipLegalName
	 */
	public WebElement getPartnershipLegalName(int timeOut) {
		return isDisplayed(driver, partnershipLegalName, "Visibility", timeOut, "Partnership Legal Name");
	}
	
	@FindBy(xpath="//span[@class='lookupInput']//input")
	private WebElement fundTextBox;

	/**
	 * @return the fundTextBox
	 */
	public WebElement getFundTextBox(int timeOut) {
		return isDisplayed(driver, fundTextBox, "Visibility", timeOut, "Fund Name Text Box");
	}
	
	@FindBy(xpath="//div[@id='Name_ileinner']")
	private WebElement partnershipNameInViewMode;

	/**
	 * @return the partnershipNameInViewMode
	 */
	public WebElement getPartnershipNameInViewMode(int timeOut) {
		return isDisplayed(driver, partnershipNameInViewMode, "Visibility", timeOut, "Partnership Name in View Mode");
	}
}
