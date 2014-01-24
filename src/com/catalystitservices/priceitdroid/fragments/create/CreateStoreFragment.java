package com.catalystitservices.priceitdroid.fragments.create;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import modellists.GetModelList;

import com.catalystitservices.priceitdroid.DisplayAddressListActivity;
import com.catalystitservices.priceitdroid.R;
import com.catalystitservices.priceitdroid.fragments.SpiceManagerFragment;
import com.catalystitservices.priceitdroid.fragments.display.DisplayAddressListFragment;
import com.catalystitservices.priceitdroid.model.Store;
import com.catalystitservices.priceitdroid.services.gps.LocationService;
import com.catalystitservices.priceitdroid.utils.Constants;
import com.catalystitservices.priceitdroid.utils.NetworkThreader;
import com.catalystitservices.priceitdroid.utils.NetworkingConstants;
import com.catalystitservices.priceitdroid.utils.Validate;
import com.catalystitservices.priceitdroid.model.*;

import android.app.Fragment;
import android.content.Intent;

import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateStoreFragment extends SpiceManagerFragment implements OnItemSelectedListener {

	private EditText mNameEditText;
	private EditText mStreetEditText;
	private EditText mCityEditText;
	private Spinner mStateSpinner;
	private EditText mZipEditText;
	private Button mSubmitButton;
	private LocationService mLocationService;
	private Geocoder mGeoCoder;
	private boolean[] mValidInput;

	private String mName;
	private String mStreet;
	private String mCity;
	private String mState;
	private String mZip;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().getActionBar().setTitle(R.string.create_store_headline);
		mLocationService = new LocationService(getActivity());
		mGeoCoder = new Geocoder(getActivity(), Locale.US);
		mValidInput = new boolean[4];
		for (int i = 0; i < mValidInput.length; i++) {
			mValidInput[i] = false;
		}
	}

	@Override
	public void onStart() {
		super.onStart();
		mLocationService.startLocationService();
		if (!mLocationService.canGetLocation()) {
			mLocationService.showSettingsAlert();
		}
	}

	@Override
	public void onStop() {
		super.onStop();
		mLocationService.stopLocationService();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_create_store, container, false);
		mNameEditText = (EditText) v.findViewById(R.id.name_editText);
		mStreetEditText = (EditText) v.findViewById(R.id.street_editText);
		mCityEditText = (EditText) v.findViewById(R.id.city_editText);
		mStateSpinner = (Spinner) v.findViewById(R.id.state_spinner);
		mZipEditText = (EditText) v.findViewById(R.id.zip_editText);
		mSubmitButton = (Button) v.findViewById(R.id.submit_button);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.states_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mStateSpinner.setAdapter(adapter);
		mStateSpinner.setOnItemSelectedListener(this);
		mNameEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				Log.d("TEST", "In On text Changed");
				mName = s.toString();
				if (Validate.validString(mName)) {
					mValidInput[0] = true;
					mNameEditText.setBackgroundResource(0);
				} else {
					mValidInput[0] = false;
					mNameEditText
							.setBackgroundResource(R.drawable.edit_text_error_background);
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		mStreetEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mStreet = s.toString();
				if (Validate.validString(mStreet)) {
					mValidInput[1] = true;
					mStreetEditText.setBackgroundResource(0);
				} else {
					mValidInput[1] = false;
					mStreetEditText
							.setBackgroundResource(R.drawable.edit_text_error_background);
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		mCityEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mCity = s.toString();
				if (Validate.validCityName(mCity)) {
					mValidInput[2] = true;
					mCityEditText.setBackgroundResource(0);
				} else {
					mValidInput[2] = false;
					mCityEditText
							.setBackgroundResource(R.drawable.edit_text_error_background);
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});


		mZipEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mZip = s.toString();
				if (Validate.zip(mZip)) {
					mValidInput[3] = true;
					mZipEditText.setBackgroundResource(0);
				} else {
					mValidInput[3] = false;
					mZipEditText
							.setBackgroundResource(R.drawable.edit_text_error_background);
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
	
		mSubmitButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {


				double fakeLat = 4.3;
				double fakeLong = 3.9;

				double latitude = 0;
				double longitude = 0;

				if (mLocationService.canGetLocation()) {
					Location location = mLocationService.getLocation();
					if (location != null) {
						longitude = location.getLongitude();
						latitude = location.getLatitude();
						System.out.println(longitude);
						System.out.println(latitude);
					}
				} else {
					mLocationService.showSettingsAlert();
				}


				if (Validate.inputValid(mValidInput)) {

					Store store = new Store(mName, fakeLat, fakeLong,mStreet, mCity, mState, mZip);


					NetworkThreader network_create = new NetworkThreader(
							NetworkingConstants.CREATE_STORE_URI,
							spiceManager, CreateStoreFragment.this,
							store);



					network_create.postObjectToServer("PostStore");



					


				} else {
					Toast.makeText(getActivity(), R.string.input_field_error,
							Toast.LENGTH_SHORT).show();
				}

			}
		});
		return v;
	}

	@Override
	public void onItemSelected(AdapterView<?> adapter, View arg1, int pos,
			long arg3) {
		mState = Constants.STATE_MAP.get(mStateSpinner.getItemAtPosition(pos).toString());
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		
		
	}


	@Override
	public void errorSomeThang(String errorCode) {
		//this is lazy code TODO parse out error Code
		Toast.makeText(getActivity(), "Store already exists", 10).show();
	}

	@Override
	public void doSomeThang(GetModelList result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void feedbackSomeThang(Object result) {
		// TODO Auto-generated method stub
		
	}

}
