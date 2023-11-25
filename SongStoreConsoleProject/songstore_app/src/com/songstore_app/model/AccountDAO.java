package com.songstore_app.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.songstore_app.databaseconnection.*;

public class AccountDAO {

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

	public List<Account> getAllAccounts() {
		Optional<Connection> connectionOpt = DatabaseConnection.getConnection();
		if (connectionOpt.isEmpty()) {
			System.out.println("### Connection object is not present");
			return Collections.emptyList();
		}

		connection = connectionOpt.get();

		List<Account> accountList = new ArrayList<>();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from account;");

			while (rs.next()) {
				accountList.add(new Account(rs.getLong("id"), rs.getString("nickName"), rs.getString("email"),
						rs.getLong("phNum"), rs.getDate("firstCreatedDate").toLocalDate(),
						LinkedSocialAccount.valueOf(rs.getString("linkedSocialAccount")), rs.getString("bio")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return accountList;
	}

	// Create C
	public int createAccount(Account account) {
		Optional<Connection> connectionOpt = DatabaseConnection.getConnection();

		int rowEffected = 0;
		if (connectionOpt.isEmpty()) {
			return rowEffected;
		}
		connection = connectionOpt.get();
		try {
			pStmt = connection.prepareStatement(
					"insert into account " + "(nickName,email,phNum,firstCreatedDate,linkedSocialAccount,bio) " + "values(?,?,?,?,?,?);");
			pStmt.setString(1, account.getNickName());
			pStmt.setString(2, account.getEmail());
			pStmt.setLong(3, account.getPhNum());
			pStmt.setDate(4,Date.valueOf(account.getFirstCreatedDate()));
			pStmt.setString(5, account.getLinkedSocialAccount().toString());
			pStmt.setString(6, account.getBio());
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
	public int updateAccount(Account account) {
		Optional<Connection> connectionOpt = DatabaseConnection.getConnection();

		int rowEffected = 0;
		if (connectionOpt.isEmpty()) {
			return rowEffected;
		}
		connection = connectionOpt.get();

		try {
			pStmt = connection.prepareStatement("update account set " + "nickName=?," + "email=?," + "phNum=?,"
					+ "firstCreatedDate=?," + "linkedSocialAccount=?," + "bio=? where id=?;");
			pStmt.setString(1, account.getNickName());
			pStmt.setString(2, account.getEmail());
			pStmt.setLong(3, account.getPhNum());
			pStmt.setDate(4,Date.valueOf(account.getFirstCreatedDate()));
			pStmt.setString(5, account.getLinkedSocialAccount().toString());
			pStmt.setString(6, account.getBio());
			pStmt.setLong(7, account.getId());
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
	public int deleteAccount(Long id) {
		Optional<Connection> connectionOpt = DatabaseConnection.getConnection();

		int rowEffected = 0;
		if (connectionOpt.isEmpty()) {
			return rowEffected;
		}
		connection = connectionOpt.get();
		try {
			pStmt = connection.prepareStatement("delete from account where id=?;");
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
	public Optional<Account> getAccountById(Long id) {
		Optional<Account> accountOpt = Optional.empty();

		Optional<Connection> connectionOpt = DatabaseConnection.getConnection();

		if (connectionOpt.isEmpty()) {
			return accountOpt;
		}
		connection = connectionOpt.get();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from account where id='" + id + "';");
			while (rs.next()) {
				accountOpt = Optional.of(new Account(rs.getLong("id"), rs.getString("nickName"), rs.getString("email"),
						rs.getLong("phNum"), rs.getDate("firstCreatedDate").toLocalDate(),
						LinkedSocialAccount.valueOf(rs.getString("linkedSocialAccount")), rs.getString("bio")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return accountOpt;

	}

	// Read R search
	public List<Account> searchAccount(String columnName, String value) {
		List<Account> accounts = new ArrayList<>();

		Optional<Connection> connectionOpt = DatabaseConnection.getConnection();

		if (connectionOpt.isEmpty()) {
			return accounts;
		}
		connection = connectionOpt.get();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from account where " + columnName + "='" + value + "';");
			while (rs.next()) {
				accounts.add(new Account(rs.getLong("id"), rs.getString("nickName"), rs.getString("email"),
						rs.getLong("phNum"), rs.getDate("firstCreatedDate").toLocalDate(),
						LinkedSocialAccount.valueOf(rs.getString("linkedSocialAccount")), rs.getString("bio")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return accounts;

	}

}

