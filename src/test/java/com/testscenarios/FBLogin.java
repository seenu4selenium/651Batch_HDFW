package com.testscenarios;

import org.testng.annotations.Test;

import com.objectrepository.Locators;
import com.utilities.CommonFunctions;

import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;

public class FBLogin extends CommonFunctions {
	@BeforeClass
	public void beforeClass() {
		chromeBrowserLaunch();
	}
	@Test
	public void f() throws Exception {
		getURL("fb_URL");
		sendKeysByAnyLocator(loc.fbLogin_email_editbox, "fb_UserName");
		sendKeysByAnyLocator(loc.fbLogin_password_editbox, "fb_password");
		clickByAnyLocator(loc.fbLogin_login_button);
	}
	@AfterClass
	public void afterClass() throws Exception {
		takeScreenshot();
	}
}
