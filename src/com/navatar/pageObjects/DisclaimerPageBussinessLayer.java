/**
 * 
 */
package com.navatar.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.navatar.generic.CommonLib;
import com.navatar.generic.CommonLib.action;

import static com.navatar.generic.BaseLib.sa;
import static com.navatar.generic.AppListeners.*;
import static com.navatar.generic.CommonLib.*;
import static com.navatar.generic.CommonVariables.M4DIS1;
import static com.navatar.generic.CommonVariables.M4DIS2;
import static com.navatar.generic.CommonVariables.M4DISDEC1;
import static com.navatar.generic.CommonVariables.M4F1;
import static com.navatar.generic.CommonVariables.M4F2;

import java.util.List;

/**
 * @author Ankur Rana
 *
 */
public class DisclaimerPageBussinessLayer extends DisclaimerPage implements DisclaimerPageErrorMessage {

	/**
	 * @param driver
	 */
	public DisclaimerPageBussinessLayer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @author Ankur Rana
	 * @param firmName
	 * @param fundName
	 * @param disclaimerName
	 * @param disclaimerDescription
	 * @param allFirmNamesInDropDown
	 * @param timeOut
	 * @return
	 */
	public boolean verifyDisclaimerPage(String firmName, String fundName, String disclaimerName, String disclaimerDescription, String allFirmNamesInDropDown, int timeOut){
		boolean flag = true;
		String text = trim(getText(driver, getDisclaimerPageHeader(60), "Header", action.BOOLEAN));
		if(text.equalsIgnoreCase("Disclaimers")){
			appLog.info("Header is verified.");
		} else {
			appLog.error("Header is not present on the page.Expected: Disclaimers\tActual: "+text);
			sa.assertTrue(false,"Header is not present on the page.Expected: Disclaimers\tActual: "+text);
			flag = false;
		}
		WebElement ele = FindElement(driver, "//span[@title='"+fundName+"']/preceding-sibling::span[@title='"+firmName+"']/preceding-sibling::div//span[contains(@id,'hideFilterText')]", "Expand or Collapse Icomn", action.BOOLEAN, timeOut);
		if(ele!=null){
			appLog.info("Disclaimer for fund '"+fundName+"' is in expanded state and is verfied.");
		} else {
			appLog.error("Disclaimer for fund '"+fundName+"' is in collapsed state.");
			sa.assertTrue(false,"Disclaimer for fund '"+fundName+"' is in collapsed state.");
			flag = false;
		}
		
		ele = FindElement(driver, "//span[@title='"+fundName+"']/preceding-sibling::span", firmName+" Firm Name label", action.BOOLEAN, timeOut);
		if(ele!=null){
			text = trim(getAttribute(driver, ele, "Firm Name", "title"));
			if(text.equalsIgnoreCase(firmName)){
				appLog.info("firm name is verifed.");
			} else {
				appLog.error("Firm name is not verifed for fund '"+fundName+"'. Expected: "+firmName+"\tActual: "+text);
				sa.assertTrue(false,"Firm name is not verifed for fund '"+fundName+"'. Expected: "+firmName+"\tActual: "+text);
				flag = false;
			}
		} else {
			appLog.error("Firm name is not present on the page for fund '"+fundName+"'");
			sa.assertTrue(false,"Firm name is not present on the page for fund '"+fundName+"'");
			flag = false;
		}
		
		ele = FindElement(driver, "//span[@title='"+firmName+"']/following-sibling::span", fundName+" Fund Name label", action.BOOLEAN, timeOut);
		if(ele!=null){
			text = trim(getAttribute(driver, ele, "Fund Name", "title"));
			if(text.equalsIgnoreCase(fundName)){
				appLog.info("Fund name is verifed.");
			} else {
				appLog.error("Fund name is not verifed for fund '"+fundName+"'. Expected: "+fundName+"\tActual: "+text);
				sa.assertTrue(false,"Fund name is not verifed for fund '"+fundName+"'. Expected: "+fundName+"\tActual: "+text);
				flag = false;
			}
		} else {
			appLog.error("Fund name is not present on the page for fund '"+fundName+"'");
			sa.assertTrue(false,"Fund name is not present on the page for fund '"+fundName+"'");
			flag = false;
		}
		
		List<WebElement> disclaimerNames = FindElements(driver, "//div[contains(@id,'filterGridContact')]//b", "DisclaimerNames");
		if(disclaimerNames.isEmpty()){
			appLog.error("No disclaimer is present on the disclaimer page.");
			sa.assertTrue(false,"No disclaimer is present on the disclaimer page.");
			flag = false;
		} else {
			for(int i = 0; i < disclaimerNames.size(); i++){
				if(trim(getText(driver, disclaimerNames.get(i), "Disclaimer name", action.BOOLEAN)).equalsIgnoreCase(disclaimerName)){
					appLog.info(disclaimerName+" is verified.");
				} else {
					if(i == disclaimerNames.size()){
						appLog.error("disclaimer Name '"+disclaimerName+"' is not present on the page.");
						sa.assertTrue(false,"disclaimer Name '"+disclaimerName+"' is not present on the page.");
						flag = false;
					}
				}
			}
		}
		ele = FindElement(driver, "//b[text()='"+disclaimerName+"']/../following-sibling::div/p/span", disclaimerName+" Disclaimer description", action.BOOLEAN, timeOut);
		if(ele!=null){
			text = trim(getText(driver, ele, "", action.BOOLEAN));
			if(text.contains(disclaimerDescription)){
				appLog.info(disclaimerName+" disclaimer description is verified");
			} else {
				appLog.error(disclaimerName+" disclaimer description is not verifed. Expected: "+disclaimerDescription+"\tActual: "+text);
				sa.assertTrue(false,disclaimerName+" disclaimer description is not verifed. Expected: "+disclaimerDescription+"\tActual: "+text);
				flag = false;
			}
		} else {
			appLog.error(disclaimerName+" disclaimer description is not present.");
			sa.assertTrue(false,disclaimerName+" disclaimer description is not present.");
			flag = false;
		}
		
		ele = FindElement(driver, "//b[text()='"+disclaimerName+"']/../following-sibling::div/a[@title='Accept']", disclaimerName+" disclaimer accept button", action.BOOLEAN, timeOut);
		if(ele!=null){
			appLog.info(disclaimerName+" accept button is verified.");
		} else {
			appLog.error(disclaimerName+" disclamer accept button is not present.");
			sa.assertTrue(false,disclaimerName+" disclamer accept button is not present.");
			flag = false;
		}
		
		text = getSelectedOptionOfDropDown(driver, getFirmNameDropdown(timeOut), "Firm Name Drop Down", "text");
		if(text.isEmpty()){
			appLog.info("Blank is selected in the drop down is verifed.");
		} else {
			appLog.error("Blank is not selected in the drop down is not verified. Expected: blank\tActual: "+text);
			sa.assertTrue(false,"Blank is not selected in the drop down is not verified. Expected: blank\tActual: "+text);
			flag = false;
		}
		
		List<WebElement> allOptions = allOptionsInDropDrop(driver, getFirmNameDropdown(timeOut), "firm name drop down.");
		if(allFirmNamesInDropDown==null){
			allFirmNamesInDropDown="All Firms";
		} else {
			allFirmNamesInDropDown=allFirmNamesInDropDown+",All Firms";
		}
		if(allOptions.isEmpty()){
			appLog.error("There is not option in the drop down.");
			sa.assertTrue(false,"There is not option in the drop down.");
			flag = false;
		} else {
			for(int i = 0; i < allOptions.size(); i++){
				text = trim(getText(driver, allOptions.get(i), "", action.BOOLEAN));
				for(int j = 0; j < allFirmNamesInDropDown.split(",").length; j++){
					if(text.equalsIgnoreCase(allFirmNamesInDropDown.split(",")[j])){
						appLog.info(text+ " option is verified.");
						break;
					} else if (text.isEmpty()){
						appLog.info("Empty option is verifed.");
						break;
					} else {
						if(allFirmNamesInDropDown.split(",").length-1==j){
							appLog.error(text+" option is not present in the list.");
							sa.assertTrue(false,text+" option is not present in the list.");
							flag = false;
						}
					}
				}
			}
		}
		return flag;
	}
	
