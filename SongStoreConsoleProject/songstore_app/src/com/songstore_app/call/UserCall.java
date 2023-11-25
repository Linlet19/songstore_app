package com.songstore_app.call;

import java.util.Scanner;

import com.songstore_app.view.*;

public class UserCall {
	
	private final UserView userView = new UserView();

	public UserCall() {
		// TODO Auto-generated constructor stub
	}

	public void callUser(Scanner userInput) {

		while (true) {
			

			System.out.println("1.View All Users 2.Search User 3.Add New User 4.Update and Fix UserInformations 5.Delete Users");
			int operations = userInput.nextInt();
			switch (operations) {
			case 1 -> userView.showAllUsers();
			case 2 -> userView.showSearchedUser(userInput);
			case 3 -> userView.showCreateForm(userInput);
			case 4 -> userView.showUpdateForm(userInput);
			case 5 -> userView.showDeleteForm(userInput);

			default -> System.out.println("Invalid Operation");
			}
			System.out.println();
			System.out.println("continue?yes-y or no-n");
			char decision = userInput.next().charAt(0);
			if(decision == 'n') break;
		}

	}
}