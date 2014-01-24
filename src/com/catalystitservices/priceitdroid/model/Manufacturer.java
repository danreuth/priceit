package com.catalystitservices.priceitdroid.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Manufacturer implements java.io.Serializable {

	//TODO make design decision on using or omitting getters and setters
	
	
	private String id;
	//TODO determine if we even need the primary key from the database
	
	private String name;
	private String city;
	private String state;
	private String street;
	private String zipcode;
	
	private Manufacturer (){
		// prevent access to generic constructor
	}
	
	public Manufacturer(String name,String street, String city, String state,String zipcode) {
		this.name = name;
		this.city = city;
		this.state = state;
		this.street = street;
		this.zipcode = zipcode;
		
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public static Manufacturer Manufacturer(String json){
		//TODO
		//-1. this is a method, masquerading as a constructor, because #wildwest
		//0. instantiate an empty Manufacturer
		//1. pass the json string, and class type to the utils.JsonWhateverMapper
		//2. return the returned class object
		//Manufacturer test = new Manufacturer();
		//return Json.toObject(test.class, json);
		return new Manufacturer();
	}
	


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
}