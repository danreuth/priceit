package com.catalystitservices.priceitdroid.fragments.create;

import java.util.UUID;

import modellists.GetModelList;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.catalystitservices.priceitdroid.CreateUserActivity;
import com.catalystitservices.priceitdroid.LoginActivity;
import com.catalystitservices.priceitdroid.R;
import com.catalystitservices.priceitdroid.fragments.SpiceManagerFragment;
import com.catalystitservices.priceitdroid.model.NewUser;
import com.catalystitservices.priceitdroid.model.User;
import com.catalystitservices.priceitdroid.utils.Constants;
import com.catalystitservices.priceitdroid.utils.NetworkThreader;
import com.catalystitservices.priceitdroid.utils.NetworkingConstants;
import com.catalystitservices.priceitdroid.utils.Validate;
import com.google.zxing.integration.android.IntentResult;

import android.app.*;

public class CreateUserFragment extends SpiceManagerFragment {

	
	private EditText mEmail;
	private EditText mFirstName;
	private EditText mLastName;
	private Button mSubmit;
	private String mToken;
	NetworkThreader network_post;

	
	public static CreateUserFragment newInstance(String token) {
		
		Bundle args = new Bundle();
		args.putSerializable(Constants.AUTH_TOKEN, token);
	
		CreateUserFragment fragment = new CreateUserFragment();
		fragment.setArguments(args);

		return fragment;
	}
	public void onCreate(Bundle savedInstanceState) {
		Log.d("Code", "In Fragment Create");
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActivity().getActionBar();
	
		actionBar.setTitle(R.string.create_user_headline);
		mToken  = (String)getArguments().getSerializable(Constants.AUTH_TOKEN);
		
		Log.d("Code2","This is the token" + mToken);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_create_user,
				container, false);
		mEmail = (EditText) view.findViewById(R.id.email_editText);
		mEmail.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {

				if (Validate.isValidEmail(s.toString())) {
					mEmail.setBackgroundResource(0);
				} else {
					mEmail.setBackgroundResource(R.drawable.edit_text_error_background);
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}
		});
		
		

			
		mFirstName = (EditText) view.findViewById(R.id.firstName_editText);
		mFirstName.addTextChangedListener(new TextWatcher() {
		
			@Override
			public void afterTextChanged(Editable s) {
				String firstName = s.toString();
				if (!(Validate.validName(firstName))) {
					mFirstName
							.setBackgroundResource(R.drawable.edit_text_error_background);
				} else {
					mFirstName.setBackgroundResource(0);
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
		mLastName = (EditText) view.findViewById(R.id.lastName_editText);

		mLastName.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				String lastName = s.toString();
				if (!(Validate.validName(lastName))) {
					mLastName
							.setBackgroundResource(R.drawable.edit_text_error_background);
				} else {
					mLastName.setBackgroundResource(0);
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
		mSubmit = (Button) view.findViewById(R.id.submit_button);
		mSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String email = mEmail.getText().toString();
				String firstName = mFirstName.getText().toString();
				String lastName = mLastName.getText().toString();

				
				if(Validate.isValidEmail(email) && Validate.validName(firstName) && Validate.validName(lastName)){
				
					
					NewUser newUser = new NewUser(firstName, lastName, email, mToken);
				
					network_post = new NetworkThreader(NetworkingConstants.CREATE_USER_URI,spiceManager, CreateUserFragment.this, newUser);
				//	network_post.postObjectToServer("createUserPost");

				} else {
					Toast.makeText(getActivity(), R.string.input_field_error, 10).show();
				}

			}
		});
		
		return view;
	}


	@Override
	public void doSomeThang(Object result) {
		
		
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
	
		
	}

	@Override
	public void feedbackSomeThang(Object result) {
		
		Toast.makeText( getActivity(), "User Created", Toast.LENGTH_SHORT ).show();
		
	}

}
