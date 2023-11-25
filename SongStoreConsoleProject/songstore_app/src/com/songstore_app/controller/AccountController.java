package com.songstore_app.controller;

import java.util.List;

import java.util.Optional;

import com.songstore_app.model.*;



public class AccountController {
	
	private final AccountDAO accountDAO = new AccountDAO();
	
	public List<Account> getAllAccounts() {

		return accountDAO.getAllAccounts().stream().sorted((m1,m2)-> m1.getNickName().compareTo(m2.getNickName()))
			.toList();
	}
	
	public boolean createAccount(Account account) {
		int rowEffected = accountDAO.createAccount(account);
		return rowEffected > 0? true : false;
	}
	
	public boolean updateAccount(Account account) {
		Optional<Account> accountOpt = getAccountById(account.getId());
		if(accountOpt.isEmpty()) {
			return false;
		}
		
		Account updateAccount = accountOpt.get();
		if(!(account.getNickName().isEmpty())) {
			updateAccount.setNickName(account.getNickName());
		}

		if(!(account.getEmail().isEmpty())) {
			updateAccount.setEmail(account.getEmail());
		}
		
		if(account.getPhNum() > 0) {
			updateAccount.setPhNum(account.getPhNum());
		}
		
		if(account.getFirstCreatedDate() != null) {
			updateAccount.setFirstCreatedDate(account.getFirstCreatedDate());
		}
		
		if(!(account.getLinkedSocialAccount().equals(LinkedSocialAccount.NOT_SPECIFIED))) {
			updateAccount.setLinkedSocialAccount(account.getLinkedSocialAccount());
		}
		
		if(!(account.getBio().isEmpty())) {
			updateAccount.setBio(account.getBio());
		}
		
		int rowEffected = accountDAO.updateAccount(updateAccount);
		return rowEffected > 0? true : false;
	}
	
	public boolean deleteAccount(Long id) {
		return accountDAO.deleteAccount(id) > 0? true : false;
	}
	
	public Optional<Account> getAccountById(Long id) {
		return accountDAO.getAccountById(id);
	}
	
	public List<Account> searchAccount(String columnName,String value) {
		return accountDAO.searchAccount(columnName,value);
	}

}

