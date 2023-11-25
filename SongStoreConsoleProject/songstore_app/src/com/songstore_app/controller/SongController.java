package com.songstore_app.controller;



import java.util.List;
import java.util.Optional;

import com.songstore_app.model.*;


public class SongController {

private final SongDAO songDAO = new SongDAO();
	
	public List<Song> getAllSong() {

		return songDAO.getAllSong().stream().sorted((m1,m2)-> m1.getTitle().compareTo(m2.getTitle()))
			.toList();
	}
	
	public boolean createSong(Song song) {
		int rowEffected = songDAO.createSong(song);
		return rowEffected > 0? true : false;
	}
	
	public boolean updateSong(Song song) {
		Optional<Song> songOpt = getSongById(song.getId());
		if(songOpt.isEmpty()) {
			return false;
		}
		
		Song updateSong = songOpt.get();
		
		if(!(song.getArtistName().isEmpty())) {
			updateSong.setArtistName(song.getArtistName().toString());
		}
		
		if(!(song.getTitle().isEmpty())) {
			updateSong.setTitle(song.getTitle());
		}
		
		if(!(song.getGenre().equals(Genre.NOT_SPECIFIED))) {
			updateSong.setGenre(song.getGenre());
		}
		
		if(song.getDurationBySecond() > 0) {
			updateSong.setDurationBySecond(song.getDurationBySecond());
		}
		
		if(song.getStarRating() > 0.0) {
			updateSong.setStarRating(song.getStarRating());
		}
		
		if(song.getReleasedDate() != null) {
			updateSong.setReleasedDate(song.getReleasedDate());
		}
		
		if(!(song.getReview().isEmpty())) {
			updateSong.setReview(song.getReview());
		}
		
		int rowEffected = songDAO.updateSong(updateSong);
		return rowEffected > 0? true : false;
	}
	
	public boolean deleteSong(Long id) {
		return songDAO.deleteSong(id) > 0? true : false;
	}
	
	public Optional<Song> getSongById(Long id) {
		return songDAO.getSongById(id);
	}
	
	public List<Song> searchSongs(String columnName,String value) {
		return songDAO.searchSong(columnName,value);
	}

}


