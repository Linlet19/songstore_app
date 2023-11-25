package com.songstore_app.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public final class DatabaseConnection {

	private DatabaseConnection() {}
	
	public static Optional<Connection> getConnection() {
		String url = "jdbc:mysql://localhost:3306/songdb?useSSL=false";
		String user = "root";
		String password = "1234";
		
		Optional<Connection> connectionOpt = Optional.empty();
		
		try {
			connectionOpt = Optional.ofNullable(DriverManager.getConnection(url, user, password));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connectionOpt;
	}
}
