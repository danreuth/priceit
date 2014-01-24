package com.catalystitservices.priceitdroid.fragments.create;

import java.io.IOException;
import java.util.ArrayList;


import modellists.GetModelList;
import modellists.ManufacturerList;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.catalystitservices.priceitdroid.CreateManufacturerActivity;
import com.catalystitservices.priceitdroid.R;
import com.catalystitservices.priceitdroid.fragments.SpiceManagerFragment;
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

public class CreateProductFragment extends SpiceManagerFragment {

	private Uri fileUri;

	private Product addAProduct;
	private EditText productNameEditText;
	private EditText productCodeEditText;
	private EditText productManufactureNameEditText;
	private Bitmap productImage;
	private ImageView productImageView;
	private EditText productManufactureEditText;
	private EditText productImageEditText;
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
	private String mManufacturer;
	private String mImage;
	private String mType;
	private String selected;
	private String mDescription;
	private ProgressDialog mGetRequest;
	private ArrayList<Manufacturer> manufacts = new ArrayList<Manufacturer>();

	private BitmapFactory.Options bOptions;

	private static final String EXTRA_CODE = "com.catalystit.code";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setRetainInstance(true);


		getActivity().getActionBar().setTitle(R.string.product_create_headline);

		// TODO implement network get after demo

		// allow the bitmap decoder to clear memory
		bOptions = new BitmapFactory.Options();
		bOptions.inPurgeable = true;

