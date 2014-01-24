package com.catalystitservices.priceitdroid.utils;

public class NetworkingConstants {
	
	public static final int ONE_MIN_IN_MILL = 6000;
	public static final int FOUR_SECONDS_IN_MILL = 4000;
	public static final int EIGHT_SECONDS_IN_MILL = 8000;


	public static final String CREATE_USER_URI = "http://192.168.0.82:8080/priceitapi/OAuth/authenticate";
	public static final String CREATE_MANUFACTURER_URI = "http://192.168.0.82:8080/priceitapi/manufacturer";
	public static final String CREATE_PRODUCT_URI = "http://192.168.0.82:8080/priceitapi/product";
	public static final String CREATE_PRICE_POINT_URI = "http://192.168.0.82:8080/priceitapi/pricepoint";

	public static final String CREATE_STORE_URI = "http://192.168.0.82:8080/priceitapi/store";

	public static final String GET_MANUFACTURER_URI = "http://192.168.0.82:8080/priceitapi/manufacturer";
	public static final String GET_STORE_URI = "http://192.168.0.82:8080/priceitapi/store";
	public static final String GET_PRODUCT_URI = "http://192.168.0.82:8080/priceitapi/product";
	public static final String GET_PRICE_POINT_URI ="http://192.168.0.82:8080/priceitapi/pricepoint";


	
	public static final String DUPLICATE_NAME_ERROR = 	"E013";
}
 
