package com.catalystitservices.priceitdroid.fragments.display;

import java.util.ArrayList;

import modellists.GetModelList;
import modellists.ManufacturerList;
import modellists.StoreList;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.catalystitservices.priceitdroid.R;
import com.catalystitservices.priceitdroid.fragments.SpiceManagerFragment;
import com.catalystitservices.priceitdroid.fragments.edit.EditManufacturerFragment;
import com.catalystitservices.priceitdroid.fragments.edit.EditStoreFragment;
import com.catalystitservices.priceitdroid.model.Manufacturer;
import com.catalystitservices.priceitdroid.model.Store;
import com.catalystitservices.priceitdroid.utils.NetworkThreader;
import com.catalystitservices.priceitdroid.utils.NetworkingConstants;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DisplayStoresFragment extends SpiceManagerFragment {
	NetworkThreader networkThread;
	StoreList storeList;
	LinearLayout storeTableScrollView;
	ViewGroup mContainer;
	View view;
	
	private ArrayList<String> mList = new ArrayList<String>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		networkThread = new NetworkThreader(NetworkingConstants.GET_STORE_URI, spiceManager, DisplayStoresFragment.this, null);
		//networkThread.getObjectFromServer("");

	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_edit_store,
				container, false);
		mContainer = container;
		return view;
		
	}
	
	private void insertNameInScrollView(String name, int index) {
		 LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		 View storeRow = inflater.inflate(R.layout.fragment_store_name,null);
		 TextView storeNameTextView = (TextView)storeRow.findViewById(R.id.store_name_text_view);
		 storeNameTextView.setText(name);
		 
		 Button storeNameButton = (Button) storeRow.findViewById(R.id.selectStoreButton);
		 storeNameButton.setOnClickListener(getStoreActivityListener);
		 
		 storeTableScrollView.addView(storeRow,index);
		 
	}
	
	public OnClickListener getStoreActivityListener = new OnClickListener(){
		
		@Override
		public void onClick(View v) {
			TableRow tableRow = (TableRow) v.getParent();
			TextView manTextView = (TextView) tableRow.findViewById(R.id.store_name_text_view);
			String selectedManName = manTextView.getText().toString();
			Store chosenStore = null;
			for(Store store : storeList){
				if(store.getName().equals(selectedManName)){
					chosenStore = store;
				}
			}
			

			Fragment fragment = new EditStoreFragment();

			Bundle bundle = new Bundle();
	
			bundle.putSerializable("chosenStore",chosenStore);
	
			fragment.setArguments(bundle);
	
			FragmentManager fm = getFragmentManager();
			//Fragment editFragment = new EditManufacturerFragment();
			FragmentTransaction transaction = fm.beginTransaction();
			transaction.replace(mContainer.getId(), fragment);
			transaction.remove(DisplayStoresFragment.this);
			transaction.commit();
			
			
			
			
		}
		
	};

	@Override
	public void errorSomeThang(String errorCode) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void doSomeThang(GetModelList result) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		storeList = mapper.convertValue(result, StoreList.class);
		for(Store store : storeList){
			mList.add(store.getName());
		}
		
		
		storeTableScrollView = (LinearLayout) view.findViewById(R.id.storeScrollView);
		if(storeTableScrollView == null){
			Log.e("null", "null");
		}
		if(mList != null){
			for(int i=0; i<mList.size();i++){
				insertNameInScrollView(mList.get(i),i);
			}
		}
		
	}

	@Override
	public void feedbackSomeThang(Object result) {
		// TODO Auto-generated method stub
		
	}

}
