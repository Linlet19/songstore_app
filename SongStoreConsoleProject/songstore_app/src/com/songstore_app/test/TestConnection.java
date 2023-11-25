package com.songstore_app.test;

import java.sql.Connection;
import java.util.Optional;

import com.songstore_app.databaseconnection.*;

public class TestConnection {

	public static void main(String[] args) {
		
		Optional<Connection> connectionOpt = DatabaseConnection.getConnection();
		if(connectionOpt.isPresent()) {
			System.out.println("Success DB connection");
		}else {
			System.out.println("Fail DB connection");
		}
	}
}
