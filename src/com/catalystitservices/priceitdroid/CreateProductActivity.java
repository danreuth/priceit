package com.catalystitservices.priceitdroid;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;

import com.catalystitservices.priceitdroid.fragments.create.CreateProductFragment;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class CreateProductActivity extends AbstractSingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new CreateProductFragment();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);

		IntentResult scanResult = IntentIntegrator.parseActivityResult(
				requestCode, resultCode, intent);
		
		if (scanResult != null) {
			FragmentManager fm = getFragmentManager();

			Fragment newFrame = CreateProductFragment.newInstance(scanResult);

			fm.beginTransaction().replace(R.id.fragmentHolder, newFrame)
					.commit();
		}

	}

}
