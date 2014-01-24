package com.catalystitservices.priceitdroid.model;

public class Store implements java.io.Serializable {

	private String name;
	private double latitude;
	private double longitude;
	private String city;
	private String state;
	private String street;
	private String zipcode;
	
	private Store() {
		
	}
	
	public Store(String json) {
		// Call json builder
	}

	public Store(String name, double latitude, double longitude,String street,String city, String state,String zipcode) {
		super();
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.city = city;
		this.state = state;
		this.street = street;
		this.zipcode = zipcode;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	
}
