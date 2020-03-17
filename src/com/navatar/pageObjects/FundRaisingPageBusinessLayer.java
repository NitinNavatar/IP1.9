/**
 * 
 */
package com.navatar.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.navatar.generic.ExcelUtils;
import com.navatar.generic.CommonLib.action;
import com.navatar.generic.CommonLib.excelLabel;

import static com.navatar.generic.AppListeners.*;
import static com.navatar.generic.CommonLib.*;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * @author Parul Singh
 *
 */
public class FundRaisingPageBusinessLayer extends FundRaisingPage implements FundRaisingPageErrorMessage {

	/**
	 * @param driver
	 */
	public FundRaisingPageBusinessLayer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @author Parul Singh
	 * @param fundraisingName
	 * @param fundName
	 * @param legalName
	 * @return true/false
	 */
	public boolean createFundRaising(String fundraisingName, String fundName, String legalName) {
		if (click(driver, getNewButton(60), "New Button", action.SCROLLANDBOOLEAN)) {
			if (sendKeys(driver, getFundraisingName(60), fundraisingName, "FundRaising Name", action.BOOLEAN)) {
				if (sendKeys(driver, getFundName(60), fundName, "Fund Name", action.BOOLEAN)) {
					if (sendKeys(driver, getLegalName(60), legalName, "Legal Name", action.BOOLEAN)) {
						if (click(driver, getSaveButton(60), "Save Button", action.SCROLLANDBOOLEAN)) {
							if (getFundraisingNameInViewMode(60) != null) {
								String fundraising = getText(driver, getFundraisingNameInViewMode(60),
										"Fundraising name	", action.BOOLEAN);
								if (fundraising.equalsIgnoreCase(fundraisingName)) {
									appLog.info("Fundraising is created successfully.:" + fundraisingName);
								} else {
									appLog.info("FundRaising is not created successfully.:" + fundraisingName);
									return false;
								}

							} else {
								appLog.error("Not able to find fundraising name in view mode");
								return false;
							}
						} else {
							appLog.error("Not able to click on save button");
							return false;
						}
					} else {
						appLog.error("Not able to enter legal Name");
						return false;
					}
				} else {
					appLog.error("Not able to enter fund name");
					return false;
				}
			} else {
				appLog.error("Not able to enter value in fundraiisng text box");
				return false;
			}
		} else {
			appLog.error("Not able to click on new button so we cannot create fundraising");
			return false;
		}
		return true;
	}

