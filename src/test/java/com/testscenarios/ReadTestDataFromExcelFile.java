package com.testscenarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadTestDataFromExcelFile {

	public static void main(String[] args) throws Exception {
		//Read TestData From Excel File
		
		//Where is your excel file located: get the path of the excel file
		//Use Apache POI s/w to read the excel file: Get the workbook
		//Get the sheet by name
		//Get the Row number
		//Get the column number
		
		FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\testdata\\td.xlsx");
		Workbook wb = new XSSFWorkbook(fi);
		Sheet s = wb.getSheet("ClgWeekLiveTD");
		Row r = s.getRow(6);
		Cell c = r.getCell(4);
		
		System.out.println(c.getStringCellValue());
		
	}

}
