package com.songstore_app;

import java.util.Scanner;

import com.songstore_app.call.AccountCall;
import com.songstore_app.call.SongCall;
import com.songstore_app.call.UserCall;



public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AccountCall accountCall = new AccountCall();
		SongCall songCall = new SongCall();
		UserCall userCall = new UserCall();


		System.out.println("Song Store App");

		Scanner userInput = new Scanner(System.in);
		

			while (true) {
				System.out.println("Enter the category 1.Accounts 2.Songs 3.Users 4.Exit");
				int category = userInput.nextInt();

				switch (category) {
				case 1 -> accountCall.callAccount(userInput);

				case 2 -> songCall.callSong(userInput);

				case 3 -> userCall.callUser(userInput);

				case 4 -> {
					System.exit(0);
				}
				default -> System.out.println("Invalid Category");
				}

				System.out.println();
				System.out.println("Go to main menu : yes-y or no-n");
				char decision = userInput.next().charAt(0);
				if (decision == 'n')
					break;
			}
		
		userInput.close();
	}

	}


