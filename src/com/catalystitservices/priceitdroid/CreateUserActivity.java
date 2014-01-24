package com.catalystitservices.priceitdroid;

import com.catalystitservices.priceitdroid.fragments.create.CreateUserFragment;
import com.catalystitservices.priceitdroid.utils.Constants;



import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;



public class CreateUserActivity extends Activity {

	protected Fragment createFragment(String token) {
		return CreateUserFragment.newInstance(token);
	}

	@Override
	public void onCreate(Bundle onSavedInstanceState) {
		super.onCreate(onSavedInstanceState);
		setContentView(R.layout.activity_abstract_single_fragment_holder);
		FragmentManager fm = getFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragmentHolder);
		String token = getIntent().getStringExtra(Constants.AUTH_TOKEN);
		
		
		if(fragment == null){
			fragment = createFragment(token);
			
			fm.beginTransaction().add(R.id.fragmentHolder, fragment).commit();
			
		}
	}
	
	

}
 