/**
 * 
 */
package com.navatar.generic;

import com.navatar.generic.CommonLib.excelLabel;
import com.navatar.scripts.SmokeTestCase;

/**
 * @author Ankit Jaiswal
 *
 */
public class SmokeCommonVariable {

	public static String smokeExcelPath =  System.getProperty("user.dir")+"/SmokeTestCases.xlsx";
	
	
	public static String SmokeURL;
	public static String SmokeSuperAdminUserName;
	public static String SmokePassword;
	public static String SmokeSuperAdminRegistered;
	public static String SmokeGmailUserName;
	public static String SmokeGmailPassword;
	public static String SmokebrowserToLaunch;
	public static String SmoketargetLoginURL;
	public static String SmokeInvestorRegistrationURL;
	public static String SmokeBoxUserName;
	public static String SmokeBoxPassword;
	
	
	public static String SmokeAdminFirstName;
	public static String SmokeAdminLastName;
	public static String SmokeAdminEmail;
	public static String SmokeAdminProfile;
	public static String SmokeAdminUserLicense;
	public static String SmokeAdminRegistered;
	public static String SmokeAdminFirstNameSpecialCharacter;
	public static String SmokeAdminLastNameSpecialCharacter;
	public static String SmokeAdminFirstNameMaxCharacter;
	public static String SmokeAdminLastNameMaxCharacter;
	public static String SmokeAdminNavatarSupportURL;
	public static String SmokeAdminFirmName;
	public static String SmokeAdminTwitterURL;
	public static String SmokeAdminUpdatedFirmName;
	public static String SmokeAdminTitle;
	public static String SmokeAdminPhone;
	public static String SmokeAdminFirmContact;
	
	public static String SmokeCRMUser1FirstName;
	public static String SmokeCRMUser1LastName;
	public static String SmokeCRMUser1Email;
	public static String SmokeCRMUser1Profile;
	public static String SmokeCRMUser1UserLicense;
	public static String SmokeCRMUser1Registered;
	public static String SmokeCRMUser1Title;
	public static String SmokeCRMUser1Phone;
	
	
	public static String SmokeCRMUser2FirstName;
	public static String SmokeCRMUser2LastName;
	public static String SmokeCRMUser2Email;
	public static String SmokeCRMUser2Profile;
	public static String SmokeCRMUser2UserLicense;
	public static String SmokeCRMUser2Registered;
	public static String SmokeCRMUser2Title;
	public static String SmokeCRMUser2Phone;
	
	public static String SmokeCRMUser3FirstName; 
	public static String SmokeCRMUser3LastName; 
	public static String SmokeCRMUser3Email; 
	public static String SmokeCRMUser3Profile; 
	public static String SmokeCRMUser3UserLicense;
	
	
	
	public static String SmokeInstitution1,SmokeInstitution2,UpdateSmokeInstitution1;
	public static String SmokeLimitedPartner1,SmokeLimitedPartner2;
	public static String SmokeContact1FirstName,SmokeContact2FirstName,SmokeContact3FirstName;
	public static String SmokeContact1LastName,SmokeContact2LastName,SmokeContact3LastName;
	public static String SmokeContact1EmailId,SmokeContact2EmailId,SmokeContact3EmailId;
	public static String SmokeFundName1;
	public static String SmokeFundType1;
	public static String SmokeFundInvestmentCategory1;
	public static String SmokeFundRaisingName1,SmokeFundRaisingName2;
	public static String SmokePartnership1;
	public static String SmokeCommitment1Id,SmokeCommitment2Id;
	public static String SmokefolderTemplateName;
	public static String SmokeFirmName;
	public static String UpdatedSmokeLP1;
	public static String SmokeContactUpdatedPassword;
	public static String SmokeMyProfileTitle,SmokeMyProfilePhone;
	public static String SmokePhoneMyFirm,SmokeEmailMyFirm;
	public static String SmokeInvestorURL;
	public static String SmokeProfilephone,SmokeContact1IndustryDropdown,SmokeContact1BillingCity,Smoketitle,Smokephone;
	
	
	
