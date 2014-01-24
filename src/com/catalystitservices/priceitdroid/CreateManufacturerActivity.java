package com.catalystitservices.priceitdroid;

import android.app.Fragment;

import com.catalystitservices.priceitdroid.fragments.create.CreateManufacturerFragment;

public class CreateManufacturerActivity extends AbstractSingleFragmentActivity{
	
	@Override
	protected Fragment createFragment() {
		return new CreateManufacturerFragment();
	}

}
