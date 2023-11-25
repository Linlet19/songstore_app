package com.songstore_app.view;


import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.songstore_app.controller.*;
import com.songstore_app.model.*;

public class UserView {

	private final UserController userController = new UserController();

	private void showUserDetails(User user) {
		System.out.println("User First Name : " + user.getFirstName());
		System.out.println("User Last Name : " + user.getLastName());
		System.out.println("User Full Name : " + user.getFullName());
		System.out.println("User Age : " + user.getAge());
		System.out.println("User Gender : " + user.getGender());
		System.out.println("User Country : " + user.getCountry());
	}

	public void showAllUsers() {
		List<User> userList = userController.getAllUser();

		int i = 1;
		System.out.println("All Users are as follows : ");
		System.out.println("Total : " + userList.size());
		for (final User user : userList) {
			System.out.println("User No : " + i);
			showUserDetails(user);
			System.out.println("\r\n");
			i++;
		}
	}

	public void showSearchedUser(Scanner userInput) {

		System.out.println("Enter Search Category : no space");
		String columnName = userInput.next();
		System.out.println("Enter Search Query : ");
		userInput.nextLine();
		String value = userInput.nextLine();

		List<User> userList = userController.searchUsers(columnName, value);

		int i = 1;
		System.out.println("All Searched Users are as follows : ");
		System.out.println("Total : " + userList.size());
		for (final User user : userList) {
			System.out.println("User No : " + i);
			showUserDetails(user);
			System.out.println("\r\n");
			i++;
		}
	}

	public void showCreateForm(Scanner userInput) {
		System.out.println("Enter First Name : ");
		userInput.nextLine();
		String firstName = userInput.nextLine();
		System.out.println("Enter Last Name : ");
		String lastName = userInput.nextLine();
		System.out.println("Enter Full Name : ");
		String fullName = userInput.nextLine();
		System.out.println("Enter Age : ");
		Integer age = userInput.nextInt();
		System.out.println("Enter Gender : ");
		userInput.nextLine();
		String gender = userInput.nextLine();
		System.out.println("Enter Country : ");
		String country = userInput.nextLine();

		User user = new User(firstName, lastName, fullName, age, gender, country);
		if (userController.createUser(user)) {
			System.out.println("added user!");
		} else {
			System.out.println("Fail to add user");
		}
	}

	public void showUpdateForm(Scanner userInput) {
		String firstName = "";
		String lastName = "";
		String fullName = "";
		Integer age = 0;
		String gender = "";
		String country = "";

		System.out.println("Enter User ID : ");
		Long userId = userInput.nextLong();

		Optional<User> userOpt = userController.getUserById(userId);
		if (userOpt.isEmpty()) {
			return;
		}

		User dbUser = userOpt.get();

		showUserDetails(dbUser);

		while (true) {
			System.out.println(
					"Choose Input : 1.firstName 2.lastName 3.fullName 4.age 5.gender 6.country");
			int inputType = userInput.nextInt();
			switch (inputType) {
			case 1 -> {
				System.out.println("Enter First Name : ");
				userInput.nextLine();
				firstName = userInput.nextLine();
			}

			case 2 -> {
				System.out.println("Enter Last Name : ");
				userInput.nextLine();
				lastName = userInput.nextLine();
			}
			
			case 3 -> {
				System.out.println("Enter Full Name : ");
				userInput.nextLine();
				fullName = userInput.nextLine();
			}

			case 4 -> {
				System.out.println("Enter Age : ");
				age = userInput.nextInt();
			}

			case 5 -> {
				System.out.println("Enter gender : ");
				gender = userInput.nextLine();
			}

			case 6 -> {
				System.out.println("Enter country : ");
				country = userInput.nextLine();
			}

			default -> System.out.println("Invalid Input Type");
			}/* end switch */
			System.out.println("finshed?yes-y or no-n");
			char decision = userInput.next().charAt(0);
			if (decision == 'y')
				break;
		} /* end while loop */
		User user = new User(userId, firstName, lastName, fullName, age, gender, country);
		if (userController.updateUser(user)) {
			System.out.println("user with id = " + userId + " is updated!");
		} else {
			System.out.println("something wrong update fail.check you input data or id");
		}
	}/* end method */

	public void showDeleteForm(Scanner userInput) {
		System.out.println("Enter User ID : ");
		Long id = userInput.nextLong();
		if (userController.deleteUser(id)) {
			System.out.println("user with id = " + id + " is deleted");
		} else {
			System.out.println("user is failed to delete");
		}
	}
}
