/**
 * 
 */
package com.navatar.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.navatar.generic.BaseLib;
import com.navatar.generic.ExcelUtils;
import com.navatar.generic.CommonLib.FolderType;
import com.navatar.generic.CommonLib.PageName;
import com.navatar.generic.CommonLib.Workspace;


import static com.navatar.generic.CommonLib.*;

import java.net.FileNameMap;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import static com.navatar.generic.AppListeners.appLog;
import static com.navatar.generic.BaseLib.sa;

/**
 * @author Ankur Rana
 *
 */
public class BoxPageBusinesslayer extends BoxPage {
	
	public static String time = getDateAccToTimeZone("America/Los_Angeles", "h-mm");
	public static String fileName = "folder_tree_run_on_"+getDateAccToTimeZone("America/Los_Angeles", "MM-dd-yy__")+time;
	
	
	
	/**
	 * @param driver
	 */
	public BoxPageBusinesslayer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @author Ankur Rana
	 * @param boxUsername
	 * @param boxPassword
	 * @return true/false
	 */
	public boolean boxLogin(String boxUsername, String boxPassword){
		driver.get("https://account.box.com/login");
		if(sendKeys(driver, getBoxUserNameTextBox(30), boxUsername, "Username", action.BOOLEAN)){
			if(click(driver, getBoxLoginNextButton(30), "Next Button", action.BOOLEAN)){
				if(sendKeys(driver, getBoxPasswordTextBox(30), boxPassword, "Box Password Text Box", action.BOOLEAN)){
					if(click(driver, getBoxLoginButton(30), "Login button", action.BOOLEAN)){
						appLog.info("Successfully logged in.");
						return true;
					} else {
						appLog.error("Login button cannot be clicked, So cannot continue.");
						sa.assertTrue(false,"Login button cannot be clicked, So cannot continue.");
					}
				} else {
					appLog.error("Password is not passed, So cannot continue.");
					sa.assertTrue(false,"Password is not passed, So cannot continue.");
				}
			} else {
				appLog.error("Cannot click on next button, So cannot continue.");
				sa.assertTrue(false,"Cannot click on next button, So cannot continue.");
			}
		} else {
			appLog.error("Username is not passed, So cannot continue.");
			sa.assertTrue(false,"Username is not passed, So cannot continue.");
		}
		return false;
	}
	
