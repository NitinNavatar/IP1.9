/**
 * 
 */
package com.navatar.scripts;

import static com.navatar.generic.AppListeners.appLog;
import static com.navatar.generic.CommonLib.*;
import static com.navatar.generic.CommonVariables.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.util.Internal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.navatar.generic.BaseLib;
import com.navatar.generic.EmailLib;
import com.navatar.generic.ExcelUtils;
import com.navatar.generic.CommonLib.CreationPage;
import com.navatar.generic.CommonLib.FolderType;
import com.navatar.generic.CommonLib.InstitutionPageFieldLabelText;
import com.navatar.generic.CommonLib.PageName;
import com.navatar.generic.CommonLib.TabName;
import com.navatar.generic.CommonLib.WorkSpaceAction;
import com.navatar.generic.CommonLib.Workspace;
import com.navatar.generic.CommonLib.action;
import com.navatar.generic.CommonLib.excelLabel;
import com.navatar.pageObjects.AllFirmsPageBusinesslayer;
import com.navatar.pageObjects.BasePageBusinessLayer;
import com.navatar.pageObjects.BasePageErrorMessage;
import com.navatar.pageObjects.CommitmentPageBusinessLayer;
import com.navatar.pageObjects.ContactPageBusinessLayer;
import com.navatar.pageObjects.DisclaimerPageBussinessLayer;
import com.navatar.pageObjects.FundRaisingPageBusinessLayer;
import com.navatar.pageObjects.FundsPageBusinessLayer;
import com.navatar.pageObjects.FundsPageErrorMessage;
import com.navatar.pageObjects.InstitutionPageBusinessLayer;
import com.navatar.pageObjects.InvestorFirmPageBusinesslayer;
import com.navatar.pageObjects.LoginPageBusinessLayer;
import com.navatar.pageObjects.NIMPageBusinessLayer;
import com.navatar.pageObjects.PartnershipPageBusinessLayer;
import static com.navatar.generic.CommonLib.*;
/**
 * @author Ankur Rana
 *
 */
public class Module5 extends BaseLib {

//	Scanner scn = new Scanner(System.in);
	@Test
	public void M5tc001_Module5_preCondition(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
//		if(lp.preCondition(superAdminUserName, CRMUser1FirstName+" "+CRMUser1LastName, CRMUser1EmailID, EnableDisable.Disable, EnableDisable.Disable, accessType.AdminUserAccess)){
//			appLog.info("Provided admin user access to crm user "+CRMUser1FirstName+" "+CRMUser1LastName+".");
//		} else {
//			appLog.error("Not able to provide admin user access to crm user "+CRMUser1FirstName+" "+CRMUser1LastName+".");
//			sa.assertTrue(false,"Not able to provide admin user access to crm user "+CRMUser1FirstName+" "+CRMUser1LastName+".");
//		}
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		InstitutionPageBusinessLayer inst = new InstitutionPageBusinessLayer(driver);
		for(int i = 1; i < 3; i++)
			if(lp.clickOnTab(TabName.InstituitonsTab)){
				if(inst.createInstitution(environment,mode,ExcelUtils.readData("Institutions", excelLabel.Variable_Name, "M5I"+i, excelLabel.Institutions_Name),"Institution",null,null)){
					appLog.info("Institution '"+ExcelUtils.readData("Institutions", excelLabel.Variable_Name, "M5I"+i, excelLabel.Institutions_Name)+"' is created successfully.");
				} else {
					appLog.error(ExcelUtils.readData("Institutions", excelLabel.Variable_Name, "M5I"+i, excelLabel.Institutions_Name)+" institution is not created.");
					sa.assertTrue(false,ExcelUtils.readData("Institutions", excelLabel.Variable_Name, "M5I"+i, excelLabel.Institutions_Name)+" institution is not created.");
				}
			} else {
				appLog.error("Insitution Tab cannot be clicked, So cannot create institution.");
				sa.assertTrue(false,"Insitution Tab cannot be clicked, So cannot create institution.");
			}
		if(lp.clickOnTab(TabName.InstituitonsTab)){
			if(inst.createInstitution(environment,mode,M5LP1,"Limited Partner",InstitutionPageFieldLabelText.Parent_Institution.toString(), M5I1)){
				appLog.info(M5LP1+" LP Created successfully.");
			} else {
				appLog.error(M5LP1+" LP created successfully.");
				sa.assertTrue(false,M5LP1+" LP created successfully.");
			}
		} else {
			appLog.error("Insitution Tab cannot be clicked, So cannot create LP.");
			sa.assertTrue(false,"Insitution Tab cannot be clicked, So cannot create LP.");
		}
		if(lp.clickOnTab(TabName.InstituitonsTab)){
			if(inst.createInstitution(environment,mode,M5LP2,"Limited Partner",InstitutionPageFieldLabelText.Parent_Institution.toString(), M5I2)){
				appLog.info(M5LP2+" LP Created successfully.");
			} else {
				appLog.error(M5LP2+" LP created successfully.");
				sa.assertTrue(false,M5LP2+" LP created successfully.");
			}
		} else {
			appLog.error("Insitution Tab cannot be clicked, So cannot create LP.");
			sa.assertTrue(false,"Insitution Tab cannot be clicked, So cannot create LP.");
		}
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		String emailId = cp.generateRandomEmailId();
		if(lp.clickOnTab(TabName.ContactTab)){
			if(cp.createContact(environment,mode,M5CFN1, M5CLN1, M5I1, emailId,null,null,CreationPage.ContactPage)){
				appLog.info(M5CFN1+" "+M5CLN1+" contact is created successfully.");
				ExcelUtils.writeData(emailId, "Contacts", excelLabel.Variable_Name, "M5Contact1", excelLabel.Contact_EmailId);
			} else {
				appLog.error(M5CFN1+" "+M5CLN1+" contact is not created.");
				sa.assertTrue(false,M5CFN1+" "+M5CLN1+" contact is not created.");
			}
		} else {
			appLog.error("Contact tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"Contact tab cannot be clicked, So cannot continue with the TC.");
		}
		
		emailId = cp.generateRandomEmailId();
		if(lp.clickOnTab(TabName.ContactTab)){
			if(cp.createContact(environment,mode,M5CFN2, M5CLN2, M5I2, emailId,null,null,CreationPage.ContactPage)){
				appLog.info(M5CFN2+" "+M5CLN2+" contact is created successfully.");
				ExcelUtils.writeData(emailId, "Contacts", excelLabel.Variable_Name, "M5Contact2", excelLabel.Contact_EmailId);
			} else {
				appLog.error(M5CFN2+" "+M5CLN2+" contact is not created.");
				sa.assertTrue(false,M5CFN2+" "+M5CLN2+" contact is not created.");
			}
		} else {
			appLog.error("Contact tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"Contact tab cannot be clicked, So cannot continue with the TC.");
		}
		
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.createFund(environment,mode,M5F1, M5FT1, M5FIC1,null,null)){
				appLog.info(M5F1+" fund created successfully");
			} else {
				appLog.error(M5F1+" fund cannot be created.");
				sa.assertTrue(false,M5F1+" fund cannot be created.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot continue with the TC.");
		}
		
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.createFund(environment,mode,M5F2, M5FT2, M5FIC2,null,null)){
				appLog.info(M5F2+" fund created successfully");
			} else {
				appLog.error(M5F2+" fund cannot be created.");
				sa.assertTrue(false,M5F2+" fund cannot be created.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot continue with the TC.");
		}
		
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.createFund(environment,mode,M5F3, M5FT3, M5FIC3,null,null)){
				appLog.info(M5F3+" fund created successfully");
			} else {
				appLog.error(M5F3+" fund cannot be created.");
				sa.assertTrue(false,M5F3+" fund cannot be created.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot continue with the TC.");
		}
		
