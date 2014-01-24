package com.catalystitservices.priceitdroid.fragments.edit;


import java.io.Serializable;
import java.util.Map;

import modellists.GetModelList;
import android.content.res.Resources;
import android.os.Bundle;
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

import com.catalystitservices.priceitdroid.R;
import com.catalystitservices.priceitdroid.fragments.SpiceManagerFragment;
import com.catalystitservices.priceitdroid.model.Manufacturer;
import com.catalystitservices.priceitdroid.utils.Constants;
import com.catalystitservices.priceitdroid.utils.NetworkThreader;
import com.catalystitservices.priceitdroid.utils.NetworkingConstants;
import com.catalystitservices.priceitdroid.utils.Validate;

public class EditManufacturerFragment extends SpiceManagerFragment implements OnItemSelectedListener {

	private EditText manufacturerName_editText;
	private EditText street_editText;
	private EditText city_editText;
	private Spinner mStateSpinner;
	private EditText zipcode_editText;
	private Button submit_button;
	private NetworkThreader network_post;
	private String mState;
	private static final String EXTRA_MANID = "EDIT_MANUFACTURE_ID";
	private Manufacturer mManufacturer;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = this.getArguments();
		
		mManufacturer =  (Manufacturer) bundle.getSerializable("chosenManufacturer");

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_create_manufacturer,
				container, false);
		Bundle args = getArguments();
		mStateSpinner = (Spinner) view.findViewById(R.id.manuf_state_spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.states_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		mStateSpinner.setAdapter(adapter);
		
		int currentStateIndex = getCurrentStateIndex();
		mStateSpinner.setSelection(currentStateIndex);
		

		
		mStateSpinner.setOnItemSelectedListener(this);
		
	
		
		

		manufacturerName_editText = (EditText) view
				.findViewById(R.id.manufacturerName_editText);
		manufacturerName_editText.setFocusable(false);
		manufacturerName_editText.setFocusableInTouchMode(false);
		manufacturerName_editText.setClickable(false);
		manufacturerName_editText.setText(mManufacturer.getName());

		street_editText = (EditText) view.findViewById(R.id.street_editText);
		street_editText.setText(mManufacturer.getStreet());
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
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

		});
		city_editText = (EditText) view.findViewById(R.id.city_editText);
		city_editText.setText(mManufacturer.getCity());
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
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

		});

		zipcode_editText = (EditText) view.findViewById(R.id.zip_editText);
		zipcode_editText.setText(mManufacturer.getZipcode());
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
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

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

				Manufacturer man = new Manufacturer(name,street,city, state, zipCode);

				if (Validate.validString(name)
						&& Validate.validString(street)
						&& Validate.validCityName(city) && Validate.zip(zipCode)) {
					
					network_post = new NetworkThreader(NetworkingConstants.CREATE_MANUFACTURER_URI,spiceManager, EditManufacturerFragment.this, man);


					network_post.putObjectToServer();


					
					Toast.makeText(getActivity(), "Contacting server...", 10).show();
				} else {
					Toast.makeText(getActivity(), R.string.input_field_error, 10).show();
				}

			}
		});
		
		if(args != null){
			//int manId = (Integer) args.getSerializable(EXTRA_MANID);
			//TODO: Network call to get manufacture from id.
		}

		return view;
	}

	
	private int getCurrentStateIndex() {

		int stateIndex = 0;
		
		 for(Map.Entry<String,String> entry : Constants.STATE_MAP.entrySet()){
			 String value = entry.getValue();
			 if(value.equals(mManufacturer.getState())){
				 break;
			 }
			 stateIndex++;
		 }

		return stateIndex;
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
		String errorNum = errorCode.substring(2, 6);
		if(errorNum.equals(NetworkingConstants.DUPLICATE_NAME_ERROR)){
			Toast.makeText( getActivity(), "Duplicate Name Error", Toast.LENGTH_SHORT ).show();
		}
		
	}

	@Override
	public void doSomeThang(GetModelList result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void feedbackSomeThang(Object object) {
		// TODO Auto-generated method stub
		
	}


}
