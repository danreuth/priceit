package com.catalystitservices.priceitdroid;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;


public abstract class AbstractSingleFragmentActivity extends Activity {

	protected abstract Fragment createFragment();
	
	@Override  	
	public void onCreate(Bundle onSavedInstanceState){
		super.onCreate(onSavedInstanceState);
		setContentView(R.layout.activity_abstract_single_fragment_holder);
		FragmentManager fm = getFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragmentHolder);
		if(fragment == null){
			fragment = createFragment();
			fm.beginTransaction().add(R.id.fragmentHolder, fragment).commit();
		}
	}
}