		FundRaisingPageBusinessLayer fdr = new FundRaisingPageBusinessLayer(driver);
		if(lp.clickOnTab(TabName.FundraisingsTab)){
			if(fdr.createFundRaising(environment,mode,M5FR1, M5F1, M5I1)){
				appLog.info(M5FR1+" fundraising created successfully.");
			} else {
				appLog.error(M5FR1+" fundraising not created.");
				sa.assertTrue(false,M5FR1+" fundraising not created.");
			}
		} else {
			appLog.error("fundraising tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"fundraising tab cannot be clicked, So cannot continue with the TC.");
		}
		
		if(lp.clickOnTab(TabName.FundraisingsTab)){
			if(fdr.createFundRaising(environment,mode,M5FR2, M5F2, M5I1)){
				appLog.info(M5FR2+" fundraising created successfully.");
			} else {
				appLog.error(M5FR2+" fundraising not created.");
				sa.assertTrue(false,M5FR2+" fundraising not created.");
			}
		} else {
			appLog.error("fundraising tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"fundraising tab cannot be clicked, So cannot continue with the TC.");
		}
		
		if(lp.clickOnTab(TabName.FundraisingsTab)){
			if(fdr.createFundRaising(environment,mode,M5FR3, M5F2, M5I2)){
				appLog.info(M5FR3+" fundraising created successfully.");
			} else {
				appLog.error(M5FR3+" fundraising not created.");
				sa.assertTrue(false,M5FR3+" fundraising not created.");
			}
		} else {
			appLog.error("fundraising tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"fundraising tab cannot be clicked, So cannot continue with the TC.");
		}
		
		PartnershipPageBusinessLayer prt= new PartnershipPageBusinessLayer(driver);
		if(lp.clickOnTab(TabName.PartnershipsTab)){
			if(prt.createPartnership(environment,mode,M5P1, M5F2)){
				appLog.info(M5P1+" PartnerShip created successfully.");
			} else {
				appLog.error(M5P1+" partnership not created.");
				sa.assertTrue(false,M5P1+" partnership not created.");
			}
		} else {
			appLog.error("PartnerShip tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"PartnerShip tab cannot be clicked, So cannot continue with the TC.");
		}
		
		if(lp.clickOnTab(TabName.PartnershipsTab)){
			if(prt.createPartnership(environment,mode,M5P2, M5F1)){
				appLog.info(M5P2+" PartnerShip created successfully.");
			} else {
				appLog.error(M5P2+" partnership not created.");
				sa.assertTrue(false,M5P2+" partnership not created.");
			}
		} else {
			appLog.error("PartnerShip tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"PartnerShip tab cannot be clicked, So cannot continue with the TC.");
		}
		
		CommitmentPageBusinessLayer cmt = new CommitmentPageBusinessLayer(driver);
		if(lp.clickOnTab(TabName.CommitmentsTab)){
			if(cmt.createCommitment(environment,mode,M5LP1, M5P2, "M5CMT1", null)){
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
			if(cmt.createCommitment(environment,mode,M5LP1, M5P1, "M5CMT2", null)){
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
			if(cmt.createCommitment(environment,mode,M5LP2, M5P1, "M5CMT3", null)){
				appLog.info("Commitment created successfully.");
			} else {
				appLog.error("Commitment not created.");
				sa.assertTrue(false,"Commitment not created.");
			}
		} else {
			appLog.error("Commitments tab cannot be clicked, So cannot continue with the TC.");
			sa.assertTrue(false,"Commitments tab cannot be clicked, So cannot continue with the TC.");
		}
		
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		if(lp.clickOnTab(TabName.NIMTab)){
			switchToFrame(driver, 30, nim.getFrame( PageName.NavatarInvestorManager, 30));
			if(nim.createFolderTemplate("FolderTemp",folderTemplateName,folderTemplateName, 60)){	
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
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Test
	public void M5tc002_CreateFundraisingWorkspaceAndVerifyManageFolder(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		if(fp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M5F1)){
				String[] step1Of3Data = {"2100","2015","abc","987654321","abc@abc.com","Dummy description"};
				if(fp.buildWorkspace(step1Of3Data, WorkSpaceAction.WITHOUTEMPLATE, null, null, null, Workspace.FundraisingWorkspace, 60)){
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					if(click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 60), "Manage Folder Icon", action.BOOLEAN)){
						if(click(driver, fp.getManageFolderCrossIcon(Workspace.FundraisingWorkspace, 30), "Cross icon", action.BOOLEAN)){
							ThreadSleep(1500);
							if(click(driver, fp.getManageFolderCrossIcon(Workspace.FundraisingWorkspace, 2), "", action.BOOLEAN)){
								appLog.error("Manage folder cross icon is not working.");
								sa.assertTrue(false,"Manage folder cross icon is not working.");
								fp.recover(Workspace.FundraisingWorkspace, 60);
							} else {
								appLog.info("Manage folder cross icon is working.");
							}
						} else {
							appLog.error("Manage folder cross icon cannot be clicked, So cannot check the functionlity.");
							sa.assertTrue(false,"Manage folder cross icon cannot be clicked, So cannot check the functionlity.");
							fp.recover(Workspace.FundraisingWorkspace, 60);
						}
					} else {
						appLog.error("Manage folder icon cannot be clicked, So cannot check manage folder.");
						sa.assertTrue(false,"Manage folder icon cannot be clicked, So cannot check manage folder.");
					}
					
					if(click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 60), "Manage Folder Icon", action.BOOLEAN)){
						if(click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 30), "Close Button", action.BOOLEAN)){
							ThreadSleep(1500);
							if(click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 2), "", action.BOOLEAN)){
								appLog.error("Manage folder Close Button is not working.");
								sa.assertTrue(false,"Manage folder Close Button is not working.");
								fp.recover(Workspace.FundraisingWorkspace, 60);
							} else {
								appLog.info("Manage folder Close Button is working.");
							}
						} else {
							appLog.error("Manage folder Close button cannot be clicked, So cannot check the functionlity.");
							sa.assertTrue(false,"Manage folder Close button cannot be clicked, So cannot check the functionlity.");
							fp.recover(Workspace.FundraisingWorkspace, 60);
						}
					} else {
						appLog.error("Manage folder icon cannot be clicked, So cannot check manage folder.");
						sa.assertTrue(false,"Manage folder icon cannot be clicked, So cannot check manage folder.");
					}
					
					if(click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 60), "Manage Folder Icon", action.BOOLEAN)){
						if(trim(getText(driver, fp.getManageFolderHeader(Workspace.FundraisingWorkspace, 30), "Manage Folder header", action.BOOLEAN)).equalsIgnoreCase("Manage Folders")){
							appLog.info("Manage folder header is verified.");
						} else {
							appLog.error("Manage Folder Header is not verified. Expected: Manage Folders\tActual: "+trim(getText(driver, fp.getManageFolderHeader(Workspace.FundraisingWorkspace, 30), "Manage Folder header", action.BOOLEAN)));
							sa.assertTrue(false,"Manage Folder Header is not verified. Expected: Manage Folders\tActual: "+trim(getText(driver, fp.getManageFolderHeader(Workspace.FundraisingWorkspace, 30), "Manage Folder header", action.BOOLEAN)));
						}
						if(trim(getText(driver, fp.getAllFolderLabel(Workspace.FundraisingWorkspace, 30), "", action.BOOLEAN)).contains("All Folders")){
							appLog.info("All folder label is verified.");
						} else {
							appLog.error("All folder is not present on manage folder pop up. Expected: All Folder\tActual: "+trim(getText(driver, fp.getAllFolderLabel(Workspace.FundraisingWorkspace, 30), "", action.BOOLEAN)));
							sa.assertTrue(false,"All folder is not present on manage folder pop up. Expected: All Folder\tActual: "+trim(getText(driver, fp.getAllFolderLabel(Workspace.FundraisingWorkspace, 30), "", action.BOOLEAN)));
						}
						if(fp.getAllFolderAddIcon(Workspace.FundraisingWorkspace, 30)!=null){
							appLog.info("All folder add icon is verified.");
						} else {
							appLog.error("All Folder add icon is not present.");
							sa.assertTrue(false,"All Folder add icon is not present.");
						}
						
						if(trim(getText(driver, fp.getManageFolderYellowBoxMessage(Workspace.FundraisingWorkspace, 30), "", action.BOOLEAN)).equalsIgnoreCase(FundsPageErrorMessage.ManageFolderPopUPYellowBlockMessage)){
							appLog.info("Yellow box message is verified.");
						} else {
							appLog.error("Yellow box message is not verified.Expected: "+FundsPageErrorMessage.ManageFolderPopUPYellowBlockMessage+"\tActual: "+trim(getText(driver, fp.getManageFolderYellowBoxMessage(Workspace.FundraisingWorkspace, 30), "", action.BOOLEAN)));
							sa.assertTrue(false,"Yellow box message is not verified.Expected: "+FundsPageErrorMessage.ManageFolderPopUPYellowBlockMessage+"\tActual: "+trim(getText(driver, fp.getManageFolderYellowBoxMessage(Workspace.FundraisingWorkspace, 30), "", action.BOOLEAN)));
						}
						if(click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 30), "Cross icon", action.BOOLEAN)){
						} else {
							fp.recover(Workspace.FundraisingWorkspace, 60);
						}
					} else {
						appLog.error("Manage folder icon cannot be clicked, So cannot check manage folder.");
						sa.assertTrue(false,"Manage folder icon cannot be clicked, So cannot check manage folder.");
					}
					
				} else {
					appLog.error("Workspace is not built for fund '"+M5F1+"', So cannot verify Manage folder.");
					sa.assertTrue(false,"Workspace is not built for fund '"+M5F1+"', So cannot verify Manage folder.");
				}
			} else {
				appLog.error(M5F1+" fund is not present, So cannot continue with the tc.");
				sa.assertTrue(false,M5F1+" fund is not present, So cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, so cannot create workspace and cannot continue with tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, so cannot create workspace and cannot continue with tc.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Test
	public void M5tc003_CreateInvestorWorkspaceAndVerifyManageFolder(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InstitutionPageBusinessLayer instiPage = new InstitutionPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		CommitmentPageBusinessLayer com = new CommitmentPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M5F2)){
				String[] step1Of3Data = {"2100","2015","abc","987654321","abc@abc.com","Dummy description"};
				if(fp.buildWorkspace(step1Of3Data, WorkSpaceAction.CREATEFOLDERTEMPLATE, null, "FolderTemp", M5I1+"/"+M5LP1+"<break>"+M5I2+"/"+M5LP2, Workspace.InvestorWorkspace, 60)){
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					if(click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 60), "Manage Folder Icon", action.BOOLEAN)){
						if(trim(getText(driver, fp.getManageFolderHeader(Workspace.InvestorWorkspace, 30), "Manage Folder header", action.BOOLEAN)).equalsIgnoreCase("Manage Folders")){
							appLog.info("Manage folder header is verified.");
						} else {
							appLog.error("Manage Folder Header is not verified. Expected: Manage Folders\tActual: "+trim(getText(driver, fp.getManageFolderHeader(Workspace.InvestorWorkspace, 30), "Manage Folder header", action.BOOLEAN)));
							sa.assertTrue(false,"Manage Folder Header is not verified. Expected: Manage Folders\tActual: "+trim(getText(driver, fp.getManageFolderHeader(Workspace.InvestorWorkspace, 30), "Manage Folder header", action.BOOLEAN)));
						}
						if(trim(getText(driver, fp.getAllFolderLabel(Workspace.InvestorWorkspace, 30), "", action.BOOLEAN)).contains("All Folders")){
							appLog.info("All folder label is verified.");
						} else {
							appLog.error("All folder is not present on manage folder pop up. Expected: All Folder\tActual: "+trim(getText(driver, fp.getAllFolderLabel(Workspace.InvestorWorkspace, 30), "", action.BOOLEAN)));
							sa.assertTrue(false,"All folder is not present on manage folder pop up. Expected: All Folder\tActual: "+trim(getText(driver, fp.getAllFolderLabel(Workspace.InvestorWorkspace, 30), "", action.BOOLEAN)));
						}
						if(fp.getAllFolderAddIcon(Workspace.InvestorWorkspace, 30)!=null){
							appLog.info("All folder add icon is verified.");
						} else {
							appLog.error("All Folder add icon is not present.");
							sa.assertTrue(false,"All Folder add icon is not present.");
						}
						
						if(trim(getText(driver, fp.getManageFolderYellowBoxMessage(Workspace.InvestorWorkspace, 30), "", action.BOOLEAN)).equalsIgnoreCase(FundsPageErrorMessage.ManageFolderPopUPYellowBlockMessage)){
							appLog.info("Yellow box message is verified.");
						} else {
							appLog.error("Yellow box message is not verified.Expected: "+FundsPageErrorMessage.ManageFolderPopUPYellowBlockMessage+"\tActual: "+trim(getText(driver, fp.getManageFolderYellowBoxMessage(Workspace.InvestorWorkspace, 30), "", action.BOOLEAN)));
							sa.assertTrue(false,"Yellow box message is not verified.Expected: "+FundsPageErrorMessage.ManageFolderPopUPYellowBlockMessage+"\tActual: "+trim(getText(driver, fp.getManageFolderYellowBoxMessage(Workspace.InvestorWorkspace, 30), "", action.BOOLEAN)));
						}
						if(click(driver, fp.getManageFolderCloseButton(Workspace.InvestorWorkspace, 30), "Cross icon", action.BOOLEAN)){
						} else {
							fp.recover(Workspace.InvestorWorkspace, 60);
						}
					} else {
						appLog.error("Manage folder icon cannot be clicked, So cannot check manage folder.");
						sa.assertTrue(false,"Manage folder icon cannot be clicked, So cannot check manage folder.");
					}
					switchToDefaultContent(driver);
					if(fp.clickOnTab(TabName.InstituitonsTab)){
						if(instiPage.clickOnCreatedInstitution(M5I1)){
							switchToFrame(driver, 30, instiPage.getFrame( PageName.InstitutionsPage, 30));
							scrollDownThroughWebelement(driver, instiPage.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), Workspace.InvestorWorkspace+" View.");
							if(instiPage.VerifyFolderStructure("FolderTemp", null, M5LP1, M5F2, Workspace.InvestorWorkspace, PageName.InstitutionsPage, 30)){
								appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
							} else {
								appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
								sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
							}
							switchToDefaultContent(driver);
						} else {
							appLog.error(M5I1+" Instituion is not present in the list.");
							sa.assertTrue(false,M5I1+" Instituion is not present in the list.");
						}
					} else {
						appLog.error("Insitutions Tab cannot be clicked, So cannot verify folder structure on institution page.");
						sa.assertTrue(false,"Insitutions Tab cannot be clicked, So cannot verify folder structure on institution page.");
					}
					
					if(fp.clickOnTab(TabName.InstituitonsTab)){
						if(instiPage.clickOnCreatedInstitution(M5I2)){
							switchToFrame(driver, 30, instiPage.getFrame( PageName.InstitutionsPage, 30));
							scrollDownThroughWebelement(driver, instiPage.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), Workspace.InvestorWorkspace+" View.");
							if(instiPage.VerifyFolderStructure("FolderTemp", null, M5LP2, M5F2, Workspace.InvestorWorkspace, PageName.InstitutionsPage, 30)){
								appLog.info("Folder structure is verified on instituion page of '"+M5I2+"'.");
							} else {
								appLog.error("Folder structure is not verified on instituion page of '"+M5I2+"'.");
								sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I2+"'.");
							}
							switchToDefaultContent(driver);
						} else {
							appLog.error(M5I2+" Instituion is not present in the list.");
							sa.assertTrue(false,M5I2+" Instituion is not present in the list.");
						}
					} else {
						appLog.error("Insitutions Tab cannot be clicked, So cannot verify folder structure on institution page.");
						sa.assertTrue(false,"Insitutions Tab cannot be clicked, So cannot verify folder structure on institution page.");
					}
					
					if(fp.clickOnTab(TabName.InstituitonsTab)){
						if(instiPage.clickOnCreatedLP(environment, mode,M5LP1)){
							switchToFrame(driver, 30, instiPage.getFrame( PageName.LimitedPartnerPage, 30));
							scrollDownThroughWebelement(driver, instiPage.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), Workspace.InvestorWorkspace+" View.");
							if(instiPage.VerifyFolderStructure("FolderTemp", null, null, M5F2, Workspace.InvestorWorkspace, PageName.InstitutionsPage, 30)){
								appLog.info("Folder structure is verified on LP page of '"+M5LP1+"'.");
							} else {
								appLog.error("Folder structure is not verified on LP page of '"+M5LP1+"'.");
								sa.assertTrue(false,"Folder structure is not verified on LP page of '"+M5LP1+"'.");
							}
							switchToDefaultContent(driver);
						} else {
							appLog.error(M5LP1+" LP is not present in the list.");
							sa.assertTrue(false,M5LP1+" LP is not present in the list.");
						}
					} else {
						appLog.error("Insitutions Tab cannot be clicked, So cannot verify folder structure on LP page.");
						sa.assertTrue(false,"Insitutions Tab cannot be clicked, So cannot verify folder structure on LP page.");
					}
					
					if(fp.clickOnTab(TabName.InstituitonsTab)){
						if(instiPage.clickOnCreatedLP(environment, mode,M5LP2)){
							switchToFrame(driver, 30, instiPage.getFrame( PageName.LimitedPartnerPage, 30));
							scrollDownThroughWebelement(driver, instiPage.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), Workspace.InvestorWorkspace+" View.");
							if(instiPage.VerifyFolderStructure("FolderTemp", null, null, M5F2, Workspace.InvestorWorkspace, PageName.InstitutionsPage, 30)){
								appLog.info("Folder structure is verified on LP page of '"+M5LP2+"'.");
							} else {
								appLog.error("Folder structure is not verified on LP page of '"+M5LP2+"'.");
								sa.assertTrue(false,"Folder structure is not verified on LP page of '"+M5LP2+"'.");
							}
							switchToDefaultContent(driver);
						} else {
							appLog.error(M5LP2+" LP is not present in the list.");
							sa.assertTrue(false,M5LP2+" LP is not present in the list.");
						}
					} else {
						appLog.error("Insitutions Tab cannot be clicked, So cannot verify folder structure on LP page.");
						sa.assertTrue(false,"Insitutions Tab cannot be clicked, So cannot verify folder structure on LP page.");
					}
					
					if(fp.clickOnTab(TabName.CommitmentsTab)){
						if(com.clickOnCreatedCommitmentId(environment, mode,M5CMT2)){
							switchToFrame(driver, 30, instiPage.getFrame( PageName.CommitmentsPage, 30));
							scrollDownThroughWebelement(driver, instiPage.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), Workspace.InvestorWorkspace+" View.");
							if(instiPage.VerifyFolderStructure("FolderTemp", null, M5LP1, null, Workspace.InvestorWorkspace, PageName.CommitmentsPage, 30)){
								appLog.info("Folder structure is verified on commitment page of '"+M5CMT2+"'.");
							} else {
								appLog.error("Folder structure is not verified on commitment page of '"+M5CMT2+"'.");
								sa.assertTrue(false,"Folder structure is not verified on commitment page of '"+M5CMT2+"'.");
							}
							switchToDefaultContent(driver);
						} else {
							appLog.error(M5CMT2+" Instituion is not present in the list.");
							sa.assertTrue(false,M5CMT2+" Instituion is not present in the list.");
						}
					} else {
						appLog.error("Insitutions Tab cannot be clicked, So cannot verify folder structure on institution page.");
						sa.assertTrue(false,"Insitutions Tab cannot be clicked, So cannot verify folder structure on institution page.");
					}
					
					if(fp.clickOnTab(TabName.CommitmentsTab)){
						if(com.clickOnCreatedCommitmentId(environment, mode,M5CMT3)){
							switchToFrame(driver, 30, instiPage.getFrame( PageName.CommitmentsPage, 30));
							scrollDownThroughWebelement(driver, instiPage.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), Workspace.InvestorWorkspace+" View.");
							if(instiPage.VerifyFolderStructure("FolderTemp", null, M5LP2, null, Workspace.InvestorWorkspace, PageName.CommitmentsPage, 30)){
								appLog.info("Folder structure is verified on commitment page of '"+M5CMT3+"'.");
							} else {
								appLog.error("Folder structure is not verified on commitment page of '"+M5CMT3+"'.");
								sa.assertTrue(false,"Folder structure is not verified on commitment page of '"+M5CMT3+"'.");
							}
							switchToDefaultContent(driver);
						} else {
							appLog.error(M5CMT3+" Instituion is not present in the list.");
							sa.assertTrue(false,M5CMT3+" Instituion is not present in the list.");
						}
					} else {
						appLog.error("Insitutions Tab cannot be clicked, So cannot verify folder structure on institution page.");
						sa.assertTrue(false,"Insitutions Tab cannot be clicked, So cannot verify folder structure on institution page.");
					}
				} else {
					appLog.error("Workspace is not built for fund '"+M5F2+"', So cannot verify Manage folder.");
					sa.assertTrue(false,"Workspace is not built for fund '"+M5F2+"', So cannot verify Manage folder.");
				}
			} else {
				appLog.error(M5F2+" fund is not present, So cannot continue with the tc.");
				sa.assertTrue(false,M5F2+" fund is not present, So cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, so cannot create workspace and cannot continue with tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, so cannot create workspace and cannot continue with tc.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Test
	public void M5tc004_CreateFundriasingWorkspaceAndVerifyManageFolder(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InstitutionPageBusinessLayer instiPage = new InstitutionPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		CommitmentPageBusinessLayer com = new CommitmentPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M5F2)){
				String[] step1Of3Data = {"2100","2015","abc","987654321","abc@abc.com","Dummy description"};
				if(fp.buildWorkspace(step1Of3Data, WorkSpaceAction.IMPORTFOLDERTEMPLATE,folderTemplateName, null, M5I1+"<break>"+M5I2, Workspace.FundraisingWorkspace, 60)){
					switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
					if(click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 60), "Manage Folder Icon", action.BOOLEAN)){
						if(trim(getText(driver, fp.getManageFolderHeader(Workspace.FundraisingWorkspace, 30), "Manage Folder header", action.BOOLEAN)).equalsIgnoreCase("Manage Folders")){
							appLog.info("Manage folder header is verified.");
						} else {
							appLog.error("Manage Folder Header is not verified. Expected: Manage Folders\tActual: "+trim(getText(driver, fp.getManageFolderHeader(Workspace.FundraisingWorkspace, 30), "Manage Folder header", action.BOOLEAN)));
							sa.assertTrue(false,"Manage Folder Header is not verified. Expected: Manage Folders\tActual: "+trim(getText(driver, fp.getManageFolderHeader(Workspace.FundraisingWorkspace, 30), "Manage Folder header", action.BOOLEAN)));
						}
						if(trim(getText(driver, fp.getAllFolderLabel(Workspace.FundraisingWorkspace, 30), "", action.BOOLEAN)).contains("All Folders")){
							appLog.info("All folder label is verified.");
						} else {
							appLog.error("All folder is not present on manage folder pop up. Expected: All Folder\tActual: "+trim(getText(driver, fp.getAllFolderLabel(Workspace.FundraisingWorkspace, 30), "", action.BOOLEAN)));
							sa.assertTrue(false,"All folder is not present on manage folder pop up. Expected: All Folder\tActual: "+trim(getText(driver, fp.getAllFolderLabel(Workspace.FundraisingWorkspace, 30), "", action.BOOLEAN)));
						}
						if(fp.getAllFolderAddIcon(Workspace.FundraisingWorkspace, 30)!=null){
							appLog.info("All folder add icon is verified.");
						} else {
							appLog.error("All Folder add icon is not present.");
							sa.assertTrue(false,"All Folder add icon is not present.");
						}
						
						if(trim(getText(driver, fp.getManageFolderYellowBoxMessage(Workspace.FundraisingWorkspace, 30), "", action.BOOLEAN)).equalsIgnoreCase(FundsPageErrorMessage.ManageFolderPopUPYellowBlockMessage)){
							appLog.info("Yellow box message is verified.");
						} else {
							appLog.error("Yellow box message is not verified.Expected: "+FundsPageErrorMessage.ManageFolderPopUPYellowBlockMessage+"\tActual: "+trim(getText(driver, fp.getManageFolderYellowBoxMessage(Workspace.FundraisingWorkspace, 30), "", action.BOOLEAN)));
							sa.assertTrue(false,"Yellow box message is not verified.Expected: "+FundsPageErrorMessage.ManageFolderPopUPYellowBlockMessage+"\tActual: "+trim(getText(driver, fp.getManageFolderYellowBoxMessage(Workspace.FundraisingWorkspace, 30), "", action.BOOLEAN)));
						}
						if(click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 30), "Cross icon", action.BOOLEAN)){
						} else {
							fp.recover(Workspace.FundraisingWorkspace, 60);
						}
					} else {
						appLog.error("Manage folder icon cannot be clicked, So cannot check manage folder.");
						sa.assertTrue(false,"Manage folder icon cannot be clicked, So cannot check manage folder.");
					}
					switchToDefaultContent(driver);
					if(fp.clickOnTab(TabName.InstituitonsTab)){
						if(instiPage.clickOnCreatedInstitution(M5I1)){
							switchToFrame(driver, 30, instiPage.getFrame( PageName.InstitutionsPage, 30));
							scrollDownThroughWebelement(driver, instiPage.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), Workspace.FundraisingWorkspace+" View.");
							if(instiPage.VerifyFolderStructure("FolderTemp", null, null, M5F2, Workspace.FundraisingWorkspace, PageName.InstitutionsPage, 30)){
								appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
							} else {
								appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
								sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
							}
							switchToDefaultContent(driver);
						} else {
							appLog.error(M5I1+" Instituion is not present in the list.");
							sa.assertTrue(false,M5I1+" Instituion is not present in the list.");
						}
					} else {
						appLog.error("Insitutions Tab cannot be clicked, So cannot verify folder structure on institution page.");
						sa.assertTrue(false,"Insitutions Tab cannot be clicked, So cannot verify folder structure on institution page.");
					}
					
					if(fp.clickOnTab(TabName.InstituitonsTab)){
						if(instiPage.clickOnCreatedInstitution(M5I2)){
							switchToFrame(driver, 30, instiPage.getFrame( PageName.InstitutionsPage, 30));
							scrollDownThroughWebelement(driver, instiPage.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), Workspace.FundraisingWorkspace+" View.");
							if(instiPage.VerifyFolderStructure("FolderTemp", null, null, M5F2, Workspace.FundraisingWorkspace, PageName.InstitutionsPage, 30)){
								appLog.info("Folder structure is verified on instituion page of '"+M5I2+"'.");
							} else {
								appLog.error("Folder structure is not verified on instituion page of '"+M5I2+"'.");
								sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I2+"'.");
							}
							switchToDefaultContent(driver);
						} else {
							appLog.error(M5I2+" Instituion is not present in the list.");
							sa.assertTrue(false,M5I2+" Instituion is not present in the list.");
						}
					} else {
						appLog.error("Insitutions Tab cannot be clicked, So cannot verify folder structure on institution page.");
						sa.assertTrue(false,"Insitutions Tab cannot be clicked, So cannot verify folder structure on institution page.");
					}
					
				} else {
					appLog.error("Workspace is not built for fund '"+M5F2+"', So cannot verify Manage folder.");
					sa.assertTrue(false,"Workspace is not built for fund '"+M5F2+"', So cannot verify Manage folder.");
				}
			} else {
				appLog.error(M5F2+" fund is not present, So cannot continue with the tc.");
				sa.assertTrue(false,M5F2+" fund is not present, So cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, so cannot create workspace and cannot continue with tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, so cannot create workspace and cannot continue with tc.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Test
	public void M5tc005_InviteContactAndVerifyFolderAtContactPage(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer con = new ContactPageBusinessLayer(driver);
		String standardFolderPath = ExcelUtils.readData("filePath", excelLabel.TestCases_Name, "M5tc005_InviteContactAndVerifyFolderAt", excelLabel.StandardPath);
		String sharedFolderPath = ExcelUtils.readData("filePath", excelLabel.TestCases_Name, "M5tc005_InviteContactAndVerifyFolderAt", excelLabel.SharedPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M5F2)){
				if(fp.inviteContact(environment, mode, M5I1, M5C1Email, null, FolderType.Standard, "Upload", "Yes", "Yes", null, Workspace.FundraisingWorkspace, M5C1Email)){
					appLog.info("Successfully provided access of '"+M5I1+"' to contact '"+M5CFN1+" "+M5CLN1+"'.");
				} else {
					appLog.error("Not able to provide access of '"+M5I1+"' to contact '"+M5CFN1+" "+M5CLN1+"'.");
					sa.assertTrue(false,"Not able to provide access of '"+M5I1+"' to contact '"+M5CFN1+" "+M5CLN1+"'.");
				}
				if(fp.inviteContact(environment, mode, M5I2, M5C1Email, null, FolderType.Standard, "Upload", "Yes", "No", null, Workspace.FundraisingWorkspace, M5C1Email)){
					appLog.info("Successfully provided access of '"+M5I2+"' to contact '"+M5CFN1+" "+M5CLN1+"'.");
				} else {
					appLog.error("Not able to provide access of '"+M5I2+"' to contact '"+M5CFN1+" "+M5CLN1+"'.");
					sa.assertTrue(false,"Not able to provide access of '"+M5I2+"' to contact '"+M5CFN1+" "+M5CLN1+"'.");
				}
				if(fp.inviteContact(environment, mode, M5I1, M5C1Email, sharedFolderPath, FolderType.Shared, "Download", "Yes", "No", null, Workspace.FundraisingWorkspace, M5C1Email)){
					appLog.info("Successfully provided access of '"+sharedFolderPath+"' to contact '"+M5CFN1+" "+M5CLN1+"'.");
				} else {
					appLog.error("Not able to provide access of '"+sharedFolderPath+"' to contact '"+M5CFN1+" "+M5CLN1+"'.");
					sa.assertTrue(false,"Not able to provide access of '"+sharedFolderPath+"' to contact '"+M5CFN1+" "+M5CLN1+"'.");
				}
			} else {
				appLog.error(M5F2+" fund is not present in the list, So cannot continue wih the tc.");
				sa.assertTrue(false,M5F2+" fund is not present in the list, So cannot continue wih the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot invite contact from fund '"+M5F2+"' and cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot invite contact from fund '"+M5F2+"' and cannot continue with the tc.");
		}
		if(lp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M5F2)){
				if(fp.inviteContact(environment, mode, M5I1, M5C1Email, null, FolderType.Standard, "Upload", "Yes", "Yes", null, Workspace.InvestorWorkspace, M5C1Email)){
					appLog.info("Successfully provided access of '"+M5I1+"' to contact '"+M5CFN1+" "+M5CLN1+"'.");
				} else {
					appLog.error("Not able to provide access of '"+M5I1+"' to contact '"+M5CFN1+" "+M5CLN1+"'.");
					sa.assertTrue(false,"Not able to provide access of '"+M5I1+"' to contact '"+M5CFN1+" "+M5CLN1+"'.");
				}
				if(fp.inviteContact(environment, mode, M5I2, M5C1Email, null, FolderType.Standard, "Upload", "Yes", "No", null, Workspace.InvestorWorkspace, M5C1Email)){
					appLog.info("Successfully provided access of '"+M5I2+"' to contact '"+M5CFN1+" "+M5CLN1+"'.");
				} else {
					appLog.error("Not able to provide access of '"+M5I2+"' to contact '"+M5CFN1+" "+M5CLN1+"'.");
					sa.assertTrue(false,"Not able to provide access of '"+M5I2+"' to contact '"+M5CFN1+" "+M5CLN1+"'.");
				}
				if(fp.inviteContact(environment, mode, null, M5C1Email, sharedFolderPath, FolderType.Shared, "Download", "Yes", "No", null, Workspace.InvestorWorkspace, M5C1Email)){
					appLog.info("Successfully provided access of '"+sharedFolderPath+"' to contact '"+M5CFN1+" "+M5CLN1+"'.");
				} else {
					appLog.error("Not able to provide access of '"+sharedFolderPath+"' to contact '"+M5CFN1+" "+M5CLN1+"'.");
					sa.assertTrue(false,"Not able to provide access of '"+sharedFolderPath+"' to contact '"+M5CFN1+" "+M5CLN1+"'.");
				}
			} else {
				appLog.error(M5F2+" fund is not present in the list, So cannot continue wih the tc.");
				sa.assertTrue(false,M5F2+" fund is not present in the list, So cannot continue wih the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot invite contact and cannot continue with the tc.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot invite contact and cannot continue with the tc.");
		}
		
		if(lp.clickOnTab(TabName.ContactTab)){
			if(con.clickOnCreatedContact(M5CFN1, M5CLN1, null)){
				switchToFrame(driver, 30, con.getFrame( PageName.ContactsPage, 30));
				if(fp.VerifyFolderStructure("FolderTemp", M5I1, null, M5F2, Workspace.FundraisingWorkspace, PageName.ContactsPage, 30)){
					appLog.info("Folder strucutre is verified on contact page for fund '"+M5F2+"' on contact page of "+M5CFN1 +" "+ M5CLN1);
				} else {
					appLog.error("Folder structure cannot be verified on contact page for fundraising workspace for fund '"+M5F2+"' on contact page of "+M5CFN1 +" "+ M5CLN1);
					sa.assertTrue(false,"Folder structure cannot be verified on contact page for fundraising workspace for fund '"+M5F2+"' on contact page of "+M5CFN1 +" "+ M5CLN1);
				}
				if(fp.VerifyFolderStructure("FolderTemp", M5I2, null, M5F2, Workspace.FundraisingWorkspace, PageName.ContactsPage, 30)){
					appLog.info("Folder strucutre is verified on contact page for fund '"+M5F2+"' on contact page of "+M5CFN1 +" "+ M5CLN1);
				} else {
					appLog.error("Folder structure cannot be verified on contact page for fundraising workspace for fund '"+M5F2+"' on contact page of "+M5CFN1 +" "+ M5CLN1);
					sa.assertTrue(false,"Folder structure cannot be verified on contact page for fundraising workspace for fund '"+M5F2+"' on contact page of "+M5CFN1 +" "+ M5CLN1);
				}
				if(fp.VerifyFolderStructure("FolderTemp", M5I1, M5LP1, M5F2, Workspace.InvestorWorkspace, PageName.ContactsPage, 30)){
					appLog.info("Folder strucutre is verified on contact page for fund '"+M5F2+"' on contact page of "+M5CFN1 +" "+ M5CLN1);
				} else {
					appLog.error("Folder structure cannot be verified on contact page for fundraising workspace for fund '"+M5F2+"' on contact page of "+M5CFN1 +" "+ M5CLN1);
					sa.assertTrue(false,"Folder structure cannot be verified on contact page for fundraising workspace for fund '"+M5F2+"' on contact page of "+M5CFN1 +" "+ M5CLN1);
				}
				if(fp.VerifyFolderStructure("FolderTemp", M5I2, M5LP2, M5F2, Workspace.InvestorWorkspace, PageName.ContactsPage, 30)){
					appLog.info("Folder strucutre is verified on contact page for fund '"+M5F2+"' on contact page of "+M5CFN1 +" "+ M5CLN1);
				} else {
					appLog.error("Folder structure cannot be verified on contact page for fundraising workspace for fund '"+M5F2+"' on contact page of "+M5CFN1 +" "+ M5CLN1);
					sa.assertTrue(false,"Folder structure cannot be verified on contact page for fundraising workspace for fund '"+M5F2+"' on contact page of "+M5CFN1 +" "+ M5CLN1);
				}
			} else {
				appLog.error(M5CFN1 +" "+ M5CLN1+" contact is not present in the list, So cannot continue wih the tc.");
				sa.assertTrue(false,M5CFN1 +" "+ M5CLN1+" contact is not present in the list, So cannot continue wih the tc.");
			}
		} else {
			appLog.error("Contact tab cannot be clicked, So cannot continue.");
			sa.assertTrue(false,"Contact tab cannot be clicked, So cannot continue.");
		}
		sa.assertAll();
	}
	
	@Test
	public void M5tc005_InviteContactAndVerifyFolderAtInvestorSide(){
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer fp = new InvestorFirmPageBusinesslayer(driver);
		boolean flag = false;
		if (ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M5Contact1", excelLabel.Registered).equalsIgnoreCase("Not Registered")) {
			try {
				investorRegLink = new EmailLib().getInvestorRegLink("InvitationMail",gmailUserName,gmailPassword,CRMUser1EmailID,M5C1Email);
				appLog.info("Investor Registration Link: " + investorRegLink);
			} catch (InterruptedException e) {
				appLog.info("Invitation mail not found.");
				e.printStackTrace();
			}
			if (investorRegLink != null) {
				driver.get(investorRegLink);
				if (bp.targetRegistration(M5CFN1, M5CLN1, M5C1Email, M5I1,
						adminPassword)) {
					appLog.info(
							"Investor is registered successfully: " + M5CFN1 + " " + M5CLN1);
					flag = true;
					ExcelUtils.writeData("Registered", "Contacts", excelLabel.Variable_Name, "M5Contact1", excelLabel.Registered);
				} else {
					driver.get(ExcelUtils.readDataFromPropertyFile("TargetRegistrationURL"));
					appLog.info("Register Target URL through Direct URL..");
					if (bp.targetRegistration(M5CFN1, M5CLN1, M5C1Email,
							M5I1, adminPassword)) {
						appLog.info("Investor is registered successfully: " + M5CFN1 + " "
								+ M5CLN1);
						flag = true;
						ExcelUtils.writeData("Registered", "Contacts", excelLabel.Variable_Name, "M5Contact1", excelLabel.Registered);
					} else {
						appLog.error("Investor is not Registered through Invited Link: " + M5CFN1 + " "
								+ M5CLN1);
						sa.assertTrue(false, "Investor is not Registered through Invited Link: " + M5CFN1
								+ " " + M5CLN1);
					}
				}
			} else {
				driver.get(ExcelUtils.readDataFromPropertyFile("TargetRegistrationURL"));
				appLog.info("Register Target URL through Direct URL..");
				if (bp.targetRegistration(M5CFN1, M5CLN1, M5C1Email,
						M5I1, adminPassword)) {
					appLog.info("Investor is registered successfully: " + M5CFN1 + " "
							+ M5CLN1);
					flag = true;
					ExcelUtils.writeData("Registered", "Contacts", excelLabel.Variable_Name, "M5Contact1", excelLabel.Registered);
				} else {
					appLog.error("Investor is not Registered through Invited Link: " + M5CFN1 + " "
							+ M5CLN1);
					sa.assertTrue(false, "Investor is not Registered through Invited Link: " + M5CFN1
							+ " " + M5CLN1);
				}
			}		
		} else {
			appLog.info("investor "+M5CFN1+" "+M5CLN1+" is already Registered.");
			sa.assertTrue(false, "investor "+M5CFN1+" "+M5CLN1+" is already Registered.");
			if(lp.investorLogin(M5C1Email, adminPassword)){
				flag = true;
			}
		}
		if(flag){
			if(fp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)){
				if(fp.VerifyFolderStructure("FolderTemp", M5I1, null, null, null, PageName.PotentialInvestmentPage, 30)){
					appLog.info("Folder structure is verified on investor potenial investment page.");
				} else {
					appLog.error("Folder structure is not verified at investor potential investment page.");
					sa.assertTrue(false,"Folder structure is not verified at investor potential investment page.");
				}
				if(fp.VerifyFolderStructure("FolderTemp", M5I2, null, null, null, PageName.PotentialInvestmentPage, 30)){
					appLog.info("Folder structure is verified on investor potenial investment page.");
				} else {
					appLog.error("Folder structure is not verified at investor potential investment page.");
					sa.assertTrue(false,"Folder structure is not verified at investor potential investment page.");
				}
			} else {
				appLog.error("Not able to click on potential investment Tab, So cannot verify folder structure on this page.");
				sa.assertTrue(false,"Not able to click on potential investment Tab, So cannot verify folder structure on this page.");
			}
			
			if(fp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)){
				if(fp.VerifyFolderStructure("FolderTemp", M5I1, M5LP1, null, null, PageName.CurrentInvestmentPgae, 30)){
					appLog.info("Folder structure is verified on investor current investment page.");
				} else {
					appLog.error("Folder structure is not verified at investor current investment page.");
					sa.assertTrue(false,"Folder structure is not verified at investor current investment page.");
				}
				if(fp.VerifyFolderStructure("FolderTemp", M5I2, M5LP2, null, null, PageName.CurrentInvestmentPgae, 30)){
					appLog.info("Folder structure is verified on investor current investment page.");
				} else {
					appLog.error("Folder structure is not verified at investor current investment page.");
					sa.assertTrue(false,"Folder structure is not verified at investor current investment page.");
				}
			} else {
				appLog.error("Not able to click on current investment Tab, So cannot verify folder structure on this page.");
				sa.assertTrue(false,"Not able to click on current investment Tab, So cannot verify folder structure on this page.");
			}
		} else {
			appLog.error("Not able to register or login by investor "+M5CFN1+" "+M5CLN1+", So cannot continue with the testcase");
			sa.assertTrue(false,"Not able to register or login by investor "+M5CFN1+" "+M5CLN1+", So cannot continue with the testcase");
		}
		
		sa.assertAll();
	}
	
	@Test
	public void M5tc006_VerifyAddAFolderPopUpFundraisingWorkspace(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InstitutionPageBusinessLayer instiPage = new InstitutionPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer con = new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M5F2)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view.");
				if(click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 30), "Manage folder icon", action.BOOLEAN)){
					if(click(driver, fp.getAllFolderAddIcon(Workspace.FundraisingWorkspace, 30), "Add folder button", action.BOOLEAN)){
						String text = trim(getText(driver, fp.getAddFolderHeader(Workspace.FundraisingWorkspace, 30),"", action.BOOLEAN));
						if(text.equalsIgnoreCase("Add a Folder")){
							appLog.info("Add a folder header is verified.");
						} else {
							appLog.error("Add a folder pop up header is not verifed.Expected: Add a Folder"+"\tActual: "+text);
							sa.assertTrue(false,"Add a folder pop up header is not verifed.Expected: Add a Folder"+"\tActual: "+text);
						}
						if(isSelected(driver, fp.getFolderTypeRadioButton(FolderType.Standard, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30), "Add a Folder Standard folder type radio button")){
							appLog.info("Standard folder radio button is present and is by default selected.");
						} else {
							appLog.error("Standard folder radio button is not by defualt selected.");
							sa.assertTrue(false,"Standard folder radio button is not by defualt selected.");
						}
						
						if(fp.getFolderTypeRadioButton(FolderType.Common, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30)!=null){
							appLog.info("Common folder radio button is verified.");
						} else {
							appLog.error("Common folder radio button is not present.");
							sa.assertTrue(false,"Common folder radio button is not present.");
						}
						
						if(fp.getFolderTypeRadioButton(FolderType.Shared, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30)!=null){
							appLog.info("Shared folder radio button is verified.");
						} else {
							appLog.error("Shared folder radio button is not present.");
							sa.assertTrue(false,"Shared folder radio button is not present.");
						}
						
						if(fp.getFolderTypeRadioButton(FolderType.Internal, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30)!=null){
							appLog.info("Internal folder radio button is verified.");
						} else {
							appLog.error("Internal folder radio button is not present.");
							sa.assertTrue(false,"Internal folder radio button is not present.");
						}
						
						if(fp.getParentFolderSaveButton(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30)!=null){
							appLog.info("Save button is verified.");
						} else {
							appLog.error("Save button on add a folder pop up is not verified.");
							sa.assertTrue(false,"Save button on add a folder pop up is not verified.");
						}
						
						if(fp.getAddFolderParentFolderCancelButton(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30)!=null){
							appLog.info("Add a folder pop up cancel button is verified.");
						} else {
							appLog.error("Add a folder pop up cancel button si not verified.");
							sa.assertTrue(false,"Add a folder pop up cancel button si not verified.");
						}
						
						if(mouseOverOperation(driver, fp.getAddFolderInformationIcon(Workspace.FundraisingWorkspace, 30))){
							text = trim(getText(driver, fp.getAddFolderInfoIconMessage(30), "", action.BOOLEAN));
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
						boolean flag = true;
						if(sendKeys(driver, fp.getParentFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30), "Standard Folder New", "Parent folder name text box", action.BOOLEAN)){
							if(click(driver, fp.getFolderTypeRadioButton(FolderType.Common, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30), "Folder type radio button", action.BOOLEAN)){
								appLog.info("Common folder radio button successfully selected.");
							} else {
								appLog.error("Common folder radio button cannot be clicked, So will continue with the current selection.");
								sa.assertTrue(false,"Common folder radio button cannot be clicked.");
							}
							if(click(driver, fp.getAddFolderCrossIcon(Workspace.FundraisingWorkspace, 30), "Cross icon", action.BOOLEAN)){
								if(FindElement(driver, "//label[contains(text(),'Standard Folder New')]", "Folder in view", action.BOOLEAN, 3)!=null){
									appLog.error("New folder is being created after clicking on cross icon.");
									sa.assertTrue(false,"New folder is being created after clicking on cross icon.");
								} else {
									appLog.info("Folder structure is same as before and cross icon is verified.");
								}
							} else {
								appLog.error("Cross icon cannot be clicked, So cannot check cross icon functionality.");
								sa.assertTrue(false,"Cross icon cannot be clicked, So cannot check cross icon functionality.");
								flag = false;
							}
						} else {
							appLog.error("Cannot enter text to folder name text box, So cannot cross icon functionality.");
							sa.assertTrue(false,"Cannot enter text to folder name text box, So cannot cross icon functionality.");
						}
						
						if(flag){
							if(click(driver, fp.getAllFolderAddIcon(Workspace.FundraisingWorkspace, 30), "Add folder button", action.BOOLEAN)){
								
							} else {
								appLog.error("Add folder icon cannot be clicked, So cannot check cancel button.");
								sa.assertTrue(false,"Add folder icon cannot be clicked, So cannot check cancel button.");
							}
						}
						if(sendKeys(driver, fp.getParentFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30), "Standard Folder New", "Parent folder name text box", action.BOOLEAN)){
							if(click(driver, fp.getFolderTypeRadioButton(FolderType.Common, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30), "Folder type radio button", action.BOOLEAN)){
								appLog.info("Common folder radio button successfully selected.");
							} else {
								appLog.error("Common folder radio button cannot be clicked, So will continue with the current selection.");
								sa.assertTrue(false,"Common folder radio button cannot be clicked.");
							}
							if(click(driver, fp.getAddFolderParentFolderCancelButton(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30), "Cancel", action.BOOLEAN)){
								flag = true;
								if(FindElement(driver, "//label[contains(text(),'Standard Folder New')]", "Folder in view", action.BOOLEAN, 3)!=null){
									appLog.error("New folder is being created after clicking on Cancle button.");
									sa.assertTrue(false,"New folder is being created after clicking on Cancel button.");
								} else {
									appLog.info("Folder structure is same as before and Cancel is verified.");
								}
							} else {
								appLog.error("Cancel button cannot be clicked, So cannot check Cancel button functionality.");
								sa.assertTrue(false,"Cross icon cannot be clicked, So cannot check cancel button functionality.");
								flag = false;
							}
						} else {
							appLog.error("Cannot enter text to folder name text box, So cannot check cancel button functionality.");
							sa.assertTrue(false,"Cannot enter text to folder name text box, So cannot Check cancel button functionality.");
						}
						if(flag){
							if(click(driver, fp.getAllFolderAddIcon(Workspace.FundraisingWorkspace, 30), "Add folder button", action.BOOLEAN)){
								
							} else {
								appLog.error("Add folder icon cannot be clicked, So cannot check cancel button.");
								sa.assertTrue(false,"Add folder icon cannot be clicked, So cannot check cancel button.");
							}
						}
						if(sendKeys(driver, fp.getParentFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30), "Standard Folder New", "Parent folder name text box", action.BOOLEAN)){
							if(click(driver, fp.getFolderTypeRadioButton(FolderType.Standard, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30), "Folder type radio button", action.BOOLEAN)){
								appLog.info("Standard folder radio button successfully selected.");
							} else {
								appLog.error("Standard folder radio button cannot be clicked, So will continue with the current selection.");
								sa.assertTrue(false,"Standard folder radio button cannot be clicked.");
							}
							if(click(driver, fp.getParentFolderSaveButton(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30), "Save button", action.BOOLEAN)){
								flag = true;
								if(FindElement(driver, "//label[contains(text(),'Standard Folder New')]", "Folder in view", action.BOOLEAN, 30)!=null){
									appLog.info("Folder is successfully created and is verified.");
								} else {
									appLog.error("New folder is not being created after clicking on Save button.");
									sa.assertTrue(false,"New folder is not being created after clicking on Save button.");
								}
							} else {
								appLog.error("Save button cannot be clicked, So cannot check Cancel button functionality.");
								sa.assertTrue(false,"Save button cannot be clicked, So cannot check cancel button functionality.");
								flag = false;
							}
						} else {
							appLog.error("Cannot enter text to folder name text box, So cannot check cancel button functionality.");
							sa.assertTrue(false,"Cannot enter text to folder name text box, So cannot Check cancel button functionality.");
						}
						if(flag){
							if(click(driver, fp.getAllFolderAddIcon(Workspace.FundraisingWorkspace, 30), "Add folder button", action.BOOLEAN)){
								
							} else {
								appLog.error("Add folder icon cannot be clicked, So cannot check cancel button.");
								sa.assertTrue(false,"Add folder icon cannot be clicked, So cannot check cancel button.");
							}
						}
						
						if(sendKeys(driver, fp.getParentFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30), "Shared Folder New", "Parent folder name text box", action.BOOLEAN)){
							if(click(driver, fp.getFolderTypeRadioButton(FolderType.Shared, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30), "Folder type radio button", action.BOOLEAN)){
								appLog.info("Shared folder radio button successfully selected.");
							} else {
								appLog.error("Shared folder radio button cannot be clicked, So will continue with the current selection.");
								sa.assertTrue(false,"Shared folder radio button cannot be clicked.");
							}
							if(click(driver, fp.getParentFolderSaveButton(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30), "Save button", action.BOOLEAN)){
								flag = true;
								if(FindElement(driver, "//label[contains(text(),'Shared Folder New')]", "Folder in view", action.BOOLEAN, 30)!=null){
									appLog.info("Folder is successfully created and is verified.");
								} else {
									appLog.error("New folder is not being created after clicking on Save button.");
									sa.assertTrue(false,"New folder is not being created after clicking on Save button.");
								}
							} else {
								appLog.error("Save button cannot be clicked, So cannot check Cancel button functionality.");
								sa.assertTrue(false,"Save button cannot be clicked, So cannot check cancel button functionality.");
								flag = false;
							}
						} else {
							appLog.error("Cannot enter text to folder name text box, So cannot check cancel button functionality.");
							sa.assertTrue(false,"Cannot enter text to folder name text box, So cannot Check cancel button functionality.");
						}
						
						if(click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 30), "Mange folder close button", action.BOOLEAN));
						if(fp.verifyFolderPathdummy("Standard Folder New", M5I1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
							appLog.info("Standard Folder New is verified for institution "+M5I1+", on Fund page of '"+M5F2+"'.");
						} else {
							appLog.error("Standard Folder New is not verified for institution "+M5I1+", on Fund page of '"+M5F2+"'.");
							sa.assertTrue(false,"Standard Folder New is not verified for institution "+M5I1+", on Fund page of '"+M5F2+"'.");
						}
						if(fp.verifyFolderPathdummy("Standard Folder New", M5I2, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
							appLog.info("Standard Folder New is verified for institution "+M5I2+", on Fund page of '"+M5F2+"'.");
						} else {
							appLog.error("Standard Folder New is not verified for institution "+M5I2+", on Fund page of '"+M5F2+"'.");
							sa.assertTrue(false,"Standard Folder New is not verified for institution "+M5I2+", on Fund page of '"+M5F2+"'.");
						}
						if(fp.verifyFolderPathdummy("Shared Folder New (Shared)", null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)){
							appLog.info("Shared Folder New (Shared) is verified on Fund page of '"+M5F2+"'.");
						} else {
							appLog.error("Shared Folder New (Shared) is not verified on Fund page of '"+M5F2+"'.");
							sa.assertTrue(false,"Shared Folder New (Shared) is not verified on Fund page of '"+M5F2+"'.");
						}
						
					} else {
						appLog.error("Add folder button cannot be clicked, So cannot continue with the tc.");
						sa.assertTrue(false,"Add folder button cannot be clicked, So cannot continue with the tc.");
					}
				} else {
					appLog.error("Manage folder icon cannot be clicked, So cannto continue with tc.");
					sa.assertTrue(false,"Manage folder icon cannot be clicked, So cannto continue with tc.");
				}
			} else {
				appLog.error(M5F2+" fund is not available so cannot continue with the tc.");
				sa.assertTrue(false,M5F2+" fund is not available so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot check add a folder pop up.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot check add a folder pop up.");
		}
		switchToDefaultContent(driver);
		if(fp.clickOnTab(TabName.InstituitonsTab)){
			if(instiPage.clickOnCreatedInstitution(M5I1)){
				switchToFrame(driver, 30, instiPage.getFrame( PageName.InstitutionsPage, 30));
				scrollDownThroughWebelement(driver, instiPage.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), Workspace.FundraisingWorkspace+" View.");
				if(fp.verifyFolderPathdummy("Standard Folder New", null, null, M5F2, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}
				if(fp.verifyFolderPathdummy("Shared Folder New (Shared)", null, null, M5F2, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}
				switchToDefaultContent(driver);
			} else {
				appLog.error(M5I1+" Instituion is not present in the list.");
				sa.assertTrue(false,M5I1+" Instituion is not present in the list.");
			}
		} else {
			appLog.error("Insitutions Tab cannot be clicked, So cannot verify folder structure on institution page.");
			sa.assertTrue(false,"Insitutions Tab cannot be clicked, So cannot verify folder structure on institution page.");
		}
		
		if(lp.clickOnTab(TabName.ContactTab)){
			if(con .clickOnCreatedContact(M5CFN1, M5CLN1, null)){
				switchToFrame(driver, 30, con.getFrame( PageName.ContactsPage, 30));
				if(fp.verifyFolderPathdummy("Standard Folder New", M5I1, null, M5F2, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)){
					appLog.info("Folder strucutre is verified on contact page for fund '"+M5F2+"' on contact page of "+M5CFN1 +" "+ M5CLN1);
				} else {
					appLog.error("Folder structure cannot be verified on contact page for fundraising workspace for fund '"+M5F2+"' on contact page of "+M5CFN1 +" "+ M5CLN1);
					sa.assertTrue(false,"Folder structure cannot be verified on contact page for fundraising workspace for fund '"+M5F2+"' on contact page of "+M5CFN1 +" "+ M5CLN1);
				}
				if(fp.verifyFolderPathdummy("Standard Folder New", M5I2, null, M5F2, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)){
					appLog.info("Folder strucutre is verified on contact page for fund '"+M5F2+"' on contact page of "+M5CFN1 +" "+ M5CLN1);
				} else {
					appLog.error("Folder structure cannot be verified on contact page for fundraising workspace for fund '"+M5F2+"' on contact page of "+M5CFN1 +" "+ M5CLN1);
					sa.assertTrue(false,"Folder structure cannot be verified on contact page for fundraising workspace for fund '"+M5F2+"' on contact page of "+M5CFN1 +" "+ M5CLN1);
				}
			} else {
				appLog.error(M5CFN1 +" "+ M5CLN1+" contact is not present in the list, So cannot continue wih the tc.");
				sa.assertTrue(false,M5CFN1 +" "+ M5CLN1+" contact is not present in the list, So cannot continue wih the tc.");
			}
		} else {
			appLog.error("Contact tab cannot be clicked, So cannot continue.");
			sa.assertTrue(false,"Contact tab cannot be clicked, So cannot continue.");
		}
		
		sa.assertAll();
	}
	
	@Test
	public void M5tc006_VerifyAddAFolderPopUpFundraisingWorkspaceAtTargetSide(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		lp.investorLogin(M5C1Email, adminPassword);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		if(ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)){
			if(fp.verifyFolderPathdummy("Standard Folder New", M5I1, null, null, PageName.PotentialInvestmentPage, null, 30)){
				appLog.info("Standard Folder New is verified for institution "+M5I1+".");
			} else {
				appLog.error("Standard Folder New is not verified for institution "+M5I1+" on investor potential investment page.");
				sa.assertTrue(false,"Standard Folder New is not verified for institution "+M5I1+" on investor potential investment page.");
			}
			if(fp.verifyFolderPathdummy("Standard Folder New", M5I2, null, null, PageName.PotentialInvestmentPage, null, 30)){
				appLog.info("Standard Folder New is verified for institution "+M5I2+".");
			} else {
				appLog.error("Standard Folder New is not verified for institution "+M5I2+" on investor potential investment page.");
				sa.assertTrue(false,"Standard Folder New is not verified for institution "+M5I2+" on investor potential investment page.");
			}
		} else {
			appLog.error("Investment tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Investment tab cannot be clicked, SO cannot continue with the tc.");
		}
	}
	
	@Test
	public void M5tc007_1_VerifyAddASubFolderPopUpFundraisingWorkspace(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ins = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer con = new ContactPageBusinessLayer(driver);
		String standardFolderPath = ExcelUtils.readData("filePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.StandardPath);
		String commonFolderPath = ExcelUtils.readData("filePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.CommonPath);
		String sharedFolderPath = ExcelUtils.readData("filePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.SharedPath);
		String internalFolderPath = ExcelUtils.readData("filePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.InternalPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M5F2)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view.");
				if(click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 30), "Manage folder icon", action.BOOLEAN)){
					if(fp.verifyAddRenameDeleteButton(null, FolderType.Common, YesNo.Yes, Workspace.FundraisingWorkspace, 30)){
						appLog.info("Add Rename Delete Button are verified for folder type Common.");
					} else {
						appLog.error("Add Rename Delete Button are not verified for folder type Common.");
						sa.assertTrue(false,"Add Rename Delete Button are not verified for folder type Common.");
					}
					
					if(fp.verifyAddRenameDeleteButton(null, FolderType.Shared, YesNo.Yes, Workspace.FundraisingWorkspace, 30)){
						appLog.info("Add Rename Delete Button are verified for folder type Shared.");
					} else {
						appLog.error("Add Rename Delete Button are not verified for folder type Shared.");
						sa.assertTrue(false,"Add Rename Delete Button are not verified for folder type Shared.");
					}
					
					if(fp.verifyAddRenameDeleteButton(null, FolderType.Internal, YesNo.Yes, Workspace.FundraisingWorkspace, 30)){
						appLog.info("Add Rename Delete Button are verified for folder type Internal.");
					} else {
						appLog.error("Add Rename Delete Button are not verified for folder type Internal.");
						sa.assertTrue(false,"Add Rename Delete Button are not verified for folder type Internal.");
					}
					
					if(fp.verifyAddRenameDeleteButton(standardFolderPath.split(",")[0], FolderType.Standard, YesNo.Yes, Workspace.FundraisingWorkspace, 30)){
						appLog.info("Add Rename Delete Button are verified for folder type Standard.");
					} else {
						appLog.error("Add Rename Delete Button are not verified for folder type Standard.");
						sa.assertTrue(false,"Add Rename Delete Button are not verified for folder type Standard.");
					}
					
					if(fp.verifyAddRenameDeleteButton("Standard Folder New", FolderType.Standard, YesNo.No, Workspace.FundraisingWorkspace, 30)){
						appLog.info("Add Rename Delete Button are verified for folder Standard Folder New.");
					} else {
						appLog.error("Add Rename Delete Button are not verified for folder Standard Folder New.");
						sa.assertTrue(false,"Add Rename Delete Button are not verified for folder Standard Folder New.");
					}
					
					if(fp.verifyAddRenameDeleteButton("Shared Folder New (Shared)", FolderType.Standard, YesNo.No, Workspace.FundraisingWorkspace, 30)){
						appLog.info("Add Rename Delete Button are verified for folder Shared Folder New (Shared).");
					} else {
						appLog.error("Add Rename Delete Button are not verified for folder Shared Folder New (Shared).");
						sa.assertTrue(false,"Add Rename Delete Button are not verified for folder Shared Folder New (Shared).");
					}
					scrollDownThroughWebelement(driver, fp.getFundRaisingWorkSpaceSection(30), "fundraising workspace View");
					if(fp.clickOnAddAFolderButton(null, FolderType.Common, Workspace.FundraisingWorkspace, 30)){
						String text = trim(getText(driver, fp.getAddFolderChildHeader(Workspace.FundraisingWorkspace, 30), "", action.BOOLEAN));
						if(text.equalsIgnoreCase("Add a folder")){
							appLog.info("Pop Up header is verified.");
						} else {
							appLog.error("Pop Up Header is not verified. Expected: Add a folder\tActual: "+text);
							sa.assertTrue(false,"Pop Up Header is not verified. Expected: Add a folder\tActual: "+text);
						}
						if(fp.getAddFolderChildCrossIcon(Workspace.FundraisingWorkspace, 30)!=null){
							appLog.info("Cross icon is present is verified.");
						} else {
							appLog.error("Pop Up Cross icon is not present so cannot check the functionality.");
							sa.assertTrue(false,"Pop Up Cross icon is not present so cannot check the functionality.");
						}
						
						if(fp.getAddFolderChildCancelButton(Workspace.FundraisingWorkspace, 30)!=null){
							appLog.info("Cancel button is present is verified.");
						} else {
							appLog.error("Pop Up Cancel button is not present so cannot check the functionality.");
							sa.assertTrue(false,"Pop Up Cancel button is not present so cannot check the functionality.");
						}
						
						if(click(driver, fp.getAddFolderChildCrossIcon(Workspace.FundraisingWorkspace, 30), "cross icon", action.BOOLEAN)){
//							ThreadSleep(1500);
//							if(!click(driver, fp.getAddFolderChildCrossIcon(Workspace.FundraisingWorkspace, 3), "cross icon", action.BOOLEAN)){
//								appLog.info("Cross icon is working");
//							} else {
//								appLog.error("Cross icon is not working.");
//								sa.assertTrue(false,"Cross icon is not working.");
//							}
						} else {
							appLog.error("Cross icon functionality cannot be checked.");
							sa.assertTrue(false,"Cross icon functionality cannot be checked.");
						}
						if(fp.clickOnAddAFolderButton(null, FolderType.Common, Workspace.FundraisingWorkspace, 30)){
							if(click(driver, fp.getAddFolderChildCancelButton(Workspace.FundraisingWorkspace, 30), "Cancel Button", action.BOOLEAN)){
//								ThreadSleep(1500);
//								if(!click(driver, fp.getAddFolderChildCancelButton(Workspace.FundraisingWorkspace, 3), "Cancel Button", action.BOOLEAN)){
//									appLog.info("Cancel Button is working");
//								} else {
//									appLog.error("Cancel Button is not working.");
//									sa.assertTrue(false,"Cancel Button is not working.");
//								}
							} else {
								appLog.error("Cross icon functionality cannot be checked.");
								sa.assertTrue(false,"Cross icon functionality cannot be checked.");
							}
						}
						if(fp.createFolderStructure(commonFolderPath.split(",")[0], FolderType.Common, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
							appLog.info(commonFolderPath.split(",")[0]+ " folder structure is created.");
						} else {
							appLog.error(commonFolderPath.split(",")[0]+ " folder structure is not created.");
							sa.assertTrue(false,commonFolderPath.split(",")[0]+ " folder structure is not created.");
						}
						if(fp.createFolderStructure(commonFolderPath.split(",")[1], FolderType.Common, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
							appLog.info(commonFolderPath.split(",")[1]+ " folder structure is created.");
						} else {
							appLog.error(commonFolderPath.split(",")[1]+ " folder structure is not created.");
							sa.assertTrue(false,commonFolderPath.split(",")[1]+ " folder structure is not created.");
						}
//						if(fp.verifyFolderPathdummy(commonFolderPath.split(",")[0], null, null, null, PageName.ManageFolderPopUp, Workspace.FundraisingWorkspace, 5)){
//							appLog.info(commonFolderPath.split(",")[0]+ " folder structure is created after clicking on save button.");
//						} else {
//							appLog.error(commonFolderPath.split(",")[0]+ " folder structure is not created after clicking on save button.");
//							sa.assertTrue(false,commonFolderPath.split(",")[0]+ " folder structure is not created after clicking on save button.");
//						}
//						if(fp.verifyFolderPathdummy(commonFolderPath.split(",")[0], null, null, null, PageName.ManageFolderPopUp, Workspace.FundraisingWorkspace, 5)){
//							appLog.info(commonFolderPath.split(",")[1]+ " folder structure is created after clicking on save button.");
//						} else {
//							appLog.error(commonFolderPath.split(",")[1]+ " folder structure is not created after clicking on save button.");
//							sa.assertTrue(false,commonFolderPath.split(",")[1]+ " folder structure is not created after clicking on save button.");
//						}
						if(FindElement(driver, "//label[contains(text(),'"+commonFolderPath.split(",")[0].split("/")[0]+"')]/../../../following-sibling::ul/li/div/a/span/label[contains(text(),'"+commonFolderPath.split(",")[0].split("/")[1]+"')]", "Folder in view", action.BOOLEAN, 30)!=null){
							appLog.info(commonFolderPath.split(",")[0]+" Folder is successfully created and is verified.");
						} else {
							appLog.error(commonFolderPath.split(",")[0]+" New folder is not being created after clicking on Save button.");
							sa.assertTrue(false,commonFolderPath.split(",")[0]+" New folder is not being created after clicking on Save button.");
						}
						if(FindElement(driver, "//label[contains(text(),'"+commonFolderPath.split(",")[1].split("/")[0]+"')]/../../../following-sibling::ul/li/div/a/span/label[contains(text(),'"+commonFolderPath.split(",")[1].split("/")[1]+"')]/../../../following-sibling::ul/li/div/a/span/label[contains(text(),'"+commonFolderPath.split(",")[1].split("/")[2]+"')]", "Folder in view", action.BOOLEAN, 30)!=null){
							appLog.info(commonFolderPath.split(",")[1]+" Folder is successfully created and is verified.");
						} else {
							appLog.error(commonFolderPath.split(",")[1]+" New folder is not being created after clicking on Save button.");
							sa.assertTrue(false,commonFolderPath.split(",")[1]+" New folder is not being created after clicking on Save button.");
						}
						if(fp.createFolderStructure(sharedFolderPath.split(",")[0], FolderType.Shared, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
							appLog.info(sharedFolderPath.split(",")[0]+ " folder structure is created.");
						} else {
							appLog.error(sharedFolderPath.split(",")[0]+ " folder structure is not created.");
							sa.assertTrue(false,sharedFolderPath.split(",")[0]+ " folder structure is not created.");
						}
						if(fp.createFolderStructure(sharedFolderPath.split(",")[1], FolderType.Shared, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
							appLog.info(sharedFolderPath.split(",")[1]+ " folder structure is created.");
						} else {
							appLog.error(sharedFolderPath.split(",")[1]+ " folder structure is not created.");
							sa.assertTrue(false,sharedFolderPath.split(",")[1]+ " folder structure is not created.");
						}
//						if(fp.verifyFolderPathdummy(sharedFolderPath.split(",")[0], null, null, null, PageName.ManageFolderPopUp, Workspace.FundraisingWorkspace, 5)){
//							appLog.info(sharedFolderPath.split(",")[0]+ " folder structure is created after clicking on save button.");
//						} else {
//							appLog.error(sharedFolderPath.split(",")[0]+ " folder structure is not created after clicking on save button.");
//							sa.assertTrue(false,sharedFolderPath.split(",")[0]+ " folder structure is not created after clicking on save button.");
//						}
//						if(fp.verifyFolderPathdummy(sharedFolderPath.split(",")[0], null, null, null, PageName.ManageFolderPopUp, Workspace.FundraisingWorkspace, 5)){
//							appLog.info(sharedFolderPath.split(",")[1]+ " folder structure is created after clicking on save button.");
//						} else {
//							appLog.error(sharedFolderPath.split(",")[1]+ " folder structure is not created after clicking on save button.");
//							sa.assertTrue(false,sharedFolderPath.split(",")[1]+ " folder structure is not created after clicking on save button.");
//						}
						if(FindElement(driver, "//label[contains(text(),'"+sharedFolderPath.split(",")[0].split("/")[0]+"')]/../../../following-sibling::ul/li/div/a/span/label[contains(text(),'"+sharedFolderPath.split(",")[0].split("/")[1]+"')]", "Folder in view", action.BOOLEAN, 30)!=null){
							appLog.info(sharedFolderPath.split(",")[0]+" Folder is successfully created and is verified.");
						} else {
							appLog.error(sharedFolderPath.split(",")[0]+" New folder is not being created after clicking on Save button.");
							sa.assertTrue(false,sharedFolderPath.split(",")[0]+" New folder is not being created after clicking on Save button.");
						}
						if(FindElement(driver, "//label[contains(text(),'"+sharedFolderPath.split(",")[1].split("/")[0]+"')]/../../../following-sibling::ul/li/div/a/span/label[contains(text(),'"+sharedFolderPath.split(",")[1].split("/")[1]+"')]/../../../following-sibling::ul/li/div/a/span/label[contains(text(),'"+sharedFolderPath.split(",")[1].split("/")[2]+"')]", "Folder in view", action.BOOLEAN, 30)!=null){
							appLog.info(sharedFolderPath.split(",")[1]+" Folder is successfully created and is verified.");
						} else {
							appLog.error(sharedFolderPath.split(",")[1]+" New folder is not being created after clicking on Save button.");
							sa.assertTrue(false,sharedFolderPath.split(",")[1]+" New folder is not being created after clicking on Save button.");
						}
						if(fp.createFolderStructure(internalFolderPath.split(",")[0], FolderType.Internal, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
							appLog.info(internalFolderPath.split(",")[0]+ " folder structure is created.");
						} else {
							appLog.error(internalFolderPath.split(",")[0]+ " folder structure is not created.");
							sa.assertTrue(false,internalFolderPath.split(",")[0]+ " folder structure is not created.");
						}
						if(fp.createFolderStructure(internalFolderPath.split(",")[1], FolderType.Internal, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
							appLog.info(internalFolderPath.split(",")[1]+ " folder structure is created.");
						} else {
							appLog.error(internalFolderPath.split(",")[1]+ " folder structure is not created.");
							sa.assertTrue(false,internalFolderPath.split(",")[1]+ " folder structure is not created.");
						}
//						if(fp.verifyFolderPathdummy(sharedFolderPath.split(",")[0], null, null, null, PageName.ManageFolderPopUp, Workspace.FundraisingWorkspace, 5)){
//							appLog.info(sharedFolderPath.split(",")[0]+ " folder structure is created after clicking on save button.");
//						} else {
//							appLog.error(sharedFolderPath.split(",")[0]+ " folder structure is not created after clicking on save button.");
//							sa.assertTrue(false,sharedFolderPath.split(",")[0]+ " folder structure is not created after clicking on save button.");
//						}
//						if(fp.verifyFolderPathdummy(sharedFolderPath.split(",")[0], null, null, null, PageName.ManageFolderPopUp, Workspace.FundraisingWorkspace, 5)){
//							appLog.info(sharedFolderPath.split(",")[1]+ " folder structure is created after clicking on save button.");
//						} else {
//							appLog.error(sharedFolderPath.split(",")[1]+ " folder structure is not created after clicking on save button.");
//							sa.assertTrue(false,sharedFolderPath.split(",")[1]+ " folder structure is not created after clicking on save button.");
//						}
						if(FindElement(driver, "//label[contains(text(),'"+internalFolderPath.split(",")[0].split("/")[0]+"')]/../../../following-sibling::ul/li/div/a/span/label[contains(text(),'"+internalFolderPath.split(",")[0].split("/")[1]+"')]", "Folder in view", action.BOOLEAN, 30)!=null){
							appLog.info(internalFolderPath.split(",")[0]+" Folder is successfully created and is verified.");
						} else {
							appLog.error(internalFolderPath.split(",")[0]+" New folder is not being created after clicking on Save button.");
							sa.assertTrue(false,internalFolderPath.split(",")[0]+" New folder is not being created after clicking on Save button.");
						}
						if(FindElement(driver, "//label[contains(text(),'"+internalFolderPath.split(",")[1].split("/")[0]+"')]/../../../following-sibling::ul/li/div/a/span/label[contains(text(),'"+internalFolderPath.split(",")[1].split("/")[1]+"')]/../../../following-sibling::ul/li/div/a/span/label[contains(text(),'"+internalFolderPath.split(",")[1].split("/")[2]+"')]", "Folder in view", action.BOOLEAN, 30)!=null){
							appLog.info(internalFolderPath.split(",")[1]+" Folder is successfully created and is verified.");
						} else {
							appLog.error(internalFolderPath.split(",")[1]+" New folder is not being created after clicking on Save button.");
							sa.assertTrue(false,internalFolderPath.split(",")[1]+" New folder is not being created after clicking on Save button.");
						}
						if(fp.createFolderStructure(standardFolderPath.split(",")[1], FolderType.Standard, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
							appLog.info(standardFolderPath.split(",")[1]+ " folder structure is created.");
						} else {
							appLog.error(standardFolderPath.split(",")[1]+ " folder structure is not created.");
							sa.assertTrue(false,standardFolderPath.split(",")[1]+ " folder structure is not created.");
						}
						if(fp.createFolderStructure(standardFolderPath.split(",")[2], FolderType.Standard, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
							appLog.info(standardFolderPath.split(",")[2]+ " folder structure is created.");
						} else {
							appLog.error(standardFolderPath.split(",")[2]+ " folder structure is not created.");
							sa.assertTrue(false,standardFolderPath.split(",")[2]+ " folder structure is not created.");
						}
//						if(fp.verifyFolderPathdummy(standardFolderPath.split(",")[1], null, null, null, PageName.ManageFolderPopUp, Workspace.FundraisingWorkspace, 5)){
//							appLog.info(standardFolderPath.split(",")[1]+ " folder structure is created after clicking on save button.");
//						} else {
//							appLog.error(standardFolderPath.split(",")[1]+ " folder structure is not created after clicking on save button.");
//							sa.assertTrue(false,standardFolderPath.split(",")[1]+ " folder structure is not created after clicking on save button.");
//						}
//						if(fp.verifyFolderPathdummy(standardFolderPath.split(",")[2], null, null, null, PageName.ManageFolderPopUp, Workspace.FundraisingWorkspace, 5)){
//							appLog.info(standardFolderPath.split(",")[2]+ " folder structure is created after clicking on save button.");
//						} else {
//							appLog.error(standardFolderPath.split(",")[2]+ " folder structure is not created after clicking on save button.");
//							sa.assertTrue(false,standardFolderPath.split(",")[2]+ " folder structure is not created after clicking on save button.");
//						}
						if(FindElement(driver, "//label[contains(text(),'"+standardFolderPath.split(",")[1].split("/")[0]+"')]/../../../following-sibling::ul/li/div/a/span/label[contains(text(),'"+standardFolderPath.split(",")[1].split("/")[1]+"')]", "Folder in view", action.BOOLEAN, 30)!=null){
							appLog.info(standardFolderPath.split(",")[1]+" Folder is successfully created and is verified.");
						} else {
							appLog.error(standardFolderPath.split(",")[1]+" New folder is not being created after clicking on Save button.");
							sa.assertTrue(false,standardFolderPath.split(",")[1]+" New folder is not being created after clicking on Save button.");
						}
						if(FindElement(driver, "//label[contains(text(),'"+standardFolderPath.split(",")[2].split("/")[0]+"')]/../../../following-sibling::ul/li/div/a/span/label[contains(text(),'"+standardFolderPath.split(",")[2].split("/")[1]+"')]/../../../following-sibling::ul/li/div/a/span/label[contains(text(),'"+standardFolderPath.split(",")[2].split("/")[2]+"')]", "Folder in view", action.BOOLEAN, 30)!=null){
							appLog.info(standardFolderPath.split(",")[2]+" Folder is successfully created and is verified.");
						} else {
							appLog.error(standardFolderPath.split(",")[2]+" New folder is not being created after clicking on Save button.");
							sa.assertTrue(false,standardFolderPath.split(",")[2]+" New folder is not being created after clicking on Save button.");
						}
					}
					
				} else {
					appLog.error("Manage folder icon cannot be clicked, So cannto continue with tc.");
					sa.assertTrue(false,"Manage folder icon cannot be clicked, So cannto continue with tc.");
				}
			} else {
				appLog.error(M5F2+" fund is not available so cannot continue with the tc.");
				sa.assertTrue(false,M5F2+" fund is not available so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot check add a folder pop up.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot check add a folder pop up.");
		}
		switchToDefaultContent(driver);
		if(fp.clickOnTab(TabName.InstituitonsTab)){
			if(ins.clickOnCreatedInstitution(M5I1)){
				switchToFrame(driver, 30, ins.getFrame( PageName.InstitutionsPage, 30));
				scrollDownThroughWebelement(driver, ins.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), Workspace.FundraisingWorkspace+" View.");
				if(fp.verifyFolderPathdummy(commonFolderPath.split(",")[0], null, null, M5F2, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}

				if(fp.verifyFolderPathdummy(commonFolderPath.split(",")[1], null, null, M5F2, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}
				
				if(fp.verifyFolderPathdummy(standardFolderPath.split(",")[1], null, null, M5F2, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}
				if(fp.verifyFolderPathdummy(standardFolderPath.split(",")[2], null, null, M5F2, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}
				if(fp.verifyFolderPathdummy(sharedFolderPath.split(",")[0], null, null, M5F2, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}
				if(fp.verifyFolderPathdummy(sharedFolderPath.split(",")[1], null, null, M5F2, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}
				
				if(fp.verifyFolderPathdummy(internalFolderPath.split(",")[0], null, null, M5F2, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}
				if(fp.verifyFolderPathdummy(internalFolderPath.split(",")[1], null, null, M5F2, PageName.InstitutionsPage, Workspace.FundraisingWorkspace, 30)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}
				switchToDefaultContent(driver);
			} else {
				appLog.error(M5I1+" Instituion is not present in the list.");
				sa.assertTrue(false,M5I1+" Instituion is not present in the list.");
			}
		} else {
			appLog.error("Insitutions Tab cannot be clicked, So cannot verify folder structure on institution page.");
			sa.assertTrue(false,"Insitutions Tab cannot be clicked, So cannot verify folder structure on institution page.");
		}
		if(lp.clickOnTab(TabName.ContactTab)){
			if(con .clickOnCreatedContact(M5CFN1, M5CLN1, null)){
				switchToFrame(driver, 30, con.getFrame( PageName.ContactsPage, 30));
				if(fp.verifyFolderPathdummy(commonFolderPath.split(",")[0], null, null, M5F2, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}

				if(fp.verifyFolderPathdummy(commonFolderPath.split(",")[1], null, null, M5F2, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}
				
				if(fp.verifyFolderPathdummy(standardFolderPath.split(",")[1], M5I1, null, M5F2, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}
				if(fp.verifyFolderPathdummy(standardFolderPath.split(",")[2], M5I1, null, M5F2, PageName.ContactsPage, Workspace.FundraisingWorkspace, 30)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}
			} else {
				appLog.error(M5CFN1 +" "+ M5CLN1+" contact is not present in the list, So cannot continue wih the tc.");
				sa.assertTrue(false,M5CFN1 +" "+ M5CLN1+" contact is not present in the list, So cannot continue wih the tc.");
			}
		} else {
			appLog.error("Contact tab cannot be clicked, So cannot continue.");
			sa.assertTrue(false,"Contact tab cannot be clicked, So cannot continue.");
		}
		
		sa.assertAll();
	}

	@Test
	public void M5tc007_2_VerifyAddAFolderPopUpFundraisingWorkspaceAtTargetSide(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		lp.investorLogin(M5C1Email, adminPassword);
		String dependOntc="M5tc007_1_VerifyAddASubFolderPopUpFundraisingWorkspace";
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String standardFolderPath = ExcelUtils.readData("filePath", excelLabel.TestCases_Name, dependOntc, excelLabel.StandardPath);
		String commonFolderPath = ExcelUtils.readData("filePath", excelLabel.TestCases_Name, dependOntc, excelLabel.CommonPath);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		if(ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)){
			if(fp.verifyFolderPathdummy(commonFolderPath.split(",")[0], null, null, M5F2, PageName.PotentialInvestmentPage, null, 30)){
				appLog.info("Folder structure is verified on Potential Investment page of '"+M5I1+"'.");
			} else {
				appLog.error("Folder structure is not verified on Potential Investment page of '"+M5I1+"'.");
				sa.assertTrue(false,"Folder structure is not verified on Potential Investment page of '"+M5I1+"'.");
			}

			if(fp.verifyFolderPathdummy(commonFolderPath.split(",")[1], null, null, M5F2, PageName.PotentialInvestmentPage, null, 30)){
				appLog.info("Folder structure is verified on Potential Investment page of '"+M5I1+"'.");
			} else {
				appLog.error("Folder structure is not verified on Potential Investment page of '"+M5I1+"'.");
				sa.assertTrue(false,"Folder structure is not verified on Potential Investment page of '"+M5I1+"'.");
			}
			
			if(fp.verifyFolderPathdummy(standardFolderPath.split(",")[1], M5I1, null, null, PageName.PotentialInvestmentPage, null, 30)){
				appLog.info("Folder structure is verified on Potential Investment page of '"+M5I1+"'.");
			} else {
				appLog.error("Folder structure is not verified on Potential Investment page of '"+M5I1+"'.");
				sa.assertTrue(false,"Folder structure is not verified on Potential Investment page of '"+M5I1+"'.");
			}
			if(fp.verifyFolderPathdummy(standardFolderPath.split(",")[2], M5I1, null, null, PageName.PotentialInvestmentPage, null, 30)){
				appLog.info("Folder structure is verified on Potential Investment page of '"+M5I1+"'.");
			} else {
				appLog.error("Folder structure is not verified on Potential Investment page of '"+M5I1+"'.");
				sa.assertTrue(false,"Folder structure is not verified on Potential Investment page of '"+M5I1+"'.");
			}
		} else {
			appLog.error("Investment tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Investment tab cannot be clicked, SO cannot continue with the tc.");
		}
	}
	
	@Test
	public void M5tc008_verifyErrorMsgOnParentLevel() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		String STDFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		String commonFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath);
		String InternalFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.InternalPath);
		String SHRDFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M5F2)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view.");
				if(click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 30), "Manage folder icon", action.BOOLEAN)){
					if(click(driver, fp.getAllFolderAddIcon(Workspace.FundraisingWorkspace, 30), "Add folder button", action.BOOLEAN)){
						String[] radioBtn= {STDFolderName,commonFolderName,SHRDFolderName,InternalFolderName};
						for(int i=0; i<radioBtn.length; i++) {
							boolean flag =false;
							if(i==0) {
								flag=fp.verifyFolderErrorMessage(FolderType.Standard, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,fp.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20);
							}if(i==1) {
								flag=fp.verifyFolderErrorMessage(FolderType.Common, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,fp.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20);
							}if(i==2) {
								flag=fp.verifyFolderErrorMessage(FolderType.Shared, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,fp.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20);
							}if(i==3) {
								flag=fp.verifyFolderErrorMessage(FolderType.Internal, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,fp.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20);
							}
							if(flag) {
								appLog.info(radioBtn[i]+" folder Error Message is verified successfully.");
							}else {
								appLog.error(radioBtn[i]+" folder Error Message is not verified.");
								sa.assertTrue(false, radioBtn[i]+" folder Error Message is not verified.");
							}
						}
						List<String> value = getValueBasedOnLabel(filePath, "SpecialChar",excelLabel.INVALID_FOLDER_NAME, 1);
						if(!value.isEmpty()) {
								for(int i=1; i<radioBtn.length; i++) {
									boolean flag =false;
									if(i==1) {
										flag=fp.verifyFolderErrorMessage(FolderType.Common, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,fp.prefixErrorMsg,value.get(i-1),ErrorMessageType.PrefixErrorMsg,20);
									}if(i==2) {
										flag=fp.verifyFolderErrorMessage(FolderType.Shared, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,fp.prefixErrorMsg,value.get(i-1),ErrorMessageType.PrefixErrorMsg,20);
									}if(i==3) {
										flag=fp.verifyFolderErrorMessage(FolderType.Internal, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,fp.prefixErrorMsg,value.get(i-1),ErrorMessageType.PrefixErrorMsg,20);
									}
									if(flag) {
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
									boolean flag =false;
									if(i==0) {
										flag=fp.verifyFolderErrorMessage(FolderType.Standard, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,fp.speicalCharErrorMsg,specialChar.get(j),ErrorMessageType.SpiecalCharErrorMsg,20);
									}if(i==1) {
										flag=fp.verifyFolderErrorMessage(FolderType.Common, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,fp.speicalCharErrorMsg,specialChar.get(j),ErrorMessageType.SpiecalCharErrorMsg,20);
									}if(i==2) {
										flag=fp.verifyFolderErrorMessage(FolderType.Shared, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,fp.speicalCharErrorMsg,specialChar.get(j),ErrorMessageType.SpiecalCharErrorMsg,20);
									}if(i==3) {
										flag=fp.verifyFolderErrorMessage(FolderType.Internal, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,fp.speicalCharErrorMsg,specialChar.get(j),ErrorMessageType.SpiecalCharErrorMsg,20);
									}
									if(flag) {
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
						for(int i=0; i<2; i++) {
							WebElement ele=null;
							ele=bp.getFolderTypeRadioButton(FolderType.Common,Workspace.FundraisingWorkspace,PageName.ManageFolderPopUp, 30);
							if(ele!=null) {
								if(click(driver, ele,"common folder radio button icon", action.BOOLEAN)) {
									appLog.info("clicked on common radio button");
										if(sendKeys(driver, bp.getParentFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,20),commonFolderName,"Common folder text box", action.BOOLEAN)) {
											appLog.info("value pass in folder Common text box: "+commonFolderName);
											if(click(driver, bp.getParentFolderSaveButton(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20), "folder save button", action.BOOLEAN)) {
												appLog.info("clicked on save button");
												WebElement ele1=bp.getFolderErrorMsg(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, ErrorMessageType.FolderCreationRestrictionErrorMsg, FolderType.Common,20);
												if(ele1!=null) {
													String aa=ele1.getText().trim();
													if(aa.contains(FundsPageErrorMessage.CommonFolderCreationErrorMsg)) {
														appLog.info("Folder error messgae is verified for folder type Common");
														
													}else {
														appLog.error("Folder error message is not verified for folder type Common Expected: "+FundsPageErrorMessage.CommonFolderCreationErrorMsg+"/t Actual: "+aa);
														sa.assertTrue(false, "Folder error message is not verified for folder type Common Expected: "+FundsPageErrorMessage.CommonFolderCreationErrorMsg+"/t Actual: "+aa);
													}
													if(i==0) {
														if(click(driver, fp.getFolderCreationErrorMessageCrossIcon(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, FolderType.Common,30),"common folder error message cross icon", action.BOOLEAN)) {
															appLog.info("clicked on common folder error message cross icon");
															if(fp.getFolderCreationErrorMessageOkBtn(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, FolderType.Common,20)==null) {
																appLog.info("error message pop is closed after click on cross icon");
																if(click(driver, fp.getAllFolderAddIcon(Workspace.FundraisingWorkspace, 30), "Add folder button", action.BOOLEAN)){
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
														if(click(driver, fp.getFolderCreationErrorMessageOkBtn(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, FolderType.Common,30),"common folder error message cross icon", action.BOOLEAN)) {
															appLog.info("clicked on common folder error message OK button");
															if(fp.getFolderCreationErrorMessageOkBtn(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, FolderType.Common,20)==null) {
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
											appLog.info("Not able to pass value in folder Common text box: "+commonFolderName+" so cannot check error message for common folder. "+FundsPageErrorMessage.CommonFolderCreationErrorMsg);
											sa.assertTrue(false, "Not able to pass value in folder Common text box: "+commonFolderName+" so cannot check error message for common folder. "+FundsPageErrorMessage.CommonFolderCreationErrorMsg);
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
						if(click(driver, fp.getAllFolderAddIcon(Workspace.FundraisingWorkspace, 30), "Add folder button", action.BOOLEAN)){
							for(int i=0; i<2; i++) {
								WebElement ele=null;
								ele=bp.getFolderTypeRadioButton(FolderType.Internal,Workspace.FundraisingWorkspace,PageName.ManageFolderPopUp, 30);
								if(ele!=null) {
									if(click(driver, ele,"Internal folder radio button icon", action.BOOLEAN)) {
										appLog.info("clicked on Internal radio button");
											if(sendKeys(driver, bp.getParentFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,20),InternalFolderName,"Internal folder text box", action.BOOLEAN)) {
												appLog.info("value pass in folder Internal text box: "+InternalFolderName);
												if(click(driver, bp.getParentFolderSaveButton(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20), "folder save button", action.BOOLEAN)) {
													appLog.info("clicked on save button");
													WebElement ele1=bp.getFolderErrorMsg(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, ErrorMessageType.FolderCreationRestrictionErrorMsg, FolderType.Internal,20);
													if(ele1!=null) {
														String aa=ele1.getText().trim();
														if(aa.contains(FundsPageErrorMessage.InternalFolderCreationErrorMsg)) {
															appLog.info("Folder error messgae is verified for folder type Internal");
															
														}else {
															appLog.error("Folder error message is not verified for folder type Internal Expected: "+FundsPageErrorMessage.InternalFolderCreationErrorMsg+"/t Actual: "+aa);
															sa.assertTrue(false, "Folder error message is not verified for folder type Internal Expected: "+FundsPageErrorMessage.InternalFolderCreationErrorMsg+"/t Actual: "+aa);
														}
														if(i==0) {
															if(click(driver, fp.getFolderCreationErrorMessageCrossIcon(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, FolderType.Internal,30),"Internal folder error message cross icon", action.BOOLEAN)) {
																appLog.info("clicked on Internal folder error message cross icon");
																if(fp.getFolderCreationErrorMessageOkBtn(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, FolderType.Internal,20)==null) {
																	appLog.info("error message pop is closed after click on cross icon");
																	if(click(driver, fp.getAllFolderAddIcon(Workspace.FundraisingWorkspace, 30), "Add folder button", action.BOOLEAN)){
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
															if(click(driver, fp.getFolderCreationErrorMessageOkBtn(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, FolderType.Internal,30),"Internal folder error message cross icon", action.BOOLEAN)) {
																appLog.info("clicked on Internal folder error message OK button");
																if(fp.getFolderCreationErrorMessageOkBtn(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, FolderType.Internal,20)==null) {
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
												appLog.info("Not able to pass value in folder Internal text box: "+InternalFolderName+" so cannot check error message"+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
												sa.assertTrue(false, "Not able to pass value in folder Internal text box: "+InternalFolderName+" so cannot check error message"+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
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
						String[] ss= {STDFolderName,SHRDFolderName};
						if(click(driver, fp.getAllFolderAddIcon(Workspace.FundraisingWorkspace, 30), "Add folder button", action.BOOLEAN)){
							for(int i=0; i<2; i++) {
								WebElement ele=null;
								if(i==0) {
									ele=bp.getFolderTypeRadioButton(FolderType.Standard,Workspace.FundraisingWorkspace,PageName.ManageFolderPopUp, 30);									
								}else {
									ele=bp.getFolderTypeRadioButton(FolderType.Shared,Workspace.FundraisingWorkspace,PageName.ManageFolderPopUp, 30);	
								}
								if(ele!=null) {
									if(click(driver, ele,ss[i]+" folder radio button icon", action.BOOLEAN)) {
										appLog.info("clicked on "+ss[i]+" radio button");
											if(sendKeys(driver, bp.getParentFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,20),ss[i],ss[i]+" folder text box", action.BOOLEAN)) {
												appLog.info("value pass in folder text box: "+ss[i]);
												boolean cssFlag=false;
												String cssSelectorPath="a[onclick*='CreateCommon_pop1fundraising();']";
												cssFlag=bp.clickUsingCssSelectorPath(cssSelectorPath, "Parent Folder Save Button");
												if(cssFlag/*click(driver, bp.getParentFolderSaveButton(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20), "folder save button", action.BOOLEAN)*/) {
													appLog.info("clicked on save button");
													ThreadSleep(2000);
													boolean isAlertPresent = isAlertPresent(driver);
													appLog.info("isAlertPresent  : "+isAlertPresent);
													if (true/*isAlertPresent(driver)*/) {
														appLog.info("Folder already exist alert pop up is successfully displayed");
														String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
														switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
														appLog.error("Error Message is displaying : "+msg);
														if(msg.contains(FundsPageErrorMessage.FolderAlreadyExistErrormsg)) {
															appLog.info("Error msg is verified successfully.");
														}else {
															appLog.error("Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
															sa.assertTrue(false, "Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
														}
													} else {
														appLog.error("Folder already exist alert pop up is not displayed for "+STDFolderName+" folder");
														sa.assertTrue(false, "Folder already exist alert pop up is not displayed for "+STDFolderName+" folder");
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
							}
							
						}else {
							appLog.error("Not able to click on Add folder icon so cannot check folder error message. Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
							sa.assertTrue(false, "Not able to click on Add folder icon so cannot check folder error message. Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
						}
						
					}else {
						appLog.error("Not able to click on Add folder icon so cannot check error messsgae in fundraising workspace");
						sa.assertTrue(false, "Not able to click on Add folder icon so cannot check error messsgae in fundraising workspace");
					}
					
				}else {
					appLog.error("Not able to click on Manage folder Icon so cannot check error message in fundraising workspace");
					sa.assertTrue(false, "Not able to click on Manage folder Icon so cannot check error message in fundraising workspace");
				}
				
			}else {
				appLog.error(M5F2+" fund is not available so cannot continue with the tc.");
				sa.assertTrue(false,M5F2+" fund is not available so cannot continue with the tc.");
			}
			
		}else {
			appLog.error("Funds tab cannot be clicked, So cannot check add a folder pop up.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot check add a folder pop up.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Test
	public void M5tc009_verifyErrorMsgOnSubLevel() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String STDFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		String[] stdFolderName=STDFolderName.split(",");
		String[] commonFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath).split(",");
		String[] InternalFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.InternalPath).split(",");
		String[] SHRDFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath).split(",");
		List<String> PrefixValue = getValueBasedOnLabel(filePath, "SpecialChar",excelLabel.INVALID_FOLDER_NAME, 1);
		List<String> specialChar = getValueBasedOnLabel(filePath, "SpecialChar",excelLabel.FOLDER_NAME, 1);
		WebElement ele=null;
		boolean cssFlag=false;
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M5F2)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view.");
				if(click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 30), "Manage folder icon", action.BOOLEAN)){
						//String[] radioBtn= {STDFolderName,commonFolderName,SHRDFolderName,InternalFolderName};
						String id=null;
						id=fp.getCreatedFolderId(stdFolderName[0], PageName.ManageFolderPopUp, 20);
						if(id!=null) {
							if(fp.clickOnAddFolderButton(id)) {
								if(fp.verifyFolderErrorMessageSubLevel(FolderType.Standard, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20)) {
									appLog.info("Error Message is verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
								}else {
									appLog.error("Error Message is not verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
								}
								if(fp.verifyFolderErrorMessageSubLevel(FolderType.Standard, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.prefixErrorMsg,PrefixValue.get(0),ErrorMessageType.PrefixErrorMsg,20)) {
									appLog.info("Error Message is verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
								}else {
									appLog.error("Error Message is not verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
								}
								if(fp.verifyFolderErrorMessageSubLevel(FolderType.Standard, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.speicalCharErrorMsg,specialChar.get(0),ErrorMessageType.SpiecalCharErrorMsg,20)) {
									appLog.info("Error Message is verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
								}else {
									appLog.error("Error Message is not verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
								}
								if(sendKeys(driver, fp.getChildFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20), stdFolderName[1], stdFolderName[0]+" sub folder text box", action.BOOLEAN)) {
								String cssSelectorPath="a[onclick*='CreateFolder_pop1fundraising();']";
									cssFlag=fp.clickUsingCssSelectorPath(cssSelectorPath, "Child Folder Save Button");
									if(cssFlag/*click(driver, fp.getChildFolderSaveButton(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20), "sub folder save button", action.BOOLEAN)*/) {
										ThreadSleep(2000);
										boolean isAlertPresent = isAlertPresent(driver);
										appLog.info("isAlertPresent  : "+isAlertPresent);
										if (true/*isAlertPresent(driver)*/) {
											appLog.info("Folder already exist alert pop up is successfully displayed");
											String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
											switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
											appLog.error("Error Message is displaying : "+msg);
											if(msg.contains(FundsPageErrorMessage.FolderAlreadyExistErrormsg)) {
												appLog.info("Error msg is verified successfully.");
											}else {
												appLog.error("Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
												sa.assertTrue(false, "Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
											}
										} else {
											appLog.error("Folder already exist alert pop up is not displayed for "+stdFolderName[0]+" folder");
											sa.assertTrue(false, "Folder already exist alert pop up is not displayed for "+stdFolderName[0]+" folder");
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
						id=fp.getCreatedFolderId(commonFolderName[0], PageName.ManageFolderPopUp, 20);
						if(id!=null) {
							if(fp.clickOnAddFolderButton(id)) {
								if(fp.verifyFolderErrorMessageSubLevel(FolderType.Common, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20)) {
									appLog.info("Error Message is verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
								}else {
									appLog.error("Error Message is not verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
								}
								if(fp.verifyFolderErrorMessageSubLevel(FolderType.Common, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.prefixErrorMsg,PrefixValue.get(1),ErrorMessageType.PrefixErrorMsg,20)) {
									appLog.info("Error Message is verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
								}else {
									appLog.error("Error Message is not verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
								}
								if(fp.verifyFolderErrorMessageSubLevel(FolderType.Common, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.speicalCharErrorMsg,specialChar.get(0),ErrorMessageType.SpiecalCharErrorMsg,20)) {
									appLog.info("Error Message is verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
								}else {
									appLog.error("Error Message is not verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
								}
								if(sendKeys(driver, fp.getChildFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20), commonFolderName[1], commonFolderName[0]+" sub folder text box", action.BOOLEAN)) {
									String cssSelectorPath="a[onclick*='CreateFolder_pop1fundraising();']";
									cssFlag=fp.clickUsingCssSelectorPath(cssSelectorPath, "Child Folder Save Button");
									if(cssFlag/*click(driver, fp.getChildFolderSaveButton(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20), "sub folder save button", action.BOOLEAN)*/) {
										ThreadSleep(2000);
										boolean isAlertPresent = isAlertPresent(driver);
										appLog.info("isAlertPresent  : "+isAlertPresent);
										if (true/*isAlertPresent(driver)*/) {
											appLog.info("Folder already exist alert pop up is successfully displayed");
											String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
											switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
											appLog.error("Error Message is displaying : "+msg);
											if(msg.contains(FundsPageErrorMessage.FolderAlreadyExistErrormsg)) {
												appLog.info("Error msg is verified successfully.");
											}else {
												appLog.error("Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
												sa.assertTrue(false, "Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
											}
										} else {
											appLog.error("Folder already exist alert pop up is not displayed for "+commonFolderName[0]+" folder");
											sa.assertTrue(false, "Folder already exist alert pop up is not displayed for "+commonFolderName[0]+" folder");
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
						id=fp.getCreatedFolderId(SHRDFolderName[0], PageName.ManageFolderPopUp, 20);
						if(id!=null) {
							if(fp.clickOnAddFolderButton(id)) {
								if(fp.verifyFolderErrorMessageSubLevel(FolderType.Shared, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20)) {
									appLog.info("Error Message is verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
								}else {
									appLog.error("Error Message is not verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
								}
								if(fp.verifyFolderErrorMessageSubLevel(FolderType.Shared, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.prefixErrorMsg,PrefixValue.get(2),ErrorMessageType.PrefixErrorMsg,20)) {
									appLog.info("Error Message is verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
								}else {
									appLog.error("Error Message is not verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
								}
								if(fp.verifyFolderErrorMessageSubLevel(FolderType.Shared, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.speicalCharErrorMsg,specialChar.get(0),ErrorMessageType.SpiecalCharErrorMsg,20)) {
									appLog.info("Error Message is verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
								}else {
									appLog.error("Error Message is not verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
								}
								if(sendKeys(driver, fp.getChildFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20), SHRDFolderName[1], SHRDFolderName[0]+" sub folder text box", action.BOOLEAN)) {
									String cssSelectorPath="a[onclick*='CreateFolder_pop1fundraising();']";
									cssFlag=fp.clickUsingCssSelectorPath(cssSelectorPath, "Child Folder Save Button");
									if(cssFlag/*click(driver, fp.getChildFolderSaveButton(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20), "sub folder save button", action.BOOLEAN)*/) {
										ThreadSleep(2000);
										boolean isAlertPresent = isAlertPresent(driver);
										appLog.info("isAlertPresent  : "+isAlertPresent);
										if (true/*isAlertPresent(driver)*/) {
											appLog.info("Folder already exist alert pop up is successfully displayed");
											String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
											switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
											appLog.error("Error Message is displaying : "+msg);
											if(msg.contains(FundsPageErrorMessage.FolderAlreadyExistErrormsg)) {
												appLog.info("Error msg is verified successfully.");
											}else {
												appLog.error("Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
												sa.assertTrue(false, "Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
											}
										} else {
											appLog.error("Folder already exist alert pop up is not displayed for "+SHRDFolderName[0]+" folder");
											sa.assertTrue(false, "Folder already exist alert pop up is not displayed for "+SHRDFolderName[0]+" folder");
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
						id=fp.getCreatedFolderId(InternalFolderName[0], PageName.ManageFolderPopUp, 20);
						if(id!=null) {
							if(fp.clickOnAddFolderButton(id)) {
								if(fp.verifyFolderErrorMessageSubLevel(FolderType.Internal, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20)) {
									appLog.info("Error Message is verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
								}else {
									appLog.error("Error Message is not verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
								}
								if(fp.verifyFolderErrorMessageSubLevel(FolderType.Internal, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.prefixErrorMsg,PrefixValue.get(2),ErrorMessageType.PrefixErrorMsg,20)) {
									appLog.info("Error Message is verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
								}else {
									appLog.error("Error Message is not verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
								}
								if(fp.verifyFolderErrorMessageSubLevel(FolderType.Internal, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.speicalCharErrorMsg,specialChar.get(0),ErrorMessageType.SpiecalCharErrorMsg,20)) {
									appLog.info("Error Message is verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
								}else {
									appLog.error("Error Message is not verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
								}
								if(sendKeys(driver, fp.getChildFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20), InternalFolderName[1], InternalFolderName[0]+" sub folder text box", action.BOOLEAN)) {
									String cssSelectorPath="a[onclick*='CreateFolder_pop1fundraising();']";
									cssFlag=fp.clickUsingCssSelectorPath(cssSelectorPath, "Child Folder Save Button");
									if(cssFlag/*click(driver, fp.getChildFolderSaveButton(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20), "sub folder save button", action.BOOLEAN)*/) {
										ThreadSleep(2000);
										boolean isAlertPresent = isAlertPresent(driver);
										appLog.info("isAlertPresent  : "+isAlertPresent);
										if (true/*isAlertPresent(driver)*/) {
											appLog.info("Folder already exist alert pop up is successfully displayed");
											String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
											switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
											appLog.error("Error Message is displaying : "+msg);
											if(msg.contains(FundsPageErrorMessage.FolderAlreadyExistErrormsg)) {
												appLog.info("Error msg is verified successfully.");
											}else {
												appLog.error("Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
												sa.assertTrue(false, "Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
											}
										} else {
											appLog.error("Folder already exist alert pop up is not displayed for "+InternalFolderName[0]+" folder");
											sa.assertTrue(false, "Folder already exist alert pop up is not displayed for "+InternalFolderName[0]+" folder");
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
					}else {
						appLog.error("Not able to click on Manage folder Icon so cannot check error message in fundraising workspace");
						sa.assertTrue(false, "Not able to click on Manage folder Icon so cannot check error message in fundraising workspace");
					}
				}else {
					appLog.error(M5F2+" fund is not available so cannot continue with the tc.");
					sa.assertTrue(false,M5F2+" fund is not available so cannot continue with the tc.");
				}
		}else {
			appLog.error("Not able to click on Fund Tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on Fund Tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Test
	public void M5tc010_1_verifyEditFolderAndUpdateFolderName() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String STDFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		String[] stdFolderName=STDFolderName.split(",");
		String[] commonFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath).split(",");
		String[] InternalFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.InternalPath).split(",");
		String[] SHRDFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath).split(",");
		List<String> PrefixValue = getValueBasedOnLabel(filePath, "SpecialChar",excelLabel.INVALID_FOLDER_NAME, 1);
		List<String> specialChar = getValueBasedOnLabel(filePath, "SpecialChar",excelLabel.FOLDER_NAME, 1);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M5F2)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view.");
				if(click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 30), "Manage folder icon", action.BOOLEAN)){
						//String[] radioBtn= {STDFolderName,commonFolderName,SHRDFolderName,InternalFolderName};
						String id=null;
						id=fp.getCreatedFolderId(commonFolderName[0], PageName.ManageFolderPopUp, 20);
						System.err.println("id>>>>>>"+id);
						if(id!=null) {
							if(fp.clickOnRenameFolderButton(id)) {
								if(fp.verifyRenameFolderErrorMessageParentLevel(FolderType.Common, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg,"",ErrorMessageType.BlankErrorMsg,20)) {
									appLog.info("Error Message is verified for Parnet Level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
								}else {
									appLog.error("Error Message is not verified for parent Level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
								}
								for(int i=0; i<PrefixValue.size(); i++) {
									if(fp.verifyRenameFolderErrorMessageParentLevel(FolderType.Common, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.prefixErrorMsg,PrefixValue.get(i),ErrorMessageType.PrefixErrorMsg,20)) {
										appLog.info("Error Message is verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
									}else {
										appLog.error("Error Message is not verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
										sa.assertTrue(false, "Error Message is not verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
									}
								}
								if(fp.verifyRenameFolderErrorMessageParentLevel(FolderType.Common, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.speicalCharErrorMsg,specialChar.get(0),ErrorMessageType.SpiecalCharErrorMsg,20)) {
									appLog.info("Error Message is verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
								}else {
									appLog.error("Error Message is not verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
								}
								
								if(click(driver, fp.getParentRenameFolderCrossIcon(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20), "rename parent folder cross icon", action.BOOLEAN)) {
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
							appLog.error(commonFolderName[0]+" folder is available in manage folder so cannot check error messgaes on Parent Rename Common folder");
							sa.assertTrue(false, stdFolderName[0]+" folder is available in manage folder so cannot check error messgaes on Parent Rename Common folder");
						}
						String[] ss= {commonFolderName[0]+"/"+commonFolderName[1],SHRDFolderName[0]+"/"+SHRDFolderName[1],InternalFolderName[0]+"/"+InternalFolderName[1],stdFolderName[0]+"/"+stdFolderName[1]};
						String[] ss1= {commonFolderName[1],SHRDFolderName[1],InternalFolderName[1],stdFolderName[1]};
						String[] ss2= {commonFolderName[2],SHRDFolderName[2],InternalFolderName[2],stdFolderName[2]};
						for(int i=0; i<ss.length; i++) {
							id=null;
							id=fp.getCreatedFolderId(ss[i], PageName.ManageFolderPopUp, 20);
							System.err.println("Folder Id is: >>>>>>"+id);
							if(id!=null) {
								if(fp.clickOnRenameFolderButton(id)) {
									ThreadSleep(2000);
									if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20), ss2[i], ss1[i]+" sub folder text box", action.BOOLEAN)) {
										boolean cssFlag=false;
										String cssSelectorPath="a[onclick*='Save_Rename_folder_pop1fundraising();']";
										cssFlag=fp.clickUsingCssSelectorPath(cssSelectorPath, "Parent Rename Folder Save Button");
										if(cssFlag/*click(driver, fp.getParentRenameFolderSaveButton(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20), "sub folder save button", action.BOOLEAN)*/) {
											ThreadSleep(2000);
											boolean isAlertPresent = isAlertPresent(driver);
											appLog.info("isAlertPresent  : "+isAlertPresent);
											if (true/*isAlertPresent(driver)*/) {
												appLog.info("Folder already exist alert pop up is successfully displayed");
												String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
												switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
												appLog.error("Error Message is displaying : "+msg);
												if(msg.contains(FundsPageErrorMessage.FolderAlreadyExistErrormsg)) {
													appLog.info("Error msg is verified successfully.");
													if(click(driver, fp.getParentRenameFolderCrossIcon(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20), "rename folder pop up cross icon", action.BOOLEAN)) {
														appLog.info("clicked on Cross icon");
													}else {
														appLog.error("Not able to click on rename folder "+ss1[i]+" cross icon so cannot close rename folder pop up and cannot check Error Message for other folders");
														sa.assertTrue(false, "Not able to click on rename folder "+ss1[i]+" cross icon so cannot close rename folder pop up and cannot check Error Message for other folders");
														break;
													}
												}else {
													appLog.error("Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
													sa.assertTrue(false, "Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
												}
											} else {
												appLog.error("Folder already exist alert pop up is not displayed for "+ss1[i]+" folder");
												sa.assertTrue(false, "Folder already exist alert pop up is not displayed for "+ss1[i]+" folder");
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
								appLog.error(ss1[i]+" folder is available in manage folder so cannot check error messgaes on sub Common folder");
								sa.assertTrue(false, ss1[i]+" folder is available in manage folder so cannot check error messgaes on sub Common folder");
							}
							
						}
						for(int i=0; i<2; i++) {
							id=null;
							id=fp.getCreatedFolderId(commonFolderName[0], PageName.ManageFolderPopUp, 20);
							System.err.println("id>>>>>>"+id);
							if(id!=null) {
								if(fp.clickOnRenameFolderButton(id)) {
									if(i==0) {
										if(fp.getParentRenameFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20)!=null) {
											appLog.info("Rename Folder pop up is open");
										}else {
											appLog.error("Rename folder Pop up is not open");
											sa.assertTrue(false, "Rename folder Pop up is not open");
										}
										String prefillValue=null;
										prefillValue= getValueFromElementUsingJavaScript(driver,fp.getParentRenameFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20),"");
										if(prefillValue!=null) {
											if(prefillValue.trim().equalsIgnoreCase(commonFolderName[0].split(" ")[0])) {
												appLog.info(commonFolderName[0].split(" ")[0]+" value is displaying in rename folder pop up");
											}else {
												appLog.error(commonFolderName[0].split(" ")[0]+" value is not displaying in rename folder pop up");
												sa.assertTrue(false, commonFolderName[0].split(" ")[0]+" value is not displaying in rename folder pop up");
											}
										}else {
											appLog.error("Rename Folder text box is not available so cannot get value from rename folder text box");
											sa.assertTrue(false, "Rename Folder text box is not available so cannot get value from rename folder text box");
										}
										if(fp.getParentRenameFolderCrossIcon(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 10)!=null) {
											appLog.info("Cross icon is visible in rename folder pop up");
										}else {
											appLog.error("Cross icon is not visible in rename folder pop up");
											sa.assertTrue(false, "Cross icon is not visible in rename folder pop up");
										}
										if(fp.getParentRenameFolderSaveButton(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 10)!=null) {
											appLog.info("Save is visible in rename folder pop up");
										}else {
											appLog.error("Save is not visible in rename folder pop up");
											sa.assertTrue(false, "Save is not visible in rename folder pop up");
										}
										if(fp.getParentRenameFolderCancelButton(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 10)!=null) {
											appLog.info("Cancel button is visible in rename folder pop up");
										}else {
											appLog.error("Cancel button is not visible in rename folder pop up");
											sa.assertTrue(false, "Cancel button is not visible in rename folder pop up");
										}
									}
									WebElement ele=null;
									String aa= null;
									if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,20),"","rename parent folder text box", action.BOOLEAN)) {
										if(i==0) {
											 ele=fp.getParentRenameFolderCancelButton(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 10);
											 aa="Cancel Button";
										}else {
											 ele= fp.getParentRenameFolderCrossIcon(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 10);
											 aa="Cross Icon";
										}
										if(ele!=null) {
											if(click(driver,ele ,"rename parent folder "+aa+"", action.BOOLEAN)) {
												appLog.info("clicked on "+aa+"");
												if(fp.getCreatedFolderId(commonFolderName[0], PageName.ManageFolderPopUp, 20)!=null) {
													appLog.info(commonFolderName[0]+" is available in manage folder after click on "+aa+"");
												}else {
													appLog.error("Created folder is not available in manage folder: "+commonFolderName[0]);
													sa.assertTrue(false, "Created folder is not available in manage folder: "+commonFolderName[0]);
												}
											}else {
												appLog.error("Not able to click on common rename parent folder "+aa+"so cannot check folder name in manage folder after click on "+aa+"");
												sa.assertTrue(false, "Not able to click on common rename parent folder "+aa+" so cannot check folder name in manage folder after click on "+aa+"");
											}
										}else {
											appLog.error(aa+" is not visible so cannot click on "+aa+" so cannot check folder name in manage folder after click on "+aa+"");
											sa.assertTrue(false, aa+" is not visible so cannot click on "+aa+" so cannot check folder name in manage folder after click on "+aa+"");
										}
										
									}else {
										appLog.error("Not able to blank "+commonFolderName[0]+" rename folder text box so cannot check folder name in manage folder after click on cancel button");
										sa.assertTrue(false, "Not able to blank "+commonFolderName[0]+" rename folder text box so cannot check folder name in manage folder after click on cancel button");
									}	
									
								}else {
									appLog.error("Not able to click on Rename folder Parent folder "+commonFolderName[0]+" so cannot check error message on "+commonFolderName[0]+" Parent folder");
									sa.assertTrue(false, "Not able to click on Rename folder Parent folder Rename Icon  "+commonFolderName[0]+" so cannot check error message on "+commonFolderName[0]+" Parent folder");
								}
							}else {
								appLog.error(commonFolderName[0]+" folder is available in manage folder so cannot check error messgaes on Parent Rename Common folder");
								sa.assertTrue(false, stdFolderName[0]+" folder is available in manage folder so cannot check error messgaes on Parent Rename Common folder");
							}
						}
						String[] folders= {commonFolderName[0],SHRDFolderName[0],InternalFolderName[0],stdFolderName[0]};
						String [] Names= {commonFolderName[0].split(" ")[0],SHRDFolderName[0].split(" ")[0],InternalFolderName[0].split(" ")[0],stdFolderName[0].split(" ")[0]};
						for(int i=0; i<folders.length; i++) {
							id=null;
							id=fp.getCreatedFolderId(folders[i], PageName.ManageFolderPopUp, 20);
							System.err.println("id>>>>>>"+id);
							if(id!=null) {
								if(fp.clickOnRenameFolderButton(id)) {
									if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,20),"Updated"+Names[i],folders[i]+" parent rename folder text box", action.BOOLEAN)) {
										if(click(driver,fp.getParentRenameFolderSaveButton(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20) ,"rename parent folder save button", action.BOOLEAN)) {
											appLog.info("clicked on parent rename folder save button");
											ThreadSleep(2000);
											if(fp.getCreatedFolderId("Updated"+folders[i], PageName.ManageFolderPopUp, 20)!=null) {
												appLog.info("Updated"+folders[i]+" is available in manage folder");
												if(i==0) {
													ExcelUtils.writeData("Updated"+Names[i]+" (Common)","FilePath", excelLabel.TestCases_Name, "M5tc010_verifyEditUpdatedFolder",excelLabel.CommonPath);
												}if(i==1) {
													ExcelUtils.writeData("Updated"+Names[i]+" (Shared)","FilePath", excelLabel.TestCases_Name, "M5tc010_verifyEditUpdatedFolder",excelLabel.SharedPath);
												}if(i==2) {
													ExcelUtils.writeData("Updated"+Names[i]+" (Internal)","FilePath", excelLabel.TestCases_Name, "M5tc010_verifyEditUpdatedFolder",excelLabel.InternalPath);
												}if(i==3) {
													ExcelUtils.writeData("Updated"+Names[i],"FilePath", excelLabel.TestCases_Name, "M5tc010_verifyEditUpdatedFolder",excelLabel.StandardPath);
												}
											}else {
												appLog.error("Updated"+folders[i]+" is not available in manage folder");
												sa.assertTrue(false, "Updated"+folders[i]+" is not available in manage folder");
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
								appLog.error(folders[i]+" folder is not available in manage folder so cannot Update folder Name");
								sa.assertTrue(false, folders[i]+" folder is not available in manage folder so cannot Update folder Name");
							}
							
						}
						String updateCommonFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc010_verifyEditUpdatedFolder",excelLabel.CommonPath);
						String updateSharedFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc010_verifyEditUpdatedFolder",excelLabel.SharedPath);
						String updateInternalFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc010_verifyEditUpdatedFolder",excelLabel.InternalPath);
						String updateSTDFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc010_verifyEditUpdatedFolder",excelLabel.StandardPath);
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
								id=fp.getCreatedFolderId(lst.get(i), PageName.ManageFolderPopUp, 20);
								System.err.println("id>>>>>>"+id);
								if(id!=null) {
									if(fp.clickOnRenameFolderButton(id)) {
										if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp,20),"Updated"+lst.get(i).split("/")[1],lst.get(i).split("/")[0]+" parent rename folder text box", action.BOOLEAN)) {
											if(click(driver,fp.getParentRenameFolderSaveButton(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20) ,lst.get(i).split("/")[0]+"rename sub folder save button", action.BOOLEAN)) {
												appLog.info("clicked on parent rename folder save button");
												ThreadSleep(2000);
												if(fp.getCreatedFolderId(lst.get(i).split("/")[0]+"/Updated"+lst.get(i).split("/")[1], PageName.ManageFolderPopUp, 20)!=null) {
													appLog.info(lst.get(i).split("/")[0]+"/Updated"+lst.get(i).split("/")[1]+" is available in manage folder");
													System.err.println("List >>>>>"+lst.get(i).trim()+" and size"+lst.size());
													System.err.println("Updated Common >>"+updateCommonFolder);
													System.err.println("Updated Internal >>"+updateInternalFolder);
													System.err.println("Updated shared >>"+updateSharedFolder);
													System.err.println("Updated STD >>"+updateSTDFolder);
													
													if(!updateCommonFolder.isEmpty() && lst.get(i).trim().contains(updateCommonFolder)) {
														ExcelUtils.writeData(lst.get(i).split("/")[0]+"/Updated"+lst.get(i).split("/")[1],"FilePath", excelLabel.TestCases_Name, "M5tc010_verifyEditUpdatedChildFolder",excelLabel.CommonPath);
													}else if(!updateInternalFolder.isEmpty() && lst.get(i).trim().contains(updateInternalFolder)) {
														ExcelUtils.writeData(lst.get(i).split("/")[0]+"/Updated"+lst.get(i).split("/")[1],"FilePath", excelLabel.TestCases_Name, "M5tc010_verifyEditUpdatedChildFolder",excelLabel.InternalPath);
													}else if(!updateSharedFolder.isEmpty() && lst.get(i).trim().contains(updateSharedFolder)) {
														ExcelUtils.writeData(lst.get(i).split("/")[0]+"/Updated"+lst.get(i).split("/")[1],"FilePath", excelLabel.TestCases_Name, "M5tc010_verifyEditUpdatedChildFolder",excelLabel.SharedPath);
													}else if(!updateSTDFolder.isEmpty() && lst.get(i).trim().contains(updateSTDFolder)) {
														ExcelUtils.writeData(lst.get(i).split("/")[0]+"/Updated"+lst.get(i).split("/")[1],"FilePath", excelLabel.TestCases_Name, "M5tc010_verifyEditUpdatedChildFolder",excelLabel.StandardPath);
													}
												}else {
													appLog.error(lst.get(i).split("/")[0]+"/Updated"+lst.get(i).split("/")[1]+" is not available in manage folder");
													sa.assertTrue(false, lst.get(i).split("/")[0]+"/Updated"+lst.get(i).split("/")[1]+" is not available in manage folder");
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
									appLog.error(lst.get(i)+" folder is not available in manage folder so cannot Update folder Name");
									sa.assertTrue(false, lst.get(i)+" folder is not available in manage folder so cannot Update folder Name");
								}
								
							}
						}else {
							appLog.error("Updated Folder Names is not available in the filepath excel sheet so cannot update Child folder Names");
							sa.assertTrue(false, "Updated Folder Names is not available in the filepath excel sheet so cannot update Child folder Names");
						}
				}else {
					appLog.error("Not able to click on Manage folder Icon so cannot check error message in fundraising workspace");
					sa.assertTrue(false, "Not able to click on Manage folder Icon so cannot check error message in fundraising workspace");
				}
		}else {
			appLog.error(M5F2+" fund is not available so cannot continue with the tc.");
			sa.assertTrue(false,M5F2+" fund is not available so cannot continue with the tc.");
		}
		
		}else {
			appLog.error("Not able to click on Fund Tab so cannot continue with the tc");
			sa.assertTrue(false, "Not able to click on Fund Tab so cannot continue with the tc");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	@Test
	public void M5tc010_2_verifyUpdateFolderNameOnInstitutionAndContactPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ins = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer contact= new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		String CommonPath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc010_verifyEditUpdatedChildFolder",excelLabel.CommonPath);
		String InternalPath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc010_verifyEditUpdatedChildFolder",excelLabel.InternalPath);
		String SHRDPath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc010_verifyEditUpdatedChildFolder",excelLabel.SharedPath);
		String STDPath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc010_verifyEditUpdatedChildFolder",excelLabel.StandardPath);
		if(fp.clickOnTab(TabName.InstituitonsTab)) {
			if(ins.clickOnCreatedInstitution(M5I1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.InstitutionsPage, 30));
				if(!CommonPath.isEmpty() && !CommonPath.equalsIgnoreCase("CommonPath")) {
					if(fp.verifyFolderPathdummy(CommonPath, null, null, M5F2, PageName.InstitutionsPage,Workspace.FundraisingWorkspace, 60)) {
						appLog.info(CommonPath+" is verified on institution page: "+M5I1);
					}else {
						appLog.error(CommonPath+" is not verified on institution page: "+M5I1);
						sa.assertTrue(false, CommonPath+" is not verified on institution page: "+M5I1);
					}
				}else {
					appLog.error("CommonPath column is empty in file path excel sheet in test case M5tc010_verifyEditUpdatedChildFolder so cannot check updated folder structure for common folder on Institution Page");
					sa.assertTrue(false, "CommonPath column is empty in file path excel sheet in test case M5tc010_verifyEditUpdatedChildFolder so cannot check updated folder structure for common folder on Institution Page");
				}
				if(!InternalPath.isEmpty() && !InternalPath.equalsIgnoreCase("InternalPath")) {
					if(fp.verifyFolderPathdummy(InternalPath, null, null, M5F2, PageName.InstitutionsPage,Workspace.FundraisingWorkspace, 60)) {
						appLog.info(InternalPath+" is verified on institution page: "+M5I1);
					}else {
						appLog.error(InternalPath+" is not verified on institution page: "+M5I1);
						sa.assertTrue(false, InternalPath+" is not verified on institution page: "+M5I1);
					}
				}else {
					appLog.error("InternalPath column is empty in file path excel sheet in test case M5tc010_verifyEditUpdatedChildFolder so cannot check updated folder structure for Internal folder on Institution Page");
					sa.assertTrue(false, "InternalPath column is empty in file path excel sheet in test case M5tc010_verifyEditUpdatedChildFolder so cannot check updated folder structure for Internal folder on Institution Page");
				}
				if(!SHRDPath.isEmpty() && !SHRDPath.equalsIgnoreCase("SharedPath")) {
					if(fp.verifyFolderPathdummy(SHRDPath, null, null, M5F2, PageName.InstitutionsPage,Workspace.FundraisingWorkspace, 60)) {
						appLog.info(SHRDPath+" is verified on institution page: "+M5I1);
					}else {
						appLog.error(SHRDPath+" is not verified on institution page: "+M5I1);
						sa.assertTrue(false, SHRDPath+" is not verified on institution page: "+M5I1);
					}
				}else {
					appLog.error("SharedPath column is empty in file path excel sheet in test case M5tc010_verifyEditUpdatedChildFolder so cannot check updated folder structure for Shared folder on Institution Page");
					sa.assertTrue(false, "Shared column is empty in file path excel sheet in test case M5tc010_verifyEditUpdatedChildFolder so cannot check updated folder structure for Shared folder on Institution Page");
				}
				if(!STDPath.isEmpty() && !STDPath.equalsIgnoreCase("StandardPath")) {
					if(fp.verifyFolderPathdummy(STDPath, null, null, M5F2, PageName.InstitutionsPage,Workspace.FundraisingWorkspace, 60)) {
						appLog.info(STDPath+" is verified on institution page: "+M5I1);
					}else {
						appLog.error(STDPath+" is not verified on institution page: "+M5I1);
						sa.assertTrue(false, STDPath+" is not verified on institution page: "+M5I1);
					}
				}else {
					appLog.error("StandardPath column is empty in file path excel sheet in test case M5tc010_verifyEditUpdatedChildFolder so cannot check updated folder structure for Standard folder on Institution Page");
					sa.assertTrue(false, "StandardPath column is empty in file path excel sheet in test case M5tc010_verifyEditUpdatedChildFolder so cannot check updated folder structure for Standard folder on Institution Page");
				}
			}else {
				appLog.error("Not able to click on "+M5I1+" institution Name so cannot check Updated Folder Names");
				sa.assertTrue(false, "Not able to click on "+M5I1+" institution Name so cannot check Updated Folder Names");
			}
		}else {
			appLog.error("Not able to click on institution tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on institution tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		if(fp.clickOnTab(TabName.ContactTab)) {
			if(contact.clickOnCreatedContact(M5CFN1, M5CLN1, null)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.ContactsPage, 30));
				if(!CommonPath.isEmpty() && !CommonPath.equalsIgnoreCase("CommonPath")) {
					if(fp.verifyFolderPathdummy(CommonPath, null, null, M5F2, PageName.ContactsPage,Workspace.FundraisingWorkspace, 60)) {
						appLog.info(CommonPath+" is verified on Contact page: "+M5I1);
					}else {
						appLog.error(CommonPath+" is not verified on Contact page: "+M5I1);
						sa.assertTrue(false, CommonPath+" is not verified on Contact page: "+M5I1);
					}
				}else {
					appLog.error("CommonPath column is empty in file path excel sheet in test case M5tc010_verifyEditUpdatedChildFolder so cannot check updated folder structure for common folder on Contact Page");
					sa.assertTrue(false, "CommonPath column is empty in file path excel sheet in test case M5tc010_verifyEditUpdatedChildFolder so cannot check updated folder structure for common folder on Contact Page");
				}
				if(!SHRDPath.isEmpty() && !SHRDPath.equalsIgnoreCase("SharedPath")) {
					if(fp.verifyFolderPathdummy(SHRDPath, null, null, M5F2, PageName.ContactsPage,Workspace.FundraisingWorkspace, 60)) {
						appLog.info(SHRDPath+" is verified on Contact page: "+M5I1);
					}else {
						appLog.error(SHRDPath+" is not verified on Contact page: "+M5I1);
						sa.assertTrue(false, SHRDPath+" is not verified on Contact page: "+M5I1);
					}
				}else {
					appLog.error("SharedPath column is empty in file path excel sheet in test case M5tc010_verifyEditUpdatedChildFolder so cannot check updated folder structure for Shared folder on Contact Page");
					sa.assertTrue(false, "Shared column is empty in file path excel sheet in test case M5tc010_verifyEditUpdatedChildFolder so cannot check updated folder structure for Shared folder on Contact Page");
				}
				if(!STDPath.isEmpty() && !STDPath.equalsIgnoreCase("StandardPath")) {
					if(fp.verifyFolderPathdummy(STDPath, M5I1, null, M5F2, PageName.ContactsPage,Workspace.FundraisingWorkspace, 60)) {
						appLog.info(STDPath+" is verified on Contact page: "+M5I1);
					}else {
						appLog.error(STDPath+" is not verified on Contact page: "+M5I1);
						sa.assertTrue(false, STDPath+" is not verified on Contact page: "+M5I1);
					}
				}else {
					appLog.error("StandardPath column is empty in file path excel sheet in test case M5tc010_verifyEditUpdatedChildFolder so cannot check updated folder structure for Standard folder on Contact Page");
					sa.assertTrue(false, "StandardPath column is empty in file path excel sheet in test case M5tc010_verifyEditUpdatedChildFolder so cannot check updated folder structure for Standard folder on Contact Page");
				}
			}else {
				appLog.error("Not able to click on "+M5I1+" Contact Name so cannot check Updated Folder Names");
				sa.assertTrue(false, "Not able to click on "+M5I1+" Contact Name so cannot check Updated Folder Names");
			}
		}else {
			appLog.error("Not able to click on Contact tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on Contact tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Test
	public void M5tc010_3_verifyUpdatedFolderNameAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.investorLogin(M5C1Email,adminPassword);
		if(ivp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			String CommonPath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc010_verifyEditUpdatedChildFolder",excelLabel.CommonPath);
			String SHRDPath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc010_verifyEditUpdatedChildFolder",excelLabel.SharedPath);
			String STDPath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc010_verifyEditUpdatedChildFolder",excelLabel.StandardPath);
			if(!CommonPath.isEmpty() && !CommonPath.equalsIgnoreCase("CommonPath")) {
				if(fp.verifyFolderPathdummy(CommonPath, null, null, null, PageName.PotentialInvestmentPage,Workspace.FundraisingWorkspace, 60)) {
					appLog.info(CommonPath+" is verified on Investor Side: "+M5CFN1+" "+M5CLN2);
				}else {
					appLog.error(CommonPath+" is not verified on Investor Side: "+M5CFN1+" "+M5CLN2);
					sa.assertTrue(false, CommonPath+" is verified on Investor Side: "+M5CFN1+" "+M5CLN2);
				}
			}else {
				appLog.error("CommonPath column is empty in file path excel sheet in test case M5tc010_verifyEditUpdatedChildFolder so cannot check updated folder structure for common folder on Investor side");
				sa.assertTrue(false, "CommonPath column is empty in file path excel sheet in test case M5tc010_verifyEditUpdatedChildFolder so cannot check updated folder structure for common folder on Investor side");
			}
			if(!SHRDPath.isEmpty() && !SHRDPath.equalsIgnoreCase("SharedPath")) {
				if(fp.verifyFolderPathdummy(SHRDPath, null, null, null, PageName.PotentialInvestmentPage,Workspace.FundraisingWorkspace, 60)) {
					appLog.info(SHRDPath+" is verified on Investor Side: "+M5CFN1+" "+M5CLN2);
				}else {
					appLog.error(SHRDPath+" is not verified on Investor Side: "+M5CFN1+" "+M5CLN2);
					sa.assertTrue(false, SHRDPath+" is verified on Investor Side: "+M5CFN1+" "+M5CLN2);
				}
			}else {
				appLog.error("SharedPath column is empty in file path excel sheet in test case M5tc010_verifyEditUpdatedChildFolder so cannot check updated folder structure for Shared folder on Investor side");
				sa.assertTrue(false, "SharedPath column is empty in file path excel sheet in test case M5tc010_verifyEditUpdatedChildFolder so cannot check updated folder structure for Shared folder on Investor side");
			}
			if(!STDPath.isEmpty() && !STDPath.equalsIgnoreCase("StandardPath")) {
				if(fp.verifyFolderPathdummy(STDPath, M5I1, null, null, PageName.PotentialInvestmentPage,Workspace.FundraisingWorkspace, 60)) {
					appLog.info(SHRDPath+" is verified on Investor Side: "+M5CFN1+" "+M5CLN2);
				}else {
					appLog.error(SHRDPath+" is not verified on Investor Side: "+M5CFN1+" "+M5CLN2);
					sa.assertTrue(false, SHRDPath+" is verified on Investor Side: "+M5CFN1+" "+M5CLN2);
				}
			}else {
				appLog.error("StandardPath column is empty in file path excel sheet in test case M5tc010_verifyEditUpdatedChildFolder so cannot check updated folder structure for Standard folder on Investor side");
				sa.assertTrue(false, "StandardPath column is empty in file path excel sheet in test case M5tc010_verifyEditUpdatedChildFolder so cannot check updated folder structure for Standard folder on Investor side");
			}
		}else {
			appLog.error("Not able to click on Potential Investment tab so cannot check Updated Folder Name");
			sa.assertTrue(false, "Not able to click on Potential Investment tab so cannot check Updated Folder Name");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M5tc011_1_verifyDeleteFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String commonFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc010_verifyEditUpdatedChildFolder",excelLabel.CommonPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M5F2)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view.");
				if(click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 30), "Manage folder icon", action.BOOLEAN)){
					if(!commonFolderName.isEmpty() && !commonFolderName.equalsIgnoreCase("Commonpath")) {
						String[] SplitedcommonFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc010_verifyEditUpdatedChildFolder",excelLabel.CommonPath).split("/");
						for(int i=0; i<2; i++) {
							String id=null;
							id=fp.getCreatedFolderId(commonFolderName, PageName.ManageFolderPopUp, 20);
							System.err.println("id>>>>>>"+id);
							if(id!=null) {
								if(fp.clickOnDeleteFolderButton(id)) {
									if(i==0) {
									if(fp.getFolderDeleteErrorMsg1(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20)!=null) {
										if(fp.getFolderDeleteErrorMsg1(Workspace.FundraisingWorkspace,PageName.ManageFolderPopUp, 10).getText().trim().contains(FundsPageErrorMessage.deleteFolderErrorMsg1)) {
											appLog.info(FundsPageErrorMessage.deleteFolderErrorMsg1+" Folder delete message is verified. ");
										}else {
											appLog.error(FundsPageErrorMessage.deleteFolderErrorMsg1+" folder delete message is not verified");
											sa.assertTrue(false, FundsPageErrorMessage.deleteFolderErrorMsg1+" folder delete message is not verified");
										}
									}else {
										appLog.error("Folder delete error message is not visible");
										sa.assertTrue(false, "Folder delete error message is not visible");
									}
									if(fp.getFolderDeleteErrorMsg2(Workspace.FundraisingWorkspace, 20)!=null) {
										if(fp.getFolderDeleteErrorMsg2(Workspace.FundraisingWorkspace, 10).getText().trim().contains(FundsPageErrorMessage.deleteFolderErrorMsg2)) {
											appLog.info(FundsPageErrorMessage.deleteFolderErrorMsg2+" Folder delete message is verified. ");
										}else {
											appLog.error(FundsPageErrorMessage.deleteFolderErrorMsg2+" folder delete message is not verified");
											sa.assertTrue(false, FundsPageErrorMessage.deleteFolderErrorMsg2+" folder delete message is not verified");
										}
									}else {
										appLog.error("Folder delete error message is not visible");
										sa.assertTrue(false, "Folder delete error message is not visible");
									}
									if(fp.getFolderDeleteYesBtn(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 20)!=null) {
										appLog.info("Yes button is displaying on folder delete pop up");
									}else {
										appLog.error("Yes button is displaying on folder delete pop up");
										sa.assertTrue(false, "Yes button is displaying on folder delete pop up");
									}
										if(click(driver, fp.getFolderDeleteNoBtn(Workspace.FundraisingWorkspace,PageName.ManageFolderPopUp, 10), "folder delete no Button", action.BOOLEAN)) {
											appLog.info("No button is displaying on folder delete pop up");
											appLog.info("clicked on folder delete No Button");
										}else {
											appLog.error("No button is not displaying on folder delete pop up so cannot click on No button");
											sa.assertTrue(false, "No button is not displaying on folder delete pop up so cannot click on No button");
										}
									}
									if(i==1) {
										if(click(driver, fp.getFolderDeleteCrossIcon(Workspace.FundraisingWorkspace,PageName.ManageFolderPopUp, 10), "folder delete cross icon", action.SCROLLANDBOOLEAN)) {
											appLog.info("Cross Icon is displaying on folder delete pop up");
											appLog.info("clicked on folder delete cross icon");
										}else {
											appLog.error("Cross Icon is not displaying on folder delete pop up so cannot click on cross icon");
											sa.assertTrue(false, "Cross Icon is displaying on folder delete pop up so cannot click on cross icon");
										}
									}
								}else {
									appLog.error("Not able to click on folder: "+SplitedcommonFolderName[1]+" delete icon so cannot check delete folder functionality");
									sa.assertTrue(false, "Not able to click on folder: "+SplitedcommonFolderName[1]+" delete icon so cannot check delete folder functionality");
								}
							}else {
								appLog.error(SplitedcommonFolderName[1]+" is not available in the manage folder structure so cannot click on folder "+SplitedcommonFolderName[1]+" delete icon");
								sa.assertTrue(false, SplitedcommonFolderName[1]+" is not available in the manage folder structure so cannot click on folder "+SplitedcommonFolderName[1]+" delete icon");
							}
						}
					}else {
						appLog.error("Update Commonpath is empty in excel sheet in test case M5tc010_verifyEditUpdatedChildFolder so cannot check delete folder functionality on Sub Common Folder");
						sa.assertTrue(false, "Update Commonpath is empty in excel sheet in test case M5tc010_verifyEditUpdatedChildFolder so cannot check delete folder functionality on Sub Common Folder");
					}
					
					String[] updateCommonFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath).split(",");
					String updateSharedFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath);
					String updateInternalFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.InternalPath);
					String updateSTDFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
					String[] listOfFolders= {updateCommonFolder[0],updateCommonFolder[1],updateInternalFolder,updateSharedFolder,updateSTDFolder};
					for(int i=0; i<listOfFolders.length; i++) {
						String id=null;
						id=fp.getCreatedFolderId(listOfFolders[i], PageName.ManageFolderPopUp, 20);
						System.err.println("id>>>>>>"+id);
						if(id!=null) {
							if(fp.clickOnDeleteFolderButton(id)) {
								if(click(driver, fp.getFolderDeleteYesBtn(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 10), "folder delete yes Button", action.BOOLEAN)) {
									appLog.info("clicked on folder delete yes Button");
									ThreadSleep(2000);
									if(fp.getCreatedFolderId(listOfFolders[i], PageName.ManageFolderPopUp, 10)!=null) {
										appLog.info(listOfFolders[i]+" is not deleted, folder is visible in manage foler pop up");
										sa.assertTrue(false, listOfFolders[i]+" is not deleted, folder is visible in manage foler pop up");
									}else {
										appLog.error(listOfFolders[i]+" is deleted successfully, it is not visible in manage foler pop up");
									}
								}else {
									appLog.error("Yes button is not displaying on folder delete pop up so cannot click on Yes button");
									sa.assertTrue(false, "yes button is not displaying on folder delete pop up so cannot click on yes button");
								}
							}else {
								appLog.error("Not able to click on folder: "+listOfFolders[i]+" delete icon so cannot check delete folder functionality");
								sa.assertTrue(false, "Not able to click on folder: "+listOfFolders[i]+" delete icon so cannot check delete folder functionality");
							}
						}else {
							appLog.error(listOfFolders[i]+" is not available in the manage folder structure so cannot click on folder "+listOfFolders[i]+" delete icon");
							sa.assertTrue(false, listOfFolders[i]+" is not available in the manage folder structure so cannot click on folder "+listOfFolders[i]+" delete icon");
						}
					}
				}else {
					appLog.error("Not able to click on Manage folder icon so cannot check delete folder functionality");
					sa.assertTrue(false, "Not able to click on Manage folder icon so cannot check delete folder functionality");
				}
			}else {
				appLog.error("Not able to click on Fund: "+M5F2+" so cannot check delete folder functionality");
				sa.assertTrue(false, "Not able to click on Fund: "+M5F2+" so cannot check delete folder functionality");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on fund tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Test
	public void M5tc011_2_verifyDeletedFolderNameOnInstitutionAndContactPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ins = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer contact= new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		String[] updateCommonFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc011_1_verifyDeleteFolder",excelLabel.CommonPath).split(",");
		String updateSharedFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc011_1_verifyDeleteFolder",excelLabel.SharedPath);
		String updateInternalFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc011_1_verifyDeleteFolder",excelLabel.InternalPath);
		String updateSTDFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc011_1_verifyDeleteFolder",excelLabel.StandardPath);
		if(fp.clickOnTab(TabName.InstituitonsTab)) {
			if(ins.clickOnCreatedInstitution(M5I1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.InstitutionsPage, 30));
					if(!fp.verifyFolderPathdummy(updateCommonFolder[0], null, null, M5F2, PageName.InstitutionsPage,Workspace.FundraisingWorkspace, 10)) {
						appLog.info(updateCommonFolder[0]+" is not available on institution page: "+M5I1);
					}else {
						appLog.error(updateCommonFolder[0]+" is available on institution page: "+M5I1);
						sa.assertTrue(false, updateCommonFolder[0]+" is available on institution page: "+M5I1);
					}
					if(!fp.verifyFolderPathdummy(updateCommonFolder[1], null, null, M5F2, PageName.InstitutionsPage,Workspace.FundraisingWorkspace,10)) {
						appLog.info(updateCommonFolder[1]+" is not available on institution page: "+M5I1);
					}else {
						appLog.error(updateCommonFolder[1]+" is available on institution page: "+M5I1);
						sa.assertTrue(false, updateCommonFolder[1]+" is available on institution page: "+M5I1);
					}
					if(!fp.verifyFolderPathdummy(updateSharedFolder, null, null, M5F2, PageName.InstitutionsPage,Workspace.FundraisingWorkspace,10)) {
						appLog.info(updateSharedFolder+" is not available on institution page: "+M5I1);
					}else {
						appLog.error(updateSharedFolder+" is available on institution page: "+M5I1);
						sa.assertTrue(false, updateSharedFolder+" is available on institution page: "+M5I1);
					}
					
					if(!fp.verifyFolderPathdummy(updateInternalFolder, null, null, M5F2, PageName.InstitutionsPage,Workspace.FundraisingWorkspace,10)) {
						appLog.info(updateInternalFolder+" is not available on institution page: "+M5I1);
					}else {
						appLog.error(updateInternalFolder+" is available on institution page: "+M5I1);
						sa.assertTrue(false, updateInternalFolder+" is available on institution page: "+M5I1);
					}
					if(!fp.verifyFolderPathdummy(updateSTDFolder, null, null, M5F2, PageName.InstitutionsPage,Workspace.FundraisingWorkspace,10)) {
						appLog.info(updateSTDFolder+" is not available on institution page: "+M5I1);
					}else {
						appLog.error(updateSTDFolder+" is available on institution page: "+M5I1);
						sa.assertTrue(false, updateSTDFolder+" is available on institution page: "+M5I1);
					}
			}else {
				appLog.error("Not able to click on "+M5I1+" institution Name so cannot check Updated Folder Names");
				sa.assertTrue(false, "Not able to click on "+M5I1+" institution Name so cannot check Updated Folder Names");
			}
		}else {
			appLog.error("Not able to click on institution tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on institution tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		if(fp.clickOnTab(TabName.ContactTab)) {
			if(contact.clickOnCreatedContact(M5CFN1, M5CLN1, null)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.ContactsPage, 30));
				if(!fp.verifyFolderPathdummy(updateCommonFolder[0], null, null, M5F2, PageName.ContactsPage,Workspace.FundraisingWorkspace, 10)) {
					appLog.info(updateCommonFolder[0]+" is not available on Contact page: "+M5I1);
				}else {
					appLog.error(updateCommonFolder[0]+" is available on Contact page: "+M5I1);
					sa.assertTrue(false, updateCommonFolder[0]+" is available on Contact page: "+M5I1);
				}
				if(!fp.verifyFolderPathdummy(updateCommonFolder[1], null, null, M5F2, PageName.ContactsPage,Workspace.FundraisingWorkspace,10)) {
					appLog.info(updateCommonFolder[1]+" is not available on Contact page: "+M5I1);
				}else {
					appLog.error(updateCommonFolder[1]+" is available on Contact page: "+M5I1);
					sa.assertTrue(false, updateCommonFolder[1]+" is available on Contact page: "+M5I1);
				}
				if(!fp.verifyFolderPathdummy(updateSharedFolder, null, null, M5F2, PageName.ContactsPage,Workspace.FundraisingWorkspace,10)) {
					appLog.info(updateSharedFolder+" is not available on Contact page: "+M5I1);
				}else {
					appLog.error(updateSharedFolder+" is available on Contact page: "+M5I1);
					sa.assertTrue(false, updateSharedFolder+" is available on Contact page: "+M5I1);
				}
				
				if(!fp.verifyFolderPathdummy(updateInternalFolder, null, null, M5F2, PageName.ContactsPage,Workspace.FundraisingWorkspace,10)) {
					appLog.info(updateInternalFolder+" is not available on Contact page: "+M5I1);
				}else {
					appLog.error(updateInternalFolder+" is available on Contact page: "+M5I1);
					sa.assertTrue(false, updateInternalFolder+" is available on Contact page: "+M5I1);
				}
				if(!fp.verifyFolderPathdummy(updateSTDFolder, null, null, M5F2, PageName.ContactsPage,Workspace.FundraisingWorkspace,10)) {
					appLog.info(updateSTDFolder+" is not available on Contact page: "+M5I1);
				}else {
					appLog.error(updateSTDFolder+" is available on Contact page: "+M5I1);
					sa.assertTrue(false, updateSTDFolder+" is available on Contact page: "+M5I1);
				}
			}else {
				appLog.error("Not able to click on "+M5I1+" Contact Name so cannot check Updated Folder Names");
				sa.assertTrue(false, "Not able to click on "+M5I1+" Contact Name so cannot check Updated Folder Names");
			}
		}else {
			appLog.error("Not able to click on Contact tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on Contact tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Test
	public void M5tc011_3_verifyDeletedFolderNameAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.investorLogin(M5C1Email,adminPassword);
		if(ivp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			String[] updateCommonFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc011_1_verifyDeleteFolder",excelLabel.CommonPath).split(",");
			String updateSharedFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc011_1_verifyDeleteFolder",excelLabel.SharedPath);
			String updateSTDFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc011_1_verifyDeleteFolder",excelLabel.StandardPath);
			if(!fp.verifyFolderPathdummy(updateCommonFolder[0], null, null, M5F2, PageName.PotentialInvestmentPage,Workspace.FundraisingWorkspace, 10)) {
				appLog.info(updateCommonFolder[0]+" is not available on PotentialInvestment page: "+M5I1);
			}else {
				appLog.error(updateCommonFolder[0]+" is available on PotentialInvestment page: "+M5I1);
				sa.assertTrue(false, updateCommonFolder[0]+" is available on PotentialInvestment page: "+M5I1);
			}
			if(!fp.verifyFolderPathdummy(updateCommonFolder[1], null, null, M5F2, PageName.PotentialInvestmentPage,Workspace.FundraisingWorkspace,10)) {
				appLog.info(updateCommonFolder[1]+" is not available on PotentialInvestment page: "+M5I1);
			}else {
				appLog.error(updateCommonFolder[1]+" is available on PotentialInvestment page: "+M5I1);
				sa.assertTrue(false, updateCommonFolder[1]+" is available on PotentialInvestment page: "+M5I1);
			}
			if(!fp.verifyFolderPathdummy(updateSharedFolder, null, null, M5F2, PageName.PotentialInvestmentPage,Workspace.FundraisingWorkspace,10)) {
				appLog.info(updateSharedFolder+" is not available on PotentialInvestment page: "+M5I1);
			}else {
				appLog.error(updateSharedFolder+" is available on PotentialInvestment page: "+M5I1);
				sa.assertTrue(false, updateSharedFolder+" is available on PotentialInvestment page: "+M5I1);
			}
			if(!fp.verifyFolderPathdummy(updateSTDFolder, null, M5I1, null, PageName.PotentialInvestmentPage,Workspace.FundraisingWorkspace,10)) {
				appLog.info(updateSTDFolder+" is not available on PotentialInvestment page: "+M5I1);
			}else {
				appLog.error(updateSTDFolder+" is available on PotentialInvestment page: "+M5I1);
				sa.assertTrue(false, updateSTDFolder+" is available on PotentialInvestment page: "+M5I1);
			}
		}else {
			appLog.error("Not able to click on Potential Investment tab so cannot check Updated Folder Name");
			sa.assertTrue(false, "Not able to click on Potential Investment tab so cannot check Updated Folder Name");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M5tc012_1_deleteInvitedFolderFromManageFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M5F2)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view.");
				if(click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 30), "Manage folder icon", action.BOOLEAN)){
					String updateSTDFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
						String id=null;
						id=fp.getCreatedFolderId(updateSTDFolder, PageName.ManageFolderPopUp, 20);
						System.err.println("id>>>>>>"+id);
						if(id!=null) {
							if(fp.clickOnDeleteFolderButton(id)) {
								if(click(driver, fp.getFolderDeleteYesBtn(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 10), "folder delete yes Button", action.BOOLEAN)) {
									appLog.info("clicked on folder delete yes Button");
									ThreadSleep(2000);
									if(fp.getCreatedFolderId(updateSTDFolder, PageName.ManageFolderPopUp, 10)!=null) {
										appLog.info(updateSTDFolder+" is not deleted, folder is visible in manage foler pop up");
										sa.assertTrue(false, updateSTDFolder+" is not deleted, folder is visible in manage foler pop up");
									}else {
										appLog.error(updateSTDFolder+" is deleted successfully, it is not visible in manage foler pop up");
									}
								}else {
									appLog.error("Yes button is not displaying on folder delete pop up so cannot click on Yes button");
									sa.assertTrue(false, "yes button is not displaying on folder delete pop up so cannot click on yes button");
								}
							}else {
								appLog.error("Not able to click on folder: "+updateSTDFolder+" delete icon so cannot check delete invited folder");
								sa.assertTrue(false, "Not able to click on folder: "+updateSTDFolder+" delete icon so cannot check delete invited folder");
							}
						}else {
							appLog.error(updateSTDFolder+" is not available in the manage folder structure so cannot click on folder "+updateSTDFolder+" delete icon");
							sa.assertTrue(false, updateSTDFolder+" is not available in the manage folder structure so cannot click on folder "+updateSTDFolder+" delete icon");
						}
				}else {
					appLog.error("Not able to click on Manage folder icon so cannot check delete invited folder");
					sa.assertTrue(false, "Not able to click on Manage folder icon so cannot check delete invited folder");
				}
			}else {
				appLog.error("Not able to click on Fund: "+M5F2+" so cannot check delete invited folder");
				sa.assertTrue(false, "Not able to click on Fund: "+M5F2+" so cannot check delete invited folder");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on fund tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Test
	public void M5tc012_2_verifyDeleteInvitedFolderNameOnInstitutionAndContactPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ins = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer contact= new ContactPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		String updateSTDFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc012_1_deleteInvitedFolderFromManageFolder",excelLabel.StandardPath);
		if(fp.clickOnTab(TabName.InstituitonsTab)) {
			if(ins.clickOnCreatedInstitution(M5I1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.InstitutionsPage, 30));
					if(!fp.verifyFolderPathdummy(updateSTDFolder, null, null, M5F2, PageName.InstitutionsPage,Workspace.FundraisingWorkspace,5)) {
						appLog.info(updateSTDFolder+" is not available on institution page: "+M5I1);
					}else {
						appLog.error(updateSTDFolder+" is available on institution page: "+M5I1);
						sa.assertTrue(false, updateSTDFolder+" is available on institution page: "+M5I1);
					}
			}else {
				appLog.error("Not able to click on "+M5I1+" institution Name so cannot check Updated Folder Names");
				sa.assertTrue(false, "Not able to click on "+M5I1+" institution Name so cannot check Updated Folder Names");
			}
		}else {
			appLog.error("Not able to click on institution tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on institution tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		if(fp.clickOnTab(TabName.ContactTab)) {
			if(contact.clickOnCreatedContact(M5CFN1, M5CLN1, null)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.ContactsPage, 30));
				if(!fp.verifyFolderPathdummy(updateSTDFolder, null, null, M5F2, PageName.ContactsPage,Workspace.FundraisingWorkspace,5)) {
					appLog.info(updateSTDFolder+" is not available on Contact page: "+M5I1);
				}else {
					appLog.error(updateSTDFolder+" is available on Contact page: "+M5I1);
					sa.assertTrue(false, updateSTDFolder+" is available on Contact page: "+M5I1);
				}
			}else {
				appLog.error("Not able to click on "+M5I1+" Contact Name so cannot check Updated Folder Names");
				sa.assertTrue(false, "Not able to click on "+M5I1+" Contact Name so cannot check Updated Folder Names");
			}
		}else {
			appLog.error("Not able to click on Contact tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on Contact tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Test
	public void M5tc012_3_verifyDeleteInvitedFolderNameAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.investorLogin(M5C1Email,adminPassword);
		if(ivp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			String updateSTDFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc012_1_deleteInvitedFolderFromManageFolder",excelLabel.StandardPath);
			if(!fp.verifyFolderPathdummy(updateSTDFolder, null, M5I1, null, PageName.PotentialInvestmentPage,Workspace.FundraisingWorkspace,10)) {
				appLog.info(updateSTDFolder+" is not available on PotentialInvestment page: "+M5I1);
			}else {
				appLog.error(updateSTDFolder+" is available on PotentialInvestment page: "+M5I1);
				sa.assertTrue(false, updateSTDFolder+" is available on PotentialInvestment page: "+M5I1);
			}
		}else {
			appLog.error("Not able to click on Potential Investment tab so cannot check Updated Folder Name");
			sa.assertTrue(false, "Not able to click on Potential Investment tab so cannot check Updated Folder Name");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M5tc013_1_VerifyAddAFolderPopUpInvestorWorkspace(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InstitutionPageBusinessLayer instiPage = new InstitutionPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer con = new ContactPageBusinessLayer(driver);
		CommitmentPageBusinessLayer com = new CommitmentPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M5F2)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Fundraising workspace view.");
				if(click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 30), "Manage folder icon", action.BOOLEAN)){
					if(click(driver, fp.getAllFolderAddIcon(Workspace.InvestorWorkspace, 30), "Add folder button", action.BOOLEAN)){
						String text = trim(getText(driver, fp.getAddFolderHeader(Workspace.InvestorWorkspace, 30),"", action.BOOLEAN));
						if(text.equalsIgnoreCase("Add a Folder")){
							appLog.info("Add a folder header is verified.");
						} else {
							appLog.error("Add a folder pop up header is not verifed.Expected: Add a Folder"+"\tActual: "+text);
							sa.assertTrue(false,"Add a folder pop up header is not verifed.Expected: Add a Folder"+"\tActual: "+text);
						}
						if(isSelected(driver, fp.getFolderTypeRadioButton(FolderType.Standard, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30), "Add a Folder Standard folder type radio button")){
							appLog.info("Standard folder radio button is present and is by default selected.");
						} else {
							appLog.error("Standard folder radio button is not by defualt selected.");
							sa.assertTrue(false,"Standard folder radio button is not by defualt selected.");
						}
						
						if(fp.getFolderTypeRadioButton(FolderType.Common, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30)!=null){
							appLog.info("Common folder radio button is verified.");
						} else {
							appLog.error("Common folder radio button is not present.");
							sa.assertTrue(false,"Common folder radio button is not present.");
						}
						
						if(fp.getFolderTypeRadioButton(FolderType.Shared, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30)!=null){
							appLog.info("Shared folder radio button is verified.");
						} else {
							appLog.error("Shared folder radio button is not present.");
							sa.assertTrue(false,"Shared folder radio button is not present.");
						}
						
						if(fp.getFolderTypeRadioButton(FolderType.Internal, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30)!=null){
							appLog.info("Internal folder radio button is verified.");
						} else {
							appLog.error("Internal folder radio button is not present.");
							sa.assertTrue(false,"Internal folder radio button is not present.");
						}
						
						if(fp.getParentFolderSaveButton(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30)!=null){
							appLog.info("Save button is verified.");
						} else {
							appLog.error("Save button on add a folder pop up is not verified.");
							sa.assertTrue(false,"Save button on add a folder pop up is not verified.");
						}
						
						if(fp.getAddFolderParentFolderCancelButton(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30)!=null){
							appLog.info("Add a folder pop up cancel button is verified.");
						} else {
							appLog.error("Add a folder pop up cancel button si not verified.");
							sa.assertTrue(false,"Add a folder pop up cancel button si not verified.");
						}
					
						if(mouseOverOperation(driver, fp.getAddFolderInformationIcon(Workspace.InvestorWorkspace, 30))){
							text = trim(getText(driver, fp.getAddFolderInfoIconMessage(30), "", action.BOOLEAN));
							System.err.println(text);
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
						boolean flag = true;
						if(sendKeys(driver, fp.getParentFolderNameTextBox(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30), "Standard Folder New", "Parent folder name text box", action.BOOLEAN)){
							if(click(driver, fp.getFolderTypeRadioButton(FolderType.Common, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30), "Folder type radio button", action.BOOLEAN)){
								appLog.info("Common folder radio button successfully selected.");
							} else {
								appLog.error("Common folder radio button cannot be clicked, So will continue with the current selection.");
								sa.assertTrue(false,"Common folder radio button cannot be clicked.");
							}
							if(click(driver, fp.getAddFolderCrossIcon(Workspace.InvestorWorkspace, 30), "Cross icon", action.BOOLEAN)){
								if(FindElement(driver, "//label[contains(text(),'Standard Folder New')]", "Folder in view", action.BOOLEAN, 3)!=null){
									appLog.error("New folder is being created after clicking on cross icon.");
									sa.assertTrue(false,"New folder is being created after clicking on cross icon.");
								} else {
									appLog.info("Folder structure is same as before and cross icon is verified.");
								}
							} else {
								appLog.error("Cross icon cannot be clicked, So cannot check cross icon functionality.");
								sa.assertTrue(false,"Cross icon cannot be clicked, So cannot check cross icon functionality.");
								flag = false;
							}
						} else {
							appLog.error("Cannot enter text to folder name text box, So cannot cross icon functionality.");
							sa.assertTrue(false,"Cannot enter text to folder name text box, So cannot cross icon functionality.");
						}
						
						if(flag){
							if(click(driver, fp.getAllFolderAddIcon(Workspace.InvestorWorkspace, 30), "Add folder button", action.BOOLEAN)){
								
							} else {
								appLog.error("Add folder icon cannot be clicked, So cannot check cancel button.");
								sa.assertTrue(false,"Add folder icon cannot be clicked, So cannot check cancel button.");
							}
						}
						if(sendKeys(driver, fp.getParentFolderNameTextBox(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30), "Standard Folder New", "Parent folder name text box", action.BOOLEAN)){
							if(click(driver, fp.getFolderTypeRadioButton(FolderType.Common, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30), "Folder type radio button", action.BOOLEAN)){
								appLog.info("Common folder radio button successfully selected.");
							} else {
								appLog.error("Common folder radio button cannot be clicked, So will continue with the current selection.");
								sa.assertTrue(false,"Common folder radio button cannot be clicked.");
							}
							if(click(driver, fp.getAddFolderParentFolderCancelButton(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30), "Cancel", action.BOOLEAN)){
								flag = true;
								if(FindElement(driver, "//label[contains(text(),'Standard Folder New')]", "Folder in view", action.BOOLEAN, 3)!=null){
									appLog.error("New folder is being created after clicking on Cancle button.");
									sa.assertTrue(false,"New folder is being created after clicking on Cancel button.");
								} else {
									appLog.info("Folder structure is same as before and Cancel is verified.");
								}
							} else {
								appLog.error("Cancel button cannot be clicked, So cannot check Cancel button functionality.");
								sa.assertTrue(false,"Cross icon cannot be clicked, So cannot check cancel button functionality.");
								flag = false;
							}
						} else {
							appLog.error("Cannot enter text to folder name text box, So cannot check cancel button functionality.");
							sa.assertTrue(false,"Cannot enter text to folder name text box, So cannot Check cancel button functionality.");
						}
						if(flag){
							if(click(driver, fp.getAllFolderAddIcon(Workspace.InvestorWorkspace, 30), "Add folder button", action.BOOLEAN)){
								
							} else {
								appLog.error("Add folder icon cannot be clicked, So cannot check cancel button.");
								sa.assertTrue(false,"Add folder icon cannot be clicked, So cannot check cancel button.");
							}
						}
						if(sendKeys(driver, fp.getParentFolderNameTextBox(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30), "Standard Folder New", "Parent folder name text box", action.BOOLEAN)){
							if(click(driver, fp.getFolderTypeRadioButton(FolderType.Standard, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30), "Folder type radio button", action.BOOLEAN)){
								appLog.info("Standard folder radio button successfully selected.");
							} else {
								appLog.error("Standard folder radio button cannot be clicked, So will continue with the current selection.");
								sa.assertTrue(false,"Standard folder radio button cannot be clicked.");
							}
							if(click(driver, fp.getParentFolderSaveButton(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30), "Save button", action.BOOLEAN)){
								flag = true;
								if(FindElement(driver, "//label[contains(text(),'Standard Folder New')]", "Folder in view", action.BOOLEAN, 30)!=null){
									appLog.info("Folder is successfully created and is verified.");
								} else {
									appLog.error("New folder is not being created after clicking on Save button.");
									sa.assertTrue(false,"New folder is not being created after clicking on Save button.");
								}
							} else {
								appLog.error("Save button cannot be clicked, So cannot check Cancel button functionality.");
								sa.assertTrue(false,"Save button cannot be clicked, So cannot check cancel button functionality.");
								flag = false;
							}
						} else {
							appLog.error("Cannot enter text to folder name text box, So cannot check cancel button functionality.");
							sa.assertTrue(false,"Cannot enter text to folder name text box, So cannot Check cancel button functionality.");
						}
						if(flag){
							if(click(driver, fp.getAllFolderAddIcon(Workspace.InvestorWorkspace, 30), "Add folder button", action.BOOLEAN)){
								
							} else {
								appLog.error("Add folder icon cannot be clicked, So cannot check cancel button.");
								sa.assertTrue(false,"Add folder icon cannot be clicked, So cannot check cancel button.");
							}
						}
						
						if(sendKeys(driver, fp.getParentFolderNameTextBox(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30), "Shared Folder New", "Parent folder name text box", action.BOOLEAN)){
							if(click(driver, fp.getFolderTypeRadioButton(FolderType.Shared, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30), "Folder type radio button", action.BOOLEAN)){
								appLog.info("Shared folder radio button successfully selected.");
							} else {
								appLog.error("Shared folder radio button cannot be clicked, So will continue with the current selection.");
								sa.assertTrue(false,"Shared folder radio button cannot be clicked.");
							}
							if(click(driver, fp.getParentFolderSaveButton(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30), "Save button", action.BOOLEAN)){
								flag = true;
								if(FindElement(driver, "//label[contains(text(),'Shared Folder New')]", "Folder in view", action.BOOLEAN, 30)!=null){
									appLog.info("Folder is successfully created and is verified.");
								} else {
									appLog.error("New folder is not being created after clicking on Save button.");
									sa.assertTrue(false,"New folder is not being created after clicking on Save button.");
								}
							} else {
								appLog.error("Save button cannot be clicked, So cannot check Cancel button functionality.");
								sa.assertTrue(false,"Save button cannot be clicked, So cannot check cancel button functionality.");
								flag = false;
							}
						} else {
							appLog.error("Cannot enter text to folder name text box, So cannot check cancel button functionality.");
							sa.assertTrue(false,"Cannot enter text to folder name text box, So cannot Check cancel button functionality.");
						}
						
						if(click(driver, fp.getManageFolderCloseButton(Workspace.InvestorWorkspace, 30), "Mange folder close button", action.BOOLEAN));
					} else {
						appLog.error("Add folder button cannot be clicked, So cannot continue with the tc.");
						sa.assertTrue(false,"Add folder button cannot be clicked, So cannot continue with the tc.");
					}
				} else {
					appLog.error("Manage folder icon cannot be clicked, So cannto continue with tc.");
					sa.assertTrue(false,"Manage folder icon cannot be clicked, So cannto continue with tc.");
				}
			} else {
				appLog.error(M5F2+" fund is not available so cannot continue with the tc.");
				sa.assertTrue(false,M5F2+" fund is not available so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot check add a folder pop up.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot check add a folder pop up.");
		}
		switchToDefaultContent(driver);
		if(fp.clickOnTab(TabName.InstituitonsTab)){
			if(instiPage.clickOnCreatedInstitution(M5I1)){
				switchToFrame(driver, 30, instiPage.getFrame( PageName.InstitutionsPage, 30));
				scrollDownThroughWebelement(driver, instiPage.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), Workspace.InvestorWorkspace+" View.");
				if(fp.verifyFolderPathdummy("Standard Folder New", null, M5LP1, M5F2, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}
				if(fp.verifyFolderPathdummy("Shared Folder New (Shared)", null, null, M5F2, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}
				switchToDefaultContent(driver);
			} else {
				appLog.error(M5LP1+" Instituion is not present in the list.");
				sa.assertTrue(false,M5LP1+" Instituion is not present in the list.");
			}
		} else {
			appLog.error("Insitutions Tab cannot be clicked, So cannot verify folder structure on institution page.");
			sa.assertTrue(false,"Insitutions Tab cannot be clicked, So cannot verify folder structure on institution page.");
		}
		if(fp.clickOnTab(TabName.InstituitonsTab)){
			if(instiPage.clickOnCreatedLP(environment, mode,M5LP1)){
				switchToFrame(driver, 30, instiPage.getFrame( PageName.LimitedPartnerPage, 30));
				scrollDownThroughWebelement(driver, instiPage.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), Workspace.InvestorWorkspace+" View.");
				if(fp.verifyFolderPathdummy("Standard Folder New", null, null, M5F2, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)){
					appLog.info("Folder structure is verified on instituion page of '"+M5LP1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5LP1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5LP1+"'.");
				}
				if(fp.verifyFolderPathdummy("Shared Folder New (Shared)", null, null, M5F2, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 30)){
					appLog.info("Folder structure is verified on instituion page of '"+M5LP1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5LP1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5LP1+"'.");
				}
				switchToDefaultContent(driver);
			} else {
				appLog.error(M5LP1+" Instituion is not present in the list.");
				sa.assertTrue(false,M5LP1+" Instituion is not present in the list.");
			}
		} else {
			appLog.error("Insitutions Tab cannot be clicked, So cannot verify folder structure on institution page.");
			sa.assertTrue(false,"Insitutions Tab cannot be clicked, So cannot verify folder structure on institution page.");
		}
		
		if(lp.clickOnTab(TabName.ContactTab)){
			if(con .clickOnCreatedContact(M5CFN1, M5CLN1, null)){
				switchToFrame(driver, 30, con.getFrame( PageName.ContactsPage, 30));
				if(fp.verifyFolderPathdummy("Standard Folder New", M5I1, M5LP1, M5F2, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)){
					appLog.info("Folder strucutre is verified on contact page for fund '"+M5F2+"' on contact page of "+M5CFN1 +" "+ M5CLN1);
				} else {
					appLog.error("Folder structure cannot be verified on contact page for fundraising workspace for fund '"+M5F2+"' on contact page of "+M5CFN1 +" "+ M5CLN1);
					sa.assertTrue(false,"Folder structure cannot be verified on contact page for fundraising workspace for fund '"+M5F2+"' on contact page of "+M5CFN1 +" "+ M5CLN1);
				}
				if(fp.verifyFolderPathdummy("Standard Folder New", M5I2, M5LP2, M5F2, PageName.ContactsPage, Workspace.InvestorWorkspace, 30)){
					appLog.info("Folder strucutre is verified on contact page for fund '"+M5F2+"' on contact page of "+M5CFN1 +" "+ M5CLN1);
				} else {
					appLog.error("Folder structure cannot be verified on contact page for fundraising workspace for fund '"+M5F2+"' on contact page of "+M5CFN1 +" "+ M5CLN1);
					sa.assertTrue(false,"Folder structure cannot be verified on contact page for fundraising workspace for fund '"+M5F2+"' on contact page of "+M5CFN1 +" "+ M5CLN1);
				}
				switchToDefaultContent(driver);
			} else {
				appLog.error(M5CFN1 +" "+ M5CLN1+" contact is not present in the list, So cannot continue wih the tc.");
				sa.assertTrue(false,M5CFN1 +" "+ M5CLN1+" contact is not present in the list, So cannot continue wih the tc.");
			}
		} else {
			appLog.error("Contact tab cannot be clicked, So cannot continue.");
			sa.assertTrue(false,"Contact tab cannot be clicked, So cannot continue.");
		}
		
		if(fp.clickOnTab(TabName.CommitmentsTab)){
			if(com.clickOnCreatedCommitmentId(environment, mode,M5CMT2)){
				switchToFrame(driver, 30, instiPage.getFrame( PageName.CommitmentsPage, 30));
				scrollDownThroughWebelement(driver, instiPage.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), Workspace.InvestorWorkspace+" View.");
				if(fp.verifyFolderPathdummy("Standard Folder New", null, M5LP1, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 30)){
					appLog.info("Standard Folder New Folder structure is verified on commitment page of '"+M5CMT2+"'.");
				} else {
					appLog.error("Folder structure is not verified on commitment page of '"+M5CMT2+"'.");
					sa.assertTrue(false,"Folder structure is not verified on commitment page of '"+M5CMT2+"'.");
				}
				if(fp.verifyFolderPathdummy("Shared Folder New (Shared)", null, null, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 30)){
					appLog.info("Shared Folder New (Shared) Folder structure is verified on commitment page of '"+M5CMT2+"'.");
				} else {
					appLog.error("Folder structure is not verified on commitment page of '"+M5CMT2+"'.");
					sa.assertTrue(false,"Folder structure is not verified on commitment page of '"+M5CMT2+"'.");
				}
				switchToDefaultContent(driver);
			} else {
				appLog.error(M5CMT2+" Instituion is not present in the list.");
				sa.assertTrue(false,M5CMT2+" Instituion is not present in the list.");
			}
		} else {
			appLog.error("Insitutions Tab cannot be clicked, So cannot verify folder structure on institution page.");
			sa.assertTrue(false,"Insitutions Tab cannot be clicked, So cannot verify folder structure on institution page.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Test
	public void M5tc013_2_VerifyAddAFolderPopUpInvestorWorkspaceAtTargetSide(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		lp.investorLogin(M5C1Email, adminPassword);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		if(ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)){
			if(fp.verifyFolderPathdummy("Standard Folder New", M5I1, M5LP1, null, PageName.CurrentInvestmentPgae, null, 30)){
				appLog.info("Standard Folder New is verified.");
			} else {
				appLog.error("Standard Folder New is not verified on investor current investment page.");
				sa.assertTrue(false,"Standard Folder New is not verified on investor current investment page.");
			}
			if(fp.verifyFolderPathdummy("Standard Folder New", M5I2, M5LP2, null, PageName.CurrentInvestmentPgae, null, 30)){
				appLog.info("Standard Folder New is verified.");
			} else {
				appLog.error("Standard Folder New is not verified on investor current investment page.");
				sa.assertTrue(false,"Standard Folder New is not verified on investor current investment page.");
			}
		} else {
			appLog.error("Investment tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Investment tab cannot be clicked, SO cannot continue with the tc.");
		}
		lp.investorLogout();
		sa.assertAll();
	}

	@Test
	public void M5tc014_1_VerifyAddASubFolderPopUpInvestorWorkspace(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer instiPage= new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer con = new ContactPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ins = new InstitutionPageBusinessLayer(driver);
		CommitmentPageBusinessLayer com = new CommitmentPageBusinessLayer(driver);
		String dependsOntc="M5tc007_1_VerifyAddASubFolderPopUpFundraisingWorkspace";
		String standardFolderPath = ExcelUtils.readData("filePath", excelLabel.TestCases_Name, dependsOntc, excelLabel.StandardPath);
		String commonFolderPath = ExcelUtils.readData("filePath", excelLabel.TestCases_Name, dependsOntc, excelLabel.CommonPath);
		String sharedFolderPath = ExcelUtils.readData("filePath", excelLabel.TestCases_Name, dependsOntc, excelLabel.SharedPath);
		String internalFolderPath = ExcelUtils.readData("filePath", excelLabel.TestCases_Name, dependsOntc, excelLabel.InternalPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M5F2)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view.");
				if(click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 30), "Manage folder icon", action.BOOLEAN)){
					scrollDownThroughWebelement(driver, fp.getAlertHistoryLink(Workspace.FundraisingWorkspace, PageName.FundsPage, 2), "");
					if(fp.verifyAddRenameDeleteButton(null, FolderType.Common, YesNo.Yes, Workspace.InvestorWorkspace, 30)){
						appLog.info("Add Rename Delete Button are verified for folder type Common.");
					} else {
						appLog.error("Add Rename Delete Button are not verified for folder type Common.");
						sa.assertTrue(false,"Add Rename Delete Button are not verified for folder type Common.");
					}
					
					if(fp.verifyAddRenameDeleteButton(null, FolderType.Shared, YesNo.Yes, Workspace.InvestorWorkspace, 30)){
						appLog.info("Add Rename Delete Button are verified for folder type Shared.");
					} else {
						appLog.error("Add Rename Delete Button are not verified for folder type Shared.");
						sa.assertTrue(false,"Add Rename Delete Button are not verified for folder type Shared.");
					}
					
					if(fp.verifyAddRenameDeleteButton(null, FolderType.Internal, YesNo.Yes, Workspace.InvestorWorkspace, 30)){
						appLog.info("Add Rename Delete Button are verified for folder type Internal.");
					} else {
						appLog.error("Add Rename Delete Button are not verified for folder type Internal.");
						sa.assertTrue(false,"Add Rename Delete Button are not verified for folder type Internal.");
					}
					
					if(fp.verifyAddRenameDeleteButton(standardFolderPath.split(",")[0], FolderType.Standard, YesNo.Yes, Workspace.InvestorWorkspace, 30)){
						appLog.info("Add Rename Delete Button are verified for folder type Standard.");
					} else {
						appLog.error("Add Rename Delete Button are not verified for folder type Standard.");
						sa.assertTrue(false,"Add Rename Delete Button are not verified for folder type Standard.");
					}
					
					if(fp.verifyAddRenameDeleteButton("Standard Folder New", FolderType.Standard, YesNo.No, Workspace.InvestorWorkspace, 30)){
						appLog.info("Add Rename Delete Button are verified for folder Standard Folder New.");
					} else {
						appLog.error("Add Rename Delete Button are not verified for folder Standard Folder New.");
						sa.assertTrue(false,"Add Rename Delete Button are not verified for folder Standard Folder New.");
					}
					
					if(fp.verifyAddRenameDeleteButton("Shared Folder New (Shared)", FolderType.Standard, YesNo.No, Workspace.InvestorWorkspace, 30)){
						appLog.info("Add Rename Delete Button are verified for folder Shared Folder New (Shared).");
					} else {
						appLog.error("Add Rename Delete Button are not verified for folder Shared Folder New (Shared).");
						sa.assertTrue(false,"Add Rename Delete Button are not verified for folder Shared Folder New (Shared).");
					}
					if(fp.clickOnAddAFolderButton(null, FolderType.Common, Workspace.InvestorWorkspace, 30)){
						String text = trim(getText(driver, fp.getAddFolderChildHeader(Workspace.InvestorWorkspace, 30), "", action.BOOLEAN));
						if(text.equalsIgnoreCase("Add a folder")){
							appLog.info("Pop UJp header is verified.");
						} else {
							appLog.error("Pop Up Header is not verified. Expected: Add a folder\tActual: "+text);
							sa.assertTrue(false,"Pop Up Header is not verified. Expected: Add a folder\tActual: "+text);
						}
						if(fp.getAddFolderChildCrossIcon(Workspace.InvestorWorkspace, 30)!=null){
							appLog.info("Cross icon is present is verified.");
						} else {
							appLog.error("Pop Up Cross icon is not present so cannot check the functionality.");
							sa.assertTrue(false,"Pop Up Cross icon is not present so cannot check the functionality.");
						}
						
						if(fp.getAddFolderChildCancelButton(Workspace.InvestorWorkspace, 30)!=null){
							appLog.info("Cancel button is present is verified.");
						} else {
							appLog.error("Pop Up Cancel button is not present so cannot check the functionality.");
							sa.assertTrue(false,"Pop Up Cancel button is not present so cannot check the functionality.");
						}
						
						if(click(driver, fp.getAddFolderChildCrossIcon(Workspace.InvestorWorkspace, 30), "cross icon", action.BOOLEAN)){
//							ThreadSleep(1500);
//							if(!click(driver, fp.getAddFolderChildCrossIcon(Workspace.InvestorWorkspace, 3), "cross icon", action.BOOLEAN)){
//								appLog.info("Cross icon is working");
//							} else {
//								appLog.error("Cross icon is not working.");
//								sa.assertTrue(false,"Cross icon is not working.");
//							}
						} else {
							appLog.error("Cross icon functionality cannot be checked.");
							sa.assertTrue(false,"Cross icon functionality cannot be checked.");
						}
						if(fp.clickOnAddAFolderButton(null, FolderType.Common, Workspace.InvestorWorkspace, 30)){
							if(click(driver, fp.getAddFolderChildCancelButton(Workspace.InvestorWorkspace, 30), "Cancel Button", action.BOOLEAN)){
//								ThreadSleep(1500);
//								if(!click(driver, fp.getAddFolderChildCancelButton(Workspace.InvestorWorkspace, 3), "Cancel Button", action.BOOLEAN)){
//									appLog.info("Cancel Button is working");
//								} else {
//									appLog.error("Cancel Button is not working.");
//									sa.assertTrue(false,"Cancel Button is not working.");
//								}
							} else {
								appLog.error("Cross icon functionality cannot be checked.");
								sa.assertTrue(false,"Cross icon functionality cannot be checked.");
							}
						}
						if(fp.createFolderStructure(commonFolderPath.split(",")[0], FolderType.Common, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
							appLog.info(commonFolderPath.split(",")[0]+ " folder structure is created.");
						} else {
							appLog.error(commonFolderPath.split(",")[0]+ " folder structure is not created.");
							sa.assertTrue(false,commonFolderPath.split(",")[0]+ " folder structure is not created.");
						}
						if(fp.createFolderStructure(commonFolderPath.split(",")[1], FolderType.Common, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
							appLog.info(commonFolderPath.split(",")[1]+ " folder structure is created.");
						} else {
							appLog.error(commonFolderPath.split(",")[1]+ " folder structure is not created.");
							sa.assertTrue(false,commonFolderPath.split(",")[1]+ " folder structure is not created.");
						}
//						if(fp.verifyFolderPathdummy(commonFolderPath.split(",")[0], null, null, null, PageName.ManageFolderPopUp, Workspace.InvestorWorkspace, 5)){
//							appLog.info(commonFolderPath.split(",")[0]+ " folder structure is created after clicking on save button.");
//						} else {
//							appLog.error(commonFolderPath.split(",")[0]+ " folder structure is not created after clicking on save button.");
//							sa.assertTrue(false,commonFolderPath.split(",")[0]+ " folder structure is not created after clicking on save button.");
//						}
//						if(fp.verifyFolderPathdummy(commonFolderPath.split(",")[0], null, null, null, PageName.ManageFolderPopUp, Workspace.InvestorWorkspace, 5)){
//							appLog.info(commonFolderPath.split(",")[1]+ " folder structure is created after clicking on save button.");
//						} else {
//							appLog.error(commonFolderPath.split(",")[1]+ " folder structure is not created after clicking on save button.");
//							sa.assertTrue(false,commonFolderPath.split(",")[1]+ " folder structure is not created after clicking on save button.");
//						}
						if(FindElement(driver, "//label[contains(text(),'"+commonFolderPath.split(",")[0].split("/")[0]+"')]/../../../following-sibling::ul/li/div/a/span/label[contains(text(),'"+commonFolderPath.split(",")[0].split("/")[1]+"')]", "Folder in view", action.BOOLEAN, 30)!=null){
							appLog.info(commonFolderPath.split(",")[0]+" Folder is successfully created and is verified.");
						} else {
							appLog.error(commonFolderPath.split(",")[0]+" New folder is not being created after clicking on Save button.");
							sa.assertTrue(false,commonFolderPath.split(",")[0]+" New folder is not being created after clicking on Save button.");
						}
						if(FindElement(driver, "//label[contains(text(),'"+commonFolderPath.split(",")[1].split("/")[0]+"')]/../../../following-sibling::ul/li/div/a/span/label[contains(text(),'"+commonFolderPath.split(",")[1].split("/")[1]+"')]/../../../following-sibling::ul/li/div/a/span/label[contains(text(),'"+commonFolderPath.split(",")[1].split("/")[2]+"')]", "Folder in view", action.BOOLEAN, 30)!=null){
							appLog.info(commonFolderPath.split(",")[1]+" Folder is successfully created and is verified.");
						} else {
							appLog.error(commonFolderPath.split(",")[1]+" New folder is not being created after clicking on Save button.");
							sa.assertTrue(false,commonFolderPath.split(",")[1]+" New folder is not being created after clicking on Save button.");
						}
						if(fp.createFolderStructure(sharedFolderPath.split(",")[0], FolderType.Shared, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
							appLog.info(sharedFolderPath.split(",")[0]+ " folder structure is created.");
						} else {
							appLog.error(sharedFolderPath.split(",")[0]+ " folder structure is not created.");
							sa.assertTrue(false,sharedFolderPath.split(",")[0]+ " folder structure is not created.");
						}
						if(fp.createFolderStructure(sharedFolderPath.split(",")[1], FolderType.Shared, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
							appLog.info(sharedFolderPath.split(",")[1]+ " folder structure is created.");
						} else {
							appLog.error(sharedFolderPath.split(",")[1]+ " folder structure is not created.");
							sa.assertTrue(false,sharedFolderPath.split(",")[1]+ " folder structure is not created.");
						}
//						if(fp.verifyFolderPathdummy(sharedFolderPath.split(",")[0], null, null, null, PageName.ManageFolderPopUp, Workspace.InvestorWorkspace, 5)){
//							appLog.info(sharedFolderPath.split(",")[0]+ " folder structure is created after clicking on save button.");
//						} else {
//							appLog.error(sharedFolderPath.split(",")[0]+ " folder structure is not created after clicking on save button.");
//							sa.assertTrue(false,sharedFolderPath.split(",")[0]+ " folder structure is not created after clicking on save button.");
//						}
//						if(fp.verifyFolderPathdummy(sharedFolderPath.split(",")[0], null, null, null, PageName.ManageFolderPopUp, Workspace.InvestorWorkspace, 5)){
//							appLog.info(sharedFolderPath.split(",")[1]+ " folder structure is created after clicking on save button.");
//						} else {
//							appLog.error(sharedFolderPath.split(",")[1]+ " folder structure is not created after clicking on save button.");
//							sa.assertTrue(false,sharedFolderPath.split(",")[1]+ " folder structure is not created after clicking on save button.");
//						}
						if(FindElement(driver, "//label[contains(text(),'"+sharedFolderPath.split(",")[0].split("/")[0]+"')]/../../../following-sibling::ul/li/div/a/span/label[contains(text(),'"+sharedFolderPath.split(",")[0].split("/")[1]+"')]", "Folder in view", action.BOOLEAN, 30)!=null){
							appLog.info(sharedFolderPath.split(",")[0]+" Folder is successfully created and is verified.");
						} else {
							appLog.error(sharedFolderPath.split(",")[0]+" New folder is not being created after clicking on Save button.");
							sa.assertTrue(false,sharedFolderPath.split(",")[0]+" New folder is not being created after clicking on Save button.");
						}
						if(FindElement(driver, "//label[contains(text(),'"+sharedFolderPath.split(",")[1].split("/")[0]+"')]/../../../following-sibling::ul/li/div/a/span/label[contains(text(),'"+sharedFolderPath.split(",")[1].split("/")[1]+"')]/../../../following-sibling::ul/li/div/a/span/label[contains(text(),'"+sharedFolderPath.split(",")[1].split("/")[2]+"')]", "Folder in view", action.BOOLEAN, 30)!=null){
							appLog.info(sharedFolderPath.split(",")[1]+" Folder is successfully created and is verified.");
						} else {
							appLog.error(sharedFolderPath.split(",")[1]+" New folder is not being created after clicking on Save button.");
							sa.assertTrue(false,sharedFolderPath.split(",")[1]+" New folder is not being created after clicking on Save button.");
						}
						if(fp.createFolderStructure(internalFolderPath.split(",")[0], FolderType.Internal, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
							appLog.info(internalFolderPath.split(",")[0]+ " folder structure is created.");
						} else {
							appLog.error(internalFolderPath.split(",")[0]+ " folder structure is not created.");
							sa.assertTrue(false,internalFolderPath.split(",")[0]+ " folder structure is not created.");
						}
						if(fp.createFolderStructure(internalFolderPath.split(",")[1], FolderType.Internal, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
							appLog.info(internalFolderPath.split(",")[1]+ " folder structure is created.");
						} else {
							appLog.error(internalFolderPath.split(",")[1]+ " folder structure is not created.");
							sa.assertTrue(false,internalFolderPath.split(",")[1]+ " folder structure is not created.");
						}
//						if(fp.verifyFolderPathdummy(sharedFolderPath.split(",")[0], null, null, null, PageName.ManageFolderPopUp, Workspace.InvestorWorkspace, 5)){
//							appLog.info(sharedFolderPath.split(",")[0]+ " folder structure is created after clicking on save button.");
//						} else {
//							appLog.error(sharedFolderPath.split(",")[0]+ " folder structure is not created after clicking on save button.");
//							sa.assertTrue(false,sharedFolderPath.split(",")[0]+ " folder structure is not created after clicking on save button.");
//						}
//						if(fp.verifyFolderPathdummy(sharedFolderPath.split(",")[0], null, null, null, PageName.ManageFolderPopUp, Workspace.InvestorWorkspace, 5)){
//							appLog.info(sharedFolderPath.split(",")[1]+ " folder structure is created after clicking on save button.");
//						} else {
//							appLog.error(sharedFolderPath.split(",")[1]+ " folder structure is not created after clicking on save button.");
//							sa.assertTrue(false,sharedFolderPath.split(",")[1]+ " folder structure is not created after clicking on save button.");
//						}
						if(FindElement(driver, "//label[contains(text(),'"+internalFolderPath.split(",")[0].split("/")[0]+"')]/../../../following-sibling::ul/li/div/a/span/label[contains(text(),'"+internalFolderPath.split(",")[0].split("/")[1]+"')]", "Folder in view", action.BOOLEAN, 30)!=null){
							appLog.info(internalFolderPath.split(",")[0]+" Folder is successfully created and is verified.");
						} else {
							appLog.error(internalFolderPath.split(",")[0]+" New folder is not being created after clicking on Save button.");
							sa.assertTrue(false,internalFolderPath.split(",")[0]+" New folder is not being created after clicking on Save button.");
						}
						if(FindElement(driver, "//label[contains(text(),'"+internalFolderPath.split(",")[1].split("/")[0]+"')]/../../../following-sibling::ul/li/div/a/span/label[contains(text(),'"+internalFolderPath.split(",")[1].split("/")[1]+"')]/../../../following-sibling::ul/li/div/a/span/label[contains(text(),'"+internalFolderPath.split(",")[1].split("/")[2]+"')]", "Folder in view", action.BOOLEAN, 30)!=null){
							appLog.info(internalFolderPath.split(",")[1]+" Folder is successfully created and is verified.");
						} else {
							appLog.error(internalFolderPath.split(",")[1]+" New folder is not being created after clicking on Save button.");
							sa.assertTrue(false,internalFolderPath.split(",")[1]+" New folder is not being created after clicking on Save button.");
						}
						if(fp.createFolderStructure(standardFolderPath.split(",")[1], FolderType.Standard, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
							appLog.info(standardFolderPath.split(",")[1]+ " folder structure is created.");
						} else {
							appLog.error(standardFolderPath.split(",")[1]+ " folder structure is not created.");
							sa.assertTrue(false,standardFolderPath.split(",")[1]+ " folder structure is not created.");
						}
						if(fp.createFolderStructure(standardFolderPath.split(",")[2], FolderType.Standard, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
							appLog.info(standardFolderPath.split(",")[2]+ " folder structure is created.");
						} else {
							appLog.error(standardFolderPath.split(",")[2]+ " folder structure is not created.");
							sa.assertTrue(false,standardFolderPath.split(",")[2]+ " folder structure is not created.");
						}
//						if(fp.verifyFolderPathdummy(standardFolderPath.split(",")[1], null, null, null, PageName.ManageFolderPopUp, Workspace.InvestorWorkspace, 5)){
//							appLog.info(standardFolderPath.split(",")[1]+ " folder structure is created after clicking on save button.");
//						} else {
//							appLog.error(standardFolderPath.split(",")[1]+ " folder structure is not created after clicking on save button.");
//							sa.assertTrue(false,standardFolderPath.split(",")[1]+ " folder structure is not created after clicking on save button.");
//						}
//						if(fp.verifyFolderPathdummy(standardFolderPath.split(",")[2], null, null, null, PageName.ManageFolderPopUp, Workspace.InvestorWorkspace, 5)){
//							appLog.info(standardFolderPath.split(",")[2]+ " folder structure is created after clicking on save button.");
//						} else {
//							appLog.error(standardFolderPath.split(",")[2]+ " folder structure is not created after clicking on save button.");
//							sa.assertTrue(false,standardFolderPath.split(",")[2]+ " folder structure is not created after clicking on save button.");
//						}
						if(FindElement(driver, "//label[contains(text(),'"+standardFolderPath.split(",")[1].split("/")[0]+"')]/../../../following-sibling::ul/li/div/a/span/label[contains(text(),'"+standardFolderPath.split(",")[1].split("/")[1]+"')]", "Folder in view", action.BOOLEAN, 30)!=null){
							appLog.info(standardFolderPath.split(",")[1]+" Folder is successfully created and is verified.");
						} else {
							appLog.error(standardFolderPath.split(",")[1]+" New folder is not being created after clicking on Save button.");
							sa.assertTrue(false,standardFolderPath.split(",")[1]+" New folder is not being created after clicking on Save button.");
						}
						if(FindElement(driver, "//label[contains(text(),'"+standardFolderPath.split(",")[2].split("/")[0]+"')]/../../../following-sibling::ul/li/div/a/span/label[contains(text(),'"+standardFolderPath.split(",")[2].split("/")[1]+"')]/../../../following-sibling::ul/li/div/a/span/label[contains(text(),'"+standardFolderPath.split(",")[2].split("/")[2]+"')]", "Folder in view", action.BOOLEAN, 30)!=null){
							appLog.info(standardFolderPath.split(",")[2]+" Folder is successfully created and is verified.");
						} else {
							appLog.error(standardFolderPath.split(",")[2]+" New folder is not being created after clicking on Save button.");
							sa.assertTrue(false,standardFolderPath.split(",")[2]+" New folder is not being created after clicking on Save button.");
						}
					}
					
				} else {
					appLog.error("Manage folder icon cannot be clicked, So cannto continue with tc.");
					sa.assertTrue(false,"Manage folder icon cannot be clicked, So cannto continue with tc.");
				}
			} else {
				appLog.error(M5F3+" fund is not available so cannot continue with the tc.");
				sa.assertTrue(false,M5F3+" fund is not available so cannot continue with the tc.");
			}
		} else {
			appLog.error("Funds tab cannot be clicked, So cannot check add a folder pop up.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot check add a folder pop up.");
		}
		switchToDefaultContent(driver);
		if(fp.clickOnTab(TabName.InstituitonsTab)){
			if(ins.clickOnCreatedInstitution(M5I1)){
				switchToFrame(driver, 30, ins.getFrame( PageName.InstitutionsPage, 30));
				scrollDownThroughWebelement(driver, ins.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), Workspace.InvestorWorkspace+" View.");
				if(fp.verifyFolderPathdummy(commonFolderPath.split(",")[0], null, null, M5F2, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}

				if(fp.verifyFolderPathdummy(commonFolderPath.split(",")[1], null, null, M5F2, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}
				
				if(fp.verifyFolderPathdummy(standardFolderPath.split(",")[1], null, M5LP1, M5F2, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}
				if(fp.verifyFolderPathdummy(standardFolderPath.split(",")[2], null, M5LP1, M5F2, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}
				if(fp.verifyFolderPathdummy(sharedFolderPath.split(",")[0], null, null, M5F2, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}
				if(fp.verifyFolderPathdummy(sharedFolderPath.split(",")[1], null, null, M5F2, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}
				
				if(fp.verifyFolderPathdummy(internalFolderPath.split(",")[0], null, null, M5F2, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}
				if(fp.verifyFolderPathdummy(internalFolderPath.split(",")[1], null, null, M5F2, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}
				switchToDefaultContent(driver);
			} else {
				appLog.error(M5I1+" Instituion is not present in the list.");
				sa.assertTrue(false,M5I1+" Instituion is not present in the list.");
			}
		} else {
			appLog.error("Insitutions Tab cannot be clicked, So cannot verify folder structure on institution page.");
			sa.assertTrue(false,"Insitutions Tab cannot be clicked, So cannot verify folder structure on institution page.");
		}
		if(lp.clickOnTab(TabName.ContactTab)){
			if(con .clickOnCreatedContact(M5CFN1, M5CLN1, null)){
				switchToFrame(driver, 30, con.getFrame( PageName.ContactsPage, 30));
				if(fp.verifyFolderPathdummy(commonFolderPath.split(",")[0], null, null, M5F2, PageName.ContactsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}

				if(fp.verifyFolderPathdummy(commonFolderPath.split(",")[1], null, null, M5F2, PageName.ContactsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}
				
				if(fp.verifyFolderPathdummy(standardFolderPath.split(",")[1], M5I1, M5LP1, M5F2, PageName.ContactsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}
				if(fp.verifyFolderPathdummy(standardFolderPath.split(",")[2], M5I1, M5LP1, M5F2, PageName.ContactsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on instituion page of '"+M5I1+"'.");
				} else {
					appLog.error("Folder structure is not verified on instituion page of '"+M5I1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on instituion page of '"+M5I1+"'.");
				}
				switchToDefaultContent(driver);
			} else {
				appLog.error(M5CFN1 +" "+ M5CLN1+" contact is not present in the list, So cannot continue wih the tc.");
				sa.assertTrue(false,M5CFN1 +" "+ M5CLN1+" contact is not present in the list, So cannot continue wih the tc.");
			}
		} else {
			appLog.error("Contact tab cannot be clicked, So cannot continue.");
			sa.assertTrue(false,"Contact tab cannot be clicked, So cannot continue.");
		}
		if(fp.clickOnTab(TabName.InstituitonsTab)) {
			if(ins.clickOnCreatedLP(environment, mode,M5LP1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.LimitedPartnerPage, 30));
				if(fp.verifyFolderPathdummy(commonFolderPath.split(",")[0], null, null, M5F2, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on LP page of '"+M5LP1+"'.");
				} else {
					appLog.error("Folder structure is not verified on LP page of '"+M5LP1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on LP page of '"+M5LP1+"'.");
				}

				if(fp.verifyFolderPathdummy(commonFolderPath.split(",")[1], null, null, M5F2, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on LP page of '"+M5LP1+"'.");
				} else {
					appLog.error("Folder structure is not verified on LP page of '"+M5LP1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on LP page of '"+M5LP1+"'.");
				}
				
				if(fp.verifyFolderPathdummy(standardFolderPath.split(",")[1], null, null, M5F2, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on LP page of '"+M5LP1+"'.");
				} else {
					appLog.error("Folder structure is not verified on LP page of '"+M5LP1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on LP page of '"+M5LP1+"'.");
				}
				if(fp.verifyFolderPathdummy(standardFolderPath.split(",")[2], null, null, M5F2, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on LP page of '"+M5LP1+"'.");
				} else {
					appLog.error("Folder structure is not verified on LP page of '"+M5LP1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on LP page of '"+M5LP1+"'.");
				}
				if(fp.verifyFolderPathdummy(sharedFolderPath.split(",")[0], null, null, M5F2, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on LP page of '"+M5LP1+"'.");
				} else {
					appLog.error("Folder structure is not verified on LP page of '"+M5LP1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on LP page of '"+M5LP1+"'.");
				}
				if(fp.verifyFolderPathdummy(sharedFolderPath.split(",")[1], null, null, M5F2, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on LP page of '"+M5LP1+"'.");
				} else {
					appLog.error("Folder structure is not verified on LP page of '"+M5LP1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on LP page of '"+M5LP1+"'.");
				}
				
				if(fp.verifyFolderPathdummy(internalFolderPath.split(",")[0], null, null, M5F2, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on LP page of '"+M5LP1+"'.");
				} else {
					appLog.error("Folder structure is not verified on LP page of '"+M5LP1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on LP page of '"+M5LP1+"'.");
				}
				if(fp.verifyFolderPathdummy(internalFolderPath.split(",")[1], null, null, M5F2, PageName.InstitutionsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on LP page of '"+M5LP1+"'.");
				} else {
					appLog.error("Folder structure is not verified on LP page of '"+M5LP1+"'.");
					sa.assertTrue(false,"Folder structure is not verified on LP page of '"+M5LP1+"'.");
				}
				switchToDefaultContent(driver);
			}else {
				appLog.error("Not able to click on "+M5LP1+" LP Name so cannot check Updated Folder Names");
				sa.assertTrue(false, "Not able to click on "+M5LP1+" LP Name so cannot check Updated Folder Names");
			}
		}else {
			appLog.error("Not able to click on institution tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on institution tab so cannot continue with tc");
		}
		if(fp.clickOnTab(TabName.CommitmentsTab)) {
			if(com.clickOnCreatedCommitmentId(environment, mode,M5CMT2)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.CommitmentsPage, 30));
				if(fp.verifyFolderPathdummy(commonFolderPath.split(",")[0], null, null, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on Commitment page of '"+M5CMT2+"'.");
				} else {
					appLog.error("Folder structure is not verified on Commitment page of '"+M5CMT2+"'.");
					sa.assertTrue(false,"Folder structure is not verified on Commitment page of '"+M5CMT2+"'.");
				}

				if(fp.verifyFolderPathdummy(commonFolderPath.split(",")[1], null, null, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on Commitment page of '"+M5CMT2+"'.");
				} else {
					appLog.error("Folder structure is not verified on Commitment page of '"+M5CMT2+"'.");
					sa.assertTrue(false,"Folder structure is not verified on Commitment page of '"+M5CMT2+"'.");
				}
				
				if(fp.verifyFolderPathdummy(standardFolderPath.split(",")[1], null, M5LP1, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on Commitment page of '"+M5CMT2+"'.");
				} else {
					appLog.error("Folder structure is not verified on Commitment page of '"+M5CMT2+"'.");
					sa.assertTrue(false,"Folder structure is not verified on Commitment page of '"+M5CMT2+"'.");
				}
				if(fp.verifyFolderPathdummy(standardFolderPath.split(",")[2], null, M5LP1, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on Commitment page of '"+M5CMT2+"'.");
				} else {
					appLog.error("Folder structure is not verified on Commitment page of '"+M5CMT2+"'.");
					sa.assertTrue(false,"Folder structure is not verified on Commitment page of '"+M5CMT2+"'.");
				}
				if(fp.verifyFolderPathdummy(sharedFolderPath.split(",")[0], null, null, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on Commitment page of '"+M5CMT2+"'.");
				} else {
					appLog.error("Folder structure is not verified on Commitment page of '"+M5CMT2+"'.");
					sa.assertTrue(false,"Folder structure is not verified on Commitment page of '"+M5CMT2+"'.");
				}
				if(fp.verifyFolderPathdummy(sharedFolderPath.split(",")[1], null, null, null, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on Commitment page of '"+M5CMT2+"'.");
				} else {
					appLog.error("Folder structure is not verified on Commitment page of '"+M5CMT2+"'.");
					sa.assertTrue(false,"Folder structure is not verified on Commitment page of '"+M5CMT2+"'.");
				}
				
				if(fp.verifyFolderPathdummy(internalFolderPath.split(",")[0], null, null, M5F2, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on Commitment page of '"+M5CMT2+"'.");
				} else {
					appLog.error("Folder structure is not verified on Commitment page of '"+M5CMT2+"'.");
					sa.assertTrue(false,"Folder structure is not verified on Commitment page of '"+M5CMT2+"'.");
				}
				if(fp.verifyFolderPathdummy(internalFolderPath.split(",")[1], null, null, M5F2, PageName.CommitmentsPage, Workspace.InvestorWorkspace, 10)){
					appLog.info("Folder structure is verified on Commitment page of '"+M5CMT2+"'.");
				} else {
					appLog.error("Folder structure is not verified on Commitment page of '"+M5CMT2+"'.");
					sa.assertTrue(false,"Folder structure is not verified on Commitment page of '"+M5CMT2+"'.");
				}
				
			}else {
				appLog.error("Not able to click on "+M5CMT2+" Commitment id so cannot check Updated Folder Names");
				sa.assertTrue(false, "Not able to click on "+M5CMT2+" Commitment id so cannot check Updated Folder Names");
			}
		}else {
			appLog.error("Not able to click on Commitment tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on Commitment tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}

	@Test
	public void M5tc014_2_VerifyAddAFolderPopUpInvestorWorkspaceAtTargetSide(){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		lp.investorLogin(M5C1Email, adminPassword);
		String dependOntc="M5tc007_1_VerifyAddASubFolderPopUpFundraisingWorkspace";
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		String standardFolderPath = ExcelUtils.readData("filePath", excelLabel.TestCases_Name, dependOntc, excelLabel.StandardPath);
		String commonFolderPath = ExcelUtils.readData("filePath", excelLabel.TestCases_Name, dependOntc, excelLabel.CommonPath);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		if(ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)){
			if(fp.verifyFolderPathdummy(commonFolderPath.split(",")[0], null, null, M5F2, PageName.CurrentInvestmentPgae, null, 30)){
				appLog.info("Folder structure is verified on Current Investment page of '"+M5I1+"'.");
			} else {
				appLog.error("Folder structure is not verified on Current Investment page of '"+M5I1+"'.");
				sa.assertTrue(false,"Folder structure is not verified on Current Investment page of '"+M5I1+"'.");
			}

			if(fp.verifyFolderPathdummy(commonFolderPath.split(",")[1], null, null, M5F2, PageName.CurrentInvestmentPgae, null, 30)){
				appLog.info("Folder structure is verified on Current Investment page of '"+M5I1+"'.");
			} else {
				appLog.error("Folder structure is not verified on Current Investment page of '"+M5I1+"'.");
				sa.assertTrue(false,"Folder structure is not verified on Current Investment page of '"+M5I1+"'.");
			}
			
			if(fp.verifyFolderPathdummy(standardFolderPath.split(",")[1], M5I1, M5LP1, null, PageName.CurrentInvestmentPgae, null, 30)){
				appLog.info("Folder structure is verified on Current Investment page of '"+M5I1+"'.");
			} else {
				appLog.error("Folder structure is not verified on Current Investment page of '"+M5I1+"'.");
				sa.assertTrue(false,"Folder structure is not verified on Current Investment page of '"+M5I1+"'.");
			}
			if(fp.verifyFolderPathdummy(standardFolderPath.split(",")[2], M5I1, M5LP1, null, PageName.CurrentInvestmentPgae, null, 30)){
				appLog.info("Folder structure is verified on Current Investment page of '"+M5I1+"'.");
			} else {
				appLog.error("Folder structure is not verified on Current Investment page of '"+M5I1+"'.");
				sa.assertTrue(false,"Folder structure is not verified on Current Investment page of '"+M5I1+"'.");
			}
		} else {
			appLog.error("Investment tab cannot be clicked, SO cannot continue with the tc.");
			sa.assertTrue(false,"Investment tab cannot be clicked, SO cannot continue with the tc.");
		}
	}
	
	@Test
	public void M5tc015_verifyErrorMsgOnParentLevelInInvestorWorkSpace() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		String dependOntc="M5tc008_verifyErrorMsgOnParentLevel";
		String STDFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOntc,excelLabel.StandardPath);
		String commonFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOntc,excelLabel.CommonPath);
		String InternalFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOntc,excelLabel.InternalPath);
		String SHRDFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOntc,excelLabel.SharedPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M5F2)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view.");
				if(click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 30), "Manage folder icon", action.BOOLEAN)){
					if(click(driver, fp.getAllFolderAddIcon(Workspace.InvestorWorkspace, 30), "Add folder button", action.BOOLEAN)){
						String[] radioBtn= {STDFolderName,commonFolderName,SHRDFolderName,InternalFolderName};
						for(int i=0; i<radioBtn.length; i++) {
							boolean flag =false;
							if(i==0) {
								flag=fp.verifyFolderErrorMessage(FolderType.Standard, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,fp.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20);
							}if(i==1) {
								flag=fp.verifyFolderErrorMessage(FolderType.Common, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,fp.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20);
							}if(i==2) {
								flag=fp.verifyFolderErrorMessage(FolderType.Shared, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,fp.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20);
							}if(i==3) {
								flag=fp.verifyFolderErrorMessage(FolderType.Internal, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,fp.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20);
							}
							if(flag) {
								appLog.info(radioBtn[i]+" folder Error Message is verified successfully.");
							}else {
								appLog.error(radioBtn[i]+" folder Error Message is not verified.");
								sa.assertTrue(false, radioBtn[i]+" folder Error Message is not verified.");
							}
						}
						List<String> value = getValueBasedOnLabel(filePath, "SpecialChar",excelLabel.INVALID_FOLDER_NAME, 1);
						if(!value.isEmpty()) {
								for(int i=1; i<radioBtn.length; i++) {
									boolean flag =false;
									if(i==1) {
										flag=fp.verifyFolderErrorMessage(FolderType.Common, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,fp.prefixErrorMsg,value.get(i-1),ErrorMessageType.PrefixErrorMsg,20);
									}if(i==2) {
										flag=fp.verifyFolderErrorMessage(FolderType.Shared, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,fp.prefixErrorMsg,value.get(i-1),ErrorMessageType.PrefixErrorMsg,20);
									}if(i==3) {
										flag=fp.verifyFolderErrorMessage(FolderType.Internal, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,fp.prefixErrorMsg,value.get(i-1),ErrorMessageType.PrefixErrorMsg,20);
									}
									if(flag) {
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
									boolean flag =false;
									if(i==0) {
										flag=fp.verifyFolderErrorMessage(FolderType.Standard, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,fp.speicalCharErrorMsg,specialChar.get(j),ErrorMessageType.SpiecalCharErrorMsg,20);
									}if(i==1) {
										flag=fp.verifyFolderErrorMessage(FolderType.Common, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,fp.speicalCharErrorMsg,specialChar.get(j),ErrorMessageType.SpiecalCharErrorMsg,20);
									}if(i==2) {
										flag=fp.verifyFolderErrorMessage(FolderType.Shared, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,fp.speicalCharErrorMsg,specialChar.get(j),ErrorMessageType.SpiecalCharErrorMsg,20);
									}if(i==3) {
										flag=fp.verifyFolderErrorMessage(FolderType.Internal, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,fp.speicalCharErrorMsg,specialChar.get(j),ErrorMessageType.SpiecalCharErrorMsg,20);
									}
									if(flag) {
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
						for(int i=0; i<2; i++) {
							WebElement ele=null;
							ele=bp.getFolderTypeRadioButton(FolderType.Common,Workspace.InvestorWorkspace,PageName.ManageFolderPopUp, 30);
							if(ele!=null) {
								if(click(driver, ele,"common folder radio button icon", action.BOOLEAN)) {
									appLog.info("clicked on common radio button");
										if(sendKeys(driver, bp.getParentFolderNameTextBox(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,20),commonFolderName,"Common folder text box", action.BOOLEAN)) {
											appLog.info("value pass in folder Common text box: "+commonFolderName);
											if(click(driver, bp.getParentFolderSaveButton(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20), "folder save button", action.BOOLEAN)) {
												appLog.info("clicked on save button");
												WebElement ele1=bp.getFolderErrorMsg(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, ErrorMessageType.FolderCreationRestrictionErrorMsg, FolderType.Common,20);
												if(ele1!=null) {
													String aa=ele1.getText().trim();
													if(aa.contains(FundsPageErrorMessage.CommonFolderCreationErrorMsg)) {
														appLog.info("Folder error messgae is verified for folder type Common");
														
													}else {
														appLog.error("Folder error message is not verified for folder type Common Expected: "+FundsPageErrorMessage.CommonFolderCreationErrorMsg+"/t Actual: "+aa);
														sa.assertTrue(false, "Folder error message is not verified for folder type Common Expected: "+FundsPageErrorMessage.CommonFolderCreationErrorMsg+"/t Actual: "+aa);
													}
													if(i==0) {
														if(click(driver, fp.getFolderCreationErrorMessageCrossIcon(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, FolderType.Common,30),"common folder error message cross icon", action.BOOLEAN)) {
															appLog.info("clicked on common folder error message cross icon");
															if(fp.getFolderCreationErrorMessageOkBtn(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, FolderType.Common,20)==null) {
																appLog.info("error message pop is closed after click on cross icon");
																if(click(driver, fp.getAllFolderAddIcon(Workspace.InvestorWorkspace, 30), "Add folder button", action.BOOLEAN)){
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
														if(click(driver, fp.getFolderCreationErrorMessageOkBtn(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, FolderType.Common,30),"common folder error message cross icon", action.BOOLEAN)) {
															appLog.info("clicked on common folder error message OK button");
															if(fp.getFolderCreationErrorMessageOkBtn(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, FolderType.Common,20)==null) {
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
											appLog.info("Not able to pass value in folder Common text box: "+commonFolderName+" so cannot check error message for common folder. "+FundsPageErrorMessage.CommonFolderCreationErrorMsg);
											sa.assertTrue(false, "Not able to pass value in folder Common text box: "+commonFolderName+" so cannot check error message for common folder. "+FundsPageErrorMessage.CommonFolderCreationErrorMsg);
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
						if(click(driver, fp.getAllFolderAddIcon(Workspace.InvestorWorkspace, 30), "Add folder button", action.BOOLEAN)){
							for(int i=0; i<2; i++) {
								WebElement ele=null;
								ele=bp.getFolderTypeRadioButton(FolderType.Internal,Workspace.InvestorWorkspace,PageName.ManageFolderPopUp, 30);
								if(ele!=null) {
									if(click(driver, ele,"Internal folder radio button icon", action.BOOLEAN)) {
										appLog.info("clicked on Internal radio button");
											if(sendKeys(driver, bp.getParentFolderNameTextBox(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,20),InternalFolderName,"Internal folder text box", action.BOOLEAN)) {
												appLog.info("value pass in folder Internal text box: "+InternalFolderName);
												if(click(driver, bp.getParentFolderSaveButton(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20), "folder save button", action.BOOLEAN)) {
													appLog.info("clicked on save button");
													WebElement ele1=bp.getFolderErrorMsg(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, ErrorMessageType.FolderCreationRestrictionErrorMsg, FolderType.Internal,20);
													if(ele1!=null) {
														String aa=ele1.getText().trim();
														if(aa.contains(FundsPageErrorMessage.InternalFolderCreationErrorMsg)) {
															appLog.info("Folder error messgae is verified for folder type Internal");
															
														}else {
															appLog.error("Folder error message is not verified for folder type Internal Expected: "+FundsPageErrorMessage.InternalFolderCreationErrorMsg+"/t Actual: "+aa);
															sa.assertTrue(false, "Folder error message is not verified for folder type Internal Expected: "+FundsPageErrorMessage.InternalFolderCreationErrorMsg+"/t Actual: "+aa);
														}
														if(i==0) {
															if(click(driver, fp.getFolderCreationErrorMessageCrossIcon(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, FolderType.Internal,30),"Internal folder error message cross icon", action.BOOLEAN)) {
																appLog.info("clicked on Internal folder error message cross icon");
																if(fp.getFolderCreationErrorMessageOkBtn(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, FolderType.Internal,20)==null) {
																	appLog.info("error message pop is closed after click on cross icon");
																	if(click(driver, fp.getAllFolderAddIcon(Workspace.InvestorWorkspace, 30), "Add folder button", action.BOOLEAN)){
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
															if(click(driver, fp.getFolderCreationErrorMessageOkBtn(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, FolderType.Internal,30),"Internal folder error message cross icon", action.BOOLEAN)) {
																appLog.info("clicked on Internal folder error message OK button");
																if(fp.getFolderCreationErrorMessageOkBtn(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, FolderType.Internal,20)==null) {
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
												appLog.info("Not able to pass value in folder Internal text box: "+InternalFolderName+" so cannot check error message"+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
												sa.assertTrue(false, "Not able to pass value in folder Internal text box: "+InternalFolderName+" so cannot check error message"+FundsPageErrorMessage.InternalFolderCreationErrorMsg);
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
						String[] ss= {STDFolderName,SHRDFolderName};
						if(click(driver, fp.getAllFolderAddIcon(Workspace.InvestorWorkspace, 30), "Add folder button", action.BOOLEAN)){
							for(int i=0; i<2; i++) {
								WebElement ele=null;
								if(i==0) {
									ele=bp.getFolderTypeRadioButton(FolderType.Standard,Workspace.InvestorWorkspace,PageName.ManageFolderPopUp, 30);									
								}else {
									ele=bp.getFolderTypeRadioButton(FolderType.Shared,Workspace.InvestorWorkspace,PageName.ManageFolderPopUp, 30);	
								}
								if(ele!=null) {
									if(click(driver, ele,ss[i]+" folder radio button icon", action.BOOLEAN)) {
										appLog.info("clicked on "+ss[i]+" radio button");
											if(sendKeys(driver, bp.getParentFolderNameTextBox(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,20),ss[i],ss[i]+" folder text box", action.BOOLEAN)) {
												appLog.info("value pass in folder text box: "+ss[i]);
												boolean cssFlag=false;
												String cssSelectorPath="a[onclick*='CreateCommon_pop1investor();']";
												cssFlag=bp.clickUsingCssSelectorPath(cssSelectorPath, "Parent Folder Save Button");
												if(cssFlag/*click(driver, bp.getParentFolderSaveButton(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20), "folder save button", action.BOOLEAN)*/) {
													appLog.info("clicked on save button");
													ThreadSleep(2000);
													boolean isAlertPresent = isAlertPresent(driver);
													appLog.info("isAlertPresent  : "+isAlertPresent);
													if (true/*isAlertPresent(driver)*/) {
														appLog.info("Folder already exist alert pop up is successfully displayed");
														String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
														switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
														appLog.error("Error Message is displaying : "+msg);
														if(msg.contains(FundsPageErrorMessage.FolderAlreadyExistErrormsg)) {
															appLog.info("Error msg is verified successfully.");
														}else {
															appLog.error("Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
															sa.assertTrue(false, "Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
														}
													} else {
														appLog.error("Folder already exist alert pop up is not displayed for "+STDFolderName+" folder");
														sa.assertTrue(false, "Folder already exist alert pop up is not displayed for "+STDFolderName+" folder");
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
							}
							
						}else {
							appLog.error("Not able to click on Add folder icon so cannot check folder error message. Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
							sa.assertTrue(false, "Not able to click on Add folder icon so cannot check folder error message. Error Message: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg);
						}
						
					}else {
						appLog.error("Not able to click on Add folder icon so cannot check error messsgae in Investor workspace");
						sa.assertTrue(false, "Not able to click on Add folder icon so cannot check error messsgae in Investor workspace");
					}
					
				}else {
					appLog.error("Not able to click on Manage folder Icon so cannot check error message in Investor workspace");
					sa.assertTrue(false, "Not able to click on Manage folder Icon so cannot check error message in Investor workspace");
				}
				
			}else {
				appLog.error(M5F2+" fund is not available so cannot continue with the tc.");
				sa.assertTrue(false,M5F2+" fund is not available so cannot continue with the tc.");
			}
			
		}else {
			appLog.error("Funds tab cannot be clicked, So cannot check add a folder pop up.");
			sa.assertTrue(false,"Funds tab cannot be clicked, So cannot check add a folder pop up.");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Test
	public void M5tc016_verifyErrorMsgOnSubLevelInInvestorWorkSpace() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependOntc="M5tc009_verifyErrorMsgOnSubLevel";
		String STDFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOntc,excelLabel.StandardPath);
		String[] stdFolderName=STDFolderName.split(",");
		String[] commonFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOntc,excelLabel.CommonPath).split(",");
		String[] InternalFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOntc,excelLabel.InternalPath).split(",");
		String[] SHRDFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOntc,excelLabel.SharedPath).split(",");
		List<String> PrefixValue = getValueBasedOnLabel(filePath, "SpecialChar",excelLabel.INVALID_FOLDER_NAME, 1);
		List<String> specialChar = getValueBasedOnLabel(filePath, "SpecialChar",excelLabel.FOLDER_NAME, 1);
		boolean cssFlag=false;
		String cssSelectorPath="a[onclick*='CreateFolder_pop1investor();']";
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M5F2)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view.");
				if(click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 30), "Manage folder icon", action.BOOLEAN)){
						//String[] radioBtn= {STDFolderName,commonFolderName,SHRDFolderName,InternalFolderName};
						String id=null;
						id=fp.getCreatedFolderId(stdFolderName[0], PageName.ManageFolderPopUp, 20);
						if(id!=null) {
							if(fp.clickOnAddFolderButton(id)) {
								if(fp.verifyFolderErrorMessageSubLevel(FolderType.Standard, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20)) {
									appLog.info("Error Message is verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
								}else {
									appLog.error("Error Message is not verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
								}
								if(fp.verifyFolderErrorMessageSubLevel(FolderType.Standard, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.prefixErrorMsg,PrefixValue.get(0),ErrorMessageType.PrefixErrorMsg,20)) {
									appLog.info("Error Message is verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
								}else {
									appLog.error("Error Message is not verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
								}
								if(fp.verifyFolderErrorMessageSubLevel(FolderType.Standard, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.speicalCharErrorMsg,specialChar.get(0),ErrorMessageType.SpiecalCharErrorMsg,20)) {
									appLog.info("Error Message is verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
								}else {
									appLog.error("Error Message is not verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for sub folder "+stdFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
								}
								if(sendKeys(driver, fp.getChildFolderNameTextBox(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20), stdFolderName[1], stdFolderName[0]+" sub folder text box", action.BOOLEAN)) {
									cssFlag=fp.clickUsingCssSelectorPath(cssSelectorPath, "Child Folder Save Button");
								
									if(cssFlag/*click(driver, fp.getChildFolderSaveButton(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20), "sub folder save button", action.BOOLEAN)*/) {
										ThreadSleep(2000);
										boolean isAlertPresent = isAlertPresent(driver);
										appLog.info("isAlertPresent  : "+isAlertPresent);
										if (true/*isAlertPresent(driver)*/) {
											appLog.info("Folder already exist alert pop up is successfully displayed");
											String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
											switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
											appLog.error("Error Message is displaying : "+msg);
											if(msg.contains(FundsPageErrorMessage.FolderAlreadyExistErrormsg)) {
												appLog.info("Error msg is verified successfully.");
											}else {
												appLog.error("Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
												sa.assertTrue(false, "Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
											}
										} else {
											appLog.error("Folder already exist alert pop up is not displayed for "+stdFolderName[0]+" folder");
											sa.assertTrue(false, "Folder already exist alert pop up is not displayed for "+stdFolderName[0]+" folder");
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
						id=fp.getCreatedFolderId(commonFolderName[0], PageName.ManageFolderPopUp, 20);
						if(id!=null) {
							if(fp.clickOnAddFolderButton(id)) {
								if(fp.verifyFolderErrorMessageSubLevel(FolderType.Common, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20)) {
									appLog.info("Error Message is verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
								}else {
									appLog.error("Error Message is not verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
								}
								if(fp.verifyFolderErrorMessageSubLevel(FolderType.Common, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.prefixErrorMsg,PrefixValue.get(1),ErrorMessageType.PrefixErrorMsg,20)) {
									appLog.info("Error Message is verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
								}else {
									appLog.error("Error Message is not verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
								}
								if(fp.verifyFolderErrorMessageSubLevel(FolderType.Common, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.speicalCharErrorMsg,specialChar.get(0),ErrorMessageType.SpiecalCharErrorMsg,20)) {
									appLog.info("Error Message is verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
								}else {
									appLog.error("Error Message is not verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for sub folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
								}
								if(sendKeys(driver, fp.getChildFolderNameTextBox(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20), commonFolderName[1], commonFolderName[0]+" sub folder text box", action.BOOLEAN)) {
									cssFlag=fp.clickUsingCssSelectorPath(cssSelectorPath, "Child Folder Save Button");
									if(cssFlag/*click(driver, fp.getChildFolderSaveButton(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20), "sub folder save button", action.BOOLEAN)*/) {
										ThreadSleep(2000);
										boolean isAlertPresent = isAlertPresent(driver);
										appLog.info("isAlertPresent  : "+isAlertPresent);
										if (true/*isAlertPresent(driver)*/) {
											appLog.info("Folder already exist alert pop up is successfully displayed");
											String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
											switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
											appLog.error("Error Message is displaying : "+msg);
											if(msg.contains(FundsPageErrorMessage.FolderAlreadyExistErrormsg)) {
												appLog.info("Error msg is verified successfully.");
											}else {
												appLog.error("Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
												sa.assertTrue(false, "Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
											}
										} else {
											appLog.error("Folder already exist alert pop up is not displayed for "+commonFolderName[0]+" folder");
											sa.assertTrue(false, "Folder already exist alert pop up is not displayed for "+commonFolderName[0]+" folder");
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
						id=fp.getCreatedFolderId(SHRDFolderName[0], PageName.ManageFolderPopUp, 20);
						if(id!=null) {
							if(fp.clickOnAddFolderButton(id)) {
								if(fp.verifyFolderErrorMessageSubLevel(FolderType.Shared, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20)) {
									appLog.info("Error Message is verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
								}else {
									appLog.error("Error Message is not verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
								}
								if(fp.verifyFolderErrorMessageSubLevel(FolderType.Shared, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.prefixErrorMsg,PrefixValue.get(2),ErrorMessageType.PrefixErrorMsg,20)) {
									appLog.info("Error Message is verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
								}else {
									appLog.error("Error Message is not verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
								}
								if(fp.verifyFolderErrorMessageSubLevel(FolderType.Shared, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.speicalCharErrorMsg,specialChar.get(0),ErrorMessageType.SpiecalCharErrorMsg,20)) {
									appLog.info("Error Message is verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
								}else {
									appLog.error("Error Message is not verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for sub folder "+SHRDFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
								}
								if(sendKeys(driver, fp.getChildFolderNameTextBox(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20), SHRDFolderName[1], SHRDFolderName[0]+" sub folder text box", action.BOOLEAN)) {
									cssFlag=fp.clickUsingCssSelectorPath(cssSelectorPath, "Child Folder Save Button");
									if(cssFlag/*click(driver, fp.getChildFolderSaveButton(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20), "sub folder save button", action.BOOLEAN)*/) {
										ThreadSleep(2000);
										boolean isAlertPresent = isAlertPresent(driver);
										appLog.info("isAlertPresent  : "+isAlertPresent);
										if (true/*isAlertPresent(driver)*/) {
											appLog.info("Folder already exist alert pop up is successfully displayed");
											String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
											switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
											appLog.error("Error Message is displaying : "+msg);
											if(msg.contains(FundsPageErrorMessage.FolderAlreadyExistErrormsg)) {
												appLog.info("Error msg is verified successfully.");
											}else {
												appLog.error("Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
												sa.assertTrue(false, "Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
											}
										} else {
											appLog.error("Folder already exist alert pop up is not displayed for "+SHRDFolderName[0]+" folder");
											sa.assertTrue(false, "Folder already exist alert pop up is not displayed for "+SHRDFolderName[0]+" folder");
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
						id=fp.getCreatedFolderId(InternalFolderName[0], PageName.ManageFolderPopUp, 20);
						if(id!=null) {
							if(fp.clickOnAddFolderButton(id)) {
								if(fp.verifyFolderErrorMessageSubLevel(FolderType.Internal, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg,null,ErrorMessageType.BlankErrorMsg,20)) {
									appLog.info("Error Message is verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
								}else {
									appLog.error("Error Message is not verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
								}
								if(fp.verifyFolderErrorMessageSubLevel(FolderType.Internal, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.prefixErrorMsg,PrefixValue.get(2),ErrorMessageType.PrefixErrorMsg,20)) {
									appLog.info("Error Message is verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
								}else {
									appLog.error("Error Message is not verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
								}
								if(fp.verifyFolderErrorMessageSubLevel(FolderType.Internal, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.speicalCharErrorMsg,specialChar.get(0),ErrorMessageType.SpiecalCharErrorMsg,20)) {
									appLog.info("Error Message is verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
								}else {
									appLog.error("Error Message is not verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for sub folder "+InternalFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
								}
								if(sendKeys(driver, fp.getChildFolderNameTextBox(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20), InternalFolderName[1], InternalFolderName[0]+" sub folder text box", action.BOOLEAN)) {
									cssFlag=fp.clickUsingCssSelectorPath(cssSelectorPath, "Child Folder Save Button");
									if(cssFlag/*click(driver, fp.getChildFolderSaveButton(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20), "sub folder save button", action.BOOLEAN)*/) {
										ThreadSleep(2000);
										boolean isAlertPresent = isAlertPresent(driver);
										appLog.info("isAlertPresent  : "+isAlertPresent);
										if (true/*isAlertPresent(driver)*/) {
											appLog.info("Folder already exist alert pop up is successfully displayed");
											String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
											switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
											appLog.error("Error Message is displaying : "+msg);
											if(msg.contains(FundsPageErrorMessage.FolderAlreadyExistErrormsg)) {
												appLog.info("Error msg is verified successfully.");
											}else {
												appLog.error("Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
												sa.assertTrue(false, "Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
											}
										} else {
											appLog.error("Folder already exist alert pop up is not displayed for "+InternalFolderName[0]+" folder");
											sa.assertTrue(false, "Folder already exist alert pop up is not displayed for "+InternalFolderName[0]+" folder");
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
					}else {
						appLog.error("Not able to click on Manage folder Icon so cannot check error message in Investor workspace");
						sa.assertTrue(false, "Not able to click on Manage folder Icon so cannot check error message in Investor workspace");
					}
				}else {
					appLog.error(M5F2+" fund is not available so cannot continue with the tc.");
					sa.assertTrue(false,M5F2+" fund is not available so cannot continue with the tc.");
				}
		}else {
			appLog.error("Not able to click on Fund Tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on Fund Tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Test
	public void M5tc017_1_verifyEditFolderAndUpdateFolderNameInInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String STDFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		String[] stdFolderName=STDFolderName.split(",");
		String[] commonFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath).split(",");
		String[] InternalFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.InternalPath).split(",");
		String[] SHRDFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath).split(",");
		List<String> PrefixValue = getValueBasedOnLabel(filePath, "SpecialChar",excelLabel.INVALID_FOLDER_NAME, 1);
		List<String> specialChar = getValueBasedOnLabel(filePath, "SpecialChar",excelLabel.FOLDER_NAME, 1);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M5F2)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view.");
				if(click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 30), "Manage folder icon", action.BOOLEAN)){
						//String[] radioBtn= {STDFolderName,commonFolderName,SHRDFolderName,InternalFolderName};
						String id=null;
						id=fp.getCreatedFolderId(commonFolderName[0], PageName.ManageFolderPopUp, 20);
						System.err.println("id>>>>>>"+id);
						if(id!=null) {
							if(fp.clickOnRenameFolderButton(id)) {
								if(fp.verifyRenameFolderErrorMessageParentLevel(FolderType.Common, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg,"",ErrorMessageType.BlankErrorMsg,20)) {
									appLog.info("Error Message is verified for Parnet Level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
								}else {
									appLog.error("Error Message is not verified for parent Level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.PleaseEnterFolderNameErrorMsg);
								}
								for(int i=0; i<PrefixValue.size(); i++) {
									if(fp.verifyRenameFolderErrorMessageParentLevel(FolderType.Common, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.prefixErrorMsg,PrefixValue.get(i),ErrorMessageType.PrefixErrorMsg,20)) {
										appLog.info("Error Message is verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
									}else {
										appLog.error("Error Message is not verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
										sa.assertTrue(false, "Error Message is not verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.prefixErrorMsg);
									}
								}
								if(fp.verifyRenameFolderErrorMessageParentLevel(FolderType.Common, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,FundsPageErrorMessage.speicalCharErrorMsg,specialChar.get(0),ErrorMessageType.SpiecalCharErrorMsg,20)) {
									appLog.info("Error Message is verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
								}else {
									appLog.error("Error Message is not verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
									sa.assertTrue(false, "Error Message is not verified for Parent level Rename folder "+commonFolderName[0]+" : "+FundsPageErrorMessage.speicalCharErrorMsg);
								}
								
								if(click(driver, fp.getParentRenameFolderCrossIcon(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20), "rename parent folder cross icon", action.BOOLEAN)) {
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
							appLog.error(commonFolderName[0]+" folder is available in manage folder so cannot check error messgaes on Parent Rename Common folder");
							sa.assertTrue(false, stdFolderName[0]+" folder is available in manage folder so cannot check error messgaes on Parent Rename Common folder");
						}
						String[] ss= {commonFolderName[0]+"/"+commonFolderName[1],SHRDFolderName[0]+"/"+SHRDFolderName[1],InternalFolderName[0]+"/"+InternalFolderName[1],stdFolderName[0]+"/"+stdFolderName[1]};
						String[] ss1= {commonFolderName[1],SHRDFolderName[1],InternalFolderName[1],stdFolderName[1]};
						String[] ss2= {commonFolderName[2],SHRDFolderName[2],InternalFolderName[2],stdFolderName[2]};
						for(int i=0; i<ss.length; i++) {
							id=null;
							id=fp.getCreatedFolderId(ss[i], PageName.ManageFolderPopUp, 20);
							System.err.println("Folder Id is: >>>>>>"+id);
							if(id!=null) {
								if(fp.clickOnRenameFolderButton(id)) {
									ThreadSleep(2000);
									if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20), ss2[i], ss1[i]+" sub folder text box", action.BOOLEAN)) {
										String cssSelectorPath="a[onclick*='Save_Rename_folder_pop1investor();']";
										boolean cssFlag = fp.clickUsingCssSelectorPath(cssSelectorPath, "Parent Rename Folder Save Button");
										if(cssFlag/*click(driver, fp.getParentRenameFolderSaveButton(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20), "sub folder save button", action.BOOLEAN)*/) {
											ThreadSleep(2000);
											boolean isAlertPresent = isAlertPresent(driver);
											appLog.info("isAlertPresent  : "+isAlertPresent);
											if (true/*isAlertPresent(driver)*/) {
												appLog.info("Folder already exist alert pop up is successfully displayed");
												String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
												switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
												appLog.error("Error Message is displaying : "+msg);
												if(msg.contains(FundsPageErrorMessage.FolderAlreadyExistErrormsg)) {
													appLog.info("Error msg is verified successfully.");
													if(click(driver, fp.getParentRenameFolderCrossIcon(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20), "rename folder pop up cross icon", action.BOOLEAN)) {
														appLog.info("clicked on Cross icon");
													}else {
														appLog.error("Not able to click on rename folder "+ss1[i]+" cross icon so cannot close rename folder pop up and cannot check Error Message for other folders");
														sa.assertTrue(false, "Not able to click on rename folder "+ss1[i]+" cross icon so cannot close rename folder pop up and cannot check Error Message for other folders");
														break;
													}
												}else {
													appLog.error("Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
													sa.assertTrue(false, "Error Message is not matched. Expected: "+FundsPageErrorMessage.FolderAlreadyExistErrormsg+" /t Actual: "+msg);
												}
											} else {
												appLog.error("Folder already exist alert pop up is not displayed for "+ss1[i]+" folder");
												sa.assertTrue(false, "Folder already exist alert pop up is not displayed for "+ss1[i]+" folder");
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
								appLog.error(ss1[i]+" folder is available in manage folder so cannot check error messgaes on sub Common folder");
								sa.assertTrue(false, ss1[i]+" folder is available in manage folder so cannot check error messgaes on sub Common folder");
							}
							
						}
						for(int i=0; i<2; i++) {
							id=null;
							id=fp.getCreatedFolderId(commonFolderName[0], PageName.ManageFolderPopUp, 20);
							System.err.println("id>>>>>>"+id);
							if(id!=null) {
								if(fp.clickOnRenameFolderButton(id)) {
									if(i==0) {
										if(fp.getParentRenameFolderNameTextBox(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20)!=null) {
											appLog.info("Rename Folder pop up is open");
										}else {
											appLog.error("Rename folder Pop up is not open");
											sa.assertTrue(false, "Rename folder Pop up is not open");
										}
										String prefillValue=null;
										prefillValue= getValueFromElementUsingJavaScript(driver,fp.getParentRenameFolderNameTextBox(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20),"");
										if(prefillValue!=null) {
											if(prefillValue.trim().equalsIgnoreCase(commonFolderName[0].split(" ")[0])) {
												appLog.info(commonFolderName[0].split(" ")[0]+" value is displaying in rename folder pop up");
											}else {
												appLog.error(commonFolderName[0].split(" ")[0]+" value is not displaying in rename folder pop up");
												sa.assertTrue(false, commonFolderName[0].split(" ")[0]+" value is not displaying in rename folder pop up");
											}
										}else {
											appLog.error("Rename Folder text box is not available so cannot get value from rename folder text box");
											sa.assertTrue(false, "Rename Folder text box is not available so cannot get value from rename folder text box");
										}
										if(fp.getParentRenameFolderCrossIcon(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 10)!=null) {
											appLog.info("Cross icon is visible in rename folder pop up");
										}else {
											appLog.error("Cross icon is not visible in rename folder pop up");
											sa.assertTrue(false, "Cross icon is not visible in rename folder pop up");
										}
										if(fp.getParentRenameFolderSaveButton(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 10)!=null) {
											appLog.info("Save is visible in rename folder pop up");
										}else {
											appLog.error("Save is not visible in rename folder pop up");
											sa.assertTrue(false, "Save is not visible in rename folder pop up");
										}
										if(fp.getParentRenameFolderCancelButton(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 10)!=null) {
											appLog.info("Cancel button is visible in rename folder pop up");
										}else {
											appLog.error("Cancel button is not visible in rename folder pop up");
											sa.assertTrue(false, "Cancel button is not visible in rename folder pop up");
										}
									}
									WebElement ele=null;
									String aa= null;
									if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,20),"","rename parent folder text box", action.BOOLEAN)) {
										if(i==0) {
											 ele=fp.getParentRenameFolderCancelButton(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 10);
											 aa="Cancel Button";
										}else {
											 ele= fp.getParentRenameFolderCrossIcon(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 10);
											 aa="Cross Icon";
										}
										if(ele!=null) {
											if(click(driver,ele ,"rename parent folder "+aa+"", action.BOOLEAN)) {
												appLog.info("clicked on "+aa+"");
												if(fp.getCreatedFolderId(commonFolderName[0], PageName.ManageFolderPopUp, 20)!=null) {
													appLog.info(commonFolderName[0]+" is available in manage folder after click on "+aa+"");
												}else {
													appLog.error("Created folder is not available in manage folder: "+commonFolderName[0]);
													sa.assertTrue(false, "Created folder is not available in manage folder: "+commonFolderName[0]);
												}
											}else {
												appLog.error("Not able to click on common rename parent folder "+aa+"so cannot check folder name in manage folder after click on "+aa+"");
												sa.assertTrue(false, "Not able to click on common rename parent folder "+aa+" so cannot check folder name in manage folder after click on "+aa+"");
											}
										}else {
											appLog.error(aa+" is not visible so cannot click on "+aa+" so cannot check folder name in manage folder after click on "+aa+"");
											sa.assertTrue(false, aa+" is not visible so cannot click on "+aa+" so cannot check folder name in manage folder after click on "+aa+"");
										}
										
									}else {
										appLog.error("Not able to blank "+commonFolderName[0]+" rename folder text box so cannot check folder name in manage folder after click on cancel button");
										sa.assertTrue(false, "Not able to blank "+commonFolderName[0]+" rename folder text box so cannot check folder name in manage folder after click on cancel button");
									}	
									
								}else {
									appLog.error("Not able to click on Rename folder Parent folder "+commonFolderName[0]+" so cannot check error message on "+commonFolderName[0]+" Parent folder");
									sa.assertTrue(false, "Not able to click on Rename folder Parent folder Rename Icon  "+commonFolderName[0]+" so cannot check error message on "+commonFolderName[0]+" Parent folder");
								}
							}else {
								appLog.error(commonFolderName[0]+" folder is available in manage folder so cannot check error messgaes on Parent Rename Common folder");
								sa.assertTrue(false, stdFolderName[0]+" folder is available in manage folder so cannot check error messgaes on Parent Rename Common folder");
							}
						}
						String[] folders= {commonFolderName[0],SHRDFolderName[0],InternalFolderName[0],stdFolderName[0]};
						String [] Names= {commonFolderName[0].split(" ")[0],SHRDFolderName[0].split(" ")[0],InternalFolderName[0].split(" ")[0],stdFolderName[0].split(" ")[0]};
						for(int i=0; i<folders.length; i++) {
							id=null;
							id=fp.getCreatedFolderId(folders[i], PageName.ManageFolderPopUp, 20);
							System.err.println("id>>>>>>"+id);
							if(id!=null) {
								if(fp.clickOnRenameFolderButton(id)) {
									if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,20),"Updated"+Names[i],folders[i]+" parent rename folder text box", action.BOOLEAN)) {
										if(click(driver,fp.getParentRenameFolderSaveButton(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20) ,"rename parent folder save button", action.BOOLEAN)) {
											appLog.info("clicked on parent rename folder save button");
											ThreadSleep(2000);
											if(fp.getCreatedFolderId("Updated"+folders[i], PageName.ManageFolderPopUp, 20)!=null) {
												appLog.info("Updated"+folders[i]+" is available in manage folder");
												if(i==0) {
													ExcelUtils.writeData("Updated"+Names[i]+" (Common)","FilePath", excelLabel.TestCases_Name, "M5tc017_verifyEditUpdatedFolder",excelLabel.CommonPath);
												}if(i==1) {
													ExcelUtils.writeData("Updated"+Names[i]+" (Shared)","FilePath", excelLabel.TestCases_Name, "M5tc017_verifyEditUpdatedFolder",excelLabel.SharedPath);
												}if(i==2) {
													ExcelUtils.writeData("Updated"+Names[i]+" (Internal)","FilePath", excelLabel.TestCases_Name, "M5tc017_verifyEditUpdatedFolder",excelLabel.InternalPath);
												}if(i==3) {
													ExcelUtils.writeData("Updated"+Names[i],"FilePath", excelLabel.TestCases_Name, "M5tc017_verifyEditUpdatedFolder",excelLabel.StandardPath);
												}
											}else {
												appLog.error("Updated"+folders[i]+" is not available in manage folder");
												sa.assertTrue(false, "Updated"+folders[i]+" is not available in manage folder");
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
								appLog.error(folders[i]+" folder is not available in manage folder so cannot Update folder Name");
								sa.assertTrue(false, folders[i]+" folder is not available in manage folder so cannot Update folder Name");
							}
							
						}
						String updateCommonFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc017_verifyEditUpdatedFolder",excelLabel.CommonPath);
						String updateSharedFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc017_verifyEditUpdatedFolder",excelLabel.SharedPath);
						String updateInternalFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc017_verifyEditUpdatedFolder",excelLabel.InternalPath);
						String updateSTDFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc017_verifyEditUpdatedFolder",excelLabel.StandardPath);
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
								id=fp.getCreatedFolderId(lst.get(i), PageName.ManageFolderPopUp, 20);
								System.err.println("id>>>>>>"+id);
								if(id!=null) {
									if(fp.clickOnRenameFolderButton(id)) {
										if(sendKeys(driver, fp.getParentRenameFolderNameTextBox(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp,20),"Updated"+lst.get(i).split("/")[1],lst.get(i).split("/")[0]+" parent rename folder text box", action.BOOLEAN)) {
											if(click(driver,fp.getParentRenameFolderSaveButton(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20) ,lst.get(i).split("/")[0]+"rename sub folder save button", action.BOOLEAN)) {
												appLog.info("clicked on parent rename folder save button");
												ThreadSleep(2000);
												if(fp.getCreatedFolderId(lst.get(i).split("/")[0]+"/Updated"+lst.get(i).split("/")[1], PageName.ManageFolderPopUp, 20)!=null) {
													appLog.info(lst.get(i).split("/")[0]+"/Updated"+lst.get(i).split("/")[1]+" is available in manage folder");
													System.err.println("List >>>>>"+lst.get(i).trim()+" and size"+lst.size());
													System.err.println("Updated Common >>"+updateCommonFolder);
													System.err.println("Updated Internal >>"+updateInternalFolder);
													System.err.println("Updated shared >>"+updateSharedFolder);
													System.err.println("Updated STD >>"+updateSTDFolder);
													
													if(!updateCommonFolder.isEmpty() && lst.get(i).trim().contains(updateCommonFolder)) {
														ExcelUtils.writeData(lst.get(i).split("/")[0]+"/Updated"+lst.get(i).split("/")[1],"FilePath", excelLabel.TestCases_Name, "M5tc017_verifyEditUpdatedChildFolder",excelLabel.CommonPath);
													}else if(!updateInternalFolder.isEmpty() && lst.get(i).trim().contains(updateInternalFolder)) {
														ExcelUtils.writeData(lst.get(i).split("/")[0]+"/Updated"+lst.get(i).split("/")[1],"FilePath", excelLabel.TestCases_Name, "M5tc017_verifyEditUpdatedChildFolder",excelLabel.InternalPath);
													}else if(!updateSharedFolder.isEmpty() && lst.get(i).trim().contains(updateSharedFolder)) {
														ExcelUtils.writeData(lst.get(i).split("/")[0]+"/Updated"+lst.get(i).split("/")[1],"FilePath", excelLabel.TestCases_Name, "M5tc017_verifyEditUpdatedChildFolder",excelLabel.SharedPath);
													}else if(!updateSTDFolder.isEmpty() && lst.get(i).trim().contains(updateSTDFolder)) {
														ExcelUtils.writeData(lst.get(i).split("/")[0]+"/Updated"+lst.get(i).split("/")[1],"FilePath", excelLabel.TestCases_Name, "M5tc017_verifyEditUpdatedChildFolder",excelLabel.StandardPath);
													}
												}else {
													appLog.error(lst.get(i).split("/")[0]+"/Updated"+lst.get(i).split("/")[1]+" is not available in manage folder");
													sa.assertTrue(false, lst.get(i).split("/")[0]+"/Updated"+lst.get(i).split("/")[1]+" is not available in manage folder");
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
									appLog.error(lst.get(i)+" folder is not available in manage folder so cannot Update folder Name");
									sa.assertTrue(false, lst.get(i)+" folder is not available in manage folder so cannot Update folder Name");
								}
								
							}
						}else {
							appLog.error("Updated Folder Names is not available in the filepath excel sheet so cannot update Child folder Names");
							sa.assertTrue(false, "Updated Folder Names is not available in the filepath excel sheet so cannot update Child folder Names");
						}
				}else {
					appLog.error("Not able to click on Manage folder Icon so cannot check error message in Investor workspace");
					sa.assertTrue(false, "Not able to click on Manage folder Icon so cannot check error message in Investor workspace");
				}
		}else {
			appLog.error(M5F2+" fund is not available so cannot continue with the tc.");
			sa.assertTrue(false,M5F2+" fund is not available so cannot continue with the tc.");
		}
		
		}else {
			appLog.error("Not able to click on Fund Tab so cannot continue with the tc");
			sa.assertTrue(false, "Not able to click on Fund Tab so cannot continue with the tc");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Test
	public void M5tc017_2_verifyUpdateFolderNameOnInstitutionAndContactPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ins = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer contact= new ContactPageBusinessLayer(driver);
		CommitmentPageBusinessLayer com = new CommitmentPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		String CommonPath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc017_verifyEditUpdatedChildFolder",excelLabel.CommonPath);
		String InternalPath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc017_verifyEditUpdatedChildFolder",excelLabel.InternalPath);
		String SHRDPath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc017_verifyEditUpdatedChildFolder",excelLabel.SharedPath);
		String STDPath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc017_verifyEditUpdatedChildFolder",excelLabel.StandardPath);
		if(fp.clickOnTab(TabName.InstituitonsTab)) {
			if(ins.clickOnCreatedInstitution(M5I1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.InstitutionsPage, 30));
				if(!CommonPath.isEmpty() && !CommonPath.equalsIgnoreCase("CommonPath")) {
					if(fp.verifyFolderPathdummy(CommonPath, null, null, M5F2, PageName.InstitutionsPage,Workspace.InvestorWorkspace, 60)) {
						appLog.info(CommonPath+" is verified on institution page: "+M5I1);
					}else {
						appLog.error(CommonPath+" is not verified on institution page: "+M5I1);
						sa.assertTrue(false, CommonPath+" is not verified on institution page: "+M5I1);
					}
				}else {
					appLog.error("CommonPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for common folder on Institution Page");
					sa.assertTrue(false, "CommonPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for common folder on Institution Page");
				}
				if(!InternalPath.isEmpty() && !InternalPath.equalsIgnoreCase("InternalPath")) {
					if(fp.verifyFolderPathdummy(InternalPath, null, null, M5F2, PageName.InstitutionsPage,Workspace.InvestorWorkspace, 60)) {
						appLog.info(InternalPath+" is verified on institution page: "+M5I1);
					}else {
						appLog.error(InternalPath+" is not verified on institution page: "+M5I1);
						sa.assertTrue(false, InternalPath+" is not verified on institution page: "+M5I1);
					}
				}else {
					appLog.error("InternalPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for Internal folder on Institution Page");
					sa.assertTrue(false, "InternalPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for Internal folder on Institution Page");
				}
				if(!SHRDPath.isEmpty() && !SHRDPath.equalsIgnoreCase("SharedPath")) {
					if(fp.verifyFolderPathdummy(SHRDPath, null, null, M5F2, PageName.InstitutionsPage,Workspace.InvestorWorkspace, 60)) {
						appLog.info(SHRDPath+" is verified on institution page: "+M5I1);
					}else {
						appLog.error(SHRDPath+" is not verified on institution page: "+M5I1);
						sa.assertTrue(false, SHRDPath+" is not verified on institution page: "+M5I1);
					}
				}else {
					appLog.error("SharedPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for Shared folder on Institution Page");
					sa.assertTrue(false, "Shared column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for Shared folder on Institution Page");
				}
				if(!STDPath.isEmpty() && !STDPath.equalsIgnoreCase("StandardPath")) {
					if(fp.verifyFolderPathdummy(STDPath, null, M5LP1, M5F2, PageName.InstitutionsPage,Workspace.InvestorWorkspace, 60)) {
						appLog.info(STDPath+" is verified on institution page: "+M5I1);
					}else {
						appLog.error(STDPath+" is not verified on institution page: "+M5I1);
						sa.assertTrue(false, STDPath+" is not verified on institution page: "+M5I1);
					}
				}else {
					appLog.error("StandardPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for Standard folder on Institution Page");
					sa.assertTrue(false, "StandardPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for Standard folder on Institution Page");
				}
			}else {
				appLog.error("Not able to click on "+M5I1+" institution Name so cannot check Updated Folder Names");
				sa.assertTrue(false, "Not able to click on "+M5I1+" institution Name so cannot check Updated Folder Names");
			}
		}else {
			appLog.error("Not able to click on institution tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on institution tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		if(fp.clickOnTab(TabName.InstituitonsTab)) {
			if(ins.clickOnCreatedLP(environment, mode,M5LP1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.LimitedPartnerPage, 30));
				if(!CommonPath.isEmpty() && !CommonPath.equalsIgnoreCase("CommonPath")) {
					if(fp.verifyFolderPathdummy(CommonPath, null, null, M5F2, PageName.InstitutionsPage,Workspace.InvestorWorkspace, 60)) {
						appLog.info(CommonPath+" is verified on LP page: "+M5I1);
					}else {
						appLog.error(CommonPath+" is not verified on LP page: "+M5I1);
						sa.assertTrue(false, CommonPath+" is not verified on LP page: "+M5I1);
					}
				}else {
					appLog.error("CommonPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for common folder on LP Page");
					sa.assertTrue(false, "CommonPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for common folder on LP Page");
				}
				if(!InternalPath.isEmpty() && !InternalPath.equalsIgnoreCase("InternalPath")) {
					if(fp.verifyFolderPathdummy(InternalPath, null, null, M5F2, PageName.InstitutionsPage,Workspace.InvestorWorkspace, 60)) {
						appLog.info(InternalPath+" is verified on LP page: "+M5I1);
					}else {
						appLog.error(InternalPath+" is not verified on LP page: "+M5I1);
						sa.assertTrue(false, InternalPath+" is not verified on LP page: "+M5I1);
					}
				}else {
					appLog.error("InternalPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for Internal folder on LP Page");
					sa.assertTrue(false, "InternalPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for Internal folder on LP Page");
				}
				if(!SHRDPath.isEmpty() && !SHRDPath.equalsIgnoreCase("SharedPath")) {
					if(fp.verifyFolderPathdummy(SHRDPath, null, null, M5F2, PageName.InstitutionsPage,Workspace.InvestorWorkspace, 60)) {
						appLog.info(SHRDPath+" is verified on LP page: "+M5I1);
					}else {
						appLog.error(SHRDPath+" is not verified on LP page: "+M5I1);
						sa.assertTrue(false, SHRDPath+" is not verified on LP page: "+M5I1);
					}
				}else {
					appLog.error("SharedPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for Shared folder on LP Page");
					sa.assertTrue(false, "Shared column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for Shared folder on LP Page");
				}
				if(!STDPath.isEmpty() && !STDPath.equalsIgnoreCase("StandardPath")) {
					if(fp.verifyFolderPathdummy(STDPath, null, null, M5F2, PageName.InstitutionsPage,Workspace.InvestorWorkspace, 60)) {
						appLog.info(STDPath+" is verified on LP page: "+M5I1);
					}else {
						appLog.error(STDPath+" is not verified on LP page: "+M5I1);
						sa.assertTrue(false, STDPath+" is not verified on LP page: "+M5I1);
					}
				}else {
					appLog.error("StandardPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for Standard folder on LP Page");
					sa.assertTrue(false, "StandardPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for Standard folder on LP Page");
				}
			}else {
				appLog.error("Not able to click on "+M5LP1+" LP Name so cannot check Updated Folder Names");
				sa.assertTrue(false, "Not able to click on "+M5LP1+" LP Name so cannot check Updated Folder Names");
			}
		}else {
			appLog.error("Not able to click on institution tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on institution tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		if(fp.clickOnTab(TabName.ContactTab)) {
			if(contact.clickOnCreatedContact(M5CFN1, M5CLN1, null)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.ContactsPage, 30));
				if(!CommonPath.isEmpty() && !CommonPath.equalsIgnoreCase("CommonPath")) {
					if(fp.verifyFolderPathdummy(CommonPath, null, null, M5F2, PageName.ContactsPage,Workspace.InvestorWorkspace, 60)) {
						appLog.info(CommonPath+" is verified on Contact page: "+M5I1);
					}else {
						appLog.error(CommonPath+" is not verified on Contact page: "+M5I1);
						sa.assertTrue(false, CommonPath+" is not verified on Contact page: "+M5I1);
					}
				}else {
					appLog.error("CommonPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for common folder on Contact Page");
					sa.assertTrue(false, "CommonPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for common folder on Contact Page");
				}
				if(!SHRDPath.isEmpty() && !SHRDPath.equalsIgnoreCase("SharedPath")) {
					if(fp.verifyFolderPathdummy(SHRDPath, null, null, M5F2, PageName.ContactsPage,Workspace.InvestorWorkspace, 60)) {
						appLog.info(SHRDPath+" is verified on Contact page: "+M5I1);
					}else {
						appLog.error(SHRDPath+" is not verified on Contact page: "+M5I1);
						sa.assertTrue(false, SHRDPath+" is not verified on Contact page: "+M5I1);
					}
				}else {
					appLog.error("SharedPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for Shared folder on Contact Page");
					sa.assertTrue(false, "Shared column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for Shared folder on Contact Page");
				}
				if(!STDPath.isEmpty() && !STDPath.equalsIgnoreCase("StandardPath")) {
					if(fp.verifyFolderPathdummy(STDPath, M5I1, M5LP1, M5F2, PageName.ContactsPage,Workspace.InvestorWorkspace, 60)) {
						appLog.info(STDPath+" is verified on Contact page: "+M5I1);
					}else {
						appLog.error(STDPath+" is not verified on Contact page: "+M5I1);
						sa.assertTrue(false, STDPath+" is not verified on Contact page: "+M5I1);
					}
				}else {
					appLog.error("StandardPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for Standard folder on Contact Page");
					sa.assertTrue(false, "StandardPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for Standard folder on Contact Page");
				}
			}else {
				appLog.error("Not able to click on "+M5I1+" Contact Name so cannot check Updated Folder Names");
				sa.assertTrue(false, "Not able to click on "+M5I1+" Contact Name so cannot check Updated Folder Names");
			}
		}else {
			appLog.error("Not able to click on Contact tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on Contact tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		if(fp.clickOnTab(TabName.CommitmentsTab)) {
			if(com.clickOnCreatedCommitmentId(environment, mode,M5CMT2)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.CommitmentsPage, 30));
				if(!CommonPath.isEmpty() && !CommonPath.equalsIgnoreCase("CommonPath")) {
					if(fp.verifyFolderPathdummy(CommonPath, null, null, M5F2, PageName.CommitmentsPage,Workspace.InvestorWorkspace, 60)) {
						appLog.info(CommonPath+" is verified on Commitment page: "+M5LP1);
					}else {
						appLog.error(CommonPath+" is not verified on Commitment page: "+M5LP1);
						sa.assertTrue(false, CommonPath+" is not verified on Commitment page: "+M5LP1);
					}
				}else {
					appLog.error("CommonPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for common folder on Commitment Page");
					sa.assertTrue(false, "CommonPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for common folder on Commitment Page");
				}
				if(!InternalPath.isEmpty() && !InternalPath.equalsIgnoreCase("InternalPath")) {
					if(fp.verifyFolderPathdummy(InternalPath, null, null, M5F2, PageName.CommitmentsPage,Workspace.InvestorWorkspace, 60)) {
						appLog.info(InternalPath+" is verified on Commitment page: "+M5LP1);
					}else {
						appLog.error(InternalPath+" is not verified on Commitment page: "+M5LP1);
						sa.assertTrue(false, InternalPath+" is not verified on Commitment page: "+M5LP1);
					}
				}else {
					appLog.error("InternalPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for Internal folder on Commitment Page");
					sa.assertTrue(false, "InternalPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for Internal folder on Commitment Page");
				}
				if(!SHRDPath.isEmpty() && !SHRDPath.equalsIgnoreCase("SharedPath")) {
					if(fp.verifyFolderPathdummy(SHRDPath, null, null, M5F2, PageName.CommitmentsPage,Workspace.InvestorWorkspace, 60)) {
						appLog.info(SHRDPath+" is verified on Commitment page: "+M5LP1);
					}else {
						appLog.error(SHRDPath+" is not verified on Commitment page: "+M5LP1);
						sa.assertTrue(false, SHRDPath+" is not verified on Commitment page: "+M5LP1);
					}
				}else {
					appLog.error("SharedPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for Shared folder on Commitment Page");
					sa.assertTrue(false, "Shared column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for Shared folder on Commitment Page");
				}
				if(!STDPath.isEmpty() && !STDPath.equalsIgnoreCase("StandardPath")) {
					if(fp.verifyFolderPathdummy(STDPath, null, M5LP1, null, PageName.CommitmentsPage,Workspace.InvestorWorkspace, 60)) {
						appLog.info(STDPath+" is verified on Commitment page: "+M5LP1);
					}else {
						appLog.error(STDPath+" is not verified on Commitment page: "+M5LP1);
						sa.assertTrue(false, STDPath+" is not verified on Commitment page: "+M5LP1);
					}
				}else {
					appLog.error("StandardPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for Standard folder on Commitment Page");
					sa.assertTrue(false, "StandardPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for Standard folder on Commitment Page");
				}
			}else {
				appLog.error("Not able to click on "+M5CMT2+" Commitment id so cannot check Updated Folder Names");
				sa.assertTrue(false, "Not able to click on "+M5CMT2+" Commitment id so cannot check Updated Folder Names");
			}
		}else {
			appLog.error("Not able to click on Commitment tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on Commitment tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Test
	public void M5tc017_3_verifyUpdatedFolderNameAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.investorLogin(M5C1Email,adminPassword);
		if(ivp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			String CommonPath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc017_verifyEditUpdatedChildFolder",excelLabel.CommonPath);
			String SHRDPath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc017_verifyEditUpdatedChildFolder",excelLabel.SharedPath);
			String STDPath=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc017_verifyEditUpdatedChildFolder",excelLabel.StandardPath);
			if(!CommonPath.isEmpty() && !CommonPath.equalsIgnoreCase("CommonPath")) {
				if(fp.verifyFolderPathdummy(CommonPath, null, null, null, PageName.CurrentInvestmentPgae,null, 60)) {
					appLog.info(CommonPath+" is verified on Investor Side: "+M5CFN1+" "+M5CLN2);
				}else {
					appLog.error(CommonPath+" is not verified on Investor Side: "+M5CFN1+" "+M5CLN2);
					sa.assertTrue(false, CommonPath+" is verified on Investor Side: "+M5CFN1+" "+M5CLN2);
				}
			}else {
				appLog.error("CommonPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for common folder on Investor side");
				sa.assertTrue(false, "CommonPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for common folder on Investor side");
			}
			if(!SHRDPath.isEmpty() && !SHRDPath.equalsIgnoreCase("SharedPath")) {
				if(fp.verifyFolderPathdummy(SHRDPath, null, null, null, PageName.CurrentInvestmentPgae,null, 60)) {
					appLog.info(SHRDPath+" is verified on Investor Side: "+M5CFN1+" "+M5CLN2);
				}else {
					appLog.error(SHRDPath+" is not verified on Investor Side: "+M5CFN1+" "+M5CLN2);
					sa.assertTrue(false, SHRDPath+" is verified on Investor Side: "+M5CFN1+" "+M5CLN2);
				}
			}else {
				appLog.error("SharedPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for Shared folder on Investor side");
				sa.assertTrue(false, "SharedPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for Shared folder on Investor side");
			}
			if(!STDPath.isEmpty() && !STDPath.equalsIgnoreCase("StandardPath")) {
				if(fp.verifyFolderPathdummy(STDPath, M5I1, M5LP1, null, PageName.CurrentInvestmentPgae,null, 60)) {
					appLog.info(SHRDPath+" is verified on Investor Side: "+M5CFN1+" "+M5CLN2);
				}else {
					appLog.error(SHRDPath+" is not verified on Investor Side: "+M5CFN1+" "+M5CLN2);
					sa.assertTrue(false, SHRDPath+" is verified on Investor Side: "+M5CFN1+" "+M5CLN2);
				}
			}else {
				appLog.error("StandardPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for Standard folder on Investor side");
				sa.assertTrue(false, "StandardPath column is empty in file path excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check updated folder structure for Standard folder on Investor side");
			}
		}else {
			appLog.error("Not able to click on Current Investment tab so cannot check Updated Folder Name");
			sa.assertTrue(false, "Not able to click on Current Investment tab so cannot check Updated Folder Name");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M5tc018_1_verifyDeleteFolderInInvestorWorkspace() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String commonFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc017_verifyEditUpdatedChildFolder",excelLabel.CommonPath);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M5F2)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view.");
				if(click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 30), "Manage folder icon", action.BOOLEAN)){
					if(!commonFolderName.isEmpty() && !commonFolderName.equalsIgnoreCase("Commonpath")) {
						String[] SplitedcommonFolderName=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc017_verifyEditUpdatedChildFolder",excelLabel.CommonPath).split("/");
						for(int i=0; i<2; i++) {
							String id=null;
							id=fp.getCreatedFolderId(commonFolderName, PageName.ManageFolderPopUp, 20);
							System.err.println("id>>>>>>"+id);
							if(id!=null) {
								if(fp.clickOnDeleteFolderButton(id)) {
									if(i==0) {
									if(fp.getFolderDeleteErrorMsg1(Workspace.InvestorWorkspace,PageName.ManageFolderPopUp, 20)!=null) {
										if(fp.getFolderDeleteErrorMsg1(Workspace.InvestorWorkspace,PageName.ManageFolderPopUp, 10).getText().trim().contains(FundsPageErrorMessage.deleteFolderErrorMsg1)) {
											appLog.info(FundsPageErrorMessage.deleteFolderErrorMsg1+" Folder delete message is verified. ");
										}else {
											appLog.error(FundsPageErrorMessage.deleteFolderErrorMsg1+" folder delete message is not verified");
											sa.assertTrue(false, FundsPageErrorMessage.deleteFolderErrorMsg1+" folder delete message is not verified");
										}
									}else {
										appLog.error("Folder delete error message is not visible");
										sa.assertTrue(false, "Folder delete error message is not visible");
									}
									if(fp.getFolderDeleteErrorMsg2(Workspace.InvestorWorkspace, 20)!=null) {
										if(fp.getFolderDeleteErrorMsg2(Workspace.InvestorWorkspace, 10).getText().trim().contains(FundsPageErrorMessage.deleteFolderErrorMsg2)) {
											appLog.info(FundsPageErrorMessage.deleteFolderErrorMsg2+" Folder delete message is verified. ");
										}else {
											appLog.error(FundsPageErrorMessage.deleteFolderErrorMsg2+" folder delete message is not verified");
											sa.assertTrue(false, FundsPageErrorMessage.deleteFolderErrorMsg2+" folder delete message is not verified");
										}
									}else {
										appLog.error("Folder delete error message is not visible");
										sa.assertTrue(false, "Folder delete error message is not visible");
									}
									if(fp.getFolderDeleteYesBtn(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 20)!=null) {
										appLog.info("Yes button is displaying on folder delete pop up");
									}else {
										appLog.error("Yes button is displaying on folder delete pop up");
										sa.assertTrue(false, "Yes button is displaying on folder delete pop up");
									}
										if(click(driver, fp.getFolderDeleteNoBtn(Workspace.InvestorWorkspace,PageName.ManageFolderPopUp, 10), "folder delete no Button", action.BOOLEAN)) {
											appLog.info("No button is displaying on folder delete pop up");
											appLog.info("clicked on folder delete No Button");
										}else {
											appLog.error("No button is not displaying on folder delete pop up so cannot click on No button");
											sa.assertTrue(false, "No button is not displaying on folder delete pop up so cannot click on No button");
										}
									}
									if(i==1) {
										if(click(driver, fp.getFolderDeleteCrossIcon(Workspace.InvestorWorkspace,PageName.ManageFolderPopUp, 10), "folder delete cross icon", action.SCROLLANDBOOLEAN)) {
											appLog.info("Cross Icon is displaying on folder delete pop up");
											appLog.info("clicked on folder delete cross icon");
										}else {
											appLog.error("Cross Icon is not displaying on folder delete pop up so cannot click on cross icon");
											sa.assertTrue(false, "Cross Icon is displaying on folder delete pop up so cannot click on cross icon");
										}
									}
								}else {
									appLog.error("Not able to click on folder: "+SplitedcommonFolderName[1]+" delete icon so cannot check delete folder functionality");
									sa.assertTrue(false, "Not able to click on folder: "+SplitedcommonFolderName[1]+" delete icon so cannot check delete folder functionality");
								}
							}else {
								appLog.error(SplitedcommonFolderName[1]+" is not available in the manage folder structure so cannot click on folder "+SplitedcommonFolderName[1]+" delete icon");
								sa.assertTrue(false, SplitedcommonFolderName[1]+" is not available in the manage folder structure so cannot click on folder "+SplitedcommonFolderName[1]+" delete icon");
							}
						}
					}else {
						appLog.error("Update Commonpath is empty in excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check delete folder functionality on Sub Common Folder");
						sa.assertTrue(false, "Update Commonpath is empty in excel sheet in test case M5tc017_verifyEditUpdatedChildFolder so cannot check delete folder functionality on Sub Common Folder");
					}
					
					String[] updateCommonFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath).split(",");
					String updateSharedFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath);
					String updateInternalFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.InternalPath);
					String updateSTDFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
					String[] listOfFolders= {updateCommonFolder[0],updateCommonFolder[1],updateInternalFolder,updateSharedFolder,updateSTDFolder};
					for(int i=0; i<listOfFolders.length; i++) {
						String id=null;
						id=fp.getCreatedFolderId(listOfFolders[i], PageName.ManageFolderPopUp, 20);
						System.err.println("id>>>>>>"+id);
						if(id!=null) {
							if(fp.clickOnDeleteFolderButton(id)) {
								if(click(driver, fp.getFolderDeleteYesBtn(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 10), "folder delete yes Button", action.BOOLEAN)) {
									appLog.info("clicked on folder delete yes Button");
									ThreadSleep(2000);
									if(fp.getCreatedFolderId(listOfFolders[i], PageName.ManageFolderPopUp, 10)!=null) {
										appLog.info(listOfFolders[i]+" is not deleted, folder is visible in manage foler pop up");
										sa.assertTrue(false, listOfFolders[i]+" is not deleted, folder is visible in manage foler pop up");
									}else {
										appLog.error(listOfFolders[i]+" is deleted successfully, it is not visible in manage foler pop up");
									}
								}else {
									appLog.error("Yes button is not displaying on folder delete pop up so cannot click on Yes button");
									sa.assertTrue(false, "yes button is not displaying on folder delete pop up so cannot click on yes button");
								}
							}else {
								appLog.error("Not able to click on folder: "+listOfFolders[i]+" delete icon so cannot check delete folder functionality");
								sa.assertTrue(false, "Not able to click on folder: "+listOfFolders[i]+" delete icon so cannot check delete folder functionality");
							}
						}else {
							appLog.error(updateSTDFolder+" is not available in the manage folder structure so cannot click on folder "+listOfFolders[i]+" delete icon");
							sa.assertTrue(false, updateSTDFolder+" is not available in the manage folder structure so cannot click on folder "+listOfFolders[i]+" delete icon");
						}
					}
				}else {
					appLog.error("Not able to click on Manage folder icon so cannot check delete folder functionality");
					sa.assertTrue(false, "Not able to click on Manage folder icon so cannot check delete folder functionality");
				}
			}else {
				appLog.error("Not able to click on Fund: "+M5F2+" so cannot check delete folder functionality");
				sa.assertTrue(false, "Not able to click on Fund: "+M5F2+" so cannot check delete folder functionality");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on fund tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Test
	public void M5tc018_2_verifyDeletedFolderNameOnInstitutionAndContactPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependOntc="M5tc018_1_verifyDeleteFolderInInvestorWorkspace";
		InstitutionPageBusinessLayer ins = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer contact= new ContactPageBusinessLayer(driver);
		CommitmentPageBusinessLayer com = new CommitmentPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		String[] updateCommonFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOntc,excelLabel.CommonPath).split(",");
		String updateSharedFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOntc,excelLabel.SharedPath);
		String updateInternalFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOntc,excelLabel.InternalPath);
		String updateSTDFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOntc,excelLabel.StandardPath);
		if(fp.clickOnTab(TabName.InstituitonsTab)) {
			if(ins.clickOnCreatedInstitution(M5I1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.InstitutionsPage, 30));
					if(!fp.verifyFolderPathdummy(updateCommonFolder[0], null, null, M5F2, PageName.InstitutionsPage,Workspace.InvestorWorkspace, 5)) {
						appLog.info(updateCommonFolder[0]+" is not available on institution page: "+M5I1);
					}else {
						appLog.error(updateCommonFolder[0]+" is available on institution page: "+M5I1);
						sa.assertTrue(false, updateCommonFolder[0]+" is available on institution page: "+M5I1);
					}
					if(!fp.verifyFolderPathdummy(updateCommonFolder[1], null, null, M5F2, PageName.InstitutionsPage,Workspace.InvestorWorkspace,5)) {
						appLog.info(updateCommonFolder[1]+" is not available on institution page: "+M5I1);
					}else {
						appLog.error(updateCommonFolder[1]+" is available on institution page: "+M5I1);
						sa.assertTrue(false, updateCommonFolder[1]+" is available on institution page: "+M5I1);
					}
					if(!fp.verifyFolderPathdummy(updateSharedFolder, null, null, M5F2, PageName.InstitutionsPage,Workspace.InvestorWorkspace,5)) {
						appLog.info(updateSharedFolder+" is not available on institution page: "+M5I1);
					}else {
						appLog.error(updateSharedFolder+" is available on institution page: "+M5I1);
						sa.assertTrue(false, updateSharedFolder+" is available on institution page: "+M5I1);
					}
					
					if(!fp.verifyFolderPathdummy(updateInternalFolder, null, null, M5F2, PageName.InstitutionsPage,Workspace.InvestorWorkspace,5)) {
						appLog.info(updateInternalFolder+" is not available on institution page: "+M5I1);
					}else {
						appLog.error(updateInternalFolder+" is available on institution page: "+M5I1);
						sa.assertTrue(false, updateInternalFolder+" is available on institution page: "+M5I1);
					}
					if(!fp.verifyFolderPathdummy(updateSTDFolder, null, M5LP1, M5F2, PageName.InstitutionsPage,Workspace.InvestorWorkspace,5)) {
						appLog.info(updateSTDFolder+" is not available on institution page: "+M5I1);
					}else {
						appLog.error(updateSTDFolder+" is available on institution page: "+M5I1);
						sa.assertTrue(false, updateSTDFolder+" is available on institution page: "+M5I1);
					}
			}else {
				appLog.error("Not able to click on "+M5I1+" institution Name so cannot check Updated Folder Names");
				sa.assertTrue(false, "Not able to click on "+M5I1+" institution Name so cannot check Updated Folder Names");
			}
		}else {
			appLog.error("Not able to click on institution tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on institution tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		if(fp.clickOnTab(TabName.InstituitonsTab)) {
			if(ins.clickOnCreatedLP(environment, mode,M5LP1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.LimitedPartnerPage, 30));
					if(!fp.verifyFolderPathdummy(updateCommonFolder[0], null, null, M5F2, PageName.InstitutionsPage,Workspace.InvestorWorkspace, 5)) {
						appLog.info(updateCommonFolder[0]+" is not available on LP page: "+M5LP1);
					}else {
						appLog.error(updateCommonFolder[0]+" is available on LP page: "+M5LP1);
						sa.assertTrue(false, updateCommonFolder[0]+" is available on LP page: "+M5LP1);
					}
					if(!fp.verifyFolderPathdummy(updateCommonFolder[1], null, null, M5F2, PageName.InstitutionsPage,Workspace.InvestorWorkspace,5)) {
						appLog.info(updateCommonFolder[1]+" is not available on LP page: "+M5LP1);
					}else {
						appLog.error(updateCommonFolder[1]+" is available on LP page: "+M5LP1);
						sa.assertTrue(false, updateCommonFolder[1]+" is available on LP page: "+M5LP1);
					}
					if(!fp.verifyFolderPathdummy(updateSharedFolder, null, null, M5F2, PageName.InstitutionsPage,Workspace.InvestorWorkspace,5)) {
						appLog.info(updateSharedFolder+" is not available on LP page: "+M5LP1);
					}else {
						appLog.error(updateSharedFolder+" is available on LP page: "+M5LP1);
						sa.assertTrue(false, updateSharedFolder+" is available on LP page: "+M5LP1);
					}
					
					if(!fp.verifyFolderPathdummy(updateInternalFolder, null, null, M5F2, PageName.InstitutionsPage,Workspace.InvestorWorkspace,5)) {
						appLog.info(updateInternalFolder+" is not available on LP page: "+M5LP1);
					}else {
						appLog.error(updateInternalFolder+" is available on LP page: "+M5LP1);
						sa.assertTrue(false, updateInternalFolder+" is available on LP page: "+M5LP1);
					}
					if(!fp.verifyFolderPathdummy(updateSTDFolder, null, null, M5F2, PageName.InstitutionsPage,Workspace.InvestorWorkspace,5)) {
						appLog.info(updateSTDFolder+" is not available on LP page: "+M5LP1);
					}else {
						appLog.error(updateSTDFolder+" is available on LP page: "+M5LP1);
						sa.assertTrue(false, updateSTDFolder+" is available on LP page: "+M5LP1);
					}
			}else {
				appLog.error("Not able to click on "+M5LP1+" LP Name so cannot check Updated Folder Names");
				sa.assertTrue(false, "Not able to click on "+M5LP1+" LP Name so cannot check Updated Folder Names");
			}
		}else {
			appLog.error("Not able to click on LP tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on LP tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		if(fp.clickOnTab(TabName.ContactTab)) {
			if(contact.clickOnCreatedContact(M5CFN1, M5CLN1, null)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.ContactsPage, 30));
				if(!fp.verifyFolderPathdummy(updateCommonFolder[0], null, null, M5F2, PageName.ContactsPage,Workspace.InvestorWorkspace, 5)) {
					appLog.info(updateCommonFolder[0]+" is not available on Contact page: "+M5I1);
				}else {
					appLog.error(updateCommonFolder[0]+" is available on Contact page: "+M5I1);
					sa.assertTrue(false, updateCommonFolder[0]+" is available on Contact page: "+M5I1);
				}
				if(!fp.verifyFolderPathdummy(updateCommonFolder[1], null, null, M5F2, PageName.ContactsPage,Workspace.InvestorWorkspace,5)) {
					appLog.info(updateCommonFolder[1]+" is not available on Contact page: "+M5I1);
				}else {
					appLog.error(updateCommonFolder[1]+" is available on Contact page: "+M5I1);
					sa.assertTrue(false, updateCommonFolder[1]+" is available on Contact page: "+M5I1);
				}
				if(!fp.verifyFolderPathdummy(updateSharedFolder, null, null, M5F2, PageName.ContactsPage,Workspace.InvestorWorkspace,5)) {
					appLog.info(updateSharedFolder+" is not available on Contact page: "+M5I1);
				}else {
					appLog.error(updateSharedFolder+" is available on Contact page: "+M5I1);
					sa.assertTrue(false, updateSharedFolder+" is available on Contact page: "+M5I1);
				}
				
				if(!fp.verifyFolderPathdummy(updateInternalFolder, null, null, M5F2, PageName.ContactsPage,Workspace.InvestorWorkspace,5)) {
					appLog.info(updateInternalFolder+" is not available on Contact page: "+M5I1);
				}else {
					appLog.error(updateInternalFolder+" is available on Contact page: "+M5I1);
					sa.assertTrue(false, updateInternalFolder+" is available on Contact page: "+M5I1);
				}
				if(!fp.verifyFolderPathdummy(updateSTDFolder, null, null, M5F2, PageName.ContactsPage,Workspace.InvestorWorkspace,10)) {
					appLog.info(updateSTDFolder+" is not available on Contact page: "+M5I1);
				}else {
					appLog.error(updateSTDFolder+" is available on Contact page: "+M5I1);
					sa.assertTrue(false, updateSTDFolder+" is available on Contact page: "+M5I1);
				}
			}else {
				appLog.error("Not able to click on "+M5I1+" Contact Name so cannot check Updated Folder Names");
				sa.assertTrue(false, "Not able to click on "+M5I1+" Contact Name so cannot check Updated Folder Names");
			}
		}else {
			appLog.error("Not able to click on Contact tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on Contact tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		if(fp.clickOnTab(TabName.CommitmentsTab)) {
			if(com.clickOnCreatedCommitmentId(environment, mode,M5CMT2)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.CommitmentsPage, 30));
					if(fp.verifyFolderPathdummy(updateCommonFolder[0], null, null, M5F2, PageName.CommitmentsPage,Workspace.InvestorWorkspace, 5)) {
						appLog.info(updateCommonFolder[0]+" is verified on Commitment page: "+M5LP1);
						sa.assertTrue(false, updateCommonFolder[0]+" is verified on Commitment page: "+M5LP1);
					}else {
						appLog.error(updateCommonFolder[0]+" is not verified on Commitment page: "+M5LP1);
						
					}
					if(fp.verifyFolderPathdummy(updateInternalFolder, null, null, M5F2, PageName.CommitmentsPage,Workspace.InvestorWorkspace, 5)) {
						appLog.info(updateInternalFolder+" is verified on Commitment page: "+M5LP1);
						sa.assertTrue(false, updateInternalFolder+" is not verified on Commitment page: "+M5LP1);
					}else {
						appLog.error(updateInternalFolder+" is not verified on Commitment page: "+M5LP1);
						
					}
					if(fp.verifyFolderPathdummy(updateSharedFolder, null, null, M5F2, PageName.CommitmentsPage,Workspace.InvestorWorkspace, 5)) {
						appLog.info(updateSharedFolder+" is verified on Commitment page: "+M5LP1);
						sa.assertTrue(false, updateSharedFolder+" is not verified on Commitment page: "+M5LP1);
					}else {
						appLog.error(updateSharedFolder+" is not verified on Commitment page: "+M5LP1);
					}
					if(fp.verifyFolderPathdummy(updateSTDFolder, null, M5LP1, null, PageName.CommitmentsPage,Workspace.InvestorWorkspace, 5)) {
						appLog.info(updateSTDFolder+" is verified on Commitment page: "+M5LP1);
						sa.assertTrue(false, updateSTDFolder+" is not verified on Commitment page: "+M5LP1);
					}else {
						appLog.error(updateSTDFolder+" is not verified on Commitment page: "+M5LP1);
					}
			}else {
				appLog.error("Not able to click on "+M5CMT2+" Commitment id so cannot check Updated Folder Names");
				sa.assertTrue(false, "Not able to click on "+M5CMT2+" Commitment id so cannot check Updated Folder Names");
			}
		}else {
			appLog.error("Not able to click on Commitment tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on Commitment tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Test
	public void M5tc018_3_verifyDeletedFolderNameAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		String dependOntc="M5tc018_1_verifyDeleteFolderInInvestorWorkspace";
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.investorLogin(M5C1Email,adminPassword);
		if(ivp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			String[] updateCommonFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOntc,excelLabel.CommonPath).split(",");
			String updateSharedFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOntc,excelLabel.SharedPath);
			String updateSTDFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOntc,excelLabel.StandardPath);
			if(!fp.verifyFolderPathdummy(updateCommonFolder[0], null, null, M5F2, PageName.CurrentInvestmentPgae,null, 5)) {
				appLog.info(updateCommonFolder[0]+" is not available on CurrentInvestment page: "+M5I1);
			}else {
				appLog.error(updateCommonFolder[0]+" is available on CurrentInvestment page: "+M5I1);
				sa.assertTrue(false, updateCommonFolder[0]+" is available on CurrentInvestment page: "+M5I1);
			}
			if(!fp.verifyFolderPathdummy(updateCommonFolder[1], null, null, M5F2, PageName.CurrentInvestmentPgae,null,5)) {
				appLog.info(updateCommonFolder[1]+" is not available on CurrentInvestment page: "+M5I1);
			}else {
				appLog.error(updateCommonFolder[1]+" is available on CurrentInvestment page: "+M5I1);
				sa.assertTrue(false, updateCommonFolder[1]+" is available on CurrentInvestment page: "+M5I1);
			}
			if(!fp.verifyFolderPathdummy(updateSharedFolder, null, null, M5F2, PageName.CurrentInvestmentPgae,null,5)) {
				appLog.info(updateSharedFolder+" is not available on CurrentInvestment page: "+M5I1);
			}else {
				appLog.error(updateSharedFolder+" is available on CurrentInvestment page: "+M5I1);
				sa.assertTrue(false, updateSharedFolder+" is available on CurrentInvestment page: "+M5I1);
			}
			if(!fp.verifyFolderPathdummy(updateSTDFolder, null, M5I1, null, PageName.CurrentInvestmentPgae,null,5)) {
				appLog.info(updateSTDFolder+" is not available on CurrentInvestment page: "+M5I1);
			}else {
				appLog.error(updateSTDFolder+" is available on CurrentInvestment page: "+M5I1);
				sa.assertTrue(false, updateSTDFolder+" is available on CurrentInvestment page: "+M5I1);
			}
			if(!fp.verifyFolderPathdummy(updateSTDFolder, null, M5LP2, null, PageName.CurrentInvestmentPgae,null,5)) {
				appLog.info(updateSTDFolder+" is not available on CurrentInvestment page: "+M5I1);
			}else {
				appLog.error(updateSTDFolder+" is available on CurrentInvestment page: "+M5I1);
				sa.assertTrue(false, updateSTDFolder+" is available on CurrentInvestment page: "+M5I1);
			}
		}else {
			appLog.error("Not able to click on Current Investment tab so cannot check Updated Folder Name");
			sa.assertTrue(false, "Not able to click on Current Investment tab so cannot check Updated Folder Name");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M5tc019_1_deleteInvitedFolderFromManageFolderInInvestorWorkspace() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M5F2)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view.");
				if(click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 30), "Manage folder icon", action.BOOLEAN)){
					String updateSTDFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
						String id=null;
						id=fp.getCreatedFolderId(updateSTDFolder, PageName.ManageFolderPopUp, 20);
						System.err.println("id>>>>>>"+id);
						if(id!=null) {
							if(fp.clickOnDeleteFolderButton(id)) {
								if(click(driver, fp.getFolderDeleteYesBtn(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 10), "folder delete yes Button", action.BOOLEAN)) {
									appLog.info("clicked on folder delete yes Button");
									ThreadSleep(2000);
									if(fp.getCreatedFolderId(updateSTDFolder, PageName.ManageFolderPopUp, 10)!=null) {
										appLog.info(updateSTDFolder+" is not deleted, folder is visible in manage foler pop up");
										sa.assertTrue(false, updateSTDFolder+" is not deleted, folder is visible in manage foler pop up");
									}else {
										appLog.error(updateSTDFolder+" is deleted successfully, it is not visible in manage foler pop up");
									}
								}else {
									appLog.error("Yes button is not displaying on folder delete pop up so cannot click on Yes button");
									sa.assertTrue(false, "yes button is not displaying on folder delete pop up so cannot click on yes button");
								}
							}else {
								appLog.error("Not able to click on folder: "+updateSTDFolder+" delete icon so cannot check delete invited folder");
								sa.assertTrue(false, "Not able to click on folder: "+updateSTDFolder+" delete icon so cannot check delete invited folder");
							}
						}else {
							appLog.error(updateSTDFolder+" is not available in the manage folder structure so cannot click on folder "+updateSTDFolder+" delete icon");
							sa.assertTrue(false, updateSTDFolder+" is not available in the manage folder structure so cannot click on folder "+updateSTDFolder+" delete icon");
						}
				}else {
					appLog.error("Not able to click on Manage folder icon so cannot check delete invited folder");
					sa.assertTrue(false, "Not able to click on Manage folder icon so cannot check delete invited folder");
				}
			}else {
				appLog.error("Not able to click on Fund: "+M5F2+" so cannot check delete invited folder");
				sa.assertTrue(false, "Not able to click on Fund: "+M5F2+" so cannot check delete invited folder");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on fund tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Test
	public void M5tc019_2_verifyDeleteInvitedFolderNameOnInstitutionAndContactPage() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ins = new InstitutionPageBusinessLayer(driver);
		ContactPageBusinessLayer contact= new ContactPageBusinessLayer(driver);
		CommitmentPageBusinessLayer com = new CommitmentPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		String updateSTDFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc019_1_deleteInvitedFolderFromManageFolderInInvestorWorkspace",excelLabel.StandardPath);
		if(fp.clickOnTab(TabName.InstituitonsTab)) {
			if(ins.clickOnCreatedInstitution(M5I1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.InstitutionsPage, 30));
					if(!fp.verifyFolderPathdummy(updateSTDFolder, null, M5LP1, M5F2, PageName.InstitutionsPage,Workspace.InvestorWorkspace,5)) {
						appLog.info(updateSTDFolder+" is not available on institution page: "+M5I1);
					}else {
						appLog.error(updateSTDFolder+" is available on institution page: "+M5I1);
						sa.assertTrue(false, updateSTDFolder+" is available on institution page: "+M5I1);
					}
			}else {
				appLog.error("Not able to click on "+M5I1+" institution Name so cannot check Updated Folder Names");
				sa.assertTrue(false, "Not able to click on "+M5I1+" institution Name so cannot check Updated Folder Names");
			}
		}else {
			appLog.error("Not able to click on institution tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on institution tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		if(fp.clickOnTab(TabName.InstituitonsTab)) {
			if(ins.clickOnCreatedLP(environment, mode,M5LP1)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.LimitedPartnerPage, 30));
					if(!fp.verifyFolderPathdummy(updateSTDFolder, null, null, M5F2, PageName.InstitutionsPage,Workspace.InvestorWorkspace,5)) {
						appLog.info(updateSTDFolder+" is not available on LP page: "+M5LP1);
					}else {
						appLog.error(updateSTDFolder+" is available on LP page: "+M5LP1);
						sa.assertTrue(false, updateSTDFolder+" is available on LP page: "+M5LP1);
					}
			}else {
				appLog.error("Not able to click on "+M5LP1+" LP Name so cannot check Updated Folder Names");
				sa.assertTrue(false, "Not able to click on "+M5LP1+" LP Name so cannot check Updated Folder Names");
			}
		}else {
			appLog.error("Not able to click on LP tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on LP tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		if(fp.clickOnTab(TabName.ContactTab)) {
			if(contact.clickOnCreatedContact(M5CFN1, M5CLN1, null)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.ContactsPage, 30));
				if(!fp.verifyFolderPathdummy(updateSTDFolder, null, M5LP1, M5F2, PageName.ContactsPage,Workspace.InvestorWorkspace,5)) {
					appLog.info(updateSTDFolder+" is not available on Contact page: "+M5I1);
				}else {
					appLog.error(updateSTDFolder+" is available on Contact page: "+M5I1);
					sa.assertTrue(false, updateSTDFolder+" is available on Contact page: "+M5I1);
				}
			}else {
				appLog.error("Not able to click on "+M5I1+" Contact Name so cannot check Updated Folder Names");
				sa.assertTrue(false, "Not able to click on "+M5I1+" Contact Name so cannot check Updated Folder Names");
			}
		}else {
			appLog.error("Not able to click on Contact tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on Contact tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		if(fp.clickOnTab(TabName.CommitmentsTab)) {
			if(com.clickOnCreatedCommitmentId(environment, mode,M5CMT2)) {
				switchToFrame(driver, 30, fp.getFrame( PageName.CommitmentsPage, 30));
					if(fp.verifyFolderPathdummy(updateSTDFolder, null, M5LP1, null, PageName.CommitmentsPage,Workspace.InvestorWorkspace, 5)) {
						appLog.info(updateSTDFolder+" is available on Commitment page: "+M5CMT2);
						sa.assertTrue(false, updateSTDFolder+" is available on Commitment page: "+M5CMT2);
					}else {
						appLog.error(updateSTDFolder+" is not available on Commitment page: "+M5CMT2);
					}
			}else {
				appLog.error("Not able to click on "+M5CMT2+" Commitment id so cannot check Updated Folder Names");
				sa.assertTrue(false, "Not able to click on "+M5CMT2+" Commitment id so cannot check Updated Folder Names");
			}
		}else {
			appLog.error("Not able to click on Commitment tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on Commitment tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Test
	public void M5tc019_3_verifyDeleteInvitedFolderNameAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.investorLogin(M5C1Email,adminPassword);
		if(ivp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			String updateSTDFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,"M5tc019_2_verifyDeleteInvitedFolderNameOnInstitutionAndContactPage",excelLabel.StandardPath);
			if(!fp.verifyFolderPathdummy(updateSTDFolder, null, M5I1, null, PageName.CurrentInvestmentPgae,null,5)) {
				appLog.info(updateSTDFolder+" is not available on CurrentInvestment page: "+M5I1);
			}else {
				appLog.error(updateSTDFolder+" is available on CurrentInvestment page: "+M5I1);
				sa.assertTrue(false, updateSTDFolder+" is available onCurrentInvestment page: "+M5I1);
			}
			if(!fp.verifyFolderPathdummy(updateSTDFolder, null, M5LP2, null, PageName.CurrentInvestmentPgae,null,5)) {
				appLog.info(updateSTDFolder+" is not available on CurrentInvestment page: "+M5LP2);
			}else {
				appLog.error(updateSTDFolder+" is available on CurrentInvestment page: "+M5LP2);
				sa.assertTrue(false, updateSTDFolder+" is available onCurrentInvestment page: "+M5LP2);
			}
		}else {
			appLog.error("Not able to click on Current Investment tab so cannot check Updated Folder Name");
			sa.assertTrue(false, "Not able to click on Current Investment tab so cannot check Updated Folder Name");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M5tc020_1_createWorkSpaceAndAddAditionalFolders() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String[] STDFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath).split(",");
		String[] CommonFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath).split(",");
		String[] InternalFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.InternalPath).split(",");
		String[] SHRDFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath).split(",");
		String[] step1Of3Data = {"2100","2015","abc","987654321","abc@abc.com","Dummy description"};
		lp.CRMLogin(CRMUser1EmailID, adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)){
			if(fp.clickOnCreatedFund(M5F1)){
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view.");
				if(click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 30), "Manage folder icon", action.BOOLEAN)){
					String[] folders= {STDFolder[0],SHRDFolder[0],InternalFolder[0]};
					for(int i=0 ;i<folders.length; i++) {
						if(i==0) {
							if(fp.createFolderStructure(folders[i], FolderType.Standard, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
								appLog.info(folders[i]+ " folder structure is created.");
							} else {
								appLog.error(folders[i]+ " folder structure is not created.");
								sa.assertTrue(false,folders[i]+ " folder structure is not created.");
							}
						}
						if(i==1) {
							if(fp.createFolderStructure(folders[i], FolderType.Shared, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
								appLog.info(folders[i]+ " folder structure is created.");
							} else {
								appLog.error(folders[i]+ " folder structure is not created.");
								sa.assertTrue(false,folders[i]+ " folder structure is not created.");
							}
						}
						if(i==2) {
							if(fp.createFolderStructure(folders[i], FolderType.Internal, Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
								appLog.info(folders[i]+ " folder structure is created.");
							} else {
								appLog.error(folders[i]+ " folder structure is not created.");
								sa.assertTrue(false,folders[i]+ " folder structure is not created.");
							}
						}
					}
					if(click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 20), "manage folder close button", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on manage folder close icon");
					}else {
						appLog.error("Not able to click on manage folder close icon");
						sa.assertTrue(false, "Not able to click on manage folder close icon");
					}
				}else {
					appLog.error("Not able to click on Manage folder icon so cannot check delete invited folder");
					sa.assertTrue(false, "Not able to click on Manage folder icon so cannot check delete invited folder");
				}
				if(click(driver, fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 20), "manage investor icon", action.SCROLLANDBOOLEAN)) {
					if(fp.selectInstitutionOrLP(M5I1, Workspace.FundraisingWorkspace,20)) {
						appLog.info("Investor add successfully : "+M5I1);
						if(click(driver, fp.getManageInvestorAddedPopupCloseButton(Workspace.FundraisingWorkspace, 20), "manage investor close button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on manage investor added close button");
						}else {
							appLog.error("Not able to clicked on manage investor close button");
							sa.assertTrue(false, "Not able to clicked on manage investor close button");
						}
					}else {
						appLog.error("Not able to add investor : "+M5I1);
						sa.assertTrue(false, "Not able to add investor : "+M5I1);
					}
					if(click(driver, fp.getManageInvestorDoneButton(Workspace.FundraisingWorkspace, 20), "manage investor done button", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on manage investor done button");
					}else {
						appLog.error("Not able to click on manage investor done button");
						sa.assertTrue(false, "Not able to click on manage investor done button");
					}
					switchToDefaultContent(driver);
					if(fp.inviteContact(environment, mode, M5I1, M5C1Email, null, FolderType.Standard, "Upload", "Yes", null, null, Workspace.FundraisingWorkspace, null)) {
						appLog.info("contact is invited successfully from fundraising workspace: "+M5CFN1+" "+M5CLN1);
					}else {
						appLog.error("Not able to invite contact from fundraising workspace : "+M5CFN1+" "+M5CLN1);
						sa.assertTrue(false, "Not able to invite contact from fundraising workspace : "+M5CFN1+" "+M5CLN1);
					}
				}else {
					appLog.error("Not able to click on manage investor icon so cannot add investor in fundraising workspace: "+M5I1);
					sa.assertTrue(false, "Not able to click on manage investor icon so cannot add investor in fundraising workspace: "+M5I1);
				}
				if(fp.buildWorkspace(step1Of3Data, WorkSpaceAction.CREATEFOLDERTEMPLATE, null, "HybridFolder_Temp", M5I1+"/"+M5LP1, Workspace.InvestorWorkspace, 60)){
					appLog.info("Invstor Workspace is build successfully  :"+M5F1);
					if(fp.inviteContact(environment, mode, M5I1+"/"+M5LP1, M5C1Email, null, FolderType.Standard, "Upload", "Yes", null, null, Workspace.InvestorWorkspace, null)) {
						appLog.info("contact is invited successfully from investor workspace: "+M5CFN1+" "+M5CLN1);
					}else {
						appLog.error("Not able to invite contact from investor workspace : "+M5CFN1+" "+M5CLN1);
						sa.assertTrue(false, "Not able to invite contact from investor workspace: "+M5CFN1+" "+M5CLN1);
					}
				}else {
					appLog.error("Invstor Workspace is not build :"+M5F1);
					sa.assertTrue(false, "Invstor Workspace is not build :"+M5F1);
					
				}
				switchToFrame(driver, 30, fp.getFrame( PageName.FundsPage, 30));
				if(click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 30), "Manage folder icon", action.BOOLEAN)){
					if(fp.createFolderStructure(CommonFolder[0], FolderType.Common, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
						appLog.info(CommonFolder[0]+ " folder structure is created.");
					} else {
						appLog.error(CommonFolder[0]+ " folder structure is not created.");
						sa.assertTrue(false,CommonFolder[0]+ " folder structure is not created.");
					}
					if(fp.createFolderStructure(SHRDFolder[1].split("/")[1], FolderType.Shared, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
						appLog.info(SHRDFolder[1].split("/")[1]+ " folder structure is created.");
					} else {
						appLog.error(SHRDFolder[1].split("/")[1]+ " folder structure is not created.");
						sa.assertTrue(false,SHRDFolder[1].split("/")[1]+ " folder structure is not created.");
					}
					if(fp.createFolderStructure(STDFolder[1].split("/")[1], FolderType.Standard, Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 30).isEmpty()){
						appLog.info(STDFolder[1].split("/")[1]+ " folder structure is created.");
					} else {
						appLog.error(STDFolder[1].split("/")[1]+ " folder structure is not created.");
						sa.assertTrue(false,STDFolder[1].split("/")[1]+ " folder structure is not created.");
					}
					if(click(driver, fp.getManageFolderCloseButton(Workspace.InvestorWorkspace, 10), "manage folder close button", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on manage folder close button");
					}else {
						appLog.error("Not able to click on manage folder close button");
						sa.assertTrue(false, "Not able to click on manage folder close button");
					}
					if(fp.verifyFolderPathdummy(STDFolder[1].split("/")[1], M5I1, M5LP1,null, PageName.FundsPage, Workspace.InvestorWorkspace,30)) {
						appLog.info("folder structure is verified: "+STDFolder[1].split("/")[1]);
					}else {
						appLog.error("folder structure is not verified: "+STDFolder[1].split("/")[1]);
						sa.assertTrue(false, "folder structure is not verified: "+STDFolder[1].split("/")[1]);
					}
					if(fp.verifyFolderPathdummy(CommonFolder[0], null, null,null, PageName.FundsPage, Workspace.InvestorWorkspace,30)) {
						appLog.info("folder structure is verified: "+CommonFolder[0]);
					}else {
						appLog.error("folder structure is not verified: "+CommonFolder[0]);
						sa.assertTrue(false, "folder structure is not verified: "+CommonFolder[0]);
					}
					if(fp.verifyFolderPathdummy(SHRDFolder[1].split("/")[1]+" (Shared)", null, null,null, PageName.FundsPage, Workspace.InvestorWorkspace,30)) {
						appLog.info("folder structure is verified: "+SHRDFolder[1].split("/")[1]+" (Shared)");
					}else {
						appLog.error("folder structure is not verified: "+SHRDFolder[1].split("/")[1]+" (Shared)");
						sa.assertTrue(false, "folder structure is not verified: "+SHRDFolder[1].split("/")[1]+" (Shared)");
					}
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view.");
					if(click(driver,fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 20), "manage folder icon", action.SCROLLANDBOOLEAN)) {
						String[] ss = {CommonFolder[0],STDFolder[1].split("/")[1],SHRDFolder[1].split("/")[1]+" (Shared)"};
						for(int i=0; i<ss.length; i++) {
							if(fp.getCreatedFolderId(ss[i], PageName.ManageFolderPopUp,5) != null) {
								appLog.error(ss[i]+" folder is visible in fundraising workspace which is created in investor workspace manage folder");
								sa.assertTrue(false, ss[i]+" folder is visible in fundraising workspace which is created in investor workspace manage folder");
							}else {
								appLog.info(ss[i]+" is not available in fundraising workspace manage folder");
							}
						}
						if(click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 10), "manage folder close button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on manage folder close button");
						}else {
							appLog.error("Not able to click on manage folder close button");
							sa.assertTrue(false, "Not able to click on manage folder close button");
						}
					}else {
						appLog.error("Not able to click on fundraising workspace manage folder icon so cannot check investor workspace created folder in fundraising workspace manage folder");
						sa.assertTrue(false, "Not able to click on fundraising workspace manage folder icon so cannot check investor workspace created folder in fundraising workspace manage folder");
					}
				}else {
					appLog.error("Not able to click on investor workspace manage folder icon so cannot add folders and check impact on investor workspace");
					sa.assertTrue(false, "Not able to click on investor workspace manage folder icon so cannot add folders and check impact on investor workspace");
				}
			}else {
				appLog.error("Not able to click on Fund: "+M5F1+" so cannot check delete invited folder");
				sa.assertTrue(false, "Not able to click on Fund: "+M5F1+" so cannot check delete invited folder");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot continue with tc");
			sa.assertTrue(false, "Not able to click on fund tab so cannot continue with tc");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout(environment,mode);
		sa.assertAll();
	}
	
	@Test
	public void M5tc020_2_verifyInvitedFolderStructureAtInvestorSide() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ivp = new InvestorFirmPageBusinesslayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependOntc="M5tc020_1_createWorkSpaceAndAddAditionalFolders";
		String[] STDFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOntc,excelLabel.StandardPath).split(",");
		String[] CommonFolder=ExcelUtils.readData("FilePath",excelLabel.TestCases_Name,dependOntc,excelLabel.CommonPath).split(",");
		lp.investorLogin(M5C1Email,adminPassword);
		if(ivp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			if(fp.verifyFolderPathdummy(CommonFolder[0], null, null, null, PageName.CurrentInvestmentPgae,null,5)) {
				appLog.info(CommonFolder[0]+" is available on CurrentInvestment page: "+M5LP1);
			}else {
				appLog.error(CommonFolder[0]+" is not available on CurrentInvestment page: "+M5LP1);
				sa.assertTrue(false, CommonFolder[0]+" is not available onCurrentInvestment page: "+M5LP1);
			}
			if(fp.verifyFolderPathdummy(STDFolder[1].split("/")[1], null, M5LP1, null, PageName.CurrentInvestmentPgae,null,5)) {
				appLog.info(STDFolder[1].split("/")[1]+" is available on CurrentInvestment page: "+M5LP1);
			}else {
				appLog.error(STDFolder[1].split("/")[1]+" is  not available on CurrentInvestment page: "+M5LP1);
				sa.assertTrue(false, STDFolder[1].split("/")[1]+" is not available onCurrentInvestment page: "+M5LP1);
			}
			if(fp.verifyFolderPathdummy(STDFolder[1].split("/")[0], null, M5LP1, null, PageName.CurrentInvestmentPgae,null,5)) {
				appLog.info(STDFolder[1].split("/")[0]+" is available on CurrentInvestment page: "+M5LP1);
			}else {
				appLog.error(STDFolder[1].split("/")[0]+" is not available on CurrentInvestment page: "+M5LP1);
				sa.assertTrue(false, STDFolder[1].split("/")[0]+" is not available onCurrentInvestment page: "+M5LP1);
			}
		}else {
			appLog.error("Not able to click on Current Investment tab so cannot check Updated Folder Name");
			sa.assertTrue(false, "Not able to click on Current Investment tab so cannot check Updated Folder Name");
		}
		driver.navigate().refresh();
		if(ivp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			if(fp.verifyFolderPathdummy(STDFolder[1].split("/")[0], M5I1, null, null, PageName.PotentialInvestmentPage,null,5)) {
				appLog.info(STDFolder[1].split("/")[0]+" is available on PotentialInvestment page: "+M5I1);
			}else {
				appLog.error(STDFolder[1].split("/")[0]+" is not available on PotentialInvestment page: "+M5I1);
				sa.assertTrue(false, STDFolder[1].split("/")[0]+" is not available on PotentialInvestment page: "+M5I1);
			}
		}else {
			appLog.error("Not able to click on Potentail Investment tab so cannot check Updated Folder Name");
			sa.assertTrue(false, "Not able to click on Potential Investment tab so cannot check Updated Folder Name");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M5tc021_PostConditionForAll() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName, adminPassword);
		lp.postCondition().assertAll();
		lp.CRMlogout(environment,mode);

	}
}
