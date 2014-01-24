package com.catalystitservices.priceitdroid.fragments.display;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import modellists.GetModelList;
import modellists.ManufacturerList;

import com.catalystitservices.priceitdroid.DeleteManufacturerActivity;
import com.catalystitservices.priceitdroid.R;
import com.catalystitservices.priceitdroid.fragments.SpiceManagerFragment;
import com.catalystitservices.priceitdroid.fragments.create.CreateUserFragment;
import com.catalystitservices.priceitdroid.fragments.edit.EditManufacturerFragment;
import com.catalystitservices.priceitdroid.model.Manufacturer;
import com.catalystitservices.priceitdroid.utils.Constants;
import com.catalystitservices.priceitdroid.utils.NetworkThreader;
import com.catalystitservices.priceitdroid.utils.NetworkingConstants;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DisplayManufacturersFragment extends SpiceManagerFragment {

	NetworkThreader networkThread;
	LinearLayout manTableScrollView;
	ManufacturerList manList;
	View view;
	ViewGroup mContainer;
	String action;

	private ArrayList<String> mList = new ArrayList<String>();

	public static DisplayManufacturersFragment newInstance(String token) {

		Bundle args = new Bundle();
		args.putString("action", token);
		DisplayManufacturersFragment fragment = new DisplayManufacturersFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		action = (String) getArguments().getString("action");
		networkThread = new NetworkThreader(
				NetworkingConstants.GET_MANUFACTURER_URI, spiceManager,
				DisplayManufacturersFragment.this, null);
		networkThread.getObjectFromServer("manufacturer_get");

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_select_manufacturer,
				container, false);
		mContainer = container;
		
		return view;

	}

	private void insertNameInScrollView(String name, int index) {
		LayoutInflater inflater = (LayoutInflater) getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View manRow = inflater.inflate(R.layout.fragement_manufacturer_name,
				null);
		TextView manNameTextView = (TextView) manRow
				.findViewById(R.id.manufacturer_name_text_view);
		manNameTextView.setText(name);

		Button manNameButton = (Button) manRow
				.findViewById(R.id.selectManufacturerButton);
		manNameButton.setOnClickListener(getManActivityListener);
		
		manNameButton.setText(action);

		manTableScrollView.addView(manRow, index);
	}

	public OnClickListener getManActivityListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			TableRow tableRow = (TableRow) v.getParent();
			TextView manTextView = (TextView) tableRow
					.findViewById(R.id.manufacturer_name_text_view);
			String selectedManName = manTextView.getText().toString();
			Manufacturer chosenMan = null;
			for (Manufacturer man : manList) {
				if (man.getName().equals(selectedManName)) {
					chosenMan = man;
				}
			}
			
			if(action.equals(Constants.EDIT)){
				Fragment fragment = new EditManufacturerFragment();
	
				Bundle bundle = new Bundle();
	
				bundle.putSerializable("chosenManufacturer", chosenMan);
	
				fragment.setArguments(bundle);
	
				FragmentManager fm = getFragmentManager();
				// Fragment editFragment = new EditManufacturerFragment();
				FragmentTransaction transaction = fm.beginTransaction();
				transaction.replace(mContainer.getId(), fragment);
				transaction.remove(DisplayManufacturersFragment.this);
				transaction.commit();
			}
			else if(action.equals(Constants.DELETE)){
				String manId = chosenMan.getId();
				networkThread = new NetworkThreader(
						NetworkingConstants.GET_MANUFACTURER_URI + "/" + manId, spiceManager,
						DisplayManufacturersFragment.this, null);
				networkThread.deleteObjectFromServer();
			}
			
			
		}

	};

	@Override
	public void doSomeThang(GetModelList result) {
		Log.e("dosomethang", "dosomethang");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		if (mList != null){
			mList.clear();
		}
		manList = mapper.convertValue(result, ManufacturerList.class);

		for (Manufacturer man : manList) {
			mList.add(man.getName());
		}
	
		if(manTableScrollView != null){
			manTableScrollView.removeAllViews();
		}
		manTableScrollView = (LinearLayout) view
				.findViewById(R.id.manufacturerScrollView);
		
		if (manTableScrollView == null) {
			Log.e("null", "null");
		}
		if (mList != null) {
			for (int i = 0; i < mList.size(); i++) {
				insertNameInScrollView(mList.get(i), i);
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

	

	
	
	@Override
	public void refreshPage(){
		spiceManager.removeAllDataFromCache();
		networkThread = new NetworkThreader(
				NetworkingConstants.GET_MANUFACTURER_URI, spiceManager,
				DisplayManufacturersFragment.this, null);
		networkThread.getObjectFromServer("manufacturer_get");
	}
}
