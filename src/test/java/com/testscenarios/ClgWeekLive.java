package com.testscenarios;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ClgWeekLive {

	public static void main(String[] args) throws Exception {
		WebDriver driver;
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\testdata\\td.xlsx");
		Workbook wb = new XSSFWorkbook(fi);
		Sheet s = wb.getSheet("ClgTD");
		
		Row r = s.getRow(1);
		
		String url = r.getCell(0).getStringCellValue();
		String firstName = r.getCell(1).getStringCellValue();
		String country = r.getCell(2).getStringCellValue();

		
		driver.get(url);		
		Thread.sleep(5000);		
		// Edibox
		driver.findElement(By.xpath("//*[@id='firstName']")).sendKeys(firstName);		
		//dropdown
		new Select(driver.findElement(By.id("country"))).selectByVisibleText(country);
		
		
		
	}

}
