package com.catalystitservices.priceitdroid;

import java.util.ArrayList;

import com.catalystitservices.priceitdroid.fragments.display.DisplayAddressListFragment;

import android.app.Fragment;
import android.location.Address;


public class DisplayAddressListActivity extends AbstractSingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		
		ArrayList<Address> list = (ArrayList<Address>)getIntent().getSerializableExtra(DisplayAddressListFragment.EXTRA_ADDRESS_LIST);
		
		return DisplayAddressListFragment.newInstance(list);
	}

}
