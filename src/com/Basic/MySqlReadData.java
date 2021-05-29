package com.Basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MySqlReadData {
	Connection connect = null;

	// Database URL.
	// "test" Is database name. You can change It as per your database name.
	String sqldb_url = "jdbc:mysql://localhost:3306/testdb";

	// Use your database user name here.
	String sqldb_uname = "root";

	// Use your database password here.
	String sqldb_pass = "root";

	@BeforeTest
	public void setUp() throws Exception {
		try {
			String dbClass = "com.mysql.jdbc.Driver";
			Class.forName(dbClass).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void printTableData() {
		try {
			// To Create database connection.
			connect = DriverManager.getConnection(sqldb_url, sqldb_uname, sqldb_pass);

			// preparing query with where clause using PreparedStatement.
			PreparedStatement pstmt = connect.prepareStatement("SELECT * FROM users");


			// Print results.
			ResultSet res = pstmt.executeQuery();
			System.out.println("Id " + "Name " + "Email " + "Address " + "Phone");
			
			while (res.next()) {
				System.out.println(String.format("%s - %s - %s - %s - %s",
						res.getString(1), res.getString(2), res.getString(3),
						res.getString(4),res.getString(5)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterTest
	public void tearDown() throws Exception {
		// Close database connection.
		if (connect != null) {
			connect.close();
		}
	}
}
