package com.catalystitservices.priceitdroid.utils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Validate {

	public final static boolean isValidEmail(CharSequence target) {
		if (target == null) {
			return false;
		} else {
			return android.util.Patterns.EMAIL_ADDRESS.matcher(target)
					.matches();
		}
	}

	

	public static boolean zip(String zipCode) {
		boolean returnResult = true;
		if (zipCode.length() != 5) {
			returnResult = false;
		}
		try {
			int testInt = Integer.parseInt(zipCode);
		} catch (Exception e) {
			returnResult = false;
		}

		return returnResult;
	}

	public static boolean UPCorPLC(String code) {
		boolean returnResult = true;
		returnResult = validString(code);

		if ((code.length() == 4) || (code.length() == 5)
				|| (code.length() == 12)) {

			try {
				int testInt = Integer.parseInt(code);
			} catch (Exception e) {
				returnResult = false;
			}

			if (code.length() == 12) {
				char[] charArray = code.toCharArray();
				int[] upc = new int[12];
				for (int i = 0; i < 12; i++) {
					upc[i] = Integer.parseInt(String.valueOf(charArray[i]));
				}
				int oddDigit = (upc[0] + upc[2] + upc[4] + upc[6] + upc[8] + upc[10]) * 3;
				int evenDigit = upc[1] + upc[3] + upc[5] + upc[7] + upc[9];
				int total = oddDigit + evenDigit;
				if ((oddDigit + evenDigit + upc[11]) % 10 == 0) {
					returnResult = true;
				} else {
					returnResult = false;
				}
			}

		} else {
			returnResult = false;
		}

		return returnResult;
	}

	public static boolean inputValid(boolean[] inputFlags) {
		boolean isValid = true;
		for (int i = 0; i < inputFlags.length; i++) {
			if (inputFlags[i] == false) {
				isValid = false;
			}
		}
		return isValid;
	}
	
	public static boolean validName(String string) {
		String regex = "^[a-zA-Z]+$";
		return regexComapre(regex, string);
		
	}
	
	public static boolean validUserName(String user){
		String regex = "^.{6,}$";
		return regexComapre(regex, user);
	}

	public static boolean validPassword(String password) {
		String regexPat = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^!*&+=])(?=\\S+$).{6,16}$";
		return regexComapre(regexPat, password);
	}
	
	
	private static boolean regexComapre(String pattern, String checking){
		Pattern reg;
		Matcher match;
		CharSequence input = checking;
		reg = Pattern.compile(pattern);
		match = reg.matcher(input);
		if(match.matches()){
			return true;
		} else {
			return false;
		}
		
	}
	
	public static boolean validString(String string){
		boolean isValid = false;
		if(string.length() > 0){
			isValid = true;
		} 
		return isValid;
	}
	
	public static boolean validCityName(String string){
		String a = "^[a-zA-Z";
		String unicode = "\\u0080-\\u024F\\";
		String special = "s\\/\\-\\)\\(\\`\\.\"\']+$";
		String regex = a + unicode + special;
		//String regex = "^[a-zA-Z ]+$"; Murica
		return regexComapre(regex, string);
	}



	public static boolean validPrice(String string) {
		boolean result = false;
		String cleanString = string.replaceAll("[$,.]", "");
		BigDecimal price = new BigDecimal(cleanString);
		if(price.compareTo(new BigDecimal(0)) > 0) {
			result = true;
		}
		return result;
	}
	
	
	
	
}
