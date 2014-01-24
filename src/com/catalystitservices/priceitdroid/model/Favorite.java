package com.catalystitservices.priceitdroid.model;

import java.util.ArrayList;
import java.util.List;

public class Favorite {
	private int productId;
	private int userId;
	private List<Integer> substituteItems;
	private final int SUBSTITUES_MAX = 2;
	public Favorite(int productId){
		this.productId = productId;
		///this.userId = getcurrentuserid
		substituteItems = new ArrayList<Integer>();	
	}
	public int getProductId() {
		return productId;
	}
	public int getUserId() {
		return userId;
	}
	public int getSUBSTITUES_MAX() {
		return SUBSTITUES_MAX;
	}
	public boolean isSubsitiuesListFull(){
		if(substituteItems.size() >= SUBSTITUES_MAX){
			return true;
		}
		return false;
	}
	
	public void addProductToSubstituesList(int nuProductId){
		substituteItems.add(nuProductId);
	}
	
	public void removeProductFromSubstituesList(int searchProductId){
		for(int i = 0; i < substituteItems.size(); i++){
			if(substituteItems.get(i) == searchProductId){
				substituteItems.remove(i);
			}
		}
	}

	
}