	/**
	 * @author Ankur Rana
	 * @param disclaimerName
	 * @param verifyConfimationPopUp
	 * @param timeOut
	 * @return true/false
	 */
	public boolean clickOnDisclaimerAcceptButton(String disclaimerName, boolean verifyConfimationPopUp, int timeOut){
		WebElement ele = FindElement(driver, "//b[text()='"+disclaimerName+"']/../following-sibling::div/a[@title='Accept']", disclaimerName+" disclaimer accept Button", action.BOOLEAN, timeOut);
		if(ele!=null){
			if(click(driver, ele, disclaimerName+" disclaimer accept button", action.BOOLEAN)){
				if(verifyConfimationPopUp == true){
					String text = trim(getText(driver, getConfirmationPopUpHeader(timeOut), "Confirmation Pop Up Header", action.BOOLEAN));
					if(text.equalsIgnoreCase("Confirmation")){
						appLog.info("Confirmation pop up header is verified.");
					} else {
						appLog.error("Confirmation Pop Up Header is not verified. Expected: Confirmation\tActual: "+text);
						sa.assertTrue(false,"Confirmation Pop Up Header is not verified. Expected: Confirmation\tActual: "+text);
					}
					text = trim(getText(driver, getConfirmationPopUpMessage(timeOut), "Confirmation Pop Up Message", action.BOOLEAN));
					if(text.equalsIgnoreCase(DisclaimerAcceptConfirmationMessage)){
						appLog.info("Confirmation message is verified.");
					} else {
						appLog.error("Confirmation pop up message is not verified. Expected: "+DisclaimerAcceptConfirmationMessage+"\tActual: "+text);
						sa.assertTrue(false,"Confirmation pop up message is not verified. Expected: "+DisclaimerAcceptConfirmationMessage+"\tActual: "+text);
					}
					if(getConfirmationPopUpOkButton(timeOut)!=null){
						appLog.info("Disclaimer accept confirmation Ok button is verified.");
					} else {
						appLog.error("Disclaimer Accept Confirmation Ok Button is not verified.");
						sa.assertTrue(false,"Disclaimer Accept Confirmation Ok Button is not verified.");
					}
				}
				if(!click(driver, getConfirmationPopUpOkButton(timeOut), "Ok Button", action.BOOLEAN)){
					refresh(driver);
				}
				return true;
			} else {
				appLog.error("Accept button of disclaimer '"+disclaimerName+"' cannot be cliked, So cannot check the functionality.");
				sa.assertTrue(false,"Accept button of disclaimer '"+disclaimerName+"' cannot be cliked, So cannot check the functionality.");
			}
		} else {
			appLog.error("Accept button for disclaimer '"+disclaimerName+"' is not present, So cannot accept the disclaimer.");
			sa.assertTrue(false,"Accept button for disclaimer '"+disclaimerName+"' is not present, So din't accept the disclaimer.");
		}
		return false;
	}
	
