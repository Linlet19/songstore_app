package com.songstore_app.model;


import java.time.LocalDate;

public class Account {

	private Long id;
	private String nickName;
	private String email;
	private Long phNum;
	private LocalDate firstCreatedDate;
	private LinkedSocialAccount linkedSocialAccount;
	private String bio;
	
	public Account(){}

	public Account(Long id, String nickName, String email, Long phNum, LocalDate firstCreatedDate,
			LinkedSocialAccount linkedSocialAccount, String bio) {
		super();
		this.id = id;
		this.nickName = nickName;
		this.email = email;
		this.phNum = phNum;
		this.firstCreatedDate = firstCreatedDate;
		this.linkedSocialAccount = linkedSocialAccount;
		this.bio = bio;
	}

	public Account(String nickName, String email, Long phNum, LocalDate firstCreatedDate,
			LinkedSocialAccount linkedSocialAccount, String bio) {
		super();
		this.nickName = nickName;
		this.email = email;
		this.phNum = phNum;
		this.firstCreatedDate = firstCreatedDate;
		this.linkedSocialAccount = linkedSocialAccount;
		this.bio = bio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhNum() {
		return phNum;
	}

	public void setPhNum(Long phNum) {
		this.phNum = phNum;
	}

	public LocalDate getFirstCreatedDate() {
		return firstCreatedDate;
	}

	public void setFirstCreatedDate(LocalDate firstCreatedDate) {
		this.firstCreatedDate = firstCreatedDate;
	}

	public LinkedSocialAccount getLinkedSocialAccount() {
		return linkedSocialAccount;
	}

	public void setLinkedSocialAccount(LinkedSocialAccount linkedSocialAccount) {
		this.linkedSocialAccount = linkedSocialAccount;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", nickName=" + nickName + ", email=" + email + ", phNum=" + phNum
				+ ", firstCreatedDate=" + firstCreatedDate + ", linkedSocialAccount=" + linkedSocialAccount + ", bio="
				+ bio + "]";
	}
	
	
}

