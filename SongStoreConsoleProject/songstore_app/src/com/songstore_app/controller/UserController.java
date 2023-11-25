package com.songstore_app.controller;

import java.util.List;
import java.util.Optional;


import com.songstore_app.model.*;


public class UserController {

private final UserDAO userDAO = new UserDAO();
	
	public List<User> getAllUser() {

		return userDAO.getAllUsers().stream().sorted((m1,m2)-> m1.getFirstName().compareTo(m2.getFirstName()))
			.toList();
	}
	
	public boolean createUser(User user) {
		int rowEffected = userDAO.createUser(user);
		return rowEffected > 0? true : false;
	}
	
	public boolean updateUser(User user) {
		Optional<User> userOpt = getUserById(user.getId());
		if(userOpt.isEmpty()) {
			return false;
		}
		
		User updateUser = userOpt.get();
		
		if(!(user.getFirstName().isEmpty())) {
			updateUser.setFirstName(user.getFirstName().toString());
		}
		
		if(!(user.getLastName().isEmpty())) {
			updateUser.setLastName(user.getLastName().toString());
		}
		
		if(user.getAge() > 0) {
			updateUser.setAge(user.getAge());
		}
			
		if(!(user.getGender().isEmpty())) {
			updateUser.setGender(user.getGender());
		}
		
		if(!(user.getCountry().isEmpty())) {
			updateUser.setCountry(user.getCountry());
		}
		
		int rowEffected = userDAO.updateUser(updateUser);
		return rowEffected > 0? true : false;
	}
	
	public boolean deleteUser(Long id) {
		return userDAO.deleteUser(id) > 0? true : false;
	}
	
	public Optional<User> getUserById(Long id) {
		return userDAO.getUserById(id);
	}
	
	public List<User> searchUsers(String columnName,String value) {
		return userDAO.searchUser(columnName,value);
	}

	
	}




