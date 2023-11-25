package com.songstore_app.view;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.songstore_app.controller.*;
import com.songstore_app.model.*;

public class SongView {

	private final SongController songController = new SongController();

	private void showSongDetails(Song song) {
		System.out.println("Song Artist Name : " + song.getArtistName());
		System.out.println("Song Title : " + song.getTitle());
		System.out.println("Song Genre : " + song.getGenre());
		System.out.println("Song Duration : " + song.getDurationBySecond() + " sec");
		System.out.println("Song starRating : " + song.getStarRating() + "/10");
		System.out.println("Song Released Date : " + song.getReleasedDate());
		System.out.println();
		System.out.println("Song Review : ");
		System.out.println(song.getReview());
	}

	public void showAllSongs() {
		List<Song> songList = songController.getAllSong();

		int i = 1;
		System.out.println("All Songs are as follows : ");
		System.out.println("Total : " + songList.size());
		for (final Song song : songList) {
			System.out.println("Song No : " + i);
			showSongDetails(song);
			System.out.println("\r\n");
			i++;
		}
	}

	public void showSearchedSong(Scanner userInput) {

		System.out.println("Enter Search Category : no space");
		String columnName = userInput.next();
		System.out.println("Enter Search Query : ");
		userInput.nextLine();
		String value = userInput.nextLine();

		List<Song> songList = songController.searchSongs(columnName, value);

		int i = 1;
		System.out.println("All Searched Songs are as follows : ");
		System.out.println("Total : " + songList.size());
		for (final Song song : songList) {
			System.out.println("Song No : " + i);
			showSongDetails(song);
			System.out.println("\r\n");
			i++;
		}
	}

	public void showCreateForm(Scanner userInput) {
		System.out.println("Enter Artist Name : ");
		userInput.nextLine();
		String artistName = userInput.nextLine();
//		userInput.nextLine();
		System.out.println("Enter Song Title : ");
		String title = userInput.nextLine();
		System.out
				.println("Choose Genre : 1.Pop 2.Rock 3.Pop Rock 4.Hip Hop 5.Country 6.Reggae 7.Metal 8.Jazz 9.Disco");
		int rawGenre = userInput.nextInt();
		Genre genre = switch (rawGenre) {
		case 1 -> Genre.POP;
		case 2 -> Genre.ROCK;
		case 3 -> Genre.POP_ROCK;
		case 4 -> Genre.HIPHOP;
		case 5 -> Genre.COUNTRY;
		case 6 -> Genre.REGGAE;
		case 7 -> Genre.METAL;
		case 8 -> Genre.JAZZ;
		case 9 -> Genre.DISCO;
		default -> Genre.POP;
		};
		System.out.println("Enter Song Duration : sec");
		Integer durationBySecond = userInput.nextInt();
		System.out.println("Enter Song Star Rating");
		Double starRating = userInput.nextDouble();
		System.out.println("Enter Song Released Date : yyyy-MM-dd");
		LocalDate releasedDate = LocalDate.parse(userInput.next());
		System.out.println("Enter Song Review : maximum - 1000 charactors");
		userInput.nextLine();
		String review = userInput.nextLine();

		Song song = new Song(artistName, title, genre, durationBySecond, starRating, releasedDate, review);
		if (songController.createSong(song)) {
			System.out.println("created song!");
		} else {
			System.out.println("Fail to create song");
		}
	}

	public void showUpdateForm(Scanner userInput) {
		String artistName = "";
		String title = "";
		Genre genre = Genre.NOT_SPECIFIED;
		Integer durationBySecond = 0;
		Double starRating = 0.0;
		LocalDate releasedDate = null;
		String review = "";

		System.out.println("Enter Song ID : ");
		Long songId = userInput.nextLong();

		Optional<Song> songOpt = songController.getSongById(songId);
		if (songOpt.isEmpty()) {
			return;
		}

		Song dbSong = songOpt.get();

		showSongDetails(dbSong);

		while (true) {
			System.out.println(
					"Choose Input : 1.artistName 2.title 3.genre 4.duration 5.starRating 6.releasedDate 7.review");
			int inputType = userInput.nextInt();
			switch (inputType) {
			case 1 -> {
				System.out.println("Enter Artist Name : ");
				userInput.nextLine();
				artistName = userInput.nextLine();
			}

			case 2 -> {
				System.out.println("Enter Song Title : ");
				userInput.nextLine();
				title = userInput.nextLine();
			}
			case 3 -> {
				System.out.println(
						"Choose Genre : 1.Pop 2.Rock 3.Pop Rock 4.Hip Hop 5.Country 6.Reggae 7.Metal 8.Jazz 9.Disco");
				int rawGenre = userInput.nextInt();
				genre = switch (rawGenre) {
				case 1 -> Genre.POP;
				case 2 -> Genre.ROCK;
				case 3 -> Genre.POP_ROCK;
				case 4 -> Genre.HIPHOP;
				case 5 -> Genre.COUNTRY;
				case 6 -> Genre.REGGAE;
				case 7 -> Genre.METAL;
				case 8 -> Genre.JAZZ;
				case 9 -> Genre.DISCO;
				default -> Genre.POP;
				};
			}

			case 4 -> {
				System.out.println("Enter Song Duration : song");
				durationBySecond = userInput.nextInt();
			}

			case 5 -> {
				System.out.println("Enter Song Star Rating");
				starRating = userInput.nextDouble();
			}

			case 6 -> {
				System.out.println("Enter Song Released Date : yyyy-MM-dd");
				releasedDate = LocalDate.parse(userInput.next());
			}

			case 7 -> {
				System.out.println("Enter Song Review : maximum - 1000 charactors");
				userInput.nextLine();
				review = userInput.nextLine();
			}
			default -> System.out.println("Invalid Input Type");
			}/* end switch */
			System.out.println("finshed?yes-y or no-n");
			char decision = userInput.next().charAt(0);
			if (decision == 'y')
				break;
		} /* end while loop */
		Song song = new Song(songId, artistName, title, genre, durationBySecond, starRating, releasedDate, review);
		if (songController.updateSong(song)) {
			System.out.println("song with id = " + songId + " is updated!");
		} else {
			System.out.println("something wrong update fail.check you input data or id");
		}
	}/* end method */

	public void showDeleteForm(Scanner userInput) {
		System.out.println("Enter Song ID : ");
		Long id = userInput.nextLong();
		if (songController.deleteSong(id)) {
			System.out.println("song with id = " + id + " is deleted");
		} else {
			System.out.println("song is failed to delete");
		}
		
		
	}
}
