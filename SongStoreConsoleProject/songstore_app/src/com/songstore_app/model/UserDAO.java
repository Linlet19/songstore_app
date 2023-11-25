package com.songstore_app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.songstore_app.databaseconnection.*;


public class UserDAO {

	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;

	private void close() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<User> getAllUsers() {
		Optional<Connection> connectionOpt = DatabaseConnection.getConnection();
		if (connectionOpt.isEmpty()) {
			System.out.println("### Connection object is not present");
			return Collections.emptyList();
		}

		connection = connectionOpt.get();

		List<User> userList = new ArrayList<>();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from user;");

			while (rs.next()) {
				userList.add(new User(rs.getLong("id"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("fullName"), rs.getInt("age"), rs.getString("gender"), rs.getString("country")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return userList;
	}

	// Create C
	public int createUser(User user) {
		Optional<Connection> connectionOpt = DatabaseConnection.getConnection();

		int rowEffected = 0;
		if (connectionOpt.isEmpty()) {
			return rowEffected;
		}
		connection = connectionOpt.get();
		try {
			pStmt = connection.prepareStatement(
					"insert into user " + "(firstName,lastName,fullName,age,gender,country) " + "values(?,?,?,?,?,?);");
			pStmt.setString(1, user.getFirstName());
			pStmt.setString(2, user.getLastName());
			pStmt.setString(3, user.getFullName());
			pStmt.setLong(4, user.getAge());
			pStmt.setString(5, user.getGender());
			pStmt.setString(6, user.getCountry());
			rowEffected = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return rowEffected;

	}

	// Update U
	public int updateUser(User user) {
		Optional<Connection> connectionOpt = DatabaseConnection.getConnection();

		int rowEffected = 0;
		if (connectionOpt.isEmpty()) {
			return rowEffected;
		}
		connection = connectionOpt.get();

		try {
			pStmt = connection.prepareStatement("update user set " + "firstName=?," + "lastName=?," + "fullName=?,"
					+ "age=?," + "gender=?," + "country=? where id=?;");
			pStmt.setString(1, user.getFirstName());
			pStmt.setString(2, user.getLastName());
			pStmt.setString(3, user.getFullName());
			pStmt.setInt(4, user.getAge());
			pStmt.setString(5, user.getGender());
			pStmt.setString(6, user.getCountry());
			pStmt.setLong(7, user.getId());
			rowEffected = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return rowEffected;
	}

	// Delete D
	public int deleteUser(Long id) {
		Optional<Connection> connectionOpt = DatabaseConnection.getConnection();

		int rowEffected = 0;
		if (connectionOpt.isEmpty()) {
			return rowEffected;
		}
		connection = connectionOpt.get();
		try {
			pStmt = connection.prepareStatement("delete from user where id=?;");
			pStmt.setLong(1, id);
			rowEffected = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return rowEffected;

	}

//	Read by ID	
	public Optional<User> getUserById(Long id) {
		Optional<User> userOpt = Optional.empty();

		Optional<Connection> connectionOpt = DatabaseConnection.getConnection();

		if (connectionOpt.isEmpty()) {
			return userOpt;
		}
		connection = connectionOpt.get();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from user where id='" + id + "';");
			while (rs.next()) {
				userOpt = Optional.of(new User(rs.getLong("id"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("fullName"), rs.getInt("age"), rs.getString("gender"), rs.getString("country")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return userOpt;

	}

	// Read R search
	public List<User> searchUser(String columnName, String value) {
		List<User> users = new ArrayList<>();

		Optional<Connection> connectionOpt = DatabaseConnection.getConnection();

		if (connectionOpt.isEmpty()) {
			return users;
		}
		connection = connectionOpt.get();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from user where " + columnName + "='" + value + "';");
			while (rs.next()) {
				users.add(new User(rs.getLong("id"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("fullName"), rs.getInt("age"), rs.getString("gender"), rs.getString("country")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return users;

	}

}
