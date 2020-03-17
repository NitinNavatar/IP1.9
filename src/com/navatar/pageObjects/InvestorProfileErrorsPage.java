/**
 * 
 */
package com.navatar.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static com.navatar.generic.CommonLib.*;
/**
 * @author Akul Bhutani
 *
 */
public interface InvestorProfileErrorsPage{
public String pleaseEnterRequiredFields = "Please enter the required fields.";
public String pleaseSpecifyFileToUpload = "Error: Please specify a file to upload, or use the \"Browse\" button to locate it in your local file system.";
public String sizeGreaterError = "Unable to upload greaterthantwomb.png because it exceeds the maximum file size (2MB).";
public String fileTypeNotCorrect = "Error: File Type Not Correct.";
public String pleaseEnterRequiredFieldsChangePassword = "Please enter the required field(s)";
public String oldPasswordInvalid = "Error: Your old password is invalid.";
public String changeLogoQuestion = "Are you sure you want to change the logo ?";
public String changeProfilePictureQuestion = "Are you sure you want to change the profile picture?";
public String passwordMustHaveLettersAndNumbers = "Your password must have a mix of letters and numbers";
public String passwordsDoNotMatch = "The passwords entered do not match.";
public String pleaseEnterValidMinInvestmentSize = "Please enter valid value Min. Investment Size.";
public String pleaseEnterValidMaxInvestmentSize = "Please enter valid value Max. Investment Size.";
public String pleaseEnterValidAUM = "Please enter a valid AUM.";
public String descriptionError = "Account Description cannot be more than 32,000 characters long.";
}
