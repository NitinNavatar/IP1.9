package com.navatar.pageObj;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InstitutionsPage {

	public InstitutionsPage(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@title='New']")
	private WebElement new_button;

	@FindBy(xpath = "//input[@title='Continue']")
	private WebElement continue_button;

	@FindBy(xpath = "//select[@name='p3']")
	private WebElement record_type_dropdown;

	@FindBy(xpath = "//table[@class='detailList']//div[@class='requiredInput']/input")
	private WebElement legal_name;

	@FindBy(xpath = "//td[@id='topButtonRow']/input[@title='Save']")
	private WebElement save_button;

	public WebElement getNew_button() {
		return new_button;
	}

	public WebElement getContinue_button() {
		return continue_button;
	}

	public WebElement getRecord_type_dropdown() {
		return record_type_dropdown;
	}

	public WebElement getLegal_name() {
		return legal_name;
	}

	public WebElement getSave_button() {
		return save_button;
	}
}