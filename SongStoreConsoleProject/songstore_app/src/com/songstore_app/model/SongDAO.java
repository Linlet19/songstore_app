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



public class SongDAO {

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

	// Read R
	public List<Song> getAllSong() {
		Optional<Connection> connectionOpt = DatabaseConnection.getConnection();
		if (connectionOpt.isEmpty()) {
			System.out.println("### Connection object is not present");
			return Collections.emptyList();
		}

		connection = connectionOpt.get();

		List<Song> songList = new ArrayList<>();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from song;");

			while (rs.next()) {
				songList.add(new Song(rs.getLong("id"), rs.getString("artistName"), rs.getString("title"),
						Genre.valueOf(rs.getString("genre")), rs.getInt("durationBySecond"), rs.getDouble("starRating"),
						rs.getDate("releasedDate").toLocalDate(), rs.getString("review")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return songList;
	}

	// Create C
	public int createSong(Song song) {
		Optional<Connection> connectionOpt = DatabaseConnection.getConnection();

		int rowEffected = 0;
		if (connectionOpt.isEmpty()) {
			return rowEffected;
		}
		connection = connectionOpt.get();
		try {
			pStmt = connection.prepareStatement(
					"insert into song " + "(artistName,title,genre,durationBySecond,starRating,releasedDate,review) "
							+ "values(?,?,?,?,?,?,?);");
			pStmt.setString(1, song.getArtistName());
			pStmt.setString(2, song.getTitle());
			pStmt.setString(3, song.getGenre().toString());
			pStmt.setInt(4, song.getDurationBySecond());
			pStmt.setDouble(5, song.getStarRating());
			pStmt.setDate(6, Date.valueOf(song.getReleasedDate()));
			pStmt.setString(7, song.getReview());
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
	public int updateSong(Song song) {
		Optional<Connection> connectionOpt = DatabaseConnection.getConnection();

		int rowEffected = 0;
		if (connectionOpt.isEmpty()) {
			return rowEffected;
		}
		connection = connectionOpt.get();

		try {
			pStmt = connection.prepareStatement("update song set " + "artistName=?," + "title=?," + "genre=?,"
					+ "durationBySecond=?," + "starRating=?," + "releasedDate=?," + "review=? where id=?;");
			pStmt.setString(1, song.getArtistName());
			pStmt.setString(2, song.getTitle());
			pStmt.setString(3, song.getGenre().toString());
			pStmt.setInt(4, song.getDurationBySecond());
			pStmt.setDouble(5, song.getStarRating());
			pStmt.setDate(6, Date.valueOf(song.getReleasedDate()));
			pStmt.setString(7, song.getReview());
			pStmt.setLong(8, song.getId());
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
	public int deleteSong(Long id) {
		Optional<Connection> connectionOpt = DatabaseConnection.getConnection();

		int rowEffected = 0;
		if (connectionOpt.isEmpty()) {
			return rowEffected;
		}
		connection = connectionOpt.get();
		try {
			pStmt = connection.prepareStatement("delete from song where id=?;");
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
	public Optional<Song> getSongById(Long id) {
		Optional<Song> songOpt = Optional.empty();

		Optional<Connection> connectionOpt = DatabaseConnection.getConnection();

		if (connectionOpt.isEmpty()) {
			return songOpt;
		}
		connection = connectionOpt.get();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from song where id='" + id + "';");
			while (rs.next()) {
				songOpt = Optional.of(new Song(rs.getLong("id"), rs.getString("artistName"), rs.getString("title"),
						Genre.valueOf(rs.getString("genre")), rs.getInt("durationBySecond"), rs.getDouble("starRating"),
						rs.getDate("releasedDate").toLocalDate(), rs.getString("review")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return songOpt;

	}

	// Read R search
	public List<Song> searchSong(String columnName, String value) {
		List<Song> songs = new ArrayList<>();

		Optional<Connection> connectionOpt = DatabaseConnection.getConnection();

		if (connectionOpt.isEmpty()) {
			return songs;
		}
		connection = connectionOpt.get();
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from song where " + columnName + "='" + value + "';");
			while (rs.next()) {
				songs.add(new Song(rs.getLong("id"), rs.getString("artistName"), rs.getString("title"),
						Genre.valueOf(rs.getString("genre")), rs.getInt("durationBySecond"), rs.getDouble("starRating"),
						rs.getDate("releasedDate").toLocalDate(), rs.getString("review")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return songs;

	}

}
