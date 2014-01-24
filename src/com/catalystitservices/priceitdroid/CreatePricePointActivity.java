package com.catalystitservices.priceitdroid;

import com.catalystitservices.priceitdroid.fragments.create.CreatePricePointFragment;

import android.app.Fragment;

public class CreatePricePointActivity extends AbstractSingleFragmentActivity{

	@Override
	protected Fragment createFragment() {
		return new CreatePricePointFragment();
	}

}
