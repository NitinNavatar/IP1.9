package com.navatar.pageObj;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FundraisingsPage {

	public FundraisingsPage(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@title='New']")
	private WebElement new_button;

	@FindBy(xpath = "//table[@class='detailList']//div[@class='requiredInput']/input[@name='Name']")
	private WebElement fundraising_name;

	@FindBy(xpath = "(//table[@class='detailList']//div[@class='requiredInput']/span/input)[1]")
	private WebElement fund_name;

	public WebElement getNew_button() {
		return new_button;
	}

	public WebElement getFundraising_name() {
		return fundraising_name;
	}

	public WebElement getFund_name() {
		return fund_name;
	}

	public WebElement getLegal_name() {
		return legal_name;
	}

	public WebElement getSave_button() {
		return save_button;
	}

	@FindBy(xpath = "(//table[@class='detailList']//div[@class='requiredInput']/span/input)[2]")
	private WebElement legal_name;

	@FindBy(xpath = "//td[@id='topButtonRow']/input[@title='Save']")
	private WebElement save_button;

}
