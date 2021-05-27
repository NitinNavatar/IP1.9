/**
 * 
 */
package com.navatar.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.script.App;

import com.navatar.generic.CommonLib.Mode;
import com.navatar.generic.CommonLib.RelatedTab;
import com.navatar.generic.CommonLib.YesNo;
import com.navatar.generic.CommonLib.action;
import com.navatar.generic.CommonLib.excelLabel;
import com.relevantcodes.extentreports.LogStatus;
import com.navatar.generic.BaseLib;
import com.navatar.generic.ExcelUtils;

import static com.navatar.generic.AppListeners.*;
import static com.navatar.generic.CommonLib.*;

import java.util.ArrayList;
import java.util.List;
import static com.navatar.generic.CommonVariables.*;

/**
 * @author Parul Singh
 *
 */
public class InstitutionPageBusinessLayer extends InstitutionPage implements InstitutionPageErrorMessage {

	/**
	 * @param driver
	 */
	public InstitutionPageBusinessLayer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @author Ankit Jaiswal
	 * @param institutionName
	 * @return true/false
	 */
	public boolean createInstitution(String institutionName) {
		if (click(driver, getNewButton(30), "New Button", action.SCROLLANDBOOLEAN)) {
			appLog.info("clicked on new button");
			if (selectVisibleTextFromDropDown(driver, getRecordTypeOfNewRecordDropDownList(60),
					"Record type of new record drop down list", "Institution")) {
				appLog.info("selecte institution from record type of new record drop down list");
				if (click(driver, getContinueBtn(60), "Continue Button", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on continue button");
					if (sendKeys(driver, getLegalNameTextBox(30), institutionName, "leagl name text box",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("passed data in text box: " + institutionName);
						if (click(driver, getSaveButton(30), "save button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on save button");
							String str = getText(driver, getLegalNameLabelTextbox(30), "legal Name Label Text",
									action.SCROLLANDBOOLEAN);
							if (str != null) {
								if (str.contains(institutionName)) {
									appLog.info(
											"created institution " + institutionName + " is verified successfully.");
									appLog.info(institutionName + " is created successfully.");
									return true;
								} else {
									appLog.error(
											"Created institution " + institutionName + " is not matched with " + str);
								}
							} else {
								appLog.error("Created institution " + institutionName + " is not visible");
							}
						} else {
							appLog.error("Not able to click on save button so cannot create institution: "
									+ institutionName);
						}
					} else {
						appLog.error("Not able to pass data in legal name text box so cannot create institution: "
								+ institutionName);
					}
				} else {
					appLog.error(
							"Not able to click on continue button so cannot create institution: " + institutionName);
				}
			} else {
				appLog.error(
						"Not able to select institution from record type of new record drop down list so cannot create institution: "
								+ institutionName);
			}
		} else {
			appLog.error("Not able to click on New Button so cannot create institution: " + institutionName);
		}
		return false;
	}
	
	
	//Lightning Method............
	public boolean createInstitution(String environment,String mode,String institutionName,String recordType, String otherLabelFields,String otherLabelValues) {
		String labelNames[]=null;
		String labelValue[]=null;
		if(otherLabelFields!=null && otherLabelValues !=null) {
			labelNames= otherLabelFields.split(",");
			labelValue=otherLabelValues.split(",");
		}
		refresh(driver);
		ThreadSleep(3000);
		if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
			ThreadSleep(10000);
			if(clickUsingJavaScript(driver, getNewButton(environment, mode, 60), "new button")) {
				appLog.info("clicked on new button");
			}else {
				appLog.error("Not able to click on New Button so cannot create institution: " + institutionName);
				return false;
			}
		}else {
			if (click(driver, getNewButton(environment,mode,60), "New Button", action.SCROLLANDBOOLEAN)) {
				appLog.info("clicked on new button");
			} else {
				appLog.error("Not able to click on New Button so cannot create institution: " + institutionName);
				return false;
			}
		}
			if(mode.equalsIgnoreCase(Mode.Classic.toString())){
				ThreadSleep(2000);
				if (selectVisibleTextFromDropDown(driver, getRecordTypeOfNewRecordDropDownList(60),
						"Record type of new record drop down list", recordType)) {
					appLog.info("selecte institution from record type of new record drop down list");
				}else{
					appLog.error("Not Able to selecte institution from record type of new record drop down list");
					return false;
				}
			}else{
				ThreadSleep(2000);
				if(click(driver, getRadioButtonforRecordType(recordType, 60), "Radio Button for New Institution", action.SCROLLANDBOOLEAN)){
					appLog.info("Clicked on radio Button for institution from record type");
				}else{
					appLog.info("Not Able to Clicked on radio Button for institution from record type");
					return false;
				}
			}

				if (click(driver, getContinueOrNextBtn(environment,mode,60), "Continue Button", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on continue button");
					if (sendKeys(driver, getLegalNameTextBox(environment,mode,30), institutionName, "leagl name text box",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("passed data in text box: " + institutionName);
						if(labelNames!=null && labelValue!=null) {
							for(int i=0; i<labelNames.length; i++) {
								WebElement ele = getInstitutionPageTextBoxOrRichTextBoxWebElement(environment, mode, labelNames[i].trim(), 30);
								if(sendKeys(driver, ele, labelValue[i], labelNames[i]+" text box", action.SCROLLANDBOOLEAN)) {
									appLog.info("passed value "+labelValue[i]+" in "+labelNames[i]+" field");
									

									if (mode.equalsIgnoreCase(Mode.Lightning.toString()) && labelNames[i].toString().equalsIgnoreCase(InstitutionPageFieldLabelText.Parent_Institution.toString())) {
										
										ThreadSleep(1000);
										if (click(driver,
												FindElement(driver,
														"//*[contains(@class,'listbox')]//*[@title='"+labelValue[i]+"']",
														"Legal Name List", action.SCROLLANDBOOLEAN, 30),
												labelValue[i] + "   :   Legal Name", action.SCROLLANDBOOLEAN)) {
											appLog.info(labelValue[i] + "  is present in list.");
										} else {
											appLog.info(labelValue[i] + "  is not present in the list.");
											BaseLib.sa.assertTrue(false,labelValue[i] + "  is not present in the list.");
										}
									}
									
								}else {
									appLog.error("Not able to pass value "+labelValue[i]+" in "+labelNames[i]+" field");
									BaseLib.sa.assertTrue(false, "Not able to pass value "+labelValue[i]+" in "+labelNames[i]+" field");
								}
							}
							
						}
						if (click(driver, getCustomTabSaveBtn(environment,mode,30), "save button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on save button");
							ThreadSleep(5000);
//							String	xpath="//span[@class='custom-truncate uiOutputText'][text()='"+institutionName+"']";
//							WebElement ele = FindElement(driver, xpath, "Header : "+institutionName, action.BOOLEAN, 30);
							WebElement ele = verifyCreatedItemOnPage(Header.Company, institutionName);
							if (ele != null) {
									appLog.info("created institution " + institutionName + " is verified successfully.");
									appLog.info(institutionName + " is created successfully.");
									
									if(labelNames!=null && labelValue!=null ) {
										for(int i=0; i<labelNames.length; i++) {
//											
											if(fieldValueVerificationOnInstitutionPage(environment, mode, null, labelNames[i].replace("_", " ").trim(),labelValue[i])){
												appLog.info(labelNames[i]+" label value "+labelValue[i]+" is matched successfully.");
											}else {
												appLog.info(labelNames[i]+" label value "+labelValue[i]+" is not matched successfully.");
												BaseLib.sa.assertTrue(false, labelNames[i]+" label value "+labelValue[i]+" is not matched.");
											}
										
										}
									}
									return true;
								
							} else {
								appLog.error("Created institution " + institutionName + " is not visible");
							}
						} else {
							appLog.error("Not able to click on save button so cannot create institution: "
									+ institutionName);
						}
					} else {
						appLog.error("Not able to pass data in legal name text box so cannot create institution: "
								+ institutionName);
					}
				} else {
					appLog.error(
							"Not able to click on continue button so cannot create institution: " + institutionName);
				}
			
		
		return false;
	}
	
	//Lightning Method...............
	public boolean fieldValueVerificationOnInstitutionPage(String environment, String mode, TabName tabName,
			String labelName,String labelValue) {
		String finalLabelName;

		
		
		if(labelName.contains(excelLabel.Total_CoInvestment_Commitments.toString())) {
			labelName=LimitedPartnerPageFieldLabelText.Total_CoInvestment_Commitments.toString();
			labelValue=convertNumberIntoMillions(labelValue);

		}else if (labelName.contains(excelLabel.Total_Fund_Commitments.toString())) {
			labelName=LimitedPartnerPageFieldLabelText.Total_Fund_Commitments.toString();
			labelValue=convertNumberIntoMillions(labelValue);
		}/*else if (labelName.equalsIgnoreCase(excelLabel.Phone.toString()) || labelName.equalsIgnoreCase(excelLabel.Fax.toString())) {
			labelValue=changeNumberIntoUSFormat(labelValue);
		}*/

		if (labelName.contains("_")) {
			finalLabelName = labelName.replace("_", " ");
		} else {
			finalLabelName = labelName;
		}
		String xpath = "";
		WebElement ele = null;
		if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
			if(finalLabelName.equalsIgnoreCase(excelLabel.Institution_Type.toString().replace("_", " "))) {

				xpath = "(//span[text()='" + finalLabelName + "']/../following-sibling::td/div)[1]";
			}else {
				xpath = "//td[text()='"+finalLabelName+"']/following-sibling::td/div";
			}

		} else {
					/////////////////  Lighting New Start /////////////////////////////////////
				if(finalLabelName.contains("Street") || finalLabelName.contains("City") || finalLabelName.contains("State") || finalLabelName.contains("Postal") || finalLabelName.contains("Zip") || finalLabelName.contains("Country")) {
				
				if(finalLabelName.contains("Shipping") ||finalLabelName.contains("Other Street") || finalLabelName.contains("Other City") || finalLabelName.contains("Other State") || finalLabelName.contains("Other Zip") || finalLabelName.contains("Other Country") ) {
					xpath="//span[text()='Shipping Address']/../following-sibling::div//a[contains(@title,'"+labelValue+"')]";	
				}else{
					xpath="//span[text()='Address']/../following-sibling::div//a[contains(@title,'"+labelValue+"')]";
				}
				
			}else {
				
				if (labelName.equalsIgnoreCase(excelLabel.Phone.toString()) || labelName.equalsIgnoreCase(excelLabel.Fax.toString())) {
					xpath = "//span[text()='"+finalLabelName+"']/../following-sibling::div//*[contains(text(),'"+labelValue+"') or contains(text(),'"+changeNumberIntoUSFormat(labelValue)+"')]";	
				} else {
					xpath = "//span[text()='"+finalLabelName+"']/../following-sibling::div//*[text()='"+labelValue+"']";
				}
				
				
			}
				
				if (labelValue.isEmpty() || labelValue.equals("")) {
						xpath = "//span[text()='"+finalLabelName+"']/../following-sibling::div//*";
						ele = 		FindElement(driver, xpath, finalLabelName + " label text with  " + labelValue, action.SCROLLANDBOOLEAN, 10);
						scrollDownThroughWebelement(driver, ele, finalLabelName + " label text with  " + labelValue);
						if (ele!=null) {
							String aa = ele.getText().trim();
							System.err.println("Value  "+aa);

							if (aa.isEmpty() || aa.equals(labelValue)) {

								return true;	
							}else {
								return false;
							}

						}else {
							return false;
						}

					}
			
			ele = 		FindElement(driver, xpath, finalLabelName + " label text with  " + labelValue, action.SCROLLANDBOOLEAN, 10);
			scrollDownThroughWebelement(driver, ele, finalLabelName + " label text with  " + labelValue);
			ele = 	isDisplayed(driver,ele,"Visibility", 10, finalLabelName + " label text with  " + labelValue);
			if (ele != null) {
				String aa = ele.getText().trim();
				System.err.println("Value  "+aa);
				
				appLog.info(finalLabelName + " label text with  " + labelValue+" verified");
				return true;

			} else {
				appLog.error("<<<<<<   "+finalLabelName + " label text with  " + labelValue+" not verified "+"   >>>>>>");
			}
			return false;
			

			/////////////////  Lighting New End /////////////////////////////////////
		}

		if(finalLabelName.contains("Street") || finalLabelName.contains("City") || finalLabelName.contains("State") || finalLabelName.contains("Postal") || finalLabelName.contains("Zip") || finalLabelName.contains("Country")) {

			if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				//	xpath="//span[text()='Address Information']/../../following-sibling::div";
				if(finalLabelName.contains("Legal Name")){
					xpath="("+xpath+")[2]";
				}else if(finalLabelName.contains("Other Street") || finalLabelName.contains("Other City") || finalLabelName.contains("Other State") || finalLabelName.contains("Other Zip") || finalLabelName.contains("Other Country") ||
						finalLabelName.contains("Shipping")) {
					xpath="(//span[text()='Address Information']/../../following-sibling::div/div/div/div/div)[2]";	
				}else{
					xpath="(//span[text()='Address Information']/../../following-sibling::div/div/div/div/div)[1]";
				}
			}else {
				if(finalLabelName.contains("Other Street") || finalLabelName.contains("Other City") || 
						finalLabelName.contains("Other State") || finalLabelName.contains("Other Zip") || finalLabelName.contains("Other Country") || 
						finalLabelName.contains("Shipping Street") || finalLabelName.contains("Shipping City") || finalLabelName.contains("Shipping State") || 
						finalLabelName.contains("Shipping Zip") || finalLabelName.contains("Shipping Country")) {
					xpath="(//h3[text()='Address Information']/../following-sibling::div[1]//td//tbody/tr[1]/td)[2]";	
				}else{
					xpath="(//h3[text()='Address Information']/../following-sibling::div[1]//td//tbody/tr[1]/td)[1]";
				}
			}
		}
		ele = isDisplayed(driver,
				FindElement(driver, xpath, finalLabelName + " label text in " + mode, action.SCROLLANDBOOLEAN, 60),
				"Visibility", 30, finalLabelName + " label text in " + mode);
		if (ele != null) {
			String aa = ele.getText().trim();
			appLog.info("Lable Value is: "+aa);

			if (labelName.equalsIgnoreCase(excelLabel.Phone.toString()) || labelName.equalsIgnoreCase(excelLabel.Fax.toString())) {
				if(aa.contains(labelValue) || aa.contains(changeNumberIntoUSFormat(labelValue)) ) {
					appLog.info(labelValue + " Value is matched successfully.");
					return true;
				}
			}else if(aa.contains(labelValue)) {
				appLog.info(labelValue + " Value is matched successfully.");
				return true;

			}else {
				appLog.info(labelValue + " Value is not matched. Expected: "+labelValue+" /t Actual : "+aa);
			}
		} else {
			appLog.error(finalLabelName + " Value is not visible so cannot matched  label Value "+labelValue);
		}
		return false;

	}
	
	
	/**
	 * @author Ankit Jaiswal
	 * @param institutionName
	 * @param updateInstitutionName
	 * @param variable_value
	 * @return
	 */
	public boolean updateCreatedInstitute(String institutionName,String updateInstitutionName,String variable_value) {
		if(click(driver, getEditButton(10), "edit button", action.SCROLLANDBOOLEAN)) {
			if (sendKeys(driver, getLegalNameTextBox(60), updateInstitutionName, "leagl name text box",
					action.SCROLLANDBOOLEAN)) {
				appLog.info("passed data in text box: " + updateInstitutionName);
				if (click(driver, getSaveButton(60), "save button", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on save button");
					String str = getText(driver, getLegalNameLabelTextbox(60), "legal Name Label Text",
							action.SCROLLANDBOOLEAN);
					if (str != null) {
						if (str.contains(updateInstitutionName)) {
							appLog.info(
									"updated institution " + institutionName + " is verified successfully.");
							appLog.info(institutionName + " is updated successfully. :"+updateInstitutionName);
							ExcelUtils.writeData(updateInstitutionName, "Institutions",excelLabel.Variable_Name,variable_value, excelLabel.Institutions_Name);
							return true;
						} else {
							appLog.error(
									"updated institution " + updateInstitutionName + " is not matched with " + str);
						}
					} else {
						appLog.error("updated institution " + updateInstitutionName + " is not visible");
					}
				} else {
					appLog.error("Not able to click on save button so cannot update institution: "
							+ updateInstitutionName);
				}
			} else {
				appLog.error("Not able to pass data in legal name text box so cannot update institution: "
						+ updateInstitutionName);
			}
			
		}else {
			appLog.error("Not able to click on edit button so cannot update institute Name: "+updateInstitutionName);
		}
		
		return false;
	}
	
	
	
	
	
	
	
	/**
	 * @author Ankit Jaiswal
	 * @param lp_name
	 * @param updatedLPName
	 * @param variable_value
	 * @return
	 */
	public boolean updateCreateLimitedPartner(String lp_name, String updatedLPName,String variable_value) {
		if (click(driver, getEditButton(60), "Edit button", action.SCROLLANDBOOLEAN)) {
			if (sendKeys(driver, getLegalNameTextBox(60), updatedLPName, "LP name", action.SCROLLANDBOOLEAN)) {
				if (click(driver, getSaveButton(60), "Save Button", action.SCROLLANDBOOLEAN)) {
					appLog.info("Clicked on save button");
					String str = getText(driver, getLegalNameLabelTextbox(60), "LP name label text",
							action.SCROLLANDBOOLEAN);
					if (str != null) {
						if (str.contains(updatedLPName)) {
							appLog.info(
									"updated LP Name " + updatedLPName + " is verified successfully.");
							appLog.info(lp_name + " is updated successfully. :"+updatedLPName);
							ExcelUtils.writeData(updatedLPName, "Limited Partner",excelLabel.Variable_Name,variable_value, excelLabel.LimitedPartner_Name);
							return true;
						}
					}
				} else {
					appLog.error("Not able to click on save button so cannot update LP Name : "+lp_name);
				}

			} else {
				appLog.error("Legal name text box is not found so cannot update LP Name: "+lp_name);
			}

		} else {
			appLog.error("Not able to click edit icon so cannot update LP Name: "+lp_name);
		}
		return false;
	}
	
	/**
	 * @author Akul Bhutani
	 * @param inst_name
	 */
	public boolean clickOnCreatedInstitution(String inst_name) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
		int i =1;
		if (getSelectedOptionOfDropDown(driver, getViewDropdown(60), "View dropdown", "text")
				.equalsIgnoreCase("All Institutions")) {
			if (click(driver, getGoButton(60), "Go button", action.BOOLEAN)) {

			} else {
				appLog.error("Go button not found");
			}
		} else {
			if (selectVisibleTextFromDropDown(driver, getViewDropdown(60), "View dropdown", "All Institutions")) {
			} else {
				appLog.error("All institutions not found in dropdown");
			}

		}
		WebElement ele = isDisplayed(driver,
				FindElement(driver, "//div[@class='x-panel-bwrap']//span[text()='" + inst_name + "']/..",
						"Institution link", action.SCROLLANDBOOLEAN, 20),
				"visibility", 20, "");
		if (ele != null) {
			scrollDownThroughWebelement(driver, ele, "");
			if (click(driver, ele, inst_name + " name text", action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on institution link");
				return true;
			} else {
				appLog.error("Not able to click on " + inst_name);
			}
		} else {
			while (true) {
				appLog.error("Institutions is not Displaying on "+i+ " Page: " + inst_name);
				if (click(driver, getNextImageonPage(10), "Institutions Page Next Button",
						action.SCROLLANDBOOLEAN)) {
					ThreadSleep(2000);
					appLog.info("Clicked on Next Button");
					ele = FindElement(driver, "//div[@class='x-panel-bwrap']//span[text()='" + inst_name + "']/..",
							"Institution link", action.SCROLLANDBOOLEAN, 20);
					if (ele != null) {
						if (click(driver, ele, inst_name, action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Institutions name : " + inst_name);
							return true;
							
						}
					}

					

				} else {
					appLog.error("Institutions Not Available : " + inst_name);
					return false;
				}
				i++;
			}
		}	
		}else{
			if(clickOnAlreadyCreated_Lighting(environment, mode, TabName.InstituitonsTab, inst_name, 30)){
				appLog.info("Clicked on Institutions name : " + inst_name);
				return true;
			}else{
				appLog.error("Institutions Not Available : " + inst_name);
			}	
		}
		return false;
	}

	//Lightning Method...........
	public boolean clickOnCreatedInstitution(String environment,String mode,String inst_name) {

		if(mode.equalsIgnoreCase(Mode.Classic.toString())){

			List<WebElement> optionsInDropDown = FindElements(driver, "//select[@id='fcf']/option[text()='All Institutions']", "");
			String[] options = {};
			if(optionsInDropDown.size()>1){
				String[] o = {optionsInDropDown.get(0).getAttribute("value"), optionsInDropDown.get(1).getAttribute("value")};
				options = o;
			} else {
				String[] o = {optionsInDropDown.get(0).getAttribute("value")};
				options = o;
			}

			int i =1;
			if (getSelectedOptionOfDropDown(driver, getViewDropdown(60), "View dropdown", "text")
					.equalsIgnoreCase("All Institutions")) {
				if (click(driver, getGoButton(60), "Go button", action.BOOLEAN)) {

				} else {
					appLog.error("Go button not found");
				}
			} else {
				if (selectVisibleTextFromDropDown(driver, getViewDropdown(60), "View dropdown", options[0])) {
				} else {
					appLog.error("All institutions not found in dropdown");
				}

			}
			WebElement ele = isDisplayed(driver,
					FindElement(driver, "//div[@class='x-panel-bwrap']//span[text()='" + inst_name + "']/..",
							"Institution link", action.SCROLLANDBOOLEAN, 20),
					"visibility", 20, "");
			if(ele==null){
				if(options.length>1){
					if (selectVisibleTextFromDropDown(driver, getViewDropdown(60), "View dropdown", options[1])) {
						ele = isDisplayed(driver,
								FindElement(driver, "//div[@class='x-panel-bwrap']//span[text()='" + inst_name + "']/..",
										"Institution link", action.SCROLLANDBOOLEAN, 20),
								"visibility", 20, "");
					} else {
						appLog.error("All institutions not found in dropdown");
					}
				} else {
					appLog.error("All institutions not found in dropdown");
				}
			}
			if (ele != null) {
				scrollDownThroughWebelement(driver, ele, "");
				if (click(driver, ele, inst_name + " name text", action.SCROLLANDBOOLEAN)) {
					appLog.info("Clicked on institution link");
					return true;
				} else {
					appLog.error("Not able to click on " + inst_name);
				}
			} else {
				while (true) {
					appLog.error("Institutions is not Displaying on "+i+ " Page: " + inst_name);
					if (click(driver, getNextImageonPage(10), "Institutions Page Next Button",
							action.SCROLLANDBOOLEAN)) {
						ThreadSleep(2000);
						appLog.info("Clicked on Next Button");
						ele = FindElement(driver, "//div[@class='x-panel-bwrap']//span[text()='" + inst_name + "']/..",
								"Institution link", action.SCROLLANDBOOLEAN, 20);
						if (ele != null) {
							if (click(driver, ele, inst_name, action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on Institutions name : " + inst_name);
								return true;

							} else {
								appLog.error("Not able to click on " + inst_name);
							}
						}
					} else {
						appLog.error("Institutions Not Available : " + inst_name);
						return false;
					}
					i++;
				}
			}
		}else{
			if(clickOnAlreadyCreated_Lighting(environment, mode, TabName.InstituitonsTab, inst_name, 30)){
				appLog.info("Clicked on Institutions name : " + inst_name);
				ThreadSleep(5000);
				click(driver, getRelatedTab(RelatedTab.Investor_Portal.toString(), 5), RelatedTab.Investor_Portal.toString(), action.BOOLEAN);
				
				return true;
			}else{
				appLog.error("Institutions Not Available : " + inst_name);
			}	
		}
		return false;
	}
	
	
	
	
	/**
	 * @author Akul Bhutani
	 * @param lp_name
	 */
	public boolean clickOnCreatedLP(String lp_name) {
		int i =1;
		if (getSelectedOptionOfDropDown(driver, getViewDropdown(60), "View dropdown", "text")
				.equalsIgnoreCase("All Limited Partners")) {
			if (click(driver, getGoButton(60), "Go button", action.BOOLEAN)) {
				appLog.info("Clicked on Go button");
			} else {
				appLog.error("Go button not found");
			}
		} else {
			if (selectVisibleTextFromDropDown(driver, getViewDropdown(60), "View dropdown", "All Limited Partners")) {
				appLog.info("Select Limited Partners in View Dropdown");

			}

		}
		WebElement ele = isDisplayed(driver,
				FindElement(driver,
						"(//*[contains(@id,'ext-gen') and contains(@class,'-body')])[2]/div//table/tbody/tr//div/a/span[text()='"+lp_name+"']", "LP link",
						action.SCROLLANDBOOLEAN, 10),
				"visibility", 10, "");
		if (ele != null) {
			scrollDownThroughWebelement(driver, ele, "");
			if (click(driver, ele, lp_name + " name text", action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on limited partner link");
				return true;
			} else {
				appLog.error("Not able to click on " + lp_name);
			}
		} else {
			while (true) {
				appLog.error("limited partner is not Displaying on "+i+ " Page: " + lp_name);
				if (click(driver, getNextImageonPage(10), "limited partner Page Next Button",
						action.SCROLLANDBOOLEAN)) {
					ThreadSleep(2000);
					appLog.info("Clicked on Next Button");
					ele = FindElement(driver,
							"//*[@id='ext-gen12']/div/table/tbody/tr/td[4]/div/a/span[text()='" + lp_name + "']", "LP link",
							action.SCROLLANDBOOLEAN, 10);
					if (ele != null) {
						if (click(driver, ele, lp_name, action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on limited partner name : " + lp_name);
							return true;
							
						}
					}

					

				} else {
					appLog.error("limited partner Not Available : " + lp_name);
					return false;
				}
				i++;
			}
		}
		return false;
	}

	
	//Lightning Method...
	public boolean clickOnCreatedLP(String environment,String mode,String lp_name) {
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
		int i =1;
		if (getSelectedOptionOfDropDown(driver, getViewDropdown(60), "View dropdown", "text")
				.equalsIgnoreCase("All Limited Partners")) {
			if (click(driver, getGoButton(60), "Go button", action.BOOLEAN)) {
				appLog.info("Clicked on Go button");
			} else {
				appLog.error("Go button not found");
			}
		} else {
			if (selectVisibleTextFromDropDown(driver, getViewDropdown(60), "View dropdown", "All Limited Partners")) {
				appLog.info("Select Limited Partners in View Dropdown");

			}

		}
		WebElement ele = isDisplayed(driver,
				FindElement(driver,
						"//*[@id='ext-gen12']/div/table/tbody/tr/td[4]/div/a/span[text()='" + lp_name + "']", "LP link",
						action.SCROLLANDBOOLEAN, 10),
				"visibility", 10, "");
		if (ele != null) {
			scrollDownThroughWebelement(driver, ele, "");
			if (click(driver, ele, lp_name + " name text", action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on limited partner link");
				return true;
			} else {
				appLog.error("Not able to click on " + lp_name);
			}
		} else {
			while (true) {
				appLog.error("limited partner is not Displaying on "+i+ " Page: " + lp_name);
				if (click(driver, getNextImageonPage(10), "limited partner Page Next Button",
						action.SCROLLANDBOOLEAN)) {
					ThreadSleep(2000);
					appLog.info("Clicked on Next Button");
					ele = FindElement(driver,
							"//*[@id='ext-gen12']/div/table/tbody/tr/td[4]/div/a/span[text()='" + lp_name + "']", "LP link",
							action.SCROLLANDBOOLEAN, 10);
					if (ele != null) {
						if (click(driver, ele, lp_name, action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on limited partner name : " + lp_name);
							return true;
							
						}
					}

					

				} else {
					appLog.error("limited partner Not Available : " + lp_name);
					return false;
				}
				i++;
			}
		}
		}else{
			if(clickOnAlreadyCreated_Lighting(environment, mode, TabName.LimitedPartner, lp_name, 30)){
				appLog.info("Clicked on limited partner name : " + lp_name);
				ThreadSleep(5000);
				click(driver, getRelatedTab(RelatedTab.Investor_Portal.toString(), 5), RelatedTab.Investor_Portal.toString(), action.BOOLEAN);
				
				return true;
			}else{
				appLog.error("limited partner Not Available : " + lp_name);
			}	
		}
		return false;
	}
	
	
	
	/**
	 * @author Parul Singh
	 * @param lp_name
	 * @param inst_name
	 * @return true/false
	 */
	public boolean createLimitedPartner(String lp_name, String inst_name) {
			if (click(driver, getNewButton(60), "New button", action.SCROLLANDBOOLEAN)) {
				if (selectVisibleTextFromDropDown(driver, getRecordType(), "Record Type dropdown", "Limited Partner")) {
					if (click(driver, getContinueBtn(60), "Continue button", action.BOOLEAN)) {
						if (sendKeys(driver, getLegalNameTextBox(environment, mode, 30), lp_name, "LP name", action.SCROLLANDBOOLEAN)) {
							if (sendKeys(driver, getParentInstitutionTextBox(60), inst_name, "institution name",
									action.SCROLLANDBOOLEAN)) {
								if (click(driver, getSaveButton(60), "Save Button", action.SCROLLANDBOOLEAN)) {
									appLog.info("Clicked on save button");
									String str = getText(driver, getLegalNameLabelTextbox(60), "LP name label text",
											action.SCROLLANDBOOLEAN);
									if (str != null) {
										if (str.contains(lp_name)) {
											appLog.info(
													"created limited part " + lp_name + " is verified successfully.");
											appLog.info(lp_name + " is created successfully.");
											return true;
										}
									}
								} else {
									appLog.error("Not able to click on save button");
								}
							} else {
								appLog.error("Institution box not found");
							}
						} else {
							appLog.error("Legal name text box not found");
						}
					} else {
						appLog.error("Continue button not found");
					}
				} else {
					appLog.error("Dropdown not found");
				}
			} else {
				appLog.error("New button on institutions page not found");
			}
		return false;
	}

	/**
	 * @author Parul Singh
	 * @param InstitutionName
	 * @return true/false
	 */
	public boolean verifyDeletedInstitution(String InstitutionName) {
		WebElement ele, selectListView;
		ele = null;
		if (click(driver, getSelectListIcon(60), "Select List Icon", action.SCROLLANDBOOLEAN)) {
			ThreadSleep(3000);
			selectListView = FindElement(driver, "//div[@class='listContent']//li/a/span[text()='All Institutions']",
					"Select List View", action.SCROLLANDBOOLEAN, 30);
			if (click(driver, selectListView, "select List View", action.SCROLLANDBOOLEAN)) {
				ThreadSleep(3000);
				if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					refresh(driver);
					ThreadSleep(5000);
				}
			} else {
				appLog.error("Not able to select on Select View List");
			}
		} else {
			appLog.error("Not able to click on Select List Icon");
		}
		for (int i = 0; i < getAllInstitutions().size(); i++) {
			if (getAllInstitutions().contains(InstitutionName)) {
				appLog.info("Institution Is Displaying");
				return false;
			} else {
				appLog.info("Institution is not Displaying");

			}
		}
		return true;
	}

	/**
	 * @author Parul Singh
	 * @param LimitedPartner
	 * @return true/false
	 */
	public boolean verifyDeletedLimitedPartner(String LimitedPartner) {
		WebElement ele, selectListView;
		ele = null;
		if (click(driver, getSelectListIcon(60), "Select List Icon", action.SCROLLANDBOOLEAN)) {
			ThreadSleep(3000);
			selectListView = FindElement(driver, "//div[@class='listContent']//li/a/span[text()='All Limited Partners']",
					"Select List View", action.SCROLLANDBOOLEAN, 30);
			if (click(driver, selectListView, "select List View", action.SCROLLANDBOOLEAN)) {
				ThreadSleep(3000);
				if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
					refresh(driver);
					ThreadSleep(5000);
				}
			} else {
				appLog.error("Not able to select on Select View List");
			}
		} else {
			appLog.error("Not able to click on Select List Icon");
		}
		for (int i = 0; i < getAllInstitutions().size(); i++) {
			if (getAllInstitutions().contains(LimitedPartner)) {
				appLog.info("LimitedPartner Is Displaying");
				return false;
			} else {
				appLog.info("LimitedPartner is not Displaying");

			}
		}
		return true;
	}

	/**
	 * @author Parul Singh
	 * @param fieldName
	 * @param fieldLabel
	 * @param startingNumber
	 * @param DecimalPlaces
	 * @param timeOut
	 * @return true/false
	 */
	public boolean addCustomFieldInAccount(String fieldName, String fieldLabel, String startingNumber,
			String DecimalPlaces,int timeOut) {

		SetupPageBusinessLayer setup = new SetupPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		sendKeys(driver, getCustomFieldAndRelationShipSearchTextBox(60), fieldLabel, "search box", action.SCROLLANDBOOLEAN);
		ThreadSleep(3000);
		String fieldLabelXpath="//div[@id='setupComponent']//table[contains(@data-aura-class,'uiVirtualDataGrid')]/tbody/tr/td[1]//a/span[text()='"+fieldLabel+"']";
		WebElement fieldLabel1 = FindElement(driver, fieldLabelXpath, fieldLabel+" label xpath", action.BOOLEAN,5);
		if(fieldLabel1!=null) {
//			List<WebElement> fieldName1 = FindElements(driver, "//div[@id='setupComponent']//table[contains(@data-aura-class,'uiVirtualDataGrid')]/tbody/tr/td[1]//a/span[text()='"+fieldLabel+"']", "Fileld name 1");
			String fieldNameXpath="/../../following-sibling::td[2]/span[contains(text(),'"+fieldName+"')]";
			WebElement fieldName1 = FindElement(driver, fieldLabelXpath+fieldNameXpath, fieldName+" field Name xpath", action.BOOLEAN,5);
				if(fieldName1!=null){
					appLog.info("Field already added");
					return true;
				}
		}
		if(click(driver, setup.getCustomFieldNewButton(30),"new button", action.SCROLLANDBOOLEAN)) {
			appLog.info("Clicked on New Button");
			ThreadSleep(5000);
			if(switchToFrame(driver, 30, setup.getNewCustomFieldFrame(object.Institution,30))) {
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
								}
							}
							if (fieldName.equalsIgnoreCase("Geolocation")) {
								if (sendKeys(driver, bp.getDecimalPlacesTextbox(timeOut), DecimalPlaces,
										"Decimal Places", action.SCROLLANDBOOLEAN)) {
									appLog.info("Entered Decimal Places successfully");
								}
							}
							if (click(driver, bp.getNextButton(timeOut), "Next Button",
									action.SCROLLANDBOOLEAN)) {
								if (click(driver, bp.getNextButton(timeOut), "Next Button",
										action.SCROLLANDBOOLEAN)) {
									if (click(driver, bp.getSaveButtonInCustomFields(timeOut), "Save Button",
											action.SCROLLANDBOOLEAN)) {
										appLog.info("Clicked On SaveButton");
										switchToDefaultContent(driver);
										ThreadSleep(5000);
										sendKeys(driver, getCustomFieldAndRelationShipSearchTextBox(60), fieldLabel, "search box", action.SCROLLANDBOOLEAN);
										ThreadSleep(5000);
										List<WebElement> AccountsList = getAllAccountscustomFields();
										for (int i = 0; i < AccountsList.size(); i++) {
											String AccountListName = trim(
													getText(driver, AccountsList.get(i),
															"Account Lists", action.SCROLLANDBOOLEAN));
											if (AccountListName.contains(fieldLabel)) {
												appLog.info(fieldName+ " Custom Field is added successfully");
												return true;
											} else {
												if (i == AccountsList.size() - 1) {
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
			}else {
				log(LogStatus.FAIL, "Not able to switch in "+object.Institution+" new object frame so cannot add custom object", YesNo.Yes);
			}
		} else {
			appLog.info("Not able to click on new button");
		}
		switchToDefaultContent(driver);
		return false;
	}
	
	static String filterPath = System.getProperty("user.dir") + "/TestCases_Filter.xlsx";
	
	/**
	 * @author Parul Singh
	 * @param institutionName
	 * @param TestDateA
	 * @param TestDatetimeA
	 * @param TestEmailA
	 * @param CurrentAllocationPrivate
	 * @param TestGeolocationALatitude
	 * @param TestGeolocationALongitude
	 * @param EmployeesNumber
	 * @param AlternativePercent
	 * @param Phone
	 * @param OwnershipPicklist
	 * @param FundPreferencesPicklistMultiSelect
	 * @param KeywordsText
	 * @param ReferralSourceDistributon
	 * @param TestTimeBetaA
	 * @param Website
	 * @param InvestInFirstTimeFunds
	 * @return true/false
	 */
	public boolean createInstitutionForFilter(String institutionName, String TestDateA, String TestDatetimeA,
			String TestEmailA, String CurrentAllocationPrivate, String TestGeolocationALatitude,
			String TestGeolocationALongitude, String EmployeesNumber, String AlternativePercent, String Phone,
			String OwnershipPicklist, String FundPreferencesPicklistMultiSelect, String KeywordsText,
			String ReferralSourceDistributon, String TestTimeBetaA, String Website, String InvestInFirstTimeFunds) {
		if (click(driver, getNewButton(60), "New Button", action.SCROLLANDBOOLEAN)) {
			appLog.info("clicked on new button");
			if (selectVisibleTextFromDropDown(driver, getRecordTypeOfNewRecordDropDownList(60),
					"Record type of new record drop down list", "Institution")) {
				appLog.info("selecte institution from record type of new record drop down list");
				if (click(driver, getContinueBtn(60), "Continue Button", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on continue button");
					if (sendKeys(driver, getLegalNameTextBox(60), institutionName, "leagl name text box",
							action.SCROLLANDBOOLEAN)) {
						appLog.info("passed data in text box: " + institutionName);
						if (sendKeys(driver, getTestDateATextBox(60), TestDateA, "TestDate A text box",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("passed data in text box: " + TestDateA);
							if (sendKeys(driver, getTestDateTimeATextBox(60), TestDatetimeA, "TestDatetime A text box",
									action.SCROLLANDBOOLEAN)) {
								appLog.info("passed data in text box: " + TestDatetimeA);
								if (sendKeys(driver, getTestEmailAtTextBox(ExcelUtils.readData(filterPath,"CustomLabels", 4,2),60), TestEmailA, "TestEmailA text box",
										action.SCROLLANDBOOLEAN)) {
									appLog.info("passed data in text box: " + TestEmailA);
									if (sendKeys(driver, getCurrentAllocationPrivateEquityTextBox(60),
											CurrentAllocationPrivate, "CurrentAllocationPrivate text box",
											action.SCROLLANDBOOLEAN)) {
										appLog.info("passed data in text box: " + CurrentAllocationPrivate);
										if (sendKeys(driver, getTestGeolocationALatitudeTextBox(60),
												TestGeolocationALatitude, "TestGeolocationALatitude text box",
												action.SCROLLANDBOOLEAN)) {
											appLog.info("passed data in text box: " + TestGeolocationALatitude);
											if (sendKeys(driver, getTestGeolocationALongitudeTextBox(60),
													TestGeolocationALongitude, "TestGeolocationALongitude text box",
													action.SCROLLANDBOOLEAN)) {
												if (sendKeys(driver, getEmployeesTextBox(60), EmployeesNumber,
														"Employees text box", action.SCROLLANDBOOLEAN)) {
													if (sendKeys(driver, getAlternativePercentTextBox(60),
															AlternativePercent, "AlternativePercent text box",
															action.SCROLLANDBOOLEAN)) {
														if (sendKeys(driver, getPhoneTextbox(60), Phone,
																"getPhone text box", action.SCROLLANDBOOLEAN)) {
															if (selectVisibleTextFromDropDown(driver,
																	getOwnershipDropdown(60), "Ownership Dropdown",
																	OwnershipPicklist)) {
																if (selectmultiList(FundPreferencesPicklistMultiSelect,
																		getFundPreferenceMultiPickList(60),getFundPreferncesAddButton(60))) {
																	appLog.info("Selected value successfully");
																	if (sendKeys(driver, getKeywordsTextbox(60),
																			KeywordsText, "Keywords text box",
																			action.SCROLLANDBOOLEAN)) {
																		if (sendKeys(driver,
																				getRreferralSourceDescTextBox(60),
																				ReferralSourceDistributon,
																				"RreferralSourceDesc text box",
																				action.SCROLLANDBOOLEAN)) {
																			if (sendKeys(driver,
																					getTestTimeBetaATextBox(ExcelUtils.readData(filterPath,"CustomLabels", 6,2),60),
																					TestTimeBetaA,
																					"TestTimeBetaATextBox text box",
																					action.SCROLLANDBOOLEAN)) {
																				if (sendKeys(driver,
																						getWebsiteTextBox(60), Website,
																						"Website text box",
																						action.SCROLLANDBOOLEAN)) {
																					if (InvestInFirstTimeFunds
																							.equalsIgnoreCase("TRUE")) {
																						if (click(driver,
																								getInvestInFirstTimeCheckbox(
																										60),
																								"Checkbox",
																								action.SCROLLANDBOOLEAN)) {
																							appLog.info(
																									"Clicked on checkbox");
																						} else {
																							appLog.info(
																									"Not able to click on checkbox");
																						}
																					}
																					if (click(driver, getSaveButton(60),
																							"save button",
																							action.SCROLLANDBOOLEAN)) {
																						appLog.info(
																								"clicked on save button");
																						ThreadSleep(2000);
																						String str = getText(driver,
																								getLegalNameLabelTextbox(
																										60),
																								"legal Name Label Text",
																								action.SCROLLANDBOOLEAN);
																						if (str != null) {
																							if (str.contains(
																									institutionName)) {
																								appLog.info(
																										"created institution "
																												+ institutionName
																												+ " is verified successfully.");
																								appLog.info(
																										institutionName
																												+ " is created successfully.");
																								return true;
																							} else {
																								appLog.error(
																										"Created institution "
																												+ institutionName
																												+ " is not matched with "
																												+ str);
																							}
																						} else {
																							appLog.error(
																									"Created institution "
																											+ institutionName
																											+ " is not visible");
																						}
																					} else {
																						appLog.error(
																								"Not able to click on save button so cannot create institution: "
																										+ institutionName);
																					}
																				} else {
																					appLog.error(
																							"Not able to pass data in Website  text box so cannot create institution: "
																									+ institutionName);
																				}
																			} else {
																				appLog.error(
																						"Not able to pass data in TestTimeBetaA  text box so cannot create institution: "
																								+ institutionName);
																			}
																		} else {
																			appLog.error(
																					"Not able to pass data in RreferralSourceDesc  text box so cannot create institution: "
																							+ institutionName);
																		}
																	} else {
																		appLog.error(
																				"Not able to pass data in Keywords  text box so cannot create institution: "
																						+ institutionName);
																	}
																} else {
																	appLog.error(
																			"Not able to select multiple picklist so cannot create institution: "
																					+ institutionName);
																}
															} else {
																appLog.error(
																		"Not able to select value in owner picklist so cannot create institution: "
																				+ institutionName);
															}
														} else {
															appLog.error(
																	"Not able to pass data in Phone  text box so cannot create institution: "
																			+ institutionName);
														}
													} else {
														appLog.error(
																"Not able to pass data in AlternativePercent  text box so cannot create institution: "
																		+ institutionName);
													}
												} else {
													appLog.error(
															"Not able to pass data in Employees  text box so cannot create institution: "
																	+ institutionName);
												}
											} else {
												appLog.error(
														"Not able to pass data in TestGeolocationALongitude  text box so cannot create institution: "
																+ institutionName);
											}
										} else {
											appLog.error(
													"Not able to pass data in TestGeolocationALatitude  text box so cannot create institution: "
															+ institutionName);
										}
									} else {
										appLog.error(
												"Not able to pass data in CurrentAllocationPrivate  text box so cannot create institution: "
														+ institutionName);
									}
								} else {
									appLog.error(
											"Not able to pass data in Test TestEmailA  text box so cannot create institution: "
													+ institutionName);
								}
							} else {
								appLog.error(
										"Not able to pass data in Test Datetime A text box so cannot create institution: "
												+ institutionName);
							}
						} else {
							appLog.error("Not able to pass data in Test Date A text box so cannot create institution: "
									+ institutionName);
						}
					} else {
						appLog.error("Not able to pass data in legal name text box so cannot create institution: "
								+ institutionName);
					}
				} else {
					appLog.error(
							"Not able to click on continue button so cannot create institution: " + institutionName);
				}
			} else {
				appLog.error(
						"Not able to select institution from record type of new record drop down list so cannot create institution: "
								+ institutionName);
			}
		} else {
			appLog.error("Not able to click on New Button so cannot create institution: " + institutionName);
		}
		return false;
	}

	/**
	 * @author Parul Singh
	 * @param PicklistMultiSelect
	 * @param webElement
	 * @param addButton
	 * @return true/false
	 */
	public boolean selectmultiList(String PicklistMultiSelect, WebElement webElement,WebElement addButton) {
		boolean flag=true;
		String[] splitedTabs = PicklistMultiSelect.split(",");
		for (int i = 0; i < splitedTabs.length; i++) {
			if (selectVisibleTextFromDropDown(driver, webElement, "Elements", splitedTabs[i])) {
				appLog.info(splitedTabs[i] + " is selected successfully in available tabs");
				if (click(driver, addButton, "Add Button",
						action.SCROLLANDBOOLEAN)) {
					appLog.error("clicked on add button");
				} else {
					appLog.error("Not able to click on add button so cannot add custom tabs");
					flag=false;
				}
			} else {
				appLog.error(splitedTabs[i] + " custom tab name is not Available list Tab.");
				flag=false;
			}
		}
		return flag;

	}
	
	/**
	 * @author Parul Singh
	 * @param Fund
	 * @param DilgenceExpenseActual
	 * @param basedOnValue
	 * @return true/false
	 */
	public boolean createadvisorInvolvements(String Fund,String DilgenceExpenseActual,String basedOnValue){
		if(click(driver, getNewAdvisorInvolvementButton(60), "New Advisor involovement Button", action.SCROLLANDBOOLEAN)){
			if(sendKeys(driver, getFundTextBox(60), Fund, "Fund name", action.SCROLLANDBOOLEAN)){
				if(sendKeys(driver, getDilgenceExpenseActualTextBox(60), DilgenceExpenseActual, "Dilgence Expense Actual", action.SCROLLANDBOOLEAN)){
					if(click(driver, getSaveButtonInCustomFields(60), "Save button", action.SCROLLANDBOOLEAN)){
						if (getAdvisorInvolvementID(60) != null) {
							String AdvisorInvolvementId = getText(driver, getAdvisorInvolvementID(60), "AdvisorInvolvementId",
									action.BOOLEAN);
							ExcelUtils.writeData(AdvisorInvolvementId, "AdvisorInvolvement", excelLabel.AdvisorInvolvementID, basedOnValue,
									excelLabel.AdvisorInvolvementID);
							return true;
						} else {
							appLog.error("Not able to find Advisor involvement id");
						}
						
					}else{
						appLog.info("Not able to click on save button");
					}						
				}else{
					appLog.info("Not able to enter Dilgence Expense Actual ");
				}
			}else{
				appLog.info("Not able to enter Fund name");
			}
			}else{
				appLog.info("Not able to click on new advisor inveolvement button");
			}				
			return false;
	}
}