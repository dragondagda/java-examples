package de.hsb.jdbc.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static Connection INSTANCE;
	
	public static Connection getInstance(){
		if(INSTANCE==null){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				INSTANCE = DriverManager.getConnection("jdbc:mysql://localhost:3306/tech_groups", "root", "root");
			} catch (SQLException e) {
				System.err.println("SQLException occured: " + e.getMessage());
			} catch (ClassNotFoundException e) {
				System.err.println("Driver not found");
			}
		}
		
		return INSTANCE;
	}
	
	public static void closeConnection(){
		if(INSTANCE != null){
			try {
				INSTANCE.close();
			} catch (SQLException e) {
				System.err.println("Failed closing Database connection");
			}
			INSTANCE = null;
		}
	}
}
