package com.catalystitservices.priceitdroid.fragments.create;



import modellists.GetModelList;



import android.content.Intent;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

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

import com.catalystitservices.priceitdroid.R;
import com.catalystitservices.priceitdroid.fragments.SpiceManagerFragment;
import com.catalystitservices.priceitdroid.model.Manufacturer;
import com.catalystitservices.priceitdroid.utils.Constants;
import com.catalystitservices.priceitdroid.utils.NetworkThreader;
import com.catalystitservices.priceitdroid.utils.NetworkingConstants;
import com.catalystitservices.priceitdroid.utils.Validate;

public class CreateManufacturerFragment extends SpiceManagerFragment implements
		OnItemSelectedListener {

	private EditText manufacturerName_editText;
	private EditText street_editText;
	private EditText city_editText;
	private String manufacturerName = null;
	private Spinner mStateSpinner;
	private EditText zipcode_editText;
	private Button submit_button;
	private NetworkThreader network_post;
	private String mState;
	private static final String EXTRA_MANID = "EDIT_MANUFACTURE_ID";

	public static CreateManufacturerFragment newInstance(String manId) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_MANID, manId);

		CreateManufacturerFragment fragment = new CreateManufacturerFragment();
		fragment.setArguments(args);

		return fragment;
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().getActionBar().setTitle(
				R.string.manufacturer_create_headline);
		Intent intent = getActivity().getIntent();

		try{
			if(intent != null){
				manufacturerName = (String) intent.getStringExtra(Constants.MAN_NAME);
			}
		} catch(Exception e){
			
		}	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_create_manufacturer,
				container, false);
		Bundle args = getArguments();
		mStateSpinner = (Spinner) view.findViewById(R.id.manuf_state_spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				getActivity(), R.array.states_array,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mStateSpinner.setAdapter(adapter);
		mStateSpinner.setOnItemSelectedListener(this);

		manufacturerName_editText = (EditText) view
				.findViewById(R.id.manufacturerName_editText);

		try {
			if (!(manufacturerName.equals(null))) {
				manufacturerName_editText.setText(manufacturerName);
			}
		} catch(Exception e){
			
		}

		manufacturerName_editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				String name = s.toString();
				if (Validate.validString(name)) {
					manufacturerName_editText.setBackgroundResource(0);
				} else {
					manufacturerName_editText
							.setBackgroundResource(R.drawable.edit_text_error_background);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				

			}

		});
		street_editText = (EditText) view.findViewById(R.id.street_editText);
		street_editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				String street = s.toString();
				if (Validate.validString(street)) {
					street_editText.setBackgroundResource(0);
				} else {
					street_editText
							.setBackgroundResource(R.drawable.edit_text_error_background);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			

			}

		});
		city_editText = (EditText) view.findViewById(R.id.city_editText);
		city_editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				String city = s.toString();
				if (Validate.validCityName(city)) {
					city_editText.setBackgroundResource(0);
				} else {
					city_editText
							.setBackgroundResource(R.drawable.edit_text_error_background);
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				

			}

		});

		zipcode_editText = (EditText) view.findViewById(R.id.zip_editText);
		zipcode_editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				String zip = s.toString();
				if (Validate.zip(zip)) {
					zipcode_editText.setBackgroundResource(0);
				} else {
					zipcode_editText
							.setBackgroundResource(R.drawable.edit_text_error_background);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				

			}

		});
		submit_button = (Button) view.findViewById(R.id.submit_button);
		submit_button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String name = manufacturerName_editText.getText().toString();
				String street = street_editText.getText().toString();
				String city = city_editText.getText().toString();
				String state = mState;
				String zipCode = zipcode_editText.getText().toString();

		
				Manufacturer man = new Manufacturer(name, street, city, state, zipCode);


				if (Validate.validString(name)
						&& Validate.validString(street)
						&& Validate.validCityName(city) && Validate.zip(zipCode)) {
					
					network_post = new NetworkThreader(NetworkingConstants.CREATE_MANUFACTURER_URI,spiceManager, CreateManufacturerFragment.this, man);
					network_post.postObjectToServer("createmanufacterpost");
					
					Toast.makeText(getActivity(), "Contacting server...", 10).show();

				} else {
					Toast.makeText(getActivity(), R.string.input_field_error,
							10).show();
				}

			}
		});

		if (args != null) {
			int manId = (Integer) args.getSerializable(EXTRA_MANID);
			// TODO: Network call to get manufacture from id.
		}

		return view;
	}


	@Override
	public void doSomeThang(Object result) {
		getActivity().onBackPressed();
		
	}


	@Override
	public void onItemSelected(AdapterView<?> adapter, View arg1, int pos,
			long arg3) {
		mState = Constants.STATE_MAP.get(mStateSpinner.getItemAtPosition(pos)
				.toString());

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		
	}

	@Override
	public void errorSomeThang(String errorCode) {
		String errorNum = errorCode.substring(2, 6);
		if(errorNum.equals(NetworkingConstants.DUPLICATE_NAME_ERROR)){
			Toast.makeText( getActivity(), "Duplicate Name Error", Toast.LENGTH_SHORT ).show();
		}
	}
	public void doSomeThang(GetModelList result) {
		// TODO Auto-generated method stub

		
	}



	@Override

	public void feedbackSomeThang(Object object) {

	}




}
