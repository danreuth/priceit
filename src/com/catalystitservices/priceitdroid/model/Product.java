package com.catalystitservices.priceitdroid.model;

import android.graphics.Bitmap;


public class Product implements java.io.Serializable{
	private int id;

	private String name;
	private String codeNum;
	private String codeType;
	//private byte[] image;
	private Manufacturer manufacturer;
	private String type;
	private String description;
	
	private Product(){
		//no null products
	}
	
	public Product(String name, String codeNum, String codeType, Manufacturer manufacturer, String type, String description) {
		this.name = name;
		this.codeNum = codeNum;
		this.codeType = codeType;
		this.manufacturer = manufacturer;
		this.type = type;
		this.description = description;
	}
	
	

//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCodeNum() {
		return codeNum;
	}

	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

//	public byte[] getImage() {
//		return image;
//	}
//
//	public void setImage(byte[] image) {
//		this.image = image;
//	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
