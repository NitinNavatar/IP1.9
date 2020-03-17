/**
 * 
 */
package com.navatar.scripts;


import org.apache.poi.hwpf.model.types.HRESIAbstractType;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.navatar.generic.BaseLib;
import com.navatar.generic.CommonLib.EnableDisable;
import com.navatar.generic.CommonLib.ErrorMessageType;
import com.navatar.generic.CommonLib.ExpandCollapse;
import com.navatar.generic.CommonLib.FolderType;
import com.navatar.generic.CommonLib.PageName;
import com.navatar.generic.CommonLib.SortOrder;
import com.navatar.generic.CommonLib.TabName;
import com.navatar.generic.CommonLib.WorkSpaceAction;
import com.navatar.generic.CommonLib.Workspace;
import com.navatar.generic.CommonLib.action;
import com.navatar.generic.CommonLib.excelLabel;
import com.navatar.generic.ExcelUtils;
import com.navatar.generic.SoftAssert;
import com.navatar.pageObjects.BasePageBusinessLayer;
import com.navatar.pageObjects.BasePageErrorMessage;
import com.navatar.pageObjects.CommitmentPageBusinessLayer;
import com.navatar.pageObjects.FundRaisingPageBusinessLayer;
import com.navatar.pageObjects.FundsPageBusinessLayer;
import com.navatar.pageObjects.FundsPageErrorMessage;
import com.navatar.pageObjects.InstitutionPageBusinessLayer;
import com.navatar.pageObjects.LoginPageBusinessLayer;
import com.navatar.pageObjects.NIMPageBusinessLayer;
import com.navatar.pageObjects.PartnershipPageBusinessLayer;

import freemarker.cache.TemplateNameFormat;

import static com.navatar.generic.AppListeners.appLog;
import static com.navatar.generic.CommonLib.*;
import static com.navatar.generic.CommonVariables.*;

import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;


/**
 * @author Ankur Rana
 *
 */
public class Module8 extends BaseLib{

	@Test
	public void M8tc001_PreCondition(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		InstitutionPageBusinessLayer inst = new InstitutionPageBusinessLayer(driver);
		if(lp.clickOnTab(TabName.InstituitonsTab)){
			if(inst.createInstitution(ExcelUtils.readData("Institutions", excelLabel.Variable_Name, "M8I1", excelLabel.Institutions_Name))){
				appLog.info("Institution '"+ExcelUtils.readData("Institutions", excelLabel.Variable_Name, "M8I1", excelLabel.Institutions_Name)+"' is created successfully.");
			} else {
				appLog.error(ExcelUtils.readData("Institutions", excelLabel.Variable_Name, "M8I1", excelLabel.Institutions_Name)+" institution is not created.");
				sa.assertTrue(false,ExcelUtils.readData("Institutions", excelLabel.Variable_Name, "M8I1", excelLabel.Institutions_Name)+" institution is not created.");
			}
		} else {
			appLog.error("Insitution Tab cannot be clicked, So cannot create institution '"+ExcelUtils.readData("Institutions", excelLabel.Variable_Name, "M8I2", excelLabel.Institutions_Name)+"'.");
			sa.assertTrue(false,"Insitution Tab cannot be clicked, So cannot create institution '"+ExcelUtils.readData("Institutions", excelLabel.Variable_Name, "M8I2", excelLabel.Institutions_Name)+"'.");
		}
		if(lp.clickOnTab(TabName.InstituitonsTab)){
			if(inst.createInstitution(ExcelUtils.readData("Institutions", excelLabel.Variable_Name, "M8I2", excelLabel.Institutions_Name))){
				appLog.info("Institution '"+ExcelUtils.readData("Institutions", excelLabel.Variable_Name, "M8I2", excelLabel.Institutions_Name)+"' is created successfully.");
			} else {
				appLog.error(ExcelUtils.readData("Institutions", excelLabel.Variable_Name, "M8I2", excelLabel.Institutions_Name)+" institution is not created.");
				sa.assertTrue(false,ExcelUtils.readData("Institutions", excelLabel.Variable_Name, "M8I2", excelLabel.Institutions_Name)+" institution is not created.");
			}
		} else {
			appLog.error("Insitution Tab cannot be clicked, So cannot create institution '"+ExcelUtils.readData("Institutions", excelLabel.Variable_Name, "M8I2", excelLabel.Institutions_Name)+"'.");
			sa.assertTrue(false,"Insitution Tab cannot be clicked, So cannot create institution '"+ExcelUtils.readData("Institutions", excelLabel.Variable_Name, "M8I2", excelLabel.Institutions_Name)+"'.");
		}
		
		if(lp.clickOnTab(TabName.InstituitonsTab)){
			if(inst.createLimitedPartner(M8LP1, M8I1)){
				appLog.info(M8LP1+" LP Created successfully.");
			} else {
				appLog.error(M8LP1+" LP is not created successfully.");
				sa.assertTrue(false,M8LP1+" LP is not created successfully.");
			}
		} else {
			appLog.error("Insitution Tab cannot be clicked, So cannot create LP '"+M8LP1+"'.");
			sa.assertTrue(false,"Insitution Tab cannot be clicked, So cannot create LP '"+M8LP1+"'.");
		}
		if(lp.clickOnTab(TabName.InstituitonsTab)){
			if(inst.createLimitedPartner(M8LP2, M8I2)){
				appLog.info(M8LP2+" LP Created successfully.");
			} else {
				appLog.error(M8LP2+" LP is not created successfully.");
				sa.assertTrue(false,M8LP2+" LP is not created successfully.");
			}
		} else {
			appLog.error("Insitution Tab cannot be clicked, So cannot create LP '"+M8LP2+"'.");
			sa.assertTrue(false,"Insitution Tab cannot be clicked, So cannot create LP '"+M8LP2+"'.");
		}
		
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.createFund(M8F1, M8FT1, M8FIC1)){
				appLog.info(M8F1+" fund created successfully");
			} else {
				appLog.error(M8F1+" fund cannot be created.");
				sa.assertTrue(false,M8F1+" fund cannot be created.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot continue with the TC.");
		}
		
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.createFund(M8F2, M8FT2, M8FIC2)){
				appLog.info(M8F2+" fund created successfully");
			} else {
				appLog.error(M8F2+" fund cannot be created.");
				sa.assertTrue(false,M8F2+" fund cannot be created.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot continue with the TC.");
		}
		
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.createFund(M8F3, M8FT3, M8FIC3)){
				appLog.info(M8F3+" fund created successfully");
			} else {
				appLog.error(M8F3+" fund cannot be created.");
				sa.assertTrue(false,M8F3+" fund cannot be created.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot continue with the TC.");
		}
		
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.createFund(M8F4, M8FT4, M8FIC4)){
				appLog.info(M8F4+" fund created successfully");
			} else {
				appLog.error(M8F4+" fund cannot be created.");
				sa.assertTrue(false,M8F4+" fund cannot be created.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot continue with the TC.");
		}
		
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.createFund(M8F5, M8FT5, M8FIC5)){
				appLog.info(M8F5+" fund created successfully");
			} else {
				appLog.error(M8F5+" fund cannot be created.");
				sa.assertTrue(false,M8F5+" fund cannot be created.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot continue with the TC.");
		}
		
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.createFund(M8F6, M8FT6, M8FIC6)){
				appLog.info(M8F6+" fund created successfully");
			} else {
				appLog.error(M8F6+" fund cannot be created.");
				sa.assertTrue(false,M8F6+" fund cannot be created.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot continue with the TC.");
		}
		
		FundRaisingPageBusinessLayer fdr = new FundRaisingPageBusinessLayer(driver);
		if(lp.clickOnTab(TabName.FundraisingsTab)){
			if(fdr.createFundRaising(M8FR1, M8F2, M8I1)){
				appLog.info(M8FR1+" fundraising created successfully.");
			} else {
				appLog.error(M8FR1+" fundraising not created.");
				sa.assertTrue(false,M8FR1+" fundraising not created.");
			}
		} else {
			appLog.error("fundraising tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"fundraising tab cannot be clicked, So cannot continue with the TC.");
		}
		
		if(lp.clickOnTab(TabName.FundraisingsTab)){
			if(fdr.createFundRaising(M8FR2, M8F2, M8I2)){
				appLog.info(M8FR2+" fundraising created successfully.");
			} else {
				appLog.error(M8FR2+" fundraising not created.");
				sa.assertTrue(false,M8FR2+" fundraising not created.");
			}
		} else {
			appLog.error("fundraising tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"fundraising tab cannot be clicked, So cannot continue with the TC.");
		}
		
		if(lp.clickOnTab(TabName.FundraisingsTab)){
			if(fdr.createFundRaising(M8FR3, M8F3, M8I1)){
				appLog.info(M8FR3+" fundraising created successfully.");
			} else {
				appLog.error(M8FR3+" fundraising not created.");
				sa.assertTrue(false,M8FR3+" fundraising not created.");
			}
		} else {
			appLog.error("fundraising tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"fundraising tab cannot be clicked, So cannot continue with the TC.");
		}
		
		if(lp.clickOnTab(TabName.FundraisingsTab)){
			if(fdr.createFundRaising(M8FR4, M8F3, M8I2)){
				appLog.info(M8FR4+" fundraising created successfully.");
			} else {
				appLog.error(M8FR4+" fundraising not created.");
				sa.assertTrue(false,M8FR4+" fundraising not created.");
			}
		} else {
			appLog.error("fundraising tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"fundraising tab cannot be clicked, So cannot continue with the TC.");
		}
		
		PartnershipPageBusinessLayer prt= new PartnershipPageBusinessLayer(driver);
		if(lp.clickOnTab(TabName.PartnershipsTab)){
			if(prt.createPartnership(M8P1, M8F5)){
				appLog.info(M8P1+" PartnerShip created successfully.");
			} else {
				appLog.error(M8P1+" partnership not created.");
				sa.assertTrue(false,M8P1+" partnership not created.");
			}
		} else {
			appLog.error("PartnerShip tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"PartnerShip tab cannot be clicked, So cannot continue with the TC.");
		}
		
		if(lp.clickOnTab(TabName.PartnershipsTab)){
			if(prt.createPartnership(M8P2, M8F6)){
				appLog.info(M8P2+" PartnerShip created successfully.");
			} else {
				appLog.error(M8P2+" partnership not created.");
				sa.assertTrue(false,M8P2+" partnership not created.");
			}
		} else {
			appLog.error("PartnerShip tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"PartnerShip tab cannot be clicked, So cannot continue with the TC.");
		}
		
		CommitmentPageBusinessLayer cmt = new CommitmentPageBusinessLayer(driver);
		if(lp.clickOnTab(TabName.CommitmentsTab)){
			if(cmt.createCommitment(M8LP1, M8P1, "M8CMT1", null)){
				appLog.info("Commitment created successfully.");
			} else {
				appLog.error("Commitment not created.");
				sa.assertTrue(false,"Commitment not created.");
			}
		} else {
			appLog.error("Commitments tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"Commitments tab cannot be clicked, So cannot continue with the TC.");
		}
		
		if(lp.clickOnTab(TabName.CommitmentsTab)){
			if(cmt.createCommitment(M8LP2, M8P1, "M8CMT2", null)){
				appLog.info("Commitment created successfully.");
			} else {
				appLog.error("Commitment not created.");
				sa.assertTrue(false,"Commitment not created.");
			}
		} else {
			appLog.error("Commitments tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"Commitments tab cannot be clicked, So cannot continue with the TC.");
		}
		
		if(lp.clickOnTab(TabName.CommitmentsTab)){
			if(cmt.createCommitment(M8LP1, M8P2, "M8CMT3", null)){
				appLog.info("Commitment created successfully.");
			} else {
				appLog.error("Commitment not created.");
				sa.assertTrue(false,"Commitment not created.");
			}
		} else {
			appLog.error("Commitments tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"Commitments tab cannot be clicked, So cannot continue with the TC.");
		}
		
		if(lp.clickOnTab(TabName.CommitmentsTab)){
			if(cmt.createCommitment(M8LP2, M8P2, "M8CMT4", null)){
				appLog.info("Commitment created successfully.");
			} else {
				appLog.error("Commitment not created.");
				sa.assertTrue(false,"Commitment not created.");
			}
		} else {
			appLog.error("Commitments tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"Commitments tab cannot be clicked, So cannot continue with the TC.");
		}
		
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M8tc002_ProvideAccessToCRMUser(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName, adminPassword);
		if(lp.clickOnTab(TabName.NIMTab)){
			if(nim.giveAccessToUserInNIMTabFromAdmin(CRMUser1FirstName+" "+CRMUser1LastName, accessType.InternalUserAccess)){
				appLog.info("Successfully provided access to user '"+CRMUser1FirstName+" "+CRMUser1LastName+"'.");
			} else {
				appLog.error("Not able to provide aaccess to user '"+CRMUser1FirstName+" "+CRMUser1LastName+"', So cannot continue wih the tc.");
				sa.assertTrue(false,"Not able to provide aaccess to user '"+CRMUser1FirstName+" "+CRMUser1LastName+"', So cannot continue wih the tc.");
			}
		} else {
			appLog.error("NIM Tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"NIM Tab cannot be clicked, SO cannot continue with the tc.");
		}
		if(lp.clickOnTab(TabName.NIMTab)){
			switchToFrame(driver, 30, nim.getNIMTabFrame(30));
			if(click(driver, nim.getFolderTemplatetab(30), "Folder template tab", action.BOOLEAN)){
				if(nim.deleteAllFolderTemplates()){
					appLog.info("Folder Templates are deleted successfully.");
				} else {
					appLog.error("Folder Templates are not deleted.");
					sa.assertTrue(false,"Folder Templates are not deleted.");
				}
			} else {
				appLog.error("Folder template tab cannot be clicked, So cannot delete folder template.");
				sa.assertTrue(false, "Folder template tab cannot be clicked, So cannot delete folder template.");
			}
		} else {
			appLog.error("NIM tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"NIM tab cannot be clicked, So cannot continue delete.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
		
	}
	
	@Test
	public void M8tc002_RegisterCRMUserAndVerifyBuildWorkspaceButton(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.NIMTab)){
			switchToFrame(driver, 30, nim.getFrame(PageName.NavatarInvestorManager, 30));
			if(click(driver, nim.getRegistrationSuccessfulCloseBtn(30), "Close Button", action.BOOLEAN)){
				appLog.info("User is successfully registered.");
			} else {
				appLog.error("cannot click on the registration successfull close button, So crm user '"+CRMUser1FirstName+" "+CRMUser1LastName+"' is not successfully registered.");
				sa.assertTrue(false,"cannot click on the registration successfull close button, So crm user '"+CRMUser1FirstName+" "+CRMUser1LastName+"' is not successfully registered.");
			}
			switchToDefaultContent(driver);
		} else {
			appLog.error("NIM Tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"NIM Tab cannot be clicked, SO cannot continue with the tc.");
		}
		if(nim.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F1)){
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Workspace view.");
				if(fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 30)!=null){
					appLog.info("Build Fundraising Workspace button is verified.");
				} else {
					appLog.error("Build Fundraising Workspace button is not verified for fund '"+M8F1+"'.");
					sa.assertTrue(false,"Build Fundraising Workspace button is not verified for fund '"+M8F1+"'.");
				}
				if(fp.getBuildWorkspaceButton(Workspace.InvestorWorkspace, 30)!=null){
					appLog.info("Build Investor Workspace button is verified.");
				} else {
					appLog.error("Build Investor Workspace button is not verified for fund '"+M8F1+"'.");
					sa.assertTrue(false,"Build Investor Workspace button is not verified for fund '"+M8F1+"'.");
				}
				switchToDefaultContent(driver);
			} else {
				appLog.error(M8F1+" fund is not present in the list, So cannot verify build workspace button.");
				sa.assertTrue(false,M8F1+" fund is not present in the list, So cannot verify build workspace button.");
			}
		} else {
			appLog.error("Funds Tab cannot be clicked, so cannot verify build workspace button.");
			sa.assertTrue(false,"Funds Tab cannot be clicked, so cannot verify build workspace button.");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M8tc003_VerifyBuildWorkspaceStep1Of3(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F1)){
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 30), "Build fundraising workspace button", action.BOOLEAN)){
					if(fp.getFundNameField(Workspace.FundraisingWorkspace, 30)!=null){
						String text = getAttribute(driver, fp.getFundNameField(Workspace.FundraisingWorkspace, 30), "", "value");
						if(text.equalsIgnoreCase(M8F1)){
							appLog.error("Fund name in the field is verified.");
						} else {
							appLog.error("Fund Name in fund name field is not verified. Expected: "+M8F1+"\tActual: "+text);
							sa.assertTrue(false,"Fund Name in fund name field is not verified. Expected: "+M8F1+"\tActual: "+text);
						}
					} else {
						appLog.error("Fund Name Field is not present on step 1 Of 3 of building process.");
						sa.assertTrue(false,"Fund Name Field is not present on step 1 Of 3 of building process.");
					}
					
					if(fp.getFundSizeField(Workspace.FundraisingWorkspace, 30)!=null){
						appLog.info("Fund size field is verified.");
					} else {
						appLog.error("Fund Size Field is not present on step 1 Of 3 of workspace building process.");
						sa.assertTrue(false,"Fund Size Field is not present on step 1 Of 3 of workspace building process.");
					}
					
					if(fp.getFundVintageYearField(Workspace.FundraisingWorkspace, 30)!=null){
						appLog.info("Fund Vintage Year field is verified.");
					} else {
						appLog.error("Fund Vintage Year Field is not present on step 1 Of 3 of workspace building process.");
						sa.assertTrue(false,"Fund Vintage Year Field is not present on step 1 Of 3 of workspace building process.");
					}
					
					if(fp.getFundFundraisingYesRadioButton(Workspace.FundraisingWorkspace, 30)!=null){
						appLog.info("Fundraising Yes Radio Button field is present and verified.");
						if(isSelected(driver, fp.getFundFundraisingYesRadioButton(Workspace.FundraisingWorkspace, 30), "Yes Radio Button")){
							appLog.info("Fundraising yes radio button is by default selected.");
						} else {
							appLog.error("Fundraising yes radio button is not by default selected.");
							sa.assertTrue(false,"Fundraising yes radio button is not by default selected.");
						}
					} else {
						appLog.error("Fundraising Yes radio button Field is not present on step 1 Of 3 of workspace building process.");
						sa.assertTrue(false,"Fundraising Yes radio button Field is not present on step 1 Of 3 of workspace building process.");
					}
					
					if(fp.getFundFundraisingNoRadioButton(Workspace.FundraisingWorkspace, 30)!=null){
						appLog.info("Fundraising No Radio Button field is present and verified.");
						if(!isSelected(driver, fp.getFundFundraisingNoRadioButton(Workspace.FundraisingWorkspace, 30), "No Radio Button")){
							appLog.info("Fundraising No radio button is by default not selected and verified.");
						} else {
							appLog.error("Fundraising No radio button is by default selected.");
							sa.assertTrue(false,"Fundraising No radio button is by default selected.");
						}
					} else {
						appLog.error("Fundraising No radio button Field is not present on step 1 Of 3 of workspace building process.");
						sa.assertTrue(false,"Fundraising No radio button Field is not present on step 1 Of 3 of workspace building process.");
					}
					
					if(fp.getFundContactField(Workspace.FundraisingWorkspace, 30)!=null){
						appLog.info("Fund Contact field is verified.");
					} else {
						appLog.error("Fund Contact Field is not present on step 1 Of 3 of workspace building process.");
						sa.assertTrue(false,"Fund Contact Field is not present on step 1 Of 3 of workspace building process.");
					}
					
					if(fp.getFundPhoneField(Workspace.FundraisingWorkspace, 30)!=null){
						appLog.info("Fund Phone field is verified.");
					} else {
						appLog.error("Fund Phone Field is not present on step 1 Of 3 of workspace building process.");
						sa.assertTrue(false,"Fund Phone Field is not present on step 1 Of 3 of workspace building process.");
					}
					
					if(fp.getFundEmailField(Workspace.FundraisingWorkspace, 30)!=null){
						appLog.info("Fund Email field is verified.");
					} else {
						appLog.error("Fund Email Field is not present on step 1 Of 3 of workspace building process.");
						sa.assertTrue(false,"Fund Email Field is not present on step 1 Of 3 of workspace building process.");
					}
					
					if(fp.getFundDescriptionField(Workspace.FundraisingWorkspace, 30)!=null){
						appLog.info("Fund Description field is verified.");
					} else {
						appLog.error("Fund Description Field is not present on step 1 Of 3 of workspace building process.");
						sa.assertTrue(false,"Fund Description Field is not present on step 1 Of 3 of workspace building process.");
					}
					
					if(fp.getStep1Of3CancelButton(Workspace.FundraisingWorkspace, 30)!=null){
						appLog.info("Step 1 Of 3 Cancel Button is verified.");
					} else {
						appLog.error("Step 1 Of 3 Cancel Button is not present on step 1 Of 3 of workspace building process.");
						sa.assertTrue(false,"Step 1 Of 3 Cancel Button is not present on step 1 Of 3 of workspace building process.");
					}
					
					if(fp.getStep1Of3CrossIcon(Workspace.FundraisingWorkspace, 30)!=null){
						appLog.info("Step 1 Of 3 Cross Icon is verified.");
					} else {
						appLog.error("Step 1 Of 3 Cross Icon is not present on step 1 Of 3 of workspace building process.");
						sa.assertTrue(false,"Step 1 Of 3 Cross Icon is not present on step 1 Of 3 of workspace building process.");
					}
					
					if(fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 30)!=null){
						appLog.info("Step 1 Of 3 Next Button is verified.");
					} else {
						appLog.error("Step 1 Of 3 Next Button is not present on step 1 Of 3 of workspace building process.");
						sa.assertTrue(false,"Step 1 Of 3 Next Button is not present on step 1 Of 3 of workspace building process.");
					}
					
					if(clearTextBox(fp.getFundNameField(Workspace.FundraisingWorkspace, 30))){
						if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 30), "Next Button", action.BOOLEAN)){
							String text = getText(driver, fp.getFundNameFieldErrorMessage(Workspace.FundraisingWorkspace, 1, 30), "Fund Name Error Message", action.BOOLEAN);
							if(text.equalsIgnoreCase(FundsPageErrorMessage.PleaseEnterValueErrorMessage)){
								appLog.info("Fund name field Error message is verified.");
							} else {
								appLog.error("Fund name field Error message is not verified. Expected: "+FundsPageErrorMessage.PleaseEnterValueErrorMessage+"\tActual: "+text);
								sa.assertTrue(false,"Fund name field Error message is not verified. Expected: "+FundsPageErrorMessage.PleaseEnterValueErrorMessage+"\tActual: "+text);
							}
							
							text = getText(driver, fp.getFundContactFieldErrorMessage(Workspace.FundraisingWorkspace, 30), "Fund Contact Error Message", action.BOOLEAN);
							if(text.equalsIgnoreCase(FundsPageErrorMessage.PleaseEnterValueErrorMessage)){
								appLog.info("Fund Contact field Error message is verified.");
							} else {
								appLog.error("Fund Contact field Error message is not verified. Expected: "+FundsPageErrorMessage.PleaseEnterValueErrorMessage+"\tActual: "+text);
								sa.assertTrue(false,"Fund Contact field Error message is not verified. Expected: "+FundsPageErrorMessage.PleaseEnterValueErrorMessage+"\tActual: "+text);
							}
							
							text = getText(driver, fp.getFundPhoneFieldErrorMessage(Workspace.FundraisingWorkspace, 1, 30), "Fund Phone Error Message", action.BOOLEAN);
							if(text.equalsIgnoreCase(FundsPageErrorMessage.PleaseEnterValueErrorMessage)){
								appLog.info("Fund Phone field Error message is verified.");
							} else {
								appLog.error("Fund Phone field Error message is not verified. Expected: "+FundsPageErrorMessage.PleaseEnterValueErrorMessage+"\tActual: "+text);
								sa.assertTrue(false,"Fund Phone field Error message is not verified. Expected: "+FundsPageErrorMessage.PleaseEnterValueErrorMessage+"\tActual: "+text);
							}
							
							text = getText(driver, fp.getFundEmailFieldErrorMessage(Workspace.FundraisingWorkspace, 2, 30), "Fund Email Error Message", action.BOOLEAN);
							if(text.equalsIgnoreCase(FundsPageErrorMessage.PleaseEnterValueErrorMessage)){
								appLog.info("Fund Email field Error message is verified.");
							} else {
								appLog.error("Fund Email field Error message is not verified. Expected: "+FundsPageErrorMessage.PleaseEnterValueErrorMessage+"\tActual: "+text);
								sa.assertTrue(false,"Fund Email field Error message is not verified. Expected: "+FundsPageErrorMessage.PleaseEnterValueErrorMessage+"\tActual: "+text);
							}
							
							if(sendKeys(driver, fp.getFundNameField(Workspace.FundraisingWorkspace, 30), "FundName []{}/\\?|<>*:", "Fund Name Field", action.BOOLEAN)){
								if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 30), "Next", action.BOOLEAN)){
									text = getText(driver, fp.getFundNameFieldErrorMessage(Workspace.FundraisingWorkspace, 2, 30), "Fund Name Error Message", action.BOOLEAN);
									if(text.equalsIgnoreCase(FundsPageErrorMessage.InvalidNameErrorMessage)){
										appLog.info("Fund name field Error message is verified.");
									} else {
										appLog.error("Fund name field Error message is not verified. Expected: "+FundsPageErrorMessage.InvalidNameErrorMessage+"\tActual: "+text);
										sa.assertTrue(false,"Fund name field Error message is not verified. Expected: "+FundsPageErrorMessage.InvalidNameErrorMessage+"\tActual: "+text);
									}
								} else {
									appLog.error("Next Button cannot be clicked, So cannot check invalid data the error message.");
									sa.assertTrue(false,"Next Button cannot be clicked, So cannot check invalid data the error message.");
								}
							} else {
								appLog.error("Cannot pass value to fund name field, So cannot check invalid data error message.");
								sa.assertTrue(false,"Cannot pass value to fund name field, So cannot check invalid data error message.");
							}
							
							if(click(driver, fp.getStep1Of3CancelButton(Workspace.FundraisingWorkspace, 30), "Cancel Button", action.BOOLEAN)){
								ThreadSleep(1500);
								if(fp.getStep1Of3Header(Workspace.FundraisingWorkspace, 30)==null){
									appLog.info("Pop is successfully closed after clicking on the cancel button.");
								} else {
									appLog.error("Pop is not closed after clicking on the cancel button.");
									sa.assertTrue(false,"Pop is not closed after clicking on the cancel button.");
									fp.recover(Workspace.FundraisingWorkspace, 30);
								}
							} else {
								appLog.error("Step 1 Of 3 cancel button cannot be clicked, So cannot check cancel button functionality.");
								sa.assertTrue(false,"Step 1 Of 3 cancel button cannot be clicked, So cannot check cancel button functionality.");
								fp.recover(Workspace.FundraisingWorkspace, 30);
							}
							
							if(click(driver, fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 30), "Build fundraising workspace button", action.BOOLEAN)){
								if(click(driver, fp.getStep1Of3CrossIcon(Workspace.FundraisingWorkspace, 30), "Cross icon", action.BOOLEAN)){
									ThreadSleep(1500);
									if(fp.getStep1Of3Header(Workspace.FundraisingWorkspace, 30)==null){
										appLog.info("Pop is successfully closed after clicking on the Cross Icon.");
									} else {
										appLog.error("Pop is not closed after clicking on the Cross Icon.");
										sa.assertTrue(false,"Pop is not closed after clicking on the Cross Icon.");
										fp.recover(Workspace.FundraisingWorkspace, 30);
									}
								} else {
									appLog.error("Cross icon cannot be clicked, So cannot check the cross icon functionality.");
									sa.assertTrue(false,"Cross icon cannot be clicked, So cannot check the cross icon functionality.");
									fp.recover(Workspace.FundraisingWorkspace, 30);
								}
							} else {
								appLog.error("Cannot click on the build workspace button, So cannot check cross icon functionality.");
								sa.assertTrue(false,"Cannot click on the build workspace button, So cannot check cross icon functionality.");
							}
							
							if(click(driver, fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 30), "Build fundraising workspace button", action.BOOLEAN)){
								if(sendKeys(driver, fp.getFundVintageYearField(Workspace.FundraisingWorkspace, 30), "Invalid Vintage Year", "Vintage year", action.BOOLEAN)){
									appLog.info("Successfully passed value to vintage year text box.");
								} else {
									appLog.error("Cannot pass value to vintage year text box, So cannot check invalid data message for vintage year field.");
									sa.assertTrue(false,"Cannot pass value to vintage year text box, So cannot check invalid data message for vintage year field.");
								}
								
								if(sendKeys(driver, fp.getFundSizeField(Workspace.FundraisingWorkspace, 30), "Invalid fund size", "Fund Size", action.BOOLEAN)){
									appLog.info("Successfully passed value to Fund size text box.");
								} else {
									appLog.error("Cannot pass value to Fund size text box, So cannot check invalid data message for Fund size field.");
									sa.assertTrue(false,"Cannot pass value to Fund size text box, So cannot check invalid data message for Fund size field.");
								}
								
								if(sendKeys(driver, fp.getFundContactField(Workspace.FundraisingWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
									appLog.info("Successfully passed value to Fund contact text box.");
								} else {
									appLog.error("Cannot pass value to Fund contact text box, So cannot check invalid data message for Fund contact field.");
									sa.assertTrue(false,"Cannot pass value to Fund contact text box, So cannot check invalid data message for Fund contact field.");
								}
								
								if(sendKeys(driver, fp.getFundEmailField(Workspace.FundraisingWorkspace, 30), "Invalid Email", "Fund Email", action.BOOLEAN)){
									appLog.info("Successfully passed value to Fund Email text box.");
								} else {
									appLog.error("Cannot pass value to Fund Email text box, So cannot check invalid data message for Fund Email field.");
									sa.assertTrue(false,"Cannot pass value to Fund Email text box, So cannot check invalid data message for Fund Email field.");
								}
								
								if(sendKeys(driver, fp.getFundPhoneField(Workspace.FundraisingWorkspace, 30), "Invalid Phone", "Fund Phone", action.BOOLEAN)){
									appLog.info("Successfully passed value to Fund Phone text box.");
								} else {
									appLog.error("Cannot pass value to Fund Phone text box, So cannot check invalid data message for Fund Phone field.");
									sa.assertTrue(false,"Cannot pass value to Fund Phone text box, So cannot check invalid data message for Fund Phone field.");
								}
								
								if(sendKeys(driver, fp.getFundDescriptionField(Workspace.FundraisingWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
									appLog.info("Successfully passed value to Fund Description text box.");
								} else {
									appLog.error("Cannot pass value to Fund Description text box, So cannot check invalid data message for Fund Description field.");
									sa.assertTrue(false,"Cannot pass value to Fund Description text box, So cannot check invalid data message for Fund Description field.");
								}
								
								if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 30), "Next Button", action.BOOLEAN)){
									text = getText(driver, fp.getFundSizeFieldErrorMessage(Workspace.FundraisingWorkspace, 0, 30), "Fund Size Error Message", action.BOOLEAN);
									if(text.equalsIgnoreCase(FundsPageErrorMessage.InvalidFundSizeErrorMessage)){
										appLog.info("Fund size field Error message is verified.");
									} else {
										appLog.error("Fund size field Error message is not verified. Expected: "+FundsPageErrorMessage.InvalidFundSizeErrorMessage+"\tActual: "+text);
										sa.assertTrue(false,"Fund size field Error message is not verified. Expected: "+FundsPageErrorMessage.InvalidFundSizeErrorMessage+"\tActual: "+text);
									}
									
									text = getText(driver, fp.getFundVintageYearFieldErrorMessage(Workspace.FundraisingWorkspace, 1, 30), "Fund Contact Error Message", action.BOOLEAN);
									if(text.equalsIgnoreCase(FundsPageErrorMessage.InvalidFundVintageYearErrorMessage)){
										appLog.info("Fund vintage year field Error message is verified.");
									} else {
										appLog.error("Fund vintage year field Error message is not verified. Expected: "+FundsPageErrorMessage.InvalidFundVintageYearErrorMessage+"\tActual: "+text);
										sa.assertTrue(false,"Fund vintage year field Error message is not verified. Expected: "+FundsPageErrorMessage.InvalidFundVintageYearErrorMessage+"\tActual: "+text);
									}
									
									text = getText(driver, fp.getFundPhoneFieldErrorMessage(Workspace.FundraisingWorkspace, 2, 30), "Fund Phone Error Message", action.BOOLEAN);
									if(text.equalsIgnoreCase(FundsPageErrorMessage.InvalidFundPhoneErrorMessage)){
										appLog.info("Fund Phone field Error message is verified.");
									} else {
										appLog.error("Fund Phone field Error message is not verified. Expected: "+FundsPageErrorMessage.InvalidFundPhoneErrorMessage+"\tActual: "+text);
										sa.assertTrue(false,"Fund Phone field Error message is not verified. Expected: "+FundsPageErrorMessage.InvalidFundPhoneErrorMessage+"\tActual: "+text);
									}
									
									text = getText(driver, fp.getFundEmailFieldErrorMessage(Workspace.FundraisingWorkspace, 1, 30), "Fund Email Error Message", action.BOOLEAN);
									if(text.equalsIgnoreCase(FundsPageErrorMessage.InvalidFundEmailErrorMessage)){
										appLog.info("Fund Email field Error message is verified.");
									} else {
										appLog.error("Fund Email field Error message is not verified. Expected: "+FundsPageErrorMessage.InvalidFundEmailErrorMessage+"\tActual: "+text);
										sa.assertTrue(false,"Fund Email field Error message is not verified. Expected: "+FundsPageErrorMessage.InvalidFundEmailErrorMessage+"\tActual: "+text);
									}
								} else {
									appLog.error("Next Button cannot be clikced, So cannot check invalid data error message for any field.");
									sa.assertTrue(false,"Next Button cannot be clikced, So cannot check invalid data error message for any field.");
								}
								
							} else {
								appLog.error("Cannot click on the build workspace button, So cannot continue check cross button functionality.");
								sa.assertTrue(false,"Cannot click on the build workspace button, So cannot continue check cross button functionality.");
							}
						} else {
							appLog.error("Next Button cannot be clicked, So cannot check the error message.");
							sa.assertTrue(false,"Next Button cannot be clicked, So cannot check the error message.");
						}
					} else {
						appLog.error("not able to clear fund name text box, So cannot check the error message.");
						sa.assertTrue(false,"not able to clear fund name text box, So cannot check the error message.");
					}
					
				} else {
					appLog.error("Build fundraising workspace button cannot be clicked, So cannot verify step 1 of 3.");
					sa.assertTrue(false,"Build fundraising workspace button cannot be clicked, So cannot verify step 1 of 3.");
				}
			} else {
				appLog.error(M8F1+" fund is not present in the list, so cannot verify step 1 of 3.");
				sa.assertTrue(false,M8F1+" fund is not present in the list, so cannot verify step 1 of 3.");
			}
		} else {
			appLog.error("Funds Tab cannot be clicked, So cannot verify step 1 of 3 of workspace building process.");
			sa.assertTrue(false,"Funds Tab cannot be clicked, So cannot verify step 1 of 3 of workspace building process.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M8tc016_VerifyBuildWorkspaceStep1Of3(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F4)){
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.InvestorWorkspace, 30), "Build Investor workspace button", action.BOOLEAN)){
					if(fp.getFundNameField(Workspace.InvestorWorkspace, 30)!=null){
						String text = getAttribute(driver, fp.getFundNameField(Workspace.InvestorWorkspace, 30), "", "value");
						if(text.equalsIgnoreCase(M8F4)){
							appLog.error("Fund name in the field is verified.");
						} else {
							appLog.error("Fund Name in fund name field is not verified. Expected: "+M8F4+"\tActual: "+text);
							sa.assertTrue(false,"Fund Name in fund name field is not verified. Expected: "+M8F4+"\tActual: "+text);
						}
					} else {
						appLog.error("Fund Name Field is not present on step 1 Of 3 of building process.");
						sa.assertTrue(false,"Fund Name Field is not present on step 1 Of 3 of building process.");
					}
					
					if(fp.getFundSizeField(Workspace.InvestorWorkspace, 30)!=null){
						appLog.info("Fund size field is verified.");
					} else {
						appLog.error("Fund Size Field is not present on step 1 Of 3 of workspace building process.");
						sa.assertTrue(false,"Fund Size Field is not present on step 1 Of 3 of workspace building process.");
					}
					
					if(fp.getFundVintageYearField(Workspace.InvestorWorkspace, 30)!=null){
						appLog.info("Fund Vintage Year field is verified.");
					} else {
						appLog.error("Fund Vintage Year Field is not present on step 1 Of 3 of workspace building process.");
						sa.assertTrue(false,"Fund Vintage Year Field is not present on step 1 Of 3 of workspace building process.");
					}
					
					if(fp.getFundFundraisingYesRadioButton(Workspace.InvestorWorkspace, 30)!=null){
						appLog.info("Investor Yes Radio Button field is present and verified.");
						if(isSelected(driver, fp.getFundFundraisingYesRadioButton(Workspace.InvestorWorkspace, 30), "Yes Radio Button")){
							appLog.info("Investor yes radio button is by default selected.");
						} else {
							appLog.error("Investor yes radio button is not by default selected.");
							sa.assertTrue(false,"Investor yes radio button is not by default selected.");
						}
					} else {
						appLog.error("Investor Yes radio button Field is not present on step 1 Of 3 of workspace building process.");
						sa.assertTrue(false,"Investor Yes radio button Field is not present on step 1 Of 3 of workspace building process.");
					}
					
					if(fp.getFundFundraisingNoRadioButton(Workspace.InvestorWorkspace, 30)!=null){
						appLog.info("Investor No Radio Button field is present and verified.");
						if(!isSelected(driver, fp.getFundFundraisingNoRadioButton(Workspace.InvestorWorkspace, 30), "No Radio Button")){
							appLog.info("Investor No radio button is by default not selected and verified.");
						} else {
							appLog.error("Investor No radio button is by default selected.");
							sa.assertTrue(false,"Investor No radio button is by default selected.");
						}
					} else {
						appLog.error("Investor No radio button Field is not present on step 1 Of 3 of workspace building process.");
						sa.assertTrue(false,"Investor No radio button Field is not present on step 1 Of 3 of workspace building process.");
					}
					
					if(fp.getFundContactField(Workspace.InvestorWorkspace, 30)!=null){
						appLog.info("Fund Contact field is verified.");
					} else {
						appLog.error("Fund Contact Field is not present on step 1 Of 3 of workspace building process.");
						sa.assertTrue(false,"Fund Contact Field is not present on step 1 Of 3 of workspace building process.");
					}
					
					if(fp.getFundPhoneField(Workspace.InvestorWorkspace, 30)!=null){
						appLog.info("Fund Phone field is verified.");
					} else {
						appLog.error("Fund Phone Field is not present on step 1 Of 3 of workspace building process.");
						sa.assertTrue(false,"Fund Phone Field is not present on step 1 Of 3 of workspace building process.");
					}
					
					if(fp.getFundEmailField(Workspace.InvestorWorkspace, 30)!=null){
						appLog.info("Fund Email field is verified.");
					} else {
						appLog.error("Fund Email Field is not present on step 1 Of 3 of workspace building process.");
						sa.assertTrue(false,"Fund Email Field is not present on step 1 Of 3 of workspace building process.");
					}
					
					if(fp.getFundDescriptionField(Workspace.InvestorWorkspace, 30)!=null){
						appLog.info("Fund Description field is verified.");
					} else {
						appLog.error("Fund Description Field is not present on step 1 Of 3 of workspace building process.");
						sa.assertTrue(false,"Fund Description Field is not present on step 1 Of 3 of workspace building process.");
					}
					
					if(fp.getStep1Of3CancelButton(Workspace.InvestorWorkspace, 30)!=null){
						appLog.info("Step 1 Of 3 Cancel Button is verified.");
					} else {
						appLog.error("Step 1 Of 3 Cancel Button is not present on step 1 Of 3 of workspace building process.");
						sa.assertTrue(false,"Step 1 Of 3 Cancel Button is not present on step 1 Of 3 of workspace building process.");
					}
					
					if(fp.getStep1Of3CrossIcon(Workspace.InvestorWorkspace, 30)!=null){
						appLog.info("Step 1 Of 3 Cross Icon is verified.");
					} else {
						appLog.error("Step 1 Of 3 Cross Icon is not present on step 1 Of 3 of workspace building process.");
						sa.assertTrue(false,"Step 1 Of 3 Cross Icon is not present on step 1 Of 3 of workspace building process.");
					}
					
					if(fp.getNext1Of3Button(Workspace.InvestorWorkspace, 30)!=null){
						appLog.info("Step 1 Of 3 Next Button is verified.");
					} else {
						appLog.error("Step 1 Of 3 Next Button is not present on step 1 Of 3 of workspace building process.");
						sa.assertTrue(false,"Step 1 Of 3 Next Button is not present on step 1 Of 3 of workspace building process.");
					}
					
					if(clearTextBox(fp.getFundNameField(Workspace.InvestorWorkspace, 30))){
						if(click(driver, fp.getNext1Of3Button(Workspace.InvestorWorkspace, 30), "Next Button", action.BOOLEAN)){
							String text = getText(driver, fp.getFundNameFieldErrorMessage(Workspace.InvestorWorkspace, 1, 30), "Fund Name Error Message", action.BOOLEAN);
							if(text.equalsIgnoreCase(FundsPageErrorMessage.PleaseEnterValueErrorMessage)){
								appLog.info("Fund name field Error message is verified.");
							} else {
								appLog.error("Fund name field Error message is not verified. Expected: "+FundsPageErrorMessage.PleaseEnterValueErrorMessage+"\tActual: "+text);
								sa.assertTrue(false,"Fund name field Error message is not verified. Expected: "+FundsPageErrorMessage.PleaseEnterValueErrorMessage+"\tActual: "+text);
							}
							
							text = getText(driver, fp.getFundContactFieldErrorMessage(Workspace.InvestorWorkspace, 30), "Fund Contact Error Message", action.BOOLEAN);
							if(text.equalsIgnoreCase(FundsPageErrorMessage.PleaseEnterValueErrorMessage)){
								appLog.info("Fund Contact field Error message is verified.");
							} else {
								appLog.error("Fund Contact field Error message is not verified. Expected: "+FundsPageErrorMessage.PleaseEnterValueErrorMessage+"\tActual: "+text);
								sa.assertTrue(false,"Fund Contact field Error message is not verified. Expected: "+FundsPageErrorMessage.PleaseEnterValueErrorMessage+"\tActual: "+text);
							}
							
							text = getText(driver, fp.getFundPhoneFieldErrorMessage(Workspace.InvestorWorkspace, 1, 30), "Fund Phone Error Message", action.BOOLEAN);
							if(text.equalsIgnoreCase(FundsPageErrorMessage.PleaseEnterValueErrorMessage)){
								appLog.info("Fund Phone field Error message is verified.");
							} else {
								appLog.error("Fund Phone field Error message is not verified. Expected: "+FundsPageErrorMessage.PleaseEnterValueErrorMessage+"\tActual: "+text);
								sa.assertTrue(false,"Fund Phone field Error message is not verified. Expected: "+FundsPageErrorMessage.PleaseEnterValueErrorMessage+"\tActual: "+text);
							}
							
							text = getText(driver, fp.getFundEmailFieldErrorMessage(Workspace.InvestorWorkspace, 2, 30), "Fund Email Error Message", action.BOOLEAN);
							if(text.equalsIgnoreCase(FundsPageErrorMessage.PleaseEnterValueErrorMessage)){
								appLog.info("Fund Email field Error message is verified.");
							} else {
								appLog.error("Fund Email field Error message is not verified. Expected: "+FundsPageErrorMessage.PleaseEnterValueErrorMessage+"\tActual: "+text);
								sa.assertTrue(false,"Fund Email field Error message is not verified. Expected: "+FundsPageErrorMessage.PleaseEnterValueErrorMessage+"\tActual: "+text);
							}
							
							if(sendKeys(driver, fp.getFundNameField(Workspace.InvestorWorkspace, 30), "FundName []{}/\\?|<>*:", "Fund Name Field", action.BOOLEAN)){
								if(click(driver, fp.getNext1Of3Button(Workspace.InvestorWorkspace, 30), "Next", action.BOOLEAN)){
									text = getText(driver, fp.getFundNameFieldErrorMessage(Workspace.InvestorWorkspace, 2, 30), "Fund Name Error Message", action.BOOLEAN);
									if(text.equalsIgnoreCase(FundsPageErrorMessage.InvalidNameErrorMessage)){
										appLog.info("Fund name field Error message is verified.");
									} else {
										appLog.error("Fund name field Error message is not verified. Expected: "+FundsPageErrorMessage.InvalidNameErrorMessage+"\tActual: "+text);
										sa.assertTrue(false,"Fund name field Error message is not verified. Expected: "+FundsPageErrorMessage.InvalidNameErrorMessage+"\tActual: "+text);
									}
								} else {
									appLog.error("Next Button cannot be clicked, So cannot check invalid data the error message.");
									sa.assertTrue(false,"Next Button cannot be clicked, So cannot check invalid data the error message.");
								}
							} else {
								appLog.error("Cannot pass value to fund name field, So cannot check invalid data error message.");
								sa.assertTrue(false,"Cannot pass value to fund name field, So cannot check invalid data error message.");
							}
							
							if(click(driver, fp.getStep1Of3CancelButton(Workspace.InvestorWorkspace, 30), "Cancel Button", action.BOOLEAN)){
								ThreadSleep(1500);
								if(fp.getStep1Of3Header(Workspace.InvestorWorkspace, 30)==null){
									appLog.info("Pop is successfully closed after clicking on the cancel button.");
								} else {
									appLog.error("Pop is not closed after clicking on the cancel button.");
									sa.assertTrue(false,"Pop is not closed after clicking on the cancel button.");
									fp.recover(Workspace.InvestorWorkspace, 30);
								}
							} else {
								appLog.error("Step 1 Of 3 cancel button cannot be clicked, So cannot check cancel button functionality.");
								sa.assertTrue(false,"Step 1 Of 3 cancel button cannot be clicked, So cannot check cancel button functionality.");
								fp.recover(Workspace.InvestorWorkspace, 30);
							}
							
							if(click(driver, fp.getBuildWorkspaceButton(Workspace.InvestorWorkspace, 30), "Build Investor workspace button", action.BOOLEAN)){
								if(click(driver, fp.getStep1Of3CrossIcon(Workspace.InvestorWorkspace, 30), "Cross icon", action.BOOLEAN)){
									ThreadSleep(1500);
									if(fp.getStep1Of3Header(Workspace.InvestorWorkspace, 30)==null){
										appLog.info("Pop is successfully closed after clicking on the Cross Icon.");
									} else {
										appLog.error("Pop is not closed after clicking on the Cross Icon.");
										sa.assertTrue(false,"Pop is not closed after clicking on the Cross Icon.");
										fp.recover(Workspace.InvestorWorkspace, 30);
									}
								} else {
									appLog.error("Cross icon cannot be clicked, So cannot check the cross icon functionality.");
									sa.assertTrue(false,"Cross icon cannot be clicked, So cannot check the cross icon functionality.");
									fp.recover(Workspace.InvestorWorkspace, 30);
								}
							} else {
								appLog.error("Cannot click on the build workspace button, So cannot check cross icon functionality.");
								sa.assertTrue(false,"Cannot click on the build workspace button, So cannot check cross icon functionality.");
							}
							
							if(click(driver, fp.getBuildWorkspaceButton(Workspace.InvestorWorkspace, 30), "Build Investor workspace button", action.BOOLEAN)){
								if(sendKeys(driver, fp.getFundVintageYearField(Workspace.InvestorWorkspace, 30), "Invalid Vintage Year", "Vintage year", action.BOOLEAN)){
									appLog.info("Successfully passed value to vintage year text box.");
								} else {
									appLog.error("Cannot pass value to vintage year text box, So cannot check invalid data message for vintage year field.");
									sa.assertTrue(false,"Cannot pass value to vintage year text box, So cannot check invalid data message for vintage year field.");
								}
								
								if(sendKeys(driver, fp.getFundSizeField(Workspace.InvestorWorkspace, 30), "Invalid fund size", "Fund Size", action.BOOLEAN)){
									appLog.info("Successfully passed value to Fund size text box.");
								} else {
									appLog.error("Cannot pass value to Fund size text box, So cannot check invalid data message for Fund size field.");
									sa.assertTrue(false,"Cannot pass value to Fund size text box, So cannot check invalid data message for Fund size field.");
								}
								
								if(sendKeys(driver, fp.getFundContactField(Workspace.InvestorWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
									appLog.info("Successfully passed value to Fund contact text box.");
								} else {
									appLog.error("Cannot pass value to Fund contact text box, So cannot check invalid data message for Fund contact field.");
									sa.assertTrue(false,"Cannot pass value to Fund contact text box, So cannot check invalid data message for Fund contact field.");
								}
								
								if(sendKeys(driver, fp.getFundEmailField(Workspace.InvestorWorkspace, 30), "Invalid Email", "Fund Email", action.BOOLEAN)){
									appLog.info("Successfully passed value to Fund Email text box.");
								} else {
									appLog.error("Cannot pass value to Fund Email text box, So cannot check invalid data message for Fund Email field.");
									sa.assertTrue(false,"Cannot pass value to Fund Email text box, So cannot check invalid data message for Fund Email field.");
								}
								
								if(sendKeys(driver, fp.getFundPhoneField(Workspace.InvestorWorkspace, 30), "Invalid Phone", "Fund Phone", action.BOOLEAN)){
									appLog.info("Successfully passed value to Fund Phone text box.");
								} else {
									appLog.error("Cannot pass value to Fund Phone text box, So cannot check invalid data message for Fund Phone field.");
									sa.assertTrue(false,"Cannot pass value to Fund Phone text box, So cannot check invalid data message for Fund Phone field.");
								}
								
								if(sendKeys(driver, fp.getFundDescriptionField(Workspace.InvestorWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
									appLog.info("Successfully passed value to Fund Description text box.");
								} else {
									appLog.error("Cannot pass value to Fund Description text box, So cannot check invalid data message for Fund Description field.");
									sa.assertTrue(false,"Cannot pass value to Fund Description text box, So cannot check invalid data message for Fund Description field.");
								}
								
								if(click(driver, fp.getNext1Of3Button(Workspace.InvestorWorkspace, 30), "Next Button", action.BOOLEAN)){
									text = getText(driver, fp.getFundSizeFieldErrorMessage(Workspace.InvestorWorkspace, 0, 30), "Fund Size Error Message", action.BOOLEAN);
									if(text.equalsIgnoreCase(FundsPageErrorMessage.InvalidFundSizeErrorMessage)){
										appLog.info("Fund size field Error message is verified.");
									} else {
										appLog.error("Fund size field Error message is not verified. Expected: "+FundsPageErrorMessage.InvalidFundSizeErrorMessage+"\tActual: "+text);
										sa.assertTrue(false,"Fund size field Error message is not verified. Expected: "+FundsPageErrorMessage.InvalidFundSizeErrorMessage+"\tActual: "+text);
									}
									
									text = getText(driver, fp.getFundVintageYearFieldErrorMessage(Workspace.InvestorWorkspace, 1, 30), "Fund Contact Error Message", action.BOOLEAN);
									if(text.equalsIgnoreCase(FundsPageErrorMessage.InvalidFundVintageYearErrorMessage)){
										appLog.info("Fund vintage year field Error message is verified.");
									} else {
										appLog.error("Fund vintage year field Error message is not verified. Expected: "+FundsPageErrorMessage.InvalidFundVintageYearErrorMessage+"\tActual: "+text);
										sa.assertTrue(false,"Fund vintage year field Error message is not verified. Expected: "+FundsPageErrorMessage.InvalidFundVintageYearErrorMessage+"\tActual: "+text);
									}
									
									text = getText(driver, fp.getFundPhoneFieldErrorMessage(Workspace.InvestorWorkspace, 2, 30), "Fund Phone Error Message", action.BOOLEAN);
									if(text.equalsIgnoreCase(FundsPageErrorMessage.InvalidFundPhoneErrorMessage)){
										appLog.info("Fund Phone field Error message is verified.");
									} else {
										appLog.error("Fund Phone field Error message is not verified. Expected: "+FundsPageErrorMessage.InvalidFundPhoneErrorMessage+"\tActual: "+text);
										sa.assertTrue(false,"Fund Phone field Error message is not verified. Expected: "+FundsPageErrorMessage.InvalidFundPhoneErrorMessage+"\tActual: "+text);
									}
									
									text = getText(driver, fp.getFundEmailFieldErrorMessage(Workspace.InvestorWorkspace, 1, 30), "Fund Email Error Message", action.BOOLEAN);
									if(text.equalsIgnoreCase(FundsPageErrorMessage.InvalidFundEmailErrorMessage)){
										appLog.info("Fund Email field Error message is verified.");
									} else {
										appLog.error("Fund Email field Error message is not verified. Expected: "+FundsPageErrorMessage.InvalidFundEmailErrorMessage+"\tActual: "+text);
										sa.assertTrue(false,"Fund Email field Error message is not verified. Expected: "+FundsPageErrorMessage.InvalidFundEmailErrorMessage+"\tActual: "+text);
									}
								} else {
									appLog.error("Next Button cannot be clikced, So cannot check invalid data error message for any field.");
									sa.assertTrue(false,"Next Button cannot be clikced, So cannot check invalid data error message for any field.");
								}
								
							} else {
								appLog.error("Cannot click on the build workspace button, So cannot continue check cross button functionality.");
								sa.assertTrue(false,"Cannot click on the build workspace button, So cannot continue check cross button functionality.");
							}
						} else {
							appLog.error("Next Button cannot be clicked, So cannot check the error message.");
							sa.assertTrue(false,"Next Button cannot be clicked, So cannot check the error message.");
						}
					} else {
						appLog.error("not able to clear fund name text box, So cannot check the error message.");
						sa.assertTrue(false,"not able to clear fund name text box, So cannot check the error message.");
					}
					
				} else {
					appLog.error("Build Investor workspace button cannot be clicked, So cannot verify step 1 of 3.");
					sa.assertTrue(false,"Build Investor workspace button cannot be clicked, So cannot verify step 1 of 3.");
				}
			} else {
				appLog.error(M8F1+" fund is not present in the list, so cannot verify step 1 of 3.");
				sa.assertTrue(false,M8F1+" fund is not present in the list, so cannot verify step 1 of 3.");
			}
		} else {
			appLog.error("Funds Tab cannot be clicked, So cannot verify step 1 of 3 of workspace building process.");
			sa.assertTrue(false,"Funds Tab cannot be clicked, So cannot verify step 1 of 3 of workspace building process.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M8tc004_VerifyBuildWorkspaceStep2Of3(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		boolean flag = true;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F1)){
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Funcraising wokspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 30), "Buld fundraising workspace button", action.BOOLEAN)){
					if(sendKeys(driver, fp.getFundVintageYearField(Workspace.FundraisingWorkspace, 30), "2013", "Vintage year", action.BOOLEAN)){
						appLog.info("Successfully passed value to vintage year text box.");
					} else {
						appLog.error("Cannot pass value to vintage year text box.");
						sa.assertTrue(false,"Cannot pass value to vintage year text box.");
					}
					
					if(sendKeys(driver, fp.getFundSizeField(Workspace.FundraisingWorkspace, 30), "5000", "Fund Size", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund size text box.");
					} else {
						appLog.error("Cannot pass value to Fund size text box.");
						sa.assertTrue(false,"Cannot pass value to Fund size text box.");
					}
					
					if(sendKeys(driver, fp.getFundContactField(Workspace.FundraisingWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund contact text box.");
					} else {
						appLog.error("Cannot pass value to Fund contact text box.");
						sa.assertTrue(false,"Cannot pass value to Fund contact text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundEmailField(Workspace.FundraisingWorkspace, 30), "abc@abc.com", "Fund Email", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Email text box.");
					} else {
						appLog.error("Cannot pass value to Fund Email text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Email text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundPhoneField(Workspace.FundraisingWorkspace, 30), "987654321", "Fund Phone", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Phone text box.");
					} else {
						appLog.error("Cannot pass value to Fund Phone text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Phone text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundDescriptionField(Workspace.FundraisingWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Description text box.");
					} else {
						appLog.error("Cannot pass value to Fund Description text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Description text box.");
					}
					
					if(flag){
						if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 30), "Next button of step 1 of 3", action.BOOLEAN)){
							if(fp.getStep2Of3Header(Workspace.FundraisingWorkspace, 30)!=null){
								appLog.info("Step 2 of 3 pop up is successfully opened.");
								String text = getText(driver, fp.getStep2Of3YellowBoxText(Workspace.FundraisingWorkspace, 30), "", action.BOOLEAN);
								if(text.trim().equalsIgnoreCase(FundsPageErrorMessage.Step2Of3YellowBoxText.trim())){
									appLog.info("Yellow box message is verified.");
								} else {
									appLog.error("Yellow box message is not verified. Expected: "+FundsPageErrorMessage.Step2Of3YellowBoxText+"\tActual: "+text);
									sa.assertTrue(false,"Yellow box message is not verified. Expected: "+FundsPageErrorMessage.Step2Of3YellowBoxText+"\tActual: "+text);
								}
								if(fp.getImportFolderTemplateButton(Workspace.FundraisingWorkspace, EnableDisable.Disable, 30)!=null){
									appLog.info("Import Folder Template button is present and is disabled.");
								} else {
									appLog.error("Import Folder Template Button is not present or is enabled.");
									sa.assertTrue(false,"Import Folder Template Button is not present or is enabled.");
								}
								
								if(fp.getClearAllFolderButton(Workspace.FundraisingWorkspace, EnableDisable.Disable, 30)!=null){
									appLog.info("Clear All Folder button is present and is disabled.");
								} else {
									appLog.error("Clear All Folder Button is not present or is enabled.");
									sa.assertTrue(false,"Clear All Folder Button is not present or is enabled.");
								}
								
								if(fp.getAllFolderAddAFolderButton(Workspace.FundraisingWorkspace, 30)!=null){
									appLog.info("All Folder Label and Add a Folder icon is verified.");
								} else {
									appLog.error("All Folder Label or Add Folder Butto is not present, Kinldy check screenshot for more info.");
									sa.assertTrue(false,"All Folder Label or Add Folder Butto is not present, Kinldy check screenshot for more info.");
								}
								
								text = getText(driver, fp.getPopUpButtomMessage(Workspace.FundraisingWorkspace, 30), "", action.BOOLEAN);
								if(text.trim().equalsIgnoreCase(FundsPageErrorMessage.Step2Of3PopUpBottomMessage.trim())){
									appLog.info("Step 2 Of 3 Pop Up Bottom Message is verified.");
								} else {
									appLog.error("Step 2 Of 3 Pop Up Bottom message is not verified. expected: "+FundsPageErrorMessage.Step2Of3PopUpBottomMessage+"\tActual: "+text);
									sa.assertTrue(false,"Step 2 Of 3 Pop Up Bottom message is not verified. expected: "+FundsPageErrorMessage.Step2Of3PopUpBottomMessage+"\tActual: "+text);
								}
								
								if(fp.getStep2Of3CancelButton(Workspace.FundraisingWorkspace, 30)!=null){
									appLog.info("Step 2 Of 3 Cancel Button is verified.");
								} else {
									appLog.error("Step 2 Of 3 Cancel Button is not present.");
									sa.assertTrue(false,"Step 2 Of 3 Cancel Button is not present.");
								}
								
								if(fp.getNext2Of3Button(Workspace.FundraisingWorkspace, 30)!=null){
									appLog.info("Next Button is verified.");
								} else {
									appLog.error("Next Button is not present on the pop up.");
									sa.assertTrue(false,"Next Button is not present on the pop up.");
								}
								
								if(fp.getStep2Of3CrossIcon(Workspace.FundraisingWorkspace, 30)!=null){
									appLog.info("Cross icon is verified.");
								} else {
									appLog.error("Cross icon is not present on the pop up.");
									sa.assertTrue(false,"Cross icon is not present on the pop up.");
								}
								if(click(driver, fp.getStep2Of3CancelButton(Workspace.FundraisingWorkspace, 30), "cancel button", action.BOOLEAN)){
									ThreadSleep(1500);
									if(fp.getStep2Of3Header(Workspace.FundraisingWorkspace, 30)==null){
										appLog.info("Pop Up is closed succesfully after clicking on cancel button.");
									} else {
										appLog.error("Pop Up is not closed after clicking on cancel button.");
										sa.assertTrue(false,"Pop Up is not closed after clicking on cancel button.");
										fp.recover(Workspace.FundraisingWorkspace, 30);
									}
									
								} else {
									appLog.error("cancel button cannot be clikced, So cannot check cancel button functionality.");
									sa.assertTrue(false,"cancel button cannot be clikced, So cannot check cancel button functionality.");
									fp.recover(Workspace.FundraisingWorkspace, 30);
								}
							} else {
								appLog.error("Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
								sa.assertTrue(false,"Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
								fp.recover(Workspace.FundraisingWorkspace, 30);
							}
							
						} else {
							appLog.error("Next button of step 1 of 3 cannot be click, So cannot check cancel button functionality..");
							sa.assertTrue(false,"Next button of step 1 of 3 cannot be click, So cannot check cancel button functionality.");
							fp.recover(Workspace.FundraisingWorkspace, 30);
						}
					} else {
						appLog.error("One of the mandatory field is not filled, So cannot check cancel button functionality.");
						sa.assertTrue(false,"One of the mandatory field is not filled, So cannot check cancel button functionality.");
						fp.recover(Workspace.FundraisingWorkspace, 30);
					}
				} else {
					appLog.error("Build fundraising workspace button cannot be clicked, So cannot check cancel button functionality.");
					sa.assertTrue(false,"Build fundraising workspace button cannot be clicked, So cannot check cancel button functionality.");
					fp.recover(Workspace.FundraisingWorkspace, 30);
				}
				
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 30), "Buld fundraising workspace button", action.BOOLEAN)){
					flag = true;
					String text = getAttribute(driver, fp.getFundNameField(Workspace.FundraisingWorkspace, 30), "", "value");
					if(text.trim().equalsIgnoreCase(M8F1)){
						appLog.info("Fund Name field is pre filled.");
					} else {
						appLog.error("Fund name field is not prefilled. Expected: "+M8F1+"\tActual: "+text);
						sa.assertTrue(false,"Fund name field is not prefilled. Expected: "+M8F1+"\tActual: "+text);
					}
					
					text = getAttribute(driver, fp.getFundContactField(Workspace.FundraisingWorkspace, 30), "", "title");
					if(text.isEmpty()){
						appLog.info("Fund contact Name field is Blank and verified.");
					} else {
						appLog.error("Fund contact name field is not Blank. Expected: blank\tActual: "+text);
						sa.assertTrue(false,"Fund contact name field is not Blank. Expected: blank\tActual: "+text);
					}
					
					text = getAttribute(driver, fp.getFundPhoneField(Workspace.FundraisingWorkspace, 30), "", "title");
					if(text.isEmpty()){
						appLog.info("Fund Phone field is Blank and verified.");
					} else {
						appLog.error("Fund Phone field is not Blank. Expected: blank\tActual: "+text);
						sa.assertTrue(false,"Fund Phone field is not Blank. Expected: blank\tActual: "+text);
					}
					
					text = getAttribute(driver, fp.getFundEmailField(Workspace.FundraisingWorkspace, 30), "", "title");
					if(text.isEmpty()){
						appLog.info("Fund Email field is Blank and verified.");
					} else {
						appLog.error("Fund Email field is not Blank. Expected: blank\tActual: "+text);
						sa.assertTrue(false,"Fund Email Field is not Blank. Expected: blank\tActual: "+text);
					}
					if(sendKeys(driver, fp.getFundVintageYearField(Workspace.FundraisingWorkspace, 30), "2013", "Vintage year", action.BOOLEAN)){
						appLog.info("Successfully passed value to vintage year text box.");
					} else {
						appLog.error("Cannot pass value to vintage year text box.");
						sa.assertTrue(false,"Cannot pass value to vintage year text box.");
					}
					
					if(sendKeys(driver, fp.getFundSizeField(Workspace.FundraisingWorkspace, 30), "5000", "Fund Size", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund size text box.");
					} else {
						appLog.error("Cannot pass value to Fund size text box.");
						sa.assertTrue(false,"Cannot pass value to Fund size text box.");
					}
					
					if(sendKeys(driver, fp.getFundContactField(Workspace.FundraisingWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund contact text box.");
					} else {
						appLog.error("Cannot pass value to Fund contact text box.");
						sa.assertTrue(false,"Cannot pass value to Fund contact text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundEmailField(Workspace.FundraisingWorkspace, 30), "abc@abc.com", "Fund Email", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Email text box.");
					} else {
						appLog.error("Cannot pass value to Fund Email text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Email text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundPhoneField(Workspace.FundraisingWorkspace, 30), "987654321", "Fund Phone", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Phone text box.");
					} else {
						appLog.error("Cannot pass value to Fund Phone text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Phone text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundDescriptionField(Workspace.FundraisingWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Description text box.");
					} else {
						appLog.error("Cannot pass value to Fund Description text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Description text box.");
					}
					
					if(flag){
						if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 30), "Next button of step 1 of 3", action.BOOLEAN)){
							String folderToBeCreated = "Standard Parent Folder";
							if(fp.createParentFolder(folderToBeCreated, FolderType.Standard, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
								appLog.info("Folder Successfully Created.");
							} else {
								appLog.error("Folder Cannot be created.");
								sa.assertTrue(false,"Folder Cannot be created.");
							}
							WebElement ele = FindElement(driver, "//span[@title='Add a Folder'][contains(@onclick,'AddFirstLevelFolderBWFR')]/../../../following-sibling::ul//label[contains(text(),'"+ folderToBeCreated +"')]", "Folder", action.BOOLEAN, 30);
							if(ele!=null){
								appLog.info(folderToBeCreated+" is successfully created and is verified.");
							} else {
								appLog.error(folderToBeCreated+" is not created.");
								sa.assertTrue(false,folderToBeCreated+" is not created.");
							}
							
							if(click(driver, fp.getStep2Of3CrossIcon(Workspace.FundraisingWorkspace, 30), "Cross Icon button", action.BOOLEAN)){
								ThreadSleep(1500);
								if(fp.getStep2Of3Header(Workspace.FundraisingWorkspace, 30)==null){
									appLog.info("Pop Up is closed succesfully after clicking on Cross Icon.");
								} else {
									appLog.error("Pop Up is not closed after clicking on Cross Icon.");
									sa.assertTrue(false,"Pop Up is not closed after clicking on Cross Icon.");
									fp.recover(Workspace.FundraisingWorkspace, 30);
								}
								
							} else {
								appLog.error("cancel button cannot be clikced, So cannot check Cross Icon functionality.");
								sa.assertTrue(false,"cancel button cannot be clikced, So cannot check Cross Icon functionality.");
								fp.recover(Workspace.FundraisingWorkspace, 30);
							}
						} else {
							appLog.error("Next button of step 1 of 3 cannot be click, So cannot check Cross Icon functionality..");
							sa.assertTrue(false,"Next button of step 1 of 3 cannot be click, So cannot check Cross Icon functionality.");
							fp.recover(Workspace.FundraisingWorkspace, 30);
						}
					} else {
						appLog.error("One of the mandatory field is not filled, So cannot check Cross Icon functionality.");
						sa.assertTrue(false,"One of the mandatory field is not filled, So cannot check Cross Icon functionality.");
						fp.recover(Workspace.FundraisingWorkspace, 30);
					}
				} else {
					appLog.error("Build fundraising workspace button cannot be clicked, So cannot check Cross Icon functionality.");
					sa.assertTrue(false,"Build fundraising workspace button cannot be clicked, So cannot check Cross Icon functionality.");
					fp.recover(Workspace.FundraisingWorkspace, 30);
				}
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 30), "Buld fundraising workspace button", action.BOOLEAN)){
					if(sendKeys(driver, fp.getFundVintageYearField(Workspace.FundraisingWorkspace, 30), "2013", "Vintage year", action.BOOLEAN)){
						appLog.info("Successfully passed value to vintage year text box.");
					} else {
						appLog.error("Cannot pass value to vintage year text box.");
						sa.assertTrue(false,"Cannot pass value to vintage year text box.");
					}
					
					if(sendKeys(driver, fp.getFundSizeField(Workspace.FundraisingWorkspace, 30), "5000", "Fund Size", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund size text box.");
					} else {
						appLog.error("Cannot pass value to Fund size text box.");
						sa.assertTrue(false,"Cannot pass value to Fund size text box.");
					}
					
					if(sendKeys(driver, fp.getFundContactField(Workspace.FundraisingWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund contact text box.");
					} else {
						appLog.error("Cannot pass value to Fund contact text box.");
						sa.assertTrue(false,"Cannot pass value to Fund contact text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundEmailField(Workspace.FundraisingWorkspace, 30), "abc@abc.com", "Fund Email", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Email text box.");
					} else {
						appLog.error("Cannot pass value to Fund Email text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Email text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundPhoneField(Workspace.FundraisingWorkspace, 30), "987654321", "Fund Phone", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Phone text box.");
					} else {
						appLog.error("Cannot pass value to Fund Phone text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Phone text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundDescriptionField(Workspace.FundraisingWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Description text box.");
					} else {
						appLog.error("Cannot pass value to Fund Description text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Description text box.");
					}
					
					if(flag){
						if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 30), "Next button of step 1 of 3", action.BOOLEAN)){
							if(fp.getStep2Of3Header(Workspace.FundraisingWorkspace, 30)!=null){
								appLog.info("Step 2 of 3 pop up is successfully opened.");
								String text = getText(driver, fp.getStep2Of3YellowBoxText(Workspace.FundraisingWorkspace, 30), "", action.BOOLEAN);
								if(text.trim().equalsIgnoreCase(FundsPageErrorMessage.Step2Of3YellowBoxText.trim())){
									appLog.info("Yellow box message is verified.");
								} else {
									appLog.error("Yellow box message is not verified. Expected: "+FundsPageErrorMessage.Step2Of3YellowBoxText+"\tActual: "+text);
									sa.assertTrue(false,"Yellow box message is not verified. Expected: "+FundsPageErrorMessage.Step2Of3YellowBoxText+"\tActual: "+text);
								}
								if(fp.getImportFolderTemplateButton(Workspace.FundraisingWorkspace, EnableDisable.Disable, 30)!=null){
									appLog.info("Import Folder Template button is present and is disabled.");
								} else {
									appLog.error("Import Folder Template Button is not present or is enabled.");
									sa.assertTrue(false,"Import Folder Template Button is not present or is enabled.");
								}
								
								if(fp.getClearAllFolderButton(Workspace.FundraisingWorkspace, EnableDisable.Disable, 30)!=null){
									appLog.info("Clear All Folder button is present and is disabled.");
								} else {
									appLog.error("Clear All Folder Button is not present or is enabled.");
									sa.assertTrue(false,"Clear All Folder Button is not present or is enabled.");
								}
								
								if(fp.getAllFolderAddAFolderButton(Workspace.FundraisingWorkspace, 30)!=null){
									appLog.info("All Folder Label and Add a Folder icon is verified.");
								} else {
									appLog.error("All Folder Label or Add Folder Butto is not present, Kinldy check screenshot for more info.");
									sa.assertTrue(false,"All Folder Label or Add Folder Butto is not present, Kinldy check screenshot for more info.");
								}
								
								text = getText(driver, fp.getPopUpButtomMessage(Workspace.FundraisingWorkspace, 30), "", action.BOOLEAN);
								if(text.trim().equalsIgnoreCase(FundsPageErrorMessage.Step2Of3PopUpBottomMessage.trim())){
									appLog.info("Step 2 Of 3 Pop Up Bottom Message is verified.");
								} else {
									appLog.error("Step 2 Of 3 Pop Up Bottom message is not verified. expected: "+FundsPageErrorMessage.Step2Of3PopUpBottomMessage+"\tActual: "+text);
									sa.assertTrue(false,"Step 2 Of 3 Pop Up Bottom message is not verified. expected: "+FundsPageErrorMessage.Step2Of3PopUpBottomMessage+"\tActual: "+text);
								}
								
								if(fp.getStep2Of3CancelButton(Workspace.FundraisingWorkspace, 30)!=null){
									appLog.info("Step 2 Of 3 Cancel Button is verified.");
								} else {
									appLog.error("Step 2 Of 3 Cancel Button is not present.");
									sa.assertTrue(false,"Step 2 Of 3 Cancel Button is not present.");
								}
								
								if(fp.getNext2Of3Button(Workspace.FundraisingWorkspace, 30)!=null){
									appLog.info("Next Button is verified.");
								} else {
									appLog.error("Next Button is not present on the pop up.");
									sa.assertTrue(false,"Next Button is not present on the pop up.");
								}
								
								if(fp.getStep2Of3CrossIcon(Workspace.FundraisingWorkspace, 30)!=null){
									appLog.info("Cross icon is verified.");
								} else {
									appLog.error("Cross icon is not present on the pop up.");
									sa.assertTrue(false,"Cross icon is not present on the pop up.");
								}
							} else {
								appLog.error("Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI.");
								sa.assertTrue(false,"Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI.");
								fp.recover(Workspace.FundraisingWorkspace, 30);
							}
							
						} else {
							appLog.error("Next button of step 1 of 3 cannot be click, So cannot verify UI.");
							sa.assertTrue(false,"Next button of step 1 of 3 cannot be click, So cannot verify UI.");
							fp.recover(Workspace.FundraisingWorkspace, 30);
						}
					} else {
						appLog.error("One of the mandatory field is not filled, So cannot verify UI.");
						sa.assertTrue(false,"One of the mandatory field is not filled, So cannot verify UI.");
						fp.recover(Workspace.FundraisingWorkspace, 30);
					}
				} else {
					appLog.error("Build fundraising workspace button cannot be clicked, So cannot verify UI.");
					sa.assertTrue(false,"Build fundraising workspace button cannot be clicked, So cannot verify UI.");
					fp.recover(Workspace.FundraisingWorkspace, 30);
				}
			} else {
				appLog.error(M8F1+" fund is not available, so cannot continue with the tc.");
				sa.assertTrue(false,M8F1+" fund is not available, so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot continue with the tc.");
		}
		
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M8tc017_VerifyBuildWorkspaceStep2Of3InvestorWorkspace(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		boolean flag = true;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F4)){
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Funcraising wokspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.InvestorWorkspace, 30), "Buld Investor workspace button", action.BOOLEAN)){
					if(sendKeys(driver, fp.getFundVintageYearField(Workspace.InvestorWorkspace, 30), "2013", "Vintage year", action.BOOLEAN)){
						appLog.info("Successfully passed value to vintage year text box.");
					} else {
						appLog.error("Cannot pass value to vintage year text box.");
						sa.assertTrue(false,"Cannot pass value to vintage year text box.");
					}
					
					if(sendKeys(driver, fp.getFundSizeField(Workspace.InvestorWorkspace, 30), "5000", "Fund Size", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund size text box.");
					} else {
						appLog.error("Cannot pass value to Fund size text box.");
						sa.assertTrue(false,"Cannot pass value to Fund size text box.");
					}
					
					if(sendKeys(driver, fp.getFundContactField(Workspace.InvestorWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund contact text box.");
					} else {
						appLog.error("Cannot pass value to Fund contact text box.");
						sa.assertTrue(false,"Cannot pass value to Fund contact text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundEmailField(Workspace.InvestorWorkspace, 30), "abc@abc.com", "Fund Email", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Email text box.");
					} else {
						appLog.error("Cannot pass value to Fund Email text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Email text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundPhoneField(Workspace.InvestorWorkspace, 30), "987654321", "Fund Phone", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Phone text box.");
					} else {
						appLog.error("Cannot pass value to Fund Phone text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Phone text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundDescriptionField(Workspace.InvestorWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Description text box.");
					} else {
						appLog.error("Cannot pass value to Fund Description text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Description text box.");
					}
					
					if(flag){
						if(click(driver, fp.getNext1Of3Button(Workspace.InvestorWorkspace, 30), "Next button of step 1 of 3", action.BOOLEAN)){
							if(fp.getStep2Of3Header(Workspace.InvestorWorkspace, 30)!=null){
								appLog.info("Step 2 of 3 pop up is successfully opened.");
								String text = getText(driver, fp.getStep2Of3YellowBoxText(Workspace.InvestorWorkspace, 30), "", action.BOOLEAN);
								if(text.trim().equalsIgnoreCase(FundsPageErrorMessage.Step2Of3YellowBoxText.trim())){
									appLog.info("Yellow box message is verified.");
								} else {
									appLog.error("Yellow box message is not verified. Expected: "+FundsPageErrorMessage.Step2Of3YellowBoxText+"\tActual: "+text);
									sa.assertTrue(false,"Yellow box message is not verified. Expected: "+FundsPageErrorMessage.Step2Of3YellowBoxText+"\tActual: "+text);
								}
								if(fp.getClearAllFolderButton(Workspace.InvestorWorkspace, EnableDisable.Disable, 30)!=null){
									appLog.info("Clear All Folder button is present and is disabled.");
								} else {
									appLog.error("Clear All Folder Button is not present or is enabled.");
									sa.assertTrue(false,"Clear All Folder Button is not present or is enabled.");
								}
								
								if(fp.getAllFolderAddAFolderButton(Workspace.InvestorWorkspace, 30)!=null){
									appLog.info("All Folder Label and Add a Folder icon is verified.");
								} else {
									appLog.error("All Folder Label or Add Folder Butto is not present, Kinldy check screenshot for more info.");
									sa.assertTrue(false,"All Folder Label or Add Folder Butto is not present, Kinldy check screenshot for more info.");
								}
								
								text = getText(driver, fp.getPopUpButtomMessage(Workspace.InvestorWorkspace, 30), "", action.BOOLEAN);
								if(text.trim().equalsIgnoreCase(FundsPageErrorMessage.Step2Of3PopUpBottomMessage.trim())){
									appLog.info("Step 2 Of 3 Pop Up Bottom Message is verified.");
								} else {
									appLog.error("Step 2 Of 3 Pop Up Bottom message is not verified. expected: "+FundsPageErrorMessage.Step2Of3PopUpBottomMessage+"\tActual: "+text);
									sa.assertTrue(false,"Step 2 Of 3 Pop Up Bottom message is not verified. expected: "+FundsPageErrorMessage.Step2Of3PopUpBottomMessage+"\tActual: "+text);
								}
								
								if(fp.getStep2Of3CancelButton(Workspace.InvestorWorkspace, 30)!=null){
									appLog.info("Step 2 Of 3 Cancel Button is verified.");
								} else {
									appLog.error("Step 2 Of 3 Cancel Button is not present.");
									sa.assertTrue(false,"Step 2 Of 3 Cancel Button is not present.");
								}
								
								if(fp.getNext2Of3Button(Workspace.InvestorWorkspace, 30)!=null){
									appLog.info("Next Button is verified.");
								} else {
									appLog.error("Next Button is not present on the pop up.");
									sa.assertTrue(false,"Next Button is not present on the pop up.");
								}
								
								if(fp.getStep2Of3CrossIcon(Workspace.InvestorWorkspace, 30)!=null){
									appLog.info("Cross icon is verified.");
								} else {
									appLog.error("Cross icon is not present on the pop up.");
									sa.assertTrue(false,"Cross icon is not present on the pop up.");
								}
								if(click(driver, fp.getStep2Of3CancelButton(Workspace.InvestorWorkspace, 30), "cancel button", action.BOOLEAN)){
									ThreadSleep(1500);
									if(fp.getStep2Of3Header(Workspace.InvestorWorkspace, 30)==null){
										appLog.info("Pop Up is closed succesfully after clicking on cancel button.");
									} else {
										appLog.error("Pop Up is not closed after clicking on cancel button.");
										sa.assertTrue(false,"Pop Up is not closed after clicking on cancel button.");
										fp.recover(Workspace.InvestorWorkspace, 30);
									}
									
								} else {
									appLog.error("cancel button cannot be clikced, So cannot check cancel button functionality.");
									sa.assertTrue(false,"cancel button cannot be clikced, So cannot check cancel button functionality.");
									fp.recover(Workspace.InvestorWorkspace, 30);
								}
							} else {
								appLog.error("Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
								sa.assertTrue(false,"Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
								fp.recover(Workspace.InvestorWorkspace, 30);
							}
							
						} else {
							appLog.error("Next button of step 1 of 3 cannot be click, So cannot check cancel button functionality..");
							sa.assertTrue(false,"Next button of step 1 of 3 cannot be click, So cannot check cancel button functionality.");
							fp.recover(Workspace.InvestorWorkspace, 30);
						}
					} else {
						appLog.error("One of the mandatory field is not filled, So cannot check cancel button functionality.");
						sa.assertTrue(false,"One of the mandatory field is not filled, So cannot check cancel button functionality.");
						fp.recover(Workspace.InvestorWorkspace, 30);
					}
				} else {
					appLog.error("Build Investor workspace button cannot be clicked, So cannot check cancel button functionality.");
					sa.assertTrue(false,"Build Investor workspace button cannot be clicked, So cannot check cancel button functionality.");
					fp.recover(Workspace.InvestorWorkspace, 30);
				}
				
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.InvestorWorkspace, 30), "Buld Investor workspace button", action.BOOLEAN)){
					flag = true;
					String text = getAttribute(driver, fp.getFundNameField(Workspace.InvestorWorkspace, 30), "", "value");
					if(text.trim().equalsIgnoreCase(M8F4)){
						appLog.info("Fund Name field is pre filled.");
					} else {
						appLog.error("Fund name field is not prefilled. Expected: "+M8F4+"\tActual: "+text);
						sa.assertTrue(false,"Fund name field is not prefilled. Expected: "+M8F4+"\tActual: "+text);
					}
					
					text = getAttribute(driver, fp.getFundContactField(Workspace.InvestorWorkspace, 30), "", "title");
					if(text.isEmpty()){
						appLog.info("Fund contact Name field is Blank and verified.");
					} else {
						appLog.error("Fund contact name field is not Blank. Expected: blank\tActual: "+text);
						sa.assertTrue(false,"Fund contact name field is not Blank. Expected: blank\tActual: "+text);
					}
					
					text = getAttribute(driver, fp.getFundPhoneField(Workspace.InvestorWorkspace, 30), "", "title");
					if(text.isEmpty()){
						appLog.info("Fund Phone field is Blank and verified.");
					} else {
						appLog.error("Fund Phone field is not Blank. Expected: blank\tActual: "+text);
						sa.assertTrue(false,"Fund Phone field is not Blank. Expected: blank\tActual: "+text);
					}
					
					text = getAttribute(driver, fp.getFundEmailField(Workspace.InvestorWorkspace, 30), "", "title");
					if(text.isEmpty()){
						appLog.info("Fund Email field is Blank and verified.");
					} else {
						appLog.error("Fund Email field is not Blank. Expected: blank\tActual: "+text);
						sa.assertTrue(false,"Fund Email Field is not Blank. Expected: blank\tActual: "+text);
					}
					if(sendKeys(driver, fp.getFundVintageYearField(Workspace.InvestorWorkspace, 30), "2013", "Vintage year", action.BOOLEAN)){
						appLog.info("Successfully passed value to vintage year text box.");
					} else {
						appLog.error("Cannot pass value to vintage year text box.");
						sa.assertTrue(false,"Cannot pass value to vintage year text box.");
					}
					
					if(sendKeys(driver, fp.getFundSizeField(Workspace.InvestorWorkspace, 30), "5000", "Fund Size", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund size text box.");
					} else {
						appLog.error("Cannot pass value to Fund size text box.");
						sa.assertTrue(false,"Cannot pass value to Fund size text box.");
					}
					
					if(sendKeys(driver, fp.getFundContactField(Workspace.InvestorWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund contact text box.");
					} else {
						appLog.error("Cannot pass value to Fund contact text box.");
						sa.assertTrue(false,"Cannot pass value to Fund contact text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundEmailField(Workspace.InvestorWorkspace, 30), "abc@abc.com", "Fund Email", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Email text box.");
					} else {
						appLog.error("Cannot pass value to Fund Email text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Email text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundPhoneField(Workspace.InvestorWorkspace, 30), "987654321", "Fund Phone", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Phone text box.");
					} else {
						appLog.error("Cannot pass value to Fund Phone text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Phone text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundDescriptionField(Workspace.InvestorWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Description text box.");
					} else {
						appLog.error("Cannot pass value to Fund Description text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Description text box.");
					}
					
					if(flag){
						if(click(driver, fp.getNext1Of3Button(Workspace.InvestorWorkspace, 30), "Next button of step 1 of 3", action.BOOLEAN)){
							String folderToBeCreated = "Standard Parent Folder";
							if(fp.createParentFolder(folderToBeCreated, FolderType.Standard, PageName.FundsPage, Workspace.InvestorWorkspace, 30)){
								appLog.info("Folder Successfully Created.");
							} else {
								appLog.error("Folder Cannot be created.");
								sa.assertTrue(false,"Folder Cannot be created.");
							}
							WebElement ele = FindElement(driver, "//span[@title='Add a Folder'][contains(@onclick,'AddFirstLevelFolderBWINV')]/../../../following-sibling::ul//label[contains(text(),'"+ folderToBeCreated +"')]", "Folder", action.BOOLEAN, 30);
							if(ele!=null){
								appLog.info(folderToBeCreated+" is successfully created and is verified.");
							} else {
								appLog.error(folderToBeCreated+" is not created.");
								sa.assertTrue(false,folderToBeCreated+" is not created.");
							}
							
							if(click(driver, fp.getStep2Of3CrossIcon(Workspace.InvestorWorkspace, 30), "Cross Icon button", action.BOOLEAN)){
								ThreadSleep(1500);
								if(fp.getStep2Of3Header(Workspace.InvestorWorkspace, 30)==null){
									appLog.info("Pop Up is closed succesfully after clicking on Cross Icon.");
								} else {
									appLog.error("Pop Up is not closed after clicking on Cross Icon.");
									sa.assertTrue(false,"Pop Up is not closed after clicking on Cross Icon.");
									fp.recover(Workspace.InvestorWorkspace, 30);
								}
								
							} else {
								appLog.error("cancel button cannot be clikced, So cannot check Cross Icon functionality.");
								sa.assertTrue(false,"cancel button cannot be clikced, So cannot check Cross Icon functionality.");
								fp.recover(Workspace.InvestorWorkspace, 30);
							}
						} else {
							appLog.error("Next button of step 1 of 3 cannot be click, So cannot check Cross Icon functionality..");
							sa.assertTrue(false,"Next button of step 1 of 3 cannot be click, So cannot check Cross Icon functionality.");
							fp.recover(Workspace.InvestorWorkspace, 30);
						}
					} else {
						appLog.error("One of the mandatory field is not filled, So cannot check Cross Icon functionality.");
						sa.assertTrue(false,"One of the mandatory field is not filled, So cannot check Cross Icon functionality.");
						fp.recover(Workspace.InvestorWorkspace, 30);
					}
				} else {
					appLog.error("Build Investor workspace button cannot be clicked, So cannot check Cross Icon functionality.");
					sa.assertTrue(false,"Build Investor workspace button cannot be clicked, So cannot check Cross Icon functionality.");
					fp.recover(Workspace.InvestorWorkspace, 30);
				}
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.InvestorWorkspace, 30), "Buld Investor workspace button", action.BOOLEAN)){
					if(sendKeys(driver, fp.getFundVintageYearField(Workspace.InvestorWorkspace, 30), "2013", "Vintage year", action.BOOLEAN)){
						appLog.info("Successfully passed value to vintage year text box.");
					} else {
						appLog.error("Cannot pass value to vintage year text box.");
						sa.assertTrue(false,"Cannot pass value to vintage year text box.");
					}
					
					if(sendKeys(driver, fp.getFundSizeField(Workspace.InvestorWorkspace, 30), "5000", "Fund Size", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund size text box.");
					} else {
						appLog.error("Cannot pass value to Fund size text box.");
						sa.assertTrue(false,"Cannot pass value to Fund size text box.");
					}
					
					if(sendKeys(driver, fp.getFundContactField(Workspace.InvestorWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund contact text box.");
					} else {
						appLog.error("Cannot pass value to Fund contact text box.");
						sa.assertTrue(false,"Cannot pass value to Fund contact text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundEmailField(Workspace.InvestorWorkspace, 30), "abc@abc.com", "Fund Email", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Email text box.");
					} else {
						appLog.error("Cannot pass value to Fund Email text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Email text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundPhoneField(Workspace.InvestorWorkspace, 30), "987654321", "Fund Phone", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Phone text box.");
					} else {
						appLog.error("Cannot pass value to Fund Phone text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Phone text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundDescriptionField(Workspace.InvestorWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Description text box.");
					} else {
						appLog.error("Cannot pass value to Fund Description text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Description text box.");
					}
					
					if(flag){
						if(click(driver, fp.getNext1Of3Button(Workspace.InvestorWorkspace, 30), "Next button of step 1 of 3", action.BOOLEAN)){
							if(fp.getStep2Of3Header(Workspace.InvestorWorkspace, 30)!=null){
								appLog.info("Step 2 of 3 pop up is successfully opened.");
								String text = getText(driver, fp.getStep2Of3YellowBoxText(Workspace.InvestorWorkspace, 30), "", action.BOOLEAN);
								if(text.trim().equalsIgnoreCase(FundsPageErrorMessage.Step2Of3YellowBoxText.trim())){
									appLog.info("Yellow box message is verified.");
								} else {
									appLog.error("Yellow box message is not verified. Expected: "+FundsPageErrorMessage.Step2Of3YellowBoxText+"\tActual: "+text);
									sa.assertTrue(false,"Yellow box message is not verified. Expected: "+FundsPageErrorMessage.Step2Of3YellowBoxText+"\tActual: "+text);
								}
								
								if(fp.getClearAllFolderButton(Workspace.InvestorWorkspace, EnableDisable.Disable, 30)!=null){
									appLog.info("Clear All Folder button is present and is disabled.");
								} else {
									appLog.error("Clear All Folder Button is not present or is enabled.");
									sa.assertTrue(false,"Clear All Folder Button is not present or is enabled.");
								}
								
								if(fp.getAllFolderAddAFolderButton(Workspace.InvestorWorkspace, 30)!=null){
									appLog.info("All Folder Label and Add a Folder icon is verified.");
								} else {
									appLog.error("All Folder Label or Add Folder Butto is not present, Kinldy check screenshot for more info.");
									sa.assertTrue(false,"All Folder Label or Add Folder Butto is not present, Kinldy check screenshot for more info.");
								}
								
								text = getText(driver, fp.getPopUpButtomMessage(Workspace.InvestorWorkspace, 30), "", action.BOOLEAN);
								if(text.trim().equalsIgnoreCase(FundsPageErrorMessage.Step2Of3PopUpBottomMessage.trim())){
									appLog.info("Step 2 Of 3 Pop Up Bottom Message is verified.");
								} else {
									appLog.error("Step 2 Of 3 Pop Up Bottom message is not verified. expected: "+FundsPageErrorMessage.Step2Of3PopUpBottomMessage+"\tActual: "+text);
									sa.assertTrue(false,"Step 2 Of 3 Pop Up Bottom message is not verified. expected: "+FundsPageErrorMessage.Step2Of3PopUpBottomMessage+"\tActual: "+text);
								}
								
								if(fp.getStep2Of3CancelButton(Workspace.InvestorWorkspace, 30)!=null){
									appLog.info("Step 2 Of 3 Cancel Button is verified.");
								} else {
									appLog.error("Step 2 Of 3 Cancel Button is not present.");
									sa.assertTrue(false,"Step 2 Of 3 Cancel Button is not present.");
								}
								
								if(fp.getNext2Of3Button(Workspace.InvestorWorkspace, 30)!=null){
									appLog.info("Next Button is verified.");
								} else {
									appLog.error("Next Button is not present on the pop up.");
									sa.assertTrue(false,"Next Button is not present on the pop up.");
								}
								
								if(fp.getStep2Of3CrossIcon(Workspace.InvestorWorkspace, 30)!=null){
									appLog.info("Cross icon is verified.");
								} else {
									appLog.error("Cross icon is not present on the pop up.");
									sa.assertTrue(false,"Cross icon is not present on the pop up.");
								}
							} else {
								appLog.error("Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI.");
								sa.assertTrue(false,"Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI.");
								fp.recover(Workspace.InvestorWorkspace, 30);
							}
							
						} else {
							appLog.error("Next button of step 1 of 3 cannot be click, So cannot verify UI.");
							sa.assertTrue(false,"Next button of step 1 of 3 cannot be click, So cannot verify UI.");
							fp.recover(Workspace.InvestorWorkspace, 30);
						}
					} else {
						appLog.error("One of the mandatory field is not filled, So cannot verify UI.");
						sa.assertTrue(false,"One of the mandatory field is not filled, So cannot verify UI.");
						fp.recover(Workspace.InvestorWorkspace, 30);
					}
				} else {
					appLog.error("Build Investor workspace button cannot be clicked, So cannot verify UI.");
					sa.assertTrue(false,"Build Investor workspace button cannot be clicked, So cannot verify UI.");
					fp.recover(Workspace.InvestorWorkspace, 30);
				}
			} else {
				appLog.error(M8F4+" fund is not available, so cannot continue with the tc.");
				sa.assertTrue(false,M8F4+" fund is not available, so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot continue with the tc.");
		}
		
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M8tc005_VerifyBuildStep3Of3(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		boolean flag = true;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F1)){
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Funcraising wokspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 30), "Buld fundraising workspace button", action.BOOLEAN)){
					if(sendKeys(driver, fp.getFundVintageYearField(Workspace.FundraisingWorkspace, 30), "2013", "Vintage year", action.BOOLEAN)){
						appLog.info("Successfully passed value to vintage year text box.");
					} else {
						appLog.error("Cannot pass value to vintage year text box.");
						sa.assertTrue(false,"Cannot pass value to vintage year text box.");
					}
					
					if(sendKeys(driver, fp.getFundSizeField(Workspace.FundraisingWorkspace, 30), "5000", "Fund Size", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund size text box.");
					} else {
						appLog.error("Cannot pass value to Fund size text box.");
						sa.assertTrue(false,"Cannot pass value to Fund size text box.");
					}
					
					if(sendKeys(driver, fp.getFundContactField(Workspace.FundraisingWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund contact text box.");
					} else {
						appLog.error("Cannot pass value to Fund contact text box.");
						sa.assertTrue(false,"Cannot pass value to Fund contact text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundEmailField(Workspace.FundraisingWorkspace, 30), "abc@abc.com", "Fund Email", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Email text box.");
					} else {
						appLog.error("Cannot pass value to Fund Email text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Email text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundPhoneField(Workspace.FundraisingWorkspace, 30), "987654321", "Fund Phone", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Phone text box.");
					} else {
						appLog.error("Cannot pass value to Fund Phone text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Phone text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundDescriptionField(Workspace.FundraisingWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Description text box.");
					} else {
						appLog.error("Cannot pass value to Fund Description text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Description text box.");
					}
					
					if(flag){
						if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 30), "Next button of step 1 of 3", action.BOOLEAN)){
							if(fp.getStep2Of3Header(Workspace.FundraisingWorkspace, 30)!=null){
								appLog.info("Step 2 of 3 pop up is successfully opened.");
								String folderToBeCreated = "Standard Parent Folder";
								if(fp.createParentFolder(folderToBeCreated, FolderType.Standard, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
									appLog.info("Folder Successfully Created.");
								} else {
									appLog.error("Folder Cannot be created.");
									sa.assertTrue(false,"Folder Cannot be created.");
								}
								WebElement ele = FindElement(driver, "//span[@title='Add a Folder'][contains(@onclick,'AddFirstLevelFolderBWFR')]/../../../following-sibling::ul//label[contains(text(),'"+ folderToBeCreated +"')]", "Folder", action.BOOLEAN, 30);
								if(ele!=null){
									appLog.info(folderToBeCreated+" is successfully created and is verified.");
								} else {
									appLog.error(folderToBeCreated+" is not created.");
									sa.assertTrue(false,folderToBeCreated+" is not created.");
								}
								if(click(driver, fp.getNext2Of3Button(Workspace.FundraisingWorkspace, 30), "Next Button", action.BOOLEAN)){
									if(fp.getStep3Of3Header(Workspace.FundraisingWorkspace, 30)!=null){
										String text = getText(driver, fp.getStep3Of3YellowBoxText(Workspace.FundraisingWorkspace, 30), "", action.BOOLEAN);
										if(text.trim().equalsIgnoreCase(FundsPageErrorMessage.Step3Of3YellowBoxText.trim())){
											appLog.info("Step 3 Of 3 yellow box text is verified.");
										} else {
											appLog.error("Step 3 Of 3 yellow box text is not verified. Expected: "+FundsPageErrorMessage.Step3Of3YellowBoxText+"\tActual: "+text);
											sa.assertTrue(false,"Step 3 Of 3 yellow box text is not verified. Expected: "+FundsPageErrorMessage.Step3Of3YellowBoxText+"\tActual: "+text);
										}
										if(fp.getInvestorFilterExpandOrCollapseIcon(Workspace.FundraisingWorkspace, ExpandCollapse.Expand, 30)!=null){
											appLog.info("Investor Filter section is in Expanded state and is verified.");
										} else {
											appLog.error("Investor Filter section is in collapsed state.");
											sa.assertTrue(false,"Investor Filter section is in collapsed state.");
										}
										
										text = getSelectedOptionOfDropDown(driver, fp.getStep3Of3FieldDropDown(Workspace.FundraisingWorkspace, 30), "Filter Field  drop down", "text");
										if(text.trim().equalsIgnoreCase("--None--")){
											appLog.info("--None-- is by default selected in the field drop down.");
										} else {
											appLog.error("--None-- is not selected in the field drop down. Expected: --None--\tActual: "+text);
											sa.assertTrue(false,"--None-- is not selected in the field drop down. Expected: --None--\tActual: "+text);
										}
										
										text = getSelectedOptionOfDropDown(driver, fp.getStep3Of3OperatorDropDown(Workspace.FundraisingWorkspace, 30), "Filter Field  drop down", "text");
										if(text.trim().equalsIgnoreCase("equals")){
											appLog.info("equals is by default selected in the operator drop down.");
										} else {
											appLog.error("equals is not selected in the operator drop down. Expected: equals\tActual: "+text);
											sa.assertTrue(false,"equals is not selected in the operator drop down. Expected: equals\tActual: "+text);
										}
										
										text = getAttribute(driver, fp.getStep3Of3FilterTextBox(Workspace.FundraisingWorkspace, 30), "", "value");
										if(text==null || text.isEmpty()){
											appLog.info("Criterion box is empty and verified.");
										} else {
											appLog.error("Criterion box is not empty.");
											sa.assertTrue(false,"Criterion box is not empty.");
										}
										
										if(fp.getStep3Of3AddRowLink(Workspace.FundraisingWorkspace, 30)!=null){
											appLog.info("Add row link is verified.");
										} else {
											appLog.error("Add row link is not present.");
											sa.assertTrue(false,"Add row link is not present.");
										}
										
										if(fp.getStep3Of3ApplyButton(Workspace.FundraisingWorkspace, 30)!=null){
											appLog.info("Apply button is verified.");
										} else {
											appLog.error("Apply button is not verified.");
											sa.assertTrue(false,"Apply button is not verified.");
										}
										
										if(fp.getStep3Of3ClearButton(Workspace.FundraisingWorkspace, 30)!=null){
											appLog.info("Clear button is verified.");
										} else {
											appLog.error("Clear button is not verified.");
											sa.assertTrue(false,"Clear button is not verified.");
										}
										
										text = getText(driver, fp.getStep3Of3NoDataToDisplayErrorMessage(30), "", action.BOOLEAN);
										if(text.trim().equalsIgnoreCase(FundsPageErrorMessage.Step3Of3NoDataToDisplayMessage.trim())){
											appLog.info("Step 3 Of 3 Error message is verified.");
										} else {
											appLog.error("Step 3 of 3 Error message is not verified. Expected: "+FundsPageErrorMessage.Step3Of3NoDataToDisplayMessage+"\tActual: "+text);
											sa.assertTrue(false,"Step 3 of 3 Error message is not verified. Expected: "+FundsPageErrorMessage.Step3Of3NoDataToDisplayMessage+"\tActual: "+text);
										}
										
										if(fp.getDone3Of3Button(Workspace.FundraisingWorkspace, 30)!=null){
											appLog.info("Done button is verified.");
										} else {
											appLog.error("Done button is not verified.");
											sa.assertTrue(false,"Done button is not verified.");
										}
										
										if(fp.getStep3Of3CrossIcon(Workspace.FundraisingWorkspace, 30)!=null){
											appLog.info("Step 3 of 3 cross icon is verified.");
										} else {
											appLog.error("Step 3 of 3 cross icon is not verified.");
											sa.assertTrue(false,"Step 3 of 3 cross icon is not verified.");
										}
										
										if(click(driver, fp.getStep3Of3CrossIcon(Workspace.FundraisingWorkspace, 30), "Cross icon", action.BOOLEAN)){
											ThreadSleep(1500);
											if(fp.getStep3Of3Header(Workspace.FundraisingWorkspace, 30)==null){
												appLog.info("Step 3 of 3 pop up is closed after clicking on the cross icon.");
												if(fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 30)!=null){
													appLog.info("Build Workspace button is visible after clicking on the cross icon and is verified.");
												} else {
													appLog.error("Build Workspace button is not visible after clicking on the cross icon and is not verified.");
													sa.assertTrue(false,"Build Workspace button is not visible after clicking on the cross icon and is not verified.");
												}
											} else {
												appLog.error("Step 3 of 3 pop up is not closed after clicking on the cross icon.");
												sa.assertTrue(false,"Step 3 of 3 pop up is not closed after clicking on the cross icon.");
											}
										} else {
											appLog.error("Cross icon cannot be clicked, SO cannot check cross icon functionality.");
											sa.assertTrue(false,"Cross icon cannot be clicked, SO cannot check cross icon functionality.");
										}
									} else {
										appLog.error("Step 3 Of 3 pop up is not opening after clicking on the next button of step 2 Of 3 pop up.");
										sa.assertTrue(false,"Step 3 Of 3 pop up is not opening after clicking on the next button of step 2 Of 3 pop up.");
									}
								} else {
									appLog.error("Next Button of step 2 Of 3 cannot be clikced, So cannot verify UI of step 3 Of 3.");
									sa.assertTrue(false,"Next Button of step 2 Of 3 cannot be clikced, So cannot verify UI of step 3 Of 3.");
									fp.recover(Workspace.FundraisingWorkspace, 30);
								}
							} else {
								appLog.error("Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
								sa.assertTrue(false,"Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
								fp.recover(Workspace.FundraisingWorkspace, 30);
							}
							
						} else {
							appLog.error("Next button of step 1 of 3 cannot be click, So cannot check cancel button functionality..");
							sa.assertTrue(false,"Next button of step 1 of 3 cannot be click, So cannot check cancel button functionality.");
							fp.recover(Workspace.FundraisingWorkspace, 30);
						}
					} else {
						appLog.error("One of the mandatory field is not filled, So cannot check cancel button functionality.");
						sa.assertTrue(false,"One of the mandatory field is not filled, So cannot check cancel button functionality.");
						fp.recover(Workspace.FundraisingWorkspace, 30);
					}
				} else {
					appLog.error("Build fundraising workspace button cannot be clicked, So cannot check cancel button functionality.");
					sa.assertTrue(false,"Build fundraising workspace button cannot be clicked, So cannot check cancel button functionality.");
					fp.recover(Workspace.FundraisingWorkspace, 30);
				}
			} else {
				appLog.error(M8F1+" fund is not available, so cannot continue with the tc.");
				sa.assertTrue(false,M8F1+" fund is not available, so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot continue with the tc.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M8tc018_VerifyBuildStep3Of3InvestorWorkspace(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		boolean flag = true;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F4)){
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Funcraising wokspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.InvestorWorkspace, 30), "Buld Investor workspace button", action.BOOLEAN)){
					if(sendKeys(driver, fp.getFundVintageYearField(Workspace.InvestorWorkspace, 30), "2013", "Vintage year", action.BOOLEAN)){
						appLog.info("Successfully passed value to vintage year text box.");
					} else {
						appLog.error("Cannot pass value to vintage year text box.");
						sa.assertTrue(false,"Cannot pass value to vintage year text box.");
					}
					
					if(sendKeys(driver, fp.getFundSizeField(Workspace.InvestorWorkspace, 30), "5000", "Fund Size", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund size text box.");
					} else {
						appLog.error("Cannot pass value to Fund size text box.");
						sa.assertTrue(false,"Cannot pass value to Fund size text box.");
					}
					
					if(sendKeys(driver, fp.getFundContactField(Workspace.InvestorWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund contact text box.");
					} else {
						appLog.error("Cannot pass value to Fund contact text box.");
						sa.assertTrue(false,"Cannot pass value to Fund contact text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundEmailField(Workspace.InvestorWorkspace, 30), "abc@abc.com", "Fund Email", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Email text box.");
					} else {
						appLog.error("Cannot pass value to Fund Email text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Email text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundPhoneField(Workspace.InvestorWorkspace, 30), "987654321", "Fund Phone", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Phone text box.");
					} else {
						appLog.error("Cannot pass value to Fund Phone text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Phone text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundDescriptionField(Workspace.InvestorWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Description text box.");
					} else {
						appLog.error("Cannot pass value to Fund Description text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Description text box.");
					}
					
					if(flag){
						if(click(driver, fp.getNext1Of3Button(Workspace.InvestorWorkspace, 30), "Next button of step 1 of 3", action.BOOLEAN)){
							if(fp.getStep2Of3Header(Workspace.InvestorWorkspace, 30)!=null){
								appLog.info("Step 2 of 3 pop up is successfully opened.");
								String folderToBeCreated = "Standard Parent Folder";
								if(fp.createParentFolder(folderToBeCreated, FolderType.Standard, PageName.FundsPage, Workspace.InvestorWorkspace, 30)){
									appLog.info("Folder Successfully Created.");
								} else {
									appLog.error("Folder Cannot be created.");
									sa.assertTrue(false,"Folder Cannot be created.");
								}
								WebElement ele = FindElement(driver, "//span[@title='Add a Folder'][contains(@onclick,'AddFirstLevelFolderBWINV')]/../../../following-sibling::ul//label[contains(text(),'"+ folderToBeCreated +"')]", "Folder", action.BOOLEAN, 30);
								if(ele!=null){
									appLog.info(folderToBeCreated+" is successfully created and is verified.");
								} else {
									appLog.error(folderToBeCreated+" is not created.");
									sa.assertTrue(false,folderToBeCreated+" is not created.");
								}
								if(click(driver, fp.getNext2Of3Button(Workspace.InvestorWorkspace, 30), "Next Button", action.BOOLEAN)){
									if(fp.getStep3Of3Header(Workspace.InvestorWorkspace, 30)!=null){
										String text = getText(driver, fp.getStep3Of3YellowBoxText(Workspace.InvestorWorkspace, 30), "", action.BOOLEAN);
										if(text.trim().equalsIgnoreCase(FundsPageErrorMessage.Step3Of3YellowBoxText.trim())){
											appLog.info("Step 3 Of 3 yellow box text is verified.");
										} else {
											appLog.error("Step 3 Of 3 yellow box text is not verified. Expected: "+FundsPageErrorMessage.Step3Of3YellowBoxText+"\tActual: "+text);
											sa.assertTrue(false,"Step 3 Of 3 yellow box text is not verified. Expected: "+FundsPageErrorMessage.Step3Of3YellowBoxText+"\tActual: "+text);
										}
										if(fp.getInvestorFilterExpandOrCollapseIcon(Workspace.InvestorWorkspace, ExpandCollapse.Collapse, 30)!=null){
											appLog.info("Investor Filter section is in Expanded state and is verified.");
										} else {
											appLog.error("Investor Filter section is in collapsed state.");
											sa.assertTrue(false,"Investor Filter section is in collapsed state.");
										}
										
										text = getSelectedOptionOfDropDown(driver, fp.getStep3Of3FieldDropDown(Workspace.InvestorWorkspace, 30), "Filter Field  drop down", "text");
										if(text.trim().equalsIgnoreCase("--None--")){
											appLog.info("--None-- is by default selected in the field drop down.");
										} else {
											appLog.error("--None-- is not selected in the field drop down. Expected: --None--\tActual: "+text);
											sa.assertTrue(false,"--None-- is not selected in the field drop down. Expected: --None--\tActual: "+text);
										}
										
										text = getSelectedOptionOfDropDown(driver, fp.getStep3Of3OperatorDropDown(Workspace.InvestorWorkspace, 30), "Filter Field  drop down", "text");
										if(text.trim().equalsIgnoreCase("equals")){
											appLog.info("equals is by default selected in the operator drop down.");
										} else {
											appLog.error("equals is not selected in the operator drop down. Expected: equals\tActual: "+text);
											sa.assertTrue(false,"equals is not selected in the operator drop down. Expected: equals\tActual: "+text);
										}
										
										text = getAttribute(driver, fp.getStep3Of3FilterTextBox(Workspace.InvestorWorkspace, 30), "", "value");
										if(text==null || text.isEmpty()){
											appLog.info("Criterion box is empty and verified.");
										} else {
											appLog.error("Criterion box is not empty.");
											sa.assertTrue(false,"Criterion box is not empty.");
										}
										
										if(fp.getStep3Of3AddRowLink(Workspace.InvestorWorkspace, 30)!=null){
											appLog.info("Add row link is verified.");
										} else {
											appLog.error("Add row link is not present.");
											sa.assertTrue(false,"Add row link is not present.");
										}
										
										if(fp.getStep3Of3ApplyButton(Workspace.InvestorWorkspace, 30)!=null){
											appLog.info("Apply button is verified.");
										} else {
											appLog.error("Apply button is not verified.");
											sa.assertTrue(false,"Apply button is not verified.");
										}
										
										if(fp.getStep3Of3ClearButton(Workspace.InvestorWorkspace, 30)!=null){
											appLog.info("Clear button is verified.");
										} else {
											appLog.error("Clear button is not verified.");
											sa.assertTrue(false,"Clear button is not verified.");
										}
										
										ele = FindElement(driver, "//span[contains(text(),'All Investors')]", "All Investor Folder", action.BOOLEAN, 30);
										if(ele != null){
											appLog.info("All investor folder is present and is verified.");
										} else {
											appLog.error("All Investors Folder is not present in the grid.");
											sa.assertTrue(false,"All Investors Folder is not present in the grid.");
										}
										
										
										if(fp.getDone3Of3Button(Workspace.InvestorWorkspace, 30)!=null){
											appLog.info("Done button is verified.");
										} else {
											appLog.error("Done button is not verified.");
											sa.assertTrue(false,"Done button is not verified.");
										}
										
										if(fp.getStep3Of3CrossIcon(Workspace.InvestorWorkspace, 30)!=null){
											appLog.info("Step 3 of 3 cross icon is verified.");
										} else {
											appLog.error("Step 3 of 3 cross icon is not verified.");
											sa.assertTrue(false,"Step 3 of 3 cross icon is not verified.");
										}
										
										if(click(driver, fp.getStep3Of3CrossIcon(Workspace.InvestorWorkspace, 30), "Cross icon", action.BOOLEAN)){
											ThreadSleep(1500);
											if(fp.getStep3Of3Header(Workspace.InvestorWorkspace, 30)==null){
												appLog.info("Step 3 of 3 pop up is closed after clicking on the cross icon.");
												if(fp.getBuildWorkspaceButton(Workspace.InvestorWorkspace, 30)!=null){
													appLog.info("Build Workspace button is visible after clicking on the cross icon and is verified.");
												} else {
													appLog.error("Build Workspace button is not visible after clicking on the cross icon and is not verified.");
													sa.assertTrue(false,"Build Workspace button is not visible after clicking on the cross icon and is not verified.");
												}
											} else {
												appLog.error("Step 3 of 3 pop up is not closed after clicking on the cross icon.");
												sa.assertTrue(false,"Step 3 of 3 pop up is not closed after clicking on the cross icon.");
											}
										} else {
											appLog.error("Cross icon cannot be clicked, SO cannot check cross icon functionality.");
											sa.assertTrue(false,"Cross icon cannot be clicked, SO cannot check cross icon functionality.");
										}
									} else {
										appLog.error("Step 3 Of 3 pop up is not opening after clicking on the next button of step 2 Of 3 pop up.");
										sa.assertTrue(false,"Step 3 Of 3 pop up is not opening after clicking on the next button of step 2 Of 3 pop up.");
									}
								} else {
									appLog.error("Next Button of step 2 Of 3 cannot be clikced, So cannot verify UI of step 3 Of 3.");
									sa.assertTrue(false,"Next Button of step 2 Of 3 cannot be clikced, So cannot verify UI of step 3 Of 3.");
									fp.recover(Workspace.InvestorWorkspace, 30);
								}
							} else {
								appLog.error("Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
								sa.assertTrue(false,"Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
								fp.recover(Workspace.InvestorWorkspace, 30);
							}
							
						} else {
							appLog.error("Next button of step 1 of 3 cannot be click, So cannot check cancel button functionality..");
							sa.assertTrue(false,"Next button of step 1 of 3 cannot be click, So cannot check cancel button functionality.");
							fp.recover(Workspace.InvestorWorkspace, 30);
						}
					} else {
						appLog.error("One of the mandatory field is not filled, So cannot check cancel button functionality.");
						sa.assertTrue(false,"One of the mandatory field is not filled, So cannot check cancel button functionality.");
						fp.recover(Workspace.InvestorWorkspace, 30);
					}
				} else {
					appLog.error("Build Investor workspace button cannot be clicked, So cannot check cancel button functionality.");
					sa.assertTrue(false,"Build Investor workspace button cannot be clicked, So cannot check cancel button functionality.");
					fp.recover(Workspace.InvestorWorkspace, 30);
				}
			} else {
				appLog.error(M8F4+" fund is not available, so cannot continue with the tc.");
				sa.assertTrue(false,M8F4+" fund is not available, so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot continue with the tc.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M8tc006_VerifyAddAFolderOnStep2OfBuildProcessAndVerifyErrorMessage(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		String STDFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		String commonFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath);
		String InternalFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.InternalPath);
		String SHRDFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath);
		boolean flag = true;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F1)){
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Funcraising wokspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 30), "Buld Investor workspace button", action.BOOLEAN)){
					if(sendKeys(driver, fp.getFundVintageYearField(Workspace.FundraisingWorkspace, 30), "2013", "Vintage year", action.BOOLEAN)){
						appLog.info("Successfully passed value to vintage year text box.");
					} else {
						appLog.error("Cannot pass value to vintage year text box.");
						sa.assertTrue(false,"Cannot pass value to vintage year text box.");
					}

					if(sendKeys(driver, fp.getFundSizeField(Workspace.FundraisingWorkspace, 30), "5000", "Fund Size", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund size text box.");
					} else {
						appLog.error("Cannot pass value to Fund size text box.");
						sa.assertTrue(false,"Cannot pass value to Fund size text box.");
					}

					if(sendKeys(driver, fp.getFundContactField(Workspace.FundraisingWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund contact text box.");
					} else {
						appLog.error("Cannot pass value to Fund contact text box.");
						sa.assertTrue(false,"Cannot pass value to Fund contact text box.");
						flag=false;
					}

					if(sendKeys(driver, fp.getFundEmailField(Workspace.FundraisingWorkspace, 30), "abc@abc.com", "Fund Email", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Email text box.");
					} else {
						appLog.error("Cannot pass value to Fund Email text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Email text box.");
						flag=false;
					}

					if(sendKeys(driver, fp.getFundPhoneField(Workspace.FundraisingWorkspace, 30), "987654321", "Fund Phone", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Phone text box.");
					} else {
						appLog.error("Cannot pass value to Fund Phone text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Phone text box.");
						flag=false;
					}

					if(sendKeys(driver, fp.getFundDescriptionField(Workspace.FundraisingWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Description text box.");
					} else {
						appLog.error("Cannot pass value to Fund Description text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Description text box.");
					}

					if(flag){
						if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 30), "Next button of step 1 of 3", action.BOOLEAN)){
							if(fp.getStep2Of3Header(Workspace.FundraisingWorkspace, 30)!=null){
								appLog.info("Step 2 of 3 pop up is successfully opened.");
								if(click(driver, FindElement(driver, "//span[@id='add0000'][@title='Add a Folder']", "+Icon",
										action.BOOLEAN, 30), "", action.BOOLEAN)){
									if(mouseOverOperation(driver, fp.getAddFolderInformationIconOnBuildProcess(Workspace.FundraisingWorkspace, 30))){
										String text = trim(getText(driver, fp.getAddFolderInfoIconMessage(30), "", action.BOOLEAN));
										if(text.contains(BasePageErrorMessage.AddFolderInfoIconMessage) && text.contains(BasePageErrorMessage.AddFolderInfoIconMessage1) && text.contains(BasePageErrorMessage.AddFolderInfoIconMessage2) && text.contains(BasePageErrorMessage.AddFolderInfoIconMessage3) && text.contains(BasePageErrorMessage.AddFolderInfoIconMessage4)){
											appLog.info("Tool tip error message is verified.");
										} else {
											appLog.error("Tool tip error message is not verified. Expected: "+ BasePageErrorMessage.AddFolderInfoIconMessage+" "+BasePageErrorMessage.AddFolderInfoIconMessage1+" "+BasePageErrorMessage.AddFolderInfoIconMessage2+" "+BasePageErrorMessage.AddFolderInfoIconMessage3+" "+BasePageErrorMessage.AddFolderInfoIconMessage4+"\tActual: "+text);
											sa.assertTrue(false,"Tool tip error message is not verified. Expected: "+ BasePageErrorMessage.AddFolderInfoIconMessage+" "+BasePageErrorMessage.AddFolderInfoIconMessage1+" "+BasePageErrorMessage.AddFolderInfoIconMessage2+" "+BasePageErrorMessage.AddFolderInfoIconMessage3+" "+BasePageErrorMessage.AddFolderInfoIconMessage4+"\tActual: "+text);
										}
									} else {
										appLog.error("Cannot hover the mouse on info icon, So cannot verify info icon message.");
										sa.assertTrue(false,"Cannot hover the mouse on info icon, So cannot verify info icon message.");
									}
									String[] radioBtn= {STDFolderName,commonFolderName,SHRDFolderName,InternalFolderName};
									for(int i=0; i<radioBtn.length; i++) {
										boolean flag1 =false;
										if(i==0) {
											flag1=fp.verifyFolderErrorMessage(FolderType.Standard, Workspace.FundraisingWorkspace, PageName.FundsPage,fp.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20);
										}if(i==1) {
											flag1=fp.verifyFolderErrorMessage(FolderType.Common, Workspace.FundraisingWorkspace, PageName.FundsPage,fp.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20);
										}if(i==2) {
											flag1=fp.verifyFolderErrorMessage(FolderType.Shared, Workspace.FundraisingWorkspace, PageName.FundsPage,fp.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20);
										}if(i==3) {
											flag1=fp.verifyFolderErrorMessage(FolderType.Internal, Workspace.FundraisingWorkspace, PageName.FundsPage,fp.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20);
										}
										if(flag1) {
											appLog.info(radioBtn[i]+" folder Error Message is verified successfully.");
										}else {
											appLog.error(radioBtn[i]+" folder Error Message is not verified.");
											sa.assertTrue(false, radioBtn[i]+" folder Error Message is not verified.");
										}
									}
									List<String> value = getValueBasedOnLabel(filePath, "SpecialChar",excelLabel.INVALID_FOLDER_NAME, 1);
									if(!value.isEmpty()) {
											for(int i=1; i<radioBtn.length; i++) {
												boolean flag2 =false;
												if(i==1) {
													flag2=fp.verifyFolderErrorMessage(FolderType.Common, Workspace.FundraisingWorkspace, PageName.FundsPage,fp.prefixErrorMsg,value.get(i-1),ErrorMessageType.PrefixErrorMsg,20);
												}if(i==2) {
													flag2=fp.verifyFolderErrorMessage(FolderType.Shared, Workspace.FundraisingWorkspace, PageName.FundsPage,fp.prefixErrorMsg,value.get(i-1),ErrorMessageType.PrefixErrorMsg,20);
												}if(i==3) {
													flag2=fp.verifyFolderErrorMessage(FolderType.Internal, Workspace.FundraisingWorkspace, PageName.FundsPage,fp.prefixErrorMsg,value.get(i-1),ErrorMessageType.PrefixErrorMsg,20);
												}
												if(flag2) {
													appLog.info(radioBtn[i]+" folder prefix Error Message is verified successfully.");
												}else {
													appLog.error(radioBtn[i]+" folder prefix Error Message is not verified.");
													sa.assertTrue(false, radioBtn[i]+" folder prefix Error Message is not verified.");
												}
											}
										}else {
											appLog.error("excel data is empty so cannot check prefix character error message");
											sa.assertTrue(false, "excel data is empty so cannot check prefix character error message");
										}
									List<String> specialChar = getValueBasedOnLabel(filePath, "SpecialChar",excelLabel.FOLDER_NAME, 1);
									if(!specialChar.isEmpty()) {
										for(int j=0; j<specialChar.size(); j++) {
											for(int i=0; i<radioBtn.length; i++) {
												boolean flag3 =false;
												if(i==0) {
													flag3=fp.verifyFolderErrorMessage(FolderType.Standard, Workspace.FundraisingWorkspace, PageName.FundsPage,fp.speicalCharErrorMsgFundsPage,specialChar.get(j),ErrorMessageType.SpiecalCharErrorMsg,20);
												}if(i==1) {
													flag3=fp.verifyFolderErrorMessage(FolderType.Common, Workspace.FundraisingWorkspace, PageName.FundsPage,fp.speicalCharErrorMsgFundsPage,specialChar.get(j),ErrorMessageType.SpiecalCharErrorMsg,20);
												}if(i==2) {
													flag3=fp.verifyFolderErrorMessage(FolderType.Shared, Workspace.FundraisingWorkspace, PageName.FundsPage,fp.speicalCharErrorMsgFundsPage,specialChar.get(j),ErrorMessageType.SpiecalCharErrorMsg,20);
												}if(i==3) {
													flag3=fp.verifyFolderErrorMessage(FolderType.Internal, Workspace.FundraisingWorkspace, PageName.FundsPage,fp.speicalCharErrorMsgFundsPage,specialChar.get(j),ErrorMessageType.SpiecalCharErrorMsg,20);
												}
												if(flag3) {
													appLog.info(radioBtn[i]+" folder special Error Message is verified successfully.");
												}else {
													appLog.error(radioBtn[i]+" folder special Error Message is not verified.");
													sa.assertTrue(false, radioBtn[i]+" folder special Error Message is not verified.");
												}
											}
										}
									}else {
										appLog.error("excel data is empty so cannot check special character error message");
										sa.assertTrue(false, "excel data is empty so cannot check special character error message");
									}
//									Scanner scan = new Scanner(System.in);
//									scan.nextLine();
									click(driver, fp.getAddFolderParentFolderCancelButton(Workspace.FundraisingWorkspace, PageName.FundsPage, 30), "Cancel Button", action.BOOLEAN);
									if(fp.createParentFolder(commonFolderName.split("\\(")[0], FolderType.Common, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
										appLog.info("Parent common folder is created.");
									} else {
										appLog.error("Parent common folder is not created.");
										sa.assertTrue(false,"Parent common folder is not created.");
									}
									if(fp.createParentFolder(InternalFolderName.split("\\(")[0], FolderType.Internal, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
										appLog.info("Parent internal folder is created.");
									} else {
										appLog.error("Parent internal folder is not created.");
										sa.assertTrue(false,"Parent internal folder is not created.");
									}
									if(fp.createFolderStructure(commonFolderName+"/Common Child Folder", FolderType.Common, Workspace.FundraisingWorkspace, PageName.FundsPage, 30).isEmpty()){
										appLog.info("Common child folder is created successfully.");
									} else {
										appLog.error("Common child folder cannot be created.");
										sa.assertTrue(false,"Common child folder cannot be created.");
									}
									if(fp.createFolderStructure(InternalFolderName+"/Internal Child Folder", FolderType.Internal, Workspace.FundraisingWorkspace, PageName.FundsPage, 30).isEmpty()){
										appLog.info("Internal child folder is created successfully.");
									} else {
										appLog.error("Internal child folder cannot be created.");
										sa.assertTrue(false,"Internal child folder cannot be created.");
									}
									click(driver, FindElement(driver, "//span[@id='add0000'][@title='Add a Folder']", "+Icon",
											action.BOOLEAN, 30), "", action.BOOLEAN);
									for(int i=0; i<2; i++) {
										WebElement ele=null;
										ele=bp.getFolderTypeRadioButton(FolderType.Common,Workspace.FundraisingWorkspace,PageName.FundsPage, 30);
										if(ele!=null) {
											if(click(driver, ele,"common folder radio button icon", action.BOOLEAN)) {
												appLog.info("clicked on common radio button");
													if(sendKeys(driver, bp.getParentFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.FundsPage,20),commonFolderName.split("\\(")[0],"Common folder text box", action.BOOLEAN)) {
														appLog.info("value pass in folder Common text box: "+commonFolderName.split("\\(")[0]);
														if(click(driver, bp.getParentFolderSaveButton(Workspace.FundraisingWorkspace, PageName.FundsPage, 20), "folder save button", action.BOOLEAN)) {
															appLog.info("clicked on save button");
															WebElement ele1=bp.getFolderErrorMsg(Workspace.FundraisingWorkspace, PageName.FundsPage, ErrorMessageType.FolderCreationRestrictionErrorMsg, FolderType.Common,20);
															if(ele1!=null) {
																String aa=ele1.getText().trim();
																if(aa.contains(FundsPageErrorMessage.CommonFolderCreationErrorMsg)) {
																	appLog.info("Folder error messgae is verified for folder type Common");
																	
																}else {
																	appLog.error("Folder error message is not verified for folder type Common Expected: "+FundsPageErrorMessage.CommonFolderCreationErrorMsg+"/t Actual: "+aa);
																	sa.assertTrue(false, "Folder error message is not verified for folder type Common Expected: "+FundsPageErrorMessage.CommonFolderCreationErrorMsg+"/t Actual: "+aa);
																}
																if(i==0) {
																	if(click(driver, fp.getFolderCreationErrorMessageCrossIcon(Workspace.FundraisingWorkspace, PageName.FundsPage, FolderType.Common,30),"common folder error message cross icon", action.BOOLEAN)) {
																		appLog.info("clicked on common folder error message cross icon");
																		if(fp.getFolderCreationErrorMessageOkBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, FolderType.Common,20)==null) {
																			appLog.info("error message pop is closed after click on cross icon");
																			if(click(driver, FindElement(driver, "//span[@id='add0000'][@title='Add a Folder']", "+Icon",
																					action.BOOLEAN, 30), "", action.BOOLEAN)){
																				appLog.info("clicked on Add folder Button again");
																			}else {
																				appLog.error("Not able to click on Add folder Icon so cannot check error message OK button functionality");
																				sa.assertTrue(false, "Not able to click on Add folder Icon so cannot check error message OK button functionality");
																				break;
																			}
																		}else {
																			appLog.error("Error Message Pop is not closed after click on cross icon");
																			sa.assertTrue(false, "Error Message Pop is not closed after click on cross icon");
																		}
																	}else {
																		appLog.error("Not able to click on common folder error message cross icon");
																		sa.assertTrue(false, "Not able to click on common folder error message cross icon");
																	}
																}else {
																	if(click(driver, fp.getFolderCreationErrorMessageOkBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, FolderType.Common,30),"common folder error message cross icon", action.BOOLEAN)) {
																		appLog.info("clicked on common folder error message OK button");
																		if(fp.getFolderCreationErrorMessageOkBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, FolderType.Common,20)==null) {
																			appLog.info("error message pop is closed after click on OK button");
																			
																		}else {
																			appLog.error("Error Message Pop is not closed after click on Ok button");
																			sa.assertTrue(false, "Error Message Pop is not closed after click on Ok button");
																		}
																	}else {
																		appLog.error("Not able to click on common folder error message cross icon");
																		sa.assertTrue(false, "Not able to click on common folder error message cross icon");
																	}
																}
															}else {
																appLog.error("Error message is not visible in folder type Common so cannot check Error Message. Expected: "+FundsPageErrorMessage.CommonFolderCreationErrorMsg);
																sa.assertTrue(false, "Error message is not visible in folder type Common so cannot check Error Message. Expected: "+FundsPageErrorMessage.CommonFolderCreationErrorMsg);
															}
														}else {
															appLog.error("Not able to click on save button so cannot check error message for folder type Common");
															sa.assertTrue(false, "Not able to click on save button so cannot check error message for folder type Common");
														}
													}else {
														appLog.info("Not able to pass value in folder Common text box: "+commonFolderName.split("\\(")[0]+" so cannot check error message for common folder. "+FundsPageErrorMessage.CommonFolderCreationErrorMsg);
														sa.assertTrue(false, "Not able to pass value in folder Common text box: "+commonFolderName.split("\\(")[0]+" so cannot check error message for common folder. "+FundsPageErrorMessage.CommonFolderCreationErrorMsg);
													}
											}else {
												appLog.error("Not able to click on Common folder radio button so cannot check error message"+FundsPageErrorMessage.CommonFolderCreationErrorMsg);
												sa.assertTrue(false, "Not able to click on Common folder radio button so cannot check error message"+FundsPageErrorMessage.CommonFolderCreationErrorMsg);
											}
										}else {
											appLog.error("folder radio button is not visible so cannot check error message");
											sa.assertTrue(false, "folder radio button is not visible so cannot check error message");
										}
									}
									if(click(driver, FindElement(driver, "//span[@id='add0000'][@title='Add a Folder']", "+Icon",
											action.BOOLEAN, 30), "", action.BOOLEAN)){
										for(int i=0; i<2; i++) {
											WebElement ele=null;
											ele=bp.getFolderTypeRadioButton(FolderType.Internal,Workspace.FundraisingWorkspace,PageName.FundsPage, 30);
											if(ele!=null) {
												if(click(driver, ele,"Internal folder radio button icon", action.BOOLEAN)) {
													appLog.info("clicked on Internal radio button");
														if(sendKeys(driver, bp.getParentFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.FundsPage,20),InternalFolderName.split("\\(")[0],"Internal folder text box", action.BOOLEAN)) {
															appLog.info("value pass in folder Internal text box: "+InternalFolderName.split("\\(")[0]);
															if(click(driver, bp.getParentFolderSaveButton(Workspace.FundraisingWorkspace, PageName.FundsPage, 20), "folder save button", action.BOOLEAN)) {
																appLog.info("clicked on save button");
																WebElement ele1=bp.getFolderErrorMsg(Workspace.FundraisingWorkspace, PageName.FundsPage, ErrorMessageType.FolderCreationRestrictionErrorMsg, FolderType.Internal,20);
																if(ele1!=null) {
																	String aa=ele1.getText().trim();
																	if(aa.contains(FundsPageErrorMessage.InternalFolderCreationErrorMsg)) {
																		appLog.info("Folder error messgae is verified for folder type Internal");
																		
																	}else {
																		appLog.error("Folder error message is not verified for folder type Internal Expected: "+FundsPageErrorMessage.InternalFolderCreationErrorMsg+"/t Actual: "+aa);
																		sa.assertTrue(false, "Folder error message is not verified for folder type Internal Expected: "+FundsPageErrorMessage.InternalFolderCreationErrorMsg+"/t Actual: "+aa);
																	}
																	if(i==0) {
																		if(click(driver, fp.getFolderCreationErrorMessageCrossIcon(Workspace.FundraisingWorkspace, PageName.FundsPage, FolderType.Internal,30),"Internal folder error message cross icon", action.BOOLEAN)) {
																			appLog.info("clicked on Internal folder error message cross icon");
																			if(fp.getFolderCreationErrorMessageOkBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, FolderType.Internal,20)==null) {
																				appLog.info("error message pop is closed after click on cross icon");
																				if(click(driver, FindElement(driver, "//span[@id='add0000'][@title='Add a Folder']", "+Icon",
																						action.BOOLEAN, 30), "", action.BOOLEAN)){
																					appLog.info("clicked on Add folder Button again");
																				}else {
																					appLog.error("Not able to click on Add folder Icon so cannot check error message OK button functionality");
																					sa.assertTrue(false, "Not able to click on Add folder Icon so cannot check error message OK button functionality");
																					break;
																				}
																			}else {
																				appLog.error("Error Message Pop is not closed after click on cross icon");
																				sa.assertTrue(false, "Error Message Pop is not closed after click on cross icon");
																			}
																		}else {
																			appLog.error("Not able to click on Internal folder error message cross icon");
																			sa.assertTrue(false, "Not able to click on Internal folder error message cross icon");
																		}
																	}else {
																		if(click(driver, fp.getFolderCreationErrorMessageOkBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, FolderType.Internal,30),"Internal folder error message cross icon", action.BOOLEAN)) {
																			appLog.info("clicked on Internal folder error message OK button");
																			if(fp.getFolderCreationErrorMessageOkBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, FolderType.Internal,20)==null) {
																				appLog.info("error message pop is closed after click on OK button");
																			}else {
																				appLog.error("Error Message Pop is not closed after click on Ok button");
																				sa.assertTrue(false, "Error Message Pop is not closed after click on Ok button");
																			}
																		}else {
																			appLog.error("Not able to click on Internal folder error message cross icon"+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
																			sa.assertTrue(false, "Not able to click on Internal folder error message cross icon"+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
																		}
																	}
																}else {
																	appLog.error("Error message is not visible in folder type Internal so cannot check Error Message. Expected: "+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
																	sa.assertTrue(false, "Error message is not visible in folder type Internal so cannot check Error Message. Expected: "+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
																}
															}else {
																appLog.error("Not able to click on save button so cannot check error message for folder type Internal"+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
																sa.assertTrue(false, "Not able to click on save button so cannot check error message for folder type Internal"+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
															}
														}else {
															appLog.info("Not able to pass value in folder Internal text box: "+InternalFolderName.split("\\(")[0]+" so cannot check error message"+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
															sa.assertTrue(false, "Not able to pass value in folder Internal text box: "+InternalFolderName.split("\\(")[0]+" so cannot check error message"+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
														}
												}else {
													appLog.error("Not able to click on Internal folder radio button so cannot check error message"+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
													sa.assertTrue(false, "Not able to click on Internal folder radio button so cannot check error message"+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
												}
											}else {
												appLog.error("folder radio button is not visible so cannot check error message"+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
												sa.assertTrue(false, "folder radio button is not visible so cannot check error message"+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
											}
										}
										
									}else {
										appLog.error("Not able to click on Add folder icon so cannot check internal folder error message. Error Message: "+fp.InternalFolderCreationErrorMsg);
										sa.assertTrue(false, "Not able to click on Add folder icon so cannot check internal folder error message. Error Message: "+fp.InternalFolderCreationErrorMsg);
									}
									if(fp.createParentFolder(STDFolderName, FolderType.Standard, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
										appLog.info("Parent Standard folder is created.");
									} else {
										appLog.error("Parent Standard folder is not created.");
										sa.assertTrue(false,"Parent Standard folder is not created.");
									}
									if(fp.createParentFolder(SHRDFolderName.split("\\(")[0], FolderType.Shared, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
										appLog.info("Parent Shared folder is created.");
									} else {
										appLog.error("Parent Shared folder is not created.");
										sa.assertTrue(false,"Parent Shared folder is not created.");
									}
									if(fp.createFolderStructure(STDFolderName+"/STD Child Folder", FolderType.Standard, Workspace.FundraisingWorkspace, PageName.FundsPage, 30).isEmpty()){
										appLog.info("STD Child Folder is created successfully.");
									} else {
										appLog.error("STD child folder cannot be created.");
										sa.assertTrue(false,"STD child folder cannot be created.");
									}
									if(fp.createFolderStructure(SHRDFolderName.split("\\(")[0]+"/SHD Child Folder", FolderType.Internal, Workspace.FundraisingWorkspace, PageName.FundsPage, 30).isEmpty()){
										appLog.info("SHD child folder is created successfully.");
									} else {
										appLog.error("SHD child folder cannot be created.");
										sa.assertTrue(false,"SHD child folder cannot be created.");
									}
									String[] ss= {STDFolderName,SHRDFolderName.split("\\(")[0]};
									for(int i=0; i<2; i++) {
									if(click(driver, FindElement(driver, "//span[@id='add0000'][@title='Add a Folder']", "+Icon",
											action.BOOLEAN, 30), "", action.BOOLEAN)){
											WebElement ele=null;
											if(i==0) {
												ele=bp.getFolderTypeRadioButton(FolderType.Standard,Workspace.FundraisingWorkspace,PageName.FundsPage, 30);									
											}else {
												ele=bp.getFolderTypeRadioButton(FolderType.Shared,Workspace.FundraisingWorkspace,PageName.FundsPage, 30);	
											}
											if(ele!=null) {
												if(click(driver, ele,ss[i]+" folder radio button icon", action.BOOLEAN)) {
													appLog.info("clicked on "+ss[i]+" radio button");
														if(sendKeys(driver, bp.getParentFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.FundsPage,20),ss[i],ss[i]+" folder text box", action.BOOLEAN)) {
															appLog.info("value pass in folder text box: "+ss[i]);
															if(click(driver, bp.getParentFolderSaveButton(Workspace.FundraisingWorkspace, PageName.FundsPage, 20), "folder save button", action.BOOLEAN)) {
																appLog.info("clicked on save button");
																ThreadSleep(2000);
																if(fp.getParentFolderErrorMsg(Workspace.FundraisingWorkspace, PageName.FundsPage, ErrorMessageType.DuplicateFolder, 30)!=null){
																	String msg = getText(driver, fp.getParentFolderErrorMsg(Workspace.FundraisingWorkspace, PageName.FundsPage, ErrorMessageType.DuplicateFolder, 30), "", action.BOOLEAN);
																	appLog.error("Error Message is displaying : "+msg);
																	if(msg.contains(FundsPageErrorMessage.FolderAlreadyExistErrormsg)) {
																		appLog.info("Error msg is verified successfully.");
																	}else {
																		appLog.error("Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
																		sa.assertTrue(false, "Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
																	}
																} else {
																	appLog.error("Duplicate Folder Error Message Pop Up Is not opening after clicking on save button for standard Folder.");
																	sa.assertTrue(false,"Duplicate Folder Error Message Pop Up Is not opening after clicking on save button for standard Folder.");
																}
																if(click(driver, fp.getFolderCreationErrorMessageOkBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, FolderType.Standard, 10), "Duplicate folder Error message ok button", action.BOOLEAN)){
																	System.err.println("successfulyl clicked on ok button.");
																} else {
																	System.err.println("\n\n\nNot able to click on OK Button.\n\n\n");
																}
															}else {
																appLog.error("Not able to click on save button so cannot check error message for folder type "+ss[i]+""+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
																sa.assertTrue(false, "Not able to click on save button so cannot check error message for folder type "+ss[i]+""+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
															}
														}else {
															appLog.info("Not able to pass value in folder text box: "+ss[i]+" so cannot check error message"+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
															sa.assertTrue(false, "Not able to pass value in folder text box: "+ss[i]+" so cannot check error message"+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
														}
												}else {
													appLog.error("Not able to click on "+ss[i]+" folder radio button so cannot check error message"+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
													sa.assertTrue(false, "Not able to click on "+ss[i]+" folder radio button so cannot check error message"+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
												}
											}else {
												appLog.error("folder radio button is not visible so cannot check error message"+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
												sa.assertTrue(false, "folder radio button is not visible so cannot check error message"+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
											}
										
									}else {
										appLog.error("Not able to click on Add folder icon so cannot check folder error message. Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
										sa.assertTrue(false, "Not able to click on Add folder icon so cannot check folder error message. Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
									}
									}
									
								}else {
									appLog.error("Not able to click on Add folder icon so cannot check error messsgae in fundraising workspace");
									sa.assertTrue(false, "Not able to click on Add folder icon so cannot check error messsgae in fundraising workspace");
								}
							} else {
								appLog.error("Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
								sa.assertTrue(false,"Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
								fp.recover(Workspace.FundraisingWorkspace, 30);
							}
						} else {
							appLog.error("Next button of step 1 of 3 cannot be click, So cannot check cancel button functionality..");
							sa.assertTrue(false,"Next button of step 1 of 3 cannot be click, So cannot check cancel button functionality.");
							fp.recover(Workspace.FundraisingWorkspace, 30);
						}
					} else {
						appLog.error("One of the mandatory field is not filled, So cannot check cancel button functionality.");
						sa.assertTrue(false,"One of the mandatory field is not filled, So cannot check cancel button functionality.");
						fp.recover(Workspace.FundraisingWorkspace, 30);
					}
				} else {
					appLog.error("Build Investor workspace button cannot be clicked, So cannot check cancel button functionality.");
					sa.assertTrue(false,"Build Investor workspace button cannot be clicked, So cannot check cancel button functionality.");
					fp.recover(Workspace.FundraisingWorkspace, 30);
				}
			} else {
				appLog.error(M8F1+" fund is not available, so cannot continue with the tc.");
				sa.assertTrue(false,M8F1+" fund is not available, so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot continue with the tc.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M8tc019_VerifyAddAFolderOnStep2OfBuildProcessAndVerifyErrorMessageInvestorWorkspace(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		String dependsOnTc = "M8tc006_VerifyAddAFolderOnStep2OfBuildProcessAndVerifyErrorMessage";
		String STDFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependsOnTc,excelLabel.StandardPath);
		String commonFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependsOnTc,excelLabel.CommonPath);
		String InternalFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependsOnTc,excelLabel.InternalPath);
		String SHRDFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependsOnTc,excelLabel.SharedPath);
		boolean flag = true;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F4)){
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Funcraising wokspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 30), "Buld Investor workspace button", action.BOOLEAN)){
					if(sendKeys(driver, fp.getFundVintageYearField(Workspace.FundraisingWorkspace, 30), "2013", "Vintage year", action.BOOLEAN)){
						appLog.info("Successfully passed value to vintage year text box.");
					} else {
						appLog.error("Cannot pass value to vintage year text box.");
						sa.assertTrue(false,"Cannot pass value to vintage year text box.");
					}

					if(sendKeys(driver, fp.getFundSizeField(Workspace.FundraisingWorkspace, 30), "5000", "Fund Size", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund size text box.");
					} else {
						appLog.error("Cannot pass value to Fund size text box.");
						sa.assertTrue(false,"Cannot pass value to Fund size text box.");
					}

					if(sendKeys(driver, fp.getFundContactField(Workspace.FundraisingWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund contact text box.");
					} else {
						appLog.error("Cannot pass value to Fund contact text box.");
						sa.assertTrue(false,"Cannot pass value to Fund contact text box.");
						flag=false;
					}

					if(sendKeys(driver, fp.getFundEmailField(Workspace.FundraisingWorkspace, 30), "abc@abc.com", "Fund Email", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Email text box.");
					} else {
						appLog.error("Cannot pass value to Fund Email text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Email text box.");
						flag=false;
					}

					if(sendKeys(driver, fp.getFundPhoneField(Workspace.FundraisingWorkspace, 30), "987654321", "Fund Phone", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Phone text box.");
					} else {
						appLog.error("Cannot pass value to Fund Phone text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Phone text box.");
						flag=false;
					}

					if(sendKeys(driver, fp.getFundDescriptionField(Workspace.FundraisingWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Description text box.");
					} else {
						appLog.error("Cannot pass value to Fund Description text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Description text box.");
					}

					if(flag){
						if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 30), "Next button of step 1 of 3", action.BOOLEAN)){
							if(fp.getStep2Of3Header(Workspace.FundraisingWorkspace, 30)!=null){
								appLog.info("Step 2 of 3 pop up is successfully opened.");
								if(click(driver, FindElement(driver, "//span[@id='add0000'][@title='Add a Folder']", "+Icon",
										action.BOOLEAN, 30), "", action.BOOLEAN)){
									String[] radioBtn= {STDFolderName,commonFolderName,SHRDFolderName,InternalFolderName};
									for(int i=0; i<radioBtn.length; i++) {
										boolean flag1 =false;
										if(i==0) {
											flag1=fp.verifyFolderErrorMessage(FolderType.Standard, Workspace.FundraisingWorkspace, PageName.FundsPage,fp.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20);
										}if(i==1) {
											flag1=fp.verifyFolderErrorMessage(FolderType.Common, Workspace.FundraisingWorkspace, PageName.FundsPage,fp.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20);
										}if(i==2) {
											flag1=fp.verifyFolderErrorMessage(FolderType.Shared, Workspace.FundraisingWorkspace, PageName.FundsPage,fp.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20);
										}if(i==3) {
											flag1=fp.verifyFolderErrorMessage(FolderType.Internal, Workspace.FundraisingWorkspace, PageName.FundsPage,fp.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20);
										}
										if(flag1) {
											appLog.info(radioBtn[i]+" folder Error Message is verified successfully.");
										}else {
											appLog.error(radioBtn[i]+" folder Error Message is not verified.");
											sa.assertTrue(false, radioBtn[i]+" folder Error Message is not verified.");
										}
									}
									List<String> value = getValueBasedOnLabel(filePath, "SpecialChar",excelLabel.INVALID_FOLDER_NAME, 1);
									if(!value.isEmpty()) {
											for(int i=1; i<radioBtn.length; i++) {
												boolean flag2 =false;
												if(i==1) {
													flag2=fp.verifyFolderErrorMessage(FolderType.Common, Workspace.FundraisingWorkspace, PageName.FundsPage,fp.prefixErrorMsg,value.get(i-1),ErrorMessageType.PrefixErrorMsg,20);
												}if(i==2) {
													flag2=fp.verifyFolderErrorMessage(FolderType.Shared, Workspace.FundraisingWorkspace, PageName.FundsPage,fp.prefixErrorMsg,value.get(i-1),ErrorMessageType.PrefixErrorMsg,20);
												}if(i==3) {
													flag2=fp.verifyFolderErrorMessage(FolderType.Internal, Workspace.FundraisingWorkspace, PageName.FundsPage,fp.prefixErrorMsg,value.get(i-1),ErrorMessageType.PrefixErrorMsg,20);
												}
												if(flag2) {
													appLog.info(radioBtn[i]+" folder prefix Error Message is verified successfully.");
												}else {
													appLog.error(radioBtn[i]+" folder prefix Error Message is not verified.");
													sa.assertTrue(false, radioBtn[i]+" folder prefix Error Message is not verified.");
												}
											}
										}else {
											appLog.error("excel data is empty so cannot check prefix character error message");
											sa.assertTrue(false, "excel data is empty so cannot check prefix character error message");
										}
									List<String> specialChar = getValueBasedOnLabel(filePath, "SpecialChar",excelLabel.FOLDER_NAME, 1);
									if(!specialChar.isEmpty()) {
										for(int j=0; j<specialChar.size(); j++) {
											for(int i=0; i<radioBtn.length; i++) {
												boolean flag3 =false;
												if(i==0) {
													flag3=fp.verifyFolderErrorMessage(FolderType.Standard, Workspace.FundraisingWorkspace, PageName.FundsPage,fp.speicalCharErrorMsgFundsPage,specialChar.get(j),ErrorMessageType.SpiecalCharErrorMsg,20);
												}if(i==1) {
													flag3=fp.verifyFolderErrorMessage(FolderType.Common, Workspace.FundraisingWorkspace, PageName.FundsPage,fp.speicalCharErrorMsgFundsPage,specialChar.get(j),ErrorMessageType.SpiecalCharErrorMsg,20);
												}if(i==2) {
													flag3=fp.verifyFolderErrorMessage(FolderType.Shared, Workspace.FundraisingWorkspace, PageName.FundsPage,fp.speicalCharErrorMsgFundsPage,specialChar.get(j),ErrorMessageType.SpiecalCharErrorMsg,20);
												}if(i==3) {
													flag3=fp.verifyFolderErrorMessage(FolderType.Internal, Workspace.FundraisingWorkspace, PageName.FundsPage,fp.speicalCharErrorMsgFundsPage,specialChar.get(j),ErrorMessageType.SpiecalCharErrorMsg,20);
												}
												if(flag3) {
													appLog.info(radioBtn[i]+" folder special Error Message is verified successfully.");
												}else {
													appLog.error(radioBtn[i]+" folder special Error Message is not verified.");
													sa.assertTrue(false, radioBtn[i]+" folder special Error Message is not verified.");
												}
											}
										}
									}else {
										appLog.error("excel data is empty so cannot check special character error message");
										sa.assertTrue(false, "excel data is empty so cannot check special character error message");
									}
//									Scanner scan = new Scanner(System.in);
//									scan.nextLine();
									click(driver, fp.getAddFolderParentFolderCancelButton(Workspace.FundraisingWorkspace, PageName.FundsPage, 30), "Cancel Button", action.BOOLEAN);
									if(fp.createParentFolder(commonFolderName.split("\\(")[0], FolderType.Common, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
										appLog.info("Parent common folder is created.");
									} else {
										appLog.error("Parent common folder is not created.");
										sa.assertTrue(false,"Parent common folder is not created.");
									}
									if(fp.createParentFolder(InternalFolderName.split("\\(")[0], FolderType.Internal, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
										appLog.info("Parent internal folder is created.");
									} else {
										appLog.error("Parent internal folder is not created.");
										sa.assertTrue(false,"Parent internal folder is not created.");
									}
									if(fp.createFolderStructure(commonFolderName+"/Common Child Folder", FolderType.Common, Workspace.FundraisingWorkspace, PageName.FundsPage, 30).isEmpty()){
										appLog.info("Common child folder is created successfully.");
									} else {
										appLog.error("Common child folder cannot be created.");
										sa.assertTrue(false,"Common child folder cannot be created.");
									}
									if(fp.createFolderStructure(InternalFolderName+"/Internal Child Folder", FolderType.Internal, Workspace.FundraisingWorkspace, PageName.FundsPage, 30).isEmpty()){
										appLog.info("Internal child folder is created successfully.");
									} else {
										appLog.error("Internal child folder cannot be created.");
										sa.assertTrue(false,"Internal child folder cannot be created.");
									}
									click(driver, FindElement(driver, "//span[@id='add0000'][@title='Add a Folder']", "+Icon",
											action.BOOLEAN, 30), "", action.BOOLEAN);
									for(int i=0; i<2; i++) {
										WebElement ele=null;
										ele=bp.getFolderTypeRadioButton(FolderType.Common,Workspace.FundraisingWorkspace,PageName.FundsPage, 30);
										if(ele!=null) {
											if(click(driver, ele,"common folder radio button icon", action.BOOLEAN)) {
												appLog.info("clicked on common radio button");
													if(sendKeys(driver, bp.getParentFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.FundsPage,20),commonFolderName.split("\\(")[0],"Common folder text box", action.BOOLEAN)) {
														appLog.info("value pass in folder Common text box: "+commonFolderName.split("\\(")[0]);
														if(click(driver, bp.getParentFolderSaveButton(Workspace.FundraisingWorkspace, PageName.FundsPage, 20), "folder save button", action.BOOLEAN)) {
															appLog.info("clicked on save button");
															WebElement ele1=bp.getFolderErrorMsg(Workspace.FundraisingWorkspace, PageName.FundsPage, ErrorMessageType.FolderCreationRestrictionErrorMsg, FolderType.Common,20);
															if(ele1!=null) {
																String aa=ele1.getText().trim();
																if(aa.contains(FundsPageErrorMessage.CommonFolderCreationErrorMsg)) {
																	appLog.info("Folder error messgae is verified for folder type Common");
																	
																}else {
																	appLog.error("Folder error message is not verified for folder type Common Expected: "+FundsPageErrorMessage.CommonFolderCreationErrorMsg+"/t Actual: "+aa);
																	sa.assertTrue(false, "Folder error message is not verified for folder type Common Expected: "+FundsPageErrorMessage.CommonFolderCreationErrorMsg+"/t Actual: "+aa);
																}
																if(i==0) {
																	if(click(driver, fp.getFolderCreationErrorMessageCrossIcon(Workspace.FundraisingWorkspace, PageName.FundsPage, FolderType.Common,30),"common folder error message cross icon", action.BOOLEAN)) {
																		appLog.info("clicked on common folder error message cross icon");
																		if(fp.getFolderCreationErrorMessageOkBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, FolderType.Common,20)==null) {
																			appLog.info("error message pop is closed after click on cross icon");
																			if(click(driver, FindElement(driver, "//span[@id='add0000'][@title='Add a Folder']", "+Icon",
																					action.BOOLEAN, 30), "", action.BOOLEAN)){
																				appLog.info("clicked on Add folder Button again");
																			}else {
																				appLog.error("Not able to click on Add folder Icon so cannot check error message OK button functionality");
																				sa.assertTrue(false, "Not able to click on Add folder Icon so cannot check error message OK button functionality");
																				break;
																			}
																		}else {
																			appLog.error("Error Message Pop is not closed after click on cross icon");
																			sa.assertTrue(false, "Error Message Pop is not closed after click on cross icon");
																		}
																	}else {
																		appLog.error("Not able to click on common folder error message cross icon");
																		sa.assertTrue(false, "Not able to click on common folder error message cross icon");
																	}
																}else {
																	if(click(driver, fp.getFolderCreationErrorMessageOkBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, FolderType.Common,30),"common folder error message cross icon", action.BOOLEAN)) {
																		appLog.info("clicked on common folder error message OK button");
																		if(fp.getFolderCreationErrorMessageOkBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, FolderType.Common,20)==null) {
																			appLog.info("error message pop is closed after click on OK button");
																			
																		}else {
																			appLog.error("Error Message Pop is not closed after click on Ok button");
																			sa.assertTrue(false, "Error Message Pop is not closed after click on Ok button");
																		}
																	}else {
																		appLog.error("Not able to click on common folder error message cross icon");
																		sa.assertTrue(false, "Not able to click on common folder error message cross icon");
																	}
																}
															}else {
																appLog.error("Error message is not visible in folder type Common so cannot check Error Message. Expected: "+FundsPageErrorMessage.CommonFolderCreationErrorMsg);
																sa.assertTrue(false, "Error message is not visible in folder type Common so cannot check Error Message. Expected: "+FundsPageErrorMessage.CommonFolderCreationErrorMsg);
															}
														}else {
															appLog.error("Not able to click on save button so cannot check error message for folder type Common");
															sa.assertTrue(false, "Not able to click on save button so cannot check error message for folder type Common");
														}
													}else {
														appLog.info("Not able to pass value in folder Common text box: "+commonFolderName.split("\\(")[0]+" so cannot check error message for common folder. "+FundsPageErrorMessage.CommonFolderCreationErrorMsg);
														sa.assertTrue(false, "Not able to pass value in folder Common text box: "+commonFolderName.split("\\(")[0]+" so cannot check error message for common folder. "+FundsPageErrorMessage.CommonFolderCreationErrorMsg);
													}
											}else {
												appLog.error("Not able to click on Common folder radio button so cannot check error message"+FundsPageErrorMessage.CommonFolderCreationErrorMsg);
												sa.assertTrue(false, "Not able to click on Common folder radio button so cannot check error message"+FundsPageErrorMessage.CommonFolderCreationErrorMsg);
											}
										}else {
											appLog.error("folder radio button is not visible so cannot check error message");
											sa.assertTrue(false, "folder radio button is not visible so cannot check error message");
										}
									}
									if(click(driver, FindElement(driver, "//span[@id='add0000'][@title='Add a Folder']", "+Icon",
											action.BOOLEAN, 30), "", action.BOOLEAN)){
										for(int i=0; i<2; i++) {
											WebElement ele=null;
											ele=bp.getFolderTypeRadioButton(FolderType.Internal,Workspace.FundraisingWorkspace,PageName.FundsPage, 30);
											if(ele!=null) {
												if(click(driver, ele,"Internal folder radio button icon", action.BOOLEAN)) {
													appLog.info("clicked on Internal radio button");
														if(sendKeys(driver, bp.getParentFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.FundsPage,20),InternalFolderName.split("\\(")[0],"Internal folder text box", action.BOOLEAN)) {
															appLog.info("value pass in folder Internal text box: "+InternalFolderName.split("\\(")[0]);
															if(click(driver, bp.getParentFolderSaveButton(Workspace.FundraisingWorkspace, PageName.FundsPage, 20), "folder save button", action.BOOLEAN)) {
																appLog.info("clicked on save button");
																WebElement ele1=bp.getFolderErrorMsg(Workspace.FundraisingWorkspace, PageName.FundsPage, ErrorMessageType.FolderCreationRestrictionErrorMsg, FolderType.Internal,20);
																if(ele1!=null) {
																	String aa=ele1.getText().trim();
																	if(aa.contains(FundsPageErrorMessage.InternalFolderCreationErrorMsg)) {
																		appLog.info("Folder error messgae is verified for folder type Internal");
																		
																	}else {
																		appLog.error("Folder error message is not verified for folder type Internal Expected: "+FundsPageErrorMessage.InternalFolderCreationErrorMsg+"/t Actual: "+aa);
																		sa.assertTrue(false, "Folder error message is not verified for folder type Internal Expected: "+FundsPageErrorMessage.InternalFolderCreationErrorMsg+"/t Actual: "+aa);
																	}
																	if(i==0) {
																		if(click(driver, fp.getFolderCreationErrorMessageCrossIcon(Workspace.FundraisingWorkspace, PageName.FundsPage, FolderType.Internal,30),"Internal folder error message cross icon", action.BOOLEAN)) {
																			appLog.info("clicked on Internal folder error message cross icon");
																			if(fp.getFolderCreationErrorMessageOkBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, FolderType.Internal,20)==null) {
																				appLog.info("error message pop is closed after click on cross icon");
																				if(click(driver, FindElement(driver, "//span[@id='add0000'][@title='Add a Folder']", "+Icon",
																						action.BOOLEAN, 30), "", action.BOOLEAN)){
																					appLog.info("clicked on Add folder Button again");
																				}else {
																					appLog.error("Not able to click on Add folder Icon so cannot check error message OK button functionality");
																					sa.assertTrue(false, "Not able to click on Add folder Icon so cannot check error message OK button functionality");
																					break;
																				}
																			}else {
																				appLog.error("Error Message Pop is not closed after click on cross icon");
																				sa.assertTrue(false, "Error Message Pop is not closed after click on cross icon");
																			}
																		}else {
																			appLog.error("Not able to click on Internal folder error message cross icon");
																			sa.assertTrue(false, "Not able to click on Internal folder error message cross icon");
																		}
																	}else {
																		if(click(driver, fp.getFolderCreationErrorMessageOkBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, FolderType.Internal,30),"Internal folder error message cross icon", action.BOOLEAN)) {
																			appLog.info("clicked on Internal folder error message OK button");
																			if(fp.getFolderCreationErrorMessageOkBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, FolderType.Internal,20)==null) {
																				appLog.info("error message pop is closed after click on OK button");
																			}else {
																				appLog.error("Error Message Pop is not closed after click on Ok button");
																				sa.assertTrue(false, "Error Message Pop is not closed after click on Ok button");
																			}
																		}else {
																			appLog.error("Not able to click on Internal folder error message cross icon"+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
																			sa.assertTrue(false, "Not able to click on Internal folder error message cross icon"+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
																		}
																	}
																}else {
																	appLog.error("Error message is not visible in folder type Internal so cannot check Error Message. Expected: "+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
																	sa.assertTrue(false, "Error message is not visible in folder type Internal so cannot check Error Message. Expected: "+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
																}
															}else {
																appLog.error("Not able to click on save button so cannot check error message for folder type Internal"+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
																sa.assertTrue(false, "Not able to click on save button so cannot check error message for folder type Internal"+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
															}
														}else {
															appLog.info("Not able to pass value in folder Internal text box: "+InternalFolderName.split("\\(")[0]+" so cannot check error message"+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
															sa.assertTrue(false, "Not able to pass value in folder Internal text box: "+InternalFolderName.split("\\(")[0]+" so cannot check error message"+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
														}
												}else {
													appLog.error("Not able to click on Internal folder radio button so cannot check error message"+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
													sa.assertTrue(false, "Not able to click on Internal folder radio button so cannot check error message"+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
												}
											}else {
												appLog.error("folder radio button is not visible so cannot check error message"+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
												sa.assertTrue(false, "folder radio button is not visible so cannot check error message"+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
											}
										}
										
									}else {
										appLog.error("Not able to click on Add folder icon so cannot check internal folder error message. Error Message: "+fp.InternalFolderCreationErrorMsg);
										sa.assertTrue(false, "Not able to click on Add folder icon so cannot check internal folder error message. Error Message: "+fp.InternalFolderCreationErrorMsg);
									}
									if(fp.createParentFolder(STDFolderName, FolderType.Standard, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
										appLog.info("Parent Standard folder is created.");
									} else {
										appLog.error("Parent Standard folder is not created.");
										sa.assertTrue(false,"Parent Standard folder is not created.");
									}
									if(fp.createParentFolder(SHRDFolderName.split("\\(")[0], FolderType.Shared, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
										appLog.info("Parent Shared folder is created.");
									} else {
										appLog.error("Parent Shared folder is not created.");
										sa.assertTrue(false,"Parent Shared folder is not created.");
									}
									if(fp.createFolderStructure(STDFolderName+"/STD Child Folder", FolderType.Standard, Workspace.FundraisingWorkspace, PageName.FundsPage, 30).isEmpty()){
										appLog.info("STD Child Folder is created successfully.");
									} else {
										appLog.error("STD child folder cannot be created.");
										sa.assertTrue(false,"STD child folder cannot be created.");
									}
									if(fp.createFolderStructure(SHRDFolderName.split("\\(")[0]+"/SHD Child Folder", FolderType.Internal, Workspace.FundraisingWorkspace, PageName.FundsPage, 30).isEmpty()){
										appLog.info("SHD child folder is created successfully.");
									} else {
										appLog.error("SHD child folder cannot be created.");
										sa.assertTrue(false,"SHD child folder cannot be created.");
									}
									String[] ss= {STDFolderName,SHRDFolderName.split("\\(")[0]};
									for(int i=0; i<2; i++) {
									if(click(driver, FindElement(driver, "//span[@id='add0000'][@title='Add a Folder']", "+Icon",
											action.BOOLEAN, 30), "", action.BOOLEAN)){
											WebElement ele=null;
											if(i==0) {
												ele=bp.getFolderTypeRadioButton(FolderType.Standard,Workspace.FundraisingWorkspace,PageName.FundsPage, 30);									
											}else {
												ele=bp.getFolderTypeRadioButton(FolderType.Shared,Workspace.FundraisingWorkspace,PageName.FundsPage, 30);	
											}
											if(ele!=null) {
												if(click(driver, ele,ss[i]+" folder radio button icon", action.BOOLEAN)) {
													appLog.info("clicked on "+ss[i]+" radio button");
														if(sendKeys(driver, bp.getParentFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.FundsPage,20),ss[i],ss[i]+" folder text box", action.BOOLEAN)) {
															appLog.info("value pass in folder text box: "+ss[i]);
															if(click(driver, bp.getParentFolderSaveButton(Workspace.FundraisingWorkspace, PageName.FundsPage, 20), "folder save button", action.BOOLEAN)) {
																appLog.info("clicked on save button");
																ThreadSleep(2000);
																if(fp.getParentFolderErrorMsg(Workspace.FundraisingWorkspace, PageName.FundsPage, ErrorMessageType.DuplicateFolder, 30)!=null){
																	String msg = getText(driver, fp.getParentFolderErrorMsg(Workspace.FundraisingWorkspace, PageName.FundsPage, ErrorMessageType.DuplicateFolder, 30), "", action.BOOLEAN);
																	appLog.error("Error Message is displaying : "+msg);
																	if(msg.contains(FundsPageErrorMessage.FolderAlreadyExistErrormsg)) {
																		appLog.info("Error msg is verified successfully.");
																	}else {
																		appLog.error("Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
																		sa.assertTrue(false, "Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
																	}
																} else {
																	appLog.error("Duplicate Folder Error Message Pop Up Is not opening after clicking on save button for standard Folder.");
																	sa.assertTrue(false,"Duplicate Folder Error Message Pop Up Is not opening after clicking on save button for standard Folder.");
																}
																if(click(driver, fp.getFolderCreationErrorMessageOkBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, FolderType.Standard, 10), "Duplicate folder Error message ok button", action.BOOLEAN)){
																	System.err.println("successfulyl clicked on ok button.");
																} else {
																	System.err.println("\n\n\nNot able to click on OK Button.\n\n\n");
																}
															}else {
																appLog.error("Not able to click on save button so cannot check error message for folder type "+ss[i]+""+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
																sa.assertTrue(false, "Not able to click on save button so cannot check error message for folder type "+ss[i]+""+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
															}
														}else {
															appLog.info("Not able to pass value in folder text box: "+ss[i]+" so cannot check error message"+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
															sa.assertTrue(false, "Not able to pass value in folder text box: "+ss[i]+" so cannot check error message"+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
														}
												}else {
													appLog.error("Not able to click on "+ss[i]+" folder radio button so cannot check error message"+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
													sa.assertTrue(false, "Not able to click on "+ss[i]+" folder radio button so cannot check error message"+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
												}
											}else {
												appLog.error("folder radio button is not visible so cannot check error message"+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
												sa.assertTrue(false, "folder radio button is not visible so cannot check error message"+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
											}
										
									}else {
										appLog.error("Not able to click on Add folder icon so cannot check folder error message. Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
										sa.assertTrue(false, "Not able to click on Add folder icon so cannot check folder error message. Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
									}
									}
									
								}else {
									appLog.error("Not able to click on Add folder icon so cannot check error messsgae in fundraising workspace");
									sa.assertTrue(false, "Not able to click on Add folder icon so cannot check error messsgae in fundraising workspace");
								}
							} else {
								appLog.error("Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
								sa.assertTrue(false,"Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
								fp.recover(Workspace.FundraisingWorkspace, 30);
							}
						} else {
							appLog.error("Next button of step 1 of 3 cannot be click, So cannot check cancel button functionality..");
							sa.assertTrue(false,"Next button of step 1 of 3 cannot be click, So cannot check cancel button functionality.");
							fp.recover(Workspace.FundraisingWorkspace, 30);
						}
					} else {
						appLog.error("One of the mandatory field is not filled, So cannot check cancel button functionality.");
						sa.assertTrue(false,"One of the mandatory field is not filled, So cannot check cancel button functionality.");
						fp.recover(Workspace.FundraisingWorkspace, 30);
					}
				} else {
					appLog.error("Build Investor workspace button cannot be clicked, So cannot check cancel button functionality.");
					sa.assertTrue(false,"Build Investor workspace button cannot be clicked, So cannot check cancel button functionality.");
					fp.recover(Workspace.FundraisingWorkspace, 30);
				}
			} else {
				appLog.error(M8F4+" fund is not available, so cannot continue with the tc.");
				sa.assertTrue(false,M8F4+" fund is not available, so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot continue with the tc.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M8tc007_VerifyAddAFolderPopUpStep2Of3(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String STDFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		String[] stdFolderName=STDFolderName.split(",");
		String[] commonFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath).split(",");
		String[] InternalFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.InternalPath).split(",");
		String[] SHRDFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath).split(",");
		List<String> PrefixValue = getValueBasedOnLabel(filePath, "SpecialChar",excelLabel.INVALID_FOLDER_NAME, 1);
		List<String> specialChar = getValueBasedOnLabel(filePath, "SpecialChar",excelLabel.FOLDER_NAME, 1);
		boolean flag = true;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F1)){
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Funcraising wokspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 30), "Buld Investor workspace button", action.BOOLEAN)){
					if(sendKeys(driver, fp.getFundVintageYearField(Workspace.FundraisingWorkspace, 30), "2013", "Vintage year", action.BOOLEAN)){
						appLog.info("Successfully passed value to vintage year text box.");
					} else {
						appLog.error("Cannot pass value to vintage year text box.");
						sa.assertTrue(false,"Cannot pass value to vintage year text box.");
					}

					if(sendKeys(driver, fp.getFundSizeField(Workspace.FundraisingWorkspace, 30), "5000", "Fund Size", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund size text box.");
					} else {
						appLog.error("Cannot pass value to Fund size text box.");
						sa.assertTrue(false,"Cannot pass value to Fund size text box.");
					}

					if(sendKeys(driver, fp.getFundContactField(Workspace.FundraisingWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund contact text box.");
					} else {
						appLog.error("Cannot pass value to Fund contact text box.");
						sa.assertTrue(false,"Cannot pass value to Fund contact text box.");
						flag=false;
					}

					if(sendKeys(driver, fp.getFundEmailField(Workspace.FundraisingWorkspace, 30), "abc@abc.com", "Fund Email", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Email text box.");
					} else {
						appLog.error("Cannot pass value to Fund Email text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Email text box.");
						flag=false;
					}

					if(sendKeys(driver, fp.getFundPhoneField(Workspace.FundraisingWorkspace, 30), "987654321", "Fund Phone", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Phone text box.");
					} else {
						appLog.error("Cannot pass value to Fund Phone text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Phone text box.");
						flag=false;
					}

					if(sendKeys(driver, fp.getFundDescriptionField(Workspace.FundraisingWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Description text box.");
					} else {
						appLog.error("Cannot pass value to Fund Description text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Description text box.");
					}

					if(flag){
						if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 30), "Next button of step 1 of 3", action.BOOLEAN)){
							if(fp.getStep2Of3Header(Workspace.FundraisingWorkspace, 30)!=null){
								appLog.info("Step 2 of 3 pop up is successfully opened.");
								if(fp.createParentFolder(stdFolderName[0].split("\\(")[0], FolderType.Standard, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
									appLog.info("Parent Standard folder is created.");
								} else {
									appLog.error("Parent Standard folder is not created.");
									sa.assertTrue(false,"Parent Standard folder is not created.");
								}
								if(fp.createFolderStructure(stdFolderName[0]+"/"+stdFolderName[1], FolderType.Standard, Workspace.FundraisingWorkspace, PageName.FundsPage, 30).isEmpty()){
									appLog.info("STD Child Folder is created successfully.");
								} else {
									appLog.error("STD child folder cannot be created.");
									sa.assertTrue(false,"STD child folder cannot be created.");
								}
								if(fp.createParentFolder(commonFolderName[0].split("\\(")[0], FolderType.Common, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
									appLog.info("Parent Common folder is created.");
								} else {
									appLog.error("Parent Common folder is not created.");
									sa.assertTrue(false,"Parent Common folder is not created.");
								}
								if(fp.createFolderStructure(commonFolderName[0]+"/"+commonFolderName[1], FolderType.Common, Workspace.FundraisingWorkspace, PageName.FundsPage, 30).isEmpty()){
									appLog.info("Common Child Folder is created successfully.");
								} else {
									appLog.error("Common child folder cannot be created.");
									sa.assertTrue(false,"Commmon child folder cannot be created.");
								}
								if(fp.createParentFolder(SHRDFolderName[0].split("\\(")[0], FolderType.Shared, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
									appLog.info("Parent Shared folder is created.");
								} else {
									appLog.error("Parent Shared folder is not created.");
									sa.assertTrue(false,"Parent Shared folder is not created.");
								}
								if(fp.createFolderStructure(SHRDFolderName[0]+"/"+SHRDFolderName[1], FolderType.Shared, Workspace.FundraisingWorkspace, PageName.FundsPage, 30).isEmpty()){
									appLog.info("Shared Child Folder is created successfully.");
								} else {
									appLog.error("Shared child folder cannot be created.");
									sa.assertTrue(false,"Shared child folder cannot be created.");
								}
								if(fp.createParentFolder(InternalFolderName[0].split("\\(")[0], FolderType.Internal, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
									appLog.info("Parent Internal folder is created.");
								} else {
									appLog.error("Parent Internal folder is not created.");
									sa.assertTrue(false,"Parent Internal folder is not created.");
								}
								if(fp.createFolderStructure(InternalFolderName[0]+"/"+InternalFolderName[1], FolderType.Internal, Workspace.FundraisingWorkspace, PageName.FundsPage, 30).isEmpty()){
									appLog.info("Internal Child Folder is created successfully.");
								} else {
									appLog.error("Internal child folder cannot be created.");
									sa.assertTrue(false,"Internal child folder cannot be created.");
								}
								String id=null;
								id=fp.getCreatedFolderId(stdFolderName[0], PageName.FundsPage, 20);
								if(id!=null) {
									if(fp.clickOnAddFolderButton(id)) {
										if(fp.verifyFolderErrorMessageSubLevel(FolderType.Standard, Workspace.FundraisingWorkspace, PageName.FundsPage,FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20)) {
											appLog.info("Error Message is verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
										}else {
											appLog.error("Error Message is not verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg+"\tActual: ");
										}
										if(fp.verifyFolderErrorMessageSubLevel(FolderType.Standard, Workspace.FundraisingWorkspace, PageName.FundsPage,FundsPageErrorMessage.suffixErrorMsg,PrefixValue.get(0),ErrorMessageType.PrefixErrorMsg,20)) {
											appLog.info("Error Message is verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.suffixErrorMsg);
										}else {
											appLog.error("Error Message is not verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.suffixErrorMsg);
										}
										if(fp.verifyFolderErrorMessageSubLevel(FolderType.Standard, Workspace.FundraisingWorkspace, PageName.FundsPage,FundsPageErrorMessage.speicalCharErrorMsgFundsPage,specialChar.get(0),ErrorMessageType.SpiecalCharErrorMsg,20)) {
											appLog.info("Error Message is verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsgFundsPage);
										}else {
											appLog.error("Error Message is not verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsgFundsPage);
										}
										if(sendKeys(driver, fp.getChildFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.FundsPage, 20), stdFolderName[1], stdFolderName[0]+" sub folder text box", action.BOOLEAN)) {
											if(click(driver, fp.getChildFolderSaveButton(Workspace.FundraisingWorkspace, PageName.FundsPage, 20), "sub folder save button", action.BOOLEAN)) {
												ThreadSleep(2000);
												if(fp.getParentFolderErrorMsg(Workspace.FundraisingWorkspace, PageName.FundsPage, ErrorMessageType.DuplicateFolder, 30)!=null){
													String msg = getText(driver, fp.getParentFolderErrorMsg(Workspace.FundraisingWorkspace, PageName.FundsPage, ErrorMessageType.DuplicateFolder, 30), "", action.BOOLEAN);
													appLog.error("Error Message is displaying : "+msg);
													if(msg.contains(FundsPageErrorMessage.FolderAlreadyExistErrormsg)) {
														appLog.info("Error msg is verified successfully.");
													}else {
														appLog.error("Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
														sa.assertTrue(false, "Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
													}
												} else {
													appLog.error("Duplicate Folder Error Message Pop Up Is not opening after clicking on save button for standard Folder.");
													sa.assertTrue(false,"Duplicate Folder Error Message Pop Up Is not opening after clicking on save button for standard Folder.");
												}
												if(click(driver, fp.getFolderCreationErrorMessageOkBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, FolderType.Standard, 10), "Duplicate folder Error message ok button", action.BOOLEAN)){
													System.err.println("successfulyl clicked on ok button.");
												} else {
													System.err.println("\n\n\nNot able to click on OK Button.\n\n\n");
												}
											}else {
												appLog.error("Not able to click on sub folder "+stdFolderName[0]+" save button so cannot chcek Error Message "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
												sa.assertTrue(false, "Not able to click on sub folder "+stdFolderName[0]+" save button so cannot chcek Error Message "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
											}
										}else {
											appLog.error("Not able to pass value in sub folder "+stdFolderName[0]+" so cannot check Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
											sa.assertTrue(false, "Not able to pass value in sub folder "+stdFolderName[0]+" so cannot check Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
										}
									}else {
										appLog.error("Not able to click on Add folder sub folder "+stdFolderName[0]+" so cannot check error message on "+stdFolderName[0]+" sub folder");
										sa.assertTrue(false, "Not able to click on Add folder sub folder "+stdFolderName[0]+" so cannot check error message on "+stdFolderName[0]+" sub folder");
									}
								}else {
									appLog.error(stdFolderName[0]+" folder is available in manage folder so cannot check error messgaes on Standard folder");
									sa.assertTrue(false, stdFolderName[0]+" folder is available in manage folder so cannot check error messgaes on Standard folder");
								}
								id=null;
								id=fp.getCreatedFolderId(commonFolderName[0], PageName.FundsPage, 20);
								if(id!=null) {
									if(fp.clickOnAddFolderButton(id)) {
										if(fp.verifyFolderErrorMessageSubLevel(FolderType.Common, Workspace.FundraisingWorkspace, PageName.FundsPage,FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20)) {
											appLog.info("Error Message is verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
										}else {
											appLog.error("Error Message is not verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
										}
										if(fp.verifyFolderErrorMessageSubLevel(FolderType.Common, Workspace.FundraisingWorkspace, PageName.FundsPage,FundsPageErrorMessage.suffixErrorMsg,PrefixValue.get(1),ErrorMessageType.PrefixErrorMsg,20)) {
											appLog.info("Error Message is verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.suffixErrorMsg);
										}else {
											appLog.error("Error Message is not verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.suffixErrorMsg);
										}
										if(fp.verifyFolderErrorMessageSubLevel(FolderType.Common, Workspace.FundraisingWorkspace, PageName.FundsPage,FundsPageErrorMessage.speicalCharErrorMsgFundsPage,specialChar.get(0),ErrorMessageType.SpiecalCharErrorMsg,20)) {
											appLog.info("Error Message is verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsgFundsPage);
										}else {
											appLog.error("Error Message is not verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsgFundsPage);
										}
										if(sendKeys(driver, fp.getChildFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.FundsPage, 20), commonFolderName[1], commonFolderName[0]+" sub folder text box", action.BOOLEAN)) {
											if(click(driver, fp.getChildFolderSaveButton(Workspace.FundraisingWorkspace, PageName.FundsPage, 20), "sub folder save button", action.BOOLEAN)) {
												ThreadSleep(2000);
												if(fp.getParentFolderErrorMsg(Workspace.FundraisingWorkspace, PageName.FundsPage, ErrorMessageType.DuplicateFolder, 30)!=null){
													String msg = getText(driver, fp.getParentFolderErrorMsg(Workspace.FundraisingWorkspace, PageName.FundsPage, ErrorMessageType.DuplicateFolder, 30), "", action.BOOLEAN);
													appLog.error("Error Message is displaying : "+msg);
													if(msg.contains(FundsPageErrorMessage.FolderAlreadyExistErrormsg)) {
														appLog.info("Error msg is verified successfully.");
													}else {
														appLog.error("Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
														sa.assertTrue(false, "Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
													}
												} else {
													appLog.error("Duplicate Folder Error Message Pop Up Is not opening after clicking on save button for standard Folder.");
													sa.assertTrue(false,"Duplicate Folder Error Message Pop Up Is not opening after clicking on save button for standard Folder.");
												}
												if(click(driver, fp.getFolderCreationErrorMessageOkBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, FolderType.Standard, 10), "Duplicate folder Error message ok button", action.BOOLEAN)){
													System.err.println("successfulyl clicked on ok button.");
												} else {
													System.err.println("\n\n\nNot able to click on OK Button.\n\n\n");
												}
											}else {
												appLog.error("Not able to click on sub folder "+commonFolderName[0]+" save button so cannot chcek Error Message "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
												sa.assertTrue(false, "Not able to click on sub folder "+commonFolderName[0]+" save button so cannot chcek Error Message "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
											}
										}else {
											appLog.error("Not able to pass value in sub folder "+commonFolderName[0]+" so cannot check Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
											sa.assertTrue(false, "Not able to pass value in sub folder "+commonFolderName[0]+" so cannot check Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
										}
									}else {
										appLog.error("Not able to click on Add folder sub folder "+commonFolderName[0]+" so cannot check error message on "+commonFolderName[0]+" sub folder");
										sa.assertTrue(false, "Not able to click on Add folder sub folder "+commonFolderName[0]+" so cannot check error message on "+commonFolderName[0]+" sub folder");
									}
								}else {
									appLog.error(commonFolderName[0]+" folder is available in manage folder so cannot check error messgaes on Standard folder");
									sa.assertTrue(false, commonFolderName[0]+" folder is available in manage folder so cannot check error messgaes on Standard folder");
								}
								id=null;
								id=fp.getCreatedFolderId(SHRDFolderName[0], PageName.FundsPage, 20);
								if(id!=null) {
									if(fp.clickOnAddFolderButton(id)) {
										if(fp.verifyFolderErrorMessageSubLevel(FolderType.Shared, Workspace.FundraisingWorkspace, PageName.FundsPage,FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20)) {
											appLog.info("Error Message is verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
										}else {
											appLog.error("Error Message is not verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
										}
										if(fp.verifyFolderErrorMessageSubLevel(FolderType.Shared, Workspace.FundraisingWorkspace, PageName.FundsPage,FundsPageErrorMessage.suffixErrorMsg,PrefixValue.get(2),ErrorMessageType.PrefixErrorMsg,20)) {
											appLog.info("Error Message is verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.suffixErrorMsg);
										}else {
											appLog.error("Error Message is not verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.suffixErrorMsg);
										}
										if(fp.verifyFolderErrorMessageSubLevel(FolderType.Shared, Workspace.FundraisingWorkspace, PageName.FundsPage,FundsPageErrorMessage.speicalCharErrorMsgFundsPage,specialChar.get(0),ErrorMessageType.SpiecalCharErrorMsg,20)) {
											appLog.info("Error Message is verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsgFundsPage);
										}else {
											appLog.error("Error Message is not verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsgFundsPage);
										}
										if(sendKeys(driver, fp.getChildFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.FundsPage, 20), SHRDFolderName[1], SHRDFolderName[0]+" sub folder text box", action.BOOLEAN)) {
											if(click(driver, fp.getChildFolderSaveButton(Workspace.FundraisingWorkspace, PageName.FundsPage, 20), "sub folder save button", action.BOOLEAN)) {
												ThreadSleep(2000);
												if(fp.getParentFolderErrorMsg(Workspace.FundraisingWorkspace, PageName.FundsPage, ErrorMessageType.DuplicateFolder, 30)!=null){
													String msg = getText(driver, fp.getParentFolderErrorMsg(Workspace.FundraisingWorkspace, PageName.FundsPage, ErrorMessageType.DuplicateFolder, 30), "", action.BOOLEAN);
													appLog.error("Error Message is displaying : "+msg);
													if(msg.contains(FundsPageErrorMessage.FolderAlreadyExistErrormsg)) {
														appLog.info("Error msg is verified successfully.");
													}else {
														appLog.error("Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
														sa.assertTrue(false, "Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
													}
												} else {
													appLog.error("Duplicate Folder Error Message Pop Up Is not opening after clicking on save button for standard Folder.");
													sa.assertTrue(false,"Duplicate Folder Error Message Pop Up Is not opening after clicking on save button for standard Folder.");
												}
												if(click(driver, fp.getFolderCreationErrorMessageOkBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, FolderType.Standard, 10), "Duplicate folder Error message ok button", action.BOOLEAN)){
													System.err.println("successfulyl clicked on ok button.");
												} else {
													System.err.println("\n\n\nNot able to click on OK Button.\n\n\n");
												}
											}else {
												appLog.error("Not able to click on sub folder "+SHRDFolderName[0]+" save button so cannot chcek Error Message "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
												sa.assertTrue(false, "Not able to click on sub folder "+SHRDFolderName[0]+" save button so cannot chcek Error Message "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
											}
										}else {
											appLog.error("Not able to pass value in sub folder "+SHRDFolderName[0]+" so cannot check Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
											sa.assertTrue(false, "Not able to pass value in sub folder "+SHRDFolderName[0]+" so cannot check Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
										}
									}else {
										appLog.error("Not able to click on Add folder sub folder "+SHRDFolderName[0]+" so cannot check error message on "+commonFolderName[0]+" sub folder");
										sa.assertTrue(false, "Not able to click on Add folder sub folder "+SHRDFolderName[0]+" so cannot check error message on "+commonFolderName[0]+" sub folder");
									}
								}else {
									appLog.error(SHRDFolderName[0]+" folder is available in manage folder so cannot check error messgaes on Standard folder");
									sa.assertTrue(false, SHRDFolderName[0]+" folder is available in manage folder so cannot check error messgaes on Standard folder");
								}
								id=null;
								id=fp.getCreatedFolderId(InternalFolderName[0], PageName.FundsPage, 20);
								if(id!=null) {
									if(fp.clickOnAddFolderButton(id)) {
										if(fp.verifyFolderErrorMessageSubLevel(FolderType.Internal, Workspace.FundraisingWorkspace, PageName.FundsPage,FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20)) {
											appLog.info("Error Message is verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
										}else {
											appLog.error("Error Message is not verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
										}
										if(fp.verifyFolderErrorMessageSubLevel(FolderType.Internal, Workspace.FundraisingWorkspace, PageName.FundsPage,FundsPageErrorMessage.suffixErrorMsg,PrefixValue.get(2),ErrorMessageType.PrefixErrorMsg,20)) {
											appLog.info("Error Message is verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.suffixErrorMsg);
										}else {
											appLog.error("Error Message is not verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.suffixErrorMsg);
										}
										if(fp.verifyFolderErrorMessageSubLevel(FolderType.Internal, Workspace.FundraisingWorkspace, PageName.FundsPage,FundsPageErrorMessage.speicalCharErrorMsgFundsPage,specialChar.get(0),ErrorMessageType.SpiecalCharErrorMsg,20)) {
											appLog.info("Error Message is verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsgFundsPage);
										}else {
											appLog.error("Error Message is not verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsgFundsPage);
										}
										if(sendKeys(driver, fp.getChildFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.FundsPage, 20), InternalFolderName[1], InternalFolderName[0]+" sub folder text box", action.BOOLEAN)) {
											if(click(driver, fp.getChildFolderSaveButton(Workspace.FundraisingWorkspace, PageName.FundsPage, 20), "sub folder save button", action.BOOLEAN)) {
												ThreadSleep(2000);
												if(fp.getParentFolderErrorMsg(Workspace.FundraisingWorkspace, PageName.FundsPage, ErrorMessageType.DuplicateFolder, 30)!=null){
													String msg = getText(driver, fp.getParentFolderErrorMsg(Workspace.FundraisingWorkspace, PageName.FundsPage, ErrorMessageType.DuplicateFolder, 30), "", action.BOOLEAN);
													appLog.error("Error Message is displaying : "+msg);
													if(msg.contains(FundsPageErrorMessage.FolderAlreadyExistErrormsg)) {
														appLog.info("Error msg is verified successfully.");
													}else {
														appLog.error("Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
														sa.assertTrue(false, "Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
													}
												} else {
													appLog.error("Duplicate Folder Error Message Pop Up Is not opening after clicking on save button for standard Folder.");
													sa.assertTrue(false,"Duplicate Folder Error Message Pop Up Is not opening after clicking on save button for standard Folder.");
												}
												if(click(driver, fp.getFolderCreationErrorMessageOkBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, FolderType.Standard, 10), "Duplicate folder Error message ok button", action.BOOLEAN)){
													System.err.println("successfulyl clicked on ok button.");
												} else {
													System.err.println("\n\n\nNot able to click on OK Button.\n\n\n");
												}
											}else {
												appLog.error("Not able to click on sub folder "+InternalFolderName[0]+" save button so cannot chcek Error Message "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
												sa.assertTrue(false, "Not able to click on sub folder "+InternalFolderName[0]+" save button so cannot chcek Error Message "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
											}
										}else {
											appLog.error("Not able to pass value in sub folder "+InternalFolderName[0]+" so cannot check Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
											sa.assertTrue(false, "Not able to pass value in sub folder "+InternalFolderName[0]+" so cannot check Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
										}
									}else {
										appLog.error("Not able to click on Add folder sub folder "+InternalFolderName[0]+" so cannot check error message on "+InternalFolderName[0]+" sub folder");
										sa.assertTrue(false, "Not able to click on Add folder sub folder "+InternalFolderName[0]+" so cannot check error message on "+InternalFolderName[0]+" sub folder");
									}
								}else {
									appLog.error(InternalFolderName[0]+" folder is available in manage folder so cannot check error messgaes on Standard folder");
									sa.assertTrue(false, InternalFolderName[0]+" folder is available in manage folder so cannot check error messgaes on Standard folder");
								}
							
							} else {
								appLog.error("Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
								sa.assertTrue(false,"Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
								fp.recover(Workspace.FundraisingWorkspace, 30);
							}
						} else {
							appLog.error("Next button of step 1 of 3 cannot be click, So cannot check cancel button functionality..");
							sa.assertTrue(false,"Next button of step 1 of 3 cannot be click, So cannot check cancel button functionality.");
							fp.recover(Workspace.FundraisingWorkspace, 30);
						}
					} else {
						appLog.error("One of the mandatory field is not filled, So cannot check cancel button functionality.");
						sa.assertTrue(false,"One of the mandatory field is not filled, So cannot check cancel button functionality.");
						fp.recover(Workspace.FundraisingWorkspace, 30);
					}
				} else {
					appLog.error("Build Investor workspace button cannot be clicked, So cannot check cancel button functionality.");
					sa.assertTrue(false,"Build Investor workspace button cannot be clicked, So cannot check cancel button functionality.");
					fp.recover(Workspace.FundraisingWorkspace, 30);
				}
			} else {
				appLog.error(M8F1+" fund is not available, so cannot continue with the tc.");
				sa.assertTrue(false,M8F1+" fund is not available, so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot continue with the tc.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
		
	}
	
	@Test
	public void M8tc020_VerifyAddAFolderPopUpStep2Of3(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependsOnMethod = "M8tc007_VerifyAddAFolderPopUpStep2Of3";
		String STDFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M8tc007_VerifyAddAFolderPopUpStep2Of3",excelLabel.StandardPath);
		String[] stdFolderName=STDFolderName.split(",");
		String[] commonFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M8tc007_VerifyAddAFolderPopUpStep2Of3",excelLabel.CommonPath).split(",");
		String[] InternalFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M8tc007_VerifyAddAFolderPopUpStep2Of3",excelLabel.InternalPath).split(",");
		String[] SHRDFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M8tc007_VerifyAddAFolderPopUpStep2Of3",excelLabel.SharedPath).split(",");
		List<String> PrefixValue = getValueBasedOnLabel(filePath, "SpecialChar",excelLabel.INVALID_FOLDER_NAME, 1);
		List<String> specialChar = getValueBasedOnLabel(filePath, "SpecialChar",excelLabel.FOLDER_NAME, 1);
		boolean flag = true;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F4)){
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Funcraising wokspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.InvestorWorkspace, 30), "Buld Investor workspace button", action.BOOLEAN)){
					if(sendKeys(driver, fp.getFundVintageYearField(Workspace.InvestorWorkspace, 30), "2013", "Vintage year", action.BOOLEAN)){
						appLog.info("Successfully passed value to vintage year text box.");
					} else {
						appLog.error("Cannot pass value to vintage year text box.");
						sa.assertTrue(false,"Cannot pass value to vintage year text box.");
					}

					if(sendKeys(driver, fp.getFundSizeField(Workspace.InvestorWorkspace, 30), "5000", "Fund Size", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund size text box.");
					} else {
						appLog.error("Cannot pass value to Fund size text box.");
						sa.assertTrue(false,"Cannot pass value to Fund size text box.");
					}

					if(sendKeys(driver, fp.getFundContactField(Workspace.InvestorWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund contact text box.");
					} else {
						appLog.error("Cannot pass value to Fund contact text box.");
						sa.assertTrue(false,"Cannot pass value to Fund contact text box.");
						flag=false;
					}

					if(sendKeys(driver, fp.getFundEmailField(Workspace.InvestorWorkspace, 30), "abc@abc.com", "Fund Email", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Email text box.");
					} else {
						appLog.error("Cannot pass value to Fund Email text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Email text box.");
						flag=false;
					}

					if(sendKeys(driver, fp.getFundPhoneField(Workspace.InvestorWorkspace, 30), "987654321", "Fund Phone", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Phone text box.");
					} else {
						appLog.error("Cannot pass value to Fund Phone text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Phone text box.");
						flag=false;
					}

					if(sendKeys(driver, fp.getFundDescriptionField(Workspace.InvestorWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Description text box.");
					} else {
						appLog.error("Cannot pass value to Fund Description text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Description text box.");
					}

					if(flag){
						if(click(driver, fp.getNext1Of3Button(Workspace.InvestorWorkspace, 30), "Next button of step 1 of 3", action.BOOLEAN)){
							if(fp.getStep2Of3Header(Workspace.InvestorWorkspace, 30)!=null){
								appLog.info("Step 2 of 3 pop up is successfully opened.");
								if(fp.createParentFolder(stdFolderName[0].split("\\(")[0], FolderType.Standard, PageName.FundsPage, Workspace.InvestorWorkspace, 30)){
									appLog.info("Parent Standard folder is created.");
								} else {
									appLog.error("Parent Standard folder is not created.");
									sa.assertTrue(false,"Parent Standard folder is not created.");
								}
								if(fp.createFolderStructure(stdFolderName[0]+"/"+stdFolderName[1], FolderType.Standard, Workspace.InvestorWorkspace, PageName.FundsPage, 30).isEmpty()){
									appLog.info("STD Child Folder is created successfully.");
								} else {
									appLog.error("STD child folder cannot be created.");
									sa.assertTrue(false,"STD child folder cannot be created.");
								}
								if(fp.createParentFolder(commonFolderName[0].split("\\(")[0], FolderType.Common, PageName.FundsPage, Workspace.InvestorWorkspace, 30)){
									appLog.info("Parent Common folder is created.");
								} else {
									appLog.error("Parent Common folder is not created.");
									sa.assertTrue(false,"Parent Common folder is not created.");
								}
								if(fp.createFolderStructure(commonFolderName[0]+"/"+commonFolderName[1], FolderType.Common, Workspace.InvestorWorkspace, PageName.FundsPage, 30).isEmpty()){
									appLog.info("Common Child Folder is created successfully.");
								} else {
									appLog.error("Common child folder cannot be created.");
									sa.assertTrue(false,"Commmon child folder cannot be created.");
								}
								if(fp.createParentFolder(SHRDFolderName[0].split("\\(")[0], FolderType.Shared, PageName.FundsPage, Workspace.InvestorWorkspace, 30)){
									appLog.info("Parent Shared folder is created.");
								} else {
									appLog.error("Parent Shared folder is not created.");
									sa.assertTrue(false,"Parent Shared folder is not created.");
								}
								if(fp.createFolderStructure(SHRDFolderName[0]+"/"+SHRDFolderName[1], FolderType.Shared, Workspace.InvestorWorkspace, PageName.FundsPage, 30).isEmpty()){
									appLog.info("Shared Child Folder is created successfully.");
								} else {
									appLog.error("Shared child folder cannot be created.");
									sa.assertTrue(false,"Shared child folder cannot be created.");
								}
								if(fp.createParentFolder(InternalFolderName[0].split("\\(")[0], FolderType.Internal, PageName.FundsPage, Workspace.InvestorWorkspace, 30)){
									appLog.info("Parent Internal folder is created.");
								} else {
									appLog.error("Parent Internal folder is not created.");
									sa.assertTrue(false,"Parent Internal folder is not created.");
								}
								if(fp.createFolderStructure(InternalFolderName[0]+"/"+InternalFolderName[1], FolderType.Internal, Workspace.InvestorWorkspace, PageName.FundsPage, 30).isEmpty()){
									appLog.info("Internal Child Folder is created successfully.");
								} else {
									appLog.error("Internal child folder cannot be created.");
									sa.assertTrue(false,"Internal child folder cannot be created.");
								}
								String id=null;
								id=fp.getCreatedFolderId(stdFolderName[0], PageName.FundsPage, 20);
								if(id!=null) {
									if(fp.clickOnAddFolderButton(id)) {
										if(fp.verifyFolderErrorMessageSubLevel(FolderType.Standard, Workspace.InvestorWorkspace, PageName.FundsPage,FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20)) {
											appLog.info("Error Message is verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
										}else {
											appLog.error("Error Message is not verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg+"\tActual: ");
										}
										if(fp.verifyFolderErrorMessageSubLevel(FolderType.Standard, Workspace.InvestorWorkspace, PageName.FundsPage,FundsPageErrorMessage.suffixErrorMsg,PrefixValue.get(0),ErrorMessageType.PrefixErrorMsg,20)) {
											appLog.info("Error Message is verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.suffixErrorMsg);
										}else {
											appLog.error("Error Message is not verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.suffixErrorMsg);
										}
										if(fp.verifyFolderErrorMessageSubLevel(FolderType.Standard, Workspace.InvestorWorkspace, PageName.FundsPage,FundsPageErrorMessage.speicalCharErrorMsgFundsPage,specialChar.get(0),ErrorMessageType.SpiecalCharErrorMsg,20)) {
											appLog.info("Error Message is verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsgFundsPage);
										}else {
											appLog.error("Error Message is not verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsgFundsPage);
										}
										if(sendKeys(driver, fp.getChildFolderNameTextBox(Workspace.InvestorWorkspace, PageName.FundsPage, 20), stdFolderName[1], stdFolderName[0]+" sub folder text box", action.BOOLEAN)) {
											if(click(driver, fp.getChildFolderSaveButton(Workspace.InvestorWorkspace, PageName.FundsPage, 20), "sub folder save button", action.BOOLEAN)) {
												ThreadSleep(2000);
												if(fp.getParentFolderErrorMsg(Workspace.InvestorWorkspace, PageName.FundsPage, ErrorMessageType.DuplicateFolder, 30)!=null){
													String msg = getText(driver, fp.getParentFolderErrorMsg(Workspace.InvestorWorkspace, PageName.FundsPage, ErrorMessageType.DuplicateFolder, 30), "", action.BOOLEAN);
													appLog.error("Error Message is displaying : "+msg);
													if(msg.contains(FundsPageErrorMessage.FolderAlreadyExistErrormsg)) {
														appLog.info("Error msg is verified successfully.");
													}else {
														appLog.error("Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
														sa.assertTrue(false, "Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
													}
												} else {
													appLog.error("Duplicate Folder Error Message Pop Up Is not opening after clicking on save button for standard Folder.");
													sa.assertTrue(false,"Duplicate Folder Error Message Pop Up Is not opening after clicking on save button for standard Folder.");
												}
												if(click(driver, fp.getFolderCreationErrorMessageOkBtn(Workspace.InvestorWorkspace, PageName.FundsPage, FolderType.Standard, 10), "Duplicate folder Error message ok button", action.BOOLEAN)){
													System.err.println("successfulyl clicked on ok button.");
												} else {
													System.err.println("\n\n\nNot able to click on OK Button.\n\n\n");
												}
											}else {
												appLog.error("Not able to click on sub folder "+stdFolderName[0]+" save button so cannot chcek Error Message "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
												sa.assertTrue(false, "Not able to click on sub folder "+stdFolderName[0]+" save button so cannot chcek Error Message "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
											}
										}else {
											appLog.error("Not able to pass value in sub folder "+stdFolderName[0]+" so cannot check Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
											sa.assertTrue(false, "Not able to pass value in sub folder "+stdFolderName[0]+" so cannot check Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
										}
									}else {
										appLog.error("Not able to click on Add folder sub folder "+stdFolderName[0]+" so cannot check error message on "+stdFolderName[0]+" sub folder");
										sa.assertTrue(false, "Not able to click on Add folder sub folder "+stdFolderName[0]+" so cannot check error message on "+stdFolderName[0]+" sub folder");
									}
								}else {
									appLog.error(stdFolderName[0]+" folder is available in manage folder so cannot check error messgaes on Standard folder");
									sa.assertTrue(false, stdFolderName[0]+" folder is available in manage folder so cannot check error messgaes on Standard folder");
								}
								id=null;
								id=fp.getCreatedFolderId(commonFolderName[0], PageName.FundsPage, 20);
								if(id!=null) {
									if(fp.clickOnAddFolderButton(id)) {
										if(fp.verifyFolderErrorMessageSubLevel(FolderType.Common, Workspace.InvestorWorkspace, PageName.FundsPage,FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20)) {
											appLog.info("Error Message is verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
										}else {
											appLog.error("Error Message is not verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
										}
										if(fp.verifyFolderErrorMessageSubLevel(FolderType.Common, Workspace.InvestorWorkspace, PageName.FundsPage,FundsPageErrorMessage.suffixErrorMsg,PrefixValue.get(1),ErrorMessageType.PrefixErrorMsg,20)) {
											appLog.info("Error Message is verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.suffixErrorMsg);
										}else {
											appLog.error("Error Message is not verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.suffixErrorMsg);
										}
										if(fp.verifyFolderErrorMessageSubLevel(FolderType.Common, Workspace.InvestorWorkspace, PageName.FundsPage,FundsPageErrorMessage.speicalCharErrorMsgFundsPage,specialChar.get(0),ErrorMessageType.SpiecalCharErrorMsg,20)) {
											appLog.info("Error Message is verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsgFundsPage);
										}else {
											appLog.error("Error Message is not verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsgFundsPage);
										}
										if(sendKeys(driver, fp.getChildFolderNameTextBox(Workspace.InvestorWorkspace, PageName.FundsPage, 20), commonFolderName[1], commonFolderName[0]+" sub folder text box", action.BOOLEAN)) {
											if(click(driver, fp.getChildFolderSaveButton(Workspace.InvestorWorkspace, PageName.FundsPage, 20), "sub folder save button", action.BOOLEAN)) {
												ThreadSleep(2000);
												if(fp.getParentFolderErrorMsg(Workspace.InvestorWorkspace, PageName.FundsPage, ErrorMessageType.DuplicateFolder, 30)!=null){
													String msg = getText(driver, fp.getParentFolderErrorMsg(Workspace.InvestorWorkspace, PageName.FundsPage, ErrorMessageType.DuplicateFolder, 30), "", action.BOOLEAN);
													appLog.error("Error Message is displaying : "+msg);
													if(msg.contains(FundsPageErrorMessage.FolderAlreadyExistErrormsg)) {
														appLog.info("Error msg is verified successfully.");
													}else {
														appLog.error("Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
														sa.assertTrue(false, "Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
													}
												} else {
													appLog.error("Duplicate Folder Error Message Pop Up Is not opening after clicking on save button for standard Folder.");
													sa.assertTrue(false,"Duplicate Folder Error Message Pop Up Is not opening after clicking on save button for standard Folder.");
												}
												if(click(driver, fp.getFolderCreationErrorMessageOkBtn(Workspace.InvestorWorkspace, PageName.FundsPage, FolderType.Standard, 10), "Duplicate folder Error message ok button", action.BOOLEAN)){
													System.err.println("successfulyl clicked on ok button.");
												} else {
													System.err.println("\n\n\nNot able to click on OK Button.\n\n\n");
												}
											}else {
												appLog.error("Not able to click on sub folder "+commonFolderName[0]+" save button so cannot chcek Error Message "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
												sa.assertTrue(false, "Not able to click on sub folder "+commonFolderName[0]+" save button so cannot chcek Error Message "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
											}
										}else {
											appLog.error("Not able to pass value in sub folder "+commonFolderName[0]+" so cannot check Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
											sa.assertTrue(false, "Not able to pass value in sub folder "+commonFolderName[0]+" so cannot check Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
										}
									}else {
										appLog.error("Not able to click on Add folder sub folder "+commonFolderName[0]+" so cannot check error message on "+commonFolderName[0]+" sub folder");
										sa.assertTrue(false, "Not able to click on Add folder sub folder "+commonFolderName[0]+" so cannot check error message on "+commonFolderName[0]+" sub folder");
									}
								}else {
									appLog.error(commonFolderName[0]+" folder is available in manage folder so cannot check error messgaes on Standard folder");
									sa.assertTrue(false, commonFolderName[0]+" folder is available in manage folder so cannot check error messgaes on Standard folder");
								}
								id=null;
								id=fp.getCreatedFolderId(SHRDFolderName[0], PageName.FundsPage, 20);
								if(id!=null) {
									if(fp.clickOnAddFolderButton(id)) {
										if(fp.verifyFolderErrorMessageSubLevel(FolderType.Shared, Workspace.InvestorWorkspace, PageName.FundsPage,FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20)) {
											appLog.info("Error Message is verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
										}else {
											appLog.error("Error Message is not verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
										}
										if(fp.verifyFolderErrorMessageSubLevel(FolderType.Shared, Workspace.InvestorWorkspace, PageName.FundsPage,FundsPageErrorMessage.suffixErrorMsg,PrefixValue.get(2),ErrorMessageType.PrefixErrorMsg,20)) {
											appLog.info("Error Message is verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.suffixErrorMsg);
										}else {
											appLog.error("Error Message is not verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.suffixErrorMsg);
										}
										if(fp.verifyFolderErrorMessageSubLevel(FolderType.Shared, Workspace.InvestorWorkspace, PageName.FundsPage,FundsPageErrorMessage.speicalCharErrorMsgFundsPage,specialChar.get(0),ErrorMessageType.SpiecalCharErrorMsg,20)) {
											appLog.info("Error Message is verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsgFundsPage);
										}else {
											appLog.error("Error Message is not verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsgFundsPage);
										}
										if(sendKeys(driver, fp.getChildFolderNameTextBox(Workspace.InvestorWorkspace, PageName.FundsPage, 20), SHRDFolderName[1], SHRDFolderName[0]+" sub folder text box", action.BOOLEAN)) {
											if(click(driver, fp.getChildFolderSaveButton(Workspace.InvestorWorkspace, PageName.FundsPage, 20), "sub folder save button", action.BOOLEAN)) {
												ThreadSleep(2000);
												if(fp.getParentFolderErrorMsg(Workspace.InvestorWorkspace, PageName.FundsPage, ErrorMessageType.DuplicateFolder, 30)!=null){
													String msg = getText(driver, fp.getParentFolderErrorMsg(Workspace.InvestorWorkspace, PageName.FundsPage, ErrorMessageType.DuplicateFolder, 30), "", action.BOOLEAN);
													appLog.error("Error Message is displaying : "+msg);
													if(msg.contains(FundsPageErrorMessage.FolderAlreadyExistErrormsg)) {
														appLog.info("Error msg is verified successfully.");
													}else {
														appLog.error("Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
														sa.assertTrue(false, "Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
													}
												} else {
													appLog.error("Duplicate Folder Error Message Pop Up Is not opening after clicking on save button for standard Folder.");
													sa.assertTrue(false,"Duplicate Folder Error Message Pop Up Is not opening after clicking on save button for standard Folder.");
												}
												if(click(driver, fp.getFolderCreationErrorMessageOkBtn(Workspace.InvestorWorkspace, PageName.FundsPage, FolderType.Standard, 10), "Duplicate folder Error message ok button", action.BOOLEAN)){
													System.err.println("successfulyl clicked on ok button.");
												} else {
													System.err.println("\n\n\nNot able to click on OK Button.\n\n\n");
												}
											}else {
												appLog.error("Not able to click on sub folder "+SHRDFolderName[0]+" save button so cannot chcek Error Message "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
												sa.assertTrue(false, "Not able to click on sub folder "+SHRDFolderName[0]+" save button so cannot chcek Error Message "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
											}
										}else {
											appLog.error("Not able to pass value in sub folder "+SHRDFolderName[0]+" so cannot check Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
											sa.assertTrue(false, "Not able to pass value in sub folder "+SHRDFolderName[0]+" so cannot check Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
										}
									}else {
										appLog.error("Not able to click on Add folder sub folder "+SHRDFolderName[0]+" so cannot check error message on "+commonFolderName[0]+" sub folder");
										sa.assertTrue(false, "Not able to click on Add folder sub folder "+SHRDFolderName[0]+" so cannot check error message on "+commonFolderName[0]+" sub folder");
									}
								}else {
									appLog.error(SHRDFolderName[0]+" folder is available in manage folder so cannot check error messgaes on Standard folder");
									sa.assertTrue(false, SHRDFolderName[0]+" folder is available in manage folder so cannot check error messgaes on Standard folder");
								}
								id=null;
								id=fp.getCreatedFolderId(InternalFolderName[0], PageName.FundsPage, 20);
								if(id!=null) {
									if(fp.clickOnAddFolderButton(id)) {
										if(fp.verifyFolderErrorMessageSubLevel(FolderType.Internal, Workspace.InvestorWorkspace, PageName.FundsPage,FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20)) {
											appLog.info("Error Message is verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
										}else {
											appLog.error("Error Message is not verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
										}
										if(fp.verifyFolderErrorMessageSubLevel(FolderType.Internal, Workspace.InvestorWorkspace, PageName.FundsPage,FundsPageErrorMessage.suffixErrorMsg,PrefixValue.get(2),ErrorMessageType.PrefixErrorMsg,20)) {
											appLog.info("Error Message is verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.suffixErrorMsg);
										}else {
											appLog.error("Error Message is not verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.suffixErrorMsg);
										}
										if(fp.verifyFolderErrorMessageSubLevel(FolderType.Internal, Workspace.InvestorWorkspace, PageName.FundsPage,FundsPageErrorMessage.speicalCharErrorMsgFundsPage,specialChar.get(0),ErrorMessageType.SpiecalCharErrorMsg,20)) {
											appLog.info("Error Message is verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsgFundsPage);
										}else {
											appLog.error("Error Message is not verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsgFundsPage);
										}
										if(sendKeys(driver, fp.getChildFolderNameTextBox(Workspace.InvestorWorkspace, PageName.FundsPage, 20), InternalFolderName[1], InternalFolderName[0]+" sub folder text box", action.BOOLEAN)) {
											if(click(driver, fp.getChildFolderSaveButton(Workspace.InvestorWorkspace, PageName.FundsPage, 20), "sub folder save button", action.BOOLEAN)) {
												ThreadSleep(2000);
												if(fp.getParentFolderErrorMsg(Workspace.InvestorWorkspace, PageName.FundsPage, ErrorMessageType.DuplicateFolder, 30)!=null){
													String msg = getText(driver, fp.getParentFolderErrorMsg(Workspace.InvestorWorkspace, PageName.FundsPage, ErrorMessageType.DuplicateFolder, 30), "", action.BOOLEAN);
													appLog.error("Error Message is displaying : "+msg);
													if(msg.contains(FundsPageErrorMessage.FolderAlreadyExistErrormsg)) {
														appLog.info("Error msg is verified successfully.");
													}else {
														appLog.error("Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
														sa.assertTrue(false, "Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
													}
												} else {
													appLog.error("Duplicate Folder Error Message Pop Up Is not opening after clicking on save button for standard Folder.");
													sa.assertTrue(false,"Duplicate Folder Error Message Pop Up Is not opening after clicking on save button for standard Folder.");
												}
												if(click(driver, fp.getFolderCreationErrorMessageOkBtn(Workspace.InvestorWorkspace, PageName.FundsPage, FolderType.Standard, 10), "Duplicate folder Error message ok button", action.BOOLEAN)){
													System.err.println("successfulyl clicked on ok button.");
												} else {
													System.err.println("\n\n\nNot able to click on OK Button.\n\n\n");
												}
											}else {
												appLog.error("Not able to click on sub folder "+InternalFolderName[0]+" save button so cannot chcek Error Message "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
												sa.assertTrue(false, "Not able to click on sub folder "+InternalFolderName[0]+" save button so cannot chcek Error Message "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
											}
										}else {
											appLog.error("Not able to pass value in sub folder "+InternalFolderName[0]+" so cannot check Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
											sa.assertTrue(false, "Not able to pass value in sub folder "+InternalFolderName[0]+" so cannot check Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
										}
									}else {
										appLog.error("Not able to click on Add folder sub folder "+InternalFolderName[0]+" so cannot check error message on "+InternalFolderName[0]+" sub folder");
										sa.assertTrue(false, "Not able to click on Add folder sub folder "+InternalFolderName[0]+" so cannot check error message on "+InternalFolderName[0]+" sub folder");
									}
								}else {
									appLog.error(InternalFolderName[0]+" folder is available in manage folder so cannot check error messgaes on Standard folder");
									sa.assertTrue(false, InternalFolderName[0]+" folder is available in manage folder so cannot check error messgaes on Standard folder");
								}
							
							} else {
								appLog.error("Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
								sa.assertTrue(false,"Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
								fp.recover(Workspace.InvestorWorkspace, 30);
							}
						} else {
							appLog.error("Next button of step 1 of 3 cannot be click, So cannot check cancel button functionality..");
							sa.assertTrue(false,"Next button of step 1 of 3 cannot be click, So cannot check cancel button functionality.");
							fp.recover(Workspace.InvestorWorkspace, 30);
						}
					} else {
						appLog.error("One of the mandatory field is not filled, So cannot check cancel button functionality.");
						sa.assertTrue(false,"One of the mandatory field is not filled, So cannot check cancel button functionality.");
						fp.recover(Workspace.InvestorWorkspace, 30);
					}
				} else {
					appLog.error("Build Investor workspace button cannot be clicked, So cannot check cancel button functionality.");
					sa.assertTrue(false,"Build Investor workspace button cannot be clicked, So cannot check cancel button functionality.");
					fp.recover(Workspace.InvestorWorkspace, 30);
				}
			} else {
				appLog.error(M8F4+" fund is not available, so cannot continue with the tc.");
				sa.assertTrue(false,M8F4+" fund is not available, so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot continue with the tc.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
		
	}

	@Test
	public void M8tc008_CreateFolderTemplate(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		boolean flag = true;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.NIMTab)){
			switchToFrame(driver, 30, nim.getFrame(PageName.NavatarInvestorManager, 30));
			if(nim.createFolderTemplate("FolderTemp", folderTemplateName, "Folder Description", 60)){	
				appLog.info("Folder template created successfully.");
			} else {
				appLog.error("Folder structure is not created.");
				sa.assertTrue(false,"Folder structure is not created.");
			}
			switchToDefaultContent(driver);
		} else {
			appLog.error("NIM tab cannot be clicked, So cannot create folder template.");
			sa.assertTrue(false,"NIM tab cannot be clicked, So cannot create folder template.");
		}
		
		if(lp.clickOnTab(TabName.NIMTab)){
			switchToFrame(driver, 30, nim.getFrame(PageName.NavatarInvestorManager, 30));
			if(nim.createFolderTemplate("FolderTemp", folderTemplateName+"1", "Folder Description", 60)){	
				appLog.info(folderTemplateName+"1"+" :Folder template created successfully.");
			} else {
				appLog.error(folderTemplateName+"1"+" :Folder structure is not created.");
				sa.assertTrue(false,folderTemplateName+"1"+" :Folder structure is not created.");
			}
			switchToDefaultContent(driver);
		} else {
			appLog.error("NIM tab cannot be clicked, So cannot create folder template.");
			sa.assertTrue(false,"NIM tab cannot be clicked, So cannot create folder template.");
		}
		
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F1)){
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Funcraising wokspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 30), "Buld fundraising workspace button", action.BOOLEAN)){
					if(sendKeys(driver, fp.getFundVintageYearField(Workspace.FundraisingWorkspace, 30), "2013", "Vintage year", action.BOOLEAN)){
						appLog.info("Successfully passed value to vintage year text box.");
					} else {
						appLog.error("Cannot pass value to vintage year text box.");
						sa.assertTrue(false,"Cannot pass value to vintage year text box.");
					}
					
					if(sendKeys(driver, fp.getFundSizeField(Workspace.FundraisingWorkspace, 30), "5000", "Fund Size", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund size text box.");
					} else {
						appLog.error("Cannot pass value to Fund size text box.");
						sa.assertTrue(false,"Cannot pass value to Fund size text box.");
					}
					
					if(sendKeys(driver, fp.getFundContactField(Workspace.FundraisingWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund contact text box.");
					} else {
						appLog.error("Cannot pass value to Fund contact text box.");
						sa.assertTrue(false,"Cannot pass value to Fund contact text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundEmailField(Workspace.FundraisingWorkspace, 30), "abc@abc.com", "Fund Email", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Email text box.");
					} else {
						appLog.error("Cannot pass value to Fund Email text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Email text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundPhoneField(Workspace.FundraisingWorkspace, 30), "987654321", "Fund Phone", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Phone text box.");
					} else {
						appLog.error("Cannot pass value to Fund Phone text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Phone text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundDescriptionField(Workspace.FundraisingWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Description text box.");
					} else {
						appLog.error("Cannot pass value to Fund Description text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Description text box.");
					}
					
					if(flag){
						if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 30), "Next button of step 1 of 3", action.BOOLEAN)){
							if(fp.getStep2Of3Header(Workspace.FundraisingWorkspace, 30)!=null){
								if(click(driver, fp.getImportFolderTemplateButton(Workspace.FundraisingWorkspace, 30), "Import Folder Template  BUtton", action.BOOLEAN)){
									if(selectVisibleTextFromDropDown(driver, fp.getSelectFolderTemplateDropDown(Workspace.FundraisingWorkspace, 30), "Folder Template drop down.", "Other Users' Templates")){
										String text = getText(driver, fp.getSelectFolderTemplateErrorMessage(30), "", action.BOOLEAN);
										if(text.trim().equalsIgnoreCase(FundsPageErrorMessage.SelectFolderTemplateNoDataMessage)){
											appLog.info("Error message is verified.");
										} else {
											appLog.error("Error message on select folder template pop is not verified. Expected: "+FundsPageErrorMessage.SelectFolderTemplateNoDataMessage+"\tActual: "+text);
											sa.assertTrue(false,"Error message on select folder template pop is not verified. Expected: "+FundsPageErrorMessage.SelectFolderTemplateNoDataMessage+"\tActual: "+text);
										}
										click(driver, fp.getSelectFolderTemplateCancelButton(Workspace.FundraisingWorkspace, 30), "Error MEssage", action.BOOLEAN);
										switchToDefaultContent(driver);
									} else {
										appLog.error("Folder Template Drop Down doesn't contains Other User's Template in the drop down, So cannot check error message on that.");
										sa.assertTrue(false,"Folder Template Drop Down doesn't contains Other User's Template in the drop down, So cannot check error message on that.");
									}
								} else {
									appLog.error("Import folder template button cannot be clicked, So cannot check error message for other users template.");
									sa.assertTrue(false,"Import folder template button cannot be clicked, So cannot check error message for other users template.");
								}
							
							} else {
								appLog.error("Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
								sa.assertTrue(false,"Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
								fp.recover(Workspace.FundraisingWorkspace, 30);
							}
							
						} else {
							appLog.error("Next button of step 1 of 3 cannot be click, So cannot check cancel button functionality..");
							sa.assertTrue(false,"Next button of step 1 of 3 cannot be click, So cannot check cancel button functionality.");
							fp.recover(Workspace.FundraisingWorkspace, 30);
						}
					} else {
						appLog.error("One of the mandatory field is not filled, So cannot check cancel button functionality.");
						sa.assertTrue(false,"One of the mandatory field is not filled, So cannot check cancel button functionality.");
						fp.recover(Workspace.FundraisingWorkspace, 30);
					}
				} else {
					appLog.error("Build fundraising workspace button cannot be clicked, So cannot check cancel button functionality.");
					sa.assertTrue(false,"Build fundraising workspace button cannot be clicked, So cannot check cancel button functionality.");
					fp.recover(Workspace.FundraisingWorkspace, 30);
				}
			} else {
				appLog.error(M8F1+" fund is not available, so cannot continue with the tc.");
				sa.assertTrue(false,M8F1+" fund is not available, so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot continue with the tc.");
		}
		
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName, adminPassword);
		if(lp.clickOnTab(TabName.NIMTab)){
			switchToFrame(driver, 30, nim.getFrame(PageName.NavatarInvestorManager, 30));
			if(nim.createFolderTemplate("FolderTemp", folderTemplateName+"2", "Folder Description", 60)){	
				appLog.info(folderTemplateName+"2"+" :Folder template created successfully.");
			} else {
				appLog.error(folderTemplateName+"2"+" :Folder structure is not created.");
				sa.assertTrue(false,folderTemplateName+"2"+" :Folder structure is not created.");
			}
			switchToDefaultContent(driver);
		} else {
			appLog.error("NIM tab cannot be clicked, So cannot create folder template.");
			sa.assertTrue(false,"NIM tab cannot be clicked, So cannot create folder template.");
		}
		
		if(lp.clickOnTab(TabName.NIMTab)){
			switchToFrame(driver, 30, nim.getFrame(PageName.NavatarInvestorManager, 30));
			if(nim.createFolderTemplate("FolderTemp", folderTemplateName+"3", "Folder Description", 60)){	
				appLog.info(folderTemplateName+"3"+" :Folder template created successfully.");
			} else {
				appLog.error(folderTemplateName+"3"+" :Folder structure is not created.");
				sa.assertTrue(false,folderTemplateName+"3"+" :Folder structure is not created.");
			}
			switchToDefaultContent(driver);
		} else {
			appLog.error("NIM tab cannot be clicked, So cannot create folder template.");
			sa.assertTrue(false,"NIM tab cannot be clicked, So cannot create folder template.");
		}
		
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M8tc009_VerifyImportFolderTemplate(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		boolean flag = true;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F1)){
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Funcraising wokspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 30), "Buld fundraising workspace button", action.BOOLEAN)){
					if(sendKeys(driver, fp.getFundVintageYearField(Workspace.FundraisingWorkspace, 30), "2013", "Vintage year", action.BOOLEAN)){
						appLog.info("Successfully passed value to vintage year text box.");
					} else {
						appLog.error("Cannot pass value to vintage year text box.");
						sa.assertTrue(false,"Cannot pass value to vintage year text box.");
					}
					
					if(sendKeys(driver, fp.getFundSizeField(Workspace.FundraisingWorkspace, 30), "5000", "Fund Size", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund size text box.");
					} else {
						appLog.error("Cannot pass value to Fund size text box.");
						sa.assertTrue(false,"Cannot pass value to Fund size text box.");
					}
					
					if(sendKeys(driver, fp.getFundContactField(Workspace.FundraisingWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund contact text box.");
					} else {
						appLog.error("Cannot pass value to Fund contact text box.");
						sa.assertTrue(false,"Cannot pass value to Fund contact text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundEmailField(Workspace.FundraisingWorkspace, 30), "abc@abc.com", "Fund Email", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Email text box.");
					} else {
						appLog.error("Cannot pass value to Fund Email text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Email text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundPhoneField(Workspace.FundraisingWorkspace, 30), "987654321", "Fund Phone", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Phone text box.");
					} else {
						appLog.error("Cannot pass value to Fund Phone text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Phone text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundDescriptionField(Workspace.FundraisingWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Description text box.");
					} else {
						appLog.error("Cannot pass value to Fund Description text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Description text box.");
					}
//					Scanner scan = new Scanner(System.in);
					if(flag){
						if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 30), "Next button of step 1 of 3", action.BOOLEAN)){
							if(fp.getStep2Of3Header(Workspace.FundraisingWorkspace, 30)!=null){
								if(fp.getImportFolderTemplateButton(Workspace.FundraisingWorkspace, EnableDisable.Enable, 30)!=null){
									appLog.info("Import Folder Template Button is enabled and verfied.");
								} else {
									appLog.error("Import Folder template button is not enabled and is not verified.");
									sa.assertTrue(false,"Import Folder template button is not enabled and is not verified.");
								}
								if(click(driver, fp.getImportFolderTemplateButton(Workspace.FundraisingWorkspace, 30), "Import Folder Template  BUtton", action.BOOLEAN)){
									String text = fp.getSelectFolderTemplateHeader(Workspace.FundraisingWorkspace, 30).getText().trim();
									if(text.equalsIgnoreCase("Select Folder Template")){
										appLog.info("Select folder template pop is successfully opened.");
									} else {
										appLog.error("Select Folder Template Pop uP is not opening after clicking on imprt folder template button. Expected: Select Folder Template\tActual: "+text);
										sa.assertTrue(false,"Select Folder Template Pop uP is not opening after clicking on imprt folder template button. Expected: Select Folder Template\tActual: "+text);
									}
									text  = fp.getSelectFolderTemplateDisplayText(Workspace.FundraisingWorkspace, 30).getText().trim();
									if(text.equalsIgnoreCase("Display:")){
										appLog.info("Display label is verified.");
									} else {
										appLog.error("Display label is not present.");
										sa.assertTrue(false,"Display label is not present.");
									}
									
									text = getSelectedOptionOfDropDown(driver, fp.getSelectFolderTemplateDropDown(Workspace.FundraisingWorkspace, 30), "Drop Down", "text");
									if(text.equalsIgnoreCase("My Templates")){
										appLog.info("By default selected option is verified.");
									} else {
										appLog.error("By default selected option in drop down is not verified. Expected: My Templates\tActual: "+text);
										sa.assertTrue(false,"By default selected option in drop down is not verified. Expected: My Templates\tActual: "+text);
									}
									List<WebElement> allOptions = allOptionsInDropDrop(driver, fp.getSelectFolderTemplateDropDown(Workspace.FundraisingWorkspace, 30), "Select Folder template Drop Down.");
									if(allOptions.isEmpty()){
										appLog.error("There is not option available in the drop down.");
										sa.assertTrue(false,"There is not option available in the drop down.");
									} else {
										for(int i = 0; i < allOptions.size(); i++){
											text = allOptions.get(i).getText().trim();
											if(text.equalsIgnoreCase("My Templates") || text.equalsIgnoreCase("All Templates") || text.equalsIgnoreCase("Other Users' Templates")){
												appLog.info(text+" is present in the drop down.");
											} else {
												appLog.error(text+" is not present in the drop down.");
												sa.assertTrue(false,text+" is not present in the drop down.");
											}
										}
									}
									
									allOptions = fp.getSelectFolderTemplateGridHeader(30);
									if(allOptions.isEmpty()){
										appLog.error("No Header is displaying in content section.");
										sa.assertTrue(false,"No Header is displaying in content section.");
									} else {
										for(int i = 0; i < allOptions.size(); i++){
											text = allOptions.get(i).getText().trim();
											if(text.equalsIgnoreCase("Select") || text.equalsIgnoreCase("Template Name") || text.equalsIgnoreCase("Description")){
												appLog.info(text+" header is verified.");
											} else {
												appLog.error(text+" is not verified.");
												sa.assertTrue(false,text+" is not verified.");
											}
										}
									}
									WebElement ele = null;
									allOptions = fp.getSelectFolderTemplateTemplateName(30);
									if(allOptions.isEmpty()){
										appLog.error("There is no template in the grid.");
										sa.assertTrue(false,"There is no template in the grid.");
									} else {
										for(int i = 0; i < allOptions.size(); i++){
											text = allOptions.get(i).getText().trim();
											if(text.equalsIgnoreCase(folderTemplateName) || text.equalsIgnoreCase(folderTemplateName+"1")){
												appLog.info(text+" Template is verified.");
												ele = FindElement(driver, "//span[@title='"+ text +"']/../preceding-sibling::span[1]//input", text+" template radio button", action.BOOLEAN, 30);
												if(!isSelected(driver, ele, text+" Template Radio Button")){
													appLog.info(text+" template radio button is not selected and is verified.");
												} else {
													appLog.error(text+" template radio button is selected.");
													sa.assertTrue(false,text+" template radio button is selected.");
												}
												System.err.println("Please Enter value to continue.");
//												scan.next();
												ele = FindElement(driver, "//span[@title='"+ text +"']/../following-sibling::span[1]//span", text+" folder template description", action.BOOLEAN, 30);
												String text1 = null;
												text1 = getText(driver, ele, text+" folder template description", action.BOOLEAN);
												if(text1.equalsIgnoreCase("Folder Description")){
													appLog.info(text1+" folder template description is verified.");
												} else {
													appLog.error(text1+" folder template description is not verified. Expected: Folder Description\tActual: "+text1);
													sa.assertTrue(false,text1+" folder template description is not verified. Expected: Folder Description\tActual: "+text1);
												}
											} else {
												appLog.error(text+" Template is not verified.");
												sa.assertTrue(false,text+" Template is not verified.");
											}
										}
									}
									ele = isDisplayed(driver, FindElement(driver, "//span[@id='BuildDealDetail_grid-headers-start']/following-sibling::span[contains(@id,'BuildDealDetail_grid-header-')][2]/span/span[3]/span", "Template Name Sort Icon", action.BOOLEAN, 30), "Visibility", 30, "Template name sort icon");
									if(ele!=null){
										appLog.info("By default sorting is on template name.");
									} else {
										appLog.error("By default sorting is not on template name.");
										sa.assertTrue(false,"By default sorting is not on template name.");
									}
									if(Integer.parseInt(fp.getSelectFolderTemplateRecordCount(Workspace.FundraisingWorkspace, 30).getText())==fp.getSelectFolderTemplateTemplateName(30).size()){
										appLog.info("Record Count is verified.");
									} else {
										appLog.error("Record count is not matched. Expected: "+fp.getSelectFolderTemplateTemplateName(30).size()+"\tActual: "+fp.getSelectFolderTemplateRecordCount(Workspace.FundraisingWorkspace, 30));
										sa.assertTrue(false,"Record count is not matched. Expected: "+fp.getSelectFolderTemplateTemplateName(30).size()+"\tActual: "+fp.getSelectFolderTemplateRecordCount(Workspace.FundraisingWorkspace, 30));
									}
									if(fp.getSelectFolderTemplateImportButton(Workspace.FundraisingWorkspace, EnableDisable.Disable, 30)!=null){
										appLog.info("Import button is disalbled and is verified.");
									} else {
										appLog.error("Import button is Enabled and is not verified.");
										sa.assertTrue(false,"Import button is Enabled and is not verified.");
									}
									if(fp.getSelectFolderTemplateCancelButton(Workspace.FundraisingWorkspace, 30)!=null){
										appLog.info("Cancel button is verified.");
									} else {
										appLog.error("Cancel button is not present on select folder template pop up.");
										sa.assertTrue(false,"Cancel button is not present on select folder template pop up.");
									}
									if(fp.getSelectFolderTemplateCrossIcon(Workspace.FundraisingWorkspace, 30)!=null){
										appLog.info("Select foder template cross icon is present and is verified.");
									} else {
										appLog.error("Select folder template cross icon is not present.");
										sa.assertTrue(false,"Select folder template cross icon is not present.");
									}
									boolean flag1 = true;
									if(click(driver, fp.getSelectFolderTemplateCancelButton(Workspace.FundraisingWorkspace, 30), "Cancel Button", action.BOOLEAN)){
										ThreadSleep(1500);
										if(fp.getSelectFolderTemplateHeader(Workspace.FundraisingWorkspace, 3)!=null){
											appLog.error("Select folder template pop up is not closed after clicking cancel button.");
											sa.assertTrue(false,"Select folder template pop up is not closed after clicking cancel button.");
										} else {
											appLog.info("Select folder template pop up is successfully closed after clicking cancel button.");
										}
										
										if(fp.getImportFolderTemplateButton(Workspace.FundraisingWorkspace, EnableDisable.Enable, 30)!=null){
											appLog.info("Import Folder template button is enabled.");
										} else {
											appLog.error("Import folder template button is not enabled.");
											sa.assertTrue(false,"Import folder template button is not enabled.");
										}
									} else {
										appLog.error("Select Folder Template Cancel button cannot be clikced, So cannot check cancel button functionality.");
										sa.assertTrue(false,"Select Folder Template Cancel button cannot be clikced, So cannot check cancel button functionality.");
										flag1=false;
									}
									if(flag1){
										click(driver, fp.getImportFolderTemplateButton(Workspace.FundraisingWorkspace, 30), "Import folder template button", action.BOOLEAN);
									}
									if(click(driver, FindElement(driver, "//span[@title='"+ folderTemplateName +"']/../preceding-sibling::span[1]//input", folderTemplateName+" template radio button", action.BOOLEAN, 30), folderTemplateName+" radio button", action.BOOLEAN)){
										if(isSelected(driver, FindElement(driver, "//span[@title='"+ folderTemplateName +"']/../preceding-sibling::span[1]//input", folderTemplateName+" template radio button", action.BOOLEAN, 30), folderTemplateName+" radio button")){
											appLog.info(folderTemplateName+" radio button is selected successfully.");
										} else {
											appLog.error(folderTemplateName+" radio button is not selected after clicking on the radio button.");
											sa.assertTrue(false,folderTemplateName+" radio button is not selected after clicking on the radio button.");
										}
										if(fp.getSelectFolderTemplateImportButton(Workspace.FundraisingWorkspace, EnableDisable.Enable, 30)!=null){
											appLog.info("Import button is enabled after clicking on the radio button.");
										} else {
											appLog.error("Import button is disabled after clicking on the radio button.");
											sa.assertTrue(false,"Import button is disabled after clicking on the radio button.");
										}
									} else {
										appLog.error(folderTemplateName+" radio button cannot be clicked, So cannot check its functionlity.");
										sa.assertTrue(false,folderTemplateName+" radio button cannot be clicked, So cannot check its functionlity.");
									}
									flag1=true;
									if(click(driver, fp.getSelectFolderTemplateCrossIcon(Workspace.FundraisingWorkspace, 30), "Cross icon", action.BOOLEAN)){
										ThreadSleep(1500);
										if(fp.getSelectFolderTemplateHeader(Workspace.FundraisingWorkspace, 3)!=null){
											appLog.error("Select folder template pop up is not closed after clicking Cross icon.");
											sa.assertTrue(false,"Select folder template pop up is not closed after clicking Cross icon.");
										} else {
											appLog.info("Select folder template pop up is successfully closed after clicking Cross icon.");
										}
										
										if(fp.getImportFolderTemplateButton(Workspace.FundraisingWorkspace, EnableDisable.Enable, 30)!=null){
											appLog.info("Import Folder template button is enabled.");
										} else {
											appLog.error("Import folder template button is not enabled.");
											sa.assertTrue(false,"Import folder template button is not enabled.");
										}
									} else {
										appLog.error("Select Folder Template Cross icon cannot be clikced, So cannot check Cross icon functionality.");
										sa.assertTrue(false,"Select Folder Template Cross icon cannot be clikced, So cannot check Cross icon functionality.");
										flag1=false;
									}
									if(flag1){
										click(driver, fp.getImportFolderTemplateButton(Workspace.FundraisingWorkspace, 30), "Import folder template button", action.BOOLEAN);
									}
									//check sorting
									allOptions = fp.getSelectFolderTemplateTemplateName(30);
									if(checkSorting(driver, SortOrder.Assecending, allOptions)){
										appLog.info("By deafult sorting is in assending order on select folder template po up.");
									} else {
										appLog.error("By default sorting is not in assecending order on select folder template pop up.");
										sa.assertTrue(false,"By default sorting is not in assecending order on select folder template pop up.");
									}
									if(click(driver, fp.getSelectFolderTemplateGridHeader(30).get(1), "Template Name header", action.BOOLEAN)){
										if(checkSorting(driver, SortOrder.Decending, allOptions)){
											appLog.info("sorting is in decending order on select folder template po up.");
										} else {
											appLog.error("Sorting is not in decending order on select folder template pop up.");
											sa.assertTrue(false,"Sorting is not in decending order on select folder template pop up.");
										}
									} else {
										appLog.error("cannot clikc on tempate name header, So cannot check decending sorting on template name column.");
										sa.assertTrue(false,"cannot clikc on tempate name header, So cannot check decending sorting on template name column.");
									}
									//All Templates
									if(selectVisibleTextFromDropDown(driver, fp.getSelectFolderTemplateDropDown(Workspace.FundraisingWorkspace, 30), "Drop Doen", "All Templates")){
										
										allOptions = fp.getSelectFolderTemplateTemplateName(30);
										if(allOptions.isEmpty()){
											appLog.error("There is no template in the grid.");
											sa.assertTrue(false,"There is no template in the grid.");
										} else {
											for(int i = 0; i < allOptions.size(); i++){
												text = allOptions.get(i).getText().trim();
												System.err.println("\n\n\n"+i+": "+text+"\n\n\n");
												if(text.equalsIgnoreCase(folderTemplateName) || text.equalsIgnoreCase(folderTemplateName+"1") || text.equalsIgnoreCase(folderTemplateName+"2") || text.equalsIgnoreCase(folderTemplateName+"3")){
													appLog.info(text+" Template is verified.");
													ele = FindElement(driver, "//span[@title='"+ text +"']/../preceding-sibling::span[1]//input", text+" template radio button", action.BOOLEAN, 30);
													if(!isSelected(driver, ele, text+" Template Radio Button")){
														appLog.info(text+" template radio button is not selected and is verified.");
													} else {
														appLog.error(text+" template radio button is selected.");
														sa.assertTrue(false,text+" template radio button is selected.");
													}
													System.err.println("Please Enter value to continue.");
//													scan.nextLine();
													ele = FindElement(driver, "//span[@title='"+ text +"']/../following-sibling::span[1]//span", text+" folder template description", action.BOOLEAN, 30);
													String text1 = null;
													text1 = getText(driver, ele, text+" folder template description", action.BOOLEAN);
													if(text1.equalsIgnoreCase("Folder Description")){
														appLog.info(text1+" folder template description is verified.");
													} else {
														appLog.error(text1+" folder template description is not verified. Expected: Folder Description\tActual: "+text1);
														sa.assertTrue(false,text1+" folder template description is not verified. Expected: Folder Description\tActual: "+text1);
													}
												} else {
													appLog.error(text+" Template is not verified.");
													sa.assertTrue(false,text+" Template is not verified.");
												}
											}
										}
										ele = isDisplayed(driver, FindElement(driver, "//span[@id='BuildDealDetail_grid-headers-start']/following-sibling::span[contains(@id,'BuildDealDetail_grid-header-')][2]/span/span[3]/span", "Template Name Sort Icon", action.BOOLEAN, 30), "Visibility", 30, "Template name sort icon");
										if(ele!=null){
											appLog.info("By default sorting is on template name.");
										} else {
											appLog.error("By default sorting is not on template name.");
											sa.assertTrue(false,"By default sorting is not on template name.");
										}
										if(Integer.parseInt(fp.getSelectFolderTemplateRecordCount(Workspace.FundraisingWorkspace, 30).getText())==fp.getSelectFolderTemplateTemplateName(30).size()){
											appLog.info("Record Count is verified.");
										} else {
											appLog.error("Record count is not matched. Expected: "+fp.getSelectFolderTemplateTemplateName(30).size()+"\tActual: "+fp.getSelectFolderTemplateRecordCount(Workspace.FundraisingWorkspace, 30));
											sa.assertTrue(false,"Record count is not matched. Expected: "+fp.getSelectFolderTemplateTemplateName(30).size()+"\tActual: "+fp.getSelectFolderTemplateRecordCount(Workspace.FundraisingWorkspace, 30));
										}
										allOptions = fp.getSelectFolderTemplateTemplateName(30);
										if(allOptions.isEmpty()){
											appLog.error("There is no template in the grid.");
											sa.assertTrue(false,"There is no template in the grid.");
										} else {
											if(checkSorting(driver, SortOrder.Decending, allOptions)){
												appLog.info("Sorting is in decending order on select folder template po up.");
											} else {
												appLog.error("Sorting is not in decending order on select folder template pop up.");
												sa.assertTrue(false,"Sorting is not in decending order on select folder template pop up.");
											}
											if(click(driver, fp.getSelectFolderTemplateGridHeader(30).get(1), "Template Name header", action.BOOLEAN)){
												if(checkSorting(driver, SortOrder.Assecending, allOptions)){
													appLog.info("By deafult sorting is in assending order on select folder template po up.");
												} else {
													appLog.error("By default sorting is not in assecending order on select folder template pop up.");
													sa.assertTrue(false,"By default sorting is not in assecending order on select folder template pop up.");
												}
											} else {
												appLog.error("cannot clikc on tempate name header, So cannot check decending sorting on template name column.");
												sa.assertTrue(false,"cannot clikc on tempate name header, So cannot check decending sorting on template name column.");
											}
										}
									} else {
										appLog.error("All Templates option is not available in drop down.");
										sa.assertTrue(false,"All Templates option is not available in drop down.");
									}
									
									
									
									
									if(selectVisibleTextFromDropDown(driver, fp.getSelectFolderTemplateDropDown(Workspace.FundraisingWorkspace, 30), "Drop Doen", "Other Users' Templates")){
										allOptions = fp.getSelectFolderTemplateTemplateName(30);
										if(allOptions.isEmpty()){
											appLog.error("There is no template in the grid.");
											sa.assertTrue(false,"There is no template in the grid.");
										} else {
											for(int i = 0; i < allOptions.size(); i++){
												text = allOptions.get(i).getText().trim();
												System.err.println("\n\n\n"+i+": "+text+"\n\n\n");
												if(text.equalsIgnoreCase(folderTemplateName+"2") || text.equalsIgnoreCase(folderTemplateName+"3")){
													appLog.info(text+" Template is verified.");
													ele = FindElement(driver, "//span[@title='"+ text +"']/../preceding-sibling::span[1]//input", text+" template radio button", action.BOOLEAN, 30);
													if(!isSelected(driver, ele, text+" Template Radio Button")){
														appLog.info(text+" template radio button is not selected and is verified.");
													} else {
														appLog.error(text+" template radio button is selected.");
														sa.assertTrue(false,text+" template radio button is selected.");
													}
													System.err.println("Please Enter value to continue.");
//													scan.nextLine();
													ele = FindElement(driver, "//span[@title='"+ text +"']/../following-sibling::span[1]//span", text+" folder template description", action.BOOLEAN, 30);
													String text1 = null;
													text1 = getText(driver, ele, text+" folder template description", action.BOOLEAN);
													if(text1.equalsIgnoreCase("Folder Description")){
														appLog.info(text1+" folder template description is verified.");
													} else {
														appLog.error(text1+" folder template description is not verified. Expected: Folder Description\tActual: "+text1);
														sa.assertTrue(false,text1+" folder template description is not verified. Expected: Folder Description\tActual: "+text1);
													}
												} else {
													appLog.error(text+" Template is not verified.");
													sa.assertTrue(false,text+" Template is not verified.");
												}
											}
										}
										ele = isDisplayed(driver, FindElement(driver, "//span[@id='BuildDealDetail_grid-headers-start']/following-sibling::span[contains(@id,'BuildDealDetail_grid-header-')][2]/span/span[3]/span", "Template Name Sort Icon", action.BOOLEAN, 30), "Visibility", 30, "Template name sort icon");
										if(ele!=null){
											appLog.info("By default sorting is on template name.");
										} else {
											appLog.error("By default sorting is not on template name.");
											sa.assertTrue(false,"By default sorting is not on template name.");
										}
										if(Integer.parseInt(fp.getSelectFolderTemplateRecordCount(Workspace.FundraisingWorkspace, 30).getText())==fp.getSelectFolderTemplateTemplateName(30).size()){
											appLog.info("Record Count is verified.");
										} else {
											appLog.error("Record count is not matched. Expected: "+fp.getSelectFolderTemplateTemplateName(30).size()+"\tActual: "+fp.getSelectFolderTemplateRecordCount(Workspace.FundraisingWorkspace, 30));
											sa.assertTrue(false,"Record count is not matched. Expected: "+fp.getSelectFolderTemplateTemplateName(30).size()+"\tActual: "+fp.getSelectFolderTemplateRecordCount(Workspace.FundraisingWorkspace, 30));
										}
										allOptions = fp.getSelectFolderTemplateTemplateName(30);
										if(checkSorting(driver, SortOrder.Assecending, allOptions)){
											appLog.info("By deafult sorting is in assending order on select folder template po up.");
										} else {
											appLog.error("By default sorting is not in assecending order on select folder template pop up.");
											sa.assertTrue(false,"By default sorting is not in assecending order on select folder template pop up.");
										}
										if(click(driver, fp.getSelectFolderTemplateGridHeader(30).get(1), "Template Name header", action.BOOLEAN)){
											if(checkSorting(driver, SortOrder.Decending, allOptions)){
												appLog.info("Sorting is in decending order on select folder template po up.");
											} else {
												appLog.error("Sorting is not in decending order on select folder template pop up.");
												sa.assertTrue(false,"Sorting is not in decending order on select folder template pop up.");
											}
										} else {
											appLog.error("cannot clikc on tempate name header, So cannot check decending sorting on template name column.");
											sa.assertTrue(false,"cannot clikc on tempate name header, So cannot check decending sorting on template name column.");
										}
									} else {
										appLog.error("Other Users' Templates option is not available in drop down.");
										sa.assertTrue(false,"Other Users' Templates option is not available in drop down.");
									}
								} else {
									appLog.error("Import folder template button cannot be clicked, So cannot check error message for other users template.");
									sa.assertTrue(false,"Import folder template button cannot be clicked, So cannot check error message for other users template.");
								}
							
							} else {
								appLog.error("Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
								sa.assertTrue(false,"Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
								fp.recover(Workspace.FundraisingWorkspace, 30);
							}
							
						} else {
							appLog.error("Next button of step 1 of 3 cannot be click, So cannot check cancel button functionality..");
							sa.assertTrue(false,"Next button of step 1 of 3 cannot be click, So cannot check cancel button functionality.");
							fp.recover(Workspace.FundraisingWorkspace, 30);
						}
					} else {
						appLog.error("One of the mandatory field is not filled, So cannot check cancel button functionality.");
						sa.assertTrue(false,"One of the mandatory field is not filled, So cannot check cancel button functionality.");
						fp.recover(Workspace.FundraisingWorkspace, 30);
					}
				} else {
					appLog.error("Build fundraising workspace button cannot be clicked, So cannot check cancel button functionality.");
					sa.assertTrue(false,"Build fundraising workspace button cannot be clicked, So cannot check cancel button functionality.");
					fp.recover(Workspace.FundraisingWorkspace, 30);
				}
			} else {
				appLog.error(M8F1+" fund is not available, so cannot continue with the tc.");
				sa.assertTrue(false,M8F1+" fund is not available, so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot continue with the tc.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M8tc021_VerifyImportFolderTemplate(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		boolean flag = true;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F4)){
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Funcraising wokspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.InvestorWorkspace, 30), "Buld Investor workspace button", action.BOOLEAN)){
					if(sendKeys(driver, fp.getFundVintageYearField(Workspace.InvestorWorkspace, 30), "2013", "Vintage year", action.BOOLEAN)){
						appLog.info("Successfully passed value to vintage year text box.");
					} else {
						appLog.error("Cannot pass value to vintage year text box.");
						sa.assertTrue(false,"Cannot pass value to vintage year text box.");
					}
					
					if(sendKeys(driver, fp.getFundSizeField(Workspace.InvestorWorkspace, 30), "5000", "Fund Size", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund size text box.");
					} else {
						appLog.error("Cannot pass value to Fund size text box.");
						sa.assertTrue(false,"Cannot pass value to Fund size text box.");
					}
					
					if(sendKeys(driver, fp.getFundContactField(Workspace.InvestorWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund contact text box.");
					} else {
						appLog.error("Cannot pass value to Fund contact text box.");
						sa.assertTrue(false,"Cannot pass value to Fund contact text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundEmailField(Workspace.InvestorWorkspace, 30), "abc@abc.com", "Fund Email", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Email text box.");
					} else {
						appLog.error("Cannot pass value to Fund Email text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Email text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundPhoneField(Workspace.InvestorWorkspace, 30), "987654321", "Fund Phone", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Phone text box.");
					} else {
						appLog.error("Cannot pass value to Fund Phone text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Phone text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundDescriptionField(Workspace.InvestorWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Description text box.");
					} else {
						appLog.error("Cannot pass value to Fund Description text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Description text box.");
					}
//					Scanner scan = new Scanner(System.in);
					if(flag){
						if(click(driver, fp.getNext1Of3Button(Workspace.InvestorWorkspace, 30), "Next button of step 1 of 3", action.BOOLEAN)){
							if(fp.getStep2Of3Header(Workspace.InvestorWorkspace, 30)!=null){
								if(fp.getImportFolderTemplateButton(Workspace.InvestorWorkspace, EnableDisable.Enable, 30)!=null){
									appLog.info("Import Folder Template Button is enabled and verfied.");
								} else {
									appLog.error("Import Folder template button is not enabled and is not verified.");
									sa.assertTrue(false,"Import Folder template button is not enabled and is not verified.");
								}
								if(click(driver, fp.getImportFolderTemplateButton(Workspace.InvestorWorkspace, 30), "Import Folder Template  BUtton", action.BOOLEAN)){
									String text = fp.getSelectFolderTemplateHeader(Workspace.InvestorWorkspace, 30).getText().trim();
									if(text.equalsIgnoreCase("Select Folder Template")){
										appLog.info("Select folder template pop is successfully opened.");
									} else {
										appLog.error("Select Folder Template Pop uP is not opening after clicking on imprt folder template button. Expected: Select Folder Template\tActual: "+text);
										sa.assertTrue(false,"Select Folder Template Pop uP is not opening after clicking on imprt folder template button. Expected: Select Folder Template\tActual: "+text);
									}
									text  = fp.getSelectFolderTemplateDisplayText(Workspace.InvestorWorkspace, 30).getText().trim();
									if(text.equalsIgnoreCase("Display:")){
										appLog.info("Display label is verified.");
									} else {
										appLog.error("Display label is not present.");
										sa.assertTrue(false,"Display label is not present.");
									}
									
									text = getSelectedOptionOfDropDown(driver, fp.getSelectFolderTemplateDropDown(Workspace.InvestorWorkspace, 30), "Drop Down", "text");
									if(text.equalsIgnoreCase("My Templates")){
										appLog.info("By default selected option is verified.");
									} else {
										appLog.error("By default selected option in drop down is not verified. Expected: My Templates\tActual: "+text);
										sa.assertTrue(false,"By default selected option in drop down is not verified. Expected: My Templates\tActual: "+text);
									}
									List<WebElement> allOptions = allOptionsInDropDrop(driver, fp.getSelectFolderTemplateDropDown(Workspace.InvestorWorkspace, 30), "Select Folder template Drop Down.");
									if(allOptions.isEmpty()){
										appLog.error("There is not option available in the drop down.");
										sa.assertTrue(false,"There is not option available in the drop down.");
									} else {
										for(int i = 0; i < allOptions.size(); i++){
											text = allOptions.get(i).getText().trim();
											if(text.equalsIgnoreCase("My Templates") || text.equalsIgnoreCase("All Templates") || text.equalsIgnoreCase("Other Users' Templates")){
												appLog.info(text+" is present in the drop down.");
											} else {
												appLog.error(text+" is not present in the drop down.");
												sa.assertTrue(false,text+" is not present in the drop down.");
											}
										}
									}
									
									allOptions = fp.getSelectFolderTemplateGridHeader(30);
									if(allOptions.isEmpty()){
										appLog.error("No Header is displaying in content section.");
										sa.assertTrue(false,"No Header is displaying in content section.");
									} else {
										for(int i = 0; i < allOptions.size(); i++){
											text = allOptions.get(i).getText().trim();
											if(text.equalsIgnoreCase("Select") || text.equalsIgnoreCase("Template Name") || text.equalsIgnoreCase("Description")){
												appLog.info(text+" header is verified.");
											} else {
												appLog.error(text+" is not verified.");
												sa.assertTrue(false,text+" is not verified.");
											}
										}
									}
									WebElement ele = null;
									allOptions = fp.getSelectFolderTemplateTemplateName(30);
									if(allOptions.isEmpty()){
										appLog.error("There is no template in the grid.");
										sa.assertTrue(false,"There is no template in the grid.");
									} else {
										for(int i = 0; i < allOptions.size(); i++){
											text = allOptions.get(i).getText().trim();
											if(text.equalsIgnoreCase(folderTemplateName) || text.equalsIgnoreCase(folderTemplateName+"1")){
												appLog.info(text+" Template is verified.");
												ele = FindElement(driver, "//span[@title='"+ text +"']/../preceding-sibling::span[1]//input", text+" template radio button", action.BOOLEAN, 30);
												if(!isSelected(driver, ele, text+" Template Radio Button")){
													appLog.info(text+" template radio button is not selected and is verified.");
												} else {
													appLog.error(text+" template radio button is selected.");
													sa.assertTrue(false,text+" template radio button is selected.");
												}
												System.err.println("Please Enter value to continue.");
//												scan.next();
												ele = FindElement(driver, "//span[@title='"+ text +"']/../following-sibling::span[1]//span", text+" folder template description", action.BOOLEAN, 30);
												String text1 = null;
												text1 = getText(driver, ele, text+" folder template description", action.BOOLEAN);
												if(text1.equalsIgnoreCase("Folder Description")){
													appLog.info(text1+" folder template description is verified.");
												} else {
													appLog.error(text1+" folder template description is not verified. Expected: Folder Description\tActual: "+text1);
													sa.assertTrue(false,text1+" folder template description is not verified. Expected: Folder Description\tActual: "+text1);
												}
											} else {
												appLog.error(text+" Template is not verified.");
												sa.assertTrue(false,text+" Template is not verified.");
											}
										}
									}
									ele = isDisplayed(driver, FindElement(driver, "//span[@id='BuildDealDetail_grid-headers-start']/following-sibling::span[contains(@id,'BuildDealDetail_grid-header-')][2]/span/span[3]/span", "Template Name Sort Icon", action.BOOLEAN, 30), "Visibility", 30, "Template name sort icon");
									if(ele!=null){
										appLog.info("By default sorting is on template name.");
									} else {
										appLog.error("By default sorting is not on template name.");
										sa.assertTrue(false,"By default sorting is not on template name.");
									}
									if(Integer.parseInt(fp.getSelectFolderTemplateRecordCount(Workspace.InvestorWorkspace, 30).getText())==fp.getSelectFolderTemplateTemplateName(30).size()){
										appLog.info("Record Count is verified.");
									} else {
										appLog.error("Record count is not matched. Expected: "+fp.getSelectFolderTemplateTemplateName(30).size()+"\tActual: "+fp.getSelectFolderTemplateRecordCount(Workspace.InvestorWorkspace, 30));
										sa.assertTrue(false,"Record count is not matched. Expected: "+fp.getSelectFolderTemplateTemplateName(30).size()+"\tActual: "+fp.getSelectFolderTemplateRecordCount(Workspace.InvestorWorkspace, 30));
									}
									if(fp.getSelectFolderTemplateImportButton(Workspace.InvestorWorkspace, EnableDisable.Disable, 30)!=null){
										appLog.info("Import button is disalbled and is verified.");
									} else {
										appLog.error("Import button is Enabled and is not verified.");
										sa.assertTrue(false,"Import button is Enabled and is not verified.");
									}
									if(fp.getSelectFolderTemplateCancelButton(Workspace.InvestorWorkspace, 30)!=null){
										appLog.info("Cancel button is verified.");
									} else {
										appLog.error("Cancel button is not present on select folder template pop up.");
										sa.assertTrue(false,"Cancel button is not present on select folder template pop up.");
									}
									if(fp.getSelectFolderTemplateCrossIcon(Workspace.InvestorWorkspace, 30)!=null){
										appLog.info("Select foder template cross icon is present and is verified.");
									} else {
										appLog.error("Select folder template cross icon is not present.");
										sa.assertTrue(false,"Select folder template cross icon is not present.");
									}
									boolean flag1 = true;
									if(click(driver, fp.getSelectFolderTemplateCancelButton(Workspace.InvestorWorkspace, 30), "Cancel Button", action.BOOLEAN)){
										ThreadSleep(1500);
										if(fp.getSelectFolderTemplateHeader(Workspace.InvestorWorkspace, 3)!=null){
											appLog.error("Select folder template pop up is not closed after clicking cancel button.");
											sa.assertTrue(false,"Select folder template pop up is not closed after clicking cancel button.");
										} else {
											appLog.info("Select folder template pop up is successfully closed after clicking cancel button.");
										}
										
										if(fp.getImportFolderTemplateButton(Workspace.InvestorWorkspace, EnableDisable.Enable, 30)!=null){
											appLog.info("Import Folder template button is enabled.");
										} else {
											appLog.error("Import folder template button is not enabled.");
											sa.assertTrue(false,"Import folder template button is not enabled.");
										}
									} else {
										appLog.error("Select Folder Template Cancel button cannot be clikced, So cannot check cancel button functionality.");
										sa.assertTrue(false,"Select Folder Template Cancel button cannot be clikced, So cannot check cancel button functionality.");
										flag1=false;
									}
									if(flag1){
										click(driver, fp.getImportFolderTemplateButton(Workspace.InvestorWorkspace, 30), "Import folder template button", action.BOOLEAN);
									}
									if(click(driver, FindElement(driver, "//span[@title='"+ folderTemplateName +"']/../preceding-sibling::span[1]//input", folderTemplateName+" template radio button", action.BOOLEAN, 30), folderTemplateName+" radio button", action.BOOLEAN)){
										if(isSelected(driver, FindElement(driver, "//span[@title='"+ folderTemplateName +"']/../preceding-sibling::span[1]//input", folderTemplateName+" template radio button", action.BOOLEAN, 30), folderTemplateName+" radio button")){
											appLog.info(folderTemplateName+" radio button is selected successfully.");
										} else {
											appLog.error(folderTemplateName+" radio button is not selected after clicking on the radio button.");
											sa.assertTrue(false,folderTemplateName+" radio button is not selected after clicking on the radio button.");
										}
										if(fp.getSelectFolderTemplateImportButton(Workspace.InvestorWorkspace, EnableDisable.Enable, 30)!=null){
											appLog.info("Import button is enabled after clicking on the radio button.");
										} else {
											appLog.error("Import button is disabled after clicking on the radio button.");
											sa.assertTrue(false,"Import button is disabled after clicking on the radio button.");
										}
									} else {
										appLog.error(folderTemplateName+" radio button cannot be clicked, So cannot check its functionlity.");
										sa.assertTrue(false,folderTemplateName+" radio button cannot be clicked, So cannot check its functionlity.");
									}
									flag1=true;
									if(click(driver, fp.getSelectFolderTemplateCrossIcon(Workspace.InvestorWorkspace, 30), "Cross icon", action.BOOLEAN)){
										ThreadSleep(1500);
										if(fp.getSelectFolderTemplateHeader(Workspace.InvestorWorkspace, 3)!=null){
											appLog.error("Select folder template pop up is not closed after clicking Cross icon.");
											sa.assertTrue(false,"Select folder template pop up is not closed after clicking Cross icon.");
										} else {
											appLog.info("Select folder template pop up is successfully closed after clicking Cross icon.");
										}
										
										if(fp.getImportFolderTemplateButton(Workspace.InvestorWorkspace, EnableDisable.Enable, 30)!=null){
											appLog.info("Import Folder template button is enabled.");
										} else {
											appLog.error("Import folder template button is not enabled.");
											sa.assertTrue(false,"Import folder template button is not enabled.");
										}
									} else {
										appLog.error("Select Folder Template Cross icon cannot be clikced, So cannot check Cross icon functionality.");
										sa.assertTrue(false,"Select Folder Template Cross icon cannot be clikced, So cannot check Cross icon functionality.");
										flag1=false;
									}
									if(flag1){
										click(driver, fp.getImportFolderTemplateButton(Workspace.InvestorWorkspace, 30), "Import folder template button", action.BOOLEAN);
									}
									//check sorting
									allOptions = fp.getSelectFolderTemplateTemplateName(30);
									if(checkSorting(driver, SortOrder.Assecending, allOptions)){
										appLog.info("By deafult sorting is in assending order on select folder template po up.");
									} else {
										appLog.error("By default sorting is not in assecending order on select folder template pop up.");
										sa.assertTrue(false,"By default sorting is not in assecending order on select folder template pop up.");
									}
									if(click(driver, fp.getSelectFolderTemplateGridHeader(30).get(1), "Template Name header", action.BOOLEAN)){
										if(checkSorting(driver, SortOrder.Decending, allOptions)){
											appLog.info("sorting is in decending order on select folder template po up.");
										} else {
											appLog.error("Sorting is not in decending order on select folder template pop up.");
											sa.assertTrue(false,"Sorting is not in decending order on select folder template pop up.");
										}
									} else {
										appLog.error("cannot clikc on tempate name header, So cannot check decending sorting on template name column.");
										sa.assertTrue(false,"cannot clikc on tempate name header, So cannot check decending sorting on template name column.");
									}
									//All Templates
									if(selectVisibleTextFromDropDown(driver, fp.getSelectFolderTemplateDropDown(Workspace.InvestorWorkspace, 30), "Drop Doen", "All Templates")){
										
										allOptions = fp.getSelectFolderTemplateTemplateName(30);
										if(allOptions.isEmpty()){
											appLog.error("There is no template in the grid.");
											sa.assertTrue(false,"There is no template in the grid.");
										} else {
											for(int i = 0; i < allOptions.size(); i++){
												text = allOptions.get(i).getText().trim();
												System.err.println("\n\n\n"+i+": "+text+"\n\n\n");
												if(text.equalsIgnoreCase(folderTemplateName) || text.equalsIgnoreCase(folderTemplateName+"1") || text.equalsIgnoreCase(folderTemplateName+"2") || text.equalsIgnoreCase(folderTemplateName+"3")){
													appLog.info(text+" Template is verified.");
													ele = FindElement(driver, "//span[@title='"+ text +"']/../preceding-sibling::span[1]//input", text+" template radio button", action.BOOLEAN, 30);
													if(!isSelected(driver, ele, text+" Template Radio Button")){
														appLog.info(text+" template radio button is not selected and is verified.");
													} else {
														appLog.error(text+" template radio button is selected.");
														sa.assertTrue(false,text+" template radio button is selected.");
													}
													System.err.println("Please Enter value to continue.");
//													scan.nextLine();
													ele = FindElement(driver, "//span[@title='"+ text +"']/../following-sibling::span[1]//span", text+" folder template description", action.BOOLEAN, 30);
													String text1 = null;
													text1 = getText(driver, ele, text+" folder template description", action.BOOLEAN);
													if(text1.equalsIgnoreCase("Folder Description")){
														appLog.info(text1+" folder template description is verified.");
													} else {
														appLog.error(text1+" folder template description is not verified. Expected: Folder Description\tActual: "+text1);
														sa.assertTrue(false,text1+" folder template description is not verified. Expected: Folder Description\tActual: "+text1);
													}
												} else {
													appLog.error(text+" Template is not verified.");
													sa.assertTrue(false,text+" Template is not verified.");
												}
											}
										}
										ele = isDisplayed(driver, FindElement(driver, "//span[@id='BuildDealDetail_grid-headers-start']/following-sibling::span[contains(@id,'BuildDealDetail_grid-header-')][2]/span/span[3]/span", "Template Name Sort Icon", action.BOOLEAN, 30), "Visibility", 30, "Template name sort icon");
										if(ele!=null){
											appLog.info("By default sorting is on template name.");
										} else {
											appLog.error("By default sorting is not on template name.");
											sa.assertTrue(false,"By default sorting is not on template name.");
										}
										if(Integer.parseInt(fp.getSelectFolderTemplateRecordCount(Workspace.InvestorWorkspace, 30).getText())==fp.getSelectFolderTemplateTemplateName(30).size()){
											appLog.info("Record Count is verified.");
										} else {
											appLog.error("Record count is not matched. Expected: "+fp.getSelectFolderTemplateTemplateName(30).size()+"\tActual: "+fp.getSelectFolderTemplateRecordCount(Workspace.InvestorWorkspace, 30));
											sa.assertTrue(false,"Record count is not matched. Expected: "+fp.getSelectFolderTemplateTemplateName(30).size()+"\tActual: "+fp.getSelectFolderTemplateRecordCount(Workspace.InvestorWorkspace, 30));
										}
										allOptions = fp.getSelectFolderTemplateTemplateName(30);
										if(allOptions.isEmpty()){
											appLog.error("There is no template in the grid.");
											sa.assertTrue(false,"There is no template in the grid.");
										} else {
											if(checkSorting(driver, SortOrder.Decending, allOptions)){
												appLog.info("Sorting is in decending order on select folder template po up.");
											} else {
												appLog.error("Sorting is not in decending order on select folder template pop up.");
												sa.assertTrue(false,"Sorting is not in decending order on select folder template pop up.");
											}
											if(click(driver, fp.getSelectFolderTemplateGridHeader(30).get(1), "Template Name header", action.BOOLEAN)){
												if(checkSorting(driver, SortOrder.Assecending, allOptions)){
													appLog.info("By deafult sorting is in assending order on select folder template po up.");
												} else {
													appLog.error("By default sorting is not in assecending order on select folder template pop up.");
													sa.assertTrue(false,"By default sorting is not in assecending order on select folder template pop up.");
												}
											} else {
												appLog.error("cannot clikc on tempate name header, So cannot check decending sorting on template name column.");
												sa.assertTrue(false,"cannot clikc on tempate name header, So cannot check decending sorting on template name column.");
											}
										}
									} else {
										appLog.error("All Templates option is not available in drop down.");
										sa.assertTrue(false,"All Templates option is not available in drop down.");
									}
									
									
									
									
									if(selectVisibleTextFromDropDown(driver, fp.getSelectFolderTemplateDropDown(Workspace.InvestorWorkspace, 30), "Drop Doen", "Other Users' Templates")){
										allOptions = fp.getSelectFolderTemplateTemplateName(30);
										if(allOptions.isEmpty()){
											appLog.error("There is no template in the grid.");
											sa.assertTrue(false,"There is no template in the grid.");
										} else {
											for(int i = 0; i < allOptions.size(); i++){
												text = allOptions.get(i).getText().trim();
												System.err.println("\n\n\n"+i+": "+text+"\n\n\n");
												if(text.equalsIgnoreCase(folderTemplateName+"2") || text.equalsIgnoreCase(folderTemplateName+"3")){
													appLog.info(text+" Template is verified.");
													ele = FindElement(driver, "//span[@title='"+ text +"']/../preceding-sibling::span[1]//input", text+" template radio button", action.BOOLEAN, 30);
													if(!isSelected(driver, ele, text+" Template Radio Button")){
														appLog.info(text+" template radio button is not selected and is verified.");
													} else {
														appLog.error(text+" template radio button is selected.");
														sa.assertTrue(false,text+" template radio button is selected.");
													}
													System.err.println("Please Enter value to continue.");
//													scan.nextLine();
													ele = FindElement(driver, "//span[@title='"+ text +"']/../following-sibling::span[1]//span", text+" folder template description", action.BOOLEAN, 30);
													String text1 = null;
													text1 = getText(driver, ele, text+" folder template description", action.BOOLEAN);
													if(text1.equalsIgnoreCase("Folder Description")){
														appLog.info(text1+" folder template description is verified.");
													} else {
														appLog.error(text1+" folder template description is not verified. Expected: Folder Description\tActual: "+text1);
														sa.assertTrue(false,text1+" folder template description is not verified. Expected: Folder Description\tActual: "+text1);
													}
												} else {
													appLog.error(text+" Template is not verified.");
													sa.assertTrue(false,text+" Template is not verified.");
												}
											}
										}
										ele = isDisplayed(driver, FindElement(driver, "//span[@id='BuildDealDetail_grid-headers-start']/following-sibling::span[contains(@id,'BuildDealDetail_grid-header-')][2]/span/span[3]/span", "Template Name Sort Icon", action.BOOLEAN, 30), "Visibility", 30, "Template name sort icon");
										if(ele!=null){
											appLog.info("By default sorting is on template name.");
										} else {
											appLog.error("By default sorting is not on template name.");
											sa.assertTrue(false,"By default sorting is not on template name.");
										}
										if(Integer.parseInt(fp.getSelectFolderTemplateRecordCount(Workspace.InvestorWorkspace, 30).getText())==fp.getSelectFolderTemplateTemplateName(30).size()){
											appLog.info("Record Count is verified.");
										} else {
											appLog.error("Record count is not matched. Expected: "+fp.getSelectFolderTemplateTemplateName(30).size()+"\tActual: "+fp.getSelectFolderTemplateRecordCount(Workspace.InvestorWorkspace, 30));
											sa.assertTrue(false,"Record count is not matched. Expected: "+fp.getSelectFolderTemplateTemplateName(30).size()+"\tActual: "+fp.getSelectFolderTemplateRecordCount(Workspace.InvestorWorkspace, 30));
										}
										allOptions = fp.getSelectFolderTemplateTemplateName(30);
										if(checkSorting(driver, SortOrder.Assecending, allOptions)){
											appLog.info("By deafult sorting is in assending order on select folder template po up.");
										} else {
											appLog.error("By default sorting is not in assecending order on select folder template pop up.");
											sa.assertTrue(false,"By default sorting is not in assecending order on select folder template pop up.");
										}
										if(click(driver, fp.getSelectFolderTemplateGridHeader(30).get(1), "Template Name header", action.BOOLEAN)){
											if(checkSorting(driver, SortOrder.Decending, allOptions)){
												appLog.info("Sorting is in decending order on select folder template po up.");
											} else {
												appLog.error("Sorting is not in decending order on select folder template pop up.");
												sa.assertTrue(false,"Sorting is not in decending order on select folder template pop up.");
											}
										} else {
											appLog.error("cannot clikc on tempate name header, So cannot check decending sorting on template name column.");
											sa.assertTrue(false,"cannot clikc on tempate name header, So cannot check decending sorting on template name column.");
										}
									} else {
										appLog.error("Other Users' Templates option is not available in drop down.");
										sa.assertTrue(false,"Other Users' Templates option is not available in drop down.");
									}
								} else {
									appLog.error("Import folder template button cannot be clicked, So cannot check error message for other users template.");
									sa.assertTrue(false,"Import folder template button cannot be clicked, So cannot check error message for other users template.");
								}
							
							} else {
								appLog.error("Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
								sa.assertTrue(false,"Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
								fp.recover(Workspace.InvestorWorkspace, 30);
							}
							
						} else {
							appLog.error("Next button of step 1 of 3 cannot be click, So cannot check cancel button functionality..");
							sa.assertTrue(false,"Next button of step 1 of 3 cannot be click, So cannot check cancel button functionality.");
							fp.recover(Workspace.InvestorWorkspace, 30);
						}
					} else {
						appLog.error("One of the mandatory field is not filled, So cannot check cancel button functionality.");
						sa.assertTrue(false,"One of the mandatory field is not filled, So cannot check cancel button functionality.");
						fp.recover(Workspace.InvestorWorkspace, 30);
					}
				} else {
					appLog.error("Build Investor workspace button cannot be clicked, So cannot check cancel button functionality.");
					sa.assertTrue(false,"Build Investor workspace button cannot be clicked, So cannot check cancel button functionality.");
					fp.recover(Workspace.InvestorWorkspace, 30);
				}
			} else {
				appLog.error(M8F4+" fund is not available, so cannot continue with the tc.");
				sa.assertTrue(false,M8F4+" fund is not available, so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot continue with the tc.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M8tc010_VerifyImportFolderTemplateErrorMessageAndClearAll(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		boolean flag = true;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F1)){
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Funcraising wokspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 30), "Buld fundraising workspace button", action.BOOLEAN)){
					if(sendKeys(driver, fp.getFundVintageYearField(Workspace.FundraisingWorkspace, 30), "2013", "Vintage year", action.BOOLEAN)){
						appLog.info("Successfully passed value to vintage year text box.");
					} else {
						appLog.error("Cannot pass value to vintage year text box.");
						sa.assertTrue(false,"Cannot pass value to vintage year text box.");
					}
					
					if(sendKeys(driver, fp.getFundSizeField(Workspace.FundraisingWorkspace, 30), "5000", "Fund Size", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund size text box.");
					} else {
						appLog.error("Cannot pass value to Fund size text box.");
						sa.assertTrue(false,"Cannot pass value to Fund size text box.");
					}
					
					if(sendKeys(driver, fp.getFundContactField(Workspace.FundraisingWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund contact text box.");
					} else {
						appLog.error("Cannot pass value to Fund contact text box.");
						sa.assertTrue(false,"Cannot pass value to Fund contact text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundEmailField(Workspace.FundraisingWorkspace, 30), "abc@abc.com", "Fund Email", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Email text box.");
					} else {
						appLog.error("Cannot pass value to Fund Email text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Email text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundPhoneField(Workspace.FundraisingWorkspace, 30), "987654321", "Fund Phone", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Phone text box.");
					} else {
						appLog.error("Cannot pass value to Fund Phone text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Phone text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundDescriptionField(Workspace.FundraisingWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Description text box.");
					} else {
						appLog.error("Cannot pass value to Fund Description text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Description text box.");
					}
					if(flag){
						if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 30), "Next button of step 1 of 3", action.BOOLEAN)){
							if(fp.getStep2Of3Header(Workspace.FundraisingWorkspace, 30)!=null){
								if(fp.getImportFolderTemplateButton(Workspace.FundraisingWorkspace, EnableDisable.Enable, 30)!=null){
									appLog.info("Import Folder Template Button is enabled and verfied.");
								} else {
									appLog.error("Import Folder template button is not enabled and is not verified.");
									sa.assertTrue(false,"Import Folder template button is not enabled and is not verified.");
								}
								if(fp.getClearAllFolderButton(Workspace.FundraisingWorkspace, EnableDisable.Disable, 30)!=null){
									appLog.info("Clear All Button is disabled and verfied.");
								} else {
									appLog.error("Clear All button is not disabled and is not verified.");
									sa.assertTrue(false,"Clear All button is not disabled and is not verified.");
								}
								if(click(driver, fp.getImportFolderTemplateButton(Workspace.FundraisingWorkspace, 30), "Import folder template button", action.BOOLEAN)){
									WebElement ele = FindElement(driver, "//span[@title='"+ folderTemplateName +"']/../preceding-sibling::span[1]//input", folderTemplateName+" template radio button", action.BOOLEAN, 30);
									if(click(driver, ele, folderTemplateName+" folder template", action.BOOLEAN)){
										if(click(driver, fp.getSelectFolderTemplateImportButton(Workspace.FundraisingWorkspace, EnableDisable.Enable, 30), "Imprt button", action.BOOLEAN)){
											appLog.info("Folder template is imported.");
											if(fp.verifyFolderStructure(fp.folderStructureInExcel("FolderTemp"), 5)){
												appLog.info("Folder strucuture is verified.");
											} else {
												appLog.error("Folder structure is not verified.");
												sa.assertTrue(false, "Folder structure is not verified.");
											}
											if(click(driver, fp.getImportFolderTemplateButton(Workspace.FundraisingWorkspace, 30), "Import Folder Template Button", action.BOOLEAN)){
												if(fp.getImportFolderTemplateErrorMessage(Workspace.FundraisingWorkspace, 30)!=null){
													String text = fp.getImportFolderTemplateErrorMessage(Workspace.FundraisingWorkspace, 30).getText().trim();
													if(text.trim().equalsIgnoreCase(FundsPageErrorMessage.ImportFolderTemplateErrorMessage.toString())){
														appLog.info("Folder Already Imported Error Message is verified.");
													} else {
														appLog.error("Folder Already Imported ErrorMessage is not verified. Expected: "+FundsPageErrorMessage.ImportFolderTemplateErrorMessage+"\tActual: "+text);
														sa.assertTrue(false,"Folder Already Imported ErrorMessage is not verified. Expected: "+FundsPageErrorMessage.ImportFolderTemplateErrorMessage+"\tActual: "+text);
													}
												} else {
													appLog.error("Error Message is not poping up while trying to import folder template when a folder template is already been imported.");
													sa.assertTrue(false,"Error Message is not poping up while trying to import folder template when a folder template is already been imported.");
												}
												if(fp.getImportFolderTemplateErrorMessageHeader(Workspace.FundraisingWorkspace, 30)!=null){
													String text = fp.getImportFolderTemplateErrorMessageHeader(Workspace.FundraisingWorkspace, 30).getText().trim();
													if(text.equalsIgnoreCase("Alert")){
														appLog.info("Header is verified.");
													} else {
														appLog.error("Folder already imported error message pop up header is not verified. Expected: Alert\tActual: "+text);
														sa.assertTrue(false,"Folder already imported error message pop up header is not verified. Expected: Alert\tActual: "+text);
													}
												} else {
													appLog.error("Folder already imported error message pop up header is not present");
													sa.assertTrue(false,"Folder already imported error message pop up header is not present");
												}
												if(fp.getImportFolderTemplateErrorMessageOkButton(Workspace.FundraisingWorkspace, 30)!=null){
													appLog.info("Import Folder Template error Message pop up Ok Button is present.");
												} else {
													appLog.error("Import Folder Template Error Message Pop Up Ok Button Is Not Present.");
													sa.assertTrue(false,"Import Folder Template Error Message Pop Up Ok Button Is Not Present.");
												}
												if(fp.getImportFolderTemplateErrorMessageCrossIcon(Workspace.FundraisingWorkspace, 30)!=null){
													appLog.info("Cross iCon is verified.");
												} else {
													appLog.error("Import folder template error message pop up Cross icon is not present.");
													sa.assertTrue(false,"Import folder template error message pop up Cross icon is not present.");
												}
												if(click(driver, fp.getImportFolderTemplateErrorMessageOkButton(Workspace.FundraisingWorkspace, 30), "IMport Folder template ok button", action.BOOLEAN)){
													ThreadSleep(1500);
													if(fp.getImportFolderTemplateErrorMessageHeader(Workspace.FundraisingWorkspace, 30)!=null){
														appLog.error("Folder already imported error message pop up is not closed after clicking on Ok Button.");
														sa.assertTrue(false,"Folder already imported error message pop up is not closed after clicking on Ok Button.");
														flag = false;
													} else {
														appLog.info("Folder already imported error message pop up is closed after clicking on the Ok Button");
														if(fp.getClearAllFolderButton(Workspace.FundraisingWorkspace, EnableDisable.Enable, 30)!=null){
															appLog.info("Clear All Button is Enabled and verfied.");
														} else {
															appLog.error("Clear All button is disabled and is not verified.");
															sa.assertTrue(false,"Clear All button is disabled and is not verified.");
														}
														if(fp.verifyFolderStructure(fp.folderStructureInExcel("FolderTemp"), 5)){
															appLog.info("Folder strucuture is verified.");
														} else {
															appLog.error("Folder structure is not verified.");
															sa.assertTrue(false, "Folder structure is not verified.");
														}
													}
												} else {
													appLog.error("Cannot click on import folder template ok button, So cannot check its functionality.");
													sa.assertTrue(false,"Cannot click on import folder template ok button, So cannot check its functionality.");
												}
												if(!flag){
													click(driver, fp.getImportFolderTemplateErrorMessageCrossIcon(Workspace.FundraisingWorkspace, 30), "cross icon", action.BOOLEAN);
												}
												if(click(driver, fp.getImportFolderTemplateButton(Workspace.FundraisingWorkspace, 30), "IMport folder template button", action.BOOLEAN)){
													if(click(driver, fp.getImportFolderTemplateErrorMessageCrossIcon(Workspace.FundraisingWorkspace, 30), "Cross icon", action.BOOLEAN)){
														ThreadSleep(1500);
														if(fp.getImportFolderTemplateErrorMessageHeader(Workspace.FundraisingWorkspace, 30)!=null){
															appLog.error("Folder already imported error message pop up is not closed after clicking on Ok Button.");
															sa.assertTrue(false,"Folder already imported error message pop up is not closed after clicking on Ok Button.");
//															flag = false;
														} else {
															appLog.info("Folder already imported error message pop up is closed after clicking on the Ok Button");
															if(fp.getClearAllFolderButton(Workspace.FundraisingWorkspace, EnableDisable.Enable, 30)!=null){
																appLog.info("Clear All Button is Enabled and verfied.");
															} else {
																appLog.error("Clear All button is disabled and is not verified.");
																sa.assertTrue(false,"Clear All button is disabled and is not verified.");
															}
															if(fp.verifyFolderStructure(fp.folderStructureInExcel("FolderTemp"), 5)){
																appLog.info("Folder strucuture is verified.");
															} else {
																appLog.error("Folder structure is not verified.");
																sa.assertTrue(false, "Folder structure is not verified.");
															}
															flag=true;
														}
													} else {
														appLog.error("cannot click on cross icon, So cannot check its functionality.");
														sa.assertTrue(false,"cannot click on cross icon, So cannot check its functionality.");
													}
												} else {
													appLog.error("cannot click on import folder template button, So cannot check cross icon functionality.");
													sa.assertTrue(false,"cannot click on import folder template button, So cannot check cross icon functionality.");
												}
												if(!flag){
													click(driver, fp.getImportFolderTemplateErrorMessageOkButton(Workspace.FundraisingWorkspace, 30), "Ok Button", action.BOOLEAN);
												}
											} else {
												appLog.error("Import Folder Template button cannot be clicked, So cannot check error message.");
												sa.assertTrue(false,"Import Folder Template button cannot be clicked, So cannot check error message.");
											}
											
											if(click(driver, fp.getClearAllFolderButton(Workspace.FundraisingWorkspace, EnableDisable.Enable, 30), "Import Folder Template Button", action.BOOLEAN)){
												if(fp.getClearAllFolderConfirmationPopUpHeader(Workspace.FundraisingWorkspace, 30)!=null){
													appLog.info("Confirmation pop up is successfully opened.");
												} else {
													appLog.error("Confirmation pop up is not opening after clicking on the clear all folder button.");
													sa.assertTrue(false,"Confirmation pop up is not opening after clicking on the clear all folder button.");
												}
												
												if(fp.getClearAllFolderConfirmationPopUpMessage(Workspace.FundraisingWorkspace, 30)!=null){
													String text = fp.getClearAllFolderConfirmationPopUpMessage(Workspace.FundraisingWorkspace, 30).getText().trim();
													if(text.equalsIgnoreCase(FundsPageErrorMessage.ClearAllFolderConfirmationMessage)){
														appLog.info("Confirmation message is verified.");
													} else {
														appLog.error("Confirmation message is not verified. Expected: "+FundsPageErrorMessage.ClearAllFolderConfirmationMessage+"\tActual: "+text);
														sa.assertTrue(false,"Confirmation message is not verified. Expected: "+FundsPageErrorMessage.ClearAllFolderConfirmationMessage+"\tActual: "+text);
													}
												} else {
													appLog.error("Confirmation message is not poping up.");
													sa.assertTrue(false, "Confirmation message is not poping up.");
												}
												
												if(fp.getClearAllFolderConfirmationPopUpYesButton(Workspace.FundraisingWorkspace, 30)!=null){
													appLog.info("Yes button is verified."); 
												} else {
													appLog.error("Yes button is not visible on confirmation pop up.");
													sa.assertTrue(false,"Yes button is not visible on confirmation pop up.");
												}
												
												if(fp.getClearAllFolderConfirmationPopUpNoButton(Workspace.FundraisingWorkspace, 30)!=null){
													appLog.info("No button is verified."); 
												} else {
													appLog.error("No button is not visible on confirmation pop up.");
													sa.assertTrue(false,"No button is not visible on confirmation pop up.");
												}
												
												if(fp.getClearAllFolderConfirmationPopUpCrossIcon(Workspace.FundraisingWorkspace, 30)!=null){
													appLog.info("Clear all folder confirmation pop up cross icon is verified.");
												} else {
													appLog.error("Clear all folder confirmation pop up cross icon is not present.");
													sa.assertTrue(false,"Clear all folder confirmation pop up cross icon is not present.");
												}
												
												if(click(driver, fp.getClearAllFolderConfirmationPopUpCrossIcon(Workspace.FundraisingWorkspace, 30), "Cross icon", action.BOOLEAN)){
													ThreadSleep(1500);
													if(fp.getClearAllFolderConfirmationPopUpHeader(Workspace.FundraisingWorkspace, 30)!=null){
														appLog.error("Confirmation pop up is not closed after clicking on the cross icon.");
														sa.assertTrue(false,"Confirmation pop up is not closed after clicking on the cross icon.");
														flag = false;
													} else {
														appLog.info("Confirmation pop up is closed after clicking on cross icon and is verified.");
													}
												} else {
													appLog.error("Clear all folder confirmation pop up cross icon cannot be clicked, so cannot check the functionality.");
													sa.assertTrue(false,"Clear all folder confirmation pop up cross icon cannot be clicked, so cannot check the functionality.");
												}
												
												if(!flag){
													click(driver, fp.getClearAllFolderConfirmationPopUpNoButton(Workspace.FundraisingWorkspace, 30), "No Button", action.BOOLEAN);
												}
												
												if(click(driver, fp.getClearAllFolderButton(Workspace.FundraisingWorkspace, EnableDisable.Enable, 30), "Clear All Folder Button", action.BOOLEAN)){
													if(click(driver, fp.getClearAllFolderConfirmationPopUpYesButton(Workspace.FundraisingWorkspace, 30), "Yes Button", action.BOOLEAN)){
														if(fp.getClearAllFolderConfirmationPopUpHeader(Workspace.FundraisingWorkspace, 30)!=null){
															appLog.error("Confirmation pop up is not closed after clicking on Yes Button.");
															sa.assertTrue(false,"Confirmation pop up is not closed after clicking on Yes Button.");
															flag = false;
														} else {
															appLog.info("Confirmation pop up is closed after clicking on Yes Button and is verified.");
														}
														if(!fp.verifyFolderStructure(fp.folderStructureInExcel("FolderTemp"),0)){
															appLog.info("Folders are successfully cleared.");
														} else {
															appLog.error("Folders structure is not cleared after clicking on clear all folder.");
															sa.assertTrue(false, "Folders structure is not cleared after clicking on clear all folder.");
														}
														if(fp.getClearAllFolderButton(Workspace.FundraisingWorkspace, EnableDisable.Disable, 30)!=null){
															appLog.info("Clear All Button is disabled and verfied.");
														} else {
															appLog.error("Clear All button is not disabled and is not verified.");
															sa.assertTrue(false,"Clear All button is not disabled and is not verified.");
														}
														if(click(driver, fp.getImportFolderTemplateButton(Workspace.FundraisingWorkspace, 30), "Import Folder Template", action.BOOLEAN)){
															if(selectVisibleTextFromDropDown(driver, fp.getSelectFolderTemplateDropDown(Workspace.FundraisingWorkspace, 30), "Drop Down", "Other Users' Templates")){
																ele = FindElement(driver, "//span[@title='"+ folderTemplateName+"3" +"']/../preceding-sibling::span[1]//input", folderTemplateName+" template radio button", action.BOOLEAN, 30);
																if(click(driver, ele, folderTemplateName+" folder template", action.BOOLEAN)){
																	if(click(driver, fp.getSelectFolderTemplateImportButton(Workspace.FundraisingWorkspace, EnableDisable.Enable, 30), "Imprt button", action.BOOLEAN)){
																		appLog.info("Folder template is imported.");
																		if(fp.verifyFolderStructure(fp.folderStructureInExcel("FolderTemp"), 5)){
																			appLog.info("Folder strucuture is verified.");
																		} else {
																			appLog.error("Folder structure is not verified.");
																			sa.assertTrue(false, "Folder structure is not verified.");
																		}
																		if(fp.getClearAllFolderButton(Workspace.FundraisingWorkspace, EnableDisable.Enable, 30)!=null){
																			appLog.info("Clear All Button is Enabled and verfied.");
																		} else {
																			appLog.error("Clear All button is disabled and is not verified.");
																			sa.assertTrue(false,"Clear All button is disabled and is not verified.");
																		}
																	} else {
																		appLog.error("Import folder template button cannot be clicked, So cannnot import folder template.");
																		sa.assertTrue(false,"Import folder template button cannot be clicked, So cannnot import folder template.");
																	}
																} else {
																	appLog.error("Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
																	sa.assertTrue(false,"Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
																	fp.recover(Workspace.FundraisingWorkspace, 30);
																}
															} else {
																appLog.error("cannot select Other Users' Template from the drop down, So cannot import folder template.");
																sa.assertTrue(false,"cannot select Other Users' Template from the drop down, So cannot import folder template.");
															}
														} else {
															appLog.error("Import Folder Template Button cannot be clicked, So cannot import "+folderTemplateName+"3 folder template.");
															sa.assertTrue(false,"Import Folder Template Button cannot be clicked, So cannot import "+folderTemplateName+"3 folder template.");
														}
													} else {
														appLog.error("Yes button cannot be clicked, So cannot check the functionality.");
														sa.assertTrue(false,"Yes button cannot be clicked, So cannot check the functionality.");
													}
												} else {
													appLog.error("Clear all folder button cannot be clicked, So cannot check the functionality.");
													sa.assertTrue(false,"Clear all folder button cannot be clicked, So cannot check the functionality.");
												}
											} else {
												appLog.error("Clear All Button Cannot be clicked, So cannot check the functionality.");
												sa.assertTrue(false,"Clear All Button Cannot be clicked, So cannot check the functionality.");
											}
										} else {
											appLog.error("Cannot click on import button so cannot import folder template.");
											sa.assertTrue(false,"Cannot click on import button so cannot import folder template.");
										}
									} else {
										appLog.error(folderTemplateName+" template is not available to import, So canot import folder template.");
										sa.assertTrue(false,folderTemplateName+" template is not available to import, So canot import folder template.");
									}
								} else {
									appLog.error("Import folder template button cannot be clicked, So cannnot import folder template.");
									sa.assertTrue(false,"Import folder template button cannot be clicked, So cannnot import folder template.");
								}
								
							} else {
								appLog.error("Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
								sa.assertTrue(false,"Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
								fp.recover(Workspace.FundraisingWorkspace, 30);
							}
						} else {
							appLog.error("Next button of step 1 of 3 cannot be click, So cannot check cancel button functionality..");
							sa.assertTrue(false,"Next button of step 1 of 3 cannot be click, So cannot check cancel button functionality.");
							fp.recover(Workspace.FundraisingWorkspace, 30);
						}
					} else {
						appLog.error("One of the mandatory field is not filled, So cannot check cancel button functionality.");
						sa.assertTrue(false,"One of the mandatory field is not filled, So cannot check cancel button functionality.");
						fp.recover(Workspace.FundraisingWorkspace, 30);
					}
				} else {
					appLog.error("Build fundraising workspace button cannot be clicked, So cannot check cancel button functionality.");
					sa.assertTrue(false,"Build fundraising workspace button cannot be clicked, So cannot check cancel button functionality.");
					fp.recover(Workspace.FundraisingWorkspace, 30);
				}
			} else {
				appLog.error(M8F1+" fund is not available, so cannot continue with the tc.");
				sa.assertTrue(false,M8F1+" fund is not available, so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot continue with the tc.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M8tc022_VerifyImportFolderTemplateErrorMessageAndClearAllInvestorWorkspace(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		boolean flag = true;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F4)){
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Funcraising wokspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.InvestorWorkspace, 30), "Buld Investor workspace button", action.BOOLEAN)){
					if(sendKeys(driver, fp.getFundVintageYearField(Workspace.InvestorWorkspace, 30), "2013", "Vintage year", action.BOOLEAN)){
						appLog.info("Successfully passed value to vintage year text box.");
					} else {
						appLog.error("Cannot pass value to vintage year text box.");
						sa.assertTrue(false,"Cannot pass value to vintage year text box.");
					}
					
					if(sendKeys(driver, fp.getFundSizeField(Workspace.InvestorWorkspace, 30), "5000", "Fund Size", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund size text box.");
					} else {
						appLog.error("Cannot pass value to Fund size text box.");
						sa.assertTrue(false,"Cannot pass value to Fund size text box.");
					}
					
					if(sendKeys(driver, fp.getFundContactField(Workspace.InvestorWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund contact text box.");
					} else {
						appLog.error("Cannot pass value to Fund contact text box.");
						sa.assertTrue(false,"Cannot pass value to Fund contact text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundEmailField(Workspace.InvestorWorkspace, 30), "abc@abc.com", "Fund Email", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Email text box.");
					} else {
						appLog.error("Cannot pass value to Fund Email text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Email text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundPhoneField(Workspace.InvestorWorkspace, 30), "987654321", "Fund Phone", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Phone text box.");
					} else {
						appLog.error("Cannot pass value to Fund Phone text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Phone text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundDescriptionField(Workspace.InvestorWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Description text box.");
					} else {
						appLog.error("Cannot pass value to Fund Description text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Description text box.");
					}
					if(flag){
						if(click(driver, fp.getNext1Of3Button(Workspace.InvestorWorkspace, 30), "Next button of step 1 of 3", action.BOOLEAN)){
							if(fp.getStep2Of3Header(Workspace.InvestorWorkspace, 30)!=null){
								if(fp.getImportFolderTemplateButton(Workspace.InvestorWorkspace, EnableDisable.Enable, 30)!=null){
									appLog.info("Import Folder Template Button is enabled and verfied.");
								} else {
									appLog.error("Import Folder template button is not enabled and is not verified.");
									sa.assertTrue(false,"Import Folder template button is not enabled and is not verified.");
								}
								if(fp.getClearAllFolderButton(Workspace.InvestorWorkspace, EnableDisable.Disable, 30)!=null){
									appLog.info("Clear All Button is disabled and verfied.");
								} else {
									appLog.error("Clear All button is not disabled and is not verified.");
									sa.assertTrue(false,"Clear All button is not disabled and is not verified.");
								}
								if(click(driver, fp.getImportFolderTemplateButton(Workspace.InvestorWorkspace, 30), "Import folder template button", action.BOOLEAN)){
									WebElement ele = FindElement(driver, "//span[@title='"+ folderTemplateName +"']/../preceding-sibling::span[1]//input", folderTemplateName+" template radio button", action.BOOLEAN, 30);
									if(click(driver, ele, folderTemplateName+" folder template", action.BOOLEAN)){
										if(click(driver, fp.getSelectFolderTemplateImportButton(Workspace.InvestorWorkspace, EnableDisable.Enable, 30), "Imprt button", action.BOOLEAN)){
											appLog.info("Folder template is imported.");
											if(fp.verifyFolderStructure(fp.folderStructureInExcel("FolderTemp"), 5)){
												appLog.info("Folder strucuture is verified.");
											} else {
												appLog.error("Folder structure is not verified.");
												sa.assertTrue(false, "Folder structure is not verified.");
											}
											if(click(driver, fp.getImportFolderTemplateButton(Workspace.InvestorWorkspace, 30), "Import Folder Template Button", action.BOOLEAN)){
												if(fp.getImportFolderTemplateErrorMessage(Workspace.InvestorWorkspace, 30)!=null){
													String text = fp.getImportFolderTemplateErrorMessage(Workspace.InvestorWorkspace, 30).getText().trim();
													if(text.trim().equalsIgnoreCase(FundsPageErrorMessage.ImportFolderTemplateErrorMessage.toString())){
														appLog.info("Folder Already Imported Error Message is verified.");
													} else {
														appLog.error("Folder Already Imported ErrorMessage is not verified. Expected: "+FundsPageErrorMessage.ImportFolderTemplateErrorMessage+"\tActual: "+text);
														sa.assertTrue(false,"Folder Already Imported ErrorMessage is not verified. Expected: "+FundsPageErrorMessage.ImportFolderTemplateErrorMessage+"\tActual: "+text);
													}
												} else {
													appLog.error("Error Message is not poping up while trying to import folder template when a folder template is already been imported.");
													sa.assertTrue(false,"Error Message is not poping up while trying to import folder template when a folder template is already been imported.");
												}
												if(fp.getImportFolderTemplateErrorMessageHeader(Workspace.InvestorWorkspace, 30)!=null){
													String text = fp.getImportFolderTemplateErrorMessageHeader(Workspace.InvestorWorkspace, 30).getText().trim();
													if(text.equalsIgnoreCase("Alert")){
														appLog.info("Header is verified.");
													} else {
														appLog.error("Folder already imported error message pop up header is not verified. Expected: Alert\tActual: "+text);
														sa.assertTrue(false,"Folder already imported error message pop up header is not verified. Expected: Alert\tActual: "+text);
													}
												} else {
													appLog.error("Folder already imported error message pop up header is not present");
													sa.assertTrue(false,"Folder already imported error message pop up header is not present");
												}
												if(fp.getImportFolderTemplateErrorMessageOkButton(Workspace.InvestorWorkspace, 30)!=null){
													appLog.info("Import Folder Template error Message pop up Ok Button is present.");
												} else {
													appLog.error("Import Folder Template Error Message Pop Up Ok Button Is Not Present.");
													sa.assertTrue(false,"Import Folder Template Error Message Pop Up Ok Button Is Not Present.");
												}
												if(fp.getImportFolderTemplateErrorMessageCrossIcon(Workspace.InvestorWorkspace, 30)!=null){
													appLog.info("Cross iCon is verified.");
												} else {
													appLog.error("Import folder template error message pop up Cross icon is not present.");
													sa.assertTrue(false,"Import folder template error message pop up Cross icon is not present.");
												}
												if(click(driver, fp.getImportFolderTemplateErrorMessageOkButton(Workspace.InvestorWorkspace, 30), "IMport Folder template ok button", action.BOOLEAN)){
													ThreadSleep(1500);
													if(fp.getImportFolderTemplateErrorMessageHeader(Workspace.InvestorWorkspace, 30)!=null){
														appLog.error("Folder already imported error message pop up is not closed after clicking on Ok Button.");
														sa.assertTrue(false,"Folder already imported error message pop up is not closed after clicking on Ok Button.");
														flag = false;
													} else {
														appLog.info("Folder already imported error message pop up is closed after clicking on the Ok Button");
														if(fp.getClearAllFolderButton(Workspace.InvestorWorkspace, EnableDisable.Enable, 30)!=null){
															appLog.info("Clear All Button is Enabled and verfied.");
														} else {
															appLog.error("Clear All button is disabled and is not verified.");
															sa.assertTrue(false,"Clear All button is disabled and is not verified.");
														}
														if(fp.verifyFolderStructure(fp.folderStructureInExcel("FolderTemp"), 5)){
															appLog.info("Folder strucuture is verified.");
														} else {
															appLog.error("Folder structure is not verified.");
															sa.assertTrue(false, "Folder structure is not verified.");
														}
													}
												} else {
													appLog.error("Cannot click on import folder template ok button, So cannot check its functionality.");
													sa.assertTrue(false,"Cannot click on import folder template ok button, So cannot check its functionality.");
												}
												if(!flag){
													click(driver, fp.getImportFolderTemplateErrorMessageCrossIcon(Workspace.InvestorWorkspace, 30), "cross icon", action.BOOLEAN);
												}
												if(click(driver, fp.getImportFolderTemplateButton(Workspace.InvestorWorkspace, 30), "IMport folder template button", action.BOOLEAN)){
													if(click(driver, fp.getImportFolderTemplateErrorMessageCrossIcon(Workspace.InvestorWorkspace, 30), "Cross icon", action.BOOLEAN)){
														ThreadSleep(1500);
														if(fp.getImportFolderTemplateErrorMessageHeader(Workspace.InvestorWorkspace, 30)!=null){
															appLog.error("Folder already imported error message pop up is not closed after clicking on Ok Button.");
															sa.assertTrue(false,"Folder already imported error message pop up is not closed after clicking on Ok Button.");
//															flag = false;
														} else {
															appLog.info("Folder already imported error message pop up is closed after clicking on the Ok Button");
															if(fp.getClearAllFolderButton(Workspace.InvestorWorkspace, EnableDisable.Enable, 30)!=null){
																appLog.info("Clear All Button is Enabled and verfied.");
															} else {
																appLog.error("Clear All button is disabled and is not verified.");
																sa.assertTrue(false,"Clear All button is disabled and is not verified.");
															}
															if(fp.verifyFolderStructure(fp.folderStructureInExcel("FolderTemp"), 5)){
																appLog.info("Folder strucuture is verified.");
															} else {
																appLog.error("Folder structure is not verified.");
																sa.assertTrue(false, "Folder structure is not verified.");
															}
															flag=true;
														}
													} else {
														appLog.error("cannot click on cross icon, So cannot check its functionality.");
														sa.assertTrue(false,"cannot click on cross icon, So cannot check its functionality.");
													}
												} else {
													appLog.error("cannot click on import folder template button, So cannot check cross icon functionality.");
													sa.assertTrue(false,"cannot click on import folder template button, So cannot check cross icon functionality.");
												}
												if(!flag){
													click(driver, fp.getImportFolderTemplateErrorMessageOkButton(Workspace.InvestorWorkspace, 30), "Ok Button", action.BOOLEAN);
												}
											} else {
												appLog.error("Import Folder Template button cannot be clicked, So cannot check error message.");
												sa.assertTrue(false,"Import Folder Template button cannot be clicked, So cannot check error message.");
											}
											
											if(click(driver, fp.getClearAllFolderButton(Workspace.InvestorWorkspace, EnableDisable.Enable, 30), "Import Folder Template Button", action.BOOLEAN)){
												if(fp.getClearAllFolderConfirmationPopUpHeader(Workspace.InvestorWorkspace, 30)!=null){
													appLog.info("Confirmation pop up is successfully opened.");
												} else {
													appLog.error("Confirmation pop up is not opening after clicking on the clear all folder button.");
													sa.assertTrue(false,"Confirmation pop up is not opening after clicking on the clear all folder button.");
												}
												
												if(fp.getClearAllFolderConfirmationPopUpMessage(Workspace.InvestorWorkspace, 30)!=null){
													String text = fp.getClearAllFolderConfirmationPopUpMessage(Workspace.InvestorWorkspace, 30).getText().trim();
													if(text.equalsIgnoreCase(FundsPageErrorMessage.ClearAllFolderConfirmationMessage)){
														appLog.info("Confirmation message is verified.");
													} else {
														appLog.error("Confirmation message is not verified. Expected: "+FundsPageErrorMessage.ClearAllFolderConfirmationMessage+"\tActual: "+text);
														sa.assertTrue(false,"Confirmation message is not verified. Expected: "+FundsPageErrorMessage.ClearAllFolderConfirmationMessage+"\tActual: "+text);
													}
												} else {
													appLog.error("Confirmation message is not poping up.");
													sa.assertTrue(false, "Confirmation message is not poping up.");
												}
												
												if(fp.getClearAllFolderConfirmationPopUpYesButton(Workspace.InvestorWorkspace, 30)!=null){
													appLog.info("Yes button is verified."); 
												} else {
													appLog.error("Yes button is not visible on confirmation pop up.");
													sa.assertTrue(false,"Yes button is not visible on confirmation pop up.");
												}
												
												if(fp.getClearAllFolderConfirmationPopUpNoButton(Workspace.InvestorWorkspace, 30)!=null){
													appLog.info("No button is verified."); 
												} else {
													appLog.error("No button is not visible on confirmation pop up.");
													sa.assertTrue(false,"No button is not visible on confirmation pop up.");
												}
												
												if(fp.getClearAllFolderConfirmationPopUpCrossIcon(Workspace.InvestorWorkspace, 30)!=null){
													appLog.info("Clear all folder confirmation pop up cross icon is verified.");
												} else {
													appLog.error("Clear all folder confirmation pop up cross icon is not present.");
													sa.assertTrue(false,"Clear all folder confirmation pop up cross icon is not present.");
												}
												
												if(click(driver, fp.getClearAllFolderConfirmationPopUpCrossIcon(Workspace.InvestorWorkspace, 30), "Cross icon", action.BOOLEAN)){
													ThreadSleep(1500);
													if(fp.getClearAllFolderConfirmationPopUpHeader(Workspace.InvestorWorkspace, 30)!=null){
														appLog.error("Confirmation pop up is not closed after clicking on the cross icon.");
														sa.assertTrue(false,"Confirmation pop up is not closed after clicking on the cross icon.");
														flag = false;
													} else {
														appLog.info("Confirmation pop up is closed after clicking on cross icon and is verified.");
													}
												} else {
													appLog.error("Clear all folder confirmation pop up cross icon cannot be clicked, so cannot check the functionality.");
													sa.assertTrue(false,"Clear all folder confirmation pop up cross icon cannot be clicked, so cannot check the functionality.");
												}
												
												if(!flag){
													click(driver, fp.getClearAllFolderConfirmationPopUpNoButton(Workspace.InvestorWorkspace, 30), "No Button", action.BOOLEAN);
												}
												
												if(click(driver, fp.getClearAllFolderButton(Workspace.InvestorWorkspace, EnableDisable.Enable, 30), "Clear All Folder Button", action.BOOLEAN)){
													if(click(driver, fp.getClearAllFolderConfirmationPopUpYesButton(Workspace.InvestorWorkspace, 30), "Yes Button", action.BOOLEAN)){
														if(fp.getClearAllFolderConfirmationPopUpHeader(Workspace.InvestorWorkspace, 30)!=null){
															appLog.error("Confirmation pop up is not closed after clicking on Yes Button.");
															sa.assertTrue(false,"Confirmation pop up is not closed after clicking on Yes Button.");
															flag = false;
														} else {
															appLog.info("Confirmation pop up is closed after clicking on Yes Button and is verified.");
														}
														if(!fp.verifyFolderStructure(fp.folderStructureInExcel("FolderTemp"),0)){
															appLog.info("Folders are successfully cleared.");
														} else {
															appLog.error("Folders structure is not cleared after clicking on clear all folder.");
															sa.assertTrue(false, "Folders structure is not cleared after clicking on clear all folder.");
														}
														if(fp.getClearAllFolderButton(Workspace.InvestorWorkspace, EnableDisable.Disable, 30)!=null){
															appLog.info("Clear All Button is disabled and verfied.");
														} else {
															appLog.error("Clear All button is not disabled and is not verified.");
															sa.assertTrue(false,"Clear All button is not disabled and is not verified.");
														}
														if(click(driver, fp.getImportFolderTemplateButton(Workspace.InvestorWorkspace, 30), "Import Folder Template", action.BOOLEAN)){
															if(selectVisibleTextFromDropDown(driver, fp.getSelectFolderTemplateDropDown(Workspace.InvestorWorkspace, 30), "Drop Down", "Other Users' Templates")){
																ele = FindElement(driver, "//span[@title='"+ folderTemplateName+"3" +"']/../preceding-sibling::span[1]//input", folderTemplateName+" template radio button", action.BOOLEAN, 30);
																if(click(driver, ele, folderTemplateName+" folder template", action.BOOLEAN)){
																	if(click(driver, fp.getSelectFolderTemplateImportButton(Workspace.InvestorWorkspace, EnableDisable.Enable, 30), "Imprt button", action.BOOLEAN)){
																		appLog.info("Folder template is imported.");
																		if(fp.verifyFolderStructure(fp.folderStructureInExcel("FolderTemp"), 5)){
																			appLog.info("Folder strucuture is verified.");
																		} else {
																			appLog.error("Folder structure is not verified.");
																			sa.assertTrue(false, "Folder structure is not verified.");
																		}
																		if(fp.getClearAllFolderButton(Workspace.InvestorWorkspace, EnableDisable.Enable, 30)!=null){
																			appLog.info("Clear All Button is Enabled and verfied.");
																		} else {
																			appLog.error("Clear All button is disabled and is not verified.");
																			sa.assertTrue(false,"Clear All button is disabled and is not verified.");
																		}
																	} else {
																		appLog.error("Import folder template button cannot be clicked, So cannnot import folder template.");
																		sa.assertTrue(false,"Import folder template button cannot be clicked, So cannnot import folder template.");
																	}
																} else {
																	appLog.error("Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
																	sa.assertTrue(false,"Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
																	fp.recover(Workspace.InvestorWorkspace, 30);
																}
															} else {
																appLog.error("cannot select Other Users' Template from the drop down, So cannot import folder template.");
																sa.assertTrue(false,"cannot select Other Users' Template from the drop down, So cannot import folder template.");
															}
														} else {
															appLog.error("Import Folder Template Button cannot be clicked, So cannot import "+folderTemplateName+"3 folder template.");
															sa.assertTrue(false,"Import Folder Template Button cannot be clicked, So cannot import "+folderTemplateName+"3 folder template.");
														}
													} else {
														appLog.error("Yes button cannot be clicked, So cannot check the functionality.");
														sa.assertTrue(false,"Yes button cannot be clicked, So cannot check the functionality.");
													}
												} else {
													appLog.error("Clear all folder button cannot be clicked, So cannot check the functionality.");
													sa.assertTrue(false,"Clear all folder button cannot be clicked, So cannot check the functionality.");
												}
											} else {
												appLog.error("Clear All Button Cannot be clicked, So cannot check the functionality.");
												sa.assertTrue(false,"Clear All Button Cannot be clicked, So cannot check the functionality.");
											}
										} else {
											appLog.error("Cannot click on import button so cannot import folder template.");
											sa.assertTrue(false,"Cannot click on import button so cannot import folder template.");
										}
									} else {
										appLog.error(folderTemplateName+" template is not available to import, So canot import folder template.");
										sa.assertTrue(false,folderTemplateName+" template is not available to import, So canot import folder template.");
									}
								} else {
									appLog.error("Import folder template button cannot be clicked, So cannnot import folder template.");
									sa.assertTrue(false,"Import folder template button cannot be clicked, So cannnot import folder template.");
								}
								
							} else {
								appLog.error("Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
								sa.assertTrue(false,"Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify UI and cancel button functionality.");
								fp.recover(Workspace.InvestorWorkspace, 30);
							}
						} else {
							appLog.error("Next button of step 1 of 3 cannot be click, So cannot check cancel button functionality..");
							sa.assertTrue(false,"Next button of step 1 of 3 cannot be click, So cannot check cancel button functionality.");
							fp.recover(Workspace.InvestorWorkspace, 30);
						}
					} else {
						appLog.error("One of the mandatory field is not filled, So cannot check cancel button functionality.");
						sa.assertTrue(false,"One of the mandatory field is not filled, So cannot check cancel button functionality.");
						fp.recover(Workspace.InvestorWorkspace, 30);
					}
				} else {
					appLog.error("Build Investor workspace button cannot be clicked, So cannot check cancel button functionality.");
					sa.assertTrue(false,"Build Investor workspace button cannot be clicked, So cannot check cancel button functionality.");
					fp.recover(Workspace.InvestorWorkspace, 30);
				}
			} else {
				appLog.error(M8F4+" fund is not available, so cannot continue with the tc.");
				sa.assertTrue(false,M8F4+" fund is not available, so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot continue with the tc.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M8tc011_VerifyEditingAFolder(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		boolean flag = true;
		String dependsOnTC = currentlyExecutingTC;
		String STDFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependsOnTC,excelLabel.StandardPath);
		String[] stdFolderName=STDFolderName.split(",");
		String[] commonFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependsOnTC,excelLabel.CommonPath).split(",");
		String[] InternalFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependsOnTC,excelLabel.InternalPath).split(",");
		String[] SHRDFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependsOnTC,excelLabel.SharedPath).split(",");
		List<String> PrefixValue = getValueBasedOnLabel(filePath, "SpecialChar",excelLabel.INVALID_FOLDER_NAME, 1);
		List<String> specialChar = getValueBasedOnLabel(filePath, "SpecialChar",excelLabel.FOLDER_NAME, 1);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F1)){
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Funcraising wokspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 30), "Buld Fundraising workspace button", action.BOOLEAN)){
					if(sendKeys(driver, fp.getFundVintageYearField(Workspace.FundraisingWorkspace, 30), "2013", "Vintage year", action.BOOLEAN)){
						appLog.info("Successfully passed value to vintage year text box.");
					} else {
						appLog.error("Cannot pass value to vintage year text box.");
						sa.assertTrue(false,"Cannot pass value to vintage year text box.");
					}
					
					if(sendKeys(driver, fp.getFundSizeField(Workspace.FundraisingWorkspace, 30), "5000", "Fund Size", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund size text box.");
					} else {
						appLog.error("Cannot pass value to Fund size text box.");
						sa.assertTrue(false,"Cannot pass value to Fund size text box.");
					}
					
					if(sendKeys(driver, fp.getFundContactField(Workspace.FundraisingWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund contact text box.");
					} else {
						appLog.error("Cannot pass value to Fund contact text box.");
						sa.assertTrue(false,"Cannot pass value to Fund contact text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundEmailField(Workspace.FundraisingWorkspace, 30), "abc@abc.com", "Fund Email", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Email text box.");
					} else {
						appLog.error("Cannot pass value to Fund Email text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Email text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundPhoneField(Workspace.FundraisingWorkspace, 30), "987654321", "Fund Phone", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Phone text box.");
					} else {
						appLog.error("Cannot pass value to Fund Phone text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Phone text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundDescriptionField(Workspace.FundraisingWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Description text box.");
					} else {
						appLog.error("Cannot pass value to Fund Description text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Description text box.");
					}
					if(flag){
						if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 30), "Next button of step 1 of 3", action.BOOLEAN)){
							if(fp.getStep2Of3Header(Workspace.FundraisingWorkspace, 30)!=null){
								if(fp.getImportFolderTemplateButton(Workspace.FundraisingWorkspace, EnableDisable.Enable, 30)!=null){
									appLog.info("Import Folder Template Button is enabled and verfied.");
								} else {
									appLog.error("Import Folder template button is not enabled and is not verified.");
									sa.assertTrue(false,"Import Folder template button is not enabled and is not verified.");
								}
								if(click(driver, fp.getImportFolderTemplateButton(Workspace.FundraisingWorkspace, 30), "Import folder template button", action.BOOLEAN)){
									WebElement ele = FindElement(driver, "//span[@title='"+ folderTemplateName +"']/../preceding-sibling::span[1]//input", folderTemplateName+" template radio button", action.BOOLEAN, 30);
									if(click(driver, ele, folderTemplateName+" folder template", action.BOOLEAN)){
										if(click(driver, fp.getSelectFolderTemplateImportButton(Workspace.FundraisingWorkspace, EnableDisable.Enable, 30), "Imprt button", action.BOOLEAN)){

											//String[] radioBtn= {STDFolderName,commonFolderName,SHRDFolderName,InternalFolderName};
											String id=null;
											id=fp.getCreatedFolderId(commonFolderName[0], PageName.FundsPage, 20);
											System.err.println("id>>>>>>"+id);
											if(id!=null) {
												if(fp.clickOnRenameFolderButton(id)) {
													if(fp.verifyRenameFolderErrorMessageParentLevel(FolderType.Common, Workspace.FundraisingWorkspace, PageName.BuildStep2Of3,FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg,"",ErrorMessageType.BlankErrorMsg,20)) {
														appLog.info("Error Message is verified for Parnet Level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
													}else {
														appLog.error("Error Message is not verified for parent Level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
														sa.assertTrue(false, "Error Message is not verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
													}
													for(int i=0; i<PrefixValue.size(); i++) {
														if(fp.verifyRenameFolderErrorMessageParentLevel(FolderType.Common, Workspace.FundraisingWorkspace, PageName.BuildStep2Of3,FundsPageErrorMessage.suffixErrorMsg,PrefixValue.get(i),ErrorMessageType.PrefixErrorMsg,20)) {
															appLog.info("Error Message is verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.suffixErrorMsg);
														}else {
															appLog.error("Error Message is not verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.suffixErrorMsg);
															sa.assertTrue(false, "Error Message is not verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.suffixErrorMsg);
														}
													}
													if(fp.verifyRenameFolderErrorMessageParentLevel(FolderType.Common, Workspace.FundraisingWorkspace, PageName.BuildStep2Of3,FundsPageErrorMessage.speicalCharErrorMsgFundsPage,specialChar.get(0),ErrorMessageType.SpiecalCharErrorMsg,20)) {
														appLog.info("Error Message is verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsgFundsPage);
													}else {
														appLog.error("Error Message is not verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsgFundsPage);
														sa.assertTrue(false, "Error Message is not verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsgFundsPage);
													}
													
													if(click(driver, fp.getParentRenameFolderCrossIcon(Workspace.FundraisingWorkspace, PageName.FundsPage, 20), "rename parent folder cross icon", action.BOOLEAN)) {
														appLog.info("clicked on rename parent folder pop up cross icon");
													}else {
														appLog.error("Not able to click on rename parent folder pop up cross icon");
														sa.assertTrue(false, "Not able to click on rename parent folder pop up cross icon");
													}
												}else {
													appLog.error("Not able to click on Rename folder Parent folder "+commonFolderName[0]+" so cannot check error message on "+commonFolderName[0]+" Parent folder");
													sa.assertTrue(false, "Not able to click on Rename folder Parent folder Rename Icon  "+commonFolderName[0]+" so cannot check error message on "+commonFolderName[0]+" Parent folder");
												}
											}else {
												appLog.error(commonFolderName[0]+" folder is available in step 2 of 3 pop up so cannot check error messgaes on Parent Rename Common folder");
												sa.assertTrue(false, stdFolderName[0]+" folder is available in step 2 of 3 pop up so cannot check error messgaes on Parent Rename Common folder");
											}
											String[] ss= {commonFolderName[0]+"/"+commonFolderName[1],SHRDFolderName[0]+"/"+SHRDFolderName[1],InternalFolderName[0]+"/"+InternalFolderName[1],stdFolderName[0]+"/"+stdFolderName[1]};
											String[] ss1= {commonFolderName[1],SHRDFolderName[1],InternalFolderName[1],stdFolderName[1]};
											String[] ss2= {commonFolderName[2],SHRDFolderName[2],InternalFolderName[2],stdFolderName[2]};
											for(int i=0; i<ss.length; i++) {
												id=null;
												id=fp.getCreatedFolderId(ss[i], PageName.FundsPage, 20);
												System.err.println("Folder Id is: >>>>>>"+id);
												if(id!=null) {
													if(fp.clickOnRenameFolderButton(id)) {
														ThreadSleep(2000);
														if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.FundsPage, 20), ss2[i], ss1[i]+" sub folder text box", action.BOOLEAN)) {
															if(click(driver, fp.getParentRenameFolderSaveButton(Workspace.FundraisingWorkspace, PageName.BuildStep2Of3, 20), "sub folder save button", action.BOOLEAN)) {
																ThreadSleep(2000);
																if(fp.getParentFolderErrorMsg(Workspace.FundraisingWorkspace, PageName.FundsPage, ErrorMessageType.DuplicateFolder, 30)!=null){
																	String msg = getText(driver, fp.getParentFolderErrorMsg(Workspace.FundraisingWorkspace, PageName.FundsPage, ErrorMessageType.DuplicateFolder, 30), "", action.BOOLEAN);
																	appLog.error("Error Message is displaying : "+msg);
																	if(msg.contains(FundsPageErrorMessage.FolderAlreadyExistErrormsg)) {
																		appLog.info("Error msg is verified successfully.");
																	}else {
																		appLog.error("Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
																		sa.assertTrue(false, "Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
																	}
																} else {
																	appLog.error("Duplicate Folder Error Message Pop Up Is not opening after clicking on save button for standard Folder.");
																	sa.assertTrue(false,"Duplicate Folder Error Message Pop Up Is not opening after clicking on save button for standard Folder.");
																}
																if(click(driver, fp.getFolderCreationErrorMessageOkBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, FolderType.Standard, 10), "Duplicate folder Error message ok button", action.BOOLEAN)){
																	System.err.println("successfulyl clicked on ok button.");
																} else {
																	System.err.println("\n\n\nNot able to click on OK Button.\n\n\n");
																}
															}else {
																appLog.error("Not able to click on sub folder "+ss1[i]+" save button so cannot chcek Error Message "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
																sa.assertTrue(false, "Not able to click on sub folder "+ss1[i]+" save button so cannot chcek Error Message "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
															}
														}else {
															appLog.error("Not able to pass value in sub folder "+ss1[i]+" so cannot check Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
															sa.assertTrue(false, "Not able to pass value in sub folder "+ss1[i]+" so cannot check Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
														}
														
													}else {
														appLog.error("Not able to click on Rename folder sub folder "+ss1[i]+" so cannot check error message on "+ss1[i]+" sub folder");
														sa.assertTrue(false, "Not able to click on Rename folder sub folder "+ss1[i]+" so cannot check error message on "+ss1[i]+" sub folder");
													}
												}else {
													appLog.error(ss1[i]+" folder is available in step 2 of 3 pop up so cannot check error messgaes on sub Common folder");
													sa.assertTrue(false, ss1[i]+" folder is available in step 2 of 3 pop up so cannot check error messgaes on sub Common folder");
												}
												
											}
											for(int i=0; i<2; i++) {
												id=null;
												id=fp.getCreatedFolderId(commonFolderName[0], PageName.FundsPage, 20);
												System.err.println("id>>>>>>"+id);
												if(id!=null) {
													if(fp.clickOnRenameFolderButton(id)) {
														if(i==0) {
															if(fp.getParentRenameFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.FundsPage, 20)!=null) {
																appLog.info("Rename Folder pop up is open");
															}else {
																appLog.error("Rename folder Pop up is not open");
																sa.assertTrue(false, "Rename folder Pop up is not open");
															}
															String prefillValue=null;
															prefillValue= getValueFromElementUsingJavaScript(driver,fp.getParentRenameFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.FundsPage, 20),"");
															if(prefillValue!=null) {
																if(prefillValue.trim().equalsIgnoreCase(trim(commonFolderName[0].split("\\(")[0]))) {
																	appLog.info(commonFolderName[0].split("\\(")[0]+" value is displaying in rename folder pop up");
																}else {
																	appLog.error(commonFolderName[0].split("\\(")[0]+" value is not displaying in rename folder pop up");
																	sa.assertTrue(false, commonFolderName[0].split("\\(")[0]+" value is not displaying in rename folder pop up");
																}
															}else {
																appLog.error("Rename Folder text box is not available so cannot get value from rename folder text box");
																sa.assertTrue(false, "Rename Folder text box is not available so cannot get value from rename folder text box");
															}
															if(fp.getParentRenameFolderCrossIcon(Workspace.FundraisingWorkspace, PageName.FundsPage, 10)!=null) {
																appLog.info("Cross icon is visible in rename folder pop up");
															}else {
																appLog.error("Cross icon is not visible in rename folder pop up");
																sa.assertTrue(false, "Cross icon is not visible in rename folder pop up");
															}
															if(fp.getParentRenameFolderSaveButton(Workspace.FundraisingWorkspace, PageName.BuildStep2Of3, 10)!=null) {
																appLog.info("Save is visible in rename folder pop up");
															}else {
																appLog.error("Save is not visible in rename folder pop up");
																sa.assertTrue(false, "Save is not visible in rename folder pop up");
															}
															if(fp.getParentRenameFolderCancelButton(Workspace.FundraisingWorkspace, PageName.FundsPage, 10)!=null) {
																appLog.info("Cancel button is visible in rename folder pop up");
															}else {
																appLog.error("Cancel button is not visible in rename folder pop up");
																sa.assertTrue(false, "Cancel button is not visible in rename folder pop up");
															}
														}
														ele=null;
														String aa= null;
														if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.FundsPage,20),"","rename parent folder text box", action.BOOLEAN)) {
															if(i==0) {
																 ele=fp.getParentRenameFolderCancelButton(Workspace.FundraisingWorkspace, PageName.FundsPage, 10);
																 aa="Cancel Button";
															}else {
																 ele= fp.getParentRenameFolderCrossIcon(Workspace.FundraisingWorkspace, PageName.FundsPage, 10);
																 aa="Cross Icon";
															}
															if(ele!=null) {
																if(click(driver,ele ,"rename parent folder "+aa+"", action.BOOLEAN)) {
																	appLog.info("clicked on "+aa+"");
																	if(fp.getCreatedFolderId(commonFolderName[0], PageName.FundsPage, 20)!=null) {
																		appLog.info(commonFolderName[0]+" is available in step 2 of 3 pop up after click on "+aa+"");
																	}else {
																		appLog.error("Created folder is not available in step 2 of 3 pop up: "+commonFolderName[0]);
																		sa.assertTrue(false, "Created folder is not available in step 2 of 3 pop up: "+commonFolderName[0]);
																	}
																}else {
																	appLog.error("Not able to click on common rename parent folder "+aa+"so cannot check folder name in step 2 of 3 pop up after click on "+aa+"");
																	sa.assertTrue(false, "Not able to click on common rename parent folder "+aa+" so cannot check folder name in step 2 of 3 pop up after click on "+aa+"");
																}
															}else {
																appLog.error(aa+" is not visible so cannot click on "+aa+" so cannot check folder name in step 2 of 3 pop up after click on "+aa+"");
																sa.assertTrue(false, aa+" is not visible so cannot click on "+aa+" so cannot check folder name in step 2 of 3 pop up after click on "+aa+"");
															}
															
														}else {
															appLog.error("Not able to blank "+commonFolderName[0]+" rename folder text box so cannot check folder name in step 2 of 3 pop up after click on cancel button");
															sa.assertTrue(false, "Not able to blank "+commonFolderName[0]+" rename folder text box so cannot check folder name in step 2 of 3 pop up after click on cancel button");
														}	
														
													}else {
														appLog.error("Not able to click on Rename folder Parent folder "+commonFolderName[0]+" so cannot check error message on "+commonFolderName[0]+" Parent folder");
														sa.assertTrue(false, "Not able to click on Rename folder Parent folder Rename Icon  "+commonFolderName[0]+" so cannot check error message on "+commonFolderName[0]+" Parent folder");
													}
												}else {
													appLog.error(commonFolderName[0]+" folder is available in step 2 of 3 pop up so cannot check error messgaes on Parent Rename Common folder");
													sa.assertTrue(false, stdFolderName[0]+" folder is available in step 2 of 3 pop up so cannot check error messgaes on Parent Rename Common folder");
												}
											}
											String[] folders= {commonFolderName[0],SHRDFolderName[0],InternalFolderName[0],stdFolderName[0]};
											String [] Names= {commonFolderName[0].split("\\(")[0].trim(),SHRDFolderName[0].split("\\(")[0].trim(),InternalFolderName[0].split("\\(")[0].trim(),stdFolderName[0].split("\\(")[0].trim()};
											for(int i=0; i<folders.length; i++) {
												id=null;
												id=fp.getCreatedFolderId(folders[i], PageName.FundsPage, 20);
												System.err.println("id>>>>>>"+id);
												if(id!=null) {
													if(fp.clickOnRenameFolderButton(id)) {
														if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.FundsPage,20),"Updated"+Names[i],folders[i]+" parent rename folder text box", action.BOOLEAN)) {
															if(click(driver,fp.getParentRenameFolderSaveButton(Workspace.FundraisingWorkspace, PageName.BuildStep2Of3, 20) ,"rename parent folder save button", action.BOOLEAN)) {
																appLog.info("clicked on parent rename folder save button");
																ThreadSleep(2000);
																if(fp.getCreatedFolderId("Updated"+folders[i], PageName.FundsPage, 20)!=null) {
																	appLog.info("Updated"+folders[i]+" is available in step 2 of 3 pop up");
																}else {
																	appLog.error("Updated"+folders[i]+" is not available in step 2 of 3 pop up");
																	sa.assertTrue(false, "Updated"+folders[i]+" is not available in step 2 of 3 pop up");
																}
															}else {
																appLog.error("Not able to click on folder "+folders[i]+" save button so cannot Update Folder Name");
																sa.assertTrue(false, "Not able to click on folder "+folders[i]+" save button so cannot Update Folder Name");
															}
														}else {
															appLog.error("Not able to pass value in "+folders[i]+" rename folder text box so cannot Update folder Name");
															sa.assertTrue(false, "Not able to pass value in "+folders[i]+" rename folder text box so cannot Update folder Name");
														}
														
													}else {
														appLog.error("Not able to click on "+folders[i]+" rename icon so cannot update folder Name");
														sa.assertTrue(false, "Not able to click on "+folders[i]+" rename icon so cannot update folder Name");
													}
												}else {
													appLog.error(folders[i]+" folder is not available in step 2 of 3 pop up so cannot Update folder Name");
													sa.assertTrue(false, folders[i]+" folder is not available in step 2 of 3 pop up so cannot Update folder Name");
												}
												
											}
											String updateCommonFolder="Updated"+commonFolderName[0].trim();
											String updateSharedFolder="Updated"+SHRDFolderName[0].trim();
											String updateInternalFolder="Updated"+InternalFolderName[0].trim();
											String updateSTDFolder="Updated"+stdFolderName[0].trim();
											String[] UpdateparentFolderName= {updateCommonFolder,updateInternalFolder,updateSharedFolder,updateSTDFolder};
											String[] subfolders= {commonFolderName[1],InternalFolderName[1],SHRDFolderName[1],stdFolderName[1]};
											String[] FoldercolumnName= {"CommonPath","InternalPath","SharedPath","Standardpath"};
											List<String> lst = new ArrayList<String>();
											for(int i=0 ;i<4; i++) {
												if(UpdateparentFolderName[i].isEmpty()|| UpdateparentFolderName[i].equalsIgnoreCase(FoldercolumnName[i])) {
													appLog.error("Updated Folder Name is not present in File Path Cloumn "+FoldercolumnName[i]);
													sa.assertTrue(false, "Updated Folder Name is not present in File Path Cloumn "+FoldercolumnName[i]);
												}else {
													appLog.info("Updated Folder Path is: "+UpdateparentFolderName[i]+"/"+subfolders[i]);
													lst.add(UpdateparentFolderName[i]+"/"+subfolders[i]);
												}
											}
											if(!lst.isEmpty()) {
												for(int i=0; i<lst.size(); i++) {
													id=null;
													id=fp.getCreatedFolderId(lst.get(i), PageName.FundsPage, 20);
													System.err.println("id>>>>>>"+id);
													if(id!=null) {
														if(fp.clickOnRenameFolderButton(id)) {
															if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.FundsPage,20),"Updated"+lst.get(i).split("/")[1],lst.get(i).split("/")[0]+" parent rename folder text box", action.BOOLEAN)) {
																if(click(driver,fp.getParentRenameFolderSaveButton(Workspace.FundraisingWorkspace, PageName.BuildStep2Of3, 20) ,lst.get(i).split("/")[0]+"rename sub folder save button", action.BOOLEAN)) {
																	appLog.info("clicked on parent rename folder save button");
																	ThreadSleep(2000);
																	if(fp.getCreatedFolderId(lst.get(i).split("/")[0]+"/Updated"+lst.get(i).split("/")[1], PageName.FundsPage, 20)!=null) {
																		appLog.info(lst.get(i).split("/")[0]+"/Updated"+lst.get(i).split("/")[1]+" is available in step 2 of 3 pop up");
																		System.err.println("List >>>>>"+lst.get(i).trim()+" and size"+lst.size());
																		System.err.println("Updated Common >>"+updateCommonFolder);
																		System.err.println("Updated Internal >>"+updateInternalFolder);
																		System.err.println("Updated shared >>"+updateSharedFolder);
																		System.err.println("Updated STD >>"+updateSTDFolder);
																	}else {
																		appLog.error(lst.get(i).split("/")[0]+"/Updated"+lst.get(i).split("/")[1]+" is not available in step 2 of 3 pop up");
																		sa.assertTrue(false, lst.get(i).split("/")[0]+"/Updated"+lst.get(i).split("/")[1]+" is not available in step 2 of 3 pop up");
																	}
																}else {
																	appLog.error("Not able to click on folder "+lst.get(i)+" save button so cannot Update Folder Name");
																	sa.assertTrue(false, "Not able to click on folder "+lst.get(i)+" save button so cannot Update Folder Name");
																}
															}else {
																appLog.error("Not able to pass value in "+lst.get(i)+" rename folder text box so cannot Update folder Name");
																sa.assertTrue(false, "Not able to pass value in "+lst.get(i)+" rename folder text box so cannot Update folder Name");
															}
															
														}else {
															appLog.error("Not able to click on "+lst.get(i)+" rename icon so cannot update folder Name");
															sa.assertTrue(false, "Not able to click on "+lst.get(i)+" rename icon so cannot update folder Name");
														}
													}else {
														appLog.error(lst.get(i)+" folder is not available in step 2 of 3 pop up so cannot Update folder Name");
														sa.assertTrue(false, lst.get(i)+" folder is not available in step 2 of 3 pop up so cannot Update folder Name");
													}
													
												}
											}else {
												appLog.error("Updated Folder Names is not available in the filepath excel sheet so cannot update Child folder Names");
												sa.assertTrue(false, "Updated Folder Names is not available in the filepath excel sheet so cannot update Child folder Names");
											}
									
										} else {
											appLog.error("Cannot click on import button so cannot import folder template.");
											sa.assertTrue(false,"Cannot click on import button so cannot import folder template.");
										}
									} else {
										appLog.error(folderTemplateName+" template is not available to import, So canot import folder template.");
										sa.assertTrue(false,folderTemplateName+" template is not available to import, So canot import folder template.");
									}
								} else {
									appLog.error("Import folder template button cannot be clicked, So cannnot import folder template.");
									sa.assertTrue(false,"Import folder template button cannot be clicked, So cannnot import folder template.");
								}
								
							} else {
								appLog.error("Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify folder edit functionality.");
								sa.assertTrue(false,"Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify folder edit functionality.");
								fp.recover(Workspace.FundraisingWorkspace, 30);
							}
						} else {
							appLog.error("Next button of step 1 of 3 cannot be click, So cannot verify folder edit functionality.");
							sa.assertTrue(false,"Next button of step 1 of 3 cannot be click, So cannot verify folder edit functionality.");
							fp.recover(Workspace.FundraisingWorkspace, 30);
						}
					} else {
						appLog.error("One of the mandatory field is not filled, So cannot verify folder edit functionality.");
						sa.assertTrue(false,"One of the mandatory field is not filled, So cannot verify folder edit functionality.");
						fp.recover(Workspace.FundraisingWorkspace, 30);
					}
				} else {
					appLog.error("Build Fundraising workspace button cannot be clicked, So cannot verify folder edit functionality.");
					sa.assertTrue(false,"Build Fundraising workspace button cannot be clicked, So cannot verify folder edit functionality.");
					fp.recover(Workspace.FundraisingWorkspace, 30);
				}
			} else {
				appLog.error(M8F1+" fund is not available, so cannot continue with the tc.");
				sa.assertTrue(false,M8F1+" fund is not available, so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot continue with the tc.");
		}
		sa.assertAll();
	}
	

	@Test
	public void M8tc023_VerifyEditingAFolder(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		boolean flag = true;
		String dependsOnTC = "M8tc011_VerifyEditingAFolder";
		String STDFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependsOnTC,excelLabel.StandardPath);
		String[] stdFolderName=STDFolderName.split(",");
		String[] commonFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependsOnTC,excelLabel.CommonPath).split(",");
		String[] InternalFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependsOnTC,excelLabel.InternalPath).split(",");
		String[] SHRDFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependsOnTC,excelLabel.SharedPath).split(",");
		List<String> PrefixValue = getValueBasedOnLabel(filePath, "SpecialChar",excelLabel.INVALID_FOLDER_NAME, 1);
		List<String> specialChar = getValueBasedOnLabel(filePath, "SpecialChar",excelLabel.FOLDER_NAME, 1);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F4)){
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Funcraising wokspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.InvestorWorkspace, 30), "Buld Investor workspace button", action.BOOLEAN)){
					if(sendKeys(driver, fp.getFundVintageYearField(Workspace.InvestorWorkspace, 30), "2013", "Vintage year", action.BOOLEAN)){
						appLog.info("Successfully passed value to vintage year text box.");
					} else {
						appLog.error("Cannot pass value to vintage year text box.");
						sa.assertTrue(false,"Cannot pass value to vintage year text box.");
					}
					
					if(sendKeys(driver, fp.getFundSizeField(Workspace.InvestorWorkspace, 30), "5000", "Fund Size", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund size text box.");
					} else {
						appLog.error("Cannot pass value to Fund size text box.");
						sa.assertTrue(false,"Cannot pass value to Fund size text box.");
					}
					
					if(sendKeys(driver, fp.getFundContactField(Workspace.InvestorWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund contact text box.");
					} else {
						appLog.error("Cannot pass value to Fund contact text box.");
						sa.assertTrue(false,"Cannot pass value to Fund contact text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundEmailField(Workspace.InvestorWorkspace, 30), "abc@abc.com", "Fund Email", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Email text box.");
					} else {
						appLog.error("Cannot pass value to Fund Email text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Email text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundPhoneField(Workspace.InvestorWorkspace, 30), "987654321", "Fund Phone", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Phone text box.");
					} else {
						appLog.error("Cannot pass value to Fund Phone text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Phone text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundDescriptionField(Workspace.InvestorWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Description text box.");
					} else {
						appLog.error("Cannot pass value to Fund Description text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Description text box.");
					}
					if(flag){
						if(click(driver, fp.getNext1Of3Button(Workspace.InvestorWorkspace, 30), "Next button of step 1 of 3", action.BOOLEAN)){
							if(fp.getStep2Of3Header(Workspace.InvestorWorkspace, 30)!=null){
								if(fp.getImportFolderTemplateButton(Workspace.InvestorWorkspace, EnableDisable.Enable, 30)!=null){
									appLog.info("Import Folder Template Button is enabled and verfied.");
								} else {
									appLog.error("Import Folder template button is not enabled and is not verified.");
									sa.assertTrue(false,"Import Folder template button is not enabled and is not verified.");
								}
								if(click(driver, fp.getImportFolderTemplateButton(Workspace.InvestorWorkspace, 30), "Import folder template button", action.BOOLEAN)){
									WebElement ele = FindElement(driver, "//span[@title='"+ folderTemplateName +"']/../preceding-sibling::span[1]//input", folderTemplateName+" template radio button", action.BOOLEAN, 30);
									if(click(driver, ele, folderTemplateName+" folder template", action.BOOLEAN)){
										if(click(driver, fp.getSelectFolderTemplateImportButton(Workspace.InvestorWorkspace, EnableDisable.Enable, 30), "Imprt button", action.BOOLEAN)){

											//String[] radioBtn= {STDFolderName,commonFolderName,SHRDFolderName,InternalFolderName};
											String id=null;
											id=fp.getCreatedFolderId(commonFolderName[0], PageName.FundsPage, 20);
											System.err.println("id>>>>>>"+id);
											if(id!=null) {
												if(fp.clickOnRenameFolderButton(id)) {
													if(fp.verifyRenameFolderErrorMessageParentLevel(FolderType.Common, Workspace.InvestorWorkspace, PageName.BuildStep2Of3,FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg,"",ErrorMessageType.BlankErrorMsg,20)) {
														appLog.info("Error Message is verified for Parnet Level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
													}else {
														appLog.error("Error Message is not verified for parent Level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
														sa.assertTrue(false, "Error Message is not verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
													}
													for(int i=0; i<PrefixValue.size(); i++) {
														if(fp.verifyRenameFolderErrorMessageParentLevel(FolderType.Common, Workspace.InvestorWorkspace, PageName.BuildStep2Of3,FundsPageErrorMessage.suffixErrorMsg,PrefixValue.get(i),ErrorMessageType.PrefixErrorMsg,20)) {
															appLog.info("Error Message is verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.suffixErrorMsg);
														}else {
															appLog.error("Error Message is not verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.suffixErrorMsg);
															sa.assertTrue(false, "Error Message is not verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.suffixErrorMsg);
														}
													}
													if(fp.verifyRenameFolderErrorMessageParentLevel(FolderType.Common, Workspace.InvestorWorkspace, PageName.BuildStep2Of3,FundsPageErrorMessage.speicalCharErrorMsgFundsPage,specialChar.get(0),ErrorMessageType.SpiecalCharErrorMsg,20)) {
														appLog.info("Error Message is verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsgFundsPage);
													}else {
														appLog.error("Error Message is not verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsgFundsPage);
														sa.assertTrue(false, "Error Message is not verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsgFundsPage);
													}
													
													if(click(driver, fp.getParentRenameFolderCrossIcon(Workspace.InvestorWorkspace, PageName.FundsPage, 20), "rename parent folder cross icon", action.BOOLEAN)) {
														appLog.info("clicked on rename parent folder pop up cross icon");
													}else {
														appLog.error("Not able to click on rename parent folder pop up cross icon");
														sa.assertTrue(false, "Not able to click on rename parent folder pop up cross icon");
													}
												}else {
													appLog.error("Not able to click on Rename folder Parent folder "+commonFolderName[0]+" so cannot check error message on "+commonFolderName[0]+" Parent folder");
													sa.assertTrue(false, "Not able to click on Rename folder Parent folder Rename Icon  "+commonFolderName[0]+" so cannot check error message on "+commonFolderName[0]+" Parent folder");
												}
											}else {
												appLog.error(commonFolderName[0]+" folder is available in step 2 of 3 pop up so cannot check error messgaes on Parent Rename Common folder");
												sa.assertTrue(false, stdFolderName[0]+" folder is available in step 2 of 3 pop up so cannot check error messgaes on Parent Rename Common folder");
											}
											String[] ss= {commonFolderName[0]+"/"+commonFolderName[1],SHRDFolderName[0]+"/"+SHRDFolderName[1],InternalFolderName[0]+"/"+InternalFolderName[1],stdFolderName[0]+"/"+stdFolderName[1]};
											String[] ss1= {commonFolderName[1],SHRDFolderName[1],InternalFolderName[1],stdFolderName[1]};
											String[] ss2= {commonFolderName[2],SHRDFolderName[2],InternalFolderName[2],stdFolderName[2]};
											for(int i=0; i<ss.length; i++) {
												id=null;
												id=fp.getCreatedFolderId(ss[i], PageName.FundsPage, 20);
												System.err.println("Folder Id is: >>>>>>"+id);
												if(id!=null) {
													if(fp.clickOnRenameFolderButton(id)) {
														ThreadSleep(2000);
														if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.InvestorWorkspace, PageName.FundsPage, 20), ss2[i], ss1[i]+" sub folder text box", action.BOOLEAN)) {
															if(click(driver, fp.getParentRenameFolderSaveButton(Workspace.InvestorWorkspace, PageName.BuildStep2Of3, 20), "sub folder save button", action.BOOLEAN)) {
																ThreadSleep(2000);
																if(fp.getParentFolderErrorMsg(Workspace.InvestorWorkspace, PageName.FundsPage, ErrorMessageType.DuplicateFolder, 30)!=null){
																	String msg = getText(driver, fp.getParentFolderErrorMsg(Workspace.InvestorWorkspace, PageName.FundsPage, ErrorMessageType.DuplicateFolder, 30), "", action.BOOLEAN);
																	appLog.error("Error Message is displaying : "+msg);
																	if(msg.contains(FundsPageErrorMessage.FolderAlreadyExistErrormsg)) {
																		appLog.info("Error msg is verified successfully.");
																	}else {
																		appLog.error("Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
																		sa.assertTrue(false, "Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
																	}
																} else {
																	appLog.error("Duplicate Folder Error Message Pop Up Is not opening after clicking on save button for standard Folder.");
																	sa.assertTrue(false,"Duplicate Folder Error Message Pop Up Is not opening after clicking on save button for standard Folder.");
																}
																if(click(driver, fp.getFolderCreationErrorMessageOkBtn(Workspace.InvestorWorkspace, PageName.FundsPage, FolderType.Standard, 10), "Duplicate folder Error message ok button", action.BOOLEAN)){
																	System.err.println("successfulyl clicked on ok button.");
																} else {
																	System.err.println("\n\n\nNot able to click on OK Button.\n\n\n");
																}
															}else {
																appLog.error("Not able to click on sub folder "+ss1[i]+" save button so cannot chcek Error Message "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
																sa.assertTrue(false, "Not able to click on sub folder "+ss1[i]+" save button so cannot chcek Error Message "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
															}
														}else {
															appLog.error("Not able to pass value in sub folder "+ss1[i]+" so cannot check Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
															sa.assertTrue(false, "Not able to pass value in sub folder "+ss1[i]+" so cannot check Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
														}
														
													}else {
														appLog.error("Not able to click on Rename folder sub folder "+ss1[i]+" so cannot check error message on "+ss1[i]+" sub folder");
														sa.assertTrue(false, "Not able to click on Rename folder sub folder "+ss1[i]+" so cannot check error message on "+ss1[i]+" sub folder");
													}
												}else {
													appLog.error(ss1[i]+" folder is available in step 2 of 3 pop up so cannot check error messgaes on sub Common folder");
													sa.assertTrue(false, ss1[i]+" folder is available in step 2 of 3 pop up so cannot check error messgaes on sub Common folder");
												}
												
											}
											for(int i=0; i<2; i++) {
												id=null;
												id=fp.getCreatedFolderId(commonFolderName[0], PageName.FundsPage, 20);
												System.err.println("id>>>>>>"+id);
												if(id!=null) {
													if(fp.clickOnRenameFolderButton(id)) {
														if(i==0) {
															if(fp.getParentRenameFolderNameTextBox(Workspace.InvestorWorkspace, PageName.FundsPage, 20)!=null) {
																appLog.info("Rename Folder pop up is open");
															}else {
																appLog.error("Rename folder Pop up is not open");
																sa.assertTrue(false, "Rename folder Pop up is not open");
															}
															String prefillValue=null;
															prefillValue= getValueFromElementUsingJavaScript(driver,fp.getParentRenameFolderNameTextBox(Workspace.InvestorWorkspace, PageName.FundsPage, 20),"");
															if(prefillValue!=null) {
																if(prefillValue.trim().equalsIgnoreCase(trim(commonFolderName[0].split("\\(")[0]))) {
																	appLog.info(commonFolderName[0].split("\\(")[0]+" value is displaying in rename folder pop up");
																}else {
																	appLog.error(commonFolderName[0].split("\\(")[0]+" value is not displaying in rename folder pop up");
																	sa.assertTrue(false, commonFolderName[0].split("\\(")[0]+" value is not displaying in rename folder pop up");
																}
															}else {
																appLog.error("Rename Folder text box is not available so cannot get value from rename folder text box");
																sa.assertTrue(false, "Rename Folder text box is not available so cannot get value from rename folder text box");
															}
															if(fp.getParentRenameFolderCrossIcon(Workspace.InvestorWorkspace, PageName.FundsPage, 10)!=null) {
																appLog.info("Cross icon is visible in rename folder pop up");
															}else {
																appLog.error("Cross icon is not visible in rename folder pop up");
																sa.assertTrue(false, "Cross icon is not visible in rename folder pop up");
															}
															if(fp.getParentRenameFolderSaveButton(Workspace.InvestorWorkspace, PageName.BuildStep2Of3, 10)!=null) {
																appLog.info("Save is visible in rename folder pop up");
															}else {
																appLog.error("Save is not visible in rename folder pop up");
																sa.assertTrue(false, "Save is not visible in rename folder pop up");
															}
															if(fp.getParentRenameFolderCancelButton(Workspace.InvestorWorkspace, PageName.FundsPage, 10)!=null) {
																appLog.info("Cancel button is visible in rename folder pop up");
															}else {
																appLog.error("Cancel button is not visible in rename folder pop up");
																sa.assertTrue(false, "Cancel button is not visible in rename folder pop up");
															}
														}
														ele=null;
														String aa= null;
														if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.InvestorWorkspace, PageName.FundsPage,20),"","rename parent folder text box", action.BOOLEAN)) {
															if(i==0) {
																 ele=fp.getParentRenameFolderCancelButton(Workspace.InvestorWorkspace, PageName.FundsPage, 10);
																 aa="Cancel Button";
															}else {
																 ele= fp.getParentRenameFolderCrossIcon(Workspace.InvestorWorkspace, PageName.FundsPage, 10);
																 aa="Cross Icon";
															}
															if(ele!=null) {
																if(click(driver,ele ,"rename parent folder "+aa+"", action.BOOLEAN)) {
																	appLog.info("clicked on "+aa+"");
																	if(fp.getCreatedFolderId(commonFolderName[0], PageName.FundsPage, 20)!=null) {
																		appLog.info(commonFolderName[0]+" is available in step 2 of 3 pop up after click on "+aa+"");
																	}else {
																		appLog.error("Created folder is not available in step 2 of 3 pop up: "+commonFolderName[0]);
																		sa.assertTrue(false, "Created folder is not available in step 2 of 3 pop up: "+commonFolderName[0]);
																	}
																}else {
																	appLog.error("Not able to click on common rename parent folder "+aa+"so cannot check folder name in step 2 of 3 pop up after click on "+aa+"");
																	sa.assertTrue(false, "Not able to click on common rename parent folder "+aa+" so cannot check folder name in step 2 of 3 pop up after click on "+aa+"");
																}
															}else {
																appLog.error(aa+" is not visible so cannot click on "+aa+" so cannot check folder name in step 2 of 3 pop up after click on "+aa+"");
																sa.assertTrue(false, aa+" is not visible so cannot click on "+aa+" so cannot check folder name in step 2 of 3 pop up after click on "+aa+"");
															}
															
														}else {
															appLog.error("Not able to blank "+commonFolderName[0]+" rename folder text box so cannot check folder name in step 2 of 3 pop up after click on cancel button");
															sa.assertTrue(false, "Not able to blank "+commonFolderName[0]+" rename folder text box so cannot check folder name in step 2 of 3 pop up after click on cancel button");
														}	
														
													}else {
														appLog.error("Not able to click on Rename folder Parent folder "+commonFolderName[0]+" so cannot check error message on "+commonFolderName[0]+" Parent folder");
														sa.assertTrue(false, "Not able to click on Rename folder Parent folder Rename Icon  "+commonFolderName[0]+" so cannot check error message on "+commonFolderName[0]+" Parent folder");
													}
												}else {
													appLog.error(commonFolderName[0]+" folder is available in step 2 of 3 pop up so cannot check error messgaes on Parent Rename Common folder");
													sa.assertTrue(false, stdFolderName[0]+" folder is available in step 2 of 3 pop up so cannot check error messgaes on Parent Rename Common folder");
												}
											}
											String[] folders= {commonFolderName[0],SHRDFolderName[0],InternalFolderName[0],stdFolderName[0]};
											String [] Names= {commonFolderName[0].split("\\(")[0].trim(),SHRDFolderName[0].split("\\(")[0].trim(),InternalFolderName[0].split("\\(")[0].trim(),stdFolderName[0].split("\\(")[0].trim()};
											for(int i=0; i<folders.length; i++) {
												id=null;
												id=fp.getCreatedFolderId(folders[i], PageName.FundsPage, 20);
												System.err.println("id>>>>>>"+id);
												if(id!=null) {
													if(fp.clickOnRenameFolderButton(id)) {
														if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.InvestorWorkspace, PageName.FundsPage,20),"Updated"+Names[i],folders[i]+" parent rename folder text box", action.BOOLEAN)) {
															if(click(driver,fp.getParentRenameFolderSaveButton(Workspace.InvestorWorkspace, PageName.BuildStep2Of3, 20) ,"rename parent folder save button", action.BOOLEAN)) {
																appLog.info("clicked on parent rename folder save button");
																ThreadSleep(2000);
																if(fp.getCreatedFolderId("Updated"+folders[i], PageName.FundsPage, 20)!=null) {
																	appLog.info("Updated"+folders[i]+" is available in step 2 of 3 pop up");
																}else {
																	appLog.error("Updated"+folders[i]+" is not available in step 2 of 3 pop up");
																	sa.assertTrue(false, "Updated"+folders[i]+" is not available in step 2 of 3 pop up");
																}
															}else {
																appLog.error("Not able to click on folder "+folders[i]+" save button so cannot Update Folder Name");
																sa.assertTrue(false, "Not able to click on folder "+folders[i]+" save button so cannot Update Folder Name");
															}
														}else {
															appLog.error("Not able to pass value in "+folders[i]+" rename folder text box so cannot Update folder Name");
															sa.assertTrue(false, "Not able to pass value in "+folders[i]+" rename folder text box so cannot Update folder Name");
														}
														
													}else {
														appLog.error("Not able to click on "+folders[i]+" rename icon so cannot update folder Name");
														sa.assertTrue(false, "Not able to click on "+folders[i]+" rename icon so cannot update folder Name");
													}
												}else {
													appLog.error(folders[i]+" folder is not available in step 2 of 3 pop up so cannot Update folder Name");
													sa.assertTrue(false, folders[i]+" folder is not available in step 2 of 3 pop up so cannot Update folder Name");
												}
												
											}
											String updateCommonFolder="Updated"+commonFolderName[0].trim();
											String updateSharedFolder="Updated"+SHRDFolderName[0].trim();
											String updateInternalFolder="Updated"+InternalFolderName[0].trim();
											String updateSTDFolder="Updated"+stdFolderName[0].trim();
											String[] UpdateparentFolderName= {updateCommonFolder,updateInternalFolder,updateSharedFolder,updateSTDFolder};
											String[] subfolders= {commonFolderName[1],InternalFolderName[1],SHRDFolderName[1],stdFolderName[1]};
											String[] FoldercolumnName= {"CommonPath","InternalPath","SharedPath","Standardpath"};
											List<String> lst = new ArrayList<String>();
											for(int i=0 ;i<4; i++) {
												if(UpdateparentFolderName[i].isEmpty()|| UpdateparentFolderName[i].equalsIgnoreCase(FoldercolumnName[i])) {
													appLog.error("Updated Folder Name is not present in File Path Cloumn "+FoldercolumnName[i]);
													sa.assertTrue(false, "Updated Folder Name is not present in File Path Cloumn "+FoldercolumnName[i]);
												}else {
													appLog.info("Updated Folder Path is: "+UpdateparentFolderName[i]+"/"+subfolders[i]);
													lst.add(UpdateparentFolderName[i]+"/"+subfolders[i]);
												}
											}
											if(!lst.isEmpty()) {
												for(int i=0; i<lst.size(); i++) {
													id=null;
													id=fp.getCreatedFolderId(lst.get(i), PageName.FundsPage, 20);
													System.err.println("id>>>>>>"+id);
													if(id!=null) {
														if(fp.clickOnRenameFolderButton(id)) {
															if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.InvestorWorkspace, PageName.FundsPage,20),"Updated"+lst.get(i).split("/")[1],lst.get(i).split("/")[0]+" parent rename folder text box", action.BOOLEAN)) {
																if(click(driver,fp.getParentRenameFolderSaveButton(Workspace.InvestorWorkspace, PageName.BuildStep2Of3, 20) ,lst.get(i).split("/")[0]+"rename sub folder save button", action.BOOLEAN)) {
																	appLog.info("clicked on parent rename folder save button");
																	ThreadSleep(2000);
																	if(fp.getCreatedFolderId(lst.get(i).split("/")[0]+"/Updated"+lst.get(i).split("/")[1], PageName.FundsPage, 20)!=null) {
																		appLog.info(lst.get(i).split("/")[0]+"/Updated"+lst.get(i).split("/")[1]+" is available in step 2 of 3 pop up");
																		System.err.println("List >>>>>"+lst.get(i).trim()+" and size"+lst.size());
																		System.err.println("Updated Common >>"+updateCommonFolder);
																		System.err.println("Updated Internal >>"+updateInternalFolder);
																		System.err.println("Updated shared >>"+updateSharedFolder);
																		System.err.println("Updated STD >>"+updateSTDFolder);
																		
																	}else {
																		appLog.error(lst.get(i).split("/")[0]+"/Updated"+lst.get(i).split("/")[1]+" is not available in step 2 of 3 pop up");
																		sa.assertTrue(false, lst.get(i).split("/")[0]+"/Updated"+lst.get(i).split("/")[1]+" is not available in step 2 of 3 pop up");
																	}
																}else {
																	appLog.error("Not able to click on folder "+lst.get(i)+" save button so cannot Update Folder Name");
																	sa.assertTrue(false, "Not able to click on folder "+lst.get(i)+" save button so cannot Update Folder Name");
																}
															}else {
																appLog.error("Not able to pass value in "+lst.get(i)+" rename folder text box so cannot Update folder Name");
																sa.assertTrue(false, "Not able to pass value in "+lst.get(i)+" rename folder text box so cannot Update folder Name");
															}
															
														}else {
															appLog.error("Not able to click on "+lst.get(i)+" rename icon so cannot update folder Name");
															sa.assertTrue(false, "Not able to click on "+lst.get(i)+" rename icon so cannot update folder Name");
														}
													}else {
														appLog.error(lst.get(i)+" folder is not available in step 2 of 3 pop up so cannot Update folder Name");
														sa.assertTrue(false, lst.get(i)+" folder is not available in step 2 of 3 pop up so cannot Update folder Name");
													}
													
												}
											}else {
												appLog.error("Updated Folder Names is not available in the filepath excel sheet so cannot update Child folder Names");
												sa.assertTrue(false, "Updated Folder Names is not available in the filepath excel sheet so cannot update Child folder Names");
											}
									
										} else {
											appLog.error("Cannot click on import button so cannot import folder template.");
											sa.assertTrue(false,"Cannot click on import button so cannot import folder template.");
										}
									} else {
										appLog.error(folderTemplateName+" template is not available to import, So canot import folder template.");
										sa.assertTrue(false,folderTemplateName+" template is not available to import, So canot import folder template.");
									}
								} else {
									appLog.error("Import folder template button cannot be clicked, So cannnot import folder template.");
									sa.assertTrue(false,"Import folder template button cannot be clicked, So cannnot import folder template.");
								}
								
							} else {
								appLog.error("Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify folder edit functionality.");
								sa.assertTrue(false,"Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify folder edit functionality.");
								fp.recover(Workspace.InvestorWorkspace, 30);
							}
						} else {
							appLog.error("Next button of step 1 of 3 cannot be click, So cannot verify folder edit functionality.");
							sa.assertTrue(false,"Next button of step 1 of 3 cannot be click, So cannot verify folder edit functionality.");
							fp.recover(Workspace.InvestorWorkspace, 30);
						}
					} else {
						appLog.error("One of the mandatory field is not filled, So cannot verify folder edit functionality.");
						sa.assertTrue(false,"One of the mandatory field is not filled, So cannot verify folder edit functionality.");
						fp.recover(Workspace.InvestorWorkspace, 30);
					}
				} else {
					appLog.error("Build Investor workspace button cannot be clicked, So cannot verify folder edit functionality.");
					sa.assertTrue(false,"Build Investor workspace button cannot be clicked, So cannot verify folder edit functionality.");
					fp.recover(Workspace.InvestorWorkspace, 30);
				}
			} else {
				appLog.error(M8F4+" fund is not available, so cannot continue with the tc.");
				sa.assertTrue(false,M8F4+" fund is not available, so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot continue with the tc.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	
	@Test
	public void M8tc012_VerifyDeleteAFolder(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		boolean flag = true;
		String dependsOnTC = currentlyExecutingTC;
		String commonFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependsOnTC,excelLabel.CommonPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F1)){
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Funcraising wokspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 30), "Buld Fundraising workspace button", action.BOOLEAN)){
					if(sendKeys(driver, fp.getFundVintageYearField(Workspace.FundraisingWorkspace, 30), "2013", "Vintage year", action.BOOLEAN)){
						appLog.info("Successfully passed value to vintage year text box.");
					} else {
						appLog.error("Cannot pass value to vintage year text box.");
						sa.assertTrue(false,"Cannot pass value to vintage year text box.");
					}
					
					if(sendKeys(driver, fp.getFundSizeField(Workspace.FundraisingWorkspace, 30), "5000", "Fund Size", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund size text box.");
					} else {
						appLog.error("Cannot pass value to Fund size text box.");
						sa.assertTrue(false,"Cannot pass value to Fund size text box.");
					}
					
					if(sendKeys(driver, fp.getFundContactField(Workspace.FundraisingWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund contact text box.");
					} else {
						appLog.error("Cannot pass value to Fund contact text box.");
						sa.assertTrue(false,"Cannot pass value to Fund contact text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundEmailField(Workspace.FundraisingWorkspace, 30), "abc@abc.com", "Fund Email", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Email text box.");
					} else {
						appLog.error("Cannot pass value to Fund Email text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Email text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundPhoneField(Workspace.FundraisingWorkspace, 30), "987654321", "Fund Phone", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Phone text box.");
					} else {
						appLog.error("Cannot pass value to Fund Phone text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Phone text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundDescriptionField(Workspace.FundraisingWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Description text box.");
					} else {
						appLog.error("Cannot pass value to Fund Description text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Description text box.");
					}
					if(flag){
						if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 30), "Next button of step 1 of 3", action.BOOLEAN)){
							if(fp.getStep2Of3Header(Workspace.FundraisingWorkspace, 30)!=null){
								if(fp.getImportFolderTemplateButton(Workspace.FundraisingWorkspace, EnableDisable.Enable, 30)!=null){
									appLog.info("Import Folder Template Button is enabled and verfied.");
								} else {
									appLog.error("Import Folder template button is not enabled and is not verified.");
									sa.assertTrue(false,"Import Folder template button is not enabled and is not verified.");
								}
								if(click(driver, fp.getImportFolderTemplateButton(Workspace.FundraisingWorkspace, 30), "Import folder template button", action.BOOLEAN)){
									WebElement ele = FindElement(driver, "//span[@title='"+ folderTemplateName +"']/../preceding-sibling::span[1]//input", folderTemplateName+" template radio button", action.BOOLEAN, 30);
									if(click(driver, ele, folderTemplateName+" folder template", action.BOOLEAN)){
										if(click(driver, fp.getSelectFolderTemplateImportButton(Workspace.FundraisingWorkspace, EnableDisable.Enable, 30), "Imprt button", action.BOOLEAN)){
											System.err.println(commonFolderName);
											if(!commonFolderName.isEmpty() && !commonFolderName.equalsIgnoreCase("Commonpath")) {
												String[] SplitedcommonFolderName=commonFolderName.split("/");
												for(int i=0; i<3; i++) {
													String id=null;
													id=fp.getCreatedFolderId(commonFolderName, PageName.FundsPage, 20);
													System.err.println("id>>>>>>"+id);
													if(id!=null) {
														if(fp.clickOnDeleteFolderButton(id)) {
															if(i==0) {
															if(fp.getFolderDeleteErrorMsg1(Workspace.FundraisingWorkspace, PageName.FundsPage, 20)!=null) {
																if(fp.getFolderDeleteErrorMsg1(Workspace.FundraisingWorkspace,PageName.FundsPage, 10).getText().trim().contains(FundsPageErrorMessage.Step2Of3ConfirmDeletionErrorMessage)) {
																	appLog.info(FundsPageErrorMessage.Step2Of3ConfirmDeletionErrorMessage+" Folder delete message is verified. ");
																}else {
																	appLog.error(FundsPageErrorMessage.Step2Of3ConfirmDeletionErrorMessage+" folder delete message is not verified");
																	sa.assertTrue(false, FundsPageErrorMessage.Step2Of3ConfirmDeletionErrorMessage+" folder delete message is not verified");
																}
															}else {
																appLog.error("Folder delete error message is not visible");
																sa.assertTrue(false, "Folder delete error message is not visible");
															}
															if(fp.getFolderDeleteYesBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, 20)!=null) {
																appLog.info("Yes button is displaying on folder delete pop up");
															}else {
																appLog.error("Yes button is displaying on folder delete pop up");
																sa.assertTrue(false, "Yes button is displaying on folder delete pop up");
															}
															if(click(driver, fp.getFolderDeleteNoBtn(Workspace.FundraisingWorkspace,PageName.FundsPage, 10), "folder delete no Button", action.BOOLEAN)) {
																appLog.info("No button is displaying on folder delete pop up");
																appLog.info("clicked on folder delete No Button");
															}else {
																appLog.error("No button is not displaying on folder delete pop up so cannot click on No button");
																sa.assertTrue(false, "No button is not displaying on folder delete pop up so cannot click on No button");
															}
															}
															if(i==1) {
																if(click(driver, fp.getFolderDeleteCrossIcon(Workspace.FundraisingWorkspace,PageName.FundsPage, 10), "folder delete cross icon", action.SCROLLANDBOOLEAN)) {
																	ThreadSleep(1500);
																	appLog.info("Cross Icon is displaying on folder delete pop up");
																	appLog.info("clicked on folder delete cross icon");
																	if(!click(driver, fp.getFolderDeleteCrossIcon(Workspace.FundraisingWorkspace,PageName.FundsPage, 1), "folder delete cross icon", action.SCROLLANDBOOLEAN)) {
																		appLog.info("cross icon is verified.");
																	}else {
																		appLog.error("Pop up is not closing even after clicking on cross icon.");
																		sa.assertTrue(false, "Pop up is not closing even after clicking on cross icon.");
																	}
																}else {
																	appLog.error("Cross Icon is not displaying on folder delete pop up so cannot click on cross icon");
																	sa.assertTrue(false, "Cross Icon is displaying on folder delete pop up so cannot click on cross icon");
																}
															}
															
															if(i==2) {
																if(click(driver, fp.getFolderDeleteNoBtn(Workspace.FundraisingWorkspace,PageName.FundsPage, 10), "No Button", action.SCROLLANDBOOLEAN)) {
																	ThreadSleep(1500);
																	appLog.info("clicked on folder delete confirmation pop up no button.");
																	if(!click(driver, fp.getFolderDeleteNoBtn(Workspace.FundraisingWorkspace,PageName.FundsPage, 10), "No Button", action.SCROLLANDBOOLEAN)) {
																		appLog.info("No Button is verified.");
																	}else {
																		appLog.error("Pop up is not closing even after clicking on No Button.");
																		sa.assertTrue(false, "Pop up is not closing even after clicking on No Button.");
																	}
																}else {
																	appLog.error("Cross Icon is not displaying on folder delete pop up so cannot click on cross icon");
																	sa.assertTrue(false, "Cross Icon is displaying on folder delete pop up so cannot click on cross icon");
																}
															}
														}else {
															appLog.error("Not able to click on folder: "+SplitedcommonFolderName[0]+" delete icon so cannot check delete folder functionality");
															sa.assertTrue(false, "Not able to click on folder: "+SplitedcommonFolderName[0]+" delete icon so cannot check delete folder functionality");
														}
													}else {
														appLog.error(SplitedcommonFolderName[0]+" is not available in the Build step 2 of 3 structure so cannot click on folder "+SplitedcommonFolderName[1]+" delete icon");
														sa.assertTrue(false, SplitedcommonFolderName[0]+" is not available in the Build step 2 of 3 structure so cannot click on folder "+SplitedcommonFolderName[1]+" delete icon");
													}
												}
											}else {
												appLog.error("Update Commonpath is empty in excel sheet in test case M8tc011_VerifyEditingAFolder so cannot check delete folder functionality on Sub Common Folder");
												sa.assertTrue(false, "Update Commonpath is empty in excel sheet in test case M8tc011_VerifyEditingAFolder so cannot check delete folder functionality on Sub Common Folder");
											}
											
											String[] updateCommonFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath).split(",");
											String updateSharedFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath);
											String updateInternalFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.InternalPath);
											String[] updateSTDFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath).split(",");
											String[] listOfFolders = {updateCommonFolder[0],updateInternalFolder,updateSharedFolder,updateSTDFolder[0],updateSTDFolder[1]};
											for(int i=0; i<listOfFolders.length; i++) {
												String id=null;
												id=fp.getCreatedFolderId(listOfFolders[i], PageName.FundsPage, 20);
												System.err.println("id>>>>>>"+id);
												if(id!=null) {
													if(fp.clickOnDeleteFolderButton(id)) {
														if(click(driver, fp.getFolderDeleteYesBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, 10), "folder delete yes Button", action.BOOLEAN)) {
															appLog.info("clicked on folder delete yes Button");
															ThreadSleep(5000);
//															if(fp.getCreatedFolderId(listOfFolders[i], PageName.FundsPage, 10)!=null) {
//																appLog.info(listOfFolders[i]+" is not deleted, folder is visible in step 2 of 3 pop up");
//																sa.assertTrue(false, listOfFolders[i]+" is not deleted, folder is visible in step 2 of 3 pop up");
//															}else {
//																appLog.error(listOfFolders[i]+" is deleted successfully, it is not visible in step 2 of 3 pop up");
//															}
														}else {
															appLog.error("Yes button is not displaying on folder delete pop up so cannot click on Yes button");
															sa.assertTrue(false, "yes button is not displaying on folder delete pop up so cannot click on yes button");
														}
													}else {
														appLog.error("Not able to click on folder: "+listOfFolders[i]+" delete icon so cannot check delete folder functionality");
														sa.assertTrue(false, "Not able to click on folder: "+listOfFolders[i]+" delete icon so cannot check delete folder functionality");
													}
												}else {
													appLog.error(listOfFolders[i]+" is not available in the Build step 2 of 3 structure so cannot click on folder "+listOfFolders[i]+" delete icon");
													sa.assertTrue(false, listOfFolders[i]+" is not available in the Build step 2 of 3 structure so cannot click on folder "+listOfFolders[i]+" delete icon");
												}
											}
										
											for(int i=0; i<listOfFolders.length; i++) {
												if(fp.getCreatedFolderId(listOfFolders[i], PageName.FundsPage, 10)!=null) {
													appLog.info(listOfFolders[i]+" is not deleted, folder is visible in step 2 of 3 pop up");
													sa.assertTrue(false, listOfFolders[i]+" is not deleted, folder is visible in step 2 of 3 pop up");
												}else {
													appLog.error(listOfFolders[i]+" is deleted successfully, it is not visible in step 2 of 3 pop up");
												}
											}
											
										} else {
											appLog.error("Cannot click on import button so cannot import folder template.");
											sa.assertTrue(false,"Cannot click on import button so cannot import folder template.");
										}
									} else {
										appLog.error(folderTemplateName+" template is not available to import, So canot import folder template.");
										sa.assertTrue(false,folderTemplateName+" template is not available to import, So canot import folder template.");
									}
								} else {
									appLog.error("Import folder template button cannot be clicked, So cannnot import folder template.");
									sa.assertTrue(false,"Import folder template button cannot be clicked, So cannnot import folder template.");
								}
								
							} else {
								appLog.error("Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify folder delete functionality.");
								sa.assertTrue(false,"Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify folder delete functionality.");
								fp.recover(Workspace.FundraisingWorkspace, 30);
							}
						} else {
							appLog.error("Next button of step 1 of 3 cannot be click, So cannot verify folder delete functionality.");
							sa.assertTrue(false,"Next button of step 1 of 3 cannot be click, So cannot verify folder delete functionality.");
							fp.recover(Workspace.FundraisingWorkspace, 30);
						}
					} else {
						appLog.error("One of the mandatory field is not filled, So cannot verify folder delete functionality.");
						sa.assertTrue(false,"One of the mandatory field is not filled, So cannot verify folder delete functionality.");
						fp.recover(Workspace.FundraisingWorkspace, 30);
					}
				} else {
					appLog.error("Build Fundraising workspace button cannot be clicked, So cannot verify folder delete functionality.");
					sa.assertTrue(false,"Build Fundraising workspace button cannot be clicked, So cannot verify folder delete functionality.");
					fp.recover(Workspace.FundraisingWorkspace, 30);
				}
			} else {
				appLog.error(M8F1+" fund is not available, so cannot continue with the tc.");
				sa.assertTrue(false,M8F1+" fund is not available, so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot continue with the tc.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	
	@Test
	public void M8tc024_VerifyDeleteAFolderInvestorSide(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		boolean flag = true;
		String dependsOnTC = "M8tc012_VerifyDeleteAFolder";
		String commonFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependsOnTC,excelLabel.CommonPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F4)){
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Funcraising wokspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.InvestorWorkspace, 30), "Buld Investor workspace button", action.BOOLEAN)){
					if(sendKeys(driver, fp.getFundVintageYearField(Workspace.InvestorWorkspace, 30), "2013", "Vintage year", action.BOOLEAN)){
						appLog.info("Successfully passed value to vintage year text box.");
					} else {
						appLog.error("Cannot pass value to vintage year text box.");
						sa.assertTrue(false,"Cannot pass value to vintage year text box.");
					}
					
					if(sendKeys(driver, fp.getFundSizeField(Workspace.InvestorWorkspace, 30), "5000", "Fund Size", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund size text box.");
					} else {
						appLog.error("Cannot pass value to Fund size text box.");
						sa.assertTrue(false,"Cannot pass value to Fund size text box.");
					}
					
					if(sendKeys(driver, fp.getFundContactField(Workspace.InvestorWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund contact text box.");
					} else {
						appLog.error("Cannot pass value to Fund contact text box.");
						sa.assertTrue(false,"Cannot pass value to Fund contact text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundEmailField(Workspace.InvestorWorkspace, 30), "abc@abc.com", "Fund Email", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Email text box.");
					} else {
						appLog.error("Cannot pass value to Fund Email text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Email text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundPhoneField(Workspace.InvestorWorkspace, 30), "987654321", "Fund Phone", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Phone text box.");
					} else {
						appLog.error("Cannot pass value to Fund Phone text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Phone text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundDescriptionField(Workspace.InvestorWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Description text box.");
					} else {
						appLog.error("Cannot pass value to Fund Description text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Description text box.");
					}
					if(flag){
						if(click(driver, fp.getNext1Of3Button(Workspace.InvestorWorkspace, 30), "Next button of step 1 of 3", action.BOOLEAN)){
							if(fp.getStep2Of3Header(Workspace.InvestorWorkspace, 30)!=null){
								if(fp.getImportFolderTemplateButton(Workspace.InvestorWorkspace, EnableDisable.Enable, 30)!=null){
									appLog.info("Import Folder Template Button is enabled and verfied.");
								} else {
									appLog.error("Import Folder template button is not enabled and is not verified.");
									sa.assertTrue(false,"Import Folder template button is not enabled and is not verified.");
								}
								if(click(driver, fp.getImportFolderTemplateButton(Workspace.InvestorWorkspace, 30), "Import folder template button", action.BOOLEAN)){
									WebElement ele = FindElement(driver, "//span[@title='"+ folderTemplateName +"']/../preceding-sibling::span[1]//input", folderTemplateName+" template radio button", action.BOOLEAN, 30);
									if(click(driver, ele, folderTemplateName+" folder template", action.BOOLEAN)){
										if(click(driver, fp.getSelectFolderTemplateImportButton(Workspace.InvestorWorkspace, EnableDisable.Enable, 30), "Imprt button", action.BOOLEAN)){
											System.err.println(commonFolderName);
											if(!commonFolderName.isEmpty() && !commonFolderName.equalsIgnoreCase("Commonpath")) {
												String[] SplitedcommonFolderName=commonFolderName.split("/");
												for(int i=0; i<3; i++) {
													String id=null;
													id=fp.getCreatedFolderId(commonFolderName, PageName.FundsPage, 20);
													System.err.println("id>>>>>>"+id);
													if(id!=null) {
														if(fp.clickOnDeleteFolderButton(id)) {
															if(i==0) {
															if(fp.getFolderDeleteErrorMsg1(Workspace.InvestorWorkspace, PageName.FundsPage, 20)!=null) {
																if(fp.getFolderDeleteErrorMsg1(Workspace.InvestorWorkspace,PageName.FundsPage, 10).getText().trim().contains(FundsPageErrorMessage.Step2Of3ConfirmDeletionErrorMessage)) {
																	appLog.info(FundsPageErrorMessage.Step2Of3ConfirmDeletionErrorMessage+" Folder delete message is verified. ");
																}else {
																	appLog.error(FundsPageErrorMessage.Step2Of3ConfirmDeletionErrorMessage+" folder delete message is not verified");
																	sa.assertTrue(false, FundsPageErrorMessage.Step2Of3ConfirmDeletionErrorMessage+" folder delete message is not verified");
																}
															}else {
																appLog.error("Folder delete error message is not visible");
																sa.assertTrue(false, "Folder delete error message is not visible");
															}
															if(fp.getFolderDeleteYesBtn(Workspace.InvestorWorkspace, PageName.FundsPage, 20)!=null) {
																appLog.info("Yes button is displaying on folder delete pop up");
															}else {
																appLog.error("Yes button is displaying on folder delete pop up");
																sa.assertTrue(false, "Yes button is displaying on folder delete pop up");
															}
															if(click(driver, fp.getFolderDeleteNoBtn(Workspace.InvestorWorkspace,PageName.FundsPage, 10), "folder delete no Button", action.BOOLEAN)) {
																appLog.info("No button is displaying on folder delete pop up");
																appLog.info("clicked on folder delete No Button");
															}else {
																appLog.error("No button is not displaying on folder delete pop up so cannot click on No button");
																sa.assertTrue(false, "No button is not displaying on folder delete pop up so cannot click on No button");
															}
															}
															if(i==1) {
																if(click(driver, fp.getFolderDeleteCrossIcon(Workspace.InvestorWorkspace,PageName.FundsPage, 10), "folder delete cross icon", action.SCROLLANDBOOLEAN)) {
																	ThreadSleep(1500);
																	appLog.info("Cross Icon is displaying on folder delete pop up");
																	appLog.info("clicked on folder delete cross icon");
																	if(!click(driver, fp.getFolderDeleteCrossIcon(Workspace.InvestorWorkspace,PageName.FundsPage, 1), "folder delete cross icon", action.SCROLLANDBOOLEAN)) {
																		appLog.info("cross icon is verified.");
																	}else {
																		appLog.error("Pop up is not closing even after clicking on cross icon.");
																		sa.assertTrue(false, "Pop up is not closing even after clicking on cross icon.");
																	}
																}else {
																	appLog.error("Cross Icon is not displaying on folder delete pop up so cannot click on cross icon");
																	sa.assertTrue(false, "Cross Icon is displaying on folder delete pop up so cannot click on cross icon");
																}
															}
															
															if(i==2) {
																if(click(driver, fp.getFolderDeleteNoBtn(Workspace.InvestorWorkspace,PageName.FundsPage, 10), "No Button", action.SCROLLANDBOOLEAN)) {
																	ThreadSleep(1500);
																	appLog.info("clicked on folder delete confirmation pop up no button.");
																	if(!click(driver, fp.getFolderDeleteNoBtn(Workspace.InvestorWorkspace,PageName.FundsPage, 10), "No Button", action.SCROLLANDBOOLEAN)) {
																		appLog.info("No Button is verified.");
																	}else {
																		appLog.error("Pop up is not closing even after clicking on No Button.");
																		sa.assertTrue(false, "Pop up is not closing even after clicking on No Button.");
																	}
																}else {
																	appLog.error("Cross Icon is not displaying on folder delete pop up so cannot click on cross icon");
																	sa.assertTrue(false, "Cross Icon is displaying on folder delete pop up so cannot click on cross icon");
																}
															}
														}else {
															appLog.error("Not able to click on folder: "+SplitedcommonFolderName[0]+" delete icon so cannot check delete folder functionality");
															sa.assertTrue(false, "Not able to click on folder: "+SplitedcommonFolderName[0]+" delete icon so cannot check delete folder functionality");
														}
													}else {
														appLog.error(SplitedcommonFolderName[0]+" is not available in the Build step 2 of 3 structure so cannot click on folder "+SplitedcommonFolderName[1]+" delete icon");
														sa.assertTrue(false, SplitedcommonFolderName[0]+" is not available in the Build step 2 of 3 structure so cannot click on folder "+SplitedcommonFolderName[1]+" delete icon");
													}
												}
											}else {
												appLog.error("Update Commonpath is empty in excel sheet in test case M8tc011_VerifyEditingAFolder so cannot check delete folder functionality on Sub Common Folder");
												sa.assertTrue(false, "Update Commonpath is empty in excel sheet in test case M8tc011_VerifyEditingAFolder so cannot check delete folder functionality on Sub Common Folder");
											}
											
											String[] updateCommonFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependsOnTC,excelLabel.CommonPath).split(",");
											String updateSharedFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependsOnTC,excelLabel.SharedPath);
											String updateInternalFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependsOnTC,excelLabel.InternalPath);
											String[] updateSTDFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependsOnTC,excelLabel.StandardPath).split(",");
											String[] listOfFolders= {updateCommonFolder[0],updateInternalFolder,updateSharedFolder,updateSTDFolder[0],updateSTDFolder[1]};
											for(int i=0; i<listOfFolders.length; i++) {
												String id=null;
												id=fp.getCreatedFolderId(listOfFolders[i], PageName.FundsPage, 20);
												System.err.println("id>>>>>>"+id);
												if(id!=null) {
													if(fp.clickOnDeleteFolderButton(id)) {
														if(click(driver, fp.getFolderDeleteYesBtn(Workspace.InvestorWorkspace, PageName.FundsPage, 10), "folder delete yes Button", action.BOOLEAN)) {
															appLog.info("clicked on folder delete yes Button");
															ThreadSleep(5000);
//															if(fp.getCreatedFolderId(listOfFolders[i], PageName.FundsPage, 10)!=null) {
//																appLog.info(listOfFolders[i]+" is not deleted, folder is visible in step 2 of 3 pop up");
//																sa.assertTrue(false, listOfFolders[i]+" is not deleted, folder is visible in step 2 of 3 pop up");
//															}else {
//																appLog.error(listOfFolders[i]+" is deleted successfully, it is not visible in step 2 of 3 pop up");
//															}
														}else {
															appLog.error("Yes button is not displaying on folder delete pop up so cannot click on Yes button");
															sa.assertTrue(false, "yes button is not displaying on folder delete pop up so cannot click on yes button");
														}
													}else {
														appLog.error("Not able to click on folder: "+listOfFolders[i]+" delete icon so cannot check delete folder functionality");
														sa.assertTrue(false, "Not able to click on folder: "+listOfFolders[i]+" delete icon so cannot check delete folder functionality");
													}
												}else {
													appLog.error(listOfFolders[i]+" is not available in the Build step 2 of 3 structure so cannot click on folder "+listOfFolders[i]+" delete icon");
													sa.assertTrue(false, listOfFolders[i]+" is not available in the Build step 2 of 3 structure so cannot click on folder "+listOfFolders[i]+" delete icon");
												}
											}
											
											for(int i=0; i<listOfFolders.length; i++) {
												if(fp.getCreatedFolderId(listOfFolders[i], PageName.FundsPage, 10)!=null) {
													appLog.info(listOfFolders[i]+" is not deleted, folder is visible in step 2 of 3 pop up");
													sa.assertTrue(false, listOfFolders[i]+" is not deleted, folder is visible in step 2 of 3 pop up");
												}else {
													appLog.error(listOfFolders[i]+" is deleted successfully, it is not visible in step 2 of 3 pop up");
												}
											}
										
											
										} else {
											appLog.error("Cannot click on import button so cannot import folder template.");
											sa.assertTrue(false,"Cannot click on import button so cannot import folder template.");
										}
									} else {
										appLog.error(folderTemplateName+" template is not available to import, So canot import folder template.");
										sa.assertTrue(false,folderTemplateName+" template is not available to import, So canot import folder template.");
									}
								} else {
									appLog.error("Import folder template button cannot be clicked, So cannnot import folder template.");
									sa.assertTrue(false,"Import folder template button cannot be clicked, So cannnot import folder template.");
								}
								
							} else {
								appLog.error("Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify folder delete functionality.");
								sa.assertTrue(false,"Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify folder delete functionality.");
								fp.recover(Workspace.InvestorWorkspace, 30);
							}
						} else {
							appLog.error("Next button of step 1 of 3 cannot be click, So cannot verify folder delete functionality.");
							sa.assertTrue(false,"Next button of step 1 of 3 cannot be click, So cannot verify folder delete functionality.");
							fp.recover(Workspace.InvestorWorkspace, 30);
						}
					} else {
						appLog.error("One of the mandatory field is not filled, So cannot verify folder delete functionality.");
						sa.assertTrue(false,"One of the mandatory field is not filled, So cannot verify folder delete functionality.");
						fp.recover(Workspace.InvestorWorkspace, 30);
					}
				} else {
					appLog.error("Build Investor workspace button cannot be clicked, So cannot verify folder delete functionality.");
					sa.assertTrue(false,"Build Investor workspace button cannot be clicked, So cannot verify folder delete functionality.");
					fp.recover(Workspace.InvestorWorkspace, 30);
				}
			} else {
				appLog.error(M8F4+" fund is not available, so cannot continue with the tc.");
				sa.assertTrue(false,M8F4+" fund is not available, so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot continue with the tc.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M8tc013_BuildFundraisingWorkspaceWithoutFolderStructureAndInstitution(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		boolean flag = true;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F1)){
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Funcraising wokspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 30), "Buld Fundraising workspace button", action.BOOLEAN)){
					if(sendKeys(driver, fp.getFundVintageYearField(Workspace.FundraisingWorkspace, 30), "2013", "Vintage year", action.BOOLEAN)){
						appLog.info("Successfully passed value to vintage year text box.");
					} else {
						appLog.error("Cannot pass value to vintage year text box.");
						sa.assertTrue(false,"Cannot pass value to vintage year text box.");
					}
					
					if(sendKeys(driver, fp.getFundSizeField(Workspace.FundraisingWorkspace, 30), "5000", "Fund Size", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund size text box.");
					} else {
						appLog.error("Cannot pass value to Fund size text box.");
						sa.assertTrue(false,"Cannot pass value to Fund size text box.");
					}
					
					if(click(driver, fp.getFundFundraisingNoRadioButton(Workspace.FundraisingWorkspace, 30), "Fundraisng No Button", action.BOOLEAN)){
						appLog.info("Successfully selected no radio button.");
					} else {
						appLog.error("Cannot select No in fundraising.");
						sa.assertTrue(false,"Cannot select No in fundraising.");
					}
					
					if(sendKeys(driver, fp.getFundContactField(Workspace.FundraisingWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund contact text box.");
					} else {
						appLog.error("Cannot pass value to Fund contact text box.");
						sa.assertTrue(false,"Cannot pass value to Fund contact text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundEmailField(Workspace.FundraisingWorkspace, 30), "abc@abc.com", "Fund Email", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Email text box.");
					} else {
						appLog.error("Cannot pass value to Fund Email text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Email text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundPhoneField(Workspace.FundraisingWorkspace, 30), "987654321", "Fund Phone", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Phone text box.");
					} else {
						appLog.error("Cannot pass value to Fund Phone text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Phone text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundDescriptionField(Workspace.FundraisingWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Description text box.");
					} else {
						appLog.error("Cannot pass value to Fund Description text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Description text box.");
					}
					if(flag){
						if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 30), "Next button of step 1 of 3", action.BOOLEAN)){
							if(fp.getStep2Of3Header(Workspace.FundraisingWorkspace, 30)!=null){
								if(click(driver, fp.getNext2Of3Button(Workspace.FundraisingWorkspace, 30), "Next Button", action.BOOLEAN)){
									if(click(driver, fp.getDone3Of3Button(Workspace.FundraisingWorkspace, 30), "Done Button", action.BOOLEAN)){
										if(fp.getCongratulationsMessage(Workspace.FundraisingWorkspace, 30)!=null){
											String text = fp.getCongratulationsMessage(Workspace.FundraisingWorkspace, 30).getText().trim();
											if(text.equalsIgnoreCase(FundsPageErrorMessage.Step3Of3CongratulationMessage)){
												appLog.error("Congratulations message is verified.");
											} else {
												appLog.error("Congratulations message is not verified. Expected: "+FundsPageErrorMessage.Step3Of3CongratulationMessage+"\tActual: "+text);
												sa.assertTrue(false,"Congratulations message is not verified. Expected: "+FundsPageErrorMessage.Step3Of3CongratulationMessage+"\tActual: "+text);
											}
										} else {
											appLog.error("Congratulations message is not poping up, So cannot verify congratulations message.");
											sa.assertTrue(false,"Congratulations message is not poping up, So cannot verify congratulations message.");
										}
										if(fp.getCongratulationsCrossIcon(Workspace.FundraisingWorkspace, 30)!=null){
											appLog.info("Cross icon is verified.");
										} else {
											appLog.error("Cross icon is not present on workspace built pop up.");
											sa.assertTrue(false,"Cross icon is not present on workspace built pop up.");
										}
										if(fp.getCongratulationsHeader(Workspace.FundraisingWorkspace, 30)!=null){
											String text = fp.getCongratulationsHeader(Workspace.FundraisingWorkspace, 30).getText().trim();
											if(text.equalsIgnoreCase("Workspace Built")){
												appLog.info(text+" Header is verified.");
											} else {
												appLog.error("Workspace built header is not verified. Expected: Workspace Built\tActual: "+text);
												sa.assertTrue(false,"Workspace built header is not verified. Expected: Workspace Built\tActual: "+text);
											}
										} else {
											appLog.error("Header is not present on workspace built pop up.");
											sa.assertTrue(false,"Header is not present on workspace built pop up.");
										}
										if(fp.getCongratulationsCloseButton(Workspace.FundraisingWorkspace, 30)!=null){
											appLog.info("Cancel buton is verified.");
											if(click(driver, fp.getCongratulationsCloseButton(Workspace.FundraisingWorkspace, 30), "Close button", action.BOOLEAN)){
												ThreadSleep(1500);
												if(click(driver, fp.getCongratulationsCloseButton(Workspace.FundraisingWorkspace, 30), "Close button", action.BOOLEAN)){
													appLog.error("Congratulations pop is not closed after clicking on close button. So close button is not working.");
													sa.assertTrue(false,"Congratulations pop is not closed after clicking on close button. So close button is not working.");
													fp.recover(Workspace.FundraisingWorkspace, 30);
												} else {
													appLog.info("Close button is verified.");
												}
											} else {
												appLog.error("Cannot click on close button so cannot check close button functionality.");
												sa.assertTrue(false,"Cannot click on close button so cannot check close button functionality.");
												fp.recover(Workspace.FundraisingWorkspace, 30);
											}
										} else {
											appLog.error("Close Button is not present on workspace built pop up.");
											sa.assertTrue(false,"Close Button is not present on workspace built pop up.");
											fp.recover(Workspace.FundraisingWorkspace, 30);
										}
										if(click(driver, fp.getInvestmentInfoLink(Workspace.FundraisingWorkspace, M8F1, 30), "Investment info link", action.BOOLEAN)){
											String text  = getText(driver, fp.getInvestmentInfoName(30), "Name", action.BOOLEAN).trim();
											if(text.equalsIgnoreCase(M8F1)){
												appLog.info("Fund Name is verified on invesment info pop up.");
											} else {
												appLog.error("Fund name is not correct on investment info pop up. Expected: "+M8F1+"\tActual: "+text);
												sa.assertTrue(false,"Fund name is not correct on investment info pop up. Expected: "+M8F1+"\tActual: "+text);
											}
											text = getText(driver, fp.getInvestmentInfoSize(30), "Size", action.BOOLEAN);
											if(text.equalsIgnoreCase("5000.00")){
												appLog.info("Fund Size is verified on invesment info pop up.");
											} else {
												appLog.error("Fund Size is not correct on investment info pop up. Expected: 5000.00\tActual: "+text);
												sa.assertTrue(false,"Fund Size is not correct on investment info pop up. Expected: 5000.00\tActual: "+text);
											}
											text = getText(driver, fp.getInvestmentInfoVintageYear(30), "Vintage Year", action.BOOLEAN);
											if(text.equalsIgnoreCase("2013")){
												appLog.info("Fund Vintage year is verified on invesment info pop up.");
											} else {
												appLog.error("Fund Vintage year is not correct on investment info pop up. Expected: 2013\tActual: "+text);
												sa.assertTrue(false,"Fund Vintage year is not correct on investment info pop up. Expected: 2013\tActual: "+text);
											}
											text = getText(driver, fp.getInvestmentInfoContact(30), "Contact Name", action.BOOLEAN);
											if(text.equalsIgnoreCase("Contact1")){
												appLog.info("Fund Contact Name is verified on invesment info pop up.");
											} else {
												appLog.error("Fund Contact Name is not correct on investment info pop up. Expected: Contact1\tActual: "+text);
												sa.assertTrue(false,"Fund Contact Name is not correct on investment info pop up. Expected: Contact1\tActual: "+text);
											}
											text = getText(driver, fp.getInvestmentInfoPhone(30), "Contact Name", action.BOOLEAN);
											if(text.equalsIgnoreCase("987654321")){
												appLog.info("Fund Phone is verified on invesment info pop up.");
											} else {
												appLog.error("Fund Phone is not correct on investment info pop up. Expected: 987654321\tActual: "+text);
												sa.assertTrue(false,"Fund Phone is not correct on investment info pop up. Expected: 987654321\tActual: "+text);
											}
											text = getText(driver, fp.getInvestmentInfoEmail(30), "Contact Name", action.BOOLEAN);
											if(text.equalsIgnoreCase("abc@abc.com")){
												appLog.info("Fund Email is verified on invesment info pop up.");
											} else {
												appLog.error("Fund Email is not correct on investment info pop up. Expected: abc@abc.com\tActual: "+text);
												sa.assertTrue(false,"Fund Email is not correct on investment info pop up. Expected: abc@abc.com\tActual: "+text);
											}
											text = getText(driver, fp.getInvestmentInfoDescription(30), "Contact Name", action.BOOLEAN);
											if(text.equalsIgnoreCase("Sample description")){
												appLog.info("Fund Description is verified on invesment info pop up.");
											} else {
												appLog.error("Fund Description is not correct on investment info pop up. Expected: Sample description\tActual: "+text);
												sa.assertTrue(false,"Fund Description is not correct on investment info pop up. Expected: Sample description\tActual: "+text);
											}
											if(isSelected(driver, fp.getInvestmentInfoFundraisingNoRadioButton(30), "No Radio Button")){
												appLog.info("No is selected and is verified.");
												System.err.println("Before clicking edit icon");
											} else {
												appLog.error("No is not selected on investment info pop up.");
												sa.assertTrue(false,"No is not selected on investment info pop up.");
											}
									
										} else {
											appLog.error("Investment info link cannot be clicked, SO cannot verify the field data.");
											sa.assertTrue(false,"Investment info link cannot be clicked, SO cannot verify the field data.");
										}
									} else {
										appLog.error("Done Button cannot be clikced, So cannot continue with the tc.");
										sa.assertTrue(false,"Done Button cannot be clikced, So cannot continue with the tc.");
									}
								} else {
									appLog.error("Next Button of 2 of 3 cannot be clicked, So cannot continue with the tc.");
									sa.assertTrue(false,"Next Button of 2 of 3 cannot be clicked, So cannot continue with the tc.");
								}
							} else {
								appLog.error("Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify fund info link.");
								sa.assertTrue(false,"Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify fund info link.");
								fp.recover(Workspace.FundraisingWorkspace, 30);
							}
						} else {
							appLog.error("Next button of step 1 of 3 cannot be click, So cannot verify fund info link.");
							sa.assertTrue(false,"Next button of step 1 of 3 cannot be click, So cannot verify fund info link.");
							fp.recover(Workspace.FundraisingWorkspace, 30);
						}
					} else {
						appLog.error("One of the mandatory field is not filled, So cannot verify fund info link.");
						sa.assertTrue(false,"One of the mandatory field is not filled, So cannot verify fund info link.");
						fp.recover(Workspace.FundraisingWorkspace, 30);
					}
				} else {
					appLog.error("Build Fundraising workspace button cannot be clicked, So cannot verify fund info link.");
					sa.assertTrue(false,"Build Fundraising workspace button cannot be clicked, So cannot verify fund info link.");
					fp.recover(Workspace.FundraisingWorkspace, 30);
				}
			} else {
				appLog.error(M8F1+" fund is not available, so cannot continue with the tc.");
				sa.assertTrue(false,M8F1+" fund is not available, so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot continue with the tc.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M8tc025_BuildFundraisingWorkspaceWithoutFolderStructureAndInstitution(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		boolean flag = true;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F4)){
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Funcraising wokspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.InvestorWorkspace, 30), "Buld Investor workspace button", action.BOOLEAN)){
					if(sendKeys(driver, fp.getFundVintageYearField(Workspace.InvestorWorkspace, 30), "2013", "Vintage year", action.BOOLEAN)){
						appLog.info("Successfully passed value to vintage year text box.");
					} else {
						appLog.error("Cannot pass value to vintage year text box.");
						sa.assertTrue(false,"Cannot pass value to vintage year text box.");
					}
					
					if(sendKeys(driver, fp.getFundSizeField(Workspace.InvestorWorkspace, 30), "5000", "Fund Size", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund size text box.");
					} else {
						appLog.error("Cannot pass value to Fund size text box.");
						sa.assertTrue(false,"Cannot pass value to Fund size text box.");
					}
					
					if(click(driver, fp.getFundFundraisingNoRadioButton(Workspace.InvestorWorkspace, 30), "Fundraisng No Button", action.BOOLEAN)){
						appLog.info("Successfully selected no radio button.");
					} else {
						appLog.error("Cannot select No in Investor.");
						sa.assertTrue(false,"Cannot select No in Investor.");
					}
					
					if(sendKeys(driver, fp.getFundContactField(Workspace.InvestorWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund contact text box.");
					} else {
						appLog.error("Cannot pass value to Fund contact text box.");
						sa.assertTrue(false,"Cannot pass value to Fund contact text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundEmailField(Workspace.InvestorWorkspace, 30), "abc@abc.com", "Fund Email", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Email text box.");
					} else {
						appLog.error("Cannot pass value to Fund Email text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Email text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundPhoneField(Workspace.InvestorWorkspace, 30), "987654321", "Fund Phone", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Phone text box.");
					} else {
						appLog.error("Cannot pass value to Fund Phone text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Phone text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundDescriptionField(Workspace.InvestorWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Description text box.");
					} else {
						appLog.error("Cannot pass value to Fund Description text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Description text box.");
					}
					if(flag){
						if(click(driver, fp.getNext1Of3Button(Workspace.InvestorWorkspace, 30), "Next button of step 1 of 3", action.BOOLEAN)){
							if(fp.getStep2Of3Header(Workspace.InvestorWorkspace, 30)!=null){
								if(click(driver, fp.getNext2Of3Button(Workspace.InvestorWorkspace, 30), "Next Button", action.BOOLEAN)){
									if(click(driver, fp.getDone3Of3Button(Workspace.InvestorWorkspace, 30), "Done Button", action.BOOLEAN)){
										if(fp.getCongratulationsMessage(Workspace.InvestorWorkspace, 30)!=null){
											String text = fp.getCongratulationsMessage(Workspace.InvestorWorkspace, 30).getText().trim();
											if(text.equalsIgnoreCase(FundsPageErrorMessage.Step3Of3CongratulationMessage)){
												appLog.error("Congratulations message is verified.");
											} else {
												appLog.error("Congratulations message is not verified. Expected: "+FundsPageErrorMessage.Step3Of3CongratulationMessage+"\tActual: "+text);
												sa.assertTrue(false,"Congratulations message is not verified. Expected: "+FundsPageErrorMessage.Step3Of3CongratulationMessage+"\tActual: "+text);
											}
										} else {
											appLog.error("Congratulations message is not poping up, So cannot verify congratulations message.");
											sa.assertTrue(false,"Congratulations message is not poping up, So cannot verify congratulations message.");
										}
										if(fp.getCongratulationsCrossIcon(Workspace.InvestorWorkspace, 30)!=null){
											appLog.info("Cross icon is verified.");
										} else {
											appLog.error("Cross icon is not present on workspace built pop up.");
											sa.assertTrue(false,"Cross icon is not present on workspace built pop up.");
										}
										if(fp.getCongratulationsHeader(Workspace.InvestorWorkspace, 30)!=null){
											String text = fp.getCongratulationsHeader(Workspace.InvestorWorkspace, 30).getText().trim();
											if(text.equalsIgnoreCase("Workspace Built")){
												appLog.info(text+" Header is verified.");
											} else {
												appLog.error("Workspace built header is not verified. Expected: Workspace Built\tActual: "+text);
												sa.assertTrue(false,"Workspace built header is not verified. Expected: Workspace Built\tActual: "+text);
											}
										} else {
											appLog.error("Header is not present on workspace built pop up.");
											sa.assertTrue(false,"Header is not present on workspace built pop up.");
										}
										if(fp.getCongratulationsCloseButton(Workspace.InvestorWorkspace, 30)!=null){
											appLog.info("Cancel buton is verified.");
											if(click(driver, fp.getCongratulationsCloseButton(Workspace.InvestorWorkspace, 30), "Close button", action.BOOLEAN)){
												ThreadSleep(1500);
												if(click(driver, fp.getCongratulationsCloseButton(Workspace.InvestorWorkspace, 30), "Close button", action.BOOLEAN)){
													appLog.error("Congratulations pop is not closed after clicking on close button. So close button is not working.");
													sa.assertTrue(false,"Congratulations pop is not closed after clicking on close button. So close button is not working.");
													fp.recover(Workspace.InvestorWorkspace, 30);
												} else {
													appLog.info("Close button is verified.");
												}
											} else {
												appLog.error("Cannot click on close button so cannot check close button functionality.");
												sa.assertTrue(false,"Cannot click on close button so cannot check close button functionality.");
												fp.recover(Workspace.InvestorWorkspace, 30);
											}
										} else {
											appLog.error("Close Button is not present on workspace built pop up.");
											sa.assertTrue(false,"Close Button is not present on workspace built pop up.");
											fp.recover(Workspace.InvestorWorkspace, 30);
										}
										if(click(driver, fp.getInvestmentInfoLink(Workspace.InvestorWorkspace, M8F4, 30), "Investment info link", action.BOOLEAN)){
											String text  = getText(driver, fp.getInvestmentInfoName(30), "Name", action.BOOLEAN).trim();
											if(text.equalsIgnoreCase(M8F4)){
												appLog.info("Fund Name is verified on invesment info pop up.");
											} else {
												appLog.error("Fund name is not correct on investment info pop up. Expected: "+M8F4+"\tActual: "+text);
												sa.assertTrue(false,"Fund name is not correct on investment info pop up. Expected: "+M8F4+"\tActual: "+text);
											}
											text = getText(driver, fp.getInvestmentInfoSize(30), "Size", action.BOOLEAN);
											if(text.equalsIgnoreCase("5000.00")){
												appLog.info("Fund Size is verified on invesment info pop up.");
											} else {
												appLog.error("Fund Size is not correct on investment info pop up. Expected: 5000.00\tActual: "+text);
												sa.assertTrue(false,"Fund Size is not correct on investment info pop up. Expected: 5000.00\tActual: "+text);
											}
											text = getText(driver, fp.getInvestmentInfoVintageYear(30), "Vintage Year", action.BOOLEAN);
											if(text.equalsIgnoreCase("2013")){
												appLog.info("Fund Vintage year is verified on invesment info pop up.");
											} else {
												appLog.error("Fund Vintage year is not correct on investment info pop up. Expected: 2013\tActual: "+text);
												sa.assertTrue(false,"Fund Vintage year is not correct on investment info pop up. Expected: 2013\tActual: "+text);
											}
											text = getText(driver, fp.getInvestmentInfoContact(30), "Contact Name", action.BOOLEAN);
											if(text.equalsIgnoreCase("Contact1")){
												appLog.info("Fund Contact Name is verified on invesment info pop up.");
											} else {
												appLog.error("Fund Contact Name is not correct on investment info pop up. Expected: Contact1\tActual: "+text);
												sa.assertTrue(false,"Fund Contact Name is not correct on investment info pop up. Expected: Contact1\tActual: "+text);
											}
											text = getText(driver, fp.getInvestmentInfoPhone(30), "Contact Name", action.BOOLEAN);
											if(text.equalsIgnoreCase("987654321")){
												appLog.info("Fund Phone is verified on invesment info pop up.");
											} else {
												appLog.error("Fund Phone is not correct on investment info pop up. Expected: 987654321\tActual: "+text);
												sa.assertTrue(false,"Fund Phone is not correct on investment info pop up. Expected: 987654321\tActual: "+text);
											}
											text = getText(driver, fp.getInvestmentInfoEmail(30), "Contact Name", action.BOOLEAN);
											if(text.equalsIgnoreCase("abc@abc.com")){
												appLog.info("Fund Email is verified on invesment info pop up.");
											} else {
												appLog.error("Fund Email is not correct on investment info pop up. Expected: abc@abc.com\tActual: "+text);
												sa.assertTrue(false,"Fund Email is not correct on investment info pop up. Expected: abc@abc.com\tActual: "+text);
											}
											text = getText(driver, fp.getInvestmentInfoDescription(30), "Contact Name", action.BOOLEAN);
											if(text.equalsIgnoreCase("Sample description")){
												appLog.info("Fund Description is verified on invesment info pop up.");
											} else {
												appLog.error("Fund Description is not correct on investment info pop up. Expected: Sample description\tActual: "+text);
												sa.assertTrue(false,"Fund Description is not correct on investment info pop up. Expected: Sample description\tActual: "+text);
											}
											if(isSelected(driver, fp.getInvestmentInfoFundraisingNoRadioButton(30), "No Radio Button")){
												appLog.info("No is selected and is verified.");
											} else {
												appLog.error("No is not selected on investment info pop up.");
												sa.assertTrue(false,"No is not selected on investment info pop up.");
											}
									
										} else {
											appLog.error("Investment info link cannot be clicked, SO cannot verify the field data.");
											sa.assertTrue(false,"Investment info link cannot be clicked, SO cannot verify the field data.");
										}
									} else {
										appLog.error("Done Button cannot be clikced, So cannot continue with the tc.");
										sa.assertTrue(false,"Done Button cannot be clikced, So cannot continue with the tc.");
									}
								} else {
									appLog.error("Next Button of 2 of 3 cannot be clicked, So cannot continue with the tc.");
									sa.assertTrue(false,"Next Button of 2 of 3 cannot be clicked, So cannot continue with the tc.");
								}
							} else {
								appLog.error("Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify fund info link.");
								sa.assertTrue(false,"Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify fund info link.");
								fp.recover(Workspace.InvestorWorkspace, 30);
							}
						} else {
							appLog.error("Next button of step 1 of 3 cannot be click, So cannot verify fund info link.");
							sa.assertTrue(false,"Next button of step 1 of 3 cannot be click, So cannot verify fund info link.");
							fp.recover(Workspace.InvestorWorkspace, 30);
						}
					} else {
						appLog.error("One of the mandatory field is not filled, So cannot verify fund info link.");
						sa.assertTrue(false,"One of the mandatory field is not filled, So cannot verify fund info link.");
						fp.recover(Workspace.InvestorWorkspace, 30);
					}
				} else {
					appLog.error("Build Investor workspace button cannot be clicked, So cannot verify fund info link.");
					sa.assertTrue(false,"Build Investor workspace button cannot be clicked, So cannot verify fund info link.");
					fp.recover(Workspace.InvestorWorkspace, 30);
				}
			} else {
				appLog.error(M8F4+" fund is not available, so cannot continue with the tc.");
				sa.assertTrue(false,M8F4+" fund is not available, so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot continue with the tc.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M8tc014_VerifyFundraisingAndSortingOnStep3Of3(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		boolean flag = true;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F2)){
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Funcraising wokspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 30), "Buld Fundraising workspace button", action.BOOLEAN)){
					if(sendKeys(driver, fp.getFundVintageYearField(Workspace.FundraisingWorkspace, 30), "2013", "Vintage year", action.BOOLEAN)){
						appLog.info("Successfully passed value to vintage year text box.");
					} else {
						appLog.error("Cannot pass value to vintage year text box.");
						sa.assertTrue(false,"Cannot pass value to vintage year text box.");
					}
					
					if(sendKeys(driver, fp.getFundSizeField(Workspace.FundraisingWorkspace, 30), "5000", "Fund Size", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund size text box.");
					} else {
						appLog.error("Cannot pass value to Fund size text box.");
						sa.assertTrue(false,"Cannot pass value to Fund size text box.");
					}
					
					if(sendKeys(driver, fp.getFundContactField(Workspace.FundraisingWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund contact text box.");
					} else {
						appLog.error("Cannot pass value to Fund contact text box.");
						sa.assertTrue(false,"Cannot pass value to Fund contact text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundEmailField(Workspace.FundraisingWorkspace, 30), "abc@abc.com", "Fund Email", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Email text box.");
					} else {
						appLog.error("Cannot pass value to Fund Email text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Email text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundPhoneField(Workspace.FundraisingWorkspace, 30), "987654321", "Fund Phone", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Phone text box.");
					} else {
						appLog.error("Cannot pass value to Fund Phone text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Phone text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundDescriptionField(Workspace.FundraisingWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Description text box.");
					} else {
						appLog.error("Cannot pass value to Fund Description text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Description text box.");
					}
					if(flag){
						if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 30), "Next button of step 1 of 3", action.BOOLEAN)){
							if(fp.getStep2Of3Header(Workspace.FundraisingWorkspace, 30)!=null){
								if(fp.getImportFolderTemplateButton(Workspace.FundraisingWorkspace, EnableDisable.Enable, 30)!=null){
									appLog.info("Import Folder Template Button is enabled and verfied.");
								} else {
									appLog.error("Import Folder template button is not enabled and is not verified.");
									sa.assertTrue(false,"Import Folder template button is not enabled and is not verified.");
								}
								if(click(driver, fp.getImportFolderTemplateButton(Workspace.FundraisingWorkspace, 30), "Import folder template button", action.BOOLEAN)){
									WebElement ele = FindElement(driver, "//span[@title='"+ folderTemplateName +"']/../preceding-sibling::span[1]//input", folderTemplateName+" template radio button", action.BOOLEAN, 30);
									if(click(driver, ele, folderTemplateName+" folder template", action.BOOLEAN)){
										if(click(driver, fp.getSelectFolderTemplateImportButton(Workspace.FundraisingWorkspace, EnableDisable.Enable, 30), "Imprt button", action.BOOLEAN)){
											if(click(driver, fp.getNext2Of3Button(Workspace.FundraisingWorkspace, 30), "Next Butto", action.BOOLEAN)){
												List<WebElement> institutionsName = fp.getStep3Of3InstitutionsList();
												if(checkSorting(driver, SortOrder.Assecending, institutionsName)){
													appLog.info("Institutions column is in assecending order.");
												} else {
													appLog.error("Institutions Column is not in assecending order.");
													sa.assertTrue(false,"Institutions Column is not in assecending order.");
												}
												if(click(driver, fp.getInsitutionsColumnHeader(30), "Institution Column", action.BOOLEAN)){
													institutionsName = fp.getStep3Of3InstitutionsList();
													if(checkSorting(driver, SortOrder.Decending, institutionsName)){
														appLog.info("Institutions column is in Decending order.");
													} else {
														appLog.error("Institutions Column is not in Decending order.");
														sa.assertTrue(false,"Institutions Column is not in Decending order.");
													}
												} else {
													appLog.error("cannot click on institution column header, So cannot check sorting.");
													sa.assertTrue(false,"cannot click on institution column header, So cannot check sorting.");
												}
												if(fp.selectInstitutionOrLP(M8I1, Workspace.FundraisingWorkspace, 30)){
													String text = getText(driver, fp.getInvestorAddedConfirmationHeader(Workspace.FundraisingWorkspace, 30), "Confirmation Header", action.BOOLEAN);
													if(text.trim().equalsIgnoreCase("Confirmation")){
														appLog.info("Confirmation popup header is verified.");
													} else {
														appLog.error("investor added Confirmation Pop Up header is not verified.Expected: Confirmation\tActual: "+text);
														sa.assertTrue(false,"investor added Confirmation Pop Up header is not verified.Expected: Confirmation\tActual: "+text);
													}
													text = getText(driver, fp.getInvestorAddedConfirmationMessage(Workspace.FundraisingWorkspace, 30), "Confirmation Message", action.BOOLEAN);
													if(text.trim().equalsIgnoreCase(FundsPageErrorMessage.Step3Of3InvestorAddedMessage)){
														appLog.info("investor added message is verified.");
													} else {
														appLog.error("investor added message is not verified. Expected: "+FundsPageErrorMessage.Step3Of3InvestorAddedMessage+"\tActual: "+text);
														sa.assertTrue(false,"investor added message is not verified. Expected: "+FundsPageErrorMessage.Step3Of3InvestorAddedMessage+"\tActual: "+text);
													}
													if(fp.getInvestorAddedConfirmationCloseButton(Workspace.FundraisingWorkspace, 30)!=null){
														appLog.info("Close button is verified.");
													} else {
														appLog.error("Close button is not present on investor added confirmation pop.");
														sa.assertTrue(false,"Close button is not present on investor added confirmation pop.");
													}
													if(fp.getInvestorAddedConfirmationCrossIcon(Workspace.FundraisingWorkspace, 30)!=null){
														appLog.info("Cross Icon is verified.");
													} else {
														appLog.error("Cross Icon is not present on investor added confirmation pop.");
														sa.assertTrue(false,"Cross Icon is not present on investor added confirmation pop.");
													}
													if(click(driver, fp.getInvestorAddedConfirmationCloseButton(Workspace.FundraisingWorkspace, 30), "Close Button", action.BOOLEAN)){
														ThreadSleep(1500);
														if(fp.getInvestorAddedConfirmationCloseButton(Workspace.FundraisingWorkspace, 1)!=null){
															appLog.error("investor added Confirmation Pop Up is not closing after clicking on cancel button.");
															sa.assertTrue(false,"investor added Confirmation Pop Up is not closing after clicking on cancel button.");
														} else {
															appLog.info("cancel button is working and is verified.");
														}
													} else {
														appLog.error("Cancel button cannot be clicked, So cannot verify cancel button functionality.");
														sa.assertTrue(false,"Cancel button cannot be clicked, So cannot verify cancel button functionality.");
														click(driver, fp.getInvestorAddedConfirmationCrossIcon(Workspace.FundraisingWorkspace, 30), "Cross icon", action.BOOLEAN);
													}
													if(isSelected(driver, fp.getInstituionCheckBoxOn3Of3(M8I1, Workspace.FundraisingWorkspace, 30), M8I1+" institution check box")){
														appLog.info(M8I1+" institution check box is selected and is verified.");
													} else {
														appLog.error(M8I1+" institution checkbox is not checked after addding the institution.");
														sa.assertTrue(false,M8I1+" institution checkbox is not checked after addding the institution.");
													}
													
													if(!isSelected(driver, fp.getInstituionCheckBoxOn3Of3(M8I2, Workspace.FundraisingWorkspace, 30), M8I2+" institution check box")){
														appLog.info(M8I2+" institution check box is not selected and is verified.");
													} else {
														appLog.error(M8I2+" institution checkbox is checked after addding the institution "+M8I2+".");
														sa.assertTrue(false,M8I2+" institution checkbox is checked after addding the institution "+M8I2+".");
													}
													if(fp.selectInstitutionOrLP(M8I1, Workspace.FundraisingWorkspace, 30)){
														text = getText(driver, fp.getInvestorRemoveConfirmationHeader(Workspace.FundraisingWorkspace, 30), "Confirmation Header", action.BOOLEAN);
														if(text.trim().equalsIgnoreCase("Confirmation")){
															appLog.info("Confirmation popup header is verified.");
														} else {
															appLog.error("investor remove Confirmation Pop Up header is not verified.Expected: Confirmation\tActual: "+text);
															sa.assertTrue(false,"investor remove Confirmation Pop Up header is not verified.Expected: Confirmation\tActual: "+text);
														}
														text = getText(driver, fp.getInvestorRemoveConfirmationMessage(Workspace.FundraisingWorkspace, 30), "Confirmation Message", action.BOOLEAN);
														if(text.trim().equalsIgnoreCase(FundsPageErrorMessage.Step3Of3InvestorRemovedMessage)){
															appLog.info("INvestor removed message is verified.");
														} else {
															appLog.error("Investor removed message is not verified. Expected: "+FundsPageErrorMessage.Step3Of3InvestorRemovedMessage+"\tActual: "+text);
															sa.assertTrue(false,"Investor removed message is not verified. Expected: "+FundsPageErrorMessage.Step3Of3InvestorRemovedMessage+"\tActual: "+text);
														}
														if(fp.getInvestorRemoveConfirmationCloseButton(Workspace.FundraisingWorkspace, 30)!=null){
															appLog.info("Close button is verified.");
														} else {
															appLog.error("Close button is not present on investor remove confirmation pop.");
															sa.assertTrue(false,"Close button is not present on investor remove confirmation pop.");
														}
														if(fp.getInvestorRemoveConfirmationCrossIcon(Workspace.FundraisingWorkspace, 30)!=null){
															appLog.info("Cross Icon is verified.");
														} else {
															appLog.error("Cross Icon is not present on investor remove confirmation pop.");
															sa.assertTrue(false,"Cross Icon is not present on investor remove confirmation pop.");
														}
														if(click(driver, fp.getInvestorRemoveConfirmationCloseButton(Workspace.FundraisingWorkspace, 30), "Close Button", action.BOOLEAN)){
															ThreadSleep(1500);
															if(fp.getInvestorRemoveConfirmationCloseButton(Workspace.FundraisingWorkspace, 1)!=null){
																appLog.error("investor remove Confirmation Pop Up is not closing after clicking on cancel button.");
																sa.assertTrue(false,"investor remove Confirmation Pop Up is not closing after clicking on cancel button.");
															} else {
																appLog.info("cancel button is working and is verified.");
															}
														} else {
															appLog.error("Cancel button cannot be clicked, So cannot verify cancel button functionality.");
															sa.assertTrue(false,"Cancel button cannot be clicked, So cannot verify cancel button functionality.");
															click(driver, fp.getInvestorRemoveConfirmationCrossIcon(Workspace.FundraisingWorkspace, 30), "Cross icon", action.BOOLEAN);
														}
														if(!isSelected(driver, fp.getInstituionCheckBoxOn3Of3(M8I1, Workspace.FundraisingWorkspace, 30), M8I1+" institution check box")){
															appLog.info(M8I1+" institution check box is not selected and is verified.");
														} else {
															appLog.error(M8I1+" institution checkbox is checked after removing the institution.");
															sa.assertTrue(false,M8I1+" institution checkbox is checked after Removing the institution.");
														}
														
														if(!isSelected(driver, fp.getInstituionCheckBoxOn3Of3(M8I2, Workspace.FundraisingWorkspace, 30), M8I2+" institution check box")){
															appLog.info(M8I2+" institution check box is not selected and is verified.");
														} else {
															appLog.error(M8I2+" institution checkbox is checked after removing the institution "+M8I2+".");
															sa.assertTrue(false,M8I2+" institution checkbox is not checked after removing the institution "+M8I2+".");
														}
														if(fp.selectInstitutionOrLP(M8I1, Workspace.FundraisingWorkspace, 30)){
															if(!click(driver, fp.getInvestorAddedConfirmationCloseButton(Workspace.FundraisingWorkspace, 30), "Close button", action.BOOLEAN)){
																if(!click(driver, fp.getInvestorAddedConfirmationCrossIcon(Workspace.FundraisingWorkspace, 30), "Cross icon", action.BOOLEAN)){
																	fp.recover(Workspace.FundraisingWorkspace, 30);
																} else {
																	if(!click(driver, fp.getStep3Of3CrossIcon(Workspace.FundraisingWorkspace, 30), "Cross icon", action.BOOLEAN)){
																		fp.recover(Workspace.FundraisingWorkspace, 30);
																	}
																}
															} else {
																if(!click(driver, fp.getStep3Of3CrossIcon(Workspace.FundraisingWorkspace, 30), "Cross icon", action.BOOLEAN)){
																	fp.recover(Workspace.FundraisingWorkspace, 30);
																}
															}
															if(fp.getInvestmentInfoLink(Workspace.FundraisingWorkspace, M8F2, 30)!=null){
																appLog.info("Workspace is created successfully.");
																if(fp.VerifyFolderStructure("FolderTemp", M8I1, null, null, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
																	appLog.info("Folder Structure is verified on fund page.");
																} else {
																	appLog.error("Folder Structure is not verified on fund page.");
																	sa.assertTrue(false,"Folder Structure is not verified on fund page.");
																}
																if(click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 30), "Manage Fundraising Icon", action.SCROLLANDBOOLEAN)){
																	if(isSelected(driver, fp.getInstituionCheckBoxOn3Of3(M8I1, Workspace.FundraisingWorkspace, 30), M8I1+" checkbox")){
																		appLog.info(M8I1+" instituion check box is selected on manage Fundraising pop up.");
																	} else {
																		appLog.error(M8I1+" instituion check box is not selected on manage Fundraising pop up.");
																		sa.assertTrue(false,M8I1+" instituion check box is not selected on manage Fundraising pop up.");
																	}
																} else {
																	appLog.error("Cannot click on manage Fundraising icon, So cannot verify institute check box on manage Fundraising.");
																	sa.assertTrue(false,"Cannot click on manage Fundraising icon, So cannot verify institute check box on manage Fundraising.");
																}
																InstitutionPageBusinessLayer ins = new InstitutionPageBusinessLayer(driver);
																switchToDefaultContent(driver);
																if(fp.clickOnTab(TabName.InstituitonsTab)){
																	if(ins.clickOnCreatedInstitution(M8I1)){
																		switchToFrame(driver, 30, fp.getFrame(PageName.InstitutionsPage, 30));
																		scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising wokspace view.");
																		if(fp.VerifyFolderStructure("FolderTemp", null, null, M8F2, Workspace.FundraisingWorkspace, PageName.InstitutionsPage, 30)){
																			appLog.info("Folder Structure is verified on instituions page.");
																		} else {
																			appLog.error("Folder Structure is not verified on instituions page.");
																			sa.assertTrue(false,"Folder Structure is not verified on instituions page.");
																		}
																	} else {
																		appLog.error(M8I1+" instituion is not present, So cannot verify folder structure on it.");
																		sa.assertTrue(false,M8I1+" instituion is not present, So cannot verify folder structure on it.");
																		
																	}
																} else {
																	appLog.error("Institutions tab cannot be clicked, So cannot verify folder structure on institution page.");
																	sa.assertTrue(false,"Institutions tab cannot be clicked, So cannot verify folder structure on institution page.");
																}
															} else {
																appLog.error("Workspace is not created, So cannot verify folder strucutre, manage Fundraising and insitution page.");
																sa.assertTrue(false,"Workspace is not created, So cannot verify folder strucutre, manage Fundraising and insitution page.");
															}
															
																
														} else {
															appLog.error("Cannot click on "+M8I1+" institutions checkbox, SO cannot add Fundraising.");
															sa.assertTrue(false,"Cannot click on "+M8I1+" institutions checkbox, SO cannot add Fundraising.");
														}
													} else {
														appLog.error("Cannot click on "+M8I1+" institutions checkbox, SO cannot check removal message and pop up.");
														sa.assertTrue(false,"Cannot click on "+M8I1+" institutions checkbox, SO cannot check removal message and pop up.");
													}
												} else {
													appLog.error("Cannot click on "+M8I1+" institutions checkbox, SO cannot continue with tc");
													sa.assertTrue(false,"Cannot click on "+M8I1+" institutions checkbox, SO cannot continue with tc.");
												}
												
											} else {
												appLog.error("Cannot click on next button so cannot continue with the tc.");
												sa.assertTrue(false,"Cannot click on next button so cannot continue with the tc.");
											}
										} else {
											appLog.error("Cannot click on import button so cannot import folder template.");
											sa.assertTrue(false,"Cannot click on import button so cannot import folder template.");
										}
									} else {
										appLog.error(folderTemplateName+" template is not available to import, So canot import folder template.");
										sa.assertTrue(false,folderTemplateName+" template is not available to import, So canot import folder template.");
									}
								} else {
									appLog.error("Import folder template button cannot be clicked, So cannnot import folder template.");
									sa.assertTrue(false,"Import folder template button cannot be clicked, So cannnot import folder template.");
								}
								
							} else {
								appLog.error("Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify Fundraising account and sorting.");
								sa.assertTrue(false,"Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify Fundraising account and sorting.");
								fp.recover(Workspace.FundraisingWorkspace, 30);
							}
						} else {
							appLog.error("Next button of step 1 of 3 cannot be click, So cannot verify Fundraising account and sorting.");
							sa.assertTrue(false,"Next button of step 1 of 3 cannot be click, So cannot verify Fundraising account and sorting.");
							fp.recover(Workspace.FundraisingWorkspace, 30);
						}
					} else {
						appLog.error("One of the mandatory field is not filled, So cannot verify Fundraising account and sorting.");
						sa.assertTrue(false,"One of the mandatory field is not filled, So cannot verify Fundraising account and sorting.");
						fp.recover(Workspace.FundraisingWorkspace, 30);
					}
				} else {
					appLog.error("Build Fundraising workspace button cannot be clicked, So cannot verify Fundraising account and sorting.");
					sa.assertTrue(false,"Build Fundraising workspace button cannot be clicked, So cannot verify Fundraising account and sorting.");
					fp.recover(Workspace.FundraisingWorkspace, 30);
				}
			} else {
				appLog.error(M8F2+" fund is not available, so cannot continue with the tc.");
				sa.assertTrue(false,M8F2+" fund is not available, so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot continue with the tc.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M8tc026_VerifyInvestorAndSortingOnStep3Of3InvestorWorkspace(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		boolean flag = true;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F5)){
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Funcraising wokspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.InvestorWorkspace, 30), "Buld Investor workspace button", action.BOOLEAN)){
					if(sendKeys(driver, fp.getFundVintageYearField(Workspace.InvestorWorkspace, 30), "2013", "Vintage year", action.BOOLEAN)){
						appLog.info("Successfully passed value to vintage year text box.");
					} else {
						appLog.error("Cannot pass value to vintage year text box.");
						sa.assertTrue(false,"Cannot pass value to vintage year text box.");
					}
					
					if(sendKeys(driver, fp.getFundSizeField(Workspace.InvestorWorkspace, 30), "5000", "Fund Size", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund size text box.");
					} else {
						appLog.error("Cannot pass value to Fund size text box.");
						sa.assertTrue(false,"Cannot pass value to Fund size text box.");
					}
					
					if(sendKeys(driver, fp.getFundContactField(Workspace.InvestorWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund contact text box.");
					} else {
						appLog.error("Cannot pass value to Fund contact text box.");
						sa.assertTrue(false,"Cannot pass value to Fund contact text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundEmailField(Workspace.InvestorWorkspace, 30), "abc@abc.com", "Fund Email", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Email text box.");
					} else {
						appLog.error("Cannot pass value to Fund Email text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Email text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundPhoneField(Workspace.InvestorWorkspace, 30), "987654321", "Fund Phone", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Phone text box.");
					} else {
						appLog.error("Cannot pass value to Fund Phone text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Phone text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundDescriptionField(Workspace.InvestorWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Description text box.");
					} else {
						appLog.error("Cannot pass value to Fund Description text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Description text box.");
					}
					if(flag){
						if(click(driver, fp.getNext1Of3Button(Workspace.InvestorWorkspace, 30), "Next button of step 1 of 3", action.BOOLEAN)){
							if(fp.getStep2Of3Header(Workspace.InvestorWorkspace, 30)!=null){
								if(fp.getImportFolderTemplateButton(Workspace.InvestorWorkspace, EnableDisable.Enable, 30)!=null){
									appLog.info("Import Folder Template Button is enabled and verfied.");
								} else {
									appLog.error("Import Folder template button is not enabled and is not verified.");
									sa.assertTrue(false,"Import Folder template button is not enabled and is not verified.");
								}
								if(click(driver, fp.getImportFolderTemplateButton(Workspace.InvestorWorkspace, 30), "Import folder template button", action.BOOLEAN)){
									WebElement ele = FindElement(driver, "//span[@title='"+ folderTemplateName +"']/../preceding-sibling::span[1]//input", folderTemplateName+" template radio button", action.BOOLEAN, 30);
									if(click(driver, ele, folderTemplateName+" folder template", action.BOOLEAN)){
										if(click(driver, fp.getSelectFolderTemplateImportButton(Workspace.InvestorWorkspace, EnableDisable.Enable, 30), "Imprt button", action.BOOLEAN)){
											if(click(driver, fp.getNext2Of3Button(Workspace.InvestorWorkspace, 30), "Next Butto", action.BOOLEAN)){
												if(fp.getLimitedPartnerCheckBox(M8I1, M8LP1, Workspace.InvestorWorkspace, 30)!=null){
													appLog.info(M8LP1+" is present under institution "+M8I1);
												//Issue is here clicking 2 times.
													if(!isSelected(driver, fp.getLimitedPartnerCheckBox(M8I1, M8LP1, Workspace.InvestorWorkspace, 30), M8LP1+" checkbox")){
														appLog.info(M8LP1+" check box is not selected and is verified.");
													} else {
														appLog.error(M8LP1+" checkbox is already selected.");
														sa.assertTrue(false,M8LP1+" checkbox is already selected.");
													}
												} else {
													appLog.error(M8LP1+" is not present under institution "+M8I1+", So cannot check selection status of LP.");
													sa.assertTrue(false,M8LP1+" is not present under institution "+M8I1+", So cannot check selection status of LP.");
												}
												
												if(fp.getLimitedPartnerCheckBox(M8I2, M8LP2, Workspace.InvestorWorkspace, 30)!=null){
													appLog.info(M8LP2+" is present under institution "+M8I2);
													if(!isSelected(driver, fp.getLimitedPartnerCheckBox(M8I2, M8LP2, Workspace.InvestorWorkspace, 30), M8LP2+" checkbox")){
														appLog.info(M8LP2+" check box is not selected and is verified.");
													} else {
														appLog.error(M8LP2+" checkbox is already selected.");
														sa.assertTrue(false,M8LP2+" checkbox is already selected.");
													}
												} else {
													appLog.error(M8LP2+" is not present under institution "+M8I2+", So cannot check selection status of LP.");
													sa.assertTrue(false,M8LP2+" is not present under institution "+M8I2+", So cannot check selection status of LP.");
												}
												if(fp.selectInstitutionOrLP(M8I1+"/"+M8LP1, Workspace.InvestorWorkspace, 30)){
													String text = getText(driver, fp.getInvestorAddedConfirmationHeader(Workspace.InvestorWorkspace, 30), "Confirmation Header", action.BOOLEAN);
													if(text.trim().equalsIgnoreCase("Confirmation")){
														appLog.info("Confirmation popup header is verified.");
													} else {
														appLog.error("investor added Confirmation Pop Up header is not verified.Expected: Confirmation\tActual: "+text);
														sa.assertTrue(false,"investor added Confirmation Pop Up header is not verified.Expected: Confirmation\tActual: "+text);
													}
													text = getText(driver, fp.getInvestorAddedConfirmationMessage(Workspace.InvestorWorkspace, 30), "Confirmation Message", action.BOOLEAN);
													if(text.trim().equalsIgnoreCase(FundsPageErrorMessage.Step3Of3InvestorAddedMessage)){
														appLog.info("investor added message is verified.");
													} else {
														appLog.error("investor added message is not verified. Expected: "+FundsPageErrorMessage.Step3Of3InvestorAddedMessage+"\tActual: "+text);
														sa.assertTrue(false,"investor added message is not verified. Expected: "+FundsPageErrorMessage.Step3Of3InvestorAddedMessage+"\tActual: "+text);
													}
													if(fp.getInvestorAddedConfirmationCloseButton(Workspace.InvestorWorkspace, 30)!=null){
														appLog.info("Close button is verified.");
													} else {
														appLog.error("Close button is not present on investor added confirmation pop.");
														sa.assertTrue(false,"Close button is not present on investor added confirmation pop.");
													}
													if(fp.getInvestorAddedConfirmationCrossIcon(Workspace.InvestorWorkspace, 30)!=null){
														appLog.info("Cross Icon is verified.");
													} else {
														appLog.error("Cross Icon is not present on investor added confirmation pop.");
														sa.assertTrue(false,"Cross Icon is not present on investor added confirmation pop.");
													}
													if(click(driver, fp.getInvestorAddedConfirmationCloseButton(Workspace.InvestorWorkspace, 30), "Close Button", action.BOOLEAN)){
														ThreadSleep(1500);
														if(fp.getInvestorAddedConfirmationCloseButton(Workspace.InvestorWorkspace, 1)!=null){
															appLog.error("investor added Confirmation Pop Up is not closing after clicking on cancel button.");
															sa.assertTrue(false,"investor added Confirmation Pop Up is not closing after clicking on cancel button.");
														} else {
															appLog.info("cancel button is working and is verified.");
														}
													} else {
														appLog.error("Cancel button cannot be clicked, So cannot verify cancel button functionality.");
														sa.assertTrue(false,"Cancel button cannot be clicked, So cannot verify cancel button functionality.");
														click(driver, fp.getInvestorAddedConfirmationCrossIcon(Workspace.InvestorWorkspace, 30), "Cross icon", action.BOOLEAN);
													}
													if(isSelected(driver, fp.getLimitedPartnerCheckBox(M8I1, M8LP1, Workspace.InvestorWorkspace, 30), M8LP1+" check box")){
														appLog.info(M8LP1+" LP check box is selected and is verified.");
													} else {
														appLog.error(M8LP1+" LP checkbox is not checked after addding the Investor.");
														sa.assertTrue(false,M8LP1+" LP checkbox is not checked after addding the Investor.");
													}
													
													if(!isSelected(driver, fp.getLimitedPartnerCheckBox(M8I2, M8LP2, Workspace.InvestorWorkspace, 30), M8LP2+" checkbox")){
														appLog.info(M8LP2+" check box is not selected and is verified.");
													} else {
														appLog.error(M8LP2+" checkbox is already selected.");
														sa.assertTrue(false,M8LP2+" checkbox is already selected.");
													}
													
													if(fp.selectInstitutionOrLP(M8I1+"/"+M8LP1, Workspace.InvestorWorkspace, 30)){
														text = getText(driver, fp.getInvestorRemoveConfirmationHeader(Workspace.InvestorWorkspace, 30), "Confirmation Header", action.BOOLEAN);
														if(text.trim().equalsIgnoreCase("Confirmation")){
															appLog.info("Confirmation popup header is verified.");
														} else {
															appLog.error("investor remove Confirmation Pop Up header is not verified.Expected: Confirmation\tActual: "+text);
															sa.assertTrue(false,"investor remove Confirmation Pop Up header is not verified.Expected: Confirmation\tActual: "+text);
														}
														text = getText(driver, fp.getInvestorRemoveConfirmationMessage(Workspace.InvestorWorkspace, 30), "Confirmation Message", action.BOOLEAN);
														if(text.trim().equalsIgnoreCase(FundsPageErrorMessage.Step3Of3InvestorRemovedMessage)){
															appLog.info("INvestor removed message is verified.");
														} else {
															appLog.error("Investor removed message is not verified. Expected: "+FundsPageErrorMessage.Step3Of3InvestorRemovedMessage+"\tActual: "+text);
															sa.assertTrue(false,"Investor removed message is not verified. Expected: "+FundsPageErrorMessage.Step3Of3InvestorRemovedMessage+"\tActual: "+text);
														}
														if(fp.getInvestorRemoveConfirmationCloseButton(Workspace.InvestorWorkspace, 30)!=null){
															appLog.info("Close button is verified.");
														} else {
															appLog.error("Close button is not present on investor remove confirmation pop.");
															sa.assertTrue(false,"Close button is not present on investor remove confirmation pop.");
														}
														if(fp.getInvestorRemoveConfirmationCrossIcon(Workspace.InvestorWorkspace, 30)!=null){
															appLog.info("Cross Icon is verified.");
														} else {
															appLog.error("Cross Icon is not present on investor remove confirmation pop.");
															sa.assertTrue(false,"Cross Icon is not present on investor remove confirmation pop.");
														}
														if(click(driver, fp.getInvestorRemoveConfirmationCloseButton(Workspace.InvestorWorkspace, 30), "Close Button", action.BOOLEAN)){
															ThreadSleep(1500);
															if(fp.getInvestorRemoveConfirmationCloseButton(Workspace.InvestorWorkspace, 1)!=null){
																appLog.error("investor remove Confirmation Pop Up is not closing after clicking on cancel button.");
																sa.assertTrue(false,"investor remove Confirmation Pop Up is not closing after clicking on cancel button.");
															} else {
																appLog.info("cancel button is working and is verified.");
															}
														} else {
															appLog.error("Cancel button cannot be clicked, So cannot verify cancel button functionality.");
															sa.assertTrue(false,"Cancel button cannot be clicked, So cannot verify cancel button functionality.");
															click(driver, fp.getInvestorRemoveConfirmationCrossIcon(Workspace.InvestorWorkspace, 30), "Cross icon", action.BOOLEAN);
														}
														if(!isSelected(driver, fp.getLimitedPartnerCheckBox(M8I1, M8LP1, Workspace.InvestorWorkspace, 30), M8LP1+" check box")){
															appLog.info(M8LP1+" institution check box is not selected and is verified.");
														} else {
															appLog.error(M8LP1+" institution checkbox is checked after removing the institution.");
															sa.assertTrue(false,M8LP1+" institution checkbox is checked after Removing the institution.");
														}
														
														if(!isSelected(driver, fp.getLimitedPartnerCheckBox(M8I2, M8LP2, Workspace.InvestorWorkspace, 30), M8LP2+" check box")){
															appLog.info(M8LP2+" institution check box is not selected and is verified.");
														} else {
															appLog.error(M8LP2+" institution checkbox is checked after removing.");
															sa.assertTrue(false,M8LP2+" institution checkbox is not checked after removing.");
														}
														if(fp.selectInstitutionOrLP(M8I1+"/"+M8LP1, Workspace.InvestorWorkspace, 30)){
															if(!click(driver, fp.getInvestorAddedConfirmationCloseButton(Workspace.InvestorWorkspace, 30), "Close button", action.BOOLEAN)){
																if(!click(driver, fp.getInvestorAddedConfirmationCrossIcon(Workspace.InvestorWorkspace, 30), "Cross icon", action.BOOLEAN)){
																	fp.recover(Workspace.InvestorWorkspace, 30);
																} else {
																	if(!click(driver, fp.getStep3Of3CrossIcon(Workspace.InvestorWorkspace, 30), "Cross icon", action.BOOLEAN)){
																		fp.recover(Workspace.InvestorWorkspace, 30);
																	}
																}
															} else {
																if(!click(driver, fp.getStep3Of3CrossIcon(Workspace.InvestorWorkspace, 30), "Cross icon", action.BOOLEAN)){
																	fp.recover(Workspace.InvestorWorkspace, 30);
																}
															}
															if(fp.getInvestmentInfoLink(Workspace.InvestorWorkspace, M8F5, 30)!=null){
																appLog.info("Workspace is created successfully.");
																if(fp.VerifyFolderStructure("FolderTemp", M8I1, M8LP1, null, Workspace.InvestorWorkspace, PageName.FundsPage, 30)){
																	appLog.info("Folder Structure is verified on fund page.");
																} else {
																	appLog.error("Folder Structure is not verified on fund page.");
																	sa.assertTrue(false,"Folder Structure is not verified on fund page.");
																}
																if(click(driver, fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 30), "Manage Investor Icon", action.SCROLLANDBOOLEAN)){
																	if(isSelected(driver, fp.getLimitedPartnerCheckBox(M8I1, M8LP1, Workspace.InvestorWorkspace, 30), M8LP1+" checkbox")){
																		appLog.info(M8LP1+" check box is selected on manage Investor pop up.");
																	} else {
																		appLog.error(M8LP1+" check box is not selected on manage Investor pop up.");
																		sa.assertTrue(false,M8LP1+" check box is not selected on manage Investor pop up.");
																	}
																} else {
																	appLog.error("Cannot click on manage Investor icon, So cannot verify LP check box on manage Investor.");
																	sa.assertTrue(false,"Cannot click on manage Investor icon, So cannot verify LP check box on manage Investor.");
																}
																//INstitution
																InstitutionPageBusinessLayer ins = new InstitutionPageBusinessLayer(driver);
																switchToDefaultContent(driver);
																if(fp.clickOnTab(TabName.InstituitonsTab)){
																	if(ins.clickOnCreatedInstitution(M8I1)){
																		switchToFrame(driver, 30, fp.getFrame(PageName.InstitutionsPage, 30));
																		scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor wokspace view.");
																		if(fp.VerifyFolderStructure("FolderTemp", null, M8LP1, M8F5, Workspace.InvestorWorkspace, PageName.InstitutionsPage, 30)){
																			appLog.info("Folder Structure is verified on institution page.");
																		} else {
																			appLog.error("Folder Structure is not verified on institution page.");
																			sa.assertTrue(false,"Folder Structure is not verified on institution page.");
																		}
																		switchToDefaultContent(driver);
																	} else {
																		appLog.error(M8I1+" instituion is not present, So cannot verify folder structure on it.");
																		sa.assertTrue(false,M8I1+" instituion is not present, So cannot verify folder structure on it.");
																		
																	}
																} else {
																	appLog.error("Institutions tab cannot be clicked, So cannot verify folder structure on institution page.");
																	sa.assertTrue(false,"Institutions tab cannot be clicked, So cannot verify folder structure on institution page.");
																}
																
																if(fp.clickOnTab(TabName.InstituitonsTab)){
																	if(ins.clickOnCreatedInstitution(M8LP1)){
																		switchToFrame(driver, 30, fp.getFrame(PageName.InstitutionsPage, 30));
																		scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor wokspace view.");
																		if(fp.VerifyFolderStructure("FolderTemp", null, null, M8F5, Workspace.InvestorWorkspace, PageName.InstitutionsPage, 30)){
																			appLog.info("Folder Structure is verified on LP page.");
																		} else {
																			appLog.error("Folder Structure is not verified on LP page.");
																			sa.assertTrue(false,"Folder Structure is not verified on LP page.");
																		}
																		switchToDefaultContent(driver);
																	} else {
																		appLog.error(M8I1+" instituion is not present, So cannot verify folder structure on it.");
																		sa.assertTrue(false,M8I1+" instituion is not present, So cannot verify folder structure on it.");
																		
																	}
																} else {
																	appLog.error("Institutions tab cannot be clicked, So cannot verify folder structure on LP page.");
																	sa.assertTrue(false,"Institutions tab cannot be clicked, So cannot verify folder structure on LP page.");
																}
																
																CommitmentPageBusinessLayer cmt = new CommitmentPageBusinessLayer(driver);
																if(fp.clickOnTab(TabName.CommitmentsTab)){
																	if(cmt.clickOnCreatedCommitmentId(M8CMT1)){
																		switchToFrame(driver, 30, cmt.getFrame(PageName.CommitmentsPage, 30));
																		scrollDownThroughWebelement(driver, cmt.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Workpsace view.");
																		if(fp.VerifyFolderStructure("FolderTemp", null, M8LP1, M8F5, Workspace.InvestorWorkspace, PageName.CommitmentsPage, 30)){
																			appLog.info("Folder Structure is verified on Commitment page.");
																		} else {
																			appLog.error("Folder Structure is not verified on Commitment page.");
																			sa.assertTrue(false,"Folder Structure is not verified on Commitment page.");
																		}
																		switchToDefaultContent(driver);
																	} else {
																		appLog.error(M8CMT1+" commitment is not present, So cannot verify folder structure on it.");
																		sa.assertTrue(false,M8CMT1+" commitment is not present, So cannot verify folder structure on it.");
																	}
																} else {
																	appLog.error("Commitment tab cannot be clicked, So cannot verify folder structure on LP page.");
																	sa.assertTrue(false,"Commitment tab cannot be clicked, So cannot verify folder structure on LP page.");
																}
																
															} else {
																appLog.error("Workspace is not created, So cannot verify folder strucutre, manage Investor and insitution page.");
																sa.assertTrue(false,"Workspace is not created, So cannot verify folder strucutre, manage Investor and insitution page.");
															}
															
																
														} else {
															appLog.error("Cannot click on "+M8LP1+" checkbox, SO cannot add Investor.");
															sa.assertTrue(false,"Cannot click on "+M8LP1+" institutions checkbox, SO cannot add Investor.");
														}
													} else {
														appLog.error("Cannot click on "+M8LP1+" checkbox, SO cannot check removal message and pop up.");
														sa.assertTrue(false,"Cannot click on "+M8LP1+" institutions checkbox, SO cannot check removal message and pop up.");
													}
												} else {
													appLog.error("Cannot click on "+M8LP1+" checkbox, SO cannot continue with tc");
													sa.assertTrue(false,"Cannot click on "+M8LP1+" checkbox, SO cannot continue with tc.");
												}
												
											} else {
												appLog.error("Cannot click on next button so cannot continue with the tc.");
												sa.assertTrue(false,"Cannot click on next button so cannot continue with the tc.");
											}
										} else {
											appLog.error("Cannot click on import button so cannot import folder template.");
											sa.assertTrue(false,"Cannot click on import button so cannot import folder template.");
										}
									} else {
										appLog.error(folderTemplateName+" template is not available to import, So canot import folder template.");
										sa.assertTrue(false,folderTemplateName+" template is not available to import, So canot import folder template.");
									}
								} else {
									appLog.error("Import folder template button cannot be clicked, So cannnot import folder template.");
									sa.assertTrue(false,"Import folder template button cannot be clicked, So cannnot import folder template.");
								}
								
							} else {
								appLog.error("Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify Investor account and sorting.");
								sa.assertTrue(false,"Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify Investor account and sorting.");
								fp.recover(Workspace.InvestorWorkspace, 30);
							}
						} else {
							appLog.error("Next button of step 1 of 3 cannot be click, So cannot verify Investor account and sorting.");
							sa.assertTrue(false,"Next button of step 1 of 3 cannot be click, So cannot verify Investor account and sorting.");
							fp.recover(Workspace.InvestorWorkspace, 30);
						}
					} else {
						appLog.error("One of the mandatory field is not filled, So cannot verify Investor account and sorting.");
						sa.assertTrue(false,"One of the mandatory field is not filled, So cannot verify Investor account and sorting.");
						fp.recover(Workspace.InvestorWorkspace, 30);
					}
				} else {
					appLog.error("Build Investor workspace button cannot be clicked, So cannot verify Investor account and sorting.");
					sa.assertTrue(false,"Build Investor workspace button cannot be clicked, So cannot verify Investor account and sorting.");
					fp.recover(Workspace.InvestorWorkspace, 30);
				}
			} else {
				appLog.error(M8F5+" fund is not available, so cannot continue with the tc.");
				sa.assertTrue(false,M8F5+" fund is not available, so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot continue with the tc.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M8tc015_VerifyWorkspaceCreationWithFolderAtStep2Of3(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		boolean flag = true;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F3)){
				String step1Of3Data[] = {"2254","2015","acb","987654321","abc@bcd.com","Sample description"};
				if(fp.buildWorkspace(step1Of3Data, WorkSpaceAction.CREATEFOLDERTEMPLATE, null, "FolderTemp", M8I1+"<break>"+M8I2, Workspace.FundraisingWorkspace, 60)){
					switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising Workspace View");
					if(fp.VerifyFolderStructure("FolderTemp", M8I1, null, null, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
						appLog.info("Folder Structure is verified on fund page institute '"+M8I1+"'.");
					} else {
						appLog.error("Folder Structure is not verified on fund page institute '"+M8I1+"'.");
						sa.assertTrue(false,"Folder Structure is not verified on fund page institute '"+M8I1+"'.");
					}
					if(fp.VerifyFolderStructure("FolderTemp", M8I2, null, null, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)){
						appLog.info("Folder Structure is verified on fund page institute '"+M8I2+"'.");
					} else {
						appLog.error("Folder Structure is not verified on fund page for institute '"+M8I2+"'.");
						sa.assertTrue(false,"Folder Structure is not verified on fund page institute '"+M8I2+"'.");
					}
					switchToDefaultContent(driver);
				} else {
					appLog.error("workspace cannot be created, So cannot check folder structure.");
					sa.assertTrue(false,"workspace cannot be created, So cannot check folder structure.");
				}
			} else {
				appLog.error(M8F3+" fund is not available, so cannot continue with the tc.");
				sa.assertTrue(false,M8F3+" fund is not available, so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot continue with the tc.");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M8tc027_VerifyWorkspaceCreationWithFolderAtStep2Of3InvestorWorkspace(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		boolean flag = true;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F6)){
				String step1Of3Data[] = {"2254","2015","acb","987654321","abc@bcd.com","Sample description"};
				if(fp.buildWorkspace(step1Of3Data, WorkSpaceAction.CREATEFOLDERTEMPLATE, null, "FolderTemp", M8I1+"/"+M8LP1+"<break>"+M8I2+"/"+M8LP2, Workspace.InvestorWorkspace, 60)){
					switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor Workspace View");
					if(fp.VerifyFolderStructure("FolderTemp", M8I1, M8LP1, null, Workspace.InvestorWorkspace, PageName.FundsPage, 30)){
						appLog.info("Folder Structure is verified on fund page institute '"+M8I1+"'.");
					} else {
						appLog.error("Folder Structure is not verified on fund page institute '"+M8I1+"'.");
						sa.assertTrue(false,"Folder Structure is not verified on fund page institute '"+M8I1+"'.");
					}
					if(fp.VerifyFolderStructure("FolderTemp", M8I2, M8LP2, null, Workspace.InvestorWorkspace, PageName.FundsPage, 30)){
						appLog.info("Folder Structure is verified on fund page institute '"+M8I2+"'.");
					} else {
						appLog.error("Folder Structure is not verified on fund page for institute '"+M8I2+"'.");
						sa.assertTrue(false,"Folder Structure is not verified on fund page institute '"+M8I2+"'.");
					}
					switchToDefaultContent(driver);
				} else {
					appLog.error("workspace cannot be created, So cannot check folder structure.");
					sa.assertTrue(false,"workspace cannot be created, So cannot check folder structure.");
				}
			} else {
				appLog.error(M8F6+" fund is not available, so cannot continue with the tc.");
				sa.assertTrue(false,M8F6+" fund is not available, so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot continue with the tc.");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M8tc028_VerifyBuildInvestorWorkspaceWhenInvestorWorkspaceIsAlreadyCreated(){

		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		boolean flag = true;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F2)){
				String step1Of3Data[] = {"5000","2015","Contact1","987654321","abc@bcd.com","Sample description"};
				if(fp.buildWorkspace(step1Of3Data, WorkSpaceAction.CREATEFOLDERTEMPLATE, null, "FolderTemp", null, Workspace.InvestorWorkspace, 60)){
					switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor Workspace View");
					if(click(driver, fp.getInvestmentInfoLink(Workspace.InvestorWorkspace, M8F2, 30), "Investment info link", action.BOOLEAN)){
						String text  = getText(driver, fp.getInvestmentInfoName(30), "Name", action.BOOLEAN).trim();
						if(text.equalsIgnoreCase(M8F2)){
							appLog.info("Fund Name is verified on invesment info pop up.");
						} else {
							appLog.error("Fund name is not correct on investment info pop up. Expected: "+M8F1+"\tActual: "+text);
							sa.assertTrue(false,"Fund name is not correct on investment info pop up. Expected: "+M8F1+"\tActual: "+text);
						}
						text = getText(driver, fp.getInvestmentInfoSize(30), "Size", action.BOOLEAN);
						if(text.equalsIgnoreCase("5000.00")){
							appLog.info("Fund Size is verified on invesment info pop up.");
						} else {
							appLog.error("Fund Size is not correct on investment info pop up. Expected: 5000.00\tActual: "+text);
							sa.assertTrue(false,"Fund Size is not correct on investment info pop up. Expected: 5000.00\tActual: "+text);
						}
						text = getText(driver, fp.getInvestmentInfoVintageYear(30), "Vintage Year", action.BOOLEAN);
						if(text.equalsIgnoreCase("2015")){
							appLog.info("Fund Vintage year is verified on invesment info pop up.");
						} else {
							appLog.error("Fund Vintage year is not correct on investment info pop up. Expected: 2013\tActual: "+text);
							sa.assertTrue(false,"Fund Vintage year is not correct on investment info pop up. Expected: 2013\tActual: "+text);
						}
						text = getText(driver, fp.getInvestmentInfoContact(30), "Contact Name", action.BOOLEAN);
						if(text.equalsIgnoreCase("Contact1")){
							appLog.info("Fund Contact Name is verified on invesment info pop up.");
						} else {
							appLog.error("Fund Contact Name is not correct on investment info pop up. Expected: Contact1\tActual: "+text);
							sa.assertTrue(false,"Fund Contact Name is not correct on investment info pop up. Expected: Contact1\tActual: "+text);
						}
						text = getText(driver, fp.getInvestmentInfoPhone(30), "Contact Phone", action.BOOLEAN);
						if(text.equalsIgnoreCase("987654321")){
							appLog.info("Fund Phone is verified on invesment info pop up.");
						} else {
							appLog.error("Fund Phone is not correct on investment info pop up. Expected: 987654321\tActual: "+text);
							sa.assertTrue(false,"Fund Phone is not correct on investment info pop up. Expected: 987654321\tActual: "+text);
						}
						text = getText(driver, fp.getInvestmentInfoEmail(30), "Contact Email", action.BOOLEAN);
						if(text.equalsIgnoreCase("abc@abc.com")){
							appLog.info("Fund Email is verified on invesment info pop up.");
						} else {
							appLog.error("Fund Email is not correct on investment info pop up. Expected: abc@abc.com\tActual: "+text);
							sa.assertTrue(false,"Fund Email is not correct on investment info pop up. Expected: abc@abc.com\tActual: "+text);
						}
						text = getText(driver, fp.getInvestmentInfoDescription(30), "Fund Description", action.BOOLEAN);
						if(text.equalsIgnoreCase("Sample description")){
							appLog.info("Fund Description is verified on invesment info pop up.");
						} else {
							appLog.error("Fund Description is not correct on investment info pop up. Expected: Sample description\tActual: "+text);
							sa.assertTrue(false,"Fund Description is not correct on investment info pop up. Expected: Sample description\tActual: "+text);
						}
						if(isSelected(driver, fp.getInvestmentInfoFundraisingYesRadioButton(30), "Yes Radio Button")){
							appLog.info("Yes is selected and is verified.");
						} else {
							appLog.error("Yes is not selected on investment info pop up.");
							sa.assertTrue(false,"Yes is not selected on investment info pop up.");
						}
				
					} else {
						appLog.error("Investment info link cannot be clicked, SO cannot verify the field data.");
						sa.assertTrue(false,"Investment info link cannot be clicked, SO cannot verify the field data.");
					}
					switchToDefaultContent(driver);
				} else {
					appLog.error("workspace cannot be created, So cannot check folder structure.");
					sa.assertTrue(false,"workspace cannot be created, So cannot check folder structure.");
				}
			} else {
				appLog.error(M8F2+" fund is not available, so cannot continue with the tc.");
				sa.assertTrue(false,M8F2+" fund is not available, so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot continue with the tc.");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M8tc029_VerifyBuildFundriaisingWorkspaceWhenInvestorWorkspaceIsAlreadyCreated(){

		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		boolean flag = true;
		String dependsOnTC = currentlyExecutingTC;
		String[] commonFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependsOnTC,excelLabel.CommonPath).split(",");
		String[] sharedFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependsOnTC,excelLabel.SharedPath).split(",");
		String[] internalFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependsOnTC,excelLabel.InternalPath).split(",");
		String[] standardFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependsOnTC,excelLabel.StandardPath).split(",");
		
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F5)){
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Funcraising wokspace view.");
				if(click(driver, fp.getBuildWorkspaceButton(Workspace.FundraisingWorkspace, 30), "Buld Fundraising workspace button", action.BOOLEAN)){
					if(sendKeys(driver, fp.getFundVintageYearField(Workspace.FundraisingWorkspace, 30), "2015", "Vintage year", action.BOOLEAN)){
						appLog.info("Successfully passed value to vintage year text box.");
					} else {
						appLog.error("Cannot pass value to vintage year text box.");
						sa.assertTrue(false,"Cannot pass value to vintage year text box.");
					}
					
					if(sendKeys(driver, fp.getFundSizeField(Workspace.FundraisingWorkspace, 30), "5000", "Fund Size", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund size text box.");
					} else {
						appLog.error("Cannot pass value to Fund size text box.");
						sa.assertTrue(false,"Cannot pass value to Fund size text box.");
					}
					
					if(sendKeys(driver, fp.getFundContactField(Workspace.FundraisingWorkspace, 30), "Contact1", "Fund contact", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund contact text box.");
					} else {
						appLog.error("Cannot pass value to Fund contact text box.");
						sa.assertTrue(false,"Cannot pass value to Fund contact text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundEmailField(Workspace.FundraisingWorkspace, 30), "abc@abc.com", "Fund Email", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Email text box.");
					} else {
						appLog.error("Cannot pass value to Fund Email text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Email text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundPhoneField(Workspace.FundraisingWorkspace, 30), "987654321", "Fund Phone", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Phone text box.");
					} else {
						appLog.error("Cannot pass value to Fund Phone text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Phone text box.");
						flag=false;
					}
					
					if(sendKeys(driver, fp.getFundDescriptionField(Workspace.FundraisingWorkspace, 30), "Sample description", "Fund Description", action.BOOLEAN)){
						appLog.info("Successfully passed value to Fund Description text box.");
					} else {
						appLog.error("Cannot pass value to Fund Description text box.");
						sa.assertTrue(false,"Cannot pass value to Fund Description text box.");
					}
					if(flag){
						if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 30), "Next button of step 1 of 3", action.BOOLEAN)){
							if(fp.getStep2Of3Header(Workspace.FundraisingWorkspace, 30)!=null){
								if(fp.getImportFolderTemplateButton(Workspace.FundraisingWorkspace, EnableDisable.Enable, 30)!=null){
									appLog.info("Import Folder Template Button is enabled and verfied.");
								} else {
									appLog.error("Import Folder template button is not enabled and is not verified.");
									sa.assertTrue(false,"Import Folder template button is not enabled and is not verified.");
								}
								if(click(driver, fp.getImportFolderTemplateButton(Workspace.FundraisingWorkspace, 30), "Import folder template button", action.BOOLEAN)){
									WebElement ele = FindElement(driver, "//span[@title='"+ folderTemplateName +"']/../preceding-sibling::span[1]//input", folderTemplateName+" template radio button", action.BOOLEAN, 30);
									if(click(driver, ele, folderTemplateName+" folder template", action.BOOLEAN)){
										if(click(driver, fp.getSelectFolderTemplateImportButton(Workspace.FundraisingWorkspace, EnableDisable.Enable, 30), "Imprt button", action.BOOLEAN)){
											String id = fp.getCreatedFolderId(commonFolder[1], PageName.FundsPage, 30);
											if(fp.clickOnDeleteFolderButton(id)){
												if(click(driver, fp.getFolderDeleteYesBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, 30), "Yes Button", action.BOOLEAN)){
													appLog.info("Successfully deleted sub folder of common. Path: "+commonFolder[1]);
												} else {
													appLog.error("Cannot click on yes button, So cannot delete subfolder of common folder. Path: "+commonFolder[1]);
													sa.assertTrue(false,"Cannot click on yes button, So cannot delete subfolder of common folder. Path: "+commonFolder[1]);
												}
											} else {
												appLog.error("Cannot click on delete button, So cannot delete subfolder of common folder. Path: "+commonFolder[1]);
												sa.assertTrue(false,"Cannot click on delete button, So cannot delete subfolder of common folder. Path: "+commonFolder[1]);
											}
											id = fp.getCreatedFolderId(commonFolder[2], PageName.FundsPage, 30);
											if(fp.clickOnDeleteFolderButton(id)){
												if(click(driver, fp.getFolderDeleteYesBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, 30), "Yes Button", action.BOOLEAN)){
													appLog.info("Successfully deleted sub folder of common. Path: "+commonFolder[2]);
												} else {
													appLog.error("Cannot click on yes button, So cannot delete subfolder of common folder. Path: "+commonFolder[2]);
													sa.assertTrue(false,"Cannot click on yes button, So cannot delete subfolder of common folder. Path: "+commonFolder[2]);
												}
											} else {
												appLog.error("Cannot click on delete button, So cannot delete subfolder of common folder. Path: "+commonFolder[2]);
												sa.assertTrue(false,"Cannot click on delete button, So cannot delete subfolder of common folder. Path: "+commonFolder[2]);
											}
											id = fp.getCreatedFolderId(commonFolder[3], PageName.FundsPage, 30);
											if(fp.clickOnDeleteFolderButton(id)){
												if(click(driver, fp.getFolderDeleteYesBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, 30), "Yes Button", action.BOOLEAN)){
													appLog.info("Successfully deleted sub folder of common. Path: "+commonFolder[3]);
												} else {
													appLog.error("Cannot click on yes button, So cannot delete subfolder of common folder. Path: "+commonFolder[3]);
													sa.assertTrue(false,"Cannot click on yes button, So cannot delete subfolder of common folder. Path: "+commonFolder[3]);
												}
											} else {
												appLog.error("Cannot click on delete button, So cannot delete subfolder of common folder. Path: "+commonFolder[3]);
												sa.assertTrue(false,"Cannot click on delete button, So cannot delete subfolder of common folder. Path: "+commonFolder[3]);
											}
											
											id = fp.getCreatedFolderId(sharedFolder[1], PageName.FundsPage, 30);
											if(fp.clickOnDeleteFolderButton(id)){
												if(click(driver, fp.getFolderDeleteYesBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, 30), "Yes Button", action.BOOLEAN)){
													appLog.info("Successfully deleted sub folder of shared. Path: "+sharedFolder[1]);
												} else {
													appLog.error("Cannot click on yes button, So cannot delete subfolder of shared folder. Path: "+sharedFolder[1]);
													sa.assertTrue(false,"Cannot click on yes button, So cannot delete subfolder of shared folder. Path: "+sharedFolder[1]);
												}
											} else {
												appLog.error("Cannot click on delete button, So cannot delete subfolder of shared folder. Path: "+sharedFolder[1]);
												sa.assertTrue(false,"Cannot click on delete button, So cannot delete subfolder of shared folder. Path: "+sharedFolder[1]);
											}
											id = fp.getCreatedFolderId(sharedFolder[2], PageName.FundsPage, 30);
											if(fp.clickOnDeleteFolderButton(id)){
												if(click(driver, fp.getFolderDeleteYesBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, 30), "Yes Button", action.BOOLEAN)){
													appLog.info("Successfully deleted sub folder of shared. Path: "+sharedFolder[2]);
												} else {
													appLog.error("Cannot click on yes button, So cannot delete subfolder of shared folder. Path: "+sharedFolder[2]);
													sa.assertTrue(false,"Cannot click on yes button, So cannot delete subfolder of shared folder. Path: "+sharedFolder[2]);
												}
											} else {
												appLog.error("Cannot click on delete button, So cannot delete subfolder of shared folder. Path: "+sharedFolder[2]);
												sa.assertTrue(false,"Cannot click on delete button, So cannot delete subfolder of shared folder. Path: "+sharedFolder[2]);
											}
											id = fp.getCreatedFolderId(sharedFolder[3], PageName.FundsPage, 30);
											if(fp.clickOnDeleteFolderButton(id)){
												if(click(driver, fp.getFolderDeleteYesBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, 30), "Yes Button", action.BOOLEAN)){
													appLog.info("Successfully deleted sub folder of shared. Path: "+sharedFolder[3]);
												} else {
													appLog.error("Cannot click on yes button, So cannot delete subfolder of shared folder. Path: "+sharedFolder[3]);
													sa.assertTrue(false,"Cannot click on yes button, So cannot delete subfolder of shared folder. Path: "+sharedFolder[3]);
												}
											} else {
												appLog.error("Cannot click on delete button, So cannot delete subfolder of shared folder. Path: "+sharedFolder[3]);
												sa.assertTrue(false,"Cannot click on delete button, So cannot delete subfolder of shared folder. Path: "+sharedFolder[3]);
											}
											
											id = fp.getCreatedFolderId(internalFolder[1], PageName.FundsPage, 30);
											if(fp.clickOnDeleteFolderButton(id)){
												if(click(driver, fp.getFolderDeleteYesBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, 30), "Yes Button", action.BOOLEAN)){
													appLog.info("Successfully deleted sub folder of internal. Path: "+internalFolder[1]);
												} else {
													appLog.error("Cannot click on yes button, So cannot delete subfolder of internal folder. Path: "+internalFolder[1]);
													sa.assertTrue(false,"Cannot click on yes button, So cannot delete subfolder of internal folder. Path: "+internalFolder[1]);
												}
											} else {
												appLog.error("Cannot click on delete button, So cannot delete subfolder of internal folder. Path: "+internalFolder[1]);
												sa.assertTrue(false,"Cannot click on delete button, So cannot delete subfolder of internal folder. Path: "+internalFolder[1]);
											}
											id = fp.getCreatedFolderId(internalFolder[2], PageName.FundsPage, 30);
											if(fp.clickOnDeleteFolderButton(id)){
												if(click(driver, fp.getFolderDeleteYesBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, 30), "Yes Button", action.BOOLEAN)){
													appLog.info("Successfully deleted sub folder of internal. Path: "+internalFolder[2]);
												} else {
													appLog.error("Cannot click on yes button, So cannot delete subfolder of internal folder. Path: "+internalFolder[2]);
													sa.assertTrue(false,"Cannot click on yes button, So cannot delete subfolder of internal folder. Path: "+internalFolder[2]);
												}
											} else {
												appLog.error("Cannot click on delete button, So cannot delete subfolder of internal folder. Path: "+internalFolder[2]);
												sa.assertTrue(false,"Cannot click on delete button, So cannot delete subfolder of internal folder. Path: "+internalFolder[2]);
											}
											id = fp.getCreatedFolderId(internalFolder[3], PageName.FundsPage, 30);
											if(fp.clickOnDeleteFolderButton(id)){
												if(click(driver, fp.getFolderDeleteYesBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, 30), "Yes Button", action.BOOLEAN)){
													appLog.info("Successfully deleted sub folder of internal. Path: "+internalFolder[3]);
												} else {
													appLog.error("Cannot click on yes button, So cannot delete subfolder of internal folder. Path: "+internalFolder[3]);
													sa.assertTrue(false,"Cannot click on yes button, So cannot delete subfolder of internal folder. Path: "+internalFolder[3]);
												}
											} else {
												appLog.error("Cannot click on delete button, So cannot delete subfolder of internal folder. Path: "+internalFolder[3]);
												sa.assertTrue(false,"Cannot click on delete button, So cannot delete subfolder of internal folder. Path: "+internalFolder[3]);
											}
											
											id = fp.getCreatedFolderId(standardFolder[1], PageName.FundsPage, 30);
											if(fp.clickOnDeleteFolderButton(id)){
												if(click(driver, fp.getFolderDeleteYesBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, 30), "Yes Button", action.BOOLEAN)){
													appLog.info("Successfully deleted sub folder of standard. Path: "+standardFolder[1]);
												} else {
													appLog.error("Cannot click on yes button, So cannot delete subfolder of standard folder. Path: "+standardFolder[1]);
													sa.assertTrue(false,"Cannot click on yes button, So cannot delete subfolder of standard folder. Path: "+standardFolder[1]);
												}
											} else {
												appLog.error("Cannot click on delete button, So cannot delete subfolder of standard folder. Path: "+standardFolder[1]);
												sa.assertTrue(false,"Cannot click on delete button, So cannot delete subfolder of standard folder. Path: "+standardFolder[1]);
											}
											id = fp.getCreatedFolderId(standardFolder[2], PageName.FundsPage, 30);
											if(fp.clickOnDeleteFolderButton(id)){
												if(click(driver, fp.getFolderDeleteYesBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, 30), "Yes Button", action.BOOLEAN)){
													appLog.info("Successfully deleted sub folder of standard. Path: "+standardFolder[2]);
												} else {
													appLog.error("Cannot click on yes button, So cannot delete subfolder of standard folder. Path: "+standardFolder[2]);
													sa.assertTrue(false,"Cannot click on yes button, So cannot delete subfolder of standard folder. Path: "+standardFolder[2]);
												}
											} else {
												appLog.error("Cannot click on delete button, So cannot delete subfolder of standard folder. Path: "+standardFolder[2]);
												sa.assertTrue(false,"Cannot click on delete button, So cannot delete subfolder of standard folder. Path: "+standardFolder[2]);
											}
											id = fp.getCreatedFolderId(standardFolder[3], PageName.FundsPage, 30);
											if(fp.clickOnDeleteFolderButton(id)){
												if(click(driver, fp.getFolderDeleteYesBtn(Workspace.FundraisingWorkspace, PageName.FundsPage, 30), "Yes Button", action.BOOLEAN)){
													appLog.info("Successfully deleted sub folder of standard. Path: "+standardFolder[3]);
												} else {
													appLog.error("Cannot click on yes button, So cannot delete subfolder of standard folder. Path: "+standardFolder[3]);
													sa.assertTrue(false,"Cannot click on yes button, So cannot delete subfolder of standard folder. Path: "+standardFolder[3]);
												}
											} else {
												appLog.error("Cannot click on delete button, So cannot delete subfolder of standard folder. Path: "+standardFolder[3]);
												sa.assertTrue(false,"Cannot click on delete button, So cannot delete subfolder of standard folder. Path: "+standardFolder[3]);
											}
											
											//Rename parent folder
											
											id = fp.getCreatedFolderId(commonFolder[0], PageName.FundsPage, 30);
											if(fp.clickOnRenameFolderButton(id)){
												if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.FundsPage, 20), commonFolder[0].split("\\(")[0]+"Updated", commonFolder[0]+" folder text box", action.BOOLEAN)) {
													if(click(driver, fp.getParentRenameFolderSaveButton(Workspace.FundraisingWorkspace, PageName.BuildStep2Of3, 20), "folder save button", action.BOOLEAN)) {
														appLog.info(commonFolder[0]+"Folder Renamed successfully.");
													}else {
														appLog.error("Not able to click on folder "+commonFolder[0]+" save button so cannot rename the folder");
														sa.assertTrue(false, "Not able to click on folder "+commonFolder[0]+" save button so cannot rename the folder");
													}
												}else {
													appLog.error("Not able to pass value in folder "+commonFolder[0]+" so cannot rename the folder");
													sa.assertTrue(false, "Not able to pass value in folder "+commonFolder[0]+" so cannot rename the folder");
												}
											} else {
												appLog.error("Cannot click on Rename button, So cannot Rename Parent folder of Common folder. Path: "+commonFolder[0]);
												sa.assertTrue(false,"Cannot click on rename button, So cannot Rename Parent folder of common folder. Path: "+commonFolder[0]);
											}
											
											id = fp.getCreatedFolderId(sharedFolder[0], PageName.FundsPage, 30);
											if(fp.clickOnRenameFolderButton(id)){
												if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.FundsPage, 20), sharedFolder[0].split("\\(")[0]+"Updated", sharedFolder[0]+" folder text box", action.BOOLEAN)) {
													if(click(driver, fp.getParentRenameFolderSaveButton(Workspace.FundraisingWorkspace, PageName.BuildStep2Of3, 20), "folder save button", action.BOOLEAN)) {
														appLog.info(sharedFolder[0]+"Folder Renamed successfully.");
													}else {
														appLog.error("Not able to click on folder "+sharedFolder[0]+" save button so cannot rename the folder");
														sa.assertTrue(false, "Not able to click on folder "+sharedFolder[0]+" save button so cannot rename the folder");
													}
												}else {
													appLog.error("Not able to pass value in folder "+sharedFolder[0]+" so cannot rename the folder");
													sa.assertTrue(false, "Not able to pass value in folder "+sharedFolder[0]+" so cannot rename the folder");
												}
											} else {
												appLog.error("Cannot click on Rename button, So cannot Rename Parent folder of shared folder. Path: "+sharedFolder[0]);
												sa.assertTrue(false,"Cannot click on rename button, So cannot Rename Parent folder of shared folder. Path: "+sharedFolder[0]);
											}
											
											id = fp.getCreatedFolderId(internalFolder[0], PageName.FundsPage, 30);
											if(fp.clickOnRenameFolderButton(id)){
												if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.FundsPage, 20), internalFolder[0].split("\\(")[0]+"Updated", internalFolder[0]+" folder text box", action.BOOLEAN)) {
													if(click(driver, fp.getParentRenameFolderSaveButton(Workspace.FundraisingWorkspace, PageName.BuildStep2Of3, 20), "folder save button", action.BOOLEAN)) {
														appLog.info(internalFolder[0]+"Folder Renamed successfully.");
													}else {
														appLog.error("Not able to click on folder "+internalFolder[0]+" save button so cannot rename the folder");
														sa.assertTrue(false, "Not able to click on folder "+internalFolder[0]+" save button so cannot rename the folder");
													}
												}else {
													appLog.error("Not able to pass value in folder "+internalFolder[0]+" so cannot rename the folder");
													sa.assertTrue(false, "Not able to pass value in folder "+internalFolder[0]+" so cannot rename the folder");
												}
											} else {
												appLog.error("Cannot click on Rename button, So cannot Rename Parent folder of internal folder. Path: "+internalFolder[0]);
												sa.assertTrue(false,"Cannot click on rename button, So cannot Rename Parent folder of internal folder. Path: "+internalFolder[0]);
											}
											
											id = fp.getCreatedFolderId(standardFolder[0], PageName.FundsPage, 30);
											if(fp.clickOnRenameFolderButton(id)){
												if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.FundsPage, 20), standardFolder[0]+"Updated", standardFolder[0]+" folder text box", action.BOOLEAN)) {
													if(click(driver, fp.getParentRenameFolderSaveButton(Workspace.FundraisingWorkspace, PageName.BuildStep2Of3, 20), "folder save button", action.BOOLEAN)) {
														appLog.info(standardFolder[0]+"Folder Renamed successfully.");
													}else {
														appLog.error("Not able to click on folder "+standardFolder[0]+" save button so cannot rename the folder");
														sa.assertTrue(false, "Not able to click on folder "+standardFolder[0]+" save button so cannot rename the folder");
													}
												}else {
													appLog.error("Not able to pass value in folder "+standardFolder[0]+" so cannot rename the folder");
													sa.assertTrue(false, "Not able to pass value in folder "+standardFolder[0]+" so cannot rename the folder");
												}
											} else {
												appLog.error("Cannot click on Rename button, So cannot Rename Parent folder of standard folder. Path: "+standardFolder[0]);
												sa.assertTrue(false,"Cannot click on rename button, So cannot Rename Parent folder of standard folder. Path: "+standardFolder[0]);
											}
											
											if(click(driver, fp.getNext2Of3Button(Workspace.FundraisingWorkspace, 30), "Next Button", action.BOOLEAN)){
												if(click(driver, fp.getDone3Of3Button(Workspace.FundraisingWorkspace, 30), "Done button", action.BOOLEAN)){
													if(click(driver, fp.getCongratulationsCloseButton(Workspace.FundraisingWorkspace, 30), "Congratualtions pop close button", action.BOOLEAN)){
														appLog.info("Workspace Created Successfully.");
													} else {
														fp.recover(Workspace.FundraisingWorkspace, 30);
													}
													
													if(fp.verifyFolderPathdummy(commonFolder[0].split("\\(")[0]+"Updated (Common)", null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
														appLog.info(commonFolder[0].split("\\(")[0]+"Updated (Common)"+" Folder path is verified.");
														if(!fp.verifyFolderPathdummy(commonFolder[0].split("\\(")[0]+"Updated (Common)/"+commonFolder[1].split("/")[1], null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
															appLog.info(commonFolder[0].split("\\(")[0]+"Updated (Common)/"+commonFolder[1].split("/")[1]+" Folder is not present and is verified.");
														} else {
															appLog.error(commonFolder[0].split("\\(")[0]+"Updated (Common)/"+commonFolder[1].split("/")[1]+" Folder path is present.");
															sa.assertTrue(false,commonFolder[0].split("\\(")[0]+"Updated (Common)/"+commonFolder[1].split("/")[1]+" Folder path is present.");
														}
													} else {
														appLog.error(commonFolder[0].split("\\(")[0]+"Updated (Common)"+" Folder path is not verified.");
														sa.assertTrue(false,commonFolder[0].split("\\(")[0]+"Updated (Common)"+" Folder path is not verified.");
													}
													
													if(fp.verifyFolderPathdummy(sharedFolder[0].split("\\(")[0]+"Updated (Shared)", null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
														appLog.info(sharedFolder[0].split("\\(")[0]+"Updated (Shared)"+" Folder path is verified.");
														if(!fp.verifyFolderPathdummy(sharedFolder[0].split("\\(")[0]+"Updated (Shared)/"+sharedFolder[1].split("/")[1], null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
															appLog.info(sharedFolder[0].split("\\(")[0]+"Updated (Shared)/"+sharedFolder[1].split("/")[1]+" Folder path is not present and is verified.");
														} else {
															appLog.error(sharedFolder[0].split("\\(")[0]+"Updated (Shared)/"+sharedFolder[1].split("/")[1]+" Folder path is present.");
															sa.assertTrue(false,sharedFolder[0].split("\\(")[0]+"Updated (Shared)/"+sharedFolder[1].split("/")[1]+" Folder path is present.");
														}
													} else {
														appLog.error(sharedFolder[0].split("\\(")[0]+"Updated (Shared)"+" Folder path is not verified.");
														sa.assertTrue(false,sharedFolder[0].split("\\(")[0]+"Updated (Shared)"+" Folder path is not verified.");
													}
													
													if(fp.verifyFolderPathdummy(internalFolder[0].split("\\(")[0]+"Updated (Internal)", null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
														appLog.info(internalFolder[0].split("\\(")[0]+"Updated (Internal)"+" Folder path is verified.");
														if(!fp.verifyFolderPathdummy(internalFolder[0].split("\\(")[0]+"Updated (Internal)/"+internalFolder[1].split("/")[1], null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
															appLog.info(internalFolder[0].split("\\(")[0]+"Updated (Internal)/"+internalFolder[1].split("/")[1]+" Folder path is not present and is verified.");
														} else {
															appLog.error(internalFolder[0].split("\\(")[0]+"Updated (Internal)/"+internalFolder[1].split("/")[1]+" Folder path is present.");
															sa.assertTrue(false,internalFolder[0].split("\\(")[0]+"Updated (Internal)/"+internalFolder[1].split("/")[1]+" Folder path is  present.");
														}
													} else {
														appLog.error(internalFolder[0].split("\\(")[0]+"Updated (Internal)"+" Folder path is not verified.");
														sa.assertTrue(false,internalFolder[0].split("\\(")[0]+"Updated (Internal)"+" Folder path is not verified.");
													}
													
													//investment info pop up fundraising worksapce
													
													if(click(driver, fp.getInvestmentInfoLink(Workspace.FundraisingWorkspace, M8F5, 30), "Investment info link", action.BOOLEAN)){
														String text  = getText(driver, fp.getInvestmentInfoName(30), "Name", action.BOOLEAN).trim();
														if(text.equalsIgnoreCase(M8F5)){
															appLog.info("Fund Name is verified on invesment info pop up.");
														} else {
															appLog.error("Fund name is not correct on investment info pop up. Expected: "+M8F5+"\tActual: "+text);
															sa.assertTrue(false,"Fund name is not correct on investment info pop up. Expected: "+M8F5+"\tActual: "+text);
														}
														text = getText(driver, fp.getInvestmentInfoSize(30), "Size", action.BOOLEAN);
														if(text.equalsIgnoreCase("5000.00")){
															appLog.info("Fund Size is verified on invesment info pop up.");
														} else {
															appLog.error("Fund Size is not correct on investment info pop up. Expected: 5000.00\tActual: "+text);
															sa.assertTrue(false,"Fund Size is not correct on investment info pop up. Expected: 5000.00\tActual: "+text);
														}
														text = getText(driver, fp.getInvestmentInfoVintageYear(30), "Vintage Year", action.BOOLEAN);
														if(text.equalsIgnoreCase("2015")){
															appLog.info("Fund Vintage year is verified on invesment info pop up.");
														} else {
															appLog.error("Fund Vintage year is not correct on investment info pop up. Expected: 2013\tActual: "+text);
															sa.assertTrue(false,"Fund Vintage year is not correct on investment info pop up. Expected: 2013\tActual: "+text);
														}
														text = getText(driver, fp.getInvestmentInfoContact(30), "Contact Name", action.BOOLEAN);
														if(text.equalsIgnoreCase("Contact1")){
															appLog.info("Fund Contact Name is verified on invesment info pop up.");
														} else {
															appLog.error("Fund Contact Name is not correct on investment info pop up. Expected: Contact1\tActual: "+text);
															sa.assertTrue(false,"Fund Contact Name is not correct on investment info pop up. Expected: Contact1\tActual: "+text);
														}
														text = getText(driver, fp.getInvestmentInfoPhone(30), "Contact Phone", action.BOOLEAN);
														if(text.equalsIgnoreCase("987654321")){
															appLog.info("Fund Phone is verified on invesment info pop up.");
														} else {
															appLog.error("Fund Phone is not correct on investment info pop up. Expected: 987654321\tActual: "+text);
															sa.assertTrue(false,"Fund Phone is not correct on investment info pop up. Expected: 987654321\tActual: "+text);
														}
														text = getText(driver, fp.getInvestmentInfoEmail(30), "Contact Email", action.BOOLEAN);
														if(text.equalsIgnoreCase("abc@abc.com")){
															appLog.info("Fund Email is verified on invesment info pop up.");
														} else {
															appLog.error("Fund Email is not correct on investment info pop up. Expected: abc@abc.com\tActual: "+text);
															sa.assertTrue(false,"Fund Email is not correct on investment info pop up. Expected: abc@abc.com\tActual: "+text);
														}
														text = getText(driver, fp.getInvestmentInfoDescription(30), "Fund Description", action.BOOLEAN);
														if(text.equalsIgnoreCase("Sample description")){
															appLog.info("Fund Description is verified on invesment info pop up.");
														} else {
															appLog.error("Fund Description is not correct on investment info pop up. Expected: Sample description\tActual: "+text);
															sa.assertTrue(false,"Fund Description is not correct on investment info pop up. Expected: Sample description\tActual: "+text);
														}
														if(isSelected(driver, fp.getInvestmentInfoFundraisingYesRadioButton(30), "Yes Radio Button")){
															appLog.info("Yes is selected and is verified.");
														} else {
															appLog.error("Yes is not selected on investment info pop up.");
															sa.assertTrue(false,"Yes is not selected on investment info pop up.");
														}
												
													} else {
														appLog.error("Investment info link cannot be clicked for fundraising workspace, SO cannot verify the field data.");
														sa.assertTrue(false,"Investment info link cannot be clicked for fundraising workspace, SO cannot verify the field data.");
													}
													
													//investment info link investor workspace
													
													if(click(driver, fp.getInvestmentInfoLink(Workspace.InvestorWorkspace, M8F5, 30), "Investment info link", action.BOOLEAN)){
														String text  = getText(driver, fp.getInvestmentInfoName(30), "Name", action.BOOLEAN).trim();
														if(text.equalsIgnoreCase(M8F5)){
															appLog.info("Fund Name is verified on invesment info pop up.");
														} else {
															appLog.error("Fund name is not correct on investment info pop up. Expected: "+M8F5+"\tActual: "+text);
															sa.assertTrue(false,"Fund name is not correct on investment info pop up. Expected: "+M8F5+"\tActual: "+text);
														}
														text = getText(driver, fp.getInvestmentInfoSize(30), "Size", action.BOOLEAN);
														if(text.equalsIgnoreCase("5000.00")){
															appLog.info("Fund Size is verified on invesment info pop up.");
														} else {
															appLog.error("Fund Size is not correct on investment info pop up. Expected: 5000.00\tActual: "+text);
															sa.assertTrue(false,"Fund Size is not correct on investment info pop up. Expected: 5000.00\tActual: "+text);
														}
														text = getText(driver, fp.getInvestmentInfoVintageYear(30), "Vintage Year", action.BOOLEAN);
														if(text.equalsIgnoreCase("2015")){
															appLog.info("Fund Vintage year is verified on invesment info pop up.");
														} else {
															appLog.error("Fund Vintage year is not correct on investment info pop up. Expected: 2013\tActual: "+text);
															sa.assertTrue(false,"Fund Vintage year is not correct on investment info pop up. Expected: 2013\tActual: "+text);
														}
														text = getText(driver, fp.getInvestmentInfoContact(30), "Contact Name", action.BOOLEAN);
														if(text.equalsIgnoreCase("Contact1")){
															appLog.info("Fund Contact Name is verified on invesment info pop up.");
														} else {
															appLog.error("Fund Contact Name is not correct on investment info pop up. Expected: Contact1\tActual: "+text);
															sa.assertTrue(false,"Fund Contact Name is not correct on investment info pop up. Expected: Contact1\tActual: "+text);
														}
														text = getText(driver, fp.getInvestmentInfoPhone(30), "Contact Phone", action.BOOLEAN);
														if(text.equalsIgnoreCase("987654321")){
															appLog.info("Fund Phone is verified on invesment info pop up.");
														} else {
															appLog.error("Fund Phone is not correct on investment info pop up. Expected: 987654321\tActual: "+text);
															sa.assertTrue(false,"Fund Phone is not correct on investment info pop up. Expected: 987654321\tActual: "+text);
														}
														text = getText(driver, fp.getInvestmentInfoEmail(30), "Contact Email", action.BOOLEAN);
														if(text.equalsIgnoreCase("abc@abc.com")){
															appLog.info("Fund Email is verified on invesment info pop up.");
														} else {
															appLog.error("Fund Email is not correct on investment info pop up. Expected: abc@abc.com\tActual: "+text);
															sa.assertTrue(false,"Fund Email is not correct on investment info pop up. Expected: abc@abc.com\tActual: "+text);
														}
														text = getText(driver, fp.getInvestmentInfoDescription(30), "Fund Description", action.BOOLEAN);
														if(text.equalsIgnoreCase("Sample description")){
															appLog.info("Fund Description is verified on invesment info pop up.");
														} else {
															appLog.error("Fund Description is not correct on investment info pop up. Expected: Sample description\tActual: "+text);
															sa.assertTrue(false,"Fund Description is not correct on investment info pop up. Expected: Sample description\tActual: "+text);
														}
														if(isSelected(driver, fp.getInvestmentInfoFundraisingYesRadioButton(30), "Yes Radio Button")){
															appLog.info("Yes is selected and is verified.");
														} else {
															appLog.error("Yes is not selected on investment info pop up.");
															sa.assertTrue(false,"Yes is not selected on investment info pop up.");
														}
												
													} else {
														appLog.error("Investment info link cannot be clicked for investor workspace, SO cannot verify the field data.");
														sa.assertTrue(false,"Investment info link cannot be clicked for investor workspace, SO cannot verify the field data.");
													}
												} else {
													appLog.error("Cannot click on done btton, So cannot continue with tc.");
													sa.assertTrue(false,"Cannot click on done btton, So cannot continue with tc.");
												}
											} else {
												appLog.error("Cannot click on next button, So cannot continue with tc.");
												sa.assertTrue(false,"Cannot click on next button, So cannot continue with tc.");
											}
										} else {
											appLog.error("Cannot click on import button so cannot import folder template.");
											sa.assertTrue(false,"Cannot click on import button so cannot import folder template.");
										}
									} else {
										appLog.error(folderTemplateName+" template is not available to import, So canot import folder template.");
										sa.assertTrue(false,folderTemplateName+" template is not available to import, So canot import folder template.");
									}
								} else {
									appLog.error("Import folder template button cannot be clicked, So cannnot import folder template.");
									sa.assertTrue(false,"Import folder template button cannot be clicked, So cannnot import folder template.");
								}
								
							} else {
								appLog.error("Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify folder delete functionality.");
								sa.assertTrue(false,"Step 2 of 3 pop up is not opening after clicking on the next button of step 1 of 3, So cannot verify folder delete functionality.");
								fp.recover(Workspace.FundraisingWorkspace, 30);
							}
						} else {
							appLog.error("Next button of step 1 of 3 cannot be click, So cannot verify folder delete functionality.");
							sa.assertTrue(false,"Next button of step 1 of 3 cannot be click, So cannot verify folder delete functionality.");
							fp.recover(Workspace.FundraisingWorkspace, 30);
						}
					} else {
						appLog.error("One of the mandatory field is not filled, So cannot verify folder delete functionality.");
						sa.assertTrue(false,"One of the mandatory field is not filled, So cannot verify folder delete functionality.");
						fp.recover(Workspace.FundraisingWorkspace, 30);
					}
				} else {
					appLog.error("Build Fundraising workspace button cannot be clicked, So cannot verify folder delete functionality.");
					sa.assertTrue(false,"Build Fundraising workspace button cannot be clicked, So cannot verify folder delete functionality.");
					fp.recover(Workspace.FundraisingWorkspace, 30);
				}
			} else {
				appLog.error(M8F5+" fund is not available, so cannot continue with the tc.");
				sa.assertTrue(false,M8F5+" fund is not available, so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot continue with the tc.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	
	}
	
	@Test
	public void M8tc030_UpdateFundInfoViaInvestmentInfoLinkAndVerifyBuildInvestorWorkspace(){


		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		boolean flag = true;
		
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M8F3)){
				switchToFrame(driver, 30, fp.getFrame(PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Funcraising wokspace view.");
				if(click(driver, fp.getInvestmentInfoLink(Workspace.FundraisingWorkspace, M8F3, 30), "Investment info link", action.BOOLEAN)){
					if(click(driver, fp.getInvestmentInfoEditIcon(30), "Edit icon", action.BOOLEAN)){
						if(!clearTextBox(fp.getInvestmentInfoFundNameTxtbox(30))){
							appLog.error("Not able to clear fund name text box, So cannot check error message under it.");
							sa.assertTrue(false,"Not able to clear fund name text box, So cannot check error message under it.");
						}
						if(!clearTextBox(fp.getInvestmentInfoEditContactName(30))){
							appLog.error("Not able to clear fund contact name text box, So cannot check error message under it.");
							sa.assertTrue(false,"Not able to clear fund contact name text box, So cannot check error message under it.");
						}
						if(!clearTextBox(fp.getInvestmentInfoEditEmail(30))){
							appLog.error("Not able to clear fund email text box, So cannot check error message under it.");
							sa.assertTrue(false,"Not able to clear fund email text box, So cannot check error message under it.");
						}
						if(!clearTextBox(fp.getInvestmentInfoEditPhone(30))){
							appLog.error("Not able to clear fund Phone text box, So cannot check error message under it.");
							sa.assertTrue(false,"Not able to clear fund Phone text box, So cannot check error message under it.");
						}

						if(click(driver, fp.getInvestmentInfoSaveBtn(30), "Save Button", action.BOOLEAN)){
							String text = getText(driver, fp.getInvestmentInfoEditFundNameEmptyErrorMessage(30), "", action.BOOLEAN);
							if(text.trim().equalsIgnoreCase(FundsPageErrorMessage.InvestmentInfoEditFundNamePleaseEnterTextErrorMessage)){
								appLog.info("Error message is verified.");
							} else {
								appLog.error("Please enter value error message for fund name textbox is not verified. expected: "+FundsPageErrorMessage.InvestmentInfoEditFundNamePleaseEnterTextErrorMessage+"\tActual: "+text);
								sa.assertTrue(false,"Please enter value error message for fund name textbox is not verified. expected: "+FundsPageErrorMessage.InvestmentInfoEditFundNamePleaseEnterTextErrorMessage+"\tActual: "+text);
							}
							text = getText(driver, fp.getInvestmentInfoEditFundContactEmptyErrorMessage(30), "", action.BOOLEAN);
							if(text.trim().equalsIgnoreCase(FundsPageErrorMessage.InvestmentInfoEditFundContactNamePleaseEnterTextErrorMessage)){
								appLog.info("Error message is verified.");
							} else {
								appLog.error("Please enter value error message for fund contact name textbox is not verified. expected: "+FundsPageErrorMessage.InvestmentInfoEditFundContactNamePleaseEnterTextErrorMessage+"\tActual: "+text);
								sa.assertTrue(false,"Please enter value error message for fund contact name textbox is not verified. expected: "+FundsPageErrorMessage.InvestmentInfoEditFundContactNamePleaseEnterTextErrorMessage+"\tActual: "+text);
							}
							text = getText(driver, fp.getInvestmentInfoEditFundPhoneEmptyErrorMessage(30), "", action.BOOLEAN);
							if(text.trim().equalsIgnoreCase(FundsPageErrorMessage.InvestmentInfoEditFundPhonePleaseEnterTextErrorMessage)){
								appLog.info("Error message is verified.");
							} else {
								appLog.error("Please enter value error message for fund Phone textbox is not verified. expected: "+FundsPageErrorMessage.InvestmentInfoEditFundPhonePleaseEnterTextErrorMessage+"\tActual: "+text);
								sa.assertTrue(false,"Please enter value error message for fund Phone textbox is not verified. expected: "+FundsPageErrorMessage.InvestmentInfoEditFundPhonePleaseEnterTextErrorMessage+"\tActual: "+text);
							}
							text = getText(driver, fp.getInvestmentInfoEditFundEmailEmptyErrorMessage(30), "", action.BOOLEAN);
							if(text.trim().equalsIgnoreCase(FundsPageErrorMessage.InvestmentInfoEditFundEmailPleaseEnterTextErrorMessage)){
								appLog.info("Error message is verified.");
							} else {
								appLog.error("Please enter value error message for fund Email textbox is not verified. expected: "+FundsPageErrorMessage.InvestmentInfoEditFundEmailPleaseEnterTextErrorMessage+"\tActual: "+text);
								sa.assertTrue(false,"Please enter value error message for fund Email textbox is not verified. expected: "+FundsPageErrorMessage.InvestmentInfoEditFundEmailPleaseEnterTextErrorMessage+"\tActual: "+text);
							}
						} else {
							appLog.error("Save button cannot be clicked, So cannot Check Empty error messages.");
							sa.assertTrue(false,"Save button cannot be clicked, So cannot continue with the tc.");
						}
						if(sendKeys(driver, fp.getInvestmentInfoFundNameTxtbox(30), "FundName {}[]\\/|*:<>", "Fund name text box", action.BOOLEAN)){
							appLog.info("Successfully passed value to fund naem text box");
						} else {
							appLog.error("NOt able to pass value to name field.");
							sa.assertTrue(false,"NOt able to pass value to name field.");
						}
						if(sendKeys(driver, fp.getInvestmentInfoEditSize(30), "abc", "Size", action.BOOLEAN)){
							appLog.info("Succesfully passed value to fund size.");
						} else {
							appLog.error("Not able to pass value to size field.");
							sa.assertTrue(false,"Not able to pass value to size field.");
						}
						if(sendKeys(driver, fp.getInvestmentInfoEditVintageYear(30), "abc", "Vintage year", action.BOOLEAN)){
							appLog.info("Succesfully passed value to fund vintage year.");
						} else {
							appLog.error("Not able to pass value to  vintage year field.");
							sa.assertTrue(false,"Not able to pass value to  vintage year field.");
						}
						if(sendKeys(driver, fp.getInvestmentInfoEditContactName(30), "Contact1", "Contact NAme", action.BOOLEAN)){
							appLog.info("Succesfully passed value to fund Contact Name.");
						} else {
							appLog.error("Not able to pass value to  Contact Name field.");
							sa.assertTrue(false,"Not able to pass value to  Contact Name field.");
						}
						if(sendKeys(driver, fp.getInvestmentInfoEditPhone(30), "Contact1", "Phone", action.BOOLEAN)){
							appLog.info("Succesfully passed value to fund Phone.");
						} else {
							appLog.error("Not able to pass value to  Phone field.");
							sa.assertTrue(false,"Not able to pass value to Phone field.");
						}
						if(sendKeys(driver, fp.getInvestmentInfoEditEmail(30), "Contact1", "Email", action.BOOLEAN)){
							appLog.info("Succesfully passed value to fund Email.");
						} else {
							appLog.error("Not able to pass value to  Email field.");
							sa.assertTrue(false,"Not able to pass value to Email field.");
						}
						if(sendKeys(driver, fp.getInvestmentInfoEditDescription(30), "Sample Description", "Description", action.BOOLEAN)){
							appLog.info("Succesfully passed value to fund Description.");
						} else {
							appLog.error("Not able to pass value to  Description field.");
							sa.assertTrue(false,"Not able to pass value to Description field.");
						}
						if(click(driver, fp.getInvestmentInfoSaveBtn(30), "Save Button", action.BOOLEAN)){
							String text = getText(driver, fp.getInvestmentInfoEditInvalidFundNameErrorMessage(30), "", action.BOOLEAN);
							if(text.trim().equalsIgnoreCase(FundsPageErrorMessage.InvestmentInfoEditInvalidFundNameErrorMessage)){
								appLog.info("Invalid data error message is verified.");
							} else {
								appLog.error("Invalid fund name error message is not verified. Expected: "+FundsPageErrorMessage.InvestmentInfoEditInvalidFundNameErrorMessage+"\tActual: "+text);
								sa.assertTrue(false,"Invalid fund name error message is not verified. Expected: "+FundsPageErrorMessage.InvestmentInfoEditInvalidFundNameErrorMessage+"\tActual: "+text);
							}
							
							text = getText(driver, fp.getInvestmentInfoEditInvalidFundPhoneErrorMessage(30), "", action.BOOLEAN);
							if(text.trim().equalsIgnoreCase(FundsPageErrorMessage.InvestmentInfoEditInvalidFundPhoneErrorMessage)){
								appLog.info("Invalid data error message is verified.");
							} else {
								appLog.error("Invalid fund Phone error message is not verified. Expected: "+FundsPageErrorMessage.InvestmentInfoEditInvalidFundPhoneErrorMessage+"\tActual: "+text);
								sa.assertTrue(false,"Invalid fund Phone error message is not verified. Expected: "+FundsPageErrorMessage.InvestmentInfoEditInvalidFundPhoneErrorMessage+"\tActual: "+text);
							}
							text = getText(driver, fp.getInvestmentInfoEditInvalidFundEmailErrorMessage(30), "", action.BOOLEAN);
							if(text.trim().equalsIgnoreCase(FundsPageErrorMessage.InvestmentInfoEditInvalidFundEmailErrorMessage)){
								appLog.info("Invalid data error message is verified.");
							} else {
								appLog.error("Invalid fund Email error message is not verified. Expected: "+FundsPageErrorMessage.InvestmentInfoEditInvalidFundEmailErrorMessage+"\tActual: "+text);
								sa.assertTrue(false,"Invalid fund Email error message is not verified. Expected: "+FundsPageErrorMessage.InvestmentInfoEditInvalidFundEmailErrorMessage+"\tActual: "+text);
							}
						} else {
							appLog.error("Save button cannot be clicked, So cannot verify invalid data error message.");
							sa.assertTrue(false,"Save button cannot be clicked, So cannot verify invalid data error message.");
						}
						
						if(sendKeys(driver, fp.getInvestmentInfoFundNameTxtbox(30), M8F3+"Updated", "Fund name text box", action.BOOLEAN)){
							appLog.info("Successfully passed value to fund naem text box");
						} else {
							appLog.error("NOt able to pass value to name field.");
							sa.assertTrue(false,"NOt able to pass value to name field.");
						}
						if(sendKeys(driver, fp.getInvestmentInfoEditSize(30), "6000", "Size", action.BOOLEAN)){
							appLog.info("Succesfully passed value to fund size.");
						} else {
							appLog.error("Not able to pass value to size field.");
							sa.assertTrue(false,"Not able to pass value to size field.");
						}
						if(sendKeys(driver, fp.getInvestmentInfoEditVintageYear(30), "2018", "Vintage year", action.BOOLEAN)){
							appLog.info("Succesfully passed value to fund vintage year.");
						} else {
							appLog.error("Not able to pass value to  vintage year field.");
							sa.assertTrue(false,"Not able to pass value to  vintage year field.");
						}
						if(sendKeys(driver, fp.getInvestmentInfoEditContactName(30), "Contact2", "Contact NAme", action.BOOLEAN)){
							appLog.info("Succesfully passed value to fund Contact Name.");
						} else {
							appLog.error("Not able to pass value to  Contact Name field.");
							sa.assertTrue(false,"Not able to pass value to  Contact Name field.");
						}
						if(sendKeys(driver, fp.getInvestmentInfoEditPhone(30), "1234567890", "Phone", action.BOOLEAN)){
							appLog.info("Succesfully passed value to fund Phone.");
						} else {
							appLog.error("Not able to pass value to  Phone field.");
							sa.assertTrue(false,"Not able to pass value to Phone field.");
						}
						if(sendKeys(driver, fp.getInvestmentInfoEditEmail(30), "abc@bcd.com", "Email", action.BOOLEAN)){
							appLog.info("Succesfully passed value to fund Email.");
						} else {
							appLog.error("Not able to pass value to  Email field.");
							sa.assertTrue(false,"Not able to pass value to Email field.");
						}
						if(sendKeys(driver, fp.getInvestmentInfoEditDescription(30), "Sample Description 2", "Description", action.BOOLEAN)){
							appLog.info("Succesfully passed value to fund Description.");
						} else {
							appLog.error("Not able to pass value to  Description field.");
							sa.assertTrue(false,"Not able to pass value to Description field.");
						}
						
						if(click(driver, fp.getInvestmentInfoSaveBtn(30), "Save Button", action.BOOLEAN)){
							if(click(driver, fp.getInvestmentInfoEditCloseButton(30), "Close button", action.BOOLEAN)){
								if(click(driver, fp.getBuildWorkspaceButton(Workspace.InvestorWorkspace, 30), "Investor workspace", action.BOOLEAN)){
									String text = getAttribute(driver, fp.getFundNameField(Workspace.InvestorWorkspace, 30), "", "value");
									if(text.trim().equalsIgnoreCase(M8F3+"Updated")){
										appLog.info("Fund name is verified.");
									} else {
										appLog.error("Fund name is not verified on build process. Expected: "+M8F3+"Updated\tActual: "+text);
										sa.assertTrue(false,"Fund name is not verified on build process. Expected: "+M8F3+"Updated\tActual: "+text);
										
									}
									text = getAttribute(driver, fp.getFundSizeField(Workspace.InvestorWorkspace, 30), "", "value");
									if(text.trim().equalsIgnoreCase("6000.00")){
										appLog.info("Fund size is verified.");
									} else {
										appLog.error("Fund size is not verified on build process. Expected: "+M8F3+"Updated\tActual: "+text);
										sa.assertTrue(false,"Fund size is not verified on build process. Expected: 6000.00\tActual: "+text);
									}
									text = getAttribute(driver, fp.getFundVintageYearField(Workspace.InvestorWorkspace, 30), "", "value");
									if(text.trim().equalsIgnoreCase("2018")){
										appLog.info("Fund vintage year is verified.");
									} else {
										appLog.error("Fund vintage year is not verified on build process. Expected: 2018\tActual: "+text);
										sa.assertTrue(false,"Fund vintage year is not verified on build process. Expected: 2018\tActual: "+text);
										
									}
									
									text = getAttribute(driver, fp.getFundContactField(Workspace.InvestorWorkspace, 30), "", "value");
									if(text.trim().equalsIgnoreCase("Contact2")){
										appLog.info("Fund Contact is verified.");
									} else {
										appLog.error("Fund Contact is not verified on build process. Expected: Contact2\tActual: "+text);
										sa.assertTrue(false,"Fund contact is not verified on build process. Expected: Contact2\tActual: "+text);
										
									}
									
									text = getAttribute(driver, fp.getFundPhoneField(Workspace.InvestorWorkspace, 30), "", "value");
									if(text.trim().equalsIgnoreCase("1234567890")){
										appLog.info("Fund Phone is verified.");
									} else {
										appLog.error("Fund Phone is not verified on build process. Expected: 1234567890\tActual: "+text);
										sa.assertTrue(false,"Fund Phone is not verified on build process. Expected: 1234567890\tActual: "+text);
										
									}
									
									text = getAttribute(driver, fp.getFundEmailField(Workspace.InvestorWorkspace, 30), "", "value");
									if(text.trim().equalsIgnoreCase("abc@bcd.com")){
										appLog.info("Fund Email is verified.");
									} else {
										appLog.error("Fund Email is not verified on build process. Expected: abc@bcd.com\tActual: "+text);
										sa.assertTrue(false,"Fund Email is not verified on build process. Expected: abc@bcd.com\tActual: "+text);
										
									}
									
									text = getAttribute(driver, fp.getFundDescriptionField(Workspace.InvestorWorkspace, 30), "", "value");
									if(text.trim().equalsIgnoreCase("Sample Description 2")){
										appLog.info("Fund Description is verified.");
									} else {
										appLog.error("Fund Description is not verified on build process. Expected: Sample Description 2\tActual: "+text);
										sa.assertTrue(false,"Fund Description is not verified on build process. Expected: Sample Description 2\tActual: "+text);
										
									}
									
								} else {
									appLog.error("Build investor workspace button cannot be clicked, So cannot check data on build process.");
									sa.assertTrue(false,"Build investor workspace button cannot be clicked, So cannot check data on build process.");
								}
							} else {
								appLog.error("Close button cannot be clicked, So cannot check data on build process.");
								sa.assertTrue(false,"Close button cannot be clicked, So cannot check data on build process.");
							}
						} else {
							appLog.error("Save button cannot be clicked, So cannot check data on build process.");
							sa.assertTrue(false,"Save button cannot be clicked, So cannot check data on build process.");
						}
					} else {
						appLog.error("Investment info popup edit icon cannot be clicked, So cannot continue with tc.");
						sa.assertTrue(false,"Investment info popup edit icon cannot be clicked, So cannot continue with tc.");
					}
				} else {
					appLog.error("Investment info link cannot be clikced, So cannot continue with tc.");
					sa.assertTrue(false,"Investment info link cannot be clikced, So cannot continue with tc.");
				}
			} else {
				appLog.error(M8F5+" fund is not available, so cannot continue with the tc.");
				sa.assertTrue(false,M8F5+" fund is not available, so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, SO cannot continue with the tc.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	
	
	}
	
	static String filterPath = System.getProperty("user.dir") + "/TestCases_Filter.xlsx";
	
	@Test
	public void M8tc031_CheckErrorMessgeInStep3PopUpAndFunctionalityOfAddRowLink(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert sa=new SoftAssert();
		lp.CRMLogin(superAdminUserName, adminPassword);
		if(bp.clickOnTab(TabName.FundsTab)){
			if (fp.clickOnCreatedFund(ExcelUtils.readData(filterPath,"Funds", 1, 0))) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));				
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace.toString()+" View.");
			if(fp.clearWorkSpace(Workspace.FundraisingWorkspace, 60)){
				appLog.info("Workspace get cleared successfully");
			}else{
				appLog.error("Not able to clear workspace ");
				sa.assertTrue(false, "Not able to clear workspace");
			}			
			}else{
				appLog.error("Not able ot click on created funs");
				sa.assertTrue(false, "Not able ot click on created fund");
			}
		}else{
			appLog.error("Not able to click on funds tab");
			sa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		 lp = new LoginPageBusinessLayer(driver);
		 bp = new BasePageBusinessLayer(driver);
		 fp = new FundsPageBusinessLayer(driver);
		 sa=new SoftAssert();
		 String fundSize=  ExcelUtils.readData(filterPath,"Funds", 1, 3);
		 String fundVintageYear=ExcelUtils.readData(filterPath,"Funds", 1, 4);
		 String fundContact=ExcelUtils.readData(filterPath,"Funds", 1, 5);
		 String fundPhone=ExcelUtils.readData(filterPath,"Funds", 1, 6);
		 String fundEmail=ExcelUtils.readData(filterPath,"Funds", 1, 7);
		 String fundDesription=ExcelUtils.readData(filterPath,"Funds", 1, 8) ;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(bp.clickOnTab(TabName.FundsTab)){
			if (fp.clickOnCreatedFund(ExcelUtils.readData(filterPath,"Funds", 1, 0))) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));				
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace.toString()+" View.");
		if(click(driver, fp.getBuildFundraisinWorkspaceButton(60), "Build Fundraising button", action.SCROLLANDBOOLEAN)){
			if(!sendKeys(driver,fp.getSizeInMillionTextBox(Workspace.FundraisingWorkspace, 60),fundSize, "Size in Million text box", action.BOOLEAN)){
				sa.assertTrue(false,"Not able to pass data to size in million text box.");
			}
			if(!sendKeys(driver, fp.getVintageYear(Workspace.FundraisingWorkspace, 60), fundVintageYear, "vintage Year", action.BOOLEAN)){
			sa.assertTrue(false,"Not able to pass data to Vintage Year text box.");
			}
			if(!sendKeys(driver, fp.getContactTextBox(Workspace.FundraisingWorkspace, 60), fundContact, "Contact text Box", action.BOOLEAN)){
				sa.assertTrue(false,"Not able to pass data to contact text box.");
			}
			if(!sendKeys(driver, fp.getPhoneTextBox(Workspace.FundraisingWorkspace, 60), fundPhone, "Phone text Box", action.BOOLEAN)){
			sa.assertTrue(false,"Not able to pass data to Phone text box.");
			}
			if(!sendKeys(driver, fp.getEmailTextBox(Workspace.FundraisingWorkspace, 60), fundEmail, "Email text Box", action.BOOLEAN)){
				sa.assertTrue(false,"Not able to pass data to fund Email text box.");
			}
			if(!sendKeys(driver, fp.getDescriptionTextBox(Workspace.FundraisingWorkspace, 60), fundDesription, "Description text Box", action.BOOLEAN)){
				sa.assertTrue(false,"Not able to pass data to fund Description text box.");
			}
			if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 60), "Next Button", action.BOOLEAN)){
			appLog.info("Clicked on next button");
			if(click(driver, fp.getNext2Of3Button(Workspace.FundraisingWorkspace, 60), "Next button 2Of3", action.BOOLEAN)){
				appLog.info("Clicked on next button");
			if(fp.getBuildWorkspaceStep3Of3Header(Workspace.FundraisingWorkspace, 60)!=null){
				appLog.info("Build Workspace (Step 3 of 3)' pop up is displaying");				
			}else{
				appLog.error("Build Workspace (Step 3 of 3)' pop up is not displaying");
				sa.assertTrue(false, "Build Workspace (Step 3 of 3)' pop up is not displaying");
			}
			if (selectVisibleTextFromDropDown(driver,
					fp.getManageInvestorFilterFieldDropdown(Workspace.FundraisingWorkspace).get(0),
					"Field Dropdown", "Account:Employees")) {
				appLog.info("Selected field dropdown value");
				if (selectVisibleTextFromDropDown(driver,
						fp.getManageInvestorFilterOperatorDropdown(Workspace.FundraisingWorkspace).get(0),
						"Operator Dropdown", "equals")) {
					appLog.info("Selected Operator dropdown value");
					if (sendKeys(driver,
							fp.getManageInvestorFilterValueTextBox(Workspace.FundraisingWorkspace).get(0),
							"ABC", "Value text box", action.SCROLLANDBOOLEAN)) {
						if (click(driver,
								fp.getManageInvestorFilterApplyButton(Workspace.FundraisingWorkspace, 60),
								"Apply buton", action.SCROLLANDBOOLEAN)) {
							if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.filterInvalidNumberErroresage,
									fp.getManageInvestorFilterErrorMessage(Workspace.FundraisingWorkspace, 60),
									"Invalid number error Message in filter")) {
								appLog.info("Invalid number Error Message is verified at filter");
							} else {
								sa.assertTrue(false,"Invalid number Error Message is not verified at filter.Expected:"+ FundsPageErrorMessage.filterInvalidNumberErroresage
										+ " Actual"
										+ getText(driver,fp.getManageInvestorFilterErrorMessage(Workspace.FundraisingWorkspace, 60),"Invalid number error Message in filter",
												action.BOOLEAN));
							}
						} else {
							appLog.info("Not able to click on apply button");
							sa.assertTrue(false, "Not able to click on apply button");
						}
					} else {
						appLog.info("Not able to enter value in value tetxbox");
						sa.assertTrue(false, "Not able to enter value in value tetxbox");
					}
				} else {
					appLog.info("Not able to select Opeartor dropdown value");
					sa.assertTrue(false, "Not able to select Opeartor dropdown value");
				}
			} else {
				appLog.info("Not able to select field dropdown value");
				sa.assertTrue(false, "Not able to select field dropdown value");
			}
			if (click(driver, fp.getManageInvestorFilterClearButton(Workspace.FundraisingWorkspace, 60),
					"Clear Buton", action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on clear button successfully");
				String selectedValuefield = getSelectedOptionOfDropDown(driver,fp.getManageInvestorFilterFieldDropdown(Workspace.FundraisingWorkspace).get(0),"Field Dropdown", "text");
				if (selectedValuefield.equalsIgnoreCase("--None--")) {
					appLog.info("None value is selected in dropdown");
				} else {
					appLog.info("None value is not selected in dropdown");
					sa.assertTrue(false,"None value is not selected in dropdown");
				}
				String selectedValueOperator = getSelectedOptionOfDropDown(driver,fp.getManageInvestorFilterOperatorDropdown(
						Workspace.FundraisingWorkspace).get(0),
						"Opearator Dropdown", "text");
				if (selectedValueOperator.equalsIgnoreCase("equals")) {
					appLog.info("Equals value is selected in dropdown");
				} else {
					appLog.info("Equals value is not selected in dropdown");
					sa.assertTrue(false,
							"Equals value is not selected in dropdown");
				}	
			String	text = getAttribute(driver, fp.getStep3Of3FilterTextBox(Workspace.FundraisingWorkspace, 30), "", "value");
				if(text==null || text.isEmpty()){
					appLog.info("Criterion box is empty and verified.");
				} else {
					appLog.error("Criterion box is not empty.");
					sa.assertTrue(false,"Criterion box is not empty.");
				}
			} else {
				appLog.info("Not able to click on clear button");
				sa.assertTrue(false, "Not able to click on clear button");
			}
			if (selectVisibleTextFromDropDown(driver,
					fp.getManageInvestorFilterFieldDropdown(Workspace.FundraisingWorkspace).get(0),
					"Field Dropdown", "Account:Ownership")) {
				appLog.info("Selected field dropdown value");
				if (selectVisibleTextFromDropDown(driver,
						fp.getManageInvestorFilterOperatorDropdown(Workspace.FundraisingWorkspace).get(0),
						"Operator Dropdown", "equals")) {
					appLog.info("Selected Operator dropdown value");
					if (sendKeys(driver,
							fp.getManageInvestorFilterValueTextBox(Workspace.FundraisingWorkspace).get(0),
							"ABC", "Value text box", action.SCROLLANDBOOLEAN)) {
						if (click(driver,
								fp.getManageInvestorFilterApplyButton(Workspace.FundraisingWorkspace, 60),
								"Apply buton", action.SCROLLANDBOOLEAN)) {
							if (bp.verifyErrorMessageOnPage(
									FundsPageErrorMessage.filterPicklistValueNotExistErroresage,
									fp.getManageInvestorFilterErrorMessage(Workspace.FundraisingWorkspace, 60),
									"Picklist value not exist error Message in filter")) {
								appLog.info("Picklist value not exist Error Message is verified at filter");
							} else {
								sa.assertTrue(false,
										"Picklist value not exist is not verified at filter.Expected:"
												+ FundsPageErrorMessage.filterPicklistValueNotExistErroresage
												+ " Actual"
												+ getText(driver,
														fp.getManageInvestorFilterErrorMessage(
																Workspace.FundraisingWorkspace, 60),
														"Picklist value not exist error Message in filter",
														action.BOOLEAN));
							}
						} else {
							appLog.info("Not able to click on apply button");
							sa.assertTrue(false, "Not able to click on apply button");
						}
					} else {
						appLog.info("Not able to enter value in value tetxbox");
						sa.assertTrue(false, "Not able to enter value in value tetxbox");
					}
				} else {
					appLog.info("Not able to select Opeartor dropdown value");
					sa.assertTrue(false, "Not able to select Opeartor dropdown value");
				}
			} else {
				appLog.info("Not able to select field dropdown value");
				sa.assertTrue(false, "Not able to select field dropdown value");
			}
			if (click(driver, fp.getManageInvestorFilterClearButton(Workspace.FundraisingWorkspace, 60),
					"Clear Buton", action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on clear button successfully");
			} else {
				appLog.info("Not able to click on clear button");
				sa.assertTrue(false, "Not able to click on clear button");
			}
			if (selectVisibleTextFromDropDown(driver,
					fp.getManageInvestorFilterFieldDropdown(Workspace.FundraisingWorkspace).get(0),
					"Field Dropdown", "Account:Total Fund Commitments")) {
				appLog.info("Selected field dropdown value");
				if (selectVisibleTextFromDropDown(driver,
						fp.getManageInvestorFilterOperatorDropdown(Workspace.FundraisingWorkspace).get(0),
						"Operator Dropdown", "equals")) {
					appLog.info("Selected Operator dropdown value");
					if (sendKeys(driver,
							fp.getManageInvestorFilterValueTextBox(Workspace.FundraisingWorkspace).get(0),
							"ABC", "Value text box", action.SCROLLANDBOOLEAN)) {
						if (click(driver,
								fp.getManageInvestorFilterApplyButton(Workspace.FundraisingWorkspace, 60),
								"Apply buton", action.SCROLLANDBOOLEAN)) {
							if (bp.verifyErrorMessageOnPage(
									FundsPageErrorMessage.filterInvalidCurrencyErroresage,
									fp.getManageInvestorFilterErrorMessage(Workspace.FundraisingWorkspace, 60),
									"Invalid Currency error Message in filter")) {
								appLog.info("Invalid Currency Error Message is verified at filter");
							} else {
								sa.assertTrue(false,
										"Invalid Currency error message is not verified at filter.Expected:"
												+ FundsPageErrorMessage.filterInvalidCurrencyErroresage
												+ " Actual"
												+ getText(driver,
														fp.getManageInvestorFilterErrorMessage(
																Workspace.FundraisingWorkspace, 60),
														"Invalid Currency error Message in filter",
														action.BOOLEAN));
							}
						} else {
							appLog.info("Not able to click on apply button");
							sa.assertTrue(false, "Not able to click on apply button");
						}
					} else {
						appLog.info("Not able to enter value in value tetxbox");
						sa.assertTrue(false, "Not able to enter value in value tetxbox");
					}
				} else {
					appLog.info("Not able to select Opeartor dropdown value");
					sa.assertTrue(false, "Not able to select Opeartor dropdown value");
				}
			} else {
				appLog.info("Not able to select field dropdown value");
				sa.assertTrue(false, "Not able to select field dropdown value");
			}

			if (click(driver, fp.getManageInvestorFilterClearButton(Workspace.FundraisingWorkspace, 60),
					"Clear Buton", action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on clear button successfully");
			} else {
				appLog.info("Not able to click on clear button");
				sa.assertTrue(false, "Not able to click on clear button");
			}
			if (selectVisibleTextFromDropDown(driver,
					fp.getManageInvestorFilterFieldDropdown(Workspace.FundraisingWorkspace).get(0),
					"Field Dropdown", "Fundraising:Target Close Date")) {
				appLog.info("Selected field dropdown value");
				if (selectVisibleTextFromDropDown(driver,
						fp.getManageInvestorFilterOperatorDropdown(Workspace.FundraisingWorkspace).get(0),
						"Operator Dropdown", "equals")) {
					appLog.info("Selected Operator dropdown value");
					if (sendKeys(driver,
							fp.getManageInvestorFilterValueTextBox(Workspace.FundraisingWorkspace).get(0),
							"ABC12345", "Value text box", action.SCROLLANDBOOLEAN)) {
						if (click(driver,
								fp.getManageInvestorFilterApplyButton(Workspace.FundraisingWorkspace, 60),
								"Apply buton", action.SCROLLANDBOOLEAN)) {
							if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.filterInvalidDateErroresage,
									fp.getManageInvestorFilterErrorMessage(Workspace.FundraisingWorkspace, 60),
									"Invalid date error Message in filter")) {
								appLog.info("Invalid date Error Message is verified at filter");
							} else {
								sa.assertTrue(false,
										"Invalid date error message is not verified at filter.Expected:"
												+ FundsPageErrorMessage.filterInvalidDateErroresage + " Actual"
												+ getText(driver,
														fp.getManageInvestorFilterErrorMessage(
																Workspace.FundraisingWorkspace, 60),
														"Invalid date error Message in filter",
														action.BOOLEAN));
							}
						} else {
							appLog.info("Not able to click on apply button");
							sa.assertTrue(false, "Not able to click on apply button");
						}
					} else {
						appLog.info("Not able to enter value in value tetxbox");
						sa.assertTrue(false, "Not able to enter value in value tetxbox");
					}
				} else {
					appLog.info("Not able to select Opeartor dropdown value");
					sa.assertTrue(false, "Not able to select Opeartor dropdown value");
				}
			} else {
				appLog.info("Not able to select field dropdown value");
				sa.assertTrue(false, "Not able to select field dropdown value");
			}
			if (click(driver, fp.getManageInvestorFilterClearButton(Workspace.FundraisingWorkspace, 60),
					"Clear Buton", action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on clear button successfully");
			} else {
				appLog.info("Not able to click on clear button");
				sa.assertTrue(false, "Not able to click on clear button");
			}
			if (selectVisibleTextFromDropDown(driver,
					fp.getManageInvestorFilterFieldDropdown(Workspace.FundraisingWorkspace).get(0),
					"Field Dropdown", "Account:Existing LP")) {
				appLog.info("Selected field dropdown value");
				if (selectVisibleTextFromDropDown(driver,
						fp.getManageInvestorFilterOperatorDropdown(Workspace.FundraisingWorkspace).get(0),
						"Operator Dropdown", "equals")) {
					appLog.info("Selected Operator dropdown value");
					if (sendKeys(driver,
							fp.getManageInvestorFilterValueTextBox(Workspace.FundraisingWorkspace).get(0),
							"test", "Value text box", action.SCROLLANDBOOLEAN)) {
						if (click(driver,
								fp.getManageInvestorFilterApplyButton(Workspace.FundraisingWorkspace, 60),
								"Apply buton", action.SCROLLANDBOOLEAN)) {
							if (bp.verifyErrorMessageOnPage(
									FundsPageErrorMessage.filterPleaseUseTrueOrFalseErroresage,
									fp.getManageInvestorFilterErrorMessage(Workspace.FundraisingWorkspace, 60),
									"Please use true or false error Message in filter")) {
								appLog.info("Please use true or false Error Message is verified at filter");
							} else {
								sa.assertTrue(false,
										"Please use true or false error message is not verified at filter.Expected:"
												+ FundsPageErrorMessage.filterPleaseUseTrueOrFalseErroresage
												+ " Actual"
												+ getText(driver,
														fp.getManageInvestorFilterErrorMessage(
																Workspace.FundraisingWorkspace, 60),
														"Please use true or falseerror Message in filter",
														action.BOOLEAN));
							}
						} else {
							appLog.info("Not able to click on apply button");
							sa.assertTrue(false, "Not able to click on apply button");
						}
					} else {
						appLog.info("Not able to enter value in value tetxbox");
						sa.assertTrue(false, "Not able to enter value in value tetxbox");
					}
				} else {
					appLog.info("Not able to select Opeartor dropdown value");
					sa.assertTrue(false, "Not able to select Opeartor dropdown value");
				}
			} else {
				appLog.info("Not able to select field dropdown value");
				sa.assertTrue(false, "Not able to select field dropdown value");
			}
			if (click(driver, fp.getManageInvestorFilterClearButton(Workspace.FundraisingWorkspace, 60),
					"Clear Buton", action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on clear button successfully");
			} else {
				appLog.info("Not able to click on clear button");
				sa.assertTrue(false, "Not able to click on clear button");
			}
			if (click(driver, fp.getManageInvestorFilterAddRowLink(Workspace.FundraisingWorkspace, 60),
					"Add Row Link", action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on add row link successfully");
				int filterRow = fp.getManageInvestorFilterRows(Workspace.FundraisingWorkspace).size();
				if (filterRow == 2) {
					appLog.info("One other row is added successfully after clicking on add row link");
					if (fp.getManageInvestorFilterOperatorDropdown(Workspace.FundraisingWorkspace)
							.size() == 2) {
						appLog.info("2 operator dropdown is displaying");
					} else {
						appLog.info("2 operator Dropdown is not displaying");
						sa.assertTrue(false, "2 operator Dropdown is not displaying");
					}
					if (fp.getManageInvestorFilterFieldDropdown(Workspace.FundraisingWorkspace).size() == 2) {
						appLog.info("2 Field dropdown is displaying");
					} else {
						appLog.info("2 Field Dropdown is not displaying");
						sa.assertTrue(false, "2 Field Dropdown is not displaying");
					}
					if (fp.getManageInvestorFilterValueTextBox(Workspace.FundraisingWorkspace).size() == 2) {
						appLog.info("2 Value textbox is displaying");
					} else {
						appLog.info("2 Value textbox is not displaying");
						sa.assertTrue(false, "2 Value textbox is not displaying");
					}
					if (click(driver, fp.getManageInvestorFilterApplyButton(Workspace.FundraisingWorkspace, 60),
							"Apply buton", action.SCROLLANDBOOLEAN)) {
						if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.filterPleaseSelectAFieldErroresage,
								fp.getManageInvestorFilterfieldSelectErrorMessage(Workspace.FundraisingWorkspace)
										.get(0),
								"Please select a field error Message in filter")) {
							appLog.info("Please select a field Error Message is verified at filter");
						} else {
							sa.assertTrue(false,
									"Please select a field error message is not verified at filter.Expected:"
											+ FundsPageErrorMessage.filterPleaseSelectAFieldErroresage + " Actual"
											+ getText(driver,
													fp.getManageInvestorFilterfieldSelectErrorMessage(
															Workspace.FundraisingWorkspace).get(0),
													"Please select a field error Message in filter",
													action.BOOLEAN));
						}
						if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.filterPleaseSelectAFieldErroresage,
								fp.getManageInvestorFilterfieldSelectErrorMessage(Workspace.FundraisingWorkspace)
										.get(1),
								"Please select a field error Message in filter")) {
							appLog.info("Please select a field Error Message is verified at filter");
						} else {
							sa.assertTrue(false,
									"Please select a field error message is not verified at filter.Expected:"
											+ FundsPageErrorMessage.filterPleaseSelectAFieldErroresage + " Actual"
											+ getText(driver,
													fp.getManageInvestorFilterfieldSelectErrorMessage(
															Workspace.FundraisingWorkspace).get(1),
													"Please select a field error Message in filter",
													action.BOOLEAN));
						}
					} else {
						appLog.info("Not able to click on apply button");
						sa.assertTrue(false, "Not able to click on apply button");
					}
				} else {
					appLog.info("One other row is not added successfully after clicking on add row link");
					sa.assertTrue(false,
							"One other row is not added successfully after clicking on add row link");
				}
				for (int i = 1; i < 9; i++) {
					if (click(driver, fp.getManageInvestorFilterAddRowLink(Workspace.FundraisingWorkspace, 60),
							"Add Row Link", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on add row link successfully");
					} else {
						appLog.info("Not able to click on add row link");
						sa.assertTrue(false, "Not able to click on add row link");
					}
				}
				 filterRow = fp.getManageInvestorFilterRows(Workspace.FundraisingWorkspace).size();
				if (filterRow == 10) {
					appLog.info("10 rows are added successfully after clicking on add row link");
					if (fp.getManageInvestorFilterOperatorDropdown(Workspace.FundraisingWorkspace).size() == 10) {
						appLog.info("10 operator dropdown is displaying");
					} else {
						appLog.info("10 operator Dropdown is not displaying");
						sa.assertTrue(false, "10 operator Dropdown is not displaying");
					}
					if (fp.getManageInvestorFilterFieldDropdown(Workspace.FundraisingWorkspace).size() == 10) {
						appLog.info("10 Field dropdown is displaying");
					} else {
						appLog.info("10 Field Dropdown is not displaying");
						sa.assertTrue(false, "10 Field Dropdown is not displaying");
					}
					if (fp.getManageInvestorFilterValueTextBox(Workspace.FundraisingWorkspace).size() == 10) {
						appLog.info("10 Value textbox is displaying");
					} else {
						appLog.info("10 Value textbox is not displaying");
						sa.assertTrue(false, "10 Value textbox is not displaying");
					}
				} else {
					appLog.info("10 rows are not added successfully after clicking on add row link");
					sa.assertTrue(false, "10 rows are not added successfully after clicking on add row link");
				}
				switchToDefaultContent(driver);
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
						"FundraisingWorkspace View.");
				ThreadSleep(4000);
				String addRow = fp.getManageInvestorFilterAddRowLink(Workspace.FundraisingWorkspace, 60)
						.getAttribute("class");
				if (addRow.equalsIgnoreCase("add deactive")) {
					appLog.info("Add row link is not enabled");
				} else {
					appLog.info("Add row link is enabled");
					sa.assertTrue(false, "Add row link is enabled");
				}
				for (int i = 0; i < 2; i++) {
					if (click(driver,
							fp.getManageInvestorFilterRemoveRowIcon(Workspace.FundraisingWorkspace).get(i),
							"Remove Row icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on remove row icon");
					} else {
						appLog.info("Not able to click on remove row icon");
						sa.assertTrue(false, "Not able to click on remove row icon");
					}
				}
				filterRow = fp.getManageInvestorFilterRows(Workspace.FundraisingWorkspace).size();
				if (filterRow == 8) {
					appLog.info("2 rows get deleted successfully");
				} else {
					appLog.info("2 rows not get deleted successfully");
					sa.assertTrue(false, "2 rows not get deleted successfully");
				}	
				if (isEnabled(driver, fp.getManageInvestorFilterAddRowLink(Workspace.FundraisingWorkspace, 60),
						"Add Row Link")) {
					appLog.info("Add row link is enabled");
				} else {
					appLog.info("Add row link is not enabled");
					sa.assertTrue(false, "Add row link is not enabled");
				}
			} else {
				appLog.info("Not able to click on add row link");
				sa.assertTrue(false, "Not able to click on add row link");
			}	
			}else{
				appLog.error("Not able to click on next step 2 of 3 button");
				sa.assertTrue(false, "Not able to click on next step 2 of 3 button");
			}
			}else{
				appLog.error("Not able to click on next step 1 of 3 button");
				sa.assertTrue(false, "Not able to click on next step 1 of 3 button");
			}			
		}else{
			appLog.error("Not able to click on build fundraising workspace button");
			sa.assertTrue(false, "Not able to click on build fundraising workspace button");
		}
		}else{
				appLog.error("Not able to click on created funs");
				sa.assertTrue(false, "Not able ot click on created fund");
			}
		}else{
			appLog.error("Not able to click on funds tab");
			sa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M8tc032_CheckFunctionalityOfClearButton(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert sa=new SoftAssert();
		String fundSize=  ExcelUtils.readData(filterPath,"Funds", 1, 3);
		 String fundVintageYear=ExcelUtils.readData(filterPath,"Funds", 1, 4);
		 String fundContact=ExcelUtils.readData(filterPath,"Funds", 1, 5);
		 String fundPhone=ExcelUtils.readData(filterPath,"Funds", 1, 6);
		 String fundEmail=ExcelUtils.readData(filterPath,"Funds", 1, 7);
		 String fundDesription=ExcelUtils.readData(filterPath,"Funds", 1, 8) ;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		String [] institutionname={ExcelUtils.readData(filterPath,"Institutions", 2, 0),ExcelUtils.readData(filterPath,"Institutions", 3, 0),ExcelUtils.readData(filterPath,"Institutions", 4, 0)};
		if(bp.clickOnTab(TabName.FundsTab)){
			if (fp.clickOnCreatedFund(ExcelUtils.readData(filterPath,"Funds", 1, 0))) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));				
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60), Workspace.FundraisingWorkspace.toString()+" View.");
		if(click(driver, fp.getBuildFundraisinWorkspaceButton(60), "Build Fundraising button", action.SCROLLANDBOOLEAN)){
			if(!sendKeys(driver,fp.getSizeInMillionTextBox(Workspace.FundraisingWorkspace, 60),fundSize, "Size in Million text box", action.BOOLEAN)){
				sa.assertTrue(false,"Not able to pass data to size in million text box.");
			}
			if(!sendKeys(driver, fp.getVintageYear(Workspace.FundraisingWorkspace, 60), fundVintageYear, "vintage Year", action.BOOLEAN)){
			sa.assertTrue(false,"Not able to pass data to Vintage Year text box.");
			}
			if(!sendKeys(driver, fp.getContactTextBox(Workspace.FundraisingWorkspace, 60), fundContact, "Contact text Box", action.BOOLEAN)){
				sa.assertTrue(false,"Not able to pass data to contact text box.");
			}
			if(!sendKeys(driver, fp.getPhoneTextBox(Workspace.FundraisingWorkspace, 60), fundPhone, "Phone text Box", action.BOOLEAN)){
			sa.assertTrue(false,"Not able to pass data to Phone text box.");
			}
			if(!sendKeys(driver, fp.getEmailTextBox(Workspace.FundraisingWorkspace, 60), fundEmail, "Email text Box", action.BOOLEAN)){
				sa.assertTrue(false,"Not able to pass data to fund Email text box.");
			}
			if(!sendKeys(driver, fp.getDescriptionTextBox(Workspace.FundraisingWorkspace, 60), fundDesription, "Description text Box", action.BOOLEAN)){
				sa.assertTrue(false,"Not able to pass data to fund Description text box.");
			}
			if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 60), "Next Button", action.BOOLEAN)){
			appLog.info("Clicked on next button");
			if(click(driver, fp.getNext2Of3Button(Workspace.FundraisingWorkspace, 60), "Next button 2Of3", action.BOOLEAN)){
				appLog.info("Clicked on next button");
			if(fp.getBuildWorkspaceStep3Of3Header(Workspace.FundraisingWorkspace, 60)!=null){
				appLog.info("Build Workspace (Step 3 of 3)' pop up is displaying");				
			}else{
				appLog.error("Build Workspace (Step 3 of 3)' pop up is not displaying");
				sa.assertTrue(false, "Build Workspace (Step 3 of 3)' pop up is not displaying");
			}
		if(fp.getInstitutionsNameInBuildWorkspaceStep3FilterSection(60).size()==4){
			appLog.info("By default 4 institution is displaying");			
		}else{
			appLog.error("By default 4 institution is not displaying");
			sa.assertTrue(false, "By default 4 institution is not displaying");
		}
		if (selectVisibleTextFromDropDown(driver,fp.getManageInvestorFilterFieldDropdown(Workspace.FundraisingWorkspace).get(0),"Field Dropdown", "Account:Total Actual Fees")) {
			appLog.info("Selected field dropdown value");
			if (selectVisibleTextFromDropDown(driver,
					fp.getManageInvestorFilterOperatorDropdown(Workspace.FundraisingWorkspace).get(0),"Operator Dropdown", "not equal to")) {
				appLog.info("Selected Operator dropdown value");
				if (sendKeys(driver,fp.getManageInvestorFilterValueTextBox(Workspace.FundraisingWorkspace).get(0),
						"75", "Value text box", action.SCROLLANDBOOLEAN)) {
					if (click(driver,
							fp.getManageInvestorFilterApplyButton(Workspace.FundraisingWorkspace, 60),
							"Apply buton", action.SCROLLANDBOOLEAN)) {
						if(fp.getInstitutionsNameInBuildWorkspaceStep3FilterSection(60).size()==3){
							appLog.info("3 institution are displaying");			
						}else{
							appLog.error("3 institution are not displaying");
							sa.assertTrue(false, "3 institution are not displaying");
						}
						for (int i=0;i<fp.getInstitutionsNameInBuildWorkspaceStep3FilterSection(60).size();i++){
							String InstitutionNameText=fp.getInstitutionsNameInBuildWorkspaceStep3FilterSection(60).get(i).getText().trim();
							if(InstitutionNameText.equalsIgnoreCase(institutionname[i])){
								appLog.info(institutionname[i]+"is displaying");
							}else{
								appLog.error(institutionname[i]+"is not displaying");
								sa.assertTrue(false, institutionname[i]+"is not displaying");
							}							
						}	
					} else {
						appLog.info("Not able to click on apply button");
						sa.assertTrue(false, "Not able to click on apply button");
					}
				} else {
					appLog.info("Not able to enter value in value tetxbox");
					sa.assertTrue(false, "Not able to enter value in value tetxbox");
				}
			} else {
				appLog.info("Not able to select Opeartor dropdown value");
				sa.assertTrue(false, "Not able to select Opeartor dropdown value");
			}
		} else {
			appLog.info("Not able to select field dropdown value");
			sa.assertTrue(false, "Not able to select field dropdown value");
		}
		if (click(driver, fp.getManageInvestorFilterClearButton(Workspace.FundraisingWorkspace, 60),
				"Clear Buton", action.SCROLLANDBOOLEAN)) {
			appLog.info("Clicked on clear button successfully");
		} else {
			appLog.info("Not able to click on clear button");
			sa.assertTrue(false, "Not able to click on clear button");
		}
		String text = getSelectedOptionOfDropDown(driver, fp.getStep3Of3FieldDropDown(Workspace.FundraisingWorkspace, 30), "Filter Field  drop down", "text");
		if(text.trim().equalsIgnoreCase("--None--")){
			appLog.info("--None-- is by default selected in the field drop down.");
		} else {
			appLog.error("--None-- is not selected in the field drop down. Expected: --None--\tActual: "+text);
			sa.assertTrue(false,"--None-- is not selected in the field drop down. Expected: --None--\tActual: "+text);
		}
		
		text = getSelectedOptionOfDropDown(driver, fp.getStep3Of3OperatorDropDown(Workspace.FundraisingWorkspace, 30), "Filter Field  drop down", "text");
		if(text.trim().equalsIgnoreCase("equals")){
			appLog.info("equals is by default selected in the operator drop down.");
		} else {
			appLog.error("equals is not selected in the operator drop down. Expected: equals\tActual: "+text);
			sa.assertTrue(false,"equals is not selected in the operator drop down. Expected: equals\tActual: "+text);
		}
		
		text = getAttribute(driver, fp.getStep3Of3FilterTextBox(Workspace.FundraisingWorkspace, 30), "", "value");
		if(text==null || text.isEmpty()){
			appLog.info("Criterion box is empty and verified.");
		} else {
			appLog.error("Criterion box is not empty.");
			sa.assertTrue(false,"Criterion box is not empty.");
		}
		if(fp.getInstitutionsNameInBuildWorkspaceStep3FilterSection(60).size()==4){
			appLog.info("4 institution is displaying");			
		}else{
			appLog.error("4 institution is not displaying");
			sa.assertTrue(false, "4 institution is not displaying");
		}
		}else{
				appLog.error("Not able to click on next step 2 of 3 button");
				sa.assertTrue(false, "Not able to click on next step 2 of 3 button");
			}
			}else{
				appLog.error("Not able to click on next step 1 of 3 button");
				sa.assertTrue(false, "Not able to click on next step 1 of 3 button");
			}			
		}else{
			appLog.error("Not able to click on build fundraising workspace button");
			sa.assertTrue(false, "Not able to click on build fundraising workspace button");
		}
		}else{
				appLog.error("Not able to click on created funs");
				sa.assertTrue(false, "Not able ot click on created fund");
			}
		}else{
			appLog.error("Not able to click on funds tab");
			sa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();		
	}
	
	@Test
	public void M8tc033_VerifyFilterAsPerFilterSingleRowFilterFundraisingMultipleRowFilterFundraisingSheet(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		SoftAssert saa1=new SoftAssert();
		String fundSize=  ExcelUtils.readData(filterPath,"Funds", 1, 3);
		 String fundVintageYear=ExcelUtils.readData(filterPath,"Funds", 1, 4);
		 String fundContact=ExcelUtils.readData(filterPath,"Funds", 1, 5);
		 String fundPhone=ExcelUtils.readData(filterPath,"Funds", 1, 6);
		 String fundEmail=ExcelUtils.readData(filterPath,"Funds", 1, 7);
		 String fundDesription=ExcelUtils.readData(filterPath,"Funds", 1, 8) ;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		for (int i = 0; i < 2; i++) {
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(ExcelUtils.readData(filterPath,"Funds", 1, 0))) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 60),
						"FundraisingWorkspace View.");
				if(click(driver, fp.getBuildFundraisinWorkspaceButton(60), "Build Fundraising button", action.SCROLLANDBOOLEAN)){
					if(!sendKeys(driver,fp.getSizeInMillionTextBox(Workspace.FundraisingWorkspace, 60),fundSize, "Size in Million text box", action.BOOLEAN)){
						sa.assertTrue(false,"Not able to pass data to size in million text box.");
					}
					if(!sendKeys(driver, fp.getVintageYear(Workspace.FundraisingWorkspace, 60), fundVintageYear, "vintage Year", action.BOOLEAN)){
					sa.assertTrue(false,"Not able to pass data to Vintage Year text box.");
					}
					if(!sendKeys(driver, fp.getContactTextBox(Workspace.FundraisingWorkspace, 60), fundContact, "Contact text Box", action.BOOLEAN)){
						sa.assertTrue(false,"Not able to pass data to contact text box.");
					}
					if(!sendKeys(driver, fp.getPhoneTextBox(Workspace.FundraisingWorkspace, 60), fundPhone, "Phone text Box", action.BOOLEAN)){
					sa.assertTrue(false,"Not able to pass data to Phone text box.");
					}
					if(!sendKeys(driver, fp.getEmailTextBox(Workspace.FundraisingWorkspace, 60), fundEmail, "Email text Box", action.BOOLEAN)){
						sa.assertTrue(false,"Not able to pass data to fund Email text box.");
					}
					if(!sendKeys(driver, fp.getDescriptionTextBox(Workspace.FundraisingWorkspace, 60), fundDesription, "Description text Box", action.BOOLEAN)){
						sa.assertTrue(false,"Not able to pass data to fund Description text box.");
					}
					if(click(driver, fp.getNext1Of3Button(Workspace.FundraisingWorkspace, 60), "Next Button", action.BOOLEAN)){
					appLog.info("Clicked on next button");
					if(click(driver, fp.getNext2Of3Button(Workspace.FundraisingWorkspace, 60), "Next button 2Of3", action.BOOLEAN)){
						appLog.info("Clicked on next button");
						
					if (i == 0) {
						sa = fp.checkFilterSingle(filterPath,Workspace.FundraisingWorkspace, "Single row filter_Fundraising",60);
						switchToDefaultContent(driver);
						appLog.info("Single Filter is Done Successfully.");
					} else if (i == 1) {
						saa1 = fp.applyCriterionOnManageInvestor(filterPath,"Multiple row filter_Fundraising", Workspace.FundraisingWorkspace,60);
						switchToDefaultContent(driver);
						appLog.info("Done");
					}
						
			}else{
					appLog.error("Not able to click on next step 2 of 3 button");
					sa.assertTrue(false, "Not able to click on next step 2 of 3 button");
				}
				}else{
					appLog.error("Not able to click on next step 1 of 3 button");
					sa.assertTrue(false, "Not able to click on next step 1 of 3 button");
				}			
			}else{
				appLog.error("Not able to click on build fundraising workspace button");
				sa.assertTrue(false, "Not able to click on build fundraising workspace button");
			}
		} else {
			appLog.info("Not able to click on created fund");
			sa.assertTrue(false, "Not able to click on created fund");
		}
	} else {
		appLog.info("Not able to click on Funds tab");
		sa.assertTrue(false, "Not able to click on Funds tab");
	}
	}
	switchToDefaultContent(driver);
	lp.CRMlogout();
	try{	
		sa.assertAll();
	} catch(Throwable th){
		appLog.error("Following asserts failed in single filter: ");
		appLog.info(th.getLocalizedMessage());
		saa1.assertTrue(false,"Some filters failed in single assert.");
	}	
	saa1.assertAll();
	sa.assertAll();		
	}
	
	@Test
	public void M8tc034_CheckErrorMessgeInStep3PopUpAndFunctionalityOfAddRowLink(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert sa=new SoftAssert();
		lp.CRMLogin(superAdminUserName, adminPassword);
		if(bp.clickOnTab(TabName.FundsTab)){
			if (fp.clickOnCreatedFund(ExcelUtils.readData(filterPath,"Funds", 1, 0))) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));				
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60), Workspace.InvestorWorkspace.toString()+" View.");
			if(fp.clearWorkSpace(Workspace.InvestorWorkspace, 60)){
				appLog.info("Workspace get cleared successfully");
			}else{
				appLog.error("Not able to clear workspace ");
				sa.assertTrue(false, "Not able to clear workspace");
			}			
			}else{
				appLog.error("Not able ot click on created funs");
				sa.assertTrue(false, "Not able ot click on created fund");
			}
		}else{
			appLog.error("Not able to click on funds tab");
			sa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		 lp = new LoginPageBusinessLayer(driver);
		 bp = new BasePageBusinessLayer(driver);
		 fp = new FundsPageBusinessLayer(driver);
		 sa=new SoftAssert();
		 String fundSize=  ExcelUtils.readData(filterPath,"Funds", 1, 3);
		 String fundVintageYear=ExcelUtils.readData(filterPath,"Funds", 1, 4);
		 String fundContact=ExcelUtils.readData(filterPath,"Funds", 1, 5);
		 String fundPhone=ExcelUtils.readData(filterPath,"Funds", 1, 6);
		 String fundEmail=ExcelUtils.readData(filterPath,"Funds", 1, 7);
		 String fundDesription=ExcelUtils.readData(filterPath,"Funds", 1, 8) ;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(bp.clickOnTab(TabName.FundsTab)){
			if (fp.clickOnCreatedFund(ExcelUtils.readData(filterPath,"Funds", 1, 0))) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));				
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60), Workspace.InvestorWorkspace.toString()+" View.");
		if(click(driver, fp.getBuildInvestorWorkspace(60), "Build Investor button", action.SCROLLANDBOOLEAN)){
			if(!sendKeys(driver,fp.getSizeInMillionTextBox(Workspace.InvestorWorkspace, 60),fundSize, "Size in Million text box", action.BOOLEAN)){
				sa.assertTrue(false,"Not able to pass data to size in million text box.");
			}
			if(!sendKeys(driver, fp.getVintageYear(Workspace.InvestorWorkspace, 60), fundVintageYear, "vintage Year", action.BOOLEAN)){
			sa.assertTrue(false,"Not able to pass data to Vintage Year text box.");
			}
			if(!sendKeys(driver, fp.getContactTextBox(Workspace.InvestorWorkspace, 60), fundContact, "Contact text Box", action.BOOLEAN)){
				sa.assertTrue(false,"Not able to pass data to contact text box.");
			}
			if(!sendKeys(driver, fp.getPhoneTextBox(Workspace.InvestorWorkspace, 60), fundPhone, "Phone text Box", action.BOOLEAN)){
			sa.assertTrue(false,"Not able to pass data to Phone text box.");
			}
			if(!sendKeys(driver, fp.getEmailTextBox(Workspace.InvestorWorkspace, 60), fundEmail, "Email text Box", action.BOOLEAN)){
				sa.assertTrue(false,"Not able to pass data to fund Email text box.");
			}
			if(!sendKeys(driver, fp.getDescriptionTextBox(Workspace.InvestorWorkspace, 60), fundDesription, "Description text Box", action.BOOLEAN)){
				sa.assertTrue(false,"Not able to pass data to fund Description text box.");
			}
			if(click(driver, fp.getNext1Of3Button(Workspace.InvestorWorkspace, 60), "Next Button", action.BOOLEAN)){
			appLog.info("Clicked on next button");
			if(click(driver, fp.getNext2Of3Button(Workspace.InvestorWorkspace, 60), "Next button 2Of3", action.BOOLEAN)){
				appLog.info("Clicked on next button");
			if(fp.getBuildWorkspaceStep3Of3Header(Workspace.InvestorWorkspace, 60)!=null){
				appLog.info("Build Workspace (Step 3 of 3)' pop up is displaying");				
			}else{
				appLog.error("Build Workspace (Step 3 of 3)' pop up is not displaying");
				sa.assertTrue(false, "Build Workspace (Step 3 of 3)' pop up is not displaying");
			}
			if (selectVisibleTextFromDropDown(driver,
					fp.getManageInvestorFilterFieldDropdown(Workspace.InvestorWorkspace).get(0),
					"Field Dropdown", "Account:Employees")) {
				appLog.info("Selected field dropdown value");
				if (selectVisibleTextFromDropDown(driver,
						fp.getManageInvestorFilterOperatorDropdown(Workspace.InvestorWorkspace).get(0),
						"Operator Dropdown", "equals")) {
					appLog.info("Selected Operator dropdown value");
					if (sendKeys(driver,
							fp.getManageInvestorFilterValueTextBox(Workspace.InvestorWorkspace).get(0),
							"ABC", "Value text box", action.SCROLLANDBOOLEAN)) {
						if (click(driver,
								fp.getManageInvestorFilterApplyButton(Workspace.InvestorWorkspace, 60),
								"Apply buton", action.SCROLLANDBOOLEAN)) {
							if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.filterInvalidNumberErroresage,
									fp.getManageInvestorFilterErrorMessage(Workspace.InvestorWorkspace, 60),
									"Invalid number error Message in filter")) {
								appLog.info("Invalid number Error Message is verified at filter");
							} else {
								sa.assertTrue(false,"Invalid number Error Message is not verified at filter.Expected:"+ FundsPageErrorMessage.filterInvalidNumberErroresage
										+ " Actual"
										+ getText(driver,fp.getManageInvestorFilterErrorMessage(Workspace.InvestorWorkspace, 60),"Invalid number error Message in filter",
												action.BOOLEAN));
							}
						} else {
							appLog.info("Not able to click on apply button");
							sa.assertTrue(false, "Not able to click on apply button");
						}
					} else {
						appLog.info("Not able to enter value in value tetxbox");
						sa.assertTrue(false, "Not able to enter value in value tetxbox");
					}
				} else {
					appLog.info("Not able to select Opeartor dropdown value");
					sa.assertTrue(false, "Not able to select Opeartor dropdown value");
				}
			} else {
				appLog.info("Not able to select field dropdown value");
				sa.assertTrue(false, "Not able to select field dropdown value");
			}
			if (click(driver, fp.getManageInvestorFilterClearButton(Workspace.InvestorWorkspace, 60),
					"Clear Buton", action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on clear button successfully");
				String selectedValuefield = getSelectedOptionOfDropDown(driver,fp.getManageInvestorFilterFieldDropdown(Workspace.InvestorWorkspace).get(0),"Field Dropdown", "text");
				if (selectedValuefield.equalsIgnoreCase("--None--")) {
					appLog.info("None value is selected in dropdown");
				} else {
					appLog.info("None value is not selected in dropdown");
					sa.assertTrue(false,"None value is not selected in dropdown");
				}
				String selectedValueOperator = getSelectedOptionOfDropDown(driver,fp.getManageInvestorFilterOperatorDropdown(
						Workspace.InvestorWorkspace).get(0),
						"Opearator Dropdown", "text");
				if (selectedValueOperator.equalsIgnoreCase("equals")) {
					appLog.info("Equals value is selected in dropdown");
				} else {
					appLog.info("Equals value is not selected in dropdown");
					sa.assertTrue(false,
							"Equals value is not selected in dropdown");
				}	
			String	text = getAttribute(driver, fp.getStep3Of3FilterTextBox(Workspace.InvestorWorkspace, 30), "", "value");
				if(text==null || text.isEmpty()){
					appLog.info("Criterion box is empty and verified.");
				} else {
					appLog.error("Criterion box is not empty.");
					sa.assertTrue(false,"Criterion box is not empty.");
				}
			} else {
				appLog.info("Not able to click on clear button");
				sa.assertTrue(false, "Not able to click on clear button");
			}
			if (selectVisibleTextFromDropDown(driver,
					fp.getManageInvestorFilterFieldDropdown(Workspace.InvestorWorkspace).get(0),
					"Field Dropdown", "Account:Ownership")) {
				appLog.info("Selected field dropdown value");
				if (selectVisibleTextFromDropDown(driver,
						fp.getManageInvestorFilterOperatorDropdown(Workspace.InvestorWorkspace).get(0),
						"Operator Dropdown", "equals")) {
					appLog.info("Selected Operator dropdown value");
					if (sendKeys(driver,
							fp.getManageInvestorFilterValueTextBox(Workspace.InvestorWorkspace).get(0),
							"ABC", "Value text box", action.SCROLLANDBOOLEAN)) {
						if (click(driver,
								fp.getManageInvestorFilterApplyButton(Workspace.InvestorWorkspace, 60),
								"Apply buton", action.SCROLLANDBOOLEAN)) {
							if (bp.verifyErrorMessageOnPage(
									FundsPageErrorMessage.filterPicklistValueNotExistErroresage,
									fp.getManageInvestorFilterErrorMessage(Workspace.InvestorWorkspace, 60),
									"Picklist value not exist error Message in filter")) {
								appLog.info("Picklist value not exist Error Message is verified at filter");
							} else {
								sa.assertTrue(false,
										"Picklist value not exist is not verified at filter.Expected:"
												+ FundsPageErrorMessage.filterPicklistValueNotExistErroresage
												+ " Actual"
												+ getText(driver,
														fp.getManageInvestorFilterErrorMessage(
																Workspace.InvestorWorkspace, 60),
														"Picklist value not exist error Message in filter",
														action.BOOLEAN));
							}
						} else {
							appLog.info("Not able to click on apply button");
							sa.assertTrue(false, "Not able to click on apply button");
						}
					} else {
						appLog.info("Not able to enter value in value tetxbox");
						sa.assertTrue(false, "Not able to enter value in value tetxbox");
					}
				} else {
					appLog.info("Not able to select Opeartor dropdown value");
					sa.assertTrue(false, "Not able to select Opeartor dropdown value");
				}
			} else {
				appLog.info("Not able to select field dropdown value");
				sa.assertTrue(false, "Not able to select field dropdown value");
			}
			if (click(driver, fp.getManageInvestorFilterClearButton(Workspace.InvestorWorkspace, 60),
					"Clear Buton", action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on clear button successfully");
			} else {
				appLog.info("Not able to click on clear button");
				sa.assertTrue(false, "Not able to click on clear button");
			}
			if (selectVisibleTextFromDropDown(driver,
					fp.getManageInvestorFilterFieldDropdown(Workspace.InvestorWorkspace).get(0),
					"Field Dropdown", "Account:Total Fund Commitments")) {
				appLog.info("Selected field dropdown value");
				if (selectVisibleTextFromDropDown(driver,
						fp.getManageInvestorFilterOperatorDropdown(Workspace.InvestorWorkspace).get(0),
						"Operator Dropdown", "equals")) {
					appLog.info("Selected Operator dropdown value");
					if (sendKeys(driver,
							fp.getManageInvestorFilterValueTextBox(Workspace.InvestorWorkspace).get(0),
							"ABC", "Value text box", action.SCROLLANDBOOLEAN)) {
						if (click(driver,
								fp.getManageInvestorFilterApplyButton(Workspace.InvestorWorkspace, 60),
								"Apply buton", action.SCROLLANDBOOLEAN)) {
							if (bp.verifyErrorMessageOnPage(
									FundsPageErrorMessage.filterInvalidCurrencyErroresage,
									fp.getManageInvestorFilterErrorMessage(Workspace.InvestorWorkspace, 60),
									"Invalid Currency error Message in filter")) {
								appLog.info("Invalid Currency Error Message is verified at filter");
							} else {
								sa.assertTrue(false,
										"Invalid Currency error message is not verified at filter.Expected:"
												+ FundsPageErrorMessage.filterInvalidCurrencyErroresage
												+ " Actual"
												+ getText(driver,
														fp.getManageInvestorFilterErrorMessage(
																Workspace.InvestorWorkspace, 60),
														"Invalid Currency error Message in filter",
														action.BOOLEAN));
							}
						} else {
							appLog.info("Not able to click on apply button");
							sa.assertTrue(false, "Not able to click on apply button");
						}
					} else {
						appLog.info("Not able to enter value in value tetxbox");
						sa.assertTrue(false, "Not able to enter value in value tetxbox");
					}
				} else {
					appLog.info("Not able to select Opeartor dropdown value");
					sa.assertTrue(false, "Not able to select Opeartor dropdown value");
				}
			} else {
				appLog.info("Not able to select field dropdown value");
				sa.assertTrue(false, "Not able to select field dropdown value");
			}

			if (click(driver, fp.getManageInvestorFilterClearButton(Workspace.InvestorWorkspace, 60),
					"Clear Buton", action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on clear button successfully");
			} else {
				appLog.info("Not able to click on clear button");
				sa.assertTrue(false, "Not able to click on clear button");
			}
			if (selectVisibleTextFromDropDown(driver,
					fp.getManageInvestorFilterFieldDropdown(Workspace.InvestorWorkspace).get(0),
					"Field Dropdown", "Commitment:Final Commitment Date")) {
				appLog.info("Selected field dropdown value");
				if (selectVisibleTextFromDropDown(driver,
						fp.getManageInvestorFilterOperatorDropdown(Workspace.InvestorWorkspace).get(0),
						"Operator Dropdown", "equals")) {
					appLog.info("Selected Operator dropdown value");
					if (sendKeys(driver,
							fp.getManageInvestorFilterValueTextBox(Workspace.InvestorWorkspace).get(0),
							"ABC12345", "Value text box", action.SCROLLANDBOOLEAN)) {
						if (click(driver,
								fp.getManageInvestorFilterApplyButton(Workspace.InvestorWorkspace, 60),
								"Apply buton", action.SCROLLANDBOOLEAN)) {
							if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.filterInvalidDateErroresage,
									fp.getManageInvestorFilterErrorMessage(Workspace.InvestorWorkspace, 60),
									"Invalid date error Message in filter")) {
								appLog.info("Invalid date Error Message is verified at filter");
							} else {
								sa.assertTrue(false,
										"Invalid date error message is not verified at filter.Expected:"
												+ FundsPageErrorMessage.filterInvalidDateErroresage + " Actual"
												+ getText(driver,
														fp.getManageInvestorFilterErrorMessage(
																Workspace.InvestorWorkspace, 60),
														"Invalid date error Message in filter",
														action.BOOLEAN));
							}
						} else {
							appLog.info("Not able to click on apply button");
							sa.assertTrue(false, "Not able to click on apply button");
						}
					} else {
						appLog.info("Not able to enter value in value tetxbox");
						sa.assertTrue(false, "Not able to enter value in value tetxbox");
					}
				} else {
					appLog.info("Not able to select Opeartor dropdown value");
					sa.assertTrue(false, "Not able to select Opeartor dropdown value");
				}
			} else {
				appLog.info("Not able to select field dropdown value");
				sa.assertTrue(false, "Not able to select field dropdown value");
			}
			if (click(driver, fp.getManageInvestorFilterClearButton(Workspace.InvestorWorkspace, 60),
					"Clear Buton", action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on clear button successfully");
			} else {
				appLog.info("Not able to click on clear button");
				sa.assertTrue(false, "Not able to click on clear button");
			}
			if (selectVisibleTextFromDropDown(driver,
					fp.getManageInvestorFilterFieldDropdown(Workspace.InvestorWorkspace).get(0),
					"Field Dropdown", "Account:Existing LP")) {
				appLog.info("Selected field dropdown value");
				if (selectVisibleTextFromDropDown(driver,
						fp.getManageInvestorFilterOperatorDropdown(Workspace.InvestorWorkspace).get(0),
						"Operator Dropdown", "equals")) {
					appLog.info("Selected Operator dropdown value");
					if (sendKeys(driver,
							fp.getManageInvestorFilterValueTextBox(Workspace.InvestorWorkspace).get(0),
							"test", "Value text box", action.SCROLLANDBOOLEAN)) {
						if (click(driver,
								fp.getManageInvestorFilterApplyButton(Workspace.InvestorWorkspace, 60),
								"Apply buton", action.SCROLLANDBOOLEAN)) {
							if (bp.verifyErrorMessageOnPage(
									FundsPageErrorMessage.filterPleaseUseTrueOrFalseErroresage,
									fp.getManageInvestorFilterErrorMessage(Workspace.InvestorWorkspace, 60),
									"Please use true or false error Message in filter")) {
								appLog.info("Please use true or false Error Message is verified at filter");
							} else {
								sa.assertTrue(false,
										"Please use true or false error message is not verified at filter.Expected:"
												+ FundsPageErrorMessage.filterPleaseUseTrueOrFalseErroresage
												+ " Actual"
												+ getText(driver,
														fp.getManageInvestorFilterErrorMessage(
																Workspace.InvestorWorkspace, 60),
														"Please use true or falseerror Message in filter",
														action.BOOLEAN));
							}
						} else {
							appLog.info("Not able to click on apply button");
							sa.assertTrue(false, "Not able to click on apply button");
						}
					} else {
						appLog.info("Not able to enter value in value tetxbox");
						sa.assertTrue(false, "Not able to enter value in value tetxbox");
					}
				} else {
					appLog.info("Not able to select Opeartor dropdown value");
					sa.assertTrue(false, "Not able to select Opeartor dropdown value");
				}
			} else {
				appLog.info("Not able to select field dropdown value");
				sa.assertTrue(false, "Not able to select field dropdown value");
			}
			if (click(driver, fp.getManageInvestorFilterClearButton(Workspace.InvestorWorkspace, 60),
					"Clear Buton", action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on clear button successfully");
			} else {
				appLog.info("Not able to click on clear button");
				sa.assertTrue(false, "Not able to click on clear button");
			}
			if (click(driver, fp.getManageInvestorFilterAddRowLink(Workspace.InvestorWorkspace, 60),
					"Add Row Link", action.SCROLLANDBOOLEAN)) {
				appLog.info("Clicked on add row link successfully");
				int filterRow = fp.getManageInvestorFilterRows(Workspace.InvestorWorkspace).size();
				if (filterRow == 2) {
					appLog.info("One other row is added successfully after clicking on add row link");
					if (fp.getManageInvestorFilterOperatorDropdown(Workspace.InvestorWorkspace)
							.size() == 2) {
						appLog.info("2 operator dropdown is displaying");
					} else {
						appLog.info("2 operator Dropdown is not displaying");
						sa.assertTrue(false, "2 operator Dropdown is not displaying");
					}
					if (fp.getManageInvestorFilterFieldDropdown(Workspace.InvestorWorkspace).size() == 2) {
						appLog.info("2 Field dropdown is displaying");
					} else {
						appLog.info("2 Field Dropdown is not displaying");
						sa.assertTrue(false, "2 Field Dropdown is not displaying");
					}
					if (fp.getManageInvestorFilterValueTextBox(Workspace.InvestorWorkspace).size() == 2) {
						appLog.info("2 Value textbox is displaying");
					} else {
						appLog.info("2 Value textbox is not displaying");
						sa.assertTrue(false, "2 Value textbox is not displaying");
					}
					if (click(driver, fp.getManageInvestorFilterApplyButton(Workspace.InvestorWorkspace, 60),
							"Apply buton", action.SCROLLANDBOOLEAN)) {
						if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.filterPleaseSelectAFieldErroresage,
								fp.getManageInvestorFilterfieldSelectErrorMessage(Workspace.InvestorWorkspace)
										.get(0),
								"Please select a field error Message in filter")) {
							appLog.info("Please select a field Error Message is verified at filter");
						} else {
							sa.assertTrue(false,
									"Please select a field error message is not verified at filter.Expected:"
											+ FundsPageErrorMessage.filterPleaseSelectAFieldErroresage + " Actual"
											+ getText(driver,
													fp.getManageInvestorFilterfieldSelectErrorMessage(
															Workspace.InvestorWorkspace).get(0),
													"Please select a field error Message in filter",
													action.BOOLEAN));
						}
						if (bp.verifyErrorMessageOnPage(FundsPageErrorMessage.filterPleaseSelectAFieldErroresage,
								fp.getManageInvestorFilterfieldSelectErrorMessage(Workspace.InvestorWorkspace)
										.get(1),
								"Please select a field error Message in filter")) {
							appLog.info("Please select a field Error Message is verified at filter");
						} else {
							sa.assertTrue(false,
									"Please select a field error message is not verified at filter.Expected:"
											+ FundsPageErrorMessage.filterPleaseSelectAFieldErroresage + " Actual"
											+ getText(driver,
													fp.getManageInvestorFilterfieldSelectErrorMessage(
															Workspace.InvestorWorkspace).get(1),
													"Please select a field error Message in filter",
													action.BOOLEAN));
						}
					} else {
						appLog.info("Not able to click on apply button");
						sa.assertTrue(false, "Not able to click on apply button");
					}
				} else {
					appLog.info("One other row is not added successfully after clicking on add row link");
					sa.assertTrue(false,
							"One other row is not added successfully after clicking on add row link");
				}
				for (int i = 1; i < 9; i++) {
					if (click(driver, fp.getManageInvestorFilterAddRowLink(Workspace.InvestorWorkspace, 60),
							"Add Row Link", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on add row link successfully");
					} else {
						appLog.info("Not able to click on add row link");
						sa.assertTrue(false, "Not able to click on add row link");
					}
				}
				 filterRow = fp.getManageInvestorFilterRows(Workspace.InvestorWorkspace).size();
				if (filterRow == 10) {
					appLog.info("10 rows are added successfully after clicking on add row link");
					if (fp.getManageInvestorFilterOperatorDropdown(Workspace.InvestorWorkspace).size() == 10) {
						appLog.info("10 operator dropdown is displaying");
					} else {
						appLog.info("10 operator Dropdown is not displaying");
						sa.assertTrue(false, "10 operator Dropdown is not displaying");
					}
					if (fp.getManageInvestorFilterFieldDropdown(Workspace.InvestorWorkspace).size() == 10) {
						appLog.info("10 Field dropdown is displaying");
					} else {
						appLog.info("10 Field Dropdown is not displaying");
						sa.assertTrue(false, "10 Field Dropdown is not displaying");
					}
					if (fp.getManageInvestorFilterValueTextBox(Workspace.InvestorWorkspace).size() == 10) {
						appLog.info("10 Value textbox is displaying");
					} else {
						appLog.info("10 Value textbox is not displaying");
						sa.assertTrue(false, "10 Value textbox is not displaying");
					}
				} else {
					appLog.info("10 rows are not added successfully after clicking on add row link");
					sa.assertTrue(false, "10 rows are not added successfully after clicking on add row link");
				}
				switchToDefaultContent(driver);
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
						"InvestorWorkspace View.");
				ThreadSleep(4000);
				String addRow = fp.getManageInvestorFilterAddRowLink(Workspace.InvestorWorkspace, 60)
						.getAttribute("class");
				if (addRow.equalsIgnoreCase("add deactive")) {
					appLog.info("Add row link is not enabled");
				} else {
					appLog.info("Add row link is enabled");
					sa.assertTrue(false, "Add row link is enabled");
				}
				for (int i = 0; i < 2; i++) {
					if (click(driver,
							fp.getManageInvestorFilterRemoveRowIcon(Workspace.InvestorWorkspace).get(i),
							"Remove Row icon", action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on remove row icon");
					} else {
						appLog.info("Not able to click on remove row icon");
						sa.assertTrue(false, "Not able to click on remove row icon");
					}
				}
				filterRow = fp.getManageInvestorFilterRows(Workspace.InvestorWorkspace).size();
				if (filterRow == 8) {
					appLog.info("2 rows get deleted successfully");
				} else {
					appLog.info("2 rows not get deleted successfully");
					sa.assertTrue(false, "2 rows not get deleted successfully");
				}	
				if (isEnabled(driver, fp.getManageInvestorFilterAddRowLink(Workspace.InvestorWorkspace, 60),
						"Add Row Link")) {
					appLog.info("Add row link is enabled");
				} else {
					appLog.info("Add row link is not enabled");
					sa.assertTrue(false, "Add row link is not enabled");
				}
			} else {
				appLog.info("Not able to click on add row link");
				sa.assertTrue(false, "Not able to click on add row link");
			}	
			}else{
				appLog.error("Not able to click on next step 2 of 3 button");
				sa.assertTrue(false, "Not able to click on next step 2 of 3 button");
			}
			}else{
				appLog.error("Not able to click on next step 1 of 3 button");
				sa.assertTrue(false, "Not able to click on next step 1 of 3 button");
			}			
		}else{
			appLog.error("Not able to click on build Investor workspace button");
			sa.assertTrue(false, "Not able to click on build Investor workspace button");
		}
		}else{
				appLog.error("Not able to click on created funs");
				sa.assertTrue(false, "Not able ot click on created fund");
			}
		}else{
			appLog.error("Not able to click on funds tab");
			sa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();	
	}
	
	@Test
	public void M8tc035_CheckFunctionalityOfClearButton(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert sa=new SoftAssert();
		String fundSize=  ExcelUtils.readData(filterPath,"Funds", 1, 3);
		 String fundVintageYear=ExcelUtils.readData(filterPath,"Funds", 1, 4);
		 String fundContact=ExcelUtils.readData(filterPath,"Funds", 1, 5);
		 String fundPhone=ExcelUtils.readData(filterPath,"Funds", 1, 6);
		 String fundEmail=ExcelUtils.readData(filterPath,"Funds", 1, 7);
		 String fundDesription=ExcelUtils.readData(filterPath,"Funds", 1, 8) ;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		String [] institutionname={ExcelUtils.readData(filterPath,"Institutions", 2, 0),ExcelUtils.readData(filterPath,"Institutions", 1, 0),ExcelUtils.readData(filterPath,"Institutions", 3, 0),ExcelUtils.readData(filterPath,"Institutions", 4, 0)};
		if(bp.clickOnTab(TabName.FundsTab)){
			if (fp.clickOnCreatedFund(ExcelUtils.readData(filterPath,"Funds", 1, 0))) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));				
				scrollDownThroughWebelement(driver, bp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60), Workspace.InvestorWorkspace.toString()+" View.");
		if(click(driver, fp.getBuildInvestorWorkspace(60), "Build Investor button", action.SCROLLANDBOOLEAN)){
			if(!sendKeys(driver,fp.getSizeInMillionTextBox(Workspace.InvestorWorkspace, 60),fundSize, "Size in Million text box", action.BOOLEAN)){
				sa.assertTrue(false,"Not able to pass data to size in million text box.");
			}
			if(!sendKeys(driver, fp.getVintageYear(Workspace.InvestorWorkspace, 60), fundVintageYear, "vintage Year", action.BOOLEAN)){
			sa.assertTrue(false,"Not able to pass data to Vintage Year text box.");
			}
			if(!sendKeys(driver, fp.getContactTextBox(Workspace.InvestorWorkspace, 60), fundContact, "Contact text Box", action.BOOLEAN)){
				sa.assertTrue(false,"Not able to pass data to contact text box.");
			}
			if(!sendKeys(driver, fp.getPhoneTextBox(Workspace.InvestorWorkspace, 60), fundPhone, "Phone text Box", action.BOOLEAN)){
			sa.assertTrue(false,"Not able to pass data to Phone text box.");
			}
			if(!sendKeys(driver, fp.getEmailTextBox(Workspace.InvestorWorkspace, 60), fundEmail, "Email text Box", action.BOOLEAN)){
				sa.assertTrue(false,"Not able to pass data to fund Email text box.");
			}
			if(!sendKeys(driver, fp.getDescriptionTextBox(Workspace.InvestorWorkspace, 60), fundDesription, "Description text Box", action.BOOLEAN)){
				sa.assertTrue(false,"Not able to pass data to fund Description text box.");
			}
			if(click(driver, fp.getNext1Of3Button(Workspace.InvestorWorkspace, 60), "Next Button", action.BOOLEAN)){
			appLog.info("Clicked on next button");
			if(click(driver, fp.getNext2Of3Button(Workspace.InvestorWorkspace, 60), "Next button 2Of3", action.BOOLEAN)){
				appLog.info("Clicked on next button");
			if(fp.getBuildWorkspaceStep3Of3Header(Workspace.InvestorWorkspace, 60)!=null){
				appLog.info("Build Workspace (Step 3 of 3)' pop up is displaying");				
			}else{
				appLog.error("Build Workspace (Step 3 of 3)' pop up is not displaying");
				sa.assertTrue(false, "Build Workspace (Step 3 of 3)' pop up is not displaying");
			}
		if(fp.getInvestorNameInBuildInvestorWorkspaceStep3FilterSection(60).size()==4){
			appLog.info("By default 4 institution is displaying");			
		}else{
			appLog.error("By default 4 institution is not displaying");
			sa.assertTrue(false, "By default 4 institution is not displaying");
		}
		if (selectVisibleTextFromDropDown(driver,fp.getManageInvestorFilterFieldDropdown(Workspace.InvestorWorkspace).get(0),"Field Dropdown", "Account:Total Actual Fees")) {
			appLog.info("Selected field dropdown value");
			if (selectVisibleTextFromDropDown(driver,
					fp.getManageInvestorFilterOperatorDropdown(Workspace.InvestorWorkspace).get(0),"Operator Dropdown", "not equal to")) {
				appLog.info("Selected Operator dropdown value");
				if (sendKeys(driver,fp.getManageInvestorFilterValueTextBox(Workspace.InvestorWorkspace).get(0),
						"75", "Value text box", action.SCROLLANDBOOLEAN)) {
					if (click(driver,
							fp.getManageInvestorFilterApplyButton(Workspace.InvestorWorkspace, 60),
							"Apply buton", action.SCROLLANDBOOLEAN)) {
						if(fp.getInvestorNameInBuildInvestorWorkspaceStep3FilterSection(60).size()==4){
							appLog.info("4 institution are displaying");			
						}else{
							appLog.error("4 institution are not displaying");
							sa.assertTrue(false, "4 institution are not displaying");
						}
						for (int i=0;i<fp.getInvestorNameInBuildInvestorWorkspaceStep3FilterSection(60).size();i++){
							String InstitutionNameText=fp.getInvestorNameInBuildInvestorWorkspaceStep3FilterSection(60).get(i).getText().trim();
							if(InstitutionNameText.equalsIgnoreCase(institutionname[i])){
								appLog.info(institutionname[i]+"is displaying");
							}else{
								appLog.error(institutionname[i]+"is not displaying");
								sa.assertTrue(false, institutionname[i]+"is not displaying");
							}							
						}	
					} else {
						appLog.info("Not able to click on apply button");
						sa.assertTrue(false, "Not able to click on apply button");
					}
				} else {
					appLog.info("Not able to enter value in value tetxbox");
					sa.assertTrue(false, "Not able to enter value in value tetxbox");
				}
			} else {
				appLog.info("Not able to select Opeartor dropdown value");
				sa.assertTrue(false, "Not able to select Opeartor dropdown value");
			}
		} else {
			appLog.info("Not able to select field dropdown value");
			sa.assertTrue(false, "Not able to select field dropdown value");
		}
		if (click(driver, fp.getManageInvestorFilterClearButton(Workspace.InvestorWorkspace, 60),
				"Clear Buton", action.SCROLLANDBOOLEAN)) {
			appLog.info("Clicked on clear button successfully");
		} else {
			appLog.info("Not able to click on clear button");
			sa.assertTrue(false, "Not able to click on clear button");
		}
		String text = getSelectedOptionOfDropDown(driver, fp.getStep3Of3FieldDropDown(Workspace.InvestorWorkspace, 30), "Filter Field  drop down", "text");
		if(text.trim().equalsIgnoreCase("--None--")){
			appLog.info("--None-- is by default selected in the field drop down.");
		} else {
			appLog.error("--None-- is not selected in the field drop down. Expected: --None--\tActual: "+text);
			sa.assertTrue(false,"--None-- is not selected in the field drop down. Expected: --None--\tActual: "+text);
		}
		
		text = getSelectedOptionOfDropDown(driver, fp.getStep3Of3OperatorDropDown(Workspace.InvestorWorkspace, 30), "Filter Field  drop down", "text");
		if(text.trim().equalsIgnoreCase("equals")){
			appLog.info("equals is by default selected in the operator drop down.");
		} else {
			appLog.error("equals is not selected in the operator drop down. Expected: equals\tActual: "+text);
			sa.assertTrue(false,"equals is not selected in the operator drop down. Expected: equals\tActual: "+text);
		}
		
		text = getAttribute(driver, fp.getStep3Of3FilterTextBox(Workspace.InvestorWorkspace, 30), "", "value");
		if(text==null || text.isEmpty()){
			appLog.info("Criterion box is empty and verified.");
		} else {
			appLog.error("Criterion box is not empty.");
			sa.assertTrue(false,"Criterion box is not empty.");
		}
		if(fp.getInvestorNameInBuildInvestorWorkspaceStep3FilterSection(60).size()==4){
			appLog.info("4 institution is displaying");			
		}else{
			appLog.error("4 institution is not displaying");
			sa.assertTrue(false, "4 institution is not displaying");
		}
		for (int i=0;i<fp.getInvestorNameInBuildInvestorWorkspaceStep3FilterSection(60).size();i++){
			String InstitutionNameText=fp.getInvestorNameInBuildInvestorWorkspaceStep3FilterSection(60).get(i).getText().trim();
			if(InstitutionNameText.equalsIgnoreCase(institutionname[i])){
				appLog.info(institutionname[i]+"is displaying");
			}else{
				appLog.error(institutionname[i]+"is not displaying");
				sa.assertTrue(false, institutionname[i]+"is not displaying");
			}							
		}
		}else{
				appLog.error("Not able to click on next step 2 of 3 button");
				sa.assertTrue(false, "Not able to click on next step 2 of 3 button");
			}
			}else{
				appLog.error("Not able to click on next step 1 of 3 button");
				sa.assertTrue(false, "Not able to click on next step 1 of 3 button");
			}			
		}else{
			appLog.error("Not able to click on build Investor workspace button");
			sa.assertTrue(false, "Not able to click on build Investor workspace button");
		}
		}else{
				appLog.error("Not able to click on created funs");
				sa.assertTrue(false, "Not able ot click on created fund");
			}
		}else{
			appLog.error("Not able to click on funds tab");
			sa.assertTrue(false, "Not able to click on funds tab");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();		
		}	
		
	@Test
	public void M8tc036_VerifyFilterAsPerFilterSingleRowFilterInvestorMultipleRowFilterInvestorSheet(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		SoftAssert sa = new SoftAssert();
		SoftAssert saa1=new SoftAssert();
		String fundSize=  ExcelUtils.readData(filterPath,"Funds", 1, 3);
		 String fundVintageYear=ExcelUtils.readData(filterPath,"Funds", 1, 4);
		 String fundContact=ExcelUtils.readData(filterPath,"Funds", 1, 5);
		 String fundPhone=ExcelUtils.readData(filterPath,"Funds", 1, 6);
		 String fundEmail=ExcelUtils.readData(filterPath,"Funds", 1, 7);
		 String fundDesription=ExcelUtils.readData(filterPath,"Funds", 1, 8) ;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		for (int i = 0; i < 2; i++) {
		if (bp.clickOnTab(TabName.FundsTab)) {
			if (fp.clickOnCreatedFund(ExcelUtils.readData(filterPath,"Funds", 1, 0))) {
				switchToFrame(driver, 30, bp.getFrame(PageName.FundsPage, 60));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 60),
						"InvestorWorkspace View.");
				if(click(driver, fp.getBuildInvestorWorkspace(60), "Build Investor button", action.SCROLLANDBOOLEAN)){
					if(!sendKeys(driver,fp.getSizeInMillionTextBox(Workspace.InvestorWorkspace, 60),fundSize, "Size in Million text box", action.BOOLEAN)){
						sa.assertTrue(false,"Not able to pass data to size in million text box.");
					}
					if(!sendKeys(driver, fp.getVintageYear(Workspace.InvestorWorkspace, 60), fundVintageYear, "vintage Year", action.BOOLEAN)){
					sa.assertTrue(false,"Not able to pass data to Vintage Year text box.");
					}
					if(!sendKeys(driver, fp.getContactTextBox(Workspace.InvestorWorkspace, 60), fundContact, "Contact text Box", action.BOOLEAN)){
						sa.assertTrue(false,"Not able to pass data to contact text box.");
					}
					if(!sendKeys(driver, fp.getPhoneTextBox(Workspace.InvestorWorkspace, 60), fundPhone, "Phone text Box", action.BOOLEAN)){
					sa.assertTrue(false,"Not able to pass data to Phone text box.");
					}
					if(!sendKeys(driver, fp.getEmailTextBox(Workspace.InvestorWorkspace, 60), fundEmail, "Email text Box", action.BOOLEAN)){
						sa.assertTrue(false,"Not able to pass data to fund Email text box.");
					}
					if(!sendKeys(driver, fp.getDescriptionTextBox(Workspace.InvestorWorkspace, 60), fundDesription, "Description text Box", action.BOOLEAN)){
						sa.assertTrue(false,"Not able to pass data to fund Description text box.");
					}
					if(click(driver, fp.getNext1Of3Button(Workspace.InvestorWorkspace, 60), "Next Button", action.BOOLEAN)){
					appLog.info("Clicked on next button");
					if(click(driver, fp.getNext2Of3Button(Workspace.InvestorWorkspace, 60), "Next button 2Of3", action.BOOLEAN)){
						appLog.info("Clicked on next button");
						
					if (i == 0) {
						sa = fp.checkFilterSingle(filterPath,Workspace.InvestorWorkspace, "Single row filter_Investor",30);
						switchToDefaultContent(driver);
						appLog.info("Single Filter is Done Successfully.");			
					} else if (i == 1) {
						saa1 = fp.applyCriterionOnManageInvestor(filterPath,"Multiple row filter_Investor", Workspace.InvestorWorkspace,30);
						switchToDefaultContent(driver);
						appLog.info("Done");
					}						
			}else{
					appLog.error("Not able to click on next step 2 of 3 button");
					sa.assertTrue(false, "Not able to click on next step 2 of 3 button");
				}
				}else{
					appLog.error("Not able to click on next step 1 of 3 button");
					sa.assertTrue(false, "Not able to click on next step 1 of 3 button");
				}			
			}else{
				appLog.error("Not able to click on build Investor workspace button");
				sa.assertTrue(false, "Not able to click on build Investor workspace button");
			}
		} else {
			appLog.info("Not able to click on created fund");
			sa.assertTrue(false, "Not able to click on created fund");
		}
	} else {
		appLog.info("Not able to click on Funds tab");
		sa.assertTrue(false, "Not able to click on Funds tab");
	}
	}
	switchToDefaultContent(driver);
	lp.CRMlogout();
	try{	
		sa.assertAll();
	} catch(Throwable th){
		appLog.error("Following asserts failed in single filter: ");
		appLog.info(th.getLocalizedMessage());
		saa1.assertTrue(false,"Some filters failed in single assert.");
	}	
	saa1.assertAll();
	sa.assertAll();			
	}
	
	@Test
	public void M8tc037_PostConditionForAll() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName, adminPassword);
		lp.postCondition().assertAll();
		lp.CRMlogout();
	}
}
