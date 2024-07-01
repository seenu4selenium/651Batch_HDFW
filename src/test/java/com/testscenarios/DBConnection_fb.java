package com.testscenarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.utilities.CommonFunctions;

public class DBConnection_fb extends CommonFunctions {

	private static Connection cnn;
	private static Statement stmt;
	private static ResultSet rs;
	String un;
	String pwd;

	@BeforeClass
	public void beforeClass() {
		chromeBrowserLaunch();
	}

	@Test
	public void tc_01_dbconnection() throws Exception {
		// Connect the DB
		String databaseURL = "jdbc:mysql://127.0.0.1:3306/649batch";
		String username = "root";
		String password = "admin@123";

		Class.forName("com.mysql.cj.jdbc.Driver");
		cnn = DriverManager.getConnection(databaseURL, username, password);

		// Write the sql query
		String myquery = "SELECT * FROM FB;";
		stmt = cnn.createStatement();

		// Run the sql query
		rs = stmt.executeQuery(myquery);

		// The statemetn execution results will store into rs variable [temp store into
		// RMA location]
		// Print the results into Console
		// Run while loop n number of rows ...
		while (rs.next()) {
			String id = rs.getString(1);
			un = rs.getString(2);
			pwd = rs.getString(3);

			System.out.println(id + "\t" + un + "\t" + pwd + "\t");
			
			//Functional script
			getURL("fb_URL");
			driver.findElement(loc.fbLogin_email_editbox).sendKeys(un);
			driver.findElement(loc.fbLogin_password_editbox).sendKeys(pwd);
			clickByAnyLocator(loc.fbLogin_login_button);

		} // while loop end

		// Close the database connection
		cnn.close();

	}

//	@Test
//	public void tc_02() throws Exception {
//		// un and pwd value coming from Database
//		getURL("fb_URL");
//		driver.findElement(loc.fbLogin_email_editbox).sendKeys(un);
//		driver.findElement(loc.fbLogin_password_editbox).sendKeys(pwd);
//		clickByAnyLocator(loc.fbLogin_login_button);
//	}

	@AfterClass
	public void afterClass() throws Exception {
		takeScreenshot();
	}

}
