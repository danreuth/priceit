package com.catalystitservices.priceitdroid;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

import com.catalystitservices.priceitdroid.fragments.display.DisplayManufacturersFragment;
import com.catalystitservices.priceitdroid.fragments.display.DisplayStoresFragment;

public class EditStoreActivity extends AbstractSingleFragmentActivity{
	FragmentManager fm;
	@Override  	
	public void onCreate(Bundle onSavedInstanceState){
		super.onCreate(onSavedInstanceState);
		Log.e("inActitiy", "fdsafds");
		setContentView(R.layout.activity_abstract_single_fragment_holder);
		if(fm == null){
			fm = getFragmentManager();
			Fragment fragmentHolder = fm.findFragmentById(R.id.fragmentHolder);
			
			if(fragmentHolder == null){
				fragmentHolder = createFragment();
				fm.beginTransaction()
				.add(R.id.fragmentHolder, fragmentHolder)			
				.commit();
				
			}
		}
		
	
		
	}
	@Override
	protected Fragment createFragment() {
		return new DisplayStoresFragment();
	}
}
