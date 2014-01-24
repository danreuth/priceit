package com.catalystitservices.priceitdroid.model;

public class Image {

	private int id;
	private byte[] image;
	
	private Image (){
		//no null instances
	}
	
	public Image(byte[] image){
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
}
