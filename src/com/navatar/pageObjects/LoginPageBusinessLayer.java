/**
 * 
 */
package com.navatar.pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.navatar.generic.ExcelUtils;
import com.navatar.generic.CommonLib.action;

import static com.navatar.generic.CommonVariables.*;

import java.util.List;

import static com.navatar.generic.AppListeners.appLog;
import static com.navatar.generic.CommonLib.*;
/**
 * @author Parul Singh
 *
 */
public class LoginPageBusinessLayer extends LoginPage implements LoginErrorPage {
	/**
	 * @param driver
	 */
	public LoginPageBusinessLayer(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @author Akul Bhutani
	 * @param username
	 * @param password
	 * @return true/false
	 */
	public boolean CRMLogin(String username, String password) {
		BasePageBusinessLayer bp=new BasePageBusinessLayer(driver);
		driver.get(URL);
		sendKeys(driver, getUserNameTextBox(20), username, "Username Text Box", action.THROWEXCEPTION);
		sendKeys(driver, getPasswordTextBox(20), password, "Password Text Box", action.THROWEXCEPTION);
		click(driver, getLoginButton(20), "Login Button", action.THROWEXCEPTION);
		click(driver, getLightingCloseButton(10), "Lighting Pop-Up Close Button.", action.BOOLEAN);
		ThreadSleep(1000);
		if (mode.contains("Light") || mode.contains("light") ) {
			appLog.info("Going for Lighting");
			if (switchToLighting()) {
				appLog.info("Successfully Switched to Lighting");
				return true;
			} else{
				appLog.error("Not Able to Switched to Lighting");
			}
			
		} else {

			appLog.info("Going for Classic");
			if (switchToClassic()) {
				appLog.info("Successfully Switched to Classic");
				return true;
			} else{
				appLog.error("Not Able to Switched to Classic");
			}
			
		}
		if (bp.getSalesForceLightingIcon(20) != null) {
			ThreadSleep(2000);
			click(driver, bp.getSalesForceLightingIcon(60), "sales force lighting icon", action.THROWEXCEPTION);
			ThreadSleep(1000);
			click(driver, bp.getSwitchToClassic(60), "sales force switch to classic link", action.THROWEXCEPTION);
			appLog.info("Sales Force is switched in classic mode successfully.");
		} else {
			appLog.info("Sales Force is open in classic mode.");
		}
		if (matchTitle(driver, "Salesforce - Enterprise Edition", 20) || matchTitle(driver, "Salesforce - Developer Edition", 20)) {
			appLog.info("User Successfully Logged In.");
			return true;
		} else {
			appLog.info("Kindly Check Username and Password.");
			exit("User is not able to log in.");
			return false;
		}
	}
	
	//Lightning Method....
	/**
	 * @author Akul Bhutani
	 * return true/false
	 */
	public boolean CRMlogout(String environment, String mode) {
	boolean flag = false;
	switchToDefaultContent(driver);
	if(mode.equalsIgnoreCase(Mode.Lightning.toString())) {
		List<WebElement> lst = getUserMenuTab_Lightning();
		for (int i = 0; i < lst.size(); i++) {
				if(clickUsingJavaScript(driver,lst.get(i), "User menu")) {
					ThreadSleep(500);
					flag = true;
				}else {
					if(i==lst.size()-1) {
						appLog.error("User menu tab not found");
					}
				}
		}
	}else {
		if (click(driver, getUserMenuTab(30), "User menu", action.SCROLLANDBOOLEAN)) {
			ThreadSleep(500);
			flag = true;
		}
		else {
			appLog.error("User menu tab not found");
		}
		
	}
	if(flag) {
		ThreadSleep(500);
		if (clickUsingJavaScript(driver, getLogoutButton(environment, mode, 30), "Log out button")) {
			if (matchTitle(driver, "Login | Salesforce", 20)) {
				appLog.info("User successfully Logged Out");
				return true;
			}
			else {
				appLog.error("Not logged out");
			}
		}else {
			appLog.error("Log out button in user menu tab not found");
		}
	}
	return false;
	}
	
	
	
	/**
	 * @author Akul Bhutani
	 * return true/false
	 */
	public boolean CRMlogout() {
		if (click(driver, getUserMenuTab(60), "User menu", action.SCROLLANDBOOLEAN)) {
			if (click(driver, getLogoutButton(60), "Log out button", action.BOOLEAN) ) {
				if (matchTitle(driver, "Login | Salesforce", 20)) {
					appLog.info("User successfully Logged Out");
					return true;
				}
				else {
					appLog.error("Not logged out");
				}
			}
			else {
				appLog.error("Log out button in user menu tab not found");
			}
		}
		else {
			appLog.error("User menu tab not found");
		}
		return false;
	}
	
	
/**
 * @author Akul Bhutani
 * @param username
 * @param password
 * @return
 */
public boolean investorLogin(String username, String password) {
	driver.get(InvestorURL);
	if (sendKeys(driver,getInvestorUsernameTextbox(60),username , "investor user name text box", action.BOOLEAN)) {
		if (sendKeys(driver, getInvestorPasswordTextbox(60), password, "investor password text box" ,action.BOOLEAN)) {
			if (click(driver, getInvestorLoginButton(60), "investor login button", action.BOOLEAN)) {
				appLog.info("Investor log in button clicked");
				ThreadSleep(5000);
				if (isAlertPresent(driver)) {
				appLog.error("Investor password is wrong so cannot login");
				String msg = switchToAlertAndGetMessage(driver, 30, action.GETTEXT);
				switchToAlertAndAcceptOrDecline(driver, 30, action.ACCEPT);
				return false;
				}
				else {
					appLog.info("Investor successfully logged in");
					return true;
				}
			}
			else {
				appLog.error("Investor Login button not clickable so cannot login investor");
			}
		}
		else {
			appLog.error("Password textbox is not visible so cannot login investor");
		}
	}
	else {
		appLog.error("Username textbox is not visible so cannot login investor");
	}
	return false;
}

/**
 * @return true/false
 */
public boolean investorLogout() {
	if (click(driver, getInvestorLogout(), "Investor log out button", action.SCROLLANDBOOLEAN)) {
		if (matchTitle(driver, "Navatar Investor | NavatarGroup.com", 60)) {
			appLog.info("Logged out successfully");
			return true;
		}
		else {
			appLog.info("Not logged out, still logged in as investor");
		}
	}
	else {
		appLog.info("Log out button not visible so cannot logout investor");
	}
	return false;
}

public boolean switchToClassic() {
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	jse.executeScript("window.scrollTo(0,0)");
	ThreadSleep(1000);
	if (getUserMenuTab(10) != null) {
		appLog.info("Sales Force is Already open in classic mode.");
		return true;
	} else {
		ThreadSleep(2000);
		if (click(driver, getSalesForceLightingIcon(30), "sales force lighting icon", action.SCROLLANDBOOLEAN)) {
			ThreadSleep(1000);
			if (click(driver, getSwitchToClassic(30), "sales force switch to classic link",action.SCROLLANDBOOLEAN)) {
				appLog.info("Sales Force is switched in classic mode successfully.");
				return true;
			} else {
				appLog.error("Not able to switch Classic.");
			}
		} else {
			appLog.error("Not able to click on Lighting Icon");
		}

	}
	return false;
}

public boolean switchToLighting() {
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	jse.executeScript("window.scrollTo(0,0)");
	ThreadSleep(1000);
	if (getSettingLink_Lighting(10) != null) {
		appLog.info("Sales Force is Already open in Lighting mode.");
		return true;
	} else {
		ThreadSleep(2000);
		if (click(driver, getSwitchToLightingLink(60), "sales force lighting icon", action.SCROLLANDBOOLEAN)) {
			appLog.info("Sales Force is switched in Lighting mode successfully.");
			return true;
		} else {
			appLog.error("Not able to click on Lighting Link");
		}
	}
	return false;

}

}