	/**
	 * @author Ankur Rana
	 * @param timeOut
	 * @return true/false
	 */
	public boolean verifyNoPendingDisclaimerErrorMessage(int timeOut){
		String text = trim(getText(driver, getNoPendingDisclaimerMessage(timeOut), "no pending disclaimer message", action.BOOLEAN));
		if(text.equalsIgnoreCase(NoPendingDisclaimerErrorMessage)){
			appLog.info("No pending disclaimer error message is verified.");
			return true;
		} else {
			appLog.error("No pending disclaimer error message si not verified. Expected: "+NoPendingDisclaimerErrorMessage+"\tActual: "+text);
			sa.assertTrue(false,"No pending disclaimer error message si not verified. Expected: "+NoPendingDisclaimerErrorMessage+"\tActual: "+text);
		}
		return false;
	}

	/**
	 * @author Parul Singh
	 * @param FundName
	 * @param timeout
	 * @return true/false
	 */
	public boolean clickOnExpandIcon(String FundName,int timeout){
		WebElement ele=FindElement(driver, "//span[@title='"+FundName+"']/../..//div[@class='heading_box_Investment']//img[contains(@src,'add-plus')]", "Collapsed Icon", action.SCROLLANDBOOLEAN, 60);
		if(ele!=null){
			if(click(driver, ele, "Collapsedicon", action.SCROLLANDBOOLEAN)){
				appLog.info("Clicked on expand icon");
				return true;
			}else{
				appLog.info("Not able to click on expand icon");
				}
		}
		return false;
	}
	
