package com.utilities;
//QA will prepare the Reusable Functions

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import com.objectrepository.Locators;

public class CommonFunctions {

	public static WebDriver driver;
	public Locators loc = new Locators();
	public Properties prop = new Properties();
	public String propertyFilePath = "./src/test/resources/testdata/QA.properties";

	public void chromeBrowserLaunch() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("Chomre Browser Launched successfully****");
	}

	public void getURL(String URL) throws Exception {
		// Read the property file test data to here
		FileInputStream fi = new FileInputStream(propertyFilePath);
		prop.load(fi);
		driver.get(prop.getProperty(URL));
		System.out.println("URL open successfully****");
	}

	public String timeStamp() {
		// Get the system date and time
		Date d = new Date();
		SimpleDateFormat df = new SimpleDateFormat("ddMMMyyyy_HHmmss");
		String timestamp = df.format(d);
		return timestamp;
	}

	public void takeScreenshot() throws Exception {
		// System will take the current screen as screenshot and store it into RAM
		// location
		File abc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// Move Screenshot to specific location
		FileHandler.copy(abc, new File("./Screenshots/Class_" + timeStamp() + ".png"));
		System.out.println("takescreenshot method executed successfully****");
	}

	// sendKeys to editbox
	public void sendKeysByAnyLocator(By locator, String inputdata) throws IOException {
		// Read the property file test data to here
		FileInputStream fi = new FileInputStream(propertyFilePath);
		prop.load(fi);

		WebElement ele = driver.findElement(locator);
		// The Element is displayed or not?
		if (ele.isDisplayed()) {
			// the element is Enabled state?
			if (ele.isEnabled()) {
				// Before type the date to the editbox, clear it if any existing data
				ele.clear();
				ele.sendKeys(prop.getProperty(inputdata));
			} else {
				System.out.println("The Webelement is Disable state on screen, please check***");
			}
		} else {
			System.out.println("The Webelement is not Diaplayed on screen, please check***");
		}
		System.out.println("SendKeysByanyLocator method executed successfully***");
	}

	// Click by any element (Button/radiobutton/image/hyperlink/....)
	public void clickByAnyLocator(By locator) throws IOException {
		WebElement ele = driver.findElement(locator);
		// The Element is displayed or not?
		if (ele.isDisplayed()) {
			// the element is Enabled state?
			if (ele.isEnabled()) {
				ele.click();
			} else {
				System.out.println("The Webelement is Disable state on screen, please check***");
			}
		} else {
			System.out.println("The Webelement is not Diaplayed on screen, please check***");
		}
		System.out.println("Clickbyanyloactor method executed successfully***");
	}

}
