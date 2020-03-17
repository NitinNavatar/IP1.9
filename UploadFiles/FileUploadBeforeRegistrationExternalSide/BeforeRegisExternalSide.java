package com.navatar.pageObj;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FundsPage {

	public FundsPage(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@title='New']")
	private WebElement new_button;

	@FindBy(xpath = "//table[@class='detailList']//div[@class='requiredInput']/input")
	private WebElement fund_name;

	@FindBy(xpath = "//table[@class='detailList']//div[@class='requiredInput']/span/select")
	private WebElement fund_type;

	@FindBy(xpath = "//td[@id='topButtonRow']/input[@title='Save']")
	private WebElement save_button;

	public WebElement getNew_button() {
		return new_button;
	}

	public WebElement getFund_name() {
		return fund_name;
	}

	public WebElement getFund_type() {
		return fund_type;
	}

	public WebElement getSave_button() {
		return save_button;
	}

}