	public SmokeCommonVariable(Object obj) {
		if(obj instanceof SmokeTestCase){
		// TODO Auto-generated constructor stub
		System.err.println(smokeExcelPath);
		
		SmokeURL = ExcelUtils.readDataFromPropertyFile("URL");
		SmokeSuperAdminUserName = ExcelUtils.readDataFromPropertyFile("SuperAdminUsername");
		SmokePassword = ExcelUtils.readDataFromPropertyFile("password");
		SmokeSuperAdminRegistered = ExcelUtils.readDataFromPropertyFile("SuperAdminRegistered");
		SmokeGmailUserName = ExcelUtils.readDataFromPropertyFile("gmailUserName");
		SmokeGmailPassword = ExcelUtils.readDataFromPropertyFile("gmailPassword");
		SmokebrowserToLaunch = ExcelUtils.readDataFromPropertyFile("Browser");
		SmoketargetLoginURL = ExcelUtils.readDataFromPropertyFile("TargetURL");
		SmokeInvestorRegistrationURL = ExcelUtils.readDataFromPropertyFile("TargetRegistrationURL");
		SmokeInvestorURL = ExcelUtils.readDataFromPropertyFile("InvestorURL");




		SmokeAdminFirstName= ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "AdminUser", excelLabel.User_First_Name); 
		SmokeAdminLastName= ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "AdminUser", excelLabel.User_Last_Name); 
		SmokeAdminEmail= ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "AdminUser", excelLabel.User_Email); 
		SmokeAdminProfile= ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "AdminUser", excelLabel.User_Profile); 
		SmokeAdminUserLicense= ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "AdminUser", excelLabel.User_License); 
		SmokeAdminRegistered= ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Registered); 
		

		SmokeCRMUser1FirstName= ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "User1", excelLabel.User_First_Name); 
		SmokeCRMUser1LastName= ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "User1", excelLabel.User_Last_Name); 
		SmokeCRMUser1Email= ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "User1", excelLabel.User_Email); 
		SmokeCRMUser1Profile= ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "User1", excelLabel.User_Profile); 
		SmokeCRMUser1UserLicense= ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "User1", excelLabel.User_License); 
		SmokeCRMUser1Registered= ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "User1", excelLabel.Registered); 
		SmokeCRMUser1Title= ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "User1", excelLabel.Title); 
		SmokeCRMUser1Phone= ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "User1", excelLabel.Phone); 
		
		SmokeCRMUser2FirstName= ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "User2", excelLabel.User_First_Name); 
		SmokeCRMUser2LastName= ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "User2", excelLabel.User_Last_Name); 
		SmokeCRMUser2Email= ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "User2", excelLabel.User_Email); 
		SmokeCRMUser2Profile= ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "User2", excelLabel.User_Profile); 
		SmokeCRMUser2UserLicense= ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "User2", excelLabel.User_License); 
		SmokeCRMUser2Registered= ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "User2", excelLabel.Registered); 
		SmokeCRMUser2Title= ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "User2", excelLabel.Title); 
		SmokeCRMUser2Phone= ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "User2", excelLabel.Phone); 
		
		
		SmokeCRMUser3FirstName= ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "User3", excelLabel.User_First_Name); 
		SmokeCRMUser3LastName= ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "User3", excelLabel.User_Last_Name); 
		SmokeCRMUser3Email= ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "User3", excelLabel.User_Email); 
		SmokeCRMUser3Profile= ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "User3", excelLabel.User_Profile); 
		SmokeCRMUser3UserLicense= ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "User3", excelLabel.User_License); 
		
		
		
		SmokeBoxUserName=ExcelUtils.readDataFromPropertyFile("BoxUsername");
		SmokeBoxPassword=ExcelUtils.readDataFromPropertyFile("BoxPassword");
		
		SmokeFirmName=ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Firm_Name);
		
		SmokeMyProfileTitle=ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Title);
		SmokeMyProfilePhone=ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "AdminUser", excelLabel.Phone);
		
		SmokePhoneMyFirm=ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "AdminUser", excelLabel.MyFirmPhone);
		SmokeEmailMyFirm=ExcelUtils.readData(smokeExcelPath,"Users",excelLabel.Variable_Name, "AdminUser", excelLabel.MyFirmEmail);
		
		
		SmokefolderTemplateName=ExcelUtils.readData(smokeExcelPath,"FolderTemp", 0,1);
		
		SmokeInstitution1=ExcelUtils.readData(smokeExcelPath,"Institutions",excelLabel.Variable_Name, "SmokeIns1", excelLabel.Institutions_Name);
		UpdateSmokeInstitution1=ExcelUtils.readData(smokeExcelPath,"Institutions",excelLabel.Variable_Name, "SmokeIns1", excelLabel.UpdateInstitution_NameFormManageInvestor);
		
		UpdatedSmokeLP1=ExcelUtils.readData(smokeExcelPath,"Limited Partner",excelLabel.Variable_Name, "SmokeLP1", excelLabel.UpdatedLimitedPartner_NameFormManageInvestor);
		
		SmokeInstitution2=ExcelUtils.readData(smokeExcelPath,"Institutions",excelLabel.Variable_Name, "SmokeIns2", excelLabel.Institutions_Name);
		SmokeLimitedPartner1=ExcelUtils.readData(smokeExcelPath,"Limited Partner",excelLabel.Variable_Name, "SmokeLP1", excelLabel.LimitedPartner_Name);
		SmokeLimitedPartner2=ExcelUtils.readData(smokeExcelPath,"Limited Partner",excelLabel.Variable_Name, "SmokeLP2", excelLabel.LimitedPartner_Name); 
		SmokeContact1FirstName=ExcelUtils.readData(smokeExcelPath,"Contact",excelLabel.Variable_Name, "SmokeC1", excelLabel.Contact_FirstName); 
		SmokeContact1LastName=ExcelUtils.readData(smokeExcelPath,"Contact",excelLabel.Variable_Name, "SmokeC1", excelLabel.Contact_LastName); 
		SmokeContact1EmailId=ExcelUtils.readData(smokeExcelPath,"Contact",excelLabel.Variable_Name, "SmokeC1", excelLabel.Contact_EmailId); 
		
		SmokeContact2FirstName=ExcelUtils.readData(smokeExcelPath,"Contact",excelLabel.Variable_Name, "SmokeC2", excelLabel.Contact_FirstName); 
		SmokeContact2LastName=ExcelUtils.readData(smokeExcelPath,"Contact",excelLabel.Variable_Name, "SmokeC2", excelLabel.Contact_LastName); 
		SmokeContact2EmailId=ExcelUtils.readData(smokeExcelPath,"Contact",excelLabel.Variable_Name, "SmokeC2", excelLabel.Contact_EmailId); 
		
		SmokeContact3FirstName=ExcelUtils.readData(smokeExcelPath,"Contact",excelLabel.Variable_Name, "SmokeC3", excelLabel.Contact_FirstName); 
		SmokeContact3LastName=ExcelUtils.readData(smokeExcelPath,"Contact",excelLabel.Variable_Name, "SmokeC3", excelLabel.Contact_LastName); 
		SmokeContact3EmailId=ExcelUtils.readData(smokeExcelPath,"Contact",excelLabel.Variable_Name, "SmokeC3", excelLabel.Contact_EmailId);
		
		 SmokeContactUpdatedPassword = ExcelUtils.readData(smokeExcelPath,"Contact",excelLabel.Variable_Name, "SmokeC1", excelLabel.Updated_Password); 
		
		
		SmokeFundName1=ExcelUtils.readData(smokeExcelPath,"Funds",excelLabel.Variable_Name, "SmokeFund1", excelLabel.Fund_Name); 
		SmokeFundType1=ExcelUtils.readData(smokeExcelPath,"Funds",excelLabel.Variable_Name, "SmokeFund1", excelLabel.Fund_Type); 
		SmokeFundInvestmentCategory1=ExcelUtils.readData(smokeExcelPath,"Funds",excelLabel.Variable_Name, "SmokeFund1", excelLabel.Fund_InvestmentCategory); 
		SmokeFundRaisingName1=ExcelUtils.readData(smokeExcelPath,"Fundraisings",excelLabel.Variable_Name, "SmokeFR1", excelLabel.FundRaising_Name); 
		SmokeFundRaisingName2=ExcelUtils.readData(smokeExcelPath,"Fundraisings",excelLabel.Variable_Name, "SmokeFR2", excelLabel.FundRaising_Name); 
		SmokePartnership1=ExcelUtils.readData(smokeExcelPath,"Partnerships",excelLabel.Variable_Name, "SmokeP1", excelLabel.PartnerShip_Name); 
		SmokeCommitment1Id=ExcelUtils.readData(smokeExcelPath,"Commitments",excelLabel.Variable_Name, "SmokeCom1", excelLabel.Commitment_ID);
		SmokeCommitment2Id=ExcelUtils.readData(smokeExcelPath,"Commitments",excelLabel.Variable_Name, "SmokeCom2", excelLabel.Commitment_ID);
		
		
		 SmokeProfilephone=ExcelUtils.readData(smokeExcelPath,"Contact",excelLabel.Variable_Name, "SmokeC1", excelLabel.Contact_Phone);
		
		
		SmokeContact1IndustryDropdown=ExcelUtils.readData(smokeExcelPath,"Contact", excelLabel.Variable_Name, "SmokeC1", excelLabel.Industry_dropdown);
		
		SmokeContact1BillingCity=ExcelUtils.readData(smokeExcelPath,"Contact", excelLabel.Variable_Name, "SmokeC1", excelLabel.Billing_city_firmprofile);
		
		 Smoketitle=ExcelUtils.readData(smokeExcelPath,"Contact",excelLabel.Variable_Name, "SmokeC1", excelLabel.Contact_Title);
		 Smokephone=ExcelUtils.readData(smokeExcelPath,"Contact",excelLabel.Variable_Name, "SmokeC1", excelLabel.Contact_Phone);
		

		}
		
	}
	
}
