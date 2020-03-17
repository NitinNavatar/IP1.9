/**
 * 
 */
package com.navatar.pageObjects;

/**
 * @author Akul Bhutani
 *
 */
public interface LoginErrorPage {
String validationError= "Validation Error: Value is required.";
String cannotResuldOldPassword = "Error: You cannot reuse this old password";
String passwordNotMatch = "The passwords do not match";
String pleaseEnterYourEmailForgotPassword = "Please enter your email address to reset your password.";
public static String resetPasswordEnterAValueErrorMsg="Please enter a value.";
public static String forgotPasswordSubmitButtonErrorMessageWithUnInvitedContactEmailValue="This email address does not exist. Please try again with a different email address.";
public static String forgotPasswordSubmitButtonErrorMessageWithInvitedButNotRegisteredContactEmailValue="This email address is not registered. Please click here to register.";
public static String resetPasswordRegistrationPagePasswordErrorMessage="Enter at least 8 characters including letters and numbers.";
public static String resetPasswordRegistrationPageClickHereMessage="If you have already registered, please click here to login.";
public static String targetRegisterPageErrorMessage="* Please enter the required fields.";
public static String resetPasswordConfirmationTemproryPasswdMsg="An email has been sent to you to reset your password.";
public static String resetPasswordErrorMsg="Please enter your email address to reset your password.";
public static String resetPasswordLinkExpiredErrorMessage="The link for resetting the password has already been used."; 
public static String resetPasswordPasswordChangeMessage="Your password has been changed. Please log in.";
public static String resetPasswordSessionErrorMessage="There is a session conflict. Please use a new browser.";
public static String resetPasswordPasswordDoNotMatchErrorMessage="The Username or Password you provided do not match. Please check your credentials.";










}


