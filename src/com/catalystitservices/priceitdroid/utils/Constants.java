 package com.catalystitservices.priceitdroid.utils;


import java.util.Map;
import java.util.TreeMap;

public class Constants {
	public static final String EDIT="Edit";
	public static final String DELETE="Delete";
	
	public static final String PRICE_POINT_STATUS_SALE = "sale";
	public static final String PRICE_POINT_STATUS_REGULAR = "reg";
	public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	public static final int SELECT_IMAGE_FROM_GALLERY_REQUEST_CODE = 101;
	public static final int SELECT_DATE_REQUEST_CODE = 102;
	public static final int SELECT_END_DATE_REQUEST_CODE = 103;
	public static final String FILE_URI_KEY = "com.catalystitservices.priceitdroid.FILE_URI";
	public static final String USER_REGISTRATION_TITLE = "Create User";
	public static final Map<String, String> STATE_MAP = new TreeMap<String, String>();
	public static final boolean CHRIS_WILL_GET_FIRED = false;

	public static final int MAKE_MAN_REQUEST_CODE = 102;
	public static final String MAN_NAME = "Name";

	public static final String AUTH_TOKEN = "com.catalystitservices.priceitdroid.AUTH_TOKEN";

    static {
        STATE_MAP.put("Alabama", "AL");
        STATE_MAP.put("Alaska", "AK");
        STATE_MAP.put("Arizona", "AZ");
		STATE_MAP.put("Arkansas", "AR");
		STATE_MAP.put("California", "CA");
		STATE_MAP.put("Colorado", "CO");
		STATE_MAP.put("Connecticut", "CT");
		STATE_MAP.put("Delaware", "DE");
		STATE_MAP.put("Florida", "FL");
		STATE_MAP.put("Georgia", "GA");
		STATE_MAP.put("Hawaii", "HI");
		STATE_MAP.put("Idaho", "ID");
		STATE_MAP.put("Illinois", "IL");
		STATE_MAP.put("Indiana", "IN");
		STATE_MAP.put("Iowa", "IA");
		STATE_MAP.put("Kansas", "KS");
		STATE_MAP.put("Kentucky", "KY");
		STATE_MAP.put("Louisiana", "LA");
		STATE_MAP.put("Maine", "ME");
		STATE_MAP.put("Maryland", "MD");
		STATE_MAP.put("Massachusetts", "MA");
		STATE_MAP.put("Michigan", "MI");
		STATE_MAP.put("Minnesota", "MN");
		STATE_MAP.put("Mississippi", "MS");
		STATE_MAP.put("Missouri", "MO");
		STATE_MAP.put("Montana", "MT");
		STATE_MAP.put("Nebraska", "NE");
		STATE_MAP.put("Nevada", "NV");
		STATE_MAP.put("New Hampshire", "NH");
		STATE_MAP.put("New Jersey", "NJ");
		STATE_MAP.put("New Mexico", "NM");
		STATE_MAP.put("New York", "NY");
		STATE_MAP.put("North Carolina", "NC");
		STATE_MAP.put("North Dakota", "ND");
		STATE_MAP.put("Ohio", "OH");
		STATE_MAP.put("Oklahoma", "OK");
		STATE_MAP.put("Oregon", "OR");
		STATE_MAP.put("Pennsylvania", "PA");
		STATE_MAP.put("Rhode Island", "RI");
		STATE_MAP.put("South Carolina", "SC");
		STATE_MAP.put("South Dakota", "SD");
		STATE_MAP.put("Tennessee", "TN");
		STATE_MAP.put("Texas", "TX");
		STATE_MAP.put("Utah", "UT");
		STATE_MAP.put("Vermont", "VT");
		STATE_MAP.put("Virginia", "VA");
		STATE_MAP.put("Washington", "WA");
		STATE_MAP.put("West Virginia", "WV");
		STATE_MAP.put("Wisconsin", "WI");
		STATE_MAP.put("Wyoming", "WY");
    }
}