		// start validator in a false state
		mValidInput = new boolean[4];
		for (int i = 0; i < mValidInput.length; i++) {
			mValidInput[i] = false;
		}
	}

	public static CreateProductFragment newInstance(IntentResult code) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_CODE, code);

		CreateProductFragment fragment = new CreateProductFragment();
		fragment.setArguments(args);

		return fragment;
	}

	/***********************************************
	 * public void onActivityResult(int requestCode, int resultCode, Intent
	 * intent) {
	 * 
	 * super.onActivityResult(requestCode, resultCode, intent); IntentResult
	 * scanningResult = IntentIntegrator.parseActivityResult(requestCode,
	 * resultCode, intent); System.out.println("iam out side"); if
	 * (scanningResult != null) { String scanContent =
	 * scanningResult.getContents(); Log.d("hello", "1,2"); Log.d("hello",
	 * scanContent.toString()); //we have a result } else{
	 * Toast.makeText(getActivity(), R.string.input_field_error,
	 * Toast.LENGTH_SHORT).show(); }
	 * 
	 * 
	 * }
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View theView = inflater.inflate(R.layout.fragment_create_product,
				container, false);

		Bundle args = getArguments();
	


		productNameEditText = (EditText) theView
				.findViewById(R.id.productNameEditText);
		productCodeEditText = (EditText) theView
				.findViewById(R.id.productCodeEditText);
		productManufactureEditText = (EditText) theView
				.findViewById(R.id.productManufactureEdit);
		productImageView = (ImageView) theView
				.findViewById(R.id.productImageView);
		productImageView.setClickable(true);
		productTypeEditText = (EditText) theView
				.findViewById(R.id.productTypeEditText);
		productDescriptionEditText = (EditText) theView
				.findViewById(R.id.productDescriptionEditText);
		mSubmitButton = (Button) theView.findViewById(R.id.submit_button);

		/****************** working with scan ********************/
		mcodeScanButton = (Button) theView.findViewById(R.id.code_scan_button);
		productCodeViewText = (TextView) theView
				.findViewById(R.id.productCodeEditText);
	

		mcodeScanButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (arg0.getId() == R.id.code_scan_button) {
					// Call Controller
					IntentIntegrator scanIntegrator = new IntentIntegrator(
							getActivity());
					scanIntegrator.initiateScan();
					

				}
			}

		});

		/****************** working with scan ********************/

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
					mValidInput[0] = true;
					productNameEditText.setBackgroundResource(0);
					mcodeScanButton.setClickable(false);
				} else {
					mValidInput[0] = false;
					productNameEditText
							.setBackgroundResource(R.drawable.edit_text_error_background);
				}

			}

		});

		productCodeEditText.addTextChangedListener(new TextWatcher() {
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
				// TODO test text change, this WAS pCodeUPCorPLC
				stringCodeUPCorPLC = s.toString();
				if (Validate.UPCorPLC(stringCodeUPCorPLC)) {
					mValidInput[1] = true;
					productCodeEditText.setBackgroundResource(0);
				} 
				else if(stringCodeUPCorPLC.length() == 8){
					mValidInput[1] = true;
					productCodeEditText.setBackgroundResource(0);
				}
				else {
					mValidInput[1] = false;
					productCodeEditText
							.setBackgroundResource(R.drawable.edit_text_error_background);
				}

			}

		});

		productImageView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						getActivity());
				alertDialog.setTitle(R.string.image_dialog_title);
				alertDialog.setMessage(R.string.image_dialog_promt);

				// On pressing Camera button
				alertDialog.setPositiveButton(
						R.string.image_select_button_camera,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								Intent intent = new Intent(
										MediaStore.ACTION_IMAGE_CAPTURE);
								fileUri = FileServices.getOutputMediaFileUri();
								intent.putExtra(MediaStore.EXTRA_OUTPUT,
										fileUri);
								startActivityForResult(
										intent,
										Constants.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
							}
						});

				// on pressing Gallery button
				alertDialog.setNegativeButton(
						R.string.image_select_button_gallery,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// generate a new intent based on the user
								// asking for the gallery
								Intent photoPickerIntent = new Intent();

								// we're looking to pick images of any type
								photoPickerIntent.setType("image/*");
								photoPickerIntent.setAction(Intent.ACTION_PICK);

								// and then we're going to start an activity
								// based on this intent and this action
								startActivityForResult(
										photoPickerIntent,
										Constants.SELECT_IMAGE_FROM_GALLERY_REQUEST_CODE);

							}
						});

				// Showing Alert Message
				alertDialog.show();

			}
		});

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
					mValidInput[2] = true;
					productTypeEditText.setBackgroundResource(0);
				} else {
					mValidInput[2] = false;
					productTypeEditText
							.setBackgroundResource(R.drawable.edit_text_error_background);
				}

			}

		});

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
					mValidInput[3] = true;
					productDescriptionEditText.setBackgroundResource(0);
				} else {
					mValidInput[3] = false;
					productDescriptionEditText
							.setBackgroundResource(R.drawable.edit_text_error_background);
				}

			}

		});

		mSubmitButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				if (Validate.inputValid(mValidInput)) {

					Manufacturer manufacturer = null;
					String manuName = productManufactureEditText.getText()
							.toString();
					for (int i = 0; i < manufacts.size(); i++) {
						if (manuName.equals(manufacts.get(i).getName())) {
							manufacturer = manufacts.get(i);
							break;
						}
					}
					

					String codeType = "";
					if(productCodeEditText.getText().toString().length() >= 8){
						codeType = "upc";
					} else {
						codeType ="plu";
					}		

					if(productCodeEditText.getText().toString().length() == 12){
						codeType = "upc";
					} else {
						codeType ="plu";
					}
					if (manufacturer != null) {
						addAProduct = new Product(productNameEditText.getText()
								.toString().trim(), productCodeEditText.getText()
								.toString().trim(), codeType.trim(), manufacturer,
								productTypeEditText.getText().toString().trim(),
								productDescriptionEditText.getText().toString().trim());

					
						network_create = new NetworkThreader(
								NetworkingConstants.CREATE_PRODUCT_URI,
								spiceManager, CreateProductFragment.this,
								addAProduct);
						network_create.postObjectToServer("PostProduct");
						

					} else {
						Toast.makeText(getActivity(),
								"Manufacturer does not exist", 10).show();
						Intent makeMan = new Intent(getActivity(),
								CreateManufacturerActivity.class);
						makeMan.putExtra(Constants.MAN_NAME, manuName);
						startActivityForResult(makeMan,
								Constants.MAKE_MAN_REQUEST_CODE);
					}

				} else {
					Toast.makeText(getActivity(), R.string.input_field_error,
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		if (args != null) {

			pCodeUPCorPLC = (IntentResult) getArguments().getSerializable(
					EXTRA_CODE);

			productCodeEditText.setText(pCodeUPCorPLC.getContents());
			productCodeEditText.setFocusable(false);
			productCodeEditText.setFocusableInTouchMode(false);
			productCodeEditText.setClickable(false);

		}
		return theView;

	}
	
	public void onStart(){
		super.onStart();
		network_get = new NetworkThreader(
				NetworkingConstants.GET_MANUFACTURER_URI, spiceManager,
				CreateProductFragment.this, ManufacturerList.class);
		network_get.getObjectFromServer("GetManufacturerList");
		mGetRequest = ProgressDialog.show(getActivity(),"",EXTRA_CODE);
		mGetRequest.setMessage("Loading..");
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		// If the user hits back without picking/taking a picture...dont crash
		if (resultCode == Activity.RESULT_CANCELED) {
			// User cancelled the image capture
			Log.d("RESULT_CANCELED",
					"Request code: " + String.valueOf(requestCode) + ", "
							+ "Result code: " + String.valueOf(resultCode));
		} else {

			
			Context context = getActivity().getApplicationContext();

		
			String filePath = "no file path";

			if (requestCode == Constants.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
				filePath = fileUri.getPath();
				Log.d("Camera file path: ", filePath);
				populateImageFields(requestCode, resultCode, filePath);

			} else if (requestCode == Constants.SELECT_IMAGE_FROM_GALLERY_REQUEST_CODE) {
				filePath = FileServices.getRealPathFromUri(context,
						data.getData());
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
			Bitmap thumb = ThumbnailUtils.extractThumbnail(productImage, 300,
					300);
			productImageView.setImageBitmap(thumb);

		}
	}


	@Override
	public void errorSomeThang(String errorCode) {
		//bad code TODO parse error code correctly
		Toast.makeText(getActivity(), "Duplicate Entry", 10).show();

	}




	public void doSomeThang(GetModelList result) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		manufacts = mapper.convertValue(result, ManufacturerList.class);
		mGetRequest.dismiss();
	}




	@Override
	public void feedbackSomeThang(Object result) {


	}
}