/**
 * 
 */
package com.navatar.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.navatar.generic.ExcelUtils;
import com.navatar.generic.CommonLib.action;

import static com.navatar.generic.AppListeners.*;
import static com.navatar.generic.CommonLib.*;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * @author Parul Singh
 *
 */
public class CommitmentPageBusinessLayer extends CommitmentPage implements CommitmentPageErrorMessage {

	/**
	 * @param driver
	 */
	public CommitmentPageBusinessLayer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @author Parul Singh
	 * @param LimitedPartner
	 * @param Partnership
	 * @param basedOnValue
	 * @param excelPath TODO
	 * @return true/false
	 */
	public boolean createCommitment(String LimitedPartner, String Partnership, String basedOnValue, String excelPath) {
		if (click(driver, getNewButton(60), "New Button", action.BOOLEAN)) {
			if (sendKeys(driver, getLimitedPartnerTextbox(60), LimitedPartner, "Limited Partner Text Box",
					action.BOOLEAN)) {
				if (sendKeys(driver, getPartnershipTextBox(60), Partnership, "Partnership Text Box", action.BOOLEAN)) {
					if (click(driver, getSaveButton(60), "Save Button", action.SCROLLANDBOOLEAN)) {
						if (getCommitmentIdInViewMode(60) != null) {
							String commitmentId = getText(driver, getCommitmentIdInViewMode(60), "Commitment ID",
									action.BOOLEAN);
							if(excelPath!=null) {
								ExcelUtils.writeData(excelPath,commitmentId, "Commitments", excelLabel.Variable_Name, basedOnValue,
										excelLabel.Commitment_ID);
							}else {
								ExcelUtils.writeData(commitmentId, "Commitments", excelLabel.Variable_Name, basedOnValue,
										excelLabel.Commitment_ID);
							}
							return true;
						} else {
							appLog.error("Not able to find Commitment id");
						}
					} else {
						appLog.error("Not able to click on save button");
					}
				} else {
					appLog.error("Not able to enter value in partnership text box");
				}
			} else {
				appLog.error("Not able to enter value in limited partner text box");
			}
		} else {
			appLog.error("Not able to click on new button so we cannot create commitment");
		}
		return false;
	}

	/*public boolean clickOnCreatedCommitmentId(String commitmentID) {
		if (click(driver, getGoButton(60), "go button", action.BOOLEAN)) {
			WebElement commitment = FindElement(driver,
					"//div[@class='x-panel-bwrap']//span[text()='" + commitmentID + "']", "Commitment ID",
					action.BOOLEAN, 60);
			if (commitment != null) {
				if (click(driver, commitment, "Commitment Id", action.SCROLLANDBOOLEAN)) {
					appLog.error("Clicked on Commitment ID successfully." + commitmentID);
					return true;
				} else {
					appLog.error("Not able to click on commitment ID." + commitmentID);
				}
			} else {
				appLog.error("Commitment Id is not displaying so cannot click on commitment ID");
			}
		} else {
			appLog.error("Not able to click on go button so cannot click on commitment ID");
		}
		return false;
	}*/

	/**
	 * @author Parul Singh
	 * @param CommitmentID
	 * @return true/false
	 */
	public boolean verifyDeletedCommitmentID(String CommitmentID) {
		if (click(driver, getGoButton(60), "Go button", action.BOOLEAN)) {
			for (int i = 0; i < getAllCommitmentID().size(); i++) {
				if (getAllCommitmentID().contains(CommitmentID)) {
					appLog.info("CommitmentID Is Displaying");
					return false;
				} else {
					appLog.info("CommitmentID is not Displaying");

				}
			}
		} else {
			appLog.info("Not able to click on go button");
			return false;
		}
		return true;
	}