	/**
	 * @author Ankur Rana
	 * @param boxUsername
	 * @param boxPassword
	 * @param firmName
	 * @param workspaceName
	 * @param workspace
	 * @param timeOut
	 * @return true/false
	 */
	@SuppressWarnings("static-access")
	public boolean exportFolderStructureReportUpdated(String boxUsername, String boxPassword, String firmName, String workspaceName, Workspace workspace, int timeOut){
		if(boxLogin(boxUsername, boxPassword)){
			if(click(driver, getBoxAdminConsoleLink(timeOut), "Admin Console", action.BOOLEAN)){
				if(click(driver, getBoxReportLink(timeOut), "Report link", action.BOOLEAN)){
					if(click(driver, getBoxFilesAndFoldersReportExport(timeOut), "Folder report export link", action.BOOLEAN)){
						if(click(driver, getBoxBrowseFolderEditIcon(timeOut), "Edit Icon", action.BOOLEAN)){
							WebElement ele = FindElement(driver, "//span[@title='"+firmName+"']", "Firm Name folder link", action.BOOLEAN, timeOut);
							if(ele!=null){
								if(click(driver, ele, "Firm name folder", action.BOOLEAN)){
									String typeOfInvestment = "";
									if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
										typeOfInvestment = "Potential Investment";
									} else if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())){
										typeOfInvestment = "Current Investment";
									}
									ele = FindElement(driver, "//span[@title='"+typeOfInvestment+"']", "Investment folder", action.BOOLEAN, timeOut);
									if(click(driver, ele, typeOfInvestment+" folder", action.BOOLEAN)){
											ele = FindElement(driver, "//span[@title='"+workspaceName+"']/../../following-sibling::div//button", "Select Button", action.SCROLLANDBOOLEAN, timeOut);
											if(ele!=null){
												ThreadSleep(3000);
												if(click(driver, ele, workspaceName+" select button", action.BOOLEAN)){
													if(click(driver, getBoxDoneButton(timeOut), "Done button", action.BOOLEAN)){
														if(click(driver, getBoxExportReportLink(timeOut), "Export button", action.BOOLEAN)){
															appLog.info("Successfully generated the report.");
															if(click(driver, getViewReportsLink(timeOut), "View Report link", action.BOOLEAN)){
																ThreadSleep(3000);
																String time = getDateAccToTimeZone("America/Los_Angeles", "h-mm");
																 fileName = "folder_tree_run_on_"+getDateAccToTimeZone("America/Los_Angeles", "MM-dd-yy__")+time;
																this.fileName=fileName;
																System.err.println(fileName);
																for(int i = 0; i<=20; i++){
																	try{
																		mouseOverOperation(driver, FindElement(driver, "(//a[contains(text(),'folder_tree')])[1]", "file name", action.BOOLEAN,30));
																		ele = driver.findElement(By.xpath("//a[contains(text(),' "+fileName+"')]/../../../following-sibling::div[@class='file-list-item-actions']/button[1]"));
																		this.fileName = getText(driver, FindElement(driver, "(//a[contains(text(),'folder_tree')])[1]", "file name", action.BOOLEAN,30), "file name", action.BOOLEAN);
																	} catch(Exception e){
																		refresh(driver);
																		ThreadSleep(4000);
																	}
																	
																}
																click(driver, ele, "...button", action.BOOLEAN);
//																try{
//																	ele = driver.findElement(By.xpath("//a[contains(text(),' "+fileName+"')]/../../../../following-sibling::div//button[@data-resin-target='download']"));
//																} catch (Exception e){
//																	System.err.println("Exception aa gya");
//																}
																List<WebElement> ele1 = driver.findElements(By.xpath("//a[contains(text(),' "+fileName+"')]/../../../../following-sibling::div//button[@data-resin-target='download']"));
																System.err.println(ele1.size());
																try{
																	((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele1.get(0));
																	return true;
																} catch (Exception e){
																	appLog.error("Not able to click on download button, So cannot download the report.");
																	sa.assertTrue(false,"Not able to click on download button, So cannot download the report.");
																	return false;
																}
															} else {
																appLog.error("Cannot click on veiw report link, So cannot download the report.");
																sa.assertTrue(false,"Cannot click on veiw report link, So cannot download the report.");
															}
																
														} else {
															appLog.error("cannot click on export button, So cannot export the report.");
															sa.assertTrue(false,"cannot click on export button, So cannot export the report.");
														}
													} else {
														appLog.error("Done button cannot be clicked, So cannot download report.");
														sa.assertTrue(false,"Done button cannot be clicked, So cannot download report.");
													}
												} else {
													appLog.error("Select button cannot be clicked, So cannot export folder report.");
													sa.assertTrue(false,"Select button cannot be clicked, So cannot export folder report.");
												}
											} else {
												appLog.error("Select button is not present on the pop up, So cannot export folder report.");
												sa.assertTrue(false,"Select button is not present on the pop up, So cannot export folder report.");
											}

									} else {
										appLog.error(typeOfInvestment+" folder cannot be cliked, So cannot export the report.");
										sa.assertTrue(false,typeOfInvestment+" folder cannot be cliked, So cannot export the report.");
									}

								} else {
									appLog.error("Firm name '"+firmName+"' folder cannot be clicked, So folders are not created in box.");
									sa.assertTrue(false,"Firm name '"+firmName+"' folder cannot be clicked, So folders are not created in box.");
								}
							} else {
								appLog.error("Firm name '"+firmName+"' folder is not present so folders are not created in box.");
								sa.assertTrue(false,"Firm name '"+firmName+"' folder is not present so folders are not created in box.");
							}
						} else {
							appLog.error("edit icon cannot be clicked, So cannot continue.");
							sa.assertTrue(false,"edit icon cannot be clicked, So cannot continue.");
						}
					} else {
						appLog.error("cannot click on the folder report export link, So cannot continue.");
						sa.assertTrue(false,"cannot click on the folder report export link, So cannot continue.");
					}
				} else {
					appLog.error("report link cannot be clikced, So cannot continue.");
					sa.assertTrue(false,"report link cannot be clikced, So cannot continue.");
				}
			} else {
				appLog.error("Admin cansole link cannot be clicked, So cannot continue.");
				sa.assertTrue(false,"Admin cansole link cannot be clicked, So cannot continue.");
			}
		} else {
			appLog.error("Not been able to login, So cannot continue.");
			sa.assertTrue(false,"Not been able to login, So cannot continue.");
		}
		return false;
	}

	/**
	 * @author Ankur Rana
	 * @param folderStructureExcelName
	 * @param downloadedFileExcelPath
	 * @param folderStructSheetName
	 * @param firmName
	 * @param fundName
	 * @param instiAndLPName
	 * @param workspace
	 * @param timeOut
	 * @return Map<String, String>
	 */
	public Map<String, String> verifyFolderStructureInBoxAndGetFolderIds(String folderStructureExcelName, String downloadedFileExcelPath, String folderStructSheetName,  String firmName, String fundName, String instiAndLPName, Workspace workspace, int timeOut){
		Map<String, String> ids = new LinkedHashMap<String, String>();
		String investmentType = null;
		String pathToBeSearched = "";
		String[] downLoadedFolderPathArray=null;
		String[] downLoadedFolderIdArray=null;
		BasePageBusinessLayer bp = new BasePageBusinessLayer(driver);
		if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
			investmentType = "Potential Investment";
		} else {
			investmentType = "Current Investment";
		}
		String downLoadedFolderPath=ExcelUtils.readAllDataForAColumn(downloadedFileExcelPath, "Sheet1", 2, false);
		if(downLoadedFolderPath!=null) {
			downLoadedFolderPathArray = downLoadedFolderPath.split(",");
			appLog.info("downLoadedFolderPathArray Length : "+downLoadedFolderPathArray.length);
			String downLoadedFolderId=ExcelUtils.readAllDataForAColumn(downloadedFileExcelPath, "Sheet1", 5, true);
			downLoadedFolderIdArray = downLoadedFolderId.split(",");
			appLog.info("downLoadedFolderIdArray Length : "+downLoadedFolderIdArray.length);

		}else {
			return ids;
		}
		Map<String, String> s = bp.folderStructureInExcel(folderStructureExcelName, folderStructSheetName);
		Set<String> paths = s.keySet();
		Iterator<String> i = paths.iterator();
		FolderType folderType = null;
		String[] instiAndLPNames = null;
		int runTill = 1;
		if(instiAndLPName!=null && !instiAndLPName.isEmpty()){
			instiAndLPNames = instiAndLPName.split("<break>");
			runTill = instiAndLPNames.length;
		} else {
			appLog.info("Will not check for standard folder because no institution or LP name is passed.");
		}
		int sum=0;
		int sumOne=0;
		//		System.err.println("Entry Set :"+s.entrySet());
		for ( Map.Entry<String, String> entry : s.entrySet()) {
			String tab = entry.getValue();
//			System.err.println("Tab Key : "+entry.getKey());
//			System.err.println("Tab Value : "+tab);
			if(tab.equalsIgnoreCase("Shared") || tab.equalsIgnoreCase("Common") || tab.equalsIgnoreCase("Internal")) {
				sum++;
			}

		}
