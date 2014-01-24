package com.catalystitservices.priceitdroid.fragments.edit;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import modellists.GetModelList;
import modellists.ManufacturerList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.catalystitservices.priceitdroid.R;
import com.catalystitservices.priceitdroid.fragments.SpiceManagerFragment;
import com.catalystitservices.priceitdroid.model.Image;
import com.catalystitservices.priceitdroid.model.Manufacturer;
import com.catalystitservices.priceitdroid.model.Product;
import com.catalystitservices.priceitdroid.utils.Constants;
import com.catalystitservices.priceitdroid.utils.FileServices;
import com.catalystitservices.priceitdroid.utils.NetworkThreader;
import com.catalystitservices.priceitdroid.utils.NetworkingConstants;
import com.catalystitservices.priceitdroid.utils.Validate;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class EditProductFragment extends SpiceManagerFragment implements OnItemSelectedListener {
	
	private Uri fileUri;
	
	private Product addAProduct;
	private EditText productNameEditText;
	private EditText productManuEditText;
	private EditText productCodeEditText;
	private Bitmap productImage;
	private ImageView productImageView;
	private Spinner productManufactureSpinner;
	private EditText productTypeEditText;
	private EditText productDescriptionEditText;
	private Button mSubmitButton;
	private Button mcodeScanButton;
	private boolean[] mValidInput;
	private TextView productCodeViewText;
	private NetworkThreader network_get;
	private NetworkThreader network_create;
	private String pName;
	private IntentResult pCodeUPCorPLC;
	private String stringCodeUPCorPLC;
	private String mType;
	private String selected;
	private String mDescription;
	private Product mProduct;
	private ManufacturerList manuList;
	private ArrayList<String> manufactureList = new ArrayList();
	private NetworkThreader network_post;
	
	private BitmapFactory.Options bOptions;

	private static final String EXTRA_CODE = "EDIT_PRODUCT_ID";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = this.getArguments();
		
		mProduct =  (Product) bundle.getSerializable("chosenProduct");
	}



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View theView = inflater.inflate(R.layout.fragment_edit_product_view,
				container, false);
		Bundle args = getArguments();
		
		//productName
		productNameEditText = (EditText) theView.findViewById(R.id.productNameEditText);	
		Log.e("name", mProduct.getName());
		productNameEditText.setText(mProduct.getName());
		productNameEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				pName = s.toString();
				if (Validate.validString(pName)) {
					productNameEditText.setBackgroundResource(0);
				} else {
					productNameEditText
							.setBackgroundResource(R.drawable.edit_text_error_background);
				}

			}

		});
	
		
		// UPCcode
		productCodeEditText = (EditText) theView.findViewById(R.id.productCodeEditText);
		productCodeEditText.setText(mProduct.getCodeNum());
		productCodeEditText.setFocusable(false);
		productCodeEditText.setFocusableInTouchMode(false);
		productCodeEditText.setClickable(false);
		mcodeScanButton = (Button) theView.findViewById(R.id.code_scan_button);
		mcodeScanButton.setVisibility(View.GONE);
		
		// image
		productImageView = (ImageView) theView.findViewById(R.id.productImageView);
		productImageView.setClickable(false);
		productImageView.setFocusable(false);
		productImageView.setFocusableInTouchMode(false);
		
		// type
		productTypeEditText = (EditText) theView.findViewById(R.id.productTypeEditText);
		productTypeEditText.setText(mProduct.getType());
		productTypeEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable arg0) {
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mType = s.toString();
				if (Validate.validString(mType)) {
					productTypeEditText.setBackgroundResource(0);
				} else {
					productTypeEditText
							.setBackgroundResource(R.drawable.edit_text_error_background);
				}

			}

		});
		
		
		// Description
		productDescriptionEditText = (EditText) theView
				.findViewById(R.id.productDescriptionEditText);
		productDescriptionEditText.setText(mProduct.getDescription());	
		productDescriptionEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable arg0) {
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mDescription = s.toString();
				if (Validate.validString(mDescription)) {
					productDescriptionEditText.setBackgroundResource(0);
				} else {
					productDescriptionEditText
							.setBackgroundResource(R.drawable.edit_text_error_background);
				}

			}

		});
		
		// manufacture
		productManufactureSpinner = (Spinner) theView.findViewById(R.id.productManufactureSpinner);

		productManufactureSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int index, long arg3) {
				selected =  manufactureList.get(index);
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		//temp code for demo, backend is broken so spinner is not useful
		//productManufactureSpinner.setEnabled(false);
		productManufactureSpinner.setClickable(false);
		//end temp code
	
		mSubmitButton = (Button) theView.findViewById(R.id.submit_button);
		mSubmitButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String name    = productNameEditText.getText().toString();
				String upcCode = productCodeEditText.getText().toString();
				String type    = productTypeEditText.getText().toString();
				String codeType;
				String desc    = productDescriptionEditText.getText().toString();
				Manufacturer currentManu = null;
				for (Manufacturer manu : manuList){
					if(manu.getName().equals(mProduct.getManufacturer().getName())){
						currentManu = manu;
						break;
					}
					
				}
				if(productCodeEditText.getText().toString().length() >= 8){
					codeType = "upc";
				} else {
					codeType ="plu";
				}
				Product editProduct = new Product(name, upcCode, codeType, currentManu, type, desc);
				network_post = new NetworkThreader(NetworkingConstants.CREATE_PRODUCT_URI,spiceManager, 
						EditProductFragment.this, editProduct);
				
				network_post.putObjectToServer();
				Toast.makeText(getActivity(), "Contacting server...", 10).show();	
		
			}
	
		});

		return theView;

	}
	

	private int getcurrentManufactureIndext() {
		for (int i =0; i < manufactureList.size(); i++){
			if(manufactureList.get(i).equals(mProduct.getManufacturer().getName())){
				Log.e("in here", Integer.toString(i));
				return i;
			}
		}
		return 0;
	}

	@Override
	public void onStart(){
		super.onStart();
		network_get =  new NetworkThreader(NetworkingConstants.GET_MANUFACTURER_URI ,spiceManager, EditProductFragment.this, ManufacturerList.class);
		network_get.getObjectFromServer("listofmanufactures");
		
		
		
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		// If the user hits back without picking/taking a picture...dont crash
		if (resultCode == Activity.RESULT_CANCELED) {
            // User cancelled the image capture
        	Log.d("RESULT_CANCELED", "Request code: " + String.valueOf(requestCode) + ", "
    				+ "Result code: " + String.valueOf(resultCode));
        } else {
        	
        	//Reach up through the fragment to find the parent activity
    		//TODO test without app context
    		Context context = getActivity().getApplicationContext();
    		
    		//Find the absolute file path on the phone for the Intent data:
    		String filePath = "no file path";
    		
    		if (requestCode == Constants.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
    			filePath = fileUri.getPath();
    //			Log.d("Camera file path: ", filePath);
    			populateImageFields(requestCode, resultCode, filePath);
    			
    		} else if (requestCode == Constants.SELECT_IMAGE_FROM_GALLERY_REQUEST_CODE){
    			filePath = FileServices.getRealPathFromUri(context, data.getData());
    			populateImageFields(requestCode, resultCode, filePath);
    		}      	
        }	
	}
	


	private void populateImageFields(int requestCode, int resultCode,
			String filePath) {
		if (requestCode == Constants.SELECT_IMAGE_FROM_GALLERY_REQUEST_CODE
				|| requestCode == Constants.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE
					&& resultCode == Activity.RESULT_OK) {
			
			productImage = BitmapFactory.decodeFile(filePath, bOptions);
	        Bitmap thumb = ThumbnailUtils.extractThumbnail(productImage, 300, 300);
	        productImageView.setImageBitmap(thumb);
	
        }
	}


	@Override
	public void errorSomeThang(String errorCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doSomeThang(GetModelList result) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		manuList = mapper.convertValue(result, ManufacturerList.class);
		for (Manufacturer manu : manuList){
			manufactureList.add(manu.getName());
			
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, manufactureList );
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		productManufactureSpinner.setAdapter(adapter);
		
	    int currentManu = getcurrentManufactureIndext();
		productManufactureSpinner.setSelection(currentManu);
		
	}

	@Override
	public void feedbackSomeThang(Object result) {
		if(result != null){
			Toast.makeText(getActivity(), "WIN", 10).show();
		} else {
			Toast.makeText(getActivity(), "EPIC FAILZ", 10).show();
		}
		
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


}