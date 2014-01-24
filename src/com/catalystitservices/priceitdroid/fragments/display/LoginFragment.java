package com.catalystitservices.priceitdroid.fragments.display;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.catalystitservices.priceitdroid.CreateUserActivity;
import com.catalystitservices.priceitdroid.LoginActivity;
import com.catalystitservices.priceitdroid.MainMenuActivity;
import com.catalystitservices.priceitdroid.R;

public class LoginFragment extends Fragment {

	private Button mLogin;
	private Button mRegister;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActivity().getActionBar();
		actionBar.hide();
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		
		View loginPage = inflater.inflate(R.layout.fragment_login, container, false);
		mLogin = (Button) loginPage.findViewById(R.id.login_submit_button);
		mLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent mainMenu = new Intent(getActivity( ), MainMenuActivity.class);
				startActivity(mainMenu);
			}
		});
	//	mRegister = (Button) loginPage.findViewById(R.id.login_register_button);
		mRegister.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent register = new Intent(getActivity(), CreateUserActivity.class);
				startActivity(register);
			}
		});
		
		return loginPage;
	}
}