//		System.err.println("Sum Size: "+sum);
		for(int j = 0; j < runTill; j++){
			i = paths.iterator();
			while (i.hasNext()) {
				String string = i.next();
				if (string.isEmpty())
					continue;
				if(s.get(string).equalsIgnoreCase("Shared") && sumOne<sum){
					folderType=FolderType.Shared;
					pathToBeSearched = "All Files/"+firmName+"/"+investmentType+"/"+fundName+"/"+string+"/"+","+pathToBeSearched;
					sumOne++;
				} else if (s.get(string).equalsIgnoreCase("Common") && sumOne<sum){
					folderType=FolderType.Common;
					pathToBeSearched = "All Files/"+firmName+"/"+investmentType+"/"+fundName+"/"+string+"/"+","+pathToBeSearched;
					sumOne++;
				} else if (s.get(string).equalsIgnoreCase("Internal") && sumOne<sum){
					folderType=FolderType.Internal;
					pathToBeSearched = "All Files/"+firmName+"/"+investmentType+"/"+fundName+"/"+string+"/"+","+pathToBeSearched;
					sumOne++;
				} else if (s.get(string).equalsIgnoreCase("Standard")){
					folderType=FolderType.Standard;
					pathToBeSearched = "All Files/"+firmName+"/"+investmentType+"/"+fundName+"/"+instiAndLPNames[j]+"/"+string+"/"+","+pathToBeSearched;
				}else {
					sumOne++;
					continue;
				}
			}

		}
		String[] pathToBeSearchedArray = pathToBeSearched.split(",");
		appLog.info("pathToBeSearchedArray Length : "+pathToBeSearchedArray.length);
		for (int j1 = 0; j1 < pathToBeSearchedArray.length; j1++) {
			appLog.info("Going to Verify : "+pathToBeSearchedArray[j1]);
			for (int  j2= 0; j2 < downLoadedFolderPathArray.length; j2++) {					
				if (pathToBeSearchedArray[j1].trim().equalsIgnoreCase(downLoadedFolderPathArray[j2].trim())) {
					appLog.info("Verified : "+downLoadedFolderPathArray[j2]);
					if (downLoadedFolderIdArray[j2]!=null || !downLoadedFolderIdArray[j2].isEmpty()) {
						appLog.info("folder id: "+ downLoadedFolderIdArray[j2]+" and folder path : "+ downLoadedFolderPathArray[j2]+" has been added in map");
						ids.put(downLoadedFolderIdArray[j2], downLoadedFolderPathArray[j2]);	
					} else {
						appLog.info("id is blank or null for folder and Not Verified : "+pathToBeSearchedArray[j1]);
						sa.assertTrue(false, "id is blank or null for folder and Not Verified : "+pathToBeSearchedArray[j1]);
					}
					break;
				} else {
					if (j2==downLoadedFolderPathArray.length-1) {
						appLog.info("Not Verified : "+pathToBeSearchedArray[j1]);
						sa.assertTrue(false, "Not Verified : "+pathToBeSearchedArray[j1]);
					} 
				}
			}


		}
		return ids;

	}
	
	/**
	 * @author Ankur Rana
	 * @param folderStructureExcelName
	 * @param downloadedFileExcelPath
	 * @param folderStructureSheetName
	 * @param hubUsername
	 * @param hubPassword
	 * @param fundName
	 * @param firmName
	 * @param instiAndLPName
	 * @param workspace
	 * @param timeOut
	 * @return List<String>
	 */
	public List<String> verifyFolderStructureInHub(String folderStructureExcelName, String downloadedFileExcelPath, String folderStructureSheetName, String hubUsername, String hubPassword, String fundName, String firmName, String instiAndLPName, String crmUserName, Workspace workspace, int timeOut){
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		boolean flag = true;
		List<String> notFoundFoldes = new ArrayList<String>();
		lp.CRMLogin(hubUsername, hubPassword);
		if(lp.clickOnTab(TabName.FolderTemplate)){
			if(!verifyViewDropDown(fundName+" "+crmUserName)){
				if(!createView(lp, fundName, crmUserName, timeOut)){
					appLog.error("View is not created, So cannot verify folder structure.");
					sa.assertTrue(false,"View is not created, So cannot verify folder structure.");
					flag=false;
				}
			} else {
				if(getSelectedOptionOfDropDown(driver, lp.getViewDropdown(timeOut), "View drop Down", "text").equalsIgnoreCase(fundName+" "+crmUserName)){
					if(click(driver, lp.getGoButton(timeOut), "Go Button", action.BOOLEAN)){
						appLog.info("Clicked on folder template go button");
					} else {
						appLog.error("Not able click on go button to select '"+fundName+" "+crmUserName+"' from view drop down, So won't be able to verify folder structure");
						sa.assertTrue(false,"Not able click on go button to select '"+fundName+" "+crmUserName+"' from view drop down, So won't be able to verify folder structure");
						return notFoundFoldes;
					}
				} else {
					if(selectVisibleTextFromDropDown(driver, lp.getViewDropdown(timeOut), "view Drop Down", fundName+" "+crmUserName)){
						appLog.info("Successfully selected '"+fundName+" "+crmUserName+"' from view drop down.");
						
					} else {
						appLog.error("Not able to select '"+fundName+" "+crmUserName+"' from view drop down, So won't be able to verify folder structure");
						sa.assertTrue(false,"Not able to select '"+fundName+" "+crmUserName+"' from view drop down, So won't be able to verify folder structure");
						return notFoundFoldes;
					}
				}
			}
			if(flag){
				WebElement ele = FindElement(driver, "(//div[@class='x-grid3-cell-inner x-grid3-col-NAME'])[1]/a", "Folder Template", action.BOOLEAN, timeOut);
				String text = ""; 
				if(click(driver, ele, "Folder Template", action.SCROLLANDBOOLEAN)){
					if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
						ele = FindElement(driver, "//td[text()='Fundraising Workspace']/following-sibling::td//img", "Fundraising workspace checkbox", action.SCROLLANDBOOLEAN, timeOut);
					} else {
						ele = FindElement(driver, "//td[text()='Investor Workspace']/following-sibling::td//img", "Investor workspace checkbox", action.SCROLLANDBOOLEAN, timeOut);
					}
					text = getAttribute(driver, ele, "", "title");
					if(!text.equalsIgnoreCase("checked")){
						driver.navigate().back();
						ele = FindElement(driver, "(//div[@class='x-grid3-cell-inner x-grid3-col-NAME'])[2]/a", "Folder Template", action.BOOLEAN, timeOut);
						if(click(driver, ele, "folder template", action.SCROLLANDBOOLEAN)){
							if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
								ele = FindElement(driver, "//td[text()='Fundraising Workspace']/following-sibling::td//img", "Fundraising workspace checkbox", action.SCROLLANDBOOLEAN, timeOut);
							} else {
								ele = FindElement(driver, "//td[text()='Investor Workspace']/following-sibling::td//img", "Investor workspace checkbox", action.SCROLLANDBOOLEAN, timeOut);
							}
							text = getAttribute(driver, ele, "", "title");
							if(!text.equalsIgnoreCase("checked")){
								appLog.error("Folder template for '"+workspace+"' is not available, So cannot verify.");
								sa.assertTrue(false,"Folder template for '"+workspace+"' is not available, So cannot verify.");
								flag=false;
							}
							
						} else {
							appLog.error("Template cannot be clicked, So cannot verify folder.");
							sa.assertTrue(false,"Template cannot be clicked, So cannot verify folder.");
						}
					}
				} else {
					appLog.error("No Folder template is present for '"+fundName+"' fund. So cannot verify folder structure.");
					sa.assertTrue(false,"No Folder template is present for '"+fundName+"' fund. So cannot verify folder structure.");
				}
			}
			
			if(flag){
				String text = getText(driver, lp.getFolderStructureInHub(timeOut), "Folder Structure in hub", action.BOOLEAN);
				String a = getText(driver, lp.getRelatedFolderIds(timeOut), "related folder ids", action.BOOLEAN);
//				if(a!=null){
//					text = text +" "+a; 
//				}
				String b=getText(driver, lp.getRelatedFolderIds2(timeOut), "related folder ids2", action.BOOLEAN);
//				if(b!=null){
//					text = text +" "+b; 
//				}
				System.err.println("\n\n\nSize of string: "+text.length()+"\n\n\n");
				Map<String, String> idsAndNames = verifyFolderStructureInBoxAndGetFolderIds(folderStructureExcelName, downloadedFileExcelPath, folderStructureSheetName, firmName, fundName, instiAndLPName, workspace, timeOut);
				if(!idsAndNames.isEmpty() && idsAndNames!=null) {
					Set<String> id = idsAndNames.keySet();
					for (String string : id) {
						String folderpath=idsAndNames.get(string);
						if(folderpath.contains("Shared") || folderpath.contains("Common") || folderpath.contains("Internal")){
							if(text.contains(string)){
								appLog.info(folderpath+" folder structure is verified in hub.");
							} else {
								appLog.error(string+" folder id of "+folderpath+" folder structure is not verified in hub.");
								notFoundFoldes.add(folderpath);
							}
							
						}else {
							if(a.contains(string) || b.contains(string)){
								appLog.info(folderpath+" folder structure is verified in hub.");
							} else {
								appLog.error(string+" folder id of "+folderpath+" folder structure is not verified in hub.");
								notFoundFoldes.add(folderpath);
							}
						}
					}
				}else {
					appLog.error("File is not found :"+downloadedFileExcelPath+"  so cannot verify folder structure in hub");
					notFoundFoldes.add("File is not found :"+downloadedFileExcelPath+"  so cannot verify folder structure in hub");
				}
			}
			
		} else {
			appLog.error("Folder Template Tab is cannot be clicked, So cannot verify folder structure.");
			sa.assertTrue(false,"Folder Template Tab is cannot be clicked, So cannot verify folder structure.");
		}
		return notFoundFoldes;
	}
	
	/**
	 * @author Ankur Rana
	 * @param fundName
	 * @return true/false
	 */
	public boolean verifyViewDropDown(String fundName){
		boolean flag = false;
		LoginPageBusinessLayer lp = new LoginPageBusinessLayer(driver);
		List<WebElement> options = allOptionsInDropDrop(driver, lp.getViewDropdown(30), "view drop down");
		List<String> optionsInString = new ArrayList<String>();
		for(int i = 0; i < options.size(); i++){
			optionsInString.add(getText(driver, options.get(i), "", action.BOOLEAN));
		}
		if(!optionsInString.isEmpty()) {
			for (int i = 0; i < optionsInString.size(); i++) {
				if(optionsInString.get(i).contains(fundName)){
					appLog.info(fundName+" view is already created ");
					flag=true;
					break;
				} else {
					if(i==optionsInString.size()-1) {
						appLog.error(fundName+" view is not created ");
						
					}
				}
				
			}
		}else {
			appLog.error("No view is created in folder template");
		}
		return flag;
	}
	
	/**
	 * @author Ankur Rana
	 * @param bp
	 * @param viewName
	 * @param timeOut
	 * @return true/false
	 */
	public boolean createView(BasePageBusinessLayer bp, String viewName,String createdBy, int timeOut){
		if(click(driver, bp.getCreateViewLink(timeOut), "Create View Link", action.BOOLEAN)){
			if(sendKeys(driver, bp.getViewName(timeOut), viewName+" "+createdBy, "View Name Text Box", action.BOOLEAN)){
//				if(sendKeys(driver, bp.getViewUniqueName(timeOut), viewName, "View Unique Name", action.BOOLEAN)){
					if(selectVisibleTextFromDropDown(driver, bp.getFieldDropDown(timeOut), "Field Drop Down", "Fund")){
						if(selectVisibleTextFromDropDown(driver, bp.getViewOperatorDropDown(timeOut), "Operator Drop Down", "equals")){
							if(sendKeys(driver, bp.getViewValueBox(timeOut), viewName, "View Name Text box", action.BOOLEAN)){
								if(selectVisibleTextFromDropDown(driver, bp.getFieldDropDown1(timeOut), "Field Drop Down", "Created By")){
									if(selectVisibleTextFromDropDown(driver, bp.getViewOperatorDropDown1(timeOut), "Operator Drop Down", "equals")){
										if(sendKeys(driver, bp.getViewValueBox1(timeOut), createdBy, "View Name Text box", action.BOOLEAN)){
											if(click(driver, bp.getSaveButton(timeOut), "save button", action.SCROLLANDBOOLEAN)){
												return true;
											} else {
												appLog.error("Save button cannot be clicked, So cannot create view.");
												sa.assertTrue(false,"Save button cannot be clicked, So cannot create view.");
											}
										} else {
											appLog.error("Cannot pass value to view text box.");
											sa.assertTrue(false,"Cannot pass value to view text box.");
										}
									} else {
										appLog.error("Operator cannot be selected, so cannot create view.");
										sa.assertTrue(false,"Operator cannot be selected, so cannot create view.");
									}
								} else {
									appLog.error("Cannot select Field value, So view cannot be created.");
									sa.assertTrue(false,"Cannot select Field value, So view cannot be created.");
								}
								
							} else {
								appLog.error("Cannot pass value to view text box.");
								sa.assertTrue(false,"Cannot pass value to view text box.");
							}
						} else {
							appLog.error("Operator cannot be selected, so cannot create view.");
							sa.assertTrue(false,"Operator cannot be selected, so cannot create view.");
						}
					} else {
						appLog.error("Cannot select Field value, So view cannot be created.");
						sa.assertTrue(false,"Cannot select Field value, So view cannot be created.");
					}
//				} else {
//					appLog.error("Cannot pass value to View unique name, So cannot create view.");
//					sa.assertTrue(false,"Cannot pass value to View unique name, So cannot create view.");
//				}
			} else {
				appLog.error("Cannot pass value to View Name Text Box");
				sa.assertTrue(false,"Cannot pass value to View Name Text Box");
			}
		} else {
			appLog.error("Create view link cannot be clicked, So cannot create view.");
			sa.assertTrue(false,"Create view link cannot be clicked, So cannot create view.");
		}
		return false;
	}
	
	/**
	 * @author Ankur Rana
	 * @param boxUsername
	 * @param boxPassword
	 * @param firmName
	 * @param workspaceName
	 * @param workspace
	 * @param timeOut
	 * @return true/false
	 */
	@SuppressWarnings("static-access")
	public boolean exportFolderStructureReport(String boxUsername, String boxPassword, String firmName, String workspaceName, Workspace workspace, int timeOut){
		if(boxLogin(boxUsername, boxPassword)){
			if(click(driver, getBoxAdminConsoleLink(timeOut), "Admin Console", action.BOOLEAN)){
				if(click(driver, getBoxReportLink(timeOut), "Report link", action.BOOLEAN)){
					if(click(driver, getBoxFilesAndFoldersReportExport(timeOut), "Folder report export link", action.BOOLEAN)){
						if(click(driver, getBoxBrowseFolderEditIcon(timeOut), "Edit Icon", action.BOOLEAN)){
							WebElement ele = FindElement(driver, "(//input[@aria-label='Search query'])[2]", "Search Box", action.BOOLEAN, timeOut);
							String typeOfInvestment = "";
							if(workspace.toString().equalsIgnoreCase(Workspace.FundraisingWorkspace.toString())){
								typeOfInvestment = "Potential Investment";
							} else if (workspace.toString().equalsIgnoreCase(Workspace.InvestorWorkspace.toString())){
								typeOfInvestment = "Current Investment";
							}
							if(sendKeys(driver, ele, typeOfInvestment, "Search box", action.BOOLEAN)){
								if(sendKeys(driver, ele, Keys.chord(Keys.ENTER), "Search box", action.BOOLEAN)){
									ele = FindElement(driver, "//span[text()='"+typeOfInvestment+"']", "Investment folder", action.BOOLEAN, timeOut);
									if(click(driver, ele, typeOfInvestment+" folder", action.BOOLEAN)){
											ele = FindElement(driver, "//span[text()='"+workspaceName+"']/../../../../following-sibling::div//input", "Select Button", action.SCROLLANDBOOLEAN, timeOut);
											if(ele!=null){
												ThreadSleep(3000);
												if(click(driver, ele, workspaceName+" select button", action.SCROLLANDBOOLEAN)){
													if(click(driver, getBoxChooseButton(timeOut), "Choose button", action.BOOLEAN)){
														if(click(driver, getBoxExportReportLink(timeOut), "Export button", action.BOOLEAN)){
															appLog.info("Successfully generated the report.");
															if(click(driver, getViewReportsLink(timeOut), "View Report link", action.BOOLEAN)){
																ThreadSleep(5000);
																if(getPagiNationButton(60)!=null) {
																	if(click(driver, getPagiNationButton(10), "pagination button", action.BOOLEAN)) {
																		if(click(driver, getPagiNationDropDownList().get(getPagiNationDropDownList().size()-1), "pagination drop down list", action.SCROLLANDBOOLEAN)) {
																			appLog.info("last page is selected from pagination drop down list");
																		}else {
																			appLog.error("Not able to click on pagination drop down list so cannot select last page");
																			sa.assertTrue(false, "Not able to click on pagination drop down list so cannot select last page");
																		}
																	}else {
																		appLog.error("Not able to click on pagination button so cannot select last page from pagination drop down list");
																		sa.assertTrue(false, "Not able to click on pagination button so cannot select last page pagination drop down list");
																	}
																}else {
																	appLog.info("PageiNation is not visible on Reprot folder page");
																}
																String time = getDateAccToTimeZone("America/Los_Angeles", "h-mm");
																String fileName = "folder_tree_run_on_"+getDateAccToTimeZone("America/Los_Angeles", "M-d-yy__")+time;
																this.fileName=fileName;
																System.err.println("downloaded File Name : "+fileName);
																int total = FindElements(driver, "//a[contains(text(),'folder_tree')]", "reports").size();
																boolean flag = false;
																for(int i = 0; i<=200; i++){
																	try{
																		mouseOverOperation(driver, FindElement(driver, "(//a[contains(text(),'folder_tree')])[1]", "file name", action.BOOLEAN,30));
																		int totalAfterRefresh = FindElements(driver, "//a[contains(text(),'folder_tree')]", "reports").size();
																		System.err.println("Count After refresh: "+totalAfterRefresh+"\tTotal Before refresh: "+total);
																		if(totalAfterRefresh > total){
																			if(getPagiNationButton(30)!=null) {
																				if(click(driver, getPagiNationButton(10), "pagination button", action.BOOLEAN)) {
																					if(click(driver, getPagiNationDropDownList().get(0), "pagination drop down list", action.SCROLLANDBOOLEAN)) {
																						appLog.info("1st page is selected from pagination drop down list");
																					}else {
																						appLog.error("Not able to click on pagination drop down list so cannot select 1st page");
																						sa.assertTrue(false, "Not able to click on pagination drop down list so cannot select 1st page");
																					}
																				}else {
																					appLog.error("Not able to click on pagination button so cannot select 1st page from pagination drop down list");
																					sa.assertTrue(false, "Not able to click on pagination button so cannot select 1st page from pagination drop down list");
																				}
																			}else {
																				appLog.info("PageiNation is not visible on Reprot folder page");
																			}
																			System.err.println("Report is successfully generated.");
																			this.fileName = getText(driver, FindElement(driver, "(//a[contains(text(),'folder_tree')])[1]", "file name", action.BOOLEAN,30), "file name", action.BOOLEAN);
																			fileName = this.fileName;
																			ele = FindElement(driver, "(//a[contains(text(),'folder_tree')])[1]", "file name", action.BOOLEAN,30);
																			
																			flag = true;
																			
																			break;
																		} else {
																			System.err.println("Report is not generated will refresh and check again.");
																			refresh(driver);
																			ThreadSleep(4000);
																		}
																	} catch(Exception e){
																		refresh(driver);
																		ThreadSleep(4000);
																	}
																	
																}
																if(flag){
																	mouseOverOperation(driver, FindElement(driver, "(//a[contains(text(),'folder_tree')])[1]", "file name", action.BOOLEAN,30));
																	ele = FindElement(driver, "(((//a[contains(text(),'folder_tree')])[1]/../../../../../following-sibling::div)[5]//button)[1]", "More Options", action.BOOLEAN,30);
																}
																click(driver, ele, "...button", action.BOOLEAN);
//																try{
//																	ele = driver.findElement(By.xpath("//a[contains(text(),' "+fileName+"')]/../../../../following-sibling::div//button[@data-resin-target='download']"));
//																} catch (Exception e){
//																	System.err.println("Exception aa gya");
//																}
//																List<WebElement> ele1 = driver.findElements(By.xpath("//a[contains(text(),' "+fileName+"')]/../../../../following-sibling::div//button[@data-resin-target='download']"));
//																System.err.println(ele1.size());
//																try{
//																	((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele1.get(0));
//																	return true;
//																} catch (Exception e){
//																	appLog.error("Not able to click on download button, So cannot download the report.");
//																	sa.assertTrue(false,"Not able to click on download button, So cannot download the report.");
//																	return false;
//																}
																
																WebElement ele1 = FindElement(driver, "//span[text()='Download']", "Download button", action.BOOLEAN, 30);
//																System.err.println(ele1.size());
																try{
																	((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele1);
																	return true;
																} catch (Exception e){
																	appLog.error("Not able to click on download button, So cannot download the report.");
																	sa.assertTrue(false,"Not able to click on download button, So cannot download the report.");
																	return false;
																}
															} else {
																appLog.error("Cannot click on veiw report link, So cannot download the report.");
																sa.assertTrue(false,"Cannot click on veiw report link, So cannot download the report.");
															}
																
														} else {
															appLog.error("cannot click on export button, So cannot export the report.");
															sa.assertTrue(false,"cannot click on export button, So cannot export the report.");
														}
													} else {
														appLog.error("Done button cannot be clicked, So cannot download report.");
														sa.assertTrue(false,"Done button cannot be clicked, So cannot download report.");
													}
												} else {
													appLog.error("Select button cannot be clicked, So cannot export folder report.");
													sa.assertTrue(false,"Select button cannot be clicked, So cannot export folder report.");
												}
											} else {
												appLog.error("Select button is not present on the pop up, So cannot export folder report.");
												sa.assertTrue(false,"Select button is not present on the pop up, So cannot export folder report.");
											}

									} else {
										appLog.error(typeOfInvestment+" folder cannot be cliked, So cannot export the report.");
										sa.assertTrue(false,typeOfInvestment+" folder cannot be cliked, So cannot export the report.");
									}

								} else {
									appLog.error("Search box is not working, So cannot continue with the report export.");
									sa.assertTrue(false,"Search box is not working, So cannot continue with the report export.");
								}
							} else {
								appLog.error("Search box is not working, So cannot continue with the report export.");
								sa.assertTrue(false,"Search box is not working, So cannot continue with the report export.");
							}
						} else {
							appLog.error("edit icon cannot be clicked, So cannot continue.");
							sa.assertTrue(false,"edit icon cannot be clicked, So cannot continue.");
						}
					} else {
						appLog.error("cannot click on the folder report export link, So cannot continue.");
						sa.assertTrue(false,"cannot click on the folder report export link, So cannot continue.");
					}
				} else {
					appLog.error("report link cannot be clikced, So cannot continue.");
					sa.assertTrue(false,"report link cannot be clikced, So cannot continue.");
				}
			} else {
				appLog.error("Admin cansole link cannot be clicked, So cannot continue.");
				sa.assertTrue(false,"Admin cansole link cannot be clicked, So cannot continue.");
			}
		} else {
			appLog.error("Not been able to login, So cannot continue.");
			sa.assertTrue(false,"Not been able to login, So cannot continue.");
		}
		return false;
	}
	
	
}