	/**
	 * @author Parul Singh
	 * @param fundName
	 * @param timeout
	 * @return true/false
	 */
	public boolean clickOnCollapseIcon(String fundName,int timeout){
		WebElement ele=FindElement(driver, "//span[@title='"+fundName+"']/../..//div[@class='heading_box_Investment']//img[contains(@src,'add-minus')]", "Collapsed Icon", action.SCROLLANDBOOLEAN, 60);
		if(ele!=null){
			if(click(driver, ele, "Collapsedicon", action.SCROLLANDBOOLEAN)){
				appLog.info("Clicked on collased icon");
				return true;
			}else{
				appLog.info("Not able to click on collapsed icon");
				}
		}
		return false;	
	}
	
	/**
	 * @author Parul Singh
	 * @param investorSideworkspace
	 * @param fundName
	 * @param documentName
	 * @param timeout
	 * @param scrollBoxele
	 * @return true/false
	 */
	public boolean clickOnDocument(String investorSideworkspace, String fundName, String documentName, int timeout,WebElement scrollBoxele) {
		  int j = 0;
		  WebElement ele = null;
		//  WebElement scrollBoxele = FindElement(driver, "//span[@id='myGrid_firmAllDoc-scroll-box']", "Scroll Pop Up",action.SCROLLANDBOOLEAN, timeout);
		  By elementToSearch = By.xpath("//a[text()=\"" + fundName + "\"]/../../span[text()=\"" + investorSideworkspace+ "\"]/../span[2]/a[text()=\"" + documentName + "\"]");
		  String elementTOSearch = "//a[text()=\"" + fundName + "\"]/../../span[text()=\"" + investorSideworkspace+ "\"]/../span[2]/a[text()=\"" + documentName + "\"]";
		  int widgetTotalScrollingHeight = Integer.parseInt(String.valueOf(((JavascriptExecutor) driver)
		    .executeScript("return arguments[0].scrollHeight", scrollBoxele)));
		  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(0,0)",scrollBoxele);
		  for (int i = 0; i <= widgetTotalScrollingHeight / 25; i++) {
		   if (!driver.findElements(elementToSearch).isEmpty() && driver.findElement(elementToSearch).isDisplayed()) {
		    appLog.info("Element Successfully Found and displayed");
		    ThreadSleep(500);
		    ele=FindElement(driver, elementTOSearch, "", action.BOOLEAN, 20);
		    if(ele!=null) {
		     if(click(driver, ele, "", action.BOOLEAN)){
		      System.err.println("Clicked ON Element");
		     }else {
		      appLog.error("Not able to clicke on Document Name: "+documentName);
		      return false;
		     }
		    }
		    break;
		   } else {
		    System.out.println("Not FOund: "+elementToSearch.toString());
		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTo(" + j + "," + (j = j + 45) + ")",
		      scrollBoxele);
		    try {
		     Thread.sleep(1000);
		    } catch (InterruptedException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }
		    if (i == widgetTotalScrollingHeight / 50) {
		     return false;
		    }
		   }
		  }
		  return true;
		 }
	
	
	/**
	 * @author Parul Singh
	 * @param fundName
	 * @return true/false
	 */
	public boolean verifyFundCollapsed(String fundName){
		WebElement ele=null;
		 ele=FindElement(driver, "//span[@title='"+fundName+"']/../..//div[@class='heading_box_Investment']//img[contains(@src,'add-plus')]", "Collapsed Icon", action.SCROLLANDBOOLEAN, 60);
			if(ele!=null){
				appLog.info("Fund  is displaying in collapsed mode");	
				return true;
			}else{
				appLog.info("Fund  is not displaying in collapsed mode");
				sa.assertTrue(false	, "Fund  is not displaying in collapsed mode");		
			}
			return false;
	}
	
	
	/**
	 * @author Parul Singh
	 * @param fundName
	 * @param orgName
	 * @return true/false
	 */
	public boolean verifyFundDisplaying(String fundName,String orgName){
		WebElement ele=FindElement(driver, "//span[@title='"+orgName+"']/following-sibling::span[@title='"+fundName+"']", "Fund Name", action.SCROLLANDBOOLEAN, 60);
		if(ele!=null){
			appLog.info(fundName+"Fund is displaying and fundName is verified");
			return true;
		}else{
			appLog.info(fundName+ "Fund  is not displaying and fund Name is not verified");
			}
		return false;
	}
	
	
	/**
	 * @author Parul Singh
	 * @param documentName
	 * @return true/false
	 */
	public boolean clickOnDocumentInWorkspaceContentGrid(String documentName){
		WebElement docNameXpath = FindElement(driver, "//a[text()='"+documentName+"']", "Document List",action.SCROLLANDBOOLEAN,60);
		if(docNameXpath!=null){
		if(click(driver, docNameXpath, "Document name", action.SCROLLANDBOOLEAN)){
			appLog.info("Clicked on document link successfully");
			return true;
		}else{
			appLog.info("Not able to click on document name xpath");
		}
		}else{
			appLog.info("Document name is not displaying in the grid");
		}
		return false;
	}
	
	
	/**
	 * @author Parul Singh
	 * @param fundname
	 * @param disclaimerName
	 * @param disclaimerDescription
	 * @return true/false
	 */
	public boolean verifyFundDisclaimerDescription(String fundname,String disclaimerName,String disclaimerDescription){
		WebElement ele = FindElement(driver, "//span[@title='"+fundname+"']/../..//b[text()='"+disclaimerName+"']/../following-sibling::div/p/span", " Disclaimer description", action.BOOLEAN, 20);
		WebElement ele1=FindElement(driver, "//span[@title='"+fundname+"']/../..//b[text()='"+disclaimerName+"']/../../..//preceding-sibling::div//table//td", " Disclaimer description", action.BOOLEAN, 20);
		if(ele!=null || ele1!=null){
		String	text = trim(getText(driver, ele, "discalimer description", action.BOOLEAN));
		String text1=trim(getText(driver, ele1, "discalimer description", action.BOOLEAN));
		System.out.println(">>>>>>>>>>>>>>>>>"+text);
			if(text.contains(disclaimerDescription) || text1.contains(disclaimerDescription)){
				appLog.info("disclaimer description is verified");
				return true;
			} else {
				appLog.error(" disclaimer description is not verifed");
				}
		} else {
			appLog.error("disclaimer description is not present.");
		}
		return false;
	}
	
	
	/**
	 * @author Parul Singh
	 * @param fundName
	 * @param disclaimerName
	 * @return true/false
	 */
	public boolean verifyAcceptButton(String fundName,String disclaimerName){
	WebElement	ele = FindElement(driver, "//span[@title='"+fundName+"']/../..//b[text()='"+disclaimerName+"']/../following-sibling::div/a[@title='Accept']", " disclaimer accept button", action.BOOLEAN, 60);
		if(ele!=null){
			appLog.info(fundName+"accept button is verified.");
			return true;
		} else {
			appLog.error(fundName+" disclamer accept button is not present.");
			}
		return false;
	}
	
}
