package com.songstore_app.call;

import java.util.Scanner;

import com.songstore_app.view.*;

public class AccountCall {
	
	private final AccountView accountView = new AccountView();

	public AccountCall() {
		// TODO Auto-generated constructor stub
	}

	public void callAccount(Scanner userInput) {

		while (true) {
			

			System.out.println("1.View All Accounts 2.Search Account 3.Create New Account 4.Update or Fix Account Information 5.Delete Account");
			int operations = userInput.nextInt();
			switch (operations) {
			case 1 -> accountView.showAllAccount();
			case 2 -> accountView.showSearchedAccount(userInput);
			case 3 -> accountView.showCreateForm(userInput);
			case 4 -> accountView.showUpdateForm(userInput);
			case 5 -> accountView.showDeleteForm(userInput);

			default -> System.out.println("Invalid Operation");
			}
			System.out.println();
			System.out.println("continue?yes-y or no-n");
			char decision = userInput.next().charAt(0);
			if(decision == 'n') break;
		}

	}
}
