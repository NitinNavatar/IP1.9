package com.navatar.scripts;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

import com.navatar.generic.AppException;
import com.navatar.generic.BaseLib;
import com.navatar.generic.CommonLib;
import com.navatar.generic.CommonLib.action;
import com.navatar.generic.CommonLib.excelLabel;
import com.navatar.generic.ExcelUtils;
import com.navatar.pageObjects.CommitmentPageBusinessLayer;
import com.navatar.pageObjects.PartnershipPageBusinessLayer;

import static com.navatar.generic.AppListeners.*;
import static com.navatar.generic.CommonLib.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DummyClass {
	
//	@SuppressWarnings("rawtypes")
//	public static void main(String[] args) {
//		CommonLib.getClasses("com.navatar.scripts");
//		try {
////			System.err.println(System.getProperty("user.dir"));
//		List<Class> classes	= CommonLib.findClasses(new File(System.getProperty("user.dir")+"\\target\\classes\\com\\navatar\\scripts"), "com.navatar.scripts");
//		int j = 0;
//		for(Class i : classes){
////			System.err.println(i.toGenericString());
//			System.err.println("com."+i.toString().split("class com.")[1]);
//			
//		}
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	public static void main(String[] args) {
		System.err.println(getDateAccToTimeZone("GMT-08:00 America/Los Angeles PST", "MM-dd-yy__hh-mm-ss"));
	}
}
