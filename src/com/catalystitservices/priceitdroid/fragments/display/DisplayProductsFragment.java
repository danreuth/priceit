package com.catalystitservices.priceitdroid.fragments.display;

import java.io.Serializable;
import java.util.ArrayList;

import modellists.GetModelList;
import modellists.ManufacturerList;
import modellists.ProductList;




import com.catalystitservices.priceitdroid.R;
import com.catalystitservices.priceitdroid.fragments.SpiceManagerFragment;
import com.catalystitservices.priceitdroid.fragments.edit.EditManufacturerFragment;
import com.catalystitservices.priceitdroid.fragments.edit.EditProductFragment;
import com.catalystitservices.priceitdroid.model.Manufacturer;
import com.catalystitservices.priceitdroid.model.Product;
import com.catalystitservices.priceitdroid.utils.NetworkThreader;
import com.catalystitservices.priceitdroid.utils.NetworkingConstants;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.location.Address;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DisplayProductsFragment extends SpiceManagerFragment{
		
		NetworkThreader networkThread;
		LinearLayout procTableScrollView;
		ProductList productList;
		View view;
		ViewGroup pContainer;
		
		private ArrayList<String> pList = new ArrayList<String>();
		

 
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			networkThread = new NetworkThreader(NetworkingConstants.GET_PRODUCT_URI, spiceManager, DisplayProductsFragment.this, null);
			networkThread.getObjectFromServer("listofproducts"); 

		}
		

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			view = inflater.inflate(R.layout.fragment_edit_product,
					container, false);
			pContainer = container;
			return view;
			
		}
		
		private void insertNameInScrollView(String name, int index) {
			 LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
			 View procRow = inflater.inflate(R.layout.fragment_product_name,null);
			 TextView procNameTextView = (TextView)procRow.findViewById(R.id.product_name_text_view);
			 procNameTextView.setText(name);
			 
			 Button productNameButton = (Button) procRow.findViewById(R.id.selectProductButton);
			 productNameButton.setOnClickListener(getroductActivityListener);
			 
			 procTableScrollView.addView(procRow,index);
			 
		}
		
		public OnClickListener getroductActivityListener = new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				TableRow tableRow = (TableRow) v.getParent();
				TextView productTextView = (TextView) tableRow.findViewById(R.id.product_name_text_view);
				String selectedProductName = productTextView.getText().toString();
				Product chosenProduct = null;
				for(Product product : productList){
					if(product.getName().equals(selectedProductName)){
						chosenProduct = product;
					}
				}
				
	
				Fragment fragment = new EditProductFragment();
	
				Bundle bundle = new Bundle();
		
				bundle.putSerializable("chosenProduct",chosenProduct);
		
				fragment.setArguments(bundle);
		
				FragmentManager fm = getFragmentManager();
		//		Fragment editFragment = new EditProductFragment();
				FragmentTransaction transaction = fm.beginTransaction();
				transaction.replace(pContainer.getId(), fragment);
				transaction.remove(DisplayProductsFragment.this);
				transaction.commit();
				
				
				
				
			}
			
		};


		@Override
		public void doSomeThang(GetModelList result) {
	
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			productList = mapper.convertValue(result, ProductList.class);
			for(Product product : productList){
				pList.add(product.getName());
			}
			
			
			procTableScrollView = (LinearLayout) view.findViewById(R.id.productScrollView);
			if(procTableScrollView == null){
				Log.e("null", "null");
			}
			if(pList != null){
				for(int i=0; i<pList.size();i++){
					insertNameInScrollView(pList.get(i),i);
				}
			}
			
		}

		@Override
		public void errorSomeThang(String errorCode) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void feedbackSomeThang(Object object) {
			// TODO Auto-generated method stub
			
		}





}
