/**
 * 
 */
package com.navatar.scripts;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;



/**
 * @author Ankur Rana
 *
 */
public class aaa {
	
	
//	public static void main(String[] args) throws InterruptedException {
//		System.setProperty("webdriver.chrome.driver",
//				System.getProperty("user.dir") + "\\exefiles\\chromedriver.exe");
//		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
//	    Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
//		 WebDriver d = new ChromeDriver();
//		 
//		 d.get("https://login.salesforce.com");
//		 
//		 Scanner sc = new Scanner(System.in);
//		 sc.nextLine();
//		 String parentWin =d.getWindowHandle();
//		 
//		System.err.println(parentWin);
//		 
////		 d.switchTo().frame(2);
//		 
//		 System.err.println("1");
//		 
////		 d.findElement(By.xpath("//div[@id='frworkspace']//a[@title='Upload Documents']")).click();
//		 
//		 System.err.println("2");
////		 Thread.sleep(10000);
//			Set <String> handles =d.getWindowHandles();
//			Iterator<String> it = handles.iterator();
//			//iterate through your windows
//			while (it.hasNext()){
//			String parent = it.next();
//			String newwin = it.next();
//			d.switchTo().window(newwin);
//			}
//			sc.nextLine();
//			
////			$("#btnSave").click()
//			
//			d.findElement(By.cssSelector("#btnSave")).click();
//			
//			Thread.sleep(15000);
//			
//			try 
//			{ 
//				Alert alert = d.switchTo().alert();
//				System.err.println("Alert is present .....");
//				alert.accept();
//
//			}   // try 
//			catch (NoAlertPresentException Ex) 
//			{ 
//				System.err.println("Alert is not present");  
//			}   // catch 
//			sc.next();
//		 
//	}

}
