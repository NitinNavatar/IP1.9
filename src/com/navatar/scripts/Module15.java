/**
 * 
 */
package com.navatar.scripts;


import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.navatar.generic.BaseLib;
import com.navatar.generic.CommonLib;
import com.navatar.generic.EmailLib;
import com.navatar.generic.CommonLib.AllOr1By1;
import com.navatar.generic.CommonLib.ContentGridArrowKeyFunctions;
import com.navatar.generic.CommonLib.EnableDisable;
import com.navatar.generic.CommonLib.FolderType;
import com.navatar.generic.CommonLib.ManageApprovalTabs;
import com.navatar.generic.CommonLib.OnlineImportFileAddTo;
import com.navatar.generic.CommonLib.PageName;
import com.navatar.generic.CommonLib.SelectDeselect;
import com.navatar.generic.CommonLib.TabName;
import com.navatar.generic.CommonLib.UploadFileActions;
import com.navatar.generic.CommonLib.Workspace;
import com.navatar.generic.CommonLib.accessType;
import com.navatar.generic.CommonLib.action;
import com.navatar.generic.CommonLib.excelLabel;
import com.navatar.generic.CommonLib.multiInstance;
import com.navatar.generic.ExcelUtils;
import com.navatar.generic.SoftAssert;
import com.navatar.pageObjects.BasePageBusinessLayer;
import com.navatar.pageObjects.BoxPageBusinesslayer;
import com.navatar.pageObjects.ContactPageBusinessLayer;
import com.navatar.pageObjects.FundsPageBusinessLayer;
import com.navatar.pageObjects.FundsPageErrorMessage;
import com.navatar.pageObjects.InvestorFirmPageBusinesslayer;
import com.navatar.pageObjects.LoginPageBusinessLayer;
import com.navatar.pageObjects.NIMPageBusinessLayer;
import com.navatar.pageObjects.NIMPageErrorMessage;
import static com.navatar.generic.CommonVariables.*;
import static com.navatar.generic.CommonLib.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Ankur Rana
 *
 */
 public class Module15 extends BaseLib{
	
	 static String excelPath=System.getProperty("user.dir") + "/Bulk.xlsx";
	

	@Parameters({ "environment", "mode" }) @Test
	public void M15tc001_RunPreconditionScenario(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim=new NIMPageBusinessLayer(driver);
		SoftAssert saa = new SoftAssert();
		if(lp.preConditionWithoutClickingRegisterrationPopupClose(superAdminUserName, CRMUser1FirstName+" "+CRMUser1LastName, CRMUser1EmailID, EnableDisable.Disable, EnableDisable.Disable, accessType.InternalUserAccess)){
			appLog.info("Provided internal user access to crm user "+CRMUser1FirstName+" "+CRMUser1LastName+".");
		} else {		
			appLog.error("Not able to provide internal user access to crm user "+CRMUser1FirstName+" "+CRMUser1LastName+".");
			saa.assertTrue(false,"Not able to provide internal user access to crm user "+CRMUser1FirstName+" "+CRMUser1LastName+".");
		}
		driver.close();
		config(browserToLaunch);
		lp=new LoginPageBusinessLayer(driver);
		nim=new NIMPageBusinessLayer(driver);
		saa=new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		 if(nim.clickOnTab(TabName.NIMTab)){
			 switchToFrame(driver, 30, nim.getNIMTabFrame(30));
			 if(click(driver, nim.getRegistrationSuccessfulCloseBtn(60), "Registration successful popup close button", action.SCROLLANDBOOLEAN)){
				 appLog.info("clicked on registration successful popup close button");
			 }else{
				 appLog.info("Not able to click on close button");
				 sa.assertTrue(false, "Not able to click on registration successful popup close button");
			 }		 
		 }else{
			 appLog.info("Not able to click on Nim Tab");
			 sa.assertTrue(false, "Not able to click on Nim Tab");			 
		 }
		 switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		saa = new SoftAssert();
		if(lp.preConditionWithoutClickingRegisterrationPopupClose(superAdminUserName, CRMUser2FirstName+" "+CRMUser2LastName, CRMUser2EmailID, EnableDisable.Disable, EnableDisable.Disable, accessType.InternalUserAccess)){
			appLog.info("Provided internal user access to crm user "+CRMUser2FirstName+" "+CRMUser2LastName+".");
		} else {
			appLog.error("Not able to provide internal user access to crm user "+CRMUser2FirstName+" "+CRMUser2LastName+".");
			saa.assertTrue(false,"Not able to provide internal user access to crm user "+CRMUser2FirstName+" "+CRMUser2LastName+".");
		}
		driver.close();
		config(browserToLaunch);
		lp=new LoginPageBusinessLayer(driver);
		nim=new NIMPageBusinessLayer(driver);
		saa=new SoftAssert();
		lp.CRMLogin(CRMUser2EmailID, adminPassword);
		 if(nim.clickOnTab(TabName.NIMTab)){
			 switchToFrame(driver, 30, nim.getNIMTabFrame(30));
			 if(click(driver, nim.getRegistrationSuccessfulCloseBtn(60), "Registration successful popup close button", action.SCROLLANDBOOLEAN)){
				 appLog.info("clicked on registration successful popup close button");
			 }else{
				 appLog.info("Not able to click on close button");
				 sa.assertTrue(false, "Not able to click on registration successful popup close button");
			 }		 
		 }else{
			 appLog.info("Not able to click on Nim Tab");
			 sa.assertTrue(false, "Not able to click on Nim Tab");			 
		 }
		 switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		 saa = new SoftAssert();
		if(lp.preConditionWithoutClickingRegisterrationPopupClose(superAdminUserName, CRMUser3FirstName+" "+CRMUser3LastName, CRMUser3EmailID, EnableDisable.Disable, EnableDisable.Disable, accessType.InternalUserAccess)){
			appLog.info("Provided internal user access to crm user "+CRMUser3FirstName+" "+CRMUser3LastName+".");
		} else {
			appLog.error("Not able to provide internal user access to crm user "+CRMUser3FirstName+" "+CRMUser3LastName+".");
			saa.assertTrue(false,"Not able to provide internal user access to crm user "+CRMUser3FirstName+" "+CRMUser3LastName+".");
		}
		driver.close();
		config(browserToLaunch);
		lp=new LoginPageBusinessLayer(driver);
		nim=new NIMPageBusinessLayer(driver);
		saa=new SoftAssert();
		lp.CRMLogin(CRMUser3EmailID, adminPassword);
		 if(nim.clickOnTab(TabName.NIMTab)){
			 switchToFrame(driver, 30, nim.getNIMTabFrame(30));
			 if(click(driver, nim.getRegistrationSuccessfulCloseBtn(60), "Registration successful popup close button", action.SCROLLANDBOOLEAN)){
				 appLog.info("clicked on registration successful popup close button");
			 }else{
				 appLog.info("Not able to click on close button");
				 sa.assertTrue(false, "Not able to click on registration successful popup close button");
			 }		 
		 }else{
			 appLog.info("Not able to click on Nim Tab");
			 sa.assertTrue(false, "Not able to click on Nim Tab");			 
		 }
		 switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		 saa = new SoftAssert();
		if(lp.preConditionWithoutClickingRegisterrationPopupClose(superAdminUserName, CRMUser2FirstName+" "+CRMUser2LastName, CRMUser2EmailID, EnableDisable.Disable, EnableDisable.Disable, accessType.AdminUserAccess)){
			appLog.info("Provided admin user access to crm user "+CRMUser2FirstName+" "+CRMUser2LastName+".");
		} else {
			appLog.error("Not able to provide admin user access to crm user "+CRMUser2FirstName+" "+CRMUser2LastName+".");
			saa.assertTrue(false,"Not able to provide admin user access to crm user "+CRMUser2FirstName+" "+CRMUser2LastName+".");
		}
		saa.assertAll();		
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc002_CreateFolderTemplate(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim=new NIMPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		SoftAssert saa=new SoftAssert();				
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(bp.clickOnTab(TabName.NIMTab)){
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			if(click(driver, nim.getAddFolderTemplateButton(60), "Add folder template butto", action.SCROLLANDBOOLEAN)){
			if(sendKeys(driver,nim.getFolderTemplateNameTextBox(60), M15BulkFolderTemplateName1, "Folder template name", action.SCROLLANDBOOLEAN)){		
				if(bp.createFolderStructureFromExcelBulk(excelPath,"Bulk Folder Template 1",Workspace.FundraisingWorkspace, PageName.NavatarInvestorManager, 60)){
				if(click(driver, nim.getFolderTemplateSaveButton(60), "Save button", action.SCROLLANDBOOLEAN)){
					appLog.error("click on save button");
					if (click(driver, nim.getSaveYesBtnAddFolderTemplate(60), "Confirm Save Yes Button", action.BOOLEAN)) {
						if (click(driver,nim.getGoBackLink(60), "Go back link", action.SCROLLANDBOOLEAN)) {
							if (FindElement(driver, "//a[@title='"+M15BulkFolderTemplateName1+"']", "Template Name",
									action.BOOLEAN, 60) != null) {
								appLog.info("Bulk Folder Template 1 Template has been created successfully.");							
							}else{
								appLog.error("Bulk Folder Template 1 Template has not been created successfully.");
								saa.assertTrue(false, "Bulk Folder Template 1 Template has not been created successfully.");
							}
						}else{
							appLog.error("Not able to click on go back link");
							saa.assertTrue(false, "Not able to click on go back link");
						}
					}else{
						appLog.error("Not able ot click on yes button on save popup");
						saa.assertTrue(false, "Not able ot click on yes button on save popup");
					}
				}else{
					appLog.error("Not able ot click on save button");
					saa.assertTrue(false, "not able to click on save button");
				}				
			}else{
				appLog.error("Not able to create folder template");
				saa.assertTrue(false, "Not able to create folder template");
			}
			}else{
				appLog.error("Not able to enter folder template name");
				saa.assertTrue(false, "Not able to enter folder template name");
			}
			}else{
				appLog.error("Not able to click on add folder template button");
				saa.assertTrue(false, "Not able to click on add folder template button");
			}			
			ThreadSleep(2000);
			if(click(driver, nim.getAddFolderTemplateButton(60), "Add folder template butto", action.SCROLLANDBOOLEAN)){
				if(sendKeys(driver,nim.getFolderTemplateNameTextBox(60), M15BulkFolderTemplateName2, "Folder template name", action.SCROLLANDBOOLEAN)){		
				if(bp.createFolderStructureFromExcelBulk(excelPath,"Bulk Folder Template 2",Workspace.FundraisingWorkspace, PageName.NavatarInvestorManager, 60)){
					if(click(driver, nim.getFolderTemplateSaveButton(60), "Save button", action.SCROLLANDBOOLEAN)){
						appLog.info("click on save button");
						if (click(driver, nim.getSaveYesBtnAddFolderTemplate(60), "Confirm Save Yes Button", action.BOOLEAN)) {
							if (click(driver,nim.getGoBackLink(60), "Go back link", action.SCROLLANDBOOLEAN)) {
								if (FindElement(driver, "//a[@title='"+M15BulkFolderTemplateName2+"']", "Template Name",
										action.BOOLEAN, 60) != null) {
									appLog.info("Bulk Folder Template 2 Template has been created successfully.");							
								}else{
									appLog.error("Bulk Folder Template 2 Template has not been created successfully.");
									saa.assertTrue(false, "Bulk Folder Template 2 Template has not been created successfully.");
								}
							}else{
								appLog.error("Not able to click on go back link");
								saa.assertTrue(false, "Not able to click on go back link");
							}
						}else{
							appLog.error("Not able ot click on yes button on save popup");
							saa.assertTrue(false, "Not able ot click on yes button on save popup");
						}
					}else{
						appLog.error("Not able ot click on save button");
						saa.assertTrue(false, "not able to click on save button");
					}				
				}else{
					appLog.error("Not able to create folder template");
					saa.assertTrue(false, "Not able to create folder template");
				}
				}else{
					appLog.error("Not able to enter folder template name");
					saa.assertTrue(false, "Not able to enter folder template name");
				}				
				}else{
					appLog.error("Not able to click on add folder template button");
					saa.assertTrue(false, "Not able to click on add folder template button");
				}			
		}else{
			appLog.error("Not able to click on nim tab");
			saa.assertTrue(false, "Not able to click on nim tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();		
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc003_VerifyErrorMessageForMaximumNumberOfFolders(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		NIMPageBusinessLayer nim=new NIMPageBusinessLayer(driver);
		FundsPageBusinessLayer fp=new FundsPageBusinessLayer(driver);
		SoftAssert saa=new SoftAssert();
		String StandardFolderName=ExcelUtils.readData(excelPath,"FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(bp.clickOnTab(TabName.NIMTab)){
			switchToFrame(driver, 60, nim.getNIMTabFrame(60));
			if(selectVisibleTextFromDropDown(driver,nim.getDropdownTempType(60), "Dropdown type	", "All Templates")){
			WebElement ele=	FindElement(driver, "//a[@title='"+M15BulkFolderTemplateName1+"']", "Template Name",
					action.BOOLEAN, 60) ;
			if(click(driver, ele, "Bulk folder template name ", action.SCROLLANDBOOLEAN)){
				if(click(driver, nim.getEditIcon(60), "Edit icon", action.SCROLLANDBOOLEAN)){				
					String id=null;
					id=fp.getCreatedFolderId(StandardFolderName, PageName.NavatarInvestorManager, 20);
					if(nim.clickOnAddFolderButton(id)){
				if(nim.createChildFolder(StandardFolderName, null, PageName.NavatarInvestorManager, 60)){
					if (bp.verifyErrorMessageOnPage(NIMPageErrorMessage.BulkAddFolderErrorMessage,nim.getBulkAddFolderErrorMessage(60),
							"Error Message after adding 100 folders")) {
						appLog.info("Error Message is verified");
					} else {
						saa.assertTrue(false,
								"Error Message is not verified.Expected:"
										+ NIMPageErrorMessage.BulkAddFolderErrorMessage + " Actual"
										+ getText(driver, nim.getBulkAddFolderErrorMessage(60),
												"Error Message after adding 100 folders", action.BOOLEAN));
					}
					if(click(driver, nim.getBulkAddFolderErrorPopupCloseButton(60), "Close Button", action.SCROLLANDBOOLEAN)){
						appLog.error("Not able ot click on close button");
						if(click(driver, nim.getFolderTemplateSaveButton(60), "Save button", action.SCROLLANDBOOLEAN)){
							appLog.info("click on save button");
							if (click(driver, nim.getSaveYesBtnAddFolderTemplate(60), "Confirm Save Yes Button", action.BOOLEAN)) {
								if (click(driver,nim.getGoBackLink(60), "Go back link", action.SCROLLANDBOOLEAN)) {
									appLog.info("click on go back link");
								}else{
									appLog.error("Not able to click on go back link");
									saa.assertTrue(false, "Not able to click on go back link");
								}
							}else{
								appLog.error("Not able ot click on yes button on save popup");
								saa.assertTrue(false, "Not able ot click on yes button on save popup");
							}
						}else{
							appLog.error("Not able ot click on save button");
							saa.assertTrue(false, "not able to click on save button");
						}
					}else{
						appLog.error("Not able ot click on close button");
						saa.assertTrue(false, "Not able to click on close button");
					}			
				}else{
					appLog.error("Not able to cretae child folder");
					saa.assertTrue(false, "Not able to click on add a child folder");
				}
					}else{
						appLog.error("Not able to click on add a folder button");
						saa.assertTrue(false, "Not able to click on add a folder button");						
					}
				}else{
					appLog.error("Not able to click on edit icon");
					saa.assertTrue(false, "Not able to click on eidt icon");
				}
			}else{
				appLog.error("Not able to click on bulk folder template");
				saa.assertTrue(false,"Not able to click on bulk folde rtemplate ");
			}	
			}else{
				appLog.error("Not ab;le ot select value fom the display dropdown");
				saa.assertTrue(false, "Not ab;le ot select value fom the display dropdown");
			}		
		if(selectVisibleTextFromDropDown(driver,nim.getDropdownTempType(60), "Dropdown type	", "All Templates")){
			WebElement ele=	FindElement(driver, "//a[@title='"+M15BulkFolderTemplateName2+"']", "Template Name",
					action.BOOLEAN, 60) ;
			if(click(driver, ele, "Bulk folder template name ", action.SCROLLANDBOOLEAN)){
				if(click(driver, nim.getEditIcon(60), "Edit icon", action.SCROLLANDBOOLEAN)){				
					String id=null;
					id=fp.getCreatedFolderId(StandardFolderName, PageName.NavatarInvestorManager, 20);
					if(nim.clickOnAddFolderButton(id)){
				if(nim.createChildFolder(StandardFolderName, null, PageName.NavatarInvestorManager, 60)){
					if (bp.verifyErrorMessageOnPage(NIMPageErrorMessage.BulkAddFolderErrorMessage,nim.getBulkAddFolderErrorMessage(60),
							"Error Message after adding 100 folders")) {
						appLog.info("Error Message is verified");
					} else {
						saa.assertTrue(false,
								"Error Message is not verified.Expected:"
										+ NIMPageErrorMessage.BulkAddFolderErrorMessage + " Actual"
										+ getText(driver, nim.getBulkAddFolderErrorMessage(60),
												"Error Message after adding 100 folders", action.BOOLEAN));
					}
					if(click(driver, nim.getBulkAddFolderErrorPopupCloseButton(60), "Close Button", action.SCROLLANDBOOLEAN)){
						appLog.error("clicked on close button");
						if(click(driver, nim.getFolderTemplateSaveButton(60), "Save button", action.SCROLLANDBOOLEAN)){
							appLog.info("click on save button");
							if (click(driver, nim.getSaveYesBtnAddFolderTemplate(60), "Confirm Save Yes Button", action.BOOLEAN)) {
								if (click(driver,nim.getGoBackLink(60), "Go back link", action.SCROLLANDBOOLEAN)) {
									appLog.info("click on go back link");
								}else{
									appLog.error("Not able to click on go back link");
									saa.assertTrue(false, "Not able to click on go back link");
								}
							}else{
								appLog.error("Not able ot click on yes button on save popup");
								saa.assertTrue(false, "Not able ot click on yes button on save popup");
							}
						}else{
							appLog.error("Not able ot click on save button");
							saa.assertTrue(false, "not able to click on save button");
						}
					}else{
						appLog.error("Not able ot click on close button");
						saa.assertTrue(false, "Not able to click on close button");
					}			
				}else{
					appLog.error("Not able to cretae child folder");
					saa.assertTrue(false, "Not able to click on add a child folder");
				}
					}else{
						appLog.error("Not able to click on add a folder button");
						saa.assertTrue(false, "Not able to click on add a folder button");						
					}
				}else{
					appLog.error("Not able to click on edit icon");
					saa.assertTrue(false, "Not able to click on eidt icon");
				}
			}else{
				appLog.error("Not able to click on bulk folder template");
				saa.assertTrue(false,"Not able to click on bulk folde rtemplate ");
			}	
			}else{
				appLog.error("Not ab;le ot select value fom the display dropdown");
				saa.assertTrue(false, "Not ab;le ot select value fom the display dropdown");
			}
		}else{
			appLog.error("Not able to click on NIM Tab");
			saa.assertTrue(false, "Not able to click on NIM Tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();	
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc004_1_BuildWorkspaceForFund1(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp=new FundsPageBusinessLayer(driver);
		String StandardFolderName=ExcelUtils.readData(excelPath,"FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		SoftAssert saa=new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(bp.clickOnTab(TabName.FundsTab)){
		if(fp.clickOnCreatedFund(M15FundName1)){
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view.");
			if(click(driver, fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 30), "Build fundraising workspace button", action.BOOLEAN)){
				String Size=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund1", excelLabel.Fund_Size);
				String vintageyear=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund1", excelLabel.Fund_VintageYear);
				String contact=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund1", excelLabel.Fund_Contact);
				String phone=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund1", excelLabel.Fund_Phone);
				String email=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund1", excelLabel.Fund_Email);
				String description=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund1", excelLabel.Fund_Description);
				String folderStructureSheetName=ExcelUtils.readData(excelPath,"Bulk Folder Template 1", 0,1);
				if(!sendKeys(driver, fp.getSizeInMillionTextBox(Workspace.FundraisingWorkspace	, 60), Size, "Size in Million text box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to size in million text box.");
				}
				if(!sendKeys(driver,fp.getVintageYear(Workspace.FundraisingWorkspace, 60), vintageyear, "vintage Year", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to Vintage Year text box.");
				}
				if(!sendKeys(driver, fp.getContactTextBox(Workspace.FundraisingWorkspace, 60), contact, "Contact text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to Contact text box.");
				}
				if(!sendKeys(driver, fp.getPhoneTextBox(Workspace.FundraisingWorkspace, 60),phone, "phone text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to phone text box.");
				}
				if(!sendKeys(driver, fp.getEmailTextBox(Workspace.FundraisingWorkspace, 60),email, "email text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to email text box.");
				}
				if(!sendKeys(driver, fp.getDescriptionTextBox(Workspace.FundraisingWorkspace, 60),description, "description text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to description text box.");
				}
				if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 60), "Next Button", action.BOOLEAN)){
					appLog.info("Click on next 1of3 button");
				}else{
					appLog.error("Not able to click on next 1of3 button");
					saa.assertTrue(false, "Not able to click on next 1of3 button");
				}
				if(!fp.importFolderTemplate(folderStructureSheetName, M15BulkFolderTemplateName1, WorkSpaceAction.IMPORTFOLDERTEMPLATE, Workspace.FundraisingWorkspace, 60)){
					appLog.error("Folder sructure is not created properly");
					BaseLib.sa.assertTrue(false,"Folder sructure is not created properly");
				} else {
					appLog.info("Folder Structure is created successfully.");
				}
				if(fp.verifyFolderStructure(fp.folderStructureInExcel(excelPath,"Bulk Folder Template 1"), 5)){
					appLog.info("Folder structure is verified.");
				} else {
					appLog.error("Folder structure is not verified.");
					sa.assertTrue(false, "Folder structure is not verified.");
				}
				String id=null;
				id=fp.getCreatedFolderId(StandardFolderName, PageName.BuildStep2Of3, 20);
				if(fp.clickOnAddFolderButton(id)){
					String text=switchToAlertAndGetMessage(driver, 20, action.GETTEXT);
					if(text.equalsIgnoreCase(FundsPageErrorMessage.bulkAddFolderErrorMessage)){
						appLog.info("Bulk add folder message is verified");
					}else{
						appLog.error("Bulk add folder message is not verified");
						saa.assertTrue(false, "Bulk add folder message is not verified");
					}
				switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
				}else{
					appLog.error("Not able to click on add folder button");
					saa.assertTrue(false, "Not able to click on add folder button");
				}
				if(click(driver, fp.getNext2Of3Button(Workspace.FundraisingWorkspace, 60), "Next button 2Of3", action.BOOLEAN)){
				appLog.info("Clicked on next 2 of 3 button");
				}else{
					appLog.error("Not able to click on next 2 of 3 button");
					saa.assertTrue(false, "Not able to click on next 2 of 3 button");
				}			
				List<String> investorList = CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
				for(int i = 0; i < investorList.size(); i++){
				if(fp.selectAccountFromBuildStep3of3(investorList.get(i), "Yes", gmailUserName,gmailPassword, "noreply@navatarinvestor.com", CRMUser1EmailID, "request update", Workspace.FundraisingWorkspace, 60)){
					appLog.info("Investor added successfully");
				}else{
					appLog.error("Investor not added successfully");
					saa.assertTrue(false, "Investor not added successfully");
				}
				}
				if (fp.setFieldValueOnManageInvestor("Account:Legal Name", 1, Workspace.FundraisingWorkspace,60)) {
					if (fp.setOperatorValueOnManageInvestor("equals", 1,Workspace.FundraisingWorkspace,60)) {
						if (fp.setCriterionValueOnManageTarget("textbox", 1,"Bulk Inst181", "Account:Legal Name",Workspace.FundraisingWorkspace,60)) {
							click(driver,fp.getManageInvestorFilterApplyButton(Workspace.FundraisingWorkspace, 60), "Apply Button", action.BOOLEAN);
						WebElement	ele = fp.getInstituionCheckBoxOn3Of3("Bulk Inst181", Workspace.FundraisingWorkspace, 60);
						if(click(driver, ele,"Institution Check Box.", action.BOOLEAN)){
							String text=switchToAlertAndGetMessage(driver, 20, action.GETTEXT);
							if(text.equalsIgnoreCase(FundsPageErrorMessage.bulkAddInvestorErrorMessage)){
								appLog.info("Bulk add investor message is verified");
							}else{
								appLog.error("Bulk add investor message is not verified");
								saa.assertTrue(false, "Bulk add investor message is not verified");
							}
						switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
						}else{
							appLog.error("Not able to click on institution checkbox");
							saa.assertTrue(false, "Not able to click on institution checkbox");
						}
						}else{
							appLog.error("Not able to set criterion value");
							saa.assertTrue(false, "Not able to set criterion value");
						}
					}else{
						appLog.error("Not able to set operator value");
						saa.assertTrue(false, "Not able ot set opeartaor value");
					}
				}else{
					appLog.error("Not able to set field value");
					saa.assertTrue(false, "Not able to set field value");
				}					
						if(click(driver, fp.getDone3Of3Button(Workspace.FundraisingWorkspace, 60), "Done Button", action.SCROLLANDBOOLEAN)){
							appLog.info("Clicked on done button");
						if(click(driver, fp.getCongratulationsCloseButton(Workspace.FundraisingWorkspace, 60), "Close button", action.SCROLLANDBOOLEAN)){
							ThreadSleep(2000);
//						if(click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60), "Manage investor icon", action.SCROLLANDBOOLEAN)){
//							investorList = CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
//							for(int i = 0; i < investorList.size(); i++){
//							if(fp.verifyAccountInManageTargetPopUp(investorList.get(i), "No", null, null, null, null, null, Workspace.FundraisingWorkspace, 60)){
//								appLog.info("Investor added successfully");
//							}else{
//								appLog.error("Investor not added successfully");
//								saa.assertTrue(false, "Investor not added successfully");
//							}
//							}
//							if(click(driver, fp.getManageInvestorPopupCrossIcon(Workspace.FundraisingWorkspace, 60), "Cross icon", action.SCROLLANDBOOLEAN)){
//								appLog.info("Clicked on cross icon");
//							}else{
//								appLog.error("Not able ot click on cross icon");
//								saa.assertTrue(false, "Not able ot click on cross icon");								
//							}							
//						}else{
//							appLog.error("Not able to click on manage invetsor");
//							saa.assertTrue(false, "Not able to clcik on manage investor");
//						}
						if(click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 60), "Manage folder", action.SCROLLANDBOOLEAN)){
							if(fp.verifyFolderStructure(fp.folderStructureInExcel(excelPath,"Bulk Folder Template 1"), 5)){
								appLog.info("Folder structure is verified.");
							} else {
								appLog.error("Folder structure is not verified.");
								sa.assertTrue(false, "Folder structure is not verified.");
							}
						if(click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 60), "Close button", action.SCROLLANDBOOLEAN)){
							appLog.info("Clicked on close button");							
						}else{
							appLog.error("Nota ble to click on close button");
							saa.assertTrue(false, "Not able ot click on close button");
						}						
						}else{
							appLog.error("Not able to click on manage folder");
							saa.assertTrue(false, "Not able to click on manage folder");
						}							
						}else{
							appLog.error("Not able to click on close button");
							saa.assertTrue(false, "Not able to click on close button");
						}
						}else{
							appLog.error("Not able to click on done button");
							saa.assertTrue(false, "Not able to click on done button");
						}					
			}else{
				appLog.error("Not able to click on build fundraising button");
				saa.assertTrue(false, "Not able to click on build fundraising button");
			}
		}else{
			appLog.error("Not able to click on created fund");
			saa.assertTrue(false, "Not able to click on created fund");
		}
		}else{
			appLog.error("Not able to click on funds tab");
			saa.assertTrue(false, "Not able to click on funds tab");			
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc004_2_verifyFolderStructure() {
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		SoftAssert saa=new SoftAssert();
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName1, Workspace.FundraisingWorkspace, 30)){
			appLog.info("Successfully downloaded report");
			
		} else {
			appLog.error("Not able to download.");
			saa.assertTrue(false, "Not able to download.");
		}
		ThreadSleep(7000);
		String s =getFilesNameFromSystemFolder("DownloadedFiles");
		String fileName=null;
		if(s != null) {
			List<String> downloadedfileList=createListOutOfString(s);
			for (int i=0; i<downloadedfileList.size(); i++) {
				if(BoxPageBusinesslayer.fileName.equalsIgnoreCase(downloadedfileList.get(i))) {
					appLog.info("Downloaded File is available in download folder : "+BoxPageBusinesslayer.fileName);
					fileName=BoxPageBusinesslayer.fileName;
					break;
				}else {
					if(i==downloadedfileList.size()-1) {
						appLog.error(BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
						sa.assertTrue(false, BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot get file from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot get file from downloaded folder");
		}
		if(s!=null && !s.isEmpty() && fileName!=null) {
			List<String> investorList = CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
			String investorListInS = CommonLib.createStringOutOfList(investorList); 
			List<String> notFoundFolders =box.verifyFolderStructureInHub(excelPath,System.getProperty("user.dir")+"\\DownloadedFiles\\"+fileName, "Bulk Folder Template 1", HubUserName, HubPassword, M15FundName1, Org1FirmName, investorListInS, CRMUser1FirstName+" "+CRMUser1LastName, Workspace.FundraisingWorkspace, 30);
			for (String string : notFoundFolders) {
//				System.err.println(string);
				saa.assertTrue(false, string+" folder structure not verified ");
			}
			
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot verifying folder structure");
			sa.assertTrue(false, "Not file is available in downloaded file folder so cannot verifying folder structure");
		}
			sa.combineAssertions(saa);
			sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc005_1_BuildWorkspaceForFund2(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp=new FundsPageBusinessLayer(driver);
		String StandardFolderName=ExcelUtils.readData(excelPath,"FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		SoftAssert saa=new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(bp.clickOnTab(TabName.FundsTab)){
		if(fp.clickOnCreatedFund(M15FundName2)){
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view.");
			if(click(driver, fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 30), "Build fundraising workspace button", action.BOOLEAN)){
				String Size=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund2", excelLabel.Fund_Size);
				String vintageyear=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund2", excelLabel.Fund_VintageYear);
				String contact=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund2", excelLabel.Fund_Contact);
				String phone=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund2", excelLabel.Fund_Phone);
				String email=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund2", excelLabel.Fund_Email);
				String description=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund2", excelLabel.Fund_Description);
				String folderStructureSheetName=ExcelUtils.readData(excelPath,"Bulk Folder Template 2", 0,1);
				if(!sendKeys(driver, fp.getSizeInMillionTextBox(Workspace.FundraisingWorkspace	, 60), Size, "Size in Million text box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to size in million text box.");
				}
				if(!sendKeys(driver,fp.getVintageYear(Workspace.FundraisingWorkspace, 60), vintageyear, "vintage Year", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to Vintage Year text box.");
				}
				if(!sendKeys(driver, fp.getContactTextBox(Workspace.FundraisingWorkspace, 60), contact, "Contact text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to Contact text box.");
				}
				if(!sendKeys(driver, fp.getPhoneTextBox(Workspace.FundraisingWorkspace, 60),phone, "phone text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to phone text box.");
				}
				if(!sendKeys(driver, fp.getEmailTextBox(Workspace.FundraisingWorkspace, 60),email, "email text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to email text box.");
				}
				if(!sendKeys(driver, fp.getDescriptionTextBox(Workspace.FundraisingWorkspace, 60),description, "description text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to description text box.");
				}
				if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 60), "Next Button", action.BOOLEAN)){
					appLog.info("Click on next 1of3 button");
				}else{
					appLog.error("Not able to click on next 1of3 button");
					saa.assertTrue(false, "Not able to click on next 1of3 button");
				}
				if(!fp.importFolderTemplate(folderStructureSheetName, M15BulkFolderTemplateName2, WorkSpaceAction.IMPORTFOLDERTEMPLATE, Workspace.FundraisingWorkspace, 60)){
					appLog.error("Folder sructure is not created properly");
					BaseLib.sa.assertTrue(false,"Folder sructure is not created properly");
				} else {
					appLog.info("Folder Structure is created successfully.");
				}
				if(fp.verifyFolderStructure(fp.folderStructureInExcel(excelPath,"Bulk Folder Template 2"), 5)){
					appLog.info("Folder strucuture is verified.");
				} else {
					appLog.error("Folder structure is not verified.");
					sa.assertTrue(false, "Folder structure is not verified.");
				}
				String id=null;
				id=fp.getCreatedFolderId(StandardFolderName, PageName.BuildStep2Of3, 20);
				if(fp.clickOnAddFolderButton(id)){
					String text=switchToAlertAndGetMessage(driver, 20, action.GETTEXT);
					if(text.equalsIgnoreCase(FundsPageErrorMessage.bulkAddFolderErrorMessage)){
						appLog.info("Bulk add folder message is verified");
					}else{
						appLog.error("Bulk add folder message is not verified");
						saa.assertTrue(false, "Bulk add folder message is not verified");
					}
				switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
				}else{
					appLog.error("Not able to click on add folder button");
					saa.assertTrue(false, "Not able to click on add folder button");
				}
				if(click(driver, fp.getNext2Of3Button(Workspace.FundraisingWorkspace, 60), "Next button 2Of3", action.BOOLEAN)){
				appLog.info("Clicked on next 2 of 3 button");
				}else{
					appLog.error("Not able to click on next 2 of 3 button");
					saa.assertTrue(false, "Not able to click on next 2 of 3 button");
				}
				List<String> investorList = CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
				for(int i = 0; i < investorList.size(); i++){
				if(fp.selectAccountFromBuildStep3of3(investorList.get(i), "Yes", gmailUserName,gmailPassword,"noreply@navatarinvestor.com",CRMUser1EmailID,"request update", Workspace.FundraisingWorkspace, 60)){
					appLog.info("Investor added successfully");
				}else{
					appLog.error("Investor not added successfully");
					saa.assertTrue(false, "Investor not added successfully");
				}
				}
				if (fp.setFieldValueOnManageInvestor("Account:Legal Name", 1, Workspace.FundraisingWorkspace,60)) {
					if (fp.setOperatorValueOnManageInvestor("equals", 1,Workspace.FundraisingWorkspace,60)) {
						if (fp.setCriterionValueOnManageTarget("textbox", 1,"Bulk Inst181", "Account:Legal Name",Workspace.FundraisingWorkspace,60)) {
							click(driver,fp.getManageInvestorFilterApplyButton(Workspace.FundraisingWorkspace, 60), "Apply Button", action.BOOLEAN);
						WebElement	ele = fp.getInstituionCheckBoxOn3Of3("Bulk Inst181", Workspace.FundraisingWorkspace, 60);
						if(click(driver, ele,"Institution Check Box.", action.BOOLEAN)){
							String text=switchToAlertAndGetMessage(driver, 20, action.GETTEXT);
							if(text.equalsIgnoreCase(FundsPageErrorMessage.bulkAddInvestorErrorMessage)){
								appLog.info("Bulk add investor message is verified");
							}else{
								appLog.error("Bulk add investor message is not verified");
								saa.assertTrue(false, "Bulk add investor message is not verified");
							}
						switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
						}else{
							appLog.error("Not able to click on institution checkbox");
							saa.assertTrue(false, "Not able to click on institution checkbox");
						}
						}else{
							appLog.error("Not able to set criterion value");
							saa.assertTrue(false, "Not able to set criterion value");
						}
					}else{
						appLog.error("Not able to set operator value");
						saa.assertTrue(false, "Not able ot set opeartaor value");
					}
				}else{
					appLog.error("Not able to set field value");
					saa.assertTrue(false, "Not able to set field value");
				}					
						if(click(driver, fp.getDone3Of3Button(Workspace.FundraisingWorkspace, 60), "Done Button", action.SCROLLANDBOOLEAN)){
							appLog.info("Clicked on done button");
						if(click(driver, fp.getCongratulationsCloseButton(Workspace.FundraisingWorkspace, 60), "Close button", action.SCROLLANDBOOLEAN)){
							ThreadSleep(2000);
//						if(click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60), "Manage investor icon", action.SCROLLANDBOOLEAN)){
//							investorList = CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
//							for(int i = 0; i < investorList.size(); i++){
//							if(fp.verifyAccountInManageTargetPopUp(investorList.get(i), "No", null, null, null, null, null, Workspace.FundraisingWorkspace, 60)){
//								appLog.info("Investor added successfully");
//							}else{
//								appLog.error("Investor not added successfully");
//								saa.assertTrue(false, "Investor not added successfully");
//							}
//							}
//							if(click(driver, fp.getManageInvestorPopupCrossIcon(Workspace.FundraisingWorkspace, 60), "Cross icon", action.SCROLLANDBOOLEAN)){
//								appLog.info("Clicked on cross icon");
//							}else{
//								appLog.error("Not able ot click on cross icon");
//								saa.assertTrue(false, "Not able ot click on cross icon");								
//							}							
//						}else{
//							appLog.error("Not able to click on manage invetsor");
//							saa.assertTrue(false, "Not able to clcik on manage investor");
//						}
						if(click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 60), "Manage folder", action.SCROLLANDBOOLEAN)){
							if(fp.verifyFolderStructure(fp.folderStructureInExcel(excelPath,"Bulk Folder Template 2"), 5)){
								appLog.info("Folder structure is verified.");
							} else {
								appLog.error("Folder structure is not verified.");
								sa.assertTrue(false, "Folder structure is not verified.");
							}
						if(click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 60), "Close button", action.SCROLLANDBOOLEAN)){
							appLog.info("Clicked on close button");							
						}else{
							appLog.error("Nota ble to click on close button");
							saa.assertTrue(false, "Not able ot click on close button");
						}						
						}else{
							appLog.error("Not able to click on manage folder");
							saa.assertTrue(false, "Not able to click on manage folder");
						}							
						}else{
							appLog.error("Not able to click on close button");
							saa.assertTrue(false, "Not able to click on close button");
						}
						}else{
							appLog.error("Not able to click on done button");
							saa.assertTrue(false, "Not able to click on done button");
						}						
			}else{
				appLog.error("Not able to click on build fundraising button");
				saa.assertTrue(false, "Not able to click on build fundraising button");
			}
		}else{
			appLog.error("Not able to click on created fund");
			saa.assertTrue(false, "Not able to click on created fund");
		}
		}else{
			appLog.error("Not able to click on funds tab");
			saa.assertTrue(false, "Not able to click on funds tab");			
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc005_2_verifyFolderStructure() {
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		SoftAssert saa=new SoftAssert();
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName2, Workspace.FundraisingWorkspace, 30)){
			appLog.info("Successfully downloaded report");
		} else {
			appLog.error("Not able to download.");
			saa.assertTrue(false, "Not able to download.");
		}
		ThreadSleep(7000);
		String s =getFilesNameFromSystemFolder("DownloadedFiles");
		String fileName=null;
		if(s != null) {
			List<String> downloadedfileList=createListOutOfString(s);
			for (int i=0; i<downloadedfileList.size(); i++) {
				if(BoxPageBusinesslayer.fileName.equalsIgnoreCase(downloadedfileList.get(i))) {
					appLog.info("Downloaded File is available in download folder : "+BoxPageBusinesslayer.fileName);
					fileName=BoxPageBusinesslayer.fileName;
					break;
				}else {
					if(i==downloadedfileList.size()-1) {
						appLog.error(BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
						sa.assertTrue(false, BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot get file from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot get file from downloaded folder");
		}
		if(s!=null && !s.isEmpty() && fileName!=null) {
			List<String> investorList = CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
			String investorListInS = CommonLib.createStringOutOfList(investorList); 
			List<String> notFoundFolders =box.verifyFolderStructureInHub(excelPath,System.getProperty("user.dir")+"\\DownloadedFiles\\"+fileName, "Bulk Folder Template 2", HubUserName, HubPassword, M15FundName2, Org1FirmName, investorListInS, CRMUser1FirstName+" "+CRMUser1LastName, Workspace.FundraisingWorkspace, 30);
			for (String string : notFoundFolders) {
//				System.err.println(string);
				saa.assertTrue(false, string+" folder structure not verified ");
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot verifying folder structure");
			sa.assertTrue(false, "Not file is available in downloaded file folder so cannot verifying folder structure");
		}
		
		
		sa.combineAssertions(saa);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc006_1_BuildWorkspaceForFund3(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp=new FundsPageBusinessLayer(driver);
		String CommonFolderName=ExcelUtils.readData(excelPath,"FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath);
		SoftAssert saa=new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(bp.clickOnTab(TabName.FundsTab)){
		if(fp.clickOnCreatedFund(M15FundName3)){
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view.");
			if(click(driver, fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 30), "Build fundraising workspace button", action.BOOLEAN)){
				String Size=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund3", excelLabel.Fund_Size);
				String vintageyear=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund3", excelLabel.Fund_VintageYear);
				String contact=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund3", excelLabel.Fund_Contact);
				String phone=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund3", excelLabel.Fund_Phone);
				String email=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund3", excelLabel.Fund_Email);
				String description=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund3", excelLabel.Fund_Description);
				if(!sendKeys(driver, fp.getSizeInMillionTextBox(Workspace.FundraisingWorkspace	, 60), Size, "Size in Million text box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to size in million text box.");
				}
				if(!sendKeys(driver,fp.getVintageYear(Workspace.FundraisingWorkspace, 60), vintageyear, "vintage Year", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to Vintage Year text box.");
				}
				if(!sendKeys(driver, fp.getContactTextBox(Workspace.FundraisingWorkspace, 60), contact, "Contact text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to Contact text box.");
				}
				if(!sendKeys(driver, fp.getPhoneTextBox(Workspace.FundraisingWorkspace, 60),phone, "phone text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to phone text box.");
				}
				if(!sendKeys(driver, fp.getEmailTextBox(Workspace.FundraisingWorkspace, 60),email, "email text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to email text box.");
				}
				if(!sendKeys(driver, fp.getDescriptionTextBox(Workspace.FundraisingWorkspace, 60),description, "description text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to description text box.");
				}
				if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 60), "Next Button", action.BOOLEAN)){
					appLog.info("Click on next 1of3 button");
				}else{
					appLog.error("Not able to click on next 1of3 button");
					saa.assertTrue(false, "Not able to click on next 1of3 button");
				}
				if(!fp.importFolderTemplateBulk(excelPath,"Bulk Folder Template 1", M15BulkFolderTemplateName1, WorkSpaceAction.CREATEFOLDERTEMPLATE, Workspace.FundraisingWorkspace, 60)){
					appLog.error("Folder sructure is not created properly");
					BaseLib.sa.assertTrue(false,"Folder sructure is not created properly");
				} else {
					appLog.info("Folder Structure is created successfully.");
				}
				if(fp.verifyFolderStructure(fp.folderStructureInExcel(excelPath,"Bulk Folder Template 1"), 5)){
					appLog.info("Folder strucuture is verified.");
				} else {
					appLog.error("Folder structure is not verified.");
					sa.assertTrue(false, "Folder structure is not verified.");
				}
				String id=null;
				id=fp.getCreatedFolderId(CommonFolderName, PageName.BuildStep2Of3, 20);
				if(fp.clickOnAddFolderButton(id)){
					String text=switchToAlertAndGetMessage(driver, 20, action.GETTEXT);
					if(text.equalsIgnoreCase(FundsPageErrorMessage.bulkAddFolderErrorMessage)){
						appLog.info("Bulk add folder message is verified");
					}else{
						appLog.error("Bulk add folder message is not verified");
						saa.assertTrue(false, "Bulk add folder message is not verified");
					}
				switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
				}else{
					appLog.error("Not able to click on add folder button");
					saa.assertTrue(false, "Not able to click on add folder button");
				}
				if(click(driver, fp.getNext2Of3Button(Workspace.FundraisingWorkspace, 60), "Next button 2Of3", action.BOOLEAN)){
				appLog.info("Clicked on next 2 of 3 button");
				}else{
					appLog.error("Not able to click on next 2 of 3 button");
					saa.assertTrue(false, "Not able to click on next 2 of 3 button");
				}
				List<String> investorList = CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
				for(int i = 0; i < 100; i++){
				if(fp.selectAccountFromBuildStep3of3(investorList.get(i), "Yes", gmailUserName, gmailPassword,"noreply@navatarinvestor.com",CRMUser1EmailID, "request update", Workspace.FundraisingWorkspace, 60)){
					appLog.info("Investor added successfully");
				}else{
					appLog.error("Investor not added successfully");
					saa.assertTrue(false, "Investor not added successfully");
				}
				}							
					if(click(driver, fp.getDone3Of3Button(Workspace.FundraisingWorkspace, 60), "Done Button", action.SCROLLANDBOOLEAN)){
							appLog.info("Clicked on done button");
						if(click(driver, fp.getCongratulationsCloseButton(Workspace.FundraisingWorkspace, 60), "Close button", action.SCROLLANDBOOLEAN)){
							ThreadSleep(2000);
						if(click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60), "Manage investor icon", action.SCROLLANDBOOLEAN)){
							investorList = CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
							for(int i = 100; i < 180; i++){
							if(fp.selectAccountFromManageTargetPopUp(investorList.get(i), "Yes",gmailUserName,gmailPassword,"noreply@navatarinvestor.com",CRMUser1EmailID,"request update", Workspace.FundraisingWorkspace, 60)){
								appLog.info("Investor added successfully");
							}else{
								appLog.error("Investor not added successfully");
								saa.assertTrue(false, "Investor not added successfully");
							}
							}	
							if (fp.setFieldValueOnManageInvestor("Account:Legal Name", 1, Workspace.FundraisingWorkspace,60)) {
								if (fp.setOperatorValueOnManageInvestor("equals", 1,Workspace.FundraisingWorkspace,60)) {
									if (fp.setCriterionValueOnManageTarget("textbox", 1,"Bulk Inst181", "Account:Legal Name",Workspace.FundraisingWorkspace,60)) {
										click(driver,fp.getManageInvestorFilterApplyButton(Workspace.FundraisingWorkspace, 60), "Apply Button", action.BOOLEAN);
									WebElement	ele = fp.getInstituionCheckBoxOn3Of3("Bulk Inst181", Workspace.FundraisingWorkspace, 60);
									if(click(driver, ele,"Institution Check Box.", action.BOOLEAN)){
										String text=switchToAlertAndGetMessage(driver, 20, action.GETTEXT);
										if(text.equalsIgnoreCase(FundsPageErrorMessage.bulkAddInvestorErrorMessage)){
											appLog.info("Bulk add investor message is verified");
										}else{
											appLog.error("Bulk add investor message is not verified");
											saa.assertTrue(false, "Bulk add investor message is not verified");
										}
									switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
									}else{
										appLog.error("Not able to click on institution checkbox");
										saa.assertTrue(false, "Not able to click on institution checkbox");
									}
									}else{
										appLog.error("Not able to set criterion value");
										saa.assertTrue(false, "Not able to set criterion value");
									}
								}else{
									appLog.error("Not able to set operator value");
									saa.assertTrue(false, "Not able ot set opeartaor value");
								}
							}else{
								appLog.error("Not able to set field value");
								saa.assertTrue(false, "Not able to set field value");
							}							
					if(click(driver, fp.getManageInvestorPopupCrossIcon(Workspace.FundraisingWorkspace, 60), "Cross icon", action.SCROLLANDBOOLEAN)){
								appLog.info("Clicked on cross icon");
							}else{
								appLog.error("Not able ot click on cross icon");
								saa.assertTrue(false, "Not able ot click on cross icon");								
							}							
						}else{
							appLog.error("Not able to click on manage invetsor");
							saa.assertTrue(false, "Not able to clcik on manage investor");
						}
						if(click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 60), "Manage folder", action.SCROLLANDBOOLEAN)){
							if(fp.verifyFolderStructure(fp.folderStructureInExcel(excelPath,"Bulk Folder Template 1"), 5)){
								appLog.info("Folder structure is verified.");
							} else {
								appLog.error("Folder structure is not verified.");
								sa.assertTrue(false, "Folder structure is not verified.");
							}
						if(click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 60), "Close button", action.SCROLLANDBOOLEAN)){
							appLog.info("Clicked on close button");							
						}else{
							appLog.error("Nota ble to click on close button");
							saa.assertTrue(false, "Not able ot click on close button");
						}						
						}else{
							appLog.error("Not able to click on manage folder");
							saa.assertTrue(false, "Not able to click on manage folder");
						}							
						}else{
							appLog.error("Not able to click on close button");
							saa.assertTrue(false, "Not able to click on close button");
						}
						}else{
							appLog.error("Not able to click on done button");
							saa.assertTrue(false, "Not able to click on done button");
						}						
			}else{
				appLog.error("Not able to click on build fundraising button");
				saa.assertTrue(false, "Not able to click on build fundraising button");
			}
		}else{
			appLog.error("Not able to click on created fund");
			saa.assertTrue(false, "Not able to click on created fund");
		}
		}else{
			appLog.error("Not able to click on funds tab");
			saa.assertTrue(false, "Not able to click on funds tab");			
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();		
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc006_2_verifyFolderStructure() {
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		SoftAssert saa=new SoftAssert();
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName3, Workspace.FundraisingWorkspace, 30)){
			appLog.info("Successfully downloaded report");
		} else {
			appLog.error("Not able to download.");
			saa.assertTrue(false, "Not able to download.");
		}
		
		ThreadSleep(7000);
		String s =getFilesNameFromSystemFolder("DownloadedFiles");
		String fileName=null;
		if(s != null) {
			List<String> downloadedfileList=createListOutOfString(s);
			for (int i=0; i<downloadedfileList.size(); i++) {
				if(BoxPageBusinesslayer.fileName.equalsIgnoreCase(downloadedfileList.get(i))) {
					appLog.info("Downloaded File is available in download folder : "+BoxPageBusinesslayer.fileName);
					fileName=BoxPageBusinesslayer.fileName;
					break;
				}else {
					if(i==downloadedfileList.size()-1) {
						appLog.error(BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
						sa.assertTrue(false, BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot get file from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot get file from downloaded folder");
		}
		
		if(s!=null && !s.isEmpty() && fileName!=null) {
			List<String> investorList = CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
			String investorListInS = CommonLib.createStringOutOfList(investorList);
			List<String> notFoundFolders =box.verifyFolderStructureInHub(excelPath,System.getProperty("user.dir")+"\\DownloadedFiles\\"+fileName, "Bulk Folder Template 1", HubUserName, HubPassword, M15FundName3, Org1FirmName, investorListInS, CRMUser1FirstName+" "+CRMUser1LastName, Workspace.FundraisingWorkspace, 30);
			for (String string : notFoundFolders) {
//				System.err.println(string);
				saa.assertTrue(false, string+" folder structure not verified ");
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot verifying folder structure");
			sa.assertTrue(false, "Not file is available in downloaded file folder so cannot verifying folder structure");
		}
		
			sa.combineAssertions(saa);
			sa.assertAll();	
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc007_1_BuildWorkspaceForFund4(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp=new FundsPageBusinessLayer(driver);
		String CommonFolderName=ExcelUtils.readData(excelPath,"FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath);
		SoftAssert saa=new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(bp.clickOnTab(TabName.FundsTab)){
		if(fp.clickOnCreatedFund(M15FundName4)){
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view.");
			if(click(driver, fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 30), "Build fundraising workspace button", action.BOOLEAN)){
				String Size=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund4", excelLabel.Fund_Size);
				String vintageyear=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund4", excelLabel.Fund_VintageYear);
				String contact=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund4", excelLabel.Fund_Contact);
				String phone=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund4", excelLabel.Fund_Phone);
				String email=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund4", excelLabel.Fund_Email);
				String description=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund4", excelLabel.Fund_Description);
				if(!sendKeys(driver, fp.getSizeInMillionTextBox(Workspace.FundraisingWorkspace	, 60), Size, "Size in Million text box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to size in million text box.");
				}
				if(!sendKeys(driver,fp.getVintageYear(Workspace.FundraisingWorkspace, 60), vintageyear, "vintage Year", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to Vintage Year text box.");
				}
				if(!sendKeys(driver, fp.getContactTextBox(Workspace.FundraisingWorkspace, 60), contact, "Contact text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to Contact text box.");
				}
				if(!sendKeys(driver, fp.getPhoneTextBox(Workspace.FundraisingWorkspace, 60),phone, "phone text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to phone text box.");
				}
				if(!sendKeys(driver, fp.getEmailTextBox(Workspace.FundraisingWorkspace, 60),email, "email text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to email text box.");
				}
				if(!sendKeys(driver, fp.getDescriptionTextBox(Workspace.FundraisingWorkspace, 60),description, "description text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to description text box.");
				}
				if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 60), "Next Button", action.BOOLEAN)){
					appLog.info("Click on next 1of3 button");
				}else{
					appLog.error("Not able to click on next 1 of 3 button");
					saa.assertTrue(false, "Not able to click on next 1 of 3 button");
				}
				if(!fp.importFolderTemplateBulk(excelPath,"Bulk Folder Template 2", M15BulkFolderTemplateName2, WorkSpaceAction.CREATEFOLDERTEMPLATE, Workspace.FundraisingWorkspace, 60)){
					appLog.error("Folder sructure is not created properly");
					BaseLib.sa.assertTrue(false,"Folder sructure is not created properly");
				} else {
					appLog.info("Folder Structure is created successfully.");
				}
				if(fp.verifyFolderStructure(fp.folderStructureInExcel(excelPath,"Bulk Folder Template 2"), 5)){
					appLog.info("Folder strucuture is verified.");
				} else {
					appLog.error("Folder structure is not verified.");
					sa.assertTrue(false, "Folder structure is not verified.");
				}
				String id=null;
				id=fp.getCreatedFolderId(CommonFolderName, PageName.BuildStep2Of3, 20);
				if(fp.clickOnAddFolderButton(id)){
					String text=switchToAlertAndGetMessage(driver, 20, action.GETTEXT);
					if(text.equalsIgnoreCase(FundsPageErrorMessage.bulkAddFolderErrorMessage)){
						appLog.info("Bulk add folder message is verified");
					}else{
						appLog.error("Bulk add folder message is not verified");
						saa.assertTrue(false, "Bulk add folder message is not verified");
					}
				switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
				}else{
					appLog.error("Not able to click on add folder button");
					saa.assertTrue(false, "Not able to click on add folder button");
				}
				if(click(driver, fp.getNext2Of3Button(Workspace.FundraisingWorkspace, 60), "Next button 2Of3", action.BOOLEAN)){
				appLog.info("Clicked on next 2 of 3 button");
				}else{
					appLog.error("Not able to click on next 2 of 3 button");
					saa.assertTrue(false, "Not able to click on next 2 of 3 button");
				}
				List<String> investorList = CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
				for(int i = 0; i < 100; i++){
				if(fp.selectAccountFromBuildStep3of3(investorList.get(i), "Yes", gmailUserName,gmailPassword,"noreply@navatarinvestor.com",CRMUser1EmailID,"request update", Workspace.FundraisingWorkspace, 60)){
					appLog.info("Investor added successfully");
				}else{
					appLog.error("Investor not added successfully");
					saa.assertTrue(false, "Investor not added successfully");
				}
				}							
					if(click(driver, fp.getDone3Of3Button(Workspace.FundraisingWorkspace, 60), "Done Button", action.SCROLLANDBOOLEAN)){
							appLog.info("Clicked on done button");
						if(click(driver, fp.getCongratulationsCloseButton(Workspace.FundraisingWorkspace, 60), "Close button", action.SCROLLANDBOOLEAN)){
							ThreadSleep(2000);
						if(click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60), "Manage investor icon", action.SCROLLANDBOOLEAN)){
							investorList = CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
							for(int i = 100; i < 180; i++){
							if(fp.selectAccountFromManageTargetPopUp(investorList.get(i), "Yes",gmailUserName,gmailPassword,"noreply@navatarinvestor.com",CRMUser1EmailID,"request update", Workspace.FundraisingWorkspace, 60)){
								appLog.info("Investor added successfully");
							}else{
								appLog.error("Investor not added successfully");
								saa.assertTrue(false, "Investor not added successfully");
							}
							}	
							if (fp.setFieldValueOnManageInvestor("Account:Legal Name", 1, Workspace.FundraisingWorkspace,60)) {
								if (fp.setOperatorValueOnManageInvestor("equals", 1,Workspace.FundraisingWorkspace,60)) {
									if (fp.setCriterionValueOnManageTarget("textbox", 1,"Bulk Inst181", "Account:Legal Name",Workspace.FundraisingWorkspace,60)) {
										click(driver,fp.getManageInvestorFilterApplyButton(Workspace.FundraisingWorkspace, 60), "Apply Button", action.BOOLEAN);
									WebElement	ele = fp.getInstituionCheckBoxOn3Of3("Bulk Inst181", Workspace.FundraisingWorkspace, 60);
									if(click(driver, ele,"Institution Check Box.", action.BOOLEAN)){
										String text=switchToAlertAndGetMessage(driver, 20, action.GETTEXT);
										if(text.equalsIgnoreCase(FundsPageErrorMessage.bulkAddInvestorErrorMessage)){
											appLog.info("Bulk add investor message is verified");
										}else{
											appLog.error("Bulk add investor message is not verified");
											saa.assertTrue(false, "Bulk add investor message is not verified");
										}
									switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
									}else{
										appLog.error("Not able to click on institution checkbox");
										saa.assertTrue(false, "Not able to click on institution checkbox");
									}
									}else{
										appLog.error("Not able to set criterion value");
										saa.assertTrue(false, "Not able to set criterion value");
									}
								}else{
									appLog.error("Not able to set operator value");
									saa.assertTrue(false, "Not able ot set opeartaor value");
								}
							}else{
								appLog.error("Not able to set field value");
								saa.assertTrue(false, "Not able to set field value");
							}							
					if(click(driver, fp.getManageInvestorPopupCrossIcon(Workspace.FundraisingWorkspace, 60), "Cross icon", action.SCROLLANDBOOLEAN)){
								appLog.info("Clicked on cross icon");
							}else{
								appLog.error("Not able ot click on cross icon");
								saa.assertTrue(false, "Not able ot click on cross icon");								
							}							
						}else{
							appLog.error("Not able to click on manage invetsor");
							saa.assertTrue(false, "Not able to clcik on manage investor");
						}
						if(click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 60), "Manage folder", action.SCROLLANDBOOLEAN)){
							if(fp.verifyFolderStructure(fp.folderStructureInExcel(excelPath,"Bulk Folder Template 2"), 5)){
								appLog.info("Folder structure is verified.");
							} else {
								appLog.error("Folder structure is not verified.");
								sa.assertTrue(false, "Folder structure is not verified.");
							}
						if(click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 60), "Close button", action.SCROLLANDBOOLEAN)){
							appLog.info("Clicked on close button");							
						}else{
							appLog.error("Nota ble to click on close button");
							saa.assertTrue(false, "Not able ot click on close button");
						}						
						}else{
							appLog.error("Not able to click on manage folder");
							saa.assertTrue(false, "Not able to click on manage folder");
						}							
						}else{
							appLog.error("Not able to click on close button");
							saa.assertTrue(false, "Not able to click on close button");
						}
						}else{
							appLog.error("Not able to click on done button");
							saa.assertTrue(false, "Not able to click on done button");
						}						
			}else{
				appLog.error("Not able to click on build fundraising button");
				saa.assertTrue(false, "Not able to click on build fundraising button");
			}
		}else{
			appLog.error("Not able to click on created fund");
			saa.assertTrue(false, "Not able to click on created fund");
		}
		}else{
			appLog.error("Not able to click on funds tab");
			saa.assertTrue(false, "Not able to click on funds tab");			
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc007_2_verifyFolderStructure() {
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		SoftAssert saa=new SoftAssert();
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName4, Workspace.FundraisingWorkspace, 30)){
			appLog.info("Successfully downloaded report");
		} else {
			appLog.error("Not able to download.");
			saa.assertTrue(false, "Not able to download.");
		}
		
		ThreadSleep(7000);
		String s =getFilesNameFromSystemFolder("DownloadedFiles");
		String fileName=null;
		if(s != null) {
			List<String> downloadedfileList=createListOutOfString(s);
			for (int i=0; i<downloadedfileList.size(); i++) {
				if(BoxPageBusinesslayer.fileName.equalsIgnoreCase(downloadedfileList.get(i))) {
					appLog.info("Downloaded File is available in download folder : "+BoxPageBusinesslayer.fileName);
					fileName=BoxPageBusinesslayer.fileName;
					break;
				}else {
					if(i==downloadedfileList.size()-1) {
						appLog.error(BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
						sa.assertTrue(false, BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot get file from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot get file from downloaded folder");
		}
		if(s!=null && !s.isEmpty() && fileName!=null) {
			List<String> investorList = CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
			String investorListInS = CommonLib.createStringOutOfList(investorList);
				List<String> notFoundFolders =box.verifyFolderStructureInHub(excelPath,System.getProperty("user.dir")+"\\DownloadedFiles\\"+fileName, "Bulk Folder Template 2", HubUserName, HubPassword, M15FundName4, Org1FirmName, investorListInS, CRMUser1FirstName+" "+CRMUser1LastName, Workspace.FundraisingWorkspace, 30);
				for (String string : notFoundFolders) {
//					System.err.println(string);
					saa.assertTrue(false, string+" folder structure not verified ");
				}
			
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot verifying folder structure");
			sa.assertTrue(false, "Not file is available in downloaded file folder so cannot verifying folder structure");
		}
		sa.combineAssertions(saa);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc008_1_BuildWorkspacForFund5(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp=new FundsPageBusinessLayer(driver);
		String StandardFolderName=ExcelUtils.readData(excelPath,"FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		SoftAssert saa=new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(bp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName5)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 30), "Build fundraising workspace button", action.BOOLEAN)){
					String Size=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund5", excelLabel.Fund_Size);
					String vintageyear=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund5", excelLabel.Fund_VintageYear);
					String contact=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund5", excelLabel.Fund_Contact);
					String phone=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund5", excelLabel.Fund_Phone);
					String email=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund5", excelLabel.Fund_Email);
					String description=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund5", excelLabel.Fund_Description);
					if(!sendKeys(driver, fp.getSizeInMillionTextBox(Workspace.FundraisingWorkspace	, 60), Size, "Size in Million text box", action.BOOLEAN)){
						BaseLib.sa.assertTrue(false,"Not able to pass data to size in million text box.");
					}
					if(!sendKeys(driver,fp.getVintageYear(Workspace.FundraisingWorkspace, 60), vintageyear, "vintage Year", action.BOOLEAN)){
						BaseLib.sa.assertTrue(false,"Not able to pass data to Vintage Year text box.");
					}
					if(!sendKeys(driver, fp.getContactTextBox(Workspace.FundraisingWorkspace, 60), contact, "Contact text Box", action.BOOLEAN)){
						BaseLib.sa.assertTrue(false,"Not able to pass data to Contact text box.");
					}
					if(!sendKeys(driver, fp.getPhoneTextBox(Workspace.FundraisingWorkspace, 60),phone, "phone text Box", action.BOOLEAN)){
						BaseLib.sa.assertTrue(false,"Not able to pass data to phone text box.");
					}
					if(!sendKeys(driver, fp.getEmailTextBox(Workspace.FundraisingWorkspace, 60),email, "email text Box", action.BOOLEAN)){
						BaseLib.sa.assertTrue(false,"Not able to pass data to email text box.");
					}
					if(!sendKeys(driver, fp.getDescriptionTextBox(Workspace.FundraisingWorkspace, 60),description, "description text Box", action.BOOLEAN)){
						BaseLib.sa.assertTrue(false,"Not able to pass data to description text box.");
					}
					if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 60), "Next Button", action.BOOLEAN)){
						appLog.info("Click on next 1of3 button");
					}else{
						appLog.error("Not able to click on next 1 of 3 button");
						saa.assertTrue(false, "Not able to click on next 1 of 3 button");
					}
					if(click(driver, fp.getNext2Of3Button(Workspace.FundraisingWorkspace, 60), "Next button 2Of3", action.BOOLEAN)){
						appLog.info("Clicked on next 2 of 3 button");
						}else{
							appLog.error("Not able to click on next 2 of 3 button");
							saa.assertTrue(false, "Not able to click on next 2 of 3 button");
						}
					if(click(driver, fp.getDone3Of3Button(Workspace.FundraisingWorkspace, 60), "Done Button", action.SCROLLANDBOOLEAN)){
						appLog.info("Clicked on done button");
					if(click(driver, fp.getCongratulationsCloseButton(Workspace.FundraisingWorkspace, 60), "Close button", action.SCROLLANDBOOLEAN)){
						ThreadSleep(2000);
					}else{
						appLog.error("Not able to click on close button");
						saa.assertTrue(false, "Not able to click on close button");
					}
					}else{
						appLog.error("Not able to click on done button");
						saa.assertTrue(false, "Not able to click on done button");
					}	
				if(click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 60), "Fundraising workspace", action.SCROLLANDBOOLEAN)){
					if(!fp.createFolderStructureFromExcelBulk(excelPath, "Bulk Folder Template 1", Workspace.FundraisingWorkspace,PageName.ManageFolderPopUp,60)){
						appLog.error("Folder sructure is not created properly");
						BaseLib.sa.assertTrue(false,"Folder sructure is not created properly");
					} else {
						appLog.info("Folder Structure is created successfully.");
					}
					if(fp.verifyFolderStructure(fp.folderStructureInExcel(excelPath,"Bulk Folder Template 1"), 5)){
						appLog.info("Folder structure is verified.");
					} else {
						appLog.error("Folder structure is not verified.");
						sa.assertTrue(false, "Folder structure is not verified.");
					}
					String id=null;
					id=fp.getCreatedFolderId(StandardFolderName, PageName.ManageFolderPopUp, 20);
					if(fp.clickOnAddFolderButton(id)){
						String text=switchToAlertAndGetMessage(driver, 20, action.GETTEXT);
						if(text.equalsIgnoreCase(FundsPageErrorMessage.bulkAddFolderErrorMessage)){
							appLog.info("Bulk add folder message is verified");
						}else{
							appLog.error("Bulk add folder message is not verified");
							saa.assertTrue(false, "Bulk add folder message is not verified");
						}
					switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
					}else{
						appLog.error("Not able to click on add folder button");
						saa.assertTrue(false, "Not able to click on add folder button");
					}	
				if(click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 60), "Manage folder close button", action.SCROLLANDBOOLEAN)){
					appLog.info("Clicked on close button successfully");
				}else{
					appLog.error("Not able to click on close button successfully");
					saa.assertTrue(false, "Not able to click on close button successfully");					
				}				
				}else{
					appLog.error("Not able to click on manage folder icon");
					saa.assertTrue(false, "Not able to click on manage folder icon");
				}
		if(click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60), "Manage investor icon", action.SCROLLANDBOOLEAN)){
			List<String> investorList = CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
			for(int i = 0; i < investorList.size(); i++){
			if(fp.selectAccountFromManageTargetPopUp(investorList.get(i), "No", null, null, null, null, null, Workspace.FundraisingWorkspace, 60)){
				appLog.info("Investor added successfully");
			}else{
				appLog.error("Investor not added successfully");
				saa.assertTrue(false, "Investor not added successfully");
			}
			}
			if (fp.setFieldValueOnManageInvestor("Account:Legal Name", 1, Workspace.FundraisingWorkspace,60)) {
				if (fp.setOperatorValueOnManageInvestor("equals", 1,Workspace.FundraisingWorkspace,60)) {
					if (fp.setCriterionValueOnManageTarget("textbox", 1,"Bulk Inst181", "Account:Legal Name",Workspace.FundraisingWorkspace,60)) {
						click(driver,fp.getManageInvestorFilterApplyButton(Workspace.FundraisingWorkspace, 60), "Apply Button", action.BOOLEAN);
					WebElement	ele = fp.getInstituionCheckBoxOn3Of3("Bulk Inst181", Workspace.FundraisingWorkspace, 60);
					if(click(driver, ele,"Institution Check Box.", action.BOOLEAN)){
						String text=switchToAlertAndGetMessage(driver, 20, action.GETTEXT);
						if(text.equalsIgnoreCase(FundsPageErrorMessage.bulkAddInvestorErrorMessage)){
							appLog.info("Bulk add investor message is verified");
						}else{
							appLog.error("Bulk add investor message is not verified");
							saa.assertTrue(false, "Bulk add investor message is not verified");
						}
					switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
					}else{
						appLog.error("Not able to click on institution checkbox");
						saa.assertTrue(false, "Not able to click on institution checkbox");
					}
					}else{
						appLog.error("Not able to set criterion value");
						saa.assertTrue(false, "Not able to set criterion value");
					}
				}else{
					appLog.error("Not able to set operator value");
					saa.assertTrue(false, "Not able ot set opeartaor value");
				}
			}else{
				appLog.error("Not able to set field value");
				saa.assertTrue(false, "Not able to set field value");
			}							
	if(click(driver, fp.getManageInvestorPopupCrossIcon(Workspace.FundraisingWorkspace, 60), "Cross icon", action.SCROLLANDBOOLEAN)){
				appLog.info("Clicked on cross icon");
			}else{
				appLog.error("Not able ot click on cross icon");
				saa.assertTrue(false, "Not able ot click on cross icon");								
			}				
		}else{
			appLog.error("Not able to click on manage investor icon ");
			saa.assertTrue(false, "Not able ot click on manage investor icon");
		}							
		}else{
			appLog.error("Not able to click on build fundraising button");
			saa.assertTrue(false, "Not able to click on build fundraising button");
		}
	}else{
		appLog.error("Not able to click on created fund");
		saa.assertTrue(false, "Not able to click on created fund");
	}
		}else{
			appLog.error("Not able to click on funds tab");
			saa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc008_2_verifyFolderStructure() {
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		SoftAssert saa=new SoftAssert();
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName5, Workspace.FundraisingWorkspace, 30)){
			appLog.info("Successfully downloaded report");
		} else {
			appLog.error("Not able to download.");
			saa.assertTrue(false, "Not able to download.");
		}
		
		ThreadSleep(7000);
		String s =getFilesNameFromSystemFolder("DownloadedFiles");
		String fileName=null;
		if(s != null) {
			List<String> downloadedfileList=createListOutOfString(s);
			for (int i=0; i<downloadedfileList.size(); i++) {
				if(BoxPageBusinesslayer.fileName.equalsIgnoreCase(downloadedfileList.get(i))) {
					appLog.info("Downloaded File is available in download folder : "+BoxPageBusinesslayer.fileName);
					fileName=BoxPageBusinesslayer.fileName;
					break;
				}else {
					if(i==downloadedfileList.size()-1) {
						appLog.error(BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
						sa.assertTrue(false, BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot get file from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot get file from downloaded folder");
		}
		
		if(s!=null && !s.isEmpty() && fileName!=null) {
			List<String> investorList = CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
			String investorListInS = CommonLib.createStringOutOfList(investorList);
			List<String> notFoundFolders =box.verifyFolderStructureInHub(excelPath,System.getProperty("user.dir")+"\\DownloadedFiles\\"+fileName, "Bulk Folder Template 1", HubUserName, HubPassword, M15FundName5, Org1FirmName, investorListInS, CRMUser1FirstName+" "+CRMUser1LastName, Workspace.FundraisingWorkspace, 30);
			for (String string : notFoundFolders) {
//				System.err.println(string);
				saa.assertTrue(false, string+" folder structure not verified ");
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot verifying folder structure");
			sa.assertTrue(false, "Not file is available in downloaded file folder so cannot verifying folder structure");
		}
		
		sa.combineAssertions(saa);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc009_1_BuildWorkspaceForFund6(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp=new FundsPageBusinessLayer(driver);
		String StandardFolderName=ExcelUtils.readData(excelPath,"FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		SoftAssert saa=new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(bp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName6)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 30), "Build fundraising workspace button", action.BOOLEAN)){
					String Size=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund6", excelLabel.Fund_Size);
					String vintageyear=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund6", excelLabel.Fund_VintageYear);
					String contact=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund6", excelLabel.Fund_Contact);
					String phone=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund6", excelLabel.Fund_Phone);
					String email=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund6", excelLabel.Fund_Email);
					String description=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund6", excelLabel.Fund_Description);
					if(!sendKeys(driver, fp.getSizeInMillionTextBox(Workspace.FundraisingWorkspace	, 60), Size, "Size in Million text box", action.BOOLEAN)){
						BaseLib.sa.assertTrue(false,"Not able to pass data to size in million text box.");
					}
					if(!sendKeys(driver,fp.getVintageYear(Workspace.FundraisingWorkspace, 60), vintageyear, "vintage Year", action.BOOLEAN)){
						BaseLib.sa.assertTrue(false,"Not able to pass data to Vintage Year text box.");
					}
					if(!sendKeys(driver, fp.getContactTextBox(Workspace.FundraisingWorkspace, 60), contact, "Contact text Box", action.BOOLEAN)){
						BaseLib.sa.assertTrue(false,"Not able to pass data to Contact text box.");
					}
					if(!sendKeys(driver, fp.getPhoneTextBox(Workspace.FundraisingWorkspace, 60),phone, "phone text Box", action.BOOLEAN)){
						BaseLib.sa.assertTrue(false,"Not able to pass data to phone text box.");
					}
					if(!sendKeys(driver, fp.getEmailTextBox(Workspace.FundraisingWorkspace, 60),email, "email text Box", action.BOOLEAN)){
						BaseLib.sa.assertTrue(false,"Not able to pass data to email text box.");
					}
					if(!sendKeys(driver, fp.getDescriptionTextBox(Workspace.FundraisingWorkspace, 60),description, "description text Box", action.BOOLEAN)){
						BaseLib.sa.assertTrue(false,"Not able to pass data to description text box.");
					}
					if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 60), "Next Button", action.BOOLEAN)){
						appLog.info("Click on next 1of3 button");
					}else{
						appLog.error("Not able to click on next 1 of 3 button");
						saa.assertTrue(false, "Not able to click on next 1 of 3 button");
					}
					if(click(driver, fp.getNext2Of3Button(Workspace.FundraisingWorkspace, 60), "Next button 2Of3", action.BOOLEAN)){
						appLog.info("Clicked on next 2 of 3 button");
						}else{
							appLog.error("Not able to click on next 2 of 3 button");
							saa.assertTrue(false, "Not able to click on next 2 of 3 button");
						}
					if(click(driver, fp.getDone3Of3Button(Workspace.FundraisingWorkspace, 60), "Done Button", action.SCROLLANDBOOLEAN)){
						appLog.info("Clicked on done button");
					if(click(driver, fp.getCongratulationsCloseButton(Workspace.FundraisingWorkspace, 60), "Close button", action.SCROLLANDBOOLEAN)){
						ThreadSleep(2000);
					}else{
						appLog.error("Not able to click on close button");
						saa.assertTrue(false, "Not able to click on close button");
					}
					}else{
						appLog.error("Not able to click on done button");
						saa.assertTrue(false, "Not able to click on done button");
					}	
				if(click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 60), "Fundraising workspace", action.SCROLLANDBOOLEAN)){
					if(!fp.createFolderStructureFromExcelBulk(excelPath, "Bulk Folder Template 2", Workspace.FundraisingWorkspace,PageName.ManageFolderPopUp,60)){
						appLog.error("Folder sructure is not created properly");
						BaseLib.sa.assertTrue(false,"Folder sructure is not created properly");
					} else {
						appLog.info("Folder Structure is created successfully.");
					}
					if(fp.verifyFolderStructure(fp.folderStructureInExcel(excelPath,"Bulk Folder Template 2"), 5)){
						appLog.info("Folder structure is verified.");
					} else {
						appLog.error("Folder structure is not verified.");
						sa.assertTrue(false, "Folder structure is not verified.");
					}
					String id=null;
					id=fp.getCreatedFolderId(StandardFolderName, PageName.ManageFolderPopUp, 20);
					if(fp.clickOnAddFolderButton(id)){
						String text=switchToAlertAndGetMessage(driver, 20, action.GETTEXT);
						if(text.equalsIgnoreCase(FundsPageErrorMessage.bulkAddFolderErrorMessage)){
							appLog.info("Bulk add folder message is verified");
						}else{
							appLog.error("Bulk add folder message is not verified");
							saa.assertTrue(false, "Bulk add folder message is not verified");
						}
					switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
					}else{
						appLog.error("Not able to click on add folder button");
						saa.assertTrue(false, "Not able to click on add folder button");
					}	
				if(click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 60), "Manage folder close button", action.SCROLLANDBOOLEAN)){
					appLog.info("Clicked on close button successfully");
				}else{
					appLog.error("Not able to click on close button successfully");
					saa.assertTrue(false, "Not able to click on close button successfully");					
				}				
				}else{
					appLog.error("Not able to click on manage folder icon");
					saa.assertTrue(false, "Not able to click on manage folder icon");
				}
		if(click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60), "Manage investor icon", action.SCROLLANDBOOLEAN)){
			List<String> investorList = CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
			for(int i = 0; i < investorList.size(); i++){
			if(fp.selectAccountFromManageTargetPopUp(investorList.get(i), "No", null, null, null, null, null, Workspace.FundraisingWorkspace, 60)){
				appLog.info("Investor added successfully");
			}else{
				appLog.error("Investor not added successfully");
				saa.assertTrue(false, "Investor not added successfully");
			}
			}
			if (fp.setFieldValueOnManageInvestor("Account:Legal Name", 1, Workspace.FundraisingWorkspace,60)) {
				if (fp.setOperatorValueOnManageInvestor("equals", 1,Workspace.FundraisingWorkspace,60)) {
					if (fp.setCriterionValueOnManageTarget("textbox", 1,"Bulk Inst181", "Account:Legal Name",Workspace.FundraisingWorkspace,60)) {
						click(driver,fp.getManageInvestorFilterApplyButton(Workspace.FundraisingWorkspace, 60), "Apply Button", action.BOOLEAN);
					WebElement	ele = fp.getInstituionCheckBoxOn3Of3("Bulk Inst181", Workspace.FundraisingWorkspace, 60);
					if(click(driver, ele,"Institution Check Box.", action.BOOLEAN)){
						String text=switchToAlertAndGetMessage(driver, 20, action.GETTEXT);
						if(text.equalsIgnoreCase(FundsPageErrorMessage.bulkAddInvestorErrorMessage)){
							appLog.info("Bulk add investor message is verified");
						}else{
							appLog.error("Bulk add investor message is not verified");
							saa.assertTrue(false, "Bulk add investor message is not verified");
						}
					switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
					}else{
						appLog.error("Not able to click on institution checkbox");
						saa.assertTrue(false, "Not able to click on institution checkbox");
					}
					}else{
						appLog.error("Not able to set criterion value");
						saa.assertTrue(false, "Not able to set criterion value");
					}
				}else{
					appLog.error("Not able to set operator value");
					saa.assertTrue(false, "Not able ot set opeartaor value");
				}
			}else{
				appLog.error("Not able to set field value");
				saa.assertTrue(false, "Not able to set field value");
			}							
	if(click(driver, fp.getManageInvestorPopupCrossIcon(Workspace.FundraisingWorkspace, 60), "Cross icon", action.SCROLLANDBOOLEAN)){
				appLog.info("Clicked on cross icon");
			}else{
				appLog.error("Not able ot click on cross icon");
				saa.assertTrue(false, "Not able ot click on cross icon");								
			}				
		}else{
			appLog.error("Not able to click on manage investor icon ");
			saa.assertTrue(false, "Not able ot click on manage investor icon");
		}							
		}else{
			appLog.error("Not able to click on build fundraising button");
			saa.assertTrue(false, "Not able to click on build fundraising button");
		}
	}else{
		appLog.error("Not able to click on created fund");
		saa.assertTrue(false, "Not able to click on created fund");
	}
		}else{
			appLog.error("Not able to click on funds tab");
			saa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
		}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc009_2_verifyFolderStructure() {
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		SoftAssert saa=new SoftAssert();
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName6, Workspace.FundraisingWorkspace, 30)){
			appLog.info("Successfully downloaded report");
		} else {
			appLog.error("Not able to download.");
			saa.assertTrue(false, "Not able to download.");
		}
		ThreadSleep(7000);
		String s =getFilesNameFromSystemFolder("DownloadedFiles");
		String fileName=null;
		if(s != null) {
			List<String> downloadedfileList=createListOutOfString(s);
			for (int i=0; i<downloadedfileList.size(); i++) {
				if(BoxPageBusinesslayer.fileName.equalsIgnoreCase(downloadedfileList.get(i))) {
					appLog.info("Downloaded File is available in download folder : "+BoxPageBusinesslayer.fileName);
					fileName=BoxPageBusinesslayer.fileName;
					break;
				}else {
					if(i==downloadedfileList.size()-1) {
						appLog.error(BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
						sa.assertTrue(false, BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot get file from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot get file from downloaded folder");
		}
		
		if(s!=null && !s.isEmpty() && fileName!=null) {
			List<String> investorList = CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
			String investorListInS = CommonLib.createStringOutOfList(investorList);
				List<String> notFoundFolders =box.verifyFolderStructureInHub(excelPath,System.getProperty("user.dir")+"\\DownloadedFiles\\"+fileName, "Bulk Folder Template 2", HubUserName, HubPassword, M15FundName6, Org1FirmName, investorListInS, CRMUser1FirstName+" "+CRMUser1LastName, Workspace.FundraisingWorkspace, 30);
				for (String string : notFoundFolders) {
//					System.err.println(string);
					saa.assertTrue(false, string+" folder structure not verified ");
				}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot verifying folder structure");
			sa.assertTrue(false, "Not file is available in downloaded file folder so cannot verifying folder structure");
		}
			sa.combineAssertions(saa);
			sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc010_1_VerifyRemovalOfInvestorFromManageInvestorsPopUp(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp=new FundsPageBusinessLayer(driver);
		String inst1 = ExcelUtils.readData(excelPath, "Institutions", excelLabel.Variable_Name, "M15Institution1", excelLabel.Institutions_Name);
		String stdPath = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
//		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		String parentID = null;
		String dropImage="DropLoc.jpg";
		String folderpath1 = "UploadFiles\\Module15";
		String msg=null;
//		String uploadedFile="Test123.pdf";
		List<String> institutionname= CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(bp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName5)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view.");
				if (fp.verifyFolderPathdummy(stdPath,inst1 , null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					if (click(driver, fp.getUploadIcon(Workspace.FundraisingWorkspace, 30), "upload file button", action.BOOLEAN)) {
						parentID = switchOnWindow(driver);
						if (parentID!=null) {
							if(click(driver, fp.getMultipleInstituionRadioButton(30), "mulitple institution radio button", action.BOOLEAN)){
								if (click(driver, fp.getCheckAllInstitutions(30), "check all institutions", action.BOOLEAN)) {
									if(click(driver, fp.getUploadNextButton(Workspace.FundraisingWorkspace, 30), "Next Button", action.BOOLEAN)){
										if (fp.dragDropFiles(folderpath1, dropImage)) {
											if(click(driver, fp.getUploadSaveButton(30), "Upload Save Button", action.BOOLEAN)){
												if (isAlertPresent(driver)) {
													msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
													switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
													if (msg.trim().equals(FundsPageErrorMessage.DocumentUploadSuccessMsg)) {
														appLog.info("successfully found uploaded document message");
													}
												}else {
													appLog.info("document is not uploaded.. we will check from email");
													msg = fp.getUploadBulkDelayedMessage(180).getText();
													if(msg.contains("email") && msg.contains("Document") && msg.contains("upload")){
														for(int j = 0 ; j <= 100 ; j++)
															if(EmailLib.mailReceived(gmailUserName, gmailPassword, "noreply@navatarinvestor.com", CRMUser1EmailID, "File upload status","file upload process has been completed" )){
																System.out.println("\n\n\nFound\n\n\n");
																break;
															} else {
																System.out.println("Mail is not found, Will Check for mail again.");
																if(j>=100){
																	appLog.error("Mail is not received.");
																	BaseLib.sa.assertTrue(false,"Mail is not received ");
																}
															}
													}else {
														appLog.error("msg warming on web page is not related to reading email regarding upload");
														sa.assertTrue(false, "msg warming on web page is not related to reading email regarding upload");
													}
												}
											}else {
												appLog.error("upload save button is not clickable");
												sa.assertTrue(false, "upload save button is not clickable");
											}
										}else {
											appLog.error("could not drag and drop files");
											sa.assertTrue(false, "could not drag and drop files");
										}
									}else {
										appLog.error("next button is not clickable");
										sa.assertTrue(false, "next button is not clickable");
									}
								}else {
									appLog.error("checkbox to check all isntitutions is not clickable");
									sa.assertTrue(false, "checkbox to check all isntitutions is not clickable");
								}
							}else {
								appLog.error("multiple inst radio button is not visible");
								sa.assertTrue(false, "multiple inst radio button is not visible");
							}
							driver.close();
							driver.switchTo().window(parentID);
						}else {
							appLog.error("could not find window to switch");
							sa.assertTrue(false, "could not find window to switch");
						}
					}else {
						appLog.error("upload icon is not clickable");
						sa.assertTrue(false, "upload icon is not clickable");
					}
					}else {
					appLog.error(stdPath+" is not found on folder structure");
					sa.assertTrue(false, stdPath+" is not found on folder structure");
					}
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view.");	
		if(click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60), "Manage investor icon", action.SCROLLANDBOOLEAN)){
			for (int i = 175; i < institutionname.size(); i++) {
				if (fp.setFieldValueOnManageInvestor("Account:Legal Name", 1, Workspace.FundraisingWorkspace,60)) {
					if (fp.setOperatorValueOnManageInvestor("equals", 1,Workspace.FundraisingWorkspace,60)) {
						if (fp.setCriterionValueOnManageTarget("textbox", 1,institutionname.get(i), "Account:Legal Name",Workspace.FundraisingWorkspace,60)) {
							click(driver,fp.getManageInvestorFilterApplyButton(Workspace.FundraisingWorkspace, 60), "Apply Button", action.BOOLEAN);
							WebElement	ele = fp.getInstituionCheckBoxOn3Of3(institutionname.get(i), Workspace.FundraisingWorkspace, 60);
							if(click(driver, ele,"Institution Check Box.", action.BOOLEAN)){
								appLog.info("Clicked on institution checkbox");
								ThreadSleep(5000);
								if(click(driver, fp.getManageInvestorDeletedPopupCloseButton(Workspace.FundraisingWorkspace, 60), "manage investor remove pop up close button", action.SCROLLANDBOOLEAN)) {
									appLog.info("clicked on close button");
								}else {
									appLog.error("manage investor remove pop up close buttton is not present");
									sa.assertTrue(false, "manage investor remove pop up close buttton is not present");
								}
							}else{
								appLog.error("Not able to click on institution checkbox");
								sa.assertTrue(false, "Not able to click on institution checkbox");
							}
						}else{
							appLog.error("Not able to set criterion value");
							sa.assertTrue(false, "Not able to set criterion value");
						}
					}else{
						appLog.error("Not able to set operator value");
						sa.assertTrue(false, "Not able ot set opeartaor value");
					}
				}else{
					appLog.error("Not able to set field value");
					sa.assertTrue(false, "Not able to set field value");
				}
			}
		if(click(driver, fp.getManageInvestorDoneButton(Workspace.FundraisingWorkspace, 60), "Done button", action.SCROLLANDBOOLEAN)){
			appLog.info("Clicked on done button");
		}else{
			appLog.error("Not able to click on done button");
			sa.assertTrue(false, "Not able to click on done button");
		}		
		}else{
			appLog.error("Not able to click on manage investor icon");
			sa.assertTrue(false, "Not able to click on manage investor icon");
		}
		for (int i = 175; i < institutionname.size(); i++) {
			if(!fp.clickOnInstituionFolder(institutionname.get(i), Workspace.FundraisingWorkspace, 1)){
				appLog.info(institutionname.get(i)+" folder is not displaying");
			}else{
				appLog.error(institutionname.get(i)+" folder is displaying");
				sa.assertTrue(false, institutionname.get(i)+" folder is displaying");
			}			
		}
		}else{
				appLog.error("Not able to click on created fund");
				sa.assertTrue(false, "Not able to click on created fund");
			}
				}else{
					appLog.error("Not able to click on funds tab");
					sa.assertTrue(false, "Not able to click on funds tab");
				}
				switchToDefaultContent(driver);
				lp.CRMlogout();
				sa.assertAll();	
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc010_2_verifyFolderStructure() {
		String uploadedFile="Test123.pdf";
		String stdPath = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, "M15tc010_1_VerifyRemovalOfInvestorFromManageInvestorsPopUp", excelLabel.StandardPath);
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName5, Workspace.FundraisingWorkspace, 30)){
			appLog.info("Successfully downloaded report");
		} else {
			appLog.error("Not able to download.");
		}
		List<String> investorList = CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
	//	List<String> l = getValueBasedOnLabel(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", excelLabel.Path, 0);
		for (int i = 0;i<investorList.size()-6;i++) {
			System.err.println("All Files/"+Org1FirmName+"/Potential Investment/"+M15FundName5+"/"+ investorList.get(i)+"/"+stdPath+"/"+uploadedFile);
			if (ExcelUtils.readData(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", excelLabel.Path, "All Files/"+Org1FirmName+"/Potential Investment/"+M15FundName5+"/"+ investorList.get(i)+"/"+stdPath+"/"+uploadedFile, excelLabel.Path).contains("All Files/"+Org1FirmName+"/Potential Investment/"+M15FundName5+"/"+ investorList.get(i)+"/"+stdPath+"/"+uploadedFile))
//		/	if (l.contains("All Files/"+Org1FirmName+"/Potential Investment/"+M15FundName5+"/"+ investorList.get(i)+"/"+stdPath+"/"+uploadedFile))
				appLog.info("successfully found file of "+investorList.get(i)+" on folder structure");
			else {
				appLog.error("could not find file of "+investorList.get(i));
				sa.assertTrue(false, "could not find file of "+investorList.get(i));
			}
		}
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc011_0_uploadFiles(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath).split(",")[0];
		List<String> institutionList = CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
		String dropImage="DropLoc.jpg";
		String folderpath1 = "UploadFiles\\Module15";
		//upload
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M15FundName3)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, dropImage, stdPath, createStringOutOfList(institutionList), folderpath1, UploadFileActions.SelectAll, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
					appLog.info("Successfully uploaded file to "+stdPath);
				} else {
					appLog.error("Not able to upload file in "+stdPath);
					sa.assertTrue(false,"Not able to upload file in "+stdPath);
				}
			}else {
				appLog.error("could not find fundname");
				sa.assertTrue(false, "could not find fundname");
			}
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc011_1_verifyUpload() {
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		List<String> investorList = new ArrayList<String>();
		List<String> downLoadedFolderPathList = new ArrayList<String>();
		List<String> itemNameList = new ArrayList<String>();
		String stdPath = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, "M15tc011_0_uploadFiles", excelLabel.StandardPath).split(",")[0];
		String uploadedFile="Test123.pdf";
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName3, Workspace.FundraisingWorkspace, 30)){
			appLog.info("Successfully downloaded report");
		} else {
			appLog.error("Not able to download.");
			sa.assertTrue(false, "Not able to download.");
		}
		ThreadSleep(7000);
		String s =getFilesNameFromSystemFolder("DownloadedFiles");
		String fileName=null;
		if(s != null) {
			List<String> downloadedfileList=createListOutOfString(s);
			for (int i=0; i<downloadedfileList.size(); i++) {
				if(BoxPageBusinesslayer.fileName.equalsIgnoreCase(downloadedfileList.get(i))) {
					appLog.info("Downloaded File is available in download folder : "+BoxPageBusinesslayer.fileName);
					fileName=BoxPageBusinesslayer.fileName;
					investorList = CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
					String downLoadedFolderPath=ExcelUtils.readAllDataForAColumn(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", 2, false);
					String ItemName = ExcelUtils.readAllDataForAColumn(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", 4, false);
					downLoadedFolderPathList=createListOutOfStringUsingComma(downLoadedFolderPath);
					itemNameList=createListOutOfStringUsingComma(ItemName);
					break;
				}else {
					if(i==downloadedfileList.size()-1) {
						appLog.error(BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
						sa.assertTrue(false, BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot get file from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot get file from downloaded folder");
		}
		if(fileName!=null && !fileName.isEmpty()) {
			for (int i = 0;i<investorList.size()-5;i++) {
				String filepath="All Files/"+Org1FirmName+"/Potential Investment/"+M15FundName3+"/"+ investorList.get(i)+"/"+stdPath+"/"+uploadedFile;
				appLog.info("Going to verify path:  "+filepath);
				for(int j=0; j<downLoadedFolderPathList.size(); j++) {
					if (filepath.equalsIgnoreCase(downLoadedFolderPathList.get(j).trim())) {
						appLog.info("successfully found file of "+investorList.get(i)+" on folder structure");
						if(itemNameList.get(j).equalsIgnoreCase(uploadedFile)) {
							appLog.info("Item Name is matched for folder path  :"+downLoadedFolderPathList.get(i));
							
							
						}else {
							appLog.error("Item Name is not matched for folder path : "+downLoadedFolderPathList.get(i));
							sa.assertTrue(false, "Item Name is not matched for folder path : "+downLoadedFolderPathList.get(i));
						}
						break;
					}else {
						if(j==downLoadedFolderPathList.size()-1) {
							appLog.error("could not find file of "+investorList.get(i));
							sa.assertTrue(false, "could not find file of "+investorList.get(i));
						}
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot verify upload file name from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot verify upload file name from downloaded folder");
		}
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc011_2_renameFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		String inst1 = ExcelUtils.readData(excelPath, "Institutions", excelLabel.Variable_Name, "M15Institution1", excelLabel.Institutions_Name);
		String stdPath = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, "M15tc011_0_uploadFiles", excelLabel.StandardPath).split(",")[0];
		String stdPath8 = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, "M15tc011_0_uploadFiles", excelLabel.StandardPath).split(",")[1];
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M15FundName3)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath,inst1 , null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					if (click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 30), "manage folder icon", action.SCROLLANDBOOLEAN)) {
						String stdId = fp.getCreatedFolderIdFullTraverse(stdPath, PageName.ManageFolderPopUp, 30);
						System.err.println("std id is "+stdId);
						try {
							fp.clickOnRenameFolderButton(stdId);
							appLog.info("Clicked on folder rename icon : "+stdPath);
						}
						catch(Exception e) {
							appLog.error("Not able to click on rename folder icon so cannot rename folder name : "+stdPath);
							exit("Not able to click on rename folder icon so cannot rename folder name : "+stdPath);
							
						}
						if (sendKeys(driver, fp.getChildRenameFolderNameTextBox(Workspace.FundraisingWorkspace,PageName.ManageFolderPopUp, 30), stdPath8+"UP", "rename folder textbox", action.BOOLEAN)) {
							if (click(driver, fp.getParentRenameFolderSaveButton(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30), "save button", action.BOOLEAN)) {
								ThreadSleep(10000);
								if(isAlertPresent(driver)){
									String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
									switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
									if(msg.contains(FundsPageErrorMessage.YourRequestInProgressErrorMsg1)){
										appLog.info(msg+" :Message is verfiied successfully.");
									} else {
										appLog.error("Progress Message is not verified.Expexcted: "+FundsPageErrorMessage.YourRequestInProgressErrorMsg1+"\tActual: "+msg);
										sa.assertTrue(false,"Progress Message is not verified.Expexcted: "+FundsPageErrorMessage.YourRequestInProgressErrorMsg1+"\tActual: "+msg);
									}
									if(msg.contains("email") && msg.contains("progress") && msg.contains("request")){
										for(int j = 0 ; j <= 100 ; j++)
											if(EmailLib.mailReceived(gmailUserName, gmailPassword, "noreply@navatargroup.com", CRMUser1EmailID, "request update", "Processing request for "+stdPath8+"UP of "+M15FundName3+" has been completed successfully")){
												System.out.println("\n\n\nFound\n\n\n");
												break;
											} else {
												System.out.println("Mail is not found, Will Check for mail again.");
												if(j>=100){
													appLog.error("Mail is not received.");
													sa.assertTrue(false,"Mail is not received for Folder creation : "+stdPath8+"UP");

												}
											}
										click(driver, fp.getManageFolderCrossIcon(Workspace.FundraisingWorkspace, 30), "manage folders cross icon", action.BOOLEAN);
									} else {
										appLog.info("Unexpected alert found with message: "+msg);

									}
								}else {
									
									click(driver, fp.getManageFolderCrossIcon(Workspace.FundraisingWorkspace, 30), "manage folders cross icon", action.BOOLEAN);

								}
							}
							else {
								appLog.error("save button of rename folder is not clickable");
								sa.assertTrue(false, "save button of rename folder is not clickable");
							}
						}
							else {
								appLog.error("child rename folder text box is not visible");
								sa.assertTrue(false, "child rename folder text box is not visible");
							}
						
					}
					else {
						appLog.error("manage folder icon is not clickable");
						sa.assertTrue(false, "manage folder icon is not clickable");
					}
				}
				else {
					appLog.error(stdPath+" is not found on folder structure");
					sa.assertTrue(false, stdPath+" is not found on folder structure");
				}
			}
			else {
				appLog.error(M15FundName3+" is not found on funds tab");
				sa.assertTrue(false, M15FundName3+" is not found on funds tab");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc011_3_verifyRenameFolder() {
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		String stdPath = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, "M15tc011_0_uploadFiles", excelLabel.StandardPath).split(",")[0];
		String stdPath8 = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, "M15tc011_0_uploadFiles", excelLabel.StandardPath).split(",")[1];
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName3, Workspace.FundraisingWorkspace, 30)){
			appLog.info("Successfully downloaded report");
		} else {
			appLog.error("Not able to download report from box so cannot vcerify folder path");
			exit("Not able to download report from box so cannot vcerify folder path");
		}
		List<String> investorList = CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
		for (int i = 0;i<investorList.size();i++) {
			System.err.println("All Files/"+Org1FirmName+"/Potential Investment/"+M15FundName3+"/"+ investorList.get(i)+"/"+stdPath+"UP"+"/");
			if (ExcelUtils.readData(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", excelLabel.Path, "All Files/"+Org1FirmName+"/Potential Investment/"+M15FundName3+"/"+ investorList.get(i)+"/"+stdPath+"UP"+"/", excelLabel.Path).contains("All Files/"+Org1FirmName+"/Potential Investment/"+M15FundName3+"/"+ investorList.get(i)+"/"+stdPath+"UP"+"/"))
				appLog.info("successfully found file of "+stdPath8+"UP on folder structure");
			else {
				appLog.error("could not find file of "+stdPath8+"UP");
				sa.assertTrue(false, "could not find file of "+stdPath8+"UP");
			}
		}
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc011_4_deleteFolders() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		String inst1 = ExcelUtils.readData(excelPath, "Institutions", excelLabel.Variable_Name, "M15Institution1", excelLabel.Institutions_Name);
		String stdPath = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, "M15tc011_0_uploadFiles", excelLabel.StandardPath).split(",")[0];
		String msg=null;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M15FundName3)) {
				stdPath += "UP";
				
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath,inst1 , null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					if (click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 30), "manage folder icon", action.SCROLLANDBOOLEAN)) {
						String stdId = fp.getCreatedFolderIdFullTraverse(stdPath, PageName.ManageFolderPopUp, 30);
						if (fp.clickOnDeleteFolderButton(stdId)) {
							if(click(driver, fp.getFolderDeleteYesBtn(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20), "delete yess button", action.BOOLEAN)) {
								if (isAlertPresent(driver))  {
									msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
									switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);

									if(msg.contains("email") && msg.contains("progress") && msg.contains("request")){
										appLog.info("alert for reading email is successfully found");
										for(int j = 0 ; j <= 100 ; j++)
											if(EmailLib.mailReceived(gmailUserName, gmailPassword, "noreply@navatargroup.com", CRMUser1EmailID, "request update","Please refresh your page and continue using Navatar Investor" )){
												System.out.println("\n\n\nFound\n\n\n");
												break;
											} else {
												System.out.println("Mail is not found, Will Check for mail again.");
												if(j>=100){
													appLog.error("Mail is not received.");
													BaseLib.sa.assertTrue(false,"Mail is not received ");
												}
											}
									} else {
										appLog.info("Unexpected alert found with message: "+msg);
									}
								}
							}
							else {
								appLog.error("delete yes button is not clickable");
								sa.assertTrue(false, "delete yes button is not clickable");
							}
						}
						else {
							appLog.error("delete folder button is not clickable");
							sa.assertTrue(false, "delete folder button is not clickable");
						}
					}
					else {
						appLog.error("could not click on manage folders icon");
					}
				}
			}else {
				appLog.error("created fund "+M15FundName3+" is not visible so cannot delete folder.");
				sa.assertTrue(false, "created fund "+M15FundName3+" is not visible so cannot delete folder.");
			}
	}else {
		appLog.error("Not able to click on fund tab so cannot delete folders");
		sa.assertTrue(false, "Not able to click on fund tab so cannot delete folders");
	}
		switchToDefaultContent(driver);	
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc011_5_verifydeletedFolder() {
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		String stdPath8 = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, "M15tc011_0_uploadFiles", excelLabel.StandardPath).split(",")[0];
		String stdPath9 = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, "M15tc011_0_uploadFiles", excelLabel.StandardPath).split(",")[2];
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName3, Workspace.FundraisingWorkspace, 30)){
			appLog.info("Successfully downloaded report");
		} else {
			appLog.error("Not able to download.");
			sa.assertTrue(false,"Not able to download report.");
		}
		
		List<String> investorList = CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
		for (int i = 0;i<investorList.size();i++) {
			System.err.println("All Files/"+Org1FirmName+"/Potential Investment/"+M15FundName3+"/"+ investorList.get(i)+"/"+stdPath8+"/");
			if (ExcelUtils.readData(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", excelLabel.Path, "All Files/"+Org1FirmName+"/Potential Investment/"+M15FundName3+"/"+ investorList.get(i)+"/"+stdPath8+"/", excelLabel.Path).contains("All Files/"+Org1FirmName+"/Potential Investment/"+M15FundName3+"/"+ investorList.get(i)+"/"+stdPath8+"/")||ExcelUtils.readData(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", excelLabel.Path, "All Files/"+Org1FirmName+"/Potential Investment/"+M15FundName3+"/"+ investorList.get(i)+"/"+stdPath8+"/"+stdPath9+"/", excelLabel.Path).contains("All Files/"+Org1FirmName+"/Potential Investment/"+M15FundName3+"/"+ investorList.get(i)+"/"+stdPath8+"/"+stdPath9+"/")) {
				appLog.error("found "+stdPath8+" even after deleting");
				sa.assertTrue(false, "found "+stdPath8+" even after deleting");
			}else {
				appLog.info(stdPath8+" and "+stdPath9+" is successfully removed from all institutions");
			}
		}
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc012_1_VerifyAdditionOfFolders() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		String inst1 = ExcelUtils.readData(excelPath, "Institutions", excelLabel.Variable_Name, "M15Institution1", excelLabel.Institutions_Name);
		String stdPath = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath).split(",")[0];
		String stdPath7 = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath).split(",")[1];
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M15FundName3)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (click(driver,fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 30), "fundraising workspace", action.SCROLLANDBOOLEAN)) {
					String id =bp.getCreatedFolderIdFullTraverse(stdPath, PageName.FundsPage, 30);
					if (bp.clickOnAddFolderButton(id)) {
						if (sendKeys(driver, fp.getChildFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30), stdPath7+"sub", "sub folder text box", action.BOOLEAN)) {
							if (click(driver, fp.getAddFolderChildSaveButton(Workspace.FundraisingWorkspace, 30), "save button", action.BOOLEAN)) {
								
								//Alert Message accept Message and mail check
								if(isAlertPresent(driver)){
									String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
									switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
									if(msg.equalsIgnoreCase(FundsPageErrorMessage.YourRequestInProgressErrorMsg1)){
										appLog.info(msg+" :Message is verfiied successfully.");
									} else {
										appLog.error("Progress Message is not verified.Expexcted: "+FundsPageErrorMessage.YourRequestInProgressErrorMsg1+"\tActual: "+msg);
										sa.assertTrue(false,"Progress Message is not verified.Expexcted: "+FundsPageErrorMessage.YourRequestInProgressErrorMsg1+"\tActual: "+msg);
									}
									if(msg.contains("email") && msg.contains("progress") && msg.contains("request")){
										for(int j = 0 ; j <= 100 ; j++)
											if(EmailLib.mailReceived(gmailUserName, gmailPassword, "noreply@navatargroup.com", CRMUser1EmailID, "request update", "Processing request for "+stdPath7+"sub of "+M15FundName3+" has been completed successfully")){
												System.out.println("\n\n\nFound\n\n\n");
												break;
											} else {
												System.out.println("Mail is not found, Will Check for mail again.");
												if(j>=100){
													appLog.error("Mail is not received.");
													sa.assertTrue(false,"Mail is not received for Folder creation : "+stdPath7+"sub");
													
												}
											}
										click(driver, fp.getManageFolderCrossIcon(Workspace.FundraisingWorkspace, 30), "manage folders cross icon", action.BOOLEAN);
									} else {
										appLog.info("Unexpected alert found with message: "+msg);
									
									}
								}else {
									click(driver, fp.getManageFolderCrossIcon(Workspace.FundraisingWorkspace, 30), "manage folders cross icon", action.BOOLEAN);
									
								}
								if (fp.verifyFolderPathdummy(stdPath+"/"+stdPath7+"sub", inst1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
									appLog.info("successfully found standard 7 sub folder");
								}
								else {
									appLog.error("std 7 sub folder is not created");
									sa.assertTrue(false, "std 7 sub folder is not created");
								}
							}else {
								appLog.error("save button is not clickable");
								sa.assertTrue(false, "save button is not clickable");
							}
						}else {
							appLog.error("text box for folder name is not visible");
							sa.assertTrue(false, "text box for folder name is not visible");
						}
					}else {
						appLog.error("Not able to click on add folder button so cannot create folder ");
						sa.assertTrue(false, "Not able to click on add folder button so cannot create folder ");
					}
				}else {
					appLog.error("Not able to click on manage folder icon so cannot create folder");
					sa.assertTrue(false, "Not able to click on manage folder icon so cannot create folder");
				}
			}else {
				appLog.error("Not able to click on create fund so cannot create folder");
				sa.assertTrue(false, "Not able to click on create fund so cannot create folder");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot create folder");
			sa.assertTrue(false, "Not able to click on fund tab so cannot create folder");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc012_2_verifyAdditionOfFolders() {
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		String stdPath = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, "M15tc012_1_VerifyAdditionOfFolders", excelLabel.StandardPath).split(",")[0];
		String stdPath7 = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, "M15tc012_1_VerifyAdditionOfFolders", excelLabel.StandardPath).split(",")[1];
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName3, Workspace.FundraisingWorkspace, 30)){
			appLog.info("Successfully downloaded report");
		} else {
			appLog.error("Not able to download.");
		}
		List<String> investorList = CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
		//List<String> l = getValueBasedOnLabel(System.getProperty("user.dir")+"\\DownloadedFiles\\"+"folder_tree_run_on_11-30-18__3-52-41-AM.xlsx", "Sheet1", excelLabel.Path, 0);
		//List<String> l = getValueBasedOnLabel(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", excelLabel.Path, 0);
		for (int i = 0;i<investorList.size();i++) {
			System.err.println("All Files/"+Org1FirmName+"/Potential Investment/"+M15FundName3+"/"+ investorList.get(i)+"/"+stdPath+"/"+stdPath7+"sub"+"/");
			if (ExcelUtils.readData(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", excelLabel.Path, "All Files/"+Org1FirmName+"/Potential Investment/"+M15FundName3+"/"+ investorList.get(i)+"/"+stdPath+"/"+stdPath7+"sub"+"/", excelLabel.Path).contains("All Files/"+Org1FirmName+"/Potential Investment/"+M15FundName3+"/"+ investorList.get(i)+"/"+stdPath+"/"+stdPath7+"sub"+"/"))
				
				appLog.info("successfully found "+stdPath7+"sub on folder structure");
			else {
				appLog.error("could not find "+stdPath7+"sub");
				sa.assertTrue(false, "could not find "+stdPath7+"sub");
			}
		}
		sa.assertAll();
	}
 
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc015_Invite1KContact(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view.");
				if(fp.clickOnInstituionFolder(M15Institution1, Workspace.FundraisingWorkspace, 30)){
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "Contact access icon", action.BOOLEAN)){
						if(fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)){
							List<WebElement> ele = FindElements(driver, "//a[contains(@href,'@gmail.com')]/../preceding-sibling::span//input", "Contacts");
							if(ele.size()==201){
								appLog.info("Contacts are verified.");
								if(fp.selectDeselectContactFromContactAccess(null, SelectDeselect.Select, AllOr1By1.All, null, Workspace.FundraisingWorkspace, 30).isEmpty()){
									if(click(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), "Add selected contact button", action.BOOLEAN)){
										if(isAlertPresent(driver)){
											String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
											switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
											if(msg.equalsIgnoreCase(FundsPageErrorMessage.CannotAddMoreThan200ContactErrorMesssage.toString())){
												appLog.info("Message is successfully verified.");
											} else {
												appLog.error(FundsPageErrorMessage.CannotAddMoreThan200ContactErrorMesssage.toString()+" error message is not verified. Expected: "+FundsPageErrorMessage.CannotAddMoreThan200ContactErrorMesssage+"\tActual: "+msg);
												sa.assertTrue(false,"Error message is not verified. Expected: "+FundsPageErrorMessage.CannotAddMoreThan200ContactErrorMesssage+"\tActual: "+msg);
											}
											List<String> contact = new ArrayList<String>();
											contact.add(M15Contact201EmailId);
											if(fp.selectDeselectContactFromContactAccess(contact, SelectDeselect.Deselect, AllOr1By1.OneByOne, null, Workspace.FundraisingWorkspace, 30).isEmpty()){
												appLog.info("Successfully deselected contact '"+M15Contact201EmailId+"'.");
											} else {
												appLog.error("Not able to deselect contact '"+M15Contact201EmailId+"'.");
												sa.assertTrue(false,"Not able to deselect contact '"+M15Contact201EmailId+"'.");
											}
										} else {
											appLog.error(FundsPageErrorMessage.CannotAddMoreThan200ContactErrorMesssage+" error message is not poping up.");
											sa.assertTrue(false,FundsPageErrorMessage.CannotAddMoreThan200ContactErrorMesssage+" error message is not poping up.");
										}
									} else {
										appLog.error("Not able to click on add selected contact button, so cannot verify 'cannot add more than 200 contact at a time' error message.");
										sa.assertTrue(false,"Not able to click on add selected contact button, so cannot verify 'cannot add more than 200 contact at a time' error message.");
									}
								} else {
									appLog.error("Not able to select all contact from the list.");
									sa.assertTrue(false,"Not able to select all contact from the list.");
								}
							} else {
								appLog.error("Count of contacts of '"+M15Institution1+"' is not verified. Expected: 201\tActual: "+ele.size());
								appLog.error("Total contacts are not 201, So will not be able to verify kindly select 200 contacts error message.");
								sa.assertTrue(false,"Count of contacts of '"+M15Institution1+"' is not verified. Expected: 201\tActual: "+ele.size());
							}
							if(click(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), "Add selected contact button", action.BOOLEAN)){
								if(fp.clickOnContactAccessApplyButton(M15Institution1, CRMUser1EmailID, Workspace.FundraisingWorkspace, 30)){
									if(fp.selectDeselectContactFromContactAccess(null, SelectDeselect.Select, AllOr1By1.All, M15Institution2, Workspace.FundraisingWorkspace, 30).isEmpty()){
										appLog.info("Successfully selected contacts of institution '"+M15Institution2+"'.");
										if(click(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), "Add selected contact button", action.BOOLEAN)){
											if(fp.clickOnContactAccessApplyButton(M15Institution1, CRMUser1EmailID, Workspace.FundraisingWorkspace, 30)){
												if(fp.selectDeselectContactFromContactAccess(null, SelectDeselect.Select, AllOr1By1.All, M15Institution3, Workspace.FundraisingWorkspace, 30).isEmpty()){
													appLog.info("Successfully selected contacts of institution '"+M15Institution3+"'.");
													if(click(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), "Add selected contact button", action.BOOLEAN)){
														if(fp.clickOnContactAccessApplyButton(M15Institution1, CRMUser1EmailID, Workspace.FundraisingWorkspace, 30)){
															if(fp.selectDeselectContactFromContactAccess(null, SelectDeselect.Select, AllOr1By1.All, M15Institution4, Workspace.FundraisingWorkspace, 30).isEmpty()){
																appLog.info("Successfully selected contacts of institution '"+M15Institution4+"'.");
																if(click(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), "Add selected contact button", action.BOOLEAN)){
																	if(fp.clickOnContactAccessApplyButton(M15Institution1, CRMUser1EmailID, Workspace.FundraisingWorkspace, 30)){
																		if(fp.selectDeselectContactFromContactAccess(null, SelectDeselect.Select, AllOr1By1.All, M15Institution5, Workspace.FundraisingWorkspace, 30).isEmpty()){
																			appLog.info("Successfully selected contacts of institution '"+M15Institution5+"'.");
																			if(click(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), "Add selected contact button", action.BOOLEAN)){
																				if(fp.clickOnContactAccessApplyButton(M15Institution1, CRMUser1EmailID, Workspace.FundraisingWorkspace, 30)){
																					appLog.info("Successfully provided access to 1K Contacts");
																					ele = FindElements(driver, "//span[contains(@id,'grid11_DealDetailBWFR-cell-3')]/a", "Selected contact email ids");
																					if(ele.size()==1000){
																						appLog.info("All the contacts have successfully been provided access.");
																					} else {
																						appLog.error("All the contacts who have access are not present in the selected grid. Expected: 1000\tActual: "+ele.size());
																						sa.assertTrue(false,"All the contacts who have access are not present in the selected grid. Expected: 1000\tActual: "+ele.size());
																					}
																					
																				} else {
																					appLog.error("Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution4+"'.");
																					sa.assertTrue(false,"Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution4+"'.");
																				}
																			} else {
																				appLog.error("Not able to click on add selected contact button, so cannot invite contacts.");
																				sa.assertTrue(false,"Not able to click on add selected contact button, so cannot invite contacts.");
																			}
																		} else {
																			appLog.error("Not able to select contacts of institution '"+M15Institution5+"'.");
																			sa.assertTrue(false,"Not able to select contacts of institution '"+M15Institution5+"'.");
																		}
																	} else {
																		appLog.error("Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution4+"'.");
																		sa.assertTrue(false,"Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution4+"'.");
																	}
																} else {
																	appLog.error("Not able to click on add selected contact button, so cannot invite contacts.");
																	sa.assertTrue(false,"Not able to click on add selected contact button, so cannot invite contacts.");
																}
															} else {
																appLog.error("Not able to select contacts of institution '"+M15Institution4+"'.");
																sa.assertTrue(false,"Not able to select contacts of institution '"+M15Institution4+"'.");
															}
														} else {
															appLog.error("Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution3+"'.");
															sa.assertTrue(false,"Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution3+"'.");
														}
													} else {
														appLog.error("Not able to click on add selected contact button, so cannot invite contacts.");
														sa.assertTrue(false,"Not able to click on add selected contact button, so cannot invite contacts.");
													}
												} else {
													appLog.error("Not able to select contacts of institution '"+M15Institution3+"'.");
													sa.assertTrue(false,"Not able to select contacts of institution '"+M15Institution3+"'.");
												}
												
											} else {
												appLog.error("Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution2+"'.");
												sa.assertTrue(false,"Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution2+"'.");
											}
										} else {
											appLog.error("Not able to click on add selected contact button, so cannot invite contacts.");
											sa.assertTrue(false,"Not able to click on add selected contact button, so cannot invite contacts.");
										}
									} else {
										appLog.error("Not able to select contacts of institution '"+M15Institution2+"'.");
										sa.assertTrue(false,"Not able to select contacts of institution '"+M15Institution2+"'.");
									}
								} else {
									appLog.error("Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution1+"'.");
									sa.assertTrue(false,"Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution1+"'.");
								}
							} else {
								appLog.error("Not able to click on add selected contact button, so cannot invite contacts.");
								sa.assertTrue(false,"Not able to click on add selected contact button, so cannot invite contacts.");
							}
							
							
						} else {
							appLog.error("Cannot expand select contact area, So cannot provide contact access to 1k contact.");
							sa.assertTrue(false,"Cannot expand select contact area, So cannot provide contact access to 1k contact.");
						}
					} else {
						appLog.error("Contact access icon cannot be clicked, So cannot invite 1k contacts.");
						sa.assertTrue(false,"Contact access icon cannot be clicked, So cannot invite 1k contacts.");
					}
				} else {
					appLog.error(M15Institution1+" institution folder cannot be clicked, So cannot provide access to 1k contacts");
					sa.assertTrue(false,M15Institution1+" institution folder cannot be clicked, So cannot provide access to 1k contacts");
				}
			} else {
				appLog.error(M15FundName1 + " fund is not present in the list, So cannot invite 1K contacts.");
				sa.assertTrue(false,M15FundName1 + " fund is not present in the list, So cannot invite 1K contacts.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot invite 1K contacts.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot invite 1K contacts.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc016_sendInvitationMailTo200Contacts(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view.");
				List<WebElement> allTheContactsInGrid = null;
				if(click(driver, fp.getmanageEmails(Workspace.FundraisingWorkspace, 30), "Manage email", action.BOOLEAN)){
					allTheContactsInGrid = FindElements(driver, "//span[@class='aw-item-template aw-templates-cell aw-grid-cell aw-column-2 aw-cells-normal ']//a[contains(@href,'mailto:')]", "Contacts In Grid");
					if(allTheContactsInGrid.size()==1000){
						appLog.info("All the contacts are present in the grid.");
					} else {
						appLog.error("All the contacts are not present in the grid. Expected: 1000\tActual: "+allTheContactsInGrid.size());
						sa.assertTrue(false,"All the contacts are not present in the grid. Expected: 1000\tActual: "+allTheContactsInGrid.size());
					}
					WebElement ele = FindElement(driver, "//input[@class='checkall']", "Manage email select all check box", action.BOOLEAN, 30);
					if(click(driver, ele, "Check all Check Box", action.BOOLEAN)){
						if(click(driver, fp.getmanageEmailsendBtn(30), "manage email send button", action.BOOLEAN)){
							if(isAlertPresent(driver)){
								String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
								switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
								if(msg.equalsIgnoreCase(FundsPageErrorMessage.ManageEmailsMailSendLimitErrorMsg)){
									appLog.info("Error message is verified successfully.");
								} else {
									appLog.error("Error message is not verified. Expected: "+FundsPageErrorMessage.ManageEmailsMailSendLimitErrorMsg+"\tActual: "+msg);
									sa.assertTrue(false,"Error message is not verified. Expected: "+FundsPageErrorMessage.ManageEmailsMailSendLimitErrorMsg+"\tActual: "+msg);
								}
							} else {
								appLog.error("Error message is not poping up, while sending mail to 200+ contacts at a time.");
								sa.assertTrue(false,"Error message is not poping up, while sending mail to 200+ contacts at a time.");
							}
						} else {
							appLog.error("Manage email send button cannot be clicked, So cannot check error message.");
							sa.assertTrue(false,"Manage email send button cannot be clicked, So cannot check error message.");
						}
						if(sendKeys(driver, fp.getManageEmailSearchTextBox(30), M15Institution1, "Search box", action.BOOLEAN)){
							if(click(driver, fp.getManageEmailSearchBtn(30), "Seacrh button", action.BOOLEAN)){
								ele = FindElement(driver, "//input[@class='checkall']", "Manage email select all check box", action.BOOLEAN, 30);
								if(click(driver, ele, "Check all check box", action.SCROLLANDBOOLEAN)){
									if(click(driver, fp.getmanageEmailsendBtn(30), "manage email send button", action.BOOLEAN)){
										if(click(driver, fp.getManageEmailSendInvitationConfirmationYesBtn(30), "Confirmation pop up yes button", action.BOOLEAN)){
											appLog.info("Successfully sent mails to the contacts.");
											for(int i = 1; i < 201; i++){
												String contactEmail = ExcelUtils.readData(excelPath, "Contacts", excelLabel.Variable_Name, "BulkContact"+i, excelLabel.Contact_EmailId);
												if(EmailLib.mailReceived(gmailUserName, gmailPassword, CRMUser1EmailID, contactEmail, "Invitation from "+Org1FirmName, "You have been granted access to Potential investments of "+M15FundName1+" by "+Org1FirmName+".")){
													appLog.info("Mail successfully received to "+contactEmail);
												} else {
													appLog.error("Mail is not received for to contact '"+contactEmail+"'.");
													sa.assertTrue(false,"Mail is not received for to contact '"+contactEmail+"'.");
												}
											}
										} else {
											appLog.error("Not able to send mail to the contacts.");
											sa.assertTrue(false,"Not able to send mail to the contacts.");
										}
									} else {
										appLog.error("Send button cannot be clicked, So cannot send the invitation mail.");
										sa.assertTrue(false,"Send button cannot be clicked, So cannot send the invitation mail.");
									}
								} else {
									appLog.error("check all check box cannot be clicked, So cannnot contiue with the testcase.");
									sa.assertTrue(false,"check all check box cannot be clicked, So cannnot contiue with the testcase.");
								}
							} else {
								appLog.error("Not able to search the contacts of Institute '"+M15Institution1+"', So cannot continue with the testcase.");
								sa.assertTrue(false,"Not able to search the contacts of Institute '"+M15Institution1+"', So cannot continue with the testcase.");
							}
						} else {
							appLog.error("Not able to search the contacts of Institute '"+M15Institution1+"', So cannot continue with the testcase.");
							sa.assertTrue(false,"Not able to search the contacts of Institute '"+M15Institution1+"', So cannot continue with the testcase.");
						}
					} else {
						appLog.error("Check all check box cannot be clicked, So cannot check error message.");
						sa.assertTrue(false,"Check all check box cannot be clicked, So cannot check error message.");
					}
				} else {
					appLog.error("Manage email icon cannot be clicked, So cannot continue with the tc.");
					sa.assertTrue(false,"Manage email icon cannot be clicked, So cannot continue with the tc.");
				}
			} else {
				appLog.error("Fund is not found, So cannot continue with the testcase.");
				sa.assertTrue(false,"Fund is not found, So cannot continue with the testcase.");
			}
		} else {
			appLog.error("Cannot click on funds tab, So cannot continue with the testcase.");
			sa.assertTrue(false,"Cannot click on funds tab, So cannot continue with the testcase.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc017_RegisterConact1(){
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		FundsPageBusinessLayer fp=new FundsPageBusinessLayer(driver);
		boolean flag = false;
		if (ExcelUtils.readData(excelPath, "Contacts", excelLabel.Variable_Name,"BulkContact1", excelLabel.Registered).equalsIgnoreCase("Not Registered")) {
			try {
				investorRegLink = new EmailLib().getInvestorRegLink("InvitationMail",gmailUserName,gmailPassword,CRMUser1EmailID,M15C1EmailId);
				appLog.info("Investor Registration Link: " + investorRegLink);
			} catch (InterruptedException e) {
				appLog.info("Invitation mail not found.");
				e.printStackTrace();
			}
			if (investorRegLink != null) {
				driver.get(investorRegLink);
				if (bp.targetRegistration(M15CFN1, M15CLN1, M15C1EmailId, M15Institution1,
						adminPassword)) {
					appLog.info(
							"Investor is registered successfully: " + M15CFN1 + " " + M15CLN1);
					flag = true;
					ExcelUtils.writeData("Registered", "Contacts", excelLabel.Variable_Name, "BulkContact1", excelLabel.Registered);
				} else {
					driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
					appLog.info("Register Target URL through Direct URL..");
					if (bp.targetRegistration(M15CFN1, M15CLN1, M15C1EmailId,
							M15Institution1, adminPassword)) {
						appLog.info("Investor is registered successfully: " + M15CFN1 + " "
								+ M15CLN1);
						flag = true;
						ExcelUtils.writeData("Registered", "Contacts", excelLabel.Variable_Name, "BulkContact1", excelLabel.Registered);
					} else {
						appLog.error("Investor is not Registered through Invited Link: " + M15CFN1 + " "
								+ M15CLN1);
						sa.assertTrue(false, "Investor is not Registered through Invited Link: " + M15CFN1
								+ " " + M15CLN1);
					}
				}
			} else {
				driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
				appLog.info("Register Target URL through Direct URL..");
				if (bp.targetRegistration(M15CFN1, M15CLN1, M15C1EmailId,
						M15Institution1, adminPassword)) {
					appLog.info("Investor is registered successfully: " + M15CFN1 + " "
							+ M15CLN1);
					flag = true;
					ExcelUtils.writeData("Registered", "Contacts", excelLabel.Variable_Name, "BulkContact1", excelLabel.Registered);
				} else {
					appLog.error("Investor is not Registered through Invited Link: " + M15CFN1 + " "
							+ M15CLN1);
					sa.assertTrue(false, "Investor is not Registered through Invited Link: " + M15CFN1
							+ " " + M15CLN1);
				}
			}		
		}else {
			appLog.info("investor "+M15CFN1+" "+M15CLN1+" is already Registered.");
			sa.assertTrue(false, "investor "+M15CFN1+" "+M15CLN1+" is already Registered.");
			if(lp.investorLogin(M15C1EmailId, adminPassword)){
				flag = true;
			} else {
				sa.assertTrue(false, "Investor password is wrong so cannot login");
				flag = false;
			}
		}
		if(flag){
			if(ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)){
				if(fp.verifyFolderPathdummy("", M15Institution1, null, null, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)){
					appLog.info("Folder structure is verified."); 
				} else {
					appLog.error("Folder strucuture is not verified.");
					sa.assertTrue(false,"Folder strucuture is not verified.");
				}
//				if(ifp.clickOnBulkDownload()){
//					
//				} else {
//					appLog.error("Not able to click on bulk download icon, So cannot verify folder structure on bulk download window.");
//					sa.assertTrue(false,"Not able to click on bulk download icon, So cannot verify folder structure on bulk download window.");
//				}
			} else {
				appLog.error("Potential invesstment tab cannot be clicked, So cannot continue with the testcase.");
				sa.assertTrue(false,"Potential invesstment tab cannot be clicked, So cannot continue with the testcase.");
			}
		}
		lp.getInvestorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M15tc018_UploadBigSizeFilesInCommon(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		CommonLib compare = new CommonLib();
		String commonFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "workspace view");
				if(fp.verifyFolderPathdummy(commonFolderPath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
					if(click(driver, fp.getUploadIcon(Workspace.FundraisingWorkspace, 30), "Upload Icon", action.BOOLEAN)){
						String parentWin = switchOnWindow(driver);
						if(parentWin!=null){
							if(fp.dragDropFiles("UploadFiles\\BulkFiles\\Common_100PlusFiles", "DropLoc.JPG")){
								ThreadSleep(10000);
								appLog.info("Successfully dropped the files.");
								System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//								if(isAlertPresent(driver)){
//									String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
//									switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
//									String errorMsg=FundsPageErrorMessage.FilesUploadedAtATimeLimitErrorMsg;
//									if(msg.equalsIgnoreCase(errorMsg)){
//										appLog.info(errorMsg+" : Error message is verified.");
//									} else {
//										appLog.error("Error message os not verified. Expected: "+errorMsg+"\tActual: "+msg);
//										sa.assertTrue(false,"Error message os not verified. Expected: "+errorMsg+"\tActual: "+msg);
//									}
//								} else {
//									appLog.error("It is allowing to upload more than 100 files at a time.");
//									sa.assertTrue(false,"It is allowing to upload more than 100 files at a time.");
//								}
							} else {
								appLog.error("Drag and drop is not successful, So cannot verify more than 100 files error message.");
								sa.assertTrue(false,"Drag and drop is not successful, So cannot verify more than 100 files error message.");
							}
							driver.close();
							driver.switchTo().window(parentWin);
						} else {
							appLog.error("Upload window is not opening after clicking on upload button, So cannot verify more than 100 files are not allowed error message.");
							sa.assertTrue(false,"Upload window is not opening after clicking on upload button, So cannot verify more than 100 files are not allowed error message.");
						}
					} else {
						appLog.error("Upload button cannot be clicked, So cannot verify 100 file drop error message.");
						sa.assertTrue(false,"Upload button cannot be clicked, So cannot verify 101 file drop error message.");
					}
				} else {
					appLog.error(commonFolderPath+" Folder is not present, so cannot verify 100 file drop error message.");
					sa.assertTrue(false,commonFolderPath+" Folder is not present, so cannot verify 100 file drop error message.");
				}
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if(fp.verifyFolderPathdummy(commonFolderPath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
					if(click(driver, fp.getUploadIcon(Workspace.FundraisingWorkspace, 30), "Upload Icon", action.BOOLEAN)){
						String parentWin = switchOnWindow(driver);
						if(parentWin!=null){
							if(fp.dragDropFiles("UploadFiles\\BulkFiles\\Common_BigSizeFiles", "DropLoc.JPG")){
								ThreadSleep(15000);
								List<String> droppedFileNames = new ArrayList<String>();
//								List<WebElement> droppedFiles = driver.findElements(By.xpath("//span[@class='File']/following-sibling::b"));
								List<WebElement> droppedFiles = FindElements(driver, "//span[@class='File']/following-sibling::b", "Files in upload win");
								if(!droppedFiles.isEmpty()){
									for(int i = 0; i < droppedFiles.size(); i++){
										droppedFileNames.add(getText(driver, droppedFiles.get(i), "Dropped Files", action.BOOLEAN).trim());
									}
									Collections.sort(droppedFileNames,compare);
									String previousuploadedFiles = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
									String newlyUploadedFiles = createStringOutOfList(droppedFileNames);
									if(previousuploadedFiles!=null && !previousuploadedFiles.isEmpty()){
										newlyUploadedFiles = previousuploadedFiles+"<break>"+newlyUploadedFiles;
									}
									if (ExcelUtils.writeData(excelPath, newlyUploadedFiles, "filePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon)) {
										appLog.info("written uploaded file data to excel");
									}
									else {
										appLog.error("could not write uploaded files information to excel");
									}
								} else {
									appLog.error("0 files are dropped.");
									BaseLib.sa.assertTrue(false,"0 files are dropped.");
								}
								if(click(driver, fp.getUploadSaveButton(30), "Upload Save Button", action.BOOLEAN)){
									if (isAlertPresent(driver)) {
										String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
										switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
										if (msg.trim().equals(FundsPageErrorMessage.DocumentUploadSuccessMsg)) {
											appLog.info("successfully found uploaded document message");
										}
									}else {
										appLog.info("document is not uploaded.. we will check from email");
										String msg = fp.getUploadBulkDelayedMessage(180).getText();
										if(msg.contains("email") && msg.contains("Document") && msg.contains("upload")){
											for(int j = 0 ; j <= 100 ; j++)
												if(EmailLib.mailReceived(gmailUserName, gmailPassword, "noreply@navatarinvestor.com", CRMUser1EmailID, "File upload status","file upload process has been completed" )){
													System.out.println("\n\n\nFound\n\n\n");
													break;
												} else {
													System.out.println("Mail is not found, Will Check for mail again.");
													if(j>=100){
														appLog.error("Mail is not received.");
														BaseLib.sa.assertTrue(false,"Mail is not received ");
													}
												}
										}else {
											appLog.error("msg warming on web page is not related to reading email regarding upload");
											sa.assertTrue(false, "msg warming on web page is not related to reading email regarding upload");
										}
										if(!driver.getWindowHandle().equalsIgnoreCase(parentWin)){
											driver.close();
										}
									}
								}else {
									appLog.error("upload save button is not clickable");
									sa.assertTrue(false, "upload save button is not clickable");
								}
							} else {
								appLog.error("Drag and drop is not successful, So cannot verify more than 100 files error message.");
								sa.assertTrue(false,"Drag and drop is not successful, So cannot verify more than 100 files error message.");
							}
							driver.switchTo().window(parentWin);
							switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
							if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30), "Refresh BUtton", action.BOOLEAN)){
								List<WebElement> filesInGridEle = fp.getContentGridDocNameListOnBasisOfTitle(Workspace.FundraisingWorkspace, PageName.FundsPage);
								List<String> filesInGrid = new ArrayList<String>();
								for (WebElement webElement : filesInGridEle) {
									filesInGrid.add(getText(driver, webElement, "", action.BOOLEAN));
								}
								boolean flag = true;
								String filesInExcel = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
								for(int i = 0; i < filesInExcel.split("<break>").length; i++){
									String fileName = filesInExcel.split("<break>")[i];
									for(int j = 0; j < filesInGrid.size(); j++){
										if(fileName.equalsIgnoreCase(filesInGrid.get(j))){
											appLog.info(fileName+" file is successfully uploaded.");
											flag = true;
											break;
										} else {
											flag = false;
										}
									}
									if(!flag){
										appLog.error(fileName+" file is  not uploaded.");
										sa.assertTrue(false,fileName+" file is  not uploaded.");
									}
								}
							} else {
								appLog.error("Refresh buttona cannot be clicked, So cannot verify file in content grid.");
								sa.assertTrue(false,"Refresh buttona cannot be clicked, So cannot verify file in content grid.");
							}
						} else {
							appLog.error("Upload window is not opening after clicking on upload button, So cannot verify more than 100 files are not allowed error message.");
							sa.assertTrue(false,"Upload window is not opening after clicking on upload button, So cannot verify more than 100 files are not allowed error message.");
						}
					} else {
						appLog.error("Upload button cannot be clicked, So cannot verify 100 file drop error message.");
						sa.assertTrue(false,"Upload button cannot be clicked, So cannot verify 101 file drop error message.");
					}
				} else {
					appLog.error(commonFolderPath+" Folder is not present, so cannot verify 100 file drop error message.");
					sa.assertTrue(false,commonFolderPath+" Folder is not present, so cannot verify 100 file drop error message.");
				}
				
			} else {
				appLog.error(M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot upload big size files in common folder.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot upload big size files in common folder.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc019_UpdateBigSizeFilesInCommon(String environment, String mode){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String commonFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
//				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
//				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "workspace view");
				if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, commonFolderPath, null, "UploadFiles\\BulkFiles\\Common_BigSizeFilesUpdate", UploadFileActions.Update, UploadFileActions.Update, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
					appLog.info("Files updated successfully.");
				} else {
					appLog.error("Files are not updated.");
					sa.assertTrue(false,"Files are not updated.");
				}
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30), "Refresh BUtton", action.BOOLEAN)){
					List<WebElement> filesInGridEle = fp.getContentGridDocNameListOnBasisOfTitle(Workspace.FundraisingWorkspace, PageName.FundsPage);
					List<String> filesInGrid = new ArrayList<String>();
					for (WebElement webElement : filesInGridEle) {
						filesInGrid.add(getText(driver, webElement, "", action.BOOLEAN));
					}
					boolean flag = true;
					String filesInExcel = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
					for(int i = 0; i < filesInExcel.split("<break>").length; i++){
						String fileName = filesInExcel.split("<break>")[i];
						for(int j = 0; j < filesInGrid.size(); j++){
							if(fileName.equalsIgnoreCase(filesInGrid.get(j))){
								appLog.info(fileName+" file is successfully uploaded.");
								flag = true;
								break;
							} else {
								flag = false;
							}
						}
						if(!flag){
							appLog.error(fileName+" file is  not uploaded.");
							sa.assertTrue(false,fileName+" file is  not uploaded.");
						}
					}
				} else {
					appLog.error("Refresh buttona cannot be clicked, So cannot verify file in content grid.");
					sa.assertTrue(false,"Refresh buttona cannot be clicked, So cannot verify file in content grid.");
				}
			} else {
				appLog.error(M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot upload big size files in common folder.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot upload big size files in common folder.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc020_UploadBigSizeFilesInInternal(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		CommonLib compare = new CommonLib();
		String InternalFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "workspace view");
				if(fp.verifyFolderPathdummy(InternalFolderPath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
					if(click(driver, fp.getUploadIcon(Workspace.FundraisingWorkspace, 30), "Upload Icon", action.BOOLEAN)){
						String parentWin = switchOnWindow(driver);
						if(parentWin!=null){
							if(fp.dragDropFiles("UploadFiles\\BulkFiles\\Internal_BigSizeFiles", "DropLoc.JPG")){
								ThreadSleep(15000);
								List<String> droppedFileNames = new ArrayList<String>();
//								List<WebElement> droppedFiles = driver.findElements(By.xpath("//span[@class='File']/following-sibling::b"));
								List<WebElement> droppedFiles = FindElements(driver, "//span[@class='File']/following-sibling::b", "Files in upload win");
								if(!droppedFiles.isEmpty()){
									for(int i = 0; i < droppedFiles.size(); i++){
										droppedFileNames.add(getText(driver, droppedFiles.get(i), "Dropped Files", action.BOOLEAN).trim());
									}
									Collections.sort(droppedFileNames,compare);
									String previousuploadedFiles = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileInternal);
									String newlyUploadedFiles = createStringOutOfList(droppedFileNames);
									if(previousuploadedFiles!=null && !previousuploadedFiles.isEmpty()){
										newlyUploadedFiles = previousuploadedFiles+"<break>"+newlyUploadedFiles;
									}
									if (ExcelUtils.writeData(excelPath, newlyUploadedFiles, "filePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileInternal)) {
										appLog.info("written uploaded file data to excel");
									}
									else {
										appLog.error("could not write uploaded files information to excel");
									}
								} else {
									appLog.error("0 files are dropped.");
									BaseLib.sa.assertTrue(false,"0 files are dropped.");
								}
								if(click(driver, fp.getUploadSaveButton(30), "Upload Save Button", action.BOOLEAN)){
									if (isAlertPresent(driver)) {
										String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
										switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
										if (msg.trim().equals(FundsPageErrorMessage.DocumentUploadSuccessMsg)) {
											appLog.info("successfully found uploaded document message");
										}
									}else {
										appLog.info("document is not uploaded.. we will check from email");
										String msg = fp.getUploadBulkDelayedMessage(180).getText();
										if(msg.contains("email") && msg.contains("Document") && msg.contains("upload")){
											for(int j = 0 ; j <= 100 ; j++)
												if(EmailLib.mailReceived(gmailUserName, gmailPassword, "noreply@navatarinvestor.com", CRMUser1EmailID, "File upload status","file upload process has been completed" )){
													System.out.println("\n\n\nFound\n\n\n");
													break;
												} else {
													System.out.println("Mail is not found, Will Check for mail again.");
													if(j>=100){
														appLog.error("Mail is not received.");
														BaseLib.sa.assertTrue(false,"Mail is not received ");
													}
												}
										}else {
											appLog.error("msg warming on web page is not related to reading email regarding upload");
											sa.assertTrue(false, "msg warming on web page is not related to reading email regarding upload");
										}
										if(!driver.getWindowHandle().equalsIgnoreCase(parentWin)){
											driver.close();
										}
									}
								}else {
									appLog.error("upload save button is not clickable");
									sa.assertTrue(false, "upload save button is not clickable");
								}
							} else {
								appLog.error("Drag and drop is not successful, So cannot verify more than 100 files error message.");
								sa.assertTrue(false,"Drag and drop is not successful, So cannot verify more than 100 files error message.");
							}
							driver.switchTo().window(parentWin);
							switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
							if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30), "Refresh BUtton", action.BOOLEAN)){
								List<WebElement> filesInGridEle = fp.getContentGridDocNameListOnBasisOfTitle(Workspace.FundraisingWorkspace, PageName.FundsPage);
								List<String> filesInGrid = new ArrayList<String>();
								for (WebElement webElement : filesInGridEle) {
									filesInGrid.add(getText(driver, webElement, "", action.BOOLEAN));
								}
								boolean flag = true;
								String filesInExcel = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileInternal);
								for(int i = 0; i < filesInExcel.split("<break>").length; i++){
									String fileName = filesInExcel.split("<break>")[i];
									for(int j = 0; j < filesInGrid.size(); j++){
										if(fileName.equalsIgnoreCase(filesInGrid.get(j))){
											appLog.info(fileName+" file is successfully uploaded.");
											flag = true;
											break;
										} else {
											flag = false;
										}
									}
									if(!flag){
										appLog.error(fileName+" file is  not uploaded.");
										sa.assertTrue(false,fileName+" file is  not uploaded.");
									}
								}
							} else {
								appLog.error("Refresh buttona cannot be clicked, So cannot verify file in content grid.");
								sa.assertTrue(false,"Refresh buttona cannot be clicked, So cannot verify file in content grid.");
							}
						} else {
							appLog.error("Upload window is not opening after clicking on upload button, So cannot verify more than 100 files are not allowed error message.");
							sa.assertTrue(false,"Upload window is not opening after clicking on upload button, So cannot verify more than 100 files are not allowed error message.");
						}
					} else {
						appLog.error("Upload button cannot be clicked, So cannot verify 100 file drop error message.");
						sa.assertTrue(false,"Upload button cannot be clicked, So cannot verify 101 file drop error message.");
					}
				} else {
					appLog.error(InternalFolderPath+" Folder is not present, so cannot verify 100 file drop error message.");
					sa.assertTrue(false,InternalFolderPath+" Folder is not present, so cannot verify 100 file drop error message.");
				}
				
			} else {
				appLog.error(M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc021_UpdateBigSizeFilesInInternal(String environment, String mode){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String InternalFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
//				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
//				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "workspace view");
				if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, InternalFolderPath, null, "UploadFiles\\BulkFiles\\Internal_BigSizeFilesUpdate", UploadFileActions.Update, UploadFileActions.Update, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
					appLog.info("Files updated successfully.");
				} else {
					appLog.error("Files are not updated.");
					sa.assertTrue(false,"Files are not updated.");
				}
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30), "Refresh BUtton", action.BOOLEAN)){
					List<WebElement> filesInGridEle = fp.getContentGridDocNameListOnBasisOfTitle(Workspace.FundraisingWorkspace, PageName.FundsPage);
					List<String> filesInGrid = new ArrayList<String>();
					for (WebElement webElement : filesInGridEle) {
						filesInGrid.add(getText(driver, webElement, "", action.BOOLEAN));
					}
					boolean flag = true;
					String filesInExcel = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileInternal);
					for(int i = 0; i < filesInExcel.split("<break>").length; i++){
						String fileName = filesInExcel.split("<break>")[i];
						for(int j = 0; j < filesInGrid.size(); j++){
							if(fileName.equalsIgnoreCase(filesInGrid.get(j))){
								appLog.info(fileName+" file is successfully uploaded.");
								flag = true;
								break;
							} else {
								flag = false;
							}
						}
						if(!flag){
							appLog.error(fileName+" file is  not uploaded.");
							sa.assertTrue(false,fileName+" file is  not uploaded.");
						}
					}
				} else {
					appLog.error("Refresh buttona cannot be clicked, So cannot verify file in content grid.");
					sa.assertTrue(false,"Refresh buttona cannot be clicked, So cannot verify file in content grid.");
				}
			} else {
				appLog.error(M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc022_OnlineImportFilesInCommonFolder(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String CommonFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
//				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
//				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising Workspace View.");
				List<String> files = listFilesForFolder(new java.io.File(System.getProperty("user.dir")+"/UploadFiles/BulkFiles/Common_OnlineImportFiles"));
				String fileName = createStringOutOfList(files);
				if(fp.onlineImportBulk(CRMUser1EmailID, null, null, null, CommonFolderPath, ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.OnlineImportPath), fileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Common, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
					appLog.info("Online import successful.");
					ExcelUtils.writeData(excelPath, fileName, "filePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30), "Refresh BUtton", action.BOOLEAN)){
						List<WebElement> filesInGridEle = fp.getContentGridDocNameListOnBasisOfTitle(Workspace.FundraisingWorkspace, PageName.FundsPage);
						List<String> filesInGrid = new ArrayList<String>();
						for (WebElement webElement : filesInGridEle) {
							filesInGrid.add(getText(driver, webElement, "", action.BOOLEAN));
						}
						boolean flag = true;
						String filesInExcel = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
						for(int i = 0; i < filesInExcel.split("<break>").length; i++){
							String file = filesInExcel.split("<break>")[i];
							for(int j = 0; j < filesInGrid.size(); j++){
								if(file.equalsIgnoreCase(filesInGrid.get(j))){
									appLog.info(file+" file is successfully Imported.");
									flag = true;
									break;
								} else {
									flag = false;
								}
							}
							if(!flag){
								appLog.error(fileName+" file is  not Imported.");
								sa.assertTrue(false,fileName+" file is  not Imported.");
							}
						}
					} else {
						appLog.error("Refresh buttona cannot be clicked, So cannot verify file in content grid.");
						sa.assertTrue(false,"Refresh buttona cannot be clicked, So cannot verify file in content grid.");
					}
				} else {
					appLog.error("Online import is not successful, So we cannot verify the functionality.");
					sa.assertTrue(false,"Online import is not successful, So we cannot verify the functionality.");
				}
			} else {
				appLog.error(M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc023_OnlineImportFilesInCommonFolderUpdate(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String CommonFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
//				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
//				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising Workspace View.");
				List<String> files = listFilesForFolder(new java.io.File(System.getProperty("user.dir")+"/UploadFiles/BulkFiles/Common_OnlineImportFilesUpdate"));
				String fileName = createStringOutOfList(files);
				if(fp.onlineImportBulk(CRMUser1EmailID, null, null, null, CommonFolderPath, ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.OnlineImportPath), fileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPDATE, FolderType.Common, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
					appLog.info("Online import successful.");
					ExcelUtils.writeData(excelPath, fileName, "filePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileCommon);
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30), "Refresh BUtton", action.BOOLEAN)){
						List<WebElement> filesInGridEle = fp.getContentGridDocNameListOnBasisOfTitle(Workspace.FundraisingWorkspace, PageName.FundsPage);
						List<String> filesInGrid = new ArrayList<String>();
						for (WebElement webElement : filesInGridEle) {
							filesInGrid.add(getText(driver, webElement, "", action.BOOLEAN));
						}
						boolean flag = true;
						String filesInExcel = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileCommon);
						for(int i = 0; i < filesInExcel.split("<break>").length; i++){
							String file = filesInExcel.split("<break>")[i];
							for(int j = 0; j < filesInGrid.size(); j++){
								if(file.equalsIgnoreCase(filesInGrid.get(j))){
									appLog.info(file+" file is successfully Imported.");
									flag = true;
									break;
								} else {
									flag = false;
								}
							}
							if(!flag){
								appLog.error(fileName+" file is  not Imported.");
								sa.assertTrue(false,fileName+" file is  not Imported.");
							}
						}
					} else {
						appLog.error("Refresh buttona cannot be clicked, So cannot verify file in content grid.");
						sa.assertTrue(false,"Refresh buttona cannot be clicked, So cannot verify file in content grid.");
					}
				} else {
					appLog.error("Online import is not successful, So we cannot verify the functionality.");
					sa.assertTrue(false,"Online import is not successful, So we cannot verify the functionality.");
				}
			} else {
				appLog.error(M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc024_OnlineImportFilesInInternalFolder(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String InternalFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
//				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
//				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising Workspace View.");
				List<String> files = listFilesForFolder(new java.io.File(System.getProperty("user.dir")+"/UploadFiles/BulkFiles/Internal_OnlineImportFiles"));
				String fileName = createStringOutOfList(files);
				if(fp.onlineImportBulk(CRMUser1EmailID, null, null, null, InternalFolderPath, ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.OnlineImportPath), fileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Internal, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
					appLog.info("Online import successful.");
					ExcelUtils.writeData(excelPath, fileName, "filePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileInternal);
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30), "Refresh BUtton", action.BOOLEAN)){
						List<WebElement> filesInGridEle = fp.getContentGridDocNameListOnBasisOfTitle(Workspace.FundraisingWorkspace, PageName.FundsPage);
						List<String> filesInGrid = new ArrayList<String>();
						for (WebElement webElement : filesInGridEle) {
							filesInGrid.add(getText(driver, webElement, "", action.BOOLEAN));
						}
						boolean flag = true;
						String filesInExcel = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileInternal);
						for(int i = 0; i < filesInExcel.split("<break>").length; i++){
							String file = filesInExcel.split("<break>")[i];
							for(int j = 0; j < filesInGrid.size(); j++){
								if(file.equalsIgnoreCase(filesInGrid.get(j))){
									appLog.info(file+" file is successfully Imported.");
									flag = true;
									break;
								} else {
									flag = false;
								}
							}
							if(!flag){
								appLog.error(fileName+" file is  not Imported.");
								sa.assertTrue(false,fileName+" file is  not Imported.");
							}
						}
					} else {
						appLog.error("Refresh buttona cannot be clicked, So cannot verify file in content grid.");
						sa.assertTrue(false,"Refresh buttona cannot be clicked, So cannot verify file in content grid.");
					}
				} else {
					appLog.error("Online import is not successful, So we cannot verify the functionality.");
					sa.assertTrue(false,"Online import is not successful, So we cannot verify the functionality.");
				}
			} else {
				appLog.error(M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc025_OnlineImportFilesInInternalFolderUpdate(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String InternalFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
//				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
//				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising Workspace View.");
				List<String> files = listFilesForFolder(new java.io.File(System.getProperty("user.dir")+"/UploadFiles/BulkFiles/Internal_OnlineImportFilesUpdate"));
				String fileName = createStringOutOfList(files);
				if(fp.onlineImportBulk(CRMUser1EmailID, null, null, null, InternalFolderPath, ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.OnlineImportPath), fileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPDATE, FolderType.Internal, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
					appLog.info("Online import successful.");
					ExcelUtils.writeData(excelPath, fileName, "filePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileInternal);
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30), "Refresh BUtton", action.BOOLEAN)){
						List<WebElement> filesInGridEle = fp.getContentGridDocNameListOnBasisOfTitle(Workspace.FundraisingWorkspace, PageName.FundsPage);
						List<String> filesInGrid = new ArrayList<String>();
						for (WebElement webElement : filesInGridEle) {
							filesInGrid.add(getText(driver, webElement, "", action.BOOLEAN));
						}
						boolean flag = true;
						String filesInExcel = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileInternal);
						for(int i = 0; i < filesInExcel.split("<break>").length; i++){
							String file = filesInExcel.split("<break>")[i];
							for(int j = 0; j < filesInGrid.size(); j++){
								if(file.equalsIgnoreCase(filesInGrid.get(j))){
									appLog.info(file+" file is successfully Imported.");
									flag = true;
									break;
								} else {
									flag = false;
								}
							}
							if(!flag){
								appLog.error(fileName+" file is  not Imported.");
								sa.assertTrue(false,fileName+" file is  not Imported.");
							}
						}
					} else {
						appLog.error("Refresh buttona cannot be clicked, So cannot verify file in content grid.");
						sa.assertTrue(false,"Refresh buttona cannot be clicked, So cannot verify file in content grid.");
					}
				} else {
					appLog.error("Online import is not successful, So we cannot verify the functionality.");
					sa.assertTrue(false,"Online import is not successful, So we cannot verify the functionality.");
				}
			} else {
				appLog.error(M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M15tc026_0_UploadFileInStandardFolder(String environment, String mode){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String StandardFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				List<String> institutions = getValueBasedOnLabel(excelPath, "Institutions", excelLabel.Institutions_Name, 0);
				String institutionOrLPName = createStringOutOfList(institutions);
				if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID,excelPath, StandardFolderPath, institutionOrLPName, "UploadFiles\\BulkFiles\\Standard_MultipleInstiFile", UploadFileActions.SelectAll, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
					appLog.info("File is uploaded successfully in "+StandardFolderPath);
					
				} else {
					appLog.error("Not able to upload files, So cannot continue the testcase.");
					sa.assertTrue(false,"Not able to upload files, So cannot continue the testcase.");
				}
			} else {
				appLog.error(M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc026_1_verifyUpload() {
		String dependOnTc="M15tc026_0_UploadFileInStandardFolder";
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		List<String> investorList = new ArrayList<String>();
		List<String> downLoadedFolderPathList = new ArrayList<String>();
		List<String> itemNameList = new ArrayList<String>();
		String StandardFolderFile =  ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileStandard);
		String StandardFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, dependOnTc, excelLabel.StandardPath);
		driver.get("https://account.box.com/login");
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName1, Workspace.FundraisingWorkspace, 30)){
			appLog.info("Successfully downloaded report");
		} else {
			appLog.error("Not able to download report, So cannot continue with tc.");
			sa.assertTrue(false,"Not able to download report, So cannot continue with tc.");
		}
		ThreadSleep(7000);
		String s =getFilesNameFromSystemFolder("DownloadedFiles");
		String fileName=null;
		if(s != null) {
			List<String> downloadedfileList=createListOutOfString(s);
			for (int i=0; i<downloadedfileList.size(); i++) {
				if(BoxPageBusinesslayer.fileName.equalsIgnoreCase(downloadedfileList.get(i))) {
					appLog.info("Downloaded File is available in download folder : "+BoxPageBusinesslayer.fileName);
					fileName=BoxPageBusinesslayer.fileName;
					investorList = CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
					String downLoadedFolderPath=ExcelUtils.readAllDataForAColumn(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", 2, false);
					String ItemName = ExcelUtils.readAllDataForAColumn(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", 4, false);
					downLoadedFolderPathList=createListOutOfStringUsingComma(downLoadedFolderPath);
					itemNameList=createListOutOfStringUsingComma(ItemName);
					break;
				}else {
					if(i==downloadedfileList.size()-1) {
						appLog.error(BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
						sa.assertTrue(false, BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot get file from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot get file from downloaded folder");
		}
		
		if(fileName!=null && !fileName.isEmpty()) {
			for (int i = 0;i<investorList.size();i++) {
				String filepath="All Files/"+Org1FirmName+"/Potential Investment/"+M15FundName1+"/"+ investorList.get(i)+"/"+StandardFolderPath+"/"+StandardFolderFile;
				appLog.info("Going to verify path:  "+filepath);
				for(int j=0; j<downLoadedFolderPathList.size(); j++) {
					if (filepath.equalsIgnoreCase(downLoadedFolderPathList.get(j).trim())) {
						appLog.info("successfully found file of "+investorList.get(i)+" on folder structure");
						if(itemNameList.get(j).equalsIgnoreCase(StandardFolderFile)) {
							appLog.info("Item Name is matched for folder path  :"+downLoadedFolderPathList.get(i));
							
							
						}else {
							appLog.error("Item Name is not matched for folder path : "+downLoadedFolderPathList.get(i));
							sa.assertTrue(false, "Item Name is not matched for folder path : "+downLoadedFolderPathList.get(i));
						}
						break;
					}else {
						if(j==downLoadedFolderPathList.size()-1) {
							appLog.error("could not find file of "+investorList.get(i));
							sa.assertTrue(false, "could not find file of "+investorList.get(i));
						}
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot verify upload file name from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot verify upload file name from downloaded folder");
		}
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc026_2_verifyUploadFilesInvestorSide() {
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String StandardFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, "M15tc026_0_UploadFileInStandardFolder", excelLabel.StandardPath);
		String StandardFolderFile = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, "M15tc026_0_UploadFileInStandardFolder", excelLabel.UploadedFileStandard);
		lp.investorLogin(M15C1EmailId, adminPassword);
		if(ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)){
			if(fp.verifyFolderPathdummy(StandardFolderPath, M15Institution1, null, null, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)){
				if(fp.verifyFileinContentGrid(PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, StandardFolderFile)){
					appLog.info(StandardFolderFile+" file is verified.");
				} else {
					appLog.error(StandardFolderFile+" file is not verified.");
					sa.assertTrue(false,StandardFolderFile+" file is not verified.");
					
				}
			} else {
				appLog.error(StandardFolderPath+" folder is not veirfied, So cannot veirfy file upload status.");
				sa.assertTrue(false,StandardFolderPath+" folder is not veirfied, So cannot veirfy file upload status.");
			}
		} else {
			appLog.error("Potential investment cannot be clicked, So cannot verify file upload status at investor side.");
			sa.assertTrue(false,"Potential investment cannot be clicked, So cannot verify file upload status at investor side.");
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M15tc027_0_OnlineImportFileInStandardFolder(String environment, String mode){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String StandardFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				List<String> institutions = getValueBasedOnLabel(excelPath, "Institutions", excelLabel.Institutions_Name, 0);
				String institutionOrLPName = createStringOutOfListUsingComma(institutions);
				List<String> files = listFilesForFolder(new java.io.File(System.getProperty("user.dir")+"\\UploadFiles\\BulkFiles\\Standard_MultipleInstiFile"));
				String fileName = createStringOutOfList(files);
				if(fp.onlineImport(environment, mode, M15Institution1, null, institutionOrLPName, StandardFolderPath, ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.OnlineImportPath), fileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.MultipleInstitute, WorkSpaceAction.UPDATE, FolderType.Standard, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
					lp.CRMlogout();
					ExcelUtils.writeData(excelPath, fileName, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
				
					
				} else {
					appLog.error("Not able to import files, So cannot continue the testcase.");
					sa.assertTrue(false,"Not able to import files, So cannot continue the testcase.");
				}
			} else {
				appLog.error(M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc027_1_verifyOnlineImportfileInBox() {
		String dependOnTc="M15tc027_0_OnlineImportFileInStandardFolder";
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		List<String> investorList = new ArrayList<String>();
		List<String> downLoadedFolderPathList = new ArrayList<String>();
		List<String> itemNameList = new ArrayList<String>();
		String stdPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, dependOnTc, excelLabel.StandardPath);
		List<String> files = listFilesForFolder(new java.io.File(System.getProperty("user.dir")+"\\UploadFiles\\BulkFiles\\Standard_MultipleInstiFile"));
		String importFileName = createStringOutOfListUsingComma(files);

		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName1, Workspace.FundraisingWorkspace, 30)){
			appLog.info("Successfully downloaded report");
		} else {
			appLog.error("Not able to download.");
			sa.assertTrue(false, "Not able to download.");
		}
		ThreadSleep(7000);
		String s =getFilesNameFromSystemFolder("DownloadedFiles");
		String fileName=null;
		if(s != null) {
			List<String> downloadedfileList=createListOutOfString(s);
			for (int i=0; i<downloadedfileList.size(); i++) {
				if(BoxPageBusinesslayer.fileName.equalsIgnoreCase(downloadedfileList.get(i))) {
					appLog.info("Downloaded File is available in download folder : "+BoxPageBusinesslayer.fileName);
					fileName=BoxPageBusinesslayer.fileName;
					investorList = CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
					String downLoadedFolderPath=ExcelUtils.readAllDataForAColumn(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", 2, false);
					String ItemName = ExcelUtils.readAllDataForAColumn(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", 4, false);
					downLoadedFolderPathList=createListOutOfStringUsingComma(downLoadedFolderPath);
					itemNameList=createListOutOfStringUsingComma(ItemName);
					break;
				}else {
					if(i==downloadedfileList.size()-1) {
						appLog.error(BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
						sa.assertTrue(false, BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot get file from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot get file from downloaded folder");
		}
		if(fileName!=null && !fileName.isEmpty()) {
			for (int i = 0;i<investorList.size();i++) {
				String filepath="All Files/"+Org1FirmName+"/Potential Investment/"+M15FundName1+"/"+ investorList.get(i)+"/"+stdPath+"/"+importFileName;
				appLog.info("Going to verify path:  "+filepath);
				for(int j=0; j<downLoadedFolderPathList.size(); j++) {
					if (filepath.equalsIgnoreCase(downLoadedFolderPathList.get(j).trim())) {
						appLog.info("successfully found file of "+investorList.get(i)+" on folder structure");
						if(itemNameList.get(j).equalsIgnoreCase(importFileName)) {
							appLog.info("Item Name is matched for folder path  :"+downLoadedFolderPathList.get(i));
							
							
						}else {
							appLog.error("Item Name is not matched for folder path : "+downLoadedFolderPathList.get(i));
							sa.assertTrue(false, "Item Name is not matched for folder path : "+downLoadedFolderPathList.get(i));
						}
						break;
					}else {
						if(j==downLoadedFolderPathList.size()-1) {
							appLog.error("could not find file of "+investorList.get(i));
							sa.assertTrue(false, "could not find file of "+investorList.get(i));
						}
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot verify upload file name from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot verify upload file name from downloaded folder");
		}
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc027_2_verifyOnlineImportedFilesInvestorSide() {
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String StandardFolderFile = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, "M15tc027_0_OnlineImportFileInStandardFolder", excelLabel.UploadedFileStandard);
		String StandardFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, "M15tc027_0_OnlineImportFileInStandardFolder", excelLabel.StandardPath);
		lp.investorLogin(M15C1EmailId, adminPassword);
		if(ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)){
			if(fp.verifyFolderPathdummy(StandardFolderPath, M15Institution1, null, null, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)){
				if(fp.verifyFileinContentGrid(PageName.PotentialInvestmentPage, Workspace.InvestorWorkspace, StandardFolderFile)){
					appLog.info(StandardFolderFile+" file is verified.");
				} else {
					appLog.error(StandardFolderFile+" file is not verified.");
					sa.assertTrue(false,StandardFolderFile+" file is not verified.");
					
				}
			} else {
				appLog.error(StandardFolderPath+" folder is not veirfied, So cannot veirfy file upload status.");
				sa.assertTrue(false,StandardFolderPath+" folder is not veirfied, So cannot veirfy file upload status.");
			}
		} else {
			appLog.error("Potential investment cannot be clicked, So cannot verify file upload status at investor side.");
			sa.assertTrue(false,"Potential investment cannot be clicked, So cannot verify file upload status at investor side.");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc028_TurnOnManageApproval(String environment, String mode){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName, adminPassword);
		if(lp.clickOnTab(TabName.NIMTab)){
			switchToFrame(driver, 30, nim.getFrame( PageName.NavatarInvestorManager, 30));
			if(nim.clickOnSideMenusTab(sideMenu.ManageApprovals)){
				List<String> aa = nim.activateManageApprovalsSettings(CRMUser1FirstName+" "+CRMUser1LastName);
				if(aa.isEmpty()){
					appLog.info("Successfully Turned on manage approval.");
				} else {
					for (String string : aa) {
						sa.assertTrue(false,string);
					}
				}
			} else {
				appLog.error("Manage approval tab cannot be clicked, So cannot Turn on manage approval.");
				sa.assertTrue(false,"Manage approval tab cannot be clicked, So cannot Turn on manage approval.");
			}
		} else {
			appLog.error("NIM Tab cannot be clicked, So cannot Turn on manage approval.");
			sa.assertTrue(false,"NIM Tab cannot be clicked, So cannot Turn on manage approval.");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc029_UploadFilesUnderCommonFolder(String environment, String mode){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String CommonFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String CommonFolderFiles = null;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, CommonFolderPath, null, "UploadFiles\\BulkFiles\\Common_ManageApprovalFiles", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
					appLog.info("Files uploaded successfully.");
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30), "Refresh BUtton", action.BOOLEAN)){
						CommonFolderFiles = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
						for(int i = 0; i < CommonFolderFiles.split("<break>").length; i++){
							String fileName = CommonFolderFiles.split("<break>")[i];
							if(fp.getStatusOfFile(PageName.FundsPage, Workspace.FundraisingWorkspace, fileName).toString().equalsIgnoreCase(Status.Pending.toString())){
								appLog.info(fileName+" is in pending status.");
							} else {
								appLog.error(fileName+" is not in Pending status.");
								sa.assertTrue(false,fileName+" is not in Pending status.");
							}
						}
					} else {
						appLog.error("Refresh button cannot be clicked, So cannot check file status.");
						sa.assertTrue(false,"Refresh button cannot be clicked, So cannot check file status.");
					}
				} else {
					appLog.error("Not able to upload file in folder, So cannot continue with the tc.");
					sa.assertTrue(false,"Not able to upload file in folder, So cannot continue with the tc.");
				}
			} else {
				appLog.error(M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds Tab cannot be clicked, So cannot continue with the testcase.");
			sa.assertTrue(false,"Funds Tab cannot be clicked, So cannot continue with the testcase.");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc030_ApproveDocumentAndVerifyStatus(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependsOn = "M15tc029_UploadFilesUnderCommonFolder";
		String CommonFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, dependsOn, excelLabel.CommonPath);
		String CommonFolderFiles = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, dependsOn, excelLabel.UploadedFileCommon);
		boolean flag = false;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if(click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "Manage Approval Icon", action.SCROLLANDBOOLEAN)){
					if(click(driver, fp.getManageApprovalsAllDocumentSelectCheckBox(30), "Select all check box", action.BOOLEAN)){
						if(click(driver, fp.getManageApprovalsApproveBtn(30), "Approve button", action.BOOLEAN)){
							if(click(driver, fp.getManageApprovalsConfirmYesBtn(30), "Yes button", action.BOOLEAN)){
								if(click(driver, fp.getManageApprovalsCloseBtn(60), "Confirmation Close button", action.BOOLEAN)){
									appLog.info("Successfully approved the documents.");
									click(driver, fp.getManageApprovalsCancelBtn(30), "cancel button", action.SCROLLANDBOOLEAN);
									flag = true;
								} else {
									switchToDefaultContent(driver);
									refresh(driver);
									switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
									scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising Workspaec view");
								}
								if(fp.verifyFolderPathdummy(CommonFolderPath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
//									CommonFolderFiles = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
									for(int i = 0; i < CommonFolderFiles.split("<break>").length; i++){
										String fileName = CommonFolderFiles.split("<break>")[i];
										if(fp.getStatusOfFile(PageName.FundsPage, Workspace.FundraisingWorkspace, fileName).toString().equalsIgnoreCase(Status.Approved.toString())){
											appLog.info(fileName+" is in Approved status and is verified.");
										} else {
											appLog.error(fileName+" is not in Approved status in crm content grid and is not verified.");
											sa.assertTrue(false,fileName+" is not Approved status in crm content grid and is not verified.");
										}
									}
								} else {
									appLog.error(CommonFolderPath+" folder path is not verified.");
									sa.assertTrue(false,CommonFolderPath+" folder path is not verified.");
								}
							} else {
								appLog.error("Approval confirmation yes button cannot be clicked, So cannot continue with the tc.");
								sa.assertTrue(false,"Approval confirmation yes button cannot be clicked, So cannot continue with the tc.");
							}
						} else {
							appLog.error("Approve button cannot be clicked, So cannot continue with the tc.");
							sa.assertTrue(false,"Approve button cannot be clicked, So cannot continue with the tc.");
						}
					} else {
						appLog.error("Cannot click on select all check box, So cannot approve all the documents.");
						sa.assertTrue(false,"Cannot click on select all check box, So cannot approve all the documents.");
					}
				} else {
					appLog.error("Manage approval icon cannot be clicked, So cannot Approve the files.");
					sa.assertTrue(false,"Manage approval icon cannot be clicked, So cannot Approve the files.");
				}
			} else {
				appLog.error(M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds Tab cannot be clicked, So cannot continue with the testcase.");
			sa.assertTrue(false,"Funds Tab cannot be clicked, So cannot continue with the testcase.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		if(flag){
			driver.close();
			config(browserToLaunch);
			lp= new LoginPageBusinessLayer(driver);
			InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
			fp = new FundsPageBusinessLayer(driver);
			lp.investorLogin(M15C1EmailId, adminPassword);
			if(ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)){
				if(fp.verifyFolderPathdummy(CommonFolderPath, null, null, null, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)){
					for(int i = 0; i < CommonFolderFiles.split("<break>").length; i++){
						if(fp.verifyFileinContentGrid(PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, CommonFolderFiles.split("<break>")[i])){
							appLog.info(CommonFolderFiles.split("<break>")[i]+" file is verified investor side.");
						} else {
							appLog.error(CommonFolderFiles.split("<break>")[i]+" file is not verified investor side.");
							sa.assertTrue(false,CommonFolderFiles.split("<break>")[i]+" file is not verified investor side.");
						}
					}
				} else {
					appLog.error(CommonFolderPath+" folder is not veirfied, So cannot veirfy file after approval.");
					sa.assertTrue(false,CommonFolderPath+" folder is not veirfied, So cannot veirfy file after approval.");
				}
			} else {
				appLog.error("Potential investment cannot be clicked, So cannot verify file upload status at investor side.");
				sa.assertTrue(false,"Potential investment cannot be clicked, So cannot verify file upload status at investor side.");
			}
			lp.investorLogout();
		}
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M15tc031_UpdateFilesAvailableInCommonFolderAndVerifyErrorMessage(String environment, String mode){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependsOn = "M15tc029_UploadFilesUnderCommonFolder";
		String CommonFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, dependsOn, excelLabel.CommonPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, CommonFolderPath, null, "UploadFiles\\BulkFiles\\Common_ManageApprovalUpdateFiles", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					if(click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "Manage Approval Icon", action.SCROLLANDBOOLEAN)){
						if(click(driver, fp.getManageApprovalsAllDocumentSelectCheckBox(30), "Select all check box", action.BOOLEAN)){
							if(click(driver, fp.getManageApprovalsApproveBtn(30), "Approve button", action.BOOLEAN)){
								if(click(driver, fp.getManageApprovalsConfirmYesBtn(30), "Yes button", action.BOOLEAN)){
									if(click(driver, fp.getManageApprovalsUpdateAllDocument(30), "Update all button", action.BOOLEAN)){
										if(fp.getSizeLimitErrorMessage(30)!=null){
											String msg = fp.getSizeLimitErrorMessage(30).getText().trim();
											if(msg.contains(FundsPageErrorMessage.ManageApprovalFileSizeLimitErrorMsg)){
												appLog.info("Size limit error message is verified.");
											} else {
												appLog.error("Size limit error message is not verified. Expected: "+FundsPageErrorMessage.ManageApprovalFileSizeLimitErrorMsg+"\tActual: "+msg);
												sa.assertTrue(false,"Size limit error message is not verified. Expected: "+FundsPageErrorMessage.ManageApprovalFileSizeLimitErrorMsg+"\tActual: "+msg);
											}
										} else {
											appLog.error("Size limit error message is not poping up.");
											sa.assertTrue(false,"Size limit error message is not poping up.");
										}
									} else {
										appLog.error("Update all button cannot be clicked, SO cannot verify size limit error message.");
										sa.assertTrue(false,"Update all button cannot be clicked, SO cannot verify size limit error message.");
									}
								} else {
									appLog.error("Approval confirmation yes button cannot be clicked, So cannot continue with the tc.");
									sa.assertTrue(false,"Approval confirmation yes button cannot be clicked, So cannot continue with the tc.");
								}
								
							} else {
								appLog.error("Approve button cannot be clicked, So cannot continue with the tc.");
								sa.assertTrue(false,"Approve button cannot be clicked, So cannot continue with the tc.");
							}
						} else {
							appLog.error("Cannot click on select all check box, So cannot approve all the documents.");
							sa.assertTrue(false,"Cannot click on select all check box, So cannot approve all the documents.");
						}
					} else {
						appLog.error("Manage approval icon cannot be clicked, So cannot Approve the files.");
						sa.assertTrue(false,"Manage approval icon cannot be clicked, So cannot Approve the files.");
					}
				} else {
					appLog.error("Not able to upload file in folder, So cannot continue with the tc.");
					sa.assertTrue(false,"Not able to upload file in folder, So cannot continue with the tc.");
				}
			} else {
				appLog.error(M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds Tab cannot be clicked, So cannot continue with the testcase.");
			sa.assertTrue(false,"Funds Tab cannot be clicked, So cannot continue with the testcase.");
		}
		switchToDefaultContent(driver);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc032_1_UploadFilesInStandardFolder(String environment, String mode){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String StandardFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				List<String> institutions = getValueBasedOnLabel(excelPath, "Institutions", excelLabel.Institutions_Name, 0).subList(0, 11);
				String institutionOrLPName = createStringOutOfList(institutions);
				if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, StandardFolderPath, institutionOrLPName, "UploadFiles\\BulkFiles\\Standard_ManageApprovalFiles", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
					appLog.info("File is Uploaded successfully");
				} else {
					appLog.error("Not able to upload file in folder, So cannot continue with the tc.");
					sa.assertTrue(false,"Not able to upload file in folder, So cannot continue with the tc.");
				}
			} else {
				appLog.error(M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds Tab cannot be clicked, So cannot continue with the testcase.");
			sa.assertTrue(false,"Funds Tab cannot be clicked, So cannot continue with the testcase.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc032_2_verifyUploadedFilesInBox() {
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		List<String> investorList = new ArrayList<String>();
		List<String> downLoadedFolderPathList = new ArrayList<String>();
		List<String> itemNameList = new ArrayList<String>();
		String[] splitedUploadedFiles = null;
		String uploadedFile=null;
		String stdPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, "M15tc032_1_UploadFilesInStandardFolder", excelLabel.StandardPath);
		driver.get("https://account.box.com/login");
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName1, Workspace.FundraisingWorkspace, 30)){
			appLog.info("File is downloaded successfully from box");
			
		} else {
			appLog.error("Not able to download report, So cannot continue with tc.");
			sa.assertTrue(false,"Not able to download report, So cannot continue with tc.");
		}
		ThreadSleep(7000);
		String s =getFilesNameFromSystemFolder("DownloadedFiles");
		String fileName=null;
		if(s != null) {
			List<String> downloadedfileList=createListOutOfString(s);
			for (int i=0; i<downloadedfileList.size(); i++) {
				if(BoxPageBusinesslayer.fileName.equalsIgnoreCase(downloadedfileList.get(i))) {
					appLog.info("Downloaded File is available in download folder : "+BoxPageBusinesslayer.fileName);
					fileName=BoxPageBusinesslayer.fileName;
					investorList = getValueBasedOnLabel(excelPath, "Institutions", excelLabel.Institutions_Name, 0).subList(0, 11);
					String downLoadedFolderPath=ExcelUtils.readAllDataForAColumn(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", 2, false);
					String ItemName = ExcelUtils.readAllDataForAColumn(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", 4, false);
					downLoadedFolderPathList=createListOutOfStringUsingComma(downLoadedFolderPath);
					itemNameList=createListOutOfStringUsingComma(ItemName);
					uploadedFile = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, "M15tc032_1_UploadFilesInStandardFolder", excelLabel.UploadedFileStandard);
					splitedUploadedFiles =uploadedFile.split("<break>");
					break;
				}else {
					if(i==downloadedfileList.size()-1) {
						appLog.error(BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
						sa.assertTrue(false, BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot get file from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot get file from downloaded folder");
		}	
		if(fileName!=null && !fileName.isEmpty()) {
			for (int i = 0;i<investorList.size();i++) {
				for(int k=0; k<splitedUploadedFiles.length; k++) {
					String filepath="All Files/"+Org1FirmName+"/Potential Investment/"+M15FundName1+"/"+ investorList.get(i)+"/"+stdPath+"/"+splitedUploadedFiles[k];
					appLog.info("Going to verify path:  "+filepath);
					for(int j=0; j<downLoadedFolderPathList.size(); j++) {
						if (filepath.equalsIgnoreCase(downLoadedFolderPathList.get(j).trim())) {
							appLog.error("successfully found file of "+investorList.get(i)+" on folder structure");
							sa.assertTrue(false, "successfully found file of "+investorList.get(i)+" on folder structure");
							if(itemNameList.get(j).equalsIgnoreCase(splitedUploadedFiles[k])) {
								appLog.error("File Name is available for folder path  :"+downLoadedFolderPathList.get(i));
								sa.assertTrue(false, "File Name is available for folder path  :"+downLoadedFolderPathList.get(i));
							}else {
								appLog.info("File Name : "+splitedUploadedFiles[k]+" is not matched for folder path : "+downLoadedFolderPathList.get(i));
								
							}
							break;
						}else {
							if(j==downLoadedFolderPathList.size()-1) {
								appLog.info("could not find file path of "+investorList.get(i));
								
							}
						}
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot verify upload file name from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot verify upload file name from downloaded folder");
		}
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc033_VerifyErrorMessageForMoreThan100Files(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if(click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "Manage Approval Icon", action.SCROLLANDBOOLEAN)){
					if(click(driver, fp.getManageApprovalsAllDocumentSelectCheckBox(30), "Select all check box", action.BOOLEAN)){
						if(click(driver, fp.getManageApprovalsApproveBtn(30), "Approve button", action.BOOLEAN)){
							if(isAlertPresent(driver)){
								String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
								switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
								if(msg.equalsIgnoreCase(FundsPageErrorMessage.ApproveMoreThan100FilesErrorMsg)){
									appLog.info("Error message verified.");
								} else {
									appLog.error("Alert message is not verified.Expected: "+FundsPageErrorMessage.ApproveMoreThan100FilesErrorMsg+"\tActual: "+msg);
									sa.assertTrue(false,"Alert message is not verified.Expected: "+FundsPageErrorMessage.ApproveMoreThan100FilesErrorMsg+"\tActual: "+msg);
								}
							} else {
								appLog.error("No Alert for approving more than 100 files at a time.");
								sa.assertTrue(false,"No Alert for approving more than 100 files at a time.");
							}
							
						} else {
							appLog.error("Approve button cannot be clicked, So cannot continue with the tc.");
							sa.assertTrue(false,"Approve button cannot be clicked, So cannot continue with the tc.");
						}
					} else {
						appLog.error("Cannot click on select all check box, So cannot approve all the documents.");
						sa.assertTrue(false,"Cannot click on select all check box, So cannot approve all the documents.");
					}
				} else {
					appLog.error("Manage approval icon cannot be clicked, So cannot Approve the files.");
					sa.assertTrue(false,"Manage approval icon cannot be clicked, So cannot Approve the files.");
				}
			} else {
				appLog.error("Not able to upload file in folder, So cannot continue with the tc.");
				sa.assertTrue(false,"Not able to upload file in folder, So cannot continue with the tc.");
			}
			} else {
				appLog.error(M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
			}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc034_ApproveFileOfStandardFolder(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependsOn = "M15tc032_1_UploadFilesInStandardFolder";
		String StandardFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, dependsOn, excelLabel.StandardPath);
		String StandardFolderFiles = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, dependsOn, excelLabel.UploadedFileStandard);
		boolean flag = true;
//		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		List<String> institutionList =getValueBasedOnLabel(excelPath, "Institutions", excelLabel.Institutions_Name, 0);
		
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if(click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "Manage Approval icon", action.SCROLLANDBOOLEAN)){
					if(selectVisibleTextFromDropDown(driver, fp.getManageApprovalsDropdown(ManageApprovalTabs.PendingDocuments, 30), "Pending documents", "All Pending Documents")){
						List<String> institutions = institutionList.subList(0, 10);
						String institutionOrLPName = createStringOutOfList(institutions);
						System.err.println(institutionOrLPName);
						//span[contains(@id,'pendingGrid-cell-2')][@title='Fund 1 > Bulk Inst8 > Standard22']/preceding-sibling::span[@title='StandMA3.pdf']/preceding-sibling::span//input
						for(int i = 0; i < institutionOrLPName.split("<break>").length; i++){
							for(int j = 0; j < StandardFolderFiles.split("<break>").length; j++){
								String fileName = StandardFolderFiles.split("<break>")[j];
								String instName = institutionOrLPName.split("<break>")[i];
								System.err.println("fileName: "+fileName+" Insti name: "+instName);
//								fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), fileName, M15FundName1+" > "+instName+" > "+StandardFolderPath, CRMUser1FirstName+" "+CRMUser1LastName,Org1FirmName,date);
								WebElement ele = FindElement(driver, "//span[contains(@id,'pendingGrid-cell')][text()='"+M15FundName1+" > "+instName+" > "+StandardFolderPath+"']/preceding-sibling::span/a[text()='"+ fileName +"']/../preceding-sibling::span//input", fileName+" file check box", action.SCROLLANDBOOLEAN, 30);
								if(click(driver, ele, fileName+" check box", action.SCROLLANDBOOLEAN)){
									appLog.info("Successfully Clicked on file '"+fileName+"' of folder path'"+M15FundName1+" > "+instName+" > "+StandardFolderPath+"'.");
									
								} else {
									appLog.error("Cannot click on file '"+fileName+"' of folder path'"+M15FundName1+" > "+instName+" > "+StandardFolderPath+"', So cannot verify files.");
									sa.assertTrue(false,"Cannot click on file '"+fileName+"' of folder path'"+M15FundName1+" > "+instName+" > "+StandardFolderPath+"', So cannot verify files.");
									flag = false;
								}
								
							}
						}
						if(flag){
							if(click(driver, fp.getManageApprovalsApproveBtn(30), "Approve button", action.SCROLLANDBOOLEAN)){
								if(click(driver, fp.getManageApprovalsConfirmYesBtn(30), "Yes button", action.SCROLLANDBOOLEAN)){
									if(click(driver, fp.getManageApprovalsCloseBtn(30), "Confirmation Close button", action.SCROLLANDBOOLEAN)){
										appLog.info("Successfully approved the documents.");
									} else {
									}
									refresh(driver);
									switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
									scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Investor Workspaec view");
									flag = true;
								} else {
									appLog.error("Approval confirmation yes button cannot be clicked, So cannot continue with the tc.");
									sa.assertTrue(false,"Approval confirmation yes button cannot be clicked, So cannot continue with the tc.");
								}
							} else {
								appLog.error("Approve button cannot be clicked, So cannot continue with the tc.");
								sa.assertTrue(false,"Approve button cannot be clicked, So cannot continue with the tc.");
							}
						} else {
							appLog.error("All the files are not selected, SO cannot continue with the tc.");
							sa.assertTrue(false,"All the files are not selected, SO cannot continue with the tc.");
						}
						
						if(flag){
							if(click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "Manage Approval icon", action.SCROLLANDBOOLEAN)){
								if(selectVisibleTextFromDropDown(driver, fp.getManageApprovalsDropdown(ManageApprovalTabs.PendingDocuments, 30), "Pending documents", "All Pending Documents")){
									institutions = institutionList.subList(0, 11);
									institutionOrLPName = createStringOutOfList(institutions);
									//span[contains(@id,'pendingGrid-cell-2')][@title='Fund 1 > Bulk Inst8 > Standard22']/preceding-sibling::span[@title='StandMA3.pdf']/preceding-sibling::span//input
										for(int j = 0; j < StandardFolderFiles.split("<break>").length; j++){
											String fileName = StandardFolderFiles.split("<break>")[j];
											String instName = institutionOrLPName.split("<break>")[institutionOrLPName.split("<break>").length-1];
//											fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), fileName, M15FundName1+" > "+instName+" > "+StandardFolderPath, CRMUser1FirstName+" "+CRMUser1LastName,Org1FirmName,date);
											WebElement ele = FindElement(driver, "//span[contains(@id,'pendingGrid-cell')][text()='"+M15FundName1+" > "+instName+" > "+StandardFolderPath+"']/preceding-sibling::span/a[text()='"+ fileName +"']/../preceding-sibling::span//input", fileName+" file check box", action.SCROLLANDBOOLEAN, 30);
											if(click(driver, ele, fileName+" check box", action.SCROLLANDBOOLEAN)){
												appLog.info("Successfully Clicked on file '"+fileName+"' of folder path'"+M15FundName1+" > "+instName+" > "+StandardFolderPath+"'.");
											} else {
												appLog.error("Cannot click on file '"+fileName+"' of folder path'"+M15FundName1+" > "+instName+" > "+StandardFolderPath+"', So cannot verify files.");
												sa.assertTrue(false,"Cannot click on file '"+fileName+"' of folder path'"+M15FundName1+" > "+instName+" > "+StandardFolderPath+"', So cannot verify files.");
												flag = false;
											}
											
										}
								} else {
									appLog.error("All pending documents cannot be selected, So cannot approve docs and verify.");
									sa.assertTrue(false,"All pending documents cannot be selected, So cannot approve docs and verify.");
								}
							} else {
								appLog.error("Manage approval icon cannot be clicked, So cannot approve and vrify files.");
								sa.assertTrue(false,"Manage approval icon cannot be clicked, So cannot approve and vrify files.");
							}
						}
						
						if(flag){
							if(click(driver, fp.getManageApprovalsApproveBtn(30), "Approve button", action.BOOLEAN)){
								if(click(driver, fp.getManageApprovalsConfirmYesBtn(30), "Yes button", action.BOOLEAN)){
									if(click(driver, fp.getManageApprovalsCloseBtn(30), "Confirmation Close button", action.BOOLEAN)){
										appLog.info("Successfully approved the documents.");
									} else {
									}
									refresh(driver);
									switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
									scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Investor Workspaec view");
									flag = true;
								} else {
									appLog.error("Approval confirmation yes button cannot be clicked, So cannot continue with the tc.");
									sa.assertTrue(false,"Approval confirmation yes button cannot be clicked, So cannot continue with the tc.");
								}
							} else {
								appLog.error("Approve button cannot be clicked, So cannot continue with the tc.");
								sa.assertTrue(false,"Approve button cannot be clicked, So cannot continue with the tc.");
							}
						} else {
							appLog.error("All the files are not selected, SO cannot continue with the tc.");
							sa.assertTrue(false,"All the files are not selected, SO cannot continue with the tc.");
						}
						
						if(flag){
							if(click(driver, fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "Manage Approval icon", action.SCROLLANDBOOLEAN)){
								if(click(driver, fp.getApprovedDocsTab(30), "Approved doc tab", action.BOOLEAN)){
									institutions = institutionList.subList(0, 10);
									institutionOrLPName = createStringOutOfList(institutions);
									//span[contains(@id,'pendingGrid-cell-2')][@title='Fund 1 > Bulk Inst8 > Standard22']/preceding-sibling::span[@title='StandMA3.pdf']/preceding-sibling::span//input
									for(int i = 0; i < institutionOrLPName.split("<break>").length-1; i++){
										for(int j = 0; j < StandardFolderFiles.split("<break>").length; j++){
											String fileName = StandardFolderFiles.split("<break>")[j];
											String instName = institutionOrLPName.split("<break>")[i];
//											fp.findRowByScrollingManageApprovals(ManageApprovalTabs.ApprovedDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.ApprovedDocuments, 30), fileName, M15FundName1+" > "+instName+" > "+StandardFolderPath, CRMUser1FirstName+" "+CRMUser1LastName,Org1FirmName,date);
											
											WebElement ele = FindElement(driver, "//span[text()='"+M15FundName1+" > "+instName+" > "+StandardFolderPath+"']/preceding-sibling::span/a[text()='"+ fileName +"']", fileName+" file check box", action.SCROLLANDBOOLEAN, 2);
											if(ele!=null){
												appLog.info("File '"+fileName+"' of folder path'"+M15FundName1+" > "+instName+" > "+StandardFolderPath+"' is present in approved docs grid and is verified.");
											} else {
												appLog.error("File '"+fileName+"' of folder path'"+M15FundName1+" > "+instName+" > "+StandardFolderPath+"' is not present in approved docs grid.");
												sa.assertTrue(false,"File '"+fileName+"' of folder path'"+M15FundName1+" > "+instName+" > "+StandardFolderPath+"' is not present in approved docs grid.");
												flag = false;
											}
											
										}
									}
										refresh(driver);
										switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
										scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Workspace view.");
										
								} else {
									appLog.error("Cannot click on approved docs tab, So cannot verify error msg.");
									sa.assertTrue(false,"Cannot click on approved docs tab, So cannot verify error msg.");
								}
							} else {
								appLog.error("Manage approval icon cannot be clicked, So cannot approve and vrify files.");
								sa.assertTrue(false,"Manage approval icon cannot be clicked, So cannot approve and vrify files.");
							}
						}
						
						if(flag){
							institutions = institutionList.subList(0, 10);
							institutionOrLPName = createStringOutOfList(institutions);
						//	StandardFolderFiles = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
							for(int i = 0; i < institutionOrLPName.split("<break>").length; i++){
								if(fp.verifyFolderPathdummy(StandardFolderPath, institutionOrLPName.split("<break>")[i], null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 2)){
									for(int j = 0; j < StandardFolderFiles.split("<break>").length; j++){
										String fileName = StandardFolderFiles.split("<break>")[j];
										if(fp.getStatusOfFile(PageName.FundsPage, Workspace.FundraisingWorkspace, fileName).toString().equalsIgnoreCase(Status.Approved.toString())){
											appLog.info(fileName+" is in Approved status and is verified.");
										} else {
											appLog.error(fileName+" is not in Approved status in crm content grid and is not verified.");
											sa.assertTrue(false,fileName+" is not Approved status in crm content grid and is not verified.");
										}
									}
								} else {
									appLog.error(StandardFolderPath+" path is not verified, So cannot verify file status for institution '"+institutionOrLPName.split("<break>")[i]+"'.");
									sa.assertTrue(false,StandardFolderPath+" path is not verified, So cannot verify file status for institution '"+institutionOrLPName.split("<break>")[i]+"'.");
								}
								
							}
						}
					} else {
						appLog.error("All pending documents cannot be selected, So cannot approve docs and verify.");
						sa.assertTrue(false,"All pending documents cannot be selected, So cannot approve docs and verify.");
					}
				} else {
					appLog.error("Manage approval icon cannot be clicked, So cannot approve and vrify files.");
					sa.assertTrue(false,"Manage approval icon cannot be clicked, So cannot approve and vrify files.");
				}
			} else {
				appLog.error("Not able to upload file in folder, So cannot continue with the tc.");
				sa.assertTrue(false,"Not able to upload file in folder, So cannot continue with the tc.");
			}
			} else {
				appLog.error(M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
			}
		
		if(flag){
			driver.close();
			config(browserToLaunch);
			lp= new LoginPageBusinessLayer(driver);
			InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
			fp = new FundsPageBusinessLayer(driver);
			lp.investorLogin(M15C1EmailId, adminPassword);
			if(ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)){
				if(fp.verifyFolderPathdummy(StandardFolderPath, M15Institution1, null, null, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)){
					for(int i = 0; i < StandardFolderPath.split("<break>").length; i++){
						if(fp.verifyFileinContentGrid(PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, StandardFolderPath.split("<break>")[i])){
							appLog.info(StandardFolderPath.split("<break>")[i]+" file is verified investor side.");
						} else {
							appLog.error(StandardFolderPath.split("<break>")[i]+" file is not verified investor side.");
							sa.assertTrue(false,StandardFolderPath.split("<break>")[i]+" file is not verified investor side.");
						}
					}
				} else {
					appLog.error(StandardFolderPath+" folder is not veirfied, So cannot veirfy file after approval.");
					sa.assertTrue(false,StandardFolderPath+" folder is not veirfied, So cannot veirfy file after approval.");
				}
			} else {
				appLog.error("Potential investment cannot be clicked, So cannot verify file upload status at investor side.");
				sa.assertTrue(false,"Potential investment cannot be clicked, So cannot verify file upload status at investor side.");
			}
			lp.investorLogout();
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc035_0_DeactivateManageApproval(){
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.NIMTab)){
			switchToFrame(driver, 30, nim.getFrame( PageName.NavatarInvestorManager, 30));
			if(nim.deactivateManageApprovalsSetting()){
				appLog.info("Manage approval is deactivated successfully.");
			} else {
				appLog.error("Not able to deactivate manage approval setting.");
				sa.assertTrue(false,"Not able to deactivate manage approval setting.");
			}
			
		} else {
			appLog.error("Nim Tab cannot be clicked, So cannot deavtivate manage approval.");
			sa.assertTrue(false,"Nim Tab cannot be clicked, So cannot deavtivate manage approval.");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M15tc035_1_UpdateFileWithDifferentNameInStandardFolderFWR() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String StandardFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String UpdatestdfilesName = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
		String uploadstdfilesName = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, "M15tc026_0_UploadFileInStandardFolder", excelLabel.UploadedFileStandard);
		String docPath=System.getProperty("user.dir") + "\\UploadFiles\\BulkFiles\\Standard_UpdateMultipleInstiFile\\";
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				if(fp.updateBulkFile(CRMUser1EmailID,StandardFolderPath, uploadstdfilesName, M15Institution1, null, FolderType.Standard,docPath+UpdatestdfilesName, multiInstance.AllInvestor, null, ContentGridArrowKeyFunctions.Update,20, PageName.FundsPage,"Yes",null, Workspace.FundraisingWorkspace)) {
					appLog.info("file is updated successfully: "+uploadstdfilesName+" in :"+StandardFolderPath+" to "+UpdatestdfilesName);
					
				}else {
					appLog.error("file is not updated: "+uploadstdfilesName+" in :"+UpdatestdfilesName);
					sa.assertTrue(false, "file is not updated: "+uploadstdfilesName+" in :"+UpdatestdfilesName);
				}
			} else {
				appLog.error(M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc035_2_verifyUpdatedFileInBox() {
		String dependOnTc="M15tc035_1_UpdateFileWithDifferentNameInStandardFolderFWR";
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		List<String> investorList = new ArrayList<String>();
		List<String> downLoadedFolderPathList = new ArrayList<String>();
		List<String> itemNameList = new ArrayList<String>();
		String StandardFolderFile =  ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileStandard);
		String StandardFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, dependOnTc, excelLabel.StandardPath);
		driver.get("https://account.box.com/login");
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName1, Workspace.FundraisingWorkspace, 30)){
			appLog.info("Successfully downloaded report");
		} else {
			appLog.error("Not able to download report, So cannot continue with tc.");
			sa.assertTrue(false,"Not able to download report, So cannot continue with tc.");
		}
		ThreadSleep(7000);
		String s =getFilesNameFromSystemFolder("DownloadedFiles");
		String fileName=null;
		if(s != null) {
			List<String> downloadedfileList=createListOutOfString(s);
			for (int i=0; i<downloadedfileList.size(); i++) {
				if(BoxPageBusinesslayer.fileName.equalsIgnoreCase(downloadedfileList.get(i))) {
					appLog.info("Downloaded File is available in download folder : "+BoxPageBusinesslayer.fileName);
					fileName=BoxPageBusinesslayer.fileName;
					investorList = CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
					String downLoadedFolderPath=ExcelUtils.readAllDataForAColumn(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", 2, false);
					String ItemName = ExcelUtils.readAllDataForAColumn(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", 4, false);
					downLoadedFolderPathList=createListOutOfStringUsingComma(downLoadedFolderPath);
					itemNameList=createListOutOfStringUsingComma(ItemName);
					break;
				}else {
					if(i==downloadedfileList.size()-1) {
						appLog.error(BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
						sa.assertTrue(false, BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot get file from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot get file from downloaded folder");
		}
		
		if(fileName!=null && !fileName.isEmpty()) {
			for (int i = 0;i<investorList.size();i++) {
				String filepath="All Files/"+Org1FirmName+"/Potential Investment/"+M15FundName1+"/"+ investorList.get(i)+"/"+StandardFolderPath+"/"+StandardFolderFile;
				appLog.info("Going to verify path:  "+filepath);
				for(int j=0; j<downLoadedFolderPathList.size(); j++) {
					if (filepath.equalsIgnoreCase(downLoadedFolderPathList.get(j).trim())) {
						appLog.info("successfully found file of "+investorList.get(i)+" on folder structure");
						if(itemNameList.get(j).equalsIgnoreCase(StandardFolderFile)) {
							appLog.info("Item Name is matched for folder path  :"+downLoadedFolderPathList.get(i));
							
							
						}else {
							appLog.error("Item Name is not matched for folder path : "+downLoadedFolderPathList.get(i));
							sa.assertTrue(false, "Item Name is not matched for folder path : "+downLoadedFolderPathList.get(i));
						}
						break;
					}else {
						if(j==downLoadedFolderPathList.size()-1) {
							appLog.error("could not find file of "+investorList.get(i));
							sa.assertTrue(false, "could not find file of "+investorList.get(i));
						}
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot verify updated file name from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot verify updated file name from downloaded folder");
		}
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc036_InviteContactFrom10Institutions(String environment, String mode){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if (fp.clickOnCreatedFund(M15FundName1)) {
				List<String> institutions = getValueBasedOnLabel(excelPath, "Institutions", excelLabel.Institutions_Name, 0).subList(0, 10);
				System.err.println("successfully retreived the data");
				System.err.println(institutions.get(1));
				try{
					if(fp.inviteContact(environment, mode, institutions.get(1), M15C1EmailId, null, FolderType.Standard, "Download", "yes", "No", null, Workspace.FundraisingWorkspace, M15C1EmailId)){
						appLog.info(M15CFN1+" "+M15CLN1+" successfully invited from "+institutions.get(1));
					} else {
						appLog.error(M15CFN1+" "+M15CLN1+" didnot get the invitation from "+institutions.get(1));
						sa.assertTrue(false,M15CFN1+" "+M15CLN1+" didnot get the invitation from "+institutions.get(1));
					}
				} catch (Exception e){
					e.printStackTrace();
				}
				if(fp.inviteContact(environment, mode, institutions.get(2), M15C1EmailId, null, FolderType.Standard, "Download", "yes", "No", null, Workspace.FundraisingWorkspace, M15C1EmailId)){
					appLog.info(M15CFN1+" "+M15CLN1+" successfully invited from "+institutions.get(2));
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" didnot get the invitation from "+institutions.get(2));
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" didnot get the invitation from "+institutions.get(2));
				}
				if(fp.inviteContact(environment, mode, institutions.get(3), M15C1EmailId, null, FolderType.Standard, "Download", "yes", "No", null, Workspace.FundraisingWorkspace, M15C1EmailId)){
					appLog.info(M15CFN1+" "+M15CLN1+" successfully invited from "+institutions.get(3));
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" didnot get the invitation from "+institutions.get(3));
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" didnot get the invitation from "+institutions.get(3));
				}
				if(fp.inviteContact(environment, mode, institutions.get(4), M15C1EmailId, null, FolderType.Standard, "Download", "yes", "No", null, Workspace.FundraisingWorkspace, M15C1EmailId)){
					appLog.info(M15CFN1+" "+M15CLN1+" successfully invited from "+institutions.get(4));
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" didnot get the invitation from "+institutions.get(4));
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" didnot get the invitation from "+institutions.get(4));
				}
				if(fp.inviteContact(environment, mode, institutions.get(5), M15C1EmailId, null, FolderType.Standard, "Download", "yes", "No", null, Workspace.FundraisingWorkspace, M15C1EmailId)){
					appLog.info(M15CFN1+" "+M15CLN1+" successfully invited from "+institutions.get(5));
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" didnot get the invitation from "+institutions.get(5));
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" didnot get the invitation from "+institutions.get(5));
				}
				if(fp.inviteContact(environment, mode, institutions.get(6), M15C1EmailId, null, FolderType.Standard, "Download", "yes", "No", null, Workspace.FundraisingWorkspace, M15C1EmailId)){
					appLog.info(M15CFN1+" "+M15CLN1+" successfully invited from "+institutions.get(6));
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" didnot get the invitation from "+institutions.get(6));
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" didnot get the invitation from "+institutions.get(6));
				}
				if(fp.inviteContact(environment, mode, institutions.get(7), M15C1EmailId, null, FolderType.Standard, "Download", "yes", "No", null, Workspace.FundraisingWorkspace, M15C1EmailId)){
					appLog.info(M15CFN1+" "+M15CLN1+" successfully invited from "+institutions.get(7));
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" didnot get the invitation from "+institutions.get(7));
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" didnot get the invitation from "+institutions.get(7));
				}
				if(fp.inviteContact(environment, mode, institutions.get(8), M15C1EmailId, null, FolderType.Standard, "Download", "yes", "No", null, Workspace.FundraisingWorkspace, M15C1EmailId)){
					appLog.info(M15CFN1+" "+M15CLN1+" successfully invited from "+institutions.get(8));
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" didnot get the invitation from "+institutions.get(8));
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" didnot get the invitation from "+institutions.get(8));
				}
				if(fp.inviteContact(environment, mode, institutions.get(9), M15C1EmailId, null, FolderType.Standard, "Download", "yes", "No", null, Workspace.FundraisingWorkspace, M15C1EmailId)){
					appLog.info(M15CFN1+" "+M15CLN1+" successfully invited from "+institutions.get(9));
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" didnot get the invitation from "+institutions.get(9));
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" didnot get the invitation from "+institutions.get(9));
				}
				if(fp.clickOnTab(TabName.ContactTab)){
					if(cp.clickOnCreatedContact(M15CFN1, M15CLN1, M15C1EmailId)){
						switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
						scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Workspace view.");
						if(fp.verifyFolderPathdummy("", M15Institution1, null, M15FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)){
							appLog.info(M15Institution1+" folder is verified.");
						} else {
							appLog.error(M15Institution1+" folder is not present on contact page.");
							sa.assertTrue(false,M15Institution1+" folder is not present on contact page.");
						}
						if(fp.verifyFolderPathdummy("", institutions.get(1), null, M15FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)){
							appLog.info(institutions.get(1)+" folder is verified.");
						} else {
							appLog.error(institutions.get(1)+" folder is not present on contact page.");
							sa.assertTrue(false,institutions.get(1)+" folder is not present on contact page.");
						}
						if(fp.verifyFolderPathdummy("", institutions.get(2), null, M15FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)){
							appLog.info(institutions.get(2)+" folder is verified.");
						} else {
							appLog.error(institutions.get(2)+" folder is not present on contact page.");
							sa.assertTrue(false,institutions.get(2)+" folder is not present on contact page.");
						}
						if(fp.verifyFolderPathdummy("", institutions.get(3), null, M15FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)){
							appLog.info(institutions.get(3)+" folder is verified.");
						} else {
							appLog.error(institutions.get(3)+" folder is not present on contact page.");
							sa.assertTrue(false,institutions.get(3)+" folder is not present on contact page.");
						}
						if(fp.verifyFolderPathdummy("", institutions.get(4), null, M15FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)){
							appLog.info(institutions.get(4)+" folder is verified.");
						} else {
							appLog.error(institutions.get(4)+" folder is not present on contact page.");
							sa.assertTrue(false,institutions.get(4)+" folder is not present on contact page.");
						}
						if(fp.verifyFolderPathdummy("", institutions.get(5), null, M15FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)){
							appLog.info(institutions.get(5)+" folder is verified.");
						} else {
							appLog.error(institutions.get(5)+" folder is not present on contact page.");
							sa.assertTrue(false,institutions.get(5)+" folder is not present on contact page.");
						}
						if(fp.verifyFolderPathdummy("", institutions.get(6), null, M15FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)){
							appLog.info(institutions.get(6)+" folder is verified.");
						} else {
							appLog.error(institutions.get(6)+" folder is not present on contact page.");
							sa.assertTrue(false,institutions.get(6)+" folder is not present on contact page.");
						}
						if(fp.verifyFolderPathdummy("", institutions.get(7), null, M15FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)){
							appLog.info(institutions.get(7)+" folder is verified.");
						} else {
							appLog.error(institutions.get(7)+" folder is not present on contact page.");
							sa.assertTrue(false,institutions.get(7)+" folder is not present on contact page.");
						}
						if(fp.verifyFolderPathdummy("", institutions.get(8), null, M15FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)){
							appLog.info(institutions.get(8)+" folder is verified.");
						} else {
							appLog.error(institutions.get(8)+" folder is not present on contact page.");
							sa.assertTrue(false,institutions.get(8)+" folder is not present on contact page.");
						}
						if(fp.verifyFolderPathdummy("", institutions.get(9), null, M15FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)){
							appLog.info(institutions.get(9)+" folder is verified.");
						} else {
							appLog.error(institutions.get(9)+" folder is not present on contact page.");
							sa.assertTrue(false,institutions.get(9)+" folder is not present on contact page.");
						}
					} else {
						appLog.error(M15CFN1+" "+M15CLN1+" is not present in list, So cannot verify structure on contact page.");
						sa.assertTrue(false,M15CFN1+" "+M15CLN1+" is not present in list, So cannot verify structure on contact page.");
					}
				} else {
					appLog.error("Contacts tab cannot be clicked, So cannot verify structure at contact page.");
					sa.assertTrue(false,"Contacts tab cannot be clicked, So cannot verify structure at contact page.");
				}
			} else {
				appLog.error(M15FundName1+" fund is not present in the list, SO cannot continue with the testcase.");
				sa.assertTrue(false,M15FundName1+" fund is not present in the list, SO cannot continue with the testcase.");
			}
		} else {
			appLog.error("Cannot clikc on funds tab, So cannot invite contacts.");
			sa.assertTrue(false,"Cannot clikc on funds tab, So cannot invite contacts.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M15tc037_Generate10000AlertsFromCRMSide(String environment, String mode){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String StandardFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		boolean flag = true;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				List<String> institutions = getValueBasedOnLabel(excelPath, "Institutions", excelLabel.Institutions_Name, 0).subList(0, 10);
				String institutionOrLPName = createStringOutOfList(institutions);
				if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, StandardFolderPath, institutionOrLPName, "UploadFiles\\BulkFiles\\Standard_MultiInstitutionsAlert", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
					if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, StandardFolderPath, institutionOrLPName, "UploadFiles\\BulkFiles\\Standard_MultiInstitutionsAlert10", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
						appLog.info("Upload successful.");
					} else {
						appLog.error("Not able to upload file so 10000 alerts cannot be generated.");
						sa.assertTrue(false,"Not able to upload file so 10000 alerts cannot be generated.");
						flag = false;
					}
					if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, StandardFolderPath, institutionOrLPName, "UploadFiles\\BulkFiles\\Standard_MultiInstitutionsAlert2", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
						appLog.info("Upload successful.");
					} else {
						appLog.error("Not able to upload file so 10000 alerts cannot be generated.");
						sa.assertTrue(false,"Not able to upload file so 10000 alerts cannot be generated.");
						flag = false;
					}
					if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, StandardFolderPath, institutionOrLPName, "UploadFiles\\BulkFiles\\Standard_MultiInstitutionsAlert3", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
						appLog.info("Upload successful.");
					} else {
						appLog.error("Not able to upload file so 10000 alerts cannot be generated.");
						sa.assertTrue(false,"Not able to upload file so 10000 alerts cannot be generated.");
						flag = false;
					}
					if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, StandardFolderPath, institutionOrLPName, "UploadFiles\\BulkFiles\\Standard_MultiInstitutionsAlert4", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
						appLog.info("Upload successful.");
					} else {
						appLog.error("Not able to upload file so 10000 alerts cannot be generated.");
						sa.assertTrue(false,"Not able to upload file so 10000 alerts cannot be generated.");
						flag = false;
					}
					
					
					if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, StandardFolderPath, institutionOrLPName, "UploadFiles\\BulkFiles\\Standard_MultiInstitutionsAlert5", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
						appLog.info("Upload successful.");
					} else {
						appLog.error("Not able to upload file so 10000 alerts cannot be generated.");
						sa.assertTrue(false,"Not able to upload file so 10000 alerts cannot be generated.");
						flag = false;
					}
					if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, StandardFolderPath, institutionOrLPName, "UploadFiles\\BulkFiles\\Standard_MultiInstitutionsAlert6", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
						appLog.info("Upload successful.");
					} else {
						appLog.error("Not able to upload file so 10000 alerts cannot be generated.");
						sa.assertTrue(false,"Not able to upload file so 10000 alerts cannot be generated.");
						flag = false;
					}
					if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, StandardFolderPath, institutionOrLPName, "UploadFiles\\BulkFiles\\Standard_MultiInstitutionsAlert7", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
						appLog.info("Upload successful.");
					} else {
						appLog.error("Not able to upload file so 10000 alerts cannot be generated.");
						sa.assertTrue(false,"Not able to upload file so 10000 alerts cannot be generated.");
						flag = false;
					}
					if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, StandardFolderPath, institutionOrLPName, "UploadFiles\\BulkFiles\\Standard_MultiInstitutionsAlert8", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
						appLog.info("Upload successful.");
					} else {
						appLog.error("Not able to upload file so 10000 alerts cannot be generated.");
						sa.assertTrue(false,"Not able to upload file so 10000 alerts cannot be generated.");
						flag = false;
					}
					if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, StandardFolderPath, institutionOrLPName, "UploadFiles\\BulkFiles\\Standard_MultiInstitutionsAlert9", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
						appLog.info("Upload successful.");
					} else {
						appLog.error("Not able to upload file so 10000 alerts cannot be generated.");
						sa.assertTrue(false,"Not able to upload file so 10000 alerts cannot be generated.");
						flag = false;
					}
				} else {
					appLog.error("Not able to upload file in folder, So cannot continue with the tc.");
					sa.assertTrue(false,"Not able to upload file in folder, So cannot continue with the tc.");
					flag = false;
				}
			} else {
				appLog.error(M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
				flag = false;
			}
		} else {
			appLog.error("Funds Tab cannot be clicked, So cannot continue with the testcase.");
			sa.assertTrue(false,"Funds Tab cannot be clicked, So cannot continue with the testcase.");
			flag = false;
		}
		if(flag){
			driver.close();
			config(browserToLaunch);
			lp.investorLogin(M15C1EmailId, adminPassword);
			lp = new LoginPageBusinessLayer(driver);
			InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
			if(click(driver, ifp.getAllDocumentsTab(30), "All documents tab", action.BOOLEAN)){
				if(ifp.getAllDocumentCount(30)!=null){
					int recordCount = getIntegerFromString(getText(driver, ifp.getAllDocumentCount(30), "", action.BOOLEAN)).get(0);
					if(recordCount>=1000){
						appLog.info("Record count is verified.");
					} else {
						appLog.error("Record Count on all document is not verified. expected: 1000+\tActual: "+recordCount);
						sa.assertTrue(false,"Record Count on all document is not verified. expected: 1000+\tActual: "+recordCount);
					}
				} else {
					appLog.error("Record count is not visible on All document page, So cannot verify count.");
					sa.assertTrue(false,"Record count is not visible on All document page, So cannot verify count.");
				}
				if(ifp.getShowMoreLink(30)!=null){
					appLog.info("Show more link is present is verified.");
				} else {
					appLog.error("Show more link is not visible.");
					sa.assertTrue(false,"Show more link is not visible.");
				}
			} else {
				appLog.error("Cannot click on All Documents tab, So cannot veirfy All documents.");
				sa.assertTrue(false,"Cannot click on All Documents tab, So cannot veirfy All documents.");
			}
			if(click(driver, ifp.getRecentActivitiesTab(30), "Recent Activities tab", action.BOOLEAN)){
				WebElement ele = FindElement(driver, "//*[text()='Records: 1999+']", "Record count", action.BOOLEAN, 30);
				if(ele!=null){
					appLog.info("Record count is verified successfully.");
				} else {
					appLog.error("Record count is not verified.");
					sa.assertTrue(false,"Record count is not verified.");
				}
				ele = FindElement(driver, "//a[@title='Show All']", "Show all link", action.BOOLEAN, 30);
				if(ele!=null){
					appLog.info("Show All link is verified.");
				} else {
					appLog.error("Show all link is not present on the page.");
					sa.assertTrue(false,"Show all link is not present on the page.");
				}
			} else {
				appLog.error("Cannot click on recent activity tab, So cannot veirfy recent activities.");
				sa.assertTrue(false,"Cannot click on recent activity tab, So cannot veirfy recent activities.");
			}
			if(ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)){
				if(fp.verifyFolderPathdummy(StandardFolderPath, M15Institution1, null, null, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)){
					WebElement ele = FindElement(driver, "//span[@id='gridDivRecords']", "Record Count", action.BOOLEAN, 30);
					if(ele!=null){
						int recordCount = Integer.parseInt(getText(driver, ele, "record Cound", action.BOOLEAN));
						if(recordCount>=1000){
							appLog.info("record count is verified on potential workspace.");
						} else {
							appLog.error("Record count is not verified. Expected: 1000+\tActual: "+recordCount);
							sa.assertTrue(false,"Record count is not verified. Expected: 1000+\tActual: "+recordCount);
						}
					} else {
						appLog.error("Report count is visible on workspace page.");
						sa.assertTrue(false,"Report count is visible on workspace page.");
					}
				} else {
					appLog.error(StandardFolderPath+" Folder structure is not verified, So cannot check files in folder.");
					sa.assertTrue(false,StandardFolderPath+" Folder structure is not verified, So cannot check files in folder.");
				}
			} else {
				appLog.error("Potential investment tab cannot be clciked, So cannot verify files in folder.");
				sa.assertTrue(false,"Potential investment tab cannot be clciked, So cannot verify files in folder.");
			}
			
		}
		switchToDefaultContent(driver);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M15tc038_Create10000AlertFromInvestorSide(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		boolean flag = false;
		String sqlQuery="select id from contact where email ='"+M15C1EmailId+"'";
		String fundIdSqlQuery="select id,name from fund__c where fund__c.CreatedBy.LastName = '"+CRMUser1LastName+"' and name='"+M15FundName1+"'";
		lp.CRMLogin(HubUserName, HubPassword);
		if(sendKeys(driver, lp.getGlobalSearch(30), M15C1EmailId, "Global search box", action.BOOLEAN)){
			if(click(driver, lp.getGlobalSearchButton(30), "Global search button", action.BOOLEAN)){
				String contactID = lp.getContactId(M15CFN1, M15CLN1);
				if(contactID!=null){
					if(sendKeys(driver, lp.getGlobalSearch(30), M15FundName1, "Global search box", action.BOOLEAN)){
						if(click(driver, lp.getGlobalSearchButton(30), "Global search button", action.BOOLEAN)){
							String fundID = lp.getFundId(M15FundName1);
							if(fundID!=null){
								if(lp.devConsole()){
									String parentWin = switchOnWindow(driver);
									if(parentWin!=null){
										SoftAssert executeQuery=lp.executeQueryOnDevConsole(sqlQuery);
										sa.combineAssertions(executeQuery);
										String ContactId=lp.getContactIdFromDevConsole(M15C1EmailId,null,null);
										SoftAssert FundsqlQueryExecute=lp.executeQueryOnDevConsole(fundIdSqlQuery);
										sa.combineAssertions(FundsqlQueryExecute);
										String FundId=lp.getContactIdFromDevConsole(null,M15FundName1,CRMUser1LastName);
										if(ContactId!=null && FundId!=null) {
											WebElement ele = FindElement(driver, "//span[@id='debugMenuEntry-btnInnerEl']", "file tab", action.BOOLEAN, 30);
											if(click(driver, ele, "Debug lik", action.BOOLEAN)){
												if(click(driver, FindElement(driver, "(//div[@class='menuEntryLeft'])[1]", "New console link", action.BOOLEAN, 30), "New console link", action.BOOLEAN)){
													ele = FindElement(driver, "//div[@class='CodeMirror-code']", "Code Editor", action.BOOLEAN, 30);
													if(ele!=null){
														click(driver, ele, "editor", action.BOOLEAN);
														ele = FindElement(driver, "//div[@class='CodeMirror-code']", "Code Editor", action.BOOLEAN, 30);
														Robot rob;
														try {
															rob = new Robot();
															rob.keyPress(KeyEvent.VK_CONTROL);
															ThreadSleep(1000);
															rob.keyPress(KeyEvent.VK_A);
															ThreadSleep(1000);
															rob.keyRelease(KeyEvent.VK_CONTROL);
															ThreadSleep(1000);
															rob.keyRelease(KeyEvent.VK_A);
															ThreadSleep(1000);
															String code = "List<Alert__c> finalAlert = new List<Alert__c>();\nfor(integer i =0 ; i<10000 ; i++) {\nAlert__c dumALert = new Alert__c();\ndumALert.Box_File_Id__c = '3705217';\ndumALert.Contact__c = '"+ContactId+"';\ndumALert.Document_Name__c =  'MA3_'+i+'.pdf';\ndumALert.Fund__c = '"+FundId+"';\ndumALert.Workspace__c = 'Potential Investment';\ndumALert.BOX_Investor_Folder_ID__c = '572825';\ndumALert.Alert_Type_New__c = 'Document Viewed';\nfinalAlert.add(dumALert);\n}\nINSERT finalAlert;";
															setClipboardData(code);
															ThreadSleep(1000);
															rob.keyPress(KeyEvent.VK_CONTROL);
															ThreadSleep(1000);
															rob.keyPress(KeyEvent.VK_V);
															ThreadSleep(1000);
															rob.keyRelease(KeyEvent.VK_CONTROL);
															ThreadSleep(1000);
															rob.keyRelease(KeyEvent.VK_V);
														} catch (AWTException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
														if(click(driver, lp.getExecuteButton1(30), "execute Button", action.BOOLEAN)){
															ThreadSleep(120000);  
															appLog.info("Script successfully executed.");
															flag = true;
														} else {
															appLog.error("Execute button canot be clicked, So cannot continue with the tc.");
															sa.assertTrue(false,"Execute button canot be clicked, So cannot continue with the tc.");
														}
													} else {
														appLog.error("Code editor is not opening, So cannot continue with the ts.");
														sa.assertTrue(false,"Code editor is not opening, So cannot continue with the ts.");
													}
												} else {
													appLog.error("New console link cannot be clicked, SO cannot continue with the tc.");
													sa.assertTrue(false,"New console link cannot be clicked, SO cannot continue with the tc.");
												}
											} else {
												appLog.error("Not able to click on debug link.");
												sa.assertTrue(false,"Not able to click on debug link.");
											}
											
										}else {
											appLog.error("Not able to get Contact and fund id from dev console editor of contact email : "+M15C1EmailId);
											sa.assertTrue(false, "Not able to get Contact and fund id from dev console editor of contact email : "+M15C1EmailId);
										}
										driver.close();
										driver.switchTo().window(parentWin);
									} else {
										appLog.error("Dev console window is not opening after clicking on dev console link.");
										sa.assertTrue(false,"Dev console window is not opening after clicking on dev console link.");
									}
								} else {
									appLog.error("Dev Console link cannot be clicked, So cannot continue with the tc.");
									sa.assertTrue(false,"Dev Console link cannot be clicked, So cannot continue with the tc.");
								}
							} else {
								appLog.error(M15FundName1+" Fund Link is not available to get fund ID, So cannot continue with the testcase.");
								sa.assertTrue(false,M15FundName1+" Fund Link is not available to get fund ID, So cannot continue with the testcase.");
							}
						} else {
							appLog.error("Global search button cannot be clicked for fund search, So cannot continue with the testcase.");
							sa.assertTrue(false,"Global search button cannot be clicked for fund search, So cannot continue with the testcase.");
						}
					} else {
						appLog.error("Global search is not present, SO cannot continue with the testcase.");
						sa.assertTrue(false,"Global search is not present, SO cannot continue with the testcase.");
					}
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" Contact Link is not available to get contact ID, So cannot continue with the testcase.");
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" Contact Link is not available to get contact ID, So cannot continue with the testcase.");
				}
			} else {
				appLog.error("Global search button cannot be clicked, So cannot continue with the testcase.");
				sa.assertTrue(false,"Global search button cannot be clicked, So cannot continue with the testcase.");
			}
		} else {
			appLog.error("Global search is not present, SO cannot continue with the testcase.");
			sa.assertTrue(false,"Global search is not present, SO cannot continue with the testcase.");
		}
		lp.CRMlogout();
		if(flag){
			FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
			lp.CRMLogin(CRMUser1EmailID, adminPassword);
			switchToFrame(driver, 30, lp.getFrame( PageName.HomePage, 30));
			if(lp.getRecordCountOnHomePage(30)!=null){
				String text = getText(driver, lp.getRecordCountOnHomePage(30), "record count", action.BOOLEAN);
				if(text.trim().equalsIgnoreCase("Records: 1999+")){
					appLog.info("Record Count is verified.");
				} else {
					appLog.error("Record Count is not verified. Expected: Records: 1999+\tActual: "+text);
					sa.assertTrue(false,"Record Count is not verified. Expected: Records: 1999+\tActual: "+text);
				}
				if(click(driver, lp.getShowAllLink(null,30), "Show All link", action.BOOLEAN)){
					appLog.info("Show All link is verified.");
					if(lp.getRecordCountOnHomePage(30)!=null){
						text = getText(driver, lp.getRecordCountOnHomePage(30), "record count", action.BOOLEAN);
						if(text.trim().equalsIgnoreCase("Records: 10000")){
							appLog.info("Record Count is verified.");
						} else {
							appLog.error("Record Count is not verified. Expected: Records: 10000\tActual: "+text);
							sa.assertTrue(false,"Record Count is not verified. Expected: Records: 10000\tActual: "+text);
						}
					} else {
						appLog.error("Record is not showing on home page after clicking on show all link.");
						sa.assertTrue(false,"Record is not showing on home page after clicking on show all link.");
					}
				} else {
					appLog.error("Show All link is not verified.");
					sa.assertTrue(false,"Show All link is not verified.");
				}
				
			} else {
				appLog.error("Record is not showing on home page.");
				sa.assertTrue(false,"Record is not showing on home page.");
			}
			switchToDefaultContent(driver);
			if(lp.clickOnTab(TabName.FundsTab)){
				if(fp.clickOnCreatedFund(M15FundName1)){
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspaec view");
					if(click(driver, fp.getAlertHistoryLink(Workspace.FundraisingWorkspace, PageName.FundsPage, 30), "Alert history link", action.BOOLEAN)){
						ThreadSleep(3000);
						if(fp.getRecordCountAlertHistoryPopUp(Workspace.FundraisingWorkspace, 20)!=null){
							ThreadSleep(5000);
							String text = getText(driver, fp.getRecordCountAlertHistoryPopUp(Workspace.FundraisingWorkspace, 20), "record Count", action.BOOLEAN);
							if(text.trim().equalsIgnoreCase("Records: 1999+")){
								appLog.info("Record count is verified.");
							} else {
								appLog.error("Record count is not verified on fundraising workspace alert history pop up. Expected: Records: 1999+\tActual: "+text);
								sa.assertTrue(false,"Record count is not verified on fundraising workspace alert history pop up. Expected: Records: 1999+\tActual: "+text);
							}
							if(click(driver, lp.getShowAllLink(Workspace.FundraisingWorkspace,30), "Show All link", action.BOOLEAN)){
								ThreadSleep(5000);
								appLog.info("Show All link is verified.");
								if(lp.getRecordCountAlertHistoryPopUp(Workspace.FundraisingWorkspace, 20)!=null){
									text = getText(driver, lp.getRecordCountAlertHistoryPopUp(Workspace.FundraisingWorkspace, 20), "record count", action.BOOLEAN);
									if(text.trim().equalsIgnoreCase("Records: 10000")){
										appLog.info("Record Count is verified.");
									} else {
										appLog.error("Record Count is not verified on fundraising workspace alert history pop up. Expected: Records: 10000\tActual: "+text);
										sa.assertTrue(false,"Record Count is not verified on fundraising workspace alert history pop up. Expected: Records: 10000\tActual: "+text);
									}
								} else {
									appLog.error("Record is not showing on fundraising workspace alert history pop up after clicking on show all link.");
									sa.assertTrue(false,"Record is not showing on fundraising workspace alert history pop up after clicking on show all link.");
								}
							} else {
								appLog.error("Show All link is not verified on fundraising workspace alert history pop up.");
								sa.assertTrue(false,"Show All link is not verified on fundraising workspace alert history pop up.");
							}
						} else {
							appLog.error("Record Count is not present on fundraising workspace alert history pop up.");
							sa.assertTrue(false,"Record Count is not present on fundraising workspace alert history pop up.");
						}
						
					} else {
						appLog.error("Alert history link cannot be clicked, So cannot verify Record Count on alert history page.");
						sa.assertTrue(false,"Alert history link cannot be clicked, So cannot verify Record Count on alert history page.");
					}
					switchToDefaultContent(driver);
				} else {
					appLog.error(M15FundName1+" fund is not present in the list, So cannot verify record count on fundraising workspace.");
					sa.assertTrue(false,M15FundName1+" fund is not present in the list, So cannot verify record count on fundraising workspace.");
				}
			} else {
				appLog.error("Funds Tab cannot be clicked, So cannot verify record count on funds Page Potential Investment.");
				sa.assertTrue(false,"Funds Tab cannot be clicked, So cannot verify record count on funds Page Potential Investment.");
			}
			ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
			if (lp.clickOnTab(TabName.ContactTab)) {
				if(cp.clickOnCreatedContact(M15CFN1, M15CLN1, M15C1EmailId)){
					switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
					if(click(driver, cp.getAlertHistoryLink(Workspace.FundraisingWorkspace, PageName.ContactsPage, 30), "Alert history link", action.BOOLEAN)){
						ThreadSleep(5000);
						if(fp.getRecordCountAlertHistoryPopUp(Workspace.FundraisingWorkspace, 20)!=null){
							String text = getText(driver, fp.getRecordCountAlertHistoryPopUp(Workspace.FundraisingWorkspace, 20), "record Count", action.BOOLEAN);
							if(text.trim().equalsIgnoreCase("Records: 1999+")){
								appLog.info("Record count is verified.");
							} else {
								appLog.error("Record count is not verified on fundraising workspace alert history pop up contact page. Expected: Records: 1999+\tActual: "+text);
								sa.assertTrue(false,"Record count is not verified on fundraising workspace alert history pop up contact page. Expected: Records: 1999+\tActual: "+text);
							}
							if(click(driver, lp.getShowAllLink(Workspace.FundraisingWorkspace,30), "Show All link", action.BOOLEAN)){
								ThreadSleep(5000);
								appLog.info("Show All link is verified.");
								if(lp.getRecordCountAlertHistoryPopUp(Workspace.FundraisingWorkspace, 20)!=null){
									text = getText(driver, lp.getRecordCountAlertHistoryPopUp(Workspace.FundraisingWorkspace, 20), "record count", action.BOOLEAN);
									if(text.trim().equalsIgnoreCase("Records: 10000")){
										appLog.info("Record Count is verified.");
									} else {
										appLog.error("Record Count is not verified on fundraising workspace alert history pop up contact page. Expected: Records: 10000\tActual: "+text);
										sa.assertTrue(false,"Record Count is not verified on fundraising workspace alert history pop up contact page. Expected: Records: 10000\tActual: "+text);
									}
								} else {
									appLog.error("Record is not showing on fundraising workspace alert history pop up contact page after clicking on show all link.");
									sa.assertTrue(false,"Record is not showing on fundraising workspace alert history pop up contact page after clicking on show all link.");
								}
							} else {
								appLog.error("Show All link is not verified on fundraising workspace alert history pop up contact page.");
								sa.assertTrue(false,"Show All link is not verified on fundraising workspace alert history pop up contact page.");
							}
						} else {
							appLog.error("Record Count is not present on fundraising workspace alert history pop up contact page.");
							sa.assertTrue(false,"Record Count is not present on fundraising workspace alert history pop up contact page.");
						}
					} else {
						appLog.error("Alert History link cannot be clicked, So cannot verify record count on contact page.");
						sa.assertTrue(false,"Alert History link cannot be clicked, So cannot verify record count on contact page.");
					}
					switchToDefaultContent(driver);
					lp.CRMlogout();
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" Contact is not present in the list, So cannot verify record count on Investor workspace.");
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" Contact is not present in the list, So cannot verify record count on Investor workspace.");
				}
			} else {
				appLog.error("Contact Tab cannot be clicked, So cannot verify record count on contact Page fundraising workspace.");
				sa.assertTrue(false,"Contact Tab cannot be clicked, So cannot verify record count on contact Page fundraising workspace.");
			}
		} else {
			appLog.error("Not able to execute script, So cannot verify alerts at CRM side.");
			sa.assertTrue(false,"Not able to execute script, So cannot verify alerts at CRM side.");
		}
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M15tc039_InviteContactFromSharedFolder(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderPath[] = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath).split("<break>");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		boolean flag = false;
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName2)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view.");
				if(fp.verifyFolderPathdummy(folderPath[0], null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "Contact access icon", action.BOOLEAN)){
						if(fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)){
							if(fp.selectDeselectContactFromContactAccess(null, SelectDeselect.Select, AllOr1By1.All, M15Institution1, Workspace.FundraisingWorkspace, 30).isEmpty()){
								if(click(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), "Add selected contact button", action.BOOLEAN)){
									if(isAlertPresent(driver)){
										String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
										switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
										if(msg.equalsIgnoreCase(FundsPageErrorMessage.CannotAddMoreThan200ContactErrorMesssage.toString())){
											appLog.info("Message is successfully verified.");
										} else {
											appLog.error(FundsPageErrorMessage.CannotAddMoreThan200ContactErrorMesssage.toString()+" error message is not verified. Expected: "+FundsPageErrorMessage.CannotAddMoreThan200ContactErrorMesssage+"\tActual: "+msg);
											sa.assertTrue(false,"Error message is not verified. Expected: "+FundsPageErrorMessage.CannotAddMoreThan200ContactErrorMesssage+"\tActual: "+msg);
										}
										List<String> contact = new ArrayList<String>();
										contact.add(M15Contact201EmailId);
										if(fp.selectDeselectContactFromContactAccess(contact, SelectDeselect.Deselect, AllOr1By1.OneByOne, null, Workspace.FundraisingWorkspace, 30).isEmpty()){
											appLog.info("Successfully deselected contact '"+M15Contact201EmailId+"'.");
											if(click(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), "Add selected contact button", action.BOOLEAN)){
												if(fp.clickOnContactAccessApplyButton(null, CRMUser1EmailID, Workspace.FundraisingWorkspace, 30)){
													appLog.info("Successfully provided access.");
													flag = true;
												} else {
													appLog.error("Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution1+"'.");
													sa.assertTrue(false,"Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution1+"'.");
												}
											} else {
												appLog.error("Not able to provide access to contact '"+M15Contact201EmailId+"', So cannot continue to give permission to contacts of institution '"+M15Institution1+"'.");
												sa.assertTrue(false,"Not able to provide access to contact '"+M15Contact201EmailId+"', So cannot continue to give permission to contacts of institution '"+M15Institution1+"'.");
											}
										} else {
											appLog.error("Not able to deselect contact '"+M15Contact201EmailId+"'.");
											sa.assertTrue(false,"Not able to deselect contact '"+M15Contact201EmailId+"'.");
										}
									} else {
										appLog.error(FundsPageErrorMessage.CannotAddMoreThan200ContactErrorMesssage+" error message is not poping up.");
										sa.assertTrue(false,FundsPageErrorMessage.CannotAddMoreThan200ContactErrorMesssage+" error message is not poping up.");
									}
								} else {
									appLog.error("Add selected contacts button cannot be clicked, So cannot provide access from folder '"+folderPath[0]+"'.");
									sa.assertTrue(false,"Add selected contacts button cannot be clicked, So cannot provide access from folder '"+folderPath[0]+"'.");
								}
							} else {
								appLog.error("Contact of institution '"+M15Institution1+"' cannot be selected, So cannot provide access from folder '"+folderPath[0]+"'.");
								sa.assertTrue(false,"Contact of institution '"+M15Institution1+"' cannot be selected, So cannot provide access from folder '"+folderPath[0]+"'.");
							}
							if(flag){
								if(fp.verifyFolderPathdummy(folderPath[1], null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
									if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "Contact access icon", action.BOOLEAN)){
										if(fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)){
											if(fp.selectDeselectContactFromContactAccess(null, SelectDeselect.Select, AllOr1By1.All, M15Institution2, Workspace.FundraisingWorkspace, 30).isEmpty()){
												if(click(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), "Add selected contact button", action.BOOLEAN)){
													if(fp.clickOnContactAccessApplyButton(null, CRMUser1EmailID, Workspace.FundraisingWorkspace, 30)){
														appLog.info("Successfully provided access.");
														flag = true;
													} else {
														appLog.error("Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution2+"'.");
														sa.assertTrue(false,"Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution2+"'.");
													}
												} else {
													appLog.error("Add selected contacts button cannot be clicked, So cannot provide access from folder '"+folderPath[1]+"'.");
													sa.assertTrue(false,"Add selected contacts button cannot be clicked, So cannot provide access from folder '"+folderPath[1]+"'.");
												}
											} else {
												appLog.error("Contact of institution '"+M15Institution2+"' cannot be selected, So cannot provide access from folder '"+folderPath[1]+"'.");
												sa.assertTrue(false,"Contact of institution '"+M15Institution2+"' cannot be selected, So cannot provide access from folder '"+folderPath[1]+"'.");
											}
											if(flag){
												if(fp.verifyFolderPathdummy(folderPath[2], null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
													if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "Contact access icon", action.BOOLEAN)){
														if(fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)){
															if(fp.selectDeselectContactFromContactAccess(null, SelectDeselect.Select, AllOr1By1.All, M15Institution3, Workspace.FundraisingWorkspace, 30).isEmpty()){
																if(click(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), "Add selected contact button", action.BOOLEAN)){
																	if(fp.clickOnContactAccessApplyButton(null, CRMUser1EmailID, Workspace.FundraisingWorkspace, 30)){
																		appLog.info("Successfully provided access.");
																		flag = true;
																	} else {
																		appLog.error("Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution3+"'.");
																		sa.assertTrue(false,"Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution3+"'.");
																	}
																} else {
																	appLog.error("Add selected contacts button cannot be clicked, So cannot provide access from folder '"+folderPath[1]+"'.");
																	sa.assertTrue(false,"Add selected contacts button cannot be clicked, So cannot provide access from folder '"+folderPath[1]+"'.");
																}
															} else {
																appLog.error("Contact of institution '"+M15Institution3+"' cannot be selected, So cannot provide access from folder '"+folderPath[1]+"'.");
																sa.assertTrue(false,"Contact of institution '"+M15Institution3+"' cannot be selected, So cannot provide access from folder '"+folderPath[1]+"'.");
															}
															if(flag){
																if(fp.verifyFolderPathdummy(folderPath[3], null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
																	if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "Contact access icon", action.BOOLEAN)){
																		if(fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)){
																			if(fp.selectDeselectContactFromContactAccess(null, SelectDeselect.Select, AllOr1By1.All, M15Institution4, Workspace.FundraisingWorkspace, 30).isEmpty()){
																				if(click(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), "Add selected contact button", action.BOOLEAN)){
																					if(fp.clickOnContactAccessApplyButton(null, CRMUser1EmailID, Workspace.FundraisingWorkspace, 30)){
																						appLog.info("Successfully provided access.");
																						flag = true;
																					} else {
																						appLog.error("Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution4+"'.");
																						sa.assertTrue(false,"Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution4+"'.");
																					}
																				} else {
																					appLog.error("Add selected contacts button cannot be clicked, So cannot provide access from folder '"+folderPath[1]+"'.");
																					sa.assertTrue(false,"Add selected contacts button cannot be clicked, So cannot provide access from folder '"+folderPath[1]+"'.");
																				}
																			} else {
																				appLog.error("Contact of institution '"+M15Institution4+"' cannot be selected, So cannot provide access from folder '"+folderPath[1]+"'.");
																				sa.assertTrue(false,"Contact of institution '"+M15Institution4+"' cannot be selected, So cannot provide access from folder '"+folderPath[1]+"'.");
																			}
																			if(flag){
																				if(fp.verifyFolderPathdummy(folderPath[4], null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
																					if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "Contact access icon", action.BOOLEAN)){
																						if(fp.verifyContactAccessExpandCollapse(Workspace.FundraisingWorkspace)){
																							if(fp.selectDeselectContactFromContactAccess(null, SelectDeselect.Select, AllOr1By1.All, M15Institution5, Workspace.FundraisingWorkspace, 30).isEmpty()){
																								if(click(driver, fp.getaddselectContactBtn(Workspace.FundraisingWorkspace, 30), "Add selected contact button", action.BOOLEAN)){
																									if(fp.clickOnContactAccessApplyButton(null, CRMUser1EmailID, Workspace.FundraisingWorkspace, 30)){
																										appLog.info("Successfully provided access.");
																										flag = true;
																									} else {
																										appLog.error("Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution5+"'.");
																										sa.assertTrue(false,"Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution5+"'.");
																									}
																								} else {
																									appLog.error("Add selected contacts button cannot be clicked, So cannot provide access from folder '"+folderPath[1]+"'.");
																									sa.assertTrue(false,"Add selected contacts button cannot be clicked, So cannot provide access from folder '"+folderPath[1]+"'.");
																								}
																							} else {
																								appLog.error("Contact of institution '"+M15Institution5+"' cannot be selected, So cannot provide access from folder '"+folderPath[1]+"'.");
																								sa.assertTrue(false,"Contact of institution '"+M15Institution5+"' cannot be selected, So cannot provide access from folder '"+folderPath[1]+"'.");
																							}
																							//if left start here with if condition
																						} else {
																							appLog.error("Contact access expand icon cannot be clicked, So cannot continue with provide access from folder '"+folderPath[4]+"'");
																							sa.assertTrue(false,"Contact access expand icon cannot be clicked, So cannot continue with provide access from folder '"+folderPath[4]+"'");
																						}
																					} else {
																						appLog.error("Contact aaccess tab cannot be clicked, So cannot continue with provide access from folder '"+folderPath[4]+"'");
																						sa.assertTrue(false,"Contact aaccess tab cannot be clicked, So cannot continue with provide access from folder '"+folderPath[4]+"'");
																					}
																				} else {
																					appLog.error(folderPath[4]+" folder path is not verified, So cannot continue with tc.");
																					sa.assertTrue(false,folderPath[4]+" folder path is not verified, So cannot continue with tc.");
																				}
																			} else {
																				appLog.error("Contact access to contacts of institution '"+M15Institution4+"' is not successful, So cannot continue with the tc.");
																				sa.assertTrue(false,"Contact access to contacts of institution '"+M15Institution4+"' is not successful, So cannot continue with the tc.");
																			}
																		} else {
																			appLog.error("Contact access expand icon cannot be clicked, So cannot continue with provide access from folder '"+folderPath[3]+"'");
																			sa.assertTrue(false,"Contact access expand icon cannot be clicked, So cannot continue with provide access from folder '"+folderPath[3]+"'");
																		}
																	} else {
																		appLog.error("Contact aaccess tab cannot be clicked, So cannot continue with provide access from folder '"+folderPath[3]+"'");
																		sa.assertTrue(false,"Contact aaccess tab cannot be clicked, So cannot continue with provide access from folder '"+folderPath[3]+"'");
																	}
																} else {
																	appLog.error(folderPath[3]+" folder path is not verified, So cannot continue with tc.");
																	sa.assertTrue(false,folderPath[3]+" folder path is not verified, So cannot continue with tc.");
																}
															} else {
																appLog.error("Contact access to contacts of institution '"+M15Institution3+"' is not successful, So cannot continue with the tc.");
																sa.assertTrue(false,"Contact access to contacts of institution '"+M15Institution3+"' is not successful, So cannot continue with the tc.");
															}
														} else {
															appLog.error("Contact access expand icon cannot be clicked, So cannot continue with provide access from folder '"+folderPath[2]+"'");
															sa.assertTrue(false,"Contact access expand icon cannot be clicked, So cannot continue with provide access from folder '"+folderPath[2]+"'");
														}
													} else {
														appLog.error("Contact aaccess tab cannot be clicked, So cannot continue with provide access from folder '"+folderPath[2]+"'");
														sa.assertTrue(false,"Contact aaccess tab cannot be clicked, So cannot continue with provide access from folder '"+folderPath[2]+"'");
													}
												} else {
													appLog.error(folderPath[2]+" folder path is not verified, So cannot continue with tc.");
													sa.assertTrue(false,folderPath[2]+" folder path is not verified, So cannot continue with tc.");
												}
											} else {
												appLog.error("Contact access to contacts of institution '"+M15Institution2+"' is not successful, So cannot continue with the tc.");
												sa.assertTrue(false,"Contact access to contacts of institution '"+M15Institution2+"' is not successful, So cannot continue with the tc.");
											}
										} else {
											appLog.error("Contact access expand icon cannot be clicked, So cannot continue with provide access from folder '"+folderPath[1]+"'");
											sa.assertTrue(false,"Contact access expand icon cannot be clicked, So cannot continue with provide access from folder '"+folderPath[1]+"'");
										}
									} else {
										appLog.error("Contact aaccess tab cannot be clicked, So cannot continue with provide access from folder '"+folderPath[1]+"'");
										sa.assertTrue(false,"Contact aaccess tab cannot be clicked, So cannot continue with provide access from folder '"+folderPath[1]+"'");
									}
								} else {
									appLog.error(folderPath[1]+" folder path is not verified, So cannot continue with tc.");
									sa.assertTrue(false,folderPath[1]+" folder path is not verified, So cannot continue with tc.");
								}
							} else {
								appLog.error("Contact access to contacts of institution '"+M15Institution1+"' is not successful, So cannot continue with the tc.");
								sa.assertTrue(false,"Contact access to contacts of institution '"+M15Institution1+"' is not successful, So cannot continue with the tc.");
							}
						} else {
							appLog.error("Contact access expand icon cannot be clicked, So cannot continue with provide access from folder '"+folderPath[0]+"'");
							sa.assertTrue(false,"Contact access expand icon cannot be clicked, So cannot continue with provide access from folder '"+folderPath[0]+"'");
						}
					} else {
						appLog.error("Contact aaccess tab cannot be clicked, So cannot continue with provide access from folder '"+folderPath[0]+"'");
						sa.assertTrue(false,"Contact aaccess tab cannot be clicked, So cannot continue with provide access from folder '"+folderPath[0]+"'");
					}
				} else {
					appLog.error(folderPath[0]+" folder path is not verified, So cannot continue with tc.");
					sa.assertTrue(false,folderPath[0]+" folder path is not verified, So cannot continue with tc.");
				}
			} else {
				appLog.error(M15FundName2+" fund is not present in list, So cannot continue with the tc.");
				sa.assertTrue(false,M15FundName2+" fund is not present in list, So cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot invite contact from shared folder.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot invite contact from shared folder.");
		}
		
		if(flag){
			refresh(driver);
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view.");
			if(fp.verifyFolderPathdummy(folderPath[4], null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
				if(click(driver, fp.getContactAccessIcon(Workspace.FundraisingWorkspace, 30), "Contact access icon", action.BOOLEAN)){
					List <WebElement> ele = FindElements(driver, "//span[contains(@id,'grid11_DealDetailBWFR-cell-3')]/a", "Selected contact email ids");
					if(ele.size()==1000){
						appLog.info("All the contacts have successfully been provided access.");
					} else {
						appLog.error("All the contacts who have access are not present in the selected grid. Expected: 1000\tActual: "+ele.size());
						sa.assertTrue(false,"All the contacts who have access are not present in the selected grid. Expected: 1000\tActual: "+ele.size());
					}
				} else {
					appLog.error("Cannot click on contact access icon, SO cannot verify contacts in selected contact list.");
					sa.assertTrue(false,"Cannot click on contact access icon, SO cannot verify contacts in selected contact list.");
				}
			} else {
				appLog.error(folderPath[4]+" Folder path is not verified, So cannot verify contacts in selected contact list.");
				sa.assertTrue(false,folderPath[4]+" Folder path is not verified, So cannot verify contacts in selected contact list.");
			}
		} else {
			appLog.error("All access are not provided, So cannot verify contacts in selected contacts list.");
			sa.assertTrue(false,"All access are not provided, So cannot verify contacts in selected contacts list.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M15tc040_InviteContactFromDifferentLevel(String environment, String mode){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String[] folderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath).split("<break>");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName2)){
//				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
//				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view.");
				if(fp.inviteContact(environment, mode, null, M15C1EmailId, folderPath[0], FolderType.Shared, "Download", "yes", "No", null, Workspace.FundraisingWorkspace, M15C1EmailId)){
					appLog.info(M15CFN1+" "+M15CLN1+" successfully invited from "+folderPath[0]);
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" didnot get the invitation from "+folderPath[0]);
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" didnot get the invitation from "+folderPath[0]);
				}
				if(fp.inviteContact(environment, mode, null, M15C1EmailId, folderPath[1], FolderType.Shared, "Download", "yes", "No", null, Workspace.FundraisingWorkspace, M15C1EmailId)){
					appLog.info(M15CFN1+" "+M15CLN1+" successfully invited from "+folderPath[1]);
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" didnot get the invitation from "+folderPath[1]);
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" didnot get the invitation from "+folderPath[1]);
				}
				if(fp.inviteContact(environment, mode, null, M15C1EmailId, folderPath[2], FolderType.Shared, "Download", "yes", "No", null, Workspace.FundraisingWorkspace, M15C1EmailId)){
					appLog.info(M15CFN1+" "+M15CLN1+" successfully invited from "+folderPath[2]);
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" didnot get the invitation from "+folderPath[2]);
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" didnot get the invitation from "+folderPath[2]);
				}
				if(fp.inviteContact(environment, mode, null, M15C1EmailId, folderPath[3], FolderType.Shared, "Download", "yes", "No", null, Workspace.FundraisingWorkspace, M15C1EmailId)){
					appLog.info(M15CFN1+" "+M15CLN1+" successfully invited from "+folderPath[3]);
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" didnot get the invitation from "+folderPath[3]);
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" didnot get the invitation from "+folderPath[3]);
				}
				switchToDefaultContent(driver);
				lp.CRMlogout();
				driver.close();
				config(browserToLaunch);
				lp = new LoginPageBusinessLayer(driver);
				InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
				fp = new FundsPageBusinessLayer(driver);
				lp.investorLogin(M15C1EmailId, adminPassword);
				if(ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)){
					if(selectVisibleTextFromDropDown(driver, ifp.getPotentialAndCurrentInvestmentInvestmentDropdown(30), "select fund", M15FundName2)){
					if(fp.verifyFolderPathdummy(folderPath[0], null, null, null, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)){
						appLog.info(folderPath[0]+" folder is verified.");
					} else {
						appLog.error(folderPath[0]+" Folder is not present at investor side after provideing the access.");
						sa.assertTrue(false,folderPath[0]+" Folder is not present at investor side after provideing the access.");
					}
					if(fp.verifyFolderPathdummy(folderPath[1], null, null, null, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 31)){
						appLog.info(folderPath[1]+" folder is verified.");
					} else {
						appLog.error(folderPath[1]+" Folder is not present at investor side after provideing the access.");
						sa.assertTrue(false,folderPath[1]+" Folder is not present at investor side after provideing the access.");
					}
					if(fp.verifyFolderPathdummy(folderPath[2], null, null, null, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 32)){
						appLog.info(folderPath[2]+" folder is verified.");
					} else {
						appLog.error(folderPath[2]+" Folder is not present at investor side after provideing the access.");
						sa.assertTrue(false,folderPath[2]+" Folder is not present at investor side after provideing the access.");
					}
					if(fp.verifyFolderPathdummy(folderPath[3], null, null, null, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 33)){
						appLog.info(folderPath[3]+" folder is verified.");
					} else {
						appLog.error(folderPath[3]+" Folder is not present at investor side after provideing the access.");
						sa.assertTrue(false,folderPath[3]+" Folder is not present at investor side after provideing the access.");
					}
					lp.investorLogout();
				} else {
					appLog.error("Investment tab cannot be clicked, So cannot continue with the testcase.");
					sa.assertTrue(false,"Investment tab cannot be clicked, So cannot continue with the testcase.");
				}
			} else {
				appLog.error(M15FundName2+" is not present in the list, SO cannot continue with the tc.");
				sa.assertTrue(false,M15FundName2+" is not present in the list, SO cannot continue with the tc.");
			}
			} else {
				appLog.error(M15FundName2+" is not present in the list, SO cannot continue with the tc.");
				sa.assertTrue(false,M15FundName2+" is not present in the list, SO cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot continue with the tc.");
		}
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M15tc041_upload100FilesInSharedFolder(String environment, String mode){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName2)){
				if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, folderPath, null, "UploadFiles\\BulkFiles\\Shared_100Files", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
					appLog.info("Successfully uploaded files.");
				} else {
					appLog.error("Not able to upload 100 file.");
					sa.assertTrue(false,"Not able to upload 100 file.");
				}
				switchToDefaultContent(driver);
				lp.CRMlogout();
				driver.close();
				config(browserToLaunch);
				lp = new LoginPageBusinessLayer(driver);
				InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
				fp = new FundsPageBusinessLayer(driver);
				lp.investorLogin(M15C1EmailId, adminPassword);
				if(ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)){
					if(selectVisibleTextFromDropDown(driver, ifp.getPotentialAndCurrentInvestmentInvestmentDropdown(30), "select fund", M15FundName2)){
						if(fp.verifyFolderPathdummy(folderPath, null, null, null, PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, 30)){
							if(ifp.getInvestorRecordCount(30)!=null){
								int count = Integer.parseInt(getText(driver, ifp.getInvestorRecordCount(30), "", action.BOOLEAN));
								if(count == 100){
									appLog.info("All Files are uploaded successfully.");
								} else {
									appLog.error("All files are not uploaded.");
									sa.assertTrue(false,"All files are not uploaded.");
								}
							} else {
								appLog.error("Reocrd count is not present on the page.");
								sa.assertTrue(false,"Reocrd count is not present on the page.");
							}
							
						} else {
							appLog.error(folderPath+" Folder is not present at investor side, So cannot verify files.");
							sa.assertTrue(false,folderPath+" Folder is not present at investor side, So cannot verify files.");
						}
					} else {
						appLog.error(M15FundName2+" fund is not present in the list, So cannot verify file upload.");
						sa.assertTrue(false,M15FundName2+" fund is not present in the list, So cannot verify file upload.");
					}
					lp.investorLogout();
				} else {
					appLog.error("Investment tab cannot be clicked, So cannot continue with the testcase.");
					sa.assertTrue(false,"Investment tab cannot be clicked, So cannot continue with the testcase.");
				}
			} else {
				appLog.error(M15FundName2+" is not present in the list, SO cannot continue with the tc.");
				sa.assertTrue(false,M15FundName2+" is not present in the list, SO cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot continue with the tc.");
		}
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc043_1_BuildWorkspaceForFund1(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp=new FundsPageBusinessLayer(driver);
		String StandardFolderName=ExcelUtils.readData(excelPath,"FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		SoftAssert saa=new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(bp.clickOnTab(TabName.FundsTab)){
		if(fp.clickOnCreatedFund(M15FundName1)){
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view.");
			if(click(driver, fp.getBuildWorkspaceButton(Workspace.InvestorWorkspace, 30), "Build investor workspace button", action.BOOLEAN)){
				String Size=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund1", excelLabel.Fund_Size);
				String vintageyear=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund1", excelLabel.Fund_VintageYear);
				String contact=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund1", excelLabel.Fund_Contact);
				String phone=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund1", excelLabel.Fund_Phone);
				String email=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund1", excelLabel.Fund_Email);
				String description=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund1", excelLabel.Fund_Description);
				String folderStructureSheetName=ExcelUtils.readData(excelPath,"Bulk Folder Template 1", 0,1);
				if(!sendKeys(driver, fp.getSizeInMillionTextBox(Workspace.InvestorWorkspace	, 60), Size, "Size in Million text box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to size in million text box.");
				}
				if(!sendKeys(driver,fp.getVintageYear(Workspace.InvestorWorkspace, 60), vintageyear, "vintage Year", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to Vintage Year text box.");
				}
				if(!sendKeys(driver, fp.getContactTextBox(Workspace.InvestorWorkspace, 60), contact, "Contact text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to Contact text box.");
				}
				if(!sendKeys(driver, fp.getPhoneTextBox(Workspace.InvestorWorkspace, 60),phone, "phone text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to phone text box.");
				}
				if(!sendKeys(driver, fp.getEmailTextBox(Workspace.InvestorWorkspace, 60),email, "email text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to email text box.");
				}
				if(!sendKeys(driver, fp.getDescriptionTextBox(Workspace.InvestorWorkspace, 60),description, "description text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to description text box.");
				}
				if(click(driver, fp.getNext1Of3Button(Workspace.InvestorWorkspace, 60), "Next Button", action.BOOLEAN)){
					appLog.info("Click on next 1of3 button");
				}else{
					appLog.error("Not able to click on next 1of3 button");
					saa.assertTrue(false, "Not able to click on next 1of3 button");
				}
				if(!fp.importFolderTemplate(folderStructureSheetName, M15BulkFolderTemplateName1, WorkSpaceAction.IMPORTFOLDERTEMPLATE, Workspace.InvestorWorkspace, 60)){
					appLog.error("Folder sructure is not created properly");
					BaseLib.sa.assertTrue(false,"Folder sructure is not created properly");
				} else {
					appLog.info("Folder Structure is created successfully.");
				}
				if(fp.verifyFolderStructure(fp.folderStructureInExcel(excelPath,"Bulk Folder Template 1"), 5)){
					appLog.info("Folder structure is verified.");
				} else {
					appLog.error("Folder structure is not verified.");
					sa.assertTrue(false, "Folder structure is not verified.");
				}
				String id=null;
				id=fp.getCreatedFolderId(StandardFolderName, PageName.BuildStep2Of3, 20);
				if(fp.clickOnAddFolderButton(id)){
					String text=switchToAlertAndGetMessage(driver, 20, action.GETTEXT);
					if(text.equalsIgnoreCase(FundsPageErrorMessage.bulkAddFolderErrorMessage)){
						appLog.info("Bulk add folder message is verified");
					}else{
						appLog.error("Bulk add folder message is not verified");
						saa.assertTrue(false, "Bulk add folder message is not verified");
					}
				switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
				}else{
					appLog.error("Not able to click on add folder button");
					saa.assertTrue(false, "Not able to click on add folder button");
				}
				if(click(driver, fp.getNext2Of3Button(Workspace.InvestorWorkspace, 60), "Next button 2Of3", action.BOOLEAN)){
				appLog.info("Clicked on next 2 of 3 button");
				}else{
					appLog.error("Not able to click on next 2 of 3 button");
					saa.assertTrue(false, "Not able to click on next 2 of 3 button");
				}			
				List<String> investorList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.Institutions_Name, 0);
				List<String> limitedPartnerList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.LimitedPartner_Name, 0);
				for(int i = 0; i < investorList.size(); i++){
				if(fp.selectAccountFromBuildStep3of3(investorList.get(i)+"/"+limitedPartnerList.get(i), "Yes", gmailUserName,gmailPassword,"noreply@navatarinvestor.com",CRMUser1EmailID,"request update", Workspace.InvestorWorkspace, 60)){
					appLog.info("Investor added successfully");
				}else{
					appLog.error("Investor not added successfully");
					saa.assertTrue(false, "Investor not added successfully");
				}
				}
				if (fp.setFieldValueOnManageInvestor("Account:Legal Name", 1, Workspace.InvestorWorkspace,60)) {
					if (fp.setOperatorValueOnManageInvestor("equals", 1,Workspace.InvestorWorkspace,60)) {
						if (fp.setCriterionValueOnManageTarget("textbox", 1,"Bulk Inst181", "Account:Legal Name",Workspace.InvestorWorkspace,60)) {
							click(driver,fp.getManageInvestorFilterApplyButton(Workspace.InvestorWorkspace, 60), "Apply Button", action.BOOLEAN);
						WebElement	ele = fp.getLimitedPartnerCheckBox("Bulk Inst181", "Bulk LP181", Workspace.InvestorWorkspace, 60);
						if(click(driver, ele,"Limited Partner Check Box.", action.BOOLEAN)){
							String text=switchToAlertAndGetMessage(driver, 20, action.GETTEXT);
							if(text.equalsIgnoreCase(FundsPageErrorMessage.bulkAddInvestorErrorMessage)){
								appLog.info("Bulk add investor message is verified");
							}else{
								appLog.error("Bulk add investor message is not verified");
								saa.assertTrue(false, "Bulk add investor message is not verified");
							}
						switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
						}else{
							appLog.error("Not able to click on institution checkbox");
							saa.assertTrue(false, "Not able to click on institution checkbox");
						}
						}else{
							appLog.error("Not able to set criterion value");
							saa.assertTrue(false, "Not able to set criterion value");
						}
					}else{
						appLog.error("Not able to set operator value");
						saa.assertTrue(false, "Not able ot set opeartaor value");
					}
				}else{
					appLog.error("Not able to set field value");
					saa.assertTrue(false, "Not able to set field value");
				}					
						if(click(driver, fp.getDone3Of3Button(Workspace.InvestorWorkspace, 60), "Done Button", action.SCROLLANDBOOLEAN)){
							appLog.info("Clicked on done button");
						if(click(driver, fp.getCongratulationsCloseButton(Workspace.InvestorWorkspace, 60), "Close button", action.SCROLLANDBOOLEAN)){
							ThreadSleep(2000);
//						if(click(driver, fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60), "Manage investor icon", action.SCROLLANDBOOLEAN)){
//							investorList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.Institutions_Name, 0);
//							 limitedPartnerList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.LimitedPartner_Name, 0);
//							for(int i = 0; i < investorList.size(); i++){
//							if(fp.verifyAccountInManageTargetPopUp(investorList.get(i)+"/"+limitedPartnerList.get(i), "No", null, null, null, null, null, Workspace.InvestorWorkspace, 60)){
//								appLog.info("Investor added successfully");
//							}else{
//								appLog.error("Investor not added successfully");
//								saa.assertTrue(false, "Investor not added successfully");
//							}
//							}
//							if(click(driver, fp.getManageInvestorPopupCrossIcon(Workspace.InvestorWorkspace, 60), "Cross icon", action.SCROLLANDBOOLEAN)){
//								appLog.info("Clicked on cross icon");
//							}else{
//								appLog.error("Not able ot click on cross icon");
//								saa.assertTrue(false, "Not able ot click on cross icon");								
//							}							
//						}else{
//							appLog.error("Not able to click on manage invetsor");
//							saa.assertTrue(false, "Not able to clcik on manage investor");
//						}
						if(click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 60), "Manage folder", action.SCROLLANDBOOLEAN)){
							if(fp.verifyFolderStructure(fp.folderStructureInExcel(excelPath,"Bulk Folder Template 1"), 5)){
								appLog.info("Folder structure is verified.");
							} else {
								appLog.error("Folder structure is not verified.");
								sa.assertTrue(false, "Folder structure is not verified.");
							}
						if(click(driver, fp.getManageFolderCloseButton(Workspace.InvestorWorkspace, 60), "Close button", action.SCROLLANDBOOLEAN)){
							appLog.info("Clicked on close button");							
						}else{
							appLog.error("Nota ble to click on close button");
							saa.assertTrue(false, "Not able ot click on close button");
						}						
						}else{
							appLog.error("Not able to click on manage folder");
							saa.assertTrue(false, "Not able to click on manage folder");
						}							
						}else{
							appLog.error("Not able to click on close button");
							saa.assertTrue(false, "Not able to click on close button");
						}
						}else{
							appLog.error("Not able to click on done button");
							saa.assertTrue(false, "Not able to click on done button");
						}					
			}else{
				appLog.error("Not able to click on build fundraising button");
				saa.assertTrue(false, "Not able to click on build fundraising button");
			}
		}else{
			appLog.error("Not able to click on created fund");
			saa.assertTrue(false, "Not able to click on created fund");
		}
		}else{
			appLog.error("Not able to click on funds tab");
			saa.assertTrue(false, "Not able to click on funds tab");			
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();	
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc043_2_verifyFolderStructure() {
		SoftAssert saa=new SoftAssert();
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName1, Workspace.InvestorWorkspace, 30)){
			appLog.info("Successfully downloaded report");
		} else {
			appLog.error("Not able to download.");
			saa.assertTrue(false, "Not able to download.");
		}
		
		ThreadSleep(7000);
		String s =getFilesNameFromSystemFolder("DownloadedFiles");
		String fileName=null;
		if(s != null) {
			List<String> downloadedfileList=createListOutOfString(s);
			for (int i=0; i<downloadedfileList.size(); i++) {
				if(BoxPageBusinesslayer.fileName.equalsIgnoreCase(downloadedfileList.get(i))) {
					appLog.info("Downloaded File is available in download folder : "+BoxPageBusinesslayer.fileName);
					fileName=BoxPageBusinesslayer.fileName;
					break;
				}else {
					if(i==downloadedfileList.size()-1) {
						appLog.error(BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
						sa.assertTrue(false, BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot get file from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot get file from downloaded folder");
		}
		
		if(s!=null && !s.isEmpty() && fileName!=null) {
			List<String>  investorList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.Institutions_Name, 0);
			List<String> limitedPartnerList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.LimitedPartner_Name, 0);
			List<String> instiAndLP = new ArrayList<>();
			for (int i = 0; i<investorList.size(); i++) {
				instiAndLP.add(investorList.get(i)+"/"+limitedPartnerList.get(i));
			}
			String instiAndLPInS = CommonLib.createStringOutOfList(instiAndLP);
				List<String> notFoundFolders =box.verifyFolderStructureInHub(excelPath,System.getProperty("user.dir")+"\\DownloadedFiles\\"+fileName, "Bulk Folder Template 1", HubUserName, HubPassword, M15FundName1, Org1FirmName, instiAndLPInS, CRMUser1FirstName+" "+CRMUser1LastName, Workspace.InvestorWorkspace, 30);
				for (String string : notFoundFolders) {
//					System.err.println(string);
					saa.assertTrue(false, string+" folder structure not verified ");
				}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot verifying folder structure");
			sa.assertTrue(false, "Not file is available in downloaded file folder so cannot verifying folder structure");
		}
		
		
		sa.combineAssertions(saa);
		sa.assertAll();	
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc044_1_BuildWorkspaceForFund2(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp=new FundsPageBusinessLayer(driver);
		String StandardFolderName=ExcelUtils.readData(excelPath,"FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		SoftAssert saa=new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(bp.clickOnTab(TabName.FundsTab)){
		if(fp.clickOnCreatedFund(M15FundName2)){
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view.");
			if(click(driver, fp.getBuildWorkspaceButton(Workspace.InvestorWorkspace, 30), "Build Investor workspace button", action.BOOLEAN)){
				String Size=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund2", excelLabel.Fund_Size);
				String vintageyear=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund2", excelLabel.Fund_VintageYear);
				String contact=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund2", excelLabel.Fund_Contact);
				String phone=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund2", excelLabel.Fund_Phone);
				String email=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund2", excelLabel.Fund_Email);
				String description=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund2", excelLabel.Fund_Description);
				String folderStructureSheetName=ExcelUtils.readData(excelPath,"Bulk Folder Template 2", 0,1);
				if(!sendKeys(driver, fp.getSizeInMillionTextBox(Workspace.InvestorWorkspace	, 60), Size, "Size in Million text box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to size in million text box.");
				}
				if(!sendKeys(driver,fp.getVintageYear(Workspace.InvestorWorkspace, 60), vintageyear, "vintage Year", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to Vintage Year text box.");
				}
				if(!sendKeys(driver, fp.getContactTextBox(Workspace.InvestorWorkspace, 60), contact, "Contact text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to Contact text box.");
				}
				if(!sendKeys(driver, fp.getPhoneTextBox(Workspace.InvestorWorkspace, 60),phone, "phone text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to phone text box.");
				}
				if(!sendKeys(driver, fp.getEmailTextBox(Workspace.InvestorWorkspace, 60),email, "email text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to email text box.");
				}
				if(!sendKeys(driver, fp.getDescriptionTextBox(Workspace.InvestorWorkspace, 60),description, "description text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to description text box.");
				}
				if(click(driver, fp.getNext1Of3Button(Workspace.InvestorWorkspace, 60), "Next Button", action.BOOLEAN)){
					appLog.info("Click on next 1of3 button");
				}else{
					appLog.error("Not able to click on next 1of3 button");
					saa.assertTrue(false, "Not able to click on next 1of3 button");
				}
				if(!fp.importFolderTemplate(folderStructureSheetName, M15BulkFolderTemplateName2, WorkSpaceAction.IMPORTFOLDERTEMPLATE, Workspace.InvestorWorkspace, 60)){
					appLog.error("Folder sructure is not created properly");
					BaseLib.sa.assertTrue(false,"Folder sructure is not created properly");
				} else {
					appLog.info("Folder Structure is created successfully.");
				}
				if(fp.verifyFolderStructure(fp.folderStructureInExcel(excelPath,"Bulk Folder Template 2"), 5)){
					appLog.info("Folder strucuture is verified.");
				} else {
					appLog.error("Folder structure is not verified.");
					sa.assertTrue(false, "Folder structure is not verified.");
				}
				String id=null;
				id=fp.getCreatedFolderId(StandardFolderName, PageName.BuildStep2Of3, 20);
				if(fp.clickOnAddFolderButton(id)){
					String text=switchToAlertAndGetMessage(driver, 20, action.GETTEXT);
					if(text.equalsIgnoreCase(FundsPageErrorMessage.bulkAddFolderErrorMessage)){
						appLog.info("Bulk add folder message is verified");
					}else{
						appLog.error("Bulk add folder message is not verified");
						saa.assertTrue(false, "Bulk add folder message is not verified");
					}
				switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
				}else{
					appLog.error("Not able to click on add folder button");
					saa.assertTrue(false, "Not able to click on add folder button");
				}
				if(click(driver, fp.getNext2Of3Button(Workspace.InvestorWorkspace, 60), "Next button 2Of3", action.BOOLEAN)){
				appLog.info("Clicked on next 2 of 3 button");
				}else{
					appLog.error("Not able to click on next 2 of 3 button");
					saa.assertTrue(false, "Not able to click on next 2 of 3 button");
				}
				List<String> investorList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.Institutions_Name, 0);
				List<String> limitedPartnerList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.LimitedPartner_Name, 0);
				for(int i = 0; i < investorList.size(); i++){
				if(fp.selectAccountFromBuildStep3of3(investorList.get(i)+"/"+limitedPartnerList.get(i), "Yes",gmailUserName,gmailPassword,"noreply@navatarinvestor.com",CRMUser1EmailID,"request update", Workspace.InvestorWorkspace, 60)){
					appLog.info("Investor added successfully");
				}else{
					appLog.error("Investor not added successfully");
					saa.assertTrue(false, "Investor not added successfully");
				}
				}
				if (fp.setFieldValueOnManageInvestor("Account:Legal Name", 1, Workspace.InvestorWorkspace,60)) {
					if (fp.setOperatorValueOnManageInvestor("equals", 1,Workspace.InvestorWorkspace,60)) {
						if (fp.setCriterionValueOnManageTarget("textbox", 1,"Bulk Inst181", "Account:Legal Name",Workspace.InvestorWorkspace,60)) {
							click(driver,fp.getManageInvestorFilterApplyButton(Workspace.InvestorWorkspace, 60), "Apply Button", action.BOOLEAN);
						WebElement	ele = fp.getLimitedPartnerCheckBox("Bulk Inst181","Bulk LP181",Workspace.InvestorWorkspace, 60);
						if(click(driver, ele,"Limited Partner Check Box.", action.BOOLEAN)){
							String text=switchToAlertAndGetMessage(driver, 20, action.GETTEXT);
							if(text.equalsIgnoreCase(FundsPageErrorMessage.bulkAddInvestorErrorMessage)){
								appLog.info("Bulk add investor message is verified");
							}else{
								appLog.error("Bulk add investor message is not verified");
								saa.assertTrue(false, "Bulk add investor message is not verified");
							}
						switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
						}else{
							appLog.error("Not able to click on institution checkbox");
							saa.assertTrue(false, "Not able to click on institution checkbox");
						}
						}else{
							appLog.error("Not able to set criterion value");
							saa.assertTrue(false, "Not able to set criterion value");
						}
					}else{
						appLog.error("Not able to set operator value");
						saa.assertTrue(false, "Not able ot set opeartaor value");
					}
				}else{
					appLog.error("Not able to set field value");
					saa.assertTrue(false, "Not able to set field value");
				}					
						if(click(driver, fp.getDone3Of3Button(Workspace.InvestorWorkspace, 60), "Done Button", action.SCROLLANDBOOLEAN)){
							appLog.info("Clicked on done button");
						if(click(driver, fp.getCongratulationsCloseButton(Workspace.InvestorWorkspace, 60), "Close button", action.SCROLLANDBOOLEAN)){
							ThreadSleep(2000);
//						if(click(driver, fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60), "Manage investor icon", action.SCROLLANDBOOLEAN)){
//							 investorList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.Institutions_Name, 0);
//							limitedPartnerList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.LimitedPartner_Name, 0);
//							for(int i = 0; i < investorList.size(); i++){
//							if(fp.verifyAccountInManageTargetPopUp(investorList.get(i)+"/"+limitedPartnerList.get(i), "No", null, null, null, null, null, Workspace.InvestorWorkspace, 60)){
//								appLog.info("Investor added successfully");
//							}else{
//								appLog.error("Investor not added successfully");
//								saa.assertTrue(false, "Investor not added successfully");
//							}
//							}
//							if(click(driver, fp.getManageInvestorPopupCrossIcon(Workspace.InvestorWorkspace, 60), "Cross icon", action.SCROLLANDBOOLEAN)){
//								appLog.info("Clicked on cross icon");
//							}else{
//								appLog.error("Not able ot click on cross icon");
//								saa.assertTrue(false, "Not able ot click on cross icon");								
//							}							
//						}else{
//							appLog.error("Not able to click on manage invetsor");
//							saa.assertTrue(false, "Not able to clcik on manage investor");
//						}
						if(click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 60), "Manage folder", action.SCROLLANDBOOLEAN)){
							if(fp.verifyFolderStructure(fp.folderStructureInExcel(excelPath,"Bulk Folder Template 2"), 5)){
								appLog.info("Folder structure is verified.");
							} else {
								appLog.error("Folder structure is not verified.");
								sa.assertTrue(false, "Folder structure is not verified.");
							}
						if(click(driver, fp.getManageFolderCloseButton(Workspace.InvestorWorkspace, 60), "Close button", action.SCROLLANDBOOLEAN)){
							appLog.info("Clicked on close button");							
						}else{
							appLog.error("Nota ble to click on close button");
							saa.assertTrue(false, "Not able ot click on close button");
						}						
						}else{
							appLog.error("Not able to click on manage folder");
							saa.assertTrue(false, "Not able to click on manage folder");
						}							
						}else{
							appLog.error("Not able to click on close button");
							saa.assertTrue(false, "Not able to click on close button");
						}
						}else{
							appLog.error("Not able to click on done button");
							saa.assertTrue(false, "Not able to click on done button");
						}						
			}else{
				appLog.error("Not able to click on build investor button");
				saa.assertTrue(false, "Not able to click on build investor button");
			}
		}else{
			appLog.error("Not able to click on created fund");
			saa.assertTrue(false, "Not able to click on created fund");
		}
		}else{
			appLog.error("Not able to click on funds tab");
			saa.assertTrue(false, "Not able to click on funds tab");			
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc044_2_verifyFolderStructure() {
		SoftAssert saa=new SoftAssert();
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName2, Workspace.InvestorWorkspace, 30)){
			appLog.info("Successfully downloaded report");
		} else {
			appLog.error("Not able to download.");
			saa.assertTrue(false, "Not able to download.");
		}
		
		ThreadSleep(7000);
		String s =getFilesNameFromSystemFolder("DownloadedFiles");
		String fileName=null;
		if(s != null) {
			List<String> downloadedfileList=createListOutOfString(s);
			for (int i=0; i<downloadedfileList.size(); i++) {
				if(BoxPageBusinesslayer.fileName.equalsIgnoreCase(downloadedfileList.get(i))) {
					appLog.info("Downloaded File is available in download folder : "+BoxPageBusinesslayer.fileName);
					fileName=BoxPageBusinesslayer.fileName;
					break;
				}else {
					if(i==downloadedfileList.size()-1) {
						appLog.error(BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
						sa.assertTrue(false, BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot get file from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot get file from downloaded folder");
		}
		
		if(s!=null && !s.isEmpty() && fileName!=null) {
			List<String>  investorList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.Institutions_Name, 0);
			List<String> limitedPartnerList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.LimitedPartner_Name, 0);
			List<String> instiAndLP = new ArrayList<>();
			for (int i = 0; i<investorList.size(); i++) {
				instiAndLP.add(investorList.get(i)+"/"+limitedPartnerList.get(i));
			}
			String instiAndLPInS = CommonLib.createStringOutOfList(instiAndLP);
		
				List<String> notFoundFolders =box.verifyFolderStructureInHub(excelPath,System.getProperty("user.dir")+"\\DownloadedFiles\\"+fileName, "Bulk Folder Template 2", HubUserName, HubPassword, M15FundName2, Org1FirmName, instiAndLPInS, CRMUser1FirstName+" "+CRMUser1LastName, Workspace.InvestorWorkspace, 30);
				for (String string : notFoundFolders) {
//					System.err.println(string);
					saa.assertTrue(false, string+" folder structure not verified ");
				}
			
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot verifying folder structure");
			sa.assertTrue(false, "Not file is available in downloaded file folder so cannot verifying folder structure");
		}
		
		
		
		
		
		
		sa.combineAssertions(saa);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M15tc045_1_BuildWorkspaceForFund3(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp=new FundsPageBusinessLayer(driver);
		String CommonFolderName=ExcelUtils.readData(excelPath,"FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath);
		SoftAssert saa=new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(bp.clickOnTab(TabName.FundsTab)){
		if(fp.clickOnCreatedFund(M15FundName3)){
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "investor workspace view.");
			if(click(driver, fp.getBuildWorkspaceButton(Workspace.InvestorWorkspace, 30), "Build investor workspace button", action.BOOLEAN)){
				String Size=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund3", excelLabel.Fund_Size);
				String vintageyear=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund3", excelLabel.Fund_VintageYear);
				String contact=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund3", excelLabel.Fund_Contact);
				String phone=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund3", excelLabel.Fund_Phone);
				String email=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund3", excelLabel.Fund_Email);
				String description=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund3", excelLabel.Fund_Description);
				if(!sendKeys(driver, fp.getSizeInMillionTextBox(Workspace.InvestorWorkspace	, 60), Size, "Size in Million text box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to size in million text box.");
				}
				if(!sendKeys(driver,fp.getVintageYear(Workspace.InvestorWorkspace, 60), vintageyear, "vintage Year", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to Vintage Year text box.");
				}
				if(!sendKeys(driver, fp.getContactTextBox(Workspace.InvestorWorkspace, 60), contact, "Contact text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to Contact text box.");
				}
				if(!sendKeys(driver, fp.getPhoneTextBox(Workspace.InvestorWorkspace, 60),phone, "phone text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to phone text box.");
				}
				if(!sendKeys(driver, fp.getEmailTextBox(Workspace.InvestorWorkspace, 60),email, "email text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to email text box.");
				}
				if(!sendKeys(driver, fp.getDescriptionTextBox(Workspace.InvestorWorkspace, 60),description, "description text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to description text box.");
				}
				if(click(driver, fp.getNext1Of3Button(Workspace.InvestorWorkspace, 60), "Next Button", action.BOOLEAN)){
					appLog.info("Click on next 1of3 button");
				}else{
					appLog.error("Not able to click on next 1of3 button");
					saa.assertTrue(false, "Not able to click on next 1of3 button");
				}
				if(!fp.importFolderTemplateBulk(excelPath,"Bulk Folder Template 1", M15BulkFolderTemplateName1, WorkSpaceAction.CREATEFOLDERTEMPLATE, Workspace.InvestorWorkspace, 60)){
					appLog.error("Folder sructure is not created properly");
					BaseLib.sa.assertTrue(false,"Folder sructure is not created properly");
				} else {
					appLog.info("Folder Structure is created successfully.");
				}
				if(fp.verifyFolderStructure(fp.folderStructureInExcel(excelPath,"Bulk Folder Template 1"), 5)){
					appLog.info("Folder strucuture is verified.");
				} else {
					appLog.error("Folder structure is not verified.");
					sa.assertTrue(false, "Folder structure is not verified.");
				}
				String id=null;
				id=fp.getCreatedFolderId(CommonFolderName, PageName.BuildStep2Of3, 20);
				if(fp.clickOnAddFolderButton(id)){
					String text=switchToAlertAndGetMessage(driver, 20, action.GETTEXT);
					if(text.equalsIgnoreCase(FundsPageErrorMessage.bulkAddFolderErrorMessage)){
						appLog.info("Bulk add folder message is verified");
					}else{
						appLog.error("Bulk add folder message is not verified");
						saa.assertTrue(false, "Bulk add folder message is not verified");
					}
				switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
				}else{
					appLog.error("Not able to click on add folder button");
					saa.assertTrue(false, "Not able to click on add folder button");
				}
				if(click(driver, fp.getNext2Of3Button(Workspace.InvestorWorkspace, 60), "Next button 2Of3", action.BOOLEAN)){
				appLog.info("Clicked on next 2 of 3 button");
				}else{
					appLog.error("Not able to click on next 2 of 3 button");
					saa.assertTrue(false, "Not able to click on next 2 of 3 button");
				}
				List<String>  investorList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.Institutions_Name, 0);
				List<String> limitedPartnerList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.LimitedPartner_Name, 0);
				for(int i = 0; i < 100; i++){
				if(fp.selectAccountFromBuildStep3of3(investorList.get(i)+"/"+limitedPartnerList.get(i), "Yes",gmailUserName,gmailPassword,"noreply@navatarinvestor.com",CRMUser1EmailID,"request update", Workspace.InvestorWorkspace, 60)){
					appLog.info("Investor added successfully");
				}else{
					appLog.error("Investor not added successfully");
					saa.assertTrue(false, "Investor not added successfully");
				}
				}							
					if(click(driver, fp.getDone3Of3Button(Workspace.InvestorWorkspace, 60), "Done Button", action.SCROLLANDBOOLEAN)){
							appLog.info("Clicked on done button");
						if(click(driver, fp.getCongratulationsCloseButton(Workspace.InvestorWorkspace, 60), "Close button", action.SCROLLANDBOOLEAN)){
							ThreadSleep(2000);
						if(click(driver, fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60), "Manage investor icon", action.SCROLLANDBOOLEAN)){
							investorList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.Institutions_Name, 0);
							limitedPartnerList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.LimitedPartner_Name, 0);
							for(int i = 100; i < 180; i++){
							if(fp.selectAccountFromManageTargetPopUp(investorList.get(i)+"/"+limitedPartnerList.get(i), "Yes",gmailUserName,gmailPassword,"noreply@navatarinvestor.com",CRMUser1EmailID,"request update", Workspace.InvestorWorkspace, 60)){
								appLog.info("Investor added successfully");
							}else{
								appLog.error("Investor not added successfully");
								saa.assertTrue(false, "Investor not added successfully");
							}
							}	
							if (fp.setFieldValueOnManageInvestor("Account:Legal Name", 1, Workspace.InvestorWorkspace,60)) {
								if (fp.setOperatorValueOnManageInvestor("equals", 1,Workspace.InvestorWorkspace,60)) {
									if (fp.setCriterionValueOnManageTarget("textbox", 1,"Bulk Inst181", "Account:Legal Name",Workspace.InvestorWorkspace,60)) {
										click(driver,fp.getManageInvestorFilterApplyButton(Workspace.InvestorWorkspace, 60), "Apply Button", action.BOOLEAN);
									WebElement	ele = fp.getLimitedPartnerCheckBox("Bulk Inst181","Bulk LP181", Workspace.InvestorWorkspace, 60);
									if(click(driver, ele,"Limited Partner Check Box.", action.BOOLEAN)){
										String text=switchToAlertAndGetMessage(driver, 20, action.GETTEXT);
										if(text.equalsIgnoreCase(FundsPageErrorMessage.bulkAddInvestorErrorMessage)){
											appLog.info("Bulk add investor message is verified");
										}else{
											appLog.error("Bulk add investor message is not verified");
											saa.assertTrue(false, "Bulk add investor message is not verified");
										}
									switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
									}else{
										appLog.error("Not able to click on institution checkbox");
										saa.assertTrue(false, "Not able to click on institution checkbox");
									}
									}else{
										appLog.error("Not able to set criterion value");
										saa.assertTrue(false, "Not able to set criterion value");
									}
								}else{
									appLog.error("Not able to set operator value");
									saa.assertTrue(false, "Not able ot set opeartaor value");
								}
							}else{
								appLog.error("Not able to set field value");
								saa.assertTrue(false, "Not able to set field value");
							}							
					if(click(driver, fp.getManageInvestorPopupCrossIcon(Workspace.InvestorWorkspace, 60), "Cross icon", action.SCROLLANDBOOLEAN)){
								appLog.info("Clicked on cross icon");
							}else{
								appLog.error("Not able ot click on cross icon");
								saa.assertTrue(false, "Not able ot click on cross icon");								
							}							
						}else{
							appLog.error("Not able to click on manage invetsor");
							saa.assertTrue(false, "Not able to clcik on manage investor");
						}
						if(click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 60), "Manage folder", action.SCROLLANDBOOLEAN)){
							if(fp.verifyFolderStructure(fp.folderStructureInExcel(excelPath,"Bulk Folder Template 1"), 5)){
								appLog.info("Folder structure is verified.");
							} else {
								appLog.error("Folder structure is not verified.");
								sa.assertTrue(false, "Folder structure is not verified.");
							}
						if(click(driver, fp.getManageFolderCloseButton(Workspace.InvestorWorkspace, 60), "Close button", action.SCROLLANDBOOLEAN)){
							appLog.info("Clicked on close button");							
						}else{
							appLog.error("Nota ble to click on close button");
							saa.assertTrue(false, "Not able ot click on close button");
						}						
						}else{
							appLog.error("Not able to click on manage folder");
							saa.assertTrue(false, "Not able to click on manage folder");
						}							
						}else{
							appLog.error("Not able to click on close button");
							saa.assertTrue(false, "Not able to click on close button");
						}
						}else{
							appLog.error("Not able to click on done button");
							saa.assertTrue(false, "Not able to click on done button");
						}						
			}else{
				appLog.error("Not able to click on build fundraising button");
				saa.assertTrue(false, "Not able to click on build fundraising button");
			}
		}else{
			appLog.error("Not able to click on created fund");
			saa.assertTrue(false, "Not able to click on created fund");
		}
		}else{
			appLog.error("Not able to click on funds tab");
			saa.assertTrue(false, "Not able to click on funds tab");			
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();				
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc045_2_verifyFolderStructure() {
		SoftAssert saa=new SoftAssert();
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName3, Workspace.InvestorWorkspace, 30)){
			appLog.info("Successfully downloaded report");
		} else {
			appLog.error("Not able to download.");
			saa.assertTrue(false, "Not able to download.");
		}
		
		ThreadSleep(7000);
		String s =getFilesNameFromSystemFolder("DownloadedFiles");
		String fileName=null;
		if(s != null) {
			List<String> downloadedfileList=createListOutOfString(s);
			for (int i=0; i<downloadedfileList.size(); i++) {
				if(BoxPageBusinesslayer.fileName.equalsIgnoreCase(downloadedfileList.get(i))) {
					appLog.info("Downloaded File is available in download folder : "+BoxPageBusinesslayer.fileName);
					fileName=BoxPageBusinesslayer.fileName;
					break;
				}else {
					if(i==downloadedfileList.size()-1) {
						appLog.error(BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
						sa.assertTrue(false, BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot get file from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot get file from downloaded folder");
		}
		if(s!=null && !s.isEmpty() && fileName!=null) {
			List<String>  investorList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.Institutions_Name, 0);
			List<String> limitedPartnerList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.LimitedPartner_Name, 0);
			List<String> instiAndLP = new ArrayList<>();
			for (int i = 0; i<investorList.size(); i++) {
				instiAndLP.add(investorList.get(i)+"/"+limitedPartnerList.get(i));
			}
			String instiAndLPInS = CommonLib.createStringOutOfList(instiAndLP);
				List<String> notFoundFolders =box.verifyFolderStructureInHub(excelPath,System.getProperty("user.dir")+"\\DownloadedFiles\\"+fileName, "Bulk Folder Template 1", HubUserName, HubPassword, M15FundName3, Org1FirmName, instiAndLPInS, CRMUser1FirstName+" "+CRMUser1LastName, Workspace.InvestorWorkspace, 30);
				for (String string : notFoundFolders) {
//					System.err.println(string);
					saa.assertTrue(false, string+" folder structure not verified ");
				}
			
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot verifying folder structure");
			sa.assertTrue(false, "Not file is available in downloaded file folder so cannot verifying folder structure");
		}
		
		
		
		
		
	
		sa.combineAssertions(saa);
		sa.assertAll();	
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M15tc046_1_BuildWorkspaceForFund4(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp=new FundsPageBusinessLayer(driver);
		String CommonFolderName=ExcelUtils.readData(excelPath,"FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath);
		SoftAssert saa=new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(bp.clickOnTab(TabName.FundsTab)){
		if(fp.clickOnCreatedFund(M15FundName4)){
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view.");
			if(click(driver, fp.getBuildWorkspaceButton(Workspace.InvestorWorkspace, 30), "Build fundraising workspace button", action.BOOLEAN)){
				String Size=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund4", excelLabel.Fund_Size);
				String vintageyear=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund4", excelLabel.Fund_VintageYear);
				String contact=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund4", excelLabel.Fund_Contact);
				String phone=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund4", excelLabel.Fund_Phone);
				String email=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund4", excelLabel.Fund_Email);
				String description=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund4", excelLabel.Fund_Description);
				if(!sendKeys(driver, fp.getSizeInMillionTextBox(Workspace.InvestorWorkspace	, 60), Size, "Size in Million text box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to size in million text box.");
				}
				if(!sendKeys(driver,fp.getVintageYear(Workspace.InvestorWorkspace, 60), vintageyear, "vintage Year", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to Vintage Year text box.");
				}
				if(!sendKeys(driver, fp.getContactTextBox(Workspace.InvestorWorkspace, 60), contact, "Contact text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to Contact text box.");
				}
				if(!sendKeys(driver, fp.getPhoneTextBox(Workspace.InvestorWorkspace, 60),phone, "phone text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to phone text box.");
				}
				if(!sendKeys(driver, fp.getEmailTextBox(Workspace.InvestorWorkspace, 60),email, "email text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to email text box.");
				}
				if(!sendKeys(driver, fp.getDescriptionTextBox(Workspace.InvestorWorkspace, 60),description, "description text Box", action.BOOLEAN)){
					BaseLib.sa.assertTrue(false,"Not able to pass data to description text box.");
				}
				if(click(driver, fp.getNext1Of3Button(Workspace.InvestorWorkspace, 60), "Next Button", action.BOOLEAN)){
					appLog.info("Click on next 1of3 button");
				}else{
					appLog.error("Not able to click on next 1 of 3 button");
					saa.assertTrue(false, "Not able to click on next 1 of 3 button");
				}
				if(!fp.importFolderTemplateBulk(excelPath,"Bulk Folder Template 2", M15BulkFolderTemplateName2, WorkSpaceAction.CREATEFOLDERTEMPLATE, Workspace.InvestorWorkspace, 60)){
					appLog.error("Folder sructure is not created properly");
					BaseLib.sa.assertTrue(false,"Folder sructure is not created properly");
				} else {
					appLog.info("Folder Structure is created successfully.");
				}
				if(fp.verifyFolderStructure(fp.folderStructureInExcel(excelPath,"Bulk Folder Template 2"), 5)){
					appLog.info("Folder strucuture is verified.");
				} else {
					appLog.error("Folder structure is not verified.");
					sa.assertTrue(false, "Folder structure is not verified.");
				}
				String id=null;
				id=fp.getCreatedFolderId(CommonFolderName, PageName.BuildStep2Of3, 20);
				if(fp.clickOnAddFolderButton(id)){
					String text=switchToAlertAndGetMessage(driver, 20, action.GETTEXT);
					if(text.equalsIgnoreCase(FundsPageErrorMessage.bulkAddFolderErrorMessage)){
						appLog.info("Bulk add folder message is verified");
					}else{
						appLog.error("Bulk add folder message is not verified");
						saa.assertTrue(false, "Bulk add folder message is not verified");
					}
				switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
				}else{
					appLog.error("Not able to click on add folder button");
					saa.assertTrue(false, "Not able to click on add folder button");
				}
				if(click(driver, fp.getNext2Of3Button(Workspace.InvestorWorkspace, 60), "Next button 2Of3", action.BOOLEAN)){
				appLog.info("Clicked on next 2 of 3 button");
				}else{
					appLog.error("Not able to click on next 2 of 3 button");
					saa.assertTrue(false, "Not able to click on next 2 of 3 button");
				}
				List<String>  investorList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.Institutions_Name, 0);
				List<String> limitedPartnerList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.LimitedPartner_Name, 0);
				for(int i = 0; i < 100; i++){
				if(fp.selectAccountFromBuildStep3of3(investorList.get(i)+"/"+limitedPartnerList.get(i), "Yes",gmailUserName,gmailPassword,"noreply@navatarinvestor.com",CRMUser1EmailID,"request update", Workspace.InvestorWorkspace, 60)){
					appLog.info("Investor added successfully");
				}else{
					appLog.error("Investor not added successfully");
					saa.assertTrue(false, "Investor not added successfully");
				}
				}							
					if(click(driver, fp.getDone3Of3Button(Workspace.InvestorWorkspace, 60), "Done Button", action.SCROLLANDBOOLEAN)){
							appLog.info("Clicked on done button");
						if(click(driver, fp.getCongratulationsCloseButton(Workspace.InvestorWorkspace, 60), "Close button", action.SCROLLANDBOOLEAN)){
							ThreadSleep(2000);
						if(click(driver, fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60), "Manage investor icon", action.SCROLLANDBOOLEAN)){
						 investorList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.Institutions_Name, 0);
						limitedPartnerList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.LimitedPartner_Name, 0);
							for(int i = 100; i < 180; i++){
							if(fp.selectAccountFromManageTargetPopUp(investorList.get(i)+"/"+limitedPartnerList.get(i), "Yes",gmailUserName,gmailPassword,"noreply@navatarinvestor.com",CRMUser1EmailID,"request update", Workspace.InvestorWorkspace, 60)){
								appLog.info("Investor added successfully");
							}else{
								appLog.error("Investor not added successfully");
								saa.assertTrue(false, "Investor not added successfully");
							}
							}	
							if (fp.setFieldValueOnManageInvestor("Account:Legal Name", 1, Workspace.InvestorWorkspace,60)) {
								if (fp.setOperatorValueOnManageInvestor("equals", 1,Workspace.InvestorWorkspace,60)) {
									if (fp.setCriterionValueOnManageTarget("textbox", 1,"Bulk Inst181", "Account:Legal Name",Workspace.InvestorWorkspace,60)) {
										click(driver,fp.getManageInvestorFilterApplyButton(Workspace.InvestorWorkspace, 60), "Apply Button", action.BOOLEAN);
									WebElement	ele = fp.getLimitedPartnerCheckBox("Bulk Inst181","Bulk LP181", Workspace.InvestorWorkspace, 60);
									if(click(driver, ele,"Institution Check Box.", action.BOOLEAN)){
										String text=switchToAlertAndGetMessage(driver, 20, action.GETTEXT);
										if(text.equalsIgnoreCase(FundsPageErrorMessage.bulkAddInvestorErrorMessage)){
											appLog.info("Bulk add investor message is verified");
										}else{
											appLog.error("Bulk add investor message is not verified");
											saa.assertTrue(false, "Bulk add investor message is not verified");
										}
									switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
									}else{
										appLog.error("Not able to click on institution checkbox");
										saa.assertTrue(false, "Not able to click on institution checkbox");
									}
									}else{
										appLog.error("Not able to set criterion value");
										saa.assertTrue(false, "Not able to set criterion value");
									}
								}else{
									appLog.error("Not able to set operator value");
									saa.assertTrue(false, "Not able ot set opeartaor value");
								}
							}else{
								appLog.error("Not able to set field value");
								saa.assertTrue(false, "Not able to set field value");
							}							
					if(click(driver, fp.getManageInvestorPopupCrossIcon(Workspace.InvestorWorkspace, 60), "Cross icon", action.SCROLLANDBOOLEAN)){
								appLog.info("Clicked on cross icon");
							}else{
								appLog.error("Not able ot click on cross icon");
								saa.assertTrue(false, "Not able ot click on cross icon");								
							}							
						}else{
							appLog.error("Not able to click on manage invetsor");
							saa.assertTrue(false, "Not able to clcik on manage investor");
						}
						if(click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 60), "Manage folder", action.SCROLLANDBOOLEAN)){
							if(fp.verifyFolderStructure(fp.folderStructureInExcel(excelPath,"Bulk Folder Template 2"), 5)){
								appLog.info("Folder structure is verified.");
							} else {
								appLog.error("Folder structure is not verified.");
								sa.assertTrue(false, "Folder structure is not verified.");
							}
						if(click(driver, fp.getManageFolderCloseButton(Workspace.InvestorWorkspace, 60), "Close button", action.SCROLLANDBOOLEAN)){
							appLog.info("Clicked on close button");							
						}else{
							appLog.error("Nota ble to click on close button");
							saa.assertTrue(false, "Not able ot click on close button");
						}						
						}else{
							appLog.error("Not able to click on manage folder");
							saa.assertTrue(false, "Not able to click on manage folder");
						}							
						}else{
							appLog.error("Not able to click on close button");
							saa.assertTrue(false, "Not able to click on close button");
						}
						}else{
							appLog.error("Not able to click on done button");
							saa.assertTrue(false, "Not able to click on done button");
						}						
			}else{
				appLog.error("Not able to click on build fundraising button");
				saa.assertTrue(false, "Not able to click on build fundraising button");
			}
		}else{
			appLog.error("Not able to click on created fund");
			saa.assertTrue(false, "Not able to click on created fund");
		}
		}else{
			appLog.error("Not able to click on funds tab");
			saa.assertTrue(false, "Not able to click on funds tab");			
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();	
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc046_2_verifyStructure() {
		SoftAssert saa=new SoftAssert();
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName4, Workspace.InvestorWorkspace, 30)){
			appLog.info("Successfully downloaded report");
		} else {
			appLog.error("Not able to download.");
			saa.assertTrue(false, "Not able to download.");
		}
		
		ThreadSleep(7000);
		String s =getFilesNameFromSystemFolder("DownloadedFiles");
		String fileName=null;
		if(s != null) {
			List<String> downloadedfileList=createListOutOfString(s);
			for (int i=0; i<downloadedfileList.size(); i++) {
				if(BoxPageBusinesslayer.fileName.equalsIgnoreCase(downloadedfileList.get(i))) {
					appLog.info("Downloaded File is available in download folder : "+BoxPageBusinesslayer.fileName);
					fileName=BoxPageBusinesslayer.fileName;
					break;
				}else {
					if(i==downloadedfileList.size()-1) {
						appLog.error(BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
						sa.assertTrue(false, BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot get file from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot get file from downloaded folder");
		}
		if(s!=null && !s.isEmpty() && fileName!=null) {
			List<String>  investorList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.Institutions_Name, 0);
			List<String> limitedPartnerList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.LimitedPartner_Name, 0);
			List<String> instiAndLP = new ArrayList<>();
			for (int i = 0; i<investorList.size(); i++) {
				instiAndLP.add(investorList.get(i)+"/"+limitedPartnerList.get(i));
			}
			String instiAndLPInS = CommonLib.createStringOutOfList(instiAndLP);
				List<String> notFoundFolders =box.verifyFolderStructureInHub(excelPath,System.getProperty("user.dir")+"\\DownloadedFiles\\"+fileName, "Bulk Folder Template 2", HubUserName, HubPassword, M15FundName4, Org1FirmName, instiAndLPInS, CRMUser1FirstName+" "+CRMUser1LastName, Workspace.InvestorWorkspace, 30);
				for (String string : notFoundFolders) {
//					System.err.println(string);
					saa.assertTrue(false, string+" folder structure not verified ");
				}
			
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot verifying folder structure");
			sa.assertTrue(false, "Not file is available in downloaded file folder so cannot verifying folder structure");
		}
		
		
		
		
		
		sa.combineAssertions(saa);
		sa.assertAll();	
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc047_1_BuildWorkspaceForFund5(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp=new FundsPageBusinessLayer(driver);
		String StandardFolderName=ExcelUtils.readData(excelPath,"FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		SoftAssert saa=new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(bp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName5)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.InvestorWorkspace, 30), "Build Investor workspace button", action.BOOLEAN)){
					String Size=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund5", excelLabel.Fund_Size);
					String vintageyear=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund5", excelLabel.Fund_VintageYear);
					String contact=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund5", excelLabel.Fund_Contact);
					String phone=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund5", excelLabel.Fund_Phone);
					String email=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund5", excelLabel.Fund_Email);
					String description=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund5", excelLabel.Fund_Description);
					if(!sendKeys(driver, fp.getSizeInMillionTextBox(Workspace.InvestorWorkspace	, 60), Size, "Size in Million text box", action.BOOLEAN)){
						BaseLib.sa.assertTrue(false,"Not able to pass data to size in million text box.");
					}
					if(!sendKeys(driver,fp.getVintageYear(Workspace.InvestorWorkspace, 60), vintageyear, "vintage Year", action.BOOLEAN)){
						BaseLib.sa.assertTrue(false,"Not able to pass data to Vintage Year text box.");
					}
					if(!sendKeys(driver, fp.getContactTextBox(Workspace.InvestorWorkspace, 60), contact, "Contact text Box", action.BOOLEAN)){
						BaseLib.sa.assertTrue(false,"Not able to pass data to Contact text box.");
					}
					if(!sendKeys(driver, fp.getPhoneTextBox(Workspace.InvestorWorkspace, 60),phone, "phone text Box", action.BOOLEAN)){
						BaseLib.sa.assertTrue(false,"Not able to pass data to phone text box.");
					}
					if(!sendKeys(driver, fp.getEmailTextBox(Workspace.InvestorWorkspace, 60),email, "email text Box", action.BOOLEAN)){
						BaseLib.sa.assertTrue(false,"Not able to pass data to email text box.");
					}
					if(!sendKeys(driver, fp.getDescriptionTextBox(Workspace.InvestorWorkspace, 60),description, "description text Box", action.BOOLEAN)){
						BaseLib.sa.assertTrue(false,"Not able to pass data to description text box.");
					}
					if(click(driver, fp.getNext1Of3Button(Workspace.InvestorWorkspace, 60), "Next Button", action.BOOLEAN)){
						appLog.info("Click on next 1of3 button");
					}else{
						appLog.error("Not able to click on next 1 of 3 button");
						saa.assertTrue(false, "Not able to click on next 1 of 3 button");
					}
					if(click(driver, fp.getNext2Of3Button(Workspace.InvestorWorkspace, 60), "Next button 2Of3", action.BOOLEAN)){
						appLog.info("Clicked on next 2 of 3 button");
						}else{
							appLog.error("Not able to click on next 2 of 3 button");
							saa.assertTrue(false, "Not able to click on next 2 of 3 button");
						}
					if(click(driver, fp.getDone3Of3Button(Workspace.InvestorWorkspace, 60), "Done Button", action.SCROLLANDBOOLEAN)){
						appLog.info("Clicked on done button");
					if(click(driver, fp.getCongratulationsCloseButton(Workspace.InvestorWorkspace, 60), "Close button", action.SCROLLANDBOOLEAN)){
						ThreadSleep(2000);
					}else{
						appLog.error("Not able to click on close button");
						saa.assertTrue(false, "Not able to click on close button");
					}
					}else{
						appLog.error("Not able to click on done button");
						saa.assertTrue(false, "Not able to click on done button");
					}	
				if(click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 60), "Investor workspace", action.SCROLLANDBOOLEAN)){
					if(!fp.createFolderStructureFromExcelBulk(excelPath, "Bulk Folder Template 1", Workspace.InvestorWorkspace,PageName.ManageFolderPopUp,60)){
						appLog.error("Folder sructure is not created properly");
						BaseLib.sa.assertTrue(false,"Folder sructure is not created properly");
					} else {
						appLog.info("Folder Structure is created successfully.");
					}
					if(fp.verifyFolderStructure(fp.folderStructureInExcel(excelPath,"Bulk Folder Template 1"), 5)){
						appLog.info("Folder structure is verified.");
					} else {
						appLog.error("Folder structure is not verified.");
						sa.assertTrue(false, "Folder structure is not verified.");
					}
					String id=null;
					id=fp.getCreatedFolderId(StandardFolderName, PageName.ManageFolderPopUp, 20);
					if(fp.clickOnAddFolderButton(id)){
						String text=switchToAlertAndGetMessage(driver, 20, action.GETTEXT);
						if(text.equalsIgnoreCase(FundsPageErrorMessage.bulkAddFolderErrorMessage)){
							appLog.info("Bulk add folder message is verified");
						}else{
							appLog.error("Bulk add folder message is not verified");
							saa.assertTrue(false, "Bulk add folder message is not verified");
						}
					switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
					}else{
						appLog.error("Not able to click on add folder button");
						saa.assertTrue(false, "Not able to click on add folder button");
					}	
				if(click(driver, fp.getManageFolderCloseButton(Workspace.InvestorWorkspace, 60), "Manage folder close button", action.SCROLLANDBOOLEAN)){
					appLog.info("Clicked on close button successfully");
				}else{
					appLog.error("Not able to click on close button successfully");
					saa.assertTrue(false, "Not able to click on close button successfully");					
				}				
				}else{
					appLog.error("Not able to click on manage folder icon");
					saa.assertTrue(false, "Not able to click on manage folder icon");
				}
		if(click(driver, fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60), "Manage investor icon", action.SCROLLANDBOOLEAN)){
			List<String>  investorList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.Institutions_Name, 0);
			List<String> limitedPartnerList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.LimitedPartner_Name, 0);
			for(int i = 0; i < investorList.size(); i++){
			if(fp.selectAccountFromManageTargetPopUp(investorList.get(i)+"/"+limitedPartnerList.get(i), "No", null, null, null, null, null, Workspace.InvestorWorkspace, 60)){
				appLog.info("Investor added successfully");
			}else{
				appLog.error("Investor not added successfully");
				saa.assertTrue(false, "Investor not added successfully");
			}
			}
			if (fp.setFieldValueOnManageInvestor("Account:Legal Name", 1, Workspace.InvestorWorkspace,60)) {
				if (fp.setOperatorValueOnManageInvestor("equals", 1,Workspace.InvestorWorkspace,60)) {
					if (fp.setCriterionValueOnManageTarget("textbox", 1,"Bulk Inst181", "Account:Legal Name",Workspace.InvestorWorkspace,60)) {
						click(driver,fp.getManageInvestorFilterApplyButton(Workspace.InvestorWorkspace, 60), "Apply Button", action.BOOLEAN);
					WebElement	ele = fp.getLimitedPartnerCheckBox("Bulk Inst181","Bulk LP181", Workspace.InvestorWorkspace, 60);
					if(click(driver, ele,"Institution Check Box.", action.BOOLEAN)){
						String text=switchToAlertAndGetMessage(driver, 20, action.GETTEXT);
						if(text.equalsIgnoreCase(FundsPageErrorMessage.bulkAddInvestorErrorMessage)){
							appLog.info("Bulk add investor message is verified");
						}else{
							appLog.error("Bulk add investor message is not verified");
							saa.assertTrue(false, "Bulk add investor message is not verified");
						}
					switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
					}else{
						appLog.error("Not able to click on institution checkbox");
						saa.assertTrue(false, "Not able to click on institution checkbox");
					}
					}else{
						appLog.error("Not able to set criterion value");
						saa.assertTrue(false, "Not able to set criterion value");
					}
				}else{
					appLog.error("Not able to set operator value");
					saa.assertTrue(false, "Not able ot set opeartaor value");
				}
			}else{
				appLog.error("Not able to set field value");
				saa.assertTrue(false, "Not able to set field value");
			}							
	if(click(driver, fp.getManageInvestorPopupCrossIcon(Workspace.InvestorWorkspace, 60), "Cross icon", action.SCROLLANDBOOLEAN)){
				appLog.info("Clicked on cross icon");
			}else{
				appLog.error("Not able ot click on cross icon");
				saa.assertTrue(false, "Not able ot click on cross icon");								
			}				
		}else{
			appLog.error("Not able to click on manage investor icon ");
			saa.assertTrue(false, "Not able ot click on manage investor icon");
		}							
		}else{
			appLog.error("Not able to click on build fundraising button");
			saa.assertTrue(false, "Not able to click on build fundraising button");
		}
	}else{
		appLog.error("Not able to click on created fund");
		saa.assertTrue(false, "Not able to click on created fund");
	}
		}else{
			appLog.error("Not able to click on funds tab");
			saa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.combineAssertions(saa);
		sa.assertAll();	
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc047_2_verifyFolderStructure() {
		SoftAssert saa=new SoftAssert();
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName5, Workspace.InvestorWorkspace, 30)){
			appLog.info("Successfully downloaded report");
		} else {
			appLog.error("Not able to download.");
			saa.assertTrue(false, "Not able to download.");
		}
		
		ThreadSleep(7000);
		String s =getFilesNameFromSystemFolder("DownloadedFiles");
		String fileName=null;
		if(s != null) {
			List<String> downloadedfileList=createListOutOfString(s);
			for (int i=0; i<downloadedfileList.size(); i++) {
				if(BoxPageBusinesslayer.fileName.equalsIgnoreCase(downloadedfileList.get(i))) {
					appLog.info("Downloaded File is available in download folder : "+BoxPageBusinesslayer.fileName);
					fileName=BoxPageBusinesslayer.fileName;
					break;
				}else {
					if(i==downloadedfileList.size()-1) {
						appLog.error(BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
						sa.assertTrue(false, BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot get file from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot get file from downloaded folder");
		}
		if(s!=null && !s.isEmpty() && fileName!=null) {
			List<String>  investorList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.Institutions_Name, 0);
			List<String> limitedPartnerList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.LimitedPartner_Name, 0);
			List<String> instiAndLP = new ArrayList<>();
			for (int i = 0; i<investorList.size(); i++) {
				instiAndLP.add(investorList.get(i)+"/"+limitedPartnerList.get(i));
			}
			String instiAndLPInS = CommonLib.createStringOutOfList(instiAndLP);
				List<String> notFoundFolders =box.verifyFolderStructureInHub(excelPath,System.getProperty("user.dir")+"\\DownloadedFiles\\"+fileName, "Bulk Folder Template 1", HubUserName, HubPassword, M15FundName5, Org1FirmName, instiAndLPInS, CRMUser1FirstName+" "+CRMUser1LastName, Workspace.InvestorWorkspace, 30);
				for (String string : notFoundFolders) {
//					System.err.println(string);
					saa.assertTrue(false, string+" folder structure not verified ");
				}
			
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot verifying folder structure");
			sa.assertTrue(false, "Not file is available in downloaded file folder so cannot verifying folder structure");
		}
		
		
		
		
		sa.combineAssertions(saa);
		sa.assertAll();	
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc048_1_BuildWorkspaceForFund6(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp=new FundsPageBusinessLayer(driver);
		String StandardFolderName=ExcelUtils.readData(excelPath,"FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		SoftAssert saa=new SoftAssert();
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(bp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName6)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.InvestorWorkspace, 30), "Build fundraising workspace button", action.BOOLEAN)){
					String Size=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund6", excelLabel.Fund_Size);
					String vintageyear=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund6", excelLabel.Fund_VintageYear);
					String contact=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund6", excelLabel.Fund_Contact);
					String phone=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund6", excelLabel.Fund_Phone);
					String email=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund6", excelLabel.Fund_Email);
					String description=ExcelUtils.readData(excelPath,"Funds",excelLabel.Variable_Name, "M15Fund6", excelLabel.Fund_Description);
					if(!sendKeys(driver, fp.getSizeInMillionTextBox(Workspace.InvestorWorkspace	, 60), Size, "Size in Million text box", action.BOOLEAN)){
						BaseLib.sa.assertTrue(false,"Not able to pass data to size in million text box.");
					}
					if(!sendKeys(driver,fp.getVintageYear(Workspace.InvestorWorkspace, 60), vintageyear, "vintage Year", action.BOOLEAN)){
						BaseLib.sa.assertTrue(false,"Not able to pass data to Vintage Year text box.");
					}
					if(!sendKeys(driver, fp.getContactTextBox(Workspace.InvestorWorkspace, 60), contact, "Contact text Box", action.BOOLEAN)){
						BaseLib.sa.assertTrue(false,"Not able to pass data to Contact text box.");
					}
					if(!sendKeys(driver, fp.getPhoneTextBox(Workspace.InvestorWorkspace, 60),phone, "phone text Box", action.BOOLEAN)){
						BaseLib.sa.assertTrue(false,"Not able to pass data to phone text box.");
					}
					if(!sendKeys(driver, fp.getEmailTextBox(Workspace.InvestorWorkspace, 60),email, "email text Box", action.BOOLEAN)){
						BaseLib.sa.assertTrue(false,"Not able to pass data to email text box.");
					}
					if(!sendKeys(driver, fp.getDescriptionTextBox(Workspace.InvestorWorkspace, 60),description, "description text Box", action.BOOLEAN)){
						BaseLib.sa.assertTrue(false,"Not able to pass data to description text box.");
					}
					if(click(driver, fp.getNext1Of3Button(Workspace.InvestorWorkspace, 60), "Next Button", action.BOOLEAN)){
						appLog.info("Click on next 1of3 button");
					}else{
						appLog.error("Not able to click on next 1 of 3 button");
						saa.assertTrue(false, "Not able to click on next 1 of 3 button");
					}
					if(click(driver, fp.getNext2Of3Button(Workspace.InvestorWorkspace, 60), "Next button 2Of3", action.BOOLEAN)){
						appLog.info("Clicked on next 2 of 3 button");
						}else{
							appLog.error("Not able to click on next 2 of 3 button");
							saa.assertTrue(false, "Not able to click on next 2 of 3 button");
						}
					if(click(driver, fp.getDone3Of3Button(Workspace.InvestorWorkspace, 60), "Done Button", action.SCROLLANDBOOLEAN)){
						appLog.info("Clicked on done button");
					if(click(driver, fp.getCongratulationsCloseButton(Workspace.InvestorWorkspace, 60), "Close button", action.SCROLLANDBOOLEAN)){
						ThreadSleep(2000);
					}else{
						appLog.error("Not able to click on close button");
						saa.assertTrue(false, "Not able to click on close button");
					}
					}else{
						appLog.error("Not able to click on done button");
						saa.assertTrue(false, "Not able to click on done button");
					}	
				if(click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 60), "Investor workspace", action.SCROLLANDBOOLEAN)){
					if(!fp.createFolderStructureFromExcelBulk(excelPath, "Bulk Folder Template 2", Workspace.InvestorWorkspace,PageName.ManageFolderPopUp,60)){
						appLog.error("Folder sructure is not created properly");
						BaseLib.sa.assertTrue(false,"Folder sructure is not created properly");
					} else {
						appLog.info("Folder Structure is created successfully.");
					}
					if(fp.verifyFolderStructure(fp.folderStructureInExcel(excelPath,"Bulk Folder Template 2"), 5)){
						appLog.info("Folder structure is verified.");
					} else {
						appLog.error("Folder structure is not verified.");
						sa.assertTrue(false, "Folder structure is not verified.");
					}
					String id=null;
					id=fp.getCreatedFolderId(StandardFolderName, PageName.ManageFolderPopUp, 20);
					if(fp.clickOnAddFolderButton(id)){
						String text=switchToAlertAndGetMessage(driver, 20, action.GETTEXT);
						if(text.equalsIgnoreCase(FundsPageErrorMessage.bulkAddFolderErrorMessage)){
							appLog.info("Bulk add folder message is verified");
						}else{
							appLog.error("Bulk add folder message is not verified");
							saa.assertTrue(false, "Bulk add folder message is not verified");
						}
					switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
					}else{
						appLog.error("Not able to click on add folder button");
						saa.assertTrue(false, "Not able to click on add folder button");
					}	
				if(click(driver, fp.getManageFolderCloseButton(Workspace.InvestorWorkspace, 60), "Manage folder close button", action.SCROLLANDBOOLEAN)){
					appLog.info("Clicked on close button successfully");
				}else{
					appLog.error("Not able to click on close button successfully");
					saa.assertTrue(false, "Not able to click on close button successfully");					
				}				
				}else{
					appLog.error("Not able to click on manage folder icon");
					saa.assertTrue(false, "Not able to click on manage folder icon");
				}
		if(click(driver, fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60), "Manage investor icon", action.SCROLLANDBOOLEAN)){
			List<String>  investorList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.Institutions_Name, 0);
			List<String> limitedPartnerList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.LimitedPartner_Name, 0);
			for(int i = 0; i < investorList.size(); i++){
			if(fp.selectAccountFromManageTargetPopUp(investorList.get(i)+"/"+limitedPartnerList.get(i), "No", null, null, null, null, null, Workspace.InvestorWorkspace, 60)){
				appLog.info("Investor added successfully");
			}else{
				appLog.error("Investor not added successfully");
				saa.assertTrue(false, "Investor not added successfully");
			}
			}
			if (fp.setFieldValueOnManageInvestor("Account:Legal Name", 1, Workspace.InvestorWorkspace,60)) {
				if (fp.setOperatorValueOnManageInvestor("equals", 1,Workspace.InvestorWorkspace,60)) {
					if (fp.setCriterionValueOnManageTarget("textbox", 1,"Bulk Inst181", "Account:Legal Name",Workspace.InvestorWorkspace,60)) {
						click(driver,fp.getManageInvestorFilterApplyButton(Workspace.InvestorWorkspace, 60), "Apply Button", action.BOOLEAN);
					WebElement	ele = fp.getLimitedPartnerCheckBox("Bulk Inst181","Bulk LP181",Workspace.InvestorWorkspace, 60);
					if(click(driver, ele,"Institution Check Box.", action.BOOLEAN)){
						String text=switchToAlertAndGetMessage(driver, 20, action.GETTEXT);
						if(text.equalsIgnoreCase(FundsPageErrorMessage.bulkAddInvestorErrorMessage)){
							appLog.info("Bulk add investor message is verified");
						}else{
							appLog.error("Bulk add investor message is not verified");
							saa.assertTrue(false, "Bulk add investor message is not verified");
						}
					switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
					}else{
						appLog.error("Not able to click on institution checkbox");
						saa.assertTrue(false, "Not able to click on institution checkbox");
					}
					}else{
						appLog.error("Not able to set criterion value");
						saa.assertTrue(false, "Not able to set criterion value");
					}
				}else{
					appLog.error("Not able to set operator value");
					saa.assertTrue(false, "Not able ot set opeartaor value");
				}
			}else{
				appLog.error("Not able to set field value");
				saa.assertTrue(false, "Not able to set field value");
			}							
	if(click(driver, fp.getManageInvestorPopupCrossIcon(Workspace.InvestorWorkspace, 60), "Cross icon", action.SCROLLANDBOOLEAN)){
				appLog.info("Clicked on cross icon");
			}else{
				appLog.error("Not able ot click on cross icon");
				saa.assertTrue(false, "Not able ot click on cross icon");								
			}				
		}else{
			appLog.error("Not able to click on manage investor icon ");
			saa.assertTrue(false, "Not able ot click on manage investor icon");
		}							
		}else{
			appLog.error("Not able to click on build fundraising button");
			saa.assertTrue(false, "Not able to click on build fundraising button");
		}
	}else{
		appLog.error("Not able to click on created fund");
		saa.assertTrue(false, "Not able to click on created fund");
	}
		}else{
			appLog.error("Not able to click on funds tab");
			saa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();	
		sa.combineAssertions(saa);
		sa.assertAll();
//		config(browserToLaunch);
//		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
//		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName6, Workspace.InvestorWorkspace, 30)){
//			appLog.info("Successfully downloaded report");
//		} else {
//			appLog.error("Not able to download.");
//			saa.assertTrue(false, "Not able to download.");
//		}
//		
//		ThreadSleep(7000);
//		String s =getFilesNameFromSystemFolder("DownloadedFiles");
//		String fileName=null;
//		if(s != null) {
//			List<String> downloadedfileList=createListOutOfString(s);
//			for (int i=0; i<downloadedfileList.size(); i++) {
//				if(BoxPageBusinesslayer.fileName.equalsIgnoreCase(downloadedfileList.get(i))) {
//					appLog.info("Downloaded File is available in download folder : "+BoxPageBusinesslayer.fileName);
//					fileName=BoxPageBusinesslayer.fileName;
//					break;
//				}else {
//					if(i==downloadedfileList.size()-1) {
//						appLog.error(BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
//						sa.assertTrue(false, BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
//					}
//				}
//			}
//		}else {
//			appLog.error("Not file is available in downloaded file folder so cannot get file from downloaded folder");
//			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot get file from downloaded folder");
//		}
//		if(s!=null && !s.isEmpty() && fileName!=null) {
//			List<String>  investorList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.Institutions_Name, 0);
//			List<String> limitedPartnerList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.LimitedPartner_Name, 0);
//			List<String> instiAndLP = new ArrayList<>();
//			for (int i = 0; i<investorList.size(); i++) {
//				instiAndLP.add(investorList.get(i)+"/"+limitedPartnerList.get(i));
//			}
//			String instiAndLPInS = CommonLib.createStringOutOfList(instiAndLP);
//				List<String> notFoundFolders =box.verifyFolderStructureInHub(excelPath,System.getProperty("user.dir")+"\\DownloadedFiles\\"+fileName, "Bulk Folder Template 2", HubUserName, HubPassword, M15FundName6, Org1FirmName, instiAndLPInS, CRMUser1FirstName+" "+CRMUser1LastName, Workspace.InvestorWorkspace, 30);
//				for (String string : notFoundFolders) {
////					System.err.println(string);
//					saa.assertTrue(false, string+" folder structure not verified ");
//				}
//			
//		}else {
//			appLog.error("Not file is available in downloaded file folder so cannot verifying folder structure");
//			sa.assertTrue(false, "Not file is available in downloaded file folder so cannot verifying folder structure");
//		}
//		sa.combineAssertions(saa);
//		sa.assertAll();		
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc048_2_verifyFolderStructure() {
		SoftAssert saa=new SoftAssert();
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName6, Workspace.InvestorWorkspace, 30)){
			appLog.info("Successfully downloaded report");
		} else {
			appLog.error("Not able to download.");
			saa.assertTrue(false, "Not able to download.");
		}
		ThreadSleep(7000);
		String s =getFilesNameFromSystemFolder("DownloadedFiles");
		String fileName=null;
		if(s != null) {
			List<String> downloadedfileList=createListOutOfString(s);
			for (int i=0; i<downloadedfileList.size(); i++) {
				if(BoxPageBusinesslayer.fileName.equalsIgnoreCase(downloadedfileList.get(i))) {
					appLog.info("Downloaded File is available in download folder : "+BoxPageBusinesslayer.fileName);
					fileName=BoxPageBusinesslayer.fileName;
					break;
				}else {
					if(i==downloadedfileList.size()-1) {
						appLog.error(BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
						sa.assertTrue(false, BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot get file from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot get file from downloaded folder");
		}
		if(s!=null && !s.isEmpty() && fileName!=null) {
			List<String>  investorList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.Institutions_Name, 0);
			List<String> limitedPartnerList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.LimitedPartner_Name, 0);
			List<String> instiAndLP = new ArrayList<>();
			for (int i = 0; i<investorList.size(); i++) {
				instiAndLP.add(investorList.get(i)+"/"+limitedPartnerList.get(i));
			}
			String instiAndLPInS = CommonLib.createStringOutOfList(instiAndLP);
				List<String> notFoundFolders =box.verifyFolderStructureInHub(excelPath,System.getProperty("user.dir")+"\\DownloadedFiles\\"+fileName, "Bulk Folder Template 2", HubUserName, HubPassword, M15FundName6, Org1FirmName, instiAndLPInS, CRMUser1FirstName+" "+CRMUser1LastName, Workspace.InvestorWorkspace, 30);
				for (String string : notFoundFolders) {
//					System.err.println(string);
					saa.assertTrue(false, string+" folder structure not verified ");
				}
			
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot verifying folder structure");
			sa.assertTrue(false, "Not file is available in downloaded file folder so cannot verifying folder structure");
		}
		sa.combineAssertions(saa);
		sa.assertAll();	
	}
 
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc049_1_VerifyRemovalOfInvestorFromManageInvestorsPopUp(String environment, String mode){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp=new FundsPageBusinessLayer(driver);
		String LimitedPartner1 = ExcelUtils.readData(excelPath, "Limited Partner", excelLabel.Variable_Name, "M15LimitedPartner1", excelLabel.LimitedPartner_Name);
		String stdPath = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String dropImage="DropLoc.jpg";
		String folderpath1 = "UploadFiles\\Module15";
		String[] institutionname={"Bulk Inst180","Bulk Inst179","Bulk Inst178","Bulk Inst177","Bulk Inst176"};
		String[] LimitedPartnerName={"Bulk LP180","Bulk LP179","Bulk LP178","Bulk LP177","Bulk LP176"};
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(bp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName5)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view.");
				if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, dropImage, stdPath, M15Institution1+"/"+LimitedPartner1, folderpath1, UploadFileActions.SelectAll, UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 130)){
					appLog.info("Successfully uploaded file to "+stdPath);
				} else {
					appLog.error("Not able to upload file in "+stdPath);
					sa.assertTrue(false,"Not able to upload file in "+stdPath);
				}
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view.");	
				if(click(driver, fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60), "Manage investor icon", action.SCROLLANDBOOLEAN)){
					for (int i = 0; i < institutionname.length; i++) {
						if (fp.setFieldValueOnManageInvestor("Account:Legal Name", 1, Workspace.InvestorWorkspace,60)) {
							if (fp.setOperatorValueOnManageInvestor("equals", 1,Workspace.InvestorWorkspace,60)) {
								if (fp.setCriterionValueOnManageTarget("textbox", 1,institutionname[i], "Account:Legal Name",Workspace.InvestorWorkspace,60)) {
									click(driver,fp.getManageInvestorFilterApplyButton(Workspace.InvestorWorkspace, 60), "Apply Button", action.BOOLEAN);
									WebElement	ele = fp.getLimitedPartnerCheckBox(institutionname[i],LimitedPartnerName[i] , Workspace.InvestorWorkspace, 60);
									if(click(driver, ele,"Limited Partner Check Box.", action.BOOLEAN)){
										appLog.info("Clicked on Limited Partner checkbox");
									}else{
										appLog.error("Not able to click on Limited Partner checkbox");
										sa.assertTrue(false, "Not able to click on Limited Partner checkbox");
									}
								}else{
									appLog.error("Not able to set criterion value");
									sa.assertTrue(false, "Not able to set criterion value");
								}
							}else{
								appLog.error("Not able to set operator value");
								sa.assertTrue(false, "Not able ot set opeartaor value");
							}
						}else{
							appLog.error("Not able to set field value");
							sa.assertTrue(false, "Not able to set field value");
						}
					}
					if(click(driver, fp.getManageInvestorDoneButton(Workspace.InvestorWorkspace, 60), "Done button", action.SCROLLANDBOOLEAN)){
						appLog.info("Clicked on done button");
					}else{
						appLog.error("Not able to click on done button");
						sa.assertTrue(false, "Not able to click on done button");
					}		
				}else{
					appLog.error("Not able to click on manage investor icon");
					sa.assertTrue(false, "Not able to click on manage investor icon");
				}
				for (int i = 0; i < institutionname.length; i++) {
					if(!fp.clickOnInstituionFolder(institutionname[i], Workspace.InvestorWorkspace, 5)){
						appLog.info(institutionname[i]+" folder is not displaying");
					}else{
						appLog.error(institutionname[i]+" folder is displaying");
						sa.assertTrue(false, institutionname[i]+" folder is displaying");
					}			
				}
			}else{
				appLog.error("Not able to click on created fund");
				sa.assertTrue(false, "Not able to click on created fund");
			}
		}else{
			appLog.error("Not able to click on funds tab");
			sa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();		
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc049_2_verifyFolderStructure() {
		List<String> investorList = new ArrayList<String>();
		List<String> limitedPartnerList = new ArrayList<String>();
		List<String> downLoadedFolderPathList = new ArrayList<String>();
		List<String> itemNameList = new ArrayList<String>();
		String stdPath = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, "M15tc049_1_VerifyRemovalOfInvestorFromManageInvestorsPopUp", excelLabel.StandardPath);
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		String uploadedFile="Test123.pdf";
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName5, Workspace.InvestorWorkspace, 30)){
			appLog.info("Successfully downloaded report");
		} else {
			appLog.error("Not able to download.");
		}
		
		ThreadSleep(7000);
		String s =getFilesNameFromSystemFolder("DownloadedFiles");
		String fileName=null;
		if(s != null) {
			List<String> downloadedfileList=createListOutOfString(s);
			for (int i=0; i<downloadedfileList.size(); i++) {
				if(BoxPageBusinesslayer.fileName.equalsIgnoreCase(downloadedfileList.get(i))) {
					appLog.info("Downloaded File is available in download folder : "+BoxPageBusinesslayer.fileName);
					fileName=BoxPageBusinesslayer.fileName;
					investorList = getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
					limitedPartnerList = getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.Institutions_Name, 0);
					String downLoadedFolderPath=ExcelUtils.readAllDataForAColumn(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", 2, false);
					String ItemName = ExcelUtils.readAllDataForAColumn(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", 4, false);
					downLoadedFolderPathList=createListOutOfStringUsingComma(downLoadedFolderPath);
					itemNameList=createListOutOfStringUsingComma(ItemName);
					break;
				}else {
					if(i==downloadedfileList.size()-1) {
						appLog.error(BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
						sa.assertTrue(false, BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot get file from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot get file from downloaded folder");
		}
		if(fileName!=null && !fileName.isEmpty()) {
			for (int i = 0;i<investorList.size()-5;i++) {
				String filepath="All Files/"+Org1FirmName+"/Current Investment/"+M15FundName5+"/"+ investorList.get(i)+"/"+limitedPartnerList.get(i)+"/"+stdPath+"/"+uploadedFile;
				appLog.info("Going to verify path:  "+filepath);
				for(int j=0; j<downLoadedFolderPathList.size(); j++) {
					if (filepath.equalsIgnoreCase(downLoadedFolderPathList.get(j).trim())) {
						appLog.info("successfully found file of "+investorList.get(i)+" on folder structure");
						if(itemNameList.get(j).equalsIgnoreCase(uploadedFile)) {
							appLog.info("Item Name is matched for folder path  :"+downLoadedFolderPathList.get(i));
							
							
						}else {
							appLog.error("Item Name is not matched for folder path : "+downLoadedFolderPathList.get(i));
							sa.assertTrue(false, "Item Name is not matched for folder path : "+downLoadedFolderPathList.get(i));
						}
						break;
					}else {
						if(j==downLoadedFolderPathList.size()-1) {
							appLog.error("could not find file of "+investorList.get(i));
							sa.assertTrue(false, "could not find file of "+investorList.get(i));
						}
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot verify upload file name from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot verify upload file name from downloaded folder");
		}
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M15tc051_0_uploadDocumentInMultiInvestorInINV(String environment, String mode) {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		String stdPath = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath).split(",")[0];
		List<String> investorList = createListOutOfStringUsingComma(ExcelUtils.readAllDataForAColumn(excelPath, "Institutions", 1, false));
		List<String> lpList = createListOutOfStringUsingComma(ExcelUtils.readAllDataForAColumn(excelPath, "Limited Partner", 2, false));
		String dropImage="DropLoc.jpg";
		String folderpath1 = "UploadFiles\\Module15";
		List<String> InsAndLpList=new ArrayList<String>();
		for (int i=lpList.size()-1; i>=0; i--) {
			InsAndLpList.add(investorList.get(i)+"/"+lpList.get(i));
		}
		System.err.println(InsAndLpList);
		//upload
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M15FundName3)) {
				
				if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, dropImage, stdPath, createStringOutOfList(InsAndLpList), folderpath1, UploadFileActions.SelectAll, UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 130)){
					appLog.info("Successfully uploaded file to "+stdPath);
				} else {
					appLog.error("Not able to upload file in "+stdPath);
					sa.assertTrue(false,"Not able to upload file in "+stdPath);
				}
			}else {
				appLog.error("could not find fundname");
				sa.assertTrue(false, "could not find fundname");
			}
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc051_1_verifyFolderStructureInBox() {
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		List<String> investorList = new ArrayList<String>();
		List<String> downLoadedFolderPathList = new ArrayList<String>();
		List<String> itemNameList = new ArrayList<String>();
		List<String> lpList = new ArrayList<String>();
		String stdPath = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, "M15tc051_0_uploadDocumentInMultiInvestorInINV", excelLabel.StandardPath).split(",")[0];
		String uploadedFile="Test123.pdf";
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName3, Workspace.InvestorWorkspace, 30)){
			appLog.info("Successfully downloaded report");
		} else {
			appLog.error("Not able to download.");
			sa.assertTrue(false, "Not able to download.");
		}
		
		
		ThreadSleep(7000);
		String s =getFilesNameFromSystemFolder("DownloadedFiles");
		String fileName=null;
		if(s != null) {
			List<String> downloadedfileList=createListOutOfString(s);
			for (int i=0; i<downloadedfileList.size(); i++) {
				if(BoxPageBusinesslayer.fileName.equalsIgnoreCase(downloadedfileList.get(i))) {
					appLog.info("Downloaded File is available in download folder : "+BoxPageBusinesslayer.fileName);
					fileName=BoxPageBusinesslayer.fileName;
					investorList =createListOutOfStringUsingComma(ExcelUtils.readAllDataForAColumn(excelPath, "Institutions", 1, false));
					lpList=createListOutOfStringUsingComma(ExcelUtils.readAllDataForAColumn(excelPath, "Limited Partner", 2, false));
					String downLoadedFolderPath=ExcelUtils.readAllDataForAColumn(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", 2, false);
					String ItemName = ExcelUtils.readAllDataForAColumn(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", 4, false);
					downLoadedFolderPathList=createListOutOfStringUsingComma(downLoadedFolderPath);
					itemNameList=createListOutOfStringUsingComma(ItemName);
					break;
				}else {
					if(i==downloadedfileList.size()-1) {
						appLog.error(BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
						sa.assertTrue(false, BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot get file from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot get file from downloaded folder");
		}
		
		
		if(fileName!=null && !fileName.isEmpty()) {
			for (int i = 0;i<investorList.size()-5;i++) {
				String filepath="All Files/"+Org1FirmName+"/Current Investment/"+M15FundName3+"/"+ investorList.get(i)+"/"+lpList.get(i)+"/"+stdPath+"/"+uploadedFile;
				appLog.info("Going to verify path:  "+filepath);
				for(int j=0; j<downLoadedFolderPathList.size(); j++) {
					if (filepath.equalsIgnoreCase(downLoadedFolderPathList.get(j).trim())) {
						appLog.info("successfully found file of "+investorList.get(i)+" on folder structure");
						if(itemNameList.get(j).equalsIgnoreCase(uploadedFile)) {
							appLog.info("Item Name is matched for folder path  :"+downLoadedFolderPathList.get(i));
							
							
						}else {
							appLog.error("Item Name is not matched for folder path : "+downLoadedFolderPathList.get(i));
							sa.assertTrue(false, "Item Name is not matched for folder path : "+downLoadedFolderPathList.get(i));
						}
						break;
					}else {
						if(j==downLoadedFolderPathList.size()-1) {
							appLog.error("could not find file of "+investorList.get(i));
							sa.assertTrue(false, "could not find file of "+investorList.get(i));
						}
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot verify upload file name from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot verify upload file name from downloaded folder");
		}
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc051_2_renameFolders() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		String inst1 = ExcelUtils.readData(excelPath, "Institutions", excelLabel.Variable_Name, "M15Institution1", excelLabel.Institutions_Name);
		String lp1 = ExcelUtils.readData(excelPath, "Limited Partner", excelLabel.Variable_Name, "M15LimitedPartner1", excelLabel.LimitedPartner_Name);
		String stdPath = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, "M15tc051_0_uploadDocumentInMultiInvestorInINV", excelLabel.StandardPath).split(",")[0];
		String stdPath8 = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, "M15tc051_0_uploadDocumentInMultiInvestorInINV", excelLabel.StandardPath).split(",")[1];
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M15FundName3)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath,inst1 , lp1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					if (click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 30), "manage folder icon", action.SCROLLANDBOOLEAN)) {
						String stdId = fp.getCreatedFolderIdFullTraverse(stdPath, PageName.ManageFolderPopUp, 30);
						System.err.println("std id is "+stdId);
						try {
							ThreadSleep(2000);
							fp.clickOnRenameFolderButton(stdId);
							ThreadSleep(2000);
							if (sendKeys(driver, fp.getChildRenameFolderNameTextBox(Workspace.InvestorWorkspace,PageName.ManageFolderPopUp, 30), stdPath8+"UP", "rename folder textbox", action.BOOLEAN)) {
								if (click(driver, fp.getParentRenameFolderSaveButton(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30), "save button", action.BOOLEAN)) {
									
									
									if(isAlertPresent(driver)){
										String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
										switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
										if(msg.contains(FundsPageErrorMessage.YourRequestInProgressErrorMsg1)){
											appLog.info(msg+" :Message is verfiied successfully.");
										} else {
											appLog.error("Progress Message is not verified.Expexcted: "+FundsPageErrorMessage.YourRequestInProgressErrorMsg1+"\tActual: "+msg);
											sa.assertTrue(false,"Progress Message is not verified.Expexcted: "+FundsPageErrorMessage.YourRequestInProgressErrorMsg1+"\tActual: "+msg);
										}
										if(msg.contains("email") && msg.contains("progress") && msg.contains("request")){
											for(int j = 0 ; j <= 100 ; j++)
												if(EmailLib.mailReceived(gmailUserName, gmailPassword, "noreply@navatargroup.com", CRMUser1EmailID, "request update", "Processing request for "+stdPath8+"UP of "+M15FundName3+" has been completed successfully")){
													System.out.println("\n\n\nFound\n\n\n");
													break;
												} else {
													System.out.println("Mail is not found, Will Check for mail again.");
													if(j>=100){
														appLog.error("Mail is not received.");
														sa.assertTrue(false,"Mail is not received for Folder creation : "+stdPath8+"UP");
														
													}
												}
											click(driver, fp.getManageFolderCrossIcon(Workspace.InvestorWorkspace, 30), "manage folders cross icon", action.BOOLEAN);
										} else {
											appLog.info("Unexpected alert found with message: "+msg);
										
										}
									}else {
										click(driver, fp.getManageFolderCrossIcon(Workspace.InvestorWorkspace, 30), "manage folders cross icon", action.BOOLEAN);
										
									}
								}else {
									appLog.error("save button of rename folder is not clickable");
									sa.assertTrue(false, "save button of rename folder is not clickable");
								}
							}
							else {
								appLog.error("child rename folder text box is not visible");
								sa.assertTrue(false, "child rename folder text box is not visible");
							}
						
						}
						catch(Exception e) {
							appLog.info("not found "+stdId+" rename button");
							
						}
						
					}
					else {
						appLog.error("manage folder icon is not clickable");
						sa.assertTrue(false, "manage folder icon is not clickable");
					}
				}
				else {
					appLog.error(stdPath+" is not found on folder structure");
					sa.assertTrue(false, stdPath+" is not found on folder structure");
				}
				switchToDefaultContent(driver);
			}
			else {
				appLog.error(M15FundName3+" is not found on funds tab");
				sa.assertTrue(false, M15FundName3+" is not found on funds tab");
			}
		}
		else {
			appLog.error("funds tab is not clickable");
			sa.assertTrue(false, "funds tab is not clickable");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc051_3_verifyRenameFolderStructure() {
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		String stdPath = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, "M15tc051_0_uploadDocumentInMultiInvestorInINV", excelLabel.StandardPath).split(",")[0];
		String stdPath8 = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, "M15tc051_0_uploadDocumentInMultiInvestorInINV", excelLabel.StandardPath).split(",")[1];
		
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName3, Workspace.InvestorWorkspace, 30)){
			appLog.info("Successfully downloaded report");
		} else {
			appLog.error("Not able to download.");
		}
		List<String> investorList = new ArrayList<String>();
		investorList = CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
		List<String> lpList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.LimitedPartner_Name, 0);
		
		List<String> l = new ArrayList<String>();
		if (!l.isEmpty()) {
			l.clear();
		}
		//l = getValueBasedOnLabel(System.getProperty("user.dir")+"\\DownloadedFiles\\"+"folder_tree_run_on_11-30-18__2-59-03-AM.xlsx", "Sheet1", excelLabel.Path, 0);
		//l = getValueBasedOnLabel(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", excelLabel.Path, 0);
		for (int i = 0;i<investorList.size();i++) {
			System.err.println("All Files/"+Org1FirmName+"/Current Investment/"+M15FundName3+"/"+ investorList.get(i)+"/"+lpList.get(i)+"/"+stdPath+"UP"+"/");
		
			if (ExcelUtils.readData(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", excelLabel.Path, "All Files/"+Org1FirmName+"/Current Investment/"+M15FundName3+"/"+ investorList.get(i)+"/"+lpList.get(i)+"/"+stdPath+"UP"+"/", excelLabel.Path).contains("All Files/"+Org1FirmName+"/Current Investment/"+M15FundName3+"/"+ investorList.get(i)+"/"+lpList.get(i)+"/"+stdPath+"UP"+"/"))
			
			//if (l.contains("All Files/"+Org1FirmName+"/Potential Investment/"+M15FundName3+"/"+ investorList.get(i)+"/"+lpList.get(i)+"/"+stdPath+"UP"+"/"))
				appLog.info("successfully found file of "+stdPath8+"UP on folder structure");
			else {
				appLog.error("could not find file of "+stdPath8+"UP");
				sa.assertTrue(false, "could not find file of "+stdPath8+"UP");
			}
		}
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc051_4_deleteFolders() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		String inst1 = ExcelUtils.readData(excelPath, "Institutions", excelLabel.Variable_Name, "M15Institution1", excelLabel.Institutions_Name);
		String lp1 = ExcelUtils.readData(excelPath, "Limited Partner", excelLabel.Variable_Name, "M15LimitedPartner1", excelLabel.LimitedPartner_Name);
		String stdPath = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, "M15tc051_0_uploadDocumentInMultiInvestorInINV", excelLabel.StandardPath).split(",")[0];
		String msg=null;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M15FundName3)) {
				stdPath += "UP";
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (fp.verifyFolderPathdummy(stdPath,inst1 , lp1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					if (click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 30), "manage folder icon", action.SCROLLANDBOOLEAN)) {
						String stdId = fp.getCreatedFolderIdFullTraverse(stdPath, PageName.ManageFolderPopUp, 30);
						if (fp.clickOnDeleteFolderButton(stdId)) {
							if(click(driver, fp.getFolderDeleteYesBtn(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20), "delete yess button", action.BOOLEAN)) {
								if (isAlertPresent(driver))  {
									msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
									switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);

									if(msg.contains("email") && msg.contains("progress") && msg.contains("request")){
										appLog.info("alert for reading email is successfully found");
										for(int j = 0 ; j <= 100 ; j++)
											if(EmailLib.mailReceived(gmailUserName, gmailPassword, "noreply@navatargroup.com", CRMUser1EmailID, "request update","Please refresh your page and continue using Navatar Investor" )){
												System.out.println("\n\n\nFound\n\n\n");
												break;
											} else {
												System.out.println("Mail is not found, Will Check for mail again.");
												if(j>=100){
													appLog.error("Mail is not received.");
													BaseLib.sa.assertTrue(false,"Mail is not received ");
												}
											}
									} else {
										appLog.info("Unexpected alert found with message: "+msg);
									}
								}
							}
							else {
								appLog.error("delete yes button is not clickable");
								sa.assertTrue(false, "delete yes button is not clickable");
							}
						}
						else {
							appLog.error("delete folder button is not clickable");
							sa.assertTrue(false, "delete folder button is not clickable");
						}
					}else {
						appLog.error("Not able to click on manage folder icon so cannot delete folders");
						sa.assertTrue(false, "Not able to click on manage folder icon so cannot delete folders");
					}
				}else {
					appLog.error("Not able to click on folder structure so cannot delete folders");
					sa.assertTrue(false, "Not able to click on folder structure so cannot delete folders");
				}
			}else {
				appLog.error("Not able to click on created Fund "+M15FundName3+" so cannot delete created folders");
				sa.assertTrue(false, "Not able to click on created Fund "+M15FundName3+" so cannot delete created folders");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot delete created folders");
			sa.assertTrue(false, "Not able to click on fund tab so cannot delete created folders");
		}
		switchToDefaultContent(driver);	
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc051_5_verifydeletedFolders() {
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		List<String> investorList = new ArrayList<String>();
		List<String> downLoadedFolderPathList = new ArrayList<String>();
		String stdPath8 = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, "M15tc051_0_uploadDocumentInMultiInvestorInINV", excelLabel.StandardPath).split(",")[1];
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName3, Workspace.InvestorWorkspace, 30)){
			appLog.info("Successfully downloaded report from box");
		} else {
			appLog.error("Not able to download report from box");
			sa.assertTrue(false, "Not able to download report from box");
		}
		ThreadSleep(7000);
		String s =getFilesNameFromSystemFolder("DownloadedFiles");
		String fileName=null;
		if(s != null) {
			List<String> downloadedfileList=createListOutOfString(s);
			for (int i=0; i<downloadedfileList.size(); i++) {
				if(BoxPageBusinesslayer.fileName.equalsIgnoreCase(downloadedfileList.get(i))) {
					appLog.info("Downloaded File is available in download folder : "+BoxPageBusinesslayer.fileName);
					fileName=BoxPageBusinesslayer.fileName;
					investorList =getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
					String downLoadedFolderPath=ExcelUtils.readAllDataForAColumn(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", 2, false);
					downLoadedFolderPathList=createListOutOfStringUsingComma(downLoadedFolderPath);
					break;
				}else {
					if(i==downloadedfileList.size()-1) {
						appLog.error(BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
						sa.assertTrue(false, BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot get file from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot get file from downloaded folder");
		}
		if(fileName!=null && !fileName.isEmpty()) {
			for (int i = 0;i<investorList.size()-5;i++) {
				String filepath="All Files/"+Org1FirmName+"/Current Investment/"+M15FundName3+"/"+ investorList.get(i)+"/"+stdPath8+"/";
				appLog.info("Going to verify path:  "+filepath);
				for(int j=0; j<downLoadedFolderPathList.size(); j++) {
					if (filepath.equalsIgnoreCase(downLoadedFolderPathList.get(j).trim())) {
						appLog.error("found deleted folder path of in downloaded file from Box:  "+investorList.get(i));
						sa.assertTrue(false,"found deleted folder path of in downloaded file from Box:  "+investorList.get(i));
						break;
					}else {
						if(j==downLoadedFolderPathList.size()-1) {
							appLog.info("Not found deleted folder path of in downloaded file from Box:  "+investorList.get(i));
							
						}
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot verify upload file name from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot verify upload file name from downloaded folder");
		}
		sa.assertAll();
	}
 
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc052_1_VerifyAdditionOfFoldersInvestor() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		String inst1 = ExcelUtils.readData(excelPath, "Institutions", excelLabel.Variable_Name, "M15Institution1", excelLabel.Institutions_Name);
		String stdPath = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath).split(",")[0];
		String lp1 = ExcelUtils.readData(excelPath, "Limited Partner", excelLabel.Variable_Name, "M15LimitedPartner1", excelLabel.LimitedPartner_Name);
		
		String stdPath7 = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath).split(",")[1];
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(M15FundName3)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if (click(driver,fp.getManageFolderIcon(Workspace.InvestorWorkspace, 30), "fundraising workspace", action.SCROLLANDBOOLEAN)) {
					String id =bp.getCreatedFolderIdFullTraverse(stdPath, PageName.FundsPage, 30);
					if (bp.clickOnAddFolderButton(id)) {
						if (sendKeys(driver, fp.getChildFolderNameTextBox(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30), stdPath7+"sub", "sub folder text box", action.BOOLEAN)) {
							if (click(driver, fp.getAddFolderChildSaveButton(Workspace.InvestorWorkspace, 30), "save button", action.BOOLEAN)) {
								
								
								if(isAlertPresent(driver)){
									String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
									switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
									if(msg.contains(FundsPageErrorMessage.YourRequestInProgressErrorMsg1)){
										appLog.info(msg+" :Message is verfiied successfully.");
									} else {
										appLog.error("Progress Message is not verified.Expexcted: "+FundsPageErrorMessage.YourRequestInProgressErrorMsg1+"\tActual: "+msg);
										sa.assertTrue(false,"Progress Message is not verified.Expexcted: "+FundsPageErrorMessage.YourRequestInProgressErrorMsg1+"\tActual: "+msg);
									}
									if(msg.contains("email") && msg.contains("progress") && msg.contains("request")){
										for(int j = 0 ; j <= 100 ; j++)
											if(EmailLib.mailReceived(gmailUserName, gmailPassword, "noreply@navatargroup.com", CRMUser1EmailID, "request update", "Processing request for "+stdPath7+"sub of "+M15FundName3+" has been completed successfully")){
												System.out.println("\n\n\nFound\n\n\n");
												break;
											} else {
												System.out.println("Mail is not found, Will Check for mail again.");
												if(j>=100){
													appLog.error("Mail is not received.");
													sa.assertTrue(false,"Mail is not received for Folder creation : "+stdPath7+"sub");
													
												}
											}
										click(driver, fp.getManageFolderCrossIcon(Workspace.InvestorWorkspace, 30), "manage folders cross icon", action.BOOLEAN);
									} else {
										appLog.info("Unexpected alert found with message: "+msg);
									
									}
								}else {
									click(driver, fp.getManageFolderCrossIcon(Workspace.InvestorWorkspace, 30), "manage folders cross icon", action.BOOLEAN);
									
								}
								if (fp.verifyFolderPathdummy(stdPath+"/"+stdPath7+"sub", inst1, lp1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
									appLog.info("successfully found standard 7 sub folder");
								}
								else {
									appLog.error("std 7 sub folder is not created");
									sa.assertTrue(false, "std 7 sub folder is not created");
								}
//								click(driver, fp.getManageFolderCrossIcon(Workspace.InvestorWorkspace, 30), "manage folders cross icon", action.BOOLEAN);
								
							}
							else {
								appLog.error("save button is not clickable");
								sa.assertTrue(false, "save button is not clickable");
							}
						}
						else {
							appLog.error("text box for folder name is not visible");
							sa.assertTrue(false, "text box for folder name is not visible");
						}
					}else {
						appLog.error("Not able to click on add folder button so cannot create folder ");
						sa.assertTrue(false, "Not able to click on add folder button so cannot create folder ");
					}
				}else {
					appLog.error("Not able to click on manage folder icon so cannot create folder");
					sa.assertTrue(false, "Not able to click on manage folder icon so cannot create folder");
				}
			}else {
				appLog.error("Not able to click on create fund so cannot create folder");
				sa.assertTrue(false, "Not able to click on create fund so cannot create folder");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot create folder");
			sa.assertTrue(false, "Not able to click on fund tab so cannot create folder");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc052_2_verifyFolderStructure() {
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		String stdPath = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, "M15tc052_1_VerifyAdditionOfFoldersInvestor", excelLabel.StandardPath).split(",")[0];
		String stdPath7 = ExcelUtils.readData(excelPath,"filepath", excelLabel.TestCases_Name, "M15tc052_1_VerifyAdditionOfFoldersInvestor", excelLabel.StandardPath).split(",")[1];
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName3, Workspace.InvestorWorkspace, 30)){
			appLog.info("Successfully downloaded report");
		} else {
			appLog.error("Not able to download.");
		}
		List<String> investorList = CommonLib.getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
		List<String> lpList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.LimitedPartner_Name, 0);
		
		//List<String> l = getValueBasedOnLabel(System.getProperty("user.dir")+"\\DownloadedFiles\\"+"folder_tree_run_on_11-30-18__3-52-41-AM.xlsx", "Sheet1", excelLabel.Path, 0);
		//List<String> l = getValueBasedOnLabel(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", excelLabel.Path, 0);
		for (int i = 0;i<investorList.size();i++) {
			System.err.println("All Files/"+Org1FirmName+"/Current Investment/"+M15FundName3+"/"+ investorList.get(i)+"/"+lpList.get(i)+"/"+stdPath+"/"+stdPath7+"sub"+"/");
			if (ExcelUtils.readData(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", excelLabel.Path, "All Files/"+Org1FirmName+"/Current Investment/"+M15FundName3+"/"+ investorList.get(i)+"/"+lpList.get(i)+"/"+stdPath+"/"+stdPath7+"sub"+"/", excelLabel.Path).contains("All Files/"+Org1FirmName+"/Current Investment/"+M15FundName3+"/"+ investorList.get(i)+"/"+lpList.get(i)+"/"+stdPath+"/"+stdPath7+"sub"+"/"))
			//if (l.contains("All Files/"+Org1FirmName+"/Potential Investment/"+M15FundName3+"/"+ investorList.get(i)+"/"+lpList.get(i)+"/"+stdPath+"/"+stdPath7+"sub"+"/"))
				appLog.info("successfully found "+stdPath7+" on folder structure");
			else {
				appLog.error("could not find "+stdPath7);
				sa.assertTrue(false, "could not find "+stdPath7);
			}
		}
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc055_sendInvitationMailTo200Contacts(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view.");
				List<WebElement> allTheContactsInGrid = null;
				if(click(driver, fp.getmanageEmails(Workspace.InvestorWorkspace, 30), "Manage email", action.BOOLEAN)){
					allTheContactsInGrid = FindElements(driver, "//span[@class='aw-item-template aw-templates-cell aw-grid-cell aw-column-2 aw-cells-normal ']//a[contains(@href,'mailto:')]", "Contacts In Grid");
					if(allTheContactsInGrid.size()==1000){
						appLog.info("All the contacts are present in the grid.");
					} else {
						appLog.error("All the contacts are not present in the grid. Expected: 1000\tActual: "+allTheContactsInGrid.size());
						sa.assertTrue(false,"All the contacts are not present in the grid. Expected: 1000\tActual: "+allTheContactsInGrid.size());
					}
					WebElement ele = FindElement(driver, "//input[@class='checkall']", "Manage email select all check box", action.BOOLEAN, 30);
					if(click(driver, ele, "Check all Check Box", action.BOOLEAN)){
						if(click(driver, fp.getmanageEmailsendBtn(30), "manage email send button", action.BOOLEAN)){
							if(isAlertPresent(driver)){
								String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
								switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
								if(msg.equalsIgnoreCase(FundsPageErrorMessage.ManageEmailsMailSendLimitErrorMsg)){
									appLog.info("Error message is verified successfully.");
								} else {
									appLog.error("Error message is not verified. Expected: "+FundsPageErrorMessage.ManageEmailsMailSendLimitErrorMsg+"\tActual: "+msg);
									sa.assertTrue(false,"Error message is not verified. Expected: "+FundsPageErrorMessage.ManageEmailsMailSendLimitErrorMsg+"\tActual: "+msg);
								}
							} else {
								appLog.error("Error message is not poping up, while sending mail to 200+ contacts at a time.");
								sa.assertTrue(false,"Error message is not poping up, while sending mail to 200+ contacts at a time.");
							}
						} else {
							appLog.error("Manage email send button cannot be clicked, So cannot check error message.");
							sa.assertTrue(false,"Manage email send button cannot be clicked, So cannot check error message.");
						}
						if(sendKeys(driver, fp.getManageEmailSearchTextBox(30), M15Institution1, "Search box", action.BOOLEAN)){
							if(click(driver, fp.getManageEmailSearchBtn(30), "Seacrh button", action.BOOLEAN)){
								ele = FindElement(driver, "//input[@class='checkall']", "Manage email select all check box", action.BOOLEAN, 30);
								if(click(driver, ele, "Check all check box", action.SCROLLANDBOOLEAN)){
									if(click(driver, fp.getmanageEmailsendBtn(30), "manage email send button", action.BOOLEAN)){
										if(click(driver, fp.getManageEmailSendInvitationConfirmationYesBtn(30), "Confirmation pop up yes button", action.BOOLEAN)){
											appLog.info("Successfully sent mails to the contacts.");
											for(int i = 1; i < 201; i++){
												String contactEmail = ExcelUtils.readData(excelPath, "Contacts", excelLabel.Variable_Name, "BulkContact"+i, excelLabel.Contact_EmailId);
												if(EmailLib.mailReceived(gmailUserName, gmailPassword, CRMUser1EmailID, contactEmail, "Invitation from "+Org1FirmName, "You have been granted access to Current investments of "+M15FundName1+" by "+Org1FirmName+".")){
													appLog.info("Mail successfully received to "+contactEmail);
												} else {
													appLog.error("Mail is not received for to contact '"+contactEmail+"'.");
													sa.assertTrue(false,"Mail is not received for to contact '"+contactEmail+"'.");
												}
											}
										} else {
											appLog.error("Not able to send mail to the contacts.");
											sa.assertTrue(false,"Not able to send mail to the contacts.");
										}
									} else {
										appLog.error("Send button cannot be clicked, So cannot send the invitation mail.");
										sa.assertTrue(false,"Send button cannot be clicked, So cannot send the invitation mail.");
									}
								} else {
									appLog.error("check all check box cannot be clicked, So cannnot contiue with the testcase.");
									sa.assertTrue(false,"check all check box cannot be clicked, So cannnot contiue with the testcase.");
								}
							} else {
								appLog.error("Not able to search the contacts of Institute '"+M15Institution1+"', So cannot continue with the testcase.");
								sa.assertTrue(false,"Not able to search the contacts of Institute '"+M15Institution1+"', So cannot continue with the testcase.");
							}
						} else {
							appLog.error("Not able to search the contacts of Institute '"+M15Institution1+"', So cannot continue with the testcase.");
							sa.assertTrue(false,"Not able to search the contacts of Institute '"+M15Institution1+"', So cannot continue with the testcase.");
						}
					} else {
						appLog.error("Check all check box cannot be clicked, So cannot check error message.");
						sa.assertTrue(false,"Check all check box cannot be clicked, So cannot check error message.");
					}
				} else {
					appLog.error("Manage email icon cannot be clicked, So cannot continue with the tc.");
					sa.assertTrue(false,"Manage email icon cannot be clicked, So cannot continue with the tc.");
				}
			} else {
				appLog.error("Fund is not found, So cannot continue with the testcase.");
				sa.assertTrue(false,"Fund is not found, So cannot continue with the testcase.");
			}
		} else {
			appLog.error("Cannot click on funds tab, So cannot continue with the testcase.");
			sa.assertTrue(false,"Cannot click on funds tab, So cannot continue with the testcase.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc054_Invite1KContact(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view.");
				if(fp.clickOnLimitedPartnerFolder(M15Institution1, M15LP1, Workspace.InvestorWorkspace, 30)){
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "Contact access icon", action.BOOLEAN)){
						if(fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)){
							List<WebElement> ele = FindElements(driver, "//a[contains(@href,'@gmail.com')]/../preceding-sibling::span//input", "Contacts");
							if(ele.size()==201){
								appLog.info("Contacts are verified.");
								if(fp.selectDeselectContactFromContactAccess(null, SelectDeselect.Select, AllOr1By1.All, null, Workspace.InvestorWorkspace, 30).isEmpty()){
									if(click(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30), "Add selected contact button", action.BOOLEAN)){
										if(isAlertPresent(driver)){
											String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
											switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
											if(msg.equalsIgnoreCase(FundsPageErrorMessage.CannotAddMoreThan200ContactErrorMesssage.toString())){
												appLog.info("Message is successfully verified.");
											} else {
												appLog.error(FundsPageErrorMessage.CannotAddMoreThan200ContactErrorMesssage.toString()+" error message is not verified. Expected: "+FundsPageErrorMessage.CannotAddMoreThan200ContactErrorMesssage+"\tActual: "+msg);
												sa.assertTrue(false,"Error message is not verified. Expected: "+FundsPageErrorMessage.CannotAddMoreThan200ContactErrorMesssage+"\tActual: "+msg);
											}
											List<String> contact = new ArrayList<String>();
											contact.add(M15Contact201EmailId);
											if(fp.selectDeselectContactFromContactAccess(contact, SelectDeselect.Deselect, AllOr1By1.OneByOne, null, Workspace.InvestorWorkspace, 30).isEmpty()){
												appLog.info("Successfully deselected contact '"+M15Contact201EmailId+"'.");
											} else {
												appLog.error("Not able to deselect contact '"+M15Contact201EmailId+"'.");
												sa.assertTrue(false,"Not able to deselect contact '"+M15Contact201EmailId+"'.");
											}
										} else {
											appLog.error(FundsPageErrorMessage.CannotAddMoreThan200ContactErrorMesssage+" error message is not poping up.");
											sa.assertTrue(false,FundsPageErrorMessage.CannotAddMoreThan200ContactErrorMesssage+" error message is not poping up.");
										}
									} else {
										appLog.error("Not able to click on add selected contact button, so cannot verify 'cannot add more than 200 contact at a time' error message.");
										sa.assertTrue(false,"Not able to click on add selected contact button, so cannot verify 'cannot add more than 200 contact at a time' error message.");
									}
								} else {
									appLog.error("Not able to select all contact from the list.");
									sa.assertTrue(false,"Not able to select all contact from the list.");
								}
							} else {
								appLog.error("Count of contacts of '"+M15Institution1+"' is not verified. Expected: 201\tActual: "+ele.size());
								appLog.error("Total contacts are not 201, So will not be able to verify kindly select 200 contacts error message.");
								sa.assertTrue(false,"Count of contacts of '"+M15Institution1+"' is not verified. Expected: 201\tActual: "+ele.size());
							}
							if(click(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30), "Add selected contact button", action.BOOLEAN)){
								if(fp.clickOnContactAccessApplyButton(M15Institution1+"/"+M15LP1, CRMUser1EmailID, Workspace.InvestorWorkspace, 30)){
									if(fp.selectDeselectContactFromContactAccess(null, SelectDeselect.Select, AllOr1By1.All, M15Institution2, Workspace.InvestorWorkspace, 30).isEmpty()){
										appLog.info("Successfully selected contacts of institution '"+M15Institution2+"'.");
										if(click(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30), "Add selected contact button", action.BOOLEAN)){
											if(fp.clickOnContactAccessApplyButton(M15Institution1+"/"+M15LP1, CRMUser1EmailID, Workspace.InvestorWorkspace, 30)){
												if(fp.selectDeselectContactFromContactAccess(null, SelectDeselect.Select, AllOr1By1.All, M15Institution3, Workspace.InvestorWorkspace, 30).isEmpty()){
													appLog.info("Successfully selected contacts of institution '"+M15Institution3+"'.");
													if(click(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30), "Add selected contact button", action.BOOLEAN)){
														if(fp.clickOnContactAccessApplyButton(M15Institution1+"/"+M15LP1, CRMUser1EmailID, Workspace.InvestorWorkspace, 30)){
															if(fp.selectDeselectContactFromContactAccess(null, SelectDeselect.Select, AllOr1By1.All, M15Institution4, Workspace.InvestorWorkspace, 30).isEmpty()){
																appLog.info("Successfully selected contacts of institution '"+M15Institution4+"'.");
																if(click(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30), "Add selected contact button", action.BOOLEAN)){
																	if(fp.clickOnContactAccessApplyButton(M15Institution1+"/"+M15LP1, CRMUser1EmailID, Workspace.InvestorWorkspace, 30)){
																		if(fp.selectDeselectContactFromContactAccess(null, SelectDeselect.Select, AllOr1By1.All, M15Institution5, Workspace.InvestorWorkspace, 30).isEmpty()){
																			appLog.info("Successfully selected contacts of institution '"+M15Institution5+"'.");
																			if(click(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30), "Add selected contact button", action.BOOLEAN)){
																				if(fp.clickOnContactAccessApplyButton(M15Institution1+"/"+M15LP1, CRMUser1EmailID, Workspace.InvestorWorkspace, 30)){
																					appLog.info("Successfully provided access to 1K Contacts");
																					ele = FindElements(driver, "//span[contains(@id,'grid11_DealDetailBWINV-cell-3')]/a", "Selected contact email ids");
																					if(ele.size()==1000){
																						appLog.info("All the contacts have successfully been provided access.");
																					} else {
																						appLog.error("All the contacts who have access are not present in the selected grid. Expected: 1000\tActual: "+ele.size());
																						sa.assertTrue(false,"All the contacts who have access are not present in the selected grid. Expected: 1000\tActual: "+ele.size());
																					}
																					
																				} else {
																					appLog.error("Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution4+"'.");
																					sa.assertTrue(false,"Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution4+"'.");
																				}
																			} else {
																				appLog.error("Not able to click on add selected contact button, so cannot invite contacts.");
																				sa.assertTrue(false,"Not able to click on add selected contact button, so cannot invite contacts.");
																			}
																		} else {
																			appLog.error("Not able to select contacts of institution '"+M15Institution5+"'.");
																			sa.assertTrue(false,"Not able to select contacts of institution '"+M15Institution5+"'.");
																		}
																	} else {
																		appLog.error("Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution4+"'.");
																		sa.assertTrue(false,"Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution4+"'.");
																	}
																} else {
																	appLog.error("Not able to click on add selected contact button, so cannot invite contacts.");
																	sa.assertTrue(false,"Not able to click on add selected contact button, so cannot invite contacts.");
																}
															} else {
																appLog.error("Not able to select contacts of institution '"+M15Institution4+"'.");
																sa.assertTrue(false,"Not able to select contacts of institution '"+M15Institution4+"'.");
															}
														} else {
															appLog.error("Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution3+"'.");
															sa.assertTrue(false,"Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution3+"'.");
														}
													} else {
														appLog.error("Not able to click on add selected contact button, so cannot invite contacts.");
														sa.assertTrue(false,"Not able to click on add selected contact button, so cannot invite contacts.");
													}
												} else {
													appLog.error("Not able to select contacts of institution '"+M15Institution3+"'.");
													sa.assertTrue(false,"Not able to select contacts of institution '"+M15Institution3+"'.");
												}
												
											} else {
												appLog.error("Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution2+"'.");
												sa.assertTrue(false,"Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution2+"'.");
											}
										} else {
											appLog.error("Not able to click on add selected contact button, so cannot invite contacts.");
											sa.assertTrue(false,"Not able to click on add selected contact button, so cannot invite contacts.");
										}
									} else {
										appLog.error("Not able to select contacts of institution '"+M15Institution2+"'.");
										sa.assertTrue(false,"Not able to select contacts of institution '"+M15Institution2+"'.");
									}
								} else {
									appLog.error("Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution1+"'.");
									sa.assertTrue(false,"Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution1+"'.");
								}
							} else {
								appLog.error("Not able to click on add selected contact button, so cannot invite contacts.");
								sa.assertTrue(false,"Not able to click on add selected contact button, so cannot invite contacts.");
							}
							
							
						} else {
							appLog.error("Cannot expand select contact area, So cannot provide contact access to 1k contact.");
							sa.assertTrue(false,"Cannot expand select contact area, So cannot provide contact access to 1k contact.");
						}
					} else {
						appLog.error("Contact access icon cannot be clicked, So cannot invite 1k contacts.");
						sa.assertTrue(false,"Contact access icon cannot be clicked, So cannot invite 1k contacts.");
					}
				} else {
					appLog.error(M15Institution1+" institution folder cannot be clicked, So cannot provide access to 1k contacts");
					sa.assertTrue(false,M15Institution1+" institution folder cannot be clicked, So cannot provide access to 1k contacts");
				}
			} else {
				appLog.error(M15FundName1 + " fund is not present in the list, So cannot invite 1K contacts.");
				sa.assertTrue(false,M15FundName1 + " fund is not present in the list, So cannot invite 1K contacts.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot invite 1K contacts.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot invite 1K contacts.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc056_RegisterConact1(){
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		FundsPageBusinessLayer fp=new FundsPageBusinessLayer(driver);
		boolean flag = false;
		if(lp.investorLogin(M15C1EmailId, adminPassword)){
			flag = true;
		} else {
			sa.assertTrue(false, "Investor password is wrong so cannot login");
			flag = false;
		}
		if(flag){
			if(ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)){
				if(fp.verifyFolderPathdummy("", M15Institution1, M15LP1, null, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 30)){
					appLog.info("Folder structure is verified."); 
				} else {
					appLog.error("Folder strucuture is not verified.");
					sa.assertTrue(false,"Folder strucuture is not verified.");
				}
//				if(ifp.clickOnBulkDownload()){
//					
//				} else {
//					appLog.error("Not able to click on bulk download icon, So cannot verify folder structure on bulk download window.");
//					sa.assertTrue(false,"Not able to click on bulk download icon, So cannot verify folder structure on bulk download window.");
//				}
			} else {
				appLog.error("Current invesstment tab cannot be clicked, So cannot continue with the testcase.");
				sa.assertTrue(false,"Current invesstment tab cannot be clicked, So cannot continue with the testcase.");
			}
		}
		lp.getInvestorLogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M15tc057_UploadBigSizeFilesInCommonInvestorWS(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		CommonLib compare = new CommonLib();
		String commonFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "workspace view");
				if(fp.verifyFolderPathdummy(commonFolderPath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)){
					if(click(driver, fp.getUploadIcon(Workspace.InvestorWorkspace, 30), "Upload Icon", action.BOOLEAN)){
						String parentWin = switchOnWindow(driver);
						if(parentWin!=null){
							if(fp.dragDropFiles("UploadFiles\\BulkFiles\\Common_100PlusFilesInvestorWorkspace", "DropLoc.JPG")){
								ThreadSleep(2000);
								System.err.println("Successfully dropped the files.");
//								if(isAlertPresent(driver)){
//									String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
//									switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
//									if(msg.equalsIgnoreCase(FundsPageErrorMessage.FilesUploadedAtATimeLimitErrorMsg)){
//										appLog.info("Error message is verified.");
//									} else {
//										appLog.error("Error message os not verified. Expected: "+FundsPageErrorMessage.FilesUploadedAtATimeLimitErrorMsg+"\tActual: "+msg);
//										sa.assertTrue(false,"Error message os not verified. Expected: "+FundsPageErrorMessage.FilesUploadedAtATimeLimitErrorMsg+"\tActual: "+msg);
//									}
//								} else {
//									appLog.error("It is allowing to upload more than 100 files at a time.");
//									sa.assertTrue(false,"It is allowing to upload more than 100 files at a time.");
//								}
							} else {
								appLog.error("Drag and drop is not successful, So cannot verify more than 100 files error message.");
								sa.assertTrue(false,"Drag and drop is not successful, So cannot verify more than 100 files error message.");
							}
							driver.close();
							driver.switchTo().window(parentWin);
						} else {
							appLog.error("Upload window is not opening after clicking on upload button, So cannot verify more than 100 files are not allowed error message.");
							sa.assertTrue(false,"Upload window is not opening after clicking on upload button, So cannot verify more than 100 files are not allowed error message.");
						}
					} else {
						appLog.error("Upload button cannot be clicked, So cannot verify 100 file drop error message.");
						sa.assertTrue(false,"Upload button cannot be clicked, So cannot verify 101 file drop error message.");
					}
				} else {
					appLog.error(commonFolderPath+" Folder is not present, so cannot verify 100 file drop error message.");
					sa.assertTrue(false,commonFolderPath+" Folder is not present, so cannot verify 100 file drop error message.");
				}
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if(fp.verifyFolderPathdummy(commonFolderPath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)){
					if(click(driver, fp.getUploadIcon(Workspace.InvestorWorkspace, 30), "Upload Icon", action.BOOLEAN)){
						String parentWin = switchOnWindow(driver);
						if(parentWin!=null){
							if(fp.dragDropFiles("UploadFiles\\BulkFiles\\Common_BigSizeFilesInvestorWorkspace", "DropLoc.JPG")){
								ThreadSleep(15000);
								List<String> droppedFileNames = new ArrayList<String>();
//								List<WebElement> droppedFiles = driver.findElements(By.xpath("//span[@class='File']/following-sibling::b"));
								List<WebElement> droppedFiles = FindElements(driver, "//span[@class='File']/following-sibling::b", "Files in upload win");
								if(!droppedFiles.isEmpty()){
									for(int i = 0; i < droppedFiles.size(); i++){
										droppedFileNames.add(getText(driver, droppedFiles.get(i), "Dropped Files", action.BOOLEAN).trim());
									}
									Collections.sort(droppedFileNames,compare);
									String previousuploadedFiles = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
									String newlyUploadedFiles = createStringOutOfList(droppedFileNames);
									if(previousuploadedFiles!=null && !previousuploadedFiles.isEmpty()){
										newlyUploadedFiles = previousuploadedFiles+"<break>"+newlyUploadedFiles;
									}
									if (ExcelUtils.writeData(excelPath, newlyUploadedFiles, "filePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon)) {
										appLog.info("written uploaded file data to excel");
									}
									else {
										appLog.error("could not write uploaded files information to excel");
									}
								} else {
									appLog.error("0 files are dropped.");
									BaseLib.sa.assertTrue(false,"0 files are dropped.");
								}
								if(click(driver, fp.getUploadSaveButton(30), "Upload Save Button", action.BOOLEAN)){
									if (isAlertPresent(driver)) {
										String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
										switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
										if (msg.trim().equals(FundsPageErrorMessage.DocumentUploadSuccessMsg)) {
											appLog.info("successfully found uploaded document message");
										}
									}else {
										appLog.info("document is not uploaded.. we will check from email");
										String msg = fp.getUploadBulkDelayedMessage(180).getText();
										if(msg.contains("email") && msg.contains("Document") && msg.contains("upload")){
											for(int j = 0 ; j <= 100 ; j++)
												if(EmailLib.mailReceived(gmailUserName, gmailPassword, "noreply@navatarinvestor.com", CRMUser1EmailID, "File upload status","file upload process has been completed" )){
													System.out.println("\n\n\nFound\n\n\n");
													break;
												} else {
													System.out.println("Mail is not found, Will Check for mail again.");
													if(j>=100){
														appLog.error("Mail is not received.");
														BaseLib.sa.assertTrue(false,"Mail is not received ");
													}
												}
										}else {
											appLog.error("msg warming on web page is not related to reading email regarding upload");
											sa.assertTrue(false, "msg warming on web page is not related to reading email regarding upload");
										}
										if(!driver.getWindowHandle().equalsIgnoreCase(parentWin)){
											driver.close();
										}
									}
								}else {
									appLog.error("upload save button is not clickable");
									sa.assertTrue(false, "upload save button is not clickable");
								}
							} else {
								appLog.error("Drag and drop is not successful, So cannot verify more than 100 files error message.");
								sa.assertTrue(false,"Drag and drop is not successful, So cannot verify more than 100 files error message.");
							}
							driver.switchTo().window(parentWin);
							switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
							if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30), "Refresh BUtton", action.BOOLEAN)){
								List<WebElement> filesInGridEle = fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage);
								List<String> filesInGrid = new ArrayList<String>();
								for (WebElement webElement : filesInGridEle) {
									filesInGrid.add(getText(driver, webElement, "", action.BOOLEAN));
								}
								boolean flag = true;
								String filesInExcel = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
								for(int i = 0; i < filesInExcel.split("<break>").length; i++){
									String fileName = filesInExcel.split("<break>")[i];
									for(int j = 0; j < filesInGrid.size(); j++){
										if(fileName.equalsIgnoreCase(filesInGrid.get(j))){
											appLog.info(fileName+" file is successfully uploaded.");
											flag = true;
											break;
										} else {
											flag = false;
										}
									}
									if(!flag){
										appLog.error(fileName+" file is  not uploaded.");
										sa.assertTrue(false,fileName+" file is  not uploaded.");
									}
								}
							} else {
								appLog.error("Refresh buttona cannot be clicked, So cannot verify file in content grid.");
								sa.assertTrue(false,"Refresh buttona cannot be clicked, So cannot verify file in content grid.");
							}
						} else {
							appLog.error("Upload window is not opening after clicking on upload button, So cannot verify more than 100 files are not allowed error message.");
							sa.assertTrue(false,"Upload window is not opening after clicking on upload button, So cannot verify more than 100 files are not allowed error message.");
						}
					} else {
						appLog.error("Upload button cannot be clicked, So cannot verify 100 file drop error message.");
						sa.assertTrue(false,"Upload button cannot be clicked, So cannot verify 101 file drop error message.");
					}
				} else {
					appLog.error(commonFolderPath+" Folder is not present, so cannot verify 100 file drop error message.");
					sa.assertTrue(false,commonFolderPath+" Folder is not present, so cannot verify 100 file drop error message.");
				}
				
			} else {
				appLog.error(M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot upload big size files in common folder.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot upload big size files in common folder.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc058_UpdateBigSizeFilesInCommonInvestorWS(String environment, String mode){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String commonFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
//				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
//				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "workspace view");
				if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, commonFolderPath, null, "UploadFiles\\BulkFiles\\Common_BigSizeFilesUpdateInvestorWorkspace", UploadFileActions.Update, UploadFileActions.Update, Workspace.InvestorWorkspace, PageName.FundsPage, 30)){
					appLog.info("Files updated successfully.");
				} else {
					appLog.error("Files are not updated.");
					sa.assertTrue(false,"Files are not updated.");
				}
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30), "Refresh BUtton", action.BOOLEAN)){
					List<WebElement> filesInGridEle = fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage);
					List<String> filesInGrid = new ArrayList<String>();
					for (WebElement webElement : filesInGridEle) {
						filesInGrid.add(getText(driver, webElement, "", action.BOOLEAN));
					}
					boolean flag = true;
					String filesInExcel = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
					for(int i = 0; i < filesInExcel.split("<break>").length; i++){
						String fileName = filesInExcel.split("<break>")[i];
						for(int j = 0; j < filesInGrid.size(); j++){
							if(fileName.equalsIgnoreCase(filesInGrid.get(j))){
								appLog.info(fileName+" file is successfully uploaded.");
								flag = true;
								break;
							} else {
								flag = false;
							}
						}
						if(!flag){
							appLog.error(fileName+" file is  not uploaded.");
							sa.assertTrue(false,fileName+" file is  not uploaded.");
						}
					}
				} else {
					appLog.error("Refresh buttona cannot be clicked, So cannot verify file in content grid.");
					sa.assertTrue(false,"Refresh buttona cannot be clicked, So cannot verify file in content grid.");
				}
			} else {
				appLog.error(M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot upload big size files in common folder.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot upload big size files in common folder.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc059_UploadBigSizeFilesInInternalInvestorWS(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		CommonLib compare = new CommonLib();
		String InternalFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "workspace view");
				if(fp.verifyFolderPathdummy(InternalFolderPath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)){
					if(click(driver, fp.getUploadIcon(Workspace.InvestorWorkspace, 30), "Upload Icon", action.BOOLEAN)){
						String parentWin = switchOnWindow(driver);
						if(parentWin!=null){
							if(fp.dragDropFiles("UploadFiles\\BulkFiles\\Internal_BigSizeFilesInvestorWorkspace", "DropLoc.JPG")){
								ThreadSleep(15000);
								List<String> droppedFileNames = new ArrayList<String>();
//								List<WebElement> droppedFiles = driver.findElements(By.xpath("//span[@class='File']/following-sibling::b"));
								List<WebElement> droppedFiles = FindElements(driver, "//span[@class='File']/following-sibling::b", "Files in upload win");
								if(!droppedFiles.isEmpty()){
									for(int i = 0; i < droppedFiles.size(); i++){
										droppedFileNames.add(getText(driver, droppedFiles.get(i), "Dropped Files", action.BOOLEAN).trim());
									}
									Collections.sort(droppedFileNames,compare);
									String previousuploadedFiles = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileInternal);
									String newlyUploadedFiles = createStringOutOfList(droppedFileNames);
									if(previousuploadedFiles!=null && !previousuploadedFiles.isEmpty()){
										newlyUploadedFiles = previousuploadedFiles+"<break>"+newlyUploadedFiles;
									}
									if (ExcelUtils.writeData(excelPath, newlyUploadedFiles, "filePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileInternal)) {
										appLog.info("written uploaded file data to excel");
									}
									else {
										appLog.error("could not write uploaded files information to excel");
									}
								} else {
									appLog.error("0 files are dropped.");
									BaseLib.sa.assertTrue(false,"0 files are dropped.");
								}
								if(click(driver, fp.getUploadSaveButton(30), "Upload Save Button", action.BOOLEAN)){
									if (isAlertPresent(driver)) {
										String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
										switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
										if (msg.trim().equals(FundsPageErrorMessage.DocumentUploadSuccessMsg)) {
											appLog.info("successfully found uploaded document message");
										}
									}else {
										appLog.info("document is not uploaded.. we will check from email");
										String msg = fp.getUploadBulkDelayedMessage(180).getText();
										if(msg.contains("email") && msg.contains("Document") && msg.contains("upload")){
											for(int j = 0 ; j <= 100 ; j++)
												if(EmailLib.mailReceived(gmailUserName, gmailPassword, "noreply@navatarinvestor.com", CRMUser1EmailID, "File upload status","file upload process has been completed" )){
													System.out.println("\n\n\nFound\n\n\n");
													break;
												} else {
													System.out.println("Mail is not found, Will Check for mail again.");
													if(j>=100){
														appLog.error("Mail is not received.");
														BaseLib.sa.assertTrue(false,"Mail is not received ");
													}
												}
										}else {
											appLog.error("msg warming on web page is not related to reading email regarding upload");
											sa.assertTrue(false, "msg warming on web page is not related to reading email regarding upload");
										}
										if(!driver.getWindowHandle().equalsIgnoreCase(parentWin)){
											driver.close();
										}
									}
								}else {
									appLog.error("upload save button is not clickable");
									sa.assertTrue(false, "upload save button is not clickable");
								}
							} else {
								appLog.error("Drag and drop is not successful, So cannot verify more than 100 files error message.");
								sa.assertTrue(false,"Drag and drop is not successful, So cannot verify more than 100 files error message.");
							}
							driver.switchTo().window(parentWin);
							switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
							if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30), "Refresh BUtton", action.BOOLEAN)){
								List<WebElement> filesInGridEle = fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage);
								List<String> filesInGrid = new ArrayList<String>();
								for (WebElement webElement : filesInGridEle) {
									filesInGrid.add(getText(driver, webElement, "", action.BOOLEAN));
								}
								boolean flag = true;
								String filesInExcel = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileInternal);
								for(int i = 0; i < filesInExcel.split("<break>").length; i++){
									String fileName = filesInExcel.split("<break>")[i];
									for(int j = 0; j < filesInGrid.size(); j++){
										if(fileName.equalsIgnoreCase(filesInGrid.get(j))){
											appLog.info(fileName+" file is successfully uploaded.");
											flag = true;
											break;
										} else {
											flag = false;
										}
									}
									if(!flag){
										appLog.error(fileName+" file is  not uploaded.");
										sa.assertTrue(false,fileName+" file is  not uploaded.");
									}
								}
							} else {
								appLog.error("Refresh buttona cannot be clicked, So cannot verify file in content grid.");
								sa.assertTrue(false,"Refresh buttona cannot be clicked, So cannot verify file in content grid.");
							}
						} else {
							appLog.error("Upload window is not opening after clicking on upload button, So cannot verify more than 100 files are not allowed error message.");
							sa.assertTrue(false,"Upload window is not opening after clicking on upload button, So cannot verify more than 100 files are not allowed error message.");
						}
					} else {
						appLog.error("Upload button cannot be clicked, So cannot verify 100 file drop error message.");
						sa.assertTrue(false,"Upload button cannot be clicked, So cannot verify 101 file drop error message.");
					}
				} else {
					appLog.error(InternalFolderPath+" Folder is not present, so cannot verify 100 file drop error message.");
					sa.assertTrue(false,InternalFolderPath+" Folder is not present, so cannot verify 100 file drop error message.");
				}
				
			} else {
				appLog.error(M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc060_UpdateBigSizeFilesInInternalInvestorWS(String environment, String mode){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String InternalFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
//				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
//				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "workspace view");
				if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, InternalFolderPath, null, "UploadFiles\\BulkFiles\\Internal_BigSizeFilesUpdateInvestorWorkspace", UploadFileActions.Update, UploadFileActions.Update, Workspace.InvestorWorkspace, PageName.FundsPage, 30)){
					appLog.info("Files updated successfully.");
				} else {
					appLog.error("Files are not updated.");
					sa.assertTrue(false,"Files are not updated.");
				}
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30), "Refresh BUtton", action.BOOLEAN)){
					List<WebElement> filesInGridEle = fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage);
					List<String> filesInGrid = new ArrayList<String>();
					for (WebElement webElement : filesInGridEle) {
						filesInGrid.add(getText(driver, webElement, "", action.BOOLEAN));
					}
					boolean flag = true;
					String filesInExcel = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileInternal);
					for(int i = 0; i < filesInExcel.split("<break>").length; i++){
						String fileName = filesInExcel.split("<break>")[i];
						for(int j = 0; j < filesInGrid.size(); j++){
							if(fileName.equalsIgnoreCase(filesInGrid.get(j))){
								appLog.info(fileName+" file is successfully uploaded.");
								flag = true;
								break;
							} else {
								flag = false;
							}
						}
						if(!flag){
							appLog.error(fileName+" file is  not uploaded.");
							sa.assertTrue(false,fileName+" file is  not uploaded.");
						}
					}
				} else {
					appLog.error("Refresh buttona cannot be clicked, So cannot verify file in content grid.");
					sa.assertTrue(false,"Refresh buttona cannot be clicked, So cannot verify file in content grid.");
				}
			} else {
				appLog.error(M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc061_OnlineImportFilesInCommonFolderINV(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String CommonFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
//				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
//				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor Workspace View.");
				List<String> files = listFilesForFolder(new java.io.File(System.getProperty("user.dir")+"/UploadFiles/BulkFiles/Common_OnlineImportFiles"));
				String fileName = createStringOutOfList(files);
				if(fp.onlineImportBulk(CRMUser1EmailID, null, null, null, CommonFolderPath, ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.OnlineImportPath), fileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Common, PageName.FundsPage, Workspace.InvestorWorkspace, 30)){
					appLog.info("Online import successful.");
					ExcelUtils.writeData(excelPath, fileName, "filePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30), "Refresh BUtton", action.BOOLEAN)){
						List<WebElement> filesInGridEle = fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage);
						List<String> filesInGrid = new ArrayList<String>();
						for (WebElement webElement : filesInGridEle) {
							filesInGrid.add(getText(driver, webElement, "", action.BOOLEAN));
						}
						boolean flag = true;
						String filesInExcel = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
						for(int i = 0; i < filesInExcel.split("<break>").length; i++){
							String file = filesInExcel.split("<break>")[i];
							for(int j = 0; j < filesInGrid.size(); j++){
								if(file.equalsIgnoreCase(filesInGrid.get(j))){
									appLog.info(file+" file is successfully Imported.");
									flag = true;
									break;
								} else {
									flag = false;
								}
							}
							if(!flag){
								appLog.error(fileName+" file is  not Imported.");
								sa.assertTrue(false,fileName+" file is  not Imported.");
							}
						}
					} else {
						appLog.error("Refresh buttona cannot be clicked, So cannot verify file in content grid.");
						sa.assertTrue(false,"Refresh buttona cannot be clicked, So cannot verify file in content grid.");
					}
				} else {
					appLog.error("Online import is not successful, So we cannot verify the functionality.");
					sa.assertTrue(false,"Online import is not successful, So we cannot verify the functionality.");
				}
			} else {
				appLog.error(M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc062_OnlineImportFilesInCommonFolderUpdateINV(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String CommonFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
//				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
//				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor Workspace View.");
				List<String> files = listFilesForFolder(new java.io.File(System.getProperty("user.dir")+"/UploadFiles/BulkFiles/Common_OnlineImportFilesUpdate"));
				String fileName = createStringOutOfList(files);
				if(fp.onlineImportBulk(CRMUser1EmailID, null, null, null, CommonFolderPath, ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.OnlineImportPath), fileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPDATE, FolderType.Common, PageName.FundsPage, Workspace.InvestorWorkspace, 30)){
					appLog.info("Online import successful.");
					ExcelUtils.writeData(excelPath, fileName, "filePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileCommon);
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30), "Refresh BUtton", action.BOOLEAN)){
						List<WebElement> filesInGridEle = fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage);
						List<String> filesInGrid = new ArrayList<String>();
						for (WebElement webElement : filesInGridEle) {
							filesInGrid.add(getText(driver, webElement, "", action.BOOLEAN));
						}
						boolean flag = true;
						String filesInExcel = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileCommon);
						for(int i = 0; i < filesInExcel.split("<break>").length; i++){
							String file = filesInExcel.split("<break>")[i];
							for(int j = 0; j < filesInGrid.size(); j++){
								if(file.equalsIgnoreCase(filesInGrid.get(j))){
									appLog.info(file+" file is successfully Imported.");
									flag = true;
									break;
								} else {
									flag = false;
								}
							}
							if(!flag){
								appLog.error(fileName+" file is  not Imported.");
								sa.assertTrue(false,fileName+" file is  not Imported.");
							}
						}
					} else {
						appLog.error("Refresh buttona cannot be clicked, So cannot verify file in content grid.");
						sa.assertTrue(false,"Refresh buttona cannot be clicked, So cannot verify file in content grid.");
					}
				} else {
					appLog.error("Online import is not successful, So we cannot verify the functionality.");
					sa.assertTrue(false,"Online import is not successful, So we cannot verify the functionality.");
				}
			} else {
				appLog.error(M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M15tc063_OnlineImportFilesInInternalFolderINV(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String InternalFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
//				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
//				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor Workspace View.");
				List<String> files = listFilesForFolder(new java.io.File(System.getProperty("user.dir")+"/UploadFiles/BulkFiles/Internal_OnlineImportFiles"));
				String fileName = createStringOutOfList(files);
				if(fp.onlineImportBulk(CRMUser1EmailID, null, null, null, InternalFolderPath, ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.OnlineImportPath), fileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Internal, PageName.FundsPage, Workspace.InvestorWorkspace, 30)){
					appLog.info("Online import successful.");
					ExcelUtils.writeData(excelPath, fileName, "filePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileInternal);
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30), "Refresh BUtton", action.BOOLEAN)){
						List<WebElement> filesInGridEle = fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage);
						List<String> filesInGrid = new ArrayList<String>();
						for (WebElement webElement : filesInGridEle) {
							filesInGrid.add(getText(driver, webElement, "", action.BOOLEAN));
						}
						boolean flag = true;
						String filesInExcel = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileInternal);
						for(int i = 0; i < filesInExcel.split("<break>").length; i++){
							String file = filesInExcel.split("<break>")[i];
							for(int j = 0; j < filesInGrid.size(); j++){
								if(file.equalsIgnoreCase(filesInGrid.get(j))){
									appLog.info(file+" file is successfully Imported.");
									flag = true;
									break;
								} else {
									flag = false;
								}
							}
							if(!flag){
								appLog.error(fileName+" file is  not Imported.");
								sa.assertTrue(false,fileName+" file is  not Imported.");
							}
						}
					} else {
						appLog.error("Refresh buttona cannot be clicked, So cannot verify file in content grid.");
						sa.assertTrue(false,"Refresh buttona cannot be clicked, So cannot verify file in content grid.");
					}
				} else {
					appLog.error("Online import is not successful, So we cannot verify the functionality.");
					sa.assertTrue(false,"Online import is not successful, So we cannot verify the functionality.");
				}
			} else {
				appLog.error(M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M15tc064_OnlineImportFilesInInternalFolderUpdateINV(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String InternalFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
//				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
//				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor Workspace View.");
				List<String> files = listFilesForFolder(new java.io.File(System.getProperty("user.dir")+"/UploadFiles/BulkFiles/Internal_OnlineImportFilesUpdate"));
				String fileName = createStringOutOfList(files);
				if(fp.onlineImportBulk(CRMUser1EmailID, null, null, null, InternalFolderPath, ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.OnlineImportPath), fileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPDATE, FolderType.Internal, PageName.FundsPage, Workspace.InvestorWorkspace, 30)){
					appLog.info("Online import successful.");
					ExcelUtils.writeData(excelPath, fileName, "filePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileInternal);
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30), "Refresh BUtton", action.BOOLEAN)){
						List<WebElement> filesInGridEle = fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage);
						List<String> filesInGrid = new ArrayList<String>();
						for (WebElement webElement : filesInGridEle) {
							filesInGrid.add(getText(driver, webElement, "", action.BOOLEAN));
						}
						boolean flag = true;
						String filesInExcel = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileInternal);
						for(int i = 0; i < filesInExcel.split("<break>").length; i++){
							String file = filesInExcel.split("<break>")[i];
							for(int j = 0; j < filesInGrid.size(); j++){
								if(file.equalsIgnoreCase(filesInGrid.get(j))){
									appLog.info(file+" file is successfully Imported.");
									flag = true;
									break;
								} else {
									flag = false;
								}
							}
							if(!flag){
								appLog.error(fileName+" file is  not Imported.");
								sa.assertTrue(false,fileName+" file is  not Imported.");
							}
						}
					} else {
						appLog.error("Refresh buttona cannot be clicked, So cannot verify file in content grid.");
						sa.assertTrue(false,"Refresh buttona cannot be clicked, So cannot verify file in content grid.");
					}
				} else {
					appLog.error("Online import is not successful, So we cannot verify the functionality.");
					sa.assertTrue(false,"Online import is not successful, So we cannot verify the functionality.");
				}
			} else {
				appLog.error(M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc065_1_UploadFileInStandardFolderINV(String environment, String mode){

		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String StandardFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				List<String> institutions = getValueBasedOnLabel(excelPath, "Limited Partner", excelLabel.Institutions_Name, 0);
				List<String> lpName = getValueBasedOnLabel(excelPath, "Limited Partner", excelLabel.LimitedPartner_Name, 0);
				List<String> instilp = new ArrayList<String>();
				for(int i = 0; i < institutions.size(); i++){
					instilp.add(institutions.get(i)+"/"+lpName.get(i));
				}
				String institutionOrLPName = createStringOutOfList(instilp);
				if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, StandardFolderPath, institutionOrLPName, "UploadFiles\\BulkFiles\\Standard_MultipleInstiFileInvestorWorkspace", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)){
					appLog.info("File is Uploaded Successfully in  "+StandardFolderPath);
					
				} else {
					appLog.error("Not able to upload files, So cannot continue the testcase.");
					sa.assertTrue(false,"Not able to upload files, So cannot continue the testcase.");
				}
			} else {
				appLog.error(M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc065_2_verifyUploadedFilesInvestorSide() {
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String StandardFolderFile = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, "M15tc065_1_UploadFileInStandardFolderINV", excelLabel.UploadedFileStandard);
		String StandardFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, "M15tc065_1_UploadFileInStandardFolderINV", excelLabel.StandardPath);
		lp.investorLogin(M15C1EmailId, adminPassword);
		if(ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)){
			if(fp.verifyFolderPathdummy(StandardFolderPath,null, M15LP1, null, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 30)){
				if(fp.verifyFileinContentGrid(PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, StandardFolderFile)){
					appLog.info(StandardFolderFile+" file is verified.");
				} else {
					appLog.error(StandardFolderFile+" file is not verified.");
					sa.assertTrue(false,StandardFolderFile+" file is not verified.");
					
				}
			} else {
				appLog.error(StandardFolderPath+" folder is not veirfied, So cannot veirfy file upload status.");
				sa.assertTrue(false,StandardFolderPath+" folder is not veirfied, So cannot veirfy file upload status.");
			}
		} else {
			appLog.error("Current investment cannot be clicked, So cannot verify file upload status at investor side.");
			sa.assertTrue(false,"Current investment cannot be clicked, So cannot verify file upload status at investor side.");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc065_3_verifyUploadFileInBox() {
		String dependOnTc="M15tc066_1_OnlineImportFileInStandardFolderINV";
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		List<String> investorList = new ArrayList<String>();
		List<String> lpList = new ArrayList<String>();
		List<String> downLoadedFolderPathList = new ArrayList<String>();
		List<String> itemNameList = new ArrayList<String>();
		String stdPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, dependOnTc, excelLabel.StandardPath);
		List<String> files = listFilesForFolder(new java.io.File(System.getProperty("user.dir")+"\\UploadFiles\\BulkFiles\\Standard_MultipleInstiFileInvestorWorkspace"));
		String uploadFileName = createStringOutOfListUsingComma(files);
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName1, Workspace.InvestorWorkspace, 30)){
			appLog.info("Successfully downloaded report");
		} else {
			appLog.error("Not able to download.");
			sa.assertTrue(false, "Not able to download.");
		}
		ThreadSleep(7000);
		String s =getFilesNameFromSystemFolder("DownloadedFiles");
		String fileName=null;
		if(s != null) {
			List<String> downloadedfileList=createListOutOfString(s);
			for (int i=0; i<downloadedfileList.size(); i++) {
				if(BoxPageBusinesslayer.fileName.equalsIgnoreCase(downloadedfileList.get(i))) {
					appLog.info("Downloaded File is available in download folder : "+BoxPageBusinesslayer.fileName);
					fileName=BoxPageBusinesslayer.fileName;
					investorList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.Institutions_Name, 0);
					lpList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.LimitedPartner_Name, 0);
					String downLoadedFolderPath=ExcelUtils.readAllDataForAColumn(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", 2, false);
					String ItemName = ExcelUtils.readAllDataForAColumn(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", 4, false);
					downLoadedFolderPathList=createListOutOfStringUsingComma(downLoadedFolderPath);
					itemNameList=createListOutOfStringUsingComma(ItemName);
					break;
				}else {
					if(i==downloadedfileList.size()-1) {
						appLog.error(BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
						sa.assertTrue(false, BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot get file from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot get file from downloaded folder");
		}
		if(fileName!=null && !fileName.isEmpty()) {
			for (int i = 0;i<investorList.size();i++) {
				String filepath="All Files/"+Org1FirmName+"/Current Investment/"+M15FundName1+"/"+ investorList.get(i)+"/"+ lpList.get(i)+"/"+stdPath+"/"+uploadFileName;
				appLog.info("Going to verify path:  "+filepath);
				for(int j=0; j<downLoadedFolderPathList.size(); j++) {
					if (filepath.equalsIgnoreCase(downLoadedFolderPathList.get(j).trim())) {
						appLog.info("successfully found file of "+investorList.get(i)+" on folder structure");
						if(itemNameList.get(j).equalsIgnoreCase(uploadFileName)) {
							appLog.info("Item Name is matched for folder path  :"+downLoadedFolderPathList.get(i));
							
							
						}else {
							appLog.error("Item Name is not matched for folder path : "+downLoadedFolderPathList.get(i));
							sa.assertTrue(false, "Item Name is not matched for folder path : "+downLoadedFolderPathList.get(i));
						}
						break;
					}else {
						if(j==downLoadedFolderPathList.size()-1) {
							appLog.error("could not find file of "+investorList.get(i));
							sa.assertTrue(false, "could not find file of "+investorList.get(i));
						}
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot verify upload file name from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot verify upload file name from downloaded folder");
		}
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M15tc066_1_OnlineImportFileInStandardFolderINV(String environment, String mode){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String StandardFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String importpath=ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.OnlineImportPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				List<String> institutions = getValueBasedOnLabel(excelPath, "Limited Partner", excelLabel.Institutions_Name, 0);
				List<String> lpName = getValueBasedOnLabel(excelPath, "Limited Partner", excelLabel.LimitedPartner_Name, 0);
				List<String> instilp = new ArrayList<String>();
				for(int i = 0; i < institutions.size(); i++){
					instilp.add(institutions.get(i)+"/"+lpName.get(i));
				}
				String institutionOrLPName = createStringOutOfList(instilp);
				List<String> files = listFilesForFolder(new java.io.File(System.getProperty("user.dir")+"\\UploadFiles\\BulkFiles\\Standard_MultipleInstiFileInvestorWorkspace"));
				String fileName = createStringOutOfList(files);
				if(fp.onlineImport(environment, mode, M15Institution1, M15LP1,institutionOrLPName, StandardFolderPath, importpath, fileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.MultipleInstitute, WorkSpaceAction.UPDATE, FolderType.Standard, PageName.FundsPage, Workspace.InvestorWorkspace, 30)){
					appLog.info("File is imported successfully");
					ExcelUtils.writeData(excelPath, fileName, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
					
				} else {
					appLog.error("Not able to import files, So cannot continue the testcase.");
					sa.assertTrue(false,"Not able to import files, So cannot continue the testcase.");
				}
			} else {
				appLog.error(M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot upload big size files in Standard folder.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot upload big size files in Standard folder.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc066_2_verifyOnlineImportedFilesInvestorSide() {
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String StandardFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, "M15tc066_1_OnlineImportFileInStandardFolderINV", excelLabel.StandardPath);
		String StandardFolderFile = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, "M15tc066_1_OnlineImportFileInStandardFolderINV", excelLabel.UploadedFileStandard);
		lp.investorLogin(M15C1EmailId, adminPassword);
		if(ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)){
			if(fp.verifyFolderPathdummy(StandardFolderPath, null, M15LP1, null, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 30)){
				if(fp.verifyFileinContentGrid(PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, StandardFolderFile)){
					appLog.info(StandardFolderFile+" file is verified.");
				} else {
					appLog.error(StandardFolderFile+" file is not verified.");
					sa.assertTrue(false,StandardFolderFile+" file is not verified.");
					
				}
			} else {
				appLog.error(StandardFolderPath+" folder is not veirfied, So cannot veirfy file upload status.");
				sa.assertTrue(false,StandardFolderPath+" folder is not veirfied, So cannot veirfy file upload status.");
			}
		} else {
			appLog.error("Current investment cannot be clicked, So cannot verify file upload status at investor side.");
			sa.assertTrue(false,"Current investment cannot be clicked, So cannot verify file upload status at investor side.");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc066_3_verifyOnlineImportfileInBox() {
		String dependOnTc="M15tc066_1_OnlineImportFileInStandardFolderINV";
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		List<String> investorList = new ArrayList<String>();
		List<String> lpList = new ArrayList<String>();
		List<String> downLoadedFolderPathList = new ArrayList<String>();
		List<String> itemNameList = new ArrayList<String>();
		String stdPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, dependOnTc, excelLabel.StandardPath);
		List<String> files = listFilesForFolder(new java.io.File(System.getProperty("user.dir")+"\\UploadFiles\\BulkFiles\\Standard_MultipleInstiFileInvestorWorkspace"));
		String importFileName = createStringOutOfListUsingComma(files);
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName1, Workspace.InvestorWorkspace, 30)){
			appLog.info("Successfully downloaded report");
		} else {
			appLog.error("Not able to download.");
			sa.assertTrue(false, "Not able to download.");
		}
		ThreadSleep(7000);
		String s =getFilesNameFromSystemFolder("DownloadedFiles");
		String fileName=null;
		if(s != null) {
			List<String> downloadedfileList=createListOutOfString(s);
			for (int i=0; i<downloadedfileList.size(); i++) {
				if(BoxPageBusinesslayer.fileName.equalsIgnoreCase(downloadedfileList.get(i))) {
					appLog.info("Downloaded File is available in download folder : "+BoxPageBusinesslayer.fileName);
					fileName=BoxPageBusinesslayer.fileName;
					investorList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.Institutions_Name, 0);
					lpList = CommonLib.getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.LimitedPartner_Name, 0);
					String downLoadedFolderPath=ExcelUtils.readAllDataForAColumn(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", 2, false);
					String ItemName = ExcelUtils.readAllDataForAColumn(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", 4, false);
					downLoadedFolderPathList=createListOutOfStringUsingComma(downLoadedFolderPath);
					itemNameList=createListOutOfStringUsingComma(ItemName);
					break;
				}else {
					if(i==downloadedfileList.size()-1) {
						appLog.error(BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
						sa.assertTrue(false, BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot get file from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot get file from downloaded folder");
		}
		if(fileName!=null && !fileName.isEmpty()) {
			for (int i = 0;i<investorList.size();i++) {
				String filepath="All Files/"+Org1FirmName+"/Current Investment/"+M15FundName1+"/"+ investorList.get(i)+"/"+ lpList.get(i)+"/"+stdPath+"/"+importFileName;
				appLog.info("Going to verify path:  "+filepath);
				for(int j=0; j<downLoadedFolderPathList.size(); j++) {
					if (filepath.equalsIgnoreCase(downLoadedFolderPathList.get(j).trim())) {
						appLog.info("successfully found file of "+investorList.get(i)+" on folder structure");
						if(itemNameList.get(j).equalsIgnoreCase(importFileName)) {
							appLog.info("Item Name is matched for folder path  :"+downLoadedFolderPathList.get(i));
							
							
						}else {
							appLog.error("Item Name is not matched for folder path : "+downLoadedFolderPathList.get(i));
							sa.assertTrue(false, "Item Name is not matched for folder path : "+downLoadedFolderPathList.get(i));
						}
						break;
					}else {
						if(j==downLoadedFolderPathList.size()-1) {
							appLog.error("could not find file of "+investorList.get(i));
							sa.assertTrue(false, "could not find file of "+investorList.get(i));
						}
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot verify upload file name from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot verify upload file name from downloaded folder");
		}
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc067_TurnOnManageApprovalInvestorWorkspace(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName, adminPassword);
		if(lp.clickOnTab(TabName.NIMTab)){
			switchToFrame(driver, 30, nim.getFrame( PageName.NavatarInvestorManager, 30));
			if(nim.clickOnSideMenusTab(sideMenu.ManageApprovals)){
				List<String> aa = nim.activateManageApprovalsSettings(CRMUser1FirstName+" "+CRMUser1LastName);
				if(aa.isEmpty()){
					appLog.info("Successfully Turned on manage approval.");
				} else {
					for (String string : aa) {
						sa.assertTrue(false,string);
					}
				}
			} else {
				appLog.error("Manage approval tab cannot be clicked, So cannot Turn on manage approval.");
				sa.assertTrue(false,"Manage approval tab cannot be clicked, So cannot Turn on manage approval.");
			}
		} else {
			appLog.error("NIM Tab cannot be clicked, So cannot Turn on manage approval.");
			sa.assertTrue(false,"NIM Tab cannot be clicked, So cannot Turn on manage approval.");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc068_UploadFilesUnderCommonFolderInvestorWorkspace(String environment, String mode){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String CommonFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String CommonFolderFiles = null;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, CommonFolderPath, null, "UploadFiles\\BulkFiles\\Common_ManageApprovalFilesInvestorWorkspace", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)){
					appLog.info("Files uploaded successfully.");
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30), "Refresh BUtton", action.BOOLEAN)){
						CommonFolderFiles = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
						for(int i = 0; i < CommonFolderFiles.split("<break>").length; i++){
							String fileName = CommonFolderFiles.split("<break>")[i];
							if(fp.getStatusOfFile(PageName.FundsPage, Workspace.InvestorWorkspace, fileName).toString().equalsIgnoreCase(Status.Pending.toString())){
								appLog.info(fileName+" is in pending status.");
							} else {
								appLog.error(fileName+" is not in Pending status.");
								sa.assertTrue(false,fileName+" is not in Pending status.");
							}
						}
					} else {
						appLog.error("Refresh button cannot be clicked, So cannot check file status.");
						sa.assertTrue(false,"Refresh button cannot be clicked, So cannot check file status.");
					}
				} else {
					appLog.error("Not able to upload file in folder, So cannot continue with the tc.");
					sa.assertTrue(false,"Not able to upload file in folder, So cannot continue with the tc.");
				}
			} else {
				appLog.error(M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds Tab cannot be clicked, So cannot continue with the testcase.");
			sa.assertTrue(false,"Funds Tab cannot be clicked, So cannot continue with the testcase.");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M15tc069_ApproveDocumentAndVerifyStatusINV(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependsOn = "M15tc068_UploadFilesUnderCommonFolderInvestorWorkspace";
		String CommonFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, dependsOn, excelLabel.CommonPath);
		String CommonFolderFiles = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, dependsOn, excelLabel.UploadedFileCommon);
		boolean flag = false;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if(click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "Manage Approval Icon", action.SCROLLANDBOOLEAN)){
					if(click(driver, fp.getManageApprovalsAllDocumentSelectCheckBox(30), "Select all check box", action.BOOLEAN)){
						if(click(driver, fp.getManageApprovalsApproveBtn(30), "Approve button", action.BOOLEAN)){
							if(click(driver, fp.getManageApprovalsConfirmYesBtn(30), "Yes button", action.BOOLEAN)){
								if(click(driver, fp.getManageApprovalsCloseBtn(30), "Confirmation Close button", action.BOOLEAN)){
									appLog.info("Successfully approved the documents.");
									click(driver, fp.getManageApprovalsCancelBtn(30), "cancel button", action.SCROLLANDBOOLEAN);
									flag = true;
								} else {
									refresh(driver);
									switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
									scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor Workspaec view");
								}
								if(fp.verifyFolderPathdummy(CommonFolderPath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)){
									CommonFolderFiles = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, dependsOn, excelLabel.UploadedFileCommon);
									for(int i = 0; i < CommonFolderFiles.split("<break>").length; i++){
										String fileName = CommonFolderFiles.split("<break>")[i];
										if(fp.getStatusOfFile(PageName.FundsPage, Workspace.InvestorWorkspace, fileName).toString().equalsIgnoreCase(Status.Approved.toString())){
											appLog.info(fileName+" is in Approved status and is verified.");
										} else {
											appLog.error(fileName+" is not in Approved status in crm content grid and is not verified.");
											sa.assertTrue(false,fileName+" is not Approved status in crm content grid and is not verified.");
										}
									}
								} else {
									appLog.error(CommonFolderPath+" folder path is not verified.");
									sa.assertTrue(false,CommonFolderPath+" folder path is not verified.");
								}
							} else {
								appLog.error("Approval confirmation yes button cannot be clicked, So cannot continue with the tc.");
								sa.assertTrue(false,"Approval confirmation yes button cannot be clicked, So cannot continue with the tc.");
							}
						} else {
							appLog.error("Approve button cannot be clicked, So cannot continue with the tc.");
							sa.assertTrue(false,"Approve button cannot be clicked, So cannot continue with the tc.");
						}
					} else {
						appLog.error("Cannot click on select all check box, So cannot approve all the documents.");
						sa.assertTrue(false,"Cannot click on select all check box, So cannot approve all the documents.");
					}
				} else {
					appLog.error("Manage approval icon cannot be clicked, So cannot Approve the files.");
					sa.assertTrue(false,"Manage approval icon cannot be clicked, So cannot Approve the files.");
				}
			} else {
				appLog.error(M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds Tab cannot be clicked, So cannot continue with the testcase.");
			sa.assertTrue(false,"Funds Tab cannot be clicked, So cannot continue with the testcase.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		if(flag){
			driver.close();
			config(browserToLaunch);
			lp= new LoginPageBusinessLayer(driver);
			InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
			fp = new FundsPageBusinessLayer(driver);
			lp.investorLogin(M15C1EmailId, adminPassword);
			if(ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)){
				if(fp.verifyFolderPathdummy(CommonFolderPath, null, null, null, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 30)){
					for(int i = 0; i < CommonFolderFiles.split("<break>").length; i++){
						if(fp.verifyFileinContentGrid(PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, CommonFolderFiles.split("<break>")[i])){
							appLog.info(CommonFolderFiles.split("<break>")[i]+" file is verified investor side.");
						} else {
							appLog.error(CommonFolderFiles.split("<break>")[i]+" file is not verified investor side.");
							sa.assertTrue(false,CommonFolderFiles.split("<break>")[i]+" file is not verified investor side.");
						}
					}
				} else {
					appLog.error(CommonFolderPath+" folder is not veirfied, So cannot veirfy file after approval.");
					sa.assertTrue(false,CommonFolderPath+" folder is not veirfied, So cannot veirfy file after approval.");
				}
			} else {
				appLog.error("Current investment cannot be clicked, So cannot verify file upload status at investor side.");
				sa.assertTrue(false,"Current investment cannot be clicked, So cannot verify file upload status at investor side.");
			}
			lp.investorLogout();
		}
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc070_UpdateFilesAvailableInCommonFolderAndVerifyErrorMessageINV(String environment, String mode){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependsOn = "M15tc068_UploadFilesUnderCommonFolderInvestorWorkspace";
		String CommonFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, dependsOn, excelLabel.CommonPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, CommonFolderPath, null, "UploadFiles\\BulkFiles\\Common_ManageApprovalUpdateFilesINV", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)){
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					if(click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "Manage Approval Icon", action.SCROLLANDBOOLEAN)){
						if(click(driver, fp.getManageApprovalsAllDocumentSelectCheckBox(30), "Select all check box", action.BOOLEAN)){
							if(click(driver, fp.getManageApprovalsApproveBtn(30), "Approve button", action.BOOLEAN)){
								if(click(driver, fp.getManageApprovalsConfirmYesBtn(30), "Yes button", action.BOOLEAN)){
									if(click(driver, fp.getManageApprovalsUpdateAllDocument(30), "Update all button", action.BOOLEAN)){
										if(fp.getSizeLimitErrorMessage(30)!=null){
											String msg = fp.getSizeLimitErrorMessage(30).getText().trim();
											if(msg.contains(FundsPageErrorMessage.ManageApprovalFileSizeLimitErrorMsg)){
												appLog.info("Size limit error message is verified.");
											} else {
												appLog.error("Size limit error message is not verified. Expected: "+FundsPageErrorMessage.ManageApprovalFileSizeLimitErrorMsg+"\tActual: "+msg);
												sa.assertTrue(false,"Size limit error message is not verified. Expected: "+FundsPageErrorMessage.ManageApprovalFileSizeLimitErrorMsg+"\tActual: "+msg);
											}
										} else {
											appLog.error("Size limit error message is not poping up.");
											sa.assertTrue(false,"Size limit error message is not poping up.");
										}
									} else {
										appLog.error("Update all button cannot be clicked, SO cannot verify size limit error message.");
										sa.assertTrue(false,"Update all button cannot be clicked, SO cannot verify size limit error message.");
									}
								} else {
									appLog.error("Approval confirmation yes button cannot be clicked, So cannot continue with the tc.");
									sa.assertTrue(false,"Approval confirmation yes button cannot be clicked, So cannot continue with the tc.");
								}
								
							} else {
								appLog.error("Approve button cannot be clicked, So cannot continue with the tc.");
								sa.assertTrue(false,"Approve button cannot be clicked, So cannot continue with the tc.");
							}
						} else {
							appLog.error("Cannot click on select all check box, So cannot approve all the documents.");
							sa.assertTrue(false,"Cannot click on select all check box, So cannot approve all the documents.");
						}
					} else {
						appLog.error("Manage approval icon cannot be clicked, So cannot Approve the files.");
						sa.assertTrue(false,"Manage approval icon cannot be clicked, So cannot Approve the files.");
					}
				} else {
					appLog.error("Not able to upload file in folder, So cannot continue with the tc.");
					sa.assertTrue(false,"Not able to upload file in folder, So cannot continue with the tc.");
				}
			} else {
				appLog.error(M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds Tab cannot be clicked, So cannot continue with the testcase.");
			sa.assertTrue(false,"Funds Tab cannot be clicked, So cannot continue with the testcase.");
		}
		switchToDefaultContent(driver);
		sa.assertAll();
	}

	@Parameters({ "environment", "mode" }) @Test
	public void M15tc071_1_UploadFilesInStandardFolderINV(String environment, String mode){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String StandardFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				List<String> institutions = getValueBasedOnLabel(excelPath, "Limited Partner", excelLabel.Institutions_Name, 0).subList(0, 11);
				List<String> lpName = getValueBasedOnLabel(excelPath, "Limited Partner", excelLabel.LimitedPartner_Name, 0).subList(0, 11);
				List<String> instilp = new ArrayList<String>();
				for(int i = 0; i < institutions.size(); i++){
					instilp.add(institutions.get(i)+"/"+lpName.get(i));
				}
				String institutionOrLPName = createStringOutOfList(instilp);
				if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, StandardFolderPath, institutionOrLPName, "UploadFiles\\BulkFiles\\Standard_ManageApprovalFilesInvestorWorkspace", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 60)){
					appLog.info("File is uploaded Successfully in "+StandardFolderPath);
				} else {
					appLog.error("Not able to upload file in folder, So cannot continue with the tc.");
					sa.assertTrue(false,"Not able to upload file in folder, So cannot continue with the tc.");
				}
			} else {
				appLog.error(M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds Tab cannot be clicked, So cannot continue with the testcase.");
			sa.assertTrue(false,"Funds Tab cannot be clicked, So cannot continue with the testcase.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc071_2_verifyUploadedFileInBox() {
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		List<String> investorList = new ArrayList<String>();
		List<String> lpList = new ArrayList<String>();
		List<String> downLoadedFolderPathList = new ArrayList<String>();
		List<String> itemNameList = new ArrayList<String>();
		String[] splitedUploadedFiles = null;
		String uploadedFile=null;
		String dependOnTc="M15tc071_1_UploadFilesInStandardFolderINV";
		String stdPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, dependOnTc, excelLabel.StandardPath);
		driver.get("https://account.box.com/login");
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName1, Workspace.InvestorWorkspace, 30)){
			appLog.info("File is downloaded successfully from box");
			
		} else {
			appLog.error("Not able to download report, So cannot continue with tc.");
			sa.assertTrue(false,"Not able to download report, So cannot continue with tc.");
		}
			
		ThreadSleep(7000);
		String s =getFilesNameFromSystemFolder("DownloadedFiles");
		String fileName=null;
		if(s != null) {
			List<String> downloadedfileList=createListOutOfString(s);
			for (int i=0; i<downloadedfileList.size(); i++) {
				if(BoxPageBusinesslayer.fileName.equalsIgnoreCase(downloadedfileList.get(i))) {
					appLog.info("Downloaded File is available in download folder : "+BoxPageBusinesslayer.fileName);
					fileName=BoxPageBusinesslayer.fileName;
					investorList = getValueBasedOnLabel(excelPath, "Institutions", excelLabel.Institutions_Name, 0).subList(0, 11);
					lpList= getValueBasedOnLabel(excelPath, "Limited Partner", excelLabel.LimitedPartner_Name, 0).subList(0, 11);
					String downLoadedFolderPath=ExcelUtils.readAllDataForAColumn(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", 2, false);
					String ItemName = ExcelUtils.readAllDataForAColumn(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", 4, false);
					downLoadedFolderPathList=createListOutOfStringUsingComma(downLoadedFolderPath);
					itemNameList=createListOutOfStringUsingComma(ItemName);
					uploadedFile = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileStandard);
					splitedUploadedFiles =uploadedFile.split("<break>");
					break;
				}else {
					if(i==downloadedfileList.size()-1) {
						appLog.error(BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
						sa.assertTrue(false, BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot get file from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot get file from downloaded folder");
		}	
		if(fileName!=null && !fileName.isEmpty()) {
			for (int i = 0;i<investorList.size();i++) {
				for(int k=0; k<splitedUploadedFiles.length; k++) {
					String filepath="All Files/"+Org1FirmName+"/Current Investment/"+M15FundName1+"/"+ investorList.get(i)+"/"+lpList.get(i)+"/"+stdPath+"/"+splitedUploadedFiles[k];
					appLog.info("Going to verify path:  "+filepath);
					for(int j=0; j<downLoadedFolderPathList.size(); j++) {
						if (filepath.equalsIgnoreCase(downLoadedFolderPathList.get(j).trim())) {
							appLog.error("successfully found file of "+investorList.get(i)+" on folder structure");
							sa.assertTrue(false, "successfully found file of "+investorList.get(i)+" on folder structure");
							if(itemNameList.get(j).equalsIgnoreCase(splitedUploadedFiles[k])) {
								appLog.error("File Name is available for folder path  :"+downLoadedFolderPathList.get(i));
								sa.assertTrue(false, "File Name is available for folder path  :"+downLoadedFolderPathList.get(i));
							}else {
								appLog.info("File Name : "+splitedUploadedFiles[k]+" is not matched for folder path : "+downLoadedFolderPathList.get(i));
								
							}
							break;
						}else {
							if(j==downLoadedFolderPathList.size()-1) {
								appLog.info("could not find file path of "+investorList.get(i));
								
							}
						}
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot verify upload file name from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot verify upload file name from downloaded folder");
		}
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc072_VerifyErrorMessageForMoreThan100FilesINV(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if(click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "Manage Approval Icon", action.SCROLLANDBOOLEAN)){
					if(click(driver, fp.getManageApprovalsAllDocumentSelectCheckBox(30), "Select all check box", action.BOOLEAN)){
						if(click(driver, fp.getManageApprovalsApproveBtn(30), "Approve button", action.BOOLEAN)){
							if(isAlertPresent(driver)){
								String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
								switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
								if(msg.equalsIgnoreCase(FundsPageErrorMessage.ApproveMoreThan100FilesErrorMsg)){
									appLog.info("Error message verified.");
								} else {
									appLog.error("Alert message is not verified.Expected: "+FundsPageErrorMessage.ApproveMoreThan100FilesErrorMsg+"\tActual: "+msg);
									sa.assertTrue(false,"Alert message is not verified.Expected: "+FundsPageErrorMessage.ApproveMoreThan100FilesErrorMsg+"\tActual: "+msg);
								}
							} else {
								appLog.error("No Alert for approving more than 100 files at a time.");
								sa.assertTrue(false,"No Alert for approving more than 100 files at a time.");
							}
							
						} else {
							appLog.error("Approve button cannot be clicked, So cannot continue with the tc.");
							sa.assertTrue(false,"Approve button cannot be clicked, So cannot continue with the tc.");
						}
					} else {
						appLog.error("Cannot click on select all check box, So cannot approve all the documents.");
						sa.assertTrue(false,"Cannot click on select all check box, So cannot approve all the documents.");
					}
				} else {
					appLog.error("Manage approval icon cannot be clicked, So cannot Approve the files.");
					sa.assertTrue(false,"Manage approval icon cannot be clicked, So cannot Approve the files.");
				}
			} else {
				appLog.error("Not able to upload file in folder, So cannot continue with the tc.");
				sa.assertTrue(false,"Not able to upload file in folder, So cannot continue with the tc.");
			}
			} else {
				appLog.error(M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
			}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc073_ApproveFileOfStandardFolderINV(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependsOn = "M15tc071_1_UploadFilesInStandardFolderINV";
		String StandardFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, dependsOn, excelLabel.StandardPath);
		String StandardFolderFiles = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, dependsOn, excelLabel.UploadedFileStandard);
		boolean flag = true;
		List<String> institutionNameList=getValueBasedOnLabel(excelPath, "Limited Partner", excelLabel.Institutions_Name, 0);
		List<String> lpLsit=getValueBasedOnLabel(excelPath, "Limited Partner", excelLabel.LimitedPartner_Name, 0);
		
		String date=getDateAccToTimeZone("America/New_York", "MM/dd/YYYY");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if(click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "Manage Approval icon", action.SCROLLANDBOOLEAN)){
					if(selectVisibleTextFromDropDown(driver, fp.getManageApprovalsDropdown(ManageApprovalTabs.PendingDocuments, 30), "Pending documents", "All Pending Documents")){
						List<String> institutions = institutionNameList.subList(0, 10);
						List<String> lpName = lpLsit.subList(0, 10);
						List<String> instilp = new ArrayList<String>();
						for(int i = 0; i < institutions.size(); i++){
							instilp.add(institutions.get(i)+"/"+lpName.get(i));
						}
						String institutionOrLPName = createStringOutOfList(instilp);
						//span[contains(@id,'pendingGrid-cell-2')][@title='Fund 1 > Bulk Inst8 > Standard22']/preceding-sibling::span[@title='StandMA3.pdf']/preceding-sibling::span//input
						for(int i = 0; i < institutionOrLPName.split("<break>").length-1; i++){
							for(int j = 0; j < StandardFolderFiles.split("<break>").length; j++){
								String fileName = StandardFolderFiles.split("<break>")[j];
								String instName = institutionOrLPName.split("<break>")[i].split("/")[0];
								String lpName1 = institutionOrLPName.split("<break>")[i].split("/")[1];
//								fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), fileName, M15FundName1+" > "+instName+" > "+lpName1+" > "+StandardFolderPath, CRMUser1FirstName+" "+CRMUser1LastName,Org1FirmName,date);
								WebElement ele = FindElement(driver, "//span[contains(@id,'pendingGrid-cell-2')][@title='"+M15FundName1+" > "+instName+" > "+lpName1+" > "+StandardFolderPath+"']/preceding-sibling::span[@title='"+ fileName +"']/preceding-sibling::span//input", fileName+" file check box", action.BOOLEAN, 30);
								if(click(driver, ele, fileName+" check box", action.SCROLLANDBOOLEAN)){
									appLog.info("Successfully Clicked on file '"+fileName+"' of folder path'"+M15FundName1+" > "+instName+" > "+lpName1+" > "+StandardFolderPath+"'.");
								} else {
									appLog.error("Cannot click on file '"+fileName+"' of folder path'"+M15FundName1+" > "+instName+" > "+lpName1+" > "+StandardFolderPath+"', So cannot verify files.");
									sa.assertTrue(false,"Cannot click on file '"+fileName+"' of folder path'"+M15FundName1+" > "+instName+" > "+lpName1+" > "+StandardFolderPath+"', So cannot verify files.");
									flag = false;
								}
								
							}
						}
						if(flag){
							if(click(driver, fp.getManageApprovalsApproveBtn(30), "Approve button", action.BOOLEAN)){
								if(click(driver, fp.getManageApprovalsConfirmYesBtn(30), "Yes button", action.BOOLEAN)){
									if(click(driver, fp.getManageApprovalsCloseBtn(30), "Confirmation Close button", action.BOOLEAN)){
										appLog.info("Successfully approved the documents.");
									} else {
									}
									refresh(driver);
									switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
									scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor Workspaec view");
									flag = true;
								} else {
									appLog.error("Approval confirmation yes button cannot be clicked, So cannot continue with the tc.");
									sa.assertTrue(false,"Approval confirmation yes button cannot be clicked, So cannot continue with the tc.");
								}
							} else {
								appLog.error("Approve button cannot be clicked, So cannot continue with the tc.");
								sa.assertTrue(false,"Approve button cannot be clicked, So cannot continue with the tc.");
							}
						} else {
							appLog.error("All the files are not selected, SO cannot continue with the tc.");
							sa.assertTrue(false,"All the files are not selected, SO cannot continue with the tc.");
						}
						
						if(flag){
							if(click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "Manage Approval icon", action.SCROLLANDBOOLEAN)){
								if(selectVisibleTextFromDropDown(driver, fp.getManageApprovalsDropdown(ManageApprovalTabs.PendingDocuments, 30), "Pending documents", "All Pending Documents")){
									institutions = institutionNameList.subList(0, 11);
									lpName = lpLsit.subList(0, 11);
									instilp = new ArrayList<String>();
									for(int i = 0; i < institutions.size(); i++){
										instilp.add(institutions.get(i)+"/"+lpName.get(i));
									}
									institutionOrLPName = createStringOutOfList(instilp);
									//span[contains(@id,'pendingGrid-cell-2')][@title='Fund 1 > Bulk Inst8 > Standard22']/preceding-sibling::span[@title='StandMA3.pdf']/preceding-sibling::span//input
										for(int j = 0; j < StandardFolderFiles.split("<break>").length; j++){
											String fileName = StandardFolderFiles.split("<break>")[j];
											String instName = institutionOrLPName.split("<break>")[institutionOrLPName.split("<break>").length-1].split("/")[0];
											String lpName1 = institutionOrLPName.split("<break>")[institutionOrLPName.split("<break>").length-1].split("/")[1];
//											fp.findRowByScrollingManageApprovals(ManageApprovalTabs.PendingDocuments, fp.manageApprovalsScrollBox(ManageApprovalTabs.PendingDocuments, 30), fileName, M15FundName1+" > "+instName+" > "+lpName1+" > "+StandardFolderPath, CRMUser1FirstName+" "+CRMUser1LastName,Org1FirmName,date);
											WebElement ele = FindElement(driver, "//span[contains(@id,'pendingGrid-cell-2')][@title='"+M15FundName1+" > "+instName+" > "+lpName1+" > "+StandardFolderPath+"']/preceding-sibling::span[@title='"+ fileName +"']/preceding-sibling::span//input", fileName+" file check box", action.BOOLEAN, 30);
											if(click(driver, ele, fileName+" check box", action.SCROLLANDBOOLEAN)){
												appLog.info("Successfully Clicked on file '"+fileName+"' of folder path'"+M15FundName1+" > "+instName+" > "+lpName1+" > "+StandardFolderPath+"'.");
											} else {
												appLog.error("Cannot click on file '"+fileName+"' of folder path'"+M15FundName1+" > "+instName+" > "+lpName1+" > "+StandardFolderPath+"', So cannot verify files.");
												sa.assertTrue(false,"Cannot click on file '"+fileName+"' of folder path'"+M15FundName1+" > "+instName+" > "+lpName1+" > "+StandardFolderPath+"', So cannot verify files.");
												flag = false;
											}
											
										}
								} else {
									appLog.error("All pending documents cannot be selected, So cannot approve docs and verify.");
									sa.assertTrue(false,"All pending documents cannot be selected, So cannot approve docs and verify.");
								}
							} else {
								appLog.error("Manage approval icon cannot be clicked, So cannot approve and vrify files.");
								sa.assertTrue(false,"Manage approval icon cannot be clicked, So cannot approve and vrify files.");
							}
						}
						
						if(flag){
							if(click(driver, fp.getManageApprovalsApproveBtn(30), "Approve button", action.BOOLEAN)){
								if(click(driver, fp.getManageApprovalsConfirmYesBtn(30), "Yes button", action.BOOLEAN)){
									if(click(driver, fp.getManageApprovalsCloseBtn(30), "Confirmation Close button", action.BOOLEAN)){
										appLog.info("Successfully approved the documents.");
									} else {
									}
									refresh(driver);
									switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
									scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor Workspaec view");
									flag = true;
								} else {
									appLog.error("Approval confirmation yes button cannot be clicked, So cannot continue with the tc.");
									sa.assertTrue(false,"Approval confirmation yes button cannot be clicked, So cannot continue with the tc.");
								}
							} else {
								appLog.error("Approve button cannot be clicked, So cannot continue with the tc.");
								sa.assertTrue(false,"Approve button cannot be clicked, So cannot continue with the tc.");
							}
						} else {
							appLog.error("All the files are not selected, SO cannot continue with the tc.");
							sa.assertTrue(false,"All the files are not selected, SO cannot continue with the tc.");
						}
						
						if(flag){
							if(click(driver, fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "Manage Approval icon", action.SCROLLANDBOOLEAN)){
								if(click(driver, fp.getApprovedDocsTab(30), "Approved doc tab", action.BOOLEAN)){
									institutions = institutionNameList.subList(0, 11);
									lpName = lpLsit.subList(0, 11);
									instilp = new ArrayList<String>();
									for(int i = 0; i < institutions.size(); i++){
										instilp.add(institutions.get(i)+"/"+lpName.get(i));
									}
									institutionOrLPName = createStringOutOfList(instilp);
									//span[contains(@id,'pendingGrid-cell-2')][@title='Fund 1 > Bulk Inst8 > Standard22']/preceding-sibling::span[@title='StandMA3.pdf']/preceding-sibling::span//input
										for(int j = 0; j < StandardFolderFiles.split("<break>").length; j++){
											String fileName = StandardFolderFiles.split("<break>")[j];
											String instName = institutionOrLPName.split("<break>")[institutionOrLPName.split("<break>").length-1].split("/")[0];
											String lpName1 = institutionOrLPName.split("<break>")[institutionOrLPName.split("<break>").length-1].split("/")[1];
											WebElement ele = FindElement(driver, "//span[@title='"+M15FundName1+" > "+instName+" > "+lpName1+" > "+StandardFolderPath+"']/preceding-sibling::span[@title='"+ fileName +"']", fileName+" file check box", action.BOOLEAN, 30);
											if(ele!=null){
												appLog.info("File '"+fileName+"' of folder path'"+M15FundName1+" > "+instName+" > "+lpName1+" > "+StandardFolderPath+"' is present in approved docs grid and is verified.");
											} else {
												appLog.error("File '"+fileName+"' of folder path'"+M15FundName1+" > "+instName+" > "+lpName1+" > "+StandardFolderPath+"' is not present in approved docs grid.");
												sa.assertTrue(false,"File '"+fileName+"' of folder path'"+M15FundName1+" > "+instName+" > "+lpName1+" > "+StandardFolderPath+"' is not present in approved docs grid.");
												flag = false;
											}
											
										}
										refresh(driver);
										switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
										scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Workspace view.");
										
								} else {
									appLog.error("Cannot click on approved docs tab, So cannot verify error msg.");
									sa.assertTrue(false,"Cannot click on approved docs tab, So cannot verify error msg.");
								}
							} else {
								appLog.error("Manage approval icon cannot be clicked, So cannot approve and vrify files.");
								sa.assertTrue(false,"Manage approval icon cannot be clicked, So cannot approve and vrify files.");
							}
						}
						
						if(flag){
							institutions = institutionNameList.subList(0, 11);
							lpName = lpLsit.subList(0, 11);
							instilp = new ArrayList<String>();
							for(int i = 0; i < institutions.size(); i++){
								instilp.add(institutions.get(i)+"/"+lpName.get(i));
							}
							institutionOrLPName = createStringOutOfList(instilp);
							StandardFolderFiles = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
							for(int i = 0; i < institutionOrLPName.split("<break>").length; i++){
								if(fp.verifyFolderPathdummy(StandardFolderPath, institutionOrLPName.split("<break>")[i].split("/")[0], institutionOrLPName.split("<break>")[i].split("/")[1], null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)){
									for(int j = 0; j < StandardFolderFiles.split("<break>").length; j++){
										String fileName = StandardFolderFiles.split("<break>")[j];
										if(fp.getStatusOfFile(PageName.FundsPage, Workspace.InvestorWorkspace, fileName).toString().equalsIgnoreCase(Status.Approved.toString())){
											appLog.info(fileName+" is in Approved status and is verified.");
										} else {
											appLog.error(fileName+" is not in Approved status in crm content grid and is not verified.");
											sa.assertTrue(false,fileName+" is not Approved status in crm content grid and is not verified.");
										}
									}
								} else {
									appLog.error(StandardFolderPath+" path is not verified, So cannot verify file status for institution '"+institutionOrLPName.split("<break>")[i]+"'.");
									sa.assertTrue(false,StandardFolderPath+" path is not verified, So cannot verify file status for institution '"+institutionOrLPName.split("<break>")[i]+"'.");
								}
								
							}
						}
					} else {
						appLog.error("All pending documents cannot be selected, So cannot approve docs and verify.");
						sa.assertTrue(false,"All pending documents cannot be selected, So cannot approve docs and verify.");
					}
				} else {
					appLog.error("Manage approval icon cannot be clicked, So cannot approve and vrify files.");
					sa.assertTrue(false,"Manage approval icon cannot be clicked, So cannot approve and vrify files.");
				}
			} else {
				appLog.error("Not able to upload file in folder, So cannot continue with the tc.");
				sa.assertTrue(false,"Not able to upload file in folder, So cannot continue with the tc.");
			}
			} else {
				appLog.error(M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
			}
		
		if(flag){
			driver.close();
			config(browserToLaunch);
			lp= new LoginPageBusinessLayer(driver);
			InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
			fp = new FundsPageBusinessLayer(driver);
			lp.investorLogin(M15C1EmailId, adminPassword);
			if(ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)){
				if(fp.verifyFolderPathdummy(StandardFolderPath, M15Institution1, M15LP1, null, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 30)){
					for(int i = 0; i < StandardFolderPath.split("<break>").length; i++){
						if(fp.verifyFileinContentGrid(PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, StandardFolderPath.split("<break>")[i])){
							appLog.info(StandardFolderPath.split("<break>")[i]+" file is verified investor side.");
						} else {
							appLog.error(StandardFolderPath.split("<break>")[i]+" file is not verified investor side.");
							sa.assertTrue(false,StandardFolderPath.split("<break>")[i]+" file is not verified investor side.");
						}
					}
				} else {
					appLog.error(StandardFolderPath+" folder is not veirfied, So cannot veirfy file after approval.");
					sa.assertTrue(false,StandardFolderPath+" folder is not veirfied, So cannot veirfy file after approval.");
				}
			} else {
				appLog.error("Current investment cannot be clicked, So cannot verify file upload status at investor side.");
				sa.assertTrue(false,"Current investment cannot be clicked, So cannot verify file upload status at investor side.");
			}
			lp.investorLogout();
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc074_0_DeactivateManageApprovalINV(){
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.NIMTab)){
			switchToFrame(driver, 30, nim.getFrame( PageName.NavatarInvestorManager, 30));
			if(nim.deactivateManageApprovalsSetting()){
				appLog.info("Manage approval is deactivated successfully.");
			} else {
				appLog.error("Not able to deactivate manage approval setting.");
				sa.assertTrue(false,"Not able to deactivate manage approval setting.");
			}
			
		} else {
			appLog.error("Nim Tab cannot be clicked, So cannot deavtivate manage approval.");
			sa.assertTrue(false,"Nim Tab cannot be clicked, So cannot deavtivate manage approval.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc074_1_UpdateFileWithDifferentNameInStandardFolderINV() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String StandardFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String UpdatestdfilesName = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
		String uploadstdfilesName = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, "M15tc065_1_UploadFileInStandardFolderINV", excelLabel.UploadedFileStandard);
		String docPath=System.getProperty("user.dir") + "\\UploadFiles\\BulkFiles\\Standard_UpdateMultipleInstiFile\\";
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				if(fp.updateBulkFile(CRMUser1EmailID,StandardFolderPath, uploadstdfilesName, M15Institution1, M15LP1, FolderType.Standard,docPath+UpdatestdfilesName, multiInstance.AllInvestor, null, ContentGridArrowKeyFunctions.Update,20, PageName.FundsPage,"Yes",null, Workspace.InvestorWorkspace)) {
					appLog.info("file is updated successfully: "+uploadstdfilesName+" in :"+StandardFolderPath+" to "+UpdatestdfilesName);
					
				}else {
					appLog.error("file is not updated: "+uploadstdfilesName+" in :"+UpdatestdfilesName);
					sa.assertTrue(false, "file is not updated: "+uploadstdfilesName+" in :"+UpdatestdfilesName);
				}
			} else {
				appLog.error(M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+ " fund is not present in list, So cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot upload big size files in Internal folder.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc074_2_verifyUpdatedFileInBox() {
		String dependOnTc="M15tc074_1_UpdateFileWithDifferentNameInStandardFolderINV";
		BoxPageBusinesslayer box = new BoxPageBusinesslayer(driver);
		List<String> investorList = new ArrayList<String>();
		List<String> lpList = new ArrayList<String>();
		List<String> downLoadedFolderPathList = new ArrayList<String>();
		List<String> itemNameList = new ArrayList<String>();
		String StandardFolderFile =  ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileStandard);
		String StandardFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, dependOnTc, excelLabel.StandardPath);
		driver.get("https://account.box.com/login");
		if(box.exportFolderStructureReport(BoxUserName, BoxPassword, Org1FirmName, M15FundName1, Workspace.InvestorWorkspace, 30)){
			appLog.info("Successfully downloaded report");
		} else {
			appLog.error("Not able to download report, So cannot continue with tc.");
			sa.assertTrue(false,"Not able to download report, So cannot continue with tc.");
		}
		ThreadSleep(7000);
		String s =getFilesNameFromSystemFolder("DownloadedFiles");
		String fileName=null;
		if(s != null) {
			List<String> downloadedfileList=createListOutOfString(s);
			for (int i=0; i<downloadedfileList.size(); i++) {
				if(BoxPageBusinesslayer.fileName.equalsIgnoreCase(downloadedfileList.get(i))) {
					appLog.info("Downloaded File is available in download folder : "+BoxPageBusinesslayer.fileName);
					fileName=BoxPageBusinesslayer.fileName;
					investorList = getValueBasedOnLabel(excelPath,"Institutions", excelLabel.Institutions_Name, 0);
					lpList = getValueBasedOnLabel(excelPath,"Limited Partner", excelLabel.LimitedPartner_Name, 0);
					String downLoadedFolderPath=ExcelUtils.readAllDataForAColumn(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", 2, false);
					String ItemName = ExcelUtils.readAllDataForAColumn(System.getProperty("user.dir")+"\\DownloadedFiles\\"+BoxPageBusinesslayer.fileName, "Sheet1", 4, false);
					downLoadedFolderPathList=createListOutOfStringUsingComma(downLoadedFolderPath);
					itemNameList=createListOutOfStringUsingComma(ItemName);
					break;
				}else {
					if(i==downloadedfileList.size()-1) {
						appLog.error(BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
						sa.assertTrue(false, BoxPageBusinesslayer.fileName+" : File is not available in downloaded file folder ");
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot get file from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot get file from downloaded folder");
		}
		
		if(fileName!=null && !fileName.isEmpty()) {
			for (int i = 0;i<investorList.size();i++) {
				String filepath="All Files/"+Org1FirmName+"/Current Investment/"+M15FundName1+"/"+ investorList.get(i)+"/"+ lpList.get(i)+"/"+StandardFolderPath+"/"+StandardFolderFile;
				appLog.info("Going to verify path:  "+filepath);
				for(int j=0; j<downLoadedFolderPathList.size(); j++) {
					if (filepath.equalsIgnoreCase(downLoadedFolderPathList.get(j).trim())) {
						appLog.info("successfully found file of "+investorList.get(i)+" on folder structure");
						if(itemNameList.get(j).equalsIgnoreCase(StandardFolderFile)) {
							appLog.info("Item Name is matched for folder path  :"+downLoadedFolderPathList.get(i));
							
							
						}else {
							appLog.error("Item Name is not matched for folder path : "+downLoadedFolderPathList.get(i));
							sa.assertTrue(false, "Item Name is not matched for folder path : "+downLoadedFolderPathList.get(i));
						}
						break;
					}else {
						if(j==downLoadedFolderPathList.size()-1) {
							appLog.error("could not find file of "+investorList.get(i));
							sa.assertTrue(false, "could not find file of "+investorList.get(i));
						}
					}
				}
			}
		}else {
			appLog.error("Not file is available in downloaded file folder so cannot verify updated file name from downloaded folder");
			sa.assertTrue(false,"Not file is available in downloaded file folder so cannot verify updated file name from downloaded folder");
		}
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc075_InviteContactFrom10InstitutionsINV(String environment, String mode){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if (fp.clickOnCreatedFund(M15FundName1)) {
				List<String> institutions = getValueBasedOnLabel(excelPath, "Limited Partner", excelLabel.Institutions_Name, 0).subList(0, 10);
				List<String> lpName = getValueBasedOnLabel(excelPath, "Limited Partner", excelLabel.LimitedPartner_Name, 0).subList(0, 10);
				List<String> instilp = new ArrayList<String>();
				for(int i = 0; i < institutions.size(); i++){
					instilp.add(institutions.get(i)+"/"+lpName.get(i));
				}
				if(fp.inviteContact(environment, mode, instilp.get(1), M15C1EmailId, null, FolderType.Standard, "Download", "yes", "No", null, Workspace.InvestorWorkspace, M15C1EmailId)){
					appLog.info(M15CFN1+" "+M15CLN1+" successfully invited from "+institutions.get(1));
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" didnot get the invitation from "+institutions.get(1));
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" didnot get the invitation from "+institutions.get(1));
				}
				if(fp.inviteContact(environment, mode, instilp.get(2), M15C1EmailId, null, FolderType.Standard, "Download", "yes", "No", null, Workspace.InvestorWorkspace, M15C1EmailId)){
					appLog.info(M15CFN1+" "+M15CLN1+" successfully invited from "+instilp.get(2));
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" didnot get the invitation from "+instilp.get(2));
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" didnot get the invitation from "+instilp.get(2));
				}
				if(fp.inviteContact(environment, mode, instilp.get(3), M15C1EmailId, null, FolderType.Standard, "Download", "yes", "No", null, Workspace.InvestorWorkspace, M15C1EmailId)){
					appLog.info(M15CFN1+" "+M15CLN1+" successfully invited from "+instilp.get(3));
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" didnot get the invitation from "+instilp.get(3));
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" didnot get the invitation from "+instilp.get(3));
				}
				if(fp.inviteContact(environment, mode, instilp.get(4), M15C1EmailId, null, FolderType.Standard, "Download", "yes", "No", null, Workspace.InvestorWorkspace, M15C1EmailId)){
					appLog.info(M15CFN1+" "+M15CLN1+" successfully invited from "+instilp.get(4));
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" didnot get the invitation from "+instilp.get(4));
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" didnot get the invitation from "+instilp.get(4));
				}
				if(fp.inviteContact(environment, mode, instilp.get(5), M15C1EmailId, null, FolderType.Standard, "Download", "yes", "No", null, Workspace.InvestorWorkspace, M15C1EmailId)){
					appLog.info(M15CFN1+" "+M15CLN1+" successfully invited from "+instilp.get(5));
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" didnot get the invitation from "+instilp.get(5));
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" didnot get the invitation from "+instilp.get(5));
				}
				if(fp.inviteContact(environment, mode, instilp.get(6), M15C1EmailId, null, FolderType.Standard, "Download", "yes", "No", null, Workspace.InvestorWorkspace, M15C1EmailId)){
					appLog.info(M15CFN1+" "+M15CLN1+" successfully invited from "+instilp.get(6));
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" didnot get the invitation from "+instilp.get(6));
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" didnot get the invitation from "+instilp.get(6));
				}
				if(fp.inviteContact(environment, mode, instilp.get(7), M15C1EmailId, null, FolderType.Standard, "Download", "yes", "No", null, Workspace.InvestorWorkspace, M15C1EmailId)){
					appLog.info(M15CFN1+" "+M15CLN1+" successfully invited from "+instilp.get(7));
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" didnot get the invitation from "+instilp.get(7));
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" didnot get the invitation from "+instilp.get(7));
				}
				if(fp.inviteContact(environment, mode, instilp.get(8), M15C1EmailId, null, FolderType.Standard, "Download", "yes", "No", null, Workspace.InvestorWorkspace, M15C1EmailId)){
					appLog.info(M15CFN1+" "+M15CLN1+" successfully invited from "+instilp.get(8));
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" didnot get the invitation from "+instilp.get(8));
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" didnot get the invitation from "+instilp.get(8));
				}
				if(fp.inviteContact(environment, mode, instilp.get(9), M15C1EmailId, null, FolderType.Standard, "Download", "yes", "No", null, Workspace.InvestorWorkspace, M15C1EmailId)){
					appLog.info(M15CFN1+" "+M15CLN1+" successfully invited from "+instilp.get(9));
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" didnot get the invitation from "+instilp.get(9));
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" didnot get the invitation from "+instilp.get(9));
				}
				if(fp.clickOnTab(TabName.ContactTab)){
					if(cp.clickOnCreatedContact(M15CFN1, M15CLN1, null)){
						switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
						scrollDownThroughWebelement(driver, cp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Workspace view.");
						
						if(fp.verifyFolderPathdummy("", lpName.get(0), null, M15FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)){
							appLog.info(lpName.get(0)+" folder is verified.");
						} else {
							appLog.error(lpName.get(0)+" folder is not present on contact page.");
							sa.assertTrue(false,lpName.get(0)+" folder is not present on contact page.");
						}
						if(fp.verifyFolderPathdummy("", lpName.get(1), null, M15FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)){
							appLog.info(lpName.get(1)+" folder is verified.");
						} else {
							appLog.error(lpName.get(1)+" folder is not present on contact page.");
							sa.assertTrue(false,lpName.get(1)+" folder is not present on contact page.");
						}
						if(fp.verifyFolderPathdummy("", lpName.get(2), null, M15FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)){
							appLog.info(lpName.get(2)+" folder is verified.");
						} else {
							appLog.error(lpName.get(2)+" folder is not present on contact page.");
							sa.assertTrue(false,lpName.get(2)+" folder is not present on contact page.");
						}
						if(fp.verifyFolderPathdummy("", lpName.get(3), null, M15FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)){
							appLog.info(lpName.get(3)+" folder is verified.");
						} else {
							appLog.error(lpName.get(3)+" folder is not present on contact page.");
							sa.assertTrue(false,lpName.get(3)+" folder is not present on contact page.");
						}
						if(fp.verifyFolderPathdummy("", lpName.get(4), null, M15FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)){
							appLog.info(lpName.get(4)+" folder is verified.");
						} else {
							appLog.error(lpName.get(4)+" folder is not present on contact page.");
							sa.assertTrue(false,lpName.get(4)+" folder is not present on contact page.");
						}
						if(fp.verifyFolderPathdummy("", lpName.get(5), null, M15FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)){
							appLog.info(lpName.get(5)+" folder is verified.");
						} else {
							appLog.error(lpName.get(5)+" folder is not present on contact page.");
							sa.assertTrue(false,lpName.get(5)+" folder is not present on contact page.");
						}
						if(fp.verifyFolderPathdummy("", lpName.get(6), null, M15FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)){
							appLog.info(lpName.get(6)+" folder is verified.");
						} else {
							appLog.error(lpName.get(6)+" folder is not present on contact page.");
							sa.assertTrue(false,lpName.get(6)+" folder is not present on contact page.");
						}
						if(fp.verifyFolderPathdummy("", lpName.get(7), null, M15FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)){
							appLog.info(lpName.get(7)+" folder is verified.");
						} else {
							appLog.error(lpName.get(7)+" folder is not present on contact page.");
							sa.assertTrue(false,lpName.get(7)+" folder is not present on contact page.");
						}
						if(fp.verifyFolderPathdummy("", lpName.get(8), null, M15FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)){
							appLog.info(lpName.get(8)+" folder is verified.");
						} else {
							appLog.error(lpName.get(8)+" folder is not present on contact page.");
							sa.assertTrue(false,lpName.get(8)+" folder is not present on contact page.");
						}
						if(fp.verifyFolderPathdummy("", lpName.get(9), null, M15FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)){
							appLog.info(lpName.get(9)+" folder is verified.");
						} else {
							appLog.error(lpName.get(9)+" folder is not present on contact page.");
							sa.assertTrue(false,lpName.get(9)+" folder is not present on contact page.");
						}
					} else {
						appLog.error(M15CFN1+" "+M15CLN1+" is not present in list, So cannot verify structure on contact page.");
						sa.assertTrue(false,M15CFN1+" "+M15CLN1+" is not present in list, So cannot verify structure on contact page.");
					}
				} else {
					appLog.error("Contacts tab cannot be clicked, So cannot verify structure at contact page.");
					sa.assertTrue(false,"Contacts tab cannot be clicked, So cannot verify structure at contact page.");
				}
			} else {
				appLog.error(M15FundName1+" fund is not present in the list, SO cannot continue with the testcase.");
				sa.assertTrue(false,M15FundName1+" fund is not present in the list, SO cannot continue with the testcase.");
			}
		} else {
			appLog.error("Cannot clikc on funds tab, So cannot invite contacts.");
			sa.assertTrue(false,"Cannot clikc on funds tab, So cannot invite contacts.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc076_Generate10000AlertsFromCRMSideINV(String environment, String mode){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String StandardFolderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		boolean flag = true;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName1)){
				List<String> institutions = getValueBasedOnLabel(excelPath, "Limited Partner", excelLabel.Institutions_Name, 0).subList(0, 10);
				List<String> lpName = getValueBasedOnLabel(excelPath, "Limited Partner", excelLabel.LimitedPartner_Name, 0).subList(0, 10);
				List<String> instilp = new ArrayList<String>();
				for(int i = 0; i < institutions.size(); i++){
					instilp.add(institutions.get(i)+"/"+lpName.get(i));
				}
				String institutionOrLPName = createStringOutOfList(instilp);
				if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, StandardFolderPath, institutionOrLPName, "UploadFiles\\BulkFiles\\Standard_MultiInstitutionsAlert", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)){
					if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, StandardFolderPath, institutionOrLPName, "UploadFiles\\BulkFiles\\Standard_MultiInstitutionsAlert10", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)){
						appLog.info("Upload successful.");
					} else {
						appLog.error("Not able to upload file so 10000 alerts cannot be generated.");
						sa.assertTrue(false,"Not able to upload file so 10000 alerts cannot be generated.");
						flag = false;
					}
					if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, StandardFolderPath, institutionOrLPName, "UploadFiles\\BulkFiles\\Standard_MultiInstitutionsAlert2", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)){
						appLog.info("Upload successful.");
					} else {
						appLog.error("Not able to upload file so 10000 alerts cannot be generated.");
						sa.assertTrue(false,"Not able to upload file so 10000 alerts cannot be generated.");
						flag = false;
					}
					if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, StandardFolderPath, institutionOrLPName, "UploadFiles\\BulkFiles\\Standard_MultiInstitutionsAlert3", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)){
						appLog.info("Upload successful.");
					} else {
						appLog.error("Not able to upload file so 10000 alerts cannot be generated.");
						sa.assertTrue(false,"Not able to upload file so 10000 alerts cannot be generated.");
						flag = false;
					}
					if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, StandardFolderPath, institutionOrLPName, "UploadFiles\\BulkFiles\\Standard_MultiInstitutionsAlert4", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)){
						appLog.info("Upload successful.");
					} else {
						appLog.error("Not able to upload file so 10000 alerts cannot be generated.");
						sa.assertTrue(false,"Not able to upload file so 10000 alerts cannot be generated.");
						flag = false;
					}
					if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, StandardFolderPath, institutionOrLPName, "UploadFiles\\BulkFiles\\Standard_MultiInstitutionsAlert5", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)){
						appLog.info("Upload successful.");
					} else {
						appLog.error("Not able to upload file so 10000 alerts cannot be generated.");
						sa.assertTrue(false,"Not able to upload file so 10000 alerts cannot be generated.");
						flag = false;
					}
					if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, StandardFolderPath, institutionOrLPName, "UploadFiles\\BulkFiles\\Standard_MultiInstitutionsAler6t", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)){
						appLog.info("Upload successful.");
					} else {
						appLog.error("Not able to upload file so 10000 alerts cannot be generated.");
						sa.assertTrue(false,"Not able to upload file so 10000 alerts cannot be generated.");
						flag = false;
					}
					if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, StandardFolderPath, institutionOrLPName, "UploadFiles\\BulkFiles\\Standard_MultiInstitutionsAlert7", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)){
						appLog.info("Upload successful.");
					} else {
						appLog.error("Not able to upload file so 10000 alerts cannot be generated.");
						sa.assertTrue(false,"Not able to upload file so 10000 alerts cannot be generated.");
						flag = false;
					}
					if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, StandardFolderPath, institutionOrLPName, "UploadFiles\\BulkFiles\\Standard_MultiInstitutionsAlert8", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)){
						appLog.info("Upload successful.");
					} else {
						appLog.error("Not able to upload file so 10000 alerts cannot be generated.");
						sa.assertTrue(false,"Not able to upload file so 10000 alerts cannot be generated.");
						flag = false;
					}
					if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, StandardFolderPath, institutionOrLPName, "UploadFiles\\BulkFiles\\Standard_MultiInstitutionsAlert9", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)){
						appLog.info("Upload successful.");
					} else {
						appLog.error("Not able to upload file so 10000 alerts cannot be generated.");
						sa.assertTrue(false,"Not able to upload file so 10000 alerts cannot be generated.");
						flag = false;
					}
				} else {
					appLog.error("Not able to upload file in folder, So cannot continue with the tc.");
					sa.assertTrue(false,"Not able to upload file in folder, So cannot continue with the tc.");
					flag = false;
				}
			} else {
				appLog.error(M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
				sa.assertTrue(false,M15FundName1+" fund is not present in the list, SO cannot continue with the tc.");
				flag = false;
			}
		} else {
			appLog.error("Funds Tab cannot be clicked, So cannot continue with the testcase.");
			sa.assertTrue(false,"Funds Tab cannot be clicked, So cannot continue with the testcase.");
			flag = false;
		}
		if(flag){
			driver.close();
			config(browserToLaunch);
			lp = new LoginPageBusinessLayer(driver);
			InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
			if(click(driver, ifp.getAllDocumentsTab(30), "All documents tab", action.BOOLEAN)){
				if(ifp.getAllDocumentCount(30)!=null){
					int recordCount = getIntegerFromString(getText(driver, ifp.getAllDocumentCount(30), "", action.BOOLEAN)).get(0);
					if(recordCount>=1000){
						appLog.info("Record count is verified.");
					} else {
						appLog.error("Record Count on all document is not verified. expected: 1000+\tActual: "+recordCount);
						sa.assertTrue(false,"Record Count on all document is not verified. expected: 1000+\tActual: "+recordCount);
					}
				} else {
					appLog.error("Record count is not visible on All document page, So cannot verify count.");
					sa.assertTrue(false,"Record count is not visible on All document page, So cannot verify count.");
				}
				if(ifp.getShowMoreLink(30)!=null){
					appLog.info("Show more link is present is verified.");
				} else {
					appLog.error("Show more link is not visible.");
					sa.assertTrue(false,"Show more link is not visible.");
				}
			} else {
				appLog.error("Cannot click on All Documents tab, So cannot veirfy All documents.");
				sa.assertTrue(false,"Cannot click on All Documents tab, So cannot veirfy All documents.");
			}
			if(click(driver, ifp.getRecentActivitiesTab(30), "Recent Activities tab", action.BOOLEAN)){
				WebElement ele = FindElement(driver, "//*[text()='Records: 1999+']", "Record count", action.BOOLEAN, 30);
				if(ele!=null){
					appLog.info("Record count is verified successfully.");
				} else {
					appLog.error("Record count is not verified.");
					sa.assertTrue(false,"Record count is not verified.");
				}
				ele = FindElement(driver, "//a[@title='Show All']", "Show all link", action.BOOLEAN, 30);
				if(ele!=null){
					appLog.info("Show All link is verified.");
				} else {
					appLog.error("Show all link is not present on the page.");
					sa.assertTrue(false,"Show all link is not present on the page.");
				}
			} else {
				appLog.error("Cannot click on recent activity tab, So cannot veirfy recent activities.");
				sa.assertTrue(false,"Cannot click on recent activity tab, So cannot veirfy recent activities.");
			}
			if(ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)){
				if(fp.verifyFolderPathdummy(StandardFolderPath, M15LP1, null, null, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 30)){
					WebElement ele = FindElement(driver, "//span[@id='gridDivRecords']", "Record Count", action.BOOLEAN, 30);
					if(ele!=null){
						int recordCount = Integer.parseInt(getText(driver, ele, "record Cound", action.BOOLEAN));
						if(recordCount>=1000){
							appLog.info("record count is verified on Current workspace.");
						} else {
							appLog.error("Record count is not verified. Expected: 1000+\tActual: "+recordCount);
							sa.assertTrue(false,"Record count is not verified. Expected: 1000+\tActual: "+recordCount);
						}
					} else {
						appLog.error("Report count is visible on workspace page.");
						sa.assertTrue(false,"Report count is visible on workspace page.");
					}
				} else {
					appLog.error(StandardFolderPath+" Folder structure is not verified, So cannot check files in folder.");
					sa.assertTrue(false,StandardFolderPath+" Folder structure is not verified, So cannot check files in folder.");
				}
			} else {
				appLog.error("Current investment tab cannot be clciked, So cannot verify files in folder.");
				sa.assertTrue(false,"Current investment tab cannot be clciked, So cannot verify files in folder.");
			}
			
		}
		switchToDefaultContent(driver);
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc077_Create10000AlertFromInvestorSideINV(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		boolean flag = false;
		String sqlQuery="select id from contact where email ='"+M15C1EmailId+"'";
		String fundIdSqlQuery="select id,name from fund__c where fund__c.CreatedBy.LastName = '"+CRMUser1LastName+"' and name='"+M15FundName1+"'";
		lp.CRMLogin(HubUserName, HubPassword);
		if(sendKeys(driver, lp.getGlobalSearch(30), M15C1EmailId, "Global search box", action.BOOLEAN)){
			if(click(driver, lp.getGlobalSearchButton(30), "Global search button", action.BOOLEAN)){
				String contactID = lp.getContactId(M15CFN1, M15CLN1);
				if(contactID!=null){
					if(sendKeys(driver, lp.getGlobalSearch(30), M15FundName1, "Global search box", action.BOOLEAN)){
						if(click(driver, lp.getGlobalSearchButton(30), "Global search button", action.BOOLEAN)){
							String fundID = lp.getFundId(M15FundName1);
							if(fundID!=null){
								if(lp.devConsole()){
									String parentWin = switchOnWindow(driver);
									if(parentWin!=null){
										SoftAssert executeQuery=lp.executeQueryOnDevConsole(sqlQuery);
										sa.combineAssertions(executeQuery);
										String ContactId=lp.getContactIdFromDevConsole(M15C1EmailId,null,null);
										SoftAssert FundsqlQueryExecute=lp.executeQueryOnDevConsole(fundIdSqlQuery);
										sa.combineAssertions(FundsqlQueryExecute);
										String FundId=lp.getContactIdFromDevConsole(null,M15FundName1,CRMUser1LastName);
										if(ContactId!=null && FundId!=null) {
										WebElement ele = FindElement(driver, "//span[@id='debugMenuEntry-btnInnerEl']", "file tab", action.BOOLEAN, 30);
										if(click(driver, ele, "Debug link", action.BOOLEAN)){
											if(click(driver, FindElement(driver, "(//div[@class='menuEntryLeft'])[1]", "New console link", action.BOOLEAN, 30), "New console link", action.BOOLEAN)){
												ele = FindElement(driver, "//div[@class='CodeMirror-code']", "Code Editor", action.BOOLEAN, 30);
												if(ele!=null){
													click(driver, ele, "editor", action.BOOLEAN);
													ele = FindElement(driver, "//div[@class='CodeMirror-code']", "Code Editor", action.BOOLEAN, 30);
													Robot rob;
													try {
														rob = new Robot();
														rob.keyPress(KeyEvent.VK_CONTROL);
														ThreadSleep(1000);
														rob.keyPress(KeyEvent.VK_A);
														ThreadSleep(1000);
														rob.keyRelease(KeyEvent.VK_CONTROL);
														ThreadSleep(1000);
														rob.keyRelease(KeyEvent.VK_A);
														ThreadSleep(1000);
														String code = "List<Alert__c> finalAlert = new List<Alert__c>();\nfor(integer i =0 ; i<10000 ; i++) {\nAlert__c dumALert = new Alert__c();\ndumALert.Box_File_Id__c = '3705217';\ndumALert.Contact__c = '"+ContactId+"';\ndumALert.Document_Name__c =  'MA3_'+i+'.pdf';\ndumALert.Fund__c = '"+FundId+"';\ndumALert.Workspace__c = 'Current Investment';\ndumALert.BOX_Investor_Folder_ID__c = '572825';\ndumALert.Alert_Type_New__c = 'Document Viewed';\nfinalAlert.add(dumALert);\n}\nINSERT finalAlert;";
														setClipboardData(code);
														ThreadSleep(1000);
														rob.keyPress(KeyEvent.VK_CONTROL);
														ThreadSleep(1000);
														rob.keyPress(KeyEvent.VK_V);
														ThreadSleep(1000);
														rob.keyRelease(KeyEvent.VK_CONTROL);
														ThreadSleep(1000);
														rob.keyRelease(KeyEvent.VK_V);
													} catch (AWTException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
													if(click(driver, lp.getExecuteButton1(30), "execute Button", action.BOOLEAN)){
														ThreadSleep(120000); 
														appLog.info("Script successfully executed.");
														flag = true;
													} else {
														appLog.error("Execute button canot be clicked, So cannot continue with the tc.");
														sa.assertTrue(false,"Execute button canot be clicked, So cannot continue with the tc.");
													}
												} else {
													appLog.error("Code editor is not opening, So cannot continue with the ts.");
													sa.assertTrue(false,"Code editor is not opening, So cannot continue with the ts.");
												}
											} else {
												appLog.error("New console link cannot be clicked, SO cannot continue with the tc.");
												sa.assertTrue(false,"New console link cannot be clicked, SO cannot continue with the tc.");
											}
										} else {
											appLog.error("Not able to click on debug link.");
											sa.assertTrue(false,"Not able to click on debug link.");
										}
										}else {
											appLog.error("Not able to get Contact and fund id from dev console editor of contact email : "+M15C1EmailId);
											sa.assertTrue(false, "Not able to get Contact and fund id from dev console editor of contact email : "+M15C1EmailId);
										}
										driver.close();
										driver.switchTo().window(parentWin);
									} else {
										appLog.error("Dev console window is not opening after clicking on dev console link.");
										sa.assertTrue(false,"Dev console window is not opening after clicking on dev console link.");
									}
								} else {
									appLog.error("Dev Console link cannot be clicked, So cannot continue with the tc.");
									sa.assertTrue(false,"Dev Console link cannot be clicked, So cannot continue with the tc.");
								}
							} else {
								appLog.error(M15FundName1+" Fund Link is not available to get fund ID, So cannot continue with the testcase.");
								sa.assertTrue(false,M15FundName1+" Fund Link is not available to get fund ID, So cannot continue with the testcase.");
							}
						} else {
							appLog.error("Global search button cannot be clicked for fund search, So cannot continue with the testcase.");
							sa.assertTrue(false,"Global search button cannot be clicked for fund search, So cannot continue with the testcase.");
						}
					} else {
						appLog.error("Global search is not present, SO cannot continue with the testcase.");
						sa.assertTrue(false,"Global search is not present, SO cannot continue with the testcase.");
					}
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" Contact Link is not available to get contact ID, So cannot continue with the testcase.");
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" Contact Link is not available to get contact ID, So cannot continue with the testcase.");
				}
			} else {
				appLog.error("Global search button cannot be clicked, So cannot continue with the testcase.");
				sa.assertTrue(false,"Global search button cannot be clicked, So cannot continue with the testcase.");
			}
		} else {
			appLog.error("Global search is not present, SO cannot continue with the testcase.");
			sa.assertTrue(false,"Global search is not present, SO cannot continue with the testcase.");
		}
		lp.CRMlogout();
		if(flag){
			FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
			lp.CRMLogin(CRMUser1EmailID, adminPassword);
			switchToFrame(driver, 30, lp.getFrame( PageName.HomePage, 30));
			if(lp.getRecordCountOnHomePage(30)!=null){
				ThreadSleep(5000);
				String text = getText(driver, lp.getRecordCountOnHomePage(30), "record count", action.BOOLEAN);
				if(text.trim().equalsIgnoreCase("Records: 1999+")){
					appLog.info("Record Count is verified.");
				} else {
					appLog.error("Record Count is not verified. Expected: Records: 1999+\tActual: "+text);
					sa.assertTrue(false,"Record Count is not verified. Expected: Records: 1999+\tActual: "+text);
				}
				if(click(driver, lp.getShowAllLink(null,30), "Show all link", action.BOOLEAN)){
					ThreadSleep(5000);
					appLog.info("Show All link is verified.");
					if(lp.getRecordCountOnHomePage(30)!=null){
						text = getText(driver, lp.getRecordCountOnHomePage(30), "record count", action.BOOLEAN);
						if(text.trim().equalsIgnoreCase("Records: 10000")){
							appLog.info("Record Count is verified.");
						} else {
							appLog.error("Record Count is not verified. Expected: Records: 10000\tActual: "+text);
							sa.assertTrue(false,"Record Count is not verified. Expected: Records: 10000\tActual: "+text);
						}
					} else {
						appLog.error("Record is not showing on home page after clicking on show all link.");
						sa.assertTrue(false,"Record is not showing on home page after clicking on show all link.");
					}
				} else {
					appLog.error("Show All link is not verified.");
					sa.assertTrue(false,"Show All link is not verified.");
				}
				
			} else {
				appLog.error("Record is not showing on home page.");
				sa.assertTrue(false,"Record is not showing on home page.");
			}
			switchToDefaultContent(driver);
			if(lp.clickOnTab(TabName.FundsTab)){
				if(fp.clickOnCreatedFund(M15FundName1)){
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspaec view");
					if(click(driver, fp.getAlertHistoryLink(Workspace.InvestorWorkspace, PageName.FundsPage, 30), "Alert history link", action.BOOLEAN)){
						ThreadSleep(5000);
						if(fp.getRecordCountAlertHistoryPopUp(Workspace.InvestorWorkspace, 20)!=null){
							String text = getText(driver, fp.getRecordCountAlertHistoryPopUp(Workspace.InvestorWorkspace, 20), "record Count", action.BOOLEAN);
							if(text.trim().equalsIgnoreCase("Records: 1999+")){
								appLog.info("Record count is verified.");
							} else {
								appLog.error("Record count is not verified on Investor workspace alert history pop up. Expected: Records: 1999+\tActual: "+text);
								sa.assertTrue(false,"Record count is not verified on Investor workspace alert history pop up. Expected: Records: 1999+\tActual: "+text);
							}
							if(click(driver, lp.getShowAllLink(Workspace.InvestorWorkspace, 30), "Show All Link", action.BOOLEAN)){
								ThreadSleep(5000);
								appLog.info("Show All link is verified.");
								if(lp.getRecordCountAlertHistoryPopUp(Workspace.InvestorWorkspace, 20)!=null){
									text = getText(driver, lp.getRecordCountAlertHistoryPopUp(Workspace.InvestorWorkspace, 20), "record count", action.BOOLEAN);
									if(text.trim().equalsIgnoreCase("Records: 10000")){
										appLog.info("Record Count is verified.");
									} else {
										appLog.error("Record Count is not verified on Investor workspace alert history pop up. Expected: Records: 10000\tActual: "+text);
										sa.assertTrue(false,"Record Count is not verified on Investor workspace alert history pop up. Expected: Records: 10000\tActual: "+text);
									}
								} else {
									appLog.error("Record is not showing on Investor workspace alert history pop up after clicking on show all link.");
									sa.assertTrue(false,"Record is not showing on Investor workspace alert history pop up after clicking on show all link.");
								}
							} else {
								appLog.error("Show All link is not verified on Investor workspace alert history pop up.");
								sa.assertTrue(false,"Show All link is not verified on Investor workspace alert history pop up.");
							}
						} else {
							appLog.error("Record Count is not present on Investor workspace alert history pop up.");
							sa.assertTrue(false,"Record Count is not present on Investor workspace alert history pop up.");
						}
						
					} else {
						appLog.error("Alert history link cannot be clicked, So cannot verify Record Count on alert history page.");
						sa.assertTrue(false,"Alert history link cannot be clicked, So cannot verify Record Count on alert history page.");
					}
					switchToDefaultContent(driver);
				} else {
					appLog.error(M15FundName1+" fund is not present in the list, So cannot verify record count on Investor workspace.");
					sa.assertTrue(false,M15FundName1+" fund is not present in the list, So cannot verify record count on Investor workspace.");
				}
			} else {
				appLog.error("Funds Tab cannot be clicked, So cannot verify record count on funds Page Investor Workspace.");
				sa.assertTrue(false,"Funds Tab cannot be clicked, So cannot verify record count on funds Page Investor Workspace.");
			}
			ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
			if (lp.clickOnTab(TabName.ContactTab)) {
				if(cp.clickOnCreatedContact(M15CFN1, M15CLN1, null)){
					switchToFrame(driver, 30, cp.getFrame( PageName.ContactsPage, 30));
					if(click(driver, cp.getAlertHistoryLink(Workspace.InvestorWorkspace, PageName.ContactsPage, 30), "Alert history link", action.BOOLEAN)){
						ThreadSleep(5000);
						if(fp.getRecordCountAlertHistoryPopUp(Workspace.InvestorWorkspace, 20)!=null){
							String text = getText(driver, fp.getRecordCountAlertHistoryPopUp(Workspace.InvestorWorkspace, 20), "record Count", action.BOOLEAN);
							if(text.trim().equalsIgnoreCase("Records: 1999+")){
								appLog.info("Record count is verified.");
							} else {
								appLog.error("Record count is not verified on Investor workspace alert history pop up contact page. Expected: Records: 1999+\tActual: "+text);
								sa.assertTrue(false,"Record count is not verified on Investor workspace alert history pop up contact page. Expected: Records: 1999+\tActual: "+text);
							}
							if(click(driver, lp.getShowAllLink(Workspace.InvestorWorkspace, 30), "Show All Link", action.BOOLEAN)){
								ThreadSleep(5000);
								appLog.info("Show All link is verified.");
								if(lp.getRecordCountAlertHistoryPopUp(Workspace.InvestorWorkspace, 20)!=null){
									text = getText(driver, lp.getRecordCountAlertHistoryPopUp(Workspace.InvestorWorkspace, 20), "record count", action.BOOLEAN);
									if(text.trim().equalsIgnoreCase("Records: 10000")){
										appLog.info("Record Count is verified.");
									} else {
										appLog.error("Record Count is not verified on Investor workspace alert history pop up contact page. Expected: Records: 10000\tActual: "+text);
										sa.assertTrue(false,"Record Count is not verified on Investor workspace alert history pop up contact page. Expected: Records: 10000\tActual: "+text);
									}
								} else {
									appLog.error("Record is not showing on Investor workspace alert history pop up contact page after clicking on show all link.");
									sa.assertTrue(false,"Record is not showing on Investor workspace alert history pop up contact page after clicking on show all link.");
								}
							} else {
								appLog.error("Show All link is not verified on Investor workspace alert history pop up contact page.");
								sa.assertTrue(false,"Show All link is not verified on Investor workspace alert history pop up contact page.");
							}
						} else {
							appLog.error("Record Count is not present on Investor workspace alert history pop up contact page.");
							sa.assertTrue(false,"Record Count is not present on Investor workspace alert history pop up contact page.");
						}
					} else {
						appLog.error("Alert History link cannot be clicked, So cannot verify record count on contact page.");
						sa.assertTrue(false,"Alert History link cannot be clicked, So cannot verify record count on contact page.");
					}
					switchToDefaultContent(driver);
					lp.CRMlogout();
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" Contact is not present in the list, So cannot verify record count on Investor workspace.");
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" Contact is not present in the list, So cannot verify record count on Investor workspace.");
				}
			} else {
				appLog.error("Contacts Tab cannot be clicked, So cannot verify record count on contact Page Investor Workspace.");
				sa.assertTrue(false,"Contacts Tab cannot be clicked, So cannot verify record count on contact Page Investor Workspace.");
			} 
		} else {
			appLog.error("Not able to execute script, So cannot verify alerts at CRM side.");
			sa.assertTrue(false,"Not able to execute script, So cannot verify alerts at CRM side.");
		}
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc078_InviteContactFromSharedFolderINV(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderPath[] = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath).split("<break>");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		boolean flag = false;
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName2)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view.");
				if(fp.verifyFolderPathdummy(folderPath[0], null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)){
					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "Contact access icon", action.BOOLEAN)){
						if(fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)){
							if(fp.selectDeselectContactFromContactAccess(null, SelectDeselect.Select, AllOr1By1.All, M15Institution1, Workspace.InvestorWorkspace, 30).isEmpty()){
								if(click(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30), "Add selected contact button", action.BOOLEAN)){
									if(isAlertPresent(driver)){
										String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
										switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
										if(msg.equalsIgnoreCase(FundsPageErrorMessage.CannotAddMoreThan200ContactErrorMesssage.toString())){
											appLog.info("Message is successfully verified.");
										} else {
											appLog.error(FundsPageErrorMessage.CannotAddMoreThan200ContactErrorMesssage.toString()+" error message is not verified. Expected: "+FundsPageErrorMessage.CannotAddMoreThan200ContactErrorMesssage+"\tActual: "+msg);
											sa.assertTrue(false,"Error message is not verified. Expected: "+FundsPageErrorMessage.CannotAddMoreThan200ContactErrorMesssage+"\tActual: "+msg);
										}
										List<String> contact = new ArrayList<String>();
										contact.add(M15Contact201EmailId);
										if(fp.selectDeselectContactFromContactAccess(contact, SelectDeselect.Deselect, AllOr1By1.OneByOne, null, Workspace.InvestorWorkspace, 30).isEmpty()){
											appLog.info("Successfully deselected contact '"+M15Contact201EmailId+"'.");
											if(click(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30), "Add selected contact button", action.BOOLEAN)){
												if(fp.clickOnContactAccessApplyButton(null, CRMUser1EmailID, Workspace.InvestorWorkspace, 30)){
													appLog.info("Successfully provided access.");
													flag = true;
												} else {
													appLog.error("Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution1+"'.");
													sa.assertTrue(false,"Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution1+"'.");
												}
											} else {
												appLog.error("Not able to provide access to contact '"+M15Contact201EmailId+"', So cannot continue to give permission to contacts of institution '"+M15Institution1+"'.");
												sa.assertTrue(false,"Not able to provide access to contact '"+M15Contact201EmailId+"', So cannot continue to give permission to contacts of institution '"+M15Institution1+"'.");
											}
										} else {
											appLog.error("Not able to deselect contact '"+M15Contact201EmailId+"'.");
											sa.assertTrue(false,"Not able to deselect contact '"+M15Contact201EmailId+"'.");
										}
									} else {
										appLog.error(FundsPageErrorMessage.CannotAddMoreThan200ContactErrorMesssage+" error message is not poping up.");
										sa.assertTrue(false,FundsPageErrorMessage.CannotAddMoreThan200ContactErrorMesssage+" error message is not poping up.");
									}
								} else {
									appLog.error("Add selected contacts button cannot be clicked, So cannot provide access from folder '"+folderPath[0]+"'.");
									sa.assertTrue(false,"Add selected contacts button cannot be clicked, So cannot provide access from folder '"+folderPath[0]+"'.");
								}
							} else {
								appLog.error("Contact of institution '"+M15Institution1+"' cannot be selected, So cannot provide access from folder '"+folderPath[0]+"'.");
								sa.assertTrue(false,"Contact of institution '"+M15Institution1+"' cannot be selected, So cannot provide access from folder '"+folderPath[0]+"'.");
							}
							if(flag){
								if(fp.verifyFolderPathdummy(folderPath[1], null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)){
									if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "Contact access icon", action.BOOLEAN)){
										if(fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)){
											if(fp.selectDeselectContactFromContactAccess(null, SelectDeselect.Select, AllOr1By1.All, M15Institution2, Workspace.InvestorWorkspace, 30).isEmpty()){
												if(click(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30), "Add selected contact button", action.BOOLEAN)){
													if(fp.clickOnContactAccessApplyButton(null, CRMUser1EmailID, Workspace.InvestorWorkspace, 30)){
														appLog.info("Successfully provided access.");
														flag = true;
													} else {
														appLog.error("Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution2+"'.");
														sa.assertTrue(false,"Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution2+"'.");
													}
												} else {
													appLog.error("Add selected contacts button cannot be clicked, So cannot provide access from folder '"+folderPath[1]+"'.");
													sa.assertTrue(false,"Add selected contacts button cannot be clicked, So cannot provide access from folder '"+folderPath[1]+"'.");
												}
											} else {
												appLog.error("Contact of institution '"+M15Institution2+"' cannot be selected, So cannot provide access from folder '"+folderPath[1]+"'.");
												sa.assertTrue(false,"Contact of institution '"+M15Institution2+"' cannot be selected, So cannot provide access from folder '"+folderPath[1]+"'.");
											}
											if(flag){
												if(fp.verifyFolderPathdummy(folderPath[2], null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)){
													if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "Contact access icon", action.BOOLEAN)){
														if(fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)){
															if(fp.selectDeselectContactFromContactAccess(null, SelectDeselect.Select, AllOr1By1.All, M15Institution3, Workspace.InvestorWorkspace, 30).isEmpty()){
																if(click(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30), "Add selected contact button", action.BOOLEAN)){
																	if(fp.clickOnContactAccessApplyButton(null, CRMUser1EmailID, Workspace.InvestorWorkspace, 30)){
																		appLog.info("Successfully provided access.");
																		flag = true;
																	} else {
																		appLog.error("Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution3+"'.");
																		sa.assertTrue(false,"Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution3+"'.");
																	}
																} else {
																	appLog.error("Add selected contacts button cannot be clicked, So cannot provide access from folder '"+folderPath[1]+"'.");
																	sa.assertTrue(false,"Add selected contacts button cannot be clicked, So cannot provide access from folder '"+folderPath[1]+"'.");
																}
															} else {
																appLog.error("Contact of institution '"+M15Institution3+"' cannot be selected, So cannot provide access from folder '"+folderPath[1]+"'.");
																sa.assertTrue(false,"Contact of institution '"+M15Institution3+"' cannot be selected, So cannot provide access from folder '"+folderPath[1]+"'.");
															}
															if(flag){
																if(fp.verifyFolderPathdummy(folderPath[3], null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)){
																	if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "Contact access icon", action.BOOLEAN)){
																		if(fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)){
																			if(fp.selectDeselectContactFromContactAccess(null, SelectDeselect.Select, AllOr1By1.All, M15Institution4, Workspace.InvestorWorkspace, 30).isEmpty()){
																				if(click(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30), "Add selected contact button", action.BOOLEAN)){
																					if(fp.clickOnContactAccessApplyButton(null, CRMUser1EmailID, Workspace.InvestorWorkspace, 30)){
																						appLog.info("Successfully provided access.");
																						flag = true;
																					} else {
																						appLog.error("Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution4+"'.");
																						sa.assertTrue(false,"Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution4+"'.");
																					}
																				} else {
																					appLog.error("Add selected contacts button cannot be clicked, So cannot provide access from folder '"+folderPath[1]+"'.");
																					sa.assertTrue(false,"Add selected contacts button cannot be clicked, So cannot provide access from folder '"+folderPath[1]+"'.");
																				}
																			} else {
																				appLog.error("Contact of institution '"+M15Institution4+"' cannot be selected, So cannot provide access from folder '"+folderPath[1]+"'.");
																				sa.assertTrue(false,"Contact of institution '"+M15Institution4+"' cannot be selected, So cannot provide access from folder '"+folderPath[1]+"'.");
																			}
																			if(flag){
																				if(fp.verifyFolderPathdummy(folderPath[4], null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)){
																					if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "Contact access icon", action.BOOLEAN)){
																						if(fp.verifyContactAccessExpandCollapse(Workspace.InvestorWorkspace)){
																							if(fp.selectDeselectContactFromContactAccess(null, SelectDeselect.Select, AllOr1By1.All, M15Institution5, Workspace.InvestorWorkspace, 30).isEmpty()){
																								if(click(driver, fp.getaddselectContactBtn(Workspace.InvestorWorkspace, 30), "Add selected contact button", action.BOOLEAN)){
																									if(fp.clickOnContactAccessApplyButton(null, CRMUser1EmailID, Workspace.InvestorWorkspace, 30)){
																										appLog.info("Successfully provided access.");
																										flag = true;
																									} else {
																										appLog.error("Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution5+"'.");
																										sa.assertTrue(false,"Apply button cannot be clicked, so cannot provide access to contacts of institution '"+M15Institution5+"'.");
																									}
																								} else {
																									appLog.error("Add selected contacts button cannot be clicked, So cannot provide access from folder '"+folderPath[1]+"'.");
																									sa.assertTrue(false,"Add selected contacts button cannot be clicked, So cannot provide access from folder '"+folderPath[1]+"'.");
																								}
																							} else {
																								appLog.error("Contact of institution '"+M15Institution5+"' cannot be selected, So cannot provide access from folder '"+folderPath[1]+"'.");
																								sa.assertTrue(false,"Contact of institution '"+M15Institution5+"' cannot be selected, So cannot provide access from folder '"+folderPath[1]+"'.");
																							}
																							//if left start here with if condition
																						} else {
																							appLog.error("Contact access expand icon cannot be clicked, So cannot continue with provide access from folder '"+folderPath[4]+"'");
																							sa.assertTrue(false,"Contact access expand icon cannot be clicked, So cannot continue with provide access from folder '"+folderPath[4]+"'");
																						}
																					} else {
																						appLog.error("Contact aaccess tab cannot be clicked, So cannot continue with provide access from folder '"+folderPath[4]+"'");
																						sa.assertTrue(false,"Contact aaccess tab cannot be clicked, So cannot continue with provide access from folder '"+folderPath[4]+"'");
																					}
																				} else {
																					appLog.error(folderPath[4]+" folder path is not verified, So cannot continue with tc.");
																					sa.assertTrue(false,folderPath[4]+" folder path is not verified, So cannot continue with tc.");
																				}
																			} else {
																				appLog.error("Contact access to contacts of institution '"+M15Institution4+"' is not successful, So cannot continue with the tc.");
																				sa.assertTrue(false,"Contact access to contacts of institution '"+M15Institution4+"' is not successful, So cannot continue with the tc.");
																			}
																		} else {
																			appLog.error("Contact access expand icon cannot be clicked, So cannot continue with provide access from folder '"+folderPath[3]+"'");
																			sa.assertTrue(false,"Contact access expand icon cannot be clicked, So cannot continue with provide access from folder '"+folderPath[3]+"'");
																		}
																	} else {
																		appLog.error("Contact aaccess tab cannot be clicked, So cannot continue with provide access from folder '"+folderPath[3]+"'");
																		sa.assertTrue(false,"Contact aaccess tab cannot be clicked, So cannot continue with provide access from folder '"+folderPath[3]+"'");
																	}
																} else {
																	appLog.error(folderPath[3]+" folder path is not verified, So cannot continue with tc.");
																	sa.assertTrue(false,folderPath[3]+" folder path is not verified, So cannot continue with tc.");
																}
															} else {
																appLog.error("Contact access to contacts of institution '"+M15Institution3+"' is not successful, So cannot continue with the tc.");
																sa.assertTrue(false,"Contact access to contacts of institution '"+M15Institution3+"' is not successful, So cannot continue with the tc.");
															}
														} else {
															appLog.error("Contact access expand icon cannot be clicked, So cannot continue with provide access from folder '"+folderPath[2]+"'");
															sa.assertTrue(false,"Contact access expand icon cannot be clicked, So cannot continue with provide access from folder '"+folderPath[2]+"'");
														}
													} else {
														appLog.error("Contact aaccess tab cannot be clicked, So cannot continue with provide access from folder '"+folderPath[2]+"'");
														sa.assertTrue(false,"Contact aaccess tab cannot be clicked, So cannot continue with provide access from folder '"+folderPath[2]+"'");
													}
												} else {
													appLog.error(folderPath[2]+" folder path is not verified, So cannot continue with tc.");
													sa.assertTrue(false,folderPath[2]+" folder path is not verified, So cannot continue with tc.");
												}
											} else {
												appLog.error("Contact access to contacts of institution '"+M15Institution2+"' is not successful, So cannot continue with the tc.");
												sa.assertTrue(false,"Contact access to contacts of institution '"+M15Institution2+"' is not successful, So cannot continue with the tc.");
											}
										} else {
											appLog.error("Contact access expand icon cannot be clicked, So cannot continue with provide access from folder '"+folderPath[1]+"'");
											sa.assertTrue(false,"Contact access expand icon cannot be clicked, So cannot continue with provide access from folder '"+folderPath[1]+"'");
										}
									} else {
										appLog.error("Contact aaccess tab cannot be clicked, So cannot continue with provide access from folder '"+folderPath[1]+"'");
										sa.assertTrue(false,"Contact aaccess tab cannot be clicked, So cannot continue with provide access from folder '"+folderPath[1]+"'");
									}
								} else {
									appLog.error(folderPath[1]+" folder path is not verified, So cannot continue with tc.");
									sa.assertTrue(false,folderPath[1]+" folder path is not verified, So cannot continue with tc.");
								}
							} else {
								appLog.error("Contact access to contacts of institution '"+M15Institution1+"' is not successful, So cannot continue with the tc.");
								sa.assertTrue(false,"Contact access to contacts of institution '"+M15Institution1+"' is not successful, So cannot continue with the tc.");
							}
						} else {
							appLog.error("Contact access expand icon cannot be clicked, So cannot continue with provide access from folder '"+folderPath[0]+"'");
							sa.assertTrue(false,"Contact access expand icon cannot be clicked, So cannot continue with provide access from folder '"+folderPath[0]+"'");
						}
					} else {
						appLog.error("Contact aaccess tab cannot be clicked, So cannot continue with provide access from folder '"+folderPath[0]+"'");
						sa.assertTrue(false,"Contact aaccess tab cannot be clicked, So cannot continue with provide access from folder '"+folderPath[0]+"'");
					}
				} else {
					appLog.error(folderPath[0]+" folder path is not verified, So cannot continue with tc.");
					sa.assertTrue(false,folderPath[0]+" folder path is not verified, So cannot continue with tc.");
				}
			} else {
				appLog.error(M15FundName2+" fund is not present in list, So cannot continue with the tc.");
				sa.assertTrue(false,M15FundName2+" fund is not present in list, So cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot invite contact from shared folder.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot invite contact from shared folder.");
		}
		
		if(flag){
			refresh(driver);
			switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
			scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view.");
			if(fp.verifyFolderPathdummy(folderPath[4], null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)){
				if(click(driver, fp.getContactAccessIcon(Workspace.InvestorWorkspace, 30), "Contact access icon", action.BOOLEAN)){
					List <WebElement> ele = FindElements(driver, "//span[contains(@id,'grid11_DealDetailBWINV-cell-3')]/a", "Selected contact email ids");
					if(ele.size()==1000){
						appLog.info("All the contacts have successfully been provided access.");
					} else {
						appLog.error("All the contacts who have access are not present in the selected grid. Expected: 1000\tActual: "+ele.size());
						sa.assertTrue(false,"All the contacts who have access are not present in the selected grid. Expected: 1000\tActual: "+ele.size());
					}
				} else {
					appLog.error("Cannot click on contact access icon, SO cannot verify contacts in selected contact list.");
					sa.assertTrue(false,"Cannot click on contact access icon, SO cannot verify contacts in selected contact list.");
				}
			} else {
				appLog.error(folderPath[4]+" Folder path is not verified, So cannot verify contacts in selected contact list.");
				sa.assertTrue(false,folderPath[4]+" Folder path is not verified, So cannot verify contacts in selected contact list.");
			}
		} else {
			appLog.error("All access are not provided, So cannot verify contacts in selected contacts list.");
			sa.assertTrue(false,"All access are not provided, So cannot verify contacts in selected contacts list.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc079_InviteContactFromDifferentLevelINV(String environment, String mode){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String[] folderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath).split("<break>");
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName2)){
//				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
//				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view.");
				if(fp.inviteContact(environment, mode, null, M15C1EmailId, folderPath[0], FolderType.Shared, "Download", "yes", "No", null, Workspace.InvestorWorkspace, M15C1EmailId)){
					appLog.info(M15CFN1+" "+M15CLN1+" successfully invited from "+folderPath[0]);
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" didnot get the invitation from "+folderPath[0]);
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" didnot get the invitation from "+folderPath[0]);
				}
				if(fp.inviteContact(environment, mode, null, M15C1EmailId, folderPath[1], FolderType.Shared, "Download", "yes", "No", null, Workspace.InvestorWorkspace, M15C1EmailId)){
					appLog.info(M15CFN1+" "+M15CLN1+" successfully invited from "+folderPath[1]);
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" didnot get the invitation from "+folderPath[1]);
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" didnot get the invitation from "+folderPath[1]);
				}
				if(fp.inviteContact(environment, mode, null, M15C1EmailId, folderPath[2], FolderType.Shared, "Download", "yes", "No", null, Workspace.InvestorWorkspace, M15C1EmailId)){
					appLog.info(M15CFN1+" "+M15CLN1+" successfully invited from "+folderPath[2]);
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" didnot get the invitation from "+folderPath[2]);
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" didnot get the invitation from "+folderPath[2]);
				}
				if(fp.inviteContact(environment, mode, null, M15C1EmailId, folderPath[3], FolderType.Shared, "Download", "yes", "No", null, Workspace.InvestorWorkspace, M15C1EmailId)){
					appLog.info(M15CFN1+" "+M15CLN1+" successfully invited from "+folderPath[3]);
				} else {
					appLog.error(M15CFN1+" "+M15CLN1+" didnot get the invitation from "+folderPath[3]);
					sa.assertTrue(false,M15CFN1+" "+M15CLN1+" didnot get the invitation from "+folderPath[3]);
				}
				switchToDefaultContent(driver);
				lp.CRMlogout();
				driver.close();
				config(browserToLaunch);
				lp = new LoginPageBusinessLayer(driver);
				InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
				fp = new FundsPageBusinessLayer(driver);
				lp.investorLogin(M15C1EmailId, adminPassword);
				if(ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)){
					if(selectVisibleTextFromDropDown(driver, ifp.getPotentialAndCurrentInvestmentInvestmentDropdown(30), "select fund", M15FundName2)){
					if(fp.verifyFolderPathdummy(folderPath[0], null, null, null, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 30)){
						appLog.info(folderPath[0]+" folder is verified.");
					} else {
						appLog.error(folderPath[0]+" Folder is not present at investor side after provideing the access.");
						sa.assertTrue(false,folderPath[0]+" Folder is not present at investor side after provideing the access.");
					}
					if(fp.verifyFolderPathdummy(folderPath[1], null, null, null, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 31)){
						appLog.info(folderPath[1]+" folder is verified.");
					} else {
						appLog.error(folderPath[1]+" Folder is not present at investor side after provideing the access.");
						sa.assertTrue(false,folderPath[1]+" Folder is not present at investor side after provideing the access.");
					}
					if(fp.verifyFolderPathdummy(folderPath[2], null, null, null, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 32)){
						appLog.info(folderPath[2]+" folder is verified.");
					} else {
						appLog.error(folderPath[2]+" Folder is not present at investor side after provideing the access.");
						sa.assertTrue(false,folderPath[2]+" Folder is not present at investor side after provideing the access.");
					}
					if(fp.verifyFolderPathdummy(folderPath[3], null, null, null, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 33)){
						appLog.info(folderPath[3]+" folder is verified.");
					} else {
						appLog.error(folderPath[3]+" Folder is not present at investor side after provideing the access.");
						sa.assertTrue(false,folderPath[3]+" Folder is not present at investor side after provideing the access.");
					}
					lp.investorLogout();
				} else {
					appLog.error("Investment tab cannot be clicked, So cannot continue with the testcase.");
					sa.assertTrue(false,"Investment tab cannot be clicked, So cannot continue with the testcase.");
				}
			} else {
				appLog.error(M15FundName2+" is not present in the list, SO cannot continue with the tc.");
				sa.assertTrue(false,M15FundName2+" is not present in the list, SO cannot continue with the tc.");
			}
			} else {
				appLog.error(M15FundName2+" is not present in the list, SO cannot continue with the tc.");
				sa.assertTrue(false,M15FundName2+" is not present in the list, SO cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot continue with the tc.");
		}
		sa.assertAll();
	}
	
	@Parameters({ "environment", "mode" }) @Test
	public void M15tc080_upload100FilesInSharedFolderINV(String environment, String mode){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String folderPath = ExcelUtils.readData(excelPath, "filepath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M15FundName2)){
//				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
//				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view.");
				if(fp.uploadFileBulk(environment, mode, CRMUser1EmailID, excelPath, folderPath, null, "UploadFiles\\BulkFiles\\Shared_100Files", UploadFileActions.Upload, UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)){
					appLog.info("Successfully uploaded files.");
				} else {
					appLog.error("Not able to upload 100 file.");
					sa.assertTrue(false,"Not able to upload 100 file.");
				}
				switchToDefaultContent(driver);
				lp.CRMlogout();
				driver.close();
				config(browserToLaunch);
				lp = new LoginPageBusinessLayer(driver);
				InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
				fp = new FundsPageBusinessLayer(driver);
				lp.investorLogin(M15C1EmailId, adminPassword);
				if(ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)){
					if(selectVisibleTextFromDropDown(driver, ifp.getPotentialAndCurrentInvestmentInvestmentDropdown(30), "select fund", M15FundName2)){
						if(fp.verifyFolderPathdummy(folderPath, null, null, null, PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace, 30)){
							if(ifp.getInvestorRecordCount(30)!=null){
								int count = Integer.parseInt(getText(driver, ifp.getInvestorRecordCount(30), "", action.BOOLEAN));
								if(count == 100){
									appLog.info("All Files are uploaded successfully.");
								} else {
									appLog.error("All files are not uploaded.");
									sa.assertTrue(false,"All files are not uploaded.");
								}
							} else {
								appLog.error("Reocrd count is not present on the page.");
								sa.assertTrue(false,"Reocrd count is not present on the page.");
							}
							
						} else {
							appLog.error(folderPath+" Folder is not present at investor side, So cannot verify files.");
							sa.assertTrue(false,folderPath+" Folder is not present at investor side, So cannot verify files.");
						}
					} else {
						appLog.error(M15FundName2+" fund is not present in the list, So cannot verify file upload.");
						sa.assertTrue(false,M15FundName2+" fund is not present in the list, So cannot verify file upload.");
					}
					lp.investorLogout();
				} else {
					appLog.error("Investment tab cannot be clicked, So cannot continue with the testcase.");
					sa.assertTrue(false,"Investment tab cannot be clicked, So cannot continue with the testcase.");
				}
			} else {
				appLog.error(M15FundName2+" is not present in the list, SO cannot continue with the tc.");
				sa.assertTrue(false,M15FundName2+" is not present in the list, SO cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot continue with the tc.");
		}
		sa.assertAll();
	}

 }
