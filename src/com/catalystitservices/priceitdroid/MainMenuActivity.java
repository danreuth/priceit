package com.catalystitservices.priceitdroid;

import android.app.Fragment;
import com.catalystitservices.priceitdroid.fragments.display.MainMenuFragment;

public class MainMenuActivity extends AbstractSingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new MainMenuFragment();
	}

}
