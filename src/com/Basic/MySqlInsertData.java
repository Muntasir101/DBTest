package com.Basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MySqlInsertData {
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
	public void printTableData() throws SQLException {
	
			// To Create database connection.
			connect = DriverManager.getConnection(sqldb_url, sqldb_uname, sqldb_pass);

			//Insert data
			PreparedStatement insert = connect.prepareStatement("INSERT INTO `users` (Name,Email,Address, Phone ) VALUES ('Leonard Hofstadter','Mikee@gmail.com','Woodcrest',0845738767");

				// the Mysql insert statement
				String query = "INSERT INTO users (Name,Email,Address, Phone ) VALUES ('Leonard Hofstadter','Mikee@gmail.com','Woodcrest','0845738767')";

				// create the mysql insert preparedstatement
				PreparedStatement preparedStmt = connect.prepareStatement(query);
				preparedStmt.execute();

		}

		@AfterTest
		public void tearDown() throws Exception {
			// Close database connection.
			if (connect != null) {
				connect.close();
			}
		}
	}
