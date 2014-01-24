package com.catalystitservices.priceitdroid.model;

import java.util.ArrayList;

public class User {
	
	private int id;
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private ArrayList<Product> favorites;
	//private ArrayList<ShoppingList> shoppingLists;
	
	public int getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFavorites(ArrayList<Product> favorites) {
		this.favorites = favorites;
	}

	public User(int id, String username, String password, String email,
			String firstName, String lastName, ArrayList<Product> favorites) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.favorites = favorites;
	}

	public String getEmail() {
		return email;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public ArrayList<Product> getFavorites() {
		return favorites;
	}
	
	private User() {
		super();
	}
	
	public User( String username, String firstName, String lastName, String password, String email){
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;		
	}
	
	public User(String json){
		//TODO 
		//CURTIS DO YOUR F******* JOB HERE.
	}

}
