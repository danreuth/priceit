package com.catalystitservices.priceitdroid;

import com.catalystitservices.priceitdroid.fragments.display.DisplayManufacturersFragment;
import com.catalystitservices.priceitdroid.utils.Constants;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.util.Log;
import android.view.Menu;

public class DeleteManufacturerActivity extends  Activity {

	FragmentManager fm;
	@Override  	
	public void onCreate(Bundle onSavedInstanceState){
		super.onCreate(onSavedInstanceState);
		setContentView(R.layout.activity_abstract_single_fragment_holder);
		FragmentManager fm = getFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragmentHolder);

		if(fragment == null){
			fragment = createFragment();
			Log.d("Code", "Before Transaction");
			fm.beginTransaction().add(R.id.fragmentHolder, fragment).commit();
			Log.d("Code", "AfterTransaction");
		}
		
	
		
	}
	private Fragment createFragment() {
		return DisplayManufacturersFragment.newInstance(Constants.DELETE);
	}


}