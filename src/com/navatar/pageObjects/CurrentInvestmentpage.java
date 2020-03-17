/**
 * 
 */
package com.navatar.pageObjects;

import java.util.List;
import static com.navatar.generic.CommonLib.*;
import static com.navatar.generic.AppException.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Ankit Jaiswal
 *
 */
public class CurrentInvestmentpage extends BasePage {

	/**
	 * @param driver
	 */
	public CurrentInvestmentpage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public List<WebElement> getDocumentNameList(){
		return FindElements(driver, "//span[@id='grid_Investor-view-box-middle']//span[contains(@class,'aw-rows-normal')]/span[2]/a", "document name list");
	}
}
