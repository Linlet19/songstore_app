package com.songstore_app.model;

import java.time.LocalDate;

public class Song {

	private Long id;
	private String artistName;
	private String title;
	private Genre genre;
	private Integer durationBySecond;
	private Double starRating;
	private LocalDate releasedDate;
	private String review;
	
	public Song(){}

	public Song(String artistName, String title, Genre genre, Integer durationBySecond, Double starRating,
			LocalDate releasedDate, String review) {
		super();
		this.artistName = artistName;
		this.title = title;
		this.genre = genre;
		this.durationBySecond = durationBySecond;
		this.starRating = starRating;
		this.releasedDate = releasedDate;
		this.review = review;
	}

	public Song(Long id, String artistName, String title, Genre genre, Integer durationBySecond, Double starRating,
			LocalDate releasedDate, String review) {
		super();
		this.id = id;
		this.artistName = artistName;
		this.title = title;
		this.genre = genre;
		this.durationBySecond = durationBySecond;
		this.starRating = starRating;
		this.releasedDate = releasedDate;
		this.review = review;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Integer getDurationBySecond() {
		return durationBySecond;
	}

	public void setDurationBySecond(Integer durationBySecond) {
		this.durationBySecond = durationBySecond;
	}

	public Double getStarRating() {
		return starRating;
	}

	public void setStarRating(Double starRating) {
		this.starRating = starRating;
	}

	public LocalDate getReleasedDate() {
		return releasedDate;
	}

	public void setReleasedDate(LocalDate releasedDate) {
		this.releasedDate = releasedDate;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", artistName=" + artistName + ", title=" + title + ", genre=" + genre
				+ ", durationBySecond=" + durationBySecond + ", starRating=" + starRating + ", releasedDate="
				+ releasedDate + ", review=" + review + "]";
	}

	
	
	
	
	
}
