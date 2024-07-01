package com.testscenarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
	
	private static Connection cnn;
	private static Statement stmt;
	private static ResultSet rs;

	public static void main(String[] args) throws Exception {
		// Connect the DB
		String databaseURL = "jdbc:mysql://127.0.0.1:3306/649batch";
		String username = "root";
		String password = "admin@123";

		Class.forName("com.mysql.cj.jdbc.Driver");
		cnn = DriverManager.getConnection(databaseURL, username, password);

		// Write the sql query
		String myquery = "SELECT * FROM STUDENT_DETAILS;";
		stmt = cnn.createStatement();

		// Run the sql query
		rs = stmt.executeQuery(myquery);

		// The statemetn execution results will store into rs variable [temp store into
		// RMA location]
		// Print the results into Console
		// Run while loop n number of rows ...
		while (rs.next()) {			
			String id = rs.getString(1);
			String name = rs.getString(2);
			String email = rs.getString(3);
			String city = rs.getString(4);
			String state = rs.getString(5);
			String county = rs.getString(6);

			System.out.println(id + "\t" + name + "\t" + email + "\t" + city + "\t" + state + "\t" + county + "\t");

		} // while loop end

		// Close the database connection
		cnn.close();

	}
}
