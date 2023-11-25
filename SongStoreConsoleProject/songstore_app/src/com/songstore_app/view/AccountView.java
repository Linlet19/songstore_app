package com.songstore_app.view;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.songstore_app.controller.*;
import com.songstore_app.model.*;


public class AccountView {

	private final AccountController accountController = new AccountController();

	private void showAccountDetails(Account account) {
		System.out.println("Account Nickname : " + account.getNickName());
		System.out.println("Account Email : " + account.getEmail());
		System.out.println("Account Phone Number : " + account.getPhNum());
		System.out.println("Account FirstCreatedDate : " + account.getFirstCreatedDate());
		System.out.println("Account Linked Social Account : " + (account.getLinkedSocialAccount()));
		System.out.println();
		System.out.println("Account Bio : ");
		System.out.println(account.getBio());
	}

	public void showAllAccount() {
		List<Account> accountList = accountController.getAllAccounts();

		int i = 1;
		System.out.println("All Accounts are as follows : ");
		System.out.println("Total : " + accountList.size());
		for (final Account account : accountList) {
			System.out.println("Account No : " + i);
			showAccountDetails(account);
			System.out.println("\r\n");
			i++;
		}
	}

	public void showSearchedAccount(Scanner userInput) {

		System.out.println("Enter Search Category : no space");
		String columnName = userInput.next();
		System.out.println("Enter Search Query : ");
		userInput.nextLine();
		String value = userInput.nextLine();

		List<Account> accountList = accountController.searchAccount(columnName, value);

		int i = 1;
		System.out.println("All Searched Accounts are as follows : ");
		System.out.println("Total : " + accountList.size());
		for (final Account account : accountList) {
			System.out.println("Account No : " + i);
			showAccountDetails(account);
			System.out.println("\r\n");
			i++;
		}
	}

	public void showCreateForm(Scanner userInput) {
		System.out.println("Enter Nickname : ");
		userInput.nextLine();
		String nickName = userInput.nextLine();
		System.out.println("Enter Email : ");
		String email = userInput.nextLine();
		System.out.println("Enter PhoneNumber : ");
		Long phNum = userInput.nextLong();
		System.out.println("Enter Account First Created Date : yyyy-MM-dd");
		LocalDate firstCreatedDate = LocalDate.parse(userInput.next());
		System.out.println(
				"Choose LinkedSocialAccount : 1.Facebook 2.Instagram 3.Twitter 4.Whatapps 5.Snapchat 6.Tiktok");
		int rawLinkedSocialAccount = userInput.nextInt();
		LinkedSocialAccount linkedSocialAccount = switch (rawLinkedSocialAccount) {
		case 1 -> LinkedSocialAccount.FACEBOOK;
		case 2 -> LinkedSocialAccount.INSTAGRAM;
		case 3 -> LinkedSocialAccount.TWITTER;
		case 4 -> LinkedSocialAccount.WHATSAPP;
		case 5 -> LinkedSocialAccount.SNAPCHAT;
		case 6 -> LinkedSocialAccount.TIKTOK;
		default -> LinkedSocialAccount.FACEBOOK;
		};
		System.out.println("Enter Bio : maximum - 1000 charactors");
		userInput.nextLine();
		String bio = userInput.nextLine();

		Account account = new Account(nickName, email, phNum, firstCreatedDate, linkedSocialAccount, bio);
		if (accountController.createAccount(account)) {
			System.out.println("created account!");
		} else {
			System.out.println("Fail to create account");
		}
	}

	public void showUpdateForm(Scanner userInput) {
		String nickName = "";
		String email = "";
		Long phNum = 0L;
		LocalDate firstCreatedDate = null;
		LinkedSocialAccount linkedSocialAccount = LinkedSocialAccount.NOT_SPECIFIED;
		String bio = "";

		System.out.println("Enter Account ID : ");
		Long accountId = userInput.nextLong();

		Optional<Account> accountOpt = accountController.getAccountById(accountId);
		if (accountOpt.isEmpty()) {
			return;
		}

		Account dbAccount = accountOpt.get();

		showAccountDetails(dbAccount);

		while (true) {
			System.out
					.println("Choose Input : 1.nickName 2.email 3.phNum 4.firstCreatedDate 5.linkSocialAccount 6.bio");
			int inputType = userInput.nextInt();
			switch (inputType) {
			case 1 -> {
				System.out.println("Enter Nickname : ");
				userInput.nextLine();
				nickName = userInput.nextLine();
			}

			case 2 -> {
				System.out.println("Enter Email : ");
				userInput.nextLine();
				email = userInput.nextLine();
			}

			case 3 -> {
				System.out.println("Enter Phone Number : ");
				phNum = userInput.nextLong();
			}

			case 4 -> {
				System.out.println("Enter Account First Created Date : yyyy-MM-dd");
				firstCreatedDate = LocalDate.parse(userInput.next());
			}

			case 5 -> {
				System.out.println(
						"Choose LinkedSocialAccount : 1.Facebook 2.Instagram 3.Twitter 4.Whatapps 5.Snapchat 6.Tiktok");
				int rawLinkedSocialAccount = userInput.nextInt();
				linkedSocialAccount = switch (rawLinkedSocialAccount) {
				case 1 -> LinkedSocialAccount.FACEBOOK;
				case 2 -> LinkedSocialAccount.INSTAGRAM;
				case 3 -> LinkedSocialAccount.TWITTER;
				case 4 -> LinkedSocialAccount.WHATSAPP;
				case 5 -> LinkedSocialAccount.SNAPCHAT;
				case 6 -> LinkedSocialAccount.TIKTOK;
				default -> LinkedSocialAccount.FACEBOOK;
				};
			}

			case 6 -> {
				System.out.println("Enter Bio : maximum - 1000 characters");
				userInput.nextLine();
				bio = userInput.nextLine();
			}
			default -> System.out.println("Invalid Input Type");
			}/* end switch */
			System.out.println("finshed?yes-y or no-n");
			char decision = userInput.next().charAt(0);
			if (decision == 'y')
				break;
		} /* end while loop */
		Account account = new Account(accountId, nickName, email, phNum, firstCreatedDate, linkedSocialAccount, bio);
		if (accountController.updateAccount(account)) {
			System.out.println("account with id = " + accountId + " is updated!");
		} else {
			System.out.println("something wrong update fail.check you input data or id");
		}
	}/* end method */

	public void showDeleteForm(Scanner userInput) {
		System.out.println("Enter Account ID : ");
		Long id = userInput.nextLong();
		if (accountController.deleteAccount(id)) {
			System.out.println("account with id = " + id + " is deleted");
		} else {
			System.out.println("account is failed to delete");
		}
	}

}
