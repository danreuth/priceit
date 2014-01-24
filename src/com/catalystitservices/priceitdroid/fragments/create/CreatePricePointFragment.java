package com.catalystitservices.priceitdroid.fragments.create;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import modellists.GetModelList;

import modellists.ProductList;
import modellists.StoreList;

import com.catalystitservices.priceitdroid.R;
import com.catalystitservices.priceitdroid.fragments.SpiceManagerFragment;
import com.catalystitservices.priceitdroid.fragments.display.DatePickerFragment;

import com.catalystitservices.priceitdroid.model.Manufacturer;
import com.catalystitservices.priceitdroid.model.PricePoint;
import com.catalystitservices.priceitdroid.model.Product;
import com.catalystitservices.priceitdroid.model.Store;

import com.catalystitservices.priceitdroid.model.User;
import com.catalystitservices.priceitdroid.utils.Constants;
import com.catalystitservices.priceitdroid.utils.NetworkThreader;
import com.catalystitservices.priceitdroid.utils.NetworkingConstants;
import com.catalystitservices.priceitdroid.utils.Validate;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class CreatePricePointFragment extends SpiceManagerFragment implements OnItemSelectedListener  {

	private RadioGroup radioButtonGroup;
	private RadioButton onSale_radioButton;
	private RadioButton regularPrice_radioButton;
	private TextView endDate_textView;
	private EditText endDate_editText;
	private EditText date_editText;
	private EditText price_editText;
	private EditText storeName_editText;
	private EditText productCode_editText;
	private Button scan_button;
	private Button submit_button;
	private NetworkThreader network_post;
	private NetworkThreader network_get;
	
	private Date date;
	private SimpleDateFormat dateFormat;
	private String pattern;
	private String wellFormedPrice;
	private Map<EditText, Boolean> validationMap;
	private StoreList storeList;
	private ProductList productList;
	private boolean gotStore;
	private boolean gotPricePoint;

	public static final String DATE_PICKER_ID =
			 "date_picker_id";

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.fragment_create_price_point,
				container, false);

		gotStore = false;
		network_get = new NetworkThreader(NetworkingConstants.GET_PRODUCT_URI, spiceManager, CreatePricePointFragment.this, null);
		network_get.getObjectFromServer("product");
		
		/* Initialization */

		getActivity().getActionBar().setTitle(R.string.create_price_point_headline);
		
		radioButtonGroup = (RadioGroup) view.findViewById(R.id.price_point_radioButtonGroup);
		onSale_radioButton = (RadioButton) view.findViewById(R.id.price_point_radioButton_sale);
		regularPrice_radioButton = (RadioButton) view.findViewById(R.id.price_point_radioButton_regular);
		endDate_textView = (TextView) view.findViewById(R.id.price_point_endDate_textView);
		endDate_editText = (EditText) view.findViewById(R.id.price_point_editText_endDate);
		date_editText = (EditText) view.findViewById(R.id.price_point_editText_date);
		price_editText = (EditText) view.findViewById(R.id.price_point_editText_price);
		storeName_editText = (EditText) view.findViewById(R.id.price_point_editText_store);
		productCode_editText = (EditText) view.findViewById(R.id.productCodeEditText);
		scan_button = (Button) view.findViewById(R.id.code_scan_button);
		submit_button = (Button) view.findViewById(R.id.submit_button);
		
		regularPrice_radioButton.setChecked(true);
		
		//initialize validation map with false to prevent a null submit
		validationMap = new HashMap<EditText, Boolean>();
		validationMap.put(date_editText, false);
		validationMap.put(price_editText, false);
		validationMap.put(productCode_editText, false);
		validationMap.put(storeName_editText, false);
		
		endDate_editText.setVisibility(View.INVISIBLE);
		endDate_textView.setVisibility(View.INVISIBLE);
		

		//TODO implement scanning in a code
		scan_button.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "UNIMPLEMENTED", Toast.LENGTH_SHORT).show();
			}
		});
		
		radioButtonGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId == R.id.price_point_radioButton_regular){
					endDate_editText.setVisibility(View.INVISIBLE);
					endDate_textView.setVisibility(View.INVISIBLE);
					//an unused end date field is valid
					validationMap.put(endDate_editText, true);
				} else if (checkedId == R.id.price_point_radioButton_sale){
					endDate_editText.setVisibility(View.VISIBLE);
					endDate_textView.setVisibility(View.VISIBLE);
					validationMap.put(endDate_editText, false);
					if(endDate_editText.getText().length() > 0){
						validationMap.put(endDate_editText, true);
					}
				}

			}
		});
		
		/* Date pickers spin off new fragments when clicked */
		
		date_editText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				date = new Date();
				
				FragmentManager fm = getActivity().getFragmentManager();
				DatePickerFragment dialog = DatePickerFragment.newInstance(date);
				dialog.setTargetFragment(CreatePricePointFragment.this, Constants.SELECT_DATE_REQUEST_CODE);
				dialog.show(fm, DATE_PICKER_ID);
				
			}
		});
		
		endDate_editText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				date = new Date();
				
				FragmentManager fm = getActivity().getFragmentManager();
				DatePickerFragment dialog = DatePickerFragment.newInstance(date);
				dialog.setTargetFragment(CreatePricePointFragment.this, Constants.SELECT_END_DATE_REQUEST_CODE);
				dialog.show(fm, DATE_PICKER_ID);
				
			}
		});
		
		submit_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				/**
				 * The api was in flux when I wrote this. Pulling a product and store from
				 * the onCreate GET is untested. Oauth and users were also unimplemented, so there's
				 * an untested dummy user here too. 
				 */
				
				if(formValidation(validationMap) == true){
					User user = new User("worc", "curt", "mcallister", "password1", "email@email.com");
		
					Product product = productList.get(0);
					Store store = storeList.get(0);
					
					String status;
					String price;
					
					if (regularPrice_radioButton.isChecked() == true) {
						status = "reg";
					} else {
						status = "sale";
					}
					
					
					price = price_editText.getText().toString().replaceAll("[$,]", "");
					
					PricePoint pricePoint = new PricePoint(user, product, 
							date_editText.getText().toString(), store, 
							new BigDecimal(price), 
							status, 
							endDate_editText.getText().toString());
					
					Toast.makeText(getActivity(), android.R.string.ok,Toast.LENGTH_SHORT).show();
			
					
					network_post = new NetworkThreader(NetworkingConstants.CREATE_PRICE_POINT_URI, spiceManager, CreatePricePointFragment.this, pricePoint);
					network_post.postObjectToServer("price_point_post");
	
				} else {
					Toast.makeText(getActivity(), R.string.input_field_error,Toast.LENGTH_SHORT).show();
				}
	
			}
			
		});
		
		/* ON TEXT CHANGE LISTENERS */
		
		productCode_editText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
				if (Validate.UPCorPLC(s.toString())) {
				
					productCode_editText.setBackgroundResource(0);
					validationMap.put(productCode_editText, true);
				} else {
					
					productCode_editText.setBackgroundResource(R.drawable.edit_text_error_background);
					validationMap.put(productCode_editText, false);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		storeName_editText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (Validate.validName(s.toString())) {
				
					storeName_editText.setBackgroundResource(0);
					validationMap.put(storeName_editText, true);
				} else {
					
					storeName_editText.setBackgroundResource(R.drawable.edit_text_error_background);
					validationMap.put(storeName_editText, false);
				}
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		price_editText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (Validate.validPrice(s.toString())) {
			
					price_editText.setBackgroundResource(0);
					validationMap.put(price_editText, true);
				} else {
		
					price_editText.setBackgroundResource(R.drawable.edit_text_error_background);
					validationMap.put(price_editText, false);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
				
				
			    if(!s.toString().equals(wellFormedPrice)){
			    	
			       
			        String cleanString = s.toString().replaceAll("[$,.]", "");

			        BigDecimal parsed = new BigDecimal(cleanString);
			        String formatted = NumberFormat.getCurrencyInstance().format((parsed.doubleValue()/100));

			       
			        wellFormedPrice = formatted;
			        
			        price_editText.setText(formatted);
			        
			        
			        price_editText.setSelection(formatted.length());
			     }
				
			}
		});

		return view;
	}
	
	/* Activity Result */
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		
		pattern = "dd MMM yyyy";
		dateFormat = new SimpleDateFormat(pattern);
		
		if (resultCode != Activity.RESULT_OK) {
			return;
		}
		if (requestCode == Constants.SELECT_DATE_REQUEST_CODE) {
			Date date = (Date) data.getSerializableExtra(DatePickerFragment.DATE_PICKER_STORED_VALUE);
			date_editText.setText(dateFormat.format(date));
			date_editText.setBackgroundResource(0);
			validationMap.put(date_editText, true);
		} else if (requestCode == Constants.SELECT_END_DATE_REQUEST_CODE) {
			Date endDate = (Date) data.getSerializableExtra(DatePickerFragment.DATE_PICKER_STORED_VALUE);
			endDate_editText.setText(dateFormat.format(endDate));
			endDate_editText.setBackgroundResource(0);
			validationMap.put(endDate_editText, true);
		}
	}
	
	private boolean formValidation(Map<EditText, Boolean> map){
		boolean result = true;
		for (Entry<EditText, Boolean> entry : map.entrySet())
		{
		    if(entry.getValue() == false){
		    	entry.getKey().setBackgroundResource(R.drawable.edit_text_error_background);
		    	result = false;
		    }
		}
		return result;
	}
	
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void errorSomeThang(String errorCode) {
		Log.d("Server", "ERROR");
		
	}

	@Override
	public void doSomeThang(GetModelList result) {
		
		/** The GET network threader will pass in the linkedhashmap "result."
		 *  First we check if the database returned us a populated collection.
		 *  If the collection is populated we pass the result along to another
		 *  method for parsing.
		 */
		
		try {
			result.get(0);
			resultHandler(result);
		} catch (Exception e) {
			Toast.makeText(getActivity(), "Error, nothing retrieved from the database.",Toast.LENGTH_SHORT).show();
		}
		
		/** 
		 * After we've handled the first GET, we will handle getting stores from the
		 * database. There's a boolean here to prevent infinite recursion. Though there's
		 * likely a better way, this was just quick and dirty.
		 */
		
		if(gotStore == false){
			gotStore = true;
			network_get = new NetworkThreader(NetworkingConstants.GET_STORE_URI, spiceManager, CreatePricePointFragment.this, null);
			network_get.getObjectFromServer("store");
		}
		
		if(gotPricePoint == false){
			gotPricePoint = true;
			network_get = new NetworkThreader(NetworkingConstants.GET_PRICE_POINT_URI, spiceManager, CreatePricePointFragment.this, null);
			network_get.getObjectFromServer("price_point"); 
		}
	}

	private void resultHandler(GetModelList result) {
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		/** 
		 *  This if-else reaches in to the linkedhashmap, "result," and
		 *  deserializes the collection based on class type declared under the
		 *  key "class".
		 */
		LinkedHashMap test = (LinkedHashMap<String, String>) result.get(0);
		if(test.get("class").equals("priceitapi.Store")){
			storeList = mapper.convertValue(result, StoreList.class);
		} else if (test.get("class").equals("priceitapi.Product")){
			productList = mapper.convertValue(result, ProductList.class);
		} else {
			Toast.makeText(getActivity(), "Error, invalid objects retrieved from the database.",Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void feedbackSomeThang(Object result) {
		Log.d("Server", "SUCCESS");
		
	}


}
