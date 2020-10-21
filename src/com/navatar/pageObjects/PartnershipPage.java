/**
 * 
 */
package com.navatar.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	
	@FindBy(xpath="//input[@name='Name']")
	private WebElement partnershipLegalName_Classic;
	
	@FindBy(xpath="//*[text()='Partnership Legal Name']/following-sibling::div/input")
	private WebElement partnershipLegalName_Lighting;

	/**
	 * @return the partnershipLegalName
	 */
	public WebElement getPartnershipLegalName(String environment,String mode,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			 return isDisplayed(driver, partnershipLegalName_Classic, "Visibility", timeOut, "Partnership Legal Name Classic");
		}else{
			 return isDisplayed(driver, partnershipLegalName_Lighting, "Visibility", timeOut, "Partnership Legal Name Lighting");
		}
		
	}
	
	@FindBy(xpath="//span[@class='lookupInput']//input")
	private WebElement fundTextBox_Classic;
	
	@FindBy(xpath="//*[text()='Fund']/following-sibling::div//input[@title='Search Funds' or contains(@placeholder,'Search Funds')]")
	private WebElement fundTextBox_Lighting;


	/**
	 * @return the fundTextBox
	 */
	public WebElement getFundTextBox(String environment,String mode,int timeOut) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
				return isDisplayed(driver, fundTextBox_Classic, "Visibility", timeOut, "Fund Name Text Box Classic");
		}else{
			return isDisplayed(driver, fundTextBox_Lighting, "Visibility", timeOut, "Fund Name Text Box Lighting");
		}
	
	}
	
	@FindBy(xpath="//div[@id='Name_ileinner']")
	private WebElement partnershipNameInViewMode_Classic;
	
	@FindBy(xpath="//div[@class='slds-media__body']//h1//span[@data-aura-class='uiOutputText']")
	private WebElement partnershipNameInViewMode_Lighting;

	/**
	 * @return the partnershipNameInViewMode
	 */
	public WebElement getPartnershipNameInViewMode(String environment,String mode,int timeOut,String partnershipName) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			return isDisplayed(driver, partnershipNameInViewMode_Classic, "Visibility", timeOut, "Partnership Name in View Mode Classic");
	}else{
		WebElement ele = FindElement(driver, "//*[contains(text(),'Partnership Legal Name')]/../..//*[text()='"+partnershipName+"']", "partnershipName", action.SCROLLANDBOOLEAN, timeOut);
		return ele;
	}

	}
}
