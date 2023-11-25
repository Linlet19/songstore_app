package com.songstore_app.model;

public class User {

	private Long id;
	private String firstName;
	private String lastName;
	private String fullName;
	private Integer age;
	private String gender;
	private String country;
	
	public User(){}

	public User(Long id, String firstName, String lastName, String fullName, Integer age, String gender, String country) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.fullName = fullName;
		this.age = age;
		this.gender = gender;
		this.country = country;
	}

	public User(String firstName, String lastName, String fullName, Integer age, String gender, String country) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.fullName = fullName;
		this.age = age;
		this.gender = gender;
		this.country = country;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", fullName=" + fullName
				+ ", age=" + age + ", gender=" + gender + ", country=" + country + "]";
	}

	
	
}
