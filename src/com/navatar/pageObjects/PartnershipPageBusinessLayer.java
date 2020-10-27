/**
 * 
 */
package com.navatar.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static com.navatar.generic.CommonLib.*;
import static com.navatar.generic.AppListeners.*;

/**
 * @author Parul Singh
 *
 */
public class PartnershipPageBusinessLayer extends PartnershipPage implements PartnershipPageErrorMessage{

	/**
	 * @param driver
	 */
	public PartnershipPageBusinessLayer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @author Parul Singh
	 * @param partnershipLegalName
	 * @param fund
	 * @return true/false
	 */
	public boolean createPartnership(String partnershipLegalName, String fund) {
		if (click(driver, getNewButton(60), "New Button", action.BOOLEAN)) {
			if (sendKeys(driver, getPartnershipLegalName(60), partnershipLegalName, "Partnership Legal Name",
					action.BOOLEAN)) {
				if (sendKeys(driver, getFundTextBox(60), fund, "Fund Text Box", action.BOOLEAN)) {
					if (click(driver, getSaveButton(60), "Save Button", action.BOOLEAN)) {
						if (getPartnershipNameInViewMode(60) != null) {
							String partnershipName = getText(driver, getPartnershipNameInViewMode(60),
									"Partnership name in view mode", action.BOOLEAN);
							if (partnershipName.equalsIgnoreCase(partnershipLegalName)) {
								appLog.info("Partnership created successfully.:" + partnershipLegalName);
								return true;
							} else {
								appLog.error("Partnership is not created successfully." + partnershipLegalName);
							}
						} else {
							appLog.error("Partnership name is not displaying");
						}
					} else {
						appLog.error("Not able to click on save button");
					}
				} else {
					appLog.error("Not able to enter value in fund text box");
				}
			} else {
				appLog.error("Not able to enter value in partnershp legal name text box");
			}
		} else {
			appLog.error("Not able to click on new button so we cannot create partnership");
		}
		return false;
	}
	
	/**
	 * @author Parul Singh
	 * @param partnershipLegalName
	 * @return true/false
	 */
	public boolean clickOnCreatedPartnership(String partnershipLegalName){
		if (click(driver, getGoButton(60), "Go Button", action.BOOLEAN)) {
			WebElement partnershipName = FindElement(driver,
					"//div[@class='x-panel-bwrap']//span[text()='" + partnershipLegalName + "']",
					"Partnership Legal Name", action.BOOLEAN, 60);
			if (partnershipName != null) {
				if (click(driver, partnershipName, "Partnership Name", action.SCROLLANDBOOLEAN)) {
					appLog.info("Clicked on partnership name" + partnershipLegalName + "successfully.");
					return true;
				} else {
					appLog.error("Not able to click on partnership name");
				}
			} else {
				appLog.error("Partnership name is not displaying");
			}
		} else {
			appLog.error("Not able to click on go button so cannot click on created partnership");
		}
		return false;
	}

	//Lightning Method ...
	
	public boolean createPartnership(String environment,String mode,String partnershipLegalName, String fund) {
		refresh(driver);
		ThreadSleep(5000);
		if (click(driver, getNewButton(environment,mode,60), "New Button", action.BOOLEAN)) {
			if (sendKeys(driver, getPartnershipLegalName(environment,mode,60), partnershipLegalName, "Partnership Legal Name",
					action.BOOLEAN)) {
				if (sendKeys(driver, getFundTextBox(environment,mode,60), fund, "Fund Text Box", action.BOOLEAN)) {
					if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
						ThreadSleep(1000);
						if (click(driver,
								FindElement(driver,
										"//*[contains(@class,'slds-listbox__option-text')]/*[@title='"+fund+"']",
										"fund Name List", action.THROWEXCEPTION, 30),
								fund + "   :   fund Name", action.BOOLEAN)) {
							appLog.info(fund + "  is present in list.");
						} else {
							appLog.info(fund + "  is not present in the list.");
						}
					}
					if (click(driver, getCustomTabSaveBtn(environment,mode,60), "Save Button", action.BOOLEAN)) {
						if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
							ThreadSleep(5000);
						}
						if (getPartnershipNameInViewMode(environment,mode,60,partnershipLegalName) != null) {
							String partnershipName = getText(driver, getPartnershipNameInViewMode(environment,mode,60,partnershipLegalName),
									"Partnership name in view mode", action.BOOLEAN);
							if (partnershipName.equalsIgnoreCase(partnershipLegalName)) {
								appLog.info("Partnership created successfully.:" + partnershipLegalName);
								return true;
							} else {
								appLog.error("Partnership is not created successfully." + partnershipLegalName);
							}
						} else {
							appLog.error("Partnership name is not displaying");
						}
					} else {
						appLog.error("Not able to click on save button");
					}
				} else {
					appLog.error("Not able to enter value in fund text box");
				}
			} else {
				appLog.error("Not able to enter value in partnershp legal name text box");
			}
		} else {
			appLog.error("Not able to click on new button so we cannot create partnership");
		}
		return false;
	}
	
	
	
	
	
	
}
