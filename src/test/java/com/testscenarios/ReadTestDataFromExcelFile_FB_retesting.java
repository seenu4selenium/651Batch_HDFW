package com.testscenarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ReadTestDataFromExcelFile_FB_retesting {

	WebDriver driver;
	String results;

	@BeforeClass
	public void beforeClass() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void f() throws Exception {
		FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\testdata\\td.xlsx");
		Workbook wb = new XSSFWorkbook(fi);
		Sheet s = wb.getSheet("FBRetest");

		for (int i = 1; i <=8; i++) {
			Row r = s.getRow(i);
			
			String un = r.getCell(0).getStringCellValue();
			String pwd = r.getCell(1).getStringCellValue();
			
			driver.get("https://www.facebook.com/");
			driver.findElement(By.name("email")).sendKeys(un);
			driver.findElement(By.id("pass")).sendKeys(pwd);
			driver.findElement(By.name("login")).click();
			Thread.sleep(5000);
			// Verify the Error message has displayed on Screen
			// if the locator is displayed on screen the size of locator is "1/2/3/..."
			// if the locator is NOT displayed on screen the size of locator is "0"
			if (driver.findElements(By.linkText("Find your account and log in.")).size() > 0
					||
				driver.findElements(By.linkText("Create a new Facebook account.")).size() > 0)
			{
				System.out.println("Given credentials are invalid");
				results = "Fail";
				System.out.println("The Results is :" + results);
			} else {
				System.out.println("Given credentials are valid");
				results = "Pass";
				System.out.println("The Results is :" + results);
			}
			Thread.sleep(1000);
			//Results will store into Excel sheet
			Cell res = r.createCell(2);
			res.setCellValue(results);
			
		}//for loop end
		//To send the results to same excel sheet(FileOutputStream)
		FileOutputStream fo = new FileOutputStream(".\\src\\test\\resources\\testdata\\td.xlsx");
		wb.write(fo);
		wb.close();

	}
}
