package com.catalystitservices.priceitdroid.model;

public class NewUser {
	private String firstName;
	private String lastName;
	private String email;
	private String oneTimeToken;
	
	
	public NewUser(String firstName, String lastName, String email,
			String oneTimeToken) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.oneTimeToken = oneTimeToken;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOneTimeToken() {
		return oneTimeToken;
	}
	public void setOneTimeToken(String oneTimeToken) {
		this.oneTimeToken = oneTimeToken;
	}
	
	
	
}
