package com.catalystitservices.priceitdroid;

import com.catalystitservices.priceitdroid.fragments.display.DatePickerFragment;

import android.app.Fragment;

public class DatePickerActivity extends AbstractSingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new DatePickerFragment();
	}


}