	/**
	 * @author Parul Singh
	 * @param fieldName
	 * @param fieldLabel
	 * @param startingNumber
	 * @param DecimalPlaces
	 * @param Value
	 * @param FormulaReturnType
	 * @param SimpleFormulaValue
	 * @param timeOut
	 * @return true/false
	 */
	public boolean addCustomFieldInFundRaising(String fieldName, String fieldLabel, String startingNumber,
			String DecimalPlaces, String Value, String FormulaReturnType, String SimpleFormulaValue,int timeOut) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		List<WebElement> fieldLabel1 = FindElements(driver, "//div[contains(@id,'CustomFieldRelatedList_body')]//tr[1]/following-sibling::tr//th//div/a", "Field label");
		List<WebElement> fieldName1 = FindElements(driver, "//div[contains(@id,'CustomFieldRelatedList_body')]//tr[1]/following-sibling::tr/td[5]", "Fileld name 1");
		for(int i =0; i < fieldLabel1.size();i++){
		if(fieldLabel1.get(i).getText().contains(fieldLabel) && fieldName1.get(i).getText().contains(fieldName)){
				appLog.info("Field already added");
				return true;
			}
		}
							if (click(driver, bp.getNewButtonInAccountsField(60), "New Button",
									action.SCROLLANDBOOLEAN)) {
								WebElement ele = FindElement(driver, "//label[text()='" + fieldName + "']/..//input",
										"", action.SCROLLANDBOOLEAN, timeOut);
								if (click(driver, ele, "Radio checkbox", action.SCROLLANDBOOLEAN)) {
									if (click(driver, bp.getNextButton(timeOut), "Next Button", action.SCROLLANDBOOLEAN)) {
										if (sendKeys(driver, bp.getFieldLabelTextBox(timeOut), fieldLabel,
												"FieldLabel Text Box", action.SCROLLANDBOOLEAN)) {
											if (fieldName.equalsIgnoreCase("Auto Number")) {
												if (sendKeys(driver, bp.getStartingNumberFieldTextBox(timeOut),
														startingNumber, "Stating Number", action.SCROLLANDBOOLEAN)) {
													appLog.info("Entered starting number successfully");
												} else {
													appLog.info("Not able to enter Starting Number");
												}
											}
											if (fieldName.equalsIgnoreCase("Geolocation")) {
												if (sendKeys(driver, bp.getDecimalPlacesTextbox(timeOut), DecimalPlaces,
														"Decimal Places", action.SCROLLANDBOOLEAN)) {
													appLog.info("Entered Decimal Places successfully");
												} else {
													appLog.info("Not able to enter Decimal Places");
												}
											}
											if (fieldName.equalsIgnoreCase("Formula")) {
												WebElement formulaReturnTypeRadioButton = FindElement(driver,
														"//label[text()='" + FormulaReturnType + "']/..//input",
														"Radio Button", action.SCROLLANDBOOLEAN, timeOut);
												if (click(driver, formulaReturnTypeRadioButton, "Radio Button",
														action.SCROLLANDBOOLEAN)) {
													appLog.info("Selected Radio Button successfully");
													if (click(driver, bp.getNextButton(timeOut), "Next Button",
															action.SCROLLANDBOOLEAN)) {
														if (sendKeys(driver, bp.getFormulaTextBox(timeOut),
																SimpleFormulaValue, "Formula Text Box",
																action.SCROLLANDBOOLEAN)) {
															appLog.info("entered value in formula tetx box");
														} else {
															appLog.info("Not able to enter value in formula tetx box");
														}
													} else {
														appLog.info("Not able to click on next button");
													}
												} else {
													appLog.info("Not able to click on radio button");
												}
											}
											if (fieldName.equalsIgnoreCase("Picklist (Multi-Select)")) {
												if (click(driver, bp.getMultiPicklistValueRadioButton(timeOut),
														"Value Radio Button", action.SCROLLANDBOOLEAN)) {
													appLog.info("Selected Value Radio Button successfully");
													String[] splitedTabs = Value.split(",");
													for (int i = 0; i < splitedTabs.length; i++) {
													if (sendKeysWithoutClearingTextBox(driver, bp.getValueTextBox(timeOut), splitedTabs[i],
															"Value Text Box", action.SCROLLANDBOOLEAN)) {
														ThreadSleep(3000);
														try{
														Robot rob = new Robot();
														rob.keyPress(KeyEvent.VK_ENTER);
														 ThreadSleep(500);
														 rob.keyRelease(KeyEvent.VK_ENTER);
														 ThreadSleep(500);
														}catch(Exception e){
															appLog.error("Inside catch");
														}
												} else {
														appLog.error(splitedTabs[i]+"Not able to enter value");
													}
													}
												} else {
													appLog.info("Not able to select radio button ");
												}
											}
											if (click(driver, bp.getNextButton(timeOut), "Next Button",
													action.SCROLLANDBOOLEAN)) {
												if (click(driver, bp.getNextButton(timeOut), "Next Button",
														action.SCROLLANDBOOLEAN)) {
													if (click(driver, bp.getSaveButtonInCustomFields(timeOut), "Save Button",
															action.SCROLLANDBOOLEAN)) {
														appLog.info("Clicked On SaveButton");
														List<WebElement> FundRaisingList = bp
																.getAllFundraisingscustomFields();
														for (int i = 0; i < FundRaisingList.size(); i++) {
															String FundRaisingListName = trim(getText(driver,
																	FundRaisingList.get(i), "Commitment Lists",
																	action.SCROLLANDBOOLEAN));
															if (FundRaisingListName.contains(fieldLabel)) {
																appLog.info(fieldName
																		+ " Custom Field is added successfully");
																return true;
															} else {
																if (i == FundRaisingList.size() - 1) {
																	appLog.info(fieldName
																			+ " Custom Field is not added successfully");
																}
															}
														}
													} else {
														appLog.info("Not able to click on save button");
													}
												} else {
													appLog.info("Not able to click on Next Button");
												}
											} else {
												appLog.info("Not able to click on Next Button");
											}
										} else {
											appLog.info("Not able to enter value in field label text box");
										}
									} else {
										appLog.info("Not able to click on Next Button");
									}
								} else {
									appLog.info("Not able to click on Radio checkbox");
								}
							} else {
								appLog.info("Not able to click on New Button");
							}
		return false;
	}

	static String filterPath = System.getProperty("user.dir") + "/TestCases_Filter.xlsx";
	
	/**
	 * @author Parul Singh
	 * @param fundraisingName
	 * @param fundName
	 * @param legalName
	 * @param TestCheckbox
	 * @param targetCloseDate
	 * @param TestDatetime
	 * @param testEmail
	 * @param InvestmentHighAmount
	 * @param testGeolocationLatiitude
	 * @param testGeoLocationLongitude
	 * @param testnumber
	 * @param potentialManageMentFee
	 * @param phone
	 * @param stageDropdownVlaue
	 * @param reasonForDeclinedValue
	 * @param multiselectValue
	 * @param nextStep
	 * @param testTextArea
	 * @param testTimeBeta
	 * @param testUrl
	 * @return true/false
	 */
	public boolean createFundRaisingWithFilterData(String fundraisingName, String fundName, String legalName,
			String TestCheckbox, String targetCloseDate, String TestDatetime, String testEmail,
			String InvestmentHighAmount, String testGeolocationLatiitude, String testGeoLocationLongitude,
			String testnumber, String potentialManageMentFee, String phone, String stageDropdownVlaue,
			String reasonForDeclinedValue, String multiselectValue, String nextStep, String testTextArea,
			String testTimeBeta, String testUrl) {
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		if (click(driver, getNewButton(60), "New Button", action.SCROLLANDBOOLEAN)) {
			
			if (sendKeys(driver, getFundraisingName(60), fundraisingName, "FundRaising Name", action.SCROLLANDBOOLEAN)) {
				if (sendKeys(driver, getFundName(60), fundName, "Fund Name", action.SCROLLANDBOOLEAN)) {
					if (sendKeys(driver, getLegalName(60), legalName, "Legal Name", action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, getTargetCloseDateTextBox(60), targetCloseDate, "Target Close Date",action.SCROLLANDBOOLEAN)) {
							if (sendKeys(driver, getTestDateTimeTextBox(60), TestDatetime, "Test Date Time",
									action.SCROLLANDBOOLEAN)) {
								if (sendKeys(driver,
										getTestEmailTextbox(ExcelUtils.readData(filterPath,"CustomLabels", 12, 2), 60), testEmail,
										"Test Email", action.SCROLLANDBOOLEAN)) {
									if (sendKeys(driver, getInvestmenthighAmountTestBox(60), InvestmentHighAmount,
											"Investment High Amount", action.SCROLLANDBOOLEAN)) {
										if (sendKeys(driver, getTestGeolocationLatitudeTextBox(60),testGeolocationLatiitude, "test Geolocation Latiitude",action.SCROLLANDBOOLEAN)) {
											if (sendKeys(driver, getTestGeolocationLongitudeTextBox(60),testGeoLocationLongitude, "test Geolocation Longitude",	action.SCROLLANDBOOLEAN)) {
												if (sendKeys(driver,getTestNumberTextbox(ExcelUtils.readData(filterPath,"CustomLabels", 14, 2),60),testnumber, "test Number", action.SCROLLANDBOOLEAN)) {
													if (sendKeys(driver, getPotentialManagementFeeTextBox(60),potentialManageMentFee, "Potential management fee",action.SCROLLANDBOOLEAN)) {
														if (sendKeys(driver,getTestPhoneTextbox(ExcelUtils.readData(filterPath,"CustomLabels", 16, 2), 60),phone, "phone", action.SCROLLANDBOOLEAN)) {
															if(!stageDropdownVlaue.isEmpty()){
															if (selectVisibleTextFromDropDown(driver,getStageDropdown(60), "Stage Dropdown",stageDropdownVlaue)) {
																if (selectVisibleTextFromDropDown(driver,getReasonForDeclineDropdown(60),"Reason For Decline Dropdown",reasonForDeclinedValue)) {
																} else {
																	appLog.error("Not able to select Reason For Dropdownn value");
																	return false;
																}
															} else {
																appLog.error("Not able to select stage dropdown value");
																return false;
															}
														}
															if (ip.selectmultiList(multiselectValue,getMultiplePicklist(60),getMultiPicklistAddButton(60))) {
																		appLog.info("Selected value successfully");
																		if (sendKeys(driver, getNextStepTextbox(60),nextStep, "next Step",action.SCROLLANDBOOLEAN)) {
																			if (sendKeys(driver,getTestTextArea(ExcelUtils.readData(filterPath,"CustomLabels", 17, 2), 60),testTextArea, "test Text Area",action.SCROLLANDBOOLEAN)) {
																				if (sendKeys(driver,getTestTimeBetaFTextBox(60),testTimeBeta, "test Time Beta",	action.SCROLLANDBOOLEAN)) {
																					if (sendKeys(driver,getTestURL(ExcelUtils.readData(filterPath,"CustomLabels",19, 2),60),testUrl, "test URL",action.SCROLLANDBOOLEAN)) {
																						if (TestCheckbox.equalsIgnoreCase("True")) {
																							if (click(driver,getTestCheckboxFCheckbox(ExcelUtils.readData(filterPath,"CustomLabels",10,2),60),"Legal Name",action.SCROLLANDBOOLEAN)) {
																								appLog.info("clicked on checkbox");
																							} else {
																								appLog.info("Not able to click on checkbox");
																							}
																						}
																								if (click(driver,getSaveButton(60),"Save Button",action.SCROLLANDBOOLEAN)) {
																									if (getFundraisingNameInViewMode(60) != null) {
																										String fundraising = getText(driver,getFundraisingNameInViewMode(60),"Fundraising name",action.SCROLLANDBOOLEAN);
																										System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"+fundraising);
																										if (fundraising.equalsIgnoreCase(fundraisingName)) {
																											appLog.info("Fundraising is created successfully.:"+ fundraisingName);
																											return true;
																										} else {
																											appLog.info("FundRaising is not created successfully.:"+fundraisingName);
																										}
																									} else {
																										appLog.error("Not able to find fundraising name in view mode");
																									}
																								} else {
																									appLog.error("Not able to click on save button");
																								}
																							} else {
																						appLog.error("Not able to enter test URL");
																					}
																				} else {
																					appLog.error("Not able to enter test Time Beta value");
																				}
																			} else {
																				appLog.error("Not able to enter test Text Area value");
																			}
																		} else {
																			appLog.error("Not able to enter next step value");
																		}

																	} else {
																		appLog.error("Not able to add multiple picklist");
																	}
															} else {
															appLog.error("Not able to enter Phone");
														}
													} else {
														appLog.error("Not able to enter Potential management fee");
													}
												} else {
													appLog.error("Not able to enter Test Number");
												}
											} else {
												appLog.error("Not able to enter Test GeoLocation Longitude");
											}
										} else {
											appLog.error("Not able to enter Test GeoLocation Latitude");
										}
									} else {
										appLog.error("Not able to enter Investment High Amount");
									}
								} else {
									appLog.error("Not able to enter test email");
								}
							} else {
								appLog.error("Not able to enter test date time");
							}
						} else {
							appLog.error("Not able to enter target close date");
						}
					} else {
						appLog.error("Not able to enter legal Name");
					}
				} else {
					appLog.error("Not able to enter fund name");
				}
			} else {
				appLog.error("Not able to enter value in fundraiisng text box");
			}
		} else {
			appLog.error("Not able to click on new button so we cannot create fundraising");
		}
		return false;
	}
	
	/**
	 * @author Parul Singh
	 * @param fundRaising
	 * @return true/false
	 */
	public boolean clickOnCreatedFundRaising(String fundRaising){
		if (getSelectedOptionOfDropDown(driver, getViewDropdown(60), "View dropdown", "text").equalsIgnoreCase("All")) {
			if (click(driver, getGoButton(60), "Go button", action.BOOLEAN)) {

			}
			else {
				appLog.error("Go button not found");
				return false;
			}
		}
		WebElement CreatedfundRaising = FindElement(driver,
				"//div[@class='x-panel-bwrap']//a//span[contains(text(),'" + fundRaising + "')]", "FundRaising Name",
				action.BOOLEAN, 60);
		if (CreatedfundRaising != null) {
			if (click(driver, CreatedfundRaising, "FundRaising Name", action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on fundRaising name.:" + fundRaising);
				} else {
				appLog.error("Not able to click on fundRaisng Name");
				return false;
			}
		} else {
			appLog.error("FundRaising Name is not Displaying.:" + fundRaising);
			return false;
		}
	
	return true;	
	}
	
	/**
	 * @author Parul Singh
	 * @param contact
	 * @return true/false
	 */
	public boolean createFundRaisingContact(String contact){
		if(click(driver, getNewFundRaisngContactButton(60), "New FundRaising Contact button", action.SCROLLANDBOOLEAN)){
			if(sendKeys(driver, getFundraisngContactContacttextBox(60), contact, "Contact name", action.SCROLLANDBOOLEAN)){
			if(click(driver, getSaveButtonInCustomFields(60), "Save button", action.SCROLLANDBOOLEAN)){
			if(getTotalFundRaisngCount(60)!=null){
				String fundRaisngCount=trim(getText(driver, getTotalFundRaisngCount(60), "FundRaising Count", action.SCROLLANDBOOLEAN));
				if(!fundRaisngCount.equalsIgnoreCase("0")){					
					return true;
				}
			}else{
				appLog.info("Not able to find fundraisng contact ID");
			}			
			}else{
				appLog.info("Not able to click on save button");
			}
			}else{
				appLog.info("Not able to enter value in contact name text box");
			}
		}else{
			appLog.info("Not able to click on fundraisng contact button");
		}	
		return false;
	}
}
