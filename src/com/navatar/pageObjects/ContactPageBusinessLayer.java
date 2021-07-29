/**
 * 
 */
package com.navatar.pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.navatar.generic.BaseLib;
import com.navatar.generic.ExcelUtils;
import com.navatar.generic.SoftAssert;
import com.navatar.generic.CommonLib.Mode;
import com.navatar.generic.CommonLib.PageName;
import com.navatar.generic.CommonLib.Workspace;
import com.navatar.generic.CommonLib.action;
import com.navatar.generic.CommonLib.excelLabel;
import com.relevantcodes.extentreports.LogStatus;

import static com.navatar.generic.CommonLib.*;
import static com.navatar.generic.CommonVariables.M12FundName1;
import static com.navatar.generic.CommonVariables.environment;
import static com.navatar.generic.CommonVariables.mode;

import java.util.List;
import java.util.Random;

import static com.navatar.generic.AppListeners.*;
/**
 * @author Parul Singh
 *
 */
public class ContactPageBusinessLayer extends ContactPage implements ContactPageErrorMessage{

	/**
	 * @param driver
	 */
	public ContactPageBusinessLayer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		
	}

	/**
	 * @author Parul Singh
	 * @param contactFirstName
	 * @param contactLastName
	 * @param legalName
	 * @param emailID
	 * @return
	 */
	public boolean createContact(String contactFirstName, String contactLastName, String legalName, String emailID) {
		if (click(driver, getNewButton(60), "New button", action.BOOLEAN)) {
			appLog.info("Clicked on new button");
			ThreadSleep(2000);
			if (sendKeys(driver, getContactFirstName(60), contactFirstName, "Contact first Name", action.BOOLEAN)) {
				if (sendKeys(driver, getContactLastName(60), contactLastName, "Contact Last Name", action.BOOLEAN)) {
					if (sendKeys(driver, getLegalName(60), legalName, "Legal Name", action.SCROLLANDBOOLEAN)) {
						if (sendKeys(driver, getEmailId(60), emailID, "Email ID", action.SCROLLANDBOOLEAN)) {
							if (click(driver, getSaveButton(60), "Save Button", action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on save button");
								if (getContactFullNameInViewMode(60) != null) {
									String contactFullName = getText(driver, getContactFullNameInViewMode(60), "Contact Name", action.BOOLEAN);
								if (contactFullName.equalsIgnoreCase(contactFirstName + " " + contactLastName)) {
										appLog.info("Contact Created Successfully :"+ contactFirstName + " " + contactLastName );
									} else {
										appLog.error("Contact did not get created successfully :"+ contactFirstName + " " + contactLastName);
										return false;
									}
								} else {
									appLog.error("Not able to find contact name label");
									return false;
								}
							} else {
								appLog.info("Not able to click on save button");
								return false;
							}

						} else {
							appLog.error("Not able to enter email id");
							return false;
						}
					} else {
						appLog.error("Not able to enter legal name");
						return false;
					}
				} else {
					appLog.error("Not able to enter last name in text box");
					return false;
				}
			} else {
				appLog.error("Not able to enter first Name in text box");
				return false;
			}
		} else {
			appLog.error("Not able to click on new buttton so we cannot create contact");
			return false;
		}
		return true;
	}
	
	//Lightning Method....
	/**
	 * @author Azhar Alam
	 * @param environment
	 * @param mode
	 * @param contactFirstName
	 * @param contactLastName
	 * @param legalName
	 * @param emailID
	 * @return true if able to create Contact
	 */
	public boolean createContact(String environment, String mode, String contactFirstName, String contactLastName,
			String legalName, String emailID, String otherLabelFields,String otherLabelValues,CreationPage creationPage) {
		InstitutionPageBusinessLayer ins = new InstitutionPageBusinessLayer(driver);
		String labelNames[]=null;
		String labelValue[]=null;
		if(otherLabelFields!=null && otherLabelValues !=null) {
			labelNames= otherLabelFields.split(",");
			labelValue=otherLabelValues.split(",");
		}
		if(creationPage.toString().equalsIgnoreCase(CreationPage.InstitutionPage.toString())) {
			if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				
				if(ClickonRelatedTab_Lighting(environment, RecordType.Contact)) {
					appLog.info("clicked on related list tab");
				}else {
					appLog.error("Not able to click on related list tab so cannot create contact: "+contactFirstName+" "+contactLastName);
					return false;
				}
			}
			if(click(driver, getNewContactBtn(environment, mode, 30), "new contact button in "+mode, action.SCROLLANDBOOLEAN)) {
				appLog.info("clicked on new contact button in institution page");
			}else {
				appLog.error("Not able to click on new button on institution page so cannot create contact: "+contactFirstName+" "+contactLastName);
				return false;
			}
		}else {
			refresh(driver);
			ThreadSleep(3000);
			if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				ThreadSleep(5000);
				if(clickUsingJavaScript(driver, getNewButton(environment, mode, 60), "new button")) {
					appLog.info("clicked on new button");
				}else {
					appLog.error("Not able to click on New Button so cannot create Contact: " + contactFirstName+" "+contactLastName);
					return false;
				}
			}else {
				ThreadSleep(5000);
				if (click(driver, getNewButton(environment,mode,60), "New Button", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on new button");
				} else {
					appLog.error("Not able to click on New Button so cannot create Contact: " + contactFirstName+" "+contactLastName);
					return false;
				}
			}
		}
			ThreadSleep(2000);
			if (sendKeys(driver, getContactFirstName(environment, mode, 60), contactFirstName, "Contact first Name",
					action.BOOLEAN)) {
				if (sendKeys(driver, getContactLastName(environment, mode, 60), contactLastName, "Contact Last Name",
						action.BOOLEAN)) {
					
					if(creationPage.toString().equalsIgnoreCase(CreationPage.InstitutionPage.toString())) {
						
					}else {
						if (sendKeys(driver, getLegalName(environment, mode, 60), legalName, "Legal Name",
								action.SCROLLANDBOOLEAN)) {
							if (mode.equalsIgnoreCase(Mode.Lightning.toString())) {
								ThreadSleep(1000);
								if (click(driver,
										FindElement(driver,
												"//*[contains(@class,'listbox')]//*[@title='"+legalName+"']",
												"Legal Name List", action.THROWEXCEPTION, 30),
										legalName + "   :   Legal Name", action.BOOLEAN)) {
									appLog.info(legalName + "  is present in list.");
								} else {
									appLog.info(legalName + "  is not present in the list.");
									return false;
								}
							}
							
						} else {
							appLog.error("Not able to enter legal name");
							return false;
						}
					}
					
						if (sendKeys(driver, getEmailId(environment, mode, 60), emailID, "Email ID",
								action.SCROLLANDBOOLEAN)) {
							if(labelNames!=null && labelValue!=null) {
								for(int i=0; i<labelNames.length; i++) {
									WebElement ele = getContactPageTextBoxOrRichTextBoxWebElement(environment, mode, labelNames[i].trim(), 30);
									if(sendKeys(driver, ele, labelValue[i], labelNames[i]+" text box", action.SCROLLANDBOOLEAN)) {
										appLog.info("passed value "+labelValue[i]+" in "+labelNames[i]+" field");
									}else {
										appLog.error("Not able to pass value "+labelValue[i]+" in "+labelNames[i]+" field");
										BaseLib.sa.assertTrue(false, "Not able to pass value "+labelValue[i]+" in "+labelNames[i]+" field");
									}
								}
								
							}
							if (click(driver, getSaveButton(environment, mode, 60), "Save Button",
									action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on save button");
								if(creationPage.toString().equalsIgnoreCase(CreationPage.InstitutionPage.toString())) {
									if(mode.equalsIgnoreCase(Mode.Lightning.toString())){
										if(clickOnGridSection_Lightning(environment, mode,RelatedList.Contacts, 30)) {
											WebElement ele = isDisplayed(driver, FindElement(driver, "//span[@title='Contact Name']/ancestor::table/tbody/tr/th/span/a", "Contact Name Text", action.SCROLLANDBOOLEAN, 30), "visibility", 20, "");
											if (ele != null) {
												String contactFullName = getText(driver,ele, "Contact Name",action.BOOLEAN);
												System.err.println("Contact Name : "+contactFullName);
												if (contactFullName.contains(contactFirstName + " " + contactLastName)) {
													appLog.info("Contact Created Successfully :" + contactFirstName + " "+ contactLastName);
													return true;
												} else {
													appLog.error("Contact did not get created successfully :" + contactFirstName
															+ " " + contactLastName);
												}
											} else {
												appLog.error("Not able to find contact name label");
											}
										}else {
											log(LogStatus.ERROR, "Not able to click on Contacts related list view all section so cannot verify Created Contact "+contactFirstName+" "+contactLastName, YesNo.Yes);
										}
									} else {
										if (getContactFullNameInViewMode(environment, mode, 60) != null) {
											String contactFullName = getText(driver,
													getContactFullNameInViewMode(environment, mode, 60), "Contact Name",
													action.BOOLEAN);
											System.err.println("Contact Name : "+contactFullName);
											if (contactFullName.contains(contactFirstName + " " + contactLastName)) {
												appLog.info("Contact Created Successfully :" + contactFirstName + " "
														+ contactLastName);
												if(labelNames!=null && labelValue!=null ) {
													for(int i=0; i<labelNames.length; i++) {
														if(fieldValueVerificationOnContactPage(environment, mode, null, labelNames[i].replace("_", " ").trim(),labelValue[i])){
															appLog.info(labelNames[i]+" label value "+labelValue[i]+" is matched successfully.");
														}else {
															appLog.info(labelNames[i]+" label value "+labelValue[i]+" is not matched successfully.");
															BaseLib.sa.assertTrue(false, labelNames[i]+" label value "+labelValue[i]+" is not matched.");
														}
													}
												}
												return true;
											} else {
												appLog.error("Contact did not get created successfully :" + contactFirstName
														+ " " + contactLastName);
											}
										} else {
											appLog.error("Not able to find contact name label");
										}
									}
								}else {
									if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
										ThreadSleep(2000);
										refresh(driver);
										ThreadSleep(5000);
									}

									if (getContactFullNameInViewMode(environment, mode, 60) != null) {
										String contactFullName = getText(driver,
												getContactFullNameInViewMode(environment, mode, 60), "Contact Name",
												action.BOOLEAN);
										System.err.println("Contact Name : "+contactFullName);
										if (contactFullName.contains(contactFirstName + " " + contactLastName)) {
											appLog.info("Contact Created Successfully :" + contactFirstName + " "
													+ contactLastName);
											if(labelNames!=null && labelValue!=null ) {
												for(int i=0; i<labelNames.length; i++) {
													if(fieldValueVerificationOnContactPage(environment, mode, null, labelNames[i].replace("_", " ").trim(),labelValue[i])){
														appLog.info(labelNames[i]+" label value "+labelValue[i]+" is matched successfully.");
													}else {
														appLog.info(labelNames[i]+" label value "+labelValue[i]+" is not matched successfully.");
														BaseLib.sa.assertTrue(false, labelNames[i]+" label value "+labelValue[i]+" is not matched.");
													}
												}
											}
											return true;
										} else {
											appLog.error("Contact did not get created successfully :" + contactFirstName
													+ " " + contactLastName);
										}
									} else {
										appLog.error("Not able to find contact name label");
									}
									
								}
								
							} else {
								appLog.info("Not able to click on save button");
							}

						} else {
							appLog.error("Not able to enter email id");
						}
					
				} else {
					appLog.error("Not able to enter last name in text box");
				}
			} else {
				appLog.error("Not able to enter first Name in text box");
			}
		return false;
	}

	
	//Lightning Mthod....
	public boolean fieldValueVerificationOnContactPage(String environment, String mode, TabName tabName,
			String labelName,String labelValue) {
		String finalLabelName="";


		if (labelName.contains("_")) {
			if(labelName.equalsIgnoreCase(excelLabel.Asst_Phone.toString())) {
//				finalLabelName= IndiviualInvestorFieldLabel.Asst_Phone.toString();
			}else {
				finalLabelName = labelName.replace("_", " ");
			}
		} else {
			finalLabelName = labelName;
		}
		String xpath = "";
		WebElement ele = null;
		if (mode.equalsIgnoreCase(Mode.Classic.toString())) {
			xpath = "(//td[text()='"+finalLabelName+"']/following-sibling::td)[1]";

		} else {

			/////////////////  Lighting New Start /////////////////////////////////////
			if(finalLabelName.contains("Street") || finalLabelName.contains("City") || finalLabelName.contains("State") || finalLabelName.contains("Postal") || finalLabelName.contains("ZIP") || finalLabelName.contains("Zip")|| finalLabelName.contains("Country")) {

				if(finalLabelName.contains("Other") || finalLabelName.contains("Other Street") || finalLabelName.contains("Other City") || finalLabelName.contains("Other State") || finalLabelName.contains("Other Zip") || finalLabelName.contains("Other Country")) {
					xpath="//span[text()='Other Address']/../following-sibling::div//a[contains(@title,'"+labelValue+"')]";	
				}else{
					xpath="//span[text()='Mailing Address']/../following-sibling::div//a[contains(@title,'"+labelValue+"')]";
				}

			}else {

				if (labelName.equalsIgnoreCase(excelLabel.Phone.toString()) || labelName.equalsIgnoreCase(excelLabel.Fax.toString())||
						labelName.equalsIgnoreCase(ContactPageFieldLabelText.Mobile.toString()) ||
						labelName.equalsIgnoreCase(excelLabel.Asst_Phone.toString())) {
					xpath = "//span[text()='"+finalLabelName+"']/../following-sibling::div//*[contains(text(),'"+labelValue+"') or contains(text(),'"+changeNumberIntoUSFormat(labelValue)+"')]";	
				} else {
					xpath = "//span[text()='"+finalLabelName+"']/../following-sibling::div//*[text()='"+labelValue+"']";
				}


			}
			ele = 		FindElement(driver, xpath, finalLabelName + " label text with  " + labelValue, action.SCROLLANDBOOLEAN, 10);
			scrollDownThroughWebelement(driver, ele, finalLabelName + " label text with  " + labelValue);
			ele = 	isDisplayed(driver,ele,"Visibility", 10, finalLabelName + " label text with  " + labelValue);
			if (ele != null) {
				String aa = ele.getText().trim();
				appLog.info(finalLabelName + " label text with  " + labelValue+" verified");
				return true;

			} else {
				appLog.error("<<<<<<   "+finalLabelName + " label text with  " + labelValue+" not verified "+">>>>>>");
			}
			return false;


			/////////////////  Lighting New End /////////////////////////////////////


		}

		/////////////////////////////////////////////////////////////////////////////////////////
		if(finalLabelName.contains("Street") || finalLabelName.contains("City") || finalLabelName.contains("State") || finalLabelName.contains("Postal") || finalLabelName.contains("ZIP") || finalLabelName.contains("Zip")|| finalLabelName.contains("Country")) {

			if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
				//	xpath="//span[text()='Address Information']/../../following-sibling::div";
				if(finalLabelName.contains("Legal Name")){
					xpath="("+xpath+")[2]";
				}else if(finalLabelName.contains("Other Street") || finalLabelName.contains("Other City") || finalLabelName.contains("Other State") || finalLabelName.contains("Other Zip") || finalLabelName.contains("Other Country")) {
					xpath="(//span[text()='Address Information']/../../following-sibling::div/div/div/div/div)[2]";	
				}else{
					xpath="(//span[text()='Address Information']/../../following-sibling::div/div/div/div/div)[1]";
				}
			}else {
				if(finalLabelName.contains("Other Street") || finalLabelName.contains("Other City") || finalLabelName.contains("Other State") || finalLabelName.contains("Other Zip") || finalLabelName.contains("Other Country")) {
					xpath="(//h3[text()='Address Information']/../following-sibling::div[1]//td//tbody/tr[1]/td)[2]";	
				}else{
					xpath="(//h3[text()='Address Information']/../following-sibling::div[1]//td//tbody/tr[1]/td)[1]";
				}
			}
		}

		ele = isDisplayed(driver,
				FindElement(driver, xpath, finalLabelName + " label text in " + mode, action.SCROLLANDBOOLEAN, 5),
				"Visibility", 5, finalLabelName + " label text in " + mode);
		if (ele != null) {
			String aa = ele.getText().trim();
			appLog.info("<<<<<<<<     "+finalLabelName+ " : Lable Value is: "+aa+"      >>>>>>>>>>>");

			if (aa.isEmpty()) {
				appLog.error(finalLabelName + " Value is Emptylabel Value "+labelValue);
				return false;
			}

			if (labelName.equalsIgnoreCase(excelLabel.Phone.toString()) || labelName.equalsIgnoreCase(excelLabel.Fax.toString())||
					labelName.equalsIgnoreCase(ContactPageFieldLabelText.Mobile.toString()) ||
					labelName.equalsIgnoreCase(excelLabel.Asst_Phone.toString())) {

				if(aa.contains(labelValue) || aa.contains(changeNumberIntoUSFormat(labelValue))) {
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
	 * @return String
	 */
	public String generateRandomEmailId() {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(99999);
		String contactEmail = ExcelUtils.readDataFromPropertyFile("gmailUserName");
		String[] EmailIDContact = contactEmail.split("@");
		String contactEmailID = EmailIDContact[0] + "+" + randomInt + "@gmail.com";
		return contactEmailID;
	}
	
	/**
	 * @author Parul Singh
	 * @param contactFirstName
	 * @param contactLastName
	 * @param emailId TODO
	 * @return true/false
	 */
	public boolean clickOnCreatedContact(String contactFirstName,String contactLastName, String emailId){
		int i =1;
		String xpath="";
		if (Mode.Lightning.toString().equalsIgnoreCase(mode)) {
			return clickOnCreatedContact(environment, mode, contactFirstName, contactLastName, emailId);
		} else {

		}
			if (getSelectedOptionOfDropDown(driver, getViewDropdown(60), "View dropdown", "text").equalsIgnoreCase("All Contacts")) {
				if (click(driver, getGoButton(60), "Go button", action.BOOLEAN)) {
				}
				else {
					appLog.error("Go button not found");
					return false;
				}
			}
			else{
				if (selectVisibleTextFromDropDown(driver, getViewDropdown(60),"View dropdown","All Contacts") ){
				}
				else {
					appLog.error("All Contacts  not found in dropdown");
					return false;
				}

			}
			if(emailId!=null) {
				xpath="//div[@class='x-panel-bwrap']//td//span[text()='"+emailId+"']/../../../preceding-sibling::td//span[contains(text(),'"
						+ contactLastName + ", " + contactFirstName + "')]";
			}else {
				xpath="//div[@class='x-panel-bwrap']//a//span[contains(text(),'"
						+ contactLastName + ", " + contactFirstName + "')]";
			}
			
			WebElement contactName = FindElement(driver,xpath, "Contact Name", action.BOOLEAN, 5);
				if (contactName != null) {
					if (click(driver, contactName, "Contact Name", action.SCROLLANDBOOLEAN)) {
						appLog.info(
								"Clicked on created contact successfully :" + contactFirstName + " " + contactLastName);
						return true;

					} else {
						appLog.error("Not able to click on created contact");
						return false;
					}
				} else {
					while (true) {
						appLog.error("Contact is not Displaying on "+i+ " Page: " + contactLastName + ", " + contactFirstName);
						if (click(driver, getNextImageonPage(10), "Contact Page Next Button",
								action.SCROLLANDBOOLEAN)) {

							appLog.info("Clicked on Next Button");
							if(emailId!=null) {
								xpath="//div[@class='x-panel-bwrap']//td//span[text()='"+emailId+"']/../../../preceding-sibling::td//span[contains(text(),'"
										+ contactLastName + ", " + contactFirstName + "')]";
							}else {
								xpath="//div[@class='x-panel-bwrap']//a//span[contains(text(),'"
										+ contactLastName + ", " + contactFirstName + "')]";
							}
							contactName = FindElement(driver,xpath, "Contact Name", action.BOOLEAN, 5);
							if (contactName != null) {
								if (click(driver, contactName, contactLastName + ", " + contactFirstName, action.SCROLLANDBOOLEAN)) {
									appLog.info("Clicked on Contact name : " + contactLastName + ", " + contactFirstName);
									return true;
									
								}
							}
						} else {
							appLog.error("Contact Not Available : " + contactLastName + ", " + contactFirstName);
							return false;
						}
						i++;
					}
				}
			}

	//Lightning Method...
	
	public boolean clickOnCreatedContact(String environment, String mode,String contactFirstName,String contactLastName,String emailId){
		int i =1;
		if(mode.equalsIgnoreCase(Mode.Classic.toString())){
			String xpath="";
			if (getSelectedOptionOfDropDown(driver, getViewDropdown(60), "View dropdown", "text").equalsIgnoreCase("All Contacts")) {
				if (click(driver, getGoButton(60), "Go button", action.BOOLEAN)) {
				}
				else {
					appLog.error("Go button not found");
					return false;
				}
			}
			else{
				if (selectVisibleTextFromDropDown(driver, getViewDropdown(60),"View dropdown","All Contacts") ){
				}
				else {
					appLog.error("All Contacts  not found in dropdown");
					return false;
				}

			}
			if(emailId!=null) {
				xpath="//div[@class='x-panel-bwrap']//td//span[text()='"+emailId+"']/../../../preceding-sibling::td//span[contains(text(),'"
						+ contactLastName + ", " + contactFirstName + "')]";
			}else {
				xpath="//div[@class='x-panel-bwrap']//a//span[contains(text(),'"
						+ contactLastName + ", " + contactFirstName + "')]";
			}

			WebElement contactName = FindElement(driver,xpath, "Contact Name", action.BOOLEAN, 5);
			if (contactName != null) {
				if (click(driver, contactName, "Contact Name", action.SCROLLANDBOOLEAN)) {
					appLog.info(
							"Clicked on created contact successfully :" + contactFirstName + " " + contactLastName);
					return true;

				} else {
					appLog.error("Not able to click on created contact");
					return false;
				}
			} else {
				while (true) {
					appLog.error("Contact is not Displaying on "+i+ " Page: " + contactLastName + ", " + contactFirstName);
					if (click(driver, getNextImageonPage(10), "Contact Page Next Button",
							action.SCROLLANDBOOLEAN)) {

						appLog.info("Clicked on Next Button");
						if(emailId!=null) {
							xpath="//div[@class='x-panel-bwrap']//td//span[text()='"+emailId+"']/../../../preceding-sibling::td//span[contains(text(),'"
									+ contactLastName + ", " + contactFirstName + "')]";
						}else {
							xpath="//div[@class='x-panel-bwrap']//a//span[contains(text(),'"
									+ contactLastName + ", " + contactFirstName + "')]";
						}
						contactName = FindElement(driver,xpath, "Contact Name", action.BOOLEAN, 5);
						if (contactName != null) {
							if (click(driver, contactName, contactLastName + ", " + contactFirstName, action.SCROLLANDBOOLEAN)) {
								appLog.info("Clicked on Contact name : " + contactLastName + ", " + contactFirstName);
								return true;

							}
						}
					} else {
						appLog.error("Contact Not Available : " + contactLastName + ", " + contactFirstName);
						return false;
					}
					i++;
				}
			}
		}else{
			String concatFullName;
			if(contactFirstName==null){
				concatFullName=contactLastName;
			} else {
				concatFullName=contactFirstName+" "+contactLastName;
			}
			if(clickOnAlreadyCreated_Lighting(environment, mode, TabName.ContactTab, concatFullName, 30)){
				appLog.info("Clicked on Contact name : " + concatFullName);
				return true;
			}else{
				appLog.error("Contact Not Available : " + concatFullName);	
			}
		}
		return false;
	}
	
	
	/**
	 * @author Parul Singh
	 * @param ContactName
	 * @return true/false
	 */
	public boolean verifyDeletedContact(String ContactName) {
		if (getSelectedOptionOfDropDown(driver, getViewDropdown(60), "View dropdown", "text")
				.equalsIgnoreCase("All Contacts")) {
			if (click(driver, getGoButton(60), "Go button", action.BOOLEAN)) {
			} else {
				appLog.error("Go button not found");
			}
		} else {
			if (selectVisibleTextFromDropDown(driver, getViewDropdown(60), "View dropdown", "All Contacts")) {
			} else {
				appLog.error("All institutions not found in dropdown");
			}
		}
		for (int i = 0; i < getAllContactsList().size(); i++) {
			if (getAllContactsList().contains(ContactName)) {
				appLog.info("Contact Is Displaying");
				return false;
			} else {
				appLog.info("Contact is not Displaying");

			}
		}
		return true;
	}
	
	/**
	 * @author Parul Singh
	 * @param workspace
	 * @param pageNAme
	 * @param contactVariableName
	 * @return true/false	
	 */
	public boolean alertRecordCountAtContactPage(Workspace workspace,PageName pageNAme,String contactVariableName){
		int recWithValue=0;
		String [] recvalue=null;
		FundsPageBusinessLayer fp=new FundsPageBusinessLayer(driver);
		if(click(driver,getAlertHistoryLink(workspace, pageNAme, 60), "Alert History Link", action.SCROLLANDBOOLEAN)){
		if(fp.getAlertHistoryPopupRecordLabelWithValue(workspace, 60)!=null){
				recvalue =fp.getAlertHistoryPopupRecordLabelWithValue(workspace,10).getText().trim().split(":");
				recWithValue=Integer.parseInt(recvalue[1].trim());	
				if(ExcelUtils.writeData(recWithValue, "FilePath",excelLabel.ContactName, contactVariableName, excelLabel.ContactPageALertCount)){
					return true;
				}else {
					appLog.error("Not able to write Contact Alert Count in Filepath excel sheet");
				}
		}else {
			appLog.error("Fund Alert Count is  not visible so cannot get Contact page alert count");
		}
		}else{
			appLog.info("Not able to click on alert history link"); 			
		}
		return false;
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param contactFirstName
	 * @param contactLastName
	 * @param updatedEmailId
	 * @return true/false
	 */
	public boolean updateEmailAddressOfCreatedContact(String contactFirstName, String contactLastName,String updatedEmailId) {
		if(clickOnTab(TabName.ContactTab)) {
			if(clickOnCreatedContact(contactFirstName, contactLastName, null)) {
				if(click(driver, getEditButton1(60), "Contact Edit Button", action.SCROLLANDBOOLEAN)) {
					if(sendKeys(driver, getEmailId(30), updatedEmailId,"Contact Email Id Text box", action.SCROLLANDBOOLEAN)) {
						if(click(driver, getSaveButton(environment, mode, 60), "Contact Save Button", action.SCROLLANDBOOLEAN)) {
							appLog.info("Created Contact Email Id is updated Successfully. ");
						}else {
							appLog.error("Not able to save Created Contact.");
							return false;
						}
					}else {
						appLog.error("Not able to pass value in Email Id Text Box");
						return false;
					}
				}else {
					appLog.error("Not able to click on Edit Button");
					return false;
				}
			}else {
				appLog.error("Not able to click on Created Contact");
				return false;
			}
		}else {
			appLog.error("Not able to click on Contact Tab");
			return false;
		}
		return true;
		
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param contactFirstName
	 * @param contactLastName
	 * @return true/false
	 */
	public boolean deleteCreatedContact(String contactFirstName, String contactLastName) {
		if(clickOnTab(TabName.ContactTab)) {
			if(clickOnCreatedContact(contactFirstName, contactLastName, null)) {
				appLog.info("Clicked on Created Contact: "+contactFirstName+" "+contactLastName);
				if(clickUsingJavaScript(driver, getDeleteButton(60), "Delete Button")) {
					ThreadSleep(3000);
					appLog.info("Contact deleted Successfully: "+contactFirstName+" "+contactLastName);
				}else {
					appLog.error("Not able to click on Contact Delete Button");
					return false;
				}
				
			}else {
				appLog.error("Not able to click on Created Contact: "+contactFirstName+" "+contactLastName);
				return false;
			}
			
		}else {
			appLog.error("Not able to click on Contact Tab");
			return false;
		}
		return true;
		
	}
	
	/**
	 * @author Ankit Jaiswal
	 * @param fundName
	 * @param workspace
	 * @return true/false
	 */
	public boolean removeContactAccessFromContactPage(String fundName, Workspace workspace) {
		WebElement ele=null;
		scrollDownThroughWebelement(driver,getWorkspaceSectionView(workspace, 10),Workspace.FundraisingWorkspace.toString() + " View.");
		if(clickUsingJavaScript(driver,getRemoveContactAccessButton(workspace, 60), "Remove contact access button", action.BOOLEAN)){
			ele=FindElement(driver,"//a[@title='Remove']", fundName+" Remove link", action.BOOLEAN, 20);
			if(ele!=null) {
				if(clickUsingJavaScript(driver, ele, "Remove Link", action.BOOLEAN)){
					String ParentID=switchOnWindow(driver);
					if(ParentID!=null){
						for(int i=0 ;i<=600; i++) {
							if (isAlertPresent(driver)) {
								switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
								appLog.info(fundName+" is successfully removed");
								driver.switchTo().window(ParentID);
								return true;
							} else {
								ThreadSleep(100);
							}
						}
						appLog.error("alert message is not displayed");
						driver.switchTo().window(ParentID);
					}else{
						appLog.info("No new window to switch");
					}
				}else{
					appLog.info("Not able ot click on fund remove link: "+fundName);
				}

			}else {
				appLog.error(fundName+" remove icon is not visible in remove contact access pop up so cannot remove contact access");
			}
		}else{
			appLog.error("Not able to clcik on remove contact access button");
		}

		return false;
	}
	
	
	public SoftAssert verifyResetPasswordPopupUI(String contactFirstName,String ContactLastNAme,String ContactEMail){
		SoftAssert sa=new SoftAssert();
			if(getResetPasswordpopupHeader(60)!=null){
				if(getResetPasswordpopupHeader(60).getText().equalsIgnoreCase("Reset Password")){
					appLog.info("Reset Password header text is displaying in the header");
				}else{
					appLog.error("Reset Passowrd header text is not displaying in the header");
					sa.assertTrue(false, "Reset Passowrd header text is not displaying in the header");
				}
			System.out.println(getResetPasswordpopupHeaderMessage(60).getText());
			
				if(getResetPasswordpopupHeaderMessage(60).getText().contains(ContactPageErrorMessage.resetPasswordPopupMessage)){
					appLog.info("Reset Password popup message is verified");
				}else{
					appLog.error("Reset Password popup message is not verified");
					sa.assertTrue(false, "Reset Password popup message is not verified");
				}
				
				System.out.println(getResetPasswordPopupMessageContactName(60).getText());
				if(getResetPasswordPopupMessageContactName(60).getText().contains(contactFirstName+" "+ContactLastNAme)){
					appLog.info("Reset Password popup message is verified");
				}else{
					appLog.error("Reset Password popup message is not verified");
					sa.assertTrue(false, "Reset Password popup message is not verified");
				}
				if(getResetPasswordPopupsendMailRadioButton(60)!=null){
					appLog.info("send email radio button is displaying");
					if(isSelected(driver, getResetPasswordPopupsendMailRadioButton(60), "Send Email radio button")){
						appLog.info("send email radio button is selected");
					}else{
						appLog.error("Send email radio button is not selected");
						sa.assertTrue(false, "Send email radio button is not selected");
					}
					
					System.out.println(getResetPasswordPopupSendMailText1(60).getText());
					if(getResetPasswordPopupSendMailText1(60).getText().contains(ContactPageErrorMessage.resetPasswordsendLinkMessage)){
						appLog.info("Reset Password send link message is verified");
					}else{
						appLog.error("Reset Password send link message is not verified");
						sa.assertTrue(false, "Reset Password send link message is not verified");
					}
					
					System.out.println(getResetPasswordPopupSendMailText2(60).getText());
					if(getResetPasswordPopupSendMailText2(60).getText().equalsIgnoreCase(ContactEMail)){
						appLog.info("Reset Password send link email  is verified");
					}else{
						appLog.error("Reset Password send link email is not verified");
						sa.assertTrue(false, "Reset Password send link email is not verified");
					}							
				}else{
					appLog.error("send email radio button is not displaying");
					sa.assertTrue(false, "Send email radio button is not displaying");
				}
				if(getResetPasswordPopupcopyLinkRadioButton(60)!=null){
					appLog.info("copy link radio button is displaying");
					if(isSelected(driver, getResetPasswordPopupcopyLinkRadioButton(60), "copy link radio button")){
						appLog.error("copy link radio button is selected");
						sa.assertTrue(false, "copy link radio button is not selected");
					}else{
						appLog.info("copy link radio button is not selected");	
					}
					if(getResetPasswordCopyLinkText(60).getText().contains(ContactPageErrorMessage.resetPasswordCopyLinkMessage)){
						appLog.info("Copy link text is verified");
					}else{
						appLog.error("Copy link text is not verified");
						sa.assertTrue(false, "Copy link text is not verified");
					}								
				}else{
					appLog.error("Copy link radio button is not displaying");
					sa.assertTrue(false, "Copy link radio button is not displaying");
				}
				if(getResetPasswordPopupSendEMailButton(60)!=null){
					appLog.info("Send email button is displaying");
				}else{
					appLog.error("Send email button is not displaying");
					sa.assertTrue(false, "snd email button is not displaying");
				}
				if(getResetPasswordPopupCancelButton(60)!=null){
					appLog.info("Cancel button is displaying");
				}else{
					appLog.error("Cancel button is not displaying");
					sa.assertTrue(false, "Cancel button is not displaying");
				}
				if(getResetPasswordPopupCloseIcon(60)!=null){
					appLog.info("Close icon is displaying");
				}else{
					appLog.error("Close icon is not displaying");
					sa.assertTrue(false, "Close icon is not displaying");
				}
				if(click(driver, getResetPasswordPopupcopyLinkRadioButton(60), "Copy link radio button", action.SCROLLANDBOOLEAN)){
					if(getResetPasswordPopupCopyEmailButton(60)!=null){
						appLog.info("Copy email button is displaying");
					}else{
						appLog.error("Copy email button is not displaying");
						sa.assertTrue(false, "Copy email button is not displaying");
					}					
				}else{
					appLog.error("Copy link radio button is not clicked");
					sa.assertTrue(false, "Copy link radio button is not clicked");
				}
				if(click(driver, getResetPasswordPopupCancelButton(60), "Cancel button", action.SCROLLANDBOOLEAN)){
					appLog.info("Clicked on cancel button");
					ThreadSleep(2000);
					if(getResetPasswordpopupHeader(10)==null){
					appLog.info("Reset Password poup get closed successfully");
					}else{
						appLog.error("Reset Password popup does not get closed successfully");
						sa.assertTrue(false, "Reset Password popup does not get closed successfully");
					}
				}else{
					appLog.error("Not able to click on cancel button");
					sa.assertTrue(false, "Not bale ot click on cancel button");
				}
			}else{
				appLog.error("ResetPAssowrd poup is not displaying");
				sa.assertTrue(false, "ResetPAssowrd poup is not displaying");
			}			
		return sa;
	}
	
	public String getTextOfPropertyResetpasswordCopyLink() {
		((JavascriptExecutor) driver).executeScript("document.getElementById('flashMsg').setAttribute('style', 'z-index: 999999; display: block; position: fixed; top: 240px; left: 501px; opacity: 0;');");
		return getText(driver, getResetPasswordPopupCopyEmailText(30), "copy email text", action.BOOLEAN);
	}
	
}

	
	
	

