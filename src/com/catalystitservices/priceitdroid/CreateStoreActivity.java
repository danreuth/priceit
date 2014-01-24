package com.catalystitservices.priceitdroid;

import com.catalystitservices.priceitdroid.fragments.create.CreateStoreFragment;

import android.app.Fragment;

public class CreateStoreActivity extends AbstractSingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new CreateStoreFragment();
	}

}
