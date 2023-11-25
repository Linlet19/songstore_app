package com.songstore_app.call;


import java.util.Scanner;

import com.songstore_app.view.*;

public class SongCall {
	
	private final SongView songView = new SongView();

	public SongCall() {
		// TODO Auto-generated constructor stub
	}

	public void callSong(Scanner userInput) {

		while (true) {
			

			System.out.println("1.View All Songs 2.Search songs 3.Create New songs 4.Update songs 5.Delete songs");
			int operations = userInput.nextInt();
			switch (operations) {
			case 1 -> songView.showAllSongs();
			case 2 -> songView.showSearchedSong(userInput);
			case 3 -> songView.showCreateForm(userInput);
			case 4 -> songView.showUpdateForm(userInput);
			case 5 -> songView.showDeleteForm(userInput);

			default -> System.out.println("Invalid Operation");
			}
			System.out.println();
			System.out.println("continue?yes-y or no-n");
			char decision = userInput.next().charAt(0);
			if(decision == 'n') break;
		}

	}
}

