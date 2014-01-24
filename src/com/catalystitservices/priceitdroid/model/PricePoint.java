package com.catalystitservices.priceitdroid.model;

import java.math.BigDecimal;

public class PricePoint {
	
	private int id;
	private User user;
	private Product productId;
	private String date;
	private Store store;
	private BigDecimal priceInBigDecimal;
	private String price;
	private String status;
	private String saleEndDate;
	
	private PricePoint (){
		//no null price points
	}

	public PricePoint(User user, Product productId, String date, Store store,
			BigDecimal price, String status, String saleEndDate) {
		this.user = user;
		this.productId = productId;
		this.date = date;
		this.store = store;
		this.priceInBigDecimal = price;
		this.price = price.toPlainString();
		this.status = status;
		this.saleEndDate = saleEndDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProductId() {
		return productId;
	}

	public void setProductId(Product productId) {
		this.productId = productId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public BigDecimal getPrice() {
		return priceInBigDecimal;
	}

	public void setPrice(BigDecimal price) {
		this.priceInBigDecimal = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSaleEndDate() {
		return saleEndDate;
	}

	public void setSaleEndDate(String saleEndDate) {
		this.saleEndDate = saleEndDate;
	}
}