/**
 * 
 */
package com.navatar.scripts;
import static com.navatar.generic.CommonVariables.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.navatar.generic.BaseLib;
import com.navatar.generic.CommonLib;
import com.navatar.generic.ExcelUtils;
import com.navatar.generic.SoftAssert;
import com.navatar.generic.CommonLib.ContentGridArrowKeyFunctions;
import com.navatar.generic.CommonLib.FolderType;
import com.navatar.generic.CommonLib.OnlineImportFileAddTo;
import com.navatar.generic.CommonLib.PageName;
import com.navatar.generic.CommonLib.SortOrder;
import com.navatar.generic.CommonLib.TabName;
import com.navatar.generic.CommonLib.UploadFileActions;
import com.navatar.generic.CommonLib.WorkSpaceAction;
import com.navatar.generic.CommonLib.Workspace;
import com.navatar.generic.CommonLib.action;
import com.navatar.generic.CommonLib.excelLabel;
import com.navatar.generic.CommonLib.investorSideWorkSpace;
import com.navatar.generic.CommonVariables;
import com.navatar.generic.EmailLib;
import com.navatar.pageObjects.AllFirmsPageBusinesslayer;
import com.navatar.pageObjects.BasePageBusinessLayer;
import com.navatar.pageObjects.CommitmentPageBusinessLayer;
import com.navatar.pageObjects.ContactPageBusinessLayer;
import com.navatar.pageObjects.FundRaisingPageBusinessLayer;
import com.navatar.pageObjects.FundsPageBusinessLayer;
import com.navatar.pageObjects.FundsPageErrorMessage;
import com.navatar.pageObjects.HomePageBusineesLayer;
import com.navatar.pageObjects.InstitutionPageBusinessLayer;
import com.navatar.pageObjects.InvestorFirmPageBusinesslayer;
import com.navatar.pageObjects.InvestorProfileBusinessLayer;
import com.navatar.pageObjects.LoginPageBusinessLayer;
import com.navatar.pageObjects.NIMPageBusinessLayer;
import com.navatar.pageObjects.PartnershipPageBusinessLayer;
import static com.navatar.generic.CommonLib.*;
/**
 * @Module Name: IP Analytics
 * @Module : Module 14
 * @author Ankit Jaiswal
 * 
 *
 */
public class Module14 extends BaseLib {

	
	@Test
	public void M14tc001_preCondition() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		ContactPageBusinessLayer cp = new ContactPageBusinessLayer(driver);
		InstitutionPageBusinessLayer ip = new InstitutionPageBusinessLayer(driver);
		CommitmentPageBusinessLayer cmp = new CommitmentPageBusinessLayer(driver);
		PartnershipPageBusinessLayer pp = new PartnershipPageBusinessLayer(driver);
		FundRaisingPageBusinessLayer frp = new FundRaisingPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		for(int i=0; i<2; i++) {
			String instutionName=ExcelUtils.readData("Institutions",excelLabel.Variable_Name, "M14Instituition"+(i+1), excelLabel.Institutions_Name);
			if(bp.clickOnTab(TabName.InstituitonsTab)) {
			if(ip.createInstitution(instutionName)) {
				appLog.info("Institution is created successfully: "+instutionName);
			}else {
				appLog.error("Not able to create institution: "+instutionName);
				sa.assertTrue(false, "Not able to create institution: "+instutionName);
			}
		}else {
				appLog.error("Not able to click on institution tab so cannot create institution: "+instutionName);
				sa.assertTrue(false, "Not able to click on institution tab so cannot create institution: "+instutionName);
			}
		}
		for(int i=0; i<3; i++) {
			String emailId = cp.generateRandomEmailId();
			if(bp.clickOnTab(TabName.ContactTab)){
				if(i==0) {
					if(cp.createContact(M14Contact1FirstName,M14Contact1LastName,M14Institution1, emailId)) {
						appLog.info("contact is created: "+M14Contact1FirstName+" "+M14Contact1LastName);
						ExcelUtils.writeData(emailId,"Contacts", excelLabel.Variable_Name, "M14Contact1",excelLabel.Contact_EmailId);
					}else {
						appLog.error("Not able to create contact: "+M14Contact1FirstName+" "+M14Contact1LastName);
						sa.assertTrue(false, "Not able to create contact: "+M14Contact1FirstName+" "+M14Contact1LastName);
					}
				}
				if(i==1) {
					if(cp.createContact(M14Contact2FirstName,M14Contact2LastName,M14Institution1, emailId)) {
						appLog.info("contact is created: "+M14Contact1FirstName+" "+M14Contact1LastName);
						ExcelUtils.writeData(emailId,"Contacts", excelLabel.Variable_Name, "M14Contact2",excelLabel.Contact_EmailId);
					}else {
						appLog.error("Not able to create contact: "+M14Contact1FirstName+" "+M14Contact1LastName);
						sa.assertTrue(false, "Not able to create contact: "+M14Contact1FirstName+" "+M14Contact1LastName);
					}
				}
				if(i==2) {
					if(cp.createContact(M14Contact3FirstName,M14Contact3LastName,M14Institution2, emailId)) {
						appLog.info("contact is created: "+M14Contact3FirstName+" "+M14Contact3LastName);
						ExcelUtils.writeData(emailId,"Contacts", excelLabel.Variable_Name, "M14Contact3",excelLabel.Contact_EmailId);
					}else {
						appLog.error("Not able to create contact: "+M14Contact3FirstName+" "+M14Contact3LastName);
						sa.assertTrue(false, "Not able to create contact: "+M14Contact3FirstName+" "+M14Contact3LastName);
					}
				}
			}
		}
		if(bp.clickOnTab(TabName.FundsTab)) {
			if(fp.createFund(M14FundName1,M14FundType,M14FundInvestmentCategory)) {
				appLog.info("fund is created: "+M14FundName1);
			}else {
				appLog.error("Not able to create fund: "+M14FundName1);
				sa.assertTrue(false, "Not able to create fund: "+M14FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot create fund: "+M14FundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot create fund: "+M14FundName1);
		}
		for(int i=0; i<2; i++) {
			String fundraisingName=ExcelUtils.readData("Fundraisings",excelLabel.Variable_Name, "M14FundRaising"+(i+1), excelLabel.FundRaising_Name);
			if(bp.clickOnTab(TabName.FundraisingsTab)) {
				String fundName=ExcelUtils.readData("Fundraisings",excelLabel.Variable_Name, "M14FundRaising"+(i+1), excelLabel.Fund_Name);
				String instutionName=ExcelUtils.readData("Institutions",excelLabel.Variable_Name, "M14Instituition"+(i+1), excelLabel.Institutions_Name);
				if(frp.createFundRaising(fundraisingName, fundName, instutionName)) {
					appLog.info("fundraising is created : "+fundraisingName);
				}else {
					appLog.error("Not able to create fundraising: "+fundraisingName);
					sa.assertTrue(false, "Not able to create fundraising: "+fundraisingName);
				}
			}else {
				appLog.error("Not able to click on fundraising tab so cannot create fundraising: "+fundraisingName);
				sa.assertTrue(false,"Not able to click on fundraising tab so cannot create fundraising: "+fundraisingName);
			}
		}
		for(int i=0; i<2; i++) {
			String lpName=ExcelUtils.readData("Limited Partner",excelLabel.Variable_Name, "M14LimitedPartner"+(i+1), excelLabel.LimitedPartner_Name);
			if(bp.clickOnTab(TabName.InstituitonsTab)) {
				String instutionName=ExcelUtils.readData("Institutions",excelLabel.Variable_Name, "M14Instituition"+(i+1), excelLabel.Institutions_Name);
				if(ip.createLimitedPartner(lpName, instutionName)) {
					appLog.info("limited partner is created: "+lpName);
				}else {
					appLog.error("Not able to create limited partner: "+lpName);
					sa.assertTrue(false, "Not able to create limited partner: "+lpName);
				}
			}else {
				appLog.error("Not able to click on institution tab so cannot create limite partner: "+lpName);
				sa.assertTrue(false, "Not able to click on institution tab so cannot create limite partner: "+lpName);
			}
		}
		if(bp.clickOnTab(TabName.PartnershipsTab)) {
			if(pp.createPartnership(M14Partnership1,M14FundName1)) {
				appLog.info("partnership is created: "+M14Partnership1);
			}else {
				appLog.error("Not able to create partnership: "+M14Partnership1);
				sa.assertTrue(false, "Not able to create partnership: "+M14Partnership1);
			}
		}else {
			appLog.error("Not able to click on partnership tab so cannot create partnership: "+M14Partnership1);
			sa.assertTrue(false, "Not able to click on partnership tab so cannot create partnership: "+M14Partnership1);
		}
		for(int i=0; i<2; i++) {
			String lpName;
			String partnershipName = null;
			lpName=ExcelUtils.readData("Limited Partner",excelLabel.Variable_Name, "M14LimitedPartner"+(i+1)+"", excelLabel.LimitedPartner_Name);
			partnershipName=ExcelUtils.readData("Partnerships",excelLabel.Variable_Name, "M14Partnership1", excelLabel.PartnerShip_Name);
			if(bp.clickOnTab(TabName.CommitmentsTab)) {
				if(cmp.createCommitment(lpName,partnershipName,"M14Commitment"+(i+1), null)) {
					appLog.info("commitment is created successfully");
				}else {
					appLog.error("Not able to create commitment for limited partner: "+lpName+" and partnership Name: "+partnershipName);
					sa.assertTrue(false, "Not able to create commitment for limited partner: "+lpName+" and partnership Name: "+partnershipName);
				}
			}else {
				appLog.error("Not able to click on commitment tab so cannot create committment for limite partner: "+lpName+" and partnership Name: "+partnershipName);
				sa.assertTrue(false, "Not able to click on commitment tab so cannot create committment for limite partner: "+lpName+" and partnership Name: "+partnershipName);
			}
		}
		if(nim.getMyProfileFistNameAndLastNameAndFirmName("User1")) {
			appLog.info("CRM User1 first,last name and firm name successfully write in excel");
		}else {
			appLog.error("Not able to write CRM User1 first,last name and firm profile in excel");
			sa.assertTrue(false, "Not able to write CRM User1 first,last name and firm profile in excel");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc002_1_buildFWRWorkSpace() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				String Size=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M14Fund1", excelLabel.Fund_Size);
				String vintageyear=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M14Fund1", excelLabel.Fund_VintageYear);
				String contact=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M14Fund1", excelLabel.Fund_Contact);
				String phone=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M14Fund1", excelLabel.Fund_Phone);
				String email=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M14Fund1", excelLabel.Fund_Email);
				String description=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M14Fund1", excelLabel.Fund_Description);
				String templateName=ExcelUtils.readData("Foldertemp", 0, 1);
				String[] data= {Size,vintageyear,contact,phone,email,description};
				if(fp.buildWorkspace(data,WorkSpaceAction.IMPORTFOLDERTEMPLATE,templateName,null,M14Institution1+"<break>"+M14Institution2, Workspace.FundraisingWorkspace,60)) {
					appLog.info("FundRaising work is build successfully on fund : "+M14FundName1);
				}else {
					appLog.error("Not able to bulid fundraising workspace on fund: "+M14FundName1);
					sa.assertTrue(false, "Not able to bulid fundraising workspace on fund: "+M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created fund Name so cannot build fundraising workspace: "+M14FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot build fundraising workspace: "+M14FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot build fundraising workspace: "+M14FundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot build fundraising workspace: "+M14FundName1);
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc002_2_validateDealRoomAnalyticsUIInFWR() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		List<String> ListofActualResult = new ArrayList<String>();
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 60,fp.getFrame(PageName.FundsPage,30));
				scrollDownThroughWebelement(driver,fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace,30), "workspace Section");
		if (click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "Deal Room Analytics Icon", action.SCROLLANDBOOLEAN)) {
			String parentid = switchOnWindow(driver);
			if (fp.getIPAnalyticsHeaderText(60) != null) {
				sa.assertTrue(fp.getIPAnalyticsHeaderText(30).getText().trim().contains("Navatar Investor Analytics"),
						"Navatar Investor Analytics header Text is not verified. Expected: Navatar Investor Analytics");
			} else {
				appLog.info("IP Analytics header text is not visible");
				sa.assertTrue(false, "IP Analytics header text is not visible");
			}
			List<WebElement> tabs = fp.getIPAnalyticsListofTab();
			String expectedTabs = "Statistics<break>Exports";
			if (!tabs.isEmpty()) {
				for (int i = 0; i < tabs.size(); i++) {
					ListofActualResult.add(tabs.get(i).getText().trim());
				}
				if (!compareMultipleListWithoutAssertion(expectedTabs, ListofActualResult)) {
					appLog.info("IP Analytics Tabs text is not verified.");
					sa.assertTrue(false, "IP Analytics Tabs text is not verified.");
				} else {
					appLog.info("IP Analytics Tabs text is verified.");
				}

				if (isDisplayed(driver, tabs.get(0), "visibility", 10, "IP Statistics Tab text") != null) {
					appLog.info("IP Statistics Tab is visibile and it's selected by Deafult.");
				} else {
					appLog.info("IP Statistics Tab is not visibile");
					appLog.info("IP Statistics Tab is not selected by Deafult.");
					sa.assertTrue(false, "IP Statistics Tab is not selected by Deafult.");
				}
				if (fp.getIPAnalyticsExportIconOnExportTab(10) != null) {
					appLog.info("IP Analytics Export Tab is selected by Deafult.");
					sa.assertTrue(false, "IP Analytics Export Tab is selected by Deafult.");
				} else {
					appLog.info("IP Analytics Export Tab visibile and it's not selected by default.");
				}

			} else {
				appLog.info("IP Analytics Tabs List is not available");
				sa.assertTrue(false, "IP Analytics Tabs List is not available");
			}
			if (fp.getIPAnalyticsCloseBtn(10) != null) {
				appLog.info("Close Button is displaying on IP Analytics Pop Up");
			} else {
				appLog.info("Close Button is not displaying on IP Analytics Pop Up");
				sa.assertTrue(false, "Close Button is not displaying on IP Analytics Pop Up");
			}

			String targetStatisticsText = "Investor Statistics<break># of Unique Documents<break># of Document Views<break># of Document Downloads<break># of Documents not Viewed or Downloaded<break># of Contacts Granted Access<break>"
					+ "# of Contacts who have not Viewed any Document<break>Most Viewed Documents (Top 5)<break>Most Active Contacts (Top 5)";
			List<WebElement> ListofData = fp.getIPAnalyticsListofTargetStatisticsText();
			ListofActualResult.clear();
			if (!ListofData.isEmpty()) {
				for (int i = 0; i < ListofData.size(); i++) {
					ListofActualResult.add(ListofData.get(i).getText().trim());
				}
				if (!compareMultipleListWithoutAssertion(targetStatisticsText, ListofActualResult)) {
					appLog.info("Investor Statistics Text is not verified");
					sa.assertTrue(false, "Investor Statistics Text is not verified");
				} else {
					appLog.info("Investor Statistics all Text is verified");
				}
			} else {
				appLog.info("Investor Statistics Text List is not available.");
				sa.assertTrue(false, "Investor Statistics Text List is not available.");
			}
			ListofData.clear();
			ListofData = fp.getIPAnalyticsListofValuesTetx();
			if (!ListofData.isEmpty()) {
				for (int j = 0; j < ListofData.size(); j++) {
					try {
						int result = Integer.parseInt(ListofData.get(j).getText().trim());
						if (result == 0) {
							appLog.info("IP Statistics value is displaying 0.");
						} else {
							appLog.info("IP Statistics value is not displaying 0.");
						}
					} catch (Exception e) {
						if (ListofData.get(j).getText().trim().contains("Value")) {
							appLog.info("IP Statistics Target Value text is verified.");
						} else if (ListofData.get(j).getText().trim().contains("View")) {
							appLog.info("IP Statistics Target View text is verified.");
						} else {
							appLog.info("IP Statistics Target Value or View text is verified.");
							sa.assertTrue(false, "IP Statistics Target Value or View text is verified.");
						}
					}
				}

			} else {
				appLog.info("IP Analytics value and Text is not verifed.");
				sa.assertTrue(false, "IP Analytics value and Text is not verifed.");
			}
			WebElement msg = isDisplayed(driver,
					FindElement(driver,
							"//div[@class='IPAnalytics_Statistics']//table//tr/td[contains(text(),'# of Documents not Viewed or Downloaded')]/../td[2]/a",
							"IP Analytics Document not viewed or downloaded Value Link", action.SCROLLANDBOOLEAN,
							30),
					"visibility", 20, "");
			if (msg != null) {
				appLog.info("# of Documents not Viewed or Downloaded Value is displaying as Link");
			} else {
				appLog.info("# of Documents not Viewed or Downloaded Value is not displaying as Link");
				sa.assertTrue(false, "# of Documents not Viewed or Downloaded Value is not displaying as Link");
			}

			msg = isDisplayed(driver,
					FindElement(driver,
							"//div[@class='IPAnalytics_Statistics']//table//tr/td[contains(text(),'# of Contacts who have not Viewed any Document')]/../td[2]/a",
							"# of Contacts who have not Viewed any Document Value Link", action.SCROLLANDBOOLEAN, 30),
					"visibility", 20, "");
			if (msg != null) {
				appLog.info("# of Contacts who have not Viewed any Document Value is displaying as Link");
			} else {
				appLog.info("# of Contacts who have not Viewed any Document Value is not displaying as Link");
				sa.assertTrue(false, "# of Contacts who have not Viewed any Document Value is not displaying as Link");
			}
			if (fp.getIPAnalyticsMostActiveContactsLink(20) != null) {
				appLog.info("Most Active Contacts (Top 5) Value is displaying as Link");
			} else {
				appLog.info("Most Active Contacts (Top 5) Value is not displaying as Link");
				sa.assertTrue(false, "Most Active Contacts (Top 5) Value is not displaying as Link");
			}

			if (fp.getIPAnalyticsMostViewedDocumentLink(10) != null) {
				appLog.info("Most Viewed Documents (Top 5) Value is displaying as Link");
			} else {
				appLog.info("Most Viewed Documents (Top 5) Value is not displaying as Link");
				sa.assertTrue(false, "Most Viewed Documents (Top 5) Value is not displaying as Link");
			}

			if (click(driver, fp.getIPAnalyticsExportTab(30), "IP Analytics Export Tab",
					action.SCROLLANDBOOLEAN)) {
				ListofData.clear();
				ListofActualResult.clear();
				String result = "Fund Workspace Reports<break>Workspace Documents Report<break>Contact Permissions Report";
				ListofData = fp.getIPAnalyticsExportListofDealRoomReport();
				if (!ListofData.isEmpty()) {
					for (int i = 0; i < ListofData.size(); i++) {
						ListofActualResult.add(ListofData.get(i).getText().trim());
					}
					if (compareMultipleListWithoutAssertion(result, ListofActualResult)) {
						appLog.info("IP Analytics Export tab IP Reports Tab text label is verified.");
					} else {
						appLog.info(
								"IP Analytics Export tab Export Reports Tab text label is not verified. Expected: "
										+ result);
						sa.assertTrue(false,
								"IP Analytics Export tab Export Reports Tab text label is not verified. Expecetd: "
										+ result);
					}
				} else {
					appLog.info("IP Reports label text list is not visible on IP Analytics Export Tab");
					sa.assertTrue(false,
							"IP Reports label text list is not visible on IP Analytics Export tab");
				}
				ListofData.clear();
				ListofActualResult.clear();
				ListofData = fp.getIPAnalyticsListOfExportText();
				if (!ListofData.isEmpty()) {
					for (int i = 0; i < ListofData.size(); i++) {
						if (ListofData.get(i).getText().trim().contains("Export")) {
							appLog.info("Export Link is available");
						} else {
							if (i == ListofData.size() - 1) {
								appLog.info("Export link is not available on IP Analytics on Export tab");
								sa.assertTrue(false,
										"Export link is not available on IP Analytics on Export tab");
							}
						}
					}
				} else {
					appLog.info("Export Link is not visible on IP Analytics Export Tab");
					sa.assertTrue(false, "Export Link is not visible on IP Analytics Export Tab");
				}
				if (fp.getIPAnalyticsExportIconOnExportTab(10) != null) {
					appLog.info("Export Img is available on IP Analytics Export Tab");
				} else {
					appLog.info("Export Img is not available on IP Analytics Export Tab");
					sa.assertTrue(false, "Export Img is not available on IP Analytics Export Tab");
				}

			} else {
				appLog.info("Not able to click on Export Tab");
				sa.assertTrue(false, "Not able to click on Export Tab");
			}
			driver.close();
			driver.switchTo().window(parentid);

		} else {
			appLog.info("Not able to Click on IP Analytics Icon on fund: " + M14FundName1);
			sa.assertTrue(false, "Not able to Click on IP Analytics Icon on fund: " + M14FundName1);
		}
	}else {
		appLog.error("Not able to click on created fund Name so cannot check IP Analytics UI in fundraising workspace");
		sa.assertTrue(false, "Not able to click on created fund Name so cannot check IP Analytics UI in fundraising workspace");
	}
}else {
	appLog.error("Not able to click on fund tab so cannot check IP Analytics UI in fundraising workspace");
	sa.assertTrue(false, "Not able to click on fund tab so cannot check IP Analytics UI in fundraising workspace");
}
		
	lp.CRMlogout();
	sa.assertAll();
	}
	
	@Test
	public void M14tc002_3_verifyMostViewedAndMostActiveLink() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		List<String> ListofActualResult = new ArrayList<String>();
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 60,fp.getFrame(PageName.FundsPage,30));
				scrollDownThroughWebelement(driver,fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace,30), "workspace Section");
				if (click(driver, fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					if (click(driver, fp.getIPAnalyticsMostViewedDocumentLink(30), "Most View Document Link",
							action.SCROLLANDBOOLEAN)) {
						ListofActualResult.clear();
						List<WebElement> headertextlist = fp.getMostViewedDocumentHeaderTextList();
						String expectedResult = "Document Name<break>Views";
						if (!headertextlist.isEmpty()) {
							for (int i = 0; i < headertextlist.size(); i++) {
								ListofActualResult.add(headertextlist.get(i).getText().trim());
							}
							if (compareMultipleListWithoutAssertion(expectedResult, ListofActualResult)) {
								appLog.info("Most Viewed Document header text is verified");
							} else {
								appLog.info("Most Viewed Document header text is not verified");
								sa.assertTrue(false, "Most Viewed Document header text is not verified");
							}
						} else {
							appLog.info("Most Viewed Document header text list is not available");
							sa.assertTrue(false, "Most Viewed Document header text list is not available");
						}
						if (fp.getMostViewedDocumentErrorMsg(10) != null) {
							sa.assertTrue(
									fp.getMostViewedDocumentErrorMsg(10).getText().trim()
									.contains(FundsPageErrorMessage.MostViewedDocErrorMsg),
									"No data to display error message is not verified. Expected: "
											+ FundsPageErrorMessage.MostViewedDocErrorMsg);
						} else {
							appLog.info("No data to display error message is not visible on Most Viewed Document pop up");
							sa.assertTrue(false,
									"No data to display error message is not visible on Most Viewed Document pop up");
						}

						if (fp.getMostviewedDocumentViewSortingIcon(10) != null) {
							appLog.info("Sorting Icon is visible on Views Text");
						} else {
							appLog.info("Sorting Icon is not visible on Views Text");
							sa.assertTrue(false, "Sorting Icon is not visible on Views Text");
						}
						if (fp.getMostviewedDocumentCloseBtn(10) != null) {
							appLog.info("Close button is visible on Most Viewed Document pop up");
						} else {
							appLog.info("Close button is not visible on Most Viewed Document pop up");
							sa.assertTrue(false, "Close button is not visible on Most Viewed Document pop up");
						}
						if (fp.getMostViewedDocumentCloseIcon(10) != null) {
							appLog.info("Close Icon is visible on Most Viewed Document pop up");
						} else {
							appLog.info("Close Icon is not visible on Most Viewed Document pop up");
							sa.assertTrue(false, "Close Icon is not visible on Most Viewed Document pop up");
						}
						if (click(driver, fp.getMostviewedDocumentCloseBtn(10),
								"Document not viewed or download close button", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Most Viewed Document pop up Close button");
						} else {
							appLog.info("Clicked on Most Viewed Document pop up Close button");
							sa.assertTrue(false, "Clicked on CMost Viewed Document pop up Close button");
						}
					} else {
						appLog.info("Not able to click on Most Viewed Document link");
						sa.assertTrue(false, "Not able to click on Most Viewed Document link");
					}
					if (click(driver, fp.getIPAnalyticsMostActiveContactsLink(30), "Most Active Contact Link",
							action.SCROLLANDBOOLEAN)) {
						ListofActualResult.clear();
						List<WebElement> headertextlist = fp.getMostActiveContactHeaderTextList();
						String expectedResult = "Contact Name<break>Firm<break>Activity Count";
						if (!headertextlist.isEmpty()) {
							for (int i = 0; i < headertextlist.size(); i++) {
								ListofActualResult.add(headertextlist.get(i).getText().trim());
							}
							if (compareMultipleListWithoutAssertion(expectedResult, ListofActualResult)) {
								appLog.info("Most Active Contact header text is verified");
							} else {
								appLog.info("Most Active Contact header text is not verified");
								sa.assertTrue(false, "Most Active Contact header text is not verified");
							}
						} else {
							appLog.info("Most Active Contact header text list is not available");
							sa.assertTrue(false, "Most Active Contact header text list is not available");
						}
						if (fp.getMostActiveContactErrorMsg(10) != null) {
							sa.assertTrue(
									fp.getMostActiveContactErrorMsg(10).getText().trim()
									.contains(FundsPageErrorMessage.MostActiveContactErrorMsg),
									"No data to display error message is not verified. Expected: "
											+ FundsPageErrorMessage.MostActiveContactErrorMsg);
						} else {
							appLog.info("No data to display error message is not visible on Most Active Contact pop up");
							sa.assertTrue(false,
									"No data to display error message is not visible on Most Active Contact pop up");
						}
						if (fp.getGetmostviewedContactActivityCountToolTip(10) != null) {
							sa.assertTrue(
									fp.getGetmostviewedContactActivityCountToolTip(10).getAttribute("title").trim()
									.contains(FundsPageErrorMessage.MostActiveContactActivityCountToolTip),
									"Activity Count Tool Tip is not verified. Expected: "
											+ FundsPageErrorMessage.MostActiveContactActivityCountToolTip);
						} else {
							appLog.info("Activity Count tooltip is not visible on most active contact pop up");
							sa.assertTrue(false, "Activity Count tooltip is not visible on most active contact pop up");
						}
						if (fp.getMostActiveContactActivityCountSortingIcon(10) != null) {
							appLog.info("Sorting Icon is visible on Activity Count Text");
						} else {
							appLog.info("Sorting Icon is not visible on Activity Count Text");
							sa.assertTrue(false, "Sorting Icon is not visible on Activity Count Text");
						}
						if (fp.getMostActiveContactCloseBtn(10) != null) {
							appLog.info("Close button is visible on Most Active Contact pop up");
						} else {
							appLog.info("Close button is not visible on Most Active Contact pop up");
							sa.assertTrue(false, "Close button is not visible on Most Active Contact pop up");
						}
						if (fp.getMostActiveContactCloseIcon(10) != null) {
							appLog.info("Close Icon is visible on Most Active Contact pop up");
						} else {
							appLog.info("Close Icon is not visible on Most Active Contact pop up");
							sa.assertTrue(false, "Close Icon is not visible on Most Active Contact pop up");
						}
						if (click(driver, fp.getMostActiveContactCloseBtn(10), "Document not viewed or download close button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Most Active Contact pop up Close button");
						} else {
							appLog.info("Clicked on Most Active Contact pop up Close button");
							sa.assertTrue(false, "Clicked on Most Active Contact pop up Close button");
						}
					} else {
						appLog.info("Not able to click on Most Active Contact link");
						sa.assertTrue(false, "Not able to click on Most Active Contact link");
					}
					if(click(driver, fp.getIPAnalyticsDocumentNotViwedorDownloadLink(30), "document not viewed or download link", action.BOOLEAN)) {
						ListofActualResult.clear();
						List<WebElement> headertextlist = fp.getDocumentNotViewedOrDownloadHeaderText();
						String expectedResult = "Document Name";
						if (!headertextlist.isEmpty()) {
							for (int i = 0; i < headertextlist.size(); i++) {
								ListofActualResult.add(headertextlist.get(i).getText().trim());
							}
							if (compareMultipleListWithoutAssertion(expectedResult, ListofActualResult)) {
								appLog.info("document not viewed or download header text is verified");
							} else {
								appLog.info("document not viewed or download text is not verified");
								sa.assertTrue(false, "document not viewed or download text is not verified");
							}
						} else {
							appLog.info("document not viewed or download text list is not available");
							sa.assertTrue(false, "document not viewed or download text list is not available");
						}
						if (fp.getDocumentNotViewedOrDownloadedErrorMsg(10) != null) {
							sa.assertTrue(
									fp.getDocumentNotViewedOrDownloadedErrorMsg(10).getText().trim()
									.contains(FundsPageErrorMessage.DocumentNotViewedOrDownload),
									"No data to display error message is not verified. Expected: "
											+ FundsPageErrorMessage.DocumentNotViewedOrDownload);
						} else {
							appLog.info("No data to display error message is not visible on document not viewed or download pop up");
							sa.assertTrue(false,
									"No data to display error message is not visible on document not viewed or download pop up");
						}
						if (fp.getDocumentNotViewedOrDownloadTextMsg(10) != null) {
							sa.assertTrue(
									fp.getDocumentNotViewedOrDownloadTextMsg(10).getText().trim()
									.contains(FundsPageErrorMessage.DocumentNotViewedOrDownloadText),
									"Header Text message is not verified. Expected: "
											+ FundsPageErrorMessage.DocumentNotViewedOrDownloadText);
						} else {
							appLog.info("HeaderText message is not visible on document not viewed or download pop up");
							sa.assertTrue(false,
									"HeaderText message is not visible on document not viewed or download pop up");
						}
						if (fp.getDocumentNotViewedOrDownloadSortingIcon(10)!= null) {
							appLog.info("Sorting Icon is visible on document name text");
						} else {
							appLog.info("Sorting Icon is not visible on document name text");
							sa.assertTrue(false, "Sorting Icon is not visible on document name text");
						}
						if (fp.getDocumentNotViewedOrDownloadedCloseBtn(10) != null) {
							appLog.info("Close button is visible on document not viewed or download pop up");
						} else {
							appLog.info("Close button is not visible on document not viewed or download pop up");
							sa.assertTrue(false, "Close button is not visible on document not viewed or download pop up");
						}
						if (fp.getDocumentNotViewedOrDownloadedCloseIcon(10) != null) {
							appLog.info("Close Icon is visible on document not viewed or download pop up");
						} else {
							appLog.info("Close Icon is not visible on document not viewed or download pop up");
							sa.assertTrue(false, "Close Icon is not visible on document not viewed or download pop up");
						}
						if (click(driver, fp.getDocumentNotViewedOrDownloadedCloseBtn(10), "Document not viewed or download close button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on document not viewed or download pop up Close button");
						} else {
							appLog.info("Clicked on document not viewed or download pop up Close button");
							sa.assertTrue(false, "Clicked on document not viewed or download pop up Close button");
						}
					}else {
						appLog.error("Not able to click on document not viewed or download link so cannot check document not viewed or download pop up");
						sa.assertTrue(false, "Not able to click on document not viewed or download link so cannot check document not viewed or download pop up");
					}
					if(click(driver, fp.getIPAnalyticsnotViewedAnyDocumentLink(10), "contact not viewed any document", action.BOOLEAN)) {
						ListofActualResult.clear();
						List<WebElement> headertextlist = fp.getContactNotViewedAnyDocumentHeaderTextList();
						String expectedResult = "Contact Name<break>Firm";
						if (!headertextlist.isEmpty()) {
							for (int i = 0; i < headertextlist.size(); i++) {
								ListofActualResult.add(headertextlist.get(i).getText().trim());
							}
							if (compareMultipleListWithoutAssertion(expectedResult, ListofActualResult)) {
								appLog.info("contact who have not viewed any document header text is verified");
							} else {
								appLog.info("contact who have not viewed any document text is not verified");
								sa.assertTrue(false, "contact who have not viewed any document text is not verified");
							}
						} else {
							appLog.info("contact who have not viewed any document text list is not available");
							sa.assertTrue(false, "contact who have not viewed any document text list is not available");
						}
						if (fp.getContactNotViewedAnyDocumentErrorMsg(10) != null) {
							sa.assertTrue(
									fp.getContactNotViewedAnyDocumentErrorMsg(10).getText().trim()
									.contains(FundsPageErrorMessage.DocumentNotViewedOrDownload),
									"No data to display error message is not verified. Expected: "
											+ FundsPageErrorMessage.DocumentNotViewedOrDownload);
						} else {
							appLog.info("No data to display error message is not visible on contact who have not viewed any document pop up");
							sa.assertTrue(false,
									"No data to display error message is not visible on contact who have not viewed any document pop up");
						}
						if (fp.getContactNotViewedAnyDocument(10) != null) {
							sa.assertTrue(
									fp.getContactNotViewedAnyDocument(10).getText().trim()
									.contains(FundsPageErrorMessage.ContactNotViewedAnyDocumentHeaderText),
									"Header Text message is not verified. Expected: "
											+ FundsPageErrorMessage.ContactNotViewedAnyDocumentHeaderText);
						} else {
							appLog.info("HeaderText message is not visible on contact who have not viewed any document pop up");
							sa.assertTrue(false,
									"HeaderText message is not visible on contact who have not viewed any document pop up");
						}
						if (fp.getContactNotViewedAnyDocumentSortingIcon()!= null) {
							appLog.info("Sorting Icon is visible on Contact name text");
						} else {
							appLog.info("Sorting Icon is not visible on Contact name text");
							sa.assertTrue(false, "Sorting Icon is not visible on Contact name text");
						}
						if (fp.getContactNotViewedAnyDocumentCloseBtn(10) != null) {
							appLog.info("Close button is visible on contact who have not viewed any document pop up");
						} else {
							appLog.info("Close button is not visible on contact who have not viewed any document pop up");
							sa.assertTrue(false, "Close button is not visible on contact who have not viewed any document pop up");
						}
						if (fp.getContactNotViewedAnyDocumentCloseIcon(10) != null) {
							appLog.info("Close Icon is visible on contact who have not viewed any document pop up");
						} else {
							appLog.info("Close Icon is not visible on contact who have not viewed any document pop up");
							sa.assertTrue(false, "Close Icon is not visible on contact who have not viewed any document pop up");
						}
						if (click(driver, fp.getContactNotViewedAnyDocumentCloseBtn(10), "contact not viewed any document",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on contact who have not viewed any document pop up Close button");
						} else {
							appLog.info("Clicked on contact who have not viewed any document pop up Close button");
							sa.assertTrue(false, "Clicked on contact who have not viewed any document pop up Close button");
						}
					}else {
						appLog.error("Contact who have not viewed any document link is not visible so cannot check pop up");
						sa.assertTrue(false, "Contact who have not viewed any document link is not visible so cannot check pop up");
					}
					
					
					
					
					
					
					driver.close();
					driver.switchTo().window(parentid);

				} else {
					appLog.info("Not able to Click on IP Analytics Icon on fund: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on IP Analytics Icon on fund: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created fund "+M14FundName1+" so cannot check Most Active Contact and Most Viewed Document Link");
				sa.assertTrue(false, "Not able to click on created fund "+M14FundName1+" so cannot check Most Active Contact and Most Viewed Document Link");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot check Most Active Contact and Most Viewed Document Link");
			sa.assertTrue(false, "Not able to click on fund tab so cannot check Most Active Contact and Most Viewed Document Link");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc003_inviteContactInFRW() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String sharedfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				if(fp.inviteContact(M14Institution1,M14Contact1EmailId,null, FolderType.Standard,"Upload", "Yes","Yes","All Folders", Workspace.FundraisingWorkspace,null,"Yes",true)) {
					appLog.info("contact is invites successfully from fundraising workspace: "+M14Contact1FirstName+" "+M14Contact1LastName+" from "+M14Institution1);
				}else {
					appLog.error("Not able to invite contact from fundraising workspace: "+M14Contact1FirstName+" "+M14Contact1LastName+" from "+M14Institution1);
					sa.assertTrue(false, "Not able to invite contact from fundraising workspace: "+M14Contact1FirstName+" "+M14Contact1LastName+" from "+M14Institution1);
				}
				if(fp.inviteContact(M14Institution2,M14Contact1EmailId,null, FolderType.Standard,"Upload", "Yes",null,"All Folders", Workspace.FundraisingWorkspace,M14Contact1EmailId,"No",false)) {
					appLog.info("contact is invites successfully from fundraising workspace: "+M14Contact1FirstName+" "+M14Contact1LastName+" from "+M14Institution2);
				}else {
					appLog.error("Not able to invite contact from fundraising workspace: "+M14Contact1FirstName+" "+M14Contact1LastName+" from "+M14Institution2);
					sa.assertTrue(false, "Not able to invite contact from fundraising workspace: "+M14Contact1FirstName+" "+M14Contact1LastName+" from "+M14Institution2);
				}
				if(fp.inviteContact(M14Institution2,M14Contact2EmailId,null, FolderType.Standard,"Upload", "Yes","Yes","All Folders", Workspace.FundraisingWorkspace,M14Contact2EmailId,"Yes",true)) {
					appLog.info("contact is invites successfully from fundraising workspace: "+M14Contact2FirstName+" "+M14Contact2LastName+" from "+M14Institution2);
				}else {
					appLog.error("Not able to invite contact from fundraising workspace: "+M14Contact2FirstName+" "+M14Contact2LastName+" from "+M14Institution2);
					sa.assertTrue(false, "Not able to invite contact from fundraising workspace: "+M14Contact2FirstName+" "+M14Contact2LastName+" from "+M14Institution2);
				}
				if(fp.inviteContact(null,M14Contact1EmailId,sharedfolderpath, FolderType.Shared,"download", "Yes",null,"All Folders", Workspace.FundraisingWorkspace,null,"No",false)) {
					appLog.info("contact is invites successfully from fundraising workspace: "+M14Contact1FirstName+" "+M14Contact1LastName+" from "+sharedfolderpath);
				}else {
					appLog.error("Not able to invite contact from fundraising workspace: "+M14Contact1FirstName+" "+M14Contact1LastName+" from "+sharedfolderpath);
					sa.assertTrue(false, "Not able to invite contact from fundraising workspace: "+M14Contact1FirstName+" "+M14Contact1LastName+" from "+sharedfolderpath);
				}
				if(fp.inviteContact(null,M14Contact2EmailId,sharedfolderpath, FolderType.Shared,"download", "Yes",null,"All Folders", Workspace.FundraisingWorkspace,null,"No",false)) {
					appLog.info("contact is invites successfully from fundraising workspace: "+M14Contact2FirstName+" "+M14Contact2LastName+" from "+sharedfolderpath);
				}else {
					appLog.error("Not able to invite contact from fundraising workspace: "+M14Contact2FirstName+" "+M14Contact2LastName+" from "+sharedfolderpath);
					sa.assertTrue(false, "Not able to invite contact from fundraising workspace: "+M14Contact2FirstName+" "+M14Contact2LastName+" from "+sharedfolderpath);
				}
				switchToFrame(driver, 30,bp.getFrame(PageName.FundsPage, 30));
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(FRW_UniqueDocument, FRW_DocumentViews,
							FRW_DocumentDownload, FRW_DocumentNotViewedOrDownload, FRW_ContactGrantedAccess,FRW_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					SoftAssert activity= fp.verifyMostActiveContactInIPAnalytics(null, null, null);
					sa.combineAssertions(activity);
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics(null, null,Workspace.FundraisingWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
				
			}else {
				appLog.error("Not able to click on created fund Name so cannot build fundraising and investor workspace: "+M14FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot build fundraising and investor workspace: "+M14FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot build fundraising workspace and investor: "+M14FundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot build fundraising workspace and investor: "+M14FundName1);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc004_1_uploadFilesInFWRAndCheckIPAnalytics() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String sharedfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath);
		String commonfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath);
		String internalfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.InternalPath);
		String standardfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		String stddocpath="UploadFiles\\Module14\\CRM_Side\\FRW\\Standard";
		String shrddocpath="UploadFiles\\Module14\\CRM_Side\\FRW\\Shared";
		String commondocpath="UploadFiles\\Module14\\CRM_Side\\FRW\\Common";
		String internaldocpath="UploadFiles\\Module14\\CRM_Side\\FRW\\Internal";
		String stdfilesName=null,shrdfileName = null,CommonfileName=null;
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.FundraisingWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
				if(fp.uploadFile(standardfolderpath,M14Institution1+"<break>"+M14Institution2, stddocpath,null,UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly in folder: "+standardfolderpath);
					switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						 stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
						if(stdfilesName!=null) {
							List<String>uploadFiles=compareMultipleListOnBasisOfTitle(driver,stdfilesName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!uploadFiles.isEmpty()) {
								for(int i=0;i<uploadFiles.size();i++) {
									sa.assertTrue(false, uploadFiles.get(i));
								}
							}else {
								appLog.info("All uploaded files are verified: "+stdfilesName);
								CommonVariables.FRW_UniqueDocument = uniquedocs.size();
								CommonVariables.FRW_DocumentNotViewedOrDownload = uniquedocs.size();
								ExcelUtils.writeData(FRW_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.FRW_Value);
								ExcelUtils.writeData(FRW_DocumentNotViewedOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.FRW_Value);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents in standard folder.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents in standard folder");
					}
				}else {
					appLog.error("File is not uploaded in folder "+standardfolderpath);
					sa.assertTrue(false, "File is not uploaded in "+standardfolderpath);
				}
				switchToDefaultContent(driver);
				if(fp.uploadFile(sharedfolderpath,null, shrddocpath,null,UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly in folder: "+sharedfolderpath);
					switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						 shrdfileName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileShared);
						if(shrdfileName!=null) {
							List<String>uploadFiles=compareMultipleListOnBasisOfTitle(driver,shrdfileName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!uploadFiles.isEmpty()) {
								for(int i=0;i<uploadFiles.size();i++) {
									sa.assertTrue(false, uploadFiles.get(i));
								}
							}else {
								appLog.info("All uploaded files are verified: "+shrdfileName);
								CommonVariables.FRW_UniqueDocument = uniquedocs.size();
								CommonVariables.FRW_DocumentNotViewedOrDownload = uniquedocs.size();
								ExcelUtils.writeData(FRW_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.FRW_Value);
								ExcelUtils.writeData(FRW_DocumentNotViewedOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.FRW_Value);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents in shared folder.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents in shared folder");
					}
				}else {
					appLog.error("File is not uploaded in folder "+sharedfolderpath);
					sa.assertTrue(false, "File is not uploaded in "+sharedfolderpath);
				}
				switchToDefaultContent(driver);
				if(fp.uploadFile(commonfolderpath,null, commondocpath,null,UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly in folder: "+commonfolderpath);
					switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						 CommonfileName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
						if(CommonfileName!=null) {
							List<String>uploadFiles=compareMultipleListOnBasisOfTitle(driver,CommonfileName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!uploadFiles.isEmpty()) {
								for(int i=0;i<uploadFiles.size();i++) {
									sa.assertTrue(false, uploadFiles.get(i));
								}
							}else {
								appLog.info("All uploaded files are verified: "+CommonfileName);
								CommonVariables.FRW_UniqueDocument = uniquedocs.size();
								CommonVariables.FRW_DocumentNotViewedOrDownload = uniquedocs.size();
								ExcelUtils.writeData(FRW_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.FRW_Value);
								ExcelUtils.writeData(FRW_DocumentNotViewedOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.FRW_Value);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents in common folder.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents in common folder");
					}
				}else {
					appLog.error("File is not uploaded in folder "+commonfolderpath);
					sa.assertTrue(false, "File is not uploaded in "+commonfolderpath);
				}
				switchToDefaultContent(driver);
				if(fp.uploadFile(internalfolderpath,null, internaldocpath,null,UploadFileActions.Upload, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly in folder: "+internalfolderpath);
					switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileInternal);
						if(filesName!=null) {
							List<String>uploadFiles=compareMultipleListOnBasisOfTitle(driver,filesName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!uploadFiles.isEmpty()) {
								for(int i=0;i<uploadFiles.size();i++) {
									sa.assertTrue(false, uploadFiles.get(i));
								}
							}else {
								appLog.info("All uploaded files are verified: "+filesName);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents in common folder.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents in common folder");
					}
				}else {
					appLog.error("File is not uploaded in folder "+internalfolderpath);
					sa.assertTrue(false, "File is not uploaded in "+internalfolderpath);
				}
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(FRW_UniqueDocument, FRW_DocumentViews,
							FRW_DocumentDownload, FRW_DocumentNotViewedOrDownload, FRW_ContactGrantedAccess,FRW_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					SoftAssert activity= fp.verifyMostActiveContactInIPAnalytics(null, null, null);
					sa.combineAssertions(activity);
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics(null, null,Workspace.FundraisingWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documentNotViewed=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(stdfilesName+"<break>"+shrdfileName+"<break>"+CommonfileName, false);
					sa.combineAssertions(documentNotViewed);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(M14Contact1FirstName+" "+M14Contact1LastName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, M14Institution1+"<break>"+M14Institution1);
					sa.combineAssertions(rsult);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc005_1_registerContact1() {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer af = new AllFirmsPageBusinesslayer(driver);
		InvestorProfileBusinessLayer ip = new InvestorProfileBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.FundraisingWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		 bp = new BasePageBusinessLayer(driver);
		 lp=new LoginPageBusinessLayer(driver);
		 fp = new FundsPageBusinessLayer(driver);
		 af = new AllFirmsPageBusinesslayer(driver);
		 ip = new InvestorProfileBusinessLayer(driver);
		if (ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Registered).equalsIgnoreCase("Not Registered")) {
			String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
			String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
			String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
			try {
				investorRegLink = new EmailLib().getInvestorRegLink("InvitationMail",gmailUserName,gmailPassword,CRMUser1EmailID,M14Contact1EmailId);
				appLog.info("Investor Registration Link: " + investorRegLink);
			} catch (InterruptedException e) {
				appLog.info("Invitation mail not found.");
				e.printStackTrace();
			}
			if (investorRegLink != null) {
				driver.get(investorRegLink);
				if (bp.targetRegistration(updatedContactFName, updatedContactLName, M14Contact1EmailId, M14Institution1,
						adminPassword)) {
					appLog.info(
							"Investor is registered successfully: " + updatedContactFName + " " + updatedContactLName);
					ExcelUtils.writeData("Registered", "Contacts",excelLabel.Variable_Name, "M14Contact1", excelLabel.Registered);
				} else {
					driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
					appLog.info("Investor Target URL through Direct URL..");
					if (bp.targetRegistration(updatedContactFName, updatedContactLName, M14Contact1EmailId,
							M14Institution1, adminPassword)) {
						appLog.info("Investor is registered successfully: " + updatedContactFName + " "
								+ updatedContactLName);
						ExcelUtils.writeData("Registered", "Contacts",excelLabel.Variable_Name, "M14Contact1", excelLabel.Registered);
					} else {
						appLog.error("Investor is not Registered through Invited Link: " + updatedContactFName + " "
								+ updatedContactLName);
						sa.assertTrue(false, "Investor is not Registered through Invited Link: " + updatedContactFName
								+ " " + updatedContactLName);
					}
				}
			} else {
				driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
				appLog.info("Register Investor URL through Direct URL..");
				if (bp.targetRegistration(updatedContactFName, updatedContactLName, M14Contact1EmailId,
						M14Institution1, adminPassword)) {
					appLog.info("Investor is registered successfully: " + updatedContactFName + " "
							+ updatedContactLName);
					ExcelUtils.writeData("Registered", "Contacts",excelLabel.Variable_Name, "M14Contact1", excelLabel.Registered);
				} else {
					appLog.error("Investor is not Registered through Invited Link: " + updatedContactFName + " "
							+ updatedContactLName);
					sa.assertTrue(false, "Investor is not Registered through Invited Link: " + updatedContactFName
							+ " " + updatedContactLName);
				}
			}
			if (click(driver, af.getProfileLink(60), "profile link on investor login", action.SCROLLANDBOOLEAN)) {
				if (click(driver,ip.getMyFirmProfileTab(60) , "my firm profile tab", action.BOOLEAN)) {
					if (click(driver, ip.getEditIcon(60), "edit icon on myfirm profile page", action.BOOLEAN)) {
						if (sendKeys(driver, ip.getFirmNameTextbox(60), updatedInsName, "firm name textbox on my firms profile page", action.SCROLLANDBOOLEAN)) {
							if(click(driver, ip.getSaveButtonFirmProfile(60), "save button my firm profile page", action.SCROLLANDBOOLEAN)) {
								appLog.info("clicked on my firm profile save button");
							}else {
								appLog.error("Not able to save my firm profile so cannot update my firm profile name");
								sa.assertTrue(false, "Not able to save my firm profile so cannot update my firm profile name");
							}
						}else {
							appLog.error("Not able to pass value in firm profile text box: "+updatedInsName);
							sa.assertTrue(false, "Not able to pass value in firm profile text box: "+updatedInsName);
						}
					}else {
						appLog.error("Not able to click on edit icon so cannot update firm name");
						sa.assertTrue(false, "Not able to click on edit icon so cannot update firm name");
					}
				}else {
					appLog.error("Not able to click on My firm profile tab so cannot update firm profile name");
					sa.assertTrue(false, "Not able to click on My firm profile tab so cannot update firm profile name");
				}
			}else {
				appLog.error("Not able to click on My profile tab so cannot update firm profile name");
				sa.assertTrue(false, "Not able to click on My profile tab so cannot update firm profile name");
			}
		lp.investorLogout();
		} else {
			appLog.info("investor "+M14Contact1FirstName+" "+M14Contact1LastName+" is already Registered.");
			sa.assertTrue(false, "investor "+M14Contact1FirstName+" "+M14Contact1LastName+" is already Registered.");
		}
		sa.assertAll();
	}
	
	@Test
	public void M14tc005_2_registerContact2() {
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		if (ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact2", excelLabel.Registered).equalsIgnoreCase("Not Registered")) {
			try {
				investorRegLink = new EmailLib().getInvestorRegLink("InvitationMail",gmailUserName,gmailPassword,CRMUser1EmailID,M14Contact2EmailId);
				appLog.info("Investor Registration Link: " + investorRegLink);
			} catch (InterruptedException e) {
				appLog.info("Invitation mail not found.");
				e.printStackTrace();
			}
			if (investorRegLink != null) {
				driver.get(investorRegLink);
				if (bp.targetRegistration(M14Contact2FirstName, M14Contact2LastName, M14Contact2EmailId, M14Institution1,
						adminPassword)) {
					appLog.info(
							"Investor is registered successfully: " + M14Contact2FirstName + " " + M14Contact2LastName);
					ExcelUtils.writeData("Registered", "Contacts",excelLabel.Variable_Name, "M14Contact2", excelLabel.Registered);
				} else {
					driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
					appLog.info("Investor Target URL through Direct URL..");
					if (bp.targetRegistration(M14Contact2FirstName, M14Contact2LastName, M14Contact2EmailId,
							M14Institution1, adminPassword)) {
						appLog.info("Investor is registered successfully: " + M14Contact2FirstName + " "
								+ M14Contact2LastName);
						ExcelUtils.writeData("Registered", "Contacts",excelLabel.Variable_Name, "M14Contact2", excelLabel.Registered);
					} else {
						appLog.error("Investor is not Registered through Invited Link: " + M14Contact2FirstName + " "
								+ M14Contact2LastName);
						sa.assertTrue(false, "Investor is not Registered through Invited Link: " + M14Contact2FirstName
								+ " " + M14Contact2LastName);
					}
				}
			} else {
				driver.get(ExcelUtils.readDataFromPropertyFile("InvestorRegistrationURL"));
				appLog.info("Register Investor URL through Direct URL..");
				if (bp.targetRegistration(M14Contact2FirstName, M14Contact2LastName, M14Contact2EmailId,
						M14Institution1, adminPassword)) {
					appLog.info("Investor is registered successfully: " + M14Contact2FirstName + " "
							+ M14Contact2LastName);
					ExcelUtils.writeData("Registered", "Contacts",excelLabel.Variable_Name, "M14Contact2", excelLabel.Registered);
				} else {
					appLog.error("Investor is not Registered through Invited Link: " + M14Contact2FirstName + " "
							+ M14Contact2LastName);
					sa.assertTrue(false, "Investor is not Registered through Invited Link: " + M14Contact2FirstName
							+ " " + M14Contact2LastName);
				}
			}		
		lp.investorLogout();
		} else {
			appLog.info("investor "+M14Contact2FirstName+" "+M14Contact2LastName+" is already Registered.");
			sa.assertTrue(false, "investor "+M14Contact2FirstName+" "+M14Contact2LastName+" is already Registered.");
		}
		sa.assertAll();
	}
	
	@Test
	public void M14tc005_3_verifyIPAnalytics() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependOnTc="M14tc004_1_uploadFilesInFWRAndCheckIPAnalytics";
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileStandard);
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileCommon);
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependOnTc, excelLabel.UploadedFileShared);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view");
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(FRW_UniqueDocument, FRW_DocumentViews,
							FRW_DocumentDownload, FRW_DocumentNotViewedOrDownload, FRW_ContactGrantedAccess,FRW_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					SoftAssert activity=fp.verifyMostActiveContactInIPAnalytics(null, null, null);
					sa.combineAssertions(activity);
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics(null, null,Workspace.FundraisingWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(stdfilesName+"<break>"+commonfilesName+"<break>"+shrdfilesName, false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1);
					sa.combineAssertions(rsult);
					if(click(driver, fp.getIPAnalyticsnotViewedAnyDocumentLink(10),"Document Not Viewed any document link", action.BOOLEAN)) {
						if(fp.getContactNotViewedAnyDocumentSortingIcon().get(0)!=null) {
							appLog.info("Default Sorting icon is visible on contact name in contact not viewed or download pop up");
						}else {
							appLog.error("Default Sorting icon is not visible on contact name in contact not viewed or download pop up");
							sa.assertTrue(false, "Default Sorting icon is not visible on contact name in contact not viewed or download pop up");
						}
						List<WebElement> sortingIcon=fp.getContactNotViewedAnyDocumentSortingIcon();
						for(int i=0; i<4; i++) {
							if(i==0) {
								if(click(driver, sortingIcon.get(i), "sorting icon", action.BOOLEAN)) {
									List<WebElement> contactNameList=fp.getContactNotViewedAnyDocumentContactNameList();
									if(fp.checkSorting(SortOrder.Decending, contactNameList)) {
										appLog.info("Contact Name is displaying in decending order");
									}else {
										appLog.error("Contact Name is not displaying in decending order");
										sa.assertTrue(false, "Contact Name is not displaying in decending order");
									}
								}else {
									appLog.error("Not able to click on sorting icon so cannot check sorting on contact name");
									sa.assertTrue(false, "Not able to click on sorting icon so cannot check sorting on contact name");
								}
							}
							sortingIcon=fp.getContactNotViewedAnyDocumentSortingIcon();
							if(i==1) {
								if(click(driver, sortingIcon.get(i-1), "sorting icon", action.BOOLEAN)) {
									List<WebElement> contactNameList=fp.getContactNotViewedAnyDocumentContactNameList();
									if(fp.checkSorting(SortOrder.Assecending, contactNameList)) {
										appLog.info("Contact Name is displaying in assecending order");
									}else {
										appLog.error("Contact Name is not displaying in assecending order");
										sa.assertTrue(false, "Contact Name is not displaying in assecending order");
									}
								}else {
									appLog.error("Not able to click on sorting icon so cannot check sorting on contact name");
									sa.assertTrue(false, "Not able to click on sorting icon so cannot check sorting on contact name");
								}
							}
							if(i==2) {
								List<WebElement> headertext= fp.getContactNotViewedAnyDocumentHeaderTextList();
								if(click(driver, headertext.get(1), "firm name header text", action.BOOLEAN)) {
									sortingIcon=fp.getContactNotViewedAnyDocumentSortingIcon();
									if(click(driver, sortingIcon.get(i-1), "sorting icon", action.BOOLEAN)) {
										List<WebElement> contactNameList=fp.getContactNotViewedAnyDocumentFirmNameList();
										if(fp.checkSorting(SortOrder.Decending, contactNameList)) {
											appLog.info("Firm Name is displaying in Decending order");
										}else {
											appLog.error("Firm Name is not displaying in Decending order");
											sa.assertTrue(false, "Firm Name is not displaying in Decending order");
										}
									}else {
										appLog.error("Not able to click on sorting icon so cannot check sorting on Firm name");
										sa.assertTrue(false, "Not able to click on sorting icon so cannot check sorting on Firm name");
									}
								}else {
									appLog.error("Not able to click on firm name header text so cannot click on firm name sorting on firm name column");
									sa.assertTrue(false, "Not able to click on firm name header text so cannot click on firm name sorting on firm name column");
								}
							}
							sortingIcon=fp.getContactNotViewedAnyDocumentSortingIcon();
							if(i==3) {
								if(click(driver, sortingIcon.get(i-2), "sorting icon", action.BOOLEAN)) {
									List<WebElement> contactNameList=fp.getContactNotViewedAnyDocumentFirmNameList();
									if(fp.checkSorting(SortOrder.Assecending, contactNameList)) {
										appLog.info("Firm Name is displaying in Assecending order");
									}else {
										appLog.error("Firm Name is not displaying in Assecending order");
										sa.assertTrue(false, "Firm Name is not displaying in Assecending order");
									}
								}else {
									appLog.error("Not able to click on sorting icon so cannot check sorting on Firm name");
									sa.assertTrue(false, "Not able to click on sorting icon so cannot check sorting on Firm name");
								}
							}
						}
						if(click(driver, fp.getContactNotViewedAnyDocumentCloseBtn(10), "document not viewed any document close button", action.BOOLEAN)) {
							appLog.info("clicked on close button");
						}else {
							appLog.error("Not able to click on document not viewed any document close button");
							sa.assertTrue(false, "Not able to click on document not viewed any document close button");
						}
						
					}else {
						appLog.error("Not able to click on document not viewed or download pop up so cannot verify Sorting");
						sa.assertTrue(false, "Not able to click on document not viewed or download pop up so cannot verify Sorting");
					}
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc006_1_uploadFilesInvestorSideAndCheckImpactAtAnalytics() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String docpath="UploadFiles\\Module14\\Investor_Side\\Potential_Investment\\Std";
		String filesName =ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.UploadedFileStandard);
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath);
		String 	date = getSystemDate("MM/dd/yyyy")+previousOrForwardDate(-1, "MM/dd/yyyy");
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.FundraisingWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp=new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M14Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			if (ifp.uploadUpdateFileInvestorSide(stdPath, filesName.split("<break>")[0], M14Institution1, null, FolderType.Standard, docpath, "yes", 30,PageName.PotentialInvestmentPage, null, null, WorkSpaceAction.UPLOAD)) {
				appLog.info("file is Upload Successfully in potential investment in folder: "+stdPath);
				M14Contact1_ActivityCount=String.valueOf((Integer.parseInt(M14Contact1_ActivityCount)+mostActiveContactActivityCount));
				ExcelUtils.writeData(CommonVariables.M14Contact1_ActivityCount, "Contacts", excelLabel.Variable_Name, "M14Contact1", excelLabel.Activity_Count);
				if(click(driver, ifp.getRefreshIcon(20), "Refresh Icon", action.SCROLLANDBOOLEAN)){
					SoftAssert saa = ifp.verifyContentGridInvestorSide(driver, PageName.PotentialInvestmentPage, filesName.split("<break>")[0],updatedContactFName+ " " + updatedContactLName, date);
					sa.combineAssertions(saa);
				}else{
					appLog.error("Not Able to Click Refresh Icon so Cannot check Upoaded File");
					sa.assertTrue(false, "Not Able to Click Refresh Icon so Cannot check Upoaded File");	
				}
			}else{
				appLog.error("Upload Unsuccessful");
				sa.assertTrue(false, "Upload Unsuccessful");	
			}
			if (ifp.uploadUpdateFileInvestorSide(stdPath, filesName.split("<break>")[1], M14Institution1, null, FolderType.Standard, docpath, "yes", 30,PageName.PotentialInvestmentPage, null, null, WorkSpaceAction.UPLOAD)) {
				appLog.info("file is Upload Successfully in potential investment in folder: "+stdPath);
				M14Contact1_ActivityCount=String.valueOf((Integer.parseInt(M14Contact1_ActivityCount)+mostActiveContactActivityCount));
				ExcelUtils.writeData(CommonVariables.M14Contact1_ActivityCount, "Contacts", excelLabel.Variable_Name, "M14Contact1", excelLabel.Activity_Count);
				if(click(driver, ifp.getRefreshIcon(20), "Refresh Icon", action.SCROLLANDBOOLEAN)){
					SoftAssert saa = ifp.verifyContentGridInvestorSide(driver, PageName.PotentialInvestmentPage, filesName.split("<break>")[1],updatedContactFName+ " " + updatedContactLName, date);
					sa.combineAssertions(saa);
				}else{
					appLog.error("Not Able to Click Refresh Icon so Cannot check Upoaded File");
					sa.assertTrue(false, "Not Able to Click Refresh Icon so Cannot check Upoaded File");	
				}
			}else{
				appLog.error("Upload Unsuccessful");
				sa.assertTrue(false, "Upload Unsuccessful");	
			}
			
		} else {
			appLog.info("Not able to click on Potential Investment tab");
			sa.assertTrue(false, "Not able to click on Potential Investment tab");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc006_2_checkImpactAtIPAnalytics() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependOnTc="M14tc006_1_uploadFilesInvestorSideAndCheckImpactAtAnalytics";
		String filesName =ExcelUtils.readData("filepath", excelLabel.TestCases_Name, dependOnTc,excelLabel.UploadedFileStandard);
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view");
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(FRW_UniqueDocument, FRW_DocumentViews,
							FRW_DocumentDownload, FRW_DocumentNotViewedOrDownload, FRW_ContactGrantedAccess,FRW_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics(null, null,Workspace.FundraisingWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(filesName, false);
					boolean flag = true;
					try {
						documents.assertAll();
					}catch (Throwable e) {
						// TODO: handle exception
						flag =false;
					}
					if(!flag) {
						appLog.info("investor side upload file is not available in document not viewed or download pop up");
					}else {
						appLog.error("investor side upload file is visible in document not viewed or download pop up");
						sa.assertTrue(false, "investor side upload file is visible in document not viewed or download pop up");
					}
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName, updatedInsName, M14Contact1_ActivityCount);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc007_accessDocFromCRMSideAndCheckImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer contact= new ContactPageBusinessLayer(driver);
		String dependONTC="M14tc004_1_uploadFilesInFWRAndCheckIPAnalytics";
		String sharedfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath);
		String stdfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		String commonfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath);
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileStandard);
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileCommon);
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileShared);
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		String[] fileNames= {stdfilesName,commonfilesName,shrdfilesName};
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.FundraisingWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
				switchToFrame(driver, 60, fp.getFrame(PageName.FundsPage, 60));
				if(fp.verifyFolderPathdummy(stdfolderpath, M14Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace,60)) {
					if (bp.verifyDownloadFunctionality(PageName.FundsPage, Workspace.FundraisingWorkspace, stdfilesName, false, false, false)) {
						appLog.info("clicked on upload file successfully: "+stdfilesName);
					}
					else {
						appLog.error("Not able to click on file name: "+stdfilesName);
						sa.assertTrue(false, "Not able to click on file name: "+stdfilesName);
					}
				}else {
					appLog.error("Not able to click on folder: "+stdfolderpath+" so cannot click on uploaded file");
					sa.assertTrue(false, "Not able to click on folder: "+stdfolderpath+" so cannot click on uploaded file");
				}
				switchToFrame(driver, 60, fp.getFrame(PageName.FundsPage, 60));
				if(fp.verifyFolderPathdummy(commonfolderpath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace,60)) {
					if (bp.verifyDownloadFunctionality(PageName.FundsPage, Workspace.FundraisingWorkspace, commonfilesName, false, false, false)) {
						appLog.info("clicked on upload file successfully: "+commonfilesName);
					}
					else {
						appLog.error("Not able to click on file name: "+commonfilesName);
						sa.assertTrue(false, "Not able to click on file name: "+commonfilesName);
					}
				}else {
					appLog.error("Not able to click on folder: "+commonfolderpath+" so cannot click on uploaded file");
					sa.assertTrue(false, "Not able to click on folder: "+commonfolderpath+" so cannot click on uploaded file");
				}
				switchToFrame(driver, 60, fp.getFrame(PageName.FundsPage, 60));
				if(fp.verifyFolderPathdummy(sharedfolderpath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace,60)) {
					if (bp.verifyDownloadFunctionality(PageName.FundsPage, Workspace.FundraisingWorkspace, shrdfilesName, false, false, false)) {
						appLog.info("clicked on upload file successfully: "+shrdfilesName);
					}
					else {
						appLog.error("Not able to click on file name: "+shrdfilesName);
						sa.assertTrue(false, "Not able to click on file name: "+shrdfilesName);
					}
				}else {
					appLog.error("Not able to click on folder: "+sharedfolderpath+" so cannot click on uploaded file");
					sa.assertTrue(false, "Not able to click on folder: "+sharedfolderpath+" so cannot click on uploaded file");
				}
				switchToFrame(driver, 60, fp.getFrame(PageName.FundsPage, 60));
				if(fp.verifyFolderPathdummy(stdfolderpath, M14Institution2, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace,60)) {
					if (bp.verifyDownloadFunctionality(PageName.FundsPage, Workspace.FundraisingWorkspace, stdfilesName, false, false, false)) {
						appLog.info("clicked on upload file successfully: "+stdfilesName);
					}
					else {
						appLog.error("Not able to click on file name: "+stdfilesName);
						sa.assertTrue(false, "Not able to click on file name: "+stdfilesName);
					}
				}else {
					appLog.error("Not able to click on folder: "+stdfolderpath+" so cannot click on uploaded file");
					sa.assertTrue(false, "Not able to click on folder: "+stdfolderpath+" so cannot click on uploaded file");
				}
				switchToFrame(driver, 30,bp.getFrame(PageName.FundsPage, 30));
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					String childWindowID = null;
					String childChildWindow = null;
					Set<String> lst1 = driver.getWindowHandles();
					Iterator<String> I1 = lst1.iterator();
					while (I1.hasNext()) {
						String windowID = I1.next();
						if (windowID.equalsIgnoreCase(parentid)) {
							appLog.info("Parent Id is Matched");
						} else {
							childWindowID = windowID;
							appLog.info("Stroged child Window Id");
							break;
						}
					}
					List<String> result = fp.verifyIPAnalyticsTargetValues(FRW_UniqueDocument, FRW_DocumentViews,
							FRW_DocumentDownload, FRW_DocumentNotViewedOrDownload, FRW_ContactGrantedAccess,FRW_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics(null, null,Workspace.FundraisingWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(stdfilesName+"<break>"+commonfilesName+"<break>"+shrdfilesName, false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName, updatedInsName, M14Contact1_ActivityCount);
					sa.combineAssertions(activityCount);
					if (click(driver, fp.getIPAnalyticsDocumentNotViwedorDownloadLink(30), "Document not viewed or download link",action.SCROLLANDBOOLEAN)) {
						for(int i=0; i<fileNames.length; i++) {
							if(!fileNames[i].isEmpty()) {
								WebElement ele=FindElement(driver, "//a[@text='"+fileNames[i]+"']",fileNames[i]+"  file Name link", action.BOOLEAN, 10);
								if(ele!=null) {
									if(click(driver, ele, "file name", action.BOOLEAN)) {
										appLog.info("Clicked on File Name : "+fileNames[i]);
										Set<String>lst11 = driver.getWindowHandles();
										Iterator<String> II1 = lst11.iterator();
										while (II1.hasNext()) {
											String windowID = II1.next();
											if (windowID.equalsIgnoreCase(parentid) || windowID.equalsIgnoreCase(childWindowID)) {
												appLog.info("window Id is Matched");
											} else {
												childChildWindow = windowID;
												appLog.info("Stroged child child Window Id");
												driver.switchTo().window(childChildWindow);
												break;
											}
										}
										if(fp.getDownloadLink(60)!=null) {
											appLog.info("document is open after click on file name: "+fileNames[i]);
										}else {
											appLog.error("document is not open after click on fileName: "+fileNames[i]);
											sa.assertTrue(false, "document is not open after click on fileName: "+fileNames[i]);
										}
										driver.close();
										driver.switchTo().window(childWindowID);
									}else {
										appLog.error("Not able to click on file Name : "+fileNames[i]);
										sa.assertTrue(false,"Not able to click on file Name : "+fileNames[i]);
									}
								}else {
									appLog.error(fileNames[i]+ "is not available in Document not viewed or download so cannot click on file");
									sa.assertTrue(false, fileNames[i]+ "is not available in Document not viewed or download so cannot click on file");
								}
							}else {
								appLog.error("file name is not written in excel sheet so cannot click on file name, check file path in excel sheet test case No: M14tc004_1_uploadFilesInFWRAndCheckIPAnalytics ");
								sa.assertTrue(false, "file name is not written in excel sheet so cannot click on file name, check file path in excel sheet test case No: M14tc004_1_uploadFilesInFWRAndCheckIPAnalytics ");
							}
						}
						if (click(driver, fp.getDocumentNotViewedOrDownloadedCloseBtn(30), "Document not viewed or download Close button",action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Document not viewed or download pop up Close button");
						} else {
							appLog.info("Not able to Click on Document not viewed or download Close button");
							result.add("Not able to Click on Document not viewed or download Close button");
						}

					}else {
						appLog.error("Not able to click on Document not viewed or download link so cannot click on documents");
						sa.assertTrue(false,"Not able to click on Document not viewed or download link so cannot click on document");
					}
					if(click(driver, fp.getIPAnalyticsnotViewedAnyDocumentLink(10),"Document Not Viewed any document link", action.BOOLEAN)) {
						WebElement ele =FindElement(driver,"//span[@id='ContactNotViewedDocument_Grid-view-box-middle']//span[@title='"+updatedContactFName+" "+updatedContactLName+"']","Contact name in document not viewed or download pop up",action.BOOLEAN, 60);
						if(ele!=null) {
							if(click(driver, ele, "Contact Name", action.BOOLEAN)) {
								Set<String> lst11 = driver.getWindowHandles();
								Iterator<String> I2 = lst11.iterator();
								while (I2.hasNext()) {
									String windowID = I2.next();
									if (windowID.equalsIgnoreCase(parentid)
											|| windowID.equalsIgnoreCase(childWindowID)) {
										appLog.info("Parent Id or child Id is Matched");
									} else {
										childChildWindow = windowID;
										appLog.info("Stroged  child child Window Id");
										driver.switchTo().window(childChildWindow);
										break;
									}
								}
								String contactname = contact.getContactHeaderNameTextBlock(60).getText().trim();
								if (contactname.contains(M14Contact1FirstName+" "+M14Contact1LastName)) {
									appLog.info("Contact Name is Matched Successfully: " + M14Contact1FirstName+" "+M14Contact1LastName);
								} else {
									appLog.error("Contact Name is not Matched Successfully: " + M14Contact1FirstName+" "+M14Contact1LastName);
									sa.assertTrue(false,
											"Contact Name is not Matched Successfully: " + M14Contact1FirstName+" "+M14Contact1LastName);
								}
								driver.close();
								driver.switchTo().window(childWindowID);
							}else {
								appLog.error("Not able to click on Contact Name so cannot verify Contact page");
								sa.assertTrue(false, "Not able to click on Contact Name so cannot verify Contact page");
							}
						}else {
							appLog.error("Contact Name "+updatedContactFName+" "+updatedContactLName+" is not available in Document not Viewed or Download pop up so cannot click on it.");
							sa.assertTrue(false, "Contact Name "+updatedContactFName+" "+updatedContactLName+" is not available in Document not Viewed or Download pop up so cannot click on it.");
						}
					}else {
						appLog.error("Not able to click on Contact not viewed any document link so cannot click on contact name");
						sa.assertTrue(false, "Not able to click on Contact not viewed any document link so cannot click on contact name");
					}
					driver.close();
					driver.switchTo().window(parentid);				
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created fund Name so cannot access document from crm side");
				sa.assertTrue(false, "Not able to click on created fund Name so cannot access document from crm side");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot access document from crm side");
			sa.assertTrue(false, "Not able to click on fund tab so cannot access document from crm side");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc008_1_viewDocFromInvestorSideAndCheckImpact() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTC="M14tc004_1_uploadFilesInFWRAndCheckIPAnalytics";
		String dependONTC1="M14tc006_1_uploadFilesInvestorSideAndCheckImpactAtAnalytics";
		String filesName =ExcelUtils.readData("filepath", excelLabel.TestCases_Name, dependONTC1,excelLabel.UploadedFileStandard);
		String sharedfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath);
		String stdfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		String commonfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath);
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileStandard);
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileCommon);
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileShared);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.FundraisingWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp=new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M14Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			if(fp.verifyFolderPathdummy(stdfolderpath,M14Institution1,null,null,PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace,20)) {
				if(fp.viewDownloadDocument(stdfilesName, "M14Contact1", viewDownload.view, Workspace.PotentialInvestment, false)) {
					appLog.info("clicked on upload file: "+stdfilesName);
				}else {
					appLog.error("Not able to view upload file: "+stdfilesName);
					sa.assertTrue(false, "Not able to view upload file: "+stdfilesName);
				}
				if (fp.verifyDownloadFunctionality(PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace, filesName.split("<break>")[0], false, false, true)) {
					appLog.info("clicked on upload file successfully: "+filesName.split("<break>")[0]);
				}
				else {
					appLog.error("Not able to click on file name: "+filesName.split("<break>")[0]);
					sa.assertTrue(false, "Not able to click on file name: "+filesName.split("<break>")[0]);
				}
			}else {
				appLog.error("Not able to click on folder: "+stdfolderpath+" so cannot click on file name: "+stdfilesName);
				sa.assertTrue(false, "Not able to click on folder: "+stdfolderpath+" so cannot click on file name: "+stdfilesName);
			}
			if(fp.verifyFolderPathdummy(commonfolderpath,null,null,null,PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace,20)) {
				if(fp.viewDownloadDocument(commonfilesName, "M14Contact1", viewDownload.view, Workspace.PotentialInvestment, false)) {
					appLog.info("clicked on upload file: "+commonfilesName);
				}else {
					appLog.error("Not able to view upload file: "+commonfilesName);
					sa.assertTrue(false, "Not able to view upload file: "+commonfilesName);
				}
			}else {
				appLog.error("Not able to click on folder: "+commonfolderpath+" so cannot click on file name: "+commonfilesName);
				sa.assertTrue(false, "Not able to click on folder: "+commonfolderpath+" so cannot click on file name: "+commonfilesName);
			}
			if(fp.verifyFolderPathdummy(sharedfolderpath,null,null,null,PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace,20)) {
				if(fp.viewDownloadDocument(shrdfilesName, "M14Contact1", viewDownload.view, Workspace.PotentialInvestment, false)) {
					appLog.info("clicked on upload file: "+shrdfilesName);
				}else {
					appLog.error("Not able to view upload file: "+shrdfilesName);
					sa.assertTrue(false, "Not able to view upload file: "+shrdfilesName);
				}
			}else {
				appLog.error("Not able to click on folder: "+sharedfolderpath+" so cannot click on file name: "+shrdfilesName);
				sa.assertTrue(false, "Not able to click on folder: "+sharedfolderpath+" so cannot click on file name: "+shrdfilesName);
			}
		} else {
			appLog.info("Not able to click on Potential Investment tab");
			sa.assertTrue(false, "Not able to click on Potential Investment tab");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc008_2_checkImpactAtIPAnalytics() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		String dependONTC="M14tc004_1_uploadFilesInFWRAndCheckIPAnalytics";
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileStandard);
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileCommon);
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileShared);
		String[] fileNames= {stdfilesName,commonfilesName,shrdfilesName};
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view");
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					String childWindowID = null;
					String childChildWindow = null;
					Set<String> lst = driver.getWindowHandles();
					Iterator<String> I1 = lst.iterator();
					while (I1.hasNext()) {
						String windowID = I1.next();
						if (windowID.equalsIgnoreCase(parentid)) {
							appLog.info("Parent Id is Matched");
						} else {
							childWindowID = windowID;
							appLog.info("Stroged child Window Id");
							break;
						}
					}
					List<String> result = fp.verifyIPAnalyticsTargetValues(FRW_UniqueDocument, FRW_DocumentViews,
							FRW_DocumentDownload, FRW_DocumentNotViewedOrDownload, FRW_ContactGrantedAccess,FRW_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.FundraisingWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(null, false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(M14Contact2FirstName+" "+M14Contact2LastName,M14Institution1);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName, updatedInsName, M14Contact1_ActivityCount);
					sa.combineAssertions(activityCount);
					if (click(driver, fp.getIPAnalyticsMostViewedDocumentLink(30), "Most View Document Link",action.SCROLLANDBOOLEAN)) {
							for(int i=0; i<fileNames.length; i++) {
								if(!fileNames[i].isEmpty()) {
									WebElement ele=FindElement(driver, "//a[@text='"+fileNames[i]+"']",fileNames[i]+"  file Name link", action.BOOLEAN, 10);
									if(ele!=null) {
										if(click(driver, ele, "file name", action.BOOLEAN)) {
											appLog.info("Clicked on File Name : "+fileNames[i]);
											Set<String>lst1 = driver.getWindowHandles();
											Iterator<String> II1 = lst1.iterator();
											while (II1.hasNext()) {
												String windowID = II1.next();
												if (windowID.equalsIgnoreCase(parentid) || windowID.equalsIgnoreCase(childWindowID)) {
													appLog.info("window Id is Matched");
												} else {
													childChildWindow = windowID;
													appLog.info("Stroged child child Window Id");
													driver.switchTo().window(childChildWindow);
													break;
												}
											}
											if(fp.getDownloadLink(60)!=null) {
												appLog.info("document is open after click on file name: "+fileNames[i]);
											}else {
												appLog.error("document is not open after click on fileName: "+fileNames[i]);
												sa.assertTrue(false, "document is not open after click on fileName: "+fileNames[i]);
											}
											driver.close();
											driver.switchTo().window(childWindowID);
										}else {
											appLog.error("Not able to click on file Name : "+fileNames[i]);
											sa.assertTrue(false,"Not able to click on file Name : "+fileNames[i]);
										}
									}else {
										appLog.error(fileNames[i]+ "is not available in most viewed document so cannot click on file");
										sa.assertTrue(false, fileNames[i]+ "is not available in most viewed document so cannot click on file");
									}
								}else {
									appLog.error("file name is not written in excel sheet so cannot click on file name, check file path in excel sheet test case No: M14tc004_1_uploadFilesInFWRAndCheckIPAnalytics ");
									sa.assertTrue(false, "file name is not written in excel sheet so cannot click on file name, check file path in excel sheet test case No: M14tc004_1_uploadFilesInFWRAndCheckIPAnalytics ");
								}
							}
						if (click(driver, fp.getMostviewedDocumentCloseBtn(10), "Most Viewed Document pop up Close button",action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Most Viewed Document pop up Close button");
						} else {
							appLog.info("Not able to Click on Most Viewed Document pop up Close button");
							result.add("Not able to Click on Most Viewed Document pop up Close button");
						}
						
					}else {
						appLog.error("Not able to click on Most viewed Document link so cannot check update document");
						sa.assertTrue(false,"Not able to click on Most viewed Document link so cannot check update document");
					}
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc008_3_changeEmailAndDeleteContactAtCRMSideAndCheckImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer con = new ContactPageBusinessLayer(driver);
		HomePageBusineesLayer home = new HomePageBusineesLayer(driver);
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String email=con.generateRandomEmailId();
		
		if(con.updateEmailAddressOfCreatedContact(M14Contact1FirstName, M14Contact1LastName,email)) {
			appLog.info(M14Contact1FirstName+" "+M14Contact1LastName+" contact email id is updated");
			ExcelUtils.writeData(email,"Contacts", excelLabel.Variable_Name, "M14Contact1",excelLabel.ContactUpdatedEmailID);
		}else {
			appLog.error(M14Contact1FirstName+" "+M14Contact1LastName+" contact email id is not updated");
			sa.assertTrue(false, M14Contact1FirstName+" "+M14Contact1LastName+" contact email id is not updated");
		}
		
		if(con.deleteCreatedContact(M14Contact2FirstName, M14Contact2LastName)) {
			appLog.info(M14Contact2FirstName+" "+M14Contact2LastName+" contact is deleted");
		}else {
			appLog.error(M14Contact2FirstName+" "+M14Contact2LastName+" contact is not deleted");
			sa.assertTrue(false, M14Contact2FirstName+" "+M14Contact2LastName+" contact is not deleted");
		}
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(click(driver, fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "ip analtyics icon", action.SCROLLANDBOOLEAN)) {
					String parentid=switchOnWindow(driver);
					ThreadSleep(3000);
					if (click(driver,fp.getIPAnalyticsnotViewedAnyDocumentLink(30),"Contact not viewed any document", action.SCROLLANDBOOLEAN)) {
						//if (fp.clickUsingCssSelectorPath("span[title='"+M14Contact2FirstName+" "+M14Contact2LastName+"'] a", "contact link")) {
						WebElement ele = isDisplayed(driver,
								FindElement(driver,
										"//span[contains(@id,'ContactNotViewedDocument_Grid-cell-0-')]/a[text()='"+M14Contact2FirstName+" "+M14Contact2LastName+"']","Contact Name Link in Contact not viewed any document pop up", action.BOOLEAN, 60),"visibility", 60, "Contact Name Link");
						if (ele != null) {
							clickUsingJavaScript(driver, ele, "");
							//if (click(driver, ele, "Contact Name Link in Contact not viewed any document",
							//		action.BOOLEAN)) {
								//if (isAlertPresent(driver)) {
									String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
									switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
									if (msg.trim().contains(FundsPageErrorMessage.ContactNotFoundErrorMessage)) {
										appLog.info(
												"Error Message is verified in Contact not viewed any document: "+FundsPageErrorMessage.ContactNotFoundErrorMessage);
									} else {
										appLog.error(
												"Error Message is not verified in Contact not viewed any document: "+FundsPageErrorMessage.ContactNotFoundErrorMessage);
										sa.assertTrue(false,
												"Error Message is not verified in Contact not viewed any document: "+FundsPageErrorMessage.ContactNotFoundErrorMessage);
									}
								/*} else {
									appLog.error("Not able to Click on Contact Name Link: " + M14Contact2FirstName + " "
											+ M14Contact2LastName
											+ " so we cannot verify error message in Contact not viewed any document.");
									sa.assertTrue(false, "Not able to Click on Contact Name Link: " + M14Contact2FirstName + " "
											+ M14Contact2LastName
											+ " so we cannot verify error message in Contact not viewed any document.");
								}

							} else {
								appLog.info("Not able to click on Contact Name Link in Contact not viewed any document");
								sa.assertTrue(false,
										"Not able to click on Contact Name Link in Contact not viewed any document");
							}*/
						} else {
							appLog.error("Contact Name is not available in Contact not viewed any document pop up : "
									+ M14Contact2FirstName + " " + M14Contact2LastName);
							sa.assertTrue(false,
									"Contact Name is not available in Contact not viewed any document pop up : "
											+ M14Contact2FirstName + " " + M14Contact2LastName);
						}
						if (click(driver,fp.getContactNotViewedAnyDocumentCloseBtn(20),
								"Contact not viewed any document Close Button", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Contact not viewed any document pop up Close button");
						} else {
							appLog.info("Not able to click  on Contact not viewed any document pop up Close button");
							sa.assertTrue(false, "Not able to click on Contact not viewed any document pop up Close button");
						}
					} else {
						appLog.info("Not able to click on Contact not viewed any document Link");
						sa.assertTrue(false, "Not able to click on Contact not viewed any document Link");
					}
					if (click(driver, fp.getIPAnalyticsMostActiveContactsLink(30), "Most Active Contact Link",
							action.SCROLLANDBOOLEAN)) {
						WebElement ele = isDisplayed(driver,
								FindElement(driver,
										"//span[contains(@id,'MostActiveContacts_Grid-cell-0-')]/a[text()='"+updatedContactFName+" "+updatedContactLName+"']",
										"Contact Name Link in Contact not any document pop up", action.BOOLEAN, 60),
								"visibility", 60, "Contact Name Link");
						if (ele != null) {
							clickUsingJavaScript(driver, ele, "updated contact name");
							//if (click(driver, ele, "Contact Name Link in Contact not viewed any document", action.BOOLEAN)) {
								//if (isAlertPresent(driver)) {
									String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
									switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
									if (msg.trim().contains(FundsPageErrorMessage.ContactNotFoundErrorMessage)) {
										appLog.info(
												"Error Message is verified in contact not viewed any document up: "+FundsPageErrorMessage.ContactNotFoundErrorMessage);
									} else {
										appLog.error(
												"Error Message is not verified in contact not viewed any document up: "+FundsPageErrorMessage.ContactNotFoundErrorMessage);
										sa.assertTrue(false,
												"Error Message is not verified in contact not viewed any document up: "+FundsPageErrorMessage.ContactNotFoundErrorMessage);
									}
								/*} else {
									appLog.error("Not able to Click on Contact Name Link: "+updatedContactFName+" "+updatedContactLName+" so we cannot verify error message in contact not viewed any document up.");
									sa.assertTrue(false, "Not able to Click on Contact Name Link: "+updatedContactFName+" "+updatedContactLName+" so we cannot verify error message in contact not viewed any document up.");
								}*/
							//} else {
							//	appLog.info("Not able to click on Contact Name Link in Contact not viewed any document");
							//	sa.assertTrue(false,
							//			"Not able to click on Contact Name Link in Contact not viewed any document");
							//}
						} else {
							appLog.error("Contact Name is not available in Contact not viewed any document pop up : "+updatedContactFName+" "+updatedContactLName);
							sa.assertTrue(false, "Contact Name is not available in Contact not viewed any document pop up : "+updatedContactFName+" "+updatedContactLName);
						}
					}else {
						appLog.error("Not able to click on Most Active Contact Link so cannot check contact Error Message of "+updatedContactFName+" "+updatedContactLName);
						sa.assertTrue(false, "Not able to click on Most Active Contact Link so cannot check contact Error Message of "+updatedContactFName+" "+updatedContactLName);
					}
					driver.close();
					driver.switchTo().window(parentid);
				}else {
					appLog.error("Not able to click on ip analytics icon so cannot check error message in ip analytics");
					sa.assertTrue(false, "Not able to click on ip analytics icon so cannot check error message in ip analytics");
				}
			}else {
				appLog.error("Not able to click on created fund : "+M14FundName1+" so cannot check error message in ip analytics");
				sa.assertTrue(false, "Not able to click on created fund : "+M14FundName1+" so cannot check error message in ip analytics");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot check error message in  ip analytics ");
			sa.assertTrue(false, "Not able to click on fund tab so cannot check error message in  ip analytics ");
		}
		if(home.restoreValuesFromRecycleBin(M14Contact2FirstName+" "+M14Contact2LastName)) {
			appLog.info(M14Contact2FirstName+" "+M14Contact2LastName+" contact is restored form Recycle Bin");
		}else {
			appLog.error(M14Contact2FirstName+" "+M14Contact2LastName+" contact is not restored form Recycle Bin");
			sa.assertTrue(false, M14Contact2FirstName+" "+M14Contact2LastName+" contact is not restored form Recycle Bin");
		}
		if(con.updateEmailAddressOfCreatedContact(M14Contact1FirstName, M14Contact1LastName,M14Contact1EmailId)) {
			appLog.info(M14Contact1FirstName+" "+M14Contact1LastName+" contact email id is updated as old email");
		}else {
			appLog.error(M14Contact1FirstName+" "+M14Contact1LastName+" contact email id is not updated as old email");
			sa.assertTrue(false, M14Contact1FirstName+" "+M14Contact1LastName+" contact email id is not updated as old email");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc009_1_downloadDocFromInvestorSideAndCheckImpact() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTC="M14tc004_1_uploadFilesInFWRAndCheckIPAnalytics";
		String sharedfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath);
		String stdfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		String commonfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath);
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileStandard);
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileCommon);
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileShared);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.FundraisingWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp=new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M14Contact2EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			if(fp.verifyFolderPathdummy(stdfolderpath,M14Institution2,null,null,PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace,20)) {
				if(fp.viewDownloadDocument(stdfilesName, "M14Contact2", viewDownload.Download, Workspace.PotentialInvestment, false)) {
					appLog.info("clicked on upload file: "+stdfilesName);
				}else {
					appLog.error("Not able to view upload file: "+stdfilesName);
					sa.assertTrue(false, "Not able to view upload file: "+stdfilesName);
				}
			}else {
				appLog.error("Not able to click on folder: "+stdfolderpath+" so cannot click on file name: "+stdfilesName);
				sa.assertTrue(false, "Not able to click on folder: "+stdfolderpath+" so cannot click on file name: "+stdfilesName);
			}
			if(fp.verifyFolderPathdummy(commonfolderpath,null,null,null,PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace,20)) {
				if(fp.viewDownloadDocument(commonfilesName, "M14Contact2", viewDownload.Download, Workspace.PotentialInvestment, false)) {
					appLog.info("clicked on upload file: "+commonfilesName);
				}else {
					appLog.error("Not able to view upload file: "+commonfilesName);
					sa.assertTrue(false, "Not able to view upload file: "+commonfilesName);
				}
			}else {
				appLog.error("Not able to click on folder: "+commonfolderpath+" so cannot click on file name: "+commonfilesName);
				sa.assertTrue(false, "Not able to click on folder: "+commonfolderpath+" so cannot click on file name: "+commonfilesName);
			}
			if(fp.verifyFolderPathdummy(sharedfolderpath,null,null,null,PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace,20)) {
				if(fp.viewDownloadDocument(shrdfilesName, "M14Contact2", viewDownload.Download, Workspace.PotentialInvestment, false)) {
					appLog.info("clicked on upload file: "+shrdfilesName);
				}else {
					appLog.error("Not able to view upload file: "+shrdfilesName);
					sa.assertTrue(false, "Not able to view upload file: "+shrdfilesName);
				}
			}else {
				appLog.error("Not able to click on folder: "+sharedfolderpath+" so cannot click on file name: "+shrdfilesName);
				sa.assertTrue(false, "Not able to click on folder: "+sharedfolderpath+" so cannot click on file name: "+shrdfilesName);
			}
		} else {
			appLog.info("Not able to click on Potential Investment tab");
			sa.assertTrue(false, "Not able to click on Potential Investment tab");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc009_2_checkImpactAtIPAnalytics() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view");
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(FRW_UniqueDocument, FRW_DocumentViews,
							FRW_DocumentDownload, FRW_DocumentNotViewedOrDownload, FRW_ContactGrantedAccess,FRW_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.FundraisingWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(null, false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1, M14Contact1_ActivityCount+"<break>"+M14Contact2_ActivityCount);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M14tc010_1_UpdateFileInvestorSideAndCheckImpactAtAnalytics() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String docpath="UploadFiles\\Module14\\Investor_Side\\Potential_Investment\\Std";
		String filesName =ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.UploadedFileStandard);
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath);
		String 	date = getSystemDate("MM/dd/yyyy")+previousOrForwardDate(-1, "MM/dd/yyyy");
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.FundraisingWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp=new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M14Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			if (ifp.uploadUpdateFileInvestorSide(stdPath, filesName, M14Institution1, null, FolderType.Standard, docpath, " ", 30,PageName.PotentialInvestmentPage, null, null, WorkSpaceAction.UPDATE)) {
				appLog.info("file is Upload Successfully in potential investment in folder: "+stdPath);
				M14Contact1_ActivityCount=String.valueOf((Integer.parseInt(M14Contact1_ActivityCount)+mostActiveContactActivityCount));
				ExcelUtils.writeData(CommonVariables.M14Contact1_ActivityCount, "Contacts", excelLabel.Variable_Name, "M14Contact1", excelLabel.Activity_Count);
				if(click(driver, ifp.getRefreshIcon(20), "Refresh Icon", action.SCROLLANDBOOLEAN)){
					SoftAssert saa = ifp.verifyContentGridInvestorSide(driver, PageName.PotentialInvestmentPage, filesName,updatedContactFName+ " " + updatedContactLName, date);
					sa.combineAssertions(saa);
				}else{
					appLog.error("Not Able to Click Refresh Icon so Cannot check Upoaded File");
					sa.assertTrue(false, "Not Able to Click Refresh Icon so Cannot check Upoaded File");	
				}
			}else{
				appLog.error("Upload Unsuccessful");
				sa.assertTrue(false, "Upload Unsuccessful");	
			}
			
		} else {
			appLog.info("Not able to click on Potential Investment tab");
			sa.assertTrue(false, "Not able to click on Potential Investment tab");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc010_2_updateFilesInFWRAndCheckIPAnalytics() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String sharedfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath);
		String commonfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath);
		String internalfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.InternalPath);
		String standardfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		String stddocpath="UploadFiles\\Module14\\CRM_Side\\FRW\\Standard";
		String shrddocpath="UploadFiles\\Module14\\CRM_Side\\FRW\\Shared";
		String commondocpath="UploadFiles\\Module14\\CRM_Side\\FRW\\Common";
		String internaldocpath="UploadFiles\\Module14\\CRM_Side\\FRW\\Internal";
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				if(fp.uploadFile(standardfolderpath,M14Institution1+"<break>"+M14Institution2, stddocpath,null,UploadFileActions.Update, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly in folder: "+standardfolderpath);
					switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileStandard);
						if(filesName!=null) {
							List<String>uploadFiles=compareMultipleListOnBasisOfTitle(driver,filesName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!uploadFiles.isEmpty()) {
								for(int i=0;i<uploadFiles.size();i++) {
									sa.assertTrue(false, uploadFiles.get(i));
								}
							}else {
								appLog.info("All uploaded files are verified: "+filesName);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents in standard folder.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents in standard folder");
					}
				}else {
					appLog.error("File is not uploaded in folder "+standardfolderpath);
					sa.assertTrue(false, "File is not uploaded in "+standardfolderpath);
				}
				switchToDefaultContent(driver);
				if(fp.uploadFile(sharedfolderpath,null, shrddocpath,null,UploadFileActions.Update, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly in folder: "+sharedfolderpath);
					switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileShared);
						if(filesName!=null) {
							List<String>uploadFiles=compareMultipleListOnBasisOfTitle(driver,filesName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!uploadFiles.isEmpty()) {
								for(int i=0;i<uploadFiles.size();i++) {
									sa.assertTrue(false, uploadFiles.get(i));
								}
							}else {
								appLog.info("All uploaded files are verified: "+filesName);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents in shared folder.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents in shared folder");
					}
				}else {
					appLog.error("File is not uploaded in folder "+sharedfolderpath);
					sa.assertTrue(false, "File is not uploaded in "+sharedfolderpath);
				}
				switchToDefaultContent(driver);
				if(fp.uploadFile(commonfolderpath,null, commondocpath,null,UploadFileActions.Update, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly in folder: "+commonfolderpath);
					switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileCommon);
						if(filesName!=null) {
							List<String>uploadFiles=compareMultipleListOnBasisOfTitle(driver,filesName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!uploadFiles.isEmpty()) {
								for(int i=0;i<uploadFiles.size();i++) {
									sa.assertTrue(false, uploadFiles.get(i));
								}
							}else {
								appLog.info("All uploaded files are verified: "+filesName);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents in common folder.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents in common folder");
					}
				}else {
					appLog.error("File is not uploaded in folder "+commonfolderpath);
					sa.assertTrue(false, "File is not uploaded in "+commonfolderpath);
				}
				switchToDefaultContent(driver);
				if(fp.uploadFile(internalfolderpath,null, internaldocpath,null,UploadFileActions.Update, Workspace.FundraisingWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly in folder: "+internalfolderpath);
					switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileInternal);
						if(filesName!=null) {
							List<String>uploadFiles=compareMultipleListOnBasisOfTitle(driver,filesName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!uploadFiles.isEmpty()) {
								for(int i=0;i<uploadFiles.size();i++) {
									sa.assertTrue(false, uploadFiles.get(i));
								}
							}else {
								appLog.info("All uploaded files are verified: "+filesName);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents in common folder.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents in common folder");
					}
				}else {
					appLog.error("File is not uploaded in folder "+internalfolderpath);
					sa.assertTrue(false, "File is not uploaded in "+internalfolderpath);
				}
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(FRW_UniqueDocument, FRW_DocumentViews,
							FRW_DocumentDownload, FRW_DocumentNotViewedOrDownload, FRW_ContactGrantedAccess,FRW_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.FundraisingWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(null, false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1, M14Contact1_ActivityCount+"<break>"+M14Contact2_ActivityCount);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc011_1_viewUpdatedDocFromInvestorSideAndCheckImpact() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTC="M14tc010_2_updateFilesInFWRAndCheckIPAnalytics";
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UpdatedFileStandard);
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UpdatedFileCommon);
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UpdatedFileShared);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.FundraisingWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp=new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M14Contact2EmailId, adminPassword);
		ThreadSleep(10000);
		if(click(driver, ifp.getRecentActivitiesTab(60), "recent activities tab", action.SCROLLANDBOOLEAN)) {
			if(fp.viewDownloadDocument(stdfilesName.split("<break>")[0], "M14Contact2", viewDownload.view, Workspace.PotentialInvestment, false)) {
				appLog.info("clicked on Updated file: "+stdfilesName.split("<break>")[0]);
			}else {
				appLog.error("Not able to view Updated file: "+stdfilesName.split("<break>")[0]);
				sa.assertTrue(false, "Not able to view Updated file: "+stdfilesName.split("<break>")[0]);
			}
			if(fp.viewDownloadDocument(commonfilesName, "M14Contact2", viewDownload.view, Workspace.PotentialInvestment, false)) {
				appLog.info("clicked on Updated file: "+commonfilesName);
			}else {
				appLog.error("Not able to view Updated file: "+commonfilesName);
				sa.assertTrue(false, "Not able to view Updated file: "+commonfilesName);
			}
			if(fp.viewDownloadDocument(shrdfilesName, "M14Contact2", viewDownload.view, Workspace.PotentialInvestment, false)) {
				appLog.info("clicked on Updated file: "+shrdfilesName);
			}else {
				appLog.error("Not able to view Updated file: "+shrdfilesName);
				sa.assertTrue(false, "Not able to view Updated file: "+shrdfilesName);
			}
		}else {
			appLog.error("Not able to click on recent activities tab so cannot click on files");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot click on files");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc011_2_checkImpactAtIPAnalytics() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view");
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(FRW_UniqueDocument, FRW_DocumentViews,
							FRW_DocumentDownload, FRW_DocumentNotViewedOrDownload, FRW_ContactGrantedAccess,FRW_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.FundraisingWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(null, false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1, M14Contact1_ActivityCount+"<break>"+M14Contact2_ActivityCount);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M14tc012_updateDocWIthDiffNameAndCheckImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTC="M14tc004_1_uploadFilesInFWRAndCheckIPAnalytics";
		String DependOnTC1="M14tc006_1_uploadFilesInvestorSideAndCheckImpactAtAnalytics";
		String sharedfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath);
		String stdfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		String commonfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath);
		String internalfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.InternalPath);
		String UpdatestdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileStandard);
		String UpdatecommonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileCommon);
		String updateshrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileShared);
		String updateinternalfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileInternal);
		
		String uploadstdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileStandard);
		String uploadcommonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileCommon);
		String uploadshrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileShared);
		String uploadinternalfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileInternal);
		String uploadInvestorSidestdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, DependOnTC1, excelLabel.UploadedFileStandard);
		
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		String docPath=System.getProperty("user.dir") + "\\UploadFiles\\Module14\\FileToUpdate\\";
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.FundraisingWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
				if(fp.updateFile(stdfolderpath, uploadstdfilesName, M14Institution1, null, FolderType.Standard,docPath+UpdatestdfilesName.split("<break>")[0], multiInstance.ThisInvestorOnly, null, ContentGridArrowKeyFunctions.Update,20, PageName.FundsPage,"Yes",null, Workspace.FundraisingWorkspace)) {
					appLog.info("file is updated successfully: "+uploadstdfilesName+" in :"+stdfolderpath+" to "+UpdatestdfilesName.split("<break>")[0]);
						switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,UpdatestdfilesName.split("<break>")[0],fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(UpdatestdfilesName.split("<break>")[0]+" is verified.");
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify imported document: "+UpdatestdfilesName.split("<break>")[0]);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify imported document: "+UpdatestdfilesName.split("<break>")[0]);
						}
				}else {
					appLog.error("file is not updated: "+uploadstdfilesName+" in :"+UpdatestdfilesName.split("<break>")[0]);
					sa.assertTrue(false, "file is not updated: "+uploadstdfilesName+" in :"+UpdatestdfilesName.split("<break>")[0]);
				}
				switchToDefaultContent(driver);
				if(fp.updateFile(stdfolderpath, uploadInvestorSidestdfilesName.split("<break>")[0], M14Institution1, null, FolderType.Standard,docPath+UpdatestdfilesName.split("<break>")[1], null, null, ContentGridArrowKeyFunctions.Update,20, PageName.FundsPage,"Yes",null, Workspace.FundraisingWorkspace)) {
					appLog.info("file is updated successfully: "+uploadInvestorSidestdfilesName.split("<break>")[0]+" in :"+stdfolderpath+" to "+UpdatestdfilesName.split("<break>")[1]);
						switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,UpdatestdfilesName.split("<break>")[1],fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(UpdatestdfilesName.split("<break>")[1]+" is verified.");
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify upload document: "+UpdatestdfilesName.split("<break>")[0]);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload document: "+UpdatestdfilesName.split("<break>")[0]);
						}
				}else {
					appLog.error("file is not updated: "+uploadInvestorSidestdfilesName+" in :"+stdfolderpath);
					sa.assertTrue(false, "file is not updated: "+uploadInvestorSidestdfilesName+" in :"+stdfolderpath);
				}
				switchToDefaultContent(driver);
				if(fp.updateFile(commonfolderpath, uploadcommonfilesName, null, null, FolderType.Standard,docPath+UpdatecommonfilesName, null, null, ContentGridArrowKeyFunctions.Update,20, PageName.FundsPage,"Yes",null, Workspace.FundraisingWorkspace)) {
					appLog.info("file is updated successfully: "+uploadcommonfilesName+" in :"+commonfolderpath+" to "+UpdatecommonfilesName);
						switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,UpdatecommonfilesName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(UpdatecommonfilesName+" is verified.");
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify upload document: "+UpdatecommonfilesName);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload document: "+UpdatecommonfilesName);
						}
				}else {
					appLog.error("file is not updated: "+uploadcommonfilesName+" in :"+uploadcommonfilesName);
					sa.assertTrue(false, "file is not updated: "+uploadcommonfilesName+" in :"+uploadcommonfilesName);
				}
				switchToDefaultContent(driver);
				if(fp.updateFile(internalfolderpath, uploadinternalfilesName, null, null, FolderType.Standard,docPath+updateinternalfilesName, null, null, ContentGridArrowKeyFunctions.Update,20, PageName.FundsPage,"Yes",null, Workspace.FundraisingWorkspace)) {
					appLog.info("file is updated successfully: "+uploadinternalfilesName+" in :"+internalfolderpath+" to "+updateinternalfilesName);
						switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,updateinternalfilesName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(updateinternalfilesName+" is verified.");
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify update document: "+updateinternalfilesName);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify update document: "+updateinternalfilesName);
						}
				}else {
					appLog.error("file is not updated: "+uploadinternalfilesName+" in :"+uploadcommonfilesName);
					sa.assertTrue(false, "file is not updated: "+uploadinternalfilesName+" in :"+uploadcommonfilesName);
				}
				switchToDefaultContent(driver);
				if(fp.updateFile(sharedfolderpath, uploadshrdfilesName, null, null, FolderType.Shared,docPath+updateshrdfilesName, null, null, ContentGridArrowKeyFunctions.Update,20, PageName.FundsPage,"Yes",null, Workspace.FundraisingWorkspace)) {
					appLog.info("file is updated successfully: "+uploadshrdfilesName+" in :"+sharedfolderpath+" to "+updateshrdfilesName);
					switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleList(driver,updateshrdfilesName,fp.getContentGridDocNameList(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(updateshrdfilesName+" is verified.");
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify update document: "+updateshrdfilesName);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify update document: "+updateshrdfilesName);
						}
				}else {
					appLog.error("file is not updated: "+uploadshrdfilesName+" in :"+sharedfolderpath);
					sa.assertTrue(false, "file is not updated: "+uploadshrdfilesName+" in :"+sharedfolderpath);
				}
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(FRW_UniqueDocument, FRW_DocumentViews,
							FRW_DocumentDownload, FRW_DocumentNotViewedOrDownload, FRW_ContactGrantedAccess,FRW_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.FundraisingWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(null, false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1, M14Contact1_ActivityCount+"<break>"+M14Contact2_ActivityCount);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);				
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created fund Name so cannot build fundraising and investor workspace: "+M14FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot build fundraising and investor workspace: "+M14FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot build fundraising workspace and investor: "+M14FundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot build fundraising workspace and investor: "+M14FundName1);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc013_1_viewDiffNameUpdatedDocFromInvestorSide() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTC="M14tc012_updateDocWIthDiffNameAndCheckImpact";
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UpdatedFileStandard);
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UpdatedFileCommon);
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UpdatedFileShared);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.FundraisingWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp=new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		AllFirmsPageBusinesslayer allfp= new AllFirmsPageBusinesslayer(driver);
		lp.investorLogin(M14Contact1EmailId, adminPassword);
		ThreadSleep(10000);
		if(allfp.selectFirmName("All Firms")) {
			ThreadSleep(5000);
			if (fp.viewDownloadDocument(stdfilesName.split("<break>")[0], "M14Contact1", viewDownload.Download, Workspace.PotentialInvestment, true)) {
				appLog.info("clicked on Updated file: "+stdfilesName.split("<break>")[0]);
			}else {
				appLog.error("Not able to view Updated file: "+stdfilesName.split("<break>")[0]);
				sa.assertTrue(false, "Not able to view Updated file: "+stdfilesName.split("<break>")[0]);
			}
			if (fp.verifyDownloadFunctionality(PageName.FundsPage, Workspace.FundraisingWorkspace, stdfilesName.split("<break>")[1], false, false, true)) {
				appLog.info("clicked on upload file successfully: "+stdfilesName.split("<break>")[1]);
			}
			else {
				appLog.error("Not able to click on file name: "+stdfilesName.split("<break>")[1]);
				sa.assertTrue(false, "Not able to click on file name: "+stdfilesName.split("<break>")[1]);
			}
			if(fp.viewDownloadDocument(commonfilesName, "M14Contact1", viewDownload.Download, Workspace.PotentialInvestment, true)) {
				appLog.info("clicked on Updated file: "+commonfilesName);
			}else {
				appLog.error("Not able to view Updated file: "+commonfilesName);
				sa.assertTrue(false, "Not able to view Updated file: "+commonfilesName);
			}
			if(fp.viewDownloadDocument(shrdfilesName, "M14Contact1", viewDownload.Download, Workspace.PotentialInvestment, true)) {
				appLog.info("clicked on Updated file: "+shrdfilesName);
			}else {
				appLog.error("Not able to view Updated file: "+shrdfilesName);
				sa.assertTrue(false, "Not able to view Updated file: "+shrdfilesName);
			}
		}else {
			appLog.error("Not able to select All firm from drop down list so cannot click on file names");
			sa.assertTrue(false, "Not able to select All firm from drop down list so cannot click on file names");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc013_2_checkImpactAtIPAnalytics() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view");
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(FRW_UniqueDocument, FRW_DocumentViews,
							FRW_DocumentDownload, FRW_DocumentNotViewedOrDownload, FRW_ContactGrantedAccess,FRW_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.FundraisingWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(null, false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1, M14Contact1_ActivityCount+"<break>"+M14Contact2_ActivityCount);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc014_activateManageApprovalsSettings() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName,adminPassword);
		if(nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, nim.getNIMTabFrame(30));
			if(nim.clickOnSideMenusTab(sideMenu.ManageApprovals)) {
				if(nim.activateManageApprovalsSettings(CRMUser1FirstName+" "+CRMUser1LastName).isEmpty()) {
					appLog.info("manage approvals settings is activated");
				}else {
					appLog.error("Manage aprrovals settings is not activated");
					sa.assertTrue(false, "Manage aprrovals settings is not activated");
				}
				
			}else {
				appLog.error("Not able to click on manage approvals tab so cannot activate manage approvals settings ");
				sa.assertTrue(false, "Not able to click on manage approvals tab so cannot activate manage approvals settings ");
			}
		}else {
			appLog.error("Not able to click on NIM Tab so cannot activate manage approvals settings");
			sa.assertTrue(false, "Not able to click on NIM Tab so cannot activate manage approvals settings");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc015_onlineImportDocInFRW() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String sharedfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath);
		String commonfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath);
		String internalfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.InternalPath);
		String standardfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		String uploadstdfileName=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.UploadedFileStandard);
		String uploadcommonfileName=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.UploadedFileCommon);
		String uploadsharedfileName=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.UploadedFileShared);
		String uploadinternalfileName=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.UploadedFileInternal);
		String DocPath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.OnlineImportPath);
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.FundraisingWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
				if(fp.onlineImport(M14Institution1, null, M14Institution2,standardfolderpath,DocPath,uploadstdfileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.MultipleInstitute, WorkSpaceAction.UPLOAD, FolderType.Standard, PageName.FundsPage, Workspace.FundraisingWorkspace,20)) {
					appLog.info("file is imported successfully: "+uploadstdfileName+" in :"+standardfolderpath);
						switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleListOnBasisOfTitle(driver,uploadstdfileName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(uploadstdfileName+" is verified.");
							}
						}
				}else {
					appLog.error("file is not imported: "+uploadstdfileName+" in :"+standardfolderpath);
					sa.assertTrue(false, "file is not imported: "+uploadstdfileName+" in :"+standardfolderpath);
				}
				switchToDefaultContent(driver);
				if(fp.onlineImport(null, null, null,commonfolderpath,DocPath,uploadcommonfileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Common, PageName.FundsPage, Workspace.FundraisingWorkspace,20)) {
					appLog.info("file is imported successfully: "+uploadcommonfileName+" in :"+commonfolderpath);
						switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleListOnBasisOfTitle(driver,uploadcommonfileName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(uploadcommonfileName+" is verified.");
							}
						}
				}else {
					appLog.error("file is not imported: "+uploadcommonfileName+" in :"+commonfolderpath);
					sa.assertTrue(false, "file is not imported: "+uploadcommonfileName+" in :"+commonfolderpath);
				}
				switchToDefaultContent(driver);
				if(fp.onlineImport(null, null, null,sharedfolderpath,DocPath,uploadsharedfileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Shared, PageName.FundsPage, Workspace.FundraisingWorkspace,20)) {
					appLog.info("file is imported successfully: "+uploadsharedfileName+" in :"+sharedfolderpath);
						switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleListOnBasisOfTitle(driver,uploadsharedfileName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(uploadsharedfileName+" is verified.");
							}
						}
				}else {
					appLog.error("file is not imported: "+uploadsharedfileName+" in :"+sharedfolderpath);
					sa.assertTrue(false, "file is not imported: "+uploadsharedfileName+" in :"+sharedfolderpath);
				}
				switchToDefaultContent(driver);
				if(fp.onlineImport(null, null, null,internalfolderpath,DocPath,uploadinternalfileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Internal, PageName.FundsPage, Workspace.FundraisingWorkspace,20)) {
					appLog.info("file is imported successfully: "+uploadinternalfileName+" in :"+internalfolderpath);
						switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleListOnBasisOfTitle(driver,uploadinternalfileName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.FundraisingWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(uploadinternalfileName+" is verified.");
							}
						}
				}else {
					appLog.error("file is not imported: "+uploadinternalfileName+" in :"+internalfolderpath);
					sa.assertTrue(false, "file is not imported: "+uploadinternalfileName+" in :"+internalfolderpath);
				}
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(FRW_UniqueDocument, FRW_DocumentViews,
							FRW_DocumentDownload, FRW_DocumentNotViewedOrDownload, FRW_ContactGrantedAccess,FRW_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.FundraisingWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(null, false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1, M14Contact1_ActivityCount+"<break>"+M14Contact2_ActivityCount);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot import files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot import files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot import files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot import files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc016_approvePendingDocAndCheckImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTc="M14tc015_onlineImportDocInFRW";
		String uploadstdfileName=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,dependONTc,excelLabel.UploadedFileStandard);
		String uploadcommonfileName=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,dependONTc,excelLabel.UploadedFileCommon);
		String uploadsharedfileName=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,dependONTc,excelLabel.UploadedFileShared);
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.FundraisingWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(click(driver,fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approvals icon", action.SCROLLANDBOOLEAN)) {
					if(fp.selectPendingFilesToApproveAndCountApprovedFiles(driver, uploadstdfileName+"<break>"+uploadcommonfileName+"<break>"+uploadsharedfileName).isEmpty()) {
						if(click(driver, fp.getManageApprovalsApproveBtn(30), "approve button", action.SCROLLANDBOOLEAN)) {
							if(click(driver, fp.getManageApprovalsConfirmYesBtn(30), "manage approvals confirm yes button", action.SCROLLANDBOOLEAN)) {
								CommonVariables.FRW_UniqueDocument = CommonVariables.FRW_UniqueDocument+BaseLib.totalUploadedFiles;
								CommonVariables.FRW_DocumentNotViewedOrDownload = CommonVariables.FRW_DocumentNotViewedOrDownload+BaseLib.totalUploadedFiles;
								ExcelUtils.writeData(FRW_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.FRW_Value);
								ExcelUtils.writeData(FRW_DocumentNotViewedOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.FRW_Value);
								if(click(driver, fp.getManageApprovalsCloseBtn(30), "manage approvals approved file close button", action.SCROLLANDBOOLEAN)) {
									if (click(driver, fp.getManageAppCancelBtn(60), "cancel button manage approvals", action.SCROLLANDBOOLEAN)) {
										appLog.error("manage approvals pop is closed");
									}
									else {
										appLog.error("cancel button is not clickable on manage approvals window");
										sa.assertTrue(false, "cancel button is not clickable on manage approvals window");
									}
								}else {
									appLog.error("Not able to click on manage approvals close button so cannot close manage approvals pop up");
									sa.assertTrue(false, "Not able to click on manage approvals close button so cannot close manage approvals pop up");
									switchToDefaultContent(driver);
									driver.navigate().refresh();
									switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
								}
							}else {
								appLog.error("Not able to click on manage approvals confirm yes button so cannot approve pending files");
								sa.assertTrue(false, "Not able to click on manage approvals confirm yes button so cannot approve pending files");
							}
						}else {
							appLog.error("Not able to click on manage approvals approve button so cannot approve pending files");
							sa.assertTrue(false, "Not able to click on manage approvals approve button so cannot approve pending files");
						}
					}else {
						appLog.error("Not able to approve all document from manage approval");
						sa.assertTrue(false, "Not able to approve all document from manage approval");
					}
					
				}else {
					appLog.error("Not able to click on manage approvals icon so cannot approve pending document");
					sa.assertTrue(false, "Not able to click on manage approvals icon so cannot approve pending document");
				}
				if(click(driver,fp.getManageApprovalIcon(Workspace.FundraisingWorkspace, 30), "manage approvals icon", action.SCROLLANDBOOLEAN)) {
					if(fp.selectAllPendingFilesToApprove(WorkSpaceAction.UPLOAD).isEmpty()) {
						appLog.info("all pending document is approved");
					}else {
						appLog.error("Not able to approve all document from manage approval");
						sa.assertTrue(false, "Not able to approve all document from manage approval");
					}
					
				}else {
					appLog.error("Not able to click on manage approvals icon so cannot approve pending document");
					sa.assertTrue(false, "Not able to click on manage approvals icon so cannot approve pending document");
				}
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(FRW_UniqueDocument, FRW_DocumentViews,
							FRW_DocumentDownload, FRW_DocumentNotViewedOrDownload, FRW_ContactGrantedAccess,FRW_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.FundraisingWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(uploadcommonfileName+"<break>"+uploadsharedfileName+"<break>"+uploadstdfileName, true);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1, M14Contact1_ActivityCount+"<break>"+M14Contact2_ActivityCount);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot import files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot import files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot import files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot import files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc017_1_viewOnlineImportDocFromInvestorSideAndCheckImpact() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTC="M14tc015_onlineImportDocInFRW";
		String sharedfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath);
		String stdfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		String commonfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath);
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileStandard);
		String[] SplitedStdFilesName=stdfilesName.split("<break>");
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileCommon);
		String [] SplitedCommonFilesName=commonfilesName.split("<break>");
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileShared);
		String [] SplitedSharedFileName=shrdfilesName.split("<break>");
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.FundraisingWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp=new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M14Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.PotentialInvestment)) {
			if(fp.verifyFolderPathdummy(stdfolderpath,M14Institution1,null,null,PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace,20)) {
				if(fp.viewDownloadDocument(SplitedStdFilesName[0], "M14Contact1", viewDownload.view, Workspace.PotentialInvestment, false)) {
					appLog.info("clicked on upload file: "+SplitedStdFilesName[0]);
				}else {
					appLog.error("Not able to view upload file: "+SplitedStdFilesName[0]);
					sa.assertTrue(false, "Not able to view upload file: "+SplitedStdFilesName[0]);
				}
				if(fp.viewDownloadDocument(SplitedStdFilesName[1], "M14Contact1", viewDownload.view, Workspace.PotentialInvestment, false)) {
					appLog.info("clicked on upload file: "+SplitedStdFilesName[1]);
				}else {
					appLog.error("Not able to view upload file: "+SplitedStdFilesName[1]);
					sa.assertTrue(false, "Not able to view upload file: "+SplitedStdFilesName[1]);
				}
				if(fp.viewDownloadDocument(SplitedStdFilesName[2], "M14Contact1", viewDownload.view, Workspace.PotentialInvestment, false)) {
					appLog.info("clicked on upload file: "+SplitedStdFilesName[2]);
				}else {
					appLog.error("Not able to view upload file: "+SplitedStdFilesName[2]);
					sa.assertTrue(false, "Not able to view upload file: "+SplitedStdFilesName[2]);
				}
			}else {
				appLog.error("Not able to click on folder: "+stdfolderpath+" so cannot click on file name: "+SplitedStdFilesName[0]+", "+SplitedStdFilesName[1]+", "+SplitedStdFilesName[2]);
				sa.assertTrue(false,"Not able to click on folder: "+stdfolderpath+" so cannot click on file name: "+SplitedStdFilesName[0]+", "+SplitedStdFilesName[1]+", "+SplitedStdFilesName[2]);
			}
			if(fp.verifyFolderPathdummy(commonfolderpath,null,null,null,PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace,20)) {
				if(fp.viewDownloadDocument(SplitedCommonFilesName[0], "M14Contact1", viewDownload.view, Workspace.PotentialInvestment, false)) {
					appLog.info("clicked on upload file: "+SplitedCommonFilesName[0]);
				}else {
					appLog.error("Not able to view upload file: "+SplitedCommonFilesName[0]);
					sa.assertTrue(false, "Not able to view upload file: "+SplitedCommonFilesName[0]);
				}
				if(fp.viewDownloadDocument(SplitedCommonFilesName[1], "M14Contact1", viewDownload.view, Workspace.PotentialInvestment, false)) {
					appLog.info("clicked on upload file: "+SplitedCommonFilesName[1]);
				}else {
					appLog.error("Not able to view upload file: "+SplitedCommonFilesName[1]);
					sa.assertTrue(false, "Not able to view upload file: "+SplitedCommonFilesName[1]);
				}
				if(fp.viewDownloadDocument(SplitedCommonFilesName[2], "M14Contact1", viewDownload.view, Workspace.PotentialInvestment, false)) {
					appLog.info("clicked on upload file: "+SplitedCommonFilesName[2]);
				}else {
					appLog.error("Not able to view upload file: "+SplitedCommonFilesName[2]);
					sa.assertTrue(false, "Not able to view upload file: "+SplitedCommonFilesName[2]);
				}
			}else {
				appLog.error("Not able to click on folder: "+commonfolderpath+" so cannot click on file name: "+SplitedCommonFilesName[0]+", "+SplitedCommonFilesName[1]+", "+SplitedCommonFilesName[2]);
				sa.assertTrue(false, "Not able to click on folder: "+commonfolderpath+" so cannot click on file name: "+SplitedCommonFilesName[0]+", "+SplitedCommonFilesName[1]+", "+SplitedCommonFilesName[2]);
			}
			if(fp.verifyFolderPathdummy(sharedfolderpath,null,null,null,PageName.PotentialInvestmentPage, Workspace.FundraisingWorkspace,20)) {
				if(fp.viewDownloadDocument(SplitedSharedFileName[0], "M14Contact1", viewDownload.view, Workspace.PotentialInvestment, false)) {
					appLog.info("clicked on upload file: "+SplitedSharedFileName[0]);
				}else {
					appLog.error("Not able to view upload file: "+SplitedSharedFileName[0]);
					sa.assertTrue(false, "Not able to view upload file: "+SplitedSharedFileName[0]);
				}
				if(fp.viewDownloadDocument(SplitedSharedFileName[1], "M14Contact1", viewDownload.view, Workspace.PotentialInvestment, false)) {
					appLog.info("clicked on upload file: "+SplitedSharedFileName[1]);
				}else {
					appLog.error("Not able to view upload file: "+SplitedSharedFileName[1]);
					sa.assertTrue(false, "Not able to view upload file: "+SplitedSharedFileName[1]);
				}
				if(fp.viewDownloadDocument(SplitedSharedFileName[2], "M14Contact1", viewDownload.view, Workspace.PotentialInvestment, false)) {
					appLog.info("clicked on upload file: "+SplitedSharedFileName[2]);
				}else {
					appLog.error("Not able to view upload file: "+SplitedSharedFileName[2]);
					sa.assertTrue(false, "Not able to view upload file: "+SplitedSharedFileName[2]);
				}
			}else {
				appLog.error("Not able to click on folder: "+sharedfolderpath+" so cannot click on file name: "+SplitedSharedFileName[0]+", "+SplitedSharedFileName[1]+", "+SplitedSharedFileName[2]);
				sa.assertTrue(false, "Not able to click on folder: "+sharedfolderpath+" so cannot click on file name: "+SplitedSharedFileName[0]+","+SplitedSharedFileName[1]+","+SplitedSharedFileName[2]);
			}
		} else {
			appLog.info("Not able to click on Potential Investment tab");
			sa.assertTrue(false, "Not able to click on Potential Investment tab");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc017_2_checkImpactAtIPAnalytics() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTc="M14tc015_onlineImportDocInFRW";
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileStandard);
		String[] SplitedStdFilesName=stdfilesName.split("<break>");
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileCommon);
		String [] SplitedCommonFilesName=commonfilesName.split("<break>");
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileShared);
		String [] SplitedSharedFileName=shrdfilesName.split("<break>");		
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view");
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(FRW_UniqueDocument, FRW_DocumentViews,
							FRW_DocumentDownload, FRW_DocumentNotViewedOrDownload, FRW_ContactGrantedAccess,FRW_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.FundraisingWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(SplitedStdFilesName[3]+"<break>"+SplitedCommonFilesName[3]+"<break>"+SplitedSharedFileName[3], false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1, M14Contact1_ActivityCount+"<break>"+M14Contact2_ActivityCount);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M14tc018_verifySortingInAnalyticsPopUps() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.FundraisingWorkspace, 30), "Fundraising workspace view");
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					if(click(driver, fp.getIPAnalyticsDocumentNotViwedorDownloadLink(30), "document not viewed or download link", action.BOOLEAN)) {
						if(click(driver, fp.getDocumentNotViewedOrDownloadSortingIcon(20), "sorting icon", action.BOOLEAN)) {
							if(fp.checkSorting(SortOrder.Decending, fp.getDocumentNotViewedOrDownloadDocumentList())) {
								appLog.info("document is verified in Decending order");
							}else {
								appLog.error("document is not verified in Decending order");
								sa.assertTrue(false, "document is not verified in Decending order");
							}
						}else {
							appLog.error("Not able to click on sorting icon so cannot check Decending Sorting in document not viewed or download");
							sa.assertTrue(false, "Not able to click on sorting icon so cannot check Decending Sorting in document not viewed or download");
						}
						if(click(driver, fp.getDocumentNotViewedOrDownloadSortingIcon(20), "sorting icon", action.BOOLEAN)) {
							if(fp.checkSorting(SortOrder.Assecending, fp.getDocumentNotViewedOrDownloadDocumentList())) {
								appLog.info("document is verified in Ascending order");
							}else {
								appLog.error("document is not verified in Ascending order");
								sa.assertTrue(false, "document is not verified in Ascending order");
							}
						}else {
							appLog.error("Not able to click on sorting icon so cannot check Ascending Sorting in document not viewed or download");
							sa.assertTrue(false, "Not able to click on sorting icon so cannot check Ascending Sorting in document not viewed or download");
						}
						
						if(click(driver, fp.getDocumentNotViewedOrDownloadedCloseBtn(20), "close button", action.BOOLEAN)) {
							appLog.info("click on close button");
						}else {
							appLog.error("Not able to click on close button so cannot close document not viewed or download pop up");
							sa.assertTrue(false, "Not able to click on close button so cannot close document not viewed or download pop up");
						}
					}else {
						appLog.error("Not able to click on document not viewed or download link so cannot check sorting");
						sa.assertTrue(false, "Not able to click on document not viewed or download link so cannot check sorting");
					}
					if(click(driver, fp.getIPAnalyticsMostViewedDocumentLink(20), "most viewed document link", action.BOOLEAN)) {
						if(click(driver, fp.getMostViewedDocumentHeaderTextList().get(0),"most viewed document header text", action.BOOLEAN)) {
							if(click(driver, fp.getMostViewedDocumentSortingIconList().get(0), "sorting icon", action.BOOLEAN)) {
								click(driver, fp.getMostViewedDocumentSortingIconList().get(0), "sorting icon", action.BOOLEAN);
								if(fp.checkSorting(SortOrder.Assecending, fp.getMostViewedDocumentName())) {
									appLog.info("document name is verified in Assecending order");
								}else {
									appLog.error("document name is not verified in Assecending order");
									sa.assertTrue(false, "document name is not verified in Assecending order");
								}
							}else {
								appLog.error("Not able to click on sorting icon so cannot check sorint in Assecending Order");
								sa.assertTrue(false, "Not able to click on sorting icon so cannot check sorint in Assecending Order");
							}
							if(click(driver, fp.getMostViewedDocumentSortingIconList().get(0), "sorting icon", action.BOOLEAN)) {
								
								if(fp.checkSorting(SortOrder.Decending, fp.getMostViewedDocumentName())) {
									appLog.info("document name is verified in Decending order");
								}else {
									appLog.error("document name is not verified in Decending order");
									sa.assertTrue(false, "document name is not verified in Decending order");
								}
							}else {
								appLog.error("Not able to click on sorting icon so cannot check sorint in Decending Order");
								sa.assertTrue(false, "Not able to click on sorting icon so cannot check sorint in Decending Order");
							}
						}else {
							appLog.error("Not able to click most viewed document header so cannot click on sorting icon and check soritng");
							sa.assertTrue(false, "Not able to click most viewed document header so cannot click on sorting icon and check soritng");
						}
						if(click(driver, fp.getMostViewedDocumentHeaderTextList().get(1),"most viewed document header text", action.BOOLEAN)) {
							if(click(driver, fp.getMostViewedDocumentSortingIconList().get(1), "sorting icon", action.BOOLEAN)) {
								if(fp.checkSorting(SortOrder.Decending, fp.getMostViewDocumentsViews(10))) {
									appLog.info("document Views is verified in Decending order");
								}else {
									appLog.error("document Views is not verified in Decending order");
									sa.assertTrue(false, "document Views is not verified in Decending order");
								}
							}else {
								appLog.error("Not able to click on sorting icon so cannot check sorint in Decending Order");
								sa.assertTrue(false, "Not able to click on sorting icon so cannot check sorint in Decending Order");
							}
							if(click(driver, fp.getMostViewedDocumentSortingIconList().get(1), "sorting icon", action.BOOLEAN)) {
								if(fp.checkSorting(SortOrder.Assecending, fp.getMostViewDocumentsViews(10))) {
									appLog.info("document Views is verified in Ascending order");
								}else {
									appLog.error("document Views is not verified in Ascending order");
									sa.assertTrue(false, "document Views is not verified in Ascending order");
								}
							}else {
								appLog.error("Not able to click on sorting icon so cannot check sorint in Ascending Order");
								sa.assertTrue(false, "Not able to click on sorting icon so cannot check sorint in Ascending Order");
							}
							
						}else {
							appLog.error("Not able to click most viewed document headder so cannot click on sorting icon and check soritng");
							sa.assertTrue(false, "Not able to click most viewed document header so cannot click on sorting icon and check soritng");
						}
						if(click(driver, fp.getMostviewedDocumentCloseBtn(10), "close button", action.BOOLEAN)) {
							appLog.info("click on close button");
						}else {
							appLog.error("Not able to click on close button so cannot close most viewed document pop up");
							sa.assertTrue(false, "Not able to click on close button so cannot close most viewed document pop up");
						}
					}else {
						appLog.error("Not able to click on most viewed document link so cannot check sorting");
						sa.assertTrue(false, "Not able to click on most viewed document link so cannot check sorting");
					}
					if(click(driver, fp.getIPAnalyticsMostActiveContactsLink(20), "most active contact link", action.BOOLEAN)) {
						if(click(driver, fp.getMostActiveContactHeaderTextList().get(0), "contact name header", action.BOOLEAN)) {
							if(click(driver, fp.getMostActiveDocumentSortingIconList().get(0), "sorting icon", action.BOOLEAN)) {
								if(fp.checkSorting(SortOrder.Decending, fp.getMostActiveContactNameList())) {
									appLog.info("Contact Name is verified in Decending order");
								}else {
									appLog.error("Contact Name is not verified in Decending order");
									sa.assertTrue(false, "Contact Name is not verified in Decending order");
								}
								
							}else {
								appLog.error("Not able to click on sorting icon so cannot check Sorting in Decending order");
								sa.assertTrue(false, "Not able to click on sorting icon so cannot check Sorting in Decending order");
							}
							if(click(driver, fp.getMostActiveDocumentSortingIconList().get(0), "sorting icon", action.BOOLEAN)) {
								if(fp.checkSorting(SortOrder.Assecending, fp.getMostActiveContactNameList())) {
									appLog.info("Contact Name is verified in Assecending order");
								}else {
									appLog.error("Contact Name is not verified in Assecending order");
									sa.assertTrue(false, "Contact Name is not verified in Assecending order");
								}
								
							}else {
								appLog.error("Not able to click on sorting icon so cannot check Sorting in Decending order");
								sa.assertTrue(false, "Not able to click on sorting icon so cannot check Sorting in Decending order");
							}
						}else {
							appLog.error("Not able to click on contact name header so cannot check sorting");
							sa.assertTrue(false, "Not able to click on contact name header so cannot check sorting");
						}
						
						if(click(driver, fp.getMostActiveContactHeaderTextList().get(1), "contact name header", action.BOOLEAN)) {
							if(click(driver, fp.getMostActiveDocumentSortingIconList().get(1), "sorting icon", action.BOOLEAN)) {
								if(fp.checkSorting(SortOrder.Decending, fp.getMostActiveContactFirmNameList())) {
									appLog.info("Firm Name is verified in Decending order");
								}else {
									appLog.error("Firm Name is not verified in Decending order");
									sa.assertTrue(false, "Firm Name is not verified in Decending order");
								}
								
							}else {
								appLog.error("Not able to click on sorting icon so cannot check Sorting in Decending order");
								sa.assertTrue(false, "Not able to click on sorting icon so cannot check Sorting in Decending order");
							}
							if(click(driver, fp.getMostActiveDocumentSortingIconList().get(1), "sorting icon", action.BOOLEAN)) {
								if(fp.checkSorting(SortOrder.Assecending, fp.getMostActiveContactFirmNameList())) {
									appLog.info("Contact Name is verified in Assecending order");
								}else {
									appLog.error("Contact Name is not verified in Assecending order");
									sa.assertTrue(false, "Contact Name is not verified in Assecending order");
								}
							}else {
								appLog.error("Not able to click on sorting icon so cannot check Sorting in Decending order");
								sa.assertTrue(false, "Not able to click on sorting icon so cannot check Sorting in Decending order");
							}
						}else {
							appLog.error("Not able to click on Firm Name header so cannot check sorting");
							sa.assertTrue(false, "Not able to click on Firm Name header so cannot check sorting");
						}
						if(click(driver, fp.getMostActiveContactHeaderTextList().get(2), "contact name header", action.BOOLEAN)) {
							if(click(driver, fp.getMostActiveDocumentSortingIconList().get(2), "sorting icon", action.BOOLEAN)) {
								if(fp.checkSorting(SortOrder.Decending, fp.getMostActiveContactActivityCountList())) {
									appLog.info("Activity Count is verified in Decending order");
								}else {
									appLog.error("Activity Count is not verified in Decending order");
									sa.assertTrue(false, "Activity Count is not verified in Decending order");
								}
								
							}else {
								appLog.error("Not able to click on sorting icon so cannot check Sorting in Decending order");
								sa.assertTrue(false, "Not able to click on sorting icon so cannot check Sorting in Decending order");
							}
							if(click(driver, fp.getMostActiveDocumentSortingIconList().get(2), "sorting icon", action.BOOLEAN)) {
								if(fp.checkSorting(SortOrder.Assecending, fp.getMostActiveContactActivityCountList())) {
									appLog.info("Activity Count is verified in Assecending order");
								}else {
									appLog.error("Activity Count is not verified in Assecending order");
									sa.assertTrue(false, "Activity Count is not verified in Assecending order");
								}
							}else {
								appLog.error("Not able to click on sorting icon so cannot check Sorting in Decending order");
								sa.assertTrue(false, "Not able to click on sorting icon so cannot check Sorting in Decending order");
							}
						}else {
							appLog.error("Not able to click on Activity Count header so cannot check sorting");
							sa.assertTrue(false, "Not able to click on Activity Count header so cannot check sorting");
						}
						if(click(driver, fp.getMostActiveContactCloseBtn(10), "close button", action.BOOLEAN)) {
							appLog.info("click on close button");
						}else {
							appLog.error("Not able to click on close button so cannot close most contact pop up");
							sa.assertTrue(false, "Not able to click on close button so cannot close most active contact pop up");
						}
							
					}else {
						appLog.error("Not able to click on most active contact link so cannot check sorting");
						sa.assertTrue(false, "Not able to click on most active contact link so cannot check sorting");
					}
					
					
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc019_deleteDocAndCheckImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer con = new ContactPageBusinessLayer(driver);
		String sharedfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath);
		String commonfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath);
		String standardfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		String uploadstdfileName=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.UploadedFileStandard);
		String uploadcommonfileName=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.UploadedFileCommon);
		String uploadsharedfileName=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.UploadedFileShared);
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		String dependONTc="M14tc015_onlineImportDocInFRW";
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileStandard);
		String[] SplitedStdFilesName=stdfilesName.split("<break>");
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileCommon);
		String [] SplitedCommonFilesName=commonfilesName.split("<break>");
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileShared);
		String [] SplitedSharedFileName=shrdfilesName.split("<break>");	
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.FundraisingWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(fp.verifyFolderPathdummy(sharedfolderpath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 20)) {
					if (fp.clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Delete,uploadsharedfileName,Workspace.FundraisingWorkspace, 60,
							"Yes")) {
						PublicFlag = false;
						if (click(driver, fp.getDocumentDeleteYesBtn(Workspace.FundraisingWorkspace,60), "Yes", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on File Delete Yes Button");
							FRW_UniqueDocument = FRW_UniqueDocument - 1;
							FRW_DocumentViews = FRW_DocumentViews - 1;
							M14Contact1_ActivityCount = String.valueOf((Integer.parseInt(M14Contact1_ActivityCount) - 1));
							ExcelUtils.writeData(FRW_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.FRW_Value);
							ExcelUtils.writeData(FRW_DocumentViews, "IPAnalytics", excelLabel.Statistics, "No of Document Views", excelLabel.FRW_Value);
							ExcelUtils.writeData(M14Contact1_ActivityCount, "Contacts", excelLabel.Variable_Name, "M14Contact1", excelLabel.Activity_Count);
							List<String> filesName = createListOutOfString(ExcelUtils.readData("IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.FRW_DocumentsName));
							List<String> NewFileList = new CommonLib().removeStringFromList(filesName,uploadsharedfileName);
							ExcelUtils.writeData(createStringOutOfList(NewFileList), "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.FRW_DocumentsName);
							if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
									List<String>result=compareMultipleListOnBasisOfTitle(driver,uploadsharedfileName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.FundraisingWorkspace, PageName.FundsPage));
									if(!result.isEmpty()) {
										appLog.info("All files are verified: "+uploadsharedfileName+" document is deleted successfully");
									}else {
										appLog.error(uploadsharedfileName+" is not deleted from content grid");
										sa.assertTrue(false, uploadsharedfileName+" is not deleted from content grid");
									}
							}else {
								appLog.error("Not able to click on refresh icon so cannot verify deleted documents.");
								sa.assertTrue(false, "Not able to click on refresh icon so cannot verify deleted documents");
							}
						} else {
							appLog.info("Not able to Click on Delete File Yes Button.");
							sa.assertTrue(false, "Not able to Click on Delete File Yes Button.");
						}
					} else {
						appLog.info("Not able to Click on Delete File Link.");
						sa.assertTrue(false, "Not able to Click on Delete File Link.");
					}
				}else {
					appLog.error("Not able to click on folder : "+sharedfolderpath+" so cannot delete uploaded document: "+uploadsharedfileName);
					sa.assertTrue(false, "Not able to click on folder : "+sharedfolderpath+" so cannot delete uploaded document: "+uploadsharedfileName);
				}
				if(fp.verifyFolderPathdummy(commonfolderpath, null, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 20)) {
					if (fp.clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Delete,uploadcommonfileName,Workspace.FundraisingWorkspace, 60,
							"Yes")) {
						PublicFlag = false;
						if (click(driver, fp.getDocumentDeleteYesBtn(Workspace.FundraisingWorkspace,60), "Yes", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on File Delete Yes Button");
							FRW_UniqueDocument = FRW_UniqueDocument - 1;
							FRW_DocumentViews = FRW_DocumentViews - 1;
							M14Contact1_ActivityCount = String.valueOf((Integer.parseInt(M14Contact1_ActivityCount) - 1));
							ExcelUtils.writeData(FRW_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.FRW_Value);
							ExcelUtils.writeData(FRW_DocumentViews, "IPAnalytics", excelLabel.Statistics, "No of Document Views", excelLabel.FRW_Value);
							ExcelUtils.writeData(M14Contact1_ActivityCount, "Contacts", excelLabel.Variable_Name, "M14Contact1", excelLabel.Activity_Count);
							List<String> filesName = createListOutOfString(ExcelUtils.readData("IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.FRW_DocumentsName));
							List<String> NewFileList = new CommonLib().removeStringFromList(filesName,uploadcommonfileName);
							ExcelUtils.writeData(createStringOutOfList(NewFileList), "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.FRW_DocumentsName);
							if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
									List<String>result=compareMultipleListOnBasisOfTitle(driver,uploadcommonfileName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.FundraisingWorkspace, PageName.FundsPage));
									if(!result.isEmpty()) {
										appLog.info("All files are verified: "+uploadcommonfileName+" document is deleted successfully");
									}else {
										appLog.error(uploadcommonfileName+" is not deleted from content grid");
										sa.assertTrue(false, uploadcommonfileName+" is not deleted from content grid");
									}
							}else {
								appLog.error("Not able to click on refresh icon so cannot verify deleted documents.");
								sa.assertTrue(false, "Not able to click on refresh icon so cannot verify deleted documents");
							}
						} else {
							appLog.info("Not able to Click on Delete File Yes Button.");
							sa.assertTrue(false, "Not able to Click on Delete File Yes Button.");
						}
					} else {
						appLog.info("Not able to Click on Delete File Link.");
						sa.assertTrue(false, "Not able to Click on Delete File Link.");
					}
				}else {
					appLog.error("Not able to click on folder : "+commonfolderpath+" so cannot delete uploaded document: "+uploadcommonfileName);
					sa.assertTrue(false, "Not able to click on folder : "+commonfolderpath+" so cannot delete uploaded document: "+uploadcommonfileName);
				}
				switchToDefaultContent(driver);
				if(fp.clickOnTab(TabName.ContactTab)) {
					if(con.clickOnCreatedContact(M14Contact1FirstName, M14Contact1LastName, null)) {
						switchToFrame(driver, 30,fp.getFrame(PageName.ContactsPage, 20));
						String[] InsName= {M14Institution1,M14Institution2};
						for(int i=0; i<2; i++) {
						if(fp.verifyFolderPathdummy(standardfolderpath,InsName[i], null, M14FundName1, PageName.ContactsPage, Workspace.FundraisingWorkspace, 20)) {
								if (fp.clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Delete,uploadstdfileName,Workspace.FundraisingWorkspace, 60,
										"Yes")) {
									PublicFlag = false;
									if (click(driver, fp.getDocumentDeleteYesBtn(Workspace.FundraisingWorkspace,60), "Yes", action.SCROLLANDBOOLEAN)) {
										appLog.info("Clicked on File Delete Yes Button");
										if(i==1) {
											FRW_UniqueDocument = FRW_UniqueDocument - 1;
											FRW_DocumentViews = FRW_DocumentViews - 1;
											M14Contact1_ActivityCount = String.valueOf((Integer.parseInt(M14Contact1_ActivityCount) - 1));
											ExcelUtils.writeData(FRW_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.FRW_Value);
											ExcelUtils.writeData(FRW_DocumentViews, "IPAnalytics", excelLabel.Statistics, "No of Document Views", excelLabel.FRW_Value);
											ExcelUtils.writeData(M14Contact1_ActivityCount, "Contacts", excelLabel.Variable_Name, "M14Contact1", excelLabel.Activity_Count);
											List<String> filesName = createListOutOfString(ExcelUtils.readData("IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.FRW_DocumentsName));
											List<String> NewFileList = new CommonLib().removeStringFromList(filesName,uploadstdfileName);
											ExcelUtils.writeData(createStringOutOfList(NewFileList), "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.FRW_DocumentsName);
										}
										if(click(driver, fp.ContentGridRefreshBtn(Workspace.FundraisingWorkspace, 30),"investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
											List<String>result=compareMultipleListOnBasisOfTitle(driver,uploadstdfileName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.FundraisingWorkspace, PageName.ContactsPage));
											if(!result.isEmpty()) {
												appLog.info("All files are verified: "+uploadstdfileName+" document is deleted successfully");
											}else {
												appLog.error(uploadstdfileName+" is not deleted from content grid");
												sa.assertTrue(false, uploadstdfileName+" is not deleted from content grid");
											}
										}else {
											appLog.error("Not able to click on refresh icon so cannot verify deleted documents.");
											sa.assertTrue(false, "Not able to click on refresh icon so cannot verify deleted documents");
										}
									} else {
										appLog.info("Not able to Click on Delete File Yes Button.");
										sa.assertTrue(false, "Not able to Click on Delete File Yes Button.");
									}
								} else {
									appLog.info("Not able to Click on Delete File Link.");
									sa.assertTrue(false, "Not able to Click on Delete File Link.");
								}
						}else {
							appLog.error("Not able to click on folder path: "+standardfolderpath+" so cannot delete uploaded document");
							sa.assertTrue(false, "Not able to click on folder path: "+standardfolderpath+" so cannot delete uploaded document");
						}
					}
					}else {
						appLog.error("Not able to click on created contact: "+M14Contact1FirstName+" "+M14Contact1LastName+" so cannot delete uloaded document");
						sa.assertTrue(false, "Not able to click on created contact: "+M14Contact1FirstName+" "+M14Contact1LastName+" so cannot delete uloaded document");
					}
				}else {
					appLog.error("NOt able to click on contact tab so cannot delete document");
					sa.assertTrue(false, "NOt able to click on contact tab so cannot delete document");
				}
				switchToDefaultContent(driver);
				if(fp.clickOnTab(TabName.FundsTab)) {
					if(fp.clickOnCreatedFund(M14FundName1)) {
						switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
						if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
							String parentid = switchOnWindow(driver);
							List<String> result = fp.verifyIPAnalyticsTargetValues(FRW_UniqueDocument, FRW_DocumentViews,
									FRW_DocumentDownload, FRW_DocumentNotViewedOrDownload, FRW_ContactGrantedAccess,FRW_ContactNotViewedAnyDocument);
							if (!result.isEmpty()) {
								for (int i = 0; i < result.size(); i++) {
									sa.assertTrue(false, result.get(i));
								}
							} else {
								appLog.info("IP Analytics target value is verified Successfully.");
							}
							result.clear();
							result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.FundraisingWorkspace);
							if (!result.isEmpty()) {
								for (int i = 0; i < result.size(); i++) {
									sa.assertTrue(false, result.get(i));
								}
							} else {
								appLog.info("Most View Document popup Error Message is verified.");
							}
							SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(SplitedStdFilesName[3]+"<break>"+SplitedCommonFilesName[3]+"<break>"+SplitedSharedFileName[3], false);
							sa.combineAssertions(documents);
							SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
							sa.combineAssertions(rsult);
							SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1, M14Contact1_ActivityCount+"<break>"+M14Contact2_ActivityCount);
							sa.combineAssertions(activityCount);
							driver.close();
							driver.switchTo().window(parentid);
						} else {
							appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
							sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
						}
					}else {
						appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot verify IP Analytics");
						sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot verify IP Analytics");
					}
				}else {
					appLog.error("Not able to click on fund tab so cannot verify IP Analytics");
					sa.assertTrue(false, "Not able to click on fund tab so cannot verify IP Analytics");
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot import files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot import files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot import files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot import files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M14tc020_inviteContact3AndDeleteSharedFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String sharedfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath);
		String dependONTc="M14tc015_onlineImportDocInFRW";
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileStandard);
		String[] SplitedStdFilesName=stdfilesName.split("<break>");
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileCommon);
		String [] SplitedCommonFilesName=commonfilesName.split("<break>");
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileShared);
		String [] SplitedSharedFileName=shrdfilesName.split("<break>");	
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.FundraisingWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
				if(fp.inviteContact(null,M14Contact3EmailId,sharedfolderpath, FolderType.Shared,"download", "Yes",null,"All Folders", Workspace.FundraisingWorkspace,M14Institution2,"Yes",true)) {
					appLog.info("contact is invites successfully from fundraising workspace: "+M14Contact3FirstName+" "+M14Contact3LastName+" from "+sharedfolderpath);
				}else {
					appLog.error("Not able to invite contact from fundraising workspace: "+M14Contact3FirstName+" "+M14Contact3LastName+" from "+sharedfolderpath);
					sa.assertTrue(false, "Not able to invite contact from fundraising workspace: "+M14Contact3FirstName+" "+M14Contact3LastName+" from "+sharedfolderpath);
				}
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(click(driver, fp.getManageFolderIcon(Workspace.FundraisingWorkspace, 30), "Manage folder icon", action.BOOLEAN)){
					String id=null;
					id=fp.getCreatedFolderId(sharedfolderpath, PageName.ManageFolderPopUp, 20);
					System.err.println("id>>>>>>"+id);
					if(id!=null) {
						if(fp.clickOnDeleteFolderButton(id)) {
							if(click(driver, fp.getFolderDeleteYesBtn(Workspace.FundraisingWorkspace, PageName.ManageFolderPopUp, 10), "folder delete yes Button", action.BOOLEAN)) {
								appLog.info("clicked on folder delete yes Button");
								ThreadSleep(2000);
								if(fp.getCreatedFolderId(sharedfolderpath, PageName.ManageFolderPopUp, 10)!=null) {
									appLog.info(sharedfolderpath+" is not deleted, folder is visible in manage foler pop up");
									sa.assertTrue(false, sharedfolderpath+" is not deleted, folder is visible in manage foler pop up");
								}else {
									appLog.error(sharedfolderpath+" is deleted successfully, it is not visible in manage foler pop up");
									FRW_ContactGrantedAccess=FRW_ContactGrantedAccess-1;
									FRW_ContactNotViewedAnyDocument=FRW_ContactNotViewedAnyDocument-1;
									ExcelUtils.writeData(FRW_ContactGrantedAccess, "IPAnalytics", excelLabel.Statistics, "No of Contacts Granted Access", excelLabel.FRW_Value);
									ExcelUtils.writeData(FRW_ContactNotViewedAnyDocument, "IPAnalytics", excelLabel.Statistics, "No of Contacts who have not Viewed any Document", excelLabel.FRW_Value);
									ExcelUtils.writeData("No Access", "Contacts", excelLabel.Variable_Name, "M14Contact3", excelLabel.Contact_Access);
								}
							}else {
								appLog.error("Yes button is not displaying on folder delete pop up so cannot click on Yes button");
								sa.assertTrue(false, "yes button is not displaying on folder delete pop up so cannot click on yes button");
							}
							if(click(driver, fp.getManageFolderCloseButton(Workspace.FundraisingWorkspace, 20), "manage folder close button", action.SCROLLANDBOOLEAN)) {
								appLog.info("manage folder pop is closed");
							}else {
								appLog.error("Not able to click on close button so cannot close manage folder pop up");
								sa.assertTrue(false, "Not able to click on close button so cannot close manage folder pop up");
							}
						}else {
							appLog.error("Not able to click on folder: "+sharedfolderpath+" delete icon so cannot delete folder");
							sa.assertTrue(false, "Not able to click on folder: "+sharedfolderpath+" delete icon so cannot delete folder");
						}
					}else {
						appLog.error(sharedfolderpath+" is not available in the manage folder structure so cannot click on folder "+sharedfolderpath+" delete icon");
						sa.assertTrue(false, sharedfolderpath+" is not available in the manage folder structure so cannot click on folder "+sharedfolderpath+" delete icon");
					}
				}else {
					appLog.error("Not able to click on Manage folder icon so cannot delete folder: "+sharedfolderpath);
					sa.assertTrue(false, "Not able to click on Manage folder icon so cannot delete folder: "+sharedfolderpath);
				}
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					String childWindowID = null;
					String childChildWindow = null;
					Set<String> lst1 = driver.getWindowHandles();
					Iterator<String> I1 = lst1.iterator();
					while (I1.hasNext()) {
						String windowID = I1.next();
						if (windowID.equalsIgnoreCase(parentid)) {
							appLog.info("Parent Id is Matched");
						} else {
							childWindowID = windowID;
							appLog.info("Stroged child Window Id");
							break;
						}
					}
					List<String> result = fp.verifyIPAnalyticsTargetValues(FRW_UniqueDocument, FRW_DocumentViews,
							FRW_DocumentDownload, FRW_DocumentNotViewedOrDownload, FRW_ContactGrantedAccess,FRW_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.FundraisingWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(SplitedStdFilesName[3]+"<break>"+SplitedCommonFilesName[3]+"<break>"+SplitedSharedFileName[3], false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1, M14Contact1_ActivityCount+"<break>"+M14Contact2_ActivityCount);
					sa.combineAssertions(activityCount);
					if (click(driver, fp.getIPAnalyticsDocumentNotViwedorDownloadLink(30), "Document not viewed or download link",action.SCROLLANDBOOLEAN)) {
							if(!SplitedSharedFileName[3].isEmpty()) {
								WebElement ele=FindElement(driver, "//a[@text='"+SplitedSharedFileName[3]+"']",SplitedSharedFileName[3]+"  file Name link", action.BOOLEAN, 10);
								if(ele!=null) {
									if(click(driver, ele, "file name", action.BOOLEAN)) {
										appLog.info("Clicked on File Name : "+SplitedSharedFileName[3]);
										Set<String>lst11 = driver.getWindowHandles();
										Iterator<String> II1 = lst11.iterator();
										while (II1.hasNext()) {
											String windowID = II1.next();
											if (windowID.equalsIgnoreCase(parentid) || windowID.equalsIgnoreCase(childWindowID)) {
												appLog.info("window Id is Matched");
											} else {
												childChildWindow = windowID;
												appLog.info("Stroged child child Window Id");
												driver.switchTo().window(childChildWindow);
												break;
											}
										}
										WebElement ErrorMsg = fp.getFileNotFoundErrorMessage(60);
										if(ErrorMsg!=null) {
											if(ErrorMsg.getText().trim().equalsIgnoreCase(FundsPageErrorMessage.DocumentNotFoundErrorMsg)) {
												appLog.info(FundsPageErrorMessage.DocumentNotFoundErrorMsg+" error message is matched in file : "+SplitedSharedFileName[3]);
											}else {
												appLog.error(FundsPageErrorMessage.DocumentNotFoundErrorMsg+" error message is not matched in file : "+SplitedSharedFileName[3]);
												sa.assertTrue(false, FundsPageErrorMessage.DocumentNotFoundErrorMsg+" error message is not matched in file : "+SplitedSharedFileName[3]);
											}
										}else {
											appLog.error(FundsPageErrorMessage.DocumentNotFoundErrorMsg+" error message is not displayed in file : "+SplitedSharedFileName[3]);
											sa.assertTrue(false, FundsPageErrorMessage.DocumentNotFoundErrorMsg+" error message is not displayed in file : "+SplitedSharedFileName[3]);
										}
										driver.close();
										driver.switchTo().window(childWindowID);
									}else {
										appLog.error("Not able to click on file Name : "+SplitedSharedFileName[3]);
										sa.assertTrue(false,"Not able to click on file Name : "+SplitedSharedFileName[3]);
									}
								}else {
									appLog.error(SplitedSharedFileName[3]+ "is not available in Document not viewed or download so cannot click on file");
									sa.assertTrue(false, SplitedSharedFileName[3]+ "is not available in Document not viewed or download so cannot click on file");
								}
							}else {
								appLog.error("file name is not written in excel sheet so cannot click on file name: "+SplitedSharedFileName[3]);
								sa.assertTrue(false, "file name is not written in excel sheet so cannot click on file name: "+SplitedSharedFileName[3]);
							}
						if (click(driver, fp.getDocumentNotViewedOrDownloadedCloseBtn(30), "Document not viewed or download Close button",action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Document not viewed or download pop up Close button");
						} else {
							appLog.info("Not able to Click on Document not viewed or download Close button");
							result.add("Not able to Click on Document not viewed or download Close button");
						}
					}else {
						appLog.error("Not able to click on Document not viewed or download link so cannot click on documents");
						sa.assertTrue(false,"Not able to click on Document not viewed or download link so cannot click on document");
					}
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot delete folder: "+sharedfolderpath);
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot delete folder: "+sharedfolderpath);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot import files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot import files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc021_removeInvestorFromManageInstorAndCheckImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String sharedfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath);
		String dependONTc="M14tc015_onlineImportDocInFRW";
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileStandard);
		String[] SplitedStdFilesName=stdfilesName.split("<break>");
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileCommon);
		String [] SplitedCommonFilesName=commonfilesName.split("<break>");
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileShared);
		String [] SplitedSharedFileName=shrdfilesName.split("<break>");	
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.FundraisingWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(click(driver,fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60), "manage investor icon", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on manage investor icon");
					ThreadSleep(3000);
					if(fp.selectInstitutionOrLP(M14Institution2, Workspace.FundraisingWorkspace, 60)) {
						appLog.info(M3Institution1+" is removed from manage investor");
						FRW_ContactGrantedAccess=FRW_ContactGrantedAccess-1;
						ExcelUtils.writeData(CommonVariables.FRW_ContactGrantedAccess, "IPAnalytics", excelLabel.Statistics, "No of Contacts Granted Access", excelLabel.FRW_Value);
						ExcelUtils.writeData("No Access", "Contacts", excelLabel.Variable_Name, "M14Contact2", excelLabel.Contact_Access);
						if(click(driver, fp.getManageInvestorDeletedPopupCloseButton(Workspace.FundraisingWorkspace, 60), "manage investor remove pop up close button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on close button");
						}else {
							appLog.error("manage investor remove pop up close buttton is not present");
							sa.assertTrue(false, "manage investor remove pop up close buttton is not present");
						}
					}else {
						appLog.error("Not able to remove institution from manage investor: "+M14Institution2);
						sa.assertTrue(false, "Not able to remove institution from manage investor: "+M14Institution2);
					}
					if(click(driver, fp.getManageInvestorDoneButton(Workspace.FundraisingWorkspace, 30), "manage investor close button", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on manage investor close button");
					}else {
						appLog.error("Not able to close manage investor pop up");
						sa.assertTrue(false, "Not able to close manage investor pop up");
					}
					}else {
						appLog.error("Not able to click on manage investor icon so cannot remove institution : "+M14Institution2);
						sa.assertTrue(false, "Not able to click on manage investor icon so cannot remove institution : "+M14Institution2);
					}
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(FRW_UniqueDocument, FRW_DocumentViews,
							FRW_DocumentDownload, FRW_DocumentNotViewedOrDownload, FRW_ContactGrantedAccess,FRW_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.FundraisingWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(SplitedStdFilesName[3]+"<break>"+SplitedCommonFilesName[3]+"<break>"+SplitedSharedFileName[3], false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName, updatedInsName, M14Contact1_ActivityCount);
					sa.combineAssertions(activityCount);
					activityCount=fp.verifyMostActiveContactInIPAnalytics(M14Contact2FirstName+" "+M14Contact2LastName, M14Institution1, M14Contact2_ActivityCount);
					try {
						activityCount.assertAll();
					}catch (Throwable e) {
						appLog.info(M14Contact2FirstName+" "+M14Contact2LastName+" is not displayed in Most Active Contact pop up");
					}
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot delete folder: "+sharedfolderpath);
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot delete folder: "+sharedfolderpath);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot import files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot import files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc022_addInvestorFromManageInstorAndCheckImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String sharedfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath);
		String dependONTc="M14tc015_onlineImportDocInFRW";
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileStandard);
		String[] SplitedStdFilesName=stdfilesName.split("<break>");
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileCommon);
		String [] SplitedCommonFilesName=commonfilesName.split("<break>");
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileShared);
		String [] SplitedSharedFileName=shrdfilesName.split("<break>");	
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.FundraisingWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(click(driver,fp.getManageInvestorIcon(Workspace.FundraisingWorkspace, 60), "manage investor icon", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on manage investor icon");
					ThreadSleep(3000);
					if(fp.selectInstitutionOrLP(M14Institution2, Workspace.FundraisingWorkspace, 60)) {
						appLog.info(M3Institution2+" is added from manage investor");
						if(click(driver, fp.getManageInvestorAddedPopupCloseButton(Workspace.FundraisingWorkspace, 60), "manage investor add pop up close button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on close button");
						}else {
							appLog.error("manage investor add pop up close buttton is not present");
							sa.assertTrue(false, "manage investor add pop up close buttton is not present");
						}
					}else {
						appLog.error("Not able to add institution from manage investor: "+M14Institution2);
						sa.assertTrue(false, "Not able to add institution from manage investor: "+M14Institution2);
					}
					if(click(driver, fp.getManageInvestorDoneButton(Workspace.FundraisingWorkspace, 30), "manage investor close button", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on manage investor close button");
					}else {
						appLog.error("Not able to close manage investor pop up");
						sa.assertTrue(false, "Not able to close manage investor pop up");
					}
					switchToDefaultContent(driver);
					if(fp.inviteContact(M14Institution2,M14Contact2EmailId,null, FolderType.Standard,"upload", "Yes",null,null, Workspace.FundraisingWorkspace,M14Contact2EmailId,"Yes",false)) {
						appLog.info("contact is invites successfully from fundraising workspace: "+M14Contact3FirstName+" "+M14Contact3LastName+" from "+sharedfolderpath);
					}else {
						appLog.error("Not able to invite contact from fundraising workspace: "+M14Contact3FirstName+" "+M14Contact3LastName+" from "+sharedfolderpath);
						sa.assertTrue(false, "Not able to invite contact from fundraising workspace: "+M14Contact3FirstName+" "+M14Contact3LastName+" from "+sharedfolderpath);
					}
					switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
					}else {
						appLog.error("Not able to click on manage investor icon so cannot add institution : "+M14Institution2);
						sa.assertTrue(false, "Not able to click on manage investor icon so cannot add institution : "+M14Institution2);
					}
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(FRW_UniqueDocument, FRW_DocumentViews,
							FRW_DocumentDownload, FRW_DocumentNotViewedOrDownload, FRW_ContactGrantedAccess,FRW_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.FundraisingWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(SplitedStdFilesName[3]+"<break>"+SplitedCommonFilesName[3]+"<break>"+SplitedSharedFileName[3], false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1, M14Contact1_ActivityCount+"<break>"+M14Contact2_ActivityCount);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot add institution : "+M14Institution2+" add provide access to contact: "+M14Contact2FirstName+" "+M14Contact2LastName);
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot add institution : "+M14Institution2+" add provide access to contact: "+M14Contact2FirstName+" "+M14Contact2LastName);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot so cannot add institution : "+M14Institution2+" add provide access to contact: "+M14Contact2FirstName+" "+M14Contact2LastName);
			sa.assertTrue(false, "Not able to click on fund tab so cannot so cannot add institution : "+M14Institution2+" add provide access to contact: "+M14Contact2FirstName+" "+M14Contact2LastName);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	
	@Test
	public void M14tc024_removeContactAccessAndCheckImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTc="M14tc015_onlineImportDocInFRW";
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileStandard);
		String[] SplitedStdFilesName=stdfilesName.split("<break>");
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileCommon);
		String [] SplitedCommonFilesName=commonfilesName.split("<break>");
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileShared);
		String [] SplitedSharedFileName=shrdfilesName.split("<break>");
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.FundraisingWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(fp.verifyFolderPathdummy("", M14Institution1, null, null, PageName.FundsPage, Workspace.FundraisingWorkspace, 30)) {
					if(fp.revokeContactAccess(M14Contact1EmailId, Workspace.FundraisingWorkspace)) {
						appLog.info(M14Contact1EmailId+ " contact access is removed from contact access.");
						FRW_UniqueDocument=FRW_UniqueDocument+2;
						FRW_DocumentViews=FRW_DocumentViews+2;
						FRW_DocumentDownload = FRW_DocumentDownload+2;
						FRW_DocumentNotViewedOrDownload=FRW_DocumentNotViewedOrDownload+1;
						FRW_ContactGrantedAccess=FRW_ContactGrantedAccess-1;
						ExcelUtils.writeData(CommonVariables.FRW_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.FRW_Value);
						ExcelUtils.writeData(CommonVariables.FRW_DocumentDownload, "IPAnalytics", excelLabel.Statistics, "No of Document Downloads", excelLabel.FRW_Value);
						ExcelUtils.writeData(CommonVariables.FRW_DocumentViews, "IPAnalytics", excelLabel.Statistics, "No of Document Views", excelLabel.FRW_Value);
						ExcelUtils.writeData(CommonVariables.FRW_DocumentNotViewedOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.FRW_Value);
						ExcelUtils.writeData(CommonVariables.FRW_ContactGrantedAccess, "IPAnalytics", excelLabel.Statistics, "No of Contacts Granted Access", excelLabel.FRW_Value);
					}else {
						appLog.error(M14Contact1EmailId+ " contact access is not removed from contact access.");
						sa.assertTrue(false, M14Contact1EmailId+ " contact access is not removed from contact access.");
					}
				}else {
					appLog.error("Not able to click on folderpath: "+M14Institution1+" so cannot remove contact access: "+M14Contact1EmailId);
					sa.assertTrue(false, "Not able to click on folderpath: "+M14Institution1+" so cannot remove contact access: "+M14Contact1EmailId);
				}
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(FRW_UniqueDocument, FRW_DocumentViews,
							FRW_DocumentDownload, FRW_DocumentNotViewedOrDownload, FRW_ContactGrantedAccess,FRW_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.FundraisingWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(SplitedStdFilesName[3]+"<break>"+SplitedCommonFilesName[3]+"<break>"+SplitedSharedFileName[3], false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName, updatedInsName, M14Contact1_ActivityCount);
					try {
						activityCount.assertAll();
					}catch (Throwable e) {
						appLog.error(updatedContactFName+" "+updatedContactLName+" is not displayed in Most Active Contact pop up");
					}
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot contact access in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot contact access in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot remove contact access in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot remove contact access in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc025_reInviteContactAccessAndCheckImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTc="M14tc015_onlineImportDocInFRW";
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileStandard);
		String[] SplitedStdFilesName=stdfilesName.split("<break>");
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileCommon);
		String [] SplitedCommonFilesName=commonfilesName.split("<break>");
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileShared);
		String [] SplitedSharedFileName=shrdfilesName.split("<break>");	
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.FundraisingWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
				if(fp.inviteContact(environment, mode, M14Institution1, M14Contact1EmailId, null, FolderType.Standard,"upload","Yes", null,null, Workspace.FundraisingWorkspace, null)) {
					appLog.info("invite contact successfully: "+M14Contact1FirstName+" "+M14Contact1LastName);
					FRW_UniqueDocument=FRW_UniqueDocument-2;
					FRW_DocumentViews=FRW_DocumentViews-2;
					FRW_DocumentDownload=FRW_DocumentDownload-2;
					FRW_DocumentNotViewedOrDownload=FRW_DocumentNotViewedOrDownload-1;
					FRW_ContactGrantedAccess=FRW_ContactGrantedAccess+1;
					ExcelUtils.writeData(CommonVariables.FRW_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.FRW_Value);
					ExcelUtils.writeData(CommonVariables.FRW_DocumentDownload, "IPAnalytics", excelLabel.Statistics, "No of Document Downloads", excelLabel.FRW_Value);
					ExcelUtils.writeData(CommonVariables.FRW_DocumentViews, "IPAnalytics", excelLabel.Statistics, "No of Document Views", excelLabel.FRW_Value);
					ExcelUtils.writeData(CommonVariables.FRW_DocumentNotViewedOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.FRW_Value);
					ExcelUtils.writeData(CommonVariables.FRW_ContactGrantedAccess, "IPAnalytics", excelLabel.Statistics, "No of Contacts Granted Access", excelLabel.FRW_Value);
				}else {
					appLog.error("Not able to invite contact: "+M14Contact1FirstName+" "+M14Contact1LastName);
					sa.assertTrue(false, "Not able to invite contact: "+M14Contact1FirstName+" "+M14Contact1LastName);
				}
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(FRW_UniqueDocument, FRW_DocumentViews,
							FRW_DocumentDownload, FRW_DocumentNotViewedOrDownload, FRW_ContactGrantedAccess,FRW_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.FundraisingWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(SplitedStdFilesName[3]+"<break>"+SplitedCommonFilesName[3]+"<break>"+SplitedSharedFileName[3], false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1, M14Contact1_ActivityCount+"<break>"+M14Contact2_ActivityCount);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot contact access in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot contact access in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot remove contact access in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot remove contact access in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M14tc026_removeContactAccessFromContactPageAndCheckImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer con = new ContactPageBusinessLayer(driver);
		String dependONTc="M14tc015_onlineImportDocInFRW";
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileStandard);
		String[] SplitedStdFilesName=stdfilesName.split("<break>");
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileCommon);
		String [] SplitedCommonFilesName=commonfilesName.split("<break>");
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileShared);
		String [] SplitedSharedFileName=shrdfilesName.split("<break>");	
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.FundraisingWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot read and write ip analytics count");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot read and write ip analytics count");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot read and write ip analytics count");
			sa.assertTrue(false, "Not able to click on fund tab so cannot read and write ip analytics count");
		}
		switchToDefaultContent(driver);
		if(con.clickOnTab(TabName.ContactTab)) {
			if(con.clickOnCreatedContact(M14Contact1FirstName, M14Contact1LastName, null)) {
				switchToFrame(driver, 30,con.getFrame(PageName.ContactsPage, 30));
				if(con.removeContactAccessFromContactPage(M14FundName1, Workspace.FundraisingWorkspace)) {
					appLog.info(M14Contact1FirstName+" "+M14Contact1LastName+" Contact Access is removed");
					FRW_UniqueDocument=FRW_UniqueDocument+2;
					FRW_DocumentViews=FRW_DocumentViews+2;
					FRW_DocumentDownload=FRW_DocumentDownload+2;
					FRW_DocumentNotViewedOrDownload=FRW_DocumentNotViewedOrDownload+1;
					FRW_ContactGrantedAccess=FRW_ContactGrantedAccess-1;
					ExcelUtils.writeData(CommonVariables.FRW_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.FRW_Value);
					ExcelUtils.writeData(CommonVariables.FRW_DocumentDownload, "IPAnalytics", excelLabel.Statistics, "No of Document Downloads", excelLabel.FRW_Value);
					ExcelUtils.writeData(CommonVariables.FRW_DocumentViews, "IPAnalytics", excelLabel.Statistics, "No of Document Views", excelLabel.FRW_Value);
					ExcelUtils.writeData(CommonVariables.FRW_DocumentNotViewedOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.FRW_Value);
					ExcelUtils.writeData(CommonVariables.FRW_ContactGrantedAccess, "IPAnalytics", excelLabel.Statistics, "No of Contacts Granted Access", excelLabel.FRW_Value);
				}else {
					appLog.error(M14Contact1FirstName+" "+M14Contact1LastName+" Contact Access is not removed");
					sa.assertTrue(false, M14Contact1FirstName+" "+M14Contact1LastName+" Contact Access is not removed");
				}
				switchToDefaultContent(driver);
			}else {
				appLog.error("Not able to click on created contact: "+M14Contact1FirstName+" "+M14Contact1LastName+" so cannot remove contact access");
				sa.assertTrue(false, "Not able to click on created contact: "+M14Contact1FirstName+" "+M14Contact1LastName+" so cannot remove contact access");
			}
		}else {
			appLog.error("Not able to click on contact tab so cannot remove contact access from contact page");
			sa.assertTrue(false, "Not able to click on contact tab so cannot remove contact access from contact page");
		}
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(FRW_UniqueDocument, FRW_DocumentViews,
							FRW_DocumentDownload, FRW_DocumentNotViewedOrDownload, FRW_ContactGrantedAccess,FRW_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.FundraisingWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(SplitedStdFilesName[3]+"<break>"+SplitedCommonFilesName[3]+"<break>"+SplitedSharedFileName[3], false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName, updatedInsName, M14Contact1_ActivityCount);
					try {
						activityCount.assertAll();
					}catch (Throwable e) {
						appLog.error(updatedContactFName+" "+updatedContactLName+" is not displayed in Most Active Contact pop up");
					}
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot contact check ip analytics");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot check ip analytics");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot check ip analytics");
			sa.assertTrue(false, "Not able to click on fund tab so cannot check ip analytics");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc027_reInviteContactAccessAndCheckImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTc="M14tc015_onlineImportDocInFRW";
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileStandard);
		String[] SplitedStdFilesName=stdfilesName.split("<break>");
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileCommon);
		String [] SplitedCommonFilesName=commonfilesName.split("<break>");
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileShared);
		String [] SplitedSharedFileName=shrdfilesName.split("<break>");	
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.FundraisingWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
				if(fp.inviteContact(environment, mode, M14Institution1, M14Contact1EmailId, null, FolderType.Standard,"upload","Yes", null,null, Workspace.FundraisingWorkspace, null)) {
					appLog.info("invite contact successfully: "+M14Contact1FirstName+" "+M14Contact1LastName);
					FRW_UniqueDocument=FRW_UniqueDocument-2;
					FRW_DocumentViews=FRW_DocumentViews-2;
					FRW_DocumentDownload=FRW_DocumentDownload-2;
					FRW_DocumentNotViewedOrDownload=FRW_DocumentNotViewedOrDownload-1;
					FRW_ContactGrantedAccess=FRW_ContactGrantedAccess+1;
					ExcelUtils.writeData(CommonVariables.FRW_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.FRW_Value);
					ExcelUtils.writeData(CommonVariables.FRW_DocumentDownload, "IPAnalytics", excelLabel.Statistics, "No of Document Downloads", excelLabel.FRW_Value);
					ExcelUtils.writeData(CommonVariables.FRW_DocumentViews, "IPAnalytics", excelLabel.Statistics, "No of Document Views", excelLabel.FRW_Value);
					ExcelUtils.writeData(CommonVariables.FRW_DocumentNotViewedOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.FRW_Value);
					ExcelUtils.writeData(CommonVariables.FRW_ContactGrantedAccess, "IPAnalytics", excelLabel.Statistics, "No of Contacts Granted Access", excelLabel.FRW_Value);
				}else {
					appLog.error("Not able to invite contact: "+M14Contact1FirstName+" "+M14Contact1LastName);
					sa.assertTrue(false, "Not able to invite contact: "+M14Contact1FirstName+" "+M14Contact1LastName);
				}
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(FRW_UniqueDocument, FRW_DocumentViews,
							FRW_DocumentDownload, FRW_DocumentNotViewedOrDownload, FRW_ContactGrantedAccess,FRW_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.FundraisingWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(SplitedStdFilesName[3]+"<break>"+SplitedCommonFilesName[3]+"<break>"+SplitedSharedFileName[3], false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1, M14Contact1_ActivityCount+"<break>"+M14Contact2_ActivityCount);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot contact access in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot contact access in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot remove contact access in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot remove contact access in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc028_closeFundRaisingWorkSpaceAndCheckImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTc="M14tc015_onlineImportDocInFRW";
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileStandard);
		String[] SplitedStdFilesName=stdfilesName.split("<break>");
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileCommon);
		String [] SplitedCommonFilesName=commonfilesName.split("<break>");
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileShared);
		String [] SplitedSharedFileName=shrdfilesName.split("<break>");	
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.FundraisingWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
			}else {
				appLog.error("Not able to click on created fund: "+M14FundName1+" so cannot read and write ip analytics count");
				sa.assertTrue(false, "Not able to click on created fund: "+M14FundName1+" so cannot read and write ip analytics count");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot read and write ip analytics count");
			sa.assertTrue(false, "Not able to click on fund tab so cannot read and write ip analytics count");
		}
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(fp.closeWorkSpace(Workspace.FundraisingWorkspace, 60)) {
					appLog.info("Fundraising Workspace is closed");
					FRW_UniqueDocument=FRW_UniqueDocument+2;
					FRW_DocumentViews=FRW_DocumentViews+2;
					FRW_DocumentDownload=FRW_DocumentDownload+2;
					FRW_DocumentNotViewedOrDownload=FRW_DocumentNotViewedOrDownload+1;
					FRW_ContactGrantedAccess=FRW_ContactGrantedAccess-2;
					ExcelUtils.writeData(CommonVariables.FRW_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.FRW_Value);
					ExcelUtils.writeData(CommonVariables.FRW_DocumentDownload, "IPAnalytics", excelLabel.Statistics, "No of Document Downloads", excelLabel.FRW_Value);
					ExcelUtils.writeData(CommonVariables.FRW_DocumentViews, "IPAnalytics", excelLabel.Statistics, "No of Document Views", excelLabel.FRW_Value);
					ExcelUtils.writeData(CommonVariables.FRW_DocumentNotViewedOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.FRW_Value);
					ExcelUtils.writeData(CommonVariables.FRW_ContactGrantedAccess, "IPAnalytics", excelLabel.Statistics, "No of Contacts Granted Access", excelLabel.FRW_Value);
				}else {
					appLog.error("Fundraising Workspace is not closed");
					sa.assertTrue(false, "Fundraising Workspace is not closed");
				}
			}else {
				appLog.error("Not able to click on created fund: "+M14FundName1+" so cannot close fundraising workspace");
				sa.assertTrue(false, "Not able to click on created fund: "+M14FundName1+" so cannot close fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot close fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot close fundraising workspace");
		}	
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(FRW_UniqueDocument, FRW_DocumentViews,
							FRW_DocumentDownload, FRW_DocumentNotViewedOrDownload, FRW_ContactGrantedAccess,FRW_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.FundraisingWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(SplitedStdFilesName[3]+"<break>"+SplitedCommonFilesName[3]+"<break>"+SplitedSharedFileName[3], false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(null,null,null);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created fund: "+M14FundName1+" so cannot check ip analytics count after close FRW");
				sa.assertTrue(false, "Not able to click on created fund: "+M14FundName1+" so cannot check ip analytics count after close FRW");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot check ip analytics count after close FRW");
			sa.assertTrue(false, "Not able to click on fund tab so cannot check ip analytics count after close FRW");
		}	
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M14tc029_againInviteContactAccessAndCheckImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTc="M14tc015_onlineImportDocInFRW";
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileStandard);
		String[] SplitedStdFilesName=stdfilesName.split("<break>");
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileCommon);
		String [] SplitedCommonFilesName=commonfilesName.split("<break>");
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileShared);
		String [] SplitedSharedFileName=shrdfilesName.split("<break>");	
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.FundraisingWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
				if(fp.inviteContact(environment, mode, M14Institution1, M14Contact1EmailId, null, FolderType.Standard,"upload","Yes", null,null, Workspace.FundraisingWorkspace, null)) {
					appLog.info("invite contact successfully: "+M14Contact1FirstName+" "+M14Contact1LastName);
					FRW_UniqueDocument=FRW_UniqueDocument-2;
					FRW_DocumentViews=FRW_DocumentViews-2;
					FRW_DocumentDownload=FRW_DocumentDownload-2;
					FRW_DocumentNotViewedOrDownload=FRW_DocumentNotViewedOrDownload-1;
					FRW_ContactGrantedAccess=FRW_ContactGrantedAccess+1;
					ExcelUtils.writeData(CommonVariables.FRW_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.FRW_Value);
					ExcelUtils.writeData(CommonVariables.FRW_DocumentDownload, "IPAnalytics", excelLabel.Statistics, "No of Document Downloads", excelLabel.FRW_Value);
					ExcelUtils.writeData(CommonVariables.FRW_DocumentViews, "IPAnalytics", excelLabel.Statistics, "No of Document Views", excelLabel.FRW_Value);
					ExcelUtils.writeData(CommonVariables.FRW_DocumentNotViewedOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.FRW_Value);
					ExcelUtils.writeData(CommonVariables.FRW_ContactGrantedAccess, "IPAnalytics", excelLabel.Statistics, "No of Contacts Granted Access", excelLabel.FRW_Value);
				}else {
					appLog.error("Not able to invite contact: "+M14Contact1FirstName+" "+M14Contact1LastName);
					sa.assertTrue(false, "Not able to invite contact: "+M14Contact1FirstName+" "+M14Contact1LastName);
				}
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(FRW_UniqueDocument, FRW_DocumentViews,
							FRW_DocumentDownload, FRW_DocumentNotViewedOrDownload, FRW_ContactGrantedAccess,FRW_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.FundraisingWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(SplitedStdFilesName[3]+"<break>"+SplitedCommonFilesName[3]+"<break>"+SplitedSharedFileName[3], false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName, updatedInsName, M14Contact1_ActivityCount);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot invite contact access in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot invite contact access in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact access in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact access in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc030_downloadReportFromExportTabInIPAnalytics() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					System.err.println("Parent wind ID: "+parentid);
					String childWindowID = null;
					String childChildWindow = null;
					Set<String> lst = driver.getWindowHandles();
					Iterator<String> I1 = lst.iterator();
					while (I1.hasNext()) {
						String windowID = I1.next();
						if (windowID.equalsIgnoreCase(parentid)) {
							appLog.info("Parent Id is Matched");
						} else {
							childWindowID = windowID;
							appLog.info("Storged child Window Id");
							break;
						}
					}
					List<WebElement> tabs = fp.getIPAnalyticsListofTab();
					String tab1 = tabs.get(1).getText().trim();
					if (click(driver, tabs.get(1), "DR Analytics Tab Link " + tab1, action.BOOLEAN)) {
						List<WebElement> exportLinks = fp.getIPAnalyticsListOfExportText();
						if (click(driver, exportLinks.get(0), "Workspace Documents Report",
								action.BOOLEAN)) {
							appLog.info("Clicked on Workspace Documents Report");
							ThreadSleep(10000);
							appLog.info("Workspace Documents Report File is downloaded");
							System.err.println("URL Of small window: "+getURL(driver, 2)+" "+driver.getWindowHandle());
							Robot rob;
							try {
								rob = new Robot();
								ThreadSleep(10000);
								rob.keyPress(KeyEvent.VK_ENTER);
								rob.keyRelease(KeyEvent.VK_ENTER);
								ThreadSleep(10000);
								rob.keyPress(KeyEvent.VK_CONTROL);
								rob.keyPress(KeyEvent.VK_J);
								rob.keyRelease(KeyEvent.VK_J);
								rob.keyRelease(KeyEvent.VK_CONTROL);
							} catch (AWTException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							// ele.sendKeys(Keys.CONTROL+"j");
							ThreadSleep(10000);
							String abc = driver.getWindowHandle();
							System.err.println("URL Of small window: "+getURL(driver, 2)+" "+abc);
							Set<String> lst1 = driver.getWindowHandles();
							Iterator<String> I2 = lst1.iterator();
							while (I2.hasNext()) {
								String windowID = I2.next();
								if (windowID.equalsIgnoreCase(parentid) || windowID.equalsIgnoreCase(childWindowID)) {
									appLog.info("Parent Id or child Id is Matched");
								} else {
									childChildWindow = windowID;
									appLog.info("Storged  child child Window Id");
									driver.switchTo().window(childChildWindow);
									System.err.println("URL Of Download window: "+getURL(driver, 2)+" "+driver.getWindowHandle());
									break;
								}
							}
							System.out.println(getURL(driver, 30));
							if (childChildWindow == null) {
								try {
									rob = new Robot();
									rob.keyPress(KeyEvent.VK_TAB);
									rob.keyRelease(KeyEvent.VK_TAB);
									rob.keyPress(KeyEvent.VK_ENTER);
									rob.keyRelease(KeyEvent.VK_ENTER);
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_CONTROL);
									rob.keyPress(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_CONTROL);
									parentid = switchOnWindow(driver);
								} catch (AWTException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							ThreadSleep(10000);
							String fileName = fp.getDownloadedFileName();
							if (fileName.equalsIgnoreCase("Fundraising_Documents_" + M14FundName1 + "_"
									+ getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv")) {
								appLog.info("file name is successfully matched. " + "Fundraising_Documents_" + M14FundName1 + "_"
										+ getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv");
							} else {
								appLog.info("File name is not the matched. Expected: " + "Fundraising_Documents_" + M14FundName1
										+ "_" + getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv"
										+ " Actual: " + fileName);
								sa.assertTrue(false,
										"File name is not the matched. Expected: " + "Fundraising_Documents_" + M14FundName1
												+ "_" + getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv"
												+ " Actual: " + fileName);
							}
							driver.close();
							driver.switchTo().window(childWindowID);

						} else {
							appLog.error("Not able to click on Workspace Documents Report Link");
							sa.assertTrue(false, "Not able to click on Workspace Documents Report Link");
						}
						exportLinks.clear();
						exportLinks = fp.getIPAnalyticsListOfExportText();
						if (click(driver, exportLinks.get(1), "Contact Permissions Report ", action.BOOLEAN)) {
							appLog.info("Clicked on Contact Permission Export Link");
							ThreadSleep(10000);
							appLog.info("Contact Permissions Report File downloaded");
							Robot rob;
							try {
								rob = new Robot();
								ThreadSleep(10000);
								rob.keyPress(KeyEvent.VK_ENTER);
								rob.keyRelease(KeyEvent.VK_ENTER);
								ThreadSleep(10000);
								rob.keyPress(KeyEvent.VK_CONTROL);
								rob.keyPress(KeyEvent.VK_J);
								rob.keyRelease(KeyEvent.VK_J);
								rob.keyRelease(KeyEvent.VK_CONTROL);
							} catch (AWTException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							// ele.sendKeys(Keys.CONTROL+"j");
							ThreadSleep(10000);
							Set<String> lst1 = driver.getWindowHandles();
							Iterator<String> I2 = lst1.iterator();
							while (I2.hasNext()) {
								String windowID = I2.next();
								if (windowID.equalsIgnoreCase(parentid) || windowID.equalsIgnoreCase(childWindowID)) {
									appLog.info("Parent Id or child Id is Matched");
								} else {
									childChildWindow = windowID;
									appLog.info("Storged  child child Window Id");
									driver.switchTo().window(childChildWindow);
									break;
								}
							}
							if (childChildWindow == null) {
								try {
									rob = new Robot();
									rob.keyPress(KeyEvent.VK_TAB);
									rob.keyRelease(KeyEvent.VK_TAB);
									rob.keyPress(KeyEvent.VK_ENTER);
									rob.keyRelease(KeyEvent.VK_ENTER);
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_CONTROL);
									rob.keyPress(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_CONTROL);
									parentid = switchOnWindow(driver);
								} catch (AWTException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							ThreadSleep(10000);
							String fileName = fp.getDownloadedFileName();
							if (fileName.equalsIgnoreCase("Fundraising_Contact_Permissions_" + M14FundName1 + "_"
									+ getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv")) {
								appLog.info("file name is successfully matched. " + "Fundraising_Contact_Permissions_" + M14FundName1 + "_"
										+ getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv");
							} else {
								appLog.info("File name is not the matched. Expected: " + "Fundraising_Contact_Permissions_"
										+ M14FundName1 + "_" + getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv"
										+ " Actual: " + fileName);
								sa.assertTrue(false,
										"File name is not the matched. Expected: " + "Fundraising_Contact_Permissions_"
												+ M14FundName1 + "_" + getDateAccToTimeZone("America/New_York", "MMddyyyy")
												+ ".csv" + " Actual: " + fileName);
							}
							driver.close();
							driver.switchTo().window(childWindowID);

						} else {
							appLog.error("Not able to click on Contact Permission Export Link");
							sa.assertTrue(false, "Not able to click on Contact Permission Export Link");
						}
					} else {
						appLog.error("Not able to click on DR Analytics Exports tab");
						sa.assertTrue(false, "Not able to click on DR Analytics Exports tab");
					}
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot download Workspace Documents and Contact Permission Report");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot download Workspace Documents and Contact Permission Report");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot download Workspace Documents and Contact Permission Report");
			sa.assertTrue(false, "Not able to click on fund tab so cannot download Workspace Documents and Contact Permission Report");
		}
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M14tc031_clearFundraisingWorkspaceAndBuildAgain() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String Size=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M14Fund1", excelLabel.Fund_Size);
		String vintageyear=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M14Fund1", excelLabel.Fund_VintageYear);
		String contact=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M14Fund1", excelLabel.Fund_Contact);
		String phone=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M14Fund1", excelLabel.Fund_Phone);
		String email=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M14Fund1", excelLabel.Fund_Email);
		String description=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M14Fund1", excelLabel.Fund_Description);
		String templateName=ExcelUtils.readData("Foldertemp", 0, 1);
		String[] data= {Size,vintageyear,contact,phone,email,description};
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.FundraisingWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
			}else {
				appLog.error("Not able to click on created fund: "+M14FundName1+" so cannot read and write ip analytics count");
				sa.assertTrue(false, "Not able to click on created fund: "+M14FundName1+" so cannot read and write ip analytics count");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot read and write ip analytics count");
			sa.assertTrue(false, "Not able to click on fund tab so cannot read and write ip analytics count");
		}
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(fp.clearWorkSpace(Workspace.FundraisingWorkspace, 60)) {
					appLog.info("Fundraising Workspace is cleared");
					FRW_UniqueDocument=0;
					FRW_DocumentViews=0;
					FRW_DocumentNotViewedOrDownload=0;
					FRW_ContactGrantedAccess=0;
					FRW_ContactNotViewedAnyDocument=0;
					FRW_DocumentDownload=0;
					ExcelUtils.writeData(CommonVariables.FRW_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.FRW_Value);
					ExcelUtils.writeData(CommonVariables.FRW_DocumentViews, "IPAnalytics", excelLabel.Statistics, "No of Document Views", excelLabel.FRW_Value);
					ExcelUtils.writeData(CommonVariables.FRW_DocumentDownload, "IPAnalytics", excelLabel.Statistics, "No of Document Downloads", excelLabel.FRW_Value);
					ExcelUtils.writeData(CommonVariables.FRW_DocumentNotViewedOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.FRW_Value);
					ExcelUtils.writeData(CommonVariables.FRW_ContactGrantedAccess, "IPAnalytics", excelLabel.Statistics, "No of Contacts Granted Access", excelLabel.FRW_Value);
					ExcelUtils.writeData(CommonVariables.FRW_ContactNotViewedAnyDocument, "IPAnalytics", excelLabel.Statistics, "No of Contacts who have not Viewed any Document", excelLabel.FRW_Value);
					ExcelUtils.writeData("No Access", "Contacts", excelLabel.Variable_Name, "M14Contact1", excelLabel.Contact_Access);
					ExcelUtils.writeData("No Access", "Contacts", excelLabel.Variable_Name, "M14Contact2", excelLabel.Contact_Access);
					ExcelUtils.writeData("No Access", "Contacts", excelLabel.Variable_Name, "M14Contact3", excelLabel.Contact_Access);
					ExcelUtils.writeData("0", "Contacts", excelLabel.Variable_Name, "M14Contact1", excelLabel.Activity_Count);
					ExcelUtils.writeData("0", "Contacts", excelLabel.Variable_Name, "M14Contact2", excelLabel.Activity_Count);
					ExcelUtils.writeData("0", "Contacts", excelLabel.Variable_Name, "M14Contact3", excelLabel.Activity_Count);
					ExcelUtils.writeData("No", "Contacts", excelLabel.Variable_Name, "M14Contact1", excelLabel.Viewed_Or_DownloadedAnyFile);
					ExcelUtils.writeData("No", "Contacts", excelLabel.Variable_Name, "M14Contact2", excelLabel.Viewed_Or_DownloadedAnyFile);
					ExcelUtils.writeData("", "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.FRW_DocumentsName);
				}else {
					appLog.error("Fundraising Workspace is not cleared");
					sa.assertTrue(false, "Fundraising Workspace is not cleared");
				}
			}else {
				appLog.error("Not able to click on created fund: "+M14FundName1+" so cannot clear fundraising workspace");
				sa.assertTrue(false, "Not able to click on created fund: "+M14FundName1+" so cannot clear fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot clear fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot clear fundraising workspace");
		}	
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				if(fp.buildWorkspace(data,WorkSpaceAction.IMPORTFOLDERTEMPLATE,templateName,null,M14Institution1+"<break>"+M14Institution2, Workspace.FundraisingWorkspace,60)) {
					appLog.info("FundRaising work is build successfully on fund : "+M14FundName1);
				}else {
					appLog.error("Not able to bulid fundraising workspace on fund: "+M14FundName1);
					sa.assertTrue(false, "Not able to bulid fundraising workspace on fund: "+M14FundName1);
				}
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(FRW_UniqueDocument, FRW_DocumentViews,
							FRW_DocumentDownload, FRW_DocumentNotViewedOrDownload, FRW_ContactGrantedAccess,FRW_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics(null,null,null);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(null, false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(null,null,null);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created fund: "+M14FundName1+" so cannot check ip analytics count after clear FRW");
				sa.assertTrue(false, "Not able to click on created fund: "+M14FundName1+" so cannot check ip analytics count after clear FRW");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot check ip analytics count after clear FRW");
			sa.assertTrue(false, "Not able to click on fund tab so cannot check ip analytics count after clear FRW");
		}	
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc032_inviteContactInFRW() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				if(fp.inviteContact(M14Institution1,M14Contact1EmailId,null, FolderType.Standard,"Upload", "Yes",null,"All Folders", Workspace.FundraisingWorkspace,null,"Yes",true)) {
					appLog.info("contact is invites successfully from fundraising workspace: "+M14Contact1FirstName+" "+M14Contact1LastName+" from "+M14Institution1);
				}else {
					appLog.error("Not able to invite contact from fundraising workspace: "+M14Contact1FirstName+" "+M14Contact1LastName+" from "+M14Institution1);
					sa.assertTrue(false, "Not able to invite contact from fundraising workspace: "+M14Contact1FirstName+" "+M14Contact1LastName+" from "+M14Institution1);
				}
				switchToFrame(driver, 30,bp.getFrame(PageName.FundsPage, 30));
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.FundraisingWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(FRW_UniqueDocument, FRW_DocumentViews,
							FRW_DocumentDownload, FRW_DocumentNotViewedOrDownload, FRW_ContactGrantedAccess,FRW_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					SoftAssert activity= fp.verifyMostActiveContactInIPAnalytics(null, null, null);
					sa.combineAssertions(activity);
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics(null, null,Workspace.FundraisingWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
				
			}else {
				appLog.error("Not able to click on created fund Name so cannot build fundraising and investor workspace: "+M14FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot build fundraising and investor workspace: "+M14FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot build fundraising workspace and investor: "+M14FundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot build fundraising workspace and investor: "+M14FundName1);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc033_deactivateManageApprovalsSettings() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName,adminPassword);
		if(nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, nim.getNIMTabFrame(30));
			if(nim.clickOnSideMenusTab(sideMenu.ManageApprovals)) {
				if(nim.deactivateManageApprovalsSetting()) {
					appLog.info("manage approvals settings is deactivated");
				}else {
					appLog.error("Manage aprrovals settings is not deactivated");
					sa.assertTrue(false, "Manage aprrovals settings is not deactivated");
				}
			}else {
				appLog.error("Not able to click on manage approvals tab so cannot deactivate manage approvals settings ");
				sa.assertTrue(false, "Not able to click on manage approvals tab so cannot deactivate manage approvals settings ");
			}
		}else {
			appLog.error("Not able to click on NIM Tab so cannot deactivate manage approvals settings");
			sa.assertTrue(false, "Not able to click on NIM Tab so cannot deactivate manage approvals settings");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	//
	
	@Test
	public void M14tc034_1_buildINVWorkSpace() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				String Size=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M14Fund1", excelLabel.Fund_Size);
				String vintageyear=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M14Fund1", excelLabel.Fund_VintageYear);
				String contact=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M14Fund1", excelLabel.Fund_Contact);
				String phone=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M14Fund1", excelLabel.Fund_Phone);
				String email=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M14Fund1", excelLabel.Fund_Email);
				String description=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M14Fund1", excelLabel.Fund_Description);
				String templateName=ExcelUtils.readData("Foldertemp", 0, 1);
				String[] data= {Size,vintageyear,contact,phone,email,description};
				if(fp.buildWorkspace(data,WorkSpaceAction.IMPORTFOLDERTEMPLATE,templateName,null,M14Institution1+"/"+M14LimitedPartner1+"<break>"+M14Institution2+"/"+M14LimitedPartner2, Workspace.InvestorWorkspace,60)) {
					appLog.info("Investor workspace is build successfully on fund : "+M14FundName1);
				}else {
					appLog.error("Not able to bulid Investor workspace on fund: "+M14FundName1);
					sa.assertTrue(false, "Not able to bulid Investor workspace on fund: "+M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created fund Name so cannot build Investor workspace: "+M14FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot build Investor workspace: "+M14FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot build Investor workspace: "+M14FundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot build Investor workspace: "+M14FundName1);
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc034_2_validateDealRoomAnalyticsUIInINV() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		List<String> ListofActualResult = new ArrayList<String>();
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 60,fp.getFrame(PageName.FundsPage,30));
				scrollDownThroughWebelement(driver,fp.getWorkspaceSectionView(Workspace.InvestorWorkspace,30), "workspace Section");
		if (click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "Deal Room Analytics Icon", action.SCROLLANDBOOLEAN)) {
			String parentid = switchOnWindow(driver);
			if (fp.getIPAnalyticsHeaderText(60) != null) {
				sa.assertTrue(fp.getIPAnalyticsHeaderText(30).getText().trim().contains("Navatar Investor Analytics"),
						"Navatar Investor Analytics header Text is not verified. Expected: Navatar Investor Analytics");
			} else {
				appLog.info("IP Analytics header text is not visible");
				sa.assertTrue(false, "IP Analytics header text is not visible");
			}
			List<WebElement> tabs = fp.getIPAnalyticsListofTab();
			String expectedTabs = "Statistics<break>Exports";
			if (!tabs.isEmpty()) {
				for (int i = 0; i < tabs.size(); i++) {
					ListofActualResult.add(tabs.get(i).getText().trim());
				}
				if (!compareMultipleListWithoutAssertion(expectedTabs, ListofActualResult)) {
					appLog.info("IP Analytics Tabs text is not verified.");
					sa.assertTrue(false, "IP Analytics Tabs text is not verified.");
				} else {
					appLog.info("IP Analytics Tabs text is verified.");
				}

				if (isDisplayed(driver, tabs.get(0), "visibility", 10, "IP Statistics Tab text") != null) {
					appLog.info("IP Statistics Tab is visibile and it's selected by Deafult.");
				} else {
					appLog.info("IP Statistics Tab is not visibile");
					appLog.info("IP Statistics Tab is not selected by Deafult.");
					sa.assertTrue(false, "IP Statistics Tab is not selected by Deafult.");
				}
				if (fp.getIPAnalyticsExportIconOnExportTab(10) != null) {
					appLog.info("IP Analytics Export Tab is selected by Deafult.");
					sa.assertTrue(false, "IP Analytics Export Tab is selected by Deafult.");
				} else {
					appLog.info("IP Analytics Export Tab visibile and it's not selected by default.");
				}

			} else {
				appLog.info("IP Analytics Tabs List is not available");
				sa.assertTrue(false, "IP Analytics Tabs List is not available");
			}
			if (fp.getIPAnalyticsCloseBtn(10) != null) {
				appLog.info("Close Button is displaying on IP Analytics Pop Up");
			} else {
				appLog.info("Close Button is not displaying on IP Analytics Pop Up");
				sa.assertTrue(false, "Close Button is not displaying on IP Analytics Pop Up");
			}

			String targetStatisticsText = "Investor Statistics<break># of Unique Documents<break># of Document Views<break># of Document Downloads<break># of Documents not Viewed or Downloaded<break># of Contacts Granted Access<break>"
					+ "# of Contacts who have not Viewed any Document<break>Most Viewed Documents (Top 5)<break>Most Active Contacts (Top 5)";
			List<WebElement> ListofData = fp.getIPAnalyticsListofTargetStatisticsText();
			ListofActualResult.clear();
			if (!ListofData.isEmpty()) {
				for (int i = 0; i < ListofData.size(); i++) {
					ListofActualResult.add(ListofData.get(i).getText().trim());
				}
				if (!compareMultipleListWithoutAssertion(targetStatisticsText, ListofActualResult)) {
					appLog.info("Investor Statistics Text is not verified");
					sa.assertTrue(false, "Investor Statistics Text is not verified");
				} else {
					appLog.info("Investor Statistics all Text is verified");
				}
			} else {
				appLog.info("Investor Statistics Text List is not available.");
				sa.assertTrue(false, "Investor Statistics Text List is not available.");
			}
			ListofData.clear();
			ListofData = fp.getIPAnalyticsListofValuesTetx();
			if (!ListofData.isEmpty()) {
				for (int j = 0; j < ListofData.size(); j++) {
					try {
						int result = Integer.parseInt(ListofData.get(j).getText().trim());
						if (result == 0) {
							appLog.info("IP Statistics value is displaying 0.");
						} else {
							appLog.info("IP Statistics value is not displaying 0.");
						}
					} catch (Exception e) {
						if (ListofData.get(j).getText().trim().contains("Value")) {
							appLog.info("IP Statistics Target Value text is verified.");
						} else if (ListofData.get(j).getText().trim().contains("View")) {
							appLog.info("IP Statistics Target View text is verified.");
						} else {
							appLog.info("IP Statistics Target Value or View text is verified.");
							sa.assertTrue(false, "IP Statistics Target Value or View text is verified.");
						}
					}
				}

			} else {
				appLog.info("IP Analytics value and Text is not verifed.");
				sa.assertTrue(false, "IP Analytics value and Text is not verifed.");
			}
			WebElement msg = isDisplayed(driver,
					FindElement(driver,
							"//div[@class='IPAnalytics_Statistics']//table//tr/td[contains(text(),'# of Documents not Viewed or Downloaded')]/../td[2]/a",
							"IP Analytics Document not viewed or downloaded Value Link", action.SCROLLANDBOOLEAN,
							30),
					"visibility", 20, "");
			if (msg != null) {
				appLog.info("# of Documents not Viewed or Downloaded Value is displaying as Link");
			} else {
				appLog.info("# of Documents not Viewed or Downloaded Value is not displaying as Link");
				sa.assertTrue(false, "# of Documents not Viewed or Downloaded Value is not displaying as Link");
			}

			msg = isDisplayed(driver,
					FindElement(driver,
							"//div[@class='IPAnalytics_Statistics']//table//tr/td[contains(text(),'# of Contacts who have not Viewed any Document')]/../td[2]/a",
							"# of Contacts who have not Viewed any Document Value Link", action.SCROLLANDBOOLEAN, 30),
					"visibility", 20, "");
			if (msg != null) {
				appLog.info("# of Contacts who have not Viewed any Document Value is displaying as Link");
			} else {
				appLog.info("# of Contacts who have not Viewed any Document Value is not displaying as Link");
				sa.assertTrue(false, "# of Contacts who have not Viewed any Document Value is not displaying as Link");
			}
			if (fp.getIPAnalyticsMostActiveContactsLink(20) != null) {
				appLog.info("Most Active Contacts (Top 5) Value is displaying as Link");
			} else {
				appLog.info("Most Active Contacts (Top 5) Value is not displaying as Link");
				sa.assertTrue(false, "Most Active Contacts (Top 5) Value is not displaying as Link");
			}

			if (fp.getIPAnalyticsMostViewedDocumentLink(10) != null) {
				appLog.info("Most Viewed Documents (Top 5) Value is displaying as Link");
			} else {
				appLog.info("Most Viewed Documents (Top 5) Value is not displaying as Link");
				sa.assertTrue(false, "Most Viewed Documents (Top 5) Value is not displaying as Link");
			}

			if (click(driver, fp.getIPAnalyticsExportTab(30), "IP Analytics Export Tab",
					action.SCROLLANDBOOLEAN)) {
				ListofData.clear();
				ListofActualResult.clear();
				String result = "Fund Workspace Reports<break>Workspace Documents Report<break>Contact Permissions Report";
				ListofData = fp.getIPAnalyticsExportListofDealRoomReport();
				if (!ListofData.isEmpty()) {
					for (int i = 0; i < ListofData.size(); i++) {
						ListofActualResult.add(ListofData.get(i).getText().trim());
					}
					if (compareMultipleListWithoutAssertion(result, ListofActualResult)) {
						appLog.info("IP Analytics Export tab IP Reports Tab text label is verified.");
					} else {
						appLog.info(
								"IP Analytics Export tab Export Reports Tab text label is not verified. Expected: "
										+ result);
						sa.assertTrue(false,
								"IP Analytics Export tab Export Reports Tab text label is not verified. Expecetd: "
										+ result);
					}
				} else {
					appLog.info("IP Reports label text list is not visible on IP Analytics Export Tab");
					sa.assertTrue(false,
							"IP Reports label text list is not visible on IP Analytics Export tab");
				}
				ListofData.clear();
				ListofActualResult.clear();
				ListofData = fp.getIPAnalyticsListOfExportText();
				if (!ListofData.isEmpty()) {
					for (int i = 0; i < ListofData.size(); i++) {
						if (ListofData.get(i).getText().trim().contains("Export")) {
							appLog.info("Export Link is available");
						} else {
							if (i == ListofData.size() - 1) {
								appLog.info("Export link is not available on IP Analytics on Export tab");
								sa.assertTrue(false,
										"Export link is not available on IP Analytics on Export tab");
							}
						}
					}
				} else {
					appLog.info("Export Link is not visible on IP Analytics Export Tab");
					sa.assertTrue(false, "Export Link is not visible on IP Analytics Export Tab");
				}
				if (fp.getIPAnalyticsExportIconOnExportTab(10) != null) {
					appLog.info("Export Img is available on IP Analytics Export Tab");
				} else {
					appLog.info("Export Img is not available on IP Analytics Export Tab");
					sa.assertTrue(false, "Export Img is not available on IP Analytics Export Tab");
				}

			} else {
				appLog.info("Not able to click on Export Tab");
				sa.assertTrue(false, "Not able to click on Export Tab");
			}
			driver.close();
			driver.switchTo().window(parentid);

		} else {
			appLog.info("Not able to Click on IP Analytics Icon on fund: " + M14FundName1);
			sa.assertTrue(false, "Not able to Click on IP Analytics Icon on fund: " + M14FundName1);
		}
	}else {
		appLog.error("Not able to click on created fund Name so cannot check IP Analytics UI in InvestorWorkspace");
		sa.assertTrue(false, "Not able to click on created fund Name so cannot check IP Analytics UI in InvestorWorkspace");
	}
}else {
	appLog.error("Not able to click on fund tab so cannot check IP Analytics UI in InvestorWorkspace");
	sa.assertTrue(false, "Not able to click on fund tab so cannot check IP Analytics UI in InvestorWorkspace");
}
		
	lp.CRMlogout();
	sa.assertAll();
	}
	
	@Test
	public void M14tc034_3_verifyMostViewedAndMostActiveLink() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		List<String> ListofActualResult = new ArrayList<String>();
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 60,fp.getFrame(PageName.FundsPage,30));
				scrollDownThroughWebelement(driver,fp.getWorkspaceSectionView(Workspace.InvestorWorkspace,30), "workspace Section");
				if (click(driver, fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					if (click(driver, fp.getIPAnalyticsMostViewedDocumentLink(30), "Most View Document Link",
							action.SCROLLANDBOOLEAN)) {
						ListofActualResult.clear();
						List<WebElement> headertextlist = fp.getMostViewedDocumentHeaderTextList();
						String expectedResult = "Document Name<break>Views";
						if (!headertextlist.isEmpty()) {
							for (int i = 0; i < headertextlist.size(); i++) {
								ListofActualResult.add(headertextlist.get(i).getText().trim());
							}
							if (compareMultipleListWithoutAssertion(expectedResult, ListofActualResult)) {
								appLog.info("Most Viewed Document header text is verified");
							} else {
								appLog.info("Most Viewed Document header text is not verified");
								sa.assertTrue(false, "Most Viewed Document header text is not verified");
							}
						} else {
							appLog.info("Most Viewed Document header text list is not available");
							sa.assertTrue(false, "Most Viewed Document header text list is not available");
						}
						if (fp.getMostViewedDocumentErrorMsg(10) != null) {
							sa.assertTrue(
									fp.getMostViewedDocumentErrorMsg(10).getText().trim()
									.contains(FundsPageErrorMessage.MostViewedDocErrorMsg),
									"No data to display error message is not verified. Expected: "
											+ FundsPageErrorMessage.MostViewedDocErrorMsg);
						} else {
							appLog.info("No data to display error message is not visible on Most Viewed Document pop up");
							sa.assertTrue(false,
									"No data to display error message is not visible on Most Viewed Document pop up");
						}

						if (fp.getMostviewedDocumentViewSortingIcon(10) != null) {
							appLog.info("Sorting Icon is visible on Views Text");
						} else {
							appLog.info("Sorting Icon is not visible on Views Text");
							sa.assertTrue(false, "Sorting Icon is not visible on Views Text");
						}
						if (fp.getMostviewedDocumentCloseBtn(10) != null) {
							appLog.info("Close button is visible on Most Viewed Document pop up");
						} else {
							appLog.info("Close button is not visible on Most Viewed Document pop up");
							sa.assertTrue(false, "Close button is not visible on Most Viewed Document pop up");
						}
						if (fp.getMostViewedDocumentCloseIcon(10) != null) {
							appLog.info("Close Icon is visible on Most Viewed Document pop up");
						} else {
							appLog.info("Close Icon is not visible on Most Viewed Document pop up");
							sa.assertTrue(false, "Close Icon is not visible on Most Viewed Document pop up");
						}
						if (click(driver, fp.getMostviewedDocumentCloseBtn(10),
								"Document not viewed or download close button", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Most Viewed Document pop up Close button");
						} else {
							appLog.info("Clicked on Most Viewed Document pop up Close button");
							sa.assertTrue(false, "Clicked on CMost Viewed Document pop up Close button");
						}
					} else {
						appLog.info("Not able to click on Most Viewed Document link");
						sa.assertTrue(false, "Not able to click on Most Viewed Document link");
					}
					if (click(driver, fp.getIPAnalyticsMostActiveContactsLink(30), "Most Active Contact Link",
							action.SCROLLANDBOOLEAN)) {
						ListofActualResult.clear();
						List<WebElement> headertextlist = fp.getMostActiveContactHeaderTextList();
						String expectedResult = "Contact Name<break>Firm<break>Activity Count";
						if (!headertextlist.isEmpty()) {
							for (int i = 0; i < headertextlist.size(); i++) {
								ListofActualResult.add(headertextlist.get(i).getText().trim());
							}
							if (compareMultipleListWithoutAssertion(expectedResult, ListofActualResult)) {
								appLog.info("Most Active Contact header text is verified");
							} else {
								appLog.info("Most Active Contact header text is not verified");
								sa.assertTrue(false, "Most Active Contact header text is not verified");
							}
						} else {
							appLog.info("Most Active Contact header text list is not available");
							sa.assertTrue(false, "Most Active Contact header text list is not available");
						}
						if (fp.getMostActiveContactErrorMsg(10) != null) {
							sa.assertTrue(
									fp.getMostActiveContactErrorMsg(10).getText().trim()
									.contains(FundsPageErrorMessage.MostActiveContactErrorMsg),
									"No data to display error message is not verified. Expected: "
											+ FundsPageErrorMessage.MostActiveContactErrorMsg);
						} else {
							appLog.info("No data to display error message is not visible on Most Active Contact pop up");
							sa.assertTrue(false,
									"No data to display error message is not visible on Most Active Contact pop up");
						}
						if (fp.getGetmostviewedContactActivityCountToolTip(10) != null) {
							sa.assertTrue(
									fp.getGetmostviewedContactActivityCountToolTip(10).getAttribute("title").trim()
									.contains(FundsPageErrorMessage.MostActiveContactActivityCountToolTip),
									"Activity Count Tool Tip is not verified. Expected: "
											+ FundsPageErrorMessage.MostActiveContactActivityCountToolTip);
						} else {
							appLog.info("Activity Count tooltip is not visible on most active contact pop up");
							sa.assertTrue(false, "Activity Count tooltip is not visible on most active contact pop up");
						}
						if (fp.getMostActiveContactActivityCountSortingIcon(10) != null) {
							appLog.info("Sorting Icon is visible on Activity Count Text");
						} else {
							appLog.info("Sorting Icon is not visible on Activity Count Text");
							sa.assertTrue(false, "Sorting Icon is not visible on Activity Count Text");
						}
						if (fp.getMostActiveContactCloseBtn(10) != null) {
							appLog.info("Close button is visible on Most Active Contact pop up");
						} else {
							appLog.info("Close button is not visible on Most Active Contact pop up");
							sa.assertTrue(false, "Close button is not visible on Most Active Contact pop up");
						}
						if (fp.getMostActiveContactCloseIcon(10) != null) {
							appLog.info("Close Icon is visible on Most Active Contact pop up");
						} else {
							appLog.info("Close Icon is not visible on Most Active Contact pop up");
							sa.assertTrue(false, "Close Icon is not visible on Most Active Contact pop up");
						}
						if (click(driver, fp.getMostActiveContactCloseBtn(10), "Document not viewed or download close button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Most Active Contact pop up Close button");
						} else {
							appLog.info("Clicked on Most Active Contact pop up Close button");
							sa.assertTrue(false, "Clicked on Most Active Contact pop up Close button");
						}
					} else {
						appLog.info("Not able to click on Most Active Contact link");
						sa.assertTrue(false, "Not able to click on Most Active Contact link");
					}
					if(click(driver, fp.getIPAnalyticsDocumentNotViwedorDownloadLink(30), "document not viewed or download link", action.BOOLEAN)) {
						ListofActualResult.clear();
						List<WebElement> headertextlist = fp.getDocumentNotViewedOrDownloadHeaderText();
						String expectedResult = "Document Name";
						if (!headertextlist.isEmpty()) {
							for (int i = 0; i < headertextlist.size(); i++) {
								ListofActualResult.add(headertextlist.get(i).getText().trim());
							}
							if (compareMultipleListWithoutAssertion(expectedResult, ListofActualResult)) {
								appLog.info("document not viewed or download header text is verified");
							} else {
								appLog.info("document not viewed or download text is not verified");
								sa.assertTrue(false, "document not viewed or download text is not verified");
							}
						} else {
							appLog.info("document not viewed or download text list is not available");
							sa.assertTrue(false, "document not viewed or download text list is not available");
						}
						if (fp.getDocumentNotViewedOrDownloadedErrorMsg(10) != null) {
							sa.assertTrue(
									fp.getDocumentNotViewedOrDownloadedErrorMsg(10).getText().trim()
									.contains(FundsPageErrorMessage.DocumentNotViewedOrDownload),
									"No data to display error message is not verified. Expected: "
											+ FundsPageErrorMessage.DocumentNotViewedOrDownload);
						} else {
							appLog.info("No data to display error message is not visible on document not viewed or download pop up");
							sa.assertTrue(false,
									"No data to display error message is not visible on document not viewed or download pop up");
						}
						if (fp.getDocumentNotViewedOrDownloadTextMsg(10) != null) {
							sa.assertTrue(
									fp.getDocumentNotViewedOrDownloadTextMsg(10).getText().trim()
									.contains(FundsPageErrorMessage.DocumentNotViewedOrDownloadText),
									"Header Text message is not verified. Expected: "
											+ FundsPageErrorMessage.DocumentNotViewedOrDownloadText);
						} else {
							appLog.info("HeaderText message is not visible on document not viewed or download pop up");
							sa.assertTrue(false,
									"HeaderText message is not visible on document not viewed or download pop up");
						}
						if (fp.getDocumentNotViewedOrDownloadSortingIcon(10)!= null) {
							appLog.info("Sorting Icon is visible on document name text");
						} else {
							appLog.info("Sorting Icon is not visible on document name text");
							sa.assertTrue(false, "Sorting Icon is not visible on document name text");
						}
						if (fp.getDocumentNotViewedOrDownloadedCloseBtn(10) != null) {
							appLog.info("Close button is visible on document not viewed or download pop up");
						} else {
							appLog.info("Close button is not visible on document not viewed or download pop up");
							sa.assertTrue(false, "Close button is not visible on document not viewed or download pop up");
						}
						if (fp.getDocumentNotViewedOrDownloadedCloseIcon(10) != null) {
							appLog.info("Close Icon is visible on document not viewed or download pop up");
						} else {
							appLog.info("Close Icon is not visible on document not viewed or download pop up");
							sa.assertTrue(false, "Close Icon is not visible on document not viewed or download pop up");
						}
						if (click(driver, fp.getDocumentNotViewedOrDownloadedCloseBtn(10), "Document not viewed or download close button",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on document not viewed or download pop up Close button");
						} else {
							appLog.info("Clicked on document not viewed or download pop up Close button");
							sa.assertTrue(false, "Clicked on document not viewed or download pop up Close button");
						}
					}else {
						appLog.error("Not able to click on document not viewed or download link so cannot check document not viewed or download pop up");
						sa.assertTrue(false, "Not able to click on document not viewed or download link so cannot check document not viewed or download pop up");
					}
					if(click(driver, fp.getIPAnalyticsnotViewedAnyDocumentLink(10), "contact not viewed any document", action.BOOLEAN)) {
						ListofActualResult.clear();
						List<WebElement> headertextlist = fp.getContactNotViewedAnyDocumentHeaderTextList();
						String expectedResult = "Contact Name<break>Firm";
						if (!headertextlist.isEmpty()) {
							for (int i = 0; i < headertextlist.size(); i++) {
								ListofActualResult.add(headertextlist.get(i).getText().trim());
							}
							if (compareMultipleListWithoutAssertion(expectedResult, ListofActualResult)) {
								appLog.info("contact who have not viewed any document header text is verified");
							} else {
								appLog.info("contact who have not viewed any document text is not verified");
								sa.assertTrue(false, "contact who have not viewed any document text is not verified");
							}
						} else {
							appLog.info("contact who have not viewed any document text list is not available");
							sa.assertTrue(false, "contact who have not viewed any document text list is not available");
						}
						if (fp.getContactNotViewedAnyDocumentErrorMsg(10) != null) {
							sa.assertTrue(
									fp.getContactNotViewedAnyDocumentErrorMsg(10).getText().trim()
									.contains(FundsPageErrorMessage.DocumentNotViewedOrDownload),
									"No data to display error message is not verified. Expected: "
											+ FundsPageErrorMessage.DocumentNotViewedOrDownload);
						} else {
							appLog.info("No data to display error message is not visible on contact who have not viewed any document pop up");
							sa.assertTrue(false,
									"No data to display error message is not visible on contact who have not viewed any document pop up");
						}
						if (fp.getContactNotViewedAnyDocument(10) != null) {
							sa.assertTrue(
									fp.getContactNotViewedAnyDocument(10).getText().trim()
									.contains(FundsPageErrorMessage.ContactNotViewedAnyDocumentHeaderText),
									"Header Text message is not verified. Expected: "
											+ FundsPageErrorMessage.ContactNotViewedAnyDocumentHeaderText);
						} else {
							appLog.info("HeaderText message is not visible on contact who have not viewed any document pop up");
							sa.assertTrue(false,
									"HeaderText message is not visible on contact who have not viewed any document pop up");
						}
						if (fp.getContactNotViewedAnyDocumentSortingIcon()!= null) {
							appLog.info("Sorting Icon is visible on Contact name text");
						} else {
							appLog.info("Sorting Icon is not visible on Contact name text");
							sa.assertTrue(false, "Sorting Icon is not visible on Contact name text");
						}
						if (fp.getContactNotViewedAnyDocumentCloseBtn(10) != null) {
							appLog.info("Close button is visible on contact who have not viewed any document pop up");
						} else {
							appLog.info("Close button is not visible on contact who have not viewed any document pop up");
							sa.assertTrue(false, "Close button is not visible on contact who have not viewed any document pop up");
						}
						if (fp.getContactNotViewedAnyDocumentCloseIcon(10) != null) {
							appLog.info("Close Icon is visible on contact who have not viewed any document pop up");
						} else {
							appLog.info("Close Icon is not visible on contact who have not viewed any document pop up");
							sa.assertTrue(false, "Close Icon is not visible on contact who have not viewed any document pop up");
						}
						if (click(driver, fp.getContactNotViewedAnyDocumentCloseBtn(10), "contact not viewed any document",
								action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on contact who have not viewed any document pop up Close button");
						} else {
							appLog.info("Clicked on contact who have not viewed any document pop up Close button");
							sa.assertTrue(false, "Clicked on contact who have not viewed any document pop up Close button");
						}
					}else {
						appLog.error("Contact who have not viewed any document link is not visible so cannot check pop up");
						sa.assertTrue(false, "Contact who have not viewed any document link is not visible so cannot check pop up");
					}
					driver.close();
					driver.switchTo().window(parentid);

				} else {
					appLog.info("Not able to Click on IP Analytics Icon on fund: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on IP Analytics Icon on fund: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created fund "+M14FundName1+" so cannot check Most Active Contact and Most Viewed Document Link");
				sa.assertTrue(false, "Not able to click on created fund "+M14FundName1+" so cannot check Most Active Contact and Most Viewed Document Link");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot check Most Active Contact and Most Viewed Document Link");
			sa.assertTrue(false, "Not able to click on fund tab so cannot check Most Active Contact and Most Viewed Document Link");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc035_inviteContactInINV() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String sharedfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				if(fp.inviteContact(M14Institution1+"/"+M14LimitedPartner1,M14Contact1EmailId,null, FolderType.Standard,"Upload", "Yes",null,"All Folders", Workspace.InvestorWorkspace,null,"Yes",true)) {
					appLog.info("contact is invites successfully from Investor workspace: "+M14Contact1FirstName+" "+M14Contact1LastName+" from "+M14LimitedPartner1);
				}else {
					appLog.error("Not able to invite contact from Investor workspace: "+M14Contact1FirstName+" "+M14Contact1LastName+" from "+M14LimitedPartner1);
					sa.assertTrue(false, "Not able to invite contact from Investor workspace: "+M14Contact1FirstName+" "+M14Contact1LastName+" from "+M14LimitedPartner1);
				}
				if(fp.inviteContact(M14Institution2+"/"+M14LimitedPartner2,M14Contact1EmailId,null, FolderType.Standard,"Upload", "Yes",null,"All Folders", Workspace.InvestorWorkspace,M14Contact1EmailId,"No",false)) {
					appLog.info("contact is invites successfully from Investor workspace: "+M14Contact1FirstName+" "+M14Contact1LastName+" from "+M14LimitedPartner2);
				}else {
					appLog.error("Not able to invite contact from Investor workspace: "+M14Contact1FirstName+" "+M14Contact1LastName+" from "+M14LimitedPartner2);
					sa.assertTrue(false, "Not able to invite contact from Investor workspace: "+M14Contact1FirstName+" "+M14Contact1LastName+" from "+M14LimitedPartner2);
				}
				if(fp.inviteContact(M14Institution2+"/"+M14LimitedPartner2,M14Contact2EmailId,null, FolderType.Standard,"Upload", "Yes",null,"All Folders", Workspace.InvestorWorkspace,M14Contact2EmailId,"Yes",true)) {
					appLog.info("contact is invites successfully from Investor workspace: "+M14Contact2FirstName+" "+M14Contact2LastName+" from "+M14LimitedPartner2);
				}else {
					appLog.error("Not able to invite contact from Investor workspace: "+M14Contact2FirstName+" "+M14Contact2LastName+" from "+M14LimitedPartner2);
					sa.assertTrue(false, "Not able to invite contact from Investor workspace: "+M14Contact2FirstName+" "+M14Contact2LastName+" from "+M14LimitedPartner2);
				}
				if(fp.inviteContact(null,M14Contact1EmailId,sharedfolderpath, FolderType.Shared,"download", "Yes",null,"All Folders", Workspace.InvestorWorkspace,null,"No",false)) {
					appLog.info("contact is invites successfully from Investor workspace: "+M14Contact1FirstName+" "+M14Contact1LastName+" from "+sharedfolderpath);
				}else {
					appLog.error("Not able to invite contact from Investor workspace: "+M14Contact1FirstName+" "+M14Contact1LastName+" from "+sharedfolderpath);
					sa.assertTrue(false, "Not able to invite contact from Investor workspace: "+M14Contact1FirstName+" "+M14Contact1LastName+" from "+sharedfolderpath);
				}
				if(fp.inviteContact(null,M14Contact2EmailId,sharedfolderpath, FolderType.Shared,"download", "Yes",null,"All Folders", Workspace.InvestorWorkspace,null,"No",false)) {
					appLog.info("contact is invites successfully from Investor workspace: "+M14Contact2FirstName+" "+M14Contact2LastName+" from "+sharedfolderpath);
				}else {
					appLog.error("Not able to invite contact from Investor workspace: "+M14Contact2FirstName+" "+M14Contact2LastName+" from "+sharedfolderpath);
					sa.assertTrue(false, "Not able to invite contact from Investor workspace: "+M14Contact2FirstName+" "+M14Contact2LastName+" from "+sharedfolderpath);
				}
				switchToFrame(driver, 30,bp.getFrame(PageName.FundsPage, 30));
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(INV_UniqueDocument, INV_DocumentViews,
							INV_DocumentDownload, INV_DocumentNotViewedOrDownload, INV_ContactGrantedAccess,INV_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					SoftAssert activity= fp.verifyMostActiveContactInIPAnalytics(null, null, null);
					sa.combineAssertions(activity);
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics(null, null,Workspace.InvestorWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
				
			}else {
				appLog.error("Not able to click on created fund Name so cannot invite contact from investor workspace: "+M14FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot invite contact from investor workspace: "+M14FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot cannot invite contact from investor workspace: "+M14FundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot cannot invite contact from investor workspace: "+M14FundName1);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc036_uploadFilesInINVAndCheckIPAnalytics() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String sharedfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath);
		String commonfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath);
		String internalfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.InternalPath);
		String standardfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		String stddocpath="UploadFiles\\Module14\\CRM_Side\\INV\\Standard";
		String shrddocpath="UploadFiles\\Module14\\CRM_Side\\INV\\Shared";
		String commondocpath="UploadFiles\\Module14\\CRM_Side\\INV\\Common";
		String internaldocpath="UploadFiles\\Module14\\CRM_Side\\INV\\Internal";
		String stdfileName=null,commonFileName=null,shrdfileName=null;
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.InvestorWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
				if(fp.uploadFile(standardfolderpath,M14Institution1+"/"+M14LimitedPartner1+"<break>"+M14Institution2+"/"+M14LimitedPartner2, stddocpath,null,UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly in folder: "+standardfolderpath);
					switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
						 stdfileName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileStandard);
						if(stdfileName!=null) {
							List<String>uploadFiles=compareMultipleListOnBasisOfTitle(driver,stdfileName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!uploadFiles.isEmpty()) {
								for(int i=0;i<uploadFiles.size();i++) {
									sa.assertTrue(false, uploadFiles.get(i));
								}
							}else {
								appLog.info("All uploaded files are verified: "+stdfileName);
								CommonVariables.INV_UniqueDocument = uniquedocs.size();
								CommonVariables.INV_DocumentNotViewedOrDownload = uniquedocs.size();
								ExcelUtils.writeData(INV_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.INV_Value);
								ExcelUtils.writeData(INV_DocumentNotViewedOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.INV_Value);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents in standard folder.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents in standard folder");
					}
				}else {
					appLog.error("File is not uploaded in folder "+standardfolderpath);
					sa.assertTrue(false, "File is not uploaded in "+standardfolderpath);
				}
				switchToDefaultContent(driver);
				if(fp.uploadFile(sharedfolderpath,null, shrddocpath,null,UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly in folder: "+sharedfolderpath);
					switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
						shrdfileName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileShared);
						if(shrdfileName!=null) {
							List<String>uploadFiles=compareMultipleListOnBasisOfTitle(driver,shrdfileName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!uploadFiles.isEmpty()) {
								for(int i=0;i<uploadFiles.size();i++) {
									sa.assertTrue(false, uploadFiles.get(i));
								}
							}else {
								appLog.info("All uploaded files are verified: "+shrdfileName);
								CommonVariables.INV_UniqueDocument = uniquedocs.size();
								CommonVariables.INV_DocumentNotViewedOrDownload = uniquedocs.size();
								ExcelUtils.writeData(INV_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.INV_Value);
								ExcelUtils.writeData(INV_DocumentNotViewedOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.INV_Value);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents in shared folder.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents in shared folder");
					}
				}else {
					appLog.error("File is not uploaded in folder "+sharedfolderpath);
					sa.assertTrue(false, "File is not uploaded in "+sharedfolderpath);
				}
				switchToDefaultContent(driver);
				if(fp.uploadFile(commonfolderpath,null, commondocpath,null,UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly in folder: "+commonfolderpath);
					switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
						commonFileName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileCommon);
						if(commonFileName!=null) {
							List<String>uploadFiles=compareMultipleListOnBasisOfTitle(driver,commonFileName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!uploadFiles.isEmpty()) {
								for(int i=0;i<uploadFiles.size();i++) {
									sa.assertTrue(false, uploadFiles.get(i));
								}
							}else {
								appLog.info("All uploaded files are verified: "+commonFileName);
								CommonVariables.INV_UniqueDocument = uniquedocs.size();
								CommonVariables.INV_DocumentNotViewedOrDownload = uniquedocs.size();
								ExcelUtils.writeData(INV_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.INV_Value);
								ExcelUtils.writeData(INV_DocumentNotViewedOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.INV_Value);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents in common folder.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents in common folder");
					}
				}else {
					appLog.error("File is not uploaded in folder "+commonfolderpath);
					sa.assertTrue(false, "File is not uploaded in "+commonfolderpath);
				}
				switchToDefaultContent(driver);
				if(fp.uploadFile(internalfolderpath,null, internaldocpath,null,UploadFileActions.Upload, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly in folder: "+internalfolderpath);
					switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UploadedFileInternal);
						if(filesName!=null) {
							List<String>uploadFiles=compareMultipleListOnBasisOfTitle(driver,filesName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!uploadFiles.isEmpty()) {
								for(int i=0;i<uploadFiles.size();i++) {
									sa.assertTrue(false, uploadFiles.get(i));
								}
							}else {
								appLog.info("All uploaded files are verified: "+filesName);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify upload documents in common folder.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify upload documents in common folder");
					}
				}else {
					appLog.error("File is not uploaded in folder "+internalfolderpath);
					sa.assertTrue(false, "File is not uploaded in "+internalfolderpath);
				}
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(INV_UniqueDocument, INV_DocumentViews,
							INV_DocumentDownload, INV_DocumentNotViewedOrDownload, INV_ContactGrantedAccess,INV_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					SoftAssert documentNotViewed=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(stdfileName+"<break>"+commonFileName+"<break>"+shrdfileName, false);
					sa.combineAssertions(documentNotViewed);
					SoftAssert activity= fp.verifyMostActiveContactInIPAnalytics(null, null, null);
					sa.combineAssertions(activity);
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics(null, null,Workspace.InvestorWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1);
					sa.combineAssertions(rsult);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in Investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in Investor workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in Investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in Investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc037_verifySortingInAnalyticsPopUpsInINV() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view");
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					if(click(driver, fp.getIPAnalyticsDocumentNotViwedorDownloadLink(30), "document not viewed or download link", action.BOOLEAN)) {
						if(click(driver, fp.getDocumentNotViewedOrDownloadSortingIcon(20), "sorting icon", action.BOOLEAN)) {
							if(fp.checkSorting(SortOrder.Decending, fp.getDocumentNotViewedOrDownloadDocumentList())) {
								appLog.info("document is verified in Decending order");
							}else {
								appLog.error("document is not verified in Decending order");
								sa.assertTrue(false, "document is not verified in Decending order");
							}
						}else {
							appLog.error("Not able to click on sorting icon so cannot check Decending Sorting in document not viewed or download");
							sa.assertTrue(false, "Not able to click on sorting icon so cannot check Decending Sorting in document not viewed or download");
						}
						if(click(driver, fp.getDocumentNotViewedOrDownloadSortingIcon(20), "sorting icon", action.BOOLEAN)) {
							if(fp.checkSorting(SortOrder.Assecending, fp.getDocumentNotViewedOrDownloadDocumentList())) {
								appLog.info("document is verified in Ascending order");
							}else {
								appLog.error("document is not verified in Ascending order");
								sa.assertTrue(false, "document is not verified in Ascending order");
							}
						}else {
							appLog.error("Not able to click on sorting icon so cannot check Ascending Sorting in document not viewed or download");
							sa.assertTrue(false, "Not able to click on sorting icon so cannot check Ascending Sorting in document not viewed or download");
						}
						
						if(click(driver, fp.getDocumentNotViewedOrDownloadedCloseBtn(20), "close button", action.BOOLEAN)) {
							appLog.info("click on close button");
						}else {
							appLog.error("Not able to click on close button so cannot close document not viewed or download pop up");
							sa.assertTrue(false, "Not able to click on close button so cannot close document not viewed or download pop up");
						}
					}else {
						appLog.error("Not able to click on document not viewed or download link so cannot check sorting");
						sa.assertTrue(false, "Not able to click on document not viewed or download link so cannot check sorting");
					}
					if(click(driver, fp.getIPAnalyticsnotViewedAnyDocumentLink(30), "Contacts who have not Viewed any Document", action.BOOLEAN)) {
						if(click(driver, fp.getContactNotViewedAnyDocumentSortingIcon().get(0), "sorting icon", action.BOOLEAN)) {
							if(fp.checkSorting(SortOrder.Decending,fp.getContactNotViewedAnyDocumentContactNameList())) {
								appLog.info("Contact Name is verified in decending order");
							}else {
								appLog.error("Contact Name is not verified in decending order");
								sa.assertTrue(false, "Contact Name is not verified in decending order");
							}
						}else {
							appLog.error("Not able to click on contact name sorting icon so cannot check sorting in decending order");
							sa.assertTrue(false, "Not able to click on contact name sorting icon so cannot check sorting in decending order");
						}
						if(click(driver, fp.getContactNotViewedAnyDocumentSortingIcon().get(0), "sorting icon", action.BOOLEAN)) {
							if(fp.checkSorting(SortOrder.Assecending,fp.getContactNotViewedAnyDocumentContactNameList())) {
								appLog.info("Contact Name is verified in Assecending order");
							}else {
								appLog.error("Contact Name is not verified in Assecending order");
								sa.assertTrue(false, "Contact Name is not verified in Assecending order");
							}
						}else {
							appLog.error("Not able to click on contact name sorting icon so cannot check sorting in Assecending order");
							sa.assertTrue(false, "Not able to click on contact name sorting icon so cannot check sorting in Assecending order");
						}
						if(click(driver, fp.getContactNotViewedAnyDocumentHeaderTextList().get(1), "firm name header text", action.BOOLEAN)) {
							if(click(driver, fp.getContactNotViewedAnyDocumentSortingIcon().get(1), "sorting icon", action.BOOLEAN)) {
								if(fp.checkSorting(SortOrder.Decending,fp.getContactNotViewedAnyDocumentFirmNameList())) {
									appLog.info("Firm Name is verified in decending order");
								}else {
									appLog.error("Firm Name is not verified in decending order");
									sa.assertTrue(false, "Firm Name is not verified in decending order");
								}
							}else {
								appLog.error("Not able to click on Firm name sorting icon so cannot check sorting in decending order");
								sa.assertTrue(false, "Not able to click on Firm name sorting icon so cannot check sorting in decending order");
							}
							if(click(driver, fp.getContactNotViewedAnyDocumentSortingIcon().get(1), "sorting icon", action.BOOLEAN)) {
								if(fp.checkSorting(SortOrder.Assecending,fp.getContactNotViewedAnyDocumentFirmNameList())) {
									appLog.info("Firm Name is verified in Assecending order");
								}else {
									appLog.error("Firm Name is not verified in Assecending order");
									sa.assertTrue(false, "Firm Name is not verified in Assecending order");
								}
							}else {
								appLog.error("Not able to click on Firm name sorting icon so cannot check sorting in Assecending order");
								sa.assertTrue(false, "Not able to click on Firm name sorting icon so cannot check sorting in Assecending order");
							}
						}else {
							appLog.error("Not able to click on firm name header so cannot click on soritng icon");
							sa.assertTrue(false, "Not able to click on firm name header so cannot click on soritng icon");
						}
						
						if(click(driver, fp.getContactNotViewedAnyDocumentCloseBtn(30), "contact not viewed any document close button", action.BOOLEAN)) {
							appLog.info("clicked on close pop up");
						}else {
							appLog.error("Not able to click on close button so cannot close contact not viewed any documement popup");
							sa.assertTrue(false, "Not able to click on close button so cannot close contact not viewed any documement popup");
						}
						
					}else {
						appLog.error("Not able to click on contact not viewed any document link so cannot check sorting");
						sa.assertTrue(false, "Not able to click on contact not viewed any document link so cannot check sorting");
					}
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc038_1_uploadFilesInvestorSideAndCheckImpactAtAnalytics() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String docpath="UploadFiles\\Module14\\Investor_Side\\Current_Investment\\Std";
		String filesName =ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.UploadedFileStandard);
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath);
		String 	date = getSystemDate("MM/dd/yyyy")+previousOrForwardDate(-1, "MM/dd/yyyy");
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.InvestorWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in InvestorWorkspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in InvestorWorkspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in InvestorWorkspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in InvestorWorkspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp=new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M14Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			if (ifp.uploadUpdateFileInvestorSide(stdPath, filesName.split("<break>")[0], M14LimitedPartner1, null, FolderType.Standard, docpath, "yes", 30,PageName.CurrentInvestmentPgae, null, null, WorkSpaceAction.UPLOAD)) {
				appLog.info("file is Upload Successfully in Current investment in folder: "+stdPath);
				System.err.println("Contact Activity Count: "+M14Contact1_ActivityCount);
				M14Contact1_ActivityCount=String.valueOf((Integer.parseInt(M14Contact1_ActivityCount)+mostActiveContactActivityCount));
				System.out.println("updated count: "+M14Contact1_ActivityCount);
				ExcelUtils.writeData(CommonVariables.M14Contact1_ActivityCount, "Contacts", excelLabel.Variable_Name, "M14Contact1", excelLabel.Activity_Count);
				if(click(driver, ifp.getRefreshIcon(20), "Refresh Icon", action.SCROLLANDBOOLEAN)){
					SoftAssert saa = ifp.verifyContentGridInvestorSide(driver, PageName.CurrentInvestmentPgae, filesName.split("<break>")[0],updatedContactFName+ " " + updatedContactLName, date);
					sa.combineAssertions(saa);
				}else{
					appLog.error("Not Able to Click Refresh Icon so Cannot check Upoaded File");
					sa.assertTrue(false, "Not Able to Click Refresh Icon so Cannot check Upoaded File");	
				}
			}else{
				appLog.error("Upload Unsuccessful");
				sa.assertTrue(false, "Upload Unsuccessful");	
			}
			if (ifp.uploadUpdateFileInvestorSide(stdPath, filesName.split("<break>")[1], M14LimitedPartner1, null, FolderType.Standard, docpath, "yes", 30,PageName.CurrentInvestmentPgae, null, null, WorkSpaceAction.UPLOAD)) {
				appLog.info("file is Upload Successfully in Current investment in folder: "+stdPath);
				M14Contact1_ActivityCount=String.valueOf((Integer.parseInt(M14Contact1_ActivityCount)+mostActiveContactActivityCount));
				ExcelUtils.writeData(CommonVariables.M14Contact1_ActivityCount, "Contacts", excelLabel.Variable_Name, "M14Contact1", excelLabel.Activity_Count);
				if(click(driver, ifp.getRefreshIcon(20), "Refresh Icon", action.SCROLLANDBOOLEAN)){
					SoftAssert saa = ifp.verifyContentGridInvestorSide(driver, PageName.CurrentInvestmentPgae, filesName.split("<break>")[1],updatedContactFName+ " " + updatedContactLName, date);
					sa.combineAssertions(saa);
				}else{
					appLog.error("Not Able to Click Refresh Icon so Cannot check Upoaded File");
					sa.assertTrue(false, "Not Able to Click Refresh Icon so Cannot check Upoaded File");	
				}
			}else{
				appLog.error("Upload Unsuccessful");
				sa.assertTrue(false, "Upload Unsuccessful");	
			}
			
		} else {
			appLog.info("Not able to click on Current Investment tab");
			sa.assertTrue(false, "Not able to click on Current Investment tab");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc038_2_checkImpactAtIPAnalytics() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependOnTc="M14tc038_1_uploadFilesInvestorSideAndCheckImpactAtAnalytics";
		String filesName =ExcelUtils.readData("filepath", excelLabel.TestCases_Name, dependOnTc,excelLabel.UploadedFileStandard);
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Fundraising workspace view");
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(INV_UniqueDocument, INV_DocumentViews,
							INV_DocumentDownload, INV_DocumentNotViewedOrDownload, INV_ContactGrantedAccess,INV_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics(null, null,Workspace.InvestorWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(filesName, false);
					boolean flag = true;
					try {
						documents.assertAll();
					}catch (Throwable e) {
						// TODO: handle exception
						flag =false;
					}
					if(!flag) {
						appLog.info("investor side upload file is not available in document not viewed or download pop up");
					}else {
						appLog.error("investor side upload file is visible in document not viewed or download pop up");
						sa.assertTrue(false, "investor side upload file is visible in document not viewed or download pop up");
					}
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName, updatedInsName, M14Contact1_ActivityCount);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in Investor workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in Investor workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in Investor workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in Investor workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc039_accessDocFromCRMSideAndCheckImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer contact = new ContactPageBusinessLayer(driver);
		String dependONTC="M14tc036_uploadFilesInINVAndCheckIPAnalytics";
		String sharedfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,dependONTC,excelLabel.SharedPath);
		String stdfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,dependONTC,excelLabel.StandardPath);
		String commonfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,dependONTC,excelLabel.CommonPath);
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileStandard);
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileCommon);
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileShared);
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		String[] fileNames= {stdfilesName,commonfilesName,shrdfilesName};
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.InvestorWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
				switchToFrame(driver, 60, fp.getFrame(PageName.FundsPage, 60));
				if(fp.verifyFolderPathdummy(stdfolderpath, M14Institution1, M14LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace,60)) {
					if (bp.verifyDownloadFunctionality(PageName.FundsPage, Workspace.InvestorWorkspace, stdfilesName, false, false, false)) {
						appLog.info("clicked on upload file successfully: "+stdfilesName);
					}
					else {
						appLog.error("Not able to click on file name: "+stdfilesName);
						sa.assertTrue(false, "Not able to click on file name: "+stdfilesName);
					}
				}else {
					appLog.error("Not able to click on folder: "+stdfolderpath+" so cannot click on uploaded file");
					sa.assertTrue(false, "Not able to click on folder: "+stdfolderpath+" so cannot click on uploaded file");
				}
				switchToFrame(driver, 60, fp.getFrame(PageName.FundsPage, 60));
				if(fp.verifyFolderPathdummy(commonfolderpath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace,60)) {
					if (bp.verifyDownloadFunctionality(PageName.FundsPage, Workspace.InvestorWorkspace, commonfilesName, false, false, false)) {
						appLog.info("clicked on upload file successfully: "+commonfilesName);
					}
					else {
						appLog.error("Not able to click on file name: "+commonfilesName);
						sa.assertTrue(false, "Not able to click on file name: "+commonfilesName);
					}
				}else {
					appLog.error("Not able to click on folder: "+commonfolderpath+" so cannot click on uploaded file");
					sa.assertTrue(false, "Not able to click on folder: "+commonfolderpath+" so cannot click on uploaded file");
				}
				switchToFrame(driver, 60, fp.getFrame(PageName.FundsPage, 60));
				if(fp.verifyFolderPathdummy(sharedfolderpath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace,60)) {
					if (bp.verifyDownloadFunctionality(PageName.FundsPage, Workspace.InvestorWorkspace, shrdfilesName, false, false, false)) {
						appLog.info("clicked on upload file successfully: "+shrdfilesName);
					}
					else {
						appLog.error("Not able to click on file name: "+shrdfilesName);
						sa.assertTrue(false, "Not able to click on file name: "+shrdfilesName);
					}
				}else {
					appLog.error("Not able to click on folder: "+sharedfolderpath+" so cannot click on uploaded file");
					sa.assertTrue(false, "Not able to click on folder: "+sharedfolderpath+" so cannot click on uploaded file");
				}
				switchToFrame(driver, 60, fp.getFrame(PageName.FundsPage, 60));
				if(fp.verifyFolderPathdummy(stdfolderpath, M14Institution2, M14LimitedPartner2, null, PageName.FundsPage, Workspace.InvestorWorkspace,60)) {
					if (bp.verifyDownloadFunctionality(PageName.FundsPage, Workspace.InvestorWorkspace, stdfilesName, false, false, false)) {
						appLog.info("clicked on upload file successfully: "+stdfilesName);
					}
					else {
						appLog.error("Not able to click on file name: "+stdfilesName);
						sa.assertTrue(false, "Not able to click on file name: "+stdfilesName);
					}
				}else {
					appLog.error("Not able to click on folder: "+stdfolderpath+" so cannot click on uploaded file");
					sa.assertTrue(false, "Not able to click on folder: "+stdfolderpath+" so cannot click on uploaded file");
				}
				switchToFrame(driver, 30,bp.getFrame(PageName.FundsPage, 30));
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					String childWindowID = null;
					String childChildWindow = null;
					Set<String> lst1 = driver.getWindowHandles();
					Iterator<String> I1 = lst1.iterator();
					while (I1.hasNext()) {
						String windowID = I1.next();
						if (windowID.equalsIgnoreCase(parentid)) {
							appLog.info("Parent Id is Matched");
						} else {
							childWindowID = windowID;
							appLog.info("Stroged child Window Id");
							break;
						}
					}
					List<String> result = fp.verifyIPAnalyticsTargetValues(INV_UniqueDocument, INV_DocumentViews,
							INV_DocumentDownload, INV_DocumentNotViewedOrDownload, INV_ContactGrantedAccess,INV_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics(null, null,Workspace.InvestorWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(stdfilesName+"<break>"+commonfilesName+"<break>"+shrdfilesName, false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName, updatedInsName, M14Contact1_ActivityCount);
					sa.combineAssertions(activityCount);
					if (click(driver, fp.getIPAnalyticsDocumentNotViwedorDownloadLink(30), "Document not viewed or download link",action.SCROLLANDBOOLEAN)) {
						for(int i=0; i<fileNames.length; i++) {
							if(!fileNames[i].isEmpty()) {
								WebElement ele=FindElement(driver, "//a[@text='"+fileNames[i]+"']",fileNames[i]+"  file Name link", action.BOOLEAN, 10);
								if(ele!=null) {
									if(click(driver, ele, "file name", action.BOOLEAN)) {
										appLog.info("Clicked on File Name : "+fileNames[i]);
										Set<String>lst11 = driver.getWindowHandles();
										Iterator<String> II1 = lst11.iterator();
										while (II1.hasNext()) {
											String windowID = II1.next();
											if (windowID.equalsIgnoreCase(parentid) || windowID.equalsIgnoreCase(childWindowID)) {
												appLog.info("window Id is Matched");
											} else {
												childChildWindow = windowID;
												appLog.info("Stroged child child Window Id");
												driver.switchTo().window(childChildWindow);
												break;
											}
										}
										if(fp.getDownloadLink(60)!=null) {
											appLog.info("document is open after click on file name: "+fileNames[i]);
										}else {
											appLog.error("document is not open after click on fileName: "+fileNames[i]);
											sa.assertTrue(false, "document is not open after click on fileName: "+fileNames[i]);
										}
										driver.close();
										driver.switchTo().window(childWindowID);
									}else {
										appLog.error("Not able to click on file Name : "+fileNames[i]);
										sa.assertTrue(false,"Not able to click on file Name : "+fileNames[i]);
									}
								}else {
									appLog.error(fileNames[i]+ "is not available in Document not viewed or download so cannot click on file");
									sa.assertTrue(false, fileNames[i]+ "is not available in Document not viewed or download so cannot click on file");
								}
							}else {
								appLog.error("file name is not written in excel sheet so cannot click on file name, check file path in excel sheet test case No: M14tc004_1_uploadFilesInFWRAndCheckIPAnalytics ");
								sa.assertTrue(false, "file name is not written in excel sheet so cannot click on file name, check file path in excel sheet test case No: M14tc004_1_uploadFilesInFWRAndCheckIPAnalytics ");
							}
						}
						if (click(driver, fp.getDocumentNotViewedOrDownloadedCloseBtn(30), "Document not viewed or download Close button",action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Document not viewed or download pop up Close button");
						} else {
							appLog.info("Not able to Click on Document not viewed or download Close button");
							result.add("Not able to Click on Document not viewed or download Close button");
						}

					}else {
						appLog.error("Not able to click on Document not viewed or download link so cannot click on documents");
						sa.assertTrue(false,"Not able to click on Document not viewed or download link so cannot click on document");
					}
					if(click(driver, fp.getIPAnalyticsnotViewedAnyDocumentLink(10),"Document Not Viewed any document link", action.BOOLEAN)) {
						WebElement ele = FindElement(driver,"//span[@id='ContactNotViewedDocument_Grid-view-box-middle']//span[@title='"+updatedContactFName+" "+updatedContactLName+"']","Contact name in document not viewed or download pop up",action.BOOLEAN, 60);
						if(ele!=null) {
							if(click(driver, ele, "Contact Name", action.BOOLEAN)) {
								Set<String> lst11 = driver.getWindowHandles();
								Iterator<String> I2 = lst11.iterator();
								while (I2.hasNext()) {
									String windowID = I2.next();
									if (windowID.equalsIgnoreCase(parentid)
											|| windowID.equalsIgnoreCase(childWindowID)) {
										appLog.info("Parent Id or child Id is Matched");
									} else {
										childChildWindow = windowID;
										appLog.info("Stroged  child child Window Id");
										driver.switchTo().window(childChildWindow);
										break;
									}
								}
								String contactname = contact.getContactHeaderNameTextBlock(30).getText().trim();
								if (contactname.contains(M14Contact1FirstName+" "+M14Contact1LastName)) {
									appLog.info("Contact Name is Matched Successfully: " + M14Contact1FirstName+" "+M14Contact1LastName);
								} else {
									appLog.error("Contact Name is not Matched Successfully: " + M14Contact1FirstName+" "+M14Contact1LastName);
									sa.assertTrue(false,
											"Contact Name is not Matched Successfully: " + M14Contact1FirstName+" "+M14Contact1LastName);
								}
								driver.close();
								driver.switchTo().window(childWindowID);
							}else {
								appLog.error("Not able to click on Contact Name so cannot verify Contact page");
								sa.assertTrue(false, "Not able to click on Contact Name so cannot verify Contact page");
							}
						}else {
							appLog.error("Contact Name "+updatedContactFName+" "+updatedContactLName+" is not available in Document not Viewed or Download pop up so cannot click on it.");
							sa.assertTrue(false, "Contact Name "+updatedContactFName+" "+updatedContactLName+" is not available in Document not Viewed or Download pop up so cannot click on it.");
						}
					}else {
						appLog.error("Not able to click on Contact not viewed any document link so cannot click on contact name");
						sa.assertTrue(false, "Not able to click on Contact not viewed any document link so cannot click on contact name");
					}
					
					driver.close();
					driver.switchTo().window(parentid);				
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created fund Name so cannot build fundraising and investor workspace: "+M14FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot build fundraising and investor workspace: "+M14FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot build fundraising workspace and investor: "+M14FundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot build fundraising workspace and investor: "+M14FundName1);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc040_1_viewDocFromInvestorSideAndCheckImpact() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTC="M14tc036_uploadFilesInINVAndCheckIPAnalytics";
		String dependONTC1="M14tc038_1_uploadFilesInvestorSideAndCheckImpactAtAnalytics";
		String filesName =ExcelUtils.readData("filepath", excelLabel.TestCases_Name, dependONTC1,excelLabel.UploadedFileStandard);
		String sharedfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath);
		String stdfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		String commonfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath);
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileStandard);
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileCommon);
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileShared);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.InvestorWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in InvestorWorkspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in InvestorWorkspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in InvestorWorkspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in InvestorWorkspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp=new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M14Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			if(fp.verifyFolderPathdummy(stdfolderpath,null,M14LimitedPartner1,null,PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace,20)) {
				if(fp.viewDownloadDocument(stdfilesName, "M14Contact1", viewDownload.view, Workspace.CurrentInvestment, false)) {
					appLog.info("clicked on upload file: "+stdfilesName);
				}else {
					appLog.error("Not able to view upload file: "+stdfilesName);
					sa.assertTrue(false, "Not able to view upload file: "+stdfilesName);
				}
				if (fp.verifyDownloadFunctionality(PageName.PotentialInvestmentPage, Workspace.InvestorWorkspace, filesName.split("<break>")[0], false, false, true)) {
					appLog.info("clicked on upload file successfully: "+filesName.split("<break>")[0]);
				}
				else {
					appLog.error("Not able to click on file name: "+filesName.split("<break>")[0]);
					sa.assertTrue(false, "Not able to click on file name: "+filesName.split("<break>")[0]);
				}
			}else {
				appLog.error("Not able to click on folder: "+stdfolderpath+" so cannot click on file name: "+stdfilesName);
				sa.assertTrue(false, "Not able to click on folder: "+stdfolderpath+" so cannot click on file name: "+stdfilesName);
			}
			if(fp.verifyFolderPathdummy(commonfolderpath,null,null,null,PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace,20)) {
				if(fp.viewDownloadDocument(commonfilesName, "M14Contact1", viewDownload.view, Workspace.CurrentInvestment, false)) {
					appLog.info("clicked on upload file: "+commonfilesName);
				}else {
					appLog.error("Not able to view upload file: "+commonfilesName);
					sa.assertTrue(false, "Not able to view upload file: "+commonfilesName);
				}
			}else {
				appLog.error("Not able to click on folder: "+commonfolderpath+" so cannot click on file name: "+commonfilesName);
				sa.assertTrue(false, "Not able to click on folder: "+commonfolderpath+" so cannot click on file name: "+commonfilesName);
			}
			if(fp.verifyFolderPathdummy(sharedfolderpath,null,null,null,PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace,20)) {
				if(fp.viewDownloadDocument(shrdfilesName, "M14Contact1", viewDownload.view, Workspace.CurrentInvestment, false)) {
					appLog.info("clicked on upload file: "+shrdfilesName);
				}else {
					appLog.error("Not able to view upload file: "+shrdfilesName);
					sa.assertTrue(false, "Not able to view upload file: "+shrdfilesName);
				}
			}else {
				appLog.error("Not able to click on folder: "+sharedfolderpath+" so cannot click on file name: "+shrdfilesName);
				sa.assertTrue(false, "Not able to click on folder: "+sharedfolderpath+" so cannot click on file name: "+shrdfilesName);
			}
		} else {
			appLog.info("Not able to click on Current Investment tab");
			sa.assertTrue(false, "Not able to click on Current Investment tab");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc040_2_checkImpactAtIPAnalytics() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		String dependONTC="M14tc036_uploadFilesInINVAndCheckIPAnalytics";
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileStandard);
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileCommon);
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileShared);
		String[] fileNames= {stdfilesName,commonfilesName,shrdfilesName};
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Fundraising workspace view");
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					String childWindowID = null;
					String childChildWindow = null;
					Set<String> lst = driver.getWindowHandles();
					Iterator<String> I1 = lst.iterator();
					while (I1.hasNext()) {
						String windowID = I1.next();
						if (windowID.equalsIgnoreCase(parentid)) {
							appLog.info("Parent Id is Matched");
						} else {
							childWindowID = windowID;
							appLog.info("Stroged child Window Id");
							break;
						}
					}
					List<String> result = fp.verifyIPAnalyticsTargetValues(INV_UniqueDocument, INV_DocumentViews,
							INV_DocumentDownload, INV_DocumentNotViewedOrDownload, INV_ContactGrantedAccess,INV_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.InvestorWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(null, false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(M14Contact2FirstName+" "+M14Contact2LastName,M14Institution1);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName, updatedInsName, M14Contact1_ActivityCount);
					sa.combineAssertions(activityCount);
					if (click(driver, fp.getIPAnalyticsMostViewedDocumentLink(30), "Most View Document Link",action.SCROLLANDBOOLEAN)) {
						for(int i=0; i<fileNames.length; i++) {
							if(!fileNames[i].isEmpty()) {
								WebElement ele=FindElement(driver, "//a[@text='"+fileNames[i]+"']",fileNames[i]+"  file Name link", action.BOOLEAN, 10);
								if(ele!=null) {
									if(click(driver, ele, "file name", action.BOOLEAN)) {
										appLog.info("Clicked on File Name : "+fileNames[i]);
										Set<String>lst1 = driver.getWindowHandles();
										Iterator<String> II1 = lst1.iterator();
										while (II1.hasNext()) {
											String windowID = II1.next();
											if (windowID.equalsIgnoreCase(parentid) || windowID.equalsIgnoreCase(childWindowID)) {
												appLog.info("window Id is Matched");
											} else {
												childChildWindow = windowID;
												appLog.info("Stroged child child Window Id");
												driver.switchTo().window(childChildWindow);
												break;
											}
										}
										if(fp.getDownloadLink(60)!=null) {
											appLog.info("document is open after click on file name: "+fileNames[i]);
										}else {
											appLog.error("document is not open after click on fileName: "+fileNames[i]);
											sa.assertTrue(false, "document is not open after click on fileName: "+fileNames[i]);
										}
										driver.close();
										driver.switchTo().window(childWindowID);
									}else {
										appLog.error("Not able to click on file Name : "+fileNames[i]);
										sa.assertTrue(false,"Not able to click on file Name : "+fileNames[i]);
									}
								}else {
									appLog.error(fileNames[i]+ "is not available in most viewed document so cannot click on file");
									sa.assertTrue(false, fileNames[i]+ "is not available in most viewed document so cannot click on file");
								}
							}else {
								appLog.error("file name is not written in excel sheet so cannot click on file name, check file path in excel sheet test case No: M14tc036_uploadFilesInINVAndCheckIPAnalytics ");
								sa.assertTrue(false, "file name is not written in excel sheet so cannot click on file name, check file path in excel sheet test case No: M14tc036_uploadFilesInINVAndCheckIPAnalytics ");
							}
						}
					if (click(driver, fp.getMostviewedDocumentCloseBtn(10), "Most Viewed Document pop up Close button",action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Most Viewed Document pop up Close button");
					} else {
						appLog.info("Not able to Click on Most Viewed Document pop up Close button");
						result.add("Not able to Click on Most Viewed Document pop up Close button");
					}
					
				}else {
					appLog.error("Not able to click on Most viewed Document link so cannot check update document");
					sa.assertTrue(false,"Not able to click on Most viewed Document link so cannot check update document");
				}
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in InvestorWorkspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in InvestorWorkspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in InvestorWorkspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in InvestorWorkspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc041_changeEmailAndDeleteContactAtCRMSideAndCheckImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer con = new ContactPageBusinessLayer(driver);
		HomePageBusineesLayer home = new HomePageBusineesLayer(driver);
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		String email=con.generateRandomEmailId();
		if(con.updateEmailAddressOfCreatedContact(M14Contact1FirstName, M14Contact1LastName,email)) {
			appLog.info(M14Contact1FirstName+" "+M14Contact1LastName+" contact email id is updated");
			ExcelUtils.writeData(email,"Contacts", excelLabel.Variable_Name, "M14Contact1",excelLabel.ContactUpdatedEmailID);
		}else {
			appLog.error(M14Contact1FirstName+" "+M14Contact1LastName+" contact email id is not updated");
			sa.assertTrue(false, M14Contact1FirstName+" "+M14Contact1LastName+" contact email id is not updated");
		}
		if(con.deleteCreatedContact(M14Contact2FirstName, M14Contact2LastName)) {
			appLog.info(M14Contact2FirstName+" "+M14Contact2LastName+" contact is deleted");
		}else {
			appLog.error(M14Contact2FirstName+" "+M14Contact2LastName+" contact is not deleted");
			sa.assertTrue(false, M14Contact2FirstName+" "+M14Contact2LastName+" contact is not deleted");
		}
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(click(driver, fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "ip analtyics icon", action.SCROLLANDBOOLEAN)) {
					String parentid=switchOnWindow(driver);
					ThreadSleep(3000);
					if (click(driver,fp.getIPAnalyticsnotViewedAnyDocumentLink(30),"Contact not viewed any document", action.SCROLLANDBOOLEAN)) {
						WebElement ele = isDisplayed(driver,
								FindElement(driver,
										"//span[contains(@id,'ContactNotViewedDocument_Grid-cell-0-')]/a[text()='"+M14Contact1FirstName+" "+M14Contact2LastName+"']","Contact Name Link in Contact not viewed any document pop up", action.BOOLEAN, 60),"visibility", 60, "Contact Name Link");
						if (ele != null) {
							clickUsingJavaScript(driver, ele, "");
							//if (click(driver, ele, "Contact Name Link in Contact not viewed any document",
							//		action.BOOLEAN)) {
								//if (isAlertPresent(driver)) {
									String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
									switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
									if (msg.trim().contains(FundsPageErrorMessage.ContactNotFoundErrorMessage)) {
										appLog.info(
												"Error Message is verified in Contact not viewed any document: "+FundsPageErrorMessage.ContactNotFoundErrorMessage);
									} else {
										appLog.error(
												"Error Message is not verified in Contact not viewed any document: "+FundsPageErrorMessage.ContactNotFoundErrorMessage);
										sa.assertTrue(false,
												"Error Message is not verified in Contact not viewed any document: "+FundsPageErrorMessage.ContactNotFoundErrorMessage);
									}
								/*} else {
									appLog.error("Not able to Click on Contact Name Link: " + M14Contact2FirstName + " "
											+ M14Contact2LastName
											+ " so we cannot verify error message in Contact not viewed any document.");
									sa.assertTrue(false, "Not able to Click on Contact Name Link: " + M14Contact2FirstName + " "
											+ M14Contact2LastName
											+ " so we cannot verify error message in Contact not viewed any document.");
								}

							} else {
								appLog.info("Not able to click on Contact Name Link in Contact not viewed any document");
								sa.assertTrue(false,
										"Not able to click on Contact Name Link in Contact not viewed any document");
							}*/
						} else {
							appLog.error("Contact Name is not available in Contact not viewed any document pop up : "
									+ M14Contact2FirstName + " " + M14Contact2LastName);
							sa.assertTrue(false,
									"Contact Name is not available in Contact not viewed any document pop up : "
											+ M14Contact2FirstName + " " + M14Contact2LastName);
						}
						if (click(driver,fp.getContactNotViewedAnyDocumentCloseBtn(20),
								"Contact not viewed any document Close Button", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on Contact not viewed any document pop up Close button");
						} else {
							appLog.info("Not able to click  on Contact not viewed any document pop up Close button");
							sa.assertTrue(false, "Not able to click on Contact not viewed any document pop up Close button");
						}
					} else {
						appLog.info("Not able to click on Contact not viewed any document Link");
						sa.assertTrue(false, "Not able to click on Contact not viewed any document Link");
					}
					if (click(driver, fp.getIPAnalyticsMostActiveContactsLink(30), "Most Active Contact Link",
							action.SCROLLANDBOOLEAN)) {
						WebElement ele = isDisplayed(driver,
								FindElement(driver,
										"//span[contains(@id,'MostActiveContacts_Grid-cell-0-')]/a[text()='"+updatedContactFName+" "+updatedContactLName+"']",
										"Contact Name Link in Contact not any document pop up", action.BOOLEAN, 60),
								"visibility", 60, "Contact Name Link");
						if (ele != null) {
							clickUsingJavaScript(driver, ele, "contact name");
							//if (click(driver, ele, "Contact Name Link in Contact not viewed any document", action.BOOLEAN)) {
								//if (isAlertPresent(driver)) {
									String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
									switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
									if (msg.trim().contains(FundsPageErrorMessage.ContactNotFoundErrorMessage)) {
										appLog.info(
												"Error Message is verified in contact not viewed any document up: "+FundsPageErrorMessage.ContactNotFoundErrorMessage);
									} else {
										appLog.error(
												"Error Message is not verified in contact not viewed any document up: "+FundsPageErrorMessage.ContactNotFoundErrorMessage);
										sa.assertTrue(false,
												"Error Message is not verified in contact not viewed any document up: "+FundsPageErrorMessage.ContactNotFoundErrorMessage);
									}
								/*} else {
									appLog.error("Not able to Click on Contact Name Link: "+updatedContactFName+" "+updatedContactLName+" so we cannot verify error message in contact not viewed any document up.");
									sa.assertTrue(false, "Not able to Click on Contact Name Link: "+updatedContactFName+" "+updatedContactLName+" so we cannot verify error message in contact not viewed any document up.");
								}
							} else {
								appLog.info("Not able to click on Contact Name Link in Contact not viewed any document");
								sa.assertTrue(false,
										"Not able to click on Contact Name Link in Contact not viewed any document");
							}*/
						} else {
							appLog.error("Contact Name is not available in Contact not viewed any document pop up : "+updatedContactFName+" "+updatedContactLName);
							sa.assertTrue(false, "Contact Name is not available in Contact not viewed any document pop up : "+updatedContactFName+" "+updatedContactLName);
						}
					}else {
						appLog.error("Not able to click on Most Active Contact Link so cannot check contact Error Message of "+updatedContactFName+" "+updatedContactLName);
						sa.assertTrue(false, "Not able to click on Most Active Contact Link so cannot check contact Error Message of "+updatedContactFName+" "+updatedContactLName);
					}
					driver.close();
					driver.switchTo().window(parentid);
				}else {
					appLog.error("Not able to click on ip analytics icon so cannot check error message in ip analytics");
					sa.assertTrue(false, "Not able to click on ip analytics icon so cannot check error message in ip analytics");
				}
			}else {
				appLog.error("Not able to click on created fund : "+M14FundName1+" so cannot check error message in ip analytics");
				sa.assertTrue(false, "Not able to click on created fund : "+M14FundName1+" so cannot check error message in ip analytics");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot check error message in  ip analytics ");
			sa.assertTrue(false, "Not able to click on fund tab so cannot check error message in  ip analytics ");
		}
		if(home.restoreValuesFromRecycleBin(M14Contact2FirstName+" "+M14Contact2LastName)) {
			appLog.info(M14Contact2FirstName+" "+M14Contact2LastName+" contact is restored form Recycle Bin");
		}else {
			appLog.error(M14Contact2FirstName+" "+M14Contact2LastName+" contact is not restored form Recycle Bin");
			sa.assertTrue(false, M14Contact2FirstName+" "+M14Contact2LastName+" contact is not restored form Recycle Bin");
		}
		if(con.updateEmailAddressOfCreatedContact(M14Contact1FirstName, M14Contact1LastName,M14Contact1EmailId)) {
			appLog.info(M14Contact1FirstName+" "+M14Contact1LastName+" contact email id is updated as old email");
		}else {
			appLog.error(M14Contact1FirstName+" "+M14Contact1LastName+" contact email id is not updated as old email");
			sa.assertTrue(false, M14Contact1FirstName+" "+M14Contact1LastName+" contact email id is not updated as old email");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc042_1_downloadDocFromInvestorSideAndCheckImpact() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTC="M14tc036_uploadFilesInINVAndCheckIPAnalytics";
		String sharedfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath);
		String stdfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		String commonfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath);
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileStandard);
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileCommon);
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileShared);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.InvestorWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in InvestorWorkspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in InvestorWorkspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in InvestorWorkspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in InvestorWorkspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp=new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M14Contact2EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			if(fp.verifyFolderPathdummy(stdfolderpath,null,M14LimitedPartner2,null,PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace,20)) {
				if(fp.viewDownloadDocument(stdfilesName, "M14Contact2", viewDownload.Download, Workspace.CurrentInvestment, false)) {
					appLog.info("clicked on upload file: "+stdfilesName);
				}else {
					appLog.error("Not able to view upload file: "+stdfilesName);
					sa.assertTrue(false, "Not able to view upload file: "+stdfilesName);
				}
			}else {
				appLog.error("Not able to click on folder: "+stdfolderpath+" so cannot click on file name: "+stdfilesName);
				sa.assertTrue(false, "Not able to click on folder: "+stdfolderpath+" so cannot click on file name: "+stdfilesName);
			}
			if(fp.verifyFolderPathdummy(commonfolderpath,null,null,null,PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace,20)) {
				if(fp.viewDownloadDocument(commonfilesName, "M14Contact2", viewDownload.Download, Workspace.CurrentInvestment, false)) {
					appLog.info("clicked on upload file: "+commonfilesName);
				}else {
					appLog.error("Not able to view upload file: "+commonfilesName);
					sa.assertTrue(false, "Not able to view upload file: "+commonfilesName);
				}
			}else {
				appLog.error("Not able to click on folder: "+commonfolderpath+" so cannot click on file name: "+commonfilesName);
				sa.assertTrue(false, "Not able to click on folder: "+commonfolderpath+" so cannot click on file name: "+commonfilesName);
			}
			if(fp.verifyFolderPathdummy(sharedfolderpath,null,null,null,PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace,20)) {
				if(fp.viewDownloadDocument(shrdfilesName, "M14Contact2", viewDownload.Download, Workspace.CurrentInvestment, false)) {
					appLog.info("clicked on upload file: "+shrdfilesName);
				}else {
					appLog.error("Not able to view upload file: "+shrdfilesName);
					sa.assertTrue(false, "Not able to view upload file: "+shrdfilesName);
				}
			}else {
				appLog.error("Not able to click on folder: "+sharedfolderpath+" so cannot click on file name: "+shrdfilesName);
				sa.assertTrue(false, "Not able to click on folder: "+sharedfolderpath+" so cannot click on file name: "+shrdfilesName);
			}
		} else {
			appLog.info("Not able to click on Potential Investment tab");
			sa.assertTrue(false, "Not able to click on Potential Investment tab");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc042_2_checkImpactAtIPAnalytics() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Fundraising workspace view");
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(INV_UniqueDocument, INV_DocumentViews,
							INV_DocumentDownload, INV_DocumentNotViewedOrDownload, INV_ContactGrantedAccess,INV_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.InvestorWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(null, false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1, M14Contact1_ActivityCount+"<break>"+M14Contact2_ActivityCount);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in InvestorWorkspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in InvestorWorkspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in InvestorWorkspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in InvestorWorkspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc043_1_UpdateFileInvestorSideAndCheckImpactAtAnalytics() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String docpath="UploadFiles\\Module14\\Investor_Side\\Current_Investment\\Std";
		String filesName =ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.UploadedFileStandard);
		String stdPath = ExcelUtils.readData("filepath", excelLabel.TestCases_Name, currentlyExecutingTC,excelLabel.StandardPath);
		String 	date = getSystemDate("MM/dd/yyyy")+previousOrForwardDate(-1, "MM/dd/yyyy");
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.InvestorWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in InvestorWorkspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in InvestorWorkspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in InvestorWorkspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in InvestorWorkspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp=new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M14Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			if (ifp.uploadUpdateFileInvestorSide(stdPath, filesName, null, M14LimitedPartner1, FolderType.Standard, docpath, " ", 30,PageName.CurrentInvestmentPgae, null, null, WorkSpaceAction.UPDATE)) {
				appLog.info("file is Upload Successfully in Current investment in folder: "+stdPath);
				M14Contact1_ActivityCount=String.valueOf((Integer.parseInt(M14Contact1_ActivityCount)+mostActiveContactActivityCount));
				ExcelUtils.writeData(CommonVariables.M14Contact1_ActivityCount, "Contacts", excelLabel.Variable_Name, "M14Contact1", excelLabel.Activity_Count);
				if(click(driver, ifp.getRefreshIcon(20), "Refresh Icon", action.SCROLLANDBOOLEAN)){
					SoftAssert saa = ifp.verifyContentGridInvestorSide(driver, PageName.CurrentInvestmentPgae, filesName,updatedContactFName+ " " + updatedContactLName, date);
					sa.combineAssertions(saa);
				}else{
					appLog.error("Not Able to Click Refresh Icon so Cannot check Upoaded File");
					sa.assertTrue(false, "Not Able to Click Refresh Icon so Cannot check Upoaded File");	
				}
			}else{
				appLog.error("Upload Unsuccessful");
				sa.assertTrue(false, "Upload Unsuccessful");	
			}
			
		} else {
			appLog.info("Not able to click on Current Investment tab");
			sa.assertTrue(false, "Not able to click on Current Investment tab");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc043_2_updateFilesInINVAndCheckIPAnalytics() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String sharedfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath);
		String commonfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath);
		String internalfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.InternalPath);
		String standardfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		String stddocpath="UploadFiles\\Module14\\CRM_Side\\INV\\Standard";
		String shrddocpath="UploadFiles\\Module14\\CRM_Side\\INV\\Shared";
		String commondocpath="UploadFiles\\Module14\\CRM_Side\\INV\\Common";
		String internaldocpath="UploadFiles\\Module14\\CRM_Side\\INV\\Internal";
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				if(fp.uploadFile(standardfolderpath,M14Institution1+"/"+M14LimitedPartner1+"<break>"+M14Institution2+"/"+M14LimitedPartner2, stddocpath,null,UploadFileActions.Update, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly in folder: "+standardfolderpath);
					switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "InvestorWorkspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"InvestorWorkspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileStandard);
						if(filesName!=null) {
							List<String>uploadFiles=compareMultipleListOnBasisOfTitle(driver,filesName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!uploadFiles.isEmpty()) {
								for(int i=0;i<uploadFiles.size();i++) {
									sa.assertTrue(false, uploadFiles.get(i));
								}
							}else {
								appLog.info("All Updated files are verified: "+filesName);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify Update documents in standard folder.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify Update documents in standard folder");
					}
				}else {
					appLog.error("File is not uploaded in folder "+standardfolderpath);
					sa.assertTrue(false, "File is not uploaded in "+standardfolderpath);
				}
				switchToDefaultContent(driver);
				if(fp.uploadFile(sharedfolderpath,null, shrddocpath,null,UploadFileActions.Update, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly in folder: "+sharedfolderpath);
					switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "InvestorWorkspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"InvestorWorkspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileShared);
						if(filesName!=null) {
							List<String>uploadFiles=compareMultipleListOnBasisOfTitle(driver,filesName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!uploadFiles.isEmpty()) {
								for(int i=0;i<uploadFiles.size();i++) {
									sa.assertTrue(false, uploadFiles.get(i));
								}
							}else {
								appLog.info("All Updated files are verified: "+filesName);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify Updated documents in shared folder.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify Updated documents in shared folder");
					}
				}else {
					appLog.error("File is not uploaded in folder "+sharedfolderpath);
					sa.assertTrue(false, "File is not uploaded in "+sharedfolderpath);
				}
				switchToDefaultContent(driver);
				if(fp.uploadFile(commonfolderpath,null, commondocpath,null,UploadFileActions.Update, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly in folder: "+commonfolderpath);
					switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "InvestorWorkspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"InvestorWorkspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileCommon);
						if(filesName!=null) {
							List<String>uploadFiles=compareMultipleListOnBasisOfTitle(driver,filesName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!uploadFiles.isEmpty()) {
								for(int i=0;i<uploadFiles.size();i++) {
									sa.assertTrue(false, uploadFiles.get(i));
								}
							}else {
								appLog.info("All Updated files are verified: "+filesName);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify Updated documents in common folder.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify Updated documents in common folder");
					}
				}else {
					appLog.error("File is not uploaded in folder "+commonfolderpath);
					sa.assertTrue(false, "File is not uploaded in "+commonfolderpath);
				}
				switchToDefaultContent(driver);
				if(fp.uploadFile(internalfolderpath,null, internaldocpath,null,UploadFileActions.Update, Workspace.InvestorWorkspace, PageName.FundsPage, 30)) {
					appLog.info("File is upload successfullly in folder: "+internalfolderpath);
					switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
					scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "InvestorWorkspace view");
					if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"InvestorWorkspace refresh button", action.SCROLLANDBOOLEAN)) {
						String filesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileInternal);
						if(filesName!=null) {
							List<String>uploadFiles=compareMultipleListOnBasisOfTitle(driver,filesName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!uploadFiles.isEmpty()) {
								for(int i=0;i<uploadFiles.size();i++) {
									sa.assertTrue(false, uploadFiles.get(i));
								}
							}else {
								appLog.info("All Updated files are verified: "+filesName);
							}
						}
					}else {
						appLog.error("Not able to click on refresh icon so cannot verify updated documents in common folder.");
						sa.assertTrue(false, "Not able to click on refresh icon so cannot verify updated documents in common folder");
					}
				}else {
					appLog.error("File is not uploaded in folder "+internalfolderpath);
					sa.assertTrue(false, "File is not uploaded in "+internalfolderpath);
				}
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(INV_UniqueDocument, INV_DocumentViews,
							INV_DocumentDownload, INV_DocumentNotViewedOrDownload, INV_ContactGrantedAccess,INV_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.InvestorWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(null, false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1, M14Contact1_ActivityCount+"<break>"+M14Contact2_ActivityCount);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in InvestorWorkspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in InvestorWorkspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in InvestorWorkspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in InvestorWorkspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc044_1_viewUpdatedDocFromInvestorSideAndCheckImpact() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTC="M14tc043_2_updateFilesInINVAndCheckIPAnalytics";
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UpdatedFileStandard);
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UpdatedFileCommon);
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UpdatedFileShared);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.InvestorWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in InvestorWorkspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in InvestorWorkspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in InvestorWorkspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in InvestorWorkspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp=new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M14Contact2EmailId, adminPassword);
		ThreadSleep(10000);
		if(click(driver, ifp.getRecentActivitiesTab(60), "recent activities tab", action.SCROLLANDBOOLEAN)) {
			if(fp.viewDownloadDocument(stdfilesName.split("<break>")[0], "M14Contact2", viewDownload.view, Workspace.CurrentInvestment, false)) {
				appLog.info("clicked on Updated file: "+stdfilesName.split("<break>")[0]);
			}else {
				appLog.error("Not able to view Updated file: "+stdfilesName.split("<break>")[0]);
				sa.assertTrue(false, "Not able to view Updated file: "+stdfilesName.split("<break>")[0]);
			}
			if(fp.viewDownloadDocument(commonfilesName, "M14Contact2", viewDownload.view, Workspace.CurrentInvestment, false)) {
				appLog.info("clicked on Updated file: "+commonfilesName);
			}else {
				appLog.error("Not able to view Updated file: "+commonfilesName);
				sa.assertTrue(false, "Not able to view Updated file: "+commonfilesName);
			}
			if(fp.viewDownloadDocument(shrdfilesName, "M14Contact2", viewDownload.view, Workspace.CurrentInvestment, false)) {
				appLog.info("clicked on Updated file: "+shrdfilesName);
			}else {
				appLog.error("Not able to view Updated file: "+shrdfilesName);
				sa.assertTrue(false, "Not able to view Updated file: "+shrdfilesName);
			}
			
		}else {
			appLog.error("Not able to click on recent activities tab so cannot click on file names");
			sa.assertTrue(false, "Not able to click on recent activities tab so cannot click on file names");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc044_2_checkImpactAtIPAnalytics() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "InvestorWorkspace view");
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(INV_UniqueDocument, INV_DocumentViews,
							INV_DocumentDownload, INV_DocumentNotViewedOrDownload, INV_ContactGrantedAccess,INV_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.InvestorWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(null, false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1, M14Contact1_ActivityCount+"<break>"+M14Contact2_ActivityCount);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in InvestorWorkspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in InvestorWorkspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in InvestorWorkspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in InvestorWorkspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M14tc045_updateDocWIthDiffNameAndCheckImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTC="M14tc036_uploadFilesInINVAndCheckIPAnalytics";
		String DependOnTC1="M14tc038_1_uploadFilesInvestorSideAndCheckImpactAtAnalytics";
		String sharedfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath);
		String stdfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		String commonfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath);
		String internalfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.InternalPath);
		String UpdatestdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileStandard);
		String UpdatecommonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileCommon);
		String updateshrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileShared);
		String updateinternalfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, currentlyExecutingTC, excelLabel.UpdatedFileInternal);
		
		String uploadstdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileStandard);
		String uploadcommonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileCommon);
		String uploadshrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileShared);
		String uploadinternalfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileInternal);
		String uploadInvestorSidestdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, DependOnTC1, excelLabel.UploadedFileStandard);
		
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		String docPath=System.getProperty("user.dir") + "\\UploadFiles\\Module14\\FileToUpdate\\";
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.InvestorWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
				if(fp.updateFile(stdfolderpath, uploadstdfilesName, M14Institution1, M14LimitedPartner1, FolderType.Standard,docPath+UpdatestdfilesName.split("<break>")[0], multiInstance.AllInvestor, null, ContentGridArrowKeyFunctions.Update,20, PageName.FundsPage,"Yes",null, Workspace.InvestorWorkspace)) {
					appLog.info("file is updated successfully: "+uploadstdfilesName+" in :"+stdfolderpath+" to "+UpdatestdfilesName.split("<break>")[0]);
						switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleListOnBasisOfTitle(driver,UpdatestdfilesName.split("<break>")[0],fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(UpdatestdfilesName.split("<break>")[0]+" is verified.");
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify updated document: "+UpdatestdfilesName.split("<break>")[0]);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify updated document: "+UpdatestdfilesName.split("<break>")[0]);
						}
				}else {
					appLog.error("file is not updated: "+uploadstdfilesName+" in :"+UpdatestdfilesName.split("<break>")[0]);
					sa.assertTrue(false, "file is not updated: "+uploadstdfilesName+" in :"+UpdatestdfilesName.split("<break>")[0]);
				}
				switchToDefaultContent(driver);
				if(fp.updateFile(stdfolderpath, uploadInvestorSidestdfilesName.split("<break>")[0], M14Institution1, M14LimitedPartner1, FolderType.Standard,docPath+UpdatestdfilesName.split("<break>")[1], null, null, ContentGridArrowKeyFunctions.Update,20, PageName.FundsPage,"Yes",null, Workspace.InvestorWorkspace)) {
					appLog.info("file is updated successfully: "+uploadInvestorSidestdfilesName.split("<break>")[0]+" in :"+stdfolderpath+" to "+UpdatestdfilesName.split("<break>")[1]);
						switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"InvestorWorkspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleListOnBasisOfTitle(driver,UpdatestdfilesName.split("<break>")[1],fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(UpdatestdfilesName.split("<break>")[1]+" is verified.");
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify updated document: "+UpdatestdfilesName.split("<break>")[0]);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify updated document: "+UpdatestdfilesName.split("<break>")[0]);
						}
				}else {
					appLog.error("file is not updated: "+uploadInvestorSidestdfilesName+" in :"+stdfolderpath);
					sa.assertTrue(false, "file is not updated: "+uploadInvestorSidestdfilesName+" in :"+stdfolderpath);
				}
				switchToDefaultContent(driver);
				if(fp.updateFile(commonfolderpath, uploadcommonfilesName, null, null, FolderType.Standard,docPath+UpdatecommonfilesName, null, null, ContentGridArrowKeyFunctions.Update,20, PageName.FundsPage,"Yes",null, Workspace.InvestorWorkspace)) {
					appLog.info("file is updated successfully: "+uploadcommonfilesName+" in :"+commonfolderpath+" to "+UpdatecommonfilesName);
						switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleListOnBasisOfTitle(driver,UpdatecommonfilesName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(UpdatecommonfilesName+" is verified.");
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify updated document: "+UpdatecommonfilesName);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify updated document: "+UpdatecommonfilesName);
						}
				}else {
					appLog.error("file is not updated: "+uploadcommonfilesName+" in :"+uploadcommonfilesName);
					sa.assertTrue(false, "file is not updated: "+uploadcommonfilesName+" in :"+uploadcommonfilesName);
				}
				switchToDefaultContent(driver);
				if(fp.updateFile(internalfolderpath, uploadinternalfilesName, null, null, FolderType.Standard,docPath+updateinternalfilesName, null, null, ContentGridArrowKeyFunctions.Update,20, PageName.FundsPage,"Yes",null, Workspace.InvestorWorkspace)) {
					appLog.info("file is updated successfully: "+uploadinternalfilesName+" in :"+internalfolderpath+" to "+updateinternalfilesName);
						switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"InvestorWorkspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleListOnBasisOfTitle(driver,updateinternalfilesName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(updateinternalfilesName+" is verified.");
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify update document: "+updateinternalfilesName);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify update document: "+updateinternalfilesName);
						}
				}else {
					appLog.error("file is not updated: "+uploadinternalfilesName+" in :"+uploadcommonfilesName);
					sa.assertTrue(false, "file is not updated: "+uploadinternalfilesName+" in :"+uploadcommonfilesName);
				}
				switchToDefaultContent(driver);
				if(fp.updateFile(sharedfolderpath, uploadshrdfilesName, null, null, FolderType.Shared,docPath+updateshrdfilesName, null, null, ContentGridArrowKeyFunctions.Update,20, PageName.FundsPage,"Yes",null, Workspace.InvestorWorkspace)) {
					appLog.info("file is updated successfully: "+uploadshrdfilesName+" in :"+sharedfolderpath+" to "+updateshrdfilesName);
					switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"InvestorWorkspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleListOnBasisOfTitle(driver,updateshrdfilesName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(updateshrdfilesName+" is verified.");
							}
						}else {
							appLog.error("Not able to click on refresh icon so cannot verify update document: "+updateshrdfilesName);
							sa.assertTrue(false, "Not able to click on refresh icon so cannot verify update document: "+updateshrdfilesName);
						}
				}else {
					appLog.error("file is not updated: "+uploadshrdfilesName+" in :"+sharedfolderpath);
					sa.assertTrue(false, "file is not updated: "+uploadshrdfilesName+" in :"+sharedfolderpath);
				}
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(INV_UniqueDocument, INV_DocumentViews,
							INV_DocumentDownload, INV_DocumentNotViewedOrDownload, INV_ContactGrantedAccess,INV_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.InvestorWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(null, false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1, M14Contact1_ActivityCount+"<break>"+M14Contact2_ActivityCount);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);				
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created fund Name so cannot  updated document in investor workspace: "+M14FundName1);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot  updated document in investor workspace: "+M14FundName1);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot  updated document in investor workspace: "+M14FundName1);
			sa.assertTrue(false, "Not able to click on fund tab so cannot updated document in investor workspace: "+M14FundName1);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc046_1_viewDiffNameUpdatedDocFromInvestorSide() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTC="M14tc045_updateDocWIthDiffNameAndCheckImpact";
		String sharedfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath);
		String stdfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		String commonfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath);
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UpdatedFileStandard);
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UpdatedFileCommon);
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UpdatedFileShared);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.InvestorWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in InvestorWorkspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in InvestorWorkspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in InvestorWorkspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in InvestorWorkspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp=new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M14Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			if(fp.verifyFolderPathdummy(stdfolderpath,null,M14LimitedPartner1,null,PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace,20)) {
				if (fp.viewDownloadDocument(stdfilesName.split("<break>")[0], "M14Contact1", viewDownload.view, Workspace.CurrentInvestment, true)) {
					appLog.info("clicked on Updated file: "+stdfilesName.split("<break>")[0]);
				}else {
					appLog.error("Not able to view Updated file: "+stdfilesName.split("<break>")[0]);
					sa.assertTrue(false, "Not able to view Updated file: "+stdfilesName.split("<break>")[0]);
				}
				if (fp.verifyDownloadFunctionality(PageName.FundsPage, Workspace.InvestorWorkspace, stdfilesName.split("<break>")[1], false, false, false)) {
					appLog.info("clicked on upload file successfully: "+stdfilesName.split("<break>")[1]);
				}
				else {
					appLog.error("Not able to click on file name: "+stdfilesName.split("<break>")[1]);
					sa.assertTrue(false, "Not able to click on file name: "+stdfilesName.split("<break>")[1]);
				}
			}else {
				appLog.error("Not able to click on folder: "+stdfolderpath+" so cannot click on file name: "+stdfilesName.split("<break>")[0]+" and "+stdfilesName.split("<break>")[1]);
				sa.assertTrue(false, "Not able to click on folder: "+stdfolderpath+" so cannot click on file name: "+stdfilesName.split("<break>")[0]+" and "+stdfilesName.split("<break>")[1]);
			}
			if(fp.verifyFolderPathdummy(commonfolderpath,null,null,null,PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace,20)) {
				if(fp.viewDownloadDocument(commonfilesName, "M14Contact1", viewDownload.view, Workspace.InvestorWorkspace, true)) {
					appLog.info("clicked on Updated file: "+commonfilesName);
				}else {
					appLog.error("Not able to view Updated file: "+commonfilesName);
					sa.assertTrue(false, "Not able to view Updated file: "+commonfilesName);
				}
			}else {
				appLog.error("Not able to click on folder: "+commonfolderpath+" so cannot click on file name: "+commonfilesName);
				sa.assertTrue(false, "Not able to click on folder: "+commonfolderpath+" so cannot click on file name: "+commonfilesName);
			}
			if(fp.verifyFolderPathdummy(sharedfolderpath,null,null,null,PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace,20)) {
				if(fp.viewDownloadDocument(shrdfilesName, "M14Contact1", viewDownload.view, Workspace.CurrentInvestment, true)) {
					appLog.info("clicked on Updated file: "+shrdfilesName);
				}else {
					appLog.error("Not able to view Updated file: "+shrdfilesName);
					sa.assertTrue(false, "Not able to view Updated file: "+shrdfilesName);
				}
			}else {
				appLog.error("Not able to click on folder: "+sharedfolderpath+" so cannot click on file name: "+shrdfilesName);
				sa.assertTrue(false, "Not able to click on folder: "+sharedfolderpath+" so cannot click on file name: "+shrdfilesName);
			}
		} else {
			appLog.info("Not able to click on Current Investment tab");
			sa.assertTrue(false, "Not able to click on Current Investment tab");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc046_2_checkImpactAtIPAnalytics() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "InvestorWorkspace view");
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(INV_UniqueDocument, INV_DocumentViews,
							INV_DocumentDownload, INV_DocumentNotViewedOrDownload, INV_ContactGrantedAccess,INV_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.InvestorWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(null, false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1, M14Contact1_ActivityCount+"<break>"+M14Contact2_ActivityCount);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in InvestorWorkspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in InvestorWorkspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in InvestorWorkspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in InvestorWorkspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc047_activateManageApprovalsSettings() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		NIMPageBusinessLayer nim = new NIMPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName,adminPassword);
		if(nim.clickOnTab(TabName.NIMTab)) {
			switchToFrame(driver, 30, nim.getNIMTabFrame(30));
			if(nim.clickOnSideMenusTab(sideMenu.ManageApprovals)) {
				if(nim.activateManageApprovalsSettings(CRMUser1FirstName+" "+CRMUser1LastName).isEmpty()) {
					appLog.info("manage approvals settings is activated");
				}else {
					appLog.error("Manage aprrovals settings is not activated");
					sa.assertTrue(false, "Manage aprrovals settings is not activated");
				}
				
			}else {
				appLog.error("Not able to click on manage approvals tab so cannot activate manage approvals settings ");
				sa.assertTrue(false, "Not able to click on manage approvals tab so cannot activate manage approvals settings ");
			}
		}else {
			appLog.error("Not able to click on NIM Tab so cannot activate manage approvals settings");
			sa.assertTrue(false, "Not able to click on NIM Tab so cannot activate manage approvals settings");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc048_onlineImportDocInINV() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String sharedfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath);
		String commonfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath);
		String internalfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.InternalPath);
		String standardfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		String uploadstdfileName=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.UploadedFileStandard);
		String uploadcommonfileName=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.UploadedFileCommon);
		String uploadsharedfileName=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.UploadedFileShared);
		String uploadinternalfileName=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.UploadedFileInternal);
		String DocPath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.OnlineImportPath);
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.InvestorWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
				if(fp.onlineImport(M14Institution1, M14LimitedPartner1, M14LimitedPartner2,standardfolderpath,DocPath,uploadstdfileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.MultipleInstitute, WorkSpaceAction.UPLOAD, FolderType.Standard, PageName.FundsPage, Workspace.InvestorWorkspace,20)) {
					appLog.info("file is imported successfully: "+uploadstdfileName+" in :"+standardfolderpath);
						switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"InvestorWorkspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleListOnBasisOfTitle(driver,uploadstdfileName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(uploadstdfileName+" is verified.");
							}
						}
				}else {
					appLog.error("file is not imported: "+uploadstdfileName+" in :"+standardfolderpath);
					sa.assertTrue(false, "file is not imported: "+uploadstdfileName+" in :"+standardfolderpath);
				}
				switchToDefaultContent(driver);
				if(fp.onlineImport(null, null, null,commonfolderpath,DocPath,uploadcommonfileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Common, PageName.FundsPage, Workspace.InvestorWorkspace,20)) {
					appLog.info("file is imported successfully: "+uploadcommonfileName+" in :"+commonfolderpath);
						switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"InvestorWorkspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleListOnBasisOfTitle(driver,uploadcommonfileName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(uploadcommonfileName+" is verified.");
							}
						}
				}else {
					appLog.error("file is not imported: "+uploadcommonfileName+" in :"+commonfolderpath);
					sa.assertTrue(false, "file is not imported: "+uploadcommonfileName+" in :"+commonfolderpath);
				}
				switchToDefaultContent(driver);
				if(fp.onlineImport(null, null, null,sharedfolderpath,DocPath,uploadsharedfileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Shared, PageName.FundsPage, Workspace.InvestorWorkspace,20)) {
					appLog.info("file is imported successfully: "+uploadsharedfileName+" in :"+sharedfolderpath);
						switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleListOnBasisOfTitle(driver,uploadsharedfileName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(uploadsharedfileName+" is verified.");
							}
						}
				}else {
					appLog.error("file is not imported: "+uploadsharedfileName+" in :"+sharedfolderpath);
					sa.assertTrue(false, "file is not imported: "+uploadsharedfileName+" in :"+sharedfolderpath);
				}
				switchToDefaultContent(driver);
				if(fp.onlineImport(null, null, null,internalfolderpath,DocPath,uploadinternalfileName, BoxUserName, BoxPassword, OnlineImportFileAddTo.SingleInstitute, WorkSpaceAction.UPLOAD, FolderType.Internal, PageName.FundsPage, Workspace.InvestorWorkspace,20)) {
					appLog.info("file is imported successfully: "+uploadinternalfileName+" in :"+internalfolderpath);
						switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
						if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"Fundraising workspace refresh button", action.SCROLLANDBOOLEAN)) {
							List<String>result=compareMultipleListOnBasisOfTitle(driver,uploadinternalfileName,fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
							if(!result.isEmpty()) {
								for(int i=0;i<result.size();i++) {
									sa.assertTrue(false, result.get(i));
								}
							}else {
								appLog.info(uploadinternalfileName+" is verified.");
							}
						}
				}else {
					appLog.error("file is not imported: "+uploadinternalfileName+" in :"+internalfolderpath);
					sa.assertTrue(false, "file is not imported: "+uploadinternalfileName+" in :"+internalfolderpath);
				}
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(INV_UniqueDocument, INV_DocumentViews,
							INV_DocumentDownload, INV_DocumentNotViewedOrDownload, INV_ContactGrantedAccess,INV_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.InvestorWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(null, false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1, M14Contact1_ActivityCount+"<break>"+M14Contact2_ActivityCount);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot import files in InvestorWorkspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot import files in InvestorWorkspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot import files in InvestorWorkspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot import files in InvestorWorkspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc049_approvePendingDocAndCheckImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTc="M14tc048_onlineImportDocInINV";
		String uploadstdfileName=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,dependONTc,excelLabel.UploadedFileStandard);
		String uploadcommonfileName=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,dependONTc,excelLabel.UploadedFileCommon);
		String uploadsharedfileName=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,dependONTc,excelLabel.UploadedFileShared);
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.InvestorWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(click(driver,fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approvals icon", action.SCROLLANDBOOLEAN)) {
					if(fp.selectPendingFilesToApproveAndCountApprovedFiles(driver, uploadstdfileName+"<break>"+uploadcommonfileName+"<break>"+uploadsharedfileName).isEmpty()) {
						if(click(driver, fp.getManageApprovalsApproveBtn(30), "approve button", action.SCROLLANDBOOLEAN)) {
							if(click(driver, fp.getManageApprovalsConfirmYesBtn(30), "manage approvals confirm yes button", action.SCROLLANDBOOLEAN)) {
								CommonVariables.INV_UniqueDocument = CommonVariables.INV_UniqueDocument+BaseLib.totalUploadedFiles;
								CommonVariables.INV_DocumentNotViewedOrDownload = CommonVariables.INV_DocumentNotViewedOrDownload+BaseLib.totalUploadedFiles;
								ExcelUtils.writeData(INV_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.INV_Value);
								ExcelUtils.writeData(INV_DocumentNotViewedOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.INV_Value);
								if(click(driver, fp.getManageApprovalsCloseBtn(30), "manage approvals approved file close button", action.SCROLLANDBOOLEAN)) {
									if (click(driver, fp.getManageAppCancelBtn(60), "cancel button manage approvals", action.SCROLLANDBOOLEAN)) {
										appLog.error("manage approvals pop is closed");
									}
									else {
										appLog.error("cancel button is not clickable on manage approvals window");
										sa.assertTrue(false, "cancel button is not clickable on manage approvals window");
									}
								}else {
									appLog.error("Not able to click on manage approvals close button so cannot close manage approvals pop up");
									sa.assertTrue(false, "Not able to click on manage approvals close button so cannot close manage approvals pop up");
									switchToDefaultContent(driver);
									driver.navigate().refresh();
									switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
								}
							}else {
								appLog.error("Not able to click on manage approvals confirm yes button so cannot approve pending files");
								sa.assertTrue(false, "Not able to click on manage approvals confirm yes button so cannot approve pending files");
							}
						}else {
							appLog.error("Not able to click on manage approvals approve button so cannot approve pending files");
							sa.assertTrue(false, "Not able to click on manage approvals approve button so cannot approve pending files");
						}
					}else {
						appLog.error("Not able to approve all document from manage approval");
						sa.assertTrue(false, "Not able to approve all document from manage approval");
					}
					
				}else {
					appLog.error("Not able to click on manage approvals icon so cannot approve pending document");
					sa.assertTrue(false, "Not able to click on manage approvals icon so cannot approve pending document");
				}
				if(click(driver,fp.getManageApprovalIcon(Workspace.InvestorWorkspace, 30), "manage approvals icon", action.SCROLLANDBOOLEAN)) {
					if(fp.selectAllPendingFilesToApprove(WorkSpaceAction.UPLOAD).isEmpty()) {
						appLog.info("all pending document is approved");
					}else {
						appLog.error("Not able to approve all document from manage approval");
						sa.assertTrue(false, "Not able to approve all document from manage approval");
					}
					
				}else {
					appLog.error("Not able to click on manage approvals icon so cannot approve pending document");
					sa.assertTrue(false, "Not able to click on manage approvals icon so cannot approve pending document");
				}
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(INV_UniqueDocument, INV_DocumentViews,
							INV_DocumentDownload, INV_DocumentNotViewedOrDownload, INV_ContactGrantedAccess,INV_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.InvestorWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(uploadcommonfileName+"<break>"+uploadsharedfileName+"<break>"+uploadstdfileName, true);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1, M14Contact1_ActivityCount+"<break>"+M14Contact2_ActivityCount);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot import files in InvestorWorkspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot import files in InvestorWorkspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot import files in InvestorWorkspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot import files in InvestorWorkspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc050_1_viewOnlineImportDocFromInvestorSideAndCheckImpact() {
		LoginPageBusinessLayer lp=new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTC="M14tc048_onlineImportDocInINV";
		String sharedfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.SharedPath);
		String stdfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.StandardPath);
		String commonfolderpath=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name,currentlyExecutingTC,excelLabel.CommonPath);
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileStandard);
		String[] SplitedStdFilesName=stdfilesName.split("<break>");
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileCommon);
		String [] SplitedCommonFilesName=commonfilesName.split("<break>");
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTC, excelLabel.UploadedFileShared);
		String [] SplitedSharedFileName=shrdfilesName.split("<break>");
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.InvestorWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in InvestorWorkspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in InvestorWorkspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in InvestorWorkspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in InvestorWorkspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp=new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		InvestorFirmPageBusinesslayer ifp = new InvestorFirmPageBusinesslayer(driver);
		lp.investorLogin(M14Contact1EmailId, adminPassword);
		if (ifp.clickOnInvestmentsTab(investorSideWorkSpace.CurrentInvestment)) {
			if(fp.verifyFolderPathdummy(stdfolderpath,null,M14LimitedPartner1,null,PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace,20)) {
				if(fp.viewDownloadDocument(SplitedStdFilesName[0], "M14Contact1", viewDownload.view, Workspace.CurrentInvestment, false)) {
					appLog.info("clicked on upload file: "+SplitedStdFilesName[0]);
				}else {
					appLog.error("Not able to view upload file: "+SplitedStdFilesName[0]);
					sa.assertTrue(false, "Not able to view upload file: "+SplitedStdFilesName[0]);
				}
				if(fp.viewDownloadDocument(SplitedStdFilesName[1], "M14Contact1", viewDownload.view, Workspace.CurrentInvestment, false)) {
					appLog.info("clicked on upload file: "+SplitedStdFilesName[1]);
				}else {
					appLog.error("Not able to view upload file: "+SplitedStdFilesName[1]);
					sa.assertTrue(false, "Not able to view upload file: "+SplitedStdFilesName[1]);
				}
				if(fp.viewDownloadDocument(SplitedStdFilesName[2], "M14Contact1", viewDownload.view, Workspace.CurrentInvestment, false)) {
					appLog.info("clicked on upload file: "+SplitedStdFilesName[2]);
				}else {
					appLog.error("Not able to view upload file: "+SplitedStdFilesName[2]);
					sa.assertTrue(false, "Not able to view upload file: "+SplitedStdFilesName[2]);
				}
			}else {
				appLog.error("Not able to click on folder: "+stdfolderpath+" so cannot click on file name: "+SplitedStdFilesName[0]+", "+SplitedStdFilesName[1]+", "+SplitedStdFilesName[2]);
				sa.assertTrue(false,"Not able to click on folder: "+stdfolderpath+" so cannot click on file name: "+SplitedStdFilesName[0]+", "+SplitedStdFilesName[1]+", "+SplitedStdFilesName[2]);
			}
			if(fp.verifyFolderPathdummy(commonfolderpath,null,null,null,PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace,20)) {
				if(fp.viewDownloadDocument(SplitedCommonFilesName[0], "M14Contact1", viewDownload.view, Workspace.CurrentInvestment, false)) {
					appLog.info("clicked on upload file: "+SplitedCommonFilesName[0]);
				}else {
					appLog.error("Not able to view upload file: "+SplitedCommonFilesName[0]);
					sa.assertTrue(false, "Not able to view upload file: "+SplitedCommonFilesName[0]);
				}
				if(fp.viewDownloadDocument(SplitedCommonFilesName[1], "M14Contact1", viewDownload.view, Workspace.CurrentInvestment, false)) {
					appLog.info("clicked on upload file: "+SplitedCommonFilesName[1]);
				}else {
					appLog.error("Not able to view upload file: "+SplitedCommonFilesName[1]);
					sa.assertTrue(false, "Not able to view upload file: "+SplitedCommonFilesName[1]);
				}
				if(fp.viewDownloadDocument(SplitedCommonFilesName[2], "M14Contact1", viewDownload.view, Workspace.CurrentInvestment, false)) {
					appLog.info("clicked on upload file: "+SplitedCommonFilesName[2]);
				}else {
					appLog.error("Not able to view upload file: "+SplitedCommonFilesName[2]);
					sa.assertTrue(false, "Not able to view upload file: "+SplitedCommonFilesName[2]);
				}
			}else {
				appLog.error("Not able to click on folder: "+commonfolderpath+" so cannot click on file name: "+SplitedCommonFilesName[0]+", "+SplitedCommonFilesName[1]+", "+SplitedCommonFilesName[2]);
				sa.assertTrue(false, "Not able to click on folder: "+commonfolderpath+" so cannot click on file name: "+SplitedCommonFilesName[0]+", "+SplitedCommonFilesName[1]+", "+SplitedCommonFilesName[2]);
			}
			if(fp.verifyFolderPathdummy(sharedfolderpath,null,null,null,PageName.CurrentInvestmentPgae, Workspace.InvestorWorkspace,20)) {
				if(fp.viewDownloadDocument(SplitedSharedFileName[0], "M14Contact1", viewDownload.view, Workspace.CurrentInvestment, false)) {
					appLog.info("clicked on upload file: "+SplitedSharedFileName[0]);
				}else {
					appLog.error("Not able to view upload file: "+SplitedSharedFileName[0]);
					sa.assertTrue(false, "Not able to view upload file: "+SplitedSharedFileName[0]);
				}
				if(fp.viewDownloadDocument(SplitedSharedFileName[1], "M14Contact1", viewDownload.view, Workspace.CurrentInvestment, false)) {
					appLog.info("clicked on upload file: "+SplitedSharedFileName[1]);
				}else {
					appLog.error("Not able to view upload file: "+SplitedSharedFileName[1]);
					sa.assertTrue(false, "Not able to view upload file: "+SplitedSharedFileName[1]);
				}
				if(fp.viewDownloadDocument(SplitedSharedFileName[2], "M14Contact1", viewDownload.view, Workspace.CurrentInvestment, false)) {
					appLog.info("clicked on upload file: "+SplitedSharedFileName[2]);
				}else {
					appLog.error("Not able to view upload file: "+SplitedSharedFileName[2]);
					sa.assertTrue(false, "Not able to view upload file: "+SplitedSharedFileName[2]);
				}
			}else {
				appLog.error("Not able to click on folder: "+sharedfolderpath+" so cannot click on file name: "+SplitedSharedFileName[0]+", "+SplitedSharedFileName[1]+", "+SplitedSharedFileName[2]);
				sa.assertTrue(false, "Not able to click on folder: "+sharedfolderpath+" so cannot click on file name: "+SplitedSharedFileName[0]+","+SplitedSharedFileName[1]+","+SplitedSharedFileName[2]);
			}
		} else {
			appLog.info("Not able to click on Potential Investment tab");
			sa.assertTrue(false, "Not able to click on Potential Investment tab");
		}
		lp.investorLogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc050_2_checkImpactAtIPAnalytics() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTc="M14tc048_onlineImportDocInINV";
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileStandard);
		String[] SplitedStdFilesName=stdfilesName.split("<break>");
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileCommon);
		String [] SplitedCommonFilesName=commonfilesName.split("<break>");
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileShared);
		String [] SplitedSharedFileName=shrdfilesName.split("<break>");		
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "InvestorWorkspace view");
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(INV_UniqueDocument, INV_DocumentViews,
							INV_DocumentDownload, INV_DocumentNotViewedOrDownload, INV_ContactGrantedAccess,INV_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.InvestorWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(SplitedStdFilesName[3]+"<break>"+SplitedCommonFilesName[3]+"<break>"+SplitedSharedFileName[3], false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1, M14Contact1_ActivityCount+"<break>"+M14Contact2_ActivityCount);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot verify ip analytics in InvestorWorkspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot verify ip analytics in InvestorWorkspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot verify ip analytics in InvestorWorkspace ");
			sa.assertTrue(false, "Not able to click on fund tab so cannot verify ip analytics in InvestorWorkspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M14tc051_verifySortingInAnalyticsPopUpsInINV() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				scrollDownThroughWebelement(driver, fp.getWorkspaceSectionView(Workspace.InvestorWorkspace, 30), "Investor workspace view");
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					if(click(driver, fp.getIPAnalyticsMostViewedDocumentLink(20), "most viewed document link", action.BOOLEAN)) {
						if(click(driver, fp.getMostViewedDocumentHeaderTextList().get(0),"most viewed document header text", action.BOOLEAN)) {
							if(click(driver, fp.getMostViewedDocumentSortingIconList().get(0), "sorting icon", action.BOOLEAN)) {
								click(driver, fp.getMostViewedDocumentSortingIconList().get(0), "sorting icon", action.BOOLEAN);
								if(fp.checkSorting(SortOrder.Assecending, fp.getMostViewedDocumentName())) {
									appLog.info("document name is verified in Assecending order");
								}else {
									appLog.error("document name is not verified in Assecending order");
									sa.assertTrue(false, "document name is not verified in Assecending order");
								}
							}else {
								appLog.error("Not able to click on sorting icon so cannot check sorint in Assecending Order");
								sa.assertTrue(false, "Not able to click on sorting icon so cannot check sorint in Assecending Order");
							}
							if(click(driver, fp.getMostViewedDocumentSortingIconList().get(0), "sorting icon", action.BOOLEAN)) {
								
								if(fp.checkSorting(SortOrder.Decending, fp.getMostViewedDocumentName())) {
									appLog.info("document name is verified in Decending order");
								}else {
									appLog.error("document name is not verified in Decending order");
									sa.assertTrue(false, "document name is not verified in Decending order");
								}
							}else {
								appLog.error("Not able to click on sorting icon so cannot check sorint in Decending Order");
								sa.assertTrue(false, "Not able to click on sorting icon so cannot check sorint in Decending Order");
							}
						}else {
							appLog.error("Not able to click most viewed document header so cannot click on sorting icon and check soritng");
							sa.assertTrue(false, "Not able to click most viewed document header so cannot click on sorting icon and check soritng");
						}
						if(click(driver, fp.getMostViewedDocumentHeaderTextList().get(1),"most viewed document header text", action.BOOLEAN)) {
							if(click(driver, fp.getMostViewedDocumentSortingIconList().get(1), "sorting icon", action.BOOLEAN)) {
								if(fp.checkSorting(SortOrder.Decending, fp.getMostViewDocumentsViews(10))) {
									appLog.info("document Views is verified in Decending order");
								}else {
									appLog.error("document Views is not verified in Decending order");
									sa.assertTrue(false, "document Views is not verified in Decending order");
								}
							}else {
								appLog.error("Not able to click on sorting icon so cannot check sorint in Decending Order");
								sa.assertTrue(false, "Not able to click on sorting icon so cannot check sorint in Decending Order");
							}
							if(click(driver, fp.getMostViewedDocumentSortingIconList().get(1), "sorting icon", action.BOOLEAN)) {
								if(fp.checkSorting(SortOrder.Assecending, fp.getMostViewDocumentsViews(10))) {
									appLog.info("document Views is verified in Ascending order");
								}else {
									appLog.error("document Views is not verified in Ascending order");
									sa.assertTrue(false, "document Views is not verified in Ascending order");
								}
							}else {
								appLog.error("Not able to click on sorting icon so cannot check sorint in Ascending Order");
								sa.assertTrue(false, "Not able to click on sorting icon so cannot check sorint in Ascending Order");
							}
							
						}else {
							appLog.error("Not able to click most viewed document headder so cannot click on sorting icon and check soritng");
							sa.assertTrue(false, "Not able to click most viewed document header so cannot click on sorting icon and check soritng");
						}
						if(click(driver, fp.getMostviewedDocumentCloseBtn(10), "close button", action.BOOLEAN)) {
							appLog.info("click on close button");
						}else {
							appLog.error("Not able to click on close button so cannot close most viewed document pop up");
							sa.assertTrue(false, "Not able to click on close button so cannot close most viewed document pop up");
						}
					}else {
						appLog.error("Not able to click on most viewed document link so cannot check sorting");
						sa.assertTrue(false, "Not able to click on most viewed document link so cannot check sorting");
					}
					if(click(driver, fp.getIPAnalyticsMostActiveContactsLink(20), "most active contact link", action.BOOLEAN)) {
						if(click(driver, fp.getMostActiveContactHeaderTextList().get(0), "contact name header", action.BOOLEAN)) {
							if(click(driver, fp.getMostActiveDocumentSortingIconList().get(0), "sorting icon", action.BOOLEAN)) {
								if(fp.checkSorting(SortOrder.Decending, fp.getMostActiveContactNameList())) {
									appLog.info("Contact Name is verified in Decending order");
								}else {
									appLog.error("Contact Name is not verified in Decending order");
									sa.assertTrue(false, "Contact Name is not verified in Decending order");
								}
								
							}else {
								appLog.error("Not able to click on sorting icon so cannot check Sorting in Decending order");
								sa.assertTrue(false, "Not able to click on sorting icon so cannot check Sorting in Decending order");
							}
							if(click(driver, fp.getMostActiveDocumentSortingIconList().get(0), "sorting icon", action.BOOLEAN)) {
								if(fp.checkSorting(SortOrder.Assecending, fp.getMostActiveContactNameList())) {
									appLog.info("Contact Name is verified in Assecending order");
								}else {
									appLog.error("Contact Name is not verified in Assecending order");
									sa.assertTrue(false, "Contact Name is not verified in Assecending order");
								}
								
							}else {
								appLog.error("Not able to click on sorting icon so cannot check Sorting in Decending order");
								sa.assertTrue(false, "Not able to click on sorting icon so cannot check Sorting in Decending order");
							}
						}else {
							appLog.error("Not able to click on contact name header so cannot check sorting");
							sa.assertTrue(false, "Not able to click on contact name header so cannot check sorting");
						}
						
						if(click(driver, fp.getMostActiveContactHeaderTextList().get(1), "contact name header", action.BOOLEAN)) {
							if(click(driver, fp.getMostActiveDocumentSortingIconList().get(1), "sorting icon", action.BOOLEAN)) {
								if(fp.checkSorting(SortOrder.Decending, fp.getMostActiveContactFirmNameList())) {
									appLog.info("Firm Name is verified in Decending order");
								}else {
									appLog.error("Firm Name is not verified in Decending order");
									sa.assertTrue(false, "Firm Name is not verified in Decending order");
								}
								
							}else {
								appLog.error("Not able to click on sorting icon so cannot check Sorting in Decending order");
								sa.assertTrue(false, "Not able to click on sorting icon so cannot check Sorting in Decending order");
							}
							if(click(driver, fp.getMostActiveDocumentSortingIconList().get(1), "sorting icon", action.BOOLEAN)) {
								if(fp.checkSorting(SortOrder.Assecending, fp.getMostActiveContactFirmNameList())) {
									appLog.info("Contact Name is verified in Assecending order");
								}else {
									appLog.error("Contact Name is not verified in Assecending order");
									sa.assertTrue(false, "Contact Name is not verified in Assecending order");
								}
							}else {
								appLog.error("Not able to click on sorting icon so cannot check Sorting in Decending order");
								sa.assertTrue(false, "Not able to click on sorting icon so cannot check Sorting in Decending order");
							}
						}else {
							appLog.error("Not able to click on Firm Name header so cannot check sorting");
							sa.assertTrue(false, "Not able to click on Firm Name header so cannot check sorting");
						}
						if(click(driver, fp.getMostActiveContactHeaderTextList().get(2), "contact name header", action.BOOLEAN)) {
							if(click(driver, fp.getMostActiveDocumentSortingIconList().get(2), "sorting icon", action.BOOLEAN)) {
								if(fp.checkSorting(SortOrder.Decending, fp.getMostActiveContactActivityCountList())) {
									appLog.info("Activity Count is verified in Decending order");
								}else {
									appLog.error("Activity Count is not verified in Decending order");
									sa.assertTrue(false, "Activity Count is not verified in Decending order");
								}
								
							}else {
								appLog.error("Not able to click on sorting icon so cannot check Sorting in Decending order");
								sa.assertTrue(false, "Not able to click on sorting icon so cannot check Sorting in Decending order");
							}
							if(click(driver, fp.getMostActiveDocumentSortingIconList().get(2), "sorting icon", action.BOOLEAN)) {
								if(fp.checkSorting(SortOrder.Assecending, fp.getMostActiveContactActivityCountList())) {
									appLog.info("Activity Count is verified in Assecending order");
								}else {
									appLog.error("Activity Count is not verified in Assecending order");
									sa.assertTrue(false, "Activity Count is not verified in Assecending order");
								}
							}else {
								appLog.error("Not able to click on sorting icon so cannot check Sorting in Decending order");
								sa.assertTrue(false, "Not able to click on sorting icon so cannot check Sorting in Decending order");
							}
						}else {
							appLog.error("Not able to click on Activity Count header so cannot check sorting");
							sa.assertTrue(false, "Not able to click on Activity Count header so cannot check sorting");
						}
						if(click(driver, fp.getMostActiveContactCloseBtn(10), "close button", action.BOOLEAN)) {
							appLog.info("click on close button");
						}else {
							appLog.error("Not able to click on close button so cannot close most contact pop up");
							sa.assertTrue(false, "Not able to click on close button so cannot close most active contact pop up");
						}
							
					}else {
						appLog.error("Not able to click on most active contact link so cannot check sorting");
						sa.assertTrue(false, "Not able to click on most active contact link so cannot check sorting");
					}
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot upload files in fundraising workspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot upload files in fundraising workspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot upload files in fundraising workspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc052_deleteDocAndCheckImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer con = new ContactPageBusinessLayer(driver);
		String dependONTc="M14tc048_onlineImportDocInINV";
		String sharedfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,dependONTc,excelLabel.SharedPath);
		String commonfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,dependONTc,excelLabel.CommonPath);
		String standardfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,dependONTc,excelLabel.StandardPath);
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileStandard);
		String[] SplitedStdFilesName=stdfilesName.split("<break>");
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileCommon);
		String [] SplitedCommonFilesName=commonfilesName.split("<break>");
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileShared);
		String [] SplitedSharedFileName=shrdfilesName.split("<break>");	
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.InvestorWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(fp.verifyFolderPathdummy(sharedfolderpath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 20)) {
					if (fp.clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Delete,SplitedSharedFileName[0],Workspace.InvestorWorkspace, 60,
							"Yes")) {
						PublicFlag = false;
						if (click(driver, fp.getDocumentDeleteYesBtn(Workspace.InvestorWorkspace,60), "Yes", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on File Delete Yes Button");
							INV_UniqueDocument = INV_UniqueDocument - 1;
							INV_DocumentViews = INV_DocumentViews - 1;
							M14Contact1_ActivityCount = String.valueOf((Integer.parseInt(M14Contact1_ActivityCount) - 1));
							ExcelUtils.writeData(INV_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.INV_Value);
							ExcelUtils.writeData(INV_DocumentViews, "IPAnalytics", excelLabel.Statistics, "No of Document Views", excelLabel.INV_Value);
							ExcelUtils.writeData(M14Contact1_ActivityCount, "Contacts", excelLabel.Variable_Name, "M14Contact1", excelLabel.Activity_Count);
							List<String> filesName = createListOutOfString(ExcelUtils.readData("IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.INV_DocumentsName));
							List<String> NewFileList = new CommonLib().removeStringFromList(filesName,SplitedSharedFileName[0]);
							ExcelUtils.writeData(createStringOutOfList(NewFileList), "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.INV_DocumentsName);
							if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
									List<String>result=compareMultipleListOnBasisOfTitle(driver,SplitedSharedFileName[0],fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
									if(!result.isEmpty()) {
										appLog.info("All files are verified: "+SplitedSharedFileName[0]+" document is deleted successfully");
									}else {
										appLog.error(SplitedSharedFileName[0]+" is not deleted from content grid");
										sa.assertTrue(false, SplitedSharedFileName[0]+" is not deleted from content grid");
									}
							}else {
								appLog.error("Not able to click on refresh icon so cannot verify deleted documents.");
								sa.assertTrue(false, "Not able to click on refresh icon so cannot verify deleted documents");
							}
						} else {
							appLog.info("Not able to Click on Delete File Yes Button.");
							sa.assertTrue(false, "Not able to Click on Delete File Yes Button.");
						}
					} else {
						appLog.info("Not able to Click on Delete File Link.");
						sa.assertTrue(false, "Not able to Click on Delete File Link.");
					}
				}else {
					appLog.error("Not able to click on folder : "+sharedfolderpath+" so cannot delete uploaded document: "+SplitedSharedFileName[0]);
					sa.assertTrue(false, "Not able to click on folder : "+sharedfolderpath+" so cannot delete uploaded document: "+SplitedSharedFileName[0]);
				}
				if(fp.verifyFolderPathdummy(commonfolderpath, null, null, null, PageName.FundsPage, Workspace.InvestorWorkspace, 20)) {
					if (fp.clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Delete,SplitedCommonFilesName[0],Workspace.InvestorWorkspace, 60,"Yes")) {
						PublicFlag = false;
						if (click(driver, fp.getDocumentDeleteYesBtn(Workspace.InvestorWorkspace,60), "Yes", action.SCROLLANDBOOLEAN)) {
							appLog.info("Clicked on File Delete Yes Button");
							INV_UniqueDocument = INV_UniqueDocument - 1;
							INV_DocumentViews = INV_DocumentViews - 1;
							M14Contact1_ActivityCount = String.valueOf((Integer.parseInt(M14Contact1_ActivityCount) - 1));
							ExcelUtils.writeData(INV_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.INV_Value);
							ExcelUtils.writeData(INV_DocumentViews, "IPAnalytics", excelLabel.Statistics, "No of Document Views", excelLabel.INV_Value);
							ExcelUtils.writeData(M14Contact1_ActivityCount, "Contacts", excelLabel.Variable_Name, "M14Contact1", excelLabel.Activity_Count);
							List<String> filesName = createListOutOfString(ExcelUtils.readData("IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.INV_DocumentsName));
							List<String> NewFileList = new CommonLib().removeStringFromList(filesName,SplitedCommonFilesName[0]);
							ExcelUtils.writeData(createStringOutOfList(NewFileList), "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.INV_DocumentsName);
							if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
									List<String>result=compareMultipleListOnBasisOfTitle(driver,SplitedCommonFilesName[0],fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.FundsPage));
									if(!result.isEmpty()) {
										appLog.info("All files are verified: "+SplitedCommonFilesName[0]+" document is deleted successfully");
									}else {
										appLog.error(SplitedCommonFilesName[0]+" is not deleted from content grid");
										sa.assertTrue(false, SplitedCommonFilesName[0]+" is not deleted from content grid");
									}
							}else {
								appLog.error("Not able to click on refresh icon so cannot verify deleted documents.");
								sa.assertTrue(false, "Not able to click on refresh icon so cannot verify deleted documents");
							}
						} else {
							appLog.info("Not able to Click on Delete File Yes Button.");
							sa.assertTrue(false, "Not able to Click on Delete File Yes Button.");
						}
					} else {
						appLog.info("Not able to Click on Delete File Link.");
						sa.assertTrue(false, "Not able to Click on Delete File Link.");
					}
				}else {
					appLog.error("Not able to click on folder : "+commonfolderpath+" so cannot delete uploaded document: "+SplitedCommonFilesName[0]);
					sa.assertTrue(false, "Not able to click on folder : "+commonfolderpath+" so cannot delete uploaded document: "+SplitedCommonFilesName[0]);
				}
				switchToDefaultContent(driver);
				if(fp.clickOnTab(TabName.ContactTab)) {
					if(con.clickOnCreatedContact(M14Contact1FirstName, M14Contact1LastName, null)) {
						switchToFrame(driver, 30,fp.getFrame(PageName.ContactsPage, 20));
						String[] LPName= {M14LimitedPartner1,M14LimitedPartner2};
						for(int i=0; i<2; i++) {
						if(fp.verifyFolderPathdummy(standardfolderpath,LPName[i], null, M14FundName1, PageName.ContactsPage, Workspace.InvestorWorkspace, 20)) {
								if (fp.clickOnOptionsOfArrowKeyInContentGrid(ContentGridArrowKeyFunctions.Delete,SplitedStdFilesName[0],Workspace.InvestorWorkspace, 60,
										"Yes")) {
									PublicFlag = false;
									if (click(driver, fp.getDocumentDeleteYesBtn(Workspace.InvestorWorkspace,60), "Yes", action.SCROLLANDBOOLEAN)) {
										appLog.info("Clicked on File Delete Yes Button");
										if(i==1) {
											INV_UniqueDocument = INV_UniqueDocument - 1;
											INV_DocumentViews = INV_DocumentViews - 1;
											M14Contact1_ActivityCount = String.valueOf((Integer.parseInt(M14Contact1_ActivityCount) - 1));
											ExcelUtils.writeData(INV_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.INV_Value);
											ExcelUtils.writeData(INV_DocumentViews, "IPAnalytics", excelLabel.Statistics, "No of Document Views", excelLabel.INV_Value);
											ExcelUtils.writeData(M14Contact1_ActivityCount, "Contacts", excelLabel.Variable_Name, "M14Contact1", excelLabel.Activity_Count);
											List<String> filesName = createListOutOfString(ExcelUtils.readData("IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.INV_DocumentsName));
											List<String> NewFileList = new CommonLib().removeStringFromList(filesName,SplitedStdFilesName[0]);
											ExcelUtils.writeData(createStringOutOfList(NewFileList), "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.INV_DocumentsName);
										}
										if(click(driver, fp.ContentGridRefreshBtn(Workspace.InvestorWorkspace, 30),"investor workspace refresh button", action.SCROLLANDBOOLEAN)) {
											List<String>result=compareMultipleListOnBasisOfTitle(driver,SplitedStdFilesName[0],fp.getContentGridDocNameListOnBasisOfTitle(Workspace.InvestorWorkspace, PageName.ContactsPage));
											if(!result.isEmpty()) {
												appLog.info("All files are verified: "+SplitedStdFilesName[0]+" document is deleted successfully");
											}else {
												appLog.error(SplitedStdFilesName[0]+" is not deleted from content grid");
												sa.assertTrue(false, SplitedStdFilesName[0]+" is not deleted from content grid");
											}
										}else {
											appLog.error("Not able to click on refresh icon so cannot verify deleted documents.");
											sa.assertTrue(false, "Not able to click on refresh icon so cannot verify deleted documents");
										}
									} else {
										appLog.info("Not able to Click on Delete File Yes Button.");
										sa.assertTrue(false, "Not able to Click on Delete File Yes Button.");
									}
								} else {
									appLog.info("Not able to Click on Delete File Link.");
									sa.assertTrue(false, "Not able to Click on Delete File Link.");
								}
						}else {
							appLog.error("Not able to click on folder path: "+standardfolderpath+" so cannot delete uploaded document");
							sa.assertTrue(false, "Not able to click on folder path: "+standardfolderpath+" so cannot delete uploaded document");
						}
					}
					}else {
						appLog.error("Not able to click on created contact: "+M14Contact1FirstName+" "+M14Contact1LastName+" so cannot delete uloaded document");
						sa.assertTrue(false, "Not able to click on created contact: "+M14Contact1FirstName+" "+M14Contact1LastName+" so cannot delete uloaded document");
					}
				}else {
					appLog.error("NOt able to click on contact tab so cannot delete document");
					sa.assertTrue(false, "NOt able to click on contact tab so cannot delete document");
				}
				switchToDefaultContent(driver);
				if(fp.clickOnTab(TabName.FundsTab)) {
					if(fp.clickOnCreatedFund(M14FundName1)) {
						switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
						if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
							String parentid = switchOnWindow(driver);
							List<String> result = fp.verifyIPAnalyticsTargetValues(INV_UniqueDocument, INV_DocumentViews,
									INV_DocumentDownload, INV_DocumentNotViewedOrDownload, INV_ContactGrantedAccess,INV_ContactNotViewedAnyDocument);
							if (!result.isEmpty()) {
								for (int i = 0; i < result.size(); i++) {
									sa.assertTrue(false, result.get(i));
								}
							} else {
								appLog.info("IP Analytics target value is verified Successfully.");
							}
							result.clear();
							result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.InvestorWorkspace);
							if (!result.isEmpty()) {
								for (int i = 0; i < result.size(); i++) {
									sa.assertTrue(false, result.get(i));
								}
							} else {
								appLog.info("Most View Document popup Error Message is verified.");
							}
							SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(SplitedStdFilesName[3]+"<break>"+SplitedCommonFilesName[3]+"<break>"+SplitedSharedFileName[3], false);
							sa.combineAssertions(documents);
							SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
							sa.combineAssertions(rsult);
							SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1, M14Contact1_ActivityCount+"<break>"+M14Contact2_ActivityCount);
							sa.combineAssertions(activityCount);
							driver.close();
							driver.switchTo().window(parentid);
						} else {
							appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
							sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
						}
					}else {
						appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot verify IP Analytics");
						sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot verify IP Analytics");
					}
				}else {
					appLog.error("Not able to click on fund tab so cannot verify IP Analytics");
					sa.assertTrue(false, "Not able to click on fund tab so cannot verify IP Analytics");
				}
				
				
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot delete document from InvestorWorkspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot delete document from InvestorWorkspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot delete document from InvestorWorkspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot delete document from InvestorWorkspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

	@Test
	public void M14tc053_inviteContact3AndDeleteSharedFolder() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTc="M14tc048_onlineImportDocInINV";
		String sharedfolderpath=ExcelUtils.readData("Filepath", excelLabel.TestCases_Name,dependONTc,excelLabel.SharedPath);
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileStandard);
		String[] SplitedStdFilesName=stdfilesName.split("<break>");
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileCommon);
		String [] SplitedCommonFilesName=commonfilesName.split("<break>");
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileShared);
		String [] SplitedSharedFileName=shrdfilesName.split("<break>");	
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.InvestorWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
				if(fp.inviteContact(null,M14Contact3EmailId,sharedfolderpath, FolderType.Shared,"download", "Yes",null,"All Folders", Workspace.InvestorWorkspace,M14Institution2,"Yes",true)) {
					appLog.info("contact is invites successfully from InvestorWorkspace: "+M14Contact3FirstName+" "+M14Contact3LastName+" from "+sharedfolderpath);
				}else {
					appLog.error("Not able to invite contact from InvestorWorkspace: "+M14Contact3FirstName+" "+M14Contact3LastName+" from "+sharedfolderpath);
					sa.assertTrue(false, "Not able to invite contact from InvestorWorkspace: "+M14Contact3FirstName+" "+M14Contact3LastName+" from "+sharedfolderpath);
				}
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(click(driver, fp.getManageFolderIcon(Workspace.InvestorWorkspace, 30), "Manage folder icon", action.BOOLEAN)){
					String id=null;
					id=fp.getCreatedFolderId(sharedfolderpath, PageName.ManageFolderPopUp, 20);
					System.err.println("id>>>>>>"+id);
					if(id!=null) {
						if(fp.clickOnDeleteFolderButton(id)) {
							if(click(driver, fp.getFolderDeleteYesBtn(Workspace.InvestorWorkspace, PageName.ManageFolderPopUp, 10), "folder delete yes Button", action.BOOLEAN)) {
								appLog.info("clicked on folder delete yes Button");
								ThreadSleep(2000);
								if(fp.getCreatedFolderId(sharedfolderpath, PageName.ManageFolderPopUp, 10)!=null) {
									appLog.info(sharedfolderpath+" is not deleted, folder is visible in manage foler pop up");
									sa.assertTrue(false, sharedfolderpath+" is not deleted, folder is visible in manage foler pop up");
								}else {
									appLog.error(sharedfolderpath+" is deleted successfully, it is not visible in manage foler pop up");
									INV_ContactGrantedAccess=INV_ContactGrantedAccess-1;
									INV_ContactNotViewedAnyDocument=INV_ContactNotViewedAnyDocument-1;
									ExcelUtils.writeData(INV_ContactGrantedAccess, "IPAnalytics", excelLabel.Statistics, "No of Contacts Granted Access", excelLabel.INV_Value);
									ExcelUtils.writeData(INV_ContactNotViewedAnyDocument, "IPAnalytics", excelLabel.Statistics, "No of Contacts who have not Viewed any Document", excelLabel.INV_Value);
									ExcelUtils.writeData("No Access", "Contacts", excelLabel.Variable_Name, "M14Contact3", excelLabel.Contact_Access);
								}
							}else {
								appLog.error("Yes button is not displaying on folder delete pop up so cannot click on Yes button");
								sa.assertTrue(false, "yes button is not displaying on folder delete pop up so cannot click on yes button");
							}
							if(click(driver, fp.getManageFolderCloseButton(Workspace.InvestorWorkspace, 20), "manage folder close button", action.SCROLLANDBOOLEAN)) {
								appLog.info("manage folder pop is closed");
							}else {
								appLog.error("Not able to click on close button so cannot close manage folder pop up");
								sa.assertTrue(false, "Not able to click on close button so cannot close manage folder pop up");
							}
						}else {
							appLog.error("Not able to click on folder: "+sharedfolderpath+" delete icon so cannot delete folder");
							sa.assertTrue(false, "Not able to click on folder: "+sharedfolderpath+" delete icon so cannot delete folder");
						}
					}else {
						appLog.error(sharedfolderpath+" is not available in the manage folder structure so cannot click on folder "+sharedfolderpath+" delete icon");
						sa.assertTrue(false, sharedfolderpath+" is not available in the manage folder structure so cannot click on folder "+sharedfolderpath+" delete icon");
					}
				}else {
					appLog.error("Not able to click on Manage folder icon so cannot delete folder: "+sharedfolderpath);
					sa.assertTrue(false, "Not able to click on Manage folder icon so cannot delete folder: "+sharedfolderpath);
				}
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					String childWindowID = null;
					String childChildWindow = null;
					Set<String> lst1 = driver.getWindowHandles();
					Iterator<String> I1 = lst1.iterator();
					while (I1.hasNext()) {
						String windowID = I1.next();
						if (windowID.equalsIgnoreCase(parentid)) {
							appLog.info("Parent Id is Matched");
						} else {
							childWindowID = windowID;
							appLog.info("Stroged child Window Id");
							break;
						}
					}
					List<String> result = fp.verifyIPAnalyticsTargetValues(INV_UniqueDocument, INV_DocumentViews,
							INV_DocumentDownload, INV_DocumentNotViewedOrDownload, INV_ContactGrantedAccess,INV_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.InvestorWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(SplitedStdFilesName[3]+"<break>"+SplitedCommonFilesName[3]+"<break>"+SplitedSharedFileName[3], false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1, M14Contact1_ActivityCount+"<break>"+M14Contact2_ActivityCount);
					sa.combineAssertions(activityCount);
					if (click(driver, fp.getIPAnalyticsDocumentNotViwedorDownloadLink(30), "Document not viewed or download link",action.SCROLLANDBOOLEAN)) {
						if(!SplitedSharedFileName[3].isEmpty()) {
							WebElement ele=FindElement(driver, "//a[@text='"+SplitedSharedFileName[3]+"']",SplitedSharedFileName[3]+"  file Name link", action.BOOLEAN, 10);
							if(ele!=null) {
								if(click(driver, ele, "file name", action.BOOLEAN)) {
									appLog.info("Clicked on File Name : "+SplitedSharedFileName[3]);
									Set<String>lst11 = driver.getWindowHandles();
									Iterator<String> II1 = lst11.iterator();
									while (II1.hasNext()) {
										String windowID = II1.next();
										if (windowID.equalsIgnoreCase(parentid) || windowID.equalsIgnoreCase(childWindowID)) {
											appLog.info("window Id is Matched");
										} else {
											childChildWindow = windowID;
											appLog.info("Stroged child child Window Id");
											driver.switchTo().window(childChildWindow);
											break;
										}
									}
									WebElement ErrorMsg = fp.getFileNotFoundErrorMessage(60);
									if(ErrorMsg!=null) {
										if(ErrorMsg.getText().trim().equalsIgnoreCase(FundsPageErrorMessage.DocumentNotFoundErrorMsg)) {
											appLog.info(FundsPageErrorMessage.DocumentNotFoundErrorMsg+" error message is matched in file : "+SplitedSharedFileName[3]);
										}else {
											appLog.error(FundsPageErrorMessage.DocumentNotFoundErrorMsg+" error message is not matched in file : "+SplitedSharedFileName[3]);
											sa.assertTrue(false, FundsPageErrorMessage.DocumentNotFoundErrorMsg+" error message is not matched in file : "+SplitedSharedFileName[3]);
										}
									}else {
										appLog.error(FundsPageErrorMessage.DocumentNotFoundErrorMsg+" error message is not displayed in file : "+SplitedSharedFileName[3]);
										sa.assertTrue(false, FundsPageErrorMessage.DocumentNotFoundErrorMsg+" error message is not displayed in file : "+SplitedSharedFileName[3]);
									}
									driver.close();
									driver.switchTo().window(childWindowID);
								}else {
									appLog.error("Not able to click on file Name : "+SplitedSharedFileName[3]);
									sa.assertTrue(false,"Not able to click on file Name : "+SplitedSharedFileName[3]);
								}
							}else {
								appLog.error(SplitedSharedFileName[3]+ "is not available in Document not viewed or download so cannot click on file");
								sa.assertTrue(false, SplitedSharedFileName[3]+ "is not available in Document not viewed or download so cannot click on file");
							}
						}else {
							appLog.error("file name is not written in excel sheet so cannot click on file name: "+SplitedSharedFileName[3]);
							sa.assertTrue(false, "file name is not written in excel sheet so cannot click on file name: "+SplitedSharedFileName[3]);
						}
					if (click(driver, fp.getDocumentNotViewedOrDownloadedCloseBtn(30), "Document not viewed or download Close button",action.SCROLLANDBOOLEAN)) {
						appLog.info("Clicked on Document not viewed or download pop up Close button");
					} else {
						appLog.info("Not able to Click on Document not viewed or download Close button");
						result.add("Not able to Click on Document not viewed or download Close button");
					}

				}else {
					appLog.error("Not able to click on Document not viewed or download link so cannot click on documents");
					sa.assertTrue(false,"Not able to click on Document not viewed or download link so cannot click on document");
				}
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot delete folder: "+sharedfolderpath);
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot delete folder: "+sharedfolderpath);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot delete shared folder in InvestorWorkspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot delete shared folder in InvestorWorkspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc054_removeLPFromManageInstorAndCheckImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTc="M14tc048_onlineImportDocInINV";
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileStandard);
		String[] SplitedStdFilesName=stdfilesName.split("<break>");
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileCommon);
		String [] SplitedCommonFilesName=commonfilesName.split("<break>");
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileShared);
		String [] SplitedSharedFileName=shrdfilesName.split("<break>");	
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.InvestorWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(click(driver,fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60), "manage investor icon", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on manage investor icon");
					ThreadSleep(3000);
					if(fp.selectInstitutionOrLP(M14Institution2+"/"+M14LimitedPartner2, Workspace.InvestorWorkspace, 60)) {
						appLog.info(M14Institution2+"/"+M14LimitedPartner2+" is removed from manage investor");
						INV_ContactGrantedAccess=INV_ContactGrantedAccess-1;
						ExcelUtils.writeData(CommonVariables.INV_ContactGrantedAccess, "IPAnalytics", excelLabel.Statistics, "No of Contacts Granted Access", excelLabel.INV_Value);
						ExcelUtils.writeData("No Access", "Contacts", excelLabel.Variable_Name, "M14Contact2", excelLabel.Contact_Access);
						if(click(driver, fp.getManageInvestorDeletedPopupCloseButton(Workspace.InvestorWorkspace, 60), "manage investor remove pop up close button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on close button");
						}else {
							appLog.error("manage investor remove pop up close buttton is not present");
							sa.assertTrue(false, "manage investor remove pop up close buttton is not present");
						}
					}else {
						appLog.error("Not able to remove LP from manage investor: "+M14Institution2);
						sa.assertTrue(false, "Not able to remove LP from manage investor: "+M14Institution2);
					}
					if(click(driver, fp.getManageInvestorDoneButton(Workspace.InvestorWorkspace, 30), "manage investor close button", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on manage investor close button");
					}else {
						appLog.error("Not able to close manage investor pop up");
						sa.assertTrue(false, "Not able to close manage investor pop up");
					}
					}else {
						appLog.error("Not able to click on manage investor icon so cannot remove institution : "+M14Institution2);
						sa.assertTrue(false, "Not able to click on manage investor icon so cannot remove institution : "+M14Institution2);
					}
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(INV_UniqueDocument, INV_DocumentViews,
							INV_DocumentDownload, INV_DocumentNotViewedOrDownload, INV_ContactGrantedAccess,INV_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.InvestorWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(SplitedStdFilesName[3]+"<break>"+SplitedCommonFilesName[3]+"<break>"+SplitedSharedFileName[3], false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName, updatedInsName, M14Contact1_ActivityCount);
					sa.combineAssertions(activityCount);
					activityCount=fp.verifyMostActiveContactInIPAnalytics(M14Contact2FirstName+" "+M14Contact2LastName, M14Institution1, M14Contact2_ActivityCount);
					try {
						activityCount.assertAll();
					}catch (Throwable e) {
						appLog.info(M14Contact2FirstName+" "+M14Contact2LastName+" is not displayed in Most Active Contact pop up");
					}
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot remove LP: "+M14LimitedPartner2);
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot remove LP: "+M14LimitedPartner2);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot remove LP: "+M14LimitedPartner2);
			sa.assertTrue(false, "Not able to click on fund tab so cannot remove LP: "+M14LimitedPartner2);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc055_addLPFromManageInstorAndCheckImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTc="M14tc048_onlineImportDocInINV";
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileStandard);
		String[] SplitedStdFilesName=stdfilesName.split("<break>");
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileCommon);
		String [] SplitedCommonFilesName=commonfilesName.split("<break>");
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileShared);
		String [] SplitedSharedFileName=shrdfilesName.split("<break>");	
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.InvestorWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(click(driver,fp.getManageInvestorIcon(Workspace.InvestorWorkspace, 60), "manage investor icon", action.SCROLLANDBOOLEAN)) {
					appLog.info("clicked on manage investor icon");
					ThreadSleep(3000);
					if(fp.selectInstitutionOrLP(M14Institution2+"/"+M14LimitedPartner2, Workspace.InvestorWorkspace, 60)) {
						appLog.info(M14Institution2+"/"+M14LimitedPartner2+" is added from manage investor");
						if(click(driver, fp.getManageInvestorAddedPopupCloseButton(Workspace.InvestorWorkspace, 60), "manage investor add pop up close button", action.SCROLLANDBOOLEAN)) {
							appLog.info("clicked on close button");
						}else {
							appLog.error("manage investor add pop up close buttton is not present");
							sa.assertTrue(false, "manage investor add pop up close buttton is not present");
						}
					}else {
						appLog.error("Not able to add institution from manage investor: "+M14Institution2);
						sa.assertTrue(false, "Not able to add institution from manage investor: "+M14Institution2);
					}
					if(click(driver, fp.getManageInvestorDoneButton(Workspace.InvestorWorkspace, 30), "manage investor close button", action.SCROLLANDBOOLEAN)) {
						appLog.info("clicked on manage investor close button");
					}else {
						appLog.error("Not able to close manage investor pop up");
						sa.assertTrue(false, "Not able to close manage investor pop up");
					}
					switchToDefaultContent(driver);
					if(fp.inviteContact(M14Institution2+"/"+M14LimitedPartner2,M14Contact2EmailId,null, FolderType.Standard,"upload", "Yes",null,null, Workspace.InvestorWorkspace,M14Contact2EmailId,"Yes",false)) {
						appLog.info("contact is invites successfully from fundraising workspace: "+M14Contact3FirstName+" "+M14Contact3LastName+" from "+M14Institution2+"/"+M14LimitedPartner2);
					}else {
						appLog.error("Not able to invite contact from fundraising workspace: "+M14Contact3FirstName+" "+M14Contact3LastName+" from "+M14Institution2+"/"+M14LimitedPartner2);
						sa.assertTrue(false, "Not able to invite contact from fundraising workspace: "+M14Contact3FirstName+" "+M14Contact3LastName+" from "+M14Institution2+"/"+M14LimitedPartner2);
					}
					switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
					}else {
						appLog.error("Not able to click on manage investor icon so cannot add institution : "+M14Institution2);
						sa.assertTrue(false, "Not able to click on manage investor icon so cannot add institution : "+M14Institution2);
					}
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(INV_UniqueDocument, INV_DocumentViews,
							INV_DocumentDownload, INV_DocumentNotViewedOrDownload, INV_ContactGrantedAccess,INV_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.InvestorWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(SplitedStdFilesName[3]+"<break>"+SplitedCommonFilesName[3]+"<break>"+SplitedSharedFileName[3], false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1, M14Contact1_ActivityCount+"<break>"+M14Contact2_ActivityCount);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot add LP: "+M14LimitedPartner2);
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot add LP: "+M14LimitedPartner2);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot add LP: "+M14LimitedPartner2);
			sa.assertTrue(false, "Not able to click on fund tab so cannot add LP: "+M14LimitedPartner2);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc056_removeContactAccessFromContactPageAndCheckImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		ContactPageBusinessLayer con = new ContactPageBusinessLayer(driver);
		String dependONTc="M14tc048_onlineImportDocInINV";
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileStandard);
		String[] SplitedStdFilesName=stdfilesName.split("<break>");
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileCommon);
		String [] SplitedCommonFilesName=commonfilesName.split("<break>");
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileShared);
		String [] SplitedSharedFileName=shrdfilesName.split("<break>");
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.InvestorWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot read and write ip analytics count");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot read and write ip analytics count");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot read and write ip analytics count");
			sa.assertTrue(false, "Not able to click on fund tab so cannot read and write ip analytics count");
		}
		switchToDefaultContent(driver);
		if(con.clickOnTab(TabName.ContactTab)) {
			if(con.clickOnCreatedContact(M14Contact1FirstName, M14Contact1LastName, null)) {
				switchToFrame(driver, 30,con.getFrame(PageName.ContactsPage, 30));
				if(con.removeContactAccessFromContactPage(M14FundName1, Workspace.InvestorWorkspace)) {
					appLog.info(M14Contact1FirstName+" "+M14Contact1LastName+" Contact Access is removed");
					INV_UniqueDocument=INV_UniqueDocument+2;
					INV_DocumentViews=INV_DocumentViews+2;
					INV_DocumentDownload = INV_DocumentDownload+1;
					INV_DocumentNotViewedOrDownload=INV_DocumentNotViewedOrDownload+1;
					INV_ContactGrantedAccess=INV_ContactGrantedAccess-1;
					ExcelUtils.writeData(CommonVariables.INV_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.INV_Value);
					ExcelUtils.writeData(CommonVariables.INV_DocumentDownload, "IPAnalytics", excelLabel.Statistics, "No of Document Downloads", excelLabel.INV_Value);
					ExcelUtils.writeData(CommonVariables.INV_DocumentViews, "IPAnalytics", excelLabel.Statistics, "No of Document Views", excelLabel.INV_Value);
					ExcelUtils.writeData(CommonVariables.INV_DocumentNotViewedOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.INV_Value);
					ExcelUtils.writeData(CommonVariables.INV_ContactGrantedAccess, "IPAnalytics", excelLabel.Statistics, "No of Contacts Granted Access", excelLabel.INV_Value);
				}else {
					appLog.error(M14Contact1FirstName+" "+M14Contact1LastName+" Contact Access is not removed");
					sa.assertTrue(false, M14Contact1FirstName+" "+M14Contact1LastName+" Contact Access is not removed");
				}
				switchToDefaultContent(driver);
			}else {
				appLog.error("Not able to click on created contact: "+M14Contact1FirstName+" "+M14Contact1LastName+" so cannot remove contact access");
				sa.assertTrue(false, "Not able to click on created contact: "+M14Contact1FirstName+" "+M14Contact1LastName+" so cannot remove contact access");
			}
		}else {
			appLog.error("Not able to click on contact tab so cannot remove contact access from contact page");
			sa.assertTrue(false, "Not able to click on contact tab so cannot remove contact access from contact page");
		}
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(INV_UniqueDocument, INV_DocumentViews,
							INV_DocumentDownload, INV_DocumentNotViewedOrDownload, INV_ContactGrantedAccess,INV_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.InvestorWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(SplitedStdFilesName[3]+"<break>"+SplitedCommonFilesName[3]+"<break>"+SplitedSharedFileName[3], false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName, updatedInsName, M14Contact1_ActivityCount);
					try {
						activityCount.assertAll();
					}catch (Throwable e) {
						appLog.error(updatedContactFName+" "+updatedContactLName+" is not displayed in Most Active Contact pop up");
					}
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
				
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot remove contact acess from contact page");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot remove contact acess from contact page");
			}
			
		}else {
			appLog.error("Not able to click on fund tab so cannot remove contact acess from contact page");
			sa.assertTrue(false, "Not able to click on fund tab so cannot remove contact acess from contact page");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc057_reInviteContactAccessAndCheckImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTc="M14tc048_onlineImportDocInINV";
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileStandard);
		String[] SplitedStdFilesName=stdfilesName.split("<break>");
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileCommon);
		String [] SplitedCommonFilesName=commonfilesName.split("<break>");
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileShared);
		String [] SplitedSharedFileName=shrdfilesName.split("<break>");	
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.InvestorWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
				if(fp.inviteContact(environment, mode, M14Institution1+"/"+M14LimitedPartner1, M14Contact1EmailId, null, FolderType.Standard,"upload","Yes", null,null, Workspace.InvestorWorkspace, null)) {
					appLog.info("invite contact successfully: "+M14Contact1FirstName+" "+M14Contact1LastName);
					INV_UniqueDocument=INV_UniqueDocument-2;
					INV_DocumentViews=INV_DocumentViews-2;
					INV_DocumentDownload=INV_DocumentDownload-1;
					INV_DocumentNotViewedOrDownload=INV_DocumentNotViewedOrDownload-1;
					INV_ContactGrantedAccess=INV_ContactGrantedAccess+1;
					ExcelUtils.writeData(CommonVariables.INV_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.INV_Value);
					ExcelUtils.writeData(CommonVariables.INV_DocumentDownload, "IPAnalytics", excelLabel.Statistics, "No of Document Downloads", excelLabel.INV_Value);
					ExcelUtils.writeData(CommonVariables.INV_DocumentViews, "IPAnalytics", excelLabel.Statistics, "No of Document Views", excelLabel.INV_Value);
					ExcelUtils.writeData(CommonVariables.INV_DocumentNotViewedOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.INV_Value);
					ExcelUtils.writeData(CommonVariables.INV_ContactGrantedAccess, "IPAnalytics", excelLabel.Statistics, "No of Contacts Granted Access", excelLabel.INV_Value);
				}else {
					appLog.error("Not able to invite contact: "+M14Contact1FirstName+" "+M14Contact1LastName);
					sa.assertTrue(false, "Not able to invite contact: "+M14Contact1FirstName+" "+M14Contact1LastName);
				}
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(INV_UniqueDocument, INV_DocumentViews,
							INV_DocumentDownload, INV_DocumentNotViewedOrDownload, INV_ContactGrantedAccess,INV_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.InvestorWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(SplitedStdFilesName[3]+"<break>"+SplitedCommonFilesName[3]+"<break>"+SplitedSharedFileName[3], false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1, M14Contact1_ActivityCount+"<break>"+M14Contact2_ActivityCount);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot  provide contact access in InvestorWorkspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot provide contact access in InvestorWorkspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot provide contact access in InvestorWorkspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot provide contact access in InvestorWorkspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc058_removeContactAccessAndCheckImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTc="M14tc048_onlineImportDocInINV";
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileStandard);
		String[] SplitedStdFilesName=stdfilesName.split("<break>");
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileCommon);
		String [] SplitedCommonFilesName=commonfilesName.split("<break>");
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileShared);
		String [] SplitedSharedFileName=shrdfilesName.split("<break>");
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.InvestorWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(fp.verifyFolderPathdummy("", M14Institution1, M14LimitedPartner1, null, PageName.FundsPage, Workspace.InvestorWorkspace, 30)) {
					if(fp.revokeContactAccess(M14Contact1EmailId, Workspace.InvestorWorkspace)) {
						appLog.info(M14Contact1EmailId+ " contact access is removed from contact access.");
						INV_UniqueDocument=INV_UniqueDocument+2;
						INV_DocumentViews=INV_DocumentViews+2;
						INV_DocumentDownload = INV_DocumentDownload+1;
						INV_DocumentNotViewedOrDownload=INV_DocumentNotViewedOrDownload+1;
						INV_ContactGrantedAccess=INV_ContactGrantedAccess-1;
						ExcelUtils.writeData(CommonVariables.INV_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.INV_Value);
						ExcelUtils.writeData(CommonVariables.INV_DocumentDownload, "IPAnalytics", excelLabel.Statistics, "No of Document Downloads", excelLabel.INV_Value);
						ExcelUtils.writeData(CommonVariables.INV_DocumentViews, "IPAnalytics", excelLabel.Statistics, "No of Document Views", excelLabel.INV_Value);
						ExcelUtils.writeData(CommonVariables.INV_DocumentNotViewedOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.INV_Value);
						ExcelUtils.writeData(CommonVariables.INV_ContactGrantedAccess, "IPAnalytics", excelLabel.Statistics, "No of Contacts Granted Access", excelLabel.INV_Value);
					}else {
						appLog.error(M14Contact1EmailId+ " contact access is not removed from contact access.");
						sa.assertTrue(false, M14Contact1EmailId+ " contact access is not removed from contact access.");
					}
				}else {
					appLog.error("Not able to click on folderpath: "+M14Institution1+" so cannot remove contact access: "+M14Contact1EmailId);
					sa.assertTrue(false, "Not able to click on folderpath: "+M14Institution1+" so cannot remove contact access: "+M14Contact1EmailId);
				}
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(INV_UniqueDocument, INV_DocumentViews,
							INV_DocumentDownload, INV_DocumentNotViewedOrDownload, INV_ContactGrantedAccess,INV_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.InvestorWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(SplitedStdFilesName[3]+"<break>"+SplitedCommonFilesName[3]+"<break>"+SplitedSharedFileName[3], false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName, updatedInsName, M14Contact1_ActivityCount);
					try {
						activityCount.assertAll();
					}catch (Throwable e) {
						appLog.error(updatedContactFName+" "+updatedContactLName+" is not displayed in Most Active Contact pop up");
					}
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot remove contact access in InvestorWorkspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot remove contact access in InvestorWorkspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot remove contact access in InvestorWorkspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot remove contact access in InvestorWorkspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc059_reInviteContactAccessAndCheckImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTc="M14tc048_onlineImportDocInINV";
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileStandard);
		String[] SplitedStdFilesName=stdfilesName.split("<break>");
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileCommon);
		String [] SplitedCommonFilesName=commonfilesName.split("<break>");
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileShared);
		String [] SplitedSharedFileName=shrdfilesName.split("<break>");	
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.InvestorWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
				if(fp.inviteContact(environment, mode, M14Institution1+"/"+M14LimitedPartner1, M14Contact1EmailId, null, FolderType.Standard,"upload","Yes", null,null, Workspace.InvestorWorkspace, null)) {
					appLog.info("invite contact successfully: "+M14Contact1FirstName+" "+M14Contact1LastName);
					INV_UniqueDocument=INV_UniqueDocument-2;
					INV_DocumentViews=INV_DocumentViews-2;
					INV_DocumentDownload=INV_DocumentDownload-1;
					INV_DocumentNotViewedOrDownload=INV_DocumentNotViewedOrDownload-1;
					INV_ContactGrantedAccess=INV_ContactGrantedAccess+1;
					ExcelUtils.writeData(CommonVariables.INV_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.INV_Value);
					ExcelUtils.writeData(CommonVariables.INV_DocumentDownload, "IPAnalytics", excelLabel.Statistics, "No of Document Downloads", excelLabel.INV_Value);
					ExcelUtils.writeData(CommonVariables.INV_DocumentViews, "IPAnalytics", excelLabel.Statistics, "No of Document Views", excelLabel.INV_Value);
					ExcelUtils.writeData(CommonVariables.INV_DocumentNotViewedOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.INV_Value);
					ExcelUtils.writeData(CommonVariables.INV_ContactGrantedAccess, "IPAnalytics", excelLabel.Statistics, "No of Contacts Granted Access", excelLabel.INV_Value);
				}else {
					appLog.error("Not able to invite contact: "+M14Contact1FirstName+" "+M14Contact1LastName);
					sa.assertTrue(false, "Not able to invite contact: "+M14Contact1FirstName+" "+M14Contact1LastName);
				}
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(INV_UniqueDocument, INV_DocumentViews,
							INV_DocumentDownload, INV_DocumentNotViewedOrDownload, INV_ContactGrantedAccess,INV_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.InvestorWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(SplitedStdFilesName[3]+"<break>"+SplitedCommonFilesName[3]+"<break>"+SplitedSharedFileName[3], false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName+"<break>"+M14Contact2FirstName+" "+M14Contact2LastName, updatedInsName+"<break>"+M14Institution1, M14Contact1_ActivityCount+"<break>"+M14Contact2_ActivityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot  provide contact access in InvestorWorkspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot provide contact access in InvestorWorkspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot provide contact access in InvestorWorkspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot provide contact access in InvestorWorkspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc060_closeInvestorWorkSpaceAndCheckImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTc="M14tc048_onlineImportDocInINV";
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileStandard);
		String[] SplitedStdFilesName=stdfilesName.split("<break>");
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileCommon);
		String [] SplitedCommonFilesName=commonfilesName.split("<break>");
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileShared);
		String [] SplitedSharedFileName=shrdfilesName.split("<break>");	
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.InvestorWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
			}else {
				appLog.error("Not able to click on created fund: "+M14FundName1+" so cannot read and write ip analytics count");
				sa.assertTrue(false, "Not able to click on created fund: "+M14FundName1+" so cannot read and write ip analytics count");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot read and write ip analytics count");
			sa.assertTrue(false, "Not able to click on fund tab so cannot read and write ip analytics count");
		}
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(fp.closeWorkSpace(Workspace.InvestorWorkspace, 60)) {
					appLog.info("Fundraising Workspace is closed");
					INV_UniqueDocument=INV_UniqueDocument+2;
					INV_DocumentViews=INV_DocumentViews+2;
					INV_DocumentDownload=INV_DocumentDownload+1;
					INV_DocumentNotViewedOrDownload=INV_DocumentNotViewedOrDownload+1;
					INV_ContactGrantedAccess=INV_ContactGrantedAccess-2;
					ExcelUtils.writeData(CommonVariables.INV_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.INV_Value);
					ExcelUtils.writeData(CommonVariables.INV_DocumentDownload, "IPAnalytics", excelLabel.Statistics, "No of Document Downloads", excelLabel.INV_Value);
					ExcelUtils.writeData(CommonVariables.INV_DocumentViews, "IPAnalytics", excelLabel.Statistics, "No of Document Views", excelLabel.INV_Value);
					ExcelUtils.writeData(CommonVariables.INV_DocumentNotViewedOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.INV_Value);
					ExcelUtils.writeData(CommonVariables.INV_ContactGrantedAccess, "IPAnalytics", excelLabel.Statistics, "No of Contacts Granted Access", excelLabel.INV_Value);
				}else {
					appLog.error("InvestorWorkspace is not closed");
					sa.assertTrue(false, "InvestorWorkspace is not closed");
				}
			}else {
				appLog.error("Not able to click on created fund: "+M14FundName1+" so cannot close InvestorWorkspace");
				sa.assertTrue(false, "Not able to click on created fund: "+M14FundName1+" so cannot close InvestorWorkspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot close InvestorWorkspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot close InvestorWorkspace");
		}	
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(INV_UniqueDocument, INV_DocumentViews,
							INV_DocumentDownload, INV_DocumentNotViewedOrDownload, INV_ContactGrantedAccess,INV_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.InvestorWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(SplitedStdFilesName[3]+"<break>"+SplitedCommonFilesName[3]+"<break>"+SplitedSharedFileName[3], false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(null,null,null);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created fund: "+M14FundName1+" so cannot close INV");
				sa.assertTrue(false, "Not able to click on created fund: "+M14FundName1+" so cannot close INV");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot close INV");
			sa.assertTrue(false, "Not able to click on fund tab so cannot close INV");
		}	
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc061_againInviteContactAccessAndCheckImpact() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String dependONTc="M14tc048_onlineImportDocInINV";
		String stdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileStandard);
		String[] SplitedStdFilesName=stdfilesName.split("<break>");
		String commonfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileCommon);
		String [] SplitedCommonFilesName=commonfilesName.split("<break>");
		String shrdfilesName=ExcelUtils.readData("FilePath", excelLabel.TestCases_Name, dependONTc, excelLabel.UploadedFileShared);
		String [] SplitedSharedFileName=shrdfilesName.split("<break>");	
		String updatedContactFName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_fName);
		String updatedContactLName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.ContactNew_lName);
		String updatedInsName=ExcelUtils.readData("Contacts", excelLabel.Variable_Name,"M14Contact1", excelLabel.Contact_updated_firmname);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.InvestorWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
				if(fp.inviteContact(environment, mode, M14Institution1+"/"+M14LimitedPartner1, M14Contact1EmailId, null, FolderType.Standard,"upload","Yes", null,null, Workspace.InvestorWorkspace, null)) {
					appLog.info("invite contact successfully: "+M14Contact1FirstName+" "+M14Contact1LastName);
					INV_UniqueDocument=INV_UniqueDocument-2;
					INV_DocumentViews=INV_DocumentViews-2;
					INV_DocumentDownload=INV_DocumentDownload-1;
					INV_DocumentNotViewedOrDownload=INV_DocumentNotViewedOrDownload-1;
					INV_ContactGrantedAccess=INV_ContactGrantedAccess+1;
					ExcelUtils.writeData(CommonVariables.INV_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.INV_Value);
					ExcelUtils.writeData(CommonVariables.INV_DocumentDownload, "IPAnalytics", excelLabel.Statistics, "No of Document Downloads", excelLabel.INV_Value);
					ExcelUtils.writeData(CommonVariables.INV_DocumentViews, "IPAnalytics", excelLabel.Statistics, "No of Document Views", excelLabel.INV_Value);
					ExcelUtils.writeData(CommonVariables.INV_DocumentNotViewedOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.INV_Value);
					ExcelUtils.writeData(CommonVariables.INV_ContactGrantedAccess, "IPAnalytics", excelLabel.Statistics, "No of Contacts Granted Access", excelLabel.INV_Value);
				}else {
					appLog.error("Not able to invite contact: "+M14Contact1FirstName+" "+M14Contact1LastName);
					sa.assertTrue(false, "Not able to invite contact: "+M14Contact1FirstName+" "+M14Contact1LastName);
				}
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(INV_UniqueDocument, INV_DocumentViews,
							INV_DocumentDownload, INV_DocumentNotViewedOrDownload, INV_ContactGrantedAccess,INV_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics("document","view",Workspace.InvestorWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(SplitedStdFilesName[3]+"<break>"+SplitedCommonFilesName[3]+"<break>"+SplitedSharedFileName[3], false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(updatedContactFName+" "+updatedContactLName, updatedInsName, M14Contact1_ActivityCount);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot invite contact access in InvestorWorkspace");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot invite contact access in InvestorWorkspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot invite contact access in InvestorWorkspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot invite contact access in InvestorWorkspace");
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc062_downloadReportFromExportTabInIPAnalytics() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					System.err.println("Parent wind ID: "+parentid);
					String childWindowID = null;
					String childChildWindow = null;
					Set<String> lst = driver.getWindowHandles();
					Iterator<String> I1 = lst.iterator();
					while (I1.hasNext()) {
						String windowID = I1.next();
						if (windowID.equalsIgnoreCase(parentid)) {
							appLog.info("Parent Id is Matched");
						} else {
							childWindowID = windowID;
							appLog.info("Storged child Window Id");
							break;
						}
					}
					List<WebElement> tabs = fp.getIPAnalyticsListofTab();
					String tab1 = tabs.get(1).getText().trim();
					if (click(driver, tabs.get(1), "DR Analytics Tab Link " + tab1, action.BOOLEAN)) {
						List<WebElement> exportLinks = fp.getIPAnalyticsListOfExportText();
						if (click(driver, exportLinks.get(0), "Workspace Documents Report",
								action.BOOLEAN)) {
							appLog.info("Clicked on Workspace Documents Report");
							ThreadSleep(10000);
							appLog.info("Workspace Documents Report File is downloaded");
							System.err.println("URL Of small window: "+getURL(driver, 2)+" "+driver.getWindowHandle());
							Robot rob;
							try {
								rob = new Robot();
								ThreadSleep(10000);
								rob.keyPress(KeyEvent.VK_ENTER);
								rob.keyRelease(KeyEvent.VK_ENTER);
								ThreadSleep(10000);
								rob.keyPress(KeyEvent.VK_CONTROL);
								rob.keyPress(KeyEvent.VK_J);
								rob.keyRelease(KeyEvent.VK_J);
								rob.keyRelease(KeyEvent.VK_CONTROL);
							} catch (AWTException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							// ele.sendKeys(Keys.CONTROL+"j");
							ThreadSleep(10000);
							String abc = driver.getWindowHandle();
							System.err.println("URL Of small window: "+getURL(driver, 2)+" "+abc);
							Set<String> lst1 = driver.getWindowHandles();
							Iterator<String> I2 = lst1.iterator();
							while (I2.hasNext()) {
								String windowID = I2.next();
								if (windowID.equalsIgnoreCase(parentid) || windowID.equalsIgnoreCase(childWindowID)) {
									appLog.info("Parent Id or child Id is Matched");
								} else {
									childChildWindow = windowID;
									appLog.info("Storged  child child Window Id");
									driver.switchTo().window(childChildWindow);
									System.err.println("URL Of Download window: "+getURL(driver, 2)+" "+driver.getWindowHandle());
									break;
								}
							}
							System.out.println(getURL(driver, 30));
							if (childChildWindow == null) {
								try {
									rob = new Robot();
									rob.keyPress(KeyEvent.VK_TAB);
									rob.keyRelease(KeyEvent.VK_TAB);
									rob.keyPress(KeyEvent.VK_ENTER);
									rob.keyRelease(KeyEvent.VK_ENTER);
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_CONTROL);
									rob.keyPress(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_CONTROL);
									parentid = switchOnWindow(driver);
									driver.switchTo().window(childWindowID);
								} catch (AWTException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							ThreadSleep(10000);
							String fileName = fp.getDownloadedFileName();
							if (fileName.equalsIgnoreCase("Investor_Documents_" + M14FundName1 + "_"
									+ getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv")) {
								appLog.info("file name is successfully matched. " + "Investor_Documents_" + M14FundName1 + "_"
										+ getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv");
							} else {
								appLog.info("File name is not the matched. Expected: " + "Investor_Documents_" + M14FundName1
										+ "_" + getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv"
										+ " Actual: " + fileName);
								sa.assertTrue(false,
										"File name is not the matched. Expected: " + "Investor_Documents_" + M14FundName1
												+ "_" + getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv"
												+ " Actual: " + fileName);
							}
							driver.close();
							driver.switchTo().window(childWindowID);

						} else {
							appLog.error("Not able to click on Workspace Documents Report Link");
							sa.assertTrue(false, "Not able to click on Workspace Documents Report Link");
						}
						exportLinks.clear();
						exportLinks = fp.getIPAnalyticsListOfExportText();
						if (click(driver, exportLinks.get(1), "Contact Permissions Report ", action.BOOLEAN)) {
							appLog.info("Clicked on Contact Permission Export Link");
							ThreadSleep(10000);
							appLog.info("Contact Permissions Report File downloaded");
							Robot rob;
							try {
								rob = new Robot();
								ThreadSleep(10000);
								rob.keyPress(KeyEvent.VK_ENTER);
								rob.keyRelease(KeyEvent.VK_ENTER);
								ThreadSleep(10000);
								rob.keyPress(KeyEvent.VK_CONTROL);
								rob.keyPress(KeyEvent.VK_J);
								rob.keyRelease(KeyEvent.VK_J);
								rob.keyRelease(KeyEvent.VK_CONTROL);
							} catch (AWTException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							// ele.sendKeys(Keys.CONTROL+"j");
							ThreadSleep(10000);
							Set<String> lst1 = driver.getWindowHandles();
							Iterator<String> I2 = lst1.iterator();
							while (I2.hasNext()) {
								String windowID = I2.next();
								if (windowID.equalsIgnoreCase(parentid) || windowID.equalsIgnoreCase(childWindowID)) {
									appLog.info("Parent Id or child Id is Matched");
								} else {
									childChildWindow = windowID;
									appLog.info("Storged  child child Window Id");
									driver.switchTo().window(childChildWindow);
									break;
								}
							}
							if (childChildWindow == null) {
								try {
									rob = new Robot();
									rob.keyPress(KeyEvent.VK_TAB);
									rob.keyRelease(KeyEvent.VK_TAB);
									rob.keyPress(KeyEvent.VK_ENTER);
									rob.keyRelease(KeyEvent.VK_ENTER);
									ThreadSleep(10000);
									rob.keyPress(KeyEvent.VK_CONTROL);
									rob.keyPress(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_J);
									rob.keyRelease(KeyEvent.VK_CONTROL);
									parentid = switchOnWindow(driver);
								} catch (AWTException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							ThreadSleep(10000);
							String fileName = fp.getDownloadedFileName();
							if (fileName.equalsIgnoreCase("Investor_Contact_Permissions_" + M14FundName1 + "_"
									+ getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv")) {
								appLog.info("file name is successfully matched. " + "Investor_Contact_Permissions_" + M14FundName1 + "_"
										+ getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv");
							} else {
								appLog.info("File name is not the matched. Expected: " + "Investor_Contact_Permissions_"
										+ M14FundName1 + "_" + getDateAccToTimeZone("America/New_York", "MMddyyyy") + ".csv"
										+ " Actual: " + fileName);
								sa.assertTrue(false,
										"File name is not the matched. Expected: " + "Investor_Contact_Permissions_"
												+ M14FundName1 + "_" + getDateAccToTimeZone("America/New_York", "MMddyyyy")
												+ ".csv" + " Actual: " + fileName);
							}
							driver.close();
							driver.switchTo().window(childWindowID);

						} else {
							appLog.error("Not able to click on Contact Permission Export Link");
							sa.assertTrue(false, "Not able to click on Contact Permission Export Link");
						}
					} else {
						appLog.error("Not able to click on DR Analytics Exports tab");
						sa.assertTrue(false, "Not able to click on DR Analytics Exports tab");
					}
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created Fund: "+M14FundName1+" so cannot download Workspace Documents and Contact Permission Report");
				sa.assertTrue(false, "Not able to click on created Fund: "+M14FundName1+" so cannot download Workspace Documents and Contact Permission Report");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot download Workspace Documents and Contact Permission Report");
			sa.assertTrue(false, "Not able to click on fund tab so cannot download Workspace Documents and Contact Permission Report");
		}
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc063_clearInvestorWorkspaceAndBuildAgain() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		String Size=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M14Fund1", excelLabel.Fund_Size);
		String vintageyear=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M14Fund1", excelLabel.Fund_VintageYear);
		String contact=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M14Fund1", excelLabel.Fund_Contact);
		String phone=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M14Fund1", excelLabel.Fund_Phone);
		String email=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M14Fund1", excelLabel.Fund_Email);
		String description=ExcelUtils.readData("Funds",excelLabel.Variable_Name, "M14Fund1", excelLabel.Fund_Description);
		String templateName=ExcelUtils.readData("Foldertemp", 0, 1);
		String[] data= {Size,vintageyear,contact,phone,email,description};
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				List<String> lst=fp.readIPAnalyticsCount(Workspace.InvestorWorkspace);
				if(lst.isEmpty()) {
					appLog.info("IP Analytics all count is read and write in excel sheet succesfully");
				}else {
					for(int i=0; i<lst.size(); i++) {
						appLog.error(lst.get(i));
						sa.assertTrue(false, lst.get(i));
					}
				}
			}else {
				appLog.error("Not able to click on created fund: "+M14FundName1+" so cannot read and write ip analytics count");
				sa.assertTrue(false, "Not able to click on created fund: "+M14FundName1+" so cannot read and write ip analytics count");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot read and write ip analytics count");
			sa.assertTrue(false, "Not able to click on fund tab so cannot read and write ip analytics count");
		}
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(superAdminUserName,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(fp.clearWorkSpace(Workspace.InvestorWorkspace, 60)) {
					appLog.info("InvestorWorkspace is cleared");
					INV_UniqueDocument=0;
					INV_DocumentViews=0;
					INV_DocumentNotViewedOrDownload=0;
					INV_ContactGrantedAccess=0;
					INV_ContactNotViewedAnyDocument=0;
					INV_DocumentDownload=0;
					ExcelUtils.writeData(CommonVariables.INV_UniqueDocument, "IPAnalytics", excelLabel.Statistics, "No of Unique Documents", excelLabel.INV_Value);
					ExcelUtils.writeData(CommonVariables.INV_DocumentViews, "IPAnalytics", excelLabel.Statistics, "No of Document Views", excelLabel.INV_Value);
					ExcelUtils.writeData(CommonVariables.INV_DocumentDownload, "IPAnalytics", excelLabel.Statistics, "No of Document Downloads", excelLabel.INV_Value);
					ExcelUtils.writeData(CommonVariables.INV_DocumentNotViewedOrDownload, "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.INV_Value);
					ExcelUtils.writeData(CommonVariables.INV_ContactGrantedAccess, "IPAnalytics", excelLabel.Statistics, "No of Contacts Granted Access", excelLabel.INV_Value);
					ExcelUtils.writeData(CommonVariables.INV_ContactNotViewedAnyDocument, "IPAnalytics", excelLabel.Statistics, "No of Contacts who have not Viewed any Document", excelLabel.INV_Value);
					ExcelUtils.writeData("No Access", "Contacts", excelLabel.Variable_Name, "M14Contact1", excelLabel.Contact_Access);
					ExcelUtils.writeData("No Access", "Contacts", excelLabel.Variable_Name, "M14Contact2", excelLabel.Contact_Access);
					ExcelUtils.writeData("No Access", "Contacts", excelLabel.Variable_Name, "M14Contact3", excelLabel.Contact_Access);
					ExcelUtils.writeData("0", "Contacts", excelLabel.Variable_Name, "M14Contact1", excelLabel.Activity_Count);
					ExcelUtils.writeData("0", "Contacts", excelLabel.Variable_Name, "M14Contact2", excelLabel.Activity_Count);
					ExcelUtils.writeData("0", "Contacts", excelLabel.Variable_Name, "M14Contact3", excelLabel.Activity_Count);
					ExcelUtils.writeData("No", "Contacts", excelLabel.Variable_Name, "M14Contact1", excelLabel.Viewed_Or_DownloadedAnyFile);
					ExcelUtils.writeData("", "IPAnalytics", excelLabel.Statistics, "No of Documents not Viewed or Downloaded", excelLabel.INV_DocumentsName);
				}else {
					appLog.error("InvestorWorkspace is not cleared");
					sa.assertTrue(false, "InvestorWorkspace is not cleared");
				}
			}else {
				appLog.error("Not able to click on created fund: "+M14FundName1+" so cannot clear InvestorWorkspace");
				sa.assertTrue(false, "Not able to click on created fund: "+M14FundName1+" so cannot clear InvestorWorkspace");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot clear InvestorWorkspace");
			sa.assertTrue(false, "Not able to click on fund tab so cannot clear InvestorWorkspace");
		}	
		lp.CRMlogout();
		driver.close();
		config(browserToLaunch);
		lp = new LoginPageBusinessLayer(driver);
		fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				if(fp.buildWorkspace(data,WorkSpaceAction.IMPORTFOLDERTEMPLATE,templateName,null,M14Institution1+"/"+M14LimitedPartner1+"<break>"+M14Institution2+"/"+M14LimitedPartner2, Workspace.InvestorWorkspace,60)) {
					appLog.info("InvestorWorkspace is build successfully on fund : "+M14FundName1);
				}else {
					appLog.error("Not able to bulid InvestorWorkspace on fund: "+M14FundName1);
					sa.assertTrue(false, "Not able to bulid InvestorWorkspace on fund: "+M14FundName1);
				}
				switchToFrame(driver, 30,fp.getFrame(PageName.FundsPage, 20));
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(INV_UniqueDocument, INV_DocumentViews,
							INV_DocumentDownload, INV_DocumentNotViewedOrDownload, INV_ContactGrantedAccess,INV_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics(null,null,null);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					SoftAssert documents=fp.verifyDocumentNamesInDocumentNotViewedOrDownloadPopUp(null, false);
					sa.combineAssertions(documents);
					SoftAssert rsult=fp.verifyContactNameAndFirmNameInContactNotViewedAnyDocument(null,null);
					sa.combineAssertions(rsult);
					SoftAssert activityCount=fp.verifyMostActiveContactInIPAnalytics(null,null,null);
					sa.combineAssertions(activityCount);
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
			}else {
				appLog.error("Not able to click on created fund: "+M14FundName1+" so cannot clear INV");
				sa.assertTrue(false, "Not able to click on created fund: "+M14FundName1+" so cannot clear INV");
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot clear INV");
			sa.assertTrue(false, "Not able to click on fund tab so cannot clear INV");
		}	
		lp.CRMlogout();
		sa.assertAll();
	}
	
	@Test
	public void M14tc064_inviteContactInINV() {
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		FundsPageBusinessLayer fp = new FundsPageBusinessLayer(driver);
		lp.CRMLogin(CRMUser1EmailID,adminPassword);
		if(fp.clickOnTab(TabName.FundsTab)) {
			if(fp.clickOnCreatedFund(M14FundName1)) {
				if(fp.inviteContact(M14Institution1+"/"+M14LimitedPartner1,M14Contact1EmailId,null, FolderType.Standard,"Upload", "Yes",null,"All Folders", Workspace.InvestorWorkspace,null,"Yes",true)) {
					appLog.info("contact is invites successfully from InvestorWorkspace: "+M14Contact1FirstName+" "+M14Contact1LastName+" from "+M14Institution1);
				}else {
					appLog.error("Not able to invite contact from InvestorWorkspace: "+M14Contact1FirstName+" "+M14Contact1LastName+" from "+M14Institution1);
					sa.assertTrue(false, "Not able to invite contact from InvestorWorkspace: "+M14Contact1FirstName+" "+M14Contact1LastName+" from "+M14Institution1);
				}
				switchToFrame(driver, 30,bp.getFrame(PageName.FundsPage, 30));
				if(click(driver,fp.getIPAnalyticsIcon(Workspace.InvestorWorkspace), "IP Analytics Icon", action.SCROLLANDBOOLEAN)) {
					String parentid = switchOnWindow(driver);
					List<String> result = fp.verifyIPAnalyticsTargetValues(INV_UniqueDocument, INV_DocumentViews,
							INV_DocumentDownload, INV_DocumentNotViewedOrDownload, INV_ContactGrantedAccess,INV_ContactNotViewedAnyDocument);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("IP Analytics target value is verified Successfully.");
					}
					SoftAssert activity= fp.verifyMostActiveContactInIPAnalytics(null, null, null);
					sa.combineAssertions(activity);
					result.clear();
					result = fp.verifyMostViewedDocumentInIPAnalytics(null, null,Workspace.InvestorWorkspace);
					if (!result.isEmpty()) {
						for (int i = 0; i < result.size(); i++) {
							sa.assertTrue(false, result.get(i));
						}
					} else {
						appLog.info("Most View Document popup Error Message is verified.");
					}
					driver.close();
					driver.switchTo().window(parentid);
				} else {
					appLog.info("Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
					sa.assertTrue(false, "Not able to Click on Deal Room Analytics Icon on Deal: " + M14FundName1);
				}
				
			}else {
				appLog.error("Not able to click on created fund Name so cannot provide access to contact: "+M14Contact1FirstName+" "+M14Contact1LastName);
				sa.assertTrue(false, "Not able to click on created fund Name so cannot provide access to contact: "+M14Contact1FirstName+" "+M14Contact1LastName);
			}
		}else {
			appLog.error("Not able to click on fund tab so cannot provide access to contact: "+M14Contact1FirstName+" "+M14Contact1LastName);
			sa.assertTrue(false, "Not able to click on fund tab so cannot provide access to contact: "+M14Contact1FirstName+" "+M14Contact1LastName);
		}
		switchToDefaultContent(driver);
		lp.CRMlogout();
		sa.assertAll();
	}

}

