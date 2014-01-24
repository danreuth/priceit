package com.catalystitservices.priceitdroid.fragments.display;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.catalystitservices.priceitdroid.CreatePricePointActivity;
import com.catalystitservices.priceitdroid.CreateProductActivity;
import com.catalystitservices.priceitdroid.CreateManufacturerActivity;
import com.catalystitservices.priceitdroid.DeleteManufacturerActivity;
import com.catalystitservices.priceitdroid.EditManufacturerActivity;
import com.catalystitservices.priceitdroid.EditProductActivity;
import com.catalystitservices.priceitdroid.EditStoreActivity;
import com.catalystitservices.priceitdroid.R;
import com.catalystitservices.priceitdroid.CreateStoreActivity;

public class MainMenuFragment extends Fragment {
	
	
	
	private Button mMakeProduct;
	private Button mMakeStore;
	private Button mMakeManufacturer;
	private Button mMakePricePoint;
	private Button mEditManufacturer;
	private Button mEditProduct;

	private Button mEditStore;
	private Button mDeleteManufacturer;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		
		View main = inflater.inflate(R.layout.fragment_main_menu, container, false);
		mMakeProduct = (Button) main.findViewById(R.id.menu_product_button);
		mMakeProduct.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent createProduct = new Intent(getActivity(), CreateProductActivity.class);
				startActivity(createProduct);
				
			}
		});
		
		mMakeStore = (Button) main.findViewById(R.id.menu_store_button);
		mMakeStore.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent createStore = new Intent(getActivity(), CreateStoreActivity.class);
				startActivity(createStore);
			}
		});
		
		mMakeManufacturer = (Button) main.findViewById(R.id.menu_manufateur_button);
		mMakeManufacturer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent createManufacturer = new Intent(getActivity(), CreateManufacturerActivity.class);
				startActivity(createManufacturer);
				
			}
		});
		
		mMakePricePoint = (Button) main.findViewById(R.id.menu_price_point_button);
		mMakePricePoint.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v){
				Intent createPricePoint = new Intent(getActivity(), CreatePricePointActivity.class);
				startActivity(createPricePoint);
			}
		});
		
		mEditManufacturer = (Button) main.findViewById(R.id.menu_edit_manufacturer_button);
		mEditManufacturer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent editManufacturer = new Intent(getActivity(), EditManufacturerActivity.class);
				startActivity(editManufacturer);
				
			}
		});
		
		
		mEditProduct = (Button) main.findViewById(R.id.menu_edit_product_button);
		mEditProduct.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent editProduct = new Intent(getActivity(), EditProductActivity.class);
				startActivity(editProduct);
				
			}
		});
		
		mEditStore = (Button) main.findViewById(R.id.menu_edit_store_button);
		mEditStore.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent editStore = new Intent(getActivity(), EditStoreActivity.class);
				startActivity(editStore);
				
			}
		});
		
		mDeleteManufacturer = (Button) main.findViewById(R.id.menu_delete_manufacturer_button);
		mDeleteManufacturer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent deleteManufacturer = new Intent(getActivity(), DeleteManufacturerActivity.class);
				startActivity(deleteManufacturer);
				
			}
		
		});
		
		return main;
		
	}
}