	/**
	 * @author Parul Singh
	 * @param fieldName
	 * @param fieldLabel
	 * @param DecimalPlaces
	 * @param Value
	 * @param Limit
	 * @param timeOut
	 * @return true/false
	 */
	public boolean addCustomFieldInCommitment(String fieldName, String fieldLabel, String DecimalPlaces, String Value,
			String Limit,int timeOut) {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		List<WebElement> fieldLabel1 = FindElements(driver, "//div[contains(@id,'CustomFieldRelatedList')]//table[@class='list']//th/div[@class='CfLabelCol']/a", "Field label");
		List<WebElement> fieldName1 = FindElements(driver, "//div[contains(@id,'CustomFieldRelatedList_body')]//tr[1]/following-sibling::tr/td[5]", "Fileld name 1");
		for(int i =0; i < fieldLabel1.size();i++){
			String text= fieldName1.get(i).getText();
			System.err.println(text +"field name passed: "+fieldName);
			if(text.contains(fieldName) && fieldLabel1.get(i).getText().contains(fieldLabel)){
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
											if (fieldName.equalsIgnoreCase("Geolocation")) {
												if (sendKeys(driver, bp.getDecimalPlacesTextbox(timeOut), DecimalPlaces,
														"Decimal Places", action.SCROLLANDBOOLEAN)) {
													appLog.info("Entered Decimal Places successfully");
												} else {
													appLog.info("Not able to enter Decimal Places");
												}
											}
											if (fieldName.equalsIgnoreCase("Text")) {
												if (sendKeys(driver, bp.getLengthTextbox(timeOut), Limit, "Length Text box",
														action.SCROLLANDBOOLEAN)) {
													appLog.info("Entered Length successfully");
												} else {
													appLog.info("Not able to enter Length");
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
														appLog.error(splitedTabs[i] + "Not able to enter value");
													}
													}
												} else {
													appLog.info("Not able to click on radio button ");
												}
													}
											if (click(driver, bp.getNextButton(timeOut), "Next Button",
													action.SCROLLANDBOOLEAN)) {
												if (click(driver, bp.getNextButton(timeOut), "Next Button",
														action.SCROLLANDBOOLEAN)) {
													if (click(driver, bp.getSaveButtonInCustomFields(timeOut), "Save Button",
															action.SCROLLANDBOOLEAN)) {
														appLog.info("Clicked On SaveButton");
														List<WebElement> CommitmentList = bp
																.getAllCommitmentscustomFields();
														for (int i = 0; i < CommitmentList.size(); i++) {
															String CommitmentListName = trim(getText(driver,
																	CommitmentList.get(i), "Commitment Lists",
																	action.SCROLLANDBOOLEAN));
															if (CommitmentListName.contains(fieldLabel)) {
																appLog.info(fieldName
																		+ " Custom Field is added successfully");
																return true;
															} else {
																if (i == CommitmentList.size() - 1) {
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
	 * @param LimitedPartner
	 * @param Partnership
	 * @param basedOnValue
	 * @param AntiMoneyCheckbox
	 * @param finalCommitmentDate
	 * @param testDatetime
	 * @param email
	 * @param commitmentAmount
	 * @param geolocationLatitude
	 * @param GeolocationLongitude
	 * @param number
	 * @param CarriedInterestPercent
	 * @param phone
	 * @param partnerType
	 * @param multiPickList
	 * @param testText
	 * @param subdocDesc
	 * @param testTimeBeta
	 * @param URL
	 * @return true/false
	 */
	public boolean createCommitmentInFilterData(String LimitedPartner, String Partnership, String basedOnValue,String AntiMoneyCheckbox,String finalCommitmentDate,String testDatetime
			,String email,String commitmentAmount,String geolocationLatitude,String GeolocationLongitude,String number,String CarriedInterestPercent,String phone,
			String partnerType,String multiPickList,String testText,String subdocDesc,String testTimeBeta,String URL){
		FundRaisingPageBusinessLayer frp=new FundRaisingPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip=new InstitutionPageBusinessLayer(driver);
		if (click(driver, getNewButton(60), "New Button", action.BOOLEAN)) {
			if (sendKeys(driver, getLimitedPartnerTextbox(60), LimitedPartner, "Limited Partner Text Box",
					action.BOOLEAN)) {
				if (sendKeys(driver, getPartnershipTextBox(60), Partnership, "Partnership Text Box", action.BOOLEAN)) {
		if(AntiMoneyCheckbox!=""){
			if(AntiMoneyCheckbox.equalsIgnoreCase("True")){
				if(click(driver, getAntiMoneyLaunderingcheckbox(60), "Anti Money Checkbox", action.SCROLLANDBOOLEAN)){
					appLog.info("Clicked on checkbox");
				}else{
					appLog.info("Not able to click on checkbox");
					return false;
				}
			}
		}
		if(finalCommitmentDate!=""){
			if(sendKeys(driver, getFinalCommitmentDate(60), finalCommitmentDate, "Final Commitment Date", action.SCROLLANDBOOLEAN)){
					appLog.info("Entered final commitment Date");
				}else{
					appLog.info("Not able to Enter final commitment Date");
					return false;
			}
		}
		if(testDatetime!=""){
			if(sendKeys(driver, getTestDateTimeTextBox(60), testDatetime, "test Date time text box", action.SCROLLANDBOOLEAN)){
					appLog.info("Entered testDatetime");
				}else{
					appLog.info("Not able to Enter testDatetime");
					return false;
			}
		}
		if(email!=""){
			if(sendKeys(driver, getTestEmailAtTextBox(ExcelUtils.readData(filterPath,"CustomLabels", 22,2),60), email, "email text box", action.SCROLLANDBOOLEAN)){
					appLog.info("Entered email");
				}else{
					appLog.info("Not able to Enter email");
					return false;
			}
		}
		if(commitmentAmount!=""){
			if(sendKeys(driver, getCommitmentAmountCurrencyTextbox(60), commitmentAmount, "commitment Amount text box", action.SCROLLANDBOOLEAN)){
					appLog.info("Entered commitmentAmount");
				}else{
					appLog.info("Not able to Enter commitmentAmount");
					return false;
			}
		}
		if(geolocationLatitude!=""){
			if(sendKeys(driver, getTestGeolocationLatitudeTextbox(60), geolocationLatitude, "geolocation Latitude text box", action.SCROLLANDBOOLEAN)){
					appLog.info("Entered geolocation Latitude");
				}else{
					appLog.info("Not able to Enter geolocation Latitude");
					return false;
			}
		}
		if(GeolocationLongitude!=""){
			if(sendKeys(driver, getTestGeolocationLongitudeTextbox(60), GeolocationLongitude, "geolocation Longitude text box", action.SCROLLANDBOOLEAN)){
					appLog.info("Entered geolocation Longitude");
				}else{
					appLog.info("Not able to Enter geolocation Longitude");
					return false;
			}
		}
		if(number!=""){
			if(sendKeys(driver, getTestNumberTextbox(ExcelUtils.readData(filterPath,"CustomLabels", 24,2), 60), number, "number text box", action.SCROLLANDBOOLEAN)){
					appLog.info("Entered number");
				}else{
					appLog.info("Not able to Enter number");
					return false;
			}
		}
		if(CarriedInterestPercent!=""){
			if(sendKeys(driver, getCarriedInterestTextBox(60), CarriedInterestPercent, "Carried Interest Percent text box", action.SCROLLANDBOOLEAN)){
					appLog.info("Entered Carried Interest Percent");
				}else{
					appLog.info("Not able to Enter Carried Interest Percent");
					return false;
			}
		}
		if(phone!=""){
			if(sendKeys(driver, getTestPhoneTextbox(ExcelUtils.readData(filterPath,"CustomLabels", 25,2),60), phone, "phone text box", action.SCROLLANDBOOLEAN)){
					appLog.info("Entered phone");
				}else{
					appLog.info("Not able to Enter phone");
					return false;
			}
		}
		if(partnerType!=""){
			if(selectVisibleTextFromDropDown(driver, getPartnerTypeDropdown(60), "Partner Type Drop Down", partnerType)){
					appLog.info("Selected partnerType");
				}else{
					appLog.info("Not able to Select partnerType");
					return false;
			}
		}
		if(multiPickList!=""){
			if (ip.selectmultiList(multiPickList,getMultiplePicklist(60),frp.getMultiPicklistAddButton(60))) {
				appLog.info("Selected value successfully");
				}else{
					appLog.info("Not able to Select multiPickList");
					return false;
			}
		}
		if(testText!=""){
			if(sendKeys(driver, getTestTextbox(ExcelUtils.readData(filterPath,"CustomLabels", 27,2),60), testText, "testText text box", action.SCROLLANDBOOLEAN)){
					appLog.info("Entered testText");
				}else{
					appLog.info("Not able to Enter testText");
					return false;
			}
		}
		if(subdocDesc!=""){
			if(sendKeys(driver, getSudDocDescriptionTextBox(60), subdocDesc, "subdocDesc text box", action.SCROLLANDBOOLEAN)){
					appLog.info("Entered subdocDesc");
				}else{
					appLog.info("Not able to Enter subdocDesc");
					return false;
			}
		}
		if(testTimeBeta!=""){
			if(sendKeys(driver, getTestTimeBetaFTextBox(60), testTimeBeta, "test Time Beta text box", action.SCROLLANDBOOLEAN)){
					appLog.info("Entered testTimeBeta");
				}else{
					appLog.info("Not able to Enter testTimeBeta");
					return false;
			}
		}
		if(URL!=""){
			if(sendKeys(driver, getTestURL(ExcelUtils.readData(filterPath,"CustomLabels", 29,2),60), URL, "URL text box", action.SCROLLANDBOOLEAN)){
					appLog.info("Entered URL");
				}else{
					appLog.info("Not able to Enter URL");
					return false;
			}
		}
			if (click(driver, getSaveButton(60), "Save Button", action.SCROLLANDBOOLEAN)) {
						if (getCommitmentIdInViewMode(60) != null) {
							String commitmentId = getText(driver, getCommitmentIdInViewMode(60), "Commitment ID",
									action.BOOLEAN);
							ExcelUtils.writeData(filterPath,commitmentId, "Commitments", excelLabel.Commitment_ID, basedOnValue,
									excelLabel.Commitment_ID);
							return true;
						} else {
							appLog.error("Not able to find Commitment id");
						}
					} else {
						appLog.error("Not able to click on save button");
					}
				} else {
					appLog.error("Not able to enter value in partnership text box");
				}
			} else {
				appLog.error("Not able to enter value in limited partner text box");
			}
		} else {
			appLog.error("Not able to click on new button so we cannot create commitment");
		}
		return false;
		}

	/**
	 * @author Parul Singh
	 * @param commitmentID
	 * @return true/false
	 */
	public boolean clickOnCreatedCommitmentId(String commitmentID) {
		int i =1;
		if (click(driver, getGoButton(60), "go button", action.BOOLEAN)) {
			WebElement commitment = FindElement(driver,
					"//div[@class='x-panel-bwrap']//span[text()='" + commitmentID + "']", "Commitment ID",
					action.BOOLEAN, 10);
			if (commitment != null) {
				if (click(driver, commitment, "Commitment Id", action.SCROLLANDBOOLEAN)) {
					appLog.error("Clicked on Commitment ID successfully." + commitmentID);
					return true;
				} else {
					appLog.error("Not able to click on commitment ID." + commitmentID);
				}
			} else {
				while (true) {
					appLog.error("Commitment is not Displaying on "+i+ " Page: " + commitmentID);
					if (click(driver, getNextImageonPage(10), "Commitment Page Next Button",
							action.SCROLLANDBOOLEAN)) {

						appLog.info("Clicked on Next Button");
						commitment = FindElement(driver,
					"//div[@class='x-panel-bwrap']//span[text()='" + commitmentID + "']", "Commitment ID",
					action.BOOLEAN, 60);
						if (commitment != null) {
							if (click(driver, commitment, commitmentID, action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on Commitment name : " + commitmentID);
								return true;
								
							}
						}

						

					} else {
						appLog.error("Commitment Not Available : " + commitmentID);
						return false;
					}
					i++;
				}
			}
		} else {
			appLog.error("Not able to click on go button so cannot click on commitment ID");
		}
		return false;
	}
	
}
